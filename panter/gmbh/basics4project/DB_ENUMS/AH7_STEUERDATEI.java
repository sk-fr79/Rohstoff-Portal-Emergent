package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum AH7_STEUERDATEI implements IF_Field {


     drucke_blatt2("DRUCKE_BLATT2","DRUCKE_BLATT2","NVARCHAR2",1,1,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     drucke_blatt3("DRUCKE_BLATT3","DRUCKE_BLATT3","NVARCHAR2",1,1,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_1_abfallerzeuger("ID_1_ABFALLERZEUGER","ID_1_ABFALLERZEUGER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_1_import_empfaenger("ID_1_IMPORT_EMPFAENGER","ID_1_IMPORT_EMPFAENGER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_1_verbr_veranlasser("ID_1_VERBR_VERANLASSER","ID_1_VERBR_VERANLASSER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_1_verwertungsanlage("ID_1_VERWERTUNGSANLAGE","ID_1_VERWERTUNGSANLAGE","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_2_abfallerzeuger("ID_2_ABFALLERZEUGER","ID_2_ABFALLERZEUGER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_2_import_empfaenger("ID_2_IMPORT_EMPFAENGER","ID_2_IMPORT_EMPFAENGER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_2_verbr_veranlasser("ID_2_VERBR_VERANLASSER","ID_2_VERBR_VERANLASSER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_2_verwertungsanlage("ID_2_VERWERTUNGSANLAGE","ID_2_VERWERTUNGSANLAGE","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_3_abfallerzeuger("ID_3_ABFALLERZEUGER","ID_3_ABFALLERZEUGER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_3_import_empfaenger("ID_3_IMPORT_EMPFAENGER","ID_3_IMPORT_EMPFAENGER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_3_verbr_veranlasser("ID_3_VERBR_VERANLASSER","ID_3_VERBR_VERANLASSER","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_3_verwertungsanlage("ID_3_VERWERTUNGSANLAGE","ID_3_VERWERTUNGSANLAGE","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_adresse_geo_start("ID_ADRESSE_GEO_START","ID_ADRESSE_GEO_START","NUMBER",11,10,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_adresse_geo_ziel("ID_ADRESSE_GEO_ZIEL","ID_ADRESSE_GEO_ZIEL","NUMBER",11,10,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_adresse_jur_start("ID_ADRESSE_JUR_START","ID_ADRESSE_JUR_START","NUMBER",11,10,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_adresse_jur_ziel("ID_ADRESSE_JUR_ZIEL","ID_ADRESSE_JUR_ZIEL","NUMBER",11,10,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_ah7_profil("ID_AH7_PROFIL","ID_AH7_PROFIL","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     id_ah7_steuerdatei("ID_AH7_STEUERDATEI","ID_AH7_STEUERDATEI","NUMBER",11,10,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     locked("LOCKED","LOCKED","NVARCHAR2",1,1,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     status_relation("STATUS_RELATION","STATUS_RELATION","NVARCHAR2",10,10,0,false,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.ah7_steuerdatei.fullTableName(),_TAB.ah7_steuerdatei.baseTableName(),false),
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

     private AH7_STEUERDATEI( String   p_fieldName,  
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

         for (IF_Field field: AH7_STEUERDATEI.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.ah7_steuerdatei.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.ah7_steuerdatei.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_AH7_STEUERDATEI 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.ah7_steuerdatei.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_AH7_STEUERDATEI AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.ah7_steuerdatei.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_AH7_STEUERDATEI AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.ah7_steuerdatei; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_AH7_STEUERDATEI AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.ah7_steuerdatei; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: AH7_STEUERDATEI.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
