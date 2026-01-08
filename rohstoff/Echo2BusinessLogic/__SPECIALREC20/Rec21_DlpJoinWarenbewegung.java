/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 27.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.09.2019
 *
 */
public class Rec21_DlpJoinWarenbewegung  extends Rec21 {

	public Rec21_DlpJoinWarenbewegung() throws myException {
		super(_TAB.dlp_join_warenbewg);
	}

	public Rec21_DlpJoinWarenbewegung(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	public Rec21_DlpJoinWarenbewegung _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	

}
