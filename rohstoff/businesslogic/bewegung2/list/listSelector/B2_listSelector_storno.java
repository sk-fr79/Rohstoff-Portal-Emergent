/**
 * rohstoff.businesslogic.bewegung2.list.listSelector
 * @author sebastien
 * @date 11.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.S;

public class B2_listSelector_storno extends E2_ListSelektorMultiselektionStatusFeld_STD{

	public B2_listSelector_storno() {
		super(new int[] {115,115}, true, S.ms(""), new Extent(0));
		
		this.ADD_STATUS_TO_Selector(false, BG_VEKTOR.id_bg_storno_info.tnfn()+" IS NULL", S.ms("Storno"), S.ms("Stornierte Fuhren as dem AKTIVEN Bereich"));
		
	}

}
