package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.exceptions.myException;

public interface IF_Field {
    public String    fullTableName(); 
    public String    baseTableName(); 
    public boolean   is_PK(); 
    public String    fieldName(); 
    public String    fieldLabel(); 
    public String    fieldType(); 
    public Integer   fieldTextLength(); 
    public Integer   fieldNumberLength(); 
    public Integer   fieldDecimals(); 
    public Boolean   is_fieldNullableBasic();  

    //2016-02-02: neue default methode um einen standard-key fuer die RB_ComponentMap zu erzeugen
    public default RB_KF  fk() throws myException {
    	return new RB_KF(this);
    }
    
    //2016-02-02: neue default methode um einen standard-key fuer die RB_ComponentMap zu erzeugen
    public default RB_KF  fk(String key) throws myException {
    	return new RB_KF(this,key);
    }
  
    /** 
     *  
     * @return Fieldname,i.e. NAME1 
     */ 
    public Term   t();  


    /** 
     *  
     * @return Fieldname with table, i.e. alias.FIELD 
     */ 
    public Term    t(String tabAlias);
    
    
    /** 
     *  
     * @return Fieldname with tableAlias and fieldAlias, i.e. alias.FIELD AS fieldAlias
     */ 
    public Term    t(String tabAlias, String fieldAlias);  
   
    
    /** 
     *  
     * @return fieldname without tablealias i.e. NAME1 
     */ 
    public String    fn(); 

    /** 
    * @return fieldname with tablealias i.e. <alias>.NAME1 
    */ 
    public String    fn(String alias);

    /**  
     * @return full-tablename i.e. JT_ADRESSE 
     */ 
     public String    tn();

    /** 
     *  
     * @return full-tablename.fieldname i.e. JT_ADRESSE.NAME1 
     */ 
     public String    tnfn();

    
     /** 
      *  
      * @return _TAB - objekt , ie (JT_WAAGE_USER AS tableAlias) 
      */ 
     public _TAB _t();

     
    
    public MyMetaFieldDEF generate_MetaFieldDef();

    
    
    public default FieldValPair v(String formatedValue) {
    	return new FieldValPair(this,formatedValue);
    }
    
    
    public default String getDebugString() {
    	return this.tn()+"->"+this.fn();
    }
}
