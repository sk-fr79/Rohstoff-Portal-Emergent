package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class RGOM_bt_DownloadMailAttachments extends MyE2_Button {

	private RECORD_VKOPF_RG  recRG = null;
	
	public RGOM_bt_DownloadMailAttachments(RECORD_VKOPF_RG  rec_RG) {
		super(E2_ResourceIcon.get_RI("down_mini.png"), true);
		
		this.recRG = rec_RG;
		
		this.setToolTipText(new MyE2_String("Download der Rechnungsanlagen ...").CTrans());
		
		this.add_oActionAgent(new ownAction());
	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RGOM_bt_DownloadMailAttachments oThis = RGOM_bt_DownloadMailAttachments.this;
			
			MyE2_MessageVector  o_mv = new MyE2_MessageVector();
			
			
			myTempFile  downFile = new RGOM___GenerateSingleConcatenatedPdf(oThis.recRG, true, o_mv).get_TempFile();
			
			if (downFile==null) {
				o_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine PDF-Anlagen gefunden!")));
			}
			
			if (o_mv.get_bHasAlarms()) {
				bibMSG.add_MESSAGE(o_mv);
			} else {
				downFile.starteDownLoad(downFile.get_NamensKern()+".pdf", "application/pdf");
			}
		}
	}
	
	
	
}
