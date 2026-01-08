package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF__BasicModuleContainer_PopUp_BeforeExecute_Container;
import panter.gmbh.Echo2.ActionEventTools.V_Single_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextFieldSmallKursiv;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextFieldLABEL;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_LIEFERBEDINGUNGEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_DB_COMBO_LaenderCode;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_DB_SELECT_INTRASTAT_MELDUNG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_CB_KEIN_KONTRAKT_NOETIG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_CB_ManuellPreis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_CB_NormaleAbrechnungsMenge;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_CB_OHNE_ABRECHNUNG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_CB_OHNE_AVV_CHECK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_SELECT_FIELD_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__PRUEFBLOCK_MengenEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___MENGENTEXT_STYLE_FACTORY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_FUO_ABZUGSLISTE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_Alt_Lieferbedingung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_AnzeigeFremdWaehrung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_BuchMge_ist_LadeMge;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_Mengenfreigabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_Preisfreigabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_Steuervermerk;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_VGL_verify_id_handelsdef;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Valid_Fremdware_und_Fremdwaren_Lager;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_STEUERDEF;

public class FUO_MASK_ComponentMAP extends E2_ComponentMAP implements	IF__BasicModuleContainer_PopUp_BeforeExecute_Container
{
	private FU_DAUGHTER_ORTE  	oFU_FUO_DaughterComponent = null;
	private String				c_EK_VK = null;

