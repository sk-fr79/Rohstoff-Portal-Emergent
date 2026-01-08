package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class TODO_LIST_ComponentMap extends E2_ComponentMAP 
{

	public TODO_LIST_ComponentMap() throws myException
	{
		super(new TODO_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(TODO_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(TODO_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TODO_WICHTIGKEIT","BESCHREIBUNG","ID_TODO_WICHTIGKEIT","ISTSTANDARD",true);
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_TODO_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));

		this.add_Component(new MyE2_DB_Label(oFM.get_("GENERIERUNGSDATUM")), new MyE2_String("Datum"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ABLAUFDATUM")), new MyE2_String("Deadline"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("AUFGABEKURZ")), new MyE2_String("Aufgabe"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ABSCHLUSSDATUM")), new MyE2_String("Fertig"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ERLEDIGT")), new MyE2_String("Erl."));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_TODO")), new MyE2_String("ID"));
	
		
		
		this.set_oSubQueryAgent(new TODO_LIST_FORMATING_Agent());
	}

}
