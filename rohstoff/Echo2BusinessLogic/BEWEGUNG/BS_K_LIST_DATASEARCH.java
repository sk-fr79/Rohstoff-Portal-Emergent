package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BS_K_LIST_DATASEARCH extends E2_DataSearch
{
	private String cWhereZusatz = null;

	private  String cVKOPF_TAB = null;
	private  String cVKOPF_PK_NAME = null;
	
	private  String cVPOS_TAB = null;
	private  String cVPOS_PK_NAME = null;
	

	public BS_K_LIST_DATASEARCH(	E2_NavigationList 	oNaviList, 
									BS__SETTING			SETTING,
									String 				MODULE_KENNER,
									String    			search_addon) throws myException
	{
		super(  SETTING.get_oVorgangTableNames().get_cVKOPF_TAB(),SETTING.get_oVorgangTableNames().get_cVKOPF_PK(),MODULE_KENNER);
		
		this.cVKOPF_TAB = SETTING.get_oVorgangTableNames().get_cVKOPF_TAB();
		this.cVKOPF_PK_NAME = SETTING.get_oVorgangTableNames().get_cVKOPF_PK();
		
		this.cVPOS_TAB = SETTING.get_oVorgangTableNames().get_cVPOS_TAB();
		this.cVPOS_PK_NAME = SETTING.get_oVorgangTableNames().get_cVPOS_PK();
		
		if (S.isFull(search_addon)) {
			this.cWhereZusatz = ""+cVKOPF_TAB+".VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'"+search_addon;
		} else {
			this.cWhereZusatz = ""+cVKOPF_TAB+".VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'";
		}
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("NAME1","Name 1",false);
		this.addSearchDef("NAME2","Name 2",false);
		this.addSearchDef("NAME3","Name 3",false);
		this.addSearchDef("STRASSE","Strasse",false);
		this.addSearchDef("PLZ","PLZ",false);
		this.addSearchDef("ORT","Ort",false);
		this.addSearchDef("ORTZUSATZ","Ort-Zusatz",false);
		
		this.addSearchDef("L_NAME1","L-Name 1",false);
		this.addSearchDef("L_NAME2","L-Name 2",false);
		this.addSearchDef("L_NAME3","L-Name 3",false);
		this.addSearchDef("L_STRASSE","L-Strasse",false);
		this.addSearchDef("L_PLZ","L-PLZ",false);
		this.addSearchDef("L_ORT","L-Ort",false);
		this.addSearchDef("L_ORTZUSATZ","L-Ort-Zusatz",false);

		
		this.addSearchDef("ID_ADRESSE","Adress-ID",true);
		this.addSearchDef(""+cVKOPF_PK_NAME+"","Vorgangs-ID",true);
		this.addSearchDef("BEMERKUNGEN_INTERN","Bemerkungen (intern)",false);
		this.addSearchDef("FORMULARTEXT_ANFANG","Formulartext (Anfang)",false);
		this.addSearchDef("FORMULARTEXT_ENDE","Formulartext (Ende)",false);
		this.addSearchDef("BUCHUNGSNUMMER","Buchungsnummer",false);
		
		this.addSearchDef("ID_ADRESSE",						"Adress-ID Vorgangskopf",true);
		this.addSearchDef(this.cVKOPF_PK_NAME,				"ID Vorgang",true);

		
		//2011-06-17: bei allen suchmethoden den where-zusatz hinzufuegen, sonst werden falsche saetze gefunden
		
		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+","+
								bibE2.cTO()+".JT_ADRESSE WHERE "+cVKOPF_TAB+".ID_ADRESSE=JT_ADRESSE.ID_ADRESSE " +
										" AND UPPER(JT_ADRESSE.LIEF_NR) LIKE UPPER('%#WERT#%')"+
										" AND "+this.cWhereZusatz
										,new MyE2_String("Lieferanten-Nr (alt)"));

		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+","+
								bibE2.cTO()+".JT_ADRESSE WHERE "+cVKOPF_TAB+".ID_ADRESSE=JT_ADRESSE.ID_ADRESSE " +
										" AND UPPER(JT_ADRESSE.ABN_NR) LIKE UPPER('%#WERT#%') "+
										" AND "+this.cWhereZusatz										
										,new MyE2_String("Abnehmer-Nr (alt)"));

		/*
		 * suche nach einer Positions-ID   
		 */
		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+
										" LEFT OUTER JOIN "+cVPOS_TAB+" ON ("+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" = "+cVPOS_TAB+"."+cVKOPF_PK_NAME+") "+
										" WHERE " +cVKOPF_TAB+".VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'"+
										" AND TO_CHAR("+cVPOS_TAB+"."+cVPOS_PK_NAME+")='#WERT#'",
										new MyE2_String(SETTING.get_cVORGANGSART4User()+" Position-ID"));


