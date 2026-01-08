package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FSL_ModulContainer_LIST extends E2_BasicModuleContainer
{
	

	private E2_NavigationList 			oNavigationList = null;
	private FSL_LIST_ComponentMAP		oLISTComponentMAP = null;
	private FSL_ModulContainer_MASK 	oMASK_Container = null;
	
	private FSL_BT_LIST_VIEW_LIEFERADRESSE 	oBT_VIEW_LIEFERADRESSE = null;
	private FSL_BT_LIST_NEW_LIEFERADRESSE 	oBT_NEW_LIEFERADRESSE = null;
	private FSL_BT_LIST_EDIT_LIEFERADRESSE 	oBT_EDIT_LIEFERADRESSE = null;
	private FSL_BT_LIST_DELETE_LIEFERADRESSE oBT_DELETE_LIEFERADRESSE = null;

	private FSL_LIST_DATASEARCH oDatasearch = null;
	
	private FSL_ListSelector    oListSelector = null;

	
	
	public FSL_ModulContainer_LIST() throws myException
	{
		super();
		
		this.set_bVisible_Row_For_Messages(false);   // die messagerow ausschalten
		
		this.oNavigationList = new E2_NavigationList();

		this.oListSelector = new FSL_ListSelector(this.oNavigationList);
		
		this.oLISTComponentMAP = new FSL_LIST_ComponentMAP();

		this.oMASK_Container = new FSL_ModulContainer_MASK();
		
		MyE2_Column oColWithPanels = new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
		
		E2_ComponentGroupHorizontal oBedienPanel2 = new E2_ComponentGroupHorizontal(new Border(1,new E2_ColorDark(),Border.STYLE_SOLID),E2_INSETS.I_1_1_1_1);
		oBedienPanel2.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList), new Insets(3,2,15,2));
		this.oBT_VIEW_LIEFERADRESSE = new FSL_BT_LIST_VIEW_LIEFERADRESSE(this.oNavigationList,this.oMASK_Container);
		this.oBT_NEW_LIEFERADRESSE = new FSL_BT_LIST_NEW_LIEFERADRESSE(this.oNavigationList,this.oMASK_Container);
		this.oBT_EDIT_LIEFERADRESSE = new FSL_BT_LIST_EDIT_LIEFERADRESSE(this.oNavigationList,this.oMASK_Container);
		this.oBT_DELETE_LIEFERADRESSE = new FSL_BT_LIST_DELETE_LIEFERADRESSE(this.oNavigationList);
		
		oBedienPanel2.add(this.oBT_VIEW_LIEFERADRESSE);
		oBedienPanel2.add(this.oBT_NEW_LIEFERADRESSE);
		oBedienPanel2.add(this.oBT_EDIT_LIEFERADRESSE);
		oBedienPanel2.add(this.oBT_DELETE_LIEFERADRESSE);
		oBedienPanel2.add(new FSL_BT_LIST_EXPORT_EXCEL(this.oNavigationList));
		
		
		oColWithPanels.add(oBedienPanel2);
		
		oBedienPanel2.add(this.oListSelector,E2_INSETS.I_10_0_0_0);
		
		//2011-12-16: suche in lieferadressen
		oBedienPanel2.add(this.oDatasearch=new FSL_LIST_DATASEARCH(
							this.oNavigationList,
							E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_LAGER_LISTE,
							this.oLISTComponentMAP.get_oSQLFieldMAP()));

		/*
		 * die liste leer initialisieren
		 */
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oLISTComponentMAP, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(10);
		
		this.add(oColWithPanels);
		//this.add_In_ContainerEX(this.oNavigationList,new Extent(FS_CONST.MASK_WIDTH-50),new Extent(FS_CONST.MASK_HEIGHT-350), null);
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
			((SQLFieldForRestrictTableRange)this.oLISTComponentMAP.get_oSQLFieldMAP().get("ID_ADRESSE_BASIS")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
			
			//2011-12-16: suche in lieferadressen
			this.oDatasearch.set_baseAdressID(cID_Unformated);
			
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_LIST:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	public FSL_ModulContainer_MASK get_oMASK_Container()
	{
		return oMASK_Container;
	}

	public FSL_BT_LIST_DELETE_LIEFERADRESSE get_oBT_DELETE_LIEFERADRESSE()
	{
		return oBT_DELETE_LIEFERADRESSE;
	}

	public FSL_BT_LIST_EDIT_LIEFERADRESSE get_oBT_EDIT_LIEFERADRESSE()
	{
		return oBT_EDIT_LIEFERADRESSE;
	}

	public FSL_BT_LIST_NEW_LIEFERADRESSE get_oBT_NEW_LIEFERADRESSE()
	{
		return oBT_NEW_LIEFERADRESSE;
	}

	public FSL_BT_LIST_VIEW_LIEFERADRESSE get_oBT_VIEW_LIEFERADRESSE()
	{
		return oBT_VIEW_LIEFERADRESSE;
	}




	public FSL_ListSelector get_oSelector()
	{
		return this.oListSelector;
	}
	
	
}
