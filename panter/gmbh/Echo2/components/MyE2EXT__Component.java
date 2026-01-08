package panter.gmbh.Echo2.components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldValidator_InputAllowed;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2IF__BelongsToNavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NaviListCol_Formater;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.DB.MyE2IF__ComponentContainer;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



public class MyE2EXT__Component implements E2_IF_Copy, Serializable
{
	private Integer 			iMERKMAL = null;
	private String 				cMERKMAL	= null;
	private String 				cMERKMAL2	= null;
	private String 				cMERKMAL3	= null;
	private String 				cEVENT_KENNUNG	= "";
	private Object 				EVERY_OBJECT	= null;
	private Object 				EVERY_OBJECT2	= null;
	private String 				cHASHKEY = null;
	
	//neues feld fuer usersettings
	private String    			cHashKey4UserSetting = null;
	
	//2012-01-02: jede komponente kann mit verschiedenen searchtags markiert werden und dann via
	//            recursiv-suche in der objekthirarchie gefunden werden
	private Vector<E2_SEARCH_TAGS.SEARCH_TAGS>      vSEARCH_TAGS = new Vector<E2_SEARCH_TAGS.SEARCH_TAGS>();
	



	/*
	 * jede komponente enthaelt einen verweis auf die ComponentMAP, bei der sie mitglied ist 
	 */
	private E2_ComponentMAP		oComponentMAP = null;

	/*
	 * falls die komponente in einen automatisch erzeugten module eine beschriftung
	 * braucht, dann wird diese hier hinterlegt
	 */
	private MyString 			cList_or_Mask_Titel = null; 

	
	/*
	 * bei benutzung in einer liste wird beim ersten aufruf eine komponente
	 * erzeugt, die in der listenueberschrift benutzt wird. 
	 * um die sortierung / hoch / runter vorhalten zu koennen,
	 * muss diese titelkomponente beibehalten werden. sie wird
	 * in der ur- E2_ComponentMAP gespeichert 
	 * 
	 */
	private Component		oCompTitleInList = null;

	
	/*
	 * schalter, der es moeglich macht, einzelne spalten aus einer liste auszublenden
	 */
	private boolean			bIsVisibleInList = true;
	
	
	
	/*
	 * fuer die listen wird eine column-width extent benoetigt
	 * diese ist standard null
	 */
	private Extent			oColExtent = null;
	
	
	/*
	 * Style-factory-klasse, 
	 * definiert die styles der felder in unterschiedlichen zustaenden:
	 * normal, MUSS-Feld, fehleingabe und kombinationen
	 */
	private XXX_StyleFactory STYLE_FACTORY = null;
	
	
	
	/*
	 * Vector fuer validatoren, ob eine maskenkomponente enabled werden darf (z.B. offen nur fuer Admins)
	 */
	private Vector<XX_FieldValidator_InputAllowed> 		vValidatorsEnabledAllowd = new Vector<XX_FieldValidator_InputAllowed>();

	
	/*
	 * Vector fuer pruefagenten, die feldsetting und pruefungen vornehmen, die vor dem auslesen der InputMAP gemacht werden
	 */
	private Vector<XX_FieldSetter_AND_Validator> 	  vField_Setters_AND_Validators___BeforeReadInputMAP = new Vector<XX_FieldSetter_AND_Validator>();

	
	
	/*
	 * zusaetzliche validierer, die die eingabe pruefen und 
     * gegebenfalls als falsch = gelb markiere (z.b. zahl die nicht groesser als 100 sein darf wird dann auch gelb, genau wie eine texteingabe in einem zahlenfeld
	 */
	private Vector<XX_FieldSetter_AND_Validator> 	  vField_Validator_Check_Input_and_MarkFalseValues = new Vector<XX_FieldSetter_AND_Validator>();

	
	/*
	 * Vector fuer pruefagenten, die feldsetting und pruefungen vornehmen, die nach dem auslesen der InputMAP gemacht werden
	 */
	private Vector<XX_FieldSetter_AND_Validator>      vField_Setters_AND_Validators__AfterReadInputMAP = new Vector<XX_FieldSetter_AND_Validator>();
	
	
	
	/*
	 * LayoutData fuer ein layout, das in listen das layout von titelzeilen definiert (z.b.
	 * ausrichtung in listenkopfzeile
	 */
	private LayoutData				oLayout_ListTitelElement = null;
	
	
	/*
	 * LayoutData fuer ein layout, das in listen das layout von listenelementen definiert (z.b.
	 * ausrichtung in liste
	 */
	private LayoutData				oLayout_ListElement = null;
	
	
	/*
	 * StaticStyle enabled und disabled
	 */
	private E2_MutableStyle        	oE2_Style = null;
	
	
	
