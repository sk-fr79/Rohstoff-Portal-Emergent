package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.RB_ComboBoxErsatz;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_Combobox_user extends RB_ComboBoxErsatz{

	public KFIX_K_M_Combobox_user(IF_Field sqlF) throws myException {
		super(sqlF, 
				new SEL()
				.ADDFIELD("NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')',NVL(VORNAME,'-')||' '|| NVL(NAME1,'-')", "B")
				.ADDFIELD(USER.id_user.fieldName(), "A")
				.FROM(_TAB.user)
				.WHERE(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(new vgl(USER.ist_verwaltung,"Y"))
				.AND(new vgl(USER.aktiv,"Y"))
				.ORDERUP(USER.name1).s());
		
		this._width(240);
		
		this.get_oPopUp().set_oIconInactiv(KFIX___CONST.IKON.EMPTY.getIkon());
		
//		String a = "NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')',NVL(VORNAME,'-')||' '|| NVL(NAME1,'-')";
		
	}
}
