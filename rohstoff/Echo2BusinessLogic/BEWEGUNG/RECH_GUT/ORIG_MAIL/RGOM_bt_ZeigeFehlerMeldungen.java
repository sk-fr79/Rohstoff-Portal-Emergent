package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageGrid_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_RECORD_VKOPF_RG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class RGOM_bt_ZeigeFehlerMeldungen extends MyE2_ButtonInLIST
{
	private MyE2_MessageVector  mv_fehler = null;
	private RECORD_VKOPF_RG 	recVKOPF = null;
	
	public RGOM_bt_ZeigeFehlerMeldungen(MyE2_MessageVector  mvfehler, RECORD_VKOPF_RG rec_VKOPF) {
		super(RGOM__CONST.ICON__ORIG_EMAIL_ERROR, false);
			
		this.mv_fehler = mvfehler;
		this.recVKOPF = rec_VKOPF;
		
		this.setToolTipText(new MyE2_String("Fehler anzeigen ...").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			new ownModulContainer();
		}
	}
	
	private class ownModulContainer extends E2_BasicModuleContainer {

		public ownModulContainer() throws myException {
			super();
			MyE2_MessageVector  mv_kopie = new MyE2_MessageVector();
			mv_kopie.add_MESSAGE(RGOM_bt_ZeigeFehlerMeldungen.this.mv_fehler);
			
			MyE2_Grid gridInnen = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.add(gridInnen, E2_INSETS.I(1,1,1,1));
			
			gridInnen.add(new MyE2_MessageGrid_NG(mv_kopie,false,this), E2_INSETS.I(10,10,10,10));
			
			if (bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES() || bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
				gridInnen.add(new notfall_button(), E2_INSETS.I(10,10,10,10));
			}
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(400), new MyE2_String("Fehlermeldungen"));
		}
		
	}
	
	
	private class notfall_button extends MyE2_Button {

		public notfall_button() {
			super(new MyE2_String("Reparatur"), MyE2_Button.StyleTextButton(
					new E2_ColorHelpBackground(), new E2_ColorHelpBackground(), Color.BLACK, Color.BLACK, true));
			
			this.setToolTipText(new MyE2_String("Zurücksetzen der Belegerstellung, Löschen der Originalbelege und evtl. angehängter eMail-Dateien").CTrans());
			this.add_oActionAgent(new notfall_action());
		}
		
		
	}
	
	private class notfall_action extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			new CHK_RECORD_VKOPF_RG(RGOM_bt_ZeigeFehlerMeldungen.this.recVKOPF).RollBackToStatus_Orig(mv);
			
			if (mv.get_bIsOK()) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Beleg ist zurückgesetzt. Bitte vernichten Sie alle Dokumente vor dem Neudrucken !")));
			} else {
				bibMSG.add_MESSAGE(mv);
			}
		}
		
	}
	
}
