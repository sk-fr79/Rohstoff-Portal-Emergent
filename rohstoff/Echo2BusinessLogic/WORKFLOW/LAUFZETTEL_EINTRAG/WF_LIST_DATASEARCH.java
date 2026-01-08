package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class WF_LIST_DATASEARCH extends E2_DataSearch
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 426781255336156728L;


	public WF_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_LAUFZETTEL_EINTRAG","ID_LAUFZETTEL_EINTRAG", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_LAUFZETTEL_EINTRAG","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ABGESCHLOSSEN_AM","ABGESCHLOSSEN_AM",false);
		this.addSearchDef("ID_USER_ABGESCHLOSSEN_VON","ID_USER_ABGESCHLOSSEN_VON",false);
		this.addSearchDef("ANGELEGT_AM","ANGELEGT_AM",false);
		this.addSearchDef("AUFGABE","AUFGABE",false);
		this.addSearchDef("BERICHT","BERICHT",false);
		this.addSearchDef("FAELLIG_AM","FAELLIG_AM",false);
		this.addSearchDef("GEAENDERT_VON","GEAENDERT_VON",false);
		this.addSearchDef("ID_LAUFZETTEL","ID_LAUFZETTEL",false);
		this.addSearchDef("ID_LAUFZETTEL_EINTRAG","ID_LAUFZETTEL_EINTRAG",false);
		this.addSearchDef("ID_LAUFZETTEL_PRIO","ID_LAUFZETTEL_PRIO",false);
		this.addSearchDef("ID_LAUFZETTEL_STATUS","ID_LAUFZETTEL_STATUS",false);
		this.addSearchDef("ID_MANDANT","ID_MANDANT",false);
		this.addSearchDef("ID_USER_BEARBEITER","ID_USER_BEARBEITER",false);
		this.addSearchDef("ID_USER_BESITZER","ID_USER_BESITZER",false);
		this.addSearchDef("LETZTE_AENDERUNG","LETZTE_AENDERUNG",false);

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
			cSearch = "SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL_EINTRAG FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE TO_CHAR(JT_LAUFZETTEL_EINTRAG."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL_EINTRAG FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE UPPER(JT_LAUFZETTEL_EINTRAG."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	@SuppressWarnings("unused")
	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL_EINTRAG FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL_EINTRAG="+cRefTableName+".ID_LAUFZETTEL_EINTRAG AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL_EINTRAG FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL_EINTRAG="+cRefTableName+".ID_LAUFZETTEL_EINTRAG AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
