package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum FAHRPLANPOS implements IF_Field {


     anrufdatum("ANRUFDATUM","ANRUFDATUM","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     anrufer("ANRUFER","ANRUFER","NVARCHAR2",50,50,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     artbez1_kundenspez("ARTBEZ1_KUNDENSPEZ","ARTBEZ1_KUNDENSPEZ","NVARCHAR2",80,80,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     bemerkung("BEMERKUNG","BEMERKUNG","NVARCHAR2",300,300,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     bemerkung_fahrplan_start("BEMERKUNG_FAHRPLAN_START","BEMERKUNG_FAHRPLAN_START","NVARCHAR2",300,300,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     bemerkung_fahrplan_ziel("BEMERKUNG_FAHRPLAN_ZIEL","BEMERKUNG_FAHRPLAN_ZIEL","NVARCHAR2",300,300,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     dat_fahrplan("DAT_FAHRPLAN","DAT_FAHRPLAN","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     dat_vorgemerkt("DAT_VORGEMERKT","DAT_VORGEMERKT","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     dat_vorgemerkt_ende("DAT_VORGEMERKT_ENDE","DAT_VORGEMERKT_ENDE","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     deleted("DELETED","DELETED","NVARCHAR2",1,1,0,false,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     del_date("DEL_DATE","DEL_DATE","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     del_grund("DEL_GRUND","DEL_GRUND","NVARCHAR2",20,20,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     ean_code("EAN_CODE","EAN_CODE","NVARCHAR2",40,40,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     erfasser("ERFASSER","ERFASSER","NVARCHAR2",10,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     eu_blatt_menge("EU_BLATT_MENGE","EU_BLATT_MENGE","NUMBER",14,12,3,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     eu_blatt_volumen("EU_BLATT_VOLUMEN","EU_BLATT_VOLUMEN","NUMBER",14,12,3,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     fahrer("FAHRER","FAHRER","NVARCHAR2",80,80,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     fahrplangruppe("FAHRPLANGRUPPE","FAHRPLANGRUPPE","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     fahrt_anfang("FAHRT_ANFANG","FAHRT_ANFANG","NVARCHAR2",5,5,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     fahrt_ende("FAHRT_ENDE","FAHRT_ENDE","NVARCHAR2",5,5,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_adresse_start("ID_ADRESSE_START","ID_ADRESSE_START","NUMBER",11,10,0,false,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_adresse_ziel("ID_ADRESSE_ZIEL","ID_ADRESSE_ZIEL","NUMBER",11,10,0,false,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_containertyp("ID_CONTAINERTYP","ID_CONTAINERTYP","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_fahrplanpos("ID_FAHRPLANPOS","ID_FAHRPLANPOS","NUMBER",11,10,0,false,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_maschinen_anh("ID_MASCHINEN_ANH","ID_MASCHINEN_ANH","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_maschinen_lkw("ID_MASCHINEN_LKW","ID_MASCHINEN_LKW","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     ist_geplant("IST_GEPLANT","IST_GEPLANT","NVARCHAR2",1,1,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     kenner_alte_saetze("KENNER_ALTE_SAETZE","KENNER_ALTE_SAETZE","NVARCHAR2",1,1,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     laendercode_transit1("LAENDERCODE_TRANSIT1","LAENDERCODE_TRANSIT1","NVARCHAR2",5,5,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     laendercode_transit2("LAENDERCODE_TRANSIT2","LAENDERCODE_TRANSIT2","NVARCHAR2",5,5,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     laendercode_transit3("LAENDERCODE_TRANSIT3","LAENDERCODE_TRANSIT3","NVARCHAR2",5,5,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     nationaler_abfall_code("NATIONALER_ABFALL_CODE","NATIONALER_ABFALL_CODE","NVARCHAR2",60,60,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     sortierung("SORTIERUNG","SORTIERUNG","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
     taetigkeit("TAETIGKEIT","TAETIGKEIT","NVARCHAR2",100,100,0,true,_TAB.fahrplanpos.fullTableName(),_TAB.fahrplanpos.baseTableName(),false),
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

     private FAHRPLANPOS( String   p_fieldName,  
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

         for (IF_Field field: FAHRPLANPOS.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.fahrplanpos.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.fahrplanpos.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_FAHRPLANPOS 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.fahrplanpos.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_FAHRPLANPOS AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.fahrplanpos.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FAHRPLANPOS AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.fahrplanpos; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FAHRPLANPOS AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.fahrplanpos; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: FAHRPLANPOS.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
