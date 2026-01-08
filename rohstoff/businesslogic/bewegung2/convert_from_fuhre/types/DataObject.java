package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Date;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public abstract class DataObject<T> {
	
	public IF_Field _field = null;
	
	public boolean _isRaw = false;
	
	public String sValue = null;
	public Long   lValue = null;
	public BigDecimal bdValue = null;
	public Date   dValue = null;
	
	protected String sDelimiter = "";
	
	public DataObject (){
		
	}
	
	public DataObject (IF_Field field){
		this._field = field;
	}

	public void setDelimiter (String delimiter){
		sDelimiter = delimiter;
	}
	
	public IF_Field Field(){
		return _field;
	}
	
	public abstract T setRaw(boolean isRaw);
	
	public abstract boolean isRaw();
	
	public abstract void CopyFrom(DataObject o);
	
	public abstract Object Value() ;
	
	public abstract Object Value(Object ValueWhenEmpty);
	
	public abstract String ValuePlain();
	
	public abstract String ValuePlain(String ValueWhenEmpty);
	
}
