package panter.gmbh.Echo2.ListAndMask;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import echopointng.PopUp;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.Echo2.components.DB.MyE2IF__ComponentContainer;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_WithSelektor;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.exceptions.myExceptionDataQueryError;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.myVectors.VEK;


/**
 * hashmap, die E2_MaskElement - komponenten haelt, 
 * jede maskmap repraesentiert einen datensatz,
 * d.h. eine maskmap ist entweder eine maske, oder eine zeile in 
 * einer liste
 */
/**
 * @author martin
 *
 */
public class E2_ComponentMAP extends HashMap<String,MyE2IF__Component> implements E2_IF_Copy
{
	
	public static final String 		STATUS_NEW_COPY = 	"STATUS_NEW_COPY_E2_ComponentMAP";

	public static final String 		STATUS_NEW_EMPTY = 	"STATUS_NEW_EMPY_E2_ComponentMAP";

	public static final String 		STATUS_VIEW = 		"STATUS_VIEW_E2_ComponentMAP";

	public static final String 		STATUS_EDIT = 		"STATUS_EDIT_E2_ComponentMAP";

	public static final String 		STATUS_UNDEFINED = 	"STATUS_STATUS_UNDEFINED_E2_ComponentMAP";
	
	/*
	 * vector mit den mapping - keys,  um die reihenfolge richtig zu halten
	 */
	private Vector<String> 	vComponentHashKeys = new Vector<String>();

	//2016-07-06: shadow-vektor mit den Hashkeys, die die alte reihenfolge in den listenspalten hinterlegt, wenn umsortiert wurde
	private Vector<String> 	vComponentHashKeysSaveOrig = new Vector<String>();
	
	
	
	/*
	 * ein SQLFieldMAP - object 
	 */
	private SQLFieldMAP		oSQLFieldMAP = null;

	/*
	 * uebernimmt die ergebnisse bei anzeigen
	 */
	private SQLResultMAP oInternalSQLResultMAP = null;

	private MyDBToolBox 	oDB	= null;
	

	/*
	 * einstellungen fuer die maskenfelder vor einer aktion
	 */
	private XX_MAP_SettingAgent oMAPSettingAgent = null;
	
	/*
	 * Vector fuer validierungen vor dem speichern,
	 * aus  XX_MAP_ValidBeforeSAVE - objekten
	 */
	private Vector<XX_MAP_ValidBeforeSAVE> vMAPValidator = new Vector<XX_MAP_ValidBeforeSAVE>();
	
	
	/*
	 * falls die map fuer eine liste geklont wird, wird sie
	 * in einen vector eingebettet. dann erhaelt die Map die Info
	 * welcher Vector dies ist
	 */
	private Vector<E2_ComponentMAP>		vVectorComponentMAP_thisBelongsTo = null;

	
	/*
	 * falls die map eine Map in einer Maske ist, dann 
	 * wird sie teil eines  E2_vCombinedComponentMAPs-objekts und
	 * bekommt eine referenz auf dies mitgeteilt 
	 */
	private  E2_vCombinedComponentMAPs  oE2_vCombinedComponentMAPs = null;
	
	
	//2011-01-10: mehrere Subquery-agents
	/*
	 * fuer zusatzfelder, die anhand der aktuellen map gefuellt werden
	 */
	//private XX_ComponentMAP_SubqueryAGENT	oSubQueryAgent = null;
	private V_ComponentMAP_SubqueryAGENT v_ComponentMAP_SubqueryAGENT = new V_ComponentMAP_SubqueryAGENT();
	
	
	/*
	 * ein daugher-object, das evtl. reingerendert wird, abhaengig von der jeweilgen ID der zeile
	 * (wird in der liste eingesetzt
	 */
	private XX_List_EXPANDER_4_ComponentMAP oList_EXPANDER_4_ComponentMAP = null;
	
	
	/*
	 * ausserdem kann einer componentMap auch direkt (z.b. aus einem save-validator raus)
	 * zusatzstatements uebergeben. diese werden immer komplett gefuellt (nicht additiv) und 
	 * beim abfragen geloescht
	 * 
	 */
	private Vector<String>  				vZusatzSQLStatements_INSERT = new Vector<String>();
	private Vector<String>  				vZusatzSQLStatements_UPDATE = new Vector<String>();
	
	
	/*
	 * hier werden die inneren MyE2IF__ComponentContainer - elemente aufgeloest
	 * damit steht ein hashmap mit einer flachen objekt-struktur zur verfuegung
	 */
	private HashMap<String,MyE2IF__Component>   	hmRealComponents = new HashMap<String, MyE2IF__Component>();
	private HashMap<String,MyE2IF__DB_Component>   	hmRealDBComponents = new HashMap<String, MyE2IF__DB_Component>();
	
	
	//Vector mit ADDON-Statments-buildern
	private Vector<builder_AddOnSQL_STATEMENTS>     vADDON_SQL_STATEMENT_BUILDER = new Vector<builder_AddOnSQL_STATEMENTS>();
	
	
	//information fuer masken-componentMaps, in welchem E2_BasicModuleContainerMASK sie sich befinden.
	//wird in der init-methode der maske ausgefuehrt
	private E2_BasicModuleContainer_MASK            oModulContainerMASK_This_BelongsTo = null;
	
	
	
	/*
	 * falls der componentVector in einer E2_NavigationList verwendet wird, wird hier definiert,
	 * zu welcher navigationlist der E2_ComponentVector gehoert. Dies wird fuer den Referenzvector bei der init-Methode gemacht, bei 
	 * den listen-E2_ComponentVectoren wirds beim Erzeugen gemacht
	 * 
	 */
	private E2_NavigationList                     	oNavigationList_This_Belongs_to = null;
	
	
	//2010-12-21: schalter, der die Liste so einstellt, dass die ausklapp-unterlisten immer eingeschaltet sind bei basis aufbau
	private boolean    								bExtendSublistsAutomatic = false;
	

	//2012-08-30: hashmap mit einstellungen, die als sicherung der normal-zeile dient
	private HashMap<String, MarkColorAndOthers>   	hm_unmarkedColorAndOthers = new HashMap<String, MarkColorAndOthers>();
	
	
	
	//2012-08-31: in fast jeder liste existiert eine E2_CheckBoxForList-Objekt. Dieses Feld soll dafuer sorgen
	//            dass die recusiv-suche, die noetig ist, diese checkbox zu finden, nur einmal ausgefuehrt wird
	private E2_CheckBoxForList   		            myOwnCheckboxInList = null;
	
	
	
	//2012-12-13: letzter fuellstatus der componentMap:
	private String                                  STATUS_LAST_FILL = E2_ComponentMAP.STATUS_UNDEFINED;
	
	
	//2012-12-13: maskeneinsteller- und validierer objekte
	private HM_interactiv_settings_validation   	hmInteractiv_settings_validation = new HM_interactiv_settings_validation();
	
	
	//2013-07-26: Flag, das nur in Componentmaps benutzt wird, die aus der methode get_Copy() erzeugt werden
	private boolean 					   			bIsCopy = false;
	
	
	
