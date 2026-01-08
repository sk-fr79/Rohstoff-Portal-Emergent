package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum ARTIKEL implements IF_Field {


     aktiv("AKTIV","AKTIV","NVARCHAR2",1,1,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     anhang7_3a_code("ANHANG7_3A_CODE","ANHANG7_3A_CODE","NVARCHAR2",20,20,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     anhang7_3a_text("ANHANG7_3A_TEXT","ANHANG7_3A_TEXT","NVARCHAR2",1000,1000,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     anhang7_3b_code("ANHANG7_3B_CODE","ANHANG7_3B_CODE","NVARCHAR2",20,20,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     anhang7_3b_text("ANHANG7_3B_TEXT","ANHANG7_3B_TEXT","NVARCHAR2",1000,1000,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     anr1("ANR1","ANR1","NVARCHAR2",10,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     artbez1("ARTBEZ1","ARTBEZ1","NVARCHAR2",80,80,0,false,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     artbez2("ARTBEZ2","ARTBEZ2","NVARCHAR2",1000,1000,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     basel_code("BASEL_CODE","BASEL_CODE","NVARCHAR2",80,80,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     basel_notiz("BASEL_NOTIZ","BASEL_NOTIZ","NVARCHAR2",500,500,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     bemerkung_intern("BEMERKUNG_INTERN","BEMERKUNG_INTERN","NVARCHAR2",1000,1000,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     dienstleistung("DIENSTLEISTUNG","DIENSTLEISTUNG","NVARCHAR2",1,1,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     end_of_waste("END_OF_WASTE","END_OF_WASTE","NVARCHAR2",1,1,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     eucode("EUCODE","EUCODE","NVARCHAR2",50,50,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     eunotiz("EUNOTIZ","EUNOTIZ","NVARCHAR2",500,500,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     gefahrgut("GEFAHRGUT","GEFAHRGUT","NVARCHAR2",1,1,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     genauigkeit_mengen("GENAUIGKEIT_MENGEN","GENAUIGKEIT_MENGEN","NUMBER",2,1,0,false,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,false,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),true),
     id_artikel_gruppe("ID_ARTIKEL_GRUPPE","ID_ARTIKEL_GRUPPE","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_artikel_gruppe_fibu("ID_ARTIKEL_GRUPPE_FIBU","ID_ARTIKEL_GRUPPE_FIBU","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_eak_code("ID_EAK_CODE","ID_EAK_CODE","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_eak_code_ex_mandant("ID_EAK_CODE_EX_MANDANT","ID_EAK_CODE_EX_MANDANT","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_einheit("ID_EINHEIT","ID_EINHEIT","NUMBER",11,10,0,false,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_einheit_preis("ID_EINHEIT_PREIS","ID_EINHEIT_PREIS","NUMBER",11,10,0,false,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     id_zolltarifnummer("ID_ZOLLTARIFNUMMER","ID_ZOLLTARIFNUMMER","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     ist_leergut("IST_LEERGUT","IST_LEERGUT","NVARCHAR2",1,1,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     ist_produkt("IST_PRODUKT","IST_PRODUKT","NVARCHAR2",1,1,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     mengendivisor("MENGENDIVISOR","MENGENDIVISOR","NUMBER",11,10,0,false,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     zolltarifnotiz("ZOLLTARIFNOTIZ","ZOLLTARIFNOTIZ","NVARCHAR2",500,500,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
     zolltarifnr("ZOLLTARIFNR","ZOLLTARIFNR","NVARCHAR2",50,50,0,true,_TAB.artikel.fullTableName(),_TAB.artikel.baseTableName(),false),
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

     private ARTIKEL( String   p_fieldName,  
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

         for (IF_Field field: ARTIKEL.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.artikel.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.artikel.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_ARTIKEL 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.artikel.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_ARTIKEL AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.artikel.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ARTIKEL AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.artikel; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ARTIKEL AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.artikel; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: ARTIKEL.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
