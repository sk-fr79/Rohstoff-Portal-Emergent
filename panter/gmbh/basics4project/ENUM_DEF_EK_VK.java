/**
 * panter.gmbh.basics4project
 * @author martin
 * @date 29.11.2019
 * 
 */
package panter.gmbh.basics4project;

import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * @date 29.11.2019
 *
 */
public enum ENUM_DEF_EK_VK implements IF_enumForDb<ENUM_DEF_EK_VK> {

	EK
	,VK
	
	;

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enumForDb#getValues()
	 */
	@Override
	public ENUM_DEF_EK_VK[] getValues() {
		return ENUM_DEF_EK_VK.values();
	}

	
}
