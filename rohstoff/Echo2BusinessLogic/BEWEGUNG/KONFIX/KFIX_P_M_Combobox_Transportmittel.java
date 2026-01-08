package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_ComboBoxErsatz;
import panter.gmbh.basics4project.DB_ENUMS.TRANSPORTMITTEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_Combobox_Transportmittel extends RB_ComboBoxErsatz {

	public KFIX_P_M_Combobox_Transportmittel(IF_Field cIfField, int iWidth)
			throws myException {
		super(cIfField, 
				new SEL().ADDFIELD(TRANSPORTMITTEL.beschreibung)
				.FROM(_TAB.transportmittel)
				.ORDERUP(TRANSPORTMITTEL.beschreibung).s()
				, false);
		this.getTextField().setWidth(new Extent(iWidth));
		this.get_oTextField().setWidth(new Extent(iWidth));
	}

}
