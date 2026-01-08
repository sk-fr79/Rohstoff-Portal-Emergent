package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MANDANT_LIST_ComponentMap extends E2_ComponentMAP 
{

	public MANDANT_LIST_ComponentMap() throws myException
	{
		super(new MANDANT_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(MANDANT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(MANDANT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_MANDANT")), new MyE2_String("ID_MANDANT"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EIGENE_ADRESS_ID")), new MyE2_String("Eigene Adress-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LANDKURZ")), new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")), new MyE2_String("Name 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME2")), new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")), new MyE2_String("Ort"));
		
		this.set_oSubQueryAgent(new MANDANT_LIST_FORMATING_Agent());
	}

}
