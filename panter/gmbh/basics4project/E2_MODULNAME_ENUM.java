package panter.gmbh.basics4project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.all.test.Component_SandBox_Container;

import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.MA_DES_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage.TLV_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK.UP_DOWN_FILENAME_Container;
import panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS.ADM_BasicModulContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE.ADK_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG.USER__LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST.DRUCK_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.LAND.LAND__LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOGTRIG__LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MAIL_ARCHIVE.MAR_ModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER.MASM_ModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST.SCAN_LIST_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.SANKTION_FREIGABE.ADR_FREIGABE_LIST_BasicModuleContainer;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.Comparator_For_2_dimensional_Arrays;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.Term;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.AS_BasicModulContainerLIST;
import rohstoff.Echo2BusinessLogic.BAM_BETRIEB.BAMB_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BAMF_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BAM_IMPORT.BAM_IMPORT_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BANKENSTAMM.BANK_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE.BSAAL__ModulContainerLIST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.BSA_K_LIST__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.BOR_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX_K_L__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_K_LIST__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING.BSKC_ModulContainerLIST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_LIST__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS.BSFP_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE.BS_VL_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST_K_LIST__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT.FPU_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF.FU_SONDER_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN.TP_KST_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA.UMA_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG.B_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.FZ_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.FZ__VALIDKEYS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG.ATOM_LAG_BEW_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_INVENTUR.ATOM_LAG_INV_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO.ATOM_SALDO_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG.LA_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.CONTYP_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.DienstleistungsProfile.DL_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FAHRPLANVARIANTEN.FV_ModulContainerList;
import rohstoff.Echo2BusinessLogic.FIBU.FIBU_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES.FIBU_EXPORT_PROFILES_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.FIBU_EXPORT_ModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN.FIBU_KONTEN_REGELN_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.FKR_FB_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM.FKR_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_LIST;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.INVENTUR_FREMDLAGER.INF_ANFORDERUNG_INVENTUR_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGERSTATUS.LAG_STAT_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG.LAG_BEW_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGER_INVENTUR.LAG_INV_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.LAG_KTO_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.MASCHINENSTAMM.MS_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG.PROJEKT_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.REPORT_VERLAUF.REP_VER_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.SPIELWIESE.SPIELWIESE_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.WF_SIMPLE_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER.ZT_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.AW_RohstoffAuswertungen;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2__BasicContainer_RohstoffAuswertungen;
import rohstoff.Echo2BusinessLogic._TAX.RATE.TAX__LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic._TAX.RATE_V2.TX_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic._TaxOld.TO_LIST_BasicModuleContainer;
import rohstoff.businesslogic.StammDaten.FuhrenKostenTypen.FKT_LIST_BasicModuleContainer;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_LIST_BasicModuleContainer;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_LIST_BasicModuleContainer;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_LIST_BasicModuleContainer;
import rohstoff.businesslogic.bewegung2.lager_liste.B2_LALI_LIST_BasicModuleContainer;
import rohstoff.businesslogic.bewegung2.lager_saldo.B2_LAG_SALDO_LIST_BasicModuleContainer;
import rohstoff.businesslogic.bewegung2.list.B2_ListBasicModuleContainer;

public class E2_MODULNAME_ENUM {

	public enum MODUL_TYP {
		MASK
		,MASK_RB         //neue maske (RB-Klassen)
		,LIST
		,INTRINSIC
		,DUMMY
	}



	public enum MODUL implements E2_MODUL_DESCRIPTOR_IF{

		NAME_MODUL_FUHRENFUELLER(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,						new MyE2_String("Fuhrenzentrale")),
		NAME_MODUL_FAHRTENPOOL(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL, 						new MyE2_String("Fahrtenpool")),
		NAME_MODUL_FAHRPLAN(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FAHRPLAN, 							new MyE2_String("Fahrplan")),
		NAME_MODUL_FAHRPLANUEBERSICHT(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FAHRPLANUEBERSICHT, 				new MyE2_String("Fahrplan-Übersicht")),
		NAME_MODUL_FBAM_LIST(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FBAM_LIST, 						new MyE2_String("Fuhren-Beanstandungen")),
		NAME_MODUL_BBAM_LIST(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_BBAM_LIST, 						new MyE2_String("Betriebs-Beanstandungen")),
		NAME_MODUL_FAHRTENVARIANTEN(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FAHRTENVARIANTEN, 					new MyE2_String("Fahrt-Varianten")),
		NAME_MODUL_FIRMENSTAMM_LIST(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST, 					new MyE2_String("Firmenstamm")),
		NAME_MODUL_ABNAHMEABGEBOT_LISTENEINGABE(MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ABNAHMEABGEBOT_LISTENEINGABE, 		new MyE2_String("Preise eingeben")),
		NAME_MODUL_MAILARCHIV(					MODUL_TYP.LIST, E2_CONSTANTS_AND_NAMES.NAME_MODUL_MAILARCHIV,		 		new MyE2_String("Mailarchiv")),
		NAME_MODUL_MASSMAILER(					MODUL_TYP.LIST, E2_CONSTANTS_AND_NAMES.NAME_MODUL_MASSMAILER, 				new MyE2_String("Massenmailer")),
		NAME_MODUL_ABNAHMEANGEBOT_LIST(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ABNAHMEANGEBOT_LIST, 				new MyE2_String("Abnahmeangebote")),
		NAME_MODUL_VERKAUFSANGEBOT_LIST(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_VERKAUFSANGEBOT_LIST, 				new MyE2_String("Verkaufsangebote")),
		NAME_MODUL_RECHNUNGEN_LIST(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_LIST, 					new MyE2_String("Rechnungen")),
		NAME_MODUL_GUTSCHRIFTEN_LIST(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_LIST, 				new MyE2_String("Gutschriften")),
		NAME_MODUL_ADMINTOOLS(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ADMINTOOLS, 						new MyE2_String("Administrator-Tools")),
		NAME_MODUL_ARTIKELSTAMM_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ARTIKELSTAMM_LISTE, 				new MyE2_String("Artikelstamm")),
		NAME_MODUL_EK_KONTRAKT_LIST(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST, 					new MyE2_String("Einkaufskontrakte")),
		NAME_MODUL_VK_KONTRAKT_LIST(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST, 					new MyE2_String("Verkaufskontrakte")),
		NAME_MODUL_VERTRAGSCLEARING_EK(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_VERTRAGSCLEARING_EK, 				new MyE2_String("Vertragsclearing (EK)")),
		NAME_MODUL_VERTRAGSCLEARING_VK(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_VERTRAGSCLEARING_VK, 				new MyE2_String("Vertragsclearing (VK)")),
		NAME_MODUL_TPA_LIST(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_TPA_LIST, 							new MyE2_String("Transportaufträge")),
		NAME_MODUL_EAK(							MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_EAK, 								new MyE2_String("EAK")),

		//2015-10-08: neue modulnamen fuer AVV-Code-Erfassung
		MODUL_EAK_BRANCHE(					    MODUL_TYP.INTRINSIC,														new MyE2_String("AVV-Branchen")),
		MODUL_EAK_GRUPPE(					    MODUL_TYP.INTRINSIC,														new MyE2_String("AVV-Gruppen")),
		MODUL_EAK_CODE(					    	MODUL_TYP.INTRINSIC,														new MyE2_String("AVV-Codes")),

