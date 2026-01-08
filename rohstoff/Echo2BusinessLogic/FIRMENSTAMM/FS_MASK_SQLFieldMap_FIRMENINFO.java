package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_MASK_SQLFieldMap_FIRMENINFO extends SQLFieldMAP
{
	
	private FS_MASK_SQLFieldMap_ADRESSE oSQLFieldMAP_Adresse = null; 
	

	public FS_MASK_SQLFieldMap_FIRMENINFO(FS_MASK_SQLFieldMap_ADRESSE SQLFieldMAP_Adresse) throws myException
	{
		super("JT_FIRMENINFO",bibE2.get_CurrSession());
		this.oSQLFieldMAP_Adresse = SQLFieldMAP_Adresse;
		
		this.addCompleteTable_FIELDLIST("JT_FIRMENINFO",":ID_FIRMENINFO:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_FIRMENINFO","ID_FIRMENINFO","ID_FIRMENINFO",new MyE2_String("ID-Firmeninfo"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FIRMENINFO.NEXTVAL FROM DUAL",true), false);

		this.add_SQLField(new SQLFieldJoinOutside(		"JT_FIRMENINFO",
														"ID_ADRESSE",
														"ID_ADRESSE",
														new MyE2_String("ID-Adresse"),
														false,
														bibE2.get_CurrSession(),
														this.oSQLFieldMAP_Adresse.get_("ID_ADRESSE")), false);

		this.initFields();
	}

}
