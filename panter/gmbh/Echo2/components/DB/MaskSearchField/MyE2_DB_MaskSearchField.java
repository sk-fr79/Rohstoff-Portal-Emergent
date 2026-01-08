package panter.gmbh.Echo2.components.DB.MaskSearchField;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponent;
import panter.gmbh.BasicInterfaces.IF_ValueChangeListener;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_GridWithLabel;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.E2_GridWithSearchResultbuttons;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Alternative_Result_Button_Generator;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

public abstract class MyE2_DB_MaskSearchField extends MyE2_Row  implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy, IF_ValueChangeListener
{

	private Extent					oPosX = null;
	private Extent					oPosY = null;
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;

	private MyE2_TextField 			oTextFieldForSearchInput = new MyE2_TextField();
	private MyE2_Button				buttonStartSearch	= new MyE2_Button(E2_ResourceIcon.get_RI("suche_mini.png"),E2_ResourceIcon.get_RI("suche_mini__.png"));
	private MyE2_Button				buttonErase	= new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),E2_ResourceIcon.get_RI("leer.png"));
	private boolean   				bShowEraser = false;

	private TextField				oTextForAnzeige = new TextField();
	
	//2011-02-05: Label fuer anzeige moeglich
	private boolean   				bLabel4AnzeigeStattText4Anzeige = false;
	private MyE2_GridWithLabel      oLabel4Anzeige = new MyE2_GridWithLabel(200, MyE2_Grid.STYLE_GRID_DDARK_BORDER());       //mit linewrap


	private String 					cCOMPLETE_SQL_FOR_Label = null;
	

	private XX_SearchBlock			oSeachBlock = null;
	
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
	private XX_MaskActionAfterFound						oMaskActionAfterMaskValueIsFound = null;
	private XX_MaskActionAfterPrepareContentForNew  	oMaskActionAfterPrepareContentForNew = null;
	
	/*
	 * 2018-07-19: weitere XX_MaskActionAfterFound validierer (als Vektor)
	 */
	private VEK<ExtendedSimpleActionAfterFound>     extendedSimpleActionsBeforeResultIsUsed = new VEK<ExtendedSimpleActionAfterFound>();
	private VEK<ExtendedSimpleActionAfterFound>     extendedSimpleActionsAfterResultIsUsed = new VEK<ExtendedSimpleActionAfterFound>();
	
	private Extent					oPopupWidth = new Extent(400);
	private Extent					oPopupHigh = new Extent(500);
	
	/*
	 * parameter definiert, ob das anzeigefeld sichtbar sein soll oder nicht
	 */
	private boolean 				bTextForAnzeigeVisible = true;
	
	/*
	 * ein formatierer fuer die gefundenen buttons
	 */
	private XX_FormaterForFoundButtons	  	oFormatterForButtons = null;
	
	private Insets	  						oInsetsForComponents = new Insets(0,1,1,1);
	
	
	/*
	 * ein validierer-vector, der prueft, ob der suchknopf aufgerufen werden kann
	 */
	private Vector<XX_ActionValidator>  vValidatorsForStartSearch = new Vector<XX_ActionValidator> ();
	
	
	/*
	 * falls der schalter gesetzt wird, dann wird die eingabe nach einer fehlsuche geloescht
	 */
	private boolean 	     		bCleanAfterFoundNothing = false;
	
	
	/*
	 * eine add-on-componente, die von oben vergeben werden kann und zwischen suchfeld und suchbutton eingeblendet wird
	 */
	//private MyE2IF__Component       oAddonComponent = null;
	private Vector<MyE2IF__Component>  vAddOnComponents = new Vector<MyE2IF__Component>();

	
	//Falls eine Anzeige umdefiniert werden muss (z.B. uebersetzt in eine andere Sprache)
	//kann ein Agent dies uebernehmen
	private XX_FormaterFor_oTextForAnzeige oAnzeigeFormatierer = null;
	
	/*
	 * eine variable haelt gefundene und geladene Datenfeldwerte fest und vergleicht diese beim speichern
	 * mit dem inhalt des sucheingabefeldes. Damit wird erzwungen, dass eine suche stattfindet,
	 * einfaches eintippen geht dann nicht mehr
	 */
	private String 				    cVergleichswertDBFeld = "";
	
	


	private XX_Alternative_Result_Button_Generator   oAlternative_Result_Button_Generator = null;
	
	
