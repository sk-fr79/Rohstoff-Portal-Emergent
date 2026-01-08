package panter.gmbh.indep.dataTools.RECORD2;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;

public class Rec20_field {

	protected IF_Field        field = null;     //MUSS immer vorhanden sein
	protected _TAB            tab = null;       //ist immer vorhanden, weil field immer vorhanden ist
	
	protected MyMetaFieldDEF  metadef = null;   //MUSS immer vorhanden sein !
	
	protected MyResultValue   result = null;
	protected String          new_val_formated_string = null;
	protected Object          new_val_raw = null;          //20180313: neues feld fuer raw-uebergaben	
	
	
	protected boolean    		is_newvalue_set = false;
	
	
	/**
	 * 
	 */
	public Rec20_field(IF_Field p_field) {
		super();
		this.field = p_field;
		this.tab = this.field._t();
		this.metadef = this.field.generate_MetaFieldDef();
	}
	
	
	public Rec20_field _setResult(MyResultValue  result_val) throws myException {
		this.result = result_val;
		
		//2017-10-13: weniger genauer vergleich zwischen den metadefs aus den IF_Field-konstanten und den abgefragten, da sonst 
		// schon eine kleine datenbankaenderung (z.N. laenge oder null zu not null) zu fehlern fuehren wuerde gegen die IF_Field-enums 
		//alt
//		if (result_val != null && !this.metadef.is_equal_to(this.result.get_MetaFieldDef(), false)) {
//			throw new myException(this, "_setResult has not compatible MyMetaFieldDEF-object !");
//		}
		if (result_val != null && !this.metadef.is_equal_type(this.result.get_MetaFieldDef(), false)) {
			throw new myException(this, "_setResult has not compatible MyMetaFieldDEF-object !");
		}
		
		return this;
	}
	
	/**
	 * 
	 * @param formated_new_val
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Rec20_field _setNewVal(String formated_new_val, MyE2_MessageVector mv) throws myException {
		MyE2_MessageVector mv_intern = this.metadef.check_NewValueForDatabase(formated_new_val);
		mv.add_MESSAGE(mv_intern);
		if (mv_intern.get_bIsOK()) {
			this.new_val_formated_string = formated_new_val;
			this.new_val_raw = null;
			this.is_newvalue_set = true;
		}
		
		return this;
	}

	
	
	/**
	 * 
	 * @param formated_new_val
	 * @param raw_val
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Rec20_field _setNewVal(String formated_new_val, Object raw_val, MyE2_MessageVector mv) throws myException {
		MyE2_MessageVector mv_intern = this.metadef.check_NewValueForDatabase(formated_new_val);
		mv.add_MESSAGE(mv_intern);
		if (mv_intern.get_bIsOK()) {
			this.new_val_formated_string = formated_new_val;
			this.new_val_raw = raw_val;
			this.is_newvalue_set = true;
		}
		
		return this;
	}

	
	
	
	public Rec20_field _checkNewVal(String formated_new_val, MyE2_MessageVector mv) throws myException {
		MyE2_MessageVector mv_intern = this.metadef.check_NewValueForDatabase(formated_new_val);
		mv.add_MESSAGE(mv_intern);
		return this;
	}

	public boolean checkNewVal(String formated_new_val) throws myException {
		MyE2_MessageVector mv_intern = this.metadef.check_NewValueForDatabase(formated_new_val);
		return mv_intern.get_bIsOK();
	}


	public IF_Field get_field() {
		return field;
	}


	public _TAB get_tab() {
		return tab;
	}


	public MyMetaFieldDEF get_metadef() {
		return metadef;
	}


	public MyResultValue get_result_val() {
		return result;
	}


	/**
	 * 
	 * @return new_value if it is set, otherwise null
	 */
	public String get_new_val_formated_string() {
		return new_val_formated_string;
	}
	
	
	
	public boolean is_newValPresent() {
		return S.isFull(this.get_new_val_formated_string());
	}
	
	
	public boolean is_new() {
		return this.result==null;
	}
	
	
	public boolean is_existing() {
		return !this.is_new();
	}
	
	

	/**
	 * 
	 * @return true, when the methode @_setNewVal was set
	 */
	public boolean is_new_value_set() {
		return is_newvalue_set;
	}
	
	
	
	public Rec20_field _resetNewVal() {
		this.new_val_formated_string=null;
		this.new_val_raw = null;
		this.is_newvalue_set=false;
		return this;
	}
	
	
	/**
	 * 
	 * @return last formated resultvalue from database, when newRecord then null
	 * @throws myException
	 */
	public String get_database_value_f() throws myException {
		if (this.result!=null) { 
			return this.result.get_FieldValueFormated();
		} else {
			return null;
		}
	}
	
	
	
	/**
	 * 
	 * @return last unformated resultvalue from database, when newRecord then null
	 * @throws myException
	 */
	public String get_database_value_uf() throws myException {
		if (this.result!=null) { 
			return this.result.get_FieldValueUnformated();
		} else {
			return null;
		}
	}

	
	
	
	
