package panter.gmbh.Echo2.ListAndMask.List;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_Const.ListBuildType;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_Usersetting_ListSettings;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingRowsInNavigationList;
import panter.gmbh.Echo2.components.MyE2IF__CanGetStampInfo;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__Indirect;
import panter.gmbh.Echo2.components.MyE2IF__IndirectHELPER;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__ComponentContainer;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLFieldMAPPrep;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.vectorForSegmentation;


//2014-11-06: stampinfo - interface

public class E2_NavigationList extends E2_NavigationObject implements MyE2IF__CanGetStampInfo
{
	//hashkeys fuer die schalterstellungen der navigationsliste
	public static String NAVILIST_MULTISELECT_IN_LIST = 					"NAVILIST_MULTISELECT_IN_LIST";
	public static String NAVILIST_MARK_SELECTED_ROWS = 						"NAVILIST_MARK_SELECTED_ROWS";
	public static String NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE = 	"NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE";
	public static String NAVILIST_MARK_COLOR_RED = 							"NAVILIST_MARK_COLOR_RED";
	public static String NAVILIST_MARK_COLOR_GREEN = 						"NAVILIST_MARK_COLOR_GREEN";
	public static String NAVILIST_MARK_COLOR_BLUE = 						"NAVILIST_MARK_COLOR_BLUE";

	public static String NAVILIST_MARK_FONTSIZE = 							"NAVILIST_MARK_FONTSIZE";
	public static String NAVILIST_MARK_FONT_BOLD = 							"NAVILIST_MARK_FONT_BOLD";
	public static String NAVILIST_MARK_FONT_ITALIC = 						"NAVILIST_MARK_FONT_ITALIC";
	
	
	
	
	public static Vector<String> NAVILIST_SETTINGS_HASHMAP = 
		bibALL.get_Vector(
				NAVILIST_MULTISELECT_IN_LIST, NAVILIST_MARK_SELECTED_ROWS, NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE,
				NAVILIST_MARK_COLOR_RED,NAVILIST_MARK_COLOR_GREEN,NAVILIST_MARK_COLOR_BLUE,
				NAVILIST_MARK_FONTSIZE,NAVILIST_MARK_FONT_BOLD,NAVILIST_MARK_FONT_ITALIC);

	
	
	public static MutableStyle  STYLE__4_2_4_2 = new Style_Table_Normal(new Border(1, new E2_ColorBase(-50), Border.STYLE_SOLID),new Insets(4,2,4,2));

	
	private E2_NavigationGrid	oDataGrid = new E2_NavigationGrid();
	
	private E2_ComponentMAP 	REF_ComponentMAP = null;
	private MyDBToolBox			oDB	= null;
	
	/*
	 * vector mit den componentMaps aus der query
	 */
	private Vector<E2_ComponentMAP>	  vComponentMAPS = new Vector<E2_ComponentMAP>();
	
	/*
	 * vector mit den neueingabe-zeilen
	 */
	private Vector<E2_ComponentMAP>	 vComponentMAPS_NEW = new Vector<E2_ComponentMAP>();

	
	
	private Vector<String> 		vActualID_Segment = new Vector<String>();
	
	/*
	 * vector enthaelt eine list von ids, die aus der suche resultieren
	 */
	private Vector<String>		vIDs_From_Search = new Vector<String>();
	
	
	/*
	 * vector enthaelt eine list von ids, die aus der selektion resultieren
	 */
	private Vector<String>		vIDs_From_Selektor = new Vector<String>();
	
	
	
	/*
	 * 2011-06-20: vector enthaelt eine list von ids, die von der letzten suche gefunden
	 * wurden, aber nicht in der selektion sind
	 */
	private Vector<String>			vIDs_Found_ButNotInSelektion			= new Vector<String>();

	
	
	/*
	 * schalter, der bestimmt, ob am enden jeder zeile eine linie eingezogen wird 
	 */
	private boolean 				bSeparatorBetweenLines = false;
	
	
	/*
	 * schalter, der das sortieren abschaltet (z.B. wenn editiert wird)
	 */
	private boolean 				bSortIsAllowed = true;
	
	
	/*
	 * normalerweise werden bei neueingabe die ComponentMaps der neuen Eintraege
	 * am ende der Liste angezeigt.
	 * Das kann hier umgedreht werden
	 */
	private boolean					bMAPS_NEW_First = false;
	
	
	
	/*
	 * die layouts fuer die tochterelemente aendern sich und werden erzeugt, allerdings mit aenderbarer backgroundcolor
	 */
	private Color					oColorBackgroundDaughter = 			new E2_ColorLLight();
	private Color					oColorBackgroundDaughterLeftSpace = new E2_ColorBase();
	private Color					oColorBackgroundHeader = 			new E2_ColorDark();
	private Color					oColorBackgroundContent = 			new E2_ColorBase();
	
	
	private String   				cMODULE_IDENTIFIER_OF_CONTAINING_MODULE = null;

	
	private boolean    				bShowNavilistWithHeader = true;
	
	// test: Liste von ActionAgents die gefeuert werden, wenn die List aufgebaut wurde...
	private Vector<XX_ActionAgent>	vActionAgentsAfterListGeneration = new Vector<XX_ActionAgent>();
	private Vector<XX_ActionAgent>	vActionAgentsBeforeListGeneration = new Vector<XX_ActionAgent>();

    // Vector von ActionAgents die aufgerufen werden, wenn der ContentVector neu gesetzt ist.
	private Vector<XX_ActionAgent>	vActionAgents_AfterContentVectorIsSet = new Vector<XX_ActionAgent>();

	//actionagents, werden aufgerufen, wenn die abfrage fuer den segmentationVector abgeschlossen wurde
	private Vector<XX_ActionAgent>	vActionAgentsAfterBuild_BASE_ID_Vector = new Vector<XX_ActionAgent>();
	
	//2011-03-17: Container der navilist uebergeben
//	private E2_BasicModuleContainer   oContainer_NaviList_BelongsTo = null;
	
	
	//2011-09-07: sortierung via temp-table moeglich (wenn z.B. jumper-ergebnisse in der list liegen)
	private boolean                   bSortViaCompleteQuery = true;
	
	
	//2012-08-30: in der liste kann eingeschaltet werde, dass eine Selektion via MyE2_DB_Label_INROW 
	//            nicht singulaer sondern additiv wirkt
	private boolean                   bMultiSelectWithButtonsInList = false;
	
	
	//2012-08-31: hervorheben von selektierten Zeilen innerhalb der Listen (damit wird das zeilenselektieren langsamer)
	private boolean                   bMarkSelectedRows = false;
	private Color   			      oColorForeMarkSelectedRows = new Color(10,10,10);
	private Font   			      	  oFontForeMarkSelectedRows =  null;                    //null heisst: schrift wird nicht veraendert
	
	
	//2012-09-06: Infoanzeige ueber die anzahl der ausgewaehlten zeilen
	private boolean                   bShowInfoSelectedRowCount = false;
	

	//2012-11-16: grid-breite festlegen (versuch, die bildschirmanzeige zu stabilisieren)
	private boolean 				  bSetGridWidthFixed = false;
	
	
	//2013-10-10: die optionale speicherung der Sortierdefinition hinterlegen
	private boolean 					bSaveSortStatus = false;
	
	
	
	private boolean   					bStandardSortButtonsLineWrap = false;
	
	
	//2018-02-08: status einer liste speichern
	private E2_NaviListStatusSaver     statusSaver = null;
	

	
//	//20190222: automatik fuer componentmaps mit E2_ComponentMapMarker 
//	private boolean 	  			    useComponentMapMarkerWhenPresent = false;
	

	/**
	 * Löschen aller ActionAgents, die gefeuert werden, wenn die Liste neu aufgebaut wurde
	 */
	public void remove_allActionsAfterListCompleted(){
		this.vActionAgentsAfterListGeneration.clear();
	}
	
	/**
	 * Hinzufügen eines ActionAgents, der gefeuert wird, wenn die Liste neu aufgebaut wurde
	 * @param (XX_ActionAgent) oAgentFiredAfterSelectionFinished
	 */
	public void add_actionAfterListCompleted(XX_ActionAgent oAgentFiredAfterSelectionFinished){
		this.vActionAgentsAfterListGeneration.add(oAgentFiredAfterSelectionFinished);
	}
		
	/**
	 * Feuern der angemeldeten ActionAgents
	 * @throws myException
	 */
	public void fireActionAgentsAfterListCompleted() throws myException{
		for (int i = 0; i < vActionAgentsAfterListGeneration.size() ; i++){
			XX_ActionAgent o = vActionAgentsAfterListGeneration.get(i);
			o.ExecuteAgentCode(new ExecINFO_OnlyCodeNavigationList(this));
		}
	}
	
	
	/**
	 * Hinzufügen eines ActionAgents, der gefeuert wird, wenn der ContentVektor neu gefüllt wurde
	 * @param (XX_ActionAgent) oActionAgentAfterContentVektorIsSet
	 */
	public void add_actionAfterContentVectorIsSet(XX_ActionAgent oActionAgentAfterContentVektorIsSet){
		this.vActionAgents_AfterContentVectorIsSet.add(oActionAgentAfterContentVektorIsSet);
	}
		
