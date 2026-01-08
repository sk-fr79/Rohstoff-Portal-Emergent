/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BT_LIST_VIEW_BAMF extends MyE2_Button
{
	public BT_LIST_VIEW_BAMF(	E2_NavigationList 			oList,
								BAMF_MASK_ModulContainer 	oMaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("view.png"), true);
		this.add_oActionAgent(new ActionAgentVIEW(oList,oMaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FBAM_LIST,"ZEIGE_FBAM"));
	}
	
	class ActionAgentVIEW extends ButtonActionAgentVIEW
	{
		
		//2012-10-16: neuer button zum direkten Sprung in die Fuhre aus der BAM-Maske
		private BAMF_MASK_OpenFuhre   oButtonOpenFuhreEdit = null;
		private BAMF_MASK_OpenFuhre   oButtonOpenFuhreView = null;
		
		public ActionAgentVIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Anzeigen Transport-Position-BAM"), onavigationList, omaskContainer, oownButton, null);
			
			this.get_vZusatzkomponenten().add(new BT_MASK_Mail_Print_BAMF((BAMF_MASK_ModulContainer)this.get_oMaskContainer(),null,true));
			this.get_vZusatzkomponenten().add(new BT_MASK_Mail_Print_WEIGER((BAMF_MASK_ModulContainer)this.get_oMaskContainer(),null,true));
			
			//2012-10-16: neue buttons
			this.oButtonOpenFuhreEdit = new BAMF_MASK_OpenFuhre((BAMF_MASK_ModulContainer)this.get_oMaskContainer(), true);
			this.oButtonOpenFuhreView = new BAMF_MASK_OpenFuhre((BAMF_MASK_ModulContainer)this.get_oMaskContainer(), false);

			
			this.get_vZusatzkomponenten().add(this.oButtonOpenFuhreEdit);
			this.get_vZusatzkomponenten().add(this.oButtonOpenFuhreView);
			
		}
		
		/*
		 * ueberschreiben der 2 relevanten methoden
		 */
		public boolean do_prepareMaskForActualID() throws myException
		{
//			//dem fuhren-button die ID_FBAM uebergeben
//			this.oButtonOpenFuhreEdit.set_cID_FBAM_uF(this.get_cActualID_Unformated());
//			this.oButtonOpenFuhreView.set_cID_FBAM_uF(this.get_cActualID_Unformated());
			
			return true;
		}


	}
	
}