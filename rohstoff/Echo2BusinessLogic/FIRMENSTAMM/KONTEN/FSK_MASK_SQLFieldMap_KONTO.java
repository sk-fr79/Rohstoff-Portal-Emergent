package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSK_MASK_SQLFieldMap_KONTO extends SQLFieldMAP
{

	
	public FSK_MASK_SQLFieldMap_KONTO() throws myException
	{
		super("JT_KONTO",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_KONTO",":ID_KONTO:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_KONTO","ID_KONTO","ID_KONTO",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_KONTO.NEXTVAL FROM DUAL",true), false);
		
		
//		this.add_SQLField(new SQLField(KONTO.id_bankenstamm.tnfn(), "ID_BANKENSTAMM2", new MyE2_String("ID-Bank")), false);
		
		/*
		 * muss-felder deklarieren
		 */
		//this.get_("KONTONUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_BANKENSTAMM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		

		
		
		/*
		 * restrict: id_adresse - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_KONTO","ID_ADRESSE","ID_ADRESSE",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);

		this.initFields();
	}

}
