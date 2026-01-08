package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum BG_ATOM implements IF_Field {


     anr1("ANR1","ANR1","NVARCHAR2",10,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     anr2("ANR2","ANR2","NVARCHAR2",10,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     artbez1("ARTBEZ1","ARTBEZ1","NVARCHAR2",80,80,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     artbez2("ARTBEZ2","ARTBEZ2","NVARCHAR2",500,500,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     bemerkung_extern("BEMERKUNG_EXTERN","BEMERKUNG_EXTERN","NVARCHAR2",2000,2000,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     bemerkung_intern("BEMERKUNG_INTERN","BEMERKUNG_INTERN","NVARCHAR2",2000,2000,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     bestellnummer("BESTELLNUMMER","BESTELLNUMMER","NVARCHAR2",30,30,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     datum_ausfuehrung("DATUM_AUSFUEHRUNG","DATUM_AUSFUEHRUNG","DATE",7,0,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     eu_steuer_vermerk("EU_STEUER_VERMERK","EU_STEUER_VERMERK","NVARCHAR2",600,600,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     eu_vertrag_check("EU_VERTRAG_CHECK","EU_VERTRAG_CHECK","NVARCHAR2",1,1,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     e_preis_basiswaehrung("E_PREIS_BASISWAEHRUNG","E_PREIS_BASISWAEHRUNG","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     e_preis_fremdwaehrung("E_PREIS_FREMDWAEHRUNG","E_PREIS_FREMDWAEHRUNG","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     e_preis_res_netto_mge_basis("E_PREIS_RES_NETTO_MGE_BASIS","E_PREIS_RES_NETTO_MGE_BASIS","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     e_preis_res_netto_mge_fremd("E_PREIS_RES_NETTO_MGE_FREMD","E_PREIS_RES_NETTO_MGE_FREMD","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     g_preis_abzug_basis("G_PREIS_ABZUG_BASIS","G_PREIS_ABZUG_BASIS","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     g_preis_abzug_fremd("G_PREIS_ABZUG_FREMD","G_PREIS_ABZUG_FREMD","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     g_preis_basiswaehrung("G_PREIS_BASISWAEHRUNG","G_PREIS_BASISWAEHRUNG","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     g_preis_fremdwaehrung("G_PREIS_FREMDWAEHRUNG","G_PREIS_FREMDWAEHRUNG","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_artikel_bez("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_atom("ID_BG_ATOM","ID_BG_ATOM","NUMBER",11,10,0,false,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),true),
     id_bg_del_info("ID_BG_DEL_INFO","ID_BG_DEL_INFO","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_pruefport_gelangensbest("ID_BG_PRUEFPORT_GELANGENSBEST","ID_BG_PRUEFPORT_GELANGENSBEST","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_pruefprot_abschluss("ID_BG_PRUEFPROT_ABSCHLUSS","ID_BG_PRUEFPROT_ABSCHLUSS","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_pruefprot_menge("ID_BG_PRUEFPROT_MENGE","ID_BG_PRUEFPROT_MENGE","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_pruefprot_preis("ID_BG_PRUEFPROT_PREIS","ID_BG_PRUEFPROT_PREIS","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_rech_block("ID_BG_RECH_BLOCK","ID_BG_RECH_BLOCK","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_station_quelle("ID_BG_STATION_QUELLE","ID_BG_STATION_QUELLE","NUMBER",11,10,0,false,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_station_ziel("ID_BG_STATION_ZIEL","ID_BG_STATION_ZIEL","NUMBER",11,10,0,false,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_storno_info("ID_BG_STORNO_INFO","ID_BG_STORNO_INFO","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_bg_vektor("ID_BG_VEKTOR","ID_BG_VEKTOR","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_eak_code("ID_EAK_CODE","ID_EAK_CODE","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_lager_konto("ID_LAGER_KONTO","ID_LAGER_KONTO","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_lieferbedingungen("ID_LIEFERBEDINGUNGEN","ID_LIEFERBEDINGUNGEN","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_tax("ID_TAX","ID_TAX","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_vpos_kon("ID_VPOS_KON","ID_VPOS_KON","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_vpos_std("ID_VPOS_STD","ID_VPOS_STD","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_waehrung("ID_WAEHRUNG","ID_WAEHRUNG","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_zahlungsbedingungen("ID_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     id_zolltarifnummer("ID_ZOLLTARIFNUMMER","ID_ZOLLTARIFNUMMER","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     intrastat_meld("INTRASTAT_MELD","INTRASTAT_MELD","NVARCHAR2",1,1,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     kontraktzwang("KONTRAKTZWANG","KONTRAKTZWANG","NVARCHAR2",1,1,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     kosten_produkt("KOSTEN_PRODUKT","KOSTEN_PRODUKT","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     lieferbedingungen("LIEFERBEDINGUNGEN","LIEFERBEDINGUNGEN","NVARCHAR2",100,100,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     manuell_preis("MANUELL_PREIS","MANUELL_PREIS","NVARCHAR2",1,1,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     menge("MENGE","MENGE","NUMBER",14,12,3,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     menge_abrech("MENGE_ABRECH","MENGE_ABRECH","NUMBER",14,12,3,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     menge_abzug("MENGE_ABZUG","MENGE_ABZUG","NUMBER",14,12,3,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     menge_netto("MENGE_NETTO","MENGE_NETTO","NUMBER",14,12,3,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     menge_verteilung("MENGE_VERTEILUNG","MENGE_VERTEILUNG","NUMBER",14,12,3,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     nationaler_abfall_code("NATIONALER_ABFALL_CODE","NATIONALER_ABFALL_CODE","NVARCHAR2",30,30,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     notifikation_nr("NOTIFIKATION_NR","NOTIFIKATION_NR","NVARCHAR2",30,30,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     postennummer("POSTENNUMMER","POSTENNUMMER","NVARCHAR2",100,100,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     pos_in_mask("POS_IN_MASK","POS_IN_MASK","NVARCHAR2",20,20,0,false,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     steuersatz("STEUERSATZ","STEUERSATZ","NUMBER",12,10,2,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     timestamp_in_mask("TIMESTAMP_IN_MASK","TIMESTAMP_IN_MASK","NVARCHAR2",16,16,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     transit_meld("TRANSIT_MELD","TRANSIT_MELD","NVARCHAR2",1,1,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     waehrungskurs("WAEHRUNGSKURS","WAEHRUNGSKURS","NUMBER",11,9,4,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
     zahlungsbedingungen("ZAHLUNGSBEDINGUNGEN","ZAHLUNGSBEDINGUNGEN","NVARCHAR2",100,100,0,true,_TAB.bg_atom.fullTableName(),_TAB.bg_atom.baseTableName(),false),
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

     private BG_ATOM( String   p_fieldName,  
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

         for (IF_Field field: BG_ATOM.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.bg_atom.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.bg_atom.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_BG_ATOM 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.bg_atom.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_BG_ATOM AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.bg_atom.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BG_ATOM AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.bg_atom; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BG_ATOM AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.bg_atom; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: BG_ATOM.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
