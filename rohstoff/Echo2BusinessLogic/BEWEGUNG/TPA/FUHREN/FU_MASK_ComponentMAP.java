package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF__BasicModuleContainer_PopUp_BeforeExecute_Container;
import panter.gmbh.Echo2.ActionEventTools.V_Single_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField_20Hoch_Font2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModulContainer_MASK_ADDON_IN_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.UP_DOWN_GenericButton4Masks;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.UP_DOWN_GenericDownloadCollector;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField4HH_MM;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextFieldLABEL;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_WithSelektorEASY;
import panter.gmbh.Echo2.components.E2_calendar.MD_bt_multiPopupCalender;
import panter.gmbh.Echo2.components.MultiValueSelector.MultiValueSelector_SaveKeySizeofPopup;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VERARBEITUNG;
import panter.gmbh.basics4project.DB_ENUMS.VERPACKUNGSART;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.GEO_FAHRPLAN.GEO_FAHR_Routing_bt_show;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_LIEFERBEDINGUNGEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__DB_TextFieldZolltarifNummer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP_DB_SelectFieldContainerTypen;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN.FUK__MASK_KostenFullDaughterInlay;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FU_DAUGHTER_ORTE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.Routen.FU_MASK_btRoutingEntfernung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_Alt_Lieferbedingung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_AnzeigeFremdWaehrung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_BuchMge_ist_LadeMge;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_FuhrenAbschluss;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_Mengenfreigabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_Preisfreigabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_Steuervermerk;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_VGL_verify_id_handelsdef;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Valid_Fremdware_und_Fremdwaren_Lager;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_STEUERDEF;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_VERANTWORTUNG;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;


