package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum REPORT implements IF_Field {


     aktiv("AKTIV","AKTIV","NVARCHAR2",1,1,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_email_employes("ALLOW_EMAIL_EMPLOYES","ALLOW_EMAIL_EMPLOYES","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_email_free("ALLOW_EMAIL_FREE","ALLOW_EMAIL_FREE","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_email_search_customer("ALLOW_EMAIL_SEARCH_CUSTOMER","ALLOW_EMAIL_SEARCH_CUSTOMER","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_html("ALLOW_HTML","ALLOW_HTML","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_pdf("ALLOW_PDF","ALLOW_PDF","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_txt("ALLOW_TXT","ALLOW_TXT","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_xls("ALLOW_XLS","ALLOW_XLS","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     allow_xml("ALLOW_XML","ALLOW_XML","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     beschreibung("BESCHREIBUNG","BESCHREIBUNG","NVARCHAR2",800,800,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     buttontext("BUTTONTEXT","BUTTONTEXT","NVARCHAR2",50,50,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     button_auth_kenner("BUTTON_AUTH_KENNER","BUTTON_AUTH_KENNER","NVARCHAR2",50,50,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     geschaeftsfuehrer("GESCHAEFTSFUEHRER","GESCHAEFTSFUEHRER","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     id_report("ID_REPORT","ID_REPORT","NUMBER",11,10,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),true),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     module_kenner("MODULE_KENNER","MODULE_KENNER","NVARCHAR2",50,50,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     name_of_reportfile("NAME_OF_REPORTFILE","NAME_OF_REPORTFILE","NVARCHAR2",200,200,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     password("PASSWORD","PASSWORD","NVARCHAR2",30,30,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     pass_multi_id("PASS_MULTI_ID","PASS_MULTI_ID","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     pass_no_id("PASS_NO_ID","PASS_NO_ID","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     pass_single_id("PASS_SINGLE_ID","PASS_SINGLE_ID","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     prefer_html("PREFER_HTML","PREFER_HTML","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     prefer_pdf("PREFER_PDF","PREFER_PDF","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     prefer_txt("PREFER_TXT","PREFER_TXT","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     prefer_xls("PREFER_XLS","PREFER_XLS","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     prefer_xml("PREFER_XML","PREFER_XML","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     static_mail_adresses("STATIC_MAIL_ADRESSES","STATIC_MAIL_ADRESSES","NVARCHAR2",1000,1000,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     supervisor("SUPERVISOR","SUPERVISOR","NVARCHAR2",1,1,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
     titel("TITEL","TITEL","NVARCHAR2",100,100,0,false,_TAB.report.fullTableName(),_TAB.report.baseTableName(),false),
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

     private REPORT( String   p_fieldName,  
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

         for (IF_Field field: REPORT.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.report.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.report.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_REPORT 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.report.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_REPORT AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.report.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_REPORT AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.report; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_REPORT AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.report; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: REPORT.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
