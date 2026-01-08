package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * @author martin
 * implementiert eine methode get_RenderedComponent und kann damit in
 * darstellungsklassen eine andere komponente zurueckgeben als sie selber
 *
 */
public interface MyE2IF__Indirect
{
	public Component    get_RenderedComponent() throws myException;
	public MyE2_Button  get_oErsatzButton() throws myException;
	
}