	/**
	 * 
	 * @return formated db_value or the last new_val, which is setted to this field (when empty, then null)
	 * @throws myException
	 */
	public String get_last_value_f() throws myException {
		if (this.is_newvalue_set) {
			return this.new_val_formated_string;
		} else {
			return this.get_database_value_f();
		}
	}
	
	
	/**
	 * unformated db_value or the last new_val, which is set to this field (when empty, then null)
	 * @author manfred
	 * @date 08.09.2020
	 *
	 * @return
	 * @throws myException
	 */
	public String get_last_value_uf() throws myException{
		if (this.is_newvalue_set) {
			return this.new_val_formated_string ;
		} else {
			return this.get_database_value_uf();
		}
	}
	

	
	/**
	 * @param mv  (can be null, when null, in case of not fitting it throws myException)
	 * @return String, which can be filled into database-statement, when not fitting, then Exception
	 * @throws myException
	 */
	public String get_value4sql_statements(MyE2_MessageVector mv) throws myException {
		
		try {
			return this.metadef.get_cStringForDataBase(get_last_value_f(),true,false);
		} catch (myExceptionDataValueNotFittingToField e) {
			if (mv == null) {
				throw e;
			} else {
				//20180307: fehlerfix: mv wurde nicht beruecksichtigt beim speichern
				//alt: bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feldzuweisungsfehler: ").ut(S.NN(this.get_last_value_f(),"NULL")).t("Passt nicht zum Feld:").ut(this.field.tnfn())));
				//neu:
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feldzuweisungsfehler: ").ut(S.NN(this.get_last_value_f(),"NULL")).t(" ... passt nicht zum Feld:").ut(this.field.tnfn())));
				//
				
				return "NULL";
			}
		}  catch (myException e) {
			throw e;
		}
	}
	

	
	
	public IF_BasicTypeWrapper get_object_dbVal() throws myException {
		return this.metadef.get_object(this.get_database_value_f());
	}
	
	
	public IF_BasicTypeWrapper get_object_lastVal() throws myException {
		return this.metadef.get_object(this.get_last_value_f());
	}

	
	
	
	
