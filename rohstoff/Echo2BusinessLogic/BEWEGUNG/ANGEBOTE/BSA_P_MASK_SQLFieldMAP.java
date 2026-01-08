package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;

public class BSA_P_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSA_P_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_STD", ":ID_VKOPF_STD:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_STD","ID_VKOPF_STD","ID_VKOPF_STD",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);

		/*
		 * defaultwerte setzen
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this,"0");
		
		this.initFields();
	}

}
