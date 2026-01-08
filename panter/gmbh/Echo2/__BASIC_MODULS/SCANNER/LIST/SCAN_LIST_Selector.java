package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;


import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public SCAN_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		SCAN_LIST_Selector_ModuleKenner selModKenner = new SCAN_LIST_Selector_ModuleKenner();
		SCAN_LIST_Selector_Programmkenner selProgKenner = new SCAN_LIST_Selector_Programmkenner();
		SCAN_LIST_Selector_Auflosung selAuflosung = new SCAN_LIST_Selector_Auflosung();
		SCAN_LIST_Selector_Filetype selFiletype = new SCAN_LIST_Selector_Filetype();
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(oNavigationList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE());
		
		this.oSelVector.add(selModKenner);
		this.oSelVector.add(selProgKenner);
		this.oSelVector.add(selFiletype);
		this.oSelVector.add(selAuflosung);
		this.oSelVector.add(oDB_BasedSelektor);
		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen);
		oGridInnen.add(selModKenner.get_oComponentForSelection(),  E2_INSETS.I(4,4,10,4));
		oGridInnen.add(selProgKenner.get_oComponentForSelection(),  E2_INSETS.I(4,4,10,4));
		oGridInnen.add(selFiletype.get_oComponentForSelection(),  E2_INSETS.I(4,4,10,4));
		oGridInnen.add(selAuflosung.get_oComponentForSelection(),  E2_INSETS.I(4,4,10,4));
		oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(),  E2_INSETS.I(4,4,10,4));
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
}
