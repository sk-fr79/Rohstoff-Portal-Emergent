package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;


public class FZ_LIST_DATASEARCH extends E2_DataSearch
{

	public FZ_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_BEWEGUNG_VEKTOR","ID_BEWEGUNG_VEKTOR",E2_MODULNAME_ENUM.MODUL.FUZE_ATOM_BASED_LIST.get_callKey());
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_BEWEGUNG_VEKTOR","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ABGESCHLOSSEN_AM","ABGESCHLOSSEN_AM",false);
		this.addSearchDef("ABGESCHLOSSEN_VON","ABGESCHLOSSEN_VON",false);
		this.addSearchDef("A_DATUM_BIS","A_DATUM_BIS",false);
		this.addSearchDef("A_DATUM_VON","A_DATUM_VON",false);
		this.addSearchDef("ANHAENGERKENNZEICHEN","ANHAENGERKENNZEICHEN",false);
		this.addSearchDef("AVV_AUSSTELLUNG_DATUM","AVV_AUSSTELLUNG_DATUM",false);
		this.addSearchDef("BEMERKUNG","BEMERKUNG",false);
		this.addSearchDef("BEMERKUNG_SACHBEARBEITER","BEMERKUNG_SACHBEARBEITER",false);
		this.addSearchDef("DEL_DATE","DEL_DATE",false);
		this.addSearchDef("DELETED","DELETED",false);
		this.addSearchDef("DEL_GRUND","DEL_GRUND",false);
		this.addSearchDef("DEL_KUERZEL","DEL_KUERZEL",false);
		this.addSearchDef("EK_VK_ZUORD_ZWANG","EK_VK_ZUORD_ZWANG",false);
		this.addSearchDef("EU_BLATT_MENGE","EU_BLATT_MENGE",false);
		this.addSearchDef("EU_BLATT_VOLUMEN","EU_BLATT_VOLUMEN",false);
		this.addSearchDef("GELANGENSBESTAETIGUNG_ERHALTEN","GELANGENSBESTAETIGUNG_ERHALTEN",false);
		this.addSearchDef("ID_ADRESSE_FREMDWARE","ID_ADRESSE_FREMDWARE",false);
		this.addSearchDef("ID_ADRESSE_SPEDITION","ID_ADRESSE_SPEDITION",false);
		this.addSearchDef("ID_ADRESSE_START_LOGISTIK","ID_ADRESSE_START_LOGISTIK",false);
		this.addSearchDef("ID_BEWEGUNG","ID_BEWEGUNG",false);
		this.addSearchDef("ID_BEWEGUNG_VEKTOR","ID_BEWEGUNG_VEKTOR",false);
		this.addSearchDef("ID_EAK_CODE","ID_EAK_CODE",false);
		this.addSearchDef("ID_UMA_KONTRAKT","ID_UMA_KONTRAKT",false);
		this.addSearchDef("ID_VEKTOR_GRUPPE","ID_VEKTOR_GRUPPE",false);
		this.addSearchDef("KOSTEN_PRODUKT_WA","KOSTEN_PRODUKT_WA",false);
		this.addSearchDef("KOSTEN_PRODUKT_WE","KOSTEN_PRODUKT_WE",false);
		this.addSearchDef("KOSTEN_TRANSPORT_WA","KOSTEN_TRANSPORT_WA",false);
		this.addSearchDef("KOSTEN_TRANSPORT_WE","KOSTEN_TRANSPORT_WE",false);
		this.addSearchDef("LAENDERCODE_TRANSIT1","LAENDERCODE_TRANSIT1",false);
		this.addSearchDef("LAENDERCODE_TRANSIT2","LAENDERCODE_TRANSIT2",false);
		this.addSearchDef("LAENDERCODE_TRANSIT3","LAENDERCODE_TRANSIT3",false);
		this.addSearchDef("L_DATUM_BIS","L_DATUM_BIS",false);
		this.addSearchDef("L_DATUM_VON","L_DATUM_VON",false);
		this.addSearchDef("POSNR","POSNR",false);
		this.addSearchDef("PRINT_EU_AMTSBLATT","PRINT_EU_AMTSBLATT",false);
		this.addSearchDef("STATUS","STATUS",false);
		this.addSearchDef("STORNIERT_AM","STORNIERT_AM",false);
		this.addSearchDef("STORNIERT_GRUND","STORNIERT_GRUND",false);
		this.addSearchDef("STORNIERT_VON","STORNIERT_VON",false);
		this.addSearchDef("TRANSPORTKENNZEICHEN","TRANSPORTKENNZEICHEN",false);
		this.addSearchDef("TRANSPORTMITTEL","TRANSPORTMITTEL",false);
		this.addSearchDef("VARIANTE","VARIANTE",false);
		this.addSearchDef("WARENKLASSE","WARENKLASSE",false);
		this.addSearchDef("ZEITSTEMPEL","ZEITSTEMPEL",false);

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
			cSearch = "SELECT JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE TO_CHAR(JT_BEWEGUNG_VEKTOR."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE UPPER(JT_BEWEGUNG_VEKTOR."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR="+cRefTableName+".ID_BEWEGUNG_VEKTOR AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR="+cRefTableName+".ID_BEWEGUNG_VEKTOR AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
