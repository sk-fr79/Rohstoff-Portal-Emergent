package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum FIBU implements IF_Field {


     anzahl_scheck_druck("ANZAHL_SCHECK_DRUCK","ANZAHL_SCHECK_DRUCK","NUMBER",4,3,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     bearbeiterkuerzel("BEARBEITERKUERZEL","BEARBEITERKUERZEL","NVARCHAR2",20,20,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     buchungsblock_nr("BUCHUNGSBLOCK_NR","BUCHUNGSBLOCK_NR","NUMBER",13,12,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     buchungsdatum("BUCHUNGSDATUM","BUCHUNGSDATUM","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     buchungsinfo("BUCHUNGSINFO","BUCHUNGSINFO","NVARCHAR2",200,200,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     buchungstyp("BUCHUNGSTYP","BUCHUNGSTYP","NVARCHAR2",20,20,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     buchung_geschlossen("BUCHUNG_GESCHLOSSEN","BUCHUNG_GESCHLOSSEN","NVARCHAR2",1,1,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     datumveraenderung("DATUMVERAENDERUNG","DATUMVERAENDERUNG","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     endbetrag_basis_waehrung("ENDBETRAG_BASIS_WAEHRUNG","ENDBETRAG_BASIS_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     endbetrag_fremd_waehrung("ENDBETRAG_FREMD_WAEHRUNG","ENDBETRAG_FREMD_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     faktor_buchung_plus_minus("FAKTOR_BUCHUNG_PLUS_MINUS","FAKTOR_BUCHUNG_PLUS_MINUS","NUMBER",3,2,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     fremdbelegnummer("FREMDBELEGNUMMER","FREMDBELEGNUMMER","NVARCHAR2",30,30,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     id_adresse("ID_ADRESSE","ID_ADRESSE","NUMBER",11,10,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     id_fibu("ID_FIBU","ID_FIBU","NUMBER",11,10,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     id_vkopf_rg("ID_VKOPF_RG","ID_VKOPF_RG","NUMBER",11,10,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     intern_info("INTERN_INFO","INTERN_INFO","NVARCHAR2",800,800,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     korrekturbuchung("KORREKTURBUCHUNG","KORREKTURBUCHUNG","NVARCHAR2",1,1,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     nettosumme_basis_waehrung("NETTOSUMME_BASIS_WAEHRUNG","NETTOSUMME_BASIS_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     nettosumme_fremd_waehrung("NETTOSUMME_FREMD_WAEHRUNG","NETTOSUMME_FREMD_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     restbetrag_fremd_waehrung("RESTBETRAG_FREMD_WAEHRUNG","RESTBETRAG_FREMD_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     schecknummer("SCHECKNUMMER","SCHECKNUMMER","NVARCHAR2",100,100,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     scheck_verwendungszweck("SCHECK_VERWENDUNGSZWECK","SCHECK_VERWENDUNGSZWECK","NVARCHAR2",800,800,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     skontobetrag_basis_waehrung("SKONTOBETRAG_BASIS_WAEHRUNG","SKONTOBETRAG_BASIS_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     skontobetrag_fremd_waehrung("SKONTOBETRAG_FREMD_WAEHRUNG","SKONTOBETRAG_FREMD_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     skonto_datum_ueberschritten("SKONTO_DATUM_UEBERSCHRITTEN","SKONTO_DATUM_UEBERSCHRITTEN","NVARCHAR2",1,1,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     steuersumme_basis_waehrung("STEUERSUMME_BASIS_WAEHRUNG","STEUERSUMME_BASIS_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     steuersumme_fremd_waehrung("STEUERSUMME_FREMD_WAEHRUNG","STEUERSUMME_FREMD_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     storniert("STORNIERT","STORNIERT","NVARCHAR2",1,1,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     storniert_am("STORNIERT_AM","STORNIERT_AM","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     stornogrund("STORNOGRUND","STORNOGRUND","NVARCHAR2",200,200,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     stornokuerzel("STORNOKUERZEL","STORNOKUERZEL","NVARCHAR2",20,20,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     vorlaeufig("VORLAEUFIG","VORLAEUFIG","NVARCHAR2",1,1,0,false,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     waehrung_fremd("WAEHRUNG_FREMD","WAEHRUNG_FREMD","NVARCHAR2",20,20,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     zahlungsbetrag_basis_waehrung("ZAHLUNGSBETRAG_BASIS_WAEHRUNG","ZAHLUNGSBETRAG_BASIS_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     zahlungsbetrag_fremd_waehrung("ZAHLUNGSBETRAG_FREMD_WAEHRUNG","ZAHLUNGSBETRAG_FREMD_WAEHRUNG","NUMBER",16,14,2,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
     zahlungsziel("ZAHLUNGSZIEL","ZAHLUNGSZIEL","DATE",7,0,0,true,_TAB.fibu.fullTableName(),_TAB.fibu.baseTableName(),false),
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

     private FIBU( String   p_fieldName,  
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

         for (IF_Field field: FIBU.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.fibu.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.fibu.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_FIBU 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.fibu.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_FIBU AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.fibu.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FIBU AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.fibu; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FIBU AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.fibu; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: FIBU.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
