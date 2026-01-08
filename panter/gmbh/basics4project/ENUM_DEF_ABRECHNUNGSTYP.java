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
public enum ENUM_DEF_ABRECHNUNGSTYP implements IF_enumForDb<ENUM_DEF_ABRECHNUNGSTYP> {

	FORDERUNG
	,VERBINDLICHKEIT
	,LEER_ZU_NULL
	,LAGER
	
	;

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enumForDb#getValues()
	 */
	@Override
	public ENUM_DEF_ABRECHNUNGSTYP[] getValues() {
		return ENUM_DEF_ABRECHNUNGSTYP.values();
	}

	
}