//	private boolean   b_UseNamedButtons  =  false;
	
	//2011-02-08: ueberschrift ueber die popup-auswahl ermoeglichen	(wirkt nur bei benannten spalten, Hashkey ist der Spaltenname)
	private HashMap<String, Component>    hmTitelzeileAuswahlBlock = new HashMap<String, Component>();
	
	

	//2011-02-11: moeglichkeit, die such-lupe als ersten button anzuzeigen
	private boolean    bShowSearchButtonLeft = false;
	


	//2013-01-18: die Nichts-gefunden-Meldung kann geaendert werden
	private MyE2_String  cMeldungNichtsGefunden = new MyE2_String("Nichts gefunden ...");
	
	
	private VEK<IF_ExecuterOnComponent>  changeListeners = new VEK<>();
	



	public MyE2_String get_cMeldungNichtsGefunden()
	{
		return this.cMeldungNichtsGefunden;
	}



	public void set_cMeldungNichtsGefunden(MyE2_String MeldungNichtsGefunden)
	{
		this.cMeldungNichtsGefunden = MeldungNichtsGefunden;
	}



	/**
	 * @param osqlField
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLOrderBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting  (Suchbedingungen mit #WERT# drin)
	 * @param cSQLqueryForUniqueSearch  (Suchbedingungen mit #WERT# drin oder null)
	 * @param charForUniqueSearch (nur mir cSQLQueryForUniqueSearch sinnvoll)
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components (NULL OK)
	 * @param showEraser 
	 * @param oPane
	 * @throws myException
	 */
	public MyE2_DB_MaskSearchField(	SQLField 		osqlField,
									String 			cSQLSelectBlock, 
									String 			cSQLFromBlock,
									String 			cSQLOrderBlock,
									String 			cSQLWhereBlockJoiningTables,
									String 			cSQLWhereBlockForSelecting,
									String 			cSQLqueryForUniqueSearch,
									String 			charForUniqueSearch,
									String 			cCOMPLETE_SQL_FOR_label, 
									Insets 			INSETS_For_Components, 
									boolean 		showEraser
									) throws myException
	{
		super(new Style_Row_Normal(0, new Insets(0,0,2,0)));
//		if (osqlField == null)
//			throw new myException("MyE2_DB_MaskSearchField:Constructor:null-SQLField not allowed !");

		if ((cSQLqueryForUniqueSearch == null && charForUniqueSearch !=null ) || 
			(cSQLqueryForUniqueSearch != null && charForUniqueSearch ==null ))
			throw new myException("MyE2_DB_MaskSearchField:Constructor:cSQLQueryForUniqueSearch and cCharForUniqueSearch must be both null or not null !");

		if (cSQLqueryForUniqueSearch != null && cSQLqueryForUniqueSearch.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_MaskSearchField:cSQLQueryForUniqueSearch:cSQLQueryForUniqueSearch MUST contain #WERT# !");
		
		
//		if (INSETS_For_Components != null)
//			this.oInsetsForComponents = INSETS_For_Components;
		
		this.cCharForUniqueSearch = charForUniqueSearch;
		this.cSQLQueryForUniqueSearch = cSQLqueryForUniqueSearch;


		this.oSeachBlock = new DBSearchBlockStandard(	cSQLSelectBlock,
														cSQLFromBlock,
														cSQLWhereBlockJoiningTables,
														cSQLWhereBlockForSelecting,
														cSQLOrderBlock,
														this.cSQLQueryForUniqueSearch,
														this.cCharForUniqueSearch,600);
		
		__init_Componentl(	osqlField,
							cCOMPLETE_SQL_FOR_label, 
							INSETS_For_Components, 
							showEraser);
				

	}

	
	


	
	
	
	/**
	 * @param osqlField
	 * @param osearchBlock
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components (NULL OK)
	 * @param showEraser 
	 * @param oPane
	 * @throws myException
	 */
	public MyE2_DB_MaskSearchField(	SQLField 		osqlField,
									XX_SearchBlock	osearchBlock, 
									String 			cCOMPLETE_SQL_FOR_label,
									Insets 			INSETS_For_Components, 
									boolean 		showEraser
									) throws myException
	{
		super(new Style_Row_Normal(0, new Insets(0,0,2,0)));

		this.oSeachBlock = osearchBlock;

		__init_Componentl(	osqlField,
							cCOMPLETE_SQL_FOR_label, 
							INSETS_For_Components, 
							showEraser);
		

	}

	
	
	private void __init_Componentl(	SQLField 		osqlField,
									String 			cCOMPLETE_SQL_FOR_label, 
									Insets 			INSETS_For_Components, 
									boolean 		showEraser
									) throws myException
	{
		if (osqlField == null)
			throw new myException("MyE2_DB_MaskSearchField:Constructor:null-SQLField not allowed !");

		if (INSETS_For_Components != null)
			this.oInsetsForComponents = INSETS_For_Components;

		this.bShowEraser = showEraser;
		
		this.buttonErase.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_DB_MaskSearchField.this.prepare_ContentForNew(false);
			}
		});
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.cCOMPLETE_SQL_FOR_Label = cCOMPLETE_SQL_FOR_label;
		
		if (this.cCOMPLETE_SQL_FOR_Label.indexOf("#WERT#")==-1)
			throw new myException("MyE2_DB_MaskSearchField:Constructor:SQL_QUERY_FOR_LABEL MUST contain #WERT# !");

		
		
		this.buttonStartSearch.add_oActionAgent(new ActionAgentPerformSearch());
		
