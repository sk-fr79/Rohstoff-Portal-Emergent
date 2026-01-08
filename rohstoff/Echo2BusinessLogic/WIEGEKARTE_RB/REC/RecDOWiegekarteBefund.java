/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.REC
 * @author manfred
 * @date 16.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 01.07.2020
 *
 */
public class RecDOWiegekarteBefund extends RB_Dataobject_V22 {
	private static final _TAB table = _TAB.wiegekarte_befund;
	
	
	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(table.n())._setHASHKEY("RecWiegekarteBefund@5561e1f2-186b-47d9-a78f-0d00635f42b0");

	
	public RecDOWiegekarteBefund( Long id, MASK_STATUS status) throws myException {
		super(table, id, status);
	}

	public RecDOWiegekarteBefund( MASK_STATUS status) throws myException {
		super(table, status);
	}

	public RecDOWiegekarteBefund(Rec22 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status);
		if (baseRec.get_tab()!=table) {
			throw new myException("RecWiegekarteBefund can only by of TABLE " + table.baseTableName() );
		}
	}
	
	@Override
	public RecDOWiegekarteBefund _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	

	@Override
	public RecDOWiegekarteBefund _fill_id(String id) throws myException {
		MyLong lid = new MyLong(id);
		if (lid.get_bOK()) {
			this._fill_id(lid.get_lValue());
		} else {
			throw new myException(this,"Error ID "+id+" is no number !");
		}
		return this;
	}

	
	
	public RecDOWiegekarteBefund _fill_from_wiegekarte( RecDOWiegekarte oWk) throws myException {
		return _fill_from_wiegekarte(oWk.getId());
	}

	
	 
	/**
	 * 
	 * @author manfred
	 * @date 01.07.2020
	 *
	 * @param id
	 * @param pos
	 * @return
	 * @throws myException 
	 */
	public RecDOWiegekarteBefund _fill_from_wiegekarte( long id_wiegekarte_parent) throws myException {
		String sql = 
		   " SELECT * FROM " + bibE2.cTO() + "." + _TAB.wiegekarte_befund.fullTableName() 
		+  " WHERE " + WIEGEKARTE_BEFUND.id_wiegekarte.fieldName() + " = ? "
		;
		
		SqlStringExtended sqlex = new SqlStringExtended(sql)
				._addParameter(new Param_Long(id_wiegekarte_parent));
		super._fill_sql(sqlex);
		
		return this;
	}
	
	
}
