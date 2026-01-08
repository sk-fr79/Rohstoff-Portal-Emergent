package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.GregorianCalendar;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;


public class BSA_P_MASK_BT_SetDateRange extends MyE2_Button
{

	private boolean bAktuellerMonat = true;
	
	/*
	 * liefert entweder den aktuellen oder den naechsten monate von anfang bis ende in die maskenfelder
	 */
	public BSA_P_MASK_BT_SetDateRange(boolean AktuellerMonat)
	{
		super();
		this.bAktuellerMonat = AktuellerMonat;
		
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
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_Button oButton = (MyE2_Button) bibE2.get_LAST_ACTIONEVENT().getSource();
			
			GregorianCalendar oCal = (GregorianCalendar)oButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			// componenten suchen
			MyE2_DB_TextField_DatePOPUP_OWN oTF_GueltigVon = (MyE2_DB_TextField_DatePOPUP_OWN)oButton.EXT().get_oComponentMAP().get__Comp("GUELTIG_VON");
			MyE2_DB_TextField_DatePOPUP_OWN oTF_GueltigBis = (MyE2_DB_TextField_DatePOPUP_OWN)oButton.EXT().get_oComponentMAP().get__Comp("GUELTIG_BIS");
			
			GregorianCalendar oDateStart = myDateHelper.Find_First_Day_OfMonth(oCal);;
			GregorianCalendar oDateEnd = myDateHelper.Find_Last_Day_OfMonth(oCal);;
			
			myDateHelper oHelpStart = new myDateHelper(oDateStart);
			myDateHelper oHelpEnd = new myDateHelper(oDateEnd);
			
			oTF_GueltigVon.set_cActualMaskValue(oHelpStart.get_cDateFormatForMask());
			oTF_GueltigBis.set_cActualMaskValue(oHelpEnd.get_cDateFormatForMask());
		}
	}
	
}
