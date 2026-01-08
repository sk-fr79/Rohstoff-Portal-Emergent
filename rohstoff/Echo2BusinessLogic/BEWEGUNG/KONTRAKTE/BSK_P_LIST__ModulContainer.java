package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

 
/*
 * zeigt die Vorgangs-Positionsliste innerhalb der Vorgangs-Kopf-Maske an
 */
public class BSK_P_LIST__ModulContainer extends E2_BasicModuleContainer 
{
	private BSK_K_MASK__ModulContainer 		oContaingingModulContainerMask = null;
	private E2_NavigationList 				oNavigationList = null;
	
	private  BSK_P_LIST__ComponentMAP		oComponentMAP_List = null;
	private  BSK_P_LIST_BedienPanel			oListBedienPanel = null;
	
	
	public BSK_P_LIST__ModulContainer(BSK_K_MASK__ModulContainer 	ContainingModulContainerMask,
									 BSK_P_MASK__ModulContainer  ModulContainerPositionsMask) throws myException
	{
		super();
		this.oContaingingModulContainerMask = ContainingModulContainerMask;
		
		this.set_MODUL_IDENTIFIER(ContainingModulContainerMask.get_SETTING().get_cMODULCONTAINER_MASK_IDENTIFIER());
		this.set_bVisible_Row_For_Messages(false);
		
		this.oComponentMAP_List = 	new BSK_P_LIST__ComponentMAP();
		this.oNavigationList = 		new E2_NavigationList();
		
		this.oListBedienPanel = new BSK_P_LIST_BedienPanel(this.oContaingingModulContainerMask,ModulContainerPositionsMask,this.oNavigationList);
		
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oComponentMAP_List,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());
		//this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(3);
		
		
		this.add(this.oListBedienPanel,E2_INSETS.I_2_2_2_2);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(BSK_K_MASK__ModulContainer.MASK_WIDTH-50),new Extent(BSK_K_MASK__ModulContainer.MASK_HEIGHT-250), null);

		
		this.oNavigationList._REBUILD_COMPLETE_LIST("");
	}

	
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_ID_VKOPF_KON_IN_Position(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_List.get_oSQLFieldMAP().get("ID_VKOPF_KON")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("BSK_P_LIST_ModulContainer:set_ID_VKOPF_KON_IN_Position: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	
	public E2_NavigationList 	get_oNavigationList() 			{		return oNavigationList;		}
	

	public BSK_P_LIST_BedienPanel get_oListBedienPanel()
	{
		return oListBedienPanel;
	}


	public BSK_P_LIST__ComponentMAP get_oComponentMAP_List()
	{
		return oComponentMAP_List;
	}

}
