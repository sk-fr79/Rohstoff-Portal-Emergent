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
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 19.10.2020
 *
 */
public class Rec22Land extends Rec22 {

	public Rec22Land() throws myException {
		super(_TAB.land);
	}


	public Rec22Land(Rec21 baseRec) throws myException {
		super(baseRec);
		
		if (baseRec.get_tab()!=_TAB.land) {
			throw new myException("Only records of type land are allowed ! <5c74e698-11ed-11eb-adc1-0242ac120002>");
		}
	}


	@Override
	public Rec22Land _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}


	@Override
	public Rec22Land _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec22Land _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec22Land _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}


	@Override
	public Rec22Land _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}


	@Override
	public Rec22Land _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		super._SAVE(b_commit, mv_from_call);
		return this;
	}

	
	@Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Rec22Land)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Rec22Land c = (Rec22Land) o; 
          
        // Compare the data members and return accordingly  
        try {
			return O.NN(c.getIdLong(),1l).equals(O.NN(this.getIdLong(),2l));
		} catch (myException e) {
			e.printStackTrace();
			return false;
		}
    } 
	
	
	public boolean isQualifiedForGelangensbestaetigung() {
		boolean ret = false;
		try {
			if (this.is_yes_db_val(LAND.intrastat_jn) || this.is_yes_db_val(LAND.sonderfall_gelangensbestaet)) {
				ret = true;
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return ret;
		
	}
	
}
