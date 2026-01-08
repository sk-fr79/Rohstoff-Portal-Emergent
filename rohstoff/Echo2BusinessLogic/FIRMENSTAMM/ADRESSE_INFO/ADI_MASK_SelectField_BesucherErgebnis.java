package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS.BESUCHSERGEBNIS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class ADI_MASK_SelectField_BesucherErgebnis extends RB_SelectField implements IF_RB_Component_Savable {

	public ADI_MASK_SelectField_BesucherErgebnis(IF_Field field) throws myException {
		super(field, new Extent(200));
		
		String cbQuery = new SEL(BESUCHSERGEBNIS.kurzbezeichnung)
				.ADDFIELD(BESUCHSERGEBNIS.id_besuchsergebnis)
				.FROM(_TAB.besuchsergebnis)
				.WHERE(new vgl(BESUCHSERGEBNIS.id_mandant,  bibALL.get_ID_MANDANT())).s();
		
		populateCombobox(cbQuery, null, false, true, true, true);
	}
}
