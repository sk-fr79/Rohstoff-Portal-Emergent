/**
 * panter.gmbh.Echo2.basic_tools.emailv2
 * @author martin
 * @date 12.02.2021
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper.DataTypeException;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector.MessageException;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.basics4project.ENUM_EmailType;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.mail.SendMail_STD;
import panter.gmbh.indep.myVectors.VEK;


/**
 * @author martin
 * @date 12.02.2021
 *
 */
public class Rec22EmailSend extends Rec22 {

	private VEK<Rec22>  targets = new VEK<Rec22>();
	private VEK<Rec22>  attachments = new VEK<Rec22>();
	
	private EmailReplacerPlaceholders  replacerPlaceholders = null;
	
	private boolean allowArchivmedienDifferentTableAndId = false;
	



	private VEK<String>  lastSendedEmailsOK = new VEK<String>();
	private VEK<String>  lastSendedEmailsError = new VEK<String>();

	public Rec22EmailSend() throws myException {
		super(_TAB.email_send);
	}

	public Rec22EmailSend(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.email_send) {
			throw new myException("Only records of type archivmedien are allowed ! <5c74e698-11ed-11eb-adc1-0245ac127802>");
		}
	}

	
	public Rec22EmailSend _setFromAdress(String emailFromAdress) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._setNewVal(EMAIL_SEND.sender_adress, emailFromAdress, mv);
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		return this;
	}
	
	
	public Rec22EmailSend _setTABBelongsTo(_TAB tab) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._setNewVal(EMAIL_SEND.table_base_name, tab.baseTableName(), mv);
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		return this;
	}
	
	public Rec22EmailSend _setTABIdBelongsTo(Long id) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._setNewVal(EMAIL_SEND.id_table, id.toString(), mv);
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		return this;
	}
	
	
	public Rec22EmailSend _setSendTyp(ES_CONST.SEND_TYPE sentType) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._setNewVal(EMAIL_SEND.send_type,sentType.db_val(), mv);
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		return this;
	}
	
	public Rec22EmailSend _setSubject(String subject) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._setNewVal(EMAIL_SEND.betreff,subject, mv);
		if (this.replacerPlaceholders==null) {
			this._setNewVal(EMAIL_SEND.betreff_2_send,subject, mv);
		} else {
			this._setNewVal(EMAIL_SEND.betreff_2_send,replacerPlaceholders.replace(subject), mv);
		}

		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		return this;
	}

	
	public Rec22EmailSend _setMailText(String text) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._setNewVal(EMAIL_SEND.text,text, mv);
		if (this.replacerPlaceholders==null) {
			this._setNewVal(EMAIL_SEND.text_2_send,text, mv);
		} else {
			this._setNewVal(EMAIL_SEND.text_2_send,replacerPlaceholders.replace(text), mv);
		}
		
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		return this;
	}


	public Rec22EmailSend _addTarget(String targetAdress) throws Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		Rec22 target = new Rec22(_TAB.email_send_targets);
		target._setNewVal(EMAIL_SEND_TARGETS.target_adress, targetAdress, mv);
		target._setNewVal(EMAIL_SEND_TARGETS.pos, new Long(targets.size()+1), mv);
		
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		targets._a(target);
		
		return this;
	}
	
	
	public Rec22EmailSend _addAttachment(Rec22Archivmedien recAttachement) throws DataTypeException,Exception {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		boolean fehler = false;

		if (!this.allowArchivmedienDifferentTableAndId ) {
			//dann muessen die koordinaten beider records zusammenpassen
			if (S.isEmpty((String)this.getValueLast(EMAIL_SEND.id_table)) || ((Long)recAttachement.getValueLast(ARCHIVMEDIEN.id_table))==null)  {
				fehler=true;
			} else {
				if (!((String)this.getValueLast(EMAIL_SEND.id_table)).equals(((Long)recAttachement.getValueLast(ARCHIVMEDIEN.id_table)).toString()))  {
					fehler=true;
				}
				
			}
			if (((String)this.getValueLast(EMAIL_SEND.table_base_name))==null || ((String)recAttachement.getValueLast(ARCHIVMEDIEN.tablename))==null)  {
				fehler=true;
			} else {
				if (!((String)this.getValueLast(EMAIL_SEND.table_base_name)).equals((String)recAttachement.getValueLast(ARCHIVMEDIEN.tablename))) {
					fehler=true;
				}
			}
		}
		
		if (fehler) {
			throw new Exception("Adding Rec22Archivmedien  to Rec22EmailSend: tablename and id MUST be the same !");
		}
		
		
		Rec22 attachment = new Rec22(_TAB.email_send_attach);
		attachment._setNewVal(EMAIL_SEND_ATTACH.id_archivmedien, recAttachement.getIdLong(), mv);
		
		if (mv.hasAlarms()) {
			throw new myException(mv.get_MessagesAsText());
		}
		attachments._a(attachment);
		
		return this;
	}
	
	
	
	public Rec22EmailSend _saveAll(boolean commit) throws EmailDoubleKennungAndKeyException,DataTypeException,Exception {

		if (!S.isAllFull(this.get_ufs_lastVal(EMAIL_SEND.table_base_name),this.get_ufs_lastVal(EMAIL_SEND.id_table))) {
		 	throw new EmailSendMissungTableAndTableIdException("Bei Speichern des eMail-Objekts MUSS die Tabelle und die Tabellen-ID, zu der die eMail gehört, vorhanden sein !");
		} else {
			
			MyE2_MessageVector mvLocal = bibMSG.getNewMV();
			
			
			String tabName = 			 (String)this.getValueLast(EMAIL_SEND.table_base_name);
			String tabId = 				 (String)this.getValueLast(EMAIL_SEND.id_table);
			String mailType = 		 	(String)this.getValueLast(EMAIL_SEND.email_type);
			String mailVerificationKey = (String)this.getValueLast(EMAIL_SEND.email_verification_key);
			
			
			if (S.isAllFull(mailType,mailVerificationKey,tabName,tabId)) {
				SEL sel = new SEL("*").FROM(_TAB.email_send)
								.WHERE(new vglParam(EMAIL_SEND.id_table))
								.AND(new vglParam(EMAIL_SEND.table_base_name))
								.AND(new vglParam(EMAIL_SEND.email_type))
								.AND(new vglParam(EMAIL_SEND.email_verification_key));
				
				SqlStringExtended statement = new SqlStringExtended(sel.s())
							._addParameter(new Param_String("", tabId))
							._addParameter(new Param_String("", tabName))
							._addParameter(new Param_String("", mailType))
							._addParameter(new Param_String("", mailVerificationKey))
							;
				
				RecList22 rl = new RecList22(_TAB.email_send)._fill(statement);
				if (rl.size()>0) {
					throw new EmailDoubleKennungAndKeyException(this.getDoubleKeyMessage());
				}
			}
			
			boolean mustRollBack = true;
			
			
			try {
				if (bibDB.saveRecords(new VEK<Rec22>()._a(this), true,false, mvLocal)) {
					Long id = this.getIdLong();
					for (Rec22 attachment: attachments) {
						attachment._setNewVal(EMAIL_SEND_ATTACH.id_email_send, id, mvLocal);
					}
					for (Rec22 target: targets) {
						target._setNewVal(EMAIL_SEND_TARGETS.id_email_send, id, mvLocal);
					}
					if (mvLocal.isOK()) {
						if (bibDB.saveRecords(new VEK<Rec22>()._a(attachments)._a(targets),false, commit, mvLocal)) {
							mustRollBack=false;
						}
					} else {
						mustRollBack = true;
					}
				}
			} catch (Exception e) {
				mustRollBack=true;
				mvLocal._add(e);
				e.printStackTrace();
			}
			
			if (mustRollBack) {
				bibDB.Rollback();
			} else if (commit) {
				//falls kein erzwungenes rollback erfolgt ist und die anforderung ein commit enthaelt, dann hier nochmal ein commit ausfuehren
				if (!bibDB.Commit()) {
					bibDB.Rollback();
				}
			}
			
			if (!mvLocal.isOK() ) {
				throw new Exception(mvLocal.get_MessagesAsText());
			}
		}
		
		
		
		return this;
	}
	
	
	
	
	
	/**
	 * @param idTargetList (can be null)
	 * @return
	 * @throws myException
	 */
	public Rec22EmailSend _sendEmail(VEK<Long> idTargetList) throws MailException, Exception {

		//zuerst pruefen, ob die archivmedien alle vorhanden sind
		//und die anhaenge zusammenstellen
		VEK<String> 			pathnames = 		new VEK<String>();
		VEK<FileWithSendName> 	fileWithSendNames = new VEK<FileWithSendName>();
		VEK<String> 			missingFilenames = new VEK<String>();
		
		VEK<Rec22>              targetsToUse = new VEK<Rec22>();
		
		lastSendedEmailsOK._clear();
		lastSendedEmailsError._clear();
		
		if (idTargetList==null) {
			for (Rec22 target: this.get_down_reclist22(EMAIL_SEND_TARGETS.id_email_send,null,EMAIL_SEND_TARGETS.pos.fieldName())) {
				targetsToUse._a(target);
			}
		} else {
			for (Rec22 target: this.get_down_reclist22(EMAIL_SEND_TARGETS.id_email_send,null,EMAIL_SEND_TARGETS.pos.fieldName())) {
				if (idTargetList.contains(target.getIdLong())) {
					targetsToUse._a(target);
				}
			}
		}
		
		
		for (Rec22 recAtt: this.get_down_reclist22(EMAIL_SEND_ATTACH.id_email_send)) {
			Rec22Archivmedien recArch = new Rec22Archivmedien(recAtt.getUpRec22(ARCHIVMEDIEN.id_archivmedien));
			if (!recArch.is_existing_in_filesystem()) {
				missingFilenames._a(recArch.get__cCompletePathAndFileName());
			} else {
				String path = recArch.get__cCompletePathAndFileName();
				String sendName = recArch.getUfs(ARCHIVMEDIEN.downloadname,recArch.getUfs(ARCHIVMEDIEN.dateiname));
				if (!pathnames.contains(path)) {
					pathnames._a(path);
					fileWithSendNames._a(new FileWithSendName(path, sendName, recArch.findJasperFileDef()));
				}
			}
		}
		
		if (missingFilenames.size()>0) {
			throw new EmailSendAttachemenIsMissingException("Email not possible: Files are missing :"+missingFilenames.concatenante("\n"));
		}
		
		//nachsehen, ob noch unverschickte Targets vorhanden sind
		int countUnsent = 0;
		for (Rec22 target: targetsToUse) {
			if (target.is_no_db_val(EMAIL_SEND_TARGETS.send_ok)) {
				countUnsent++;
			}
		}
		if (countUnsent==0) {
			throw new EmailSendNoRemainingTargetException("Email not possible: No unsent Target existing!");
		}
		
		
		SEND_TYPE sendType = SEND_TYPE.find(this.getUfs(EMAIL_SEND.send_type));
		
		if (sendType==null) {
			throw new EmailSendTypeTargetException("Email not possible: You must define Sending Type: SINGLE,CC,BCC");
		}
		
		
		String absender = this.getUfs(EMAIL_SEND.sender_adress);
		String betreff = this.getUfs(EMAIL_SEND.betreff_2_send);
		String text = 	 this.getUfs(EMAIL_SEND.text_2_send);

		if (!S.isAllFull(absender,betreff,text)) {
			throw new EmailSendTypeTargetException("Email not possible: You must define the Fields: SENDER-Adress, Subject, Mailtext");
		}
		
		if (sendType==SEND_TYPE.SINGLE) {
			for (Rec22 target: targetsToUse) {
				if (target.is_no_db_val(EMAIL_SEND_TARGETS.send_ok)) {
					try {
						SendMail_STD  sendMail = new SendMail_STD(absender, target.getUfs(EMAIL_SEND_TARGETS.target_adress), null, null, betreff, text,fileWithSendNames);
						sendMail.sendViaStandardMail();
						target._setNewVal(EMAIL_SEND_TARGETS.send_ok, "Y");
						target._setNewVal(EMAIL_SEND_TARGETS.id_user_send,bibALL.get_RECORD_USER().get_ID_USER_lValue(null));
						target._SAVE(true);
						lastSendedEmailsOK._a(target.getUfs(EMAIL_SEND_TARGETS.target_adress));
					} catch (MailException e) {
						e.printStackTrace();
						lastSendedEmailsError._a(target.getUfs(EMAIL_SEND_TARGETS.target_adress));

						throw new MailException("Error on eMail: "+target.getUfs(EMAIL_SEND_TARGETS.target_adress));
					}
				}
			}
		} else if (sendType==SEND_TYPE.CC || sendType==SEND_TYPE.BCC) {
			String firstAdress = null;
			VEK<String> ccAdresses = new VEK<String>();
			for (Rec22 target: targetsToUse) {
				if (target.is_no_db_val(EMAIL_SEND_TARGETS.send_ok)) {
					if (firstAdress==null) {
						firstAdress = target.getUfs(EMAIL_SEND_TARGETS.target_adress);
					} else {
						ccAdresses._a(target.getUfs(EMAIL_SEND_TARGETS.target_adress));
					}
				}
			}
			try {
				SendMail_STD sendMail = null;
				if (sendType==SEND_TYPE.CC) {
					sendMail = new SendMail_STD(absender, firstAdress, ccAdresses, null, betreff, text,fileWithSendNames);
				} else {
					sendMail = new SendMail_STD(absender, firstAdress, null, ccAdresses, betreff, text,fileWithSendNames);
				}
				sendMail.sendViaStandardMail();
				for (Rec22 target: targetsToUse) {
					if (target.is_no_db_val(EMAIL_SEND_TARGETS.send_ok)) {
						target._setNewVal(EMAIL_SEND_TARGETS.send_ok, "Y");
						target._setNewVal(EMAIL_SEND_TARGETS.id_user_send,bibALL.get_RECORD_USER().get_ID_USER_lValue(null));
						target._SAVE(true);
					}
				}
				lastSendedEmailsOK._a(ccAdresses);
			} catch (MailException e) {
				e.printStackTrace();
				lastSendedEmailsError._a(ccAdresses);
				throw new MailException("Error on eMails: "+ccAdresses.concatenante("\n"));

			}
		}
		
		return this;
	}

	
	
	
	public SEND_STATUS getSendStatus() throws Exception {
		
		int countUnsent = 0;
		int countAllTargets = 0;
		for (Rec22 target: this.get_down_reclist22(EMAIL_SEND_TARGETS.id_email_send)) {
			if (target.is_no_db_val(EMAIL_SEND_TARGETS.send_ok)) {
				countUnsent++;
			}
			countAllTargets++;
		}

		if (countAllTargets==countUnsent) {
			return SEND_STATUS.SEND_NONE;
		} else if (countAllTargets!=countUnsent && countUnsent>0) {
			return SEND_STATUS.SEND_PART;
		} else {
			return SEND_STATUS.SEND_ALL;
		}
		
	}
	

	
	public static interface EmailReplacerPlaceholders {
		public String replace(String originalText);
	}

	public EmailReplacerPlaceholders getReplacerPlaceholders() {
		return replacerPlaceholders;
	}

	public Rec22EmailSend _setReplacerPlaceholders(EmailReplacerPlaceholders replacerPlaceholders) {
		this.replacerPlaceholders = replacerPlaceholders;
		return this;
	}
	
	public VEK<String> getLastSendedEmailsOK() {
		return lastSendedEmailsOK;
	}

	public VEK<String> getLastSendedEmailsError() {
		return lastSendedEmailsError;
	}

	
	@Override
	public Rec22EmailSend _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	
	
	/**
	 * @return the allowArchivmedienDifferentTableAndId
	 */
	public boolean isAllowArchivmedienDifferentTableAndId() {
		return allowArchivmedienDifferentTableAndId;
	}

	/**
	 * @param allowArchivmedienDifferentTableAndId (damit kann ein archivsatz, der nicht zur gleichen zieltabelle/Id gehoert angehaengt werden
	 */
	public Rec22EmailSend _setAllowArchivmedienDifferentTableAndId(boolean allowArchivmedienDifferentTableAndId) {
		this.allowArchivmedienDifferentTableAndId = allowArchivmedienDifferentTableAndId;
		return this;
	}

	
	public Rec22EmailSend _setEmailType(ENUM_EmailType kennung) throws myException, MessageException, Exception {
		this._setNewVal(EMAIL_SEND.email_type, kennung.db_val());
		return this;
	}
	
	public Rec22EmailSend _setMailEmailVerificationKey(String email_verification_key) throws myException, MessageException, Exception {
		this._setNewVal(EMAIL_SEND.email_verification_key, email_verification_key);
		return this;
	}
	
	

	public static class EmailSendAttachemenIsMissingException extends Exception {
		public EmailSendAttachemenIsMissingException(String message) {
			super(message);
		}
	}
	
	public static class EmailSendNoRemainingTargetException extends Exception {
		public EmailSendNoRemainingTargetException(String message) {
			super(message);
		}
	}


	public static class EmailSendTypeTargetException extends Exception {
		public EmailSendTypeTargetException(String message) {
			super(message);
		}
	}

	public static class EmailSendBaseFieldsMissingException extends Exception {
		public EmailSendBaseFieldsMissingException(String message) {
			super(message);
		}
	}

	
	public static class EmailSendMissungTableAndTableIdException extends Exception {
		public EmailSendMissungTableAndTableIdException(String message) {
			super(message);
		}
	}

	public static class EmailDoubleKennungAndKeyException extends Exception {
		public EmailDoubleKennungAndKeyException(String message) {
			super(message);
		}
	}
	
	
	public static class EmailSendVerificationKeyIsDouble extends Exception {
		public EmailSendVerificationKeyIsDouble(Rec22EmailSend rec22EmailSend) {
			super();
		}

	}
	
	public String getDoubleKeyMessage() throws DataTypeException, Exception {
		String ret = null;
		String id = S.NN((String)this.getValueLast(EMAIL_SEND.id_table));
		String tableName = S.NN((String)this.getValueLast(EMAIL_SEND.table_base_name));
		String mailType = S.NN((String)this.getValueLast(EMAIL_SEND.email_type));
		String mailVerification = S.NN((String)this.getValueLast(EMAIL_SEND.email_verification_key),"-");   //verifizierung null oder leer ist ebenfalls ein zustand
		ENUM_EmailType enMailtype = ENUM_EmailType.MAILVERSAND_WAAGE_STORNO.getEnum(mailType);
		String mailTypeUserText = "";
		if (enMailtype!=null) {
			mailTypeUserText=enMailtype.user_text();
		}
		ret = "Tabelle: "+tableName+" (ID: "+id+") Mailing Typ: "+S.NN(mailTypeUserText)+": Kennung existiert bereits ("+mailVerification+")";
		return ret;
	}

	
	
	
}
