package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;


public class FUO_LIST_BasicModuleContainer extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	private FUO_LIST_BedienPanel 			oPanel  = null;
	private FUO_MASK_BasicModuleContainer 	oMaskContainer = null;
	private FUO_LIST_ComponentMap  			oListComponentMap = null;
	
	private E2_NavigationList 				oNaviList = null;
	
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;
	
	public FUO_LIST_BasicModuleContainer(String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super();
		
		this.oFUO_DaughterComponent = FUO_DaugherComponent;
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oMaskContainer =new FUO_MASK_BasicModuleContainer(cEK_VK, oFUO_DaughterComponent);
		this.oListComponentMap = new FUO_LIST_ComponentMap(cEK_VK, oFUO_DaughterComponent);
		this.oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(this.oListComponentMap,E2_NavigationList.STYLE__4_2_4_2, null);
		this.oPanel = new FUO_LIST_BedienPanel(oNaviList,this.oMaskContainer, oFUO_DaughterComponent);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		this.oNaviList._REBUILD_COMPLETE_LIST("");
		
	}

	public FUO_LIST_BedienPanel get_oPanel()
	{
		return oPanel;
	}

	public FUO_MASK_BasicModuleContainer get_oMaskContainer()
	{
		return oMaskContainer;
	}
	 
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ID_VPOS_TPA_FUHRE(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oListComponentMap.get_oSQLFieldMAP().get("ID_VPOS_TPA_FUHRE")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
			((SQLFieldForRestrictTableRange)this.oMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oSQLFieldMAP().get("ID_VPOS_TPA_FUHRE")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException(this,"FSM_ModulContainer_LIST:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}

	
		
}
