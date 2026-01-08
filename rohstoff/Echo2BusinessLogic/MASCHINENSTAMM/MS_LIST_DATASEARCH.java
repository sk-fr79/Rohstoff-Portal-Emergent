package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MS_LIST_DATASEARCH extends E2_DataSearch
{

	public MS_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_MASCHINEN","ID_MASCHINEN",E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_MASCHINEN","ID",true);

		//hier kommen die Felder	
		this.addSearchDef(		"BEMERKUNG",			"Bemerkung",false);
		this.addSearchDef(		"BESCHREIBUNG",			"Beschreibung",false);
		this.addSearchDef(		"BRIEFNUMMER",			"Brief",false);
		this.addSearchDefDatum(	"DATUM_ANSCHAFFUNG",	"Anschaffungsdatum");
		this.addSearchDefDatum(	"GEWAEHRLEISTUNG_BIS",	"Gewährleistung bis");
		this.addSearchDefDatum(	"LEASING_BIS",			"Leasing bis");
		this.addSearchDef(		"FAHRGESTELLNUMMER",	"Fahrgestellnummer",false);
		this.addSearchDefDatum(	"GEKAUFT_AB",			"gekauft ab");
		this.addSearchDef(		"HERSTELLER",			"Hersteller",false);
		this.addSearchDef(		"ID_MASCHINEN",			"ID",false);
		this.addSearchDef(		"KFZKENNZEICHEN",		"KFZ-Kennzeichen",false);
		this.addSearchDef(		"KOSTEN_ANSCHAFFUNG",	"Anschaffungkosten",false);
		this.addSearchDef(		"KOSTENSTELLE1",		"Kostenstelle 1",false);
		this.addSearchDef(		"KOSTENSTELLE2",		"Lostenstelle 2",false);
		this.addSearchDef(		"TYPENBEZ",				"Typenbezeichnung",false);

		//benutzer
		String cQueryBediener_1_oder_2 = "SELECT MS.ID_MASCHINEN "+ 
												" FROM"+ 
												" "+bibE2.cTO()+".JT_MASCHINEN MS "+
												" WHERE MS.ID_USER_BEDIENER1 IN ("+
												"   SELECT ID_USER FROM "+bibE2.cTO()+".JD_USER " +
												"   WHERE ID_MANDANT="+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+
												"   AND (UPPER(NVL(VORNAME,'')) LIKE UPPER('%#WERT#%') OR UPPER(NVL(NAME1,'')) LIKE UPPER('%#WERT#%') OR  UPPER(NVL(KUERZEL,'')) LIKE UPPER('%#WERT#%'))) " +
												" OR  MS.ID_USER_BEDIENER2 IN ("+
												"   SELECT ID_USER FROM "+bibE2.cTO()+".JD_USER " +
												"   WHERE ID_MANDANT="+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+
												"   AND (UPPER(NVL(VORNAME,'')) LIKE UPPER('%#WERT#%') OR UPPER(NVL(NAME1,'')) LIKE UPPER('%#WERT#%') OR  UPPER(NVL(KUERZEL,'')) LIKE UPPER('%#WERT#%'))) ";

		this.add_SearchElement(cQueryBediener_1_oder_2,new MyE2_String("Bediener"));
		
		
		//adresse
		String cQueryStandort = "SELECT MS.ID_MASCHINEN "+ 
												" FROM"+ 
												" "+bibE2.cTO()+".JT_MASCHINEN MS "+
												" WHERE ID_ADRESSE_STANDORT IN ("+
												"   SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE " +
												"   WHERE (UPPER(NVL(VORNAME,'')) LIKE UPPER('%#WERT#%') OR UPPER(NVL(NAME1,'')) LIKE UPPER('%#WERT#%') OR UPPER(NVL(NAME2,'')) LIKE UPPER('%#WERT#%') OR  UPPER(NVL(ORT,'')) LIKE UPPER('%#WERT#%'))) ";

		this.add_SearchElement(cQueryStandort,new MyE2_String("Standort"));
		
		
		
		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_MASCHINEN.ID_MASCHINEN FROM "+bibE2.cTO()+".JT_MASCHINEN WHERE TO_CHAR(JT_MASCHINEN."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_MASCHINEN.ID_MASCHINEN FROM "+bibE2.cTO()+".JT_MASCHINEN WHERE UPPER(JT_MASCHINEN."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_MASCHINEN.ID_MASCHINEN FROM "+bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_MASCHINEN.ID_MASCHINEN="+cRefTableName+".ID_MASCHINEN AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_MASCHINEN.ID_MASCHINEN FROM "+bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_MASCHINEN.ID_MASCHINEN="+cRefTableName+".ID_MASCHINEN AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


	private void addSearchDefDatum(String cFieldName,String cInfoText) throws myException
	{

		String cSearch = 	"SELECT JT_MASCHINEN.ID_MASCHINEN  FROM "+bibE2.cTO()+".JT_MASCHINEN WHERE " +
													" TO_CHAR(JT_MASCHINEN."+cFieldName+",'DD.MM.YYYY') LIKE '%#WERT#%' OR "+
													" TO_CHAR(JT_MASCHINEN."+cFieldName+",'DDMMYYYY') LIKE '%#WERT#%' OR "+
													" TO_CHAR(JT_MASCHINEN."+cFieldName+",'DDMMYY') LIKE '%#WERT#%'";
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
	
}
