package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LC_LIST_ComponentMap extends E2_ComponentMAP 
{

	public LC_LIST_ComponentMap() throws myException
	{
		super(new LC_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(LC_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LC_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new LC__LIST_CB_AktivAnAus(oFM.get_(_DB.COLUMNS_TO_CALC$ACTIVE)), new MyE2_String("Aktiv"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MODULNAME_LISTE")), new MyE2_String("Listenmodul"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("COLUMN_LABEL")), new MyE2_String("Spalte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NUMBER_DECIMALS")), new MyE2_String("Dezimale"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SUMMATION_VIA_QUERY")), new MyE2_String("Summation via SQL"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.COLUMNS_TO_CALC$SHOW_LINE_IN_LISTHEADER)), new MyE2_String("Zeige Ergebniss in Titelzeile"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TEXT4WINDOWTITLE")), new MyE2_String("Titel des Popupfensters"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TEXT4TITLE_IN_WINDOW")), new MyE2_String("Überschrift im Fenster"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TEXT4SUMMATION")), new MyE2_String("Beschriftung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TOOLTIPS")), new MyE2_String("Tooltips"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VALIDATION_TAG")), new MyE2_String("Berechtigungs- Kennung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_COLUMNS_TO_CALC")), new MyE2_String("ID"));

		GridLayoutData oGLLeft = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,1,1,1));
		GridLayoutData oGLRight = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I(2,1,1,1));
		for (MyE2IF__Component oComp: this.values()) {
			oComp.EXT().set_bLineWrapListHeader(true);
			oComp.EXT().set_oLayout_ListTitelElement(oGLLeft);
		}
		this.get__Comp(_DB.COLUMNS_TO_CALC$ID_COLUMNS_TO_CALC).EXT().set_oLayout_ListTitelElement(oGLRight);
		
		this.set_oSubQueryAgent(new LC_LIST_FORMATING_Agent());
	}

}