public class FU_MASK_ComponentMAP extends E2_ComponentMAP  implements 
															IF__BasicModuleContainer_PopUp_BeforeExecute_Container,
															E2_BasicModulContainer_MASK_ADDON_IN_MASK
{
	
	
	private String[] cFieldList=		{	
											"AUFLADEN_BRUTTO",
											"AUFLADEN_TARA",
											"ABLADEN_BRUTTO",
											"ABLADEN_TARA",
											};

	private String[] cFieldListMax100=		{	
											"ABZUG_LADEMENGE_LIEF          <L70L><F-2F><TTSumme Abzüge auf Lademenge (reduzieren Lagermenge - im Gegensatz zu anhaftenden Abzügen)TT>",
											"ABZUG_ABLADEMENGE_ABN         <L70L><F-2F><TTSumme Abzüge auf Ablademenge (reduzieren Lagermenge - im Gegensatz zu anhaftenden Abzügen)TT>",
											"EPREIS_RESULT_NETTO_MGE_EK    <L70L><F-2F><TTResultierender Einzelpreis auf Nettomenge (EK) TT>",
											"EPREIS_RESULT_NETTO_MGE_VK    <L70L><F-2F><TTResultierender Einzelpreis auf Nettomenge (VK) TT>",
											};

	private String[] cFieldListMax80=		{	
											"WIEGEKARTENKENNER_LADEN",
											"WIEGEKARTENKENNER_ABLADEN",
											};

	
	
	private String[] cFieldsTextAVV=	{	
											"BASEL_CODE",
											"AVV_AUSSTELLUNG_DATUM <L70L><F-2F><TTAusstellungsdatum EU-AmtsblattTT>",
											"NOTIFIKATION_NR      <L135L><F-2F>",			  
											"NOTIFIKATION_NR_EK   <L135L><F-2F>",			  
											"EU_BLATT_MENGE       <L70L><F-2F><TTMengenangabe im EU-AmtsblattTT>",
											"EU_BLATT_VOLUMEN     <L70L><F-2F><TTVolumenangabe im EU-AmtsblattTT>",
											};

	private String[] cFieldsTextDefLen=	{
											"L_NAME1                  <L350L>",
											"L_NAME2                  <L350L>",
											"L_NAME3                  <L350L>",
											"L_ORTZUSATZ              <L350L>",
											"L_PLZ                    <L63L>",
											"L_STRASSE                <L291L>",
											"L_HAUSNUMMER             <L50L>",
											"L_ORT                    <L210L>",
											"A_NAME1                  <L350L>",
											"A_NAME2                  <L350L>",
											"A_NAME3                  <L350L>",
											"A_ORTZUSATZ              <L350L>",
											"A_PLZ                    <L63L>", 
											"A_HAUSNUMMER             <L50L>",
											"A_STRASSE                <L291L>",
											"A_ORT                    <L210L>",
											"TEL_LIEFERANT            <L350L>",
											"FAX_LIEFERANT            <L350L>",
											"TEL_ABNEHMER             <L350L>",
											"FAX_ABNEHMER             <L350L>",
											"ARTBEZ1_EK               <L350L>",
											"ARTBEZ1_VK               <L350L>",
											"ORDNUNGSNUMMER_CMR       <L350L>",
											"BUCHUNGSNR_FUHRE         <L70L>              <DA1DA>",
											"EK_KONTRAKTNR_ZUSATZ     <L50L>",
											"VK_KONTRAKTNR_ZUSATZ     <L50L>",
											"EUNOTIZ                  <L700L>    <H4H>",
											"EUCODE                   <L100L>",
											"BEMERKUNG                <L805L>    <H6H>",
											"BEMERKUNG_SACHBEARBEITER <L810L>    <H6H>",
											"MENGE_VORGABE_KO         <L95L>                <DA1DA>",
											"MENGE_AUFLADEN_KO        <L95L>                <DA1DA>",
											"MENGE_ABLADEN_KO         <L95L>                <DA1DA>",
											"ANTEIL_PLANMENGE_LIEF    <L90L>",
											"ANTEIL_LADEMENGE_LIEF    <L90L>",
											"ANTEIL_ABLADEMENGE_LIEF  <L90L>                ",
											"ANTEIL_PLANMENGE_ABN     <L90L>",
											"ANTEIL_LADEMENGE_ABN     <L90L>                ",
											"ANTEIL_ABLADEMENGE_ABN   <L90L>",
											"ANR1_EK                  <L50L>               <DA1DA>",
											"ANR1_VK                  <L50L>               <DA1DA>",
											"ANR2_EK                  <L50L>               <DA1DA>",
											"ANR2_VK                  <L50L>               <DA1DA>",
											"ZEITSTEMPEL              <L120L>              <DA1DA>",
											"NATIONALER_ABFALL_CODE   <L215L>",
											"ALTE_LIEFERSCHEIN_NR     <L60L>",
											"BASEL_NOTIZ              <L700L>    <H4H>",
											"TRANSPORTKENNZEICHEN     <L80L>",
											"ANHAENGERKENNZEICHEN     <L80L>",
											"ARTBEZ2_EK               <L350L>   <H6H>",
											"ARTBEZ2_VK               <L350L>   <H6H>",
											"OEFFNUNGSZEITEN_LIEF     <L350L>",
											"OEFFNUNGSZEITEN_ABN      <L350L>",
											"BESTELLNUMMER_EK         <L145L>",
											"BESTELLNUMMER_VK         <L145L>",
											"POSTENNUMMER_EK          <L145L><TTPostennummer Ladeseite / Externe EingangsnummerTT>",
											"POSTENNUMMER_VK          <L145L><TTPostennummer Abladeseite / Externe EingangsnummerTT>",
											
			
										};
	

	
	//2013-01-03: neue steuerdropdowns validieren
	private FU_Set_And_Valid_Steuervermerk oSteuerValidiererEK  = null;
	private FU_Set_And_Valid_Steuervermerk oSteuerValidiererVK  = null;
	
	
	//2013-01-18: neue Validierer fuer die Preisfreigabe
	private FU_Set_And_Valid_Preisfreigabe     oPreisFreigabeValidiererEK = null;
	private FU_Set_And_Valid_Preisfreigabe     oPreisFreigabeValidiererVK = null;
	
	private String  					 		modulkenner_mask = null;
	
	public FU_MASK_ComponentMAP(	FU_MASK_SQLFieldMAP             oFuhrenSQLFieldMAP, 
									boolean   						bMitSpeditionsFeld,
									boolean   						bAddOpenTPA_Button,
									String                          MODULKENNER_MASK) throws myException
	{
		super(oFuhrenSQLFieldMAP);
		
		this.modulkenner_mask = MODULKENNER_MASK;
		
		try
		{
			this.oSteuerValidiererEK = new FU_Set_And_Valid_Steuervermerk(this, "EK");
			this.oSteuerValidiererVK = new FU_Set_And_Valid_Steuervermerk(this, "VK");
			
			this.oPreisFreigabeValidiererEK = new FU_Set_And_Valid_Preisfreigabe("EK");
			this.oPreisFreigabeValidiererVK = new FU_Set_And_Valid_Preisfreigabe("VK");

			
			SQLFieldMAP oFieldMAP = this.get_oSQLFieldMAP();
			oFieldMAP.initFields();
			
			MaskComponentsFAB.addStandardComponentsToMAP(cFieldsTextDefLen,cFieldsTextDefLen,this.get_oSQLFieldMAP(),false,false, this, 400);
			MaskComponentsFAB.addStandardComponentsToMAP(cFieldList,cFieldList,this.get_oSQLFieldMAP(),false,false, this, 350);
			MaskComponentsFAB.addStandardComponentsToMAP(cFieldListMax100,cFieldListMax100,this.get_oSQLFieldMAP(),false,false, this, 100);
			MaskComponentsFAB.addStandardComponentsToMAP(cFieldListMax80,cFieldListMax80,this.get_oSQLFieldMAP(),false,false, this, 80);
			MaskComponentsFAB.addStandardComponentsToMAP(cFieldsTextAVV,cFieldsTextAVV,this.get_oSQLFieldMAP(),false,false, this, 200);
			
			this.add_Component(new MyE2_DB_Label(oFieldMAP.get_SQLField("ID_VPOS_TPA_FUHRE")),new MyE2_String("ID"));
			
			this.add_Component(new MyE2_DB_CheckBox(oFieldMAP.get_("FUHRE_IST_UMGELEITET")), new MyE2_String("Transport-Position ist umgeleitet"));

			this.add_Component(new MyE2_DB_CheckBox(oFieldMAP.get_("PRINT_EU_AMTSBLATT")), new MyE2_String("Drucke Amtsblatt"));
			
			//2011-02-12: zolltarifnummer mit text im tooltip
			this.add_Component(new BS__DB_TextFieldZolltarifNummer(oFieldMAP.get_("ZOLLTARIFNR"),true,70,50,false, new E2_FontPlain(-2)), new MyE2_String("Zolltarifnummer"));

			// aenderung AVV
			this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFieldMAP.get_("LAENDERCODE_TRANSIT1")), new MyE2_String("Transitland 1"));
			this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFieldMAP.get_("LAENDERCODE_TRANSIT2")), new MyE2_String("Transitland 2"));
			this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFieldMAP.get_("LAENDERCODE_TRANSIT3")), new MyE2_String("Transitland 3"));

			this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFieldMAP.get_("L_LAENDERCODE")), new MyE2_String("Ländercode Lieferant"));
			this.add_Component(new FU_MASK_DB_COMBO_LaenderCode(oFieldMAP.get_("A_LAENDERCODE")), new MyE2_String("Ländercode Abnehmer"));
			this.add_Component(new FU_MASK_DB_COMBO_MengenEinheit(oFieldMAP.get_("EINHEIT_MENGEN")), new MyE2_String("Mengeneinheit"));

			this.add_Component(new FU_MASK_DB_COMBO_TransportMittel(oFieldMAP.get_("TRANSPORTMITTEL")),new MyE2_String("Transportmittel"));
			this.add_Component(new FU_MASK_DB_SEARCH_Adresse(oFieldMAP.get_("ID_ADRESSE_START"),"EK"),new MyE2_String("Lieferant-Adresse - ID"));
			this.add_Component(new FU_MASK_DB_SEARCH_Adresse(oFieldMAP.get_("ID_ADRESSE_ZIEL"),"VK"),new MyE2_String("Abnehmer-Adresse - ID"));
	
			this.add_Component(new FU_MASK_DB_SEARCH_AdresseLager(oFieldMAP.get_("ID_ADRESSE_LAGER_START"),"EK"),new MyE2_String("Lieferant-Lager-Adresse - ID"));
			this.add_Component(new FU_MASK_DB_SEARCH_AdresseLager(oFieldMAP.get_("ID_ADRESSE_LAGER_ZIEL"),"VK"),new MyE2_String("Abnehmer-Lager-Adresse - ID"));
	
			this.add_Component(new FU_MASK_DB_SEARCH_SORTE( oFieldMAP.get_("ID_ARTIKEL")),new MyE2_String("Sorte"));
	
			this.add_Component(new FU_MASK_DB_SEARCH_Kontrakt(oFieldMAP.get_("ID_VPOS_KON_EK"),"EK"),new MyE2_String("EK-Kontrakt"));
			this.add_Component(new FU_MASK_DB_SEARCH_Kontrakt(oFieldMAP.get_("ID_VPOS_KON_VK"),"VK"),new MyE2_String("VK-Kontrakt"));
	
			this.add_Component(new FU_MASK_DB_SEARCH_SORTE_BEZ(oFieldMAP.get_("ID_ARTIKEL_BEZ_EK")),	new MyE2_String("ID-Art-Bez.EK"));
			this.add_Component(new FU_MASK_DB_SEARCH_SORTE_BEZ(oFieldMAP.get_("ID_ARTIKEL_BEZ_VK")),	new MyE2_String("ID-Art-Bez.VK"));
			
			//2011-09-07: AVV-Code in der maske aenderbar machen
			// anzeigefeld fuer die eak-codes
			//this.add_Component(new FU_MASK_DB_SEARCH_EAK_CODE(oFieldMAP.get_("ID_EAK_CODE")), new MyE2_String("AVV-Code"));
			this.add_Component(new ownAVV_Search(oFieldMAP.get_("ID_EAK_CODE")), new MyE2_String("AVV-Code"));
			
			// anzeigefeld fuer die WiegekartenID
			this.add_Component(new MyE2_DB_TextFieldLABEL(oFieldMAP.get_("ID_WIEGEKARTE_LIEF"),true,80), new MyE2_String("ID-Wiegekarte(Lief)"));
			this.add_Component(new MyE2_DB_TextFieldLABEL(oFieldMAP.get_("ID_WIEGEKARTE_ABN"),true,80), new MyE2_String("ID-Wiegekarte(ABN)"));
			
			
			this.add_Component(FU___CONST.HASH_KEY_PRUEFBLOCK_MENGE_EK, 
									new FU__PRUEFBLOCK_MengenEingabe("PRUEFUNG_LADEMENGE","PRUEFUNG_LADEMENGE_VON","PRUEFUNG_LADEMENGE_AM","LADEMENGE_GUTSCHRIFT","DATUM_AUFLADEN",oFieldMAP,this,
									"ANTEIL_PLANMENGE_LIEF","ANTEIL_LADEMENGE_LIEF","ANTEIL_ABLADEMENGE_LIEF",true ), 
									new MyE2_String("Prüfblock Lade-Menge"));

			this.add_Component(FU___CONST.HASH_KEY_PRUEFBLOCK_MENGE_VK, 
									new FU__PRUEFBLOCK_MengenEingabe("PRUEFUNG_ABLADEMENGE","PRUEFUNG_ABLADEMENGE_VON","PRUEFUNG_ABLADEMENGE_AM","ABLADEMENGE_RECHNUNG","DATUM_ABLADEN",oFieldMAP,this,
									"ANTEIL_PLANMENGE_ABN","ANTEIL_LADEMENGE_ABN","ANTEIL_ABLADEMENGE_ABN",false ), 
									new MyE2_String("Prüfblock Ablade-Menge"));

			this.add_Component(FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI, new FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben(false), new MyE2_String("Auf beiden Seiten Mengen/Preise freigeben ..."));
			
			//2011-10-07: kombinierter zauberstab, freigabe und speichern
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI_UND_SAVE, new FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben(true), new MyE2_String("Auf beiden Seiten Mengen/Preise freigeben und wenn Freigabe erfolgreich, direkt speichern..."));
			
			
			
			//2013-01-14: preisfreigabe
			this.add_Component(new MyE2_DB_CheckBox( oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS)), new MyE2_String("Preis (EK) als geprüft markieren"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS_VON),false,30,10,true,new E2_FontPlain(-2)), new MyE2_String("Preis (EK)  wurde von <Kürzel> geprüft"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS_AM),false,60,10,true,new E2_FontPlain(-2)), new MyE2_String("Preis (EK)  wurde geprüft am"));

			this.add_Component(new MyE2_DB_CheckBox( oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS)), new MyE2_String("Preis (VK) als geprüft markieren"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS_VON),false,30,10,true,new E2_FontPlain(-2)), new MyE2_String("Preis (VK)  wurde von <Kürzel> geprüft"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS_AM),false,60,10,true,new E2_FontPlain(-2)), new MyE2_String("Preis (VK)  wurde geprüft am"));
			
			//2013-01-14:  und fuhrenabschlussfeld (nur gemeinsam) 
			this.add_Component(new MyE2_DB_CheckBox( oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE)), new MyE2_String("Fuhre abschliessen (wird bei abrechenbaren Fuhren automatisch mit der kompletten Überführung in die freien Positionen ausgeführt)"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_VON),false,30,10,true,new E2_FontPlain(-2)), new MyE2_String("Fuhre abgeschlossen von"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_AM),false,60,10,true,new E2_FontPlain(-2)),  new MyE2_String("Fuhre abgeschlossen am"));
			
			//2014-02-20: Gelangensbestaetigung erhalten-Schalter
			this.add_Component(new FU__MASK_CB_GelangensbestaetigungAnAus( oFieldMAP.get_(_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN)), new MyE2_String("Gelangensbestätigung erhalten"));			
			
			//jetzt die fahrplan-felder
			// ------------------------
//			String cQueryMaschinenANH = "SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
//													bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
//													" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
//													" JT_MASCHINENTYP.IST_ANHAENGER='Y'"
//													+ "ORDER BY KFZKENNZEICHEN";		

//			String cQueryMaschinenLKW = "SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
//													bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
//													" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
//													" JT_MASCHINENTYP.IST_LKW='Y' "
//													+ "ORDER BY KFZKENNZEICHEN";		
			
			String cQueryFahrer = 		"SELECT  NVL(NAME1,'-')||', '|| NVL(VORNAME,'-') FROM "+bibE2.cTO()+
											".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y'" +
													" AND  NVL(AKTIV,'N')='Y' ORDER BY NAME1, VORNAME";

//			this.add_Component(new MyE2_DB_SelectField(					oFieldMAP.get_("ID_MASCHINEN_LKW_FP"),	cQueryMaschinenLKW,false,false),new MyE2_String("LKW"));
			this.add_Component(new MyE2_DB_SelectField_LKW(				oFieldMAP.get_("ID_MASCHINEN_LKW_FP")),new MyE2_String("LKW"));
			
