/**
 * 
 */
package panter.gmbh.basics4project;

import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST.ENUM_MESSAGE_TYP;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_ENUMS.NACHRICHT_KATEGORIE;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * enum mit db-codes fuer message-verteilung an diverse stellen
 */
public enum ENUM_MESSAGE_PROVIDER implements IF_enum_4_db{
	
	MESSAGE_QUALIFYING_AH7_STEUERSATZ("Anforderung Freigabe","Meldung an AH-7-Sachbearbeiter zum Qualifizeren einer neuen Steuerrelation", ENUM_MESSAGE_TYP.USER)
	,MESSAGE_ADRESSE_FREIGABE("Sanktionierte Adresse freigeben","Personen, die zur Freigabe von sanktionierten Adressen berechtigt sind, über eine Sperre informieren !",ENUM_MESSAGE_TYP.USER)
	,MESSAGE_ERSTELLUNG_HANDELSDEFINITION("Handelsdefinition erstellen","Meldung an Anwender zum Erstellen einer fehlenden Handelsdefinition/Steuerrelation ",ENUM_MESSAGE_TYP.USER)
	;

	
	private String 			       	userText = null;
	private ENUM_MESSAGE_TYP		messageTyp = null;
	private String 					kategorie = null;    
	
	private ENUM_MESSAGE_PROVIDER(String p_kategorie, String p_userText, ENUM_MESSAGE_TYP typ) {
		this.kategorie = p_kategorie;
		this.userText = p_userText;
		this.messageTyp = typ;
	}

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return S.NN(this.userText, this.name());
	}

	@Override
	public String user_text_lang() {
		return this.userText;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_MESSAGE_PROVIDER.values(), true);
	}
	
	public String getKategorie() {
		return S.NN(this.kategorie, this.name());
	}
	
	
	
	
	public MyE2_MessageVector generateMessages(MODUL jumpTarget, String id_uf_jumptarget, String meldung_an_target, String nameSprungZiel) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		//zuerst nachsehen, ob die nachrichtenkategorie existiert, falls nicht, eine schreiben
		Long  id_nachrichtenkategorie = null; 
		SEL sk = new SEL(_TAB.nachricht_kategorie).FROM(_TAB.nachricht_kategorie).WHERE(new vgl(NACHRICHT_KATEGORIE.kategorie,this.getKategorie()));
		RecList20 rlk = new RecList20(_TAB.nachricht_kategorie)._fill(sk.s());
		if (rlk.size()==1) {
			id_nachrichtenkategorie = rlk.get(0).get_myLong_dbVal(NACHRICHT_KATEGORIE.id_nachricht_kategorie).get_oLong();
		} else {
			Rec20 recNK = new Rec20(_TAB.nachricht_kategorie)._nv(NACHRICHT_KATEGORIE.kategorie, this.getKategorie(), mv);
			if (mv.get_bIsOK()) {
				recNK._SAVE(true, mv);
				if (mv.get_bIsOK()) {
					mv.add_MESSAGE(new MyE2_Info_Message(S.ms("Die Nachrichtenkategorie <").ut(this.getKategorie()).t("> wurde dem System hinzugefügt!")));
					id_nachrichtenkategorie = recNK.get_rec_after_save_new().get_myLong_dbVal(NACHRICHT_KATEGORIE.id_nachricht_kategorie).get_oLong();
				}
			}
		}
		
		
		if (id_nachrichtenkategorie!=null && mv.get_bIsOK()) {
		
			SEL s = new SEL(_TAB.message_provider).FROM(_TAB.message_provider).WHERE(new vgl(MESSAGE_PROVIDER.messagekey, this.db_val()));
			
			RecList20 rl = new RecList20(_TAB.message_provider)._fill(s.s());

			if (rl.isEmpty()) {
				throw new myException(this,"No Message-Recipient for MessageProvider with key: "+MESSAGE_PROVIDER.messagekey);
			}
			
			MESSAGE_Handler mh = new MESSAGE_Handler(this.messageTyp!=null?this.messageTyp.db_val():ENUM_MESSAGE_TYP.SYSTEM.db_val());
			String sTitle = S.NN(nameSprungZiel,this.kategorie);
			
			for (Rec20 r: rl) {
				MESSAGE_Entry msgEntry = new MESSAGE_Entry()
	                    .set_Titel(sTitle)
	                    .set_Nachricht( this.userText+ (S.isFull(meldung_an_target)?"\n\n"+meldung_an_target:""))
	                    .set_vIdEmpfaenger(new VEK<String>()._a(r.get_ufs_dbVal(MESSAGE_PROVIDER.id_user)))
	                    .set_Sofortanzeige(true)
	                    .set_DtAnzeigeAb(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
	                    .set_SendEmail(r.is_yes_db_val(MESSAGE_PROVIDER.send_email))
	                    .set_ID_Kategorie(id_nachrichtenkategorie)
	                    ;
				if (this.messageTyp!=null && this.messageTyp==ENUM_MESSAGE_TYP.USER) {
					msgEntry.set_IDUserSender(bibALL.get_ID_USER());
				}
				
				if (jumpTarget != null && S.isFull(id_uf_jumptarget)) {
					msgEntry.add_Target(new MESSAGE_Entry_Target(id_uf_jumptarget, jumpTarget.get_callKey(), null, null));
				}
				if (mh.saveMessage(msgEntry)) {
					mv.add_MESSAGE(new MyE2_Info_Message(	S.ms("Nachricht an ")
															.ut(new Rec20(_TAB.user)._fill_id(r.get_ufs_dbVal(MESSAGE_PROVIDER.id_user)).get_ufs_kette(" ", USER.vorname,USER.name1))
															.t(" wurde verschickt !")
														));
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Fehler der Versendung einer Nachricht!")));
				}
				if (mv.get_bHasAlarms()) {
					break;
				}
			}
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Fehler bei der Kategorisierung der Nachricht !")));
		}
		return mv;
	}
	
	
}
