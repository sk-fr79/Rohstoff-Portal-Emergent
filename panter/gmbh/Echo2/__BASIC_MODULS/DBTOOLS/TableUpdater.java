/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS
 * @author martin
 * @date 30.10.2019
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 30.10.2019
 *
 */
public class TableUpdater {

	
	/**
	 * @author martin
	 * @date 30.10.2019
	 *
	 */
	public TableUpdater() {
	}
	
	
	public enum loggingFields {
		ERZEUGT_VON("DATE", 				"UPDATE #TABLE# SET ERZEUGT_VON=GEAENDERT_VON WHERE ERZEUGT_VON IS NULL AND GEAENDERT_VON IS NOT NULL")
		,ERZEUGT_AM("NVARCHAR2(10)", 		"UPDATE #TABLE# SET ERZEUGT_AM=LETZTE_AENDERUNG WHERE ERZEUGT_AM IS NULL AND LETZTE_AENDERUNG IS NOT NULL")
		,LETZTE_AENDERUNG("DATE", 			null)
		,GEAENDERT_VON("NVARCHAR2(10)", 	null)
		,SYS_TRIGGER_UUID("NVARCHAR2(40)", 	null)
		,SYS_TRIGGER_TIMESTAMP("DATE", 		null)		
		,SYS_TRIGGER_VERSION("NUMBER(10)", 	"UPDATE #TABLE# SET SYS_TRIGGER_VERSION=0 where SYS_TRIGGER_VERSION IS NULL")     //2020-04-09: version-field
		;
		private String fieldDef = null;
		private String correctorStatement = null;
		
		private loggingFields(String p_fieldDef, String p_corrector) {
			this.fieldDef = p_fieldDef;
			this.correctorStatement = p_corrector;
		}
		
		public String getRawAlterTableStatement(String tableName) {
			return "ALTER TABLE "+bibE2.cTO()+"."+ tableName+" ADD ("+this.name()+" "+this.fieldDef+")";
		}
		
		public String getRawUpdateStatement(String tableName) {
			return bibALL.ReplaceTeilString(correctorStatement, "#TABLE#", tableName);
		}
		
		
	}

	
	protected String getTriggerCreateCode() {

		StringBuilder oTrigg = new StringBuilder();
		oTrigg.append("CREATE OR REPLACE TRIGGER trigg_##WERT## \n");
		oTrigg.append("BEFORE INSERT OR UPDATE OR DELETE \n");
		oTrigg.append("ON ##TABELLE## \n");
		oTrigg.append("FOR EACH ROW \n");
		oTrigg.append("DECLARE \n");
	    oTrigg.append("ora_sess        NVARCHAR2(100); \n");
		oTrigg.append("BEGIN \n");
		oTrigg.append("IF INSERTING THEN \n");
		oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:NEW.ID_##WERT##,'##TABELLE##','INSERT',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
		oTrigg.append(":NEW.ERZEUGT_VON:=:NEW.GEAENDERT_VON; \n");
		oTrigg.append(":NEW.ERZEUGT_AM:=:NEW.LETZTE_AENDERUNG; \n");

		//2019-10-30-neue felder
		oTrigg.append(":NEW.sys_trigger_uuid:=TO_CHAR(''||sys_guid()); \n");
		oTrigg.append(":NEW.sys_trigger_timestamp:=SYSDATE; \n");

		//2020-04-09: locking gegen jpql-locking
		//sys_trigger_version nur aktivieren, wenn keine groessere version von oben kommt
		oTrigg.append("IF (NVL(:NEW.sys_trigger_version,-1)=-1 OR NVL(:NEW.sys_trigger_version,-1) = NVL(:OLD.sys_trigger_version,-1)) THEN \n");
		oTrigg.append(" :NEW.sys_trigger_version:= NVL(:OLD.sys_trigger_version,0)+1; \n"); 
		oTrigg.append("END IF; \n");  

		
		oTrigg.append("ELSIF UPDATING THEN \n");
		oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:OLD.ID_##WERT##,'##TABELLE##','UPDATE',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");

		//2019-10-30-neue felder
		oTrigg.append(":NEW.sys_trigger_uuid:=TO_CHAR(''||sys_guid()); \n");
		oTrigg.append(":NEW.sys_trigger_timestamp:=SYSDATE; \n");

		//2020-04-09: locking gegen jpql-locking
		//sys_trigger_version nur aktivieren, wenn keine groessere version von oben kommt
		oTrigg.append("IF (NVL(:NEW.sys_trigger_version,-1)=-1 OR NVL(:NEW.sys_trigger_version,-1) = NVL(:OLD.sys_trigger_version,-1)) THEN \n");
		oTrigg.append(" :NEW.sys_trigger_version:= NVL(:OLD.sys_trigger_version,0)+1; \n"); 
		oTrigg.append("END IF; \n");  

		
		oTrigg.append("ELSIF DELETING THEN \n");
		oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:OLD.ID_##WERT##,'##TABELLE##','DELETE',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
		oTrigg.append("END IF; \n");
		oTrigg.append("END; \n");

		
		return oTrigg.toString();
	}

