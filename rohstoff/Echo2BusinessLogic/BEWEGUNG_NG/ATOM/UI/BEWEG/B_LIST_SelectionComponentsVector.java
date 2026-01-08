package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class B_LIST_SelectionComponentsVector extends E2_SelectionComponentsVector {

	public B_LIST_SelectionComponentsVector(E2_NavigationList onavigationList) 	throws myException {
		super(onavigationList);
	}

	
	public MyE2_Grid get_oGridWithSelectors() {
		MyE2_Grid oGridRueck = new MyE2_Grid(1);
		
		return oGridRueck;
	}
	
}