		NAME_MODUL_FREIEPOSITIONEN(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN, 					new MyE2_String("Freie Positionen")),
		NAME_MODUL_BANKENSTAMM_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_BANKENSTAMM_LISTE, 				new MyE2_String("Bankenstamm")),
		NAME_MODUL_PROJEKT_LISTE(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_PROJEKT_LISTE, 					new MyE2_String("Projekte")),
		NAME_WORKFLOW_LAUFZETTEL_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, 				new MyE2_String("Laufzettel")),
		NAME_MODUL_POSITION_VORLAGEN(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_POSITION_VORLAGEN, 				new MyE2_String("Zusatzpositionen")),
		NAME_MODUL_LISTE_FUHREN_SONDERFAELLE(	MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LISTE_FUHREN_SONDERFAELLE, 		new MyE2_String("Fuhren-Sonderfälle")),
		NAME_MODUL_LAGERLISTE(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAGERLISTE, 						new MyE2_String("Lager-Bestand")),
		NAME_MODUL_LAGERLISTE_KONTO(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAGERLISTE_KONTO, 					new MyE2_String("Lager-Bewegung")),
		NAME_MODUL_LAGER_BEWEGUNG_LISTE(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAGER_BEWEGUNG_LISTE, 				new MyE2_String("Lager-Bewertung")),
		NAME_MODUL_LAGER_INVENTUR_LISTE(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAGER_INVENTUR_LISTE, 				new MyE2_String("Lager-Inventur")),
		NAME_MODUL_WIEGEKARTE_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE, 					new MyE2_String("Wiegekarten")),
		NAME_MODUL_FIBU_LIST(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_FIBU_LIST, 						new MyE2_String("Fibu")),
		NAME_MODUL_INTRASTAT_LISTE(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_INTRASTAT_LISTE, 					new MyE2_String("Intrastat")),
		NAME_MODUL_NACHRICHTEN_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE, 				new MyE2_String("Nachricht")),
		NAME_MODUL_TPA_KOSTEN_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_TPA_KOSTEN_LISTE, 					new MyE2_String("Transportkosten")),
		NAME_MODUL_ROHSTOFFAUSWERTUNGEN(		MODUL_TYP.LIST, 	new MyE2_String("Rohstoffauswertungen")),
		NAME_MODUL_ROHSTOFFAUSWERTUNGEN_NG(		MODUL_TYP.LIST,     new MyE2_String("Rohstoffauswertungen NG")),
		NAME_MODUL_ROHSTOFFAUSWERTUNGEN2(		MODUL_TYP.LIST,     new MyE2_String("Rohstoffauswertungen-Gruppierbar")),
		NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE(
				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE, 	new MyE2_String("Kundenstatus Forderungen")),
		NAME_MODUL_LAGERSTATUS_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAGERSTATUS_LISTE, 				new MyE2_String("Lagerstatus")),
		NAME_MODUL_MASCHINENSTAMM_LISTE(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE, 				new MyE2_String("Maschinenpark")),
		NAME_MODUL_LOG_TRIGGER_DEF_LISTE(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LOG_TRIGGER_DEF_LISTE, 			new MyE2_String("Änderungsüberwachung")),
		NAME_MODUL_UMA_LIST(					MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_UMA_LIST, 							new MyE2_String("Umarbeitungsverträge")),
		NAME_MODUL_ATOM_LIVELISTE(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ATOM_LIVELISTE, 					new MyE2_String("Auswertungszentrale")),
		NAME_MODUL_MAIL_INBOX_LISTE(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_MAIL_INBOX_LISTE, 					new MyE2_String("Email Eingang")),
		NAME_MODUL_ATOM_LAGER_SALDO(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_SALDO, 					new MyE2_String("Lagerbestand Bewegungsatom")),
		NAME_MODUL_WARENBEWEGUNG_NG_LIST(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_WARENBEWEGUNG_NG_LIST, 			new MyE2_String("Warenbewegungen")),
		NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST(	MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST, 			new MyE2_String("Lagerbewegungen Bewegungsatom")),
		NAME_MODUL_LAENDER_LIST(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAENDER_LIST, 						new MyE2_String("Länder")),
		NAME_MODUL_VERSENDE_INVENTUR_ANFORDERUNG(
				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_VERSENDE_INVENTUR_ANFORDERUNG, 	new MyE2_String("Inventurmails versenden")),
		NAME_MODUL_LAGER_ATOM_INVENTUR_LISTE(	MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_LAGER_ATOM_INVENTUR_LISTE, 		new MyE2_String("Lagerinventur Bewegungsatom")),
		NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE(
				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE, new MyE2_String("Lagerbewertung Kostenbehaftet")),
		NAME_MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_LISTE(
				MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_LISTE, new MyE2_String("Lagerbewertung reiner WE-Wert")),
		NAME_LIST_FIBU_KONTEN_REGELN(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_LIST_FIBU_KONTEN_REGELN, 				new MyE2_String("Erfassung von FIBU-Konten-Regeln (ALT)")),
		NAME_FIBU_KONTEN_EXPORT(				MODUL_TYP.LIST, E2_MODULNAMES.NAME_FIBU_KONTEN_EXPORT, 						new MyE2_String("Datev-Export")),
		NAME_FIBU_EXPORT_PROFILES_LIST(			MODUL_TYP.LIST, E2_MODULNAMES.NAME_FIBU_EXPORT_PROFILES_LIST, 				new MyE2_String("Datev-Exportprofile")),
		NAME_LIST_FIBU_KONTEN_REGELN_NEU(		MODUL_TYP.LIST, E2_MODULNAMES.NAME_LIST_FIBU_KONTEN_REGELN_NEU, 			new MyE2_String("Erfassung von FIBU-Konten-Regeln")),
		NAME_LIST_FIBU_KONTEN_REGELN_FILTERBASED(
				MODUL_TYP.LIST, E2_MODULNAMES.NAME_LIST_FIBU_KONTEN_REGELN_FILTERBASED, 	new MyE2_String("Filterbasierte Kontenregeln")),
		SPIELWIESE_LIST(						MODUL_TYP.LIST, E2_MODULNAMES.SPIELWIESE_LIST, 								new MyE2_String("Spielwiese List")),
		BAM_IMPORT_LIST(						MODUL_TYP.LIST,E2_MODULNAMES.BAM_IMPORT_LIST, 								new MyE2_String("Importliste der Lagerabzüge")),

		MODUL_TAXRULES_LIST(					MODUL_TYP.LIST,"MODUL_TAXRULES_LIST", 										new MyE2_String("Regeln für Bewegungssatz-Prüfung")),
		MODUL_TAX_LIST(							MODUL_TYP.LIST,"MODUL_TAX_LIST", 											new MyE2_String("Steuersätze")),


		//2015-07-07: pseudoeintrag fuer modulweite validierung
		//MODUL_KENNER_PROGRAMM_WIDE(             MODUL_TYP.DUMMY, new MyE2_String("Alle Module / Programmweit gültig"), E2_GLOBAL_VALID_KEYS.values()),
		//aenderung: 2019-01-09: martin
		MODUL_KENNER_PROGRAMM_WIDE(             MODUL_TYP.DUMMY, new MyE2_String("Alle Module / Programmweit gültig")),

		//2015-06-18: neue fuhrenzentrale
		FUZE_ATOM_BASED_LIST(					MODUL_TYP.LIST,new MyE2_String("Fuhrenzentrale (NEU) - Listenmodul"), FZ__VALIDKEYS.values()),
		FUZE_ATOM_BASED_MASK(					MODUL_TYP.MASK_RB,new MyE2_String("Fuhrenmaske"), FZ__VALIDKEYS.values()),


		//2015-08-21: definitionsmodul fuer adressklassen mit farbwaehler
		ADRESSKLASSE_DEF_LIST(                  MODUL_TYP.LIST, new MyE2_String("Definition der Adress-Klassen")),

		//2017-07-25: fuer jeden ein testmodul
		TESTMODUL_MANFRED(						MODUL_TYP.LIST, new MyE2_String("Testmodul Manfred")),
		TESTMODUL_MARTIN(						MODUL_TYP.LIST, new MyE2_String("Testmodul Martin")),
		TESTMODUL_SEBASTIEN(					MODUL_TYP.LIST, new MyE2_String("Testmodul Sebastien")),

		MODUL_TAX_MASK(		 					MODUL_TYP.MASK,	 new MyE2_String("Maske Steuersätze")),
		MODUL_TAXRULES_MASK( 					MODUL_TYP.MASK,	 new MyE2_String("Maske Handelsdefinitonen")),

		//alle alten masken uebersetzt
		MODUL_FUHRENMASKE(						MODUL_TYP.MASK, "MODUL_FUHRENMASKE",new MyE2_String("Fuhrenmaske")),
		NAME_MODUL_FUHREN_ORT_MASKE(			MODUL_TYP.MASK, "NAME_MODUL_FUHREN_ORT_MASKE",new MyE2_String("Fuhren-Ort-Maske")),
		FUHRE_FBAM_MASK	(						MODUL_TYP.MASK,"FUHRE_FBAM_MASK",new MyE2_String("Maske Fuhrenbeanstandung")),
		MODUL_BBAM_MASK	(						MODUL_TYP.MASK,"MODUL_BBAM_MASK",new MyE2_String("Maske Betriebsbeanstandung")),
		MODUL_FIRMENSTAMM_MASKE	(				MODUL_TYP.MASK,"MODUL_FIRMENSTAMM_MASKE",new MyE2_String("Maske Adresse/Firmenstamm")),
		MODUL_FIRMENSTAMM_MASK_LAGER_MASKE	(	MODUL_TYP.MASK,"MODUL_FIRMENSTAMM_MASK_LAGER_MASKE",new MyE2_String("Maske Lageradresse")),
		MODUL_ARTIKELSTAMM_MASKE	(			MODUL_TYP.MASK,"MODUL_ARTIKELSTAMM_MASKE",new MyE2_String("Maske Artikelstamm")),
		MODUL_LAGERMASKE	(					MODUL_TYP.MASK,"MODUL_LAGERMASKE",new MyE2_String("Maske Lager")),
		MODUL_ABNAHMEANGEBOT_MASK(				MODUL_TYP.MASK,"MODUL_ABNAHMEANGEBOT_MASK",new MyE2_String("Maske Abnahmeangebot")),
		NAME_MODUL_ABNAHMEANGEBOT_POS_MASK	(	MODUL_TYP.MASK,"NAME_MODUL_ABNAHMEANGEBOT_POS_MASK",new MyE2_String("Maske Position im Abnahmeangebot")),
		MODUL_VERKAUFSANGEBOT_MASK	(			MODUL_TYP.MASK,"MODUL_VERKAUFSANGEBOT_MASK",new MyE2_String("Maske Verkaufsangebot")),
		MODUL_VERKAUFSANGEBOT_POS_MASK	(		MODUL_TYP.MASK,"MODUL_VERKAUFSANGEBOT_POS_MASK",new MyE2_String("Maske Position im Verkaufsangebot")),
		MODUL_EK_KONTRAKT_MASK	(				MODUL_TYP.MASK,"MODUL_EK_KONTRAKT_MASK",new MyE2_String("Maske EK-Kontrakt")),
		MODUL_EK_KONTRAKT_POS_MASK	(			MODUL_TYP.MASK,"MODUL_EK_KONTRAKT_POS_MASK",new MyE2_String("Maske Position im EK-Kontrakt")),
		MODUL_VK_KONTRAKT_MASK	(				MODUL_TYP.MASK,"MODUL_VK_KONTRAKT_MASK",new MyE2_String("Maske VK-Kontrakt")),
		MODUL_VK_KONTRAKT_POS_MASK(				MODUL_TYP.MASK,"MODUL_VK_KONTRAKT_POS_MASK",new MyE2_String("Maske Position im VK-Kontrakt")),
		NAME_MODUL_TPA_MASK	(					MODUL_TYP.MASK,"NAME_MODUL_TPA_MASK",new MyE2_String("Maske Transportauftrag")),
		NAME_MODUL_TPA_POS_MASK	(				MODUL_TYP.MASK,"NAME_MODUL_TPA_POS_MASK",new MyE2_String("Maske Position im Transportauftrag")),
		NAME_MODUL_RECHNUNGEN_MASK(				MODUL_TYP.MASK,"NAME_MODUL_RECHNUNGEN_MASK",new MyE2_String("Maske Rechnungen")),
		NAME_MODUL_RECHNUNGEN_POS_MASK(			MODUL_TYP.MASK,"NAME_MODUL_RECHNUNGEN_POS_MASK",new MyE2_String("Maske Position der Rechnung")),
		NAME_MODUL_GUTSCHRIFTEN_MASK(			MODUL_TYP.MASK,"NAME_MODUL_GUTSCHRIFTEN_MASK",new MyE2_String("Maske Gutschriften")),
		NAME_MODUL_GUTSCHRIFTEN_POS_MASK(		MODUL_TYP.MASK,"NAME_MODUL_GUTSCHRIFTEN_POS_MASK",new MyE2_String("Maske Position der Gutschrift")),
		NAME_MODUL_WIEGEKARTE_MASKE(			MODUL_TYP.MASK,"NAME_MODUL_WIEGEKARTE_MASKE",new MyE2_String("Maske Wiegekarte")),
		NAME_MODUL_BANKENSTAMM_MASKE(			MODUL_TYP.MASK,"NAME_MODUL_BANKENSTAMM_MASKE",new MyE2_String("Maske Bank")),
		NAME_MODUL_PROJEKT_MASKE(				MODUL_TYP.MASK,"NAME_MODUL_PROJEKT_MASKE",new MyE2_String("Maske Projekt")),
		NAME_WORKFLOW_LAUFZETTEL_MASKE(			MODUL_TYP.MASK,"NAME_WORKFLOW_LAUFZETTEL_MASKE",new MyE2_String("Maske Laufzettel")),
		NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE(	MODUL_TYP.MASK,"NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE",new MyE2_String("Maske Aufgabe im Laufzettel")),
		MODUL_LAGER_BEWEGUNG_MASKE(				MODUL_TYP.MASK,"MODUL_LAGER_BEWEGUNG_MASKE",new MyE2_String("Maske Lager-Bewegung")),
		MODUL_LAGER_INVENTUR_MASKE(				MODUL_TYP.MASK,"MODUL_LAGER_INVENTUR_MASKE",new MyE2_String("Maske Lager-Inventur")),
		MODUL_FIBU_MASK(						MODUL_TYP.MASK,"MODUL_FIBU_MASK",new MyE2_String("Maske Fibu")),
		MODUL_INTRASTAT_MASKE(					MODUL_TYP.MASK,"MODUL_INTRASTAT_MASKE",new MyE2_String("Maske Intrastat")),
		MODUL_NACHRICHTEN_MASKE(				MODUL_TYP.MASK,"MODUL_NACHRICHTEN_MASKE",new MyE2_String("Maske Nachrichten")),
		MODUL_LOG_TRIGGER_DEF_MASKE(			MODUL_TYP.MASK,"MODUL_LOG_TRIGGER_DEF_MASKE",new MyE2_String("Maske Überwachungspunkte")),
		MODUL_SELEKTORDEF_MASK(					MODUL_TYP.MASK,"MODUL_SELEKTORDEF_MASK",new MyE2_String("Maske Definition von Selektoren")),
		MODUL_GROOVYDEF_MASK(					MODUL_TYP.MASK,"MODUL_GROOVYDEF_MASK",new MyE2_String("Maske Definition von Groovy-Scripten")),
		MODUL_ATOM_LAGER_BEWEGUNG_MASK(			MODUL_TYP.MASK,"MODUL_ATOM_LAGER_BEWEGUNG_MASK",new MyE2_String("Lagerbewegungen Atom-basiert")),
		MODUL_LAGERSTATUS_MASKE(				MODUL_TYP.MASK,"MODUL_LAGERSTATUS_MASKE",new MyE2_String("Maske Lagerstatus")),
		NAME_MODUL_MASCHINENSTAMM_MASKE(		MODUL_TYP.MASK,"NAME_MODUL_MASCHINENSTAMM_MASKE",new MyE2_String("Maske Maschinenstamm")),
		MODUL_MASCHINENSTAMM_AUFGABE_MASKE(		MODUL_TYP.MASK,"MODUL_MASCHINENSTAMM_AUFGABE_MASKE",new MyE2_String("Maske Aufgaben Maschinenstamm")),
		MODUL_KREDITVERSICHERUNG_MASKE(			MODUL_TYP.MASK,"MODUL_KREDITVERSICHERUNG_MASKE",new MyE2_String("Maske Kreditversicherungen")),
		MODUL_UMA_MASK(							MODUL_TYP.MASK,"MODUL_UMA_MASK",new MyE2_String("Maske Umarbeitungen")),
		MODUL_MAIL_INBOX_MASKE(					MODUL_TYP.MASK,"MODUL_MAIL_INBOX_MASKE",new MyE2_String("Maske Maileingang")),
		MODUL_LAENDER_MASK(						MODUL_TYP.MASK,"MODUL_LAENDER_MASK",new MyE2_String("Maske Länder")),
		MODUL_FIELDRULE_MASK(					MODUL_TYP.MASK,"MODUL_FIELDRULE_MASK",new MyE2_String("Maske Feldregeln")),
		MODUL_ADRESSEN_KOSTEN_MASK(				MODUL_TYP.MASK,"MODUL_ADRESSEN_KOSTEN_MASK",new MyE2_String("Maske Kosten")),
		MODUL_LAGER_ATOM_INVENTUR_MASKE(		MODUL_TYP.MASK,"MODUL_LAGER_ATOM_INVENTUR_MASKE",new MyE2_String("Maske Lagerinventur")),
		MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_MASKE(
				MODUL_TYP.MASK,"MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_MASKE",new MyE2_String("Maske Lagerbewertung mit Kosten")),
		MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_MASKE(
				MODUL_TYP.MASK,"MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_MASKE",new MyE2_String("Maske Lagerbewertung ohne Kosten")),
		MODUL_MAIL_AUS_MASK_DEF__MASK(			MODUL_TYP.MASK,"MODUL_MAIL_AUS_MASK_DEF__MASK",new MyE2_String("Definitionen Mail-aus-Maske")),
		COLS_TO_CALC_DEF_MASK(					MODUL_TYP.MASK,"COLS_TO_CALC_DEF_MASK",new MyE2_String("Maske Spaltenrechner")),
		NAME_MASK_FIBU_KONTEN_REGELN_NEU(		MODUL_TYP.MASK,"NAME_MASK_FIBU_KONTEN_REGELN_NEU",new MyE2_String("Maske Fibu-Kontenregeln")),
		BAM_IMPORT_MASK(						MODUL_TYP.MASK,"BAM_IMPORT_MASK",new MyE2_String("Maske Import BAM")),
		EMAIL_SEND_MASK(						MODUL_TYP.MASK,"EMAIL_SEND_MASK",new MyE2_String("Maske eMail-Sendungen")),





		//INTRINSISCHE Module (meist listen in masken)
		NAME_MANDANT_MASKENMODUL_EMAIL_SCHABLONEN_LIST(MODUL_TYP.INTRINSIC,"MANDANT_MASKENMODUL_EMAIL_SCHABLONEN_LIST", new MyE2_String("eMail-Schablonen für automatisch erzeugt eMails (Liste)"))
		//		,NAME_MANDANT_MASKENMODUL_EMAIL_SCHABLONEN_MASK(MODUL_TYP.INTRINSIC,"MANDANT_MASKENMODUL_EMAIL_SCHABLONEN_MASK", new MyE2_String("eMail-Schablonen für automatisch erzeugt eMails (daraus resultierende Maske)"))



		//maskenmodule nach der neuen struktur
		,POPUP_EMAIL_SEND(					MODUL_TYP.MASK_RB,		new MyE2_String("Maske eMail-Versand-Verwaltung"))
		,MASK_EMAIL_SCHABLONEN(				MODUL_TYP.MASK_RB,		new MyE2_String("Maske eMail-Versand-Schablonen in Mandantenstamm"))
		,ADRESSKLASSE_DEF_MASK(				MODUL_TYP.MASK_RB,		new MyE2_String("Definition der Adressklasse"))
		,HILFETEXT_MASKE(					MODUL_TYP.MASK_RB,		new MyE2_String("Hilfetext erfassen/bearbeiten"))					

		//2015-10-06: upload-button ohne bezweichner
		,POPUP_UPLOADS(						MODUL_TYP.LIST, new MyE2_String("Upload-Dateien"))
		,POPUP_WINDOW_HELPTEXT_LIST(        MODUL_TYP.LIST, new MyE2_String("Hilfetext-Auflistung"))

		,NAME_MODUL_MANDANTENVERWALTUNG_LISTE( MODUL_TYP.LIST, new MyE2_String("Mandanten-Verwaltung, Listenansicht"))				
		,NAME_MODUL_MANDANTENVERWALTUNG_MASKE( MODUL_TYP.MASK, new MyE2_String("Mandanten-Verwaltung, Maske"))				

		//2015-10-19: neue module: verwaltung der Scanner
		,SCANNER_LIST( MODUL_TYP.LIST, new MyE2_String("Scanner-Liste"))				
		,SCANNER_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Scanner-Maske"))				


		//		//2015-10-23: testpaar an keys liste-maske_rb zum testn Programmgenerator
		//		,TEST_LIST( MODUL_TYP.LIST, new MyE2_String("Test-Liste"))				
		//		,TEST_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Test-Maske"))				

		,REPORT_REITER_LIST( MODUL_TYP.INTRINSIC, new MyE2_String("Report-Reiter-Liste"))		
		,REPORT_REITER_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Report-Reiter-Maske"))		
		,REPORT_INFO_U_PASSWORT_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Report-Info-und-Passwort-Eingabe"))		

		//2016-03-16: neues modul: erfassung der bordercrossing-warnungen
		,BORDERCROSSING_LIST( MODUL_TYP.LIST, new MyE2_String("Liste zu überwachender Grenzübertritte"))		
		,BORDERCROSSING_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Grenzübertritt"))		

		//2016-03-16: neues modul: erfassung der bordercrossing-warnungen
		,REMINDER_DEF_LIST( MODUL_TYP.LIST, new MyE2_String("Erinnerungsmeldung-Liste"))		
		,REMINDER_DEF_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Erinnerungsmeldung-Maske"))		

		,KOSTENERFASSUNG_FUHRE_NG(MODUL_TYP.MASK_RB, new MyE2_String("Erfassung von Fuhrenkosten mit Verteiler"))

		//2016-06-22: neues untermodul zur bearbeitung von adress-infos
		,ADRESSE_INFO_LIST_LIST(MODUL_TYP.LIST, new MyE2_String("Erfassung von Adress-Infos (Liste)"))
		,ADRESSE_INFO_LIST_MASK(MODUL_TYP.MASK_RB, new MyE2_String("Erfassung von Adress-Infos (Maske)"))

		//2016-07-04: 
		,COMPONENT_SANDBOX_MODUL(MODUL_TYP.DUMMY, new MyE2_String("ZZZ-Test Umgebung"))

		//2019-09-08
		,MODUL_ZOLLTARIFNUMMER_LIST(MODUL_TYP.LIST, new MyE2_String("Zolltarifnummern-Liste"))
		,MODUL_ZOLLTARIFNUMMER_MASK(MODUL_TYP.MASK_RB, new MyE2_String("Zolltarifnummern-Maske"))

		//2020-10-16: neue Version Zolltarif-Verwaltung mit pruefung der aenderung von RC-Setting im Land
		,ZT_NUMMER_LIST(MODUL_TYP.LIST, new MyE2_String("Zolltarifnummern-Liste"))
		,ZT_NUMMER_MASK(MODUL_TYP.MASK_RB, new MyE2_String("Zolltarifnummern-Maske"))
	
		
		,MAHNUNG_MASK(MODUL_TYP.MASK_RB, new MyE2_String("Mahnung-Maske"))

		//2016-11-18: sebastien
		,NAME_MODUL_EK_KONTRAKT_LIST_NG(MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST_NG, 	new MyE2_String("Einkaufskontrakte (NG)"))
		,NAME_MODUL_VK_KONTRAKT_LIST_NG(MODUL_TYP.LIST, E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST_NG, 	new MyE2_String("Verkaufskontrakte (NG)"))
		,NAME_MODUL_EK_KONTRAKT_MASK_NG(MODUL_TYP.MASK_RB, E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_MASK_NG, 	new MyE2_String("Einkaufskontrakte (NG)"))
		,NAME_MODUL_VK_KONTRAKT_MASK_NG(MODUL_TYP.MASK_RB, E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_MASK_NG, 	new MyE2_String("Verkaufskontrakte (NG)"))


		//2016-12-21: action-trigger-def-module
		,TRIGGER_ACTION_DEF_LIST(MODUL_TYP.LIST,  	new MyE2_String("Definition der Action-Trigger"))
		,TRIGGER_ACTION_DEF_MASK(MODUL_TYP.MASK_RB, new MyE2_String("Definition der Action-Trigger"))


		//2017-01-04: liefertermine and lieferung list und maske
		//,VPOS_KON_LAGER_LIST(MODUL_TYP.LIST,  		new MyE2_String("List fur die Lieferung"))
		,VPOS_KON_LIEFERUNG_ZUM_LAGER_MASK(MODUL_TYP.MASK_RB, 	new MyE2_String("Maske für die Lieferung zum Lagen innerhalb der Kontraktpositionsmaske"))
		,VPOS_KON_TERMINE_LIST(MODUL_TYP.LIST,  	new MyE2_String("List fur die Lieferterminen"))
		,VPOS_KON_TERMINE_MASK(MODUL_TYP.MASK_RB, 	new MyE2_String("Mask fur die Lieferterminen"))

		//2017-03-07: neue module: verwaltung der Drucker
		,DRUCKER_LIST( MODUL_TYP.LIST, new MyE2_String("Drucker-Liste"))				
		,DRUCKER_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Drucker-Maske"))			

		//2017-09-15: benutzerverwaltung als standardmodul in den tabs
		,NAME_MODUL_USERVERWALTUNG_LISTE( MODUL_TYP.LIST, new MyE2_String("Benutzer-Liste"))	
		,NAME_MODUL_USERVERWALTUNG_MASKE( MODUL_TYP.MASK, new MyE2_String("Benutzer-Maske"))
		,NAME_MODUL_USERVERWALTUNG_LISTE_POPUP_USERSETTINGS(MODUL_TYP.INTRINSIC, new MyE2_String("Rechtevergabe"))

		//2017-10-11: anhang7-p
		,AH7_STEUERDATEI_LISTE( MODUL_TYP.LIST, new MyE2_String("Anhang7-Druck-Steuerdatei"))	
		,AH7_STEUERDATEI_MASKE( MODUL_TYP.MASK_RB, new MyE2_String("Anhang7-Druck-Steuerdatensatz"))

		//2017-10-23: anhang7-steuerdatei
		,AH7_PROFIL_LIST( MODUL_TYP.LIST, new MyE2_String("Anhang7-Profil"))	
		,AH7_PROFIL_MASK( MODUL_TYP.MASK_RB, new MyE2_String("Anhang7-Profil (Maske)"))

		//2017-10-18
		,WF_COPY_LAUFZETTEL(MODUL_TYP.MASK, new MyE2_String("Kopieren eines Laufzettels"))

		//2017-11-13: mask definition 
		,MASK_DESIGN_LIST(MODUL_TYP.LIST, 			new MyE2_String("Mask Definition (Maske)"))
		,MASK_DESIGN_MASK(MODUL_TYP.MASK_RB,  		new MyE2_String("Mask Definition (Liste)"))
		,MASKCELL_DESIGN_LIST(MODUL_TYP.LIST,  		new MyE2_String("Zelle Definition (Liste)"))
		,MASKCELL_DESIGN_MASK(MODUL_TYP.MASK_RB, 	new MyE2_String("Zelle Definition (Maske)"))

		//20171127: messageprovider-modul
		,MESSAGE_PROVIDER_LIST(MODUL_TYP.LIST,      S.ms("Meldungsverteile für systeminterne Meldungen (Liste)"))
		,MESSAGE_PROVIDER_MASK(MODUL_TYP.MASK_RB,     S.ms("Meldungsverteile für systeminterne Meldungen (Maske)"))

		//20171201: Container-Verwaltung (Basierend auf ContainerTyp
		,CONTAINER_LIST(MODUL_TYP.LIST, S.ms("Container-Verwaltung (Liste)"))
		,CONTAINER_MASK(MODUL_TYP.MASK_RB, S.ms("Container-Verwatung (Maske)"))

		//20171206: Container-Verwaltung (Basierend auf ContainerTyp
		,CONTAINERTYP_LIST(MODUL_TYP.LIST, S.ms("ContainerTyp-Verwaltung (Liste)"))
		,CONTAINERTYP_MASK(MODUL_TYP.MASK_RB, S.ms("ContainerTyp-Verwatung (Maske)"))

		,MODUL_MASCHINENSTAMM_KOSTEN_LISTE(		MODUL_TYP.INTRINSIC, new MyE2_String("Maske-Liste im Maschinentyp"))
		,MODUL_MASCHINENSTAMM_KOSTEN_MASKE(		MODUL_TYP.INTRINSIC, new MyE2_String("Maske Kosten im Maschinentyp"))


		//20181120: Neues BewegungsModell: LISTE/MASKE
		,B_TRANSPORT_LIST(MODUL_TYP.LIST, S.ms("Bewegungssatz (Liste)"))
		,B_TRANSPORT_MASK(MODUL_TYP.MASK_RB, S.ms("Bewegungssatz (Maske)"))

		,BG_LAGER_BEWEGUNG_LIST(MODUL_TYP.LIST		, S.ms("Lagerbewegung (Liste)"))
		,BG_LAGER_BEWEGUNG_MASK(MODUL_TYP.MASK_RB	, S.ms("Lagerbewegung (Maske)"))

		,BG_LAGER_SALDO_LIST(MODUL_TYP.LIST		, S.ms("Lagersaldo (Liste)"))

		,FUHREN_KOSTEN_TYP_LIST(MODUL_TYP.LIST		, S.ms("Fuhrenkosten-Typen (Liste)"))
		,FUHREN_KOSTEN_TYP_MASK(MODUL_TYP.MASK_RB	, S.ms("Fuhrenkosten-Typen (Maske)"))

		//2018-04-04: Profile fur grenzubertritt beleg
		,BGL_PROFIL_MASK(MODUL_TYP.MASK_RB			, S.ms("Grenzubertritt Beleg Profile(Maske)"))

		//20180503: jaspercompile-chain
		,JASPER_COMPILE_CHAIN_LIST(MODUL_TYP.LIST		, S.ms("Kompilierkette für Jasperreports (Liste)"))
		,JASPER_COMPILE_CHAIN_MASK(MODUL_TYP.MASK_RB   , S.ms("Kompilierkette für Jasperreports (Maske)"))

		//2018-05-18: neues modulsettings-Tab: searchdef
		,SEARCHDEF_LIST(MODUL_TYP.LIST		, S.ms("Suchdefinitionen (Liste)"))
		,SEARCHDEF_MASK(MODUL_TYP.MASK_RB	, S.ms("Suchdefinitionen (Maske)"))


		//2018-05-22: freigab modul
		,SANKTION_PRUEFUNG_LIST(MODUL_TYP.LIST		, S.ms("Adressen-Sanktionen (Liste)"))
		,SANKTION_PRUEFUNG_MASK(MODUL_TYP.MASK_RB	, S.ms("Adressen-Sanktionen (Maske)"))

		// 2018-06-12: Tochtertabelle für die Artikeldefinition bei der Grenzübertritt-Überwachtung
		,BORDERCROSSING_ARTIKEL_LIST(MODUL_TYP.LIST, S.ms("Grenzübertritt-Artikel (Liste)"))
		,BORDERCROSSING_ARTIKEL_MASK(MODUL_TYP.MASK_RB, S.ms("Grenzübertritt-Artikel (Maske)"))

		//2018-06-13: steuersaetze in gruppen einteilen
		,TAX_GROUP_LIST(MODUL_TYP.LIST		, S.ms("Steuersatz (Tax-) Gruppe (Liste)"))
		,TAX_GROUP_MASK(MODUL_TYP.MASK_RB	, S.ms("Steuersatz (Tax-) Gruppe (Maske)"))


		//2018-05-22: Freigabe Modul fuer Sanktionen
		,REPORT_VERLAUF_LIST(MODUL_TYP.LIST		, S.ms("Reportverlauf (Liste)"))
		,REPORT_VERLAUF_MASK(MODUL_TYP.MASK_RB	, S.ms("Reportverlauf (Maske)"))
		
		//2018-07-20: Reports auf query-basis
		,REPORTING_QUERY_LIST(MODUL_TYP.LIST		, S.ms("SQL-Basierte Abfragen (Liste)"))
        ,REPORTING_QUERY_MASK(MODUL_TYP.MASK_RB		, S.ms("SQL-Basierte Abfragen  (Maske)"))
        ,REPORTING_QUERY_FIELD_LIST(MODUL_TYP.INTRINSIC	, S.ms("Abfragefeld (Liste)"))    // zwei tochterlisten, felder und parameter
        ,REPORTING_QUERY_FIELD_MASK(MODUL_TYP.MASK_RB	, S.ms("Abfragefeld (Maske)"))
        ,REPORTING_QUERY_PARAM_LIST(MODUL_TYP.INTRINSIC	, S.ms("Eingabe-Parameter (Liste)"))
	    ,REPORTING_QUERY_PARAM_MASK(MODUL_TYP.MASK_RB	, S.ms("Eingabe-Parameter (Maske)"))
	    
	    //2018-08-28: neues Hilfemodul
        ,HILFE_V2_LIST(MODUL_TYP.INTRINSIC	, S.ms("Hilfetexte"))
	    ,HILFE_V2_MASK(MODUL_TYP.MASK_RB	, S.ms("Hilfetexte (Maske)"))

	    //2018-11-08: MRS Lagerhaltung
	    ,LAGER_BOX_LIST(MODUL_TYP.LIST				, S.ms("Lagerbox (Liste)"))
	    ,LAGER_BOX_MASK(MODUL_TYP.MASK_RB			, S.ms("Lagerbox (Maske)"))
	    ,LAGER_BOX_PALETTE_LIST(MODUL_TYP.LIST		, S.ms("Box-Palette (Liste)"))
	    ,LAGER_BOX_PALETTE_MASK(MODUL_TYP.MASK_RB	, S.ms("Box-Palette (Maske)"))
        ,LAGER_PALETTE_LIST(MODUL_TYP.LIST			, S.ms("Palette (Liste)"))
        ,LAGER_PALETTE_MASK(MODUL_TYP.MASK_RB		, S.ms("Palette (Maske)"))
        
        
        //2018-12-14: neue info-module in Adressmaske
        ,ADRESSE_INFO_EMBEDDED_LIST(MODUL_TYP.INTRINSIC	,	 S.ms("Adress-Information"))
	    ,ADRESSE_INFO_EMBEDDED_MASK(MODUL_TYP.MASK_RB	,	 S.ms("Adress-Information"))
	    ,ADRESSE_MESSAGE_EMBEDDED_LIST(MODUL_TYP.INTRINSIC	, S.ms("Adress-Meldung"))
		,ADRESSE_MESSAGE_EMBEDDED_MASK(MODUL_TYP.MASK_RB	, S.ms("Adress-Meldung"))
 
        
        //2019-18-01: Dateiname korrektur modul
		,DATEINAME_KORREKTUR(MODUL_TYP.LIST, S.ms("Dateiname korrektur"))
		
		,WF_SIMPLE_LIST (MODUL_TYP.LIST, S.ms("Vereinfachte Laufzettel-Liste"))
		,WF_SIMPLE_MASK (MODUL_TYP.MASK_RB,S.ms("Laufztettel-Eintrag"))
		
		,B_LAGER_ATOM_LIST(MODUL_TYP.LIST, S.ms("Lagerbewegungen Bewegungsatom (Liste)"))
		,B_LAGER_ATOM_MASK(MODUL_TYP.MASK,	S.ms("Lagerbewegungen Bewegungsatom (Maske)"))
		
		
		//2019-08-19: martin
        ,DIENSTLEISTUNG_PROFIL_LIST(MODUL_TYP.LIST		, S.ms("Dienstleistungsprofil (Liste)"))
	    ,DIENSTLEISTUNG_PROFIL_MASK(MODUL_TYP.MASK_RB	, S.ms("Dienstleistungsprofil (Maske)"))

	    //2019-08-22: sebastien - Lagerbestand Bewegungsatom (NEU)
	    ,B2_LAG_SALDO_LIST(MODUL_TYP.LIST, 		S.ms("Lagerbestand bewegungsatom (Liste)"))
	    ,B2_LAG_SALDO_MASK(MODUL_TYP.MASK_RB,	S.ms("Lagerbestand bewegungsatom (Maske)"))

		//2019-09-04: sebastien internal palette liste
		,LAGER_PALETTE_LIST_INTERNAL(MODUL_TYP.INTRINSIC	, S.ms("Internal Lagerpaletten"))
		
		 ,B2_LALI_LAGER_LIST(MODUL_TYP.LIST, 		S.ms("Lagerbewegungen BG (Liste)"))
		 ,B2_LALI_LAGER_MASK(MODUL_TYP.MASK_RB,		S.ms("Laberbewegungen BG (Maske)"))
		 
		 
		 //2020-01-27: report-varianten
		 ,REP_VARIANTEN_MASKE_LIST(MODUL_TYP.INTRINSIC, S.ms("Report-Varianten"))
		 ,REP_VARIANTEN_MASKE_MASK(MODUL_TYP.MASK_RB, S.ms("Report-Varianten"))
		 ,REP_VARIANTEN_PARAM_MASK_LIST(MODUL_TYP.INTRINSIC, S.ms("Report-Parameter"))
		 ,REP_VARIANTEN_PARAM_MASK_MASK(MODUL_TYP.MASK_RB, S.ms("Report-Parameter"))
		 
		 ,TL_LIST_LIST(MODUL_TYP.INTRINSIC, S.ms("Textliste (Listenmodul)"))
		 ,TL_LIST_MASK(MODUL_TYP.MASK_RB, S.ms("Textliste (Maske)"))
		 
		 // 2020-03-11:neues Wiegekarten-Modul
		 ,WK_RB_LIST(MODUL_TYP.LIST, S.ms("Wiegekarte RB (Liste)"))
		 ,WK_RB_MASK(MODUL_TYP.MASK_RB, S.ms("Wiegekarte RB (Maske)"))
		 // Erweiterung Child Abzug Gebinde
		 ,WK_RB_CHILD_LIST_ABZUG_GEB(MODUL_TYP.LIST, S.ms("WK Abzug Gebinde(Liste)"))
		 ,WK_RB_CHILD_MASK_ABZUG_GEB(MODUL_TYP.MASK_RB, S.ms("WK Abzug Gebinde (Maske)"))
		 
		 // Erweiterung Child Abzug Menge
		 ,WK_RB_CHILD_LIST_MGE_ABZ(MODUL_TYP.LIST, S.ms("WK Abzug Menge(Liste)"))
		 ,WK_RB_CHILD_MASK_MGE_ABZ(MODUL_TYP.MASK_RB, S.ms("WK Abzug Menge (Maske)"))
		 
		 //2020-03-25: Text-liste-vorlagen 
		 ,TEXT_LISTE_VORLAGE_LIST(MODUL_TYP.LIST, S.ms("Textlisten-Vorlagen"))
		 ,TEXT_LISTE_VORLAGE_MASK(MODUL_TYP.MASK_RB, S.ms("Textlisten-Vorlage"))
		 
		 //2020-06-25: erweiterung mwst-schluessel (alt)
		 ,MWSTSCHLUESSEL_OLD_LIST(MODUL_TYP.LIST, S.ms("MWST-Schlüssel (alt)"))
		 ,MWSTSCHLUESSEL_OLD_MASK(MODUL_TYP.MASK_RB, S.ms("MWST-Schlüssel (alt)"))
		 
		 ,MWSTSCHLUESSEL_OLD_AENDERUNGEN_LIST(MODUL_TYP.INTRINSIC, S.ms("MWST.Änderungen"))
		 ,MWSTSCHLUESSEL_OLD_AENDERUNGEN_MASK(MODUL_TYP.MASK_RB, S.ms("MWST.Änderungen"))
		 
		 //2020-11-05: neues steuermodul mit bereichen 
		 ,TAX_RATE_V2_LIST(MODUL_TYP.LIST, S.ms("Steuersätze"))
		 ,TAX_RATE_V2_MASK(MODUL_TYP.MASK_RB, S.ms("Steuersätze"))
		 ,TAX_AENDERUNGEN_LIST(MODUL_TYP.INTRINSIC, S.ms("MWST.Änderungen"))
		 ,TAX_AENDERUNGEN_MASK(MODUL_TYP.MASK_RB, S.ms("MWST.Änderungen"))
		 
		 
		 //2021-02-08: email-archiv-tools
		 ,EMAIL_SEND_V2_LIST(MODUL_TYP.INTRINSIC,S.ms("eMail-Archiv"))
		 ,EMAIL_SEND_V2_MASK(MODUL_TYP.MASK_RB,S.ms("eMail"))
		 
		 ,EMAIL_SEND_ATTACH_V2_LIST(MODUL_TYP.INTRINSIC,S.ms("eMail-Anlagen"))
		 ,EMAIL_SEND_ATTACH_V2_MASK(MODUL_TYP.MASK_RB,S.ms("eMail-Anlagen"))
		 
		 ,EMAIL_SEND_TARGETS_V2_LIST(MODUL_TYP.INTRINSIC,S.ms("eMail-Empfänger"))
		 ,EMAIL_SEND_TARGETS_V2_MASK(MODUL_TYP.MASK_RB,S.ms("eMail-Empfänger"))
		 
		;

		private String 						call_Key = null;
		private MyE2_String 				user_Info = null;
		private Vector<IF_enum_4_db> 		vAKTION = null;    //wenn dieser vector gefuellt wird, dann koennen hier alle buttons des moduls hinterlegt werden

		private MODUL_TYP                   modulTyp = null;

		private MODUL(MODUL_TYP p_modulTyp, String callKey, MyE2_String userInfo){
			this.call_Key	=	callKey;
			this.user_Info	= userInfo;
			this.modulTyp = p_modulTyp;
		}

		private MODUL(MODUL_TYP p_modulTyp, MyE2_String userInfo) {
			this.call_Key	=	this.name();
			this.user_Info	= userInfo;
			this.modulTyp = p_modulTyp;
		}


		private MODUL(MODUL_TYP p_modulTyp, MyE2_String userInfo, IF_enum_4_db... buttons) {
			if (buttons != null); {
				this.vAKTION = new Vector<IF_enum_4_db>();
				for (IF_enum_4_db en: buttons) {
					this.vAKTION.add(en);
				}
			}
			this.user_Info	= userInfo;
			this.modulTyp = p_modulTyp;
		}

		@Override
		public String get_callKey() {
			if (S.isEmpty(this.call_Key)) {
				return this.name();
			}
			return call_Key;
		}

		@Override
		public MyE2_String get_userInfo() {
			return user_Info;
		}
		public Vector<IF_enum_4_db> get_vAKTION() {
			return vAKTION;
		}

		public MODUL_TYP get_ModulTyp() {
			return modulTyp;
		}
		public boolean is_MASK() {
			return this.modulTyp.equals(MODUL_TYP.MASK);
		}

		public boolean is_RB_MASK() {
			return this.modulTyp.equals(MODUL_TYP.MASK_RB);
		}


		public boolean is_LIST() {
			return this.modulTyp.equals(MODUL_TYP.LIST);
		}

		
		/**
		 * 2018-08-30: neuer getter-name 
		 * @param MODULKENNER (String)
		 * @return found enum (null when no corresponding member)
		 */
		public static E2_MODULNAME_ENUM.MODUL  getModule(String MODULKENNER) {

			E2_MODULNAME_ENUM.MODUL modRueck = null;

			for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
				if (mod.get_callKey().equals(MODULKENNER)) {
					modRueck = mod;
					break;
				}
			}

			return modRueck;


		}

		

		public E2_BasicModuleContainer generate_E2_BasicModuleContainer() throws myException {
			E2_BasicModuleContainer container = null;

			//20171124: validierung von menuepunkten
			MyE2_MessageVector mv = ENUM_VALIDATION.validateMenueCall(this);
			if (mv.get_bHasAlarms()) {
				bibMSG.add_MESSAGE(mv);
				throw new myExceptionForUser(S.ms("Berechtigung nicht vorhanden !"));
			}


			switch (this) {
			case NAME_MODUL_FUHRENFUELLER: 				container=new FU_LIST_ModulContainer(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER);
			break;

			case NAME_MODUL_FAHRTENPOOL: 				container=new FU_LIST_ModulContainer(E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL); 
			break;

			case NAME_MODUL_FAHRPLAN:					container=new FU_LIST_ModulContainer(E2_MODULNAMES.NAME_MODUL_FAHRPLAN); 
			break;

			case NAME_MODUL_FAHRPLANUEBERSICHT:			container=new FPU_BasicModuleContainer(); 
			break;

			case NAME_MODUL_FBAM_LIST:					container=new BAMF_LIST_ModulContainer(); 
			break;

			case NAME_MODUL_BBAM_LIST:					container=new BAMB_LIST_ModulContainer(); 
			break;

			case NAME_MODUL_FAHRTENVARIANTEN:			container=new FV_ModulContainerList(); 
			break;

			case NAME_MODUL_FIRMENSTAMM_LIST:			container=new FS_ModulContainer_LIST(); 
			break;

			case NAME_MODUL_ABNAHMEABGEBOT_LISTENEINGABE:	container=new BSAAL__ModulContainerLIST(); 
			break;

			case NAME_MODUL_MAILARCHIV:					container=new MAR_ModuleContainer(); 
			break;

			case NAME_MODUL_MASSMAILER:					container=new MASM_ModuleContainer(); 
			break;

			case NAME_MODUL_ABNAHMEANGEBOT_LIST:		container=new BSA_K_LIST__ModulContainer(myCONST.VORGANGSART_ABNAHMEANGEBOT); 
			break;

			case NAME_MODUL_VERKAUFSANGEBOT_LIST:		container=new BSA_K_LIST__ModulContainer(myCONST.VORGANGSART_ANGEBOT); 
			break;

			case NAME_MODUL_RECHNUNGEN_LIST:			container=new BSRG_K_LIST__ModulContainer(myCONST.VORGANGSART_RECHNUNG); 
			break;

			case NAME_MODUL_GUTSCHRIFTEN_LIST:			container=new BSRG_K_LIST__ModulContainer(myCONST.VORGANGSART_GUTSCHRIFT); 
			break;

			case NAME_MODUL_ADMINTOOLS:					container=new ADM_BasicModulContainer(); 
			break;

			case NAME_MODUL_ARTIKELSTAMM_LISTE:			container=new AS_BasicModulContainerLIST(); 
			break;

			case NAME_MODUL_EK_KONTRAKT_LIST:			container=new BSK_K_LIST__ModulContainer(myCONST.VORGANGSART_EK_KONTRAKT); 
			break;

			case NAME_MODUL_VK_KONTRAKT_LIST:			container=new BSK_K_LIST__ModulContainer(myCONST.VORGANGSART_VK_KONTRAKT); 
			break;

			case NAME_MODUL_VERTRAGSCLEARING_EK:		container=new BSKC_ModulContainerLIST("EK"); 
			break;

			case NAME_MODUL_VERTRAGSCLEARING_VK:		container=new BSKC_ModulContainerLIST("VK"); 
			break;

			case NAME_MODUL_TPA_LIST:					container=new BST_K_LIST__ModulContainer(); 
			break;

			case NAME_MODUL_EAK:						container=new EAK_BasicModuleContainer(); 
			break;

			case NAME_MODUL_FREIEPOSITIONEN:			container=new BSFP_LIST_BasicModuleContainer(null); 
			break;

			case NAME_MODUL_BANKENSTAMM_LISTE:			container=new BANK_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_PROJEKT_LISTE:				container=new PROJEKT_LIST_BasicModuleContainer(); 
			break;

			case NAME_WORKFLOW_LAUFZETTEL_LISTE:		container=new WF_HEAD_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_POSITION_VORLAGEN:			container=new BS_VL_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LISTE_FUHREN_SONDERFAELLE:	container=new FU_SONDER_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAGERLISTE:					container=new LAG_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAGERLISTE_KONTO:			container=new LAG_KTO_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAGER_BEWEGUNG_LISTE:		container=new LAG_BEW_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAGER_INVENTUR_LISTE:		container=new LAG_INV_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_WIEGEKARTE_LISTE:			container=new WK_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_FIBU_LIST:					container=new FIBU_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_INTRASTAT_LISTE:			container=new INSTAT_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_NACHRICHTEN_LISTE:			container=new MESSAGE_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_TPA_KOSTEN_LISTE:			container=new TP_KST_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_ROHSTOFFAUSWERTUNGEN:		container=new AW_RohstoffAuswertungen(); 
			break;

			case NAME_MODUL_ROHSTOFFAUSWERTUNGEN2:		container=new AW2__BasicContainer_RohstoffAuswertungen(); 
			break;

			//			case NAME_MODUL_ROHSTOFFAUSWERTUNGEN_NG:		container=new AuswertungenNextGeneration_BasicModuleContainer(); 
			//				break;

			case NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE:	container=new STATKD_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAGERSTATUS_LISTE:			container=new LAG_STAT_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_MASCHINENSTAMM_LISTE:		container=new MS_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LOG_TRIGGER_DEF_LISTE:		container=new LOGTRIG__LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_UMA_LIST:					container=new UMA_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_ATOM_LIVELISTE:				container=new LA_BasicModuleContainer(); 
			break;

			case NAME_MODUL_MAIL_INBOX_LISTE:			container=new MAIL_INBOX_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_ATOM_LAGER_SALDO:			container=new ATOM_SALDO_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_WARENBEWEGUNG_NG_LIST:		container=new B_LIST_ModulContainer(); 
			break;

			case NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST:	container=new ATOM_LAG_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAENDER_LIST:				container=new LAND__LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_VERSENDE_INVENTUR_ANFORDERUNG:	container=new INF_ANFORDERUNG_INVENTUR_BasicModuleContainer(); 
			break;

			case NAME_MODUL_LAGER_ATOM_INVENTUR_LISTE:	container=new ATOM_LAG_INV_LIST_BasicModuleContainer(); 
			break;


			case NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE:		container=new ATOM_LAG_BEW_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_LISTE:		container=new ATOM_LAG_BEW_LIST_BasicModuleContainer(false); 
			break;

			case NAME_LIST_FIBU_KONTEN_REGELN:							container=new FKR_LIST_BasicModuleContainer(); 
			break;

			case NAME_FIBU_KONTEN_EXPORT:								container=new FIBU_EXPORT_ModuleContainer(); 
			break;

			case NAME_FIBU_EXPORT_PROFILES_LIST:						container=new FIBU_EXPORT_PROFILES_LIST_BasicModuleContainer(); 
			break;

			case NAME_LIST_FIBU_KONTEN_REGELN_NEU:						container=new FIBU_KONTEN_REGELN_LIST_BasicModuleContainer(); 
			break;

			case NAME_LIST_FIBU_KONTEN_REGELN_FILTERBASED:				container=new FKR_FB_LIST_BasicModuleContainer(); 
			break;

			case SPIELWIESE_LIST:										container=new SPIELWIESE_BasicModuleContainer(); 
			break;

			case BAM_IMPORT_LIST:										container=new BAM_IMPORT_LIST_BasicModuleContainer(); 
			break;


			case FUZE_ATOM_BASED_LIST: 									container=new FZ_LIST_BasicModuleContainer();
			break;

			case ADRESSKLASSE_DEF_LIST: 								container=new ADK_LIST_BasicModuleContainer();
			break;

			case MODUL_TAXRULES_LIST:									container=new TR__LIST_BasicModuleContainer(null); 
			break;

			case MODUL_TAX_LIST:										container=new TAX__LIST_BasicModuleContainer(null); 
			break;

			//2020-11-05: neues tax-modul
			case TAX_RATE_V2_LIST:										container=new TX_LIST_BasicModuleContainer(); 
			break;

			
			case SCANNER_LIST:											container=new SCAN_LIST_BasicModuleContainer(); 
			break;

			////enwicklungstest-programmgenerator				
			//			case TEST_LIST:												container=new TEST_LIST_BasicModuleContainer(); 
			//				break;
			//2016-03-16: grenzuebertritte
			case BORDERCROSSING_LIST:									container=new BOR_LIST_BasicModuleContainer(); 
			break;

			//2016-03-22: reminder
			case REMINDER_DEF_LIST:										container=new REM_LIST_BasicModuleContainer(); 
			break;

			case COMPONENT_SANDBOX_MODUL:								container = new Component_SandBox_Container();
			break;

//			case MODUL_ZOLLTARIFNUMMER_LIST:							container = new ZOL_LIST_BasicModuleContainer();
//			break;

			case ZT_NUMMER_LIST:										container = new ZT_LIST_BasicModuleContainer();
			break;

			
			case NAME_MODUL_EK_KONTRAKT_LIST_NG:						container = new KFIX_K_L__ModulContainer(NAME_MODUL_EK_KONTRAKT_LIST_NG);
			break;

			case NAME_MODUL_VK_KONTRAKT_LIST_NG:						container = new KFIX_K_L__ModulContainer(NAME_MODUL_VK_KONTRAKT_LIST_NG);
			break;	

			case DRUCKER_LIST:											container=new DRUCK_LIST_BasicModuleContainer(); 
			break;

			case NAME_MODUL_USERVERWALTUNG_LISTE:						container= new USER__LIST_BasicModuleContainer();
			break;

			case AH7_STEUERDATEI_LISTE:									container= new AH7_LIST_BasicModuleContainer();
			break;

			case AH7_PROFIL_LIST:										container= new AH7P_LIST_BasicModuleContainer();
			break;

			case TESTMODUL_MARTIN: 										container=new _Test.TESTMODUL_MARTIN();
			break;

			case TESTMODUL_MANFRED: 									container=new _Test.TESTMODUL_MANFRED();
			break;

			case TESTMODUL_SEBASTIEN: 									container=new _Test.TESTMODUL_SEBASTIEN();
			break;

			case MASK_DESIGN_LIST:										container = new MA_DES_LIST_BasicModuleContainer();
			break;

			case CONTAINER_LIST:										container = new CON_LIST_BasicModuleContainer();
			break;

			case CONTAINERTYP_LIST:										container = new CONTYP_LIST_BasicModuleContainer();
			break;

			case FUHREN_KOSTEN_TYP_LIST:								container = new FKT_LIST_BasicModuleContainer();
			break;

			case BG_LAGER_BEWEGUNG_LIST:								container = new BG_Lager_Bewegung_LIST_BasicModuleContainer();
			break;

			case BG_LAGER_SALDO_LIST:									container = new BG_Lager_Saldo_LIST_BasicModuleContainer();
			break;

			case SANKTION_PRUEFUNG_LIST:								container = new ADR_FREIGABE_LIST_BasicModuleContainer();
			break;

			case TAX_GROUP_LIST:										container = new TAXG_LIST_BasicModuleContainer(null);
			break;

			case REPORT_VERLAUF_LIST:									container = new REP_VER_LIST_BasicModuleContainer();
			break;
			
			case REPORTING_QUERY_LIST:									container = new RQ_LIST_BasicModuleContainer();
			break;
			
			
			//09.11.2018:Sebastien - MRS Lagerhaltung
			case LAGER_BOX_LIST:										container = new LH_LIST_BasicModuleContainer();
			break;

			//07.11.2018:Sebastien - MRS Lagerhaltung
			case LAGER_PALETTE_LIST:									container = new LH_P_LIST_BasicModuleContainer();
			break;

			//20181120: neues bewegungsmodul, take2
			case B_TRANSPORT_LIST:										container = new B2_ListBasicModuleContainer();
			break;

			case DATEINAME_KORREKTUR:									container = new UP_DOWN_FILENAME_Container();
			break;
			
			case WF_SIMPLE_LIST:										container = new WF_SIMPLE_LIST_BasicModuleContainer();
			break;
			
			case B_LAGER_ATOM_LIST:										container = new B2_LAG_LIST_BasicModuleContainer();
			break;
			
			//20190819: martin: dienstleistungsprofile
			case DIENSTLEISTUNG_PROFIL_LIST:							container = new DL_LIST_BasicModuleContainer();
			break;
			
			case B2_LAG_SALDO_LIST:										container = new B2_LAG_SALDO_LIST_BasicModuleContainer();
			break;
			
			case B2_LALI_LAGER_LIST:									container = new B2_LALI_LIST_BasicModuleContainer();
			break;
			
			case WK_RB_LIST:											container = new WK_RB_LIST_BasicModuleContainer();
			break;
			
			case TEXT_LISTE_VORLAGE_LIST:								 container = new TLV_LIST_BasicModuleContainer();
			break;	

			case MWSTSCHLUESSEL_OLD_LIST:								 container = new TO_LIST_BasicModuleContainer();
			break;	

			
			default:													container= new DummyBasicContainer();
			break;
			}



			return container;		
		}


	}



	
	
	/**
	 * looks for usertext to a given modulKey
	 * @param modulKey
	 * @return
	 */
	public static MyE2_String getUserText(String modulKey) {
		for (MODUL m: MODUL.values()) {
			if (m.get_callKey().equals(modulKey)) {
				return m.get_userInfo();
			}
		}
		return new MyE2_String(modulKey,false);
	}
	
	



	/**
	 * 
	 * @param MODULKENNER
	 * @return found enum (null when no corresponding member)
	 */
	public static E2_MODULNAME_ENUM.MODUL  find_Corresponding_Enum(String MODULKENNER) {

		E2_MODULNAME_ENUM.MODUL modRueck = null;

		for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
			if (mod.get_callKey().equals(MODULKENNER)) {
				modRueck = mod;
				break;
			}
		}

		return modRueck;


	}






	
	
	/**
	 * 
	 * @param MODULKENNER
	 * @return found usertext (dummytext when no corresponding member)
	 */
	public static MyE2_String  find_Corresponding_TabText(String MODULKENNER) {
		//		
		//		for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
		//			if (mod.get_callKey().equals(MODULKENNER)) {
		//				return S.NN(mod.get_userInfo(),new MyE2_String("<leer>"));
		//			}
		//		}
		//		return new MyE2_String("<leer>");

		return E2_MODULNAME_ENUM.find_Corresponding_TabText(MODULKENNER, new MyE2_String("<leer>"));
	}


	/**
	 * 
	 * @param MODULKENNER
	 * @param nullValue
	 * @return found usertext (dummytext when no corresponding member)
	 */
	public static MyE2_String  find_Corresponding_TabText(String MODULKENNER, MyE2_String nullValue) {
		for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
			if (mod.get_callKey().equals(MODULKENNER)) {
				return S.NN(mod.get_userInfo(),nullValue);
			}
		}
		return nullValue;
	}




	/**
	 * gibt eine hashmap mit den key/MyString-paaren
	 */
	public static HashMap<String, MyE2_String> get_hmModul_Kenner_And_Names() {
		HashMap<String, MyE2_String> hmRueck = new HashMap<String, MyE2_String>();

		for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
			hmRueck.put(mod.get_callKey(), mod.user_Info);
		}

		return hmRueck;
	}


	/**
	 * gibt eine hashmap mit den key/MyString-paaren
	 */
	public static LinkedHashMap<String, MyE2_String> get_hmModul_Kenner_And_Names_ONLY_LIST_MODULES() {
		LinkedHashMap<String, MyE2_String> hmRueck = new LinkedHashMap<String, MyE2_String>();

		for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
			if (mod.is_LIST()) {
				hmRueck.put(mod.get_callKey(), mod.user_Info);
			}
		}

		return hmRueck;
	}



	/**
	 * gibt eine hashmap mit den key/MyString-paaren
	 */
	public static LinkedHashMap<String, MyE2_String> get_hmModul_Kenner_And_Names_ONLY_MASK_MODULES() {
		LinkedHashMap<String, MyE2_String> hmRueck = new LinkedHashMap<String, MyE2_String>();

		for (E2_MODULNAME_ENUM.MODUL mod: E2_MODULNAME_ENUM.MODUL.values()) {
			if (mod.is_MASK()) {
				hmRueck.put(mod.get_callKey(), mod.user_Info);
			}
		}

		return hmRueck;
	}





	public static void create_DB_Button_Entrys_4_all_ModuleRanges(Vector<MyString> v4Meldungen) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();

		for (MODUL modulname: MODUL.values()) {
			for (IF_enum_4_db button: modulname.vAKTION) {
				RECLIST_BUTTON  rl_bt = new RECLIST_BUTTON(
						new SELECT("*").from(_DB.BUTTON).
						where(Term.field(_DB.BUTTON$MODULKENNER),Term.value(modulname.get_callKey())).
						and(Term.field(_DB.BUTTON$BUTTONKENNER),Term.value(button.db_val())));

				if (rl_bt.size()==0) {
					//dann reinschreiben
					RECORDNEW_BUTTON rn_bt = new RECORDNEW_BUTTON();
					rn_bt.set_NEW_VALUE_MODULKENNER(modulname.get_callKey());
					rn_bt.set_NEW_VALUE_BUTTONKENNER(button.db_val());

					rn_bt.do_WRITE_NEW_BUTTON(mv);

					if (mv.get_bHasAlarms()) {
						break;
					} else {
						if (v4Meldungen==null) {
							mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(S.t("Geschrieben:"),S.ut(modulname.get_callKey()+"/"+button.db_val()))));
						} else {
							v4Meldungen.add(new MyE2_String(S.t("Geschrieben:"),S.ut(modulname.get_callKey()+"/"+button.db_val())));
						}
					}
				}
			}

			if (mv.get_bHasAlarms()) {
				break;
			}

		}

		bibMSG.add_MESSAGE(mv);

	}


	/**
	 * 
	 * @author martin
	 * @date ??
	 *
	 * @param v_module_dazu
	 * @param emtpy_in_front
	 * @return
	 * 
	 * edit: 2016-04-25 : Liste wird sortiert nach Namen ausgegeben
	 */
	public static String[][] get_dd_moduls(Vector<MODUL_TYP> v_module_dazu, boolean emtpy_in_front) {

		if (v_module_dazu==null) {
			v_module_dazu = new Vector<E2_MODULNAME_ENUM.MODUL_TYP>();
			for (MODUL_TYP typ: MODUL_TYP.values()) {
				v_module_dazu.add(typ);
			}
		}

		//erste durchlauf um die gewuenschten zu zaehlen
		int i=0;
		if (emtpy_in_front) {i++;}
		for (MODUL mod: MODUL.values()) {
			if (v_module_dazu.contains(mod.get_ModulTyp())) {
				i++;
			}
		}

		String[][] ddarray =  new String[i][2];

		i=0;
		if (emtpy_in_front) {
			ddarray[0][0] = "-"; ddarray[0][1] = "";
			i++;
		}
		for (MODUL mod: MODUL.values()) {
			if (v_module_dazu.contains(mod.get_ModulTyp())) {
				ddarray[i][0] = mod.get_userInfo().CTrans() ; 
				ddarray[i][1] = mod.get_callKey();
				i++;
			}
		}

		Arrays.sort(ddarray,new Comparator_For_2_dimensional_Arrays(0, true));
		return ddarray;
	}


	/**
	 * die Werte im 2. Element werden mit trenner versehen, z.B. für einen Selektor
	 * Bsp: get_dd_moduls_for_selector( vector..., true, 2, "'")
	 * [-][] 
	 * [][]
	 * [][]
	 * [key1]['value1']
	 * [key2]['value2']
	 * ...
	 * 
	 * 
	 * @author manfred
	 * @date ??
	 *
	 * @param v_module_dazu
	 * @param emtpy_in_front
	 * @param additional_elements_in_front  : zusätzliche leere Felder am Anfang der Liste, falls empty_in_front == true, dann nach dem 1. Eintrag
	 * @return
	 * 
	 * edit: 2016-04-25 : Liste wird sortiert nach Namen ausgegeben
	 */
	public static String[][] get_dd_moduls_for_selector( Vector<MODUL_TYP> v_module_dazu, boolean emtpy_in_front, int additional_elements_in_front, String delimiter) {

		if (v_module_dazu==null) {
			v_module_dazu = new Vector<E2_MODULNAME_ENUM.MODUL_TYP>();
			for (MODUL_TYP typ: MODUL_TYP.values()) {
				v_module_dazu.add(typ);
			}
		}

		if (delimiter == null){
			delimiter = "";
		}

		//erste durchlauf um die gewuenschten zu zaehlen
		int iCountModuls=0;
		int i = 0;

		for (MODUL mod: MODUL.values()) {
			if (v_module_dazu.contains(mod.get_ModulTyp())) {
				iCountModuls++;
			}
		}

		// Array mit den modulen füllen und sortieren
		String[][] arrModuls =  new String[iCountModuls][2];

		for (MODUL mod: MODUL.values()) {
			if (v_module_dazu.contains(mod.get_ModulTyp())) {
				arrModuls[i][0] = mod.get_userInfo().CTrans() ; 
				arrModuls[i][1] = delimiter + mod.get_callKey() + delimiter ;
				i++;
			}
		}
		Arrays.sort(arrModuls,new Comparator_For_2_dimensional_Arrays(0, true));



		// Array komplett aufbauen /erweitern und kopieren, damit die zusätzlichen Einträge vorne sind...
		int iComplete = iCountModuls;
		if (emtpy_in_front) {iComplete++;}
		iComplete += additional_elements_in_front;


		String[][] ddarray =  new String[iComplete][2];
		i=0;
		if (emtpy_in_front) {
			ddarray[i][0] = "-"; 
			ddarray[i][1] = "";
			i++;
		}

		// die zusätzlichen Elemente überspringen
		i += additional_elements_in_front;

		for (int iOld= 0; iOld < iCountModuls; iOld ++ ){
			ddarray[iOld + i][0] = arrModuls[iOld][0];
			ddarray[iOld + i][1] = arrModuls[iOld][1];
		}

		return ddarray;
	}



	public static HashMap<String, String> get_hm_MODULNAMES() {
		HashMap<String, String> hm_rueck = new HashMap<String, String>();
		for (MODUL s: MODUL.values()) {
			hm_rueck.put(s.get_callKey(), s.user_Info.CTrans());
		}
		return hm_rueck;
	}
}
