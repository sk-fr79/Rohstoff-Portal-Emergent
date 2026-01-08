package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest_Container_IF;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;





public class EMS_LIST_BasicModule_Inlay extends E2_BasicModuleContainer implements TS_Treasure_Chest_Container_IF
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_LIST =				E2_MODULNAME_ENUM.MODUL.NAME_MANDANT_MASKENMODUL_EMAIL_SCHABLONEN_LIST.get_callKey();
//	public static final String MODULKENNER_ADDON_MASK =			E2_MODULNAME_ENUM.MODUL.NAME_MANDANT_MASKENMODUL_EMAIL_SCHABLONEN_MASK.get_callKey();


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private EMS_LIST_SqlFieldMAP oSQLFieldMapList = new EMS_LIST_SqlFieldMAP();

    //hier den namen des ID-Feldes der eigenen Tabelle (Tochter), was die Verbindung zur Muttertabelle herstellt (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   							=  _DB.EMAIL_SEND_SCHABLONE$ID_MANDANT;
	
//	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON 	= "MAIL_SCHABLONE_"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorDark();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;


	
	//die listenbuttons muessen exportiert werden koennen
	private EMS_bt_NewListToMask  oButtonNewInList = null; 
	private EMS_bt_ViewListToMask oButtonViewInList = null; 
	private EMS_bt_EditListToMask oButtonEditInList = null; 
	private EMS_LIST_BT_DELETE 	oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;


	private String    ID_MANDANT_in_Mask = null;
	
	

	public EMS_LIST_BasicModule_Inlay(String id_mandant_uf, boolean edit) throws myException
	{
		super();
		
		this.ID_MANDANT_in_Mask = id_mandant_uf;
		
		this.set_MODUL_IDENTIFIER(EMS_LIST_BasicModule_Inlay.MODULKENNER_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new E2_NavigationList();
		
		this.oNaviList.INIT_WITH_ComponentMAP(new EMS_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		this.oNaviList.set_oDBToolBox(new EMS_DBToolBox());   //toolbox ohne automatische ID_MANDANT-setzung und ohne Ersetzungstable-zu-views
		
		
		EMS_LIST_BedienPanel oPanel = new EMS_LIST_BedienPanel(oNaviList,null);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		//sonst
		this.set_ID_From_Calling_Record(id_mandant_uf);
		
		oNaviList._REBUILD_COMPLETE_LIST("");
		
		if (edit) {
			this.set_ListButtonsEnabled(true,true);
		} else {
			this.set_ListButtonsEnabled(false, true);
		}
	}
		


	//beide fieldmaps werden nach einem restrict-Field durchsucht und dann mit dem wert gefuettert
	public void set_ID_From_Calling_Record(String cRestrictValueInDBFormat) throws myException
	{
		Iterator<Entry<String, SQLField>> it = this.oSQLFieldMapList.entrySet().iterator(); 
		
		while (it.hasNext()) 
		{
		    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it.next();
		    
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange)
		    {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cRestrictValueInDBFormat);
		    }
		} 			

	}



	/*
	 * methode, um die listbuttons enabled/diabled zu schalten
	 */
	public void set_ListButtonsEnabled(boolean bEnabled, boolean allowViewWhenDisabled) throws myException
	{
		this.oButtonDeleteInList.set_bEnabled_For_Edit(bEnabled);
		this.oButtonEditInList.set_bEnabled_For_Edit(bEnabled);
		this.oButtonViewInList.set_bEnabled_For_Edit(bEnabled);
		this.oButtonNewInList.set_bEnabled_For_Edit(bEnabled);
		
		if (allowViewWhenDisabled)
		{
			this.oButtonViewInList.set_bEnabled_For_Edit(true);
			this.oButtonSelVisibleCols.set_bEnabled_For_Edit(true);
		}
		
	}
	



	private class EMS_LIST_BedienPanel extends MyE2_Column {
		public EMS_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
			
			EMS_LIST_BasicModule_Inlay oThis = EMS_LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
			
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(EMS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,2));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(EMS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(1,1,3,1));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(EMS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(1,1,10,1));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new EMS_bt_NewListToMask(oNaviList), 				oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new EMS_bt_ViewListToMask(oNaviList), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new EMS_bt_EditListToMask(oNaviList), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new EMS_LIST_BT_DELETE(oNaviList), 			oRowLayout);
		}
	
	}
	
	

	
	public EMS_LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	private class EMS_LIST_BT_DELETE extends MyE2_Button
	{
	
		public EMS_LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		}
		
	
		private class ownActionAgent extends ButtonActionAgentMULTIDELETE
		{
			public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
			{
				super(new MyE2_String("Loeschen von -Eintraegen"), onavigationList);
			}
			
			public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  			{return  new Vector<String>();}
			public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 			{return  new Vector<String>();}
			public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
			public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
			public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
	
		}
	}
	
	
	
	
	
	
	
	
	
	
	private class EMS_LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public EMS_LIST_ComponentMap() throws myException
		{
			super(EMS_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(EMS_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(EMS_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("ABSENDER"),					true,150), new MyE2_String("Absender"));
			this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("BETREFF"),					true,200), new MyE2_String("Betreff"));
			this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("TEXT"),						true,300), new MyE2_String("Mailtext"));
			this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("KENNUNG_MAILVERSAND"),		true,100), new MyE2_String("Kennung"));
			this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_SCHABLONE.send_type),true,100), new MyE2_String("Versand-Typ"));
			
