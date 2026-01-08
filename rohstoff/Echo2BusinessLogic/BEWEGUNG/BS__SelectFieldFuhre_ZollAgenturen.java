package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class BS__SelectFieldFuhre_ZollAgenturen extends MyE2_DB_SelectField
{

	public BS__SelectFieldFuhre_ZollAgenturen(SQLField osqlField, int iWidth) throws myException
	{
		super(osqlField,"SELECT KURZBEZEICHNUNG, ID_ZOLLAGENTUR  FROM "+bibE2.cTO()+".JT_ZOLLAGENTUR ORDER BY KURZBEZEICHNUNG",false,false);
		this.setWidth(new Extent(iWidth));

	}

}
