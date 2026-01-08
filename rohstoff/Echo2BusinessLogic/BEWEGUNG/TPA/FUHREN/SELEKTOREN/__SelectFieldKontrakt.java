package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class __SelectFieldKontrakt extends MyE2_SelectField
{
	public __SelectFieldKontrakt() throws myException
	{
		super();
		String[][] cLeer = {{"-",""}};
		this.set_ListenInhalt(cLeer,false);
		this.setWidth(new Extent(100));
	}

}
