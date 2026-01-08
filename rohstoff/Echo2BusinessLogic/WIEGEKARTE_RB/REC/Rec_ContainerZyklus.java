package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_STATION;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_ZYKLUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

public class Rec_ContainerZyklus extends Rec22 {

	public Rec_ContainerZyklus(_TAB p_tab) throws myException {
		super(_TAB.container_zyklus);
		
	}

	public Rec_ContainerZyklus(Rec21 baseRec) throws myException {
		super(baseRec);
		
	}

	public Rec_ContainerZyklus(Rec22 baseRec) throws myException {
		super(baseRec);
	}

	@Override
	public Rec22 _fill(Rec21 baseRec) throws myException {
		return super._fill(baseRec);
	}

	@Override
	public Rec22 _fill(Rec22 baseRec) throws myException {
		return super._fill(baseRec);
	}

	@Override
	public Rec22 _fill_id(String id) throws myException {
		return super._fill_id(id);
	}

	@Override
	public Rec22 _fill_id(long id) throws myException {
		return super._fill_id(id);
	}

	@Override
	public Rec22 _fill_sql(String sql) throws myException {
		return super._fill_sql(sql);
	}

	@Override
	public Rec22 _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		return super._fill_sql(sqlStringExtended);
	}

	
	
	public RecList22 getReclistContainerStationen() throws myException {
		RecList22 rlRet =  this.get_down_reclist22(CONTAINER_STATION.id_container_zyklus, "", CONTAINER_STATION.id_container_station.fn() +  " desc ");
		return rlRet;
	}
	
	
//	public RecList22 getReclistContainerStationenAktiv() throws myException {
//		String sWhere = "NVL(" +  CONTAINER_ZYKLUS.abgeschlossen.fn() + ",'N') = 'N'";
//		RecList22 rlRet =  this.get_down_reclist22(CONTAINER_STATION.id_container_zyklus, sWhere, CONTAINER_STATION.id_container_station.fn() +  " desc ");
//		return rlRet;	
//	}
	
	
	
	
	
	

}
