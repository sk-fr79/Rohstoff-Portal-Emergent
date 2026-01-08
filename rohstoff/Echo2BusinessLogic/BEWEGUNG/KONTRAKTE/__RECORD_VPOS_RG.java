package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;

public class __RECORD_VPOS_RG extends RECORD_VPOS_RG
{

	public __RECORD_VPOS_RG(RECORD_VPOS_RG recordOrig)
	{
		super(recordOrig);
	}


	/**
	 * @return die nettomenge oder 0
	 * @throws myException
	 */
	public BigDecimal  _get_ANZAHL_NETTO_bd() throws myException
	{
		return this.get_ANZAHL_bdValue(new BigDecimal(0)).subtract(this.get_ANZAHL_ABZUG_LAGER_bdValue(new BigDecimal(0)));
	}
	
	
}
