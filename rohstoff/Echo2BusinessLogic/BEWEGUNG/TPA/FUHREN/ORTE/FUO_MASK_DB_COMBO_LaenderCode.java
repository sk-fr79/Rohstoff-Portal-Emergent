package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FUO_MASK_DB_COMBO_LaenderCode extends MyE2_DB_ComboBoxErsatz
{
	public FUO_MASK_DB_COMBO_LaenderCode(SQLField osqlField) throws myException
	{
		super(	osqlField, 
				"SELECT LAENDERCODE FROM "+bibE2.cTO()+".JD_LAND ORDER BY LAENDERCODE ", true);
		
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(200),null,new Integer(40));
		this.set_bTextFieldIsWriteable(false);
	}
	
}
