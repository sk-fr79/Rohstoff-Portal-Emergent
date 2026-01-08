package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

/**
 * Abgeleitetes Selectfield, um von der Maske auf die Daten in der Selectbox zugreifen zu können
 * @author manfred
 *
 */

public class WK_RB_SelField_Lager extends MyE2_SelectField {


	private static final long serialVersionUID = 8254609624758340752L;

	public WK_RB_SelField_Lager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WK_RB_SelField_Lager(String cSQLQueryForLIST,boolean bThirdColumnISSTANDARDMARKER, boolean bEmtyValueInFront, boolean bValuesFormated, boolean btranslate) 
	throws myException {
		super(cSQLQueryForLIST, bThirdColumnISSTANDARDMARKER, bEmtyValueInFront,
				bValuesFormated, btranslate);
		// TODO Auto-generated constructor stub
	}

	public WK_RB_SelField_Lager(String[] aDefArray, String cdefaultValue,
			boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		// TODO Auto-generated constructor stub
	}

	public WK_RB_SelField_Lager(String[][] aDefArray, String cdefaultValue,
			boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		// TODO Auto-generated constructor stub
	}

}
