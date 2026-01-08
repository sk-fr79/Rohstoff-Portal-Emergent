package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.exceptions.myException;

public class HAD_Button extends MyE2_Button  implements IF_BasicModuleContainer_ADD_ON_Component{
	
	private String 						cMODULE_KENNER = 	"";
	
	private MyE2_String   				cTitelString = null;

	
	public HAD_Button(E2_BasicModuleContainer ContainerCalling) {
		super(E2_ResourceIcon.get_RI("help.png"),true);
		this.add_oActionAgent(new ownActionAgent());

		this.cMODULE_KENNER = ContainerCalling.get_MODUL_IDENTIFIER();

		cTitelString = new MyE2_String("Hilfe für das Modul ... ",true,this.get_uebersetzte_modulKenner(this.cMODULE_KENNER),false);
		this.setToolTipText(cTitelString.CTrans());

	}
	
	
	/**
	 * globale ausfuehrung
	 */
	public HAD_Button() 
	{
		super(E2_ResourceIcon.get_RI("help.png"),true);
		this.add_oActionAgent(new ownActionAgent());

		this.cMODULE_KENNER = E2_MODULNAME_ENUM.MODUL.MODUL_KENNER_PROGRAMM_WIDE.get_callKey();

		cTitelString = new MyE2_String("Globale Hilfe (Modulübergreifend)",true);
		this.setToolTipText(cTitelString.CTrans());
	}
	
	private String get_uebersetzte_modulKenner(String ModulKenner) {
		String c_rueck = ModulKenner;
		
		MODUL mod = E2_MODULNAME_ENUM.find_Corresponding_Enum(cMODULE_KENNER);
		if (mod != null) {
			if (mod.get_userInfo()!=null) {
				c_rueck = mod.get_userInfo().CTrans();
			}
		}
		
		return c_rueck;
	}
	
	
	private class ownActionAgent extends XX_ActionAgent {

		public void executeAgentCode(ExecINFO oExecInfo) {
			HAD_Button oThis = HAD_Button.this;
			
			try	{
				HAD__BasicModulContainer oModulContainer = new HAD__BasicModulContainer(oThis.cMODULE_KENNER);
				
				oModulContainer.CREATE_AND_SHOW_POPUPWINDOW(	new Extent(HAD___CONST.WindowWIDTH),
																new Extent(HAD___CONST.WindowHEIGTH),
																oThis.cTitelString);
			}	catch (myException ex)	{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Laden des Hilfefensters ..."));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
		
	}


	public boolean get_bIsShown()
	{
		return true;
	}

	public void set_cMODULKENNER(String cModulKenner)
	{
		this.cMODULE_KENNER = cModulKenner;
	}

	public String get_cMODULKENNER()
	{
		return this.cMODULE_KENNER;
	}

	public void add_cZusatzMODULKENNER(String cModulKenner)
	{
	}

}
