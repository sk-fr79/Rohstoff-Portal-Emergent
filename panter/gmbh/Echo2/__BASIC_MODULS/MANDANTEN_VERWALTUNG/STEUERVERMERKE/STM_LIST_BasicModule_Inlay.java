package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.STEUERVERMERKE;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;








public class STM_LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_STM_LIST";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_STM_MASK";


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private STM_LIST_SqlFieldMAP oSQLFieldMapList = new STM_LIST_SqlFieldMAP();
//	private STM_MASK_SQLFieldMAP oSQLFieldMapMask = new STM_MASK_SQLFieldMAP();


    //hier den namen des ID-Feldes aus der rufenden Einheit (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  "ID_MANDANT";
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "STEUERVERMERK_"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorBase();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;


	private E2_BASIC_EditListButtonPanel	  	oBasicEditListPanel = null;

	private STM_LIST_ComponentMap  				oComponentMAP_List = null;


	public STM_LIST_BasicModule_Inlay() throws myException
	{
		super();
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = 		new E2_NavigationList();

		this.oComponentMAP_List = 	new STM_LIST_ComponentMap();
		
		this.oBasicEditListPanel = 	new E2_BASIC_EditListButtonPanel(this.oNaviList,
				    							true,true,true,null,null,null,"", null, null, null);
		
		
		
		//jetzt die automatik-insert/update-felder ohne id_mandant bestimmen
		this.oBasicEditListPanel.set_arrayDBAutomatikFelder(bibALL.get_DB_ZusatzFelder(false, true, true, bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF(), bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("-")));
		
		this.oBasicEditListPanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
		this.oBasicEditListPanel.set_BUTTON_STATUS_VIEW();
		
		this.oNaviList.INIT_WITH_ComponentMAP(this.oComponentMAP_List,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//this.oNaviList.get_vectorSegmentation().set_iSegmentGroesse(5);
		
		//2014-06-12: neuen button implementiert
		this.oBasicEditListPanel.add(new E2_ButtonToCreate_SQL_ExportStatements(this.oNaviList), E2_INSETS.I(10,0,0,0));

		
		E2_ComponentGroupHorizontal oHorizontalgroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_2_2);
		oHorizontalgroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNaviList));
		oHorizontalgroup.add(this.oBasicEditListPanel);
		this.add(oHorizontalgroup, E2_INSETS.I_2_2_2_2);
		this.add_In_ContainerEX(this.oNaviList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);
		
		this.oNaviList._REBUILD_COMPLETE_LIST("");
		
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


	
	
	private class STM_LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public STM_LIST_ComponentMap() throws myException
		{
			super(STM_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(STM_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(STM_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			//hier kommen die Felder	
			MyE2_DB_CheckBox oCB_RC = new MyE2_DB_CheckBox(oFM.get_("REVERSE_CHARGE"));
			oCB_RC.add_oActionAgent(new ownActionPruefeReverseChargeEindeutigkeit());
			
			this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_("ID_LAND"),50), new MyE2_String("Land"));
			this.add_Component(oCB_RC, new MyE2_String("RC"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("DROPDOWN_INFO"), true, 200), new MyE2_String("Info auf Knopf"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("STEUERVERMERK_GUTSCHRIFT"),300,6,false,new E2_FontPlain(-2)), new MyE2_String("Steuervermerk des Landes für Gutschriften"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("STEUERVERMERK_RECHNUNG"),  300,6,false,new E2_FontPlain(-2)), new MyE2_String("Steuervermerk des Landes für Rechnungen"));
			
			this.add_Component(new MyE2_DB_TextField(oFM.get_("GUELTIG_AB"), true, 100), new MyE2_String("Datum ab"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("STEUERSATZ"), true, 100), new MyE2_String("Steuersatz"));
			
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MANDANT_STEUERVERMERK")), new MyE2_String("ID-Vermerk"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MANDANT")), new MyE2_String("ID-Mandant"));
			
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					STM_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					STM_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			
			
			this.get__Comp("ID_LAND").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_MANDANT_STEUERVERMERK").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_MANDANT").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("STEUERVERMERK_GUTSCHRIFT").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("STEUERVERMERK_RECHNUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("GUELTIG_AB").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("DROPDOWN_INFO").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("STEUERSATZ").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			
			this.get__Comp("ID_LAND").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_MANDANT_STEUERVERMERK").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_MANDANT").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("STEUERVERMERK_GUTSCHRIFT").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("STEUERVERMERK_RECHNUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("GUELTIG_AB").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("DROPDOWN_INFO").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("STEUERSATZ").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);


			
			this.get__Comp(STM_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, STM_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp("ID_LAND").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_MANDANT_STEUERVERMERK").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("STEUERVERMERK_GUTSCHRIFT").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("STEUERVERMERK_RECHNUNG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("GUELTIG_AB").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_MANDANT").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			this.get__Comp("DROPDOWN_INFO").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("STEUERSATZ").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
	
			this.set_oSubQueryAgent(new STM_LIST_FORMATING_Agent());
			
		}
	
		
		
		
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				STM_LIST_ComponentMap oCopy = new STM_LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
		
		private class ownActionPruefeReverseChargeEindeutigkeit extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_DB_CheckBox  oCB_RC = (MyE2_DB_CheckBox)oExecInfo.get_MyActionEvent().getSource();
				E2_ComponentMAP   oMap   = oCB_RC.EXT().get_oComponentMAP();
				
				if (oCB_RC.isSelected())
				{
				
					long           iActualID = 0;   //bei neuerfassung wird ID_MANDANT_STEUERVERMERK als 0 gefuehrt fuer vergleiche unten
					if (oMap.get_oInternalSQLResultMAP()!=null)
					{
						iActualID = oMap.get_oInternalSQLResultMAP().get_LActualDBValue("ID_MANDANT_STEUERVERMERK", false);
					}
					
					
					//hier die aktuelle id_mandant beschaffen
					String cID_Mandant = oMap.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.MANDANT_STEUERVERMERK$ID_MANDANT);
//					DEBUG.System_println(cID_Mandant);
					
					
					long lActual_ID_Land = oMap.get_LActualDBValue("ID_LAND", 0l, 0l);
					
					if (lActual_ID_Land==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst das Land wählen !!"));
						oCB_RC.setSelected(false);
						return;
					}
					
					if (lActual_ID_Land==bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(-1l))
					{
						//dann pruefen, ob es bereits einen RC-Eintrag gibt, der nicht diese zeile ist
						//zuerst pruefung in der Datenbank
//						RECLIST_MANDANT_STEUERVERMERK reclistVM = new RECLIST_MANDANT_STEUERVERMERK(
//								"SELECT * FROM JD_MANDANT_STEUERVERMERK WHERE ID_LAND="+lActual_ID_Land);
						
						//2014-06-12: fehler, da mandanten nicht beruecksichtigt werden
						RECLIST_MANDANT_STEUERVERMERK reclistVM = new RECLIST_MANDANT_STEUERVERMERK(
								"SELECT * FROM JD_MANDANT_STEUERVERMERK WHERE ID_LAND="+lActual_ID_Land+" AND "+_DB.MANDANT_STEUERVERMERK$ID_MANDANT+"="+cID_Mandant);
						
						
						Iterator<RECORD_MANDANT_STEUERVERMERK>  oIter = reclistVM.values().iterator();
						
						while (oIter.hasNext())
						{
							RECORD_MANDANT_STEUERVERMERK recVM = oIter.next();
							
							if (recVM.get_ID_MANDANT_STEUERVERMERK_lValue(-1l)!=iActualID)
							{
								if (recVM.is_REVERSE_CHARGE_YES())
								{
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es existiert bereits ein Reverse-Charge-Eintrag zu diesem Land in der Datenbank. Diesen zuerst entfernen !!!"));
									oCB_RC.setSelected(false);
									return;
								}
							}
						}
						
						
						//dann koennten noch weitere zum bearbeiten geoeffnet sein und ebenfalls angehakt, dies ebenfalls checken
						for (E2_ComponentMAP o_Map: oMap.get_VectorComponentMAP_thisBelongsTo())
						{
							if (o_Map != oMap)
							{
								long lActual_ID_Land2 = o_Map.get_LActualDBValue("ID_LAND", 0l, 0l);
								
								if (lActual_ID_Land2==bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(-1l))
								{
									MyE2_DB_CheckBox  oCB_in_map = (MyE2_DB_CheckBox)o_Map.get__Comp("REVERSE_CHARGE");
									if (oCB_in_map.isSelected())    //dann wurde eine weitere Zeile als reverse-charge-zeile gewaehlt
									{
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben eine andere Zeile bereits als RC markiert !!!"));
										oCB_RC.setSelected(false);
										return;
									}
								}
							}
						}
					}
				}
			}
		}
		
		
	}
	
	
	
	
	
	
	
	private class STM_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	private class STM_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public STM_LIST_SqlFieldMAP() throws myException 
		{
			super("JD_MANDANT_STEUERVERMERK", STM_LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JD_MANDANT_STEUERVERMERK",STM_LIST_BasicModule_Inlay.CONNECTION_FIELD,STM_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			//alle Felder sind MUSS-Felder
			this.get_("ID_LAND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("STEUERVERMERK_GUTSCHRIFT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("STEUERVERMERK_RECHNUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("GUELTIG_AB").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

			this.get_("DROPDOWN_INFO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("STEUERSATZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			
			this.initFields();
		}
		
	}
	
	
		
	
	
	
	
	
		
	private class ownGridLayoutDataLeft extends GridLayoutData
	{
		public ownGridLayoutDataLeft(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(oColBack);
		}
	}

	
	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(STM_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	
	public E2_NavigationList get_oNavigationList() 
	{
		return oNaviList;
	}

	public E2_BASIC_EditListButtonPanel 		get_oBasicEditListPanel() 
	{
		return oBasicEditListPanel;
	}


	
	
	
}