	/*
	 * Referenz auf das die eigene komponente beinhaltende E2_BasicModuleContainer - Object.
	 * Wird benoetigt fuer Popup-Anzeigen von BasicModulContainern, mit einer separaten Split-Darstellung.
	 * Dort sind elemente nicht innerhalb des E2_BasicModuleContainer angeordnet und koennen so auch nicht
	 * fuer MessageAgents usw benutzt werden
	 */
	private E2_BasicModuleContainer    oBasicModulContainerThisBelongsTo = null;
	
	
	
	/*
	 * separate String der es erlaubt, eine komponente noch fuer bestimmte maskenzustaende zu dekaktivieren
	 * z.B. eine komponente ist nur bei neueingabe erlaubt, aber nicht bei editieren
	 * Wird als string im Format YNYYYY definiert, wobei die positionen den Typen in der folgenden reihenfolge entsprechen
	 *  public static final String 		STATUS_NEW_EMPTY = 	"STATUS_NEW_EMPY_E2_ComponentMAP";
	 * 	public static final String 		STATUS_NEW_COPY = 	"STATUS_NEW_COPY_E2_ComponentMAP";
	 *	public static final String 		STATUS_EDIT = 		"STATUS_EDIT_E2_ComponentMAP";
	 *  public static final String 		STATUS_VIEW = 		"STATUS_VIEW_E2_ComponentMAP";
	 *	public static final String 		STATUS_UNDEFINED = 	"STATUS_STATUS_UNDEFINED_E2_ComponentMAP";
	 */	
	private String AllowedInMaskCODE = "YYYYY";                 // standardmaessig ueberall erlaubt
	
	

	
	private boolean 			bDisabledFromBasic = false;			// wenn in der rufenden einheit disabled wird
	private boolean 			bDisabledFromInteractive = false;		// wenn durch interaktion disabled wird
	

	private MyE2IF__Component   oComponentThisBelongsTo = null;
	
	
	
	public boolean 				get_bDisabledFromInteractive()										{		return bDisabledFromInteractive;	}
	public void 				set_bDisabledFromInteractive(boolean disabledFromInteractive)		{		bDisabledFromInteractive = disabledFromInteractive;	}
	public boolean 				get_bDisabledFromBasic()												{		return bDisabledFromBasic;	}
	public void 				set_bDisabledFromBasic(boolean disabledFromBasic)					{		bDisabledFromBasic = disabledFromBasic;	}

	
	
	private boolean    			bHasFocusOnWindowPopup = false;
	
	
	/*
	 * Hashmap.um boolesche zustaende zu definieren zu definieren. 
	 * Diese koennen in den Action-Modulen ausgewertet werden und muessen vordefiniert werden (sonst 
	 * kommt bei der abfrage eine Exception hoch).
	 * Es gibt 3 Methoden:
	 * INIT_ZustandsVariable(String);  //der variablenwert ist true
	 * GET_Zustand(String);            //der boolesche Wert wird ausgelesen und wieder auf True gesetzt
	 * SET_Zustand(String,boolean)     //der boolesche Wert wird auf den Uebergabewert gesetzt 
	 * 
	 */
	private     HashMap<String, Boolean>		oHashZustaende = new HashMap<String, Boolean>();
	
	
	
	
	/*
	 * Extender fuer innerhalb von komponenten liegende popup-fenster, damit die position eindeutig gehalten werden kann
	 */
	private  	String    						MASK_SAVE_TAG  = null; 

	
	private 	MyE2_String   					cStringForListHeaderToolTips = null;
	private 	MutableStyle					oStyle_4_ListHeaderComponent = null;
	
	/*
	 * 2011-06-06: weiteres feld, dass definiert, ob das objekt zu
	 * einem MyE2IF__ComponentContainer
	 * gehoert
	 */
	private   MyE2IF__ComponentContainer        oColumnComponentContainerThisBelongsTo = null; 
	
	
	//2012-12-06: schalter, der definiert, ob die listenueberschriften (falls listenobjekt) mit zeilenumbruch dargestellt werden koennen
	private  boolean                            bLineWrapListHeader = false;     //standard=falsch
	
	
	//2014-08-29: weitere komponenten in den spaltentitel platzieren (am anfang: summationsbutton)
	private  Vector<E2IF__BelongsToNavigationList>       	v_ZusatzKomponentenInListenTitel = new Vector<E2IF__BelongsToNavigationList>();
	
	
	//2014-11-03: UUIDs erzeugen
	private UUID     							UUID = null;
	
	
	//2015-02-27: fuer das validierungsframework werden RB_MASK_FIELD - objekte bei der registrierung der komponente in der RB_MASK mitgefuehrt
	private RB_KF 							rb_K_FIELD = null;
	
	
	//2016-04-05: speicherplatz fuer die gerenderte komponente in der navilist (z.B. durch indirektes einfuegen)
	//            hier steht die kompoenente, die wirklich im grid der navilist erzeugt wird 
	//            !!! diese komponente wird nicht KOPIERT und nur in der navilist gesetzt
	private Component                         real_rendered_component_in_list = null; 
	
	
	//2016-07-19: RB_ComponentMAP: hinterlegen, welcher RB_ComponentMAP die komponente zugehoert (nur RB_Components)
	private RB_ComponentMap                   rb_componentMapThisBelongsTo = null;
	//2016-07-19: Key, unter dem die komponente registriert wurde
	private RB_K   		                      my_key_in_collection = null;
	
	
	
