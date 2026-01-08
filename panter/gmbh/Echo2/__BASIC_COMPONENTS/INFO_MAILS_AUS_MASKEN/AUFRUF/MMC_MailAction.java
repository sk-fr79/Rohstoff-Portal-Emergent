package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAIL_AUS_MASK;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MMC_MailAction extends XX_ActionAgent {

	private RECORD_MAIL_AUS_MASK  			oRecMailAusMask	=	null;
	
	//variablen, die bereits die platzhalter-ersetzung hinter sich haben, beginnen mit cc_
	private String 							cc_Betreff = null;
	private String 							cc_MailText = null;
	private String 							cc_GROOVY_BEDINGUNG = null;
	private MMC_ERSETZUNGS_HASH   			o_ErsetzungsHash = null;
	
	public MMC_MailAction(RECORD_MAIL_AUS_MASK  recMailAusMask, MMC_ERSETZUNGS_HASH oErsetzungsHash) throws myException {
		super();
		this.o_ErsetzungsHash = oErsetzungsHash;
		this.oRecMailAusMask = recMailAusMask;
	}

	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {

		
		//nochmal einlesen
		this.oRecMailAusMask.REBUILD();
		
		//die ersetzungshash neu aufbauen
		o_ErsetzungsHash.CleanAndFillHashMap();
		
		
		//1. ebene sicherheitscheck
		if (! (	this.oRecMailAusMask.is_ERLAUBT_BEI_EDIT_YES() &&
				this.oRecMailAusMask.is_ERLAUBT_BEI_NEUEINGABE_YES() &&
				this.oRecMailAusMask.is_ERLAUBT_BEI_VIEW_YES() &&
				this.oRecMailAusMask.is_ERLAUBT_BEI_UNDEF_YES())) {
			if (this.o_ErsetzungsHash.get_bMask_STATUS_NeuErfassung() && this.oRecMailAusMask.is_ERLAUBT_BEI_NEUEINGABE_NO()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Mailversendung ist bei Masken-Neueingaben verboten !")));
				return;
			}
			
			if (this.o_ErsetzungsHash.get_bMask_STATUS_Edit() && this.oRecMailAusMask.is_ERLAUBT_BEI_EDIT_NO()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Mailversendung ist bei Masken-Bearbeitung verboten !")));
				return;
			}
			
			if (this.o_ErsetzungsHash.get_bMask_STATUS_Anzeige() && this.oRecMailAusMask.is_ERLAUBT_BEI_VIEW_NO()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Mailversendung ist bei Masken-Anzeige verboten !")));
				return;
			}

			if (this.o_ErsetzungsHash.get_bMask_STATUS_Undefiniert() && this.oRecMailAusMask.is_ERLAUBT_BEI_UNDEF_NO()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Mailversendung ist bei undefiniertem Masken-Status verboten !")));
				return;
			}

		}
		
		this.cc_Betreff = o_ErsetzungsHash.get_Ersetzungstext(this.oRecMailAusMask.get_BETREFF_cUF_NN(""));
		this.cc_MailText = o_ErsetzungsHash.get_Ersetzungstext(this.oRecMailAusMask.get_MAILTEXT_cUF_NN(""));
		this.cc_GROOVY_BEDINGUNG = o_ErsetzungsHash.get_Ersetzungstext(this.oRecMailAusMask.get_GROOVY_BEDINGUNG_cUF_NN(""));
		
		MMC_GroovyCheck oCheck = new MMC_GroovyCheck(this.cc_GROOVY_BEDINGUNG);
		
		if (oCheck.get_bErlaubnisErteilt()) {
			
			if (S.isFull(this.oRecMailAusMask.get_SICHERHEITSABFRAGE_VOR_START_cUF_NN(""))) {
				Vector<String> vFrage_JA_Nein = bibALL.TrenneZeile(this.oRecMailAusMask.get_SICHERHEITSABFRAGE_VOR_START_cUF_NN(""), "#");
				if (vFrage_JA_Nein.size()==3) {
					Vector<MyString> vMeldung = new Vector<MyString>();
					vMeldung.add(new MyE2_String(vFrage_JA_Nein.get(0),false));
					new E2_MessageBoxYesNo(	new MyE2_String("Sicherheitsabfrage:"), 
											new MyE2_String(vFrage_JA_Nein.get(1),false), 
											new MyE2_String(vFrage_JA_Nein.get(2),false), 
											vMeldung, 
											new ownActionStartAusfuehrung(),
											450,180);
				} else {
					Vector<MyString> vMeldung = new Vector<MyString>();
					vMeldung.add(new MyE2_String(this.oRecMailAusMask.get_SICHERHEITSABFRAGE_VOR_START_cUF_NN(""),false));
					new E2_MessageBoxYesNo(	new MyE2_String("Sicherheitsabfrage:"), 
											new MyE2_String("Ja"), 
											new MyE2_String("Nein"), 
											vMeldung, 
											new ownActionStartAusfuehrung(),
											450,180);
				}
			} else {
				this.InnereAusfuehrung();
			}
			
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oCheck.get_cFehlermeldung()));
			
		}
		
		
	}

	private class ownActionStartAusfuehrung extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MMC_MailAction.this.InnereAusfuehrung();
		}
		
	}
	
	
	
	private void InnereAusfuehrung() throws myException {
		
		
		MMC_BaueMailStruktur oMailStrukt = new MMC_BaueMailStruktur(this.oRecMailAusMask, this.cc_Betreff, this.cc_MailText, this.o_ErsetzungsHash);
		
		//wenn bei der erstellung eines der reports fehler auftreten, dann hier abbrechen
		if (bibMSG.get_bIsOK()) {
			oMailStrukt.get_O_Mailer().CREATE_AND_SHOW_POPUPWINDOW(new Extent(900), new Extent(600), new MyE2_String("eMail versenden ..."));
		}
		
	}
	
	
	
}
