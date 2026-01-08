package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP_ext4ReadOnly;
import panter.gmbh.indep.exceptions.myException;

public class LA_Auswertung_ALLE_ATOME  extends XX_ClassSammler4AuswerteZentrale
{

//	private E2_ComponentMAP  oMAP = null;
	
	
	public LA_Auswertung_ALLE_ATOME() throws myException
	{
	}

//	public void init() throws myException
//	{
//		this.oMAP = new ComponentMAP();
//	}
	
	
	public class ComponentMAP extends E2_ComponentMAP
	{
		private HashMapSimpleListen  hmSpaltenDef = new HashMapSimpleListen();

		
		public ComponentMAP() throws myException
		{
			super(new LA_SQL_FIELDMAP());
			
			this.add_Component(LA_CONST.NAME_OF_CHECKBOX_IN_LIST,   new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(LA_CONST.NAME_OF_LISTMARKER_IN_LIST, new E2_ButtonListMarker(),new MyE2_String("?"));

			//checkboxen
			this.hmSpaltenDef.ADD("ABRECHENBAR",		"ABRECHENBAR",		20,"Kann abgerechnet werden");
			this.hmSpaltenDef.ADD("ABGERECHNET",		"ABGERECHNET",		20,"Wurde bereits abgerechnet");
			this.hmSpaltenDef.ADD("KONTRAKTZWANG",		"KONTRAKTZWANG",	20,"Es muss einen Kontrakt zur Position geben");

			//zuerst die spalten von jt_bewegungsatom
			this.hmSpaltenDef.ADD("ID_BEWEGUNGSATOM", 	"ID-Bewegungsatom",	20);
			this.hmSpaltenDef.ADD("ID_VPOS_STD",		"ID-RG-Pos",	20);
			this.hmSpaltenDef.ADD("KONTRAKTZWANG_AUS_VON", 		"K aus",20,"Kontraktzwang ausgeschaltet von (Benutzerkürzel)");
			this.hmSpaltenDef.ADD("KONTRAKTZWANG_AUS_GRUND", 	"K aus wegen",20,"Kontraktzwang wurde aus folgendem Grund ausgeschaltet");
			this.hmSpaltenDef.ADD("KONTRAKTZWANG_AUS_AM", 		"K aus am",20,"Kontraktzwang wurde ausgeschaltet am");
			this.hmSpaltenDef.ADD("LEISTUNGSDATUM", 			"Leistungsdatum",20,"Relevantes Ausführungsdatum");
			this.hmSpaltenDef.ADD("POSTENNUMMER",				"Postennummer",20,"Postennummer des Handelspartners");
			this.hmSpaltenDef.ADD("STEUERSATZ",					"Steuer",20,"Steuersatz");
			this.hmSpaltenDef.ADD("EU_STEUER_VERMERK", 			"Steuervermerk",20);
			this.hmSpaltenDef.ADD("BEMERKUNG", 					"Bemerkung",20);
			this.hmSpaltenDef.ADD("BEMERKUNG_SACHBEARBEITER", 	"Bemerkung für Sachbearbeiter",20);
			this.hmSpaltenDef.ADD("BESTELLNUMMER",				"BESTELLNUMMER",20);
			this.hmSpaltenDef.ADD("BUCHUNGSNUMMER", 			"BUCHUNGSNUMMER",20);
			this.hmSpaltenDef.ADD("NOTIFIKATION_NR", 			"NOTIFIKATION_NR",20);
			this.hmSpaltenDef.ADD("PRUEFUNG_MENGE", 			"PRUEFUNG_MENGE",20);
			this.hmSpaltenDef.ADD("PRUEFUNG_MENGE_AM", 			"PRUEFUNG_MENGE_AM",20);
			this.hmSpaltenDef.ADD("PRUEFUNG_MENGE_VON", 		"PRUEFUNG_MENGE_VON",20);
			this.hmSpaltenDef.ADD("L_WIEGEKARTENKENNER", 		"L_WIEGEKARTENKENNER",20);
			this.hmSpaltenDef.ADD("L_NAME1", 					"L_NAME1",20);
			this.hmSpaltenDef.ADD("L_NAME2", 					"L_NAME2",20);
			this.hmSpaltenDef.ADD("L_NAME3", 					"L_NAME3",20);
			this.hmSpaltenDef.ADD("L_STRASSE", 					"L_STRASSE",20);
			this.hmSpaltenDef.ADD("L_HAUSNUMMER",				"L_HAUSNUMMER",20);
			this.hmSpaltenDef.ADD("L_LAENDERCODE",				"L_LAENDERCODE",20);
			this.hmSpaltenDef.ADD("L_PLZ", 						"L_PLZ",20);
			this.hmSpaltenDef.ADD("L_ORT", 						"L_ORT",20);
			this.hmSpaltenDef.ADD("L_ORTZUSATZ",				"L_ORTZUSATZ",20);
			this.hmSpaltenDef.ADD("L_OEFFNUNGSZEITEN", 			"L_OEFFNUNGSZEITEN",20);
			this.hmSpaltenDef.ADD("L_BESTELLNUMMER", 			"L_BESTELLNUMMER",20);
			this.hmSpaltenDef.ADD("L_TELEFON", 					"L_TELEFON",20);
			this.hmSpaltenDef.ADD("L_FAX", 						"L_FAX",20);
			this.hmSpaltenDef.ADD("A_WIEGEKARTENKENNER", 		"A_WIEGEKARTENKENNER",20);
			this.hmSpaltenDef.ADD("A_NAME1", 					"A_NAME1",20);
			this.hmSpaltenDef.ADD("A_NAME2", 					"A_NAME2",20);
			this.hmSpaltenDef.ADD("A_NAME3", 					"A_NAME3",20);
			this.hmSpaltenDef.ADD("A_STRASSE", 					"A_STRASSE",20);
			this.hmSpaltenDef.ADD("A_HAUSNUMMER",				"A_HAUSNUMMER",20);
			this.hmSpaltenDef.ADD("A_LAENDERCODE",				"A_LAENDERCODE",20);
			this.hmSpaltenDef.ADD("A_PLZ", 						"A_PLZ",20);
			this.hmSpaltenDef.ADD("A_ORT", 						"A_ORT",20);
			this.hmSpaltenDef.ADD("A_ORTZUSATZ",				"A_ORTZUSATZ",20);
			this.hmSpaltenDef.ADD("A_OEFFNUNGSZEITEN", 			"A_OEFFNUNGSZEITEN",20);
			this.hmSpaltenDef.ADD("A_BESTELLNUMMER", 			"A_BESTELLNUMMER",20);
			this.hmSpaltenDef.ADD("A_TELEFON", 					"A_TELEFON",20);
			this.hmSpaltenDef.ADD("A_FAX", 						"A_FAX",20);
			this.hmSpaltenDef.ADD("ARTBEZ1", 					"ARTBEZ1",20);
			this.hmSpaltenDef.ADD("ARTBEZ2", 					"ARTBEZ2",20);
			this.hmSpaltenDef.ADD("DELETED", 					"DELETED",20);
			this.hmSpaltenDef.ADD("DEL_DATE", 					"DEL_DATE",20);
			this.hmSpaltenDef.ADD("DEL_GRUND", 					"DEL_GRUND",20);
			this.hmSpaltenDef.ADD("DEL_KUERZEL",				"DEL_KUERZEL",20);
			this.hmSpaltenDef.ADD("LIEFERINFO_TPA", 			"LIEFERINFO_TPA",20);
			this.hmSpaltenDef.ADD("PREISERMITTLUNG", 			"PREISERMITTLUNG",20);
			this.hmSpaltenDef.ADD("NATIONALER_ABFALL_CODE", 	"NATIONALER_ABFALL_CODE",20);
			this.hmSpaltenDef.ADD("ORDNUNGSNUMMER_CMR", 		"ORDNUNGSNUMMER_CMR",20);
			this.hmSpaltenDef.ADD("ZEITSTEMPEL",				"ZEITSTEMPEL",20);
			this.hmSpaltenDef.ADD("SETZKASTEN_KOMPLETT", 		"SETZKASTEN_KOMPLETT",20);
			this.hmSpaltenDef.ADD("STORNIERT", 					"STORNIERT",20);
			this.hmSpaltenDef.ADD("STORNIERT_AM",				"STORNIERT_AM",20);
			this.hmSpaltenDef.ADD("STORNIERT_VON",				"STORNIERT_VON",20);
			this.hmSpaltenDef.ADD("STORNIERT_GRUND", 			"STORNIERT_GRUND",20);
			this.hmSpaltenDef.ADD("ID_ATOM_PARENT", 			"ID_ATOM_PARENT",20);
			this.hmSpaltenDef.ADD("VORZEICHEN_LAGER", 			"VORZEICHEN_LAGER",20);
			this.hmSpaltenDef.ADD("POSNR", 						"POSNR",20);
			this.hmSpaltenDef.ADD("ID_TYP_LAGER_START", 		"ID_TYP_LAGER_START",20);
			this.hmSpaltenDef.ADD("ATTRIB_LAGER_START", 		"ATTRIB_LAGER_START",20);
			this.hmSpaltenDef.ADD("ID_TYP_LAGER_ZIEL", 			"ID_TYP_LAGER_ZIEL",20);
			this.hmSpaltenDef.ADD("ATTRIB_LAGER_ZIEL", 			"ATTRIB_LAGER_ZIEL",20);
			this.hmSpaltenDef.ADD("ERZEUGT_VON",				"ERZEUGT_VON",20);
			this.hmSpaltenDef.ADD("ERZEUGT_AM",					"ERZEUGT_AM",20);
			this.hmSpaltenDef.ADD("ID_ARTIKEL",					"ID_ARTIKEL",20);
			this.hmSpaltenDef.ADD("ID_BEWEGUNG",				"ID_BEWEGUNG",20);
			
			//felder der Tabelle JT_BEWEGUNGSVECTOR
		//	this.hmSpaltenDef.ADD("V_ID_BEWEGUNGSVEKTOR",		"ID_BEWEGUNGSVEKTOR",20);
		//	this.hmSpaltenDef.ADD("V_ID_MANDANT",				"ID_MANDANT",20);
		//	this.hmSpaltenDef.ADD("V_LETZTE_AENDERUNG", 		"LETZTE_AENDERUNG",20);
		//	this.hmSpaltenDef.ADD("V_GEAENDERT_VON", 			"GEAENDERT_VON",20);
			this.hmSpaltenDef.ADD("V_ID_BEWEGUNG",				"ID_BEWEGUNG",20);
			this.hmSpaltenDef.ADD("V_STATUS", 					"STATUS",20);
			this.hmSpaltenDef.ADD("V_STORNIERT_AM", 			"STORNIERT_AM",20);
			this.hmSpaltenDef.ADD("V_STORNIERT_VON", 			"STORNIERT_VON",20);
			this.hmSpaltenDef.ADD("V_STORNIERT_GRUND", 			"STORNIERT_GRUND",20);
			this.hmSpaltenDef.ADD("V_ABGESCHLOSSEN_VON", 		"ABGESCHLOSSEN_VON",20);
			this.hmSpaltenDef.ADD("V_ABGESCHLOSSEN_AM", 		"ABGESCHLOSSEN_AM",20);
			this.hmSpaltenDef.ADD("V_VARIANTE","VARIANTE",20);
			this.hmSpaltenDef.ADD("V_ID_ADRESSE_START_LOGISTIK","ID_ADRESSE_START_LOGISTIK",20);
			this.hmSpaltenDef.ADD("V_L_DATUM_VON",				"L_DATUM_VON",20);
			this.hmSpaltenDef.ADD("V_L_DATUM_BIS",				"L_DATUM_BIS",20);
			this.hmSpaltenDef.ADD("V_A_DATUM_VON",				"A_DATUM_VON",20);
			this.hmSpaltenDef.ADD("V_A_DATUM_BIS",				"A_DATUM_BIS",20);
			this.hmSpaltenDef.ADD("V_LAENDERCODE_TRANSIT1", 	"LAENDERCODE_TRANSIT1",20);
			this.hmSpaltenDef.ADD("V_LAENDERCODE_TRANSIT2", 	"LAENDERCODE_TRANSIT2",20);
			this.hmSpaltenDef.ADD("V_LAENDERCODE_TRANSIT3", 	"LAENDERCODE_TRANSIT3",20);
			this.hmSpaltenDef.ADD("V_WARENKLASSE",			 	"WARENKLASSE",20);
			this.hmSpaltenDef.ADD("V_ID_ADRESSE_FREMDWARE", 	"ID_ADRESSE_FREMDWARE",20);
			this.hmSpaltenDef.ADD("V_ID_UMA_KONTRAKT", 			"ID_UMA_KONTRAKT",20);
			this.hmSpaltenDef.ADD("V_TRANSPORTMITTEL", 			"TRANSPORTMITTEL",20);
			this.hmSpaltenDef.ADD("V_TRANSPORTKENNZEICHEN", 	"TRANSPORTKENNZEICHEN",20);
			this.hmSpaltenDef.ADD("V_ANHAENGERKENNZEICHEN",	 	"ANHAENGERKENNZEICHEN",20);
			this.hmSpaltenDef.ADD("V_ID_EAK_CODE",	 			"ID_EAK_CODE",20);
			this.hmSpaltenDef.ADD("V_PRINT_EU_AMTSBLATT", 		"PRINT_EU_AMTSBLATT",20);
			this.hmSpaltenDef.ADD("V_EU_BLATT_MENGE", 			"EU_BLATT_MENGE",20);
			this.hmSpaltenDef.ADD("V_EU_BLATT_VOLUMEN", 		"EU_BLATT_VOLUMEN",20);
			this.hmSpaltenDef.ADD("V_AVV_AUSSTELLUNG_DATUM", 	"AVV_AUSSTELLUNG_DATUM",20);
			this.hmSpaltenDef.ADD("V_BEMERKUNG",				"BEMERKUNG",20);
			this.hmSpaltenDef.ADD("V_BEMERKUNG_SACHBEARBEITER", "BEMERKUNG_SACHBEARBEITER",20);
			this.hmSpaltenDef.ADD("V_DELETED", 					"DELETED",20);
			this.hmSpaltenDef.ADD("V_DEL_DATE",					"DEL_DATE",20);
			this.hmSpaltenDef.ADD("V_DEL_GRUND",				"DEL_GRUND",20);
			this.hmSpaltenDef.ADD("V_DEL_KUERZEL",				"DEL_KUERZEL",20);
			this.hmSpaltenDef.ADD("V_EK_VK_ZUORD_ZWANG", 		"EK_VK_ZUORD_ZWANG",20);
			this.hmSpaltenDef.ADD("V_ID_ADRESSE_SPEDITION", 	"ID_ADRESSE_SPEDITION",20);
			this.hmSpaltenDef.ADD("V_ZEITSTEMPEL",				"ZEITSTEMPEL",20);
			//this.hmSpaltenDef.ADD("V_ID_VEKTOR_PARENT", 		"ID_VEKTOR_PARENT",20);
			this.hmSpaltenDef.ADD("V_POSNR", 					"POSNR",20);
//			this.hmSpaltenDef.ADD("V_ERZEUGT_VON",				"ERZEUGT_VON",20);
//			this.hmSpaltenDef.ADD("V_ERZEUGT_AM",				"ERZEUGT_AM",20);
//			this.hmSpaltenDef.ADD("V_ID_VEKTOR_GRUPPE", 		"ID_VEKTOR_GRUPPE",20);
			
			//felder der Tabelle JT_BEWEGUNG
		//	this.hmSpaltenDef.ADD("B_ID_BEWEGUNG","ID_BEWEGUNG",20);
		//	this.hmSpaltenDef.ADD("B_ID_MANDANT","ID_MANDANT",20);
		//	this.hmSpaltenDef.ADD("B_LETZTE_AENDERUNG", 		"LETZTE_AENDERUNG",20);
		//	this.hmSpaltenDef.ADD("B_GEAENDERT_VON", 			"GEAENDERT_VON",20);
			this.hmSpaltenDef.ADD("B_ID_VPOS_TPA_NG", 			"ID_VPOS_TPA_NG",20);
			this.hmSpaltenDef.ADD("B_ID_VPOS_TPA_FUHRE", 		"ID_VPOS_TPA_FUHRE",20);
			this.hmSpaltenDef.ADD("B_FAHRPLAN_SATZ", 			"FAHRPLAN_SATZ",20);
			this.hmSpaltenDef.ADD("B_BEMERKUNG","BEMERKUNG",20);
			this.hmSpaltenDef.ADD("B_BEMERKUNG_SACHBEARBEITER", "BEMERKUNG_SACHBEARBEITER",20);
			this.hmSpaltenDef.ADD("B_ANRUFDATUM_FP", 			"ANRUFDATUM_FP",20);
			this.hmSpaltenDef.ADD("B_ANRUFER_FP","ANRUFER_FP",20);
			this.hmSpaltenDef.ADD("B_ANZAHL_CONTAINER_FP", 		"ANZAHL_CONTAINER_FP",20);
			this.hmSpaltenDef.ADD("B_BEMERKUNG_START_FP",		"BEMERKUNG_START_FP",20);
			this.hmSpaltenDef.ADD("B_BEMERKUNG_ZIEL_FP", 		"BEMERKUNG_ZIEL_FP",20);
			this.hmSpaltenDef.ADD("B_DAT_FAHRPLAN_FP", 			"DAT_FAHRPLAN_FP",20);
			this.hmSpaltenDef.ADD("B_DAT_VORGEMERKT_ENDE_FP", 	"DAT_VORGEMERKT_ENDE_FP",20);
			this.hmSpaltenDef.ADD("B_DAT_VORGEMERKT_FP", 		"DAT_VORGEMERKT_FP",20);
			this.hmSpaltenDef.ADD("B_EAN_CODE_FP","EAN_CODE_FP",20);
			this.hmSpaltenDef.ADD("B_ERFASSER_FP","ERFASSER_FP",20);
			this.hmSpaltenDef.ADD("B_FAHRER_FP","FAHRER_FP",20);
			this.hmSpaltenDef.ADD("B_FAHRPLANGRUPPE_FP", 		"FAHRPLANGRUPPE_FP",20);
			this.hmSpaltenDef.ADD("B_FAHRT_ANFANG_FP", 			"FAHRT_ANFANG_FP",20);
			this.hmSpaltenDef.ADD("B_FAHRT_ENDE_FP", 			"FAHRT_ENDE_FP",20);
			this.hmSpaltenDef.ADD("B_ID_CONTAINERTYP_FP", 		"ID_CONTAINERTYP_FP",20);
			this.hmSpaltenDef.ADD("B_ID_MASCHINEN_ANH_FP", 		"ID_MASCHINEN_ANH_FP",20);
			this.hmSpaltenDef.ADD("B_ID_MASCHINEN_LKW_FP", 		"ID_MASCHINEN_LKW_FP",20);
			this.hmSpaltenDef.ADD("B_IST_GEPLANT_FP", 			"IST_GEPLANT_FP",20);
			this.hmSpaltenDef.ADD("B_SORTIERUNG_FP", 			"SORTIERUNG_FP",20);
			this.hmSpaltenDef.ADD("B_TAETIGKEIT_FP", 			"TAETIGKEIT_FP",20);
			this.hmSpaltenDef.ADD("B_LADE_DATUM_VON", 			"LADE_DATUM_VON",20);
			this.hmSpaltenDef.ADD("B_LADE_DATUM_BIS", 			"LADE_DATUM_BIS",20);
			this.hmSpaltenDef.ADD("B_ABLADE_DATUM_VON", 		"ABLADE_DATUM_VON",20);
			this.hmSpaltenDef.ADD("B_ABLADE_DATUM_BIS", 		"ABLADE_DATUM_BIS",20);
			this.hmSpaltenDef.ADD("B_DELETED", 					"DELETED",20);
			this.hmSpaltenDef.ADD("B_DEL_DATE","DEL_DATE",20);
			this.hmSpaltenDef.ADD("B_DEL_GRUND","DEL_GRUND",20);
			this.hmSpaltenDef.ADD("B_DEL_KUERZEL","DEL_KUERZEL",20);
			this.hmSpaltenDef.ADD("B_ZEITSTEMPEL","ZEITSTEMPEL",20);
//			this.hmSpaltenDef.ADD("B_ERZEUGT_VON","ERZEUGT_VON",20);
//			this.hmSpaltenDef.ADD("B_ERZEUGT_AM","ERZEUGT_AM",20);
			
			
			//jetzt die Spaltendefs durchscannen, welche felder als checkboxen zu sehen sind
			SQLFieldMAP  		oSQLFieldMAP = 	this.get_oSQLFieldMAP();
			Vector<String>  	vAuflistung = 	this.hmSpaltenDef.get_vSorter();
			
			for (String cFeld: vAuflistung)
			{
				SimpleListenSpalte oSpalte = hmSpaltenDef.get(cFeld);
				oSpalte.init(oSQLFieldMAP);
				this.add_Component(oSpalte.get_Component(), oSpalte.get_oTitleString(),true,true );
			}

		}

	}
	
	
	
	
	public class LA_SQL_FIELDMAP extends SQLFieldMAP_ext4ReadOnly
	{

