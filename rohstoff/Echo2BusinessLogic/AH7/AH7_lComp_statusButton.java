package rohstoff.Echo2BusinessLogic.AH7;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.RadioButtonAction;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class AH7_lComp_statusButton extends E2_Button implements MyE2IF_DB_SimpleComponent, DS_IF_components4decision{

	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();

	private Rec21  		ownRec = null;
	private IF_Field  	controlField = null;
	
	
	private RB_cb     			cb_statusUNDEF = 		new RB_cb()._t_add(S.mt("Undefininert/zurückgesetzt"))._setSomeThing(AH7__ENUM_STATUSRELATION.UNDEF);
	private RB_cb     			cb_statusINAKTIV = 		new RB_cb()._t_add(S.mt("Definiert, aber inaktiv"))._setSomeThing(AH7__ENUM_STATUSRELATION.INACTIVE);
	private RB_cb     			cb_statusAKTIV = 		new RB_cb()._t_add(S.mt("Aktiv, kann verwendet werden"))._setSomeThing(AH7__ENUM_STATUSRELATION.ACTIVE);
	
	private RadioButtonAction   rb_Action = 			new RadioButtonAction(false)._addCheckBox(cb_statusUNDEF)._addCheckBox(cb_statusINAKTIV)._addCheckBox(cb_statusAKTIV);
	
	private E2_Button     		btSendMessages4Activation = new E2_Button()._s_BorderText()._b()._backGreen()._tr("Aktivierung anfragen")._aaa(new actionSendMessages());
	
	private String  			id_relation =  null;
	
	//2021-04-26-martin
	private E2_NavigationList   naviList = null;
	private boolean  			buttonActive = false;
	private MyE2_String  		title = null;
	
	public AH7_lComp_statusButton(IF_Field  	p_controlField, E2_NavigationList navilist, boolean isActive, MyE2_String titel) throws myException{
		super();
		this._image(E2_ResourceIcon.get_RI("ampel-base.png"));
		if (isActive) {
			this._aaa(new ownActionAgent4Decision(this))._aaa(new ownToggleAction());
		}
		this.controlField = p_controlField;
		
		this.naviList= navilist;
		this.buttonActive = isActive;
		this.title = titel;
		this.EXT().set_oCompTitleInList(new SortComponent(naviList,this.title));

	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) 	throws myException {
		String dbValStatus = oResultMAP.get_UnFormatedValue(this.controlField.fn());
		this.id_relation = oResultMAP.get_cUNFormatedROW_ID();
		
		if (dbValStatus.equals(AH7__ENUM_STATUSRELATION.UNDEF.db_val())) {
			this._image(E2_ResourceIcon.get_RI("ampel-red.png"));
			this._ttt("Relation ist noch nicht bearbeitet und damit noch nicht benutzbar !");
			this.cb_statusUNDEF.setSelected(true);
			this.cb_statusINAKTIV.setSelected(false);
			this.cb_statusAKTIV.setSelected(false);
			
			this.cb_statusINAKTIV.set_bEnabled_For_Edit(false);
			this.cb_statusAKTIV.set_bEnabled_For_Edit(false);
		} else if (dbValStatus.equals(AH7__ENUM_STATUSRELATION.INACTIVE.db_val())) {

			this._image(E2_ResourceIcon.get_RI("ampel-yellow.png"));
			this._ttt("Relation ist definiert, muss aber noch aktiviert werden !");
			this.cb_statusUNDEF.setSelected(false);
			this.cb_statusINAKTIV.setSelected(true);
			this.cb_statusAKTIV.setSelected(false);
			
		} else if (dbValStatus.equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val())) {
			
			this._image(E2_ResourceIcon.get_RI("ampel-green.png"));
			this._ttt("Relation ist aktiv.");
			this.cb_statusUNDEF.setSelected(false);
			this.cb_statusINAKTIV.setSelected(false);
			this.cb_statusAKTIV.setSelected(true);
			
		}
		
		//20171127: nur geschaeftsfuehrer kann auf 
		if (ENUM_MANDANT_DECISION.AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF.is_YES() && !bibALL.is_geschaeftsfuehrer()) {
			this.cb_statusAKTIV.set_bEnabled_For_Edit(false);
		}

	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._image(E2_ResourceIcon.get_RI("ampel-base.png"));
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		AH7_lComp_statusButton bt;
		try {
			bt = new AH7_lComp_statusButton(this.controlField,this.naviList,this.buttonActive,this.title);
			bt.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(bt));
			return bt;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
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

	
	private Rec21 ownRec21() throws myException {
		if (this.ownRec==null) {
		   this.ownRec= new Rec21(_TAB.ah7_steuerdatei)._fill_id(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		}
		return this.ownRec;
	}
	
	
	/**
	 * decision-action
	 * @author martin
	 *
	 */
	private class ownActionAgent4Decision extends DS_ActionAgent {
		
		public ownActionAgent4Decision(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {

			//im modul profile wird der button nur zur anzeige benutzt
			if (AH7_lComp_statusButton.this.controlField._t()==_TAB.ah7_profil) {
				return false;
			}
			
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) throws myException {
			return new ownBasicModuleContainer(activeComponent);
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			AH7_lComp_statusButton oThis = AH7_lComp_statusButton.this;
			
			E2_Grid grid = new E2_Grid()._setSize(200,200);
			
			container.RESET_Content();
			container.add(grid, E2_INSETS.I(4,4,4,4));
			
			grid
				._a(new RB_lab()._tr("Bitte wählen Sie den neuen Status aus: ")._b(), 	new RB_gld()._col(new E2_ColorDark())._span(2)._ins(2, 2, 2, 2))
				._a(oThis.cb_statusUNDEF, 												new RB_gld()._span(2)._ins(2, 2, 2, 2))
				._a(oThis.cb_statusINAKTIV, 											new RB_gld()._span(2)._ins(2, 2, 2, 2))
				._a(oThis.cb_statusAKTIV, 												new RB_gld()._span(2)._ins(2, 2, 2, 2))
			
				._a(container.get_bt_OK(),new RB_gld()._col(new E2_ColorBase())._ins(2, 20, 40, 4))
				._a(container.get_bt_NO(),new RB_gld()._col(new E2_ColorBase())._ins(2, 20, 40, 4))
				;
			//20171127: nur geschaeftsfuehrer kann auf 
			if (ENUM_MANDANT_DECISION.AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF.is_YES() && !bibALL.is_geschaeftsfuehrer()) {
				grid._a(oThis.btSendMessages4Activation, new RB_gld()._ins(2,10,2,2)._span(2));
			}

			
			
		}
		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			return mv;
		}
	}

	
	private class ownToggleAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			AH7_lComp_statusButton 		oThis = 	AH7_lComp_statusButton.this;
			
			AH7__ENUM_STATUSRELATION  	statusOld = AH7__ENUM_STATUSRELATION.getStatus(oThis.ownRec21().get_ufs_dbVal(AH7_STEUERDATEI.status_relation));
			AH7__ENUM_STATUSRELATION  	statusNew = null;
			
			for (CheckBox oCB: oThis.rb_Action.getvCheckBoxes()) {
				if (oCB.isSelected()) {
					statusNew = (AH7__ENUM_STATUSRELATION)((RB_cb)oCB).getSomeThing();
					break;
				}
			}
			
			if (statusNew==null || statusOld==null) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: ein Status ist nicht definiert !"));
				return;
			}
			
			if (statusNew == statusOld) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Keine Änderung !"));
				return;
			}
			
			if (statusOld == AH7__ENUM_STATUSRELATION.UNDEF) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Bitte bearbeiten Sie den Datensatz, um ihn zu aktivieren !"));
				return;
			}
			
			if (statusNew == AH7__ENUM_STATUSRELATION.UNDEF) {
				//setzt den record in den status unbewertet
				Rec21 rec = new Rec21(AH7_lComp_statusButton.this.ownRec21());
				rec._nv(AH7_STEUERDATEI.id_1_abfallerzeuger, null, mv);
				rec._nv(AH7_STEUERDATEI.id_1_import_empfaenger, null, mv);
				rec._nv(AH7_STEUERDATEI.id_1_verbr_veranlasser, null, mv);
				rec._nv(AH7_STEUERDATEI.id_1_verwertungsanlage, null, mv);
				rec._nv(AH7_STEUERDATEI.drucke_blatt2, "N", mv);
				rec._nv(AH7_STEUERDATEI.id_2_abfallerzeuger, null, mv);
				rec._nv(AH7_STEUERDATEI.id_2_import_empfaenger, null, mv);
				rec._nv(AH7_STEUERDATEI.id_2_verbr_veranlasser, null, mv);
				rec._nv(AH7_STEUERDATEI.id_2_verwertungsanlage, null, mv);
				rec._nv(AH7_STEUERDATEI.drucke_blatt3, "N", mv);
				rec._nv(AH7_STEUERDATEI.id_3_abfallerzeuger, null, mv);
				rec._nv(AH7_STEUERDATEI.id_3_import_empfaenger, null, mv);
				rec._nv(AH7_STEUERDATEI.id_3_verbr_veranlasser, null, mv);
				rec._nv(AH7_STEUERDATEI.id_3_verwertungsanlage, null, mv);
				rec._nv(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv);
				if (mv.get_bIsOK()) {
					rec._SAVE(true, mv);
				}
				if (mv.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(mv);
				}
			}
			
			
			if (statusNew == AH7__ENUM_STATUSRELATION.INACTIVE) {
				//setzt den record in den status unbewertet
				Rec21 rec = new Rec21(AH7_lComp_statusButton.this.ownRec21());
				rec._nv(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.INACTIVE.db_val(), mv);
				if (mv.get_bIsOK()) {
					rec._SAVE(true, mv);
				}
				if (mv.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(mv);
				}
			}
			
			
			if (statusNew == AH7__ENUM_STATUSRELATION.ACTIVE) {
				//setzt den record in den status unbewertet
				Rec21 rec = new Rec21(AH7_lComp_statusButton.this.ownRec21());
				rec._nv(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.ACTIVE.db_val(), mv);
				if (mv.get_bIsOK()) {
					rec._SAVE(true, mv);
				}
				if (mv.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(mv);
				}
			}
			
			AH7_lComp_statusButton.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
			AH7_lComp_statusButton.this.ownRec21()._rebuildRecord();
		}
	}

	
	private class ownBasicModuleContainer extends DS_PopupContainer4Decision {

		public ownBasicModuleContainer(DS_IF_components4decision p_motherComponent) {
			super(p_motherComponent);
		}

		@Override
		public int get_width_in_pixel() {
			return 500;
		}

		@Override
		public int get_height_in_pixel() {
			return 300;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Bitte neuen Status wählen");
		}
	}

	
	
	
	private class actionSendMessages extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			bibMSG.add_MESSAGE(ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.generateMessages(MODUL.AH7_STEUERDATEI_LISTE
																					, AH7_lComp_statusButton.this.id_relation
																					, "Bitte um Freigabe der AH-7-Druck-Steuer-Relation"
																					, "AH7-Druck-Steuertabelle"));
			
		}
		
	}
	
	
	
	
	private class SortComponent extends E2_Grid {

		/**
		 * @author martin
		 * @date 23.04.2021
		 *
		 */
		public SortComponent(E2_NavigationList naviList, MyE2_String title) {
			super();
			String sortBase = "(CASE NVL(STATUS_RELATION,'-') WHEN n'ACTIVE' THEN 1 WHEN n'INACTIVE'  THEN 2 WHEN n'UNDEF'  THEN 3  ELSE 4 END)";
			this._setSize(40)._a(new OwnSorter(naviList, sortBase,title));
		}
		
	}
	
	
	//einbau sortierung nach WE/WA
	private class OwnSorter extends E2_ButtonListSorterNG {
		private E2_NavigationList navilist  = null;


		public OwnSorter(E2_NavigationList naviList, String sortBase, MyE2_String text) {
			super();
			this.navilist = naviList;
			this._setSortTermUp(sortBase+" ASC")._setSortTermDown(sortBase+" DESC");
			this._setButtonText(text);
			
		}


		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return this.navilist;
		}
		
	}
	
}
