/**
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.CellLayoutData;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.bibHASHMAP;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_CheckBoxOption;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_CONST;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_Selection_User_DropDown;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_USER_EXT;

/**
 * @author manfred Expander für die Liste unterhalb der Kopfeintragungen Liste
 *         Enthält Buttons für anschauen, ändern und löschen
 */
public class WF_LIST_EXPANDER_4_Compressed_Entries extends XX_List_EXPANDER_4_ComponentMAP
{

	// die maximale Länge der Spalte Aufgabe im Grid für die Anzeige!
	static int MAX_LENGTH_IN_GRID = 80;

	// diverse Gridlayout-Settings für die Darstellung
	GridLayoutData GridLayout_HeaderLeft = null;
	GridLayoutData GridLayout_HeaderRight = null;
	GridLayoutData GridLayout_HeaderCenter_NoInsets = null;

	GridLayoutData GridLayout_left = null;
	GridLayoutData GridLayout_left_center = null;
	GridLayoutData GridLayout_right = null;
	GridLayoutData GridLayout_middle = null;
	GridLayoutData GridLayout_left_top = null;
	
	ColumnLayoutData ColLayout_left_top = null;
	ColumnLayoutData ColLayout_right_top = null;
	

	// lokale Variablen
	private MyE2_Row oRowDaughter = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	
	private String m_LaufzettelID = null;
	private RECORD_LAUFZETTEL m_oLaufzettel = null;
	
	
	//private WF_Tree_Entry oTreeRoot = new WF_Tree_Entry("root", 0);
	private MyE2_Grid oGrid = null;
	private Color     m_colorGridEntries = null;
	private Color 	  m_colorGridHeading = null;

	private WF_LIST_EXPANDER_HIERARCHY_Settings oHierarchySettings = new WF_LIST_EXPANDER_HIERARCHY_Settings();
	
	
	private E2_NavigationList oNaviList = null;


	private RECLIST_USER   m_reclistUser;
	
