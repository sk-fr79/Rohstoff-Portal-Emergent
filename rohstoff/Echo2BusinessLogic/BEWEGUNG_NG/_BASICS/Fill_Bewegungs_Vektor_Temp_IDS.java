package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.tempTables.IF_tmp_table_def;
import panter.gmbh.indep.dataTools.tempTables.TT_LOG_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.dataTools.tempTables.TT_TABLE_LIST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;




/**
 * 
 * @author martin
 * fuellt die tt_log_bewegung_vektor (falls noetig, erzeugt diese auch) mit allen id_bewegung_vektor, start_atom, ziel_atom, rechpos, gutpos, statistik_timestamp
 * wo der statistiktimestamp < letzte_aenderung. anschliessend wird diese tabelle in die jt_bewegung_vektor zurueckuebertragen. 
 */
public class Fill_Bewegungs_Vektor_Temp_IDS {
	
	private String sql_fill_temp = null;
	private String sql_write_to_bewegung_vektor = null;
	private String sql_set_final_updatestamp = null;
	
	
	public Fill_Bewegungs_Vektor_Temp_IDS() throws myException {
		super();

		if (TT_TABLE_LIST.tt_log_bewegung_vektor.build_table(true).is_ok()) {
			this.sql_fill_temp = "INSERT INTO "+TT_TABLE_LIST.tt_log_bewegung_vektor.get_tablename()+"(";
			for (IF_tmp_table_def f: TT_TABLE_LIST.tt_log_bewegung_vektor.get_fields()) {
				this.sql_fill_temp=this.sql_fill_temp+f.get_fieldname()+",";
			}
			this.sql_fill_temp = this.sql_fill_temp.substring(0,this.sql_fill_temp.length()-1)+") ";  //letztes komma raus
			
			this.sql_fill_temp = this.sql_fill_temp+ " SELECT "
							+ BEWEGUNG_VEKTOR.id_bewegung_vektor.t("V").s()
							+",START_ATOM("+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn("V")+") AS ID_START_ATOM,"
							+ "ZIEL_ATOM("+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn("V")+") AS ID_ZIEL_ATOM,"
							+ "RECHPOS("+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn("V")+") AS RECHPOS,"
							+ "GUTPOS("+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn("V")+") AS GUTPOS,"
							+ "SYSDATE AS DATUM FROM "+_TAB.bewegung_vektor.n()+" V "+
						" WHERE "+BEWEGUNG_VEKTOR.letzte_aenderung.fn("V")+">"+BEWEGUNG_VEKTOR.statistik_timestamp.fn("V")+
						" OR "+BEWEGUNG_VEKTOR.statistik_timestamp.fn("V")+" IS NULL";

			
			
			sql_write_to_bewegung_vektor = "UPDATE "+_TAB.bewegung_vektor.n()+" V SET ("	+BEWEGUNG_VEKTOR.id_bewegung_atom_trigstart.fn()
																		+","+BEWEGUNG_VEKTOR.id_bewegung_atom_trigziel.fn()
																		+","+BEWEGUNG_VEKTOR.zahl_rechpos.fn()
																		+","+BEWEGUNG_VEKTOR.zahl_gutpos.fn()
																		+","+BEWEGUNG_VEKTOR.statistik_timestamp.fn()+")="
							 +"(SELECT  "
								+TT_LOG_BEWEGUNG_VEKTOR.ID_ATOM_START.fn("T")+","
								+TT_LOG_BEWEGUNG_VEKTOR.ID_ATOM_ZIEL.fn("T")+","
								+TT_LOG_BEWEGUNG_VEKTOR.RECHPOS.fn("T")+","
								+TT_LOG_BEWEGUNG_VEKTOR.GUTPOS.fn("T")+","
								+TT_LOG_BEWEGUNG_VEKTOR.DATUM.fn("T")
								+ " FROM "+TT_TABLE_LIST.tt_log_bewegung_vektor.get_tablename()+" T "
								+ " WHERE "+TT_LOG_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR.fn("T")+"="+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn("V")+")"
								+" WHERE EXISTS (SELECT 1 FROM "+TT_TABLE_LIST.tt_log_bewegung_vektor.get_tablename()+" T "
								+"  WHERE "+TT_LOG_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR.fn("T")+"="+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn("V")+")";

			
			//zum schluss alle korrektur-timestamps der betroffenen datensaetze auf sysdate+2 sekunden setzen, damit der statistik_timestamp auf jeden fall neuer als der automatische trigger-timestamp ist 
			sql_set_final_updatestamp = "UPDATE "+_TAB.bewegung_vektor.n()+" SET "+BEWEGUNG_VEKTOR.statistik_timestamp+"=SYSDATE+NUMTODSINTERVAL(2,'SECOND') WHERE "+_TAB.bewegung_vektor.keyFieldName()+" IN "
										+"("
										+"SELECT  "+TT_LOG_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR.fn()+" FROM "+TT_TABLE_LIST.tt_log_bewegung_vektor.get_tablename()
										+")";
								
//			DEBUG.System_print(bibVECTOR.get_Vector(this.sql_fill_temp,this.sql_write_to_bewegung_vektor,sql_set_final_updatestamp),"  update-stack   ");
			
		} else {
			throw new myException("Error refreshing statistics ...");
		}
		
	}

	
	public MyE2_MessageVector  do_update() {
		MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
		
		mv_rueck.add_MESSAGE(bibALL.get_oTransactionLock().make_Lock(bibALL.get_oConnectionNormal()));
		Vector<Integer> v_anzahl = new Vector<>();
		

		boolean do_commit = false;
		
		if (mv_rueck.get_bIsOK()) {
			
			mv_rueck.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibVECTOR.get_Vector(this.sql_fill_temp),false));
			if (mv_rueck.get_bIsOK()) {
				mv_rueck.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibVECTOR.get_Vector(this.sql_write_to_bewegung_vektor),false, v_anzahl,null));
				if (mv_rueck.get_bIsOK()) {
					mv_rueck.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibVECTOR.get_Vector(this.sql_set_final_updatestamp),false));
					if (mv_rueck.get_bIsOK()) {
						do_commit=true;
						if (mv_rueck.get_bIsOK() && v_anzahl.get(0).intValue()>0) {         //sonst war nichts upzudaten
							String zahl = "<ERROR>";
							zahl = ""+v_anzahl.get(0).intValue();
							if (bibALL.is_developer()) {
								mv_rueck.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Anzahl Statistik-Updates: ").ut(zahl)));
							}
						}
					}
				}
			}
			
		}

		
		if (do_commit) {
			bibDB.Commit();
		} else {
			bibDB.Rollback();
		}
		
		return mv_rueck;
	}
	
	
}



