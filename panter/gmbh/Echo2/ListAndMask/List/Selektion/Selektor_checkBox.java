package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Selektor_checkBox extends XX_ListSelektor_EXT {

	private String 			whereBlockSelected = null;
	private String 			whereBlockUnselected = null;
	private MyE2_String  	checkboxText = null;
	private RB_cb   		cb = new RB_cb();

	
	/**
	 * 
	 * @param p_checkboxText
	 * @param p_whereBlockSelected
	 * @param p_whereBlockUnSelected
	 */
	public Selektor_checkBox(MyE2_String p_checkboxText, String p_whereBlockSelected, String p_whereBlockUnSelected) {
		super();
		this.checkboxText = p_checkboxText;
		
		//this.cb.setText(this.checkboxText!=null?this.checkboxText.CTrans():"");
		this.whereBlockSelected = S.NN(p_whereBlockSelected);
		this.whereBlockUnselected = S.NN(p_whereBlockUnSelected);
	}

	@Override
	public String get_WhereBlock() throws myException {
		if (this.cb.isSelected()) {
			return this.whereBlockSelected;
		} else {
			return this.whereBlockUnselected;
		}
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		
		E2_Grid g = new E2_Grid()._s(2)._a(new RB_lab()._t(this.checkboxText.CTrans()), new RB_gld()._ins(0, 0, 5, 0))._a(this.cb);
		
		return g;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.cb;	
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.cb.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {

	}

	public RB_cb get_cb() {
		return cb;
	}

}
