package panter.gmbh.Echo2.AgentsAndValidators;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainerWith_HTTP_PANE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ActionAgentOpenURL extends XX_ActionAgent
{
	private String  			cFensterTitel = null;
	private String   			cURL = null;
	private boolean             bOwnBrowserWindow = false;
	private String   			WindowOpenAttributes = "width=800,height=600, resizable=yes, scrollbars=yes, menubar=yes, toolbar=yes";

	public ActionAgentOpenURL(String FensterTitel, String URL, boolean OwnBrowserWindow, String windowOpenAttributes)
	{
		super();
		this.cFensterTitel = FensterTitel;
		this.cURL = URL;
		this.bOwnBrowserWindow = OwnBrowserWindow;
		if (S.isFull(windowOpenAttributes))
		{
			WindowOpenAttributes = windowOpenAttributes;
		}
	}

	public ActionAgentOpenURL(String FensterTitel, String URL, boolean OwnBrowserWindow)
	{
		super();
		this.cFensterTitel = FensterTitel;
		this.cURL = URL;
		this.bOwnBrowserWindow = OwnBrowserWindow;
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		if (!bibALL.isEmpty(this.cURL))
		{
			String cWindowAttribs = this.WindowOpenAttributes;
			//pruefen, ob die mandanten-zusatzvariable einen wert: BROWSER_OPEN_WINDOW_COMMAND enthaelt
			String cAlternativOpenString = __RECORD_MANDANT_ZUSATZ.get___Value("BROWSER_OPEN_WINDOW_COMMAND", "", "");
			if (S.isFull(cAlternativOpenString))
			{
				cWindowAttribs = cAlternativOpenString;
			}
			
			
			
			if (bOwnBrowserWindow)
			{
				ApplicationInstance.getActive().enqueueCommand(
					     new BrowserOpenWindowCommand(cURL,S.NN(this.cFensterTitel, "<unbekannter Titel>"),cWindowAttribs));
				
			}
			else
			{
				ownBasicContainer4Internet oPopUp = new ownBasicContainer4Internet(800,600);
				oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Internetansicht ..."));
				oPopUp.showWebsite(cURL);
			}
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Internet-Adresse ist leer !"));
		}
	}
	
	
	// eigene klasse, damit das abspeichern der benutzerdefinierten groesse geht
	private class ownBasicContainer4Internet extends E2_BasicModuleContainerWith_HTTP_PANE
	{
		public ownBasicContainer4Internet(int width, int height)
		{
			super(width, height);
		}
	}
	

}
