package panter.gmbh.Echo2.__BASIC_MODULS.SESSIONMANAGEMENT;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

import com.sap.dbtech.jdbc.trace.Connection;

public class ModuleContainer_POPUP_SessionManagement extends Project_BasicModuleContainer
{
	private int WIDTH = 1000;
	private int HEIGHT = 600;
	
	private MyE2_Grid 		oGridForSessions =	new MyE2_Grid(11,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
	private MyE2_Grid 		oGridForInfos = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
	private MyE2_TabbedPane oTabbed	= 			new MyE2_TabbedPane(new Integer(HEIGHT-250));
	
	private MyE2_Label 		oLabelInvalid = new MyE2_Label("0");
	private MyE2_Label 		oLabelNull = 	new MyE2_Label("0");
	
	public ModuleContainer_POPUP_SessionManagement() throws myException 
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_SHOW_SESSION_MANAGEMENT);
		
		this.oTabbed.add_Tabb(new MyE2_String("Sessions verwalten"),this.oGridForSessions);
		this.oTabbed.add_Tabb(new MyE2_String("Informationen"),this.oGridForInfos);
		
		this.add(new E2_ComponentGroupHorizontal(0,	new MyE2_Label(new MyE2_String("Inaktive Sessions :")),
													this.oLabelInvalid,
													new MyE2_Label(new MyE2_String(" / NULL-Sessions :")),
													this.oLabelNull,
													new MyE2_Label(new MyE2_String(" / Eigene Session :")),
													new MyE2_Label(bibE2.get_CurrSession().getId()),
													new Button_RefreshSessionList(),
													E2_INSETS.I_0_0_10_0),E2_INSETS.I_10_10_10_10);
		this.add(new MyE2_Label(new MyE2_String("Im Moment aktive Sessions :")),E2_INSETS.I_10_10_10_10);
		this.add(oTabbed);
		
