package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * @author martin
 * hilfsklasse, macht den austausch der Komponenten in den Rendervorgaengen (z.B. komponente vom Typ MyE2IF__Indirect einlagern in MyE2_Row
 *
 */
public class MyE2IF__IndirectHELPER
{
	
	private Component oCompRueck = null;

	public MyE2IF__IndirectHELPER(Component oCompOriginal) throws myException
	{
		super();
		
		this.oCompRueck = oCompOriginal;
		
		if (oCompOriginal instanceof MyE2IF__Indirect)
		{
			oCompRueck = ((MyE2IF__Indirect)oCompOriginal).get_RenderedComponent();
			oCompRueck.setLayoutData(oCompOriginal.getLayoutData());
			oCompOriginal.setLayoutData(null);
		}
	}

	
	public Component get_oCompRueck()
	{
		return oCompRueck;
	}
	
}
