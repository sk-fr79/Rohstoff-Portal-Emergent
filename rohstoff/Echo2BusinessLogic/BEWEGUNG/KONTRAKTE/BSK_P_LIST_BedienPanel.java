package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public class BSK_P_LIST_BedienPanel extends MyE2_Grid
{
	private BSK_P_LIST_BT_EDIT_VORGANGSPOS			oButEDIT = null;
	private BSK_P_LIST_BT_NEW_VORGANGSPOS			oButNEW = null;
	private BSK_P_LIST_BT_DELETE_VORGANGSPOS		oButDEL = null;
	private BSK_P_LIST_BT_ZUORDNUNG					oButZuordnung = null;
	private BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT		oButLockUnlock = null;
	
	public BSK_P_LIST_BedienPanel(BSK_K_MASK__ModulContainer 	ContainingModulContainerMask,
								 BSK_P_MASK__ModulContainer	PopUpMaskContainerPosition,
								 E2_NavigationList			oNaviListPositions) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());

		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviListPositions), E2_INSETS.I_0_2_2_2);
		
		E2_ComponentGroupHorizontal oGroupMaske = new E2_ComponentGroupHorizontal(0,
				new MyE2_Label(new MyE2_String("Maskenbefehle:")),
				new BSK_P_LIST_BT_VIEW_VORGANGSPOS(oNaviListPositions,PopUpMaskContainerPosition,ContainingModulContainerMask),
				this.oButEDIT=		new BSK_P_LIST_BT_EDIT_VORGANGSPOS(oNaviListPositions,PopUpMaskContainerPosition,ContainingModulContainerMask),
				this.oButNEW=		new BSK_P_LIST_BT_NEW_VORGANGSPOS(oNaviListPositions,PopUpMaskContainerPosition,ContainingModulContainerMask),
				this.oButDEL=		new BSK_P_LIST_BT_DELETE_VORGANGSPOS(oNaviListPositions,ContainingModulContainerMask),
				this.oButLockUnlock=	new BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT(oNaviListPositions,PopUpMaskContainerPosition.get_SETTING()),
				this.oButZuordnung=	new BSK_P_LIST_BT_ZUORDNUNG(oNaviListPositions,ContainingModulContainerMask),
				E2_INSETS.I_2_2_2_2);

		oGroupMaske.setStyle(MyE2_Row.STYLE_3D_BORDER());
	
		this.add(oGroupMaske,E2_INSETS.I_0_2_2_2);
	}
	
	
	
	public void set_ButtonStatus(String cMASK_STATUS)
	{
		try
		{
			this.oButEDIT.set_bEnabled_For_Edit(false);
			this.oButNEW.set_bEnabled_For_Edit(false);
			this.oButDEL.set_bEnabled_For_Edit(false);
			this.oButLockUnlock.set_bEnabled_For_Edit(false);
			this.oButZuordnung.set_bEnabled_For_Edit(false);
			
		
			if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
			{
				this.oButEDIT.set_bEnabled_For_Edit(true);
				this.oButNEW.set_bEnabled_For_Edit(true);
				this.oButDEL.set_bEnabled_For_Edit(true);
				this.oButLockUnlock.set_bEnabled_For_Edit(true);
				this.oButZuordnung.set_bEnabled_For_Edit(true);	
			}
		}
		catch (myException ex)
		{
		}
	}



	public BSK_P_LIST_BT_DELETE_VORGANGSPOS get_oButDEL()
	{
		return oButDEL;
	}




	public BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT get_oBut_Lock_Unlock()
	{
		return oButLockUnlock;
	}



	/**
	 * 2018-05-28
	 * @return
	 */
	public BSK_P_LIST_BT_NEW_VORGANGSPOS getButNEW() {
		return oButNEW;
	}
	
	

}
