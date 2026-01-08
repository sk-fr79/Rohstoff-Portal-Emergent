package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainerUseInPopupAction;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class BANK_LIST_BasicModuleContainer extends Project_BasicModuleContainer implements IF_BasicModuleContainerUseInPopupAction {
	
	public static final String NAME_OF_CHECKBOX_IN_LIST =					"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =					"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MASK_FIELD_DAUGHTER_TELEFON = 				"MASK_FIELD_DAUGHTER_TELEFON";
	public static final String MASK_FIELD_DAUGHTER_EMAIL = 					"MASK_FIELD_DAUGHTER_EMAIL";
	public static final String MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS = 	"MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS";
	public static final String MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER = 	"MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER";
	
	
	private E2_NavigationList m_naviList = null; 
	
	public BANK_LIST_BasicModuleContainer() throws myException 	{
		super(E2_MODULNAMES.NAME_MODUL_BANKENSTAMM_LISTE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		m_naviList = new E2_NavigationList();
		m_naviList.INIT_WITH_ComponentMAP(new BANK_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		BANK_LIST_BedienPanel oPanel = new BANK_LIST_BedienPanel(m_naviList,new BANK_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(m_naviList, E2_INSETS.I_2_2_2_2);

	}
	
	
	public BANK_LIST_BasicModuleContainer _populate() throws myException {
		m_naviList._REBUILD_COMPLETE_LIST("");

		return this;
	}


	@Override
	public E2_NavigationList getNavigationList() throws myException {
		return this.m_naviList;
	}


	@Override
	public void fillList() throws myException {
		this.m_naviList._REBUILD_COMPLETE_LIST("");
	}


	@Override
	public String getMapKey4ReturnButton() {
		return "6fe75c5a-a512-11e8-98d0-529269fb1459";
	}


	@Override
	public MyE2_String getTitle4Popup() {
		return S.ms("Bankenstamm");
	}


	@Override
	public void fillList(Object obj) throws myException {
		if (obj instanceof Long) {
			this.m_naviList._REBUILD_COMPLETE_LIST("");
			this.m_naviList.gotoSiteWithID_orFirstSite(((Long)obj).toString());
			this.m_naviList.Mark_ID_IF_IN_Page(((Long)obj).toString());
		} else {
			throw new myException(this, "only Long-Objects allowed !");
		}
		
	}
	
	
}
