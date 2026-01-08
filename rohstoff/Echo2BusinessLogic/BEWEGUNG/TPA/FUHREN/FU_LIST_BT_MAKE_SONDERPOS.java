package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;


public class FU_LIST_BT_MAKE_SONDERPOS extends MyE2_Button
{
	private E2_NavigationList 		oNaviList = null;

	public FU_LIST_BT_MAKE_SONDERPOS(E2_NavigationList NaviList)
	{
		super(new MyE2_String("Buchung Sonderpositionen"));
		this.oNaviList = NaviList;

		this.add_GlobalAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "BUCHUNG_SONDER_POSITIONEN");
		
		this.add_IDValidator(new validDarfSonderbuchung());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			Vector<String> vIDs = FU_LIST_BT_MAKE_SONDERPOS.this.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte eine Fuhre auswählen !")));
			}
			else
			{
				execInfo.get_MyActionEvent().make_ID_Validation_ADD_TO_Global_MV(vIDs);
				
			}
			
			
		}
		
	}
	
	private class validDarfSonderbuchung extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
		{
			return new MyE2_MessageVector();
		}

		
		public MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			RECORD_VPOS_TPA_FUHRE oMapFuhre = new RECORD_VPOS_TPA_FUHRE(cID_Unformated);
			
			if (oMapFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre().get_vKeyValues().size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Transport-Position hat keine Sonderpositionen hinterlegt "));
			}
			
			if (oMapFuhre.is_IST_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde storniert !"));
			}

			if (oMapFuhre.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde gelöscht !"));
			}

			
			
			
			
			return oMV;
		}

	}
	

	
}
