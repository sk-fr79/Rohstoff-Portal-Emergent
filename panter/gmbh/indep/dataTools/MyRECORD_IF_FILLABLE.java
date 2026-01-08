package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public interface MyRECORD_IF_FILLABLE
{
	//2015-02-02: weitere interface-methoden
	public MyE2_MessageVector  		set_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException;
	public MyE2_MessageVector  		check_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException;
	public MyE2_MessageVector 		set_NewValueForDatabase(String FIELDNAME, long lNewValueFormated) throws myException;
	public MyE2_MessageVector 		set_NewValueForDatabase(String FIELDNAME, double dNewValueFormated) throws myException;
	public MyE2_MessageVector 		set_NewValueForDatabase(String FIELDNAME, BigDecimal bdNewValueFormated) throws myException;
	public MyE2_MessageVector 		set_NewValueForDatabase(String FIELDNAME, GregorianCalendar calNewValueFormated) throws myException;
	public MySqlStatementBuilder 	get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException;
	public String        			get_TABLENAME() throws myException;
	public String   				get_PRIMARY_KEY_NAME() throws myException;
	
	public boolean     				get_bHasSomething_to_save() throws myException;
	
	/**
	 * 
	 * @return hashmap mit den uebergabewerten aus den eingabefeldern
	 * @throws myException
	 */
	public HashMap<String,String>   get_hm_InputValuesFormated() throws myException;
	
	public void RESET_ALL_NEWVALUES() throws myException;
	
	public HashMap<String, String> get_hm_Field_Value_pairs_from_outside();
	
	
	
	/*
	 * 2016-05-11: allgemeine setNewValue-methode 
	 */
	public default MyRECORD_IF_FILLABLE nv(IF_Field field, Object value, MyE2_MessageVector mv)  throws myException {
		MyMetaFieldDEF fd = field.generate_MetaFieldDef();
		
		if (this instanceof MyRECORD)  {
			if (!(((MyRECORD)this).containsKey(field.fn()))) {
				throw new myException(this,"nv(): Field: "+field.fn()+" is not in the MyRECORD "+S.NN(((MyRECORD)this).get_TABLENAME()));
			}
		} else if (this instanceof MyRECORD_NEW) {
		    if (!(((MyRECORD_NEW)this).containsKey(field.fn()))) {
			  throw new myException(this,"nv(): Field: "+field.fn()+" is not in the MyRECORD_NEW "+S.NN(((MyRECORD)this).get_TABLENAME()));
		    }
		} else if (this instanceof Rec20) {
			Rec20 r20 = (Rec20)this;
			if (!r20.keySet().contains(field)) {
				throw new myException(this,"nv(): Field: "+field.fn()+" is not in the Rec20 "+S.NN(((MyRECORD)this).get_TABLENAME()));
			}
		}
	
		
		String inputText = null;
		
		if (value == null) {
			inputText = "";
		} else if (value instanceof String) {
			inputText = (String) value;
		} else if (value instanceof Integer) {
			inputText = ""+((Integer)value).intValue();
		} else if (value instanceof Long) {
			inputText = ""+((Long)value).longValue();
		} else if (value instanceof Double) {
			inputText = MyNumberFormater.formatDez(((Double)value).doubleValue(), fd.get_FieldDecimals(),false);
		} else if (value instanceof BigDecimal) {
			inputText = MyNumberFormater.formatDez(((BigDecimal)value), fd.get_FieldDecimals(),true);	
		} else if (value instanceof GregorianCalendar) {
			inputText = myDateHelper.FormatDateNormal(((GregorianCalendar)value).getTime());	
		} else {
			throw new myException(this,"newVal(): Only Datatypes  String,Integer,Long,Double,BigDecimal,GregorianCalendar are allowed !");
		}
		
		mv.add_MESSAGE(this.set_NewValueForDatabase(field.fn(), inputText));
		
		return this;
	}
	
	

	
}

