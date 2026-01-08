package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_LabelFilledWithResult extends RB_lab   {
	
	private RB_KF Key = null;
	public abstract String  transferDbValueToVisibleText(String c_db_val) throws myException;   
	
	
	public RB_LabelFilledWithResult() throws myException {
		super();
	}
	

	public RB_LabelFilledWithResult(String s) throws myException {
		super(s);
	}

	public RB_LabelFilledWithResult(LayoutData oLayout) throws myException {
		super("");
		this._set_ld(oLayout);
	}

	public RB_LabelFilledWithResult(E2_MutableStyle oStyle,	LayoutData oLayout) throws myException {
		super("");
		this._set_ld(oLayout)._set_style(oStyle);
	}

	public RB_LabelFilledWithResult(E2_MutableStyle oStyle)	throws myException {
		super("");
		this._set_style(oStyle);
	}


	
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.setText(this.transferDbValueToVisibleText(valueFormated));
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()!=null) {
			this.setText(this.transferDbValueToVisibleText(
					((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()))
					);
		}
	}


	
	
}
