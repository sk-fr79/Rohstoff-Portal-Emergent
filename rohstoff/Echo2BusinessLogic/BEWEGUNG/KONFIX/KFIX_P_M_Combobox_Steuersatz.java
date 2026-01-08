package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_ComboBoxErsatz;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_Combobox_Steuersatz extends RB_ComboBoxErsatz{

	public KFIX_P_M_Combobox_Steuersatz(IF_Field cIfField) throws myException {

		super(cIfField, 
				new SEL(MWSTSCHLUESSEL.steuersatz)
				.FROM(_TAB.mwstschluessel)
				.ORDERUP(MWSTSCHLUESSEL.steuersatz)
				.s()
				, true);
		
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(80),null, new Integer(60));
		this.get_oTextField().setFont(new E2_FontPlain(-2));
	}

}
