
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class FK_MASK_MaskGrid extends RB_MaskGrid {

	public FK_MASK_MaskGrid(FK_MASK_ComponentMapCollector mapColl, FK_MASK_bt_MULTI_FUHREN_KOSTEN oParentButton) throws myException {
		super(3, MyE2_Grid.STYLE_GRID_NO_BORDER());

		int [] iBreite={150,200,200};
		
		this.set_Spalten(iBreite);
		
		FK_MASK_ComponentMap map1 = (FK_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.vpos_tpa_fuhre_kosten));

		this.add(new MyE2_Label(new MyE2_String("Fuhren IDs")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String(bibALL.ConcatenateWithoutException(oParentButton.getSelectedFuhreId(), ", ", ""))),2, E2_INSETS.I(2, 2, 2, 2));
		
		this.add(new MyE2_Label(new MyE2_String("Kostentyp")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.id_fuhren_kosten_typ)), 2, E2_INSETS.I(2, 2, 2, 2));
		
		this.add(new MyE2_Label(new MyE2_String("Zollagentur")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.id_zollagentur)), 2, E2_INSETS.I(2, 2, 2, 2));
		
		
//		this.add(new MyE2_Label(new MyE2_String("Preis/Tonne")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.ist_preis_pro_tonne)), 2, E2_INSETS.I(2, 2, 2, 2));
		
		this.add(new MyE2_Label(new MyE2_String("Betrag")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten)), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("/" + oParentButton.getSelectedFuhreId().size())),1,1, E2_INSETS.I(2, 2, 2, 2), E2_ALIGN.LEFT_MID);

		//		this.add(new MyE2_Label(new MyE2_String("(=")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.anzahl)), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(new MyE2_Label(new MyE2_String("x")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.einzelpreis)), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(new MyE2_Label(new MyE2_String("EUR )")), 1, E2_INSETS.I(2, 2, 2, 2));
//		
		this.add(new MyE2_Label(new MyE2_String("Datum Beleg")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.datum_beleg)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Fremdbeleg-Nummer")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.fremdbeleg_nummer)), 2, E2_INSETS.I(2, 2, 2, 2));		
		this.add(new MyE2_Label(new MyE2_String("Information")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.info_kosten)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Spedition")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.name_spedition)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Beleg liegt vor")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.beleg_vorhanden)), 2, E2_INSETS.I(2, 2, 2, 2));

/*		this.add(new MyE2_Label(new MyE2_String("deleted")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.deleted)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("del_date")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.del_date)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("del_grund")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.del_grund)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("del_kuerzel")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.del_kuerzel)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("id_adresse_spedition")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.id_adresse_spedition)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("id_vpos_tpa_fuhre")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("id_vpos_tpa_fuhre_kosten")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.rb_Component(new RB_KF(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre_kosten)), 2, E2_INSETS.I(2, 2, 2, 2));*/

		
	}

}
