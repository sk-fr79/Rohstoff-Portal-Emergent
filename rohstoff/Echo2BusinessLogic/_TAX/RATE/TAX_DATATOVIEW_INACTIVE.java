package rohstoff.Echo2BusinessLogic._TAX.RATE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class TAX_DATATOVIEW_INACTIVE extends dataToView
{

	private static final String cSQL = "SELECT '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '||DROPDOWN_TEXT,ID_TAX FROM "+
	bibE2.cTO()+".JT_TAX WHERE NVL("+_DB.TAX$AKTIV+",'N')='N'  ORDER BY 1";

	public TAX_DATATOVIEW_INACTIVE(boolean bFormated) throws myException
	{
		super((	bFormated?
				bibDB.EinzelAbfrageInArrayFormatiert(TAX_DATATOVIEW_INACTIVE.cSQL, "")
				:
				bibDB.EinzelAbfrageInArray(TAX_DATATOVIEW_INACTIVE.cSQL, ""))		,false, bibE2.get_CurrSession());


	}
}


