package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum VPOS_TPA_FUHRE_ORT implements IF_Field {


     ablademenge_rechnung("ABLADEMENGE_RECHNUNG","ABLADEMENGE_RECHNUNG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     abzug_menge("ABZUG_MENGE","ABZUG_MENGE","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     anr1("ANR1","ANR1","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     anr2("ANR2","ANR2","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     anteil_ablademenge("ANTEIL_ABLADEMENGE","ANTEIL_ABLADEMENGE","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     anteil_lademenge("ANTEIL_LADEMENGE","ANTEIL_LADEMENGE","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     anteil_planmenge("ANTEIL_PLANMENGE","ANTEIL_PLANMENGE","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     artbez1("ARTBEZ1","ARTBEZ1","NVARCHAR2",80,80,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     artbez2("ARTBEZ2","ARTBEZ2","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     avv_ausstellung_datum("AVV_AUSSTELLUNG_DATUM","AVV_AUSSTELLUNG_DATUM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     basel_code("BASEL_CODE","BASEL_CODE","NVARCHAR2",80,80,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     basel_notiz("BASEL_NOTIZ","BASEL_NOTIZ","NVARCHAR2",300,300,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     bestellnummer("BESTELLNUMMER","BESTELLNUMMER","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     buchungsnummer_zusatz("BUCHUNGSNUMMER_ZUSATZ","BUCHUNGSNUMMER_ZUSATZ","NVARCHAR2",3,3,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     datum_lade_ablade("DATUM_LADE_ABLADE","DATUM_LADE_ABLADE","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     def_quelle_ziel("DEF_QUELLE_ZIEL","DEF_QUELLE_ZIEL","NVARCHAR2",10,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     einheit_mengen("EINHEIT_MENGEN","EINHEIT_MENGEN","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     einzelpreis("EINZELPREIS","EINZELPREIS","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     epreis_result_netto_mge("EPREIS_RESULT_NETTO_MGE","EPREIS_RESULT_NETTO_MGE","NUMBER",14,12,2,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     eucode("EUCODE","EUCODE","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     eunotiz("EUNOTIZ","EUNOTIZ","NVARCHAR2",500,500,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     eu_blatt_menge("EU_BLATT_MENGE","EU_BLATT_MENGE","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     eu_blatt_volumen("EU_BLATT_VOLUMEN","EU_BLATT_VOLUMEN","NUMBER",14,12,3,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     eu_steuer_vermerk("EU_STEUER_VERMERK","EU_STEUER_VERMERK","NVARCHAR2",300,300,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     fax("FAX","FAX","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     gelangensbestaetigung_erhalten("GELANGENSBESTAETIGUNG_ERHALTEN","GELANGENSBESTAETIGUNG_ERHALTEN","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     hausnummer("HAUSNUMMER","HAUSNUMMER","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_adresse_lager("ID_ADRESSE_LAGER","ID_ADRESSE_LAGER","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_artikel_bez("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_eak_code("ID_EAK_CODE","ID_EAK_CODE","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_handelsdef("ID_HANDELSDEF","ID_HANDELSDEF","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_tax("ID_TAX","ID_TAX","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_vpos_kon("ID_VPOS_KON","ID_VPOS_KON","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_vpos_std("ID_VPOS_STD","ID_VPOS_STD","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),true),
     id_vpos_tpa_fuhre_sonder("ID_VPOS_TPA_FUHRE_SONDER","ID_VPOS_TPA_FUHRE_SONDER","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     id_wiegekarte("ID_WIEGEKARTE","ID_WIEGEKARTE","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     intrastat_meld("INTRASTAT_MELD","INTRASTAT_MELD","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     kein_kontrakt_noetig("KEIN_KONTRAKT_NOETIG","KEIN_KONTRAKT_NOETIG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     lademenge_gutschrift("LADEMENGE_GUTSCHRIFT","LADEMENGE_GUTSCHRIFT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     laendercode("LAENDERCODE","LAENDERCODE","NVARCHAR2",6,6,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     laendercode_transit1("LAENDERCODE_TRANSIT1","LAENDERCODE_TRANSIT1","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     laendercode_transit2("LAENDERCODE_TRANSIT2","LAENDERCODE_TRANSIT2","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     laendercode_transit3("LAENDERCODE_TRANSIT3","LAENDERCODE_TRANSIT3","NVARCHAR2",5,5,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     lieferbed_alternativ("LIEFERBED_ALTERNATIV","LIEFERBED_ALTERNATIV","NVARCHAR2",200,200,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     manuell_preis("MANUELL_PREIS","MANUELL_PREIS","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     name1("NAME1","NAME1","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     name2("NAME2","NAME2","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     name3("NAME3","NAME3","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     nationaler_abfall_code("NATIONALER_ABFALL_CODE","NATIONALER_ABFALL_CODE","NVARCHAR2",20,20,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     notifikation_nr("NOTIFIKATION_NR","NOTIFIKATION_NR","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     oeffnungszeiten("OEFFNUNGSZEITEN","OEFFNUNGSZEITEN","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     ohne_abrechnung("OHNE_ABRECHNUNG","OHNE_ABRECHNUNG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     ohne_avv_vertrag_check("OHNE_AVV_VERTRAG_CHECK","OHNE_AVV_VERTRAG_CHECK","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     ort("ORT","ORT","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     ortzusatz("ORTZUSATZ","ORTZUSATZ","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     plz("PLZ","PLZ","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     postennummer("POSTENNUMMER","POSTENNUMMER","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     print_eu_amtsblatt("PRINT_EU_AMTSBLATT","PRINT_EU_AMTSBLATT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     pruefung_menge("PRUEFUNG_MENGE","PRUEFUNG_MENGE","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     pruefung_menge_am("PRUEFUNG_MENGE_AM","PRUEFUNG_MENGE_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     pruefung_menge_von("PRUEFUNG_MENGE_VON","PRUEFUNG_MENGE_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     pruefung_preis("PRUEFUNG_PREIS","PRUEFUNG_PREIS","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     pruefung_preis_am("PRUEFUNG_PREIS_AM","PRUEFUNG_PREIS_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     pruefung_preis_von("PRUEFUNG_PREIS_VON","PRUEFUNG_PREIS_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     steuersatz("STEUERSATZ","STEUERSATZ","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     strasse("STRASSE","STRASSE","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     tel("TEL","TEL","NVARCHAR2",30,30,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     transit("TRANSIT","TRANSIT","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     wiegekartenkenner("WIEGEKARTENKENNER","WIEGEKARTENKENNER","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     zeitstempel("ZEITSTEMPEL","ZEITSTEMPEL","NVARCHAR2",16,16,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
     zolltarifnr("ZOLLTARIFNR","ZOLLTARIFNR","NVARCHAR2",50,50,0,true,_TAB.vpos_tpa_fuhre_ort.fullTableName(),_TAB.vpos_tpa_fuhre_ort.baseTableName(),false),
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

     private VPOS_TPA_FUHRE_ORT( String   p_fieldName,  
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

         for (IF_Field field: VPOS_TPA_FUHRE_ORT.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.vpos_tpa_fuhre_ort.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.vpos_tpa_fuhre_ort.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_VPOS_TPA_FUHRE_ORT 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.vpos_tpa_fuhre_ort.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_VPOS_TPA_FUHRE_ORT AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.vpos_tpa_fuhre_ort.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_TPA_FUHRE_ORT AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.vpos_tpa_fuhre_ort; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_TPA_FUHRE_ORT AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.vpos_tpa_fuhre_ort; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: VPOS_TPA_FUHRE_ORT.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
