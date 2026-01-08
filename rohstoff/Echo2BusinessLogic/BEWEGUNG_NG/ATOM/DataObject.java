package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM;

import java.math.BigDecimal;
import java.util.Date;

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
	
	public abstract String ValuePlain();
	
}
