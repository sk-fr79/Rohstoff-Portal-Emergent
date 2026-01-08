package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_SearchDefinition;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSL_LIST_DATASEARCH extends E2_DataSearch
{
	String cWhereZusatz = null;;
	
	

	public FSL_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER, SQLFieldMAP  oSQLFieldMap) throws myException
	{
		super("JT_ADRESSE","ID_ADRESSE", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		String cTO = bibE2.cTO();
		
		
		this.addSearchDefADRESSE("ID_ADRESSE","Adress-ID der Lieferadresse",true, oSQLFieldMap);
		this.addSearchDefADRESSE("VORNAME","Vorname",false, oSQLFieldMap);
		this.addSearchDefADRESSE("NAME1","Name 1",false, oSQLFieldMap);
		this.addSearchDefADRESSE("NAME2","Name 2",false, oSQLFieldMap);
		this.addSearchDefADRESSE("NAME3","Name 3",false, oSQLFieldMap);
		this.addSearchDefADRESSE("STRASSE","Strasse",false, oSQLFieldMap);
		this.addSearchDefADRESSE("PLZ","PLZ",false, oSQLFieldMap);
		this.addSearchDefADRESSE("ORT","Ort",false, oSQLFieldMap);
		this.addSearchDefADRESSE("ORTZUSATZ","Ort-Zusatz",false, oSQLFieldMap);
		
		this.addSearchDefADRESSE("BEMERKUNGEN","Bemerkungen (Adre.)",false, oSQLFieldMap);

		//neue Suchfelder
		this.addSearchDefLieferADRESSE(RECORD_LIEFERADRESSE.FIELD__BESCHREIBUNG,"Beschreibung",false, oSQLFieldMap);
		this.addSearchDefADRESSE(RECORD_ADRESSE.FIELD__LAGER_KONTROLLINFO,"Zuständigkeit-/Verwaltungsinfo",false, oSQLFieldMap);
		
		
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_EMAIL WHERE JT_ADRESSE.ID_ADRESSE=JT_EMAIL.ID_ADRESSE AND UPPER(JT_EMAIL.EMAIL) LIKE UPPER('%#WERT#%')",new MyE2_String("e-Mail"), oSQLFieldMap);
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_INTERNET WHERE JT_ADRESSE.ID_ADRESSE=JT_INTERNET.ID_ADRESSE AND UPPER(JT_INTERNET.INTERNET) LIKE UPPER('%#WERT#%')",new MyE2_String("Internet-Adresse"), oSQLFieldMap);
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_KOMMUNIKATION WHERE " +
										" JT_ADRESSE.ID_ADRESSE=JT_KOMMUNIKATION.ID_ADRESSE AND   NVL(JT_KOMMUNIKATION.wert_laendervorwahl,'-')||  NVL(JT_KOMMUNIKATION.wert_vorwahl,'-')||  NVL(JT_KOMMUNIKATION.wert_rufnummer,'-') LIKE '%#WERT#%'",new MyE2_String("Telefon-Nummer"), oSQLFieldMap);
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

		Vector<E2_SearchDefinition> vSearchDefs = this.get_vSearchDefinitions();
		
		XX_SearchAddonBedingung  oAddonSearch = new XX_SearchAddonBedingung()
		{
			@Override
			public String get_AddOnBedingung() throws myException
			{
				return FSL_LIST_DATASEARCH.this.cWhereZusatz;
			}
		};
		
		for (E2_SearchDefinition oSD: vSearchDefs)
		{
			oSD.set_oSearchAddon(oAddonSearch);
		}
		
		
	}

	private void addSearchDefADRESSE(String cFieldName,String cInfoText, boolean bNumber, SQLFieldMAP  oSQLFieldMap) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE TO_CHAR(JT_ADRESSE."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE UPPER(JT_ADRESSE."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText),  oSQLFieldMap);
	}
	
	
	private void addSearchDefLieferADRESSE(String cFieldName,String cInfoText, boolean bNumber, SQLFieldMAP  oSQLFieldMap) throws myException
	{
		
		String cSearch = "";
		if (bNumber)
		{
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE INNER JOIN JT_LIEFERADRESSE ON (JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE."+RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER+")" +
								" WHERE TO_CHAR(JT_LIEFERADRESSE."+cFieldName+")='#WERT#'";
		}
		else
		{
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE INNER JOIN JT_LIEFERADRESSE ON (JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE."+RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER+")" +
			" WHERE UPPER(JT_LIEFERADRESSE."+cFieldName+") like upper('%#WERT#%')";
		}
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText), oSQLFieldMap);
		
	}
	
	
	
	
	public void set_baseAdressID(String cBaseAdressID)
	{
		this.cWhereZusatz="JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE+" AND "+
		                  "JT_ADRESSE.ID_ADRESSE IN (" +
		                  " SELECT ID_ADRESSE_LIEFER FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+cBaseAdressID+
		                  ")";
	}

	
	
	
}
