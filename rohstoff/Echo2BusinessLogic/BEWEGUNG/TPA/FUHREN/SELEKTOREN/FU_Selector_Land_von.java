package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.XX_ListSelector_4Multiselect;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selector_Land_von  extends XX_ListSelector_4Multiselect{

	private MyE2_SelectField land_selField;

	public FU_Selector_Land_von(FU_Selector_Land_von_nach fu_Selector_Land_von_nach) throws myException{
		super();
		
		this.land_selField = new MyE2_SelectField(
				new SEL().ADDFIELD(LAND.beschreibung).ADDFIELD(LAND.id_land).FROM(_TAB.land).s(), 
				false, 
				true, 
				false, 
				false);
		
		this.land_selField.setWidth(new Extent(100));
		this.land_selField.setFont(new E2_FontPlain(-1));
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
		return null;
	}

	@Override
	public String get_WhereBlock() throws myException {
		
		StringBuffer query_as_strbuf = new StringBuffer("SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE JT_VPOS_TPA_FUHRE ");
		query_as_strbuf.append("LEFT OUTER JOIN JT_ADRESSE A1  ON A1.ID_ADRESSE = JT_VPOS_TPA_FUHRE.ID_ADRESSE_START ");
		query_as_strbuf.append("LEFT OUTER JOIN JT_ADRESSE A2  ON A2.ID_ADRESSE = JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START ");
		query_as_strbuf.append("LEFT OUTER JOIN JT_VPOS_TPA_FUHRE_ORT JT_VPOS_TPA_FUHRE_ORT ON JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE ");
		query_as_strbuf.append("LEFT OUTER JOIN JT_ADRESSE A3 ON JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE = A3.ID_ADRESSE ");
		query_as_strbuf.append("WHERE ");
//		query_as_strbuf.append("NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND");
		if(S.isFull(land_selField.get_ActualWert())){
			query_as_strbuf.append(
					"A1.ID_LAND = " + land_selField.get_ActualWert() + " OR " + 
					"A2.ID_LAND = " + land_selField.get_ActualWert() + " OR " + 
					"A3.ID_LAND = " + land_selField.get_ActualWert() );
			return query_as_strbuf.toString();
		}
		
				//.WHERE(new vgl(ADRESSE.id_land, land_selField.get_ActualWert())).s();
		
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
