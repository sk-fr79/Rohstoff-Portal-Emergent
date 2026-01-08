package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

public class FPT_BUTTON_OpenSortPanel extends MyE2_Button
{
	private FU_LIST_ModulContainer 		 		ModulContainer = null;
	private MyE2_SelectField  					oSelectLKW = null;
	private E2_DateBrowser  					oDateBrowser = null;
	
	public FPT_BUTTON_OpenSortPanel(FU_LIST_ModulContainer 			Modulcontainer, 
									MyE2_SelectField  				SelectLKW, 
									E2_DateBrowser  				DateBrowser)
	{
		super(E2_ResourceIcon.get_RI("up_low.png"));
		this.ModulContainer = Modulcontainer;
		this.oDateBrowser = DateBrowser;
		this.oSelectLKW = SelectLKW;
		
		this.add_oActionAgent(new actionAgentOpenSortPanel());
		this.setToolTipText(new MyE2_String("Fenster zur Sortierung des Fahrplans öffnen").CTrans());
		
	}

	
	/*
	 * action nach oeffnen des fahrplans
	 */
	private class actionAgentOpenSortPanel extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FPT_BUTTON_OpenSortPanel oThis = FPT_BUTTON_OpenSortPanel.this;
			
			String cID_MASCHINE_LKW = new MyLong(oThis.oSelectLKW.get_ActualWert(),new Long(0), new Long(0)).get_cUF_LongString();
			String cDatum			= oThis.oDateBrowser.DO_EvaluateActualTextAndGiveBackFormatedText("yyyy-MM-dd"); 
			if (cID_MASCHINE_LKW.equals("0") || cDatum == null )
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte LKW und Datum des Fahrplans definieren !"));
			}
			else
			{
				new ownSortPanel(cID_MASCHINE_LKW,cDatum);
			}	
		}

		private class ownSortPanel extends FP__SortPanel_Fahrplan
		{
			
			public ownSortPanel(String maschinen_LKW, String date_in_YYYYMMDD) 	throws myException
			{
				super(maschinen_LKW, date_in_YYYYMMDD);
			}
			
			@Override
			public void do_action_after_Sorting(String cid_maschinen_lkw,String date_YYYYMMDD) throws myException
			{
				FPT_BUTTON_OpenSortPanel.this.ModulContainer.get_oNavList()._REBUILD_ACTUAL_SITE(null);
			}
			
		}
		
	}
	
	

	
}
