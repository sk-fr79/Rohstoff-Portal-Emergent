package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum MAIL_AUS_MASK_JASPER implements IF_Field {


     downloadname("DOWNLOADNAME","DOWNLOADNAME","NVARCHAR2",100,100,0,false,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     id_mail_aus_mask("ID_MAIL_AUS_MASK","ID_MAIL_AUS_MASK","NUMBER",11,10,0,false,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     id_mail_aus_mask_jasper("ID_MAIL_AUS_MASK_JASPER","ID_MAIL_AUS_MASK_JASPER","NUMBER",11,10,0,false,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter01("PARAMETER01","PARAMETER01","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter02("PARAMETER02","PARAMETER02","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter03("PARAMETER03","PARAMETER03","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter04("PARAMETER04","PARAMETER04","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter05("PARAMETER05","PARAMETER05","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter06("PARAMETER06","PARAMETER06","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter07("PARAMETER07","PARAMETER07","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter08("PARAMETER08","PARAMETER08","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter09("PARAMETER09","PARAMETER09","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter10("PARAMETER10","PARAMETER10","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter11("PARAMETER11","PARAMETER11","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter12("PARAMETER12","PARAMETER12","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter13("PARAMETER13","PARAMETER13","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter14("PARAMETER14","PARAMETER14","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter15("PARAMETER15","PARAMETER15","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter16("PARAMETER16","PARAMETER16","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter17("PARAMETER17","PARAMETER17","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter18("PARAMETER18","PARAMETER18","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter19("PARAMETER19","PARAMETER19","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     parameter20("PARAMETER20","PARAMETER20","NVARCHAR2",100,100,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     reportname("REPORTNAME","REPORTNAME","NVARCHAR2",100,100,0,false,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert01("WERT01","WERT01","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert02("WERT02","WERT02","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert03("WERT03","WERT03","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert04("WERT04","WERT04","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert05("WERT05","WERT05","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert06("WERT06","WERT06","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert07("WERT07","WERT07","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert08("WERT08","WERT08","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert09("WERT09","WERT09","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert10("WERT10","WERT10","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert11("WERT11","WERT11","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert12("WERT12","WERT12","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert13("WERT13","WERT13","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert14("WERT14","WERT14","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert15("WERT15","WERT15","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert16("WERT16","WERT16","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert17("WERT17","WERT17","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert18("WERT18","WERT18","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert19("WERT19","WERT19","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
     wert20("WERT20","WERT20","NVARCHAR2",500,500,0,true,_TAB.mail_aus_mask_jasper.fullTableName(),_TAB.mail_aus_mask_jasper.baseTableName(),false),
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

     private MAIL_AUS_MASK_JASPER( String   p_fieldName,  
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

         for (IF_Field field: MAIL_AUS_MASK_JASPER.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.mail_aus_mask_jasper.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.mail_aus_mask_jasper.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_MAIL_AUS_MASK_JASPER 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.mail_aus_mask_jasper.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_MAIL_AUS_MASK_JASPER AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.mail_aus_mask_jasper.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_MAIL_AUS_MASK_JASPER AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.mail_aus_mask_jasper; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_MAIL_AUS_MASK_JASPER AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.mail_aus_mask_jasper; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: MAIL_AUS_MASK_JASPER.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
