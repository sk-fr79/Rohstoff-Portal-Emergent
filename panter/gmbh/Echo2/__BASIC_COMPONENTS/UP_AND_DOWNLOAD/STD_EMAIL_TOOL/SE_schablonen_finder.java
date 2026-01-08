package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;

public class SE_schablonen_finder {

	private String  betreff = null;
	private String  text = null;
	private String  absender = null;
	private String  send_typ = ES_CONST.SEND_TYPE.SINGLE.db_val();   //standard-versandtyp = einzelversand
	
	public SE_schablonen_finder(MAILPROFILE profile) throws myException {
		super();
		
		RECLIST_EMAIL_SEND_SCHABLONE rlSend = new RECLIST_EMAIL_SEND_SCHABLONE(new SELECT("*").from(_DB.EMAIL_SEND_SCHABLONE));
		RECORD_EMAIL_SEND_SCHABLONE  recSpecial = null;
		RECORD_EMAIL_SEND_SCHABLONE  recAllgem = null;
			
		for (RECORD_EMAIL_SEND_SCHABLONE recSchab: rlSend) {
			if (profile!=null && recSchab.get_KENNUNG_MAILVERSAND_cUF_NN("").equals(profile.toString())) {
				recSpecial = recSchab;
			}
			if (recSchab.get_KENNUNG_MAILVERSAND_cUF_NN("").equals(MANDANT_CONST.MAILPROFILE.ALLGEMEIN.toString())) {
				recAllgem = recSchab;
			}
		}
		
		this.betreff = "Belegversand";
		this.text = "Diese eMail wurd automatisch erzeugt.";
		this.absender = "";
		if (recSpecial!=null) {
			this.betreff = recSpecial.get_BETREFF_cUF_NN("Belegversand");
			this.text = recSpecial.get_TEXT_cUF_NN("Diese eMail wurd automatisch erzeugt.");
			this.absender = recSpecial.get_ABSENDER_cUF_NN("");
			this.send_typ = recSpecial.ufs(EMAIL_SEND_SCHABLONE.send_type,ES_CONST.SEND_TYPE.SINGLE.db_val());
		} else if (recAllgem != null) {
			this.betreff = recAllgem.get_BETREFF_cUF_NN("Belegversand");
			this.text = recAllgem.get_TEXT_cUF_NN("Diese eMail wurd automatisch erzeugt.");
			this.absender = recAllgem.get_ABSENDER_cUF_NN("");
			this.send_typ = recAllgem.ufs(EMAIL_SEND_SCHABLONE.send_type,ES_CONST.SEND_TYPE.SINGLE.db_val());
		}
		
	}

	public String get_betreff() {
		return betreff;
	}

	public String get_text() {
		return text;
	}

	public String get_absender() {
		return absender;
	}

	public String get_send_type() {
		return this.send_typ;
	}
	
	
}
