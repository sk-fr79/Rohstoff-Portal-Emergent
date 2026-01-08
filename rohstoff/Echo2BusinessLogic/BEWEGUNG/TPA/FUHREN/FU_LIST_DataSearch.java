package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.exceptions.myException;


public class FU_LIST_DataSearch extends E2_DataSearch
{

	private FU_LIST_ModulContainer oModulContainerLIST = null;

	public FU_LIST_DataSearch(FU_LIST_ModulContainer modulContainerLIST, String MODULE_KENNER) throws myException
	{
		super("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE", MODULE_KENNER);
		this.oModulContainerLIST = modulContainerLIST;
		
		E2_NavigationList   			oNavList = this.oModulContainerLIST.get_oNavList();
		
		String cTO = bibE2.cTO();
		
		HashMap<String, MyE2IF__Component> oHelpHash = this.oModulContainerLIST.get_oE2_ComponentMAPList().get_REAL_ComponentHashMap();

		
		this.set_oSearchAgent(new E2_DataSearchAgentList(oNavList));
		
		String cQueryNameOrt_EK = "SELECT FVIEW.ID_VPOS_TPA_FUHRE "+ 
								" FROM"+ 
								" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FVIEW"+
								" WHERE UPPER(NVL(L_NAME1,'')) LIKE UPPER('%#WERT#%') OR "+
								"       UPPER(NVL(L_ORT,'')) LIKE UPPER('%#WERT#%')";
		
		String cQueryNameOrt_VK = "SELECT FVIEW.ID_VPOS_TPA_FUHRE "+ 
								" FROM"+ 
								" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FVIEW"+
								" WHERE UPPER(NVL(A_NAME1,'')) LIKE UPPER('%#WERT#%') OR "+
								"       UPPER(NVL(A_ORT,'')) LIKE UPPER('%#WERT#%')";

		String cQueryNameOrt_ZusatzOrt_EK = "SELECT FVIEW.ID_VPOS_TPA_FUHRE "+ 
									" FROM"+ 
									" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FVIEW"+
									" WHERE DEF_QUELLE_ZIEL='EK' AND  (UPPER(NVL(NAME1,'')) LIKE UPPER('%#WERT#%') OR "+
									"       UPPER(NVL(ORT,'')) LIKE UPPER('%#WERT#%'))";

		String cQueryNameOrt_ZusatzOrt_VK = "SELECT FVIEW.ID_VPOS_TPA_FUHRE "+ 
									" FROM"+ 
									" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FVIEW"+
									" WHERE DEF_QUELLE_ZIEL='VK' AND  (UPPER(NVL(NAME1,'')) LIKE UPPER('%#WERT#%') OR "+
									"       UPPER(NVL(ORT,'')) LIKE UPPER('%#WERT#%'))";


		//zusatz: suche nach id_adresse_start oder ziel
		String cQuery_id_adresse_start = "SELECT FU.ID_VPOS_TPA_FUHRE "+ 
											" FROM"+ 
											" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU "+
											" WHERE TO_CHAR(FU.ID_ADRESSE_START) LIKE '%#WERT#%'" +
											" UNION  " +
										" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO " +
											 " WHERE DEF_QUELLE_ZIEL='EK' AND TO_CHAR(FUO.ID_ADRESSE) LIKE '%#WERT#%'";

		String cQuery_id_adresse_ziel = "SELECT FU.ID_VPOS_TPA_FUHRE "+ 
											" FROM"+ 
											" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU "+
											" WHERE TO_CHAR(FU.ID_ADRESSE_ZIEL) LIKE '%#WERT#%'" +
											" UNION  " +
										" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO " +
											 " WHERE DEF_QUELLE_ZIEL='VK' AND TO_CHAR(FUO.ID_ADRESSE) LIKE '%#WERT#%'";

		
		
		
		
		
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("ID_VPOS_TPA_FUHRE"));
		
		this.add_SearchElement(cQueryNameOrt_EK,new MyE2_String("Abhol-Adresse (Lieferant)"));		
		this.add_SearchElement(cQueryNameOrt_VK,new MyE2_String("Liefer-Adresse (Abnehmer)"));		
		this.add_SearchElement(cQueryNameOrt_ZusatzOrt_EK,new MyE2_String("Abhol-Adresse (Zusatzort)"));		
		this.add_SearchElement(cQueryNameOrt_ZusatzOrt_VK,new MyE2_String("Liefer-Adresse (Zusatzort)"));		

