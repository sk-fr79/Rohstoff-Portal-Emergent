package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;

public class BST_P_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BST_P_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_TPA", ":ID_VKOPF_TPA:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_TPA","ID_VKOPF_TPA","ID_VKOPF_TPA",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);

		/*
		 * defaultwerte setzen
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this, "0");
		
		this.initFields();
	}

}
