package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.DATENBLATT;

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
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
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
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_SELECTOR_ZAHLEN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_SelectField_Einheit;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB__SPECIAL_UPLOAD_IMAGE;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_DATENBLATT;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;






public class ART_DB__LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{

	
	
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_ART_DB_LIST";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_ART_DB_MASK";


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private ART_DB__LIST_SqlFieldMAP oSQLFieldMapList = new ART_DB__LIST_SqlFieldMAP();
	private ART_DB__MASK_SQLFieldMAP oSQLFieldMapMask = new ART_DB__MASK_SQLFieldMAP();


	
	private E2_BasicModuleContainer    oCallingContainer = null;


    //hier den namen des ID-Feldes der eigenen Tabelle (Tochter), was die Verbindung zur Muttertabelle herstellt (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  "ID_ARTIKEL";
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "DATENBLATT_DEFINITION"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  								oColorBackgroundListHeader = new E2_ColorDark();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList 					oNaviList = null;

	
	private ART_DB__MASK_BasicModuleContainer   oMaskModulContainer = null;

	
	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}


	//die listenbuttons muessen exportiert werden koennen
	private ART_DB__LIST_BT_NEW  oButtonNewInList = null; 
	private ART_DB__LIST_BT_VIEW oButtonViewInList = null; 
	private ART_DB__LIST_BT_EDIT oButtonEditInList = null; 
	private ART_DB__LIST_BT_DELETE oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;



	public ART_DB__LIST_BasicModule_Inlay(E2_BasicModuleContainer  CallingContainer) throws myException
	{
		super();
		
		this.oCallingContainer = CallingContainer;
		
		this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+ART_DB__LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new E2_NavigationList();
		
		oNaviList.INIT_WITH_ComponentMAP(new ART_DB__LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		ART_DB__LIST_BedienPanel oPanel = new ART_DB__LIST_BedienPanel(oNaviList,this.oMaskModulContainer=new ART_DB__MASK_BasicModuleContainer());
		this.add(oPanel, new Insets(2,2,2,0));
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
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
		
		
		RECORD_ARTIKEL  recArtikel = new RECORD_ARTIKEL(new MyInteger(cRestrictValueInDBFormat).get_cUF_IntegerString());
		
		//hier ein paar standard-werte setzen
		this.oSQLFieldMapMask.get_("GEGENSTAND").set_cDefaultValueFormated(recArtikel.get_ARTBEZ1_cUF_NN(""));
		this.oSQLFieldMapMask.get_("DATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.oSQLFieldMapMask.get_("ID_USER_KONTAKT").set_cDefaultValueFormated(bibALL.get_ID_USER());
		
		this.oNaviList._REBUILD_COMPLETE_LIST("");
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
	



	private class ART_DB__LIST_BedienPanel extends MyE2_Column 
	{
		
		public ART_DB__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
			
			ART_DB__LIST_BasicModule_Inlay oThis = ART_DB__LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
			
			Color  oColBasic = new E2_ColorBase();
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(oColBasic);
			oColLayout.setInsets(new Insets(0,0,0,2));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(oColBasic);
			oRowLayout.setInsets(new Insets(1,1,3,1));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(oColBasic);
			oRowLayoutWide.setInsets(new Insets(1,1,10,1));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new ART_DB__LIST_BT_NEW(oNaviList,oMaskContainer), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new ART_DB__LIST_BT_VIEW(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new ART_DB__LIST_BT_EDIT(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new ART_DB__LIST_BT_DELETE(oNaviList), 					oRowLayout);
		
		}
	
	}
	
	
	public ART_DB__LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public ART_DB__LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public ART_DB__LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public ART_DB__LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	private class ART_DB__LIST_BT_DELETE extends MyE2_Button
	{
	
		public ART_DB__LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(ART_DB__LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+ART_DB__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
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
	
	
	
	
	
	
	
	private class ART_DB__LIST_BT_EDIT extends MyE2_Button
	{
	
		public ART_DB__LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(ART_DB__LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+ART_DB__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			}
		}
		
	
	}
	
	
	
	
	private class ART_DB__LIST_BT_NEW extends MyE2_Button
	{
	
		public ART_DB__LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(ART_DB__LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+ART_DB__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class ART_DB__LIST_BT_VIEW extends MyE2_Button
	{
	
		public ART_DB__LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(ART_DB__LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+ART_DB__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
	
	
	
	
	private class ART_DB__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public ART_DB__LIST_SqlFieldMAP() throws myException 
		{
			super("JT_ARTIKEL_DATENBLATT", ART_DB__LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARTIKEL_DATENBLATT",ART_DB__LIST_BasicModule_Inlay.CONNECTION_FIELD,ART_DB__LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			
			
			this.initFields();
		}
		
	}

	
	
	private class ART_DB__LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public ART_DB__LIST_ComponentMap() throws myException
		{
			super(ART_DB__LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(ART_DB__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(ART_DB__LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEGENSTAND")), 			new MyE2_String("Gegenstand"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EU_SORTE")), 				new MyE2_String("EU-Sorte"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ABMESSUNG1")), 			new MyE2_String("A1"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ABMESSUNG2")),	 			new MyE2_String("A2"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ABMESSUNG3")), 			new MyE2_String("A3"));
			this.add_Component(new DB_SelectField_Einheit(oFM.get_("ID_EINHEIT_ABMESSUNG")), 	new MyE2_String("EH"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STAERKE")), 				new MyE2_String("Stärke"));
			this.add_Component(new DB_SelectField_Einheit(oFM.get_("ID_EINHEIT_STAERKE")), 	new MyE2_String("EH-S"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEZEICHNUNG")), 			new MyE2_String("Bezeichnung"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM")), 					new MyE2_String("Datum"));
			
			this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_KONTAKT"),true), new MyE2_String("Kontakt"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID(Art)"),false,false);
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL_DATENBLATT")), new MyE2_String("ID"),false,true);

			
			Color oColorBase = new E2_ColorBase();
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					ART_DB__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_2_1_2_1);
			
			ownGridLayoutDataRight  oGLRightTitel = new ownGridLayoutDataRight(
					ART_DB__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_2_1_2_1);

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					oColorBase, E2_INSETS.I_2_1_2_1);
			
			ownGridLayoutDataRight  oGLRightInhalt = new ownGridLayoutDataRight(
					oColorBase, E2_INSETS.I_2_1_2_1);

			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("GEGENSTAND").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("EU_SORTE").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ABMESSUNG1").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ABMESSUNG2").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ABMESSUNG3").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ID_EINHEIT_ABMESSUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("STAERKE").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ID_EINHEIT_STAERKE").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BEZEICHNUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DATUM").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_ARTIKEL").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ID_ARTIKEL_DATENBLATT").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("ID_USER_KONTAKT").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			
			
			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftInhalt);
			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftInhalt);

			this.get__Comp("GEGENSTAND").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("EU_SORTE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ABMESSUNG1").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ABMESSUNG2").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ABMESSUNG3").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ID_EINHEIT_ABMESSUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("STAERKE").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ID_EINHEIT_STAERKE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BEZEICHNUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATUM").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_ARTIKEL").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ID_ARTIKEL_DATENBLATT").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("ID_USER_KONTAKT").EXT().set_oLayout_ListElement(oGLRightInhalt);

			

			
			this.get__Comp("GEGENSTAND").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("EU_SORTE").EXT().set_oColExtent(new Extent(50));
			this.get__Comp("ABMESSUNG1").EXT().set_oColExtent(new Extent(40));
			this.get__Comp("ABMESSUNG2").EXT().set_oColExtent(new Extent(40));
			this.get__Comp("ABMESSUNG3").EXT().set_oColExtent(new Extent(40));
			this.get__Comp("ID_EINHEIT_ABMESSUNG").EXT().set_oColExtent(new Extent(40));
			this.get__Comp("STAERKE").EXT().set_oColExtent(new Extent(40));
			this.get__Comp("ID_EINHEIT_STAERKE").EXT().set_oColExtent(new Extent(40));
			this.get__Comp("BEZEICHNUNG").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("DATUM").EXT().set_oColExtent(new Extent(60));
			this.get__Comp("ID_ARTIKEL").EXT().set_oColExtent(new Extent(60));
			this.get__Comp("ID_ARTIKEL_DATENBLATT").EXT().set_oColExtent(new Extent(60));
			this.get__Comp("ID_USER_KONTAKT").EXT().set_oColExtent(new Extent(160));
			
			
			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, ART_DB__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			
			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp(ART_DB__LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			this.get__Comp("GEGENSTAND").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("EU_SORTE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ABMESSUNG1").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ABMESSUNG2").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ABMESSUNG3").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_EINHEIT_ABMESSUNG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("STAERKE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_EINHEIT_STAERKE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BEZEICHNUNG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("DATUM").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);

			this.get__Comp("ID_ARTIKEL").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_ARTIKEL_DATENBLATT").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_USER_KONTAKT").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);

	
			this.set_oSubQueryAgent(new ART_DB__LIST_FORMATING_Agent());
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				ART_DB__LIST_ComponentMap oCopy = new ART_DB__LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class ART_DB__LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	
	
	
	
	
		
	
	private class ART_DB__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
	{
	
		public ART_DB__MASK_BasicModuleContainer() throws myException
		{
			super(ART_DB__LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()+ART_DB__LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK);
						
			this.set_bVisible_Row_For_Messages(true);
			
			ART_DB__MASK_ComponentMAP oART_DB__MASK_ComponentMAP = new ART_DB__MASK_ComponentMAP();
			
			this.INIT(oART_DB__MASK_ComponentMAP, new ART_DB__MASK(oART_DB__MASK_ComponentMAP), new Extent(900), new Extent(650));
			
		}
	}
	
	
	
	
	private class ART_DB__MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	private class ART_DB__MASK_MapSettingAgent extends XX_MAP_SettingAgent {
	
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
	
	
	
	
	
	private class ART_DB__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public ART_DB__MASK_SQLFieldMAP() throws myException 
		{
			super("JT_ARTIKEL_DATENBLATT", ART_DB__LIST_BasicModule_Inlay.CONNECTION_FIELD, false);
	
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARTIKEL_DATENBLATT",ART_DB__LIST_BasicModule_Inlay.CONNECTION_FIELD,ART_DB__LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
	
			/*
			 * ein weiteres ID-Feld fuer die Datenblatt-anbindung
			 */
			this.add_SQLField(new SQLField("JT_ARTIKEL_DATENBLATT.ID_ARTIKEL_DATENBLATT", "ID_ARTIKEL_DATENBLATT_4_BILD", new MyE2_String("Bildanbindung"), bibE2.get_CurrSession()), true);


			
			this.get_(RECORD_ARTIKEL_DATENBLATT.FIELD__NACHKOMMA_ABMESSUNGEN).set_cDefaultValueFormated("0");
			this.get_(RECORD_ARTIKEL_DATENBLATT.FIELD__NACHKOMMA_STAERKE).set_cDefaultValueFormated("0");
			
			this.initFields();
		}
	
	}
	
	
	private class ART_DB__MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		public ART_DB__MASK_ComponentMAP() throws myException
		{
			super(ART_DB__LIST_BasicModule_Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ARTIKEL")),						 	new MyE2_String("ID(Sorte)"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ARTIKEL_DATENBLATT")), 				new MyE2_String("ID_(Datenblatt)"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ABMESSUNG1"),true,100,10,false), 	new MyE2_String("Abmessung 1"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ABMESSUNG2"),true,100,10,false),		new MyE2_String("Abmessung 2"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ABMESSUNG3"),true,100,10,false), 	new MyE2_String("Abmessung 3"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("STAERKE"),true,100,10,false), 		new MyE2_String("Stärke"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("GEGENSTAND"),true,400,200,false), 	new MyE2_String("Gegenstand"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("BEZEICHNUNG"),true,400,200,false), 	new MyE2_String("Bezeichnung"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("BESCHREIBUNG"),400,8), 				new MyE2_String("Beschreibung"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATUM")),   			new MyE2_String("DATUM"));
			this.add_Component(new DB_SelectField_Einheit(oFM.get_("ID_EINHEIT_ABMESSUNG"),60,true),new MyE2_String("Einheit Abmessung"));
			this.add_Component(new DB_SelectField_Einheit(oFM.get_("ID_EINHEIT_STAERKE"),60,true), 	new MyE2_String("Einheit Stärke"));
			this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_KONTAKT"),true,130),new MyE2_String("Mitarbeiter Kontakt"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("EU_SORTE"),true,100,50,false), 		new MyE2_String("EU-Sorte"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ISRI_CODE"),true,100,50,false), 		new MyE2_String("ISRI-Code"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("KLASSIFIZIERUNG"),true,400,200,false),new MyE2_String("Klassifizierung"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("LEGIERUNG_INFO"),true,400,200,false), new MyE2_String("Legierungsinfo"));
	
			String[][] cDefZahlen = {{"-",""},{"0","0"},{"1","1"},{"2","2"},{"3","3"},{"4","4"},};
			
			this.add_Component(new DB_SELECTOR_ZAHLEN(oFM.get_("NACHKOMMA_ABMESSUNGEN"),cDefZahlen,40), new MyE2_String("Nachkommastellen Abmessung"));
			this.add_Component(new DB_SELECTOR_ZAHLEN(oFM.get_("NACHKOMMA_STAERKE"),cDefZahlen,40 ), 	  new MyE2_String("Nachkommastellen Stärke"));

			//das datenblatt-bild
			this.add_Component(new ownBildVerwalter_4_Datenblatt_Bild(oFM.get_("ID_ARTIKEL_DATENBLATT_4_BILD"), MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS(),"JT_ARTIKEL_DATENBLATT",null), 	  new MyE2_String("Nachkommastellen Stärke"));
		
			/*
	 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
			 */
			this.set_oMAPSettingAgent(new ART_DB__MASK_MapSettingAgent());
			
			/*
			 * ermoeglicht formatierungen von zusatzinfos in der maske
			 */
			this.set_oSubQueryAgent(new ART_DB__MASK_FORMATING_Agent());
		}
		
	}

	
	
	private class ownBildVerwalter_4_Datenblatt_Bild extends MyE2_DB__SPECIAL_UPLOAD_IMAGE
	{
		public ownBildVerwalter_4_Datenblatt_Bild(SQLField osqlField, MutableStyle oStyle, String MOTHER_TABLE, String PROGRAMM_KENNER) throws myException
		{
			super(osqlField, oStyle, MOTHER_TABLE, PROGRAMM_KENNER);
			
			//beschriftungen regeln
			this.set__BUTTON_VALIDATOR_DELETE_IMAGE("BILD_DATENBLATT_ENTFERNEN");
			this.set__BUTTON_VALIDATOR_UPLOAD_IMAGE("BILD_DATENBLATT_HINZUFUEGEN");
			this.set__cButtonTextUpload(new MyE2_String("Datenblatt-Bild hinzufügen"));
			this.set__cButtonTextDelete(new MyE2_String("Datenblatt-Bild entfernen"));
			this.set__cButtonTextJa_Loeschen(new MyE2_String("Ja"));
			this.set__cButtonTextNein_NichtLoeschen(new MyE2_String("Nein"));

			this.set__cButtonTextUpload(new MyE2_String("Bild für Datenblatt hochladen ..."));
			this.set__cFehlermeldungBildIstDoppelt(new MyE2_String("Zu diesem Datenblatt existiert bereits ein Bild ..."));
			this.set__cLabelText4NewMask(new MyE2_String("Eine Bild kann erst zugefügt werden, wenn das Datenblatt einmal gespeichert wurde ..."));
			this.set__cUeberschrift_4_Upload_Dialog("Ein Datenblatt-Bild hochladen");
			this.set__cWarnTextVorLoeschen(new MyE2_String("Löschen des Datenblatt-Bildes"));
			
		}
		
	}

	
	
	
	


	
	
	
	
	
	
	/*
	* maskenvariante mit TabbedPane 
	*/
	private class ART_DB__MASK extends MyE2_Column implements IF_BaseComponent4Mask
	{
		private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
		
		public ART_DB__MASK(ART_DB__MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
	
			MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
			
			oTabbedPaneMaske.set_bAutoHeight(true);         //damit wird beim vergroessern die tabbedpane automatisch mitgezogen
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid1 = new MyE2_Grid(2,0);
			
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Beschreibung"), oGrid0);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Bild"), oGrid1);
			
			this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
			
			GridLayoutData gl1 = LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_2_5_0);
			
			
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("IDs<B150B>",			"Sorte:<B50B>","<B5B>","ID_ARTIKEL<B65B>", 	"<B10B>","Datenblatt:<B60B>", "<B5B>","ID_ARTIKEL_DATENBLATT<B75B>"),2);
			oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_0_0_0);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Abmessung<B150B>",		"ABMESSUNG1<B120B>",				  		"<B10B>","ABMESSUNG2<B120B>",									"<B10B>", "ABMESSUNG3<B120B>",		"ID_EINHEIT_ABMESSUNG<B60B>","<B10B>",	"Nachkomma: <B100B>","<B5B>","NACHKOMMA_ABMESSUNGEN<B50B>" ),	2);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Stärke<B150B>",			"STAERKE<B120B>",   						"<B10B>","ID_EINHEIT_STAERKE<B120B>",                        	"<B10B>", "<B190B>", 														"Nachkomma: <B100B>","<B5B>","NACHKOMMA_STAERKE<B50B>"),2);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Gegenstand<B150B>",		"GEGENSTAND<B500B>"),2);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Bezeichnung<B150B>",	"BEZEICHNUNG<B500B>"),2);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Beschreibung<B150B>",	"BESCHREIBUNG<B500B>"),2);
			
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("EU-Sorte<B150B>",		"EU_SORTE<B500B>"),2);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("ISRI-Code<B150B>",		"ISRI_CODE<B500B>"),2);

			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Klassifizierung<B150B>","KLASSIFIZIERUNG<B500B>"),2);
			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Legierung<B150B>",		"LEGIERUNG_INFO<B500B>"),2);

			oFiller.BlockAdd(oGrid0, gl1, bibVECTOR.get_Vector("Kontaktperson<B150B>",	"ID_USER_KONTAKT<B150B>", 					"Datum:<B50B>","DATUM<B75B>"),2);
			
			oGrid1.add(oMap.get_Comp("ID_ARTIKEL_DATENBLATT_4_BILD"),2, E2_INSETS.I_5_5_5_5);

			this.vMaskGrids.add(oGrid0);
			this.vMaskGrids.add(oGrid1);
			
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
			super(ART_DB__LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	

	
	
	
}
