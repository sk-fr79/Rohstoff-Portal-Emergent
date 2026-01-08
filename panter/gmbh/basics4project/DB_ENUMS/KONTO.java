package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum KONTO implements IF_Field {


     bic_nr("BIC_NR","BIC_NR","NVARCHAR2",50,50,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     iban_nr("IBAN_NR","IBAN_NR","NVARCHAR2",50,50,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     id_bankenstamm("ID_BANKENSTAMM","ID_BANKENSTAMM","NUMBER",11,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     id_konto("ID_KONTO","ID_KONTO","NUMBER",11,10,0,false,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kk_land("KK_LAND","KK_LAND","NVARCHAR2",60,60,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kk_name("KK_NAME","KK_NAME","NVARCHAR2",60,60,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kk_ort("KK_ORT","KK_ORT","NVARCHAR2",60,60,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kk_plz("KK_PLZ","KK_PLZ","NVARCHAR2",60,60,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kk_strasse("KK_STRASSE","KK_STRASSE","NVARCHAR2",60,60,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kontonummer("KONTONUMMER","KONTONUMMER","NVARCHAR2",20,20,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kontotyp("KONTOTYP","KONTOTYP","NVARCHAR2",20,20,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kreditkartenablaufmm("KREDITKARTENABLAUFMM","KREDITKARTENABLAUFMM","NUMBER",11,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kreditkartenablaufyyyy("KREDITKARTENABLAUFYYYY","KREDITKARTENABLAUFYYYY","NUMBER",11,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kreditkartennummer("KREDITKARTENNUMMER","KREDITKARTENNUMMER","NVARCHAR2",20,20,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kreditkartentyp("KREDITKARTENTYP","KREDITKARTENTYP","NVARCHAR2",20,20,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     kreditkartenvv("KREDITKARTENVV","KREDITKARTENVV","NVARCHAR2",4,4,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.konto.fullTableName(),_TAB.konto.baseTableName(),false),
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

     private KONTO( String   p_fieldName,  
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

         for (IF_Field field: KONTO.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.konto.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.konto.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_KONTO 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.konto.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_KONTO AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.konto.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_KONTO AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.konto; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_KONTO AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.konto; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: KONTO.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
