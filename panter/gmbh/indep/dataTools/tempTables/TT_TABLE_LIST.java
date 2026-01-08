package panter.gmbh.indep.dataTools.tempTables;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

/**
 * enum stellt die verbindung zwischen einer tabelle und einer field-list her
 * @author martin
 *
 */
public enum TT_TABLE_LIST implements IF_tmp_tables_generator {
	
	tt_log_bewegung_vektor(TT_LOG_BEWEGUNG_VEKTOR.values(), bibVECTOR.get_Vector("create index TT_LOG_BEWEGUNG_VEKTOR_idx1 on TT_LOG_BEWEGUNG_VEKTOR (ID_BEWEGUNG_VEKTOR)"))
	;

	
	private IF_tmp_table_def[] 	f_fields = null;
	private String      		f_tablename = null;
	private Vector<String>      v_statements_addon = null;
	
	private TT_TABLE_LIST(IF_tmp_table_def[] p_fields,  Vector<String> p_statements_addon) {
		this.f_fields = p_fields;
		this.f_tablename = this.name().toUpperCase();
		this.v_statements_addon = p_statements_addon;
	}
	
	public IF_tmp_table_def[] get_fields() {
		return f_fields;
	}
	
	public String get_tablename() {
		return f_tablename;
	}

	
	
	@Override
	public boolean check_if_present() throws myException {
		String sql_check = "SELECT COUNT(*) FROM USER_TABLES WHERE TABLE_NAME='"+this.get_tablename()+"'";
		
		MyLong l_result = new MyLong(bibDB.EinzelAbfrage(sql_check));
		
		if (l_result.get_bOK()) {
			if (l_result.get_iValue()==1) {
				return true; 
			} else if (l_result.get_iValue()==0) {
				return false;
			}
		}

		throw new myException("Error: "+this.name()+" table can not be checked !");
	}

	@Override
	public TT_GENERATE_RESULT_VALUES build_table(boolean b_commit) throws myException {

		if (!this.check_if_present()) {
			String sql_create = "CREATE GLOBAL TEMPORARY TABLE "+this.get_tablename()+" ("; 
			
			for (IF_tmp_table_def f: this.f_fields) {
				sql_create = sql_create+" "+f.get_fieldname()+" "+f.get_field_def()+",";
			}
			sql_create = sql_create.substring(0,sql_create.length()-1)+") ON COMMIT DELETE ROWS";
			Vector<String> v_sql = new Vector<>();
			v_sql.add(sql_create);
			if (this.v_statements_addon!=null) {
				v_sql.addAll(this.v_statements_addon);
			}
			
			MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(v_sql, b_commit);
			
			if (mv.get_bIsOK()) {
				return TT_GENERATE_RESULT_VALUES.CREATE_OK;
			} else {
				return TT_GENERATE_RESULT_VALUES.CREATE_FAILED;
			}
			
		} else {
			return TT_GENERATE_RESULT_VALUES.TABLE_WAS_THERE;
		}
		
	}



	
	
	
}
