package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class CHANGELOG__LIST_ComponentMap extends E2_ComponentMAP 
{

	public CHANGELOG__LIST_ComponentMap() throws myException
	{
		super(new CHANGELOG__LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(CHANGELOG__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(CHANGELOG__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("BESCHREIBUNG")), new MyE2_String("BESCHREIBUNG"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("COLNAME")), new MyE2_String("COLNAME"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ERZEUGT_AM")), new MyE2_String("ERZEUGT_AM"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ERZEUGT_VON")), new MyE2_String("ERZEUGT_VON"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_CHANGELOG")), new MyE2_String("ID_CHANGELOG"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_TABLE")), new MyE2_String("ID_TABLE"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("NEW_VALUE")), new MyE2_String("NEW_VALUE"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("OLD_VALUE")), new MyE2_String("OLD_VALUE"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("TABLENAME")), new MyE2_String("TABLENAME"));
		


		this.set_oSubQueryAgent(new CHANGELOG__LIST_FORMATING_Agent());
	}

}
