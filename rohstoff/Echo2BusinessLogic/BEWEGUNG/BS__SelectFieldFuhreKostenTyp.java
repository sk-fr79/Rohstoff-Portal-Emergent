package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FUHREN_KOSTEN_TYP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class BS__SelectFieldFuhreKostenTyp extends MyE2_DB_SelectField
{

	public BS__SelectFieldFuhreKostenTyp(SQLField osqlField, int iWidth) throws myException
	{
		super(osqlField,"SELECT "+RECORD_FUHREN_KOSTEN_TYP.FIELD__TEXT4BENUTZER+","+RECORD_FUHREN_KOSTEN_TYP.FIELD__ID_FUHREN_KOSTEN_TYP+" " +
				            " FROM "+bibE2.cTO()+".JT_FUHREN_KOSTEN_TYP ORDER BY ID_FUHREN_KOSTEN_TYP",false, false);
		this.setWidth(new Extent(iWidth));
	}

}
