package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum LAGER_PALETTE implements IF_Field {


     ausbuchung_hand("AUSBUCHUNG_HAND","AUSBUCHUNG_HAND","NVARCHAR2",1,1,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     bruttomenge("BRUTTOMENGE","BRUTTOMENGE","NUMBER",14,12,3,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     buchungsnr_hand("BUCHUNGSNR_HAND","BUCHUNGSNR_HAND","NVARCHAR2",40,40,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     chargennummer("CHARGENNUMMER","CHARGENNUMMER","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     datum_verarbeitet("DATUM_VERARBEITET","DATUM_VERARBEITET","DATE",7,0,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     einbuchung_hand("EINBUCHUNG_HAND","EINBUCHUNG_HAND","NVARCHAR2",1,1,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     endkontrolle_von("ENDKONTROLLE_VON","ENDKONTROLLE_VON","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     gepresst_von("GEPRESST_VON","GEPRESST_VON","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     hand_ausbuchung_bemerkung("HAND_AUSBUCHUNG_BEMERKUNG","HAND_AUSBUCHUNG_BEMERKUNG","NVARCHAR2",400,400,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_adresse_ausbuch_hand("ID_ADRESSE_AUSBUCH_HAND","ID_ADRESSE_AUSBUCH_HAND","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_adresse_einbuch_hand("ID_ADRESSE_EINBUCH_HAND","ID_ADRESSE_EINBUCH_HAND","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_artikel_bez("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_lager_box("ID_LAGER_BOX","ID_LAGER_BOX","NUMBER",11,10,0,false,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_lager_palette("ID_LAGER_PALETTE","ID_LAGER_PALETTE","NUMBER",11,10,0,false,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_vpos_tpa_fuhre_aus("ID_VPOS_TPA_FUHRE_AUS","ID_VPOS_TPA_FUHRE_AUS","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     id_vpos_tpa_fuhre_ein("ID_VPOS_TPA_FUHRE_EIN","ID_VPOS_TPA_FUHRE_EIN","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     ist_palette("IST_PALETTE","IST_PALETTE","NVARCHAR2",1,1,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     material_zusatzinfo("MATERIAL_ZUSATZINFO","MATERIAL_ZUSATZINFO","NVARCHAR2",40,40,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     nettomenge("NETTOMENGE","NETTOMENGE","NUMBER",14,12,3,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     palettiert_von("PALETTIERT_VON","PALETTIERT_VON","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
     taramenge("TARAMENGE","TARAMENGE","NUMBER",14,12,3,true,_TAB.lager_palette.fullTableName(),_TAB.lager_palette.baseTableName(),false),
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

     private LAGER_PALETTE( String   p_fieldName,  
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

         for (IF_Field field: LAGER_PALETTE.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.lager_palette.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.lager_palette.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_LAGER_PALETTE 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.lager_palette.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_LAGER_PALETTE AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.lager_palette.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_LAGER_PALETTE AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.lager_palette; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_LAGER_PALETTE AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.lager_palette; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: LAGER_PALETTE.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
