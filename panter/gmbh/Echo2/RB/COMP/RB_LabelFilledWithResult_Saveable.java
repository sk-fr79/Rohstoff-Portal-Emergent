package panter.gmbh.Echo2.RB.COMP;

import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_LabelFilledWithResult_Saveable extends RB_LabelFilledWithResult implements IF_RB_Component_Savable {
	
	private String  db_val_formated = null;

	/**
	 * @throws myException
	 */
	public RB_LabelFilledWithResult_Saveable() throws myException {
		super();
	}

	/**
	 * @param oStyle
	 * @param oLayout
	 * @throws myException
	 */
	public RB_LabelFilledWithResult_Saveable(E2_MutableStyle oStyle, LayoutData oLayout) throws myException {
		super(oStyle, oLayout);
	}

	/**
	 * @param oStyle
	 * @throws myException
	 */
	public RB_LabelFilledWithResult_Saveable(E2_MutableStyle oStyle) throws myException {
		super(oStyle);
	}

	public String rb_readValue_4_dataobject() throws myException {
		return this.db_val_formated;
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.setText(this.transferDbValueToVisibleText(valueFormated));
		this.db_val_formated = valueFormated;
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()!=null) {
			this.setText(this.transferDbValueToVisibleText(
					((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()))
					);
			
			this.db_val_formated = ((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME());
		}
	}

	
}
