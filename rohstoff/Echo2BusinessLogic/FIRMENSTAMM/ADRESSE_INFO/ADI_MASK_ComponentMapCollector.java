
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;


import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;



public class ADI_MASK_ComponentMapCollector extends RB_ComponentMapCollector {

	private RECORD_ADRESSE rec_adresse = null;

	public ADI_MASK_ComponentMapCollector(RECORD_ADRESSE  p_rec_adresse) throws myException {
		super();
		this.rec_adresse = p_rec_adresse;

		this.registerComponent(new ADI__TableKey(), new ADI_MASK_ComponentMap(this.rec_adresse));
	}
}
