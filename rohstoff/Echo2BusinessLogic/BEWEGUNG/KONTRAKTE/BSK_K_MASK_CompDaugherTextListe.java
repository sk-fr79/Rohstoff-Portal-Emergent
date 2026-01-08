/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE
 * @author martin
 * @date 19.02.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.RB.COMP.TextListe.EnumTableTranslator;
import panter.gmbh.Echo2.RB.COMP.TextListe.TL_MASK_DaughterListForMotherMaskClassicType;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

/**
 * @author martin
 * @date 19.02.2020
 *
 */
public class BSK_K_MASK_CompDaugherTextListe extends TL_MASK_DaughterListForMotherMaskClassicType {

	/**
	 * @author martin
	 * @date 19.02.2020
	 *
	 * @param useCompactList
	 * @param p_motherTable
	 * @throws myException
	 */
	public BSK_K_MASK_CompDaugherTextListe(BS__SETTING setting) throws myException {
		super(true, "ID-Kontrakt",setting.get_cVORGANGSART().equals(myCONST.VORGANGSART_EK_KONTRAKT)?EnumTableTranslator.VKOPF_KON_EK:EnumTableTranslator.VKOPF_KON_VK);
	}

	
	public static String getKey() {
		return TL_MASK_DaughterListForMotherMaskClassicType.keyForMotherMask;
	}
	
}
