package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange_FIELD_MUST_BE_NULL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSL_LIST_SQLFieldMap extends SQLFieldMAP  {

	
	public static enum FSL_QUERYSTRING {
		
		 ADRESSE_FREMDWARE("Eigentümer (Fremdware)","( SELECT (NVL (A.NAME1,'') ||  NVL2(A.NAME2,' ' || A.NAME2 ,'') ||  NVL2(A.ORT,', ' || A.ORT,'') ) FROM JT_ADRESSE A WHERE A.ID_ADRESSE = JT_LIEFERADRESSE.ID_ADRESSE_FREMDWARE )")
		,ADRESSE_BESITZ_LAGER("Besitzer (Grundstück)","( SELECT (NVL (A.NAME1,'') ||  NVL2(A.NAME2,' ' || A.NAME2 ,'') ||  NVL2(A.ORT,', ' || A.ORT,'') ) FROM JT_ADRESSE A WHERE A.ID_ADRESSE = JT_LIEFERADRESSE.ID_ADRESSE_BESITZ_LAGER )")		
		,ADRESSE_BESITZER_WARE("Besitzer (Ware)","( SELECT (NVL (A.NAME1,'') ||  NVL2(A.NAME2,' ' || A.NAME2 ,'') ||  NVL2(A.ORT,', ' || A.ORT,'') ) FROM JT_ADRESSE A WHERE A.ID_ADRESSE = JT_LIEFERADRESSE.ID_ADRESSE_BESITZER_WARE )")		
		
		;
		private String titelText = null;
		private String queryString = null;
		
		private FSL_QUERYSTRING(String p_titelText, String p_querystring) {
			this.titelText=p_titelText;
			this.queryString = p_querystring;
		}

		public String getQueryString() {
			return queryString;
		}

		public String getLabel() {
			return name();
		}

		/**
		 * @return the titelText
		 */
		public String getTitelText() {
			return titelText;
		}
		
	}
	
	
	public FSL_LIST_SQLFieldMap() throws myException
	{
		super("JT_ADRESSE",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":ID_ADRESSE:ADRESSTYP:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:SONDERLAGER:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE.NEXTVAL FROM DUAL",true), false);
	
		this.addCompleteTable_FIELDLIST("JT_LIEFERADRESSE",":IST_STANDARD:BESCHREIBUNG:OEFFNUNGSZEITEN:ID_ADRESSE_FREMDWARE:",true,true, "U_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_LIEFERADRESSE","ID_LIEFERADRESSE","U_ID_LIEFERADRESSE",new MyE2_String("ID-Lieferadresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_LIEFERADRESSE.NEXTVAL FROM DUAL",true), false);

		

////		// die Adresse des Besitzers der Fremdware
//		String sSQLAdressInfo = "( SELECT ( " +
//								"    NVL (ADR_BESITZER.NAME1,'') || " +
//								"    NVL2(ADR_BESITZER.NAME2,' ' || ADR_BESITZER.NAME2 ,'') || " +
//								"    NVL2(ADR_BESITZER.ORT,', ' || ADR_BESITZER.ORT,'') " +
//								"   ) FROM JT_ADRESSE ADR_BESITZER WHERE ADR_BESITZER.ID_ADRESSE = JT_LIEFERADRESSE.ID_ADRESSE_FREMDWARE ) " ;
//		this.add_SQLField(new SQLField(sSQLAdressInfo, "ADRESS_INFO", new MyString("ADRESS_INFO"), bibE2.get_CurrSession()), false);
		
		
		//die fremd-adressen-lookups einbauen
		this.add_SQLField(new SQLField(FSL_QUERYSTRING.ADRESSE_FREMDWARE.getQueryString(), FSL_QUERYSTRING.ADRESSE_FREMDWARE.getLabel(), S.ms(FSL_QUERYSTRING.ADRESSE_FREMDWARE.getLabel()), bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField(FSL_QUERYSTRING.ADRESSE_BESITZER_WARE.getQueryString(), FSL_QUERYSTRING.ADRESSE_BESITZER_WARE.getLabel(), S.ms(FSL_QUERYSTRING.ADRESSE_BESITZER_WARE.getLabel()), bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField(FSL_QUERYSTRING.ADRESSE_BESITZ_LAGER.getQueryString(), FSL_QUERYSTRING.ADRESSE_BESITZ_LAGER.getLabel(), S.ms(FSL_QUERYSTRING.ADRESSE_BESITZ_LAGER.getLabel()), bibE2.get_CurrSession()), false);

		
		
		//2016-03-23: das land
//		// die Adresse des Besitzers der Fremdware
		String sql_land = "( SELECT ( " +
								"    JD_LAND.LAENDERCODE" +
								"   ) FROM JD_LAND WHERE JD_LAND.ID_LAND = JT_ADRESSE.ID_LAND) " ;
		this.add_SQLField(new SQLField(sql_land, "LAND", new MyString("Land"), bibE2.get_CurrSession()), false);

//		
//		this.add_JOIN_Table(	"JT_ADRESSE", 
//								"ADR_BESITZER", 
//								SQLFieldMAP.LEFT_OUTER, 
//								"", 
//								true, 
//								"JT_LIEFERADRESSE.ID_ADRESSE_FREMDWARE = ADR_BESITZER.ID_ADRESSE", 
//								"", 
//								bibALL.get_HashMap("ADRESSE_BESITZER", sSQLAdressInfo));

	
		/*
		 * connect-felder
		 */
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER"));

		/*
		 * restrict: adresstyp = Lieferadresse
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE","ADRESSTYP","ADRESSTYP",new MyE2_String("Adresstyp"),""+myCONST.ADRESSTYP_LIEFERADRESSE,bibE2.get_CurrSession()), false);
		
		/*
		 * restrict: id_adresse_basis - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_LIEFERADRESSE","ID_ADRESSE_BASIS","ID_ADRESSE_BASIS",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);

		/*
		 * 2013-06-26: das feld "SONDERLAGER" muss NULL sein
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange_FIELD_MUST_BE_NULL("JT_ADRESSE","SONDERLAGER","SONDERLAGER",new MyE2_String("Sonderlager")), false);
		
		
		
		this.initFields();
	}

}
