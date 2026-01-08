package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.S;

public class B2_listSelector_loeschen extends E2_ListSelektorMultiselektionStatusFeld_STD {

	public B2_listSelector_loeschen(boolean is_geloescht) {
		super(new int[] { 100, 100 }, true, S.ms(""), new Extent(0));
			this.ADD_STATUS_TO_Selector(true, BG_VEKTOR.id_bg_del_info.tnfn() + " IS NOT NULL", S.ms("Gelöschte"),
					S.ms("Zeige Bewegung, die gelöscht wurde"));
			this.ADD_STATUS_TO_Selector(true, BG_VEKTOR.id_bg_del_info.tnfn() + " IS NULL", S.ms("Ungelöschte"),
				S.ms("Zeige Bewegung, die NICHT als gelöscht markiert sind."));
			
			for(MyE2_CheckBox checkboxTyp: this.get_vCheckBoxTypen()) {
				checkboxTyp.setFont(new E2_FontPlain(-2));
			}
	}
}
