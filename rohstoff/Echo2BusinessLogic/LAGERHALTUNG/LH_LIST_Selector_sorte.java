/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 03.05.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import org.hamcrest.core.IsNull;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.is_not_null;
import panter.gmbh.indep.dataTools.TERM.is_null;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


public class LH_LIST_Selector_sorte extends E2_ListSelectorMultiDropDown2 {

	public LH_LIST_Selector_sorte() throws myException {
		super();

		String kunde_abfrage = new SEL()
	        	.ADDFIELD("DISTINCT(''||NVL("+ARTIKEL.anr1.tnfn()+",' ')||'-'||NVL("+ARTIKEL_BEZ.anr2.tnfn()+",' ')||' '||"+ARTIKEL_BEZ.artbez1.tnfn()+")", "ART_SORTE") 
	            .ADDFIELD(ARTIKEL_BEZ.id_artikel_bez.tnfn())
	            .FROM(_TAB.lager_palette)
	            .INNERJOIN(_TAB.artikel_bez, ARTIKEL_BEZ.id_artikel_bez.tnfn(), LAGER_PALETTE.id_artikel_bez.tnfn())
	            .INNERJOIN(_TAB.artikel, ARTIKEL.id_artikel.tnfn(), ARTIKEL_BEZ.id_artikel.tnfn())
	            .ORDER("1")
	            .s();
		MyE2_SelectField  selFieldKenner = new MyE2_SelectField(kunde_abfrage, false, true, false, false);

		SEL subSel = new SEL(LAGER_PALETTE_BOX.id_lager_box).FROM(_TAB.lager_palette_box)
				.INNERJOIN(_TAB.lager_palette, 				LAGER_PALETTE.id_lager_palette, 	LAGER_PALETTE_BOX.id_lager_palette)
				.INNERJOIN(_TAB.artikel_bez,				ARTIKEL_BEZ.id_artikel_bez, 		LAGER_PALETTE.id_artikel_bez)
				.WHERE(ARTIKEL_BEZ.id_artikel_bez,			COMP.EQ.ausdruck(), 				"#WERT#")
				.AND(new is_null(LAGER_PALETTE_BOX.ausbuchung_am)	)
				;
		
		And  bed = new And(LAGER_BOX.id_lager_box,COMP.IN.ausdruck(), "("+subSel.s()+")");
		
		this.INIT(selFieldKenner, bed.s(), null);
		
		this.set_extOfSelectComponentDropDown(new Extent(200));

	}
	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	private class ownBasicContainer extends E2_BasicModuleContainer {}
	@Override
	public Component get_oComponentForSelection() throws myException {
		E2_Grid  gridHelp = new E2_Grid()._setSize(100,100)._bo_no()
				._a(new RB_lab()._t("Material:"), 		new RB_gld()._ins(30,2,10,2)._left_mid())
				._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
		return gridHelp;
	}
}

