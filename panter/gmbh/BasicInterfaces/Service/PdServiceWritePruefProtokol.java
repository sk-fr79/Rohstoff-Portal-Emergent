/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 19.05.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.Date;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.EnPruefungTyp;
import panter.gmbh.basics4project.DB_ENUMS.BG_PRUEFPROT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 19.05.2020
 *
 */
public class PdServiceWritePruefProtokol {

	/**
	 * @author martin
	 * @date 19.05.2020
	 *
	 */
	public PdServiceWritePruefProtokol() {
	}

	
	public Rec21 getNewPruefprotRec(EnPruefungTyp pruefTyp, _TAB table, Long idTable) {
		try {
			Rec21 r = new Rec21(_TAB.bg_pruefprot);
			
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			r._nv(BG_PRUEFPROT.id_user, bibALL.get_ID_USER(), mv);
			r._setNewVal(BG_PRUEFPROT.pruefung_am, new Date(), mv);
			r._setNewVal(BG_PRUEFPROT.base_tablename, table.baseTableName(), mv);
			r._setNewVal(BG_PRUEFPROT.id_base_table, idTable, mv);
			r._setNewVal(BG_PRUEFPROT.en_pruefung_typ, pruefTyp.dbVal(), mv);
			r._SaveCleanRebuild(true, mv);
			return r;
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
