package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SelectFieldFuhreKostenTyp;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentMarkDeletedRows;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;




public class FUK__LIST_BasicModule_Inlay extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
//	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_LIST_FUHREN_KOSTEN";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_MASK_FUHREN_KOSTEN";


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private FUK__LIST_SqlFieldMAP oSQLFieldMapList = new FUK__LIST_SqlFieldMAP();
	private FUK__MASK_SQLFieldMAP oSQLFieldMapMask = new FUK__MASK_SQLFieldMAP();


    //hier den namen des ID-Feldes aus der rufenden Einheit (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_VPOS_TPA_FUHRE;
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "FUHREN_KOSTEN"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorBase();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;

	
	//die listenbuttons muessen exportiert werden koennen
	private FUK__LIST_BT_NEW  oButtonNewInList = null; 
	private FUK__LIST_BT_VIEW oButtonViewInList = null; 
	private FUK__LIST_BT_EDIT oButtonEditInList = null; 
	private FUK__LIST_BT_DELETE oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;

	
	private String       cMODULKENNER_FUER_BUTTON_AUTH = null;
	
	
	private E2_ComponentMAP      oComponentMapOfCalling_FUHREN_NaviListRow = null;
	



	public FUK__LIST_BasicModule_Inlay(String MODULKENNER, boolean bStandAlone) throws myException
	{
		super(bStandAlone?MODULKENNER:MODULKENNER+FUK__LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);

		this.cMODULKENNER_FUER_BUTTON_AUTH = MODULKENNER;
		
		this.set_bVisible_Row_For_Messages(bStandAlone);
		
		this.oNaviList = new E2_NavigationList();
		
		oNaviList.INIT_WITH_ComponentMAP(new FUK__LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		FUK__LIST_BedienPanel oPanel = new FUK__LIST_BedienPanel(oNaviList);
		this.add(oPanel, E2_INSETS.I_1_0_0_0);
		this.add(oNaviList, E2_INSETS.I_0_0_0_0);
		
		//sonst
		oNaviList._REBUILD_COMPLETE_LIST("");    
	}
		


	//beide fieldmaps werden nach einem restrict-Field durchsucht und dann mit dem wert gefuettert
	public void set_ID_From_Calling_Record(String cID_VPOS_TPA_FUHRE) throws myException
	{
		Iterator<Entry<String, SQLField>> it = this.oSQLFieldMapList.entrySet().iterator(); 
		
		while (it.hasNext()) 
		{
		    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it.next();
		    
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange)
		    {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cID_VPOS_TPA_FUHRE);
		    }
		} 			

		Iterator<Entry<String, SQLField>> it2 = this.oSQLFieldMapMask.entrySet().iterator(); 
		
		while (it2.hasNext()) 
		{
		    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it2.next();
		    
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange)
		    {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cID_VPOS_TPA_FUHRE);
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
	



	private class FUK__LIST_BedienPanel extends MyE2_Column 
	{
		public FUK__LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
			
			FUK__LIST_BasicModule_Inlay oThis = FUK__LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
			
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,0));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(1,0,3,0));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(1,0,10,0));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new FUK__LIST_BT_NEW(oNaviList), 							oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new FUK__LIST_BT_VIEW(oNaviList), 							oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new FUK__LIST_BT_EDIT(oNaviList), 							oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new FUK__LIST_BT_DELETE(oNaviList), 						oRowLayout);
		}
	}
	
	
	public FUK__LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public FUK__LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public FUK__LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public FUK__LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	private class FUK__LIST_BT_DELETE extends MyE2_Button
	{
	
		public FUK__LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH, "LOESCHE_"+FUK__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
			this.add_IDValidator(new XX_ActionValidator()
			{
				@Override
				protected MyE2_MessageVector isValid(String cID_Unformated) 	throws myException
				{
					MyE2_MessageVector  oMV = new MyE2_MessageVector();
					
					RECORD_VPOS_TPA_FUHRE_KOSTEN  recTest = new RECORD_VPOS_TPA_FUHRE_KOSTEN(cID_Unformated);
					
					if (recTest.is_DELETED_YES())
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Dieser Kostenpunkt ist bereits gelöscht !")));
					}
					if (recTest.is_BELEG_VORHANDEN_YES())
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Kostenpositionen mit dem Merkmal <Beleg vorhanden> können nicht gelöscht werden !")));
					}
					
					return oMV;
				}
				
				@Override
				public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException
				{
					return null;
				}
			});
			
		}
		
	
		private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
		{
			public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
			{
				super(onavigationList, "JT_VPOS_TPA_FUHRE_KOSTEN", true);
			}
			
			public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) 	{return  new MyE2_MessageVector();}
			
			@Override
			public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete,Vector<String> vSQL_Stack, String cLoeschInfoText)throws myException
			{
			}
	
		}
		
	
	
		
		
	}
	
	
	
	
	
	
	
	private class FUK__LIST_BT_EDIT extends MyE2_Button
	{
	
		public FUK__LIST_BT_EDIT(E2_NavigationList onavigationList) throws myException
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH,"BEARBEITE_"+FUK__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

			this.add_IDValidator(new XX_ActionValidator()
			{
				@Override
				protected MyE2_MessageVector isValid(String cID_Unformated)		throws myException
				{
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					
					RECORD_VPOS_TPA_FUHRE_KOSTEN recKosten = new RECORD_VPOS_TPA_FUHRE_KOSTEN(cID_Unformated);
					if (recKosten.is_DELETED_YES())
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Gelöschte können nicht mehr barbeitet werden !!"));
					}
					
					return oMV;
				}
				
				@Override
				public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException
				{
					return null;
				}
			});
			
			
		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, MyE2_Button oownButton) throws myException
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, new FUK__MASK_BasicModuleContainer(
																								FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH+FUK__LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK,
																								FUK__LIST_BasicModule_Inlay.this.oSQLFieldMapMask), oownButton, null, null);
			}
		}
	}
	
	
	
	
	private class FUK__LIST_BT_NEW extends MyE2_Button
	{
	
		public FUK__LIST_BT_NEW(E2_NavigationList onavigationList) throws myException
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH,"NEUEINGABE_"+FUK__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ownButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton) throws myException
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, new FUK__MASK_BasicModuleContainer(
																				FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH+FUK__LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK,
																				FUK__LIST_BasicModule_Inlay.this.oSQLFieldMapMask), oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	
	
	private class FUK__LIST_BT_VIEW extends MyE2_Button
	{
	
		public FUK__LIST_BT_VIEW(E2_NavigationList onavigationList) throws myException
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH,"ANZEIGE_"+FUK__LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, MyE2_Button oownButton) throws myException
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, 
						new FUK__MASK_BasicModuleContainer(	FUK__LIST_BasicModule_Inlay.this.cMODULKENNER_FUER_BUTTON_AUTH+FUK__LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK,
															FUK__LIST_BasicModule_Inlay.this.oSQLFieldMapMask), oownButton,null);
			}
		}
		
	}
	
	
	
	private class FUK__LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public FUK__LIST_ComponentMap() throws myException
		{
			super(FUK__LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(FUK__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
	
			BS__SelectFieldFuhreKostenTyp  oSFKostenTyp = new BS__SelectFieldFuhreKostenTyp(oFM.get_SQLField(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_FUHREN_KOSTEN_TYP),170);
			oSFKostenTyp.setFont(new E2_FontPlain(-2));

			
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BETRAG_KOSTEN"),false,100), new MyE2_String("Betrag"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FREMDBELEG_NUMMER"),false,100), new MyE2_String("Fremdbeleg"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("INFO_KOSTEN"),true,200), new MyE2_String("Info"));
			this.add_Component(oSFKostenTyp, new MyE2_String("Typ"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_BELEG"),false,60), new MyE2_String("Datum"));
			this.add_Component(new ownCheckBox_BELEG_VORHANDEN(oFM.get_("BELEG_VORHANDEN")), new MyE2_String("OK"));
			
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_5_1_5_1);
			
			ownGridLayoutDataRight  oGLRightTitel = new ownGridLayoutDataRight(
					FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_5_1_5_1);

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_5_1_5_1);
			
			ownGridLayoutDataRight  oGLRightInhalt = new ownGridLayoutDataRight(
					FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_5_1_5_1);

			
			this.get__Comp(FUK__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			this.get__Comp("BETRAG_KOSTEN").EXT().set_oLayout_ListElement(oGLRightInhalt);
			this.get__Comp("FREMDBELEG_NUMMER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_FUHREN_KOSTEN_TYP").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("INFO_KOSTEN").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BELEG_VORHANDEN").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DATUM_BELEG").EXT().set_oLayout_ListElement(oGLLeftInhalt);

			
			this.get__Comp(FUK__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BETRAG_KOSTEN").EXT().set_oLayout_ListTitelElement(oGLRightTitel);
			this.get__Comp("FREMDBELEG_NUMMER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_FUHREN_KOSTEN_TYP").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("INFO_KOSTEN").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BELEG_VORHANDEN").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DATUM_BELEG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			

			this.get__Comp(FUK__LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, FUK__LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

//            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp("BETRAG_KOSTEN").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("FREMDBELEG_NUMMER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_FUHREN_KOSTEN_TYP").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("INFO_KOSTEN").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BELEG_VORHANDEN").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("DATUM_BELEG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
	
			this.set_oSubQueryAgent(new FUK__LIST_FORMATING_Agent());
			
			//loeschmarkiereungen und anzeige loeschgrund
			this.set_oSubQueryAgent(new BS__SubQueryAgentMarkDeletedRows());
			this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

			
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				FUK__LIST_ComponentMap oCopy = new FUK__LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	private class FUK__LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	}
	
	
	
	private class ownCheckBox_BELEG_VORHANDEN extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction
	{

		public ownCheckBox_BELEG_VORHANDEN(SQLField osqlField) 	throws myException
		{
			super(osqlField);
			this.add_oActionAgent(this.get_ToggleAction());
		}

		public Object get_Copy(Object ob) throws myExceptionCopy
		{
			ownCheckBox_BELEG_VORHANDEN oCheckCopy = null;
			try
			{
				oCheckCopy = new ownCheckBox_BELEG_VORHANDEN(this.EXT_DB().get_oSQLField());
			}
			catch (myException ex)
			{
				throw new myExceptionCopy("ownCheckBox_BELEG_VORHANDEN:get_Copy:copy-error!");
			}
			
			oCheckCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCheckCopy));
			oCheckCopy.__setText(this.get_oText());
			
			oCheckCopy.setFont(this.getFont());
			
			if (this.getIcon() != null)
				oCheckCopy.setIcon(this.getIcon());
			
			oCheckCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oCheckCopy));
			return oCheckCopy;
		}

		@Override
		public XX_ActionAgent get_ToggleAction() throws myException
		{
			return new ownActionToggle();
		}

		private class ownActionToggle extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownCheckBox_BELEG_VORHANDEN oThis = (ownCheckBox_BELEG_VORHANDEN)oExecInfo.get_MyActionEvent().getSource();
				RECORD_VPOS_TPA_FUHRE_KOSTEN  recKosten = new RECORD_VPOS_TPA_FUHRE_KOSTEN(oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				
				String cMeldung = "";
				
				if (recKosten.is_DELETED_YES())
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Dieser Eintrag wurde bereits gelöscht !!"));
				}
				else
				{
					if (recKosten.is_BELEG_VORHANDEN_NO())
					{
						recKosten.set_NEW_VALUE_BELEG_VORHANDEN("Y");
						cMeldung = "Der Beleg wurde als VORHANDEN klassifiziert!";
					}
					else
					{
						recKosten.set_NEW_VALUE_BELEG_VORHANDEN("N");
						cMeldung = "Der Beleg wurde als FEHLEND/NICHT ABGESCHLOSSEN klassifiziert!";
					}
	
					recKosten.UPDATE(null, true);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(cMeldung));
				}
				oThis.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
				
				
				//nachsehen, ob die componentenmap der zugrundeliegenden Navilist-Zeile vorhanden ist
				if (FUK__LIST_BasicModule_Inlay.this.oComponentMapOfCalling_FUHREN_NaviListRow!=null)
				{
					FUK__LIST_BasicModule_Inlay.this.oComponentMapOfCalling_FUHREN_NaviListRow._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
				}
			}
		}
		
		
	}
	
	
	
	
	
	private class FUK__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public FUK__LIST_SqlFieldMAP() throws myException 
		{
			super("JT_VPOS_TPA_FUHRE_KOSTEN", FUK__LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_TPA_FUHRE_KOSTEN",FUK__LIST_BasicModule_Inlay.CONNECTION_FIELD,FUK__LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			this.initFields();
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
			super(FUK__LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	
	
	public E2_ComponentMAP get_oComponentMapOfCalling_FUHREN_NaviListRow()
	{
		return oComponentMapOfCalling_FUHREN_NaviListRow;
	}



	public void set_oComponentMapOfCalling_FUHREN_NaviListRow(E2_ComponentMAP oComponentMapOfCallingNaviListRow)
	{
		this.oComponentMapOfCalling_FUHREN_NaviListRow = oComponentMapOfCallingNaviListRow;
	}


	
	private class ownButtonActionAgentNEW extends ButtonActionAgentNEW {

		public ownButtonActionAgentNEW(	MyString 						actionName, 
										E2_NavigationList 				onavigationList, 
										E2_BasicModuleContainer_MASK 	omaskContainer, 
										MyE2_Button 					oownButton, 
										Vector<Component> 				ZusatzComponentsInMaskTitleButtonBar, 	
										E2_BasicModuleContainer_PopUp_BeforeExecute POPUP__Before_Execute) {
			super(actionName, onavigationList, omaskContainer, oownButton, ZusatzComponentsInMaskTitleButtonBar, POPUP__Before_Execute);

			MyE2_Button  bt_SaveAndCloseAll = new maskButtonSaveNew(POPUP__Before_Execute);
			bt_SaveAndCloseAll.setIcon(E2_ResourceIcon.get_RI("save_and_close.png"));
			bt_SaveAndCloseAll.add_oActionAgent(new action_CloseAllAfterSave());
			bt_SaveAndCloseAll.setToolTipText(new MyE2_String("Maske speichern und das Kostenfenster schließen ...").CTrans());
			
			this.get_vZusatzComponentsInMaskTitleButtonBar().add(bt_SaveAndCloseAll);   
		}
		
		//der neue button bt_SaveAndCloseAll bekommt zusaetzlich noch den agentenm die dieses modul schliesst
		private class action_CloseAllAfterSave extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FUK__LIST_BasicModule_Inlay.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Kosteneintrag wurde gespeichert")),true);
			}
		}
		
	}
	
	
	
}
