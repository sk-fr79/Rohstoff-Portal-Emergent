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
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_RECORD_VKOPF_RG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class RGOM_bt_ZeigeSendestatus extends MyE2_ButtonInLIST
{
	private RECORD_VKOPF_RG 	recVKOPF = null;
	
	public RGOM_bt_ZeigeSendestatus(RECORD_VKOPF_RG rec_VKOPF) {
		super(RGOM__CONST.ICON__ORIG_EMAIL_DONE, false);
		this.recVKOPF = rec_VKOPF;
		
		this.setToolTipText(new MyE2_String("Sendstatus anzeigen ...").CTrans());
		
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
			MyE2_Grid gridInnen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.add(gridInnen, E2_INSETS.I(1,1,1,1));

			RECORD_EMAIL_SEND 			recSend = 	new CHK_RECORD_VKOPF_RG(RGOM_bt_ZeigeSendestatus.this.recVKOPF).get_RECORD_EMAIL_SEND_withOriginal();
			RECLIST_EMAIL_SEND_TARGETS 	rlTargets = recSend.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send();
			RECLIST_EMAIL_SEND_ATTACH 	rlAttach =  recSend.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send();
				
			if (recSend != null) {

				gridInnen.add(new MyE2_Label(new MyE2_String("Sendestatus der Original-Email")), 2, E2_INSETS.I(1,1,1,1));
				if (rlTargets.size()==1) {
					gridInnen.add(new MyE2_Label(new MyE2_String("Verschickt am")), 1, E2_INSETS.I(1,1,1,1));
					String cTime = rlTargets.get(0).get(_DB.EMAIL_SEND_TARGETS$SENDING_TIME).get_cDateTimeValueFormated();
					gridInnen.add(new MyE2_Label(S.NN(cTime)), 1, E2_INSETS.I(1,1,1,1));
				}
				
				gridInnen.add(new MyE2_Label(new MyE2_String("Verschickt an")), 1, E2_INSETS.I(1,1,1,1));
				for (int i=0;i<rlTargets.size();i++) {
					if (i>0) {
						gridInnen.add(new MyE2_Label(""), 1, E2_INSETS.I(1,1,1,1));
					}
					gridInnen.add(new MyE2_Label(rlTargets.get(i).get_TARGET_ADRESS_cUF_NN("")), 1, E2_INSETS.I(1,1,1,1));
				}

				gridInnen.add(new MyE2_Label(new MyE2_String("Anlagen")), 1, E2_INSETS.I(1,1,1,1));
				for (int i=0;i<rlAttach.size();i++) {
					if (i>0) {
						gridInnen.add(new MyE2_Label(""), 1, E2_INSETS.I(1,1,1,1));
					}
					gridInnen.add(new MyE2_Label(rlAttach.get(i).get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_DOWNLOADNAME_cUF_NN("")), 1, E2_INSETS.I(1,1,1,1));
				}
				
			}
			
			if (bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES() || bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
				gridInnen.add(new notfall_button(), 2, E2_INSETS.I(10,10,10,10));
			}
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(400), new MyE2_String("Sendestatus des eMail-Originals anzeigen"));
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
			new CHK_RECORD_VKOPF_RG(RGOM_bt_ZeigeSendestatus.this.recVKOPF).RollBackToStatus_Orig(mv);
			
			if (mv.get_bIsOK()) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Mit dem Ausführen der Statements ist der Beleg zurückgesetzt. Bitte vernichten Sie alle Dokumente vor dem Neudrucken !")));
			} else {
				bibMSG.add_MESSAGE(mv);
			}
		}
		
	}
	
}
