/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 27.11.2018
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.11.2018
 *
 */
public abstract class E2_btOpenMask extends E2_Button {

	/*
	 * maskenobject erzeugen und fuellen 
	 */
	public abstract RB_ModuleContainerMASK createMask(Long id, MASK_STATUS status) throws myException;
	
	public abstract Long getIdToOpen() throws myException;

	public abstract ENUM_VALIDATION  getValidationKeyForEdit();
	
	public int getMaskWidth() {
		return 1000;
	}
	
	public int getMaskHeight() {
		return 800;
	}
	
	
	public abstract MyE2_String getMaskTitel();
	
	public E2_btOpenMask() {
		super();
		this._image(E2_ResourceIcon.get_RI("edit_list3.png"),true)._aaa(new ownActionAgent());
		
		
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Long id = getIdToOpen();
			
			boolean edit = true;
			MyE2_MessageVector mv_valid = getValidationKeyForEdit().getValidator().isValid(E2_btOpenMask.this);
			if (mv_valid.get_bHasAlarms()) {
				edit=false;
			}
			
			RB_ModuleContainerMASK mask = createMask(id, edit?MASK_STATUS.EDIT : MASK_STATUS.VIEW);
			
			mask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(getMaskWidth()),new Extent(getMaskHeight()), getMaskTitel());
			
		}
		
	}
	
	
	
	
}
