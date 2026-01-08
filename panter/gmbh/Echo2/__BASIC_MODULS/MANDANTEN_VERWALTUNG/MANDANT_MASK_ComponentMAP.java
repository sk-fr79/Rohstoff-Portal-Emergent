package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_WAEHRUNG_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_MASK_grid_enum_mandant_einstellungen.MandantConfig;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE.EMS__MASKField_4_Inly;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.STEUERVERMERKE.STM_MASK_VERMERKE_LIST_FullDaughterZusaetze;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.MZ_MASK_BEZ_LIST_FullDaughterZusaetze;
import panter.gmbh.Echo2.components.E2_ColorSelectPopUp;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory;


public class MANDANT_MASK_ComponentMAP extends E2_ComponentMAP 
{
	public static String COLORSELECT1 = "COLORSELECT1";
	public static String COLORSELECT2 = "COLORSELECT2";
	public static String COLORSELECT3 = "COLORSELECT3";
	public static String COLORSELECT4 = "COLORSELECT4";
	
	public static String COLORLABEL1 = "COLORLABEL1";
	public static String COLORLABEL2 = "COLORLABEL2";
	public static String COLORLABEL3 = "COLORLABEL3";
	public static String COLORLABEL4 = "COLORLABEL4";
	
	
	 
	private E2_ColorSelectPopUp oSelPop= null;
	private E2_ColorSelectPopUp oSelPop2= null;
	private E2_ColorSelectPopUp oSelPop3= null;
	private E2_ColorSelectPopUp oSelPop4= null;
	