//			this.add_Component(new MyE2_DB_SelectField(					oFieldMAP.get_("ID_MASCHINEN_ANH_FP"), 	cQueryMaschinenANH,false,false),new MyE2_String("Anhänger"));
			this.add_Component(new MyE2_DB_SelectField_ANH(				oFieldMAP.get_("ID_MASCHINEN_ANH_FP")),new MyE2_String("Anhänger"));
			

			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("ANZAHL_CONTAINER_FP"),true,50,3,false), new MyE2_String("Anzahl Container Wiegeinheit"));
			this.add_Component(new FP_DB_SelectFieldContainerTypen(		oFieldMAP.get_("ID_CONTAINERTYP_FP")),	new MyE2_String("Container"));

			this.add_Component(new MyE2_DB_ComboBoxErsatz(				oFieldMAP.get_("FAHRER_FP"), 		 	cQueryFahrer, false),					new MyE2_String("Fahrer"));
			this.add_Component(new MyE2_DB_Label(						oFieldMAP.get_("EAN_CODE_FP")),			new MyE2_String("EAN-Code"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("SORTIERUNG_FP"),true,100,5,true), 		new MyE2_String("Sortierung"));
			this.add_Component(new MyE2_DB_TextField_WithSelektorEASY(	oFieldMAP.get_("TAETIGKEIT_FP"),"FAHRPLAN_TAETIGKEIT"),	new MyE2_String("Tätigkeit"));
			
			((MyE2_DB_TextField_WithSelektorEASY)this.get__Comp("TAETIGKEIT_FP")).get_oTextField().set_iWidthPixel(270);
			((MyE2_DB_TextField_WithSelektorEASY)this.get__Comp("TAETIGKEIT_FP")).get_oTextField().set_iMaxInputSize(100);

			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DAT_VORGEMERKT_FP")),new MyE2_String("Vorm.von"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DAT_VORGEMERKT_ENDE_FP")),new MyE2_String("Vorm.bis"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("FAHRPLANGRUPPE_FP"),true,100,10,true),new MyE2_String("Gruppe"));
			this.add_Component(new MyE2_DB_CheckBox(					oFieldMAP.get_("IST_GEPLANT_FP"),true),new MyE2_String("Geplant"));
			this.add_Component(new MyE2_DB_CheckBox(					oFieldMAP.get_("KENNER_ALTE_SAETZE_FP"),true),new MyE2_String("Alte Datensätze"));
			this.add_Component(new MyE2_DB_CheckBox(					oFieldMAP.get_("FUHRE_AUS_FAHRPLAN"),true),new MyE2_String("Fahrplanfuhre"));


			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("DAT_FAHRPLAN_FP"),true,100,10,true),new MyE2_String("Fahrp.dat."));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("FAHRT_ANFANG_FP"),true,100,5,false),new MyE2_String("Anfang"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("FAHRT_ENDE_FP"),true,100,5,false),new MyE2_String("Ende"));

			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("ANRUFER_FP"),true,200,50,false),new MyE2_String("Anrufer"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("ANRUFDATUM_FP"),true,100,10,false),new MyE2_String("Anrufdatum"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("ERFASSER_FP"),true,100,10,true), new MyE2_String("Erfasser"));

			this.add_Component(new MyE2_DB_TextArea(					oFieldMAP.get_("BEMERKUNG_START_FP"),300,4),new MyE2_String("Fahrplaninfo START"));
			this.add_Component(new MyE2_DB_TextArea(					oFieldMAP.get_("BEMERKUNG_ZIEL_FP"),300,4),new MyE2_String("Fahrplaninfo START"));

			this.add_Component(new MyE2_DB_CheckBox(					oFieldMAP.get_("FUHRE_KOMPLETT"),true),new MyE2_String("Fuhre komplett"));
			this.add_Component(new MyE2_DB_CheckBox(					oFieldMAP.get_("ALT_WIRD_NICHT_GEBUCHT"),true),new MyE2_String("Alte Fuhre"));
			this.add_Component(new MyE2_DB_CheckBox(					oFieldMAP.get_("EK_VK_ZUORD_ZWANG"),false),new MyE2_String("Zwang zur Kontraktzuordnung"));
			// ------------------------
			// ENDE fahrplan-felder

			
			// ---------------------- BUCHUNGSFELDER
			// ----------------------
			this.add_Component(new FU__MASK_CB_ManuellPreis(	    	oFieldMAP.get_("MANUELL_PREIS_EK"),"EINZELPREIS_EK","ID_VPOS_STD_EK","DEF_MANUELL_PREIS"),new MyE2_String("Lade-Seite bekommt einen manuellen Preis"));
			this.add_Component(new FU__MASK_CB_ManuellPreis(	    	oFieldMAP.get_("MANUELL_PREIS_VK"),"EINZELPREIS_VK","ID_VPOS_STD_VK","DEF_MANUELL_PREIS"),new MyE2_String("Ablade-Seite bekommt einen manuellen Preis"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("EINZELPREIS_EK"),true,100,10,false), new MyE2_String("Einzelpreis Lieferant"));
			this.add_Component(new MyE2_DB_TextField(					oFieldMAP.get_("EINZELPREIS_VK"),true,100,10,false), new MyE2_String("Einzelpreis Abnehmer"));
			this.add_Component(new BS_ComboBox_MWST(					oFieldMAP.get_("STEUERSATZ_EK")),	new MyE2_String("MWST. auf der Gutschrift"));
			this.add_Component(new BS_ComboBox_MWST(					oFieldMAP.get_("STEUERSATZ_VK")),	new MyE2_String("MWST. auf der Rechnung"));
			
			this.add_Component(new FU_MASK_DB_SEARCH_PREIS(				oFieldMAP.get_("ID_VPOS_STD_EK"),"EK"),	new MyE2_String("Angebotsposition Lieferant (Abnahmeangebot)"));
			this.add_Component(new FU_MASK_DB_SEARCH_PREIS(				oFieldMAP.get_("ID_VPOS_STD_VK"),"VK"),	new MyE2_String("Angebotsposition Abnehmer (Verkaufsangebot)"));
			
			this.add_Component(new MyE2_DB_TextArea(					oFieldMAP.get_("EU_STEUER_VERMERK_EK"),263,3,true,new E2_FontPlain(-2)), new MyE2_String("Steuervermerk Gutschrift"));
			this.add_Component(new MyE2_DB_TextArea(					oFieldMAP.get_("EU_STEUER_VERMERK_VK"),263,3,true,new E2_FontPlain(-2)), new MyE2_String("Steuervermerk Rechnung"));

			this.add_Component(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK, 
								new FU__MASK_SELECT_FIELD_STEUERVERMERK((MyE2_DB_TextArea)this.get__Comp("EU_STEUER_VERMERK_EK"),
																		"EK",
																		this.get__DBComp("STEUERSATZ_EK")),
																		new MyE2_String("Selektion der Moeglichkeiten des Steuertextes"));
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK, 
								new FU__MASK_SELECT_FIELD_STEUERVERMERK((MyE2_DB_TextArea)this.get__Comp("EU_STEUER_VERMERK_VK"),
																		"VK",
																		this.get__DBComp("STEUERSATZ_VK")),
																		new MyE2_String("Selektion der Moeglichkeiten des Steuertextes"));
			
			
			//zauberstab zum holen der preise und steuern
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER, new FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER(),new MyE2_String("Preis und Steuer holen"));
			
			//zauberstaebe zum verteilen der mengen
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_PLANMENGE_LIEF, new FU_MASK_BT_ZAUBERSTAB_MENGEN_PLANMENGE_LIEF(),new MyE2_String("Planmenge auf Abladeseite übertragen"));
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_LADEMENGE_LIEF, new FU_MASK_BT_ZAUBERSTAB_MENGEN_LADEMENGE_LIEF(),new MyE2_String("Lademenge auf Ablademenge und Planmenge Abladeseite übertragen"));
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_ABLADEMENGE_ABN, new FU_MASK_BT_ZAUBERSTAB_MENGEN_ABLADEMENGE_ABN(),new MyE2_String("Ablademenge von Ladeseite holen"));
			
			//button zum oeffnen eines TPA aus einer fuhrenmaske
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_MASK_OPEN_TPA, bAddOpenTPA_Button?new FU_MASK_BT_OpenTPA():new MyE2_Label(""),new MyE2_String("Öffne TPA aus Fuhre"));
			
			//zusatzkomponenten zum Anzeigen der Sortenbez. auf seite 1 der fuhre
			this.add_Component(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK, new FU__ARTBEZ_ANZEIGE(475),new MyE2_String("Infos zur Sorte EK-Seite"));
			this.add_Component(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK, new FU__ARTBEZ_ANZEIGE(475),new MyE2_String("Infos zur Sorte VK-Seite"));
			
			((MyE2_DB_ComboBoxErsatz)this.get__Comp("FAHRER_FP")).get_oTextField().set_iWidthPixel(270);
			

			//2011-08-16: der schalter EK-VK-sorte-gleich speichert die maske sofort ab (auf dialog-frage)
			this.add_Component(new FU__MASK_CB_EK_VK_SORTE_GLEICH(oFieldMAP.get_("EK_VK_SORTE_LOCK")),new MyE2_String("EK-VK-Sorte verbinden"));
			

			
			//2018-03-05: routing-felder
			this.add_Component(FU___CONST.HASH_KEY_BT_ROUTING, new FU_MASK_btRoutingEntfernung(),new MyE2_String("Entfernung berechnen"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(VPOS_TPA_FUHRE.routing_distance_km),true,98,10,false)._al_center(), new MyE2_String("Entfernung km"));
			this.add_Component(new MyE2_DB_TextField4HH_MM(oFieldMAP.get_(VPOS_TPA_FUHRE.routing_time_minutes),true,98,10,false)._al_center(), new MyE2_String("Fahrtdauer(Prognose)"));
