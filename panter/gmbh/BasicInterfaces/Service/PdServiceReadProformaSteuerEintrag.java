/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 02.03.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

/**
 * @author martin
 * @date 02.03.2020
 *
 */
public class PdServiceReadProformaSteuerEintrag {

	/**
	 * @author martin
	 * @date 02.03.2020
	 *
	 */
	public PdServiceReadProformaSteuerEintrag() {
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 02.03.2020
	 *
	 * @return s single existing Rec21 from taxes 
	 */
	public Rec21 getTaxLagerProformaEintrag() {
	
		Rec21 ret = null;
		
		try {
			SEL sel = new SEL(_TAB.tax).FROM(_TAB.tax).WHERE(new vgl(TAX.taxtyp,TAX_CONST.TAXTYP_NULL_LAGERSEITE)).AND(new vgl_YN(TAX.aktiv, true)).AND(new vgl(TAX.steuersatz,"0"));
			
			RecList21 rlTaxes = new RecList21(_TAB.tax)._fill(new SqlStringExtended(sel.s()));
			
			if (rlTaxes.size()==1) {
				return rlTaxes.get(0);
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		return ret;
		
	}
	

}
