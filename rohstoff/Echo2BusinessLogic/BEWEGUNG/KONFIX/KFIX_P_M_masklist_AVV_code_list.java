package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughter;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen_Rec20;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_delete_single;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_AVV;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class KFIX_P_M_masklist_AVV_code_list extends RB_MaskDaughter {

	private MyLong l_id_vpos_kon = null;
	
	public KFIX_P_M_masklist_AVV_code_list() {
		super();
		this._set_width_of_mask_inlay_container_ex(new Extent(500));
		this._set_height_of_mask_inlay_container_ex(new Extent(160));	
		
	}

	@Override
	public RB_MaskDaughter _mark_components_in_innergrid_neutral() throws myException {
		return this;
	}

	@Override
	public RB_MaskDaughter _mark_components_in_innergrid_disabled() throws myException {
		return this;
	}

	@Override
	public RB_MaskDaughter _set_components_in_innergrid_enabled_for_edit(boolean b_enabled_4_edit) throws myException {
		return this;
	}

	@Override
	public RB_MaskDaughter _rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (((KFIX_P_M__DataObject)dataObject).rb_MASK_STATUS().isStatusNew()) {
			this._rb_set_db_value_manual("");
		} else {
			this._rb_set_db_value_manual(((KFIX_P_M__DataObject)dataObject).get_rec20().get_ufs_dbVal(VPOS_KON.id_vpos_kon));
		}
		return this;
	}

	@Override
	public RB_MaskDaughter _rb_set_db_value_manual(String id_of_mothertable) throws myException {
		MyLong l_id_vpos_kon = new MyLong(id_of_mothertable);

		this.get_inner_grid_4_data()._clear()._s(1)._bo_dd();

		if (S.isEmpty(id_of_mothertable)) {
			E2_Button  bt_save_mask_first = new E2_Button()._tr("Kontraktposition speichern, um AVV-Code erfassen zu können ")._s_BorderText()._fsa(2)._center()._i();
			bt_save_mask_first._s_BorderText()._center()._i();
			bt_save_mask_first._aaa(new ownActionSaveAndReopen_vpos_kon());

			this.get_inner_grid_4_data()._a_cm(bt_save_mask_first);

		}else{
			if (!l_id_vpos_kon.isOK()) {
				this.get_inner_grid_4_data()._a(new RB_lab("<Fehler beim Aufbau der Liste>"));
			}else{
					
				this.l_id_vpos_kon = l_id_vpos_kon;
					
				build_new_list(l_id_vpos_kon);
			}
		}
		
		return this;
	}

	public MyLong get_id_vpos_kon() {
		return l_id_vpos_kon;
	}

	private void build_new_list(MyLong l_id_vpos_kon) throws myException{
		VEK<E2_Button> v_sammle = new VEK<>();
		
		this.get_inner_grid_4_data()._clear()._setSize(25,25,450)._bo_dd();
		
		this.get_inner_grid_4_data()._a(v_sammle._ar(new own_new_avv_code_search()), new RB_gld()._ins(2)._left_top()._span(3));

		
		SEL query_4_avv_code = new SEL().FROM(_TAB.vpos_kon_avv).WHERE(new vgl(VPOS_KON_AVV.id_vpos_kon, l_id_vpos_kon.get_cUF_LongString()));

		RecList20 avv_recordlist = new RecList20(_TAB.vpos_kon_avv)._fill(query_4_avv_code.s());
		
		for(int i = 0; i<avv_recordlist.size();i++){
			
			MyLong l_id_avv = avv_recordlist.get(i).get_myLong_dbVal(VPOS_KON_AVV.id_vpos_kon_avv);
			MyLong l_id_eak = avv_recordlist.get(i).get_myLong_dbVal(VPOS_KON_AVV.id_eak_code);
						
			KFIX_P_M_AVV_Search avv_search_field = new KFIX_P_M_AVV_Search(this);
			avv_search_field.FillComponent(			l_id_eak.get_cUF_LongString());
			avv_search_field.set_id_vpos_kon_avv(	l_id_avv);
			
			own_edit_button edit_bt = new own_edit_button(avv_search_field);
			
			own_loesch_button_v2 del_bt = new own_loesch_button_v2(l_id_avv.get_cUF_LongString());
			
			this.get_inner_grid_4_data()
			._a(v_sammle._ar(edit_bt)	, new RB_gld()._ins(2)._left_mid())	
			._a(v_sammle._ar(del_bt)	, new RB_gld()._ins(2)._left_mid())	
			._a(avv_search_field		, new RB_gld()._ins(2)._left_mid());

		}
		
		MASK_STATUS  status_vpos_kon_mask = this._find_componentMap_i_belong_to().getRbDataObjectActual().rb_MASK_STATUS();
		if (status_vpos_kon_mask.isStatusView()) {
			for (E2_Button b: v_sammle) {
				b.set_bEnabled_For_Edit(false);
			}
		}
	}

	public void add_new_line(KFIX_P_M_AVV_Search avv_search_field) throws myException{
		String id_vpos_kon_avv ="";
		if(avv_search_field.get_id_vpos_kon_avv() != null){
			id_vpos_kon_avv = avv_search_field.get_id_vpos_kon_avv().get_cUF_LongString();					
		}
		
		own_edit_button edit_bt = new own_edit_button(avv_search_field);
		
		own_loesch_button_v2 del_bt = new own_loesch_button_v2(id_vpos_kon_avv);
		
		get_inner_grid_4_data()
		._a(edit_bt			, new RB_gld()._ins(2)._center_mid())	
		._a(del_bt 			, new RB_gld()._ins(2)._center_mid())	
		._a(avv_search_field, new RB_gld()._ins(2)._left_mid());
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
			KFIX_P_M_masklist_AVV_code_list oThis = KFIX_P_M_masklist_AVV_code_list.this;

			return oThis._find_componentMapCollector_i_belong_to();
		}
	}

	private class own_new_avv_code_search extends E2_Button{

		public own_new_avv_code_search() throws myException {
			super();
			this._image(E2_ResourceIcon.get_RI("new.png"), E2_ResourceIcon.get_RI("new__.png"));
			this._ttt("AVV-Code Auswählen");
			this._aaa(new XX_ActionAgent(){

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					KFIX_P_M_AVV_Search avv_search_field = new KFIX_P_M_AVV_Search(KFIX_P_M_masklist_AVV_code_list.this);
					avv_search_field.getButtonSelect().doActionPassiv();	
					
				}

			});
		}
	}
	
	
	private class own_edit_button extends E2_Button{

		public own_edit_button(KFIX_P_M_AVV_Search avv_search_field) {
			super();
			this._image(E2_ResourceIcon.get_RI("edit.png"), E2_ResourceIcon.get_RI("edit__.png"));
			this.EXT().set_C_MERKMAL("EDIT");
			this._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
//					KFIX_P_M_masklist_AVV_code_list.this._find_componentMap_i_belong_to().rb_Mask_Dataobject_actual().rb_MASK_STATUS();
					
					avv_search_field.getButtonSelect().doActionPassiv();
					
					KFIX_P_M_masklist_AVV_code_list.this.rb_set_db_value_manual(l_id_vpos_kon.get_cUF_LongString());
				}
			});
		}
	}

	private class own_loesch_button_v2 extends RB_bt_delete_single {

		public own_loesch_button_v2(String p_id_to_delete) throws myException {
			super(p_id_to_delete);
		}

		@Override
		public Vector<String> get_delete_sql_statements(String id_to_delete) throws myException {
			return new VEK<String>()._a(new Rec20(_TAB.vpos_kon_avv)._fill_id(id_to_delete).get_sql_2_delete());
		}

		@Override
		public Vector<XX_ActionAgent> get_action_after_delete() throws myException {
			return new VEK<XX_ActionAgent>()._a(new ownActionRefreshList());
		}

		@Override
		public String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
			return "Achtung! Sie löschen einen AVV-Code!";
		}

		@Override
		public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
			return "Achtung! Sie löschen einen AVV-Code!";
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
			KFIX_P_M_masklist_AVV_code_list oThis =KFIX_P_M_masklist_AVV_code_list.this;
			oThis.rb_set_db_value_manual(oThis.get_id_vpos_kon().get_cUF_LongString());
		}
		
	}

}
