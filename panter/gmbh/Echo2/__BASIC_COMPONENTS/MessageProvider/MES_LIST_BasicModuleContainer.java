package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider.MES_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;



public class MES_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
	public static final String NAME_OF_CHECKBOX_IN_LIST = MES_CONST.MES_NAMES.CHECKBOX_LISTE.db_val();
	public static final String NAME_OF_LISTMARKER_IN_LIST = MES_CONST.MES_NAMES.MARKER_LISTE.db_val();

	public MES_LIST_BasicModuleContainer() throws myException {
		super(TRANSLATOR.LIST.get_modul().get_callKey());

		this.set_bVisible_Row_For_Messages(true);

		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new MES_LIST_ComponentMap(), E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		MES_LIST_BedienPanel oPanel = new MES_LIST_BedienPanel(oNaviList);

		this.add(oPanel, E2_INSETS.I_2_2_2_2);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

		oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	}

}
