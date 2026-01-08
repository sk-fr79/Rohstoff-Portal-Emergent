package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum BEWEGUNG_ATOM implements IF_Field {


     abgerechnet("ABGERECHNET","ABGERECHNET","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     abgeschlossen("ABGESCHLOSSEN","ABGESCHLOSSEN","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     abgeschlossen_am("ABGESCHLOSSEN_AM","ABGESCHLOSSEN_AM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     abgeschlossen_von("ABGESCHLOSSEN_VON","ABGESCHLOSSEN_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     abrechenbar("ABRECHENBAR","ABRECHENBAR","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     abzug_menge("ABZUG_MENGE","ABZUG_MENGE","NUMBER",14,12,3,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     artbez1("ARTBEZ1","ARTBEZ1","NVARCHAR2",80,80,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     artbez2("ARTBEZ2","ARTBEZ2","NVARCHAR2",500,500,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",600,600,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     bemerkung_sachbearbeiter("BEMERKUNG_SACHBEARBEITER","BEMERKUNG_SACHBEARBEITER","NVARCHAR2",600,600,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     buchungsnummer("BUCHUNGSNUMMER","BUCHUNGSNUMMER","NVARCHAR2",20,20,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",300,300,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     eu_steuer_vermerk("EU_STEUER_VERMERK","EU_STEUER_VERMERK","NVARCHAR2",600,600,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     e_preis("E_PREIS","E_PREIS","NUMBER",12,10,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     e_preis_result_brutto_mge("E_PREIS_RESULT_BRUTTO_MGE","E_PREIS_RESULT_BRUTTO_MGE","NUMBER",14,12,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     e_preis_result_netto_mge("E_PREIS_RESULT_NETTO_MGE","E_PREIS_RESULT_NETTO_MGE","NUMBER",12,10,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     gesamtpreis("GESAMTPREIS","GESAMTPREIS","NUMBER",14,12,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     gpreis_abz_auf_nettomge("GPREIS_ABZ_AUF_NETTOMGE","GPREIS_ABZ_AUF_NETTOMGE","NUMBER",14,12,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     gpreis_abz_mge("GPREIS_ABZ_MGE","GPREIS_ABZ_MGE","NUMBER",14,12,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     gpreis_abz_vorauszahlung("GPREIS_ABZ_VORAUSZAHLUNG","GPREIS_ABZ_VORAUSZAHLUNG","NUMBER",14,12,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_adresse_wb_start("ID_ADRESSE_WB_START","ID_ADRESSE_WB_START","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_adresse_wb_ziel("ID_ADRESSE_WB_ZIEL","ID_ADRESSE_WB_ZIEL","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_artikel_bez("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_bewegung("ID_BEWEGUNG","ID_BEWEGUNG","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_bewegungstation_logi_start("ID_BEWEGUNGSTATION_LOGI_START","ID_BEWEGUNGSTATION_LOGI_START","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_bewegungstation_logi_ziel("ID_BEWEGUNGSTATION_LOGI_ZIEL","ID_BEWEGUNGSTATION_LOGI_ZIEL","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_bewegung_atom("ID_BEWEGUNG_ATOM","ID_BEWEGUNG_ATOM","NUMBER",11,10,0,false,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),true),
     id_bewegung_vektor("ID_BEWEGUNG_VEKTOR","ID_BEWEGUNG_VEKTOR","NUMBER",11,10,0,false,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_bewegung_vektor_pos("ID_BEWEGUNG_VEKTOR_POS","ID_BEWEGUNG_VEKTOR_POS","NUMBER",11,10,0,false,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_lager_konto("ID_LAGER_KONTO","ID_LAGER_KONTO","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_lieferbedingungen("ID_LIEFERBEDINGUNGEN","ID_LIEFERBEDINGUNGEN","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_vpos_kon("ID_VPOS_KON","ID_VPOS_KON","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_vpos_std("ID_VPOS_STD","ID_VPOS_STD","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     id_zahlungsbedingungen("ID_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     kontraktzwang("KONTRAKTZWANG","KONTRAKTZWANG","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     kontraktzwang_aus_am("KONTRAKTZWANG_AUS_AM","KONTRAKTZWANG_AUS_AM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     kontraktzwang_aus_grund("KONTRAKTZWANG_AUS_GRUND","KONTRAKTZWANG_AUS_GRUND","NVARCHAR2",300,300,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     kontraktzwang_aus_von("KONTRAKTZWANG_AUS_VON","KONTRAKTZWANG_AUS_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     leistungsdatum("LEISTUNGSDATUM","LEISTUNGSDATUM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     lieferbedingungen("LIEFERBEDINGUNGEN","LIEFERBEDINGUNGEN","NVARCHAR2",200,200,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     lieferinfo_tpa("LIEFERINFO_TPA","LIEFERINFO_TPA","NVARCHAR2",600,600,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     manuell_preis("MANUELL_PREIS","MANUELL_PREIS","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     menge("MENGE","MENGE","NUMBER",14,12,3,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     menge_netto("MENGE_NETTO","MENGE_NETTO","NUMBER",14,12,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     menge_verteilung("MENGE_VERTEILUNG","MENGE_VERTEILUNG","NUMBER",14,12,3,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     nationaler_abfall_code("NATIONALER_ABFALL_CODE","NATIONALER_ABFALL_CODE","NVARCHAR2",20,20,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     notifikation_nr("NOTIFIKATION_NR","NOTIFIKATION_NR","NVARCHAR2",30,30,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     ordnungsnummer_cmr("ORDNUNGSNUMMER_CMR","ORDNUNGSNUMMER_CMR","NVARCHAR2",30,30,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     planmenge("PLANMENGE","PLANMENGE","NUMBER",14,12,3,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     posnr("POSNR","POSNR","NUMBER",11,10,0,false,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     postennummer("POSTENNUMMER","POSTENNUMMER","NVARCHAR2",100,100,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     preisermittlung("PREISERMITTLUNG","PREISERMITTLUNG","NVARCHAR2",20,20,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     pruefung_menge("PRUEFUNG_MENGE","PRUEFUNG_MENGE","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     pruefung_menge_am("PRUEFUNG_MENGE_AM","PRUEFUNG_MENGE_AM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     pruefung_menge_von("PRUEFUNG_MENGE_VON","PRUEFUNG_MENGE_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     pruefung_preis("PRUEFUNG_PREIS","PRUEFUNG_PREIS","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     pruefung_preis_am("PRUEFUNG_PREIS_AM","PRUEFUNG_PREIS_AM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     pruefung_preis_von("PRUEFUNG_PREIS_VON","PRUEFUNG_PREIS_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     setzkasten_komplett("SETZKASTEN_KOMPLETT","SETZKASTEN_KOMPLETT","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     steuersatz("STEUERSATZ","STEUERSATZ","NUMBER",12,10,2,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     storniert("STORNIERT","STORNIERT","NVARCHAR2",1,1,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     storniert_am("STORNIERT_AM","STORNIERT_AM","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     storniert_grund("STORNIERT_GRUND","STORNIERT_GRUND","NVARCHAR2",300,300,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     storniert_von("STORNIERT_VON","STORNIERT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
     zeitstempel("ZEITSTEMPEL","ZEITSTEMPEL","NVARCHAR2",16,16,0,true,_TAB.bewegung_atom.fullTableName(),_TAB.bewegung_atom.baseTableName(),false),
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

     private BEWEGUNG_ATOM( String   p_fieldName,  
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

         for (IF_Field field: BEWEGUNG_ATOM.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.bewegung_atom.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.bewegung_atom.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_BEWEGUNG_ATOM 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.bewegung_atom.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_BEWEGUNG_ATOM AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.bewegung_atom.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BEWEGUNG_ATOM AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.bewegung_atom; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BEWEGUNG_ATOM AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.bewegung_atom; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: BEWEGUNG_ATOM.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