	/***
	 * 
	 * @param NavigationList
	 * @throws myException
	 */
	public WF_LIST_EXPANDER_4_Compressed_Entries(E2_NavigationList NavigationList) 
		throws myException
	{
		super(NavigationList);
		
		// die Farben für die Grideintragungen
		this.m_colorGridEntries = new E2_ColorBase(30);
		this.m_colorGridHeading = new E2_ColorBase(15);
		
		
		GridLayout_middle = createGridLayout(null, new Alignment(Alignment.CENTER,Alignment.CENTER), E2_INSETS.I_2_2_10_2, null);
		GridLayout_left = createGridLayout(null, new Alignment(Alignment.LEFT,Alignment.CENTER), E2_INSETS.I_2_2_10_2, null);
		GridLayout_left_center = createGridLayout(null, new Alignment(Alignment.LEFT,Alignment.CENTER), E2_INSETS.I_2_2_10_2, null);
		GridLayout_right = createGridLayout(null, new Alignment(Alignment.RIGHT,Alignment.CENTER), E2_INSETS.I_2_2_10_2, null);
		GridLayout_left_top = createGridLayout(null, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_2_2_2_2, null);
		GridLayout_HeaderLeft = createGridLayout(m_colorGridHeading, new Alignment(Alignment.LEFT,Alignment.CENTER), E2_INSETS.I_2_2_10_2, null);
		GridLayout_HeaderRight = createGridLayout(m_colorGridHeading, new Alignment(Alignment.RIGHT,Alignment.CENTER), E2_INSETS.I_2_2_10_2, null);
		GridLayout_HeaderCenter_NoInsets = createGridLayout(m_colorGridHeading, new Alignment(Alignment.CENTER,Alignment.CENTER), E2_INSETS.I_0_0_0_0, null);

		ColLayout_left_top = new ColumnLayoutData();
		ColLayout_left_top.setInsets(E2_INSETS.I_2_2_10_2);
		ColLayout_left_top.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		ColLayout_right_top = new ColumnLayoutData();
		ColLayout_right_top.setInsets(E2_INSETS.I_2_2_10_2);
		ColLayout_right_top.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));

		
		// 2 Spalten von Links als Offset
		this.set_iLeftColumnOffset(3);
		this.oNaviList = NavigationList;

		// liste aller User einmal aufbauen
		m_reclistUser = new RECLIST_USER("SELECT * FROM " + bibE2.cTO()+ ".JD_USER WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() );
		
	}

	
	
	/**
	 * Baut das Gridlayout zusammen
	 * @param Background
	 * @param Align
	 * @param Inset
	 * @param Colspan
	 * @return
	 */
	private GridLayoutData createGridLayout(Color Background,Alignment Align, Insets Inset, Integer Colspan){
		GridLayoutData oGLD = new GridLayoutData();
		if (Background != null){
			oGLD.setBackground(Background);
		}
		if (Align != null){
			oGLD.setAlignment(Align);
		}
		if (Inset != null){
			oGLD.setInsets(Inset);
		}
		if (Colspan != null ){
			oGLD.setColumnSpan(Colspan.intValue());
		}
		
		return oGLD;
	}
	
	
	
	@Override
	/***
	 * 
	 */
	public Component get_ComponentDaughter(String cID_WF_HEAD)
			throws myException
	{

		this.m_LaufzettelID = cID_WF_HEAD;
		this.build_Daughter();
		return this.oRowDaughter;
	}


	@Override
	public void refreshDaughterComponent() throws myException
	{
		this.build_Daughter();
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			return new WF_LIST_EXPANDER_4_Compressed_Entries(this
					.get_oNavigationList());
		} catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(
					"Error Copying WF_LIST_EXPANDER_4_Compressed_Entries");
		}
	}

	
	
	/***
	 * Baut die Tochtertabelle auf
	 * 
	 * @throws myException
	 */
	private void build_Daughter() throws myException
	{
		this.oRowDaughter.removeAll();

		// Wenn keine Id vorhanden ist, gehts zurück
		if (bibALL.isEmpty(this.m_LaufzettelID))
		{
			return;
		}
		
		//
		// Zustände der drüberliegenden Checkboxen prüfen, d.h. abfragen:
		// 
		// recursive nach dem E2_BasicModuleContainer suchen
		E2_RecursiveSearchParent_BasicModuleContainer oSearch = new E2_RecursiveSearchParent_BasicModuleContainer( this.oNaviList );

		E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();

		E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WF_CheckBoxOption.class.getName()), null);
		Vector<Component> vResult = oSearchComps.get_vAllComponents();
		for (Component cb : vResult)
		{
			WF_CheckBoxOption cbOptions = (WF_CheckBoxOption) cb;
			oHierarchySettings.setValue(cbOptions);
		}
		
		// suchen nach der User-Selektion WF_HEAD_LIST_Selection_User_DropDown
		oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WF_HEAD_LIST_Selection_User_DropDown.class.getName()), null);
		vResult = oSearchComps.get_vAllComponents();
		if (vResult.size() > 0){
			WF_HEAD_LIST_Selection_User_DropDown oSelect = (WF_HEAD_LIST_Selection_User_DropDown)vResult.get(0);
			String sID = oSelect.get_ActualWert();
			if (!bibALL.isEmpty(sID)){
				oHierarchySettings.set_idOwnUser(sID);
			}
		}

		
		

		
		// bei jedem Aufbau den Parent neu lesen...
		m_oLaufzettel = new RECORD_LAUFZETTEL(this.m_LaufzettelID);
		
		RECLIST_LAUFZETTEL_EINTRAG oChilds = m_oLaufzettel.get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_laufzettel("","",true);

		// das Grid initialisieren
		oGrid = new MyE2_Grid(12, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		
		//oGrid.setBackground(new E2_ColorLLight());
		oGrid.setBackground(m_colorGridEntries);
		
		// Header aufbauen...
		this.GenerateGridHeader(oGrid);



		// wenn es keine Einträge gibt, dann gibts auch nix auszugeben
		if (oChilds.get_vKeyValues().size() == 0)
		{
			oGrid.add(new MyE2_Label(new MyE2_String(
					"Es sind keine Einträge vorhanden!")), 6,
					E2_INSETS.I_5_0_5_0);
		} 
		else
		{
			
			WF_LIST_EXPANDER_HIERARCHY oHierarchy = new WF_LIST_EXPANDER_HIERARCHY(oChilds);
			oHierarchy.set_Settings(oHierarchySettings);
			oHierarchy.BuildEntryList();

			Vector<WF_LIST_EXPANDER_ROW_OBJECT> oVDisplayrows = null;
			oVDisplayrows = oHierarchy.getVDisplayRows();
			if (!oVDisplayrows.isEmpty())
			{
				// die Displayrows in die Tabelle aufnehmen
				for (WF_LIST_EXPANDER_ROW_OBJECT oRow :oVDisplayrows)
				{
					addRowToGrid(oChilds, oRow.getId_Laufzettel_Eintrag(), oRow.getDepth());
				}
			}

			String 	sStatus = "Aufgaben: " +  Integer.toString(oHierarchy.get_countAll());
			   		sStatus += " - davon ausgeblendet: " +  Integer.toString(oHierarchy.get_countHiddenActive() + oHierarchy.get_countHiddenDeleted() + oHierarchy.get_countHiddenUser() + oHierarchy.get_countHiddenPrivat());
			   		
	   		Vector<String> sAdditionalInfo = new Vector<>();
	   		if (oHierarchy.get_countHiddenPrivat() > 0){
	   			sAdditionalInfo.add(" Privat: " + Integer.toString(oHierarchy.get_countHiddenPrivat()));
	   		}
	   		if (oHierarchy.get_countHiddenDeleted() > 0){
	   			sAdditionalInfo.add(" gelöscht: " + Integer.toString(oHierarchy.get_countHiddenDeleted()));
	   		}
			if (sAdditionalInfo.size() > 0) {
				
	   			sStatus += " ( " + bibTEXT.concat(sAdditionalInfo, " - ") + " )";
	   		}
	   		
			addStatusRowToGrid(sStatus);

		}

		this.oRowDaughter.add(oGrid, new Insets(0));

		
	}

	
	
	/**
	 * Fügt eine Zeile zum Grid dazu mit der Tiefe depth
	 * 
	 * @param oChilds
	 * @param id
	 * @param depth
	 * @return boolean
	 * @throws myException
	 */
	private boolean addRowToGrid(RECLIST_LAUFZETTEL_EINTRAG oChilds, String id, int depth) throws myException
	{
		
		// status, ob eine Zeile gezeigt werden darf oder nicht.
		boolean bHideRow = false;

		RECORD_LAUFZETTEL_EINTRAG oRec = oChilds.get(id);
		
		// Stati aus DB 
		boolean bDeleted = S.NN(oRec.get_GELOESCHT_cF()).equals("Y");
		boolean bIsPrivate = S.NN(oRec.get_PRIVAT_cF()).equals("Y");
		


		
		// wird true, wenn irgend eine UserID gleich der ID des angemeldeten Benutzers ist
		boolean bRowIsAssignedToMe = false;
		
		RECORD_USER oUser = null;

		// Bearbeiter
		String sIDBearbeiter = oRec.get_ID_USER_BEARBEITER_cUF();
		String sIDBesitzer = oRec.get_ID_USER_BESITZER_cUF();
		String sPrioID = oRec.get_ID_LAUFZETTEL_PRIO_cUF();
		String sErledigt = oRec.get_ABGESCHLOSSEN_AM_cUF();
		String sIDAbgeschlVon = oRec.get_ID_USER_ABGESCHLOSSEN_VON_cUF();
		
		String sPrivat = S.NN(oRec.get_PRIVAT_cUF());
		
		
		// prüfen, ob der ausgwählte User "ich" bin, da private Einträge nur von einem selbst gelesen werden dürfen
		boolean bUserIsMe = false;
		bUserIsMe = S.NN(sIDBearbeiter).equals(bibALL.get_ID_USER());
		bUserIsMe |= S.NN(sIDBesitzer).equals(bibALL.get_ID_USER());
		

		boolean bIsOwnEntry = S.NN(sIDBearbeiter).equals(oHierarchySettings.get_idOwnUser());
		bIsOwnEntry |= S.NN(sIDBesitzer).equals(oHierarchySettings.get_idOwnUser());
		
		boolean bErledigt = S.NN(sErledigt).length() > 0;

		
		String sUserBearbeiter = "";
		if (sIDBearbeiter != null)
		{
			oUser = m_reclistUser.get(sIDBearbeiter);
			sUserBearbeiter = S.NN(oUser.get_NAME_cUF());
			bRowIsAssignedToMe |= oUser.get_ID_USER_cUF().equals(oHierarchySettings.get_idOwnUser()) ;
		}

		// Besitzer
		String sBesitzer = "";
		if (sIDBesitzer != null)
		{
			oUser = m_reclistUser.get(sIDBesitzer);
			sBesitzer = S.NN(oUser.get_NAME_cUF());
			bRowIsAssignedToMe |= oUser.get_ID_USER_cUF().equals(oHierarchySettings.get_idOwnUser()) ;
		}
		
		// Besitzer
		String sAbgeschlVon = "";
		if (sIDAbgeschlVon != null)
		{
			oUser = m_reclistUser.get(sIDAbgeschlVon);
			sAbgeschlVon= S.NN(oUser.get_NAME_cUF());
			bRowIsAssignedToMe |= oUser.get_ID_USER_cUF().equals(oHierarchySettings.get_idOwnUser()) ;
		}
		

		String sPrio = "";
		if (sPrioID != null)
		{
			RECORD_LAUFZETTEL_PRIO o = new RECORD_LAUFZETTEL_PRIO(sPrioID);
			sPrio = S.NN(o.get_PRIO_cF());
		}

		E2_ResourceIcon icoPrivate = null;
		if (sPrivat.equals("Y"))
		{
			icoPrivate = E2_ResourceIcon.get_RI("privat_16.png");
		}
		
		

		BT_Edit btEdit = new BT_Edit(oRec.get_ID_LAUFZETTEL_cUF(), id, sIDBearbeiter);
		
		BT_AttachementToWFEntry btAttachmentToEntry = new BT_AttachementToWFEntry(id);

		// anstatt impliziten Button(deprectated) den externen Button nutzen
		WF_LIST_BT_NEW_EXTERNAL btNew = new WF_LIST_BT_NEW_EXTERNAL(this, oRec.get_ID_LAUFZETTEL_cUF(), id,sIDBearbeiter, sIDBesitzer);
		btNew.add_IDValidator(new ValidateLaufzettelGeloescht());
		btNew.add_IDValidator(new ValidateLaufzettelAbgeschlossen());
		
		
		BT_Delete btDelete = new BT_Delete(oChilds, id);
		BT_SendMail btMail = new BT_SendMail(oRec.get_ID_LAUFZETTEL_cUF(),id,oChilds);
		
		
		//2012-01-05: ruecksendebutton
		MyE2_Grid oGrid4Mailbuttons = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid4Mailbuttons.add(btMail, E2_INSETS.I_1_1_1_1);
		oGrid4Mailbuttons.add(new WF_BT_RETURN_MAIL(oRec.get_ID_LAUFZETTEL_cUF(),id), E2_INSETS.I_1_1_1_1);
		
		oGrid.add(this.generateTreeDepthIndicator(depth),GridLayout_left_top);
		oGrid.add(btEdit,GridLayout_left_top);
		oGrid.add(btDelete,GridLayout_left_top);
		
		MyE2_Grid oGridNewAttach = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS() );
		oGridNewAttach.add(btNew,GridLayout_left_top);
		oGridNewAttach.add(btAttachmentToEntry,GridLayout_left_top);
		oGrid.add(oGridNewAttach,GridLayout_left_top);
		
		//oGrid.add(btMail,GridLayout_left_top);
		oGrid.add(oGrid4Mailbuttons,GridLayout_left_top);   //ab:2012-01-05
		
		oGrid.add(this.makeLabel(sPrio, GridLayout_left_top, false, bDeleted, 0));
		
		if (icoPrivate != null)
		{
			oGrid.add(new MyE2_Label(icoPrivate),GridLayout_left_top);
		}
		else
		{
			oGrid.add(new MyE2_Label(""));
		}
		
		
		//2016-07-12: martin: highlighting users
