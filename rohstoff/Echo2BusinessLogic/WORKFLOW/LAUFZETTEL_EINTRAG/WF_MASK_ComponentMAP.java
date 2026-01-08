package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.BT_UserDropdownExtension_Self_Setter;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UB_SelectField_USERS;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Editor;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TF_4_Date;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;
import rohstoff.utils.ECHO2.E2_AuswahlSelectorUsers;

public class WF_MASK_ComponentMAP extends E2_ComponentMAP 
{

	// Laufzettel-ID 
	// Normalfall:  wird angegeben (nich null): die Maske wird als Standardmäßig benutzt und nur der Eintrag zum gegebenen Laufzettel gespeichert
	// Ausnahme:    ID ist null: Dann wird beim Speichern der Laufzettel-Kopf und -Eintrag generiert.
	// 				Dabei ist der Kopf rudimentär ausgefüllt mit dem gegebenen Text
	private String m_ID_LAUFZETTEL = null;
	
	/**
	 * Falls man für die Initialisierung der Maske beim neuaufbau Werte übergeben will, werden die in der Component-Map abgelegt.
	 */
	private Hashtable<String, String> m_ParameterForInitialisation = new Hashtable<String, String>();
	
	private ActionAgent_RadioFunction_CheckBoxList agentWatchdogKadenz = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2964717306963626907L;
//	// Enabled-Zustände der Felder 
//	private HashMap<String,Boolean> mapFeldStatus = new HashMap<String, Boolean>();
	
	/**
	 * @deprecated Use {@link #WF_MASK_ComponentMAP(String,String,String)} instead
	 */
	public WF_MASK_ComponentMAP(String ID_LAUFZETTEL, String ID_USER_BEARBEITER) throws myException
	{
		this(ID_LAUFZETTEL, ID_USER_BEARBEITER, null);
		
	}


