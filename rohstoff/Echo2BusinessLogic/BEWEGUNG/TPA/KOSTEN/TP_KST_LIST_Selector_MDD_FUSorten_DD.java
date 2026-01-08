package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_Selector_MDD_FUSorten_DD extends MyE2_SelectField {

	public TP_KST_LIST_Selector_MDD_FUSorten_DD() throws myException {
		super(new Extent(250));
		
		this.populateCombobox(new TP_KST__SEL_FUSorten().s(), null, false, true, false, false);
		
	}

}
