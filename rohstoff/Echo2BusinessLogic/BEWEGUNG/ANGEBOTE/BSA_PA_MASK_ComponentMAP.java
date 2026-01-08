package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.MAP_Validator_CheckCorrectDateRange;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


/*
 * zusatzfelder des 1x1-tabelle jt_vpos_std_angebot
 */
public class BSA_PA_MASK_ComponentMAP extends E2_ComponentMAP {

	public BSA_PA_MASK_ComponentMAP(SQLFieldMAP			 		oSQLFieldMap_VPOS_STD) throws myException 
	{
		
		super(new BSA_PA_MASK_SQLFieldMAP(oSQLFieldMap_VPOS_STD));
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_STD_ANGEBOT")),			new MyE2_String("ID (VPOS_STD_ANGEBOT)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_STD")),					new MyE2_String("ID (VPOS_STD)"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_VON")),	new MyE2_String("gültig von"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_BIS")),	new MyE2_String("gültig bis"));

		// zwei buttons um in der maske die zeitraeume zu setzen
		this.add_Component(BSA__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH,new BSA_P_MASK_BT_SetDateRange(true),new MyE2_String("Aktuellen Monat besetzen"));
		this.add_Component(BSA__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH,new BSA_P_MASK_BT_SetDateRange(false),new MyE2_String("Nächsten Monat besetzen"));

		
		/*
		 * Feldgroessen
		 */
		((MyE2_DB_TextField)this.get("ID_VPOS_STD_ANGEBOT")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VPOS_STD_ANGEBOT")).set_iWidthPixel(80);
		

		/*
		 * abgeschaltete felder
		 */
		((MyE2_DB_TextField)this.get("ID_VPOS_STD_ANGEBOT")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VPOS_STD")).EXT().set_bDisabledFromBasic(true);

		this.add_oMAPValidator(new BSA_PA_MapValidator_DatesNotNULL_ARTIKEL_POS());
		this.add_oMAPValidator(new MAP_Validator_CheckCorrectDateRange("GUELTIG_VON","GUELTIG_BIS"));
		
	}
	
	
}
