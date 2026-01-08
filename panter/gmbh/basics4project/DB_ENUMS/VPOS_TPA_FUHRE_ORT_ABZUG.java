package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum VPOS_TPA_FUHRE_ORT_ABZUG implements IF_Field {


     abzug("ABZUG","ABZUG","NUMBER",14,12,4,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     abzug2("ABZUG2","ABZUG2","NUMBER",14,12,4,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     abzugtyp("ABZUGTYP","ABZUGTYP","NVARCHAR2",100,100,0,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     abzug_belegtext("ABZUG_BELEGTEXT","ABZUG_BELEGTEXT","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     abzug_belegtext_schablone("ABZUG_BELEGTEXT_SCHABLONE","ABZUG_BELEGTEXT_SCHABLONE","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     anteil_abzug_gesamt("ANTEIL_ABZUG_GESAMT","ANTEIL_ABZUG_GESAMT","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     anteil_abzug_gesamt_fw("ANTEIL_ABZUG_GESAMT_FW","ANTEIL_ABZUG_GESAMT_FW","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     epreis_nach_abzug("EPREIS_NACH_ABZUG","EPREIS_NACH_ABZUG","NUMBER",12,10,2,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     epreis_nach_abzug_fw("EPREIS_NACH_ABZUG_FW","EPREIS_NACH_ABZUG_FW","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     epreis_vor_abzug("EPREIS_VOR_ABZUG","EPREIS_VOR_ABZUG","NUMBER",12,10,2,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     epreis_vor_abzug_fw("EPREIS_VOR_ABZUG_FW","EPREIS_VOR_ABZUG_FW","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     id_abzugsgrund("ID_ABZUGSGRUND","ID_ABZUGSGRUND","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     id_vpos_tpa_fuhre_ort_abzug("ID_VPOS_TPA_FUHRE_ORT_ABZUG","ID_VPOS_TPA_FUHRE_ORT_ABZUG","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),true),
     kurztext("KURZTEXT","KURZTEXT","NVARCHAR2",100,100,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     langtext("LANGTEXT","LANGTEXT","NVARCHAR2",400,400,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     mengenfaktor_fuer_druck("MENGENFAKTOR_FUER_DRUCK","MENGENFAKTOR_FUER_DRUCK","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     menge_nach_abzug("MENGE_NACH_ABZUG","MENGE_NACH_ABZUG","NUMBER",14,12,3,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     menge_vor_abzug("MENGE_VOR_ABZUG","MENGE_VOR_ABZUG","NUMBER",14,12,3,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     pos_nummer("POS_NUMMER","POS_NUMMER","NUMBER",11,10,0,false,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     preisfaktor_fuer_druck("PREISFAKTOR_FUER_DRUCK","PREISFAKTOR_FUER_DRUCK","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     preisfaktor_fuer_druck_fw("PREISFAKTOR_FUER_DRUCK_FW","PREISFAKTOR_FUER_DRUCK_FW","NUMBER",12,10,2,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
     waage_abzug("WAAGE_ABZUG","WAAGE_ABZUG","NVARCHAR2",1,1,0,true,_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),_TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(),false),
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

     private VPOS_TPA_FUHRE_ORT_ABZUG( String   p_fieldName,  
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

         for (IF_Field field: VPOS_TPA_FUHRE_ORT_ABZUG.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.vpos_tpa_fuhre_ort_abzug.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_VPOS_TPA_FUHRE_ORT_ABZUG 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_VPOS_TPA_FUHRE_ORT_ABZUG AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.vpos_tpa_fuhre_ort_abzug.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_TPA_FUHRE_ORT_ABZUG AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.vpos_tpa_fuhre_ort_abzug; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_VPOS_TPA_FUHRE_ORT_ABZUG AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.vpos_tpa_fuhre_ort_abzug; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: VPOS_TPA_FUHRE_ORT_ABZUG.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
