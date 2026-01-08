package panter.gmbh.indep.enumtools;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;


public interface IF_enum_4_db {
	public String db_val();
	public String user_text();

	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException;

	
	public default String dbVal4SqlTerm() {
		if (S.isEmpty(this.db_val())) {
			return "NULL";
		} else {
			return bibALL.MakeSql(this.db_val(),false);
		}
	}
	
	public default String user_text_lang() {
		return this.user_text();
	}




	
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
		
//		for (int l=0;l<ddarray.length;l++){
//			DEBUG.System_println("TEST asdd: 0="+ddarray[l][0]+"   --->   1="+ddarray[l][1]);
//		}
		
		return ddarray;
	}

	public default Pair<String>  getPair() {
		return Pair.P(this.db_val(), this.user_text());
	}
	public default Pair<String>  getPairLangtext() {
		return Pair.P(this.db_val(), this.user_text_lang());
	}
	
	/**
	 * 
	 * @return hashmap with value - text4user - pairs
	 * @throws myException 
	 */
	public default HashMap<String, String>  getHmKeyUserText() throws myException {
		HashMap<String, String>  hmRet = new HashMap<String, String>();
		String[][] c = this.get_dd_Array(false);
		
		for (int i=0;i<c.length;i++) {
			hmRet.put(c[i][1], c[i][0]);
		}
		return hmRet;
	}

}
