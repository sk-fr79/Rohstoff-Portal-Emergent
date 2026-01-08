package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN.TP_KST__CONST;


public class BSRG__CONST
{
	
	public static final String HASH_KEY_DATUM_VON_BIS = 							"HASH_KEY_DATUM_VON_BIS";
	public static final String HASH_KEY_ANZEIGE_LOCKED = 							"HASH_KEY_ANZEIGE_LOCKED";
	public static final String HASH_KEY_ANZEIGE_POSITIONS = 						"HASH_KEY_ANZEIGE_POSITIONS";
	public static final String HASH_KEY_ANZEIGE_STORNO_STATUS = 					"HASH_KEY_ANZEIGE_STORNO_STATUS";
	
	
	public static final String HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS =				"HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS";
	public static final String HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS =				"HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS";
	
	public static final String HASH_KEY_JUMPER_RG_KOPF = 							"HASH_KEY_JUMPER_RG_KOPF";
	
	
	public static E2_ResourceIcon LABEL_EMPTY = 									E2_ResourceIcon.get_RI("empty20.png")	;
	public static E2_ResourceIcon LABEL_POSITION_LOCKED  = 							E2_ResourceIcon.get_RI("locked.png")	;

	public static final String HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL = 				"HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL";
	public static final String HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL = 	"HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL";

	public static final String HASH_KEY_DAUGHTERTABLE_POSITIONS = 					"HASH_KEY_DAUGHTERTABLE_POSITIONS";
	
	
	public static final String HASH_KEY_SELECT_USTID = 								"HASH_KEY_SELECT_USTID";
	public static final String HASH_KEY_SELECT_USTID_MANDANT = 						"HASH_KEY_SELECT_USTID_MANDANT";
	

	//2015-01-15: weitere Spalte mit der ID_VKOPF_RG
	public static final String HASH_KEY_ID_VKOPF_RG_2 = 							"HASH_KEY_ID_VKOPF_RG_2";
	
	// einige fuer die tochtermaske
	public static final String HASH_KEY_DAUGHTER_ABZUEGE_IN_POS = 					"HASH_KEY_DAUGHTER_ABZUEGE_IN_POS";
	
	
	// fuer die freien-positionen
	public static String BSFP_MODULE_CONTAINER_LIST = 			"BSFP_MODULE_CONTAINER_LIST";
	
	public static String COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG = 	"COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG";
	public static String COMPONENTKEY_ANZEIGE_HAT_ABZUEGE = 	"COMPONENTKEY_ANZEIGE_HAT_ABZUEGE";
	public static String COMPONENTKEY_ANZEIGE_IST_STORNO_POS = 	"COMPONENTKEY_ANZEIGE_IST_STORNO_POS";
	public static String COMPONENTKEY_ANZEIGE_JUMP_TO_FUHRE = 	"COMPONENTKEY_ANZEIGE_JUMP_TO_FUHRE";
	
	public static String COMPONENTKEY_INPUT_BUTTON_PREIS_MWST = "COMPONENTKEY_INPUT_BUTTON_PREIS_MWST";
	
//	public static String COMPONENTKEY_BUTTON_AKTIV_INAKTIV = 			"COMPONENTKEY_ANZEIGE_AKTIV";
	
	public static String HASHKEY_COLUMN_UNSEND_EMAILS = 		"HASHKEY_COLUMN_UNSEND_EMAILS";
	
	//2018-06-12: spalte mit anzeige der fremdwaehrungsinfo
	public static String HASHKEY_COLUMN_CURRENCYS = 			"HASHKEY_COLUMN_CURRENCYS";
	
	
	public static E2_ResourceIcon  ICON_LEER = E2_ResourceIcon.get_RI("leer.png");
	public static E2_ResourceIcon  ICON_LEERKLEIN = E2_ResourceIcon.get_RI("empty10.png");
	
	public static E2_ResourceIcon  ICON_ZUGANG = E2_ResourceIcon.get_RI("freie_pos_lagerzugang.png");
	public static E2_ResourceIcon  ICON_ABGANG = E2_ResourceIcon.get_RI("freie_pos_lagerabgang.png");
	public static E2_ResourceIcon  ICON_STRECKE_RECHNUNG = E2_ResourceIcon.get_RI("freie_pos_strecke_rechnung.png");
	public static E2_ResourceIcon  ICON_STRECKE_GUTSCHRIFT = E2_ResourceIcon.get_RI("freie_pos_strecke_gutschrift.png");

