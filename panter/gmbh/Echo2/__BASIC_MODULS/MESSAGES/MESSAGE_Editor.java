package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_DATE;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.NACHRICHT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT_KATEGORIE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.utils.ECHO2.E2_AuswahlSelectorUsers;

/**
 * Klasse zum betrachten und erstellen von Messages 
 * @author manfred   
 *
 */
public class MESSAGE_Editor extends Project_BasicModuleContainer {

	
	private static final int c_initialHeight = 550;
	private static final int c_initialWidth = 700;

	// CacheObjekt zur speicherung von Wertepaar-Daten innerhalb des Editors (für Dropdown-Box)
	private  Hashtable<String, Object> htCachedObjects = new Hashtable<String, Object>();
		
	// data
	private enum EnumDisplayType 	{ READ, UNREAD, IMMEDIATE, NEW, SELECTED }

	//	private EnumStatus			m_mode = EnumStatus.NEW;
	private EnumDisplayType     m_displayType = EnumDisplayType.NEW;

	private RECLIST_NACHRICHT 	m_reclist_unread = null;
	private RECLIST_NACHRICHT   m_reclist_read = null;
	private RECLIST_NACHRICHT   m_reclist_immediate = null;
	private RECLIST_NACHRICHT   m_reclist_selected = null;
	
	
	private boolean 			m_closeAfterSave = false;
	private boolean				m_directShow = false;
	
	// es wird nur der Tab für die selektierten Meldungen angezeigt
	private boolean 		    m_display_selected_only = false;
	
	
	private boolean 			m_new_record = false;
	private E2_NavigationList   m_navigationList = null;
	
	// layout
	private GridLayoutData 		m_LayoutDataRightTop = null;
	

	// für neue Nachrichten ein eigenes Panel
	private MyE2_Grid			m_panel_new = null;
	private MyE2_Label 			m_Infotext_new = null;
	
	private MyE2_Grid			m_panel_unread  = null;
	private MyE2_Label 			m_Infotext_unread = null;
	private MESSAGE_SelectField_Kategorie  m_selKategorie_unread = null;
	
	// button, der alle ungelesenen Nachrichten als gelesen markiert.
	private MyE2_Button			m_btnSetAllUnreadAsRead = null;
	
	
	
	
//	private MyE2_Column			m_panel_read 	= null;
	private MyE2_Grid			m_panel_read 	= null;
	private MyE2_Label 			m_Infotext_read = null;
	private MESSAGE_SelectField_Kategorie  m_selKategorie_read = null;
	private MESSAGE_SelectField_MessageTyp m_selMessageType_read = null;
	
	
	private MyE2_Grid		m_panel_sofort 	 		= null;
	private MyE2_Label 			m_Infotext_sofort 	= null;
	private MyE2_SelectField	m_cmbPopUpDelay		= null;
	private MyE2_Button			m_btnPopUpDelay		= null;
	
	
	
	// button, der alle Sofort-Nachrichten als gelesen markiert.
	private MyE2_Button			m_btnSetAllSofortNachrichtAsRead = null;
	// button der alle Sofort-Nachrichten in normale umwandelt
	private MyE2_Button			m_btnSetAllSofort2Normal = null;
	
	
	
	private MyE2_Grid			m_panel_selected 	 	= null;
	private MyE2_Label 			m_Infotext_selected 	= null;

	
	private MyE2_TabbedPane		m_tabbedMain  = null;
	private MyE2_SelectField    m_cmbZeitraum_read = null;
	
	// selektor für die Kategorie
	
	
	
	// für die Wiedervorlage-Anzeige
	private String 				m_ID_Target = null;
	private String				m_Target	= null;
	private String 				m_TargetZusatz = null;
	private Vector<String> 		m_vIDEmpfaenger	= null;
	private String				m_sBetreff 	= null;
	private String 				m_sNachricht= null;
	private String				m_Kategorie = null;
	private Long 				m_IDKategorie = null;
	private String				m_DTVorlageAm = null;
	
	// cache für die Selektion der User...
	private USER_SELECTOR_GENERATOR  m_UserSelectorGenerator 	= null;
	
	
	
	/*
	 * 2012-02-09: eine referenz innerhalb der klasse des letzten erzeugten CMessage_Writing_Block 
	 *             wird mitgeloggt, um eine messagefenster von aussen beinflussen zu koennen
	 *             dieser block wird benutzt, um die eingabe-maske via setter beinflussen zu koennen
	 */
	private CMessage_Writing_Block   oLastCreatedMessageWritingBlock = null;
	
	
	/**
	 * Aufruf für Neuanlage eines Satzes 
	 * @param oNaviList
	 * @throws myException 
	 */
	public MESSAGE_Editor() throws myException{
		this((RECLIST_NACHRICHT)null,(E2_NavigationList)null, true, true, false);
	}

	
	/**
	 * wenn bIsNew, dann neuanlage einer Meldung
	 * bCloseAfterSave: der Dialog wird geschlossen, nach abschicken der Meldung
	 * @param bCloseAfterSave
	 * @param bIsNew
	 * @throws myException
	 */
	public MESSAGE_Editor(boolean bCloseAfterSave,boolean bIsNew) throws myException{
		this((RECLIST_NACHRICHT)null,(E2_NavigationList)null, bCloseAfterSave, bIsNew, false);
	}
	
	
	/**
	 * Aufruf für Neuanlage eines Satzes aus der Liste
	 * @param oNaviList
	 * @throws myException 
	 */
	public MESSAGE_Editor(E2_NavigationList oNaviList) throws myException{
		this((RECLIST_NACHRICHT)null,oNaviList, true,true, false);
	}

	

	/**
	 * Aufruf um zu editieren aus der Liste, zeigt nur die in der Reclist vorhandenen Nachrichten an, nicht die schon bestätigten
	 * @param reclist
	 * @param oNaviList
	 * @param bCloseAfterSave
	 * @throws myException
	 */
	public MESSAGE_Editor(RECLIST_NACHRICHT reclist, E2_NavigationList oNaviList, boolean bCloseAfterSave) throws myException{
		super(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_MASKE);
		initMessageEditor(reclist, oNaviList, bCloseAfterSave, false, false, null,null,null,null,null,null,true, null,null,null,null);
	}

	
	/**
	 * Aufruf für Direct-Show-Anzeige
	 * @param reclist
	 * @throws myException
	 */
	public MESSAGE_Editor(RECLIST_NACHRICHT reclist) throws myException{
		super(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_MASKE);
		initMessageEditor(null, null, false, false, true, null,null,null,null,null,null,false, reclist,null,null,null);
	}


	
	/**
	 * NEU-Erfassung einer Meldung mit den vordefinierten Daten aus dem MESSAGE_Entry-Objekt
	 * @param entry
	 * @throws myException
	 */
	public MESSAGE_Editor ( MESSAGE_Entry entry )throws myException {	
		super(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_MASKE);
		
		initMessageEditor((RECLIST_NACHRICHT)null, (E2_NavigationList)null, true, true, 
				entry.get_Sofortanzeige().booleanValue(), 
				entry.get_Titel(), 
				entry.get_Nachricht(), 
				entry.get_vIdEmpfaenger(), 
				entry.get_vTargets().get(0).get_IDTarget(),
				entry.get_vTargets().get(0).get_ModulTarget(),
				entry.get_vTargets().get(0).get_ModulTargetZusatz(),
				false, 
				null, 
				entry.get_Kategorie(), 
				entry.get_ID_Kategorie(),
				entry.get_DtAnzeigeAb() );
		
	}

	
	
	/**
	 * 
	 * @param reclist_Messages_unread
	 * @param oNaviList
	 * @param bCloseAfterSave
	 * @param bIsNew
	 * @param bDirectShow
	 * @throws myException
	 */
	private MESSAGE_Editor(	RECLIST_NACHRICHT reclist_Messages_unread, 
			E2_NavigationList oNaviList , 
			boolean bCloseAfterSave, 
			boolean bIsNew,
			boolean bDirectShow) 
	throws myException{
		super(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_MASKE);
		initMessageEditor(reclist_Messages_unread, oNaviList, bCloseAfterSave, bIsNew, bDirectShow, null,null,null,null,null,null,false, null,null,null,null);
	}
	

	/**
	 * cache-object-Hashtable
	 * @return
	 */
	public Hashtable<String, Object> getCacheObject(){
		return htCachedObjects;
	}
	