	public MANDANT_MASK_ComponentMAP() throws myException
	{
		super(new MANDANT_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANREDE"),true,100,10,false), new MyE2_String("ANREDE"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_RED"),true,33,3,false), new MyE2_String("COLOR_RED"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_GREEN"),true,33,3,false), new MyE2_String("COLOR_GREEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_BLUE"),true,33,3,false), new MyE2_String("COLOR_BLUE"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_POPUP_TITEL_RED"),true,33,3,false), new MyE2_String("COLOR_POPUP_TITEL_RED"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_POPUP_TITEL_GREEN"),true,33,3,false), new MyE2_String("COLOR_POPUP_TITEL_GREEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_POPUP_TITEL_BLUE"),true,33,3,false), new MyE2_String("COLOR_POPUP_TITEL_BLUE"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_MASK_HIGHLIGHT_RED"),true,33,3,false), new MyE2_String("COLOR_MASK_HIGHLIGHT_RED"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_MASK_HIGHLIGHT_GREEN"),true,33,3,false), new MyE2_String("COLOR_MASK_HIGHLIGHT_GREEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_MASK_HIGHLIGHT_BLUE"),true,33,3,false), new MyE2_String("COLOR_MASK_HIGHLIGHT_BLUE"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_(MANDANT.color_backtext_red.fn()),true,33,3,false), 	new MyE2_String("COLOR_BACKTEXT_RED"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(MANDANT.color_backtext_green.fn()),true,33,3,false), 	new MyE2_String("COLOR_BACKTEXT_GREEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(MANDANT.color_backtext_blue.fn()),true,33,3,false), 	new MyE2_String("COLOR_BACKTEXT_BLUE"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("COLOR_DIFF"),true,33,3,false), new MyE2_String("COLOR_DIFF"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EIGENE_ADRESS_ID"),true,110,10,false), new MyE2_String("EIGENE_ADRESS_ID"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("FIRMENBLOCKRECHTSOBEN"),500,8), new MyE2_String("FIRMENBLOCKRECHTSOBEN"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("FIRMENBLOCKSEITENFUSS"),500,8), new MyE2_String("FIRMENBLOCKSEITENFUSS"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("HAT_ABZUEGE")), new MyE2_String("HAT_ABZUEGE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_MANDANT"),true,110,10,false), new MyE2_String("ID_MANDANT"));

		this.add_Component(new DB_Component_WAEHRUNG_DROPDOWN(oFM.get_("ID_WAEHRUNG")), new MyE2_String("ID_WAEHRUNG"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("INFO"),500,8), new MyE2_String("INFO"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LANDKURZ"),true,100,10,false), new MyE2_String("LANDKURZ"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LOGOGROESSE"),true,55,5,false), new MyE2_String("LOGOGROESSE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LOGONAME"),true,350,40,false), new MyE2_String("LOGONAME"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LOGOSCHRIFT"),true,200,20,false), new MyE2_String("LOGOSCHRIFT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LOGOTEXT"),true,200,20,false), new MyE2_String("LOGOTEXT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAILACCOUNT"),true,350,50,false), new MyE2_String("MAILACCOUNT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAILPASSWORD"),true,350,50,false), new MyE2_String("MAILPASSWORD"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAILSERVER"),true,350,50,false), new MyE2_String("MAILSERVER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAILUSERNAME"),true,350,50,false), new MyE2_String("MAILUSERNAME"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NAME1"),true,350,30,false), new MyE2_String("NAME1"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NAME2"),true,350,30,false), new MyE2_String("NAME2"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NAME3"),true,350,30,false), new MyE2_String("NAME3"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ORT"),true,350,30,false), new MyE2_String("ORT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("PLZ"),true,100,10,false), new MyE2_String("PLZ"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RUNDEN_ABZUGS_MENGEN")), new MyE2_String("RUNDEN_ABZUGS_MENGEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("STRASSE"),true,350,30,false), new MyE2_String("STRASSE"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("TITELUEBERANSCHRIFT"),500,5), new MyE2_String("TITELUEBERANSCHRIFT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("VORNAME"),true,350,30,false), new MyE2_String("VORNAME"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KURZNAME"),true,350,20,false), new MyE2_String("KURZNAME"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_RECH"),true,50,5,false), new MyE2_String("Buchungsprefix RECHNUNG"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_GUT"),true,50,5,false), new MyE2_String("Buchungsprefix Gutschrift"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_TPA"),true,50,5,false), new MyE2_String("Buchungsprefix TPA"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_EKK"),true,50,5,false), new MyE2_String("Buchungsprefix EK-Kontrakt"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_VKK"),true,50,5,false), new MyE2_String("Buchungsprefix VK-Kontrakt"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_ABANGEB"),true,50,5,false), new MyE2_String("Buchungsprefix Abnahmeangebot"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSPREFIX_LIEFANGEB"),true,50,5,false), new MyE2_String("Buchungsprefix Verkaufsangebot"));

		this.add_Component(MANDANT_MASK_ComponentMAP.COLORSELECT1, new colorButton1(), new MyE2_String("ColorSelector (Hauptfarbe)"));
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORSELECT2, new colorButton2(), new MyE2_String("ColorSelector (Farbe Titelbalken)"));
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORSELECT3, new colorButton3(), new MyE2_String("ColorSelector (Farbe Maskenhervorhebung)"));
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORSELECT4, new colorButton4(), new MyE2_String("ColorSelector (Farbe Hintergrundtext)"));
		
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORLABEL1, new MyE2_TextField("",100,50), new MyE2_String("ColorLabel (Hauptfarbe)"));
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORLABEL2, new MyE2_TextField("",100,50), new MyE2_String("ColorLabel (Farbe Titelbalken)"));
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORLABEL3, new MyE2_TextField("",100,50), new MyE2_String("ColorLabel (Farbe Maskenhervorhebung)"));
		this.add_Component(MANDANT_MASK_ComponentMAP.COLORLABEL4, new MyE2_TextField("",100,50), new MyE2_String("ColorLabel (Farbe Hintergrundtext)"));


		this.add_Component(new MyE2_DB_TextField(oFM.get_("MG_TOLERANZ_EK_KONTRAKT_POS"),true,50,3,false), new MyE2_String("Mengentoleranz für EK-Kontrakte"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MG_TOLERANZ_VK_KONTRAKT_POS"),true,50,3,false), new MyE2_String("Mengentoleranz für VK-Kontrakte"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("GRENZE_MENG_DIFF_ABRECH_PROZ"),true,50,7,false), new MyE2_String("Differenz in Prozenten für Warnmeldung bei der Buchung in Fuhrenzentrale"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("STICHTAG_START_FIBU"),true,100,10,false), new MyE2_String("Fibu wird bebucht ab dem angegebenen Leistungsdatum"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("SCHECKDRUCK_BANKNAME"),true,400,100,false), new MyE2_String("Eintrag Bankname auf Scheckdruck"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("SCHECKDRUCK_BLZ"),true,200,20,false), new MyE2_String("BLZ-Eintrag auf Scheckdruckdruck"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("SCHECKDRUCK_KONTO_NR"),true,200,20,false), new MyE2_String("Kontonummer auf Scheckdruck"));
		
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("SCHECKVERMERK_AUF_GUTSCHRIFT"),500,6), new MyE2_String("Scheckvermerk auf Gutschriften"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EU_STEUER_VERMERK"),true,700,200,false), new MyE2_String("Steuervermerk auf Rechnung/Gutschrift (EU-Innergemeinschaftliche Lieferung)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("AUSSEN_STEUER_VERMERK"),true,700,200,false), new MyE2_String("Steuervermerk auf Rechnung/Gutschrift (steuerfreie Aussenlieferung)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("VERMERK_STEUERFR_DIENSTLEIST"),true,700,200,false), new MyE2_String("Steuervermerk auf Rechnung/Gutschrift (steuerfreie Dienstleistung)"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ALLOW_EDIT_PRICE_IN_FUHREN_RG"),  new MyE2_String("Erlaube Preisänderung bei fuhrenbasierten Rechnung/Gutschrift-Positionen")), new MyE2_String("Erlaube Preisänderung bei fuhrenbasierten Positionen"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ALLOW_EDIT_ABZUG_IN_FUHREN_RG"),  new MyE2_String("Erlaube Abzugseingaben bei fuhrenbasierten Rechnung/Gutschrift-Positionen")), new MyE2_String("Erlaube Preisänderung bei fuhrenbasierten Positionen"));
		
		//2013-07-29: neues feld fuer das ausschalten der mandantenpruefung in der steuerermittlung
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$STEUERFINDUNG_OHNE_EIGENORTE),  
				new MyE2_String("Wenn angeschaltet, dann spielt das Merkmal <Ist Mandantenort> für die Steuerfindung keine Rolle mehr !")), new MyE2_String("Steuerfindung ohne spezielle Mandantenunterscheidung"));
		

		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_KONTO"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Kontonummer"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_BLZ"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Bankleitzahl"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_BANK"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Bank"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_TELEFON"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Telefon"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_TELEFAX"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Telefax"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_EMAIL"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  eMail"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_WWW"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Internet-Homepage"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_REGISTERGERICHT"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Registergericht"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_HANDELSREG_NR"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Handelsregister-Nummer"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_USTID"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Umsatzsteuer-ID"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGDRUCK_STEUERNR"),true,400,30,false), new MyE2_String("Feld für den Belegdruck:  Steuernummer"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("FILENAME_INTRASTAT_IN"),true,200,30,false), new MyE2_String("Name der erzeugten Instrastat-Datei (Einfuhren)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("FILENAME_INTRASTAT_OUT"),true,200,30,false), new MyE2_String("Name der erzeugten Instrastat-Datei (Ausfuhren)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("UNTERSCHEIDUNGSNR_INTRASTAT"),true,30,3,false), new MyE2_String("Unterscheidungs-Nr. Intrastat"));


		//schalter fuer die definition der oberflaechen-buttons
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ZEIGE_MODUL_FAHRPLANERFASSUNG")), 	new MyE2_String("ZEIGE_MODUL_FAHRPLANERFASSUNG"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ZEIGE_MODUL_KALENDER")),	 			new MyE2_String("ZEIGE_MODUL_KALENDER"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ZEIGE_MODUL_GLOBAL_REPORTS")), 		new MyE2_String("ZEIGE_MODUL_GLOBAL_REPORTS"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ZEIGE_MODUL_TODOS")), 				new MyE2_String("ZEIGE_MODUL_TODOS"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ZEIGE_MODUL_WORKFLOW")), 				new MyE2_String("ZEIGE_MODUL_WORKFLOW"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ZEIGE_MODUL_MESSAGES")), 				new MyE2_String("ZEIGE_MODUL_MESSAGES"));

		
		//felder fuer die nummernkreise der debitoren/kreditoren
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_DEBITOR_INLAND_VON"),true,100,10,false), new MyE2_String("Nummernkreis Debitoren-Inland (Start)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_DEBITOR_EU_VON"),true,100,10,false), new MyE2_String("Nummernkreis Debitoren-EU-Ausland (Start)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_DEBITOR_NICHT_EU_VON"),true,100,10,false), new MyE2_String("Nummernkreis Debitoren-Nicht-EU-Ausland (Start)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_DEBITOR_INLAND_BIS"),true,100,10,false), new MyE2_String("Nummernkreis Debitoren-Inland (Ende)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_DEBITOR_EU_BIS"),true,100,10,false), new MyE2_String("Nummernkreis Debitoren-EU-Ausland (Ende)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_DEBITOR_NICHT_EU_BIS"),true,100,10,false), new MyE2_String("Nummernkreis Debitoren-Nicht-EU-Ausland (Ende)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_KREDITOR_INLAND_VON"),true,100,10,false), new MyE2_String("Nummernkreis Kreditoren-Inland (Start)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_KREDITOR_EU_VON"),true,100,10,false), new MyE2_String("Nummernkreis Kreditoren-EU-Ausland (Start)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_KREDITOR_NICHT_EU_VON"),true,100,10,false), new MyE2_String("Nummernkreis Kreditoren-Nicht-EU-Ausland (Start)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_KREDITOR_INLAND_BIS"),true,100,10,false), new MyE2_String("Nummernkreis Kreditoren-Inland (Ende)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_KREDITOR_EU_BIS"),true,100,10,false), new MyE2_String("Nummernkreis Kreditoren-EU-Ausland (Ende)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NUMKREIS_KREDITOR_NICHT_EU_BIS"),true,100,10,false), new MyE2_String("Nummernkreis Kreditoren-Nicht-EU-Ausland (Ende)"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MANDANT$ANR1_GLEICHHEIT_FUHRE_STELLEN),true,30,2,false), new MyE2_String("Stellenzahl für die Gleichheitsprüfung der Fuhren-EK-VK-Sorte"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$PREISFREIGABE_AUTO_FUHRE)), new MyE2_String("Automatische Preisfreigabe in der Fuhre bei kompletten Fuhren und Benutzernm die die Berechtigung haben."));
		
		//neue Tabelle fuer Zusatzinfos
		this.add_Component(MANDANT_CONST.HASHKEY_FULL_DAUGHTER_ZUSATZFELDER, 
				new MZ_MASK_BEZ_LIST_FullDaughterZusaetze( 
						       (SQLFieldForPrimaryKey)oFM.get_("ID_MANDANT")), new MyE2_String("Zusatzfeld-Liste"));

		
		this.add_Component(MANDANT_CONST.HASHKEY_FULL_DAUGHTER_STEUERVERMERKE, 
				new STM_MASK_VERMERKE_LIST_FullDaughterZusaetze( 
						       (SQLFieldForPrimaryKey)oFM.get_("ID_MANDANT")), new MyE2_String("Liste Steuervermerke"));

		
		this.add_Component(MANDANT_CONST.HASHKEY_MASK_POPUP_ABZUEGE, new MANDANT_MASK_ButtonPopupABZUEGE(),new MyE2_String("Abzüge definieren"));
		
		// es wird eine ID-Land eingefuehrt
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
		oFM.get_("ID_LAND").set_cDefaultValueFormated(ddLand.getDefault());
		MyE2_DB_SelectField  oSelLand = new MyE2_DB_SelectField(oFM.get_("ID_LAND"),ddLand.getDD(),false);
		this.add_Component(oSelLand,new MyE2_String("Land"));
		oSelLand.setWidth(new Extent(100));
		MyE2_DB_TextField oLandKurz = (MyE2_DB_TextField)this.get__Comp("LANDKURZ");
		oLandKurz.EXT().set_bDisabledFromBasic(true);
		oSelLand.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_DB_SelectField oSelLand = (MyE2_DB_SelectField)execInfo.get_MyActionEvent().getSource();
				
				if (S.isFull(oSelLand.get_ActualWert()))
				{
					String cID_LAND = bibALL.ReplaceTeilString(oSelLand.get_ActualWert(), ".", "");
					RECORD_LAND  recLand = new RECORD_LAND(cID_LAND);
					
					((MyE2_DB_TextField)oSelLand.EXT().get_oComponentMAP().get__Comp("LANDKURZ")).setText(recLand.get_LAENDERCODE_cF_NN(""));
				}
				
				
			}
		});
				
		
		
		//2013-10-31: neue Maskenkomponente fuer die definierten YES-NO-Felder
		this.add_Component(MANDANT_CONST.HASHKEY_MASK_ZUSATZFELDER_YESNO, new MANDANT_MASK_Feld_Zusatz_Yes_No_Fields((SQLFieldForPrimaryKey)oFM.get_("ID_MANDANT")),new MyE2_String("Abzüge definieren"));
		
		//2014-07-11: felder um die Berechnungsstruktur der Zahlungsziele einzustellen
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$RECHDAT_IST_LEISTUNGSDATUM)), new MyE2_String("Rechnungsdatum ist nicht mehr frei erfassbar, sondern automatisch das neueste Leistungsdatum"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MANDANT$KARENZ_ZAHLFRIST_AB_HEUTE),true,50), new MyE2_String("Zahlungsfrist, die mindestens eingeräumt wird, unabhängig vom Zahlungsdatum"));
		this.add_Component(MANDANT_CONST.HASHKEY_MASK_HILFSBUTTON_RECHNUNGSDATUMSKALKULATION, new MANDANT_MASK_BT_HelpInfoAbrechnung(),new MyE2_String("Infos zur Datumserzeugung"));
		
		//2014-08-04: neue maskenfelder fuer das ein/ausschalten des Verhaltens "nur gedruckte Angebote/Kontrakte werden bei Preis berücksichtigt"
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$PREISFIND_ANGEB_NUR_GEDRUCKT)), new MyE2_String("Preisfindung findet nur noch gedruckte Angebote"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$PREISFIND_KONTR_NUR_GEDRUCKT)), new MyE2_String("Preisfindung findet nur noch gedruckte Kontrakte"));
		
		//2014-08-26: neues maskenfelder: definiert das berechnen der SA-SO-Zahlungsziele auf MONTAG
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$KORR_ZAHLDAT_WOCHENENDE)), new MyE2_String("Korrektur eines Zahlungsdatums am Wochenende (SA oder SO wird zu Montag)"));
		 
		
		//2015-03-23: wasserzeichen: definition von imprints von kopierten belegen
		Vector<String> vFonts = new Vector<String>();
		vFonts.addAll(new PDF_Overlay_Factory().get_FontNames());
		String cAllowedFonts = bibALL.Concatenate(vFonts, "/", "");
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MANDANT$WASSERZEICHEN_TEXT),true,400), new MyE2_String("Eingedrucktes Wasserzeichen, wenn Originalbelege aus dem Archiv geladen werden"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MANDANT$WASSERZEICHEN_FONTNAME),true,200), new MyE2_String("Erlaubte Schriftarten für das Wasserzeichen: ",true,cAllowedFonts,false));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MANDANT$WASSERZEICHEN_FONTSIZE),true,200), new MyE2_String("Fontgröße für den Wasserzeichen-Imprint (10-100)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MANDANT$WASSERZEICHEN_ROTATE),true,200), new MyE2_String("Winkel in Grad für die Rotation des Imprints (0-360)"));
		
		//2015-04-30: email-schablone-full-daughter
		this.add_Component(MANDANT_CONST.HASHKEY_FULL_DAUGHTER_EMAIL_SCHABLONE, new EMS__MASKField_4_Inly((SQLFieldForPrimaryKey)oFM.get_(_DB.MANDANT$ID_MANDANT)),new MyE2_String("Infos zur Datumserzeugung"));
		
		
		//2015-06-02: anhaenge an rg-formularen mit ausdrucken 
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MANDANT$APPEND_ATTACHMENT_PDF_TO_RG)), new MyE2_String("Sollen die zum beleg vorhandenen PDF-Dokumente auch ausgedruckt werden ?"));
		
		//2016-02-01: maximaler zeitabstand innerhalb einer Fuhre (6 datumsfelder in der fuhrenmaske)
		this.add_Component(new MyE2_DB_TextField(oFM.get_(MANDANT.allowed_date_diff),true,200), new MyE2_String("Maximale Differenz in Tagen innerhalb der Fuhrendaten (Lade- zu Ablade-Datum) ?"));
		
		//2016-07-07: in den JT_REPORT - gebundenen Reports die ID_REPORT in klammern hinter den namen des reports schreiben 
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(MANDANT.show_ids_on_report_labels)), new MyE2_String("Die Button-Beschriftungen der Datenbank-definierten Reports um die ID_REPORT erweitern"));
	
		//2016-09-06: neue decisions in einen weiteren reiter 
		this.add_Component(MANDANT_CONST.HASHKEY_MASK_MANDANT_DECISIONS, new MANDANT_MASK_grid_enum_mandant_decision(oFM.get_(MANDANT.id_mandant)),new MyE2_String("Weitere Yes-No-Felder"));
		
		//2018-01-16@Sebastien: configuration moeglichkeit in einen weiteren reiter
		MANDANT_MASK_grid_enum_mandant_einstellungen maskSettingsListe = new MANDANT_MASK_grid_enum_mandant_einstellungen(oFM.get_(MANDANT.id_mandant));
		this.add_Component(MANDANT_CONST.HASHKEY_MASK_MANDANT_CONFIGURATION,maskSettingsListe ,new MyE2_String("Einstellungen"));
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new MANDANT_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new MANDANT_MASK_FORMATING_Agent());
		
		this.add_oMAPValidator(new ownMapValidator());
		
		
		//ein map-setter-und-validator, der die mandant-einstellungen vor dem speichern auf korrektheit prueft
		this.register_Interactiv_settings_validation(MANDANT.id_mandant.fn(), new ValidMandantSettingsEinstellungsListe(maskSettingsListe));
		
		
		//2020-08-18: Schalter ausser betrieb, da er unsinn erzeugt!!!!!!
		this.get__DBComp(_DB.MANDANT$STEUERFINDUNG_OHNE_EIGENORTE).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_CheckBox)this.get__DBComp(_DB.MANDANT$STEUERFINDUNG_OHNE_EIGENORTE)).EXT();
		this.register_Interactiv_settings_validation(MANDANT.id_mandant.fn(), new XX_MAP_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
				return setSchalterSteuerfindungOhneEigenOrtAus(oMAP);
			}
			
			@Override
			public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
				return setSchalterSteuerfindungOhneEigenOrtAus(oMAP);
			}
			
			@Override
			public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
				return setSchalterSteuerfindungOhneEigenOrtAus(oMAP);
			}
			
			@Override
			public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
				return setSchalterSteuerfindungOhneEigenOrtAus(oMAP);
			}
			
			@Override
			public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
				return setSchalterSteuerfindungOhneEigenOrtAus(oMAP);
			}
			private MyE2_MessageVector setSchalterSteuerfindungOhneEigenOrtAus(E2_ComponentMAP oMap) {
				try {
					((MyE2_DB_CheckBox)oMap.get__DBComp(_DB.MANDANT$STEUERFINDUNG_OHNE_EIGENORTE)).setSelected(false);
				} catch (myException e) {
					e.printStackTrace();
				}
				return bibMSG.getNewMV();
			}
			
		});
		//2020-08-18: Schalter ausser betrieb, da er unsinn erzeugt!!!!!!
		
	}

	
	
	private class ValidMandantSettingsEinstellungsListe extends XX_MAP_Set_And_Valid {
		
		private MANDANT_MASK_grid_enum_mandant_einstellungen maskSettingsListe = null; 
		
		public ValidMandantSettingsEinstellungsListe(MANDANT_MASK_grid_enum_mandant_einstellungen p_maskSettingsListe) {
			super();
			this.maskSettingsListe = p_maskSettingsListe;
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
			return null;
		}
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
			return null;
		}
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
			return null;
		}
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
			return null;
		}
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
				for (MandantConfig c: maskSettingsListe.getMandantConfigs()) {
					mv._add(c.getEnum_mandant_config().checkValue(c.getTextField().getText()));
				}
			} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
				for (MandantConfig c: maskSettingsListe.getMandantConfigs()) {
					mv._add(c.getEnum_mandant_config().checkValue(c.getTextField().getText())._changeErrorsToWarnings());
				}
			}
			
			return mv;
		}

	}
	
	
	private class ownMapValidator extends XX_MAP_ValidBeforeSAVE
	{

		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP inputMap, String cstatus_maske)	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			String[] cFarben = {"COLOR_RED","COLOR_POPUP_TITEL_RED","COLOR_POPUP_TITEL_GREEN","COLOR_POPUP_TITEL_BLUE","COLOR_MASK_HIGHLIGHT_RED",
								"COLOR_MASK_HIGHLIGHT_GREEN","COLOR_MASK_HIGHLIGHT_BLUE","COLOR_GREEN","COLOR_BLUE"};
			
			for (int i=0;i<cFarben.length;i++)
			{
				if (oMap.get_LActualDBValue(cFarben[i], true, true, new Long(-1)).longValue()<0 || oMap.get_LActualDBValue(cFarben[i], true, true, new Long(-1)).longValue()>254)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Farbanteil "+cFarben[i]+" zwischen 0 und 254 !"));
				}
			}
			
			if (oMap.get_LActualDBValue("COLOR_DIFF", true, true, new Long(-1)).longValue()<0 || oMap.get_LActualDBValue("COLOR_DIFF", true, true, new Long(-1)).longValue()>254)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Farbunterschiede zwischen 2 und 20 erlaubt !"));
			}

			return oMV;
		}
		
	}
	
	
	
	private class colorButton1 extends MyE2_Button
	{

		public colorButton1()
		{
			super(new MyE2_String("Farbe"));
			this.add_oActionAgent(new colorButtonAction());
		}
		
	}

	private class colorButtonAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			MANDANT_MASK_ComponentMAP oThis = MANDANT_MASK_ComponentMAP.this;
			Color oStartCol = null;
			int iRed =  oThis.get_LActualDBValue("COLOR_RED", true, true, new Long(-1)).intValue();
			int iGreen =  oThis.get_LActualDBValue("COLOR_GREEN", true, true, new Long(-1)).intValue();
			int iBlue =  oThis.get_LActualDBValue("COLOR_BLUE", true, true, new Long(-1)).intValue();
			
			if (iRed>0 && iRed<255 && iGreen>0 && iGreen<255 && iBlue>0 && iBlue<255)
			{
				oStartCol = new Color(iRed,iGreen,iBlue);
			}
			
			MANDANT_MASK_ComponentMAP.this.oSelPop = new E2_ColorSelectPopUp(new colorSaveAction(),oStartCol);
		}
	}
	
	
	private class colorSaveAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_RED")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop.get_RED());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_BLUE")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop.get_BLUE());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_GREEN")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop.get_GREEN());
			
			((MyE2_TextField)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL1)).setBackground(
					new Color(MANDANT_MASK_ComponentMAP.this.oSelPop.get_RED(),MANDANT_MASK_ComponentMAP.this.oSelPop.get_GREEN(),MANDANT_MASK_ComponentMAP.this.oSelPop.get_BLUE()));
		}
		
	}
	
	
	
	private class colorButton2 extends MyE2_Button
	{

		public colorButton2()
		{
			super(new MyE2_String("Farbe"));
			this.add_oActionAgent(new colorButtonAction2());
		}
		
	}

	private class colorButtonAction2 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			MANDANT_MASK_ComponentMAP oThis = MANDANT_MASK_ComponentMAP.this;
			Color oStartCol = null;
			int iRed =  oThis.get_LActualDBValue("COLOR_POPUP_TITEL_RED", true, true, new Long(-1)).intValue();
			int iGreen =  oThis.get_LActualDBValue("COLOR_POPUP_TITEL_GREEN", true, true, new Long(-1)).intValue();
			int iBlue =  oThis.get_LActualDBValue("COLOR_POPUP_TITEL_BLUE", true, true, new Long(-1)).intValue();
			
			if (iRed>0 && iRed<255 && iGreen>0 && iGreen<255 && iBlue>0 && iBlue<255)
			{
				oStartCol = new Color(iRed,iGreen,iBlue);
			}
			
			MANDANT_MASK_ComponentMAP.this.oSelPop2 = new E2_ColorSelectPopUp(new colorSaveAction2(),oStartCol);
		}
	}
	
	
	private class colorSaveAction2 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_POPUP_TITEL_RED")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop2.get_RED());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_POPUP_TITEL_BLUE")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop2.get_BLUE());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_POPUP_TITEL_GREEN")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop2.get_GREEN());
			
			((MyE2_TextField)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL2)).setBackground(
					new Color(MANDANT_MASK_ComponentMAP.this.oSelPop2.get_RED(),MANDANT_MASK_ComponentMAP.this.oSelPop2.get_GREEN(),MANDANT_MASK_ComponentMAP.this.oSelPop2.get_BLUE()));

		}
		
	}
	private class colorButton3 extends MyE2_Button
	{

		public colorButton3()
		{
			super(new MyE2_String("Farbe"));
			this.add_oActionAgent(new colorButtonAction3());
		}
		
	}

	private class colorButtonAction3 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			MANDANT_MASK_ComponentMAP oThis = MANDANT_MASK_ComponentMAP.this;
			Color oStartCol = null;
			int iRed =  oThis.get_LActualDBValue("COLOR_MASK_HIGHLIGHT_RED", true, true, new Long(-1)).intValue();
			int iGreen =  oThis.get_LActualDBValue("COLOR_MASK_HIGHLIGHT_GREEN", true, true, new Long(-1)).intValue();
			int iBlue =  oThis.get_LActualDBValue("COLOR_MASK_HIGHLIGHT_BLUE", true, true, new Long(-1)).intValue();
			
			if (iRed>0 && iRed<255 && iGreen>0 && iGreen<255 && iBlue>0 && iBlue<255)
			{
				oStartCol = new Color(iRed,iGreen,iBlue);
			}
			
			MANDANT_MASK_ComponentMAP.this.oSelPop3 = new E2_ColorSelectPopUp(new colorSaveAction3(),oStartCol);
		}
	}
	
	
	private class colorSaveAction3 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_MASK_HIGHLIGHT_RED")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop3.get_RED());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_MASK_HIGHLIGHT_BLUE")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop3.get_BLUE());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp("COLOR_MASK_HIGHLIGHT_GREEN")).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop3.get_GREEN());
			
			((MyE2_TextField)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL3)).setBackground(
					new Color(MANDANT_MASK_ComponentMAP.this.oSelPop3.get_RED(),MANDANT_MASK_ComponentMAP.this.oSelPop3.get_GREEN(),MANDANT_MASK_ComponentMAP.this.oSelPop3.get_BLUE()));

		}
		
	}
	
	

	
	
	//2015-09-14: neue farbe fuer hintergrundtext
	private class colorButton4 extends MyE2_Button
	{

		public colorButton4()
		{
			super(new MyE2_String("Farbe"));
			this.add_oActionAgent(new colorButtonAction4());
		}
		
	}

	private class colorButtonAction4 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			MANDANT_MASK_ComponentMAP oThis = MANDANT_MASK_ComponentMAP.this;
			Color oStartCol = null;
			int iRed =  oThis.get_LActualDBValue(MANDANT.color_backtext_red.fn(), true, true, new Long(-1)).intValue();
			int iGreen =  oThis.get_LActualDBValue(MANDANT.color_backtext_green.fn(), true, true, new Long(-1)).intValue();
			int iBlue =  oThis.get_LActualDBValue(MANDANT.color_backtext_blue.fn(), true, true, new Long(-1)).intValue();
			
			if (iRed>0 && iRed<255 && iGreen>0 && iGreen<255 && iBlue>0 && iBlue<255)
			{
				oStartCol = new Color(iRed,iGreen,iBlue);
			}
			
			MANDANT_MASK_ComponentMAP.this.oSelPop4 = new E2_ColorSelectPopUp(new colorSaveAction4(),oStartCol);
		}
	}
	
	
	private class colorSaveAction4 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT.color_backtext_red.fn())).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop4.get_RED());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT.color_backtext_green.fn())).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop4.get_GREEN());
			((MyE2IF__DB_Component)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT.color_backtext_blue.fn())).set_cActualMaskValue(""+MANDANT_MASK_ComponentMAP.this.oSelPop4.get_BLUE());
			
			((MyE2_TextField)MANDANT_MASK_ComponentMAP.this.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL4)).setBackground(
					new Color(MANDANT_MASK_ComponentMAP.this.oSelPop4.get_RED(),MANDANT_MASK_ComponentMAP.this.oSelPop4.get_GREEN(),MANDANT_MASK_ComponentMAP.this.oSelPop4.get_BLUE()));

		}
		
	}
}
