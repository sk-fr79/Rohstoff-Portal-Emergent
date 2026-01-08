package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum MASK_DEF_CELL implements IF_Field {


     alignment("ALIGNMENT","ALIGNMENT","NVARCHAR2",64,64,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     bottom_insets("BOTTOM_INSETS","BOTTOM_INSETS","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     classname("CLASSNAME","CLASSNAME","NVARCHAR2",2000,2000,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     colspan("COLSPAN","COLSPAN","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     fieldname("FIELDNAME","FIELDNAME","NVARCHAR2",400,400,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     field_heigth("FIELD_HEIGTH","FIELD_HEIGTH","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     field_length("FIELD_LENGTH","FIELD_LENGTH","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     id_mask_def("ID_MASK_DEF","ID_MASK_DEF","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     id_mask_def_cell("ID_MASK_DEF_CELL","ID_MASK_DEF_CELL","NUMBER",11,10,0,false,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),true),
     left_insets("LEFT_INSETS","LEFT_INSETS","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     right_insets("RIGHT_INSETS","RIGHT_INSETS","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     rowspan("ROWSPAN","ROWSPAN","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     start_col_in_mask("START_COL_IN_MASK","START_COL_IN_MASK","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     start_row_in_mask("START_ROW_IN_MASK","START_ROW_IN_MASK","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     text_bold("TEXT_BOLD","TEXT_BOLD","NVARCHAR2",1,1,0,false,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     text_italic("TEXT_ITALIC","TEXT_ITALIC","NVARCHAR2",1,1,0,false,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     text_size("TEXT_SIZE","TEXT_SIZE","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     top_insets("TOP_INSETS","TOP_INSETS","NUMBER",11,10,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
     usertext("USERTEXT","USERTEXT","NVARCHAR2",2000,2000,0,true,_TAB.mask_def_cell.fullTableName(),_TAB.mask_def_cell.baseTableName(),false),
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

     private MASK_DEF_CELL( String   p_fieldName,  
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

         for (IF_Field field: MASK_DEF_CELL.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.mask_def_cell.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.mask_def_cell.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JD_MASK_DEF_CELL 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.mask_def_cell.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JD_MASK_DEF_CELL AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.mask_def_cell.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JD_MASK_DEF_CELL AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.mask_def_cell; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JD_MASK_DEF_CELL AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.mask_def_cell; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: MASK_DEF_CELL.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