//			this.get__Comp(VPOS_TPA_FUHRE.routing_distance_km).EXT().set_bDisabledFromBasic(true);
//			this.get__Comp(VPOS_TPA_FUHRE.routing_time_minutes).EXT().set_bDisabledFromBasic(true);
			//2018-03-05 -------
			
			//2018-03-08: show route
			this.add_Component(FU___CONST.HASH_KEY_BT_ROUTING_SHOW, new GEO_FAHR_Routing_bt_show(VPOS_TPA_FUHRE.id_adresse_lager_start, VPOS_TPA_FUHRE.id_adresse_lager_ziel), new MyE2_String("Route schauen"));

			//2011-11-22: neues feld externe postennummer hinter die bestellnummer haengen
			((MyE2_DB_TextField)this.get__Comp("BESTELLNUMMER_EK")).set_AutoFontDownSize(10, new E2_FontPlain(),  new E2_FontPlain(-2));
			((MyE2_DB_TextField)this.get__Comp("BESTELLNUMMER_VK")).set_AutoFontDownSize(10, new E2_FontPlain(),  new E2_FontPlain(-2));
			
			((MyE2_DB_TextField)this.get__Comp("POSTENNUMMER_EK")).set_AutoFontDownSize(10, new E2_FontPlain(),  new E2_FontPlain(-2));
			((MyE2_DB_TextField)this.get__Comp("POSTENNUMMER_VK")).set_AutoFontDownSize(10, new E2_FontPlain(),  new E2_FontPlain(-2));
			
			
			
			
			this.add_Component(new FU__MASK_CB_OHNE_ABRECHNUNG(oFieldMAP.get_("OHNE_ABRECHNUNG")), new MyE2_String("ohne Mengenabrechnung"));
			this.add_Component(new FU__MASK_CB_KEIN_KONTRAKT_NOETIG(oFieldMAP.get_("KEIN_KONTRAKT_NOETIG")), new MyE2_String("kein VK-Kontrakt nötig"));
			this.add_Component(new FU__MASK_CB_OHNE_AVV_CHECK(oFieldMAP.get_("OHNE_AVV_VERTRAG_CHECK")), new MyE2_String("kein AVV-Vertrags-Check"));
			
			//ENZALARM2
			this.add_Component(new MyE2_DB_TextFieldLABEL(oFieldMAP.get_("ERZEUGT_VON"),true,50),new MyE2_String("Erzeugt von"));

			
			//eine select-field komponente fuer die sonderfaelle, wenn eine fuhre ohne VK-kontrakt ausgeliefert werden soll
			MyE2_DB_SelectField  oSelAusnahmen = new FU_MASK_DB_SELECT_SonderDef(oFieldMAP.get_("ID_VPOS_TPA_FUHRE_SONDER"));
			this.add_Component(oSelAusnahmen, new MyE2_String("Sonderdefinition"));
			
			
			this.add_Component(new FU__MASK_CB_NormaleAbrechnungsMenge(oFieldMAP.get_("LADEMENGE_GUTSCHRIFT"),"ANTEIL_LADEMENGE_LIEF","ANTEIL_ABLADEMENGE_LIEF",true), new MyE2_String("Lademenge für Gutschrift"));
			this.add_Component(new FU__MASK_CB_NormaleAbrechnungsMenge(oFieldMAP.get_("ABLADEMENGE_RECHNUNG"),"ANTEIL_ABLADEMENGE_ABN","ANTEIL_LADEMENGE_ABN",false), new MyE2_String("Ablademenge für Rechnung"));
			this.add_Component(new MyE2_DB_Label(oFieldMAP.get_("STATUS_BUCHUNG")), new MyE2_String("Status Buchung"));

			

			// tochterkomponenten fuer kosten
			//this.add_Component(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN,new FU_MASK_DAUGHTER_KOSTEN(oFieldMAP,this),new MyE2_String("Kosten"));
			//2011-05-12: neue fulldaughterkomponente fuer die kosten
			this.add_Component(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN,new FUK__MASK_KostenFullDaughterInlay(oFieldMAP.get_oSQLFieldPKMainTable(),MODULKENNER_MASK),new MyE2_String("Kosten"));
			

			//ZEITSTEMPEL
			// tochterkomponenten fuer druckprotokoll      
			this.add_Component("TOCHTER_DRUCKPROTOKOLL",new FU_MASK_DAUGHTER_PrintProtokoll(oFieldMAP,this),new MyE2_String("Drucke"));
			

			//tochter fuer die moeglichen sonderberechnungen/vorgangs-positions-vorlagen
			this.add_Component(FU___CONST.HASH_KEY_DAUGHTER_POS_VORLAGEN,new FU_MASK_DAUGHTER_POS_VL(oFieldMAP,this),new MyE2_String("Vorlagen"));
			
					
			// falls speditionsfeld vorhanden ist, wird es in der maske angezeigt
			if (bMitSpeditionsFeld)
			{
				DB_SEARCH_Adress oSearchSpedition = new DB_SEARCH_Adress(oFieldMAP.get_("ID_ADRESSE_SPEDITION"));
				oSearchSpedition.get_oTextForAnzeige().setWidth(new Extent(200));
				oSearchSpedition.get_oTextFieldForSearchInput().setWidth(new Extent(70));
				this.add_Component(oSearchSpedition,new MyE2_String("Spedition"));
				
			}
			
			//fremdauftragsfuhre, keine abrechnung
			DB_SEARCH_Adress oSearchFremdauftrag = new DB_SEARCH_Adress(oFieldMAP.get_("ID_ADRESSE_FREMDAUFTRAG"));
			oSearchFremdauftrag.get_oTextForAnzeige().setWidth(new Extent(200));
			oSearchFremdauftrag.get_oTextFieldForSearchInput().setWidth(new Extent(70));
			this.add_Component(oSearchFremdauftrag,new MyE2_String("Fremdauftrag"));
			
			// checkbox, um eine falsch AVV-stellung doch speichern zu koennen (bekommt validierer)
			MyE2_DB_CheckBox  oCB_SPEICHERN_TROTZ_INKONSISTENZ = new MyE2_DB_CheckBox(oFieldMAP.get_("SPEICHERN_TROTZ_INKONSISTENZ"));
			oCB_SPEICHERN_TROTZ_INKONSISTENZ.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("SAVE_FALSE_AVV_DEFINITION"));
			oCB_SPEICHERN_TROTZ_INKONSISTENZ.setToolTipText(new MyE2_String("Speichere AVV-Einstellung trotz Inkonsistenz !").CTrans());
			
			// damit der validator gezogen wird, muss ein actionAgent vorhanden sein
			oCB_SPEICHERN_TROTZ_INKONSISTENZ.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) 
				{
				}
			});

			this.add_Component(oCB_SPEICHERN_TROTZ_INKONSISTENZ, new MyE2_String("Trotz falscher AVV-Einstellung speichern !"));
			
			//gefahrgutanzeige
			MyE2_Label labGefahrgut = new MyE2_Label(new MyE2_String("GEFAHRGUT"),MyE2_Label.STYLE_ALARM_LABEL());
			this.add_Component(FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE, labGefahrgut, new MyE2_String("Gefahrgut"));
			
			
			//neue toechter fuer die eingabe zusaetzlicher quell- und zielorte
			this.add_Component(FU___CONST.HASH_KEY_MASKE_TOCHTER_QUELLORTE,	new FU_DAUGHTER_ORTE(oFieldMAP.get_oSQLFieldPKMainTable(),"EK"), new MyE2_String("Zusätzliche Quellen"));
			this.add_Component(FU___CONST.HASH_KEY_MASKE_TOCHTER_ZIELORTE,	new FU_DAUGHTER_ORTE(oFieldMAP.get_oSQLFieldPKMainTable(),"VK"), new MyE2_String("Zusätzliche Ziele"));
			
			//Anzeigebereich fuer die Stornierung
			this.add_Component(new MyE2_DB_CheckBox(oFieldMAP.get_("IST_STORNIERT")), 										new MyE2_String("Storniert ?"));
			this.add_Component(new MyE2_DB_TextArea(oFieldMAP.get_("STORNO_GRUND"),200,1,true,new E2_FontPlain(-2)), 		new MyE2_String("Stornogrund"));
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_("STORNO_KUERZEL"),true,30,10,true,new E2_FontPlain(-2)),	new MyE2_String("Wer hat storniert"));
			
			//neue datumsfelder
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DATUM_ABHOLUNG"),         "",75,false,null,true),new MyE2_String("Abholung Start"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DATUM_ABHOLUNG_ENDE"),    "",75,false,null,true),new MyE2_String("Abholung Ende"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DATUM_ANLIEFERUNG"),      "",75,false,null,true),new MyE2_String("Anlieferung Start"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DATUM_ANLIEFERUNG_ENDE"), "",75,false,null,true),new MyE2_String("Anlieferung Ende"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DATUM_AUFLADEN"),         "",75,false,null,true),new MyE2_String("Ladedatum (laut Wiegeschein)"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(		oFieldMAP.get_("DATUM_ABLADEN"),          "",75,false,null,true),new MyE2_String("Abladedatum (laut Wiegeschein)"));
			
			
			FU_MASK_BT_ZAUBERSTAB_VerteileDatum  oButtonVerteileDatum = new FU_MASK_BT_ZAUBERSTAB_VerteileDatum();
			
			this.add_Component(FU___CONST.HASH_KEY_BUTTON_MASK_VERTEILE_DATUM,oButtonVerteileDatum,new MyE2_String("Datum auf alle relevanten Felder verteilen (für Bareinkäufe)"));
			
			
			//2016-04-21:martin:neue Felder fuer MRS
			E2_DropDownSettings dd_verpackung = new E2_DropDownSettings( VERPACKUNGSART.fullTabName(), VERPACKUNGSART.verpackungsart.fn(), VERPACKUNGSART.id_verpackungsart.fn(), null, true);
			MyE2_DB_SelectField selVerpackungsart = new MyE2_DB_SelectField(oFieldMAP.get_(VPOS_TPA_FUHRE.id_verpackungsart), dd_verpackung.getDD(),false, new Extent(350));
			this.add_Component(selVerpackungsart, new MyE2_String("Verpackungsart"));
			this.add_Component(new MyE2_DB_TextArea(oFieldMAP.get_(VPOS_TPA_FUHRE.bemerkung_fuer_kunde),810,3,false,new E2_FontPlain()), 	new MyE2_String("Bemerkung für Kundenbelege"));
			//-------------------------
			
			//2020-08-19:martin:neue Felder fuer MRS
			E2_DropDownSettings dd_verarbeitung = new E2_DropDownSettings( VERARBEITUNG.fullTabName(), VERARBEITUNG.verarbeitung.fn(), VERARBEITUNG.id_verarbeitung.fn(), null, true);
			MyE2_DB_SelectField selVerarbeitung = new MyE2_DB_SelectField(oFieldMAP.get_(VERARBEITUNG.id_verarbeitung), dd_verarbeitung.getDD(),false, new Extent(350));
			this.add_Component(selVerarbeitung, new MyE2_String("Verarbeitung"));
			//-------------------------
			
			
			//2015-12-09: mehrfachauswahl datumsfelder --- START
			LinkedHashMap<String,MyE2_DB_TextField_DatePOPUP_OWN> fields4popup = new LinkedHashMap<String,MyE2_DB_TextField_DatePOPUP_OWN>();
			fields4popup.put(VPOS_TPA_FUHRE.datum_abholung.fn(), 			(MyE2_DB_TextField_DatePOPUP_OWN)this.get(VPOS_TPA_FUHRE.datum_abholung.fn()));
			fields4popup.put(VPOS_TPA_FUHRE.datum_abholung_ende.fn(), 		(MyE2_DB_TextField_DatePOPUP_OWN)this.get(VPOS_TPA_FUHRE.datum_abholung_ende.fn()));
			fields4popup.put(VPOS_TPA_FUHRE.datum_aufladen.fn(), 			(MyE2_DB_TextField_DatePOPUP_OWN)this.get(VPOS_TPA_FUHRE.datum_aufladen.fn()));
			fields4popup.put(VPOS_TPA_FUHRE.datum_anlieferung.fn(), 		(MyE2_DB_TextField_DatePOPUP_OWN)this.get(VPOS_TPA_FUHRE.datum_anlieferung.fn()));
			fields4popup.put(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn(), 	(MyE2_DB_TextField_DatePOPUP_OWN)this.get(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn()));
			fields4popup.put(VPOS_TPA_FUHRE.datum_abladen.fn(), 			(MyE2_DB_TextField_DatePOPUP_OWN)this.get(VPOS_TPA_FUHRE.datum_abladen.fn()));
			LinkedHashMap<String,Component> labels4popup = 					new LinkedHashMap<String,Component>();
			labels4popup.put(VPOS_TPA_FUHRE.datum_abholung.fn(), 			new MyE2_Label("Abholung von"));
			labels4popup.put(VPOS_TPA_FUHRE.datum_abholung_ende.fn(), 		new MyE2_Label("Abholung bis"));
			labels4popup.put(VPOS_TPA_FUHRE.datum_aufladen.fn(), 			new MyE2_Label("Ladedatum"));
			labels4popup.put(VPOS_TPA_FUHRE.datum_anlieferung.fn(), 		new MyE2_Label("Anlieferung von"));
			labels4popup.put(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn(), 	new MyE2_Label("Anlieferung bis"));
			labels4popup.put(VPOS_TPA_FUHRE.datum_abladen.fn(), 			new MyE2_Label("Abladedatum"));
			MD_bt_multiPopupCalender  multiCalendar = new MD_bt_multiPopupCalender(fields4popup, labels4popup,MultiValueSelector_SaveKeySizeofPopup.FUHREN_SIXPACK_DATUMSWERTE) ;
			this.add_Component(FU___CONST.HASH_KEY_MASK_BUTTON_MULTI_POPUP_DATE,	multiCalendar,	new MyE2_String("Alle Datumsfelder zusammen füllen"));
			//2015-12-09: mehrfachauswahl datumsfelder --- ENDE
			
			
			
			
			
			__FU_FUO_ABZUGSLISTE oAbzuege_EK = new __FU_FUO_ABZUGSLISTE(oFieldMAP, 	"ID_VPOS_TPA_FUHRE",
																					"JT_VPOS_TPA_FUHRE_ABZUG_EK", 
																					this,
																					RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_LADEMENGE_LIEF,
																					RECORD_VPOS_TPA_FUHRE.FIELD__EINZELPREIS_EK,
																					RECORD_VPOS_TPA_FUHRE.FIELD__ABZUG_LADEMENGE_LIEF,
																					RECORD_VPOS_TPA_FUHRE.FIELD__EPREIS_RESULT_NETTO_MGE_EK);
			
			__FU_FUO_ABZUGSLISTE oAbzuege_VK = new __FU_FUO_ABZUGSLISTE(oFieldMAP, 	"ID_VPOS_TPA_FUHRE",
																					"JT_VPOS_TPA_FUHRE_ABZUG_VK", this,
																					RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_ABLADEMENGE_ABN,
																					RECORD_VPOS_TPA_FUHRE.FIELD__EINZELPREIS_VK,
																					RECORD_VPOS_TPA_FUHRE.FIELD__ABZUG_ABLADEMENGE_ABN,
																					RECORD_VPOS_TPA_FUHRE.FIELD__EPREIS_RESULT_NETTO_MGE_VK);
			
			
			oAbzuege_EK.set_oContainerExScrollHeight(new Extent(400));
			oAbzuege_VK.set_oContainerExScrollHeight(new Extent(400));
			
			
			this.add_Component(FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_EK,oAbzuege_EK,new MyE2_String("Abzüge EK"));
			this.add_Component(FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_VK,oAbzuege_VK,new MyE2_String("Abzüge VK"));

			
			//2017-01-16: aktive Label-komponenten
			this.add_Component(FU___CONST.ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE, new RB_lab()._tr("Lademenge (2) = Gutschriftsmenge")._fsa(-2)._i(), new MyE2_String(""));
			this.add_Component(FU___CONST.ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE, new RB_lab()._tr("Ablademenge (6) = Rechnungsmenge")._fsa(-2)._i(), new MyE2_String(""));
			
			
			this.get__Comp("IST_STORNIERT").EXT().set_bDisabledFromBasic(true);
			this.get__Comp("ID_MASCHINEN_LKW_FP").EXT().set_bDisabledFromBasic(true);
			this.get__Comp("FAHRPLANGRUPPE_FP").EXT().set_bDisabledFromBasic(true);
			
			
			
			MyE2_Row_EveryTimeEnabled labStorno= new MyE2_Row_EveryTimeEnabled();
			this.add_Component(FU___CONST.HASH_KEY_STORNO_ANZEIGE, labStorno, new MyE2_String("Storno"));
			

			
			
			//2011-12-30: neue komponenten fuer die uma-kontrakte
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_("ID_UMA_KONTRAKT"),true,100,10,true,new E2_FontPlain(-2)),new MyE2_String("UMA-Kontrakt-ID"));
			this.add_Component(FU___CONST.HASH_MASK_BUTTON_UMA_KONTRAKT, new FU_MASK_BT_DEFINIERE_UMA_VERTRAG(), new MyE2_String("UMA-Vertrag definieren"));
			
			//2012-01-09: uma-anzeige-rahmen links und rechts
			this.add_Component(FU___CONST.HASH_MASK_COMP_UMA_MASKEN_ANZEIGE, new FU_MASK_UMA_ANZEIGE(), new MyE2_String("UMA-Vertrags-Info"));
			
			
			//2013-05-27: fremdlager-anzeiger
			this.add_Component(FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_LADESEITE, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String(""));
			this.add_Component(FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_ABLADESEITE, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String(""));
			
			
			
			//2013-09-26: feld, das die handelsdefinition aufnimmt
			this.add_Component(new MyE2_DB_TextField(oFieldMAP.get_(_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF), true, 50, 10, true, new E2_FontPlain()), new MyE2_String("USt./Steuertext-Definition"));
			this.add_Component(FU___CONST.HASH_MASK_ERASE_ID_HANDELSDEF,new FU_MASK_BT_Loesche_ID_HANDELSDEF(),new MyE2_String("Löscht die USt./Steuertext-ID! \nDamit können Steuer und Steuertext manuell vergeben werden!"));
			this.add_Component(FU___CONST.HASH_MASK_EDIT_ID_HANDELSDEF,new FU_MASK_BtOpenSteuerregel(),new MyE2_String("Öffnen der Steuer-Regel / Handelsdefinition"));
			
			//2018-06-04: infoblock fuer die anzeige des Fremdwaehrungsstatus der Firmen
			this.add_Component(FU___CONST.FIELDNAME_INFOBOCK_WAEHRUNGEN, new FU_MASK_InfoBlockWaehrungen(), S.ms("Fremdwährungsindikator"));
			
			
			//2021-01-07: neue fuhrenfelder
			this.add_Component(new FU_MASK_SelectFahrplanZeitenDef(oFieldMAP.get_(VPOS_TPA_FUHRE.id_fahrplan_zeitangabe)), S.ms("Zeitangabe-Referenz"));
			this.add_Component(new MyE2_DB_TextArea(oFieldMAP.get_(VPOS_TPA_FUHRE.zeitangabe),265,2), new MyE2_String("Zeitangabe"));
			
			//die planmengen auf der rechten seite Grau machen, um zu signalisieren, dass diese mengen am schluss berechnet werden
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE_ABN")).setForeground(Color.DARKGRAY);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE_ABN")).setToolTipText(new MyE2_String("Achtung! Feld wird von Automatik überschrieben, wenn Ablademengen vorhanden sind !!").CTrans());
			
			((MyE2_DB_TextField)this.get__Comp("NOTIFIKATION_NR_EK")).setToolTipText(new MyE2_String("Falls für die EK-Seite eine eigene Notifikation-Nr nötig ist, kann diese hier eingetragen werden").CTrans());
			
			// der merker fuer alte datensaetze wird gesperrt
			//this.get__Comp("ALT_WIRD_NICHT_GEBUCHT").EXT().set_bDisabledFromBasic(true);
			((MyE2_DB_CheckBox)this.get__Comp("ALT_WIRD_NICHT_GEBUCHT")).add_GlobalAUTHValidator_AUTO("SCHALTER_ALT_UNGEBUCHT");
			((MyE2_DB_CheckBox)this.get__Comp("ALT_WIRD_NICHT_GEBUCHT")).add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					//dummy, damit die globale-validierung ausgefuehrt wird
				}
			});
			

			
			// der merker fuer alte datensaetze wird gesperrt
			((MyE2_DB_CheckBox)this.get__Comp("EK_VK_ZUORD_ZWANG")).setToolTipText(new MyE2_String("Falls EK- und VK-Kontrakte in der Relation vorliegen, dann zwingt dieser Schalter zu einer Zuordnung").CTrans());
			((MyE2_DB_CheckBox)this.get__Comp("EK_VK_ZUORD_ZWANG")).add_GlobalAUTHValidator_AUTO("SCHALTER_EK_VK_ZUORD_ZWANG");
			((MyE2_DB_CheckBox)this.get__Comp("EK_VK_ZUORD_ZWANG")).add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					//dummy, damit die globale-validierung ausgefuehrt wird
				}
			});

			
			
			// ek-kontraktnummer-zusatz wird nutr noch aus historischen gruenden eingeblendet
			((MyE2_DB_TextField)this.get__Comp("EK_KONTRAKTNR_ZUSATZ")).setFont(new E2_FontPlain(-2));
			((MyE2_DB_TextField)this.get__Comp("VK_KONTRAKTNR_ZUSATZ")).setFont(new E2_FontPlain(-2));
			((MyE2_DB_TextField)this.get__Comp("EK_KONTRAKTNR_ZUSATZ")).setForeground(Color.DARKGRAY);
			((MyE2_DB_TextField)this.get__Comp("VK_KONTRAKTNR_ZUSATZ")).setForeground(Color.DARKGRAY);
			((MyE2_DB_TextField)this.get__Comp("EK_KONTRAKTNR_ZUSATZ")).setToolTipText(new MyE2_String("Rohstoff-Kontrakt-Nummer -- falls vorhanden ").CTrans());
			((MyE2_DB_TextField)this.get__Comp("VK_KONTRAKTNR_ZUSATZ")).setToolTipText(new MyE2_String("Rohstoff-Kontrakt-Nummer -- falls vorhanden ").CTrans());
			
			
			
			((MyE2IF__Component)this.get_Comp("EINHEIT_MENGEN")).EXT().set_bDisabledFromBasic(true);
			((MyE2IF__Component)this.get_Comp("FUHRE_IST_UMGELEITET")).EXT().set_bDisabledFromBasic(true);


			// schalter-felder werden ausgeschaltet
			((MyE2IF__Component)this.get_Comp("OHNE_ABRECHNUNG")).EXT().set_bDisabledFromBasic(true);
			((MyE2IF__Component)this.get_Comp("KEIN_KONTRAKT_NOETIG")).EXT().set_bDisabledFromBasic(true);
			((MyE2IF__Component)this.get_Comp("OHNE_AVV_VERTRAG_CHECK")).EXT().set_bDisabledFromBasic(true);
			
			((MyE2IF__Component)this.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__EPREIS_RESULT_NETTO_MGE_EK)).EXT().set_bDisabledFromBasic(true);
			((MyE2IF__Component)this.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__EPREIS_RESULT_NETTO_MGE_VK)).EXT().set_bDisabledFromBasic(true);
			((MyE2IF__Component)this.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ABZUG_LADEMENGE_LIEF)).EXT().set_bDisabledFromBasic(true);
			((MyE2IF__Component)this.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ABZUG_ABLADEMENGE_ABN)).EXT().set_bDisabledFromBasic(true);
			
			((MyE2IF__Component)this.get_Comp("OHNE_AVV_VERTRAG_CHECK")).EXT().set_bDisabledFromBasic(true);
			

			
			
			//den fahrplanzeitraum "von" so definieren, dass der das "bis" automatisch fuellt
			((MyE2_DB_TextField_DatePOPUP_OWN) this.get__Comp("DAT_VORGEMERKT_FP")).get_vActionAgentsZusatz().add(
					new XX_ActionAgent()
					{

						@Override
						public void executeAgentCode(ExecINFO execInfo) throws myException
						{
							FU_MASK_ComponentMAP oThis = FU_MASK_ComponentMAP.this;
							if (S.isEmpty(oThis.get__DBComp("DAT_VORGEMERKT_ENDE_FP").get_cActualMaskValue().trim()))
							{
								oThis.get__DBComp("DAT_VORGEMERKT_ENDE_FP").set_cActualMaskValue(
										oThis.get__DBComp("DAT_VORGEMERKT_FP").get_cActualMaskValue());
							}
						}
						
					});
			

			//Ebenso Start- und ZielDatum
			((MyE2_DB_TextField_DatePOPUP_OWN) this.get__Comp("DATUM_ABHOLUNG")).get_vActionAgentsZusatz().add(
					new XX_ActionAgent()
					{

						@Override
						public void executeAgentCode(ExecINFO execInfo) throws myException
						{
							FU_MASK_ComponentMAP oThis = FU_MASK_ComponentMAP.this;
							if (S.isEmpty(oThis.get__DBComp("DATUM_ABHOLUNG_ENDE").get_cActualMaskValue().trim()))
							{
								oThis.get__DBComp("DATUM_ABHOLUNG_ENDE").set_cActualMaskValue(
										oThis.get__DBComp("DATUM_ABHOLUNG").get_cActualMaskValue());
							}
						}
						
					});
			

			
			((MyE2_DB_TextField_DatePOPUP_OWN) this.get__Comp("DATUM_ANLIEFERUNG")).get_vActionAgentsZusatz().add(
					new XX_ActionAgent()
					{

						@Override
						public void executeAgentCode(ExecINFO execInfo) throws myException
						{
							FU_MASK_ComponentMAP oThis = FU_MASK_ComponentMAP.this;
							if (S.isEmpty(oThis.get__DBComp("DATUM_ANLIEFERUNG_ENDE").get_cActualMaskValue().trim()))
							{
								oThis.get__DBComp("DATUM_ANLIEFERUNG_ENDE").set_cActualMaskValue(
										oThis.get__DBComp("DATUM_ANLIEFERUNG").get_cActualMaskValue());
							}
						}
						
					});
			

			
			//2014-05-27: zusatzkomponenten fuer die erfassung von abweichenden lieferbedingungen
			BS_ComboBox_LIEFERBEDINGUNGEN oLFBED_Alternativ_EK = new BS_ComboBox_LIEFERBEDINGUNGEN(oFieldMAP.get_(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK),300,3);
			BS_ComboBox_LIEFERBEDINGUNGEN oLFBED_Alternativ_VK = new BS_ComboBox_LIEFERBEDINGUNGEN(oFieldMAP.get_(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK),300,3);
			this.add_Component(oLFBED_Alternativ_EK, new MyE2_String("Lieferbedingungen EK (alternativ)"));
			this.add_Component(oLFBED_Alternativ_VK, new MyE2_String("Lieferbedingungen VK (alternativ)"));
			this.add_Component(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK, new FU_AL_Component(   oLFBED_Alternativ_EK,
																											 this,
																											 _DB.VPOS_TPA_FUHRE$ID_ADRESSE_START, 
																											 _DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK, 
																											 _DB.VPOS_TPA_FUHRE$ID_VPOS_KON_EK, 
																											 _DB.VPOS_TPA_FUHRE$ID_VPOS_STD_EK, 
																											 _DB.ADRESSE$ID_LIEFERBEDINGUNGEN), new MyE2_String("Lieferbedingungen EK (alternativ)"));
			this.add_Component(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK, new FU_AL_Component(oLFBED_Alternativ_VK,
																											 this,
																											 _DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, 
																											 _DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK, 
																											 _DB.VPOS_TPA_FUHRE$ID_VPOS_KON_VK, 
																											 _DB.VPOS_TPA_FUHRE$ID_VPOS_STD_VK, 
																											 _DB.ADRESSE$ID_LIEFERBEDINGUNGEN_VK), new MyE2_String("Lieferbedingungen VK (alternativ)"));
			
			
			
