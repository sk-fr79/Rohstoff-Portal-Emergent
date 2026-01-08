package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class HELP2__Button extends E2_Button  implements IF_BasicModuleContainer_ADD_ON_Component {

	private E2_MODULNAME_ENUM.MODUL     module = null;
	
	
	public HELP2__Button(E2_BasicModuleContainer ContainerCalling) {
		super();
		
		this._image(E2_ResourceIcon.get_RI("help.png"),true);
		
		this.add_oActionAgent(new ownActionAgent());

		if (ContainerCalling!=null) {
			this.module = E2_MODULNAME_ENUM.find_Corresponding_Enum(ContainerCalling.get_MODUL_IDENTIFIER());
		}

		String hlpTxt = " (Global)"; 
		if (this.module!=null) {
			hlpTxt = module.get_userInfo().CTrans();
		}
		
		this.setToolTipText(S.ms("Hilfe für das Modul ... ").ut(hlpTxt).CTrans());
	}
	

	
	private class ownActionAgent extends XX_ActionAgent {

		public void executeAgentCode(ExecINFO oExecInfo) {
			try	{
				new HELP2_LIST_BasicModuleContainer(module).CREATE_AND_SHOW_POPUPWINDOW(
													new Extent(HELP2_CONST.HELP2_NUM_CONST.LISTPOPUP_WIDTH.getValue()),
													new Extent(HELP2_CONST.HELP2_NUM_CONST.LISTPOPUP_HEIGHT.getValue()), S.ms("Handbuch und Hilfetexte"));
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


	@Override
	public void set_cMODULKENNER(String cModulKenner) {
		this.module = E2_MODULNAME_ENUM.find_Corresponding_Enum(cModulKenner);
	}


	@Override
	public String get_cMODULKENNER() {
		if (this.module!=null) {
			return this.module.get_callKey();
		}
		return "";
	}


	@Override
	public void add_cZusatzMODULKENNER(String cModulKenner) {
	}


}
