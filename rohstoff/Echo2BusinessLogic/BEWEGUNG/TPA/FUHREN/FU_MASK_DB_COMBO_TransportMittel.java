package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_DB_COMBO_TransportMittel extends MyE2_DB_ComboBoxErsatz
{

	public FU_MASK_DB_COMBO_TransportMittel(SQLField osqlField) throws myException
	{
		super(osqlField, "SELECT BESCHREIBUNG FROM "+bibE2.cTO()+".JT_TRANSPORTMITTEL ORDER BY BESCHREIBUNG", false);
		this.get_oTextField().set_iWidthPixel(95);
		this.get_oTextField().setFont(new E2_FontPlain(-2));
		
	}

}