//			//aenderung 2011-02-01: M.L. Mengen und Preise groesser anzeigen
			//spezielle StyleFactorys fuer die Anteil-Mengen-Felder einfuehren, die das jeweilige gueltige feld markiert
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE_LIEF")).EXT().set_STYLE_FACTORY(new StyleFactory_TextField_20Hoch_Font2());
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE_LIEF")).EXT().set_STYLE_FACTORY(new StyleFactory_TextField_20Hoch_Font2());
			((MyE2_DB_TextField)this.get__Comp("EINZELPREIS_EK")).EXT().set_STYLE_FACTORY(new StyleFactory_TextField_20Hoch_Font2());
			((MyE2_DB_TextField)this.get__Comp("EINZELPREIS_VK")).EXT().set_STYLE_FACTORY(new StyleFactory_TextField_20Hoch_Font2());
			
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE_LIEF")).EXT().set_STYLE_FACTORY(	new FU___MENGENTEXT_STYLE_FACTORY("LADEMENGE_GUTSCHRIFT",this,true));
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_ABLADEMENGE_LIEF")).EXT().set_STYLE_FACTORY(	new FU___MENGENTEXT_STYLE_FACTORY("LADEMENGE_GUTSCHRIFT",this,false));
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_ABLADEMENGE_ABN")).EXT().set_STYLE_FACTORY(	new FU___MENGENTEXT_STYLE_FACTORY("ABLADEMENGE_RECHNUNG",this,true));
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE_ABN")).EXT().set_STYLE_FACTORY(	new FU___MENGENTEXT_STYLE_FACTORY("ABLADEMENGE_RECHNUNG",this,false));
		
			E2_FontPlain  oFontGross = new E2_FontPlain(2);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE_LIEF")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE_LIEF")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_ABLADEMENGE_LIEF")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_PLANMENGE_ABN")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_LADEMENGE_ABN")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("ANTEIL_ABLADEMENGE_ABN")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("EINZELPREIS_EK")).setFont(oFontGross);
			((MyE2_DB_TextField)this.get__Comp("EINZELPREIS_VK")).setFont(oFontGross);
			
			
			//2012-03-26: suchtags einfuegen
			this.get__Comp("ANTEIL_PLANMENGE_LIEF").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_ANTEIL_PLANMENGE_LIEF);
			this.get__Comp("ANTEIL_LADEMENGE_LIEF").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_ANTEIL_LADEMENGE_LIEF);
			this.get__Comp("ANTEIL_ABLADEMENGE_LIEF").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_ANTEIL_ABLADEMENGE_LIEF);
			this.get__Comp("ANTEIL_PLANMENGE_ABN").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_ANTEIL_PLANMENGE_ABN);
			this.get__Comp("ANTEIL_LADEMENGE_ABN").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_ANTEIL_LADEMENGE_ABN);
			this.get__Comp("ANTEIL_ABLADEMENGE_ABN").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_ANTEIL_ABLADEMENGE_ABN);
			
			
			
			

			//2018-07-04: tax-selektoren umschaltbar
			if (ENUM_MANDANT_DECISION.USE_GROUPED_TAX_SELECTOR.is_YES_FromSession()) {
				FU_MASK_SelectTaxViaPopup popEK = new FU_MASK_SelectTaxViaPopup(oFieldMAP.get_("ID_TAX_EK"));
				FU_MASK_SelectTaxViaPopup popVK = new FU_MASK_SelectTaxViaPopup(oFieldMAP.get_("ID_TAX_VK"));
				
				this.add_Component(popEK,			new MyE2_String("Steuer-Definition EK-Seite festlegen.."));
				this.add_Component(popVK,			new MyE2_String("Steuer-Definition VK-Seite festlegen.."));
				
				//noetig, um fuer interactive_setting_validating - routinen die id der maskenkomponente an die eigentlichen buttons zu uebertragen
				popEK._setOwnIdToAllButtonEndpointIds();
				popVK._setOwnIdToAllButtonEndpointIds();
				
			} else {
			
				TAX__DD_STEUERDEF steuerEK = new TAX__DD_STEUERDEF(oFieldMAP.get_("ID_TAX_EK"),70,false);
				TAX__DD_STEUERDEF steuerVK = new TAX__DD_STEUERDEF(oFieldMAP.get_("ID_TAX_VK"),70,false);

				steuerEK.setFont(new E2_FontPlain(-2));
				steuerVK.setFont(new E2_FontPlain(-2));

				this.add_Component(steuerEK,			new MyE2_String("Steuer-Definition EK-Seite festlegen.."));
				this.add_Component(steuerVK,			new MyE2_String("Steuer-Definition VK-Seite festlegen.."));
			}
			
			
			this.add_Component(new FU_MASK_DB_SELECT_INTRASTAT_MELDUNG(oFieldMAP.get_("INTRASTAT_MELD_IN"),50), 	new MyE2_String("Intrastat Einfuhr melden oder nicht melden (bei undefiniert wird das automatische Verfahren angewendet)"));
			this.add_Component(new FU_MASK_DB_SELECT_INTRASTAT_MELDUNG(oFieldMAP.get_("INTRASTAT_MELD_OUT"),50), 	new MyE2_String("Intrastat Ausfuhr melden oder nicht melden (bei undefiniert wird das automatische Verfahren angewendet)"));
			this.add_Component(new TAX__DD_VERANTWORTUNG(oFieldMAP.get_("TP_VERANTWORTUNG"),80,false,false),	new MyE2_String("Transport-Verantwortung (\"Wer ist Herr des Verfahrens\") ?"));

			//2013-03-11: felder fuer die definition der bundesbank-meldungen
			this.add_Component(new MyE2_DB_CheckBox(oFieldMAP.get_(_DB.VPOS_TPA_FUHRE$TRANSIT_EK), new MyE2_String("Definiert den Status einer Transit-Meldung (>12.500,- EUR an Bundesbank)")), new MyE2_String("Bundesbankmeldung Einkauf"));
			this.add_Component(new MyE2_DB_CheckBox(oFieldMAP.get_(_DB.VPOS_TPA_FUHRE$TRANSIT_VK), new MyE2_String("Definiert den Status einer Transit-Meldung (>12.500,- EUR an Bundesbank)")), new MyE2_String("Bundesbankmeldung Verkauf"));
			
			
			
			//2013-01-03: die Validierer der neuen steuer-def-felder setzen
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__ID_TAX_EK, this.oSteuerValidiererEK);
			this.get_hmInteractiv_settings_validation().put_(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK, this.oSteuerValidiererEK);
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__ID_TAX_VK, this.oSteuerValidiererVK);
			this.get_hmInteractiv_settings_validation().put_(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK, this.oSteuerValidiererVK);
			//2013-01-18: steuervalidierer einstellen
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS, this.oPreisFreigabeValidiererEK);
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS, this.oPreisFreigabeValidiererVK);
			//2013-01-20: das handling der mengenfreigabe auch auf die SET_AND_VALID-klassen legen
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE, new FU_Set_And_Valid_Mengenfreigabe("EK"));
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE, new FU_Set_And_Valid_Mengenfreigabe("VK"));
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__LADEMENGE_GUTSCHRIFT, new FU_Set_And_Valid_BuchMge_ist_LadeMge("EK"));
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG, new FU_Set_And_Valid_BuchMge_ist_LadeMge("VK"));
			this.get_hmInteractiv_settings_validation().put_(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE, new FU_Set_And_Valid_FuhrenAbschluss());
			
			
			//2013-01-03: die action-agents der maske
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ID_TAX_EK)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR());
			((E2_IF_Handles_ActionAgents)this.get__Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ID_TAX_VK)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR());
			((E2_IF_Handles_ActionAgents)this.get__Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			//2013-01-18: steuervalidierer einstellen
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			//2013-01-20: das handling der mengenfreigabe auch auf die SET_AND_VALID-klassen legen
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__LADEMENGE_GUTSCHRIFT)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			((E2_IF_Handles_ActionAgents)this.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE)).add_oActionAgent(new ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR(),true);
			
			
			//2013-01-14: tooltips eintragen
			((MyE2_DB_CheckBox)this.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__LADEMENGE_GUTSCHRIFT)).setToolTipText(new MyE2_String("Falls gesetzt, dann wird die Lademenge für die Gutschrift genommen, sonst die Ablademenge (links)").CTrans());
			((MyE2_DB_CheckBox)this.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG)).setToolTipText(new MyE2_String("Falls gesetzt, dann wird die Ablademenge für die Rechnung genommen, sonst die Ablademenge (links)").CTrans());
			
			
			//2013-05-16: validierung der fuhre auf Richtigkeit der Lagertype und des typs eigenware/fremdware
			this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE$SPEICHERN_TROTZ_INKONSISTENZ, new FU_Valid_Fremdware_und_Fremdwaren_Lager());

			//2013-05-16: validierung der fuhre auf Richtigkeit der avv-positivliste gegen die abladeadresse
			this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE$SPEICHERN_TROTZ_INKONSISTENZ, 
								new FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, _DB.VPOS_TPA_FUHRE$ID_EAK_CODE));
			
			//2013-10-02: validierer der fuhre auf uebereinstimmung der handelsdef mit der maske
			this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE$SPEICHERN_TROTZ_INKONSISTENZ, 
								new FU_Set_And_Valid_VGL_verify_id_handelsdef());
			
			
			//2014-05-27: neue validierer fuer die lieferbedingungsfelder (prueft beide felder)
			this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK, new FU_Set_And_Valid_Alt_Lieferbedingung());
			
			//2018-06-05: neuer map-setter fuer die anzueige der Fremdwaehrungen
			this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START, new FU_Set_And_Valid_AnzeigeFremdWaehrung());
			
			//2011-04-21: neue validierung fuer die sortengleichheit 
			this.add_oMAPValidator(	new FU_MASK_MAP_Validator_PruefeEK_VK_Sortenbedingung());
			//2013-01-15: Fuhren-Masken-Validierer, wenn id_adresse_fremdauftrag, dann status zwingend Fremdware
			this.add_oMAPValidator(	new ownMapValidator_ID_ADRESSE_FREMDAUFTRAG_bedingt_status_fremdware());
			this.add_oMAPValidator(	new FU_MASK_MAP_VALIDATOR_SetzeDatumEnde_And_calc_abzugsliste());
			this.add_oMAPValidator(	new ownMapValidatorBeforeSave_XX_MAP_Set_And_Valid_VECTOR());

			this.set_oMAPSettingAgent(	new FU_MASK_MAP_SETTER());
			
			/**
			 * 2017-01-23: neue methoden zum aufbau der verbindung zu den fuhrenbasierten fixis
			 */
			this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new own_addon_statement_builders());
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException(this,"Error:"+ex.getLocalizedMessage());
		}
		
	}



	//2013-01-03: den validierer in den klicks einsetzen
	private class ownActionAgentValid_XX_MAP_Set_And_Valid_VECTOR extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(FU_MASK_ComponentMAP.this, oExecInfo);
		}
	}
	
	
	
	
	
	private class ownMapValidatorBeforeSave_XX_MAP_Set_And_Valid_VECTOR extends XX_MAP_ValidBeforeSAVE
	{
		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			return FU_MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(FU_MASK_ComponentMAP.this, oInputMap);
		}
	}
	



	@Override
	public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_BasicModuleContainer_PopUp_BeforeExecute() 	throws myException
	{
		V_Single_BasicModuleContainer_PopUp_BeforeExecute vRueck = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
		vRueck.add(new FU__POPUP_BeforeSaveFuhre_Check_Kontrakt());
		return vRueck;
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
	private class ownMapValidator_ID_ADRESSE_FREMDAUFTRAG_bedingt_status_fremdware extends XX_MAP_ValidBeforeSAVE
	{
		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			String  cFremdWare = 		(oMap.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_ABRECHNUNG)).get_cActualDBValueFormated();
			String 	cID_FremdAuftrag = 	(oMap.get__DBComp( RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_FREMDAUFTRAG)).get_cActualDBValueFormated();
			
			//wenn es eine Fremdauftragsfuhre ist, dann muss auch der schalter ohne abrechnung gesetzt werden
			if (S.isFull(cID_FremdAuftrag) && cFremdWare.equals("N") )
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! Wenn es eine Fremdbeauftragung gibt, dann muss der Schalter <Fremdwaren-Fuhre> gesetzt sein !!")));
			}
			
			return oMV;
		}
	}
	
	
	
	public boolean get_bHas_OrteAufMaske() throws myException
	{
		boolean bRueck = false;
		
		FU_DAUGHTER_ORTE  oOrteEK = (FU_DAUGHTER_ORTE)this.get__Comp(FU___CONST.HASH_KEY_MASKE_TOCHTER_QUELLORTE);
		FU_DAUGHTER_ORTE  oOrteVK = (FU_DAUGHTER_ORTE)this.get__Comp(FU___CONST.HASH_KEY_MASKE_TOCHTER_ZIELORTE);
		
		
		if (oOrteEK.get_oFUO_LIST_BasicModuleContainer().get_oNaviList().get_vectorSegmentation().size()>0 ||
			oOrteVK.get_oFUO_LIST_BasicModuleContainer().get_oNaviList().get_vectorSegmentation().size()>0)
		{
			bRueck = true;
		}
		
		return bRueck;
	}



	/**
	 * fuegt zwei buttons an: upload-files und sammeldownload
	 */
	@Override
	public Vector<Component> generate_MaskComponents(E2_ComponentMAP map)	throws myException {
		Vector<Component>  v_comp = new Vector<Component>();
		
		v_comp.add(new UP_DOWN_GenericButton4Masks(this, this.modulkenner_mask, new MyE2_String("Zusatzdateien zur Fuhre hoch/runterladen"), null));
		v_comp.add(new UP_DOWN_GenericDownloadCollector(this, _TAB.vpos_tpa_fuhre.n(), new E2_ButtonAUTHValidator(
														E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FUHRENFUELLER.get_callKey(),
														"ZUSATZDATEIEN_DOWNLOADEN")));
		
		return v_comp;
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
			
			MyLong   id_vpos_kon_ek = new MyLong(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE.id_vpos_kon_ek.fn()));
			MyLong   id_vpos_kon_vk = new MyLong(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE.id_vpos_kon_vk.fn()));
			
			if (id_vpos_kon_ek!=null && id_vpos_kon_ek.isOK()) {
				try {
					Rec20 rec_vpos_kon_ek = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon_ek.get_lValue());
					if (rec_vpos_kon_ek.is_yes_db_val(VPOS_KON.typ_ladung)) {
						MyBigDecimal bd_menge = null;
						if (map.get_bActualDBValue(VPOS_TPA_FUHRE.lademenge_gutschrift.fn())) {
							bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE.anteil_lademenge_lief.fn()));
						} else {
							bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE.anteil_ablademenge_lief.fn()));
						}
						
						if (bd_menge != null && bd_menge.isOK()) {
							rec_vpos_kon_ek.nv(VPOS_KON.anzahl, bd_menge.get_bdWert(), bibMSG.MV());
							String sql=rec_vpos_kon_ek.get_sql_4_save(true);
							if (S.isFull(sql)) {
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Beim Speichern wird die Kontraktpositionsmenge (EK) auf ").ut(bd_menge.get_FormatedRoundedNumber(3)).t(" gesetzt !")));
								v_return.add(sql);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung ! Fehler beim Schreiben der Menge in die EK-Kontrakt-Position! ")));
				}
			}
			
			
			if (id_vpos_kon_vk!=null && id_vpos_kon_vk.isOK()) {
				try {
					Rec20 rec_vpos_kon_vk = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon_vk.get_lValue());
					if (rec_vpos_kon_vk.is_yes_db_val(VPOS_KON.typ_ladung)) {
						MyBigDecimal bd_menge = null;
						if (map.get_bActualDBValue(VPOS_TPA_FUHRE.ablademenge_rechnung.fn())) {
							bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE.anteil_ablademenge_abn.fn()));
						} else {
							bd_menge = new MyBigDecimal(map.get_cActualDBValueFormated(VPOS_TPA_FUHRE.anteil_lademenge_abn.fn()));
						}
						
						if (bd_menge != null && bd_menge.isOK()) {
							rec_vpos_kon_vk.nv(VPOS_KON.anzahl, bd_menge.get_bdWert(), bibMSG.MV());
							String sql=rec_vpos_kon_vk.get_sql_4_save(true);
							if (S.isFull(sql)) {
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Beim Speichern wird die Kontraktpositionsmenge (VK) auf ").ut(bd_menge.get_FormatedRoundedNumber(3)).t(" gesetzt !")));
								v_return.add(sql);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung ! Fehler beim Schreiben der Menge in die VK-Kontrakt-Position! ")));
				}
			}
			
