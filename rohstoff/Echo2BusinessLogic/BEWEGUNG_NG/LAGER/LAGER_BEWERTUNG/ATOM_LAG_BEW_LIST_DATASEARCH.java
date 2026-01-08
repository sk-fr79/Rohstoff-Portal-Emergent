package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_LAG_BEW_LIST_DATASEARCH extends E2_DataSearch
{

	/**
	 * 
	 */


	public ATOM_LAG_BEW_LIST_DATASEARCH(E2_NavigationList oNaviList,String MODUL_KENNER) throws myException
	{
		super("JT_LAGER_KONTO","ID_LAGER_KONTO",MODUL_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		//neu am 2010.06.24
		this.set_bForceListQueryOnSearch(true);
		
		this.addSearchDef("ID_LAGER_KONTO","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ID_LAGER_KONTO","ID",false);
		this.addSearchDef("BUCHUNGSDATUM","Buchungsdatum",false);
		this.addSearchDef("ID_ADRESSE_LAGER","ID Adresse",false);
		this.addSearchDef("ID_ARTIKEL_SORTE","ID Sorte",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE","ID Fuhre",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE_ORT","ID Fuhrenort",false);
		this.addSearchDef("MENGE","Menge",false);
		this.addSearchDef("PREIS","Preis",false);
		
		// kunde suchen:
		String cKundesuche = " Select jt_lager_konto.ID_LAGER_KONTO  from  "+bibE2.cTO()+".jt_lager_konto  left outer join "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE  ON jt_lager_konto.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE "+
							 " WHERE upper(L_NAME1) like upper('%#WERT#%')   OR upper(L_NAME2) like upper('%#WERT#%')  OR upper(L_NAME3) like upper('%#WERT#%') " +
							 " OR upper(A_NAME1) like upper('%#WERT#%')   OR upper(A_NAME2) like upper('%#WERT#%')  OR upper(A_NAME3) like upper('%#WERT#%') ";
		this.add_SearchElement(cKundesuche,new MyE2_String("Kunde"));

		
		
//		this.addSearchDef("IST_KOMPLETT","Komplett",false);
//		this.addSearchDef("ID_VPOS_RG","ID Rechnung",false);
//		this.addSearchDef("ID_LAGER_KONTO_PARENT","ID_LAGER_KONTO_PARENT",false);
//		this.addSearchDef("SALDO","SALDO",false);
//		this.addSearchDef("STORNO","STORNO",false);

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
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE TO_CHAR(JT_LAGER_KONTO."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE UPPER(JT_LAGER_KONTO."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	@SuppressWarnings("unused")
	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO="+cRefTableName+".ID_LAGER_KONTO AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_LAGER_KONTO.ID_LAGER_KONTO="+cRefTableName+".ID_LAGER_KONTO AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
