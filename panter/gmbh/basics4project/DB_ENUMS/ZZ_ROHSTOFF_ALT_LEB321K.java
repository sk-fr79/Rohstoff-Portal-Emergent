package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum ZZ_ROHSTOFF_ALT_LEB321K implements IF_Field {


     abgeschlossen("ABGESCHLOSSEN","ABGESCHLOSSEN","VARCHAR2",1,1,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     bezug("BEZUG","BEZUG","VARCHAR2",50,50,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     bezug2("BEZUG2","BEZUG2","VARCHAR2",40,40,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     datum("DATUM","DATUM","DATE",7,0,0,false,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     druckkennz("DRUCKKENNZ","DRUCKKENNZ","NUMBER",3,2,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     erfasser("ERFASSER","ERFASSER","VARCHAR2",3,3,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     id_zz_rohstoff_alt_leb321k("ID_ZZ_ROHSTOFF_ALT_LEB321K","ID_ZZ_ROHSTOFF_ALT_LEB321K","NUMBER",11,10,0,false,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),true),
     kontraktpartner("KONTRAKTPARTNER","KONTRAKTPARTNER","VARCHAR2",40,40,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     kontrakt_nr("KONTRAKT_NR","KONTRAKT_NR","NUMBER",11,10,0,false,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lager("LAGER","LAGER","NUMBER",3,2,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lfrbed("LFRBED","LFRBED","NUMBER",3,2,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lfrbed_text("LFRBED_TEXT","LFRBED_TEXT","VARCHAR2",55,55,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lieferadresse("LIEFERADRESSE","LIEFERADRESSE","VARCHAR2",30,30,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lieferort("LIEFERORT","LIEFERORT","VARCHAR2",40,40,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lieferzeit("LIEFERZEIT","LIEFERZEIT","VARCHAR2",20,20,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     lief_nr("LIEF_NR","LIEF_NR","VARCHAR2",16,16,0,false,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     preisstellung("PREISSTELLUNG","PREISSTELLUNG","VARCHAR2",55,55,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     recnum("RECNUM","RECNUM","NUMBER",9,8,0,false,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     statuskennz("STATUSKENNZ","STATUSKENNZ","VARCHAR2",1,1,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     textbausteinnr("TEXTBAUSTEINNR","TEXTBAUSTEINNR","NUMBER",5,4,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     transportmittel("TRANSPORTMITTEL","TRANSPORTMITTEL","VARCHAR2",1,1,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     verkaeufer("VERKAEUFER","VERKAEUFER","VARCHAR2",20,20,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     zlgbed("ZLGBED","ZLGBED","NUMBER",3,2,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
     zlgbed_text("ZLGBED_TEXT","ZLGBED_TEXT","VARCHAR2",55,55,0,true,_TAB.zz_rohstoff_alt_leb321k.fullTableName(),_TAB.zz_rohstoff_alt_leb321k.baseTableName(),false),
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

     private ZZ_ROHSTOFF_ALT_LEB321K( String   p_fieldName,  
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

         for (IF_Field field: ZZ_ROHSTOFF_ALT_LEB321K.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.zz_rohstoff_alt_leb321k.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.zz_rohstoff_alt_leb321k.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_ZZ_ROHSTOFF_ALT_LEB321K 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.zz_rohstoff_alt_leb321k.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_ZZ_ROHSTOFF_ALT_LEB321K AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.zz_rohstoff_alt_leb321k.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ZZ_ROHSTOFF_ALT_LEB321K AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.zz_rohstoff_alt_leb321k; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ZZ_ROHSTOFF_ALT_LEB321K AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.zz_rohstoff_alt_leb321k; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: ZZ_ROHSTOFF_ALT_LEB321K.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
