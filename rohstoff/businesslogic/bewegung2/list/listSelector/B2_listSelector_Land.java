package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_listSelector_Land extends E2_ListSelectorMultiDropDown2 {

	public B2_listSelector_Land(EnTabKeyInMask p_enStationPos) throws myException {
		super();		
		
		B2_SelectField selField = new B2_SelectField(
				new SEL().ADDFIELD(LAND.beschreibung).ADDFIELD(LAND.id_land).FROM(_TAB.land).s()
				,false, true, false, false
				);
		selField._fo_s_plus(-2);
		
		And bedingung = new And();
		
		if(p_enStationPos == EnTabKeyInMask.S1) {
			bedingung.and( "S1.id_land", COMP.EQ, "#WERT#");
		}else if(p_enStationPos == EnTabKeyInMask.S3) {
			bedingung.and( "S3.id_land", COMP.EQ, "#WERT#");
		}
		
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
