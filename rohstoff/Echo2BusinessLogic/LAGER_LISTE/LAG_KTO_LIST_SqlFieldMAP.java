package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


/**
 * Anzeige der letzten Kontostände (SALDO) 
 * @author manfred
 *
 */
public class LAG_KTO_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6634758154806405979L;

	public LAG_KTO_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_LAGER_KONTO", "", false);
		
		
		this.add_SQLField(new SQLField("CASE to_char(JT_LAGER_KONTO.BUCHUNGSTYP) WHEN 'WE' THEN  JT_LAGER_KONTO.MENGE ELSE  null END", "MENGE_WE", new MyE2_String("WE"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("CASE to_char(JT_LAGER_KONTO.BUCHUNGSTYP) WHEN 'WA' THEN  JT_LAGER_KONTO.MENGE * (-1) ELSE  null END", "MENGE_WA", new MyE2_String("WA"), bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("CASE to_char(JT_LAGER_KONTO.BUCHUNGSTYP) WHEN 'WE' THEN  JT_LAGER_KONTO.BUCHUNGSDATUM ELSE  null END", "DATUM_ABLADEN", new MyE2_String("Abladedatum"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("CASE to_char(JT_LAGER_KONTO.BUCHUNGSTYP) WHEN 'WA' THEN  JT_LAGER_KONTO.BUCHUNGSDATUM ELSE  null END", "DATUM_AUFLADEN", new MyE2_String("Ladedatum"), bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("JT_VPOS_TPA_FUHRE.BUCHUNGSNR_FUHRE || NVL2(JT_VPOS_TPA_FUHRE_ORT.BUCHUNGSNUMMER_ZUSATZ,'-' || JT_VPOS_TPA_FUHRE_ORT.BUCHUNGSNUMMER_ZUSATZ,'')","BUCHUNGSNUMMER_FUHRE", new MyE2_String("Buchungsnummer"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField("CASE WHEN JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE_ORT IS NULL THEN   " +
				"	 CASE to_char(JT_LAGER_KONTO.BUCHUNGSTYP) WHEN 'WE' THEN  JT_VPOS_TPA_FUHRE.ARTBEZ2_VK ELSE JT_VPOS_TPA_FUHRE.ARTBEZ2_EK END " +
				" ELSE JT_VPOS_TPA_FUHRE_ORT.ARTBEZ2 END" +
				"", "ARTIKELBEZ2", new MyE2_String("ArtBez2"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField("nvl(JT_LAGER_KONTO.BEMERKUNG,'') || NVL(JT_VPOS_TPA_FUHRE.BEMERKUNG_SACHBEARBEITER,'')","BEMERKUNG_FUHRE_LAGER", new MyE2_String("Bemerkung"), bibE2.get_CurrSession()), true);
		
		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:", true, "JT_LAGER_KONTO.ID_ARTIKEL_SORTE=JT_ARTIKEL.ID_ARTIKEL", "", 
			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, "JT_LAGER_KONTO.ID_ADRESSE_LAGER=JT_ADRESSE.ID_ADRESSE", "", 
			bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') "));
		
		
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"JT_LIEFERADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":BESCHREIBUNG:", 
				true, 
				" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER ",
				"", null
				);
		
				
		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE", 
				"JT_VPOS_TPA_FUHRE", 
				SQLFieldMAP.LEFT_OUTER, 
				":TRANSPORTKENNZEICHEN:ANHAENGERKENNZEICHEN:BEMERKUNG_SACHBEARBEITER:STATUS_BUCHUNG:OHNE_ABRECHNUNG:",
				true,
				" JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE ", "", null);


		// FUHREN - Adressen der Abnehmerseite
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_ABN", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL = ADR_ABN.ID_ADRESSE ", "", null);

		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_ABN_LAGER", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL = ADR_ABN_LAGER.ID_ADRESSE ", "", null);
		
		//FUHREN - Adressen der Lieferseite
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_LIEF", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_VPOS_TPA_FUHRE.ID_ADRESSE_START = ADR_LIEF.ID_ADRESSE ", "", null);

		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_LIEF_LAGER", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START = ADR_LIEF_LAGER.ID_ADRESSE ", "", null);
		
	
			
		// die Spedtionsinfo
		String sSQLSpedInfo = 	"   NVL2(   JT_VPOS_TPA_FUHRE.ID_ADRESSE_SPEDITION, " +
								"  ( NVL(ADR_SPED.NAME1,'') || NVL2(ADR_SPED.NAME2,' ' || ADR_SPED.NAME2 || ', ',', ') || NVL2(ADR_SPED.STRASSE,ADR_SPED.STRASSE || NVL2(ADR_SPED.HAUSNUMMER,' ' || ADR_SPED.HAUSNUMMER,'') || ', ','') || NVL2(ADR_SPED.PLZ,JT_VKOPF_TPA.PLZ|| ' ' ,'') || NVL(ADR_SPED.ORT,'') ) ," +
								"  ( NVL(JT_VKOPF_TPA.NAME1,'') || NVL2(JT_VKOPF_TPA.NAME2,' ' || JT_VKOPF_TPA.NAME2 || ', ',', ') || NVL2(JT_VKOPF_TPA.STRASSE,JT_VKOPF_TPA.STRASSE || NVL2(JT_VKOPF_TPA.HAUSNUMMER,' ' || JT_VKOPF_TPA.HAUSNUMMER,'') || ', ','') || NVL2(JT_VKOPF_TPA.PLZ,JT_VKOPF_TPA.PLZ|| ' ' ,'') || NVL(JT_VKOPF_TPA.ORT,'') ) )";
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_SPED", 
				SQLFieldMAP.LEFT_OUTER, 
				""
				, true, "JT_VPOS_TPA_FUHRE.ID_ADRESSE_SPEDITION = ADR_SPED.ID_ADRESSE", "", 
				bibALL.get_HashMap("SPEDITION_INFO", sSQLSpedInfo));

		

		this.add_JOIN_Table("JT_VPOS_TPA", "JT_VPOS_TPA", 
				SQLFieldMAP.LEFT_OUTER, 
				"", 
				true, 
				" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA = JT_VPOS_TPA.ID_VPOS_TPA ", "", null);
		
		this.add_JOIN_Table("JT_VKOPF_TPA", "JT_VKOPF_TPA", 
				SQLFieldMAP.LEFT_OUTER, 
				":BUCHUNGSNUMMER:", 
				true, 
				" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA ", "", null);
		
		
		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE_ORT", 
				"JT_VPOS_TPA_FUHRE_ORT", 
				SQLFieldMAP.LEFT_OUTER, 
				":DATUM_LADE_ABLADE:DEF_QUELLE_ZIEL:",
				true,
				" JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE_ORT = JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE_ORT " +
				" AND JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE ", "", null);

		
		// FUHRENORT - Adressen
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_ORT", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE = ADR_ORT.ID_ADRESSE ", "", null);

		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_ORT_LAGER", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE_LAGER = ADR_ORT_LAGER.ID_ADRESSE ", "", null);
	
		
		this.add_SQLField(new SQLField("(case to_char(JT_LAGER_KONTO.BUCHUNGSTYP) when 'WE' then     NVL(JT_VPOS_TPA_FUHRE.L_NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE.L_NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE.L_PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE.L_ORT,'') " +
				" else   NVL(JT_VPOS_TPA_FUHRE.A_NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE.A_NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE.A_PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE.A_ORT,'')  end )" ,
				"LIEFERANT_INFO",
				new MyString("Kunde"),
				bibE2.get_CurrSession()), true);
		
		
		this.add_SQLField(new SQLField("(case to_char(JT_LAGER_KONTO.BUCHUNGSTYP) when 'WE' then     NVL(JT_VPOS_TPA_FUHRE.ID_ADRESSE_START,-1) " +
				" else   NVL(JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL,-1)   end )" ,
				"LIEFERANT_ID_ADRESSE",
				new MyString("ID Kunde"),
				bibE2.get_CurrSession()), true);
	
		this.add_SQLField(new SQLField("(case to_char(JT_LAGER_KONTO.BUCHUNGSTYP) when 'WE' then     NVL(JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START,-1) " +
				" else   NVL(JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL,-1)   end )" ,
				"LIEFERANT_ID_ADRESSE_LAGER",
				new MyString("ID Lager"),
				bibE2.get_CurrSession()), true);
		

		// die Kundenadresse, der Basisadresse, falls die Lieferadresse ein Lieferort war
		String sSQLAdrFuhre = 	"(CASE to_char(JT_LAGER_KONTO.BUCHUNGSTYP) when 'WE' THEN " +
								"    NVL(ADR_LIEF.NAME1,'') || ' '|| NVL(ADR_LIEF.NAME2,'')|| ', '|| NVL(ADR_LIEF.PLZ,'') || ' ' || NVL(ADR_LIEF.ORT,'') " +
								" ELSE   " +
								"	NVL(ADR_ABN.NAME1,'') || ' '|| NVL(ADR_ABN.NAME2,'')|| ', '|| NVL(ADR_ABN.PLZ,'') || ' ' || NVL(ADR_ABN.ORT,'')  " +
								" END )" ;
