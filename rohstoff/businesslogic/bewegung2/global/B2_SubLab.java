/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 24.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.S;

/**
 * @author martin
 * @date 24.01.2019
 *
 */
public class B2_SubLab extends RB_lab {

	public B2_SubLab(String text) {
		super(S.ms(text));
		this._i()._fsa(-2);
		
		this._set_ld(new RB_gld()._ins(2,5,1,2));
		
	}
	
}
