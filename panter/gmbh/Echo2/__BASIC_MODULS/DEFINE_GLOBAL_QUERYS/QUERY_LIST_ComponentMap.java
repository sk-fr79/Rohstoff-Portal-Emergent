package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_LIST_ComponentMap extends E2_ComponentMAP 
{

	public QUERY_LIST_ComponentMap() throws myException
	{
		super(new QUERY_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(QUERY_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,	new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(QUERY_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		this.add_Component(new MyE2_DB_Label(oFM.get_("NAME")), 		new MyE2_String("Name"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("BESCHREIB1")), 	new MyE2_String("Beschreibung 1"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("DOWNLOAD")), 	new MyE2_String("Down?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_QUERY")), 	new MyE2_String("ID"));

		this.set_oSubQueryAgent(new QUERY_LIST_FORMATING_Agent());
	}

}
