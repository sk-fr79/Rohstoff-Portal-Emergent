package panter.gmbh.indep.enumtools;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

public interface IF_Enum4db<T extends Enum<T>> extends IF_enum_4_db {
	
	public T[]    	get_Values();
	
	public VEK<String>  getHelpTextVek();
	
	
	/**
	 * ..[0]=usertext
	 * ..[1]=dbVal
	 */
	public default String[][] get_dd_Array(boolean emptyPairInFront) throws myException  {
		String[][] ddarray = new String[this.get_Values().length][2];
		int i=0;
		for (T e: this.get_Values()) {
			ddarray[i][0]=new MyE2_String(((IF_enum_4_db)e).user_text()).CTrans();
			ddarray[i][1]=((IF_enum_4_db)e).db_val();
			i++;
		}
		if (emptyPairInFront) {
			ddarray= bibARR.add_emtpy_db_value_inFront(ddarray);
		} 
		return ddarray;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 27.03.2020
	 *
	 * @param emptyPairInFront
	 * @return s hashmap with readablename as key, dbvalue as value
	 * @throws myException
	 */
	public default HMAP<String,String> getHMAP(boolean emptyPairInFront) throws myException  {
		HMAP<String, String>  map = new HMAP<>();

		if (emptyPairInFront) {
			map.put("-", "");
		} 

		for (T e: this.get_Values()) {
			map._put( ((IF_enum_4_db)e).user_text(),  ((IF_enum_4_db)e).db_val());
		}
		return map;
	}

	
	
	public default HashMap<T,String> getHmTextEnum() {
		HashMap<T,String> hm = new HashMap<T,String>();
		
		for (T t:  this.get_Values()) {
			hm.put(t, ((IF_enum_4_db)t).db_val());
		}
		return hm;
	}


	/**
	 * 
	 * @param dbVal
	 * @return Enum korresponding to dbVal
	 */
	public default T getEnum(String dbVal) {
		T ret = null;
		
		for (T t: this.getHmTextEnum().keySet()) {
			if ( ((IF_enum_4_db) t).db_val().equals(dbVal)) {
				ret = t;
			}
		}
		return ret;
	}
	
	

	/**
	 * 
	 * @param dbVal
	 * @return Enum korresponding usetText
	 */
	public default T getEnumFromText(String userText) {
		T ret = null;
		
		for (T t: this.getHmTextEnum().keySet()) {
			if ( ((IF_enum_4_db) t).user_text().equals(userText)) {
				ret = t;
			}
		}
		return ret;
	}
	
	

	
	
}
