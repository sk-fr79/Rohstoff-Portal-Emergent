package panter.gmbh.Echo2.RB.DATA;

import java.util.LinkedHashMap;

import panter.gmbh.indep.exceptions.myException;

/**
 * hilfsklasse, um innerhalb eines mehrfach-datasets zu navigieren (z.b. masken-blaettern)
 * @author martin
 *
 */
public class RB_hm_multi_DataobjectsCollector extends LinkedHashMap<String,RB_DataobjectsCollector> {

	public RB_hm_multi_DataobjectsCollector() {
		super();
	}

	
	public String get_firstKey() {
		String firstKey = null;
		
		for (String  key: this.keySet()) {
			firstKey = key;
			break;
		}
		return firstKey;
	}
	
	
	
	public String get_lastKey() {
		String lastKey = null;
		
		for (String  key: this.keySet()) {
			lastKey = key;
		}
		return lastKey;
	}

	
	
	public RB_DataobjectsCollector get_first_DataObjects_container() {
		return this.get(this.get_firstKey());
	}
	
	public RB_DataobjectsCollector get_last_DataObjects_container() {
		return this.get(this.get_lastKey());
	}
	
	public boolean is_first(RB_DataobjectsCollector container) {
		if (this.get_first_DataObjects_container()==container) {
			return true;
		} 
		return false;
	}
	
	
	
	public boolean is_last(RB_DataobjectsCollector container) {
		if (this.get_last_DataObjects_container()==container) {
			return true;
		} 
		return false;
	}
	
	
	public String key_of(RB_DataobjectsCollector container) {
		String ckeyFound = null;
		for (String  key: this.keySet()) {
			if (this.get(key)==container) {
				ckeyFound=key;
				break;
			}
		}
		return ckeyFound;
	}


	public int get_positionNumber(String cKey)  {
		int iRueck = 0;
		
		for (String  key: this.keySet()) {
			iRueck++;
			if ((key.equals(cKey))) {
				break;
			}
		}
	
		if (iRueck>this.size()) {
			iRueck=0;
		}
		
		return iRueck;
	}

	
	
	
	public String get_previousKey(String cKey)  {
		String cRueck = null;
		
		for (String  key: this.keySet()) {
			if (!(key.equals(cKey))) {
				cRueck = key;
			} else {
				break;
			}
		}
	
		//wenn es der erste war, dann null
		if (cRueck==null) {
			cRueck = this.get_firstKey();
		}
		
		return cRueck;
	}
	

	public String get_nextKey(String cKey)  {
		String cRueck = null;
		boolean bStart = false;
		for (String  key: this.keySet()) {
			if (key.equals(cKey)) {
				bStart=true;
			} else {
				if (bStart) {
					cRueck = key;
					break;
				}
			}
		}
	
		//wenn es der erste war, dann null
		if (cRueck==null) {
			cRueck = this.get_lastKey();
		}
		
		return cRueck;
	}

	
	
//	public void rb_Clear_ALL_RECNEW() throws myException {
//		for (RB_DataobjectsCollector d_obc: this.values()) {
//			for (RB_Dataobject d_ob: d_obc) {
//				d_ob.rb_Clear_RECNEW();
//			}
//		}
//	}
	
	public void rb_Rebuild_ALL_RECORD(boolean bOnlyWhenExists) throws myException {
		for (RB_DataobjectsCollector d_obc: this.values()) {
			for (RB_Dataobject d_ob: d_obc) {
				if (d_ob.get_RecORD()!=null || (!bOnlyWhenExists)) {
					d_ob.rb_Rebuild_RECORD();
				}
			}
		}
	}


	
	
	
}
