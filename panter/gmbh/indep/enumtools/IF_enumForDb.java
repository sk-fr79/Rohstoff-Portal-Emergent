package panter.gmbh.indep.enumtools;

import java.util.HashMap;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;


public interface IF_enumForDb<T extends Enum<T>> {
	
	public T[] getValues();
	
	public default String userText() {
		return this.dbVal();
	}

	public default String userTextLang() {
		return this.userText();
	}

	
	@SuppressWarnings("unchecked")
	public default String dbVal() {
		return ((Enum<T>)this).name();
	}
	
	public default String dbVal4SqlTerm() {
		if (S.isEmpty(this.dbVal())) {
			return "NULL";
		} else {
			return bibALL.MakeSql(this.dbVal(),false);
		}
	}
	

	public default Pair<String>  getPair() {
		return Pair.P(this.dbVal(), this.userText());
	}
	public default Pair<String>  getPairLangtext() {
		return Pair.P(this.dbVal(), this.userTextLang());
	}
	
	
	public default RB_selField getRBSelField(boolean emptyInFront) throws myException {
		RB_selField s = new RB_selField()._populate(this.getArray4Selfield(emptyInFront));
		return s;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public default String[][] getArray4Selfield(boolean emptyInFront) throws myException {
		
		String[][] arr = new String[this.getValues().length][2];
		
		for (int i=0;i<this.getValues().length;i++) {
			arr[i][0]=((IF_enumForDb)this.getValues()[i]).userText();
			arr[i][1]=((IF_enumForDb)this.getValues()[i]).dbVal();
		}
		
		if (emptyInFront) {
			arr = bibARR.add_emtpy_db_value_inFront(arr);
		}
		
		return arr;
	}

	
	@SuppressWarnings("rawtypes")
	public default HashMap<T,String> getHmTextEnum() {
		HashMap<T,String> hm = new HashMap<T,String>();
		
		for (T t:  this.getValues()) {
			hm.put(t, ((IF_enumForDb)t).dbVal());
		}
		return hm;
	}


	/**
	 * 
	 * @param dbVal
	 * @return Enum korresponding to dbVal
	 */
	@SuppressWarnings("rawtypes")
	public default T getEnum(String dbVal) {
		T ret = null;
		
		for (T t: this.getHmTextEnum().keySet()) {
			if ( ((IF_enumForDb) t).dbVal().equals(dbVal)) {
				ret = t;
			}
		}
		return ret;
	}
	
	
	
	

}
