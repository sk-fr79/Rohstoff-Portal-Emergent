package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum STATUS_LAGER implements IF_Field {


     avg_wert_gesamt("AVG_WERT_GESAMT","AVG_WERT_GESAMT","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     avg_wert_mit_null("AVG_WERT_MIT_NULL","AVG_WERT_MIT_NULL","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     avg_wert_nicht_null("AVG_WERT_NICHT_NULL","AVG_WERT_NICHT_NULL","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     buchungsdatum("BUCHUNGSDATUM","BUCHUNGSDATUM","DATE",7,0,0,false,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",20,20,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,false,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     id_sorte("ID_SORTE","ID_SORTE","NUMBER",11,10,0,false,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     id_status_lager("ID_STATUS_LAGER","ID_STATUS_LAGER","NUMBER",11,10,0,false,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),true),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     menge_gesamt("MENGE_GESAMT","MENGE_GESAMT","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     menge_preise_leer("MENGE_PREISE_LEER","MENGE_PREISE_LEER","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     menge_preise_nicht_null("MENGE_PREISE_NICHT_NULL","MENGE_PREISE_NICHT_NULL","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     menge_preise_null("MENGE_PREISE_NULL","MENGE_PREISE_NULL","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     sum_restwert("SUM_RESTWERT","SUM_RESTWERT","NUMBER",40,38,2,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.status_lager.fullTableName(),_TAB.status_lager.baseTableName(),false),
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

     private STATUS_LAGER( String   p_fieldName,  
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

         for (IF_Field field: STATUS_LAGER.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.status_lager.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.status_lager.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_STATUS_LAGER 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.status_lager.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_STATUS_LAGER AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.status_lager.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_STATUS_LAGER AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.status_lager; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_STATUS_LAGER AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.status_lager; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: STATUS_LAGER.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
