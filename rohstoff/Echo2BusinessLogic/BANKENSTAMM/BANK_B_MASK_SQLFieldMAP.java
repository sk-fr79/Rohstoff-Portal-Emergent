package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.exceptions.myException;

public class BANK_B_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public BANK_B_MASK_SQLFieldMAP(BANK_MASK_SQLFieldMAP oFieldMapMother) throws myException 
	{
		super("JT_BANKENSTAMM",":ID_BANKENSTAMM:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:", false);
	
		this.add_SQLField(new SQLFieldJoinOutside(		"JT_BANKENSTAMM",
														"ID_ADRESSE",
														"ID_ADRESSE",
														new MyE2_String("ID-Adresse"),
														false,
														bibE2.get_CurrSession(),
														oFieldMapMother.get_("ID_ADRESSE")), false);
		
		this.initFields();
	}

}
