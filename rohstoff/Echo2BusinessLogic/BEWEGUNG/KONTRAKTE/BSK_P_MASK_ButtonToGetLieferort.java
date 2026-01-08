package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSK_P_MASK_ButtonToGetLieferort extends MyE2_Button
{

	public BSK_P_MASK_ButtonToGetLieferort()
	{
		super(E2_ResourceIcon.get_RI("popup.png"), true);
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Lieferorte/Lagerorte des Kunden/Lieferanten").CTrans());
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSK_P_MASK_ButtonToGetLieferort oThis = (BSK_P_MASK_ButtonToGetLieferort)oExecInfo.get_MyActionEvent().getSource();
			
			E2_ComponentMAP  oMap_VPOS_KON_TRAKT = oThis.EXT().get_oComponentMAP();
			
			E2_ComponentMAP  oMapVPOS_KON = oMap_VPOS_KON_TRAKT.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get(0);
			
			SQLFieldMAP      oFM = oMapVPOS_KON.get_oSQLFieldMAP();
			
			String cID_VKOPF_KON = ((SQLFieldForRestrictTableRange)oFM.get_SQLField("ID_VKOPF_KON")).get_cRestictionValue_IN_DB_FORMAT();
			
			if (S.isFull(cID_VKOPF_KON))
			{
				RECORD_VKOPF_KON  recVKOPF_KON = new RECORD_VKOPF_KON(cID_VKOPF_KON);
				new ownBasicContainer(recVKOPF_KON);
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Ich konnte den Kopf-Satz nicht finden !!"));
			}
			
		}
	}
	
	
	
	private class ownBasicContainer extends E2_BasicModuleContainer
	{

		public ownBasicContainer(RECORD_VKOPF_KON  recVKOPF_KON) throws myException
		{
			super();
			
			Vector<String> vLieferorte = new Vector<String>();
			
			String cFeldListeAusMandantenZusatz = bibALL.get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ("FELDLISTE_KONTRAKT_LIEFERORT");
			
			if (S.isEmpty(cFeldListeAusMandantenZusatz))
			{
				cFeldListeAusMandantenZusatz="STRASSE|PLZ|ORT";
			}
			
			Vector<String> vNamenFelder = bibALL.TrenneZeile(cFeldListeAusMandantenZusatz, "|");

			vLieferorte.add(recVKOPF_KON.get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(vNamenFelder, "", "", "", " "));
			
			RECLIST_LIEFERADRESSE reclistLAGER = recVKOPF_KON.get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
			
			if (reclistLAGER.get_vKeyValues().size()>0)
			{
				for (int i=0;i<reclistLAGER.get_vKeyValues().size();i++)
				{
					vLieferorte.add(reclistLAGER.get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer().get___KETTE(vNamenFelder, "", "", "", " "));
				}
			}
			
			MyE2_Grid oGrid  = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			
			this.add(oGrid, E2_INSETS.I_5_5_5_5);
			
			if (vLieferorte.size()==1)  //dann gleich besetzen
			{
				this.setLieferort(vLieferorte.get(0));
			}
			else
			{
				for (int i=0;i<vLieferorte.size();i++)
				{
					MyE2_Button  oButtonSetOrt = new MyE2_Button(vLieferorte.get(i));
					oButtonSetOrt.add_oActionAgent(new actionToSetLieferort());
					oGrid.add_raw(oButtonSetOrt, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_2_5_2));
				}
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Bitte wählen Sie einen Lieferort..."));
			}
		}
		
		private class actionToSetLieferort extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_Button   ownButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
				ownBasicContainer.this.setLieferort(ownButton.getText());
				ownBasicContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
		}
		
		private void setLieferort(String cOrt) throws myException
		{
			BSK_P_MASK_ButtonToGetLieferort oThis = BSK_P_MASK_ButtonToGetLieferort.this;
			
			E2_ComponentMAP  oMap = oThis.EXT().get_oComponentMAP();

			oMap.get__DBComp("LIEFERORT").set_cActualMaskValue((cOrt+"                                                  ").substring(0,50).trim());

		}

	}
	
	
	
}
