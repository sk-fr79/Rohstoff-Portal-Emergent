package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.basics4project.DB_RECORDS.*;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;


public enum _TAB {


     zz_rohstoff_alt_leb321k("JT_ZZ_ROHSTOFF_ALT_LEB321K"),
     zz_aw_warenstroeme("JT_ZZ_AW_WARENSTROEME"),
     z_test("JT_Z_TEST"),
     zolltarifnummer_import("JT_ZOLLTARIFNUMMER_IMPORT"),
     zolltarifnummer("JT_ZOLLTARIFNUMMER"),
     zollagentur("JT_ZOLLAGENTUR"),
     zahlungsmedium("JT_ZAHLUNGSMEDIUM"),
     zahlungsbedingungen("JT_ZAHLUNGSBEDINGUNGEN"),
     wiegekarte_user_befund("JT_WIEGEKARTE_USER_BEFUND"),
     wiegekarte_mge_abz("JT_WIEGEKARTE_MGE_ABZ"),
     wiegekarte_gebinde("JT_WIEGEKARTE_GEBINDE"),
     wiegekarte_container("JT_WIEGEKARTE_CONTAINER"),
     wiegekarte_befund("JT_WIEGEKARTE_BEFUND"),
     wiegekarte_abzug_geb("JT_WIEGEKARTE_ABZUG_GEB"),
     wiegekarte("JT_WIEGEKARTE"),
     waegung("JT_WAEGUNG"),
     waage_user("JT_WAAGE_USER"),
     waage_standort("JT_WAAGE_STANDORT"),
     waage_settings("JT_WAAGE_SETTINGS"),
     waage_lager("JT_WAAGE_LAGER"),
     waage_hofschein_pruef("JT_WAAGE_HOFSCHEIN_PRUEF"),
     vpos_tpa_fuhre_vp_ext("JT_VPOS_TPA_FUHRE_VP_EXT"),
     vpos_tpa_fuhre_sonder("JT_VPOS_TPA_FUHRE_SONDER"),
     vpos_tpa_fuhre_rgvl("JT_VPOS_TPA_FUHRE_RGVL"),
     vpos_tpa_fuhre_ort_rgvl("JT_VPOS_TPA_FUHRE_ORT_RGVL"),
     vpos_tpa_fuhre_ort_abzug("JT_VPOS_TPA_FUHRE_ORT_ABZUG"),
     vpos_tpa_fuhre_ort("JT_VPOS_TPA_FUHRE_ORT"),
     vpos_tpa_fuhre_kosten("JT_VPOS_TPA_FUHRE_KOSTEN"),
     vpos_tpa_fuhre_druck_em("JT_VPOS_TPA_FUHRE_DRUCK_EM"),
     vpos_tpa_fuhre_druck("JT_VPOS_TPA_FUHRE_DRUCK"),
     vpos_tpa_fuhre_abzug_vk("JT_VPOS_TPA_FUHRE_ABZUG_VK"),
     vpos_tpa_fuhre_abzug_ek("JT_VPOS_TPA_FUHRE_ABZUG_EK"),
     vpos_tpa_fuhre("JT_VPOS_TPA_FUHRE"),
     vpos_tpa("JT_VPOS_TPA"),
     vpos_std_angebot("JT_VPOS_STD_ANGEBOT"),
     vpos_std("JT_VPOS_STD"),
     vpos_rg_vl("JT_VPOS_RG_VL"),
     vpos_rg_abzug("JT_VPOS_RG_ABZUG"),
     vpos_rg("JT_VPOS_RG"),
     vpos_kon_trakt("JT_VPOS_KON_TRAKT"),
     vpos_kon_term("JT_VPOS_KON_TERM"),
     vpos_kon_lager("JT_VPOS_KON_LAGER"),
     vpos_kon_avv("JT_VPOS_KON_AVV"),
     vpos_kon_aenderungen("JT_VPOS_KON_AENDERUNGEN"),
     vpos_kon("JT_VPOS_KON"),
     vpos_export_rg("JT_VPOS_EXPORT_RG"),
     vkopf_tpa_druck_em("JT_VKOPF_TPA_DRUCK_EM"),
     vkopf_tpa_druck("JT_VKOPF_TPA_DRUCK"),
     vkopf_tpa("JT_VKOPF_TPA"),
     vkopf_std_druck_em("JT_VKOPF_STD_DRUCK_EM"),
     vkopf_std_druck("JT_VKOPF_STD_DRUCK"),
     vkopf_std("JT_VKOPF_STD"),
     vkopf_rg_druck_em("JT_VKOPF_RG_DRUCK_EM"),
     vkopf_rg_druck("JT_VKOPF_RG_DRUCK"),
     vkopf_rg("JT_VKOPF_RG"),
     vkopf_kon_druck_em("JT_VKOPF_KON_DRUCK_EM"),
     vkopf_kon_druck("JT_VKOPF_KON_DRUCK"),
     vkopf_kon("JT_VKOPF_KON"),
     vkopf_export_rg("JT_VKOPF_EXPORT_RG"),
     verpackungsart("JT_VERPACKUNGSART"),
     verarbeitung("JT_VERARBEITUNG"),
     ustid_pruefung("JT_USTID_PRUEFUNG"),
     uma_kontrakt("JT_UMA_KONTRAKT"),
     uma_kon_artb_ruecklief("JT_UMA_KON_ARTB_RUECKLIEF"),
     uma_kon_artb_lief("JT_UMA_KON_ARTB_LIEF"),
     trigger_action_log("JT_TRIGGER_ACTION_LOG"),
     trigger_action_def("JT_TRIGGER_ACTION_DEF"),
     transportmittel("JT_TRANSPORTMITTEL"),
     todo_wichtigkeit("JT_TODO_WICHTIGKEIT"),
     todo_teilnehmer("JT_TODO_TEILNEHMER"),
     todo("JT_TODO"),
     text_liste_vorlage("JT_TEXT_LISTE_VORLAGE"),
     text_liste("JT_TEXT_LISTE"),
     textblock_kat("JT_TEXTBLOCK_KAT"),
     textblock("JT_TEXTBLOCK"),
     testtabelle_handeldef("JT_TESTTABELLE_HANDELDEF"),
     termin_user("JT_TERMIN_USER"),
     termin("JT_TERMIN"),
     temp_text("JT_TEMP_TEXT"),
     temp_imp_sorten("JT_TEMP_IMP_SORTEN"),
     temp_import_def("JT_TEMP_IMPORT_DEF"),
     tax_group("JT_TAX_GROUP"),
     tax_aenderungen("JT_TAX_AENDERUNGEN"),
     tax("JT_TAX"),
     suchvorschlag("JT_SUCHVORSCHLAG"),
     strecken_def("JT_STRECKEN_DEF"),
     status_lager("JT_STATUS_LAGER"),
     status_kunde("JT_STATUS_KUNDE"),
     sonderzeiten("JT_SONDERZEITEN"),
     sheet_def("JT_SHEET_DEF"),
     sheet_content("JT_SHEET_CONTENT"),
     sheet("JT_SHEET"),
     selector("JT_SELECTOR"),
     searchindex("JT_SEARCHINDEX"),
     searchdef("JT_SEARCHDEF"),
     schlagwort("JT_SCHLAGWORT"),
     scanner_user("JT_SCANNER_USER"),
     scanner_settings("JT_SCANNER_SETTINGS"),
     scanner_group_2_setting("JT_SCANNER_GROUP_2_SETTING"),
     scanner_group("JT_SCANNER_GROUP"),
     sanktion_pruefung_pos("JT_SANKTION_PRUEFUNG_POS"),
     sanktion_pruefung("JT_SANKTION_PRUEFUNG"),
     rep_varianten_param("JT_REP_VARIANTEN_PARAM"),
     rep_varianten("JT_REP_VARIANTEN"),
     report_setting("JT_REPORT_SETTING"),
     report_reiter("JT_REPORT_REITER"),
     report_preparing("JT_REPORT_PREPARING"),
     report_pp_pos_user_incl("JT_REPORT_PP_POS_USER_INCL"),
     report_pp_pos_user_excl("JT_REPORT_PP_POS_USER_EXCL"),
     report_pipeline_pos("JT_REPORT_PIPELINE_POS"),
     report_pipeline("JT_REPORT_PIPELINE"),
     report_parameter("JT_REPORT_PARAMETER"),
     report_log_param("JT_REPORT_LOG_PARAM"),
     report_log("JT_REPORT_LOG"),
     reporting_query_param("JT_REPORTING_QUERY_PARAM"),
     reporting_query_field("JT_REPORTING_QUERY_FIELD"),
     reporting_query("JT_REPORTING_QUERY"),
     report("JT_REPORT"),
     reminder_user("JT_REMINDER_USER"),
     reminder_log("JT_REMINDER_LOG"),
     reminder_def("JT_REMINDER_DEF"),
     rechtsform("JT_RECHTSFORM"),
     query_teilnehmer("JT_QUERY_TEILNEHMER"),
     query("JT_QUERY"),
     qualifier("JT_QUALIFIER"),
     protokolle_batch("JT_PROTOKOLLE_BATCH"),
     pro_mitarb_typ("JT_PRO_MITARB_TYP"),
     pro_mitarb("JT_PRO_MITARB"),
     projektstatusvariante("JT_PROJEKTSTATUSVARIANTE"),
     projektinfo("JT_PROJEKTINFO"),
     projektgewicht("JT_PROJEKTGEWICHT"),
     projekt("JT_PROJEKT"),
     proforma_rechnung("JT_PROFORMA_RECHNUNG"),
     profil_grenzubertritt("JT_PROFIL_GRENZUBERTRITT"),
     pro_adressen("JT_PRO_ADRESSEN"),
     preisinfo_temp("JT_PREISINFO_TEMP"),
     plz_bundesland("JT_PLZ_BUNDESLAND"),
     offene_adr_trans_pap("JT_OFFENE_ADR_TRANS_PAP"),
     oecd_code("JT_OECD_CODE"),
     nullpreis("JT_NULLPREIS"),
     nachricht_mail_settings("JT_NACHRICHT_MAIL_SETTINGS"),
     nachricht_mail_log("JT_NACHRICHT_MAIL_LOG"),
     nachricht_kategorie("JT_NACHRICHT_KATEGORIE"),
     nachricht_kat_default("JT_NACHRICHT_KAT_DEFAULT"),
     nachricht("JT_NACHRICHT"),
     m2n("JT_M2N"),
     mwstschluessel_aenderungen("JT_MWSTSCHLUESSEL_AENDERUNGEN"),
     mwstschluessel("JT_MWSTSCHLUESSEL"),
     modul_connect("JT_MODUL_CONNECT"),
     mitarbeitertyp("JT_MITARBEITERTYP"),
     mitarbeiter("JT_MITARBEITER"),
     message_provider("JT_MESSAGE_PROVIDER"),
     medientyp("JT_MEDIENTYP"),
     medien("JT_MEDIEN"),
     mat_spez("JT_MAT_SPEZ"),
     mat_element("JT_MAT_ELEMENT"),
     maschinentyp("JT_MASCHINENTYP"),
     maschinen_kosten("JT_MASCHINEN_KOSTEN"),
     maschinen_aufgabe_typ("JT_MASCHINEN_AUFGABE_TYP"),
     maschinen_aufgabe("JT_MASCHINEN_AUFGABE"),
     maschinen("JT_MASCHINEN"),
     mandant_decision("JT_MANDANT_DECISION"),
     mandant_config("JT_MANDANT_CONFIG"),
     mailing("JT_MAILING"),
     mail_aus_mask_jasper("JT_MAIL_AUS_MASK_JASPER"),
     mail_aus_mask_email("JT_MAIL_AUS_MASK_EMAIL"),
     mail_aus_mask("JT_MAIL_AUS_MASK"),
     mahnung_pos("JT_MAHNUNG_POS"),
     mahnung("JT_MAHNUNG"),
     lock("JT_LOCK"),
     listen_zusatzfelder("JT_LISTEN_ZUSATZFELDER"),
     lieferbed_kosten("JT_LIEFERBED_KOSTEN"),
     lieferbedingungen("JT_LIEFERBEDINGUNGEN"),
     lieferadresse("JT_LIEFERADRESSE"),
     laufzettel_teilnehmer("JT_LAUFZETTEL_TEILNEHMER"),
     laufzettel_status("JT_LAUFZETTEL_STATUS"),
     laufzettel_prio("JT_LAUFZETTEL_PRIO"),
     laufzettel_eintrag("JT_LAUFZETTEL_EINTRAG"),
     laufzettel("JT_LAUFZETTEL"),
     land_rc_sorten("JT_LAND_RC_SORTEN"),
     lagerplatz_typ("JT_LAGERPLATZ_TYP"),
     lagerplatz("JT_LAGERPLATZ"),
     lager_palette_user("JT_LAGER_PALETTE_USER"),
     lager_palette_box("JT_LAGER_PALETTE_BOX"),
     lager_palette("JT_LAGER_PALETTE"),
     lager_korr_st("JT_LAGER_KORR_ST"),
     lager_konto("JT_LAGER_KONTO"),
     lager_inventur("JT_LAGER_INVENTUR"),
     lager_box("JT_LAGER_BOX"),
     lager_bewegung("JT_LAGER_BEWEGUNG"),
     kunde_mwst("JT_KUNDE_MWST"),
     kred_num_ausschluss("JT_KRED_NUM_AUSSCHLUSS"),
     kreditvers_pos("JT_KREDITVERS_POS"),
     kreditvers_kopf("JT_KREDITVERS_KOPF"),
     kreditvers_adresse("JT_KREDITVERS_ADRESSE"),
     kreditlimit_bedingung("JT_KREDITLIMIT_BEDINGUNG"),
     kostentyp("JT_KOSTENTYP"),
     kosten_lieferbed_def("JT_KOSTEN_LIEFERBED_DEF"),
     kosten_lieferbed_adr("JT_KOSTEN_LIEFERBED_ADR"),
     kosten_artbez_lief("JT_KOSTEN_ARTBEZ_LIEF"),
     korre_abzuege("JT_KORRE_ABZUEGE"),
     konto("JT_KONTO"),
     kommunikations_typ("JT_KOMMUNIKATIONS_TYP"),
     kommunikation("JT_KOMMUNIKATION"),
     kamera_snapshot_grp("JT_KAMERA_SNAPSHOT_GRP"),
     kamera_snapshot_entry("JT_KAMERA_SNAPSHOT_ENTRY"),
     kamera_settings("JT_KAMERA_SETTINGS"),
     jasperreport_value("JT_JASPERREPORT_VALUE"),
     jasper_def("JT_JASPER_DEF"),
     jasper_compile_chain("JT_JASPER_COMPILE_CHAIN"),
     intrastat_verkehrszweig("JT_INTRASTAT_VERKEHRSZWEIG"),
     intrastat_ursprung_reg("JT_INTRASTAT_URSPRUNG_REG"),
     intrastat_negativliste("JT_INTRASTAT_NEGATIVLISTE"),
     intrastat_meldung("JT_INTRASTAT_MELDUNG"),
     intrastat_land_finanzamt("JT_INTRASTAT_LAND_FINANZAMT"),
     intrastat_land("JT_INTRASTAT_LAND"),
     intrastat_kosten("JT_INTRASTAT_KOSTEN"),
     intrastat_geschaeftsart("JT_INTRASTAT_GESCHAEFTSART"),
     intrastat_bestimm_region("JT_INTRASTAT_BESTIMM_REGION"),
     internet_bookmark("JT_INTERNET_BOOKMARK"),
     internet("JT_INTERNET"),
     info_typ("JT_INFO_TYP"),
     info_card_x_user("JT_INFO_CARD_X_USER"),
     info_card_x_typ("JT_INFO_CARD_X_TYP"),
     info_card_x_thema("JT_INFO_CARD_X_THEMA"),
     info_card("JT_INFO_CARD"),
     hilfetext_zu_modul("JT_HILFETEXT_ZU_MODUL"),
     hilfetext("JT_HILFETEXT"),
     help_screen_part("JT_HELP_SCREEN_PART"),
     help_screen("JT_HELP_SCREEN"),
     handelsdef("JT_HANDELSDEF"),
     groovyscript("JT_GROOVYSCRIPT"),
     fuhre_sonder_vl("JT_FUHRE_SONDER_VL"),
     fuhren_rechnungen("JT_FUHREN_RECHNUNGEN"),
     fuhren_kosten_typ("JT_FUHREN_KOSTEN_TYP"),
     fuhren_co2_profil("JT_FUHREN_CO2_PROFIL"),
     fp_pos_akte_grund("JT_FP_POS_AKTE_GRUND"),
     fp_pos_akte("JT_FP_POS_AKTE"),
     firmeninfo("JT_FIRMENINFO"),
     filter_or("JT_FILTER_OR"),
     filter_and("JT_FILTER_AND"),
     filter("JT_FILTER"),
     field_rule_modulekenner("JT_FIELD_RULE_MODULEKENNER"),
     field_rule("JT_FIELD_RULE"),
     fibu_vpos_export("JT_FIBU_VPOS_EXPORT"),
     fibu_vkopf_export("JT_FIBU_VKOPF_EXPORT"),
     fibu_verrech_waehrung("JT_FIBU_VERRECH_WAEHRUNG"),
     fibu_vblock_export("JT_FIBU_VBLOCK_EXPORT"),
     fibu_sachbearbeiter("JT_FIBU_SACHBEARBEITER"),
     fibu_mahnung("JT_FIBU_MAHNUNG"),
     fibu_konto("JT_FIBU_KONTO"),
     fibu_kontenregel_neu("JT_FIBU_KONTENREGEL_NEU"),
     fibu_kontenregel_land("JT_FIBU_KONTENREGEL_LAND"),
     fibu_kontenregel("JT_FIBU_KONTENREGEL"),
     fibu_konstante("JT_FIBU_KONSTANTE"),
     fibu_formular("JT_FIBU_FORMULAR"),
     fibu("JT_FIBU"),
     fbam_user("JT_FBAM_USER"),
     fbam_infoblock("JT_FBAM_INFOBLOCK"),
     fbam_grund("JT_FBAM_GRUND"),
     fbam_feststellung("JT_FBAM_FESTSTELLUNG"),
     fbam_druck_em("JT_FBAM_DRUCK_EM"),
     fbam_druck("JT_FBAM_DRUCK"),
     fbam_betreff("JT_FBAM_BETREFF"),
     fbam("JT_FBAM"),
     fahrtenvarianten("JT_FAHRTENVARIANTEN"),
     fahrplan_zeitangabe("JT_FAHRPLAN_ZEITANGABE"),
     fahrplanpos("JT_FAHRPLANPOS"),
     export_log("JT_EXPORT_LOG"),
     email_send_targets("JT_EMAIL_SEND_TARGETS"),
     email_send_schablone("JT_EMAIL_SEND_SCHABLONE"),
     email_send_attach("JT_EMAIL_SEND_ATTACH"),
     email_send("JT_EMAIL_SEND"),
     email_protokoll("JT_EMAIL_PROTOKOLL"),
     email_inbox_def("JT_EMAIL_INBOX_DEF"),
     email_inbox("JT_EMAIL_INBOX"),
     email_block("JT_EMAIL_BLOCK"),
     email("JT_EMAIL"),
     element("JT_ELEMENT"),
     ek_vk_bezug("JT_EK_VK_BEZUG"),
     einheit_faktor("JT_EINHEIT_FAKTOR"),
     einheiten_kombinationen("JT_EINHEITEN_KOMBINATIONEN"),
     einheit("JT_EINHEIT"),
     eak_gruppe_sp("JT_EAK_GRUPPE_SP"),
     eak_gruppe("JT_EAK_GRUPPE"),
     eak_code_sp("JT_EAK_CODE_SP"),
     eak_code("JT_EAK_CODE"),
     eak_branche_sp("JT_EAK_BRANCHE_SP"),
     eak_branche("JT_EAK_BRANCHE"),
     drucker_zuordnung("JT_DRUCKER_ZUORDNUNG"),
     drucker("JT_DRUCKER"),
     dlp_profil("JT_DLP_PROFIL"),
     dlp_join_warenbewg("JT_DLP_JOIN_WARENBEWG"),
     dieselpreis("JT_DIESELPREIS"),
     def_sonderzeiten("JT_DEF_SONDERZEITEN"),
     deb_num_ausschluss("JT_DEB_NUM_AUSSCHLUSS"),
     datev_profile("JT_DATEV_PROFILE"),
     container_zyklus("JT_CONTAINER_ZYKLUS"),
     containertyp("JT_CONTAINERTYP"),
     container_station("JT_CONTAINER_STATION"),
     container_sorten_ug("JT_CONTAINER_SORTEN_UG"),
     container_sorten_hg("JT_CONTAINER_SORTEN_HG"),
     container_sorten("JT_CONTAINER_SORTEN"),
     container_absetz_grund("JT_CONTAINER_ABSETZ_GRUND"),
     container("JT_CONTAINER"),
     columns_to_calc("JT_COLUMNS_TO_CALC"),
     collections("JT_COLLECTIONS"),
     collection_def("JT_COLLECTION_DEF"),
     changelog("JT_CHANGELOG"),
     bundesland("JT_BUNDESLAND"),
     branche("JT_BRANCHE"),
     bordercrossing_userinfo("JT_BORDERCROSSING_USERINFO"),
     bordercrossing_artikel("JT_BORDERCROSSING_ARTIKEL"),
     bordercrossing("JT_BORDERCROSSING"),
     bg_vektor_kosten("JT_BG_VEKTOR_KOSTEN"),
     bg_vektor_konvert("JT_BG_VEKTOR_KONVERT"),
     bg_vektor("JT_BG_VEKTOR"),
     bg_storno_info("JT_BG_STORNO_INFO"),
     bg_station("JT_BG_STATION"),
     bg_rech_block("JT_BG_RECH_BLOCK"),
     bg_pruefprot("JT_BG_PRUEFPROT"),
     bg_fahrplan_to_vektor("JT_BG_FAHRPLAN_TO_VEKTOR"),
     bg_fahrplan("JT_BG_FAHRPLAN"),
     bg_del_info("JT_BG_DEL_INFO"),
     bg_beweg_to_vektor("JT_BG_BEWEG_TO_VEKTOR"),
     bg_beweg("JT_BG_BEWEG"),
     bg_auswert("JT_BG_AUSWERT"),
     bg_atom("JT_BG_ATOM"),
     beziehungsdef("JT_BEZIEHUNGSDEF"),
     beziehung("JT_BEZIEHUNG"),
     bewegung_vektor_pos("JT_BEWEGUNG_VEKTOR_POS"),
     bewegung_vektor_pn("JT_BEWEGUNG_VEKTOR_PN"),
     bewegung_vektor_log("JT_BEWEGUNG_VEKTOR_LOG"),
     bewegung_vektor("JT_BEWEGUNG_VEKTOR"),
     bewegung_trig("JT_BEWEGUNG_TRIG"),
     bewegung_station("JT_BEWEGUNG_STATION"),
     bewegung_setzkasten_k("JT_BEWEGUNG_SETZKASTEN_K"),
     bewegung_setzkasten("JT_BEWEGUNG_SETZKASTEN"),
     bewegung_menge("JT_BEWEGUNG_MENGE"),
     bewegung_atom_verbucht_k("JT_BEWEGUNG_ATOM_VERBUCHT_K"),
     bewegung_atom_verbucht("JT_BEWEGUNG_ATOM_VERBUCHT"),
     bewegung_atom_kosten("JT_BEWEGUNG_ATOM_KOSTEN"),
     bewegung_atom_abzug("JT_BEWEGUNG_ATOM_ABZUG"),
     bewegung_atom("JT_BEWEGUNG_ATOM"),
     bewegung("JT_BEWEGUNG"),
     besuchsergebnis("JT_BESUCHSERGEBNIS"),
     bestellkopf("JT_BESTELLKOPF"),
     batch_user("JT_BATCH_USER"),
     basel_code("JT_BASEL_CODE"),
     bankenstamm("JT_BANKENSTAMM"),
     bam_import_info("JT_BAM_IMPORT_INFO"),
     bam_import("JT_BAM_IMPORT"),
     artikel_umwandlung("JT_ARTIKEL_UMWANDLUNG"),
     artikel_gruppe_fibu("JT_ARTIKEL_GRUPPE_FIBU"),
     artikel_gruppe("JT_ARTIKEL_GRUPPE"),
     artikel_datenblatt("JT_ARTIKEL_DATENBLATT"),
     artikel_bez_mwst("JT_ARTIKEL_BEZ_MWST"),
     artikelbez_lief("JT_ARTIKELBEZ_LIEF"),
     artikel_bez("JT_ARTIKEL_BEZ"),
     artikel_bereich("JT_ARTIKEL_BEREICH"),
     artikel("JT_ARTIKEL"),
     artbez_verunreinigung("JT_ARTBEZ_VERUNREINIGUNG"),
     artbez_mech_zustand("JT_ARTBEZ_MECH_ZUSTAND"),
     archivmedien("JT_ARCHIVMEDIEN"),
     anrede("JT_ANREDE"),
     aktionsanlass("JT_AKTIONSANLASS"),
     ah7_steuerdatei("JT_AH7_STEUERDATEI"),
     ah7_profil("JT_AH7_PROFIL"),
     adress_zusatzwerte("JT_ADRESS_ZUSATZWERTE"),
     adress_zusatzfeld("JT_ADRESS_ZUSATZFELD"),
     adressklasse_def("JT_ADRESSKLASSE_DEF"),
     adressklasse("JT_ADRESSKLASSE"),
     adresse_zusatzbranche("JT_ADRESSE_ZUSATZBRANCHE"),
     adresse_waehrung("JT_ADRESSE_WAEHRUNG"),
     adresse_ust_id("JT_ADRESSE_UST_ID"),
     adresse_potential("JT_ADRESSE_POTENTIAL"),
     adresse_merkmal5("JT_ADRESSE_MERKMAL5"),
     adresse_merkmal4("JT_ADRESSE_MERKMAL4"),
     adresse_merkmal3("JT_ADRESSE_MERKMAL3"),
     adresse_merkmal2("JT_ADRESSE_MERKMAL2"),
     adresse_merkmal1("JT_ADRESSE_MERKMAL1"),
     adresse_info("JT_ADRESSE_INFO"),
     adresse_geschenk("JT_ADRESSE_GESCHENK"),
     adresse_fahrzeuge("JT_ADRESSE_FAHRZEUGE"),
     adresse_eu_vertr_form("JT_ADRESSE_EU_VERTR_FORM"),
     adresse_eak_code("JT_ADRESSE_EAK_CODE"),
     adresse_artikel("JT_ADRESSE_ARTIKEL"),
     adresse_aqu_rel_wbw_kd("JT_ADRESSE_AQU_REL_WBW_KD"),
     adresse_aqu_rel_kd_sorte("JT_ADRESSE_AQU_REL_KD_SORTE"),
     adresse_aquise_akte("JT_ADRESSE_AQUISE_AKTE"),
     adresse_aquise("JT_ADRESSE_AQUISE"),
     adresse("JT_ADRESSE"),
     adressausstatt_def("JT_ADRESSAUSSTATT_DEF"),
     adressausstatt("JT_ADRESSAUSSTATT"),
     adr_containertyp("JT_ADR_CONTAINERTYP"),
     abzugsschablonen("JT_ABZUGSSCHABLONEN"),
     abzugsgrund("JT_ABZUGSGRUND"),
     abrechblatt_pos("JT_ABRECHBLATT_POS"),
     abrechblatt_artbez_out("JT_ABRECHBLATT_ARTBEZ_OUT"),
     abrechblatt_artbez_in("JT_ABRECHBLATT_ARTBEZ_IN"),
     abrechblatt("JT_ABRECHBLATT"),
     zugriff("JD_ZUGRIFF"),
     waehrungsquery("JD_WAEHRUNGSQUERY"),
     waehrung("JD_WAEHRUNG"),
     version("JD_VERSION"),
     usersettings_jsonbig("JD_USERSETTINGS_JSONBIG"),
     usersettings_json("JD_USERSETTINGS_JSON"),
     usersettings("JD_USERSETTINGS"),
     usergroup("JD_USERGROUP"),
     user_applet("JD_USER_APPLET"),
     user("JD_USER"),
     trigger_def("JD_TRIGGER_DEF"),
     textuebersetzung("JD_TEXTUEBERSETZUNG"),
     textkonstante("JD_TEXTKONSTANTE"),
     tabellenmigration("JD_TABELLENMIGRATION"),
     sprache("JD_SPRACHE"),
     speed_index("JD_SPEED_INDEX"),
     servlets("JD_SERVLETS"),
     reportaktion("JD_REPORTAKTION"),
     registry("JD_REGISTRY"),
     negativlist("JD_NEGATIVLIST"),
     mask_def_cell("JD_MASK_DEF_CELL"),
     mask_def("JD_MASK_DEF"),
     mandant_zusatz_feldnamen("JD_MANDANT_ZUSATZ_FELDNAMEN"),
     mandant_zusatz("JD_MANDANT_ZUSATZ"),
     mandant_steuervermerk("JD_MANDANT_STEUERVERMERK"),
     mandant("JD_MANDANT"),
     login("JD_LOGIN"),
     land("JD_LAND"),
     jasperreport_key("JD_JASPERREPORT_KEY"),
     hauptmenue("JD_HAUPTMENUE"),
     enum_vektor_pos_typ("JD_ENUM_VEKTOR_POS_TYP"),
     enum_bewegung_typ("JD_ENUM_BEWEGUNG_TYP"),
     db_log("JD_DB_LOG"),
     datum("JD_DATUM"),
     button_user("JD_BUTTON_USER"),
     button("JD_BUTTON"),
     batch_task("JD_BATCH_TASK"),
     batch_log("JD_BATCH_LOG"),
     applet("JD_APPLET"),
    ;

