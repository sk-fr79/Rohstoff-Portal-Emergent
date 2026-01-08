/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FU_LIST_BT_STORNO_Fuhre extends MyE2_Button
{
	private E2_NavigationList 	oNavigationList = 	null;
	private Vector<String>		vSQL_Stack = 		new Vector<String>();
	private Vector<String>		vID_Selected = 		new Vector<String>();
	
	private MyE2_TextArea oTextStornoBeguendung = new MyE2_TextArea("",300,500,5);
	
	
	public FU_LIST_BT_STORNO_Fuhre(E2_NavigationList onavigationlist)
	{
		super(E2_ResourceIcon.get_RI("storno.png"),true);
		this.oNavigationList = onavigationlist;
		this.add_oActionAgent(new ownActionAgent());

		this.setToolTipText((new MyE2_String("Stornieren/Reaktivieren der angewählten Position ...")).CTrans());
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"STORNO_FUHREN"));

		
//		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA_FUHRE","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Fuhre wurde bereits gelöscht !")));
		//this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA_FUHRE","  NVL(IST_STORNIERT,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Fuhre wurde bereits storniert !")));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA_FUHRE","  NVL(ANTEIL_LADEMENGE_LIEF,0),  NVL(ANTEIL_ABLADEMENGE_ABN,0)",bibALL.get_Array("0","0"),true, new MyE2_String("Die Fuhre hat bereits LADE- / und oder ABLADEMENGE !")));
		this.add_IDValidator(new ownValidatorNoZusatzorte());
		this.add_IDValidator(new FU__Validator_Fuhre_hat_buchungs_positionen_ODER_ist_gloescht());
		
	}

	

	private class ownValidatorNoZusatzorte extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_UnFormated) 	throws myException
		{
			RECORD_VPOS_TPA_FUHRE oRecFuhre = new RECORD_VPOS_TPA_FUHRE(cID_UnFormated);
			
			long iCountUngeloeschteOrte = oRecFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_ID_VPOS_TPA_FUHRE_l_Count_NotNull(
					new RECLIST_VPOS_TPA_FUHRE_ORT.Validation()
					{
						@Override
						public boolean isValid(RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
						{
							return orecord.is_DELETED_NO();
						}
					});

			MyE2_MessageVector oMV = new MyE2_MessageVector();
			if (iCountUngeloeschteOrte>0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es existieren Zusatzorte. Bitte vor dem Storno löschen !!")));
			}
			
			return oMV;
		}
		
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_LIST_BT_STORNO_Fuhre oThis = FU_LIST_BT_STORNO_Fuhre.this;
			
			FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.removeAllElements();
			FU_LIST_BT_STORNO_Fuhre.this.vID_Selected.removeAllElements();
			FU_LIST_BT_STORNO_Fuhre.this.vID_Selected.addAll(FU_LIST_BT_STORNO_Fuhre.this.oNavigationList.get_vSelectedIDs_Unformated());

			if (FU_LIST_BT_STORNO_Fuhre.this.vID_Selected.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte markieren Sie GENAU EINE Fuhre zum Stornieren/Reaktivieren !"));
				return;
			}

			bibMSG.add_MESSAGE(oThis.valid_IDValidation(oThis.vID_Selected));
			
			
			if (bibMSG.get_bIsOK())
			{
				
				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(FU_LIST_BT_STORNO_Fuhre.this.vID_Selected.get(0));

				//wenn die fuhre an einem TPA haengt, dann weitere statements
				boolean bHasTPA =  ! recFuhre.get_ID_VPOS_TPA_cUF_NN("0").equals("0");   //tpa nur, wenn eine ID_VPOS_TPA eingetragen ist
				
				//jetzt entscheiden, ob storno oder wieder aktivieren
				if (recFuhre.is_IST_STORNIERT_YES())
				{
					FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.add(
							"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET IST_STORNIERT='N',STORNO_GRUND=NULL,STORNO_KUERZEL=NULL, ZEITSTEMPEL="+bibALL.MakeSql(bibALL.get_cDateTimeNOWInverse())+"  WHERE ID_VPOS_TPA_FUHRE="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
					
					if (bHasTPA)
					{
						String cID_VKOPF_TPA = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_ID_VKOPF_TPA_cUF_NN("leer");
						if (! bibALL.isInteger(cID_VKOPF_TPA))
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error querying VKOPF_TPA to VPOS_TPA !"));
						}
						else
						{
							//aus artikelbez 2 sollte der begriff storno wieder raus
							String cArtbez2 = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_ARTBEZ2_cF_NN("");
							cArtbez2 = bibALL.ReplaceTeilString(cArtbez2,"STORNO", "");
							FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA SET ARTBEZ2="+bibALL.MakeSql(cArtbez2)+" WHERE ID_VPOS_TPA="+recFuhre.get_ID_VPOS_TPA_cUF_NN("0"));
							
							boolean bID_VKOPF_CLOSED = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().is_ABGESCHLOSSEN_YES();
							if (bID_VKOPF_CLOSED)
							{
								/*
								 * dann wird automatisch entsperrt
								 */
								FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA " + 
										" SET ABGESCHLOSSEN='N',LETZTER_DRUCK = DRUCKDATUM, DRUCKDATUM=NULL, ZAEHLER_ENTSPERRUNG= NVL(ZAEHLER_ENTSPERRUNG,0)+1  WHERE "+
										" ID_VKOPF_TPA = "+cID_VKOPF_TPA);
							}
						}
					}				
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack, true));
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Stornierung wurde revidiert! Bitte beachten Sie die 0-Mengen !"));
						}
					}

				}         //ende reaktvierungsblock
				else
				{
					
					FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.add(
							"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET MENGE_ABLADEN_KO=0,MENGE_AUFLADEN_KO=0,MENGE_VORGABE_KO=0, IST_STORNIERT='Y', ZEITSTEMPEL="+bibALL.MakeSql(bibALL.get_cDateTimeNOWInverse())+"  WHERE ID_VPOS_TPA_FUHRE="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
					
					if (bHasTPA)
					{
						String cID_VKOPF_TPA = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_ID_VKOPF_TPA_cUF_NN("leer");
						if (! bibALL.isInteger(cID_VKOPF_TPA))
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error querying VKOPF_TPA to VPOS_TPA !"));
						}
						else
						{
							FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA SET ANZAHL=0,EINZELPREIS=0,GESAMTPREIS=0, ARTBEZ2=  NVL(ARTBEZ2,' ')||'\nSTORNO'  WHERE ID_VPOS_TPA="+recFuhre.get_ID_VPOS_TPA_cUF_NN("0"));
							
							boolean bID_VKOPF_CLOSED = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().is_ABGESCHLOSSEN_YES();
							if (bID_VKOPF_CLOSED)
							{
								/*
								 * dann wird automatisch entsperrt
								 */
								FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA " + 
										" SET ABGESCHLOSSEN='N',LETZTER_DRUCK = DRUCKDATUM, DRUCKDATUM=NULL, ZAEHLER_ENTSPERRUNG= NVL(ZAEHLER_ENTSPERRUNG,0)+1  WHERE "+
										" ID_VKOPF_TPA = "+cID_VKOPF_TPA);
							}
						}
					}				
					
					
					if (bibMSG.get_bIsOK())
					{
						oThis.oTextStornoBeguendung.setText("");
						
						E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
								new MyE2_String("Sicherheitsabfrage:"),
								new MyE2_String("Anzahl zu stornierende Fuhren :"+FU_LIST_BT_STORNO_Fuhre.this.vID_Selected.size()),
								bibALL.get_Vector(new MyE2_String("Bitte geben sie den Storno-Grund an !")),
								oThis.oTextStornoBeguendung,
								new ownValidatorStornoBegruendung(),
								new MyE2_String("JA"), 
								new MyE2_String("NEIN"),
								new Extent(250), 
								new Extent(200)
						);
						
						oConfirm.set_ActionAgentForOK( 
								new XX_ActionAgent()
								{
									public void executeAgentCode(ExecINFO ooExecInfo) throws myException
									{
										FU_LIST_BT_STORNO_Fuhre ooThis = FU_LIST_BT_STORNO_Fuhre.this;
										ooThis.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET" +
												" STORNO_GRUND="+bibALL.MakeSql(ooThis.oTextStornoBeguendung.getText())+"," +
												"STORNO_KUERZEL="+bibALL.MakeSql(bibALL.get_KUERZEL())+
												" WHERE ID_VPOS_TPA_FUHRE IN ("+bibALL.Concatenate(ooThis.vID_Selected, ",", "-1")+")");
										bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(FU_LIST_BT_STORNO_Fuhre.this.vSQL_Stack,true));
										if (bibMSG.get_bHasAlarms())
										{
											bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Ausführen der Storno-Anweisung !"));
										}
										else
										{
											bibMSG.add_MESSAGE(new MyE2_Info_Message("Stornierung durchgeführt !"));
										}
										/*
										 * auf jeden fall die liste refreshen
										 */
										try
										{
											for (int i=0;i<vID_Selected.size();i++)
											{
												String cID_VPOS_TPA_FUHRE = (String)vID_Selected.get(i);
												FU_LIST_BT_STORNO_Fuhre.this.oNavigationList.Refresh_ComponentMAP(cID_VPOS_TPA_FUHRE,E2_ComponentMAP.STATUS_VIEW);
											}
										}
										catch (myException ex)
										{
											bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error refreshing List after update!"));
											bibMSG.add_MESSAGE(ex.get_ErrorMessage());
										}
										
									}
								});
						oConfirm.show_POPUP_BOX();
						
					}
				}        //ende stornoblock


				/*
				 * auf jeden fall die liste refreshen
				 */
				try
				{
					FU_LIST_BT_STORNO_Fuhre.this.oNavigationList.Refresh_ComponentMAP(vID_Selected.get(0), E2_ComponentMAP.STATUS_VIEW);
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
				
			}
		}
	}
	

	private class ownValidatorStornoBegruendung extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException	
		{
			FU_LIST_BT_STORNO_Fuhre ooThis = FU_LIST_BT_STORNO_Fuhre.this;
			MyE2_MessageVector ovRueck = new MyE2_MessageVector();
			if (S.isEmpty(ooThis.oTextStornoBeguendung.getText()))
			{
				ovRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte eine Begründung für die Stornierung eingeben !")));
			}
			else if (ooThis.oTextStornoBeguendung.getText().length()>299)
			{
				ovRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Begründung bitte kürzer halten !")));
			}
			return ovRueck;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated) 	throws myException
		{
			return null;
		}

	}
	
	
}