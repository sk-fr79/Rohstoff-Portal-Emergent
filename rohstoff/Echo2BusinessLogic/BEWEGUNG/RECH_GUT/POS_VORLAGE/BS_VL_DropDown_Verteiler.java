package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class BS_VL_DropDown_Verteiler extends MyE2_DB_SelectField
{

	public BS_VL_DropDown_Verteiler(SQLField osqlField) throws myException
	{
		super(osqlField, BS_VL_CONST.VERTEILER_VARIANTEN, true);
	}

}