	public WF_MASK_ComponentMAP(String ID_LAUFZETTEL, String ID_USER_BEARBEITER, String ID_EINTRAG_PARENT) throws myException
	{
		super(new WF_MASK_SQLFieldMAP(ID_LAUFZETTEL,ID_USER_BEARBEITER, ID_EINTRAG_PARENT));
		
		m_ID_LAUFZETTEL = ID_LAUFZETTEL;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ABGESCHLOSSEN_AM"),true,100), new MyE2_String("Eintrag abgeschlossen am:"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANGELEGT_AM"),true,100), new MyE2_String("Eintrag angelegt am:"));
		
		this.add_Component(new MyE2_DB_TextArea(oFM.get("AUFGABE"),680, 6),new MyE2_String("Aufgabe:"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get("BERICHT"), 680, 10),new MyE2_String("Tätigkeitsbericht:"));
		
		
		
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("FAELLIG_AM"),true,100), new MyE2_String("Zu erledigen bis:"));
		this.add_Component(new MyE2_DB_TF_4_Date(oFM.get("FAELLIG_AM"), E2_TF_4_Date_Enum.SLIP_SELECTION_MODE),new MyE2_String("Zu erledigen bis:"));
		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_LAUFZETTEL"),true,80), new MyE2_String("Laufzettel-ID:"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_LAUFZETTEL_EINTRAG"),true,80), new MyE2_String("Eintrags-ID:"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("PRIVAT")), new MyE2_String("Eintrag ist Privat!"));

		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KADENZ_NACH_ABSCHLUSS"),true,50), new MyE2_String("Tage Kadenz"));
		
		MyE2_DB_CheckBox oCBKadenzNachFaelligkeit = new MyE2_DB_CheckBox(oFM.get(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn()),new MyE2_String("nach Fälligkeit"), new MyE2_String("Kadenz berechnet sich aus dem Termin plus der Kadenz-Zeit"));
		this.add_Component(oCBKadenzNachFaelligkeit, new MyE2_String("Kadenz nach Fälligkeitstermin"));
		
		MyE2_CheckBox  oCBKadenzNachAbschluss = new MyE2_CheckBox("nach Erledigung",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		oCBKadenzNachAbschluss.setToolTipText(new MyE2_String("Kadenzzeit wird bezogen auf das Datum des Abschlusses der Aufgabe.").CTrans());
		this.add_Component(WF_CONST.HASH_CHECKBOX_KADENZ_NACH_ABSCHLUSS, oCBKadenzNachAbschluss, new MyE2_String("nach Erledigung"));
		
		// Watchdog für die Radio-Button-Funktion
		agentWatchdogKadenz = new ActionAgent_RadioFunction_CheckBoxList(false);
		agentWatchdogKadenz.add_CheckBox(oCBKadenzNachAbschluss);
		agentWatchdogKadenz.add_CheckBox(oCBKadenzNachFaelligkeit);
		agentWatchdogKadenz.set_AllUnselected();
		
		
		
		// gelöscht nur in der Komponent-Map aufnehmen, nicht in die Maske
		this.add_Component(new MyE2_DB_TextField(oFM.get_("GELOESCHT"),true,20), new MyE2_String("Eintrag ist gelöscht:"));
		
		
		// PRIORITAET Dropdown definieren und zuweisen
		MyDropDownSettings oDD = new MyDropDownSettings(bibE2.cTO(),"JT_LAUFZETTEL_PRIO","PRIO","ID_LAUFZETTEL_PRIO",null,"ISDEFAULT",true, "PRIO_SORT");
		MyE2_DB_SelectField oSelectID_LAUFZETTEL_PRIO = new MyE2_DB_SelectField(oFM.get_("ID_LAUFZETTEL_PRIO"),oDD.getDD(),true);
		this.add_Component(oSelectID_LAUFZETTEL_PRIO, new MyE2_String("Priorität:"));

		// Dropdown Laufzettel Status
		MyDropDownSettings oDDStatus = new MyDropDownSettings(bibE2.cTO(),"JT_LAUFZETTEL_STATUS","STATUS","ID_LAUFZETTEL_STATUS",null,"ISDEFAULT",true,"STATUS_SORT");
		MyE2_DB_SelectField oSelectID_LAUFZETTEL_STATUS = new MyE2_DB_SelectField(oFM.get_("ID_LAUFZETTEL_STATUS"),oDDStatus.getDD(),true);
		this.add_Component(oSelectID_LAUFZETTEL_STATUS,new MyE2_String("Aktueller Status:"));
		
		
		// Dropdown User_Bearbeiter für Edit
		DB_Component_USER_DROPDOWN oSelectID_USER = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_BEARBEITER"));
		oSelectID_USER.setInsets(E2_INSETS.I_0_0_0_0);
		
		BT_UserDropdownExtension_Self_Setter btnUserSelf = new BT_UserDropdownExtension_Self_Setter(oSelectID_USER);
		this.add_Component(WF_CONST.HASH_BUTTON_SET_WF_BEARBEITER_SELF, btnUserSelf, new MyE2_String("Auswählen des aktuellen Benutzers / Kein Benutzer"));
		
		// Auswahlfeld User_Bearbeiter für Neueingabe
		E2_AuswahlSelectorUsers oSelektorUsers = new E2_AuswahlSelectorUsers( new Extent(250),new Extent(120),null  );
		oSelektorUsers.setInsets(E2_INSETS.I_0_0_0_0);
		
		// die Felder einer Multi-Component-Column zufügen
		MyE2_DB_MultiComponentColumn oColBearbeiterContainer = new MyE2_DB_MultiComponentColumn();
		oColBearbeiterContainer.add_Component(oSelectID_USER, new MyE2_String("Zu erledigen von:"),null);
		oColBearbeiterContainer.add_Component(oSelektorUsers, new MyE2_String("Benutzerauswahl"), WF_CONST.HASH_LAUFZETTEL_BENUTZER_AUSWAHL);
		oColBearbeiterContainer.setInsets(E2_INSETS.I_0_0_0_0);
		
		// den Container zur Component-map dazufügen
		this.add_Component(WF_CONST.HASH_LAUFZETTEL_BENUTZER_AUSWAHL_CONTAINER, oColBearbeiterContainer, new MyE2_String("Bearbeiter"));
		

		
		// Standard-Erinnerungsnachricht
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SEND_NACHRICHT"),
				new MyE2_String("Nachricht zum Fälligkeitstermin"),
				new MyE2_String("Am Termin wird eine Nachricht generiert und dem Bearbeiter bzw. Besitzer der Aufgabe als Erinnerung geschickt.")), new MyE2_String("Erinnerungsnachricht"));
		
		MyE2_Button  oButtonSendReminder = new MyE2_Button("Nachricht (an mich)");
		oButtonSendReminder.setToolTipText(new MyE2_String("Erzeugt eine zusätzliche Nachricht die als Wiedervorlagen-Nachricht zum gewählten Zeitpunkt erscheint.").CTrans());
		this.add_Component(WF_CONST.HASH_BUTTON_CREATE_REMINDER, oButtonSendReminder, new MyE2_String("Wiedervorlage"));

		
		MyE2_Button  oButtonSendMessageToGroup = new MyE2_Button("Nachricht (an alle beteiligten Mitarbeiter)");
		oButtonSendMessageToGroup.setToolTipText(new MyE2_String("Erzeugt eine zusätzliche Nachricht an alle am Laufzettel beteiligten Mitarbeiter, die als Wiedervorlagen-Nachricht zum gewählten Zeitpunkt erscheint.").CTrans());
		this.add_Component(	WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP,oButtonSendMessageToGroup, new MyE2_String("Nachricht (an alle beteiligten Mitarbeiter)"));
		
		//this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_USER_BESITZER"),true,200), new MyE2_String("Aufgabe angelegt von:"));
		DB_Component_USER_DROPDOWN oSelectID_USER_BESITZER = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_BESITZER"));
		this.add_Component(oSelectID_USER_BESITZER, new MyE2_String( "Eintrag von:"));
		
		// Dropdown User Abgeschlossen_Von
		DB_Component_USER_DROPDOWN oSelectID_USER_AV = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_ABGESCHLOSSEN_VON"));
		this.add_Component(oSelectID_USER_AV, new MyE2_String("Eintrag abgeschlossen von:"));
		
		
		// letzte Änderungen User/Datum
		this.add_Component(new MyE2_DB_TextField(oFM.get_(WF_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG),true,80), new MyE2_String("LETZTE_AENDERUNG"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(WF_CONST.HASH_SONDERFELD_GEANDERT_VON),true,80), new MyE2_String("GEAENDERT_VON"));
		
		
		// Beschreibung des Laufzettels
		MyE2_TextArea oTextarea = new MyE2_TextArea();
		oTextarea.setFont(new E2_FontBold());
		oTextarea.set_iRows(4);
		oTextarea.set_iWidthPixel(680);
		oTextarea.set_bEnabled_For_Edit(false);
		this.add_Component(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG,oTextarea,new MyE2_String("Laufzettel"));
		
		// Dropdown für den Supervisor
		UB_SelectField_USERS oSelectSupervisor = new UB_SelectField_USERS("ID_USER_SUPERVISOR", true, false, 200);
		this.add_Component(WF_CONST.HASH_LAUFZETTEL_SUPERVISOR, oSelectSupervisor, new MyE2_String("Supervisor"));

		
		// das Modul für die Verlinkung einbauen
		this.add_Component(WF_CONST.HASH_LIST_CONNECTOR, new MODUL_LINK_Connector() , new MyE2_String("Connector"));

				
		((MyE2_DB_SelectField)this.get__Comp_From_RealComponents("ID_USER_BEARBEITER")).setWidth(new Extent(200));
		((MyE2_DB_SelectField)this.get__Comp("ID_LAUFZETTEL_PRIO")).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get__Comp("ID_LAUFZETTEL_STATUS")).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get__Comp("ID_USER_BESITZER")).setWidth(new Extent(200));
		((MyE2_DB_SelectField)this.get__Comp("ID_USER_ABGESCHLOSSEN_VON")).setWidth(new Extent(200));
		

		this.get__Comp("ID_LAUFZETTEL").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("ID_LAUFZETTEL_EINTRAG").EXT().set_bDisabledFromBasic(true);
		
		//Nur der supervisor darf den Besitzer und das Angelegt_am Datum ändern
		if (!bibALL.get_bIST_SUPERVISOR())
		{
			this.get__Comp("ID_USER_BESITZER").EXT().set_bDisabledFromBasic(true);
			this.get__Comp("ANGELEGT_AM").EXT().set_bDisabledFromBasic(true);
		}
		
		// dieses Feld immer disablen!!
		// dieses Feld disablen, wenn eine Laufzettel-ID vorgegeben wurde. 
		if (m_ID_LAUFZETTEL != null){
			this.get__Comp(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG).EXT().set_bDisabledFromBasic(true);
			this.get__Comp(WF_CONST.HASH_LAUFZETTEL_SUPERVISOR).EXT().set_bDisabledFromBasic(true);
		}
		
		this.get__Comp(WF_CONST.HASH_LIST_CONNECTOR).set_bEnabled_For_Edit(true);
		this.get__Comp(WF_CONST.HASH_BUTTON_CREATE_REMINDER).set_bEnabled_For_Edit(true);
				
		
		//dropdown fuer id_user_erled.... mit actionagent versehen
		
		/**
		 * Anonymer ActionAgent für die Combobox "Abgeschlossen von"
		 * Füllt den Wert des Feldes "Abgeschlossen am" mit dem aktuellen Datum, falls ein Wert eingetragen wird, 
		 * null wenn der Wert zurückgesetzt wird
		 */
		oSelectID_USER_AV.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_DB_SelectField oSelectUserFinal = (MyE2_DB_SelectField)WF_MASK_ComponentMAP.this.get__Comp("ID_USER_ABGESCHLOSSEN_VON");
				
				E2_ComponentMAP oMAP_Own = oSelectUserFinal.EXT().get_oComponentMAP();
				
				if (bibALL.isEmpty(oSelectUserFinal.get_ActualWert()))
				{
					((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText("");
					
					// aktuellen Benutzer löschen
					MyE2_DB_SelectField oSelectStatus = (MyE2_DB_SelectField)oMAP_Own.get__Comp("ID_LAUFZETTEL_STATUS");
					oSelectStatus.setSelectedIndex(0);
				}
				else
				{
					((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText(bibALL.get_cDateNOW());
					
					// die Combobox setzen
					String idStatus = getIDForStatusAbgeschlossen();
					if (idStatus != null){
					 ((MyE2_DB_SelectField)oMAP_Own.get__Comp("ID_LAUFZETTEL_STATUS")).set_ActiveValue(idStatus);
					}
					
					// Info-Dialog der Anzeigt, dass x Tage nach Abschluss eine neue Aufgabe angelegt wird. 
					String sKadenz = ((MyE2_DB_TextField)oMAP_Own.get__Comp("KADENZ_NACH_ABSCHLUSS")).getText();
					if (!bibALL.isEmpty(sKadenz)){
						new cInfoPopUP_Abschluss(sKadenz).showActualMessages();
					}
					
				}
			}
		});
		
		
		/**
		 * ActionAgent für den Status des Laufzetteleintrags
		 */
		oSelectID_LAUFZETTEL_STATUS.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				MyE2_DB_SelectField oSelectStatus = (MyE2_DB_SelectField)WF_MASK_ComponentMAP.this.get__Comp("ID_LAUFZETTEL_STATUS");
				E2_ComponentMAP oMAP_Own = oSelectStatus.EXT().get_oComponentMAP();
				
				boolean bAbschliessen = false;
				
				String idStatus = oSelectStatus.get_ActualWert().replace(".", "");
				
				if (bibALL.isEmpty(idStatus)){
					bAbschliessen = false;
				} else {
					RECORD_LAUFZETTEL_STATUS recStatus = new RECORD_LAUFZETTEL_STATUS(idStatus);
					if (recStatus.get_TRIGGER_ABSCHLUSS_cUF_NN("N").equals("Y")){
						bAbschliessen = true;
					}
				}
				
				MyE2_DB_SelectField oDDAbgeschlossenVon = (MyE2_DB_SelectField)oMAP_Own.get__Comp("ID_USER_ABGESCHLOSSEN_VON");

				// die Combobox-Referenz...
				if (bAbschliessen){
					// den Wert auf den aktuellen Benutzer setzen...
					oDDAbgeschlossenVon.set_ActiveValue(bibALL.get_ID_USER_FORMATTED());
					oDDAbgeschlossenVon.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(oExecInfo.get_MyActionEvent(),true));
				
				} else {
					// aktuellen Benutzer löschen
					oDDAbgeschlossenVon.setSelectedIndex(0);
					((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText("");
//					oDDAbgeschlossenVon.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(oExecInfo.get_MyActionEvent(),true));
				}
			}
		});
	
		
	
		/**
		 * anonymer Agent zum generieren von Wiedervorlagen
		 */
		oButtonSendReminder.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WF_MASK_ComponentMAP oThis = WF_MASK_ComponentMAP.this;
				SQLResultMAP rm = oThis.get_oInternalSQLResultMAP();
				
				String sTitel = "Wiedervorlage Laufzettel-Aufgabe";
				String sNachricht = "";
				sNachricht += ((MyE2_TextArea)oThis.get__Comp(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG)).getText();
				sNachricht += "\n\n";
				sNachricht += rm.get_UnFormatedValue("AUFGABE"); 
				
				Vector<String> vIDs = new Vector<String>();
				vIDs.add(bibALL.get_ID_USER_FORMATTED());
				
				//
				GregorianCalendar 	calNow 			= new GregorianCalendar();
				GregorianCalendar 	calAnzeigeAb 	= new GregorianCalendar();
				Date 				dtAnzeigeAb 	= calNow.getTime();
				
				String 				sDTAnzeigeAb 	= rm.get_UnFormatedValue("FAELLIG_AM");
				
				
				if (!bibALL.isEmpty(sDTAnzeigeAb)){
					dtAnzeigeAb = bibDate.String2Date(sDTAnzeigeAb);
					calAnzeigeAb.setTime(dtAnzeigeAb);
				} else {
					dtAnzeigeAb = calNow.getTime();
					calAnzeigeAb.setTime(dtAnzeigeAb);
				}

				// Unterscheidung, welcher User die Wiedervorlage haben möchte.
				String idUser = bibALL.get_ID_USER();
				String idUserBearbeiter = rm.get_UnFormatedValue("ID_USER_BEARBEITER","*");
				
				// Differenz zum Anzeige-Datum (negativ -> Anzeigedatum liegt in der Zukunft)
				int diff = (int)myDateHelper.get_DayDifference_Date2_MINUS_Date1(calAnzeigeAb , calNow);
				
				// Wenn das Fälligkeitsdatum in der Vergangenheit oder bei Heute liegt, dann aktuelles Datum + 2 Tage
				if (diff >= 0 ){
					calNow.add(GregorianCalendar.DATE, 2);
					dtAnzeigeAb = calNow.getTime();
				}  else {
					// wenn der aktuelle Benutzer der Bearbeiter ist, dann wird das Fälligkeitsdatum als WV-Datum genommen
					if (idUser.equals(idUserBearbeiter)){
						dtAnzeigeAb = calAnzeigeAb.getTime();
					} else {
						// Sonst das Fälligkeitsdatum + 2 Tage (Überprüfung, ob Aufgabe erledigt wurde)
						calAnzeigeAb.add(GregorianCalendar.DATE, 2);
						dtAnzeigeAb = calAnzeigeAb.getTime();
					}
				}
				sDTAnzeigeAb = bibDate.Date2StringISO(dtAnzeigeAb);
				
				
				MESSAGE_Entry_Target target = new MESSAGE_Entry_Target(
						rm.get_UnFormatedValue("ID_LAUFZETTEL"),
						E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,
						null,
						null);
				
				MESSAGE_Entry entry = new MESSAGE_Entry()
										.set_Titel(sTitel)
										.set_Nachricht(sNachricht)
										.set_vIdEmpfaenger(vIDs)
										.set_Kategorie(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE)
										.add_Target(target)
										.set_DtAnzeigeAb(sDTAnzeigeAb)
										;
				
				MESSAGE_Editor oMSG_EDIT = new MESSAGE_Editor(entry);

				
				
			}
		});
		
		
		/**
		 * anonymer Agent zum generieren von Meldungen an die beteiligten Persionen
		 */
		oButtonSendMessageToGroup.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WF_MASK_ComponentMAP oThis = WF_MASK_ComponentMAP.this;
				
				SQLResultMAP rm = oThis.get_oInternalSQLResultMAP();
				
				String sTitel = "Nachricht zur Laufzettel-Aufgabe";
				String sNachricht = "";
				
				String idLaufzettel = rm.get_UnFormatedValue("ID_LAUFZETTEL");
				String idLaufzettelEintrag = rm.get_UnFormatedValue("ID_LAUFZETTEL_EINTRAG");
				
				RECORD_LAUFZETTEL oRec = new RECORD_LAUFZETTEL(idLaufzettel);
				RECORD_LAUFZETTEL_EINTRAG oRecEintrag = new RECORD_LAUFZETTEL_EINTRAG(idLaufzettelEintrag);
				
				WF_MessageHelper msgHelper = new WF_MessageHelper(oRec, oRecEintrag);
				Vector<String> vIDs = msgHelper.getListOfWFUser(true);
				sNachricht= msgHelper.getMessageText(true);
				
