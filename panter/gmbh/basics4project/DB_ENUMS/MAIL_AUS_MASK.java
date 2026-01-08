package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum MAIL_AUS_MASK implements IF_Field {


     basistabelle("BASISTABELLE","BASISTABELLE","NVARCHAR2",50,50,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     betreff("BETREFF","BETREFF","NVARCHAR2",500,500,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     buttonbeschriftung("BUTTONBESCHRIFTUNG","BUTTONBESCHRIFTUNG","NVARCHAR2",100,100,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     buttonkenner("BUTTONKENNER","BUTTONKENNER","NVARCHAR2",30,30,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erlaube_freiemailadresse("ERLAUBE_FREIEMAILADRESSE","ERLAUBE_FREIEMAILADRESSE","NVARCHAR2",1,1,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erlaubt_bei_edit("ERLAUBT_BEI_EDIT","ERLAUBT_BEI_EDIT","NVARCHAR2",1,1,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erlaubt_bei_neueingabe("ERLAUBT_BEI_NEUEINGABE","ERLAUBT_BEI_NEUEINGABE","NVARCHAR2",1,1,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erlaubt_bei_undef("ERLAUBT_BEI_UNDEF","ERLAUBT_BEI_UNDEF","NVARCHAR2",1,1,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erlaubt_bei_view("ERLAUBT_BEI_VIEW","ERLAUBT_BEI_VIEW","NVARCHAR2",1,1,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     groovy_bedingung("GROOVY_BEDINGUNG","GROOVY_BEDINGUNG","NVARCHAR2",2000,2000,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     id_mail_aus_mask("ID_MAIL_AUS_MASK","ID_MAIL_AUS_MASK","NUMBER",11,10,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     mailtext("MAILTEXT","MAILTEXT","NVARCHAR2",2000,2000,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     modulkenner("MODULKENNER","MODULKENNER","NVARCHAR2",100,100,0,false,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     sicherheitsabfrage_vor_start("SICHERHEITSABFRAGE_VOR_START","SICHERHEITSABFRAGE_VOR_START","NVARCHAR2",400,400,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     signatur_anhaengen("SIGNATUR_ANHAENGEN","SIGNATUR_ANHAENGEN","NVARCHAR2",1,1,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.mail_aus_mask.fullTableName(),_TAB.mail_aus_mask.baseTableName(),false),
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

     private MAIL_AUS_MASK( String   p_fieldName,  
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

         for (IF_Field field: MAIL_AUS_MASK.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.mail_aus_mask.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.mail_aus_mask.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_MAIL_AUS_MASK 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.mail_aus_mask.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_MAIL_AUS_MASK AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.mail_aus_mask.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_MAIL_AUS_MASK AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.mail_aus_mask; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_MAIL_AUS_MASK AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.mail_aus_mask; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: MAIL_AUS_MASK.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
