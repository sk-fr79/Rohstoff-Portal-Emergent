package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.exceptions.myException;

public class BS__DropDownSelectorLieferbedingungen extends E2_DropDownSettingsNew {

	/**
	 * 
	 * @param bDoAnEmpty_ValueToList
	 * @param QueryFormated
	 * @throws myException
	 */
	public BS__DropDownSelectorLieferbedingungen(boolean bDoAnEmpty_ValueToList,boolean  QueryFormated) throws myException {
		super(	"SELECT KURZBEZEICHNUNG AS A,ID_LIEFERBEDINGUNGEN AS B FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN WHERE NVL(AKTIV,'N')='Y' ORDER BY KURZBEZEICHNUNG", 
				bDoAnEmpty_ValueToList,QueryFormated);
	}
		
}
