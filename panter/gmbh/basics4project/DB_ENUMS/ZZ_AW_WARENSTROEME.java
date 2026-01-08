package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum ZZ_AW_WARENSTROEME implements IF_Field {


     benutzerkuerzel("BENUTZERKUERZEL","BENUTZERKUERZEL","NVARCHAR2",10,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     epreis_brutto_wa("EPREIS_BRUTTO_WA","EPREIS_BRUTTO_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     epreis_brutto_we("EPREIS_BRUTTO_WE","EPREIS_BRUTTO_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     epreis_netto_marge("EPREIS_NETTO_MARGE","EPREIS_NETTO_MARGE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     epreis_netto_wa("EPREIS_NETTO_WA","EPREIS_NETTO_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     epreis_netto_we("EPREIS_NETTO_WE","EPREIS_NETTO_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gesamtpreis_wa("GESAMTPREIS_WA","GESAMTPREIS_WA","NUMBER",14,12,2,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gesamtpreis_we("GESAMTPREIS_WE","GESAMTPREIS_WE","NUMBER",14,12,2,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_abz_auf_nettomge_wa("GPREIS_ABZ_AUF_NETTOMGE_WA","GPREIS_ABZ_AUF_NETTOMGE_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_abz_auf_nettomge_we("GPREIS_ABZ_AUF_NETTOMGE_WE","GPREIS_ABZ_AUF_NETTOMGE_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_abz_mge_wa("GPREIS_ABZ_MGE_WA","GPREIS_ABZ_MGE_WA","NUMBER",14,12,2,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_abz_mge_we("GPREIS_ABZ_MGE_WE","GPREIS_ABZ_MGE_WE","NUMBER",14,12,2,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_netto_marge("GPREIS_NETTO_MARGE","GPREIS_NETTO_MARGE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_netto_wa("GPREIS_NETTO_WA","GPREIS_NETTO_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     gpreis_netto_we("GPREIS_NETTO_WE","GPREIS_NETTO_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     id_adresse_lager("ID_ADRESSE_LAGER","ID_ADRESSE_LAGER","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     id_einheit("ID_EINHEIT","ID_EINHEIT","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     id_einheit_preis("ID_EINHEIT_PREIS","ID_EINHEIT_PREIS","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     id_zz_aw_warenstroeme("ID_ZZ_AW_WARENSTROEME","ID_ZZ_AW_WARENSTROEME","NUMBER",11,10,0,false,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),true),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     mengendivisor("MENGENDIVISOR","MENGENDIVISOR","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_abzug_lagerausgang("MENGE_ABZUG_LAGERAUSGANG","MENGE_ABZUG_LAGERAUSGANG","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_abzug_lagereingang("MENGE_ABZUG_LAGEREINGANG","MENGE_ABZUG_LAGEREINGANG","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_fuhre_abzug_wa("MENGE_FUHRE_ABZUG_WA","MENGE_FUHRE_ABZUG_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_fuhre_abzug_we("MENGE_FUHRE_ABZUG_WE","MENGE_FUHRE_ABZUG_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_fuhre_wa("MENGE_FUHRE_WA","MENGE_FUHRE_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_fuhre_we("MENGE_FUHRE_WE","MENGE_FUHRE_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_lagerausgang("MENGE_LAGERAUSGANG","MENGE_LAGERAUSGANG","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_lagereingang("MENGE_LAGEREINGANG","MENGE_LAGEREINGANG","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_abzug_lag_rel_wa("MENGE_RG_POS_ABZUG_LAG_REL_WA","MENGE_RG_POS_ABZUG_LAG_REL_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_abzug_lag_rel_we("MENGE_RG_POS_ABZUG_LAG_REL_WE","MENGE_RG_POS_ABZUG_LAG_REL_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_abzug_wa("MENGE_RG_POS_ABZUG_WA","MENGE_RG_POS_ABZUG_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_abzug_we("MENGE_RG_POS_ABZUG_WE","MENGE_RG_POS_ABZUG_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_netto_wa("MENGE_RG_POS_NETTO_WA","MENGE_RG_POS_NETTO_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_netto_we("MENGE_RG_POS_NETTO_WE","MENGE_RG_POS_NETTO_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_wa("MENGE_RG_POS_WA","MENGE_RG_POS_WA","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     menge_rg_pos_we("MENGE_RG_POS_WE","MENGE_RG_POS_WE","NUMBER",18,16,4,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     reportnummer("REPORTNUMMER","REPORTNUMMER","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.zz_aw_warenstroeme.fullTableName(),_TAB.zz_aw_warenstroeme.baseTableName(),false),
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

     private ZZ_AW_WARENSTROEME( String   p_fieldName,  
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

         for (IF_Field field: ZZ_AW_WARENSTROEME.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.zz_aw_warenstroeme.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.zz_aw_warenstroeme.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_ZZ_AW_WARENSTROEME 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.zz_aw_warenstroeme.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_ZZ_AW_WARENSTROEME AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.zz_aw_warenstroeme.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ZZ_AW_WARENSTROEME AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.zz_aw_warenstroeme; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ZZ_AW_WARENSTROEME AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.zz_aw_warenstroeme; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: ZZ_AW_WARENSTROEME.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
