package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__LIST_ComponentMap extends E2_ComponentMAP 
{

	public MODUL__LIST_ComponentMap() throws myException
	{
		super(new MODUL__LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		MyDropDownSettings  oDDHauptmenue = new MyDropDownSettings(bibE2.cTO(),"JD_HAUPTMENUE","MENUETXT","ID_HAUPTMENUE",null,null,true,"MENUETXT");
		MyE2_DB_SelectField oSelFieldHM = new MyE2_DB_SelectField(oFM.get_("ID_HAUPTMENUE"),oDDHauptmenue.getDD(),false);
		oSelFieldHM.EXT().set_oColExtent(new Extent(250));
		
		
		this.add_Component(MODUL__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(MODUL__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_SERVLETS")), new MyE2_String("ID"),true,true);
		this.add_Component(oSelFieldHM, new MyE2_String("Hauptmenü"),true,true);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENUEEINTRAG")), new MyE2_String("Eintrag im Menü"),true,true);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BESCHREIBUNG")), new MyE2_String("Beschreibung"),true,true);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SERVLETAUFRUF")), new MyE2_String("Aufruf"),true,true);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SORTNUMMER")), new MyE2_String("Sortierung"),true,true);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TABTEXT")), new MyE2_String("Text auf Programmtab"),false,true);

		this.set_oSubQueryAgent(new MODUL__LIST_FORMATING_Agent());
	}

}
