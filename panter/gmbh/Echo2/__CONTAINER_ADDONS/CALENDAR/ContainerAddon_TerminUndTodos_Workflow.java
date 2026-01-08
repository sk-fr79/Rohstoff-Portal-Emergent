package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_Refreshable;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.BaseJumper;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Editor;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS.TODO_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_BasicModuleContainer;

public class ContainerAddon_TerminUndTodos_Workflow extends MyE2_Row implements E2_Refreshable
{

	public ContainerAddon_TerminUndTodos_Workflow() 
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.RefreshInfo();
	}
	
	public void RefreshInfo()
	{
		this.removeAll();
		/*
		 * heutigen tag besorgen
		 */
		try
		{
			myDateHelper oDateHeute_0 = new  myDateHelper(new GregorianCalendar());
			myDateHelper oDateHeute_1 = new  myDateHelper(myDateHelper.Add_Day_To_Calendar(1,new GregorianCalendar()));
			myDateHelper oDateHeute_2 = new  myDateHelper(myDateHelper.Add_Day_To_Calendar(2,new GregorianCalendar()));
			myDateHelper oDateHeute_3 = new  myDateHelper(myDateHelper.Add_Day_To_Calendar(3,new GregorianCalendar()));
			
			String cZahl0 = QueryAnzahlTermine(oDateHeute_0);
			String cZahl1 = QueryAnzahlTermine(oDateHeute_1);
			String cZahl2 = QueryAnzahlTermine(oDateHeute_2);
			String cZahl3 = QueryAnzahlTermine(oDateHeute_3);

			String cZahlToDo = QueryAnzahlToDos(); 

			Insets oIN = new Insets(5,1,1,1);
			
			this.setStyle(MyE2_Row.STYLE_THIN_BORDER());
			
			if (bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_KALENDER_YES())
			{
				this.add(new MyE2_Label(new MyE2_String("Termine: "),MyE2_Label.STYLE_SMALL_ITALIC()),oIN);
				this.add(new buttonTermine(oDateHeute_0,cZahl0,0),oIN);
				this.add(new buttonTermine(oDateHeute_1,cZahl1,50),oIN);
				this.add(new buttonTermine(oDateHeute_2,cZahl2,100),oIN);
				this.add(new buttonTermine(oDateHeute_3,cZahl3,200),oIN);
			}
			
			if (bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_TODOS_YES())
			{
				this.add(new MyE2_Label(new MyE2_String("TODOs: "),MyE2_Label.STYLE_SMALL_ITALIC()),new Insets(10,1,1,1));
				this.add(new buttonToDos(oDateHeute_0,cZahlToDo),oIN);
			}			
			
			
			if (bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_WORKFLOW_YES())
			{
				// workflows
				String cAnzahl1 = QueryAnzahlWorkflowEintraege() ;
				String cAnzahl2 = QueryAnzahlWorkflowEintraegeBesitzer();
				String cAnzahl3 = QueryAnzahlWorkflowBesitzer();
				String sCaption = cAnzahl1 + " / " + cAnzahl2 + " / " + cAnzahl3;
				//String sCaption = "["+cAnzahl1 + "] [" + cAnzahl2 + "] [" + cAnzahl3+"]";
				boolean bHighlight = ( !cAnzahl1.equals("0") || !cAnzahl2.equals("0")  ||  !cAnzahl3.equals("0"));
	
				this.add(new MyE2_Label(new MyE2_String("Laufzettel: "),MyE2_Label.STYLE_SMALL_ITALIC()),new Insets(10,1,1,1));
				this.add(new buttonWorkflows(sCaption,bHighlight));
			}		
			
			
			if (bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_MESSAGES_YES()){
				String cAnzahl = QueryAnzahlNachrichten();
				boolean bHighlight = ( !cAnzahl.equals("0") );

				//2016-03-14: aenderung name user einblenden
				String nameUser = "";
				try {
					nameUser = bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn());
				} catch (Exception e) {
				}
				
				this.add(new MyE2_Label(new MyE2_String("Unbestätigte Nachrichten an: ").ut(nameUser),MyE2_Label.STYLE_SMALL_ITALIC()),new Insets(10,1,5,1));
				this.add(new buttonMessages(cAnzahl, bHighlight));
			}
			
		}
		catch (myException ex)
		{
			ex.printStackTrace();
		}
	
		
	}
	
	
	
	
	private String QueryAnzahlTermine(myDateHelper oDateHelper) throws myException
	{
		String cSQL_0 = "select count(jt_termin_user.id_termin_user) from "+ bibE2.cTO()+".JT_TERMIN," +
							 bibE2.cTO()+".jt_termin_user where jt_termin_user.id_user ="+bibALL.get_ID_USER()+" and " +
							 		" jt_termin_user.ID_TERMIN = jt_termin.ID_TERMIN and " +
							 		" datum = '"+oDateHelper.get_cDateFormat_ISO_FORMAT()+"'";		 
		
		return bibDB.EinzelAbfrage(cSQL_0);
		
	}
	

	/**
	 * Ermitteln der Laufzettel die dem Mitarbeiter zugewiesen sind
	 * @return
	 * @throws myException
	 */
	private String QueryAnzahlWorkflowEintraege() throws myException
	{
		String cID_USER = bibALL.get_ID_USER();
		String cSQL_0 = "select count(*) from (" +
				" SELECT   JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL  FROM " +
				bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG  INNER JOIN  " + bibE2.cTO() + ".JT_LAUFZETTEL " + 
				" ON  JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL = JT_LAUFZETTEL.ID_LAUFZETTEL  " + 
				" WHERE  nvl(JT_LAUFZETTEL_EINTRAG.ID_USER_ABGESCHLOSSEN_VON,0) = 0" +
				" AND nvl(JT_LAUFZETTEL_EINTRAG.GELOESCHT,'N' ) = 'N' " +
				" AND nvl(JT_LAUFZETTEL.GELOESCHT,'N') = 'N' " + 
				" AND nvl(JT_LAUFZETTEL.ID_USER_ABGESCHLOSSEN_VON,0) = 0 " +
				" AND JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER = " + cID_USER +
				" AND NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE) <= SYSDATE + 14 " +
				" )"; 
    		 
		return bibDB.EinzelAbfrage(cSQL_0);
		
	}

	private String QueryAnzahlWorkflowEintraegeBesitzer() throws myException
	{
		String cID_USER = bibALL.get_ID_USER();
		String cSQL_0 = "select count(*) from (" +
				" SELECT   JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL  FROM " +
				bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG  INNER JOIN  " + bibE2.cTO() + ".JT_LAUFZETTEL " + 
				" ON  JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL = JT_LAUFZETTEL.ID_LAUFZETTEL  " + 
				" WHERE  nvl(JT_LAUFZETTEL_EINTRAG.ID_USER_ABGESCHLOSSEN_VON,0) = 0" +
				" AND nvl(JT_LAUFZETTEL_EINTRAG.GELOESCHT,'N' ) = 'N' " +
				" AND nvl(JT_LAUFZETTEL.GELOESCHT,'N') = 'N' " + 
				" AND nvl(JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER,0) != " + cID_USER +
				" AND JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER = " + cID_USER +
				" AND NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE) <= SYSDATE + 14 " +
				")" ; 
    		 
		return bibDB.EinzelAbfrage(cSQL_0);
		
	}

	
	
	/**
	 * Ermittelt die Anzahl der Laufzettel, an denen man Besitzer oder Supervisor ist.
	 * @return
	 * @throws myException
	 */
	private String QueryAnzahlWorkflowBesitzer() throws myException
	{
		String cID_USER = bibALL.get_ID_USER();
		String cSQL = "select count(*) from ( " +
		"		SELECT ID_LAUFZETTEL FROM   " + bibE2.cTO() + ".JT_LAUFZETTEL " +
		"		WHERE  nvl(JT_LAUFZETTEL.ID_USER_ABGESCHLOSSEN_VON,0) = 0 " +
		"		and nvl(JT_LAUFZETTEL.GELOESCHT,'N') = 'N'  " +
		"		and (ID_USER_BESITZER = " + cID_USER + " OR ID_USER_SUPERVISOR = " + cID_USER + ")" +
		"	) "	;		
		return bibDB.EinzelAbfrage(cSQL);
				   	
	}
	
	
	
	private String QueryAnzahlToDos() throws myException
	{
		String cID_USER = bibALL.get_ID_USER();
		String cSQL_0 = "SELECT COUNT(*) FROM JT_TODO WHERE NVL(ERLEDIGT,'N')='N' AND (JT_TODO.ID_USER="+cID_USER+" OR JT_TODO.ID_TODO IN (SELECT ID_TODO FROM "+bibE2.cTO()+".JT_TODO_TEILNEHMER WHERE ID_USER="+cID_USER+"))";		 
		return bibDB.EinzelAbfrage(cSQL_0);
	}

	
	
	
	
	private String m_QueryMessages = "	SELECT * FROM   " + bibE2.cTO() + ".JT_NACHRICHT " +
									"	WHERE  NVL(BESTAETIGT,'N')='N'  " +
									"	 AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') " +
									"	 AND NVL(SOFORTANZEIGE,'Y') = 'N' " +
									"    AND NVL(GELOESCHT,'N') = 'N' " +
									"	 AND ID_USER = " + bibALL.get_ID_USER() ;
	
	private String m_QueryMessages_read = "	SELECT * FROM   " + bibE2.cTO() + ".JT_NACHRICHT " +
									"	WHERE  NVL(BESTAETIGT,'N')='Y'  " +
									"	 AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') " +
									"	 AND ID_USER = " + bibALL.get_ID_USER() ;
	

	private String m_QueryMessagesOrder = " ORDER BY AKTIV_AB, ID_NACHRICHT  ";
	private String m_QueryMessagesOrder_read = " ORDER BY AKTIV_AB DESC, ID_NACHRICHT DESC ";
	
	
	/**
	 * Ermittelt die Anzahl der Nachrichten die noch nicht bestätigt sind, und die
	 * nicht für die Sofortanzeige gekennzeichnet sind.
	 * @return
	 * @throws myException
	 */
	
	private String QueryAnzahlNachrichten() throws myException
	{
		String cSQL = "select count(*) from ( " + m_QueryMessages + " )";
		return bibDB.EinzelAbfrage(cSQL);
	}


	
	
	
	
	
	private class buttonTermine extends MyE2_Button
	{
		private myDateHelper oDateHelper = null;
		
		public buttonTermine(myDateHelper dateHelper, String anzahl, int iColorGray) 
		{
			super(anzahl);
			this.oDateHelper = dateHelper;
			this.add_oActionAgent(new ownActionAgentStartMASKTermine(this.oDateHelper));
			this.setForeground(new E2_ColorGray(iColorGray));
			this.setFont(new E2_FontBold(2));
			
			MyE2_String oString = new MyE2_String("Termine verwalten am: ");
			try
			{
				this.setToolTipText(oString.CTrans()+" "+dateHelper.get_cDateFormatForMask());
			}
			catch (myException ex) {}
		}
		public myDateHelper get_DateHelper() {return oDateHelper;}
	}
	
	
	private class buttonWorkflows extends MyE2_Button
	{
		public buttonWorkflows( String sText, boolean bHighlight) 
		{
			super(sText);
			boolean bJumpToSimple = false;
			
			try {
				bJumpToSimple = ENUM_MANDANT_DECISION.WORKFLOW_JUMP_TO_SIMPLE_WORKFLOW_LIST.is_YES();
			} catch (myException e) {		
			}
			
			if (bJumpToSimple) {
				this.add_oActionAgent(new actionGotoModulWFSimple());
			} else {
				this.add_oActionAgent(new ownActionAgentStartTabWorkflow());
			}
			

			if (bHighlight) {
				this.setBackground(Color.RED);
			}
			this.setFont(new E2_FontBold(2));
			String sUser = bibALL.get_USERNAME();
			//MyE2_String oString = new MyE2_String("Offenen Aufgaben von " + sUser + " (+14 Tage) / Aufgaben von " + sUser + " (+14 Tage) / Laufzettel von " + sUser + " (gesamt) ");
			MyE2_String oString = new MyE2_String(	"Zahl 1: Offene Aufgaben (14 Tage, " + sUser + "="+"Bearbeiter) / \n"
												+	"Zahl 2: Offene Aufgaben (14 Tage, " + sUser +"="+"Besitzer) / \n"
												+ 	"Zahl 3: Laufzettel (Alle Zeit,  " + sUser + "=Besitzer)");
			this.setToolTipText(oString.CTrans());
		}
	}

	
	/**
	 * Button-Klasse für Unbestätigte Nachrichten 
	 * @author manfred
	 *
	 */
	private class buttonMessages extends MyE2_Button
	{
		public buttonMessages( String sText, boolean bHighlight) 
		{
			super(sText);
			
			this.add_oActionAgent(new ownActionAgentStartTabMessages());

			if (bHighlight) {
				this.setBackground(Color.RED);
			}
			this.setFont(new E2_FontBold(2));
			
			String nameUser = "";
			try {
				nameUser = bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn());
			} catch (Exception e) {
			}
			
			
			MyE2_String oString = new MyE2_String("Unbestätigte Nachrichten an: ").ut(nameUser);
			this.setToolTipText(oString.CTrans());
		}
	}

	
	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartTabMessages extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				
				// falls man den Knopf in der Leiste drückt um an die Messages zu kommen, wird der Sofort-Anzeigen-Delay zurückgenommen.
				bibSES.set_Messages_Popup_TimeStamp(null);
				
				new MESSAGE_Editor(false,false);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error showing Messages !",false)));
			}
		}
	}


	
	
	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartTabWorkflow extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				WF_HEAD_LIST_BasicModuleContainer oWorkflow = new WF_HEAD_LIST_BasicModuleContainer();
				oWorkflow.set_bIsStartContainer_4_DBTimeStamps(true);
				oWorkflow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), new MyE2_String("Laufzettel und Aufgaben bearbeiten"));
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error showing workflow list",false)));
			}
		}
	}
	

	
	private class actionGotoModulWFSimple extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				Vector<String> v = new Vector<String>();
				new BaseJumper(E2_MODULNAME_ENUM.MODUL.WF_SIMPLE_LIST.get_callKey(), E2_MODULNAME_ENUM.MODUL.WF_SIMPLE_LIST.get_callKey(), v, false, null);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error jumping to Workflow List !",false)));
			}
		}
	}

	
	
	
	
	private class buttonToDos extends MyE2_Button
	{
		private myDateHelper oDateHelper = null;
		
		public buttonToDos(myDateHelper dateHelper, String anzahl) 
		{
			super(anzahl);
			this.oDateHelper = dateHelper;
			this.add_oActionAgent(new ownActionAgentStartMASKToDO());
			
			try
			{
				Integer oAnzahl = new Integer(anzahl);
				if (oAnzahl.intValue()>0)
				{
					this.setBackground(Color.RED);
				}
			}
			catch (Exception ex)
			{}
			
			this.setFont(new E2_FontBold(2));
			
			
			
			MyE2_String oString = new MyE2_String("Offene TODOS ...");
			this.setToolTipText(oString.CTrans());
		}

		public myDateHelper get_DateHelper() {return oDateHelper;}
	}

	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartMASKToDO extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				TODO_LIST_BasicModuleContainer oToDo = new TODO_LIST_BasicModuleContainer();
				oToDo.set_bIsStartContainer_4_DBTimeStamps(true);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error showing Calendar !",false)));
			}
		}
	}
	
	
	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartMASKTermine extends XX_ActionAgent
	{
		private myDateHelper oDateHelper = null;

		public ownActionAgentStartMASKTermine(myDateHelper dateHelper) 
		{
			super();
			oDateHelper = dateHelper;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			/*
			 * zuerst pruefen, ob es einen modulkenner gibt, wenn ja dann popup anzeigen, sonst nicht, da bei manchen
			 * zustaenden am anfang der modulkenner erst nach einfuegen des E2_ROHSTOFF_SetButtonAccess erfolgt, und damit
			 * bei der ersten anzeige noch nicht bekannt ist
			 */
			try
			{
				CAL_BasicModuleContainer oBasic = new CAL_BasicModuleContainer(	oDateHelper.get_IMonth().intValue(),
																				oDateHelper.get_IYear().intValue());
				oBasic.get_oCalendarGrid().openDay(oDateHelper);
				oBasic.set_bIsStartContainer_4_DBTimeStamps(true);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error showing Calendar !",false)));
			}
		}
	}


	
}
