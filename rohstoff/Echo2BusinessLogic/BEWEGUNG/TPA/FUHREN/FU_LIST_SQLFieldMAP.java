package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAPPrep;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_CONST_ABZUG;


public class FU_LIST_SQLFieldMAP extends SQLFieldMAPPrep
{



	
	public FU_LIST_SQLFieldMAP(String BASIC_MODULE_NAME) throws myException
	{
		super("JT_VPOS_TPA_FUHRE", bibE2.get_CurrSession());
		
		HttpSession oSES = bibE2.get_CurrSession();
		
//		this.addCompleteTable_FIELDLIST("JT_VPOS_TPA_FUHRE",
//										":ID_ARTIKEL:ID_VPOS_TPA_FUHRE:ID_VPOS_TPA:LETZTE_AENDERUNG:GEAENDERT_VON:" +
//										"ID_MANDANT:ARTBEZ2_EK:ARTBEZ1_VK:ARTBEZ2_VK:EUNOTIZ:BASEL_NOTIZ:" +
//										"",false,true, "");

		//aenderung 2010-11-25: Artbez1EK und artbez1_vk  werden in der liste angezeigt
		this.addCompleteTable_FIELDLIST("JT_VPOS_TPA_FUHRE",
				":ID_ARTIKEL:ID_VPOS_TPA_FUHRE:ID_VPOS_TPA:LETZTE_AENDERUNG:GEAENDERT_VON:" +
				"ID_MANDANT:ARTBEZ2_EK:ARTBEZ2_VK:EUNOTIZ:BASEL_NOTIZ:" +
				"",false,true, "");
		
		/*
		 * dann die primary-keys aller beteiligten tabellen
		 */
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",new MyString("ID-Fuhre"),oSES,
																"SELECT "+bibE2.cTO()+".SEQ_VPOS_TPA_FUHRE.NEXTVAL FROM DUAL",true), false);


		
		this.add_JOIN_Table("JT_ARTIKEL", 
								"JT_ARTIKEL", 
								SQLFieldMAP.LEFT_OUTER, 
								":AKTIV:ZOLLTARIFNR:ZOLLTARIFNOTIZ:MENGENDIVISOR:ID_EAK_CODE:" +
								"ID_EINHEIT_PREIS:EUNOTIZ:EUCODE:BASEL_NOTIZ:BASEL_CODE:" +
							"ARTBEZ2:LETZTE_AENDERUNG:GEAENDERT_VON:ID_MANDANT:ERZEUGT_VON:ERZEUGT_AM:", false, "JT_VPOS_TPA_FUHRE.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL", "", 
							bibALL.get_HashMap("ART_INFO", "SUBSTR(NVL(JT_ARTIKEL.ARTBEZ1||'                              ','                              '),1,30)||' -- '||NVL(JT_ARTIKEL.ANR1,'')"));

		this.add_JOIN_Table("JT_VPOS_TPA", 
								"JT_VPOS_TPA", 
								SQLFieldMAP.LEFT_OUTER, 
								":ID_VPOS_TPA:", true, "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA=JT_VPOS_TPA.ID_VPOS_TPA", "", null);
		
		
		this.add_JOIN_Table("JT_VKOPF_TPA", 
								"JT_VKOPF_TPA", 
								SQLFieldMAP.LEFT_OUTER, 
								":NAME1:NAME2:STRASSE:ORT:BUCHUNGSNUMMER:TELEFON_AUF_FORMULAR:ID_VKOPF_TPA", true, "JT_VPOS_TPA.ID_VKOPF_TPA=JT_VKOPF_TPA.ID_VKOPF_TPA", "", null);
		
		
		this.add_JOIN_Table("JT_FBAM", 
								"JT_FBAM", 
								SQLFieldMAP.LEFT_OUTER, 
								":ABGESCHLOSSEN_BEHEBUNG:ABGESCHLOSSEN_KONTROLLE:ID_FBAM:", true, "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE=JT_FBAM.ID_VPOS_TPA_FUHRE", "", null);

		
		this.add_JOIN_Table("JT_MASCHINEN", 
								"JT_MASCHINEN_LKW", 
								SQLFieldMAP.LEFT_OUTER, 
								":KFZKENNZEICHEN:", true, "JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP=JT_MASCHINEN_LKW.ID_MASCHINEN", "LKW_", null);

		this.add_JOIN_Table("JT_MASCHINEN", 
								"JT_MASCHINEN_ANH", 
								SQLFieldMAP.LEFT_OUTER, 
								":KFZKENNZEICHEN:", true, "JT_VPOS_TPA_FUHRE.ID_MASCHINEN_ANH_FP=JT_MASCHINEN_ANH.ID_MASCHINEN", "ANH_", null);

		this.add_JOIN_Table("JT_CONTAINERTYP", 
								"JT_CONTAINERTYP", 
								SQLFieldMAP.LEFT_OUTER, 
								":KURZBEZEICHNUNG:", true, "JT_VPOS_TPA_FUHRE.ID_CONTAINERTYP_FP=JT_CONTAINERTYP.ID_CONTAINERTYP", "CONTAINER_", null);
		

		
		this.add_SQLField(new SQLField("NVL(TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_VORGEMERKT_FP,'dd.mm.'),'?')||'-'||NVL(  TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_VORGEMERKT_ENDE_FP,'dd.mm.'),    NVL(TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_VORGEMERKT_FP,'dd.mm.'),'?'))",
								"FP_VORMERK_VON_BIS",new MyE2_String("Vormerkung von bis"),oSES), false);
		
		
