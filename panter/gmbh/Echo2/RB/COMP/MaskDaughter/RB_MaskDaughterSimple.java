package panter.gmbh.Echo2.RB.COMP.MaskDaughter;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_MaskDaughterSimple extends E2_Grid implements IF_RB_Component{

	private RB_KF 			  rb_kf = null;
	
	
	
	
//	public abstract 
	
	/**
	 * 
	 */
	public RB_MaskDaughterSimple() {
		super();
	}

	
	
	@Override
	public abstract void set_bEnabled_For_Edit(boolean enabled) throws myException;

	
	@Override
	public abstract void mark_Neutral() throws myException;

	@Override
	public abstract void mark_MustField() throws myException;

	@Override
	public abstract void mark_Disabled() throws myException ;

	@Override
	public abstract void mark_FalseInput() throws myException;
	
	
	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public abstract void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException ;

	@Override
	public abstract void rb_set_db_value_manual(String id_of_mothertable) throws myException ;

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


}
