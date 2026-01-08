/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 22.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 22.03.2019
 *
 */
public interface IF_FieldInfo_Component  {
	public Component get_component(Rec21 r) throws myException;
}
