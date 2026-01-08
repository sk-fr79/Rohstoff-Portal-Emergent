package panter.gmbh.indep.dataTools;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;


public interface IF_RecordList<T> {
	
	public Vector<String> get_vKeyValues();
	public T get(String cIndexKeyOfRecord) throws myException;
	public T get(int iNumberInVector) throws myException;

	
	/**
	 * 
	 * @return sorted vektor of records, sortet by natural order of creation
	 * @throws myException
	 */
	public default Vector<T>  get_v_records() throws myException {
		
		Vector<T>  v = new Vector<>();
		
		for (String k: this.get_vKeyValues()) {
			v.add(this.get(k));
		}
		
		return v;
	}
	
	
	public default Vector<T>  get_v_records(IF_RecordListFilter<T> filter) throws myException {
		Vector<T>  v = new Vector<>();
		
		for (String k: this.get_vKeyValues()) {
			if (filter.isInFilter(this.get(k))) {
				v.add(this.get(k));
			}
		}
		
		return v;
	}
	

}
