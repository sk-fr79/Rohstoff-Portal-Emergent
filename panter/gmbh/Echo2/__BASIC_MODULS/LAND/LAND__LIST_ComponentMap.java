package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NaviListCol_Formater;
import panter.gmbh.Echo2.ListAndMask.List.ZusatzFelder.ADD_ZusatzFelder;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAND__LIST_ComponentMap extends E2_ComponentMAP 
{

	public LAND__LIST_ComponentMap(String cMODULE_KENNER_LAND_LIST) throws myException
	{
		super(new LAND__LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(LAND__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LAND__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$LAENDERCODE)), new MyE2_String("Länder- code"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(LAND.iso_country_code.fn())), new MyE2_String("ISO- code"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$UST_PRAEFIX)), new MyE2_String("UST-Prefix"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$LAENDERNAME)), new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$BESCHREIBUNG)), new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$LAENDERVORWAHL)), new MyE2_String("Länder- vorwahl"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$POST_CODE)), new MyE2_String("Post- Code"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$HAT_POSTCODE)), new MyE2_String("Hat Postcode"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$INTRASTAT_JN)), new MyE2_String("EU"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAND.sonderfall_gelangensbestaet.fn())), new MyE2_String("Gelangens. Best. Sonderfall"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$GENERELLE_EINFUHR_NOTI)), new MyE2_String("Einfuhr - Noti"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$ISTSTANDARD)), new MyE2_String("Std."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$ANZEIGEREIHENFOLGE)), new MyE2_String("Reih."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.LAND$ID_LAND)), new MyE2_String("ID"));

		
		E2_NaviListCol_Formater.STANDARD_LEFT(100).Format(this.get__Comp(_DB.LAND$ID_LAND));
		E2_NaviListCol_Formater.STANDARD_LEFT(100).Format(this.get__Comp(LAND.iso_country_code.fn()));
		E2_NaviListCol_Formater.STANDARD_LEFT(100).Format(this.get__Comp(_DB.LAND$UST_PRAEFIX));
		E2_NaviListCol_Formater.STANDARD_LEFT(150).Format(this.get__Comp(_DB.LAND$LAENDERNAME));
		E2_NaviListCol_Formater.STANDARD_LEFT(300).Format(this.get__Comp(_DB.LAND$BESCHREIBUNG));
		E2_NaviListCol_Formater.STANDARD_LEFT(60).Format(this.get__Comp(_DB.LAND$LAENDERVORWAHL));
		E2_NaviListCol_Formater.STANDARD_LEFT(60).Format(this.get__Comp(_DB.LAND$POST_CODE));
		E2_NaviListCol_Formater.STANDARD_LEFT(60).Format(this.get__Comp(LAND.sonderfall_gelangensbestaet.fn()));
		E2_NaviListCol_Formater.STANDARD_LEFT(60).Format(this.get__Comp(_DB.LAND$HAT_POSTCODE));
		E2_NaviListCol_Formater.STANDARD_CENTER(40).Format(this.get__Comp(_DB.LAND$INTRASTAT_JN));
		E2_NaviListCol_Formater.STANDARD_CENTER(40).Format(this.get__Comp(_DB.LAND$GENERELLE_EINFUHR_NOTI));
		E2_NaviListCol_Formater.STANDARD_CENTER(40).Format(this.get__Comp(_DB.LAND$ISTSTANDARD));
		E2_NaviListCol_Formater.STANDARD_CENTER(70).Format(this.get__Comp(_DB.LAND$ANZEIGEREIHENFOLGE));
		E2_NaviListCol_Formater.STANDARD_RIGHT(60).Format(this.get__Comp(_DB.LAND$ID_LAND));
			
		
		new ADD_ZusatzFelder(this, cMODULE_KENNER_LAND_LIST);
		
		this.set_oSubQueryAgent(new LAND__LIST_FORMATING_Agent());
	}

}
