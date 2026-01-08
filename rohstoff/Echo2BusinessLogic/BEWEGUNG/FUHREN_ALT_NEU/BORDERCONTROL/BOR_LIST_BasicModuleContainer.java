
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.BOR_CONST.TRANSLATOR;

public class BOR_LIST_BasicModuleContainer extends Project_BasicModuleContainer {

	public static final String NAME_OF_CHECKBOX_IN_LIST = BOR_CONST.BOR_NAMES.CHECKBOX_LISTE.db_val();
	public static final String NAME_OF_LISTMARKER_IN_LIST = BOR_CONST.BOR_NAMES.MARKER_LISTE.db_val();
	public static final String NAME_OF_ARTIKEL_COL_IN_LIST = BOR_CONST.BOR_NAMES.ARTIKEL_LISTE.db_val();
	

	public BOR_LIST_BasicModuleContainer() throws myException {
		super(TRANSLATOR.LIST.get_modul().get_callKey());

		this.set_bVisible_Row_For_Messages(true);

		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new BOR_LIST_ComponentMap(), E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		BOR_LIST_BedienPanel oPanel = new BOR_LIST_BedienPanel(oNaviList);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

		oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	}

}