	//2018-01-26: neuer schalter, kann einen zustand: ist teil einer maske speichern
	private boolean  						 isRegisteredToMask = false;
	
	
	//2018-08-02: Martin: neues TextFeld, definiert ausfuehrliche Beschreibung einer Spalte in einer Liste (statt der ? oder abkürzungen) 
	private MyString  						longString4ColumnSelection = null;
	
	
	/*
	 * 2019-02-18: martin: breite der sortierbuttons (nur falls gesetzt) 
	 */
	private Integer   widthOfTitelSortButton = null;
	
	/*
	 *2019-02-19_ style des automatische generierten Sortbuttons 
	 */
	private MutableStyle  styleOfAutoTitelButton = null;
	
	
	/*
	 * 2020-06-17: fuer das highlighten von mask-components einen platz zum speichern der non-highlight-background-color
	 */
	private Color        colorBackOfLayoutDataBeforeHighlight = null;
	private boolean      highlightActive = false;
	
	

	public      void 	INIT_Schalter_TRUE(String cHASH)
	{
		this.oHashZustaende.put(cHASH, new Boolean(true));
	}

	public boolean 		GET_Schalter_und_setze_TRUE(String cHASH) throws myException
	{
		if (this.oHashZustaende.containsKey(cHASH))
		{
			boolean bRueck = this.oHashZustaende.get(cHASH).booleanValue();
			this.SET_Schalter(cHASH, true);
			return bRueck;
		}
		else
		{
			throw new myException(this,new MyE2_String("Zustandsvariable ist nicht bekannt !").CTrans());
		}
	}

	public void 		SET_Schalter(String cHASH, boolean Zustand) throws myException
	{
		if (this.oHashZustaende.containsKey(cHASH))
		{
			this.oHashZustaende.put(cHASH,new Boolean(Zustand));			
		}
		else
		{
			throw new myException(this,new MyE2_String("Zustandsvariable ist nicht bekannt !").CTrans());
		}
	}
	
	/**
	 * Setzt alle zustaende wieder auf true
	 */
	public void    	     RESET_ALLE_SchalterAuf_TRUE()
	{
		Iterator<Map.Entry<String, Boolean>> iT = this.oHashZustaende.entrySet().iterator();
		while (iT.hasNext())
		{
			Map.Entry<String,Boolean> oEntry = iT.next();
			oEntry.setValue(new Boolean(true));
			//DEBUG.System_println(oEntry.getKey(), "");
		}
		
		
	}
	
	
	public MyE2EXT__Component(MyE2IF__Component   ComponentThisBelongsTo)
	{
		super();
		this.oComponentThisBelongsTo = ComponentThisBelongsTo;
	}
	
	
	public void set_I_MERKMAL(Integer imerkmal) 						{		this.iMERKMAL = imerkmal;				}
	public void set_C_MERKMAL(String cmerkmal)							{		this.cMERKMAL = cmerkmal;				}
	public void set_C_MERKMAL2(String cmerkmal)						{		this.cMERKMAL2 = cmerkmal;				}
	public void set_C_MERKMAL3(String cmerkmal)						{		this.cMERKMAL3 = cmerkmal;				}
	public void set_C_EVENT_KENNUNG(String ceventkennung)				{		this.cEVENT_KENNUNG = ceventkennung;	}
	public void set_O_PLACE_FOR_EVERYTHING(Object everyObject)		{		this.EVERY_OBJECT = everyObject;		}
	public void set_O_PLACE_FOR_EVERYTHING2(Object everyObject)		{		this.EVERY_OBJECT2 = everyObject;		}
	public Integer get_I_MERKMAL()										{		return this.iMERKMAL;					}
	public String get_C_MERKMAL()										{		return this.cMERKMAL;					}
	public String get_C_MERKMAL2()										{		return this.cMERKMAL2;					}
	public String get_C_MERKMAL3()										{		return this.cMERKMAL3;					}
	public String get_C_EVENT_KENNUNG()									{		return this.cEVENT_KENNUNG;				}
	public Object get_O_PLACE_FOR_EVERYTHING()							{		return this.EVERY_OBJECT;				}
	public Object get_O_PLACE_FOR_EVERYTHING2()							{		return this.EVERY_OBJECT2;				}

