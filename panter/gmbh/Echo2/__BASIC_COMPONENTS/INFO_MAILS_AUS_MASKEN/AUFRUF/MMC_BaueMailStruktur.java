package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing_but_EditAdress;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_PDF_Connector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAIL_AUS_MASK_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAIL_AUS_MASK;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;


public class MMC_BaueMailStruktur {

	private MailBlock_Vector 				vMailEintraege = 	new MailBlock_Vector();
	private RECORD_MAIL_AUS_MASK  			recMailAusMask=	null;
	private String 							cBetreff_4_Send = null;
	private String 							cText_4_Send = null;
	

	private ownMassMailer  					oMailer = null;
	
	private MailSecurityPolicy  			oMailSecPolicy = null;
	private MMC_ERSETZUNGS_HASH   			oErsetzungsHash = null;
	
		
	public MMC_BaueMailStruktur(	RECORD_MAIL_AUS_MASK 			rec_MailAusMask,
									String 							c_Betreff_4_Send, 
									String 							c_Text_4_Send,
									MMC_ERSETZUNGS_HASH   			o_ErsetzungsHash) throws myException {
		super();
		this.recMailAusMask = rec_MailAusMask;
		this.cBetreff_4_Send = c_Betreff_4_Send;
		this.cText_4_Send = c_Text_4_Send;
		
		this.oErsetzungsHash = o_ErsetzungsHash;
		
		if (this.recMailAusMask.is_SIGNATUR_ANHAENGEN_YES()) {
			this.cText_4_Send = this.cText_4_Send+"\n\n\n"+bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cF_NN("");
		}
		
		
		this.oMailSecPolicy = new MailSecurityPolicyAllowNothing_but_EditAdress();
		if (this.recMailAusMask.is_ERLAUBE_FREIEMAILADRESSE_YES()) {
			this.oMailSecPolicy = new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed();
		}
		
		MMC_MailBlock  oMailBlock = new MMC_MailBlock(new MyE2_String(""),new MyE2_String("<Vorgabe-eMail-Adresse>"),this.oMailSecPolicy);
		
		
		
		RECLIST_MAIL_AUS_MASK_EMAIL  rlEmail = this.recMailAusMask.get_DOWN_RECORD_LIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask(null,_DB.MAIL_AUS_MASK_EMAIL$MAILADRESSE,true);
		
		if (rlEmail.get_vKeyValues().size()==0 && this.recMailAusMask.is_ERLAUBE_FREIEMAILADRESSE_NO()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei dieser Nachricht wurde keine Zieladresse angegeben, es darf auch keine manuelle Mail-Adresse erfaﬂt werden !")));
			return;
		}

		for (int i=0;i<rlEmail.get_vKeyValues().size();i++) {
			oMailBlock.ADD_NewTargetAdress(rlEmail.get(i).get_MAILADRESSE_cF_NN(""));
		}
		
		
		if (this.recMailAusMask.get_DOWN_RECORD_LIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask(null,_DB.MAIL_AUS_MASK_JASPER$DOWNLOADNAME,true).get_vKeyValues().size()>0) {
			
			//hier wird der Vektor benutzt um die jasperhashes (incl. tempfiles) lange genug am Leben zu halten
			// nach ausfuehrung von MMC_BaueReports(..) enthalten die E2_JasperHASH-Objekte die tempfiles mit den dateien
			Vector<E2_JasperHASH>  vJasperHash = new MMC_BaueReports(rec_MailAusMask, this.oErsetzungsHash).get_vJasperHash();
			
			
			
			//hier sind jetzt alle jasperreports incl. FileWithSendnames vorhanden
			//jetzt muss ein einziges file generiert werden
			
			Vector<FileWithSendName> vConcatenateFiles = new Vector<FileWithSendName>();
			for (E2_JasperHASH oJasperHash: vJasperHash) {
				if (oJasperHash.get_oTempFileWithSendeName()!=null) {
					vConcatenateFiles.add(oJasperHash.get_oTempFileWithSendeName());
				}
			}
			
			String cTargetFileName = "anlage";
				
			if (vConcatenateFiles.size()==0) {
				throw new myException("Keine Dateien zum Versenden gefunden ...");
			} else if (vConcatenateFiles.size()==1) {
				cTargetFileName = vConcatenateFiles.get(0).get_cNameFor_USER_EmailAttachment(); 
				if (cTargetFileName.length()>4) {
					cTargetFileName = cTargetFileName.substring(0,cTargetFileName.length()-4);
				}
			} else {
				cTargetFileName = vConcatenateFiles.get(0).get_cNameFor_USER_EmailAttachment();
				if (cTargetFileName.length()>4) {
					cTargetFileName = cTargetFileName.substring(0,cTargetFileName.length()-4)+"__diverse";
				}
			}
			
			//oMailBlock.build_MailSendGrid()
			
			myTempFile  oTempFile = new E2_PDF_Connector(vConcatenateFiles, true, cTargetFileName).get_oTempFileAutoDel();
			oMailBlock.ADD_OwnAttachement(new FileWithSendName(oTempFile, cTargetFileName+".pdf", new JasperFileDef_PDF()));
			
			if (vJasperHash.size()==1) {
				oMailBlock.set_cStringForLabel(new MyE2_String("Infomail (eine automatisch erzeugte Anlage)"));
			} else {
				oMailBlock.set_cStringForLabel(new MyE2_String("Infomail ("+vJasperHash.size()+" automatisch erzeugte Anlagen)"));
			}
		} else {
			oMailBlock.set_cStringForLabel(new MyE2_String("Infomail"));
		}
		
		
		this.vMailEintraege.add(oMailBlock);
		
		this.oMailer = new ownMassMailer();
	}


	public class ownMassMailer extends E2_MassMailer
	{
		
		public ownMassMailer() throws myException	{
			super(	MMC__CONST.KEY_INFOMAIL_KLEMMBRETTKENNER4BETREFF, 
					MMC__CONST.KEY_INFOMAIL_KLEMMBRETTKENNER4TEXT,
					MMC__CONST.KEY_INFOMAIL_KENNER4ARCHIV, 
					new MailSecurityPolicyAllowNothing());
			
			MMC_BaueMailStruktur oThis = MMC_BaueMailStruktur.this;
			
			this.baue_MailMaske(	oThis.vMailEintraege, 
									bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("<eMail>"), false, false, false, -1, null, 
									oThis.cBetreff_4_Send, 
									oThis.cText_4_Send);
		}

		@Override
		public MailBlock build_MailBlock4Added_EmptyMails() throws myException	{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_MitarbeiterMails()	throws myException	{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_SearchedMails() throws myException	{
			return null;
		}

		
	}


	public ownMassMailer get_O_Mailer() {
		return oMailer;
	}

	
	
	
	
	
}