		public LA_SQL_FIELDMAP() throws myException
		{
			super(LA_Auswertung_ALLE_ATOME.this.get__cBaseSQLQuery(),"ATOM");
			
			this.addCompleteBase_Table_FIELDLIST(":ID_BEWEGUNGSATOM:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false);
			this.add_SQLField(new SQLFieldForPrimaryKey("ATOM","ID_BEWEGUNGSATOM","ID_BEWEGUNGSATOM",new MyE2_String("ID-ATOM"),bibE2.get_CurrSession(),
					"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ATOM.NEXTVAL FROM DUAL",true), false);

			this.add_JOIN_Table("JT_BEWEGUNGSVEKTOR", "JT_BEWEGUNGSVEKTOR", SQLFieldMAP.LEFT_OUTER, 
								":ID_BEWEGUNGSVEKTOR:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
								"ATOM.ID_BEWEGUNGSVEKTOR=JT_BEWEGUNGSVEKTOR.ID_BEWEGUNGSVEKTOR", "V_", null);

			this.add_JOIN_Table("JT_BEWEGUNG", "JT_BEWEGUNG", SQLFieldMAP.LEFT_OUTER, 
								":ID_BEWEGUNG:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
								"JT_BEWEGUNG.ID_BEWEGUNG=JT_BEWEGUNGSVEKTOR.ID_BEWEGUNG", "B_", null);

			this.initFields();
		}

	}
	
	
	
	 



	@Override
	public E2_ComponentMAP build__oComponentMAP() throws myException
	{
		return new ComponentMAP();
	}





	@Override
	public E2_ListSelectorContainer get__oListSelectorContainer() throws myException
	{
		return null;
	}





	@Override
	public MyE2_Grid get__oListBedienPanelWithButtons() throws myException
	{
		return null;
	}





	@Override
	public E2_DataSearch get__oListSearch() throws myException
	{
		return null;
	}





	@Override
	public String get__cMODUL_KENNER_ADDON()
	{
		return LA_CONST.MODUL_KENNER_ADD_ON_BASISLISTE;
	}





	@Override
	public String get__cBaseSQLQuery()
	{
		return "SELECT * FROM JT_BEWEGUNGSATOM";
	}


	
	
		
	
	
	
}
