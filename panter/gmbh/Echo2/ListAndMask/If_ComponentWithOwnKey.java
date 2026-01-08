/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 08.01.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 08.01.2019
 * interface fuer einfachere felddefinitionen in der e2_componentMap
 */
public interface If_ComponentWithOwnKey extends MyE2IF__Component {
	
	public String key() throws myException;
	public String userText() throws myException; 
	
}