	public static E2_ResourceIcon  ICON_ZUGANG_MK = E2_ResourceIcon.get_RI("freie_pos_lagerzugang_mit_kopf.png");
	public static E2_ResourceIcon  ICON_ABGANG_MK = E2_ResourceIcon.get_RI("freie_pos_lagerabgang_mit_kopf.png");
	public static E2_ResourceIcon  ICON_STRECKE_RECHNUNG_MK = E2_ResourceIcon.get_RI("freie_pos_strecke_rechnung_mit_kopf.png");
	public static E2_ResourceIcon  ICON_STRECKE_GUTSCHRIFT_MK = E2_ResourceIcon.get_RI("freie_pos_strecke_gutschrift_mit_kopf.png");

	public static E2_ResourceIcon  ICON_WARNUNG = E2_ResourceIcon.get_RI("warnschild_16.png");

	public static E2_ResourceIcon  ICON_AKTIV = E2_ResourceIcon.get_RI("aktiv.png");
	public static E2_ResourceIcon  ICON_INAKTIV = E2_ResourceIcon.get_RI("inaktiv.png");
	public static E2_ResourceIcon  ICON_AKTIV_MIT_KOPF = E2_ResourceIcon.get_RI("aktiv_mit_kopfsatz.png");
	
	public static E2_ResourceIcon  ICON_HAT_ABZUEGE = E2_ResourceIcon.get_RI("abzuege.png");
	
	public static final String HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT =		"HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT";

	
	
	
	public enum SQLFIELDMAP_FIELDS_RG {
		
		LEISTUNGSDAT_MIN(
				"(SELECT MIN(RP.AUSFUEHRUNGSDATUM)  FROM "+bibE2.cTO()+
				".JT_VPOS_RG RP WHERE RP.ID_VKOPF_RG=JT_VKOPF_RG.ID_VKOPF_RG AND NVL(RP.DELETED,'N')='N')" ,new MyE2_String("L.Dat"))
		; 
		
		private String 			SqlQuery = null;
		private MyE2_String     Description = null;
		
		SQLFIELDMAP_FIELDS_RG(String sqlQuery, MyE2_String description) {
			this.SqlQuery = sqlQuery;
			this.Description = description;
		}

		public String _hash() {
			return this.name();
		}

		public String _query() {
			return SqlQuery;
		}

		public MyE2_String _descript() {
			return Description;
		}
	}

	
	
	

