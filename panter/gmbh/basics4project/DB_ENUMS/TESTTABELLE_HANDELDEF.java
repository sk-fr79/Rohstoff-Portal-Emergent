package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum TESTTABELLE_HANDELDEF implements IF_Field {


     epreis_quelle("EPREIS_QUELLE","EPREIS_QUELLE","NUMBER",12,10,2,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     epreis_ziel("EPREIS_ZIEL","EPREIS_ZIEL","NUMBER",12,10,2,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     id_handelsdef("ID_HANDELSDEF","ID_HANDELSDEF","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     id_tax_start_hd("ID_TAX_START_HD","ID_TAX_START_HD","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     id_tax_ziel_hd("ID_TAX_ZIEL_HD","ID_TAX_ZIEL_HD","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     id_testtabelle_handeldef("ID_TESTTABELLE_HANDELDEF","ID_TESTTABELLE_HANDELDEF","NUMBER",11,10,0,false,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),true),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     intrastat_hd("INTRASTAT_HD","INTRASTAT_HD","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     laendercode_start("LAENDERCODE_START","LAENDERCODE_START","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     laendercode_ziel("LAENDERCODE_ZIEL","LAENDERCODE_ZIEL","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_id_land_quelle_geo("OM_ID_LAND_QUELLE_GEO","OM_ID_LAND_QUELLE_GEO","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_id_land_quelle_jur("OM_ID_LAND_QUELLE_JUR","OM_ID_LAND_QUELLE_JUR","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_id_land_ziel_geo("OM_ID_LAND_ZIEL_GEO","OM_ID_LAND_ZIEL_GEO","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_id_land_ziel_jur("OM_ID_LAND_ZIEL_JUR","OM_ID_LAND_ZIEL_JUR","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_dienstlst("OM_START_DIENSTLST","OM_START_DIENSTLST","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_eow("OM_START_EOW","OM_START_EOW","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_ist_mandant("OM_START_IST_MANDANT","OM_START_IST_MANDANT","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_land_geo("OM_START_LAND_GEO","OM_START_LAND_GEO","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_land_jur("OM_START_LAND_JUR","OM_START_LAND_JUR","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_produkt("OM_START_PRODUKT","OM_START_PRODUKT","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_rc("OM_START_RC","OM_START_RC","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_start_unternehmen("OM_START_UNTERNEHMEN","OM_START_UNTERNEHMEN","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_tpa_verantwort("OM_TPA_VERANTWORT","OM_TPA_VERANTWORT","NVARCHAR2",100,100,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_dienstlst("OM_ZIEL_DIENSTLST","OM_ZIEL_DIENSTLST","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_eow("OM_ZIEL_EOW","OM_ZIEL_EOW","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_ist_mandant("OM_ZIEL_IST_MANDANT","OM_ZIEL_IST_MANDANT","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_land_geo("OM_ZIEL_LAND_GEO","OM_ZIEL_LAND_GEO","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_land_jur("OM_ZIEL_LAND_JUR","OM_ZIEL_LAND_JUR","NVARCHAR2",10,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_produkt("OM_ZIEL_PRODUKT","OM_ZIEL_PRODUKT","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_rc("OM_ZIEL_RC","OM_ZIEL_RC","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     om_ziel_unternehmen("OM_ZIEL_UNTERNEHMEN","OM_ZIEL_UNTERNEHMEN","NVARCHAR2",1,1,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuersatz_start_alt("STEUERSATZ_START_ALT","STEUERSATZ_START_ALT","NUMBER",12,10,2,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuersatz_start_hd("STEUERSATZ_START_HD","STEUERSATZ_START_HD","NUMBER",12,10,2,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuersatz_ziel_alt("STEUERSATZ_ZIEL_ALT","STEUERSATZ_ZIEL_ALT","NUMBER",12,10,2,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuersatz_ziel_hd("STEUERSATZ_ZIEL_HD","STEUERSATZ_ZIEL_HD","NUMBER",12,10,2,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuervermerk_start_alt("STEUERVERMERK_START_ALT","STEUERVERMERK_START_ALT","NVARCHAR2",600,600,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuervermerk_start_hd("STEUERVERMERK_START_HD","STEUERVERMERK_START_HD","NVARCHAR2",600,600,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuervermerk_ziel_alt("STEUERVERMERK_ZIEL_ALT","STEUERVERMERK_ZIEL_ALT","NVARCHAR2",600,600,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     steuervermerk_ziel_hd("STEUERVERMERK_ZIEL_HD","STEUERVERMERK_ZIEL_HD","NVARCHAR2",600,600,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     suchergebnis("SUCHERGEBNIS","SUCHERGEBNIS","NVARCHAR2",100,100,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.testtabelle_handeldef.fullTableName(),_TAB.testtabelle_handeldef.baseTableName(),false),
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

     private TESTTABELLE_HANDELDEF( String   p_fieldName,  
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

         for (IF_Field field: TESTTABELLE_HANDELDEF.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.testtabelle_handeldef.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.testtabelle_handeldef.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_TESTTABELLE_HANDELDEF 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.testtabelle_handeldef.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_TESTTABELLE_HANDELDEF AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.testtabelle_handeldef.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_TESTTABELLE_HANDELDEF AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.testtabelle_handeldef; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_TESTTABELLE_HANDELDEF AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.testtabelle_handeldef; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: TESTTABELLE_HANDELDEF.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
