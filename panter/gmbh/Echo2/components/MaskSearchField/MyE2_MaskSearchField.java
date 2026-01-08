package panter.gmbh.Echo2.components.MaskSearchField;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_GridWithLabel;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.E2_GridWithSearchResultbuttons;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Alternative_Result_Button_Generator;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public abstract class MyE2_MaskSearchField extends MyE2_Row  implements MyE2IF__Component, E2_IF_Copy
{
	
	private Extent					oPosX = null;
	private Extent					oPosY = null;

	private MyE2_TextField 			oTextFieldForSearchInput = new MyE2_TextField();
	private MyE2_Button				buttonStartSearch	= new MyE2_Button(E2_ResourceIcon.get_RI("suche.png"),E2_ResourceIcon.get_RI("leer.png"));
	private MyE2_Button				buttonErase	= new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),E2_ResourceIcon.get_RI("leer.png"));
	private boolean   				bShowEraser = false;
	
	private int 					m_iMaxResults = 0;
	
	private TextField				oTextForAnzeige = new TextField();

	
	private String 					cCOMPLETE_SQL_FOR_Label = null;
	
	private XX_SearchBlockNonDB		oSeachBlock = null;
	
	// Auswahl-Fenster auch zeigen, wenn nur eine Ergebniszeile gefunden wurde...default == false
	private boolean					m_bShowDialogOnSingleResultRow = false;
	
	
	
	//2011-09-20: weitere funktion fuer autoaktionen nach der fuellaktion der maske
	private XX_DoSomeThingAfterComponentIsFilled   oDoSomeThingAfterComponentIsFilled = null;
	



	/*
	 * wenn diese beiden parameter uebergeben werden, existiert eine schnelle,
	 * eindeutige suche, die in der cSQLQueryForUniqueSearch erfolgt.
	 * Dies wird immer dann ausgefuehrt, wenn der suchbegriff mit dem cCharForUniqueSearch beginnt
	 */
	private String 					cSQLQueryForUniqueSearch= null; 
	private String 					cCharForUniqueSearch = null;

	/*
	 * nach dem fuellen des maskenfeldes wird dieser agent aufgerufen
	 */
	private XX_MaskActionAfterFoundNonDB		oMaskActionAfterMaskValueIsFound = null;
	
	
	private Extent					oPopupWidth = new Extent(400);
	private Extent					oPopupHigh = new Extent(500);
	
	
	/*
	 * parameter definiert, ob das anzeigefeld sichtbar sein soll oder nicht
	 */
	private boolean 				bTextForAnzeigeVisible = true;
	
	
	/*
	 * ein formatierer fuer die gefundenen buttons
	 */
	private XX_FormaterForFoundButtonsNonDB	  oFormatterForButtons = null;
	
	
	private Insets	  				oInsetsForComponents = new Insets(0,2,5,2);
	
	
	/*
	 * ein validierer-vector, der prueft, ob der suchknopf aufgerufen werden kann
	 */
	private Vector<XX_ActionValidator>  		vValidatorsForStartSearch = new Vector<XX_ActionValidator>();
	
	
	
	private XX_Alternative_Result_Button_Generator   oAlternative_Result_Button_Generator = null;

	// Vector für Eventhandler die ausgeführt werden, wenn sich der Wert des Objekts ändert
	private Vector<XX_ActionAgent> 					m_vActionAgentsAfterValueChanged = new Vector<XX_ActionAgent>();
	
	
	//2011-03-27: Label fuer anzeige moeglich
	private boolean   				bLabel4AnzeigeStattText4Anzeige = false;
	private MyE2_GridWithLabel      oLabel4Anzeige = new MyE2_GridWithLabel(200, MyE2_Grid.STYLE_GRID_DDARK_BORDER());       //mit linewrap
	
	private MyE2_Grid				oLabelContainer = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	// suchbutton ganz rechts
	private boolean    bShowSearchButtonLeft = false;

	
	//2011-09-09: zusatzkomponenten auch hier
	private Vector<MyE2IF__Component>  vAddOnComponents = new Vector<MyE2IF__Component>();

	
	/*
	 * 2011-09-12: vergleichswert auch hier
	 * eine variable haelt gefundene und geladene Datenfeldwerte fest und vergleicht diese beim speichern
	 * mit dem inhalt des sucheingabefeldes. Damit wird erzwungen, dass eine suche stattfindet,
	 * einfaches eintippen geht dann nicht mehr
	 */
	private String 				    cVergleichswertDBFeld = "";

	
	/*
	 * 2015-11-30: select-anteile veroeffentlichen
	 */
	private String 			c_sql_selectBlock = null;


	private String 			c_sql_fromBlock = null;
	private String 			c_sql_orderBlock = null;
	private String 			c_sql_whereBlockJoiningTables = null;
	private String 			c_sql_whereBlockForSelecting = null;
	
	private MutableStyle    style_4_grid_in_popup = null;
	
	


	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLOrderBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting  (Suchbedingungen mit #WERT# drin)
	 * @param cSQLqueryForUniqueSearch  (Suchbedingungen mit #WERT# drin oder null)
	 * @param charForUniqueSearch (nur mir cSQLQueryForUniqueSearch sinnvoll)
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components (NULL OK)
	 * @param bShowEraser 
	 * @param oMessageagent
	 * @throws myException
	 */
	public MyE2_MaskSearchField(	String 			cSQLSelectBlock,
									String 			cSQLFromBlock,
									String 			cSQLOrderBlock,
									String 			cSQLWhereBlockJoiningTables,
									String 			cSQLWhereBlockForSelecting,
									String 			cSQLqueryForUniqueSearch, 
									String 			charForUniqueSearch, 
									String 			cCOMPLETE_SQL_FOR_label, 
									Insets 			INSETS_For_Components, 
									boolean 		ShowEraser
									) throws myException
	{
		super();

		if ((cSQLqueryForUniqueSearch == null && charForUniqueSearch !=null ) || 
			(cSQLqueryForUniqueSearch != null && charForUniqueSearch ==null ))
			throw new myException("MyE2_DB_MaskSearchField:Constructor:cSQLQueryForUniqueSearch and cCharForUniqueSearch must be both null or not null !");

		if (cSQLqueryForUniqueSearch != null && cSQLqueryForUniqueSearch.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_MaskSearchField:cSQLQueryForUniqueSearch:cSQLQueryForUniqueSearch MUST contain #WERT# !");
		
		if (INSETS_For_Components != null)
			this.oInsetsForComponents = INSETS_For_Components;
		
		
		this.c_sql_selectBlock = cSQLSelectBlock;
		this.c_sql_fromBlock = cSQLFromBlock;
		this.c_sql_orderBlock = cSQLOrderBlock;
		this.c_sql_whereBlockJoiningTables = cSQLWhereBlockJoiningTables;
		this.c_sql_whereBlockForSelecting = cSQLWhereBlockForSelecting;

		
		
		this.cCharForUniqueSearch = charForUniqueSearch;
		this.cSQLQueryForUniqueSearch = cSQLqueryForUniqueSearch;
		
		this.oSeachBlock = new SearchBlockStandard(	cSQLSelectBlock,
														cSQLFromBlock,
														cSQLWhereBlockJoiningTables,
														cSQLWhereBlockForSelecting,
														cSQLOrderBlock,
														this.cSQLQueryForUniqueSearch,
														this.cCharForUniqueSearch,100);
		
		
		this.cCOMPLETE_SQL_FOR_Label = cCOMPLETE_SQL_FOR_label;

		this.bShowEraser = ShowEraser;
		
		if (this.cCOMPLETE_SQL_FOR_Label.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_MaskSearchField:Constructor:SQL_QUERY_FOR_LABEL MUST contain #WERT# !");
		
		this.buttonStartSearch.add_oActionAgent(new ActionAgentPerformSearch());
		this.buttonErase.add_oActionAgent(new actionEraser());
		
		
		this.build_Component();		
		
	}

	
	
	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLOrderBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting  (Suchbedingungen mit #WERT# drin)
	 * @param cSQLqueryForUniqueSearch  (Suchbedingungen mit #WERT# drin oder null)
	 * @param charForUniqueSearch (nur mir cSQLQueryForUniqueSearch sinnvoll)
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components (NULL OK)
	 * @param bShowEraser 
	 * @param oMessageagent
	 * @throws myException
	 */
	public MyE2_MaskSearchField(	String 			cSQLSelectBlock,
									String 			cSQLFromBlock,
									String 			cSQLOrderBlock,
									String 			cSQLWhereBlockJoiningTables,
									String 			cSQLWhereBlockForSelecting,
									String 			cSQLqueryForUniqueSearch, 
									String 			charForUniqueSearch, 
									String 			cCOMPLETE_SQL_FOR_label, 
									Insets 			INSETS_For_Components, 
									boolean 		ShowEraser,
									int             iMaxresults
									) throws myException
	{
		super();

		m_iMaxResults = iMaxresults;
		
		this.c_sql_selectBlock = cSQLSelectBlock;
		this.c_sql_fromBlock = cSQLFromBlock;
		this.c_sql_orderBlock = cSQLOrderBlock;
		this.c_sql_whereBlockJoiningTables = cSQLWhereBlockJoiningTables;
		this.c_sql_whereBlockForSelecting = cSQLWhereBlockForSelecting;

		
		
		if ((cSQLqueryForUniqueSearch == null && charForUniqueSearch !=null ) || 
			(cSQLqueryForUniqueSearch != null && charForUniqueSearch ==null ))
			throw new myException("MyE2_DB_MaskSearchField:Constructor:cSQLQueryForUniqueSearch and cCharForUniqueSearch must be both null or not null !");

		if (cSQLqueryForUniqueSearch != null && cSQLqueryForUniqueSearch.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_MaskSearchField:cSQLQueryForUniqueSearch:cSQLQueryForUniqueSearch MUST contain #WERT# !");
		
		if (INSETS_For_Components != null)
			this.oInsetsForComponents = INSETS_For_Components;
		
		this.cCharForUniqueSearch = charForUniqueSearch;
		this.cSQLQueryForUniqueSearch = cSQLqueryForUniqueSearch;
		
		this.oSeachBlock = new SearchBlockStandard(	cSQLSelectBlock,
														cSQLFromBlock,
														cSQLWhereBlockJoiningTables,
														cSQLWhereBlockForSelecting,
														cSQLOrderBlock,
														this.cSQLQueryForUniqueSearch,
														this.cCharForUniqueSearch,
														iMaxresults);
		
		
		this.cCOMPLETE_SQL_FOR_Label = cCOMPLETE_SQL_FOR_label;

		this.bShowEraser = ShowEraser;
		
		if (this.cCOMPLETE_SQL_FOR_Label.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_MaskSearchField:Constructor:SQL_QUERY_FOR_LABEL MUST contain #WERT# !");
		
		this.buttonStartSearch.add_oActionAgent(new ActionAgentPerformSearch());
		this.buttonErase.add_oActionAgent(new actionEraser());
		
		this.build_Component();		

	}

	
	
	
	/**
	 * @param osearchBlock
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components (NULL OK)
	 * @param ShowEraser 
	 * @throws myException
	 */
	public MyE2_MaskSearchField(	XX_SearchBlockNonDB		osearchBlock,
									String 					cCOMPLETE_SQL_FOR_label, 
									Insets 					INSETS_For_Components, 
									boolean 				ShowEraser
									) throws myException
	{
		super();

		if (INSETS_For_Components != null)
			this.oInsetsForComponents = INSETS_For_Components;

		this.oSeachBlock = osearchBlock;
		
		this.bShowEraser = ShowEraser;
		
		this.cCOMPLETE_SQL_FOR_Label = cCOMPLETE_SQL_FOR_label;
		
		if (this.cCOMPLETE_SQL_FOR_Label.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_TextField:Constructor:SQL_QUERY_FOR_LABEL MUST contain #WERT# !");
		
		this.buttonStartSearch.add_oActionAgent(new ActionAgentPerformSearch());
		this.buttonErase.add_oActionAgent(new actionEraser());
		
		
		this.build_Component();

	}
	
	
	
	
	
	private void build_Component() throws myException
	{
		this.removeAll();
		
		//DEBUG
		//this.oTextForAnzeige.setBackground(Color.RED);
		//DEBUG
		
		this.__baueAnzeigeContainer();
		
		if (this.bTextForAnzeigeVisible)
		{
			this.add(this.oLabelContainer,this.oInsetsForComponents);
		}
		
		
//		if (this.bTextForAnzeigeVisible)
//		{
//			//2011-09-14: hier bereits beruecksichtigen, das der label angezeigt wird 
//			if (this.bLabel4AnzeigeStattText4Anzeige)
//			{
//				this.oLabelContainer.removeAll();
//				this.oLabelContainer.add(this.oLabel4Anzeige,this.oInsetsForComponents);
//			}
//			else
//			{
//				this.oLabelContainer.removeAll();
//				this.oLabelContainer.add(this.oTextForAnzeige,this.oInsetsForComponents);
//			}
//		}
		
		this.add(this.oTextFieldForSearchInput,this.oInsetsForComponents);
		
		//2011-02-11:  aenderung: suchbutton ganz links, wenn
		
		if (this.bShowSearchButtonLeft)
		{
			this.add(this.buttonStartSearch,this.oInsetsForComponents);
			if (this.bShowEraser)
			{
				this.add(this.buttonErase,this.oInsetsForComponents);
			}
			this.PutAddOns();
		}
		else
		{
			this.PutAddOns();
			
			if (this.bShowEraser)
			{
				this.add(this.buttonErase,this.oInsetsForComponents);
			}
			this.add(this.buttonStartSearch,this.oInsetsForComponents);
		}
	
		//jetzt die komponente durchsuchen und alle Buttons mit einem actionAgent (als erster) versehen, der die zustandsvariablen dieser komponente resettet
		E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(this, bibALL.get_Vector(MyE2_Button.class.getName()),null);
		Vector<Component> vButtons = oSearchComps.get_vAllComponents();
		
		for (Component oComp: vButtons)
		{
			if (oComp instanceof MyE2_Button)
			{
				((MyE2_Button)oComp).add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo)	throws myException
					{
						MyE2_MaskSearchField.this.EXT().RESET_ALLE_SchalterAuf_TRUE();
					}
				},true);
			}
		}
		
		

		
		this.oTextForAnzeige.setStyle(MyE2_MaskSearchField.StyleSearchField());
		this.oTextForAnzeige.setEnabled(false);
		
		
		this.oTextFieldForSearchInput.setWidth(new Extent(100));
		this.oTextForAnzeige.setWidth(new Extent(200));
		
		this.oLabel4Anzeige.get_oLabel().setStyle(MyE2_MaskSearchField.StyleLabel4Anzeige());
		this.oLabel4Anzeige.setWidth(new Extent(200));
		
		this.oLabel4Anzeige.set_Text(" ");
		this.oLabel4Anzeige.get_oLabel().setToolTipText("");
		
		this.oTextFieldForSearchInput.setForeground(new E2_ColorGray(160));
		
		
		this.buttonErase.setToolTipText(new MyE2_String("Alle Werte im Suchfeld LEEREN ").CTrans());
		this.buttonStartSearch.setToolTipText(new MyE2_String("Suche starten ").CTrans());
		
	}


	
	
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException
	{
		boolean enabled = Enabled && this.EXT().is_ValidEnableAlowed();
		this.oTextFieldForSearchInput.set_bEnabled_For_Edit(enabled);
		this.buttonStartSearch.set_bEnabled_For_Edit(enabled);
		this.buttonErase.set_bEnabled_For_Edit(enabled);
		this.oTextFieldForSearchInput.setStyle(this.oTextFieldForSearchInput.EXT().get_STYLE_FACTORY().get_Style(enabled,true,false));
		
		//2011-09-09: zusatzkomonenten
		for (int i=0;i<this.vAddOnComponents.size();i++)
		{
			this.vAddOnComponents.get(i).set_bEnabled_For_Edit(enabled);
		}

	}

	
	public void set_bTextForAnzeigeVisible(boolean bVisible) throws myException
	{
		this.bTextForAnzeigeVisible = bVisible;
		this.build_Component();
	}




	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy(myExceptionCopy.ERROR_COPY_NOT_IMPLEMENTED);
	}
	
	
	
	public void set_FormatterForButtons(XX_FormaterForFoundButtonsNonDB formatterForButtons)
	{
		this.oFormatterForButtons = formatterForButtons;
	}


	
	//2011-09-12: fuellen der werte
	public void fill_MaskText_And_Label(String ValueForDB) throws myException
	{
		this.oTextFieldForSearchInput.setText(ValueForDB);
		this.cVergleichswertDBFeld = ValueForDB;
		this.FillLabelWithDBQuery(ValueForDB);
		
	}
	

	
	public void FillLabelWithDBQuery(String ValueForDB) throws myException
	{

		
		// zuerst die uebergabe eines leeren wertes abfangen
		if (bibALL.isEmpty(ValueForDB)){
			
			// leere Einträge immer im Textfeld anzeigen wegen dem Rand
			this.__baueAnzeigeContainer();

			this.oTextForAnzeige.setText("");
			this.oTextForAnzeige.setToolTipText("");
			
			this.oLabel4Anzeige.set_Text("");
			this.oLabel4Anzeige.get_oLabel().setToolTipText("");
			
			if (this.oDoSomeThingAfterComponentIsFilled!=null)
			{
				this.oDoSomeThingAfterComponentIsFilled.doSomeThingAfterComponentIsFilled(this, "");
			}
			
			return;
		}

		this.__baueAnzeigeContainer();
		
		
		String cQuery = bibALL.ReplaceTeilString(this.cCOMPLETE_SQL_FOR_Label,"#WERT#",ValueForDB);
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cQuery);
		
		if (cErgebnis == null || cErgebnis.length!=1)
			throw new myException("MyE2_MaskSearchField:FillLabelWithDBQuery:Error Filling Field : " + cQuery);
		
		this.oTextForAnzeige.setText(cErgebnis[0][0]);
		this.oTextForAnzeige.setToolTipText(cErgebnis[0][0]);
		
		this.oLabel4Anzeige.set_Text(cErgebnis[0][0]);
		this.oLabel4Anzeige.get_oLabel().setToolTipText(cErgebnis[0][0]);

		if (this.oDoSomeThingAfterComponentIsFilled!=null)
		{
			this.oDoSomeThingAfterComponentIsFilled.doSomeThingAfterComponentIsFilled(this, ValueForDB);
		}

		
	}

	
	
	private void __baueAnzeigeContainer()
	{
		this.oLabelContainer.removeAll();
		
		if (this.bLabel4AnzeigeStattText4Anzeige)
		{
			this.oLabelContainer.add(this.oLabel4Anzeige,this.oInsetsForComponents);
		}
		else
		{
			this.oLabelContainer.add(this.oTextForAnzeige,this.oInsetsForComponents);
		}
	}
	

	
	public MyE2_Button get_buttonStartSearch()											{		return buttonStartSearch;					}
	public TextField get_oTextForAnzeige()												{		return oTextForAnzeige;					}
	public MyE2_TextField get_oTextFieldForSearchInput()								{		return oTextFieldForSearchInput;			}
	public Extent get_oPosX()															{		return oPosX;								}
	public void set_oPosX(Extent posX)													{		oPosX = posX;								}
	public Extent get_oPosY()															{		return oPosY;								}
	public void set_oPosY(Extent posY)													{		oPosY = posY;								}
	public XX_MaskActionAfterFoundNonDB 	get_oMaskActionAfterMaskValueIsFound()				{		return oMaskActionAfterMaskValueIsFound;	}
	public void  set_oMaskActionAfterMaskValueIsFound(XX_MaskActionAfterFoundNonDB action)	{		oMaskActionAfterMaskValueIsFound=action;	}
	public XX_SearchBlockNonDB get_oSeachBlock()												{		return oSeachBlock;							}
	public void set_oSeachBlock(XX_SearchBlockNonDB seachBlock)								{		oSeachBlock = seachBlock;					}
	public void set_oPopupHigh(Extent popupHigh) 										{		oPopupHigh = popupHigh;	}
	public void set_oPopupWidth(Extent popupWidth) 										{		oPopupWidth = popupWidth;	}

	
	public boolean get_bTextForAnzeigeVisible()
	{
		return this.bTextForAnzeigeVisible;
	}

	public MyE2_GridWithLabel get_oLabel4Anzeige()
	{
		return oLabel4Anzeige;
	}

	
	public void add_ValidatorStartSearch(XX_ActionValidator oValidator)
	{
		this.vValidatorsForStartSearch.add(oValidator);
	}
	
	public void add_ValidatorStartSearch(Vector<XX_ActionValidator> vValidator)
	{
		this.vValidatorsForStartSearch.addAll(vValidator);
	}

	
	public Vector<XX_ActionValidator> get_ValidatorStartSearch()
	{
		return this.vValidatorsForStartSearch;
	}
	
	public void clear_ValidatorStartSearch()
	{
		this.vValidatorsForStartSearch.removeAllElements();
	}
	
	
	
	/**
	 * @param cUnformatedValue
	 * @param AfterActionFlag
	 * @throws myException
	 * erlaubt den AfterFoundAgenten von aussen zu starten
	 */
	public void perform_XX_MaskActionAfterFound(String cUnformatedValue,boolean AfterActionFlag) throws myException
	{
		if (this.oMaskActionAfterMaskValueIsFound != null)
			this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(cUnformatedValue, this, AfterActionFlag);

	}
	


	public static MutableStyle StyleSearchField()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorBase(0));
		oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( TextComponent.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStyle.setProperty( TextComponent.PROPERTY_FONT, new E2_FontItalic(-2));
		return oStyle;
	}
	
	public static MutableStyle StyleLabel4Anzeige()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorBase(0));
//		oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
//		oStyle.setProperty( TextComponent.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStyle.setProperty( TextComponent.PROPERTY_FONT, new E2_FontItalic(-2));
		return oStyle;
	}


	
	/**
	 * Einfügen eines Actionagents, der aufgerufen wird, wenn sich der Wert des Feldes (wahrscheinlich) geändert hat 
	 * @param oAgent
	 */
	public void addActionAgent_ValueChanged(XX_ActionAgent oAgent){
		this.m_vActionAgentsAfterValueChanged.add(oAgent);
	}
	
	/**
	 * löscht alle ActionAgents die angemeldet waren
	 */
	public void clearActionAgents_ValueChanged(){
		this.m_vActionAgentsAfterValueChanged.clear();
	}
	
	
	/**
	 * feuert alle angemeldeten ActionAgents
	 * @param oInfo
	 * @throws myException
	 */
	private void fireActionAgents_ValueChanged(ExecINFO oInfo) throws myException{
		for (XX_ActionAgent o : m_vActionAgentsAfterValueChanged){
			o.executeAgentCode(oInfo);
		}
	}
	
	
	

	private class ActionAgentPerformSearch extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			MyE2_MaskSearchField oThis = MyE2_MaskSearchField.this;
			
			
			if (oThis.vValidatorsForStartSearch.size()>0)
			{
				for (int i=0;i<oThis.vValidatorsForStartSearch.size();i++)
				{
					bibMSG.add_MESSAGE(((XX_ActionValidator)oThis.vValidatorsForStartSearch.get(i)).isValid((Component)oExecInfo.get_MyActionEvent().getSource()));
					if (bibMSG.get_bHasAlarms())
						break;
				}
			}
			
			
			if (bibMSG.get_bIsOK())
			{
			
				if (MyE2_MaskSearchField.this.oSeachBlock==null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("MyE2_DB_MaskSearch:ActionAgentPerformSearch:No SearchBlock-class is defined !!",false),false);
				}
				
				if (bibMSG.get_bIsOK())
				{
			
			
					String cText = MyE2_MaskSearchField.this.oTextFieldForSearchInput.getText();
					/*
					 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit auch formatierte IDs gefunden werden
					 */
					String cTextTest = bibALL.ReplaceTeilString(cText,".","");
					if (bibALL.isInteger(cTextTest))
						cText=cTextTest;
					
					try
					{
						Vector<XX_Button4SearchResultList[]> vButtons = new Vector<XX_Button4SearchResultList[]>();;
						boolean bStartSearchClassic = true;
						
						if (S.isEmpty(cText) && oThis.get_oAlternative_Result_Button_Generator()!=null)
						{
							if (oThis.get_oAlternative_Result_Button_Generator().get_bUseAlternative())
							{
								vButtons = oThis.get_oAlternative_Result_Button_Generator().get_vResultButtons();
								bStartSearchClassic = false;
							}
						}
						
						if (bStartSearchClassic)
						{
							vButtons = MyE2_MaskSearchField.this.oSeachBlock.get_vResultButtons(cText);
						}
						
						if (oThis.oFormatterForButtons != null)
							oThis.oFormatterForButtons.RESET();
						
						
						if (vButtons.size()==0)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Nichts gefunden ..."),false);
						}
						else if (vButtons.size()==1 
								&& !m_bShowDialogOnSingleResultRow)  // Schalter zum definieren, ob der AuswahlDialog angezeigt werden soll, wenn nur eine Zeile gefunden wurde
						{
							// volltreffer
							MyE2_Button oButt = vButtons.get(0)[0];
							MyE2_MaskSearchField.this.oTextFieldForSearchInput.setText(oButt.EXT().get_C_MERKMAL());
							MyE2_MaskSearchField.this.cVergleichswertDBFeld = oButt.EXT().get_C_MERKMAL();
							
							MyE2_MaskSearchField.this.FillLabelWithDBQuery(oButt.EXT().get_C_MERKMAL());
							if (MyE2_MaskSearchField.this.oMaskActionAfterMaskValueIsFound != null)
								MyE2_MaskSearchField.this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(oButt.EXT().get_C_MERKMAL(), MyE2_MaskSearchField.this, true);
							
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Genau ein Datensatz gefunden und geladen !"),false);
							fireActionAgents_ValueChanged(null);
						}
						else
						{
							
								
								E2_BasicModuleContainer oWindow = oThis.oSeachBlock.get_ContainerForShowResults();
								
								E2_BasicModuleContainer oWindow2 = oThis.get_ownContainer();   //eigene container-klasse zum abspeichern von weite/hoehe
								if (oWindow2!=null)
								{
									oWindow = oWindow2;
								}

								
								
								oWindow.set_oExtLeftPos(MyE2_MaskSearchField.this.oPosX);
								oWindow.set_oExtTopPos(MyE2_MaskSearchField.this.oPosY);
								
								actionAgentForSelectResult oActionKlick = new actionAgentForSelectResult(oWindow);
	
//2011-09-26: inneres grid ausgelagert in eigene methode, um sortierung zu ermoeglichen								
//								MyE2_Grid oGrid_4_Selection = new MyE2_Grid(vButtons.get(0).length,MyE2_Grid.STYLE_GRID_NO_BORDER());
//
//								for (int i=0;i<vButtons.size();i++)
//								{
//									for (int k=0;k<vButtons.get(i).length;k++)
//									{
//										vButtons.get(i)[k].add_oActionAgent(oActionKlick);
//										oGrid_4_Selection.add(vButtons.get(i)[k],new Insets(2,2,2,0));
//										if (oThis.oFormatterForButtons != null)
//											oThis.oFormatterForButtons.DO_Format(vButtons.get(i)[k]);
//									}
//								}
								
//neu ab 2011-09-26:								
								E2_GridWithSearchResultbuttons oGrid_4_Selection = 
									new E2_GridWithSearchResultbuttons(
											vButtons, MyE2_MaskSearchField.this.style_4_grid_in_popup,  
											oActionKlick, 
											oThis.oFormatterForButtons,
											oThis.get_oSeachBlock().get_hmSortierInfo(),
											oThis.get_oSeachBlock().get_bResultListIsSortable(),
											oThis);
//Ende neu ab 2011-09-26:								

								
								// Warnmeldung im Suchfenster
								if (m_iMaxResults > 0 && vButtons.size() >= m_iMaxResults ){
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurden mehr als " + Integer.toString(m_iMaxResults) + " Ergebnisse gefunden. Bitte schränken Sie die Suche ein." ));
								} else {
									bibMSG.add_MESSAGE(new MyE2_Info_Message("Es wurden " + Integer.toString(vButtons.size()) + " Ergebnisse gefunden. " ));
								}
								
								oWindow.add(oGrid_4_Selection);
								oWindow.CREATE_AND_SHOW_POPUPWINDOW(MyE2_MaskSearchField.this.oPopupWidth,
																	MyE2_MaskSearchField.this.oPopupHigh,
																	new MyE2_String("Bitte wählen Sie eine Ergebnis-Zeile !"));

						}
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					}
				}
			}
			
		}
	}
	
	/**
	 * actionagent fuer die liste von buttons, die aus dem result zurueckkommen
	 */
	private class actionAgentForSelectResult extends XX_ActionAgent
	{
		private E2_BasicModuleContainer oWindow = null;
		
		public actionAgentForSelectResult(E2_BasicModuleContainer window)
		{
			super();
			oWindow = window;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_Button oButt = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			MyE2_MaskSearchField.this.oTextFieldForSearchInput.setText(oButt.EXT().get_C_MERKMAL());
			
			//2011-09-12: vergleichsfeld fuellen
			MyE2_MaskSearchField.this.cVergleichswertDBFeld= oButt.EXT().get_C_MERKMAL();
			
			try
			{
				MyE2_MaskSearchField.this.FillLabelWithDBQuery(oButt.EXT().get_C_MERKMAL());
				if (MyE2_MaskSearchField.this.oMaskActionAfterMaskValueIsFound != null)
					MyE2_MaskSearchField.this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(oButt.EXT().get_C_MERKMAL(), MyE2_MaskSearchField.this, true);

			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Übernahme ..."));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			this.oWindow.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			fireActionAgents_ValueChanged(null);
			
		}
	}

	
	
	public XX_Alternative_Result_Button_Generator get_oAlternative_Result_Button_Generator()
	{
		return oAlternative_Result_Button_Generator;
	}



	public void set_oAlternative_Result_Button_Generator(XX_Alternative_Result_Button_Generator alternative_Result_Button_Generator)
	{
		oAlternative_Result_Button_Generator = alternative_Result_Button_Generator;
	}

	
	public void clean(){
		try {
			this._clean();
		} catch (myException e) {

			//e.printStackTrace();
		}
	}

	private class actionEraser extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			MyE2_MaskSearchField.this._clean();
		}
	}


	private  void _clean() throws myException
	{
		MyE2_MaskSearchField.this.oTextFieldForSearchInput.setText("");
		MyE2_MaskSearchField.this.oLabel4Anzeige.set_Text("");
		MyE2_MaskSearchField.this.FillLabelWithDBQuery("");
		fireActionAgents_ValueChanged(null);
		
		//2011-09-12: vergleichsfeld fuellen
		this.cVergleichswertDBFeld = "";
	}


	public MyE2_Button get_oButtonErase()
	{
		return buttonErase;
	}
	
	
	public boolean get_bLabel4AnzeigeStattText4Anzeige()
	{
		return bLabel4AnzeigeStattText4Anzeige;
	}


	public void set_bLabel4AnzeigeStattText4Anzeige(boolean bLabel4AnzeigeStattText4Anzeige) throws myException
	{
		this.bLabel4AnzeigeStattText4Anzeige = bLabel4AnzeigeStattText4Anzeige;
		this.build_Component();
	}

	
	public boolean get_bShowSearchButtonLeft() 
	{
		return bShowSearchButtonLeft;
	}


	public void set_bShowSearchButtonLeft(boolean bShowSearchButtonLeft) throws myException 
	{
		this.bShowSearchButtonLeft = bShowSearchButtonLeft;
		this.build_Component();
	}

	
	//2011-09-09: addon-komponenten
	/**
	 * 
	 * @param oComponent (leert vorher den addon-Vector)
	 * @throws myException
	 */
	public void set_AddOnComponent(MyE2IF__Component oComponent) throws myException
	{
		this.vAddOnComponents.removeAllElements();
		this.vAddOnComponents.add(oComponent);
		
		oComponent.EXT().set_O_PLACE_FOR_EVERYTHING2(this);
		
		this.build_Component();
		
	}

	

	public void add_AddOnComponent(MyE2IF__Component oComponent) throws myException
	{
		this.vAddOnComponents.add(oComponent);
		oComponent.EXT().set_O_PLACE_FOR_EVERYTHING2(this);
		this.build_Component();
	}

	
	private void PutAddOns()
	{
		for (int i=0;i<this.vAddOnComponents.size();i++)
		{
			this.add((Component)this.vAddOnComponents.get(i),this.oInsetsForComponents);
		}
	}
	
	public Vector<MyE2IF__Component> get_vAddOnComponents()
	{
		return vAddOnComponents;
	}


	 

	/**
	 * //2011-09-12: vergleichsfeld:
	 * Gibt info, ob in einem Feld eine eingabe steht, die aus einer suche herruehrt oder manuell eingetippt wurde
	 * @return
	 */
	public boolean get_bValueWasSearched()
	{
		return S.NN(this.get_oTextFieldForSearchInput().getText()).equals(S.NN(this.cVergleichswertDBFeld));
	}
	

	
	public XX_DoSomeThingAfterComponentIsFilled get_oDoSomeThingAfterComponentIsFilled()
	{
		return oDoSomeThingAfterComponentIsFilled;
	}


	public void set_oDoSomeThingAfterComponentIsFilled(XX_DoSomeThingAfterComponentIsFilled oDoSomeThingAfterComponentIsFilled)
	{
		this.oDoSomeThingAfterComponentIsFilled = oDoSomeThingAfterComponentIsFilled;
	}

	
	
	
	public void set_cActualMaskValue(String cText) throws myException
	{
		this.oTextFieldForSearchInput.setText(cText);
		this.cVergleichswertDBFeld = cText;
	}
	
	
	public String get_cActualMaskValue() throws myException
	{
		return this.oTextFieldForSearchInput.getText();
	}

	//kann bei eigenen suchbuttons ueberschrieben werden, damit eine eigene groesse gespeichert werden kann
	public abstract E2_BasicModuleContainer get_ownContainer() throws myException;

	
	
	public String get_c_sql_selectBlock() {
		return c_sql_selectBlock;
	}



	public String get_c_sql_fromBlock() {
		return c_sql_fromBlock;
	}



	public String get_c_sql_orderBlock() {
		return c_sql_orderBlock;
	}



	public String get_c_sql_whereBlockJoiningTables() {
		return c_sql_whereBlockJoiningTables;
	}



	public String get_c_sql_whereBlockForSelecting() {
		return c_sql_whereBlockForSelecting;
	}
	
	
	
	public MutableStyle get_style_4_grid_in_popup() {
		return style_4_grid_in_popup;
	}



	public void set_style_4_grid_in_popup(MutableStyle p_style_4_grid_in_popup) {
		this.style_4_grid_in_popup = p_style_4_grid_in_popup;
	}

	/**
	 * Setzen, ob der Dialog auch erscheinen soll, wenn nur eine Zeile gefunden wurde
	 * @author manfred
	 * @date 12.01.2021
	 *
	 * @param bShowOnSingleRow
	 */
	protected void setShowResultDialogOnSingleRow(boolean bShowOnSingleRow) {
		m_bShowDialogOnSingleResultRow = bShowOnSingleRow;
	}
	

}
