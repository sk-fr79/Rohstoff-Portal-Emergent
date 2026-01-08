/**
 * panter.gmbh.Echo2.__BASIC_MODULS.textlisteVorlage
 * @author martin
 * @date 26.03.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;

import panter.gmbh.Echo2.RB.COMP.RB_SelFieldV3;
import panter.gmbh.Echo2.RB.COMP.TextListe.EnumTableTranslator;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 26.03.2020
 *
 */
public class TLV_MaskComponentSelTable extends RB_SelFieldV3 {

	/**
	 * @author martin
	 * @throws myException 
	 * @date 26.03.2020
	 *
	 */
	public TLV_MaskComponentSelTable() throws myException {
		super();
		
		HMAP<String, String> values = EnumTableTranslator.VKOPF_KON_EK.getHMAP(true).getHMAPFiltered((key,value)-> {
			if (S.isEmpty((String)value)) {
				return true;   //der leereintrag kommt auch mit
			} else {
//				EnumTableTranslator translator = EnumTableTranslator.VKOPF_KON_EK.getEnumFromText((String)key);
				return true;
			}
		});
		this._populate(values, null)._render();
		
	}

}
