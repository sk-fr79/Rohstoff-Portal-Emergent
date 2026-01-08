package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum USER implements IF_Field {


     aktiv("AKTIV","AKTIV","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     anrede("ANREDE","ANREDE","NVARCHAR2",30,30,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     autcode("AUTCODE","AUTCODE","NVARCHAR2",30,30,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_error_blue("COLOR_MASK_ERROR_BLUE","COLOR_MASK_ERROR_BLUE","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_error_green("COLOR_MASK_ERROR_GREEN","COLOR_MASK_ERROR_GREEN","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_error_red("COLOR_MASK_ERROR_RED","COLOR_MASK_ERROR_RED","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_ok_blue("COLOR_MASK_OK_BLUE","COLOR_MASK_OK_BLUE","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_ok_green("COLOR_MASK_OK_GREEN","COLOR_MASK_OK_GREEN","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_ok_red("COLOR_MASK_OK_RED","COLOR_MASK_OK_RED","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_warn_blue("COLOR_MASK_WARN_BLUE","COLOR_MASK_WARN_BLUE","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_warn_green("COLOR_MASK_WARN_GREEN","COLOR_MASK_WARN_GREEN","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     color_mask_warn_red("COLOR_MASK_WARN_RED","COLOR_MASK_WARN_RED","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     developer("DEVELOPER","DEVELOPER","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     eigendef_breiteaenderbar("EIGENDEF_BREITEAENDERBAR","EIGENDEF_BREITEAENDERBAR","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     eigendef_menuebreite("EIGENDEF_MENUEBREITE","EIGENDEF_MENUEBREITE","NUMBER",6,5,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     eigendef_schriftgroesse("EIGENDEF_SCHRIFTGROESSE","EIGENDEF_SCHRIFTGROESSE","NUMBER",6,5,0,false,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     email("EMAIL","EMAIL","NVARCHAR2",100,100,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     fenster_mit_schatten("FENSTER_MIT_SCHATTEN","FENSTER_MIT_SCHATTEN","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     geschaeftsfuehrer("GESCHAEFTSFUEHRER","GESCHAEFTSFUEHRER","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     hat_fahrplan_button("HAT_FAHRPLAN_BUTTON","HAT_FAHRPLAN_BUTTON","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     id_datev_profile("ID_DATEV_PROFILE","ID_DATEV_PROFILE","NUMBER",11,10,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     id_sprache("ID_SPRACHE","ID_SPRACHE","NUMBER",11,10,0,false,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     id_user("ID_USER","ID_USER","NUMBER",11,10,0,false,_TAB.user.fullTableName(),_TAB.user.baseTableName(),true),
     id_usergroup("ID_USERGROUP","ID_USERGROUP","NUMBER",11,10,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     ist_fahrer("IST_FAHRER","IST_FAHRER","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     ist_supervisor("IST_SUPERVISOR","IST_SUPERVISOR","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     ist_verwaltung("IST_VERWALTUNG","IST_VERWALTUNG","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     kuerzel("KUERZEL","KUERZEL","NVARCHAR2",10,10,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     laufzeit_session("LAUFZEIT_SESSION","LAUFZEIT_SESSION","NUMBER",6,5,0,false,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     listegesamtlaenge("LISTEGESAMTLAENGE","LISTEGESAMTLAENGE","NUMBER",6,5,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     listeseitelaenge("LISTESEITELAENGE","LISTESEITELAENGE","NUMBER",6,5,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     mail_signatur("MAIL_SIGNATUR","MAIL_SIGNATUR","NVARCHAR2",1200,1200,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     meldung_kreditvers_ablauf("MELDUNG_KREDITVERS_ABLAUF","MELDUNG_KREDITVERS_ABLAUF","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     meldung_kreditvers_betrag("MELDUNG_KREDITVERS_BETRAG","MELDUNG_KREDITVERS_BETRAG","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     mengenabschluss_fuhre_ok("MENGENABSCHLUSS_FUHRE_OK","MENGENABSCHLUSS_FUHRE_OK","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     name("NAME","NAME","NVARCHAR2",50,50,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     name1("NAME1","NAME1","NVARCHAR2",30,30,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     name2("NAME2","NAME2","NVARCHAR2",30,30,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     name3("NAME3","NAME3","NVARCHAR2",30,30,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     passwort("PASSWORT","PASSWORT","NVARCHAR2",20,20,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     preisabschluss_fuhre_ok("PREISABSCHLUSS_FUHRE_OK","PREISABSCHLUSS_FUHRE_OK","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     sonderrech_zeige_opliste_saldo("SONDERRECH_ZEIGE_OPLISTE_SALDO","SONDERRECH_ZEIGE_OPLISTE_SALDO","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     telefax("TELEFAX","TELEFAX","NVARCHAR2",50,50,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     telefon("TELEFON","TELEFON","NVARCHAR2",50,50,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     textcollector("TEXTCOLLECTOR","TEXTCOLLECTOR","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     todo_supervisor("TODO_SUPERVISOR","TODO_SUPERVISOR","NVARCHAR2",1,1,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     vertical_offset_masktabs("VERTICAL_OFFSET_MASKTABS","VERTICAL_OFFSET_MASKTABS","NUMBER",4,3,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
     vorname("VORNAME","VORNAME","NVARCHAR2",50,50,0,true,_TAB.user.fullTableName(),_TAB.user.baseTableName(),false),
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

     private USER( String   p_fieldName,  
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

         for (IF_Field field: USER.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.user.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.user.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JD_USER 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.user.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JD_USER AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.user.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JD_USER AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.user; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JD_USER AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.user; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: USER.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
