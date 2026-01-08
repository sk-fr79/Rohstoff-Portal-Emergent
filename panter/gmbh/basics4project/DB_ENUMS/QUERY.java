package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum QUERY implements IF_Field {


     anzeigesql("ANZEIGESQL","ANZEIGESQL","NVARCHAR2",1,1,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     beschreib1("BESCHREIB1","BESCHREIB1","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     beschreib2("BESCHREIB2","BESCHREIB2","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     beschreib3("BESCHREIB3","BESCHREIB3","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     beschreib4("BESCHREIB4","BESCHREIB4","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     default01("DEFAULT01","DEFAULT01","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     default02("DEFAULT02","DEFAULT02","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     default03("DEFAULT03","DEFAULT03","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     default04("DEFAULT04","DEFAULT04","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     default05("DEFAULT05","DEFAULT05","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     default06("DEFAULT06","DEFAULT06","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     download("DOWNLOAD","DOWNLOAD","NVARCHAR2",1,1,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     id_query("ID_QUERY","ID_QUERY","NUMBER",11,10,0,false,_TAB.query.fullTableName(),_TAB.query.baseTableName(),true),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     liste_titel("LISTE_TITEL","LISTE_TITEL","NVARCHAR2",300,300,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     name("NAME","NAME","NVARCHAR2",30,30,0,false,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     param01("PARAM01","PARAM01","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     param02("PARAM02","PARAM02","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     param03("PARAM03","PARAM03","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     param04("PARAM04","PARAM04","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     param05("PARAM05","PARAM05","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     param06("PARAM06","PARAM06","NVARCHAR2",80,80,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     paramdropdowndef01("PARAMDROPDOWNDEF01","PARAMDROPDOWNDEF01","NVARCHAR2",400,400,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     paramdropdowndef02("PARAMDROPDOWNDEF02","PARAMDROPDOWNDEF02","NVARCHAR2",400,400,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     paramdropdowndef03("PARAMDROPDOWNDEF03","PARAMDROPDOWNDEF03","NVARCHAR2",400,400,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     paramdropdowndef04("PARAMDROPDOWNDEF04","PARAMDROPDOWNDEF04","NVARCHAR2",400,400,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     paramdropdowndef05("PARAMDROPDOWNDEF05","PARAMDROPDOWNDEF05","NVARCHAR2",400,400,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     paramdropdowndef06("PARAMDROPDOWNDEF06","PARAMDROPDOWNDEF06","NVARCHAR2",400,400,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sqlfeldliste("SQLFELDLISTE","SQLFELDLISTE","NVARCHAR2",800,800,0,false,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sqlfromblock("SQLFROMBLOCK","SQLFROMBLOCK","NVARCHAR2",2000,2000,0,false,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sqlindexfeld("SQLINDEXFELD","SQLINDEXFELD","NVARCHAR2",50,50,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sqlorderblock("SQLORDERBLOCK","SQLORDERBLOCK","NVARCHAR2",200,200,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sqlwhereblock("SQLWHEREBLOCK","SQLWHEREBLOCK","NVARCHAR2",1600,1600,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.query.fullTableName(),_TAB.query.baseTableName(),false),
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

     private QUERY( String   p_fieldName,  
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

         for (IF_Field field: QUERY.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.query.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.query.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_QUERY 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.query.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_QUERY AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.query.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_QUERY AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.query; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_QUERY AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.query; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: QUERY.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
