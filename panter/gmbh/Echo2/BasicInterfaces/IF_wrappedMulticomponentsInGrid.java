package panter.gmbh.Echo2.BasicInterfaces;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public interface IF_wrappedMulticomponentsInGrid {
	
	//funktionales interface zu nutzung in einem lamba ausdruck
	public E2_Grid grid(Component ... components) throws myException;
	
	
}
