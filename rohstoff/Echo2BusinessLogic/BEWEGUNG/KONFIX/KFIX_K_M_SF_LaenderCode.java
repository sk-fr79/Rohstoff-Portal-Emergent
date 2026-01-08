package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_SF_LaenderCode extends RB_SelectField {

	public KFIX_K_M_SF_LaenderCode(IF_Field field, int iWidth) throws myException {
		super(field,
				new SEL()
				.ADDFIELD(LAND.laendercode.fieldName(), "A")
				.ADDFIELD(LAND.laendercode.fieldName(), "B")
				.FROM(_TAB.land)
				.ORDERUP(LAND.laendercode).s(),
				false, 
				false
				);
		this.setWidth(new Extent(iWidth));
	}
}
