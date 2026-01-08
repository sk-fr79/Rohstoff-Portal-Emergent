/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 03.05.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


public class LH_LIST_Selector_kunde extends E2_ListSelectorMultiDropDown2 {

	public LH_LIST_Selector_kunde() throws myException {
		super();

		String kunde_abfrage = new SEL()
				.ADDFIELD("DISTINCT(" + ADRESSE.name1.tnfn() 
				+ "||' '|| + NVL("+ADRESSE.name2.tnfn()+",'')||' - '||"+ADRESSE.ort.tnfn() +" ||' ('||" +ADRESSE.id_adresse.tnfn()+"||')')")
				.ADDFIELD(ADRESSE.id_adresse)
				.FROM(_TAB.adresse)
				.INNERJOIN(_TAB.vpos_tpa_fuhre, ADRESSE.id_adresse, 			VPOS_TPA_FUHRE.id_adresse_start)
				.INNERJOIN(_TAB.lager_palette, 	LAGER_PALETTE.id_vpos_tpa_fuhre_ein,VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)
				.WHERE(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
				.ORDER("1")
				.s();

		MyE2_SelectField  selFieldKenner = new MyE2_SelectField(kunde_abfrage, false, true, false, false);

		SEL subSel = new SEL(LAGER_BOX.id_lager_box).FROM(_TAB.lager_box)
				.INNERJOIN(_TAB.lager_palette, LAGER_BOX.id_lager_box, LAGER_PALETTE.id_lager_box)
				.INNERJOIN(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
				.INNERJOIN(_TAB.adresse,ADRESSE.id_adresse, VPOS_TPA_FUHRE.id_adresse_start)
				.WHERE(ADRESSE.id_adresse,COMP.EQ.ausdruck(), "#WERT#");
		
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
				._a(new RB_lab()._t("Kunde:"), 			new RB_gld()._ins(30,2,10,2)._left_mid())
				._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
		return gridHelp;
	}
}

