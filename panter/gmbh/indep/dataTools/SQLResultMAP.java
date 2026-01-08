package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;



/**
 * speichert die formated-values einer resultsets, basierend auf einer 
 * SQLFieldMAP. Haelt sowohl formatierte als auch unformatierte werte in jeweils einem objekt vom type MyResultValue
 * WICHTIG ! DIE MyResultValue in der MAP speichern keine NULL, sondern ""
 */
public class SQLResultMAP extends HashMap<String,MyResultValue>
{
	private SQLFieldMAP 	oSQLFieldMAP = 				null;
	
	private String 			cFormatedROW_ID	= 			null;
	private String 			cUnformatedRowID = 			null;
	

	public SQLResultMAP(SQLFieldMAP osqlfieldMAP , ResultSet oRs) throws myException
	{
		super();
		this.oSQLFieldMAP = osqlfieldMAP;
		SQLField	o_PK_MainTable = this.oSQLFieldMAP.get_oSQLFieldPKMainTable();
		
		Vector<String> vFieldLabels = this.oSQLFieldMAP.get_vFieldLabels();
		
		for (int i=0;i<vFieldLabels.size();i++)
		{
			SQLField oField = (SQLField)this.oSQLFieldMAP.get(vFieldLabels.get(i));
			if (oField == null)
				throw new myException("SQLResultMAP:Constructor: Cannot find field with Label "+(String)vFieldLabels.get(i));
			
			MyResultValue oResultValue = oField.get_oResultValue(oRs);
			this.put(oField.get_cFieldLabel(),oResultValue);
			
			if (oField == o_PK_MainTable)
			{
				this.cFormatedROW_ID = oResultValue.get_FieldValueFormated();
				this.cUnformatedRowID =oResultValue.get_FieldValueUnformated();
			}
		}
	}
	
	
	public String get_FormatedValue(String cFieldLabel) throws myException
	{
		if (!this.containsKey(cFieldLabel))
			throw new myException("SQLResultMAP:get_FormatedValue: Cannot find field with Label "+cFieldLabel);
		
		return ((MyResultValue)this.get(cFieldLabel)).get_FieldValueFormated() ;
	}
	

	public String get_UnFormatedValue(String cFieldLabel) throws myException
	{
		if (!this.containsKey(cFieldLabel))
			throw new myException("SQLResultMAP:get_UnFormatedValue: Cannot find field with Label "+cFieldLabel);
		
		return ((MyResultValue)this.get(cFieldLabel)).get_FieldValueUnformated() ;
	}

	
	
	//----------------------------------- neue methoden
	public String get_FormatedValue(String cFieldLabel, String cValueWhenNull_or_empty) throws myException
	{
		String cRueck = this.get_FormatedValue(cFieldLabel);
		
		return S.isEmpty(cRueck)?cValueWhenNull_or_empty:cRueck ;
	}
	

	public String get_UnFormatedValue(String cFieldLabel, String cValueWhenNull_or_empty) throws myException
	{
		String cRueck = this.get_UnFormatedValue(cFieldLabel);
		
		return S.isEmpty(cRueck)?cValueWhenNull_or_empty:cRueck ;
	}
	
	
	
	
	//

	public MyResultValue get_oResultField(String cFieldLabel) throws myException
	{
		if (!this.containsKey(cFieldLabel))
			throw new myException("SQLResultMAP:get_oResultField: Cannot find field with Label "+cFieldLabel);
		
		return ((MyResultValue)this.get(cFieldLabel));
	}

	
	public String get_cFormatedROW_ID()			{	return this.cFormatedROW_ID;	}
	public String get_cUNFormatedROW_ID()			{	return this.cUnformatedRowID;	}

	
	/**
	 * @param cTableName
	 * @return the key-value of the tablename (formated)
	 * @throws myException
	 */
	public String get_cFormatedID_FromTable(String cTableName) throws myException
	{
		if (this.oSQLFieldMAP.get_hmPrimaryKeys().get(cTableName) == null)
			throw new myException("SQLResultMAP:get_cFormatedID_FromTable:table has no primary-key-field in the fieldmap: "+cTableName);
		
		SQLFieldForPrimaryKey oKey = (SQLFieldForPrimaryKey)this.oSQLFieldMAP.get_hmPrimaryKeys().get(cTableName);
		String cRueck = this.get_FormatedValue(oKey.get_cFieldLabel());
		
		return cRueck;
	}


	public SQLFieldMAP get_oSQLFieldMAP()
	{
		return oSQLFieldMAP;
	}
	
	
	public Double get_DActualDBValue(String cHASH_KEY,boolean b_0_WhenEmpty) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("SQLResultMAP:get_DActualMaskFieldValue: empty HASHKEY is not allowed !!");

