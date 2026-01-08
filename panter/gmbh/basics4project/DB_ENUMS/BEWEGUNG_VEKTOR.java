package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum BEWEGUNG_VEKTOR implements IF_Field {


     abgeschlossen("ABGESCHLOSSEN","ABGESCHLOSSEN","NVARCHAR2",1,1,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     abgeschlossen_am("ABGESCHLOSSEN_AM","ABGESCHLOSSEN_AM","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     abgeschlossen_von("ABGESCHLOSSEN_VON","ABGESCHLOSSEN_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     anhaengerkennzeichen("ANHAENGERKENNZEICHEN","ANHAENGERKENNZEICHEN","NVARCHAR2",50,50,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     avv_ausstellung_datum("AVV_AUSSTELLUNG_DATUM","AVV_AUSSTELLUNG_DATUM","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     a_datum_bis("A_DATUM_BIS","A_DATUM_BIS","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     a_datum_von("A_DATUM_VON","A_DATUM_VON","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",600,600,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     bemerkung_fuer_kunde("BEMERKUNG_FUER_KUNDE","BEMERKUNG_FUER_KUNDE","NVARCHAR2",500,500,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     bemerkung_sachbearbeiter("BEMERKUNG_SACHBEARBEITER","BEMERKUNG_SACHBEARBEITER","NVARCHAR2",600,600,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",300,300,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     ek_vk_zuord_zwang("EK_VK_ZUORD_ZWANG","EK_VK_ZUORD_ZWANG","NVARCHAR2",1,1,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     eu_blatt_menge("EU_BLATT_MENGE","EU_BLATT_MENGE","NUMBER",14,12,3,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     eu_blatt_volumen("EU_BLATT_VOLUMEN","EU_BLATT_VOLUMEN","NUMBER",14,12,3,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     gelangensbestaetigung_erhalten("GELANGENSBESTAETIGUNG_ERHALTEN","GELANGENSBESTAETIGUNG_ERHALTEN","NVARCHAR2",1,1,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_adresse_fremdware("ID_ADRESSE_FREMDWARE","ID_ADRESSE_FREMDWARE","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_adresse_spedition("ID_ADRESSE_SPEDITION","ID_ADRESSE_SPEDITION","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_adresse_start_logistik("ID_ADRESSE_START_LOGISTIK","ID_ADRESSE_START_LOGISTIK","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_bewegung("ID_BEWEGUNG","ID_BEWEGUNG","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_bewegung_atom_trigstart("ID_BEWEGUNG_ATOM_TRIGSTART","ID_BEWEGUNG_ATOM_TRIGSTART","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_bewegung_atom_trigziel("ID_BEWEGUNG_ATOM_TRIGZIEL","ID_BEWEGUNG_ATOM_TRIGZIEL","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_bewegung_vektor("ID_BEWEGUNG_VEKTOR","ID_BEWEGUNG_VEKTOR","NUMBER",11,10,0,false,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),true),
     id_eak_code("ID_EAK_CODE","ID_EAK_CODE","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_uma_kontrakt("ID_UMA_KONTRAKT","ID_UMA_KONTRAKT","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_vektor_gruppe("ID_VEKTOR_GRUPPE","ID_VEKTOR_GRUPPE","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     id_verpackungsart("ID_VERPACKUNGSART","ID_VERPACKUNGSART","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     kosten_produkt_wa("KOSTEN_PRODUKT_WA","KOSTEN_PRODUKT_WA","NUMBER",12,10,2,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     kosten_produkt_we("KOSTEN_PRODUKT_WE","KOSTEN_PRODUKT_WE","NUMBER",12,10,2,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     kosten_transport_wa("KOSTEN_TRANSPORT_WA","KOSTEN_TRANSPORT_WA","NUMBER",12,10,2,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     kosten_transport_we("KOSTEN_TRANSPORT_WE","KOSTEN_TRANSPORT_WE","NUMBER",12,10,2,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     laendercode_transit1("LAENDERCODE_TRANSIT1","LAENDERCODE_TRANSIT1","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     laendercode_transit2("LAENDERCODE_TRANSIT2","LAENDERCODE_TRANSIT2","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     laendercode_transit3("LAENDERCODE_TRANSIT3","LAENDERCODE_TRANSIT3","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     l_datum_bis("L_DATUM_BIS","L_DATUM_BIS","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     l_datum_von("L_DATUM_VON","L_DATUM_VON","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     posnr("POSNR","POSNR","NUMBER",11,10,0,false,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     print_eu_amtsblatt("PRINT_EU_AMTSBLATT","PRINT_EU_AMTSBLATT","NVARCHAR2",1,1,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     statistik_timestamp("STATISTIK_TIMESTAMP","STATISTIK_TIMESTAMP","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     status("STATUS","STATUS","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     storniert("STORNIERT","STORNIERT","NVARCHAR2",1,1,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     storniert_am("STORNIERT_AM","STORNIERT_AM","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     storniert_grund("STORNIERT_GRUND","STORNIERT_GRUND","NVARCHAR2",300,300,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     storniert_von("STORNIERT_VON","STORNIERT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     transportkennzeichen("TRANSPORTKENNZEICHEN","TRANSPORTKENNZEICHEN","NVARCHAR2",50,50,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     transportmittel("TRANSPORTMITTEL","TRANSPORTMITTEL","NVARCHAR2",30,30,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     variante("VARIANTE","VARIANTE","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     warenklasse("WARENKLASSE","WARENKLASSE","NVARCHAR2",10,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     zahl_gutpos("ZAHL_GUTPOS","ZAHL_GUTPOS","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     zahl_rechpos("ZAHL_RECHPOS","ZAHL_RECHPOS","NUMBER",11,10,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
     zeitstempel("ZEITSTEMPEL","ZEITSTEMPEL","NVARCHAR2",16,16,0,true,_TAB.bewegung_vektor.fullTableName(),_TAB.bewegung_vektor.baseTableName(),false),
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

     private BEWEGUNG_VEKTOR( String   p_fieldName,  
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

         for (IF_Field field: BEWEGUNG_VEKTOR.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.bewegung_vektor.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.bewegung_vektor.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_BEWEGUNG_VEKTOR 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.bewegung_vektor.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_BEWEGUNG_VEKTOR AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.bewegung_vektor.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BEWEGUNG_VEKTOR AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.bewegung_vektor; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BEWEGUNG_VEKTOR AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.bewegung_vektor; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: BEWEGUNG_VEKTOR.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