	/**
	 * 
	 * 
	 * @author manfred
	 * @date   09.07.2015
	 *
	 * @param reclist_Messages
	 * @param oNaviList
	 * @param bCloseAfterSave
	 * @param bIsNew
	 * @param bDirectShow
	 * @param Betreff
	 * @param Nachricht
	 * @param vIDEmpfaenger
	 * @param ID_Target
	 * @param Target
	 * @param TargetZusatz
	 * @param bDisplaySelectedOnly
	 * @param reclist_Messages_immediate
	 * @param Kategorie
	 * @param IDKategorie
	 * @throws myException
	 */
	private void initMessageEditor
						(	RECLIST_NACHRICHT reclist_Messages, 
							E2_NavigationList oNaviList , 
							boolean bCloseAfterSave, 
							boolean bIsNew,
							boolean bDirectShow,
							String Betreff, 
							String Nachricht, 
							Vector<String> vIDEmpfaenger, 
							String ID_Target, 
							String Target,
							String TargetZusatz,
							boolean bDisplaySelectedOnly,
							RECLIST_NACHRICHT reclist_Messages_immediate,
							String Kategorie,
							Long   IDKategorie,
							String sDTVorlageAm
							) throws myException {
		
		
		// Selektions-Daten für die user-selektionen
		m_UserSelectorGenerator =  new USER_SELECTOR_GENERATOR(true);
		
		// anzahl der Monate für die die Datumswerte in der Selektion selektiert werden sollen (Wert = Anzahl Monate)
		// Wareneingang/ -Ausgang
		String[][] 	arrZeitraum = new String[][]{
				{new MyE2_String("1 Monat").CTrans(),"-1"},
				{new MyE2_String("2 Monate").CTrans(),"-2"},
				{new MyE2_String("3 Monate").CTrans(),"-3"},
				{new MyE2_String("4 Monate").CTrans(),"-4"},
				{new MyE2_String("5 Monate").CTrans(),"-5"},
				{new MyE2_String("6 Monate").CTrans(),"-6"},
				{new MyE2_String("9 Monate").CTrans(),"-9"},
				{new MyE2_String("1 Jahr").CTrans(),"-12"},
				{new MyE2_String("2 Jahre").CTrans(),"-24"},
				{new MyE2_String("-").CTrans(),"-9999"}
				};
		
				
				
		String[][] 	arrPopUpDelay = new String[][]{
					{new MyE2_String("1 Minute").CTrans(),"1"},
					{new MyE2_String("5 Minuten").CTrans(),"5"},
					{new MyE2_String("10 Minuten").CTrans(),"10"},
					{new MyE2_String("15 Minuten").CTrans(),"15"}
					};		
		
		// parameter übernehmen
		this.m_sBetreff = Betreff;
		this.m_sNachricht = Nachricht;
		this.m_ID_Target = ID_Target;
		this.m_Target = Target;
		this.m_TargetZusatz = TargetZusatz;
		this.m_vIDEmpfaenger = vIDEmpfaenger;
		this.m_Kategorie = Kategorie;
		this.m_IDKategorie = IDKategorie;
		this.m_DTVorlageAm = sDTVorlageAm;
		
		
		this.m_tabbedMain = new MyE2_TabbedPane(null);
		this.m_tabbedMain.set_bAutoHeight(true);
		this.m_tabbedMain.setHeight(new Extent(c_initialHeight - 150,Extent.PX));
		
		// Tabreiter für die Neue Nachricht
		this.m_panel_new = new MyE2_Grid(1);
		this.m_panel_new.setWidth(new Extent(99,Extent.PERCENT));
		this.m_Infotext_new = new MyE2_Label(MyE2_Label.STYLE_NORMAL_BOLD());
		this.m_panel_new.add(m_Infotext_new, E2_INSETS.I_0_5_0_5);
		

		//
		// Tabreiter für die ungelesenen Nachrichten
		//
		this.m_panel_unread 				= new MyE2_Grid(1);
		this.m_panel_unread.setWidth(new Extent(99,Extent.PERCENT));

		this.m_Infotext_unread 			= new MyE2_Label(MyE2_Label.STYLE_NORMAL_BOLD());
		this.m_selKategorie_unread 		= new MESSAGE_SelectField_Kategorie(300,true,this);
		
		this.m_selKategorie_unread.add_oActionAgent(new actionAgentDisplayType_Selection(EnumDisplayType.UNREAD));
		
		MyE2_Grid grid_unread_inner 	= new MyE2_Grid(5,0);
		grid_unread_inner.add(m_Infotext_unread,5, E2_INSETS.I_5_5_5_5);
		grid_unread_inner.add(new MyE2_Label(new MyE2_String("Kategorie: ")),1,E2_INSETS.I_5_0_5_0);
		grid_unread_inner.add(m_selKategorie_unread, 3, E2_INSETS.I_5_0_5_0);
		
		if(ENUM_MANDANT_DECISION.NACHRICHT_BUTTON_SET_ALL_AS_READ.is_YES()){
			m_btnSetAllUnreadAsRead = new MyE2_Button(new MyString("Ich habe alle gelesen!"));
			m_btnSetAllUnreadAsRead.setToolTipText("Setzt für alle Nachrichten im Fenster den Status auf 'Bestätigt'");
			m_btnSetAllUnreadAsRead.add_oActionAgent(new actionAgentSetAllAsRead(EnumDisplayType.UNREAD, m_panel_unread));
			grid_unread_inner.add(m_btnSetAllUnreadAsRead, E2_INSETS.I_20_2_2_2);
		} else {
			grid_unread_inner.add(new MyE2_Label(""), E2_INSETS.I_20_2_2_2);
		}

		
		
		this.m_panel_unread.add(grid_unread_inner);
		
		
		//
		// Tabreiter für die gelesenen Nachrichten
		//
		this.m_panel_read 				= new MyE2_Grid(1);
		this.m_panel_read.setWidth(new Extent(99,Extent.PERCENT));
		this.m_Infotext_read 			= new MyE2_Label(MyE2_Label.STYLE_NORMAL_BOLD());

		this.m_cmbZeitraum_read 		= new MyE2_SelectField(arrZeitraum, null, false);
		this.m_selKategorie_read 		= new MESSAGE_SelectField_Kategorie(300,true,this);
		this.m_selMessageType_read		= new MESSAGE_SelectField_MessageTyp(300);
		
		this.m_cmbZeitraum_read.add_oActionAgent(new actionAgentZeitraum());
		this.m_selKategorie_read.add_oActionAgent(new actionAgentDisplayType_Selection(EnumDisplayType.READ));
		this.m_selMessageType_read.add_oActionAgent(new actionAgentDisplayType_Selection(EnumDisplayType.READ));
		

		MyE2_Grid grid_read_inner 	= new MyE2_Grid(4,0);
		grid_read_inner.add(m_Infotext_read, 4, E2_INSETS.I_5_5_5_5);
		grid_read_inner.add(new MyE2_Label(new MyE2_String("Zeitraum: ")),1,E2_INSETS.I_5_0_5_0);
		grid_read_inner.add(m_cmbZeitraum_read,3,E2_INSETS.I_5_0_5_0);
		grid_read_inner.add(new MyE2_Label(new MyE2_String("Kategorie: ")),1,E2_INSETS.I_5_0_5_0);
		grid_read_inner.add(m_selKategorie_read, 3, E2_INSETS.I_5_0_5_0);
		grid_read_inner.add(new MyE2_Label(new MyE2_String("Typ: ")),1,E2_INSETS.I_5_0_5_0);
		grid_read_inner.add(m_selMessageType_read,3,E2_INSETS.I_5_0_5_0);
		
		this.m_panel_read.add(grid_read_inner);
		
		// Tabreiter für die Sofortnachrichten
		this.m_panel_sofort = new MyE2_Grid(1);
		this.m_panel_sofort.setWidth(new Extent(99,Extent.PERCENT));
		this.m_Infotext_sofort = new MyE2_Label(MyE2_Label.STYLE_NORMAL_BOLD());
		this.m_panel_sofort.add(m_Infotext_sofort, E2_INSETS.I_0_5_0_5);
		
		// grid für die Anzeigenverzögerung der Sofortnachrichten
		MyE2_Grid grid_PopUpDelay 	= new MyE2_Grid(5, 0);
		m_cmbPopUpDelay				= new MyE2_SelectField(arrPopUpDelay, null, false);
		m_btnPopUpDelay = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));
		m_btnPopUpDelay.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String sdelay = null;
				int delay = 0;
				try {
					sdelay = m_cmbPopUpDelay.get_ActualWert();
					delay = Integer.parseInt(sdelay);
				} catch (myException e) {
					delay = 0;
				}
				
				if(delay > 0){
					Calendar cal = GregorianCalendar.getInstance();
					cal.add(Calendar.MINUTE, delay);
					bibSES.set_Messages_Popup_TimeStamp(cal);
					
					// editor neu aufbauen
					MESSAGE_Editor oEditor = MESSAGE_Editor.this;
					oEditor.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
				}
			}
		});
		
		
		
		
		// GUI PopupDelay
		grid_PopUpDelay.add(new MyE2_Label("Sofort-Nachrichten deaktivieren für "), E2_INSETS.I_0_2_0_2);
		grid_PopUpDelay.add(m_cmbPopUpDelay);
		grid_PopUpDelay.add(m_btnPopUpDelay);

		
		
		if(ENUM_MANDANT_DECISION.NACHRICHT_BUTTON_SET_ALL_AS_READ.is_YES()){
			m_btnSetAllSofortNachrichtAsRead = new MyE2_Button(new MyString("Bestätige alle Meldungen"));
			//martin: etwas groesser formatiert 
			m_btnSetAllSofortNachrichtAsRead._bordDDDark()._backColorBase()._insets(E2_INSETS.I(6,2,6,2))
														._setImages(E2_ResourceIcon.get_RI("ok.png") , E2_ResourceIcon.get_RI("leer.png"));;
			m_btnSetAllSofortNachrichtAsRead.setToolTipText("Setzt für alle Nachrichten im Fenster den Status auf 'Bestätigt'");
			m_btnSetAllSofortNachrichtAsRead.add_oActionAgent(new actionAgentSetAllAsRead(EnumDisplayType.IMMEDIATE, m_panel_sofort));
			grid_PopUpDelay.add(m_btnSetAllSofortNachrichtAsRead, E2_INSETS.I(30, 0,0,0));
		} else {
			grid_PopUpDelay.add(new MyE2_Label(""), E2_INSETS.I(30, 0,0,0));
		}
		
		if(ENUM_MANDANT_DECISION.NACHRICHT_BUTTON_SET_ALL_AS_NORMAL.is_YES()){
			m_btnSetAllSofort2Normal = new MyE2_Button(new MyString("Sofortanzeige für alle Meldungen beenden"));
			//martin: etwas groesser formatiert 
			m_btnSetAllSofort2Normal._bordDDDark()._backColorBase()._insets(E2_INSETS.I(6,2,6,2))
										._setImages(E2_ResourceIcon.get_RI("pfeil_rechts.png") , E2_ResourceIcon.get_RI("leer.png"));
			m_btnSetAllSofort2Normal.setToolTipText("Beendet für alle Nachrichten im Fenster die Sofortanzeige" );
			m_btnSetAllSofort2Normal.add_oActionAgent(new actionAgentSetAll2Normal(EnumDisplayType.IMMEDIATE, m_panel_sofort));
			
			grid_PopUpDelay.add(m_btnSetAllSofort2Normal, E2_INSETS.I(30, 0,0,0));
		} else {
			grid_PopUpDelay.add(new MyE2_Label(""), E2_INSETS.I(30, 0,0,0));
		}
		

	
		
		m_panel_sofort.add(grid_PopUpDelay);
		
		
		
		// Tabreiter für die Nachrichten die als Liste übergeben worden
		this.m_panel_selected = new MyE2_Grid(1);
		this.m_panel_selected.setWidth(new Extent(99,Extent.PERCENT));
		this.m_Infotext_selected = new MyE2_Label(MyE2_Label.STYLE_NORMAL_BOLD());
		this.m_panel_selected.add(m_Infotext_selected, E2_INSETS.I_0_5_0_5);
		
		this.add(m_tabbedMain);
		
		// Elemente im Grid setzen
		m_LayoutDataRightTop  = new GridLayoutData();
		m_LayoutDataRightTop.setAlignment(new Alignment(Alignment.RIGHT, Alignment.TOP));
		m_LayoutDataRightTop.setColumnSpan(1);
		m_LayoutDataRightTop.setInsets(E2_INSETS.I_0_0_5_0);

		
		m_closeAfterSave = bCloseAfterSave;
		m_navigationList = oNaviList;
		m_directShow = bDirectShow;
		m_new_record = bIsNew;
		m_display_selected_only = bDisplaySelectedOnly;
		
		
		// die Liste der unbestätigten Nachrichten übernehmen, die gelesenen werden hier dynamisch zugeladen
		
		// Sonderbehandlung: Selektion von vorgegebenen Records
		if (bDisplaySelectedOnly){
			m_reclist_selected = reclist_Messages;
		} else {
			m_reclist_unread = reclist_Messages;
			m_reclist_immediate = reclist_Messages_immediate;
		}
		
		//
		// Tabs initialisieren
		//
		
		// wenn die Sofortanzeige von aussen aufgerufen wird, dann auf jeden Fall nur die Sofortanzeige anzeigen
		if (m_reclist_immediate != null && m_reclist_immediate.size() > 0 ){
			// Tab aufbauen
			this.m_tabbedMain.add_Tabb(new MyString("Sofortanzeige"), m_panel_sofort,new actionAgentForTab(EnumDisplayType.IMMEDIATE) );
			
		} else {
			
			// Wenn es eine neue Nachricht geben soll
			if (m_new_record ){
				this.m_tabbedMain.add_Tabb(new MyString("Neue Nachricht"), m_panel_new,new actionAgentForTab(EnumDisplayType.NEW));
			} else {
				
				// falls es Selektierte sind, nur diese anzeigen
				if (m_reclist_selected != null && m_reclist_selected.size() > 0){
					this.m_tabbedMain.add_Tabb(new MyString("Ausgewählte Nachrichten"), m_panel_selected,new actionAgentForTab(EnumDisplayType.SELECTED) );
					
				} else {
					// ermitteln der ungelesenen Nahcrichtigen
					m_reclist_unread = getNachrichten(EnumDisplayType.UNREAD);
					// sonst die ungelesenen und gelesene anzeigen
					if (m_reclist_unread != null && m_reclist_unread.size() > 0 ){
						//ungelesene Nachrichten
						this.m_tabbedMain.add_Tabb(new MyString("Unbestätigte Nachrichten"), m_panel_unread,new actionAgentForTab(EnumDisplayType.UNREAD) );
					} else {
						// oder neue Nachricht
						this.m_tabbedMain.add_Tabb(new MyString("Neue Nachricht"), m_panel_new,new actionAgentForTab(EnumDisplayType.NEW));
					}
				}
				
				// wenn kein DirectShow-Aufruf, dann werden auch die bestätigten Nachrichten gelesen und angezeigt 
				m_reclist_read = null;
				if (!m_directShow && !m_new_record && !m_display_selected_only){
					m_reclist_read 	 = getNachrichten(EnumDisplayType.READ);
					if (m_reclist_read != null && m_reclist_read.size() > 0 ){
						this.m_tabbedMain.add_Tabb(new MyString("Bestätigte Nachrichten"), m_panel_read,new actionAgentForTab(EnumDisplayType.READ) );
					}
				}
			}
		}

		
		try {
			this.initModules();
			setToPassiveAction();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (m_navigationList != null){
					m_navigationList.RefreshList();
				}
			}
		});

		
		//vergroesserungsabstand fuer das tabbed-pane definieren
		this.set_iVerticalOffsetForTabbedPane(150);
		
		// Search-Tag für die Findung des Fenstgers:
		if (m_directShow){
			this.EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.NACHRICHTEN_EDITOR_SOFORT);
		} else {
			this.EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.NACHRICHTEN_EDITOR);
		}
		
	
		try {
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(c_initialWidth), new Extent(c_initialHeight), new MyE2_String("Nachricht"));
		} catch (myException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setToPassiveAction() {
		// Alle Buttons auf Passiv stellen
		MyE2_Grid otitelComp = this.get_oGridTopLineButtonsAndMessages();
		for (int i=0; i< otitelComp.getComponentCount(); i++){
			Component  c = otitelComp.getComponent(i);
			if (c instanceof MyE2_Button){
				((MyE2_Button)c).set_bPassivAction(true);
			}
		}
	}
	
	
	
	
	/**
	 * ermitteln der Nachrichten, die angezeigt werden sollen, anhand des Typs
	 * @param type
	 * @return
	 */
	private RECLIST_NACHRICHT getNachrichten(EnumDisplayType type){

		RECLIST_NACHRICHT recList = null;
		String sQuery = "";
		String sZeitraum = "";
		try {
			sZeitraum = this.m_cmbZeitraum_read.get_ActualWert();
		} catch (myException e) {
			sZeitraum = "-1";
		}
		
		String sWHEREZeitraum = " AND AKTIV_AB  >= ADD_MONTHS(SYSDATE," + sZeitraum + ")";
		String sWHEREKategorie = "";
		String sWHEREType = "";
		
		String sIDKategorie = "";
		
		switch (type) {
		case READ:
			sIDKategorie = "";
			try {
				 sIDKategorie = this.m_selKategorie_read.get_ActualWert();
				 if (!bibALL.isEmpty(sIDKategorie)){
					 if (sIDKategorie.equals("-1")){
						 sWHEREKategorie = " AND ID_NACHRICHT_KATEGORIE is null ";
					 } else {
						 sWHEREKategorie = " AND ID_NACHRICHT_KATEGORIE = " + sIDKategorie;
					 }
				 }
				 
			} catch (myException e) {
				e.printStackTrace();
			}
					
			try {
				sWHEREType = this.m_selMessageType_read.get_ActualWert();
				if (!bibALL.isEmpty(sWHEREType)){
					sWHEREType = " AND " + sWHEREType;
				}
			} catch (Exception e) {
				sWHEREType = "";
			}
			
			sQuery = "	SELECT * FROM   " + bibE2.cTO() + ".JT_NACHRICHT " +
			"	WHERE  NVL(BESTAETIGT,'N')='Y'  " +
			"	 AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') " +
			"	 AND NVL(GELOESCHT,'N') = 'N' " +
			"	 AND ID_USER = " + bibALL.get_ID_USER() +
			sWHEREZeitraum + " " +
			sWHEREKategorie + " " +
			sWHEREType + " " + 
			" ORDER BY AKTIV_AB DESC, ID_NACHRICHT DESC ";
			break;
			
		case UNREAD:
			sIDKategorie = "";
			try {
				 sIDKategorie = this.m_selKategorie_unread.get_ActualWert();
				 if (!bibALL.isEmpty(sIDKategorie)){
					 if (sIDKategorie.equals("-1")){
						 sWHEREKategorie = " AND ID_NACHRICHT_KATEGORIE is null ";
					 } else {
						 sWHEREKategorie = " AND ID_NACHRICHT_KATEGORIE = " + sIDKategorie;
					 }
				 }
			} catch (myException e) {
				e.printStackTrace();
			}
			
			sQuery = "	SELECT * FROM   " + bibE2.cTO() + ".JT_NACHRICHT " +
			"	WHERE  NVL(BESTAETIGT,'N')='N'  " +
			"	 AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') " +
			"	 AND NVL(SOFORTANZEIGE,'Y') = 'N' " +
			"	 AND NVL(GELOESCHT,'N') = 'N' " +
			"	 AND ID_USER = " + bibALL.get_ID_USER() +
			sWHEREKategorie + " " + 
			" ORDER BY AKTIV_AB, ID_NACHRICHT  ";
			break;
			
		case IMMEDIATE:
			sQuery = "	SELECT * FROM   " + bibE2.cTO() + ".JT_NACHRICHT " +
			"	WHERE  NVL(BESTAETIGT,'N')='N'  " +
			"	 AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') " +
			"	 AND NVL(SOFORTANZEIGE,'Y') = 'Y' " +
			"	 AND NVL(GELOESCHT,'N') = 'N' " +
			"	 AND ID_USER = " + bibALL.get_ID_USER() +
			" ORDER BY AKTIV_AB, ID_NACHRICHT  ";
			break;
		default:
			break;
		}
		
		
		try
		{
			recList = new RECLIST_NACHRICHT( sQuery );
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error reading Messages !",false)));
		}
		
		return recList;
	}
	
	
	
	
	private void initModule (EnumDisplayType displayType) throws myException{
		// Panel ermitteln
		MyE2_Grid oPanel = null;
		MyE2_Label  olblInfo = null;
		RECLIST_NACHRICHT recListNachrichten = null;
		
		if (displayType.equals(EnumDisplayType.IMMEDIATE)){
			oPanel = m_panel_sofort;
			olblInfo = m_Infotext_sofort;
			recListNachrichten = m_reclist_immediate;
		} else if (displayType.equals(EnumDisplayType.UNREAD)){
			oPanel = m_panel_unread;
			olblInfo = m_Infotext_unread;
			recListNachrichten = m_reclist_unread;
		} else 	if (displayType.equals(EnumDisplayType.READ)){
			oPanel = m_panel_read;
			olblInfo = m_Infotext_read;
			recListNachrichten = m_reclist_read;
		} else if (displayType.equals(EnumDisplayType.NEW)){
			oPanel = m_panel_new;
			olblInfo = m_Infotext_new;
			recListNachrichten = null;
		} else if (displayType.equals(EnumDisplayType.SELECTED)){
			oPanel = m_panel_selected;
			olblInfo = m_Infotext_selected;
			recListNachrichten = m_reclist_selected;
		}else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Meldungstyp nicht bekannt !",false)));
		}
	
		olblInfo.setText("");
		
		// alle Message-Blöcke im Tab rauswerfen
		for (int i=oPanel.getComponentCount()-1; i >= 0; i--){
			Component  c = oPanel.getComponent(i);
			if (c instanceof CMessageDisplayBlock){
				oPanel.remove(c);
			}
		}

		
		CMessageDisplayBlock oblock = null;

		// Sonderbehandlung NEW
		if (displayType.equals(EnumDisplayType.NEW)){
			olblInfo.setText("Neue Nachricht");
			oblock = new CMessageDisplayBlock(null,1);
			if (oblock != null){
				oPanel.add(oblock.getMessageBlock(),E2_INSETS.I_0_0_0_10);
			}
		} else {
			
			if (recListNachrichten == null ){
				return;
			} 
			
			// Info-Zeile
			String sInfo = Integer.toString(recListNachrichten.size()) +  new MyString(" Nachrichten").CTrans();
			olblInfo.setText(sInfo);
			
			// Einträge
			for (int i=0; i < recListNachrichten.size(); i++){
				oblock = new CMessageDisplayBlock(recListNachrichten.get(i),i);
				if (oblock != null){
					oPanel.add(oblock.getMessageBlock(),E2_INSETS.I_0_0_0_10);
				}
			}
		}
	}
	
	
	
	private void initModules() throws myException{
		initModule(EnumDisplayType.IMMEDIATE);
		initModule(EnumDisplayType.SELECTED);
		initModule(EnumDisplayType.NEW);
		initModule(EnumDisplayType.UNREAD);
		initModule(EnumDisplayType.READ);
	}


	
	
	/**
	 * Prüft, ob noch mehr unbeantwortete Meldungen da sind. Wenn ja, werden diese angezeigt,
	 * wenn nein wird der Dialog beendet.
	 * @throws myException 
	 */
	private void refreshModule() throws myException{

		if (!m_closeAfterSave){
			if (m_reclist_unread != null){
				m_reclist_unread.REFRESH();
			}
			if (m_reclist_immediate != null){
				m_reclist_immediate.REFRESH();
			}
			if (m_reclist_selected != null){
				m_reclist_selected.REFRESH();
			}
			
			if ((m_reclist_unread != null && m_reclist_unread.size() > 0) || 
				(m_reclist_immediate != null && m_reclist_immediate.size() > 0 ) ||	
				(m_reclist_selected != null && m_reclist_selected.size() > 0 )){
				// wenn noch mehr Nachrichten da sind, dann wird das ganze upgedatet
				this.initModules();
			} else {
				this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		} else {
			this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	

	

	
	/**
	 * Speichert eine Nachricht oder eine Antwort
	 * und fügt eine Verbindung zu einem Modul ein
	 * @param sTitel
	 * @param sNachricht
	 * @param vIdEmpfaenger
	 * @param rec_ori
	 * @throws myException 
	 */
	private boolean saveMessage(String sTitel, String sNachricht, Vector<String> vIdEmpfaenger, RECORD_NACHRICHT rec_ori, Boolean bSofortanzeige, String sDtAnzeigeAb,
			String ID_Target, String MODUL_Target, String MODUL_Zusatz, String sIDKategorie, Boolean bSendEmail) throws myException{
		
		MESSAGE_Handler oHandler = new MESSAGE_Handler();
		
		// wenn die ID der Meldungskategorie direkt gesetzt werden soll, dann muss sie umgesetzt werden in ein Long
		Long lIDKategorie = null;
		if (!bibALL.isEmpty(sIDKategorie )){
			try {
				lIDKategorie = Long.parseLong(sIDKategorie);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		Vector<MESSAGE_Entry_Target> vTargets = new Vector<MESSAGE_Entry_Target>();
		if (!bibALL.isEmpty(MODUL_Target) ){
			MESSAGE_Entry_Target target = new MESSAGE_Entry_Target(ID_Target,MODUL_Target,MODUL_Zusatz,null);
			vTargets.add(target);
		}
		
		
		MESSAGE_Entry oEntry = new MESSAGE_Entry()
		.set_Titel(sTitel)
		.set_Nachricht(sNachricht)
		.set_vIdEmpfaenger(vIdEmpfaenger)
		.set_Sofortanzeige(bSofortanzeige)
		.set_DtAnzeigeAb(sDtAnzeigeAb)
		.set_rec_Nachricht_ori(rec_ori)
		.set_vTargets(vTargets)
		.set_ID_Kategorie(lIDKategorie)
		.set_SendEmail(bSendEmail)
		;
		
		return oHandler.saveMessage(oEntry);
		
	}

	

	
	/**
	 * Klasse für die Anzeige/Verwaltung eines Nachrichtenblocks
	 * @author manfred
	 *
	 */
	private class CMessageDisplayBlock extends  MyE2_Column{

		MyE2_Grid					m_gridMessageBlock = null;

		RECORD_NACHRICHT 			m_rec = null;
		ownButton		 			m_btnBestaetigen = null;
		ownButton					m_btnConvertSofort2Normal = null;
		
		
		
		CMessage_Writing_Block		m_BlockErfassen = null;
		E2_ExpandableRow 			m_rowAntwort = null;
		E2_ExpandableRow			m_rowHistory = null;
		
		E2_ExpandableRow			m_rowMessage = null;
		
		E2_ColorBase				m_Background = new E2_ColorBase();
		E2_ColorBase				m_BackgroundField = new E2_ColorBase(10);
		
		
		// Wiedervorlage-Button, um eine einfach Wiedervorlage durchzuführen
		ownButton		 				m_btnWV = null;
		UB_TextField_With_DatePOPUP_OWN m_dtWVAm = null;
		MyE2_CheckBox					m_cbWVAm = null;
		
		MODUL_LINK_Connector				m_oModulLinks = null;
		
		BT_AttachementToNachricht 		m_btAttachementToNachricht = null;
		
		
		//2011-11-17: unterschiedliche darstellung der message, je nachdem, ob standard-benutzer-meldung oder system-meldung
		boolean bIstSystemMessage = false;
		boolean bIstUserMessage   = true;    //alter standard
		boolean bIstUndefined   = false;

		
		public CMessageDisplayBlock(RECORD_NACHRICHT oRec, int pos) throws myException {
			m_rec = oRec;
			this.setStyle(MyE2_Column.STYLE_3D_BORDER());
			
			
			//martin:  standardfarben
			m_Background = 			new E2_ColorLight();
			m_BackgroundField = 	new E2_ColorLLLight();
			//martin

			//2011-11-17: unterschiedliche darstellung der message, je nachdem, ob standard-benutzer-meldung oder system-meldung
			if (this.m_rec!=null)
			{
				_RECORD_NACHRICHT   recHelp = new _RECORD_NACHRICHT(this.m_rec);
				this.bIstSystemMessage = recHelp.get_bIsSystemNachricht();
				this.bIstUserMessage   = recHelp.get_bIsUserNachricht();
				this.bIstUndefined   = recHelp.get_bIsUndefinedNachricht();
			}

			this.setBackground(m_Background);
			
			this.initMessageBlock();
		}
		
		
		/**
		 * Gibt den Block mit der Meldung zurück
		 * @return
		 */
		public MyE2_Column getMessageBlock(){
			return this;
		}
		
		
		
		
		/**
		 * Initialisieren des Moduls
		 * @throws myException 
		 */
		private void initMessageBlock() throws myException{
			
			// Schreibe-Block generieren, brauchen wir auf jeden Fall
			m_BlockErfassen = new CMessage_Writing_Block(m_rec);

			MyE2_Label lblHeading = null;
			// Maske aufbauen
			if (m_rec != null){
				
				// den Block für die Meldung erzeugen
				createMessageDisplayBlock();                 //hier wird m_gridMessageBlock erzeugt
				
				this.add(m_gridMessageBlock,E2_INSETS.I_0_0_0_0);
				
				if (this.bIstUserMessage || this.bIstUndefined)
				{
					m_rowAntwort = new E2_ExpandableRow(new MyE2_String("Nachricht beantworten..."), new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
					m_rowAntwort.add(m_BlockErfassen,E2_INSETS.I_0_0_0_0);
					m_rowAntwort.get_oButtonClose().doActionPassiv();
					m_rowAntwort.get_oButtonClose().set_bPassivAction(true);
					m_rowAntwort.get_oButtonOpen().set_bPassivAction(true);
					this.add(m_rowAntwort,E2_INSETS.I_0_0_5_0);

				}
				else if (this.bIstSystemMessage)
				{
					
				}
				else
				{
					throw new myException(this,"undefined message-status (1)");
				}
				
				
				
			} else {
				
				// Heading eintragen
				lblHeading = new MyE2_Label(new MyString("Eine neue Nachricht verfassen."));
				lblHeading.setFont(new E2_FontBold(2));
				
				//martin
				this.add(lblHeading, E2_INSETS.I_5_5_5_5);
				//martin
				
				//martin
				this.add(m_BlockErfassen, E2_INSETS.I_0_0_0_0);
				//martin
			}
			
		}
		
		
		
		

		
		
		
		
		//martin: createMessageDisplayBlock veraendert
		private void createMessageDisplayBlock(  ) throws myException{
			
			
			// wenn keine Nachricht vorhanden ist, dann gleich zurück
			if (m_rec == null){
				return ;
			}
			
			String sHeader = "";
			String sSender = "";
			String sReceiver = "";
			
			RECORD_USER oUserSender = null;
			try {
				oUserSender = new RECORD_USER(m_rec.get_ID_USER_SENDER_cUF());
				sSender  = oUserSender.get___KETTE(bibALL.get_Vector( "VORNAME","NAME1")) ; //+ " (" + oUserSender.get_NAME_cUF() + ")";
			} catch (Exception e) {
				sSender = "-";	
			}
			
			RECORD_USER oUser = null;
			if (! m_rec.get_ID_USER_cUF_NN("-1").equals(bibALL.get_ID_USER())){
				try {
						// wenn "ich" der Empfänger ist, dann nicht anzeigen
						oUser = new RECORD_USER(m_rec.get_ID_USER_cUF());
						sReceiver = " an " +  oUser.get___KETTE(bibALL.get_Vector( "VORNAME","NAME1")) ;//+ " (" + oUser.get_NAME_cUF() + ")";
				} catch (Exception e) {
					sReceiver = "-";
				}
			}
			
			
			String sDTAktiv = m_rec.get_AKTIV_AB_cF();
			Color  col = m_Background;
			
			if (sDTAktiv != null){
				myDateHelper dtHelp = new myDateHelper(sDTAktiv);
				GregorianCalendar  cal = dtHelp.get_oCalDate();
				GregorianCalendar  calNow = new GregorianCalendar();
				
				int diff = (int)myDateHelper.get_DayDifference_Date2_MINUS_Date1(cal , calNow);
				if (diff >= 0){
					// Jetzt oder Vergangenheit 
					switch (diff) {
					case 0:
						sDTAktiv = new MyString("Heute").CTrans();
						col = Color.GREEN;
						break;
					case 1:
						sDTAktiv = new MyString("Gestern").CTrans();
						col = Color.ORANGE;
						break;
					case 2: 
						sDTAktiv = new MyString("Vorgestern").CTrans();
						col = Color.RED;
						break;
					default:
						sDTAktiv = new MyString("vor ") + Integer.toString(diff) + new MyString(" Tagen").CTrans() + " (" + sDTAktiv + ")";
						col = Color.RED;
						break;
					}
				} else {
					// Zukunft
					diff = diff * -1;
					col = Color.GREEN;
					switch (diff) {
					case 1:
						sDTAktiv = new MyString("Morgen").CTrans();
						break;
					case 2: 
						sDTAktiv = new MyString("Übermorgen").CTrans();
						break;
					default:
						sDTAktiv = new MyString("in ") + Integer.toString(diff) + new MyString(" Tagen").CTrans() + " (" + sDTAktiv + ")";
						break;
					}
				}

			}
			
			
			
			sHeader =  m_rec.get_TITEL_cUF_NN("- kein Betreff -_");

			// Kategorie
			String 		sKategorie		= "-";
			RECORD_NACHRICHT_KATEGORIE rec_Kat = m_rec.get_UP_RECORD_NACHRICHT_KATEGORIE_id_nachricht_kategorie();
			if (rec_Kat != null){
				sKategorie = rec_Kat.get_KATEGORIE_cUF_NN("-");
			}
			
			MyE2_Label lblKategorieDesc 		= new MyE2_Label(new MyString("Kategorie "));
			MyE2_Label lblKategorie		= new MyE2_Label(new MyString(sKategorie));

			
			MyE2_Label lblVon 	= new MyE2_Label(new MyString("Von"));
			lblVon.setToolTipText(m_rec.get_ID_NACHRICHT_cUF_NN(""));
			
			MyE2_Label lblBetreff = new MyE2_Label(new MyString("Betreff"));
			MyE2_Label lblNachricht = new MyE2_Label(new MyString("Nachricht"));
			
			MyE2_TextArea tfNachricht	= new MyE2_TextArea("", 550, 4000, 10);
			tfNachricht.set_bEnabled_For_Edit(false);
			tfNachricht.setBackground(m_BackgroundField);
			
			// höhe der Nachricht dynamisch anpassen
			String sNachricht = m_rec.get_NACHRICHT_cUF();
			String[] rows = sNachricht.split("\n");
			int   rows_len = sNachricht.length() / 50 ;
			int r = (int) Math.round((rows.length + 2) * 1.1);
			int rResult = (r > rows_len ? r: rows_len);
			tfNachricht.setHeight(new Extent((int) (rResult ) ,Extent.PC));
		
			
			// Sender / Empfänger-Angaben
			MyE2_Label lblSender 	= new MyE2_Label(sSender + sReceiver);
			lblSender.setFont(new E2_FontBold());
			
			// Gültigkeitsdatum
			MyE2_Label lblValidity = new MyE2_Label(sDTAktiv);
			lblValidity.setBackground(col);
			
			// Titelangaben
			MyE2_Label lblTitel 	= new MyE2_Label(sHeader);
			lblTitel.setFont(new E2_FontBold(1));
			
			// Nachrichtentext
			tfNachricht.setText(sNachricht);
			
			//rowButtons.setAlignment(Alignment.ALIGN_RIGHT);
			
			// die Knöpfe für Anhänge einbauen
			m_btAttachementToNachricht = new BT_AttachementToNachricht(m_rec.get_ID_NACHRICHT_cUF());
			
			
			// das Modul für die Verlinkung einbauen
			m_oModulLinks = new MODUL_LINK_Connector(  );
			m_oModulLinks.initConnector(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE, m_rec.get_ID_NACHRICHT_cUF(), MESSAGE_Editor.this );

			// Bestätigungsbutton
			m_btnBestaetigen = new ownButton(new MyString("Bestätigen"),E2_ResourceIcon.get_RI("ok.png") , E2_ResourceIcon.get_RI("leer.png"));
			m_btnBestaetigen.setToolTipText(new MyString("Bestätigt die Nachricht und schließt den Dialog.").CTrans());
			m_btnBestaetigen.setInsets(E2_INSETS.I_10_2_10_2);
			m_btnBestaetigen.set_bEnabled_For_Edit(m_rec != null && m_rec.get_BESTAETIGT_cUF_NN("N").equals("N"));
			
			
			
			if (m_rec.get_BESTAETIGT_cUF_NN("N").equals("Y")){
				// wenn schon bestätigt, dann disablen
				m_btnBestaetigen.set_Text(new MyString("Bestätigt!"));
				m_btnBestaetigen.set_bEnabled_For_Edit(false);
			}

			
			// Umwandeln einer Sofortanzeige in eine normale Anzeige
			if(m_rec.get_SOFORTANZEIGE_cUF_NN("N").equals("Y")){
				m_btnConvertSofort2Normal = new ownButton(new MyString("Sofortanzeige beenden"),E2_ResourceIcon.get_RI("pfeil_rechts.png") , E2_ResourceIcon.get_RI("leer.png"));
				m_btnConvertSofort2Normal.setToolTipText(new MyString("Wandelt die Sofortnachricht in eine normale, unbestätigte Nachricht um.").CTrans() );
				m_btnConvertSofort2Normal.setInsets(E2_INSETS.I_10_2_10_2);
				m_btnConvertSofort2Normal.set_bEnabled_For_Edit(m_rec != null && m_rec.get_SOFORTANZEIGE_cUF_NN("N").equals("Y") && m_rec.get_BESTAETIGT_cUF_NN("N").equals("N") );
			}
			
			
			
			// Wiedervorlage-Button
			m_cbWVAm = new MyE2_CheckBox("Wiedervorlage am", new MyE2_String("Ermöglicht die direkte Wiedervorlage zum gewählten Datum."));
			((MutableStyle)m_cbWVAm.getStyle()).setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
			m_cbWVAm.set_bPassivAction(true);
			m_cbWVAm.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					m_btnWV.set_bEnabled_For_Edit(m_cbWVAm.isSelected());
					m_dtWVAm.set_bEnabled_For_Edit(m_cbWVAm.isSelected());
				}
			});
			
			m_btnWV = new ownButton(new MyString("Speichern"),E2_ResourceIcon.get_RI("ok.png") , E2_ResourceIcon.get_RI("leer.png"));
			m_btnWV.setToolTipText(new MyE2_String("Speichert die Wiedervorlage ab und bestätigt die aktuelle Nachricht.").CTrans());
			GregorianCalendar cal = new GregorianCalendar();
			String sDate =  myDateHelper.FormatDateNormal(cal.getTime());
			m_btnWV.set_bEnabled_For_Edit(false);
			m_btnWV.set_bPassivAction(true);
			m_btnWV.add_oActionAgent( new actionAgentReminder());
						
			
			m_dtWVAm 	= new UB_TextField_With_DatePOPUP_OWN("WIEDERVORLAGE_AM",true,sDate, 100);
			m_dtWVAm.set_bPassivAction(true);
			m_dtWVAm.add_InputValidator(new VALIDATE_INPUT_DATE());
			m_dtWVAm.set_bEnabled_For_Edit(false);
			m_dtWVAm.set_bEmptyAllowd(false);
			m_dtWVAm.get_oTextField().set_bEnabled_For_Edit(false);
			
			
			
			if (this.bIstUserMessage || this.bIstUndefined)
			{
				// Komponenten ins Grid setzen
				m_gridMessageBlock = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				
				m_gridMessageBlock.add(lblVon,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_5_5_0));
				m_gridMessageBlock.add(lblSender,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_0_0));
				m_gridMessageBlock.add(lblValidity.get_InBorderGrid(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID), null, E2_INSETS.I_2_2_2_2),LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_0_5_0_0));
				
				m_gridMessageBlock.add(lblKategorieDesc,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
				m_gridMessageBlock.add(lblKategorie,  LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5, 2));
				
				m_gridMessageBlock.add(lblBetreff,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
				m_gridMessageBlock.add(lblTitel,  LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5, 2));
	
				m_gridMessageBlock.add(lblNachricht,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
				m_gridMessageBlock.add(tfNachricht, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5, 2));
				
				// Sprung auf Anhänge
				if (m_btAttachementToNachricht.bIsVisible()){
					m_gridMessageBlock.add(new MyE2_Label(""),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
					//m_gridMessageBlock.add(m_btAttachementToNachricht,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5,2));
					E2_ComponentGroupHorizontal_NG grp 
						= new E2_ComponentGroupHorizontal_NG(	
								m_btAttachementToNachricht, 
								new MyE2_Label(m_btAttachementToNachricht.getDescription()) 
							, LayoutDataFactory.get_GridLayoutGridLeftMID(new Insets(0,0,20,0), 1));
					m_gridMessageBlock.add(grp,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5,2));
				}
				
				
				// Sprung auf Referenz 
				if (m_oModulLinks.isVisible()){
					m_gridMessageBlock.add(new MyE2_Label(""),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
					m_gridMessageBlock.add(m_oModulLinks,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5,2));
				}
				
				
				m_gridMessageBlock.add(new MyE2_Label(""),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_0_5));
				E2_ComponentGroupHorizontal_NG grp = new E2_ComponentGroupHorizontal_NG(	
													m_btnBestaetigen, m_cbWVAm, m_dtWVAm, m_btnWV, LayoutDataFactory.get_GridLayoutGridLeftMID(new Insets(0,0,20,0), 1));
				// falls es eine Sofortnachricht ist den Konvertierungsbutton noch dranhängen
				if(m_rec.get_SOFORTANZEIGE_cUF_NN("N").equals("Y")){
					grp.add_(m_btnConvertSofort2Normal);
					m_btnConvertSofort2Normal.set_bPassivAction(true);
					m_btnConvertSofort2Normal.add_oActionAgent(new actionAgentConvert2Standard());
					
				}
				m_gridMessageBlock.add(grp,2,E2_INSETS.I_0_0_0_10);

				
	
				// Historien-Eintrag
				CMessage_History_Block histBlock = new CMessage_History_Block(m_rec) ;
				if (histBlock.getBlock() != null){
					m_rowHistory = new E2_ExpandableRow(new MyE2_String("Historie..."), new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
					m_rowHistory.add(histBlock.getBlock());
	
					m_rowHistory.get_oButtonClose().doActionPassiv();
					m_rowHistory.get_oButtonClose().set_bPassivAction(true);
					m_rowHistory.get_oButtonOpen().set_bPassivAction(true);
	
					m_gridMessageBlock.add(new MyE2_Label(""),m_LayoutDataRightTop);
					m_gridMessageBlock.add(m_rowHistory,2,E2_INSETS.I_0_0_5_0);
				}
			}
			else if (this.bIstSystemMessage)
			{
				Insets iHelp = 			new Insets(5,2,5,0);
				Insets iHelpBottom = 	new Insets(5,2,5,2);
				
				
				// Komponenten ins Grid setzen
				m_gridMessageBlock = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
				//m_gridMessageBlock.add(lblBetreff,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
				m_gridMessageBlock.add(lblTitel,  LayoutDataFactory.get_GridLayoutGridLeftTOP(iHelp,2));
				m_gridMessageBlock.add(lblValidity.get_InBorderGrid(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID), null, E2_INSETS.I_2_2_2_2),LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_0_5_0_0));
				
				// Kategorie
				MyE2_Grid gridKat = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				gridKat.add(lblKategorieDesc);
				gridKat.add(lblKategorie,E2_INSETS.I_5_0_0_0);
				m_gridMessageBlock.add(gridKat,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5,3));
				m_gridMessageBlock.add(tfNachricht, LayoutDataFactory.get_GridLayoutGridLeftTOP(iHelp,3));
				
				// Sprung auf Anhänge
				if (m_btAttachementToNachricht.bIsVisible()){
					m_gridMessageBlock.add(new MyE2_Label(""),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5));
