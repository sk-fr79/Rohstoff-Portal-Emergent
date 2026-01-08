package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

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
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_POS;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;




public class KV_POS_LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_VERSPOS_LIST";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_VERSPOS_MASK";


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private VERSPOS_LIST_SqlFieldMAP oSQLFieldMapList = new VERSPOS_LIST_SqlFieldMAP();
	private VERSPOS_MASK_SQLFieldMAP oSQLFieldMapMask = new VERSPOS_MASK_SQLFieldMAP();


	
	private KV_HEAD_LIST_BasicModule_Inlay    kv_head_list_BasicModule_Inlay = null;


    //hier den namen des ID-Feldes der eigenen Tabelle (Tochter), was die Verbindung zur Muttertabelle herstellt (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   = "ID_KREDITVERS_KOPF";
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = E2_MODULNAMES.NAME_MODUL_KREDITVERSICHERUNG_POS_INLAY; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	//private Color  				oColorBackgroundListHeader = new E2_ColorLLight();
	private Color  				oColorBackgroundListHeader = new E2_ColorLight();

	//navigationlist muss klassenobjekt sein
	//@martin: 20171122: schmaelere navizeile
	private KV_PosNavigationList oNaviList = null;

	private E2_SelectionComponentsVector 	oSelVector = null;
	
	
	//die listenbuttons muessen exportiert werden koennen
	private VERSPOS_LIST_BT_NEW  oButtonNewInList = null; 
	private VERSPOS_LIST_BT_VIEW oButtonViewInList = null; 
	private VERSPOS_LIST_BT_EDIT oButtonEditInList = null; 
	private VERSPOS_LIST_BT_DELETE oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;


	private String idKopfSatz = null;
	

	public KV_POS_LIST_BasicModule_Inlay(KV_HEAD_LIST_BasicModule_Inlay  p_kv_head_list_BasicModule_Inlay) throws myException
	{
		super();
		
		this.kv_head_list_BasicModule_Inlay = p_kv_head_list_BasicModule_Inlay;
		
		this.set_MODUL_IDENTIFIER(p_kv_head_list_BasicModule_Inlay.get_MODUL_IDENTIFIER()+KV_POS_LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new KV_PosNavigationList();
		
		// hier den "Listenselektionsvector initialisieren
		this.oSelVector = new E2_SelectionComponentsVector(oNaviList);

		
		oNaviList.INIT_WITH_ComponentMAP(new VERSPOS_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		VERSPOS_LIST_BedienPanel oPanel = new VERSPOS_LIST_BedienPanel(oNaviList,new VERSPOS_MASK_BasicModuleContainer());
		// this.add(oPanel);
		
		this.oNaviList.add_ComponentBevorNaviElements(oPanel);
		this.add(oNaviList, E2_INSETS.I_0_0_0_0);
		
		// Selector 
		oSelVector.doActionPassiv();
	}
		


	//beide fieldmaps werden nach einem restrict-Field durchsucht und dann mit dem wert gefuettert
	public void set_ID_From_Calling_Record(String cRestrictValueInDBFormat) throws myException
	{
		this.idKopfSatz = cRestrictValueInDBFormat;
		
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
			this.oButtonSelVisibleCols.set_bEnabled_For_Edit(true);
		}
		
	}
	



	private class VERSPOS_LIST_BedienPanel extends MyE2_Column 
	{
		
		public VERSPOS_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
			
			KV_POS_LIST_BasicModule_Inlay oThis = KV_POS_LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,2));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(0,0,3,0));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(0,0,10,0));

			RowLayoutData  oRowLayout_XXX_Wide = new RowLayoutData();
			oRowLayout_XXX_Wide.setBackground(KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout_XXX_Wide.setInsets(new Insets(0,0,30,0));

			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  	oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new VERSPOS_LIST_BT_NEW(oNaviList,oMaskContainer), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new VERSPOS_LIST_BT_VIEW(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new VERSPOS_LIST_BT_EDIT(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new VERSPOS_LIST_BT_DELETE(oNaviList), 					oRowLayout);
			
			// Selektor in den Bedienpanel einbauen, damit man die aktiven selektieren kann
			MyE2_CheckBox oCBNurAktive = new MyE2_CheckBox("Nur aktive Einträge",true, false);
			oSelVector.add(new E2_ListSelectorStandard(oCBNurAktive," NVL(JT_KREDITVERS_POS.AKTIV,'N')='Y'" ,""));
			oRowForComponents.add(oCBNurAktive,oRowLayout_XXX_Wide);
			
		}
	
	}
	
	
	public VERSPOS_LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public VERSPOS_LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public VERSPOS_LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public VERSPOS_LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	private class VERSPOS_LIST_BT_DELETE extends MyE2_Button
	{
	
		public VERSPOS_LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_POS_LIST_BasicModule_Inlay.this.kv_head_list_BasicModule_Inlay.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+KV_POS_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
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
	
	
	
	private class VERSPOS_LIST_BT_EDIT extends MyE2_Button
	{
	
		public VERSPOS_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_POS_LIST_BasicModule_Inlay.this.kv_head_list_BasicModule_Inlay.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+KV_POS_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
				
				this.get_oButtonMaskSave().add_oActionAgent(new ownActionRefreshListAfterSave());
				
			}
		}
	}
	
	
	
	private class VERSPOS_LIST_BT_NEW extends MyE2_Button
	{
	
		public VERSPOS_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_POS_LIST_BasicModule_Inlay.this.kv_head_list_BasicModule_Inlay.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+KV_POS_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
				this.get_oButtonMaskSave().add_oActionAgent(new ownActionRefreshListAfterSave());
			}
		}
		
	}
	
	
	private class ownActionRefreshListAfterSave extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			KV_POS_LIST_BasicModule_Inlay.this.oSelVector.doActionPassiv();
		}

	}
	
	
	
	private class VERSPOS_LIST_BT_VIEW extends MyE2_Button
	{
	
		public VERSPOS_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_POS_LIST_BasicModule_Inlay.this.kv_head_list_BasicModule_Inlay.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+KV_POS_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
	
	
	
	
	private class VERSPOS_LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public VERSPOS_LIST_ComponentMap() throws myException
		{
			super(KV_POS_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(KV_POS_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_KREDITVERS_POS")), new MyE2_String("ID"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("Aktiv"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BETRAG")), new MyE2_String("Betrag (Eur)"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GUELTIG_AB")), new MyE2_String("Gültig ab"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GUELTIG_BIS")), new MyE2_String("Gültig bis"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BETRAG_ANFRAGE")), new MyE2_String("Betrag Anfrage (Eur)"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_ANFRAGE")), new MyE2_String("Datum Anfrage"));
			this.add_Component(new KV_POS_SelectField_Bedingung(oFM.get_("ID_KREDITLIMIT_BEDINGUNG1"), 100), new MyE2_String("Bedingung 1"));
			this.add_Component(new KV_POS_SelectField_Bedingung(oFM.get_("ID_KREDITLIMIT_BEDINGUNG2"), 100), new MyE2_String("Bedingung 2"));
			this.add_Component(new KV_POS_SelectField_Bedingung(oFM.get_("ID_KREDITLIMIT_BEDINGUNG3"), 100), new MyE2_String("Bedingung 3"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_KREDITVERS_KOPF")), new MyE2_String("ID Kopf"));
			
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_bIsVisibleInList(false);
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_bIsVisibleInList(false);
			
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataRight  oGLRightTitel = new ownGridLayoutDataRight(
					KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataRight  oGLRightInhalt = new ownGridLayoutDataRight(
					KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			this.get__Comp(KV_POS_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp(KV_POS_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			
			this.get__Comp("AKTIV").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BETRAG").EXT().set_oLayout_ListElement(oGLRightInhalt);
			
			this.get__Comp("BETRAG_ANFRAGE").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("DATUM_ANFRAGE").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("GUELTIG_AB").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("GUELTIG_BIS").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG1").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG2").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG3").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("AKTIV").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BETRAG").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("BETRAG_ANFRAGE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("DATUM_ANFRAGE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("GUELTIG_AB").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("GUELTIG_BIS").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp(KV_POS_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, KV_POS_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));
			oStyleSmallTextLightColor.setProperty(PROPERTY_FOREGROUND, Color.DARKGRAY);
			
            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp("AKTIV").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BETRAG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BETRAG_ANFRAGE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("DATUM_ANFRAGE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("GUELTIG_AB").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("GUELTIG_BIS").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG1").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG2").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG3").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
	
			//weitere definitionen
			this.get__Comp("AKTIV").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BETRAG").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("BETRAG_ANFRAGE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("DATUM_ANFRAGE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("GUELTIG_AB").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("GUELTIG_BIS").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG1").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG2").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG3").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
	
			this.get__Comp("AKTIV").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BETRAG").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("BETRAG_ANFRAGE").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("DATUM_ANFRAGE").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("GUELTIG_AB").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("GUELTIG_BIS").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG1").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG2").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG3").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_oLayout_ListElement(oGLLeftInhalt);
	
			this.get__Comp("AKTIV").EXT().set_oColExtent(new Extent(50));
			this.get__Comp("BETRAG").EXT().set_oColExtent(new Extent(120));
			this.get__Comp("BETRAG_ANFRAGE").EXT().set_oColExtent(new Extent(150));
			this.get__Comp("DATUM_ANFRAGE").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("GUELTIG_AB").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("GUELTIG_BIS").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG1").EXT().set_oColExtent(new Extent(150));
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG2").EXT().set_oColExtent(new Extent(150));
			this.get__Comp("ID_KREDITLIMIT_BEDINGUNG3").EXT().set_oColExtent(new Extent(150));
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oColExtent(new Extent(50));
			this.get__Comp("ID_KREDITVERS_POS").EXT().set_oColExtent(new Extent(50));


		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				VERSPOS_LIST_ComponentMap oCopy = new VERSPOS_LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	private class VERSPOS_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public VERSPOS_LIST_SqlFieldMAP() throws myException 
		{
			super("JT_KREDITVERS_POS", KV_POS_LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_KREDITVERS_POS",KV_POS_LIST_BasicModule_Inlay.CONNECTION_FIELD,KV_POS_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			this.initFields();
		}
		
	}
	
	
		
	
	private class VERSPOS_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
	{
	
		public VERSPOS_MASK_BasicModuleContainer() throws myException
		{
			super(KV_POS_LIST_BasicModule_Inlay.this.kv_head_list_BasicModule_Inlay.get_MODUL_IDENTIFIER()+KV_POS_LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK);
						
			this.set_bVisible_Row_For_Messages(true);
			
			VERSPOS_MASK_ComponentMAP oVERSPOS_MASK_ComponentMAP = new VERSPOS_MASK_ComponentMAP();
			
			this.INIT(oVERSPOS_MASK_ComponentMAP, new VERSPOS_MASK(oVERSPOS_MASK_ComponentMAP), new Extent(900), new Extent(650));
		}
		
		
	}
	
	
	
	
	private class VERSPOS_MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		public VERSPOS_MASK_ComponentMAP() throws myException
		{
			super(KV_POS_LIST_BasicModule_Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			
		
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("Aktiv"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("BETRAG"),true,100,19,false), new MyE2_String("Betrag (Eur)"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("BETRAG_ANFRAGE"),true,100,19,false), new MyE2_String("Betrag Anfrage (Eur)"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATUM_ANFRAGE"),"",100), new MyE2_String("Datum der Anfrage"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_AB"),"",100), new MyE2_String("Gültig ab"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_BIS"),"",100), new MyE2_String("Gültig bis"));
			
			this.add_Component(new KV_POS_SelectField_Bedingung(oFM.get_("ID_KREDITLIMIT_BEDINGUNG1"), 100), new MyE2_String("Bedingung 1"));
			this.add_Component(new KV_POS_SelectField_Bedingung(oFM.get_("ID_KREDITLIMIT_BEDINGUNG2"), 100), new MyE2_String("Bedingung 2"));
			this.add_Component(new KV_POS_SelectField_Bedingung(oFM.get_("ID_KREDITLIMIT_BEDINGUNG3"), 100), new MyE2_String("Bedingung 3"));
			
			
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_KREDITVERS_KOPF"),true,100,10,false), new MyE2_String("ID KredVers"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_KREDITVERS_POS"),true,100,10,false), new MyE2_String("ID Pos"));
			
			//20190627: martin
			this.register_Interactiv_settings_validation(KREDITVERS_POS.id_kreditvers_pos.fn(),
					new KV_MASK_POS_MapSetAndValidatorPositionsAblaufDatum(KV_POS_LIST_BasicModule_Inlay.this, kv_head_list_BasicModule_Inlay));
			
		}
		
	}
	
	
	
	
	
	
	private class VERSPOS_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public VERSPOS_MASK_SQLFieldMAP() throws myException 
		{
			super("JT_KREDITVERS_POS", KV_POS_LIST_BasicModule_Inlay.CONNECTION_FIELD, false);
		
			/*
			 * beispiel fuer die definition von standard-werten
			 */
			this.get_("AKTIV").set_cDefaultValueFormated("Y");
			
			this.get_("ID_KREDITVERS_POS").set_cDefaultValueFormated("");
			this.get_("AKTIV").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("BETRAG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("GUELTIG_AB").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_KREDITVERS_POS",KV_POS_LIST_BasicModule_Inlay.CONNECTION_FIELD,KV_POS_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
	
			this.initFields();
		}
	
	}
	
	
	
	
	
	/*
	* maskenvariante mit TabbedPane 
	*/
	private class VERSPOS_MASK extends MyE2_Column   implements IF_BaseComponent4Mask	
	{
		private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
		
		public VERSPOS_MASK(VERSPOS_MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			
			
			this.add(oGrid0, E2_INSETS.I_2_2_2_2);
			Alignment align = new Alignment(Alignment.LEFT,Alignment.CENTER);
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("IDKopf:"), 1, "ID_KREDITVERS_KOPF|#  |#IDPos:|ID_KREDITVERS_POS|# |",3,align);
			
			oFiller.add_Line(oGrid0, new MyE2_String("Aktiv:"), 1, "AKTIV|#  |",3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Betrag (Eur):"), 1, "BETRAG|#  |",3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Gültig ab:"), 1, "GUELTIG_AB|#  |#" + new MyE2_String("Gültig bis:").CTrans() + "|# |GUELTIG_BIS|#  |"  ,3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Betrag Anfrage (Eur):"), 1, "BETRAG_ANFRAGE|#  |",3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Datum Anfrage:"), 1, "DATUM_ANFRAGE|#  |",3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Bedingung 1:"), 1, "ID_KREDITLIMIT_BEDINGUNG1|#  |",3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Bedingung 2:"), 1, "ID_KREDITLIMIT_BEDINGUNG2|#  |",3,align);
			oFiller.add_Line(oGrid0, new MyE2_String("Bedingung 3:"), 1, "ID_KREDITLIMIT_BEDINGUNG3|#  |",3,align);
	
			this.vMaskGrids.add(oGrid0);
			
		}

		@Override
		public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
		{
			return this.vMaskGrids;
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

	
	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(KV_POS_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}


	/**
	 * 
	 * @author martin
	 * @date 27.06.2019
	 *
	 * @return
	 */
	public String getIdKopfSatz() {
		return idKopfSatz;
	}
	
	
}
