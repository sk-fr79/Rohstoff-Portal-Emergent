package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS.AKTIONSANLASS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class ADI_MASK_SelectField_Anlass extends RB_SelectField implements IF_RB_Component_Savable {

	public ADI_MASK_SelectField_Anlass(IF_Field field) throws myException {
		
		super(field, new Extent(200));
		
		String cbQuery = new SEL(AKTIONSANLASS.kurzbezeichnung)
				.ADDFIELD(AKTIONSANLASS.id_aktionsanlass)
				.FROM(_TAB.aktionsanlass)
				.WHERE(new vgl(AKTIONSANLASS.id_mandant,  bibALL.get_ID_MANDANT())).s();
		
		populateCombobox(cbQuery, null, false, true, true, true);
	}
}
