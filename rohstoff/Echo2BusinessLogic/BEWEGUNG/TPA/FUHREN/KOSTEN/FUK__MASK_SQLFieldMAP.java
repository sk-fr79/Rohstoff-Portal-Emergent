package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FUK__MASK_SQLFieldMAP extends Project_SQLFieldMAP
{
	private SQLFieldForRestrictTableRange oRestrictFieldFuhre = null;
	
	public FUK__MASK_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_KOSTEN", FUK__LIST_BasicModule_Inlay.CONNECTION_FIELD, false);

		/*
		 * restrict: id_adresse_basis - je nach situation
		 */
		this.oRestrictFieldFuhre =new SQLFieldForRestrictTableRange("JT_VPOS_TPA_FUHRE_KOSTEN",FUK__LIST_BasicModule_Inlay.CONNECTION_FIELD,FUK__LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()); 
		this.add_SQLField(this.oRestrictFieldFuhre, false);


		this.initFields();
	}

	
	public void set_ID_Fuhre_Restrict(String cID_Fuhre_UF) throws myException 
	{
		this.oRestrictFieldFuhre.set_cRestrictionValue_IN_DB_FORMAT(cID_Fuhre_UF);
	}
	
}
