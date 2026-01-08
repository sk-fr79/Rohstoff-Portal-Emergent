package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum INTRASTAT_MELDUNG implements IF_Field {


     anmeldeform("ANMELDEFORM","ANMELDEFORM","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     anmeldejahr("ANMELDEJAHR","ANMELDEJAHR","NVARCHAR2",4,4,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     anmeldemonat("ANMELDEMONAT","ANMELDEMONAT","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     bestimm_land("BESTIMM_LAND","BESTIMM_LAND","NVARCHAR2",3,3,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     bestimm_region("BESTIMM_REGION","BESTIMM_REGION","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     bezugsjahr("BEZUGSJAHR","BEZUGSJAHR","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     bezugsmonat("BEZUGSMONAT","BEZUGSMONAT","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     bundesland_fa("BUNDESLAND_FA","BUNDESLAND_FA","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     eigenmasse("EIGENMASSE","EIGENMASSE","NVARCHAR2",11,11,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     exportfreigabe("EXPORTFREIGABE","EXPORTFREIGABE","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     exportiert_am("EXPORTIERT_AM","EXPORTIERT_AM","DATE",7,0,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     export_no_price("EXPORT_NO_PRICE","EXPORT_NO_PRICE","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     frachtkosten("FRACHTKOSTEN","FRACHTKOSTEN","NVARCHAR2",11,11,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     geschaeftsart("GESCHAEFTSART","GESCHAEFTSART","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     id_intrastat_meldung("ID_INTRASTAT_MELDUNG","ID_INTRASTAT_MELDUNG","NUMBER",11,10,0,false,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",11,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     land_ursprung("LAND_URSPRUNG","LAND_URSPRUNG","NVARCHAR2",3,3,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     masseinheit("MASSEINHEIT","MASSEINHEIT","NVARCHAR2",11,11,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     meldeart("MELDEART","MELDEART","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     nicht_melden("NICHT_MELDEN","NICHT_MELDEN","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     paginiernummer("PAGINIERNUMMER","PAGINIERNUMMER","NVARCHAR2",6,6,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     preistyp("PREISTYP","PREISTYP","NVARCHAR2",2,2,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     rechbetrag("RECHBETRAG","RECHBETRAG","NVARCHAR2",11,11,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     statistischer_wert("STATISTISCHER_WERT","STATISTISCHER_WERT","NVARCHAR2",11,11,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     steuernr("STEUERNR","STEUERNR","NVARCHAR2",11,11,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     umsatzsteuerid("UMSATZSTEUERID","UMSATZSTEUERID","NVARCHAR2",20,20,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     umsatzsteuer_lkz("UMSATZSTEUER_LKZ","UMSATZSTEUER_LKZ","NVARCHAR2",10,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     unterscheidungsnr("UNTERSCHEIDUNGSNR","UNTERSCHEIDUNGSNR","NVARCHAR2",3,3,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     verkehrszweig("VERKEHRSZWEIG","VERKEHRSZWEIG","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     waehrungskennziffer("WAEHRUNGSKENNZIFFER","WAEHRUNGSKENNZIFFER","NVARCHAR2",1,1,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     warennr("WARENNR","WARENNR","NVARCHAR2",8,8,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
     zaehler_meldung("ZAEHLER_MELDUNG","ZAEHLER_MELDUNG","NUMBER",11,10,0,true,_TAB.intrastat_meldung.fullTableName(),_TAB.intrastat_meldung.baseTableName(),false),
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

     private INTRASTAT_MELDUNG( String   p_fieldName,  
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

         for (IF_Field field: INTRASTAT_MELDUNG.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.intrastat_meldung.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.intrastat_meldung.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_INTRASTAT_MELDUNG 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.intrastat_meldung.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_INTRASTAT_MELDUNG AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.intrastat_meldung.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_INTRASTAT_MELDUNG AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.intrastat_meldung; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_INTRASTAT_MELDUNG AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.intrastat_meldung; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: INTRASTAT_MELDUNG.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
