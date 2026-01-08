package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.COMP.RB_ComboBoxErsatzArea;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_ComboboxErsatz_Lieferbedingungen extends RB_ComboBoxErsatzArea {

	public KFIX_K_M_ComboboxErsatz_Lieferbedingungen(IF_Field field) throws myException {
		super(new RB_SQLField(field), 
				new SEL()
				.ADDFIELD(LIEFERBEDINGUNGEN.kurzbezeichnung.fieldName(), "A")
				.FROM(_TAB.lieferbedingungen)
				.WHERE(new vgl_YN(LIEFERBEDINGUNGEN.aktiv, true))
				.ORDERUP(LIEFERBEDINGUNGEN.kurzbezeichnung).s()
				);

		this.get_oTextArea().set_iRows(3);
		this.get_oTextArea().set_iWidthPixel(394);
	}

	public KFIX_K_M_ComboboxErsatz_Lieferbedingungen(IF_Field field, int iWidth, int iRows) throws myException {
		super(new RB_SQLField(field), 
				new SEL()
				.ADDFIELD(LIEFERBEDINGUNGEN.kurzbezeichnung.fieldName(), "A")
				.FROM(_TAB.lieferbedingungen)
				.WHERE(new vgl_YN(LIEFERBEDINGUNGEN.aktiv, true))
				.ORDERUP(LIEFERBEDINGUNGEN.kurzbezeichnung).s()
				);

		this.get_oTextArea().set_iRows(iRows);
		this.get_oTextArea().set_iWidthPixel(iWidth);
	}
	
}
