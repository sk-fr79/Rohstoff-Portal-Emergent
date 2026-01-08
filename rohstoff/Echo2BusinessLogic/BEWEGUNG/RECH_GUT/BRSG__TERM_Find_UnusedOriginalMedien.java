package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.GENERATE.GenTERM;
import panter.gmbh.indep.exceptions.myException;

public class BRSG__TERM_Find_UnusedOriginalMedien extends GenTERM {
	
	private String cSQL = null;
	
	/**
	 * abfrage ist true, wenn jede original-archivdatei einem email-target-datensatz zugeordnet wurde
	 * @throws myException
	 */
	public BRSG__TERM_Find_UnusedOriginalMedien() throws myException {
		
		this.
		AppendTerm("NVL("+_DB.Z_ARCHIVMEDIEN$IST_ORIGINAL+",'N')", 	"=", "Y",false,true).
		AppendTerm("NVL("+_DB.Z_ARCHIVMEDIEN$MEDIENKENNER+",'-')", 	"=", Archiver_CONST.MEDIENKENNER.ORIGBELEG.get_DB_WERT(), false,true).
		AppendTerm("NVL("+_DB.Z_EMAIL_SEND_ATTACH$ID_EMAIL_SEND_ATTACH+",-1)", "=", "-1",false,false).
		AppendTerm(_DB.Z_ARCHIVMEDIEN$TABLENAME, "=", _DB.VKOPF_RG.substring(3),false,true).
		AppendTerm(_DB.Z_ARCHIVMEDIEN$ID_TABLE, "=",_DB.Z_VKOPF_RG$ID_VKOPF_RG,false,false);
		
		GenTERM join2 = new GenTERM();
		join2.AppendTerm(_DB.Z_EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN, "=", _DB.Z_ARCHIVMEDIEN$ID_ARCHIVMEDIEN);


		this.cSQL = "SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.ARCHIVMEDIEN + 
				" LEFT OUTER JOIN "+bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH + 	" ON ("+join2.get_TERMS_WITH("AND")+")"+
				" WHERE "+this.get_TERMS_WITH("AND");
		
		DEBUG.System_println(this.cSQL);
		
	}
	
//	
//	public String get_SQL() {
//		return "("+this.cSQL+")";
//	}
//	
	public String get_SQL_Bedingung_4_Selector() {
		return "(("+this.cSQL+")=0)";
	}


	
}
