package panter.gmbh.basics4project;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_DECISION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * YES-NO-Schalter fuer die Benutzung im Mandanten (fuer jeden Eintrag in der enum wird automatische ein Eintrag pro mandant in der Tabelle JT_MANDANT_DECISION angelegt
 * @author martin
 *
 */
public enum ENUM_MANDANT_DECISION {
	USE_NEW_INFO_BUTTON("Neuen, optimierten ADRESS-INFO-Button benutzen",false)
	,USE_MAHNUNG_BUTTON_NEU("Neue Mahnung verwenden", false)
	,USE_MAHNUNG_BUTTON_ALT("Alte Mahnung verwenden", false)
	,CONVERT_HANDBUCHUNG_LAGER_TO_ATOM("Alle Handbuchungen die im alten Fuhrenlager gebucht wurden ins ATOM übernehmen",false)
	,CONVERT_LAGERBESTANDSERFASSUNG_TO_ATOM("Inventurbuchungen (Bem=LAGERBESTANDSERFASSUNG), die im alten Fuhrenlager gesetzt wurden ins ATOM übernhemen",true)
	,USE_NEW_KONTRAK_TYP("Neues Kontrakt Modul (incl. Fixierung) verwenden", false)
	,FIELDSIZE_4_NEW_BEWEGUNGSMODEL("Neues Bewegungsmodul: kleine Eingabefelder verwenden", false)
	,AUSGANGSAVV_IN_FUHRE_VERWENDEN("In Fuhren den Verkaufs-AVV-Code berücksichtigen",false)
	,USE_WEBSEITE_WAEHRUNGSKURS_KNOPF("Kontrakte: Fixierungsvariante: In der Position Währungskurs-Popup verwenden", false)
	,USE_BUTTON_CONNECT_WK_FUHRE("Wiegekarten-Fuhre-Verknüpfung: Nachträgliche Verknüpfung von Fuhre zu Wiegekarte ermöglichen",false)
	,AH7_USE_STEUERTABELLE("ANHANG 7 (1): Für Anhang7-Drucke die steuertabellenbasierte Validierung benutzen", false
			,	"Wenn der Schalter gesetzt ist, dann wird beim Erstellen eines AH7-Dokuments geprüft, ob "+
				"in der Tabelle AH7_STEUERDATEI ein aktiver und korrekter Eintrag zu der Transport-Relation vorhanden ist, der die "+
				"eingedruckten Adressen im AH7 spezifiziert. Ist der Datensatz freigegeben, dann wird das steuertabellen-basierte Formular verwendet."
				+ "Ist der Datensatz nicht aktiv, erfolgt eine Fehlermeldung. Falls es noch keinen Datensatz gibt, dann ist der Schalter ANHANG 7 (2) "
				+ "entscheidend: Falls gesetzt, dann wird das klassische AH7-Formular benutzt, falls nicht, dann wird ein Eintrag in die Steuertabelle"
				+ "geschrieben und muss vor Benutzung qualifiziert und aktivert werden.")
	
//	,AH7_AUTO_CREATE_IF_NOT_FOUND_AND_SENDMESSAGE_IF_NOT_VALID("ANHANG 7: Fehlende AH7-Relationen erzeugen und melden, Fehlerhafte vorhandene melden", false
//				,"Wird bei einem AH7-Druck im neuen Verfahren kein Steuertabelleneintrag gefunden, dann einen leeren Satz erzeugen und eine Meldung an den Verteiler senden. Falls ein "
//				+ "fehlerhafter Satz gefunden wird, dann melden.")
	
//	,AH7_SENDMESSAGE_WHEN_EXISTING_NOT_VALID("ANHANG 7: Nicht benutzbare, aber existente AH7-Steuereinträge melden ", false
//			,	"Wird bei einem AH7-Druck im neuen Verfahren ein unvollständiger Steuertabelleneintrag gefunden, dann eine Meldung an den Verteiler senden.")
	
	,AH7_USE_OLD_IF_NO_RULE_EXISTING("ANHANG 7 (2): Falls bei der steuertabellenbasierten Validierung keine Regel gefunden wird, alte Systematik benutzen", false
			,	"Wenn der Schalter gesetzt ist, dann wird beim Erstellen eines AH7-Dokuments geprüft, ob "+
					"in der Tabelle AH7_STEUERDATEI ein aktiver und korrekter Eintrag zu der Transport-Relation vorhanden ist, der die "+
					"eingedruckten Adressen im AH7 spezifiziert. Ist der Datensatz freigegeben, dann wird das steuertabellen-basierte Formular verwendet."
					+ "Ist der Datensatz nicht aktiv, erfolgt eine Fehlermeldung. Falls es noch keinen Datensatz gibt, dann ist der Schalter ANHANG 7 (2) "
					+ "entscheidend: Falls gesetzt, dann wird das klassische AH7-Formular benutzt, falls nicht, dann wird ein Eintrag in die Steuertabelle"
					+ "geschrieben und muss vor Benutzung qualifiziert und aktivert werden.")
	
	
	,AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF("ANHANG 7: AH7-Druck-Steuertabelleneinträge: Nur ein Geschäftsführer darf die AH7-Druck-Steuertabelleneinträge aktivieren !", false
			,	"Wenn der Schalter gesetzt ist, dann wird beim Bearbeiten von AH7-Profilen und AH7-Druck-Steuertabellen-Einträgen "+
				"die Änderung auf den Status AKTIVIERT bei manuellen Vorgängen nur Benutzern mit Status Geschäftsführer erlaubt."+
				"Wenn der Schalter nicht gesetzt ist, greift eine Standard-Validierung !")
	,USE_WIEGEKARTE_CONTAINER("Container-Erfassung bei der Wiegekarte zulassen",false
			,	"Wenn der Schalter gesetzt ist, wird in der Wiegekarte der Karteireiter Container angezeigt, "	+ 
				"und es können die gewogenen Container erfasst werden.")	
	,USE_GEOPOINT_SHOW_ROUTE("Kartendarstellung einer Route zulassen ",false,"Kartendarstellung einer Route zulassen ")
	,USE_GEOPOINT_SHOW("Kartendarstellung einer Geokoordinate zulassen ",false,"Kartendarstellung einer Geokoordinate zulassen ")
	
	// wenn die Route angezeigt werden soll, dann auf dem internen Mapserver
	,GEOPOINT_SHOW_ROUTE_INTERNAL("Kartendarstellung einer Route auf internem Mapserver",false,"Kartendarstellung einer Route auf internem Mapserver")
	
	,WAAGE_AUTO_CAPTURE("Automatische Bild-Erfassung beim Wiegevorgang (Modulname: CAPTURE_WAAGE_AUTOMATIK) ",false,"Automatische Bild-Erfassung beim Wiegevorgang (Modulname: CAPTURE_WAAGE_AUTOMATIK)")

	,ADD_AUTOMATIC_DELETE_AND_STORNO_INFOS("In den Listen als Tooltips der Checkboxen die Lösch- und Storno-Standardfelder einblenden (wenn in der Basic-Abfrage vorhanden)",true)
	,ADD_HISTORY_INFORMATION_ON_LIST_CHECKBOX("In den Listen als Tooltips der Checkboxen zusaetzlich die Statistik-Daten <Erstellt von/am> und <Letzte Änderung von/am> einblenden ", false)
	,USE_GROUPED_TAX_SELECTOR("In den Masken, wo eine Steuer-(TAX-) auswahl benutzt wird, die Gruppierte Popup-Variante benutzen ", false)
	,NACHRICHT_BUTTON_SET_ALL_AS_READ("Button 'Ich habe alles gelesen' in der Nachrichten-Anzeige anzeigen.",false)
	,NACHRICHT_BUTTON_SET_ALL_AS_NORMAL("Button 'Ich möchte alles später lesen' in der Nachrichten-Anzeige anzeigen.",false)
	,SANKTIONS_CHECK_ADRESS_AT_SAVE("Beim Speichern von Adressen automatisch eine Sanktionsprüfung durchführen", true)
	,SANKTIONS_CHECK_ADRESS_AT_LOAD_IN_ALL_MODULES("Beim Laden einer Adresse in einer Bewegungsmaske automatisch eine Sanktionsprüfung ausführen", true)
	,SANKTIONS_CHECK_ADRESS_EXCLUDE_ADRESS_MANDANT("Bei allen Sanktionschecks die Adresse des Mandanten ignorieren", true)
	,LAGER_ABZUG_VON_WA_NACH_WE_UEBERNEHMEN("Lagerabzüge von der Ladungsseite ins Lager übernehmen, wenn Lagerseitig keine Abzugsliste definiert ist",false)
	,ZAUBERSTAB_STEUERREGEL_TEST_AUSBLENDEN("In der Fuhrenzentrale (Listenansicht) den Test-Zauberstab zum Prüfen der Steuerregel/Handelsdefinition ausblenden (für Geschäftsführer bleibt der Schalter)",false)
	,FUHRENMASKE_ALTE_STEUER_AUSBLENDEN("In der Fuhrenzentrale (Maskenansicht) die alte Steuerselektion ausschalten",false)
	,HANDELSDEF_FREMDWAREN_FUHREN_MANUELL("Steuerfindung: Fuhre ohne Abrechnung mit manuellem Wert besetzen",false,
						"Wird bei der Steuerfindung in der Maske festgestellt, daß die Fuhre eine"
						+ " Fremdwarenfuhre ist und dieser Schalter ist true, "
						+ "dann keine Steuerdefinition suchen, sondern 0/Konstanten in die Steuerfelder setzen")
	
	
	,RECHNUNG_GUTSCHRIFT_POSITIONEN_MONATSGLEICH("Beim Zusammenstellen der Rechungsbelege nur gleiche Monate erlauben",false, 
						  "Wird dieser Schalter gesetzt, dann können aus den freien Positionen nur noch Positionen zusammengefaßt werden,"
						+ " deren Leistungsdatum im gleichen Moant liegt.")
	
	,DIENSTLEISTUNGS_VERFAHREN_AKTIV("Dienstleistungsverfahrens/Fuhrenzentrale nutzen",false, "Nutzung der Dienstleistungsprofile zur Erstellung von an die\n "
												 + "Dienstleistungsfuhren gekoppelte Dienstleistung-Bewegungspositionen. \n"
												 + "Dabei werden Fuhren ohne Abrechnung an Folgefuhren mit Abrechnung gekoppelt.\n "
												 + "Diese Folgefuhren sind dabei Pseudofuhren mit einem abgerechneten Warenausgang eine Dienstleistungssorte.\n"
												 + "Dieser Schalter aktiviert dieses Verfahren !")
	
	,MARKIERUNG_AVV_IN_FUHRE_ROT_GELB("Gefahrgutmarkierung in Fuhre gestuft rot/gelb",true,	"Wenn Schalter aus, dann wird der AVV-Code in der Fuhrenmaske in rot markiert, \n "
																							+ " wenn der AVV-Code in der Fuhre gefährlich ist.\n"
																							+ " Ist der Schalter an, dann wird der AVV-Code rot markiert, wenn sowohl der AVV-Code \n"
																							+ " gefährlich ist (*) , als auch der OECD-Code und Basel-Code gefährlich ist (A).\n"
																							+ " Ist der AVV-Code ungefährlich, aber der BASEL- oder OECD-Code in der "
																							+ " Fuhre ist gefährlich (A), dann wird der Label gelb!")
	
	,WORKFLOW_JUMP_TO_SIMPLE_WORKFLOW_LIST("Workflow: Sprung zur Vereinfachten Laufzettel-Liste ",false,"Wenn der Schalter gesetzt ist, dann wird bei den allgemeinen Sprung-Buttons auf die vereinfachte Workflow Liste gesprungen.")
	
	,FORMULAR_TEXT_LISTEN_SCHRIFTGROESSE_ERLAUBT("Formulartextlisten - Änderung Schriftgröße erlauben", false, 
																				"Bei Formulartextlisten kann den Benutzern\n"
																			+ 	"damit Auswahl der Schriftgröße erlaubt werden.")
	,FORMULAR_TEXT_LISTEN_FETTDRUCK_ERLAUBT("Formulartextlisten - Fettdruck erlauben", false, 
																				"Bei Formulartextlisten kann den Benutzern\n"
																			+ 	"damit die Auswahl von Fettdruck erlaubt werden.")
	,FORMULAR_TEXT_LISTEN_KURSIVDRUCK_ERLAUBT("Formulartextlisten - Kursivdruck erlauben", false, 
																				"Bei Formulartextlisten kann den Benutzern\n"
																			+ 	"damit die Auswahl von Kursivdruck erlaubt werden.")
	,FORMULAR_TEXT_LISTEN_UNTERSTREICHUNG_ERLAUBT("Formulartextlisten - Unterstreichung erlauben", false, 
																				"Bei Formulartextlisten kann den Benutzern\n"
																			+ 	"damit die Auswahl von Unterstreichung erlaubt werden.")
	,WAAGE_ERLAUBE_HANDWIEGUNG("Handverwiegung erlauben",false,"Erlaubt die allgemeine Handverwiegung.\n"
																			+   "(Zusätzlich zur Tara-Übernahme)")
	,WIEGEKARTE_DRUCKE_STORNO_AUTO("Wiegekarte bei Stornierung automatisch drucken",true, "Es wird eine Wiegekarte automatisch gedruckt, wenn eine Verwiegung storniert wird.")
	
	,WIEGEKARTE_DRUCKE_ETIKETT_AUTO("Wiegekarten-Etiketten bei Druck der Wiegekarte automatisch drucken",false,"Die Etiketten werden automatisch gedruckt, wenn die Wiegekarte gedruckt wird.")
	
	,INTRASTAT_XML_FORMAT("Intrastat im XML-Format erzeugen",false,"Die Intrastat im neuen XML-Format erzeugen. "
																			+   "\nDazu müssen die XML-Intrastat-Kenner in den Einstellungen gesetzt werden.")
	
	,ERLAUBE_VERSAND_GESPERRTER_RECH_GUT_BELEGE("Original-Email-Versand von Rechung/Gutschrift-Belegen trotz Sperre erlauben!",false,"Wenn im Kundenstamm die Sperrhaken Rechnung/Gutschrift gesperrt\n"
			+ "gesetzt sind, dann werden die Original-Belege erzeugt und lokal bereitgestellt, aber es wird im Falle eMail-Versand keine eMail angelegt.\n"
			+ "Mit diesem Schalter (auf an) wird trotz Sperrhaken eine eMail erzeugt.")
	
	,RECHNUNG_GUTSCHRIFT_KOPIE_SET_MENGE_TO_0("Bei Kopie von Rechnung/Gutschrift die Menge auf 0 setzen",true)
	
	;

	private String   mask_text = null;
	private boolean  value_standard = false;
	private String   enum_key_4_db = null;
	private String   toolTipText = null;
	
	private static String Addon4ENUM_MANDANT_DECISIONStore = "@@@#liwerfnjikvhgijresnbkjvhtvurjankjchefuj#";
	
	
	private ENUM_MANDANT_DECISION(String p_mask_text, boolean p_valueStandard) {
		this.mask_text = 		p_mask_text;
		this.value_standard = 	p_valueStandard;
	}

	private ENUM_MANDANT_DECISION(String p_mask_text, boolean p_valueStandard, String p_toolTips) {
		this.mask_text = 		p_mask_text;
		this.value_standard = 	p_valueStandard;
		this.toolTipText = 	p_toolTips;
	}

	
	public String get_mask_text() {
		return mask_text;
	}

	
	public boolean valueStandard() {
		return value_standard;
	}

	
	public String get_ENUM_KEY_4_DB() {
		if (S.isEmpty(this.enum_key_4_db)) {
			return this.name();
		}
		return enum_key_4_db;
	}
	
	
	public boolean is_YES(String id_mandant) throws myException {
		boolean b_rueck = false;
		
		try {
			ownRECORD  rec_decision = new ownRECORD(new And(new vgl(MANDANT_DECISION.enum_key, this.get_ENUM_KEY_4_DB())).and(new vgl(MANDANT_DECISION.id_mandant,id_mandant)).s());
			b_rueck = rec_decision.is_YES_NO_YES();
		} catch (myException e) {
			if (e.get_ErrorCode()==myException.TYPE_ERROR_SQL_QUERY_IS_EMPTY) {
				//hier einen satz anlegen
				RECORDNEW_MANDANT_DECISION  rn = new RECORDNEW_MANDANT_DECISION();
				rn.set_to_raw_state();
				rn.set_NEW_VALUE_ENUM_KEY(this.get_ENUM_KEY_4_DB());
				rn.set_NEW_VALUE_YES_NO(this.value_standard?"Y":"N");
				rn.set_NEW_VALUE_ID_MANDANT(id_mandant);
				MyE2_MessageVector mv = new MyE2_MessageVector();

				rn._add_sequencer()._add_timestamp()._add_user().SAVE_RAW(true, mv);
				
				if (mv.get_bHasAlarms()) {
					throw new myException("Error adding decision-Record to database !");
				}
				b_rueck = this.value_standard;
			} else {
				throw e;
			}
		}
		
		return b_rueck;
	}
	

	/**
	 * liest die werte aus der session, nur einmal aus der datenbank
	 * @param id_mandant
	 * @return
	 * @throws myException
	 */
	public boolean is_YES_FromSession(String id_mandant) throws myException {
		boolean b_rueck = false;
		
		try {
			if (ENUM_MANDANT_DECISION.hasSessionValue(this, id_mandant)) {
				RECORD_MANDANT_DECISION rec = ENUM_MANDANT_DECISION.getSessionValue(this, id_mandant);
				b_rueck = rec.is_YES_NO_YES();
			} else {
				ownRECORD  rec_decision = new ownRECORD(new And(new vgl(MANDANT_DECISION.enum_key, this.get_ENUM_KEY_4_DB())).and(new vgl(MANDANT_DECISION.id_mandant,id_mandant)).s());
				ENUM_MANDANT_DECISION.setSessionValue(this, id_mandant,rec_decision);
				b_rueck = rec_decision.is_YES_NO_YES();
			}
			
		} catch (myException e) {
			if (e.get_ErrorCode()==myException.TYPE_ERROR_SQL_QUERY_IS_EMPTY) {
				//hier einen satz anlegen
				RECORDNEW_MANDANT_DECISION  rn = new RECORDNEW_MANDANT_DECISION();
				rn.set_to_raw_state();
				rn.set_NEW_VALUE_ENUM_KEY(this.get_ENUM_KEY_4_DB());
				rn.set_NEW_VALUE_YES_NO(this.value_standard?"Y":"N");
				rn.set_NEW_VALUE_ID_MANDANT(id_mandant);
				MyE2_MessageVector mv = new MyE2_MessageVector();

				rn._add_sequencer()._add_timestamp()._add_user().SAVE_RAW(true, mv);
				
				if (mv.get_bHasAlarms()) {
					throw new myException("Error adding decision-Record to database !");
				}
				b_rueck = this.value_standard;
			} else {
				throw e;
			}
		}
		
		return b_rueck;
	}

	
	public boolean is_YES_FromSession() throws myException {
		return is_YES_FromSession(bibALL.get_ID_MANDANT());
	}
	
	public boolean is_NO_FromSession() throws myException {
		return !is_YES_FromSession(bibALL.get_ID_MANDANT());
	}

	public boolean is_NO_FromSession(String id_mandant) throws myException {
		return !is_YES_FromSession(id_mandant);
	}

	
	
	public boolean is_NO(String id_mandant) throws myException {
		return !this.is_YES(id_mandant);
	}

	
	public boolean is_YES() throws myException {
		return is_YES(bibALL.get_ID_MANDANT());
	}

	public boolean is_NO() throws myException {
		return is_NO(bibALL.get_ID_MANDANT());
	}
	
	
	public String get_toolTipText() {
		return S.NN(toolTipText,this.mask_text);
	}
	

	
	/**
	 * alle datensaetze fuer alle mandanten schreiben
	 * @throws myException 
	 */
	public static void write_all_4_all() throws myException {
		
		RECLIST_MANDANT  rl = new RECLIST_MANDANT();
		rl.set_to_raw_state();
		rl.set_cQueryString(new SEL(_TAB.mandant).FROM(_TAB.mandant).s());
		rl.REFRESH();
		
		for (RECORD_MANDANT rm: rl) {
			for (ENUM_MANDANT_DECISION dec: ENUM_MANDANT_DECISION.values()) {
				dec.is_YES(rm.ufs(MANDANT.id_mandant));
			}
		}
		
	}
	
	
	private class ownRECORD extends RECORD_MANDANT_DECISION {

		public ownRECORD(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
			super();
			this.set_to_raw_state();
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MANDANT_DECISION", c_ID_or_WHEREBLOCK_OR_SQL);
//			DEBUG._print("###sessionquery###: "+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		
	}
	
	//2018-05-30: weitere zugriffsstruktur: die datenbank-setting von ENUM_MANDANT_DECISION nur einmal lesen (statt normalerweise direkt)
	/**
	 * 
	 * @param PARAMETES_IN_CREATE_SESSION
	 * @return
	 */
	
	public static RECORD_MANDANT_DECISION getSessionValue(ENUM_MANDANT_DECISION key, String id_mandant) {
		return (RECORD_MANDANT_DECISION)bibSES.getSessionValue(key.toString()+ENUM_MANDANT_DECISION.Addon4ENUM_MANDANT_DECISIONStore+"@MANDANT:"+id_mandant);
	}
	/**
	 * 
	 * @param PARAMETES_IN_CREATE_SESSION
	 * @param o
	 */
	public static void setSessionValue(ENUM_MANDANT_DECISION key, String id_mandant, RECORD_MANDANT_DECISION o) {
		bibSES.setSessionValue(key.toString()+ENUM_MANDANT_DECISION.Addon4ENUM_MANDANT_DECISIONStore+"@MANDANT:"+id_mandant, o);
	}

	public static boolean hasSessionValue(ENUM_MANDANT_DECISION key, String id_mandant) {
		return bibSES.hasSessionValue(key.toString()+ENUM_MANDANT_DECISION.Addon4ENUM_MANDANT_DECISIONStore+"@MANDANT:"+id_mandant);
	}

	
	//alle sessionwerte alle enumMandant-decision loeschen
	public static void clearAllValuesFrom_ENUM_MANDANT_DECISION() {
		try {
			RecList21 reclistMand = new RecList21(_TAB.mandant)._fillWithAll();
			
			for (Rec21 rm: reclistMand) {
				for (ENUM_MANDANT_DECISION en: ENUM_MANDANT_DECISION.values()) {
					bibSES.removeSessionValue(en.toString()+ENUM_MANDANT_DECISION.Addon4ENUM_MANDANT_DECISIONStore+"@MANDANT:"+rm.getUfs(MANDANT.id_mandant));
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
	}

}