	public void set_C_HASHKEY(String chASHKEY)							{		this.cHASHKEY = chASHKEY;				}
	public String get_C_HASHKEY()										{		return this.cHASHKEY;					}
	public void set_oComponentMAP(E2_ComponentMAP ocomponentMap)		{		this.oComponentMAP = ocomponentMap;	}
	public E2_ComponentMAP get_oComponentMAP()							{		return this.oComponentMAP;				}
	public MyString get_cList_or_Mask_Titel()							{		return cList_or_Mask_Titel;					}
	public void set_cList_or_Mask_Titel(MyString list_or_Mask_Titel)	{		cList_or_Mask_Titel = list_or_Mask_Titel;	}

	public Component get_oCompTitleInList()							{		return oCompTitleInList;	}
	public void set_oCompTitleInList(Component compTitleInList)					{		oCompTitleInList = compTitleInList;	}

//	public E2_ContentPane 	get__oContentPane()									{		return oContentPane;	}
//	public void set__oContentPane(E2_ContentPane contentPane)					{		oContentPane = contentPane;	}

	public boolean get_bIsVisibleInList()										{		return bIsVisibleInList;	}
	public void set_bIsVisibleInList(boolean isVisibleInList)					{		bIsVisibleInList = isVisibleInList;	}

	public XXX_StyleFactory get_STYLE_FACTORY()									{		return STYLE_FACTORY;	}
	public void set_STYLE_FACTORY(XXX_StyleFactory style_factory)				{		this.STYLE_FACTORY = style_factory;	}

	
	public LayoutData get_oLayout_ListTitelElement() 							{		return oLayout_ListTitelElement;	}
	public void set_oLayout_ListTitelElement(LayoutData layout_ListTitelElement) {		oLayout_ListTitelElement = layout_ListTitelElement;	}

	public LayoutData get_oLayout_ListElement() 									{		return oLayout_ListElement;	}
	public void set_oLayout_ListElement(LayoutData layout_ListElement) 			{		oLayout_ListElement = layout_ListElement;	}
	public void set_oLayout_ListElement_AND_Titel(LayoutData layout_ListElement) 			
	{		
		this.oLayout_ListElement = layout_ListElement;	
		this.oLayout_ListTitelElement = layout_ListElement;	
	}
	

	//2013-11-07: formatierungsmoeglichkeit
	public void set_oLayout_ListElement_AND_Titel( E2_NaviListCol_Formater oFormatter) {
		this.oLayout_ListElement = oFormatter.get_oLayoutList();	
		this.oLayout_ListTitelElement = oFormatter.get_oLayoutTitle();	
	}
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if ((objHelp == null))
			throw new myExceptionCopy("Error Copying MyE2EXTComponent: Must receive an object of type MyE2EXTComponent, NULL IS NOT ALLOWED");

		if (! (objHelp instanceof MyE2IF__Component))
			throw new myExceptionCopy("Error Copying MyE2EXTComponent: Must receive an object of type MyE2EXTComponent");
		
		MyE2EXT__Component oEXT = new MyE2EXT__Component((MyE2IF__Component)objHelp);

		oEXT.set_I_MERKMAL(this.get_I_MERKMAL());
		oEXT.set_C_MERKMAL(this.get_C_MERKMAL());
		oEXT.set_C_MERKMAL2(this.get_C_MERKMAL2());
		oEXT.set_C_MERKMAL3(this.get_C_MERKMAL3());
		
		oEXT.set_C_EVENT_KENNUNG(this.get_C_EVENT_KENNUNG());
		oEXT.set_O_PLACE_FOR_EVERYTHING(this.get_O_PLACE_FOR_EVERYTHING());
		oEXT.set_C_HASHKEY(this.get_C_HASHKEY());
		oEXT.set_cList_or_Mask_Titel(this.get_cList_or_Mask_Titel());
		oEXT.set_oCompTitleInList(this.get_oCompTitleInList());
		oEXT.set_oColExtent(this.get_oColExtent());
		
		oEXT.set_oComponentMAP(null);
		
		oEXT.set_bIsVisibleInList(this.get_bIsVisibleInList());
		
		oEXT.set_STYLE_FACTORY(this.get_STYLE_FACTORY());

		oEXT.set_oLayout_ListTitelElement(this.oLayout_ListTitelElement);
		oEXT.set_oLayout_ListElement(this.oLayout_ListElement);
		
		for (int i=0;i<this.vField_Setters_AND_Validators__AfterReadInputMAP.size();i++)
			oEXT.add_FieldSetters_AND_Validator__AfterReadInputMAP((XX_FieldSetter_AND_Validator)this.vField_Setters_AND_Validators__AfterReadInputMAP.get(i));

