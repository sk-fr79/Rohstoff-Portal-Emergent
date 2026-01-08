package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.indep.exceptions.myException;

public class DODate extends DataObject {

	private boolean _value_is_sql_plain = false;
	
	
	public DODate(String name, Date Value) {
		// TODO Auto-generated constructor stub
		this.dValue = Value;
	}
	
	public DODate(String name){
		super(name);
	}
	
	public DODate (Date value){
		this.dValue = value;
	}
	
	

	public DODate(String value, String Format) throws myException{
		this.setValue(value,Format);
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
	
	
	
	@Override
	public String Value() {
		String sRet = null;
		if (dValue != null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sRet = " to_date('" +  df.format(dValue) + "','yyyy-MM-dd') ";
		} else if (sValue != null){
			sRet = sValue;
		}
		return sRet;
	}

	@Override
	/***
	 * Entspricht Value()
	 */
	public String ValuePlain() {
		
		return Value();
	}
	
	/**
	 * gibt das Objekt als Date zurück
	 * @author manfred
	 * @date 10.11.2017
	 *
	 * @return
	 */
	public Date ValueDate(){
		return this.dValue;
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
				this.setValue(((DODate) o).ValueDate());
			}
		}
	}
	
}
