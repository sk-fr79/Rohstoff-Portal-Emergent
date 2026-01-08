package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class MANDANT_MASK extends MyE2_Column implements IF_BaseComponent4Mask {
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public MANDANT_MASK(MANDANT_MASK_ComponentMAP oMap) throws myException	{
		
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
		
		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		
		oTabbedPaneMaske.set_bAutoHeight(true);
		
		// 5 Grids
		MyE2_Grid oGrid_0 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid_1 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid_2 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid_3 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid_4 = new MyE2_Grid(3,0);    //nummernkreise
		MyE2_Grid oGrid_5 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid_6 = new MyE2_Grid(2,0);    //fulldaughter mit zusatzinfos
		MyE2_Grid oGrid_7 = new MyE2_Grid(2,0);    //fulldaughter mit zusatzinfos (speziell checkboxen)
		E2_Grid oGrid_7a = new E2_Grid()._setSize(900)._w100();   //weitere entscheidungen
		E2_Grid oGrid_7b = new E2_Grid()._setSize(900)._w100();		//weitere einstellungen
		MyE2_Grid oGrid_8 = new MyE2_Grid(4,0);    //fulldaughter mit Steuervermerken
		MyE2_Grid oGrid_9 = new MyE2_Grid(4,0);    //fulldaughter mit eMail-Profilen
		
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Basisangaben"), oGrid_0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Zusätze"), oGrid_1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Zusätze (2)"), oGrid_2);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Benutzeroberfläche/Einstellungen"), oGrid_3);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Nummernkreise"), oGrid_4);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Felder für Formulardruck"), oGrid_5);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Zusatzfelder"), oGrid_6);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Schalter (1)"), oGrid_7);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Schalter (2)"), oGrid_7a);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Einstellungen"), oGrid_7b);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Steuervermerke"), oGrid_8);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("eMail-Profile"), oGrid_9);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
		
		//hier kommen die Felder	
		oFiller.add_Line(oGrid_0, new MyE2_String("ID_MANDANT:"), 1, "ID_MANDANT|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Eigene Adress-ID:"), 1, "EIGENE_ADRESS_ID|#  |",3);
		oFiller.add_Trenner(oGrid_0, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_0, new MyE2_String("Anrede:"), 1, "ANREDE|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Vorname:"), 1, "VORNAME|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Name 1:"), 1, "NAME1|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Name 2:"), 1, "NAME2|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Name 3:"), 1, "NAME3|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Strasse:"), 1, "STRASSE|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("PLZ, Ort:"), 1, "PLZ|# |ORT|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Land:"), 1, "|ID_LAND|#  |LANDKURZ|#  |",3);
		oFiller.add_Line(oGrid_0, new MyE2_String("Stammwährung:"), 1, "ID_WAEHRUNG|#  |",3);
		oFiller.add_Trenner(oGrid_0, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_0, new MyE2_String("Kurzname:"), 1, "KURZNAME|#  |",3);
		oFiller.add_Trenner(oGrid_0, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_0, new MyE2_String("Buchungsnr.Prefix:"), 	1, "|#Rechnung...|BUCHUNGSPREFIX_RECH|#  |#Gutschrift.............|BUCHUNGSPREFIX_GUT|#  |#TPA|BUCHUNGSPREFIX_TPA|",3);
		oFiller.add_Line(oGrid_0, new MyE2_String(""), 						1, "|#EK-Kontrakt|BUCHUNGSPREFIX_EKK|#  |#VK-Kontrakt.........|BUCHUNGSPREFIX_VKK|",3);
		oFiller.add_Line(oGrid_0, new MyE2_String(""), 						1, "|#Angebot......|BUCHUNGSPREFIX_LIEFANGEB|#  |#Abnahme-Angebot|BUCHUNGSPREFIX_ABANGEB|",3);
		oFiller.add_Trenner(oGrid_0, E2_INSETS.I_0_0_0_0);
		
		if (ENUM_MANDANT_DECISION.INTRASTAT_XML_FORMAT.is_NO()) {
			oFiller.add_Line(oGrid_0, new MyE2_String("Dateiname Intrastat:"), 	1, "|#Einfuhr: |FILENAME_INTRASTAT_IN|#  |#Ausfuhr: |FILENAME_INTRASTAT_OUT|#  |#Unterscheidungs-Nr: |UNTERSCHEIDUNGSNR_INTRASTAT",3);
		} else {
			oFiller.add_Line(oGrid_0, new MyE2_String("Intrastat-Einstellungen jetzt in Reiter Einstellungen!"), 	4);
		}
		
		
		
		oFiller.add_Line(oGrid_1, new MyE2_String("Farbanteile:"), 1, "#Rot |COLOR_RED|# |#Grün|COLOR_GREEN|# |#Blau|COLOR_BLUE|#  |#Farbunterschiede: |COLOR_DIFF|# |"+
													MANDANT_MASK_ComponentMAP.COLORSELECT1+"|"+MANDANT_MASK_ComponentMAP.COLORLABEL1,3);

		oFiller.add_Line(oGrid_1, new MyE2_String("Titelfarbe einesPopups (R,G,B)"), 1, "#Rot |COLOR_POPUP_TITEL_RED|# |#Grün|COLOR_POPUP_TITEL_GREEN|# |#Blau|COLOR_POPUP_TITEL_BLUE|#  |"+
													MANDANT_MASK_ComponentMAP.COLORSELECT2+"|"+MANDANT_MASK_ComponentMAP.COLORLABEL2,3);
		
		oFiller.add_Line(oGrid_1, new MyE2_String("Masken-Hervorhebungen:"), 1, "#Rot |COLOR_MASK_HIGHLIGHT_RED|# |#Grün|COLOR_MASK_HIGHLIGHT_GREEN|# |#Blau|COLOR_MASK_HIGHLIGHT_BLUE|#  |"+
													MANDANT_MASK_ComponentMAP.COLORSELECT3+"|"+MANDANT_MASK_ComponentMAP.COLORLABEL3,3);
		
		oFiller.add_Line(oGrid_1, new MyE2_String("Hintergrundtext-Farbe:"), 1, "#Rot |"+MANDANT.color_backtext_red.fn()+"|# |#Grün|"+MANDANT.color_backtext_green.fn()+"|# |#Blau|"+MANDANT.color_backtext_blue.fn()+"|#  |"+
													MANDANT_MASK_ComponentMAP.COLORSELECT4+"|"+MANDANT_MASK_ComponentMAP.COLORLABEL4,3);

		oFiller.add_Trenner(oGrid_1, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_1, new MyE2_String("Firmenblock rechts Oben:"), 1, "FIRMENBLOCKRECHTSOBEN|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Firmenblock Seitenfuss:"), 1, "FIRMENBLOCKSEITENFUSS|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Mandant hat Abzüge:"), 1, "HAT_ABZUEGE|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Info:"), 1, "INFO|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Groesse des Logos:"), 1, "LOGOGROESSE|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Name des Logos:"), 1, "LOGONAME|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Schriftart des Logos:"), 1, "LOGOSCHRIFT|#  |",3);
		oFiller.add_Line(oGrid_1, new MyE2_String("Logotext:"), 1, "LOGOTEXT|#  |",3);

		oFiller.add_Line(oGrid_2, new MyE2_String("Mailaccount:"), 1, "MAILACCOUNT|#  |",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Mail-Benutzername:"), 1, "MAILUSERNAME|#  |",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Mailpasswort:"), 1, "MAILPASSWORD|#  |",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Mail-Server-IP:"), 1, "MAILSERVER|#  |",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Abzugsmengen runden ?"), 1, "RUNDEN_ABZUGS_MENGEN|#   |#Stellen bei Prüf. Sortengleichh.Fuhre|"+RECORD_MANDANT.FIELD__ANR1_GLEICHHEIT_FUHRE_STELLEN,3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Titel über Anschriftenfeld:"), 1, "TITELUEBERANSCHRIFT|#  |",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Mengentoleranzen (in %)"), 1, "#EK-Kontrakte:|MG_TOLERANZ_EK_KONTRAKT_POS|#  |#VK-Kontrakte:|MG_TOLERANZ_VK_KONTRAKT_POS|#  |#Fuhrenbuchung:|#  |GRENZE_MENG_DIFF_ABRECH_PROZ|",3);
		oFiller.add_Trenner(oGrid_2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_2, new MyE2_String("Fibueinträge ab Leistungsdatum:"), 1,	"STICHTAG_START_FIBU",3);
		oFiller.add_Trenner(oGrid_2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_2, new MyE2_String("Bankname auf Scheck:"), 1, 			"SCHECKDRUCK_BANKNAME",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("BLZ auf Scheck:"), 1, 				"SCHECKDRUCK_BLZ",3);
		oFiller.add_Line(oGrid_2, new MyE2_String("Kontonummer auf Scheck:"), 1, 		"SCHECKDRUCK_KONTO_NR",3);
		oFiller.add_Trenner(oGrid_2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_2, new MyE2_String("Scheckvermerk auf Gutschr.:"), 1, 	"SCHECKVERMERK_AUF_GUTSCHRIFT",3);
		oFiller.add_Trenner(oGrid_2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_2, new MyE2_String("Wasserzeichen auf Kopien (Text)"), 1, 	_DB.MANDANT$WASSERZEICHEN_TEXT,3);
		oFiller.add_Line(oGrid_2, new MyE2_String("..... (Schriftart)"), 1, 				_DB.MANDANT$WASSERZEICHEN_FONTNAME,3);
		oFiller.add_Line(oGrid_2, new MyE2_String("..... (Schriftgröße)"), 1, 				_DB.MANDANT$WASSERZEICHEN_FONTSIZE,3);
		oFiller.add_Line(oGrid_2, new MyE2_String("..... (Rotation in Grad)"), 1, 			_DB.MANDANT$WASSERZEICHEN_ROTATE,3);
		oFiller.add_Trenner(oGrid_2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid_2, new MyE2_String("Differenz in Tagen Lade- zu Ablade"), 1,	MANDANT.allowed_date_diff.fn(),3);

		
		//Seite; Grid3
		//aufteilung der maske in raster
		int iBreite[]= {200,60};
		MyE2_Grid oGridButtonsInSchnellzugriff = new MyE2_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid oGridButtonsWeitereSettings = new MyE2_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid oGridSettingRechnungsdatum = new MyE2_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_Label(new MyE2_String("Buttons in den Schnellzugriff-Leisten"),new E2_FontBold()), 2);
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_String("Fahrplan-Schnellerfassung"), 1, "ZEIGE_MODUL_FAHRPLANERFASSUNG",1);
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_String("Kalender"), 1,				 	"ZEIGE_MODUL_KALENDER",1);
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_String("Globale Listen"), 1, 			"ZEIGE_MODUL_GLOBAL_REPORTS",1);
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_String("ToDos"), 1, 					"ZEIGE_MODUL_TODOS",1);
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_String("Laufzettel"), 1, 				"ZEIGE_MODUL_WORKFLOW",1);
		oFiller.add_Line(oGridButtonsInSchnellzugriff, new MyE2_String("Schnell-Nachrichten"), 1, 		"ZEIGE_MODUL_MESSAGES",1);
		
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_Label(new MyE2_String("Weitere Einstellungen"),new E2_FontBold()), 2);
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Preisfreigabe Fuhre mit Mengenfreigabe"), 1, _DB.MANDANT$PREISFREIGABE_AUTO_FUHRE,1);
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Preisänderung bei Fuhren-Pos.: "), 1, 		_DB.MANDANT$ALLOW_EDIT_PRICE_IN_FUHREN_RG,1);
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Abzugsänderungen bei Fuhren-Pos.:"),1,		_DB.MANDANT$ALLOW_EDIT_ABZUG_IN_FUHREN_RG,1);
		//oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Steuerfindung ohne Mandantenmerkmal"),1,		_DB.MANDANT$STEUERFINDUNG_OHNE_EIGENORTE,1);
		oFiller.add_Line(oGridButtonsWeitereSettings, new RB_lab("Steuerfindung ohne Mandantenmerkmal")._f(new E2_Font(Font.LINE_THROUGH)._fsa(-2)),1,		_DB.MANDANT$STEUERFINDUNG_OHNE_EIGENORTE,1);

		//2014-08-04: preisfindung bei nicht abgeschlossenen angeboten/kontrakte
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Preisfindung/Fuhre nur gedruckte Angebote"),1,		_DB.MANDANT$PREISFIND_ANGEB_NUR_GEDRUCKT,1);
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Preisfindung/Fuhre nur gedruckte Kontrakte"),1,		_DB.MANDANT$PREISFIND_KONTR_NUR_GEDRUCKT,1);

		//2014-08-26: korrektur von zahlungszielen ein/ausschalten
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Zahl.ziele von Sa./So. auf Mo. setzen"),1,		_DB.MANDANT$KORR_ZAHLDAT_WOCHENENDE,1);
		
		//2015-06-02: anlagen an RG-Belegen auch mitausdrucken
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Zum Rech./Gutschr. Beleg vorhandene PDF-Dokumente autom. ausdrucken ?"),1,_DB.MANDANT$APPEND_ATTACHMENT_PDF_TO_RG,1);
		//2016-07-07: 
		oFiller.add_Line(oGridButtonsWeitereSettings, new MyE2_String("Auf den Reportbuttons zur Beschriftung der Reports die ID_REPORT anzeigen"),1,MANDANT.show_ids_on_report_labels.fn(),1);
		
		//2014-07-11: angabe zu den alternativen Rechnungsdaten
		oFiller.add_Line(oGridSettingRechnungsdatum, new MyE2_String("Alternative Rechnungserstellung"), 1, MANDANT_CONST.HASHKEY_MASK_HILFSBUTTON_RECHNUNGSDATUMSKALKULATION, 1);
		oFiller.add_Line(oGridSettingRechnungsdatum, new MyE2_String("Aktiviere alternative Rechnungserstellung?"), 1, _DB.MANDANT$RECHDAT_IST_LEISTUNGSDATUM, 1,true);
		oFiller.add_Line(oGridSettingRechnungsdatum, new MyE2_String("Mindestens eingeräumte Zahlungsfrist"), 1, _DB.MANDANT$KARENZ_ZAHLFRIST_AB_HEUTE, 1,true);
		
		
		oGrid_3.setSize(2);
		oGrid_3.add(oGridButtonsInSchnellzugriff,MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(0,2,20,2)));
		oGrid_3.add(oGridButtonsWeitereSettings,MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(0,2,20,2)));
		oFiller.add_Trenner(oGrid_3, E2_INSETS.I_2_2_2_2);
		oGrid_3.add(oGridSettingRechnungsdatum,2,E2_INSETS.I_2_2_2_2);
		
		
		
		oFiller.add_Trenner(oGrid_3, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(oGrid_3, MANDANT_CONST.HASHKEY_MASK_POPUP_ABZUEGE, oGrid_3.getSize());

		
		
		
		oFiller.add_Line(oGrid_5, new MyE2_String("Telefon"), 1, 				"BELEGDRUCK_TELEFON",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Telefax"), 1, 				"BELEGDRUCK_TELEFAX",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("eMail"), 1, 					"BELEGDRUCK_EMAIL",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Internet-Homepage"), 1, 		"BELEGDRUCK_WWW",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Bank"), 1, 					"BELEGDRUCK_BANK",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Bankleitzahl"), 1, 			"BELEGDRUCK_BLZ",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Kontonummer"), 1, 			"BELEGDRUCK_KONTO",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Registergericht"), 1, 		"BELEGDRUCK_REGISTERGERICHT",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Handelsregister-Nummer"), 1, 	"BELEGDRUCK_HANDELSREG_NR",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Umsatzsteuer-ID"), 1, 		"BELEGDRUCK_USTID",3);
		oFiller.add_Line(oGrid_5, new MyE2_String("Steuernummer"), 1, 			"BELEGDRUCK_STEUERNR",3);
		
		oFiller.add_Trenner(oGrid_5, E2_INSETS.I_2_2_2_2);
		
		
		
		
		
		GridLayoutData oGLL = MyE2_Grid.LAYOUT_LEFT(	new Insets(2,1,10,1));
		GridLayoutData oGLR = MyE2_Grid.LAYOUT_RIGHT(	new Insets(2,1,10,1));
		
		GridLayoutData oGL2 = MyE2_Grid.LAYOUT_LEFT(	new Insets(2,1,10,10));
		GridLayoutData oGR2 = MyE2_Grid.LAYOUT_RIGHT(	new Insets(2,1,10,10));
		GridLayoutData oGC2 = MyE2_Grid.LAYOUT_CENTER(	new Insets(2,1,10,10));
		
		oFiller.add_Line(oGrid_4, new MyE2_String("Nummernkreise"), 			oGL2, new MyE2_String("von"), 			oGC2, new MyE2_String("bis"),			oGC2);
		oFiller.add_Line(oGrid_4, new MyE2_String("Debitoren-INLAND"), 		oGLL,"NUMKREIS_DEBITOR_INLAND_VON",		oGLR,"NUMKREIS_DEBITOR_INLAND_BIS",		oGLR);
		oFiller.add_Line(oGrid_4, new MyE2_String("Debitoren-EU"), 			oGLL,"NUMKREIS_DEBITOR_EU_VON",			oGLR,"NUMKREIS_DEBITOR_EU_BIS",			oGLR);
		oFiller.add_Line(oGrid_4, new MyE2_String("Debitoren-NICHT-EU"), 	oGL2,"NUMKREIS_DEBITOR_NICHT_EU_VON",	oGR2,"NUMKREIS_DEBITOR_NICHT_EU_BIS",	oGR2);
		oFiller.add_Line(oGrid_4, new MyE2_String("Kreditoren-INLAND"), 		oGLL,"NUMKREIS_KREDITOR_INLAND_VON",	oGLR,"NUMKREIS_KREDITOR_INLAND_BIS",	oGLR);
		oFiller.add_Line(oGrid_4, new MyE2_String("Kreditoren-EU"), 			oGLL,"NUMKREIS_KREDITOR_EU_VON",		oGLR,"NUMKREIS_KREDITOR_EU_BIS",		oGLR);
		oFiller.add_Line(oGrid_4, new MyE2_String("Kreditoren-NICHT-EU"), 	oGLL,"NUMKREIS_KREDITOR_NICHT_EU_VON",	oGLR,"NUMKREIS_KREDITOR_NICHT_EU_BIS",	oGLR);
	
		oFiller.add_Line(oGrid_6, new MyE2_String("Zusätze"), 1, 			MANDANT_CONST.HASHKEY_FULL_DAUGHTER_ZUSATZFELDER,1);

		oFiller.add_Line(oGrid_7, new MyE2_String("Zusatz-Schalter"), 1, 			MANDANT_CONST.HASHKEY_MASK_ZUSATZFELDER_YESNO,1);
		
		oFiller.add_Line(oGrid_8, new MyE2_String("Innergemeinsch.Vermerk Rechnung/Gut."), 1, "EU_STEUER_VERMERK",3);
		oFiller.add_Line(oGrid_8, new MyE2_String("Aussenlieferung-Vermerk Rechnung/Gut."), 1, "AUSSEN_STEUER_VERMERK",3);
		oFiller.add_Line(oGrid_8, new MyE2_String("Vermerk für steuerfr.Dienstleistung"), 1, "VERMERK_STEUERFR_DIENSTLEIST",3);
		oFiller.add_Trenner(oGrid_8, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(oGrid_8, new MyE2_String("Ländervermerke"), 4); 
		oFiller.add_Line(oGrid_8, MANDANT_CONST.HASHKEY_FULL_DAUGHTER_STEUERVERMERKE,4);
		
		oFiller.add_Line(oGrid_9, new MyE2_String("eMail-Profil"), 4); 
		oFiller.add_Line(oGrid_9, MANDANT_CONST.HASHKEY_FULL_DAUGHTER_EMAIL_SCHABLONE,4);
		
		
//		//2016-09-06: schalter-2 - grid
//		oGrid_7a._a(new RB_lab()._t(new MyE2_String("Weitere Systemschalter ..."))._b(),new RB_gld()._ins(4, 5, 2,10))._bo_green();
//		this.containerExForDecisions.setWidth(new Extent(100,Extent.PERCENT));
//		this.containerExForDecisions.setHeight(new Extent(300));
//		this.containerExForDecisions.add(oMap.get_Comp(MANDANT_CONST.HASHKEY_MASK_MANDANT_DECISIONS));
//		//oGrid_7a._a(oMap.get_Comp(MANDANT_CONST.HASHKEY_MASK_MANDANT_DECISIONS), 		new RB_gld()._ins(2, 2, 2,2));
//		oGrid_7a._a(this.containerExForDecisions, 		new RB_gld()._ins(2, 2, 2,2));
//		
		
		oGrid_7a._a(oMap.get_Comp(MANDANT_CONST.HASHKEY_MASK_MANDANT_DECISIONS), 		new RB_gld()._ins(2, 2, 2,2));
		
		
		//2016-09-06: schalter-2 - grid
//		oGrid_7b._a(new RB_lab()._t("Systemeinstellungen...")._b(), 					new RB_gld()._ins(4, 5, 2,10));
		oGrid_7b._a(oMap.get_Comp(MANDANT_CONST.HASHKEY_MASK_MANDANT_CONFIGURATION), 	new RB_gld()._ins(2, 2, 2,2));
		
		this.vMaskGrids.add(oGrid_0);
		this.vMaskGrids.add(oGrid_1);
		this.vMaskGrids.add(oGrid_2);
		this.vMaskGrids.add(oGrid_5);
		this.vMaskGrids.add(oGrid_3);
		this.vMaskGrids.add(oGrid_4);
		this.vMaskGrids.add(oGrid_6);
		this.vMaskGrids.add(oGrid_7);
		this.vMaskGrids.add(oGrid_7a);
		this.vMaskGrids.add(oGrid_7b);
		this.vMaskGrids.add(oGrid_8);
		this.vMaskGrids.add(oGrid_9);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException {
		return this.vMaskGrids;
	}

}
