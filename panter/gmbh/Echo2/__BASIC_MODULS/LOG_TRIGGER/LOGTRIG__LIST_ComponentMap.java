package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__LIST_ComponentMap extends E2_ComponentMAP 
{

	public LOGTRIG__LIST_ComponentMap() throws myException
	{
		super(new LOGTRIG__LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(LOGTRIG__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LOGTRIG__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_TRIGGER_DEF")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TABELLE")), new MyE2_String("Tabelle"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SPALTE")), new MyE2_String("Spalte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TRIGG_NAME")), new MyE2_String("Triggername"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BESCHREIBUNG")), new MyE2_String("Eintrag"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT)), new MyE2_String("Vordef."));

		this.set_oSubQueryAgent(new LOGTRIG__LIST_FORMATING_Agent());
	}

}
