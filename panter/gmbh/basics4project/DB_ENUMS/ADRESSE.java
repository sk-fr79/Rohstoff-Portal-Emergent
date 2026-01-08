package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum ADRESSE implements IF_Field {


     abn_nr("ABN_NR","ABN_NR","NVARCHAR2",60,60,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     adresstyp("ADRESSTYP","ADRESSTYP","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     ah7_quelle_sicher("AH7_QUELLE_SICHER","AH7_QUELLE_SICHER","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     ah7_ziel_sicher("AH7_ZIEL_SICHER","AH7_ZIEL_SICHER","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     aktiv("AKTIV","AKTIV","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     ausweis_ablauf_datum("AUSWEIS_ABLAUF_DATUM","AUSWEIS_ABLAUF_DATUM","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     ausweis_nummer("AUSWEIS_NUMMER","AUSWEIS_NUMMER","NVARCHAR2",30,30,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     barkunde("BARKUNDE","BARKUNDE","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     bemerkungen("BEMERKUNGEN","BEMERKUNGEN","NVARCHAR2",700,700,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     bemerkung_fahrplan("BEMERKUNG_FAHRPLAN","BEMERKUNG_FAHRPLAN","NVARCHAR2",300,300,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     erstkontakt("ERSTKONTAKT","ERSTKONTAKT","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_ansprech("EU_BEIBLATT_ANSPRECH","EU_BEIBLATT_ANSPRECH","NVARCHAR2",50,50,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_ek_vertrag("EU_BEIBLATT_EK_VERTRAG","EU_BEIBLATT_EK_VERTRAG","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_email("EU_BEIBLATT_EMAIL","EU_BEIBLATT_EMAIL","NVARCHAR2",50,50,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_fax("EU_BEIBLATT_FAX","EU_BEIBLATT_FAX","NVARCHAR2",50,50,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_infofeld("EU_BEIBLATT_INFOFELD","EU_BEIBLATT_INFOFELD","NVARCHAR2",200,200,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_tel("EU_BEIBLATT_TEL","EU_BEIBLATT_TEL","NVARCHAR2",50,50,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     eu_beiblatt_vk_vertrag("EU_BEIBLATT_VK_VERTRAG","EU_BEIBLATT_VK_VERTRAG","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     geburtsdatum("GEBURTSDATUM","GEBURTSDATUM","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     gutschriften_sperren("GUTSCHRIFTEN_SPERREN","GUTSCHRIFTEN_SPERREN","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     hausnummer("HAUSNUMMER","HAUSNUMMER","NVARCHAR2",10,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,false,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),true),
     id_adresse_eu_vertr_form("ID_ADRESSE_EU_VERTR_FORM","ID_ADRESSE_EU_VERTR_FORM","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse_merkmal1("ID_ADRESSE_MERKMAL1","ID_ADRESSE_MERKMAL1","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse_merkmal2("ID_ADRESSE_MERKMAL2","ID_ADRESSE_MERKMAL2","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse_merkmal3("ID_ADRESSE_MERKMAL3","ID_ADRESSE_MERKMAL3","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse_merkmal4("ID_ADRESSE_MERKMAL4","ID_ADRESSE_MERKMAL4","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse_merkmal5("ID_ADRESSE_MERKMAL5","ID_ADRESSE_MERKMAL5","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_adresse_potential("ID_ADRESSE_POTENTIAL","ID_ADRESSE_POTENTIAL","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_anrede("ID_ANREDE","ID_ANREDE","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_land("ID_LAND","ID_LAND","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_lieferbedingungen("ID_LIEFERBEDINGUNGEN","ID_LIEFERBEDINGUNGEN","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_lieferbedingungen_vk("ID_LIEFERBEDINGUNGEN_VK","ID_LIEFERBEDINGUNGEN_VK","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_sprache("ID_SPRACHE","ID_SPRACHE","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_user("ID_USER","ID_USER","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_user_ersatz("ID_USER_ERSATZ","ID_USER_ERSATZ","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_user_lager_haendler("ID_USER_LAGER_HAENDLER","ID_USER_LAGER_HAENDLER","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_user_lager_sachbearb("ID_USER_LAGER_SACHBEARB","ID_USER_LAGER_SACHBEARB","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_user_sachbearbeiter("ID_USER_SACHBEARBEITER","ID_USER_SACHBEARBEITER","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_waehrung("ID_WAEHRUNG","ID_WAEHRUNG","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_zahlungsbedingungen("ID_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     id_zahlungsbedingungen_vk("ID_ZAHLUNGSBEDINGUNGEN_VK","ID_ZAHLUNGSBEDINGUNGEN_VK","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     is_pob_active("IS_POB_ACTIVE","IS_POB_ACTIVE","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     kdnr("KDNR","KDNR","NVARCHAR2",20,20,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     lager_kontrollinfo("LAGER_KONTROLLINFO","LAGER_KONTROLLINFO","NVARCHAR2",100,100,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     lager_zustaendig_extern("LAGER_ZUSTAENDIG_EXTERN","LAGER_ZUSTAENDIG_EXTERN","NVARCHAR2",100,100,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     latitude("LATITUDE","LATITUDE","NUMBER",14,12,8,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     lieferinfo_tpa("LIEFERINFO_TPA","LIEFERINFO_TPA","NVARCHAR2",300,300,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     lief_nr("LIEF_NR","LIEF_NR","NVARCHAR2",60,60,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     longitude("LONGITUDE","LONGITUDE","NUMBER",14,12,8,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     name1("NAME1","NAME1","NVARCHAR2",40,40,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     name2("NAME2","NAME2","NVARCHAR2",40,40,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     name3("NAME3","NAME3","NVARCHAR2",40,40,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     ort("ORT","ORT","NVARCHAR2",30,30,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     ortzusatz("ORTZUSATZ","ORTZUSATZ","NVARCHAR2",30,30,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     plz("PLZ","PLZ","NVARCHAR2",10,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     plz_pob("PLZ_POB","PLZ_POB","NVARCHAR2",10,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     pob("POB","POB","NVARCHAR2",20,20,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     rechnungen_sperren("RECHNUNGEN_SPERREN","RECHNUNGEN_SPERREN","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     rechnung_per_fax("RECHNUNG_PER_FAX","RECHNUNG_PER_FAX","NVARCHAR2",40,40,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     sonderlager("SONDERLAGER","SONDERLAGER","NVARCHAR2",10,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     strasse("STRASSE","STRASSE","NVARCHAR2",45,45,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     transfer("TRANSFER","TRANSFER","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     vorname("VORNAME","VORNAME","NVARCHAR2",30,30,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     warenausgang_sperren("WARENAUSGANG_SPERREN","WARENAUSGANG_SPERREN","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
     wareneingang_sperren("WARENEINGANG_SPERREN","WARENEINGANG_SPERREN","NVARCHAR2",1,1,0,true,_TAB.adresse.fullTableName(),_TAB.adresse.baseTableName(),false),
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

     private ADRESSE( String   p_fieldName,  
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

         for (IF_Field field: ADRESSE.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.adresse.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.adresse.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_ADRESSE 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.adresse.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_ADRESSE AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.adresse.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ADRESSE AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.adresse; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ADRESSE AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.adresse; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: ADRESSE.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
