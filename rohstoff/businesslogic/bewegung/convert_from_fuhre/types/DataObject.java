package rohstoff.businesslogic.bewegung.convert_from_fuhre.types;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Date;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public abstract class DataObject {
	public String sName = null;
	public String sValue = null;
	public Long   lValue = null;
	public BigDecimal bdValue = null;
	public Date   dValue = null;
	
	protected String sDelimiter = "'";
	
	public DataObject (){
		
	}
	
	public DataObject (String name){
		this.sName = name;
	}

	public void setDelimiter (String delimiter){
		sDelimiter = delimiter;
	}
	
	public String Name(){
		return sName;
	}
	
	public abstract void CopyFrom(DataObject o);
	
	public abstract String Value() ;
	
	public abstract String Value(String ValueWhenEmpty);
	
	public abstract String ValuePlain();
	
	public abstract String ValuePlain(String ValueWhenEmpty);
	
	public abstract int SetPreparedStatementValue(MyDBStatementPrepared stmt, int idx);
}
