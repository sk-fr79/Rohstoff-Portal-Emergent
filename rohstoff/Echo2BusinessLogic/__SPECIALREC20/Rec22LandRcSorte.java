/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 19.10.2020
 *
 */
public class Rec22LandRcSorte extends Rec22 {

	public Rec22LandRcSorte() throws myException {
		super(_TAB.land_rc_sorten);
	}


	public Rec22LandRcSorte(Rec21 baseRec) throws myException {
		super(baseRec);
		
		if (baseRec.get_tab()!=_TAB.land_rc_sorten) {
			throw new myException("Only records of type land_rc_sorten are allowed ! <5c74e698-11ed-11eb-adc1-0245ac120002>");
		}
	}


	@Override
	public Rec22LandRcSorte _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}


	@Override
	public Rec22LandRcSorte _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec22LandRcSorte _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec22LandRcSorte _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}


	@Override
	public Rec22LandRcSorte _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}


	@Override
	public Rec22LandRcSorte _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		super._SAVE(b_commit, mv_from_call);
		return this;
	}

	
	public Rec22Land getLand() throws myException {
		return new Rec22Land(this.get_up_Rec22(LAND.id_land));
	}
	
}