    private String     fullTableName = null;

    _TAB(String full_tableName) {
       this.fullTableName = full_tableName;
    }

    ;
    /**  
     *   
     * @return base-Table-name i.e. "ADRESSE"  
     */  
    public String baseTableName() {
       return this.fullTableName.substring(3);
    }

    ;
    /**  
     *   
     * @return key-field-name i.e. "ID_ADRESSE"  
     */  
    public String keyFieldName() {
       return "ID_"+this.fullTableName.substring(3);
    }

    ;
    public IF_Field  key() throws myException {
      IF_Field field = null;
      for (IF_Field f: this.get_fields()) {
        if (f.fn().toUpperCase().equals(this.keyFieldName().toUpperCase())) {
          field = f;
          break;
        }
      }
      return field;
      }
    

    ;
    /**  
     *   
     * @return full-table-name i.e. "JT_ADRESSE"  
     */  
    public String fullTableName() {
       return this.fullTableName;
    }

    /**  
     *   
     * @return full-table-name i.e. "JT_ADRESSE"  
     */  
    public String n() {
       return this.fullTableName;
    }

 
    /**  
     *   
     * @return trigger-name i.e. "TRIGG_ADRESSE"  
     */  
    public String triggerName() {
       return "TRIGG_"+this.baseTableName();
    }

 
    public RB_KM rb_km() throws myException { 
	    return new RB_KM(this); 
    } 
 
 
    public RB_KM rb_km(int iCount) throws myException { 
	    return new RB_KM(this,this.n()+"/"+iCount); 
    } 
 
 
    /**  
     *   
     * @return i.e. "SEQ_ADRESSE"  
     */  
    public String seq_name() {  
    	return "SEQ_"+this.baseTableName();  
    }  


    /**  
     *   
     * @return i.e. "SEQ_ADRESSE.NEXTVAL"  
     */  
    public String seq_nextval() {  
    	return "SEQ_"+this.baseTableName()+".NEXTVAL";  
    }  


    /**  
     *   
     * @return i.e. "SEQ_ADRESSE.CURRVAL"  
     */  
   public String seq_currval() {  
    	return "SEQ_"+this.baseTableName()+".CURRVAL";  
    }  


   /**  
    *   
    * @return i.e. "SELECT SEQ_ADRESSE.NEXTVAL FROM DUAL"  
    */  
   public String sql_nextval() {  
    	return "SELECT " +this.seq_nextval()+" FROM DUAL";  
    }  


   /**  
    *   
    * @return i.e. "SELECT SEQ_ADRESSE.CURRVAL FROM DUAL"  
    */  
    public String sql_currval() {  
    	return "SELECT " +this.seq_currval()+" FROM DUAL";  
    }  


    public static _TAB find_Table(String fullTableName)  throws myException {
       for (_TAB tab: _TAB.values()) {
          if (tab.fullTableName.equals(fullTableName)) {
             return tab;
          }
       }
       throw new myException("_TAB.find_Table(): Tablename: "+fullTableName+" was not found !");
    }


    public static _TAB findTable(String fullTableName) {
       for (_TAB tab: _TAB.values()) {
          if (tab.fullTableName.equals(fullTableName)) {
             return tab;
          }
       }
       return null;
    }


    /**  
     *   
     * @return currval of table-sequencer (on error null)  
     */  
    public String getCurrVal() {  
        String query = this.sql_currval();  
        String val = bibDB.EinzelAbfrage(query);  
        if (new MyLong(val).isOK()) {  
            return val;  
        }  
        return null;  
     }  


    /**  
     *   
     * @return nextval of table-sequencer (on error null)  
     */  
    public String getNextVal() {  
        String query = this.sql_nextval();  
        String val = bibDB.EinzelAbfrage(query);  
            if (new MyLong(val).isOK()) {  
                return val;  
            }  
        return null;  
    }  


    public static _TAB find_TableFromBasename(String baseTableName)  throws myException {
       for (_TAB tab: _TAB.values()) {
          if (tab.baseTableName().equals(baseTableName)) {
             return tab;
          }
       }
       throw new myException("_TAB.find_Table(): Base-Tablename: "+baseTableName+" was not found !");
    }


    public static IF_Field find_field(_TAB tab, String fieldName) throws myException {
        IF_Field[] fields = tab.get_fields();

        for (IF_Field f: fields) {
            if (f.fieldName().equalsIgnoreCase(fieldName)) {
              return f;
            }
        }

        return null;
    }


