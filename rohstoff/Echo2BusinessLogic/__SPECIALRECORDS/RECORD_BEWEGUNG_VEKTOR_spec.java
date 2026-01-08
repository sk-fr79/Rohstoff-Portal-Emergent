package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class RECORD_BEWEGUNG_VEKTOR_spec extends RECORD_BEWEGUNG_VEKTOR {

	public RECORD_BEWEGUNG_VEKTOR_spec(RECORD_BEWEGUNG_VEKTOR recordOrig) {
		super(recordOrig);
	}

	
	
	public RECORD_BEWEGUNG_ATOM[] get_FirstAndLastAtom() throws myException {
		RECLIST_BEWEGUNG_ATOM   rlATOM = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor(
				"NVL("+_DB.BEWEGUNG_ATOM$DELETED+",'N')='N'", _DB.BEWEGUNG_ATOM$DELETED, true);
		
		RECORD_BEWEGUNG_ATOM[] baRueck = new RECORD_BEWEGUNG_ATOM[2];
		
		for (int i=0;i<rlATOM.get_vKeyValues().size();i++) {
			if (i==0) {
				baRueck[0]=rlATOM.get(i);
			}
			
			if (i==(rlATOM.get_vKeyValues().size()-1)) {
				baRueck[1]=rlATOM.get(i);
			}
		}
		
		return baRueck;
	}
	
	
	
//	public MyE2_MessageVector  update_start_and_end_atom_id() throws myException {
//		MyE2_MessageVector  mv = new MyE2_MessageVector();
//		
//		MyLong l_start = new MyLong(bibDB.EinzelAbfrage("SELECT START_ATOM("+this.ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
//		MyLong l_ziel = new MyLong(bibDB.EinzelAbfrage("SELECT ZIEL_ATOM("+this.ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
//		MyLong l_zahl_rechpos = new MyLong(bibDB.EinzelAbfrage("SELECT RECHPOS("+this.ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
//		MyLong l_zahl_gutpos  = new MyLong(bibDB.EinzelAbfrage("SELECT GUTPOS("+this.ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
//		
//		if (l_start.get_bOK() && l_ziel.get_bOK()) {
//		
//		String sql = "UPDATE "+bibE2.cTO()+"."+BEWEGUNG_VEKTOR.fullTabName()+" SET "
//						+BEWEGUNG_VEKTOR.id_bewegung_atom_trigstart.fn()+"="+l_start.get_cUF_LongString()+","
//						+BEWEGUNG_VEKTOR.id_bewegung_atom_trigziel.fn()+"="+l_ziel.get_cUF_LongString()+","
//						+BEWEGUNG_VEKTOR.zahl_rechpos.fn()+"="+l_zahl_rechpos.get_cUF_LongString()+","
//						+BEWEGUNG_VEKTOR.zahl_gutpos.fn()+"="+l_zahl_gutpos.get_cUF_LongString()+","
//						+BEWEGUNG_VEKTOR.statistik_timestamp.fn()+"=SYSDATE+NUMTODSINTERVAL(2,'SECOND')"+
//						" WHERE "+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn()+"="+this.ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor);
//		
//			mv.add_MESSAGE(bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(sql),true));
//		} else {
//			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Speichern war erfolgreich, Satzstatististik fehlerhaft !!!")));
//		}
//
//		return mv;
//	}
//	
//	
//	public MyE2_MessageVector  update_endPreis() throws myException {
//		MyE2_MessageVector  mv = new MyE2_MessageVector();
//		
//		this.REBUILD();
//		
//		RECLIST_BEWEGUNG_ATOM  rl_ba = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor();
//		
//		for (RECORD_BEWEGUNG_ATOM ra: rl_ba) {
//			RECORD_BEWEGUNG_ATOM_ext  rae = new RECORD_BEWEGUNG_ATOM_ext(ra);
//			mv.add_MESSAGE(rae.schreibe_preise());
//		}
//		
//		return mv;
//
//	}
	
	
}