//				MESSAGE_Editor oMessage = new MESSAGE_Editor(sTitel,sNachricht,vIDs,rm.get_UnFormatedValue("ID_LAUFZETTEL"),E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,null,E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE);
				
				MESSAGE_Entry_Target target = new MESSAGE_Entry_Target(
						rm.get_UnFormatedValue("ID_LAUFZETTEL"),
						E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,
						null,
						null);
				
				MESSAGE_Entry entry = new MESSAGE_Entry()
										.set_Titel(sTitel)
										.set_Nachricht(sNachricht)
										.set_vIdEmpfaenger(vIDs)
										.set_Kategorie(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE)
										.add_Target(target)
										;
				
				MESSAGE_Editor oMSG_EDIT = new MESSAGE_Editor(entry);
				
				
			}
		});
		
		
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new WF_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new WF_MASK_FORMATING_Agent());
	}
	
	
	/**
	 * ermitteln der ID die für den Abschluss Zuständig ist.
	 * @return
	 * @throws myException
	 */
	private String getIDForStatusAbgeschlossen() throws myException{
		String idRet = null;
		RECLIST_LAUFZETTEL_STATUS list = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM " + bibE2.cTO() + ".JT_LAUFZETTEL_STATUS WHERE NVL(TRIGGER_ABSCHLUSS,'N') = 'Y' ");
		if (list.size() > 0){
			idRet = list.get(0).get_ID_LAUFZETTEL_STATUS_cF();
		}
		return idRet;
	}
	
	

	/**
	 * Setzen von Initialisierungsparametern, um bestimmte Werte vorzubelegen
	 * Als Key muss der Name des Feldes in der Component-Map verwendet werden
	 * @param Key
	 * @param Value
	 */
	public void setParameterForInitialisation (String Key, String Value) throws myException{
			 try {
				Object o = this.get__Comp(Key);
				m_ParameterForInitialisation.put(Key, Value);
			} catch (Exception e) {
				throw new myException("WF_MASK_ComponentMAP.setParameterForInitialisation::der Schlüssel konnte nicht gefunden werden");
			}
		
	}
	
	
	public String getParameterForInitialisation (String Key){
		String sRet = null;
		if (m_ParameterForInitialisation.containsKey(Key)){
			sRet = m_ParameterForInitialisation.get(Key);
		} 
		return sRet;
	}
	
	public Hashtable<String, String> getParameters(){
		return m_ParameterForInitialisation;
	}
	
	
	public ActionAgent_RadioFunction_CheckBoxList getAgentWatchdogKadenz() {
		return agentWatchdogKadenz;
	}



	/**
	 * InfoPopup für neu angelegte Aufgaben nach Abschluss 
	 * @author manfred
	 * @date 07.10.2015
	 */
	 private class cInfoPopUP_Abschluss extends E2_MessageBoxYesNo {

	        public cInfoPopUP_Abschluss(String sKadenz)    throws myException {
	            super(  new MyE2_String("Anlegen einer neuen Aufgabe nach Abschluss"), 
	            		new MyE2_String("OK"),
	            		new MyE2_String("-"),
	                    true,
	                    false,
	                    bibVECTOR.get_Vector(
	                    		new MyE2_String("Wird diese Aufgabe abgeschlossen und gespeichert,"), 
	                    		new MyE2_String("wird die selbe Aufgabe mit einem neuen Termin in "),
	                    		new MyE2_String(sKadenz + " Tagen angelegt.") 
	                    		),
	                    null);
	        }
	    }
	
}
