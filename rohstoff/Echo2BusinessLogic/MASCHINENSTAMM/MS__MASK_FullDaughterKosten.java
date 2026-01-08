package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
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
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MS__MASK_FullDaughterKosten extends XX_FULL_DAUGHTER  {

	private MS__BasicModuleContainerKosten  kostenInlay = null;
	
	public MS__MASK_FullDaughterKosten(SQLFieldForPrimaryKey osqlField, E2_BasicModuleContainer  oMaskContainerMaschinen) throws myException	{
		super(osqlField);
		this.kostenInlay = new MS__BasicModuleContainerKosten(oMaskContainerMaschinen);
	}

	@Override
	public Component build_content_for_Mask(String cValueFormated,String cValueUnformated, String cMASK_STATUS) throws myException 	{
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))	{
			return new MyE2_Label(new MyE2_String("Kosten können erst erfasst werden, wenn der Vorgangskopf gespeichert wurde!"));
		} else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT)) {
			
			this.kostenInlay.set_ID_From_Calling_Record(cValueUnformated);
			this.kostenInlay.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");    
			return this.kostenInlay;
		} else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)) {

			this.kostenInlay.set_ID_From_Calling_Record(cValueUnformated);
			this.kostenInlay.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");    
			return this.kostenInlay;
		}  else { 
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		}
		
	}


	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException {
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))	{
			this.kostenInlay.set_ListButtonsEnabled(true, true);
		}
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)){
			this.kostenInlay.set_ListButtonsEnabled(false, true);
		}
	}

	public void prepare_DaughterContentForNew() throws myException 	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Maschinen-Aufgaben können erst erfasst werden, wenn die Maschine gespeichert wurde!")));
	}

	public Component build_non_active_placeholder() throws myException	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}


	
	/**
	 * inlay for full-daughter
	 * @author martin
	 *
	 */
	public class MS__BasicModuleContainerKosten extends E2_BasicModuleContainer  {
		
		public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
		public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
		
		public static final String MODULKENNER_ADDON_LIST =			"_ADDON_KOSTEN";
		public static final String MODULKENNER_ADDON_MASK =			"_ADDON_KOSTEN";


		//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
		private MS___KostenLIST_SqlFieldMAP oSQLFieldMapList = new MS___KostenLIST_SqlFieldMAP();
		private SqlFieldMapKostenMask oSQLFieldMapMask = new SqlFieldMapKostenMask();
	 
		
		private E2_BasicModuleContainer    oCallingContainer = null;


	    //hier den namen des ID-Feldes aus der rufenden Einheit (1-zu-N - Beziehung)
		public  String CONNECTION_FIELD   =  MASCHINEN_KOSTEN.id_maschinen.fn();
		
		public  String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "_KOSTEN"; 


		//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
		private Color  				oColorBackgroundListHeader = new E2_ColorBase();

		//navigationlist muss klassenobjekt sein
		private E2_NavigationList 	oNaviList = null;

		
		//die listenbuttons muessen exportiert werden koennen
		private MS___LIST_BT_NEW  oButtonNewInList = null; 
		private MS___LIST_BT_VIEW oButtonViewInList = null; 
		private MS___LIST_BT_EDIT oButtonEditInList = null; 
		private MS___LIST_BT_DELETE oButtonDeleteInList  = null;
		
		private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;



		public MS__BasicModuleContainerKosten(E2_BasicModuleContainer  CallingContainer) throws myException {
			super();
			
			this.oCallingContainer = CallingContainer;
			this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+MS__BasicModuleContainerKosten.MODULKENNER_ADDON_LIST);
			
			this.set_bVisible_Row_For_Messages(false);
			
			this.oNaviList = new E2_NavigationList();
			
			oNaviList.INIT_WITH_ComponentMAP(new KostenListComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
			MS___LIST_BedienPanel oPanel = new MS___LIST_BedienPanel(oNaviList,new MS___MASK_BasicModuleContainer());
			this.add(oPanel);
			this.add(oNaviList, E2_INSETS.I_2_2_2_2);
			
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
		public void set_ListButtonsEnabled(boolean bEnabled, boolean allowViewWhenDisabled) throws myException 		{
			this.oButtonDeleteInList.set_bEnabled_For_Edit(bEnabled);
			this.oButtonEditInList.set_bEnabled_For_Edit(bEnabled);
			this.oButtonViewInList.set_bEnabled_For_Edit(bEnabled);
			this.oButtonNewInList.set_bEnabled_For_Edit(bEnabled);
			
			if (allowViewWhenDisabled)			{
				this.oButtonViewInList.set_bEnabled_For_Edit(true);
				this.oButtonSelVisibleCols.set_bEnabled_For_Edit(true);
			}
		}
		

			
		
			


		private class MS___LIST_BedienPanel extends MyE2_Column 
		{
			
			public MS___LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
			{
				super(MyE2_Column.STYLE_NO_BORDER());
				
				MS__BasicModuleContainerKosten oThis = MS__BasicModuleContainerKosten.this;
				
				MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
				
			
				ColumnLayoutData  oColLayout = new ColumnLayoutData();
				oColLayout.setBackground(MS__BasicModuleContainerKosten.this.oColorBackgroundListHeader);
				oColLayout.setInsets(new Insets(0,0,0,2));
				
				this.add(oRowForComponents, oColLayout);

				RowLayoutData  oRowLayout = new RowLayoutData();
				oRowLayout.setBackground(MS__BasicModuleContainerKosten.this.oColorBackgroundListHeader);
				oRowLayout.setInsets(new Insets(1,1,3,1));
				
				RowLayoutData  oRowLayoutWide = new RowLayoutData();
				oRowLayoutWide.setBackground(MS__BasicModuleContainerKosten.this.oColorBackgroundListHeader);
				oRowLayoutWide.setInsets(new Insets(1,1,10,1));
				
				oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
				oRowForComponents.add(oThis.oButtonNewInList = 		new MS___LIST_BT_NEW(oNaviList,oMaskContainer), 			oRowLayout);
				oRowForComponents.add(oThis.oButtonViewInList =		new MS___LIST_BT_VIEW(oNaviList,oMaskContainer), 			oRowLayout);
				oRowForComponents.add(oThis.oButtonEditInList =		new MS___LIST_BT_EDIT(oNaviList,oMaskContainer), 			oRowLayout);
				oRowForComponents.add(oThis.oButtonDeleteInList =	new MS___LIST_BT_DELETE(oNaviList), 						oRowLayoutWide);
				oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(		oNaviList,
																						E2_MODULNAME_ENUM.MODUL.MODUL_MASCHINENSTAMM_KOSTEN_LISTE.get_callKey(),
																						MS__BasicModuleContainerKosten.this.oCallingContainer.get_MODUL_IDENTIFIER()), oRowLayoutWide);
			
			}
		
		}
		
		
		private class MS___LIST_BT_DELETE extends MyE2_Button {
		
			public MS___LIST_BT_DELETE(E2_NavigationList onavigationList)	{
				super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
				this.add_oActionAgent(new ownActionAgent(onavigationList,this));

				this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS__BasicModuleContainerKosten.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																	"LOESCHE_"+KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			}
			
		
			private class ownActionAgent extends ButtonActionAgentMULTIDELETE 	{
				public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)	{
					super(new MyE2_String("Loeschen von -Eintraegen"), onavigationList);
				}
				
				public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  			{return  new Vector<String>();}
				public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 			{return  new Vector<String>();}
				public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
				public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
				public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
			}
		}
		
		
		
		private class MS___LIST_BT_EDIT extends MyE2_Button {
			
			public MS___LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer) 	{
				super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
				this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

				//die freigabe der buttons erfolgt im ubergeordneten modul 
				this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS__BasicModuleContainerKosten.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																	"BEARBEITE_"+KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			}
			
			private class ownActionAgent extends ButtonActionAgentEDIT	{
				public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) 	{
					super(new MyE2_String("Bearbeiten eines Kosten-Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
				}
			}
		}
		
		
		
		
		private class MS___LIST_BT_NEW extends MyE2_Button {
		
			public MS___LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)	{
				super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
				this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
				
				this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS__BasicModuleContainerKosten.this.oCallingContainer.get_MODUL_IDENTIFIER(),
										"NEUEINGABE_"+KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			}
			
			private class ownActionAgent extends ButtonActionAgentNEW	{
				public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)	{
					super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
				}
			}
		}
		
		
		
		
		private class MS___LIST_BT_VIEW extends MyE2_Button {
		
			public MS___LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)	{
				super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
				this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
				this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS__BasicModuleContainerKosten.this.oCallingContainer.get_MODUL_IDENTIFIER(),
										"ANZEIGE_"+KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			}
			
			private class ownActionAgent extends ButtonActionAgentVIEW	{
				public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)	{
					super(new MyE2_String("Anzeige von Kosten-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
				}
			}
			
		}
		
		
		
		
		private class KostenListComponentMap extends E2_ComponentMAP {
		
			public KostenListComponentMap() throws myException	{
				super(MS__BasicModuleContainerKosten.this.oSQLFieldMapList);
				
				SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
				
				this.add_Component(MS__BasicModuleContainerKosten.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
				this.add_Component(MS__BasicModuleContainerKosten.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
		
				this.add_Component(new ownSelectFieldKostenTyp(oFM.get_(MASCHINEN_KOSTEN.ms_enum_kostentyp.fn())), 	new MyE2_String("Kostentyp"));
				this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(MASCHINEN_KOSTEN.kosten_beschreibung.fn())), 	new MyE2_String("Kostenbeschreibung"));
				this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(MASCHINEN_KOSTEN.kosten_betrag.fn())), 			new MyE2_String("Betrag"));
				this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(MASCHINEN_KOSTEN.id_maschinen_kosten.fn())),	new MyE2_String("id"));

				this._setColExtent(new Extent(100), MASCHINEN_KOSTEN.ms_enum_kostentyp.fn(),MASCHINEN_KOSTEN.kosten_betrag.fn(),MASCHINEN_KOSTEN.id_maschinen_kosten.fn());
				this._setColExtent(new Extent(200), MASCHINEN_KOSTEN.kosten_beschreibung.fn());

				this.get__Comp(MS__BasicModuleContainerKosten.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
		
				MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
				oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, MS__BasicModuleContainerKosten.this.oColorBackgroundListHeader);
				oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

	            //den buttons/komponenten des listenheaders einen eigenen style verpassen
				this.get__Comp(MASCHINEN_KOSTEN.ms_enum_kostentyp.fn()).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
				this.get__Comp(MASCHINEN_KOSTEN.kosten_beschreibung.fn()).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
				this.get__Comp(MASCHINEN_KOSTEN.kosten_betrag.fn()).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
				this.get__Comp(MASCHINEN_KOSTEN.id_maschinen_kosten.fn()).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
				
				this.set_oSubQueryAgent(new KostenListFormatingAgent());
			}
		
		
			public Object get_Copy(Object objHelp) throws myExceptionCopy {
				try	{
					KostenListComponentMap oCopy = new KostenListComponentMap();
					return oCopy;
				} catch (myException ex) {
					throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
				}
			}
		
		}
		
		
		
		private class ownSelectFieldKostenTyp extends MyE2_DB_SelectField {
			public ownSelectFieldKostenTyp(SQLField osqlField)	throws myException {
				super(osqlField, ENUM_KOSTENTYP.FAHRTKOSTEN_PAUSCHAL.getArray4Selfield(true), false, new Extent(150));
			}
		}
		
		
		
		
		private class KostenListFormatingAgent extends XX_ComponentMAP_SubqueryAGENT 	{
			public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 	{}
		
			public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException	{
			}
		}
		
		
		
		
		private class MS___KostenLIST_SqlFieldMAP extends Project_SQLFieldMAP{
		
			public MS___KostenLIST_SqlFieldMAP() throws myException 			{
				super(_TAB.maschinen_kosten.n(), MASCHINEN_KOSTEN.id_maschinen.fn() , false);
				
				this.add_SQLField(new SQLFieldForRestrictTableRange(_TAB.maschinen_kosten.n(),MASCHINEN_KOSTEN.id_maschinen.fn(),MASCHINEN_KOSTEN.id_maschinen.fn(),new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);

				this.initFields();
			}
		}
		
			
		
		private class MS___MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK	{
		
			public MS___MASK_BasicModuleContainer() throws myException	{
				
				super(MS__BasicModuleContainerKosten.this.oCallingContainer.get_MODUL_IDENTIFIER()+MS__BasicModuleContainerKosten.MODULKENNER_ADDON_MASK);
							
				this.set_bVisible_Row_For_Messages(true);
				
				KostenMaskComponentMap oMS___MASK_ComponentMAP = new KostenMaskComponentMap();
				
				this.INIT(oMS___MASK_ComponentMAP, new KostenMask(oMS___MASK_ComponentMAP), new Extent(700), new Extent(400));
			}
			
			
		}
		
		
		
		
		private class KostenMaskComponentMap extends E2_ComponentMAP {
		
			public KostenMaskComponentMap() throws myException	{
				super(MS__BasicModuleContainerKosten.this.oSQLFieldMapMask);
				
				SQLFieldMAP oFM = this.get_oSQLFieldMAP();

				this.add_Component(new MyE2_DB_Label(oFM.get_(MASCHINEN_KOSTEN.id_maschinen_kosten.fn())),				new MyE2_String("id"));
				this.add_Component(new ownSelectFieldKostenTyp(oFM.get_(MASCHINEN_KOSTEN.ms_enum_kostentyp.fn())), 		new MyE2_String("Kostentyp"));
				this.add_Component(new MyE2_DB_TextField(oFM.get_(MASCHINEN_KOSTEN.kosten_beschreibung.fn()),true,300), new MyE2_String("kostenbeschreibung"));
				this.add_Component(new MyE2_DB_TextField(oFM.get_(MASCHINEN_KOSTEN.kosten_betrag.fn())), 				new MyE2_String("Betrag"));
				
				/*
		 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
				 */
				this.set_oMAPSettingAgent(new MS___MASK_MapSettingAgent());
				
				/*
				 * ermoeglicht formatierungen von zusatzinfos in der maske
				 */
				this.set_oSubQueryAgent(new MS___MASK_FORMATING_Agent());
			}
			
		}
		
		
		
		private class MS___MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT	{
			public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException	{}
			public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException	{}
		}
		
		
		
		
		
		private class MS___MASK_MapSettingAgent extends XX_MAP_SettingAgent {
			public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException	{}
			public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException {}
		}
		
		
		
		
		
		private class SqlFieldMapKostenMask extends Project_SQLFieldMAP {
			public SqlFieldMapKostenMask() throws myException 	{
				super(_TAB.maschinen_kosten.n(), MASCHINEN_KOSTEN.id_maschinen.fn(), false);
			
				this.add_SQLField(new SQLFieldForRestrictTableRange(_TAB.maschinen_kosten.n(),MASCHINEN_KOSTEN.id_maschinen.fn(),MASCHINEN_KOSTEN.id_maschinen.fn(),new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
				this.initFields();
			}
		}
		
		
		
		private class KostenMask extends MyE2_Column  implements IF_BaseComponent4Mask{
		
			private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
			
			public KostenMask(KostenMaskComponentMap oMap) throws myException {
				super(MyE2_Column.STYLE_NO_BORDER());
			
				E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
				
				MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
				
				this.add(oGrid0, E2_INSETS.I_2_2_2_2);
			
				//hier kommen die Felder	
				oFiller.add_Line(oGrid0, new MyE2_String("ID"), 1, 				MASCHINEN_KOSTEN.id_maschinen_kosten.fn()+"|#  |",3);
				oFiller.add_Line(oGrid0, new MyE2_String("Kostenart"), 1, 		MASCHINEN_KOSTEN.ms_enum_kostentyp.fn()+"|#  |",3);
				oFiller.add_Line(oGrid0, new MyE2_String("Beschreibung"), 1, 	MASCHINEN_KOSTEN.kosten_beschreibung.fn()+"|#  |",3);
				oFiller.add_Line(oGrid0, new MyE2_String("Kosten"), 1, 			MASCHINEN_KOSTEN.kosten_betrag.fn()+"|# EUR|",3);
		
				this.vMaskGrids.add(oGrid0);
			}

			@Override
			public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException	{
				return this.vMaskGrids;
			}
		}
		

		
		private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert{

			public ownSelectPopupAllNoneInvert() {
				super(MS__BasicModuleContainerKosten.this.oNaviList.get_vComponentMAPS());
				
				this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
				this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
			}
		}
		

		
		
		
	}

	
	
}
