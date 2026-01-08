/**
 * rohstoff.businesslogic.bewegung2.list.listSelector
 * @author sebastien
 * @date 04.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;


public class B2_listSelector_BewegungArt extends E2_ListSelectorMultiDropDown2{

	public B2_listSelector_BewegungArt() throws myException{
		super();
		
		B2_SelectField selField = new B2_SelectField(
				EnTransportTyp.STRECKE.getArray4Selfield4Mask(true), 
				"-", false);
		
		selField._fo_s_plus(-2);
		
		And bedingung = new And(new vgl(BG_VEKTOR.en_transport_typ,"#WERT#"));
		
		this.INIT(selField, bedingung.s(),null);
	}
	
	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public Component get_oComponentForSelection() throws myException {

			return this.get_oComponentWithoutText();
	}
	
	

	private class ownBasicContainer extends E2_BasicModuleContainer {}
}