//			if (v_return.size()>0) {
//				DEBUG.System_print(v_return, true);
//			} else {
//				DEBUG.System_println("keine korrektur der vpos", true);
//			}
			
			return v_return;

		}
		
		
	}
	


	
	private class MyE2_DB_SelectField_LKW extends MyE2_DB_SelectField {

		private String sqlMaschinenKLW_Shadow = "SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
				bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
				" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
				" JT_MASCHINENTYP.IST_LKW='Y'"
				+ " AND NVL(JT_MASCHINEN.AKTIV,'N') = 'N' "
				+ "ORDER BY KFZKENNZEICHEN";		

		
		/**
		 * @author manfred
		 * @date 28.09.2020
		 *
		 * @param osqlField
		 * @throws myException
		 */
		public MyE2_DB_SelectField_LKW(SQLField osqlField) throws myException {
			super(osqlField,
					"SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
							bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
							" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
							" JT_MASCHINENTYP.IST_LKW='Y'"
							+ " AND NVL(JT_MASCHINEN.AKTIV,'N') = 'Y' "
							+ "ORDER BY KFZKENNZEICHEN"
					,false,false);
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] cResultShadow = oDB.EinzelAbfrageInArrayFormatiert(sqlMaschinenKLW_Shadow,"");
			bibALL.destroy_myDBToolBox(oDB);
			this.set_odataToViewShadow(new dataToView(cResultShadow, false, bibE2.get_CurrSession()));
			
			this.setWidth(new Extent(275));
			
		}
		
	}
	
	private class MyE2_DB_SelectField_ANH extends MyE2_DB_SelectField {

		private String sqlMaschinenANH_Shadow = "SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
				bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
				" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
				" JT_MASCHINENTYP.IST_ANHAENGER='Y'"
				+ " AND NVL(JT_MASCHINEN.AKTIV,'N') = 'N' "
				+ "ORDER BY KFZKENNZEICHEN";		

		
		/**
		 * @author manfred
		 * @date 28.09.2020
		 *
		 * @param osqlField
		 * @throws myException
		 */
		public MyE2_DB_SelectField_ANH(SQLField osqlField) throws myException {
			super(osqlField,
					"SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
							bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
							" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
							" JT_MASCHINENTYP.IST_ANHAENGER='Y'"
							+ " AND NVL(JT_MASCHINEN.AKTIV,'N') = 'Y' "
							+ "ORDER BY KFZKENNZEICHEN"
					,false,false);
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] cResultShadow = oDB.EinzelAbfrageInArrayFormatiert(sqlMaschinenANH_Shadow,"");
			bibALL.destroy_myDBToolBox(oDB);
			this.set_odataToViewShadow(new dataToView(cResultShadow, false, bibE2.get_CurrSession()));
			
			this.setWidth(new Extent(275));

			
		}
		
	}
		

}
