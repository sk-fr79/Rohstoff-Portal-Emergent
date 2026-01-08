package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonUpDown extends MyE2_Button
{
	
	private String cModulKenner =  	null;
	private String cTableName = 	null;
	private String cTableID = 		null;
	
	
	/**
	 * @param cMODUL_KENNER (kann null sein)
	 */
	public E2_ButtonUpDown(String cMODULKENNER, String TableName, String TableID, String cToolTipText)
	{
		super(E2_ResourceIcon.get_RI("attach_mini.png"), E2_ResourceIcon.get_RI("leer.png"));
		
		this.cTableName = TableName;
		this.cTableID = TableID;
		
		if (cMODULKENNER!=null) this.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,"ZUSATZDATEIEN"));
		
		this.setToolTipText(new MyE2_String(
				S.isEmpty(cToolTipText)?
				"Zusatzdateien für Dateiarchiv hoch/runterladen. Archiv-Bereich"
				:
				cToolTipText
				).CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		
	}
	

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		
		public ownActionAgent()
		{
			super();
		}



		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			E2_ButtonUpDown oThis = E2_ButtonUpDown.this;
			
			
			try
			{
				String cID = 			oThis.cTableID;
				String cTableName = 	oThis.cTableName;
				AM_BasicContainer oPopUp = new AM_BasicContainer(
																		cTableName,
																		cID,
																		oThis.cModulKenner,
																		true);
			
				oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien ..."));
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Download-Window: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}

	}
	
	
}
