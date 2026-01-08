package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel.XX_ADDON_Buttons_Status_AGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchButtons;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class AS_MASK_BEZ_ModulContainerLIST extends E2_BasicModuleContainer 
{
	private AS_BasicModuleContainerMASK			oContaingingModulContainerMask = null;
	private E2_NavigationList 					oNavigationList = null;
	private E2_BASIC_EditListButtonPanel	  	oBasicEditListPanel = null;
	private AS_MASK_BEZ_LIST_ComponentMAP  		oComponentMAP_List = null;
	
	
	
	public AS_MASK_BEZ_ModulContainerLIST(AS_BasicModuleContainerMASK 	ContainingModulContainerMask) throws myException
	{
		super();
		this.oContaingingModulContainerMask = ContainingModulContainerMask;
		
		this.set_MODUL_IDENTIFIER(ContainingModulContainerMask.get_MODUL_IDENTIFIER());
		this.set_bVisible_Row_For_Messages(false);
		
		//NEU_09  --aenderung der uebergabe
		this.oComponentMAP_List = 	new AS_MASK_BEZ_LIST_ComponentMAP(ContainingModulContainerMask);
		this.oNavigationList = 		new E2_NavigationList();
	
		this.oBasicEditListPanel = 	new E2_BASIC_EditListButtonPanel(this.oNavigationList,
				    							true,true,true,null,null,ContainingModulContainerMask.get_MODUL_IDENTIFIER(),"", null, null, null);
			
		this.oBasicEditListPanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
		
		this.oBasicEditListPanel.set_oADDON_BUTTON_STATUS(new ownADDON_Buttons_Status_AGENT());
		this.oBasicEditListPanel.set_BUTTON_STATUS_VIEW();
		
		
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oComponentMAP_List,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(5);
		
		E2_ComponentGroupHorizontal oHorizontalgroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_2_2);
		oHorizontalgroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList));
		oHorizontalgroup.add(this.oBasicEditListPanel);
		this.add(oHorizontalgroup, E2_INSETS.I_2_2_2_2);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);
		
		this.oNavigationList._REBUILD_COMPLETE_LIST("");
	}

	
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_ID_ARTIKEL_IN_ARTBEZ(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_List.get_oSQLFieldMAP().get("ID_ARTIKEL")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
				
		}
		catch (Exception ex)
		{
			throw new myException("AS_MASK_BEZ_ModulContainerLIST:set_ID_ARTIKEL_IN_ARTBEZ: Unknown Error: "+ex.getLocalizedMessage());
		}
	}
	
	public E2_NavigationList 		get_oNavigationList() 			{		return oNavigationList;		}
	public E2_BASIC_EditListButtonPanel get_oBasicEditListPanel()	{		return oBasicEditListPanel;	}

	
	
	/*
	 * ein XX_ADDON_Buttons_Status_AGENT erstellen, damit ein editieren / neueingabe
	 * die maskenbuttons disabled. Damit ist die maske gesperrt, solange eine eingabe in den positionen erfolgt
	 */
	private class ownADDON_Buttons_Status_AGENT extends XX_ADDON_Buttons_Status_AGENT
	{
		public boolean set_Status(String cActualStatus) throws myException
		{
			AS_MASK_BEZ_ModulContainerLIST oThis = AS_MASK_BEZ_ModulContainerLIST.this;
			
			
			E2_RecursiveSearchButtons oSearchButtons = new E2_RecursiveSearchButtons(oThis.oContaingingModulContainerMask.get_oRowForButtons());
			
			boolean bEnabled = true;
			
			if (cActualStatus.equals(XX_ADDON_Buttons_Status_AGENT.STATUS_EDIT) || 
				cActualStatus.equals(XX_ADDON_Buttons_Status_AGENT.STATUS_INPUT_NEW))
				bEnabled = false;
				
				
			for (int i=0;i<oSearchButtons.get_vButtons().size();i++)
			{
				Component oTest = (Component) oSearchButtons.get_vButtons().get(i);
				if (oTest instanceof MyE2IF__Component)
				{
					((MyE2IF__Component)oTest).set_bEnabled_For_Edit(bEnabled);
				}
				
			}
			return true;
		}
	}

}
