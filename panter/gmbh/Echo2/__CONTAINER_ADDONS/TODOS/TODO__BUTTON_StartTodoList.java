package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class TODO__BUTTON_StartTodoList extends MyE2_Button implements IF_BasicModuleContainer_ADD_ON_Component
{
	private String 						cMODULKENNER = null; 
	private E2_BasicModuleContainer 	oContainerCalling = null;
	
	
	/*
	 * hier koennen z.b. Masken-Tochterkomponenten benannt werden, die eine Druckdefinition bekommen koennen
	 */
	private Vector<String>  vZusatzModuleKenner = new Vector<String>();
	
	
	public TODO__BUTTON_StartTodoList(E2_BasicModuleContainer  ocontainer)
	{
		super(E2_ResourceIcon.get_RI("todos.png"));
		this.oContainerCalling = ocontainer;
		
		this.set_cMODULKENNER(this.oContainerCalling.get_MODUL_IDENTIFIER());
		
		this.add_oActionAgent(new ownActionAgentStartMASK());
		
		MyE2_String oStringToolTips = new MyE2_String("TODOs verwalten  ... ");
		oStringToolTips.addUnTranslated(cMODULKENNER);
		this.setToolTipText(oStringToolTips.CTrans());
	}

	
	
	/**
	 * @throws myException 
	 * 
	 */
	public boolean get_bIsShown() throws myException
	{
		return bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_TODOS_YES();
	}

	public void set_cMODULKENNER(String cModulKenner)
	{
		this.cMODULKENNER = cModulKenner;
	}

	public String get_cMODULKENNER()
	{
		return this.cMODULKENNER;
	}

	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartMASK extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			/*
			 * zuerst pruefen, ob es einen modulkenner gibt, wenn ja dann popup anzeigen, sonst nicht, da bei manchen
			 * zustaenden am anfang der modulkenner erst nach einfuegen des E2_ROHSTOFF_SetButtonAccess erfolgt, und damit
			 * bei der ersten anzeige noch nicht bekannt ist
			 */
			TODO__BUTTON_StartTodoList oThis = TODO__BUTTON_StartTodoList.this;
			
			oThis.set_cMODULKENNER(oThis.oContainerCalling.get_MODUL_IDENTIFIER());

			if (bibALL.isEmpty(oThis.cMODULKENNER))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Das Modul hat keinen Modulkenner !! Deswegen ist eine Terminplanung nicht möglich !"));
				return;
			}
			new TODO_LIST_BasicModuleContainer();
		}
		
	}


	public void add_cZusatzMODULKENNER(String cModulKenner)
	{
		this.vZusatzModuleKenner.add(cModulKenner);
	}

}
