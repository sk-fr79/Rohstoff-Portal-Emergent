package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	private E2_NavigationList  m_navigationList;
	
	public BAM_IMPORT_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.BAM_IMPORT_LIST);
		
		this.set_bVisible_Row_For_Messages(true);
		
		m_navigationList = new E2_NavigationList();
		m_navigationList.INIT_WITH_ComponentMAP(new BAM_IMPORT_LIST_ComponentMap(this),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		BAM_IMPORT_LIST_BedienPanel oPanel = new BAM_IMPORT_LIST_BedienPanel(m_navigationList,new BAM_IMPORT_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(m_navigationList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oBAM_IMPORT_LIST_Selector().get_oSelVector().doActionPassiv();
	}

	/**
	 * Gibt die Navigation List der Liste zurück
	 * @return
	 */
	public E2_NavigationList get_oNaviList() {
		return m_navigationList;
	}
		
	
	
	
}
