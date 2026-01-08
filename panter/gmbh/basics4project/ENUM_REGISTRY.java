package panter.gmbh.basics4project;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_ENUMS.REGISTRY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_keys4save;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_REGISTRY implements IF_enum_4_db {
	SANKTION_JDBC_STRING("TERROR_JDBC_STRING")
	,SANKTION_JDBC_LOGIN("TERROR_JDBC_LOGIN")
	,SANKTION_JDBC_PWD("TERROR_JDBC_PWD")
	,SANKTION_MAX_VEKTOR_LENGTH("MAX_VEKTOR_LENGTH")
	,SANKTION_MIN_WORD_LENGTH("MIN_WORD_LENGTH")
	,SUBDIR_TO_JSON("SUBDIR_TO_JSON")
	;

	private String registry_key;

	
	private ENUM_REGISTRY(String str_registry_key) {
		this.registry_key = str_registry_key;
	}

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return this.registry_key;
	}

	

	public Rec21 readRecord() throws myException {
		
		//zuerst nachsehen, ob die Session ein rec21 des schluessels enthaelt
		Rec21 r = ENUM_REGISTRY.getSessionValue(this, bibALL.get_ID_MANDANT());
		
		if (r==null) {
			String abfrage = new SEL().FROM(_TAB.registry).WHERE(new And(new vgl(REGISTRY.enum_registry_key, this.name())).and(new vgl(REGISTRY.id_mandant,bibALL.get_ID_MANDANT()))).s();
			r = new Rec21(_TAB.registry)._fill_sql(abfrage);
			ENUM_REGISTRY.setSessionValue(this,bibALL.get_ID_MANDANT(),r);
			
		}
		
		return r;
	}
	


	public static void setSessionValue(ENUM_REGISTRY key, String id_mandant, Rec21 sessionRec) {
		bibSES.setSessionValue(key.toString()+bibSES_keys4save.SAVEKEY_FOR_REGISTRY_VALS.s()+"@MANDANT:"+id_mandant, sessionRec);
	}

	public static Rec21 getSessionValue(ENUM_REGISTRY key, String id_mandant) {
		return (Rec21)bibSES.getSessionValue(key.toString()+bibSES_keys4save.SAVEKEY_FOR_REGISTRY_VALS.s()+"@MANDANT:"+id_mandant);
	}


	
	public BigDecimal getBdValue() throws myException {
		return this.getBdValue(null);
	}
	
	
	public String getStringValue() throws myException {
		return this.getStringValue(null);
	}
	
	
	public BigDecimal getBdValue(BigDecimal whenNull) throws myException {
		Rec21 rec = this.readRecord();
		if (rec==null || rec.is_newRecordSet()) {
			return whenNull;
		} else {
			return rec.get_raw_resultValue_bigDecimal(REGISTRY.nmber);
		}
	}
	
	
	public String getStringValue(String whenNull) throws myException {
		Rec21 rec = this.readRecord();
		if (rec==null || rec.is_newRecordSet()) {
			return whenNull;
		} else {
			return rec.get_ufs_dbVal(REGISTRY.nvarchr);
		}
	}
	
	public boolean isYes(boolean bWhenNull) throws myException {
		Rec21 rec = this.readRecord();
		if (rec==null || rec.is_newRecordSet()) {
			return bWhenNull;
		} else {
			return rec.is_yes_db_val(REGISTRY.nvarchr1);
		}
	}
	
	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return null;
	}
}
