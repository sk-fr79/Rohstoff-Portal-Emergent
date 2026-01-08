/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author martin
 * @date 07.11.2019
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP.enumBlacklistAutomaticFields;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 07.11.2019
 *
 */
public class RecWatch {

	private String errorCode = "<d849a180-f4e7-4197-8d46-c2d0c98f1345>";
	
	private _TAB 		tab = null;
	private Long 		id  = null;

	private String 		triggerName = null;
	private String 		fieldNameUUID = enumBlacklistAutomaticFields.SYS_TRIGGER_UUID.name();
	
	private String      saveFieldName = "ERZEUGT_VON";
	
	private SEL 		selTrigger = null; 
	private SEL         selActualStamp = null;

	private String 		acutalUUID = null;
	
	
	public String getAcutalUUID() {
		return acutalUUID;
	}


	public RecWatch(_TAB tab, int id) throws myException {
		this(tab,new Long(id));
	}
	
	public RecWatch(_TAB tab, Long id) throws myException {
		
		try {
			this.tab = tab;
			this.id = id;
			
			this.triggerName = "TRIGG_"+tab.baseTableName();
			
			selTrigger = new SEL("TO_CHAR(STATUS)").FROM("USER_TRIGGERS").WHERE(new vglParam("USER_TRIGGERS", "TRIGGER_NAME"));
			
			VEK<Object[]> result = bibDB.getResultLines(new SqlStringExtended(selTrigger.s())._addParameter(new Param_String("", triggerName)), false);
			
			
			if (result.size()==1) {
				String ergebnis = S.NN((String)result.get(0)[0]);
				if (!ergebnis.equals("ENABLED")) {
					throw new myException("Trigger: "+triggerName+" is disabled !"+errorCode);
				}
				
				selActualStamp = new SEL(fieldNameUUID).FROM(tab).WHERE(new vglParam(tab.key()));
				
				this.acutalUUID = readActualUUID();
				
			} else {
				throw new myException("Trigger: "+triggerName+" cannot be localized correct !"+errorCode);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
			
		}
	}
	
	
	
//	public boolean isUnchanged() throws RecWatchException {
//		return this.readActualUUID().equals(this.acutalUUID);
//	}
	
	
	/**
	 * 
	 * @author martin
	 * @throws myException 
	 * @throws myException 
	 * @date 07.11.2019
	 * writes update on this.tab without commit and reads new uuid
	 * !!! YOU MUST ENSURE COMMIT or ROLLBACK !!!
	 */
	public boolean lock() throws myException {
		
		boolean locked=false;
		
		String sql = "UPDATE "+tab.fullTableName()+" SET "+saveFieldName+"="+saveFieldName+" WHERE "+tab.keyFieldName()+"=? AND NVL("+fieldNameUUID+",'0')=?";
		SqlStringExtended ext = new SqlStringExtended(sql)._addParameter(new Param_Long(id))._addParameter(new Param_String("", this.acutalUUID));
		int count = bibDB.executeRaw(new VEK<SqlStringExtended>()._a(ext),false);
		if (count ==1) {
			this.acutalUUID=readActualUUID();
			locked = true;
		}

		return locked;
	}

	
	private String readActualUUID() throws myException {
		String uuid = null;
		
		try {
			VEK<Object[]> resultStamp = bibDB.getResultLines(new SqlStringExtended(selActualStamp.s())._addParameter(new Param_Long("", id)), false);
			if (resultStamp.size()==1) {
				uuid = S.NN((String)resultStamp.get(0)[0],"0");
			} else {
				throw new myException("Table: "+tab.fullTableName()+":  Error reading "+fieldNameUUID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		}

		return uuid;
	}
	
	
	
	
}
