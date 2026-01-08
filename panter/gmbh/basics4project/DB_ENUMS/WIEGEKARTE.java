package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum WIEGEKARTE implements IF_Field {


     adresse_lieferant("ADRESSE_LIEFERANT","ADRESSE_LIEFERANT","NVARCHAR2",500,500,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     adresse_spedition("ADRESSE_SPEDITION","ADRESSE_SPEDITION","NVARCHAR2",500,500,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     anz_allg("ANZ_ALLG","ANZ_ALLG","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     anz_behaelter("ANZ_BEHAELTER","ANZ_BEHAELTER","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     anz_bigbags("ANZ_BIGBAGS","ANZ_BIGBAGS","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     anz_gitterboxen("ANZ_GITTERBOXEN","ANZ_GITTERBOXEN","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     anz_paletten("ANZ_PALETTEN","ANZ_PALETTEN","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     befund("BEFUND","BEFUND","NVARCHAR2",200,200,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     bemerkung1("BEMERKUNG1","BEMERKUNG1","NVARCHAR2",250,250,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     bemerkung2("BEMERKUNG2","BEMERKUNG2","NVARCHAR2",250,250,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     bemerkung_intern("BEMERKUNG_INTERN","BEMERKUNG_INTERN","NVARCHAR2",250,250,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     bez_allg("BEZ_ALLG","BEZ_ALLG","NVARCHAR2",250,250,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     container_absetz_grund("CONTAINER_ABSETZ_GRUND","CONTAINER_ABSETZ_GRUND","NVARCHAR2",200,200,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     container_nr("CONTAINER_NR","CONTAINER_NR","NVARCHAR2",50,50,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     container_tara("CONTAINER_TARA","CONTAINER_TARA","NUMBER",14,12,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     druckzaehler("DRUCKZAEHLER","DRUCKZAEHLER","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     druckzaehler_eingangsschein("DRUCKZAEHLER_EINGANGSSCHEIN","DRUCKZAEHLER_EINGANGSSCHEIN","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     es_nr("ES_NR","ES_NR","NVARCHAR2",20,20,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     gedruckt_am("GEDRUCKT_AM","GEDRUCKT_AM","DATE",7,0,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     gewicht_abzug("GEWICHT_ABZUG","GEWICHT_ABZUG","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     gewicht_abzug_fuhre("GEWICHT_ABZUG_FUHRE","GEWICHT_ABZUG_FUHRE","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     gewicht_nach_abzug("GEWICHT_NACH_ABZUG","GEWICHT_NACH_ABZUG","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     gewicht_nach_abzug_fuhre("GEWICHT_NACH_ABZUG_FUHRE","GEWICHT_NACH_ABZUG_FUHRE","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     grund_abzug("GRUND_ABZUG","GRUND_ABZUG","NVARCHAR2",250,250,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     gueterkategorie("GUETERKATEGORIE","GUETERKATEGORIE","NVARCHAR2",100,100,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_adresse_abn_strecke("ID_ADRESSE_ABN_STRECKE","ID_ADRESSE_ABN_STRECKE","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_adresse_lager("ID_ADRESSE_LAGER","ID_ADRESSE_LAGER","NUMBER",11,10,0,false,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_adresse_lieferant("ID_ADRESSE_LIEFERANT","ID_ADRESSE_LIEFERANT","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_adresse_spedition("ID_ADRESSE_SPEDITION","ID_ADRESSE_SPEDITION","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_artikel_bez("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_artikel_sorte("ID_ARTIKEL_SORTE","ID_ARTIKEL_SORTE","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_container_eigen("ID_CONTAINER_EIGEN","ID_CONTAINER_EIGEN","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_lagerplatz_absetz("ID_LAGERPLATZ_ABSETZ","ID_LAGERPLATZ_ABSETZ","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_lagerplatz_schuett("ID_LAGERPLATZ_SCHUETT","ID_LAGERPLATZ_SCHUETT","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_waage_standort("ID_WAAGE_STANDORT","ID_WAAGE_STANDORT","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_wiegekarte("ID_WIEGEKARTE","ID_WIEGEKARTE","NUMBER",11,10,0,false,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),true),
     id_wiegekarte_parent("ID_WIEGEKARTE_PARENT","ID_WIEGEKARTE_PARENT","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     id_wiegekarte_storno("ID_WIEGEKARTE_STORNO","ID_WIEGEKARTE_STORNO","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     ist_gesamtverwiegung("IST_GESAMTVERWIEGUNG","IST_GESAMTVERWIEGUNG","VARCHAR2",1,1,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     ist_lieferant("IST_LIEFERANT","IST_LIEFERANT","NVARCHAR2",1,1,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     kennzeichen("KENNZEICHEN","KENNZEICHEN","NVARCHAR2",20,20,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     lfd_nr("LFD_NR","LFD_NR","NVARCHAR2",10,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     nettogewicht("NETTOGEWICHT","NETTOGEWICHT","NUMBER",21,19,3,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     siegel_nr("SIEGEL_NR","SIEGEL_NR","NVARCHAR2",50,50,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     sorte_hand("SORTE_HAND","SORTE_HAND","VARCHAR2",1,1,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     storniert_am("STORNIERT_AM","STORNIERT_AM","DATE",7,0,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     storniert_von("STORNIERT_VON","STORNIERT_VON","NVARCHAR2",20,20,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     storno("STORNO","STORNO","NVARCHAR2",1,1,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     storno_grund("STORNO_GRUND","STORNO_GRUND","NVARCHAR2",200,200,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     strahlung_geprueft("STRAHLUNG_GEPRUEFT","STRAHLUNG_GEPRUEFT","NVARCHAR2",1,1,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     tara_korr_container("TARA_KORR_CONTAINER","TARA_KORR_CONTAINER","NUMBER",12,10,2,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     trailer("TRAILER","TRAILER","NVARCHAR2",30,30,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     typ_wiegekarte("TYP_WIEGEKARTE","TYP_WIEGEKARTE","NVARCHAR2",2,2,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
     uvv_datum_container("UVV_DATUM_CONTAINER","UVV_DATUM_CONTAINER","DATE",7,0,0,true,_TAB.wiegekarte.fullTableName(),_TAB.wiegekarte.baseTableName(),false),
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

     private WIEGEKARTE( String   p_fieldName,  
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

         for (IF_Field field: WIEGEKARTE.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.wiegekarte.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.wiegekarte.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_WIEGEKARTE 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.wiegekarte.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_WIEGEKARTE AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.wiegekarte.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_WIEGEKARTE AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.wiegekarte; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_WIEGEKARTE AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.wiegekarte; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: WIEGEKARTE.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
