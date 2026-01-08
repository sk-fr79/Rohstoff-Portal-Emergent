package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel.XX_ADDON_Buttons_Status_AGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchButtons;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FSK__FULL_MASK_DAUGHTER_TP_KOSTEN_LIST_CONTAINER extends E2_BasicModuleContainer
{
	
	private E2_NavigationList 				oNavigationList = null;
	private E2_BASIC_EditListButtonPanel 	oBasicBedienpanel = null;
	private E2_BasicModuleContainer_MASK	oModulContainerMASK_ADRESS  = null;
	private FSK_ListComponentMAP  			oComponentMAP_FSK = null;
	
	private FSK_ListSelector  				oListSelektor = null;
	 
	
	public FSK__FULL_MASK_DAUGHTER_TP_KOSTEN_LIST_CONTAINER(E2_BasicModuleContainer_MASK	 OwnE2_ModulContainerMASK) throws myException
	{
		super();
		this.oModulContainerMASK_ADRESS = OwnE2_ModulContainerMASK;
		
		this.set_bVisible_Row_For_Messages(false);   // die messagerow ausschalten
		
		this.oNavigationList = 		new E2_NavigationList();
		this.oComponentMAP_FSK = 	new FSK_ListComponentMAP(true,this.oModulContainerMASK_ADRESS);
		this.oListSelektor = 		new FSK_ListSelector(this.oNavigationList);
		
		
		MyE2_Column oColWithPanels = new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
		
		E2_ComponentGroupHorizontal oBedienPanel2 = new E2_ComponentGroupHorizontal(new Border(1,new E2_ColorDark(),Border.STYLE_SOLID),E2_INSETS.I_1_1_1_1);
		oBedienPanel2.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList), new Insets(3,2,15,2));
		
		
		
		/*
		 * universalbedienelement einfuegen
		 */
		this.oBasicBedienpanel = new E2_BASIC_EditListButtonPanel(this.oNavigationList,true,true,true,null,null,null,"", null, null, null);
		this.oBasicBedienpanel.set_oADDON_BUTTON_STATUS(new ownADDON_Buttons_Status_AGENT());
		
		/*
		 * selektor dranhaengen
		 */
		this.oBasicBedienpanel.add(this.oListSelektor,E2_INSETS.I(10,0,0,0));
		
		/*
		 * jetzt button-validators einfuegen
		 */
		String cMotherModuleName = OwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER();
		String ButtonsBasis = "KOSTEN_TP_";
		
		oBasicBedienpanel.get_ButtonEditSelected().add_GlobalValidator_removeOthers(new E2_ButtonAUTHValidator(	cMotherModuleName,ButtonsBasis+"BEARBEITEN"));
		oBasicBedienpanel.get_ButtonNewROW().add_GlobalValidator_removeOthers(new E2_ButtonAUTHValidator(		cMotherModuleName,ButtonsBasis+"NEUEINGABE"));
		oBasicBedienpanel.get_ButtonDelete().add_GlobalValidator_removeOthers(new E2_ButtonAUTHValidator(		cMotherModuleName,ButtonsBasis+"LOESCHEN"));
		oBasicBedienpanel.get_ButtonCopy().add_GlobalValidator_removeOthers(new E2_ButtonAUTHValidator(			cMotherModuleName,ButtonsBasis+"KOPIEREN"));
		
		oBedienPanel2.add(oBasicBedienpanel);
		
		oColWithPanels.add(oBedienPanel2);
		
		/*
		 * die liste leer initialisieren
		 */
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oComponentMAP_FSK, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(3);
		this.oBasicBedienpanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
		
		this.add(oColWithPanels);
		//this.add_In_ContainerEX(this.oNavigationList,new Extent(FS_CONST.MASK_WIDTH-20),new Extent(FS_CONST.MASK_HEIGHT-350), null);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);

	}

	public E2_NavigationList get_oNavigationList()
	{
		return oNavigationList;
	}


	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ADRESS_ID(String cID_ADRESSE_UF) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_FSK.get_oSQLFieldMAP().get(_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS)).set_cRestrictionValue_IN_DB_FORMAT(cID_ADRESSE_UF);
			//dann die selektoren fuer die jeweilge Adresse einstellen
			this.oListSelektor.Refresh(cID_ADRESSE_UF);
			this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_DYNAMIC();
			
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
	private class ownADDON_Buttons_Status_AGENT extends XX_ADDON_Buttons_Status_AGENT {
		public boolean set_Status(String cActualStatus) throws myException  {
			FSK__FULL_MASK_DAUGHTER_TP_KOSTEN_LIST_CONTAINER oThis = FSK__FULL_MASK_DAUGHTER_TP_KOSTEN_LIST_CONTAINER.this;
			
			E2_RecursiveSearchButtons oSearchButtons = new E2_RecursiveSearchButtons(oThis.oModulContainerMASK_ADRESS.get_oRowForButtons());
			
			boolean bEnabled = true;
			
			if (cActualStatus.equals(XX_ADDON_Buttons_Status_AGENT.STATUS_EDIT) || 
				cActualStatus.equals(XX_ADDON_Buttons_Status_AGENT.STATUS_INPUT_NEW)) {
				bEnabled = false;
			}
				
			for (int i=0;i<oSearchButtons.get_vButtons().size();i++) {
				Component oTest = (Component) oSearchButtons.get_vButtons().get(i);
				if (oTest instanceof MyE2IF__Component) {
					((MyE2IF__Component)oTest).set_bEnabled_For_Edit(bEnabled);
				}
			}
			
			// dann die tabbed-panes auch abschalten
			MyE2_TabbedPane oTabbedPane = oThis.oModulContainerMASK_ADRESS.get_oTabbedPane();
			
			if (oTabbedPane != null) {
				if (!bEnabled) {
					oTabbedPane.set_disable_All_Tabs_ExceptActualTab();
				} else {
					oTabbedPane.set_enable_All_Tabs();
				}
			}
			
			return true;
		}
		
	}



	public FSK_ListComponentMAP get_oComponentMAP_FSK() {
		return oComponentMAP_FSK;
	}

	
}
