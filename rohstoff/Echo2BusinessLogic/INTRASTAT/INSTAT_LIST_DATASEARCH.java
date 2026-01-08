package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class INSTAT_LIST_DATASEARCH extends E2_DataSearch
{

	public INSTAT_LIST_DATASEARCH(E2_NavigationList oNaviList,String MODUL_KENNER) throws myException
	{
		super("JT_INTRASTAT_MELDUNG","ID_INTRASTAT_MELDUNG",MODUL_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_INTRASTAT_MELDUNG","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ANMELDEFORM","ANMELDEFORM",false);
		this.addSearchDef("ANMELDEMONAT","ANMELDEMONAT",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT",false);
		this.addSearchDef("BESTIMM_LAND","BESTIMM_LAND",false);
		this.addSearchDef("BESTIMM_REGION","BESTIMM_REGION",false);
		this.addSearchDef("BEZUGSJAHR","BEZUGSJAHR",false);
		this.addSearchDef("BEZUGSMONAT","BEZUGSMONAT",false);
		this.addSearchDef("BUNDESLAND_FA","BUNDESLAND_FA",false);
		this.addSearchDef("EIGENMASSE","EIGENMASSE",false);
		this.addSearchDef("EXPORTFREIGABE","EXPORTFREIGABE",false);
		this.addSearchDef("EXPORTIERT_AM","EXPORTIERT_AM",false);
		this.addSearchDef("GESCHAEFTSART","GESCHAEFTSART",false);
		this.addSearchDef("ID_INTRASTAT_MELDUNG","ID_INTRASTAT_MELDUNG",false);
		this.addSearchDef("LAND_URSPRUNG","LAND_URSPRUNG",false);
		this.addSearchDef("MASSEINHEIT","MASSEINHEIT",false);
		this.addSearchDef("MELDEART","MELDEART",false);
		this.addSearchDef("PAGINIERNUMMER","PAGINIERNUMMER",false);
		this.addSearchDef("RECHBETRAG","RECHBETRAG",false);
		this.addSearchDef("STATISTISCHER_WERT","STATISTISCHER_WERT",false);
		this.addSearchDef("STEUERNR","STEUERNR",false);
		this.addSearchDef("UNTERSCHEIDUNGSNR","UNTERSCHEIDUNGSNR",false);
		this.addSearchDef("VERKEHRSZWEIG","VERKEHRSZWEIG",false);
		this.addSearchDef("WAEHRUNGSKENNZIFFER","WAEHRUNGSKENNZIFFER",false);
		this.addSearchDef("WARENNR","WARENNR",false);

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
			cSearch = "SELECT JT_INTRASTAT_MELDUNG.ID_INTRASTAT_MELDUNG FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG WHERE TO_CHAR(JT_INTRASTAT_MELDUNG."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_INTRASTAT_MELDUNG.ID_INTRASTAT_MELDUNG FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG WHERE UPPER(JT_INTRASTAT_MELDUNG."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_INTRASTAT_MELDUNG.ID_INTRASTAT_MELDUNG FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_INTRASTAT_MELDUNG.ID_INTRASTAT_MELDUNG="+cRefTableName+".ID_INTRASTAT_MELDUNG AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_INTRASTAT_MELDUNG.ID_INTRASTAT_MELDUNG FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_INTRASTAT_MELDUNG.ID_INTRASTAT_MELDUNG="+cRefTableName+".ID_INTRASTAT_MELDUNG AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
