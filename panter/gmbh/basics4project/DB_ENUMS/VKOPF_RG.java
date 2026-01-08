package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum VKOPF_RG implements IF_Field {


     abgeschlossen("ABGESCHLOSSEN","ABGESCHLOSSEN","NVARCHAR2",1,1,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     beleg_ist_intern("BELEG_IST_INTERN","BELEG_IST_INTERN","NVARCHAR2",1,1,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     bemerkungen_intern("BEMERKUNGEN_INTERN","BEMERKUNGEN_INTERN","NVARCHAR2",500,500,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     bezug("BEZUG","BEZUG","NVARCHAR2",100,100,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     buchungsnummer("BUCHUNGSNUMMER","BUCHUNGSNUMMER","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,false,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",200,200,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     druckdatum("DRUCKDATUM","DRUCKDATUM","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     druckzaehler("DRUCKZAEHLER","DRUCKZAEHLER","NUMBER",4,3,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     email_auf_formular("EMAIL_AUF_FORMULAR","EMAIL_AUF_FORMULAR","NVARCHAR2",100,100,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     erstellungsdatum("ERSTELLUNGSDATUM","ERSTELLUNGSDATUM","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     fax_ansprech_intern("FAX_ANSPRECH_INTERN","FAX_ANSPRECH_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     fax_bearbeiter_intern("FAX_BEARBEITER_INTERN","FAX_BEARBEITER_INTERN","NVARCHAR2",50,50,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     fax_sachbearb_intern("FAX_SACHBEARB_INTERN","FAX_SACHBEARB_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     fixmonat("FIXMONAT","FIXMONAT","NUMBER",6,5,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     fixtag("FIXTAG","FIXTAG","NUMBER",6,5,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     formulartext_anfang("FORMULARTEXT_ANFANG","FORMULARTEXT_ANFANG","NVARCHAR2",800,800,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     formulartext_ende("FORMULARTEXT_ENDE","FORMULARTEXT_ENDE","NVARCHAR2",800,800,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     gueltig_bis("GUELTIG_BIS","GUELTIG_BIS","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     gueltig_von("GUELTIG_VON","GUELTIG_VON","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     hausnummer("HAUSNUMMER","HAUSNUMMER","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_user("ID_USER","ID_USER","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_user_ansprech_intern("ID_USER_ANSPRECH_INTERN","ID_USER_ANSPRECH_INTERN","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_user_sachbearb_intern("ID_USER_SACHBEARB_INTERN","ID_USER_SACHBEARB_INTERN","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_vkopf_rg("ID_VKOPF_RG","ID_VKOPF_RG","NUMBER",11,10,0,false,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),true),
     id_vkopf_rg_storno_nachfolger("ID_VKOPF_RG_STORNO_NACHFOLGER","ID_VKOPF_RG_STORNO_NACHFOLGER","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_vkopf_rg_storno_vorgaenger("ID_VKOPF_RG_STORNO_VORGAENGER","ID_VKOPF_RG_STORNO_VORGAENGER","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_waehrung_fremd("ID_WAEHRUNG_FREMD","ID_WAEHRUNG_FREMD","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     id_zahlungsbedingungen("ID_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN","NUMBER",11,10,0,false,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     is_pob_active("IS_POB_ACTIVE","IS_POB_ACTIVE","NVARCHAR2",1,1,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     kdnr("KDNR","KDNR","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     keine_mahnungen("KEINE_MAHNUNGEN","KEINE_MAHNUNGEN","NVARCHAR2",1,1,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     laendercode("LAENDERCODE","LAENDERCODE","NVARCHAR2",6,6,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     letzter_druck("LETZTER_DRUCK","LETZTER_DRUCK","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     lieferadresse_aktiv("LIEFERADRESSE_AKTIV","LIEFERADRESSE_AKTIV","NVARCHAR2",1,1,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     lieferbedingungen("LIEFERBEDINGUNGEN","LIEFERBEDINGUNGEN","NVARCHAR2",200,200,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_hausnummer("L_HAUSNUMMER","L_HAUSNUMMER","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_laendercode("L_LAENDERCODE","L_LAENDERCODE","NVARCHAR2",6,6,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_name1("L_NAME1","L_NAME1","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_name2("L_NAME2","L_NAME2","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_name3("L_NAME3","L_NAME3","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_ort("L_ORT","L_ORT","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_ortzusatz("L_ORTZUSATZ","L_ORTZUSATZ","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_plz("L_PLZ","L_PLZ","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_strasse("L_STRASSE","L_STRASSE","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     l_vorname("L_VORNAME","L_VORNAME","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name1("NAME1","NAME1","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name2("NAME2","NAME2","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name3("NAME3","NAME3","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name_ansprechpartner("NAME_ANSPRECHPARTNER","NAME_ANSPRECHPARTNER","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name_ansprech_intern("NAME_ANSPRECH_INTERN","NAME_ANSPRECH_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name_bearbeiter_intern("NAME_BEARBEITER_INTERN","NAME_BEARBEITER_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     name_sachbearb_intern("NAME_SACHBEARB_INTERN","NAME_SACHBEARB_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     oeffnungszeiten("OEFFNUNGSZEITEN","OEFFNUNGSZEITEN","NVARCHAR2",100,100,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     ort("ORT","ORT","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     ortzusatz("ORTZUSATZ","ORTZUSATZ","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     plz("PLZ","PLZ","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     plz_pob("PLZ_POB","PLZ_POB","NVARCHAR2",10,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     pob("POB","POB","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     skonto_prozent("SKONTO_PROZENT","SKONTO_PROZENT","NUMBER",10,8,3,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     sprache("SPRACHE","SPRACHE","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     strasse("STRASSE","STRASSE","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     teilzahlung_prozent("TEILZAHLUNG_PROZENT","TEILZAHLUNG_PROZENT","NUMBER",14,12,4,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     telefax_auf_formular("TELEFAX_AUF_FORMULAR","TELEFAX_AUF_FORMULAR","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     telefon_auf_formular("TELEFON_AUF_FORMULAR","TELEFON_AUF_FORMULAR","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     tel_ansprech_intern("TEL_ANSPRECH_INTERN","TEL_ANSPRECH_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     tel_bearbeiter_intern("TEL_BEARBEITER_INTERN","TEL_BEARBEITER_INTERN","NVARCHAR2",50,50,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     tel_sachbearb_intern("TEL_SACHBEARB_INTERN","TEL_SACHBEARB_INTERN","NVARCHAR2",80,80,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     text_auslandsvertretung("TEXT_AUSLANDSVERTRETUNG","TEXT_AUSLANDSVERTRETUNG","NVARCHAR2",400,400,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     umsatzsteuerid("UMSATZSTEUERID","UMSATZSTEUERID","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     umsatzsteuerid_mandant("UMSATZSTEUERID_MANDANT","UMSATZSTEUERID_MANDANT","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     umsatzsteuerlkz("UMSATZSTEUERLKZ","UMSATZSTEUERLKZ","NVARCHAR2",3,3,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     umsatzsteuerlkz_mandant("UMSATZSTEUERLKZ_MANDANT","UMSATZSTEUERLKZ_MANDANT","NVARCHAR2",3,3,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     vorgangsgruppe("VORGANGSGRUPPE","VORGANGSGRUPPE","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     vorgang_nr("VORGANG_NR","VORGANG_NR","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     vorgang_typ("VORGANG_TYP","VORGANG_TYP","NVARCHAR2",40,40,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     vorname("VORNAME","VORNAME","NVARCHAR2",30,30,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     waehrung_basis("WAEHRUNG_BASIS","WAEHRUNG_BASIS","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     waehrung_fremd("WAEHRUNG_FREMD","WAEHRUNG_FREMD","NVARCHAR2",20,20,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     zaehler_entsperrung("ZAEHLER_ENTSPERRUNG","ZAEHLER_ENTSPERRUNG","NUMBER",11,10,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     zahltage("ZAHLTAGE","ZAHLTAGE","NUMBER",6,5,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
     zahlungsbedingungen("ZAHLUNGSBEDINGUNGEN","ZAHLUNGSBEDINGUNGEN","NVARCHAR2",200,200,0,true,_TAB.vkopf_rg.fullTableName(),_TAB.vkopf_rg.baseTableName(),false),
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

     private VKOPF_RG( String   p_fieldName,  
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

         for (IF_Field field: VKOPF_RG.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.vkopf_rg.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.vkopf_rg.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_VKOPF_RG 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.vkopf_rg.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_VKOPF_RG AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.vkopf_rg.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VKOPF_RG AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.vkopf_rg; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VKOPF_RG AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.vkopf_rg; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: VKOPF_RG.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
