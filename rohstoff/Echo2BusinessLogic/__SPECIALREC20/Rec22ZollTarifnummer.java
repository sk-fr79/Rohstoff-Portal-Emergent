/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 19.10.2020
 *
 */
public class Rec22ZollTarifnummer extends Rec22 {

	public Rec22ZollTarifnummer() throws myException {
		super(_TAB.zolltarifnummer);
	}


	public Rec22ZollTarifnummer(Rec21 baseRec) throws myException {
		super(baseRec);
		
		if (baseRec.get_tab()!=_TAB.zolltarifnummer) {
			throw new myException("Only records of type zolltarifnummer are allowed ! <5c74e698-134d-11eb-adc1-0242ac120002>");
		}
	}


	@Override
	public Rec22ZollTarifnummer _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}


	@Override
	public Rec22ZollTarifnummer _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec22ZollTarifnummer _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec22ZollTarifnummer _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}


	@Override
	public Rec22ZollTarifnummer _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}


	@Override
	public Rec22ZollTarifnummer _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		super._SAVE(b_commit, mv_from_call);
		return this;
	}

	
	public RecList22 getSortenWithThisZolltarifnummer() throws myException {
		return this.get_down_reclist22(ARTIKEL.id_zolltarifnummer);
	}

	public RecList22 getSortenWithThisZolltarifnummer(String orderString) throws myException {
		return this.get_down_reclist22(ARTIKEL.id_zolltarifnummer,null,orderString,true);
	}

	public RecList22 getSortenWithThisZolltarifnummer(IF_Field orderField) throws myException {
		return this.get_down_reclist22(ARTIKEL.id_zolltarifnummer,null,orderField.tnfn()+" ASC",true);
	}


	
	
	
	
}