		for (int i=0;i<this.vField_Setters_AND_Validators___BeforeReadInputMAP.size();i++)
			oEXT.add_FieldSetters_AND_Validator__BeforeReadInputMAP((XX_FieldSetter_AND_Validator)this.vField_Setters_AND_Validators___BeforeReadInputMAP.get(i));

		//2011-05-06
		for (int i=0;i<this.vField_Validator_Check_Input_and_MarkFalseValues.size();i++)
			oEXT.add_Field_Validator_Check_Input_and_MarkFalseValues((XX_FieldSetter_AND_Validator)this.vField_Validator_Check_Input_and_MarkFalseValues.get(i));

		
		for (int i=0;i<this.vValidatorsEnabledAllowd.size();i++)
			oEXT.add_ValidatorsEnabledAllowd((XX_FieldValidator_InputAllowed)this.vValidatorsEnabledAllowd.get(i));
		
		
		//oEXT.set_bDeleteIntegrityWatcherInContainer(this.get_bDeleteIntegrityWatcherInContainer());
		
		oEXT.set_oBasicModulContainerThisBelongsTo(this.get_oBasicModulContainerThisBelongsTo());
		
		
		oEXT.set_MaskEnabled_Combination(	this.get_bMaskEnabled_NEW_EMPTY(),
											this.get_bMaskEnabled_NEW_COPY(),
											this.get_bMaskEnabled_EDIT(),
											this.get_bMaskEnabled_VIEW(),
											this.get_bMaskEnabled_UNDEFINED());
		
		
		
		oEXT.set_bDisabledFromBasic(this.get_bDisabledFromBasic());
		oEXT.set_bDisabledFromInteractive(this.get_bDisabledFromInteractive());

		oEXT.set_oE2_Style(this.get_oE2_Style());
		
		oEXT.set_bHasFocusOnWindowPopup(this.get_bHasFocusOnWindowPopup());
		
		
		oEXT.set_ToolTipStringForListHeader(this.get_ToolTipStringForListHeader());
		
		oEXT.set_oStyle_4_ListHeaderComponent(this.oStyle_4_ListHeaderComponent);
		
		
		//2012-01-02: searchtags
		oEXT.get_vSEARCH_TAGS().addAll(this.get_vSEARCH_TAGS());
		
		
		//2012-12-06: einstellung linewrap fuer titelzeilen
		oEXT.set_bLineWrapListHeader(this.get_bLineWrapListHeader());
		
		
		//2014-09-04: zusatzkomponenten ebenfalls mitkopieren
		oEXT.get_vZusatzKomponentenInListenTitel().addAll(this.v_ZusatzKomponentenInListenTitel);
		
		//2015-02-27: fuer das validierungsframework werden RB_MASK_FIELD - objekte bei der registrierung der komponente in der RB_MASK mitgefuehrt
		oEXT.set_RB_K(this.get_RB_K());
		
		//2018-08-02: neues Erlaeuterungsfeld fuer spaltenselection
		oEXT.setLongString4ColumnSelection(this.getLongString4ColumnSelection());
		
		//2019-02-18
		oEXT.setWidthOfTitelSortButton(this.getWidthOfTitelSortButton());
		oEXT.setStyleOfAutoTitelButton(this.getStyleOfAutoTitelButton());
		
		//2020-06-17
		oEXT.setColorBackOfLayoutDataBeforeHighlight(this.colorBackOfLayoutDataBeforeHighlight);
		