	public FUO_MASK_ComponentMAP(String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(new FUO_MASK_SQLFieldMAP(cEK_VK, FUO_DaugherComponent));
		
		this.c_EK_VK = cEK_VK;
		boolean bEK = this.c_EK_VK.equals("EK");

		this.oFU_FUO_DaughterComponent = FUO_DaugherComponent;
	
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		String[] cFieldsStandard = {	
						"DEF_QUELLE_ZIEL",
						"PRINT_EU_AMTSBLATT",
						}; 


		String[] cFieldsStandardMAX130 = {	
						"NAME1                  <L350L>",
						"NAME2                  <L350L>",
						"NAME3                  <L350L>",
						"ORTZUSATZ              <L350L>",
						"PLZ                    <L63L>",
						"STRASSE                <L291L>",
						"HAUSNUMMER             <L50L>",
						"ORT                    <L203L>",
						"TEL                    <L350L>",
						"FAX                    <L350L>",
						"ARTBEZ1                <L350L>",
						"ARTBEZ2                <L350L>  <H5H>",
						"BESTELLNUMMER         	<L180L>",
						"POSTENNUMMER         	<L163L><TTPostennummer / Externe EingangsnummerTT>",
						"OEFFNUNGSZEITEN       	<L480L>",
						"NATIONALER_ABFALL_CODE <L250L>",
						"AVV_AUSSTELLUNG_DATUM  <L250L>",
						"NOTIFIKATION_NR        <L224L>",
						"ANTEIL_PLANMENGE       <L95L>",
						"ANTEIL_LADEMENGE       <L95L>",
						"ANTEIL_ABLADEMENGE     <L95L>",
						"ABZUG_MENGE              <L70L><F-2F><TTSumme Abzüge auf Menge (reduzieren Lagermenge - im Gegensatz zu anhaftenden Abzügen)TT>",
						"EPREIS_RESULT_NETTO_MGE  <L70L><F-2F><TTResultierender Einzelpreis auf Nettomenge (EK) TT>",
						"EU_BLATT_MENGE         <L80L>",
						"EU_BLATT_VOLUMEN       <L80L>",
						"ANR1                   <L50L>   <DA1DA>",
						"ANR2                   <L50L>   <DA1DA>",
						"EUNOTIZ                  <L700L>    <H4H>",
						"EUCODE                   <L100L>",
						"BEMERKUNG                <L700L>    <H6H>",
						"BASEL_NOTIZ              <L700L>    <H4H>",
						"BASEL_CODE               <L100L>",
						"ZOLLTARIFNR              <L100L>",

						}; 

		
		String[] cFieldsStandardMAX80 = {	
							"WIEGEKARTENKENNER"
						}; 

		
		
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, 		cFieldsStandard, 		oFM, false, false, this, 400);
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandardMAX130, cFieldsStandardMAX130, 	oFM, false, false, this, 130);
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandardMAX80, cFieldsStandardMAX80, 	oFM, false, false, this, 80);


		this.add_Component(new FUO_MASK_DB_SEARCH_Kontrakt(oFM.get_("ID_VPOS_KON"),cEK_VK,this.oFU_FUO_DaughterComponent), new MyE2_String("Kontrakt-POS"));
		
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("BUCHUNGSNUMMER_ZUSATZ"),true,50), new MyE2_String("Buchung-Zusatz"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ZEITSTEMPEL"),100,new StyleFactory_TextFieldSmallKursiv()), new MyE2_String("Zeitstempel"));

		
		
		this.add_Component(new FUO_MASK_DB_COMBO_LaenderCode(oFM.get_("LAENDERCODE")), new MyE2_String("Ländercode des Zusatzortes"));
		
		//suchfeld fuer sortenbezeichner
		this.add_Component(new FUO_MASK_DB_SEARCH_SORTE_BEZ(oFM.get_("ID_ARTIKEL_BEZ"),cEK_VK,this.oFU_FUO_DaughterComponent), new MyE2_String("ID_ARTIKEL_BEZ"));
		//nur anzeige fuer die artikel_id
		this.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("ID_ARTIKEL"),true,80), new MyE2_String("ID_ARTIKEL"));
		
		FUO_MASK_DB_SEARCH_Adresse oSearchAdresse = new FUO_MASK_DB_SEARCH_Adresse(oFM.get_("ID_ADRESSE"),cEK_VK,oFU_FUO_DaughterComponent);
		this.add_Component(oSearchAdresse, new MyE2_String("ID-Adresse"));
		this.add_Component(new FUO_MASK_DB_SEARCH_AdresseLager(oFM.get_("ID_ADRESSE_LAGER"),oSearchAdresse,cEK_VK,oFU_FUO_DaughterComponent), new MyE2_String("ID-Adresse-Lager"));
	
		
		this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFM.get_("LAENDERCODE_TRANSIT1")), new MyE2_String("Transitland 1"));
		this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFM.get_("LAENDERCODE_TRANSIT2")), new MyE2_String("Transitland 2"));
		this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFM.get_("LAENDERCODE_TRANSIT3")), new MyE2_String("Transitland 3"));

		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATUM_LADE_ABLADE")), new MyE2_String("Lade-/Abladedatum"));
		
		//einheit mengen wird zugeladen, nicht aenderbar
		this.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("EINHEIT_MENGEN"),true,50), new MyE2_String("Mengeneineiheit"));
		
		//gefahrgutanzeige
		MyE2_Label labGefahrgut = new MyE2_Label(new MyE2_String("GEFAHRGUT"),MyE2_Label.STYLE_ALARM_LABEL());
		this.add_Component(FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE, labGefahrgut, new MyE2_String("Gefahrgut"));

		
		//2011-09-07: avv-code editierbar machen
		// anzeigefeld fuer die eak-codes
		//this.add_Component(new FUO_MASK_DB_SEARCH_EAK_CODE(oFM.get_("ID_EAK_CODE")), new MyE2_String("AVV-Code"));
		this.add_Component(new ownAVV_Search(oFM.get_("ID_EAK_CODE")), new MyE2_String("AVV-Code"));
		
		
		
		//ladebutton fuer den avv-code aus der fuhre
		this.add_Component(FUO__CONST.HASHKEY_MASK_LADE_AVV_VON_FUHRE,new FUO_MASK_BUTTON_LoadAVV_CodeFromFuhre(cEK_VK,this.oFU_FUO_DaughterComponent), new MyE2_String("Hole AVV-Code aus Fuhre !"));
		
		this.add_Component(new FU__MASK_CB_OHNE_ABRECHNUNG(oFM.get_("OHNE_ABRECHNUNG")), new MyE2_String("ohne Mengenabrechnung"));
		this.add_Component(new FU__MASK_CB_KEIN_KONTRAKT_NOETIG(oFM.get_("KEIN_KONTRAKT_NOETIG")), new MyE2_String("kein VK-Kontrakt nötig"));
		this.add_Component(new FU__MASK_CB_OHNE_AVV_CHECK(oFM.get_("OHNE_AVV_VERTRAG_CHECK")), new MyE2_String("kein AVV-Vertrags-Check"));

		//eine select-field komponente fuer die sonderfaelle, wenn eine fuhre ohne VK-kontrakt ausgeliefert werden soll
		MyE2_DB_SelectField  oSelAusnahmen = new FUO_MASK_DB_SELECT_SonderDef(oFM.get_("ID_VPOS_TPA_FUHRE_SONDER"));
		this.add_Component(oSelAusnahmen, new MyE2_String("Sonderdefinition"));

		//tochter fuer die moeglichen sonderberechnungen/vorgangs-positions-vorlagen
		this.add_Component(FUO__CONST.HASHKEY_DAUGHTER_POS_VORLAGEN,new FUO_MASK_DAUGHTER_POS_VL(oFM,this),new MyE2_String("Vorlagen"));

		MyE2_Grid oGHelp =new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11_W100_H100());
		oGHelp.setColumnWidth(0, new Extent(200));
		this.add_Component(FUO__CONST.HASHKEY_MASK_SHOW_DELETED, oGHelp,new MyE2_String("Gelöscht"));
		
		
		// anzeigefeld fuer die WiegekartenID
		this.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("ID_WIEGEKARTE"),true,80), new MyE2_String("ID-Wiegekarte"));

		//2013-02-04: intrastat - meldung
		this.add_Component(new FU_MASK_DB_SELECT_INTRASTAT_MELDUNG(oFM.get_("INTRASTAT_MELD"),60), 	new MyE2_String("Intrastat "+ (bEK?"Einfuhr":"Ausfuhr")+ " melden oder nicht melden (bei undefiniert wird das automatische Verfahren angewendet)"));

		
		//2013-03-11: fuhrenort-feld transitgeschaeft
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.VPOS_TPA_FUHRE_ORT$TRANSIT), new MyE2_String("Definiert den Status einer Transit-Meldung (>12.500,- EUR an Bundesbank)")), new MyE2_String("Bundesbankmeldung"));

		//2013-05-27: fremdlager-anzeiger
		this.add_Component(FUO__CONST.HASH_MASK_COMP_FREMDWARENLAGER, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String(""));

		
		//2014-02-20: Gelangensbestaetigung
		this.add_Component(new FUO__MASK_CB_GelangensbestaetigungAnAus(oFM.get_(_DB.VPOS_TPA_FUHRE_ORT$GELANGENSBESTAETIGUNG_ERHALTEN),
																	   cEK_VK,this.oFU_FUO_DaughterComponent), new MyE2_String("Gelangensbestätigung erhalten"));			

		
		
		//abzugsliste
		
		//abzugslisten erzeugen
		/*
		 * tochterkomponenten fuer die abzuege definieren
		 */
