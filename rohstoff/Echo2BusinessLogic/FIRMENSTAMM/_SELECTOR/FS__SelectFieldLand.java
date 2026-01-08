package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class FS__SelectFieldLand extends MyE2_SelectField 
{

	public FS__SelectFieldLand() throws myException 
	{
		super("select laendername,id_land from "+bibE2.cTO()+".jd_land order by laendername",
				false,true,false,false);
		
		this.setWidth(new Extent(100));
	}

}