//		this.add_SQLField(new SQLField(" NVL(JT_FBAM.ABGESCHLOSSEN_BEHEBUNG,'-')|| NVL(JT_FBAM.ABGESCHLOSSEN_KONTROLLE,'-')",
//										FU_LIST_ModulContainer.NAME_OF_INFOFIELD,new MyE2_String("Info"),oSES), false);
//
		
		
		this.add_SQLField(new SQLField(" NVL(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA,0)",
										FU_LIST_ModulContainer.NAME_OF_ICON_TPA_FIELD,new MyE2_String("Tpa"),oSES), false);

		
		//NEU_09  -- fuer die mengen der JT_VPOS_TPA um stornos zu finden und zu markieren
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA_FUHRE.MENGE_VORGABE_KO,-1)",	"AA__MENGE_VORGABE_KO",		new MyE2_String("Vorgabe"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA_FUHRE.MENGE_AUFLADEN_KO,-1)",	"AA__MENGE_AUFLADEN_KO",	new MyE2_String("Lademenge"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA_FUHRE.MENGE_ABLADEN_KO,-1)",	"AA__MENGE_ABLADEN_KO",		new MyE2_String("Ablademenge"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA.ANZAHL,-1)",					"AA__ANZAHL",				new MyE2_String("Anzahl"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA.EINZELPREIS,-1)",				"AA__EINZELPREIS",			new MyE2_String("EPreis"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA.GESAMTPREIS,-1)",				"AA__GESAMTPREIS",			new MyE2_String("GPreis"),oSES),false);
		//NEU_09

		
		//Neu: preis-info-felder
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(EINZELPREIS_EK,'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"BB__EINZELPREIS_EK", new MyE2_String("EPreis(EK)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(EINZELPREIS_VK,'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"BB__EINZELPREIS_VK", new MyE2_String("EPreis(VK)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(STEUERSATZ_EK,'fm999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"BB__STEUERSATZ_EK", new MyE2_String("Steuer(EK)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(STEUERSATZ_VK,'fm999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"BB__STEUERSATZ_VK", new MyE2_String("Steuer(VK)"),oSES),false);
		this.add_SQLField(new SQLField("  CASE WHEN NVL(EU_STEUER_VERMERK_EK,'-')='-' THEN '(--)' ELSE 'OK' END  ",	"BB__STEUERVERMERK_EK", new MyE2_String("Vermerk(EK)"),oSES),false);
		this.add_SQLField(new SQLField("  CASE WHEN NVL(EU_STEUER_VERMERK_VK,'-')='-' THEN '(--)' ELSE 'OK' END ",	"BB__STEUERVERMERK_VK", new MyE2_String("Vermerk(VK)"),oSES),false);
		this.add_SQLField(new SQLField("  CASE WHEN NVL(PRUEFUNG_LADEMENGE,'N')='N' THEN '(--)' ELSE 'OK' END ",	"BB__MENGENPRUEFUNG_EK", new MyE2_String("Prüfung(EK)"),oSES),false);
		this.add_SQLField(new SQLField("  CASE WHEN NVL(PRUEFUNG_ABLADEMENGE,'N')='N' THEN '(--)' ELSE 'OK' END ",	"BB__MENGENPRUEFUNG_VK", new MyE2_String("Prüfung(VK)"),oSES),false);
		

		//2010-11-25: Mengenfelder ohne nachkomma in der liste
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANTEIL_PLANMENGE_LIEF,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ANTEIL_PLANMENGE_LIEF", new MyE2_String("Anteil Planmenge (Lieferant)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANTEIL_LADEMENGE_LIEF,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ANTEIL_LADEMENGE_LIEF", new MyE2_String("Anteil Lademenge (Lieferant)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANTEIL_ABLADEMENGE_LIEF,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')","C_ANTEIL_ABLADEMENGE_LIEF", new MyE2_String("Anteil Ablademenge (Lieferant)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANTEIL_PLANMENGE_ABN,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ANTEIL_PLANMENGE_ABN", new MyE2_String("Anteil Planmenge (Abnehmer)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANTEIL_LADEMENGE_ABN,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ANTEIL_LADEMENGE_ABN", new MyE2_String("Anteil Lademenge (Abnehmer)"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANTEIL_ABLADEMENGE_ABN,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ANTEIL_ABLADEMENGE_ABN", new MyE2_String("Anteil Ablademenge (Abnehmer)"),oSES),false);
		
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ABZUG_LADEMENGE_LIEF,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ABZUG_LADEMENGE_LIEF", new MyE2_String("Abzug Lademenge"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ABZUG_ABLADEMENGE_ABN,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'(--)')",	"C_ABZUG_ABLADEMENGE_ABN", new MyE2_String("Abzug Ablademenge"),oSES),false);
		
		//2010-11-25: Summe der Absoluten Preisabzuege
		String cSubqueryAbzugEK = "SELECT SUM(ABZUG) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_EK WHERE ABZUGTYP='"+BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT+"' AND  JT_VPOS_TPA_FUHRE_ABZUG_EK.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE";
		String cSubqueryAbzugVK = "SELECT SUM(ABZUG) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_VK WHERE ABZUGTYP='"+BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT+"' AND  JT_VPOS_TPA_FUHRE_ABZUG_VK.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE";
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(("+cSubqueryAbzugEK+"),'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'0,00')",	"C_ABZUG_BETRAG_EK", new MyE2_String("Abzug Pauschal EK"),oSES),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(("+cSubqueryAbzugVK+"),'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'0,00')",	"C_ABZUG_BETRAG_VK", new MyE2_String("Abzug Pauschal VK"),oSES),false);
		
		
		
		/*
		 * subquery, die prueft, wieviele fahrten in einer fahrplangruppe vorhanden sind.
		*/
		this.add_SQLField(new SQLField("  (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE B WHERE B.FAHRPLANGRUPPE_FP=JT_VPOS_TPA_FUHRE.FAHRPLANGRUPPE_FP)","AA_ANZAHL_IN_GRUPPE",		new MyE2_String("Anzahl in Fahrplangruppe"),oSES),false);

		/*
		 * subquery, die prueft, wieviele zusatzorte ungeloescht der fuhre zugeordnet sind (fuer anzeige in der liste)
		*/
		this.add_SQLField(new SQLField("  (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT B WHERE NVL(B.DELETED,'N')='N' AND B.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)","AA_ANZAHL_ZUSATZORTE",	new MyE2_String("Anzahl Zusatzorte"),oSES),false);
		
		
		
		/*
		 * 2011-02-22: storno-infos
		 */
		this.add_SQLField(new SQLField(" (NVL(JT_VPOS_TPA_FUHRE.STORNO_GRUND,'')|| CASE WHEN JT_VPOS_TPA_FUHRE.STORNO_GRUND IS NOT NULL THEN '('||NVL(JT_VPOS_TPA_FUHRE.STORNO_KUERZEL,'')||')' ELSE  CSCONVERT('','NCHAR_CS') END)  ","AA_STORNO_INFO",	new MyE2_String("Storno-Info"),oSES),false);
		
		
		//2011-03-01: weitere spalten mit adress-ids fuer die info-buttons
		this.add_SQLField(new SQLField(" JT_VPOS_TPA_FUHRE.ID_ADRESSE_START ",									"INFO_ID_ADRESSE_START",		new MyE2_String("Startadresse"),oSES),false);
		this.add_SQLField(new SQLField(" JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL ", 									"INFO_ID_ADRESSE_ZIEL",			new MyE2_String("Zieladresse"),oSES),false);
		this.add_SQLField(new SQLField(" NVL(JT_VKOPF_TPA.ID_ADRESSE,JT_VPOS_TPA_FUHRE.ID_ADRESSE_SPEDITION) ", "INFO_ID_ADRESSE_SPEDITION",	new MyE2_String("Speditionsadresse"),oSES),false);
		

		//2011-06-01: fuhrenid ein weiteres mal als Text, damit das info-Feld der Kosten angezeigt werden kann
		this.add_SQLField(new SQLField(" TO_CHAR(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)","C__ID_VPOS_TPA_FUHRE",new MyE2_String("FuhrenId-Text"),oSES), false);

		
		//2012-08-31: neues feld mit zweiter fuhren-id, weil die 2-mal eingeblendet wird
		this.add_SQLField(new SQLField(" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE_2",new MyE2_String("ID-Fuhre"),oSES), false);
		
		
		//hier die definitionen aus der enum
		for (FU___CONST.addonFields field: FU___CONST.addonFields.values()) {
			this.add_SQLField(new SQLField(field), false);
		}
		
		
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		
		
		//2011-09-06: neues zusammengesetztes feld um anr1_ek und anr1_vk anzeigen zu koennen
		this.add_SQLField(new SQLField(" NVL(JT_VPOS_TPA_FUHRE.ANR1_EK,'<anr1>')||'-'||NVL(JT_VPOS_TPA_FUHRE.ANR2_EK,'<anr2>')||' - '||NVL(JT_VPOS_TPA_FUHRE.ARTBEZ1_EK,'<artbez1>')","ANR1_ANR2_ARTBEZ1_EK",new MyE2_String("Sorte-EK"),oSES), false);
		this.add_SQLField(new SQLField(" NVL(JT_VPOS_TPA_FUHRE.ANR1_VK,'<anr1>')||'-'||NVL(JT_VPOS_TPA_FUHRE.ANR2_VK,'<anr2>')||' - '||NVL(JT_VPOS_TPA_FUHRE.ARTBEZ1_VK,'<artbez1>')","ANR1_ANR2_ARTBEZ1_VK",new MyE2_String("Sorte-EK"),oSES), false);
		
		
		//2013-09-06: neues Feld, das prueft, wieviele Proformaeintraege an eine fuhre angehaengt sind
		this.add_SQLField(new SQLField("  (SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.PROFORMA_RECHNUNG+" PFR WHERE PFR.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)",FU___CONST.HASH_LIST_FIELD_ANZAHL_PROFROMARECHNUNG,		new MyE2_String("Anzahl Proformarechnungen"),oSES),false);

		
		//2015-06-03: weiteres pseudo-id-feld
		this.add_SQLField(new SQLField(" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE",FU___CONST.FIELDNAME_ID_VPOS_TPA_FUHRE_3,new MyE2_String("ID-Fuhre"),oSES), false);

		
		
		if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN) || BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL))
		{
			this.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.FUHRE_AUS_FAHRPLAN,'N')='Y'");
			this.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N'");
		}
		if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN))
		{
			this.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'");
		}
		

		
		
		
		
		// sortierung festlegen
		if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL))
		{
			// standard: die neuesten zuerst
			this.add_ORDER_SEGMENT("JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE DESC");
		}
		else if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN))
		{
			//sortierfeld
			this.add_ORDER_SEGMENT("JT_VPOS_TPA_FUHRE.SORTIERUNG_FP");
			this.add_ORDER_SEGMENT("JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE DESC");  //als zweite sortierung
		}
		else
		{
			// standard: die neuesten zuerst
			this.add_ORDER_SEGMENT("JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE DESC");
		}

		

		
		
		this.initFields();
		// ------------------------------- Ende sql-field-def
		
		
		
	}

	
	
}
