package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__VALIDATOR_4_ATTACHMENT_POPUP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class ES_BtDownload extends MyE2_Button {

	public ES_BtDownload(RECORD_ARCHIVMEDIEN recArchiv) {
		super(E2_ResourceIcon.get_RI("down.png"),true);
		
		this.setToolTipText(new MyE2_String("Die hinterlegte Archivdatei downloaden").CTrans());
		
		this.add_oActionAgent(new ownActionAgentDownload(new RECORD_ARCHIVMEDIEN_ext(recArchiv)));
		
		this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DOWNLOAD_ATTACHMENT));

	}
	
	private class ownActionAgentDownload extends XX_ActionAgent {
		private RECORD_ARCHIVMEDIEN_ext RecArchiv = null;
		
		public ownActionAgentDownload(RECORD_ARCHIVMEDIEN_ext recArchiv) {
			super();
			RecArchiv = recArchiv;
		}

		public void executeAgentCode(ExecINFO oExecInfo) {
			try {
				RecArchiv.starte__downLoad();
			} catch (myException ex) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("RGOM_LISTE_ANLAGEN:Download not possible !"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}

}
