package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.XX_ListSelector_4Multiselect;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class Test_multiselector_selectfield extends XX_ListSelector_4Multiselect {
	
	private MyE2_SelectField land_selField = null;
	
	public Test_multiselector_selectfield() throws myException {
		super();
		land_selField = new MyE2_SelectField(
				new SEL().ADDFIELD(LAND.beschreibung).ADDFIELD(LAND.id_land).FROM(_TAB.land).s(), 
				false, 
				true, 
				false, 
				false);
		land_selField.setWidth(new Extent(120));
	}
	
	@Override
	public E2_Grid render_representation_4_base_selector(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors)
			throws myException {
		return new E2_Grid()._a(land_selField);
	}

	@Override
	public E2_Grid render_representation_4_popup_selector(
			Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors) throws myException {
		return new E2_Grid()._a(land_selField);
	}

	@Override
	public E2_Grid render_representation_placeholder(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors)
			throws myException {
		return new E2_Grid()._a(new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER))._w(new Extent(300));
	}

	@Override
	public String get_WhereBlock() throws myException {
		if(S.isFull(land_selField.get_ActualWert())){
			return _TAB.land.fullTableName()+LAND.id_land.fieldName()+"="+land_selField.get_ActualWert();
		}
		return "";
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.land_selField;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.land_selField;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.land_selField._aaa(oAgent);	
	}

	@Override
	public void doInternalCheck() {}
}
