package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;


import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class LC_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public LC_LIST_Selector(E2_NavigationList oNavigationList, MyE2_SelectField oSelField) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		
		this.oSelVector.add(new E2_ListSelectorStandard(
						oSelField, 
						_DB.COLUMNS_TO_CALC+"."+_DB.COLUMNS_TO_CALC$MODULNAME_LISTE, 
						new MyE2_String(""), 0));
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
