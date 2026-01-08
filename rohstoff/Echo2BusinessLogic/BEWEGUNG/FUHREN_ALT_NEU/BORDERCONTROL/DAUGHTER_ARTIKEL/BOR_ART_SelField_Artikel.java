/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL
 * @author manfred
 * @date 13.06.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 13.06.2018
 *
 */
public class BOR_ART_SelField_Artikel extends RB_SelectField {

	/**
	 * @author manfred
	 * @date 13.06.2018
	 *
	 * @param field
	 * @param oExt
	 * @throws myException
	 */
	public BOR_ART_SelField_Artikel(IF_Field field, int width) throws myException {
		super(field, (new SEL()
				.ADDFIELD(ARTIKEL.anr1.fieldName() + " ||' '|| " + ARTIKEL.artbez1 )
				.ADDFIELD(ARTIKEL.id_artikel.fieldName())
				.FROM(_TAB.artikel)
				.ORDERUP(ARTIKEL.anr1).s()),
				false, 
				false, 
				new Extent(width));
	}



}
