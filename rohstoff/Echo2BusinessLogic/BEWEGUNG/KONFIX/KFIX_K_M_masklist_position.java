package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import echopointng.Separator;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughter;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen_Rec20;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_masklist_position extends RB_MaskDaughter{

	private KFIX_K_M_ComponentMap parent;

	private E2_NavigationList naviList = null;

	private String id_vkopf_kon = "";
	
	private own_simple_selector simpleSelector = null;
	
	private  KFIX_K_M_masklist_position_raster_grid raster_grid;
	
	public KFIX_K_M_masklist_position(KFIX_K_M_ComponentMap p_parent) throws myException {
		super();
		this._set_width_of_mask_inlay_container_ex(new Extent(1300));
		this._set_height_of_mask_inlay_container_ex(new Extent(500));
		this.parent = p_parent;
		this.simpleSelector = new own_simple_selector(this.parent.isFixiert());

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
		if (((KFIX_K_M_DataObject)dataObject).rb_MASK_STATUS().isStatusNew()) {
			this._rb_set_db_value_manual("");
		} else {
			this._rb_set_db_value_manual(((KFIX_K_M_DataObject)dataObject).get_rec20().get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
		}
		return this;
	}

	
	@Override
	public RB_MaskDaughter _rb_set_db_value_manual(String p_id_vkopf_kon) throws myException {

		this.get_inner_grid_4_data()._clear();

		if (S.isEmpty(p_id_vkopf_kon)) {

			E2_Button  bt_save_mask_first = 
					new E2_Button()._tr("Kontraktkopf muss zuerst gespeichert werden, um Positionen erfassen zu können ")
					._s_BorderText()
					._fsa(2)
					._center()
					._i()
					._aaa(new ownActionSaveAndReopen_vpos_kon());

			this.get_inner_grid_4_data()
			._bo_ddd()
			._a(bt_save_mask_first, new RB_gld()._ins(2)._center_mid());

		}else{
			MyLong l_id_vkopf_kon = new MyLong(p_id_vkopf_kon);

			if (!l_id_vkopf_kon.isOK()) {
				this.get_inner_grid_4_data()._a(new RB_lab("<Fehler beim Aufbau des Position>"));

			} else {
				this.id_vkopf_kon = p_id_vkopf_kon;
				
				SEL query_for_reclist = new SEL("JT_VPOS_KON.*")
												.FROM(_TAB.vpos_kon)
												.INNERJOIN(_TAB.vpos_kon_trakt, VPOS_KON.id_vpos_kon, VPOS_KON_TRAKT.id_vpos_kon)
												.WHERE(new vgl(VPOS_KON.id_vkopf_kon, id_vkopf_kon));
				
				String where_clause =  this.simpleSelector.build_where_clause();
				
				if(S.isFull(where_clause)){
					String cplte_query = query_for_reclist.s() + " AND(" + where_clause + ") ORDER BY POSITIONSNUMMER ASC";
					
					this.raster_grid = new KFIX_K_M_masklist_position_raster_grid(this, cplte_query);
					this.get_inner_grid_4_data()._s(1)._clear()
					
					._a(raster_grid.get_bedien_panel())
					._a(new Separator())
					._a(this.raster_grid);
				}			
			}
			
			
			
		}
		return this;


	}

	public own_simple_selector getSimpleSelector() {

		KFIX_K_L__ModulContainer liste_module_container = (KFIX_K_L__ModulContainer)this.naviList.get_oContainer_NaviList_BelongsTo();
		this.simpleSelector.cb_del.setSelected(liste_module_container.get_oSelektor().get_oCB_ShowDeletedRows().isSelected());

		return simpleSelector;
	}

	public KFIX_K_M_ComponentMap get_parent_component_map() {
		return parent;
	}

	public void set_navigation_list(E2_NavigationList p_navi_list){
		this.naviList = p_navi_list;
	}

	public E2_NavigationList get_navigation_list(){
		return this.naviList; 
	}

	public String get_id_vkopf_kon() {
		return id_vkopf_kon;
	}

	public boolean is_ek_kontrakt() {
		return parent.is_ek_kontrakt();
	}

	private class own_simple_selector extends E2_Grid{

		private RB_cb cb_del	= new RB_cb("Zeige gelöschte")			._fsa(-1)._aaa(new rb_cb_selector_actionagent());
		private RB_cb cb_undel	= new RB_cb("Zeige ungelöschte")		._fsa(-1)._aaa(new rb_cb_selector_actionagent());
		private	RB_cb cb_offpos	= new RB_cb("Offene Position")			._fsa(-1)._aaa(new rb_cb_selector_actionagent());
		private	RB_cb cb_agpos	= new RB_cb("Abgeschlossene Position")	._fsa(-1)._aaa(new rb_cb_selector_actionagent());

		public own_simple_selector(boolean b_isFixiert) throws myException {
			super();
			this._s(6);

			RB_gld gld_l = new RB_gld()._ins(2)._left_mid();
			this.cb_offpos.setSelected(true);
			this.cb_agpos.setSelected(true);
			this.cb_undel.setSelected(true);

			this._setSize(50,150,200);

			this
			._a("Zeige", gld_l)		._a(cb_offpos,  gld_l)	._a(cb_agpos, 	gld_l)	
			._a()					._a(cb_undel, 	gld_l)	._a(cb_del, 	gld_l)	
			;

		}

		private String build_where_clause() throws myException{

			String where_clause="";

			String part_deleted = "";
			if(this.is_geloeschte_selected()){
				part_deleted = part_deleted +"NVL(JT_VPOS_KON.DELETED,'N')='Y'";
			}
			if(this.is_ungeloescht_selected()){
				if(S.isFull(part_deleted)){
					part_deleted = part_deleted + " OR ";
				}
				part_deleted = part_deleted +"NVL(JT_VPOS_KON.DELETED,'N')='N'";
			}

			String part_lock_unlock = "";
			if(this.is_abgeschlossen_selected()){
				part_lock_unlock = part_lock_unlock +"NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='Y'";
			}
			if(this.is_offpos_selected()){
				if(S.isFull(part_lock_unlock)){
					part_lock_unlock = part_lock_unlock + " OR ";
				}
				part_lock_unlock = part_lock_unlock +" NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'";
			}

		

			if(S.isFull(part_deleted) && S.isEmpty(where_clause)){
				if(S.isFull(where_clause)){
					where_clause = where_clause + " AND ";
				}
				where_clause = where_clause  + "(" + part_deleted  + ")";
			}
			if(S.isFull(part_lock_unlock) && S.isFull(where_clause)){
				if(S.isFull(where_clause)){
					where_clause = where_clause + " AND ";
				}
				where_clause = where_clause  + "("   + part_lock_unlock +")" ;
			}

			return where_clause;
		}

		private boolean is_geloeschte_selected(){
			return this.cb_del.isSelected();
		}

		private boolean is_ungeloescht_selected(){
			return this.cb_undel.isSelected();
		}

		private boolean is_offpos_selected(){
			return this.cb_offpos.isSelected();
		}

		private boolean is_abgeschlossen_selected(){
			return this.cb_agpos.isSelected();
		}

	}

	private class rb_cb_selector_actionagent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_K_M_masklist_position oThis = KFIX_K_M_masklist_position.this;
			oThis.rb_set_db_value_manual(oThis.id_vkopf_kon);
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
			this._set_mask_key_main(_TAB.vkopf_kon.rb_km())._set_leading_table_on_mask(_TAB.vkopf_kon);
		}

		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_vpos_kon, RB_ComponentMapCollector componentMapCollector_actual) throws myException {
			KFIX_K_M__ComponentMapCollector 	map_collector = (KFIX_K_M__ComponentMapCollector)componentMapCollector_actual;
			KFIX_K_M__DataObjectCollector      data_collector = (KFIX_K_M__DataObjectCollector)map_collector.rb_Actual_DataobjectCollector();

			return new KFIX_K_M__DataObjectCollector(id_vpos_kon, MASK_STATUS.EDIT,data_collector.getBelegTyp());
		}

		@Override
		public RB_ComponentMapCollector get_componentMapCollector() throws myException {
			KFIX_K_M_masklist_position oThis = KFIX_K_M_masklist_position.this;

			return oThis._find_componentMapCollector_i_belong_to();
		}
	}
}
