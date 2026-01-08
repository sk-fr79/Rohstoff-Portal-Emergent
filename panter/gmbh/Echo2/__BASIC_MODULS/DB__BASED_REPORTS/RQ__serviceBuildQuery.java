/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS
 * @author martin
 * @date 30.10.2018
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_CONST.RQ_NAMES;
import panter.gmbh.basics4project.myCONST_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 30.10.2018
 *
 */
public class RQ__serviceBuildQuery {
	
	/**
	 * baut einen sqlstring mit allen parameter-ersetzungen auf
	 * @author martin
	 * @date 30.10.2018
	 *
	 * @param record
	 * @param hmParametersValues (ohne $$ vorn und hinten)
	 * @return
	 * @throws myException 
	 */
	public String getSqlSelectBlock(Rec21 record, HashMap<String, String> hmParametersValues) throws myException {
		
		String ret = null;
		
		if (record!=null && record.is_ExistingRecord() && hmParametersValues!=null) {
			
			StringBuffer sbAll = new StringBuffer()	
					.append(S.NN(record.getUfs(REPORTING_QUERY.query1)))
					.append(S.NN(record.getUfs(REPORTING_QUERY.query2)))
					.append(S.NN(record.getUfs(REPORTING_QUERY.query3)))
					.append(S.NN(record.getUfs(REPORTING_QUERY.query4)));

			String sql = sbAll.toString();
			
			String indexCol = "SEQ_"+myCONST_ENUM.NUMMERNKREISE_GLOBAL.TR_TEMPIDS.name()+".NEXTVAL AS ID_"+record.getUfs(REPORTING_QUERY.realname_temptable).substring(3);
			
			sql = sql.replaceAll(RQ_CONST.RQ_NAMES.INDEX_PLACEHOLDER.db_val(), indexCol);
			
			//jetzt noch das sonderfeld fuer den Sessioncode/usercode/timestamp einfuegen
			sql = sql.replaceFirst("SELECT ", 
									"SELECT "+RQ_CONST.getSESCode()+" AS "+RQ_NAMES.SESSIONFIELDNAME.db_val()
									+","+RQ_CONST.getUserCode()+" AS "+RQ_NAMES.USERIDFIELDNAME.db_val()
									+","+RQ_CONST.getTimeStamp()+" AS "+RQ_NAMES.TIMESTAMPFIELDNAME.db_val()
									+",");

			RecList21 rl = record.get_down_reclist21(REPORTING_QUERY_PARAM.id_reporting_query);
			
			for (Rec21 r: rl) {
				String paramName = r.getUfs(REPORTING_QUERY_PARAM.paramkey);
				if (S.isFull(hmParametersValues.get(paramName))) {
					String paramNameExt = RQ_NAMES.TAG_FOR_FIELDS_REGEX.db_val()+paramName+RQ_NAMES.TAG_FOR_FIELDS_REGEX.db_val();
					sql=sql.replaceAll(paramNameExt, hmParametersValues.get(paramName));
					//DEBUG._print(sql);
				} else {
					throw new myException("Parameter "+paramName+" is in the recordlist, but not in the hashMap with values !!");
				}
			}
			ret = sql;
		}
		
		return ret;
	}

	
	private boolean isHelpTableIsExisting(Rec21 record) throws myException {
		boolean exists = false;
		String sql = DB_META.get_TablesQuery(DB_META.DB_ORA, bibE2.cTO(), true, false);
		String[][] ret = bibDB.EinzelAbfrageInArray(sql,"");
		
		if (!(ret==null || ret.length==0 || S.isEmpty(ret[0][0]))) {
			for (int i=0;i<ret.length;i++) {
				if (ret[i][0].equals(record.getUfs(REPORTING_QUERY.realname_temptable))) {
					exists=true;
				}
			}
		}
		return exists;
	}
	
	
	
	
	
	
	public MyE2_MessageVector  fillOrCreateAndFillHelpTable(Rec21 record, String queryBase) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		String sql = "";
		if (isHelpTableIsExisting(record)) {
			Vector<String> v = new Vector<>();
			v.add("DELETE FROM "+record.getUfs(REPORTING_QUERY.realname_temptable)+
					" WHERE ("+RQ_NAMES.USERIDFIELDNAME.db_val()+"="+RQ_CONST.getUserCode()+
					" OR "+RQ_NAMES.SESSIONFIELDNAME.db_val()+"="+RQ_CONST.getSESCode()+")");
			v.add("INSERT INTO "+record.getUfs(REPORTING_QUERY.realname_temptable)+"  "+queryBase);
			mv.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(v, true));
			
		} else {
			sql = "CREATE TABLE "+record.getUfs(REPORTING_QUERY.realname_temptable)+" AS "+queryBase;
			if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sql, true)) {
				mv._addAlarm(S.ms("Fehler beim Ausführen des SQL-Statements ...").ut(sql));
			}
		}
		return mv;
	}
	
	
	public Long getNumberOfRows(Rec21 record) throws myException {
		
		String val = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+record.getUfs(REPORTING_QUERY.realname_temptable)); 
		
		MyLong mL = new MyLong(val);
		if (mL.isNotOK()) {
			throw new myException(this,"Error: cannot read the number of the result-amount ");
		}
		
		return mL.get_oLong();
	}
	
	
	
	public MyE2_MessageVector dropTableIfExisting(Rec21 rec) {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		try {
			if (rec != null && rec.is_ExistingRecord() && rec.getUfs(REPORTING_QUERY.realname_temptable).startsWith("TR_"))	 {
				if (isHelpTableIsExisting(rec)) {
					if (bibDB.ExecSQL("DROP TABLE "+rec.getUfs(REPORTING_QUERY.realname_temptable),true)) {
						mv._addInfo(S.ms("Tabelle ").ut(rec.getUfs(REPORTING_QUERY.realname_temptable)).t(" erfolgreich gelöscht"));
					} else {
						mv._addAlarm(S.ms("Tabelle ").ut(rec.getUfs(REPORTING_QUERY.realname_temptable)).t(" konnte nicht gelöscht werden"));
					}
				}
			} else {
				mv._addAlarm(S.ms("Systemfehler: Tabelle  konnte nicht gelöscht werden"));
			}
		} catch (myException e) {
			e.printStackTrace();
			mv._addAlarmUT(e.ErrorMessage);
		}

		return mv;
	}
	
}
