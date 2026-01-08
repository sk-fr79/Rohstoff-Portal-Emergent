package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum HANDELSDEF implements IF_Field {


     aktiv("AKTIV","AKTIV","NVARCHAR2",1,1,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_handelsdef("ID_HANDELSDEF","ID_HANDELSDEF","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),true),
     id_land_quelle_geo("ID_LAND_QUELLE_GEO","ID_LAND_QUELLE_GEO","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_land_quelle_jur("ID_LAND_QUELLE_JUR","ID_LAND_QUELLE_JUR","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_land_ziel_geo("ID_LAND_ZIEL_GEO","ID_LAND_ZIEL_GEO","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_land_ziel_jur("ID_LAND_ZIEL_JUR","ID_LAND_ZIEL_JUR","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_tax_negativ_quelle("ID_TAX_NEGATIV_QUELLE","ID_TAX_NEGATIV_QUELLE","NUMBER",11,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_tax_negativ_ziel("ID_TAX_NEGATIV_ZIEL","ID_TAX_NEGATIV_ZIEL","NUMBER",11,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_tax_quelle("ID_TAX_QUELLE","ID_TAX_QUELLE","NUMBER",11,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     id_tax_ziel("ID_TAX_ZIEL","ID_TAX_ZIEL","NUMBER",11,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     intrastat_meld_in("INTRASTAT_MELD_IN","INTRASTAT_MELD_IN","NVARCHAR2",1,1,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     intrastat_meld_out("INTRASTAT_MELD_OUT","INTRASTAT_MELD_OUT","NVARCHAR2",1,1,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     meldung_fuer_user("MELDUNG_FUER_USER","MELDUNG_FUER_USER","NVARCHAR2",500,500,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     quelle_ist_mandant("QUELLE_IST_MANDANT","QUELLE_IST_MANDANT","NVARCHAR2",1,1,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_dienstleist_quelle("SORTE_DIENSTLEIST_QUELLE","SORTE_DIENSTLEIST_QUELLE","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_dienstleist_ziel("SORTE_DIENSTLEIST_ZIEL","SORTE_DIENSTLEIST_ZIEL","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_eow_quelle("SORTE_EOW_QUELLE","SORTE_EOW_QUELLE","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_eow_ziel("SORTE_EOW_ZIEL","SORTE_EOW_ZIEL","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_produkt_quelle("SORTE_PRODUKT_QUELLE","SORTE_PRODUKT_QUELLE","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_produkt_ziel("SORTE_PRODUKT_ZIEL","SORTE_PRODUKT_ZIEL","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_rc_quelle("SORTE_RC_QUELLE","SORTE_RC_QUELLE","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sorte_rc_ziel("SORTE_RC_ZIEL","SORTE_RC_ZIEL","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     tp_verantwortung("TP_VERANTWORTUNG","TP_VERANTWORTUNG","NVARCHAR2",20,20,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     transit_ek("TRANSIT_EK","TRANSIT_EK","NVARCHAR2",1,1,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     transit_vk("TRANSIT_VK","TRANSIT_VK","NVARCHAR2",1,1,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     typ_meldung("TYP_MELDUNG","TYP_MELDUNG","NVARCHAR2",20,20,0,true,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     ust_teilnehmer_quelle("UST_TEILNEHMER_QUELLE","UST_TEILNEHMER_QUELLE","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     ust_teilnehmer_ziel("UST_TEILNEHMER_ZIEL","UST_TEILNEHMER_ZIEL","NUMBER",3,2,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     versionszaehler("VERSIONSZAEHLER","VERSIONSZAEHLER","NUMBER",11,10,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
     ziel_ist_mandant("ZIEL_IST_MANDANT","ZIEL_IST_MANDANT","NVARCHAR2",1,1,0,false,_TAB.handelsdef.fullTableName(),_TAB.handelsdef.baseTableName(),false),
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

     private HANDELSDEF( String   p_fieldName,  
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

         for (IF_Field field: HANDELSDEF.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.handelsdef.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.handelsdef.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_HANDELSDEF 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.handelsdef.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_HANDELSDEF AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.handelsdef.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_HANDELSDEF AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.handelsdef; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_HANDELSDEF AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.handelsdef; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: HANDELSDEF.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