//		/*
//		 * suche nach einer Sorte in den Positionen
//		 */
//		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+
//										" LEFT OUTER JOIN "+cVPOS_TAB+" ON ("+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" = "+cVPOS_TAB+"."+cVKOPF_PK_NAME+") "+
//										" WHERE "+cVPOS_TAB+".ANR1 like '#WERT#%'",
//										new MyE2_String(SETTING.get_cVORGANGSART4User()+" ANR1 in Positionen"));


		
		/*
		 * suche nach einer fuhren-ID
		 */
		if (	SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_GUTSCHRIFT) ||
				SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG))
		{
			this.add_SearchElement("SELECT JT_VKOPF_RG.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VKOPF_RG "+
											" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_RG ON " +
													" (JT_VKOPF_RG.ID_VKOPF_RG = JT_VPOS_RG.ID_VKOPF_RG) "+
											" WHERE TO_CHAR(JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD)='#WERT#' AND" +
											" JT_VKOPF_RG.VORGANG_TYP="+bibALL.MakeSql(SETTING.get_cBELEGTYP()),
											new MyE2_String(SETTING.get_cVORGANGSART4User()+" Fuhren-ID"));
		}
		
	
		//2011-01-19: aenderung weitere suchmethoden 
		// ANR1
		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+
									" LEFT OUTER JOIN "+cVPOS_TAB+" ON ("+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" = "+cVPOS_TAB+"."+cVKOPF_PK_NAME+") "+
									" WHERE " +cVKOPF_TAB+".VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'"+
									" AND "+cVPOS_TAB+".ANR1 LIKE '%#WERT#%'",
									new MyE2_String(SETTING.get_cVORGANGSART4User()+" ANR1 in Positionen"));

		
		
		
		
		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+
									" LEFT OUTER JOIN "+cVPOS_TAB+" ON ("+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" = "+cVPOS_TAB+"."+cVKOPF_PK_NAME+") "+
									" WHERE " +cVKOPF_TAB+".VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'"+
									" AND "+cVPOS_TAB+".BESTELLNUMMER LIKE '%#WERT#%'",
									new MyE2_String(SETTING.get_cVORGANGSART4User()+"-Pos. Bestellnummer (extern)"));


		
		
		//2011-05-04: aenderung weitere suchmethoden 
		// quasi-volltext
		this.add_SearchElement("SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+
									" LEFT OUTER JOIN "+cVPOS_TAB+" ON ("+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" = "+cVPOS_TAB+"."+cVKOPF_PK_NAME+") "+
									" WHERE " +cVKOPF_TAB+".VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'"+
									" AND (" +
									     cVPOS_TAB+".ANR1 LIKE '%#WERT#%'  OR "+ 
									     cVPOS_TAB+".ANR2 LIKE '%#WERT#%'  OR " +
									     "("+cVPOS_TAB+".ANR1||'-'||"+cVPOS_TAB+".ANR2) LIKE '%#WERT#%'  OR "+ 
									     "UPPER("+cVPOS_TAB+".ARTBEZ1) LIKE UPPER('%#WERT#%')  OR "+ 
									     "UPPER("+cVPOS_TAB+".ARTBEZ2) LIKE UPPER('%#WERT#%')" +
									     ")", 
									new MyE2_String("Artikel-Volltextsuche (ANR1-ANR2-ARTBEZ1-ARTBEZ2)"));

		//2011-12-02: suche nach postennummer
		if (	SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			this.add_SearchElement("SELECT DISTINCT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON "+
					" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU_L ON " +
							" (FU_L.ID_VPOS_KON_EK = JT_VPOS_KON.ID_VPOS_KON) "+
					" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FU_O ON " +
							" (FU_O.ID_VPOS_KON=JT_VPOS_KON.ID_VPOS_KON) "+
					" WHERE (" +
						" UPPER(FU_L.POSTENNUMMER_EK) LIKE UPPER('%#WERT#%') OR " +
						" (UPPER(FU_O.POSTENNUMMER)   LIKE UPPER('%#WERT#%') AND FU_O.DEF_QUELLE_ZIEL='EK')" +
						" ) ",
			new MyE2_String("Postennummer/Externe Eingangsnummer in Fuhre oder Fuhrenort "));
		}
		
		if (	SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			this.add_SearchElement("SELECT DISTINCT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON "+
					" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU_R ON " +
							" (FU_R.ID_VPOS_KON_VK = JT_VPOS_KON.ID_VPOS_KON) "+
					" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FU_O ON " +
							" (FU_O.ID_VPOS_KON=JT_VPOS_KON.ID_VPOS_KON) "+
					" WHERE (" +
						" UPPER(FU_R.POSTENNUMMER_VK) LIKE UPPER('%#WERT#%') OR " +
						" (UPPER(FU_O.POSTENNUMMER)   LIKE UPPER('%#WERT#%') AND FU_O.DEF_QUELLE_ZIEL='VK')" +
						" ) ",
			new MyE2_String("Postennummer/Externe Eingangsnummer in Fuhre oder Fuhrenort "));
		}
		
		
		if (	SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT)|| SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			//2011-12-09: suche nach kompletter buchungsnummer, einschliesslich position
			this.add_SearchElement("SELECT DISTINCT KP.ID_VKOPF_KON FROM JT_VPOS_KON KP LEFT OUTER JOIN JT_VKOPF_KON KK ON (KP.ID_VKOPF_KON=KK.ID_VKOPF_KON) "+
					" WHERE NVL(UPPER(KK.BUCHUNGSNUMMER),'')||'-'||TO_CHAR(KP.POSITIONSNUMMER) LIKE '%#WERT#%' AND KK.VORGANG_TYP="+
					bibALL.MakeSql(
							SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT)?
									myCONST.VORGANGSART_VK_KONTRAKT:
									myCONST.VORGANGSART_EK_KONTRAKT), 
					new MyE2_String("Suche nach Buchungsnummer/Position"));		
		}
		
		//2011-06-17: rebuild immer forcieren
		this.set_bForceListQueryOnSearch(true);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+" WHERE TO_CHAR("+cVKOPF_TAB+"."+cFieldName+") ='#WERT#' AND "+this.cWhereZusatz;
		else
			cSearch = "SELECT "+cVKOPF_TAB+"."+cVKOPF_PK_NAME+" FROM "+bibE2.cTO()+"."+cVKOPF_TAB+" WHERE UPPER("+cVKOPF_TAB+"."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereZusatz;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	
}
