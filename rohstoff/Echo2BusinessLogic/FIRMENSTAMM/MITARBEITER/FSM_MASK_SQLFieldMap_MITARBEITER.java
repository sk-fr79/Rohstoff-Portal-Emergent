package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSM_MASK_SQLFieldMap_MITARBEITER extends SQLFieldMAP
{
	// beschraenkungsfeld fuer das definieren der basis-adresse
	
	private SQLFieldForRestrictTableRange oRestrictBase_ID_ADRESSE_BASIS = null;


	public FSM_MASK_SQLFieldMap_MITARBEITER(FSM_MASK_SQLFieldMap_ADRESSE SQLFieldMAP_Adresse) throws myException
	{
		super("JT_MITARBEITER",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_MITARBEITER",":ID_MITARBEITER:ID_ADRESSE_MITARBEITER:ID_ADRESSE_BASIS:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_MITARBEITER","ID_MITARBEITER","ID_MITARBEITER",new MyE2_String("ID-MITARBEITER"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MITARBEITER.NEXTVAL FROM DUAL",true), false);

		this.oRestrictBase_ID_ADRESSE_BASIS = new SQLFieldForRestrictTableRange("JT_MITARBEITER","ID_ADRESSE_BASIS","ID_ADRESSE_BASIS",new MyE2_String("ID-Basis-Adresse"),"NULL",bibE2.get_CurrSession());
		
		this.add_SQLField(this.oRestrictBase_ID_ADRESSE_BASIS, false);
		
		this.add_SQLField(new SQLFieldJoinOutside(		"JT_MITARBEITER",
														"ID_ADRESSE_MITARBEITER",
														"ID_ADRESSE_MITARBEITER",
														new MyE2_String("ID-Adresse Mitarbeiter"),
														false,
														bibE2.get_CurrSession(),
														SQLFieldMAP_Adresse.get_("ID_ADRESSE")), false);

		
		this.initFields();
	}


	public SQLFieldForRestrictTableRange get_oRestrictBase_ID_ADRESSE_BASIS()
	{
		return oRestrictBase_ID_ADRESSE_BASIS;
	}

	
	
}
