package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSL_MASK_SQLFieldMap_LIEFERADRESSE extends SQLFieldMAP
{


	public FSL_MASK_SQLFieldMap_LIEFERADRESSE(FSL_MASK_SQLFieldMap_ADRESSE SQLFieldMAP_Adresse) throws myException
	{
		super("JT_LIEFERADRESSE",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_LIEFERADRESSE",":ID_LIEFERADRESSE:ID_ADRESSE_LIEFER:ID_ADRESSE_BASIS:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_LIEFERADRESSE","ID_LIEFERADRESSE","ID_LIEFERADRESSE",new MyE2_String("ID-Lieferadresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_LIEFERADRESSE.NEXTVAL FROM DUAL",true), false);

		SQLFieldForRestrictTableRange oRestrictBase_ID_ADRESSE_BASIS = new SQLFieldForRestrictTableRange("JT_LIEFERADRESSE","ID_ADRESSE_BASIS","ID_ADRESSE_BASIS",new MyE2_String("ID-Basis-Adresse"),"NULL",bibE2.get_CurrSession());
		
		this.add_SQLField(oRestrictBase_ID_ADRESSE_BASIS, false);
		
		this.add_SQLField(new SQLFieldJoinOutside(		"JT_LIEFERADRESSE",
														"ID_ADRESSE_LIEFER",
														"ID_ADRESSE_LIEFER",
														new MyE2_String("ID der Lieferadresse"),
														false,
														bibE2.get_CurrSession(),
														SQLFieldMAP_Adresse.get_("ID_ADRESSE")), false);

		
		
		this.initFields();
	}


	
}