//		oGrid.add(this.makeLabel(sUserBearbeiter, GridLayout_left_top, false, bDeleted, 0));
		oGrid.add(this.makeLabelUser(sIDBearbeiter, GridLayout_left_top, false, bDeleted, 0), this.find_gl(sIDBearbeiter));
		
		
		// lesen des systembedingten Zeilenumbruchzeichens...
		String lineSep = System.getProperty("line.separator");
		
		
		// Aufgabe und Bericht lesen...
		//String sBericht = S.NN(oChilds.get_cF_BERICHT(id));
		String sAufgabeComplete = S.NN(oRec.get_AUFGABE_cF());
		// die Aufgabe auf 100 Zeichen kürzen und dann mit ... erweitern.
		String sAufgabe = sAufgabeComplete;

		int nLastIdx = -1;
		int nIdx = sAufgabe.indexOf(lineSep);
		if (nIdx > 0 )
		{	// wenn was gefunden wurde, dann abschneiden
			sAufgabe = sAufgabe.substring(0,nIdx) + "(...)";
		}
		
		
		// wenn kein Zeilenumbruch gefunden wurde, dann wird nach dem letzten
		// Leerzeichen gesucht,
		// oder dann endgültig abgeschnitten.
		if (sAufgabe.length() > MAX_LENGTH_IN_GRID  )
		{
			// suchen des letzten Wortes...
			sAufgabe = sAufgabe.substring(0, MAX_LENGTH_IN_GRID - 5);
			nLastIdx = sAufgabe.lastIndexOf(' ', MAX_LENGTH_IN_GRID - 5);
			if (nLastIdx == -1)
			{
				nLastIdx = MAX_LENGTH_IN_GRID - 5;
				
			}
			
			sAufgabe = sAufgabe.substring(0, nLastIdx) + "(...)";
		}
		
		
		// die Antwort
		String sAntwortComplete  = oRec.get_BERICHT_cUF();
		String sAntwort = sAntwortComplete;
		nLastIdx = -1;
		if (sAntwort != null){
			nIdx = sAntwort.indexOf(lineSep);
			if (nIdx > 0 )
			{	// wenn was gefunden wurde, dann abschneiden
				sAntwort = sAntwort.substring(0,nIdx) + "(...)";
			}
			
			// wenn kein Zeilenumbruch gefunden wurde, dann wird nach dem letzten
			// Leerzeichen gesucht,
			// oder dann endgültig abgeschnitten.
			if (sAntwort.length() > MAX_LENGTH_IN_GRID  )
			{
				// suchen des letzten Wortes...
				sAntwort = sAntwort.substring(0, MAX_LENGTH_IN_GRID - 5);
				nLastIdx = sAntwort.lastIndexOf(' ', MAX_LENGTH_IN_GRID - 5);
				if (nLastIdx == -1)
				{
					nLastIdx = MAX_LENGTH_IN_GRID - 5;
				}
				sAntwort = sAntwort.substring(0, nLastIdx) + "(...)";
			}
		}

		//2016-07-12: martin: neues layout fuer die textareas
		E2_Grid colTexte = new E2_Grid()._s(1);
		colTexte._a(this.makeTextArea(sAufgabe, GridLayout_left_top, false, bDeleted, 0, m_colorGridEntries, sAufgabeComplete), new RB_gld()._ins(0,0,0,0));
		if (sAntwortComplete != null){
			colTexte._a(this.makeTextArea(sAntwort, GridLayout_left_top, false, bDeleted, 0, m_colorGridEntries, sAntwortComplete,true), new RB_gld()._ins(0,0,0,0));
		}
		oGrid.add(colTexte, new RB_gld()._ins(2,0,2,0));
		
		//--old
//		MyE2_Column colTexte = new MyE2_Column();
//		colTexte.add(this.makeTextArea(sAufgabe, GridLayout_left_top, false, bDeleted, 0, m_colorGridEntries, sAufgabeComplete));
//		if (sAntwortComplete != null){
//			colTexte.add(this.makeTextArea(sAntwort, GridLayout_left_top, false, bDeleted, 0, m_colorGridEntries, sAntwortComplete,true),E2_INSETS.I_0_2_0_0);
//		}
//		oGrid.add(colTexte);
		//--old-end
		
		
		
		E2_Grid col_termin = new E2_Grid()._s(1);
		
//		oGrid.add(this.makeLabel(oRec.get_FAELLIG_AM_cF(),GridLayout_left_top, false, bDeleted, 0));
		col_termin.add(this.makeLabel(oRec.get_FAELLIG_AM_cF_NN("-"),GridLayout_left_top, false, bDeleted, 0));
		if (! bibALL.isEmpty(oRec.get_KADENZ_NACH_ABSCHLUSS_cUF())){
			col_termin.add(this.makeLabel("Kadenz: " + oRec.get_KADENZ_NACH_ABSCHLUSS_cUF_NN("-") + " Tage", GridLayout_left_top, false, bDeleted, 0));
			
			String sKandenzNachFaelligkeit = oRec.get_KADENZ_NACH_FAELLIGKEIT_cUF_NN("N");
			col_termin.add(this.makeLabel(sKandenzNachFaelligkeit.equals("Y") ? "nach Fälligkeit" : "nach Erledigung" , GridLayout_left_top, false, bDeleted, 0));
		}
		
		oGrid.add(col_termin,MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));
		//2016-07-12: martin: highlighting users
//		oGrid.add(this.makeLabel(sBesitzer, GridLayout_left_top, false, bDeleted, 0));
		oGrid.add(this.makeLabelUser(sIDBesitzer, GridLayout_left_top, false, bDeleted, 0), this.find_gl(sIDBesitzer));
		
		//2016-07-12: martin: highlighting users
