/**
 * 
 */
package panter.gmbh.Echo2.RB.IF;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface IF_generate_RB_PSEUDO_FIELDKEY {
	public RB_KF getPseudoFieldKey(_TAB table) throws myException;
}
