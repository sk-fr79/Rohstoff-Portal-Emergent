package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/*
 * actionagent mit einem SQL-Statement mit eingefuegtem #ID#.
 * dieses statement wird fuer jeden datensatz, der selektert wird,
 * als transaktion ausgefuehrt
 */
public abstract class ButtonActionAgentMultiSQLStatement extends XX_ActionAgent
{
	private E2_NavigationList 	oNavigationList = 	null;
	private MyE2_String 		cNameOfAction = 	null;
	private Vector<String> 		vSQL_Statements = 	new Vector<String>();

	
	/*
	 *	falls der button einen MyIntegrityWatcherVECTOR definiert hat, werden hier fuer jede id eine 
	 *  kopie angelegt und fuer den execute-vorgang vorgehalten (ein Vector von Vectoren)
	 */
	// private MyIntegrityWatcherVECTOR	vIntegrityWatcher = new MyIntegrityWatcherVECTOR();

	
	public ButtonActionAgentMultiSQLStatement(	MyE2_String 		actionName,
												String 				cSQL_MIT_Platzhalter,
												E2_NavigationList 	onavigationList) throws myException
	{
		super();
		this.oNavigationList = 	onavigationList;
		this.cNameOfAction = 	actionName;
		this.vSQL_Statements.add(cSQL_MIT_Platzhalter);
		
		if (bibALL.isEmpty(cSQL_MIT_Platzhalter))
			throw new myException("ButtonActionAgentMultiSQLStatement:Constructor:Empty SQL_Statement not allowed !!!");
		
		if (cSQL_MIT_Platzhalter.indexOf("#ID#")<0)
			throw new myException("ButtonActionAgentMultiSQLStatement:Constructor: SQL_Statement must containe #ID#!!!");
		
		
	}

	
	
	public void ADD_Statement(String cSQL_MIT_Platzhalter) throws myException
	{
		if (bibALL.isEmpty(cSQL_MIT_Platzhalter))
			throw new myException("ButtonActionAgentMultiSQLStatement:Constructor:Empty SQL_Statement not allowed !!!");
		
		if (cSQL_MIT_Platzhalter.indexOf("#ID#")<0)
			throw new myException("ButtonActionAgentMultiSQLStatement:Constructor: SQL_Statement must containe #ID#!!!");
	
		this.vSQL_Statements.add(cSQL_MIT_Platzhalter);
	}
	
	
	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		Vector<String> vIDsToDoSQL = this.oNavigationList.get_vSelectedIDs_Unformated();
		
		if (vIDsToDoSQL.size() == 0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+new MyE2_String("  Bitte mindestens einen Datensatz zum Ändern auswählen !!").CTrans(),false),true);
		}
		else
		{
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(vIDsToDoSQL);
			
			if (bibMSG.get_bIsOK())
			{	

				bibMSG.add_MESSAGE(this.CheckIdToChange(vIDsToDoSQL));
				
				if (bibMSG.get_bIsOK())
				{
					MyE2_String cActionHinweis = new MyE2_String("Änderung durchführen ?");
					if (this.cNameOfAction != null && !this.cNameOfAction.CTrans().trim().equals(""))
						cActionHinweis =new MyE2_String(this.cNameOfAction.CTrans(),false);
					
					E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
														new MyE2_String("Sicherheitsabfrage"),
														cActionHinweis,
														new MyE2_String(""),
														new MyE2_String("JA"),
														new MyE2_String("NEIN"),
														new Extent(300),
														new Extent(200)
														);
					oConfirm.set_ActionAgentForOK(new ownActionAgentConfirm_SQL_Exceution(vIDsToDoSQL,this.oNavigationList));
					oConfirm.show_POPUP_BOX();
				}
			}
		}
	}

	
	
	
	/**
	 * @return s Vector of MyString-object with errormessages
	 * methode kann ueberschrieben werden, wenn eine pruefung auf erlaubnis erfolgen soll
	 */
	public abstract MyE2_MessageVector CheckIdToChange(Vector<String> vID_UnformatedToDelete) throws myException;
	
	
	
	private class ownActionAgentConfirm_SQL_Exceution extends XX_ActionAgent
	{
		
		private Vector<String>		vIDs_To_Change = null;
		private E2_NavigationList 	oNaviList = null;
		
		public ownActionAgentConfirm_SQL_Exceution(Vector<String> vIds_to_delete, E2_NavigationList onaviList)
		{
			super();
			this.vIDs_To_Change = vIds_to_delete;
			this.oNaviList = onaviList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			try
			{
				Vector<String> vStatementsPerID = ButtonActionAgentMultiSQLStatement.this.vSQL_Statements;
				Vector<String> vALL_SQL = new Vector<String>();
				
				for (int i=0;i<this.vIDs_To_Change.size();i++)
				{
					String cID_To_Change = (String)this.vIDs_To_Change.get(i);
					
					for (int k=0;k<vStatementsPerID.size();k++)
					{
						String cSQL = (String)vStatementsPerID.get(k);
						vALL_SQL.add(bibALL.ReplaceTeilString(cSQL,"#ID#",cID_To_Change));
					}
				}

				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vALL_SQL,true));
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensätze wurden geändert. IDs: "+bibALL.Concatenate(this.vIDs_To_Change,",", null)), false);
					oNaviList._REBUILD_ACTUAL_SITE(null);
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("ButtonActionAgentMultiSQLStatement:ownActionAgentConfirm_SQL_Exceution:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	

	

}