	/**
	 * zentralisierte Methode, um die Abfrage nach einem Rechnungsendbetrag zu ermoeglichen
	 * @param VORGANGSART
	 * @param SQL_BEZEICHNER_FUER_ID_VKOPF_RG (kann z.b. sein "JT_VKOPF_RG.ID_VKOPF_RG"  oder eine reale unformatierte ID)
	 * @param Umklammern  (wenn die abfrage in klammern uebergeben werden soll)
	 * @return Abfrage, die bei normalen Rechnungen und normalen gutschriften jeweils einen positiven betrag liefert
	 * @throws myException
	 */
	public static String get_SQL_4_RECH_GUT_ENDBETRAG_BRUTTO_FW(String VORGANGSART, String SQL_BEZEICHNER_FUER_ID_VKOPF_RG, boolean Umklammern) throws myException
	{
		if (VORGANGSART.equals(myCONST.VORGANGSART_RECHNUNG) || VORGANGSART.equals(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			String cMultiplikator = VORGANGSART.equals(myCONST.VORGANGSART_RECHNUNG)?"-1":"1";
			
			String cQuery4Gesamtbetrag = "SELECT "+
										" ROUND((  "+
										" SUM( "+
										" VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN-NVL(VP.GESAMTPREIS_ABZUG_FW,0)*VP.LAGER_VORZEICHEN" +
										" )+ "+
										" ROUND(SUM((VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN-NVL(VP.GESAMTPREIS_ABZUG_FW*VP.LAGER_VORZEICHEN,0))*NVL(VP.STEUERSATZ,0)/100),2) "+
										" )*"+cMultiplikator+",2) "+
										" AS GESAMTPREIS_RG "+
										" FROM "+
										" "+bibE2.cTO()+".JT_VPOS_RG VP LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_RG VK ON (VP.ID_VKOPF_RG=VK.ID_VKOPF_RG) "+
										" WHERE "+
										" VK.ID_VKOPF_RG = "+SQL_BEZEICHNER_FUER_ID_VKOPF_RG+" "+
										" AND "+
										" VP.POSITION_TYP = 'ARTIKEL' "+
										" AND "+
										" NVL(VP.DELETED,'N')='N'";
			
			if (Umklammern)
			{
				cQuery4Gesamtbetrag = "("+cQuery4Gesamtbetrag+")";
			}
			return cQuery4Gesamtbetrag;
		}
		else
		{
			throw new myException("BSRG_CONST.get_SQL_4_RECH_GUT_ENDBETRAG: Only VORGANGSART_RECHNUNG/VORGANGSART_GUTSCHRIFT allowed !");
		}
		
	}
	
	
	
	/**
	 * zentralisierte Methode, um die Abfrage nach einem Rechnungsendbetrag zu ermoeglichen
	 * @param VORGANGSART
	 * @param SQL_BEZEICHNER_FUER_ID_VKOPF_RG (kann z.b. sein "JT_VKOPF_RG.ID_VKOPF_RG"  oder eine reale unformatierte ID)
	 * @param Umklammern  (wenn die abfrage in klammern uebergeben werden soll)
	 * @return Abfrage, die bei normalen Rechnungen und normalen gutschriften jeweils einen positiven betrag liefert
	 * @throws myException
	 */
	public static String get_SQL_4_RECH_GUT_ENDBETRAG_BRUTTO_FW_NUR_REALE_ABZUEGE(String VORGANGSART, String SQL_BEZEICHNER_FUER_ID_VKOPF_RG, boolean Umklammern) throws myException
	{
		if (VORGANGSART.equals(myCONST.VORGANGSART_RECHNUNG) || VORGANGSART.equals(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			String cMultiplikator = VORGANGSART.equals(myCONST.VORGANGSART_RECHNUNG)?"-1":"1";
			
			String cQuery4Gesamtbetrag = "SELECT "+
										" ROUND((  "+
										" SUM( "+
										"VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN-NVL(VP.GPREIS_ABZ_MGE_FW,0)*VP.LAGER_VORZEICHEN-NVL(VP.GPREIS_ABZ_AUF_NETTOMGE_FW,0)*VP.LAGER_VORZEICHEN" +
										")+ "+
										"ROUND(SUM((" +
										"VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN-NVL(VP.GPREIS_ABZ_MGE_FW,0)*VP.LAGER_VORZEICHEN-NVL(VP.GPREIS_ABZ_AUF_NETTOMGE_FW,0)*VP.LAGER_VORZEICHEN" +
										")*NVL(VP.STEUERSATZ,0)/100),2) "+
										" )*"+cMultiplikator+",2) "+
										" AS GESAMTPREIS_RG "+
										" FROM "+
										" "+bibE2.cTO()+".JT_VPOS_RG VP LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_RG VK ON (VP.ID_VKOPF_RG=VK.ID_VKOPF_RG) "+
										" WHERE "+
										" VK.ID_VKOPF_RG = "+SQL_BEZEICHNER_FUER_ID_VKOPF_RG+" "+
										" AND "+
										" VP.POSITION_TYP = 'ARTIKEL' "+
										" AND "+
										" NVL(VP.DELETED,'N')='N'";
			
			if (Umklammern)
			{
				cQuery4Gesamtbetrag = "("+cQuery4Gesamtbetrag+")";
			}
			return cQuery4Gesamtbetrag;
		}
		else
		{
			throw new myException("BSRG_CONST.get_SQL_4_RECH_GUT_ENDBETRAG: Only VORGANGSART_RECHNUNG/VORGANGSART_GUTSCHRIFT allowed !");
		}
		
	}

	
	
	
	
	
}
