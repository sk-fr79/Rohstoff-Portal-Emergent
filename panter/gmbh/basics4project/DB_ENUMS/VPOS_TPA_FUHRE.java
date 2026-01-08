package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum VPOS_TPA_FUHRE implements IF_Field {


     abgeschlossen("ABGESCHLOSSEN","ABGESCHLOSSEN","NVARCHAR2",1,1,0,false,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ablademenge_rechnung("ABLADEMENGE_RECHNUNG","ABLADEMENGE_RECHNUNG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     abladen_brutto("ABLADEN_BRUTTO","ABLADEN_BRUTTO","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     abladen_tara("ABLADEN_TARA","ABLADEN_TARA","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     abzug_ablademenge_abn("ABZUG_ABLADEMENGE_ABN","ABZUG_ABLADEMENGE_ABN","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     abzug_lademenge_lief("ABZUG_LADEMENGE_LIEF","ABZUG_LADEMENGE_LIEF","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     alte_lieferschein_nr("ALTE_LIEFERSCHEIN_NR","ALTE_LIEFERSCHEIN_NR","NVARCHAR2",12,12,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     alt_wird_nicht_gebucht("ALT_WIRD_NICHT_GEBUCHT","ALT_WIRD_NICHT_GEBUCHT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anhaengerkennzeichen("ANHAENGERKENNZEICHEN","ANHAENGERKENNZEICHEN","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anr1_ek("ANR1_EK","ANR1_EK","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anr1_vk("ANR1_VK","ANR1_VK","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anr2_ek("ANR2_EK","ANR2_EK","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anr2_vk("ANR2_VK","ANR2_VK","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anrufdatum_fp("ANRUFDATUM_FP","ANRUFDATUM_FP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anrufer_fp("ANRUFER_FP","ANRUFER_FP","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anteil_ablademenge_abn("ANTEIL_ABLADEMENGE_ABN","ANTEIL_ABLADEMENGE_ABN","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anteil_ablademenge_lief("ANTEIL_ABLADEMENGE_LIEF","ANTEIL_ABLADEMENGE_LIEF","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anteil_lademenge_abn("ANTEIL_LADEMENGE_ABN","ANTEIL_LADEMENGE_ABN","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anteil_lademenge_lief("ANTEIL_LADEMENGE_LIEF","ANTEIL_LADEMENGE_LIEF","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anteil_planmenge_abn("ANTEIL_PLANMENGE_ABN","ANTEIL_PLANMENGE_ABN","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anteil_planmenge_lief("ANTEIL_PLANMENGE_LIEF","ANTEIL_PLANMENGE_LIEF","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     anzahl_container_fp("ANZAHL_CONTAINER_FP","ANZAHL_CONTAINER_FP","NUMBER",4,3,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     artbez1_ek("ARTBEZ1_EK","ARTBEZ1_EK","NVARCHAR2",80,80,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     artbez1_vk("ARTBEZ1_VK","ARTBEZ1_VK","NVARCHAR2",80,80,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     artbez2_ek("ARTBEZ2_EK","ARTBEZ2_EK","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     artbez2_vk("ARTBEZ2_VK","ARTBEZ2_VK","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     aufladen_brutto("AUFLADEN_BRUTTO","AUFLADEN_BRUTTO","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     aufladen_tara("AUFLADEN_TARA","AUFLADEN_TARA","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     avv_ausstellung_datum("AVV_AUSSTELLUNG_DATUM","AVV_AUSSTELLUNG_DATUM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_hausnummer("A_HAUSNUMMER","A_HAUSNUMMER","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_laendercode("A_LAENDERCODE","A_LAENDERCODE","NVARCHAR2",6,6,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_name1("A_NAME1","A_NAME1","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_name2("A_NAME2","A_NAME2","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_name3("A_NAME3","A_NAME3","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_ort("A_ORT","A_ORT","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_ortzusatz("A_ORTZUSATZ","A_ORTZUSATZ","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_plz("A_PLZ","A_PLZ","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     a_strasse("A_STRASSE","A_STRASSE","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     basel_code("BASEL_CODE","BASEL_CODE","NVARCHAR2",80,80,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     basel_notiz("BASEL_NOTIZ","BASEL_NOTIZ","NVARCHAR2",600,600,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bemerkung_fuer_kunde("BEMERKUNG_FUER_KUNDE","BEMERKUNG_FUER_KUNDE","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bemerkung_sachbearbeiter("BEMERKUNG_SACHBEARBEITER","BEMERKUNG_SACHBEARBEITER","NVARCHAR2",600,600,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bemerkung_start_fp("BEMERKUNG_START_FP","BEMERKUNG_START_FP","NVARCHAR2",300,300,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bemerkung_ziel_fp("BEMERKUNG_ZIEL_FP","BEMERKUNG_ZIEL_FP","NVARCHAR2",300,300,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bestellnummer_ek("BESTELLNUMMER_EK","BESTELLNUMMER_EK","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     bestellnummer_vk("BESTELLNUMMER_VK","BESTELLNUMMER_VK","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     buchungsnr_fuhre("BUCHUNGSNR_FUHRE","BUCHUNGSNR_FUHRE","NVARCHAR2",20,20,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     datum_abholung("DATUM_ABHOLUNG","DATUM_ABHOLUNG","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     datum_abholung_ende("DATUM_ABHOLUNG_ENDE","DATUM_ABHOLUNG_ENDE","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     datum_abladen("DATUM_ABLADEN","DATUM_ABLADEN","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     datum_anlieferung("DATUM_ANLIEFERUNG","DATUM_ANLIEFERUNG","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     datum_anlieferung_ende("DATUM_ANLIEFERUNG_ENDE","DATUM_ANLIEFERUNG_ENDE","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     datum_aufladen("DATUM_AUFLADEN","DATUM_AUFLADEN","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     dat_fahrplan_fp("DAT_FAHRPLAN_FP","DAT_FAHRPLAN_FP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     dat_vorgemerkt_ende_fp("DAT_VORGEMERKT_ENDE_FP","DAT_VORGEMERKT_ENDE_FP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     dat_vorgemerkt_fp("DAT_VORGEMERKT_FP","DAT_VORGEMERKT_FP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,false,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ean_code_fp("EAN_CODE_FP","EAN_CODE_FP","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     einheit_mengen("EINHEIT_MENGEN","EINHEIT_MENGEN","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     einzelpreis_ek("EINZELPREIS_EK","EINZELPREIS_EK","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     einzelpreis_vk("EINZELPREIS_VK","EINZELPREIS_VK","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ek_kontraktnr_zusatz("EK_KONTRAKTNR_ZUSATZ","EK_KONTRAKTNR_ZUSATZ","NVARCHAR2",15,15,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ek_vk_sorte_lock("EK_VK_SORTE_LOCK","EK_VK_SORTE_LOCK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ek_vk_zuord_zwang("EK_VK_ZUORD_ZWANG","EK_VK_ZUORD_ZWANG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     epreis_result_netto_mge_ek("EPREIS_RESULT_NETTO_MGE_EK","EPREIS_RESULT_NETTO_MGE_EK","NUMBER",14,12,2,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     epreis_result_netto_mge_vk("EPREIS_RESULT_NETTO_MGE_VK","EPREIS_RESULT_NETTO_MGE_VK","NUMBER",14,12,2,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     erfasser_fp("ERFASSER_FP","ERFASSER_FP","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     eucode("EUCODE","EUCODE","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     eunotiz("EUNOTIZ","EUNOTIZ","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     eu_blatt_menge("EU_BLATT_MENGE","EU_BLATT_MENGE","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     eu_blatt_volumen("EU_BLATT_VOLUMEN","EU_BLATT_VOLUMEN","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     eu_steuer_vermerk_ek("EU_STEUER_VERMERK_EK","EU_STEUER_VERMERK_EK","NVARCHAR2",600,600,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     eu_steuer_vermerk_vk("EU_STEUER_VERMERK_VK","EU_STEUER_VERMERK_VK","NVARCHAR2",600,600,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fahrer_fp("FAHRER_FP","FAHRER_FP","NVARCHAR2",80,80,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fahrplangruppe_fp("FAHRPLANGRUPPE_FP","FAHRPLANGRUPPE_FP","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fahrt_anfang_fp("FAHRT_ANFANG_FP","FAHRT_ANFANG_FP","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fahrt_ende_fp("FAHRT_ENDE_FP","FAHRT_ENDE_FP","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fax_abnehmer("FAX_ABNEHMER","FAX_ABNEHMER","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fax_lieferant("FAX_LIEFERANT","FAX_LIEFERANT","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fuhrengruppe("FUHRENGRUPPE","FUHRENGRUPPE","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fuhre_aus_fahrplan("FUHRE_AUS_FAHRPLAN","FUHRE_AUS_FAHRPLAN","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fuhre_ist_umgeleitet("FUHRE_IST_UMGELEITET","FUHRE_IST_UMGELEITET","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     fuhre_komplett("FUHRE_KOMPLETT","FUHRE_KOMPLETT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     gelangensbestaetigung_erhalten("GELANGENSBESTAETIGUNG_ERHALTEN","GELANGENSBESTAETIGUNG_ERHALTEN","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_adresse_fremdauftrag("ID_ADRESSE_FREMDAUFTRAG","ID_ADRESSE_FREMDAUFTRAG","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_adresse_lager_start("ID_ADRESSE_LAGER_START","ID_ADRESSE_LAGER_START","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_adresse_lager_ziel("ID_ADRESSE_LAGER_ZIEL","ID_ADRESSE_LAGER_ZIEL","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_adresse_spedition("ID_ADRESSE_SPEDITION","ID_ADRESSE_SPEDITION","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_adresse_start("ID_ADRESSE_START","ID_ADRESSE_START","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_adresse_ziel("ID_ADRESSE_ZIEL","ID_ADRESSE_ZIEL","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_artikel_bez_ek("ID_ARTIKEL_BEZ_EK","ID_ARTIKEL_BEZ_EK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_artikel_bez_vk("ID_ARTIKEL_BEZ_VK","ID_ARTIKEL_BEZ_VK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_containertyp_fp("ID_CONTAINERTYP_FP","ID_CONTAINERTYP_FP","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_eak_code("ID_EAK_CODE","ID_EAK_CODE","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_fahrplan_zeitangabe("ID_FAHRPLAN_ZEITANGABE","ID_FAHRPLAN_ZEITANGABE","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_handelsdef("ID_HANDELSDEF","ID_HANDELSDEF","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_maschinen_anh_fp("ID_MASCHINEN_ANH_FP","ID_MASCHINEN_ANH_FP","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_maschinen_lkw_fp("ID_MASCHINEN_LKW_FP","ID_MASCHINEN_LKW_FP","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_tax_ek("ID_TAX_EK","ID_TAX_EK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_tax_vk("ID_TAX_VK","ID_TAX_VK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_uma_kontrakt("ID_UMA_KONTRAKT","ID_UMA_KONTRAKT","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_verarbeitung("ID_VERARBEITUNG","ID_VERARBEITUNG","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_verpackungsart("ID_VERPACKUNGSART","ID_VERPACKUNGSART","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_vpos_kon_ek("ID_VPOS_KON_EK","ID_VPOS_KON_EK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_vpos_kon_vk("ID_VPOS_KON_VK","ID_VPOS_KON_VK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_vpos_std_ek("ID_VPOS_STD_EK","ID_VPOS_STD_EK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_vpos_std_vk("ID_VPOS_STD_VK","ID_VPOS_STD_VK","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_vpos_tpa("ID_VPOS_TPA","ID_VPOS_TPA","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),true),
     id_vpos_tpa_fuhre_sonder("ID_VPOS_TPA_FUHRE_SONDER","ID_VPOS_TPA_FUHRE_SONDER","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_wiegekarte_abn("ID_WIEGEKARTE_ABN","ID_WIEGEKARTE_ABN","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     id_wiegekarte_lief("ID_WIEGEKARTE_LIEF","ID_WIEGEKARTE_LIEF","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     intrastat_meld_in("INTRASTAT_MELD_IN","INTRASTAT_MELD_IN","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     intrastat_meld_out("INTRASTAT_MELD_OUT","INTRASTAT_MELD_OUT","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ist_geplant_fp("IST_GEPLANT_FP","IST_GEPLANT_FP","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ist_storniert("IST_STORNIERT","IST_STORNIERT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     kein_kontrakt_noetig("KEIN_KONTRAKT_NOETIG","KEIN_KONTRAKT_NOETIG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     kenner_alte_saetze_fp("KENNER_ALTE_SAETZE_FP","KENNER_ALTE_SAETZE_FP","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     lademenge_gutschrift("LADEMENGE_GUTSCHRIFT","LADEMENGE_GUTSCHRIFT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     laendercode_transit1("LAENDERCODE_TRANSIT1","LAENDERCODE_TRANSIT1","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     laendercode_transit2("LAENDERCODE_TRANSIT2","LAENDERCODE_TRANSIT2","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     laendercode_transit3("LAENDERCODE_TRANSIT3","LAENDERCODE_TRANSIT3","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     lieferbed_alternativ_ek("LIEFERBED_ALTERNATIV_EK","LIEFERBED_ALTERNATIV_EK","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     lieferbed_alternativ_vk("LIEFERBED_ALTERNATIV_VK","LIEFERBED_ALTERNATIV_VK","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     lieferinfo_tpa("LIEFERINFO_TPA","LIEFERINFO_TPA","NVARCHAR2",600,600,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_hausnummer("L_HAUSNUMMER","L_HAUSNUMMER","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_laendercode("L_LAENDERCODE","L_LAENDERCODE","NVARCHAR2",6,6,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_name1("L_NAME1","L_NAME1","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_name2("L_NAME2","L_NAME2","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_name3("L_NAME3","L_NAME3","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_ort("L_ORT","L_ORT","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_ortzusatz("L_ORTZUSATZ","L_ORTZUSATZ","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_plz("L_PLZ","L_PLZ","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     l_strasse("L_STRASSE","L_STRASSE","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     manuell_preis_ek("MANUELL_PREIS_EK","MANUELL_PREIS_EK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     manuell_preis_vk("MANUELL_PREIS_VK","MANUELL_PREIS_VK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     menge_abladen_ko("MENGE_ABLADEN_KO","MENGE_ABLADEN_KO","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     menge_aufladen_ko("MENGE_AUFLADEN_KO","MENGE_AUFLADEN_KO","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     menge_vorgabe_ko("MENGE_VORGABE_KO","MENGE_VORGABE_KO","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     nationaler_abfall_code("NATIONALER_ABFALL_CODE","NATIONALER_ABFALL_CODE","NVARCHAR2",20,20,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     notifikation_nr("NOTIFIKATION_NR","NOTIFIKATION_NR","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     notifikation_nr_ek("NOTIFIKATION_NR_EK","NOTIFIKATION_NR_EK","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     oeffnungszeiten_abn("OEFFNUNGSZEITEN_ABN","OEFFNUNGSZEITEN_ABN","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     oeffnungszeiten_lief("OEFFNUNGSZEITEN_LIEF","OEFFNUNGSZEITEN_LIEF","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ohne_abrechnung("OHNE_ABRECHNUNG","OHNE_ABRECHNUNG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ohne_avv_vertrag_check("OHNE_AVV_VERTRAG_CHECK","OHNE_AVV_VERTRAG_CHECK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     ordnungsnummer_cmr("ORDNUNGSNUMMER_CMR","ORDNUNGSNUMMER_CMR","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     postennummer_ek("POSTENNUMMER_EK","POSTENNUMMER_EK","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     postennummer_vk("POSTENNUMMER_VK","POSTENNUMMER_VK","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     print_eu_amtsblatt("PRINT_EU_AMTSBLATT","PRINT_EU_AMTSBLATT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_ablademenge("PRUEFUNG_ABLADEMENGE","PRUEFUNG_ABLADEMENGE","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_ablademenge_am("PRUEFUNG_ABLADEMENGE_AM","PRUEFUNG_ABLADEMENGE_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_ablademenge_von("PRUEFUNG_ABLADEMENGE_VON","PRUEFUNG_ABLADEMENGE_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_ek_preis("PRUEFUNG_EK_PREIS","PRUEFUNG_EK_PREIS","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_ek_preis_am("PRUEFUNG_EK_PREIS_AM","PRUEFUNG_EK_PREIS_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_ek_preis_von("PRUEFUNG_EK_PREIS_VON","PRUEFUNG_EK_PREIS_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_lademenge("PRUEFUNG_LADEMENGE","PRUEFUNG_LADEMENGE","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_lademenge_am("PRUEFUNG_LADEMENGE_AM","PRUEFUNG_LADEMENGE_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_lademenge_von("PRUEFUNG_LADEMENGE_VON","PRUEFUNG_LADEMENGE_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_vk_preis("PRUEFUNG_VK_PREIS","PRUEFUNG_VK_PREIS","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_vk_preis_am("PRUEFUNG_VK_PREIS_AM","PRUEFUNG_VK_PREIS_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     pruefung_vk_preis_von("PRUEFUNG_VK_PREIS_VON","PRUEFUNG_VK_PREIS_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     routing_distance_km("ROUTING_DISTANCE_KM","ROUTING_DISTANCE_KM","NUMBER",12,10,3,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     routing_time_minutes("ROUTING_TIME_MINUTES","ROUTING_TIME_MINUTES","NUMBER",7,6,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     schliesse_fuhre("SCHLIESSE_FUHRE","SCHLIESSE_FUHRE","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     schliesse_fuhre_am("SCHLIESSE_FUHRE_AM","SCHLIESSE_FUHRE_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     schliesse_fuhre_von("SCHLIESSE_FUHRE_VON","SCHLIESSE_FUHRE_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     sortierung_fp("SORTIERUNG_FP","SORTIERUNG_FP","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     speichern_trotz_inkonsistenz("SPEICHERN_TROTZ_INKONSISTENZ","SPEICHERN_TROTZ_INKONSISTENZ","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     status_buchung("STATUS_BUCHUNG","STATUS_BUCHUNG","NUMBER",2,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     steuersatz_ek("STEUERSATZ_EK","STEUERSATZ_EK","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     steuersatz_vk("STEUERSATZ_VK","STEUERSATZ_VK","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     storno_grund("STORNO_GRUND","STORNO_GRUND","NVARCHAR2",300,300,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     storno_kuerzel("STORNO_KUERZEL","STORNO_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     taetigkeit_fp("TAETIGKEIT_FP","TAETIGKEIT_FP","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     tel_abnehmer("TEL_ABNEHMER","TEL_ABNEHMER","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     tel_lieferant("TEL_LIEFERANT","TEL_LIEFERANT","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     tp_verantwortung("TP_VERANTWORTUNG","TP_VERANTWORTUNG","NVARCHAR2",20,20,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     transit_ek("TRANSIT_EK","TRANSIT_EK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     transit_vk("TRANSIT_VK","TRANSIT_VK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     transportkennzeichen("TRANSPORTKENNZEICHEN","TRANSPORTKENNZEICHEN","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     transportmittel("TRANSPORTMITTEL","TRANSPORTMITTEL","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     typ_strecke_lager_mixed("TYP_STRECKE_LAGER_MIXED","TYP_STRECKE_LAGER_MIXED","NUMBER",2,1,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     vk_kontraktnr_zusatz("VK_KONTRAKTNR_ZUSATZ","VK_KONTRAKTNR_ZUSATZ","NVARCHAR2",15,15,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     wiegekartenkenner_abladen("WIEGEKARTENKENNER_ABLADEN","WIEGEKARTENKENNER_ABLADEN","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     wiegekartenkenner_laden("WIEGEKARTENKENNER_LADEN","WIEGEKARTENKENNER_LADEN","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     zeitangabe("ZEITANGABE","ZEITANGABE","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     zeitstempel("ZEITSTEMPEL","ZEITSTEMPEL","NVARCHAR2",16,16,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
     zolltarifnr("ZOLLTARIFNR","ZOLLTARIFNR","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre.fullTableName(),_TAB.vpos_tpa_fuhre.baseTableName(),false),
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

     private VPOS_TPA_FUHRE( String   p_fieldName,  
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

         for (IF_Field field: VPOS_TPA_FUHRE.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.vpos_tpa_fuhre.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.vpos_tpa_fuhre.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_VPOS_TPA_FUHRE 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.vpos_tpa_fuhre.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_VPOS_TPA_FUHRE AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.vpos_tpa_fuhre.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_TPA_FUHRE AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.vpos_tpa_fuhre; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_TPA_FUHRE AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.vpos_tpa_fuhre; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: VPOS_TPA_FUHRE.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
