package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_Create_ZW_Umbuchung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class _WE_Umbuchung extends FZ_Create_ZW_Umbuchung {

	public _WE_Umbuchung(RB_DataobjectsCollector_V2 doCollector, MyE2_MessageVector mv) throws myException {
		super(doCollector, mv);
	}

	@Override
	public String get_position_nummer_umbuchungsatom_links() throws myException {
		return "100";
	}

	@Override
	public String get_position_nummer_umbuchungsatom_recht() throws myException {
		return "200";
	}

	@Override
	public ENUM_VEKTORPOS_TYP get_vektorPos_typ() throws myException {
		return ENUM_VEKTORPOS_TYP.WE_UMB;
	}

	@Override
	public ENUM_SONDERLAGER get_umbunchung_sonderlager() throws myException {
		return ENUM_SONDERLAGER.UH;
	}

}
