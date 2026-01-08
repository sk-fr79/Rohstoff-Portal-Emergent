package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorPosHelperTop;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

public class FZ_LIST_SEL_VektorTyp extends E2_ListSelectorMultiDropDown2 {

	private MyE2_SelectField  			selField = 			new MyE2_SelectField(bibENUM.dd_array(ENUM_VEKTOR_TYP.values(), true), "", true);
	private HashMap<String, String>  	hmWertToBedingung = new HashMap<String, String>();
	
	public FZ_LIST_SEL_VektorTyp() throws myException {
		super();
		
		for (ENUM_VEKTOR_TYP stat: ENUM_VEKTOR_TYP.values()) {
			this.hmWertToBedingung.put(stat.db_val(), this.genSel4Bed(stat.db_val()).s());
		}
		
		this.INIT(selField, null, hmWertToBedingung);
		this.set_extOfSelectComponentDropDown(new Extent(200));

	}
	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}		
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}


	private And genSel4Bed(String variante) throws myException {
		return new And(new vgl(BEWEGUNG_VEKTOR.variante, variante));
	}
	
	@Override
	public Component get_oComponentForSelection() throws myException {
		//return new E2_ListSelectorPosHelperTop("Bewegung-Typ", this.get_oComponentWithoutText(), 100, 200);
		return this.get_oComponentWithoutText();
	}

}