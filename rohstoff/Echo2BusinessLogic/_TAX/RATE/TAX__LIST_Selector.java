package rohstoff.Echo2BusinessLogic._TAX.RATE;



import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

public class TAX__LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public TAX__LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		TAX__LIST_Selector_SelActiveOrNot oTaxActiveSelektor = new TAX__LIST_Selector_SelActiveOrNot();
		this.oSelVector.add(oTaxActiveSelektor);
		
		
		MyE2_Grid oGridInnen = new MyE2_Grid(2,0);
		this.add(oGridInnen, E2_INSETS.I(4,4,4,4));
		
		oGridInnen.add(new MyE2_Label("Zeige:"),  E2_INSETS.I(4,2,20,2));
		oGridInnen.add(oTaxActiveSelektor.get_oComponentWithoutText(),  E2_INSETS.I(4,2,15,2));
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
