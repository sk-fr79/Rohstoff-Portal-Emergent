package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class USER__LIST_ComponentMap extends E2_ComponentMAP 
{

	public USER__LIST_ComponentMap() throws myException
	{
		super(new USER__LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(USER__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(USER__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_USER")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VORNAME")), new MyE2_String("Vorname"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")), new MyE2_String("Name 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME2")), new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME")), new MyE2_String("Benutzername"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME_INFO")), new MyE2_String("Mandant"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EMAIL")), new MyE2_String("Mail-Adresse"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("aktiv"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_FAHRER")), new MyE2_String("Fahrer"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_SUPERVISOR")), new MyE2_String("SUPER"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KUERZEL")), new MyE2_String("KUERZEL"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("HAT_FAHRPLAN_BUTTON")), new MyE2_String("FP"));
		

		this.set_oSubQueryAgent(new USER__LIST_FORMATING_Agent());
	}

}
