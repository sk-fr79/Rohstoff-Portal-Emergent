package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

 
/*
 * zeigt die Vorgangs-Positionsliste innerhalb der Vorgangs-Kopf-Maske an
 */
public class BST_P_LIST__ModulContainer extends E2_BasicModuleContainer 
{
	private BST_K_MASK__ModulContainer 		oContaingingModulContainerMask = null;
	private E2_NavigationList 				oNavigationList = null;
	
	private  BST_P_LIST__ComponentMAP		oComponentMAP_List = null;
	private  BST_P_LIST_BedienPanel			oListBedienPanel = null;
	
	
	public BST_P_LIST__ModulContainer(BST_K_MASK__ModulContainer 	ContainingModulContainerMask,
									 BST_P_MASK__ModulContainer  ModulContainerPositionsMask) throws myException
	{
		super();
		this.oContaingingModulContainerMask = ContainingModulContainerMask;
		
		this.set_MODUL_IDENTIFIER(ContainingModulContainerMask.get_MODUL_IDENTIFIER());
		this.set_bVisible_Row_For_Messages(false);
		
		this.oComponentMAP_List = 	new BST_P_LIST__ComponentMAP();
		this.oNavigationList = 		new E2_NavigationList();
		
		this.oListBedienPanel = new BST_P_LIST_BedienPanel(this.oContaingingModulContainerMask,ModulContainerPositionsMask,this.oNavigationList);
		
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oComponentMAP_List,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());
		//this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(5);
		
		// hilfs-columns
		MyE2_Column	oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		MyE2_ContainerEx	oContainerEX = new MyE2_ContainerEx();
		oContainerEX.setHeight(new Extent(400));
		oColBasic.add(this.oListBedienPanel, E2_INSETS.I_2_2_2_2);
		oColBasic.add(oContainerEX, E2_INSETS.I_2_2_2_2);
		oContainerEX.add(this.oNavigationList);
		
		this.add(oColBasic);
		this.oNavigationList._REBUILD_COMPLETE_LIST("");
	}

	
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_ID_VKOPF_TPA_IN_Position(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_List.get_oSQLFieldMAP().get("ID_VKOPF_TPA")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("BST_P_LIST_ModulContainer:set_ID_VKOPF_TPA_IN_Position: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	
	public E2_NavigationList 	get_oNavigationList() 			{		return oNavigationList;		}
	

	public BST_P_LIST_BedienPanel get_oListBedienPanel()
	{
		return oListBedienPanel;
	}


	public BST_P_LIST__ComponentMAP get_oComponentMAP_List()
	{
		return oComponentMAP_List;
	}

}
