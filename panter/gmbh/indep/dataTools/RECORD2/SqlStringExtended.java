/**
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBResultSet_Prepared;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class SqlStringExtended implements Cloneable {

	//wenn diese hashmap leer ist, dann gilt fuer die abfrage kein prepared- sonder ein normales statement
	private Vector<ParamDataObject> 	valuesList = new Vector<>();
	private String 						sqlString = null;

	public SqlStringExtended(String sql) {
		super();
		this.sqlString=sql;
	}

	public Vector<ParamDataObject> getValuesList() {
		return valuesList;
	}

	/**
	 * Fügt einen Parameter in die Parameter-Liste ein
	 * @author manfred
	 * @date 02.04.2019
	 *
	 * @param param
	 * @return
	 */
	public SqlStringExtended _addParameter(ParamDataObject param){
		this.valuesList.add(param);
		return this;
	}
	
	
	public SqlStringExtended _addParameters(Collection<ParamDataObject> params) {
		this.valuesList.addAll(params);
		return this;
	}
	
	public String getSqlString() {
		return sqlString;
	}

	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}
	
	public boolean isForPreparedStatement() {
		return this.valuesList.size()>0;
	}
	
	
	/**
	 * 
	 * @param oDB
	 * @return when this is has value-list, a {@link MyDBResultSet_Prepared}  else a {@link MyDBResultSet}
	 */
	public MyDBResultSet generateResultset(MyDBToolBox oDB) throws myException {
		if (S.isEmpty(this.sqlString)) {
			throw new myException("Error ! Empty Statement-Text");
		}
		if (this.isForPreparedStatement()) {
			return oDB.OpenResultSet_Prepared(this.sqlString,this.getValuesList());
		} else {
			return oDB.OpenResultSet(this.sqlString);
		}
	}
	
	
	
	/**
	 * 
	 * @param oDB
	 * @return everytime MyDBResultSet_Prepared, even with a empty list a {@link MyDBResultSet_Prepared}
	 */
	public MyDBResultSet_Prepared generateResultsetPrepared(MyDBToolBox oDB) throws myException {
		if (S.isEmpty(this.sqlString)) {
			throw new myException("Error ! Empty Statement-Text");
		}
		return oDB.OpenResultSet_Prepared(this.sqlString,this.getValuesList());
	}
	
	

	/**
	 * 
	 * @param oDB
	 * @return everytime MyDBStatementPrepared, even with a empty list a {@link MyDBResultSet_Prepared}
	 * @throws myException
	 */
	public MyDBStatementPrepared generatePreparedStatement(MyDBToolBox oDB) throws myException {
		MyDBStatementPrepared stmtPrep = new MyDBStatementPrepared(oDB.get_MyConnection(), sqlString);
		
		int idx=1;
		for (ParamDataObject d_ob: this.valuesList) {
			d_ob.SetPreparedStatementValue(stmtPrep, idx++);
		}
		return stmtPrep;
	}
	
	
	
	

	@Override
	protected SqlStringExtended clone() throws CloneNotSupportedException {
		SqlStringExtended clone = new SqlStringExtended(this.sqlString);
		clone.getValuesList().addAll(this.valuesList);
		return clone;
	}
	


	
}
