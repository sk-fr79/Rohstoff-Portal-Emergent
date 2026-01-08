package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class LC_LIST_BedienPanel extends MyE2_Column 
{
	
	private E2_SelectionComponentsVector 	oSelVector = null;

	public LC_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		E2_DropDownSettingsNew  oDD = new E2_DropDownSettingsNew(
				"SELECT MODULEKENNER,MODULEKENNER AS KENN2  FROM "+bibE2.cTO()+".JT_FIELD_RULE_MODULEKENNER WHERE MODULTYP='LIST' ORDER BY MODULEKENNER",
				true, false);

		MyE2_SelectField  oSelField = new MyE2_SelectField(oDD.getDD(), "", false, new Extent(200));

		
		this.oSelVector = new E2_SelectionComponentsVector(oNaviList);
		this.oSelVector.add(new E2_ListSelectorStandard(
						oSelField, 
						"UPPER("+_DB.COLUMNS_TO_CALC+"."+_DB.COLUMNS_TO_CALC$MODULNAME_LISTE+")=UPPER('#WERT#')", 
						new MyE2_String(""), 0));

		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new LC_LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LC_LIST_BT_COPY(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LC_LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LC_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LC_LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_COLS_TO_CALC_DEF_LIST), new Insets(20,2,2,2));
		oRowForComponents.add(new E2_ButtonToCreate_SQL_ExportStatements(oNaviList, new E2_ButtonAUTH_ONLY_ADMIN()), new Insets(20,2,2,2));

		oRowForComponents.add(new LC_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));

		//die einzige selektorkomponente anhaengen
		oRowForComponents.add(oSelField, new Insets(20,2,2,2));
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
}
