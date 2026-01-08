package panter.gmbh.indep.enumtools;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;


@SuppressWarnings("rawtypes")
public class bibENUM {
	
	public static String[][] dd_array(IF_enum_4_db[] enum_list, boolean bEmtpyInFront) throws myException {
		String[][] ddarray = new String[enum_list.length][2];
		int i=0;
		for (IF_enum_4_db stat: enum_list) {
			ddarray[i][0]=new MyE2_String(stat.user_text()).CTrans();
			ddarray[i][1]=stat.db_val();
			i++;
		}
		if (bEmtpyInFront) {
			ddarray= bibARR.add_emtpy_db_value_inFront(ddarray);
		} 
		
		return ddarray;
	}
	
	public static String[][] dd_array(IF_enumForDb[] enum_list, boolean bEmtpyInFront) throws myException {
		String[][] ddarray = new String[enum_list.length][2];
		int i=0;
		for (IF_enumForDb stat: enum_list) {
			ddarray[i][0]=new MyE2_String(stat.userText()).CTrans();
			ddarray[i][1]=stat.dbVal();
			i++;
		}
		if (bEmtpyInFront) {
			ddarray= bibARR.add_emtpy_db_value_inFront(ddarray);
		} 
		
		return ddarray;
	}

	
	/**
	 * 
	 * @param enumList
	 * @param bAddEmtpyKey
	 * @return s hashmap with dbVal as key and userText as value
	 * @throws myException
	 */
	public static HashMap<String, String> hmDbValUserText(IF_enumForDb[] enumList, boolean bAddEmtpyKey) throws myException {
		HashMap<String, String>  hm = new HashMap<String, String>();
		
		for (IF_enumForDb e: enumList) {
			hm.put(e.dbVal(), e.userText());
		}
		
		if (bAddEmtpyKey) {
			hm.put("", "-");
		}
		
		return hm;
	}
	
	
	
	/**
	 * gets enum to a given dbVal (null when nothing found)
	 * @param enumList
	 * @param dbVal
	 * @return
	 */
	public static IF_enumForDb findEnum(IF_enumForDb[] enumList, String dbVal) {
		
		for (IF_enumForDb e: enumList) {
			if (e.dbVal().equals(dbVal)) {
				return e;
			}
		}
		return null;
	}
	
	
}
