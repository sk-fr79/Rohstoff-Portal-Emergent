package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.exceptions.myException;

public class DODate extends DataObject {

	private boolean _value_is_sql_plain = false;
	
	
	public DODate(IF_Field field, Date Value) {
		super(field);
		this.dValue = Value;
	}
	
	public DODate(IF_Field field){
		super(field);
	}
	
	public DODate (Date value){
		this.dValue = value;
	}
	
	

	public DODate(String value, String Format) throws myException{
		this.setValue(value,Format);
	}
	
	@Override
	public DODate setRaw(boolean isRaw){
		_isRaw = isRaw;
		return this;
	}

	@Override
	public boolean isRaw() {
		return _isRaw;
	}
	
	

	public void setValue(Date value) {
		this.dValue = value;
	}
	

	/**
	 * datumswert setzen, falls man für den insert was hat wie "sysdate"
	 * @author manfred
	 * @date 10.10.2016
	 *
	 * @param value
	 * @param bIsPlainSql
	 * @throws myException
	 */
	public void setValue(String value,boolean bIsPlainSql) {
		_value_is_sql_plain = bIsPlainSql;
		if (!_value_is_sql_plain){
			try {
				setValue(value);
			} catch (myException e) {
				dValue=null;
				sValue=null;
			}
		} else {
			dValue = null;
			sValue = value;
		}
		
	}
	
	
	public void setValue(String value) throws myException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(value != null){
			try {
				dValue = df.parse(value);
			} catch (ParseException e) {
				dValue = null;
			}
			
			// zweiter versuch
			if (dValue == null){
				df = new SimpleDateFormat("dd.MM.yyyy");
				try {
					dValue = df.parse(value);
				} catch (ParseException e) {
					throw new myException("Fehler beim Ermitteln des Datums: " + value , e);
				}
			}
		}
	}
	

	
	
	public void setValue(String value, String Format) throws myException{
		SimpleDateFormat df = new SimpleDateFormat(Format);
		if(value != null){
			try {
				dValue = df.parse(value);
			} catch (ParseException e) {
				dValue = null;
				throw new myException("Fehler beim Ermitteln des Datums: " + value , e);
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DataObject#ValuePlain()
	 */
	@Override
	public String ValuePlain() {
		String sRet = null;
		if (dValue != null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sRet = " to_date('" +  df.format(dValue) + "','yyyy-MM-dd') ";
		} else if (sValue != null){
			sRet = sValue;
		}
		return sRet;
	}
	
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public String ValuePlain(String ValueWhenEmpty) {
		String sRet = null;
		if (dValue != null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sRet = " to_date('" +  df.format(dValue) + "','yyyy-MM-dd') ";
		} else if (sValue != null){
			sRet = sValue;
		} else {
			sRet = ValueWhenEmpty;
		}
		return sRet;
	}
	
	
	@Override
	public Date Value() {
		return dValue;
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#ValuePlain(java.lang.String)
	 */
	@Override
	public Date Value(Object ValueWhenEmpty) {
		if (dValue == null){
			if (ValueWhenEmpty instanceof Date){
				return (Date)ValueWhenEmpty;
			}
		}
		return dValue;
	}
	
	
	/**
	 * gibt das Datum im ISO-Format zurück yyyy-MM-dd
	 * @author manfred
	 * @date   16.12.2013
	 * @return
	 */
	public String Value_ISO(){
		String sRet = null;
		if (dValue != null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sRet = df.format(dValue) ;
		}
		return sRet;
	}


	public boolean is_value_is_sql_plain() {
		return _value_is_sql_plain;
	}

	public void set_value_is_sql_plain(boolean _value_is_sql_plain) {
		this._value_is_sql_plain = _value_is_sql_plain;
	}

	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject#CopyFrom(rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject)
	 */
	@Override
	public void CopyFrom(DataObject o) {
		if (o instanceof DODate){
			this._value_is_sql_plain = ((DODate)o).is_value_is_sql_plain();
			if (this._value_is_sql_plain){
				this.setValue(o.ValuePlain(),this._value_is_sql_plain);
			} else {
				this.setValue(((DODate) o).Value());
			}
		}
	}


	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#SetPreparedStatementValue(java.sql.PreparedStatement, int)
	 */
//	@Override
//	public int SetPreparedStatementValue(MyDBStatementPrepared stmt, int idx) {
//		try {
//				java.sql.Timestamp dt = null;
//				if (this.dValue == null){
//					stmt.getPreparedStatement().setNull(idx, java.sql.Types.DATE);
//				} else {
//					dt = new java.sql.Timestamp(this.dValue.getTime());
//					stmt.getPreparedStatement().setTimestamp(idx, dt );
//				}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return idx+1;
//	}
}
