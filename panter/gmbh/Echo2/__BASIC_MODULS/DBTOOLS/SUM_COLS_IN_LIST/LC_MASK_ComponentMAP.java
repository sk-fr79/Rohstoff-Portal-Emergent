package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class LC_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public LC_MASK_ComponentMAP() throws myException
	{
		super(new LC_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_DropDownSettingsNew  oDD = new E2_DropDownSettingsNew(
				"SELECT MODULEKENNER,MODULEKENNER AS KENN2  FROM "+bibE2.cTO()+".JT_FIELD_RULE_MODULEKENNER WHERE MODULTYP='LIST' ORDER BY MODULEKENNER",
				true, false);

		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_ComboBoxErsatz(oFM.get_("MODULNAME_LISTE"),oDD.getDD(),false,500), new MyE2_String("Listenmodul"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLUMN_LABEL"),true,500,100,false), new MyE2_String("Spalte"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMBER_DECIMALS"),true,60,3,false), new MyE2_String("Dezimalstellen"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SUMMATION_VIA_QUERY")), new MyE2_String("Summation via SQL"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.COLUMNS_TO_CALC$SHOW_LINE_IN_LISTHEADER)), new MyE2_String("Zeige Ergebniss in Titelzeile"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.COLUMNS_TO_CALC$ACTIVE)), new MyE2_String("Aktiv"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TEXT4SUMMATION"),true,500,100,false), new MyE2_String("Beschriftung"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TEXT4TITLE_IN_WINDOW"),true,500,100,false), new MyE2_String("Überschrift im Fenster"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TEXT4WINDOWTITLE"),true,500,100,false), new MyE2_String("Titel des Popupfensters"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("TOOLTIPS"),500,8), new MyE2_String("Tooltips"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("VALIDATION_TAG"),true,200,20,false), new MyE2_String("Berechtigungs- Kennung"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_COLUMNS_TO_CALC"),true,200,10,false), new MyE2_String("ID"));
		
		this.set_oMAPSettingAgent(new LC_MASK_MapSettingAgent());
		
		this.set_oSubQueryAgent(new LC_MASK_FORMATING_Agent());
	}
	
}
