/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 02.03.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 02.03.2020
 *
 */
public class PdServiceAdressCurrencyV2 extends PdServiceAdressCurrency {

	/**
	 * @author martin
	 * @date 02.03.2020
	 *
	 * @param idAdress
	 * @throws myException
	 */
	public PdServiceAdressCurrencyV2(Long idAdress) throws myException {
		super(idAdress);
	}
	
	
	/**
	 * neue implementierung, wandelt lieferadressen automatisch in hauptadressen
	 */
	public Rec21_adresse getRecAdress() throws myException {
		Rec21_adresse adresse = super.getRecAdress();
		
		if (adresse!=null && adresse.is_ExistingRecord()) {
			if (adresse.__is_liefer_adresse()) {
				adresse = adresse._getMainAdresse();
			}
		}
		return adresse;
	}


}
