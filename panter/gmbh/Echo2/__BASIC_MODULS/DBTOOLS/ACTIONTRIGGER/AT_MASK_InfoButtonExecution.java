package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import java.util.Vector;

import calledByName.triggerExecuter.ENUM_TRIGGER_EXECUTER;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HelpButton;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class AT_MASK_InfoButtonExecution extends RB_HelpButton {

	
	private VEK<String>  v_help_text = new VEK<>();
	
	/**
	 * 
	 */
	public AT_MASK_InfoButtonExecution() {
		super();
		this.set_oPopupWidth(new Extent(420)).set_oPopupHeight(new Extent(500));
	}

	@Override
	public E2_BasicModuleContainer generate_E2_BasicModuleContainer() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public E2_Grid generate_InfoGrid() throws myException {
		
		AT_MaskController controller = new AT_MaskController(this._find_componentMapCollector_i_belong_to());
		
		String actual_valid_class = controller.get_maskVal(new RB_KM(_TAB.trigger_action_def), TRIGGER_ACTION_DEF.execution_class);
		
		ENUM_TRIGGER_EXECUTER  executer = ENUM_TRIGGER_EXECUTER.find_TRIGGER_EXECUTER(actual_valid_class);
		
		String info = "<Fehler beim Laden>";
		
		if (executer!=null) {
			info = executer.get_informationBlock();
		}
		Vector<String> zeilen = new Vector<>();
		zeilen.addAll(bibTEXT.get_ZeilenAusTextblock(info, true, false));
		
		E2_Grid g = new E2_Grid()._w100()._s(1);
		g._a(S.mt("Informationen zur Ausführung der Triggeraktion:"), new RB_gld()._col(new E2_ColorDDDark())._ins(E2_INSETS.I(2,2,2,5)));
		
		int i_row = 0;
		
		for (String c: zeilen) {
			if (S.isEmpty(c.trim())) {
				g._a(c, new RB_gld()._ins(E2_INSETS.I(2,1,2,1)));
				g._setRowH(i_row, 20);
				i_row++;
			} else {
				g._a(c, new RB_gld()._ins(E2_INSETS.I(2,1,2,1)));
				i_row++;
			}
		}
		
		return g;
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}


	public VEK<String> get_v_help_text() {
		return v_help_text;
	}
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(true);
	}

}
 