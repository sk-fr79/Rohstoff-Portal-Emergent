package panter.gmbh.Echo2.ListAndMask.ComponentExtender;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

/**
 * definiert einen container, der komponenten vom typ formatable besitzt. 
 * der container besitzt dann automatisch eine moeglichkeit, diese formatierung auszufuehren
 * @author martin
 *
 */
public interface IF_Formatable_Container {

	public Vector<IF_Formatable> get_formatables() throws myException;
	
	public default void execute_format_formateable_members() throws myException {
		for (IF_Formatable formatable: this.get_formatables()) {
			formatable.execute_format();
		}
	}
	
}
