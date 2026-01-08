package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;








public class FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_FIBU_EXPORT_PROFILES_LIST";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_FIBU_EXPORT_PROFILES_MASK";


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP oSQLFieldMapList = new FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP();
	private FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP oSQLFieldMapMask = new FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP();


	
	private E2_BasicModuleContainer    oCallingContainer = null;


    //hier den namen des ID-Feldes der eigenen Tabelle (Tochter), was die Verbindung zur Muttertabelle herstellt (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  "KENNER";
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "KENNER2"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorBase();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;


	
	//die listenbuttons muessen exportiert werden koennen
	private FIBU_EXPORT_PROFILES_LIST_BT_NEW  oButtonNewInList = null; 
	private FIBU_EXPORT_PROFILES_LIST_BT_VIEW oButtonViewInList = null; 
	private FIBU_EXPORT_PROFILES_LIST_BT_EDIT oButtonEditInList = null; 
	private FIBU_EXPORT_PROFILES_LIST_BT_DELETE oButtonDeleteInList  = null; 



	public FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay(E2_BasicModuleContainer  CallingContainer) throws myException
	{
		super();
		
		this.oCallingContainer = CallingContainer;
		
		this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new E2_NavigationList();
		
		oNaviList.INIT_WITH_ComponentMAP(new FIBU_EXPORT_PROFILES_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		FIBU_EXPORT_PROFILES_LIST_BedienPanel oPanel = new FIBU_EXPORT_PROFILES_LIST_BedienPanel(oNaviList,new FIBU_EXPORT_PROFILES_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		//  oPanel.get_oFIBU_EXPORT_PROFILES_LIST_Selector().get_oSelVector().doActionPassiv();  //falls es einen selector gibt
		
		//sonst
		oNaviList._REBUILD_COMPLETE_LIST("");    
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

		Iterator<Entry<String, SQLField>> it2 = this.oSQLFieldMapMask.entrySet().iterator(); 
		
		while (it2.hasNext()) 
		{
		    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it2.next();
		    
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
		}
		
	}
	



	private class FIBU_EXPORT_PROFILES_LIST_BedienPanel extends MyE2_Column 
	{
		private FIBU_EXPORT_PROFILES_LIST_Selector  oFIBU_EXPORT_PROFILES_LIST_Selector = null;
		
		public FIBU_EXPORT_PROFILES_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
			
			FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay oThis = FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
			
			this.oFIBU_EXPORT_PROFILES_LIST_Selector = new FIBU_EXPORT_PROFILES_LIST_Selector(oNaviList);
		
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,2));
			
			this.add(oFIBU_EXPORT_PROFILES_LIST_Selector, oColLayout);
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(1,1,3,1));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(1,1,10,1));
			
			oRowForComponents.add(oThis.oButtonNewInList = 		new FIBU_EXPORT_PROFILES_LIST_BT_NEW(oNaviList,oMaskContainer), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new FIBU_EXPORT_PROFILES_LIST_BT_VIEW(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new FIBU_EXPORT_PROFILES_LIST_BT_EDIT(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new FIBU_EXPORT_PROFILES_LIST_BT_DELETE(oNaviList), 					oRowLayout);
		
			// oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,FIBU_EXPORT_PROFILES_LIST), new Insets(20,2,2,2));   //fuer inlays i.d.r. nicht noetig
			
			// oRowForComponents.add(new FIBU_EXPORT_PROFILES_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));  //fuer inlays i.d.r. nicht noetig
		}
	
		public FIBU_EXPORT_PROFILES_LIST_Selector get_oFIBU_EXPORT_PROFILES_LIST_Selector() 
		{
			return oFIBU_EXPORT_PROFILES_LIST_Selector;
		}
	}
	
	
	public FIBU_EXPORT_PROFILES_LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public FIBU_EXPORT_PROFILES_LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public FIBU_EXPORT_PROFILES_LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public FIBU_EXPORT_PROFILES_LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	
	private class FIBU_EXPORT_PROFILES_LIST_BT_DELETE extends MyE2_Button
	{
	
		public FIBU_EXPORT_PROFILES_LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
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
	
	
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_BT_EDIT extends MyE2_Button
	{
	
		public FIBU_EXPORT_PROFILES_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			}
		}
		
	
	}
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_BT_NEW extends MyE2_Button
	{
	
		public FIBU_EXPORT_PROFILES_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_BT_VIEW extends MyE2_Button
	{
	
		public FIBU_EXPORT_PROFILES_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public FIBU_EXPORT_PROFILES_LIST_ComponentMap() throws myException
		{
			super(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label(oFM.get_("DATEV_BERATERNUMMER")), new MyE2_String("DATEV_BERATERNUMMER"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("DATEV_GESCHAEFTSJAHRESBEGINN")), new MyE2_String("DATEV_GESCHAEFTSJAHRESBEGINN"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("DATEV_MANDANTNUMMER")), new MyE2_String("DATEV_MANDANTNUMMER"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_DATEV_PROFILE")), new MyE2_String("ID_DATEV_PROFILE"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_DRUCKER")), new MyE2_String("ID_DRUCKER"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER")), new MyE2_String("ID_USER"));
			
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataRight  oGLRightTitel = new ownGridLayoutDataRight(
					FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataRight  oGLRightInhalt = new ownGridLayoutDataRight(
					FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			
			this.get__Comp("DATEV_BERATERNUMMER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATEV_GESCHAEFTSJAHRESBEGINN").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATEV_MANDANTNUMMER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_DATEV_PROFILE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_DRUCKER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_USER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATEV_BERATERNUMMER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DATEV_GESCHAEFTSJAHRESBEGINN").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DATEV_MANDANTNUMMER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_DATEV_PROFILE").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_DRUCKER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_USER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp("DATEV_BERATERNUMMER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("DATEV_GESCHAEFTSJAHRESBEGINN").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("DATEV_MANDANTNUMMER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_DATEV_PROFILE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_DRUCKER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_USER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
	
			//weitere definitionen
			this.get__Comp("DATEV_BERATERNUMMER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DATEV_GESCHAEFTSJAHRESBEGINN").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DATEV_MANDANTNUMMER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_DATEV_PROFILE").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_DRUCKER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_USER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
	
			this.get__Comp("DATEV_BERATERNUMMER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATEV_GESCHAEFTSJAHRESBEGINN").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATEV_MANDANTNUMMER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_DATEV_PROFILE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_DRUCKER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_USER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
	
			this.get__Comp("DATEV_BERATERNUMMER").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("DATEV_GESCHAEFTSJAHRESBEGINN").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("DATEV_MANDANTNUMMER").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_DATEV_PROFILE").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_DRUCKER").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_USER").EXT().set_oColExtent(new Extent(100));


			this.set_oSubQueryAgent(new FIBU_EXPORT_PROFILES_LIST_FORMATING_Agent());
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				FIBU_EXPORT_PROFILES_LIST_ComponentMap oCopy = new FIBU_EXPORT_PROFILES_LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	//datasearch in inlay-objekten i.d.R. nicht noetig
	private class FIBU_EXPORT_PROFILES_LIST_DATASEARCH extends E2_DataSearch
	{
	
		public FIBU_EXPORT_PROFILES_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
		{
			super("JT_DATEV_PROFILE","ID_DATEV_PROFILE",FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.get_MODUL_IDENTIFIER());
			
			E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
			this.set_oSearchAgent(oSearchAgent);
			
			this.addSearchDef("ID_DATEV_PROFILE","ID",true);
	
			//hier kommen die Felder	
			this.addSearchDef("DATEV_BERATERNUMMER","DATEV_BERATERNUMMER",false);
			this.addSearchDef("DATEV_GESCHAEFTSJAHRESBEGINN","DATEV_GESCHAEFTSJAHRESBEGINN",false);
			this.addSearchDef("DATEV_MANDANTNUMMER","DATEV_MANDANTNUMMER",false);
			this.addSearchDef("ID_DATEV_PROFILE","ID_DATEV_PROFILE",false);
			this.addSearchDef("ID_DRUCKER","ID_DRUCKER",false);
			this.addSearchDef("ID_USER","ID_USER",false);
	
			/*
			this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
			this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
			this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
			this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
			*/		
	
		}
	
		private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
		{
			String cSearch = "";
			if (bNumber)
				cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE TO_CHAR(JT_DATEV_PROFILE."+cFieldName+")='#WERT#'";
			else
				cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE UPPER(JT_DATEV_PROFILE."+cFieldName+") like upper('%#WERT#%')";
			
			this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
		}
		
	
		private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
		{
			String cSearch = "";
			if (bNumber)
				cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_DATEV_PROFILE.ID_DATEV_PROFILE="+cRefTableName+".ID_DATEV_PROFILE AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
			else
				cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_DATEV_PROFILE.ID_DATEV_PROFILE="+cRefTableName+".ID_DATEV_PROFILE AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
			
			this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
		}
	
	
			
	}
	
	
	
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_Selector extends E2_ExpandableRow
	{
		
		private E2_SelectionComponentsVector 	oSelVector = null;
		
		public FIBU_EXPORT_PROFILES_LIST_Selector(E2_NavigationList oNavigationList) throws myException
		{
			super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
			
			this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
	
			/*
			 * Beispiele
			 *
			String cID_USER = bibALL.get_ID_USER(bibE2.get_CurrSession());
			MyE2_CheckBox oCBNurEigene = new MyE2_CheckBox("Nur mit eigener Beteiligung");
			oSelVector.add_(new E2_ListSelectorStandard(oCBNurEigene,"(JT_DATEV_PROFILE.ID_USER="+cID_USER+" OR JT_DATEV_PROFILE.ID_FIBU_EXPORT_PROFILES IN (SELECT ID_FIBU_EXPORT_PROFILES FROM "+bibE2.cTO()+".JT_DATEV_PROFILE_TEILNEHMER WHERE ID_USER="+cID_USER+"))",""));
	
			E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_DATEV_PROFILE_WICHTIGKEIT","BESCHREIBUNG","ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT","ISTSTANDARD",true);
	
			MyE2_SelectField	oSelectWichtigkeit = new MyE2_SelectField(oDDWichtigkeit.getDD(),null,false);
			oSelVector.add_(new E2_ListSelectorStandard(oSelectWichtigkeit,"JT_DATEV_PROFILE.ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT=#WERT#"));
	
			
			MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
			this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
			
			oGridInnen.add(new MyE2_Label("Wichtigkeit:"), new Insets(4,2,20,2));
			oGridInnen.add(oSelectWichtigkeit, new Insets(4,2,15,2));
			oGridInnen.add(oCBNurEigene, new Insets(4,2,15,2));
			*/
		}
	
		public E2_SelectionComponentsVector get_oSelVector()
		{
			return oSelVector;
		}
	
	}
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP() throws myException 
		{
			super("JT_DATEV_PROFILE", FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
	
			/*
			 * beispiel fuer die definition von standard-werten
			 */
			this.get_("DATEV_BERATERNUMMER").set_cDefaultValueFormated("");
			this.get_("DATEV_GESCHAEFTSJAHRESBEGINN").set_cDefaultValueFormated("");
			this.get_("DATEV_MANDANTNUMMER").set_cDefaultValueFormated("");
			this.get_("ID_DATEV_PROFILE").set_cDefaultValueFormated("");
			this.get_("ID_DRUCKER").set_cDefaultValueFormated("");
			this.get_("ID_USER").set_cDefaultValueFormated("");
			
			/*	
			E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_DATEV_PROFILE_WICHTIGKEIT","BESCHREIBUNG","ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT","ISTSTANDARD",true);
			this.get_("ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
			*/
	
			/*
			 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
	 		 *
			cSonderQuery =  "(JT_DATEV_PROFILE.ID_USER="+cID_USER+" OR JT_DATEV_PROFILE.ID_FIBU_EXPORT_PROFILES IN (SELECT ID_FIBU_EXPORT_PROFILES FROM "+bibE2.cTO()+".JT_DATEV_PROFILE_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
			
			this.add_BEDINGUNG_STATIC(cSonderQuery);
			*/
			
			
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_DATEV_PROFILE",FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.CONNECTION_FIELD,FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			
			this.initFields();
		}
		
	}
	
	
		
	
	private class FIBU_EXPORT_PROFILES_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
	{
	
		public FIBU_EXPORT_PROFILES_MASK_BasicModuleContainer() throws myException
		{
			super(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()+FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK);
						
			this.set_bVisible_Row_For_Messages(true);
			
			FIBU_EXPORT_PROFILES_MASK_ComponentMAP oFIBU_EXPORT_PROFILES_MASK_ComponentMAP = new FIBU_EXPORT_PROFILES_MASK_ComponentMAP();
			
			this.INIT(oFIBU_EXPORT_PROFILES_MASK_ComponentMAP, (IF_BaseComponent4Mask) new FIBU_EXPORT_PROFILES_MASK(oFIBU_EXPORT_PROFILES_MASK_ComponentMAP), new Extent(900), new Extent(650));
		}
		
		
	}
	
	
	
	
	private class FIBU_EXPORT_PROFILES_MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		public FIBU_EXPORT_PROFILES_MASK_ComponentMAP() throws myException
		{
			super(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			
			/*
			 * beispiele fuer felder in der map
			 *
			*/
			String[] cFieldsStandard = {"DATEV_BERATERNUMMER","DATEV_GESCHAEFTSJAHRESBEGINN","DATEV_MANDANTNUMMER","ERZEUGT_AM","ERZEUGT_VON","GEAENDERT_VON","ID_DATEV_PROFILE","ID_DRUCKER","ID_MANDANT","ID_USER","LETZTE_AENDERUNG"}; 
			String[] cFieldsInfolabs = {"DATEV_BERATERNUMMER","DATEV_GESCHAEFTSJAHRESBEGINN","DATEV_MANDANTNUMMER","ERZEUGT_AM","ERZEUGT_VON","GEAENDERT_VON","ID_DATEV_PROFILE","ID_DRUCKER","ID_MANDANT","ID_USER","LETZTE_AENDERUNG"}; 
	
			//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_DATEV_PROFILE_WICHTIGKEIT","BESCHREIBUNG","ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT","ISTSTANDARD",true);
			
			MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_TextField(oFM.get_("DATEV_BERATERNUMMER"),true,100,10,false), new MyE2_String("DATEV_BERATERNUMMER"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("DATEV_GESCHAEFTSJAHRESBEGINN"),true,120,10,false), new MyE2_String("DATEV_GESCHAEFTSJAHRESBEGINN"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("DATEV_MANDANTNUMMER"),true,100,10,false), new MyE2_String("DATEV_MANDANTNUMMER"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_DATEV_PROFILE"),true,200,10,false), new MyE2_String("ID_DATEV_PROFILE"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_DRUCKER"),true,200,10,false), new MyE2_String("ID_DRUCKER"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_USER"),true,200,10,false), new MyE2_String("ID_USER"));
			
	
			/*
			 * beispiele fuer beliebige felder
			this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
			this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_EXPORT_PROFILES")), new MyE2_String("ID"));
	
			((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iWidthPixel(600);
			((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iWidthPixel(600);
			((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iRows(4);
			((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iRows(4);
			
			((MyE2_DB_TextField)this.get__Comp("AUFGABEKURZ")).set_iWidthPixel(600);
			((MyE2_DB_TextField)this.get__Comp("ANTWORTKURZ")).set_iWidthPixel(600);
	
			((MyE2_DB_CheckBox)this.get__Comp("ERLEDIGT")).add_oActionAgent(new cbActionAgent());
			*/	
	
			/*
	 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
			 */
			this.set_oMAPSettingAgent(new FIBU_EXPORT_PROFILES_MASK_MapSettingAgent());
			
			/*
			 * ermoeglicht formatierungen von zusatzinfos in der maske
			 */
			this.set_oSubQueryAgent(new FIBU_EXPORT_PROFILES_MASK_FORMATING_Agent());
		}
		
	}
	
	
	
	private class FIBU_EXPORT_PROFILES_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_MASK_MapSettingAgent extends XX_MAP_SettingAgent {
	
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
		
	
		}
	
		public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
		{
			/*
			 * beispiele fuer inhaltabhaengige erlaubnis, etwas zu bearbeiten
			 *
			String[] cAllFieldsEditable = {"GENERIERUNGSDATUM","ABLAUFDATUM","ABSCHLUSSDATUM","AUFGABEKURZ",
										"AUFGABENTEXT","ANTWORTKURZ","ANTWORTTEXT","ERLEDIGT","ID_USER",
										FIBU_EXPORT_PROFILES_MASK_BasicModuleContainer.HASHVALUE_FIBU_EXPORT_PROFILES_TEILNEHMER,}; 
			
			String[] cFieldsEditable0 = {"ABSCHLUSSDATUM","ANTWORTKURZ","ANTWORTTEXT"}; 
			myUser  oUser = new myUser(bibALL.get_ID_USER(bibE2.get_CurrSession()),bibE2.get_CurrSession());
	
			
			String cIDUserInMaske =((MyE2_DB_SelectField)oMap.get__Comp("ID_USER")).get_ActualWert();
			DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(cIDUserInMaske);
			if (!oDF.doFormat())
			{
				throw new myException(this,":Error Finding ID_USER from Mask-Field !!");
			}
			cIDUserInMaske = oDF.getStringUnFormated();
			
			// alles auf start
			this.do_Disable(oMap, cAllFieldsEditable, false);
			
			if (oUser.get_StatusFIBU_EXPORT_PROFILES_SUPERVISOR().equals("0") || oUser.get_StatusFIBU_EXPORT_PROFILES_SUPERVISOR().equals("1"))
			{
				// fuer 0 - kandidaten im editmode nur eigene todos erlaubt
				if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
				{
					if (! cIDUserInMaske.equals(bibALL.get_ID_USER(bibE2.get_CurrSession())))
					{
						this.do_Disable(oMap, cAllFieldsEditable, true);
						this.do_Disable(oMap, cFieldsEditable0, false);
					}
				}
	
				// fuer user typ 0 ist der besitzer immer gesperrt
				if (oUser.get_StatusFIBU_EXPORT_PROFILES_SUPERVISOR().equals("0"))
				{
					oMap.get__Comp("ID_USER").EXT().set_bDisabledFromInteractive(true);
				}
			}
			*/
	
			
		}
		
		private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
		{
			for (int i=0;i<cFieldList.length;i++)
			{
				oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
			}
		}
	
	}
	
	
	
	
	
	private class FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP() throws myException 
		{
			super("JT_DATEV_PROFILE", FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.CONNECTION_FIELD, false);
		
			/*
			 * beispiel fuer die definition von standard-werten
			 */
			this.get_("DATEV_BERATERNUMMER").set_cDefaultValueFormated("");
			this.get_("DATEV_GESCHAEFTSJAHRESBEGINN").set_cDefaultValueFormated("");
			this.get_("DATEV_MANDANTNUMMER").set_cDefaultValueFormated("");
			this.get_("ID_DATEV_PROFILE").set_cDefaultValueFormated("");
			this.get_("ID_DRUCKER").set_cDefaultValueFormated("");
			this.get_("ID_USER").set_cDefaultValueFormated("");
			this.get_("DATEV_BERATERNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("DATEV_GESCHAEFTSJAHRESBEGINN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("DATEV_MANDANTNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("ID_DATEV_PROFILE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("ID_DRUCKER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			
			/*
			 * beispiel fuer felder
			 *		
			this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
			this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
			this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
			*/
	
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_DATEV_PROFILE",FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.CONNECTION_FIELD,FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
	
	
			this.initFields();
		}
	
	}
	
	
	
	
	
	/*
	* maskenvariante mit TabbedPane 
	* TODO .... MUSS Umbenannt werden in FIBU_EXPORT_PROFILES_MASK
	*/
	private class FIBU_EXPORT_PROFILES_MASK extends MyE2_Column 
	{
	
		
		public FIBU_EXPORT_PROFILES_MASK(FIBU_EXPORT_PROFILES_MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
	
			MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
			
			oTabbedPaneMaske.set_bAutoHeight(true);         //damit wird beim vergroessern die tabbedpane automatisch mitgezogen
			
			
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid1 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid2 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid3 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid4 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid5 = new MyE2_Grid(2,0);
			
			oTabbedPaneMaske.add_Tabb(new MyE2_String("text1"), oGrid0);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("text2"), oGrid1);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("text3"), oGrid2);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("text4"), oGrid3);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("text5"), oGrid4);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("text6"), oGrid5);
			
			this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("DATEV_BERATERNUMMER:"), 1, "DATEV_BERATERNUMMER|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("DATEV_GESCHAEFTSJAHRESBEGINN:"), 1, "DATEV_GESCHAEFTSJAHRESBEGINN|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("DATEV_MANDANTNUMMER:"), 1, "DATEV_MANDANTNUMMER|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("ID_DATEV_PROFILE:"), 1, "ID_DATEV_PROFILE|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("ID_DRUCKER:"), 1, "ID_DRUCKER|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("ID_USER:"), 1, "ID_USER|#  |",3);
	
			
			
			/*
			 * beispiel fuer action-umschalter bei komplexten maskenelementen die actionagents zur aktivierung der komplexen toechter definieren
			 *
			oTabbedPaneMaske.add_ActionAgent_to_Tab(0,new tabbActionAgent(null,oMap));   // alles inaktiv
			oTabbedPaneMaske.add_ActionAgent_to_Tab(1,new tabbActionAgent(null,oMap));   // alles inaktiv
			oTabbedPaneMaske.add_ActionAgent_to_Tab(2,new tabbActionAgent(null,oMap));   // alles inaktiv
			oTabbedPaneMaske.add_ActionAgent_to_Tab(3,new tabbActionAgent(null,oMap));   // alles inaktiv
			oTabbedPaneMaske.add_ActionAgent_to_Tab(4,new tabbActionAgent((XX_FULL_DAUGHTER)oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS),oMap));   // alles inaktiv
			oTabbedPaneMaske.add_ActionAgent_to_Tab(5,new tabbActionAgent((XX_FULL_DAUGHTER)oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER),oMap));   // alles inaktiv
			*/
	
		}
	
		
		
		
		/**
		 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
		 */
		private class tabbActionAgent extends XX_ActionAgent
		{
			private E2_ComponentMAP 			oE2_MAP = null;
			private XX_FULL_DAUGHTER			oDaughterToActivate = null;
			
			public tabbActionAgent(XX_FULL_DAUGHTER activate, E2_ComponentMAP oe2_map)
			{
				super();
				this.oDaughterToActivate = activate;
				this.oE2_MAP = oe2_map;
			}
	
	
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				try
				{
					/* hier alle full-daughter-klassen deaktivieren
					((XX_FULL_DAUGHTER) this.oE2_MAP.get(FIBU_EXPORT_PROFILES_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
					((XX_FULL_DAUGHTER) this.oE2_MAP.get(FIBU_EXPORT_PROFILES_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
					*/
	
					if (this.oDaughterToActivate!=null) this.oDaughterToActivate.set_bIsActive(true);
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("tabbActionAgent:doAction:Error setting Complex-Objects!"));
				}
			}
		}
		
	
		
	}
	
	
		
	private class ownGridLayoutDataLeft extends GridLayoutData
	{
		public ownGridLayoutDataLeft(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
			this.setBackground(oColBack);
		}
	}

	private class ownGridLayoutDataRight extends GridLayoutData
	{
		public ownGridLayoutDataRight(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
			this.setBackground(oColBack);
		}
	}

	
	private class ownGridLayoutDataCenter extends GridLayoutData
	{
		public ownGridLayoutDataCenter(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
			this.setBackground(oColBack);
		}
	}

	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(FIBU_EXPORT_PROFILES_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	

	
	
	
}
