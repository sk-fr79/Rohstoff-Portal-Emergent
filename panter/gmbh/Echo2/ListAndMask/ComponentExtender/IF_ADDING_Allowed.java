package panter.gmbh.Echo2.ListAndMask.ComponentExtender;

import nextapp.echo2.app.Component;

public interface IF_ADDING_Allowed
{
	public Component[] 	getComponents();
	public void      	add_raw(Component oComp);
	public void        removeAll();
	
}
