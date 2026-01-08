package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.BUTTON_MODULVERWALTUNG;
import panter.gmbh.indep.exceptions.myException;

public class TODO_LIST_BasicModuleContainer extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public TODO_LIST_BasicModuleContainer() throws myException
	{
		super();
		this.add_AddonComponent(new BUTTON_MODULVERWALTUNG(this));

		this.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_TODO_LIST);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new TODO_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		TODO_LIST_BedienPanel oPanel = new TODO_LIST_BedienPanel(oNaviList,new TODO_MASK_BasicModuleContainer(),this);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oTODO_LIST_Selector().get_oSelVector().doActionPassiv();

		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(500),new MyE2_String("Aufgaben verwalten ..."));
		
	}
		
}
