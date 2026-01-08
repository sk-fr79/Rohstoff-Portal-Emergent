/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 16.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 16.09.2019
 *
 */
public class RecList21_DlpProfile extends RecList21 {

	/**
	 * @author martin
	 * @date 16.09.2019
	 *
	 * @param p_tab
	 */
	public RecList21_DlpProfile() {
		super(_TAB.dlp_profil);
	}

	@Override
	public RecList21_DlpProfile _fill(SqlStringExtended completeSqlExt) throws myException {
		this.execute_query(completeSqlExt);
		return this;
	}

	
	public VEK<Long>  getIdsDlpProfil() throws myException {
		return this.getVEKLong( r-> {return r.getIdLong();});
	}
	
}
