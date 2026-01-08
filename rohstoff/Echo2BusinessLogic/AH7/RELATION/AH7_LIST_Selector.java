package rohstoff.Echo2BusinessLogic.AH7.RELATION;


import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class AH7_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public AH7_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		AH7_LIST_Selector_Status 				selStatus = 		new AH7_LIST_Selector_Status();
		AH7_LIST_Selector_SperreAnAus 			selSperre = 		new AH7_LIST_Selector_SperreAnAus();
		AH7_LIST_Selector_WA_WE_LL_ST   		selTypen = 			new AH7_LIST_Selector_WA_WE_LL_ST();
		AH7_LIST_Selector_MultiSelectProfil     selProfile = 		new AH7_LIST_Selector_MultiSelectProfil();
		
		
		this.oSelVector.add(selStatus);
		this.oSelVector.add(selSperre);
		this.oSelVector.add(selTypen);
		this.oSelVector.add(selProfile);

		E2_Grid grid = new E2_Grid();
		RB_gld ld = new RB_gld()._left_top()._ins(1);
		RB_gld ld2 = new RB_gld()._left_top()._ins(1)._span_r(2);
		
		grid._setSize(170,190,170,150)
			._a(selStatus.get_vCheckBoxTypen().get(0),ld)	._a(selSperre.get_vCheckBoxTypen().get(0),ld)	._a(selTypen.get_vCheckBoxTypen().get(0))._a(selProfile.get_oComponentForSelection(),ld2)
			._a(selStatus.get_vCheckBoxTypen().get(1),ld)	._a(selSperre.get_vCheckBoxTypen().get(1),ld)	._a(selTypen.get_vCheckBoxTypen().get(1))
			._a(selStatus.get_vCheckBoxTypen().get(2),ld)	._a("",ld)										._a(selTypen.get_vCheckBoxTypen().get(2))._a("",ld)
			._a("",ld)										._a("",ld)										._a(selTypen.get_vCheckBoxTypen().get(3))._a("",ld)
			;
		
		this.add(grid, E2_INSETS.I_4_4_4_4);

	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
