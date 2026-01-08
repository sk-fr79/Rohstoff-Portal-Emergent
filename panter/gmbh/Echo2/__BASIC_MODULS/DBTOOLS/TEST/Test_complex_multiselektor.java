package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.XX_ListSelector_4Multiselect;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Test_complex_multiselektor extends XX_ListSelector_4Multiselect {

	private Test_multiselector_selectfield select_field_von = null;
	private Test_multiselector_selectfield select_field_nach = null;


	
	public Test_complex_multiselektor() throws myException {
		super();
		this.select_field_von = new Test_multiselector_selectfield();
		this.select_field_nach = new Test_multiselector_selectfield();
	}
	
	@Override
	public E2_Grid render_representation_4_base_selector(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors)
			throws myException {
		E2_Grid grd = new E2_Grid()._s(4)
				._a(new RB_lab("von"), new RB_gld()._al(E2_ALIGN.LEFT_MID))
				._a(select_field_von.get_oComponentForSelection(),new RB_gld()._al(E2_ALIGN.LEFT_MID))
				._a(new RB_lab("nach"), new RB_gld()._al(E2_ALIGN.CENTER_MID))
				._a(select_field_nach.get_oComponentForSelection(),new RB_gld()._al(E2_ALIGN.LEFT_MID))
				;

		return grd;
	}

	@Override
	public E2_Grid render_representation_4_popup_selector(
			Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors) throws myException {

		E2_Grid grd = new E2_Grid()._s(4)
				._a(new RB_lab("von"), new RB_gld()._al(E2_ALIGN.LEFT_MID))
				._a(select_field_von.get_oComponentForSelection(),new RB_gld()._al(E2_ALIGN.LEFT_MID))
				._a(new RB_lab("nach"), new RB_gld()._al(E2_ALIGN.CENTER_MID))
				._a(select_field_nach.get_oComponentForSelection(),new RB_gld()._al(E2_ALIGN.LEFT_MID))
				;

		return grd;


	}

	@Override
	public E2_Grid render_representation_placeholder(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors)
			throws myException {
		return new E2_Grid()._a(new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER))._w(new Extent(300));
	}

	@Override
	public String get_WhereBlock() throws myException {
		if(S.isAllFull(select_field_von.get_WhereBlock(), select_field_nach.get_WhereBlock())){	
			
			return "("+ select_field_von.get_WhereBlock() + " AND " + select_field_nach.get_WhereBlock() + ")";
			
		}else if(S.isFull(select_field_von.get_WhereBlock())){
			
			return  "("+ select_field_von.get_WhereBlock()+")";
			
		}else if(S.isFull(select_field_nach.get_WhereBlock())){
			
			return  "(" + select_field_nach.get_WhereBlock() + ")";
			
		}else{
			
			return "";
			
		}
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return null;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return null;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {}

	@Override
	public void doInternalCheck() {}

}
