package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_manage_attachments_and_mails;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class KFIX_P_L_BT_Position_Anlage_Management extends E2_manage_attachments_and_mails {

	private Rec20 rec20_vpos_kon = null;
	private KFIX_K_L_EXPANDER_4_ComponentMAP_NG parent = null;
	
	public KFIX_P_L_BT_Position_Anlage_Management(KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent, Rec20 rec_vpos_kon) throws myException {
		super();
		
		this.rec20_vpos_kon = rec_vpos_kon;
		this.parent = oParent;
		this.setOrientation(ORIENTATION_VERTICAL);
		this._fill();
		
	}
	
	@Override
	public String find_id_table_uf() throws myException {
		return rec20_vpos_kon.get_key_value();
	}

	@Override
	public _TAB find_tab() throws myException {
		return _TAB.vpos_kon;
	}

	@Override
	public String find_module_identifier() throws myException {
		return null;
	}

	@Override
	public VEK<XX_ActionAgentWhenCloseWindow> get_closeActionsForPopup() throws myException {
	
		return new VEK<XX_ActionAgentWhenCloseWindow>();//._a(new ownActionRefreshMarkLastUsed());
	}
	
	/*Unused for the moment*/
	@SuppressWarnings("unused")
	private class ownActionRefreshMarkLastUsed extends XX_ActionAgentWhenCloseWindow {

		public ownActionRefreshMarkLastUsed() {
			super(KFIX_P_L_BT_Position_Anlage_Management.this.parent.get_E2_ComponentMAP_this_BelongsTo().get_oModulContainerMASK_This_BelongsTo());
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			
		}
	}


}