		this.add_SearchElement(cQuery_id_adresse_start,new MyE2_String("Abhole-Adresse (ID)"));
		this.add_SearchElement(cQuery_id_adresse_ziel,new MyE2_String("Ziel-Adresse (ID)"));
		
		
		/*
		 * suche in vorgangsbuchungsnummer muss von hand definiert werden
		 */
		String cQuery = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  FROM " +
				     cTO+".JT_VPOS_TPA_FUHRE, "+cTO+".JT_VPOS_TPA, "+cTO+".JT_VKOPF_TPA WHERE JT_VPOS_TPA_FUHRE.ID_VPOS_TPA=JT_VPOS_TPA.ID_VPOS_TPA AND JT_VPOS_TPA.ID_VKOPF_TPA=JT_VKOPF_TPA.ID_VKOPF_TPA AND JT_VKOPF_TPA.BUCHUNGSNUMMER LIKE '%#WERT#%'";
		
		this.add_SearchElement(cQuery,new MyE2_String("Buchungsnummer TPA"));
		
		
		//ZEITSTEMPEL
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BUCHUNGSNR_FUHRE"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("TRANSPORTKENNZEICHEN"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("ANHAENGERKENNZEICHEN"));
		
		String cSearch = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE FROM " +
							cTO+".JT_ARTIKEL,"+cTO+".JT_VPOS_TPA_FUHRE WHERE JT_ARTIKEL.ID_ARTIKEL = JT_VPOS_TPA_FUHRE.ID_ARTIKEL AND UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String("Artikelbezeichnung 1"));
		

		
		String cQueryKosten = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  FROM " +
							cTO+".JT_VPOS_TPA_FUHRE, "+cTO+".JT_VPOS_TPA_FUHRE_KOSTEN WHERE JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE_KOSTEN.ID_VPOS_TPA_FUHRE " +
									" AND JT_VPOS_TPA_FUHRE_KOSTEN.FREMDBELEG_NUMMER LIKE '%#WERT#%'";
		
		this.add_SearchElement(cQueryKosten,new MyE2_String("Kosten Belegnummer"));
		
		
		
		String cQueryEAKCode ="SELECT "+ 
								" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE "+
								" FROM "+ 
								" "+cTO+".JT_EAK_CODE,"+ 
								" "+cTO+".JT_EAK_GRUPPE,"+ 
								" "+cTO+".JT_EAK_BRANCHE,"+ 
								" "+cTO+".JT_VPOS_TPA_FUHRE "+ 
								" WHERE "+ 
								" JT_VPOS_TPA_FUHRE.ID_EAK_CODE   	= JT_EAK_CODE.ID_EAK_CODE "+ 
								" AND JT_EAK_CODE.ID_EAK_GRUPPE    	= JT_EAK_GRUPPE.ID_EAK_GRUPPE "+ 
								" AND JT_EAK_GRUPPE.ID_EAK_BRANCHE 	= JT_EAK_BRANCHE.ID_EAK_BRANCHE "+ 
								" AND "+     
								"( JT_EAK_BRANCHE.KEY_BRANCHE||JT_EAK_GRUPPE.KEY_GRUPPE||JT_EAK_CODE.KEY_CODE LIKE '#WERT#%' OR "+
								" JT_EAK_BRANCHE.KEY_BRANCHE||' '||JT_EAK_GRUPPE.KEY_GRUPPE||' '||JT_EAK_CODE.KEY_CODE LIKE '#WERT#%' OR "+
								" JT_EAK_BRANCHE.KEY_BRANCHE||'-'||JT_EAK_GRUPPE.KEY_GRUPPE||'-'||JT_EAK_CODE.KEY_CODE LIKE '#WERT#%') ";
		
