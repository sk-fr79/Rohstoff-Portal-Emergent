/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.Mask;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * 2018-07-19: controller fuer den speichervorgang. koennen den E2_ComponentMaps zugefuegt werden und 
 *             werden dann in den E2_SaveMask - Objekten ausgewertet
 */
public interface E2_SaveMaskSaveController_IF {

	public enum ENUM_SAVEMASKCONTROLLERS_POS {
		BEVORE_SAVE
		,AFTER_SAVE
	}
	
	
	
	public MyE2_MessageVector checkSaveing(E2_SaveMASK saver, ENUM_SAVEMASKCONTROLLERS_POS pos) throws myException; 
}
