/**
 * panter.gmbh.indep
 * @author martin
 * @date 06.11.2018
 * 
 */
package panter.gmbh.indep;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;

/**
 * @author martin
 * @date 06.11.2018
 * wird benutzt als Factory und abkuerzung fuer zu lange new irgendwasvonwirgendwem() -calls
 */
public class F {

	public static MyE2_MessageVector MV() {
		return new MyE2_MessageVector();
	}
	
}
