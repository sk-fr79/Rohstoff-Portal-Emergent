
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class BOR_MASK_MaskGrid extends RB_MaskGrid {

	public BOR_MASK_MaskGrid(BOR_MASK_ComponentMapCollector mapColl) throws myException {
		super(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		BOR_MASK_ComponentMap map1 = (BOR_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.bordercrossing));

		this._add(new MyE2_Label(new MyE2_String("ID")), new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.id_bordercrossing)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		this.add(new MyE2_Label(new MyE2_String("Quell-Land")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.id_land_quelle)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		this.add(new MyE2_Label(new MyE2_String("Ziel-Land")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.id_land_ziel)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		
		this.add(new MyE2_Label(new MyE2_String("Intervall der Erinnerung")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.intervall_tage)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		
		this.add(new MyE2_Label(new MyE2_String("Tage VOR dem Fuhrendatum starten"),true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.offset_before_start)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		
		this.add(new MyE2_Label(new MyE2_String("Sofortmeldung bei der Anlage")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.erinnerung_bei_anlage)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
	
		
		this.add(new MyE2_Label(new MyE2_String("Titel")), new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.title)), new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		this.add(new MyE2_Label(new MyE2_String("Meldung")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(new RB_KF(BORDERCROSSING.message)),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));

		this.add(new MyE2_Label(new MyE2_String("Benutzer")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(BOR_CONST.MASK_KEYS.USER_CROSSTABLE.key()),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		
		this.add(new MyE2_Label(new MyE2_String("Artikel")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this.add(map1.getRbComponent(BOR_CONST.MASK_KEYS.BORDERCROSSING_ARTIKEL.key()),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._span(2));
		
		
	}

}
