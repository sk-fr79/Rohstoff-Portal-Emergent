package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_CONST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		SCAN_CONST.SCAN_NAMES.CHECKBOX_LISTE.db_val();
	public static final String NAME_OF_LISTMARKER_IN_LIST =		SCAN_CONST.SCAN_NAMES.MARKER_LISTE.db_val();

	public SCAN_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.SCANNER_LIST.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new SCAN_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		SCAN_LIST_BedienPanel oPanel = new SCAN_LIST_BedienPanel(oNaviList);
		
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	}
		
}
