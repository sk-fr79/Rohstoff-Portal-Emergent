package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FSMS_DB_Select_STATUS_SOLL_IST extends MyE2_DB_SelectField {

	public FSMS_DB_Select_STATUS_SOLL_IST(SQLField osqlField, int iWidth) 	throws myException {
		super(osqlField, new Extent(iWidth));
		
		this.set_ListenInhalt(FSMS_Const.FSMS_Status_DD, false);
	}

}
