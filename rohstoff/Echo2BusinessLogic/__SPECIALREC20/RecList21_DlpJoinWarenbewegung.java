/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 16.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.DLP_JOIN_WARENBEWG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 16.09.2019
 *
 */
public class RecList21_DlpJoinWarenbewegung extends RecList21 {

	/**
	 * @author martin
	 * @date 16.09.2019
	 *
	 * @param p_tab
	 */
	public RecList21_DlpJoinWarenbewegung() {
		super(_TAB.dlp_join_warenbewg);
	}

	@Override
	public RecList21_DlpJoinWarenbewegung _fill(SqlStringExtended completeSqlExt) throws myException {
		this.execute_query(completeSqlExt);
		return this;
	}

	public VEK<Long>  getIdsDlpProfil() throws myException {
		return this.getVEKLong( r-> {return r.getLongDbValue(DLP_JOIN_WARENBEWG.id_dlp_profil);});
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 01.10.2019
	 *
	 * @param idDlpProfil
	 * @return true, wenn der join-datensatz bereits eine dienstleistungfuhre enthaelt, sonst false. Null im Fehlerfall
	 */
	public Boolean isUnDeletedDienstleistungsFuhreZugeordnet(Long idDlpProfil) {
		Boolean ret = null;
		
		try {
			for (Rec21 r: this) {
				
				if (r.getLongDbValue(DLP_JOIN_WARENBEWG.id_dlp_profil).longValue()==idDlpProfil.longValue()) {
					if (r.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl)!=null) {
						if (r.get_up_Rec21(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, true).is_no_db_val(VPOS_TPA_FUHRE.deleted)) {
							ret = true;
						} else {
							ret = false;
						}
					} else {
						ret = false;
					}
					break;
					
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		return ret;
	}
	
	
	public Boolean isActive(Long idDlpProfile) {
		Boolean ret = null;
		
		try {
			for (Rec21 r: this) {
				if (r.getLongDbValue(DLP_JOIN_WARENBEWG.id_dlp_profil).longValue()==idDlpProfile.longValue()) {
					if (r.is_yes_db_val(DLP_JOIN_WARENBEWG.aktiv)) {
						ret = true;
					} else {
						ret = false;
					}
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 01.10.2019
	 *
	 * @param idDlpProfil
	 * @return true, wenn der join-datensatz bereits eine dienstleistungfuhre enthaelt, sonst false. Null im Fehlerfall
	 */
	public Boolean isInactive(Long idDlpProfil) {
		Boolean ret = null;
		
		try {
			for (Rec21 r: this) {
				
				if (r.getLongDbValue(DLP_JOIN_WARENBEWG.id_dlp_profil).longValue()==idDlpProfil.longValue()) {
					if (r.is_no_db_val(DLP_JOIN_WARENBEWG.aktiv)) {
						ret = true;
					} else {
						ret = false;
					}
					break;
					
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		return ret;
	}
	
	
	public Rec21_DlpJoinWarenbewegung getRec21JoinWarenbeweg(Long idDlpProfile) {
		Rec21_DlpJoinWarenbewegung ret = null;
		
		try {
			for (Rec21 r: this) {
				if (r.getLongDbValue(DLP_JOIN_WARENBEWG.id_dlp_profil).longValue()==idDlpProfile.longValue()) {
					ret = new Rec21_DlpJoinWarenbewegung()._fill_id(r.getId());
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return ret;
	}
	

}
