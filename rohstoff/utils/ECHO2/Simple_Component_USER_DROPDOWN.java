package rohstoff.utils.ECHO2;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class Simple_Component_USER_DROPDOWN extends MyE2_SelectField 
{

	public Simple_Component_USER_DROPDOWN(boolean bMandantWieBenutzer) throws myException 
	{
		super();
		E2_DropDownSettings ddUser = new E2_DropDownSettings( 	"JD_USER LEFT OUTER JOIN JD_MANDANT ON (JD_USER.ID_MANDANT=JD_MANDANT.ID_MANDANT)", 
																"  NVL(JD_USER.NAME1,'-')||' '||NVL(JD_USER.VORNAME,'-')||'    <<'||NVL(JD_MANDANT.NAME1,'-')||'>> ('|| NVL(JD_USER.KUERZEL,'-')||')'", 
																"ID_USER",
																bMandantWieBenutzer?"ID_MANDANT="+bibALL.get_ID_MANDANT():"", null, true, "JD_USER.NAME1");
		this.set_ListenInhalt(ddUser.getDD(), false);
	}

}
