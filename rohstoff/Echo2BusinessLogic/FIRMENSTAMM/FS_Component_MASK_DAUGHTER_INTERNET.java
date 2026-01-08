package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.BasicTools.m2n.ENUM_M2N_CONTEXT;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainerWith_HTTP_PANE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.IF_DBC_DaughterTable_do_something_before_filling;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_ENUMS.INTERNET;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.enumtools.IF_enum_4_fielddefinition;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class FS_Component_MASK_DAUGHTER_INTERNET extends MyE2_DBC_DaughterTable implements IF_DBC_DaughterTable_do_something_before_filling {

	public enum MY_FIELDS implements 	IF_enum_4_fielddefinition {
		INDEX2(INTERNET.id_internet.tnfn(),"Zusatzfeld")
		;

		private String fieldDef = 	null; 
		private String userText =	null;
		private MY_FIELDS(String p_fieldDef, String p_userText) {
			this.fieldDef = p_fieldDef;
			this.userText = p_userText;
		}
		
		@Override
		public String alias() { return this.name();	}

		@Override
		public String querydef() {	return this.fieldDef;	}

		@Override
		public String user_text_lang() {return S.isEmpty(this.userText)?this.name():this.userText;
		}

		@Override
		public IF_enum_4_fielddefinition[] get_all_defs() {
			return FZ__CONST.FIELDS.values();
		}
		
	}
	
	
	/**
	 * checkbox fuer die radiobutton-funktion der standard-web-adresse
	 */
	private VEK<ownCheckBoxSetStandard>  v_cbs = new VEK<>();
	
	public FS_Component_MASK_DAUGHTER_INTERNET(		SQLFieldMAP 			fieldMAPMotherTable, 
													E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException {
		super();
		
		SQLFieldMAP oSQLFieldMapINTERNET = new SQLFieldMAP("JT_INTERNET",bibE2.get_CurrSession());
		oSQLFieldMapINTERNET.addCompleteTable_FIELDLIST("JT_INTERNET",":ID_INTERNET:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapINTERNET.add_SQLField(new SQLFieldForPrimaryKey("JT_INTERNET","ID_INTERNET","ID_INTERNET",new MyE2_String("ID-INTERNET"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_INTERNET.NEXTVAL FROM DUAL",true), false);

		//neues proforma feld fuer die usersettings
		oSQLFieldMapINTERNET.add_SQLField(new SQLField(MY_FIELDS.INDEX2),true);
		
		
		oSQLFieldMapINTERNET.add_SQLField(new SQLFieldJoinOutside("JT_INTERNET","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMapINTERNET.get_("INTERNET").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oSQLFieldMapINTERNET.initFields();
		
		
		E2_ComponentMAP 			oMapInternet = new E2_ComponentMAP(oSQLFieldMapINTERNET);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_WWWAdresse = 		new MyE2_DB_TextField(oSQLFieldMapINTERNET.get_SQLField("INTERNET"),true,300,0,false);
		MyE2_DB_TextField			oTF_Beschreibung = 		new MyE2_DB_TextField(oSQLFieldMapINTERNET.get_SQLField("BESCHREIBUNG"),true,300,0,false);
		ownCheckBoxSetStandard		oCB_STD = 				new ownCheckBoxSetStandard(oSQLFieldMapINTERNET.get_SQLField("IST_STANDARD"));
		ownCheckBoxUserPreference   oCB4User = 				new ownCheckBoxUserPreference(oSQLFieldMapINTERNET.get_SQLField(MY_FIELDS.INDEX2.name()));
		
		oTF_WWWAdresse.EXT().set_oColExtent(new Extent(300));
		oTF_Beschreibung.EXT().set_oColExtent(new Extent(300));
		
		oTF_WWWAdresse.EXT_DB().set_bIsSortable(false);
		oTF_Beschreibung.EXT_DB().set_bIsSortable(false);
		oCB_STD.EXT_DB().set_bIsSortable(false);
		
		oMapInternet.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapInternet.add_Component(oTF_WWWAdresse,new MyE2_String("Internet-Adresse"));
		oMapInternet.add_Component(oTF_Beschreibung,new MyE2_String("Beschreibung"));
		oMapInternet.add_Component(new MyE2_DB_CheckBox(oSQLFieldMapINTERNET.get_("OPEN_OWN_BROWSER")),new MyE2_String("Neues Fenster ?"));
		oMapInternet.add_Component("INTERNET_ANSICHT",new ButtonOpenURL(),new MyE2_String("WEB"));
		oMapInternet.add_Component(oCB_STD,new MyE2_String("Std.?"));
		oMapInternet.add_Component(oCB4User,new MyE2_String("Benutzer?"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(130));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));

		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapInternet,
							null);
		
	}

	
	private class ButtonOpenURL extends MyE2_ButtonInLIST
	{
		public ButtonOpenURL()
		{
			super(E2_ResourceIcon.get_RI("world.png"));
			this.add_oActionAgent(new actionOpenWeb());
		}
		
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return new ButtonOpenURL();
		}
	}
	
	
	private class actionOpenWeb extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			ButtonOpenURL oButton = (ButtonOpenURL)execInfo.get_MyActionEvent().getSource();
			
			//url feststellen
			E2_ComponentMAP oMap = oButton.EXT().get_oComponentMAP();
			
			String cInternet = bibALL.null2leer(oMap.get_cActualDBValueFormated("INTERNET"));
			boolean bOwnBrowserWindow = ((MyE2_DB_CheckBox)oMap.get__Comp("OPEN_OWN_BROWSER")).isSelected();
			
			
			if (!bibALL.isEmpty(cInternet))
			{
				if (!((cInternet.toLowerCase().startsWith("http://") || cInternet.toLowerCase().startsWith("https://"))))
				{
					cInternet = "http://"+cInternet;
				}
				
				if (bOwnBrowserWindow)
				{
					ApplicationInstance.getActive().enqueueCommand(
						     new BrowserOpenWindowCommand(cInternet,"Infofenster","width=800,height=600"));

				}
				else
				{
					ownBasicContainer4Internet oPopUp = new ownBasicContainer4Internet(800,600);
					oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Internetansicht ..."));
					oPopUp.showWebsite(cInternet);
				}
			}
		}
	}
	
	

	// eigene klasse, damit das abspeichern der benutzerdefinierten groesse geht
	private class ownBasicContainer4Internet extends E2_BasicModuleContainerWith_HTTP_PANE
	{
		public ownBasicContainer4Internet(int width, int height)
		{
			super(width, height);
		}
	}
	
	
	/**
	 * checkbox fuer die standard-setzung (radio-checkbox)
	 * @author martin
	 *
	 */
	private class ownCheckBoxSetStandard extends MyE2_DB_CheckBox {
		/**
		 * @param osqlField
		 * @throws myException
		 */
		public ownCheckBoxSetStandard(SQLField osqlField) throws myException {
			super(osqlField);
			FS_Component_MASK_DAUGHTER_INTERNET.this.v_cbs.add(this);
			this.add_oActionAgent(new ownAction());
		}
		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			try {
				return new ownCheckBoxSetStandard(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.getLocalizedMessage());
			}
		}
		
		/**
		 * sorgt fuer radiobutton-effekt
		 * @author martin
		 *
		 */
		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownCheckBoxSetStandard oThis = ownCheckBoxSetStandard.this;
				if (oThis.isSelected()) {
					for (ownCheckBoxSetStandard cb: FS_Component_MASK_DAUGHTER_INTERNET.this.v_cbs) {
						if (cb != oThis) {
							cb.setSelected(false);
						}
					}
				}
			}
		}
	}


	/**
	 * no-db-klasse, die die jeweilige benutzervorliebe definiert, benutzt die ENUM_M2N_CONTEXT
	 * @author martin
	 *
	 */
	private class ownCheckBoxUserPreference extends MyE2_DB_CheckBox implements DS_IF_components4decision, MyE2IF_IsMarkable {

		private String id_user = 		bibALL.get_ID_USER();
		private String id_internet = 	null;
		
		public ownCheckBoxUserPreference(SQLField field) throws myException {
			super(field);
			this.EXT_DB().set_bGivesBackValueToDB(false);
			this.add_oActionAgent(new ownAction4Decision());
			this.add_oActionAgent(new ownActionSaveUserSetting());
			this.setToolTipText(new MyE2_String("Diese Internetseite zu den Favoriten von <",true,bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn()),false,"> zufügen/entfernen",true).CTrans());
		}

		@Override
		public void prepare_ContentForNew(boolean bSetDefault) throws myException	{
			this.setSelected(false);
		}

		public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException 		{
			this.id_internet = oResultMAP.get_cUNFormatedROW_ID();
			this.setSelected(ENUM_M2N_CONTEXT.USER_WEBSEITE.checkValue(this.id_user, this.id_internet));
		}

		private class ownAction4Decision extends DS_ActionAgent {
			public ownAction4Decision() {
				super(ownCheckBoxUserPreference.this);
			}

			@Override
			public Boolean make_decision_when_true_then_popup() throws myException {
				boolean popup_must_be = S.isEmpty(ownCheckBoxUserPreference.this.id_internet);
				if (popup_must_be) {
					//wenn diese maske kommt, dann muss die checkbox wieder aus sein
					ownCheckBoxUserPreference.this.setSelected(false);
				}
				return popup_must_be;
			}

			@Override
			public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)	throws myException {
				return new own_popup();
			}
			private class own_popup extends DS_PopupContainer4Decision {
				public own_popup() {
					super(ownCheckBoxUserPreference.this);
				}
				@Override
				public int get_width_in_pixel() { return 300; }
				@Override
				public int get_height_in_pixel() { return 200; }
				@Override
				public MyE2_String get_titleText4PopUp() {	return new MyE2_String("Die Adressmaske muss vorher gespeichert werden!"); }
			}

			@Override
			public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
				E2_Grid g = new E2_Grid()._setSize(150,150)	._a(new RB_lab()._tr("Soll die Maske gespeichert werden ?"), new RB_gld()._span(2)._ins(5,10,5,20))
															._a(container.get_bt_OK(),new RB_gld()._ins(5))
															._a(container.get_bt_NO(),new RB_gld()._ins(5));
				container.RESET_Content();
				container.add(g);
			}

			@Override
			public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				MyE2_MessageVector mv_save = new MyE2_MessageVector();
				mv_save.addAll(bibMSG.MV());
				bibMSG.MV().clear();
			
				//hier zuerst die mask der adresse speichern ansonsten fehlermeldung zurueck
				E2_BasicModuleContainer_MASK  maskContainer = FS_Component_MASK_DAUGHTER_INTERNET.this.get_oComponentMAP_From_Mask().get_oModulContainerMASK_This_BelongsTo();
				try	{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(maskContainer,	maskContainer.get_oNavigationListWhichBelongsToTheMask());
					try	{
						oSaveMask.doSaveMask(true);
						if (bibMSG.get_bIsOK())	{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Adresse wurde gespeichert: "+oSaveMask.get_cActualMaskID_Unformated()), false);
						}
					} catch (myExceptionForUser exx) {
						exx.printStackTrace();
						bibMSG.add_MESSAGE(exx.get_ErrorMessage(), false);
					}
				} catch (myException ex) {
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
				mv.add_MESSAGE(bibMSG.MV());
				bibMSG.MV().clear();
				bibMSG.MV().addAll(mv_save);
				
				return mv;
			}
		}
		
		private class ownActionSaveUserSetting extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownCheckBoxUserPreference oThis = ownCheckBoxUserPreference.this;
				
				if (S.isFull(oThis.id_internet)) {         //falls id_internet leer ist, dann greift vorher die ownAction4Decision ein  
					MyE2_MessageVector  mv = new MyE2_MessageVector();
					if (oThis.isSelected()) {
						//neuerfassungsmaske oder neue zeile 
						if (!ENUM_M2N_CONTEXT.USER_WEBSEITE.checkValue(oThis.id_user, oThis.id_internet)) {
							mv._add(ENUM_M2N_CONTEXT.USER_WEBSEITE.insert(oThis.id_user, oThis.id_internet));
							if (mv.get_bIsOK()) {
								mv.add_MESSAGE(new MyE2_Info_Message(
										new MyE2_String("Webseite wurde zu den Favoriten des Benutzers <",true,bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn()),false, "> hinzugefügt!",true)));
							} else {
								mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim hinzufügen der Webseite zu den Benutzer-Favoriten ")),true);
								oThis.setSelected(false);
							}
						} else {
							mv.add_MESSAGE(new MyE2_Alarm_Message(
									new MyE2_String("Die Webseite war bereits ein Favorit des Benutzers ",true,bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn()),false)));
						}
					} else {
						mv._add(ENUM_M2N_CONTEXT.USER_WEBSEITE.delete(oThis.id_user, oThis.id_internet,true));
						if (mv.get_bIsOK()) {
							mv.add_MESSAGE(new MyE2_Info_Message(
									new MyE2_String("Webseite aus den Favoriten des Benutzers <",true,bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn()),false, "> entfernt!",true)));
						} else {
							mv.add_MESSAGE(new MyE2_Info_Message(
									new MyE2_String("Fehler! Die Webseite konnte nicht aus den Favoriten des Benutzers <",true,bibALL.get_RECORD_USER().get___KETTE(USER.vorname.fn(),USER.name1.fn()),false, "> entfernt werden!",true)
									),true);
						}
					}
					bibMSG.add_MESSAGE(mv);
				} else {
					oThis.setSelected(false);
				}
			}
		}

		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			try {
				return new ownCheckBoxUserPreference(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.getLocalizedMessage());
			}
		}

		public void set_bEnabled_For_Edit(boolean enabled) throws myException 	{}  //getoetet

		private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
		private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
		private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
		private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
		
		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
			return this.storage_vector_4_all_agents;
		}
	
		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
			return this.storage_vector_4_standard_agents;
		}
	
		@Override
		public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
			return this.storage_vector_4_decision_agents;
		}
	
		@Override
		public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
			return this.hm_descision_containers;
		}

		@Override
		public void make_Look_Deleted(boolean bIsDeleted) {
			this.setEnabled(!bIsDeleted);
			this.set_Icons(this.isEnabled());
		}

		@Override
		public void setForeColorActive(Color ForeColor) {
		}

		@Override
		public void setFontActive(Font Font) {
		}

		@Override
		public Color get_Unmarked_ForeColor() {
			return null;
		}

		@Override
		public Font get_Unmarked_Font() {
			return null;
		}

		
	}
	
	

	@Override
	public void prepare4Filling(MyE2_DBC_DaughterTable daughter) throws myException {
		FS_Component_MASK_DAUGHTER_INTERNET.this.v_cbs.clear();
	}



	@Override
	public void prepare4NewMask(MyE2_DBC_DaughterTable daughter) throws myException {
		FS_Component_MASK_DAUGHTER_INTERNET.this.v_cbs.clear();
	}
	
	
}
