/**
 * rohstoff.Echo2BusinessLogic.CONTAINER
 * @author manfred
 * @date 05.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 05.12.2017
 *
 */
public class CON_SelField_ContainterTyp extends RB_SelectField {

	/**
	 * @author manfred
	 * @date 05.12.2017
	 *
	 * @param field
	 * @param oExt
	 * @throws myException
	 */
	public CON_SelField_ContainterTyp(IF_Field field,  int iWidth) throws myException {
		super(field, 
				(new SEL()
				.ADDFIELD(CONTAINERTYP.kurzbezeichnung.fieldName())
				.ADDFIELD(CONTAINERTYP.id_containertyp.fieldName())
				.FROM(_TAB.containertyp)
				.ORDERUP(CONTAINERTYP.kurzbezeichnung).s()),
				false, 
				false, 
				new Extent(iWidth));
	}



}
