package panter.gmbh.Echo2.Container;

import java.util.ArrayList;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.event.WindowPaneEvent;
import nextapp.echo2.app.event.WindowPaneListener;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageGrid_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_IF_Component;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingTabReihenfolge;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingWindowSize;
import panter.gmbh.Echo2.components.IF_GetInBorder;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class E2_BasicModuleContainer extends MyE2_Column implements WindowPaneListener, IF_MessagePanel,IF_GetInBorder
{
	
	
	public static String WINDOW_STATUS_CLOSE_WITH_CANCEL = 	"WINDOW_STATUS_CLOSE_WITH_CANCEL";
	public static String WINDOW_STATUS_CLOSE_WITH_SAVE = 	"WINDOW_STATUS_CLOSE_WITH_SAVE";
	
	/*
	 * Komponente hat die moeglichkeit, sich selbst in ein popup-fenster einzublenden.
	 * Das Fenster hat die Ausmasse wie unten dargestellt:
	 * die position des einzublendenden fensters muss ebenfalls vorliegen
	 */
	private Extent					oExtLeftPos = null;					//vorgabe = zentriert
	private Extent					oExtTopPos = null;					//vorgabe = zentriert
	private Extent					oExtWidth = new Extent(1000);
	private Extent					oExtHeight = new Extent(700);
	
	//minimale weiten und hoehen, koennen dafuer sorgen, dass buttons nicht verschwinden
	private Extent 					oExtMINIMALWidth = null;
	private Extent 					oExtMINIMALHeight = null;
	
	
	private Extent					oExtSplitPosition = null;
	private String 					MODUL_IDENTIFIER = null;

	
	//falls ein dialogfenster aufgemacht wird, werden evtl. rechte usw. des rufenden containers gezogen (weil ein dialog keine eigene MODUL_IDENTIFIER hat)
	private String 					MODUL_IDENIFIER_OF_CALLING_BasicModuleContainer = null;
	
	/*
	 * 
	 * Message-Row, ist in jedem modul vorhanden
	 */
	private MyE2_Grid	 			oGridTopLineButtonsAndMessages= new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid	 			oGridForZusatzKomponenten = 	new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	
	
	/*
	 * das fenster, wo die maske dargestellt wird
	 * und der Stil, der fuer das Popup-Window verwendet wird
	 */
	private MyE2_WindowPane			oWindowPane		= null;
	private MutableStyle 			oStyleForWindowPane = MyE2_WindowPane.STYLE_WINDOW_STANDARD();
	
	
	
	
	/*
	 * unterstes contentpane, darin liegt entweder das einzelne oContentPaneOfWindow
	 * oder das splitpane (falls das fenster gesplittet dargestellt wird
	 */
	private E2_ContentPane			oContentPaneAussen = new E2_ContentPane(true);
	
	
	
	/*
	 * wird vor dem popup-vorgang neu erzeugt und beim schliessen zerstoert
	 */
	private E2_ContentPane			oContentPaneInnen = new E2_ContentPane(true);
	
	/*
	 * falls es ein splitpane-fenster wird
	 */
	private E2_ContentPane			oContentPaneOfWindowButtons = new E2_ContentPane(true);
	private SplitPane				oSplitPane	= new SplitPane(SplitPane.ORIENTATION_VERTICAL);
	
	
		
	/*
	 * button als einleitung zu der infozeile am oberen ran, wenn geklickt wird, dann 
	 * werden die messages angezeigt
	 */
	private MyE2_Button 			oInfoButton = new MyE2_Button(new MyE2_String("Info >"));
	
	
	
	// einstellungen fuer fenster werden in einem popup-menue zusammengefasst
	private PopDown_Settings       	oPopUpSettings = new PopDown_Settings();
	
	// falls das fenster zusatzinfos fuer die benutzerdefinierten einstellungen haben soll, ausser 
    // des klassennamens der E2_BasicModuleContainers, dann kann der hier hinterlegt werden
	// damit werden unterschiedliche varianten innerhalb einesprogrammteils auch unterschieden
	private String  				cADDON_TO_CLASSNAME = null;
	

	
	/*
	 * boolean, der erst dann true ist, wenn das maskenfenster definitiv dargestellt wurde.
	 * Kann beim mehrfach-verarbeitungen dafuer sorgen, dass der befehl userClose von aussen
	 * nur dann aktiv wird, wenn die maske bereits einmal gerendert wurde, also wirklich steht 
	 */
	private boolean 				bPopUpWasShown =false;

	
	/*
	 * ein vector mit componenten, die das interface E2IF_BasicModuleContainer_ADD_ON_Component erfuellen,
	 * damit koennen links neben dem messagepanel componenten eingeblendet werden
	 */
	private Vector<IF_BasicModuleContainer_ADD_ON_Component> 		vAddOnComponents = new Vector<IF_BasicModuleContainer_ADD_ON_Component>();
	

	private Separator 				oSeparator	= new Separator();
	
	
	
	/*
	 * message-anzeigebereich ein/ausgeschaltet
	 */
	private boolean  				bMessageBereichEin = true;
	
	private String      			cWINDOW_CLOSE_STATUS = E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL;            // damit kann die die ueberschreibende schliessmethode gesteuert werden 

	
	/*
	 * hier wird die ganze "popup-linie" mitgeschrieben
	 */
	private Vector<E2_BasicModuleContainer>    				vBasicContainerHierarchie = new Vector<E2_BasicModuleContainer>();
	
	
	
	private boolean    				bWasUsedAsPopup = false;
	
	
	
	/*
	 * variable signalisiert, dass ein closeEvent von dem close-button des 
	 * fensters (dann muessen evtl. messages angezeigt werden) oder von der close-action
	 * eines benutzer-clicks (OK oder ABBRUCH) ausgefuehrt wurde (dann duerfen im window-close-event keine
	 * messages angezeigt werden, da sonst die messages sich gegenseitig ausloeschen 
	 */
	private boolean 				bCloseWithWindowCloseButton = true;
	
	/*
	 * wenn ein container der start-container eines moduls ist (z.B. in einem Tab-Pane liegt), dann
	 * wird der schalter bIsStartContainer = true. findet ein button einen solchen startcontainer beim 
	 * click-event, dann wird ein datenbank-stamp hinterlegt
	 */
	private boolean 				bIsStartContainer_4_DBTimeStamps = false;
	
	//private String   				MILLISECONDSTAMP = null;

	
	/*
	 * ein vector mit close-actionAgents, die im close-event aufgerufen werden
	 */
	private Vector<XX_ActionAgentWhenCloseWindow> vCloseActions = new Vector<XX_ActionAgentWhenCloseWindow>();
	
	
	/*
	 * ein disabler-objekt, das fuer bestimmte aktionen alle aktiven komponenten in sich selbst disabled
	 */
	private E2_ComponentDisabler   oDisabler = new E2_ComponentDisabler(this);
	
	
	
	/*
	 * ein schalter fuer die popup-buttons in den Fehlermeldungen.
	 * falls ein container nicht das interface  Refreshable erfuellt, aber auch nicht refresht werden muss
	 * (z.B. Maskencontainer) kann dieser schalter gesetzt werden, damit die popups editierbar bleiben
	 * 
	 */
	private boolean	  				bMustBeRefreshed = true;
	
	
	
	/*
	 * Komponente, die einen POPUP ausgeloest hat, hier mitschreiben
	 */
	private Component    			oComponentWhichStartetThisPopup = null;
	
	
	/*
	 * wird eine komponente dieser klasse zugefuegt und ist es eine E2_NavigationList, dann wird diese hier vermerkt
	 */
	private E2_NavigationList      oNaviListFirstAdded = null;
	
	
	
	/*
	 * angabe des vertikalen offests fuer tabbedpanes
	 */
	private Integer    				iVerticalOffsetForTabbedPane = null; 
	
	
	/*
	 * button zum simulierten verkleinern eines fensters
	 */
	private buttonMakeWindowSmall4Big		oButtonWindowSmallOrBig = new buttonMakeWindowSmall4Big();
	


	/*
	 * 2011-07-05: neues objekt zum individuellen programmierbaren einstellen von inneren groessen nach resize
	 */
	private XX_BasicContainerResizeHelper     oResizeHelper = null;
	

	
	/**
	 * 2013-04-17: vector mit objekten, die das Interface Typ E2_BasicModuleContainer_ActionOnClose. Damit kann auf Inhalte innerhalb
	 *             eines popup-fensters beim schliessen des fensters eingewirkt werden
	 */
	private Vector<E2_BasicModuleContainer_ActionOnClose>  vActionsOnWindowClose = new Vector<E2_BasicModuleContainer_ActionOnClose>();
	


	/**
	 * 2015-05-20: neue kennung fuer eine erweiterte rechteverwaltung (falls die kennung der abgespeicherten Rechte nicht der des alten Modulkenners entpricht)
	 */
	private VALID_ENUM_MODULES.RANGE_KEY   RangeKey = null;
	

	
	/**
	 * 2016-05-17: speichermoeglichkeit fuer focusable componenten vor dem popup
	 */
	private ArrayList<Component>   al_components_outside_which_can_get_focus = new ArrayList<>();
	private Component  			   compontFocusAfterClose = null;

	
	
	// feld kann benutzt werden, um auf abgeleiteten klassen eine init-methode zu erzwingen (bevor ein modul benutzt werden kann)
	private boolean             initOk = true;                 
	




	/**
	 * Konstuktor kann benutzt werden, wenn der container als basis benutzt wird,
	 * nicht innerhalb eines Popup-fensters
	 */
	public E2_BasicModuleContainer()
	{
		super(new Style_Column_Normal(0,new Insets(2)));
		
		this.RESET_Content();
		
		oInfoButton.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				E2_BasicModuleContainer.this.showActualMessages();
			}
		});
		
		
		//2016-05-17: kein focus
		this.oPopUpSettings.setFocusTraversalParticipant(false);
		this.oInfoButton.setFocusTraversalParticipant(false);

	}
	
	
	
	
	public void RESET_Content()
	{
		this.RESET_Content(true);
//		this.removeAll();
//		this.add(this.oGridTopLineButtonsAndMessages);
//		this.add(this.oSeparator);
//		this.oGridTopLineButtonsAndMessages.setLayoutData(new ColumnLayout_For_MessagePanel());
//		E2_MutableStyle oStyle = new E2_MutableStyle();
//		oStyle.setProperty(Button.PROPERTY_FONT, new E2_FontItalic(), new E2_FontItalic());
//		oStyle.setProperty(Button.PROPERTY_FOREGROUND, new E2_ColorDDDark(), new E2_ColorDDDark());
//		oStyle.setProperty(Button.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
//
//		oInfoButton.EXT().set_oE2_Style(oStyle);
//		oInfoButton.setStyle(oStyle);
//		
//		this.oPopUpSettings.setVisible(false);    //nur sichtbar, wenn es etwas zu speichern gibt
//		
//		this.build_TitleContainer_NG(null);
	}


	/**
	 * gleich wie RESET_Content, lediglich ohne ausblenden des this.oPopUpSettings()
	 */
	
	
	/**
	 * martin:2015-03-27:
	 * @param hideSettingsButton
	 */
	public void RESET_Content(boolean hideSettingsButton)
	{
		this.removeAll();
		this.add(this.oGridTopLineButtonsAndMessages);
		this.add(this.oSeparator);
		this.oGridTopLineButtonsAndMessages.setLayoutData(new ColumnLayout_For_MessagePanel());
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(Button.PROPERTY_FONT, new E2_FontItalic(), new E2_FontItalic());
		oStyle.setProperty(Button.PROPERTY_FOREGROUND, new E2_ColorDDDark(), new E2_ColorDDDark());
		oStyle.setProperty(Button.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oInfoButton.EXT().set_oE2_Style(oStyle);
		oInfoButton.setStyle(oStyle);

		if (hideSettingsButton) {
			this.oPopUpSettings.setVisible(false);    //nur sichtbar, wenn es etwas zu speichern gibt
		}
		this.build_TitleContainer_NG(null);
	}

	

	
	/**
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(MyE2_String oTitle) throws myException	{
		this.CREATE_AND_SHOW_POPUPWINDOW(this.oExtWidth,this.oExtHeight,false,null,oTitle);
	}
	
	
	/**
	 * @param oextWidth
	 * @param oextHeight
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(Extent				oextWidth,
											Extent				oextHeight,
											MyE2_String 		oTitle) throws myException
	{
		this.CREATE_AND_SHOW_POPUPWINDOW(oextWidth,oextHeight,false,null,oTitle);
	}
	
	
	

	/**
	 * 
	 * @author martin
	 * @date 02.10.2019
	 *
	 * @param title
	 * @param width
	 * @param height
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(MyE2_String title, int width, int height) throws myException	{
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(width),new Extent(height),false,null,title);
	}
	
	/**
	 * @param oextWidth
	 * @param oextHeight
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW_SPLIT(	Extent				oextWidth,
													Extent				oextHeight,
													MyE2_String 		oTitle) throws myException
	{
		this.CREATE_AND_SHOW_POPUPWINDOW(oextWidth,oextHeight,true,null,oTitle);
	}

	


	
	/**
	 * 
	 * @param oWidth
	 * @param oHeight
	 * @param oE2_ContentPaneCallingUnit
	 * @param bSplit
	 * @param oSplitPosition
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(		Extent 					oWidth, 
													Extent 					oHeight,
													boolean 				bSplit,
													Extent    				oSplitPosition,
													MyE2_String 			oTitle) throws myException
	{
	
		//2021-02-16: benutzen der initOk-Variable
		if (!initOk) {
			throw new myException("You need to init this module !!");
		}
		
		if (oWidth != null) 	this.oExtWidth = 			oWidth;
		if (oHeight != null) 	this.oExtHeight = 			oHeight;

		//zuerst den letzten actionEvent suchen, es muss einer da sein
		MyActionEvent oEvent = bibE2.get_LAST_ACTIONEVENT();
		if (oEvent==null)
			throw new myException(this,":CREATE_AND_SHOW_POPUPWINDOW:Cannot find last ActionEvent !");
		
		this.set_oComponentWhichStartetThisPopup((Component)oEvent.getSource());
		
		this.bWasUsedAsPopup = true;                //unterscheidung zwischen eingelagerten und als popup verwendeten containern
		this.bCloseWithWindowCloseButton = true;     // vorgabe
		


		// BasicModuleContainer zum event suchen
		E2_BasicModuleContainer oBasicContainer_from_EventComponent_WithMessageBlock = 
			new E2_RecursiveSearchParent_BasicModuleContainer(oEvent).get_First_FoundContainer_WithMessageBlock();

		
		//jetzt die kette der popup-modulecontainer fuellen, damit ein messageevent einen messageagent finden kann
		if (oBasicContainer_from_EventComponent_WithMessageBlock != null)   //null ist moeglich, wenn der button nicht innerhalb eines E2_BasicModuleContainers liegt
		{
			this.vBasicContainerHierarchie.removeAllElements();
			this.vBasicContainerHierarchie.addAll(oBasicContainer_from_EventComponent_WithMessageBlock.get_vBasicContainerHierarchie());	
			this.vBasicContainerHierarchie.add(oBasicContainer_from_EventComponent_WithMessageBlock);
		}
		
		
		//Basiccontainer mit MODULKENNER suchen und vermerken, damit bei popup-fenster der Automatische AuthValidator funktioniert
		this.MODUL_IDENIFIER_OF_CALLING_BasicModuleContainer = null;
		
		E2_RecursiveSearchParent_BasicModuleContainer oRecSearch = new E2_RecursiveSearchParent_BasicModuleContainer(oEvent);
		
		E2_BasicModuleContainer oBasicContainer_from_EventComponent_WithModulKenner = oRecSearch.get_First_FoundContainer_With_MODUL_IDENTIFIER();
		E2_BasicModuleContainer oBasicContainer_from_EventComponent_With_Parent_ModulKenner = oRecSearch.get_First_FoundContainer_With_MODUL_IDENTIFIER_FROM_CALLING_MODULE();
		
		if (oBasicContainer_from_EventComponent_WithModulKenner != null)
		{
			this.MODUL_IDENIFIER_OF_CALLING_BasicModuleContainer = oBasicContainer_from_EventComponent_WithModulKenner.get_MODUL_IDENTIFIER();
		}
		else
		{
			if (oBasicContainer_from_EventComponent_With_Parent_ModulKenner != null)   //dann den MODULKENNER an den uebernaechten popup weitergeben
			{
				this.MODUL_IDENIFIER_OF_CALLING_BasicModuleContainer = oBasicContainer_from_EventComponent_With_Parent_ModulKenner.get_MODUL_IDENTIFIER_OF_CALLING_BasicModuleContainer();
			}
		}
	
		this.cWINDOW_CLOSE_STATUS = E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL;

		
		this.oWindowPane = new MyE2_WindowPane(true);
		
		//2018-05-16: closeButton uebergeben (falls vorhanden)
		this.oWindowPane.setButtonForClosingWindow(this.buttonForClosingWindow);
		//2018-05-16
		
		this.oWindowPane.setStyle(this.oStyleForWindowPane);
		
		//2011-07-05: verbindung zu container schaffen
		this.oWindowPane.set_oContainerThisBelongsTo(this);
		
		
		if (oTitle != null)
			this.oWindowPane.set_oTitle(oTitle);
		

		this.oContentPaneAussen.removeAll();
		this.oContentPaneInnen.removeAll();
		this.oContentPaneInnen.add(this);

		SplitPane  spHelp = new SplitPane(SplitPane.ORIENTATION_VERTICAL);    //splitpane zur darstellung einer trennung zur titelzeile
		spHelp.setSeparatorColor(new Color(120,120,120));
		spHelp.setSeparatorHeight(new Extent(1));
		spHelp.setSeparatorPosition(new Extent(0));
		this.oContentPaneAussen.add(spHelp);
		spHelp.add(new Row());                  //dummy
	
		if (bSplit)
		{
			this.oSplitPane.removeAll();
			
			//this.oContentPaneAussen.add(this.oSplitPane);
			spHelp.add(this.oSplitPane);
	
			this.oSplitPane.add(this.oContentPaneInnen);
			this.oSplitPane.add(this.oContentPaneOfWindowButtons);
			//this.oSplitPane.setSeparatorPosition(this.oExtSplitPosition);
		}
		else
		{
			//this.oContentPaneAussen.add(this.oContentPaneInnen);
			spHelp.add(this.oContentPaneInnen);
		}

		this.oWindowPane.add(this.oContentPaneAussen);
		
		this.oWindowPane.setWidth(this.oExtWidth);
		this.oWindowPane.setHeight(this.oExtHeight);

		if (this.oExtLeftPos !=null) this.oWindowPane.setPositionX(this.oExtLeftPos);
		if (this.oExtTopPos !=null) this.oWindowPane.setPositionY(this.oExtTopPos);
		
		if (this.oExtMINIMALWidth !=null) this.oWindowPane.setMinimumWidth(this.oExtMINIMALWidth);
		if (this.oExtMINIMALHeight !=null) this.oWindowPane.setMinimumHeight(this.oExtMINIMALHeight);

		
		
		/*
		 * veranlassen, dass nach dem schliessen eines fensters dieses vernichtet wird
		 */
		this.oWindowPane.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		this.oWindowPane.addWindowPaneListener(this);


		
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(this.oWindowPane);
		
		this.bPopUpWasShown = true;
		bibE2.set_LASTPOPUP_CONTAINER(this);

		
		//workaround fuer darstellungsfehler beim window-popup ab firefox 29
		int iHeight =400;
		if (this.oExtHeight!=null && this.oExtHeight.getUnits()==Extent.PX) {
			iHeight=this.oExtHeight.getValue();
		}
		new E2_BasicModuleContainer_ExtendTabPaneHightAutomatic(this, iHeight);
		
		
		this.oPopUpSettings.removeAllButtons();
		if (new E2_UserSettingWindowSize().prepareContainer(this))
		{
			this.oPopUpSettings.addButton(new E2_BasicModuleContainerButtonSaveSize(this), true);
		}


		//nachsehen, ob tabbedPaneReihenfolge gespeichert ist
		if (new E2_UserSettingTabReihenfolge().prepareTabbedPane(this))
		{
			this.oPopUpSettings.addButton(new E2_BasicModuleContainer_Button_To_Change_TabPaneOrder(this), true);
		}
		
		//2014-10-13: falls developer, neuer button fuer das einblenden von feldinfos
		if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
			this.oPopUpSettings.addButton(new E2_BasicModuleContainer_Button_ShowFieldnames(),true);
		}
		
		this.oButtonWindowSmallOrBig.setVisible(true);
				
		
		if (bSplit)
		{
			//eine evtl. splitposition erst nach dem laden der groesse setzen 
			if (this.oExtSplitPosition==null) this.oExtSplitPosition = new Extent(this.oExtHeight.getValue()-80);
			if (oSplitPosition != null) 	
			{
				this.oExtSplitPosition = 	oSplitPosition;
			}
			this.oSplitPane.setSeparatorPosition(this.oExtSplitPosition);
		}
		
		//jetzt die close-actioner einschalten
		this.set_CloseActionersTo_UNDONE();
		
		//jetzt schauen, ob ein focus gesetzt ist
		this.set_focus_if_one_component_has();
	}


	
	
	private void set_focus_if_one_component_has() throws myException
	{
		Vector<MyE2IF__Component> vComponents = new E2_RecursiveSearch_IF_Component(this,true).get_vE2IF_Components();

		for (MyE2IF__Component oComp: vComponents)
		{
			if (oComp.EXT().get_bHasFocusOnWindowPopup())
			{
				ApplicationInstance.getActive().setFocusedComponent((Component)oComp);
			}
		}
	}
	
	
	
	

	// ersatz mit steuerung der variable cWINDOW_CLOSE_STATUS
	public void CLOSE_AND_DESTROY_POPUPWINDOW(boolean bCloseStatusOK_or_Cancel) throws myException
	{
		
		if (bCloseStatusOK_or_Cancel) {
			this.cWINDOW_CLOSE_STATUS = E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_SAVE;
		} else {
			this.cWINDOW_CLOSE_STATUS = E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL;
		}
		
		this.bCloseWithWindowCloseButton = false;
		
		if (this.oWindowPane != null && this.bPopUpWasShown)
		{
			//this.oWindowPane.userClose();   	//aenderung 2018-05-16 neues close-handling
			this.oWindowPane.superUserClose();  //aenderung 2018-05-16 neues close-handling
			this.oWindowPane = null;
		} else {
			this.oWindowPane = null;
		}
		
		this.bPopUpWasShown = false;
		bibE2.set_LASTPOPUP_CONTAINER(null);

		/*
		 * WICHTIG: zuerst muss der window-closing-event erfolgen, da dort evtl noch
		 * eine recursionssuche mit einem (extra erzeugten) actionevent gestartet wird, um einen
		 * timestamp zu finden. Dieser ist aber in der ContentPane abgelegt. Wird diese nun geleert,
		 * dann findet die recursion nix !!!
		 */
		this.execute_CloseActionVector();
		this.oContentPaneInnen.removeAll();
		
		
		//2013-04-17: den Vector vActionsOnWindowClose abarbeiten
		for (E2_BasicModuleContainer_ActionOnClose oCloseAction: vActionsOnWindowClose) {
			oCloseAction.do_ActionOnClose(this);
		}
		
		
		//2016-05-17: die vorigen focusierbaren komponenten wieder eintragen
		this.restore_focusable_components();
	}


	
	
	
	public void windowPaneClosing(WindowPaneEvent Evi)
	{		
		
		try
		{
			this.oPopUpSettings.removeAllButtons();
//			this.oButtonSaveSize.setVisible(false);
//			this.oButtonChangeTabbedPane.setVisible(false);
			
			this.oWindowPane = null;
			this.bPopUpWasShown = false;
			bibE2.set_LASTPOPUP_CONTAINER(null);
			/*
			 * WICHTIG: zuerst muss der window-closing-event erfolgen, da dort evtl noch
			 * eine recursionssuche mit einem (extra erzeugten) actionevent gestartet wird, um einen
			 * timestamp zu finden. Dieser ist aber in der ContentPane abgelegt. Wird diese nun geleert,
			 * dann findet die recursion nix !!!
			 */
			this.execute_CloseActionVector();
			
			this.oContentPaneInnen.removeAll();
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("E2_BasicModuleContainer:windowPaneClosing: ",false)));
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(),false);
		}
		
		/*
		 * hier noch evtl. aufgelaufenen messages anzeigen (nur wenn der messagecontainer noch was enthaelt,
		 * sonst passiert beim schliessen via windowbutton folgendes:
		 * Der button durchlauft das action-handling und zeigt messages an, danach wird der windowpane-closing-event
		 * aufgerufen und zeigt im gleichen container nochmal die (jetzt leere) Message-queue an -> damit wird scheinbar nichts angezeigt
		 */ 
		if (this.vBasicContainerHierarchie != null && this.vBasicContainerHierarchie.size()>0 && this.bCloseWithWindowCloseButton)
		{
			E2_BasicModuleContainer oVorherigerContainer= (E2_BasicModuleContainer)this.vBasicContainerHierarchie.get(0);
			oVorherigerContainer.showActualMessages();
		}

	}

	
	

	private void execute_CloseActionVector() throws myException
	{
		for (XX_ActionAgentWhenCloseWindow oClose: vCloseActions)
		{
			if (!oClose.get_bWasDone())
			{
				oClose.ExecuteAgentCode(new ExecINFO_OnlyCode());
			}
			oClose.set_bWasDone(true);
		}
	}
	
	
	
	private void set_CloseActionersTo_UNDONE() throws myException
	{
		for (XX_ActionAgentWhenCloseWindow oClose: vCloseActions)
		{
			oClose.set_bWasDone(false);
		}

	}
	


	public String get_MODUL_IDENTIFIER()						{		return MODUL_IDENTIFIER;	}
	public void set_MODUL_IDENTIFIER(String modul_identifier)	{		MODUL_IDENTIFIER = modul_identifier;	}

	
	public void showActualMessages() {
		/*
		 * die meldungen einsammeln und anzeigen / oder platzhalter, damit die leiste sichtbar bleibt
		 */
		if (bibMSG.MV().size()>0) {
			this.build_TitleContainer_NG(new MyE2_MessageGrid_NG(bibMSG.MV(), true,this));
		} else {
			this.cleanMessages();
		}
	}

	
	public void showMessages(MyE2_MessageVector oMV) {
		if (oMV != null && oMV.size()>0) {
			this.build_TitleContainer_NG(new MyE2_MessageGrid_NG(oMV, true,this));
		} else {
			this.cleanMessages();
		}
	}
	
	
	public void cleanMessages() {
		this.build_TitleContainer_NG(null);
	}
	
	

	
	private void build_TitleContainer_NG(MyE2_MessageGrid_NG oMessageColumn) {
		
		this.oGridTopLineButtonsAndMessages.removeAll();
		this.oGridTopLineButtonsAndMessages.add_RAW_AutoResize(this.oGridForZusatzKomponenten,new Extent(10),this.gl_4Buttons(E2_INSETS.I(1,0,2,0)));
		
		this.oGridTopLineButtonsAndMessages.add_RAW_AutoResize(this.oButtonWindowSmallOrBig,new Extent(10),this.gl_4Buttons(E2_INSETS.I(1,0,1,0)));
		this.oGridTopLineButtonsAndMessages.add_RAW_AutoResize(this.oPopUpSettings,new Extent(10),this.gl_4Buttons(E2_INSETS.I(1,0,1,0)));
		this.oGridTopLineButtonsAndMessages.add_RAW_AutoResize(this.oInfoButton,new Extent(10),this.gl_4Buttons(E2_INSETS.I(10,0,1,0)));
		
		this.oInfoButton.setWidth(new Extent(40));
		
		if (oMessageColumn != null) {
			this.oGridTopLineButtonsAndMessages.add_RAW_AutoResize(oMessageColumn,new Extent(100,Extent.PERCENT),this.gl_4Buttons(E2_INSETS.I(3,0,1,0)));
		} else {
			this.oGridTopLineButtonsAndMessages.add_RAW_AutoResize(new MyE2_Label(""),new Extent(100,Extent.PERCENT),this.gl_4Buttons(E2_INSETS.I(3,0,1,0)));
		}

		
	}
	///
	//-----------------------------------------------------------------------


	private GridLayoutData gl_4Buttons(Insets inSets) {
		GridLayoutData  gl4Buttons = new GridLayoutData();
		gl4Buttons.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		gl4Buttons.setColumnSpan(1);
		gl4Buttons.setInsets(inSets);
		return gl4Buttons;
	}
	

	public MyE2_WindowPane get_oWindowPane()
	{
		return oWindowPane;
	}


	public void clear_AddOnsComponents()
	{
		this.vAddOnComponents.removeAllElements();
	}
	
	

    public void add_AddonComponent(IF_BasicModuleContainer_ADD_ON_Component oComponent)
	{
   	
		GridLayoutData  gl4Buttons = new GridLayoutData();
		gl4Buttons.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		gl4Buttons.setColumnSpan(1);
		gl4Buttons.setInsets(E2_INSETS.I(1,1,1,1));

    	
    	
		this.vAddOnComponents.add(oComponent);
		//dann gleich anzeigen
		this.oGridForZusatzKomponenten.removeAll();
		if (this.vAddOnComponents.size()>0)
		{
			for (int k=0;k<this.vAddOnComponents.size();k++)
			{
				IF_BasicModuleContainer_ADD_ON_Component oComp = (IF_BasicModuleContainer_ADD_ON_Component) this.vAddOnComponents.get(k);
				
				try
				{
					if (oComp.get_bIsShown()) {
						//this.oGridForZusatzKomponenten.add((Component)this.vAddOnComponents.get(k), new Insets(0,2,3,2));
						this.oGridForZusatzKomponenten.add_RAW_AutoResize((Component)this.vAddOnComponents.get(k),new Extent(10),gl4Buttons);
					}
				} 
				catch (myException e)
				{
					e.printStackTrace();
				}
			}
		}

    	
    	
	}

	
	
	public Vector<IF_BasicModuleContainer_ADD_ON_Component> get_add_AddOnComponentVector()
	{
		return this.vAddOnComponents;
	}
	


	private class ColumnLayout_For_MessagePanel extends ColumnLayoutData
	{
		public ColumnLayout_For_MessagePanel()
		{
			super();
			this.setInsets(new Insets(2,0,0,0));
		}
	}


	public boolean get_bPopUpWasShown()
	{
		return bPopUpWasShown;
	}






	public Extent get_oExtHeight() 				{	return oExtHeight;			}
	public Extent get_oExtLeftPos()				{	return oExtLeftPos;			}
	public Extent get_oExtTopPos()				{	return oExtTopPos;			}
	public Extent get_oExtWidth()				{	return oExtWidth;			}
	public Extent get_oExtSplitPosition()		{	return oExtSplitPosition;	}

	public void set_oExtHeight(Extent extHeight)			
	{	
		oExtHeight = extHeight;				
	}

	public void set_oExtLeftPos(Extent extLeftPos)			{	oExtLeftPos = extLeftPos;			}
	public void set_oExtTopPos(Extent extTopPos)			{	oExtTopPos = extTopPos;				}
	public void set_oExtWidth(Extent extWidth)				{	oExtWidth = extWidth;				}
	public void set_oExtSplitPosition(Extent extSplit)		{	oExtSplitPosition = extSplit;		}


	/**
	 * @param bVisible
	 * Schaltet die row am oberen rand ein / oder aus,
	 * ebenfalls einoder ausgeschaltet wird parallel der separator.
	 * Dies ist noetig, wenn der container innerhalb einer maske liegt
	 */
	public void set_bVisible_Row_For_Messages(boolean bVisible)
	{
		this.oSeparator.setVisible(bVisible);
		this.oGridTopLineButtonsAndMessages.setVisible(bVisible);
		this.bMessageBereichEin = bVisible;
	}




	
	/**
	 * @param oComp
	 * Fuegt dem Popup-Fenster eine komponente mit Bedienelementen hinzu
	 */
	public void set_Component_To_ButtonPane(Component oComp)
	{
		this.oContentPaneOfWindowButtons.removeAll();
		this.oContentPaneOfWindowButtons.add(oComp);
		
		// jetzt die componente recursiv nach E2IF_Component-elementen durchsuchen und denen den Hinweis auf den eigenen container geben
		// damit im messageagent-handling der container in der splitpane auch gefunden wird
		try
		{
			Vector<MyE2IF__Component> vComponents = new E2_RecursiveSearch_IF_Component(oComp, true).get_vE2IF_Components();
			for (int i=0;i<vComponents.size();i++)
			{
				((MyE2IF__Component)vComponents.get(i)).EXT().set_oBasicModulContainerThisBelongsTo(this);
			}
			
		}
		catch (Exception ex){};
		
	}
	
	
	
	
	public SplitPane get_oSplitPane() 
	{
		return oSplitPane;
	}

	
	/**
	 * es wird ein container-ex angelegt, der (falls gewuenscht) benutzt werden
	 * kann, um die Liste aufzunehmen. Das fuehrt dann dazu, dass bei langen listen,
	 * die scrollbars in den listen und nicht in den ganzen fenstern liegen.
	 * Um den ContainerEX zu nutzen muss eine komponente (z.B. E2_NavigationList) mit
	 * der Methode add_In_ContainerEX() dazugefuegt werden
	 * @param oComp
	 * @param containerExScrollWidth
	 * @param containerExScrollHeight
	 * @param oInsets
	 */
	public void add_In_ContainerEX(Component oComp,Extent containerExScrollWidth,Extent containerExScrollHeight, Insets oInsets)
	{
		
		MyE2_ContainerEx oContainer = new MyE2_ContainerEx(oComp);
		if (containerExScrollWidth!=null) oContainer.setWidth(containerExScrollWidth);
		if (containerExScrollHeight!=null) oContainer.setHeight(containerExScrollHeight);
		if (oInsets==null)
			this.add(oContainer);
		else
			this.add(oContainer,oInsets);
	}



	
	


	public MyE2_Grid get_oGridTopLineButtonsAndMessages() {
		return this.oGridTopLineButtonsAndMessages;
	}
	
	
	
	public boolean get_bMessageBereichEin()
	{
		return bMessageBereichEin;
	}



	public MutableStyle get_oStyleForWindowPane() 
	{
		return oStyleForWindowPane;
	}


	public void set_oStyleForWindowPane(MutableStyle styleForWindowPane) 
	{
		oStyleForWindowPane = styleForWindowPane;
	}
	
	
	public String get_cWINDOW_CLOSE_STATUS() 
	{
		return cWINDOW_CLOSE_STATUS;
	}

	public void set_cWINDOW_CLOSE_STATUS(String cwindow_close_status) 
	{
		cWINDOW_CLOSE_STATUS = cwindow_close_status;
	}



	public Vector<E2_BasicModuleContainer> get_vBasicContainerHierarchie() 
	{
		return vBasicContainerHierarchie;
	}



	
	public boolean get_bIsPopupContainer()
	{
		return this.bWasUsedAsPopup;
	}


	
	public void set_WindowPane(MyE2_WindowPane oPane)
	{
		this.oWindowPane = oPane;
	}

	public void set_bPopUpWasShown(boolean popUpWasShown) 
	{
		bPopUpWasShown = popUpWasShown;
	}
	
	public void set_bWasUsedAsPopup(boolean bwasUsedAsPopup) 
	{
		this.bWasUsedAsPopup = bwasUsedAsPopup;
	}


	public boolean get_bIsStartContainer_4_DBTimeStamps() 
	{
		return this.bIsStartContainer_4_DBTimeStamps;
	}


	public void set_bIsStartContainer_4_DBTimeStamps(boolean isStartContainer) 
	{
		this.bIsStartContainer_4_DBTimeStamps = isStartContainer;
	}


	
	public void  add_CloseActions(XX_ActionAgentWhenCloseWindow oAction) 
	{
		this.vCloseActions.add(oAction);
		oAction.set_bWasDone(false);
	}

	public void  clear_CloseActions() 
	{
		this.vCloseActions.removeAllElements();
	}








	public Extent get_oExtMINIMALWidth() 
	{	
		return oExtMINIMALWidth;
	}


	public void set_oExtMINIMALWidth(Extent extMINIMALWidth)
	{
		oExtMINIMALWidth = extMINIMALWidth;
	}


	public Extent get_oExtMINIMALHeight()
	{
		return oExtMINIMALHeight;
	}


	public void set_oExtMINIMALHeight(Extent extMINIMALH)
	{
		oExtMINIMALHeight = extMINIMALH;
	}




	public E2_ComponentDisabler get_oDisabler()
	{
		return this.oDisabler;
	}


	/**
	 * eine eigene klass zur verwaltung von settings 
	 */
	private class PopDown_Settings extends MyE2_PopUpMenue
	{

		public PopDown_Settings()
		{
			super(E2_ResourceIcon.get_RI("einstellungen.png"),E2_ResourceIcon.get_RI("leer.png"),false);
			this.setPopUpAlignment(new Alignment(Alignment.CENTER,Alignment.BOTTOM));
			this.setPopUpLeftOffset(80);
	        this.setPopUpTopOffset(0);
		}
		
		
	    public void addButton(MyE2_Button oNewButton, boolean bSetDefaultStyle)
	    {
	    	super.addButton(oNewButton, bSetDefaultStyle);
	    	this.setVisible(this.get_vMenueButtons().size()>0);
	    }

	    

	    
	    /**
	     * Baut eine impliziten button aus dem uebergebenen string
	     * @param cButtonText
	     */
	    public void addTextButton(MyString cButtonText, String cEVENTKENNUNG)
	    {
	    	super.addTextButton(cButtonText, cEVENTKENNUNG);
	    	this.setVisible(this.get_vMenueButtons().size()>0);
	    }

		
	}


	public boolean get_bMustBeRefreshed()
	{
		return bMustBeRefreshed;
	}




	public void set_bMustBeRefreshed(boolean mustBeRefreshed)
	{
		bMustBeRefreshed = mustBeRefreshed;
	}



	/**
	 * falls das fenster zusatzinfos fuer die benutzerdefinierten einstellungen haben soll, ausser 
     * des klassennamens der E2_BasicModuleContainers, dann kann der hier hinterlegt werden
	 * damit werden unterschiedliche varianten innerhalb einesprogrammteils auch unterschieden
	 * @return
	 */
	public String get_cADDON_TO_CLASSNAME()
	{
		return cADDON_TO_CLASSNAME;
	}



	/**
 * 	 * falls das fenster zusatzinfos fuer die benutzerdefinierten einstellungen haben soll, ausser 
     * des klassennamens der E2_BasicModuleContainers, dann kann der hier hinterlegt werden
	 * damit werden unterschiedliche varianten innerhalb einesprogrammteils auch unterschieden

	 * @param caddon_to_classname
	 */
	public void set_cADDON_TO_CLASSNAME(String caddon_to_classname)
	{
		cADDON_TO_CLASSNAME = caddon_to_classname;
	}




	public String get_MODUL_IDENTIFIER_OF_CALLING_BasicModuleContainer()
	{
		return MODUL_IDENIFIER_OF_CALLING_BasicModuleContainer;
	}




	public Component get_oComponentWhichStartetThisPopup()
	{
		return oComponentWhichStartetThisPopup;
	}




	public void set_oComponentWhichStartetThisPopup(Component componentWhichStartetThisPopup)
	{
		oComponentWhichStartetThisPopup = componentWhichStartetThisPopup;
	}




	public E2_NavigationList get_oNaviListFirstAdded()
	{
		return oNaviListFirstAdded;
	}
	
	
	public void set_oNaviListFirstAdded(E2_NavigationList oNaviList)
	{
		if (this.oNaviListFirstAdded==null)
		{
			this.oNaviListFirstAdded = oNaviList;
			
			//2011-03-17: Container der navilist uebergeben
			oNaviList.set_oContainer_NaviList_BelongsTo(this);

		}

	}
	
	
	
	
	//dazu werden ein paar methoden ueberschrieben

	public void add(Component oComp)
	{
		super.add(oComp);
		if (oComp instanceof E2_NavigationList && this.oNaviListFirstAdded==null)
		{
			this.oNaviListFirstAdded = (E2_NavigationList)oComp;
			
			//2011-03-17: Container der navilist uebergeben
			((E2_NavigationList)oComp).set_oContainer_NaviList_BelongsTo(this);

		}
	}

	
	
	public void add(Component oComp, Insets oInsets)
	{
		super.add(oComp,oInsets);
		if (oComp instanceof E2_NavigationList && this.oNaviListFirstAdded==null)
		{
			this.oNaviListFirstAdded = (E2_NavigationList)oComp;

			//2011-03-17: Container der navilist uebergeben
			((E2_NavigationList)oComp).set_oContainer_NaviList_BelongsTo(this);

		}
	}
	
	public void add(Component oComp, ColumnLayoutData oLayout)
	{
		super.add(oComp,oLayout);
		if (oComp instanceof E2_NavigationList && this.oNaviListFirstAdded==null)
		{
			this.oNaviListFirstAdded = (E2_NavigationList)oComp;

			//2011-03-17: Container der navilist uebergeben
			((E2_NavigationList)oComp).set_oContainer_NaviList_BelongsTo(this);
		}
	}
	
	public void addAfterRemoveAll(Component oComp, Insets oInsets)
	{
		super.addAfterRemoveAll(oComp, oInsets);
		if (oComp instanceof E2_NavigationList && this.oNaviListFirstAdded==null)
		{
			this.oNaviListFirstAdded = (E2_NavigationList)oComp;
			
			//2011-03-17: Container der navilist uebergeben
			((E2_NavigationList)oComp).set_oContainer_NaviList_BelongsTo(this);

		}
	}
	
	public void addAfterRemoveAll(Component oComp, ColumnLayoutData oLayout)
	{
		super.addAfterRemoveAll(oComp, oLayout);
		if (oComp instanceof E2_NavigationList && this.oNaviListFirstAdded==null)
		{
			this.oNaviListFirstAdded = (E2_NavigationList)oComp;
			
			//2011-03-17: Container der navilist uebergeben
			((E2_NavigationList)oComp).set_oContainer_NaviList_BelongsTo(this);

		}
	}


	public Integer get_iVerticalOffsetForTabbedPane() 
	{
		return iVerticalOffsetForTabbedPane;
	}


	public void set_iVerticalOffsetForTabbedPane(Integer VerticalOffsetForTabbedPane) 
	{
		this.iVerticalOffsetForTabbedPane = VerticalOffsetForTabbedPane;
	}


	
	public buttonMakeWindowSmall4Big get_oButtonWindowSmallOrBig() 
	{
		return oButtonWindowSmallOrBig;
	}

	
	
	private class buttonMakeWindowSmall4Big extends MyE2_Button
	{
		public buttonMakeWindowSmall4Big() 
		{
			super(E2_ResourceIcon.get_RI("window_big.png"));
			this.setStyle(MyE2_Button.StyleImageButtonBorderLess());
			
			// !!!!!!!! NORMAL unsichtbar, erst im popup-fall sichtbar
			
			this.setVisible(false);
			this.setToolTipText(new MyE2_String("Fenster ausblenden ...").CTrans());
			
			//2016-05-17: kein focus
			this.setFocusTraversalParticipant(false);
			
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					E2_BasicModuleContainer  oThis = E2_BasicModuleContainer.this;
				
					if (oThis.oWindowPane!=null)
					{
						
						MiniWindow oMiniWindow = new MiniWindow();
						bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(oMiniWindow);

						oThis.oWindowPane.setVisible(false);
					}
				}
			});			
		}
	}
	
	

	//window-klasse zum einblenden eines mini-platzhalters fuer verkleinerungen
	private class MiniWindow extends MyE2_WindowPane
	{
		public MiniWindow() 
		{
			super(true);
			
			this.setPositionX(new Extent(10));
			this.setPositionY(new Extent(10));
			this.setWidth(new Extent(300));
			this.setHeight(new Extent(60));
			
			this.setClosable(false);
			this.setResizable(false);
			this.setStyle(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
			
			this.setTitle(E2_BasicModuleContainer.this.oWindowPane.getTitle());
			
			this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
			
			E2_ContentPane  oPane = new E2_ContentPane(true);
			MyE2_Grid       oGrid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGrid.setWidth(new Extent(100,Extent.PERCENT));
			oGrid.setWidth(new Extent(100,Extent.PERCENT));
			
			MyE2_Button     oButtonClose = new MyE2_Button(new MyE2_String("Fenster wieder einblenden ..."));
			oButtonClose.setWidth(new Extent(95,Extent.PERCENT));
			oButtonClose.setHeight(new Extent(95,Extent.PERCENT));
			oButtonClose.setForeground(Color.BLACK);
			oButtonClose.setBackground(Color.RED);
			
			
			oButtonClose.setFont(new E2_FontBold(4));
			
			oButtonClose.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					MiniWindow.this.userClose();
					E2_BasicModuleContainer.this.oWindowPane.setVisible(true);
				}
			});
			
			
