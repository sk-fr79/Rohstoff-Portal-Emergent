package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum FIRMENINFO implements IF_Field {


     ablademenge_fuer_gutschrift("ABLADEMENGE_FUER_GUTSCHRIFT","ABLADEMENGE_FUER_GUTSCHRIFT","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     akonto("AKONTO","AKONTO","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     aquisitionskosten("AQUISITIONSKOSTEN","AQUISITIONSKOSTEN","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     beschreibung("BESCHREIBUNG","BESCHREIBUNG","NVARCHAR2",255,255,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     besuchsrythmus("BESUCHSRYTHMUS","BESUCHSRYTHMUS","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     betriebsnummer_saa("BETRIEBSNUMMER_SAA","BETRIEBSNUMMER_SAA","NVARCHAR2",20,20,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     debitor_nummer("DEBITOR_NUMMER","DEBITOR_NUMMER","NVARCHAR2",30,30,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     debitor_nummer_alt("DEBITOR_NUMMER_ALT","DEBITOR_NUMMER_ALT","NVARCHAR2",30,30,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     dokumentkopien("DOKUMENTKOPIEN","DOKUMENTKOPIEN","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     fbam_nur_intern("FBAM_NUR_INTERN","FBAM_NUR_INTERN","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     firma("FIRMA","FIRMA","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     firma_ohne_ustid("FIRMA_OHNE_USTID","FIRMA_OHNE_USTID","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     forderungsverrechnung("FORDERUNGSVERRECHNUNG","FORDERUNGSVERRECHNUNG","NVARCHAR2",20,20,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     gruendungsdatum("GRUENDUNGSDATUM","GRUENDUNGSDATUM","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     handelsregister("HANDELSREGISTER","HANDELSREGISTER","NVARCHAR2",50,50,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_branche("ID_BRANCHE","ID_BRANCHE","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_eak_branche("ID_EAK_BRANCHE","ID_EAK_BRANCHE","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_firmeninfo("ID_FIRMENINFO","ID_FIRMENINFO","NUMBER",11,10,0,false,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),true),
     id_kreditlimit2_bedingung1("ID_KREDITLIMIT2_BEDINGUNG1","ID_KREDITLIMIT2_BEDINGUNG1","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit2_bedingung2("ID_KREDITLIMIT2_BEDINGUNG2","ID_KREDITLIMIT2_BEDINGUNG2","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit2_bedingung3("ID_KREDITLIMIT2_BEDINGUNG3","ID_KREDITLIMIT2_BEDINGUNG3","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit3_bedingung1("ID_KREDITLIMIT3_BEDINGUNG1","ID_KREDITLIMIT3_BEDINGUNG1","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit3_bedingung2("ID_KREDITLIMIT3_BEDINGUNG2","ID_KREDITLIMIT3_BEDINGUNG2","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit3_bedingung3("ID_KREDITLIMIT3_BEDINGUNG3","ID_KREDITLIMIT3_BEDINGUNG3","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit_bedingung1("ID_KREDITLIMIT_BEDINGUNG1","ID_KREDITLIMIT_BEDINGUNG1","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit_bedingung2("ID_KREDITLIMIT_BEDINGUNG2","ID_KREDITLIMIT_BEDINGUNG2","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_kreditlimit_bedingung3("ID_KREDITLIMIT_BEDINGUNG3","ID_KREDITLIMIT_BEDINGUNG3","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_rechtsform("ID_RECHTSFORM","ID_RECHTSFORM","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     id_zahlungsmedium("ID_ZAHLUNGSMEDIUM","ID_ZAHLUNGSMEDIUM","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     innergemein_lief_eu("INNERGEMEIN_LIEF_EU","INNERGEMEIN_LIEF_EU","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     keine_mahnungen("KEINE_MAHNUNGEN","KEINE_MAHNUNGEN","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditbetrag_intern("KREDITBETRAG_INTERN","KREDITBETRAG_INTERN","NUMBER",12,10,2,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditbetrag_intern_valid_to("KREDITBETRAG_INTERN_VALID_TO","KREDITBETRAG_INTERN_VALID_TO","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit("KREDITLIMIT","KREDITLIMIT","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit2("KREDITLIMIT2","KREDITLIMIT2","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit2_bis("KREDITLIMIT2_BIS","KREDITLIMIT2_BIS","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit2_von("KREDITLIMIT2_VON","KREDITLIMIT2_VON","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit3("KREDITLIMIT3","KREDITLIMIT3","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit3_bis("KREDITLIMIT3_BIS","KREDITLIMIT3_BIS","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit3_von("KREDITLIMIT3_VON","KREDITLIMIT3_VON","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit_bis("KREDITLIMIT_BIS","KREDITLIMIT_BIS","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditlimit_von("KREDITLIMIT_VON","KREDITLIMIT_VON","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditor_nummer("KREDITOR_NUMMER","KREDITOR_NUMMER","NVARCHAR2",30,30,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditor_nummer_alt("KREDITOR_NUMMER_ALT","KREDITOR_NUMMER_ALT","NVARCHAR2",30,30,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditstand("KREDITSTAND","KREDITSTAND","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     kreditvers_nr("KREDITVERS_NR","KREDITVERS_NR","NVARCHAR2",30,30,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     lademenge_fuer_rechnung("LADEMENGE_FUER_RECHNUNG","LADEMENGE_FUER_RECHNUNG","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     liefermenge_max("LIEFERMENGE_MAX","LIEFERMENGE_MAX","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     liefermenge_schnitt("LIEFERMENGE_SCHNITT","LIEFERMENGE_SCHNITT","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     oeffnungszeiten("OEFFNUNGSZEITEN","OEFFNUNGSZEITEN","NVARCHAR2",100,100,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     ohne_steuer_warenausgang("OHNE_STEUER_WARENAUSGANG","OHNE_STEUER_WARENAUSGANG","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     ohne_steuer_wareneingang("OHNE_STEUER_WARENEINGANG","OHNE_STEUER_WARENEINGANG","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     privat("PRIVAT","PRIVAT","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     privat_mit_ustid("PRIVAT_MIT_USTID","PRIVAT_MIT_USTID","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     scheckdruck_jn("SCHECKDRUCK_JN","SCHECKDRUCK_JN","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     stammkapital("STAMMKAPITAL","STAMMKAPITAL","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     steuernummer("STEUERNUMMER","STEUERNUMMER","NVARCHAR2",20,20,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     steuernummer_statt_ust("STEUERNUMMER_STATT_UST","STEUERNUMMER_STATT_UST","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     umsatzsteuerid("UMSATZSTEUERID","UMSATZSTEUERID","NVARCHAR2",20,20,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     umsatzsteuerlkz("UMSATZSTEUERLKZ","UMSATZSTEUERLKZ","NVARCHAR2",3,3,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     verlaengert_eigentum_vorbehalt("VERLAENGERT_EIGENTUM_VORBEHALT","VERLAENGERT_EIGENTUM_VORBEHALT","NVARCHAR2",20,20,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     versichanfr_dat("VERSICHANFR_DAT","VERSICHANFR_DAT","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     versichanfr_summe("VERSICHANFR_SUMME","VERSICHANFR_SUMME","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     versich_meldefrist_tage("VERSICH_MELDEFRIST_TAGE","VERSICH_MELDEFRIST_TAGE","NUMBER",7,6,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     verwertungsverfahren("VERWERTUNGSVERFAHREN","VERWERTUNGSVERFAHREN","NVARCHAR2",20,20,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     vorsteuerabzug_rc_inland("VORSTEUERABZUG_RC_INLAND","VORSTEUERABZUG_RC_INLAND","NVARCHAR2",1,1,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     zahlangestellte_am("ZAHLANGESTELLTE_AM","ZAHLANGESTELLTE_AM","DATE",7,0,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
     zahl_angestellte("ZAHL_ANGESTELLTE","ZAHL_ANGESTELLTE","NUMBER",11,10,0,true,_TAB.firmeninfo.fullTableName(),_TAB.firmeninfo.baseTableName(),false),
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

     private FIRMENINFO( String   p_fieldName,  
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

         for (IF_Field field: FIRMENINFO.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.firmeninfo.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.firmeninfo.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_FIRMENINFO 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.firmeninfo.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_FIRMENINFO AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.firmeninfo.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FIRMENINFO AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.firmeninfo; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FIRMENINFO AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.firmeninfo; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: FIRMENINFO.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