		this.build_Grid();
		this.build_grid2();
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(this.WIDTH),new Extent(this.HEIGHT),new MyE2_String("Sitzungsmanagement und Infos ..."));
	}
	

	
	private void build_Grid()
	{

		oGridForSessions.removeAll();
		
		oGridForSessions.add(new titleLabel(new MyE2_String("DEL")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("Locks")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("<>")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("Benutzer")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("Gestartet")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("zuletzt benutzt")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("Eigene")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("Servlet-Session-ID")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("DB-Connect-ID (Lock)")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("DB-Connect-ID (Trigger)")),E2_INSETS.I_2_2_10_2);
		oGridForSessions.add(new titleLabel(new MyE2_String("Mandant")),E2_INSETS.I_2_2_10_2);
		
		try
		{

			// die sammler-hashmap aus dem servlet-context rausholen
			HashMap hmSessions = (HashMap)bibE2.get_CurrSession().getServletContext().getAttribute("applications.users");
			
			Iterator iT = hmSessions.entrySet().iterator();
			
			int iCountInvalid = 0;
			int iCountNull    = 0;
			
			Vector<String> vControll = new Vector<String>();
			
		    // in der hashmap ist fuer jede neue session aus createSession eine WeakReference vorhanden
		    while (iT.hasNext())
		    {
		    	
			   Map.Entry entry = (Map.Entry)iT.next();
			   
			   WeakReference wrSession = (WeakReference)entry.getValue();
			   
			   // ist die referenz noch intakt
			   if (wrSession.get() != null)
			   {
				   
				   HttpSession itSES = null;
				   try {

					   itSES = (HttpSession)wrSession.get(); 
					
					} catch (Exception e) {
						   iCountInvalid ++;
						   continue;
					}

				   
				   //jetzt die werte rauslesen
				   Date created = null;
				   try
				   {
					   if (itSES != null)
					   {
						   created = new Date(itSES.getCreationTime());
					   }
					   else
					   {
						   iCountNull ++;
						   continue;
					   }
				   }
				   catch (Exception ex)
				   {
					   iCountInvalid ++;
					   continue;
				   }
				   
				   
				   
				   //die werte auch direkt in die session schreibe, damit sie im sitzungsmanagement auch wieder ausgelesen werden koennen
				   String cDBSessionID = (String)itSES.getAttribute("@@@SESSION_4_LOCK_CHECK@@@");
				   String cDBSessionNumber= (String)itSES.getAttribute("@@@SESSION_IN_TRIGGER@@@");
				   String cName = (String)itSES.getAttribute("@@@SESSION_USERNAME@@@");
				   
				   String cID_MANDANT = ((BASIC_RECORD_MANDANT)bibALL.getSessionValue("RECORD_MANDANT", itSES)).get_ID_MANDANT_cUF();
				   
				   // falls sich die hashmap waehrend des auslesens dynamisch veraendert
				   if (vControll.contains(itSES.getId()))
					   continue;
				   else
					   vControll.add(itSES.getId());
				   
				   
				   Date accessed = new Date(itSES.getLastAccessedTime());
				   
				   // wenn es ein administrator ist, dann werden alle mandanten eingeblendet, sonst nur die user des eigenen mandanten
				   if (! bibALL.get_bIST_SUPERVISOR())
					   if (! bibALL.get_ID_MANDANT().equals(bibALL.get_ID_MANDANT()))
							  continue;
				   
				   
				   SimpleDateFormat oSD = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				   
				   MyE2_Button oButton = new MyE2_Button(E2_ResourceIcon.get_RI("windowpaneclose_small.png"));
				   oButton.add_oActionAgent(new ownActionAgentKillButton(itSES.getId()));
				   oGridForSessions.add(oButton);
				   
				   String cInfoLocks= bibDB.EinzelAbfrage(DB_META.get_SQL_CountLocks_Oracle_Session(),false,false);
				   oGridForSessions.add(new smallLabel(cInfoLocks),E2_INSETS.I_2_2_10_2);

				   // ++++ 
				   MyE2_Button oButtonCommit = new MyE2_Button(E2_ResourceIcon.get_RI("unlocked.png"));
				   oButtonCommit.add_oActionAgent(new ownActionAgentCommitButton(itSES.getId()));
				   oGridForSessions.add(oButtonCommit);
				   // ++++
				   
				   oGridForSessions.add(new smallLabel(cName),E2_INSETS.I_2_2_10_2);
				   oGridForSessions.add(new smallLabel(oSD.format(created)),E2_INSETS.I_2_2_10_2);
				   oGridForSessions.add(new smallLabel(oSD.format(accessed)),E2_INSETS.I_2_2_10_2);
				   if (itSES == bibE2.get_CurrSession())
					   oGridForSessions.add(new smallLabel("*"),E2_INSETS.I_2_2_10_2);
				   else
					   oGridForSessions.add(new smallLabel(""),E2_INSETS.I_2_2_10_2);
				   
				   oGridForSessions.add(new smallLabel(itSES.getId()),E2_INSETS.I_2_2_10_2);
				   oGridForSessions.add(new smallLabel(cDBSessionID),E2_INSETS.I_2_2_10_2);
				   oGridForSessions.add(new smallLabel(cDBSessionNumber),E2_INSETS.I_2_2_10_2);
				   oGridForSessions.add(new smallLabel(cID_MANDANT),E2_INSETS.I_2_2_10_2);
				   
				
			   }
			   else
			   {
				   iCountNull++;
			   }
		    }
		    this.oLabelInvalid.setText(""+iCountInvalid);
		    this.oLabelNull.setText(""+iCountNull);
		
		}
		catch (Exception ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.getLocalizedMessage(),false)));
		}

	}
	
	
	
	
	private void build_grid2()
	{
		this.oGridForInfos.removeAll();
		
		String cVersion = myCONST.VERSION;
        String cUsedMEM = ""+(bibALL.get_usedMemory()/1000000)+" MByte";
        String cFreeMEM = ""+(bibALL.get_freeMemory()/1000000)+" MByte";
        String cMandant = bibALL.get_MANDANT_NAME(false,true,true,true,true,true,true,true,true," ");
        
 
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("Infos:")),2, E2_INSETS.I_2_2_20_2);
        
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("Programmversion:")), E2_INSETS.I_2_2_20_2);
        this.oGridForInfos.add(new MyE2_Label(cVersion), E2_INSETS.I_2_2_20_2);
        
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("Benutzter Speicher:")), E2_INSETS.I_2_2_20_2);
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String(cUsedMEM)), E2_INSETS.I_2_2_20_2);;

        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("Freier Speicher:")), E2_INSETS.I_2_2_20_2);
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String(cFreeMEM)), E2_INSETS.I_2_2_20_2);

        
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("Mandant:")), E2_INSETS.I_2_2_20_2);
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String(cMandant)), E2_INSETS.I_2_2_20_2);

        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("DATENBANK:")), E2_INSETS.I_2_2_20_2);
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String(cMandant)), E2_INSETS.I_2_2_20_2);
        
        
        
        String cIsolationLevel = "";
        
        try
        {
        	if 		(bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation() == Connection.TRANSACTION_NONE)
        		cIsolationLevel = "TRANSACTION_NONE ("+bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation()+")";   // 0
        	else if (bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation() == Connection.TRANSACTION_READ_UNCOMMITTED)
        		cIsolationLevel = "TRANSACTION_READ_UNCOMMITTED ("+bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation()+")";  // 1
        	else if (bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation() == Connection.TRANSACTION_READ_COMMITTED)
        		cIsolationLevel = "TRANSACTION_READ_COMMITTED ("+bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation()+")";  // 2
        	else if (bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation() == Connection.TRANSACTION_REPEATABLE_READ)
        		cIsolationLevel = "TRANSACTION_REPEATABLE_READ ("+bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation()+")";
        	else if (bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation() == Connection.TRANSACTION_SERIALIZABLE)
        		cIsolationLevel = "TRANSACTION_SERIALIZABLE ("+bibALL.get_oConnectionNormal().get_oConnection().getTransactionIsolation()+")";
        	
        }
        catch (Exception ex)
        {
        	cIsolationLevel = "ERROR";
        }
        
        this.oGridForInfos.add(new MyE2_Label(new MyE2_String("Datenbank-Isolation-Level:")), E2_INSETS.I_2_2_20_2);
        this.oGridForInfos.add(new MyE2_Label(cIsolationLevel), E2_INSETS.I_2_2_20_2);
        
        
        
        
        this.oGridForInfos.add(new ButtonGarbadgeColl(),2, E2_INSETS.I_2_2_20_2);
        
	}
	
	
	
	private class titleLabel extends MyE2_Label
	{
		public titleLabel(Object cText)
		{
			super(cText,MyE2_Label.STYLE_SMALL_BOLD());
		}
	}
	

	private class smallLabel extends MyE2_Label
	{
		public smallLabel(Object cText)
		{
			super(cText,MyE2_Label.STYLE_SMALL_PLAIN());
		}
	}


	
	private class ButtonGarbadgeColl extends MyE2_Button
	{

		public ButtonGarbadgeColl()
		{
			super(new MyE2_String("Speicher aufräumen"));
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					//System.gc();
					bibServer.ownGc();
					
					ModuleContainer_POPUP_SessionManagement.this.build_grid2();
				}
			}
			);
		}
	}
	
	
	
	private class ownActionAgentKillButton extends XX_ActionAgent
	{
		private String cSessionID = null;
		
		public ownActionAgentKillButton(String sessionID)
		{
			super();
			this.cSessionID = sessionID;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_String cHelp = new MyE2_String(this.cSessionID,false);
			
			E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
					new MyE2_String("Sicherheitsabfrage"),
					new MyE2_String("Session schliessen: "),
					cHelp,
					new MyE2_String("JA"),
					new MyE2_String("NEIN"),
					new Extent(400),
					new Extent(200)
					);
			oConfirm.set_ActionAgentForOK(new ownActionKill(this.cSessionID));
			oConfirm.show_POPUP_BOX();
			
		}
		
	}
	
	
	
	
	
	
	/*
	 * actionagent um sessions zu killen
	 */
	private class ownActionKill extends XX_ActionAgent
	{
		private String cSessionID = null;
		
		public ownActionKill(String sessionID)
		{
			super();
			this.cSessionID = sessionID;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			// die sammler-hashmap aus dem servlet-context rausholen
			HashMap hmSessions = (HashMap)bibE2.get_CurrSession().getServletContext().getAttribute("applications.users");
			
			Iterator iT = hmSessions.entrySet().iterator();
			
			
		    // in der hashmap ist fuer jede neue session aus createSession eine WeakReference vorhanden
		    while (iT.hasNext())
		    {
		    	
			   Map.Entry entry = (Map.Entry)iT.next();
			   
			   WeakReference wrSession = (WeakReference)entry.getValue();
			   
			   // ist die referenz noch intakt
			   if (wrSession.get() != null)
			   {
				   HttpSession itSES = (HttpSession)wrSession.get(); 
			    	
				   if (itSES.getId().equals(this.cSessionID))
				   {
					   // zuerst die datenbank-verbindung einlesen und, falls vorhanden rollback
					   if (bibALL.get_oConnectionNormal()!=null)
					   {
						   try
						   {
							   bibALL.get_oConnectionNormal().ownRollBack();
						   }
						   catch (Exception ex) {}
					   }
					   
					   bibALL.CloseConnection_In_SESSION(itSES);
					   
					   itSES.invalidate();
					   itSES = null;
					   bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("Session killed: "+this.cSessionID));
					   break;
				   }
			   }
		    }
		    
		    ModuleContainer_POPUP_SessionManagement.this.build_Grid();

		}
	}
	
	
	
	
	// ++++++++++++++++
	private class ownActionAgentCommitButton extends XX_ActionAgent
	{
		private String cSessionID = null;
		
		public ownActionAgentCommitButton(String sessionID)
		{
			super();
			this.cSessionID = sessionID;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_String cHelp = new MyE2_String(this.cSessionID,false);
			
			E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
					new MyE2_String("Sicherheitsabfrage"),
					new MyE2_String("Verbindung Committen ? "),
					cHelp,
					new MyE2_String("JA"),
					new MyE2_String("NEIN"),
					new Extent(400),
					new Extent(200)
					);
			oConfirm.set_ActionAgentForOK(new ownActionCommit(this.cSessionID));
			oConfirm.show_POPUP_BOX();
			
		}
		
	}
	
	
	
	
	
	
	/*
	 * actionagent um sessions zu killen
	 */
	private class ownActionCommit extends XX_ActionAgent
	{
		private String cSessionID = null;
		
		public ownActionCommit(String sessionID)
		{
			super();
			this.cSessionID = sessionID;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			// die sammler-hashmap aus dem servlet-context rausholen
			HashMap hmSessions = (HashMap)bibE2.get_CurrSession().getServletContext().getAttribute("applications.users");
			
			Iterator iT = hmSessions.entrySet().iterator();
			
			
		    // in der hashmap ist fuer jede neue session aus createSession eine WeakReference vorhanden
		    while (iT.hasNext())
		    {
		    	
			   Map.Entry entry = (Map.Entry)iT.next();
			   
			   WeakReference wrSession = (WeakReference)entry.getValue();
			   
			   // ist die referenz noch intakt
			   if (wrSession.get() != null)
			   {
				   HttpSession itSES = (HttpSession)wrSession.get(); 
			    	
				   
				   if (itSES.getId().equals(this.cSessionID))
				   {
					   MyConnection oConnection = (MyConnection)bibALL.getSessionValue("CONN_NORMAL",itSES);
					   
					   // zuerst die datenbank-verbindung einlesen und, falls vorhanden rollback
					   if (oConnection!=null)
					   {
						   try
						   {
							   oConnection.ownCommit();
							   bibMSG.add_MESSAGE(new MyE2_Info_Message("Session-Transaktion committed ! "+this.cSessionID));
						   }
						   catch (Exception ex) 
						   {
							   bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("ERROR: Session-Transaktion NOT committed ! "+this.cSessionID,false)));
						   }
					   }
					   break;
				   }
			   }
		    }
		    
		    ModuleContainer_POPUP_SessionManagement.this.build_Grid();

		}
	}
	// ++++++++++++++++
	
	
	
	
	
	
	
	
	private class Button_RefreshSessionList extends MyE2_Button
	{

		public Button_RefreshSessionList()
		{
			super(E2_ResourceIcon.get_RI("reload.png"), true);
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					ModuleContainer_POPUP_SessionManagement.this.build_Grid();
				}
			}
			);
		}
	}


	
	
	
}
