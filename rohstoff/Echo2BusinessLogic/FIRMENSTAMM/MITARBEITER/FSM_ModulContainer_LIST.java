package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FSM_ModulContainer_LIST extends E2_BasicModuleContainer
{
	
	private E2_NavigationList 			oNavigationList = null;
	private FSM_LIST_ComponentMAP		oLISTComponentMAP = null;
	private FSM_ModulContainer_MASK 	oMASK_Container = null;
	
	private FSM_BT_LIST_VIEW_MITARBEITER 	oBT_VIEW_MITARBEITER = null;
	private FSM_BT_LIST_NEW_MITARBEITER 	oBT_NEW_MITARBEITER = null;
	private FSM_BT_LIST_EDIT_MITARBEITER 	oBT_EDIT_MITARBEITER = null;
	private FSM_BT_LIST_DELETE_MITARBEITER 	oBT_DELETE_MITARBEITER = null;

	private E2_SelectionComponentsVector 	oSelVector = null;
	/*
	 * eigener messageagent, der nur dann die messages nicht in dem 
	 * uebergebenen messageagent angzeigt, wenn die eigene maske aktiv ist
	 */
	
	public FSM_ModulContainer_LIST() throws myException
	{
		super();
		
		this.set_bVisible_Row_For_Messages(false);   // die messagerow ausschalten
		
		this.oNavigationList = new E2_NavigationList();

		this.oLISTComponentMAP = new FSM_LIST_ComponentMAP();

		
		this.oMASK_Container = new FSM_ModulContainer_MASK();
		
		
		MyE2_Column oColWithPanels = new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
		
		E2_ComponentGroupHorizontal oBedienPanel2 = new E2_ComponentGroupHorizontal(new Border(1,new E2_ColorDark(),Border.STYLE_SOLID),E2_INSETS.I_1_1_1_1);
		oBedienPanel2.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList), new Insets(3,2,15,2));
		this.oBT_VIEW_MITARBEITER = new FSM_BT_LIST_VIEW_MITARBEITER(this.oNavigationList,this.oMASK_Container);
		this.oBT_NEW_MITARBEITER = new FSM_BT_LIST_NEW_MITARBEITER(this.oNavigationList,this.oMASK_Container);
		this.oBT_EDIT_MITARBEITER = new FSM_BT_LIST_EDIT_MITARBEITER(this.oNavigationList,this.oMASK_Container);
		this.oBT_DELETE_MITARBEITER = new FSM_BT_LIST_DELETE_MITARBEITER(this.oNavigationList);
		
		oBedienPanel2.add(this.oBT_VIEW_MITARBEITER,E2_INSETS.I_5_0_0_0);
		oBedienPanel2.add(this.oBT_NEW_MITARBEITER,E2_INSETS.I_5_0_0_0);
		oBedienPanel2.add(this.oBT_EDIT_MITARBEITER,E2_INSETS.I_5_0_0_0);
		oBedienPanel2.add(this.oBT_DELETE_MITARBEITER,E2_INSETS.I_5_0_0_0);
		
		// selektion aktiv/nicht aktiv anzeigen
		this.oSelVector =	new E2_SelectionComponentsVector(oNavigationList);
		
		
		ownSelectorAktivInaktiv onwSelektor = new ownSelectorAktivInaktiv(bibARR.get_Array(50, 50));
		oSelVector.add(onwSelektor);
		oBedienPanel2.add(onwSelektor.get_oComponentForSelection(),E2_INSETS.I_10_0_0_0);
        // -- selektor ende		

		
		
		oColWithPanels.add(oBedienPanel2);
		
		/*
		 * die liste leer initialisieren
		 */
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oLISTComponentMAP, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(10);
		
		this.add(oColWithPanels);
		//this.add_In_ContainerEX(this.oNavigationList,new Extent(FS_CONST.MASK_WIDTH-50),new Extent(FS_CONST.MASK_HEIGHT-350), null);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);

		
	}

	
	private class ownSelectorAktivInaktiv extends E2_ListSelektorMultiselektionStatusFeld_STD
	{

		public ownSelectorAktivInaktiv(int[] intColWidths)
		{
			super(intColWidths, false, null, null);
			
			this.ADD_STATUS_TO_Selector(true, "NVL(JT_ADRESSE.AKTIV,'N')='Y'", new MyE2_String("Aktiv"), new MyE2_String("Zeige aktive Adressen"));
			this.ADD_STATUS_TO_Selector(true, "NVL(JT_ADRESSE.AKTIV,'N')='N'", new MyE2_String("Inaktiv"), new MyE2_String("Zeige inaktive Adressen"));
			
		}
		
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
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_LIST:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	public FSM_ModulContainer_MASK get_oMASK_Container()
	{
		return oMASK_Container;
	}

	public FSM_BT_LIST_DELETE_MITARBEITER get_oBT_DELETE_MITARBEITER()
	{
		return oBT_DELETE_MITARBEITER;
	}

	public FSM_BT_LIST_EDIT_MITARBEITER get_oBT_EDIT_MITARBEITER()
	{
		return oBT_EDIT_MITARBEITER;
	}

	public FSM_BT_LIST_NEW_MITARBEITER get_oBT_NEW_MITARBEITER()
	{
		return oBT_NEW_MITARBEITER;
	}

	public FSM_BT_LIST_VIEW_MITARBEITER get_oBT_VIEW_MITARBEITER()
	{
		return oBT_VIEW_MITARBEITER;
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	
}
