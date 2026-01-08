package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.Vector;

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

public class E2_ButtonAttachmentUseOnToOfNavigationList extends E2_ButtonAttachments {

	
	private E2_NavigationList  m_naviList =  null;
	
	public E2_ButtonAttachmentUseOnToOfNavigationList() {
		super();
		this._image("attach_mini.png","attach_mini_grau.png");
		
		this.get_vActionAgentWhenCloseWindow().add(new ActionWhenWindowClose());
		
	}

	@Override
	public Long getIdTableForAttachment() throws myException {
		
		if (m_naviList==null) { throw new myException(this, "You must set E2_NavigationList !"); };
		
		Vector<String> v_ids = this.m_naviList.get_vSelectedIDs_Unformated();
		
		if (v_ids.size()==1) {
			MyLong ml= new MyLong(v_ids.get(0));
			if (ml.isOK()) {
				return ml.get_oLong();
			}
		}
		return null;
	}

	@Override
	public _TAB getTableForAttachment() throws myException {
		String s_tab = null;
		try {
			s_tab = this.m_naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE();
			return _TAB.findTable(S.NN(s_tab));
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}
		
	}

	
	@Override
	public MODUL getModule() throws myException {
		try {
			String s_module = this.m_naviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
			return MODUL.getModule(s_module);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		E2_ButtonAttachmentUseOnToOfNavigationList btCopy = new E2_ButtonAttachmentUseOnToOfNavigationList();
		btCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(btCopy));
		return btCopy;
	}


	public E2_NavigationList getNaviList() {
		return m_naviList;
	}

	public E2_ButtonAttachmentUseOnToOfNavigationList _setNaviList(E2_NavigationList naviList) {
		this.m_naviList = naviList;
		return this;
	}

	
	private class ActionWhenWindowClose extends XX_ActionAgentWhenCloseWindow {
		public ActionWhenWindowClose() {
			super(null);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {
				E2_ButtonAttachmentUseOnToOfNavigationList oThis = E2_ButtonAttachmentUseOnToOfNavigationList.this;
				if (oThis.m_naviList!=null) {
					m_naviList._REBUILD_ACTUAL_SITE("");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getLocalizedMessage());
			}
			
			
		}
	}
	
	
	
}
