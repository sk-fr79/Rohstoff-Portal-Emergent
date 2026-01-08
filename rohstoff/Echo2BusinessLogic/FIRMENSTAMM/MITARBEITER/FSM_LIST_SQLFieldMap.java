package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSM_LIST_SQLFieldMap extends SQLFieldMAP
{

	
	public FSM_LIST_SQLFieldMap() throws myException
	{
		super("JT_ADRESSE",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":ID_ADRESSE:ADRESSTYP:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE.NEXTVAL FROM DUAL",true), false);
	
		this.addCompleteTable_FIELDLIST("JT_MITARBEITER",	":ID_MITARBEITERTYP:ID_MITARBEITERTYP2:ID_MITARBEITERTYP3:ID_MITARBEITERTYP4:IST_ANSPRECHPARTNER:WEIHNACHTSGESCHENK:SOMMERGESCHENK:" +
															"ASP_ANGEBOT:ASP_RECHNUNG:ASP_GUTSCHRIFT:ASP_TRANSPORT:ASP_EK_KONTRAKT:ASP_VK_KONTRAKT:ASP_ABNAHMEANGEBOT:ASP_FIBU",true,true, "U_");    //NEU_09 zusatzfelder
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_MITARBEITER","ID_MITARBEITER","U_ID_MITARBEITER",new MyE2_String("ID-Mitarbeiter"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MITARBEITER.NEXTVAL FROM DUAL",true), false);

		this.addCompleteTable_FIELDLIST("JT_MITARBEITERTYP",":KURZBEZEICHNUNG:",true,true, "V_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_MITARBEITERTYP","ID_MITARBEITERTYP","V_ID_MITARBEITERTYP",new MyE2_String("Mitarbeitertyp"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MITARBEITERTYP.NEXTVAL FROM DUAL",true), false);

		this.addCompleteTable_FIELDLIST("JT_ANREDE",":ANREDE:",true,true, "W_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ANREDE","ID_ANREDE","W_ID_ANREDE",new MyE2_String("Anrede"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ANREDE.NEXTVAL FROM DUAL",true), false);


		
		/*
		 * connect-felder
		 */
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ADRESSE.ID_ADRESSE=JT_MITARBEITER.ID_ADRESSE_MITARBEITER"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_MITARBEITER.ID_MITARBEITERTYP=JT_MITARBEITERTYP.ID_MITARBEITERTYP (+)"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ADRESSE.ID_ANREDE=JT_ANREDE.ID_ANREDE (+)"));

		/*
		 * restrict: adresstyp = mitarbeiter
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE","ADRESSTYP","ADRESSTYP",new MyE2_String("Adresstyp"),""+myCONST.ADRESSTYP_MITARBEITER,bibE2.get_CurrSession()), false);
		
		/*
		 * restrict: id_adresse_basis - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_MITARBEITER","ID_ADRESSE_BASIS","ID_ADRESSE_BASIS",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);

		this.initFields();
	}

}
