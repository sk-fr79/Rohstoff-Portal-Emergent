package panter.gmbh.Echo2.components;

import java.util.GregorianCalendar;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;


public class E2_Button_SetDateRange extends MyE2_Button
{

	private boolean bAktuellerMonat = true;
	private MyE2IF__DB_Component oTF_GueltigVon = null;;
	private MyE2IF__DB_Component oTF_GueltigBis = null;

	
	
	/*
	 * liefert entweder den aktuellen oder den naechsten monate von anfang bis ende in die maskenfelder
	 */
	public E2_Button_SetDateRange(boolean AktuellerMonat,MyE2IF__DB_Component TF_GueltigVon,MyE2IF__DB_Component TF_GueltigBis)
	{
		super();
		
		this.setStyle(MyE2_Button.StyleTextButton());
		
		if (AktuellerMonat)
		{
			this.setToolTipText(new MyE2_String("Den Datumsbereich auf Anfang bis Ende des aktuellen Monats setzen").CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Den Datumsbereich auf Anfang bis Ende des folgenden Monats setzen").CTrans());

		}
		
		
		this.bAktuellerMonat = AktuellerMonat;
		this.oTF_GueltigVon = TF_GueltigVon;
		this.oTF_GueltigBis = TF_GueltigBis;
		
		GregorianCalendar oCal = new GregorianCalendar();
		
		if (!this.bAktuellerMonat)
			oCal = myDateHelper.Find_First_Day_NextMonth(oCal);
		
		try
		{
			myDateHelper oDateHelper = new myDateHelper(oCal);
		
			String cButtonText = oDateHelper.get_cDateFormatForMask().substring(3);
			this.set_Text(cButtonText);
			this.EXT().set_O_PLACE_FOR_EVERYTHING(oCal);

			this.add_oActionAgent(new ownActionAgent());
			
		}
		catch (myException ex)
		{
			this.set_Text("@@@ERROR@@@");
		}
		
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_Button oButton = (MyE2_Button) bibE2.get_LAST_ACTIONEVENT().getSource();
			
			GregorianCalendar oCal = (GregorianCalendar)oButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			try
			{
				GregorianCalendar oDateStart = myDateHelper.Find_First_Day_OfMonth(oCal);;
				GregorianCalendar oDateEnd = myDateHelper.Find_Last_Day_OfMonth(oCal);;
				
				myDateHelper oHelpStart = new myDateHelper(oDateStart);
				myDateHelper oHelpEnd = new myDateHelper(oDateEnd);
				
				E2_Button_SetDateRange.this.oTF_GueltigVon.set_cActualMaskValue(oHelpStart.get_cDateFormatForMask());
				E2_Button_SetDateRange.this.oTF_GueltigBis.set_cActualMaskValue(oHelpEnd.get_cDateFormatForMask());
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error BSK_P_MaskButton_SetDateRange:ownactionagent: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
	
	
}
