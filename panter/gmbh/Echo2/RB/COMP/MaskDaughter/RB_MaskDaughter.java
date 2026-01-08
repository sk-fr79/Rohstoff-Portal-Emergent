package panter.gmbh.Echo2.RB.COMP.MaskDaughter;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_e2_bt_new;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_MaskDaughter extends E2_Grid implements IF_RB_Component{

	private RB_e2_bt_new 	bt_2_add_data = null;
	
	//ein grid, das fuer die darstellung der daten genutzt werden kann 
	private E2_Grid   		inner_grid_4_data = new E2_Grid();
	
	
	private RB_KF 			  rb_kf = null;
	
	private MyE2_ContainerEx  container_ex_4_grid = new MyE2_ContainerEx();
	private Extent 			  width_of_container_ex = new Extent(400);
	private Extent 			  height_of_container_ex = new Extent(200);
	
	/**
	 * weitergabe von markneutral an evtl. vorhandene komponenten im innergrid
	 * @return
	 * @throws myException
	 */
	public abstract   RB_MaskDaughter      _mark_components_in_innergrid_neutral() throws myException;
	public abstract   RB_MaskDaughter      _mark_components_in_innergrid_disabled() throws myException;
	
	
	/**
	 * weitergabe von set_enabled_for_edit an evtl. vorhandene komponenten im innergrid
	 * @return
	 * @throws myException
	 */
	public abstract  RB_MaskDaughter      _set_components_in_innergrid_enabled_for_edit(boolean b_enabled_4_edit) throws myException;
	
	public abstract  RB_MaskDaughter      _rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException;

	public abstract  RB_MaskDaughter      _rb_set_db_value_manual(String id_of_mothertable) throws myException;


	
	/**
	 * anordnung der komponenten innerhalb der RB_MaskDaughter, kann ueberschrieben werden fuer das Einfuegen Textinfos oder Steuerkomponenten 
	 * @return
	 */
	public 	RB_MaskDaughter  _arrange_inner_in_outer_grid() {
		
		this._clear()
			._s(1)
			._w100()._a(this.inner_grid_4_data, new RB_gld()._ins(0))._bo_no();
		
		return this;
	}
	
	
	
	
//	public abstract 
	
	/**
	 * 
	 */
	public RB_MaskDaughter() {
		super();
	}

	
	public RB_MaskDaughter  _set_bt_2_add_new_dataset(RB_e2_bt_new p_bt_2_add_data) {
		this.bt_2_add_data = p_bt_2_add_data;
		return this;
	}
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		if (this.bt_2_add_data!=null) {
			this.bt_2_add_data.set_bEnabled_For_Edit(enabled);
		}
		this._set_components_in_innergrid_enabled_for_edit(enabled);
	}

	
	@Override
	public void mark_Neutral() throws myException {
		if (this.bt_2_add_data!=null) {
			this.bt_2_add_data.mark_Neutral();
		}
		this._mark_components_in_innergrid_neutral();
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
		if (this.bt_2_add_data!=null) {
			this.bt_2_add_data.mark_Disabled();
		}
		this._mark_components_in_innergrid_disabled();
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this._arrange_inner_in_outer_grid();
		this.inner_grid_4_data._clear();
		this._rb_Datacontent_to_Component(dataObject);
	}

	@Override
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {
		this._arrange_inner_in_outer_grid();
		this.inner_grid_4_data._clear();
		this._rb_set_db_value_manual(id_of_mothertable);
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.rb_kf;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.rb_kf=key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return vVALIDATORS_4_INPUT;
	}


	public RB_e2_bt_new get_bt_2_add_data() {
		return bt_2_add_data;
	}


	public E2_Grid get_inner_grid_4_data() {
		return inner_grid_4_data;
	}
	public Extent get_width_of_container_ex() {
		return this.width_of_container_ex;
	}
	public Extent get_height_of_container_ex() {
		return this.height_of_container_ex;
	}
	public RB_MaskDaughter _set_width_of_mask_inlay_container_ex(Extent p_width_of_container_ex) {
		this.width_of_container_ex = p_width_of_container_ex;
		return this;
	}
	public RB_MaskDaughter _set_height_of_mask_inlay_container_ex(Extent p_height_of_container_ex) {
		this.height_of_container_ex = p_height_of_container_ex;
		return this;
	}


	/**
	 * mit dieser methode laesst sich die komponente innerhalb eines containers anzeigen
	 * @return
	 */
	public MyE2_ContainerEx  get_me_in_container_ex() {
		this.container_ex_4_grid.removeAll();
		this.container_ex_4_grid.add(this);
		this.container_ex_4_grid.setWidth(this.width_of_container_ex);
		this.container_ex_4_grid.setHeight(this.height_of_container_ex);
		return this.container_ex_4_grid;
	}
}
