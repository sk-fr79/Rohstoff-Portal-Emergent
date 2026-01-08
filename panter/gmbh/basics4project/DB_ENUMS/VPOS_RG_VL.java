package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum VPOS_RG_VL implements IF_Field {


     anr1("ANR1","ANR1","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     anr2("ANR2","ANR2","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     anzahl("ANZAHL","ANZAHL","NUMBER",14,12,3,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     anzahl_abzug("ANZAHL_ABZUG","ANZAHL_ABZUG","NUMBER",12,10,3,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     artbez1("ARTBEZ1","ARTBEZ1","NVARCHAR2",80,80,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     artbez2("ARTBEZ2","ARTBEZ2","NVARCHAR2",1000,1000,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     ausfuehrungsdatum("AUSFUEHRUNGSDATUM","AUSFUEHRUNGSDATUM","DATE",7,0,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     bemerkung_intern("BEMERKUNG_INTERN","BEMERKUNG_INTERN","NVARCHAR2",400,400,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     bestellnummer("BESTELLNUMMER","BESTELLNUMMER","NVARCHAR2",30,30,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",200,200,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einheitkurz("EINHEITKURZ","EINHEITKURZ","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einheit_preis_kurz("EINHEIT_PREIS_KURZ","EINHEIT_PREIS_KURZ","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     eintrag_fuer_dropdown("EINTRAG_FUER_DROPDOWN","EINTRAG_FUER_DROPDOWN","NVARCHAR2",50,50,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einzelpreis("EINZELPREIS","EINZELPREIS","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einzelpreis_abzug("EINZELPREIS_ABZUG","EINZELPREIS_ABZUG","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einzelpreis_abzug_fw("EINZELPREIS_ABZUG_FW","EINZELPREIS_ABZUG_FW","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einzelpreis_fw("EINZELPREIS_FW","EINZELPREIS_FW","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einzelpreis_result("EINZELPREIS_RESULT","EINZELPREIS_RESULT","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     einzelpreis_result_fw("EINZELPREIS_RESULT_FW","EINZELPREIS_RESULT_FW","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     eucode("EUCODE","EUCODE","NVARCHAR2",50,50,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     eunotiz("EUNOTIZ","EUNOTIZ","NVARCHAR2",500,500,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     fixmonat("FIXMONAT","FIXMONAT","NUMBER",6,5,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     fixtag("FIXTAG","FIXTAG","NUMBER",6,5,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     gebucht("GEBUCHT","GEBUCHT","NVARCHAR2",1,1,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     gesamtpreis("GESAMTPREIS","GESAMTPREIS","NUMBER",14,12,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     gesamtpreis_abzug("GESAMTPREIS_ABZUG","GESAMTPREIS_ABZUG","NUMBER",14,12,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     gesamtpreis_abzug_fw("GESAMTPREIS_ABZUG_FW","GESAMTPREIS_ABZUG_FW","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     gesamtpreis_fw("GESAMTPREIS_FW","GESAMTPREIS_FW","NUMBER",14,12,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_adresse_lager("ID_ADRESSE_LAGER","ID_ADRESSE_LAGER","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_adresse_lager_start("ID_ADRESSE_LAGER_START","ID_ADRESSE_LAGER_START","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_adresse_lager_ziel("ID_ADRESSE_LAGER_ZIEL","ID_ADRESSE_LAGER_ZIEL","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_artikel_bez("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_strecken_def("ID_STRECKEN_DEF","ID_STRECKEN_DEF","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_vkopf_rg("ID_VKOPF_RG","ID_VKOPF_RG","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_vpos_kon_zugeord("ID_VPOS_KON_ZUGEORD","ID_VPOS_KON_ZUGEORD","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_vpos_preisklammer("ID_VPOS_PREISKLAMMER","ID_VPOS_PREISKLAMMER","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_vpos_rg_vl("ID_VPOS_RG_VL","ID_VPOS_RG_VL","NUMBER",11,10,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),true),
     id_vpos_tpa_fuhre_zugeord("ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_vpos_x_nachfolger("ID_VPOS_X_NACHFOLGER","ID_VPOS_X_NACHFOLGER","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_waehrung_fremd("ID_WAEHRUNG_FREMD","ID_WAEHRUNG_FREMD","NUMBER",11,10,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     id_zahlungsbedingungen("ID_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN","NUMBER",11,10,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     lager_vorzeichen("LAGER_VORZEICHEN","LAGER_VORZEICHEN","NUMBER",3,2,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     lieferbedingungen("LIEFERBEDINGUNGEN","LIEFERBEDINGUNGEN","NVARCHAR2",200,200,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     mengendivisor("MENGENDIVISOR","MENGENDIVISOR","NUMBER",11,10,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     ohne_steuer("OHNE_STEUER","OHNE_STEUER","NVARCHAR2",1,1,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     positionsklasse("POSITIONSKLASSE","POSITIONSKLASSE","NVARCHAR2",40,40,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     positionsnummer("POSITIONSNUMMER","POSITIONSNUMMER","NUMBER",11,10,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     position_active("POSITION_ACTIVE","POSITION_ACTIVE","NVARCHAR2",1,1,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     position_typ("POSITION_TYP","POSITION_TYP","NVARCHAR2",40,40,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     skonto_prozent("SKONTO_PROZENT","SKONTO_PROZENT","NUMBER",10,8,3,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     steuersatz("STEUERSATZ","STEUERSATZ","NUMBER",12,10,2,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     verteiler("VERTEILER","VERTEILER","NVARCHAR2",30,30,0,false,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     vorgang_typ("VORGANG_TYP","VORGANG_TYP","NVARCHAR2",40,40,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     waehrungskurs("WAEHRUNGSKURS","WAEHRUNGSKURS","NUMBER",11,9,4,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     wiegekartenkenner("WIEGEKARTENKENNER","WIEGEKARTENKENNER","NVARCHAR2",10,10,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     zahltage("ZAHLTAGE","ZAHLTAGE","NUMBER",6,5,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     zahlungsbedingungen("ZAHLUNGSBEDINGUNGEN","ZAHLUNGSBEDINGUNGEN","NVARCHAR2",200,200,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     zahlungsbed_calc_datum("ZAHLUNGSBED_CALC_DATUM","ZAHLUNGSBED_CALC_DATUM","DATE",7,0,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
     zolltarifnr("ZOLLTARIFNR","ZOLLTARIFNR","NVARCHAR2",50,50,0,true,_TAB.vpos_rg_vl.fullTableName(),_TAB.vpos_rg_vl.baseTableName(),false),
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

     private VPOS_RG_VL( String   p_fieldName,  
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

         for (IF_Field field: VPOS_RG_VL.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.vpos_rg_vl.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.vpos_rg_vl.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_VPOS_RG_VL 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.vpos_rg_vl.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_VPOS_RG_VL AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.vpos_rg_vl.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_RG_VL AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.vpos_rg_vl; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_RG_VL AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.vpos_rg_vl; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: VPOS_RG_VL.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