//			this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("ID_EMAIL_SEND_SCHABLONE"),	true,80), new MyE2_String("ID"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_EMAIL_SEND_SCHABLONE")), 	new MyE2_String("ID"));
			
			GridLayoutData oGLLeftTitel = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorDark(),1);
			GridLayoutData oGLRightTitel = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorDark(),1);
		
			GridLayoutData oGLLeftInhalt = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2));
			GridLayoutData oGLRightInhalt = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I(2,2,2,2));

			GridLayoutData oGCenterInhalt = MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2));

			
			this.get__Comp("ABSENDER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BETREFF").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_EMAIL_SEND_SCHABLONE").EXT().set_oLayout_ListElement(oGCenterInhalt);
			this.get__Comp("KENNUNG_MAILVERSAND").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("TEXT").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			this.get__Comp("ABSENDER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BETREFF").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_EMAIL_SEND_SCHABLONE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("KENNUNG_MAILVERSAND").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("TEXT").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp(EMS_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, EMS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp("ABSENDER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BETREFF").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_EMAIL_SEND_SCHABLONE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("KENNUNG_MAILVERSAND").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("TEXT").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
	
//			//weitere definitionen
//			this.get__Comp("ABSENDER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
//			this.get__Comp("BETREFF").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
//			this.get__Comp("ID_EMAIL_SEND_SCHABLONE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
//			this.get__Comp("KENNUNG_MAILVERSAND").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
//			this.get__Comp("TEXT").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
//	
//			this.get__Comp("ABSENDER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
//			this.get__Comp("BETREFF").EXT().set_oLayout_ListElement(oGLLeftInhalt);
//			this.get__Comp("ID_EMAIL_SEND_SCHABLONE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
//			this.get__Comp("KENNUNG_MAILVERSAND").EXT().set_oLayout_ListElement(oGLLeftInhalt);
//			this.get__Comp("TEXT").EXT().set_oLayout_ListElement(oGLLeftInhalt);
	
			this.set_oSubQueryAgent(new EMS_LIST_FORMATING_Agent());
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				EMS_LIST_ComponentMap oCopy = new EMS_LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	
	
	
	private class EMS_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	private class EMS_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
		public EMS_LIST_SqlFieldMAP() throws myException 	{
			super("JT_EMAIL_SEND_SCHABLONE", EMS_LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_EMAIL_SEND_SCHABLONE",EMS_LIST_BasicModule_Inlay.CONNECTION_FIELD,EMS_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			this.initFields();
		}
	}
	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(EMS_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}


	public String get_ID_MANDANT_IN_MASK() {
		return this.ID_MANDANT_in_Mask;
	}


	@Override
	public Vector<TS_Treasure_Chest> get_my_treasure_chests() throws myException {
		Vector<TS_Treasure_Chest> vRueck = new Vector<TS_Treasure_Chest>();
		vRueck.add(new TS_Treasure_Chest() {
			@Override
			public TS_DEFINITION get_TREASURE_CHEST_DEF() {
				return TS_DEFINITION.MAIL_PROFILE_INLAY_IN_MANDANTENMASK;
			}
			
			@Override
			public Object get_TREASURE_CHEST() throws myException {
				return EMS_LIST_BasicModule_Inlay.this.ID_MANDANT_in_Mask;
			}
		});
		return vRueck;
	}



	

	
	
	
}