	public MyLong get_MyLong_dbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyLong) {
			return (MyLong)bo;
		} else {
			return  null;
		}
	}
	
	public MyLong get_MyLong_lastVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyLong) {
			return (MyLong)bo;
		} else {
			return  null;
		}
	}
	
	
	public MyDate get_MyDate_dbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyDate) {
			return (MyDate)bo;
		} else {
			return  null;
		}
	}
	
	public MyDate get_MyDate_lastVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyDate) {
			return (MyDate)bo;
		} else {
			return  null;
		}
	}

	
	
	
	public MyBigDecimal get_MyBigDecimal_dbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyBigDecimal) {
			return (MyBigDecimal)bo;
		} else if (bo != null && bo instanceof MyLong){
			MyLong l = (MyLong) bo;

			return new MyBigDecimal(l.get_lValue());
		} else {
			return  null;
		}
	}
	
	public MyBigDecimal get_MyBigDecimal_lastVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyBigDecimal) {
			return (MyBigDecimal)bo;
		} else {
			return  null;
		}
	}


	public Rec20_field get_copy() {
		Rec20_field copy = new Rec20_field(this.field);
		
		copy.metadef = this.metadef;
		
		copy.result = this.result==null?null:this.result.get_copy();
		copy.new_val_formated_string = this.new_val_formated_string;
		copy.new_val_raw = this.new_val_raw;
		copy.is_newvalue_set = this.is_newvalue_set;

		return copy;
	}

	

	
	/**
	 * 2017-01-03: getter-varianten mit null-ersatzwert
	 */
	public MyLong get_MyLong_dbVal(MyLong whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyLong && bo!=null) {
			return (MyLong)bo;
		} else {
			return  whenNull;
		}
	}
	
	public MyLong get_MyLong_lastVal(MyLong whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyLong && bo!=null) {
			return (MyLong)bo;
		} else {
			return  whenNull;
		}
	}
	
	
	public MyDate get_MyDate_dbVal(MyDate whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyDate && bo!=null) {
			return (MyDate)bo;
		} else {
			return  whenNull;
		}
	}
	
	public MyDate get_MyDate_lastVal(MyDate whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyDate && bo!=null) {
			return (MyDate)bo;
		} else {
			return  whenNull;
		}
	}

	
	
	
	public MyBigDecimal get_MyBigDecimal_dbVal(MyBigDecimal whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyBigDecimal && bo!=null) {
			return (MyBigDecimal)bo;
		} else if (bo instanceof MyLong  && bo!=null){
			MyLong l = (MyLong) bo;

			return new MyBigDecimal(l.get_lValue());
		} else {
			return  whenNull;
		}
	}
	
	public MyBigDecimal get_MyBigDecimal_lastVal(MyBigDecimal whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyBigDecimal && bo!=null) {
			return (MyBigDecimal)bo;
		} else {
			return  whenNull;
		}
	}
	
	
	
	public MyLong get_MyLong_dbVal(Long whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyLong && bo!=null) {
			return (MyLong)bo;
		} else {
			return new MyLong(whenNull);
		}
	}
	
	public MyLong get_MyLong_lastVal(Long whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyLong && bo!=null) {
			return (MyLong)bo;
		} else {
			return  new MyLong(whenNull);
		}
	}
	
	
	public MyDate get_MyDate_dbVal(GregorianCalendar whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyDate && bo!=null) {
			return (MyDate)bo;
		} else {
			return new MyDate(whenNull);
		}
	}
	
	public MyDate get_MyDate_lastVal(GregorianCalendar whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyDate && bo!=null) {
			return (MyDate)bo;
		} else {
			return  new MyDate(whenNull);
		}
	}

	
	
	
	public MyBigDecimal get_MyBigDecimal_dbVal(BigDecimal whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyBigDecimal && bo!=null) {
			return (MyBigDecimal)bo;
		} else if (bo instanceof MyLong && bo!=null){
			MyLong l = (MyLong) bo;

			return new MyBigDecimal(l.get_lValue());
		} else {
			return  new MyBigDecimal(whenNull);
		}
	}
	
	public MyBigDecimal get_MyBigDecimal_lastVal(BigDecimal whenNull)  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_lastVal();
		if (bo instanceof MyBigDecimal && bo!=null) {
			return (MyBigDecimal)bo;
		} else {
			return  new MyBigDecimal(whenNull);
		}
	}

	//ENDE 2017-01-03: getter-varianten mit null-ersatzwert
	
	
	
	
	/**
	 * 2017-01-13: neue resultvalues als raw-werte (wenn new object oder feld nicht belegt, dann null)
	 */
	public Object get_raw_resultValue() {
		if (this.get_result_val()!=null) {
			return this.get_result_val().get_oNativeDataObject();
		} else {
			return null;
		}
	}

	
	/**
	 * 2017-01-13: neue resultvalues als raw-werte (wenn new object oder feld nicht belegt, dann null)
	 */
	public Object getRawResultValueCorrected() {
		if (this.get_result_val()!=null) {
			return this.get_result_val().getNativeDateObjectCorrected();
		} else {
			return null;
		}
	}

	
	/**
	 * 2017-01-13: neue resultvalues als raw-werte (wenn new object oder feld nicht belegt, dann null)
	 */
	public BigDecimal get_raw_resultValue_bigDecimal() {
		if (this.get_result_val()!=null) {
			if (this.get_result_val().get_oNativeDataObject() instanceof BigDecimal) {
				return (BigDecimal)this.get_result_val().get_oNativeDataObject();
			}
		}
		return (BigDecimal)null;
	}

	
	/**
	 * 2017-01-13: neue resultvalues als raw-werte (wenn new object oder feld nicht belegt, dann null)
	 */
	public Timestamp get_raw_resultValue_timeStamp() {
		if (this.get_result_val()!=null) {
			if (this.get_result_val().get_oNativeDataObject() instanceof Timestamp) {
				return (Timestamp)this.get_result_val().get_oNativeDataObject();
			}
		}
		return (Timestamp)null;
	}

	 
	
	/**
	 * Rückgabewert als Long, wenn keine dezimalstellen in der DB definiert sind 
	 * @author manfred
	 * @date 22.02.2018
	 *
	 * @return
	 */
	public Long get_raw_resultValue_Long() throws myException {
		if (this.get_result_val()!=null) {
			if (this.get_result_val().get_MetaFieldDef().get_bIsNumberTypeWithOutDecimals() ) {
				BigDecimal bd = (BigDecimal)this.get_result_val().get_oNativeDataObject();
				
				return (bd != null ? new Long(bd.longValue()) : (Long)null );
			}  else {
				throw new myException("Datenfeld ist kein Long-Feld: "  + this.get_result_val().get_MetaFieldDef().get_FieldName());
			}
		}
		return (Long)null;
	}


	/**
	 * 2018-03-13: new-val-raw, falls vorhanden
	 * @return
	 */
	public Object getNewValRaw() {
		return new_val_raw;
	}

	
	/**
	 * 2018-03-13: 
	 * @return s correct paramDataobject for this row 
	 */
	public ParamDataObject getParamDataObject() throws myException {
		if (this.is_newvalue_set) {
			Object ob = this.new_val_raw;
			if (ob==null) {
				//dann erzeugen
				ob = this.field.generate_MetaFieldDef().getRaw(this.get_new_val_formated_string());
			}
			return this.get_metadef().getParamDataObject(ob);
		} else {
			return this.get_metadef().getParamDataObject(this.getRawResultValueCorrected());
		}
	}

	
	

	
	
}
