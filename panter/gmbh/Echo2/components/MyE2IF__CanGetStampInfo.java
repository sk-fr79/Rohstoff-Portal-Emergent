package panter.gmbh.Echo2.components;

import panter.gmbh.indep.exceptions.myException;

/**
 * kann bei komponenten implementiert werden, wo ein masken-stamp gezogen werden muss
 * @author martin
 *
 */
public interface MyE2IF__CanGetStampInfo {

	public String get_STAMP_INFO() throws myException;
	
}
