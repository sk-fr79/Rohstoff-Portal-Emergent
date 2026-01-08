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
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 16.03.2020
 *
 */
public class RecWaegung2_old extends RB_Dataobject_V22 {
	private static final _TAB table = _TAB.waegung;
	
	public static final int waegungPos = 2;
	
	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(table.n())._setHASHKEY("RecWaegung2@a0f2b7d4-3d69-41ea-848b-5a1a78eceb5e");

	
	public RecWaegung2_old( Long id, MASK_STATUS status) throws myException {
		super(table, id, status);
	}

	public RecWaegung2_old( MASK_STATUS status) throws myException {
		super(table, status);
	}

	public RecWaegung2_old(Rec22 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status);
		if (baseRec.get_tab()!=table) {
			throw new myException("RecWaegung2 can only by of TABLE " + table.baseTableName() );
		}
	}
	
	@Override
	public RecWaegung2_old _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	public RecWaegung2_old _fill_from_wiegekarte( RecDOWiegekarte oWk) throws myException {
		return _fill_from_wiegekarte(oWk.getId());
	}

	
	/**
	 * 
	 * @author manfred
	 * @date 12.03.2020
	 *
	 * @param id
	 * @param pos
	 * @return
	 * @throws myException 
	 */
	public RecWaegung2_old _fill_from_wiegekarte( long id) throws myException {
		String sql = 
		   " SELECT * FROM " + bibE2.cTO() + "." + _TAB.waegung.fullTableName() 
		+  " WHERE " + WAEGUNG.id_wiegekarte.fieldName() + " = ? "
		+  " and "   + WAEGUNG.waegung_pos.fieldName() + " = ? " 
		+  " and NVL(" + WAEGUNG.storno.fieldName() + ",'N') = 'N'"
		;
		
		SqlStringExtended sqlex = new SqlStringExtended(sql)
				._addParameter(new Param_Long(id))
				._addParameter(new Param_Long(waegungPos));
		super._fill_sql(sqlex);
		
		return this;
	}
	
	
}
