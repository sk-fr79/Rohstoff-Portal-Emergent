package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_P_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSRG_P_LIST_SQLFieldMAP(BS__SETTING oBS_Setting) throws myException 
	{
		super("JT_VPOS_RG", ":ID_VKOPF_RG:", false);
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_RG","ID_VKOPF_RG","ID_VKOPF_RG",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);

		/*
		 * defaultwerte setzen
		 */
		//this.get_("POSITIONSKLASSE").set_cDefaultValueFormated(myCONST.VG_POSITIONSKLASSE_BELEGPOSITION);
		this.get_("POSITION_TYP").set_cDefaultValueFormated(myCONST.VG_POSITION_TYP_ARTIKEL);
		
		if (oBS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG))
			this.get_("LAGER_VORZEICHEN").set_cDefaultValueFormated("-1");            // rechnung ist per standard warenausgang
		else
			this.get_("LAGER_VORZEICHEN").set_cDefaultValueFormated("1");            // gutschrift ist per standard wareneingang
		
		/*
		 * must-values
		 */
		this.get_("POSITION_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("WAEHRUNGSKURS").set_cDefaultValueFormated("1");
		this.get_("IST_SONDERPOSITION").set_cDefaultValueFormated("N");

		
		// standard-sortierung nach positionsnummer
		this.add_ORDER_SEGMENT("JT_VPOS_RG.POSITIONSNUMMER");
		
		this.initFields();
	}

}
