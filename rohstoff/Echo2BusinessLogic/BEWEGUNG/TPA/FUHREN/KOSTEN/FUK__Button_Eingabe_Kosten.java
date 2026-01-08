package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class FUK__Button_Eingabe_Kosten extends MyE2_ButtonInLIST
{

	public FUK__Button_Eingabe_Kosten()
	{
		super(E2_ResourceIcon.get_RI("kosten.png"),true);
		
		this.add_GlobalAUTHValidator_AUTO("EINGABE_KOSTEN_ZU_FUHRE");
		
		this.setToolTipText(new MyE2_String("Kosten und Gebühren einer Fuhre erfassen ").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		
	}
	

	public abstract String 							get_cID_VPOS_TPA_FUHRE_UF() 			throws myException;
	public abstract XX_ActionAgentWhenCloseWindow  	get_ActionAgent4Close_Kosten_Window(E2_BasicModuleContainer oContainerToClose)  	throws myException;
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			FUK__Button_Eingabe_Kosten oThis = (FUK__Button_Eingabe_Kosten)oExecInfo.get_MyActionEvent().getSource();
			
			String cID_VPOS_TPA_FUHRE = oThis.get_cID_VPOS_TPA_FUHRE_UF();
			
			if (S.isEmpty(cID_VPOS_TPA_FUHRE))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ich konnte keine Fuhre finden, um Kosten zu erfassen !!")));
				return;
			}
			
			FUK__LIST_BasicModule_Inlay oContainer = new FUK__LIST_BasicModule_Inlay(E2_MODULNAMES.NAME_MODUL_ERFASSUNG_FUHRENKOSTEN,true);
			
			//die componentmap der aktuellen zeile mitgeben
			oContainer.set_oComponentMapOfCalling_FUHREN_NaviListRow(oThis.EXT().get_oComponentMAP());
			
			oContainer.set_ID_From_Calling_Record(cID_VPOS_TPA_FUHRE);
			oContainer.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");   
			
			XX_ActionAgentWhenCloseWindow oCloseAction = oThis.get_ActionAgent4Close_Kosten_Window(oContainer);
			if (oCloseAction != null)
			{
				oContainer.add_CloseActions(oThis.get_ActionAgent4Close_Kosten_Window(oContainer));
			}
//			{
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException
//				{
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Test"));
//				}
//			});
			
			oContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(400), new MyE2_String("Erfassung von Fuhrenkosten"));
		}
	}
	
	

	
}
