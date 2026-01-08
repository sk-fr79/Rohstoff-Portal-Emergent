package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.ENUM_BEWGUNGSATZ_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.__FU_FUO_Col_With_Attach_and_Befundung;

public class __FU_FUO_COL_ANHAENGE_BEFUNDUNGEN extends  __FU_FUO_Col_With_Attach_and_Befundung {

	public __FU_FUO_COL_ANHAENGE_BEFUNDUNGEN(SQLField sqlField, ENUM_BEWGUNGSATZ_TYP mytype, E2_NavigationList navilist) throws myException {
		super(sqlField, mytype, navilist);
	}

}
