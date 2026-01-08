/**
 * panter.gmbh.indep.dataTools
 * @author martin
 * @date 29.10.2020
 * 
 */
package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;

import panter.gmbh.indep.dataTools.RECORD2.FieldFalseTypeException;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 29.10.2020
 *
 */
public class DBMap extends HMAP<String, Object> {


	public DBMap() {
		super();
	}

	public String getString(String field) {
		Object o = this.get(field);
		if (o!=null) {
			return o.toString();
		}
		return null;
	}
	
	public Long getLong(String field) throws myException {
		Object o = this.get(field);
		if (o!=null) {
			if (o instanceof Long) {
				return (Long)o;
			} else if (o instanceof BigDecimal) {
				return ((BigDecimal)o).longValue();
			} else if (o instanceof Integer) {
				return ((Integer)o).longValue();
			} else {
				throw new FieldFalseTypeException("Field "+field+" is not type of Number (1) !");
			}
		}
		return null;
	}
	
	
	public BigDecimal getBigDecimal(String field) throws myException {
		Object o = this.get(field);
		if (o!=null) {
			if (o instanceof BigDecimal) {
				return (BigDecimal)o;
			} else if (o instanceof Long) {
				return new BigDecimal(((Long)o));
			} else if (o instanceof Integer) {
				return new BigDecimal(((Integer)o));
			} else {
				throw new FieldFalseTypeException("Field "+field+" is not type of Number (2) !");
			}
		}
		return null;
	}
	
	public String getString(IF_Field field) {
		return getString(field.fn());
	}
	
	public Long getLong(IF_Field field) throws myException {
		return getLong(field.fn());
	}
	
	
	public BigDecimal getBigDecimal(IF_Field field) throws myException {
		return getBigDecimal(field.fn());
	}
	
	
	
}
