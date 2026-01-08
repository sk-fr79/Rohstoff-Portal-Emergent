/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 14.09.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecWatch;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 14.09.2020
 *
 */
public class Rec21_LagerPalette extends Rec21 {

	/**
	 * @author martin
	 * @date 14.09.2020
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_LagerPalette() throws myException {
		super(_TAB.lager_palette);
	}

	/**
	 * @author martin
	 * @date 14.09.2020
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_LagerPalette(Rec21 baseRec) throws myException {
		super(baseRec);
		
		if (baseRec.get_tab() != _TAB.lager_palette) {
			throw new myException(this,"Only Record from type LAGER_PALETTE are allowed !");
		}
	}

	
	
	public boolean isAusgebucht() throws myException  {
		return this.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand) || S.isFull(this.getUfs(LAGER_PALETTE.id_vpos_tpa_fuhre_aus));
	}


	@Override
	public Rec21_LagerPalette _fill(Rec21 baseRec) throws myException {
		return (Rec21_LagerPalette)super._fill(baseRec);
	}

	@Override
	public Rec21_LagerPalette _fill_id(String id) throws myException {
		return (Rec21_LagerPalette)super._fill_id(id);
	}

	@Override
	public Rec21_LagerPalette _fill_id(long id) throws myException {
		return (Rec21_LagerPalette)super._fill_id(id);
	}

	@Override
	public Rec21_LagerPalette _fill_sql(String sql) throws myException {
		return (Rec21_LagerPalette)super._fill_sql(sql);
	}

	@Override
	public Rec21_LagerPalette _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		return (Rec21_LagerPalette)super._fill_sql(sqlStringExtended);
	}

	@Override
	public Rec21_LagerPalette _fill(MyRECORD_IF_RECORDS rec) throws myException {
		return (Rec21_LagerPalette)super._fill(rec);
	}

	@Override
	public Rec21_LagerPalette _rebuildRecord() throws myException {
		return (Rec21_LagerPalette)super._rebuildRecord();
	}

	@Override
	public Rec21_LagerPalette _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		return (Rec21_LagerPalette)super._SAVE(b_commit, mv_from_call);
	}

	@Override
	public Rec21_LagerPalette _setNewVal(String fieldName, Object value, MyE2_MessageVector mv) throws myException {
		return (Rec21_LagerPalette)super._setNewVal(fieldName, value, mv);
	}

	@Override
	public Rec21_LagerPalette _setNewVal(IF_Field field, Object value, MyE2_MessageVector mv) throws myException {
		return (Rec21_LagerPalette)super._setNewVal(field, value, mv);
	}

	@Override
	public Rec21_LagerPalette _nv(IF_Field f, String formated_value, MyE2_MessageVector mv) throws myException {
		return (Rec21_LagerPalette)super._nv(f, formated_value, mv);
	}

	@Override
	public Rec21_LagerPalette _addRecWatch(RecWatch rw) {
		return (Rec21_LagerPalette)super._addRecWatch(rw);
	}

	@Override
	public Rec21_LagerPalette _addRecWatch(VEK<RecWatch> rws) {
		return (Rec21_LagerPalette)super._addRecWatch(rws);
	}
	
	
	
	
	
}
