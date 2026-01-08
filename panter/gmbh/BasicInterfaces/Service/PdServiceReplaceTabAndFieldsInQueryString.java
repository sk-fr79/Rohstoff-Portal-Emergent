/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 15.11.2018
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 15.11.2018
 *
 */
public class PdServiceReplaceTabAndFieldsInQueryString {
	
	public String replace(String tagString, String tag, VEK<PAIR<_TAB,String>> tables) throws myException {
		String ret = tagString;

		
		
		
		for (PAIR<_TAB,String> p: tables) {
			_TAB    t = p.getVal1();
			String  alias = p.getVal2();
		
			//zuerst die tabellen 
			ret = ret.replace("#"+t.n()+"#", t.n());
			
			for (IF_Field f: t.get_fields()) {
				//ersetzt in string z.b. #S1.NAME1#"  S1.NAME1 
				ret = ret.replace(tag+alias+"."+f.fn()+tag, f.fn(alias));
			}
		}
		
		
		
		return ret;
	}
	
}
