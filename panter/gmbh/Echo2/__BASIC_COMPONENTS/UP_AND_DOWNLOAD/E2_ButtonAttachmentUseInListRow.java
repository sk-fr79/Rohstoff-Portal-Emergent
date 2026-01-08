package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_ButtonAttachmentUseInListRow extends E2_ButtonAttachments {

	public E2_ButtonAttachmentUseInListRow() {
		super();
		this._image("attach_mini.png","attach_mini_grau.png");
		this.get_vActionAgentWhenCloseWindow().add(new ActionWhenWindowClose());
	}

	@Override
	public Long getIdTableForAttachment() throws myException {
		MyLong ml= new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		if (ml.isOK()) {
			return ml.get_oLong();
		}
		return null;
	}

	@Override
	public _TAB getTableForAttachment() throws myException {
		String s_tab = null;
		try {
			s_tab = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_oSQLFieldMAP().get_cMAIN_TABLE();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return _TAB.findTable(S.NN(s_tab));
	}

	
	@Override
	public MODUL getModule() throws myException {
		String s_module = this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
		return MODUL.getModule(s_module);
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		E2_ButtonAttachmentUseInListRow btCopy = new E2_ButtonAttachmentUseInListRow();
		btCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(btCopy));
		return btCopy;
	}

	@Override
	/**
	 * listenbutten, muss aktiv sein
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	private class ActionWhenWindowClose extends XX_ActionAgentWhenCloseWindow {
		public ActionWhenWindowClose() {
			super(null);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {
				E2_NavigationList naviList = E2_ButtonAttachmentUseInListRow.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
				naviList._REBUILD_ACTUAL_SITE("");
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getLocalizedMessage());
			}
			
			
		}
	}
	

	
	
}
