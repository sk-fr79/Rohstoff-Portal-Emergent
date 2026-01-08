package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.XX_ListSelector_4Multiselect;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class Test_multiselector_cb extends XX_ListSelector_4Multiselect {

	private MyE2_CheckBox   cb = new MyE2_CheckBox();
	
	public Test_multiselector_cb() {
		super();
	}

	@Override
	public E2_Grid render_representation_4_base_selector(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors)throws myException {
		return new E2_Grid()._a(cb);
	}

	@Override
	public E2_Grid render_representation_4_popup_selector(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors) throws myException {
		return new E2_Grid()._a(cb);
	}

	@Override
	public E2_Grid render_representation_placeholder(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors)
			throws myException {

		return new E2_Grid()._a(new RB_lab("< MEHRFACH >"))._w(new Extent(300));
	}

	@Override
	public String get_WhereBlock() throws myException {
		return cb.isSelected()?"AKTIV='Y'":"AKTIV='N'";
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return cb;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return cb;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.cb.add_oActionAgent(oAgent);
		
	}

	@Override
	public void doInternalCheck() {
	}

	public MyE2_CheckBox get_cb() {
		return cb;
	}
	

}
