package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum FUHREN_RECHNUNGEN implements IF_Field {


     anr1("ANR1","ANR1","NVARCHAR2",10,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     buchungs_nr("BUCHUNGS_NR","BUCHUNGS_NR","NVARCHAR2",24,24,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_adresse_standort_start("ID_ADRESSE_STANDORT_START","ID_ADRESSE_STANDORT_START","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_adresse_standort_ziel("ID_ADRESSE_STANDORT_ZIEL","ID_ADRESSE_STANDORT_ZIEL","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_adresse_start("ID_ADRESSE_START","ID_ADRESSE_START","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_adresse_ziel("ID_ADRESSE_ZIEL","ID_ADRESSE_ZIEL","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_eak_code("ID_EAK_CODE","ID_EAK_CODE","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_fuhren_rechnungen("ID_FUHREN_RECHNUNGEN","ID_FUHREN_RECHNUNGEN","NUMBER",11,10,0,false,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),true),
     id_lager("ID_LAGER","ID_LAGER","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_lager_gegenseite("ID_LAGER_GEGENSEITE","ID_LAGER_GEGENSEITE","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_lager_konto("ID_LAGER_KONTO","ID_LAGER_KONTO","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_sonderlager("ID_SONDERLAGER","ID_SONDERLAGER","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_vkopf_rg("ID_VKOPF_RG","ID_VKOPF_RG","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_vpos_kon("ID_VPOS_KON","ID_VPOS_KON","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_vpos_rg("ID_VPOS_RG","ID_VPOS_RG","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     ist_dienstleistung("IST_DIENSTLEISTUNG","IST_DIENSTLEISTUNG","NVARCHAR2",1,1,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     korrektur("KORREKTUR","KORREKTUR","NUMBER",39,38,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     leistungsdatum("LEISTUNGSDATUM","LEISTUNGSDATUM","DATE",7,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     leistungsdatum_lager("LEISTUNGSDATUM_LAGER","LEISTUNGSDATUM_LAGER","DATE",7,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     menge("MENGE","MENGE","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     mengen_vorzeichen("MENGEN_VORZEICHEN","MENGEN_VORZEICHEN","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     menge_abzug("MENGE_ABZUG","MENGE_ABZUG","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     menge_lager("MENGE_LAGER","MENGE_LAGER","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     menge_lager_ori("MENGE_LAGER_ORI","MENGE_LAGER_ORI","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     menge_nach_abzug("MENGE_NACH_ABZUG","MENGE_NACH_ABZUG","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     menge_nach_abzug_ori("MENGE_NACH_ABZUG_ORI","MENGE_NACH_ABZUG_ORI","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_buchungs_nr("R_BUCHUNGS_NR","R_BUCHUNGS_NR","NVARCHAR2",40,40,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_id_adresse("R_ID_ADRESSE","R_ID_ADRESSE","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_id_artikel_bez("R_ID_ARTIKEL_BEZ","R_ID_ARTIKEL_BEZ","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_lager_vorzeichen("R_LAGER_VORZEICHEN","R_LAGER_VORZEICHEN","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_pct_abzug("R_PCT_ABZUG","R_PCT_ABZUG","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_pct_metall("R_PCT_METALL","R_PCT_METALL","NUMBER",14,12,3,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_abzug("R_PREIS_ABZUG","R_PREIS_ABZUG","NUMBER",14,12,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_abzug_einzel("R_PREIS_ABZUG_EINZEL","R_PREIS_ABZUG_EINZEL","NUMBER",14,12,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_einzel("R_PREIS_EINZEL","R_PREIS_EINZEL","NUMBER",14,12,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_einzel_result("R_PREIS_EINZEL_RESULT","R_PREIS_EINZEL_RESULT","NUMBER",14,12,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_einzel_result_ori("R_PREIS_EINZEL_RESULT_ORI","R_PREIS_EINZEL_RESULT_ORI","NUMBER",12,10,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_gesamt("R_PREIS_GESAMT","R_PREIS_GESAMT","NUMBER",14,12,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_nach_abzug("R_PREIS_NACH_ABZUG","R_PREIS_NACH_ABZUG","NUMBER",14,12,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     r_preis_nach_abzug_ori("R_PREIS_NACH_ABZUG_ORI","R_PREIS_NACH_ABZUG_ORI","NUMBER",12,10,2,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     typ("TYP","TYP","NVARCHAR2",30,30,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     typ_strecke_lager_mixed("TYP_STRECKE_LAGER_MIXED","TYP_STRECKE_LAGER_MIXED","NUMBER",39,0,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     user_fuhre_bearbeiter("USER_FUHRE_BEARBEITER","USER_FUHRE_BEARBEITER","NVARCHAR2",20,20,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
     user_fuhre_erzeuger("USER_FUHRE_ERZEUGER","USER_FUHRE_ERZEUGER","NVARCHAR2",10,10,0,true,_TAB.fuhren_rechnungen.fullTableName(),_TAB.fuhren_rechnungen.baseTableName(),false),
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

     private FUHREN_RECHNUNGEN( String   p_fieldName,  
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

         for (IF_Field field: FUHREN_RECHNUNGEN.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.fuhren_rechnungen.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.fuhren_rechnungen.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_FUHREN_RECHNUNGEN 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.fuhren_rechnungen.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_FUHREN_RECHNUNGEN AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.fuhren_rechnungen.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FUHREN_RECHNUNGEN AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.fuhren_rechnungen; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FUHREN_RECHNUNGEN AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.fuhren_rechnungen; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: FUHREN_RECHNUNGEN.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