//			KeyStrokeListener  oKL = new KeyStrokeListener();
//			oKL.addKeyCombination(KeyStrokeListener.VK_RETURN);
//			oKL.addKeyCombination(KeyStrokeListener.VK_ESCAPE);
//			oKL.addKeyCombination(KeyStrokeListener.VK_ENTER);
//			oKL.addActionListener(new ActionListener()
//				{
//					public void actionPerformed(ActionEvent arg0)
//					{
//						MiniWindow.this.userClose();
//						E2_BasicModuleContainer.this.oWindowPane.setVisible(true);
//					}
//				});


			this.add(oPane);
			oPane.add(oGrid);
			oGrid.add(oButtonClose, MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
//			oGrid.add(oKL);
		}
	}
	

	//2011-07-05: individuell programmierbare vergroesserung einstellbar
	public XX_BasicContainerResizeHelper get_oResizeHelper()
	{
		return oResizeHelper;
	}




	public void set_oResizeHelper(XX_BasicContainerResizeHelper oResizeHelper)
	{
		this.oResizeHelper = oResizeHelper;
	}




	//2011-02-10: Grid in Rahmen
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt, Insets oInsets)
	{
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		MyE2_Grid_InLIST oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this);
		return oGridRueck;
	}




	//2011-11-18: neue getter-methoden
	public E2_ContentPane get_oContentPaneAussen()
	{
		return oContentPaneAussen;
	}

	public E2_ContentPane get_oContentPaneInnen()
	{
		return oContentPaneInnen;
	}

	public E2_ContentPane get_oContentPaneOfWindowButtons()
	{
		return oContentPaneOfWindowButtons;
	}

	public Vector<E2_BasicModuleContainer_ActionOnClose> get_vActionsOnWindowClose() {
		return vActionsOnWindowClose;
	}

	
	//2014-11-11: pruefen, ob der Container als popup-window vorliegt
	public boolean IS_Popup() {
		return this.oWindowPane!=null;
	}



	/**
	 * 2015-05-20: neue kennung fuer eine erweiterte rechteverwaltung (falls die kennung der abgespeicherten Rechte nicht der des alten Modulkenners entpricht)
	 */
	public VALID_ENUM_MODULES.RANGE_KEY get_rangeKey() {
		return this.RangeKey;
	}


	/**
	 * 2015-05-20: neue kennung fuer eine erweiterte rechteverwaltung (falls die kennung der abgespeicherten Rechte nicht der des alten Modulkenners entpricht)
	 */
	public void set_RangeKey(VALID_ENUM_MODULES.RANGE_KEY rangeKey) {
		this.RangeKey = rangeKey;
	}
	
	
	
	/**
	 * methode macht alle komponenten ausserhalb des containers nicht focussierbar
	 * @return
	 */
	public ArrayList<Component>  save_focusable_components_outside(Component  p_componentFocusAfterclose) {
		this.compontFocusAfterClose = p_componentFocusAfterclose;
		ArrayList<Component> all_components = new ArrayList<>();
		
		ArrayList<Component> my_components = new ArrayList<>();
		ArrayList<Component> help = new ArrayList<>();
		
		help.clear();
		help.addAll(new E2_RecursiveSearch_Component(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(),null,null).get_vAllComponents());
		for (Component  c: help) {
			if (c.isFocusTraversalParticipant()) {
				all_components.add(c);
			}
		}
		
		help.clear();
		help.addAll(new E2_RecursiveSearch_Component(this,null,null).get_vAllComponents());
		for (Component  c: help) {
			if (c.isFocusTraversalParticipant()) {
				my_components.add(c);
			}
		}
		
		this.al_components_outside_which_can_get_focus.clear();
		for (Component c: all_components) {
			if (c.isFocusTraversalParticipant()) {
				if (!my_components.contains(c)) {
					this.al_components_outside_which_can_get_focus.add(c);
					c.setFocusTraversalParticipant(false);
				}
			}
		}
		
		return this.al_components_outside_which_can_get_focus;
	}
	
	
	public void restore_focusable_components() {
		for (Component c: this.al_components_outside_which_can_get_focus) {
			c.setFocusTraversalParticipant(true);
		}
		if (this.compontFocusAfterClose!=null) {
			bibALL.setFocus(this.compontFocusAfterClose);
		}
		this.compontFocusAfterClose=null;
	}
	
	
	
	/**
	 * splitpane mit 3 zeilen, oben das messagegrid, dazwischen ein beliebiges steuergrid, darunter der basiccontainer
	 * (beispiel fuer eigene ableitungen)
	 * 
	 * @return
	 */
	public E2_BasicModuleContainer  re_arrange_window_variante_1() {
		//die eigenen contentpanes werden nicht verwendet
		E2_SplitPane_in_3_lines split = new E2_SplitPane_in_3_lines();
		
		split._set_to_top_pane(this.oGridTopLineButtonsAndMessages);
		split._set_to_bottom_pane(this.oContentPaneAussen);
		
		split._set_height_top(25);
		split._set_height_mid(10);
		
		this.oWindowPane.removeAll();
		this.oWindowPane.add(split);
		
		return this;
	}




	
	public Separator get_oSeparator() {
		return oSeparator;
	}
	

	/**
	 * 20180305-neue methoden
	 * @param extLeftPos
	 * @return
	 */
	public E2_BasicModuleContainer _setLeftPos(Extent extLeftPos) {
		oExtLeftPos = extLeftPos;
		return this;
	}
	public E2_BasicModuleContainer _setTopPos(Extent extTopPos)	  {
		oExtTopPos = extTopPos;				
		return this;
	}
	public E2_BasicModuleContainer _setWidth(Extent extWidth)	 {	
		oExtWidth = extWidth;				
		return this;
	}

	public E2_BasicModuleContainer _setHeight(Extent extHeight)	 {	
		oExtHeight = extHeight;				
		return this;
	}
	
	public E2_BasicModuleContainer _setLeftPos(int iLeftPos) {
		oExtLeftPos = new Extent(iLeftPos);
		return this;
	}
	public E2_BasicModuleContainer _setTopPos(int iTopPos)	  {
		oExtTopPos = new Extent(iTopPos);				
		return this;
	}
	public E2_BasicModuleContainer _setWidth(int iWidth)	 {	
		oExtWidth = new Extent(iWidth);				
		return this;
	}

	public E2_BasicModuleContainer _setHeight(Long iHeight)	 {	
		oExtHeight = new Extent(iHeight.intValue());				
		return this;
	}
	public E2_BasicModuleContainer _setWidth(Long iWidth)	 {	
		oExtWidth = new Extent(iWidth.intValue());				
		return this;
	}

	public E2_BasicModuleContainer _setHeight(int iHeight)	 {	
		oExtHeight = new Extent(iHeight);				
		return this;
	}

	
	
	/*
	 * 2018-05-16: neue methode, um den close-button zu kontrollieren:
	 * Es kann dieser klasse ein externer button zugeordnet werden, der die close-operation ausfuehrt.
	 * Dies kann ein eigener button oder ein button aus der maske sein.
	 * Wenn ein solcher button existert, dann wird die userClose() - Methode ueberbrueckt und durch die ausfuehrung des buttons ersetzt.
	 * Einzige Anderung im sonstigen Code: im CLOSE_AND_DESTROY - aufruf wird nicht die userclose-Methode, sondern die methode superUserClose() aufgerufen
	 */
	private Button buttonForClosingWindow = null;
	
	public Button getButtonForClosingWindow() {
		return buttonForClosingWindow;
	}

	public void setButtonForClosingWindow(Button buttonForClosingWindow) {
		this.buttonForClosingWindow = buttonForClosingWindow;
	}

	

	/**
	 * @return the initOk
	 */
	public boolean isInitOk() {
		return initOk;
	}

	/**
	 * @param initOk the initOk to set
	 */
	public E2_BasicModuleContainer _setInitOk(boolean initOk) {
		this.initOk = initOk;
		return this;
	}


	
}
