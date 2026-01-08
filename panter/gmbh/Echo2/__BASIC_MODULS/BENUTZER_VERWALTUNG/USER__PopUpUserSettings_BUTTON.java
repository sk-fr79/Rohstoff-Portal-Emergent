package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class USER__PopUpUserSettings_BUTTON extends MyE2_Button
{
	private E2_NavigationList oNaviList  = null;
	
	public USER__PopUpUserSettings_BUTTON(E2_NavigationList NaviList)
	{
		super(new MyE2_String("EINSTELLUNGEN Kopieren"));
		this.oNaviList = NaviList;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());  //2014-02-17: neu: Benutzung fuer alle USER moeglich, aber nur fuer den eigenen Account

	}

	
	private class ownActionAgent extends XX_ActionAgent
	{


		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			new USER__PopUpUserSettings_CONTAINER(USER__PopUpUserSettings_BUTTON.this.oNaviList).CREATE_AND_SHOW_POPUPWINDOW(
					new Extent(800), new Extent(600), new MyE2_String("Benutzereigenschaften kopieren"));
			
		}
		
	}
}
