package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class Mandatory_DB_Object_OracleSysContextHandlerPackage extends Mandatory_DB_Object_ORACLE {

	private String context_name = null;
	private String package_name = null;
	
	public Mandatory_DB_Object_OracleSysContextHandlerPackage() {
		super("PD_PACKAGE");
		
		this.context_name = this.getObjectName()+"_CTX";
		this.package_name = this.getObjectName();
	}

	@Override
	public boolean ObjectExists() {
		if (this.Object_ContextExists() &&  this.Object_PackageExists() && this.Object_PackageBodyExists()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean ObjectIsValid() throws myException {
		if (this.Object_ContextValid() && this.Object_PackageValid() && this.Object_PackageBodyValid()) {
			return true;
		}

		return false;
	}

	
	@Override
	public boolean CreateObject() {
		
		String sql_1 = "CREATE OR REPLACE CONTEXT "+this.context_name+" USING "+this.package_name;
		
		String sql_2 = "CREATE OR REPLACE PACKAGE "+this.package_name+" "+"\n"+
						"IS "+"\n"+
						"common_ctx_name   CONSTANT VARCHAR2 (60) "+"\n"+
		                ":= '"+this.context_name+"'; "+"\n"+
		                "\n"+
		   				"  FUNCTION   get_context_name   RETURN VARCHAR2; "+"\n"+
		   				"  PROCEDURE  set_context_value (var_name VARCHAR2, var_value VARCHAR2); "+"\n"+
		   				"END;"+"\n";
//		   				"/"+"\n";			// der slash ist notwendig !!
		
		String sql_3 = 	"CREATE OR REPLACE PACKAGE BODY "+this.package_name+" "+"\n"+
						"IS"+"\n"+
						"  FUNCTION get_context_name"+"\n"+
						"     RETURN VARCHAR2"+"\n"+
						"  IS"+"\n"+
						"  BEGIN"+"\n"+
						"     RETURN common_ctx_name;"+"\n"+
						"  END;"+"\n"+
						"\n"+
			  			" PROCEDURE set_context_value (var_name VARCHAR2, var_value VARCHAR2)"+"\n"+
			  			" IS"+"\n"+
			  			" BEGIN"+"\n"+
			  			"    DBMS_SESSION.set_context (common_ctx_name, var_name, var_value);"+"\n"+
			  			" END;"+"\n"+
		  			"END;"
		  			;
		
//		DEBUG.System_println(sql_1, true);
//		DEBUG.System_println(sql_2, true);
//		DEBUG.System_println(sql_3, true);
		
		Vector<String>  v_sql = bibVECTOR.get_Vector(sql_1,sql_2,sql_3);
		

		MyE2_MessageVector mv = new MyE2_MessageVector();
		try {
			if (!this.ObjectExists() || !this.ObjectIsValid()) {
				mv.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(v_sql, true));
			}
		} catch (myException e) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Fehler beim erstellen des packages")));
			e.printStackTrace();
		}
		
		return mv.get_bIsOK();
	}

	public String get_Context_name() {
		return context_name;
	}

	public String get_Package_name() {
		return package_name;
	}

}
