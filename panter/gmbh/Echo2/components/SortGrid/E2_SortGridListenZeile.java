package panter.gmbh.Echo2.components.SortGrid;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_SortGridListenZeile
{

	public abstract Component[] 			get_KomponentenZeile() 			throws myException;				
	public abstract boolean 				get_bZeileIstSichtbar()		 	throws myException;
	@SuppressWarnings("rawtypes")
	public abstract Comparable				get_SortableObject(int iColumn) throws myException;

}
