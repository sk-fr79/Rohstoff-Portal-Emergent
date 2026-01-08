package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_LIST_DATASEARCH extends E2_DataSearch
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5738430232101896422L;
	E2_NavigationList m_oNaviList = null;

	public WF_HEAD_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_LAUFZETTEL","ID_LAUFZETTEL",MODULE_KENNER);
		
		m_oNaviList = oNaviList;
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_LAUFZETTEL","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ABGESCHLOSSEN_AM","Abgeschlossen am",false);
		this.addSearchDef("ID_USER_ABGESCHLOSSEN_VON","Abgeschlossen von",false);
		this.addSearchDef("ANGELEGT_AM","Angelegt am",false);
		this.addSearchDef("GEAENDERT_VON","Geändert von ",false);
		this.addSearchDef("ID_LAUFZETTEL","Laufzettel-ID",false);
		this.addSearchDef("ID_LAUFZETTEL_STATUS","Laufzettel-Status",false);
		this.addSearchDef("ID_MANDANT","Mandant",false);
		this.addSearchDef("ID_USER_BESITZER","Besitzer-ID",false);
		this.addSearchDef("ID_USER_SUPERVISOR","Supervisor-ID",false);
		this.addSearchDef("LETZTE_AENDERUNG","Letzte Änderung",false);
		this.addSearchDef("TEXT","Laufzettel-Beschreibung",false);
		this.addSearchDef("PRIVAT", "Laufzettel ist privat", false);
		
		// Laufzettel-Eintrags-Werte
		this.addSearchDefRefTable("JT_LAUFZETTEL_EINTRAG","AUFGABE","Aufgabe des Eintrags",false);
		this.addSearchDefRefTable("JT_LAUFZETTEL_EINTRAG","BERICHT","Tätigkeitsbericht des Eintrags",false);
		this.addSearchDefRefTable("JT_LAUFZETTEL_EINTRAG","PRIVAT","Eintrag ist privat",false);
		
		
		this.add_XX_SearchAddonBedingung_to_all_SearchDefinitions(new XX_SearchAddonBedingung() {
			
			@Override
			public String get_AddOnBedingung() throws myException {
				String sBedinungStaticForSearch = WF_HEAD_LIST_DATASEARCH.this.m_oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cSQL_WHERE_BLOCK_STATIC();
				if (bibALL.isEmpty(sBedinungStaticForSearch) ){
					sBedinungStaticForSearch = " 1=1 ";
				}
				return sBedinungStaticForSearch;
			}
		});

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
			cSearch = "SELECT JT_LAUFZETTEL.ID_LAUFZETTEL FROM "+bibE2.cTO()+".JT_LAUFZETTEL WHERE TO_CHAR(JT_LAUFZETTEL."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAUFZETTEL.ID_LAUFZETTEL FROM "+bibE2.cTO()+".JT_LAUFZETTEL WHERE UPPER(JT_LAUFZETTEL."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAUFZETTEL.ID_LAUFZETTEL FROM "+bibE2.cTO()+".JT_LAUFZETTEL,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_LAUFZETTEL.ID_LAUFZETTEL="+cRefTableName+".ID_LAUFZETTEL AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAUFZETTEL.ID_LAUFZETTEL FROM "+bibE2.cTO()+".JT_LAUFZETTEL,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_LAUFZETTEL.ID_LAUFZETTEL="+cRefTableName+".ID_LAUFZETTEL AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
