package panter.gmbh.basics4project.DB_ENUMS;


import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;


public enum FBAM implements IF_Field {


     abgeschlossen_behebung("ABGESCHLOSSEN_BEHEBUNG","ABGESCHLOSSEN_BEHEBUNG","NVARCHAR2",1,1,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     abgeschlossen_kontrolle("ABGESCHLOSSEN_KONTROLLE","ABGESCHLOSSEN_KONTROLLE","NVARCHAR2",1,1,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     auswirkungen("AUSWIRKUNGEN","AUSWIRKUNGEN","NVARCHAR2",400,400,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     bam_datum("BAM_DATUM","BAM_DATUM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     bam_grund("BAM_GRUND","BAM_GRUND","NVARCHAR2",80,80,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     bam_ort("BAM_ORT","BAM_ORT","NVARCHAR2",80,80,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     bearbeitung("BEARBEITUNG","BEARBEITUNG","NVARCHAR2",1,1,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     bearbeitung_datum("BEARBEITUNG_DATUM","BEARBEITUNG_DATUM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     beheb_vermerk("BEHEB_VERMERK","BEHEB_VERMERK","NVARCHAR2",400,400,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     beheb_vorschlag("BEHEB_VORSCHLAG","BEHEB_VORSCHLAG","NVARCHAR2",400,400,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     betreff_bam("BETREFF_BAM","BETREFF_BAM","NVARCHAR2",80,80,0,false,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     buchungsnummer("BUCHUNGSNUMMER","BUCHUNGSNUMMER","NVARCHAR2",40,40,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     datum_ausstellung("DATUM_AUSSTELLUNG","DATUM_AUSSTELLUNG","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     datum_behebung("DATUM_BEHEBUNG","DATUM_BEHEBUNG","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     datum_kontrolle("DATUM_KONTROLLE","DATUM_KONTROLLE","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     erzeugt_am("ERZEUGT_AM","ERZEUGT_AM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     erzeugt_von("ERZEUGT_VON","ERZEUGT_VON","NVARCHAR2",10,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     fehlerbeschreibung("FEHLERBESCHREIBUNG","FEHLERBESCHREIBUNG","NVARCHAR2",800,800,0,false,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     fehlerinfo_extern("FEHLERINFO_EXTERN","FEHLERINFO_EXTERN","NVARCHAR2",600,600,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     fehlerursache("FEHLERURSACHE","FEHLERURSACHE","NVARCHAR2",80,80,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     feststellung_bam("FESTSTELLUNG_BAM","FESTSTELLUNG_BAM","NVARCHAR2",80,80,0,false,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     geaendert_von("GEAENDERT_VON","GEAENDERT_VON","NVARCHAR2",10,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_fbam("ID_FBAM","ID_FBAM","NUMBER",11,10,0,false,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),true),
     id_mandant("ID_MANDANT","ID_MANDANT","NUMBER",11,10,0,false,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_user_ausstellung("ID_USER_AUSSTELLUNG","ID_USER_AUSSTELLUNG","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_user_bearbeitung("ID_USER_BEARBEITUNG","ID_USER_BEARBEITUNG","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_user_behebung("ID_USER_BEHEBUNG","ID_USER_BEHEBUNG","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_user_kontrolle("ID_USER_KONTROLLE","ID_USER_KONTROLLE","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_vpos_tpa_fuhre("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     id_vpos_tpa_fuhre_ort("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     letzte_aenderung("LETZTE_AENDERUNG","LETZTE_AENDERUNG","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     sys_trigger_timestamp("SYS_TRIGGER_TIMESTAMP","SYS_TRIGGER_TIMESTAMP","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     sys_trigger_uuid("SYS_TRIGGER_UUID","SYS_TRIGGER_UUID","NVARCHAR2",40,40,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     sys_trigger_version("SYS_TRIGGER_VERSION","SYS_TRIGGER_VERSION","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_abholdatum("WM_ABHOLDATUM","WM_ABHOLDATUM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_abnehmer("WM_ABNEHMER","WM_ABNEHMER","NVARCHAR2",50,50,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_anlieferdatum("WM_ANLIEFERDATUM","WM_ANLIEFERDATUM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_ansprechpartner_inhouse("WM_ANSPRECHPARTNER_INHOUSE","WM_ANSPRECHPARTNER_INHOUSE","NVARCHAR2",50,50,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_ansprechpartner_lieferant("WM_ANSPRECHPARTNER_LIEFERANT","WM_ANSPRECHPARTNER_LIEFERANT","NVARCHAR2",50,50,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_artbez1("WM_ARTBEZ1","WM_ARTBEZ1","NVARCHAR2",80,80,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_datum("WM_DATUM","WM_DATUM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_faxnummer("WM_FAXNUMMER","WM_FAXNUMMER","NVARCHAR2",50,50,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_gesperrt("WM_GESPERRT","WM_GESPERRT","NVARCHAR2",1,1,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_letzterdruck_datum("WM_LETZTERDRUCK_DATUM","WM_LETZTERDRUCK_DATUM","DATE",7,0,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_letzterdruck_uhrzeit("WM_LETZTERDRUCK_UHRZEIT","WM_LETZTERDRUCK_UHRZEIT","NVARCHAR2",10,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_menge_abladen("WM_MENGE_ABLADEN","WM_MENGE_ABLADEN","NUMBER",14,12,3,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_ort("WM_ORT","WM_ORT","NVARCHAR2",30,30,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_uebernahmevorschlag("WM_UEBERNAHMEVORSCHLAG","WM_UEBERNAHMEVORSCHLAG","NVARCHAR2",400,400,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_uhrzeit("WM_UHRZEIT","WM_UHRZEIT","NVARCHAR2",10,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
     wm_zaehler_entsperrung("WM_ZAEHLER_ENTSPERRUNG","WM_ZAEHLER_ENTSPERRUNG","NUMBER",11,10,0,true,_TAB.fbam.fullTableName(),_TAB.fbam.baseTableName(),false),
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

     private FBAM( String   p_fieldName,  
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

         for (IF_Field field: FBAM.values()) { 
            ftlist.add(new FieldTerm( 
                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  
                       field, 
                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); 

          }   

          return ftlist; 
     } 



    public static String fullTabName() { 
       return _TAB.fbam.fullTableName(); 
    } 
 
 
    public static String baseTabName() { 
       return _TAB.fbam.baseTableName(); 
    } 
 
 
    /** 
     *  
     * @return full Tablename, ie JT_FBAM 
     */ 
    public static TableTerm T() { 
       return new TableTerm(_TAB.fbam.fullTableName()); 
    } 
 
 
    /** 
     *  
     * @return Tablename with alias, ie (JT_FBAM AS tableAlias) 
     */ 
    public static TableTerm T(String tableAlias) { 
       return new TableTerm(_TAB.fbam.fullTableName(),tableAlias); 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FBAM AS tableAlias) 
     */ 
    public static _TAB _tab() { 
       return _TAB.fbam; 
    } 
 
 
 
    /** 
     *  
     * @return _TAB - objekt , ie (JT_FBAM AS tableAlias) 
     */ 
    public _TAB _t() { 
       return _TAB.fbam; 
    } 
 
 
 
    /** 
     *  
     * @return field from fieldname or null 
     */ 
    public static IF_Field field(String fieldName) { 
      IF_Field field_rueck = null; 
 
      for (IF_Field field: FBAM.values()) { 
         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { 
            field_rueck=field; 
         } 
       } 
       return field_rueck; 
   } 
 
} 
