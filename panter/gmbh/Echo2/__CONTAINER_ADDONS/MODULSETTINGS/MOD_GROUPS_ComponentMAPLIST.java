package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MOD_GROUPS_ComponentMAPLIST extends E2_ComponentMAP
{

	public MOD_GROUPS_ComponentMAPLIST(String cMODULE_KENNER_LIST_BELONGS_TO) throws myException
	{
		super(new MOD_GROUPS_SQLFieldMAP(cMODULE_KENNER_LIST_BELONGS_TO));
		
		SQLFieldMAP oSQLFields = this.get_oSQLFieldMAP();
		
		this.add_Component("CHECK_BOX",new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(new MyE2_DB_Label(oSQLFields.get_("MODULE_KENNER")), new MyE2_String("Modul"));
		this.add_Component(new MyE2_DB_TextField(oSQLFields.get_("MENUETEXT")), new MyE2_String("Button-Text"));
		this.add_Component(new MyE2_DB_TextField(oSQLFields.get_("BUTTONKENNER")), new MyE2_String("Button-Kenner"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFields.get_("BESCHREIBUNG")), new MyE2_String("Beschreibung"));
		
		((MyE2IF__Component)this.get("MODULE_KENNER")).EXT().set_oColExtent(new Extent(120));
		((MyE2IF__Component)this.get("MENUETEXT")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("BUTTONKENNER")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("BESCHREIBUNG")).EXT().set_oColExtent(new Extent(250));
		
		((MyE2_DB_Label)this.get("MODULE_KENNER")).setFont(new E2_FontItalic(-2));
		
		((MyE2_DB_TextField)this.get("MENUETEXT")).set_iWidthPixel(180);
		((MyE2_DB_TextField)this.get("BUTTONKENNER")).set_iWidthPixel(180);
		((MyE2_DB_TextArea)this.get("BESCHREIBUNG")).set_iWidthPixel(200);
		((MyE2_DB_TextArea)this.get("BESCHREIBUNG")).set_iRows(4);
		((MyE2_DB_TextArea)this.get("BESCHREIBUNG")).setFont(new E2_FontPlain(-2));
		
	}

}
