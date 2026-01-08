package panter.gmbh.indep.dataTools.RECORD2.ParamTypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Date;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public abstract class ParamDataObject {
	public String sName = null;
	
	public String sValue = null;
	public Long   lValue = null;
	public BigDecimal bdValue = null;
	public Date   dValue = null;
	
	protected String sDelimiter = "'";
	
	public ParamDataObject (){
		
	}
	
	public ParamDataObject (String name){
		this.sName = name;
	}

	public void setDelimiter (String delimiter){
		sDelimiter = delimiter;
	}
	
	public String Name(){
		return sName;
	}
	
	public abstract void CopyFrom(ParamDataObject o);
	
	public abstract String Value() ;
	
	public abstract String ValuePlain();
	
	public abstract int SetPreparedStatementValue(MyDBStatementPrepared stmt, int idx);
}
