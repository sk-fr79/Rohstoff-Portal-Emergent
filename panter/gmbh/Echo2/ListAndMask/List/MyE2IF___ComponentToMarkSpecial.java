package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

import nextapp.echo2.app.Component;

/**
 * interface fuer die komponenten, die in der liste mot setForeground nicht markiert werden koennen, da sie
 * komplexer aufgebaut sind
 * @author martin
 *
 */
public interface MyE2IF___ComponentToMarkSpecial
{
	
	public abstract Vector<Component> get_vComponentsToMarkInList() throws myException;
	
}