//					m_gridMessageBlock.add(m_btAttachementToNachricht,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5,2));
					E2_ComponentGroupHorizontal_NG grp 
						= new E2_ComponentGroupHorizontal_NG(	
								m_btAttachementToNachricht, 
								new MyE2_Label(m_btAttachementToNachricht.getDescription()) 
							, LayoutDataFactory.get_GridLayoutGridLeftMID(new Insets(0,0,20,0), 1));
					m_gridMessageBlock.add(grp,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_5,2));
				}
				
				
				// Sprung auf Referenz 
				if (m_oModulLinks.isVisible()){
					m_gridMessageBlock.add(m_oModulLinks,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_5_5_5,3));
				}
				
				m_gridMessageBlock.add(new E2_ComponentGroupHorizontal_NG(	m_btnBestaetigen,
																			LayoutDataFactory.get_GridLayoutGridLeftMID(new Insets(0,0,20,0), 1)),iHelpBottom);
				m_gridMessageBlock.add(new MyE2_Label(""),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_5,2));
				
				
				//ein bischen formatieren
				Font  oFontHelp = new E2_FontPlain();
				lblTitel.setFont(oFontHelp);
				m_btnBestaetigen.setFont(oFontHelp);
				
				tfNachricht.setWidth(new Extent(100,Extent.PERCENT));
				tfNachricht.setForeground(Color.BLACK);
				tfNachricht.setBackground(new E2_ColorLight());
				tfNachricht.setFont(oFontHelp);
				tfNachricht.setBorder(new Border(new Extent(1), new E2_ColorAlarm(), Border.STYLE_SOLID));
				

			}
			else
			{
				throw new myException(this,"undefined message-status (1)");
			}
			

			
			// Grid einhängen
			this.add(m_gridMessageBlock,E2_INSETS.I_0_0_0_0);
			
			// MessageAgents setzen
			m_btnBestaetigen.set_bPassivAction(true);
			m_btnBestaetigen.add_oActionAgent(new actionAgentConfirm());
			

			
			
		}

		
		private class actionAgentConvert2Standard extends XX_ActionAgent	{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				CMessageDisplayBlock oThis = CMessageDisplayBlock.this;
				MESSAGE_Editor  oEditor = MESSAGE_Editor.this;
				oThis.m_rec.set_NEW_VALUE_SOFORTANZEIGE("N");
				oThis.m_rec.set_NEW_VALUE_MSG_CONVERT_(new GregorianCalendar());
				oThis.m_rec.UPDATE(null, true);			
				
				// editor neu aufbauen
				oEditor.refreshModule();
			}
		}
		
		
		private class actionAgentConfirm extends XX_ActionAgent	{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				CMessageDisplayBlock oThis = CMessageDisplayBlock.this;
				MESSAGE_Editor  oEditor = MESSAGE_Editor.this;
				oThis.m_rec.set_NEW_VALUE_BESTAETIGT("Y");
				oThis.m_rec.UPDATE(null, true);			
				
				// editor neu aufbauen
				oEditor.refreshModule();
			}
		}
		
		private class actionAgentReminder extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				CMessageDisplayBlock oThis = CMessageDisplayBlock.this;
				MESSAGE_Editor  oEditor = MESSAGE_Editor.this;

				GregorianCalendar cal = new GregorianCalendar();
				String sDatum = myDateHelper.ChangeNormalString2DBFormatString( myDateHelper.FormatDateNormal(cal.getTime()));
				sDatum = myDateHelper.ChangeNormalString2DBFormatString( oThis.m_dtWVAm.get_cString4Database() );

				
				MESSAGE_Handler oHandler = new MESSAGE_Handler();
				oHandler.copyReminder(oThis.m_rec, sDatum );
				
				// editor neu aufbauen
				oEditor.refreshModule();
				
			}
			
		}
	}

	
	
	/**
	 * Klasse definiert den Block in dem die Meldungen erfasst werden
	 * @author manfred
	 *
	 */
	private class CMessage_Writing_Block extends MyE2_Column{
		
		RECORD_NACHRICHT  	m_rec = null;
		
		MyE2_Label 		 	m_lblAn = null; 
		ownButton      		m_btnOk = null;
		
		
		MyE2_Label 			m_lblKategorie	= null;
		MESSAGE_SelectField_Kategorie    m_selKategorie = null;
		
		MyE2_Label 			m_lblTitel	= null;
		MyE2_TextField 		m_tfTitel	= null;
		
		MyE2_Label 			m_lblAntwort 	= null;
		MyE2_TextArea 		m_tfAntwort	= null;
		
		MyE2_CheckBox		m_cbDirektanzeige = null;
		MyE2_CheckBox		m_cbSendEmail = null;
		
		
		UB_TextField_With_DatePOPUP_OWN m_dtVorlageAm = null;
		MyE2_CheckBox		m_cbVorlageAm = null;
		
		MyE2_Grid 			m_grid = null;
		
		
		E2_AuswahlSelectorUsers m_selUsers = null;
		
		// falls eine Verknüpfung gewünscht wird bei einer Direktnachricht
		String 				m_Target = null;
		String 				m_TargetZusatz = null;
		String				m_ID_Target = null;
		
		// Kategorie
		String				m_Kategorie = null;
		Long				m_IDKategorie = null;
		
		
		/**
		 * Erstellen einer neuen Nachricht.
		 * Wenn oNachricht != null ist, wird 
		 * 		der Titel als RE: vorbelegt 
		 * 		und der Empfänger vorbelegt
		 * 
		 * @param oNachricht
		 * @throws myException 
		 */
		public CMessage_Writing_Block(RECORD_NACHRICHT oNachricht) throws myException {
			
			//super(new MyE2_String("Nachricht beantworten..."), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
			super();

			MESSAGE_Editor.this.oLastCreatedMessageWritingBlock = this;
			
			this.m_rec = oNachricht;
			
			m_grid = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			//martin m_grid.setBorder(new Border(0,null,Border.STYLE_NONE));
			
			// Felder 
			GregorianCalendar cal = new GregorianCalendar();
			String sDate =  myDateHelper.FormatDateNormal(cal.getTime());
			m_dtVorlageAm 	= new UB_TextField_With_DatePOPUP_OWN("AKTIV_AB",true,sDate, 100);
			m_dtVorlageAm.set_bPassivAction(true);
			m_dtVorlageAm.add_InputValidator(new VALIDATE_INPUT_DATE());
			m_dtVorlageAm.set_bEnabled_For_Edit(false);
			

			m_cbDirektanzeige = new MyE2_CheckBox(new MyString("Nachricht direkt anzeigen.").CTrans(), new MyE2_String("Die Nachricht wird dem Empfänger direkt angezeigt und erfordert eine sofortige Bestätigung."));
			((MutableStyle)m_cbDirektanzeige.getStyle()).setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
			m_cbDirektanzeige.setSelected(true);
			m_cbDirektanzeige.set_bPassivAction(true);

			m_cbSendEmail = new MyE2_CheckBox(new MyString("Nachricht als Mail versenden").CTrans(), new MyE2_String("Die Nachricht wird dem Empfänger am Termin als Mail zugestellt."));
			((MutableStyle)m_cbSendEmail.getStyle()).setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
			m_cbSendEmail.setSelected(true);
			m_cbSendEmail.set_bPassivAction(true);
			
			
			m_cbVorlageAm = new MyE2_CheckBox(new MyString("Vorlage am ").CTrans(), new MyE2_String("Die Nachricht erscheint beim Empfänger erst am angegebenen Datum"));
			((MutableStyle)m_cbVorlageAm.getStyle()).setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
			m_cbVorlageAm.set_bPassivAction(true);
			m_cbVorlageAm.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					if (m_cbVorlageAm.isSelected()){
						m_dtVorlageAm.set_bEnabled_For_Edit(true);
						m_dtVorlageAm.get_oTextField().set_bEnabled_For_Edit(false);
					}else {
						m_dtVorlageAm.set_bEnabled_For_Edit(false);
					}
				}
			});
			m_cbVorlageAm.setSelected(false);
			m_dtVorlageAm.set_bEnabled_For_Edit(false);
			
			m_lblTitel      = new MyE2_Label(new MyString("Betreff"));
			m_tfTitel		= new MyE2_TextField("", 530, 100);
			m_tfTitel.set_iMaxInputSize(100);
			
			
			m_lblAntwort 	= new MyE2_Label(new MyString("Nachricht"));
			m_tfAntwort	= new MyE2_TextArea("", 530, 4000, 6);
			

			m_lblAn = new MyE2_Label(new MyString("An"));
			
			// 2015-06-25 Kategorie-Auswahl voreinstellen
			m_lblKategorie      = new MyE2_Label(new MyString("Kategorie"));
			m_selKategorie 		= new MESSAGE_SelectField_Kategorie(534, MESSAGE_Editor.this);
			
			
			// Adressauswahl, die Buttons "deaktivieren"
			m_selUsers = new E2_AuswahlSelectorUsers(m_UserSelectorGenerator, new Extent(250),new Extent(120), null);
			
			m_selUsers.get_oSelQuelle().set_bPassivAction(true);
			m_selUsers.get_oSelZiel().set_bPassivAction(true);
			m_selUsers.get_oButtonAddSelected().set_bPassivAction(true);
			m_selUsers.get_oButtonClearSelected().set_bPassivAction(true);
			
			m_selUsers.get_oButtonClearAll().set_bPassivAction(true);
			m_selUsers.get_oButtonSelectAll().set_bPassivAction(true);

			
					
			m_btnOk = new ownButton(new MyString("Abschicken"),E2_ResourceIcon.get_RI("ok.png") , E2_ResourceIcon.get_RI("leer.png"));
			m_btnOk.set_bPassivAction(true);
			m_btnOk.setInsets(E2_INSETS.I_10_2_10_2);

			
			// Vorbelegung der Felder, falls es sich um eine Antwort handelt
			if (m_rec != null){
				// Titel
				String sTitel = "RE: " + m_rec.get_TITEL_cUF();
				if (sTitel.length() > 100){
					sTitel = sTitel.substring(0, 97) + "...";
				}
				m_tfTitel.setText(sTitel);
				
				// die User
				m_selUsers.addSelectionByID(m_rec.get_ID_USER_SENDER_cF());
				
				// die Kategorie
				String idKat = m_rec.get_ID_NACHRICHT_KATEGORIE_cUF();
				m_selKategorie.set_ActiveValue_OR_FirstValue(idKat);
			}
			
			// Vorbelegung falls schon Texte und Benutzer gegeben sind
			MESSAGE_Editor oMessageEditor = MESSAGE_Editor.this;
			if (oMessageEditor.m_Target != null){
				
				// Übernehmen der Zieldaten und vorbelegen der Maskeninhalte
				m_ID_Target = oMessageEditor.m_ID_Target;
				m_Target = oMessageEditor.m_Target;
				m_TargetZusatz = oMessageEditor.m_TargetZusatz;
				
				m_tfTitel.setText(oMessageEditor.m_sBetreff);
				m_tfAntwort.setText(oMessageEditor.m_sNachricht);
				if (oMessageEditor.m_vIDEmpfaenger != null){
					for (String id : oMessageEditor.m_vIDEmpfaenger){
						m_selUsers.addSelectionByID(id);
					}
				}
				m_cbVorlageAm.setSelected(true);
				m_cbVorlageAm.doAction();
				if (!bibALL.isEmpty(oMessageEditor.m_DTVorlageAm)){
					myDateHelper o = new myDateHelper(bibDate.String2Date(m_DTVorlageAm));
					m_dtVorlageAm.get_oTextField().setText(o.get_cDateFormatForMask());
					m_dtVorlageAm.set_oLastDateKlicked(o);
				}
				
				
				m_cbDirektanzeige.setSelected(oMessageEditor.m_directShow);
				m_cbSendEmail.setSelected(true);
				
				// Kategorie setzen
				if (oMessageEditor.m_IDKategorie != null){
					m_selKategorie.set_ActiveValue_OR_FirstValue(m_IDKategorie.toString());
				} else if (oMessageEditor.m_Kategorie != null){
					MESSAGE_Handler oHandler = new MESSAGE_Handler();
					m_selKategorie.set_ActiveValue_OR_FirstValue(oHandler.getID_from_Kategorie(oMessageEditor.m_Kategorie));
				}
				
			}
			
			
			
			
			//martin: einiges anders angeordnet
			m_grid.add(m_lblTitel, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_0));     	
			m_grid.add(m_tfTitel, 2, E2_INSETS.I_0_0_0_0);    										 		
			
			m_grid.add(m_lblKategorie, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_0));     	
			m_grid.add(m_selKategorie, 2, E2_INSETS.I_0_0_0_0);    										 		

			m_grid.add(m_lblAntwort, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_0));     
			m_grid.add(m_tfAntwort,2,E2_INSETS.I_0_0_0_0);      											

			m_grid.add(m_lblAn, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_5_5_0));			
			m_grid.add(m_selUsers,2,E2_INSETS.I_0_5_0_0);  													
			
			
			MyE2_Row rowSofort = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			m_grid.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0);
			rowSofort.add(m_cbDirektanzeige,E2_INSETS.I_0_0_0_0);
			m_grid.add(rowSofort,2,E2_INSETS.I_0_5_5_0);						
			
			MyE2_Grid gridEmail = new MyE2_Grid(1,MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			gridEmail.add(m_cbSendEmail,E2_INSETS.I_0_0_0_0);
			m_grid.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0);
			m_grid.add(gridEmail,2,E2_INSETS.I_0_5_5_0);	
			
			
			MyE2_Row rowVorlage = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			rowVorlage.add(m_cbVorlageAm,E2_INSETS.I_0_0_5_0);
			rowVorlage.add(m_dtVorlageAm,E2_INSETS.I_10_0_0_0);
			
			m_grid.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0);
			m_grid.add(rowVorlage,2,E2_INSETS.I_0_0_5_0);													
			
			
			m_grid.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_5_0);
			MyE2_Row rowButtons = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			rowButtons.add(m_btnOk);
			m_grid.add(rowButtons,2,E2_INSETS.I_0_5_5_5);													

			this.add(m_grid,E2_INSETS.I_0_0_0_0);
			
			this.m_btnOk.add_oActionAgent(new actionAgentConfirm());
		}
		

		
		private class actionAgentConfirm extends XX_ActionAgent	{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				boolean bOk = true;
				
				CMessage_Writing_Block  oThis = CMessage_Writing_Block.this;
				MESSAGE_Editor  oEditor = MESSAGE_Editor.this;
				
				
				oThis.m_tfAntwort.show_InputStatus(false);
				if(bibALL.isEmpty(oThis.m_tfAntwort.getText()) ){
					oThis.m_tfAntwort.show_InputStatus(true);
					bOk &= false;

					//martin
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Feld <Nachricht> darf nicht leer sein !")));

				}

				oThis.m_tfTitel.show_InputStatus(false);
				if (bibALL.isEmpty(oThis.m_tfTitel.getText())){
					oThis.m_tfTitel.show_InputStatus(true);
					bOk &= false;

					//martin
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Feld <Betreff> darf nicht leer sein !")));
				}
				
				if (oThis.m_tfTitel.getText().length() > 100 ){
					oThis.m_tfTitel.show_InputStatus(true);
					bOk &= false;
					
					//martin
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Feld <Betreff> fasst höchstens 100 Zeichen!")));

					
				}
				
				oThis.m_selUsers.get_oSelZiel().show_InputStatus(false);
				if (oThis.m_selUsers.get_DataToView_AUSWAHL().get_vDataValues().size() <= 0 ){
					oThis.m_selUsers.get_oSelZiel().show_InputStatus(true);
					//oThis.m_lblAn.show_InputStatus(true);
					bOk &= false;
					
					//martin
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben keine Adressaten für die Nachricht gewählt!")));
					
				}
				
				
				GregorianCalendar cal = new GregorianCalendar();
				String sDatum = myDateHelper.ChangeNormalString2DBFormatString( myDateHelper.FormatDateNormal(cal.getTime()));
				if (oThis.m_cbVorlageAm.isSelected()){
					sDatum = myDateHelper.ChangeNormalString2DBFormatString( oThis.m_dtVorlageAm.get_cString4Database() );
				} 

				
				if (bOk){
					oEditor.saveMessage(
						oThis.m_tfTitel.getText(), 
						oThis.m_tfAntwort.getText(), 
						oThis.m_selUsers.get_DataToView_AUSWAHL().get_vDataValues(),
						m_rec,
						oThis.m_cbDirektanzeige.isSelected(),
						sDatum, 
						m_ID_Target,
						m_Target,
						m_TargetZusatz,
						m_selKategorie.get_ActualWert(),
						oThis.m_cbSendEmail.isSelected()
						);
					
					if (bibMSG.get_bIsOK()){
						oEditor.refreshModule();
					}
					
				}
				
				//martin: da alle buttons auf passiv stehen, die evtl. vorhandenen messages hier anzeigen
				MESSAGE_Editor.this.showActualMessages();

				
			}	
		}
	}
	
	
	/**
	 * Klasse zum darstellen aller Nachrichten, die vor der aktuellen Nachricht in der Unterhaltung
	 * geschrieben wurden.
	 * 
	 * 
	 * @author manfred
	 *
	 */
	private class CMessage_History_Block {
		
		RECORD_NACHRICHT 	m_rec = null;
		MyE2_Column 		m_col = null;
		
		
		public CMessage_History_Block(RECORD_NACHRICHT oNachricht) throws myException{
			this.m_rec = oNachricht;
			
			
			// einfache Prüfung, ob es ältere (historische) Nachrichten gibt... 
			// dann muss die Parent-ID nämlich gesetzt sein!!!!
			if (m_rec.get_ID_NACHRICHT_PARENT_cUF() == null){
				return;
			}
			
			
			String sSql = " SELECT " +
					"ID_NACHRICHT, " +
					"TITEL, " +
					"NACHRICHT, " +
					"ID_NACHRICHT_PARENT, " +
					"JT_NACHRICHT.ID_USER_SENDER, U1.VORNAME ||' ' || U1.NAME1, " +
					"JT_NACHRICHT.ID_USER, U2.VORNAME ||' ' ||  U2.NAME1 , " +
					"to_char(JT_NACHRICHT.ERZEUGT_AM ,'dd.MM.yyyy HH24:MI') , " +
					"JT_NACHRICHT_KATEGORIE.KATEGORIE , " +
					"LEVEL " +
					" FROM " + bibE2.cTO() +  ".JT_NACHRICHT " +
					" INNER JOIN  " + bibE2.cTO() +  ".JD_USER U1 ON JT_NACHRICHT.ID_USER_SENDER = U1.ID_USER" +
					" INNER JOIN  " + bibE2.cTO() +  ".JD_USER U2 ON JT_NACHRICHT.ID_USER = U2.ID_USER " +
					" LEFT OUTER JOIN " + bibE2.cTO() + ".JT_NACHRICHT_KATEGORIE ON JT_NACHRICHT.ID_NACHRICHT_KATEGORIE = JT_NACHRICHT_KATEGORIE.ID_NACHRICHT_KATEGORIE " + 
					" WHERE ID_NACHRICHT != " + m_rec.get_ID_NACHRICHT_cUF() +
					" START WITH ID_NACHRICHT = " + m_rec.get_ID_NACHRICHT_cUF() + 
					" CONNECT BY  ID_NACHRICHT = PRIOR ID_NACHRICHT_PARENT";
			
			String [][] asMessages = new String[0][0];
			
			asMessages = bibDB.EinzelAbfrageInArray(sSql,"");
			if (asMessages.length > 0){
				
				StringBuilder sb = new StringBuilder();
				String lineSep = System.getProperty("line.separator");
				
				for (int i=0;i<asMessages.length;i++)
				{
					String sBetreff			= asMessages[i][1];
					String sText			= asMessages[i][2];
					String sIDUserSender 	= asMessages[i][4];
					String sUserSender 		= asMessages[i][5];
					String sIDUser 			= asMessages[i][6];
					String sUser  			= asMessages[i][7];
					String sDatum			= asMessages[i][8];
					String sKategorie 		= asMessages[i][9];
					
					if (sIDUserSender.equals(bibALL.get_ID_USER())){
						sUserSender = new MyE2_String("Ich").CTrans();
					}
					if ( sIDUser.equals(bibALL.get_ID_USER())){
						sUser = new MyE2_String("mich").CTrans();
					}
					sb.append(sUserSender);
					sb.append(new MyE2_String(" an ").CTrans());
					sb.append(sUser);
					sb.append(" (");
					sb.append(sDatum);
					sb.append(")");
					
					if (!bibALL.isEmpty(sKategorie)) {
						sb.append(" / Kategorie: ");
						sb.append(sKategorie);
					}
					
					sb.append(lineSep);
					sb.append(sBetreff);
					sb.append(lineSep);
					sb.append("***");
					sb.append(lineSep);
					sb.append(sText);
					sb.append(lineSep);
					sb.append("__________________________________________________________");
					sb.append(lineSep);
					
				}
				MyE2_TextArea tf = new MyE2_TextArea(sb.toString(), 530, 4000, 10);
				tf.set_bEnabled_For_Edit(false);

				m_col = new MyE2_Column();
				m_col.add(tf);
			}
		}
		
		/**
		 * gibt die aktuelle Column mit den Texten zurück
		 * @return
		 */
		public MyE2_Column getBlock(){
			return m_col;
		}
		
	}

	
	private class ownButton extends MyE2_Button
	{


		public ownButton(Object cText, ImageReference oImg,	ImageReference oimgDisabled)
		{
			super(cText, oImg, oimgDisabled);
			this.set_internals();
		}

		private void set_internals()
		{
			this.set_COLORS(new E2_ColorLight(), Color.BLACK, new E2_ColorLight(), Color.DARKGRAY);
		}
		
	}
	
	
	
	
	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public MyE2_TextField __get_tfTitel()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_tfTitel;
		}
		else
		{
			return null;
		}
	}


	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public MyE2_TextArea __get_tfNachricht()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_tfAntwort;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public MyE2_CheckBox __get_cbDirektanzeige()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_cbDirektanzeige;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public UB_TextField_With_DatePOPUP_OWN __get_dtVorlageAm()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_dtVorlageAm;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public MyE2_CheckBox __get_cbVorlageAm()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_cbVorlageAm;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public E2_AuswahlSelectorUsers __get_selUsers()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_selUsers;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public String __get_Target()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return m_Target;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 2012-02-09: Martin: angefuegt, um von aussen felder setzen zu koennen
	 * @return
	 */
	public String __get_ID_Target()
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			return this.oLastCreatedMessageWritingBlock.m_ID_Target;
		}
		else
		{
			return null;
		}
	}

	
	/**
	 * 2012-02-09: Martin: empfaenger von aussen zudefinieren
	 * @return
	 */
	public void __add_Empfaenger(Vector<String> vIDs) 
	{
		if (this.oLastCreatedMessageWritingBlock!=null)
		{
			E2_AuswahlSelectorUsers oSelektorUser = this.oLastCreatedMessageWritingBlock.m_selUsers;
			
			for (String id : vIDs)
			{
				oSelektorUser.addSelectionByID(id);
			}
		}
	}
	
	
	/**
	 * actionAgent liest die Menge der bestätigten Nachrichten neu ein um den 
	 * neuen Zeitraum zu betrachten
	 * @author manfred
	 *
	 */
	private class actionAgentZeitraum extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_Editor oThis = MESSAGE_Editor.this;
			oThis.m_reclist_read = oThis.getNachrichten(EnumDisplayType.READ);
			oThis.initModule(EnumDisplayType.READ);
