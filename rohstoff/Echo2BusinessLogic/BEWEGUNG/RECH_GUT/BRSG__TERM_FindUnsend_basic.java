package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.GENERATE.GenTERM;
import panter.gmbh.indep.exceptions.myException;

public class BRSG__TERM_FindUnsend_basic extends GenTERM {
	
	private String cSQL = null;
	
	/**
	 * abfrage liefert true, wenn alle originalarchivmedien mit ihrern versendungen und targets SEND_OK='Y'
	 * @throws myException
	 */
	public BRSG__TERM_FindUnsend_basic() throws myException {
		
		this.
		AppendTerm("NVL("+_DB.Z_ARCHIVMEDIEN$IST_ORIGINAL+",'N')", 	"=", "Y",false,true).
		//AppendTerm("NVL("+_DB.Z_ARCHIVMEDIEN$MEDIENKENNER+",'-')", 	"=", Archiver_CONST.MEDIENKENNER.ORIGBELEG.get_DB_WERT(), false,true).
		AppendTerm("NVL("+_DB.Z_EMAIL_SEND_TARGETS$SEND_OK+",'N')", "=", "N",false,true).
		AppendTerm(_DB.Z_ARCHIVMEDIEN$TABLENAME, "=", _DB.VKOPF_RG.substring(3),false,true).
		AppendTerm(_DB.Z_ARCHIVMEDIEN$ID_TABLE, "=",_DB.Z_VKOPF_RG$ID_VKOPF_RG,false,false);
		
		GenTERM join1 = new GenTERM();
		join1.AppendTerm(_DB.Z_EMAIL_SEND_ATTACH$ID_EMAIL_SEND, "=", _DB.Z_EMAIL_SEND$ID_EMAIL_SEND);
		
		GenTERM join2 = new GenTERM();
		join2.AppendTerm(_DB.Z_EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN, "=", _DB.Z_ARCHIVMEDIEN$ID_ARCHIVMEDIEN);

		GenTERM join3 = new GenTERM();
		join3.AppendTerm(_DB.Z_EMAIL_SEND$ID_EMAIL_SEND, "=", _DB.Z_EMAIL_SEND_TARGETS$ID_EMAIL_SEND);

		this.cSQL = "SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.EMAIL_SEND + 
				" INNER JOIN "+bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH + 	" ON ("+join1.get_TERMS_WITH("AND")+")"+
				" INNER JOIN "+bibE2.cTO()+"."+_DB.ARCHIVMEDIEN + 		" ON ("+join2.get_TERMS_WITH("AND")+")"+
				" INNER JOIN "+bibE2.cTO()+"."+_DB.EMAIL_SEND_TARGETS + " ON ("+join3.get_TERMS_WITH("AND")+")"+
				" WHERE "+this.get_TERMS_WITH("AND");
		
//		DEBUG.System_println(this.cSQL);
	}

	public String get_SQL_Bedingung_4_Selector() {
		return "(("+this.cSQL+")=0)";
	}
	
}
