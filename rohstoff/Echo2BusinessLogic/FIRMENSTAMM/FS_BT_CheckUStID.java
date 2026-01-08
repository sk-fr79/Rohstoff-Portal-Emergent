package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic._TAX.TaxId;
import rohstoff.Echo2BusinessLogic._TAX.TaxIdChecker;

public class FS_BT_CheckUStID extends MyE2_ButtonInLIST {
	private String fieldLkzName;
	private String fieldNumberName;
	
// Unused constructor for text button
//	public FS_BT_CheckUStID() {
//		super(new MyE2_String("CKH"), MyE2_Button.StyleImageButtonCenteredWithDDBorder());
//		this.add_oActionAgent(new CheckUStIdAction());
//	}

	/** 
	 * Construct Tax id check button and provide the names of the fields where the 
	 * tax ID's country code prefix and the number suffix will be found on the UI.
	 * @param lkzName
	 * @param numberName
	 */
	public FS_BT_CheckUStID(String lkzName, String numberName) {
		super(E2_ResourceIcon.get_RI("tax-ok.png") , E2_ResourceIcon.get_RI("tax-ok.png"));
		fieldLkzName = lkzName;
		fieldNumberName = numberName;
		this.add_oActionAgent(new CheckUStIdAction());
	}

	private class CheckUStIdAction extends XX_ActionAgent {
		public CheckUStIdAction() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			E2_ComponentMAP map = FS_BT_CheckUStID.this.EXT().get_oComponentMAP();
			
			String lkz = "", no = "";

			try {
				// Try to get field values from mask
				lkz = ((MyE2_DB_TextField)map.get(fieldLkzName)).get_cActualMaskValue();
				no = ((MyE2_DB_TextField)map.get(fieldNumberName)).get_cActualMaskValue();
			} catch (NullPointerException e) {
				// Otherwise get database entries
				lkz = map.get_oInternalSQLResultMAP().get(fieldLkzName).get_FieldValueUnformated();
				no = map.get_oInternalSQLResultMAP().get(fieldNumberName).get_FieldValueUnformated();

			}
			
			TaxIdChecker tic = new TaxIdChecker();
			try {
				TaxId tid = new TaxId(lkz, no);
				if (!tic.isValid(tid)) { 
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Ungültige UST-ID: '"+lkz+" "+no+"'. Meldung: "+tic.getMessage()));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("UST-ID ist gültig: "+lkz+" "+no));
				}
			} catch (IllegalArgumentException e) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Syntaktisch ungültige USt-ID: "+e.getMessage()));
			}

		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FS_BT_CheckUStID oButton = new FS_BT_CheckUStID(this.fieldLkzName, this.fieldNumberName);
		return oButton;
	}
}