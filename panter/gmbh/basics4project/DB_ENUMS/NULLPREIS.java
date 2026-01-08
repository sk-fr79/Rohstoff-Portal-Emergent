package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum NULLPREIS implements IF_Field {


     erledigt("ERLEDIGT","ERLEDIGT","NVARCHAR2",1,1,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     geaendertvon("GEAENDERTVON","GEAENDERTVON","NVARCHAR2",10,10,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     id_nullpreis("ID_NULLPREIS","ID_NULLPREIS","NUMBER",11,10,0,false,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),true),
     kontrakt_nr("KONTRAKT_NR","KONTRAKT_NR","NUMBER",11,10,0,false,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     letzteaenderung("LETZTEAENDERUNG","LETZTEAENDERUNG","DATE",7,0,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_bezug("NP_BEZUG","NP_BEZUG","NVARCHAR2",39,39,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_bezug2("NP_BEZUG2","NP_BEZUG2","NVARCHAR2",40,40,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_ca_menge("NP_CA_MENGE","NP_CA_MENGE","NUMBER",11,10,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_lfrbed_text("NP_LFRBED_TEXT","NP_LFRBED_TEXT","NVARCHAR2",55,55,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_lieferadresse("NP_LIEFERADRESSE","NP_LIEFERADRESSE","NVARCHAR2",20,20,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_lieferort("NP_LIEFERORT","NP_LIEFERORT","NVARCHAR2",20,20,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     np_preis("NP_PREIS","NP_PREIS","NUMBER",10,8,2,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_bezug("OLD_BEZUG","OLD_BEZUG","NVARCHAR2",39,39,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_bezug2("OLD_BEZUG2","OLD_BEZUG2","NVARCHAR2",40,40,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_ca_menge("OLD_CA_MENGE","OLD_CA_MENGE","NUMBER",11,10,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_lfrbed_text("OLD_LFRBED_TEXT","OLD_LFRBED_TEXT","NVARCHAR2",55,55,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_lieferadresse("OLD_LIEFERADRESSE","OLD_LIEFERADRESSE","NVARCHAR2",20,20,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_lieferort("OLD_LIEFERORT","OLD_LIEFERORT","NVARCHAR2",20,20,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     old_preis("OLD_PREIS","OLD_PREIS","NUMBER",10,8,2,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     typ("TYP","TYP","NVARCHAR2",1,1,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     zusatzinfo1("ZUSATZINFO1","ZUSATZINFO1","NVARCHAR2",100,100,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     zusatzinfo2("ZUSATZINFO2","ZUSATZINFO2","NVARCHAR2",100,100,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     zusatzinfo3("ZUSATZINFO3","ZUSATZINFO3","NVARCHAR2",100,100,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     zusatzinfo4("ZUSATZINFO4","ZUSATZINFO4","NVARCHAR2",100,100,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
     zusatzinfo5("ZUSATZINFO5","ZUSATZINFO5","NVARCHAR2",100,100,0,true,_TAB.nullpreis.fullTableName(),_TAB.nullpreis.baseTableName(),false),
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

     private NULLPREIS( String   p_fieldName,  
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

         for (IF_Field field: NULLPREIS.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.nullpreis.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.nullpreis.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_NULLPREIS 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.nullpreis.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_NULLPREIS AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.nullpreis.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_NULLPREIS AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.nullpreis; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_NULLPREIS AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.nullpreis; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: NULLPREIS.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