//		if (this.bLabel4AnzeigeStattText4Anzeige)
//		{
//			this.add(this.oLabel4Anzeige,this.oInsetsForComponents);
//		}
//		else
//		{
//			this.add(this.oTextForAnzeige,this.oInsetsForComponents);
//		}
//		this.add(this.oTextFieldForSearchInput,this.oInsetsForComponents);
//		if (this.bShowEraser)
//		{
//			this.add(this.buttonErase,this.oInsetsForComponents);
//		}
//		this.add(this.buttonStartSearch,this.oInsetsForComponents);

		this.build_Component();
		
		this.oTextForAnzeige.setStyle(MyE2_DB_MaskSearchField.StyleSearchField());
		this.oTextForAnzeige.setEnabled(false);
		
		this.oLabel4Anzeige.get_oLabel().setStyle(MyE2_DB_MaskSearchField.StyleLabel4Anzeige());
		
		this.oTextFieldForSearchInput.setWidth(new Extent(100));
		this.oTextFieldForSearchInput.setForeground(new E2_ColorGray(160));

		
		this.oTextForAnzeige.setWidth(new Extent(200));
		this.oLabel4Anzeige.setWidth(new Extent(200));
		
		this.buttonErase.setToolTipText(new MyE2_String("Alle Werte im Suchfeld LEEREN ").CTrans());
		this.buttonStartSearch.setToolTipText(new MyE2_String("Suche starten ").CTrans());

		this.EXT().add_FieldSetters_AND_Validator__BeforeReadInputMAP(
				new XX_FieldSetter_AND_Validator()
				{
					@Override
					public MyE2_MessageVector isValid(String cstatus_map,MyE2EXT__Component EXT_own) throws myException
					{
						MyE2_DB_MaskSearchField oThis =  MyE2_DB_MaskSearchField.this;
						MyE2_MessageVector oMV = new MyE2_MessageVector();
						if (!S.NN(oThis.cVergleichswertDBFeld).equals(oThis.oTextFieldForSearchInput.getText()))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feldeingabe wurde nicht über die Suchfunktion gemacht: SUCHFELD: ",true,oThis.EXT_DB().get_oSQLField().get_cFieldLabelForUser().CTrans(),false)));
						}
						return oMV;
					}
					
				});

		
	}
	
	
	
	
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


	
	
	private void build_Component() throws myException
	{
		this.removeAll();
		if (this.bTextForAnzeigeVisible)
		{
			if (this.bLabel4AnzeigeStattText4Anzeige)
			{
				this.add(this.oLabel4Anzeige,this.oInsetsForComponents);
			}
			else
			{
				this.add(this.oTextForAnzeige,this.oInsetsForComponents);
			}
		}
		
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
						MyE2_DB_MaskSearchField.this.EXT().RESET_ALLE_SchalterAuf_TRUE();
					}
				},true);
			}
		}
		
	}
	
	
	private void PutAddOns()
	{
		for (int i=0;i<this.vAddOnComponents.size();i++)
		{
			this.add((Component)this.vAddOnComponents.get(i),this.oInsetsForComponents);
		}
	}
	
	
	
	public void set_bTextForAnzeigeVisible(boolean bVisible)
	{
		this.removeAll();
		this.bTextForAnzeigeVisible = bVisible;
		if (bVisible)
		{
			if (this.bLabel4AnzeigeStattText4Anzeige)
			{
				this.add(this.oLabel4Anzeige,this.oInsetsForComponents);
			}
			else
			{
				this.add(this.oTextForAnzeige,this.oInsetsForComponents);
			}
		}
		this.add(this.oTextFieldForSearchInput,this.oInsetsForComponents);
		
		this.PutAddOns();
		
		if (this.bShowEraser)
		{
			this.add(this.buttonErase,this.oInsetsForComponents);
		}

		this.add(this.buttonStartSearch,this.oInsetsForComponents);
	}
	
	
	
	public void prepare_ContentForNew(boolean bSetDefaults) throws myException
	{
		if (!bSetDefaults)
		{
			this.oTextFieldForSearchInput.setText("");
			this.cVergleichswertDBFeld = "";
			this.oTextForAnzeige.setText("");
			this.oTextForAnzeige.setToolTipText("");
			this.oLabel4Anzeige.set_Text("");
			this.oLabel4Anzeige.get_oLabel().setToolTipText("");
			
			
			if (this.oMaskActionAfterPrepareContentForNew != null) {
				this.oMaskActionAfterPrepareContentForNew.doMaskSettingsAfterPrepareContentForNew(this, bSetDefaults);
			}
			
			
			
			return;
		}
		
		String cText = "";
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		this.oTextFieldForSearchInput.setText(cText);
		this.cVergleichswertDBFeld = cText;
		this.oTextForAnzeige.setText("");
		this.oTextForAnzeige.setToolTipText("");
		this.oLabel4Anzeige.set_Text("");
		this.oLabel4Anzeige.get_oLabel().setToolTipText("");

		
		if (this.oMaskActionAfterMaskValueIsFound != null)
			this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField("", this, false, true);

		if (this.oMaskActionAfterPrepareContentForNew != null)
			this.oMaskActionAfterPrepareContentForNew.doMaskSettingsAfterPrepareContentForNew(this, bSetDefaults);

		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

		if (this.changeListeners.size()>0) {
			for (IF_ExecuterOnComponent e:  this.changeListeners) {
				e.execute(this);
			}
		}
		
	}

	public String get_cActualMaskValue() throws myException
	{
		return this.oTextFieldForSearchInput.getText();
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return this.oTextFieldForSearchInput.getText();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		this.oTextFieldForSearchInput.setText(cText);
		this.cVergleichswertDBFeld = cText;
		
		if (this.changeListeners.size()>0) {
			for (IF_ExecuterOnComponent e:  this.changeListeners) {
				e.execute(this);
			}
		}

	}

	/**
	 * @param cText
	 * @param bFillLabel
	 * @param bPerform_FollowingAction wenn true, dann wird doMaskSettingsAfterValueWrittenInMaskField aufgerufen 
	 * @throws myException
	 */
	public void set_cActualMaskValue(String cText,boolean bFillLabel, boolean bPerform_FollowingAction, boolean bPrimaryCall) throws myException
	{
		this.oTextFieldForSearchInput.setText(cText);
		this.cVergleichswertDBFeld = cText;

		if (bFillLabel)
		{
			this.FillLabelWithDBQuery(cText);
		}
		if (bPerform_FollowingAction)
		{
			if (this.oMaskActionAfterMaskValueIsFound != null)
				this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(cText, this, true, bPrimaryCall);

		}
		
		
		if (this.changeListeners.size()>0) {
			for (IF_ExecuterOnComponent e:  this.changeListeners) {
				e.execute(this);
			}
		}

		
	}
	
	
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_TextField:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		this.set_cActualMaskValue(cText);
		
		String cUnformatedValue = this.EXT_DB().get_oSQLField().get_cUnformated_ValueString_For_InternalUse(cText,false);
		this.FillLabelWithDBQuery(cUnformatedValue);

		if (this.oMaskActionAfterMaskValueIsFound != null)
			this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(cUnformatedValue, this, false, false);
		
		
		if (this.changeListeners.size()>0) {
			for (IF_ExecuterOnComponent e:  this.changeListeners) {
				e.execute(this);
			}
		}

	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return this.bIsComplexObject;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy(myExceptionCopy.ERROR_COPY_NOT_IMPLEMENTED);
	}
	
	
	
	public void set_FormatterForButtons(XX_FormaterForFoundButtons formatterForButtons)
	{
		this.oFormatterForButtons = formatterForButtons;
	}


	
	
	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextFieldForSearchInput.setStyle(
				this.oTextFieldForSearchInput.EXT().get_STYLE_FACTORY().get_Style(this.oTextFieldForSearchInput.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK)
				);
	}

	
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException
	{
		boolean enabled = Enabled;
		
		enabled = enabled && this.EXT().is_ValidEnableAlowed();
		enabled = enabled && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.oTextFieldForSearchInput.set_bEnabled_For_Edit(enabled);
		this.buttonStartSearch.set_bEnabled_For_Edit(enabled);
		this.buttonErase.set_bEnabled_For_Edit(enabled);
		this.oTextFieldForSearchInput.setStyle(this.oTextFieldForSearchInput.EXT().get_STYLE_FACTORY().get_Style(enabled,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
		
		for (int i=0;i<this.vAddOnComponents.size();i++)
		{
			this.vAddOnComponents.get(i).set_bEnabled_For_Edit(enabled);
		}
	}

	
	public void FillLabelWithDBQuery(String ValueForDB) throws myException
	{
		// zuerst die uebergabe eines leeren wertes abfangen
		if (bibALL.isEmpty(ValueForDB))
		{
			this.oTextForAnzeige.setText("");
			this.oTextForAnzeige.setToolTipText("");
			
			this.oLabel4Anzeige.set_Text("");
			this.oLabel4Anzeige.get_oLabel().setToolTipText("");

			return;
		}
		
		String cQuery = bibALL.ReplaceTeilString(this.cCOMPLETE_SQL_FOR_Label,"#WERT#",ValueForDB);
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cQuery);
		
		if (cErgebnis == null || cErgebnis.length!=1)
			throw new myException("MyE2_MaskSearchField:FillLabelWithDBQuery:Error Filling Field "+this.oEXTDB.get_oSQLField().get_cFieldName()+": "+cQuery);
		
		this.oTextForAnzeige.setText(cErgebnis[0][0]);
		this.oTextForAnzeige.setToolTipText(cErgebnis[0][0]);
		
		this.oLabel4Anzeige.set_Text(cErgebnis[0][0]);
		this.oLabel4Anzeige.get_oLabel().setToolTipText(cErgebnis[0][0]);

		
		if (this.oAnzeigeFormatierer != null)
			this.oAnzeigeFormatierer.format_Anzeige(this,ValueForDB);
		
	}
	

	
	public MyE2_Button get_buttonStartSearch()											{		return buttonStartSearch;					}
	public MyE2_Button get_buttonErase()												{		return buttonErase;					}
	public TextField get_oTextForAnzeige()												{		return oTextForAnzeige;					}
	public MyE2_TextField get_oTextFieldForSearchInput()								{		return oTextFieldForSearchInput;			}
	public Extent get_oPosX()															{		return oPosX;								}
	public void set_oPosX(Extent posX)													{		oPosX = posX;								}
	public Extent get_oPosY()															{		return oPosY;								}
	public void set_oPosY(Extent posY)													{		oPosY = posY;								}
	public XX_MaskActionAfterFound 	get_oMaskActionAfterMaskValueIsFound()				{		return oMaskActionAfterMaskValueIsFound;	}
	public void  set_oMaskActionAfterMaskValueIsFound(XX_MaskActionAfterFound action)	{		oMaskActionAfterMaskValueIsFound=action;	}
	public XX_SearchBlock get_oSeachBlock()												{		return oSeachBlock;							}
	public void set_oSeachBlock(XX_SearchBlock seachBlock)								{		oSeachBlock = seachBlock;					}
	public void set_oPopupHigh(Extent popupHigh) 										{		oPopupHigh = popupHigh;	}
	public void set_oPopupWidth(Extent popupWidth) 										{		oPopupWidth = popupWidth;	}

	
	public boolean get_bTextForAnzeigeVisible()
	{
		return this.bTextForAnzeigeVisible;
	}



	
	public void add_ValidatorStartSearch(XX_ActionValidator oValidator)
	{
		this.vValidatorsForStartSearch.add(oValidator);
	}
	
	public void add_ValidatorStartSearch(Vector<XX_ActionValidator>  vValidator)
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
	
	
//	
//	/**
//	 * @param cUnformatedValue
//	 * @param AfterActionFlag
//	 * @throws myException
//	 * erlaubt den AfterFoundAgenten von aussen zu starten
//	 */
//	public void perform_XX_MaskActionAfterFound(String cUnformatedValue,boolean AfterActionFlag) throws myException
//	{
//		if (this.oMaskActionAfterMaskValueIsFound != null)
//			this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(cUnformatedValue, this, AfterActionFlag);
//
//	}
//	


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
	

	private class ActionAgentPerformSearch extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_DB_MaskSearchField oThis = MyE2_DB_MaskSearchField.this;
			
			Insets   oIN = new Insets(2,2,2,0);
			GridLayoutData  GLHead = LayoutDataFactory.get_GL_Copy(MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
			GridLayoutData  GLNormal = LayoutDataFactory.get_GL_Copy(MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
			
			GLHead.setBackground(new E2_ColorDDark());
			
			
			
			//alle Zustaende auf true
			oThis.EXT().RESET_ALLE_SchalterAuf_TRUE();
			//--------------------------------------
			
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
			
				if (MyE2_DB_MaskSearchField.this.oSeachBlock==null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("MyE2_DB_MaskSearch:ActionAgentPerformSearch:No SearchBlock-class is defined !!",false),false);
				}
				
				if (bibMSG.get_bIsOK())
				{
				
					String cSearchInputText = MyE2_DB_MaskSearchField.this.oTextFieldForSearchInput.getText();
					/*
					 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit auch formatierte IDs gefunden werden
					 */
					String cTextTest = bibALL.ReplaceTeilString(cSearchInputText,".","");
					if (bibALL.isInteger(cTextTest))
						cSearchInputText=cTextTest;
					
					try
					{
						// zuerst den spezialfall: eingabefeld ist leer, aber der cVergleichswertDBFeld ist voll,
						// das heist: es war bereits mal was gesucht und gefunden, und wird jetzt leergemacht
						if (S.isEmpty(cSearchInputText) && S.isFull(oThis.cVergleichswertDBFeld))
						{
							oThis.prepare_ContentForNew(false);
							return;
						}
						
						Vector<XX_Button4SearchResultList[]> vButtons = new Vector<XX_Button4SearchResultList[]>();;
						boolean bStartSearchClassic = true;
						
						if (S.isEmpty(cSearchInputText) && oThis.get_oAlternative_Result_Button_Generator()!=null)
						{
							if (oThis.get_oAlternative_Result_Button_Generator().get_bUseAlternative())
							{
								vButtons = oThis.get_oAlternative_Result_Button_Generator().get_vResultButtons();
								bStartSearchClassic = false;
							}
						}
						
						if (bStartSearchClassic)
						{
							vButtons = MyE2_DB_MaskSearchField.this.oSeachBlock.get_vResultButtons(cSearchInputText);
						}
						
						if (oThis.oFormatterForButtons != null)
							oThis.oFormatterForButtons.RESET();
						
						
						if (vButtons.size()==0)
						{
//							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Nichts gefunden ..."),false);
							//2013-01-18: Definierbare Meldung "nichts gefunden"
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(MyE2_DB_MaskSearchField.this.cMeldungNichtsGefunden),false);
							if (oThis.bCleanAfterFoundNothing)
							{
								oThis.prepare_ContentForNew(false);
							}
							else
							{
								// wenn eine nummer im feld eingetragen war, dann muss diese sofort im falle nichtfindens geloescht werden
								if (oThis.EXT().get_oComponentMAP().get_LActualDBValue(oThis.EXT_DB().get_oSQLField().get_cFieldLabel(), true,true, new Long(-1)).longValue()>=0)
								{
									oThis.prepare_ContentForNew(false);
								}
							}
						}
						else if (vButtons.size()==1)
						{
							MyE2_Button oButt = vButtons.get(0)[0];

							//20180719: weitere performSearchActions
							for (ExtendedSimpleActionAfterFound action: MyE2_DB_MaskSearchField.this.extendedSimpleActionsBeforeResultIsUsed) {
								bibMSG.MV()._add(action.doExtendedSearchAction(oButt.EXT().get_C_MERKMAL(),MyE2_DB_MaskSearchField.this));
							}
							
							if (bibMSG.get_bIsOK()) {
							
								// volltreffer
								MyE2_DB_MaskSearchField.this.oTextFieldForSearchInput.setText(oButt.EXT().get_C_MERKMAL());
								MyE2_DB_MaskSearchField.this.cVergleichswertDBFeld = oButt.EXT().get_C_MERKMAL();
								MyE2_DB_MaskSearchField.this.FillLabelWithDBQuery(oButt.EXT().get_C_MERKMAL());
								if (MyE2_DB_MaskSearchField.this.oMaskActionAfterMaskValueIsFound != null) {
									MyE2_DB_MaskSearchField.this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(oButt.EXT().get_C_MERKMAL(), MyE2_DB_MaskSearchField.this, true, true);
								}
								
								//20180719: weitere performSearchActions
								for (ExtendedSimpleActionAfterFound action: MyE2_DB_MaskSearchField.this.extendedSimpleActionsAfterResultIsUsed) {
									bibMSG.MV()._add(action.doExtendedSearchAction(oButt.EXT().get_C_MERKMAL(),MyE2_DB_MaskSearchField.this));
								}
							
								if (bibMSG.get_bIsOK()) {
									bibMSG.add_MESSAGE(new MyE2_Info_Message("Genau ein Datensatz gefunden und geladen !"),false);
								}
								
								if (changeListeners.size()>0) {
									for (IF_ExecuterOnComponent e:  changeListeners) {
										e.execute(MyE2_DB_MaskSearchField.this);
									}
								}
								
							}
						}
						else
						{
//							if (oThis.oSeachBlock.get_ContainerWithFoundButtons()==null)
//							{
							
								
//								//nachsehen, ob es eine benannte abfrage ist, ob es eine Titelzeile gibt
//								if (oThis.b_UseNamedButtons)
//								{
//									if (oThis.hmTitelzeileAuswahlBlock.size()>0)
//									{
//										//es wird die Zeile 1 genommen, um die namen festzustellen
//										MyE2_Button[] arrayZeile1 = vButtons.get(0);
//										for (int i=0;i<arrayZeile1.length;i++)
//										{
//											Component  oComp = new MyE2_Label("");
//											
//											String cHashKey =  arrayZeile1[i].EXT().get_C_MERKMAL2();
//											
//											if (oThis.hmTitelzeileAuswahlBlock.get(cHashKey)!=null)
//											{
//												oComp = oThis.hmTitelzeileAuswahlBlock.get(cHashKey);
//											}
//											oGrid_4_Selection.add(oComp, GLHead);
//										}
//										
//									}
//								}
								
								E2_BasicModuleContainer oWindow = oThis.oSeachBlock.get_ContainerForShowResults();
								
								E2_BasicModuleContainer oWindow2 = oThis.get_ownContainer();   //eigene container-klasse zum abspeichern von weite/hoehe
								if (oWindow2!=null)
								{
									oWindow = oWindow2;
								}
								
								oWindow.set_oExtLeftPos(MyE2_DB_MaskSearchField.this.oPosX);
								oWindow.set_oExtTopPos(MyE2_DB_MaskSearchField.this.oPosY);
								
								actionAgentForSelectResult oActionKlick = new actionAgentForSelectResult(oWindow);
	
								//neu ab 2011-09-26:								
								E2_GridWithSearchResultbuttons oGrid_4_Selection = 
									new E2_GridWithSearchResultbuttons(
											vButtons, null, 
											oActionKlick, 
											oThis.oFormatterForButtons,
											oThis.get_oSeachBlock().get_hmSortierInfo(),
											oThis.get_oSeachBlock().get_bResultListIsSortable(),
											oThis);
								
								//Ende neu ab 2011-09-26:								

								
//								MyE2_Grid oGrid_4_Selection = new MyE2_Grid(vButtons.get(0).length,MyE2_Grid.STYLE_GRID_NO_BORDER());
//
//								for (int i=0;i<vButtons.size();i++)
//								{
//									if (oThis.oFormatterForButtons != null)
//										oThis.oFormatterForButtons.RESET_ROW(vButtons.get(i));
//
//									
//									for (int k=0;k<vButtons.get(i).length;k++)
//									{
//										vButtons.get(i)[k].add_oActionAgent(oActionKlick);
//										
//										oGrid_4_Selection.add(vButtons.get(i)[k],GLNormal);
//										if (oThis.oFormatterForButtons != null)
//										{
//											oThis.oFormatterForButtons.DO_Format(vButtons.get(i)[k]);
//										}
//									}
//								}
								oWindow.add(oGrid_4_Selection);
								oWindow.CREATE_AND_SHOW_POPUPWINDOW(MyE2_DB_MaskSearchField.this.oPopupWidth,
																	MyE2_DB_MaskSearchField.this.oPopupHigh,
																	new MyE2_String("Bitte wählen Sie eine Ergebnis-Zeile !"));
//							}
//							else
//							{
//								E2_BasicModuleContainer oWindow = oThis.oSeachBlock.get_ContainerForShowResults();
//								
//								E2_BasicModuleContainer oWindow2 = oThis.get_ownContainer();   //eigene container-klasse zum abspeichern von weite/hoehe
//								if (oWindow2!=null)
//								{
//									oWindow = oWindow2;
//								}
//
//								
//								actionAgentForSelectResult oActionKlick = new actionAgentForSelectResult(oWindow);
//								
//								for (int i=0;i<vButtons.size();i++)
//								{
//									for (int k=0;k<vButtons.get(i).length;k++)
//									{
//										vButtons.get(i)[k].add_oActionAgent(oActionKlick);
//										if (oThis.oFormatterForButtons != null)
//											oThis.oFormatterForButtons.DO_Format(vButtons.get(i)[k]);
//									}
//								}
//								
//								oWindow.add(oThis.oSeachBlock.get_ContainerWithFoundButtons());
//								oWindow.CREATE_AND_SHOW_POPUPWINDOW(MyE2_DB_MaskSearchField.this.oPopupWidth,
//																	MyE2_DB_MaskSearchField.this.oPopupHigh,
//																	new MyE2_String("Bitte wählen Sie eine Ergebnis-Zeile !"));
//								
//							}
						}
					}
					catch (myException ex)
					{
						ex.printStackTrace();
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
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
			MyE2_DB_MaskSearchField.this.oTextFieldForSearchInput.setText(oButt.EXT().get_C_MERKMAL());
			MyE2_DB_MaskSearchField.this.cVergleichswertDBFeld = oButt.EXT().get_C_MERKMAL();

			try
			{
				//20180719: weitere performSearchActions
				for (ExtendedSimpleActionAfterFound action: MyE2_DB_MaskSearchField.this.extendedSimpleActionsBeforeResultIsUsed) {
					bibMSG.MV()._add(action.doExtendedSearchAction(oButt.EXT().get_C_MERKMAL(),MyE2_DB_MaskSearchField.this));
				}
				
				if (bibMSG.get_bIsOK()) {
				
					MyE2_DB_MaskSearchField.this.FillLabelWithDBQuery(oButt.EXT().get_C_MERKMAL());
					if (MyE2_DB_MaskSearchField.this.oMaskActionAfterMaskValueIsFound != null) {
						MyE2_DB_MaskSearchField.this.oMaskActionAfterMaskValueIsFound.doMaskSettingsAfterValueWrittenInMaskField(oButt.EXT().get_C_MERKMAL(), MyE2_DB_MaskSearchField.this, true, true);
					}
	
					//20180719: weitere performSearchActions
					for (ExtendedSimpleActionAfterFound action: MyE2_DB_MaskSearchField.this.extendedSimpleActionsAfterResultIsUsed) {
						bibMSG.MV()._add(action.doExtendedSearchAction(oButt.EXT().get_C_MERKMAL(),MyE2_DB_MaskSearchField.this));
					}
					
					
					
					if (changeListeners.size()>0) {
						for (IF_ExecuterOnComponent e:  changeListeners) {
							e.execute(MyE2_DB_MaskSearchField.this);
						}
					}

				}
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Übernahme ..."));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			this.oWindow.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			
		}
	}
	
	
	public boolean get_bCleanAfterFoundNothing() 
	{
		return bCleanAfterFoundNothing;
	}


	public void set_bCleanAfterFoundNothing(boolean cleanAfterFoundNothing) 
	{
		bCleanAfterFoundNothing = cleanAfterFoundNothing;
	}


	public void set_oAnzeigeFormatierer(XX_FormaterFor_oTextForAnzeige anzeigeFormatierer) 
	{
		this.oAnzeigeFormatierer = anzeigeFormatierer;
	}

	
	public class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainer()
		{
			super();
		}
		
	}


	public String get_cCOMPLETE_SQL_FOR_Label()
	{
		return cCOMPLETE_SQL_FOR_Label;
	}
	
	
	public XX_Alternative_Result_Button_Generator get_oAlternative_Result_Button_Generator()
	{
		return oAlternative_Result_Button_Generator;
	}



	public void set_oAlternative_Result_Button_Generator(XX_Alternative_Result_Button_Generator alternative_Result_Button_Generator)
	{
		oAlternative_Result_Button_Generator = alternative_Result_Button_Generator;
	}



	public Vector<MyE2IF__Component> get_vAddOnComponents()
	{
		return vAddOnComponents;
	}

	
	public String get_VergleichswertDBFeld() 
	{
		return cVergleichswertDBFeld;
	}

	public XX_MaskActionAfterPrepareContentForNew get_oMaskActionAfterPrepareContentForNew() 
	{
		return oMaskActionAfterPrepareContentForNew;
	}

	public void set_oMaskActionAfterPrepareContentForNew(XX_MaskActionAfterPrepareContentForNew oMaskActionAfterPrepareContentForNew) 
	{
		this.oMaskActionAfterPrepareContentForNew = oMaskActionAfterPrepareContentForNew;
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


	public MyE2_GridWithLabel get_oLabel4Anzeige()
	{
		return oLabel4Anzeige;
	}

	public HashMap<String, Component> get_hmTitelzeileAuswahlBlock() 
	{
		return hmTitelzeileAuswahlBlock;
	}



	/**
	 * 
	 * @param hmTitelzeileAuswahlBlock (wirkt nur bei benannten spalten, Hashkey ist der Spaltenname) 
	 */
	public void set_hmTitelzeileAuswahlBlock(HashMap<String, Component> hmTitelzeileAuswahlBlock) 
	{
		this.hmTitelzeileAuswahlBlock = hmTitelzeileAuswahlBlock;
	}

//	//kann bei eigenen suchbuttons ueberschrieben werden, damit eine eigene groesse gespeichert werden kann
//	public E2_BasicModuleContainer get_ownContainer() throws myException
//	{
//		return null;
//	}
	
	//kann bei eigenen suchbuttons ueberschrieben werden, damit eine eigene groesse gespeichert werden kann
	public abstract E2_BasicModuleContainer get_ownContainer() throws myException;


	public boolean get_bShowSearchButtonLeft() 
	{
		return bShowSearchButtonLeft;
	}


	public void set_bShowSearchButtonLeft(boolean bShowSearchButtonLeft) throws myException 
	{
		this.bShowSearchButtonLeft = bShowSearchButtonLeft;
		this.build_Component();
	}



	/**
	 * 2018-07-19: weitere actions after search
	 * @author martin
	 *
	 */
	public VEK<ExtendedSimpleActionAfterFound> getExtendedSimpleActionsAfterResultIsUsed() {
		return extendedSimpleActionsAfterResultIsUsed;
	}

	/**
	 * 2018-07-19: weitere actions after search
	 * @author martin
	 *
	 */
	public VEK<ExtendedSimpleActionAfterFound> getExtendedSimpleActionsBeforeResultIsUsed() {
		return extendedSimpleActionsBeforeResultIsUsed;
	}


	/**
	 * 2018-07-19: weitere actions after search
	 * @author martin
	 *
	 */
	public static interface ExtendedSimpleActionAfterFound {
		public MyE2_MessageVector doExtendedSearchAction(String dbValueFound, MyE2_DB_MaskSearchField maskSearchField);
	}
	
	
	

	//2019-11-05: martin: querystring fuer den label getter und setter
	public String getCOMPLETE_SQL_FOR_Label() {
		return cCOMPLETE_SQL_FOR_Label;
	}

	public MyE2_DB_MaskSearchField _setCOMPLETE_SQL_FOR_Label(String cCOMPLETE_SQL_FOR_Label) {
		this.cCOMPLETE_SQL_FOR_Label = cCOMPLETE_SQL_FOR_Label;
		return this;
	}

	public VEK<IF_ExecuterOnComponent> getChangeListeners() {
		return changeListeners;
	}

	public IF_ValueChangeListener  _addValueChangeListener(IF_ExecuterOnComponent executerOnChange) {
		changeListeners._a(executerOnChange);
		return this;
	}

	public MyE2_GridWithLabel getGridForShowResults() {
		return oLabel4Anzeige;
	}

	
	//returns actual longValue from input field
	public Long getLongValue() {
		Long l = null;
		
		MyLong ml = new MyLong(this.oTextFieldForSearchInput.getText());
		if (ml.isOK()) {
			return ml.get_oLong();
		}
		return l;
	}
	
	
}
