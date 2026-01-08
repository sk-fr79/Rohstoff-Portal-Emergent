package panter.gmbh.basics4project;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES.RANGE_KEY;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Container.IF_HasBasicModuleContainerSettings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.SCAN_DESCRIPTION_IF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.SCAN_POPUP_Button_Generic;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_bt_New_USER_REMINDERS;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR.CAL__BUTTON_StartCalendar;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.GLOBAL_REPORTS.REPORT__BUTTON_StartReports;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2__Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MESSAGE_STARTER.BUTTON_MESSAGE_Start;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.BUTTON_MODULVERWALTUNG;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS.TODO__BUTTON_StartTodoList;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.WORKFLOW_STARTER.BUTTON_WORKFLOW_Start;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG.FP__CONTAINER_ADDON_FahrtErfassung;

public class Project_BasicModuleContainer extends E2_BasicModuleContainer 
{

	private BUTTON_MODULVERWALTUNG  oBUTTON_MODUL_VERWALTUNG = null;
	
	public Project_BasicModuleContainer(String cMODULEIDENTIFIER) 
	{
		super();
		this.set_MODUL_IDENTIFIER(cMODULEIDENTIFIER);
		
		this.oBUTTON_MODUL_VERWALTUNG = new BUTTON_MODULVERWALTUNG(this);
		
		this.add_AddonComponent(this.oBUTTON_MODUL_VERWALTUNG);
		
		//2012-02-13: neuer button fuer implementierungen des basicmodulcontainer-setting-interfaces
		if (this instanceof IF_HasBasicModuleContainerSettings)
		{
			try
			{
				this.add_AddonComponent((IF_BasicModuleContainer_ADD_ON_Component)(((IF_HasBasicModuleContainerSettings)this).get_oBasicModuleContainerSettings()).get_oButton4Start());
			}
			catch (myException e)
			{
				e.printStackTrace();
			}
		}
		
		this.add_AddonComponent(new CAL__BUTTON_StartCalendar(this));
		this.add_AddonComponent(new REPORT__BUTTON_StartReports(this));
		this.add_AddonComponent(new TODO__BUTTON_StartTodoList(this));
		this.add_AddonComponent(new BUTTON_WORKFLOW_Start(this));
		this.add_AddonComponent(new BUTTON_MESSAGE_Start(this));
		this.add_AddonComponent(new FP__CONTAINER_ADDON_FahrtErfassung(this));
		
		try {
			this.add_AddonComponent(new ownButton4Scan());
		} catch (Exception e){
			e.printStackTrace();
		}

		try {
			this.add_AddonComponent(new REM_bt_New_USER_REMINDERS());
		} catch (myException e) {
			e.printStackTrace();
		}

		
		//this.add_AddonComponent(new HELP_BUTTON_FOR_MODULES(this));
		
		//2015-10-02: neuer help-button
		//this.add_AddonComponent(new HAD_Button(this));
		
		//20180905: neuer hilfebutton
		this.add_AddonComponent(new HELP2__Button(this));

		 
	}

	private class ownButton4Scan extends MyE2_Grid implements IF_BasicModuleContainer_ADD_ON_Component 	{

		private String modulKenner = null;
//		private RECLIST_SCANNER_SETTINGS_SPECIAL  rlScannerSettingsThisUserAllModules = null;
		
		public ownButton4Scan() throws myException  {
			super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			this.build();
		}

		
		public void build() throws myException {
			this.removeAll();
			SCAN_POPUP_Button_Generic scanButton = new SCAN_POPUP_Button_Generic(new ownScanDescripter());
			scanButton.setToolTipText(new MyE2_String("Scannen von Dokumenten zum Download ...").CTrans());
			this.add(scanButton, E2_INSETS.I(0,0,0,0));
		}
		
		
		@Override
		public boolean get_bIsShown() throws myException	{
			return true;
		}

		@Override
		public void set_cMODULKENNER(String cModulKenner){
			this.modulKenner=cModulKenner;
		}

		@Override
		public String get_cMODULKENNER(){
			return this.modulKenner;
		}

		@Override
		public void add_cZusatzMODULKENNER(String cModulKenner)	{
		}
	}
	
	
	
	
	

	public BUTTON_MODULVERWALTUNG get_oBUTTON_MODUL_VERWALTUNG()
	{
		return oBUTTON_MODUL_VERWALTUNG;
	}
	
	
	//scanner-descriptor fuer die Scan-to-download-buttons
	private class ownScanDescripter implements SCAN_DESCRIPTION_IF {

		@Override
		public String get_ArchivBaseTable() throws myException {
			return null;
		}
	
		@Override
		public String get_ArchiveIdTable() throws myException {
			return null;
		}
	
		@Override
		public E2_NavigationList get_ArchivNaviList() throws myException {
			return null;
		}
	
		@Override
		public boolean get_ScanIs4Download() {
			return true;
		}
	
		@Override
		public RANGE_KEY get_RANGE_KEY() throws myException {
			return RANGE_KEY.ATTACHMENT_ALL_OTHERS;
		}
	
}

	

}
