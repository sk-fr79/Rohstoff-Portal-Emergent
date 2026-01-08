package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/*
 * actionagent mit einer aktionsmethode, die nach der sicherheitsabfrage ausgefuehrt wird
 */
public abstract class ButtonActionAgentMultiActionWithConfirmPOPUP extends XX_ActionAgent
{
	private E2_NavigationList 	oNavigationList = null;
	private MyE2_Button			oOwnButton = null;
	private MyE2_String			cNameOfAction = null;
	private MyE2_String			cStringFirstLineInConfirmbox = null;
	private MyE2_String   		cStringSecondLineInConfirmbox = null;
	
	private Extent  			oWidthConfirmBox = new Extent(400);
	private Extent  			oHeightConfirmBox = new Extent(300);
	
	
	public ButtonActionAgentMultiActionWithConfirmPOPUP(	MyE2_String 		actionName,
														E2_NavigationList 	onavigationList,
														MyE2_Button			oownButton,
														MyE2_String			StringFirstLineInConfirmbox, 
														MyE2_String 		StringSecondLineInConfirmbox, 
														Extent 				WidthOfConfirmBox, 
														Extent 				HeightOfConfirmBox)
	{
		super();
		this.oNavigationList = 				onavigationList;
		this.oOwnButton = 					oownButton;
		this.cNameOfAction = 				actionName;
		this.cStringFirstLineInConfirmbox = 	StringFirstLineInConfirmbox;
		if (this.cStringFirstLineInConfirmbox == null)
			this.cStringFirstLineInConfirmbox = new MyE2_String("Änderung durchführen ?");
		
		this.cStringSecondLineInConfirmbox = 	StringSecondLineInConfirmbox;
		if (this.cStringSecondLineInConfirmbox == null)
			this.cStringSecondLineInConfirmbox = new MyE2_String("");
		
		if (WidthOfConfirmBox != null)
			this.oWidthConfirmBox = WidthOfConfirmBox;
		
		if (HeightOfConfirmBox != null)
			this.oHeightConfirmBox = HeightOfConfirmBox;
		
	}

	public abstract void do_ChangeAction(Vector<String> vIDsSelected,E2_NavigationList oNaviList) throws myException;
	
	
	
	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		Vector<String> vIDsToDoSQL = this.oNavigationList.get_vSelectedIDs_Unformated();
		
		
		if (vIDsToDoSQL.size() == 0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(this.cNameOfAction.CTrans()+new MyE2_String("  Bitte mindestens einen Datensatz auswählen !!").CTrans(),false)));
		}
		else
		{

	
			
			// die validatoren pruefen
			for (int i=0;i<vIDsToDoSQL.size();i++)
			{
				String cID_ToDo_SQL = (String)vIDsToDoSQL.get(i);
				bibMSG.add_MESSAGE(this.oOwnButton.valid_IDValidation(bibALL.get_Vector(cID_ToDo_SQL)));
			}
			
			if (bibMSG.get_bIsOK())
			{	

				bibMSG.add_MESSAGE(this.CheckIdToChange(vIDsToDoSQL));
				
				if (bibMSG.get_bIsOK())
				{
					E2_ConfirmBasicModuleContainer oConfirm = this.create_ConfirmBasicModuleContainer(
																		new MyE2_String("Sicherheitsabfrage"),
																		this.cStringFirstLineInConfirmbox,
																		this.cStringSecondLineInConfirmbox,
																		new MyE2_String("JA"),
																		new MyE2_String("NEIN"),
																		this.oWidthConfirmBox,
																		this.oHeightConfirmBox
																		);
					oConfirm.set_ActionAgentForOK(new ownActionAgentConfirm_Execution(vIDsToDoSQL,this.oNavigationList));
					oConfirm.set_bVisible_Row_For_Messages(true);
					oConfirm.show_POPUP_BOX();
					oConfirm.set_SplitPixelsFromBottom(70);
				}
			}
		}
	}

	/*
	 * damit kann in der rufenden einheit eine eigene klasse erzeigt werden, deren 
	 * groesse damit individuell abspeicherbar ist
	 */
	public abstract E2_ConfirmBasicModuleContainer create_ConfirmBasicModuleContainer(	MyE2_String windowtitle,
																						MyE2_String texttitle, 
																						MyE2_String innerTextblock,
																						MyE2_String labelOKButton, 
																						MyE2_String labelCancelButton,
																						Extent width, 
																						Extent height) throws myException;
	
	
//	private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
//	{
//
//		public ownConfirmContainer(MyE2_String windowtitle,
//				MyE2_String texttitle, MyE2_String innerTextblock,
//				MyE2_String labelOKButton, MyE2_String labelCancelButton,
//				Extent width, Extent height)
//		{
//			super(windowtitle, texttitle, innerTextblock, labelOKButton, labelCancelButton,
//					width, height);
//		}
//		
//	}
	
	
	/**
	 * @return s Vector of MyString-object with errormessages
	 * methode kann ueberschrieben werden, wenn eine pruefung auf erlaubnis erfolgen soll
	 */
	public abstract MyE2_MessageVector CheckIdToChange(Vector<String> vID_UnformatedToDelete);

	
	
	private class ownActionAgentConfirm_Execution extends XX_ActionAgent
	{
		private Vector<String> 		vIDs_To_Change = null;
		private E2_NavigationList 	oNaviList = null;
		
		
		public ownActionAgentConfirm_Execution(Vector<String> vIds_to_execute, E2_NavigationList onaviList)
		{
			super();
			this.vIDs_To_Change = vIds_to_execute;
			this.oNaviList = onaviList;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				ButtonActionAgentMultiActionWithConfirmPOPUP.this.do_ChangeAction(this.vIDs_To_Change,this.oNaviList);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("ButtonActionAgentMultiSQLStatement:ownActionAgentConfirm_SQL_Exceution:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	

	

}
