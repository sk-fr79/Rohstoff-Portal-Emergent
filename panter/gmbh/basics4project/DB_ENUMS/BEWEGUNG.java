package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum BEWEGUNG implements IF_Field {


     ablade_datum_bis("ABLADE_DATUM_BIS","ABLADE_DATUM_BIS","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     ablade_datum_von("ABLADE_DATUM_VON","ABLADE_DATUM_VON","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     anrufdatum_fp("ANRUFDATUM_FP","ANRUFDATUM_FP","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     anrufer_fp("ANRUFER_FP","ANRUFER_FP","NVARCHAR2",50,50,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     anzahl_container_fp("ANZAHL_CONTAINER_FP","ANZAHL_CONTAINER_FP","NUMBER",4,3,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",600,600,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     bemerkung_sachbearbeiter("BEMERKUNG_SACHBEARBEITER","BEMERKUNG_SACHBEARBEITER","NVARCHAR2",600,600,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     bemerkung_start_fp("BEMERKUNG_START_FP","BEMERKUNG_START_FP","NVARCHAR2",300,300,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     bemerkung_ziel_fp("BEMERKUNG_ZIEL_FP","BEMERKUNG_ZIEL_FP","NVARCHAR2",300,300,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     bewegung_typ("BEWEGUNG_TYP","BEWEGUNG_TYP","NVARCHAR2",100,100,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     dat_fahrplan_fp("DAT_FAHRPLAN_FP","DAT_FAHRPLAN_FP","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     dat_vorgemerkt_ende_fp("DAT_VORGEMERKT_ENDE_FP","DAT_VORGEMERKT_ENDE_FP","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     dat_vorgemerkt_fp("DAT_VORGEMERKT_FP","DAT_VORGEMERKT_FP","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",300,300,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     del_kuerzel("DEL_KUERZEL","DEL_KUERZEL","NVARCHAR2",10,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     ean_code_fp("EAN_CODE_FP","EAN_CODE_FP","NVARCHAR2",40,40,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     erfasser_fp("ERFASSER_FP","ERFASSER_FP","NVARCHAR2",10,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     fahrer_fp("FAHRER_FP","FAHRER_FP","NVARCHAR2",80,80,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     fahrplangruppe_fp("FAHRPLANGRUPPE_FP","FAHRPLANGRUPPE_FP","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     fahrplan_satz("FAHRPLAN_SATZ","FAHRPLAN_SATZ","NVARCHAR2",1,1,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     fahrt_anfang_fp("FAHRT_ANFANG_FP","FAHRT_ANFANG_FP","NVARCHAR2",5,5,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     fahrt_ende_fp("FAHRT_ENDE_FP","FAHRT_ENDE_FP","NVARCHAR2",5,5,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     id_bewegung("ID_BEWEGUNG","ID_BEWEGUNG","NUMBER",11,10,0,false,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),true),
     id_containertyp_fp("ID_CONTAINERTYP_FP","ID_CONTAINERTYP_FP","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     id_maschinen_anh_fp("ID_MASCHINEN_ANH_FP","ID_MASCHINEN_ANH_FP","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     id_maschinen_lkw_fp("ID_MASCHINEN_LKW_FP","ID_MASCHINEN_LKW_FP","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     id_vpos_tpa_ng("ID_VPOS_TPA_NG","ID_VPOS_TPA_NG","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     ist_geplant_fp("IST_GEPLANT_FP","IST_GEPLANT_FP","NVARCHAR2",1,1,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     ist_lagerbuchung_alt("IST_LAGERBUCHUNG_ALT","IST_LAGERBUCHUNG_ALT","NVARCHAR2",1,1,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     lade_datum_bis("LADE_DATUM_BIS","LADE_DATUM_BIS","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     lade_datum_von("LADE_DATUM_VON","LADE_DATUM_VON","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     sortierung_fp("SORTIERUNG_FP","SORTIERUNG_FP","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     taetigkeit_fp("TAETIGKEIT_FP","TAETIGKEIT_FP","NVARCHAR2",100,100,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
     zeitstempel("ZEITSTEMPEL","ZEITSTEMPEL","NVARCHAR2",16,16,0,true,_TAB.bewegung.fullTableName(),_TAB.bewegung.baseTableName(),false),
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

     private BEWEGUNG( String   p_fieldName,  
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

         for (IF_Field field: BEWEGUNG.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.bewegung.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.bewegung.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_BEWEGUNG 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.bewegung.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_BEWEGUNG AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.bewegung.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BEWEGUNG AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.bewegung; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_BEWEGUNG AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.bewegung; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: BEWEGUNG.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