//		BL_Daughter_Abzuege_FUHREN_AND_VPOS_RG oAbzuege = new BL_Daughter_Abzuege_FUHREN_AND_VPOS_RG(
//							oFM.get_oSQLFieldPKMainTable(),
//							oFM,
//							"ID_VPOS_TPA_FUHRE_ORT",
//							"JT_VPOS_TPA_FUHRE_ORT_ABZUG",
//							this,
//							null,null,null,null,null,
//							null,
//							null,null,null,(MyE2_DB_TextField)this.get__Comp("ABZUG_MENGE"),null,null,null,null,null,null,false);
		
		
		//buchungsfelder
		// ---------------------- BUCHUNGSFELDER
		// ----------------------
		this.add_Component(new FU__MASK_CB_ManuellPreis(	    	oFM.get_("MANUELL_PREIS"),"EINZELPREIS","ID_VPOS_STD","DEF_MANUELL_PREIS"),new MyE2_String("Fuhrenort bekommt einen manuellen Preis"));
		this.add_Component(new MyE2_DB_TextField(					oFM.get_("EINZELPREIS"),true,90,10,false), new MyE2_String("Einzelpreis auf der "+(this.c_EK_VK.equals("EK")?"Gutschriftposition":"Rechnungsposition")));
		this.add_Component(new BS_ComboBox_MWST(					oFM.get_("STEUERSATZ")),	new MyE2_String("MWST. auf der Gutschrift"));
		this.add_Component(new FUO_MASK_DB_SEARCH_PREIS(			oFM.get_("ID_VPOS_STD"),this.c_EK_VK),	new MyE2_String("Angebotsposition "+(this.c_EK_VK.equals("EK")?"Lieferant":"Abnehmer")));
		this.add_Component(new MyE2_DB_TextArea(					oFM.get_("EU_STEUER_VERMERK"),350,4,true, new E2_FontPlain(-2)), new MyE2_String("Steuervermerk "+(this.c_EK_VK.equals("EK")?"Gutschriftposition":"Rechnungsposition")));
		
		this.add_Component(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, 
				new FU__MASK_SELECT_FIELD_STEUERVERMERK((MyE2_DB_TextArea)this.get__Comp("EU_STEUER_VERMERK"),
										cEK_VK,
										this.get__DBComp("STEUERSATZ")),new MyE2_String("Selektion der Moeglichkeiten des Steuertextes"));

		
		//zauberstab zum holen der preise und steuern
		this.add_Component(FUO__CONST.HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER, new FUO_MASK_BUTTON_HOLE_PREIS_STEUER_ZAUBERSTAB(),new MyE2_String("Preis und Steuer holen"));

		
		//pruefblock fuer die mengepruefung
		this.add_Component(FUO__CONST.HASHKEY_PRUEFBLOCK_MENGE, 
				new FU__PRUEFBLOCK_MengenEingabe("PRUEFUNG_MENGE","PRUEFUNG_MENGE_VON","PRUEFUNG_MENGE_AM",bEK?"LADEMENGE_GUTSCHRIFT":"ABLADEMENGE_RECHNUNG","DATUM_LADE_ABLADE",oFM,this,
				"ANTEIL_PLANMENGE","ANTEIL_LADEMENGE","ANTEIL_ABLADEMENGE",bEK), 
				new MyE2_String("Prüfblock Lade-Menge"));

		this.add_Component(new FU__MASK_CB_NormaleAbrechnungsMenge(oFM.get_("LADEMENGE_GUTSCHRIFT"),"ANTEIL_LADEMENGE","ANTEIL_ABLADEMENGE",true), new MyE2_String("Lademenge für Gutschrift"));
		this.add_Component(new FU__MASK_CB_NormaleAbrechnungsMenge(oFM.get_("ABLADEMENGE_RECHNUNG"),"ANTEIL_ABLADEMENGE","ANTEIL_LADEMENGE",false), new MyE2_String("Ablademenge für Rechnung"));


				
		//es werden nur noch die mengenfelder freigegeben, die sinnvollerweise auf dieser "seite" eingetragen werden koennen
		if (this.c_EK_VK.equals("VK"))
		{
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE")).setForeground(Color.DARKGRAY);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE")).setToolTipText(new MyE2_String("Achtung! Feld wird von Automatik überschrieben, wenn Ablademengen vorhanden sind !!").CTrans());
		}
		
		
		
		//neue abzugsliste
		__FU_FUO_ABZUGSLISTE oAbzuege = new __FU_FUO_ABZUGSLISTE(	oFM, 	
				"ID_VPOS_TPA_FUHRE_ORT",
				"JT_VPOS_TPA_FUHRE_ORT_ABZUG", 
				this,
				bEK?RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_LADEMENGE:RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_ABLADEMENGE,
				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EINZELPREIS,
				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ABZUG_MENGE,
				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EPREIS_RESULT_NETTO_MGE);


		oAbzuege.set_oContainerExScrollHeight(new Extent(400));
		this.add_Component(FUO__CONST.HASHKEY_MASK_ABZUG_FUHRE_ORT,oAbzuege,new MyE2_String("Abzüge"));

		
		//2013-09-30: feld, das die handelsdefinition aufnimmt
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.VPOS_TPA_FUHRE_ORT$ID_HANDELSDEF), true, 60, 10, true, new E2_FontPlain()), new MyE2_String("USt./Steuertext"));
		this.add_Component(FUO__CONST.HASH_MASK_ERASE_ID_HANDELSDEF_FUO,new FUO_MASK_BT_Loesche_ID_HANDELSDEF(),new MyE2_String("Löscht die USt./Steuertext-ID! \nDamit können Steuer und Steuertext manuell vergeben werden!"));
		//20181127: neue komponente zum direkt bearbeiten der handelsdef
		this.add_Component(FUO__CONST.HASH_MASK_EDIT_ID_HANDELSDEF_FUO,new FUO_MASK_BtOpenSteuerregel(),new MyE2_String("Öffnen der Steuer-Regel / Handelsdefinition"));

		
		//2017-01-16: aktive Label-komponenten
		this.add_Component(FUO__CONST.ACTIVLABEL_LADE_ABLADE_MENGE_IST_BUCHUNGSMENGE, new RB_lab()._tr("<beschriftung>")._fsa(-2)._i(), new MyE2_String(""));

		
		
		//2011-12-09: fuhrenort-felder im anhang 7 nur auf der abladeseite gueltig
		if (this.c_EK_VK.equals("EK"))
		{
			this.get__Comp("EU_BLATT_MENGE").EXT().set_bDisabledFromBasic(true);
			this.get__Comp("EU_BLATT_VOLUMEN").EXT().set_bDisabledFromBasic(true);
		}
		
		
		
		//2013-01-25: preisfreigabe
		this.add_Component(new MyE2_DB_CheckBox( oFM.get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS)), new MyE2_String("Preis als geprüft markieren"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS_VON),false,30,10,true,new E2_FontPlain(-2)), new MyE2_String("Preis wurde von <Kürzel> geprüft"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS_AM),false,60,10,true,new E2_FontPlain(-2)), new MyE2_String("Preis wurde geprüft am"));


		// tax-selektoren umschaltbar
		if (ENUM_MANDANT_DECISION.USE_GROUPED_TAX_SELECTOR.is_YES_FromSession()) {
			FUO_MASK_SelectTaxViaPopup popTax = new FUO_MASK_SelectTaxViaPopup(oFM.get_(VPOS_TPA_FUHRE_ORT.id_tax));
			
			this.add_Component(popTax,			new MyE2_String("Steuer-Definition festlegen.."));
			
			//noetig, um fuer interactive_setting_validating - routinen die id der maskenkomponente an die eigentlichen buttons zu uebertragen
			popTax._setOwnHashkeyToAllButtonEndpointIds();
		} else {
			TAX__DD_STEUERDEF steuer = new TAX__DD_STEUERDEF(oFM.get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX),70,false);
			steuer.setFont(new E2_FontPlain(-2));

			this.add_Component(steuer,	new MyE2_String("Steuer-Definition festlegen.."));
		}

		//2018-06-04: infoblock fuer die anzeige des Fremdwaehrungsstatus der Firmen
		this.add_Component(FUO__CONST.FIELDNAME_INFOBOCK_WAEHRUNGEN, new FUO_MASK_InfoBlockWaehrungen(), S.ms("Fremdwährungsindikator"));

		
		
		//spezielle StyleFactorys fuer die Anteil-Mengen-Felder einfuehren, die das jeweilige gueltige feld markiert
		if (bEK)
		{
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE")).EXT().set_STYLE_FACTORY(new FU___MENGENTEXT_STYLE_FACTORY("LADEMENGE_GUTSCHRIFT",this,true));
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_ABLADEMENGE")).EXT().set_STYLE_FACTORY(new FU___MENGENTEXT_STYLE_FACTORY("LADEMENGE_GUTSCHRIFT",this,false));
		}
		else
		{
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_ABLADEMENGE")).EXT().set_STYLE_FACTORY(new FU___MENGENTEXT_STYLE_FACTORY("ABLADEMENGE_RECHNUNG",this,true));
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE")).EXT().set_STYLE_FACTORY(new FU___MENGENTEXT_STYLE_FACTORY("ABLADEMENGE_RECHNUNG",this,false));
		}
		
		
		
		((MyE2IF__Component)this.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EPREIS_RESULT_NETTO_MGE)).EXT().set_bDisabledFromBasic(true);
		((MyE2IF__Component)this.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ABZUG_MENGE)).EXT().set_bDisabledFromBasic(true);


		//2014-06-02: neue felder fuer alternative lieferbedingung
		BS_ComboBox_LIEFERBEDINGUNGEN oLFBED_Alternativ = new BS_ComboBox_LIEFERBEDINGUNGEN(oFM.get_(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV),300,3);
		this.add_Component(oLFBED_Alternativ, new MyE2_String("Lieferbedingungen (alternativ)"));

		FU_AL_Component oFU_AL_Component = new FU_AL_Component(oLFBED_Alternativ, this,	 _DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, 
																						 _DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV, 
																						 _DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_KON, 
																						 _DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_STD, 
																						 bEK?_DB.ADRESSE$ID_LIEFERBEDINGUNGEN:_DB.ADRESSE$ID_LIEFERBEDINGUNGEN_VK);
		this.add_Component(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV, oFU_AL_Component,  new MyE2_String("Lieferbedingungen alternativ"));
		int[] iWidthHelp = {352,50};
		oFU_AL_Component.set__ColWidth(iWidthHelp);

		
		//2013-01-25: die Validierer der neuen steuer-def-felder setzen
		FUO_Set_And_Valid_Steuervermerk  oSteuerValidierer = new FUO_Set_And_Valid_Steuervermerk(this);
		this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX, oSteuerValidierer);
		this.get_hmInteractiv_settings_validation().put_(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, oSteuerValidierer);
		//2013-01-25: steuervalidierer einstellen
		this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS, new FUO_Set_And_Valid_Preisfreigabe());
		//2013-01-25: das handling der mengenfreigabe auch auf die SET_AND_VALID-klassen legen
		this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE, new FUO_Set_And_Valid_Mengenfreigabe());
		this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__LADEMENGE_GUTSCHRIFT, new FUO_Set_And_Valid_BuchMge_ist_LadeMge());
		this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ABLADEMENGE_RECHNUNG, new FUO_Set_And_Valid_BuchMge_ist_LadeMge());
		
		
		//2013-05-27: validierung der fuhre auf Richtigkeit der Lagertype und des typs eigenware/fremdware
		this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, new FUO_Valid_Fremdware_und_Fremdwaren_Lager());

		//2013-05-16: validierung der fuhre auf Richtigkeit der avv-positivliste gegen die abladeadresse
		this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, 
							new FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE_LAGER, _DB.VPOS_TPA_FUHRE_ORT$ID_EAK_CODE));

		
		//2013-10-02: validierung der fuhre auf uebereinstimmung id_handelsdef mit den feldern in der maske
		this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, 
							new FUO_Set_And_Valid_VGL_verify_id_handelsdef());

		//2014-06-02: alternative lieferbedingung behandeln
		this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV, new FUO_Set_And_Valid_Alt_Lieferbedingung());
		
		//2013-01-25: die action-agents der maske
		((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR());
		((E2_IF_Handles_ActionAgents)this.get__Comp(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
		//2013-01-25: steuervalidierer einstellen
		((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
		//2013-01-25: das handling der mengenfreigabe auch auf die SET_AND_VALID-klassen legen
		((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
		((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__LADEMENGE_GUTSCHRIFT)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
		((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ABLADEMENGE_RECHNUNG)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new FUO_MASK_MapSettingAgent(cEK_VK, FUO_DaugherComponent));
		
		//abzugsliste durchrechnen
		this.add_oMAPValidator(new FUO_MASK_MAP_Validator_CalcAbzugsliste(this.c_EK_VK));
		
		//2011-04-21: neue validierung fuer die sortengleichheit 
		this.add_oMAPValidator(new FUO_MASK_MAP_Validator_PruefeEK_VK_Sortenbedingung());
		
		
		//2013-01-17: status fremdware muss mit der hauptfuhre uebereinstimmen
		this.add_oMAPValidator(new ownMapValidator_preufe_Gleichheit_Status_Fremdwarenlieferung());
		
		//2013-05-28: 	neues XX_MAP_Set_And_Valid-object, das beim Speichern geprueft werden muss 
		//         		(Fremdware darf nur zu eignen lagern gehen, wenn diese eine Fremdwaren-definition haben)
		this.add_oMAPValidator(	new ownMapValidatorBeforeSave_XX_MAP_Set_And_Valid_VECTOR());

		
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new FUO_MASK_FORMATING_Agent());
		
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new ownZusatzStatemtens());
		
		
		/**
		 * 2017-01-23: neue methoden zum aufbau der verbindung zu den fuhrenbasierten fixis
		 */
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new own_addon_statement_builders());

		//2018-06-05: neuer map-setter fuer die anzueige der Fremdwaehrungen
		this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, new FUO_Set_And_Valid_AnzeigeFremdWaehrung());

	}

	
	
	
	//2013-01-03: den validierer in den klicks einsetzen
	private class ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FUO_MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(FUO_MASK_ComponentMAP.this, oExecInfo);
		}
	}
	

	private class ownMapValidatorBeforeSave_XX_MAP_Set_And_Valid_VECTOR extends XX_MAP_ValidBeforeSAVE
	{
		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			return FUO_MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(FUO_MASK_ComponentMAP.this, oInputMap);
		}
	}
	

	
	
	
	/*
	 * zusatzstatements, liefern dummy-statement, damit die Fuhre im Daemon behandelt wird 
	 * die fuhrenmaske aendert
	 */
	private class ownZusatzStatemtens implements E2_ComponentMAP.builder_AddOnSQL_STATEMENTS
	{

		@Override
		public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(	E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP, MyE2_MessageVector oMV)	throws myException
		{
			return createStatements(oMV);
		}

		@Override
		public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP, MyE2_MessageVector oMV)	throws myException
		{
			return createStatements(oMV);
		}
		
		private Vector<String> createStatements(MyE2_MessageVector oMV) 	throws myException
		{
			
			// hier muss die zugrundeliegende fuhren-maske auch gespeichert werden, damit gegenseitige validierungen moeglich sind
			FUO_MASK_ComponentMAP 			oThis = 				FUO_MASK_ComponentMAP.this;
			E2_BasicModuleContainer_MASK 	oMaskMother = 			oThis.oFU_FUO_DaughterComponent.EXT().get_ModuleContainer_MASK_this_BelongsTo();
			E2_NavigationList  				oListMother = 			oMaskMother.get_oNavigationListWhichBelongsToTheMask();
			
			E2_SaveMaskStandard  oSaveStandardFuhreOderTPAPOS = new E2_SaveMaskStandard(oMaskMother,oListMother);
			
			Vector<String> vSQL = new Vector<String>();
			
			oMV.add_MESSAGE(oSaveStandardFuhreOderTPAPOS.doSaveMask_DUMMY(vSQL, true));

			if (oMV.get_bIsOK())
			{
				//zuerst wird die obere maske manipuliert und es werden die die Datenbankfelder gespeichert
				String cID_FUHRE = FUO_MASK_ComponentMAP.this.oFU_FUO_DaughterComponent.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				vSQL.add("UPDATE JT_VPOS_TPA_FUHRE SET ERZEUGT_VON=ERZEUGT_VON WHERE ID_VPOS_TPA_FUHRE="+cID_FUHRE);
				
				return vSQL;
			}
			
			return new Vector<String>();
			
		}

		
		
	}




	public FU_DAUGHTER_ORTE get_oFU_FUO_DaughterComponent()
	{
		return oFU_FUO_DaughterComponent;
	}




	public String get_cEK_VK()
	{
		return c_EK_VK;
	}




	@Override
	public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_BasicModuleContainer_PopUp_BeforeExecute() throws myException
	{
		
		return new V_Single_BasicModuleContainer_PopUp_BeforeExecute(new FUO__POPUP_BeforeSaveFuhre_Check_Kontrakt());
	}
	
	
	
	//2011-09-07: avv-code in der fuhre veraenderbar
	private class ownAVV_Search extends MASK_COMPONENT_SEARCH_EAK_CODES
	{
		public ownAVV_Search(SQLField osqlField) throws myException 
		{
			super(osqlField, null, null);
			this.get_oTextForAnzeige().setFont(new E2_FontItalic());
			this.get_oTFDatenFeldWithID().setFont(new E2_FontItalic());

			this.get_oTextForAnzeige().set_iWidthPixel(300);
		}
	}


	
	
	
	/*
	 * spezieller validierer, der die gleichheit der fremdwareneinstellungen zwischen fuhre und fuhrenort sicherstellt
	 */
	private class ownMapValidator_preufe_Gleichheit_Status_Fremdwarenlieferung extends XX_MAP_ValidBeforeSAVE
	{
		
		
		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMAP_FUO, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			MyE2_MessageVector oMV = 		new MyE2_MessageVector();
			E2_ComponentMAP    oMAP_FU = 	FUO_MASK_ComponentMAP.this.oFU_FUO_DaughterComponent.EXT().get_oComponentMAP();
			
			String  cFremdWare_FU = 		(oMAP_FU.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_ABRECHNUNG)).get_cActualDBValueFormated();
			String 	cID_FremdAuftrag_FU = 	(oMAP_FU.get__DBComp( RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_FREMDAUFTRAG)).get_cActualDBValueFormated();

			boolean bMustBeFremdauftrag = (cFremdWare_FU.equals("Y") || S.isFull(cID_FremdAuftrag_FU));
			
			if (bMustBeFremdauftrag && oMAP_FUO.get_cActualDBValueFormated(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__OHNE_ABRECHNUNG).equals("N"))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! Der Status <Fremdware> ist hier nicht aktiviert, aber in der Hauptfuhre ist der Status <Fremdware> definiert. Bitte gleichen Sie diesen Wert an!!")));
			}
			else if (!bMustBeFremdauftrag && oMAP_FUO.get_cActualDBValueFormated(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__OHNE_ABRECHNUNG).equals("Y"))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! Der Status <Fremdware> ist hier aktiviert, aber in der Hauptfuhre ist der Status <KEINE Fremdware> definiert. Bitte gleichen Sie diesen Wert an!!")));
			}
				
			return oMV;
		}
	}

	
	
	
	
	
	
	
	/**
	 * 2017-01-23: neue klassen zum fuellen der (falls es welche sind) transport-fixies zum aufbau der verbindung zu den fuhrenbasierten fixis
	 */
	private class own_addon_statement_builders implements builder_AddOnSQL_STATEMENTS {
		@Override
		public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP, 	MyE2_MessageVector oMV) throws myException {
			return this.build_korrect_sql_4_vpos_kon(oE2_ComponentMAP);
		}

		@Override
		public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP,  	MyE2_MessageVector oMV) throws myException {
			return this.build_korrect_sql_4_vpos_kon(oE2_ComponentMAP);
		}


		private Vector<String> build_korrect_sql_4_vpos_kon(E2_ComponentMAP map) throws myException {
			Vector<String>  v_return = new Vector<>();
			
			boolean bEK = FUO_MASK_ComponentMAP.this.c_EK_VK.equals("EK");

			
			MyLong   id_vpos_kon = new MyLong(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.id_vpos_kon.fn()));
			
			if (id_vpos_kon!=null && id_vpos_kon.isOK()) {
				try {
					Rec20 rec_vpos_kon = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon.get_lValue());
					if (rec_vpos_kon.is_yes_db_val(VPOS_KON.typ_ladung)) {
						MyBigDecimal bd_menge = null;
						if (bEK) {
							if (map.get_bActualDBValue(VPOS_TPA_FUHRE_ORT.lademenge_gutschrift.fn())) {
								bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.anteil_lademenge.fn()));
							} else {
								bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.anteil_ablademenge.fn()));
							}
						} else {
							if (map.get_bActualDBValue(VPOS_TPA_FUHRE_ORT.ablademenge_rechnung.fn())) {
								bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.anteil_ablademenge.fn()));
							} else {
								bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.anteil_lademenge.fn()));
							}
						}
						
						if (bd_menge != null && bd_menge.isOK()) {
							rec_vpos_kon.nv(VPOS_KON.anzahl, bd_menge.get_bdWert(), bibMSG.MV());
							String sql=rec_vpos_kon.get_sql_4_save(true);
							if (S.isFull(sql)) {
								v_return.add(sql);
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Beim Speichern wird die Kontraktpositionsmenge auf ").ut(bd_menge.get_FormatedRoundedNumber(3)).t(" gesetzt !")));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung ! Fehler beim Schreiben der Menge in die Kontrakt-Position! ")));
				}
			}
			return v_return;
		}
		
	}
	
	
	
}