//		MyE2_Column colAbgeschlossen = new MyE2_Column();
//		colAbgeschlossen.add(this.makeLabel(oRec.get_ABGESCHLOSSEN_AM_cF(),ColLayout_right_top, false, bDeleted, 0));
//		colAbgeschlossen.add(this.makeLabel(sAbgeschlVon,ColLayout_right_top, false, bDeleted, 0));
//		oGrid.add(colAbgeschlossen,GridLayout_left_top);
		E2_Grid colAbgeschlossen = new E2_Grid()._setSize(1)._w(new Extent(100,Extent.PERCENT))
										._a(this.makeLabel(oRec.get_ABGESCHLOSSEN_AM_cF(),ColLayout_right_top, false, bDeleted, 0))
										._a(this.makeLabelUser(sIDAbgeschlVon,ColLayout_right_top, false, bDeleted, 0), this.find_gl(sIDAbgeschlVon));
		oGrid.add(colAbgeschlossen,GridLayout_left_top);
		
		
		

		return true;

	}

	
	private boolean addStatusRowToGrid(String sStatus) throws myException
	{
		
		// status, ob eine Zeile gezeigt werden darf oder nicht.
		boolean bHideRow = false;

		
		oGrid.add(this.makeLabel(sStatus,GridLayout_left, false, false, 0), 12, oGrid.getInsets());
				

		return true;

	}

	
	
	/***
	 * Generiert den Grid-Header
	 * 
	 * @param grid
	 */
	private void GenerateGridHeader(MyE2_Grid grid)
	{
		//Color colGrid = new E2_ColorLight();
		
		// Spalte  Stufe ****
		grid.add(this.makeLabel("Stufe", GridLayout_middle, true, false, 0), GridLayout_HeaderLeft);

		// Spalte  ****
//		BT_New btNew = new BT_New(m_LaufzettelID, null, null, null);
		WF_LIST_BT_NEW_EXTERNAL btNew = new WF_LIST_BT_NEW_EXTERNAL(this,m_LaufzettelID, null,null,null);
		btNew.add_IDValidator(new ValidateLaufzettelGeloescht());
		btNew.add_IDValidator(new ValidateLaufzettelAbgeschlossen());
		
		grid.add(btNew, GridLayout_HeaderCenter_NoInsets);
		
		// Spalte ****
		BT_SendMail btMail = new BT_SendMail(m_LaufzettelID);
		grid.add(btMail,GridLayout_HeaderCenter_NoInsets);
		
		// Spalte ****
		grid.add (this.makeLabel("", GridLayout_middle, true, false, 0), GridLayout_HeaderLeft);
				
		// Spalte ****
		grid.add(this.makeLabel("", GridLayout_middle, true, false, 0), GridLayout_HeaderLeft);
		
		// Spalte Prio****
		grid.add(this.makeLabel("Prio", GridLayout_middle, true, false, 0), GridLayout_HeaderLeft);
		
		// Spalte Privat ****
		grid.add(this.makeLabel("Priv", GridLayout_HeaderLeft, true, false, 0));

		// Spalte Bearbeiter ****
		grid.add(this.makeLabel("Bearbeiter", GridLayout_left, true, false, 0), GridLayout_HeaderLeft);
		// Spalte Aufgabe ****
		grid.add(this.makeLabel("Aufgabe / Bericht", GridLayout_left, true, false, 0), GridLayout_HeaderLeft);
		// Spalte Termin ****
		grid.add(this.makeLabel("Termin", GridLayout_middle, true, false, 0), GridLayout_HeaderLeft);
		// Spalte Besitzer****
		grid.add(this.makeLabel("Besitzer", GridLayout_left, true, false, 0), GridLayout_HeaderLeft);
		// Spalte Erledigt ****
		grid.add(this.makeLabel("Abgeschl. am / von", GridLayout_middle, true, false, 0), GridLayout_HeaderLeft);
		

		// spaltenbreiten auseinanderziehen
		int iCol = 0;
		grid.setColumnWidth(iCol++, new Extent(40));
		grid.setColumnWidth(iCol++, new Extent(25));
		grid.setColumnWidth(iCol++, new Extent(25));
		grid.setColumnWidth(iCol++, new Extent(25));		
		grid.setColumnWidth(iCol++, new Extent(25));
		grid.setColumnWidth(iCol++, new Extent(60));
		grid.setColumnWidth(iCol++, new Extent(25));
		grid.setColumnWidth(iCol++, new Extent(100));
		grid.setColumnWidth(iCol++, new Extent(570));
		grid.setColumnWidth(iCol++, new Extent(100));
		grid.setColumnWidth(iCol++, new Extent(100));
		grid.setColumnWidth(iCol++, new Extent(100));


		//grid.setBackground(colGrid);

	}

	/***
	 * Erzeugt ein formatiertes Label für das Grid
	 * 
	 * @param cText
	 * @param bIstZahl
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param pSize
	 *            - relative Größe: 0 = normal, -1 kleiner, +1 größer
	 * @return MyE2_Label: das neue Label
	 */
	
	private MyE2_Label makeLabel(String cText, CellLayoutData LayoutData,boolean bIsTitle, boolean bIsDeleted, int pSize)
	{
		return makeLabel(cText, LayoutData, bIsTitle, bIsDeleted, pSize, null, null);
	}
	
	
	/***
	 * Erzeugt ein formatiertes Label mit benutzernamen für das Grid
	 * 
	 * @param cText
	 * @param bIstZahl
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param pSize
	 *            - relative Größe: 0 = normal, -1 kleiner, +1 größer
	 * @return MyE2_Label: das neue Label
	 */
	
	private MyE2_Label makeLabelUser(String id_user, CellLayoutData LayoutData,	boolean bIsTitle, boolean bIsDeleted, int pSize) {
		if (S.isFull(id_user)) {
			try {
				RECORD_USER_EXT rl = new RECORD_USER_EXT(this.m_reclistUser.get(id_user));
				MyE2_Label lb= makeLabel(rl.get__vorname_name1(), LayoutData, bIsTitle, bIsDeleted, pSize, null, null);
				lb.setLineWrap(true);
				return lb;
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return makeLabel(id_user, LayoutData, bIsTitle, bIsDeleted, pSize, null, null);

	}
	
	
	
	/***
	 * Erzeugt ein formatiertes Label für das Grid
	 * 
	 * @param cText
	 * @param bIstZahl
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param pSize
	 *            - relative Größe: 0 = normal, -1 kleiner, +1 größer
	 * @param Tooltip
	 *            : wenn nicht null, wird der Tooltip des Labels gesetzt
	 * @return MyE2_Label: das neue Label
	 */
	private MyE2_Label makeLabel(String cText, CellLayoutData LayoutData,boolean bIsTitle, boolean bIsDeleted, int pSize, String ToolTip)
	{
		return makeLabel(cText, LayoutData, bIsTitle, bIsDeleted, pSize, null, ToolTip);
	}

	/***
	 * Erzeugt ein formatiertes Label für das Grid
	 * 
	 * @param cText
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param pSize
	 *            - relative Größe: 0 = normal, -1 kleiner, +1 größer
	 * @param backgroundcol
	 *            : wenn nicht null, wird die gesetzte Farbe als
	 *            Hintergrundfarbe gesetzt
	 * @param cTextComplete 
	 * @param bIstZahl
	 * @return MyE2_Label: das neue Label
	 */
	private MyE2_Label makeLabel(String cText, CellLayoutData LayoutData,
			boolean bIsTitle, boolean bIsDeleted, int pSize, Color backgroundcol, String cTextComplete)
	{

		MyE2_Label oLabRueck = new MyE2_Label();

		String ccText = "";

		if (cText == null)
		{
			return oLabRueck;
		}

		if (cText.equals("###"))
			ccText = "-";
		else
			ccText = cText;

		E2_Font oDelFont = new E2_Font(Font.ITALIC + Font.LINE_THROUGH, pSize);

		if (bIsTitle)
		{
			oLabRueck = new MyE2_Label(new MyE2_String(ccText));
			// oLabRueck.setFont(new E2_FontBold(pSize));
		} else
		{
			oLabRueck = new MyE2_Label(ccText);
			if (bIsDeleted)
				oLabRueck.setFont(oDelFont);
			else
				oLabRueck.setFont(new E2_FontPlain(pSize));
		}

		oLabRueck.setLayoutData(LayoutData);

		if (backgroundcol != null)
		{
			oLabRueck.setBackground(backgroundcol);
		
		}
		
		if (cTextComplete != null)
		{
			oLabRueck.setToolTipText(cTextComplete);
		}

		return oLabRueck;
	}

	
	
	/***
	 * Erzeugt ein formatiertes Label für das Grid
	 * 
	 * @param cText
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param pSize
	 *            - relative Größe: 0 = normal, -1 kleiner, +1 größer
	 * @param backgroundcol
	 *            : wenn nicht null, wird die gesetzte Farbe als
	 *            Hintergrundfarbe gesetzt
	 * @param cTextComplete 
	 * @param bIstZahl
	 * @return MyE2_Label: das neue Label
	 */
	private MyE2_TextArea makeTextArea(String cText, GridLayoutData LayoutData,
			boolean bIsTitle, boolean bIsDeleted, int pSize, Color backgroundcol, String cTextComplete){
		return makeTextArea(cText, LayoutData, bIsTitle, bIsDeleted, pSize, backgroundcol, cTextComplete, false);
	}
	
	
	/***
	 * Erzeugt ein formatiertes Label für das Grid
	 * 
	 * @param cText
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param pSize
	 *            - relative Größe: 0 = normal, -1 kleiner, +1 größer
	 * @param backgroundcol
	 *            : wenn nicht null, wird die gesetzte Farbe als
	 *            Hintergrundfarbe gesetzt
	 * @param cTextComplete 
	 * @param bIsAnswer :true, wenn es das Antwortfeld ist (Kursiv)
	 * @return MyE2_Label: das neue Label
	 */
	private MyE2_TextArea makeTextArea(String cText, GridLayoutData LayoutData,
			boolean bIsTitle, boolean bIsDeleted, int pSize, Color backgroundcol, String cTextComplete, boolean bIsAnswer)
	{

		MyE2_TextArea oTextRueck = new MyE2_TextArea();

		String 	ccText = "";
		int 	iRowToAdd = 0;
		
		if (cText == null)
		{
			return oTextRueck;
		}

		E2_Font oFontAnswer = new E2_Font(Font.ITALIC, pSize);
		E2_Font oDelFont = new E2_Font(Font.ITALIC + Font.LINE_THROUGH, pSize);
		
		if (cText.equals("###")){
			ccText = "-";
		}
		else{
			ccText = cTextComplete;
			iRowToAdd = 1;
		}
		
		String[] rows = cTextComplete.split("\n");
		
		// vorrausgesetzt man geht von 90 Zeichen in der BOX aus...
		int r1= cTextComplete.length() / 90;
		int r = (int) Math.round((rows.length + iRowToAdd) * 1.2);
		r = Math.max(r1, r);
		
		if (r > 6) r = 6;
		
		oTextRueck = new MyE2_TextArea(ccText,600,4000,r);

		if (bIsDeleted){
			oTextRueck.setFont(oDelFont);
		}
		else if (bIsAnswer){
			oTextRueck.setFont(oFontAnswer);
		}
		else {
		  	oTextRueck.setFont(new E2_FontPlain(pSize));
		}


		if (backgroundcol != null)
		{
			oTextRueck.setBackground(backgroundcol);
		}
		
		if (cTextComplete != null)
		{
			oTextRueck.setToolTipText(cTextComplete);
		}

		try {
			oTextRueck.set_bEnabled_For_Edit(false);
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oTextRueck;
	}

	
	
	
	/**
	 * Erzeugt eine Row mit depth*Bildchen drin um eine Hierarchie-Anzeige zu
	 * simulieren
	 * 
	 * @param depth
	 * @return
	 */
	private MyE2_Row generateTreeDepthIndicator(int depth)
	{
		MyE2_Row row = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		for (int i = 0; i < depth - 1; i++)
		{
			row.add(new MyE2_Label(E2_ResourceIcon.get_RI("tree_leer.png")));
		}
		if (depth > 1)
		{
			row.add(new MyE2_Label(E2_ResourceIcon.get_RI("tree1.png")));
		} else
		{
			row.add(new MyE2_Label(E2_ResourceIcon.get_RI("tree0.png")));
		}

		return row;
	}







	/**
	 * Button-Klasse: Editieren von Laufzettel-Einträgen
	 * @author manfred
	 */
	private class BT_Edit extends MyE2_Button
	{

		private String m_ID_Laufzettel = null;
		private String m_ID_Laufzettel_Eintrag = null;

		private String m_ID_User_Bearbeiter = null;

		/**
		 * @param sIDLaufzettelEintrag
		 * @param img
		 */
		public BT_Edit(String sIDLaufzettel, String sIDLaufzettelEintrag,
				String sUserBearbeiter)
		{
			//super(E2_ResourceIcon.get_RI("edit.png"));
			super ();
			m_ID_Laufzettel = sIDLaufzettel;
			m_ID_Laufzettel_Eintrag = sIDLaufzettelEintrag;
			m_ID_User_Bearbeiter = sUserBearbeiter;

			E2_ResourceIcon ico_edit = E2_ResourceIcon.get_RI("edit.png");
			this.__setImages(ico_edit, ico_edit);
			
			
			this.add_GlobalAUTHValidator(
					E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,
					"BEARBEITE_LAUFZETTEL_EINTRAG");

			this.add_oActionAgent(new EditActionAgent());
			this.setToolTipText(new MyE2_String("Diese Aufgabe bearbeiten / abschließen.").CTrans());
		}

		
		private class EditActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				execInfo.get_MyActionEvent().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(BT_Edit.this.m_ID_Laufzettel));
				
				if (!bibMSG.get_bHasAlarms())
				{

					WF_MASK_BasicModuleContainer oMaskEintrag = new WF_MASK_BasicModuleContainer(
							BT_Edit.this.m_ID_Laufzettel,
							BT_Edit.this.m_ID_User_Bearbeiter, null);

					oMaskEintrag.get_oRowForButtons().removeAll();

					maskButtonSaveMask BT_saveMask = new maskButtonSaveMask(oMaskEintrag, new E2_SaveMaskStandard(oMaskEintrag,	null), null, null);

					BT_saveMask.add_oActionAgent(new XX_ActionAgent()
					{
						@Override
						public void executeAgentCode(ExecINFO execInfo)
								throws myException
						{
							//
							// nach dem Speichern des Laufzetteleintrags noch prüfen, ob ein neuer erzeugt werden soll
							//
							WF_Handler oHandler = new WF_Handler();
							
							oHandler.createNew_WFEntry_ForReminder(m_ID_Laufzettel, m_ID_Laufzettel_Eintrag,true,true);
							
							oHandler.sendMessagesIfStatusChanged(m_ID_Laufzettel, m_ID_Laufzettel_Eintrag);
							
							
							//
							// die Anzeige aktualisieren
							WF_LIST_EXPANDER_4_Compressed_Entries.this.refreshDaughterComponent();
						}

					});

					
					oMaskEintrag.get_oRowForButtons().add(BT_saveMask);
					oMaskEintrag.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskEintrag));

					
					
					E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskEintrag	.get_vCombinedComponentMAPs();
					vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,BT_Edit.this.m_ID_Laufzettel_Eintrag);

					oMaskEintrag.CREATE_AND_SHOW_POPUPWINDOW(null, null,new MyE2_String("Bearbeite die Aufgabe"));

				}
			}

		}
	}

	/**
	 * Button-Klasse zum löschen von Laufzettel-Einträgen
	 * @author manfred
	 * 
	 */
	private class BT_Delete extends MyE2_Button
	{

		private RECLIST_LAUFZETTEL_EINTRAG m_oChildList = null;
		private String m_wfEntryId = null;

		// private String UserBearbeiterID = null;

		/**
		 * @param img
		 */
		public BT_Delete(RECLIST_LAUFZETTEL_EINTRAG oChilds, String id)
		{
			super();
			E2_ResourceIcon ico_del = E2_ResourceIcon.get_RI("delete.png");
			this.__setImages(ico_del, ico_del);
			

			m_oChildList = oChilds;
			m_wfEntryId = id;

			this.add_GlobalAUTHValidator(
					E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,
					"LOESCHE_LAUFZETTEL_EINTRAG");

			String sDel = "";
			String sDelMsg = "";

			
			try
			{	RECORD_LAUFZETTEL_EINTRAG oRec = oChilds.get(id);
				sDel = oRec.get_GELOESCHT_cUF() + "";
			} catch (myException e1)
			{
				sDel = "";
			}

			if (sDel.equals("Y"))
			{
				sDelMsg = "Laufzetteleintrag wiederherstellen?";
			} else
			{
				sDelMsg = "Laufzetteleintrag löschen?";
			}

			this.add_oActionAgent(new DeleteActionAgent(oChilds, m_wfEntryId,sDelMsg));
			this.setToolTipText(new MyE2_String("Diesen Laufzetteleintrag löschen.").CTrans());
			
			this.add_IDValidator(new ValidateLaufzettelGeloescht());
			this.add_IDValidator(new ValidateLaufzettelAbgeschlossen());
			
			
			this.add_IDValidator(new XX_ActionValidator()
			{
				@Override
				public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
				{
					return null;
				}

				@Override
				protected MyE2_MessageVector isValid(String unformated)
						throws myException
				{
					
					// Besitzer und supervisor des Laufzettels holen
					String sOwner = S.NN(m_oLaufzettel.get_ID_USER_BESITZER_cUF());
					String sSupervisor = S.NN(m_oLaufzettel.get_ID_USER_SUPERVISOR_cUF());
										
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					
					RECORD_LAUFZETTEL_EINTRAG oRec =  m_oChildList.get(m_wfEntryId);
					
					try
					{	
						if ( !(oRec.get_ID_USER_BESITZER_cUF().equals(bibALL.get_ID_USER()) 
								|| bibALL.get_bIST_SUPERVISOR()
								|| sOwner.equals(bibALL.get_ID_USER())
								|| sSupervisor.equals(bibALL.get_ID_USER()))
							)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(
									"Nur der Besitzer oder der Supervisor dürfen die Aufgabe löschen!"));
						}
						
						if (oRec.get_ID_USER_ABGESCHLOSSEN_VON_cUF() == null 
								&& oRec.get_KADENZ_NACH_ABSCHLUSS_cUF() != null
								&& oRec.get_GELOESCHT_cUF_NN("N").equalsIgnoreCase("N")
							) {
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Aufgabe kann nicht gelöscht werden da die Wiedervorlage nach Abschluss gesetzt ist.\n" +
											"Leeren Sie das Feld für die Wiedervorlage nach Abschluss.")));

						}
						
						
					} catch (myException e)
					{
						e.printStackTrace();
						oMV.add_MESSAGE(e.get_ErrorMessage());
					}
					

					// Abgeschlossener Eintrag kann nicht gelöscht werden
					try
					{
						if (!oRec.get_ABGESCHLOSSEN_AM_cUF_NN("").isEmpty() )
						{
							oMV.add(new MyE2_Alarm_Message("Eine abgeschlossene Aufgabe kann nicht gelöscht werden!"));
						}
					}
					catch(myException ex)
					{
						ex.printStackTrace();
						oMV.add_MESSAGE(ex.get_ErrorMessage());
					}
					
					
					
					
					return oMV;
				}
			});
		}

		private class DeleteActionAgent extends ButtonActionAgent_TOGGLE_Y_N
		{
			private String m_wfEntryId = null;
			private String m_wfParentId = null;
			RECLIST_LAUFZETTEL_EINTRAG m_oChildList = null;

			public DeleteActionAgent(RECLIST_LAUFZETTEL_EINTRAG oChilds,
					String sWFentryID, String sMessage)
			{
				super(new MyE2_String(sMessage), null, "GELOESCHT",
						"JT_LAUFZETTEL_EINTRAG", "ID_LAUFZETTEL_EINTRAG");
				m_wfEntryId = sWFentryID;
				m_oChildList = oChilds;

				
				try
				{	
					RECORD_LAUFZETTEL_EINTRAG oRec = m_oChildList.get(sWFentryID);
					m_wfParentId = oRec.get_ID_EINTRAG_PARENT_cUF();
				} catch (myException e)
				{
					// no Parent found...
					m_wfParentId = null;
				}

			}

			@Override
			public MyE2_MessageVector CheckIdToToggle(Vector unformatedToDelete)
			{
				return null;
			}

			@Override
			public void Execute_After_TOGGLE(Vector ds_toToggleUnformated)
					throws myException
			{
				WF_LIST_EXPANDER_4_Compressed_Entries.this
						.refreshDaughterComponent();
			}

			@Override
			public void Execute_Before_TOGGLE(Vector ds_toToggleUnformated)
					throws myException
			{
			}

			@Override
			public Vector<String> get_vSQL_After_TOGGLE(
					String toggleUnformated, String newValue)
					throws myException
			{
				return new Vector<String>();
			}

			@Override
			public Vector<String> get_vSQL_Before_TOGGLE(
					String toggleUnformated, String newValue)
					throws myException
			{
				String sUpateDeletes = "UPDATE "
						+ bibALL.get_TABLEOWNER()
						+ ".jt_laufzettel_eintrag SET id_eintrag_parent = "
						+ m_wfParentId + " WHERE id_eintrag_parent = "
						+ m_wfEntryId;

				return bibALL.get_Vector(sUpateDeletes);

			}

			public Vector<String> get_IDS_FOR_Toggle() throws myException
			{
				return bibALL.get_Vector(this.m_wfEntryId);
			}

		}
	}


	/***
	 * Button zum Versenden von Mails 
	 * @author manfred
	 *
	 */
	protected class BT_SendMail extends MyE2_Button
	{

		private String m_ID_Laufzettel = null;
		private String m_ID_Laufzettel_Eintrag = null;
		private RECLIST_LAUFZETTEL_EINTRAG m_oChilds = null;
		
		/*
		 * komponenten fuer die e-Mail-Sende-Abfrage
		 */
		MyE2_Column oColumn = null; 			
		MyE2_TextArea oTextArea= null;
		MyE2_CheckBox oSendToClosedEntries = null;

		
		/***
		 * Konstruktor,wenn man die Mails an alle bearbeier des Laufzettels schicken will
		 * z.B. aus dem Laufzettel selbst heraus
		 * 
		 * Sender ist immer der aktuell angemeldetet Benutzer
		 * 
		 * @param IDLaufzettel
		 * @param sUserBearbeiter
		 * 
		 */
		public BT_SendMail(String IDLaufzettel)
		{
			this(IDLaufzettel, null,null);
		}

		
		
		
		/**
		 * Button zum Mailversand von Laufzettel-Nachrichten.
		 * Es gibt 2 implizite Möglichkeiten: 
		 * 	1. Es sollen Mails an alle Laufzetteleinträge-Bearbeiter eines Laufzettels verschickt werden  (sIDLaufzettelEintrag == null)
		 * 	2. Es soll eine Mail für genau einen Laufzetteleintrag verschickt werden. (sIDLaufzettelEintrag != null)
		 * 
		 * @param sIDLaufzettel	
		 * @param sIDLaufzettelEintrag
		 * @param oChilds  - 
		 * 	
		 */
		public BT_SendMail(String sIDLaufzettel, String sIDLaufzettelEintrag, RECLIST_LAUFZETTEL_EINTRAG oChilds)
		{
			
			super ();
			m_ID_Laufzettel = sIDLaufzettel;
			m_ID_Laufzettel_Eintrag = sIDLaufzettelEintrag;
			m_oChilds = oChilds;

			E2_ResourceIcon ico_mail = null;
			if (sIDLaufzettelEintrag == null)
			{
				ico_mail = E2_ResourceIcon.get_RI("mass_email.png");
				this.setToolTipText(new MyE2_String("Alle Aufgaben per Mail verschicken").CTrans());
			}
			else
			{
				ico_mail = E2_ResourceIcon.get_RI("email_small.png");
				this.setToolTipText(new MyE2_String("Diese Aufgabe per Mail verschicken").CTrans());
			}
			
			this.__setImages(ico_mail, ico_mail);
			
			
			this.add_GlobalAUTHValidator(
					E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"SENDE_LAUFZETTEL_EINTRAG");

			this.add_oActionAgent(new MailButton_ActionAgent());
			
			// Laufzettel-Validierer
			this.add_IDValidator(new ValidateLaufzettelAbgeschlossen());
//			this.add_IDValidator(new ValidateLaufzettelGeloescht());

			
			this.add_IDValidator(new XX_ActionValidator(){

				@Override
				public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				protected MyE2_MessageVector isValid(String unformated)
						throws myException {
					
					// Besitzer und supervisor des Laufzettels holen
					String sOwner = S.NN(m_oLaufzettel.get_ID_USER_BESITZER_cUF());
					String sSupervisor = S.NN(m_oLaufzettel.get_ID_USER_SUPERVISOR_cUF());
					
					RECORD_LAUFZETTEL_EINTRAG oRec = null;
					
					if (m_ID_Laufzettel_Eintrag != null && m_oChilds != null){
						// lesen des Laufzettel-Eintrags
						oRec = m_oChilds.get(m_ID_Laufzettel_Eintrag);
					}
					
					
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					try
					{
						
						if (oRec != null)
						{// wenn es die Mail für einen Eintrag ist, dann das hier:
							if ( !(oRec.get_ID_USER_BESITZER_cUF().equals(bibALL.get_ID_USER()) 
									|| bibALL.get_bIST_SUPERVISOR()
									|| sOwner.equals(bibALL.get_ID_USER())
									|| sSupervisor.equals(bibALL.get_ID_USER()))
								)
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message(
										"Nur der Besitzer oder Supervisor dürfen das!"));
							}
						}
						else
						{// es ist ein Mailbutton für den gesamten Laufzettel
							if (!(sOwner.equals(bibALL.get_ID_USER())	
									|| sSupervisor.equals(bibALL.get_ID_USER()) 
									|| bibALL.get_bIST_SUPERVISOR())
								)
								{
									oMV.add_MESSAGE(new MyE2_Alarm_Message("Nur der Besitzer des Laufzettels oder Supervisor dürfen das!"));
								}
						}
						
					}catch (myException e)
					{
						e.printStackTrace();
						oMV.add_MESSAGE(e.get_ErrorMessage());
					}
					
					
					
					// gelöschte Einträge können nicht einzeln verschickt werden.
					try
					{
						if (oRec != null)
						{// wenn es die Mail für einen Eintrag ist, dann das hier:
							//RECORD_LAUFZETTEL_EINTRAG oRec = m_oChilds.get(m_ID_Laufzettel_Eintrag);
							if (   !(
									!oRec.get_GELOESCHT_cUF_NN("").equalsIgnoreCase("Y") 
									|| bibALL.get_bIST_SUPERVISOR()
									|| sOwner.equals(bibALL.get_ID_USER())
									|| sSupervisor.equals(bibALL.get_ID_USER())
								))
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message(
										"Gelöschte Einträge dürfen nicht gemailt werden."));
							}
						}

					} catch (myException e2)
					{
						e2.printStackTrace();
						oMV.add_MESSAGE(e2.get_ErrorMessage());
					}	
					
					
					
					return oMV;
				}
				
			});
		}

		
		/***
		 * Action Agent zeigt einen Dialog an, der den Kopftext der Mail eingeben lässt,
		 * und wenn es eine Multi-Mail ist für den ganzen Laufzettel, dann wird noch abgefragt, ob auch noch
		 * die abgeschlossenen Einträge gesendet werden sollen! 
		 * @author manfred
		 * @date 13.11.2008
		 */
		private class MailButton_ActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{

				execInfo.get_MyActionEvent()
				.make_ID_Validation_ADD_TO_Global_MV(
						bibALL.get_Vector(BT_SendMail.this.m_ID_Laufzettel));
				if (!bibMSG.get_bHasAlarms())
				{
					int nrows = 10;
					
					oColumn =  new MyE2_Column();
					oTextArea= new MyE2_TextArea(new MyE2_String("Es gibt eine Laufzettel-Info für Sie...").toString(),300,2000,10);
					oSendToClosedEntries = new MyE2_CheckBox(new MyE2_String("Auch abgeschlossene Einträge berücksichtigen!"));
			
					// komponenten fuer eMail-senden 
					oColumn.setInsets(E2_INSETS.I_0_0_0_0);
					oColumn.add(new MyE2_Label(new MyE2_String("Text:")));
					oColumn.add(oTextArea);
					oTextArea.set_iRows(10);
					oTextArea.set_iWidthPixel(500);
					
					if(BT_SendMail.this.m_ID_Laufzettel_Eintrag == null)
					{	// Braucht man nur bei Mehrfachmails...
						oTextArea.set_iRows(nrows - 2);
						oColumn.add(oSendToClosedEntries);
					}
					
					ownConfirmContainer oMessageBox = new ownConfirmContainer(
							new MyE2_String("Laufzettel-Mails verschicken"),
							new MyE2_String("Mail-Text anpassen"),
							null,
							oColumn,
							null,
							new MyE2_String("Mailen..."), 
							new MyE2_String("Abbrechen"),
							new Extent(300),
							new Extent(300));
	
					
//					oMessageBox.get_oButtonOK().activate_KeyCode(-1);
					
					// den ActionAgent für die Mailverschickung dran hängen!!
					oMessageBox.set_ActionAgentForOK(new MailSendActionAgent(BT_SendMail.this.m_ID_Laufzettel,BT_SendMail.this.m_ID_Laufzettel_Eintrag,oMessageBox));
					
					oMessageBox.show_POPUP_BOX();
				}
			}
		}
		
		
		private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
		{

			public ownConfirmContainer(MyE2_String windowtitle,
					MyE2_String texttitle, Vector<MyString> innerTextblock,
					Component innerComponent,
					XX_ActionValidator validatorForOK_Button,
					MyE2_String labelOKButton, MyE2_String labelCancelButton,
					Extent width, Extent height)  throws myException
			{
				super(windowtitle, texttitle, innerTextblock, innerComponent,
						validatorForOK_Button, labelOKButton, labelCancelButton, width, height);

			}
			
		}
		
		/**
		 * Action Agent, den man dem Abfrage-Dialog mitgeben muss, damit dann die Mails verschickt werden können 
		 * @author manfred
		 * @date 13.11.2008
		 */
		private class MailSendActionAgent extends XX_ActionAgent
		{

			private String m_ID_Laufzettel = null;
			private String m_ID_Eintrag = null;
			@SuppressWarnings("unused")
			private E2_ConfirmBasicModuleContainer m_calling_Messagebox = null;
			
			/***
			 * Konstruktor zum übernehmen der IDs füs verschicken der Mails
			 * @param ID_Laufzettel_ID
			 * @param ID_Laufzettel_Eintrag_ID
			 */
			public MailSendActionAgent(String ID_Laufzettel_ID, String ID_Laufzettel_Eintrag_ID,E2_ConfirmBasicModuleContainer caller)
			{
				this.m_ID_Eintrag = ID_Laufzettel_Eintrag_ID;
				this.m_ID_Laufzettel = ID_Laufzettel_ID;
				this.m_calling_Messagebox = caller;
			}
			
			
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				
//				MyE2_TextArea oTextArea = 
//				m_calling_Messagebox.get_add_AddOnComponentVector().elementAt(0);
					
				String sEingabe = BT_SendMail.this.oTextArea.getText();
				boolean bErledigte = BT_SendMail.this.oSendToClosedEntries.isSelected();
				
				WF_MailHelper mailHelper = null;
				
				// TODO Auto-generated method stub
				if (this.m_ID_Eintrag == null){
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("Mail verschicken an alle Bearbeiter von Laufzettel: " + this.m_ID_Laufzettel));
					mailHelper = new WF_MailHelper(this.m_ID_Laufzettel);
						
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("Mail verschicken an Bearbeiter von Aufgabe: " +this.m_ID_Laufzettel + "/" + this.m_ID_Eintrag));
					mailHelper = new WF_MailHelper(this.m_ID_Laufzettel,this.m_ID_Eintrag);
				}
				
				if (mailHelper != null)
				{
					mailHelper.set_MailBetreff("Es gibt eine Laufzettel-Info für Sie...");
					mailHelper.set_MailBodyText(sEingabe);
					mailHelper.set_MailWhenClosed(bErledigte);
					
					// die Mail(s) werden verschickt!	
					mailHelper.SendMail();
				}
			}
		}
	}

		
	/**
	 * Ein Validator der prüft, ob der Laufzettel schon abgeschlossen ist
	 * @author manfred
	 * @date 31.10.2008
	 */
	private class ValidateLaufzettelAbgeschlossen extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)	throws myException {
			
			// Besitzer und supervisor des Laufzettels holen
			String sAbgeschlossen = S.NN(m_oLaufzettel.get_ABGESCHLOSSEN_AM_cF());
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (sAbgeschlossen != null && sAbgeschlossen.length() > 0 )
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(	"Einen abgeschlossenen Laufzettel kann man nicht mehr erweitern/löschen/verschicken!"));
			}
			
			return oMV;
		}
	}
	
	
	
	
	/**
	 * Ein Validator der prüft, ob der Laufzettel schon gelöscht ist
	 * @author manfred
	 * @date 31.10.2008
	 */
	private class ValidateLaufzettelGeloescht extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)	throws myException {
			
			// Besitzer und supervisor des Laufzettels holen
			String sAbgeschlossen = S.NN(m_oLaufzettel.get_GELOESCHT_cF());
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (sAbgeschlossen != null && sAbgeschlossen.equalsIgnoreCase("Y") )
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(	"Einen gelöschten Laufzettel kann man nicht mehr erweitern/ändern/verschicken!"));
			}
			
			return oMV;
		}
	}

	


	
	
	/**
	 * Ein Validator der prüft, ob der Laufzetteleintrag schon abgeschlossen ist
	 * @author manfred
	 * @date 6.11.2008  
	 */
	private class ValidateLaufzettelEintragAbgeschlossen extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)	throws myException {
			
			// Besitzer und supervisor des Laufzettels holen
			String sAbgeschlossen = S.NN(m_oLaufzettel.get_GELOESCHT_cF());
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (sAbgeschlossen != null && sAbgeschlossen.equalsIgnoreCase("Y") )
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(	"Eine gelöschte Aufgabe kann man nicht mehr ändern!"));
			}
			
			return oMV;
		}
	}



	@Override
	public ArrayList<HashMap<String, Component>> get_ComponentListDaughter(
			String cIDROWUnformated) throws myException {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Button zum Anhängen von Attachements an die Workflow-Aufgabe
	 * @author manfred
	 * @date 13.06.2016
	 *
	 */
	private class BT_AttachementToWFEntry extends E2_ButtonUpDown {
		String sTableID = null;
		public BT_AttachementToWFEntry(String TableID) {
			super(E2_MODULNAME_ENUM.MODUL.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE.get_callKey(), 
					LAUFZETTEL_EINTRAG.baseTabName(), TableID, "Zusatzdatei zur Laufzettel-Aufgabe hinzufügen oder Scannen");
			sTableID = TableID;
			initButton();
		}
		
		public void initButton() {
			try {
				RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(LAUFZETTEL_EINTRAG.baseTabName(), sTableID, null,null);
				if (rlArchivmedienNachricht.size() > 0){
					this.__setImages(E2_ResourceIcon.get_RI("attach_mini_green.png"), E2_ResourceIcon.get_RI("leer.png"));
				}
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	


	/**
	 * sucht den benutzer-superselektor und hightlighted die dargestellten benutzerlabels via hintergrundfarbe
	 * @param id_user_to_show_in_field
	 * @return
	 */
	private GridLayoutData find_gl(String id_user_to_show_in_field) {
		RB_gld gl = new RB_gld();
		
		try {
//			RECORD_USER  user_actual = new WF_search_recuser_from_superselector().get_rec_user(); 					//this.find_user_from_Superselection();
			WF_HEAD_LIST_Selection_User_DropDown super_selektor = new WF_search_HEAD_LIST_Selection_User_DropDown().get_HEAD_LIST_Selection_User_DropDown();
			
			String id_user_from_superSelector = null;
			if (super_selektor != null) {
				id_user_from_superSelector=super_selektor.get_ActualWert();
			}
			id_user_from_superSelector = S.NN(id_user_from_superSelector,bibALL.get_ID_USER());   //wenn nix ausgewaehlt, dann den eigenen user highlighten
			
			if (id_user_from_superSelector.equals(id_user_to_show_in_field)) {
				gl._col(WF_HEAD_CONST.HIGHLIGHT_COLOR_4_USER_FIELDS);
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return gl;
	}
	
	
	
//	private String find_user_from_Superselection() {
//		String ru = null;
//		try {
//			// suchen nach der User-Selektion WF_HEAD_LIST_Selection_User_DropDown
//			E2_RecursiveSearchParent_BasicModuleContainer oSearch = new E2_RecursiveSearchParent_BasicModuleContainer( this.oNaviList );
//			E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();
//			E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WF_HEAD_LIST_Selection_User_DropDown.class.getName()), null);
//			Vector<Component> vResult = oSearchComps.get_vAllComponents();
//			if (vResult.size() > 0){
//				WF_HEAD_LIST_Selection_User_DropDown oSelect = (WF_HEAD_LIST_Selection_User_DropDown)vResult.get(0);
//				ru = oSelect.get_ActualWert();
//			}
//		} catch (myException e) {
//			e.printStackTrace();
//		}
//		
//		return ru;
//	}
	
	
}
