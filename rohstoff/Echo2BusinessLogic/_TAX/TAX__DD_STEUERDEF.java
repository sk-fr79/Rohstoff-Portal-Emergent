package rohstoff.Echo2BusinessLogic._TAX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField_LabelInList;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.TAX_DATATOVIEW_ACTIVE;
import rohstoff.Echo2BusinessLogic._TAX.RATE.TAX_DATATOVIEW_INACTIVE;

public class TAX__DD_STEUERDEF extends MyE2_DB_SelectField_LabelInList
{

	public TAX__DD_STEUERDEF(SQLField osqlField, int iWidth, boolean bUseInList) throws myException
	{
		super(osqlField,new TAX_DATATOVIEW_ACTIVE(true),new Extent(iWidth));
		this.set_odataToViewShadow(new TAX_DATATOVIEW_INACTIVE(true));

		this.set_bUseInList(bUseInList);
	}


}