	/**
	 * Feuern der angemeldeten ActionAgents
	 * @throws myException
	 */
	 protected void  fireActionAgentsAfterContentsIsSet() {
		for (int i = 0; i < vActionAgents_AfterContentVectorIsSet.size() ; i++){
			XX_ActionAgent o = vActionAgents_AfterContentVectorIsSet.get(i);
			try {
				o.ExecuteAgentCode(new ExecINFO_OnlyCodeNavigationList(this));
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Loeschen aller ActionAgents, die gefeuert werden, wenn die Liste neu aufgebaut wurde
	 */
	public void remove_allActionsBeforeListStarted(){
		this.vActionAgentsBeforeListGeneration.clear();
	}
	
	/**
	 * Hinzufuegen eines ActionAgents, der gefeuert wird, wenn die Liste neu aufgebaut wurde
	 * @param (XX_ActionAgent) oAgentFiredAfterSelectionFinished
	 */
	public void add_actionBeforeListStarted(XX_ActionAgent oAgentFireBeforeSelectionFinished){
		this.vActionAgentsBeforeListGeneration.add(oAgentFireBeforeSelectionFinished);
	}
		
	/**
	 * Feuern der angemeldeten ActionAgents
	 * @throws myException
	 */
	private void fireActionAgentsBeforeListStarted() throws myException{
		for (int i = 0; i < vActionAgentsBeforeListGeneration.size() ; i++){
			XX_ActionAgent o = vActionAgentsBeforeListGeneration.get(i);
			o.ExecuteAgentCode(new ExecINFO_OnlyCodeNavigationList(this));
		}
	}

	
	// 20170831: neue actionagents-serie, fuer actions nach query des segmentationVectors
	/**
	 * Löschen aller ActionAgents, die gefeuert werden, wenn die Liste neu aufgebaut wurde
	 */
	public void remove_allActionAgentsAfterBuild_BASE_ID_Vector(){
		this.vActionAgentsAfterBuild_BASE_ID_Vector.clear();
	}
	
	/**
	 * Hinzufügen eines ActionAgents, der gefeuert wird, wenn die Liste neu aufgebaut wurde
	 * @param (XX_ActionAgent) oAgentFiredAfterSelectionFinished
	 */
	public void add_actionActionAgentsAfterBuild_BASE_ID_Vector(XX_ActionAgent oAgentFiredAfterSelectionFinished){
		this.vActionAgentsAfterBuild_BASE_ID_Vector.add(oAgentFiredAfterSelectionFinished);
	}
		
	/**
	 * Feuern der angemeldeten ActionAgents
	 * @throws myException
	 */
	public void fireActionAgentsAfterBuild_BASE_ID_Vector() throws myException{
		for (int i = 0; i < vActionAgentsAfterBuild_BASE_ID_Vector.size() ; i++){
			XX_ActionAgent o = vActionAgentsAfterBuild_BASE_ID_Vector.get(i);
			o.ExecuteAgentCode(new ExecINFO_OnlyCodeNavigationList(this));
		}
	}
	
	public Vector<XX_ActionAgent> getvActionAgentsAfterBuild_BASE_ID_Vector() {
		return vActionAgentsAfterBuild_BASE_ID_Vector;
	}
	// Ende:20170831: neue actionagents-serie, fuer actions nach query des segmentationVectors
   
	
	
	
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}
	
	
	
	public E2_NavigationList() throws myException
	{
		super();
	}

	
	
	public void INIT_WITH_ComponentMAP(E2_ComponentMAP 	ocomponentMAP, MutableStyle StyleForGrid, String MODULE_IDENTIFIER_OF_CONTAINING_MODULE) throws myException
	{
		this.REF_ComponentMAP = ocomponentMAP;
		this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULE = MODULE_IDENTIFIER_OF_CONTAINING_MODULE;
		
		//2016-07-06: die reihenfolge in der hashmap sichern
		this.REF_ComponentMAP.save_OriginalHashKeys();
		
		//2011-10-19: ----------------
		//System.out.println(ocomponentMAP.getClass().getName()+"   ------     "+ocomponentMAP.get_oSQLFieldMAP().get_cMAIN_TABLE());
		
		this.set_NEW_remove_OLD_Content(this.oDataGrid);
		this.oDB = bibALL.get_myDBToolBox();

		if (StyleForGrid!=null)
			this.oDataGrid.setStyle(StyleForGrid);
		else
			this.oDataGrid.setStyle(new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)));
		
		/*
		 * falls es eine daughter-anzeige gibt, dann wird dieser hier die liste uebergeben
		 */
		if (this.REF_ComponentMAP.get_List_EXPANDER_4_ComponentMAP() != null)
			this.REF_ComponentMAP.get_List_EXPANDER_4_ComponentMAP().set_oNavigationList(this);
		
		
		this.REF_ComponentMAP.set_oNavigationList_This_Belongs_to(this);
		
		//falls es einen module-identifier gibt, nachsehen, ob eine spaltenreihenfolge festgelegt ist
		if (this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULE != null)
		{
			try
			{
				new E2_UserSettingRowsInNavigationList().restore_ColumnList(this, this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULE);
			} 
			catch (myException e)
			{
				e.printStackTrace();
			}
		}
		
		
		//2011-06-20: automatischen formatting-agenten, der unselektierte, aber gefundene saetze markiert
		this.REF_ComponentMAP.add_oSubQueryAgent(new ownFormattingAgentHighlight_Hidden_And_found());
		
		//hier den kenner fuer Benutzer-Speicher-vorgaenge erzeugen
		this.set_AUTOMATIC_GENERATED_KENNUNG(this.generate_SaveKennungFromListHashmap());
		
		
		//2012-02-02: immer automatisch die listenlaenge laden, falls einmal gespeichert
		this.get_vectorSegmentation().set_iSegmentGroesse(
				new E2_Usersetting_SiteLength(this.get_AUTOMATIC_GENERATED_KENNUNG()).get_StoredListLengt(10));

		
		//2012-09-06: schalterwerte fuer die navigationlist aus der session/datenbank laden (betrifft den status der hervorhebung der markierten zeilen)
		@SuppressWarnings("unchecked")
		HashMap<String, String> hmValues = (HashMap<String, String>)new E2_Usersetting_ListSettings().get_Settings(this.get_AUTOMATIC_GENERATED_KENNUNG());
		if (hmValues !=null)
		{
			if (hmValues.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS)!=null)
			{
				this.set_bMarkSelectedRows(S.NN(hmValues.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS)).equals("Y"));
				this.set_bMultiSelectWithButtonsInList(S.NN(hmValues.get(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST)).equals("Y"));
				this.set_bShowInfoSelectedRowCount(S.NN(hmValues.get(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE)).equals("Y"));
				
				this.set_oColorForeMarkSelectedRows(E2_Usersetting_ListSettings.get_oColorFromListSetting(hmValues));
				
				this.set_oFontForeMarkSelectedRows(E2_Usersetting_ListSettings.get_oFontFromListSetting(hmValues));
			}
		}
		
		
		//2013-10-10: wenn eine sortierungsspeicherung hinterlegt wurde, dann hier das ordersegment eintragen
		if (this.bSaveSortStatus) {
			Vector<String>  vSaveSortVector = new E2_Usersetting_ListSortStatus(this.get_AUTOMATIC_GENERATED_KENNUNG()).READ_Vector();
			if (vSaveSortVector.size()>0) {
				this.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
				this.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields().addAll(vSaveSortVector);
			}
		}
		
		
		
		//2014-09-04: Summationsbuttons registrieren
		E2_NavigationList_ManageCalcButtons oRegister = new E2_NavigationList_ManageCalcButtons(this, this.REF_ComponentMAP);
		oRegister.DO_01_Search_and_Add_StandardCalcButtonDefs();
		oRegister.DO_02_RegisterNavigationListToComponentAddons();
		
		
		
		//2016-07-06: evtl gespeicherte benutzerdefinierte Spaltenbreiten aktivieren
		try {
			new E2_NavigationList_RestoreUserSettings_ColumnWidth(this);
			new E2_NavigationList_RestoreUserSettings_ColumnOrder(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * generiert eine (unsicher) eindeutige kennung fuer eine navigationist aus den anfanges- und endbuchstaben der 
	 * feld-keys
	 * AUFBAU: cMODULE_IDENTIFIER_OF_CONTAINING_MODULE@aus der realcomponenthashmap anfangs- und endbuchstabe der keys + laenge als zahl
	 *         wenn laenger als 200 dann nur 200 zeichen  
	 * @return
	 */
	public String generate_SaveKennungFromListHashmap()
	{
		StringBuffer cRueck = new StringBuffer();
		
		if (this.REF_ComponentMAP != null)
		{
			cRueck.append(S.NN(this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULE)+"@");
			
			Iterator<String> oIter = this.REF_ComponentMAP.get_hmRealComponents().keySet().iterator();
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				if (cKey.length()>0)
				{
					//anfangs- und endbuchstaben anhaengen
					cRueck.append(cKey.substring(0,1));
					cRueck.append(cKey.substring(cKey.length()-1,cKey.length()));
				}
			}
			cRueck.append(""+this.REF_ComponentMAP.get_hmRealComponents().size());
		}
		
		if (cRueck.length()==0)
		{
			return null;
		}
		else
		{
			String cHelp = cRueck.toString();
			if (cHelp.length()>200)
			{
				cHelp = cHelp.substring(0,200);
			}
			return cHelp;
		}
	}
	
	
	/**
	 * @return
	 * gibt die letzte markierte zeile zurueck
	 */
	public String get_cID_Unformated_Of_LastActive_Row()
	{
		String cRueck = null;
		
		Component oComponent[] = this.oDataGrid.getComponents();
		
		for (int i=0;i<oComponent.length;i++)
		{
			if (oComponent[i] instanceof E2_ButtonListMarker)
			{
				if (((E2_ButtonListMarker)oComponent[i]).get_bIsMarked())
				{
					E2_ButtonListMarker oActivMarker = (E2_ButtonListMarker)oComponent[i];
					E2_ComponentMAP oMap = oActivMarker.EXT().get_oComponentMAP();
					SQLResultMAP oResMap = oMap.get_oInternalSQLResultMAP();
					cRueck = oResMap.get_cUNFormatedROW_ID();
					break;
				}
			}
		}

		return cRueck;
	}
	
	
	
	/**
	 * Neuaufbau der Listenzeile mit der ID
	 * @param cID_Unformated
	 */
	public boolean Refresh_ComponentMAP(String cID_Unformated,String cSTATUS_MAP) throws myException
	{
		boolean bRueck = false;
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(cID_Unformated))
			{
				oMap._DO_REFRESH_COMPONENTMAP(cSTATUS_MAP,false, null);
				bRueck = true;
				
				//2012-08-31: falls die liste auf markierung steht:
				if (this.bMarkSelectedRows)
				{
					oMap.set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
				}
			}
			
		}
		return bRueck;
	}
	

	
	/**
	 * Neuaufbau der Listenzeile mit der ID
	 * @param cID_Unformated
	 */
	public boolean Refresh_ComponentMAP(Vector<String> vIDs_Unformated,String cSTATUS_MAP) throws myException
	{
		boolean bRueck = false;
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (vIDs_Unformated.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()))
			{
				oMap._DO_REFRESH_COMPONENTMAP(cSTATUS_MAP,false, null);
				bRueck = true;
				
				//2012-08-31: falls die liste auf markierung steht:
				if (this.bMarkSelectedRows)
				{
					oMap.set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
				}

				
				
			}
		}
		return bRueck;
	}

	
	
	/**
	 * Raussuchen einer componentMap aus der liste
	 * @param cID_Unformated
	 */
	public E2_ComponentMAP get_ComponentMAP(String cID_Unformated)
	{
		E2_ComponentMAP oComponentMAP = null;
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(cID_Unformated))
			{
				oComponentMAP = oMap;
				break;
			}
		}
		return oComponentMAP;
	}

	
	
	
	/**
	 * @param cID_Unformated_ToMark (markierbutton)
	 */
	public void Mark_ID_IF_IN_Page(String cID_Unformated_ToMark)
	{
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(cID_Unformated_ToMark)) {
				oMap.set_Marker(true);
			}
		}
	}
	
	
	/**
	 * 2015-11-17
	 * @param cID_Unformated_ToMark (markierbutton)
	 */
	public void Mark_ID_IF_IN_Page_not_unmark_others(String cID_Unformated_ToMark)
	{
		for (int i=0;i<this.vComponentMAPS.size();i++) {
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(cID_Unformated_ToMark)) {
				oMap.set_Marker_not_unmark_others(true);
			}
		}
	}
	
	
	
	/**
	 * 2015-11-17:martin
	 * @param v_ids_to_mark (alle gleichzeitig)
	 */
	public void Mark_ID_IF_IN_Page(Vector<String> v_ids_to_mark) {
		boolean b_first_is_marked = true;
		for (String id: v_ids_to_mark) {
			for (int i=0;i<this.vComponentMAPS.size();i++) {
				E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
				if (oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(id)) {
					if (!b_first_is_marked) {
						oMap.set_Marker(true);
						b_first_is_marked = true;
					} else {
						oMap.set_Marker_not_unmark_others(true);
					}
				}
			}
		}
	}
	
	
	
	

	/**
	 * 
	 * @param vIDs_To_Check__Unformated IDs der zeilen, die angehakt werden, alle anderen aus
	 */
	public void set_CheckBox_To_AllIdsInVector(Vector<String>  vIDs_To_Check__Unformated)
	{
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			oMap.setChecked_CheckBoxForList(vIDs_To_Check__Unformated.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()));
		}
	}
	

	

	/**
	 * @param cID_Unformated_ToCheck (checkbox)
	 */
	public void Check_ID_IF_IN_Page(String cID_Unformated_ToCheck)
	{
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(cID_Unformated_ToCheck))
			{
				oMap.setChecked_CheckBoxForList(true);
				
				//2012-08-31: falls die liste auf markierung steht:
				if (this.bMarkSelectedRows)
				{
					oMap.set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
				}
				
			}
		}
	}


	/**
	 * @param vID_Unformated_ToCheck (checkbox)
	 */
	public void Check_ID_IF_IN_Page(Vector<String> vID_Unformated_ToCheck)
	{
		for (String cID:vID_Unformated_ToCheck)
		{
			this.Check_ID_IF_IN_Page(cID);
		}
	}

	
	/**
	 * @param cBedingungVonOben
	 * baut den vector aus unformatierten ID_MainTable auf, die dann in einzelnen segmenten 
	 * angezeigt werden
	 * @throws myException
	 */
	public Vector<String> build_BASE_ID_Vector(String cBedingungVonOben,  boolean bWithDynamicWhereBlock) throws myException
	{
		//2018-02-08: saveSettings loeschen
		this.statusSaver=null;
		this.getButtRestoreSaveSetting()._setHidden();
		//2018-02-08: saveSettings loeschen
		
		
		//2015-11-18: neuer interface-einsprung fuer die vorbereitung des datasets
		if (this instanceof E2_NavigationList_dataPrepare) {
			MyE2_MessageVector mv = ((E2_NavigationList_dataPrepare)this).prepareData(ListBuildType.COMPLETE);
			if (mv != null && mv.get_bHasAlarms()) {
				String cErr = "";
				for (MyE2_Message m: mv){
					cErr = cErr+m.get_cMessage().COrig()+"\n";
				}
				throw new myException(cErr);
			} else  if (mv != null && mv.get_bIsOK()) {
				bibMSG.add_MESSAGE(mv);
			}
		}
		//ende interface-definition

		//2018-03-09: unterscheiden zwischen prep und normal
		MyDBResultSet oRS = null;
		String cQuery = this.REF_ComponentMAP.get_oSQLFieldMAP().get_CompleteSQLQueryFor_ID_VECTOR(cBedingungVonOben,bWithDynamicWhereBlock);
		if (this.REF_ComponentMAP.get_oSQLFieldMAP() instanceof SQLFieldMAPPrep) {
			oRS = ((SQLFieldMAPPrep)this.REF_ComponentMAP.get_oSQLFieldMAP()).get_CompleteSQLQueryFor_ID_VECTOR_Prep(cBedingungVonOben,bWithDynamicWhereBlock).generateResultsetPrepared(this.oDB);
		} else {
			oRS = this.oDB.OpenResultSet(cQuery);
		}
		Vector<String> vIDs = new Vector<String>();
		
		
		if (oRS.RS != null)
		{
			try
			{
				while (oRS.RS.next())
				{
					vIDs.add(oRS.RS.getString(1));
				}
			}
			catch (SQLException ex)
			{
				oRS.Close();
				throw new myException("MyE2_NavigationList:rebuild_VectorForSegmentation:Error stepping through result: "+ex.getMessage());
			}
			oRS.Close();
		}
		else
		{
			throw new myException("MyE2_NavigationList:rebuild_VectorForSegmentation:Error in Query: "+cQuery);
		}

		this.fireActionAgentsAfterBuild_BASE_ID_Vector();
		
		return vIDs;
	}
	
	
	
	public Vector<String> build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components(String cBedingungVonOben, boolean bForceRebuild) throws myException 	{
		
		Vector<String> vRueck = new Vector<String>();
		if (bForceRebuild)
		{
			vRueck.addAll(this.build_BASE_ID_Vector(cBedingungVonOben,true));
			this.vIDs_From_Selektor.removeAllElements();
			this.vIDs_From_Selektor.addAll(vRueck);
		}
		else
		{
			vRueck.addAll(this.vIDs_From_Selektor);
		}
		
		if (this.vIDs_From_Search.size() > 0)
			vRueck.retainAll(this.vIDs_From_Search);
		
		return vRueck;
		
		
	}
	
	
	
	
	//2011-09-06: weitere aufbaumethode fuer sortierung auf basis vorhandener listen 
	public Vector<String> build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components(String cBedingungVonOben, boolean bForceRebuild,  boolean bWithDynamicWhereBlock) throws myException
	{
		Vector<String> vRueck = new Vector<String>();
		if (bForceRebuild)
		{
			vRueck.addAll(this.build_BASE_ID_Vector(cBedingungVonOben,bWithDynamicWhereBlock));
			this.vIDs_From_Selektor.removeAllElements();
			this.vIDs_From_Selektor.addAll(vRueck);
		}
		else
		{
			vRueck.addAll(this.vIDs_From_Selektor);
		}
		
		if (this.vIDs_From_Search.size() > 0)
			vRueck.retainAll(this.vIDs_From_Search);
		
		return vRueck;
		
		
	}

	
	
	/**
	 * fuegt eine neue ID zu einer bestehenden liste in alle relevanten vectoren,
	 * damit der neueingegebene satz auch bis zum naechten suchvorgang vorhanden ist
	 */
	public void ADD_NEW_ID_TO_ALL_VECTORS(String cNewID_Unformated) throws myException
	{
		this.get_vActualID_Segment().add(cNewID_Unformated);
		this.get_vectorSegmentation().add(cNewID_Unformated);;	// neue ID an das gesamte segment anhaengen
		if (this.vIDs_From_Search.size()>0)
			this.vIDs_From_Search.add(cNewID_Unformated);
		
		this.vIDs_From_Selektor.add(cNewID_Unformated);
		
		
		this.BUILD_ComponentMAP_Vector_from_ActualSegment();
		this.FILL_GRID_From_InternalComponentMAPs(true, true);
		this.refresh_pageinfo_in_navigator(this.get_iActualPage());


	}
	
	

	/**
	 * fuegt einen Vector mit neuen IDs zu einer bestehenden liste in alle relevanten vectoren,
	 * damit der neueingegebene satz auch bis zum naechten suchvorgang vorhanden ist
	 */
	public void ADD_NEW_ID_TO_ALL_VECTORS(Vector<String> vNewID_Unformated) throws myException
	{
		this.get_vActualID_Segment().addAll(vNewID_Unformated);
		this.get_vectorSegmentation().addAll(vNewID_Unformated);;	// neue ID an das gesamte segment anhaengen
		if (this.vIDs_From_Search.size()>0)
			this.vIDs_From_Search.addAll(vNewID_Unformated);

		this.vIDs_From_Selektor.addAll(vNewID_Unformated);
		
		this.BUILD_ComponentMAP_Vector_from_ActualSegment();
		this.FILL_GRID_From_InternalComponentMAPs(true, true);
		this.refresh_pageinfo_in_navigator(this.get_iActualPage());
	}

	
	/**
	 * 2011-09-29
	 * 
	 * fuegt einen Vector mit neuen IDs zu einer bestehenden liste in alle relevanten vectoren,
	 * damit der neueingegebene satz auch bis zum naechten suchvorgang vorhanden ist
	 * WICHTIG: DIE NEUEN WERTE WERDEN AN DEN ANFANG DER LISTE GESETZT
	 */
	public void ADD_NEW_ID_TO_ALL_VECTORS_IN_FRONT(Vector<String> vNewID_Unformated) throws myException
	{
		this.get_vActualID_Segment().addAll(0, vNewID_Unformated);
		this.get_vectorSegmentation().addAll(0, vNewID_Unformated);;	// neue ID an das gesamte segment anhaengen
		if (this.vIDs_From_Search.size()>0)
			this.vIDs_From_Search.addAll(0, vNewID_Unformated);

		this.vIDs_From_Selektor.addAll(0, vNewID_Unformated);
		
		this.BUILD_ComponentMAP_Vector_from_ActualSegment();
		this.FILL_GRID_From_InternalComponentMAPs(true, true);
		this.refresh_pageinfo_in_navigator(this.get_iActualPage());
	}


	
    /**
     * @füllen der liste und sprung auf die erste seite 
     * @param cBedingungVonOben
     */	
	public void Fill_NavigationList(String cBedingungVonOben) throws myException
	{
		this.set_newContentVector(this.build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components(cBedingungVonOben,true));
		this.goToPage(null,0);
	}

	
    /**
     * @füllen der liste mit leerem ID-Vector
     */	
	public void Show_Empty_NavigationList() throws myException
	{
		this.set_newContentVector(new Vector<String>());
		this.goToPage(null,0);
	}


	
	
	public void _REBUILD_COMPLETE_LIST(String cBedingungVonOben) throws myException
	{
		// 2011-06-19: der vector der via suche zusaetzlich eingemischten ids
		// muss leer werden
		this.get_vIDs_Found_ButNotInSelektion().removeAllElements();

		

		
		Vector<String> vIDs = this.build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components(cBedingungVonOben,true);
		String cID_Active = this.get_cID_Unformated_Of_LastActive_Row();
		this.set_newContentVector(vIDs);
		this.gotoSiteWithID_orFirstSite(cID_Active);
	}
	
	
	/**
	 * @param MarkOnlyID (wird als einzige markiert, wenn null werden die vorigen markiert
	 * @throws myException
	 */
	public void _REBUILD_ACTUAL_SITE(String MarkOnlyID) throws myException
	{
		//2010-12-21: zusammenfuehren mit der methode REBUILD_ACTUAL_SITE(String MarkOnlyID, boolean autoOpenDaughter)
		
		this._REBUILD_ACTUAL_SITE(MarkOnlyID, false);
		
	}

	
	/**
	 * 2018-08-10: rebuild der seite wobei markierungen (rote karos) behalten werden
	 * @param idForCheckBox (wird als einzige markiert, wenn null werden die vorigen markiert
	 * @throws myException
	 */
	public E2_NavigationList _RebuildSiteAndKeepMarkers(String idForCheckBox) throws myException {
		VEK<String> vMarkers = this.getMarkedIds();
		this._REBUILD_ACTUAL_SITE(idForCheckBox, false);
		this._setMarkedIds(vMarkers);
		return this;
	}

	
	

	/**
	 * 
	 * @param MarkOnlyID
	 * @param autoOpenDaughter
	 * @throws myException
	 */
	public void _REBUILD_ACTUAL_SITE(String MarkOnlyID, boolean autoOpenDaughters) throws myException
	{
		Vector<String> vIDs = this.get_vSelectedIDs_Unformated();
		Vector<String> vIDs_OpenDaughter = new Vector<String>();
		
		if (autoOpenDaughters)
		{
			// es werden alle toechter geoeffnet
			vIDs_OpenDaughter.addAll(this.get_vActualID_Segment());
		}
		else
		{
			vIDs_OpenDaughter.addAll(this.get_vIDsWithDaughterOpen_Unformated());
		}
			
		
		this.BUILD_ComponentMAP_Vector_from_ActualSegment();
		
		// die vorher offenen wieder oeffnen
		if (vIDs_OpenDaughter.size()>0)
		{
			for (int i=0;i<this.vComponentMAPS.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
				if (oMap.get_oInternalSQLResultMAP()!=null && vIDs_OpenDaughter.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()))
					oMap.set_OPEN_DaughterObjectForList_With_ToggleButton(true);
			}
		}
		
		this.FILL_GRID_From_InternalComponentMAPs(true, true);
		if (bibALL.isEmpty(MarkOnlyID))
		{
			if (vIDs != null && vIDs.size()>0)
			{
				for (int i=0;i<vIDs.size();i++)
				{
					this.Check_ID_IF_IN_Page((String)vIDs.get(i));
				}
			}
		}
		else
		{
			this.Check_ID_IF_IN_Page(MarkOnlyID);
		}
	}

	
	
	
	
	/**
	 * 2017-01-18: neue methode zum rebuild einer seite mit gleichzeitigem offenlassen von offenen extenderns
	 */
	public E2_NavigationList _REBUILD_ACTUAL_SITE(boolean mark_old_ids, boolean keep_extended_lines_open, Vector<String> ids_to_add_to_all_vectors) throws myException {
		Vector<String> vIDs = this.get_vSelectedIDs_Unformated();
		Vector<String> vIDs_OpenDaughter = new Vector<String>();
		vIDs_OpenDaughter.addAll(this.get_vIDsWithDaughterOpen_Unformated());
		
		if (ids_to_add_to_all_vectors!=null && ids_to_add_to_all_vectors.size()>0) {
			this.get_vActualID_Segment().addAll(0, ids_to_add_to_all_vectors);
			this.get_vectorSegmentation().addAll(0, ids_to_add_to_all_vectors);;	// neue ID an das gesamte segment anhaengen
			if (this.vIDs_From_Search.size()>0)
				this.vIDs_From_Search.addAll(0, ids_to_add_to_all_vectors);
	
			this.vIDs_From_Selektor.addAll(0, ids_to_add_to_all_vectors);
		}
		
		this.BUILD_ComponentMAP_Vector_from_ActualSegment();
		
		if (keep_extended_lines_open) {
			if (vIDs_OpenDaughter.size()>0)	{
				for (int i=0;i<this.vComponentMAPS.size();i++)	{
					E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
					if (oMap.get_oInternalSQLResultMAP()!=null && vIDs_OpenDaughter.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID())) {
						oMap.set_OPEN_DaughterObjectForList_With_ToggleButton(true);
					}
				}
			}
		}
		
		this.FILL_GRID_From_InternalComponentMAPs(true, true);

		if (mark_old_ids) {
			for (String id: vIDs) {
				this.Check_ID_IF_IN_Page(id);
			}
		}
		
		
		return this;
	}

	
	
	
	
	
	/**
	 * springt auf die seite mit der uebergebenen ID, wenn diese im basis-vector vorhanden ist. sonst
	 * auf die erste seite
	 * @param cID_ActiveUNFORMATED
	 */
	public void gotoSiteWithID_orFirstSite(String cID_ActiveUNFORMATED) throws myException
	{
		int iNextSite = 0;
		
		/*
		 * dann war einer markiert
		 */
		if (!bibALL.isEmpty(cID_ActiveUNFORMATED))
		{
			vectorForSegmentation oSeg = this.get_vectorSegmentation();
			int iPos[] = oSeg.get_Segment_and_Position(cID_ActiveUNFORMATED);
			if (iPos!=null)
			{
				iNextSite = iPos[0];
			}
		}
		this.goToPage(null,iNextSite);
		
		/*
		 * jetzt, falls einer markiert war, den wieder markieren
		 * !!! WICHTIG! die markierung kann nicht mit dem offset geschehen,
		 * da bei undefinierten sortierungen ein offset aus der kompletten id-liste uebergeben wird,
		 * das bei erneuter, sortierter Abfrage des segmentes evtl nicht mehr gueltig ist 
		 * 
		 */
		this.Mark_ID_IF_IN_Page(cID_ActiveUNFORMATED);

	}
	
	
	//20190218: seite mit bestehende, segmentationVektor neu bauen (neuer name)
	
	/**
	 * rebuilds list based on actual ids in segmentationVector and starts on page 1
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @return itself
	 * @throws myException
	 */
	public E2_NavigationList _RebuildListWithActualIds() throws myException {
		this.gotoSiteWithID_orFirstSite(null);
		return this;
	}
	
	
	
	
	public boolean goToPage(Object object, int iZielseite)  throws myException
	{
		
		/*
		 * die id-liste der zielseite rausziehen
		 */
		this.vActualID_Segment = this.get_vectorSegmentation().get_vSegment(iZielseite);
		
		try
		{
			// falls irgendwelche Eventhandler genutzt werden, die vor der Anzeige bzw. dem Lesen der aktuellen Seite gebraucht werden,
			// dann werden sie hier aufgerufen. Z.B: wenn man Daten braucht, die im FormattingAgent gebraucht werden.
			fireActionAgentsBeforeListStarted();

			
			// hier werden auch (falls vorhanden) eintraege im vector
			this.vComponentMAPS_NEW.removeAllElements();
			
			Vector<String> vOldCheckedRows = this.get_vSelectedIDs_Unformated();   // letzte markierungen
			this.BUILD_ComponentMAP_Vector_from_ActualSegment();
			this.FILL_GRID_From_InternalComponentMAPs(true, true);
			this.set_SelectIDs(vOldCheckedRows);
			this.refresh_pageinfo_in_navigator(iZielseite);
			
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			throw new myException("E2_NavigationList:goToPage: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
			
		return false;
	}

	
	
	
	/**
	 * baut die liste auf mit dem actuell vorhandenen segment neu auf
	 */
	public void BUILD_ComponentMAP_Vector_from_ActualSegment() throws myException
	{
		
		
		//2015-11-18: neuer interface-einsprung fuer die vorbereitung des datasets
		if (this instanceof E2_NavigationList_dataPrepare) {
			MyE2_MessageVector mv = ((E2_NavigationList_dataPrepare)this).prepareData(ListBuildType.SITESEGMENT);
			if (mv != null && mv.get_bHasAlarms()) {
				String cErr = "";
				for (MyE2_Message m: mv){
					cErr = cErr+m.get_cMessage().COrig()+"\n";
				}
				throw new myException(cErr);
			} else  if (mv != null && mv.get_bIsOK()) {
				bibMSG.add_MESSAGE(mv);
			}
		}
		//2015-11-18: neuer interface-einsprung fuer die vorbereitung des datasets

		
		//2018-03-09: unterscheidung, ob es eine prepared-sqlfieldmap ist oder nicht
		MyDBResultSet oRS = null;
		String cQuerySegment = this.REF_ComponentMAP.get_oSQLFieldMAP().get_CompleteSQLQueryFor_SEGMENT(this.vActualID_Segment);
		if (this.REF_ComponentMAP.get_oSQLFieldMAP() instanceof SQLFieldMAPPrep) {
			oRS =  ((SQLFieldMAPPrep)this.REF_ComponentMAP.get_oSQLFieldMAP()).get_CompleteSQLQueryFor_SEGMENT_Prep(this.vActualID_Segment).generateResultset(this.oDB);
		} else {
			oRS = this.oDB.OpenResultSet(cQuerySegment);
		}
		this.vComponentMAPS.removeAllElements();
		
		
		
		if (oRS.RS != null)
		{
						
			/*
			 * alle E2_ComponentMAP - objekte bauen und fuellen
			 */
			
			try 
			{
				while (oRS.RS.next())
				{
					E2_ComponentMAP oMap = (E2_ComponentMAP)this.REF_ComponentMAP.get_Copy(null);
					oMap.set_oNavigationList_This_Belongs_to(this);
					oMap.fill_ComponentMapFromDB(oRS.RS,null, E2_ComponentMAP.STATUS_VIEW,false,new Boolean(false));
					this.vComponentMAPS.add(oMap);
					oMap.set_VectorComponentMAP_thisBelongsTo(this.vComponentMAPS);
					
					//2012-12-13: settings-collection anwenden
					oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMap);
				}
				oRS.Close();
			} 
			catch (SQLException e) 
			{
				oRS.Close();
				e.printStackTrace();
				throw new myException(this,"SQL-Error:"+e.getLocalizedMessage());
			}
			
		}
		else
		{
			DEBUG.System_println("----------> Datenbankfehler :", "");
			DEBUG.System_println("----------> Abfrage: "+cQuerySegment, "");
			throw new myException(this,"Error opening Segment: "+cQuerySegment);
		}


	}
	
	
	
	
	
	public void FILL_GRID_From_InternalComponentMAPs(boolean bShowOldRows, boolean bShowNewRows) throws myException
	{
		/*
		 * dann die tabelle fuellen (mit "normalen listenelementen und Neueingabe-Elementen)
		 */
		int iNumberColsInList = this.REF_ComponentMAP.get_iCountVisibleElementsInList();
		
		Vector<String>  vVisibleHashes = this.REF_ComponentMAP.get_vVisibleElementsInList();
		
		this.oDataGrid.removeAll();
		this.oDataGrid.setSize(iNumberColsInList);   // spaltenzahl definieren
	
		//2012-11-14: falls alle sichtbaren spalten eingeschaltet sind und die navilist so eingestellt ist, dann die breite der liste fixieren
		if (this.bSetGridWidthFixed)
		{
			Integer intBreite = this.REF_ComponentMAP.get_iWidth_sum_of_AllVisibleColumns();
			if (intBreite != null)
			{
				this.oDataGrid.setWidth(new Extent(intBreite.intValue()));
			}
			else
			{
				this.oDataGrid.setWidth(null);
			}
		}
		//ende 2012-11-14
		
		
		/*
		 * ueberschriften definieren
		 */
//		int iCount = 0;
		for (int i=0;i<vVisibleHashes.size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component) this.REF_ComponentMAP.get(vVisibleHashes.get(i));

			/*
			 * der Header-Renderer bekommt als text die nummer der komponente aus der MAP
			 * gesagt, die in der spalte icount dargestellt wird. wenn keine versteckt sind, dann
			 * ist die spalte iCount immer gleich der Text-info - Nummer
			 */
			if ((oComp).EXT().get_oColExtent() != null)
				this.oDataGrid.setColumnWidth(i,(oComp).EXT().get_oColExtent());
			
			if (this.bShowNavilistWithHeader)
			{
				//alt:
				//this.oDataGrid.add(this.build_HeaderComponent((Component)oComp));
			
				//2014-09-05: neuer wrapper um die titelkomponente
				this.oDataGrid.add(
						E2_NavigationList_ComponentHelper.wrapHeaderWithAddonKomponents(
								oComp,this.build_HeaderComponent((Component)oComp)));
			}
			

		}

		
		/*
		 * abschlusslinie
		 */
		if (this.bSeparatorBetweenLines)
		{
			Separator 	oSep = new Separator();
			Column 		oCol = new Column();
			ColumnLayoutData oColLayout = new ColumnLayoutData();
			oColLayout.setHeight(new Extent(5));
			oSep.setTopSize(new Extent(0));
			oSep.setBottomColor(new E2_ColorDDDark());
			oSep.setLayoutData(oColLayout);
			oCol.add(oSep);
			oCol.setLayoutData(new GridLayoutVARIABLE(iNumberColsInList, null, null,this.oColorBackgroundContent));
			this.oDataGrid.add(oCol);
		}


		if (! this.bMAPS_NEW_First)
		{
			/*
			 * zuerst die zeilen mit den regulaeren daten an das table-model anhaengen
			 */
			if (bShowOldRows)
			{
				this.add_ComponentMAPs_to_Table(this.vComponentMAPS,vVisibleHashes);
			}
			/*
			 * dann die zeilen mit neueingabe-elementen dazu (falls vorhanden)
			 */
			if (bShowNewRows)this.add_ComponentMAPs_to_Table(this.vComponentMAPS_NEW,vVisibleHashes);
		}
		else
		{
			/*
			 * umgekehrt
			 */
			if (bShowNewRows)
			{
				this.add_ComponentMAPs_to_Table(this.vComponentMAPS_NEW,vVisibleHashes);
			}
			
			if (bShowOldRows) this.add_ComponentMAPs_to_Table(this.vComponentMAPS,vVisibleHashes);
			
		}
		
		// actionAgents feuern, die nach dem Aufbau der Liste automatisch was ausführen sollen
		this.fireActionAgentsAfterListCompleted();
		
		
//		//2019-02-22: formatierautomatik
//		if (this.isUseComponentMapMarkerWhenPresent()) {
//			this._applyMarker();
//		}
	}
	
	
	/*
	 * ausgelagert
	 */
	private void add_ComponentMAPs_to_Table(Vector<E2_ComponentMAP> vComponentMAPs, Vector<String>  vVisibleHashes) throws myException
	{
		
		//2016-04-05: alle Extender von den set_real_rendered_component_in_list saeubern
		for (E2_ComponentMAP compmap: vComponentMAPs) {
			for (MyE2IF__Component co: compmap.values()) {
				co.EXT().set_real_rendered_component_in_list(null);
			}
		}
		//sauber
		
		int iNumberColsInList = vVisibleHashes.size();
		
		for (int i=0;i<vComponentMAPs.size();i++)
		{
			E2_ComponentMAP oE2_ComponentMap = (E2_ComponentMAP)vComponentMAPs.get(i);

			for (int k=0;k<vVisibleHashes.size();k++)
			{
				
				MyE2IF__Component 	oComp = 		(MyE2IF__Component)oE2_ComponentMap.get(vVisibleHashes.get(k));   	// komponente (Cast 1)
				Component			oCompEcho = 	(Component)oComp; 													// komponente (Cast 2)
				
				if (oCompEcho instanceof IF_Pass_Automatic_GridLayoutDataHandling) {
					//DEBUG.System_println("------------ E2_NavigationList:  NEUES Layouthandling ....");
					Component comp_real = null;
					if (oCompEcho instanceof MyE2IF__Indirect) {
						comp_real = ((MyE2IF__Indirect)oCompEcho).get_RenderedComponent();
					} else {
						comp_real = oCompEcho;
					}
					oComp.EXT().set_real_rendered_component_in_list(comp_real);
					this.oDataGrid.add_SUPER(comp_real);
					
				} else {
				
					E2_NavigationList_ComponentHelper.set_GRID_LayoutData_InList(oCompEcho);
					
					//2016-04-05: hier wird den MyE2IF__Component's ein in das EXT-objekt die real eingefuegte komponente uebergeben
					Component comp_real = new MyE2IF__IndirectHELPER(oCompEcho).get_oCompRueck();
					oComp.EXT().set_real_rendered_component_in_list(comp_real);
					//this.oDataGrid.add_SUPER(new MyE2IF__IndirectHELPER(oCompEcho).get_oCompRueck());
					this.oDataGrid.add_SUPER(comp_real);
				}
			}
			
			/*
			 * jetzt nachsehen, ob es eine ausgeklappte tochterkomponente gibt,
			 * aber nur bei gefuellten MAP, nicht bei neuen
			 */
			if (oE2_ComponentMap.get_oInternalSQLResultMAP()!=null)
			{
				if (oE2_ComponentMap.get_List_EXPANDER_4_ComponentMAP() != null)
				{
					XX_List_EXPANDER_4_ComponentMAP 	oDaughterObject = oE2_ComponentMap.get_List_EXPANDER_4_ComponentMAP();
					String 								cROW_ID = oE2_ComponentMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					
					// sicherheitsabfrage: nachsehen, ob die navigationlist, die die tochterkomponente kennt, diese hier ist
					if (oDaughterObject.get_oNavigationList()!=this)
						throw new myException("E2_NavigationList:add_ComponentMAPs_to_Table:Error: Daughter-NavigationList is not this");
					
					
					if (oDaughterObject.get_bIsOpen())
					{
						int iOffset = oDaughterObject.get_iLeftColumnOffset();
						if ((iOffset+1) > iNumberColsInList)
						{
							throw new myExceptionForUser("Darstellung ist nicht möglich, die Liste hat zu wenige Spalten !");
						}
						else
						{

							Component oComp = oDaughterObject.get_ComponentDaughter(cROW_ID);   // erster versuch, wird benutzt, wenn != null
							
							
							if (oComp!=null)          //variante 1: Eine komplette komponente wird als tochter uebergeben wird
							{
							
								// den linken offset durch einen gespannten label fuellen
								Label oLab = new Label();
								oLab.setLayoutData(new GridLayoutVARIABLE(iOffset, null, null,this.oColorBackgroundDaughterLeftSpace));
								this.oDataGrid.add(oLab);
	
								// die tochter ueber den rest verteilen
								int iColSpan = iNumberColsInList-iOffset;
								if (oComp.getLayoutData()==null)
									oComp.setLayoutData(new GridLayoutVARIABLE(iColSpan, null, null,this.oColorBackgroundDaughter));
								else
									((GridLayoutData)oComp.getLayoutData()).setColumnSpan(iColSpan);
								
								this.oDataGrid.add(oComp);
							}
							else                        //variante 2: eine arraylist aus hashmaps aus komponenten wird uebergeben und dem gleichnamigen aus der liste
								                        //zugeordnet
							{
								ArrayList<HashMap<String,Component>> hmArray = oDaughterObject.get_ComponentListDaughter(cROW_ID);
								if (hmArray != null && hmArray.size()>0)
								{
									//den einrueckblock definieren mit Columnspan und rowspan ueber die ganze hoehe
									Label oLab = new Label("");
									oLab.setLayoutData(new GridLayoutVARIABLE(iOffset, null, new Integer(hmArray.size()),this.oColorBackgroundDaughterLeftSpace));
									this.oDataGrid.add_SUPER(oLab);

									for (int size=0;size<hmArray.size();size++)
									{
										HashMap<String, Component> hmZeile = hmArray.get(size);
										
										//hier muss die einrueckung ueber die n ersten komponenten der ComponentMap definiert werden
										for (int l=iOffset;l<vVisibleHashes.size();l++)
										{
											if (hmZeile.get(vVisibleHashes.get(l))==null)
											{
												Label oLab2 = new Label("");
												oLab2.setLayoutData(new GridLayoutVARIABLE(1, null, null,this.oColorBackgroundDaughter));
												this.oDataGrid.add_SUPER(oLab2);
											}
											else
											{
												
												Component  		oRowComp = hmZeile.get(vVisibleHashes.get(l));
												GridLayoutData  oGL = new GridLayoutData(); 
												if (oRowComp.getLayoutData() instanceof GridLayoutData)
												{
													GridLayoutData  oGL_OLD =  (GridLayoutData)oRowComp.getLayoutData();
													
													oGL.setAlignment(((GridLayoutData)oRowComp.getLayoutData()).getAlignment());
													oGL.setBackgroundImage(((GridLayoutData)oRowComp.getLayoutData()).getBackgroundImage());
													oGL.setInsets(((GridLayoutData)oRowComp.getLayoutData()).getInsets());
													//2011-12-12: nur wenn keine farbe vorhanden, dann die hintergrundfarbe vergeben
													if (oGL_OLD==null || oGL_OLD.getBackground()==null)
													{
														oGL.setBackground(this.oColorBackgroundDaughter);
													}
													else
													{
														if (oGL_OLD!=null && oGL_OLD.getBackground()!=null)
														{
															oGL.setBackground(oGL_OLD.getBackground());
														}
														else
														{
															oGL.setBackground(this.oColorBackgroundDaughter);
														}
													}
												}
												else
												{
													oGL = new GridLayoutVARIABLE(1, null, null,this.oColorBackgroundDaughter);
												}
												oRowComp.setLayoutData(oGL);
												this.oDataGrid.add_SUPER(oRowComp);
											}
										}
									}
								}
								else
								{
									throw new myException(this,"Existing XX_List_EXPANDER_4_ComponentMAP MUST give back an Daughtercomponent !");
								}
							}
						}
					}
				}
			}
			
			/*
			 * abschlusslinie
			 */
			if (this.bSeparatorBetweenLines)
			{
				Separator 	oSep = new Separator();
				Column 		oCol = new Column();
				ColumnLayoutData oColLayout = new ColumnLayoutData();
				oColLayout.setHeight(new Extent(5));
				oSep.setTopSize(new Extent(0));
				oSep.setBottomColor(new E2_ColorDDDark());
				oSep.setLayoutData(oColLayout);
				oCol.add(oSep);
				oCol.setLayoutData(new GridLayoutVARIABLE(iNumberColsInList, null, null,this.oColorBackgroundContent));
				this.oDataGrid.add(oCol);
			}
		}
	}
	

	
	

	
	
	
	
	
	
	
	/**
	 * @return 
	 * Vector mit unformatierten Row-IDs der gerade selektierten rows
	 */
	public Vector<String> get_vSelectedIDs_Unformated()
	{
		Vector<String> vRueck = new Vector<String>();
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			if (((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_bHasChecked_CheckBoxForList())
				vRueck.add(((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		}
		
		return vRueck;
	}

	
	/**
	 * 2013-05-13: martin: moeglichkeit, bei listen aus einer zeile, sich den select-schalter zu sparen
	 * @return 
	 * Vector mit unformatierten Row-IDs der gerade selektierten rows, falls keine Selektiert ist
	 * und in der Liste nur einer existiert, dann wird der automatisch aktiviert
	 */
	public Vector<String> get_vSelectedIDs_Unformated_Select_the_one_and_only()
	{
		Vector<String> vRueck = new Vector<String>();
		for (int i=0;i<this.vComponentMAPS.size();i++) 	{
			if (((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_bHasChecked_CheckBoxForList()) {
				vRueck.add(((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			}
		}

		if (vRueck.size()==0 && this.vComponentMAPS.size()==1) {
			this.vComponentMAPS.get(0).setChecked_CheckBoxForList(true);
			vRueck.addAll(this.get_vSelectedIDs_Unformated());
		}
		
		return vRueck;
	}

	
	
	

	/**
	 * @return 
	 * Vector mit unformatierten Row-IDs der gerade selektierten rows
	 */
	public Vector<E2_ComponentMAP> get_vSelected_ComponentMaps()
	{
		Vector<E2_ComponentMAP> vRueck = new Vector<E2_ComponentMAP>();
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			if (((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_bHasChecked_CheckBoxForList())
				vRueck.add(this.vComponentMAPS.get(i));
		}
		
		return vRueck;
	}

	

	
	
	/**
	 * @return 
	 * Vector mit geoeffneten toechtern
	 */
	public Vector<String> get_vIDsWithDaughterOpen_Unformated()
	{
		Vector<String> vRueck = new Vector<String>();
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			if (((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_List_EXPANDER_4_ComponentMAP()!=null)
				if (((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
					vRueck.add(((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		}
		
		return vRueck;
	}

	

	/**
	 * @return 
	 * Vector mit allen Row-IDs der gerade angezeigten seite
	 */
	public Vector<String> get_AllVisibleIDs_Unformated()
	{
		Vector<String> vRueck = new Vector<String>();
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			vRueck.add(((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		}
		
		return vRueck;
	}


	
	/**
	 * @param vIDsToSelect
	 * uebernimmt einen vector mit unformatierten IDs 
	 * und selectiert diese, wenn er sie in der 
	 * aktuellen anzeige findet
	 * 
	 */
	public void set_SelectIDs(Vector<String> vIDsToSelect)
	{
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			String cID = ((E2_ComponentMAP)this.vComponentMAPS.get(i)).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			if (vIDsToSelect.contains(cID))
			{
				((E2_ComponentMAP)this.vComponentMAPS.get(i)).setChecked_CheckBoxForList(true);
				
				//2012-08-31: falls die liste auf markierung steht:
				if (this.bMarkSelectedRows)
				{
					this.vComponentMAPS.get(i).set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
				}
			}
		}
		
	}
	
	


	/**
	 * @param bPrepareContentForNew
	 * @param bMakeMapSettingBeforeNew
	 * @param bSetEnabledForEdit
	 * @throws myException
	 */
	public void add_Row_MAP_FOR_NEW_INPUT(boolean bPrepareContentForNew, boolean bMakeMapSettingBeforeNew, boolean bSetEnabledForEdit) throws myException
	{
		E2_ComponentMAP oMapNew = (E2_ComponentMAP)this.REF_ComponentMAP.get_Copy(null);
		if (bPrepareContentForNew) 		oMapNew.PrepareMaskContentForNew();
		if (bMakeMapSettingBeforeNew) 	oMapNew.do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_NEW_EMPTY);
		if (bSetEnabledForEdit)     	oMapNew.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_NEW_EMPTY);
		
		//2012-12-13: settings-collection anwenden
		oMapNew.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMapNew);
		
		this.vComponentMAPS_NEW.add(oMapNew);
	}
	
	
	

	public void changeSettingsForNewSegmentSize() throws myException
	{
		String cID_Active = this.get_cID_Unformated_Of_LastActive_Row();
		this.gotoSiteWithID_orFirstSite(cID_Active);
	}


	
	private Component build_HeaderComponent(Component oComp) throws myException
	{
		
		try
		{
			
			/*
			 * falls schon eine titelkomponente vorhanden ist, dann diese zurueckgeben, damit werden 
			 * die ueberschriften/sortbuttons nur einmal erzeugt. Auch ist es damit moeglich, einer komponenten aus der
			 * rufenden einheit bereits eine komponente fuer die titelzeile mitzugeben 
			 */
			if (((MyE2IF__Component)oComp).EXT().get_oCompTitleInList() != null)
			{
				Component oCompRueck = ((MyE2IF__Component)oComp).EXT().get_oCompTitleInList();
				
				E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oCompRueck,oComp);
				
				E2_NavigationList_ComponentHelper.pruefe_und_setze_Header_style_aus_EXT(oComp,oCompRueck);
				
				return (oCompRueck);
			}
			
			
			if (oComp instanceof XX_CheckBoxForList)
			{
				Component oRueck  = null;
				
				
				//2012-09-05: checkbox4list hat ihren eigenen header-generator
				oRueck =(Component)((XX_CheckBoxForList)oComp).get_TitleComponent4Checkbox(this);
				if (oRueck==null)
				{
					oRueck = (Component)new MyE2_Label("-");
				}
					
				((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
				
				E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oRueck,oComp);
				
				
				E2_NavigationList_ComponentHelper.pruefe_und_setze_Header_style_aus_EXT(oComp,oRueck);
				return oRueck;
			}
			
			
			if (oComp instanceof MyE2IF__ComponentContainer)
			{
				Component ocompRueck = (Component)((MyE2IF__ComponentContainer)oComp).get_ListHeaderComponent(this);
				E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(ocompRueck,oComp);
				
				E2_NavigationList_ComponentHelper.pruefe_und_setze_Header_style_aus_EXT(oComp,ocompRueck);

				return ocompRueck;
			}
			else
			{
			
				if (oComp instanceof MyE2IF__DB_Component)
				{
					Component oRueck = E2_NavigationList_ComponentHelper.build_HeaderComponent_helper(oComp,this);
					
					E2_NavigationList_ComponentHelper.set_ToolTipsToHeaderComponent(oComp, oRueck);
					
					E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oRueck,oComp);

					((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
					return oRueck;
				}
				else
				{
					MyString cHelp = null;
					
					if (((MyE2IF__Component) oComp).EXT().get_cList_or_Mask_Titel() != null)
					{
						cHelp = ((MyE2IF__Component) oComp).EXT().get_cList_or_Mask_Titel();
					}
					else
					{
						cHelp = new MyString(" ");
					}

					
					//20171017: lineWrap-tag einfuegen (neuer code sicherheitshalber in try-catch-block
					Component oRueck=new MyE2_Label(cHelp);
					try {
						oRueck = new MyE2_Label(cHelp,((MyE2IF__Component) oComp).EXT().get_bLineWrapListHeader());
					} catch (Exception e) {
						e.printStackTrace();
					}

					E2_NavigationList_ComponentHelper.set_ToolTipsToHeaderComponent(oComp, oRueck);
					
					E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oRueck,oComp);

					((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
					return oRueck;
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return new MyE2_Label("@@@@@@@@@ERROR@@@@@@@@@");
		}

	}
	
	

	
	
	public E2_ComponentMAP 					get_oComponentMAP__REF()				{		return REF_ComponentMAP;	}
	public Vector<E2_ComponentMAP> 			get_vComponentMAPS()					{		return vComponentMAPS;	}
	public Vector<E2_ComponentMAP> 			get_vComponentMAPS_NEW()				{		return this.vComponentMAPS_NEW;	}
	public E2_NavigationGrid	 			get_oDataGrid()							{		return oDataGrid;	}
	public Vector<String>					get_vActualID_Segment()					{		return this.vActualID_Segment;	}
	public Vector<String> 					get_vIDs_From_Search()					{		return vIDs_From_Search;	}
	public Vector<String> 					get_vIDs_From_Selector()				{		return vIDs_From_Selektor;	}



	public boolean 				get_bSeparatorBetweenLines()			{		return bSeparatorBetweenLines;	}
	public void 				set_bSeparatorBetweenLines(boolean separatorBetweenLines)	{		bSeparatorBetweenLines = separatorBetweenLines;	}
	
	public boolean 				get_bSortIsAllowed()					{			return bSortIsAllowed;	}
	public void 				set_bSortIsAllowed(boolean sortIsAllowed){		bSortIsAllowed = sortIsAllowed;	}
	
	public boolean 				get_bMAPS_NEW_First()						{		return bMAPS_NEW_First;	}
	public void 				set_bMAPS_NEW_First(boolean first)			{		bMAPS_NEW_First = first;	}

	public boolean 				get_bSortViaCompleteQuery()					{		return bSortViaCompleteQuery;	}
	public void 				set_bSortViaCompleteQuery(boolean b_SortViaCompleteQuery)		{		this.bSortViaCompleteQuery = b_SortViaCompleteQuery;	}



	/**
	 * @param vVectorUnformatedID
	 * sucht die liste durch , und setzt die daughter-objects auf open/closed, die 
	 * im uebergebenen Vector enthalten sind
	 */
	public void activate_DaughterObjects(Vector<String> vVectorUnformatedID, boolean bSetOpen) throws myException
	{
		if (this.REF_ComponentMAP.get_List_EXPANDER_4_ComponentMAP() == null)    // wenn es keinen daughter-Builder gibt, dann zurueck
			return;
		
		for (int i=0;i<this.vComponentMAPS.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.vComponentMAPS.get(i);
			if (!vVectorUnformatedID.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()))
				continue;
			
			
			if (bSetOpen)
			{
				if (!oMap.get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
				{
					oMap.get_List_EXPANDER_4_ComponentMAP().set_bIsOpen(true);
				}
			}
			else
			{
				if (oMap.get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
				{
					oMap.get_List_EXPANDER_4_ComponentMAP().set_bIsOpen(false);
				}
			}
			this.FILL_GRID_From_InternalComponentMAPs(true, true);
		}
	}
	
	
	

	
	/**
	 * Reset aller vorhandenen sortbuttons in der liste(pfeil hoch/runter weg)
	 */
	public void ResetSortButtons()
	{
		/*
		 * die tabelle nach anderen sortbuttons durchsuchen und diese resetten,
		 * es koennen auch kombinationen von titelbuttons in der titelzeile stehen
		 */
		
		Vector<MyE2IF__Component> vRealComponents = this.REF_ComponentMAP.get_REAL_ComponentVector();
		for (int i=0;i<vRealComponents.size();i++)
		{
			if (vRealComponents.get(i) instanceof MyE2IF__Component)
			{
				MyE2IF__Component oComp = (MyE2IF__Component)vRealComponents.get(i);
				if (oComp.EXT().get_oCompTitleInList()!= null && oComp.EXT().get_oCompTitleInList() instanceof E2_ButtonListSorter)
				{
					((E2_ButtonListSorter)oComp.EXT().get_oCompTitleInList()).Reset();
				} else if (oComp.EXT().get_oCompTitleInList()!= null && oComp.EXT().get_oCompTitleInList() instanceof E2_ButtonListSorterNG) {
					//2019-04-11: Martin: hier auch die neuen sorter beruecksichtigen
					try {
						((E2_ButtonListSorterNG)oComp.EXT().get_oCompTitleInList()).Reset();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				
			}
		}
		
	}
	
	
	
	
	private class GridLayoutVARIABLE extends GridLayoutData
	{

		public GridLayoutVARIABLE(int icolSpan, Alignment oAlign, Integer intRowSpan, Color oColorBackground)
		{
			super();
			if (oAlign == null)
				this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			else
				this.setAlignment(oAlign);
			
			this.setColumnSpan(icolSpan);
			if (intRowSpan != null && intRowSpan.intValue()>1)
				this.setRowSpan(intRowSpan.intValue());
			
			
			this.setInsets(E2_INSETS.I_0_0_0_10);
			
			this.setBackground(oColorBackground);
		}
		
	}




	public Color get_oColorBackgroundContent() 								{	return oColorBackgroundContent;	}
	public void set_oColorBackgroundContent(Color colorBackgroundContent)	{	oColorBackgroundContent = colorBackgroundContent;	}
	public Color get_oColorBackgroundDaughter()								{	return oColorBackgroundDaughter;	}
	public void set_oColorBackgroundDaughter(Color colorBackgroundDaughter)	{	oColorBackgroundDaughter = colorBackgroundDaughter;	}
	public Color get_oColorBackgroundDaughterLeftSpace()					{	return oColorBackgroundDaughterLeftSpace;	}
	public void set_oColorBackgroundDaughterLeftSpace(Color colorBackgroundDaughterLeftSpace)	{	oColorBackgroundDaughterLeftSpace = colorBackgroundDaughterLeftSpace;	}
	public Color get_oColorBackgroundHeader()								{	return oColorBackgroundHeader;	}
	public void set_oColorBackgroundHeader(Color colorBackgroundHeader)		{	oColorBackgroundHeader = colorBackgroundHeader;	}

	
	

	public void RefreshList() throws myException
	{
		this._REBUILD_COMPLETE_LIST("");
	}



	//recursive suche in der liste nach MyE2_Label - objekten
	public Vector<MyE2_Label>   get_vLabels()
	{
		Vector<MyE2_Label> vRueck = new Vector<MyE2_Label>();
		
		E2_RecursiveSearch_Component oSeachLabels = new E2_RecursiveSearch_Component(this,bibALL.get_Vector(MyE2_Label.class.getName()),new Vector<String>()) ;
		
		for (Component oComp:oSeachLabels.get_vAllComponents())
		{
			vRueck.add((MyE2_Label)oComp);
		}
		
		return vRueck;
	}
	


	public String get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE()
	{
		return cMODULE_IDENTIFIER_OF_CONTAINING_MODULE;
	}



	public boolean get_bShowNavilistWithHeader()
	{
		return bShowNavilistWithHeader;
	}



	public void set_bShowNavilistWithHeader(boolean showNavilistWithHeader)
	{
		bShowNavilistWithHeader = showNavilistWithHeader;
	}
	
	

	//2011-06-20: einblenden nicht selektierte aber gefundene
	public Vector<String> get_vIDs_Found_ButNotInSelektion()
	{
		return vIDs_Found_ButNotInSelektion;
	}

	/*
	 * 2011-06-20 subqueryagent bekommt jede E2_Componentmap: wenn ein Datensatz
	 * im Vector vIDs_Found_ButNotInSelektion vorhanden ist
	 */
	private class ownFormattingAgentHighlight_Hidden_And_found extends 	XX_ComponentMAP_SubqueryAGENT
	{

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)
				throws myException
		{
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP,  SQLResultMAP oUsedResultMAP) throws myException
		{
			GridLayoutData glHighlight = new GridLayoutData();
			glHighlight.setBackground(	new E2_ColorHelpBackground());
			glHighlight.setAlignment(	new Alignment(Alignment.LEFT,Alignment.TOP));
			glHighlight.setInsets(E2_INSETS.I_3_1_6_1);
			
			if (E2_NavigationList.this.vIDs_Found_ButNotInSelektion.size()>0)
			{
				if (oMAP.get_oInternalSQLResultMAP()!=null)
				{
					if (E2_NavigationList.this.vIDs_Found_ButNotInSelektion.contains(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()))
					{
						Iterator<MyE2IF__Component> oIter = oMAP.get_hmRealComponents().values().iterator();
						while (oIter.hasNext())
						{
							Component oComp = (Component)oIter.next();
							if (oComp instanceof E2_CheckBoxForList)
							{
								((E2_CheckBoxForList) oComp).EXT().set_oLayout_ListElement(glHighlight);
							}
						}
					}
				}
			}
		}
	}



	
	
	//2012-08-30: neue funktionen fuer die multiselektion aus listen
	public void ShowMessageWithInfoAboutSelectedLinesAndMarkSelectedLines()
	{
		
		if (this.bShowInfoSelectedRowCount)
		{
			int iCount = this.get_vSelectedIDs_Unformated().size();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Sie haben ",true,""+iCount,false,iCount==1?" Zeile ausgewählt!":" Zeilen ausgewählt!",true)));
		}

		if (this.bMarkSelectedRows)
		{
			//jetzt alle componentmaps durchsuchen und bei den selektierten einen anderen hintergrund suchen
			for (E2_ComponentMAP oMap: this.vComponentMAPS)
			{
				oMap.set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
			}
		}
	}

	
	
	public boolean get_bMultiSelectWithButtonsInList()
	{
		return bMultiSelectWithButtonsInList;
	}

	public void set_bMultiSelectWithButtonsInList(boolean MultiSelectWithButtonsInList)
	{
		this.bMultiSelectWithButtonsInList = MultiSelectWithButtonsInList;
	}

	public boolean get_bMarkSelectedRows()
	{
		return bMarkSelectedRows;
	}

	public void set_bMarkSelectedRows(boolean MarkSelectedRows)
	{
		this.bMarkSelectedRows = MarkSelectedRows;
	}

	public boolean get_bShowInfoSelectedRowCount()
	{
		return this.bShowInfoSelectedRowCount;
	}

	public void set_bShowInfoSelectedRowCount(boolean ShowInfoSelectedRowCount)
	{
		this.bShowInfoSelectedRowCount = ShowInfoSelectedRowCount;
	}

	public Color get_oColorForeMarkSelectedRows()
	{
		return oColorForeMarkSelectedRows;
	}

	public void set_oColorForeMarkSelectedRows(Color Color4ForegroundMark)
	{
		this.oColorForeMarkSelectedRows = Color4ForegroundMark;
	}

	public Font get_oFontForeMarkSelectedRows()
	{
		return this.oFontForeMarkSelectedRows;
	}

	public void set_oFontForeMarkSelectedRows(Font fontForeMarkSelectedRows)
	{
		this.oFontForeMarkSelectedRows = fontForeMarkSelectedRows;
	}

	public boolean get_bSetGridWidthFixed()
	{
		return bSetGridWidthFixed;
	}

	public void set_bSetGridWidthFixed(boolean bSetGridWidthFixed)
	{
		this.bSetGridWidthFixed = bSetGridWidthFixed;
	}
	
	//2013-10-10: die optionale speicherung der Sortierdefinition hinterlegen
	public boolean get_bSaveSortStatus() {
		return this.bSaveSortStatus;
	}

	//2013-10-10: die optionale speicherung der Sortierdefinition hinterlegen
	public void set_bSaveSortStatus(boolean saveSortStatus) {
		this.bSaveSortStatus = saveSortStatus;
	}

	public boolean get_bStandardSortButtonsLineWrap() {
		return bStandardSortButtonsLineWrap;
	}

	public void set_bStandardSortButtonsLineWrap(boolean standardSortButtonsLineWrap) {
		this.bStandardSortButtonsLineWrap = standardSortButtonsLineWrap;
	}

	

	/**
	 * 
	 * @param cHASH
	 * @return s null, wenn key not found else True or False
	 */
	public Boolean get_bIsVisible(String cHASH) {
		if (this.get_oComponentMAP__REF().get(cHASH)!=null) {
			MyE2IF__Component oComponent = (MyE2IF__Component)this.get_oComponentMAP__REF().get(cHASH) ;
			return new Boolean(oComponent.EXT().get_bIsVisibleInList());
		} else {
			return null;
		}
	}

	@Override
	public String get_STAMP_INFO() throws myException {
		String cInfo = S.NN(this.get_AUTOMATIC_GENERATED_KENNUNG());
		
		Vector<String> vID_Selected = this.get_vSelectedIDs_Unformated();
		if (vID_Selected.size()>0) {
			cInfo += bibALL.Concatenate(vID_Selected, "/", "/");
		}
		
		return cInfo;
	}

	/**
	 * 2015-05-06: martin
	 * @param oDBToolBox
	 */
	public void set_oDBToolBox(MyDBToolBox oDBToolBox) {
		this.oDB = oDBToolBox;
	}

	
	/**
	 * 20170831: martin: getter hinzugefuegt
	 * @return
	 */
	public Vector<XX_ActionAgent> getvActionAgentsAfterListGeneration() {
		return vActionAgentsAfterListGeneration;
	}

	public Vector<XX_ActionAgent> getvActionAgentsBeforeListGeneration() {
		return vActionAgentsBeforeListGeneration;
	}

	public Vector<XX_ActionAgent> getvActionAgents_AfterContentVectorIsSet() {
		return vActionAgents_AfterContentVectorIsSet;
	}

	
	/**
	 * 2018-02-08: martin status einer liste speichern
	 * 
	 * @return
	 */
	public E2_NaviListStatusSaver getStatusSaver() {
		return statusSaver;
	}

	/**
	 * 2018-02-08: martin status einer liste speichern
	 */
	public void setStatusSaver(E2_NaviListStatusSaver saver) {
		this.statusSaver=saver;
	}

	
	/**
	 * 2018-02-08: martin status einer liste speichern
	 */
	public void saveStatus(MyE2_MessageVector mv) throws myException {
		if (this.statusSaver != null) {
			if (mv==null) {
				throw new myException(this, "Status is saved !!");
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Die Liste wurde bereits zwischengespeichert !")));
			}
		} else {
			this.statusSaver=new E2_NaviListStatusSaver(this);
			this.getButtRestoreSaveSetting()._setVisible();
		}
	}

	/**
	 * 2018-02-08: martin status einer liste speichern
	 */
	public void restoreStatus() throws myException {
		if (this.statusSaver == null) {
			throw new myException(this, "No Status was saved !!");
		} else {
			this.get_vectorSegmentation().clear();
			this.get_vectorSegmentation().addAll(this.statusSaver.getCopyOfSegmentationIds());
			this.getButtRestoreSaveSetting()._setHidden();
			
			this.goToPage(null, this.statusSaver.getActualSiteNumber());
			
			//2019-02-18-ebenfalls auf farbmarkierungen achten
			try {
				for (String s: this.get_vSelectedIDs_Unformated()) {
					E2_ComponentMAP map = this.getMap(s);
					if (map!=null) {
						map.set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	
	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param idUnformated
	 * @return s map with idUnformated or null, when not in visible maps
	 */
	public E2_ComponentMAP getMap(String idUnformated) {
		for (E2_ComponentMAP m: this.vComponentMAPS) {
			if (m.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(idUnformated)) {
				return m;
			}
		}
		return null;
	}
	
	
	/**
	 * 2018-08-10: 
	 * sucht alle markierten (Rotes Karo) zeilen-IDs raus
	 * @return
	 */
	public VEK<String> getMarkedIds() {
		VEK<String>  v=new VEK<>();
		for (E2_ComponentMAP m: this.get_vComponentMAPS()) {
			for (MyE2IF__Component c: m.get_hmRealComponents().values()) {
				if (c instanceof E2_ButtonListMarker) {
					if ( ((E2_ButtonListMarker)c).get_bIsMarked()) {
						v._a(m.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
					}
				}
			}
		}
		return v;
	}
	
	/**
	 * 2018-08-10: 
	 * markiert die uebergebenen ids mit dem roten Karo
	 * @return
	 */
	public E2_NavigationList _setMarkedIds(VEK<String> v) {
		for (E2_ComponentMAP m: this.get_vComponentMAPS()) {
			for (MyE2IF__Component c: m.get_hmRealComponents().values()) {
				if (c instanceof E2_ButtonListMarker) {
					if (v.contains(m.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID())) {
						((E2_ButtonListMarker)c).set_bIsMarked(true);
					}
				}
			}
		}
		return this;
	}
	
	/**
	 * 2018-08-10: 
	 * alle marker entfernen
	 * @return
	 */
	public E2_NavigationList _clearAllMarkers() {
		for (E2_ComponentMAP m: this.get_vComponentMAPS()) {
			for (MyE2IF__Component c: m.get_hmRealComponents().values()) {
				if (c instanceof E2_ButtonListMarker) {
					((E2_ButtonListMarker)c).set_bIsMarked(false);
				}
			}
		}
		return this;
	}
	


	
	/**
	 * 20190218: martin
	 * experimentelle funktion zur verbreiterung der gesamt liste, wenn die spaltenbreiten geaendert werden,
	 * da sonst die aenderungen in spaltenbreiten sich gegenseitig wieder nullen 
	 * kommt in der Klasse E2_NavigationList_RestoreUserSettings_ColumnWidth zum tragen
	 */
	private boolean extendWidthWithSumOfAllRowWidth = false;

	public boolean isExtendWidthWithSumOfAllRowWidth() {
		return extendWidthWithSumOfAllRowWidth;
	}

	public void setExtendWidthWithSumOfAllRowWidth(boolean p_extendWidthWithSumOfAllRowWidth) {
		this.extendWidthWithSumOfAllRowWidth = p_extendWidthWithSumOfAllRowWidth;
	}

	
	
	
	/**
	 * marks all selected rows with forecolor and fore-font
	 * @author martin
	 * @date 19.02.2019
	 *
	 * @return
	 */
	public E2_NavigationList _applyMarker() {
		for (E2_ComponentMAP map: this.get_vComponentMAPS()) {
			map.set_ZeileMarkOrUnmark(this.oColorForeMarkSelectedRows, this.oFontForeMarkSelectedRows);
		}
		return this;
	}

	
//	/**
//	 * damit kann nach jedem seitenaufbau automatisch ein zeilenmarker aktiv werden
//	 * @author martin
//	 * @date 22.02.2019
//	 *
//	 * @return
//	 */
//	public boolean isUseComponentMapMarkerWhenPresent() {
//		return useComponentMapMarkerWhenPresent;
//	}
//
//	public E2_NavigationList _setUseComponentMapMarkerWhenPresent(boolean p_useComponentMapMarkerWhenPresent) {
//		this.useComponentMapMarkerWhenPresent = p_useComponentMapMarkerWhenPresent;
//		return this;
//	}
	
}