	//2015-02-25: anbindung an RB-klassen mittels Factoryklasse. Diese kann je ein passendes record und record_new - object bauen
	private E2_ComponentMAP_Factory4Records  		Factory4Records = null;
	private MyRECORD_IF_FILLABLE    				RecordNew4MainTable = null;
	private MyRECORD_IF_RECORDS  					Record4MainTable = null;
	//2015-02-25: anbindung an RB-klassen mittels Factoryklasse. Diese kann je ein passendes record und record_new - object bauen
	
	
	/*
	 * 2015-07-08: fehlerkorrektur: die methode set_AllComponentsAsDeleted und set_AllComponentsAsDeleted(Vector<>()) sind fehlerhaft implementiert,
	 * deswegen variante
	 */
	private boolean 								deletedSetting_newVersion = false;

	
	/*
	 * 2018-12-04: zentrale HashMap zur speicherung von Rec21-objekten in listen.
	 * Dabei muss fuer E2_NavigationList - listenoperationen in jede Rec21 - Kombination nur einmal gelesen werden 
	 */
	public HashMap<String, Rec21>   				hmRec21 = new  HashMap<>();
	
	
	/**
	 * 20190221: neue formatierung, die alle moeglichkeiten umfasst, via E2_ComponentMapMarker - klasse
	 * wenn der vorhanden ist, dann erfolgt alle farbmarkierung ueber diesen marker, angehakte, geloeschte usw
	 */
	private E2_ComponentMapMarker  					markingAgent = null;
	
	

	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}

	
	//2014-04-02: sqlfieldmap separat uebergeben, damit leere konstruktor moeglich ist
	public E2_ComponentMAP() {
		super();
	}
	
	
	public E2_ComponentMAP(SQLFieldMAP sqlfieldMAP)
	{
		super();
		oSQLFieldMAP = sqlfieldMAP;
		this.oDB = bibALL.get_myDBToolBox();
	}

	
	
	//2014-04-02: sqlfieldmap separat uebergeben, damit leere konstruktor moeglich ist
	public void set_oSQLFieldMAP(SQLFieldMAP  sqlFieldMAP) {
		this.oSQLFieldMAP = sqlFieldMAP;
	}


	
	
	public MyE2IF__DB_Component add_Component(	MyE2IF__DB_Component oDBComponent,
												MyString cTitleForMaskOrList, 
												boolean bVisibleInListAtStart, 
												boolean bIsSortableInList, 
												GridLayoutData oLayoutTitel,
												GridLayoutData oLayoutGrid
												) throws myException
	{
		((MyE2IF__Component)oDBComponent).EXT().set_bIsVisibleInList(bVisibleInListAtStart);
		oDBComponent.EXT_DB().set_bIsSortable(bIsSortableInList);
		if (oLayoutTitel != null) oDBComponent.EXT().set_oLayout_ListTitelElement(oLayoutTitel);
		if (oLayoutGrid != null) oDBComponent.EXT().set_oLayout_ListElement(oLayoutGrid);
		return this.add_Component(oDBComponent, cTitleForMaskOrList);
	}



	/**
	 * 
	 * @param oDBComponent
	 * @param cTitleForMaskOrList
	 * @param bVisibleInListAtStart
	 * @param bIsSortableInList
	 * @param cToolTip4Title (can be null)
	 * @param oLayoutTitel (can be null)
	 * @param oLayoutGrid (can be null)
	 * @return
	 * @throws myException
	 */
	public MyE2IF__DB_Component add_Component(	MyE2IF__DB_Component oDBComponent,
												MyString cTitleForMaskOrList, 
												boolean bVisibleInListAtStart, 
												boolean bIsSortableInList,
												MyE2_String cToolTip4Title,
												GridLayoutData oLayoutTitel,
												GridLayoutData oLayoutGrid
												) throws myException
	{
		((MyE2IF__Component)oDBComponent).EXT().set_bIsVisibleInList(bVisibleInListAtStart);
		oDBComponent.EXT_DB().set_bIsSortable(bIsSortableInList);
		if (oLayoutTitel != null) oDBComponent.EXT().set_oLayout_ListTitelElement(oLayoutTitel);
		if (oLayoutGrid != null) oDBComponent.EXT().set_oLayout_ListElement(oLayoutGrid);
		if (cToolTip4Title != null) oDBComponent.EXT().set_ToolTipStringForListHeader(cToolTip4Title);
		return this.add_Component(oDBComponent, cTitleForMaskOrList);
	}

	
	
	/**
	 * 
	 * @param oDBComponent
	 * @param cTitleForMaskOrList
	 * @param bVisibleInListAtStart
	 * @param bIsSortableInList
	 * @return
	 * @throws myException
	 */
	public MyE2IF__DB_Component add_Component(MyE2IF__DB_Component oDBComponent,MyString cTitleForMaskOrList, boolean bVisibleInListAtStart, boolean bIsSortableInList) throws myException
	{
		((MyE2IF__Component)oDBComponent).EXT().set_bIsVisibleInList(bVisibleInListAtStart);
		oDBComponent.EXT_DB().set_bIsSortable(bIsSortableInList);
		return this.add_Component(oDBComponent, cTitleForMaskOrList);
	}


	
	public MyE2IF__DB_Component add_Component(MyE2IF__DB_Component oDBComponent,MyString cTitleForMaskOrList) throws myException
	{
		if (!(oDBComponent instanceof E2_IF_Copy))
			throw new myException("E2_ComponentMAP:add_Component:only types E2_IF_Copy allowed");

		if (!(oDBComponent instanceof MyE2IF__Component))
			throw new myException("E2_ComponentMAP:add_Component:Component must implement interface MyE2IF__Component");

		String cHashKey = "";
		
		MyString c_NEW_ListOrMaskTitle = null;
		
		cHashKey = oDBComponent.EXT_DB().get_oSQLField().get_cFieldLabel();
		
		if (cTitleForMaskOrList==null)
			c_NEW_ListOrMaskTitle = oDBComponent.EXT_DB().get_oSQLField().get_cFieldLabelForUser();
		else
			c_NEW_ListOrMaskTitle = cTitleForMaskOrList;
		
		/*
		 * wenn der feldbezeichner im sqlfield noch dem label entspricht, dann wird dieser
		 * auch ueberschrieben
		 */
		if (oDBComponent.EXT_DB().get_oSQLField().get_cFieldLabelForUser().COrig().equals(oDBComponent.EXT_DB().get_oSQLField().get_cFieldLabel()))
			 oDBComponent.EXT_DB().get_oSQLField().set_cFieldLabelForUser(c_NEW_ListOrMaskTitle);
		
		if (this.containsKey(cHashKey))
			throw new myException("E2_ComponentMAP:add_Component:duplicate key not possible: "+cHashKey);
		
		this.put(cHashKey,oDBComponent);
		this.vComponentHashKeys.add(cHashKey);

		((MyE2IF__Component)oDBComponent).EXT().set_oComponentMAP(this);
		((MyE2IF__Component)oDBComponent).EXT().set_cList_or_Mask_Titel(c_NEW_ListOrMaskTitle);
		((MyE2IF__Component)oDBComponent).EXT().set_C_HASHKEY(cHashKey);
		
		this.hmRealComponents.put(cHashKey, oDBComponent);
		if (oDBComponent instanceof MyE2IF__DB_Component)
		{
			this.hmRealDBComponents.put(cHashKey,(MyE2IF__DB_Component)oDBComponent);
		};
		
		this.check_if_toolTipsArePossible(oDBComponent, cTitleForMaskOrList);

		return oDBComponent;
	}

	
	public MyE2IF__Component add_Component(String cHashKey,MyE2IF__Component oComponent, MyString cTitleForMaskOrList, boolean bVisibleInListAtStart, 
			GridLayoutData oLayoutTitel,
			GridLayoutData oLayoutGrid) throws myException
	{
		oComponent.EXT().set_bIsVisibleInList(bVisibleInListAtStart);
		if (oLayoutTitel != null) oComponent.EXT().set_oLayout_ListTitelElement(oLayoutTitel);
		if (oLayoutGrid != null) oComponent.EXT().set_oLayout_ListElement(oLayoutGrid);

		return this.add_Component(cHashKey, oComponent, cTitleForMaskOrList);
	}


	
	/**
	 * 
	 * @param cHashKey
	 * @param oComponent
	 * @param cTitleForMaskOrList
	 * @param bVisibleInListAtStart
	 * @param cToolTip4Title  (null allowed)
	 * @param oLayoutTitel (null allowed)
	 * @param oLayoutGrid (null allowed)
	 * @return
	 * @throws myException
	 */
	public MyE2IF__Component add_Component(	String cHashKey,	
											MyE2IF__Component oComponent, 
											MyString cTitleForMaskOrList, 
											boolean bVisibleInListAtStart,
											MyE2_String cToolTip4Title,
											GridLayoutData oLayoutTitel,
											GridLayoutData oLayoutGrid) throws myException
	{
		oComponent.EXT().set_bIsVisibleInList(bVisibleInListAtStart);
		if (oLayoutTitel != null) oComponent.EXT().set_oLayout_ListTitelElement(oLayoutTitel);
		if (oLayoutGrid != null) oComponent.EXT().set_oLayout_ListElement(oLayoutGrid);
		if (cToolTip4Title != null) oComponent.EXT().set_ToolTipStringForListHeader(cToolTip4Title);

		
		return this.add_Component(cHashKey, oComponent, cTitleForMaskOrList);
	}


	
	/**
	 * 
	 * @author martin
	 * @date 08.01.2019
	 *
	 * @param c
	 * @return
	 * @throws myException
	 */
	public MyE2IF__Component add_Component(If_ComponentWithOwnKey c) throws myException {
		return this.add_Component(c,true);
	}
	
	/**
	 * 
	 * @author martin
	 * @date 08.01.2019
	 *
	 * @param c
	 * @param visibleAtStart
	 * @return
	 * @throws myException
	 */
	public MyE2IF__Component add_Component(If_ComponentWithOwnKey c, boolean visibleAtStart) throws myException {
		return this.add_Component(c.key(),c,S.ms(c.userText()), visibleAtStart);
	}
	
	
	
	public MyE2IF__Component add_Component(String cHashKey,MyE2IF__Component oComponent, MyString cTitleForMaskOrList, boolean bVisibleInListAtStart) throws myException
	{
		oComponent.EXT().set_bIsVisibleInList(bVisibleInListAtStart);
		return this.add_Component(cHashKey, oComponent, cTitleForMaskOrList);
	}
	
	public MyE2IF__Component add_Component(String cHashKey,MyE2IF__Component oComponent, MyString cTitleForMaskOrList) throws myException
	{
		if (oComponent instanceof MyE2IF__DB_Component && !((MyE2IF__DB_Component)oComponent).get_bIsComplexObject())
			throw new myException("E2_ComponentMAP:add_Component:please use add_Component(MyE2IF__DB_Component oComponent) for not complex database components");

		if (!(oComponent instanceof E2_IF_Copy))
			throw new myException("E2_ComponentMAP:add_Component:only types E2_IF_Copy allowed");
		
		if (this.containsKey(cHashKey))
			throw new myException("E2_ComponentMAP:add_Component:duplicate key not possible! "+cHashKey);

		this.put(cHashKey,oComponent);
		oComponent.EXT().set_oComponentMAP(this);
		oComponent.EXT().set_cList_or_Mask_Titel(cTitleForMaskOrList);
		oComponent.EXT().set_C_HASHKEY(cHashKey);
			
		this.vComponentHashKeys.add(cHashKey);
		
		/*
		 * jetzt noch falls es ein container-object ist, die inneren komponenten verarzten
		 */
		if (oComponent instanceof MyE2IF__ComponentContainer)
		{
			Vector<MyE2IF__Component> vInnerComponents = ((MyE2IF__ComponentContainer)oComponent).get_vComponents();
			for (int i=0;i<vInnerComponents.size();i++)
			{
				Component CompIn = (Component)vInnerComponents.get(i);
				if (CompIn instanceof MyE2IF__Component)
				{
					((MyE2IF__Component)CompIn).EXT().set_oComponentMAP(this);
				}
				else
					throw new myException("E2_ComponentMAP:add_Component: only MyE2IF__Component are allowed in Componentcontainers");
				
				
				//2012-08-31: die E2_checkBoxInList gleich identifizieren und merken
				if (CompIn instanceof E2_CheckBoxForList)
				{
					this.myOwnCheckboxInList = (E2_CheckBoxForList)CompIn;
				}
				
			}
			
			this.hmRealComponents.putAll(((MyE2IF__ComponentContainer)oComponent).get_hmComponents());
			for (Entry<String,MyE2IF__Component> oEntry:((MyE2IF__ComponentContainer)oComponent).get_hmComponents().entrySet())
			{
				if (oEntry.getValue() instanceof MyE2IF__DB_Component)
				{
					this.hmRealDBComponents.put(oEntry.getKey(),(MyE2IF__DB_Component)oEntry.getValue());
				}
			}
		}
		else
		{
			this.hmRealComponents.put(cHashKey, oComponent);
			if (oComponent instanceof MyE2IF__DB_Component)
			{
				this.hmRealDBComponents.put(cHashKey,(MyE2IF__DB_Component)oComponent);
			}
			
			//2012-08-31: die E2_checkBoxInList gleich identifizieren und merken
			if (oComponent instanceof E2_CheckBoxForList)
			{
				this.myOwnCheckboxInList = (E2_CheckBoxForList)oComponent;
			}

			
		}
		
		this.check_if_toolTipsArePossible(oComponent, cTitleForMaskOrList);
		
		return oComponent;
	}

	
	
	
	/*
	 * aenderung 2013-07-26: wenn in der datenbank ein @USER:  - Tooltip gefunden wird, dann wird diese angezeigt UND ERSETZT ALLE ALTEN!!! (wird 
	 * nur in original-E2_ComponentMAPs durchgefuehrt, in kopien nicht (wegen listen-laufzeiten)
	 */
	private void check_if_toolTipsArePossible(MyE2IF__Component oComponent, MyString cTitleForMaskOrList)
	{
		String c_TooltipFromDB = bibServer.get_cTooltipInfosFromDBDescription(oComponent);
		//String c_TooltipFromDB = null;
		
		MyE2_String cTooltipFromDB = S.isEmpty(c_TooltipFromDB)?null:new MyE2_String(c_TooltipFromDB);
		
		
		//2013-01-04: selectField muss auch tooltips bekommen
		if (oComponent instanceof MyE2_SelectField)
		{
			if (cTooltipFromDB != null) {
				((MyE2_SelectField)oComponent).setToolTipText(cTooltipFromDB.CTrans());
				((MyE2_SelectField)oComponent).set_cToolTipWhenEmptyDropDownValue(cTooltipFromDB.CTrans());
			} else {
			
				if (S.isFull(((MyE2_SelectField)oComponent).getToolTipText()))
				{
					((MyE2_SelectField)oComponent).set_cToolTipWhenEmptyDropDownValue(((MyE2_SelectField)oComponent).getToolTipText());
				}
				else if (S.isEmpty(((MyE2_SelectField)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((MyE2_SelectField)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
					((MyE2_SelectField)oComponent).set_cToolTipWhenEmptyDropDownValue(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof TextField)
		{
			if (cTooltipFromDB != null) {
				((TextField)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
			
				if (S.isEmpty(((TextField)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((TextField)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof TextArea)
		{
			if (cTooltipFromDB != null) {
				((TextArea)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				if (S.isEmpty(((TextArea)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((TextArea)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
			}
		}
		else if (oComponent instanceof Button)
		{
			if (cTooltipFromDB != null) {
				((Button)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				
				if (S.isEmpty(((Button)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((Button)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof Label)
		{
			if (cTooltipFromDB != null) {
				((Label)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				
				if (S.isEmpty(((Label)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((Label)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof CheckBox)
		{
			if (cTooltipFromDB != null) {
				((CheckBox)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				if (S.isEmpty(((CheckBox)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((CheckBox)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
			}
		}
		else if (oComponent instanceof MyE2_DB_TextField_WithSelektor)
		{
			MyE2_DB_TextField_WithSelektor oHelp = (MyE2_DB_TextField_WithSelektor)oComponent;
			
			TextField 	oTFHelp = oHelp.get_oTextField();
			PopUp 		oPopUp = oHelp.get_oPopUp();
			
			if (cTooltipFromDB != null) {
				oTFHelp.setToolTipText(cTooltipFromDB.CTrans());
				oPopUp.setToolTipText(cTooltipFromDB.CTrans());
			}
			else {

				if (S.isEmpty(oTFHelp.getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					oTFHelp.setToolTipText(cTitleForMaskOrList.CTrans());
				}
				if (S.isEmpty(oPopUp.getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					oPopUp.setToolTipText(cTitleForMaskOrList.CTrans());
				}
			}
		}
		else if (oComponent instanceof MyE2_Row)
		{
			Vector<String> vSearchList = bibALL.get_Vector(Button.class.getName(), Label.class.getName(),TextField.class.getName(),TextArea.class.getName());
			E2_RecursiveSearch_Component oRecSearch = new E2_RecursiveSearch_Component((MyE2_Row)oComponent,vSearchList,null);

			for (Component oComp: oRecSearch.get_vAllComponents())
			{
				
				if (oComp instanceof TextField)
				{
					if (S.isEmpty(((TextField)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((TextField)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
				else if (oComp instanceof TextArea)
				{
					if (S.isEmpty(((TextArea)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((TextArea)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
				else if (oComp instanceof Button)
				{
					if (S.isEmpty(((Button)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((Button)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
				else if (oComp instanceof Label)
				{
					if (S.isEmpty(((Label)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((Label)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
			}
		} 

	}
	
	
	
	
	/**
	 * Leert die Maske fuer die Eingabe eines neuen Datensatzes
	 * @throws myException
	 */
	public void PrepareMaskContentForNew() throws myException
	{
		this.Reset_MaskErrorStyles();
		this.oInternalSQLResultMAP = null;
		
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			Component oComp = (Component)this.get(this.vComponentHashKeys.get(i));
			
			if (oComp instanceof MyE2IF__DB_Component)
			{
				((MyE2IF__DB_Component) oComp).prepare_ContentForNew(true);
			} 
			else if  (oComp instanceof MyE2IF__ComponentContainer)
			{
				Vector<MyE2IF__DB_Component> vHelp = ((MyE2IF__ComponentContainer)oComp).get_vDB_Components();
				for (int k=0;k<vHelp.size();k++)
				{
					if (vHelp.get(k) instanceof MyE2IF__DB_Component)
					{
						((MyE2IF__DB_Component)vHelp.get(k)).prepare_ContentForNew(true);
					}

				}
			}
			//20170818: weitere variante 
			else if (oComp instanceof MyE2IF_DB_SimpleComponent) {
				((MyE2IF_DB_SimpleComponent)oComp).prepare_ContentForNew(false);
			}
		}
		 
		//2011-01-10: mehrere Subquery-agents
		if (this.v_ComponentMAP_SubqueryAGENT.size()>0)
			this.v_ComponentMAP_SubqueryAGENT.PrepareComponents_For_NEW(this);
		
		
		//2012-12-13:
		this.STATUS_LAST_FILL = E2_ComponentMAP.STATUS_NEW_EMPTY;
		
		//2015-02-25: neue factory-klasse pruefen
		if (this.Factory4Records!=null) {
			this.Record4MainTable = null;
			this.RecordNew4MainTable = this.Factory4Records.get_RecordNew();
		}
		
	}

	
	
	/**
	 * loescht evtl.von der letzten eingabe stehengebliebene 
	 * error-styles
	 */
	private void Reset_MaskErrorStyles()
	{
		
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			Component oComp = (Component)this.get(this.vComponentHashKeys.get(i));
			
			if (oComp instanceof MyE2IF__Component)
			{
				((MyE2IF__Component) oComp).show_InputStatus(true);
			} 
			else if  (oComp instanceof MyE2IF__ComponentContainer)
			{
				Vector<MyE2IF__DB_Component> vHelp = ((MyE2IF__ComponentContainer)oComp).get_vDB_Components();
				for (int k=0;k<vHelp.size();k++)
				{
					if (vHelp.get(k) instanceof MyE2IF__Component)
					{
						((MyE2IF__Component) oComp).show_InputStatus(true);
					}

				}
			}
		}
	}

	
	
	
	/**
	 * neu Einlesen der werte einer vorhanden row
	 */
	public void _DO_REFRESH_COMPONENTMAP(	String 		cSTATUS_MASKE,
											boolean 	doMapSettingBefore,
											Boolean  	SetComponentsEnabled
											) throws myException
	{
		if (this.oInternalSQLResultMAP == null)
			throw new myException("E2_ComponentMap:doRequeryActualResultMAP:Cannot Refresh empty E2_ComponentMap");
		
		String cPrimaryKEYMainTable = this.oInternalSQLResultMAP.get_cUNFormatedROW_ID();
		
		if (bibALL.isEmpty(cPrimaryKEYMainTable))
			throw new myException("E2_ComponentMap:doRequeryActualResultMAP:Cannot Refresh empty E2_ComponentMap with empty Primary-Key-Value");
		
		this.fill_ComponentMapFromDB(null, cPrimaryKEYMainTable, cSTATUS_MASKE,doMapSettingBefore,SetComponentsEnabled);
		
		
		
		/*
		 * 2011-11-29: jetzt steht normalerweise in jedem tochterobjekt ein verweis auf den toggle-button (siehe
		 * get_Copy von E2_componentMAP.
		 * Deswegen wird hier nochmal explizit die schalterstellung diese toggle-buttons korrigiert 
		 * 
		 * alt:
		 * 		if (this.oList_EXPANDER_4_ComponentMAP != null)
		 *      	if (this.oList_EXPANDER_4_ComponentMAP.get_bIsOpen())
		 *      		this.oList_EXPANDER_4_ComponentMAP.refreshDaughterComponent();
		 */
		if (this.oList_EXPANDER_4_ComponentMAP != null)
		{
			E2_ListButtonExtendDaughterObject oToggleButton = this.oList_EXPANDER_4_ComponentMAP.get_ownExpanderButton();
			
			if (this.oList_EXPANDER_4_ComponentMAP.get_bIsOpen())
			{
				this.oList_EXPANDER_4_ComponentMAP.refreshDaughterComponent();
				if (oToggleButton != null)
				{
					oToggleButton.set_bButtonAnsichtOpen(true);
				}
			}
			else
			{
				if (oToggleButton != null)
				{
					oToggleButton.set_bButtonAnsichtOpen(false);
				}
			}
		}
		
		
		//2012-12-13: settings-collection anwenden
		this.hmInteractiv_settings_validation.execute_ComponentMAP_interactiv_settings(this);
		
	}
	
	
	
	
	
	/**
	 * prueffunktion, die checked, ob ein datensatz vor dem speichern 
	 * wo anders geaendert wurde
	 * gibt einen vector mit den infos, was geaendert wurde, zurueck,
	 * infos, die bereits uebersetzt sind
	 */
	public Vector<String> get_ChangedFieldResults() throws myException
	{
		/*
		 * kann nur benutzt werden, wenn die basis-resultmap bereits 
		 * vorhanden ist
		 */

		if (this.oInternalSQLResultMAP == null)
			throw new myException("E2_ComponentMAP:get_ChangedFieldResults:own resultmap is not initialisiert !!");
		
		if (bibALL.isEmpty(this.oInternalSQLResultMAP.get_cUNFormatedROW_ID()))
			throw new myException("E2_ComponentMAP:get_ChangedFieldResults:rowid is empty !!");

		
		Vector<String> vRueck = new Vector<String>();
		SQLResultMAP oTempResultMap = this.createResultMap(this.oInternalSQLResultMAP.get_cUNFormatedROW_ID());
		Vector<String> vFieldLabels = this.oSQLFieldMAP.get_vFieldLabels();
		
		for (int i=0;i<vFieldLabels.size();i++)
		{
			String cResultOld = this.oInternalSQLResultMAP.get_FormatedValue((String)vFieldLabels.get(i));
			String cResultNew = oTempResultMap.get_FormatedValue((String)vFieldLabels.get(i));
			if (!cResultOld.equals(cResultNew))
			{
				SQLField oField = (SQLField)this.oSQLFieldMAP.get(vFieldLabels.get(i));
				String cName = oField.get_cFieldLabelForUser().CTrans();
				vRueck.add(cName+"  ("+cResultOld+"->"+cResultNew+")");
			}
			
		}
		return vRueck;
	}
	
	
	
	
	//2014-03-02:optional:leerer konstruktor, damit existiert am anfang keine MyDBToolBox, und muss generiert werden
	private void check_DBToolbox_and_SqlFieldMAP() throws myException {
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
		}

		if (this.oSQLFieldMAP==null) {
			throw new myException(this,"Error: SQLFieldMAP is undefined !!");
		}
		
	}
	
	


	/*
	 * aufbau einer resultmap fuer beliebige zwecke
	 */
	public SQLResultMAP createResultMap(String cID_MAIN_TABLE_UNFORMATED) throws myException
	{
		//2014-03-02:optional:leerer konstruktor, damit existiert am anfang keine MyDBToolBox, und muss generiert werden
		this.check_DBToolbox_and_SqlFieldMAP();
		
		
		SQLResultMAP oResult = null;
		
		String cQuery = this.oSQLFieldMAP.get_CompleteSQLQueryFor_ONE_ROW(cID_MAIN_TABLE_UNFORMATED);
		
		MyDBResultSet oRS = this.oDB.OpenResultSet(cQuery);
		
		if (oRS.RS != null)
		{
			try
			{
				if (oRS.RS.next())
				{
					oResult = new SQLResultMAP(this.oSQLFieldMAP,oRS.RS);
				}
				else
				{
					oRS.Close();
					throw new myExceptionDataQueryError(myExceptionDataQueryError.ERROR_ID_NOT_FOUND,"E2_ComponentMAP:FillFieldFromDB:ID "+cID_MAIN_TABLE_UNFORMATED+" was not found !");
				}
			}
			catch (SQLException ex)
			{
				oRS.Close();
				throw new myException("E2_ComponentMAP:FillFieldFromDB:SQL-Error: "+ex.getMessage());
			}
			oRS.Close();
		}
		else
			throw new myException("E2_ComponentMAP:FillFieldFromDB:SQL-Error:"+cQuery);

		return oResult;
	}
	
	
	
	
	/**
	 * @param oRs
	 * @param cID_MAIN_TABLE_UNFORMATED
	 * @param cSTATUS_MASKE
	 * @param doMapSettingBefore :  wenn true, dann wird er mapSettingAgent aufgerufen
	 * @param SetComponentsEnabled : wenn null, dann wird das nicht beachtet, sonst wird set_ComponentsEnable(SetComponentsEnabled.booleanValue(),Status) aufgerufen
	 * 
	 * Darf nur eines von beiden oder gar keines uebergeben:
	 * 1. ist Resultset oRS=null und  cID_MAIN_TABLE_UNFORMATED != null dann wird die interne resultmap von mit einer abfrage gefuellt
	 * 2. umgekehrt heisst: die interne resultmap wird mit dem Resultset gebaut
	 * 3. sind beide null, dann wird davon ausgegangen, dass bereits eine resultmap vorhanden ist
	 * 
	 * @throws myException
	 */
	public void fill_ComponentMapFromDB(	ResultSet 	oRs, 
											String 		cID_MAIN_TABLE_UNFORMATED, 
											String 		cSTATUS_MASKE,
											boolean 	doMapSettingBefore,
											Boolean  	SetComponentsEnabled
											) throws myException
	{
		if (oRs != null && !bibALL.isEmpty(cID_MAIN_TABLE_UNFORMATED))
			throw new myException(this,":myException:Error: Both parameters != null is not allowed !");
		
		if (oRs == null && bibALL.isEmpty(cID_MAIN_TABLE_UNFORMATED) && this.oInternalSQLResultMAP==null)
			throw new myException(this,":myException:Error: Both parameters == null: internalResultMap MUST BE NOT NULL !!");

		if (oRs != null)
		{
			this.oInternalSQLResultMAP = new SQLResultMAP(this.oSQLFieldMAP,oRs);
		}
		else if (!bibALL.isEmpty(cID_MAIN_TABLE_UNFORMATED))
		{
			this.oInternalSQLResultMAP = this.createResultMap(cID_MAIN_TABLE_UNFORMATED);
		}
		
		//2015-02-25: neue factory-klasse pruefen
		if (this.Factory4Records!=null) {
			this.Record4MainTable = this.Factory4Records.get_RECORD(this.oInternalSQLResultMAP.get_cUNFormatedROW_ID());
			this.RecordNew4MainTable = this.Factory4Records.get_RecordNew();
		}

		
		
		//jetzt die komponenten fuellen
		this.Reset_MaskErrorStyles();
		
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			Component oComp = (Component)this.get(this.vComponentHashKeys.get(i));
			
			if (oComp instanceof MyE2IF__DB_Component)
			{
				MyE2IF__DB_Component oDBComp = (MyE2IF__DB_Component) oComp;
				
//				DEBUG.System_println(oDBComp.EXT_DB().get_oSQLField().get_cFieldLabel(), null);
				
				String cHelp = this.oInternalSQLResultMAP.get_FormatedValue(oDBComp.EXT_DB().get_oSQLField().get_cFieldLabel());
				oDBComp.set_cActual_Formated_DBContent_To_Mask(cHelp, cSTATUS_MASKE, this.oInternalSQLResultMAP);
			}
			else if  (oComp instanceof MyE2IF__ComponentContainer)
			{
				Vector<MyE2IF__DB_Component> vHelp = ((MyE2IF__ComponentContainer)oComp).get_vDB_Components();
				for (int k=0;k<vHelp.size();k++)
				{
					MyE2IF__DB_Component oDBComp = (MyE2IF__DB_Component) vHelp.get(k);
					String cHelp = this.oInternalSQLResultMAP.get_FormatedValue(oDBComp.EXT_DB().get_oSQLField().get_cFieldLabel());
					oDBComp.set_cActual_Formated_DBContent_To_Mask(cHelp, cSTATUS_MASKE, this.oInternalSQLResultMAP);
				}
			}
			//2015-09-02: weitere differenzierung - nur passive komponente, die an die datenbank angebunden ist
			else if (oComp instanceof MyE2IF_DB_SimpleComponent) {
				((MyE2IF_DB_SimpleComponent)oComp).set_cActual_Formated_DBContent_To_Mask(null, cSTATUS_MASKE, this.oInternalSQLResultMAP);
			}
			
			this.__Check_If_CheckBoxForList_And_set_ToolTips(oComp);
		}
		
		//2011-01-10: mehrere subquery-agents
		if (this.v_ComponentMAP_SubqueryAGENT.size()>0)
			this.v_ComponentMAP_SubqueryAGENT.fillComponents(this, this.oInternalSQLResultMAP);

		if (doMapSettingBefore)
		{
			this.do_MapSettings_BEFORE(cSTATUS_MASKE);
		}
		
		if (SetComponentsEnabled != null)
		{
			this.set_AllComponentsEnabled_For_Edit(SetComponentsEnabled.booleanValue(),cSTATUS_MASKE);
		}
		
		
		//2012-12-13: status setzen
		this.STATUS_LAST_FILL = cSTATUS_MASKE;

		
	}
	
	
	
	private void __Check_If_CheckBoxForList_And_set_ToolTips(Component oComp) throws myException
	{
		if (oComp instanceof E2_CheckBoxForList)
		{
			String cTabelle = this.oInternalSQLResultMAP.get_oSQLFieldMAP().get_cMAIN_TABLE();
			String cID = this.oInternalSQLResultMAP.get_cUNFormatedROW_ID();
			
			((E2_CheckBoxForList)oComp).setToolTipText(new MyE2_String("Tabelle: ",true,cTabelle,false," ... ID: ",true,cID,false).CTrans());
			
			
			if (ENUM_MANDANT_DECISION.ADD_AUTOMATIC_DELETE_AND_STORNO_INFOS.is_YES_FromSession()) {
				String toolTips = ((E2_CheckBoxForList)oComp).getToolTipText();
				boolean firstLineFeed = false;
				for (ENUM_LIST_CHECKBOXINFO infoblock: ENUM_LIST_CHECKBOXINFO.values()) {
					String s  = infoblock.getToolTipBlock(this.oInternalSQLResultMAP);
					if (S.isFull(s)) {
						if (!firstLineFeed) {
							toolTips+="\n";
							firstLineFeed = true;
						}
						toolTips += ("\n"+infoblock.getToolTipBlock(this.oInternalSQLResultMAP));
					}
				}
				((E2_CheckBoxForList)oComp).setToolTipText(toolTips);
			}
			
			
			if (ENUM_MANDANT_DECISION.ADD_HISTORY_INFORMATION_ON_LIST_CHECKBOX.is_YES_FromSession()) {
				// dann tooltips erweitern
				try {
					_TAB tab = _TAB.find_Table(cTabelle.toUpperCase());
					if (tab != null) {
						Rec21 r21 = new Rec21(tab)._fill_id(cID);
						if (r21.is_ExistingRecord()) {
							String 	infoText = ((E2_CheckBoxForList)oComp).getToolTipText()+"\n";
									infoText+= S.ms("\nErsteller: ").CTrans()+  S.NN(r21.getFDbValueFormated("ERZEUGT_VON","??"),"???");
									infoText+= S.ms("\nErstellt am: ").CTrans()+S.NN(r21.getFDbValueFormated("ERZEUGT_AM","??"),"???");
									infoText+= S.ms("\nLetzte Änderung von: ").CTrans()+S.NN(r21.getFDbValueFormated("GEAENDERT_VON","??"),"???");
									infoText+= S.ms("\nLetzte Änderung am: ").CTrans()+S.NN(r21.getFDbValueFormated("LETZTE_AENDERUNG","??"),"???");
									
							((E2_CheckBoxForList)oComp).setToolTipText(infoText);

						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
			
		}
	}
	
	
	
	
	public void set_AllComponentsEnabled_For_Edit(boolean bEnabled, String cSTATUS_MASKE) throws myException
	{
		for (Map.Entry<String, MyE2IF__Component> oEntry : this.hmRealComponents.entrySet())
		{
			oEntry.getValue().EXT().set_bEnabled_For_Edit(bEnabled, cSTATUS_MASKE);
		}
	}
	
	

	/**
	 * 
	 * @return s all fields, which content in editmode has been changed
	 * @throws myException
	 */
	public Vector<String> get_vAllChangedFields() throws myException
	{	
		Vector<String> vRueck = new Vector<String>();
		
		for (Map.Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			if (oEntry.getValue() instanceof MyE2IF__DB_Component)
			{
				MyE2IF__DB_Component oDBComp = (MyE2IF__DB_Component)oEntry.getValue();
				String cOldValue = "";
				if (this.oInternalSQLResultMAP!=null)
				{
					cOldValue = S.NN(this.oInternalSQLResultMAP.get_FormatedValue(oDBComp.EXT_DB().get_oSQLField().get_cFieldLabel()));					
				}
				
				String cNewValue = S.NN(this.get_cActualDBValueFormated(oDBComp.EXT_DB().get_oSQLField().get_cFieldLabel()));
			
				if (!cOldValue.equals(cNewValue))
				{
					vRueck.add(oDBComp.EXT_DB().get_oSQLField().get_cFieldLabel());
				}
			}
		}
		return vRueck;
	}
	
	
	
	
	
	/**
	 * baut eine InputMAP aller datenbank-objekte,
	 * die den schalter get_bGivesBackValueToDB() tragen
	 * @return
	 * @throws myException 
	 */
	public SQLMaskInputMAP get_ActualInputMAP_And_MarkFalseInput() throws myException
	{
		return new SQLMaskInputMAP(this);
	}

	
	
	
	
	
	/**
	 * Sets marker if there is one in the ComponentMAP,
	 * unmarks all others
	 * @param bEnabled
	 */
	public void set_Marker(boolean bEnabled)
	{
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			if (this.get(this.vComponentHashKeys.get(i)) instanceof E2_ButtonListMarker)
			{
				
				if (bEnabled)
				{
					/*
					 * dann erst mal alle, auch sich selbst deaktivieren
					 */
					Vector<E2_ComponentMAP> vAllMapsInList = this.get_VectorComponentMAP_thisBelongsTo();
					
					if (vAllMapsInList != null)
					{
	
						/*
						 * erst alle demarkieren
						 */
						for (int k=0;k<vAllMapsInList.size();k++)
						{
							E2_ComponentMAP oMap2 = (E2_ComponentMAP)vAllMapsInList.get(k);
							E2_ButtonListMarker oMarker2 = (E2_ButtonListMarker)oMap2.get(this.vComponentHashKeys.get(i));
							oMarker2.set_bIsMarked(false);
							// oMap2.set_Marker(false);
						}
					}
				}
				
				E2_ButtonListMarker oButtonMarker = (E2_ButtonListMarker)this.get(this.vComponentHashKeys.get(i));
				oButtonMarker.set_bIsMarked(bEnabled);
			}
		}
		
	}
	
	
	
	
	/**
	 * Sets marker if there is one in the ComponentMAP,
	 * allows multimarkers
	 * @param bEnabled
	 */
	public void set_Marker_not_unmark_others(boolean bEnabled)
	{
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			if (this.get(this.vComponentHashKeys.get(i)) instanceof E2_ButtonListMarker)
			{
				E2_ButtonListMarker oButtonMarker = (E2_ButtonListMarker)this.get(this.vComponentHashKeys.get(i));
				oButtonMarker.set_bIsMarked(bEnabled);
			}
		}
		
	}

	
	
	
	
	//2011-03-02: marker zurueckgeben
	public E2_ButtonListMarker get_Marker()
	{
		E2_ButtonListMarker buttonRueck = null;
		
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			if (this.get(this.vComponentHashKeys.get(i)) instanceof E2_ButtonListMarker)
			{
				buttonRueck=(E2_ButtonListMarker)this.get(this.vComponentHashKeys.get(i));
			}
		}
		
		return buttonRueck;
	}

	
	
	
	/**
	 * @return
	 * true, wenn in der map eine Checkbox-komponente gefunden wird
	 */
	public boolean get_bHasChecked_CheckBoxForList()
	{
		if (this.myOwnCheckboxInList != null)    //zuerst schaeuen, ob die variable gesetzt ist
		{
			return this.myOwnCheckboxInList.isSelected();
		}
		
		
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			if (this.get(this.vComponentHashKeys.get(i)) instanceof E2_CheckBoxForList)
			{
				E2_CheckBoxForList oCheck = (E2_CheckBoxForList)this.get(this.vComponentHashKeys.get(i));
				if (oCheck.isSelected())
					return true;
			}
		}
		return false;
	}
	
	
	
	public boolean isChecked() {
		return this.get_bHasChecked_CheckBoxForList();
	}
	

	/**
	 * wenn in der map eine Checkbox-komponente gefunden wird, wird diese
	 * nach dem booleschen wert gesetzt
	 */
	public void setChecked_CheckBoxForList(boolean bChecked)
	{
		
		if (this.myOwnCheckboxInList != null)    //zuerst schaeuen, ob die variable gesetzt ist
		{
			this.myOwnCheckboxInList.setSelected(bChecked);
			return;
		}
		
		
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			if (this.get(this.vComponentHashKeys.get(i)) instanceof E2_CheckBoxForList)
			{
				E2_CheckBoxForList oCheck = (E2_CheckBoxForList)this.get(this.vComponentHashKeys.get(i));
				oCheck.setSelected(bChecked);
			}
		}

	}

	

	/**
	 * Methode setzt, je nach markierungsmethodik in der navigationliste (additiv oder exclusiv)
	 * die Listen-Checkboxen und  startet danach die markierung der ausgewaehlten zeilen an
	 *
	 */
	public void set_CheckBoxForList_ToggleSelected()
	{
		Vector<E2_ComponentMAP> vVectorComponentMapThisBelongsTo = this.get_VectorComponentMAP_thisBelongsTo();
		
		//2012-08-30: multiselect-option in der liste nutzen
		boolean 			bMultiSelectInList = false;
		E2_NavigationList  	oNaviList = this.get_oNavigationList_This_Belongs_to();
		if (oNaviList!=null)
		{
			bMultiSelectInList = oNaviList.get_bMultiSelectWithButtonsInList();
		}
		
		if (vVectorComponentMapThisBelongsTo!=null)
		{
			if (bMultiSelectInList)
			{
				this.setChecked_CheckBoxForList(!this.get_bHasChecked_CheckBoxForList()); 
			}
			else
			{
				for (E2_ComponentMAP oMapSchleife: vVectorComponentMapThisBelongsTo)
				{
					{
						if (oMapSchleife != this)
						{
							oMapSchleife.setChecked_CheckBoxForList(false);   //alle aus
						}
						else
						{
							oMapSchleife.setChecked_CheckBoxForList(true);   //eigener an
						}
					}
				}
			}
		}
		
		if (oNaviList!=null)
		{
			oNaviList.ShowMessageWithInfoAboutSelectedLinesAndMarkSelectedLines();
		}


	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 07.01.2019
	 *
	 * @return
	 */
	public E2_ComponentMAP _setLineSelected() {
		this.set_CheckBoxForList_ToggleSelected();
		return this;
	}
	
	
	
	/**
	 * @return s Number of components, which are visisble in the list
	 * 
	 */
	public int get_iCountVisibleElementsInList()
	{
		int iRueck = 0;
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component)this.get(this.vComponentHashKeys.get(i));
			if (oComp.EXT().get_bIsVisibleInList())
				iRueck++;
		}
		
		
		return iRueck;
	}
	
	

	
	/**
	 * @return s Number of components, which are visisble in the list
	 * 
	 */
	public Vector<String> get_vVisibleElementsInList()
	{
		Vector<String> vRueck = new Vector<String>();

		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component)this.get(this.vComponentHashKeys.get(i));
			if (oComp.EXT().get_bIsVisibleInList())
			{
				vRueck.add(this.vComponentHashKeys.get(i));
			}
		}
		return vRueck;
	}

	
	

	/**
	 * 2012-11-16: summe alle pixel-spalten-breiten  
	 * @return s null, wenn nicht alle sichtbaren spalten eine pixel-ext haben 
	 */
	public Integer get_iWidth_sum_of_AllVisibleColumns()
	{
		int iGesamtBreite = 0;
		for (int i=0;i<this.vComponentHashKeys.size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component)this.get(this.vComponentHashKeys.get(i));
			if (oComp.EXT().get_bIsVisibleInList())
			{
				if ((oComp.EXT().get_oColExtent()!=null && oComp.EXT().get_oColExtent().getUnits()==Extent.PX))
				{
					iGesamtBreite += oComp.EXT().get_oColExtent().getValue();
				}
				else
				{
					return null;
				}
			}
		}
		return new Integer(iGesamtBreite);
	}
	

	
	
	
	
	
	//2014-04-04: neue version der copy-struktur mit statischer hilfsmethode
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_ComponentMAP oRueck = new E2_ComponentMAP(this.oSQLFieldMAP);
		E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
		
		return oRueck;
	}
	
	
	
	//2014-04-04: auslagerung der uebernahme der inhalt von quelle zu kopie um einfachere Kopiermethoden zu implementieren
	public static void Copy_FieldsAndSettings(E2_ComponentMAP  oMAP_Source, E2_ComponentMAP  oMAP_Target)  throws myExceptionCopy {
		oMAP_Target.set_bIsCopy(true);
		
		//2011-01-10: mehrere Subquery-Agents
		//oRueck.set_oSubQueryAgent(this.get_oSubQueryAgent());
		oMAP_Target.get_vComponentMapSubQueryAgents().addAll(oMAP_Source.get_vComponentMapSubQueryAgents());
		
		XX_List_EXPANDER_4_ComponentMAP oDaughter_Expander = null;
		
		if (oMAP_Source.get_oList_EXPANDER_4_ComponentMAP() != null)
		{
			oDaughter_Expander = (XX_List_EXPANDER_4_ComponentMAP)oMAP_Source.get_oList_EXPANDER_4_ComponentMAP().get_Copy(null);
			oMAP_Target.set_List_EXPANDER_4_ComponentMAP(oDaughter_Expander);
			oDaughter_Expander.set_E2_ComponentMAP_this_BelongsTo(oMAP_Target);
		}
		
		for (int i=0;i<oMAP_Source.get_vComponentHashKeys().size();i++)
		{
			String cHashKey = (String) oMAP_Source.get_vComponentHashKeys().get(i);
			String cHashNew = new String(cHashKey);
			MyE2IF__Component oComp = ((MyE2IF__Component)oMAP_Source.get(cHashKey));
			Component oComp2 = (Component) ((E2_IF_Copy)oComp).get_Copy(null);
			
			try
			{
				if (oComp2 instanceof MyE2IF__DB_Component)
				{
					String cHashKeyFromDataLabel = ((MyE2IF__DB_Component)oComp).EXT_DB().get_oSQLField().get_cFieldLabel();
					
					if (cHashKeyFromDataLabel.equals(cHashNew))           //dann alles ok, normales datenbank-objekt
					{
						oMAP_Target.add_Component((MyE2IF__DB_Component)oComp2,((MyE2IF__Component)oComp2).EXT().get_cList_or_Mask_Titel());
					}
					else   //sonder-datenbank-feld, z.b. komplexes feld mit von aussen gesteuertem hashkey
					{
						oMAP_Target.add_Component(cHashNew,(MyE2IF__Component)oComp2,((MyE2IF__Component)oComp2).EXT().get_cList_or_Mask_Titel());
					}
				}
				else
				{
					oMAP_Target.add_Component(cHashNew,(MyE2IF__Component)oComp2,((MyE2IF__Component)oComp2).EXT().get_cList_or_Mask_Titel());
				}
					
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.getOriginalMessage()+" - "+ myExceptionCopy.ERROR_COPYING);
			}
			
			
			//2011-11-29: dem Expander-Objekt wird waehrend des kopierens ein E2_ListButtonExtendDaughterObject - zugewiesen, wenn die mutter einen solchen beinhaltet
			if (oComp2 != null &&  oDaughter_Expander != null &&  oComp2 instanceof E2_ListButtonExtendDaughterObject)
			{
				oDaughter_Expander.set_ownExpanderButton( (E2_ListButtonExtendDaughterObject)oComp2);
			}
			
		}
		oMAP_Target.set_oMAPSettingAgent(oMAP_Source.get_oMAPSettingAgent());
		for (int k=0;k<oMAP_Source.get_vMAPValidator().size();k++)
		{
			oMAP_Target.add_oMAPValidator((XX_MAP_ValidBeforeSAVE)oMAP_Source.get_vMAPValidator().get(k));
		}
	
		oMAP_Target.get_V_ADDON_SQL_STATEMENT_BUILDER().addAll(oMAP_Source.get_V_ADDON_SQL_STATEMENT_BUILDER());
		
		
		//2010-12-21: permanentes ausklappen von tochterkomponenten uebernehmen
		oMAP_Target.set_bExtendSublistsAutomatic(oMAP_Source.get_bExtendSublistsAutomatic());
		
		
		//2012-12-20: kopie der feldvalidierer
		oMAP_Target.set_hmInteractiv_settings_validation((HM_interactiv_settings_validation)oMAP_Source.get_hmInteractiv_settings_validation().get_Copy(null));

		//2015-02-25: kopier der RecordFactory
		oMAP_Target.set_Factory4Records(oMAP_Source.get_Factory4Records());
		
		//2015-07-08: kopier der einstellung wie deleted-zeilen markiert werden
		oMAP_Target.set_DeletedSetting_newVersion(oMAP_Source.is_DeletedSetting_newVersion());
		
	}

	
	

	public SQLResultMAP 						get_oInternalSQLResultMAP()					{		return oInternalSQLResultMAP;		}
	public Vector<String> 						get_vComponentHashKeys()					{		return vComponentHashKeys;	}
	public SQLFieldMAP 							get_oSQLFieldMAP()							{		return oSQLFieldMAP;	}

	public Vector<E2_ComponentMAP> get_VectorComponentMAP_thisBelongsTo()									{		return vVectorComponentMAP_thisBelongsTo;	}
	public void set_VectorComponentMAP_thisBelongsTo(	Vector<E2_ComponentMAP> vectorComponentMAP_thisBelongsTo)	
	{		vVectorComponentMAP_thisBelongsTo = vectorComponentMAP_thisBelongsTo;	}

	public XX_MAP_SettingAgent 			get_oMAPSettingAgent()			{		return oMAPSettingAgent;	}
	public void set_oMAPSettingAgent(XX_MAP_SettingAgent settingAgent)	{		oMAPSettingAgent = settingAgent;	}
	public void add_oMAPValidator(XX_MAP_ValidBeforeSAVE validator)		{		this.vMAPValidator.add(validator);	}
	
	public void set_InternResultMAP_TO_NULL()
	{
		this.oInternalSQLResultMAP = null;
	}

	public void set_InternResultMAP(SQLResultMAP oResultMap)
	{
		this.oInternalSQLResultMAP = oResultMap;
	}


	
	/**
	 * leerer Vector heist: alles ok
	 */
	public MyE2_MessageVector make_Setting_AND_Validation_Components__BeforeReadInputMAP( String cSTATUS_MAP) throws myException
	{
		MyE2_MessageVector vErrors = new MyE2_MessageVector();
		
		Vector<MyE2IF__Component> vComps =  this.get_REAL_ComponentVector();
		for (MyE2IF__Component oComp: vComps)
		{
			vErrors.add_MESSAGE(oComp.EXT().make_Setting_AND_Validation__BeforeReadInputMAP(cSTATUS_MAP));
		}
		
		return vErrors;
	}

	
	
	
	
	/**
	 * leerer Vector heist: alles ok
	 */
	public MyE2_MessageVector make_Setting_AND_Validation_Components__AfterReadInputMAP( String cSTATUS_MAP) throws myException
	{
		MyE2_MessageVector vErrors = new MyE2_MessageVector();
		Vector<MyE2IF__Component> vComps =  this.get_REAL_ComponentVector();
		
		for (MyE2IF__Component oComp: vComps)
		{
			vErrors.add_MESSAGE(oComp.EXT().make_Setting_AND_Validation__AfterReadInputMAP( cSTATUS_MAP));
		}
		
		return vErrors;
	}

	
	
	
	
	/**
	 * leerer Vector heist: alles ok
	 */
	public MyE2_MessageVector make_MapValidation( SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
	
		for (XX_MAP_ValidBeforeSAVE oValid: this.vMAPValidator)
		{
			vRueck.add_MESSAGE(oValid.doValidation(this,oInputMap,cSTATUS_MASKE));
		}
		return vRueck;
	}
	
	
	
	public void do_MapSettings_BEFORE(String cSTATUS_MASKE) throws myException
	{
		if (this.oMAPSettingAgent != null)
		{
			this.reset_InteractiveSettings(this.get_REAL_ComponentVector());
			this.oMAPSettingAgent.doSettings_BEFORE(this,cSTATUS_MASKE);
		}
	}
	
	public void do_MapSettings_AFTER(String cSTATUS_MASKE) throws myException
	{
		if (this.oMAPSettingAgent != null)
			this.oMAPSettingAgent.doSettings_AFTER(this,cSTATUS_MASKE);
	}
	

	

	/*
	 * zuruecksetzen der feld-parameter, die in der regel im map-setting-agent gesetzt werden
	 */
	private void reset_InteractiveSettings(Vector<MyE2IF__Component> vRealCompVector)
	{
		for (int i=0;i<vRealCompVector.size();i++)
		{
			vRealCompVector.get(i).EXT().set_bDisabledFromInteractive(false);
			
			if (vRealCompVector.get(i) instanceof MyE2IF__DB_Component)
			{
				((MyE2IF__DB_Component)vRealCompVector.get(i)).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true); 
			}
		}
	}
	
	
	
	
	
	/**
	 * @returns Vector mit allen Einzelkomponenten, die in der map vorhanden sind,
	 * auch die in multicolumn oder multirow-objekten
	 */
	public Vector<MyE2IF__Component> get_REAL_ComponentVector()
	{
		Vector<MyE2IF__Component> vComponents = new Vector<MyE2IF__Component>();
		for (String cHashKey:this.get_vComponentHashKeys())
		{
			MyE2IF__Component oComp = this.get(cHashKey);
			
			if (oComp instanceof MyE2_DB_MultiComponentColumn)
			{
				vComponents.addAll( ((MyE2_DB_MultiComponentColumn)oComp).get_vComponents());
			}
			else if (oComp instanceof MyE2_DB_MultiComponentRow)
			{
				vComponents.addAll( ((MyE2_DB_MultiComponentRow)oComp).get_vComponents());
			}
			else if (oComp instanceof MyE2_DB_MultiComponentGrid)
			{
				vComponents.addAll( ((MyE2_DB_MultiComponentGrid)oComp).get_vComponents());
			}
			else
				vComponents.add(oComp);
		}

		return vComponents;
	}
	

	/**
	 * @returns HashMap mit allen Einzelkomponenten, die in der map vorhanden sind,
	 * auch die in multicolumn oder multirow-objekten (nur die komponenten, die das interface MyE2IF__Component implementieren
	 */
	public HashMap<String,MyE2IF__Component> get_REAL_ComponentHashMap()
	{
		HashMap<String,MyE2IF__Component> oComponents = new HashMap<String,MyE2IF__Component>();
		for (int k=0;k<this.get_vComponentHashKeys().size();k++)
		{
			MyE2IF__Component oComp = this.get(this.get_vComponentHashKeys().get(k));
			if (oComp instanceof MyE2IF__ComponentContainer)
			{
				Vector<MyE2IF__Component> vInnerComponents = ((MyE2IF__ComponentContainer)oComp).get_vComponents();
				for (int l=0;l<vInnerComponents.size();l++)
				{
					MyE2IF__Component oInnerComp = (MyE2IF__Component)vInnerComponents.get(l);
					oComponents.put(oInnerComp.EXT().get_C_HASHKEY(),oInnerComp);
				}
			}
			else
			{
				oComponents.put(oComp.EXT().get_C_HASHKEY(),oComp);
			}
		}

		return oComponents;
	}
	
	
	
	
	/**
	 * Auslesen der komplexen felder die ganze sql-stacks zurueckgegeben
	 * @param oE2_ComponentMAP
	 * @param oInputMAP
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_InsertStackFromComplexFields(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		Vector<String> vRueck = new Vector<String>();
		Vector<MyE2IF__Component> vComponents = this.get_REAL_ComponentVector();
		
		for (int i=0;i<vComponents.size();i++)
		{
			if (vComponents.get(i) instanceof MyE2IF__DB_Component)
				if (((MyE2IF__DB_Component)vComponents.get(i)).get_bIsComplexObject())
				{
					Vector<String> vAdd = ((MyE2IF__DB_Component)vComponents.get(i)).get_vInsertStack(oE2_ComponentMAP, oInputMAP);
					if (vAdd != null)
						vRueck.addAll(vAdd);
				}
		}
		
		return vRueck;
	}
	
	
	
	
	/**
	 * Auslesen der komplexen felder die ganze sql-stacks zurueckgegeben
	 * @param oE2_ComponentMAP
	 * @param oInputMAP
	 * @return
	 * @throws myException
	 */
	public Vector<String>  get_UpdateStackFromComplexFields(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		Vector<String>  vRueck = new Vector<String> ();
		Vector<MyE2IF__Component> vComponents = this.get_REAL_ComponentVector();
		
		for (int i=0;i<vComponents.size();i++)
		{
			if (vComponents.get(i) instanceof MyE2IF__DB_Component)
				if (((MyE2IF__DB_Component)vComponents.get(i)).get_bIsComplexObject())
				{
					Vector<String>  vAdd = ((MyE2IF__DB_Component)vComponents.get(i)).get_vUpdateStack(oE2_ComponentMAP, oInputMAP);
					if (vAdd != null)
						vRueck.addAll(vAdd);
				}
		}
		
		return vRueck;
	}
	



	
	/** 
	 * 2012-02-07: neue version, complex-db-fields auch unsortierbar
	 * @Disables sorting in all DB-Components
	 * @param bEnabled
	 */
	public void set_allDBComponents_Sortable(boolean bEnabled)
	{
		Vector<MyE2IF__Component> vComponents = this.get_REAL_ComponentVector();
		
		for (int i=0;i<vComponents.size();i++)
		{
			if (vComponents.get(i) instanceof MyE2IF__DB_Component)
			{
				((MyE2IF__DB_Component)vComponents.get(i)).EXT_DB().set_bIsSortable(bEnabled);
			}
		}
	}
	
	
	
//	-------------------------------
	
	//2012-01-12: neue methoden zum inaktiv-machen der maske
	//2012-01-12
	public void set_bEnabled_For_Edit_ALL(boolean bEnabled) throws myException
	{
		Iterator<MyE2IF__Component> oIter = this.get_hmRealComponents().values().iterator();
		while (oIter.hasNext())
		{
			oIter.next().EXT().set_bEnabled_For_Edit(bEnabled);
		}
	}
	//2012-01-12
	public void set_bEnabled_For_Edit_and_set_DisabledFromBasicFlag_ALL(boolean bEnabled) throws myException
	{
		Iterator<MyE2IF__Component> oIter = this.get_hmRealComponents().values().iterator();
		while (oIter.hasNext())
		{
			MyE2IF__Component oComp=oIter.next();
			oComp.EXT().set_bEnabled_For_Edit(bEnabled);
			oComp.EXT().set_bDisabledFromBasic(!bEnabled);
		}
	}
	//2012-01-12
	public void set_bEnabled_For_Edit_and_set_DisabledFromInteractiveFlag_ALL(boolean bEnabled) throws myException
	{
		Iterator<MyE2IF__Component> oIter = this.get_hmRealComponents().values().iterator();
		while (oIter.hasNext())
		{
			MyE2IF__Component oComp=oIter.next();
			oComp.EXT().set_bEnabled_For_Edit(bEnabled);
			oComp.EXT().set_bDisabledFromInteractive(!bEnabled);
		}
	}

	
//	-------------------------------
	

	/**
	 * @param FieldsList (ie: ":NAME:VORNAME:STRASSE:" )
	 * @param bDisable
	 * set for all MyE2IF__DB_Component - fields with the hash contained in FieldsList the 
	 * set_bDisabledFromInteractive-flag
	 * 
	 */
	public void set_DisabledFromInteractive( String FieldsList, String cTrenner,boolean bDisable)
	{
		Vector<MyE2IF__Component> vAllComponents = this.get_REAL_ComponentVector();
		for (int i=0;i<vAllComponents.size();i++)
		{
			if (vAllComponents.get(i) instanceof MyE2IF__Component)
			{
				MyE2IF__Component oComp = (MyE2IF__Component) vAllComponents.get(i);
				
				if (FieldsList.indexOf(cTrenner+oComp.EXT().get_C_HASHKEY()+cTrenner)>=0)
					oComp.EXT().set_bDisabledFromInteractive(bDisable);
			}
		}

	}


	/**
	 * @param FieldsList (ie: ":NAME:VORNAME:STRASSE:" )
	 * @param bDisable
	 * set for all MyE2IF__DB_Component - fields with the hash contained in FieldsList the 
	 * set_bDisabledFromInteractive-flag
	 * 
	 */
	public void set_DisabledFromBasic( String FieldsList, String cTrenner,boolean bDisable)
	{
		Vector<MyE2IF__Component> vAllComponents = this.get_REAL_ComponentVector();
		for (int i=0;i<vAllComponents.size();i++)
		{
			if (vAllComponents.get(i) instanceof MyE2IF__Component)
			{
				MyE2IF__Component oComp = (MyE2IF__Component) vAllComponents.get(i);
				
				if (FieldsList.indexOf(cTrenner+oComp.EXT().get_C_HASHKEY()+cTrenner)>=0)
					oComp.EXT().set_bDisabledFromBasic(bDisable);
			}
		}

	}


	
	public void set_DisabledFromBasic_ALL(boolean bDisabled)
	{
		for (Entry<String, MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			oEntry.getValue().EXT().set_bDisabledFromBasic(bDisabled);
		}
	}


	public void set_DisabledFromInteractiv_ALL(boolean bDisabled)
	{
		for (Entry<String, MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			oEntry.getValue().EXT().set_bDisabledFromInteractive(bDisabled);
		}
	}


	
	
	public Component get_Comp(String cHashName) throws myException
	{
		Component oCompRueck = (Component)this.get(cHashName);
		
		if (oCompRueck == null)
			throw new myException("E2_ComponentMAP:get_Comp:Component not found: "+cHashName);
		
		return oCompRueck;
		
	}


	
	
	

	
	public MyE2IF__Component get__Comp_From_RealComponents(String cHashName) throws myException
	{
		MyE2IF__Component oCompRueck = this.hmRealComponents.get(cHashName);
		
		if (oCompRueck == null)
			throw new myException("E2_ComponentMAP:get__Comp:Component not found: "+cHashName);
		
		return oCompRueck;
	}

	
	
	
	
	public MyE2IF__Component get__Comp(String cHashName) throws myException
	{
		MyE2IF__Component oCompRueck = (MyE2IF__Component)this.get(cHashName);
		
		if (oCompRueck == null)
			throw new myException("E2_ComponentMAP:get__Comp:Component not found: "+cHashName);
		
		return oCompRueck;
	}
	

	public MyE2IF__Component get__Comp(IF_Field field) throws myException
	{
		return this.get__Comp(field.fn());
	}
	
	//2016-04-28: weiterer getter
	public Component get__CompEcho(IF_Field field) throws myException 	{
		return (Component)this.get__Comp(field.fn());
	}
	//2018-03-05: weiterer getter
	public Component get__CompEcho(String s_field) throws myException 	{
		return (Component)this.get__Comp(s_field);
	}

	
	/*
	 * methode, um ein Feld einer Componentmap in einer aktion waehrend der eingabe aktiv oder inakiv zu schalten
	 * aenderung 20101019: beim inaktiv-schalten auch inhalt loeschen
	 */
	public void set_ActiveADHOC(String cHashName, boolean bActive, boolean bResetWhenInactiv)  throws myException
	{
		MyE2IF__Component oComp = get__Comp_From_RealComponents(cHashName);
		
		oComp.EXT().set_bDisabledFromInteractive(!bActive);
		oComp.set_bEnabled_For_Edit(bActive);
		
		if (bResetWhenInactiv && (!bActive))
		{
			if (oComp instanceof MyE2IF__DB_Component)
			{
				((MyE2IF__DB_Component)oComp).prepare_ContentForNew(false);
			}
		}
	}
	
	
	/*
	 * methode, um eine Feldliste einer Componentmap in einer aktion waehrend der eingabe aktiv oder inakiv zu schalten
	 * aenderung 20101019: beim inaktiv-schalten auch inhalt loeschen
	 */
	public void set_ActiveADHOC(Vector<String> vHashName, boolean bActive, boolean bResetWhenInactiv)  throws myException
	{
		for (String cFeldname: vHashName)
		{
			this.set_ActiveADHOC(cFeldname, bActive, bResetWhenInactiv);
		}
	}
	
	
	
	
	
	
	
	public MyE2IF__DB_Component get__DBComp(String cHashName) throws myException
	{
		MyE2IF__Component oCompRueck = (MyE2IF__Component)this.get(cHashName);
		
		if (oCompRueck == null)
			throw new myException("E2_ComponentMAP:get__Comp:Component not found: "+cHashName);
		
		if (!(oCompRueck instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP: NO MyE2IF__DB_Component: "+cHashName);
		
		
		return (MyE2IF__DB_Component)oCompRueck;
	}

	
	public MyE2IF__Component get__Comp(int iNumberInMap) throws myException
	{
		if (iNumberInMap >= this.vComponentHashKeys.size())
			throw new myException("E2_ComponentMAP:get__Comp:Number too big !");
		
		MyE2IF__Component oCompRueck = (MyE2IF__Component)this.get(this.vComponentHashKeys.get(iNumberInMap));
		
		if (oCompRueck == null)
			throw new myException("E2_ComponentMAP:get__Comp:Component not found: "+iNumberInMap);
		
		return oCompRueck;
	}
	
	
	
	//2011-01-10: mehrere Subquery-Agents
	public V_ComponentMAP_SubqueryAGENT get_vComponentMapSubQueryAgents()
	{
		return this.v_ComponentMAP_SubqueryAGENT;
	}

	
	//2011-01-10: bleibt aus kompatibilitaet noch da
	public void set_oSubQueryAgent(XX_ComponentMAP_SubqueryAGENT subQueryAgent)
	{
		this.v_ComponentMAP_SubqueryAGENT.removeAllElements();
		this.v_ComponentMAP_SubqueryAGENT.add(subQueryAgent);
	}
	public void add_oSubQueryAgent(XX_ComponentMAP_SubqueryAGENT subQueryAgent)
	{
		this.v_ComponentMAP_SubqueryAGENT.add(subQueryAgent);
	}
	

	public E2_ComponentMAP _clearSubQueryAgents() {
		this.v_ComponentMAP_SubqueryAGENT.clear();
		return this;
	}
	
	public E2_ComponentMAP _addSubQueryAgent(XX_ComponentMAP_SubqueryAGENT agent) {
		this.v_ComponentMAP_SubqueryAGENT.add(agent);
		return this;
	}
	
	/**
	 * fuer die nutzung in lambdas
	 * @author martin
	 * @date 11.02.2019
	 *
	 * @param agent
	 * @return
	 */
	public E2_ComponentMAP _addSubQueryFormatter(If_SubQueryFormatter agent) {
		this.v_ComponentMAP_SubqueryAGENT.add(agent.genSubQueryAgent());
		return this;
	}
	
	
	public XX_List_EXPANDER_4_ComponentMAP get_List_EXPANDER_4_ComponentMAP()
	{
		return oList_EXPANDER_4_ComponentMAP;
	}



	public void set_List_EXPANDER_4_ComponentMAP(XX_List_EXPANDER_4_ComponentMAP daughterInList)
	{
		oList_EXPANDER_4_ComponentMAP = daughterInList;
		oList_EXPANDER_4_ComponentMAP.set_E2_ComponentMAP_this_BelongsTo(this);
	}
	

	
	/**
	 * @see evtl. vorhandene tochterdarstellung schliessen, dazu auch eventuelle ausklappbuttons einklappen 
	 */
	public void set_OPEN_DaughterObjectForList_With_ToggleButton(boolean bOpen)
	{
//		Vector<MyE2IF__Component> vReal = this.get_REAL_ComponentVector();
//		
//		for (int i=0;i<vReal.size();i++)
//		{
//			if (vReal.get(i) instanceof E2_ListButtonExtendDaughterObject)
//			{
		for (Entry<String, MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			if (oEntry.getValue() instanceof E2_ListButtonExtendDaughterObject)
			{
				//E2_ListButtonExtendDaughterObject oButton = (E2_ListButtonExtendDaughterObject)vReal.get(i);
				E2_ListButtonExtendDaughterObject oButton = (E2_ListButtonExtendDaughterObject)oEntry.getValue();
				oButton.set_bIsOpen(bOpen);
			}
		}
		
		if (this.oList_EXPANDER_4_ComponentMAP != null)
			this.oList_EXPANDER_4_ComponentMAP.set_bIsOpen(bOpen);
		
		
	}


	/**
	 * @see evtl. vorhandene tochterdarstellung schliessen, dazu auch eventuelle ausklappbuttons einklappen 
	 */
	public E2_ListButtonExtendDaughterObject get_OPEN_ToggleButton()
	{
		E2_ListButtonExtendDaughterObject oRueck = null;
		
		for (Entry<String, MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			if (oEntry.getValue() instanceof E2_ListButtonExtendDaughterObject)
			{
				oRueck = (E2_ListButtonExtendDaughterObject)oEntry.getValue();
				break;
			}
		}

		return oRueck;
	}

	
	
	public E2_vCombinedComponentMAPs get_E2_vCombinedComponentMAPs()
	{
		return oE2_vCombinedComponentMAPs;
	}

	public void set_E2_vCombinedComponentMAPs(E2_vCombinedComponentMAPs combinedComponentMAPs)
	{
		oE2_vCombinedComponentMAPs = combinedComponentMAPs;
	}

	

	/**
	 * @param bSetDefaults
	 * @param vFieldList
	 * @param bIncludeList
	 * @throws myException
	 */
	public void prepare_ContentForNew(	boolean 		bSetDefaults, 
										Vector<String>	vFieldList, 
										boolean 		bIncludeList, 
										boolean 		bUse_ALL_FROM_CombinedComponentMAPS) throws myException
	{

		Vector<MyE2IF__Component> vReal = null;
		if (bUse_ALL_FROM_CombinedComponentMAPS)
		{
			if (this.oE2_vCombinedComponentMAPs != null)
				vReal = this.oE2_vCombinedComponentMAPs.get_REAL_ComponentVector_OfAllMAPS();
			else
				vReal = this.get_REAL_ComponentVector();
		}
		else
			vReal = this.get_REAL_ComponentVector();
		
		
		for (int i=0;i<vReal.size();i++)
		{
			if (vReal.get(i) instanceof MyE2IF__DB_Component)
			{
				MyE2IF__DB_Component oComp = (MyE2IF__DB_Component)vReal.get(i);
				
				if (bIncludeList)
				{	
					if (vFieldList.contains(oComp.EXT_DB().get_oSQLField().get_cFieldLabel()))
						((MyE2IF__DB_Component)vReal.get(i)).prepare_ContentForNew(bSetDefaults);
				}
				else
				{
					if (!vFieldList.contains(oComp.EXT_DB().get_oSQLField().get_cFieldLabel()))
						((MyE2IF__DB_Component)vReal.get(i)).prepare_ContentForNew(bSetDefaults);
				}
			}
		}
	}

	
	

	/**
	 * 
	 * @param bDisabled
	 * @param vFieldList
	 * @param bIncludeList
	 * @param bUse_ALL_FROM_CombinedComponentMAPS
	 */
	public void set_bDisabledFromInteractive(boolean bDisabled, Vector<String> FieldList, boolean bIncludeList, boolean bUse_ALL_FROM_CombinedComponentMAPS)
	{
		Vector<String> vFieldList = new Vector<String>();
		
		if (FieldList != null)
			vFieldList.addAll(FieldList);
		
		Vector<MyE2IF__Component> vReal = null;
		if (bUse_ALL_FROM_CombinedComponentMAPS)
		{
			if (this.oE2_vCombinedComponentMAPs != null)
				vReal = this.oE2_vCombinedComponentMAPs.get_REAL_ComponentVector_OfAllMAPS();
			else
				vReal = this.get_REAL_ComponentVector();
		}
		else
			vReal = this.get_REAL_ComponentVector();

		
		for (int i=0;i<vReal.size();i++)
		{
			if (vReal.get(i) instanceof MyE2IF__Component)
			{
				MyE2IF__Component oComp = (MyE2IF__Component)vReal.get(i);
				
				if (bIncludeList)
				{	
					if (vFieldList.contains(oComp.EXT().get_C_HASHKEY()))
						oComp.EXT().set_bDisabledFromInteractive(bDisabled);
				}
				else
				{
					if (!vFieldList.contains(oComp.EXT().get_C_HASHKEY()))
						oComp.EXT().set_bDisabledFromInteractive(bDisabled);
					
				}
			}
		}
		
	}
	

	
	
	


	/**
	 * 
	 * @param bEnabled
	 * @param bSet_Disabled_From_Interactive_Flag (wenn true, dann wird EXT().bDisabledFromInteractive = !bEnabled
	 * @param vFieldList
	 * @param bIncludeList
	 * @param bUse_ALL_FROM_CombinedComponentMAPS
	 * @throws myException
	 */
	public void set_bEnabled_For_Edit(	boolean 			bEnabled, 
										boolean   			bSet_Disabled_From_Interactive_Flag,
										Vector<String> 		vFieldList, 
										boolean 			bIncludeList,
										boolean 			bUse_ALL_FROM_CombinedComponentMAPS) throws myException
	{

		Vector<MyE2IF__Component> vReal = null;
		if (bUse_ALL_FROM_CombinedComponentMAPS)
		{
			if (this.oE2_vCombinedComponentMAPs != null)
				vReal = this.oE2_vCombinedComponentMAPs.get_REAL_ComponentVector_OfAllMAPS();
			else
				vReal = this.get_REAL_ComponentVector();
		}
		else
			vReal = this.get_REAL_ComponentVector();
		
		
		for (MyE2IF__Component oComp:vReal)
		{
			if (bIncludeList)
			{	
				if (vFieldList.contains(oComp.EXT().get_C_HASHKEY()))
				{
					if (bSet_Disabled_From_Interactive_Flag)
					{
						oComp.EXT().set_bDisabledFromInteractive(!bEnabled);   //sonst laueft das ganze ins leere (evtl.)
					}
					oComp.set_bEnabled_For_Edit(bEnabled);
				}
			}
			else
			{
				if (!vFieldList.contains(oComp.EXT().get_C_HASHKEY()))
				{
					if (bSet_Disabled_From_Interactive_Flag)
					{
						oComp.EXT().set_bDisabledFromInteractive(!bEnabled);   //sonst laueft das ganze ins leere
					}
					
					oComp.set_bEnabled_For_Edit(bEnabled);
				}
			
			}
		}
		
	}

	

	
	/**
	 * 
	 * @param cHASH_KEY
	 * @param bNullValueWhenEmpty
	 * @param bNullValueWhenFalseInput
	 * @param nullValue
	 * @return
	 * @throws myException
	 */
	public Long get_LActualDBValue(String cHASH_KEY,boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Long nullValue) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_IActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_IActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_LActualDBValue(bNullValueWhenEmpty, bNullValueWhenFalseInput, nullValue);
		
	}
	
	
	/**
	 * 
	 * @param cHASH_KEY
	 * @param bNullValueWhenEmpty
	 * @param bNullValueWhenFalseInput
	 * @param nullValue
	 * @return
	 * @throws myException
	 */
	public Double get_DActualDBValue(String cHASH_KEY,boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Double nullValue) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_DActualDBValue(bNullValueWhenEmpty, bNullValueWhenFalseInput, nullValue);
		
	}


	
	
	public MyDate get_ActualMyDate(String cHASH_KEY,boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, MyDate nullValue) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_ActualMyDate: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_ActualMyDate: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_MyDateActualDBValue(bNullValueWhenEmpty, bNullValueWhenFalseInput, nullValue);
		
	}

	
	
	
	
	public Long get_LActualDBValue(String cHASH_KEY,Long lValueWhenEmpty, Long lValueWhenFalseInput) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_IActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_IActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_LActualDBValue(lValueWhenEmpty, lValueWhenFalseInput);
		
	}
	
	
	public Double get_DActualDBValue(String cHASH_KEY,Double dValueWhenEmpty, Double dValueWhenFalseInput) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_DActualDBValue(dValueWhenEmpty, dValueWhenFalseInput);
		
	}

	

	public BigDecimal get_bdActualDBValue(String cHASH_KEY,BigDecimal bdValueWhenEmpty, BigDecimal bdValueWhenFalseInput) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_bdActualDBValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_bdActualDBValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_bdActualDBValue(bdValueWhenEmpty, bdValueWhenFalseInput);
		
	}

	
	
	

	public TestingDate get_DateActualDBValue(String cHASH_KEY,boolean bNullWhenEmpty, boolean bNullWhenFalseDateInput) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		return ((MyE2IF__DB_Component)ocomp).EXT_DB().get_DateActualMaskValue(bNullWhenEmpty,bNullWhenFalseDateInput);
		
	}

	
	
	
	
	
	public String get_cActualDBValueFormated(String cHASH_KEY) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).get_cActualDBValueFormated();
		
	}


	public boolean get_bActualDBValue(String cHASH_KEY) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("E2_ComponentMAP:get_DActualMaskFieldValue: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		
		return ((MyE2IF__DB_Component)ocomp).get_cActualDBValueFormated().equals("Y");
		
	}


	
	public String get_cActualDBValueFormated(String cHASH_KEY, String cValueWhenNullOrEmpty) throws myException
	{
		String cRueck = this.get_cActualDBValueFormated(cHASH_KEY);
		if (S.isEmpty(cRueck))
			return cValueWhenNullOrEmpty;
		
		return cRueck;
	}

	
	public void set_cActualDatabaseValueToMask(String cHASH_KEY, String cValue4Mask) throws myException
	{
		if ( bibALL.isEmpty(cHASH_KEY))
			throw new myException("set_cActualDatabaseValueToMask: empty HASHKEY is not allowed !!");

		MyE2IF__Component ocomp = this.get__Comp_From_RealComponents(cHASH_KEY);
		
		if ( !(ocomp instanceof MyE2IF__DB_Component))
			throw new myException("set_cActualDatabaseValueToMask: <"+bibALL.null2leer(cHASH_KEY)+"> is not an MyE2IF__DB_Component !");

		((MyE2IF__DB_Component)ocomp).set_cActualMaskValue(cValue4Mask);
	}
	
	
	
	/**
	 * @param bRemoveStatemtentsAfterGet
	 * @return
	 */
	public Vector<String> get_vInsert_ADDON_STATEMENTS(boolean bRemoveStatemtentsAfterGet)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.addAll(this.vZusatzSQLStatements_INSERT);
		
		if (bRemoveStatemtentsAfterGet)
		{
			this.vZusatzSQLStatements_INSERT.removeAllElements();
		}
		
		return vRueck;
	}
	
	/**
	 * @param bRemoveStatemtentsAfterGet
	 * @return
	 */
	public Vector<String> get_vUpdate_ADDON_STATEMENTS(boolean bRemoveStatemtentsAfterGet)
	{
		Vector<String>  vRueck = new Vector<String> ();

		vRueck.addAll(this.vZusatzSQLStatements_UPDATE);
		
		if (bRemoveStatemtentsAfterGet)
		{
			this.vZusatzSQLStatements_UPDATE.removeAllElements();
		}
		
		return vRueck;
	}
	

	/**
	 * @param vZusatzSQL
	 * @param bRemoveOldStatementsBeforeSet
	 */
	public void set_Insert_ADDON_Statements(Vector<String> vZusatzSQL, boolean bRemoveOldStatementsBeforeSet)
	{
		if (bRemoveOldStatementsBeforeSet)
		{
			this.vZusatzSQLStatements_INSERT.removeAllElements();
		}
		
		this.vZusatzSQLStatements_INSERT.addAll(vZusatzSQL);
	}
	
	/**
	 * @param vZusatzSQL
	 * @param bRemoveOldStatementsBeforeSet
	 */
	public void set_Update_ADDON_Statements(Vector<String> vZusatzSQL, boolean bRemoveOldStatementsBeforeSet)
	{
		if (bRemoveOldStatementsBeforeSet)
		{
			this.vZusatzSQLStatements_UPDATE.removeAllElements();
		}
		this.vZusatzSQLStatements_UPDATE.addAll(vZusatzSQL);
	}

	
	
	/**
	 * @param oResultMAP
	 * @param oMV
	 * @param cSTATUS
	 * @return
	 * Methode fuehrt den pruefzyklus mit allen Validatoren in der richtigen reihenfolge durch
	 * @throws myException
	 */
	public SQLMaskInputMAP MakeCompleteCycle_of_Validation_After_Input(	SQLResultMAP 		oResultMAP, 
																		MyE2_MessageVector 	oMV, 
																		String 				cSTATUS) throws myException
	{
		
		//zu allererst die validierung der einzelfelder (vor dem Einlesen)
		oMV.add_MESSAGE(this.make_Setting_AND_Validation_Components__BeforeReadInputMAP(cSTATUS));
		
		//dann werden die fehleingaben auf SQLField-Ebene markiert
		SQLMaskInputMAP 	oInputMap = this.get_ActualInputMAP_And_MarkFalseInput();		// inputmap

	
		// dann wird der maskenvaildator durchlaufen
		oMV.add_MESSAGE(this.make_MapValidation(oInputMap,cSTATUS));

		// dann wird die validierung der einzelfelder (nach dem Einlesen) gemacht
		oMV.add_MESSAGE(this.make_Setting_AND_Validation_Components__AfterReadInputMAP( cSTATUS));

		// dann werden die eingabewerte geprueft, alles gut heist: vector ist leer
		oMV.add_MESSAGE(this.get_oSQLFieldMAP().get_vCheckNewValues(oResultMAP,oInputMap));

		//2011-05-06: weitere validierungsebene, die auch beim auslesen der inputmap wie eine falscheingabe wirkt
		oMV.add_MESSAGE(this.make_Field_Validation_Check_Input_and_MarkFalseValues());

		return oInputMap;
	}

	

	//2011-05-06: weiterer validierer, der formal auf jedem feld wie eine falsche eingabe wirkt
	public MyE2_MessageVector make_Field_Validation_Check_Input_and_MarkFalseValues() throws myException
	{
		Iterator<MyE2IF__Component>  oIter = this.hmRealComponents.values().iterator();
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		while (oIter.hasNext())
		{
			MyE2IF__Component oComp = oIter.next();
			oMV.add_MESSAGE(oComp.EXT().get_Field_Validation_Check_Input_and_MarkFalseValues());
		}
		return oMV;
	}
	
	
	
	
	public HashMap<String, MyE2IF__Component> get_hmRealComponents()
	{
		return hmRealComponents;
	}
	
	public HashMap<String, MyE2IF__DB_Component> get_hmRealDBComponents()
	{
		return hmRealDBComponents;
	}
	
	
	

	/**
	 * 
	 * @throws myException
	 */
	public void set_All_DB_ComponentsAsDisableFromInteractive() throws myException
	{

		for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			if 		(oEntry.getValue() instanceof MyE2IF__DB_Component)
			{
				oEntry.getValue().EXT().set_bDisabledFromInteractive(true);
			}
		}
	}

	
	/**
	 * 
	 * @throws myException
	 */
	public void set_All_NONDB_ComponentsAsDisableFromInteractive() throws myException
	{

		for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
		{
			if 		(!(oEntry.getValue() instanceof MyE2IF__DB_Component))
			{
				oEntry.getValue().EXT().set_bDisabledFromInteractive(true);
			}
		}
	}


	
	
	
	
	/**
	 * methode fuer listen, um komponenten als geloescht anzuzeigen
	 * Sollte in XX_ComponentMAP_SubqueryAGENT angewendet werden
	 */
	public void set_AllComponentsAsDeleted() throws myException
	{
		if (this.markingAgent==null) {
			if (! this.deletedSetting_newVersion) {
	
				
				/*
				 * jetzt geloeschte datensaetze markieren
				 */
				Font oDelFont = bibE2.get_Font4DeletedLinesInLists();
		
				
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
				{
					if 		(oEntry.getValue() instanceof MyE2_Label)
						((MyE2_Label)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_TextField)
						((MyE2_TextField)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_TextArea)
						((MyE2_TextArea)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_Button)
						((MyE2_Button)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_SelectField)
					{
						((MyE2_SelectField)oEntry.getValue()).setFont(oDelFont);
						((MyE2_SelectField)oEntry.getValue()).setForeground(new E2_ColorLLLight());
					}
					else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor)
					{
						((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField().setFont(oDelFont);
					}
					else if (oEntry.getValue() instanceof MyE2IF_IsMarkable)
					{
						((MyE2IF_IsMarkable)oEntry.getValue()).make_Look_Deleted(true);
					}
		
				}
			} else {
				//2015-07-08: neue methode der deleted-markierung
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet()) {
					 if (oEntry.getValue() instanceof MyE2IF_IsMarkable) {
						((MyE2IF_IsMarkable)oEntry.getValue()).make_Look_Deleted(true);
					} else if (oEntry.getValue() instanceof MyE2_SelectField) {
						bibFONT.change_fontToLineThrough((Component)oEntry.getValue(), true);;
						((MyE2_SelectField)oEntry.getValue()).setForeground(new E2_ColorLLLight());
					} else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor) {
						bibFONT.change_fontToLineThrough(((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField(), true);;
					} else if (oEntry.getValue() instanceof Component) {
						bibFONT.change_fontToLineThrough((Component)oEntry.getValue(), true);;
					}
				}
	
				
			}
		}
	}
	
	
	
	
	/**
	 * 2012-06-19: formatierer um in zeilen bestimmte sachverhalte durch spezielle textfarbe anzuzeigen
	 * 
	 */
	public void set_AllComponentsWithForeColor(Color  oForeColor) throws myException
	{
		if (this.markingAgent==null) {

			if (oForeColor != null)
			{
			
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
				{
					if (oEntry.getValue() instanceof MyE2IF_IsMarkable)
					{
						((MyE2IF_IsMarkable)oEntry.getValue()).setForeColorActive(oForeColor);
					}
					else if (oEntry.getValue() instanceof MyE2_Label)
						((MyE2_Label)oEntry.getValue()).setForeground(oForeColor);
					else if (oEntry.getValue() instanceof MyE2_TextField)
						((MyE2_TextField)oEntry.getValue()).setForeground(oForeColor);
					else if (oEntry.getValue() instanceof MyE2_TextArea)
						((MyE2_TextArea)oEntry.getValue()).setForeground(oForeColor);
					else if (oEntry.getValue() instanceof MyE2_Button)
						((MyE2_Button)oEntry.getValue()).setForeground(oForeColor);
					else if (oEntry.getValue() instanceof MyE2_SelectField)
					{
						((MyE2_SelectField)oEntry.getValue()).setForeground(oForeColor);
					}
					else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor)
					{
						((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField().setForeground(oForeColor);
					}
	
				}
			}
		}
	}
	

	
	
	
	
	/**
	 * methode fuer listen, um komponenten als geloescht anzuzeigen
	 * Sollte in XX_ComponentMAP_SubqueryAGENT angewendet werden
	 */
	public void set_AllComponentsAsDeleted(Vector<String> vHashKeysOfNotMarkedComponents) throws myException
	{
		if (this.markingAgent==null) {    //sonst macht der alles
		
			if (! this.deletedSetting_newVersion) {
	
				/*
				 * jetzt geloeschte datensaetze markieren
				 */
				E2_Font oDelFont = new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2);
		
				
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
				{
					if (vHashKeysOfNotMarkedComponents.contains(oEntry.getKey()))
					{
						continue;
					}
					
					if 		(oEntry.getValue() instanceof MyE2_Label)
						((MyE2_Label)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_TextField)
						((MyE2_TextField)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_TextArea)
						((MyE2_TextArea)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_Button)
						((MyE2_Button)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_SelectField)
					{
						((MyE2_SelectField)oEntry.getValue()).setFont(oDelFont);
						((MyE2_SelectField)oEntry.getValue()).setForeground(new E2_ColorLLLight());
					}
					else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor)
					{
						((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField().setFont(oDelFont);
					}
					else if (oEntry.getValue() instanceof MyE2IF_IsMarkable)
					{
						((MyE2IF_IsMarkable)oEntry.getValue()).make_Look_Deleted(true);
					}
		
				}
			} else {
				//2015-07-08: neue methode der deleted-markierung
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet()) {
					if (vHashKeysOfNotMarkedComponents.contains(oEntry.getKey())) {
						continue;
					}
	
					if (oEntry.getValue() instanceof MyE2IF_IsMarkable) {
						((MyE2IF_IsMarkable)oEntry.getValue()).make_Look_Deleted(true);
					} else if (oEntry.getValue() instanceof MyE2_SelectField) {
						bibFONT.change_fontToLineThrough((Component)oEntry.getValue(), true);;
						((MyE2_SelectField)oEntry.getValue()).setForeground(new E2_ColorLLLight());
					} else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor) {
						bibFONT.change_fontToLineThrough(((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField(), true);;
					} else if (oEntry.getValue() instanceof Component) {
						bibFONT.change_fontToLineThrough((Component)oEntry.getValue(), true);;
					}
				}
	
			}
		}
	}

	
	
	
	/**
	 * methode fuer listen, um komponenten als geloescht anzuzeigen
	 * Sollte in XX_ComponentMAP_SubqueryAGENT angewendet werden
	 */
	public void set_AllComponentsAsNormal()
	{
		if (this.markingAgent==null) {
		
			if (! this.deletedSetting_newVersion) {
	
				/*
				 * jetzt geloeschte datensaetze markieren
				 */
				E2_Font oDelFont = new E2_Font(Font.PLAIN,0);
		
				
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet())
				{
					if 		(oEntry.getValue() instanceof MyE2_Label)
						((MyE2_Label)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_TextField)
						((MyE2_TextField)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_TextArea)
						((MyE2_TextArea)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_Button)
						((MyE2_Button)oEntry.getValue()).setFont(oDelFont);
					else if (oEntry.getValue() instanceof MyE2_SelectField)
					{
						((MyE2_SelectField)oEntry.getValue()).setFont(oDelFont);
						((MyE2_SelectField)oEntry.getValue()).setForeground(null);
		
					}
					else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor) {
						((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField().setFont(oDelFont);
						
					} else if (oEntry.getValue() instanceof MyE2IF_IsMarkable) {     //2013-06-03: markable allgemein hinzugefuegt 
						((MyE2IF_IsMarkable)oEntry.getValue()).make_Look_Deleted(false);
					}
				}
			} else {
				//2015-07-08: neue methode der deleted-markierung
				for (Entry<String,MyE2IF__Component> oEntry: this.hmRealComponents.entrySet()) {
					if (oEntry.getValue() instanceof MyE2IF_IsMarkable) {
						((MyE2IF_IsMarkable)oEntry.getValue()).make_Look_Deleted(false);
					} else if (oEntry.getValue() instanceof MyE2_SelectField) {
						bibFONT.change_fontToLineThrough((Component)oEntry.getValue(), false);;
						((MyE2_SelectField)oEntry.getValue()).setForeground(new E2_ColorLLLight());
					} else if (oEntry.getValue() instanceof MyE2_DB_TextField_WithSelektor) {
						bibFONT.change_fontToLineThrough(((MyE2_DB_TextField_WithSelektor)oEntry.getValue()).get_oTextField(), false);;
					} else if (oEntry.getValue() instanceof Component) {
						bibFONT.change_fontToLineThrough((Component)oEntry.getValue(), false);;
					}
				}
	
			}
		}
	}
	
	
	public E2_BasicModuleContainer_MASK get_oModulContainerMASK_This_BelongsTo()
	{
		return oModulContainerMASK_This_BelongsTo;
	}

	public void set_oModulContainerMASK_This_BelongsTo(E2_BasicModuleContainer_MASK modulContainerMASK_This_BelongsTo)
	{
		oModulContainerMASK_This_BelongsTo = modulContainerMASK_This_BelongsTo;
	}


	
	
	public boolean get_bIs_Neueingabe()
	{
		return (this.oInternalSQLResultMAP == null);
	}
	
	public boolean get_bIs_Edit()
	{
		return !this.get_bIs_Neueingabe();
	}

	
	/*
	 * in der Maske bestimmte Felder (->vFieldNames) neu einlesen, damit die maskenpruefung auf aenderung
	 * von aussen in die datenbank gespeicherte veraenderungen nicht bemerkt
	 */
	public void update_ResultMAP_and_MaskComponents(Vector<String>  vFieldNames) throws myException
	{
		if (this.get_oInternalSQLResultMAP() == null)
			throw new myException(this,"Is only allowed in EDIT-Mode !");
		
		SQLResultMAP oResult = this.createResultMap(this.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		
		if (vFieldNames == null)
		{
			//alle updaten
			this.oInternalSQLResultMAP.putAll(oResult);
			Iterator<Entry<String, MyResultValue>> it = oResult.entrySet().iterator(); 

			while (it.hasNext()) 
			{
			    Map.Entry<String, MyResultValue> entry = (Map.Entry<String, MyResultValue>)it.next();
			    this.oInternalSQLResultMAP.put((String)entry.getKey(), oResult.get(entry.getKey()));
			} 			

		}
		else
		{
			for (String cField:vFieldNames)
			{
				if (!this.oInternalSQLResultMAP.containsKey(cField))
				{
					throw new myException(this,"ResultMap does not has key: "+cField);
				}
				this.oInternalSQLResultMAP.put(cField, oResult.get(cField));
				
				if (this.hmRealDBComponents.containsKey(cField))
				{
					this.hmRealDBComponents.get(cField).set_cActualMaskValue(oResult.get_FormatedValue(cField));
				}
			}
		}
	}
	
	
	/*
	 * zusatzstatements fuer den speicher-sql-stack
	 */
	public Vector<builder_AddOnSQL_STATEMENTS> get_V_ADDON_SQL_STATEMENT_BUILDER()
	{
		return vADDON_SQL_STATEMENT_BUILDER;
	}

	
	
	public static interface builder_AddOnSQL_STATEMENTS
	{
		public Vector<String>  get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP, MyE2_MessageVector oMV) throws myException;
		public Vector<String>  get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP, MyE2_MessageVector oMV) throws myException;
	}

	public static interface builder_ActionsAfterSaveMask
	{
		public void  doActionAfterUPDATE(E2_ComponentMAP oE2_ComponentMAP) throws myException;
		public void  doActionAfterNEW(E2_ComponentMAP oE2_ComponentMAP) throws myException;
	}

	
	public E2_NavigationList get_oNavigationList_This_Belongs_to()
	{
		return oNavigationList_This_Belongs_to;
	}

	public void set_oNavigationList_This_Belongs_to(	E2_NavigationList navigationList_This_Belongs_to)
	{
		oNavigationList_This_Belongs_to = navigationList_This_Belongs_to;
	}

	public boolean get_bExtendSublistsAutomatic() {
		return bExtendSublistsAutomatic;
	}

	public void set_bExtendSublistsAutomatic(boolean bExtendSublistsAutomatic) {
		this.bExtendSublistsAutomatic = bExtendSublistsAutomatic;
	}

	
	
	//2012-05-10: experimentell: Spalten einer Liste verschieben
	//verschiebt die members des Vectors "vComponentHashKeys"
	public boolean move_Row(String cHashKey, long iTargetposition)
	{
		boolean bRueck = true;
		
		//zuerst feststellen, ob der hash enthalten isst
		if (this.vComponentHashKeys.contains(cHashKey))
		{
			Vector<String> vNeuKeys = new Vector<String>();
			for (int i=0;i<this.vComponentHashKeys.size();i++)
			{
				if (i==iTargetposition)
				{
					vNeuKeys.add(cHashKey);
				}
				
				//alle aus dem zu verschiebenden key werden nacheinander eingehaengt
				if (! this.vComponentHashKeys.get(i).equals(cHashKey))
				{
					vNeuKeys.add(this.vComponentHashKeys.get(i));
				}
			}

			if (!vNeuKeys.contains(cHashKey))   //dann war die zielposition zu gross
			{
				vNeuKeys.add(cHashKey);
			}
			
			//jetzt inhalte tauschen
			this.vComponentHashKeys.removeAllElements();
			this.vComponentHashKeys.addAll(vNeuKeys);
			
			
		}
		else
		{
			bRueck = false;
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Eine Spalte mit dem Kenner: ",true,cHashKey,false," ist nicht vorhanden !",true)));
		}
		
		return bRueck;
	}
	
	
	
	//2012-08-31: Zeile je nach zustand markieren oder entmarkieren
	public void set_ZeileMarkOrUnmark(Color ColorMark, Font Font4Mark)
	{
		if (this.get_bHasChecked_CheckBoxForList())
		{
			this.set_ZeileMark(ColorMark, Font4Mark);
		}
		else
		{
			this.set_ZeileUnMark();
		}
		
	}
	
	
	
	//2014-02-24: neu-strukturierte markierungsmethoden
	
	//2012-08-30: markierte Componentmap mit hervorgehobenen LayoutData darstellen
	private void set_ZeileMark(Color ColorMark, Font FontMark)
	{

		//20190221: neue markierung
		if (this.markingAgent!= null) {
			this.markingAgent.formatComponentMap();
		} else {
			if (this.hm_unmarkedColorAndOthers.size()==0)   //nur dann ist die zeile unmarkiert
			{
				for (String cHash: this.hmRealComponents.keySet())
				{
					MyE2IF__Component 	oComp = this.hmRealComponents.get(cHash);
					
					if (oComp instanceof MyE2IF_IsMarkable) {  //falls eine indirekte komponente, dann wird die ersatzkomponente markiert
					
						MyE2IF_IsMarkable omComp = (MyE2IF_IsMarkable)oComp;
						this.hm_unmarkedColorAndOthers.put(cHash, new MarkColorAndOthers(omComp.get_Unmarked_ForeColor(), omComp.get_Unmarked_Font()));
	
						omComp.setForeColorActive(ColorMark);
						if (FontMark!=null) {omComp.setFontActive(FontMark);}
						
					} else if (oComp instanceof Component)	{
						
						Component omComp = (Component)oComp;
						this.hm_unmarkedColorAndOthers.put(cHash, new MarkColorAndOthers(omComp.getForeground(), omComp.getFont()));
						omComp.setForeground(ColorMark); 	
						
						//2015-07-08: neue deleted-markerung
						if (this.deletedSetting_newVersion) {
							if (FontMark!=null) {
								omComp.setFont(bibFONT.equal_LineThrough_status(FontMark, omComp));
							}		
						} else {
							if (FontMark!=null) {omComp.setFont(FontMark);}		  //falls der font bei markieren null ist, dann bleibt der listenfont erhalten
						}
					} else {
						this.hm_unmarkedColorAndOthers.put(cHash, new MarkColorAndOthers(Color.BLACK, new E2_FontPlain()));    //nur sicherheitshalber 
					}
				}
			}
		}
	}
	
	
	//2012-08-30: markierte Componentmap mit hervorgehobenen LayoutData darstellen
	public void set_ZeileUnMark()
	{
		//20190221: neue markierung
		if (this.markingAgent!= null) {
			this.markingAgent.formatComponentMap();
		} else {

			if (this.hm_unmarkedColorAndOthers.size()>0)   //nur dann ist das eine markierte zeile
			{
				for (String cHash: this.hmRealComponents.keySet())
				{
					MyE2IF__Component 	oComp = this.hmRealComponents.get(cHash);
					
					MarkColorAndOthers oMarker = this.hm_unmarkedColorAndOthers.get(cHash);
					
					if (oComp instanceof MyE2IF_IsMarkable) {  //falls eine indirekte komponente, dann wird die ersatzkomponente markiert
					
						MyE2IF_IsMarkable omComp = (MyE2IF_IsMarkable)oComp;
	
						omComp.setForeColorActive(oMarker.oColor);
						omComp.setFontActive(oMarker.oFont);
						
					} else if (oComp instanceof Component) {
						Component omComp = (Component)oComp;
						omComp.setForeground(oMarker.oColor); 	
						
						//2015-07-08: neue deleted-markerung
						if (this.deletedSetting_newVersion) {
							omComp.setFont(bibFONT.equal_LineThrough_status(oMarker.oFont,omComp)); //stellt den linethrough-status auf den vorherigen
						} else {
							omComp.setFont(oMarker.oFont);		               //ein font sollte immer vorhanden sein
						}
					}
					
				}
				this.hm_unmarkedColorAndOthers.clear();
			}
		}
	}
	



	
	
	private class MarkColorAndOthers extends Object
	{
		public Color oColor = null;
		public Font  oFont  = null;
		
		public MarkColorAndOthers(Color color, Font font)
		{
			super();
			this.oColor = 	color;
			this.oFont = 	font;
		}
	}


	//2012-12-13:
	public String get_STATUS_LAST_FILL()
	{
		return STATUS_LAST_FILL;
	}
	
	//2012-12-13: workaround wegen bug (wird nur in der E2_vCombinedComponentMAPS verwendet)
	public void set_STATUS_LAST_FILL_TO_NEW_COPY() 
	{
		this.STATUS_LAST_FILL=E2_ComponentMAP.STATUS_NEW_COPY;
	}

	
	//2012-12-13:
	public HM_interactiv_settings_validation get_hmInteractiv_settings_validation()
	{
		return hmInteractiv_settings_validation;
	}
	
	//2012-12-13:
	public void set_hmInteractiv_settings_validation(HM_interactiv_settings_validation oSetAndValid)
	{
		this.hmInteractiv_settings_validation=oSetAndValid;
	}
	
	//2013-07-26: zeigt, ob es eine kopie ist
	public boolean get_bIsCopy()
	{
		return bIsCopy;
	}

	
	public void set_bIsCopy(boolean isCopy)
	{
		this.bIsCopy = isCopy;
	}


	/**
	 * 2013-12-04: validierer anmelden, bei ersten annmelden wird automatische ein MapValidator fuer speichern hinterlegt und den 
	 *             hash-key zugeordneten objekten wird ein actionagent hinterlegt
	 * @param cHASH4Component, oValidator
	 */
	public void register_Interactiv_settings_validation(String cHASH4Component, XX_MAP_Set_And_Valid oValidator) throws myException {
		
		if (this.keySet().contains(cHASH4Component)) {
			
			MyE2IF__Component oComp = this.get(cHASH4Component);
			
			if (oComp != null) {
				this.get_hmInteractiv_settings_validation().put_(cHASH4Component, oValidator);
			} else {
				throw new myException(this,"Validation-Component not found!");
			}
			
			
			if (oComp instanceof E2_IF_Handles_ActionAgents) {

				//dafuer sorgen, dass bei mehrfacher zuordnung eines validier-objects der actionagent nur einemal zugeordnet wird
				Vector<XX_ActionAgent>  vActionAgents = ((E2_IF_Handles_ActionAgents)oComp).get_vActionAgents();
				boolean bIstBereitsDa = false;
				for (XX_ActionAgent oAgent: vActionAgents) {
					if (oAgent instanceof ownClickActionAgent) {
						bIstBereitsDa = true;
					}
				}
				
				if (!bIstBereitsDa) {
					((E2_IF_Handles_ActionAgents)oComp).add_oActionAgent(new ownClickActionAgent());
				}
			}
			
			//jetzt pruefen, ob es im MapValidator-Vector einen ownMapValidatorBeforeSave gibt, wenn nein, einen einfuegen
			boolean bIstVorhanden = false;
			for (XX_MAP_ValidBeforeSAVE oValid: this.vMAPValidator) {
				if (oValid instanceof ownMapValidatorBeforeSave) {
					bIstVorhanden=true;
				}
			}
			if (!bIstVorhanden) {
				this.add_oMAPValidator(new ownMapValidatorBeforeSave());
			}
			
			
		} else {
			throw new myException(this,"Validation-Component not found!");
		}
		
	}
	
	private class ownMapValidatorBeforeSave extends XX_MAP_ValidBeforeSAVE
	{
		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			return oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(oMap, oInputMap);
		}
	}
	

	private class ownClickActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP oMAP = oExecInfo.get_ComponentMAP_of_KLICK_COMPONENT();
			if (oMAP==null) {
				throw new myException(this,"Cannot find ComponentMAP! ");
			} else {
				oMAP.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMAP,oExecInfo);
			}
		}
		
	}


	//2014-04-04
	public XX_List_EXPANDER_4_ComponentMAP get_oList_EXPANDER_4_ComponentMAP() {
		return oList_EXPANDER_4_ComponentMAP;
	}


	public Vector<XX_MAP_ValidBeforeSAVE> get_vMAPValidator() {
		return vMAPValidator;
	}


	//2015-02-25: anbindung an RB-klassen mittels Factoryklasse. Diese kann je ein passendes record und record_new - object bauen
	public E2_ComponentMAP_Factory4Records get_Factory4Records() {
		return Factory4Records;
	}


	public void set_Factory4Records(E2_ComponentMAP_Factory4Records factory4Records) {
		Factory4Records = factory4Records;
		if (this.Factory4Records != null) {
			this.Factory4Records.set_E2_ComponentMap_This_BelongsTo(this);
		}
	}


	public MyRECORD_IF_FILLABLE get_RecordNew4MainTable() {
		return RecordNew4MainTable;
	}


	public MyRECORD_IF_RECORDS get_Record4MainTable() {
		return Record4MainTable;
	}
	//2015-02-25: anbindung an RB-klassen mittels Factoryklasse. Diese kann je ein passendes record und record_new - object bauen



	//2015-07-08: neue behandlung der geloescht-darstellung
	public boolean is_DeletedSetting_newVersion() {
		return this.deletedSetting_newVersion;
	}


	//2015-07-08: neue behandlung der geloescht-darstellung
	public void set_DeletedSetting_newVersion(boolean p_deletedSetting_newVersion) {
		this.deletedSetting_newVersion = p_deletedSetting_newVersion;
	}


	/**
	 * methode sucht die referenz der componente in der Referenzmap der Navigationlist
	 * @param comp
	 * @return
	 */
	public static boolean is_visible_in_NavigationList(MyE2IF__Component comp) throws myException {
		
		String 				cHASHKey = 	comp.EXT().get_C_HASHKEY();
		E2_ComponentMAP  	map = 		comp.EXT().get_oComponentMAP();
		
		if (S.isFull(cHASHKey) && map!=null) {
			E2_NavigationList  naviList = map.get_oNavigationList_This_Belongs_to();
			
			
			if (naviList != null) {
				E2_ComponentMAP map_ref = naviList.get_oComponentMAP__REF();
				
				if (map_ref!=null) {
					
					//gesucht wird in der realComponentmap
					MyE2IF__Component ref_comp = map_ref.get_hmRealComponents().get(cHASHKey);
					
					if (ref_comp!=null) {
						
						return ref_comp.EXT().get_bIsVisibleInList();
						
					}
				}
			}
		}
		throw new myException("E2_ComponentMAP.is_visible_in_NavigationList: Component not found: "+S.NN(cHASHKey));
	}


	/**
	 * uebertraegt den originalen vektor der hashkeys in den sicherungsvektor
	 */
	public void save_OriginalHashKeys() {
		this.vComponentHashKeysSaveOrig.clear();
		for (String s: this.vComponentHashKeys) {
			this.vComponentHashKeysSaveOrig.addElement(s);
		}
	}

	public Vector<String> get_vComponentHashKeysSaveOrig() {
		return vComponentHashKeysSaveOrig;
	}
	

	
	
	/**
	 * mehrere titelkomponenten mit dem gleichen titel-gridlayoutdata formatieren
	 * @param gld
	 * @param componentKeys (String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLayoutTitles(GridLayoutData gld, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			this.get__Comp(o).EXT().set_oLayout_ListTitelElement(gld);
		}
		return this;
	}

	/**
	 * mehrere komponenten mit dem gleichen titel-gridlayoutdata formatieren
	 * @param gld
	 * @param componentKeys (String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLayoutElements(GridLayoutData gld, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			this.get__Comp(o).EXT().set_oLayout_ListElement(gld);
		}
		return this;
	}


	
	/**
	 * alle titelkomponenten ausser den angegebenen formatieren
	 * @param gld
	 * @param componentKeys (String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLayoutTitlesAllBut(GridLayoutData gld, String ... componentKeys) throws myException {
		VEK<String> v = new VEK<String>()._a(componentKeys);
		for (String o: this.keySet()) {
			if (!v.contains(o)) {
				this.get__Comp(o).EXT().set_oLayout_ListTitelElement(gld);
			}
		}
		return this;
	}

	/**
	 * alle komponenten ausser den angegebenen formatieren
	 * @param gld
	 * @param componentKeys (nur String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLayoutElementsAllBut(GridLayoutData gld, String ... componentKeys) throws myException {
		VEK<String> v = new VEK<String>()._a(componentKeys);

		for (String o: this.keySet()) {
			if (!v.contains(o)) {
				this.get__Comp(o).EXT().set_oLayout_ListElement(gld);
			}
		}
		return this;
	}

	/**
	 * fuer mehrere titelkomponenten den zeilenumbruch im titel einfuehren
	 * @param lineWrap
	 * @param componentKeys (nur String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLineWrapListHeader(boolean lineWrap, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			this.get__Comp(o).EXT().set_bLineWrapListHeader(lineWrap);
		}
		return this;
	}

	

	

	/**
	 * fuer mehrere titelkomponenten ein tooltip auf den header setzen
	 * @param tooltip
	 * @param componentKey (nString)
	 * @throws myException
	 */
	public E2_ComponentMAP _setToolTipToHeader(MyE2_String tooltip, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			this.get__Comp(o).EXT().set_ToolTipStringForListHeader(tooltip);
		}
		return this;
	}


	/**
	 * fuer mehrere titelkomponenten eine spaltenbreite auf den header setzen
	 * @param tooltip
	 * @param componentKey (nString)
	 * @throws myException
	 */
	public E2_ComponentMAP _setColExtent(Extent width, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			this.get__Comp(o).EXT().set_oColExtent(width);
		}
		return this;
	}

	
	/**
	 * @param tooltip
	 * @param componentKey (String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setHeaderComponent(String componentKey, Component c) throws myException {
		this.get__Comp(componentKey).EXT().set_oCompTitleInList(c);
		return this;
	}

	
	
	/**
	 * fuer mehrere komonenten (nur typen: Textfield, TextArea, Selectfield) eine eine width setzen
	 * @param tooltip
	 * @param componentKey (nString)
	 * @throws myException
	 */
	public E2_ComponentMAP _setWidth(int width, String ... componentKeys) throws myException {
		for (String s: componentKeys) {
			MyE2IF__Component comp = this.get(s);
			if (comp!=null) {
				if        (comp instanceof TextField) 		{ ((TextField)comp).setWidth(new Extent(width));  	}
				else if   (comp instanceof TextArea) 		{ ((TextArea)comp).setWidth(new Extent(width));  	}
				else if   (comp instanceof SelectField) 	{ ((SelectField)comp).setWidth(new Extent(width)); }
			} else {
				throw new myException(this,"field:"+s+" .. not found !");
			}
		}
		return this;
	}

	/**
	 * fuer mehrere komonenten (nur typen: Textfield, TextArea, Selectfield) eine eine width setzen
	 * @param tooltip
	 * @param componentKey (nString)
	 * @throws myException
	 */
	public E2_ComponentMAP _setWidth(int width, IF_Field ... componentKeys) throws myException {
		for (IF_Field f: componentKeys) {
			MyE2IF__Component comp = this.get(f.fn());
			if (comp!=null) {
				if        (comp instanceof TextField) 		{ ((TextField)comp).setWidth(new Extent(width));  	}
				else if   (comp instanceof TextArea) 		{ ((TextArea)comp).setWidth(new Extent(width));  	}
				else if   (comp instanceof SelectField) 	{ ((SelectField)comp).setWidth(new Extent(width)); }
			} else {
				throw new myException(this,"field:"+f.fn()+" .. not found !");
			}
		}
		return this;
	}


	public HashMap<String, Rec21> getHmRec21() {
		return hmRec21;
	}


	
	/**
	 * fuer mehrere titelkomponenten den zeilenumbruch im titel einfuehren (hier in allen komponenten, auch innerhalb containern)
	 * @param lineWrap
	 * @param componentKeys (nur String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLineWrapListHeaderInAll(boolean lineWrap, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			MyE2IF__Component c = this.hmRealComponents.get(o);
			if (c==null) {
				throw new myException(this,"Component "+S.NN(o)+" was not found in E2_ComponentMAP");
			}
			c.EXT().set_bLineWrapListHeader(lineWrap);
		}
		return this;
	}

	

	/**
	 * mehrere komponenten mit dem gleichen titel-gridlayoutdata formatieren (hier in allen komponenten, auch innerhalb containern)
	 * @param gld
	 * @param componentKeys (String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLayoutElementsInAll(GridLayoutData gld, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			MyE2IF__Component c = this.hmRealComponents.get(o);
			if (c==null) {
				throw new myException(this,"Component "+S.NN(o)+" was not found in E2_ComponentMAP");
			}
			c.EXT().set_oLayout_ListElement(gld);
		}
		return this;
	}

	
	/**
	 * mehrere titelkomponenten mit dem gleichen titel-gridlayoutdata formatieren (hier in allen komponenten, auch innerhalb containern)
	 * @param gld
	 * @param componentKeys (String)
	 * @throws myException
	 */
	public E2_ComponentMAP _setLayoutTitlesInAll(GridLayoutData gld, String ... componentKeys) throws myException {
		for (String o: componentKeys) {
			MyE2IF__Component c = this.hmRealComponents.get(o);
			if (c==null) {
				throw new myException(this,"Component "+S.NN(o)+" was not found in E2_ComponentMAP");
			}
			c.EXT().set_oLayout_ListTitelElement(gld);
		}
		return this;
	}


	/**
	 * 
	 * @author martin
	 * @date 21.02.2019
	 *
	 * @return
	 */
	public E2_ComponentMapMarker getComponentMapMarker() {
		return markingAgent;
	}


	public E2_ComponentMAP setComponentMapMarker(E2_ComponentMapMarker markingAgent) {
		this.markingAgent = markingAgent;
		return this;
	}
	
}
