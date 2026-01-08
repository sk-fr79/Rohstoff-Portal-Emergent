package panter.gmbh.basics4project;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.A__Button_ADD_NEW_MailBlock;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class myCONST_ENUM {
	
	public enum VORGANGSART {
		
		
	    ANGEBOT(		myCONST.VORGANGSART_ANGEBOT,		myCONST.VORGANGSART_ANGEBOT_FOR_USER, 		"JT_VKOPF_STD"),
	    ABNAHMEANGEBOT(	myCONST.VORGANGSART_ABNAHMEANGEBOT,	myCONST.VORGANGSART_ABNAHMEANGEBOT_FOR_USER,"JT_VKOPF_STD"),
	    RECHNUNG(		myCONST.VORGANGSART_RECHNUNG,		myCONST.VORGANGSART_RECHNUNG_FOR_USER, 		"JT_VKOPF_RG"),
	    GUTSCHRIFT(		myCONST.VORGANGSART_GUTSCHRIFT,		myCONST.VORGANGSART_GUTSCHRIFT_FOR_USER, 	"JT_VKOPF_RG"),
	    EK_KONTRAKT(	myCONST.VORGANGSART_EK_KONTRAKT,	myCONST.VORGANGSART_EK_KONTRAKT_FOR_USER, 	"JT_VKOPF_KON"),
	    VK_KONTRAKT(	myCONST.VORGANGSART_VK_KONTRAKT,	myCONST.VORGANGSART_VK_KONTRAKT_FOR_USER, 	"JT_VKOPF_KON"),
	    TRANSPORT(		myCONST.VORGANGSART_TRANSPORT,		myCONST.VORGANGSART_TRANSPORT_FOR_USER, 	"JT_VKOPF_TPA"),
	    ;
	    
		
		private String DBValue = null;
		private String UserValue = null;
		private String TableName = null;
		
		VORGANGSART(String dbValue, String userValue, String tableName) {
			this.DBValue = 		dbValue;
			this.UserValue = 	userValue;
			this.TableName = 	tableName;
		}

		public String get_DBValue() {
			return DBValue;
		}
		public String get_UserValue() {
			return UserValue;
		}
		public String get_TableName() {
			return TableName;
		}
		
		
		public MyRECORD_IF_RECORDS get_RECORD(String cID) throws myException {
			
			if (this.TableName.equals("JT_VKOPF_STD")) {
				return new RECORD_VKOPF_STD(cID);
			} else if (this.TableName.equals("JT_VKOPF_RG")) {
				return new RECORD_VKOPF_RG(cID);
			} else if (this.TableName.equals("JT_VKOPF_KON")) {
				return new RECORD_VKOPF_KON(cID);
			} else if (this.TableName.equals("JT_VKOPF_TPA")) {
				return new RECORD_VKOPF_TPA(cID);
			} 
			return null;
		}
		
		/**
		 * 
		 * @param cID_Vorgang
		 * @return RECORD_ADRESSE zum Vorgang
		 * @throws myException
		 */
		public RECORD_ADRESSE get_RECORD_ADRESSE(String cID_Vorgang) throws myException {
			
			if (this.TableName.equals("JT_VKOPF_STD")) {
				return new RECORD_VKOPF_STD(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} else if (this.TableName.equals("JT_VKOPF_RG")) {
				return new RECORD_VKOPF_RG(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} else if (this.TableName.equals("JT_VKOPF_KON")) {
				return new RECORD_VKOPF_KON(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} else if (this.TableName.equals("JT_VKOPF_TPA")) {
				return new RECORD_VKOPF_TPA(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} 
			return null;
		}

		
	}
	
	
	public static VORGANGSART find_Vorgang(String tableName) {
		for (VORGANGSART va: myCONST_ENUM.VORGANGSART.values()) {
			if (va.get_TableName().equals(tableName)) {
				return va;
			}
		}
		
		return null;
	}
	
	
	public enum MAILDEF implements IF_enum_4_db {
		EMAIL_ALLTYPES(			"ALLEBELEGE",	"FÜR ALLE BELEGE")
		,EMAIL_NOTYPES(			"KEINEBELEGE",	"NICHT für Belege")
		,EMAIL_ANGEBOT(			"ANGEBOT",		"Angebot")
		,EMAIL_RECHNUNG(		"RECHNUNG",		"Rechnung")
		,EMAIL_GUTSCHRIFT(		"GUTSCHRIFT",	"Gutschrift")
		,EMAIL_AUFT_BEST(		"AUFT_BEST",	"Auftragsbestätigung")
		,EMAIL_LIEFERSCHEIN(	"LIEFERSCHEIN","Lieferschein")
		,EMAIL_TRANSPORT(		"TRANSPORT",	"Transportauftrag")
		,EMAIL_EK_KONTRAKT(		"EK_KONTRAKT",	"Einkaufskontrakt")
		,EMAIL_VK_KONTRAKT(		"VK_KONTRAKT",	"Verkaufskontrakt")
		,EMAIL_ABNAHMEANGEBOT(	"ABNAHMEANGEBOT","Abnahmeangebot")
		,EMAIL_FIBU(			"FIBU",			"Finanzbuchhaltung")
		,EMAIL_MAHNUNG(			"MAHNUNG",		"Mahnung")
		,EMAIL_LAGER(			"LAGER",		"Lager")
		,EMAIL_BUCHHALTUNG_RE_GUT("BUCHHALTUNG_RE_GUT",	"Versand von Rechnungen/Gutschriften")
		;
		
		private String db_val = null;
		private String readable = null;
		
		private MAILDEF(String p_db_val, String p_readable) {
			this.db_val = p_db_val;
			this.readable = p_readable;
		}
		@Override
		public String db_val() {
			return this.db_val;
		}
		@Override
		public String user_text() {
			return this.readable;
		}
		@Override
		public String user_text_lang() {
			return this.readable;
		}
		
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(MAILDEF.values(), emptyPairInFront);
		}
	}
	
	public enum FAXNUMMER_DEF{
		FIBU("FIBU"),
		MAHNUNG("FAX_FUER_MAHNUNG"),
		ALL("");
		
		private String datenbanktag = "";
		
		private FAXNUMMER_DEF(String oDatenbanktag){
			this.datenbanktag = oDatenbanktag;
		}
		
		public String getDatenbanktag(){
			return datenbanktag;
		}
	}

	
	
	
	/**
	 * globale sequencer fuer jeden mandanten, erzeugt werden SEQ_<ID_MANDANT>_NAME
	 */
	public enum SEQ_MANDANT {
		PRIMANOTABLOCK
		;

		private SEQ_MANDANT() {
		}
		
		public String getNextVal() throws myException {
			MyLong l = new MyLong(bibDB.EinzelAbfrage(this.getNextValQuery()));
			if (l.isOK()) {
				return l.get_cUF_LongString();
			} else {
				throw new myException("No possible sequence-number found !");
			}
		}

		public String getCurrVal() throws myException {
			MyLong l = new MyLong(bibDB.EinzelAbfrage(this.getCurrValQuery()));
			if (l.isOK()) {
				return l.get_cUF_LongString();
			} else {
				throw new myException("No possible sequence-number found !");
			}
		}

		
		public String getSequenceName() {
			return "SEQ_"+bibALL.get_ID_MANDANT()+"_"+this.name();
		}
		
		public String getNextValQuery() {
			return "SELECT "+this.getSequenceName()+".nextVal FROM DUAL";
		}
		
		
		public String getCurrValQuery() {
			return "SELECT "+this.getSequenceName()+".currval FROM DUAL";
		}
		
		public boolean isExisting() {
			String csql = "SELECT COUNT(*) FROM USER_SEQUENCES WHERE SEQUENCE_NAME='"+this.getSequenceName()+"'";
			if (bibDB.EinzelAbfrage(csql).trim().equals("1")) {
				return true;
			}
			return false;
		}

		
		public boolean dropSequence() {
			String csql = "DROP SEQUENCE "+this.getSequenceName();
			return bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(csql, true);
		}
		
		public boolean createSequence(long startnumber) {
			String csql = "CREATE SEQUENCE "+bibE2.cTO()+"."+this.getSequenceName()+"  START WITH  "+startnumber+"  INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999 NOCACHE"; ;
			return bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(csql, true);
		}
		
		public static String[] getNames() {
			String[] arr = new String[SEQ_MANDANT.values().length];
			int i=0;
			for (SEQ_MANDANT n: SEQ_MANDANT.values()) {
				arr[i++]=n.name();
			}
			return arr;
		}
		
	}
	
	
	
	
	public enum NUMMERNKREISE_GLOBAL {
		REPORTNUMMER()
		,REPORTARCHIV()
		,BUCHUNGSBLOCK_FIBU()
		,TR_TEMPTABLES()
		,TR_TEMPIDS()
		 
		;
		
		private NUMMERNKREISE_GLOBAL() {
		}

		
		public String getSqlString4NextVal() {
			return "SELECT SEQ_"+this.name()+".NEXTVAL FROM DUAL";
		}
		
		
		public Long getNextId() {
			MyLong l = new MyLong(bibDB.EinzelAbfrage(this.getSqlString4NextVal()));
			if (l.isOK()) {
				return l.get_oLong();
			}
			return  null;
		}
		
		

		
	}
	
	
}
