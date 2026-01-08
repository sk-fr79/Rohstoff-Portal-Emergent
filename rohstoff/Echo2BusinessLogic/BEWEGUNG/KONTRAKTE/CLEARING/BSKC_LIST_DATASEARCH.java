package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_LIST_DATASEARCH extends E2_DataSearch
{
	
	private String cKurz = 		null;
	private String cGegenKurz = null;
	private String cREFERENZTYP= null;
	private String cReferenzTypSQL =null;
	private String cGEGENTYP =null;
	private String cGegenTypSQL =null;


	public BSKC_LIST_DATASEARCH(E2_NavigationList 	oNaviList, String MODULE_KENNER,String cEK_VK) throws myException
	{
		super("JT_VPOS_KON","ID_VPOS_KON",MODULE_KENNER);
		
		
		this.cREFERENZTYP=cEK_VK.equals("EK")?myCONST.VORGANGSART_EK_KONTRAKT:myCONST.VORGANGSART_VK_KONTRAKT;
		this.cReferenzTypSQL = bibALL.MakeSql(cREFERENZTYP);
		
		this.cGEGENTYP=cEK_VK.equals("EK")?myCONST.VORGANGSART_VK_KONTRAKT:myCONST.VORGANGSART_EK_KONTRAKT;
		this.cGegenTypSQL = bibALL.MakeSql(cGEGENTYP);
		
		this.cKurz = cEK_VK;
		this.cGegenKurz = cEK_VK.equals("EK")?"VK":"EK";
		
		
		String cSearchPosBase = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
												" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
												" AND TO_CHAR(JT_VPOS_KON.ID_VPOS_KON) = '#WERT#'"+"AND JT_VKOPF_KON.VORGANG_TYP="; 

		String cSearchPosID = 		cSearchPosBase+cReferenzTypSQL;
		String cSearchPosID_gegen = cSearchPosBase+cGegenTypSQL;
		
		
		String cSearchPosID_ZUORD = "SELECT ID_VPOS_KON_"+cKurz+" FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+cGegenKurz+" IN ("+cSearchPosID_gegen+")";
		String cSearchVPOS_ID = cSearchPosID+" UNION "+cSearchPosID_ZUORD;

		
		String cSearchAdressID = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
										" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
										" AND TO_CHAR(JT_VKOPF_KON.ID_ADRESSE) = '#WERT#'"+"AND JT_VKOPF_KON.VORGANG_TYP="+cReferenzTypSQL; 
		
		String cSearchAdressID_gegen = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
											" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
											" AND TO_CHAR(JT_VKOPF_KON.ID_ADRESSE) = '#WERT#'"+"AND JT_VKOPF_KON.VORGANG_TYP="+this.cGegenTypSQL; 

		String cSearchAdressID_Zuord = "SELECT ID_VPOS_KON_"+cKurz+" FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+cGegenKurz+" IN ("+cSearchAdressID_gegen+")";
		String cSearchADRESS_ID = cSearchAdressID+" UNION "+cSearchAdressID_Zuord;
		
		
		/*
		 * geaendert am: 20.01.2010 von: martin
		 * zusatz suche nach kontrakt-buchungsnummern
		 */
		
		String cSearchBuchNr = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
										" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
										" AND NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,'@@') LIKE '%#WERT#%' AND JT_VKOPF_KON.VORGANG_TYP="+cReferenzTypSQL; 
		
		String ccSearchBuchNr_gegen = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
											" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
											" AND NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,'@@') LIKE '%#WERT#%' AND JT_VKOPF_KON.VORGANG_TYP="+this.cGegenTypSQL; 

		String ccSearchBuchNr_Zuord = "SELECT ID_VPOS_KON_"+cKurz+" FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+cGegenKurz+" IN ("+ccSearchBuchNr_gegen+")";
		String ccSearchBuchNr_ID = cSearchBuchNr+" UNION "+ccSearchBuchNr_Zuord;

		
		String cSearchAnr1 = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON " +
							        " LEFT OUTER JOIN JT_VKOPF_KON ON (JT_VPOS_KON.ID_VKOPF_KON=JT_VKOPF_KON.ID_VKOPF_KON) "+
									" WHERE JT_VPOS_KON.ANR1 LIKE '#WERT#%' AND JT_VKOPF_KON.VORGANG_TYP="+cReferenzTypSQL; 

		
		
		//2011-01-19: Suche nach Fremdbestellnummer
		String cSearchBestellnr = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON " +
							        " LEFT OUTER JOIN JT_VKOPF_KON ON (JT_VPOS_KON.ID_VKOPF_KON=JT_VKOPF_KON.ID_VKOPF_KON) "+
									" WHERE JT_VPOS_KON.BESTELLNUMMER LIKE '%#WERT#%' AND JT_VKOPF_KON.VORGANG_TYP="+cReferenzTypSQL; 
		
		/*
		 * ende suche nach kontrakt-buchungsnummern
		 */
		
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef_Kopf("NAME1","Name 1",false);
		this.addSearchDef_Kopf("NAME2","Name 2",false);
		this.addSearchDef_Kopf("NAME3","Name 3",false);
		this.addSearchDef_Kopf("STRASSE","Strasse",false);
		this.addSearchDef_Kopf("PLZ","PLZ",false);
		this.addSearchDef_Kopf("ORT","Ort",false);
		this.addSearchDef_Kopf("ORTZUSATZ","Ort-Zusatz",false);
		this.addSearchDef_Kopf("ID_VKOPF_KON","ID-Kopf",true);
		this.add_SearchElement(cSearchVPOS_ID,new MyE2_String("ID-Pos"));
		this.add_SearchElement(cSearchADRESS_ID,new MyE2_String("ID-Adresse"));
		this.add_SearchElement(ccSearchBuchNr_ID,new MyE2_String("Buchungsnummer Kopf"));
		this.add_SearchElement(cSearchAnr1,new MyE2_String("ANR1"));
		this.add_SearchElement(cSearchBestellnr,new MyE2_String("Bestellnummer (ext)"));

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	
	
	private void addSearchDef_Kopf(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearchBase = "";

		
		if (bNumber)
		{
			cSearchBase = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
						" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
						" AND TO_CHAR(JT_VKOPF_KON."+cFieldName+") = '#WERT#'"+"AND JT_VKOPF_KON.VORGANG_TYP=";
		}
		else
		{
			cSearchBase = "SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VKOPF_KON "+
						" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON "+
						" AND UPPER(JT_VKOPF_KON."+cFieldName+") LIKE UPPER('%#WERT#%')"+"AND JT_VKOPF_KON.VORGANG_TYP=";
		}
		
		String cSearch = cSearchBase+this.cReferenzTypSQL;
		String cSearchGegen = cSearchBase+this.cGegenTypSQL;
		
		
		String cSearchTochter = "SELECT ID_VPOS_KON_"+this.cKurz+" FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+cGegenKurz+" IN ("+cSearchGegen+")";

		
		this.add_SearchElement(cSearch+" UNION "+cSearchTochter,new MyE2_String(cInfoText));
	}
	

	
	
}
