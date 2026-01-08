package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel.XX_ADDON_Buttons_Status_AGENT;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchButtons;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;


//NEU_09
public class FSA_ModulContainer_LIST extends E2_BasicModuleContainer
{
	
	private E2_ContentPane 					oContentPane = null;
	private E2_NavigationList 				oNavigationList = null;
	private FSA_LIST_ComponentMAP			oLISTComponentMAP = null;
	private E2_BASIC_EditListButtonPanel 	oBasicBedienpanel = null;
	private FS_ModulContainer_MASK			oOwnE2_ModulContainerMASK  = null;
	
	private E2_SelectionComponentsVector	oSelVector = null;    
	private MyE2_SelectField   				oSelectSortennummer = null; 
	/**
	 * @param cEK_VK MUSS entweder "EK" oder "VK" sein
	 * @throws myException
	 */
	public FSA_ModulContainer_LIST(	FS_ModulContainer_MASK	OwnE2_ModulContainerMASK,
									FS_MASK_ComponentMAP    oFS_ComponentMapMASK,
									String 					cEK_VK) throws myException
	{
		super();
		this.oOwnE2_ModulContainerMASK = OwnE2_ModulContainerMASK;
		
		this.set_bVisible_Row_For_Messages(false);   // die messagerow ausschalten
		
		this.oNavigationList = new E2_NavigationList();
		this.oNavigationList.get_oDataGrid().setWidth(new Extent(99,Extent.PERCENT));
		

		this.oLISTComponentMAP = new FSA_LIST_ComponentMAP(oFS_ComponentMapMASK,cEK_VK);

		///
		this.oSelVector = 				new E2_SelectionComponentsVector(this.oNavigationList);

		this.oSelectSortennummer = new MyE2_SelectField(
				"select distinct SUBSTR(ANR1,1,2)||'xx',SUBSTR(ANR1,1,2) from "+bibE2.cTO()+".JT_ARTIKEL ORDER BY SUBSTR(ANR1,1,2)",
				false,true,false,false);
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelectSortennummer,"JT_ARTIKEL.ANR1 LIKE '#WERT#%'", null, null));
		//
		
		
		MyE2_Column oColWithPanels = new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
		
		E2_ComponentGroupHorizontal oBedienPanel2 = new E2_ComponentGroupHorizontal(new Border(1,new E2_ColorDark(),Border.STYLE_SOLID),E2_INSETS.I_1_1_1_1);
		oBedienPanel2.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList), new Insets(3,2,15,2));
		
		/*
		 * universalbedienelement einfuegen
		 */
		this.oBasicBedienpanel = new E2_BASIC_EditListButtonPanel(this.oNavigationList,true,true,true,null,null,null,"", null, null, null);
		this.oBasicBedienpanel.set_oADDON_BUTTON_STATUS(new ownADDON_Buttons_Status_AGENT());
		
		/*
		 * jetzt button-validators einfuegen
		 */
		oBasicBedienpanel.get_ButtonEditSelected().add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"ARTIKELBEZ_BEARBEITEN"));
		oBasicBedienpanel.get_ButtonNewROW().add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"ARTIKELBEZ_NEUEINGABE"));
		oBasicBedienpanel.get_ButtonDelete().add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"ARTIKELBEZ_LOESCHEN"));
		oBasicBedienpanel.get_ButtonCopy().add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"ARTIKELBEZ_KOPIEREN"));
		oBedienPanel2.add(oBasicBedienpanel);
		
		
		oBedienPanel2.add(new MyE2_Label(new MyE2_String("Material")), new Insets(5,2,2,2));
		oBedienPanel2.add(this.oSelectSortennummer, new Insets(3,2,5,2));
		
		oColWithPanels.add(oBedienPanel2);
		
		/*
		 * die liste leer initialisieren
		 */
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oLISTComponentMAP, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Insets(2,0,2,0)), null);
		//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(5);
		this.oBasicBedienpanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
		
		this.add(oColWithPanels);
		//this.add_In_ContainerEX(this.oNavigationList,new Extent(FS_CONST.MASK_WIDTH-50),new Extent(FS_CONST.MASK_HEIGHT-350), null);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), E2_INSETS.I_0_0_0_0);
		
	}

	public E2_NavigationList get_oNavigationList()
	{
		return oNavigationList;
	}

	public E2_ContentPane get_oContentPane()
	{
		return oContentPane;
	}

	
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ADRESS_ID(String cID_Unformated) throws myException
	{
		try
		{
			// hier muss der selektor auf leer gesetzt werden, damit eine evtl. vorher gesetzte bedingung nicht aktiv bleibt
			this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_DYNAMIC();
			this.oSelectSortennummer.setSelectedIndex(0);
			
			
			((SQLFieldForRestrictTableRange)this.oLISTComponentMAP.get_oSQLFieldMAP().get("ID_ADRESSE")).set_cRestrictionValue_IN_DB_FORMAT(cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_LIST:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	public E2_BASIC_EditListButtonPanel get_oBASIC_Bedienpanel()
	{
		return oBasicBedienpanel;
	}

	
	
	/*
	 * ein XX_ADDON_Buttons_Status_AGENT erstellen, damit ein editieren / neueingabe
	 * die maskenbuttons
	 */
	private class ownADDON_Buttons_Status_AGENT extends XX_ADDON_Buttons_Status_AGENT
	{
		public boolean set_Status(String cActualStatus) throws myException
		{
			FSA_ModulContainer_LIST oThis = FSA_ModulContainer_LIST.this;
			
			E2_RecursiveSearchButtons oSearchButtons = new E2_RecursiveSearchButtons(oThis.oOwnE2_ModulContainerMASK.get_oRowForButtons());
			
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
			
			//selector nicht vergessen
			oThis.oSelectSortennummer.set_bEnabled_For_Edit(bEnabled);
			
			// dann die tabbed-panes auch abschalten
			if (!bEnabled)
				oThis.oOwnE2_ModulContainerMASK.get_oTabbedPaneMask().set_disable_All_Tabs_ExceptActualTab();
			else
				oThis.oOwnE2_ModulContainerMASK.get_oTabbedPaneMask().set_enable_All_Tabs();
			
			return true;
		}
		
	}

	
}
