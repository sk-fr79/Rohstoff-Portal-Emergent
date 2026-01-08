package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;

public class FUO_Ermittle_EK_VK
{
	private FUO_MASK_ComponentMAP  MAP = null;

	public FUO_Ermittle_EK_VK(E2_ComponentMAP mAP) throws myException
	{
		super();
		
		if (mAP instanceof FUO_MASK_ComponentMAP )
		{
			MAP = (FUO_MASK_ComponentMAP)mAP;
		}
		else
		{
			throw new myException(this,"False Type of ComponentMAP");
		}
	}
	
	public boolean is_EK() throws myException
	{
		return ((SQLFieldForRestrictTableRange)this.MAP.get_oSQLFieldMAP().get_SQLField(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__DEF_QUELLE_ZIEL)).get_cRestictionValue_IN_DB_FORMAT().equals("'EK'");
	}
	
	
	
}
