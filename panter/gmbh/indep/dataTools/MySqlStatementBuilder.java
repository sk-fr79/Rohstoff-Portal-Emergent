package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MySqlStatementBuilder extends HashMap<String,String>
{

	private String cTableName = null;
	
	//2015-02-26: optional kann ein 
	private MyRECORD_IF_FILLABLE  RecordCorrelated = null;

	public MySqlStatementBuilder()
	{
		super();
	}

	//2011-07-29: optional kann der Tablename in den statementbuilder uebergeben werden
	public MySqlStatementBuilder(String TableName)
	{
		super();
		this.cTableName = TableName;
	}


	
	
	/**
     * @param cFieldName
     * @param cValueString
     * @param bString
      */
    public void addSQL_Paar(String cFieldName, String cValueString,boolean bString) throws myException
    {
    	this.addSQL_Paar(cFieldName, cValueString, bString,true);
    }

    
	/**
     * @param cFieldName
     * @param cSQLValueString
     */
    public void addSQL_Paar(String cFieldName, String cSQLValueString) throws myException
    {
    	this.addSQL_Paar(cFieldName, cSQLValueString, false,true);
    }

    
    
	/**
     * @param cFieldName
     * @param cSQLValueString
     */
    public void add_raw(IF_Field field, String cSQLValueString, boolean wrapInTicks) throws myException   {
    	if (S.isEmpty(cSQLValueString)) {
    		this.put(field.fn(), "NULL");
    	} else {
    		this.put(field.fn(), wrapInTicks?bibALL.MakeSql(cSQLValueString):cSQLValueString);
    	}
    }


    
    
    
    /**
     * @param cFieldName
     * @param cValueString
     * @param bString
     * @param bNullAllowed
     */
    public void addSQL_Paar(String cFieldName, String cValueString, boolean bString, boolean bNullAllowed) throws myException
    {
        String cValue = cValueString;
    	
        if (bNullAllowed && S.isEmpty(cValueString))
        {
        	cValue = "NULL";
        }
        else
        {
        	if (cValue == null)
        	{
        		throw new myException(this,"Null-Value is not allowed !!");
        	}
        	else
        	{
	            cValue = cValueString;
	            if (bString)
	                cValue= bibALL.MakeSql(cValueString);
        	}
        }
        
        this.put(cFieldName,cValue);

        

    }

    
    
    public String get_cFieldsBlock(boolean bMitKlammer)
    {
    	String cRueck = "";
    	StringBuilder sb = new StringBuilder();
    	
		Iterator<java.util.Map.Entry<String, String>> it = this.entrySet().iterator(); 
	    
		
		while (it.hasNext()) 
		{
		    Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
//		    cRueck += ","+entry.getKey();
		    // in schleifen ist Stringbuilder schneller
		    sb.append(",");
		    sb.append(entry.getKey());
		} 	
		
		
//    	if (cRueck.length()>0)
//    		cRueck = cRueck.substring(1);            // erstes komma weg
		
		if (sb.length() > 0){
			cRueck = sb.substring(1);
		} 

    	if (bMitKlammer)
    		cRueck = "("+cRueck+")";
    	
        return  cRueck;
    }

    
    
    
    public String get_cValuesBlock(boolean bMitKlammer)
    {
    	String cRueck = "";
    	StringBuilder sb = new StringBuilder();
    	
		Iterator<java.util.Map.Entry<String, String>> it = this.entrySet().iterator(); 
	    
		while (it.hasNext()) 
		{
		    Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
		    sb.append(",");
		    sb.append(entry.getValue());
//		    cRueck += ","+entry.getValue();
		} 	
		
//    	if (cRueck.length()>0)
//    		cRueck = cRueck.substring(1);            // erstes komma weg
		if (sb.length() > 0){
			cRueck = sb.substring(1);
		} 
    	
    	if (bMitKlammer)
    		cRueck = "("+cRueck+")";
    	
    	
        return  cRueck;
    }

    
    /**
     * @param vFieldsNotInUpdate ist vector mit ausschlussfeldern, damit eine MySqlZuordnung fuer insert und update verwendet werden kann -> rauslassen der id (z.B.)
     * @return
     */
    public String get_cUpdateSetBlock(Vector<String> vFieldsNotInUpdate)
    {
    	String cRueck = "";
    	StringBuilder sb = new StringBuilder();
    	
		Iterator<java.util.Map.Entry<String, String>> it = this.entrySet().iterator(); 
	    
		while (it.hasNext()) 
		{
		    Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
		    if (vFieldsNotInUpdate != null)       // pruefen, ob der feldname in der negativliste steht
		    {
		    	if (!vFieldsNotInUpdate.contains(entry.getKey()))
		    	{
//		    		cRueck += ","+entry.getKey()+"="+entry.getValue();
		    		sb.append(",");
		    		sb.append(entry.getKey());
		    		sb.append("=");
		    		sb.append(entry.getValue());
		    	}
		    }
		    else
		    {
//		    	cRueck += ","+entry.getKey()+"="+entry.getValue();
	    		sb.append(",");
	    		sb.append(entry.getKey());
	    		sb.append("=");
	    		sb.append(entry.getValue());
		    }
		} 	
		
//    	if (cRueck.length()>0)
//    		cRueck = cRueck.substring(1);            // erstes komma weg
		
		if (sb.length() > 0){
			cRueck = sb.substring(1);
		}   
		
        return  cRueck;
    }


    public String get_CompleteInsertString(String cTABLENAME)
    {
    	String cRueck = "INSERT INTO "+cTABLENAME+" "+this.get_cFieldsBlock(true)+" VALUES "+this.get_cValuesBlock(true);
    	return cRueck;
    }

    
    public String get_CompleteInsertString(String cTABLENAME,String cTableOwner)
    {
    	String cRueck = "INSERT INTO "+cTableOwner+"."+cTABLENAME+" "+this.get_cFieldsBlock(true)+" VALUES "+this.get_cValuesBlock(true);
    	return cRueck;
    }
    
    public String get_CompleteUPDATEString(String cTABLENAME,String cTableOwner, String cWHEREBLOCK_OhneWhere, Vector<String> vFieldsNotInUpdate)
    {
    	String cRueck = "UPDATE "+cTableOwner+"."+cTABLENAME+" SET "+this.get_cUpdateSetBlock(vFieldsNotInUpdate)+" WHERE "+cWHEREBLOCK_OhneWhere;
    	return cRueck;
    }

    
	//2011-07-29: optional kann der Tablename in den statementbuilder uebergeben werden
	public String get_cTableName()
	{
		return cTableName;
	}


	//2011-07-29: optional kann der Tablename in den statementbuilder uebergeben werden
	public void set_cTableName(String cTableName)
	{
		this.cTableName = cTableName;
	}


	/*
	 * 2012-08-06: moeglichkeit, datumswerte, die hier immer in der form: 'YYYY-MM-DD' auftauchen
	 *             in CTOD('2012-08-06','YYYY-MM-DD') umzuwandeln  
	 */
	public void ChangeDateFormat(MyRECORD oRecord)
	{

		for (String cField: oRecord.keySet())
		{
			if (this.containsKey(cField) && oRecord.get(cField).get_MetaFieldDef().get_bIsDateType() && !this.get(cField).toUpperCase().equals("NULL"))
			{
				this.put(cField, "TO_DATE("+this.get(cField)+",'YYYY-MM-DD')");
			}
		}
	}

	//2015-02-26: optional kann ein 
	public MyRECORD_IF_FILLABLE get_RecordCorrelated() {
		return RecordCorrelated;
	}

	public void set_RecordCorrelated(MyRECORD_IF_FILLABLE recordCorrelated) {
		RecordCorrelated = recordCorrelated;
	}
    
	
	
    public String get_InsertString() throws myException     {
    	if (S.isEmpty(this.cTableName)) {
    		throw new myException(this,"You must have tablename !");
    	}
    	String cRueck = "INSERT INTO "+this.get_cTableName()+" "+this.get_cFieldsBlock(true)+" VALUES "+this.get_cValuesBlock(true);
    	return cRueck;
    }

    
    public String get_InsertString(String cTableOwner) throws myException  {
      	if (S.isEmpty(this.cTableName) || S.isEmpty(cTableOwner)) {
    		throw new myException(this,"You must have tablename ! AND OWNER");
    	}
      	String cRueck = "INSERT INTO "+cTableOwner+"."+this.get_cTableName()+" "+this.get_cFieldsBlock(true)+" VALUES "+this.get_cValuesBlock(true);
    	return cRueck;
    }

    
    /**
     * @param f
     * @param remove_string_tags  (wenn true, dann wird aus 'Meier' Meier
     * @return   db_value of field in this statement
     * @throws myException
     */
    public String get_raw_value(IF_Field f, boolean remove_string_tags) throws myException {
    	String ret = null;
    	
    	String fname = f.fn();
    	
    	if (this.containsKey(fname)) {
    		ret = this.get(fname);
    		if (remove_string_tags) {
    			ret = bibALL.RemoveTeilString(ret, "'");
    		}
    		
    	} else {
    		throw new myException(this, "Field: "+fname+" is not in this collection !");
    	}
    	
    	return ret;
    }
    
    
    /**
     * @param f
     * @return   db_value of field in this statement
     * @throws myException
     */
    public String get_raw_value(IF_Field f) throws myException {
    	return this.get_raw_value(f, false);
    } 
	
    
    
    /**
     * entfernt eine liste von feldern aus dem statementbuilder
     * @param removeList
     * @return
     */
    public HashMap<String, String>  remove_fields(Vector<String> removeList) {
    	HashMap<String, String>  hm_realy_removed = new HashMap<>();
    	
    	Vector<String> keyset = new Vector<>(this.keySet());
    	
    	if (removeList!=null) {
	    	for (String field: keyset) {
	    		if (removeList.contains(field)) {
	    			hm_realy_removed.put(field, this.get(field));
	    			this.remove(field);
	    		}
	    	}
    	}
    	return hm_realy_removed;
    }
    
    
    
//    /**
//     * 
//     * @param cTABLENAME
//     * @param cTableOwner
//     * @param id_field
//     * @param id_uf
//     * @param vFieldsNotInUpdate
//     * @param commit
//     * @return MyRECORD or null if error. When an error happens, there may be an rollback 
//     */
//    public String get_sql4Update(String cTABLENAME, String id_field, String id_uf, Vector<String> vFieldsExclude, MyE2_MessageVector mv_sammler)    {
//    	if (mv_sammler==null) {
//    		mv_sammler=bibMSG.MV();
//    	}
//    	
//    	
//    	String csql = null;
//    	try {
//			
//			//ungewuenschte felder raus
//			HashMap<String,String> hm_save = this.remove_fields(vFieldsExclude);
//			
//			csql = "UPDATE "+bibE2.cTO()+"."+cTABLENAME+" SET "+this.get_cUpdateSetBlock(null)+" WHERE "+id_field+"="+id_uf;
//
//			this.putAll(hm_save);  //alles wieder beim alten
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error generating Update-Statement:"+e.getMessage())));;
//		}
//    	return csql;
//    }
//
//    
//    
//    /**
//     * 
//     * @param cTABLENAME
//     * @param cTableOwner
//     * @param id_field
//     * @param id_uf
//     * @param vFieldsNotInUpdate
//     * @return MyRECORD or null if error. When an error happens, there may be an rollback 
//     */
//    public MyRECORD executeUPDATE(String cTABLENAME, String id_field, String id_uf, Vector<String> vFieldsExclude, boolean commit, MyE2_MessageVector mv_sammler)    {
//    	if (mv_sammler==null) {
//    		mv_sammler=bibMSG.MV();
//    	}
//
//    	try {
//			
//			String csql = this.get_sql4Update(cTABLENAME, id_field, id_uf, vFieldsExclude,  mv_sammler);
//
//			if (mv_sammler.get_bIsOK()) {
//				
//				MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(csql), commit);
//				
//				mv_sammler.add_MESSAGE(mv);
//				
//				if (mv.get_bIsOK()) {
//					//_TAB-objekt raussuchen
//					_TAB tab = _TAB.find_Table(cTABLENAME);
//					return tab.get_RECORD(id_uf);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error executing Update-Statement:"+e.getMessage())));;
//		}
//    	return null;
//    }
//
//
//    
//    /**
//     * 
//     * @param cTABLENAME
//     * @param cTableOwner
//     * @return MyRECORD or null if error. When an error happens, there may be an rollback 
//     */
//    public String get_sql4InsertWithSequencer(String cTABLENAME, Vector<String> vFieldsExclude, MyE2_MessageVector mv_sammler)    {
//    	
//    	if (mv_sammler==null) {
//    		mv_sammler=bibMSG.MV();
//    	}
//
//
//    	String csql = null;
//    	
//       	try {
//       		
//			//ungewuenschte felder raus
//			HashMap<String,String> hm_save = this.remove_fields(vFieldsExclude);
//      		
//			//_TAB-objekt raussuchen
//			_TAB tab = _TAB.find_Table(cTABLENAME);
//
//			this.addSQL_Paar(tab.keyFieldName(), tab.seq_nextval());
//
//       		csql = "INSERT INTO "+bibE2.cTO()+"."+cTABLENAME+" "+this.get_cFieldsBlock(true)+" VALUES "+this.get_cValuesBlock(true);
//       		
//			this.putAll(hm_save);  //alles wieder beim alten
//    	
//		} catch (Exception e) {
//			e.printStackTrace();
//			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error generating insert-Statement:"+e.getMessage())));;
//		}
//    	return csql;
//
//    }

    
//    
//    /**
//     * 
//     * @param cTABLENAME
//     * @param cTableOwner
//     * @param commit
//     * @return MyRECORD or null if error. When an error happens, there may be an rollback 
//     */
//    public MyRECORD executeINSERTWithSequencer(String cTABLENAME, Vector<String> vFieldsExclude, boolean commit, MyE2_MessageVector mv_sammler)    {
//
//    	if (mv_sammler==null) {
//    		mv_sammler=bibMSG.MV();
//    	}
//
//    	
//       	try {
//       		
//			//_TAB-objekt raussuchen
//			_TAB tab = _TAB.find_Table(cTABLENAME);
//
//			this.addSQL_Paar(tab.keyFieldName(), tab.seq_nextval());
//
//       		String csql = this.get_sql4InsertWithSequencer(cTABLENAME, vFieldsExclude, mv_sammler);
//       		
//       		if (mv_sammler.get_bIsOK()) {
//       		
//				MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(csql), commit);
//				
//				mv_sammler.add_MESSAGE(mv);
//	
//				if (mv.get_bIsOK()) {
//					return tab.get_RECORD(bibDB.EinzelAbfrage("SELECT "+tab.seq_currval()+" FROM DUAL"));
//				}
//       		}
//    	
//		} catch (Exception e) {
//			e.printStackTrace();
//			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error executing insert-Statement:"+e.getMessage())));;
//		}
//    	return null;
//
//    }
    
    
//    /**
//     * automatischer speichervorgang, entscheidet ob insert oder update durch pruefen
//     * des RecordCorrelated 
//     * @param vFieldsExclude
//     * @param commit
//     * @param mv_sammler
//     * @return
//     * @throws myException
//     */
//    public MyRECORD executeSave(Vector<String> vFieldsExclude, boolean commit, MyE2_MessageVector mv_sammler) throws myException {
//    	
//    	if (mv_sammler==null) {
//    		mv_sammler=bibMSG.MV();
//    	}
//
//    	
//    	if (this.RecordCorrelated == null) {
//    		throw new myException(this, "Method executeAutomaticSave only possible with RecordCorrelated NOT NULL !!!");
//    	} else {
//    		if (this.RecordCorrelated instanceof MyRECORD) {
//    			//_TAB-objekt raussuchen
//    			_TAB tab = _TAB.find_Table(this.RecordCorrelated.get_TABLENAME());
//
//    			return this.executeUPDATE(tab.fullTableName(),  tab.keyFieldName(), ((MyRECORD)this.RecordCorrelated).ufs(tab.keyFieldName()), vFieldsExclude, commit, mv_sammler);
//    		} else {
//    			//_TAB-objekt raussuchen
//    			_TAB tab = _TAB.find_Table(this.RecordCorrelated.get_TABLENAME());
//    			
//    			return this.executeINSERTWithSequencer(tab.fullTableName(), vFieldsExclude, commit, mv_sammler);
//    		}
//    	}
//    	
//    }
//    
    
//    /**
//     * automatischer speichervorgang, entscheidet ob insert oder update durch pruefen
//     * des RecordCorrelated, es werden die standardfelder excluded 
//     * @param commit
//     * @param mv_sammler
//     * @return
//     * @throws myException
//     */
//    public MyRECORD executeSave(boolean commit, MyE2_MessageVector mv_sammler) throws myException {
//    	return this.executeSave(DB_STATICS.get_AutomaticWrittenFields_STD(), commit, mv_sammler);
//    }

    
//    /**
//     * liefert je nach status eine save_statement
//     * @param vFieldsExclude
//     * @param mv_sammler
//     * @return
//     * @throws myException
//     */
//    public String sql4Save(Vector<String> vFieldsExclude, MyE2_MessageVector mv_sammler) throws myException {
//    	if (this.RecordCorrelated == null) {
//    		throw new myException(this, "Method executeAutomaticSave only possible with RecordCorrelated NOT NULL !!!");
//    	} else {
//    		if (this.RecordCorrelated instanceof MyRECORD) {
//    			//_TAB-objekt raussuchen
//    			_TAB tab = _TAB.find_Table(this.RecordCorrelated.get_TABLENAME());
//
//    			return this.get_sql4Update(tab.fullTableName(),  tab.keyFieldName(), ((MyRECORD)this.RecordCorrelated).ufs(tab.keyFieldName()), vFieldsExclude, mv_sammler);
//    		} else {
//    			//_TAB-objekt raussuchen
//    			_TAB tab = _TAB.find_Table(this.RecordCorrelated.get_TABLENAME());
//    			
//    			return this.get_sql4InsertWithSequencer(tab.fullTableName(), vFieldsExclude, mv_sammler);
//    		}
//    	}
//    	
//    }
//    
//    
//    /**
//     * liefert je nach status eine save_statement
//     * @param mv_sammler
//     * @return
//     * @throws myException
//     */
//    public String sql4Save(MyE2_MessageVector mv_sammler) throws myException {
//    	if (this.RecordCorrelated == null) {
//    		throw new myException(this, "Method executeAutomaticSave only possible with RecordCorrelated NOT NULL !!!");
//    	} else {
//    		if (this.RecordCorrelated instanceof MyRECORD) {
//    			//_TAB-objekt raussuchen
//    			_TAB tab = _TAB.find_Table(this.RecordCorrelated.get_TABLENAME());
//
//    			return this.get_sql4Update(tab.fullTableName(),  tab.keyFieldName(), ((MyRECORD)this.RecordCorrelated).ufs(tab.keyFieldName()),DB_STATICS.get_AutomaticWrittenFields_STD(), mv_sammler);
//    		} else {
//    			//_TAB-objekt raussuchen
//    			_TAB tab = _TAB.find_Table(this.RecordCorrelated.get_TABLENAME());
//    			
//    			return this.get_sql4InsertWithSequencer(tab.fullTableName(), DB_STATICS.get_AutomaticWrittenFields_STD(), mv_sammler);
//    		}
//    	}
//    	
//    }
    
    
    
    
    
}
