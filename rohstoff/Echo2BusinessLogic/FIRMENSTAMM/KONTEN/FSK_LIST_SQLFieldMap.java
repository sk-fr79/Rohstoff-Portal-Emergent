package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSK_LIST_SQLFieldMap extends SQLFieldMAP
{

	
	public FSK_LIST_SQLFieldMap() throws myException
	{
		super("JT_KONTO",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_KONTO",":ID_KONTO:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_KONTO","ID_KONTO","ID_KONTO",new MyE2_String("ID-Konto"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_KONTO.NEXTVAL FROM DUAL",true), false);
	
	
		this.addCompleteTable_FIELDLIST("JT_BANKENSTAMM",":BANKLEITZAHL:SWIFTCODE:BEMERKUNGEN:",true,true, "U_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_BANKENSTAMM","ID_BANKENSTAMM","U_ID_BANKENSTAMM",new MyE2_String("ID-Bankenstamm"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_BANKENSTAMM.NEXTVAL FROM DUAL",true), false);

		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":NAME1:ORT:",true,true, "A_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","A_ID_ADRESSE",new MyE2_String("ID-Adresse (Bank)"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE.NEXTVAL FROM DUAL",true), false);

		
		/*
		 * connect-felder
		 */
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_KONTO.ID_BANKENSTAMM=JT_BANKENSTAMM.ID_BANKENSTAMM"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_BANKENSTAMM.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE"));

		
		/*
		 * restrict: id_KONTO_basis - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_KONTO","ID_ADRESSE","ID_ADRESSE",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);

		this.initFields();
	}

}
