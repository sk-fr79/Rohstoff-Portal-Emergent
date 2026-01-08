/**
 * panter.gmbh.Echo2.__BASIC_MODULS.textlisteVorlage
 * @author martin
 * @date 26.03.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;

import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.TextListe.EnumTableTranslator;
import panter.gmbh.Echo2.RB.COMP.TextListe.TL_MASK_DaughterListForMotherMask;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 26.03.2020
 *
 */
public class TLV_MaskComponentTextListe extends TL_MASK_DaughterListForMotherMask implements IF_RbComponentWithOwnKey {

	
	public static RB_KF key = new RB_KF()._setREALNAME(TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn());
	
	/**
	 * @author martin
	 * @date 26.03.2020
	 *
	 * @param p_tpHashMap
	 * @throws myException
	 */
	public TLV_MaskComponentTextListe(RB_TransportHashMap p_tpHashMap) throws myException {
		super(p_tpHashMap, true, "ID-Vorlagen-Tabelle",EnumTableTranslator.TEXT_LISTE_VORLAGE);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey#getFieldKey()
	 */
	@Override
	public RB_KF getFieldKey() {
		return TLV_MaskComponentTextListe.key;
	}

	
	
	
}
