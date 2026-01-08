package rohstoff.Echo2BusinessLogic._TAX.RATE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class TAX_DATATOVIEW_ACTIVE extends dataToView
{

//	private static final String cSQL = "SELECT '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '||DROPDOWN_TEXT,ID_TAX FROM "+
//										bibE2.cTO()+".JT_TAX WHERE NVL("+_DB.TAX$AKTIV+",'N')='Y'  ORDER BY 1";

//	private static final String cSQL = "SELECT "
//			+ " (CASE WHEN (STEUERSATZ_NEU IS NULL) "
//			+ "       THEN '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '||DROPDOWN_TEXT "
//			+ "       ELSE '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||'/'||TO_CHAR(STEUERSATZ_NEU,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '    ||DROPDOWN_TEXT END"
//			+ " ) AS TEXT ,"
//			+ " ID_TAX FROM "+bibE2.cTO()+".JT_TAX WHERE NVL(AKTIV,'N')='Y'  ORDER BY 1";
	
	
	//2020-11-16: neue subtabellen-info anzeigen
	private static final String cSQL = "SELECT DROPDOWN_TEXT"
											+ " ||' '|| (CASE   WHEN ( STEUERSATZ_NEU IS NULL)"
															+ " THEN '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '"
															+ " ELSE '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||'/'||TO_CHAR(STEUERSATZ_NEU,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '  END )"
												+ " || (CASE WHEN ((SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_TAX_AENDERUNGEN WHERE JT_TAX_AENDERUNGEN.ID_TAX = JT_TAX.ID_TAX)>0)"
														+  " THEN  ' / **AUSNAHMEN** ' "
														+  " ELSE ''  END)"
												+ " ||'  (ID:'||TO_CHAR(JT_TAX.ID_TAX)||')'  AS TEXT "
										+ ",   ID_TAX FROM  "+bibE2.cTO()+".JT_TAX WHERE NVL(AKTIV,'N')='Y' ORDER BY  1";
	
	public TAX_DATATOVIEW_ACTIVE(boolean bFormated) throws myException
	{
		super((bFormated?
				bibDB.EinzelAbfrageInArrayFormatiert(TAX_DATATOVIEW_ACTIVE.cSQL, "")
				:
				bibDB.EinzelAbfrageInArray(TAX_DATATOVIEW_ACTIVE.cSQL, ""))		,false, bibE2.get_CurrSession());
		
		//leerer Wert in Front
		this.addPairOfValues("-", "", true);
		
	}

}
