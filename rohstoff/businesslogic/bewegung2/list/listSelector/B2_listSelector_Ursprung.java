package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.S;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;

public class B2_listSelector_Ursprung extends E2_ListSelektorMultiselektionStatusFeld_STD {

	public B2_listSelector_Ursprung() {
		super(new int[] { 100, 100, 100 }, true, S.ms(""), new Extent(0));
			this.ADD_STATUS_TO_Selector(true, BG_VEKTOR.en_vektor_quelle.tnfn() + "="+EN_VEKTOR_QUELLE.NATIV.dbVal4SqlTerm(), S.ms("Atom"),
					S.ms("Zeige aus neue Warenbewegungs-Sätze"));
			this.ADD_STATUS_TO_Selector(false, BG_VEKTOR.en_vektor_quelle.tnfn() + "="+EN_VEKTOR_QUELLE.FUHRE.dbVal4SqlTerm(), S.ms("Fuhre"),
					S.ms("Zeige aus Fuhren-Daten konvertierte Bewegungen"));
			this.ADD_STATUS_TO_Selector(false, BG_VEKTOR.en_vektor_quelle.tnfn() + "="+EN_VEKTOR_QUELLE.LAGER.dbVal4SqlTerm(), S.ms("Umbuch."),
					S.ms("Zeige aus Bewegungen aus alten Umbuchungen"));
			
			
			for(MyE2_CheckBox checkboxTyp: this.get_vCheckBoxTypen()) {
				checkboxTyp.setFont(new E2_FontPlain(-2));
			}
			
			this.set_cConditionWhenAllIsSelected("1=1");
			
	}
}
