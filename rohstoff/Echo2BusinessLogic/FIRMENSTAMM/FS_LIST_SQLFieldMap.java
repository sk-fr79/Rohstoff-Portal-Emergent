package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_LIST_SQLFieldMap extends SQLFieldMAP
{

	
	public FS_LIST_SQLFieldMap() throws myException
	{
		super("JT_ADRESSE",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":ID_ADRESSE:ADRESSTYP:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE.NEXTVAL FROM DUAL",true), false);
	
		//2011-01-25: weiteres ID_ADRESSE-Feld beschaffen
//		this.addCompleteTable_FIELDLIST("JT_FIRMENINFO",":ID_FIRMENINFO:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ABLADEMENGE_FUER_GUTSCHRIFT:LADEMENGE_FUER_RECHNUNG:VERLAENGERT_EIGENTUM_VORBEHALT:FORDERUNGSVERRECHNUNG:BESCHREIBUNG:",false,true, "F_");
		this.addCompleteTable_FIELDLIST("JT_FIRMENINFO",":ID_FIRMENINFO:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ABLADEMENGE_FUER_GUTSCHRIFT:LADEMENGE_FUER_RECHNUNG:VERLAENGERT_EIGENTUM_VORBEHALT:FORDERUNGSVERRECHNUNG:BESCHREIBUNG:KREDITBETRAG_INTERN:KREDITBETRAG_INTERN_VALID_TO:",false,true, "F_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_FIRMENINFO","ID_FIRMENINFO","F_ID_FIRMENINFO",new MyE2_String("ID-Firmeninfo"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FIRMENINFO.NEXTVAL FROM DUAL",true), false);

		this.addCompleteTable_FIELDLIST("JD_USER",":NAME:",true,true, "U_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JD_USER","ID_USER","U_ID_USER",new MyE2_String("ID-User"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_USER.NEXTVAL FROM DUAL",true), false);

		this.addCompleteTable_FIELDLIST("JD_LAND",":LAENDERNAME:",true,true, "L_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JD_LAND","ID_LAND","L_ID_LAND",new MyE2_String("ID-Land"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_LAND.NEXTVAL FROM DUAL",true), false);
		
		this.add_SQLField(new SQLField("JT_FIRMENINFO.UMSATZSTEUERLKZ||' '||JT_FIRMENINFO.UMSATZSTEUERID", "UST_LKZ_ID", new MyE2_String("UST-ID"), bibE2.get_CurrSession()),true);
		
		/*
		 * connect-felder
		 */
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ADRESSE.ID_USER=JD_USER.ID_USER (+)"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ADRESSE.ID_LAND=JD_LAND.ID_LAND (+)"));

		/*
		 * restrict: adresstyp = 1
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE","ADRESSTYP","ADRESSTYP",new MyE2_String("Adresstyp"),""+myCONST.ADRESSTYP_FIRMENINFO,bibE2.get_CurrSession()), false);
		
		/*
		 * zusatzfeld fuer die markierungsspalte 
		 */
		this.add_SQLField(new SQLField(FS_CONST.SQL_FIELDS.ID2),true);
		
		//2017-02-10: zusatzfeld fuer die einblendung der benutzer-webseiten
		this.add_SQLField(new SQLField(FS_CONST.SQL_FIELDS.ID3),true);
		
		
		
		this.initFields();
	}

}
