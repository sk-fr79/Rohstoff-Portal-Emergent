/**
 * 
 */
package panter.gmbh.BasicInterfaces;

import java.util.Collection;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface IF__addHelperr<T> {
	public Collection<T> getMembers() throws myException; 
}
