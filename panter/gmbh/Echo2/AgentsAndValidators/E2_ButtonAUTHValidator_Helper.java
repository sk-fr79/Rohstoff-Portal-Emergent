package panter.gmbh.Echo2.AgentsAndValidators;

import java.sql.SQLException;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_AlarmMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.BUTTON;
import panter.gmbh.basics4project.DB_ENUMS.BUTTON_USER;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BUTTON_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailAdressChecker;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.mail.SendMail_STD;
import panter.gmbh.indep.myVectors.VectorSingle;

public class E2_ButtonAUTHValidator_Helper {

	private MyE2_MessageVector  oMV = new MyE2_MessageVector();
	
	private String cAUTHMODUL = null;
	private String cAUTHBUTTON = null;
	
	private boolean bSuperVisorDarfAlles = true;

	
	/**
	 * 20171124: weitere ausdefinierung fuer validierer, die auch fuer supervisor gelten (z.B. menue-sperren)
	 * @param authMODUL
	 * @param authBUTTON
	 * @param superVisorDarfAlles
	 */
	public E2_ButtonAUTHValidator_Helper(String authMODUL, String authBUTTON, boolean superVisorDarfAlles) {
		super();
		
		this.cAUTHMODUL = 	authMODUL;
		this.cAUTHBUTTON = 	authBUTTON;
		this.bSuperVisorDarfAlles = superVisorDarfAlles;

		boolean    bAllowed     = false;
		boolean    bButtonFound = false;
		
		// es gibt einen Eintrag, der einen benutzer für alle autentifizierungen dieser maske 
		// zulässt, dieser muss, wenn nicht gefunden, auch einmal geschrieben werden
		boolean bSuperEntryFound = false;

		//2015-05-19: debug-info
		try {
			if (bibSES.get_RECORD_USER().is_DEVELOPER_YES()) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("DEV.Info:Modul:"+this.cAUTHMODUL+":Button:"+this.cAUTHBUTTON));
			}
		} catch (myException e) {e.printStackTrace();
		}
		
		String[][] cAllowed     = bibSES.get_ALLOWEDBUTTONS();
		
		// falls der validator unsauber definiert wurde, KEIN zugriff
		if (this.cAUTHBUTTON.trim().equals("") || this.cAUTHMODUL.trim().equals("")) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Sie haben keine Berechtigung für diese Funktion:")));
		} else {
	
			
			if (cAllowed != null) {
				for (int i = 0; i < cAllowed.length; i++)
				{
					if (this.cAUTHMODUL.equals(cAllowed [i] [0]))
					{
	
						// supereintrittskarte für das modul (//2018-07-13: nur wenn nicht MODUL_KENNER_PROGRAMM_WIDE giltm um irrlauefer zu verhindern, sonst wuerden alle globalen auf einmal freigegeben)
						if (cAllowed [i] [1].equals("@@@ALL@@@") && (	!cAllowed[i][0].equals(
																		E2_MODULNAME_ENUM.MODUL.MODUL_KENNER_PROGRAMM_WIDE.get_callKey()))) 
						{
							bSuperEntryFound = true;
	
							if (cAllowed [i] [2].equals("Y"))
								bAllowed = true;
							
						}
	
						if (this.cAUTHBUTTON.equals(cAllowed [i] [1]))
						{
							bButtonFound = true;
	
							if (cAllowed [i] [2].equals("Y"))
								bAllowed = true; // dann darf er
	
						}
					}
				}
			}
	
	
			// jetzt, falls die button/modul-kombination noch nicht verwendet wurde, in die datenbank schreiben
			if (!bButtonFound) 	{
				MyDBToolBox oDB     = bibALL.get_myDBToolBox();
				
				// die ersetzung table / view muss abgeschaltet werden, da 
				// in dem where-bereich die werte JT_xxx enthalten, was fälschlicherweise ersetzt würde
				oDB.set_bErsetzungTableView(false);
				
				String		 cInsert = "INSERT INTO " + bibALL.get_TABLEOWNER() + ".JD_BUTTON (ID_BUTTON,MODULKENNER,BUTTONKENNER) VALUES (SEQ_BUTTON.NEXTVAL," + bibALL.MakeSql(this.cAUTHMODUL) + "," + bibALL.MakeSql(this.cAUTHBUTTON) + ")";
	
				String cCount = oDB.EinzelAbfrage("SELECT COUNT(*) FROM " + bibALL.get_TABLEOWNER() + ".JD_BUTTON " + " WHERE MODULKENNER=" + bibALL.MakeSql(this.cAUTHMODUL) + " AND BUTTONKENNER =" + bibALL.MakeSql(this.cAUTHBUTTON));
	
				if (cCount.trim().equals("0")) // sonst war der knopf schon drin	
				{
					if (!oDB.ExecSQL(cInsert,true))
					{
						bibALL.WriteLogInfoLine(this.getClass().getName()+"Fehler beim Schreiben eine Button/Modul-Kenner-Kombination (button)");
						bibALL.WriteLogInfoLine("      "+cInsert);
					}
					else
					{
						bibALL.WriteLogInfoLine("Zugriff in Datenbank geschrieben: " + this.cAUTHMODUL + " / " + this.cAUTHBUTTON);
					}
				}
				oDB.set_bErsetzungTableView(true);
				bibALL.destroy_myDBToolBox(oDB);
			}
	
			if (!bSuperEntryFound) {
				MyDBToolBox oDB     = bibALL.get_myDBToolBox();
				oDB.set_bErsetzungTableView(false);
				String		 cInsert = "INSERT INTO " + bibALL.get_TABLEOWNER() + ".JD_BUTTON (ID_BUTTON,MODULKENNER,BUTTONKENNER) VALUES (SEQ_BUTTON.NEXTVAL," + bibALL.MakeSql(this.cAUTHMODUL) + ",'@@@ALL@@@')";
	
				String cSQL_anzahl = "SELECT COUNT(*) FROM " + bibALL.get_TABLEOWNER() + ".JD_BUTTON " + " WHERE MODULKENNER=" + bibALL.MakeSql(this.cAUTHMODUL) + " AND BUTTONKENNER ='@@@ALL@@@'";
	
				String cCount = oDB.EinzelAbfrage(cSQL_anzahl);
	
				if (cCount.trim().equals("0")) // sonst war der knopf schon drin	
				{
					if (!oDB.ExecSQL(cInsert,true))
					{
						bibALL.WriteLogInfoLine(this.getClass().getName()+"Fehler beim Schreiben eine Button/Modul-Kenner-Kombination (all)");
						bibALL.WriteLogInfoLine("      "+cInsert);
					}
					else
					{
						bibALL.WriteLogInfoLine("Zugriff in Datenbank geschrieben: " + this.cAUTHMODUL + " / " + this.cAUTHBUTTON);
					}
				}
				oDB.set_bErsetzungTableView(true);
				bibALL.destroy_myDBToolBox(oDB);
			}

		}
		
		boolean persilSchein = bibALL.is_geschaeftsfuehrer() || (bibALL.is_SUPERVISOR() && this.bSuperVisorDarfAlles);
		
		
		//geschaeftsfuehrer darf immer alles
		if (persilSchein) {
			this.oMV.removeAllElements();
		} else {
			if (!bAllowed) {
				MyString cHelp = new MyString( "Sie haben keine Berechtigung für diese Funktion:");
				cHelp.addUnTranslated(this.cAUTHMODUL+" / "+this.cAUTHBUTTON);
				
				//validierer mit dem schalter this.bSuperVisorDarfAlles=false sind kritischer zu sehen, deshalb dort kein eMail-sende-button
				if (this.bSuperVisorDarfAlles) {
					oMV.add_MESSAGE(new sendeInfoAnDeveloper(cHelp));
				} else {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cHelp));
				}
			}
		}
	}

	
	
	public MyE2_MessageVector get_oMV() {
		return oMV;
	}

	
	
	
	private class sendeInfoAnDeveloper extends MyE2_BASIC_AlarmMessageWithAddonComponent {

		public sendeInfoAnDeveloper(MyString cmessage) {
			super(cmessage, new buttonInfo());
		}
		
	}
	

	private class buttonInfo extends MyE2_Button {
		public buttonInfo() {
			super(new MyE2_String("Sende Info"));
			this.setToolTipText(new MyE2_String("Information an die Entwicker senden").CTrans());
			this.add_oActionAgent(new ownActionSendeInfo());
		}
	}
	
	
	private class ownActionSendeInfo extends XX_ActionAgent {
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			SEL sel = new SEL("*").FROM(_TAB.user).WHERE(new vgl(USER.developer,"Y"));
			RECLIST_USER rlUsers = new RECLIST_USER(sel.s());
			
			Vector<String> vSendeProtokoll = new Vector<String>();
			Vector<String> vSendeFehlerProtokoll = new Vector<String>();
			
			String cAbsender = bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("");
			
			RECLIST_BUTTON  rlButt = new RECLIST_BUTTON(
													new SEL("*").
													FROM(BUTTON.T()).
													WHERE(new vgl(BUTTON.modulkenner,E2_ButtonAUTHValidator_Helper.this.cAUTHMODUL)).
													AND(new vgl(BUTTON.buttonkenner,E2_ButtonAUTHValidator_Helper.this.cAUTHBUTTON) ).s());

			RECLIST_BUTTON  rlButtAll = new RECLIST_BUTTON(
													new SEL("*").
													FROM(BUTTON.T()).
													WHERE(new vgl(BUTTON.modulkenner,E2_ButtonAUTHValidator_Helper.this.cAUTHMODUL)).
													AND(new vgl(BUTTON.buttonkenner,VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.__ALL__.db_val())).s());
			
			String cINSERT_BUTTON_Special = null;
			String cINSERT_USER_Spec = null;
			
			String cINSERT_BUTTON_ALL = null;
			String cINSERT_USER_ALL = null;
			
			
			if (rlButt.size()==0) {
				//anlege-statement erzeugen
				RECORDNEW_BUTTON rbnew = new RECORDNEW_BUTTON(); 
				rbnew.set_NEW_VALUE_MODULKENNER(E2_ButtonAUTHValidator_Helper.this.cAUTHMODUL);
				rbnew.set_NEW_VALUE_BUTTONKENNER(E2_ButtonAUTHValidator_Helper.this.cAUTHBUTTON);
				cINSERT_BUTTON_Special=rbnew.get_InsertSQLStatementWith_Id_Field(true, true);
				
				RECORDNEW_BUTTON_USER  rbu_new = new RECORDNEW_BUTTON_USER();
				rbu_new.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(BUTTON_USER.id_button.fieldName(),"SEQ_BUTTON.CURRVAL");
				rbu_new.set_NEW_VALUE_ID_USER(bibALL.get_RECORD_USER().get_ID_USER_cUF());
				cINSERT_USER_Spec = rbu_new.get_InsertSQLStatementWith_Id_Field(true, true);
				
			} else {
				RECORDNEW_BUTTON_USER  rbu_new = new RECORDNEW_BUTTON_USER();
				rbu_new.set_NEW_VALUE_ID_BUTTON(rlButt.get(0).get_ID_BUTTON_cUF());
				rbu_new.set_NEW_VALUE_ID_USER(bibALL.get_RECORD_USER().get_ID_USER_cUF());
				cINSERT_USER_Spec = rbu_new.get_InsertSQLStatementWith_Id_Field(true, true);
			}
			if (rlButtAll.size()==0) {
				//anlege-statement erzeugen
				RECORDNEW_BUTTON rbnew = new RECORDNEW_BUTTON(); 
				rbnew.set_NEW_VALUE_MODULKENNER(E2_ButtonAUTHValidator_Helper.this.cAUTHMODUL);
				rbnew.set_NEW_VALUE_BUTTONKENNER(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.__ALL__.db_val());
				cINSERT_BUTTON_ALL=rbnew.get_InsertSQLStatementWith_Id_Field(true, true);
				
				RECORDNEW_BUTTON_USER  rbu_new = new RECORDNEW_BUTTON_USER();
				rbu_new.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(BUTTON_USER.id_button.fieldName(),"SEQ_BUTTON.CURRVAL");
				rbu_new.set_NEW_VALUE_ID_USER(bibALL.get_RECORD_USER().get_ID_USER_cUF());
				cINSERT_USER_ALL = rbu_new.get_InsertSQLStatementWith_Id_Field(true, true);
			} else {
				RECORDNEW_BUTTON_USER  rbu_new = new RECORDNEW_BUTTON_USER();
				rbu_new.set_NEW_VALUE_ID_BUTTON(rlButtAll.get(0).get_ID_BUTTON_cUF());
				rbu_new.set_NEW_VALUE_ID_USER(bibALL.get_RECORD_USER().get_ID_USER_cUF());
				cINSERT_USER_ALL = rbu_new.get_InsertSQLStatementWith_Id_Field(true, true);
			}
			
			
			
//			if (rlButt.get_vKeyValues().size()==1) {
				
				try
				{
					StringBuffer Meldung = new StringBuffer("Validierung: Modul:"+E2_ButtonAUTHValidator_Helper.this.cAUTHMODUL+" ... BUTTON: "+E2_ButtonAUTHValidator_Helper.this.cAUTHBUTTON+"\n\n");
					Meldung.append("\n");
					Meldung.append("Wenn Sie den speziellen Benutzerzugriff zulassen möchten, dann folgende Statements ausführen:\n\n");
					if (S.isFull(cINSERT_BUTTON_Special)) {
						Meldung.append(bibDB.generate_plain_sql_Statement(cINSERT_BUTTON_Special)+"\n");
					}
					Meldung.append(bibDB.generate_plain_sql_Statement(cINSERT_USER_Spec)+"\n");
					
//2018-07-13: keine statements zur erfassung alle buttons eines moduls					
//					Meldung.append("\n");
//					Meldung.append("\n");
//					Meldung.append("Wenn Sie den speziellen Benutzerzugriff auf alle Buttons des Moduls erlauben möchten, dann folgende Statements ausführen:\n\n");
//					if (S.isFull(cINSERT_BUTTON_ALL)) {
//						Meldung.append(bibDB.generate_plain_sql_Statement(cINSERT_BUTTON_ALL)+"\n");
//					}
//					Meldung.append(bibDB.generate_plain_sql_Statement(cINSERT_USER_ALL)+"\n");
					
					
//				Meldung += "INSERT INTO "+_DB.BUTTON_USER+"("+
//											_DB.BUTTON_USER$ID_BUTTON_USER+","+
//											_DB.BUTTON_USER$ID_BUTTON+","+
//											_DB.BUTTON_USER$ID_USER+","+
//											_DB.BUTTON_USER$ID_MANDANT+","+
//											_DB.BUTTON_USER$GEAENDERT_VON+","+
//											_DB.BUTTON_USER$LETZTE_AENDERUNG+") VALUES("+
//											_DB.BUTTON_USER$$SEQ_NEXT+","+
//											rlButt.get(0).get_ID_BUTTON_cUF()+","+
//											bibALL.get_RECORD_USER().get_ID_USER_cUF()+","+
//											bibALL.get_ID_MANDANT()+","+
//											"'AUTOMAT',"+
//											"SYSDATE)"+
//											"\n\n\n"+
//											bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cUF_NN("<signatur>");
												
					
					
					if (new MailAdressChecker(cAbsender).isOK()) {
						VectorSingle vEmailsZuInformieren = new VectorSingle();
						for (RECORD_USER recUser: rlUsers) {
							if (vEmailsZuInformieren.contains(recUser.get_EMAIL_cUF_NN(""))) {
								continue;
							}
							vEmailsZuInformieren.add(recUser.get_EMAIL_cUF_NN(""));
							if (new MailAdressChecker(recUser.get_EMAIL_cUF_NN("")).isOK()) {
								try {
									SendMail_STD mail = new SendMail_STD(cAbsender, recUser.get_EMAIL_cUF_NN(""), null,null, "Fehler/Zugang verweigert: "+bibALL.get_cDateTimeNOWInverse(), Meldung.toString(), null);
									mail.sendViaStandardMail();
									vSendeProtokoll.add("Erfolgreich an :"+recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>")+" ("+recUser.get_EMAIL_cUF_NN("")+")");
								} catch (MailException e) {
									e.printStackTrace();
									vSendeFehlerProtokoll.add("Fehler beim Aufbau eMail:"+recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>"));
								}
							} else {
								vSendeFehlerProtokoll.add("Fehlerhafte oder keine Mailadresse:"+recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>"));
							}
						}
						
						if ((vSendeFehlerProtokoll.size()+vSendeProtokoll.size())==0) {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Keine berechtigten Empfänger gefunden !")));
						} else {
						
							for (String c_ok: vSendeProtokoll) {
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(c_ok,false)));
							}
							for (String c_err: vSendeFehlerProtokoll) {
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(c_err,false)));
							}
						}
						
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben noch keine oder keine korrekte eMailadresse in Ihrem Benutzer-Profil hinterlegt!")));
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					throw new myException(e.getLocalizedMessage());
				}
			
//			} else {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(
//																	S.ut(E2_ButtonAUTHValidator_Helper.this.cAUTHMODUL),
//																	S.t("/"),
//																	S.ut(E2_ButtonAUTHValidator_Helper.this.cAUTHBUTTON),
//																	S.t(" konnte nicht identifiziert werden!"))));
//			}
			
		}
	}
	
	
	
}
