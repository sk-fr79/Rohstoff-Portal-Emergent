package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_DB_PARAMETER implements IF_enum_4_db{

	ID_USER()
	,ID_MANDANT()
	;
	
	
	private ENUM_DB_PARAMETER() {
		
	}

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return null;
	}

	@Override
	public String user_text_lang() {
		return null;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return null;
	}
	
	
	
	
	
	public MyE2_MessageVector  save_value(String val_in_session) {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		//String sql = "EXEC PD_PACKAGE.set_context_value('"+this.name()+"','"+val_in_session+"')";
		String sql = "begin PD_PACKAGE.set_context_value('"+this.name()+"','"+val_in_session+"'); end;";
		if (!bibDB.ExecSQL(sql, true)) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Fehler beim Schreiben des DB-Parameters ").ut(this.name())));
		}
		
		return mv;
	}
	
	
	public String  read_value() throws myException {
		String ret = null;
		
		String sql = "select SYS_CONTEXT(PD_PACKAGE.GET_CONTEXT_NAME, '"+this.name()+"') from dual";
		String[][] c_ret = bibDB.EinzelAbfrageInArray(sql);
		
		
		if (c_ret==null) {
			throw new myException(S.mt("Fehler bei der Abfrage des DB-Parameters ").ut(this.name()).CTrans());
		} else if (c_ret.length==1) {
			ret = c_ret[0][0];
		}
		return ret;
	}
	

	public String sql_term() {
		return "SYS_CONTEXT(PD_PACKAGE.GET_CONTEXT_NAME, '"+this.name()+"')";
	}
	
	
	
	
}
