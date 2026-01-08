package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum WAEGUNG implements IF_Field {


     datum("DATUM","DATUM","DATE",7,0,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     gewicht("GEWICHT","GEWICHT","NUMBER",21,19,3,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     handeingabe("HANDEINGABE","HANDEINGABE","NVARCHAR2",1,1,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     handeingabe_bem("HANDEINGABE_BEM","HANDEINGABE_BEM","NVARCHAR2",100,100,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     id_user_waegung("ID_USER_WAEGUNG","ID_USER_WAEGUNG","NUMBER",11,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     id_waage_settings("ID_WAAGE_SETTINGS","ID_WAAGE_SETTINGS","NUMBER",11,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     id_waegung("ID_WAEGUNG","ID_WAEGUNG","NUMBER",11,10,0,false,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),true),
     id_wiegekarte("ID_WIEGEKARTE","ID_WIEGEKARTE","NUMBER",11,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     storno("STORNO","STORNO","NVARCHAR2",1,1,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     waage_ds_ori("WAAGE_DS_ORI","WAAGE_DS_ORI","NVARCHAR2",100,100,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     waegung_pos("WAEGUNG_POS","WAEGUNG_POS","NUMBER",11,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_brutto_gewicht("W_BRUTTO_GEWICHT","W_BRUTTO_GEWICHT","NVARCHAR2",8,8,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_datum("W_DATUM","W_DATUM","NVARCHAR2",10,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_einheit("W_EINHEIT","W_EINHEIT","NVARCHAR2",2,2,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_fehlercode("W_FEHLERCODE","W_FEHLERCODE","NVARCHAR2",2,2,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_ident_nr("W_IDENT_NR","W_IDENT_NR","NVARCHAR2",10,10,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_netto_gewicht("W_NETTO_GEWICHT","W_NETTO_GEWICHT","NVARCHAR2",8,8,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_pruefziffer("W_PRUEFZIFFER","W_PRUEFZIFFER","NVARCHAR2",8,8,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_status("W_STATUS","W_STATUS","NVARCHAR2",2,2,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_taracode("W_TARACODE","W_TARACODE","NVARCHAR2",2,2,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_taragewicht("W_TARAGEWICHT","W_TARAGEWICHT","NVARCHAR2",8,8,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_terminal("W_TERMINAL","W_TERMINAL","NVARCHAR2",3,3,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_waagen_nr("W_WAAGEN_NR","W_WAAGEN_NR","NVARCHAR2",1,1,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_waegebereich("W_WAEGEBEREICH","W_WAEGEBEREICH","NVARCHAR2",1,1,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     w_zeit("W_ZEIT","W_ZEIT","NVARCHAR2",5,5,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
     zeit("ZEIT","ZEIT","NVARCHAR2",8,8,0,true,_TAB.waegung.fullTableName(),_TAB.waegung.baseTableName(),false),
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

     private WAEGUNG( String   p_fieldName,  
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

         for (IF_Field field: WAEGUNG.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.waegung.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.waegung.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_WAEGUNG 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.waegung.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_WAEGUNG AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.waegung.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_WAEGUNG AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.waegung; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_WAEGUNG AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.waegung; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: WAEGUNG.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
