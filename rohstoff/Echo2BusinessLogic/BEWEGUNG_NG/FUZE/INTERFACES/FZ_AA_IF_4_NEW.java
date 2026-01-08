package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES;

import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;

public interface FZ_AA_IF_4_NEW {
	public abstract RB_ModuleContainerMASK 					generate_MaskContainer() throws myException;
	public abstract RB_DataobjectsCollector 				generate_DataObjects4New() throws myException;
	
	//hier werden die actions festgelegt
	public abstract void define_Actions_4_saveButton(RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException;
	public abstract void define_Actions_4_CloseButton(RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup) throws myException;

}