    public IF_Field[] get_fields() throws myException {
       if (this.fullTableName.equals(_TAB.zz_rohstoff_alt_leb321k.fullTableName)) { return ZZ_ROHSTOFF_ALT_LEB321K.values(); }; 
       if (this.fullTableName.equals(_TAB.zz_aw_warenstroeme.fullTableName)) { return ZZ_AW_WARENSTROEME.values(); }; 
       if (this.fullTableName.equals(_TAB.z_test.fullTableName)) { return Z_TEST.values(); }; 
       if (this.fullTableName.equals(_TAB.zolltarifnummer_import.fullTableName)) { return ZOLLTARIFNUMMER_IMPORT.values(); }; 
       if (this.fullTableName.equals(_TAB.zolltarifnummer.fullTableName)) { return ZOLLTARIFNUMMER.values(); }; 
       if (this.fullTableName.equals(_TAB.zollagentur.fullTableName)) { return ZOLLAGENTUR.values(); }; 
       if (this.fullTableName.equals(_TAB.zahlungsmedium.fullTableName)) { return ZAHLUNGSMEDIUM.values(); }; 
       if (this.fullTableName.equals(_TAB.zahlungsbedingungen.fullTableName)) { return ZAHLUNGSBEDINGUNGEN.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte_user_befund.fullTableName)) { return WIEGEKARTE_USER_BEFUND.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte_mge_abz.fullTableName)) { return WIEGEKARTE_MGE_ABZ.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte_gebinde.fullTableName)) { return WIEGEKARTE_GEBINDE.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte_container.fullTableName)) { return WIEGEKARTE_CONTAINER.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte_befund.fullTableName)) { return WIEGEKARTE_BEFUND.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte_abzug_geb.fullTableName)) { return WIEGEKARTE_ABZUG_GEB.values(); }; 
       if (this.fullTableName.equals(_TAB.wiegekarte.fullTableName)) { return WIEGEKARTE.values(); }; 
       if (this.fullTableName.equals(_TAB.waegung.fullTableName)) { return WAEGUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.waage_user.fullTableName)) { return WAAGE_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.waage_standort.fullTableName)) { return WAAGE_STANDORT.values(); }; 
       if (this.fullTableName.equals(_TAB.waage_settings.fullTableName)) { return WAAGE_SETTINGS.values(); }; 
       if (this.fullTableName.equals(_TAB.waage_lager.fullTableName)) { return WAAGE_LAGER.values(); }; 
       if (this.fullTableName.equals(_TAB.waage_hofschein_pruef.fullTableName)) { return WAAGE_HOFSCHEIN_PRUEF.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_vp_ext.fullTableName)) { return VPOS_TPA_FUHRE_VP_EXT.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_sonder.fullTableName)) { return VPOS_TPA_FUHRE_SONDER.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_rgvl.fullTableName)) { return VPOS_TPA_FUHRE_RGVL.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort_rgvl.fullTableName)) { return VPOS_TPA_FUHRE_ORT_RGVL.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName)) { return VPOS_TPA_FUHRE_ORT_ABZUG.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort.fullTableName)) { return VPOS_TPA_FUHRE_ORT.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_kosten.fullTableName)) { return VPOS_TPA_FUHRE_KOSTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_druck_em.fullTableName)) { return VPOS_TPA_FUHRE_DRUCK_EM.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_druck.fullTableName)) { return VPOS_TPA_FUHRE_DRUCK.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_abzug_vk.fullTableName)) { return VPOS_TPA_FUHRE_ABZUG_VK.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_abzug_ek.fullTableName)) { return VPOS_TPA_FUHRE_ABZUG_EK.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre.fullTableName)) { return VPOS_TPA_FUHRE.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_tpa.fullTableName)) { return VPOS_TPA.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_std_angebot.fullTableName)) { return VPOS_STD_ANGEBOT.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_std.fullTableName)) { return VPOS_STD.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_rg_vl.fullTableName)) { return VPOS_RG_VL.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_rg_abzug.fullTableName)) { return VPOS_RG_ABZUG.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_rg.fullTableName)) { return VPOS_RG.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_kon_trakt.fullTableName)) { return VPOS_KON_TRAKT.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_kon_term.fullTableName)) { return VPOS_KON_TERM.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_kon_lager.fullTableName)) { return VPOS_KON_LAGER.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_kon_avv.fullTableName)) { return VPOS_KON_AVV.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_kon_aenderungen.fullTableName)) { return VPOS_KON_AENDERUNGEN.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_kon.fullTableName)) { return VPOS_KON.values(); }; 
       if (this.fullTableName.equals(_TAB.vpos_export_rg.fullTableName)) { return VPOS_EXPORT_RG.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_tpa_druck_em.fullTableName)) { return VKOPF_TPA_DRUCK_EM.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_tpa_druck.fullTableName)) { return VKOPF_TPA_DRUCK.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_tpa.fullTableName)) { return VKOPF_TPA.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_std_druck_em.fullTableName)) { return VKOPF_STD_DRUCK_EM.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_std_druck.fullTableName)) { return VKOPF_STD_DRUCK.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_std.fullTableName)) { return VKOPF_STD.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_rg_druck_em.fullTableName)) { return VKOPF_RG_DRUCK_EM.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_rg_druck.fullTableName)) { return VKOPF_RG_DRUCK.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_rg.fullTableName)) { return VKOPF_RG.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_kon_druck_em.fullTableName)) { return VKOPF_KON_DRUCK_EM.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_kon_druck.fullTableName)) { return VKOPF_KON_DRUCK.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_kon.fullTableName)) { return VKOPF_KON.values(); }; 
       if (this.fullTableName.equals(_TAB.vkopf_export_rg.fullTableName)) { return VKOPF_EXPORT_RG.values(); }; 
       if (this.fullTableName.equals(_TAB.verpackungsart.fullTableName)) { return VERPACKUNGSART.values(); }; 
       if (this.fullTableName.equals(_TAB.verarbeitung.fullTableName)) { return VERARBEITUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.ustid_pruefung.fullTableName)) { return USTID_PRUEFUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.uma_kontrakt.fullTableName)) { return UMA_KONTRAKT.values(); }; 
       if (this.fullTableName.equals(_TAB.uma_kon_artb_ruecklief.fullTableName)) { return UMA_KON_ARTB_RUECKLIEF.values(); }; 
       if (this.fullTableName.equals(_TAB.uma_kon_artb_lief.fullTableName)) { return UMA_KON_ARTB_LIEF.values(); }; 
       if (this.fullTableName.equals(_TAB.trigger_action_log.fullTableName)) { return TRIGGER_ACTION_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.trigger_action_def.fullTableName)) { return TRIGGER_ACTION_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.transportmittel.fullTableName)) { return TRANSPORTMITTEL.values(); }; 
       if (this.fullTableName.equals(_TAB.todo_wichtigkeit.fullTableName)) { return TODO_WICHTIGKEIT.values(); }; 
       if (this.fullTableName.equals(_TAB.todo_teilnehmer.fullTableName)) { return TODO_TEILNEHMER.values(); }; 
       if (this.fullTableName.equals(_TAB.todo.fullTableName)) { return TODO.values(); }; 
       if (this.fullTableName.equals(_TAB.text_liste_vorlage.fullTableName)) { return TEXT_LISTE_VORLAGE.values(); }; 
       if (this.fullTableName.equals(_TAB.text_liste.fullTableName)) { return TEXT_LISTE.values(); }; 
       if (this.fullTableName.equals(_TAB.textblock_kat.fullTableName)) { return TEXTBLOCK_KAT.values(); }; 
       if (this.fullTableName.equals(_TAB.textblock.fullTableName)) { return TEXTBLOCK.values(); }; 
       if (this.fullTableName.equals(_TAB.testtabelle_handeldef.fullTableName)) { return TESTTABELLE_HANDELDEF.values(); }; 
       if (this.fullTableName.equals(_TAB.termin_user.fullTableName)) { return TERMIN_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.termin.fullTableName)) { return TERMIN.values(); }; 
       if (this.fullTableName.equals(_TAB.temp_text.fullTableName)) { return TEMP_TEXT.values(); }; 
       if (this.fullTableName.equals(_TAB.temp_imp_sorten.fullTableName)) { return TEMP_IMP_SORTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.temp_import_def.fullTableName)) { return TEMP_IMPORT_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.tax_group.fullTableName)) { return TAX_GROUP.values(); }; 
       if (this.fullTableName.equals(_TAB.tax_aenderungen.fullTableName)) { return TAX_AENDERUNGEN.values(); }; 
       if (this.fullTableName.equals(_TAB.tax.fullTableName)) { return TAX.values(); }; 
       if (this.fullTableName.equals(_TAB.suchvorschlag.fullTableName)) { return SUCHVORSCHLAG.values(); }; 
       if (this.fullTableName.equals(_TAB.strecken_def.fullTableName)) { return STRECKEN_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.status_lager.fullTableName)) { return STATUS_LAGER.values(); }; 
       if (this.fullTableName.equals(_TAB.status_kunde.fullTableName)) { return STATUS_KUNDE.values(); }; 
       if (this.fullTableName.equals(_TAB.sonderzeiten.fullTableName)) { return SONDERZEITEN.values(); }; 
       if (this.fullTableName.equals(_TAB.sheet_def.fullTableName)) { return SHEET_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.sheet_content.fullTableName)) { return SHEET_CONTENT.values(); }; 
       if (this.fullTableName.equals(_TAB.sheet.fullTableName)) { return SHEET.values(); }; 
       if (this.fullTableName.equals(_TAB.selector.fullTableName)) { return SELECTOR.values(); }; 
       if (this.fullTableName.equals(_TAB.searchindex.fullTableName)) { return SEARCHINDEX.values(); }; 
       if (this.fullTableName.equals(_TAB.searchdef.fullTableName)) { return SEARCHDEF.values(); }; 
       if (this.fullTableName.equals(_TAB.schlagwort.fullTableName)) { return SCHLAGWORT.values(); }; 
       if (this.fullTableName.equals(_TAB.scanner_user.fullTableName)) { return SCANNER_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.scanner_settings.fullTableName)) { return SCANNER_SETTINGS.values(); }; 
       if (this.fullTableName.equals(_TAB.scanner_group_2_setting.fullTableName)) { return SCANNER_GROUP_2_SETTING.values(); }; 
       if (this.fullTableName.equals(_TAB.scanner_group.fullTableName)) { return SCANNER_GROUP.values(); }; 
       if (this.fullTableName.equals(_TAB.sanktion_pruefung_pos.fullTableName)) { return SANKTION_PRUEFUNG_POS.values(); }; 
       if (this.fullTableName.equals(_TAB.sanktion_pruefung.fullTableName)) { return SANKTION_PRUEFUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.rep_varianten_param.fullTableName)) { return REP_VARIANTEN_PARAM.values(); }; 
       if (this.fullTableName.equals(_TAB.rep_varianten.fullTableName)) { return REP_VARIANTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.report_setting.fullTableName)) { return REPORT_SETTING.values(); }; 
       if (this.fullTableName.equals(_TAB.report_reiter.fullTableName)) { return REPORT_REITER.values(); }; 
       if (this.fullTableName.equals(_TAB.report_preparing.fullTableName)) { return REPORT_PREPARING.values(); }; 
       if (this.fullTableName.equals(_TAB.report_pp_pos_user_incl.fullTableName)) { return REPORT_PP_POS_USER_INCL.values(); }; 
       if (this.fullTableName.equals(_TAB.report_pp_pos_user_excl.fullTableName)) { return REPORT_PP_POS_USER_EXCL.values(); }; 
       if (this.fullTableName.equals(_TAB.report_pipeline_pos.fullTableName)) { return REPORT_PIPELINE_POS.values(); }; 
       if (this.fullTableName.equals(_TAB.report_pipeline.fullTableName)) { return REPORT_PIPELINE.values(); }; 
       if (this.fullTableName.equals(_TAB.report_parameter.fullTableName)) { return REPORT_PARAMETER.values(); }; 
       if (this.fullTableName.equals(_TAB.report_log_param.fullTableName)) { return REPORT_LOG_PARAM.values(); }; 
       if (this.fullTableName.equals(_TAB.report_log.fullTableName)) { return REPORT_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.reporting_query_param.fullTableName)) { return REPORTING_QUERY_PARAM.values(); }; 
       if (this.fullTableName.equals(_TAB.reporting_query_field.fullTableName)) { return REPORTING_QUERY_FIELD.values(); }; 
       if (this.fullTableName.equals(_TAB.reporting_query.fullTableName)) { return REPORTING_QUERY.values(); }; 
       if (this.fullTableName.equals(_TAB.report.fullTableName)) { return REPORT.values(); }; 
       if (this.fullTableName.equals(_TAB.reminder_user.fullTableName)) { return REMINDER_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.reminder_log.fullTableName)) { return REMINDER_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.reminder_def.fullTableName)) { return REMINDER_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.rechtsform.fullTableName)) { return RECHTSFORM.values(); }; 
       if (this.fullTableName.equals(_TAB.query_teilnehmer.fullTableName)) { return QUERY_TEILNEHMER.values(); }; 
       if (this.fullTableName.equals(_TAB.query.fullTableName)) { return QUERY.values(); }; 
       if (this.fullTableName.equals(_TAB.qualifier.fullTableName)) { return QUALIFIER.values(); }; 
       if (this.fullTableName.equals(_TAB.protokolle_batch.fullTableName)) { return PROTOKOLLE_BATCH.values(); }; 
       if (this.fullTableName.equals(_TAB.pro_mitarb_typ.fullTableName)) { return PRO_MITARB_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.pro_mitarb.fullTableName)) { return PRO_MITARB.values(); }; 
       if (this.fullTableName.equals(_TAB.projektstatusvariante.fullTableName)) { return PROJEKTSTATUSVARIANTE.values(); }; 
       if (this.fullTableName.equals(_TAB.projektinfo.fullTableName)) { return PROJEKTINFO.values(); }; 
       if (this.fullTableName.equals(_TAB.projektgewicht.fullTableName)) { return PROJEKTGEWICHT.values(); }; 
       if (this.fullTableName.equals(_TAB.projekt.fullTableName)) { return PROJEKT.values(); }; 
       if (this.fullTableName.equals(_TAB.proforma_rechnung.fullTableName)) { return PROFORMA_RECHNUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.profil_grenzubertritt.fullTableName)) { return PROFIL_GRENZUBERTRITT.values(); }; 
       if (this.fullTableName.equals(_TAB.pro_adressen.fullTableName)) { return PRO_ADRESSEN.values(); }; 
       if (this.fullTableName.equals(_TAB.preisinfo_temp.fullTableName)) { return PREISINFO_TEMP.values(); }; 
       if (this.fullTableName.equals(_TAB.plz_bundesland.fullTableName)) { return PLZ_BUNDESLAND.values(); }; 
       if (this.fullTableName.equals(_TAB.offene_adr_trans_pap.fullTableName)) { return OFFENE_ADR_TRANS_PAP.values(); }; 
       if (this.fullTableName.equals(_TAB.oecd_code.fullTableName)) { return OECD_CODE.values(); }; 
       if (this.fullTableName.equals(_TAB.nullpreis.fullTableName)) { return NULLPREIS.values(); }; 
       if (this.fullTableName.equals(_TAB.nachricht_mail_settings.fullTableName)) { return NACHRICHT_MAIL_SETTINGS.values(); }; 
       if (this.fullTableName.equals(_TAB.nachricht_mail_log.fullTableName)) { return NACHRICHT_MAIL_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.nachricht_kategorie.fullTableName)) { return NACHRICHT_KATEGORIE.values(); }; 
       if (this.fullTableName.equals(_TAB.nachricht_kat_default.fullTableName)) { return NACHRICHT_KAT_DEFAULT.values(); }; 
       if (this.fullTableName.equals(_TAB.nachricht.fullTableName)) { return NACHRICHT.values(); }; 
       if (this.fullTableName.equals(_TAB.m2n.fullTableName)) { return M2N.values(); }; 
       if (this.fullTableName.equals(_TAB.mwstschluessel_aenderungen.fullTableName)) { return MWSTSCHLUESSEL_AENDERUNGEN.values(); }; 
       if (this.fullTableName.equals(_TAB.mwstschluessel.fullTableName)) { return MWSTSCHLUESSEL.values(); }; 
       if (this.fullTableName.equals(_TAB.modul_connect.fullTableName)) { return MODUL_CONNECT.values(); }; 
       if (this.fullTableName.equals(_TAB.mitarbeitertyp.fullTableName)) { return MITARBEITERTYP.values(); }; 
       if (this.fullTableName.equals(_TAB.mitarbeiter.fullTableName)) { return MITARBEITER.values(); }; 
       if (this.fullTableName.equals(_TAB.message_provider.fullTableName)) { return MESSAGE_PROVIDER.values(); }; 
       if (this.fullTableName.equals(_TAB.medientyp.fullTableName)) { return MEDIENTYP.values(); }; 
       if (this.fullTableName.equals(_TAB.medien.fullTableName)) { return MEDIEN.values(); }; 
       if (this.fullTableName.equals(_TAB.mat_spez.fullTableName)) { return MAT_SPEZ.values(); }; 
       if (this.fullTableName.equals(_TAB.mat_element.fullTableName)) { return MAT_ELEMENT.values(); }; 
       if (this.fullTableName.equals(_TAB.maschinentyp.fullTableName)) { return MASCHINENTYP.values(); }; 
       if (this.fullTableName.equals(_TAB.maschinen_kosten.fullTableName)) { return MASCHINEN_KOSTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.maschinen_aufgabe_typ.fullTableName)) { return MASCHINEN_AUFGABE_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.maschinen_aufgabe.fullTableName)) { return MASCHINEN_AUFGABE.values(); }; 
       if (this.fullTableName.equals(_TAB.maschinen.fullTableName)) { return MASCHINEN.values(); }; 
       if (this.fullTableName.equals(_TAB.mandant_decision.fullTableName)) { return MANDANT_DECISION.values(); }; 
       if (this.fullTableName.equals(_TAB.mandant_config.fullTableName)) { return MANDANT_CONFIG.values(); }; 
       if (this.fullTableName.equals(_TAB.mailing.fullTableName)) { return MAILING.values(); }; 
       if (this.fullTableName.equals(_TAB.mail_aus_mask_jasper.fullTableName)) { return MAIL_AUS_MASK_JASPER.values(); }; 
       if (this.fullTableName.equals(_TAB.mail_aus_mask_email.fullTableName)) { return MAIL_AUS_MASK_EMAIL.values(); }; 
       if (this.fullTableName.equals(_TAB.mail_aus_mask.fullTableName)) { return MAIL_AUS_MASK.values(); }; 
       if (this.fullTableName.equals(_TAB.mahnung_pos.fullTableName)) { return MAHNUNG_POS.values(); }; 
       if (this.fullTableName.equals(_TAB.mahnung.fullTableName)) { return MAHNUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.lock.fullTableName)) { return LOCK.values(); }; 
       if (this.fullTableName.equals(_TAB.listen_zusatzfelder.fullTableName)) { return LISTEN_ZUSATZFELDER.values(); }; 
       if (this.fullTableName.equals(_TAB.lieferbed_kosten.fullTableName)) { return LIEFERBED_KOSTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.lieferbedingungen.fullTableName)) { return LIEFERBEDINGUNGEN.values(); }; 
       if (this.fullTableName.equals(_TAB.lieferadresse.fullTableName)) { return LIEFERADRESSE.values(); }; 
       if (this.fullTableName.equals(_TAB.laufzettel_teilnehmer.fullTableName)) { return LAUFZETTEL_TEILNEHMER.values(); }; 
       if (this.fullTableName.equals(_TAB.laufzettel_status.fullTableName)) { return LAUFZETTEL_STATUS.values(); }; 
       if (this.fullTableName.equals(_TAB.laufzettel_prio.fullTableName)) { return LAUFZETTEL_PRIO.values(); }; 
       if (this.fullTableName.equals(_TAB.laufzettel_eintrag.fullTableName)) { return LAUFZETTEL_EINTRAG.values(); }; 
       if (this.fullTableName.equals(_TAB.laufzettel.fullTableName)) { return LAUFZETTEL.values(); }; 
       if (this.fullTableName.equals(_TAB.land_rc_sorten.fullTableName)) { return LAND_RC_SORTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.lagerplatz_typ.fullTableName)) { return LAGERPLATZ_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.lagerplatz.fullTableName)) { return LAGERPLATZ.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_palette_user.fullTableName)) { return LAGER_PALETTE_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_palette_box.fullTableName)) { return LAGER_PALETTE_BOX.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_palette.fullTableName)) { return LAGER_PALETTE.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_korr_st.fullTableName)) { return LAGER_KORR_ST.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_konto.fullTableName)) { return LAGER_KONTO.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_inventur.fullTableName)) { return LAGER_INVENTUR.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_box.fullTableName)) { return LAGER_BOX.values(); }; 
       if (this.fullTableName.equals(_TAB.lager_bewegung.fullTableName)) { return LAGER_BEWEGUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.kunde_mwst.fullTableName)) { return KUNDE_MWST.values(); }; 
       if (this.fullTableName.equals(_TAB.kred_num_ausschluss.fullTableName)) { return KRED_NUM_AUSSCHLUSS.values(); }; 
       if (this.fullTableName.equals(_TAB.kreditvers_pos.fullTableName)) { return KREDITVERS_POS.values(); }; 
       if (this.fullTableName.equals(_TAB.kreditvers_kopf.fullTableName)) { return KREDITVERS_KOPF.values(); }; 
       if (this.fullTableName.equals(_TAB.kreditvers_adresse.fullTableName)) { return KREDITVERS_ADRESSE.values(); }; 
       if (this.fullTableName.equals(_TAB.kreditlimit_bedingung.fullTableName)) { return KREDITLIMIT_BEDINGUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.kostentyp.fullTableName)) { return KOSTENTYP.values(); }; 
       if (this.fullTableName.equals(_TAB.kosten_lieferbed_def.fullTableName)) { return KOSTEN_LIEFERBED_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.kosten_lieferbed_adr.fullTableName)) { return KOSTEN_LIEFERBED_ADR.values(); }; 
       if (this.fullTableName.equals(_TAB.kosten_artbez_lief.fullTableName)) { return KOSTEN_ARTBEZ_LIEF.values(); }; 
       if (this.fullTableName.equals(_TAB.korre_abzuege.fullTableName)) { return KORRE_ABZUEGE.values(); }; 
       if (this.fullTableName.equals(_TAB.konto.fullTableName)) { return KONTO.values(); }; 
       if (this.fullTableName.equals(_TAB.kommunikations_typ.fullTableName)) { return KOMMUNIKATIONS_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.kommunikation.fullTableName)) { return KOMMUNIKATION.values(); }; 
       if (this.fullTableName.equals(_TAB.kamera_snapshot_grp.fullTableName)) { return KAMERA_SNAPSHOT_GRP.values(); }; 
       if (this.fullTableName.equals(_TAB.kamera_snapshot_entry.fullTableName)) { return KAMERA_SNAPSHOT_ENTRY.values(); }; 
       if (this.fullTableName.equals(_TAB.kamera_settings.fullTableName)) { return KAMERA_SETTINGS.values(); }; 
       if (this.fullTableName.equals(_TAB.jasperreport_value.fullTableName)) { return JASPERREPORT_VALUE.values(); }; 
       if (this.fullTableName.equals(_TAB.jasper_def.fullTableName)) { return JASPER_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.jasper_compile_chain.fullTableName)) { return JASPER_COMPILE_CHAIN.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_verkehrszweig.fullTableName)) { return INTRASTAT_VERKEHRSZWEIG.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_ursprung_reg.fullTableName)) { return INTRASTAT_URSPRUNG_REG.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_negativliste.fullTableName)) { return INTRASTAT_NEGATIVLISTE.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_meldung.fullTableName)) { return INTRASTAT_MELDUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_land_finanzamt.fullTableName)) { return INTRASTAT_LAND_FINANZAMT.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_land.fullTableName)) { return INTRASTAT_LAND.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_kosten.fullTableName)) { return INTRASTAT_KOSTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_geschaeftsart.fullTableName)) { return INTRASTAT_GESCHAEFTSART.values(); }; 
       if (this.fullTableName.equals(_TAB.intrastat_bestimm_region.fullTableName)) { return INTRASTAT_BESTIMM_REGION.values(); }; 
       if (this.fullTableName.equals(_TAB.internet_bookmark.fullTableName)) { return INTERNET_BOOKMARK.values(); }; 
       if (this.fullTableName.equals(_TAB.internet.fullTableName)) { return INTERNET.values(); }; 
       if (this.fullTableName.equals(_TAB.info_typ.fullTableName)) { return INFO_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.info_card_x_user.fullTableName)) { return INFO_CARD_X_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.info_card_x_typ.fullTableName)) { return INFO_CARD_X_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.info_card_x_thema.fullTableName)) { return INFO_CARD_X_THEMA.values(); }; 
       if (this.fullTableName.equals(_TAB.info_card.fullTableName)) { return INFO_CARD.values(); }; 
       if (this.fullTableName.equals(_TAB.hilfetext_zu_modul.fullTableName)) { return HILFETEXT_ZU_MODUL.values(); }; 
       if (this.fullTableName.equals(_TAB.hilfetext.fullTableName)) { return HILFETEXT.values(); }; 
       if (this.fullTableName.equals(_TAB.help_screen_part.fullTableName)) { return HELP_SCREEN_PART.values(); }; 
       if (this.fullTableName.equals(_TAB.help_screen.fullTableName)) { return HELP_SCREEN.values(); }; 
       if (this.fullTableName.equals(_TAB.handelsdef.fullTableName)) { return HANDELSDEF.values(); }; 
       if (this.fullTableName.equals(_TAB.groovyscript.fullTableName)) { return GROOVYSCRIPT.values(); }; 
       if (this.fullTableName.equals(_TAB.fuhre_sonder_vl.fullTableName)) { return FUHRE_SONDER_VL.values(); }; 
       if (this.fullTableName.equals(_TAB.fuhren_rechnungen.fullTableName)) { return FUHREN_RECHNUNGEN.values(); }; 
       if (this.fullTableName.equals(_TAB.fuhren_kosten_typ.fullTableName)) { return FUHREN_KOSTEN_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.fuhren_co2_profil.fullTableName)) { return FUHREN_CO2_PROFIL.values(); }; 
       if (this.fullTableName.equals(_TAB.fp_pos_akte_grund.fullTableName)) { return FP_POS_AKTE_GRUND.values(); }; 
       if (this.fullTableName.equals(_TAB.fp_pos_akte.fullTableName)) { return FP_POS_AKTE.values(); }; 
       if (this.fullTableName.equals(_TAB.firmeninfo.fullTableName)) { return FIRMENINFO.values(); }; 
       if (this.fullTableName.equals(_TAB.filter_or.fullTableName)) { return FILTER_OR.values(); }; 
       if (this.fullTableName.equals(_TAB.filter_and.fullTableName)) { return FILTER_AND.values(); }; 
       if (this.fullTableName.equals(_TAB.filter.fullTableName)) { return FILTER.values(); }; 
       if (this.fullTableName.equals(_TAB.field_rule_modulekenner.fullTableName)) { return FIELD_RULE_MODULEKENNER.values(); }; 
       if (this.fullTableName.equals(_TAB.field_rule.fullTableName)) { return FIELD_RULE.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_vpos_export.fullTableName)) { return FIBU_VPOS_EXPORT.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_vkopf_export.fullTableName)) { return FIBU_VKOPF_EXPORT.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_verrech_waehrung.fullTableName)) { return FIBU_VERRECH_WAEHRUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_vblock_export.fullTableName)) { return FIBU_VBLOCK_EXPORT.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_sachbearbeiter.fullTableName)) { return FIBU_SACHBEARBEITER.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_mahnung.fullTableName)) { return FIBU_MAHNUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_konto.fullTableName)) { return FIBU_KONTO.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_kontenregel_neu.fullTableName)) { return FIBU_KONTENREGEL_NEU.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_kontenregel_land.fullTableName)) { return FIBU_KONTENREGEL_LAND.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_kontenregel.fullTableName)) { return FIBU_KONTENREGEL.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_konstante.fullTableName)) { return FIBU_KONSTANTE.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu_formular.fullTableName)) { return FIBU_FORMULAR.values(); }; 
       if (this.fullTableName.equals(_TAB.fibu.fullTableName)) { return FIBU.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_user.fullTableName)) { return FBAM_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_infoblock.fullTableName)) { return FBAM_INFOBLOCK.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_grund.fullTableName)) { return FBAM_GRUND.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_feststellung.fullTableName)) { return FBAM_FESTSTELLUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_druck_em.fullTableName)) { return FBAM_DRUCK_EM.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_druck.fullTableName)) { return FBAM_DRUCK.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam_betreff.fullTableName)) { return FBAM_BETREFF.values(); }; 
       if (this.fullTableName.equals(_TAB.fbam.fullTableName)) { return FBAM.values(); }; 
       if (this.fullTableName.equals(_TAB.fahrtenvarianten.fullTableName)) { return FAHRTENVARIANTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.fahrplan_zeitangabe.fullTableName)) { return FAHRPLAN_ZEITANGABE.values(); }; 
       if (this.fullTableName.equals(_TAB.fahrplanpos.fullTableName)) { return FAHRPLANPOS.values(); }; 
       if (this.fullTableName.equals(_TAB.export_log.fullTableName)) { return EXPORT_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.email_send_targets.fullTableName)) { return EMAIL_SEND_TARGETS.values(); }; 
       if (this.fullTableName.equals(_TAB.email_send_schablone.fullTableName)) { return EMAIL_SEND_SCHABLONE.values(); }; 
       if (this.fullTableName.equals(_TAB.email_send_attach.fullTableName)) { return EMAIL_SEND_ATTACH.values(); }; 
       if (this.fullTableName.equals(_TAB.email_send.fullTableName)) { return EMAIL_SEND.values(); }; 
       if (this.fullTableName.equals(_TAB.email_protokoll.fullTableName)) { return EMAIL_PROTOKOLL.values(); }; 
       if (this.fullTableName.equals(_TAB.email_inbox_def.fullTableName)) { return EMAIL_INBOX_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.email_inbox.fullTableName)) { return EMAIL_INBOX.values(); }; 
       if (this.fullTableName.equals(_TAB.email_block.fullTableName)) { return EMAIL_BLOCK.values(); }; 
       if (this.fullTableName.equals(_TAB.email.fullTableName)) { return EMAIL.values(); }; 
       if (this.fullTableName.equals(_TAB.element.fullTableName)) { return ELEMENT.values(); }; 
       if (this.fullTableName.equals(_TAB.ek_vk_bezug.fullTableName)) { return EK_VK_BEZUG.values(); }; 
       if (this.fullTableName.equals(_TAB.einheit_faktor.fullTableName)) { return EINHEIT_FAKTOR.values(); }; 
       if (this.fullTableName.equals(_TAB.einheiten_kombinationen.fullTableName)) { return EINHEITEN_KOMBINATIONEN.values(); }; 
       if (this.fullTableName.equals(_TAB.einheit.fullTableName)) { return EINHEIT.values(); }; 
       if (this.fullTableName.equals(_TAB.eak_gruppe_sp.fullTableName)) { return EAK_GRUPPE_SP.values(); }; 
       if (this.fullTableName.equals(_TAB.eak_gruppe.fullTableName)) { return EAK_GRUPPE.values(); }; 
       if (this.fullTableName.equals(_TAB.eak_code_sp.fullTableName)) { return EAK_CODE_SP.values(); }; 
       if (this.fullTableName.equals(_TAB.eak_code.fullTableName)) { return EAK_CODE.values(); }; 
       if (this.fullTableName.equals(_TAB.eak_branche_sp.fullTableName)) { return EAK_BRANCHE_SP.values(); }; 
       if (this.fullTableName.equals(_TAB.eak_branche.fullTableName)) { return EAK_BRANCHE.values(); }; 
       if (this.fullTableName.equals(_TAB.drucker_zuordnung.fullTableName)) { return DRUCKER_ZUORDNUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.drucker.fullTableName)) { return DRUCKER.values(); }; 
       if (this.fullTableName.equals(_TAB.dlp_profil.fullTableName)) { return DLP_PROFIL.values(); }; 
       if (this.fullTableName.equals(_TAB.dlp_join_warenbewg.fullTableName)) { return DLP_JOIN_WARENBEWG.values(); }; 
       if (this.fullTableName.equals(_TAB.dieselpreis.fullTableName)) { return DIESELPREIS.values(); }; 
       if (this.fullTableName.equals(_TAB.def_sonderzeiten.fullTableName)) { return DEF_SONDERZEITEN.values(); }; 
       if (this.fullTableName.equals(_TAB.deb_num_ausschluss.fullTableName)) { return DEB_NUM_AUSSCHLUSS.values(); }; 
       if (this.fullTableName.equals(_TAB.datev_profile.fullTableName)) { return DATEV_PROFILE.values(); }; 
       if (this.fullTableName.equals(_TAB.container_zyklus.fullTableName)) { return CONTAINER_ZYKLUS.values(); }; 
       if (this.fullTableName.equals(_TAB.containertyp.fullTableName)) { return CONTAINERTYP.values(); }; 
       if (this.fullTableName.equals(_TAB.container_station.fullTableName)) { return CONTAINER_STATION.values(); }; 
       if (this.fullTableName.equals(_TAB.container_sorten_ug.fullTableName)) { return CONTAINER_SORTEN_UG.values(); }; 
       if (this.fullTableName.equals(_TAB.container_sorten_hg.fullTableName)) { return CONTAINER_SORTEN_HG.values(); }; 
       if (this.fullTableName.equals(_TAB.container_sorten.fullTableName)) { return CONTAINER_SORTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.container_absetz_grund.fullTableName)) { return CONTAINER_ABSETZ_GRUND.values(); }; 
       if (this.fullTableName.equals(_TAB.container.fullTableName)) { return CONTAINER.values(); }; 
       if (this.fullTableName.equals(_TAB.columns_to_calc.fullTableName)) { return COLUMNS_TO_CALC.values(); }; 
       if (this.fullTableName.equals(_TAB.collections.fullTableName)) { return COLLECTIONS.values(); }; 
       if (this.fullTableName.equals(_TAB.collection_def.fullTableName)) { return COLLECTION_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.changelog.fullTableName)) { return CHANGELOG.values(); }; 
       if (this.fullTableName.equals(_TAB.bundesland.fullTableName)) { return BUNDESLAND.values(); }; 
       if (this.fullTableName.equals(_TAB.branche.fullTableName)) { return BRANCHE.values(); }; 
       if (this.fullTableName.equals(_TAB.bordercrossing_userinfo.fullTableName)) { return BORDERCROSSING_USERINFO.values(); }; 
       if (this.fullTableName.equals(_TAB.bordercrossing_artikel.fullTableName)) { return BORDERCROSSING_ARTIKEL.values(); }; 
       if (this.fullTableName.equals(_TAB.bordercrossing.fullTableName)) { return BORDERCROSSING.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_vektor_kosten.fullTableName)) { return BG_VEKTOR_KOSTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_vektor_konvert.fullTableName)) { return BG_VEKTOR_KONVERT.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_vektor.fullTableName)) { return BG_VEKTOR.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_storno_info.fullTableName)) { return BG_STORNO_INFO.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_station.fullTableName)) { return BG_STATION.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_rech_block.fullTableName)) { return BG_RECH_BLOCK.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_pruefprot.fullTableName)) { return BG_PRUEFPROT.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_fahrplan_to_vektor.fullTableName)) { return BG_FAHRPLAN_TO_VEKTOR.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_fahrplan.fullTableName)) { return BG_FAHRPLAN.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_del_info.fullTableName)) { return BG_DEL_INFO.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_beweg_to_vektor.fullTableName)) { return BG_BEWEG_TO_VEKTOR.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_beweg.fullTableName)) { return BG_BEWEG.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_auswert.fullTableName)) { return BG_AUSWERT.values(); }; 
       if (this.fullTableName.equals(_TAB.bg_atom.fullTableName)) { return BG_ATOM.values(); }; 
       if (this.fullTableName.equals(_TAB.beziehungsdef.fullTableName)) { return BEZIEHUNGSDEF.values(); }; 
       if (this.fullTableName.equals(_TAB.beziehung.fullTableName)) { return BEZIEHUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_vektor_pos.fullTableName)) { return BEWEGUNG_VEKTOR_POS.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_vektor_pn.fullTableName)) { return BEWEGUNG_VEKTOR_PN.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_vektor_log.fullTableName)) { return BEWEGUNG_VEKTOR_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_vektor.fullTableName)) { return BEWEGUNG_VEKTOR.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_trig.fullTableName)) { return BEWEGUNG_TRIG.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_station.fullTableName)) { return BEWEGUNG_STATION.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_setzkasten_k.fullTableName)) { return BEWEGUNG_SETZKASTEN_K.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_setzkasten.fullTableName)) { return BEWEGUNG_SETZKASTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_menge.fullTableName)) { return BEWEGUNG_MENGE.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_atom_verbucht_k.fullTableName)) { return BEWEGUNG_ATOM_VERBUCHT_K.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_atom_verbucht.fullTableName)) { return BEWEGUNG_ATOM_VERBUCHT.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_atom_kosten.fullTableName)) { return BEWEGUNG_ATOM_KOSTEN.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_atom_abzug.fullTableName)) { return BEWEGUNG_ATOM_ABZUG.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung_atom.fullTableName)) { return BEWEGUNG_ATOM.values(); }; 
       if (this.fullTableName.equals(_TAB.bewegung.fullTableName)) { return BEWEGUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.besuchsergebnis.fullTableName)) { return BESUCHSERGEBNIS.values(); }; 
       if (this.fullTableName.equals(_TAB.bestellkopf.fullTableName)) { return BESTELLKOPF.values(); }; 
       if (this.fullTableName.equals(_TAB.batch_user.fullTableName)) { return BATCH_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.basel_code.fullTableName)) { return BASEL_CODE.values(); }; 
       if (this.fullTableName.equals(_TAB.bankenstamm.fullTableName)) { return BANKENSTAMM.values(); }; 
       if (this.fullTableName.equals(_TAB.bam_import_info.fullTableName)) { return BAM_IMPORT_INFO.values(); }; 
       if (this.fullTableName.equals(_TAB.bam_import.fullTableName)) { return BAM_IMPORT.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_umwandlung.fullTableName)) { return ARTIKEL_UMWANDLUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_gruppe_fibu.fullTableName)) { return ARTIKEL_GRUPPE_FIBU.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_gruppe.fullTableName)) { return ARTIKEL_GRUPPE.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_datenblatt.fullTableName)) { return ARTIKEL_DATENBLATT.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_bez_mwst.fullTableName)) { return ARTIKEL_BEZ_MWST.values(); }; 
       if (this.fullTableName.equals(_TAB.artikelbez_lief.fullTableName)) { return ARTIKELBEZ_LIEF.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_bez.fullTableName)) { return ARTIKEL_BEZ.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel_bereich.fullTableName)) { return ARTIKEL_BEREICH.values(); }; 
       if (this.fullTableName.equals(_TAB.artikel.fullTableName)) { return ARTIKEL.values(); }; 
       if (this.fullTableName.equals(_TAB.artbez_verunreinigung.fullTableName)) { return ARTBEZ_VERUNREINIGUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.artbez_mech_zustand.fullTableName)) { return ARTBEZ_MECH_ZUSTAND.values(); }; 
       if (this.fullTableName.equals(_TAB.archivmedien.fullTableName)) { return ARCHIVMEDIEN.values(); }; 
       if (this.fullTableName.equals(_TAB.anrede.fullTableName)) { return ANREDE.values(); }; 
       if (this.fullTableName.equals(_TAB.aktionsanlass.fullTableName)) { return AKTIONSANLASS.values(); }; 
       if (this.fullTableName.equals(_TAB.ah7_steuerdatei.fullTableName)) { return AH7_STEUERDATEI.values(); }; 
       if (this.fullTableName.equals(_TAB.ah7_profil.fullTableName)) { return AH7_PROFIL.values(); }; 
       if (this.fullTableName.equals(_TAB.adress_zusatzwerte.fullTableName)) { return ADRESS_ZUSATZWERTE.values(); }; 
       if (this.fullTableName.equals(_TAB.adress_zusatzfeld.fullTableName)) { return ADRESS_ZUSATZFELD.values(); }; 
       if (this.fullTableName.equals(_TAB.adressklasse_def.fullTableName)) { return ADRESSKLASSE_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.adressklasse.fullTableName)) { return ADRESSKLASSE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_zusatzbranche.fullTableName)) { return ADRESSE_ZUSATZBRANCHE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_waehrung.fullTableName)) { return ADRESSE_WAEHRUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_ust_id.fullTableName)) { return ADRESSE_UST_ID.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_potential.fullTableName)) { return ADRESSE_POTENTIAL.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_merkmal5.fullTableName)) { return ADRESSE_MERKMAL5.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_merkmal4.fullTableName)) { return ADRESSE_MERKMAL4.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_merkmal3.fullTableName)) { return ADRESSE_MERKMAL3.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_merkmal2.fullTableName)) { return ADRESSE_MERKMAL2.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_merkmal1.fullTableName)) { return ADRESSE_MERKMAL1.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_info.fullTableName)) { return ADRESSE_INFO.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_geschenk.fullTableName)) { return ADRESSE_GESCHENK.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_fahrzeuge.fullTableName)) { return ADRESSE_FAHRZEUGE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_eu_vertr_form.fullTableName)) { return ADRESSE_EU_VERTR_FORM.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_eak_code.fullTableName)) { return ADRESSE_EAK_CODE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_artikel.fullTableName)) { return ADRESSE_ARTIKEL.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_aqu_rel_wbw_kd.fullTableName)) { return ADRESSE_AQU_REL_WBW_KD.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_aqu_rel_kd_sorte.fullTableName)) { return ADRESSE_AQU_REL_KD_SORTE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_aquise_akte.fullTableName)) { return ADRESSE_AQUISE_AKTE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse_aquise.fullTableName)) { return ADRESSE_AQUISE.values(); }; 
       if (this.fullTableName.equals(_TAB.adresse.fullTableName)) { return ADRESSE.values(); }; 
       if (this.fullTableName.equals(_TAB.adressausstatt_def.fullTableName)) { return ADRESSAUSSTATT_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.adressausstatt.fullTableName)) { return ADRESSAUSSTATT.values(); }; 
       if (this.fullTableName.equals(_TAB.adr_containertyp.fullTableName)) { return ADR_CONTAINERTYP.values(); }; 
       if (this.fullTableName.equals(_TAB.abzugsschablonen.fullTableName)) { return ABZUGSSCHABLONEN.values(); }; 
       if (this.fullTableName.equals(_TAB.abzugsgrund.fullTableName)) { return ABZUGSGRUND.values(); }; 
       if (this.fullTableName.equals(_TAB.abrechblatt_pos.fullTableName)) { return ABRECHBLATT_POS.values(); }; 
       if (this.fullTableName.equals(_TAB.abrechblatt_artbez_out.fullTableName)) { return ABRECHBLATT_ARTBEZ_OUT.values(); }; 
       if (this.fullTableName.equals(_TAB.abrechblatt_artbez_in.fullTableName)) { return ABRECHBLATT_ARTBEZ_IN.values(); }; 
       if (this.fullTableName.equals(_TAB.abrechblatt.fullTableName)) { return ABRECHBLATT.values(); }; 
       if (this.fullTableName.equals(_TAB.zugriff.fullTableName)) { return ZUGRIFF.values(); }; 
       if (this.fullTableName.equals(_TAB.waehrungsquery.fullTableName)) { return WAEHRUNGSQUERY.values(); }; 
       if (this.fullTableName.equals(_TAB.waehrung.fullTableName)) { return WAEHRUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.version.fullTableName)) { return VERSION.values(); }; 
       if (this.fullTableName.equals(_TAB.usersettings_jsonbig.fullTableName)) { return USERSETTINGS_JSONBIG.values(); }; 
       if (this.fullTableName.equals(_TAB.usersettings_json.fullTableName)) { return USERSETTINGS_JSON.values(); }; 
       if (this.fullTableName.equals(_TAB.usersettings.fullTableName)) { return USERSETTINGS.values(); }; 
       if (this.fullTableName.equals(_TAB.usergroup.fullTableName)) { return USERGROUP.values(); }; 
       if (this.fullTableName.equals(_TAB.user_applet.fullTableName)) { return USER_APPLET.values(); }; 
       if (this.fullTableName.equals(_TAB.user.fullTableName)) { return USER.values(); }; 
       if (this.fullTableName.equals(_TAB.trigger_def.fullTableName)) { return TRIGGER_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.textuebersetzung.fullTableName)) { return TEXTUEBERSETZUNG.values(); }; 
       if (this.fullTableName.equals(_TAB.textkonstante.fullTableName)) { return TEXTKONSTANTE.values(); }; 
       if (this.fullTableName.equals(_TAB.tabellenmigration.fullTableName)) { return TABELLENMIGRATION.values(); }; 
       if (this.fullTableName.equals(_TAB.sprache.fullTableName)) { return SPRACHE.values(); }; 
       if (this.fullTableName.equals(_TAB.speed_index.fullTableName)) { return SPEED_INDEX.values(); }; 
       if (this.fullTableName.equals(_TAB.servlets.fullTableName)) { return SERVLETS.values(); }; 
       if (this.fullTableName.equals(_TAB.reportaktion.fullTableName)) { return REPORTAKTION.values(); }; 
       if (this.fullTableName.equals(_TAB.registry.fullTableName)) { return REGISTRY.values(); }; 
       if (this.fullTableName.equals(_TAB.negativlist.fullTableName)) { return NEGATIVLIST.values(); }; 
       if (this.fullTableName.equals(_TAB.mask_def_cell.fullTableName)) { return MASK_DEF_CELL.values(); }; 
       if (this.fullTableName.equals(_TAB.mask_def.fullTableName)) { return MASK_DEF.values(); }; 
       if (this.fullTableName.equals(_TAB.mandant_zusatz_feldnamen.fullTableName)) { return MANDANT_ZUSATZ_FELDNAMEN.values(); }; 
       if (this.fullTableName.equals(_TAB.mandant_zusatz.fullTableName)) { return MANDANT_ZUSATZ.values(); }; 
       if (this.fullTableName.equals(_TAB.mandant_steuervermerk.fullTableName)) { return MANDANT_STEUERVERMERK.values(); }; 
       if (this.fullTableName.equals(_TAB.mandant.fullTableName)) { return MANDANT.values(); }; 
       if (this.fullTableName.equals(_TAB.login.fullTableName)) { return LOGIN.values(); }; 
       if (this.fullTableName.equals(_TAB.land.fullTableName)) { return LAND.values(); }; 
       if (this.fullTableName.equals(_TAB.jasperreport_key.fullTableName)) { return JASPERREPORT_KEY.values(); }; 
       if (this.fullTableName.equals(_TAB.hauptmenue.fullTableName)) { return HAUPTMENUE.values(); }; 
       if (this.fullTableName.equals(_TAB.enum_vektor_pos_typ.fullTableName)) { return ENUM_VEKTOR_POS_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.enum_bewegung_typ.fullTableName)) { return ENUM_BEWEGUNG_TYP.values(); }; 
       if (this.fullTableName.equals(_TAB.db_log.fullTableName)) { return DB_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.datum.fullTableName)) { return DATUM.values(); }; 
       if (this.fullTableName.equals(_TAB.button_user.fullTableName)) { return BUTTON_USER.values(); }; 
       if (this.fullTableName.equals(_TAB.button.fullTableName)) { return BUTTON.values(); }; 
       if (this.fullTableName.equals(_TAB.batch_task.fullTableName)) { return BATCH_TASK.values(); }; 
       if (this.fullTableName.equals(_TAB.batch_log.fullTableName)) { return BATCH_LOG.values(); }; 
       if (this.fullTableName.equals(_TAB.applet.fullTableName)) { return APPLET.values(); }; 
    throw new myException(this,this.name()+": fieldsEnum was not found !");
    }



    public MyRECORD_IF_RECORDS nativeRecord() throws myException {
        if (this.fullTableName.equals(_TAB.zz_rohstoff_alt_leb321k.fullTableName)) { return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(); }; 
        if (this.fullTableName.equals(_TAB.zz_aw_warenstroeme.fullTableName)) { return new RECORD_ZZ_AW_WARENSTROEME(); }; 
        if (this.fullTableName.equals(_TAB.z_test.fullTableName)) { return new RECORD_Z_TEST(); }; 
        if (this.fullTableName.equals(_TAB.zolltarifnummer_import.fullTableName)) { return new RECORD_ZOLLTARIFNUMMER_IMPORT(); }; 
        if (this.fullTableName.equals(_TAB.zolltarifnummer.fullTableName)) { return new RECORD_ZOLLTARIFNUMMER(); }; 
        if (this.fullTableName.equals(_TAB.zollagentur.fullTableName)) { return new RECORD_ZOLLAGENTUR(); }; 
        if (this.fullTableName.equals(_TAB.zahlungsmedium.fullTableName)) { return new RECORD_ZAHLUNGSMEDIUM(); }; 
        if (this.fullTableName.equals(_TAB.zahlungsbedingungen.fullTableName)) { return new RECORD_ZAHLUNGSBEDINGUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_user_befund.fullTableName)) { return new RECORD_WIEGEKARTE_USER_BEFUND(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_mge_abz.fullTableName)) { return new RECORD_WIEGEKARTE_MGE_ABZ(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_gebinde.fullTableName)) { return new RECORD_WIEGEKARTE_GEBINDE(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_container.fullTableName)) { return new RECORD_WIEGEKARTE_CONTAINER(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_befund.fullTableName)) { return new RECORD_WIEGEKARTE_BEFUND(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_abzug_geb.fullTableName)) { return new RECORD_WIEGEKARTE_ABZUG_GEB(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte.fullTableName)) { return new RECORD_WIEGEKARTE(); }; 
        if (this.fullTableName.equals(_TAB.waegung.fullTableName)) { return new RECORD_WAEGUNG(); }; 
        if (this.fullTableName.equals(_TAB.waage_user.fullTableName)) { return new RECORD_WAAGE_USER(); }; 
        if (this.fullTableName.equals(_TAB.waage_standort.fullTableName)) { return new RECORD_WAAGE_STANDORT(); }; 
        if (this.fullTableName.equals(_TAB.waage_settings.fullTableName)) { return new RECORD_WAAGE_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.waage_lager.fullTableName)) { return new RECORD_WAAGE_LAGER(); }; 
        if (this.fullTableName.equals(_TAB.waage_hofschein_pruef.fullTableName)) { return new RECORD_WAAGE_HOFSCHEIN_PRUEF(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_vp_ext.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_VP_EXT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_sonder.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_SONDER(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_rgvl.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_RGVL(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort_rgvl.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_ORT_RGVL(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_ORT_ABZUG(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_ORT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_kosten.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_druck_em.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_druck.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_abzug_vk.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_ABZUG_VK(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_abzug_ek.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE_ABZUG_EK(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre.fullTableName)) { return new RECORD_VPOS_TPA_FUHRE(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa.fullTableName)) { return new RECORD_VPOS_TPA(); }; 
        if (this.fullTableName.equals(_TAB.vpos_std_angebot.fullTableName)) { return new RECORD_VPOS_STD_ANGEBOT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_std.fullTableName)) { return new RECORD_VPOS_STD(); }; 
        if (this.fullTableName.equals(_TAB.vpos_rg_vl.fullTableName)) { return new RECORD_VPOS_RG_VL(); }; 
        if (this.fullTableName.equals(_TAB.vpos_rg_abzug.fullTableName)) { return new RECORD_VPOS_RG_ABZUG(); }; 
        if (this.fullTableName.equals(_TAB.vpos_rg.fullTableName)) { return new RECORD_VPOS_RG(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_trakt.fullTableName)) { return new RECORD_VPOS_KON_TRAKT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_term.fullTableName)) { return new RECORD_VPOS_KON_TERM(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_lager.fullTableName)) { return new RECORD_VPOS_KON_LAGER(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_avv.fullTableName)) { return new RECORD_VPOS_KON_AVV(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_aenderungen.fullTableName)) { return new RECORD_VPOS_KON_AENDERUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon.fullTableName)) { return new RECORD_VPOS_KON(); }; 
        if (this.fullTableName.equals(_TAB.vpos_export_rg.fullTableName)) { return new RECORD_VPOS_EXPORT_RG(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_tpa_druck_em.fullTableName)) { return new RECORD_VKOPF_TPA_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_tpa_druck.fullTableName)) { return new RECORD_VKOPF_TPA_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_tpa.fullTableName)) { return new RECORD_VKOPF_TPA(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_std_druck_em.fullTableName)) { return new RECORD_VKOPF_STD_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_std_druck.fullTableName)) { return new RECORD_VKOPF_STD_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_std.fullTableName)) { return new RECORD_VKOPF_STD(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_rg_druck_em.fullTableName)) { return new RECORD_VKOPF_RG_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_rg_druck.fullTableName)) { return new RECORD_VKOPF_RG_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_rg.fullTableName)) { return new RECORD_VKOPF_RG(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_kon_druck_em.fullTableName)) { return new RECORD_VKOPF_KON_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_kon_druck.fullTableName)) { return new RECORD_VKOPF_KON_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_kon.fullTableName)) { return new RECORD_VKOPF_KON(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_export_rg.fullTableName)) { return new RECORD_VKOPF_EXPORT_RG(); }; 
        if (this.fullTableName.equals(_TAB.verpackungsart.fullTableName)) { return new RECORD_VERPACKUNGSART(); }; 
        if (this.fullTableName.equals(_TAB.verarbeitung.fullTableName)) { return new RECORD_VERARBEITUNG(); }; 
        if (this.fullTableName.equals(_TAB.ustid_pruefung.fullTableName)) { return new RECORD_USTID_PRUEFUNG(); }; 
        if (this.fullTableName.equals(_TAB.uma_kontrakt.fullTableName)) { return new RECORD_UMA_KONTRAKT(); }; 
        if (this.fullTableName.equals(_TAB.uma_kon_artb_ruecklief.fullTableName)) { return new RECORD_UMA_KON_ARTB_RUECKLIEF(); }; 
        if (this.fullTableName.equals(_TAB.uma_kon_artb_lief.fullTableName)) { return new RECORD_UMA_KON_ARTB_LIEF(); }; 
        if (this.fullTableName.equals(_TAB.trigger_action_log.fullTableName)) { return new RECORD_TRIGGER_ACTION_LOG(); }; 
        if (this.fullTableName.equals(_TAB.trigger_action_def.fullTableName)) { return new RECORD_TRIGGER_ACTION_DEF(); }; 
        if (this.fullTableName.equals(_TAB.transportmittel.fullTableName)) { return new RECORD_TRANSPORTMITTEL(); }; 
        if (this.fullTableName.equals(_TAB.todo_wichtigkeit.fullTableName)) { return new RECORD_TODO_WICHTIGKEIT(); }; 
        if (this.fullTableName.equals(_TAB.todo_teilnehmer.fullTableName)) { return new RECORD_TODO_TEILNEHMER(); }; 
        if (this.fullTableName.equals(_TAB.todo.fullTableName)) { return new RECORD_TODO(); }; 
        if (this.fullTableName.equals(_TAB.text_liste_vorlage.fullTableName)) { return new RECORD_TEXT_LISTE_VORLAGE(); }; 
        if (this.fullTableName.equals(_TAB.text_liste.fullTableName)) { return new RECORD_TEXT_LISTE(); }; 
        if (this.fullTableName.equals(_TAB.textblock_kat.fullTableName)) { return new RECORD_TEXTBLOCK_KAT(); }; 
        if (this.fullTableName.equals(_TAB.textblock.fullTableName)) { return new RECORD_TEXTBLOCK(); }; 
        if (this.fullTableName.equals(_TAB.testtabelle_handeldef.fullTableName)) { return new RECORD_TESTTABELLE_HANDELDEF(); }; 
        if (this.fullTableName.equals(_TAB.termin_user.fullTableName)) { return new RECORD_TERMIN_USER(); }; 
        if (this.fullTableName.equals(_TAB.termin.fullTableName)) { return new RECORD_TERMIN(); }; 
        if (this.fullTableName.equals(_TAB.temp_text.fullTableName)) { return new RECORD_TEMP_TEXT(); }; 
        if (this.fullTableName.equals(_TAB.temp_imp_sorten.fullTableName)) { return new RECORD_TEMP_IMP_SORTEN(); }; 
        if (this.fullTableName.equals(_TAB.temp_import_def.fullTableName)) { return new RECORD_TEMP_IMPORT_DEF(); }; 
        if (this.fullTableName.equals(_TAB.tax_group.fullTableName)) { return new RECORD_TAX_GROUP(); }; 
        if (this.fullTableName.equals(_TAB.tax_aenderungen.fullTableName)) { return new RECORD_TAX_AENDERUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.tax.fullTableName)) { return new RECORD_TAX(); }; 
        if (this.fullTableName.equals(_TAB.suchvorschlag.fullTableName)) { return new RECORD_SUCHVORSCHLAG(); }; 
        if (this.fullTableName.equals(_TAB.strecken_def.fullTableName)) { return new RECORD_STRECKEN_DEF(); }; 
        if (this.fullTableName.equals(_TAB.status_lager.fullTableName)) { return new RECORD_STATUS_LAGER(); }; 
        if (this.fullTableName.equals(_TAB.status_kunde.fullTableName)) { return new RECORD_STATUS_KUNDE(); }; 
        if (this.fullTableName.equals(_TAB.sonderzeiten.fullTableName)) { return new RECORD_SONDERZEITEN(); }; 
        if (this.fullTableName.equals(_TAB.sheet_def.fullTableName)) { return new RECORD_SHEET_DEF(); }; 
        if (this.fullTableName.equals(_TAB.sheet_content.fullTableName)) { return new RECORD_SHEET_CONTENT(); }; 
        if (this.fullTableName.equals(_TAB.sheet.fullTableName)) { return new RECORD_SHEET(); }; 
        if (this.fullTableName.equals(_TAB.selector.fullTableName)) { return new RECORD_SELECTOR(); }; 
        if (this.fullTableName.equals(_TAB.searchindex.fullTableName)) { return new RECORD_SEARCHINDEX(); }; 
        if (this.fullTableName.equals(_TAB.searchdef.fullTableName)) { return new RECORD_SEARCHDEF(); }; 
        if (this.fullTableName.equals(_TAB.schlagwort.fullTableName)) { return new RECORD_SCHLAGWORT(); }; 
        if (this.fullTableName.equals(_TAB.scanner_user.fullTableName)) { return new RECORD_SCANNER_USER(); }; 
        if (this.fullTableName.equals(_TAB.scanner_settings.fullTableName)) { return new RECORD_SCANNER_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.scanner_group_2_setting.fullTableName)) { return new RECORD_SCANNER_GROUP_2_SETTING(); }; 
        if (this.fullTableName.equals(_TAB.scanner_group.fullTableName)) { return new RECORD_SCANNER_GROUP(); }; 
        if (this.fullTableName.equals(_TAB.sanktion_pruefung_pos.fullTableName)) { return new RECORD_SANKTION_PRUEFUNG_POS(); }; 
        if (this.fullTableName.equals(_TAB.sanktion_pruefung.fullTableName)) { return new RECORD_SANKTION_PRUEFUNG(); }; 
        if (this.fullTableName.equals(_TAB.rep_varianten_param.fullTableName)) { return new RECORD_REP_VARIANTEN_PARAM(); }; 
        if (this.fullTableName.equals(_TAB.rep_varianten.fullTableName)) { return new RECORD_REP_VARIANTEN(); }; 
        if (this.fullTableName.equals(_TAB.report_setting.fullTableName)) { return new RECORD_REPORT_SETTING(); }; 
        if (this.fullTableName.equals(_TAB.report_reiter.fullTableName)) { return new RECORD_REPORT_REITER(); }; 
        if (this.fullTableName.equals(_TAB.report_preparing.fullTableName)) { return new RECORD_REPORT_PREPARING(); }; 
        if (this.fullTableName.equals(_TAB.report_pp_pos_user_incl.fullTableName)) { return new RECORD_REPORT_PP_POS_USER_INCL(); }; 
        if (this.fullTableName.equals(_TAB.report_pp_pos_user_excl.fullTableName)) { return new RECORD_REPORT_PP_POS_USER_EXCL(); }; 
        if (this.fullTableName.equals(_TAB.report_pipeline_pos.fullTableName)) { return new RECORD_REPORT_PIPELINE_POS(); }; 
        if (this.fullTableName.equals(_TAB.report_pipeline.fullTableName)) { return new RECORD_REPORT_PIPELINE(); }; 
        if (this.fullTableName.equals(_TAB.report_parameter.fullTableName)) { return new RECORD_REPORT_PARAMETER(); }; 
        if (this.fullTableName.equals(_TAB.report_log_param.fullTableName)) { return new RECORD_REPORT_LOG_PARAM(); }; 
        if (this.fullTableName.equals(_TAB.report_log.fullTableName)) { return new RECORD_REPORT_LOG(); }; 
        if (this.fullTableName.equals(_TAB.reporting_query_param.fullTableName)) { return new RECORD_REPORTING_QUERY_PARAM(); }; 
        if (this.fullTableName.equals(_TAB.reporting_query_field.fullTableName)) { return new RECORD_REPORTING_QUERY_FIELD(); }; 
        if (this.fullTableName.equals(_TAB.reporting_query.fullTableName)) { return new RECORD_REPORTING_QUERY(); }; 
        if (this.fullTableName.equals(_TAB.report.fullTableName)) { return new RECORD_REPORT(); }; 
        if (this.fullTableName.equals(_TAB.reminder_user.fullTableName)) { return new RECORD_REMINDER_USER(); }; 
        if (this.fullTableName.equals(_TAB.reminder_log.fullTableName)) { return new RECORD_REMINDER_LOG(); }; 
        if (this.fullTableName.equals(_TAB.reminder_def.fullTableName)) { return new RECORD_REMINDER_DEF(); }; 
        if (this.fullTableName.equals(_TAB.rechtsform.fullTableName)) { return new RECORD_RECHTSFORM(); }; 
        if (this.fullTableName.equals(_TAB.query_teilnehmer.fullTableName)) { return new RECORD_QUERY_TEILNEHMER(); }; 
        if (this.fullTableName.equals(_TAB.query.fullTableName)) { return new RECORD_QUERY(); }; 
        if (this.fullTableName.equals(_TAB.qualifier.fullTableName)) { return new RECORD_QUALIFIER(); }; 
        if (this.fullTableName.equals(_TAB.protokolle_batch.fullTableName)) { return new RECORD_PROTOKOLLE_BATCH(); }; 
        if (this.fullTableName.equals(_TAB.pro_mitarb_typ.fullTableName)) { return new RECORD_PRO_MITARB_TYP(); }; 
        if (this.fullTableName.equals(_TAB.pro_mitarb.fullTableName)) { return new RECORD_PRO_MITARB(); }; 
        if (this.fullTableName.equals(_TAB.projektstatusvariante.fullTableName)) { return new RECORD_PROJEKTSTATUSVARIANTE(); }; 
        if (this.fullTableName.equals(_TAB.projektinfo.fullTableName)) { return new RECORD_PROJEKTINFO(); }; 
        if (this.fullTableName.equals(_TAB.projektgewicht.fullTableName)) { return new RECORD_PROJEKTGEWICHT(); }; 
        if (this.fullTableName.equals(_TAB.projekt.fullTableName)) { return new RECORD_PROJEKT(); }; 
        if (this.fullTableName.equals(_TAB.proforma_rechnung.fullTableName)) { return new RECORD_PROFORMA_RECHNUNG(); }; 
        if (this.fullTableName.equals(_TAB.profil_grenzubertritt.fullTableName)) { return new RECORD_PROFIL_GRENZUBERTRITT(); }; 
        if (this.fullTableName.equals(_TAB.pro_adressen.fullTableName)) { return new RECORD_PRO_ADRESSEN(); }; 
        if (this.fullTableName.equals(_TAB.preisinfo_temp.fullTableName)) { return new RECORD_PREISINFO_TEMP(); }; 
        if (this.fullTableName.equals(_TAB.plz_bundesland.fullTableName)) { return new RECORD_PLZ_BUNDESLAND(); }; 
        if (this.fullTableName.equals(_TAB.offene_adr_trans_pap.fullTableName)) { return new RECORD_OFFENE_ADR_TRANS_PAP(); }; 
        if (this.fullTableName.equals(_TAB.oecd_code.fullTableName)) { return new RECORD_OECD_CODE(); }; 
        if (this.fullTableName.equals(_TAB.nullpreis.fullTableName)) { return new RECORD_NULLPREIS(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_mail_settings.fullTableName)) { return new RECORD_NACHRICHT_MAIL_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_mail_log.fullTableName)) { return new RECORD_NACHRICHT_MAIL_LOG(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_kategorie.fullTableName)) { return new RECORD_NACHRICHT_KATEGORIE(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_kat_default.fullTableName)) { return new RECORD_NACHRICHT_KAT_DEFAULT(); }; 
        if (this.fullTableName.equals(_TAB.nachricht.fullTableName)) { return new RECORD_NACHRICHT(); }; 
        if (this.fullTableName.equals(_TAB.m2n.fullTableName)) { return new RECORD_M2N(); }; 
        if (this.fullTableName.equals(_TAB.mwstschluessel_aenderungen.fullTableName)) { return new RECORD_MWSTSCHLUESSEL_AENDERUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.mwstschluessel.fullTableName)) { return new RECORD_MWSTSCHLUESSEL(); }; 
        if (this.fullTableName.equals(_TAB.modul_connect.fullTableName)) { return new RECORD_MODUL_CONNECT(); }; 
        if (this.fullTableName.equals(_TAB.mitarbeitertyp.fullTableName)) { return new RECORD_MITARBEITERTYP(); }; 
        if (this.fullTableName.equals(_TAB.mitarbeiter.fullTableName)) { return new RECORD_MITARBEITER(); }; 
        if (this.fullTableName.equals(_TAB.message_provider.fullTableName)) { return new RECORD_MESSAGE_PROVIDER(); }; 
        if (this.fullTableName.equals(_TAB.medientyp.fullTableName)) { return new RECORD_MEDIENTYP(); }; 
        if (this.fullTableName.equals(_TAB.medien.fullTableName)) { return new RECORD_MEDIEN(); }; 
        if (this.fullTableName.equals(_TAB.mat_spez.fullTableName)) { return new RECORD_MAT_SPEZ(); }; 
        if (this.fullTableName.equals(_TAB.mat_element.fullTableName)) { return new RECORD_MAT_ELEMENT(); }; 
        if (this.fullTableName.equals(_TAB.maschinentyp.fullTableName)) { return new RECORD_MASCHINENTYP(); }; 
        if (this.fullTableName.equals(_TAB.maschinen_kosten.fullTableName)) { return new RECORD_MASCHINEN_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.maschinen_aufgabe_typ.fullTableName)) { return new RECORD_MASCHINEN_AUFGABE_TYP(); }; 
        if (this.fullTableName.equals(_TAB.maschinen_aufgabe.fullTableName)) { return new RECORD_MASCHINEN_AUFGABE(); }; 
        if (this.fullTableName.equals(_TAB.maschinen.fullTableName)) { return new RECORD_MASCHINEN(); }; 
        if (this.fullTableName.equals(_TAB.mandant_decision.fullTableName)) { return new RECORD_MANDANT_DECISION(); }; 
        if (this.fullTableName.equals(_TAB.mandant_config.fullTableName)) { return new RECORD_MANDANT_CONFIG(); }; 
        if (this.fullTableName.equals(_TAB.mailing.fullTableName)) { return new RECORD_MAILING(); }; 
        if (this.fullTableName.equals(_TAB.mail_aus_mask_jasper.fullTableName)) { return new RECORD_MAIL_AUS_MASK_JASPER(); }; 
        if (this.fullTableName.equals(_TAB.mail_aus_mask_email.fullTableName)) { return new RECORD_MAIL_AUS_MASK_EMAIL(); }; 
        if (this.fullTableName.equals(_TAB.mail_aus_mask.fullTableName)) { return new RECORD_MAIL_AUS_MASK(); }; 
        if (this.fullTableName.equals(_TAB.mahnung_pos.fullTableName)) { return new RECORD_MAHNUNG_POS(); }; 
        if (this.fullTableName.equals(_TAB.mahnung.fullTableName)) { return new RECORD_MAHNUNG(); }; 
        if (this.fullTableName.equals(_TAB.lock.fullTableName)) { return new RECORD_LOCK(); }; 
        if (this.fullTableName.equals(_TAB.listen_zusatzfelder.fullTableName)) { return new RECORD_LISTEN_ZUSATZFELDER(); }; 
        if (this.fullTableName.equals(_TAB.lieferbed_kosten.fullTableName)) { return new RECORD_LIEFERBED_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.lieferbedingungen.fullTableName)) { return new RECORD_LIEFERBEDINGUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.lieferadresse.fullTableName)) { return new RECORD_LIEFERADRESSE(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_teilnehmer.fullTableName)) { return new RECORD_LAUFZETTEL_TEILNEHMER(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_status.fullTableName)) { return new RECORD_LAUFZETTEL_STATUS(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_prio.fullTableName)) { return new RECORD_LAUFZETTEL_PRIO(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_eintrag.fullTableName)) { return new RECORD_LAUFZETTEL_EINTRAG(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel.fullTableName)) { return new RECORD_LAUFZETTEL(); }; 
        if (this.fullTableName.equals(_TAB.land_rc_sorten.fullTableName)) { return new RECORD_LAND_RC_SORTEN(); }; 
        if (this.fullTableName.equals(_TAB.lagerplatz_typ.fullTableName)) { return new RECORD_LAGERPLATZ_TYP(); }; 
        if (this.fullTableName.equals(_TAB.lagerplatz.fullTableName)) { return new RECORD_LAGERPLATZ(); }; 
        if (this.fullTableName.equals(_TAB.lager_palette_user.fullTableName)) { return new RECORD_LAGER_PALETTE_USER(); }; 
        if (this.fullTableName.equals(_TAB.lager_palette_box.fullTableName)) { return new RECORD_LAGER_PALETTE_BOX(); }; 
        if (this.fullTableName.equals(_TAB.lager_palette.fullTableName)) { return new RECORD_LAGER_PALETTE(); }; 
        if (this.fullTableName.equals(_TAB.lager_korr_st.fullTableName)) { return new RECORD_LAGER_KORR_ST(); }; 
        if (this.fullTableName.equals(_TAB.lager_konto.fullTableName)) { return new RECORD_LAGER_KONTO(); }; 
        if (this.fullTableName.equals(_TAB.lager_inventur.fullTableName)) { return new RECORD_LAGER_INVENTUR(); }; 
        if (this.fullTableName.equals(_TAB.lager_box.fullTableName)) { return new RECORD_LAGER_BOX(); }; 
        if (this.fullTableName.equals(_TAB.lager_bewegung.fullTableName)) { return new RECORD_LAGER_BEWEGUNG(); }; 
        if (this.fullTableName.equals(_TAB.kunde_mwst.fullTableName)) { return new RECORD_KUNDE_MWST(); }; 
        if (this.fullTableName.equals(_TAB.kred_num_ausschluss.fullTableName)) { return new RECORD_KRED_NUM_AUSSCHLUSS(); }; 
        if (this.fullTableName.equals(_TAB.kreditvers_pos.fullTableName)) { return new RECORD_KREDITVERS_POS(); }; 
        if (this.fullTableName.equals(_TAB.kreditvers_kopf.fullTableName)) { return new RECORD_KREDITVERS_KOPF(); }; 
        if (this.fullTableName.equals(_TAB.kreditvers_adresse.fullTableName)) { return new RECORD_KREDITVERS_ADRESSE(); }; 
        if (this.fullTableName.equals(_TAB.kreditlimit_bedingung.fullTableName)) { return new RECORD_KREDITLIMIT_BEDINGUNG(); }; 
        if (this.fullTableName.equals(_TAB.kostentyp.fullTableName)) { return new RECORD_KOSTENTYP(); }; 
        if (this.fullTableName.equals(_TAB.kosten_lieferbed_def.fullTableName)) { return new RECORD_KOSTEN_LIEFERBED_DEF(); }; 
        if (this.fullTableName.equals(_TAB.kosten_lieferbed_adr.fullTableName)) { return new RECORD_KOSTEN_LIEFERBED_ADR(); }; 
        if (this.fullTableName.equals(_TAB.kosten_artbez_lief.fullTableName)) { return new RECORD_KOSTEN_ARTBEZ_LIEF(); }; 
        if (this.fullTableName.equals(_TAB.korre_abzuege.fullTableName)) { return new RECORD_KORRE_ABZUEGE(); }; 
        if (this.fullTableName.equals(_TAB.konto.fullTableName)) { return new RECORD_KONTO(); }; 
        if (this.fullTableName.equals(_TAB.kommunikations_typ.fullTableName)) { return new RECORD_KOMMUNIKATIONS_TYP(); }; 
        if (this.fullTableName.equals(_TAB.kommunikation.fullTableName)) { return new RECORD_KOMMUNIKATION(); }; 
        if (this.fullTableName.equals(_TAB.kamera_snapshot_grp.fullTableName)) { return new RECORD_KAMERA_SNAPSHOT_GRP(); }; 
        if (this.fullTableName.equals(_TAB.kamera_snapshot_entry.fullTableName)) { return new RECORD_KAMERA_SNAPSHOT_ENTRY(); }; 
        if (this.fullTableName.equals(_TAB.kamera_settings.fullTableName)) { return new RECORD_KAMERA_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.jasperreport_value.fullTableName)) { return new RECORD_JASPERREPORT_VALUE(); }; 
        if (this.fullTableName.equals(_TAB.jasper_def.fullTableName)) { return new RECORD_JASPER_DEF(); }; 
        if (this.fullTableName.equals(_TAB.jasper_compile_chain.fullTableName)) { return new RECORD_JASPER_COMPILE_CHAIN(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_verkehrszweig.fullTableName)) { return new RECORD_INTRASTAT_VERKEHRSZWEIG(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_ursprung_reg.fullTableName)) { return new RECORD_INTRASTAT_URSPRUNG_REG(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_negativliste.fullTableName)) { return new RECORD_INTRASTAT_NEGATIVLISTE(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_meldung.fullTableName)) { return new RECORD_INTRASTAT_MELDUNG(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_land_finanzamt.fullTableName)) { return new RECORD_INTRASTAT_LAND_FINANZAMT(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_land.fullTableName)) { return new RECORD_INTRASTAT_LAND(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_kosten.fullTableName)) { return new RECORD_INTRASTAT_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_geschaeftsart.fullTableName)) { return new RECORD_INTRASTAT_GESCHAEFTSART(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_bestimm_region.fullTableName)) { return new RECORD_INTRASTAT_BESTIMM_REGION(); }; 
        if (this.fullTableName.equals(_TAB.internet_bookmark.fullTableName)) { return new RECORD_INTERNET_BOOKMARK(); }; 
        if (this.fullTableName.equals(_TAB.internet.fullTableName)) { return new RECORD_INTERNET(); }; 
        if (this.fullTableName.equals(_TAB.info_typ.fullTableName)) { return new RECORD_INFO_TYP(); }; 
        if (this.fullTableName.equals(_TAB.info_card_x_user.fullTableName)) { return new RECORD_INFO_CARD_X_USER(); }; 
        if (this.fullTableName.equals(_TAB.info_card_x_typ.fullTableName)) { return new RECORD_INFO_CARD_X_TYP(); }; 
        if (this.fullTableName.equals(_TAB.info_card_x_thema.fullTableName)) { return new RECORD_INFO_CARD_X_THEMA(); }; 
        if (this.fullTableName.equals(_TAB.info_card.fullTableName)) { return new RECORD_INFO_CARD(); }; 
        if (this.fullTableName.equals(_TAB.hilfetext_zu_modul.fullTableName)) { return new RECORD_HILFETEXT_ZU_MODUL(); }; 
        if (this.fullTableName.equals(_TAB.hilfetext.fullTableName)) { return new RECORD_HILFETEXT(); }; 
        if (this.fullTableName.equals(_TAB.help_screen_part.fullTableName)) { return new RECORD_HELP_SCREEN_PART(); }; 
        if (this.fullTableName.equals(_TAB.help_screen.fullTableName)) { return new RECORD_HELP_SCREEN(); }; 
        if (this.fullTableName.equals(_TAB.handelsdef.fullTableName)) { return new RECORD_HANDELSDEF(); }; 
        if (this.fullTableName.equals(_TAB.groovyscript.fullTableName)) { return new RECORD_GROOVYSCRIPT(); }; 
        if (this.fullTableName.equals(_TAB.fuhre_sonder_vl.fullTableName)) { return new RECORD_FUHRE_SONDER_VL(); }; 
        if (this.fullTableName.equals(_TAB.fuhren_rechnungen.fullTableName)) { return new RECORD_FUHREN_RECHNUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.fuhren_kosten_typ.fullTableName)) { return new RECORD_FUHREN_KOSTEN_TYP(); }; 
        if (this.fullTableName.equals(_TAB.fuhren_co2_profil.fullTableName)) { return new RECORD_FUHREN_CO2_PROFIL(); }; 
        if (this.fullTableName.equals(_TAB.fp_pos_akte_grund.fullTableName)) { return new RECORD_FP_POS_AKTE_GRUND(); }; 
        if (this.fullTableName.equals(_TAB.fp_pos_akte.fullTableName)) { return new RECORD_FP_POS_AKTE(); }; 
        if (this.fullTableName.equals(_TAB.firmeninfo.fullTableName)) { return new RECORD_FIRMENINFO(); }; 
        if (this.fullTableName.equals(_TAB.filter_or.fullTableName)) { return new RECORD_FILTER_OR(); }; 
        if (this.fullTableName.equals(_TAB.filter_and.fullTableName)) { return new RECORD_FILTER_AND(); }; 
        if (this.fullTableName.equals(_TAB.filter.fullTableName)) { return new RECORD_FILTER(); }; 
        if (this.fullTableName.equals(_TAB.field_rule_modulekenner.fullTableName)) { return new RECORD_FIELD_RULE_MODULEKENNER(); }; 
        if (this.fullTableName.equals(_TAB.field_rule.fullTableName)) { return new RECORD_FIELD_RULE(); }; 
        if (this.fullTableName.equals(_TAB.fibu_vpos_export.fullTableName)) { return new RECORD_FIBU_VPOS_EXPORT(); }; 
        if (this.fullTableName.equals(_TAB.fibu_vkopf_export.fullTableName)) { return new RECORD_FIBU_VKOPF_EXPORT(); }; 
        if (this.fullTableName.equals(_TAB.fibu_verrech_waehrung.fullTableName)) { return new RECORD_FIBU_VERRECH_WAEHRUNG(); }; 
        if (this.fullTableName.equals(_TAB.fibu_vblock_export.fullTableName)) { return new RECORD_FIBU_VBLOCK_EXPORT(); }; 
        if (this.fullTableName.equals(_TAB.fibu_sachbearbeiter.fullTableName)) { return new RECORD_FIBU_SACHBEARBEITER(); }; 
        if (this.fullTableName.equals(_TAB.fibu_mahnung.fullTableName)) { return new RECORD_FIBU_MAHNUNG(); }; 
        if (this.fullTableName.equals(_TAB.fibu_konto.fullTableName)) { return new RECORD_FIBU_KONTO(); }; 
        if (this.fullTableName.equals(_TAB.fibu_kontenregel_neu.fullTableName)) { return new RECORD_FIBU_KONTENREGEL_NEU(); }; 
        if (this.fullTableName.equals(_TAB.fibu_kontenregel_land.fullTableName)) { return new RECORD_FIBU_KONTENREGEL_LAND(); }; 
        if (this.fullTableName.equals(_TAB.fibu_kontenregel.fullTableName)) { return new RECORD_FIBU_KONTENREGEL(); }; 
        if (this.fullTableName.equals(_TAB.fibu_konstante.fullTableName)) { return new RECORD_FIBU_KONSTANTE(); }; 
        if (this.fullTableName.equals(_TAB.fibu_formular.fullTableName)) { return new RECORD_FIBU_FORMULAR(); }; 
        if (this.fullTableName.equals(_TAB.fibu.fullTableName)) { return new RECORD_FIBU(); }; 
        if (this.fullTableName.equals(_TAB.fbam_user.fullTableName)) { return new RECORD_FBAM_USER(); }; 
        if (this.fullTableName.equals(_TAB.fbam_infoblock.fullTableName)) { return new RECORD_FBAM_INFOBLOCK(); }; 
        if (this.fullTableName.equals(_TAB.fbam_grund.fullTableName)) { return new RECORD_FBAM_GRUND(); }; 
        if (this.fullTableName.equals(_TAB.fbam_feststellung.fullTableName)) { return new RECORD_FBAM_FESTSTELLUNG(); }; 
        if (this.fullTableName.equals(_TAB.fbam_druck_em.fullTableName)) { return new RECORD_FBAM_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.fbam_druck.fullTableName)) { return new RECORD_FBAM_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.fbam_betreff.fullTableName)) { return new RECORD_FBAM_BETREFF(); }; 
        if (this.fullTableName.equals(_TAB.fbam.fullTableName)) { return new RECORD_FBAM(); }; 
        if (this.fullTableName.equals(_TAB.fahrtenvarianten.fullTableName)) { return new RECORD_FAHRTENVARIANTEN(); }; 
        if (this.fullTableName.equals(_TAB.fahrplan_zeitangabe.fullTableName)) { return new RECORD_FAHRPLAN_ZEITANGABE(); }; 
        if (this.fullTableName.equals(_TAB.fahrplanpos.fullTableName)) { return new RECORD_FAHRPLANPOS(); }; 
        if (this.fullTableName.equals(_TAB.export_log.fullTableName)) { return new RECORD_EXPORT_LOG(); }; 
        if (this.fullTableName.equals(_TAB.email_send_targets.fullTableName)) { return new RECORD_EMAIL_SEND_TARGETS(); }; 
        if (this.fullTableName.equals(_TAB.email_send_schablone.fullTableName)) { return new RECORD_EMAIL_SEND_SCHABLONE(); }; 
        if (this.fullTableName.equals(_TAB.email_send_attach.fullTableName)) { return new RECORD_EMAIL_SEND_ATTACH(); }; 
        if (this.fullTableName.equals(_TAB.email_send.fullTableName)) { return new RECORD_EMAIL_SEND(); }; 
        if (this.fullTableName.equals(_TAB.email_protokoll.fullTableName)) { return new RECORD_EMAIL_PROTOKOLL(); }; 
        if (this.fullTableName.equals(_TAB.email_inbox_def.fullTableName)) { return new RECORD_EMAIL_INBOX_DEF(); }; 
        if (this.fullTableName.equals(_TAB.email_inbox.fullTableName)) { return new RECORD_EMAIL_INBOX(); }; 
        if (this.fullTableName.equals(_TAB.email_block.fullTableName)) { return new RECORD_EMAIL_BLOCK(); }; 
        if (this.fullTableName.equals(_TAB.email.fullTableName)) { return new RECORD_EMAIL(); }; 
        if (this.fullTableName.equals(_TAB.element.fullTableName)) { return new RECORD_ELEMENT(); }; 
        if (this.fullTableName.equals(_TAB.ek_vk_bezug.fullTableName)) { return new RECORD_EK_VK_BEZUG(); }; 
        if (this.fullTableName.equals(_TAB.einheit_faktor.fullTableName)) { return new RECORD_EINHEIT_FAKTOR(); }; 
        if (this.fullTableName.equals(_TAB.einheiten_kombinationen.fullTableName)) { return new RECORD_EINHEITEN_KOMBINATIONEN(); }; 
        if (this.fullTableName.equals(_TAB.einheit.fullTableName)) { return new RECORD_EINHEIT(); }; 
        if (this.fullTableName.equals(_TAB.eak_gruppe_sp.fullTableName)) { return new RECORD_EAK_GRUPPE_SP(); }; 
        if (this.fullTableName.equals(_TAB.eak_gruppe.fullTableName)) { return new RECORD_EAK_GRUPPE(); }; 
        if (this.fullTableName.equals(_TAB.eak_code_sp.fullTableName)) { return new RECORD_EAK_CODE_SP(); }; 
        if (this.fullTableName.equals(_TAB.eak_code.fullTableName)) { return new RECORD_EAK_CODE(); }; 
        if (this.fullTableName.equals(_TAB.eak_branche_sp.fullTableName)) { return new RECORD_EAK_BRANCHE_SP(); }; 
        if (this.fullTableName.equals(_TAB.eak_branche.fullTableName)) { return new RECORD_EAK_BRANCHE(); }; 
        if (this.fullTableName.equals(_TAB.drucker_zuordnung.fullTableName)) { return new RECORD_DRUCKER_ZUORDNUNG(); }; 
        if (this.fullTableName.equals(_TAB.drucker.fullTableName)) { return new RECORD_DRUCKER(); }; 
        if (this.fullTableName.equals(_TAB.dlp_profil.fullTableName)) { return new RECORD_DLP_PROFIL(); }; 
        if (this.fullTableName.equals(_TAB.dlp_join_warenbewg.fullTableName)) { return new RECORD_DLP_JOIN_WARENBEWG(); }; 
        if (this.fullTableName.equals(_TAB.dieselpreis.fullTableName)) { return new RECORD_DIESELPREIS(); }; 
        if (this.fullTableName.equals(_TAB.def_sonderzeiten.fullTableName)) { return new RECORD_DEF_SONDERZEITEN(); }; 
        if (this.fullTableName.equals(_TAB.deb_num_ausschluss.fullTableName)) { return new RECORD_DEB_NUM_AUSSCHLUSS(); }; 
        if (this.fullTableName.equals(_TAB.datev_profile.fullTableName)) { return new RECORD_DATEV_PROFILE(); }; 
        if (this.fullTableName.equals(_TAB.container_zyklus.fullTableName)) { return new RECORD_CONTAINER_ZYKLUS(); }; 
        if (this.fullTableName.equals(_TAB.containertyp.fullTableName)) { return new RECORD_CONTAINERTYP(); }; 
        if (this.fullTableName.equals(_TAB.container_station.fullTableName)) { return new RECORD_CONTAINER_STATION(); }; 
        if (this.fullTableName.equals(_TAB.container_sorten_ug.fullTableName)) { return new RECORD_CONTAINER_SORTEN_UG(); }; 
        if (this.fullTableName.equals(_TAB.container_sorten_hg.fullTableName)) { return new RECORD_CONTAINER_SORTEN_HG(); }; 
        if (this.fullTableName.equals(_TAB.container_sorten.fullTableName)) { return new RECORD_CONTAINER_SORTEN(); }; 
        if (this.fullTableName.equals(_TAB.container_absetz_grund.fullTableName)) { return new RECORD_CONTAINER_ABSETZ_GRUND(); }; 
        if (this.fullTableName.equals(_TAB.container.fullTableName)) { return new RECORD_CONTAINER(); }; 
        if (this.fullTableName.equals(_TAB.columns_to_calc.fullTableName)) { return new RECORD_COLUMNS_TO_CALC(); }; 
        if (this.fullTableName.equals(_TAB.collections.fullTableName)) { return new RECORD_COLLECTIONS(); }; 
        if (this.fullTableName.equals(_TAB.collection_def.fullTableName)) { return new RECORD_COLLECTION_DEF(); }; 
        if (this.fullTableName.equals(_TAB.changelog.fullTableName)) { return new RECORD_CHANGELOG(); }; 
        if (this.fullTableName.equals(_TAB.bundesland.fullTableName)) { return new RECORD_BUNDESLAND(); }; 
        if (this.fullTableName.equals(_TAB.branche.fullTableName)) { return new RECORD_BRANCHE(); }; 
        if (this.fullTableName.equals(_TAB.bordercrossing_userinfo.fullTableName)) { return new RECORD_BORDERCROSSING_USERINFO(); }; 
        if (this.fullTableName.equals(_TAB.bordercrossing_artikel.fullTableName)) { return new RECORD_BORDERCROSSING_ARTIKEL(); }; 
        if (this.fullTableName.equals(_TAB.bordercrossing.fullTableName)) { return new RECORD_BORDERCROSSING(); }; 
        if (this.fullTableName.equals(_TAB.bg_vektor_kosten.fullTableName)) { return new RECORD_BG_VEKTOR_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.bg_vektor_konvert.fullTableName)) { return new RECORD_BG_VEKTOR_KONVERT(); }; 
        if (this.fullTableName.equals(_TAB.bg_vektor.fullTableName)) { return new RECORD_BG_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bg_storno_info.fullTableName)) { return new RECORD_BG_STORNO_INFO(); }; 
        if (this.fullTableName.equals(_TAB.bg_station.fullTableName)) { return new RECORD_BG_STATION(); }; 
        if (this.fullTableName.equals(_TAB.bg_rech_block.fullTableName)) { return new RECORD_BG_RECH_BLOCK(); }; 
        if (this.fullTableName.equals(_TAB.bg_pruefprot.fullTableName)) { return new RECORD_BG_PRUEFPROT(); }; 
        if (this.fullTableName.equals(_TAB.bg_fahrplan_to_vektor.fullTableName)) { return new RECORD_BG_FAHRPLAN_TO_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bg_fahrplan.fullTableName)) { return new RECORD_BG_FAHRPLAN(); }; 
        if (this.fullTableName.equals(_TAB.bg_del_info.fullTableName)) { return new RECORD_BG_DEL_INFO(); }; 
        if (this.fullTableName.equals(_TAB.bg_beweg_to_vektor.fullTableName)) { return new RECORD_BG_BEWEG_TO_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bg_beweg.fullTableName)) { return new RECORD_BG_BEWEG(); }; 
        if (this.fullTableName.equals(_TAB.bg_auswert.fullTableName)) { return new RECORD_BG_AUSWERT(); }; 
        if (this.fullTableName.equals(_TAB.bg_atom.fullTableName)) { return new RECORD_BG_ATOM(); }; 
        if (this.fullTableName.equals(_TAB.beziehungsdef.fullTableName)) { return new RECORD_BEZIEHUNGSDEF(); }; 
        if (this.fullTableName.equals(_TAB.beziehung.fullTableName)) { return new RECORD_BEZIEHUNG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor_pos.fullTableName)) { return new RECORD_BEWEGUNG_VEKTOR_POS(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor_pn.fullTableName)) { return new RECORD_BEWEGUNG_VEKTOR_PN(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor_log.fullTableName)) { return new RECORD_BEWEGUNG_VEKTOR_LOG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor.fullTableName)) { return new RECORD_BEWEGUNG_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_trig.fullTableName)) { return new RECORD_BEWEGUNG_TRIG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_station.fullTableName)) { return new RECORD_BEWEGUNG_STATION(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_setzkasten_k.fullTableName)) { return new RECORD_BEWEGUNG_SETZKASTEN_K(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_setzkasten.fullTableName)) { return new RECORD_BEWEGUNG_SETZKASTEN(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_menge.fullTableName)) { return new RECORD_BEWEGUNG_MENGE(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_verbucht_k.fullTableName)) { return new RECORD_BEWEGUNG_ATOM_VERBUCHT_K(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_verbucht.fullTableName)) { return new RECORD_BEWEGUNG_ATOM_VERBUCHT(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_kosten.fullTableName)) { return new RECORD_BEWEGUNG_ATOM_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_abzug.fullTableName)) { return new RECORD_BEWEGUNG_ATOM_ABZUG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom.fullTableName)) { return new RECORD_BEWEGUNG_ATOM(); }; 
        if (this.fullTableName.equals(_TAB.bewegung.fullTableName)) { return new RECORD_BEWEGUNG(); }; 
        if (this.fullTableName.equals(_TAB.besuchsergebnis.fullTableName)) { return new RECORD_BESUCHSERGEBNIS(); }; 
        if (this.fullTableName.equals(_TAB.bestellkopf.fullTableName)) { return new RECORD_BESTELLKOPF(); }; 
        if (this.fullTableName.equals(_TAB.batch_user.fullTableName)) { return new RECORD_BATCH_USER(); }; 
        if (this.fullTableName.equals(_TAB.basel_code.fullTableName)) { return new RECORD_BASEL_CODE(); }; 
        if (this.fullTableName.equals(_TAB.bankenstamm.fullTableName)) { return new RECORD_BANKENSTAMM(); }; 
        if (this.fullTableName.equals(_TAB.bam_import_info.fullTableName)) { return new RECORD_BAM_IMPORT_INFO(); }; 
        if (this.fullTableName.equals(_TAB.bam_import.fullTableName)) { return new RECORD_BAM_IMPORT(); }; 
        if (this.fullTableName.equals(_TAB.artikel_umwandlung.fullTableName)) { return new RECORD_ARTIKEL_UMWANDLUNG(); }; 
        if (this.fullTableName.equals(_TAB.artikel_gruppe_fibu.fullTableName)) { return new RECORD_ARTIKEL_GRUPPE_FIBU(); }; 
        if (this.fullTableName.equals(_TAB.artikel_gruppe.fullTableName)) { return new RECORD_ARTIKEL_GRUPPE(); }; 
        if (this.fullTableName.equals(_TAB.artikel_datenblatt.fullTableName)) { return new RECORD_ARTIKEL_DATENBLATT(); }; 
        if (this.fullTableName.equals(_TAB.artikel_bez_mwst.fullTableName)) { return new RECORD_ARTIKEL_BEZ_MWST(); }; 
        if (this.fullTableName.equals(_TAB.artikelbez_lief.fullTableName)) { return new RECORD_ARTIKELBEZ_LIEF(); }; 
        if (this.fullTableName.equals(_TAB.artikel_bez.fullTableName)) { return new RECORD_ARTIKEL_BEZ(); }; 
        if (this.fullTableName.equals(_TAB.artikel_bereich.fullTableName)) { return new RECORD_ARTIKEL_BEREICH(); }; 
        if (this.fullTableName.equals(_TAB.artikel.fullTableName)) { return new RECORD_ARTIKEL(); }; 
        if (this.fullTableName.equals(_TAB.artbez_verunreinigung.fullTableName)) { return new RECORD_ARTBEZ_VERUNREINIGUNG(); }; 
        if (this.fullTableName.equals(_TAB.artbez_mech_zustand.fullTableName)) { return new RECORD_ARTBEZ_MECH_ZUSTAND(); }; 
        if (this.fullTableName.equals(_TAB.archivmedien.fullTableName)) { return new RECORD_ARCHIVMEDIEN(); }; 
        if (this.fullTableName.equals(_TAB.anrede.fullTableName)) { return new RECORD_ANREDE(); }; 
        if (this.fullTableName.equals(_TAB.aktionsanlass.fullTableName)) { return new RECORD_AKTIONSANLASS(); }; 
        if (this.fullTableName.equals(_TAB.ah7_steuerdatei.fullTableName)) { return new RECORD_AH7_STEUERDATEI(); }; 
        if (this.fullTableName.equals(_TAB.ah7_profil.fullTableName)) { return new RECORD_AH7_PROFIL(); }; 
        if (this.fullTableName.equals(_TAB.adress_zusatzwerte.fullTableName)) { return new RECORD_ADRESS_ZUSATZWERTE(); }; 
        if (this.fullTableName.equals(_TAB.adress_zusatzfeld.fullTableName)) { return new RECORD_ADRESS_ZUSATZFELD(); }; 
        if (this.fullTableName.equals(_TAB.adressklasse_def.fullTableName)) { return new RECORD_ADRESSKLASSE_DEF(); }; 
        if (this.fullTableName.equals(_TAB.adressklasse.fullTableName)) { return new RECORD_ADRESSKLASSE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_zusatzbranche.fullTableName)) { return new RECORD_ADRESSE_ZUSATZBRANCHE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_waehrung.fullTableName)) { return new RECORD_ADRESSE_WAEHRUNG(); }; 
        if (this.fullTableName.equals(_TAB.adresse_ust_id.fullTableName)) { return new RECORD_ADRESSE_UST_ID(); }; 
        if (this.fullTableName.equals(_TAB.adresse_potential.fullTableName)) { return new RECORD_ADRESSE_POTENTIAL(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal5.fullTableName)) { return new RECORD_ADRESSE_MERKMAL5(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal4.fullTableName)) { return new RECORD_ADRESSE_MERKMAL4(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal3.fullTableName)) { return new RECORD_ADRESSE_MERKMAL3(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal2.fullTableName)) { return new RECORD_ADRESSE_MERKMAL2(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal1.fullTableName)) { return new RECORD_ADRESSE_MERKMAL1(); }; 
        if (this.fullTableName.equals(_TAB.adresse_info.fullTableName)) { return new RECORD_ADRESSE_INFO(); }; 
        if (this.fullTableName.equals(_TAB.adresse_geschenk.fullTableName)) { return new RECORD_ADRESSE_GESCHENK(); }; 
        if (this.fullTableName.equals(_TAB.adresse_fahrzeuge.fullTableName)) { return new RECORD_ADRESSE_FAHRZEUGE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_eu_vertr_form.fullTableName)) { return new RECORD_ADRESSE_EU_VERTR_FORM(); }; 
        if (this.fullTableName.equals(_TAB.adresse_eak_code.fullTableName)) { return new RECORD_ADRESSE_EAK_CODE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_artikel.fullTableName)) { return new RECORD_ADRESSE_ARTIKEL(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aqu_rel_wbw_kd.fullTableName)) { return new RECORD_ADRESSE_AQU_REL_WBW_KD(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aqu_rel_kd_sorte.fullTableName)) { return new RECORD_ADRESSE_AQU_REL_KD_SORTE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aquise_akte.fullTableName)) { return new RECORD_ADRESSE_AQUISE_AKTE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aquise.fullTableName)) { return new RECORD_ADRESSE_AQUISE(); }; 
        if (this.fullTableName.equals(_TAB.adresse.fullTableName)) { return new RECORD_ADRESSE(); }; 
        if (this.fullTableName.equals(_TAB.adressausstatt_def.fullTableName)) { return new RECORD_ADRESSAUSSTATT_DEF(); }; 
        if (this.fullTableName.equals(_TAB.adressausstatt.fullTableName)) { return new RECORD_ADRESSAUSSTATT(); }; 
        if (this.fullTableName.equals(_TAB.adr_containertyp.fullTableName)) { return new RECORD_ADR_CONTAINERTYP(); }; 
        if (this.fullTableName.equals(_TAB.abzugsschablonen.fullTableName)) { return new RECORD_ABZUGSSCHABLONEN(); }; 
        if (this.fullTableName.equals(_TAB.abzugsgrund.fullTableName)) { return new RECORD_ABZUGSGRUND(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt_pos.fullTableName)) { return new RECORD_ABRECHBLATT_POS(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt_artbez_out.fullTableName)) { return new RECORD_ABRECHBLATT_ARTBEZ_OUT(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt_artbez_in.fullTableName)) { return new RECORD_ABRECHBLATT_ARTBEZ_IN(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt.fullTableName)) { return new RECORD_ABRECHBLATT(); }; 
        if (this.fullTableName.equals(_TAB.zugriff.fullTableName)) { return new RECORD_ZUGRIFF(); }; 
        if (this.fullTableName.equals(_TAB.waehrungsquery.fullTableName)) { return new RECORD_WAEHRUNGSQUERY(); }; 
        if (this.fullTableName.equals(_TAB.waehrung.fullTableName)) { return new RECORD_WAEHRUNG(); }; 
        if (this.fullTableName.equals(_TAB.version.fullTableName)) { return new RECORD_VERSION(); }; 
        if (this.fullTableName.equals(_TAB.usersettings_jsonbig.fullTableName)) { return new RECORD_USERSETTINGS_JSONBIG(); }; 
        if (this.fullTableName.equals(_TAB.usersettings_json.fullTableName)) { return new RECORD_USERSETTINGS_JSON(); }; 
        if (this.fullTableName.equals(_TAB.usersettings.fullTableName)) { return new RECORD_USERSETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.usergroup.fullTableName)) { return new RECORD_USERGROUP(); }; 
        if (this.fullTableName.equals(_TAB.user_applet.fullTableName)) { return new RECORD_USER_APPLET(); }; 
        if (this.fullTableName.equals(_TAB.user.fullTableName)) { return new RECORD_USER(); }; 
        if (this.fullTableName.equals(_TAB.trigger_def.fullTableName)) { return new RECORD_TRIGGER_DEF(); }; 
        if (this.fullTableName.equals(_TAB.textuebersetzung.fullTableName)) { return new RECORD_TEXTUEBERSETZUNG(); }; 
        if (this.fullTableName.equals(_TAB.textkonstante.fullTableName)) { return new RECORD_TEXTKONSTANTE(); }; 
        if (this.fullTableName.equals(_TAB.tabellenmigration.fullTableName)) { return new RECORD_TABELLENMIGRATION(); }; 
        if (this.fullTableName.equals(_TAB.sprache.fullTableName)) { return new RECORD_SPRACHE(); }; 
        if (this.fullTableName.equals(_TAB.speed_index.fullTableName)) { return new RECORD_SPEED_INDEX(); }; 
        if (this.fullTableName.equals(_TAB.servlets.fullTableName)) { return new RECORD_SERVLETS(); }; 
        if (this.fullTableName.equals(_TAB.reportaktion.fullTableName)) { return new RECORD_REPORTAKTION(); }; 
        if (this.fullTableName.equals(_TAB.registry.fullTableName)) { return new RECORD_REGISTRY(); }; 
        if (this.fullTableName.equals(_TAB.negativlist.fullTableName)) { return new RECORD_NEGATIVLIST(); }; 
        if (this.fullTableName.equals(_TAB.mask_def_cell.fullTableName)) { return new RECORD_MASK_DEF_CELL(); }; 
        if (this.fullTableName.equals(_TAB.mask_def.fullTableName)) { return new RECORD_MASK_DEF(); }; 
        if (this.fullTableName.equals(_TAB.mandant_zusatz_feldnamen.fullTableName)) { return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(); }; 
        if (this.fullTableName.equals(_TAB.mandant_zusatz.fullTableName)) { return new RECORD_MANDANT_ZUSATZ(); }; 
        if (this.fullTableName.equals(_TAB.mandant_steuervermerk.fullTableName)) { return new RECORD_MANDANT_STEUERVERMERK(); }; 
        if (this.fullTableName.equals(_TAB.mandant.fullTableName)) { return new RECORD_MANDANT(); }; 
        if (this.fullTableName.equals(_TAB.login.fullTableName)) { return new RECORD_LOGIN(); }; 
        if (this.fullTableName.equals(_TAB.land.fullTableName)) { return new RECORD_LAND(); }; 
        if (this.fullTableName.equals(_TAB.jasperreport_key.fullTableName)) { return new RECORD_JASPERREPORT_KEY(); }; 
        if (this.fullTableName.equals(_TAB.hauptmenue.fullTableName)) { return new RECORD_HAUPTMENUE(); }; 
        if (this.fullTableName.equals(_TAB.enum_vektor_pos_typ.fullTableName)) { return new RECORD_ENUM_VEKTOR_POS_TYP(); }; 
        if (this.fullTableName.equals(_TAB.enum_bewegung_typ.fullTableName)) { return new RECORD_ENUM_BEWEGUNG_TYP(); }; 
        if (this.fullTableName.equals(_TAB.db_log.fullTableName)) { return new RECORD_DB_LOG(); }; 
        if (this.fullTableName.equals(_TAB.datum.fullTableName)) { return new RECORD_DATUM(); }; 
        if (this.fullTableName.equals(_TAB.button_user.fullTableName)) { return new RECORD_BUTTON_USER(); }; 
        if (this.fullTableName.equals(_TAB.button.fullTableName)) { return new RECORD_BUTTON(); }; 
        if (this.fullTableName.equals(_TAB.batch_task.fullTableName)) { return new RECORD_BATCH_TASK(); }; 
        if (this.fullTableName.equals(_TAB.batch_log.fullTableName)) { return new RECORD_BATCH_LOG(); }; 
        if (this.fullTableName.equals(_TAB.applet.fullTableName)) { return new RECORD_APPLET(); }; 
    throw new myException(this,this.name()+": Table was not found !");
    }



    public MyRECORD_IF_FILLABLE nativeRecordNEW() throws myException {
        if (this.fullTableName.equals(_TAB.zz_rohstoff_alt_leb321k.fullTableName)) { return new RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K(); }; 
        if (this.fullTableName.equals(_TAB.zz_aw_warenstroeme.fullTableName)) { return new RECORDNEW_ZZ_AW_WARENSTROEME(); }; 
        if (this.fullTableName.equals(_TAB.z_test.fullTableName)) { return new RECORDNEW_Z_TEST(); }; 
        if (this.fullTableName.equals(_TAB.zolltarifnummer_import.fullTableName)) { return new RECORDNEW_ZOLLTARIFNUMMER_IMPORT(); }; 
        if (this.fullTableName.equals(_TAB.zolltarifnummer.fullTableName)) { return new RECORDNEW_ZOLLTARIFNUMMER(); }; 
        if (this.fullTableName.equals(_TAB.zollagentur.fullTableName)) { return new RECORDNEW_ZOLLAGENTUR(); }; 
        if (this.fullTableName.equals(_TAB.zahlungsmedium.fullTableName)) { return new RECORDNEW_ZAHLUNGSMEDIUM(); }; 
        if (this.fullTableName.equals(_TAB.zahlungsbedingungen.fullTableName)) { return new RECORDNEW_ZAHLUNGSBEDINGUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_user_befund.fullTableName)) { return new RECORDNEW_WIEGEKARTE_USER_BEFUND(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_mge_abz.fullTableName)) { return new RECORDNEW_WIEGEKARTE_MGE_ABZ(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_gebinde.fullTableName)) { return new RECORDNEW_WIEGEKARTE_GEBINDE(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_container.fullTableName)) { return new RECORDNEW_WIEGEKARTE_CONTAINER(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_befund.fullTableName)) { return new RECORDNEW_WIEGEKARTE_BEFUND(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte_abzug_geb.fullTableName)) { return new RECORDNEW_WIEGEKARTE_ABZUG_GEB(); }; 
        if (this.fullTableName.equals(_TAB.wiegekarte.fullTableName)) { return new RECORDNEW_WIEGEKARTE(); }; 
        if (this.fullTableName.equals(_TAB.waegung.fullTableName)) { return new RECORDNEW_WAEGUNG(); }; 
        if (this.fullTableName.equals(_TAB.waage_user.fullTableName)) { return new RECORDNEW_WAAGE_USER(); }; 
        if (this.fullTableName.equals(_TAB.waage_standort.fullTableName)) { return new RECORDNEW_WAAGE_STANDORT(); }; 
        if (this.fullTableName.equals(_TAB.waage_settings.fullTableName)) { return new RECORDNEW_WAAGE_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.waage_lager.fullTableName)) { return new RECORDNEW_WAAGE_LAGER(); }; 
        if (this.fullTableName.equals(_TAB.waage_hofschein_pruef.fullTableName)) { return new RECORDNEW_WAAGE_HOFSCHEIN_PRUEF(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_vp_ext.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_VP_EXT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_sonder.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_SONDER(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_rgvl.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_RGVL(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort_rgvl.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_ORT_RGVL(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_ORT_ABZUG(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_ort.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_ORT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_kosten.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_druck_em.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_druck.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_abzug_vk.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_ABZUG_VK(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre_abzug_ek.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE_ABZUG_EK(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa_fuhre.fullTableName)) { return new RECORDNEW_VPOS_TPA_FUHRE(); }; 
        if (this.fullTableName.equals(_TAB.vpos_tpa.fullTableName)) { return new RECORDNEW_VPOS_TPA(); }; 
        if (this.fullTableName.equals(_TAB.vpos_std_angebot.fullTableName)) { return new RECORDNEW_VPOS_STD_ANGEBOT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_std.fullTableName)) { return new RECORDNEW_VPOS_STD(); }; 
        if (this.fullTableName.equals(_TAB.vpos_rg_vl.fullTableName)) { return new RECORDNEW_VPOS_RG_VL(); }; 
        if (this.fullTableName.equals(_TAB.vpos_rg_abzug.fullTableName)) { return new RECORDNEW_VPOS_RG_ABZUG(); }; 
        if (this.fullTableName.equals(_TAB.vpos_rg.fullTableName)) { return new RECORDNEW_VPOS_RG(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_trakt.fullTableName)) { return new RECORDNEW_VPOS_KON_TRAKT(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_term.fullTableName)) { return new RECORDNEW_VPOS_KON_TERM(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_lager.fullTableName)) { return new RECORDNEW_VPOS_KON_LAGER(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_avv.fullTableName)) { return new RECORDNEW_VPOS_KON_AVV(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon_aenderungen.fullTableName)) { return new RECORDNEW_VPOS_KON_AENDERUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.vpos_kon.fullTableName)) { return new RECORDNEW_VPOS_KON(); }; 
        if (this.fullTableName.equals(_TAB.vpos_export_rg.fullTableName)) { return new RECORDNEW_VPOS_EXPORT_RG(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_tpa_druck_em.fullTableName)) { return new RECORDNEW_VKOPF_TPA_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_tpa_druck.fullTableName)) { return new RECORDNEW_VKOPF_TPA_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_tpa.fullTableName)) { return new RECORDNEW_VKOPF_TPA(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_std_druck_em.fullTableName)) { return new RECORDNEW_VKOPF_STD_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_std_druck.fullTableName)) { return new RECORDNEW_VKOPF_STD_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_std.fullTableName)) { return new RECORDNEW_VKOPF_STD(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_rg_druck_em.fullTableName)) { return new RECORDNEW_VKOPF_RG_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_rg_druck.fullTableName)) { return new RECORDNEW_VKOPF_RG_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_rg.fullTableName)) { return new RECORDNEW_VKOPF_RG(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_kon_druck_em.fullTableName)) { return new RECORDNEW_VKOPF_KON_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_kon_druck.fullTableName)) { return new RECORDNEW_VKOPF_KON_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_kon.fullTableName)) { return new RECORDNEW_VKOPF_KON(); }; 
        if (this.fullTableName.equals(_TAB.vkopf_export_rg.fullTableName)) { return new RECORDNEW_VKOPF_EXPORT_RG(); }; 
        if (this.fullTableName.equals(_TAB.verpackungsart.fullTableName)) { return new RECORDNEW_VERPACKUNGSART(); }; 
        if (this.fullTableName.equals(_TAB.verarbeitung.fullTableName)) { return new RECORDNEW_VERARBEITUNG(); }; 
        if (this.fullTableName.equals(_TAB.ustid_pruefung.fullTableName)) { return new RECORDNEW_USTID_PRUEFUNG(); }; 
        if (this.fullTableName.equals(_TAB.uma_kontrakt.fullTableName)) { return new RECORDNEW_UMA_KONTRAKT(); }; 
        if (this.fullTableName.equals(_TAB.uma_kon_artb_ruecklief.fullTableName)) { return new RECORDNEW_UMA_KON_ARTB_RUECKLIEF(); }; 
        if (this.fullTableName.equals(_TAB.uma_kon_artb_lief.fullTableName)) { return new RECORDNEW_UMA_KON_ARTB_LIEF(); }; 
        if (this.fullTableName.equals(_TAB.trigger_action_log.fullTableName)) { return new RECORDNEW_TRIGGER_ACTION_LOG(); }; 
        if (this.fullTableName.equals(_TAB.trigger_action_def.fullTableName)) { return new RECORDNEW_TRIGGER_ACTION_DEF(); }; 
        if (this.fullTableName.equals(_TAB.transportmittel.fullTableName)) { return new RECORDNEW_TRANSPORTMITTEL(); }; 
        if (this.fullTableName.equals(_TAB.todo_wichtigkeit.fullTableName)) { return new RECORDNEW_TODO_WICHTIGKEIT(); }; 
        if (this.fullTableName.equals(_TAB.todo_teilnehmer.fullTableName)) { return new RECORDNEW_TODO_TEILNEHMER(); }; 
        if (this.fullTableName.equals(_TAB.todo.fullTableName)) { return new RECORDNEW_TODO(); }; 
        if (this.fullTableName.equals(_TAB.text_liste_vorlage.fullTableName)) { return new RECORDNEW_TEXT_LISTE_VORLAGE(); }; 
        if (this.fullTableName.equals(_TAB.text_liste.fullTableName)) { return new RECORDNEW_TEXT_LISTE(); }; 
        if (this.fullTableName.equals(_TAB.textblock_kat.fullTableName)) { return new RECORDNEW_TEXTBLOCK_KAT(); }; 
        if (this.fullTableName.equals(_TAB.textblock.fullTableName)) { return new RECORDNEW_TEXTBLOCK(); }; 
        if (this.fullTableName.equals(_TAB.testtabelle_handeldef.fullTableName)) { return new RECORDNEW_TESTTABELLE_HANDELDEF(); }; 
        if (this.fullTableName.equals(_TAB.termin_user.fullTableName)) { return new RECORDNEW_TERMIN_USER(); }; 
        if (this.fullTableName.equals(_TAB.termin.fullTableName)) { return new RECORDNEW_TERMIN(); }; 
        if (this.fullTableName.equals(_TAB.temp_text.fullTableName)) { return new RECORDNEW_TEMP_TEXT(); }; 
        if (this.fullTableName.equals(_TAB.temp_imp_sorten.fullTableName)) { return new RECORDNEW_TEMP_IMP_SORTEN(); }; 
        if (this.fullTableName.equals(_TAB.temp_import_def.fullTableName)) { return new RECORDNEW_TEMP_IMPORT_DEF(); }; 
        if (this.fullTableName.equals(_TAB.tax_group.fullTableName)) { return new RECORDNEW_TAX_GROUP(); }; 
        if (this.fullTableName.equals(_TAB.tax_aenderungen.fullTableName)) { return new RECORDNEW_TAX_AENDERUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.tax.fullTableName)) { return new RECORDNEW_TAX(); }; 
        if (this.fullTableName.equals(_TAB.suchvorschlag.fullTableName)) { return new RECORDNEW_SUCHVORSCHLAG(); }; 
        if (this.fullTableName.equals(_TAB.strecken_def.fullTableName)) { return new RECORDNEW_STRECKEN_DEF(); }; 
        if (this.fullTableName.equals(_TAB.status_lager.fullTableName)) { return new RECORDNEW_STATUS_LAGER(); }; 
        if (this.fullTableName.equals(_TAB.status_kunde.fullTableName)) { return new RECORDNEW_STATUS_KUNDE(); }; 
        if (this.fullTableName.equals(_TAB.sonderzeiten.fullTableName)) { return new RECORDNEW_SONDERZEITEN(); }; 
        if (this.fullTableName.equals(_TAB.sheet_def.fullTableName)) { return new RECORDNEW_SHEET_DEF(); }; 
        if (this.fullTableName.equals(_TAB.sheet_content.fullTableName)) { return new RECORDNEW_SHEET_CONTENT(); }; 
        if (this.fullTableName.equals(_TAB.sheet.fullTableName)) { return new RECORDNEW_SHEET(); }; 
        if (this.fullTableName.equals(_TAB.selector.fullTableName)) { return new RECORDNEW_SELECTOR(); }; 
        if (this.fullTableName.equals(_TAB.searchindex.fullTableName)) { return new RECORDNEW_SEARCHINDEX(); }; 
        if (this.fullTableName.equals(_TAB.searchdef.fullTableName)) { return new RECORDNEW_SEARCHDEF(); }; 
        if (this.fullTableName.equals(_TAB.schlagwort.fullTableName)) { return new RECORDNEW_SCHLAGWORT(); }; 
        if (this.fullTableName.equals(_TAB.scanner_user.fullTableName)) { return new RECORDNEW_SCANNER_USER(); }; 
        if (this.fullTableName.equals(_TAB.scanner_settings.fullTableName)) { return new RECORDNEW_SCANNER_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.scanner_group_2_setting.fullTableName)) { return new RECORDNEW_SCANNER_GROUP_2_SETTING(); }; 
        if (this.fullTableName.equals(_TAB.scanner_group.fullTableName)) { return new RECORDNEW_SCANNER_GROUP(); }; 
        if (this.fullTableName.equals(_TAB.sanktion_pruefung_pos.fullTableName)) { return new RECORDNEW_SANKTION_PRUEFUNG_POS(); }; 
        if (this.fullTableName.equals(_TAB.sanktion_pruefung.fullTableName)) { return new RECORDNEW_SANKTION_PRUEFUNG(); }; 
        if (this.fullTableName.equals(_TAB.rep_varianten_param.fullTableName)) { return new RECORDNEW_REP_VARIANTEN_PARAM(); }; 
        if (this.fullTableName.equals(_TAB.rep_varianten.fullTableName)) { return new RECORDNEW_REP_VARIANTEN(); }; 
        if (this.fullTableName.equals(_TAB.report_setting.fullTableName)) { return new RECORDNEW_REPORT_SETTING(); }; 
        if (this.fullTableName.equals(_TAB.report_reiter.fullTableName)) { return new RECORDNEW_REPORT_REITER(); }; 
        if (this.fullTableName.equals(_TAB.report_preparing.fullTableName)) { return new RECORDNEW_REPORT_PREPARING(); }; 
        if (this.fullTableName.equals(_TAB.report_pp_pos_user_incl.fullTableName)) { return new RECORDNEW_REPORT_PP_POS_USER_INCL(); }; 
        if (this.fullTableName.equals(_TAB.report_pp_pos_user_excl.fullTableName)) { return new RECORDNEW_REPORT_PP_POS_USER_EXCL(); }; 
        if (this.fullTableName.equals(_TAB.report_pipeline_pos.fullTableName)) { return new RECORDNEW_REPORT_PIPELINE_POS(); }; 
        if (this.fullTableName.equals(_TAB.report_pipeline.fullTableName)) { return new RECORDNEW_REPORT_PIPELINE(); }; 
        if (this.fullTableName.equals(_TAB.report_parameter.fullTableName)) { return new RECORDNEW_REPORT_PARAMETER(); }; 
        if (this.fullTableName.equals(_TAB.report_log_param.fullTableName)) { return new RECORDNEW_REPORT_LOG_PARAM(); }; 
        if (this.fullTableName.equals(_TAB.report_log.fullTableName)) { return new RECORDNEW_REPORT_LOG(); }; 
        if (this.fullTableName.equals(_TAB.reporting_query_param.fullTableName)) { return new RECORDNEW_REPORTING_QUERY_PARAM(); }; 
        if (this.fullTableName.equals(_TAB.reporting_query_field.fullTableName)) { return new RECORDNEW_REPORTING_QUERY_FIELD(); }; 
        if (this.fullTableName.equals(_TAB.reporting_query.fullTableName)) { return new RECORDNEW_REPORTING_QUERY(); }; 
        if (this.fullTableName.equals(_TAB.report.fullTableName)) { return new RECORDNEW_REPORT(); }; 
        if (this.fullTableName.equals(_TAB.reminder_user.fullTableName)) { return new RECORDNEW_REMINDER_USER(); }; 
        if (this.fullTableName.equals(_TAB.reminder_log.fullTableName)) { return new RECORDNEW_REMINDER_LOG(); }; 
        if (this.fullTableName.equals(_TAB.reminder_def.fullTableName)) { return new RECORDNEW_REMINDER_DEF(); }; 
        if (this.fullTableName.equals(_TAB.rechtsform.fullTableName)) { return new RECORDNEW_RECHTSFORM(); }; 
        if (this.fullTableName.equals(_TAB.query_teilnehmer.fullTableName)) { return new RECORDNEW_QUERY_TEILNEHMER(); }; 
        if (this.fullTableName.equals(_TAB.query.fullTableName)) { return new RECORDNEW_QUERY(); }; 
        if (this.fullTableName.equals(_TAB.qualifier.fullTableName)) { return new RECORDNEW_QUALIFIER(); }; 
        if (this.fullTableName.equals(_TAB.protokolle_batch.fullTableName)) { return new RECORDNEW_PROTOKOLLE_BATCH(); }; 
        if (this.fullTableName.equals(_TAB.pro_mitarb_typ.fullTableName)) { return new RECORDNEW_PRO_MITARB_TYP(); }; 
        if (this.fullTableName.equals(_TAB.pro_mitarb.fullTableName)) { return new RECORDNEW_PRO_MITARB(); }; 
        if (this.fullTableName.equals(_TAB.projektstatusvariante.fullTableName)) { return new RECORDNEW_PROJEKTSTATUSVARIANTE(); }; 
        if (this.fullTableName.equals(_TAB.projektinfo.fullTableName)) { return new RECORDNEW_PROJEKTINFO(); }; 
        if (this.fullTableName.equals(_TAB.projektgewicht.fullTableName)) { return new RECORDNEW_PROJEKTGEWICHT(); }; 
        if (this.fullTableName.equals(_TAB.projekt.fullTableName)) { return new RECORDNEW_PROJEKT(); }; 
        if (this.fullTableName.equals(_TAB.proforma_rechnung.fullTableName)) { return new RECORDNEW_PROFORMA_RECHNUNG(); }; 
        if (this.fullTableName.equals(_TAB.profil_grenzubertritt.fullTableName)) { return new RECORDNEW_PROFIL_GRENZUBERTRITT(); }; 
        if (this.fullTableName.equals(_TAB.pro_adressen.fullTableName)) { return new RECORDNEW_PRO_ADRESSEN(); }; 
        if (this.fullTableName.equals(_TAB.preisinfo_temp.fullTableName)) { return new RECORDNEW_PREISINFO_TEMP(); }; 
        if (this.fullTableName.equals(_TAB.plz_bundesland.fullTableName)) { return new RECORDNEW_PLZ_BUNDESLAND(); }; 
        if (this.fullTableName.equals(_TAB.offene_adr_trans_pap.fullTableName)) { return new RECORDNEW_OFFENE_ADR_TRANS_PAP(); }; 
        if (this.fullTableName.equals(_TAB.oecd_code.fullTableName)) { return new RECORDNEW_OECD_CODE(); }; 
        if (this.fullTableName.equals(_TAB.nullpreis.fullTableName)) { return new RECORDNEW_NULLPREIS(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_mail_settings.fullTableName)) { return new RECORDNEW_NACHRICHT_MAIL_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_mail_log.fullTableName)) { return new RECORDNEW_NACHRICHT_MAIL_LOG(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_kategorie.fullTableName)) { return new RECORDNEW_NACHRICHT_KATEGORIE(); }; 
        if (this.fullTableName.equals(_TAB.nachricht_kat_default.fullTableName)) { return new RECORDNEW_NACHRICHT_KAT_DEFAULT(); }; 
        if (this.fullTableName.equals(_TAB.nachricht.fullTableName)) { return new RECORDNEW_NACHRICHT(); }; 
        if (this.fullTableName.equals(_TAB.m2n.fullTableName)) { return new RECORDNEW_M2N(); }; 
        if (this.fullTableName.equals(_TAB.mwstschluessel_aenderungen.fullTableName)) { return new RECORDNEW_MWSTSCHLUESSEL_AENDERUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.mwstschluessel.fullTableName)) { return new RECORDNEW_MWSTSCHLUESSEL(); }; 
        if (this.fullTableName.equals(_TAB.modul_connect.fullTableName)) { return new RECORDNEW_MODUL_CONNECT(); }; 
        if (this.fullTableName.equals(_TAB.mitarbeitertyp.fullTableName)) { return new RECORDNEW_MITARBEITERTYP(); }; 
        if (this.fullTableName.equals(_TAB.mitarbeiter.fullTableName)) { return new RECORDNEW_MITARBEITER(); }; 
        if (this.fullTableName.equals(_TAB.message_provider.fullTableName)) { return new RECORDNEW_MESSAGE_PROVIDER(); }; 
        if (this.fullTableName.equals(_TAB.medientyp.fullTableName)) { return new RECORDNEW_MEDIENTYP(); }; 
        if (this.fullTableName.equals(_TAB.medien.fullTableName)) { return new RECORDNEW_MEDIEN(); }; 
        if (this.fullTableName.equals(_TAB.mat_spez.fullTableName)) { return new RECORDNEW_MAT_SPEZ(); }; 
        if (this.fullTableName.equals(_TAB.mat_element.fullTableName)) { return new RECORDNEW_MAT_ELEMENT(); }; 
        if (this.fullTableName.equals(_TAB.maschinentyp.fullTableName)) { return new RECORDNEW_MASCHINENTYP(); }; 
        if (this.fullTableName.equals(_TAB.maschinen_kosten.fullTableName)) { return new RECORDNEW_MASCHINEN_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.maschinen_aufgabe_typ.fullTableName)) { return new RECORDNEW_MASCHINEN_AUFGABE_TYP(); }; 
        if (this.fullTableName.equals(_TAB.maschinen_aufgabe.fullTableName)) { return new RECORDNEW_MASCHINEN_AUFGABE(); }; 
        if (this.fullTableName.equals(_TAB.maschinen.fullTableName)) { return new RECORDNEW_MASCHINEN(); }; 
        if (this.fullTableName.equals(_TAB.mandant_decision.fullTableName)) { return new RECORDNEW_MANDANT_DECISION(); }; 
        if (this.fullTableName.equals(_TAB.mandant_config.fullTableName)) { return new RECORDNEW_MANDANT_CONFIG(); }; 
        if (this.fullTableName.equals(_TAB.mailing.fullTableName)) { return new RECORDNEW_MAILING(); }; 
        if (this.fullTableName.equals(_TAB.mail_aus_mask_jasper.fullTableName)) { return new RECORDNEW_MAIL_AUS_MASK_JASPER(); }; 
        if (this.fullTableName.equals(_TAB.mail_aus_mask_email.fullTableName)) { return new RECORDNEW_MAIL_AUS_MASK_EMAIL(); }; 
        if (this.fullTableName.equals(_TAB.mail_aus_mask.fullTableName)) { return new RECORDNEW_MAIL_AUS_MASK(); }; 
        if (this.fullTableName.equals(_TAB.mahnung_pos.fullTableName)) { return new RECORDNEW_MAHNUNG_POS(); }; 
        if (this.fullTableName.equals(_TAB.mahnung.fullTableName)) { return new RECORDNEW_MAHNUNG(); }; 
        if (this.fullTableName.equals(_TAB.lock.fullTableName)) { return new RECORDNEW_LOCK(); }; 
        if (this.fullTableName.equals(_TAB.listen_zusatzfelder.fullTableName)) { return new RECORDNEW_LISTEN_ZUSATZFELDER(); }; 
        if (this.fullTableName.equals(_TAB.lieferbed_kosten.fullTableName)) { return new RECORDNEW_LIEFERBED_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.lieferbedingungen.fullTableName)) { return new RECORDNEW_LIEFERBEDINGUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.lieferadresse.fullTableName)) { return new RECORDNEW_LIEFERADRESSE(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_teilnehmer.fullTableName)) { return new RECORDNEW_LAUFZETTEL_TEILNEHMER(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_status.fullTableName)) { return new RECORDNEW_LAUFZETTEL_STATUS(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_prio.fullTableName)) { return new RECORDNEW_LAUFZETTEL_PRIO(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel_eintrag.fullTableName)) { return new RECORDNEW_LAUFZETTEL_EINTRAG(); }; 
        if (this.fullTableName.equals(_TAB.laufzettel.fullTableName)) { return new RECORDNEW_LAUFZETTEL(); }; 
        if (this.fullTableName.equals(_TAB.land_rc_sorten.fullTableName)) { return new RECORDNEW_LAND_RC_SORTEN(); }; 
        if (this.fullTableName.equals(_TAB.lagerplatz_typ.fullTableName)) { return new RECORDNEW_LAGERPLATZ_TYP(); }; 
        if (this.fullTableName.equals(_TAB.lagerplatz.fullTableName)) { return new RECORDNEW_LAGERPLATZ(); }; 
        if (this.fullTableName.equals(_TAB.lager_palette_user.fullTableName)) { return new RECORDNEW_LAGER_PALETTE_USER(); }; 
        if (this.fullTableName.equals(_TAB.lager_palette_box.fullTableName)) { return new RECORDNEW_LAGER_PALETTE_BOX(); }; 
        if (this.fullTableName.equals(_TAB.lager_palette.fullTableName)) { return new RECORDNEW_LAGER_PALETTE(); }; 
        if (this.fullTableName.equals(_TAB.lager_korr_st.fullTableName)) { return new RECORDNEW_LAGER_KORR_ST(); }; 
        if (this.fullTableName.equals(_TAB.lager_konto.fullTableName)) { return new RECORDNEW_LAGER_KONTO(); }; 
        if (this.fullTableName.equals(_TAB.lager_inventur.fullTableName)) { return new RECORDNEW_LAGER_INVENTUR(); }; 
        if (this.fullTableName.equals(_TAB.lager_box.fullTableName)) { return new RECORDNEW_LAGER_BOX(); }; 
        if (this.fullTableName.equals(_TAB.lager_bewegung.fullTableName)) { return new RECORDNEW_LAGER_BEWEGUNG(); }; 
        if (this.fullTableName.equals(_TAB.kunde_mwst.fullTableName)) { return new RECORDNEW_KUNDE_MWST(); }; 
        if (this.fullTableName.equals(_TAB.kred_num_ausschluss.fullTableName)) { return new RECORDNEW_KRED_NUM_AUSSCHLUSS(); }; 
        if (this.fullTableName.equals(_TAB.kreditvers_pos.fullTableName)) { return new RECORDNEW_KREDITVERS_POS(); }; 
        if (this.fullTableName.equals(_TAB.kreditvers_kopf.fullTableName)) { return new RECORDNEW_KREDITVERS_KOPF(); }; 
        if (this.fullTableName.equals(_TAB.kreditvers_adresse.fullTableName)) { return new RECORDNEW_KREDITVERS_ADRESSE(); }; 
        if (this.fullTableName.equals(_TAB.kreditlimit_bedingung.fullTableName)) { return new RECORDNEW_KREDITLIMIT_BEDINGUNG(); }; 
        if (this.fullTableName.equals(_TAB.kostentyp.fullTableName)) { return new RECORDNEW_KOSTENTYP(); }; 
        if (this.fullTableName.equals(_TAB.kosten_lieferbed_def.fullTableName)) { return new RECORDNEW_KOSTEN_LIEFERBED_DEF(); }; 
        if (this.fullTableName.equals(_TAB.kosten_lieferbed_adr.fullTableName)) { return new RECORDNEW_KOSTEN_LIEFERBED_ADR(); }; 
        if (this.fullTableName.equals(_TAB.kosten_artbez_lief.fullTableName)) { return new RECORDNEW_KOSTEN_ARTBEZ_LIEF(); }; 
        if (this.fullTableName.equals(_TAB.korre_abzuege.fullTableName)) { return new RECORDNEW_KORRE_ABZUEGE(); }; 
        if (this.fullTableName.equals(_TAB.konto.fullTableName)) { return new RECORDNEW_KONTO(); }; 
        if (this.fullTableName.equals(_TAB.kommunikations_typ.fullTableName)) { return new RECORDNEW_KOMMUNIKATIONS_TYP(); }; 
        if (this.fullTableName.equals(_TAB.kommunikation.fullTableName)) { return new RECORDNEW_KOMMUNIKATION(); }; 
        if (this.fullTableName.equals(_TAB.kamera_snapshot_grp.fullTableName)) { return new RECORDNEW_KAMERA_SNAPSHOT_GRP(); }; 
        if (this.fullTableName.equals(_TAB.kamera_snapshot_entry.fullTableName)) { return new RECORDNEW_KAMERA_SNAPSHOT_ENTRY(); }; 
        if (this.fullTableName.equals(_TAB.kamera_settings.fullTableName)) { return new RECORDNEW_KAMERA_SETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.jasperreport_value.fullTableName)) { return new RECORDNEW_JASPERREPORT_VALUE(); }; 
        if (this.fullTableName.equals(_TAB.jasper_def.fullTableName)) { return new RECORDNEW_JASPER_DEF(); }; 
        if (this.fullTableName.equals(_TAB.jasper_compile_chain.fullTableName)) { return new RECORDNEW_JASPER_COMPILE_CHAIN(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_verkehrszweig.fullTableName)) { return new RECORDNEW_INTRASTAT_VERKEHRSZWEIG(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_ursprung_reg.fullTableName)) { return new RECORDNEW_INTRASTAT_URSPRUNG_REG(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_negativliste.fullTableName)) { return new RECORDNEW_INTRASTAT_NEGATIVLISTE(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_meldung.fullTableName)) { return new RECORDNEW_INTRASTAT_MELDUNG(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_land_finanzamt.fullTableName)) { return new RECORDNEW_INTRASTAT_LAND_FINANZAMT(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_land.fullTableName)) { return new RECORDNEW_INTRASTAT_LAND(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_kosten.fullTableName)) { return new RECORDNEW_INTRASTAT_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_geschaeftsart.fullTableName)) { return new RECORDNEW_INTRASTAT_GESCHAEFTSART(); }; 
        if (this.fullTableName.equals(_TAB.intrastat_bestimm_region.fullTableName)) { return new RECORDNEW_INTRASTAT_BESTIMM_REGION(); }; 
        if (this.fullTableName.equals(_TAB.internet_bookmark.fullTableName)) { return new RECORDNEW_INTERNET_BOOKMARK(); }; 
        if (this.fullTableName.equals(_TAB.internet.fullTableName)) { return new RECORDNEW_INTERNET(); }; 
        if (this.fullTableName.equals(_TAB.info_typ.fullTableName)) { return new RECORDNEW_INFO_TYP(); }; 
        if (this.fullTableName.equals(_TAB.info_card_x_user.fullTableName)) { return new RECORDNEW_INFO_CARD_X_USER(); }; 
        if (this.fullTableName.equals(_TAB.info_card_x_typ.fullTableName)) { return new RECORDNEW_INFO_CARD_X_TYP(); }; 
        if (this.fullTableName.equals(_TAB.info_card_x_thema.fullTableName)) { return new RECORDNEW_INFO_CARD_X_THEMA(); }; 
        if (this.fullTableName.equals(_TAB.info_card.fullTableName)) { return new RECORDNEW_INFO_CARD(); }; 
        if (this.fullTableName.equals(_TAB.hilfetext_zu_modul.fullTableName)) { return new RECORDNEW_HILFETEXT_ZU_MODUL(); }; 
        if (this.fullTableName.equals(_TAB.hilfetext.fullTableName)) { return new RECORDNEW_HILFETEXT(); }; 
        if (this.fullTableName.equals(_TAB.help_screen_part.fullTableName)) { return new RECORDNEW_HELP_SCREEN_PART(); }; 
        if (this.fullTableName.equals(_TAB.help_screen.fullTableName)) { return new RECORDNEW_HELP_SCREEN(); }; 
        if (this.fullTableName.equals(_TAB.handelsdef.fullTableName)) { return new RECORDNEW_HANDELSDEF(); }; 
        if (this.fullTableName.equals(_TAB.groovyscript.fullTableName)) { return new RECORDNEW_GROOVYSCRIPT(); }; 
        if (this.fullTableName.equals(_TAB.fuhre_sonder_vl.fullTableName)) { return new RECORDNEW_FUHRE_SONDER_VL(); }; 
        if (this.fullTableName.equals(_TAB.fuhren_rechnungen.fullTableName)) { return new RECORDNEW_FUHREN_RECHNUNGEN(); }; 
        if (this.fullTableName.equals(_TAB.fuhren_kosten_typ.fullTableName)) { return new RECORDNEW_FUHREN_KOSTEN_TYP(); }; 
        if (this.fullTableName.equals(_TAB.fuhren_co2_profil.fullTableName)) { return new RECORDNEW_FUHREN_CO2_PROFIL(); }; 
        if (this.fullTableName.equals(_TAB.fp_pos_akte_grund.fullTableName)) { return new RECORDNEW_FP_POS_AKTE_GRUND(); }; 
        if (this.fullTableName.equals(_TAB.fp_pos_akte.fullTableName)) { return new RECORDNEW_FP_POS_AKTE(); }; 
        if (this.fullTableName.equals(_TAB.firmeninfo.fullTableName)) { return new RECORDNEW_FIRMENINFO(); }; 
        if (this.fullTableName.equals(_TAB.filter_or.fullTableName)) { return new RECORDNEW_FILTER_OR(); }; 
        if (this.fullTableName.equals(_TAB.filter_and.fullTableName)) { return new RECORDNEW_FILTER_AND(); }; 
        if (this.fullTableName.equals(_TAB.filter.fullTableName)) { return new RECORDNEW_FILTER(); }; 
        if (this.fullTableName.equals(_TAB.field_rule_modulekenner.fullTableName)) { return new RECORDNEW_FIELD_RULE_MODULEKENNER(); }; 
        if (this.fullTableName.equals(_TAB.field_rule.fullTableName)) { return new RECORDNEW_FIELD_RULE(); }; 
        if (this.fullTableName.equals(_TAB.fibu_vpos_export.fullTableName)) { return new RECORDNEW_FIBU_VPOS_EXPORT(); }; 
        if (this.fullTableName.equals(_TAB.fibu_vkopf_export.fullTableName)) { return new RECORDNEW_FIBU_VKOPF_EXPORT(); }; 
        if (this.fullTableName.equals(_TAB.fibu_verrech_waehrung.fullTableName)) { return new RECORDNEW_FIBU_VERRECH_WAEHRUNG(); }; 
        if (this.fullTableName.equals(_TAB.fibu_vblock_export.fullTableName)) { return new RECORDNEW_FIBU_VBLOCK_EXPORT(); }; 
        if (this.fullTableName.equals(_TAB.fibu_sachbearbeiter.fullTableName)) { return new RECORDNEW_FIBU_SACHBEARBEITER(); }; 
        if (this.fullTableName.equals(_TAB.fibu_mahnung.fullTableName)) { return new RECORDNEW_FIBU_MAHNUNG(); }; 
        if (this.fullTableName.equals(_TAB.fibu_konto.fullTableName)) { return new RECORDNEW_FIBU_KONTO(); }; 
        if (this.fullTableName.equals(_TAB.fibu_kontenregel_neu.fullTableName)) { return new RECORDNEW_FIBU_KONTENREGEL_NEU(); }; 
        if (this.fullTableName.equals(_TAB.fibu_kontenregel_land.fullTableName)) { return new RECORDNEW_FIBU_KONTENREGEL_LAND(); }; 
        if (this.fullTableName.equals(_TAB.fibu_kontenregel.fullTableName)) { return new RECORDNEW_FIBU_KONTENREGEL(); }; 
        if (this.fullTableName.equals(_TAB.fibu_konstante.fullTableName)) { return new RECORDNEW_FIBU_KONSTANTE(); }; 
        if (this.fullTableName.equals(_TAB.fibu_formular.fullTableName)) { return new RECORDNEW_FIBU_FORMULAR(); }; 
        if (this.fullTableName.equals(_TAB.fibu.fullTableName)) { return new RECORDNEW_FIBU(); }; 
        if (this.fullTableName.equals(_TAB.fbam_user.fullTableName)) { return new RECORDNEW_FBAM_USER(); }; 
        if (this.fullTableName.equals(_TAB.fbam_infoblock.fullTableName)) { return new RECORDNEW_FBAM_INFOBLOCK(); }; 
        if (this.fullTableName.equals(_TAB.fbam_grund.fullTableName)) { return new RECORDNEW_FBAM_GRUND(); }; 
        if (this.fullTableName.equals(_TAB.fbam_feststellung.fullTableName)) { return new RECORDNEW_FBAM_FESTSTELLUNG(); }; 
        if (this.fullTableName.equals(_TAB.fbam_druck_em.fullTableName)) { return new RECORDNEW_FBAM_DRUCK_EM(); }; 
        if (this.fullTableName.equals(_TAB.fbam_druck.fullTableName)) { return new RECORDNEW_FBAM_DRUCK(); }; 
        if (this.fullTableName.equals(_TAB.fbam_betreff.fullTableName)) { return new RECORDNEW_FBAM_BETREFF(); }; 
        if (this.fullTableName.equals(_TAB.fbam.fullTableName)) { return new RECORDNEW_FBAM(); }; 
        if (this.fullTableName.equals(_TAB.fahrtenvarianten.fullTableName)) { return new RECORDNEW_FAHRTENVARIANTEN(); }; 
        if (this.fullTableName.equals(_TAB.fahrplan_zeitangabe.fullTableName)) { return new RECORDNEW_FAHRPLAN_ZEITANGABE(); }; 
        if (this.fullTableName.equals(_TAB.fahrplanpos.fullTableName)) { return new RECORDNEW_FAHRPLANPOS(); }; 
        if (this.fullTableName.equals(_TAB.export_log.fullTableName)) { return new RECORDNEW_EXPORT_LOG(); }; 
        if (this.fullTableName.equals(_TAB.email_send_targets.fullTableName)) { return new RECORDNEW_EMAIL_SEND_TARGETS(); }; 
        if (this.fullTableName.equals(_TAB.email_send_schablone.fullTableName)) { return new RECORDNEW_EMAIL_SEND_SCHABLONE(); }; 
        if (this.fullTableName.equals(_TAB.email_send_attach.fullTableName)) { return new RECORDNEW_EMAIL_SEND_ATTACH(); }; 
        if (this.fullTableName.equals(_TAB.email_send.fullTableName)) { return new RECORDNEW_EMAIL_SEND(); }; 
        if (this.fullTableName.equals(_TAB.email_protokoll.fullTableName)) { return new RECORDNEW_EMAIL_PROTOKOLL(); }; 
        if (this.fullTableName.equals(_TAB.email_inbox_def.fullTableName)) { return new RECORDNEW_EMAIL_INBOX_DEF(); }; 
        if (this.fullTableName.equals(_TAB.email_inbox.fullTableName)) { return new RECORDNEW_EMAIL_INBOX(); }; 
        if (this.fullTableName.equals(_TAB.email_block.fullTableName)) { return new RECORDNEW_EMAIL_BLOCK(); }; 
        if (this.fullTableName.equals(_TAB.email.fullTableName)) { return new RECORDNEW_EMAIL(); }; 
        if (this.fullTableName.equals(_TAB.element.fullTableName)) { return new RECORDNEW_ELEMENT(); }; 
        if (this.fullTableName.equals(_TAB.ek_vk_bezug.fullTableName)) { return new RECORDNEW_EK_VK_BEZUG(); }; 
        if (this.fullTableName.equals(_TAB.einheit_faktor.fullTableName)) { return new RECORDNEW_EINHEIT_FAKTOR(); }; 
        if (this.fullTableName.equals(_TAB.einheiten_kombinationen.fullTableName)) { return new RECORDNEW_EINHEITEN_KOMBINATIONEN(); }; 
        if (this.fullTableName.equals(_TAB.einheit.fullTableName)) { return new RECORDNEW_EINHEIT(); }; 
        if (this.fullTableName.equals(_TAB.eak_gruppe_sp.fullTableName)) { return new RECORDNEW_EAK_GRUPPE_SP(); }; 
        if (this.fullTableName.equals(_TAB.eak_gruppe.fullTableName)) { return new RECORDNEW_EAK_GRUPPE(); }; 
        if (this.fullTableName.equals(_TAB.eak_code_sp.fullTableName)) { return new RECORDNEW_EAK_CODE_SP(); }; 
        if (this.fullTableName.equals(_TAB.eak_code.fullTableName)) { return new RECORDNEW_EAK_CODE(); }; 
        if (this.fullTableName.equals(_TAB.eak_branche_sp.fullTableName)) { return new RECORDNEW_EAK_BRANCHE_SP(); }; 
        if (this.fullTableName.equals(_TAB.eak_branche.fullTableName)) { return new RECORDNEW_EAK_BRANCHE(); }; 
        if (this.fullTableName.equals(_TAB.drucker_zuordnung.fullTableName)) { return new RECORDNEW_DRUCKER_ZUORDNUNG(); }; 
        if (this.fullTableName.equals(_TAB.drucker.fullTableName)) { return new RECORDNEW_DRUCKER(); }; 
        if (this.fullTableName.equals(_TAB.dlp_profil.fullTableName)) { return new RECORDNEW_DLP_PROFIL(); }; 
        if (this.fullTableName.equals(_TAB.dlp_join_warenbewg.fullTableName)) { return new RECORDNEW_DLP_JOIN_WARENBEWG(); }; 
        if (this.fullTableName.equals(_TAB.dieselpreis.fullTableName)) { return new RECORDNEW_DIESELPREIS(); }; 
        if (this.fullTableName.equals(_TAB.def_sonderzeiten.fullTableName)) { return new RECORDNEW_DEF_SONDERZEITEN(); }; 
        if (this.fullTableName.equals(_TAB.deb_num_ausschluss.fullTableName)) { return new RECORDNEW_DEB_NUM_AUSSCHLUSS(); }; 
        if (this.fullTableName.equals(_TAB.datev_profile.fullTableName)) { return new RECORDNEW_DATEV_PROFILE(); }; 
        if (this.fullTableName.equals(_TAB.container_zyklus.fullTableName)) { return new RECORDNEW_CONTAINER_ZYKLUS(); }; 
        if (this.fullTableName.equals(_TAB.containertyp.fullTableName)) { return new RECORDNEW_CONTAINERTYP(); }; 
        if (this.fullTableName.equals(_TAB.container_station.fullTableName)) { return new RECORDNEW_CONTAINER_STATION(); }; 
        if (this.fullTableName.equals(_TAB.container_sorten_ug.fullTableName)) { return new RECORDNEW_CONTAINER_SORTEN_UG(); }; 
        if (this.fullTableName.equals(_TAB.container_sorten_hg.fullTableName)) { return new RECORDNEW_CONTAINER_SORTEN_HG(); }; 
        if (this.fullTableName.equals(_TAB.container_sorten.fullTableName)) { return new RECORDNEW_CONTAINER_SORTEN(); }; 
        if (this.fullTableName.equals(_TAB.container_absetz_grund.fullTableName)) { return new RECORDNEW_CONTAINER_ABSETZ_GRUND(); }; 
        if (this.fullTableName.equals(_TAB.container.fullTableName)) { return new RECORDNEW_CONTAINER(); }; 
        if (this.fullTableName.equals(_TAB.columns_to_calc.fullTableName)) { return new RECORDNEW_COLUMNS_TO_CALC(); }; 
        if (this.fullTableName.equals(_TAB.collections.fullTableName)) { return new RECORDNEW_COLLECTIONS(); }; 
        if (this.fullTableName.equals(_TAB.collection_def.fullTableName)) { return new RECORDNEW_COLLECTION_DEF(); }; 
        if (this.fullTableName.equals(_TAB.changelog.fullTableName)) { return new RECORDNEW_CHANGELOG(); }; 
        if (this.fullTableName.equals(_TAB.bundesland.fullTableName)) { return new RECORDNEW_BUNDESLAND(); }; 
        if (this.fullTableName.equals(_TAB.branche.fullTableName)) { return new RECORDNEW_BRANCHE(); }; 
        if (this.fullTableName.equals(_TAB.bordercrossing_userinfo.fullTableName)) { return new RECORDNEW_BORDERCROSSING_USERINFO(); }; 
        if (this.fullTableName.equals(_TAB.bordercrossing_artikel.fullTableName)) { return new RECORDNEW_BORDERCROSSING_ARTIKEL(); }; 
        if (this.fullTableName.equals(_TAB.bordercrossing.fullTableName)) { return new RECORDNEW_BORDERCROSSING(); }; 
        if (this.fullTableName.equals(_TAB.bg_vektor_kosten.fullTableName)) { return new RECORDNEW_BG_VEKTOR_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.bg_vektor_konvert.fullTableName)) { return new RECORDNEW_BG_VEKTOR_KONVERT(); }; 
        if (this.fullTableName.equals(_TAB.bg_vektor.fullTableName)) { return new RECORDNEW_BG_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bg_storno_info.fullTableName)) { return new RECORDNEW_BG_STORNO_INFO(); }; 
        if (this.fullTableName.equals(_TAB.bg_station.fullTableName)) { return new RECORDNEW_BG_STATION(); }; 
        if (this.fullTableName.equals(_TAB.bg_rech_block.fullTableName)) { return new RECORDNEW_BG_RECH_BLOCK(); }; 
        if (this.fullTableName.equals(_TAB.bg_pruefprot.fullTableName)) { return new RECORDNEW_BG_PRUEFPROT(); }; 
        if (this.fullTableName.equals(_TAB.bg_fahrplan_to_vektor.fullTableName)) { return new RECORDNEW_BG_FAHRPLAN_TO_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bg_fahrplan.fullTableName)) { return new RECORDNEW_BG_FAHRPLAN(); }; 
        if (this.fullTableName.equals(_TAB.bg_del_info.fullTableName)) { return new RECORDNEW_BG_DEL_INFO(); }; 
        if (this.fullTableName.equals(_TAB.bg_beweg_to_vektor.fullTableName)) { return new RECORDNEW_BG_BEWEG_TO_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bg_beweg.fullTableName)) { return new RECORDNEW_BG_BEWEG(); }; 
        if (this.fullTableName.equals(_TAB.bg_auswert.fullTableName)) { return new RECORDNEW_BG_AUSWERT(); }; 
        if (this.fullTableName.equals(_TAB.bg_atom.fullTableName)) { return new RECORDNEW_BG_ATOM(); }; 
        if (this.fullTableName.equals(_TAB.beziehungsdef.fullTableName)) { return new RECORDNEW_BEZIEHUNGSDEF(); }; 
        if (this.fullTableName.equals(_TAB.beziehung.fullTableName)) { return new RECORDNEW_BEZIEHUNG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor_pos.fullTableName)) { return new RECORDNEW_BEWEGUNG_VEKTOR_POS(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor_pn.fullTableName)) { return new RECORDNEW_BEWEGUNG_VEKTOR_PN(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor_log.fullTableName)) { return new RECORDNEW_BEWEGUNG_VEKTOR_LOG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_vektor.fullTableName)) { return new RECORDNEW_BEWEGUNG_VEKTOR(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_trig.fullTableName)) { return new RECORDNEW_BEWEGUNG_TRIG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_station.fullTableName)) { return new RECORDNEW_BEWEGUNG_STATION(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_setzkasten_k.fullTableName)) { return new RECORDNEW_BEWEGUNG_SETZKASTEN_K(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_setzkasten.fullTableName)) { return new RECORDNEW_BEWEGUNG_SETZKASTEN(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_menge.fullTableName)) { return new RECORDNEW_BEWEGUNG_MENGE(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_verbucht_k.fullTableName)) { return new RECORDNEW_BEWEGUNG_ATOM_VERBUCHT_K(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_verbucht.fullTableName)) { return new RECORDNEW_BEWEGUNG_ATOM_VERBUCHT(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_kosten.fullTableName)) { return new RECORDNEW_BEWEGUNG_ATOM_KOSTEN(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom_abzug.fullTableName)) { return new RECORDNEW_BEWEGUNG_ATOM_ABZUG(); }; 
        if (this.fullTableName.equals(_TAB.bewegung_atom.fullTableName)) { return new RECORDNEW_BEWEGUNG_ATOM(); }; 
        if (this.fullTableName.equals(_TAB.bewegung.fullTableName)) { return new RECORDNEW_BEWEGUNG(); }; 
        if (this.fullTableName.equals(_TAB.besuchsergebnis.fullTableName)) { return new RECORDNEW_BESUCHSERGEBNIS(); }; 
        if (this.fullTableName.equals(_TAB.bestellkopf.fullTableName)) { return new RECORDNEW_BESTELLKOPF(); }; 
        if (this.fullTableName.equals(_TAB.batch_user.fullTableName)) { return new RECORDNEW_BATCH_USER(); }; 
        if (this.fullTableName.equals(_TAB.basel_code.fullTableName)) { return new RECORDNEW_BASEL_CODE(); }; 
        if (this.fullTableName.equals(_TAB.bankenstamm.fullTableName)) { return new RECORDNEW_BANKENSTAMM(); }; 
        if (this.fullTableName.equals(_TAB.bam_import_info.fullTableName)) { return new RECORDNEW_BAM_IMPORT_INFO(); }; 
        if (this.fullTableName.equals(_TAB.bam_import.fullTableName)) { return new RECORDNEW_BAM_IMPORT(); }; 
        if (this.fullTableName.equals(_TAB.artikel_umwandlung.fullTableName)) { return new RECORDNEW_ARTIKEL_UMWANDLUNG(); }; 
        if (this.fullTableName.equals(_TAB.artikel_gruppe_fibu.fullTableName)) { return new RECORDNEW_ARTIKEL_GRUPPE_FIBU(); }; 
        if (this.fullTableName.equals(_TAB.artikel_gruppe.fullTableName)) { return new RECORDNEW_ARTIKEL_GRUPPE(); }; 
        if (this.fullTableName.equals(_TAB.artikel_datenblatt.fullTableName)) { return new RECORDNEW_ARTIKEL_DATENBLATT(); }; 
        if (this.fullTableName.equals(_TAB.artikel_bez_mwst.fullTableName)) { return new RECORDNEW_ARTIKEL_BEZ_MWST(); }; 
        if (this.fullTableName.equals(_TAB.artikelbez_lief.fullTableName)) { return new RECORDNEW_ARTIKELBEZ_LIEF(); }; 
        if (this.fullTableName.equals(_TAB.artikel_bez.fullTableName)) { return new RECORDNEW_ARTIKEL_BEZ(); }; 
        if (this.fullTableName.equals(_TAB.artikel_bereich.fullTableName)) { return new RECORDNEW_ARTIKEL_BEREICH(); }; 
        if (this.fullTableName.equals(_TAB.artikel.fullTableName)) { return new RECORDNEW_ARTIKEL(); }; 
        if (this.fullTableName.equals(_TAB.artbez_verunreinigung.fullTableName)) { return new RECORDNEW_ARTBEZ_VERUNREINIGUNG(); }; 
        if (this.fullTableName.equals(_TAB.artbez_mech_zustand.fullTableName)) { return new RECORDNEW_ARTBEZ_MECH_ZUSTAND(); }; 
        if (this.fullTableName.equals(_TAB.archivmedien.fullTableName)) { return new RECORDNEW_ARCHIVMEDIEN(); }; 
        if (this.fullTableName.equals(_TAB.anrede.fullTableName)) { return new RECORDNEW_ANREDE(); }; 
        if (this.fullTableName.equals(_TAB.aktionsanlass.fullTableName)) { return new RECORDNEW_AKTIONSANLASS(); }; 
        if (this.fullTableName.equals(_TAB.ah7_steuerdatei.fullTableName)) { return new RECORDNEW_AH7_STEUERDATEI(); }; 
        if (this.fullTableName.equals(_TAB.ah7_profil.fullTableName)) { return new RECORDNEW_AH7_PROFIL(); }; 
        if (this.fullTableName.equals(_TAB.adress_zusatzwerte.fullTableName)) { return new RECORDNEW_ADRESS_ZUSATZWERTE(); }; 
        if (this.fullTableName.equals(_TAB.adress_zusatzfeld.fullTableName)) { return new RECORDNEW_ADRESS_ZUSATZFELD(); }; 
        if (this.fullTableName.equals(_TAB.adressklasse_def.fullTableName)) { return new RECORDNEW_ADRESSKLASSE_DEF(); }; 
        if (this.fullTableName.equals(_TAB.adressklasse.fullTableName)) { return new RECORDNEW_ADRESSKLASSE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_zusatzbranche.fullTableName)) { return new RECORDNEW_ADRESSE_ZUSATZBRANCHE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_waehrung.fullTableName)) { return new RECORDNEW_ADRESSE_WAEHRUNG(); }; 
        if (this.fullTableName.equals(_TAB.adresse_ust_id.fullTableName)) { return new RECORDNEW_ADRESSE_UST_ID(); }; 
        if (this.fullTableName.equals(_TAB.adresse_potential.fullTableName)) { return new RECORDNEW_ADRESSE_POTENTIAL(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal5.fullTableName)) { return new RECORDNEW_ADRESSE_MERKMAL5(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal4.fullTableName)) { return new RECORDNEW_ADRESSE_MERKMAL4(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal3.fullTableName)) { return new RECORDNEW_ADRESSE_MERKMAL3(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal2.fullTableName)) { return new RECORDNEW_ADRESSE_MERKMAL2(); }; 
        if (this.fullTableName.equals(_TAB.adresse_merkmal1.fullTableName)) { return new RECORDNEW_ADRESSE_MERKMAL1(); }; 
        if (this.fullTableName.equals(_TAB.adresse_info.fullTableName)) { return new RECORDNEW_ADRESSE_INFO(); }; 
        if (this.fullTableName.equals(_TAB.adresse_geschenk.fullTableName)) { return new RECORDNEW_ADRESSE_GESCHENK(); }; 
        if (this.fullTableName.equals(_TAB.adresse_fahrzeuge.fullTableName)) { return new RECORDNEW_ADRESSE_FAHRZEUGE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_eu_vertr_form.fullTableName)) { return new RECORDNEW_ADRESSE_EU_VERTR_FORM(); }; 
        if (this.fullTableName.equals(_TAB.adresse_eak_code.fullTableName)) { return new RECORDNEW_ADRESSE_EAK_CODE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_artikel.fullTableName)) { return new RECORDNEW_ADRESSE_ARTIKEL(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aqu_rel_wbw_kd.fullTableName)) { return new RECORDNEW_ADRESSE_AQU_REL_WBW_KD(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aqu_rel_kd_sorte.fullTableName)) { return new RECORDNEW_ADRESSE_AQU_REL_KD_SORTE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aquise_akte.fullTableName)) { return new RECORDNEW_ADRESSE_AQUISE_AKTE(); }; 
        if (this.fullTableName.equals(_TAB.adresse_aquise.fullTableName)) { return new RECORDNEW_ADRESSE_AQUISE(); }; 
        if (this.fullTableName.equals(_TAB.adresse.fullTableName)) { return new RECORDNEW_ADRESSE(); }; 
        if (this.fullTableName.equals(_TAB.adressausstatt_def.fullTableName)) { return new RECORDNEW_ADRESSAUSSTATT_DEF(); }; 
        if (this.fullTableName.equals(_TAB.adressausstatt.fullTableName)) { return new RECORDNEW_ADRESSAUSSTATT(); }; 
        if (this.fullTableName.equals(_TAB.adr_containertyp.fullTableName)) { return new RECORDNEW_ADR_CONTAINERTYP(); }; 
        if (this.fullTableName.equals(_TAB.abzugsschablonen.fullTableName)) { return new RECORDNEW_ABZUGSSCHABLONEN(); }; 
        if (this.fullTableName.equals(_TAB.abzugsgrund.fullTableName)) { return new RECORDNEW_ABZUGSGRUND(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt_pos.fullTableName)) { return new RECORDNEW_ABRECHBLATT_POS(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt_artbez_out.fullTableName)) { return new RECORDNEW_ABRECHBLATT_ARTBEZ_OUT(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt_artbez_in.fullTableName)) { return new RECORDNEW_ABRECHBLATT_ARTBEZ_IN(); }; 
        if (this.fullTableName.equals(_TAB.abrechblatt.fullTableName)) { return new RECORDNEW_ABRECHBLATT(); }; 
        if (this.fullTableName.equals(_TAB.zugriff.fullTableName)) { return new RECORDNEW_ZUGRIFF(); }; 
        if (this.fullTableName.equals(_TAB.waehrungsquery.fullTableName)) { return new RECORDNEW_WAEHRUNGSQUERY(); }; 
        if (this.fullTableName.equals(_TAB.waehrung.fullTableName)) { return new RECORDNEW_WAEHRUNG(); }; 
        if (this.fullTableName.equals(_TAB.version.fullTableName)) { return new RECORDNEW_VERSION(); }; 
        if (this.fullTableName.equals(_TAB.usersettings_jsonbig.fullTableName)) { return new RECORDNEW_USERSETTINGS_JSONBIG(); }; 
        if (this.fullTableName.equals(_TAB.usersettings_json.fullTableName)) { return new RECORDNEW_USERSETTINGS_JSON(); }; 
        if (this.fullTableName.equals(_TAB.usersettings.fullTableName)) { return new RECORDNEW_USERSETTINGS(); }; 
        if (this.fullTableName.equals(_TAB.usergroup.fullTableName)) { return new RECORDNEW_USERGROUP(); }; 
        if (this.fullTableName.equals(_TAB.user_applet.fullTableName)) { return new RECORDNEW_USER_APPLET(); }; 
        if (this.fullTableName.equals(_TAB.user.fullTableName)) { return new RECORDNEW_USER(); }; 
        if (this.fullTableName.equals(_TAB.trigger_def.fullTableName)) { return new RECORDNEW_TRIGGER_DEF(); }; 
        if (this.fullTableName.equals(_TAB.textuebersetzung.fullTableName)) { return new RECORDNEW_TEXTUEBERSETZUNG(); }; 
        if (this.fullTableName.equals(_TAB.textkonstante.fullTableName)) { return new RECORDNEW_TEXTKONSTANTE(); }; 
        if (this.fullTableName.equals(_TAB.tabellenmigration.fullTableName)) { return new RECORDNEW_TABELLENMIGRATION(); }; 
        if (this.fullTableName.equals(_TAB.sprache.fullTableName)) { return new RECORDNEW_SPRACHE(); }; 
        if (this.fullTableName.equals(_TAB.speed_index.fullTableName)) { return new RECORDNEW_SPEED_INDEX(); }; 
        if (this.fullTableName.equals(_TAB.servlets.fullTableName)) { return new RECORDNEW_SERVLETS(); }; 
        if (this.fullTableName.equals(_TAB.reportaktion.fullTableName)) { return new RECORDNEW_REPORTAKTION(); }; 
        if (this.fullTableName.equals(_TAB.registry.fullTableName)) { return new RECORDNEW_REGISTRY(); }; 
        if (this.fullTableName.equals(_TAB.negativlist.fullTableName)) { return new RECORDNEW_NEGATIVLIST(); }; 
        if (this.fullTableName.equals(_TAB.mask_def_cell.fullTableName)) { return new RECORDNEW_MASK_DEF_CELL(); }; 
        if (this.fullTableName.equals(_TAB.mask_def.fullTableName)) { return new RECORDNEW_MASK_DEF(); }; 
        if (this.fullTableName.equals(_TAB.mandant_zusatz_feldnamen.fullTableName)) { return new RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN(); }; 
        if (this.fullTableName.equals(_TAB.mandant_zusatz.fullTableName)) { return new RECORDNEW_MANDANT_ZUSATZ(); }; 
        if (this.fullTableName.equals(_TAB.mandant_steuervermerk.fullTableName)) { return new RECORDNEW_MANDANT_STEUERVERMERK(); }; 
        if (this.fullTableName.equals(_TAB.mandant.fullTableName)) { return new RECORDNEW_MANDANT(); }; 
        if (this.fullTableName.equals(_TAB.login.fullTableName)) { return new RECORDNEW_LOGIN(); }; 
        if (this.fullTableName.equals(_TAB.land.fullTableName)) { return new RECORDNEW_LAND(); }; 
        if (this.fullTableName.equals(_TAB.jasperreport_key.fullTableName)) { return new RECORDNEW_JASPERREPORT_KEY(); }; 
        if (this.fullTableName.equals(_TAB.hauptmenue.fullTableName)) { return new RECORDNEW_HAUPTMENUE(); }; 
        if (this.fullTableName.equals(_TAB.enum_vektor_pos_typ.fullTableName)) { return new RECORDNEW_ENUM_VEKTOR_POS_TYP(); }; 
        if (this.fullTableName.equals(_TAB.enum_bewegung_typ.fullTableName)) { return new RECORDNEW_ENUM_BEWEGUNG_TYP(); }; 
        if (this.fullTableName.equals(_TAB.db_log.fullTableName)) { return new RECORDNEW_DB_LOG(); }; 
        if (this.fullTableName.equals(_TAB.datum.fullTableName)) { return new RECORDNEW_DATUM(); }; 
        if (this.fullTableName.equals(_TAB.button_user.fullTableName)) { return new RECORDNEW_BUTTON_USER(); }; 
        if (this.fullTableName.equals(_TAB.button.fullTableName)) { return new RECORDNEW_BUTTON(); }; 
        if (this.fullTableName.equals(_TAB.batch_task.fullTableName)) { return new RECORDNEW_BATCH_TASK(); }; 
        if (this.fullTableName.equals(_TAB.batch_log.fullTableName)) { return new RECORDNEW_BATCH_LOG(); }; 
        if (this.fullTableName.equals(_TAB.applet.fullTableName)) { return new RECORDNEW_APPLET(); }; 
    throw new myException(this,this.name()+": Table was not found !");
    }





    public  MyRECORD get_RECORD() throws myException { 
     
        try { 
            @SuppressWarnings("rawtypes") 
            Class c = Class.forName("panter.gmbh.basics4project.DB_RECORDS.RECORD_"+this.baseTableName()); 
            return (MyRECORD)c.newInstance(); 
            } catch (ClassNotFoundException e) { 
                e.printStackTrace(); 
                new myException("Class: "+"RECORD_"+this.baseTableName()+" can not be found !!!"); 
            } catch (InstantiationException e) { 
                e.printStackTrace(); 
                new myException("Class: "+"RECORD_"+this.baseTableName()+" can not be found !!!"); 
            } catch (IllegalAccessException e) { 
                e.printStackTrace(); 
                new myException("Class: "+"RECORD_"+this.baseTableName()+" can not be found !!!"); 
            }  
            return null; 
         } 
     
     
    public MyRECORD get_RECORD(String id) throws myException { 
        MyRECORD rec = this.get_RECORD(); 
        if (S.isFull(id)) { 
            rec.PrepareAndBuild("*", bibE2.cTO()+"."+this.fullTableName, this.keyFieldName()+"="+id); 
        } 
        return rec; 
    } 
     
    @SuppressWarnings("rawtypes") 
    public  Class get_RECORD_Class() throws myException { 
        try { 
            Class c = Class.forName("panter.gmbh.basics4project.DB_RECORDS.RECORD_"+this.baseTableName()); 
            return c; 
        } catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
            new myException("Class: "+"RECORD_"+this.baseTableName()+" can not be found !!!"); 
        } 
        return null; 
    } 
}