//			oThis.initModules();
		}
	}
	

	
	private class actionAgentDisplayType_Selection extends XX_ActionAgent{

		EnumDisplayType m_displayType = null;
		
		/**
		 * Konstruktor für die verschiedenen TabReiter
		 * @param displayType
		 */
		public actionAgentDisplayType_Selection(EnumDisplayType displayType) {
			m_displayType = displayType;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_Editor oThis = MESSAGE_Editor.this;
			RECLIST_NACHRICHT rl = oThis.getNachrichten(m_displayType);
			switch (m_displayType) {
				case READ:
					oThis.m_reclist_read = rl;
					break;
				case UNREAD:
					oThis.m_reclist_unread = rl;
					break;
				default:
					break;
					//	
			}
			
			oThis.initModule(m_displayType);
		}
		
	}
	
	private class actionAgentForTab extends XX_ActionAgent{

		EnumDisplayType m_displaytype = null;
		public actionAgentForTab(EnumDisplayType type){
			m_displaytype = type;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_Editor oThis = MESSAGE_Editor.this;
			oThis.initModule(m_displaytype);
		}
		
	}
	
	/**
	 * ActionHandler setzt alle 
	 * @author manfred
	 * @date 16.07.2018
	 *
	 */
	private class actionAgentSetAll2Normal extends XX_ActionAgent{
		
		EnumDisplayType m_displaytype = null;
		MyE2_Grid		m_grid = null;
		
		public actionAgentSetAll2Normal(EnumDisplayType type, MyE2_Grid m_panel) {
			m_displayType = type;
			m_grid = m_panel;
		}
		
		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_Editor oThis = MESSAGE_Editor.this;
			String sMessage = "";
			MESSAGE_Handler h = new MESSAGE_Handler();

			int i = 0;
			for (Component c : m_grid.getComponents()){
				if(c instanceof CMessageDisplayBlock){
					CMessageDisplayBlock block = (CMessageDisplayBlock)c;
					
					h.setMessageAsNormal(block.m_rec);
					i++;
					String id = block.m_rec.get_ID_NACHRICHT_cUF();
					sMessage += id + "; ";
				}
			}
			if ( i > 0){
				oThis.refreshModule();
				bibMSG.add_MESSAGE(new MyE2_Info_Message(String.format("Es wurden %d Meldungen in normale Meldungen konvertiert. ", i)));
			}
			
		}
		
	}

	
	private class actionAgentSetAllAsRead extends XX_ActionAgent{
		
		EnumDisplayType m_displaytype = null;
		MyE2_Grid		m_grid = null;
		
		public actionAgentSetAllAsRead(EnumDisplayType type, MyE2_Grid m_panel) {
			m_displayType = type;
			m_grid = m_panel;
		}
		
		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_Editor oThis = MESSAGE_Editor.this;
			String sMessage = "";
			MESSAGE_Handler h = new MESSAGE_Handler();
			int i = 0;
			
			for (Component c : m_grid.getComponents()){
				if(c instanceof CMessageDisplayBlock){
					CMessageDisplayBlock block = (CMessageDisplayBlock)c;
					h.setMessageAsRead(block.m_rec);
					i++;
				}
			}
			if ( i > 0){
				oThis.refreshModule();
				bibMSG.add_MESSAGE(new MyE2_Info_Message(String.format("Es wurden %d Meldungen als gelesen gekennzeichnet. ", i)));
			}
			
			
		}
		
	}
	
	
	
	private class BT_AttachementToNachricht extends E2_ButtonUpDown {
		String sTableID = null;
		
		boolean _bIsVisible = true;
		private String _description;
		
		public BT_AttachementToNachricht(String TableID) {
			super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_NACHRICHTEN_LISTE.get_callKey(), 
					NACHRICHT.baseTabName(), TableID, "Zusatzdatei zur Nachricht");
			sTableID = TableID;
			initButton();
		}
		
		
		public boolean bIsVisible(){
			return this._bIsVisible;
		}
		
		public String getDescription(){
			return this._description;
		}
		
		public void initButton() {
			try {
				SqlStringExtended 	sqlExt 		= null;
				String sTableName = NACHRICHT.baseTabName();
				
				SEL sel = new SEL("*").FROM(ARCHIVMEDIEN._tab()).WHERE(new vglParam(ARCHIVMEDIEN.tablename)).AND(new vglParam(ARCHIVMEDIEN.id_table));
				
				sqlExt = new SqlStringExtended(sel.s());
				
				sqlExt.getValuesList().add(new Param_String("TAblename", sTableName));
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(sTableID)));
				
				RecList21 rlArchivmedienNachricht = new RecList21(ARCHIVMEDIEN._tab())._fill(sqlExt);
				
				if (rlArchivmedienNachricht.size() > 0){
					this.__setImages(E2_ResourceIcon.get_RI("attach_mini_green.png"), E2_ResourceIcon.get_RI("leer.png"));
					if (rlArchivmedienNachricht.size() == 1){
						_description = "";
						Rec21 rec = rlArchivmedienNachricht.get(0);
						_description = rec.getUfs(ARCHIVMEDIEN.downloadname);
					} else {
						_description = String.format("%d Anhänge",rlArchivmedienNachricht.size());
					}
					
				} else {
					_bIsVisible = false;
					this.setVisible(_bIsVisible);
				}
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
