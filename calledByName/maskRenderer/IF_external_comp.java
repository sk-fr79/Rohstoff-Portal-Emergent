package calledByName.maskRenderer;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public interface IF_external_comp<T extends Component> {
	/**
	 * Set the data record
	 * @param rec
	 * @param key
	 * @return
	 * @throws myException
	 */
	public T		_setRec20(Rec20 rec, IF_Field key) 	throws myException;

	/**
	 * set definition record
	 * @param rec
	 * @return
	 * @throws myException
	 */
	public T		_set_definition_rec20(Rec20 rec) 	throws myException;

	
	public default E2_Grid  getRenderedContainer() 	throws myException {
		if (this instanceof Component) {
			E2_Grid grid = new E2_Grid()._s(1)._a((Component)this, new RB_gld()._ins(0));
			return grid;
		}
		throw new myException("Component MUST extent nextapp.echo2.app.Component !");
	}	
}
