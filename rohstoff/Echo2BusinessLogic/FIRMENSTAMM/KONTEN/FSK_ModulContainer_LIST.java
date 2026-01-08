package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;

public class FSK_ModulContainer_LIST extends E2_BasicModuleContainer
{
	
	private E2_NavigationList 			oNavigationList = null;
	private FSK_LIST_ComponentMAP		oLISTComponentMAP = null;
	private FSK_ModulContainer_MASK 	oMASK_Container = null;
	
	private FSK_BT_LIST_VIEW_KONTO 	oBT_VIEW_KONTO = null;
	private FSK_BT_LIST_NEW_KONTO 		oBT_NEW_KONTO = null;
	private FSK_BT_LIST_EDIT_KONTO 	oBT_EDIT_KONTO = null;
	private FSK_BT_LIST_DELETE_KONTO 	oBT_DELETE_KONTO = null;

	
	
	public FSK_ModulContainer_LIST() throws myException
	{
		super();
		
		
		this.set_bVisible_Row_For_Messages(false);   // die messagerow ausschalten
		
		this.oNavigationList = new E2_NavigationList();

		this.oLISTComponentMAP = new FSK_LIST_ComponentMAP();

		
		this.oMASK_Container = new FSK_ModulContainer_MASK();
		
		
		MyE2_Column oColWithPanels = new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
		
		E2_ComponentGroupHorizontal oBedienPanel2 = new E2_ComponentGroupHorizontal(new Border(1,new E2_ColorDark(),Border.STYLE_SOLID),E2_INSETS.I_1_1_1_1);
		oBedienPanel2.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList), new Insets(3,2,15,2));
		this.oBT_VIEW_KONTO = new FSK_BT_LIST_VIEW_KONTO(this.oNavigationList,this.oMASK_Container);
		this.oBT_NEW_KONTO = new FSK_BT_LIST_NEW_KONTO(this.oNavigationList,this.oMASK_Container);
		this.oBT_EDIT_KONTO = new FSK_BT_LIST_EDIT_KONTO(this.oNavigationList,this.oMASK_Container);
		this.oBT_DELETE_KONTO = new FSK_BT_LIST_DELETE_KONTO(this.oNavigationList);
		
		oBedienPanel2.add(this.oBT_VIEW_KONTO);
		oBedienPanel2.add(this.oBT_NEW_KONTO);
		oBedienPanel2.add(this.oBT_EDIT_KONTO);
		oBedienPanel2.add(this.oBT_DELETE_KONTO);
		
		oColWithPanels.add(oBedienPanel2);
		
		/*
		 * die liste leer initialisieren
		 */
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oLISTComponentMAP, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(10);
		
		this.add(oColWithPanels);
		// this.add_In_ContainerEX(this.oNavigationList,new Extent(FS_CONST.MASK_WIDTH-50),new Extent(FS_CONST.MASK_HEIGHT-350), null);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);

	}

	public E2_NavigationList get_oNavigationList()
	{
		return oNavigationList;
	}

	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ADRESS_ID(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oLISTComponentMAP.get_oSQLFieldMAP().get("ID_ADRESSE")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_LIST:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	public FSK_ModulContainer_MASK get_oMASK_Container()
	{
		return oMASK_Container;
	}

	public FSK_BT_LIST_DELETE_KONTO get_oBT_DELETE_MITARBEITER()
	{
		return oBT_DELETE_KONTO;
	}

	public FSK_BT_LIST_EDIT_KONTO get_oBT_EDIT_MITARBEITER()
	{
		return oBT_EDIT_KONTO;
	}

	public FSK_BT_LIST_NEW_KONTO get_oBT_NEW_MITARBEITER()
	{
		return oBT_NEW_KONTO;
	}

	public FSK_BT_LIST_VIEW_KONTO get_oBT_VIEW_MITARBEITER()
	{
		return oBT_VIEW_KONTO;
	}
	
	
}