		this.add_SearchElement(cQueryEAKCode,new MyE2_String("AVV-Code"));
		
		

		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("FAHRPLANGRUPPE_FP"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("TAETIGKEIT_FP"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BEMERKUNG"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("DAT_FAHRPLAN_FP"));

		String cQuery01 = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  FROM "+
									bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
									" JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP=JT_MASCHINEN.ID_MASCHINEN AND " +
									" UPPER(JT_MASCHINEN.KFZKENNZEICHEN) like upper('%#WERT#%')";
		String cQuery02 = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  FROM "+
									bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
									" JT_VPOS_TPA_FUHRE.ID_MASCHINEN_ANH_FP=JT_MASCHINEN.ID_MASCHINEN AND " +
									" UPPER(JT_MASCHINEN.KFZKENNZEICHEN) like upper('%#WERT#%')";



		String cQuery03 = "SELECT     FVIEW.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FVIEW "+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ADR_START ON (ADR_START.ID_ADRESSE=FVIEW.ID_ADRESSE_START) "+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ADR_ZIEL ON (ADR_ZIEL.ID_ADRESSE=FVIEW.ID_ADRESSE_ZIEL) "+
									" WHERE " +
									" UPPER(ADR_START.NAME1) LIKE UPPER('%#WERT#%') OR "+
									" UPPER(ADR_START.ORT)  LIKE UPPER('%#WERT#%') OR "+
									" UPPER(ADR_ZIEL.NAME1) LIKE UPPER('%#WERT#%') OR "+
									" UPPER(ADR_ZIEL.ORT) LIKE UPPER('%#WERT#%')"+
									" UNION "+
									" SELECT JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ON (JT_ADRESSE.ID_ADRESSE=JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE) "+
									" WHERE " +
									" UPPER(JT_ADRESSE.NAME1) LIKE UPPER('%#WERT#%') OR "+
									" UPPER(JT_ADRESSE.ORT)  LIKE UPPER('%#WERT#%')";
									

		String cQuery07 = 	"SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE TO_CHAR(DAT_VORGEMERKT_FP,'DD.MM.YYYY') LIKE '%#WERT#%' OR "+
													"TO_CHAR(DAT_VORGEMERKT_FP,'DDMMYYYY') = '#WERT#' OR "+
													"TO_CHAR(DAT_VORGEMERKT_FP,'DDMMYY') = '#WERT#'";

		String cQuery08 = 	"SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE TO_CHAR(DAT_VORGEMERKT_ENDE_FP,'DD.MM.YYYY') LIKE '%#WERT#%' OR "+
													"TO_CHAR(DAT_VORGEMERKT_ENDE_FP,'DDMMYYYY') = '#WERT#' OR "+
													"TO_CHAR(DAT_VORGEMERKT_ENDE_FP,'DDMMYY') = '#WERT#'";
		
		
		String cQuery09 = "SELECT FVIEW.ID_VPOS_TPA_FUHRE"+ 
									" FROM"+ 
									" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FVIEW"+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_KON   KON_EK  ON (FVIEW.ID_VPOS_KON_EK= KON_EK.ID_VPOS_KON)"+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_KON   KON_VK  ON (FVIEW.ID_VPOS_KON_VK= KON_VK.ID_VPOS_KON)"+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_KON   K_KON_EK  ON (K_KON_EK.ID_VKOPF_KON= KON_EK.ID_VKOPF_KON)"+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_KON   K_KON_VK  ON (K_KON_VK.ID_VKOPF_KON= KON_VK.ID_VKOPF_KON)"+
									" WHERE  TO_CHAR(FVIEW.ID_VPOS_KON_EK) LIKE '%#WERT#%'"+
									" OR        TO_CHAR(FVIEW.ID_VPOS_KON_VK) LIKE '%#WERT#%'"+
									" OR        NVL(K_KON_EK.BUCHUNGSNUMMER,'')  LIKE '%#WERT#%'"+
									" OR        NVL(K_KON_VK.BUCHUNGSNUMMER,'')  LIKE '%#WERT#%'";
		
		String cQuery10 = "SELECT    JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE "+ 
									" FROM"+ 
									" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_KON   KON     ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON = KON.ID_VPOS_KON)"+
									" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_KON  K_KON   ON (K_KON.ID_VKOPF_KON= KON.ID_VKOPF_KON)"+
									" WHERE     TO_CHAR(JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON) LIKE '%#WERT#%'"+
									" OR        NVL(K_KON.BUCHUNGSNUMMER,'')  LIKE '%#WERT#%'";
		
		
	
		String cQuery11 = " SELECT "+
						"    JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE "+
						" FROM "+
						"    JT_FBAM "+
						" LEFT OUTER JOIN  JT_VPOS_TPA_FUHRE   ON   ( JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) "+
						" WHERE "+
						"    (UPPER(JT_FBAM.BUCHUNGSNUMMER)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.FESTSTELLUNG_BAM)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.BETREFF_BAM)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.FEHLERBESCHREIBUNG)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.WM_UEBERNAHMEVORSCHLAG)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.FEHLERURSACHE)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.AUSWIRKUNGEN)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.BEHEB_VORSCHLAG)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.BEHEB_VERMERK)  LIKE UPPER('%#WERT#%')) "+
						"    AND JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IS NOT NULL"+
						" UNION "+
						" SELECT  "+
						"    JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE  "+
						" FROM  "+
						"    JT_FBAM  "+
						" LEFT OUTER JOIN  JT_VPOS_TPA_FUHRE_ORT   ON   ( JT_FBAM.ID_VPOS_TPA_FUHRE_ORT = JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE_ORT) "+
						" LEFT OUTER JOIN  JT_VPOS_TPA_FUHRE          ON   (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) "+
						" WHERE  "+
						"    (UPPER(JT_FBAM.BUCHUNGSNUMMER)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.FESTSTELLUNG_BAM)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.BETREFF_BAM)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.FEHLERBESCHREIBUNG)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.WM_UEBERNAHMEVORSCHLAG)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.FEHLERURSACHE)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.AUSWIRKUNGEN)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.BEHEB_VORSCHLAG)  LIKE UPPER('%#WERT#%') OR "+
						"    UPPER(JT_FBAM.BEHEB_VERMERK)  LIKE UPPER('%#WERT#%')) " +
						"    AND JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IS NOT NULL";
		
		
		//aenderung 2010-11-25: Suche nach wiegeschein-nummern WIEGEKARTENKENNER_ABLADEN / 	WIEGEKARTENKENNER_LADEN
		String cQuery12 = "SELECT     JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+
						" WHERE " +
						" UPPER(JT_VPOS_TPA_FUHRE.WIEGEKARTENKENNER_LADEN) LIKE UPPER('%#WERT#%') OR "+
						" UPPER(JT_VPOS_TPA_FUHRE.WIEGEKARTENKENNER_ABLADEN)  LIKE UPPER('%#WERT#%')"+
						" UNION "+
						" SELECT JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+
						" WHERE " +
						" UPPER(JT_VPOS_TPA_FUHRE_ORT.WIEGEKARTENKENNER) LIKE UPPER('%#WERT#%')";

		
		
		this.add_SearchElement(cQuery01,new MyE2_String("LKW-Nummer (Fahrplan)"));
		this.add_SearchElement(cQuery02,new MyE2_String("Anhänger-Nummer (Fahrplan)"));
		this.add_SearchElement(cQuery03,new MyE2_String("Start/Ziel-Adresse"));

		this.add_SearchElement(cQuery07,new MyE2_String("Vormerkdatum (FP) von"));
		this.add_SearchElement(cQuery08,new MyE2_String("Vormerkdatum (FP) bis"));
		
		this.add_SearchElement(cQuery09,new MyE2_String("Kontr-Pos-ID oder Buchungsnr. (Hauptpos.)"));
		this.add_SearchElement(cQuery10,new MyE2_String("Kontr-Pos-ID oder Buchungsnr. (Zusatzpos.)"));

		this.add_SearchElement(cQuery11,new MyE2_String("BAM (Buchungsnummer oder Text)"));

		this.add_SearchElement(cQuery12,new MyE2_String("Wiegekarte (EK/VK oder ORT)"));
		
		
		//2011-11-22: postennummer suchen
		String cQuery_postennummer = "SELECT FU.ID_VPOS_TPA_FUHRE "+ 
											" FROM"+ 
											" "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU "+
											" WHERE (FU.POSTENNUMMER_EK LIKE '%#WERT#%' OR FU.POSTENNUMMER_VK LIKE '%#WERT#%' )" +
											" UNION  " +
										" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO " +
											 " WHERE FUO.POSTENNUMMER LIKE '%#WERT#%'";

		this.add_SearchElement(cQuery_postennummer,new MyE2_String("Posten-/externe Eingangsnummer"));
		
		
		this.DO_EnableSearchFieldWithLabelText("Kosten Belegnummer",false);    
		this.DO_EnableSearchFieldWithLabelText("AVV-Code",false);
		this.DO_EnableSearchFieldWithLabelText("BAM (Buchungsnummer oder Text)",false);
		
		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

}
