package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.RadioButtonAction;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.AH7_ENUM_SONDERPROFILE;
import rohstoff.Echo2BusinessLogic.AH7.Reclist21_AH7P_GlobalProfiles;
import rohstoff.Echo2BusinessLogic.AH7.Reclist21_AH7P_SpecifiedProfiles;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceAH7Profiler;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;


public class AH7_bt_ProfileAusListenEinordnen extends E2_Button implements DS_IF_components4decision{
	
	private E2_NavigationList naviList=null;

	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();

	
	private Select_AH7_ProfilVarianten  	selectAH7ProfilVarianten = new Select_AH7_ProfilVarianten();
	//private Rec20 							actualMatchProfil = null;
	
	private RB_cb cbChecked = 	null;
	private RB_cb cbSite = 		null;
	private RB_cb cbSelektor = 	null;

	private VEK<RB_cb> vCBs = new VEK<RB_cb>();
	
	
	private VEK<Rec21>   vRecProfilesSpecified = new VEK<Rec21>();    	//hier landen die dedizierten profile
	private VEK<Rec21>   vRecProfilesSonder = new VEK<Rec21>(); 		//hier landen die Sonderprofile (die danach angewendet werden)
	
	
	private VEK<Rec21_AH7_Steuerdatei>  v_recsToQualify = new VEK<Rec21_AH7_Steuerdatei>();   //beim aufruf get_vIds_to_classify() wird dieser vektor mit records gefuellt
	
	
	/**
	 * aufbau der moeglichen relationen innerhalb der tabelle 
	 * @throws myException 
	 */
	public AH7_bt_ProfileAusListenEinordnen(E2_NavigationList p_naviList) throws myException {
		super();
		this.naviList = p_naviList;
		this._tr("AH7-Profile auf AH7-Druck-Steuerdatensätze anwenden")._width(200)._lw();
		if (ENUM_MANDANT_DECISION.AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF.is_YES()) {
			this._s_BorderText()._ttt("Die Eintrage der AH7-Profile auf die AH7-Druck-Steuerdatensätze anwenden (ACHTUNG! Nur wenn ein Benutzer im Status Geschäftsführer diese Bewertung vornimmt, wird aktiviert)");
		} else {
			this._s_BorderText()._ttt("Die Eintrage der AH7-Profile auf die AH7-Druck-Steuerdatensätze anwenden");
		}
		this._aaa(new ownActionAgent4Decision(this));
		this._aaa(new ownActionAgentReal());
		this.add_GlobalValidator(ENUM_VALIDATION.AH7_STEUER_AUTOMATIC_SETTING.getValidatorWithoutSupervisorPersilschein());
	}

	
	private Vector<String> get_vIds_to_classify() {
		Vector<String> v = new VEK<String>();
		
		for (RB_cb cb: this.vCBs) {
			if (cb.isSelected()) {
				v.clear();
				v.addAll((Vector<String>)cb.getSomeThing());
				break;
			}
		}
		return v;
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

	
	
	
	private class ownActionAgent4Decision extends DS_ActionAgent {
		
		public ownActionAgent4Decision(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) throws myException {
			return new ownBasicModuleContainer(activeComponent);
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			
			AH7_bt_ProfileAusListenEinordnen oThis = AH7_bt_ProfileAusListenEinordnen.this;
			
			E2_Grid grid = new E2_Grid()._setSize(200,200);
			
			container.RESET_Content();
			container.add(grid, E2_INSETS.I(4,4,4,4));
			
			Vector<String> vChecked = 		oThis.naviList.get_vSelectedIDs_Unformated();
			Vector<String> vSite = 			oThis.naviList.get_vActualID_Segment();
			Vector<String> vSelektor =  	oThis.naviList.get_vectorSegmentation();
			
			RadioButtonAction radioAction = new RadioButtonAction(false) ;
			
			oThis.cbChecked = 	new RB_cb()._t(S.ms("In der Liste angehakte Relationen: ").ut(		""+vChecked.size())	)._setSomeThing(vChecked);
			oThis.cbSite = 		new RB_cb()._t(S.ms("Relationen auf dieser Seite: ").ut(			""+vSite.size())	)._setSomeThing(vSite);;
			oThis.cbSelektor = 	new RB_cb()._t(S.ms("Alle Relationen in der momentanen Liste: ").ut(	""+vSelektor.size()))._setSomeThing(vSelektor);;
			radioAction._addCheckBox(oThis.cbChecked)._addCheckBox(oThis.cbSelektor)._addCheckBox(oThis.cbSite);
			
			oThis.vCBs._clear()._a(oThis.cbChecked,oThis.cbSite,oThis.cbSelektor);
			
			if (vChecked.size()>0) {
				oThis.cbChecked.setSelected(true);
			} else {
				oThis.cbSelektor.setSelected(true);
			}
			
			grid._a(new RB_lab()._tr("Bitte wählen Sie den Bereich, der bewertet werden soll:")._b(), 	new RB_gld()._ins(2,5,2,5)._span(2));
			grid._a(oThis.cbChecked, 	new RB_gld()._ins(2,5,2,5)._span(2));
			grid._a(oThis.cbSite, 		new RB_gld()._ins(2,5,2,5)._span(2));
			grid._a(oThis.cbSelektor, 	new RB_gld()._ins(2,5,2,5)._span(2));
			
			grid._a(new RB_lab()._tr("Verfügbare Profile:")._b(), 	new RB_gld()._ins(2,20,2,5)._span(2));
			grid._a(oThis.selectAH7ProfilVarianten,new RB_gld()._col(new E2_ColorBase())._span(2)._ins(2, 10, 2, 4))
				._a(container.get_bt_OK(),new RB_gld()._col(new E2_ColorBase())._ins(2, 20, 40, 4))
				._a(container.get_bt_NO(),new RB_gld()._col(new E2_ColorBase())._ins(2, 20, 40, 4))
				;
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return new MyE2_MessageVector();
		}
	}

	
	private class ownActionAgentReal extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			AH7_bt_ProfileAusListenEinordnen oThis = AH7_bt_ProfileAusListenEinordnen.this;

			oThis.vRecProfilesSpecified.clear();
			oThis.vRecProfilesSonder.clear();
		
			if (S.isFull(AH7_bt_ProfileAusListenEinordnen.this.selectAH7ProfilVarianten.getActualDbVal())) {
				Rec21 r_ah7p = new Rec21(_TAB.ah7_profil)._fill_id(new MyLong(AH7_bt_ProfileAusListenEinordnen.this.selectAH7ProfilVarianten.getActualDbVal()).get_lValue());
				oThis.vRecProfilesSpecified._a(r_ah7p);
			} else {
				//hier alle ProfilVarianten durch-iterieren
				Reclist21_AH7P_SpecifiedProfiles 	rlSpec = 	new Reclist21_AH7P_SpecifiedProfiles();
				Reclist21_AH7P_GlobalProfiles 		rlSonder = 	new Reclist21_AH7P_GlobalProfiles();
				
				for (Rec21 r: rlSpec) {
					oThis.vRecProfilesSpecified._a(r);
				}
				for (Rec21 r: rlSonder) {
					oThis.vRecProfilesSonder._a(r);
				}
			}
			
			//jetzt nachsehen, welche Vector mit ids verwendet werden soll und den VEK mit records fuellen
			oThis.v_recsToQualify.clear();
			for (String id: oThis.get_vIds_to_classify()) {
				oThis.v_recsToQualify._a(new Rec21_AH7_Steuerdatei()._fill_id(id));
			}
			
			
			
			//wenn mehr als 500 pruefsaetze ausgewaehlt sind, dann fortschrittsanzeige
			if (oThis.get_vIds_to_classify().size()>500) {
				new E2_ServerPushMessageContainer(new Extent(800),new Extent(200),new MyE2_String("Prüfung der Relationen läuft ..."),true,false,true,2000) {
					@Override
					public void Run_Loop() throws myException {
						AH7_bt_ProfileAusListenEinordnen oThis = AH7_bt_ProfileAusListenEinordnen.this;
						oThis.runProfiling(this,oThis.vRecProfilesSpecified,oThis.vRecProfilesSonder);
					}
					@Override
					public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
					};
				};
			} else {
				oThis.runProfiling(null,oThis.vRecProfilesSpecified,oThis.vRecProfilesSonder);
			}
		}
	}
	
	
	/**
	 * 
	 * @param loopPopup = 	E2_ServerPushMessageContainer can be null
	 * @param rlSpec =    	Vector mit den Rec20 der speziellen Varianten
	 * @param rlBasic = 	Vector mit den Rec20 der basic-varianten
	 * @throws myException 
	 */
	private void runProfiling(E2_ServerPushMessageContainer loopPopup, VEK<Rec21> vSpec,  VEK<Rec21> vSonder) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		new ownProfiler(loopPopup, vSpec, vSonder, mv);
		bibMSG.add_MESSAGE(mv);
		Vector<String> vIds = AH7_bt_ProfileAusListenEinordnen.this.naviList.get_vSelectedIDs_Unformated();
		AH7_bt_ProfileAusListenEinordnen.this.naviList._REBUILD_COMPLETE_LIST("");
		if (vIds.size()>0) {
			AH7_bt_ProfileAusListenEinordnen.this.naviList.gotoSiteWithID_orFirstSite(vIds.get(0));
			for (String id: vIds) {
				AH7_bt_ProfileAusListenEinordnen.this.naviList.Check_ID_IF_IN_Page(id);
			}
		}
	}
	
	
	
	
	
	
	private class ownBasicModuleContainer extends DS_PopupContainer4Decision {

		public ownBasicModuleContainer(DS_IF_components4decision p_motherComponent) {
			super(p_motherComponent);
		}

		@Override
		public int get_width_in_pixel() {
			return 600;
		}

		@Override
		public int get_height_in_pixel() {
			return 350;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Relation mit einem oder allen Profilen abgleichen");
		}
	}
	
	
	
	private class Select_AH7_ProfilVarianten extends RB_selField {

		public Select_AH7_ProfilVarianten() throws myException {
			super();
			
			//hier alle anbieten, ausser den standardprofilen
			SEL  sel = new SEL(_TAB.ah7_profil).FROM(_TAB.ah7_profil).WHERE(new vgl(AH7_PROFIL.profile4allothers,AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val())).ORDERUP(AH7_PROFIL.bezeichnung);
			RecList21 rl = new RecList21(_TAB.ah7_profil)._fill(sel.s());
			
			this._addPair(Pair.P("<Alle Profile anwenden>", ""));
			for (Rec21 r: rl) {
				this._addPair(Pair.P(r.get_fs_dbVal(AH7_PROFIL.bezeichnung), r.get_fs_dbVal(AH7_PROFIL.id_ah7_profil)));
			}
			this.setSelectedIndex(0);
		}
		
	}
	
	
	
	private class ownProfiler extends _PdServiceAH7Profiler {

		/**
		 * @param loopPopup
		 * @param vSpec
		 * @param vSonder
		 * @param mv
		 * @throws myException
		 */
		public ownProfiler(E2_ServerPushMessageContainer loopPopup, VEK<Rec21> vSpec, VEK<Rec21> vSonder,MyE2_MessageVector mv) throws myException {
			super(loopPopup, vSpec, vSonder, mv, ! (bibALL.is_geschaeftsfuehrer() || ENUM_MANDANT_DECISION.AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF.is_NO()));
		}

		@Override
		public Vector<Rec21_AH7_Steuerdatei> get_vIds__AH_7_steuerdatei_to_classify() throws myException {
			return AH7_bt_ProfileAusListenEinordnen.this.v_recsToQualify;
		}
		
	}
	
}
