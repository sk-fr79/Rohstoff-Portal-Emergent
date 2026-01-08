package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.E2_ColorSelect_Button;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PasswordField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB__SPECIAL_UPLOAD_IMAGE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class USER__MASK_ComponentMAP extends E2_ComponentMAP 
{
	
//	//2011-12-06: unterschrifts-bild zum hochladen
//	public static String    HASHKEY_UNTERSCHRIFT = "HASHKEY_UNTERSCHRIFT";
	
	public static String    HASHKEY_SELECT_OK_COLOR = 		"HASHKEY_SELECT_OK_COLOR";
	public static String    HASHKEY_SELECT_WARN_COLOR = 	"HASHKEY_SELECT_WARN_COLOR";
	public static String    HASHKEY_SELECT_ERROR_COLOR = 	"HASHKEY_SELECT_ERROR_COLOR";
	
	public USER__MASK_ComponentMAP() throws myException
	{
		super(new USER__MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_DropDownSettings ddSprache = new E2_DropDownSettings( "JD_SPRACHE", "BEZEICHNUNG", "ID_SPRACHE", "ISTSTANDARD", true);
		E2_DropDownSettings ddUserGroup = new E2_DropDownSettings( "JD_USERGROUP", "GRUPPE", "ID_USERGROUP", "IST_STANDARD", true);
		E2_DropDownSettings ddMandant = new E2_DropDownSettings( "JD_MANDANT", "NVL(NAME1,'')||' '||NVL(NAME2,'')", "ID_MANDANT", null, true);

		//String[][] todoSuper = new String[3][2];
		String[][] todoSuper = {{"-",""},{"Keine Sonderrechte","0"},{"Besitzer ändern","1"},{"Alles ändern","2"}};
		
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER")), new MyE2_String("ID_USER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EIGENDEF_SCHRIFTGROESSE"),true,100,5,false), new MyE2_String("EIGENDEF_SCHRIFTGROESSE"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_SPRACHE"),ddSprache.getDD(),false), new MyE2_String("Sprache"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_USERGROUP"),ddUserGroup.getDD(),false), new MyE2_String("Benutzergruppe"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_MANDANT"),ddMandant.getDD(),false), new MyE2_String("Mandant"));

		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("HAT_FAHRPLAN_BUTTON"),    new MyE2_String("Fahrplan-Erfasser"),new MyE2_String("Benutzer ist Fahrplan-Erfasser")), new MyE2_String("HAT_FAHRPLAN_BUTTON"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV"),					new MyE2_String("Aktiv"),new MyE2_String("Benutzer ist aktiv")), new MyE2_String("AKTIV"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_FAHRER"),				new MyE2_String("Fahrer"),new MyE2_String("Benutzer ist Fahrer")), 		new MyE2_String("Fahrer"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_VERWALTUNG"),			new MyE2_String("Verwaltung"),new MyE2_String("Benutzer ist Verwaltungsmitarbeiter")), new MyE2_String("Verwaltungsbenutzer"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("FENSTER_MIT_SCHATTEN"),	new MyE2_String("Fenster-Schatten"),new MyE2_String("Fenster-Schatten anzeigen")), new MyE2_String("Fenster mit Schatteneffekt"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_SUPERVISOR"),			new MyE2_String("Supervisor"),new MyE2_String("Benutzer ist Systemadministrator/Supervisor")), new MyE2_String("IST_SUPERVISOR"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("TEXTCOLLECTOR"),			new MyE2_String("Textsammler"),new MyE2_String("Bei diesem Benutzer werden die Übersetzungstexte gesammelt")), new MyE2_String("TEXTCOLLECTOR"));
		//2011-06-07: neues feld: geschaeftsfuehrer
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("GESCHAEFTSFUEHRER"),		new MyE2_String("Geschäftsführer"),new MyE2_String("Benutzer hat Geschäftsführer-Privilegien")), new MyE2_String("Geschäftsführer"));
		//2013-01-20: mengefreigabe und preisfreigabe-berechtigung in der fuhre
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(RECORD_USER.FIELD__MENGENABSCHLUSS_FUHRE_OK), 	new MyE2_String("Mengenabschluss"),	new MyE2_String("Benutzer hat das Recht, in der Fuhre eine Mengenfreigabe zu erteilen !")), 	new MyE2_String("Mengenabschluss"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(RECORD_USER.FIELD__PREISABSCHLUSS_FUHRE_OK), 	new MyE2_String("Preisabschluss"),	new MyE2_String("Benutzer hat das Recht, in der Fuhre eine Preisfreigabe zu erteilen !")),	 	new MyE2_String("Preisabschluss"));
		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LAUFZEIT_SESSION"),true,100,6,false), new MyE2_String("LAUFZEIT_SESSION"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("VERTICAL_OFFSET_MASKTABS"),true,100,3,false), new MyE2_String("VERTICAL_OFFSET_MASKTABS"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EMAIL"),true,500,60,false), new MyE2_String("EMAIL"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("MAIL_SIGNATUR"),500,15), new MyE2_String("MAIL_SIGNATUR"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANREDE"),true,350,50,false), new MyE2_String("Anrede"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("VORNAME"),true,350,50,false), new MyE2_String("VORNAME"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NAME1"),true,350,30,false), new MyE2_String("NAME1"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NAME2"),true,350,30,false), new MyE2_String("NAME2"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NAME3"),true,350,30,false), new MyE2_String("NAME3"));

		this.add_Component(new MyE2_DB_TextField(		oFM.get_("NAME"),		true,	100,50,false), new MyE2_String("NAME"));
		this.add_Component(new MyE2_DB_PasswordField(	oFM.get_("PASSWORT"), 	  		100,50,false), new MyE2_String("Passwort"));
		this.add_Component(new MyE2_DB_TextField(		oFM.get_("KUERZEL"),	true,	100,10,false), new MyE2_String("Benutzerkürzel"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TELEFAX"),true,350,50,false), new MyE2_String("TELEFAX"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TELEFON"),true,350,50,false), new MyE2_String("TELEFON"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("TODO_SUPERVISOR"),todoSuper,true), new MyE2_String("TODO_SUPERVISOR"));
		
		this.get_oSQLFieldMAP().get_("ID_SPRACHE").set_cDefaultValueFormated(ddSprache.getDefault());
		this.get_oSQLFieldMAP().get_("ID_USERGROUP").set_cDefaultValueFormated(ddUserGroup.getDefault());
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SONDERRECH_ZEIGE_OPLISTE_SALDO")), new MyE2_String("Blendet die Anzeige ein, wie der Saldo in der OP-Liste ist"));

		//2011-12-06: unterschrifts-bild zum hochladen
		this.add_Component(new ownBildVerwalter_4_Unterschrift(oFM.get_("ID_USER_SIGNATUR"), MyE2_Grid.STYLE_GRID_DDARK_BORDER(), "JD_USER", Archiver_CONST.PROGRAMMKENNER.BENUTZER_SIGNATUR.get_DB_WERT()), new MyE2_String("Signatur"));

		
		//2014-02-17: Farbwaehler fuer die Signalfarben von Schriften auf Masken (BSP: Infofelder in Adressen, die Korrektheit der Adressen zeigen
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_OK_RED),true,50,4,true), 	new MyE2_String(_DB.USER$COLOR_MASK_OK_RED));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_OK_GREEN),true,50,4,true), 	new MyE2_String(_DB.USER$COLOR_MASK_OK_GREEN));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_OK_BLUE),true,50,4,true), 	new MyE2_String(_DB.USER$COLOR_MASK_OK_BLUE));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_ERROR_RED),true,50,4,true), 	new MyE2_String(_DB.USER$COLOR_MASK_ERROR_RED));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_ERROR_GREEN),true,50,4,true),new MyE2_String(_DB.USER$COLOR_MASK_ERROR_GREEN));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_ERROR_BLUE),true,50,4,true),	new MyE2_String(_DB.USER$COLOR_MASK_ERROR_BLUE));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_WARN_RED),true,50,4,true), 	new MyE2_String(_DB.USER$COLOR_MASK_WARN_RED));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_WARN_GREEN),true,50,4,true), new MyE2_String(_DB.USER$COLOR_MASK_WARN_GREEN));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.USER$COLOR_MASK_WARN_BLUE),true,50,4,true), 	new MyE2_String(_DB.USER$COLOR_MASK_WARN_BLUE));
		
		this.add_Component(USER__MASK_ComponentMAP.HASHKEY_SELECT_OK_COLOR, new ownButtonColorSelect(new MyE2_String("Anzeige OK"), _DB.USER$COLOR_MASK_OK_RED, _DB.USER$COLOR_MASK_OK_GREEN, _DB.USER$COLOR_MASK_OK_BLUE), new MyE2_String("Anzeigefarbe für OK"));
		this.add_Component(USER__MASK_ComponentMAP.HASHKEY_SELECT_WARN_COLOR, new ownButtonColorSelect(new MyE2_String("Anzeige WARNUNG"), _DB.USER$COLOR_MASK_WARN_RED, _DB.USER$COLOR_MASK_WARN_GREEN, _DB.USER$COLOR_MASK_WARN_BLUE), new MyE2_String("Anzeigefarbe für Warnmeldung"));
		this.add_Component(USER__MASK_ComponentMAP.HASHKEY_SELECT_ERROR_COLOR, new ownButtonColorSelect(new MyE2_String("Anzeige FEHLER"), _DB.USER$COLOR_MASK_ERROR_RED, _DB.USER$COLOR_MASK_ERROR_GREEN, _DB.USER$COLOR_MASK_ERROR_BLUE), new MyE2_String("Anzeigefarbe für Fehler"));
		
		
		//2014-10-06: neue schalter zur definition, wer meldungen zur kreditversicherung bekommt
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(RECORD_USER.FIELD__MELDUNG_KREDITVERS_ABLAUF), 	new MyE2_String("Warnung Ablauf Kreditversicherung"),	new MyE2_String("Benutzer bekommt eine Meldung, wenn Kreditversicherungen ablaufen")),	 	new MyE2_String("Warnung Ablauf Kreditversicherung"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(RECORD_USER.FIELD__MELDUNG_KREDITVERS_BETRAG), 	new MyE2_String("Warnung Überschreiten Kreditversicherung"),	new MyE2_String("Benutzer bekommt eine Meldung, wenn bei einem Kunden der Forderungsbetrag über das Kreditlimit geht.")),	 	new MyE2_String("Warnung Überschreiten Kreditversicherung"));

		//2014-10-13: schalter DEVELOPER
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.USER$DEVELOPER), 	new MyE2_String("Benutzer arbeitet im Entwicklermodus"),	new MyE2_String("Benutzer ist ein Softwareentwickler")),	 	new MyE2_String("Benutzer ist ein Softwareentwickler"));

		//2014-12-10: neues Feld: Datev-profil
		E2_DropDownSettingsNew  oDD_Datev = new E2_DropDownSettingsNew(	_DB.DATEV_PROFILE,
																		"NVL("+_DB.DATEV_PROFILE$DESCRIPTION+",'Datev-Profil:'||TO_CHAR("+_DB.DATEV_PROFILE$ID_DATEV_PROFILE+"))", 
																		_DB.DATEV_PROFILE$ID_DATEV_PROFILE, null, true, true);
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(_DB.USER$ID_DATEV_PROFILE), oDD_Datev.getDD(),false,new Extent(300)), new MyE2_String("Datevprofil"));
		
		
		((MyE2_DB_CheckBox) this.get__Comp("GESCHAEFTSFUEHRER")).add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
			}
		});
		((MyE2_DB_CheckBox) this.get__Comp("GESCHAEFTSFUEHRER")).add_GlobalValidator(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER());
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new USER__MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new USER__MASK_FORMATING_Agent());
		
		
		
		
		//MAP_setAndValid-objecte
		this.register_Interactiv_settings_validation(_DB.USER$COLOR_MASK_OK_RED, new USER_MASK_ComponentMAP_SetAndValid_ColorButtons());
		this.register_Interactiv_settings_validation(_DB.USER$COLOR_MASK_OK_GREEN, new USER_MASK_ComponentMAP_SetAndValid_LockFieldsNonAdmin());
		
	}

	
	
	
	private class ownBildVerwalter_4_Unterschrift extends MyE2_DB__SPECIAL_UPLOAD_IMAGE
	{
		public ownBildVerwalter_4_Unterschrift(SQLField osqlField, MutableStyle oStyle, String MOTHER_TABLE, String PROGRAMM_KENNER) throws myException
		{
			super(osqlField, oStyle, MOTHER_TABLE, PROGRAMM_KENNER);
			
			//beschriftungen regeln
			this.set__BUTTON_VALIDATOR_DELETE_IMAGE("UNTERSCHRIFT_ENTFERNEN");
			this.set__BUTTON_VALIDATOR_UPLOAD_IMAGE("UNTERSCHRIFT_HINZUFUEGEN");
			this.set__cButtonTextUpload(new MyE2_String("Unterschrift hinzufügen"));
			this.set__cButtonTextDelete(new MyE2_String("Unterschrift entfernen"));
			this.set__cButtonTextJa_Loeschen(new MyE2_String("Ja"));
			this.set__cButtonTextNein_NichtLoeschen(new MyE2_String("Nein"));

			this.set__cButtonTextUpload(new MyE2_String("Unterschriftsdatei hochladen ..."));
			this.set__cFehlermeldungBildIstDoppelt(new MyE2_String("Zu diesem Benutzer existiert bereits eine Unterschriftsdatei ..."));
			this.set__cLabelText4NewMask(new MyE2_String("Eine Unterschriftsdatei kann erst zugefügt werden, wenn die Benutzermaske einmal gespeichert wurde ..."));
			this.set__cUeberschrift_4_Upload_Dialog("Eine Unterschriftsdatei hochladen");
			this.set__cWarnTextVorLoeschen(new MyE2_String("Löschen der Unterschriftsdatei"));
			
		}
		
	}
	
	
	
	private class ownButtonColorSelect extends E2_ColorSelect_Button {
		
		private String hashkeyRED = null;
		private String hashkeyGREEN = null;
		private String hashkeyBLUE = null;
		
		public ownButtonColorSelect(MyString cText, String hashRED, String hashGREEN, String hashBLUE) {
			super(cText);
			
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Center(new E2_FontBold(2)));
			
			this.hashkeyRED = 	hashRED;
			this.hashkeyGREEN = hashGREEN;
			this.hashkeyBLUE = 	hashBLUE;
			
			this.INIT_Button(this.hashkeyRED, this.hashkeyGREEN, this.hashkeyBLUE, USER__MASK_ComponentMAP.this);
		}

		@Override
		public void fill_Anzeige_Mit_Color_AfterSelect(Color oCol) throws myException {
			this.setForeground(oCol);
		}
		
	}
	
}