	/**
	 * 
	 * @author martin
	 * @date 29.10.2019
	 *
	 * @param fullTableName = z,B,JT_ADRESSE
	 * @return
	 */
	public String getTriggerCreateCode(String fullTableName) {
		
		String s_raw = getTriggerCreateCode();
		
		s_raw = s_raw.replace("##WERT##", fullTableName.toUpperCase().substring(3));
		s_raw = s_raw.replace("##TABELLE##", fullTableName.toUpperCase());
		
		return s_raw;
		
	}
	
	

	public PAIR<VEK<String>,VEK<String>> tableUpdate(String fullTableName) {
		VEK<String> meldungenOk = new VEK<>();
		VEK<String> meldungenError = new VEK<>();
		
		
		try {
			VEK<String>  vsql = new TableUpdater().getRawUpdateStatementsForNeededFields(fullTableName);
			
			//2020-04-09: hier alle tabellentrigger vorher disablen, hinterher enablen (falls aenderungen erforderlich sind)
			if (vsql.size()>0) {
				vsql._addInFront("ALTER TABLE "+fullTableName+" DISABLE ALL TRIGGERS");
				vsql._a("ALTER TABLE "+fullTableName+" ENABLE ALL TRIGGERS");
			}
			// 2020-04-09: hier alle tabellentrigger disablen, hinterher enablen
			
			if (bibDB.execRawStatements(vsql, true)) {
				meldungenOk._a("Tableupdates Tabelle "+fullTableName+" OK");
			} else {
				meldungenError._a("Tableupdates Tabelle "+fullTableName+" FEHLER!!");
			}

			if (bibDB.execRawStatements(new VEK<String>()._a(getTriggerCreateCode(fullTableName)), true)) {
				meldungenOk._a("<Creating Trigger "+fullTableName+" OK>");							
			} else	{
				meldungenError._a("<Creating Trigger "+fullTableName+" ERROR>");							
			}
		} catch (myException e) {
			meldungenError._a("Error updating table "+fullTableName+": "+e.ErrorMessage);
			e.printStackTrace();
		}
	
		return new PAIR<VEK<String>,VEK<String>>()._setVal1(meldungenOk)._setVal2(meldungenError);
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 30.10.2019
	 *
	 * @param fullTableName
	 * @return stack of statements for adding and (if needed) filling table-fields used for automatic logging 
	 * @throws myException
	 */
	public VEK<String> getRawUpdateStatementsForNeededFields(String fullTableName) throws myException {
		VEK<String> ret = new VEK<>();
		
		Vector<String> fieldsA = DB_META.get_ORA_FIELDS(fullTableName.toUpperCase());
		VEK<String>    fields = new VEK<String>();  
		
		
		if (fieldsA==null || fieldsA.size()==0) {
			throw new myException("Error reading fields of Table: "+fullTableName+" Code: <8270cfdf-b38e-421d-8b42-92a4dbdb2583>");
		}
		
		fieldsA.stream().forEach(e-> {fields._a(e.toUpperCase());});
		
		VEK<loggingFields>  fieldsToAdd = new VEK<>();
		for (loggingFields lf: loggingFields.values()) {
			if (!fields.contains(lf.name())) {
				fieldsToAdd._a(lf);
			}
		}
		
		for (loggingFields f: fieldsToAdd) {
			ret._a(f.getRawAlterTableStatement(fullTableName));
		}
		
		for (loggingFields f: fieldsToAdd) {
			if (S.isFull(f.getRawUpdateStatement(fullTableName))) {
				ret._a(f.getRawUpdateStatement(fullTableName));
			}
		}
		
		return ret;
	}
	


}
