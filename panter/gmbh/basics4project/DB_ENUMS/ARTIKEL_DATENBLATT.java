package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum ARTIKEL_DATENBLATT implements IF_Field {


     abmessung1("ABMESSUNG1","ABMESSUNG1","NUMBER",12,10,4,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     abmessung2("ABMESSUNG2","ABMESSUNG2","NUMBER",12,10,4,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     abmessung3("ABMESSUNG3","ABMESSUNG3","NUMBER",12,10,4,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     beschreibung("BESCHREIBUNG","BESCHREIBUNG","NVARCHAR2",2000,2000,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     bezeichnung("BEZEICHNUNG","BEZEICHNUNG","NVARCHAR2",200,200,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     datum("DATUM","DATUM","DATE",7,0,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     eu_sorte("EU_SORTE","EU_SORTE","NVARCHAR2",50,50,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     gegenstand("GEGENSTAND","GEGENSTAND","NVARCHAR2",200,200,0,false,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     id_artikel("ID_ARTIKEL","ID_ARTIKEL","NUMBER",11,10,0,false,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     id_artikel_datenblatt("ID_ARTIKEL_DATENBLATT","ID_ARTIKEL_DATENBLATT","NUMBER",11,10,0,false,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),true),
     id_einheit_abmessung("ID_EINHEIT_ABMESSUNG","ID_EINHEIT_ABMESSUNG","NUMBER",11,10,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     id_einheit_staerke("ID_EINHEIT_STAERKE","ID_EINHEIT_STAERKE","NUMBER",11,10,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     id_user_kontakt("ID_USER_KONTAKT","ID_USER_KONTAKT","NUMBER",11,10,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     isri_code("ISRI_CODE","ISRI_CODE","NVARCHAR2",50,50,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     klassifizierung("KLASSIFIZIERUNG","KLASSIFIZIERUNG","NVARCHAR2",200,200,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     legierung_info("LEGIERUNG_INFO","LEGIERUNG_INFO","NVARCHAR2",200,200,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     nachkomma_abmessungen("NACHKOMMA_ABMESSUNGEN","NACHKOMMA_ABMESSUNGEN","NUMBER",2,1,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     nachkomma_staerke("NACHKOMMA_STAERKE","NACHKOMMA_STAERKE","NUMBER",2,1,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     staerke("STAERKE","STAERKE","NUMBER",12,10,4,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.artikel_datenblatt.fullTableName(),_TAB.artikel_datenblatt.baseTableName(),false),
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

     private ARTIKEL_DATENBLATT( String   p_fieldName,  
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

         for (IF_Field field: ARTIKEL_DATENBLATT.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.artikel_datenblatt.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.artikel_datenblatt.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_ARTIKEL_DATENBLATT 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.artikel_datenblatt.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_ARTIKEL_DATENBLATT AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.artikel_datenblatt.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ARTIKEL_DATENBLATT AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.artikel_datenblatt; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_ARTIKEL_DATENBLATT AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.artikel_datenblatt; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: ARTIKEL_DATENBLATT.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
