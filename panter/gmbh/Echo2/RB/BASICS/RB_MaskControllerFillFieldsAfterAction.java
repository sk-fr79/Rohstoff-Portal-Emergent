/**
 * 
 */
package panter.gmbh.Echo2.RB.BASICS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public abstract class RB_MaskControllerFillFieldsAfterAction extends RB_MaskController {

	/**
	 * @param p_componentMap
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskControllerFillFieldsAfterAction(RB_ComponentMap p_componentMap, boolean immediateBuild) throws myException {
		super(p_componentMap,immediateBuild);
	}

	/**
	 * @param p_componentMap
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskControllerFillFieldsAfterAction(RB_ComponentMapCollector p_mapCollector, boolean immediateBuild) throws myException {
		super(p_mapCollector,immediateBuild);
	}
	
	public abstract MyE2_MessageVector fillMaskfieldsAfterAction(Component callingComponent, String foundValue, boolean activ) throws myException;
	
	
}
