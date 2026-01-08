package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

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
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selector_Land_von_nach extends XX_ListSelector_4Multiselect {

	private FU_Selector_Land_von 	select_field_von 	= null;
	private FU_Selector_Land_nach 	select_field_nach 	= null;

	private XX_ActionAgent 			oActionAgent 		= null;

	private E2_Grid grd_4_anzeige 						= new E2_Grid();
	/**
	 * 
	 * @param is_in_baseSelector = true, bei einzeiligen, false im popup
	 * @throws myException
	 */
	public FU_Selector_Land_von_nach() throws myException {
		super();


		this.select_field_von 	= 	new FU_Selector_Land_von(this);
		this.select_field_nach	= 	new FU_Selector_Land_nach(this);
	}




	@Override
	public E2_Grid render_representation_4_base_selector(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors) throws myException {
		grd_4_anzeige._clear();

		 grd_4_anzeige._setSize(138,142,81,100)
		._a(new RB_lab("Startland"), 						new RB_gld()._al(E2_ALIGN.LEFT_MID)		)
		._a(select_field_von.get_oComponentForSelection(),	new RB_gld()._al(E2_ALIGN.LEFT_MID)		)
		._a(new RB_lab("Zielland"), 						new RB_gld()._al(E2_ALIGN.LEFT_MID)		)
		._a(select_field_nach.get_oComponentForSelection(),	new RB_gld()._al(E2_ALIGN.LEFT_MID)		)
		;

		return grd_4_anzeige;
	}

	@Override
	public E2_Grid render_representation_4_popup_selector(Vector<XX_ListSelector_4Multiselect> vector_of_inner_selectors) throws myException {

		E2_Grid grd = new E2_Grid()._setSize(80,120,30,120)
		._a(new RB_lab("von"), 								new RB_gld()._al(E2_ALIGN.LEFT_MID)		)
		._a(select_field_von.get_oComponentForSelection(),	new RB_gld()._al(E2_ALIGN.LEFT_MID)		)
		._a(new RB_lab("nach"), 							new RB_gld()._al(E2_ALIGN.CENTER_MID)	)
		._a(select_field_nach.get_oComponentForSelection(),	new RB_gld()._al(E2_ALIGN.RIGHT_MID)	)
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
		String sorting_query = "";

		if(S.isAllFull(select_field_von.get_WhereBlock(), select_field_nach.get_WhereBlock())){
			sorting_query = 
					"(" + 
							VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.tnfn() + " IN ("+ select_field_von.get_WhereBlock() + ")" +
							" AND " +
							VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.tnfn() + " IN ("+ select_field_nach.get_WhereBlock() + ")" +
							")";
		}else if(S.isFull(select_field_von.get_WhereBlock())){
			sorting_query = VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.tnfn() + " IN ("+ select_field_von.get_WhereBlock() + ")";
		}
		else if(S.isFull(select_field_nach.get_WhereBlock())){
			sorting_query = VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.tnfn() + " IN ("+ select_field_nach.get_WhereBlock() + ")";
		}
		
		return sorting_query;
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.grd_4_anzeige;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.grd_4_anzeige;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.select_field_von.add_ActionAgentToComponent(oAgent);
		this.select_field_nach.add_ActionAgentToComponent(oAgent);
	}

	@Override
	public void doInternalCheck() {
	}

	public FU_Selector_Land_von get_select_field_von() {
		return select_field_von;
	}

	public FU_Selector_Land_nach get_select_field_nach() {
		return select_field_nach;
	}

	public XX_ActionAgent getActionAgent() {
		return oActionAgent;
	}


}
