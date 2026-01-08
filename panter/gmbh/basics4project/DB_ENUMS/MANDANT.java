package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum MANDANT implements IF_Field {


     allowed_date_diff("ALLOWED_DATE_DIFF","ALLOWED_DATE_DIFF","NUMBER",4,3,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     allow_edit_abzug_in_fuhren_rg("ALLOW_EDIT_ABZUG_IN_FUHREN_RG","ALLOW_EDIT_ABZUG_IN_FUHREN_RG","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     allow_edit_price_in_fuhren_rg("ALLOW_EDIT_PRICE_IN_FUHREN_RG","ALLOW_EDIT_PRICE_IN_FUHREN_RG","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     anr1_gleichheit_fuhre_stellen("ANR1_GLEICHHEIT_FUHRE_STELLEN","ANR1_GLEICHHEIT_FUHRE_STELLEN","NUMBER",3,2,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     anrede("ANREDE","ANREDE","NVARCHAR2",10,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     append_attachment_pdf_to_rg("APPEND_ATTACHMENT_PDF_TO_RG","APPEND_ATTACHMENT_PDF_TO_RG","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     aussen_steuer_vermerk("AUSSEN_STEUER_VERMERK","AUSSEN_STEUER_VERMERK","NVARCHAR2",200,200,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_bank("BELEGDRUCK_BANK","BELEGDRUCK_BANK","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_blz("BELEGDRUCK_BLZ","BELEGDRUCK_BLZ","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_email("BELEGDRUCK_EMAIL","BELEGDRUCK_EMAIL","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_handelsreg_nr("BELEGDRUCK_HANDELSREG_NR","BELEGDRUCK_HANDELSREG_NR","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_konto("BELEGDRUCK_KONTO","BELEGDRUCK_KONTO","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_registergericht("BELEGDRUCK_REGISTERGERICHT","BELEGDRUCK_REGISTERGERICHT","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_steuernr("BELEGDRUCK_STEUERNR","BELEGDRUCK_STEUERNR","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_telefax("BELEGDRUCK_TELEFAX","BELEGDRUCK_TELEFAX","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_telefon("BELEGDRUCK_TELEFON","BELEGDRUCK_TELEFON","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_ustid("BELEGDRUCK_USTID","BELEGDRUCK_USTID","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     belegdruck_www("BELEGDRUCK_WWW","BELEGDRUCK_WWW","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_abangeb("BUCHUNGSPREFIX_ABANGEB","BUCHUNGSPREFIX_ABANGEB","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_ekk("BUCHUNGSPREFIX_EKK","BUCHUNGSPREFIX_EKK","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_gut("BUCHUNGSPREFIX_GUT","BUCHUNGSPREFIX_GUT","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_liefangeb("BUCHUNGSPREFIX_LIEFANGEB","BUCHUNGSPREFIX_LIEFANGEB","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_rech("BUCHUNGSPREFIX_RECH","BUCHUNGSPREFIX_RECH","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_tpa("BUCHUNGSPREFIX_TPA","BUCHUNGSPREFIX_TPA","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     buchungsprefix_vkk("BUCHUNGSPREFIX_VKK","BUCHUNGSPREFIX_VKK","NVARCHAR2",5,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_backtext_blue("COLOR_BACKTEXT_BLUE","COLOR_BACKTEXT_BLUE","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_backtext_green("COLOR_BACKTEXT_GREEN","COLOR_BACKTEXT_GREEN","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_backtext_red("COLOR_BACKTEXT_RED","COLOR_BACKTEXT_RED","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_blue("COLOR_BLUE","COLOR_BLUE","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_diff("COLOR_DIFF","COLOR_DIFF","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_green("COLOR_GREEN","COLOR_GREEN","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_mask_highlight_blue("COLOR_MASK_HIGHLIGHT_BLUE","COLOR_MASK_HIGHLIGHT_BLUE","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_mask_highlight_green("COLOR_MASK_HIGHLIGHT_GREEN","COLOR_MASK_HIGHLIGHT_GREEN","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_mask_highlight_red("COLOR_MASK_HIGHLIGHT_RED","COLOR_MASK_HIGHLIGHT_RED","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_popup_titel_blue("COLOR_POPUP_TITEL_BLUE","COLOR_POPUP_TITEL_BLUE","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_popup_titel_green("COLOR_POPUP_TITEL_GREEN","COLOR_POPUP_TITEL_GREEN","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_popup_titel_red("COLOR_POPUP_TITEL_RED","COLOR_POPUP_TITEL_RED","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     color_red("COLOR_RED","COLOR_RED","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     eigene_adress_id("EIGENE_ADRESS_ID","EIGENE_ADRESS_ID","NUMBER",11,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     eu_steuer_vermerk("EU_STEUER_VERMERK","EU_STEUER_VERMERK","NVARCHAR2",200,200,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     filename_intrastat_in("FILENAME_INTRASTAT_IN","FILENAME_INTRASTAT_IN","NVARCHAR2",20,20,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     filename_intrastat_out("FILENAME_INTRASTAT_OUT","FILENAME_INTRASTAT_OUT","NVARCHAR2",20,20,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     firmenblockrechtsoben("FIRMENBLOCKRECHTSOBEN","FIRMENBLOCKRECHTSOBEN","NVARCHAR2",600,600,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     firmenblockseitenfuss("FIRMENBLOCKSEITENFUSS","FIRMENBLOCKSEITENFUSS","NVARCHAR2",600,600,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     grenze_meng_diff_abrech_proz("GRENZE_MENG_DIFF_ABRECH_PROZ","GRENZE_MENG_DIFF_ABRECH_PROZ","NUMBER",12,10,4,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     hat_abzuege("HAT_ABZUEGE","HAT_ABZUEGE","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     id_artikel_bez_dummy("ID_ARTIKEL_BEZ_DUMMY","ID_ARTIKEL_BEZ_DUMMY","NUMBER",11,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     id_land("ID_LAND","ID_LAND","NUMBER",11,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",6,5,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),true),
     id_waehrung("ID_WAEHRUNG","ID_WAEHRUNG","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     info("INFO","INFO","NVARCHAR2",1000,1000,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     karenz_zahlfrist_ab_heute("KARENZ_ZAHLFRIST_AB_HEUTE","KARENZ_ZAHLFRIST_AB_HEUTE","NUMBER",5,4,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     korr_zahldat_wochenende("KORR_ZAHLDAT_WOCHENENDE","KORR_ZAHLDAT_WOCHENENDE","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     kurzname("KURZNAME","KURZNAME","NVARCHAR2",20,20,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     landkurz("LANDKURZ","LANDKURZ","NVARCHAR2",10,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     logogroesse("LOGOGROESSE","LOGOGROESSE","NUMBER",6,5,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     logoname("LOGONAME","LOGONAME","NVARCHAR2",40,40,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     logoschrift("LOGOSCHRIFT","LOGOSCHRIFT","NVARCHAR2",20,20,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     logotext("LOGOTEXT","LOGOTEXT","NVARCHAR2",20,20,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     mailaccount("MAILACCOUNT","MAILACCOUNT","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     mailpassword("MAILPASSWORD","MAILPASSWORD","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     mailserver("MAILSERVER","MAILSERVER","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     mailusername("MAILUSERNAME","MAILUSERNAME","NVARCHAR2",50,50,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     mg_toleranz_ek_kontrakt_pos("MG_TOLERANZ_EK_KONTRAKT_POS","MG_TOLERANZ_EK_KONTRAKT_POS","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     mg_toleranz_vk_kontrakt_pos("MG_TOLERANZ_VK_KONTRAKT_POS","MG_TOLERANZ_VK_KONTRAKT_POS","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     name1("NAME1","NAME1","NVARCHAR2",30,30,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     name2("NAME2","NAME2","NVARCHAR2",30,30,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     name3("NAME3","NAME3","NVARCHAR2",30,30,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_debitor_eu_bis("NUMKREIS_DEBITOR_EU_BIS","NUMKREIS_DEBITOR_EU_BIS","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_debitor_eu_von("NUMKREIS_DEBITOR_EU_VON","NUMKREIS_DEBITOR_EU_VON","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_debitor_inland_bis("NUMKREIS_DEBITOR_INLAND_BIS","NUMKREIS_DEBITOR_INLAND_BIS","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_debitor_inland_von("NUMKREIS_DEBITOR_INLAND_VON","NUMKREIS_DEBITOR_INLAND_VON","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_debitor_nicht_eu_bis("NUMKREIS_DEBITOR_NICHT_EU_BIS","NUMKREIS_DEBITOR_NICHT_EU_BIS","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_debitor_nicht_eu_von("NUMKREIS_DEBITOR_NICHT_EU_VON","NUMKREIS_DEBITOR_NICHT_EU_VON","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_kreditor_eu_bis("NUMKREIS_KREDITOR_EU_BIS","NUMKREIS_KREDITOR_EU_BIS","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_kreditor_eu_von("NUMKREIS_KREDITOR_EU_VON","NUMKREIS_KREDITOR_EU_VON","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_kreditor_inland_bis("NUMKREIS_KREDITOR_INLAND_BIS","NUMKREIS_KREDITOR_INLAND_BIS","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_kreditor_inland_von("NUMKREIS_KREDITOR_INLAND_VON","NUMKREIS_KREDITOR_INLAND_VON","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_kreditor_nicht_eu_bis("NUMKREIS_KREDITOR_NICHT_EU_BIS","NUMKREIS_KREDITOR_NICHT_EU_BIS","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     numkreis_kreditor_nicht_eu_von("NUMKREIS_KREDITOR_NICHT_EU_VON","NUMKREIS_KREDITOR_NICHT_EU_VON","NUMBER",11,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     ort("ORT","ORT","NVARCHAR2",30,30,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     plz("PLZ","PLZ","NVARCHAR2",10,10,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     preisfind_angeb_nur_gedruckt("PREISFIND_ANGEB_NUR_GEDRUCKT","PREISFIND_ANGEB_NUR_GEDRUCKT","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     preisfind_kontr_nur_gedruckt("PREISFIND_KONTR_NUR_GEDRUCKT","PREISFIND_KONTR_NUR_GEDRUCKT","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     preisfreigabe_auto_fuhre("PREISFREIGABE_AUTO_FUHRE","PREISFREIGABE_AUTO_FUHRE","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     rechdat_ist_leistungsdatum("RECHDAT_IST_LEISTUNGSDATUM","RECHDAT_IST_LEISTUNGSDATUM","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     runden_abzugs_mengen("RUNDEN_ABZUGS_MENGEN","RUNDEN_ABZUGS_MENGEN","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     scheckdruck_bankname("SCHECKDRUCK_BANKNAME","SCHECKDRUCK_BANKNAME","NVARCHAR2",100,100,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     scheckdruck_blz("SCHECKDRUCK_BLZ","SCHECKDRUCK_BLZ","NVARCHAR2",20,20,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     scheckdruck_konto_nr("SCHECKDRUCK_KONTO_NR","SCHECKDRUCK_KONTO_NR","NVARCHAR2",30,30,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     scheckvermerk_auf_gutschrift("SCHECKVERMERK_AUF_GUTSCHRIFT","SCHECKVERMERK_AUF_GUTSCHRIFT","NVARCHAR2",1000,1000,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     show_ids_on_report_labels("SHOW_IDS_ON_REPORT_LABELS","SHOW_IDS_ON_REPORT_LABELS","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     steuerfindung_ohne_eigenorte("STEUERFINDUNG_OHNE_EIGENORTE","STEUERFINDUNG_OHNE_EIGENORTE","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     stichtag_start_fibu("STICHTAG_START_FIBU","STICHTAG_START_FIBU","DATE",7,0,0,false,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     strasse("STRASSE","STRASSE","NVARCHAR2",30,30,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     titelueberanschrift("TITELUEBERANSCHRIFT","TITELUEBERANSCHRIFT","NVARCHAR2",300,300,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     unterscheidungsnr_intrastat("UNTERSCHEIDUNGSNR_INTRASTAT","UNTERSCHEIDUNGSNR_INTRASTAT","NUMBER",11,10,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     vermerk_steuerfr_dienstleist("VERMERK_STEUERFR_DIENSTLEIST","VERMERK_STEUERFR_DIENSTLEIST","NVARCHAR2",200,200,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     vorname("VORNAME","VORNAME","NVARCHAR2",30,30,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     wasserzeichen_fontname("WASSERZEICHEN_FONTNAME","WASSERZEICHEN_FONTNAME","NVARCHAR2",100,100,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     wasserzeichen_fontsize("WASSERZEICHEN_FONTSIZE","WASSERZEICHEN_FONTSIZE","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     wasserzeichen_rotate("WASSERZEICHEN_ROTATE","WASSERZEICHEN_ROTATE","NUMBER",4,3,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     wasserzeichen_text("WASSERZEICHEN_TEXT","WASSERZEICHEN_TEXT","NVARCHAR2",100,100,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     zeige_modul_fahrplanerfassung("ZEIGE_MODUL_FAHRPLANERFASSUNG","ZEIGE_MODUL_FAHRPLANERFASSUNG","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     zeige_modul_global_reports("ZEIGE_MODUL_GLOBAL_REPORTS","ZEIGE_MODUL_GLOBAL_REPORTS","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     zeige_modul_kalender("ZEIGE_MODUL_KALENDER","ZEIGE_MODUL_KALENDER","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     zeige_modul_messages("ZEIGE_MODUL_MESSAGES","ZEIGE_MODUL_MESSAGES","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     zeige_modul_todos("ZEIGE_MODUL_TODOS","ZEIGE_MODUL_TODOS","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
     zeige_modul_workflow("ZEIGE_MODUL_WORKFLOW","ZEIGE_MODUL_WORKFLOW","NVARCHAR2",1,1,0,true,_TAB.mandant.fullTableName(),_TAB.mandant.baseTableName(),false),
;
     private String  s_fullTableName = null; 
     private String  s_baseTableName = null;  
     private boolean b_PK = false; 
     private String  s_fieldName = null; 
     private String  s_fieldLabel = null; 
     private String  s_fieldType = null; 
     private Integer i_fieldTextLENGTH = null; 
     private Integer i_fieldNumberLENGTH= null;  
     private Integer i_fieldDecimals= null; 
     private Boolean b_fieldNullableBasic= null; 

     private MANDANT( String   p_fieldName,  
                     String   p_fieldLabel, 
                     String   p_fieldType,  
                     int      p_fieldTextLENGTH, 
                     int      p_fieldNumberLENGTH,  
                     int      p_fieldDecimals, 
                     boolean  p_fieldNullableBasic,  
                     String   p_fullTableName, 
                     String   p_baseTableName,  
                     boolean  p_PK)  
     { 
         this.s_fieldName =           p_fieldName; 
         this.s_fieldLabel =          p_fieldLabel; 
         this.s_fieldType =           p_fieldType; 
         this.i_fieldTextLENGTH =     p_fieldTextLENGTH; 
         this.i_fieldNumberLENGTH =   p_fieldNumberLENGTH; 
         this.i_fieldDecimals =       p_fieldDecimals; 
         this.b_fieldNullableBasic =  p_fieldNullableBasic; 
         this.s_fullTableName =       p_fullTableName; 
         this.s_baseTableName =       p_baseTableName; 
         this.b_PK =                  p_PK; 
     } 



    public String    fullTableName()          { return s_fullTableName; } 
    public String    baseTableName()          { return s_baseTableName;} 
    public boolean   is_PK()                  { return b_PK;} 
    public String    fieldName()              { return s_fieldName;} 
    public String    fieldLabel()             { return s_fieldLabel;} 
    public String    fieldType()              { return s_fieldType;} 
    public Integer   fieldTextLength()        { return i_fieldTextLENGTH;} 
    public Integer   fieldNumberLength()      { return i_fieldNumberLENGTH; } 
    public Integer   fieldDecimals()          { return i_fieldDecimals;} 
    public Boolean   is_fieldNullableBasic()  { return b_fieldNullableBasic;}  
    /** 
     *  
     * @return fieldname without tablealias i.e. NAME1 
     */ 
     public String    fn() 		             { return s_fieldName;} 

     /** 
     * @return fieldname with tablealias i.e. <alias>.NAME1 
     */ 
     public String    fn(String alias) 		             { return alias+"."+s_fieldName;} 

     /**  
      * @return full-tablename i.e. JT_ADRESSE 
      */ 
      public String    tn() 		             { return s_fullTableName;} 

     /** 
      *  
      * @return full-tablename.fieldname i.e. JT_ADRESSE.NAME1 
      */ 
      public String    tnfn() 		         { return s_fullTableName+"."+s_fieldName;} 

    /** 
     *  
     * @return Term: Fieldname,i.e. TABLE.NAME1 
     */ 
    public Term    t()                      { return new FieldTerm(this);}  

    /** 
     *  
     * @return  Term: Fieldname with tabAlias, i.e. alias.FIELD 
     */ 
    public Term    t(String tabAlias)         {  return new FieldTerm(tabAlias,this);}  


    /** 
     *  
     * @return  Term: Fieldname with tableAlias and fieldAlias, i.e. alias.FIELD AS fieldAlias 
     */ 
    public Term    t(String tabAlias, String fieldAlias)         {return new FieldTerm(tabAlias,this,fieldAlias);}  


    public MyMetaFieldDEF generate_MetaFieldDef() { 
       return new MyMetaFieldDEF(s_fullTableName, s_fieldName, s_fieldLabel, s_fieldType, i_fieldTextLENGTH, i_fieldNumberLENGTH, i_fieldDecimals, b_fieldNullableBasic); 
    } 

 
    /**  
     *  
     * @param tableAlias (wenn fuer diese tabelle ein alias definiert wurde)  kann null sein  
     * @param fieldPrefix (wird allen feldern vorangestellt) kann null sein  
     * @return FieldTermList ...  
     */  
     public static FieldTermList gen_FieldList(String tableAlias, String fieldPrefix) { 
         FieldTermList  ftlist = new FieldTermList(); 

         for (IF_Field field: MANDANT.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.mandant.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.mandant.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JD_MANDANT 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.mandant.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JD_MANDANT AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.mandant.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JD_MANDANT AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.mandant; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JD_MANDANT AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.mandant; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: MANDANT.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
