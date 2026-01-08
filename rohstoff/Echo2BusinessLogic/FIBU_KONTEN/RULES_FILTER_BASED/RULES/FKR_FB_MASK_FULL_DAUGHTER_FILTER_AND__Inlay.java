package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.RULES;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
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
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EXPORT_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REC_FILTER_AND_ext;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.FKR_FB_MASK_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.FKR_FB__CONST;





public class FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_ANDLABEL_IN_LIST =		"ANDLABEL";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_FILTER_LIST";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_FILTER_MASK";
	
	//fuer das anzeigegrid der or-bedingungen innerhalb der and-bedingung
	public static final String NAME_OF_OR_LIST_IN_LIST =		"NAME_OF_OR_LIST_IN_LIST";

	public static final String NAME_OF_SIMPLE_DAUGHTER_OR =		"NAME_OF_SIMPLE_DAUGHTER_OR";

	
	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private FKR_FB_LIST_SqlFieldMAP oSQLFieldMapList = new FKR_FB_LIST_SqlFieldMAP();
	private FKR_FB_MASK_SQLFieldMAP oSQLFieldMapMask = new FKR_FB_MASK_SQLFieldMAP();


	
	private E2_BasicModuleContainer    oCallingContainer = null;


    //hier den namen des ID-Feldes der eigenen Tabelle (Tochter), was die Verbindung zur Muttertabelle herstellt (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  _DB.FILTER_AND$ID_FILTER;
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "FILTER_MASK"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorBase();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;


	
	//die listenbuttons muessen exportiert werden koennen
	private FKR_FB_LIST_BT_NEW  oButtonNewInList = null; 
	private FKR_FB_LIST_BT_VIEW oButtonViewInList = null; 
	private FKR_FB_LIST_BT_EDIT oButtonEditInList = null; 
	private FKR_FB_LIST_BT_DELETE oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;



	public FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay(E2_BasicModuleContainer  CallingContainer) throws myException
	{
		super();
		
		this.oCallingContainer = CallingContainer;
			
		this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.MODULKENNER_ADDON_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new E2_NavigationList();
		
		oNaviList.INIT_WITH_ComponentMAP(new FKR_FB_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		FKR_FB_LIST_BedienPanel oPanel = new FKR_FB_LIST_BedienPanel(oNaviList, new InnerFKR_FB_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		//  oPanel.get_oFKR_FB_LIST_Selector().get_oSelVector().doActionPassiv();  //falls es einen selector gibt
		
		//sonst
		oNaviList._REBUILD_COMPLETE_LIST("");    
	}
		


	//beide fieldmaps werden nach einem restrict-Field durchsucht und dann mit dem wert gefuettert
	public void set_ID_From_Calling_Record(String cid_FILTER) throws myException
	{
		Iterator<Entry<String, SQLField>> it = this.oSQLFieldMapList.entrySet().iterator(); 
		
		while (it.hasNext()) {
		    Map.Entry<String, SQLField> entry = it.next();
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange) {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cid_FILTER);
		    }
		} 			

		Iterator<Entry<String, SQLField>> it2 = this.oSQLFieldMapMask.entrySet().iterator(); 
		
		while (it2.hasNext()) {
		    Map.Entry<String, SQLField> entry = it2.next(); 	    
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange) {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cid_FILTER);
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
	



	private class FKR_FB_LIST_BedienPanel extends MyE2_Column 
	{
		
		public FKR_FB_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
			
			FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay oThis = FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,2));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(1,1,3,1));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(1,1,10,1));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new FKR_FB_LIST_BT_NEW(oNaviList,oMaskContainer), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new FKR_FB_LIST_BT_VIEW(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new FKR_FB_LIST_BT_EDIT(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new FKR_FB_LIST_BT_DELETE(oNaviList), 					oRowLayout);
		
		}
	
	}
	
	
	public FKR_FB_LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public FKR_FB_LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public FKR_FB_LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public FKR_FB_LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	/** The delete button in the full daughter table */
	private class FKR_FB_LIST_BT_DELETE extends MyE2_Button
	{
		public FKR_FB_LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
		}
		
	
		private class ownActionAgent extends ButtonActionAgentMULTIDELETE
		{
			public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
			{
				super(new MyE2_String("Löschen von AND-Filter-Bedingungen"), onavigationList);
			}
			public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  							{return  new Vector<String>();}
			public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 								{return  new Vector<String>();}
			public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
			public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {
				
			}
			
			/** 
			 * Deletes all OR-Conditions which are underlying the AND condition to be deleted,
			 * otherwise we would get some error from Oracle because of child records.
			 */
			public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {
				for (String cId : vIDs_toDeleteUnformated) {
					RECLIST_FILTER_OR manyOrs = new RECLIST_FILTER_OR("ID_FILTER_AND = "+cId, "1");
					for (RECORD_FILTER_OR oneOr : manyOrs.values()) {
						oneOr.DELETE();
					}
				}
				FKR_FB_MASK_BasicModuleContainer occ = (FKR_FB_MASK_BasicModuleContainer)oCallingContainer; 
				occ.getModuleNaviList()._REBUILD_COMPLETE_LIST("");    
			}
		}
		
	}
	
	
	
	
	
	
	
	private class FKR_FB_LIST_BT_EDIT extends MyE2_Button
	{
	
		public FKR_FB_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			}
		}
		
	
	}
	
	
	
	
	private class FKR_FB_LIST_BT_NEW extends MyE2_Button
	{
	
		public FKR_FB_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class FKR_FB_LIST_BT_VIEW extends MyE2_Button
	{
	
		public FKR_FB_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class FKR_FB_LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public FKR_FB_LIST_ComponentMap() throws myException
		{
			super(oSQLFieldMapList);
			
			
			this.add_Component(NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

			this.add_Component(NAME_OF_ANDLABEL_IN_LIST, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11()), new MyE2_String(""));
			
			this.add_Component(NAME_OF_OR_LIST_IN_LIST, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String("Bedingung(en)"));
			this.get__Comp(NAME_OF_CHECKBOX_IN_LIST).EXT().
			set_oCompTitleInList(new ownSelectPopupAllNoneInvert());



			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

			// Diese zwei habe ich entfernt (NILSANDRE)
			/*
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FILTER")), new MyE2_String("ID_FILTER"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FILTER_AND")), new MyE2_String("ID_FILTER_AND"));
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataRight  oGLRightTitel = new ownGridLayoutDataRight(
					FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataRight  oGLRightInhalt = new ownGridLayoutDataRight(
					FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			
			this.get__Comp("ID_FILTER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_FILTER_AND").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_FILTER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_FILTER_AND").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp("ID_FILTER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_FILTER_AND").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
	
			//weitere definitionen
			this.get__Comp("ID_FILTER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_FILTER_AND").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
	
			this.get__Comp("ID_FILTER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_FILTER_AND").EXT().set_oLayout_ListElement(oGLLeftInhalt);
	
			this.get__Comp("ID_FILTER").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_FILTER_AND").EXT().set_oColExtent(new Extent(100));
*/
			


			this.set_oSubQueryAgent(new FKR_FB_LIST_FORMATING_Agent());
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				FKR_FB_LIST_ComponentMap oCopy = new FKR_FB_LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	}
	
	/** Adds the AND clauses to the list and adds a textual "UND" before every row except the first */
	private class FKR_FB_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
			MyE2_Grid grid4ListInList = (MyE2_Grid)oMAP.get__Comp(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.NAME_OF_OR_LIST_IN_LIST);
			grid4ListInList.removeAll();
			
			REC_FILTER_AND_ext recAnd = new REC_FILTER_AND_ext(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			grid4ListInList.add(recAnd.get_Info_Grid(), E2_INSETS.I(0,0,0,0));
			
			
			long rownum = oUsedResultMAP.get("ROW_NUM").getLongValue();
			if (rownum > 1) {
				MyE2_Grid qGrid = (MyE2_Grid) oMAP.get__Comp(NAME_OF_ANDLABEL_IN_LIST);
				qGrid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				MyE2_Label lab = new MyE2_Label("UND");
				qGrid.removeAll();
				qGrid.add(lab);
			}
		}
	
	}
	
	private class FKR_FB_LIST_SqlFieldMAP extends Project_SQLFieldMAP {
		public FKR_FB_LIST_SqlFieldMAP() throws myException	{
			super("JT_FILTER_AND", FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.CONNECTION_FIELD , false);
			//this.add_SQLField(new SQLField("ROWNUM AS NILS", true);
			//this.add_BEDINGUNG_STATIC("ROWNUM as NILS");
			
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_FILTER_AND",FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.CONNECTION_FIELD,FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			SQLField  sqlROWNUM = new SQLField("ROWNUM", "ROW_NUM", new MyE2_String("row") , bibE2.get_CurrSession());
			this.add_SQLField(sqlROWNUM, false);
			
			this.initFields();
		}
	}
	
	private class InnerFKR_FB_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK {
		public InnerFKR_FB_MASK_BasicModuleContainer() throws myException {
			super(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()+FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.MODULKENNER_ADDON_MASK);
			this.set_bVisible_Row_For_Messages(true);
			FKR_FB_MASK_ComponentMAP oFKR_FB_MASK_ComponentMAP = new FKR_FB_MASK_ComponentMAP();
			this.INIT(oFKR_FB_MASK_ComponentMAP, new FKR_FB_MASK(oFKR_FB_MASK_ComponentMAP), new Extent(900), new Extent(650));
		}
	}
	
	private class FKR_FB_MASK_ComponentMAP extends E2_ComponentMAP {
		public FKR_FB_MASK_ComponentMAP() throws myException {
			super(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FILTER_AND")), new MyE2_String("ID_FILTER_AND"));
			
			//hier dann der unterhalb der AND-Filter angeordneten OR-Filter
			FKR_FB_MASK_SimpleDaughter_OR oSD_or = new FKR_FB_MASK_SimpleDaughter_OR(oFM, this);
			this.add_Component(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.NAME_OF_SIMPLE_DAUGHTER_OR, oSD_or, new MyE2_String("Tochtertabelle mit 1 oder n - OR-Regeln"));
			
			
			/*
	 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
			 */
			this.set_oMAPSettingAgent(new FKR_FB_MASK_MapSettingAgent());
			
			/*
			 * ermoeglicht formatierungen von zusatzinfos in der maske
			 */
			this.set_oSubQueryAgent(new FKR_FB_MASK_FORMATING_Agent());
		}
		
	}
	
	
	
	private class FKR_FB_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT {
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	private class FKR_FB_MASK_MapSettingAgent extends XX_MAP_SettingAgent {
	
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
		}
	
		public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
		{
		}
		
		private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
		{
			for (int i=0;i<cFieldList.length;i++)
			{
				oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
			}
		}
	
	}
	
	
	
	
	
	private class FKR_FB_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public FKR_FB_MASK_SQLFieldMAP() throws myException 
		{
			super("JT_FILTER_AND", FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.CONNECTION_FIELD, false);
		
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_FILTER_AND",FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.CONNECTION_FIELD,FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
	
	
			this.initFields();
		}
	
	}
	
	
	
	
	
	private class FKR_FB_MASK extends MyE2_Column implements IF_BaseComponent4Mask
	{
		
		private Vector<IF_ADDING_Allowed> vContainers = new Vector<IF_ADDING_Allowed>();
		
		public FKR_FB_MASK(FKR_FB_MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
	
			MyE2_Grid oGrid0 = new MyE2_Grid(4,0);
			this.add(oGrid0, E2_INSETS.I(2,2,2,2));
			
			this.vContainers.add(oGrid0);
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("ID:"), 1, "ID_FILTER_AND|#|",1, new MyE2_String("(die unten angegebenen Blöcke werden mit ODER verknüpft)"), 3);
			
			oFiller.add_Line(oGrid0, new MyE2_String("Blöcke:"), 1, FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.NAME_OF_SIMPLE_DAUGHTER_OR+"|#  |",3);
		}



		@Override
		public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
		{
			return this.vContainers;
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
			super(FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	
}