//		String sSQLAdrFuhreOrt = "    NVL(ADR_ORT.NAME1,'') || ' '|| NVL(ADR_ORT.NAME2,'')|| ', '|| NVL(ADR_ORT.PLZ,'') || ' ' || NVL(ADR_ORT.ORT,'') " ;
//		
//		String sSQLBasisadresse = 	"CASE WHEN ID_VPOS_TPA_FUHRE_ORT IS NULL THEN   " + sSQLAdrFuhre
//									 + " ELSE " + sSQLAdrFuhreOrt
//									 + " END";
		
		this.add_SQLField(new SQLField(sSQLAdrFuhre ,
				"LIEFERANT_BASISADRESSE",
				new MyString("Lieferantenadresse"),
				bibE2.get_CurrSession()), true);
		
		
//		this.add_SQLField(new SQLField("(case to_char(BUCHUNGSTYP) when 'WE' THEN " +
//		"	NVL2(JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_ABN,JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_ABN,'') || NVL2(WIEGEKARTENKENNER_ABLADEN,' (' || WIEGEKARTENKENNER_ABLADEN || ')','') " +
//		" 	ELSE " +
//		"  	NVL2(JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_LIEF,JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_LIEF,'') || NVL2(WIEGEKARTENKENNER_LADEN,' (' || WIEGEKARTENKENNER_LADEN || ')','')" +
//		"   END )" ,
//		"WIEGEKARTE",
//		new MyString("Wiegekarte"),
//		bibE2.get_CurrSession()), true);
			
		this.add_SQLField(new SQLField(
				" (case WHEN JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE_ORT is not null THEN " + 
				" NVL2(JT_VPOS_TPA_FUHRE_ORT.ID_WIEGEKARTE,JT_VPOS_TPA_FUHRE_ORT.ID_WIEGEKARTE,'') || NVL2(JT_VPOS_TPA_FUHRE_ORT.WIEGEKARTENKENNER,' (' || JT_VPOS_TPA_FUHRE_ORT.WIEGEKARTENKENNER || ')','') " +
				" ELSE " + 
				"(case to_char(JT_LAGER_KONTO.BUCHUNGSTYP) when 'WE' THEN " +
				"	NVL2(JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_ABN,JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_ABN,'') || NVL2(JT_VPOS_TPA_FUHRE.WIEGEKARTENKENNER_ABLADEN,' (' || JT_VPOS_TPA_FUHRE.WIEGEKARTENKENNER_ABLADEN || ')','') " +
				" 	ELSE " +
				"  	NVL2(JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_LIEF,JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_LIEF,'') || NVL2(JT_VPOS_TPA_FUHRE.WIEGEKARTENKENNER_LADEN,' (' || JT_VPOS_TPA_FUHRE.WIEGEKARTENKENNER_LADEN || ')','')" +
				"   END ) " +
				" END )" ,
				"WIEGEKARTE",
				new MyString("Wiegekarte"),
				bibE2.get_CurrSession()), true);
		

		// POSTENNUMMER
		this.add_SQLField(new SQLField(
				" (case WHEN JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE_ORT is not null THEN " + 
				" NVL(JT_VPOS_TPA_FUHRE_ORT.POSTENNUMMER,'') " +
				" ELSE " + 
				"(case to_char(JT_LAGER_KONTO.BUCHUNGSTYP) when 'WE' THEN " +
				"	NVL(JT_VPOS_TPA_FUHRE.POSTENNUMMER_VK,'')  " +
				" 	ELSE " +
				"  	NVL(JT_VPOS_TPA_FUHRE.POSTENNUMMER_EK,'') " +
				"   END ) " +
				" END )" ,
				"POSTENNUMMER",
				new MyString("Postennummer"),
				bibE2.get_CurrSession()), true);
		
		
		//2011-05-20: Martin
		//dummyfield fuer die anzeigekomponente der kosten
		this.add_SQLField(new SQLField("TO_CHAR(JT_LAGER_KONTO.ID_LAGER_KONTO)" ,"C__LAGER_KONTO",new MyString("Lagerkonto-Text"),bibE2.get_CurrSession()), true);
		
		
		
		
//		this.add_SQLField(new SQLField(sSQLSpedInfo ,
//				"SPEDITION_INFO",
//				new MyString("Spedition"),
//				bibE2.get_CurrSession()), true);
		


//		String sWhere = " nvl(JT_LAGER_KONTO.STORNO,'N') = 'N' ";
//		this.add_BEDINGUNG_STATIC(sWhere);
		
		//this.add_ORDER_SEGMENT("JT_LAGER_KONTO.ID_LAGER_KONTO DESC" );
//		this.add_ORDER_SEGMENT("NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'')");
		this.add_ORDER_SEGMENT(	" JT_LAGER_KONTO.BUCHUNGSDATUM desc" +
								" , NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') " +
								" , JT_ARTIKEL.ANR1" +
								" , BUCHUNGSTYP desc");
		
		
		
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_LAGER_KONTO_WICHTIGKEIT","BESCHREIBUNG","ID_LAG_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_LAG_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_LAGER_KONTO.ID_USER="+cID_USER+" OR JT_LAGER_KONTO.ID_LAG IN (SELECT ID_LAG FROM "+bibE2.cTO()+".JT_LAGER_KONTO_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		
		this.initFields();
	}
	
}
