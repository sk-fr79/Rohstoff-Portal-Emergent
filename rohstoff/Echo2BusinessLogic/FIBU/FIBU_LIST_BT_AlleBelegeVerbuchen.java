package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_LIST_BT_AlleBelegeVerbuchen extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;
	
	public FIBU_LIST_BT_AlleBelegeVerbuchen(E2_NavigationList NaviList)
	{
		super(E2_ResourceIcon.get_RI("verbuchen.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.oNaviList = NaviList;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("VERBUCHUNGSLAUF"));
		this.setToolTipText(new MyE2_String("Verbuchung bereits gedruckter Rechnungen/Gutschriften erzwingen").CTrans());
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			bibDB.ExecMultiSQLVector(bibALL.get_Vector("UPDATE "+bibE2.cTO()+".JT_VKOPF_RG SET ABGESCHLOSSEN=ABGESCHLOSSEN WHERE NVL(ABGESCHLOSSEN,'N')='Y' AND ID_VKOPF_RG NOT IN" +
					"( SELECT ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_FIBU )"),true);
			
			//jetzt die storno-paare finden (die auf belegebene storniert wurden
			String[][] arrayID_VKOPF_RG = bibDB.EinzelAbfrageInArray("SELECT ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(BUCHUNG_GESCHLOSSEN,'N')='N' AND ID_VKOPF_RG IS NOT NULL");
			
			int iCountStornoPaare = 0;
			
			for (int i=0;i<arrayID_VKOPF_RG.length;i++)
			{
				RECORD_VKOPF_RG recVKOPF_RG_ = new RECORD_VKOPF_RG(arrayID_VKOPF_RG[i][0]);
				
				/*
				 * Storno-Eintraege im Kopfsatz
				 * jetzt nachschauen, ob dieser fibu-eintrag einen Storno-vorgaenger hat. dann ist es ein storno-satz
				 * und (falls es einen BUCHUNG_GESCHLOSSEN='N' fibu-eintrag des vorgaengers gibt, dann
				 * und die endbetrage sich aufheben, dann wird BUCHUNG_GESCHLOSSEN='Y' bei beiden  
				 */
				Vector<String> vSQL_Rueck = new Vector<String>();
				if (!recVKOPF_RG_.get_ID_VKOPF_RG_STORNO_VORGAENGER_cUF_NN("-").equals("-"))
				{
					RECORD_VKOPF_RG recVKOPF_RG_StornoVorgaenger = new RECORD_VKOPF_RG(recVKOPF_RG_.get_ID_VKOPF_RG_STORNO_VORGAENGER_cUF_NN("-"));
					RECORD_VKOPF_RG recVKOPF_RG_StornoNachfolger = recVKOPF_RG_;
					
					RECORD_FIBU     recFibuEintragStornoVorgaenger = null;
					RECORD_FIBU     recFibuEintragStornoNachfolger = null;
					if (recVKOPF_RG_StornoVorgaenger.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(STORNIERT,'N')='N'",null,true).size()==1)
					{
						if (recVKOPF_RG_StornoNachfolger.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(STORNIERT,'N')='N'",null,true).size()==1)
						{
							recFibuEintragStornoVorgaenger = recVKOPF_RG_StornoVorgaenger.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(STORNIERT,'N')='N'",null,true).get(0);
							recFibuEintragStornoNachfolger = recVKOPF_RG_StornoNachfolger.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(STORNIERT,'N')='N'",null,true).get(0);
							if (	recFibuEintragStornoVorgaenger.is_BUCHUNG_GESCHLOSSEN_NO() && 
									recFibuEintragStornoVorgaenger.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).add(
											recFibuEintragStornoNachfolger.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO)).compareTo(BigDecimal.ZERO) ==0)
							{
								//dann werden beide als geschlossen definiert und bekommen eine buchungsblock-nummer
								
								try
								{
									recFibuEintragStornoVorgaenger.set_NEW_VALUE_BUCHUNG_GESCHLOSSEN("Y");
									recFibuEintragStornoNachfolger.set_NEW_VALUE_BUCHUNG_GESCHLOSSEN("Y");
									recFibuEintragStornoNachfolger.set_NEW_VALUE_BUCHUNGSBLOCK_NR(recFibuEintragStornoVorgaenger.get_BUCHUNGSBLOCK_NR_cUF());
									vSQL_Rueck.add(recFibuEintragStornoVorgaenger.get_SQL_UPDATE_STATEMENT(null, true));
									vSQL_Rueck.add(recFibuEintragStornoNachfolger.get_SQL_UPDATE_STATEMENT(null, true));
									bibDB.ExecMultiSQLVector(vSQL_Rueck, true);
									iCountStornoPaare++;
								} 
								catch (Exception e)
								{
									e.printStackTrace();
									throw new myException(e.getLocalizedMessage());
								}
							}
						}
					}
				}

			}
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Verarbeitete Storno-Paare: ",true,""+iCountStornoPaare,false)));
			FIBU_LIST_BT_AlleBelegeVerbuchen.this.oNaviList._REBUILD_COMPLETE_LIST("");
		}
	}
	
}
