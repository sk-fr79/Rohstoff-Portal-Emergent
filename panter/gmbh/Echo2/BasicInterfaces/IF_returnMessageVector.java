/**
 * panter.gmbh.Echo2.BasicInterfaces
 * @author martin
 * @date 15.02.2019
 * 
 */
package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;

/**
 * @author martin
 * @date 15.02.2019
 * funktionales interface, was die rueckgabe eines messagevectors erzwingt
 */
public interface IF_returnMessageVector {
	
	public MyE2_MessageVector execute();
	
}
