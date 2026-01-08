package panter.gmbh.basics4project;

import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR.CAL__BUTTON_StartCalendar;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.GLOBAL_REPORTS.REPORT__BUTTON_StartReports;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2__Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MESSAGE_STARTER.BUTTON_MESSAGE_Start;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.BUTTON_MODULVERWALTUNG;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS.TODO__BUTTON_StartTodoList;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.WORKFLOW_STARTER.BUTTON_WORKFLOW_Start;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG.FP__CONTAINER_ADDON_FahrtErfassung;

public class Project_BasicModuleContainer_MASK extends E2_BasicModuleContainer_MASK
{
	private BUTTON_MODULVERWALTUNG  			oBUTTON_MODULVERWALTUNG= null;
	
	
	public Project_BasicModuleContainer_MASK(String cMODULEIDENTIFIER) 
	{
		super();
		this.set_MODUL_IDENTIFIER(cMODULEIDENTIFIER);
		this.add_AddonComponent(this.oBUTTON_MODULVERWALTUNG=new BUTTON_MODULVERWALTUNG(this));
		this.add_AddonComponent(new CAL__BUTTON_StartCalendar(this));
		this.add_AddonComponent(new REPORT__BUTTON_StartReports(this));
		this.add_AddonComponent(new TODO__BUTTON_StartTodoList(this));
		this.add_AddonComponent(new BUTTON_WORKFLOW_Start(this));
		this.add_AddonComponent(new BUTTON_MESSAGE_Start(this));
		this.add_AddonComponent(new FP__CONTAINER_ADDON_FahrtErfassung(this));

		//2015-10-08: neues hilfemodul
		//this.add_AddonComponent(new HELP_BUTTON_FOR_MODULES(this));
		//this.add_AddonComponent(new HAD_Button(this));
		
		//2018-09-05: neues hilfemodul
		this.add_AddonComponent(new HELP2__Button(this));

	} 


	public BUTTON_MODULVERWALTUNG getOBUTTON_MODULVERWALTUNG()
	{
		return oBUTTON_MODULVERWALTUNG;
	}

	
}
