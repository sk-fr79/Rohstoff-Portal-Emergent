/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 13.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 13.03.2019
 *
 */
public enum EnBesitzerTyp  implements IF_enumForDb<EnBesitzerTyp> {
	
	MANDANT
	,QUELLFIRMA
	,ZIELFIRMA
	,FREMDFIRMA
	;

	
	
	@Override
	public EnBesitzerTyp[] getValues() {
		return EnBesitzerTyp.values();
	}

	@Override
	public String userTextLang() {
		String ret = null;
		switch (this) {
		case FREMDFIRMA:
			ret = "Fremdfirma";
			break;
		case MANDANT:
			try {
				ret = ((Rec21_adresse)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2);
			} catch (myException e) {
				ret = "Firma des Mandanten";
			}
			break;
		case QUELLFIRMA:
			ret = "Quellfirma";
			break;
		case ZIELFIRMA:
			ret = "Zielfirma";
			break;
		}
		
		return ret;
	}

}
