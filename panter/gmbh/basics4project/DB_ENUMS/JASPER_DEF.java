package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum JASPER_DEF implements IF_Field {


     allow_csv("ALLOW_CSV","ALLOW_CSV","NVARCHAR2",1,1,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     allow_html("ALLOW_HTML","ALLOW_HTML","NVARCHAR2",1,1,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     allow_pdf("ALLOW_PDF","ALLOW_PDF","NVARCHAR2",1,1,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     allow_xls("ALLOW_XLS","ALLOW_XLS","NVARCHAR2",1,1,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     allow_xml("ALLOW_XML","ALLOW_XML","NVARCHAR2",1,1,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     description1("DESCRIPTION1","DESCRIPTION1","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     description2("DESCRIPTION2","DESCRIPTION2","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     description3("DESCRIPTION3","DESCRIPTION3","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     description4("DESCRIPTION4","DESCRIPTION4","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     description5("DESCRIPTION5","DESCRIPTION5","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     id_jasper_def("ID_JASPER_DEF","ID_JASPER_DEF","NUMBER",11,10,0,false,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     name_of_jasperfile("NAME_OF_JASPERFILE","NAME_OF_JASPERFILE","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param1_default("PARAM1_DEFAULT","PARAM1_DEFAULT","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param1_definition("PARAM1_DEFINITION","PARAM1_DEFINITION","NVARCHAR2",300,300,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param1_description("PARAM1_DESCRIPTION","PARAM1_DESCRIPTION","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param1_name("PARAM1_NAME","PARAM1_NAME","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param1_type("PARAM1_TYPE","PARAM1_TYPE","NVARCHAR2",30,30,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param2_default("PARAM2_DEFAULT","PARAM2_DEFAULT","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param2_definition("PARAM2_DEFINITION","PARAM2_DEFINITION","NVARCHAR2",300,300,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param2_description("PARAM2_DESCRIPTION","PARAM2_DESCRIPTION","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param2_name("PARAM2_NAME","PARAM2_NAME","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param2_type("PARAM2_TYPE","PARAM2_TYPE","NVARCHAR2",30,30,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param3_default("PARAM3_DEFAULT","PARAM3_DEFAULT","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param3_definition("PARAM3_DEFINITION","PARAM3_DEFINITION","NVARCHAR2",300,300,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param3_description("PARAM3_DESCRIPTION","PARAM3_DESCRIPTION","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param3_name("PARAM3_NAME","PARAM3_NAME","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param3_type("PARAM3_TYPE","PARAM3_TYPE","NVARCHAR2",30,30,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param4_default("PARAM4_DEFAULT","PARAM4_DEFAULT","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param4_definition("PARAM4_DEFINITION","PARAM4_DEFINITION","NVARCHAR2",300,300,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param4_description("PARAM4_DESCRIPTION","PARAM4_DESCRIPTION","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param4_name("PARAM4_NAME","PARAM4_NAME","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param4_type("PARAM4_TYPE","PARAM4_TYPE","NVARCHAR2",30,30,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param5_default("PARAM5_DEFAULT","PARAM5_DEFAULT","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param5_definition("PARAM5_DEFINITION","PARAM5_DEFINITION","NVARCHAR2",300,300,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param5_description("PARAM5_DESCRIPTION","PARAM5_DESCRIPTION","NVARCHAR2",50,50,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param5_name("PARAM5_NAME","PARAM5_NAME","NVARCHAR2",20,20,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     param5_type("PARAM5_TYPE","PARAM5_TYPE","NVARCHAR2",30,30,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     report_name("REPORT_NAME","REPORT_NAME","NVARCHAR2",30,30,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.jasper_def.fullTableName(),_TAB.jasper_def.baseTableName(),false),
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

     private JASPER_DEF( String   p_fieldName,  
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

         for (IF_Field field: JASPER_DEF.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.jasper_def.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.jasper_def.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_JASPER_DEF 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.jasper_def.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_JASPER_DEF AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.jasper_def.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_JASPER_DEF AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.jasper_def; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_JASPER_DEF AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.jasper_def; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: JASPER_DEF.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
