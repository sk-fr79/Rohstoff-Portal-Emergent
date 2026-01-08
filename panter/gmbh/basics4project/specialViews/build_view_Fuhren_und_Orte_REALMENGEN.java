package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_Fuhren_und_Orte_REALMENGEN extends XXX_ViewBuilder
{

	private String[][] cDefArray = 
			{
			{"ID_VPOS_TPA_FUHRE",			"",							""},
			{"",							"ID_VPOS_TPA_FUHRE_ORT",	"ID_VPOS_TPA_FUHRE_ORT"},
			{"",							"DEF_QUELLE_ZIEL",			"DEF_QUELLE_ZIEL"},
			{"ID_MANDANT",					"ID_MANDANT",				"ID_MANDANT"},
			{"LETZTE_AENDERUNG",			"LETZTE_AENDERUNG",			"LETZTE_AENDERUNG"},
			{"GEAENDERT_VON",				"GEAENDERT_VON",			"GEAENDERT_VON"},
			{"ID_VPOS_TPA",					"",							""},
			{"ID_ADRESSE_START",			"ID_ADRESSE",				""},
			{"ID_ADRESSE_ZIEL",				"",							"ID_ADRESSE"},
			{"ID_ADRESSE_LAGER_START",		"ID_ADRESSE_LAGER",			""},
			{"ID_ADRESSE_LAGER_ZIEL",		"",							"ID_ADRESSE_LAGER"},
			{"ID_VPOS_KON_EK",				"ID_VPOS_KON",				""},
			{"ID_VPOS_KON_VK",				"",							"ID_VPOS_KON"},
			{"L_NAME1",						"NAME1",					""},
			{"L_NAME2",						"NAME2",					""},
			{"L_NAME3",						"NAME3",					""},
			{"L_STRASSE",					"STRASSE",					""},
			{"L_HAUSNUMMER",				"HAUSNUMMER",				""},
			{"L_PLZ",						"PLZ",						""},
			{"L_ORT",						"ORT",						""},
			{"L_ORTZUSATZ",					"ORTZUSATZ",				""},
			{"L_LAENDERCODE",				"LAENDERCODE",				""},
			{"A_NAME1",						"",							"NAME1"},
			{"A_NAME2",						"",							"NAME2"},
			{"A_NAME3",						"",							"NAME3"},
			{"A_STRASSE",					"",							"STRASSE"},
			{"A_HAUSNUMMER",				"",							"HAUSNUMMER"},
			{"A_PLZ",						"",							"PLZ"},
			{"A_ORT",						"",							"ORT"},
			{"A_ORTZUSATZ",					"",							"ORTZUSATZ"},
			{"A_LAENDERCODE",				"",							"LAENDERCODE"},
			{"DATUM_ABHOLUNG",				"DATUM_LADE_ABLADE",		""},
			{"DATUM_ANLIEFERUNG",			"",							"DATUM_LADE_ABLADE"},
			{"TEL_LIEFERANT",				"TEL",						""},
			{"TEL_ABNEHMER",				"",							"TEL"},
			{"TRANSPORTKENNZEICHEN",		"",							""},
			{"ANHAENGERKENNZEICHEN",		"",							""},
			{"MENGE_VORGABE_KO",			"",							""},
			{"MENGE_AUFLADEN_KO",			"",							""},
			{"MENGE_ABLADEN_KO",			"",							""},
			{"OEFFNUNGSZEITEN_LIEF",		"OEFFNUNGSZEITEN",			""},
			{"OEFFNUNGSZEITEN_ABN",			"",							"OEFFNUNGSZEITEN"},
			{"FAX_LIEFERANT",				"FAX",						""},
			{"FAX_ABNEHMER",				"",							"FAX"},
			{"BESTELLNUMMER_EK",			"BESTELLNUMMER",			""},
			{"BESTELLNUMMER_VK",			"",							"BESTELLNUMMER"},
			//2011-11-22: postennummer
			{"POSTENNUMMER_EK",				"POSTENNUMMER",				""},
			{"POSTENNUMMER_VK",				"",							"POSTENNUMMER"},
			{"TRANSPORTMITTEL",				"",							""},
			{"DATUM_ABHOLUNG_ENDE",			"",							""},
			{"DATUM_ANLIEFERUNG_ENDE",		"",							""},
			{"FUHRE_IST_UMGELEITET",		"",							""},
			{"ID_ARTIKEL",					"ID_ARTIKEL",				"ID_ARTIKEL"},
			{"ARTBEZ1_EK",					"ARTBEZ1",					""},
			{"ARTBEZ2_EK",					"ARTBEZ2",					""},
			{"ARTBEZ1_VK",					"",							"ARTBEZ1"},
			{"ARTBEZ2_VK",					"",							"ARTBEZ2"},
			{"BUCHUNGSNR_FUHRE",			"",							""},
			{"ZOLLTARIFNR",					"",							""},
			{"EINHEIT_MENGEN",				"EINHEIT_MENGEN",			"EINHEIT_MENGEN"},
			{"EUNOTIZ",						"EUNOTIZ",					""},
			{"EUCODE",						"EUCODE",					""},
			{"EK_KONTRAKTNR_ZUSATZ",		"",							""},
			{"VK_KONTRAKTNR_ZUSATZ",		"",							""},
			{"DATUM_ABLADEN",				"",							"DATUM_LADE_ABLADE"},
			{"DATUM_AUFLADEN",				"DATUM_LADE_ABLADE",		""},
			{"AUFLADEN_BRUTTO",				"",							""},
			{"AUFLADEN_TARA",				"",							""},
			{"ABLADEN_BRUTTO",				"",							""},
			{"ABLADEN_TARA",				"",							""},
			{"FUHRENGRUPPE",				"",							""},
			{"BEMERKUNG",					"BEMERKUNG",				"BEMERKUNG"},
			{"ABGESCHLOSSEN",				"",							""},
			{"ID_ARTIKEL_BEZ_EK",			"ID_ARTIKEL_BEZ",			""},
			{"ANR1_EK",						"ANR1",						""},
			{"ANR2_EK",						"ANR2",						""},
			{"ID_ARTIKEL_BEZ_VK",			"",							"ID_ARTIKEL_BEZ"},
			{"ANR1_VK",						"",							"ANR1"},
			{"ANR2_VK",						"",							"ANR2"},
			{"EK_VK_SORTE_LOCK",			"",							""},
			{"ID_ADRESSE_SPEDITION",		"",							""},
			{"ID_EAK_CODE",					"ID_EAK_CODE",				"ID_EAK_CODE"},
			{"LAENDERCODE_TRANSIT1",		"LAENDERCODE_TRANSIT1",		"LAENDERCODE_TRANSIT1"},
			{"LAENDERCODE_TRANSIT2",		"LAENDERCODE_TRANSIT2",		"LAENDERCODE_TRANSIT2"},
			{"LAENDERCODE_TRANSIT3",		"LAENDERCODE_TRANSIT3",		"LAENDERCODE_TRANSIT3"},
			{"BASEL_CODE",					"BASEL_CODE",				"BASEL_CODE"},
			{"BASEL_NOTIZ",					"BASEL_NOTIZ",				"BASEL_NOTIZ"},
			{"PRINT_EU_AMTSBLATT",			"PRINT_EU_AMTSBLATT",		"PRINT_EU_AMTSBLATT"},
			{"EU_BLATT_MENGE",				"EU_BLATT_MENGE",			"EU_BLATT_MENGE"},
			{"EU_BLATT_VOLUMEN",			"EU_BLATT_VOLUMEN",			"EU_BLATT_VOLUMEN"},
			{"NATIONALER_ABFALL_CODE",		"NATIONALER_ABFALL_CODE",	"NATIONALER_ABFALL_CODE"},
			{"ALTE_LIEFERSCHEIN_NR",		"",							""},
			{"AVV_AUSSTELLUNG_DATUM",		"AVV_AUSSTELLUNG_DATUM",	"AVV_AUSSTELLUNG_DATUM"},
			{"NOTIFIKATION_NR",				"NOTIFIKATION_NR",			"NOTIFIKATION_NR"},
			{"ZEITSTEMPEL",					"ZEITSTEMPEL",				"ZEITSTEMPEL"},
			{"OHNE_ABRECHNUNG",				"OHNE_ABRECHNUNG",			"OHNE_ABRECHNUNG"},
			{"ERZEUGT_VON",					"ERZEUGT_VON",				"ERZEUGT_VON"},
			{"OHNE_AVV_VERTRAG_CHECK",		"OHNE_AVV_VERTRAG_CHECK",	"OHNE_AVV_VERTRAG_CHECK"},
			{"ID_ADRESSE_FREMDAUFTRAG",		"",							""},
			{"LADEMENGE_GUTSCHRIFT",		"LADEMENGE_GUTSCHRIFT",		"LADEMENGE_GUTSCHRIFT"},
			{"ABLADEMENGE_RECHNUNG",		"ABLADEMENGE_RECHNUNG",		"ABLADEMENGE_RECHNUNG"},
			{"STATUS_BUCHUNG",				"",							""},
			{"ID_VPOS_TPA_FUHRE_SONDER",	"ID_VPOS_TPA_FUHRE_SONDER",	"ID_VPOS_TPA_FUHRE_SONDER"},
			{"KEIN_KONTRAKT_NOETIG",		"KEIN_KONTRAKT_NOETIG",		"KEIN_KONTRAKT_NOETIG"},
			{"IST_STORNIERT",				"",							""},
			{"STORNO_GRUND",				"",							""},
			{"STORNO_KUERZEL",				"",							""},
			{"ANTEIL_PLANMENGE_LIEF",		"ANTEIL_PLANMENGE",			"@0"},
			{"ANTEIL_LADEMENGE_LIEF",		"ANTEIL_LADEMENGE",			"@0"},
			{"ANTEIL_ABLADEMENGE_LIEF",		"ANTEIL_ABLADEMENGE",		"@0"},
			{"ANTEIL_PLANMENGE_ABN",		"@0",						"ANTEIL_PLANMENGE"},
			{"ANTEIL_LADEMENGE_ABN",		"@0",						"ANTEIL_LADEMENGE"},
			{"ANTEIL_ABLADEMENGE_ABN",		"@0",						"ANTEIL_ABLADEMENGE"},
			{"LIEFERINFO_TPA",				"",							""},
			{"WIEGEKARTENKENNER_LADEN",		"WIEGEKARTENKENNER",		""},
			{"WIEGEKARTENKENNER_ABLADEN",	"",							"WIEGEKARTENKENNER"},
			{"DAT_VORGEMERKT_FP",			"",							""},
			{"DAT_VORGEMERKT_ENDE_FP",		"",							""},
			{"DAT_FAHRPLAN_FP",				"",							""},
			{"ID_MASCHINEN_LKW_FP",			"",							""},
			{"ID_MASCHINEN_ANH_FP",			"",							""},
			{"ID_CONTAINERTYP_FP",			"",							""},
			{"TAETIGKEIT_FP",				"",							""},
			{"FAHRPLANGRUPPE_FP",			"",							""},
			{"EAN_CODE_FP",					"",							""},
			{"FAHRT_ANFANG_FP",				"",							""},
			{"FAHRT_ENDE_FP",				"",							""},
			//2021-01-07-neue fahrplan-zeitangabe
			{"ID_FAHRPLAN_ZEITANGABE",		"",							""},
			{"ZEITANGABE",					"",							""},

			{"ERFASSER_FP",					"",							""},
			{"IST_GEPLANT_FP",				"",							""},
			{"SORTIERUNG_FP",				"",							""},
			{"ANRUFER_FP",					"",							""},
			{"ANRUFDATUM_FP",				"",							""},
			{"BEMERKUNG_START_FP",			"",							""},
			{"BEMERKUNG_ZIEL_FP",			"",							""},
			{"KENNER_ALTE_SAETZE_FP",		"",							""},
			{"FAHRER_FP",					"",							""},
			{"FUHRE_AUS_FAHRPLAN",			"",							""},
			{"FUHRE_KOMPLETT",				"",							""},
			{"ORDNUNGSNUMMER_CMR",			"",							""},
			{"ANZAHL_CONTAINER_FP",			"",							""},
			{"ALT_WIRD_NICHT_GEBUCHT",		"",							""},
			{"EK_VK_ZUORD_ZWANG",			"",							""},
			{"ERZEUGT_AM",					"ERZEUGT_AM",				"ERZEUGT_AM"},
			{"",							"BUCHUNGSNUMMER_ZUSATZ",	"BUCHUNGSNUMMER_ZUSATZ"},
			{"ID_WIEGEKARTE_LIEF",			"ID_WIEGEKARTE",			""},
			{"ID_WIEGEKARTE_ABN",			"",							"ID_WIEGEKARTE"},
			
			{"ABZUG_LADEMENGE_LIEF",		"ABZUG_MENGE",				"@0"},                  //neue felder fuhren und fuhrenorte
			{"ABZUG_ABLADEMENGE_ABN",		"@0",						"ABZUG_MENGE"},
			{"ID_VPOS_STD_EK",			    "ID_VPOS_STD",				""},
			{"ID_VPOS_STD_VK",			    "",							"ID_VPOS_STD"},
			{"MANUELL_PREIS_EK",			"MANUELL_PREIS",			""},
			{"MANUELL_PREIS_VK",			"",							"MANUELL_PREIS"},
			{"EINZELPREIS_EK",			    "EINZELPREIS",				""},
			{"EINZELPREIS_VK",				"",							"EINZELPREIS"},
			{"STEUERSATZ_EK",			    "STEUERSATZ",				""},
			{"STEUERSATZ_VK",				"",							"STEUERSATZ"},
			{"EU_STEUER_VERMERK_EK",		"EU_STEUER_VERMERK",		""},
			{"EU_STEUER_VERMERK_VK",		"",							"EU_STEUER_VERMERK"},
			{"PRUEFUNG_LADEMENGE",			"PRUEFUNG_MENGE",			""},
			{"PRUEFUNG_LADEMENGE_VON",		"PRUEFUNG_MENGE_VON",		""},
			{"PRUEFUNG_LADEMENGE_AM",		"PRUEFUNG_MENGE_AM",		""},
			{"PRUEFUNG_ABLADEMENGE",		"",							"PRUEFUNG_MENGE"},
			{"PRUEFUNG_ABLADEMENGE_VON",	"",							"PRUEFUNG_MENGE_VON"},
			{"PRUEFUNG_ABLADEMENGE_AM",		"",							"PRUEFUNG_MENGE_AM"},
			{"SPEICHERN_TROTZ_INKONSISTENZ","",							""},
			{"ID_UMA_KONTRAKT",				"",							""},                      //2012-01-05: UMA-Kontrakte
			
			{"ID_TAX_EK",					"ID_TAX",					""},					  //2013-03-21: restliche Felder	                      
			{"ID_TAX_VK",					"",							"ID_TAX"},                      
			{"TP_VERANTWORTUNG",			"",							""},                      
			{"INTRASTAT_MELD_IN",			"INTRASTAT_MELD",			""},                      
			{"INTRASTAT_MELD_OUT",			"",							"INTRASTAT_MELD"},                      
			{"PRUEFUNG_EK_PREIS",			"PRUEFUNG_PREIS",			""},                      
			{"PRUEFUNG_EK_PREIS_VON",		"PRUEFUNG_PREIS_VON",		""},                      
			{"PRUEFUNG_EK_PREIS_AM",		"PRUEFUNG_PREIS_AM",		""},                      
			{"PRUEFUNG_VK_PREIS",			"",							"PRUEFUNG_PREIS"},                      
			{"PRUEFUNG_VK_PREIS_VON",		"",							"PRUEFUNG_PREIS_VON"},                      
			{"PRUEFUNG_VK_PREIS_AM",		"",							"PRUEFUNG_PREIS_AM"},                      
			{"SCHLIESSE_FUHRE",				"",							""},                      
			{"SCHLIESSE_FUHRE_VON",			"",							""},                      
			{"SCHLIESSE_FUHRE_AM",			"",							""},                      
			{"TRANSIT_EK",					"TRANSIT",					""},                      
			{"TRANSIT_VK",					"",							"TRANSIT"},                      
			};
	
	
	
	
	@Override
	public boolean build_View_forAll_Mandants() throws myException
	{
		
		RECLIST_MANDANT  oRecList = new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		
		String c_SQL =  	 " CREATE OR REPLACE VIEW V#MANDANT#_REALGEWICHTE AS "+	
									"  (SELECT "+ 
									this.CreateBaseFieldList()+
									" FROM "+ 
									bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE JT_VPOS_TPA_FUHRE.ID_MANDANT=#MANDANT#) "+
									"UNION "+
									"(SELECT "+                       
									this.CreateEKFieldList()+
									" FROM "+ 
									bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+
									"left outer join "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) "+
									"WHERE  JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL='EK' AND JT_VPOS_TPA_FUHRE_ORT.ID_MANDANT=#MANDANT# ) "+
									"UNION "+
									"(SELECT "+ 
									this.CreateVKFieldList()+
									" FROM "+ 
									"    "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+
									"left outer join "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) "+
									"WHERE  JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL='VK' AND JT_VPOS_TPA_FUHRE_ORT.ID_MANDANT=#MANDANT# ) ";

		for (int i=0;i<oRecList.get_vKeyValues().size();i++)
		{
			
			String cSQL = bibALL.ReplaceTeilString(c_SQL,"#MANDANT#",oRecList.get(i).get_ID_MANDANT_cUF());
			
			if (bibDB.ExecSQL(cSQL, true))
			{
				MyE2_String cInfo = new MyE2_String("Fuhren-Gewichts-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> erfolgreich erstellt !",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Fuhren-Gewichts-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> KONNTE NICHT ERSTELLT WERDEN !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
		}
		
		
		return false;
	}

	
	
	private String CreateBaseFieldList()
	{
		StringBuffer  SQLListBase = new StringBuffer();
		
		for (int i=0;i<cDefArray.length;i++)
		{
			if (S.isEmpty(cDefArray[i][0]))
			{
				SQLListBase.append("NULL AS "+cDefArray[i][1]+", ");
			}
			else
			{
				SQLListBase.append("JT_VPOS_TPA_FUHRE."+cDefArray[i][0]+" AS "+cDefArray[i][0] + ", ");
			}
		}
		
		SQLListBase.append(	"   JT_VPOS_TPA_FUHRE.DELETED, "+ 
							"   JT_VPOS_TPA_FUHRE.DEL_GRUND, "+ 
							"   JT_VPOS_TPA_FUHRE.DEL_DATE "); 

		
		return SQLListBase.toString();
	}
	
	private String CreateEKFieldList()
	{
		StringBuffer  SQLListBase = new StringBuffer();
		
		for (int i=0;i<cDefArray.length;i++)
		{
			if (S.isEmpty(cDefArray[i][0]))
			{
				SQLListBase.append("JT_VPOS_TPA_FUHRE_ORT."+cDefArray[i][1]+" AS "+cDefArray[i][1]+", ");
			}
			else
			{
				if (S.isEmpty(cDefArray[i][1]))
				{
					SQLListBase.append("JT_VPOS_TPA_FUHRE."+cDefArray[i][0]+" AS "+cDefArray[i][0] + ", ");
				}
				else
				{
					if (cDefArray[i][1].startsWith("@"))
					{
						SQLListBase.append(cDefArray[i][1].substring(1)+" AS "+cDefArray[i][0]+", ");
					}
					else
					{
						SQLListBase.append("JT_VPOS_TPA_FUHRE_ORT."+cDefArray[i][1]+" AS "+cDefArray[i][0]+", ");
					}
				}
			}
		}
		
		SQLListBase.append(	
						"    CASE WHEN NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y' THEN JT_VPOS_TPA_FUHRE.DELETED 	ELSE JT_VPOS_TPA_FUHRE_ORT.DELETED END 		AS DELETED, "+ 
						"    CASE WHEN NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y' THEN JT_VPOS_TPA_FUHRE.DEL_GRUND 	ELSE JT_VPOS_TPA_FUHRE_ORT.DEL_GRUND END 	AS DEL_GRUND, "+ 
						"    CASE WHEN NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y' THEN JT_VPOS_TPA_FUHRE.DEL_DATE 	ELSE JT_VPOS_TPA_FUHRE_ORT.DEL_DATE END 	AS DEL_DATE "); 

		
		return SQLListBase.toString();
	}
	
	

	
	private String CreateVKFieldList()
	{
		StringBuffer  SQLListBase = new StringBuffer();
		
		for (int i=0;i<cDefArray.length;i++)
		{
			if (S.isEmpty(cDefArray[i][0]))
			{
				SQLListBase.append("JT_VPOS_TPA_FUHRE_ORT."+cDefArray[i][1]+" AS "+cDefArray[i][1]+", ");
			}
			else
			{
				if (S.isEmpty(cDefArray[i][2]))
				{
					SQLListBase.append("JT_VPOS_TPA_FUHRE."+cDefArray[i][0]+" AS "+cDefArray[i][0] + ", ");
				}
				else
				{
					if (cDefArray[i][2].startsWith("@"))
					{
						SQLListBase.append(cDefArray[i][2].substring(1)+" AS "+cDefArray[i][0]+", ");
					}
					else
					{
						SQLListBase.append("JT_VPOS_TPA_FUHRE_ORT."+cDefArray[i][2]+" AS "+cDefArray[i][0]+", ");
					}

					
				}
			}
		}
		
		SQLListBase.append(	
						"    CASE WHEN NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y' THEN JT_VPOS_TPA_FUHRE.DELETED 	ELSE JT_VPOS_TPA_FUHRE_ORT.DELETED END 		AS DELETED, "+ 
						"    CASE WHEN NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y' THEN JT_VPOS_TPA_FUHRE.DEL_GRUND 	ELSE JT_VPOS_TPA_FUHRE_ORT.DEL_GRUND END 	AS DEL_GRUND, "+ 
						"    CASE WHEN NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y' THEN JT_VPOS_TPA_FUHRE.DEL_DATE 	ELSE JT_VPOS_TPA_FUHRE_ORT.DEL_DATE END 	AS DEL_DATE "); 

		
		return SQLListBase.toString();
	}
	

	
	
	
	
}
