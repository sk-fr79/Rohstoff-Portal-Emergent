package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class TP_KST_LIST_DATASEARCH extends E2_DataSearch
{

	public TP_KST_LIST_DATASEARCH(E2_NavigationList oNaviList,String MODUL_KENNER) throws myException
	{
		super("JT_VPOS_TPA","ID_VPOS_TPA", MODUL_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_VPOS_TPA","ID",true);

		//hier kommen die Felder	
//		this.addSearchDef("ANR1","ANR1",false);
//		this.addSearchDef("ANR2","ANR2",false);
//		this.addSearchDef("ANZAHL","ANZAHL",false);
//		this.addSearchDef("ANZAHL_ABZUG","ANZAHL_ABZUG",false);
		this.addSearchDef("ARTBEZ1","ARTBEZ1",false);
//		this.addSearchDef("ARTBEZ2","ARTBEZ2",false);
//		this.addSearchDef("AUSFUEHRUNGSDATUM","AUSFUEHRUNGSDATUM",false);
//		this.addSearchDef("BEMERKUNG_INTERN","BEMERKUNG_INTERN",false);
//		this.addSearchDef("BESTELLNUMMER","BESTELLNUMMER",false);
//		this.addSearchDef("DEL_DATE","DEL_DATE",false);
//		this.addSearchDef("DELETED","DELETED",false);
//		this.addSearchDef("DEL_GRUND","DEL_GRUND",false);
//		this.addSearchDef("EINHEITKURZ","EINHEITKURZ",false);
//		this.addSearchDef("EINHEIT_PREIS_KURZ","EINHEIT_PREIS_KURZ",false);
//		this.addSearchDef("EINZELPREIS","EINZELPREIS",false);
//		this.addSearchDef("EINZELPREIS_ABZUG","EINZELPREIS_ABZUG",false);
//		this.addSearchDef("EINZELPREIS_ABZUG_FW","EINZELPREIS_ABZUG_FW",false);
//		this.addSearchDef("EINZELPREIS_FW","EINZELPREIS_FW",false);
//		this.addSearchDef("EINZELPREIS_RESULT","EINZELPREIS_RESULT",false);
//		this.addSearchDef("EINZELPREIS_RESULT_FW","EINZELPREIS_RESULT_FW",false);
//		this.addSearchDef("EUCODE","EUCODE",false);
//		this.addSearchDef("EUNOTIZ","EUNOTIZ",false);
//		this.addSearchDef("FIXMONAT","FIXMONAT",false);
//		this.addSearchDef("FIXTAG","FIXTAG",false);
//		this.addSearchDef("GEBUCHT","GEBUCHT",false);
//		this.addSearchDef("GESAMTPREIS","GESAMTPREIS",false);
//		this.addSearchDef("GESAMTPREIS_ABZUG","GESAMTPREIS_ABZUG",false);
//		this.addSearchDef("GESAMTPREIS_ABZUG_FW","GESAMTPREIS_ABZUG_FW",false);
//		this.addSearchDef("GESAMTPREIS_FW","GESAMTPREIS_FW",false);
		this.addSearchDef("ID_ADRESSE","ID_ADRESSE",false);
//		this.addSearchDef("ID_ADRESSE_LAGER","ID_ADRESSE_LAGER",false);
//		this.addSearchDef("ID_ADRESSE_LAGER_START","ID_ADRESSE_LAGER_START",false);
//		this.addSearchDef("ID_ADRESSE_LAGER_ZIEL","ID_ADRESSE_LAGER_ZIEL",false);
//		this.addSearchDef("ID_ARTIKEL","ID_ARTIKEL",false);
		this.addSearchDef("ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ",false);
//		this.addSearchDef("ID_STRECKEN_DEF","ID_STRECKEN_DEF",false);
//		this.addSearchDef("ID_VKOPF_TPA","ID_VKOPF_TPA",false);
//		this.addSearchDef("ID_VPOS_KON_ZUGEORD","ID_VPOS_KON_ZUGEORD",false);
//		this.addSearchDef("ID_VPOS_TPA","ID_VPOS_TPA",false);
//		this.addSearchDef("ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD",false);
//		this.addSearchDef("ID_WAEHRUNG_FREMD","ID_WAEHRUNG_FREMD",false);
//		this.addSearchDef("ID_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN",false);
//		this.addSearchDef("LAGER_VORZEICHEN","LAGER_VORZEICHEN",false);
//		this.addSearchDef("LIEFERBEDINGUNGEN","LIEFERBEDINGUNGEN",false);
//		this.addSearchDef("MENGENDIVISOR","MENGENDIVISOR",false);
//		this.addSearchDef("POSITION_ACTIVE","POSITION_ACTIVE",false);
//		this.addSearchDef("POSITIONSKLASSE","POSITIONSKLASSE",false);
//		this.addSearchDef("POSITIONSNUMMER","POSITIONSNUMMER",false);
//		this.addSearchDef("POSITION_TYP","POSITION_TYP",false);
//		this.addSearchDef("SKONTO_PROZENT","SKONTO_PROZENT",false);
//		this.addSearchDef("STEUERSATZ","STEUERSATZ",false);
//		this.addSearchDef("VORGANG_TYP","VORGANG_TYP",false);
//		this.addSearchDef("WAEHRUNGSKURS","WAEHRUNGSKURS",false);
//		this.addSearchDef("WIEGEKARTENKENNER","WIEGEKARTENKENNER",false);
//		this.addSearchDef("ZAHLTAGE","ZAHLTAGE",false);
//		this.addSearchDef("ZAHLUNGSBED_CALC_DATUM","ZAHLUNGSBED_CALC_DATUM",false);
//		this.addSearchDef("ZAHLUNGSBEDINGUNGEN","ZAHLUNGSBEDINGUNGEN",false);
//		this.addSearchDef("ZOLLTARIFNR","ZOLLTARIFNR",false);

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
			cSearch = "SELECT JT_VPOS_TPA.ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE TO_CHAR(JT_VPOS_TPA."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_VPOS_TPA.ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE UPPER(JT_VPOS_TPA."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_VPOS_TPA.ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_VPOS_TPA.ID_VPOS_TPA="+cRefTableName+".ID_VPOS_TPA AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_VPOS_TPA.ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_VPOS_TPA.ID_VPOS_TPA="+cRefTableName+".ID_VPOS_TPA AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
