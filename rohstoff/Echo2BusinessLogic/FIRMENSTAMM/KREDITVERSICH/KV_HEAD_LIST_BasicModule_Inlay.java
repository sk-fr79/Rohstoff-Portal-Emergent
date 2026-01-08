package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

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
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListSortHelper;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KREDITVERS_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KREDITVERS_KOPF;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;



public class KV_HEAD_LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_"+E2_MODULNAMES.NAME_MODUL_KREDITVERSICHERUNG_LISTE;
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_"+E2_MODULNAMES.NAME_MODUL_KREDITVERSICHERUNG_MASKE;
	
	public static  final String LISTE_DER_VERKNUEPFTEN_FIRMEN = "LISTE_DER_VERKNUEPFTEN_FIRMEN";  
	
	
	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private KV_LIST_SqlFieldMAP oSQLFieldMapList = new KV_LIST_SqlFieldMAP();
	private KV_MASK_SQLFieldMAP oSQLFieldMapMask = new KV_MASK_SQLFieldMAP();

	private FS_ModulContainer_MASK    oCallingContainer = null;

    //hier den namen des ID-Feldes der eigenen Tabelle (Tochter), was die Verbindung zur Muttertabelle herstellt (1-zu-N - Beziehung)
	///public static final String CONNECTION_FIELD   =  
	

	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "KREDITVERSICHERUNG"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorBase();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;

	

	
	//die listenbuttons muessen exportiert werden koennen
	private KV_LIST_BT_NEW  oButtonNewInList = null; 
	private KV_LIST_BT_VIEW oButtonViewInList = null; 
	private KV_LIST_BT_EDIT oButtonEditInList = null; 
	private KV_LIST_BT_DELETE oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;


	// Selectionsvector global für die Liste
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private String idAdresse = null;
	
	

	public KV_HEAD_LIST_BasicModule_Inlay(FS_ModulContainer_MASK  CallingContainer) throws myException
	{
		super();
		
		this.oCallingContainer = CallingContainer;
		
		this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+KV_HEAD_LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new E2_NavigationList();
		
		
		oNaviList.INIT_WITH_ComponentMAP(new KV_HEAD_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		
		// hier den "Listenselektionsvector initialisieren
		this.oSelVector = new E2_SelectionComponentsVector(oNaviList);

		KV_HEAD_LIST_BedienPanel oPanel = new KV_HEAD_LIST_BedienPanel(oNaviList,new KV_HEAD_MASK_BasicModuleContainer());
		this.add(oNaviList, E2_INSETS.I_0_0_0_0);
		this.oNaviList.add_ComponentBevorNaviElements(oPanel);
		this.oNaviList.get_oSelectPageSize().set_ActiveValue("3");
		
		this.oNaviList.set_oColorBackgroundDaughter(new E2_ColorLight());
	}
		


	//beide fieldmaps werden nach einem restrict-Field durchsucht und dann mit dem wert gefuettert
	public void set_ID_From_Calling_Record(String cID_ADRESSE) throws myException
	{
		
		this.idAdresse = cID_ADRESSE;
		
		
		//listen-sql-fieldmap wird beschraenkt
		this.oSQLFieldMapList.clear_BEDINGUNG_STATIC();
		String cSQL_Where = "JT_KREDITVERS_KOPF.ID_KREDITVERS_KOPF IN (SELECT JT_KREDITVERS_ADRESSE.ID_KREDITVERS_KOPF" +
																		" FROM "+bibE2.cTO()+".JT_KREDITVERS_ADRESSE "+
																		" WHERE JT_KREDITVERS_ADRESSE.ID_ADRESSE="+cID_ADRESSE+")";
		this.oSQLFieldMapList.add_BEDINGUNG_STATIC(cSQL_Where);
		
		
		//2012-01-31: Sortierung am Start ist immer, der neueste Aktive vorne
		E2_ListSortHelper.reset_all_sorters(this.oNaviList);
		String cSortString = "JT_KREDITVERS_KOPF."+RECORD_KREDITVERS_KOPF.FIELD__AKTIV+" DESC, "+"JT_KREDITVERS_KOPF."+RECORD_KREDITVERS_KOPF.FIELD__ID_KREDITVERS_KOPF+" DESC";
		this.oSQLFieldMapList.clear_ORDER_SEGMENT();
		this.oSQLFieldMapList.add_ORDER_SEGMENT(cSortString);

	}

	public FS_ModulContainer_MASK get_oCallingAdresseContainer()
	{
		return oCallingContainer;
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
	



	private class KV_HEAD_LIST_BedienPanel extends MyE2_Column 
	{
		
		public KV_HEAD_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
			
			KV_HEAD_LIST_BasicModule_Inlay oThis = KV_HEAD_LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,0));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(0,0,3,0));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(0,0,10,0));

			RowLayoutData  oRowLayout_XXX_Wide = new RowLayoutData();
			oRowLayout_XXX_Wide.setBackground(KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout_XXX_Wide.setInsets(new Insets(0,0,30,0));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  						oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new KV_LIST_BT_NEW(oNaviList,oMaskContainer), 								oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new KV_LIST_BT_VIEW(oNaviList,oMaskContainer), 								oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new KV_LIST_BT_EDIT(oNaviList,oMaskContainer), 								oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new KV_LIST_BT_DELETE(oNaviList), 											oRowLayout);
			oRowForComponents.add(new KV_LIST_BT_LINK_TO_OTHER_ADRESSES(oNaviList,oMaskContainer), 	oRowLayout);
			
			
			// Selektor in den Bedienpanel einbauen, damit man die aktiven selektieren kann
			MyE2_CheckBox oCBNurAktive = new MyE2_CheckBox("Nur aktive Verträge",true,false);
			oSelVector.add(new E2_ListSelectorStandard(oCBNurAktive," NVL(JT_KREDITVERS_KOPF.AKTIV,'N')='Y'" ,""));

			oRowForComponents.add(oCBNurAktive,oRowLayout_XXX_Wide);
			
			
		}
	
	}
	
	
	public KV_LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public KV_LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public KV_LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public KV_LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}

	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	private class KV_LIST_BT_DELETE extends MyE2_Button
	{
	
		public KV_LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new actionAgentDeleteMessagebox(onavigationList));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+KV_HEAD_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
		}
		
		
		/**
		 * eigentlicher ActionAgent, wird aufgerufen von "actionAgentDeleteMessagebox"
		 * @author manfred
		 * @date   26.09.2011
		 */
		private class actionAgentDelete extends XX_ActionAgent{
			private E2_NavigationList m_navList = null;
			public actionAgentDelete(E2_NavigationList onavigationList) {
				m_navList = onavigationList;
				
			}
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (m_navList.get_vSelectedIDs_Unformated().size() == 1){
					// prüfen, ob es noch weiter Kunden für diesen Vertrag gibt.
					String idVertrag = m_navList.get_vSelectedIDs_Unformated().firstElement();
					RECLIST_KREDITVERS_ADRESSE rlKopf = new RECLIST_KREDITVERS_ADRESSE("ID_KREDITVERS_KOPF = " + idVertrag, "");
					//String sSql = null;
					String cID_ADRESSE = ((FS_ModulContainer_MASK)KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer).get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					Vector<String> vSql = new Vector<String>();
					
					if (rlKopf.size() > 1){
						// Wenn ja, einfach den n:m-Eintrag löschen
						vSql.add("DELETE FROM JT_KREDITVERS_ADRESSE WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() + " AND ID_KREDITVERS_KOPF = " + idVertrag
							   + " AND ID_ADRESSE = "  + cID_ADRESSE);
					} else {
						// sonst den Vertrag löschen und den Zuordnungssatz
						vSql.add("DELETE FROM JT_KREDITVERS_ADRESSE WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() + " AND ID_KREDITVERS_KOPF = " + idVertrag
 						     + " AND ID_ADRESSE = "  + cID_ADRESSE);
						
						vSql.add("DELETE FROM JT_KREDITVERS_KOPF WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() + " AND ID_KREDITVERS_KOPF = " + idVertrag );
					}
						
					MyE2_MessageVector v =  bibDB.ExecMultiSQLVector(vSql, true);
					bibMSG.add_MESSAGE(v);
					m_navList.RefreshList();
					
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss genau 1 Vertrag ausgwählt sein."));
				}
				
			}
			
		}
	
		
		private class actionAgentDeleteMessagebox extends XX_ActionAgent{
			private E2_NavigationList m_navList = null;
			public actionAgentDeleteMessagebox(E2_NavigationList onavigationList) {
				super();
				m_navList = onavigationList;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (bibMSG.get_bIsOK())
				{
					E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
														new MyE2_String("Löschen einer Kreditversicherung"),
														new MyE2_String("Löschen einer Kreditversicherung"),
														new MyE2_String(""),
														new MyE2_String("JA"),
														new MyE2_String("NEIN"),
														new Extent(300),
														new Extent(200)
														);
					oConfirm.set_oExtMINIMALWidth(new Extent(400));
					oConfirm.set_oExtMINIMALHeight(new Extent(200));
					
					oConfirm.set_ActionAgentForOK(new actionAgentDelete(m_navList));
					oConfirm.show_POPUP_BOX();
				}
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	private class KV_LIST_BT_EDIT extends MyE2_Button
	{
	
		public KV_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+KV_HEAD_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			}
		}
		
	
	}
	
	
	
	
	private class KV_LIST_BT_NEW extends MyE2_Button
	{
	
		public KV_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+KV_HEAD_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	private class KV_LIST_BT_VIEW extends MyE2_Button
	{
	
		public KV_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+KV_HEAD_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
	
	private class KV_LIST_BT_LINK_TO_OTHER_ADRESSES extends MyE2_Button
	{
	
		public KV_LIST_BT_LINK_TO_OTHER_ADRESSES(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("verbinden.png") , E2_ResourceIcon.get_RI("verbinden.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer ));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"VERKNUEPFUNG_" + KV_HEAD_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
			this.setToolTipText(new MyString("Zusätzliche Firmen zum Versicherungsvertrag zuordnen oder entfernen").CTrans());
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{

			private E2_NavigationList m_onavigationList = null;
			private E2_BasicModuleContainer_MASK m_omaskContainer = null;
			
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
			{
				m_onavigationList = onavigationList;
				m_omaskContainer = omaskContainer;
			}
			
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				String cID_ADRESSE = ((FS_ModulContainer_MASK)KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer).get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				new KV_Mask_Adresse_Versicherung(m_onavigationList, cID_ADRESSE);
			}
		}
	}
	
	
	
	
	private class KV_HEAD_LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public KV_HEAD_LIST_ComponentMap() throws myException
		{
			super(KV_HEAD_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(true, null),new MyE2_String("+"));

			
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("Vertrag Aktiv"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VERS_NR")), new MyE2_String("VersicherungsNr"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MELDEFRIST")), new MyE2_String("Meldefrist (Tage)"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FAKTURIERUNGSFRIST")), new MyE2_String("Fakturierungsfrist (Tage)"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG")), new MyE2_String("Bemerkung"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE_VERS")), new MyE2_String("ID Vers."));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VERSICHERUNGS_INFO")), new MyE2_String("Versicherung"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_KREDITVERS_KOPF")), new MyE2_String("ID"));
			
			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);
			

			
			this.get__Comp("AKTIV").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BEMERKUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_ADRESSE_VERS").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("MELDEFRIST").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("VERS_NR").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("VERSICHERUNGS_INFO").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			this.get__Comp("AKTIV").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BEMERKUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_ADRESSE_VERS").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("MELDEFRIST").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("VERS_NR").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("VERSICHERUNGS_INFO").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			this.get__Comp(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, KV_HEAD_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));
			oStyleSmallTextLightColor.setProperty(PROPERTY_FOREGROUND, Color.DARKGRAY);

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			this.get__Comp(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			this.get__Comp("AKTIV").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BEMERKUNG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_ADRESSE_VERS").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("MELDEFRIST").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("VERS_NR").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("VERSICHERUNGS_INFO").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			//weitere definitionen
			this.get__Comp(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp(KV_HEAD_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("EXTENDER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("AKTIV").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BEMERKUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_ADRESSE_VERS").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("MELDEFRIST").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("VERS_NR").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("VERSICHERUNGS_INFO").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("AKTIV").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BEMERKUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_ADRESSE_VERS").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("MELDEFRIST").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("VERS_NR").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("VERSICHERUNGS_INFO").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			this.get__Comp("AKTIV").EXT().set_oColExtent(new Extent(80));
			this.get__Comp("BEMERKUNG").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_ADRESSE_VERS").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("ID_KREDITVERS_KOPF").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("MELDEFRIST").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_oColExtent(new Extent(100));
			this.get__Comp("VERS_NR").EXT().set_oColExtent(new Extent(150));
			this.get__Comp("VERSICHERUNGS_INFO").EXT().set_oColExtent(new Extent(300));
			
			this.get__Comp("FAKTURIERUNGSFRIST").EXT().set_ToolTipStringForListHeader(new MyE2_String("Verlängerte Fakturierungsfrist in Tagen"));;
			

			
			
			this.set_oSubQueryAgent(new KV_LIST_FORMATING_Agent());
			
			
			this.set_List_EXPANDER_4_ComponentMAP(new KV_HEAD_ListExpander_SHOW_POS_LIST(KV_HEAD_LIST_BasicModule_Inlay.this.oNaviList, KV_HEAD_LIST_BasicModule_Inlay.this));
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				KV_HEAD_LIST_ComponentMap oCopy = new KV_HEAD_LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	private class KV_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
			// alle aktiven Versicherungen automatisch ausklappen
			if ( ((MyE2_DB_CheckBox)oMAP.get__Comp("AKTIV")).isSelected())
			{
				oMAP.set_OPEN_DaughterObjectForList_With_ToggleButton(true);
			}
		}
	}
	
	
	
	
	
	
	private class KV_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public KV_LIST_SqlFieldMAP() throws myException 
		{
			super("JT_KREDITVERS_KOPF", "" , false);
			
			this.add_JOIN_Table(	"JT_ADRESSE", 
					"JT_ADRESSE", 
					SQLFieldMAP.LEFT_OUTER, 
					":NAME1:NAME2:ORT:",
					true, 
					"JT_KREDITVERS_KOPF.ID_ADRESSE_VERS=JT_ADRESSE.ID_ADRESSE", 
					"A_", 
					null);

			this.add_SQLField(new SQLField("nvl(JT_ADRESSE.NAME1,'') || NVL2(JT_ADRESSE.NAME2, ' ' || JT_ADRESSE.NAME2,'') || NVL2(JT_ADRESSE.ORT, ' ,' || JT_ADRESSE.ORT,'')","VERSICHERUNGS_INFO", new MyE2_String("Versicherung"), bibE2.get_CurrSession()), true);
			
			this.get_("AKTIV").set_cDefaultValueFormated("Y");
			
			this.initFields();
		}
		
	}
	
	
		
	
	private class KV_HEAD_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
	{
	
		public KV_HEAD_MASK_BasicModuleContainer() throws myException
		{
			super(KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()+KV_HEAD_LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK);
						
			this.set_bVisible_Row_For_Messages(true);
			
			KV_MASK_ComponentMAP oKV_MASK_ComponentMAP = new KV_MASK_ComponentMAP();
			
			this.INIT(oKV_MASK_ComponentMAP, new KV_MASK(oKV_MASK_ComponentMAP), new Extent(900), new Extent(650));
		}
		
		
	}
	
	
	
	
	private class KV_MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		public KV_MASK_ComponentMAP() throws myException
		{
			super(KV_HEAD_LIST_BasicModule_Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("Vertrag ist aktiv"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG"),500,5), new MyE2_String("Bemerkung"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_KREDITVERS_KOPF")), new MyE2_String("ID"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("MELDEFRIST"),true,50,10,false), new MyE2_String("Meldefrist"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("FAKTURIERUNGSFRIST"),true,50,10,false), new MyE2_String("Verlängerte Fakturierungsfrist"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("VERS_NR"),true,200,100,false), new MyE2_String("VersicherungsNr"));
			
			
			this.add_Component(LISTE_DER_VERKNUEPFTEN_FIRMEN, new KV_GRID_VerknuepfteFirmen( 400 ), new MyE2_String("Verknüpfte Firmen:"));
			
			
			DB_SearchFieldAdresse oSearchAdresse = new DB_SearchFieldAdresse(oFM.get_("ID_ADRESSE_VERS"),true, 300,100);
			oSearchAdresse.set_bLabel4AnzeigeStattText4Anzeige(true);
			oSearchAdresse.get_oTextForAnzeige().setWidth(new Extent(400));
			oSearchAdresse.get_oTextForAnzeige().setFont(new E2_FontPlain(0));
			oSearchAdresse.get_oLabel4Anzeige().setWidth(new Extent(400));
			oSearchAdresse.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain(0));

			this.add_Component(oSearchAdresse, new MyE2_String("Versicherung"));
			 
			
			/*
			 * ermoeglicht formatierungen von zusatzinfos in der maske
			 */
			this.set_oSubQueryAgent(new KV_MASK_FORMATING_Agent());
			
			this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new mask_statement_builder_statement_to_connect_to_jt_kreditver_adresse());
			
			
			//2019-06-27: martin: einfuegen einer validierung, die verhindert, dass mehr als die letzte position ein offenes ende hat, wenn eine 
			//                    fakturierungsfrist vorliegt
			this.register_Interactiv_settings_validation(KREDITVERS_KOPF.id_kreditvers_kopf.fn(), new KV_MASK_KOPF_MapSetAndValidatorPositionsAblaufDatum(KV_HEAD_LIST_BasicModule_Inlay.this));
			
			
		}
		
		
		private class mask_statement_builder_statement_to_connect_to_jt_kreditver_adresse implements builder_AddOnSQL_STATEMENTS
		{
			
			@Override
			public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP, MyE2_MessageVector oMV) throws myException
			{
				return new Vector<String>();
			}
			
			@Override
			public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP, MyE2_MessageVector oMV) throws myException
			{
				String cID_ADRESSE = ((FS_ModulContainer_MASK)KV_HEAD_LIST_BasicModule_Inlay.this.oCallingContainer).get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				String cSQL = "INSERT INTO JT_KREDITVERS_ADRESSE (ID_KREDITVERS_ADRESSE,ID_ADRESSE,ID_KREDITVERS_KOPF) VALUES " +
						"(" +
						"SEQ_KREDITVERS_ADRESSE.NEXTVAL,"
						+cID_ADRESSE+","+
						"SEQ_KREDITVERS_KOPF.CURRVAL)";
				
				return bibALL.get_Vector(cSQL);
			}
			
		}
	}

	
	
	
	private class KV_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
			String idKreditversKopf = oUsedResultMAP.get_cUNFormatedROW_ID();
			( (KV_GRID_VerknuepfteFirmen) oMAP.get__Comp(LISTE_DER_VERKNUEPFTEN_FIRMEN)).refreshList(idKreditversKopf);
		}
	
	}
	
	
	
	
	
	
	
	private class KV_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public KV_MASK_SQLFieldMAP() throws myException 
		{
			super("JT_KREDITVERS_KOPF", "", false);
		
			/*
			 * beispiel fuer die definition von standard-werten
			 */
			this.get_("AKTIV").set_cDefaultValueFormated("Y");
			
			this.get_("VERS_NR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
	

			
			this.get_("AKTIV").set_cDefaultValueFormated("Y");
			
			this.initFields();
		}
	
	}
	
	
	private class KV_MASK extends MyE2_Column   implements IF_BaseComponent4Mask	
	{
		private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
		
		public KV_MASK(KV_MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			
			this.add(oGrid0, E2_INSETS.I_2_2_2_2);
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("ID:"), 1, "ID_KREDITVERS_KOPF|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Versicherung ist aktiv:"), 1, "AKTIV|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("VersicherungsNr:"), 1, "VERS_NR|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Meldefrist (Tage):"), 1, "MELDEFRIST|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Verlängerte Fakturierungsfrist (Tage):"), 1, "FAKTURIERUNGSFRIST|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Versicherungsgesellschaft:"), 1, "ID_ADRESSE_VERS|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Bemerkung:"), 1, "BEMERKUNG|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Vertrag zugeordnete Firmen:"), 1, "LISTE_DER_VERKNUEPFTEN_FIRMEN|#  |",3);
			
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


	
	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(KV_HEAD_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}


	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}



	public String getIdAdresse() {
		return idAdresse;
	}
	
	
}
