package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_LIST_Selector extends E2_ExpandableRow
{

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */

	private E2_SelectionComponentsVector     oSelVector = null;

	public DRUCK_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(oNavigationList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE());
		DRUCK_LIST_Selektor_Aktiv ownSelektor = new DRUCK_LIST_Selektor_Aktiv(); 

		this.oSelVector.add(ownSelektor);
		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		oGridInnen.add(ownSelektor.get_oComponentForSelection());
		oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), new Insets(4,2,10,2));
		
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);

	}
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
}

