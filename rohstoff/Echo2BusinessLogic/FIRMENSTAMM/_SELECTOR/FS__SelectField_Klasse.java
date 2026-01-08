package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class FS__SelectField_Klasse extends MyE2_SelectField 
{

	public FS__SelectField_Klasse() throws myException 
	{
		super("select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
				false,true,false,false);
		
		this.setWidth(new Extent(100));
	}

}
