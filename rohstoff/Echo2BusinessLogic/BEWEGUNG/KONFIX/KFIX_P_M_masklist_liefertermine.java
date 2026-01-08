package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughter;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen_Rec20;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_delete_single;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_bt_to_open_simple_mask;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.TOOLS.RB_grid4masks;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TERM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class KFIX_P_M_masklist_liefertermine extends RB_MaskDaughter {

	private String id_vpos_kon_actual = null;
	private RB_gld ld_l_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._left_top();
	private RB_gld ld_m_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._center_top();
	private RB_gld ld_r_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._right_top();
	private RB_gld ld_l_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._left_top();
	private RB_gld ld_m_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._center_top();
	private RB_gld ld_r_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._right_top();

	/**
	 * 
	 */
	public KFIX_P_M_masklist_liefertermine() {
		super();
		this._set_width_of_mask_inlay_container_ex(new Extent(800));
		this._set_height_of_mask_inlay_container_ex(new Extent(200));
	}

	@Override
	public KFIX_P_M_masklist_liefertermine _mark_components_in_innergrid_neutral() throws myException {
		return this;
	}

	@Override
	public KFIX_P_M_masklist_liefertermine _mark_components_in_innergrid_disabled() throws myException {
		return this;
	}

	@Override
	public KFIX_P_M_masklist_liefertermine _set_components_in_innergrid_enabled_for_edit(boolean b_enabled_4_edit) throws myException {
		return this;
	}

	@Override
	public KFIX_P_M_masklist_liefertermine _rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (((KFIX_P_M__DataObject)dataObject).rb_MASK_STATUS().isStatusNew()) {
			this._rb_set_db_value_manual("");
		} else {
			this._rb_set_db_value_manual(((KFIX_P_M__DataObject)dataObject).get_rec20().get_ufs_dbVal(VPOS_KON.id_vpos_kon));
		}
		return this;
	}

	@Override
	public KFIX_P_M_masklist_liefertermine _rb_set_db_value_manual(String id_vpos_kon) throws myException {
		
		this.id_vpos_kon_actual = id_vpos_kon;
		
		
		VEK<E2_Button>  v_sammle = new VEK<>();  //damit im falle des status: view die buttons auf disabled gestellt werden koennen 
		
		//ueberschrift
		this.get_inner_grid_4_data()._clear();


		if (S.isEmpty(id_vpos_kon)) {
			E2_Button  bt_save_mask_first = new E2_Button()._tr("Kontraktposition speichern, um Liefertermine erfassen zu können ")._s_BorderText()._fsa(2)._center()._i();
			bt_save_mask_first.add_oActionAgent(new ownActionSaveAndReopen_vpos_kon());
			
			this.set_title(null);
			this.get_inner_grid_4_data()._a(bt_save_mask_first, new RB_gld()._span(7));

		} else {
			
			MyLong l_id_vkopf_kon = new MyLong(id_vpos_kon);
			if (!l_id_vkopf_kon.isOK()) {
				this.set_title(null);
				this.get_inner_grid_4_data()._clear();
				this.get_inner_grid_4_data()._a(new RB_lab("<Fehler beim Aufbau der Liste>"), ld_m_d._c()._span(7));
			} else {

				this.set_title(v_sammle._ar(new own_bt_edit_termine(MASK_STATUS.NEW,"")));

				RecList20 reclist_term = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon).get_down_reclist20(VPOS_KON_TERM.id_vpos_kon, "", VPOS_KON_TERM.id_vpos_kon.fn());
				
				for(Rec20 rec_lager_term : reclist_term){
					this.get_inner_grid_4_data()
							._a(v_sammle._ar(new own_bt_edit_termine(MASK_STATUS.EDIT,rec_lager_term.get_ufs_dbVal(VPOS_KON_TERM.id_vpos_kon_term))),  	ld_l_d)
							._a(v_sammle._ar(new ownDeleteButton(rec_lager_term.get_ufs_dbVal(VPOS_KON_TERM.id_vpos_kon_term))),   						ld_l_d)
							._a(new RB_lab(rec_lager_term.get_fs_dbVal(VPOS_KON_TERM.lieferdatum, "")),													ld_m_d)
							._a(new RB_lab(rec_lager_term.get_fs_dbVal(VPOS_KON_TERM.lieferdatum_bis, "")),												ld_m_d)
							._a(new RB_lab(rec_lager_term.get_fs_dbVal(VPOS_KON_TERM.anzahl, "")),														ld_r_d)
							._a(new RB_lab(rec_lager_term.get_fs_dbVal(VPOS_KON_TERM.bemerkung, "")),													ld_l_d)
							._a(new RB_lab(rec_lager_term.get_fs_dbVal(VPOS_KON_TERM.id_vpos_kon_term, "")),											ld_r_d)
							;
				}
			}
		}
		
		//jetzt, falls nur status view, alle buttons disabled
		MASK_STATUS  status_vpos_kon_mask = this._find_componentMap_i_belong_to().getRbDataObjectActual().rb_MASK_STATUS();
		if (status_vpos_kon_mask.isStatusView()) {
			for (E2_Button b: v_sammle) {
				b.set_bEnabled_For_Edit(false);
			}
		}
		return this;
	}

	
	
	private void set_title(Component bt) {
		//ueberschrift
		this.get_inner_grid_4_data()._setSize(30,30,120,120,100,400,80)._bo_dd()
					._a(bt==null?new RB_lab():bt,			ld_l_t)
					._a(new RB_lab(),						ld_l_t)
					._a(new RB_lab()._tr("Zeitraum von"),	ld_m_t)
					._a(new RB_lab()._tr("..bis"),			ld_m_t)
					._a(new RB_lab()._tr("Menge"),			ld_r_t)
					._a(new RB_lab()._tr("Bemerkung"),		ld_l_t)
					._a(new RB_lab()._tr("ID"),				ld_r_t)
					;

	}
	
	
	
	
	/**
	 * button zum erfassen und editieren der liefertermine
	 * @author martin
	 *
	 */
	private class own_bt_edit_termine extends RB_HL_bt_to_open_simple_mask {

		private MASK_STATUS status = 			null;
		private String 		id_vpos_kon_term =	null;
		

		/**
		 * @param p_status
		 * @param p_id_vpos_kon_lager
		 */
		public own_bt_edit_termine(MASK_STATUS p_status, String p_id_vpos_kon_lager) {
			super();
			this.status = p_status;
			this.id_vpos_kon_term = p_id_vpos_kon_lager;
			this._set_mask_status_and_id(this.status, this.id_vpos_kon_term);
			this._set_height_popup(400)._set_width_popup(650)._set_maskTitle(S.mt("Liefertermin erfassen ..."));
		}

		@Override
		public RB_ModuleContainerMASK generate_RB_ModuleContainerMASK() throws myException {
			return new ownE2ModulContainerMask();
		}

		@Override
		public RB_DataobjectsCollector_V2 generate_RB_DataobjectsCollector_V2(MASK_STATUS actual_mask_status,	String p_id_vpos_kon_lager) throws myException {
			return new ownDataObjectCollector(actual_mask_status, p_id_vpos_kon_lager);
		}

		@Override
		public RB_ComponentMapCollector generate_RB_ComponentMapCollector() throws myException {
			return new ownComponentMapCollector();
		}

		@Override
		public RB_grid4masks generate_RB_grid4masks(RB_ComponentMapCollector componentMapCollector) throws myException {
			RB_grid4masks  mask = new RB_grid4masks();
			
			RB_ComponentMap map = componentMapCollector.get(new RB_KM(_TAB.vpos_kon_term));
			
			mask._setSize(130,400)
				._a(new RB_lab()._tr("ID"), 					new RB_gld()._left_mid()._ins(4))._a(map.getComp(VPOS_KON_TERM.id_vpos_kon_term), 	new RB_gld()._left_mid()._ins(2))
				._a(new RB_lab()._tr("Liefertermin von ..."), 	new RB_gld()._left_mid()._ins(4))._a(map.getComp(VPOS_KON_TERM.lieferdatum), 		new RB_gld()._left_mid()._ins(2))
				._a(new RB_lab()._tr("bis ..."), 				new RB_gld()._left_mid()._ins(4))._a(map.getComp(VPOS_KON_TERM.lieferdatum_bis), 	new RB_gld()._left_mid()._ins(2))
				._a(new RB_lab()._tr("Menge"), 					new RB_gld()._left_mid()._ins(4))._a(map.getComp(VPOS_KON_TERM.anzahl), 			new RB_gld()._left_mid()._ins(2))
				._a(new RB_lab()._tr("Bemerkung"), 				new RB_gld()._left_mid()._ins(4))._a(map.getComp(VPOS_KON_TERM.bemerkung), 			new RB_gld()._left_mid()._ins(2))
				;
			return mask;
		}

		@Override
		public RB_HL_bt_to_open_simple_mask define_actionsagents_for_mask_save_button(E2_IF_Handles_ActionAgents button_savemask, RB_ModuleContainerMASK mask) throws myException {
			button_savemask.add_oActionAgent(new RB_actionStandardSave(mask));
			button_savemask.add_oActionAgent(new RB_actionStandardClosePopup(mask));
			button_savemask.add_oActionAgent(new ownActionRefreshList());
			return this;
		}

		@Override
		public RB_HL_bt_to_open_simple_mask define_actionsagents_for_mask_cancel_button(E2_IF_Handles_ActionAgents button_cancelmask, RB_ModuleContainerMASK mask) throws myException {
			button_cancelmask.add_oActionAgent(new RB_actionStandardClosePopup(mask));
			button_cancelmask.add_oActionAgent(new ownActionRefreshList());
			return this;
		}
		
		
		private class ownE2ModulContainerMask extends RB_ModuleContainerMASK {
		}
		
		
		/**
		 * RB_dataobjectCollector fuer die lagermaske
		 * @author martin
		 *
		 */
		private class ownDataObjectCollector extends RB_DataobjectsCollector_V2 {
			private MASK_STATUS status = 			null;
			private String 		id_vpos_kon_term =	null;
			
			public ownDataObjectCollector(MASK_STATUS p_status, String p_id_vpos_kon_term) throws myException {
				super();
				this.status = p_status;
				this.id_vpos_kon_term = p_id_vpos_kon_term;

				//2017-01-31: formal ein RB_dataobject mit fuehren, der die kopftabelle locked gegen konkurierende aenderungen
				KFIX_P_M_masklist_liefertermine oThis =KFIX_P_M_masklist_liefertermine.this;
				String id_vpos_kon = oThis.id_vpos_kon_actual;
				Rec20 rec_vkopf = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon).get_up_Rec20(VKOPF_KON.id_vkopf_kon);
				this.registerComponent(_TAB.vpos_kon.rb_km(), new KFIX_K__dataObject(rec_vkopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon)));
				
				if (p_status.isStatusNew()) {
					this.registerComponent(_TAB.vpos_kon_term.rb_km(), new RB_Dataobject_V2(_TAB.vpos_kon_term));
				} else {
					this.registerComponent(_TAB.vpos_kon_term.rb_km(), new RB_Dataobject_V2(new Rec20(_TAB.vpos_kon_term)._fill_id(id_vpos_kon_term),p_status));
				}
			}

			@Override
			public void database_to_dataobject(Object startPoint) throws myException {
			}

			@Override
			public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector,MyE2_MessageVector mv) throws myException {
			}

			@Override
			public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,	MyE2_MessageVector mv) throws myException {
				//nachschauen, ob die summe der efrassten menge die liefermenge ueberschreitet
				KFIX_P_M_masklist_liefertermine oThis =KFIX_P_M_masklist_liefertermine.this;
				String id_vpos_kon = oThis.id_vpos_kon_actual;
				
				Rec20 		rec_vpos_kon = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon);
				
				RecList20  	rl_lieftermine = rec_vpos_kon.get_down_reclist20(VPOS_KON_TERM.id_vpos_kon, null, null);
				
				BigDecimal  bd_summe = rl_lieftermine.get_sum_bigDecimal((r)-> {return r.get_myBigDecimal_dbVal(VPOS_KON_TERM.anzahl, new MyBigDecimal(BigDecimal.ZERO)).get_bdWert();});
				
				
				MyBigDecimal db_in_mask = new MyBigDecimal(((IF_RB_Component_Savable)oThis._find_component_in_neighborhood(_TAB.vpos_kon.rb_km(), VPOS_KON.anzahl.fk())).rb_readValue_4_dataobject());
				MyBigDecimal bd_toleranz = new MyBigDecimal(((IF_RB_Component_Savable)oThis._find_component_in_neighborhood(_TAB.vpos_kon.rb_km(), VPOS_KON.mengen_toleranz_prozent.fk())).rb_readValue_4_dataobject());
				
				boolean      is_ladungs_kontrakt = S.NN((((IF_RB_Component_Savable)oThis._find_component_in_neighborhood(_TAB.vpos_kon.rb_km(), VPOS_KON.typ_ladung.fk())).rb_readValue_4_dataobject())).equals("Y");
				
				if (db_in_mask.isOK()) { 
					if (bd_toleranz.isNotOK()) {
						bd_toleranz = new MyBigDecimal(0);
					}
					
					BigDecimal bd_in_mask = 					db_in_mask.get_bdWert(); 
					BigDecimal bd_toleranzFaktor = 				MyBigDecimal.divide(bd_toleranz.get_bdWert(), new BigDecimal(100), 5);    // zahl in prozent
					BigDecimal db_in_mask_toleranz_inclusive = 	MyBigDecimal.multiplicate(bd_in_mask, bd_toleranzFaktor.add(new BigDecimal(1)), 3);
					
					//falls es eine position vom typ lieferungsfixierung ist, dann keine validierung, da die menge von der fuhre abhaengt
					if (!is_ladungs_kontrakt) {
						if (bd_summe.compareTo(db_in_mask_toleranz_inclusive)>0) {
							mv.add_MESSAGE(new MyE2_Alarm_Message("Achtung! Die Menge in den Lieferterminen ist größer als die Positionsmenge (incl. evtl. Toleranz) !"));
						}
					} else {
						if (bd_in_mask.doubleValue()>0 && bd_summe.compareTo(db_in_mask_toleranz_inclusive)>0) {
							mv.add_MESSAGE(new MyE2_Warning_Message("Achtung! Die Menge in den Lieferterminen ist evtl. größer als die Positionsmenge, hängt aber von der Ladung ab !"));
						}
					}
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message("Achtung! Das Mengenfeld in der Maske der Kontraktposition enthält keine Zahl oder ist leer. Bitte geben Sie eine Menge ein, damit Liefertermine erfaßt werden können !!!"));
				}
			}

			@Override
			public ownDataObjectCollector get_copy() throws myException {
				ownDataObjectCollector v_neu = new ownDataObjectCollector(this.status,this.id_vpos_kon_term);
				v_neu.rb_hm_DataObjects().putAll(this.rb_hm_DataObjects());
				return v_neu;
			}
		}
		
		
		/**
		 * RB_componentMapCollector fuer die lagermaske
		 * @author martin
		 *
		 */
		private class ownComponentMapCollector extends RB_ComponentMapCollector {
			public ownComponentMapCollector() throws myException {
				super();
				this.registerComponent(new RB_KM(_TAB.vpos_kon_term), new ownComponentMAP());
			}
		}


		/**
		 * RB_componentmap fuer die Lagermaske
		 * @author martin
		 *
		 */
		private class ownComponentMAP extends RB_ComponentMap {
			public ownComponentMAP() throws myException {
				super();
				RB_date_selektor lieftermin_von = new RB_date_selektor(100,true);
				lieftermin_von.get_vActionAgentsZusatz().add(new ownActionSetDatumInBeideFelder());
				
		        this.registerComponent(VPOS_KON_TERM.lieferdatum,  	  	lieftermin_von);
		        this.registerComponent(VPOS_KON_TERM.lieferdatum_bis,  	new RB_date_selektor(100,true));
		        this.registerComponent(VPOS_KON_TERM.anzahl,    			new RB_TextField(100)._bord_ddd());
		        this.registerComponent(VPOS_KON_TERM.bemerkung,    		new RB_TextArea(400,6)._bord_ddd());
		        this.registerComponent(VPOS_KON_TERM.id_vpos_kon_term,   	new RB_lab());
			}

			@Override
			public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
				return null;
			}

			@Override
			public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
				bibALL.setFocus(this.get__CompEcho(VPOS_KON_TERM.lieferdatum));
				return null;
			}

			@Override
			public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
				preSettingsContainer.rb_set_forcedValueAtSave(VPOS_KON_TERM.id_vpos_kon, KFIX_P_M_masklist_liefertermine.this.id_vpos_kon_actual);
				return new MyE2_MessageVector();
			}

			@Override
			public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
			}
			
			private class ownActionSetDatumInBeideFelder extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					ownComponentMAP oThis = ownComponentMAP.this;
					RB_date_selektor von = (RB_date_selektor)oThis.getComp(VPOS_KON_TERM.lieferdatum);
					RB_date_selektor bis = (RB_date_selektor)oThis.getComp(VPOS_KON_TERM.lieferdatum_bis);
					
					if (S.isEmpty(bis.get_oTextField().getText())) {
						bis.get_oTextField().setText(von.get_oTextField().getText());
					}
					
				}
			}

			
		}
	}
	


	/**
	 * actionagent, speichert und laedt die vpos-kon-maske, damit eine lager-menge erfasst werden kann
	 * @author martin
	 *
	 */
	private class ownActionSaveAndReopen_vpos_kon extends RB_actionStandardSaveAndReopen_Rec20 {

		public ownActionSaveAndReopen_vpos_kon() throws myException {
			super();
			this._set_mask_key_main(_TAB.vpos_kon.rb_km())._set_leading_table_on_mask(_TAB.vpos_kon);
		}

		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_vpos_kon, RB_ComponentMapCollector componentMapCollector_actual) throws myException {
			KFIX_P_M__ComponentMapCollector 	map_collector = (KFIX_P_M__ComponentMapCollector)componentMapCollector_actual;
			KFIX_P_M__DataObjectCollector      data_collector = (KFIX_P_M__DataObjectCollector)map_collector.rb_Actual_DataobjectCollector();
			
			return new KFIX_P_M__DataObjectCollector(id_vpos_kon, MASK_STATUS.EDIT,data_collector.getBelegTyp());
		}

		@Override
		public RB_ComponentMapCollector get_componentMapCollector() throws myException {
			KFIX_P_M_masklist_liefertermine oThis = KFIX_P_M_masklist_liefertermine.this;
 			
 			return oThis._find_componentMapCollector_i_belong_to();
 		}

	}

	
	/**
	 * nach beliebiger aktion die liste neu bauen
	 * @author martin
	 *
	 */
	private class ownActionRefreshList extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_P_M_masklist_liefertermine oThis =KFIX_P_M_masklist_liefertermine.this;
			oThis.rb_set_db_value_manual(oThis.id_vpos_kon_actual);
		}
	}

	
	private class ownDeleteButton extends RB_bt_delete_single {

		public ownDeleteButton(String p_id_to_delete) throws myException {
			super(p_id_to_delete);
		}

		@Override
		public Vector<String> get_delete_sql_statements(String id_to_delete) throws myException {
			return new VEK<String>()._a(new Rec20(_TAB.vpos_kon_term)._fill_id(id_to_delete).get_sql_2_delete());
		}

		@Override
		public Vector<XX_ActionAgent> get_action_after_delete() throws myException {
			return new VEK<XX_ActionAgent>()._a(new ownActionRefreshList());
		}

		@Override
		public String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
			return "Achtung! Sie löschen einen Liefertermin!";
		}

		@Override
		public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
			return "Achtung! Sie löschen einen Liefertermin!";
		}
		
	}
	

	
}
