package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonRefreshLIST;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public class BSRG_P_LIST_BedienPanel extends MyE2_Grid
{
	private BSRG_P_LIST_BT_EDIT_VORGANGSPOS	oButEDIT = null;
	private BSRG_P_LIST_BT_NEW_VORGANGSPOS	oButNEW = null;
	private BSRG_P_LIST_BT_DEL_VORGANGSPOS	oButDEL = null;
	private E2_ButtonRefreshLIST            oButRefresh = null;
	
	
	public BSRG_P_LIST_BedienPanel(BSRG_K_MASK__ModulContainer 	ContainingModulContainerMask,
								  BSRG_P_MASK__ModulContainer		PopUpMaskContainerPosition,
								  E2_NavigationList				oNaviListPositions)
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());

		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviListPositions), E2_INSETS.I_0_2_2_2);
		
		E2_ComponentGroupHorizontal oGroupMaske = new E2_ComponentGroupHorizontal(0,
				new BSRG_P_LIST_BT_VIEW_VORGANGSPOS(oNaviListPositions,PopUpMaskContainerPosition,ContainingModulContainerMask),
				this.oButEDIT=new BSRG_P_LIST_BT_EDIT_VORGANGSPOS(oNaviListPositions,PopUpMaskContainerPosition,ContainingModulContainerMask),
				this.oButNEW=new BSRG_P_LIST_BT_NEW_VORGANGSPOS(oNaviListPositions,PopUpMaskContainerPosition,ContainingModulContainerMask),
				this.oButDEL=new BSRG_P_LIST_BT_DEL_VORGANGSPOS(oNaviListPositions,ContainingModulContainerMask),
				this.oButRefresh = new E2_ButtonRefreshLIST(oNaviListPositions),
				E2_INSETS.I_2_2_2_2);
		
		oGroupMaske.setStyle(MyE2_Row.STYLE_3D_BORDER());
		
		this.add(oGroupMaske,E2_INSETS.I_0_2_2_2);
	}
	
	
	
	public void set_ButtonStatus(String cMASK_STATUS)
	{
		// this.oBasicEditListPanel.set_ALL_ButtonsEnabled_Without_Refresh(false);
		try
		{
			this.oButEDIT.set_bEnabled_For_Edit(false);
			this.oButNEW.set_bEnabled_For_Edit(false);
			this.oButDEL.set_bEnabled_For_Edit(false);
			this.oButRefresh.set_bEnabled_For_Edit(false);
		
			if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
			{
				//this.oBasicEditListPanel.set_BUTTON_STATUS_VIEW();
				this.oButEDIT.set_bEnabled_For_Edit(true);
				this.oButNEW.set_bEnabled_For_Edit(true);
				this.oButDEL.set_bEnabled_For_Edit(true);
				this.oButRefresh.set_bEnabled_For_Edit(true);
		}
			if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
			{
			}

		}
		catch (myException ex)
		{
			
		}
		
	}



	public BSRG_P_LIST_BT_NEW_VORGANGSPOS getoButNEW() {
		return oButNEW;
	}
	


}