		if (!this.containsKey(cHASH_KEY))
			throw new myException("SQLResultMAP:get_oResultField: Cannot find field with Label "+cHASH_KEY);
	
		
		MyDouble oDOU = new MyDouble(this.get_FormatedValue(cHASH_KEY));
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else if (oDOU.get_cErrorCODE().equals(MyDouble.ERROR_NULLSTRING) && b_0_WhenEmpty)
		{
			return new Double(0);
		}
		else
		{
			throw new myException(this,"get_DActualDBValue:"+oDOU.get_cErrorCODE()+" --> "+cHASH_KEY);
		}
		
	}


	public Long get_LActualDBValue(String cHASH_KEY,boolean b_0_WhenEmpty) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("SQLResultMAP:get_LActualDBValue: empty HASHKEY is not allowed !!");

		if (!this.containsKey(cHASH_KEY))
			throw new myException("SQLResultMAP:get_oResultField: Cannot find field with Label "+cHASH_KEY);
	
		
		MyLong oLONG = new MyLong(this.get_FormatedValue(cHASH_KEY));
		
		if (oLONG.get_cErrorCODE().equals(MyLong.ALL_OK))
		{
			return oLONG.get_oLong();
		}
		else if (oLONG.get_cErrorCODE().equals(MyLong.ERROR_NULLSTRING) && b_0_WhenEmpty)
		{
			return new Long(0);
		}
		else
		{
			throw new myException(this,"get_DActualDBValue:"+oLONG.get_cErrorCODE()+" --> "+cHASH_KEY);
		}
		
	}

	
	public boolean  get_booleanActualValue(String cHASH_KEY) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("SQLResultMAP: get_booleanActualValue: empty HASHKEY is not allowed !!");

		if (!this.containsKey(cHASH_KEY))
			throw new myException("SQLResultMAP: get_booleanActualValue: Cannot find field with Label "+cHASH_KEY);

		return S.NN(this.get(cHASH_KEY).get_FieldValueUnformated()).equals("Y");
		
		
	}
	
	
	
	public BigDecimal get_bdActualValue(String cHASH_KEY, boolean b_0_WhenEmpty) throws myException
	{
		
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("SQLResultMAP:get_bdActualValue: empty HASHKEY is not allowed !!");

		if (!this.containsKey(cHASH_KEY))
			throw new myException("SQLResultMAP:get_bdActualValue: Cannot find field with Label "+cHASH_KEY);

		
		BigDecimal bdRueck = this.get(cHASH_KEY).getBigDecimalValue();
		
		if (bdRueck==null)
		{
			if (b_0_WhenEmpty)
			{
				return BigDecimal.ZERO;
			}
		}
		else
		{
			return bdRueck;
		}
		return null;
	}
	
	
	//
	
	/**
	 * 2018-06-08: neuer getter 
	 * @param String cHASH_KEY
	 * @return Long-Val of field or null when field is null or not fitting to long-type, throws exception when field not found
	 * @throws myException
	 */
	public Long getLongDBValue(String cHASH_KEY) throws myException {
		if ( bibALL.isEmpty(cHASH_KEY)) {
			throw new myException("SQLResultMAP:get_LActualDBValue: empty HASHKEY is not allowed !!");
		}

		if (!this.containsKey(cHASH_KEY)) {
			throw new myException("SQLResultMAP:get_oResultField: Cannot find field with Label "+cHASH_KEY);
		}
		
		MyLong _long = new MyLong(this.get_FormatedValue(cHASH_KEY));
		
		if (_long.isOK()) {
			return _long.get_oLong();
		} else {
			return null;
		}
	}

	/**
	 * 2018-06-08: neuer getter 
	 * @param IF_Field f
	 * @return Long-Val of field or null when field is null or not fitting to long-type, throws exception when field not found
	 * @throws myException
	 */
	public Long getLongDBValue(IF_Field f) throws myException {
		return this.getLongDBValue(f.fn());
	}

	
	

	/**
	 * 
	 * @author martin
	 * @date 04.12.2018
	 *
	 * @return Long-value of rowId
	 */
	public Long getLongId() {
		try {
			return new Long(this.cUnformatedRowID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

	/**
	 * 2018-08-28: neuer getter
	 * Returns unformated-String of dataContent, can return null
	 * 
	 * @param f
	 * @return
	 * @throws myException when field not present
	 */
	public String getUfs(IF_Field f) throws myException {
		if (this.containsKey(f.fn())) {
			return this.get(f.fn()).get_FieldValueUnformated();
		} else {
			throw new myException("Field: "+f.fn()+" is not in this SQLResultMap!");
		}
	}
	
}
