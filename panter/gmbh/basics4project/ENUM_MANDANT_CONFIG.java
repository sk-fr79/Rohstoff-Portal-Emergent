package panter.gmbh.basics4project;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_CONFIG;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * YES-NO-Schalter fuer die Benutzung im Mandanten (fuer jeden Eintrag in der enum wird automatische ein Eintrag pro mandant in der Tabelle JT_MANDANT_DECISION angelegt
 * @author martin
 *
 */
public enum ENUM_MANDANT_CONFIG {
	NOMINATIM_URL		(ENUM_CONFIG_GROUP.ROUTING, "Nominatim Server URL:","http://192.168.112.100/nominatim/search.php?",	"Adresse fuer Nominatim Server"		, 400,200, 	ENUM_FIELD_TYP.STRING, 1)
//	,GRAPHHOPPER_URL	("Graphhopper Server URL:","http://192.168.111.68:8989",				"Adresse fuer Graphhopper Server"	, 400,200, 	ENUM_FIELD_TYP.STRING, 1)
	,GEOPOINT_URL		(ENUM_CONFIG_GROUP.ROUTING, "URL zum Aufruf von Karte einer Adresse","https://www.google.com/maps/search/?api=1&query=<breite>,<laenge><width:900><height:700>"
																								,  "URL zum Aufruf einer Karte mit den hinterlegten Geokoordinaten,"
																								+  " danach durch <> umfasst, width=Breite und height=Höhe des Popupfensters"	
																																, 400,400, 	ENUM_FIELD_TYP.STRING, 1)
	,OSRM_CAR_URL			(ENUM_CONFIG_GROUP.ROUTING, "PKW Routing Server:", "http://192.168.112.100:5000", 	"Adresse fuer OSRM routing server fuer PKW"	,   400, 200, ENUM_FIELD_TYP.STRING, 1)	
	,OSRM_LKW_URL			(ENUM_CONFIG_GROUP.ROUTING, "LKW Routing Server:", "http://192.168.112.100:5000", 	"Adresse fuer OSRM routing server fuer LKW"	,   400, 200, ENUM_FIELD_TYP.STRING, 1)	
	,GEOPOINT_ROUTING_URL	(ENUM_CONFIG_GROUP.ROUTING, "URL für Kartendarstellung eine Route ", "http://www.google.de/maps/dir/'<start_breite>,<start_laenge>'/'<ziel_breite>,<ziel_laenge>'", 
							"URL für Kartendarstellung eine Route, danach durch <> umfasst, width=Breite und height=Höhe des Popupfensters ", 400,200, 	ENUM_FIELD_TYP.STRING, 3)
	
	,URL_GEO_SERVER         (ENUM_CONFIG_GROUP.ROUTING,"Server-Adresse interne Kartendarstellung","http://192.168.112.100", "Server-Adresse interne Kartendarstellung", 400,200,ENUM_FIELD_TYP.STRING,1)
	,URL_GEO_SHOW_POINTS	(ENUM_CONFIG_GROUP.ROUTING,"Webseite zur Darstellung von Punkten auf der Karte","/show_points.html","Seite zur Darstellung von Punkten auf der Karte",400,200, ENUM_FIELD_TYP.STRING,1)
	,URL_GEO_SHOW_ROUTE		(ENUM_CONFIG_GROUP.ROUTING,"Webseite zur Darstellung von Routen auf der Karte","/show_route_p.html","Seite zur Darstellung von Routen auf der Karte",400,200, ENUM_FIELD_TYP.STRING,1)
	
	,FUHRE_OHNE_ABRECH_TEXT (ENUM_CONFIG_GROUP.ABRECHNUNG,"Fuhre ohne Abrechung: Proforma-Eintrag"
								,"Keine Abrechnung: Proforma-Text"
								,"Fuhre ohne Abrechung: Proforma-Eintrag, wird bei gesetztem Schalter <HANDELSDEF_FREMDWAREN_FUHREN_MANUELL> in Fremdwarenfuhren hinterlegt"
								,400,400,ENUM_FIELD_TYP.STRING,2)
	
	,TAGE_WARNGRENZE_FAKTFRIST_GELB(ENUM_CONFIG_GROUP.ABRECHNUNG,"Fakturierungsfrist: Gelbe Warnung", "20", 	
									"Abstand in Tagen bis zum letzten Fakturierungsdatum, ab wann eine GELB-Warnung erfolgt",100,100,ENUM_FIELD_TYP.NUMERIC,1)
	
	,TAGE_WARNGRENZE_FAKTFRIST_ROT(ENUM_CONFIG_GROUP.ABRECHNUNG,"Fakturierungsfrist: Rote Warnung", "5",		
									"Abstand in Tagen bis zum letzten Fakturierungsdatum, ab wann eine ROT-Warnung erfolgt",100,100,ENUM_FIELD_TYP.NUMERIC,1)

	,TAGE_WARNGRENZE_FAHRT_PLANUNG_GELB(ENUM_CONFIG_GROUP.FAHRPLAN,"Fahrplan-Vormerkung: Gelbe Warnung", "3", 	
			"Abstand in Tagen bis zum Fahrplan-Vormerkdatum, ab wann eine GELB-Warnung erfolgt",100,100,ENUM_FIELD_TYP.NUMERIC,1)

	,TAGE_WARNGRENZE_FAHRT_PLANUNG_ROT(ENUM_CONFIG_GROUP.FAHRPLAN,"Fahrplan-Vormerkung: Rote Warnung", "2",		
			"Abstand in Tagen bis zum Fahrplan-Vormerkdatum, ab wann eine ROT-Warnung erfolgt",100,100,ENUM_FIELD_TYP.NUMERIC,1)

	
	,BASIS_VERBRAUCH_LKW_OHNE_ANHAENGER(ENUM_CONFIG_GROUP.ROUTING,"Dieselverbrauch LKW (ohne Anhänger)", "30", 	
										"Verbrauchsmittelwert eines LKW ohne Anhänger",100,100,ENUM_FIELD_TYP.NUMERIC,1)
	
	,BASIS_VERBRAUCH_LKW_MIT_ANHAENGER(ENUM_CONFIG_GROUP.ROUTING,"Dieselverbrauch LKW (mit Anhänger)", "40", 	
										"Verbrauchsmittelwert eines LKW mit Anhänger",100,100,ENUM_FIELD_TYP.NUMERIC,1)
	
	,KG_CO2_PRO_LITER_DIESEL(ENUM_CONFIG_GROUP.ROUTING,	"CO2-Emission (in kg) eines Liter Diesel", "2,65", 	
										"CO2-Emission (in kg) eines verbrauchten Liter Diesel",100,100,ENUM_FIELD_TYP.NUMERIC,1)
	
	,LAGERHALTUNG_NB_OF_ANGEZEIGT_FUHRE(ENUM_CONFIG_GROUP.PALETTENMANAGEMENT,"Anzahl der angezeigten Fuhren im Lagerbox-Modul","5",
										"Anzahl der angezeigten Fuhren im Lagerbox-Modul",100,100,ENUM_FIELD_TYP.NUMERIC, 1)
	
	,STARTDATUM_FUHREN_DIENSTLEISTUNGS_VERFAHREN("Startdatum des Dienstleistungsverfahrens/Fuhrenzentrale ", "30.10.2019", 
												 "Stichdatum des Beginns der Nutzung der Dienstleistungsprofile zur Erstellung von an die \n"
												 + "Dienstleistungsfuhren gekoppelte Dienstleistung-Bewegungspositionen. \n"
												 + "Dabei werden Fuhren ohne Abrechnung an Folgefuhren mit Abrechnung gekoppelt. \n"
												 + "Diese Folgefuhren sind dabei Pseudofuhren mit einem abgerechneten \n Warenausgang einer Dienstleistungssorte", 100,10,ENUM_FIELD_TYP.DATE, 1)
   
	,PALETTE_BARCODE_PREFIX(ENUM_CONFIG_GROUP.PALETTENMANAGEMENT,"Barcode-Präfix für Paletten","PAL","",100,4,ENUM_FIELD_TYP.STRING,1)
	
	,BOX_BARCODE_PREFIX(ENUM_CONFIG_GROUP.PALETTENMANAGEMENT, "Barcode-Präfix für Boxen","BOX","",	100,4,ENUM_FIELD_TYP.STRING,1)
	
	,FUHRE_BARCODE_PREFIX(ENUM_CONFIG_GROUP.PALETTENMANAGEMENT,"Barcode-Präfix für Fuhren","FU","",	100,4,ENUM_FIELD_TYP.STRING,1)
	
	,FORMULAR_TEXT_LISTEN_STANDARD_SCHRIFTGROESSE("Formulartextlisten - Standard-Schriftgröße","10",
													"Bei Formulartextlisten kann den Benutzern\n"
													+ "die Auswahl der Schriftgröße gesperrt werden.\n"
													+ "Falls dieses gesperrt ist, dann wird diese Schriftgröße\n"
													+ "als Standard verwendet. Mögliche Werte: 8,10,12,14"
													,100,10,ENUM_FIELD_TYP.NUMERIC, 1)
	
	,INTRASTAT_XML_MATERIALNUMMER(ENUM_CONFIG_GROUP.INTRASTAT,"Intrastat Materialnummer für das XML-Format","",	
													"Die Intrastat-Materialnummer identifiziert \n"
													+ "im Erhebungsverfahren die versendende \n"
													+ "Stelle (d.h. den Mandanten)."
											,100,100,ENUM_FIELD_TYP.STRING, 1)
	,INTRASTAT_XML_UNTERSCHEIDUNGSNUMMER(ENUM_CONFIG_GROUP.INTRASTAT,"Intrastat 3-stellige UnterscheidungsNr für das XML-Format","",	
			"Die 3-stellige Intrastat Unterscheidungsnummer für das XML-Format"
			,30,3,ENUM_FIELD_TYP.STRING, 1)
	
	
	,EMAIL_SMTP_CONNECTION_TIMEOUT(ENUM_CONFIG_GROUP.EMAILING,"eMail-Versand Verbindungstimeout beim Verbinden"
															 ,"5000"
															 ,"eMail-Versand Verbindungstimeout beim Verbinden (bedient das Setting <mail.smtp.connectiontimeout>"
															 ,100,10
															 ,ENUM_FIELD_TYP.NUMERIC, 1)
														
	,EMAIL_SMTP_TIMEOUT(ENUM_CONFIG_GROUP.EMAILING,"eMail-Versand Sendungstimeout beim Senden"
									 ,"5000"
									 ,"eMail-Versand Verbindungstimeout beim Verbinden (bedient das Setting <mail.smtp.timeout>"
									 ,100,10
									 ,ENUM_FIELD_TYP.NUMERIC, 1)
		
	,EMAIL_WIEGEKARTE_UVV_FEHLER(ENUM_CONFIG_GROUP.WIEGEKARTE,"Email-Verteiler bei UVV-Fälligkeit"
			 ,"WIEGEKARTE_UVV_FEHLER"
			 ,"Email-Verteiler, definiert im Modul e-Mail-Blöcke, getrennt mit ';'"
			 ,400,100
			 ,ENUM_FIELD_TYP.STRING, 1)
	
	,EMAIL_WIEGEKARTE_TARA_AENDERUNG(ENUM_CONFIG_GROUP.WIEGEKARTE,"Email-Verteiler bei einer Container TARA-Änderung"
			 ,"WIEGEKARTE_TARA_AENDERUNG"
			 ,"Email-Verteiler, definiert im Modul e-Mail-Blöcke, getrennt mit ';'"
			 ,400,100
			 ,ENUM_FIELD_TYP.STRING, 1)
	
	,EMAIL_WIEGEKARTE_STORNIERUNG(ENUM_CONFIG_GROUP.WIEGEKARTE,"Email-Verteiler bei einer Wiegekarten-Stornierung"
			 ,"WIEGEKARTE_STORNO"
			 ,"Email-Verteiler, definiert im Modul e-Mail-Blöcke, getrennt mit ';'"
			 ,400,100
			 ,ENUM_FIELD_TYP.STRING, 1)
	,EMAIL_ZYKLUS_WK_DOPPELTE_EINBUCHUNG(ENUM_CONFIG_GROUP.WIEGEKARTE,"Email-Verteiler bei einer Doppeltbuchung eines Containers bei der Eingangsverwiegung"
			 ,"WK_CONTAINER_DOPPELBUCHUNG"
			 ,"Email-Verteiler, definiert im Modul e-Mail-Blöcke, getrennt mit ';'"
			 ,400,100
			 ,ENUM_FIELD_TYP.STRING, 1)
	
	,WK_LAGERPLATZ_CONTAINER_MANDATORY(ENUM_CONFIG_GROUP.WIEGEKARTE,"Lagerplatz Pflicht, wenn Eigencontainer bei WE"
			 ,"Y"
			 ,"'Y'/'N': Wenn beim WE ein Eigencontainer angegeben ist, muss ein Lagerplatz ausgwählt sein, sonst nur Anlage des Zyklus und Einbuchung in Standard-Lager"
			 ,100,100
			 ,ENUM_FIELD_TYP.STRING,1)
	
	,CONT_ZYKLUS_WK_SCHUETTGUT_STARTEN(ENUM_CONFIG_GROUP.WIEGEKARTE,"Containerzyklus starten wenn Schüttgut"
			 ,"Y"
			 ,"Y - Zyklus starten / N - kein Zyklus"
			 ,100,100
			 ,ENUM_FIELD_TYP.STRING,1)
	,CONT_ZYKLUS_WK_STUECKGUT_STARTEN(ENUM_CONFIG_GROUP.WIEGEKARTE,"Containerzyklus starten wenn Stückgut"
			 ,"Y"
			 ,"Y - Zyklus starten / N - kein Zyklus"
			 ,100,100
			 ,ENUM_FIELD_TYP.STRING,1)
	,CONT_ZYKLUS_WK_SCHUETTGUT_ABSCHLIESSEN(ENUM_CONFIG_GROUP.WIEGEKARTE,"Containerzyklus abschliessen wenn Schüttgut"
			 ,"ENTLADEN"
			 ,"Mögliche Werte bei denen der der Zyklus abgeschlossen werden soll, getrennt mit ';' : 'ENTLADEN;EINLAGERN' "
			 ,200,100
			 ,ENUM_FIELD_TYP.STRING,1)
	,CONT_ZYKLUS_WK_STUECKGUT_ABSCHLIESSEN(ENUM_CONFIG_GROUP.WIEGEKARTE,"Containerzyklus abschliessen wenn Stückgut"
			 ,"ENTLADEN;EINLAGERN"
			 ,"Mögliche Werte bei denen der der Zyklus abgeschlossen werden soll, getrennt mit ';' : 'ENTLADEN;EINLAGERN' "
			 ,200,100
			 ,ENUM_FIELD_TYP.STRING,1)
	,WK_LAGERVERWIEGUNG_KENNZEICHEN(ENUM_CONFIG_GROUP.WIEGEKARTE,"Standard-Kennzeichen für Lagerverwiegung"
			 ,"LAGER"
			 ,"Kennzeichen, welches bei der Lageverwiegung automatisch gesetzt werden soll "
			 ,200,100
			 ,ENUM_FIELD_TYP.STRING,1)
	,WK_FREMDVERWIEGUNG_ARTIKEL(ENUM_CONFIG_GROUP.WIEGEKARTE,"Artikel-ID des Artikels der Fremdverwiegung"
			 ,""
			 ,"Artikelbezeichnungs-ID des Artikels der bei der Fremdverwiegung voreingestellt werden soll"
			 ,200,100
			 ,ENUM_FIELD_TYP.STRING,1)
	,WK_BEMERKUNG_BEI_LAGERWIEGUNG(ENUM_CONFIG_GROUP.WIEGEKARTE,"Eintrag in Bemerkungsfeld bei Lagerverwiegung"
			,"Material wurde in angegebenem Container zwischengelagert"
			,"Der eingetragene Wert wird bei der Auswahl des Wiegeprozesses 'Lagerverwiegung' in das Feld 'Bemerkung Wiegekarte' eingetragen."
			,200,100
			,ENUM_FIELD_TYP.STRING,1)
	;

	
	/**
	 *	Config-Gruppen-Text zur Optischen Gliederung der Konfig-Einträge  
	 *
	 */
	private enum ENUM_CONFIG_GROUP{
		ALLGEMEIN("Allgemein",1),
		ROUTING ("Geo-Codierung/Routing",2),
		ABRECHNUNG("Abrechnung",3),
		PALETTENMANAGEMENT("Palettenmanagement",4),
		INTRASTAT( "Intrastat",5),
		EMAILING("Email-Einstellungen",6),
		WIEGEKARTE("Wiegekarten",7),
		FAHRPLAN("Fahrplan",8)
		;
		
		private String Gruppentext = null;
		private int    Sortierung = 0;
		private ENUM_CONFIG_GROUP(String GroupText, int Sort) {
			Gruppentext = GroupText;
			Sortierung = Sort;
		}
		
		public String getGruppentext() {
			return Gruppentext;
		}
	};
	
	
	// 2020-11-13 Einteilung der Config in Gruppen
	private ENUM_CONFIG_GROUP config_group  = null;
	private String   		mask_text 		= null;
	private String   		enum_key_4_db 	= null;
	private int				field_length	= 100;
	private int 			max_length		= 100;
	private int 			max_rows		= 1;
	private String			helpText 		= "";
	

	private ENUM_FIELD_TYP	typ 			= ENUM_FIELD_TYP.STRING;
	private String 			default_value	= "";

	/**
	 * default-Construktor, alles in ALLGEMEIN
	 * @author manfred
	 * @date 13.11.2020
	 *
	 * @param p_mask_text
	 * @param p_default_value
	 * @param p_help_text
	 * @param i_Fieldlength
	 * @param i_max_str_length
	 * @param e_typ
	 * @param maxRows
	 */
	private ENUM_MANDANT_CONFIG(String p_mask_text, String p_default_value, String p_help_text, int i_Fieldlength, int i_max_str_length, ENUM_FIELD_TYP e_typ, int maxRows) {
		this(ENUM_CONFIG_GROUP.ALLGEMEIN,p_mask_text,p_default_value,p_help_text,i_Fieldlength,i_max_str_length,e_typ,maxRows);
	}
	
	/**
	 * Spezieller Konstruktur zur Einteilung in die Grupppen
	 * @author manfred
	 * @date 13.11.2020
	 *
	 * @param config_group
	 * @param p_mask_text
	 * @param p_default_value
	 * @param p_help_text
	 * @param i_Fieldlength
	 * @param i_max_str_length
	 * @param e_typ
	 * @param maxRows
	 */
	private ENUM_MANDANT_CONFIG(ENUM_CONFIG_GROUP p_config_group, String p_mask_text, String p_default_value, String p_help_text, int i_Fieldlength, int i_max_str_length, ENUM_FIELD_TYP e_typ, int maxRows) {
		this.config_group = 	p_config_group;
		this.mask_text = 		p_mask_text;
		this.default_value = 	p_default_value;
		this.field_length = 	i_Fieldlength;	
		this.max_length = 		i_max_str_length;
		this.typ = 				e_typ;
		this.max_rows = 		maxRows;
		this.helpText = 		p_help_text;
	}

	
	
	public String get_mask_text() {
		return mask_text;
	}

	public int get_field_length() {
		return this.field_length;
	}

	public int get_max_length() {
		return this.max_length;
	}

	public String get_default_value() {
		return this.default_value;
	}
	public String get_ENUM_KEY_4_DB() {
		if (S.isEmpty(this.enum_key_4_db)) {
			return this.name();
		}
		return enum_key_4_db;
	}

	public ENUM_FIELD_TYP get_ENUM_TYP() {
		return this.typ;
	}
	
	
	public int get_max_rows() {
		return max_rows;
	}

	public String get_helptext() {
		return this.helpText;
	}
	
	
	public String get_configGroup() {
		if (this.config_group != null){
			return this.config_group.getGruppentext();
		} else {
			return ENUM_CONFIG_GROUP.ALLGEMEIN.Gruppentext;
		}
		
	}
	
	public int get_configGroupSort() {
		if (this.config_group != null) {
			return this.config_group.Sortierung;
		} else return ENUM_CONFIG_GROUP.ALLGEMEIN.Sortierung;
	}
	
	

	public String getUncheckedValue(String id_mandant) throws myException{
		String value = "";
		MyE2_MessageVector mv = new MyE2_MessageVector();


		try {
			ownRECORD  rec_decision = new ownRECORD(new And(new vgl(MANDANT_CONFIG.enum_key, this.get_ENUM_KEY_4_DB())).and(new vgl(MANDANT_CONFIG.id_mandant,id_mandant)).s());
//			mv = check_typ(this, rec_decision.get_WERT_cUF_NN(""));
//			if(mv.get_bIsOK()){
			value = rec_decision.get_WERT_cUF_NN("");
//			}else {
//				throw new myException("Error adding decision-Record to database ! " + mv.get_MessagesAsText());
//			}
		} catch (myException e) {
//			mv = check_typ(this, this.default_value);
			if(mv.get_bIsOK()) {
				if (e.get_ErrorCode()==myException.TYPE_ERROR_SQL_QUERY_IS_EMPTY) {
					RECORDNEW_MANDANT_CONFIG  rn = new RECORDNEW_MANDANT_CONFIG();
					rn.set_NEW_VALUE_WERT(this.default_value);
					rn.set_NEW_VALUE_FELD_LAENGE(this.field_length);
					rn.set_NEW_VALUE_MAX_LAENGE	(this.max_length);
					rn.set_NEW_VALUE_TYP(this.typ.db_val());
					rn.set_NEW_VALUE_ENUM_KEY(this.get_ENUM_KEY_4_DB());
					rn.set_NEW_VALUE_ID_MANDANT(id_mandant);

					rn._add_sequencer()._add_timestamp()._add_user().SAVE_RAW(true, mv);

					if (mv.get_bHasAlarms()) {
						throw new myException("Error adding decision-Record to database ! " + mv.get_MessagesAsText());
					}
					value = this.default_value;
				} else {
					throw e;
				}
			}
		}
		return value;
	}


	public String getUncheckedValue() throws myException{
		return getUncheckedValue(bibALL.get_ID_MANDANT());
	}


	
	/**
	 * 
	 * @author martin
	 * @date 14.06.2019
	 *
	 * @param id_mandant
	 * @return checked value from database for mandant or default value from enum (when nor correct type was defined, then null)
	 */
	public String getValue(String id_mandant) {
		try {
			String val = this.getUncheckedValue();
			if (this.checkValue(val).isOK()) {
				return val;
			} else {
				return null;
			}
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @author martin
	 * @date 14.06.2019
	 *
	 * @param id_mandant
	 * @return checked value from database for mandant or default value from enum (when nor correct type was defined, then null)
	 */
	public String getCheckedValue() {
		return getValue(bibALL.get_ID_MANDANT());
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
			for (ENUM_MANDANT_CONFIG dec: ENUM_MANDANT_CONFIG.values()) {
				dec.getUncheckedValue(rm.ufs(MANDANT.id_mandant));
			}
		}

	}


	private class ownRECORD extends RECORD_MANDANT_CONFIG {

		public ownRECORD(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
			super();
			this.set_to_raw_state();
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MANDANT_CONFIG", c_ID_or_WHEREBLOCK_OR_SQL);
		}

	}


	public MyE2_MessageVector check_typ(ENUM_MANDANT_CONFIG e_config, String p_value) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if(e_config.get_ENUM_TYP() == ENUM_FIELD_TYP.NUMERIC) {
			MyBigDecimal mbd = new MyBigDecimal(p_value);
			if(! mbd.get_bOK()) {
				mv._addAlarm(e_config.name() + " ist keine Zahl");
			}
		}else if(e_config.get_ENUM_TYP() == ENUM_FIELD_TYP.DATE) {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			try {
				df.parse(p_value);
			} catch (ParseException e) {
				mv._addAlarm( e_config.name() + " ist kein Datum");
			}
		}
		return mv;
	}

	
	
	public MyE2_MessageVector checkValue(String p_value) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if(this.get_ENUM_TYP() == ENUM_FIELD_TYP.NUMERIC) {
			MyBigDecimal mbd = new MyBigDecimal(p_value);
			if(! mbd.get_bOK()) {
				mv._addAlarm(this.name() + " ist keine Zahl");
			}
		}else if(this.get_ENUM_TYP() == ENUM_FIELD_TYP.DATE) {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			try {
				df.parse(p_value);
			} catch (ParseException e) {
				mv._addAlarm( this.name() + " ist kein Datum");
			}
		}
		return mv;
	}

	
}
