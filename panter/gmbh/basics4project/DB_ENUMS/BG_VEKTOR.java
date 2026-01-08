package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum BG_VEKTOR implements IF_Field {


     ah7_ausstellung_datum("AH7_AUSSTELLUNG_DATUM","AH7_AUSSTELLUNG_DATUM","DATE",7,0,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     ah7_menge("AH7_MENGE","AH7_MENGE","NUMBER",14,12,3,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     ah7_volumen("AH7_VOLUMEN","AH7_VOLUMEN","NUMBER",14,12,3,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     anhaengerkennzeichen("ANHAENGERKENNZEICHEN","ANHAENGERKENNZEICHEN","NVARCHAR2",50,50,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",2000,2000,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     bemerkung_fuer_kunde("BEMERKUNG_FUER_KUNDE","BEMERKUNG_FUER_KUNDE","NVARCHAR2",2000,2000,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     bemerkung_sachbearbeiter("BEMERKUNG_SACHBEARBEITER","BEMERKUNG_SACHBEARBEITER","NVARCHAR2",2000,2000,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     buchungsnummer("BUCHUNGSNUMMER","BUCHUNGSNUMMER","NVARCHAR2",20,20,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     datum_planung_bis("DATUM_PLANUNG_BIS","DATUM_PLANUNG_BIS","DATE",7,0,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     datum_planung_von("DATUM_PLANUNG_VON","DATUM_PLANUNG_VON","DATE",7,0,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     ek_vk_sorte_lock("EK_VK_SORTE_LOCK","EK_VK_SORTE_LOCK","NVARCHAR2",1,1,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     ek_vk_zuord_zwang("EK_VK_ZUORD_ZWANG","EK_VK_ZUORD_ZWANG","NVARCHAR2",1,1,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     en_transport_typ("EN_TRANSPORT_TYP","EN_TRANSPORT_TYP","NVARCHAR2",20,20,0,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     en_vektor_quelle("EN_VEKTOR_QUELLE","EN_VEKTOR_QUELLE","NVARCHAR2",20,20,0,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     en_vektor_status("EN_VEKTOR_STATUS","EN_VEKTOR_STATUS","NVARCHAR2",20,20,0,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_adresse_fremdware("ID_ADRESSE_FREMDWARE","ID_ADRESSE_FREMDWARE","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_adresse_logi_start("ID_ADRESSE_LOGI_START","ID_ADRESSE_LOGI_START","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_adresse_logi_ziel("ID_ADRESSE_LOGI_ZIEL","ID_ADRESSE_LOGI_ZIEL","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_adresse_spedition("ID_ADRESSE_SPEDITION","ID_ADRESSE_SPEDITION","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_atom_quelle("ID_BG_ATOM_QUELLE","ID_BG_ATOM_QUELLE","NUMBER",11,10,0,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_atom_ziel("ID_BG_ATOM_ZIEL","ID_BG_ATOM_ZIEL","NUMBER",11,10,0,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_del_info("ID_BG_DEL_INFO","ID_BG_DEL_INFO","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_pruefprot_abschluss("ID_BG_PRUEFPROT_ABSCHLUSS","ID_BG_PRUEFPROT_ABSCHLUSS","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_storno_info("ID_BG_STORNO_INFO","ID_BG_STORNO_INFO","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_vektor("ID_BG_VEKTOR","ID_BG_VEKTOR","NUMBER",11,10,0,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),true),
     id_bg_vektor_base("ID_BG_VEKTOR_BASE","ID_BG_VEKTOR_BASE","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_bg_vektor_sub("ID_BG_VEKTOR_SUB","ID_BG_VEKTOR_SUB","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_handelsdef("ID_HANDELSDEF","ID_HANDELSDEF","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_land_transit1("ID_LAND_TRANSIT1","ID_LAND_TRANSIT1","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_land_transit2("ID_LAND_TRANSIT2","ID_LAND_TRANSIT2","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_land_transit3("ID_LAND_TRANSIT3","ID_LAND_TRANSIT3","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_transportmittel("ID_TRANSPORTMITTEL","ID_TRANSPORTMITTEL","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_uma_kontrakt("ID_UMA_KONTRAKT","ID_UMA_KONTRAKT","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_verarbeitung("ID_VERARBEITUNG","ID_VERARBEITUNG","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     id_verpackungsart("ID_VERPACKUNGSART","ID_VERPACKUNGSART","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     kosten_transport("KOSTEN_TRANSPORT","KOSTEN_TRANSPORT","NUMBER",12,10,2,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     lieferinfo_tpa("LIEFERINFO_TPA","LIEFERINFO_TPA","NVARCHAR2",2000,2000,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     ordnungsnummer_cmr("ORDNUNGSNUMMER_CMR","ORDNUNGSNUMMER_CMR","NVARCHAR2",30,30,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     planmenge("PLANMENGE","PLANMENGE","NUMBER",14,12,3,false,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     posnr("POSNR","POSNR","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     print_anhang7("PRINT_ANHANG7","PRINT_ANHANG7","NVARCHAR2",1,1,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     timestamp_in_mask("TIMESTAMP_IN_MASK","TIMESTAMP_IN_MASK","NVARCHAR2",16,16,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     transportkennzeichen("TRANSPORTKENNZEICHEN","TRANSPORTKENNZEICHEN","NVARCHAR2",50,50,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     transportmittel("TRANSPORTMITTEL","TRANSPORTMITTEL","NVARCHAR2",100,100,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
     transportverantwortung("TRANSPORTVERANTWORTUNG","TRANSPORTVERANTWORTUNG","NVARCHAR2",10,10,0,true,_TAB.bg_vektor.fullTableName(),_TAB.bg_vektor.baseTableName(),false),
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

     private BG_VEKTOR( String   p_fieldName,  
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

         for (IF_Field field: BG_VEKTOR.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.bg_vektor.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.bg_vektor.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_BG_VEKTOR 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.bg_vektor.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_BG_VEKTOR AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.bg_vektor.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BG_VEKTOR AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.bg_vektor; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BG_VEKTOR AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.bg_vektor; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: BG_VEKTOR.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
