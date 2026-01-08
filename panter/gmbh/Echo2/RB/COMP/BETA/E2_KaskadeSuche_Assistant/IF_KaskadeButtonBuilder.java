/**
 * panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant
 * @author sebastien
 * @date 20.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant;

import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author sebastien
 * @date 20.05.2019
 *
 */
public interface IF_KaskadeButtonBuilder {
	public VEK<E2_Button> build_Buttons_In_Grid(E2_KaskadeSelectionSuche oMultiSel, String[] ergebnisZeile, E2_Grid ownSelectGrid) throws myException;
}
