/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * die 4 globalen profile-typen (allgemeine typen)
 */
public class Reclist21_AH7P_GlobalProfiles extends RecList21 {

	/**
	 * @param p_tab
	 * @throws myException 
	 */
	public Reclist21_AH7P_GlobalProfiles() throws myException {
		super(_TAB.ah7_profil);
		SEL  selSonder = 	new SEL(_TAB.ah7_profil).FROM(_TAB.ah7_profil).WHERE(new vgl(AH7_PROFIL.profile4allothers,COMP.NOT_EQ,AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val())).ORDERUP(AH7_PROFIL.bezeichnung);

		this._fill(selSonder.s());
		
	}

}
