/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import java.util.HashMap;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceNormalizeString;
import panter.gmbh.indep.O;
import panter.gmbh.indep.bibALL;

/**
 * @author martin
 *
 */
public class PdServiceNormalizeStringBean implements PdServiceNormalizeString {

	private String positiv = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private HashMap<String, String> hmTausch = new HashMap<String, String>(){{put("ä","AE");put("Ä","AE");put("ö","OE");put("Ö","OE");put("ü","UE");put("Ü","UE");put("ß","SS");}};
	
	@Override
	public String normalForm(String c_orig) {
		c_orig = O.NN(c_orig,"");
		
		for (String s: hmTausch.keySet()) {
			c_orig = bibALL.ReplaceTeilString(c_orig, s, hmTausch.get(s));
		}
		
		c_orig = c_orig.toUpperCase();
		
		StringBuffer sbNew = new StringBuffer();
		for (int i=0;i<c_orig.length();i++) {
			if (positiv.contains(c_orig.substring(i, i+1))) {
				sbNew.append(c_orig.substring(i, i+1));
			}
		}
		
		return sbNew.toString();
	}

}
