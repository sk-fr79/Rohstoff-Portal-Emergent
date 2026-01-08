/**
 * rohstoff.businesslogic.bewegung2.list.listSelector
 * @author sebastien
 * @date 11.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class B2_listSelector_haendler extends E2_ListSelectorMultiDropDown2{

	private static String[] keineBetreuerWert = {"<kein Betreuer>","-1"};
	
	public B2_listSelector_haendler() throws myException {
		super();
		
		
		B2_SelectField selField = new B2_SelectField();
		selField._fo_s_plus(-2);
		
		USER_SELECTOR_GENERATOR  oSelUsers = new USER_SELECTOR_GENERATOR(false);
		
		String[][] o =  bibARR.add_array_inFront(oSelUsers.get_AktiveBenutzer(false, null), new String[][] {keineBetreuerWert});
		
		String[][] cUSER = bibARR.add_emtpy_db_value_inFront(o);
		
		selField.set_ListenInhalt(cUSER, false);

		String bed = BG_VEKTOR.erzeugt_von.tnfn() + "= (SELECT "+USER.kuerzel.fn() 
				+ " FROM " + _TAB.user.fullTableName() +  
				" WHERE ID_MANDANT=" + bibALL.get_ID_MANDANT() + " AND ID_USER=#WERT#)" ;
		
		this.INIT(selField, bed, null);
	}


	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}

	private class ownBasicContainer extends E2_BasicModuleContainer{}
	
}
