package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class ADI_MASK_SelectField_Betreuer extends RB_SelectField implements IF_RB_Component_Savable {

	public ADI_MASK_SelectField_Betreuer(IF_Field field) throws myException {
		super(field, new Extent(200));
		
		String aktivQuery = new SEL(USER.vorname + "||' '||" + USER.name1 + ","+ USER.id_user)
				.FROM(_TAB.user)
				.WHERE(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(new vgl(USER.ist_verwaltung,"Y"))
				.AND(new vgl(USER.aktiv,"Y"))
				.ORDERUP(USER.name1)
				.s();
		
		String inaktivQuery = new SEL(USER.vorname+ "||' '||" + USER.name1+","+ USER.id_user)
				.FROM(_TAB.user)
				.WHERE(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(new vgl(USER.ist_verwaltung,"Y"))
				.AND(new vgl(USER.aktiv,"N"))
				.ORDERUP(USER.name1)
				.s();
		
		populateCombobox(aktivQuery, inaktivQuery, false, true, true, true);
	}

}