		return oEXT;
	}

	public Extent get_oColExtent()									{		return oColExtent;	}
	public void set_oColExtent(Extent colExtent)					{		oColExtent = colExtent;	}

	
	public void add_ValidatorsEnabledAllowd(XX_FieldValidator_InputAllowed oValidator)
	{
		this.vValidatorsEnabledAllowd.add(oValidator);
	}

	public void add_ValidatorsEnabledAllowd_AFTER_Clean(XX_FieldValidator_InputAllowed oValidator)
	{
		this.vValidatorsEnabledAllowd.removeAllElements();
		this.vValidatorsEnabledAllowd.add(oValidator);
	}

	
	public void add_FieldSetters_AND_Validator__BeforeReadInputMAP(XX_FieldSetter_AND_Validator oFieldSetter)
	{
		this.vField_Setters_AND_Validators___BeforeReadInputMAP.add(oFieldSetter);
	}

	//2011-05-06
	public void add_Field_Validator_Check_Input_and_MarkFalseValues(XX_FieldSetter_AND_Validator oFieldSetter)
	{
		this.vField_Validator_Check_Input_and_MarkFalseValues.add(oFieldSetter);
	}

	
	public void add_FieldSetters_AND_Validator__AfterReadInputMAP(XX_FieldSetter_AND_Validator oFieldSetter)
	{
		this.vField_Setters_AND_Validators__AfterReadInputMAP.add(oFieldSetter);
	}

	
	public boolean is_ValidEnableAlowed()
	{
		boolean bRueck = true;
		for (int i=0;i<this.vValidatorsEnabledAllowd.size();i++)
			bRueck = bRueck & ((XX_FieldValidator_InputAllowed)this.vValidatorsEnabledAllowd.get(i)).isValid();
		
		return bRueck;
	}
	

	/**
	 * leerer Vector heist: valid ist ok
	 */
	public MyE2_MessageVector make_Setting_AND_Validation__BeforeReadInputMAP( String cSTATUS_MAP) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
		for (int i=0;i<this.vField_Setters_AND_Validators___BeforeReadInputMAP.size();i++)
			vRueck.add_MESSAGE( ((XX_FieldSetter_AND_Validator)this.vField_Setters_AND_Validators___BeforeReadInputMAP.get(i)).isValid(cSTATUS_MAP, this));
		
		return vRueck;
	}

	/**
	 * leerer Vector heist: valid ist ok
	 * 2011-05-06
	 */
	public MyE2_MessageVector get_Field_Validation_Check_Input_and_MarkFalseValues() throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
		for (int i=0;i<this.vField_Validator_Check_Input_and_MarkFalseValues.size();i++)
			vRueck.add_MESSAGE( ((XX_FieldSetter_AND_Validator)this.vField_Validator_Check_Input_and_MarkFalseValues.get(i)).isValid(null, this));
		
		return vRueck;
	}


	
	
	

	/**
	 * leerer Vector heist: valid ist ok
	 */
	public MyE2_MessageVector make_Setting_AND_Validation__AfterReadInputMAP(String cSTATUS_MAP) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
		for (int i=0;i<this.vField_Setters_AND_Validators__AfterReadInputMAP.size();i++)
			vRueck.add_MESSAGE( ((XX_FieldSetter_AND_Validator)this.vField_Setters_AND_Validators__AfterReadInputMAP.get(i)).isValid(cSTATUS_MAP, this));
		
		return vRueck;
	}


	public E2_BasicModuleContainer get_oBasicModulContainerThisBelongsTo()
	{
		return oBasicModulContainerThisBelongsTo;
	}


	public void set_oBasicModulContainerThisBelongsTo(E2_BasicModuleContainer basicModulContainerThisBelongsTo)
	{
		oBasicModulContainerThisBelongsTo = basicModulContainerThisBelongsTo;
	}
	

	/**
	 * @param bAllowd_NEW_EMPTY
	 * @param bAllowd_NEW_COPY
	 * @param bAllowd_EDIT
	 * @param bAllowd_VIEW
	 * @param bAllowd_UNDEFINED
	 */
	public void set_MaskEnabled_Combination(	boolean bAllowd_NEW_EMPTY,
												boolean bAllowd_NEW_COPY,
												boolean bAllowd_EDIT,
												boolean bAllowd_VIEW,
												boolean bAllowd_UNDEFINED)
	{
		this.AllowedInMaskCODE = "";
		if (bAllowd_NEW_EMPTY)	this.AllowedInMaskCODE +="Y";  else 	this.AllowedInMaskCODE +="N";
		if (bAllowd_NEW_COPY)	this.AllowedInMaskCODE +="Y";  else 	this.AllowedInMaskCODE +="N";
		if (bAllowd_EDIT)		this.AllowedInMaskCODE +="Y";  else 	this.AllowedInMaskCODE +="N";
		if (bAllowd_VIEW)		this.AllowedInMaskCODE +="Y";  else 	this.AllowedInMaskCODE +="N";
		if (bAllowd_UNDEFINED)	this.AllowedInMaskCODE +="Y";  else 	this.AllowedInMaskCODE +="N";
		
	}

	public boolean get_bMaskEnabled(String cSTATUS_MASKE) throws myException
	{
		if (bibALL.isEmpty(cSTATUS_MASKE))
			throw new myException("MyE2EXT_Component:get_bMaskEnabled: EMPTY Status not allowed !!!");
		else if (cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
			return this.get_bMaskEnabled_NEW_EMPTY();
		else if (cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY))
			return this.get_bMaskEnabled_NEW_COPY();
		else if (cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
			return this.get_bMaskEnabled_EDIT();
		else if (cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW))
			return this.get_bMaskEnabled_VIEW();
		else if (cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_UNDEFINED))
			return this.get_bMaskEnabled_UNDEFINED();
		else
			throw new myException("MyE2EXT_Component:get_bMaskEnabled: Status not allowed:<"+cSTATUS_MASKE+">");
		
	}
	
	public boolean get_bMaskEnabled_NEW_EMPTY()
	{
		return (this.AllowedInMaskCODE.substring(0,1).equals("Y"));
	}

	public boolean get_bMaskEnabled_NEW_COPY()
	{
		return (this.AllowedInMaskCODE.substring(1,2).equals("Y"));
	}
	
	public boolean get_bMaskEnabled_EDIT()
	{
		return (this.AllowedInMaskCODE.substring(2,3).equals("Y"));
	}
	
	public boolean get_bMaskEnabled_VIEW()
	{
		return (this.AllowedInMaskCODE.substring(3,4).equals("Y"));
	}
	
	public boolean get_bMaskEnabled_UNDEFINED()
	{
		return (this.AllowedInMaskCODE.substring(4,5).equals("Y"));
	}


	
	/**
	 * prueft anhand der schalter disabled ... und dem feld (writeable or not) ob ein
	 * feld, das diese extension hat, enabled sein kann
	 * Bei komplexen feldern werden nur die schalter disabled gecheckt, da diese nicht direkt in die datenbanktabelle schreiben
	 * 
	 * @param bDBFieldIsComplex
	 * @return
	 */
	public boolean get_bCanBeEnabled()
	{
		return !this.bDisabledFromBasic && !this.bDisabledFromInteractive;
	}
	

	public MyE2IF__Component 	get_oComponentThisBelongsTo() {		return oComponentThisBelongsTo;	}
	public void 				set_oComponentThisBelongsTo(MyE2IF__Component componentThisBelongsTo) {	oComponentThisBelongsTo = componentThisBelongsTo;	}
	
	
	public E2_MutableStyle get_oE2_Style()
	{
		return oE2_Style;
	}
	
	public void set_oE2_Style(E2_MutableStyle oStyle)
	{
		this.oE2_Style= oStyle;
	}

	
	public void set_bEnabled_For_Edit(boolean bEnabled, String cSTATUS_MASKE) throws myException
	{
		this.oComponentThisBelongsTo.set_bEnabled_For_Edit(bEnabled && this.get_bMaskEnabled(cSTATUS_MASKE));
	}
	
	
	//2012-01-12
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		this.oComponentThisBelongsTo.set_bEnabled_For_Edit(bEnabled);
	}
	//2012-01-12
	public void set_bEnabled_For_Edit_and_set_DisabledFromBasicFlag(boolean bEnabled) throws myException
	{
		this.oComponentThisBelongsTo.set_bEnabled_For_Edit(bEnabled);
		this.oComponentThisBelongsTo.EXT().set_bDisabledFromBasic(!bEnabled);
	}
	//2012-01-12
	public void set_bEnabled_For_Edit_and_set_DisabledFromInteractiveFlag(boolean bEnabled) throws myException
	{
		this.oComponentThisBelongsTo.set_bEnabled_For_Edit(bEnabled);
		this.oComponentThisBelongsTo.EXT().set_bDisabledFromInteractive(!bEnabled);
	}
	
	
	
	public boolean get_bHasFocusOnWindowPopup()
	{
		return bHasFocusOnWindowPopup;
	}
	
	
	public void set_bHasFocusOnWindowPopup(boolean hasFocusOnWindowPopup)
	{
		bHasFocusOnWindowPopup = hasFocusOnWindowPopup;
	}
	

	
	
	/**
	 * @return E2_BasicModuleContainer_MASK in which this component is used (or null)
	 */
	public E2_BasicModuleContainer_MASK get_ModuleContainer_MASK_this_BelongsTo()
	{
		E2_BasicModuleContainer_MASK oMask = null;
		
		if (this.get_oComponentThisBelongsTo() != null)
		{
			if (this.get_oComponentThisBelongsTo().EXT().get_oComponentMAP() != null)
			{
				oMask = this.get_oComponentThisBelongsTo().EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo();
			}
		}
		return oMask;
	}
	
	public String get_MASK_SAVE_TAG()
	{
		return MASK_SAVE_TAG;
	}
	
	public void set_MASK_SAVE_TAG(String mask_save_tag)
	{
		MASK_SAVE_TAG = mask_save_tag;
	}
	
	public MyE2_String get_ToolTipStringForListHeader()
	{
		return cStringForListHeaderToolTips;
	}
	
	public void set_ToolTipStringForListHeader(MyE2_String stringForListHeaderToolTips)
	{
		cStringForListHeaderToolTips = stringForListHeaderToolTips;
	}
	
	public MutableStyle get_oStyle_4_ListHeaderComponent() 
	{
		return oStyle_4_ListHeaderComponent;
	}
	
	public void set_oStyle_4_ListHeaderComponent(MutableStyle oStyle_4_ListHeaderComponent) 
	{
		this.oStyle_4_ListHeaderComponent = oStyle_4_ListHeaderComponent;
	}

	
	public String get_cHashKey4UserSetting() 
	{
		return cHashKey4UserSetting;
	}
	public void set_cHashKey4UserSetting(String HashKey4UserSetting) 
	{
		this.cHashKey4UserSetting = HashKey4UserSetting;
	}

	
	/*
	 * 2011-06-06: weiteres feld, dass definiert, ob das objekt zu
	 * einem MyE2IF__ComponentContainer
	 * gehoert
	 */
	public MyE2IF__ComponentContainer get_oColumnComponentContainerThisBelongsTo()
	{
		return oColumnComponentContainerThisBelongsTo;
	}
	public void set_oColumnComponentContainerThisBelongsTo(MyE2IF__ComponentContainer oColumnComponentContainerThisBelongsTo)
	{
		this.oColumnComponentContainerThisBelongsTo = oColumnComponentContainerThisBelongsTo;
	}
	
	public Vector<E2_SEARCH_TAGS.SEARCH_TAGS> get_vSEARCH_TAGS()
	{
		return vSEARCH_TAGS;
	}

	
	public void add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS oTyp)
	{
		this.vSEARCH_TAGS.add(oTyp);
	}
	
	public boolean get_bLineWrapListHeader()
	{
		return bLineWrapListHeader;
	}
	
	public void set_bLineWrapListHeader(boolean LineWrapListHeader)
	{
		this.bLineWrapListHeader = LineWrapListHeader;
	}

	
	//2014-08-29: weitere komponenten in den spaltentitel platzieren (am anfang: Summationsbutton)
	public Vector<E2IF__BelongsToNavigationList> get_vZusatzKomponentenInListenTitel() {
		return v_ZusatzKomponentenInListenTitel;
	}
	
	
	/**
	 * 2014-11-03: UUID wird erzeugt
	 * @return
	 */
	public UUID get_UUID() {
		if (this.UUID == null) {
			this.UUID = java.util.UUID.randomUUID();
		}
		return this.UUID;
	}
	
	
	
	//2015-02-27: fuer das validierungsframework werden RB_MASK_FIELD - objekte bei der registrierung der komponente in der RB_MASK mitgefuehrt
	public RB_KF get_RB_K() {
		return this.rb_K_FIELD;
	}
	public void set_RB_K(RB_KF fieldKey) {
		this.rb_K_FIELD = fieldKey;
	}

	
	public Component get_real_rendered_component_in_list() {
		return real_rendered_component_in_list;
	}
	
	public void set_real_rendered_component_in_list(Component p_real_rendered_component_in_list) {
		this.real_rendered_component_in_list = p_real_rendered_component_in_list;
	}
	
	

	public void rb_set_belongs_to(RB_ComponentMap map) throws myException {
		this.rb_componentMapThisBelongsTo=map;
	}
	public RB_ComponentMap rb_get_belongs_to() throws myException {
		return this.rb_componentMapThisBelongsTo;
	}
	
	/**
	 * 
	 * @return key, under which this component is registered
	 * @throws myException
	 */
	public RB_K   get_my_key_in_collection() throws myException {
		return this.my_key_in_collection;
	}
	public RB_K   set_my_key_in_collection(RB_K key) throws myException {
		this.my_key_in_collection=key;
		return key;
	}


	//2018-01-26: neuer schalter, kann einen zustand: ist teil einer maske speichern
	public boolean isRegisteredToMask() {
		return isRegisteredToMask;
	}
	public void setRegisteredToMask(boolean p_isPartOfMask) {
		this.isRegisteredToMask = p_isPartOfMask;
	}
	
	
	public MyString getLongString4ColumnSelection() {
		return longString4ColumnSelection;
	}
	public void setLongString4ColumnSelection(MyString longString4ColumnSelection) {
		this.longString4ColumnSelection = longString4ColumnSelection;
	}

	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @return
	 */
	public Integer getWidthOfTitelSortButton() {
		return widthOfTitelSortButton;
	}
	
	/**
	 *
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param p_widthOfTitelSortButton
	 */
	public void setWidthOfTitelSortButton(Integer p_widthOfTitelSortButton) {
		this.widthOfTitelSortButton = p_widthOfTitelSortButton;
	}
	
	public MutableStyle getStyleOfAutoTitelButton() {
		return styleOfAutoTitelButton;
	}
	
	public void setStyleOfAutoTitelButton(MutableStyle styleOfAutoTitelButton) {
		this.styleOfAutoTitelButton = styleOfAutoTitelButton;
	}

	/*
	 * 
	 * @author martin
	 * @date 17.06.2020
	 *
	 * @return
	 */
	public Color getColorBackOfLayoutDataBeforeHighlight() {
		return colorBackOfLayoutDataBeforeHighlight;
	}
	public void setColorBackOfLayoutDataBeforeHighlight(Color colorBackOfLayoutDataBeforeHighlight) {
		this.colorBackOfLayoutDataBeforeHighlight = colorBackOfLayoutDataBeforeHighlight;
	}
	public boolean isHighlightActive() {
		return highlightActive;
	}
	public void setHighlightActive(boolean highlightActive) {
		this.highlightActive = highlightActive;
	}
	
}



