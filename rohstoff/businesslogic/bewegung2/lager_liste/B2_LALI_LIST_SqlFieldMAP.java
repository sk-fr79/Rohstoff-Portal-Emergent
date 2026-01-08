package rohstoff.businesslogic.bewegung2.lager_liste;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * Lagerliste auf der Auswerte-Tabelle der BG_ATOM-Struktur 
 * @author manfred
 * @date   16.01.2020
 * @throws myException
 */
public class B2_LALI_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public B2_LALI_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_BG_AUSWERT", "", false);

		// LEFT OUTER JOIN -> Auswertetabelle- Gegenseite 
		this.add_JOIN_Table("JT_BG_AUSWERT", 
						"AU2", 
						SQLFieldMAP.LEFT_OUTER, 
						"::",
						true, 
						"JT_BG_AUSWERT.ID_BG_VEKTOR = AU2.ID_BG_VEKTOR AND JT_BG_AUSWERT.ID_BG_ATOM != AU2.ID_BG_ATOM AND "	+ 
								"(" 	+ 
								"  (JT_BG_AUSWERT.STATION_KZ = -1 * AU2.STATION_KZ AND JT_BG_AUSWERT.ID_BG_STATION != AU2.ID_BG_STATION) OR " +   // gegenseite normales Lager
								"  (JT_BG_AUSWERT.STATION_KZ = 0 AND JT_BG_AUSWERT.ID_BG_STATION != AU2.ID_BG_STATION)" +						// gegenseite Streckenlager
								")", 
						"", 
						null);
		
		
		//
		//  ATOM
		//
		this.add_JOIN_Table("JT_BG_ATOM", 
				"JT_BG_ATOM", 
				SQLFieldMAP.INNER, 
				":BEMERKUNG_INTERN:BEMERKUNG_EXTERN:E_PREIS_BASISWAEHRUNG:E_PREIS_RES_NETTO_MGE_BASIS:", 
				true, 
				"JT_BG_ATOM.ID_BG_ATOM=JT_BG_AUSWERT.ID_BG_ATOM", 
				"", 
				null);

		//
		//  VEKTOR
		//
		this.add_JOIN_Table("JT_BG_VEKTOR", 
				"JT_BG_VEKTOR", 
				SQLFieldMAP.INNER, 
				":BEMERKUNG_SACHBEARBEITER:", 
				true, 
				"JT_BG_VEKTOR.ID_BG_VEKTOR=JT_BG_AUSWERT.ID_BG_VEKTOR", 
				"", 
				null);
		
		
		this.add_JOIN_Table("JT_BG_VEKTOR_KONVERT", 
				"JT_BG_VEKTOR_KONVERT", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_VPOS_TPA_FUHRE:ID_VPOS_TPA_FUHRE_ORT:ID_LAGER_KONTO:", 
				true, 
				"JT_BG_VEKTOR.ID_BG_VEKTOR=JT_BG_VEKTOR_KONVERT.ID_BG_VEKTOR", 
				"K_", 
				null);		

		//
		//  STATION 
		//
		this.add_JOIN_Table("JT_BG_STATION", 
				"S1", 
				SQLFieldMAP.INNER, 
				":", 
				true, 
				" S1.ID_BG_STATION = JT_BG_AUSWERT.ID_BG_STATION ", 
				"", 
				null);
		
		
		//
		//  STATION gegenseite
		//
		this.add_JOIN_Table("JT_BG_STATION", 
				"S2", 
				SQLFieldMAP.INNER, 
				":", 
				true, 
				" S2.ID_BG_STATION = AU2.ID_BG_STATION ", 
				"", 
				null);
		
		//
		//  ARTIKEL
		//
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.INNER, 
				":ANR1:", 
				true, 
				"JT_BG_AUSWERT.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL", 
				"", 
				null);

		//
		//  EINHEIT
		//
		this.add_JOIN_Table("JT_EINHEIT", 
				"JT_EINHEIT", 
				SQLFieldMAP.INNER, 
				":EINHEITKURZ:", 
				true, 
				"JT_EINHEIT.ID_EINHEIT=JT_ARTIKEL.ID_EINHEIT", 
				"", 
				null);
		
		
		//
		//  ARTIKELBEZEICHNUNG
		//
		this.add_JOIN_Table("JT_ARTIKEL_BEZ", 
				"JT_ARTIKEL_BEZ", 
				SQLFieldMAP.INNER, 
				":ANR2:ARTBEZ2:", 
				true, 
				"JT_BG_AUSWERT.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ", 
				"", 
				null);
		
		this.add_SQLField(new SQLField("NVL(JT_ARTIKEL.ANR1,'') || '-' ||NVL(JT_ARTIKEL_BEZ.ANR2,'') || ' - ' ||NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'')","ART_INFO", new MyE2_String("Artikelinfo"), bibE2.get_CurrSession()), true);
		
		
		// 
		//  LAGERADRESSE
		//
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_LAGER", 
				SQLFieldMAP.INNER, 
				":"	, 
				true, 
				"JT_BG_AUSWERT.ID_ADRESSE=ADR_LAGER.ID_ADRESSE", 
				"", 
			null);

		// 
		//  LAGERADRESSE
		//
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"LA", 
				SQLFieldMAP.LEFT_OUTER, 
				":"	, 
				true, 
				"JT_BG_AUSWERT.ID_ADRESSE=LA.ID_ADRESSE_LIEFER", 
				"", 
			null);
		
		// 
		//  LAGERADRESSE BASIS
		//
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_LAGER_BASIS", 
				SQLFieldMAP.INNER, 
				":"	, 
				true, 
				"ADR_LAGER_BASIS.ID_ADRESSE = JT_BG_AUSWERT.ID_ADRESSE_BASIS", 
				"", 
				null
			);

//			bibALL.get_HashMap("LAGER_INFO", " NVL2(JT_ADRESSE.ORT,JT_ADRESSE.ORT,'') ||  NVL2(JT_ADRESSE.PLZ,' (' || JT_ADRESSE.PLZ || '), ','') || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'')  || NVL2(ADR_LIEFER.LA_BESCHREIBUNG, ' - ' || ADR_LIEFER.LA_BESCHREIBUNG,'')")


		// 
		//  KUNDENADRESSE
		//
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_KUNDE", 
				SQLFieldMAP.INNER, 
				":ID_ADRESSE:"	, 
				true, 
				"ADR_KUNDE.ID_ADRESSE = AU2.ID_ADRESSE", 
				"ADR_KUNDE_", 
				null);
		
		// 
		//  KUNDENADRESSE BASIS
		//
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_KUNDE_BASIS", 
				SQLFieldMAP.INNER, 
				":"	, 
				true, 
				"ADR_KUNDE_BASIS.ID_ADRESSE = AU2.ID_ADRESSE_BASIS", 
				"", 
				null
			);
//			bibALL.get_HashMap("LAGER_INFO", " NVL2(JT_ADRESSE.ORT,JT_ADRESSE.ORT,'') ||  NVL2(JT_ADRESSE.PLZ,' (' || JT_ADRESSE.PLZ || '), ','') || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'')  || NVL2(ADR_LIEFER.LA_BESCHREIBUNG, ' - ' || ADR_LIEFER.LA_BESCHREIBUNG,'')")

		

		//
		// EIGENWARE=Y / FREMDWARE=N
		//
		String sSqlFieldEWFW = 
				  " CASE WHEN JT_BG_AUSWERT.ID_ADRESSE_BESITZ_LDG = " + bibALL.get_ID_ADRESS_MANDANT() + " "
				+ " 				THEN 'Y' "
				+ "                 ELSE 'N' " 
				+ " END ";
		
		this.add_SQLField(
				new SQLField(sSqlFieldEWFW,
				"EW_FW", 
				new MyE2_String("EW_FW"), 
				bibE2.get_CurrSession()), 
				true);

		
		//
		// Abrechenbar, wenn Besitzerwechsel und eigene ID betroffen
		//
		String sSqlFieldAbrechenbar = 
				  "  CASE WHEN (JT_BG_AUSWERT.ID_ADRESSE_BESITZ_LDG = " + bibALL.get_ID_ADRESS_MANDANT() + " OR S2.ID_ADRESSE_BESITZ_LDG = " + bibALL.get_ID_ADRESS_MANDANT() + ") "
				+ " 									AND JT_BG_AUSWERT.ID_ADRESSE_BESITZ_LDG != S2.ID_ADRESSE_BESITZ_LDG " 
				+ "				THEN 'Y' "
				+ "             ELSE 'N' " 
				+ " END ";

		this.add_SQLField(
				new SQLField(sSqlFieldAbrechenbar,
				"ABRECHENBAR_BESITZ", 
				new MyE2_String("ABRECHENBAR_BESITZ"), 
				bibE2.get_CurrSession()), 
				true);

		
		//
		// LAGER INFO
		//
		this.add_SQLField(new SQLField("NVL2(ADR_LAGER.ORT,ADR_LAGER.ORT,'') ||  NVL2(ADR_LAGER.PLZ,' (' || ADR_LAGER.PLZ || '), ','') || NVL(ADR_LAGER.NAME1,'') ||' ' || NVL(ADR_LAGER.NAME2,'') "
				,"LAGER_INFO"
				, new MyE2_String("ID Kunde")
				, bibE2.get_CurrSession())
				, true);
		
		
		
//		// INNER JOIN auf LIEFERADRESSE zum ermitteln und einschränken des Lagerorts des Mandanten
//		String sJoinTable = "(" + 
//			" SELECT LA.ID_ADRESSE_LIEFER   LA_ID_ADRESSE_LIEFER" +
//			", LA.BESCHREIBUNG as LA_BESCHREIBUNG" +
//			" FROM " + bibE2.cTO() + ".JT_LIEFERADRESSE LA" +
//			" INNER JOIN " + bibE2.cTO() + ".JT_ADRESSE ADRL ON LA.ID_ADRESSE_LIEFER = ADRL.ID_ADRESSE" +
//			" " +
//			"      WHERE LA.ID_ADRESSE_BASIS = " +
//			" 		     ( SELECT M.EIGENE_ADRESS_ID FROM  " + bibE2.cTO() + ".JD_MANDANT M WHERE M.ID_MANDANT = " + bibALL.get_ID_MANDANT() + " )" +
//			"        AND ( ADRL.SONDERLAGER is null OR trim(ADRL.SONDERLAGER) = trim('STRECKE') )" +
//			" UNION " +
//			"    SELECT M.EIGENE_ADRESS_ID, null FROM  " + bibE2.cTO() + ".JD_MANDANT M" +
//			"    WHERE M.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + 
//			" ) " ;
//		
//		
//		this.add_JOIN_Table(sJoinTable, 
//				"ADR_LIEFER", 
//				SQLFieldMAP.INNER, 
//				":LA_ID_ADRESSE_LIEFER:LA_BESCHREIBUNG:", 
//				true, 
//				"JT_BEWEGUNG_STATION.ID_ADRESSE=LA_ID_ADRESSE_LIEFER", 
//				"",
//				null);
		
		this.add_SQLField(new SQLField("ADR_KUNDE.NAME1 || NVL2(ADR_KUNDE.NAME2,' ' || ADR_KUNDE.NAME2,'') || ', ' ||  NVL(ADR_KUNDE.ORT,'') || ' (' || TO_CHAR(ADR_KUNDE.ID_ADRESSE) || ')'"
				,"NAME_GEGEN"
				, new MyE2_String("Kundenlager")
				, bibE2.get_CurrSession())
				, true);
		
		this.add_SQLField(new SQLField("ADR_KUNDE_BASIS.NAME1 || NVL2(ADR_KUNDE_BASIS.NAME2,' ' || ADR_KUNDE_BASIS.NAME2,'') || ', ' ||  NVL(ADR_KUNDE_BASIS.ORT,'') || ' (' || TO_CHAR(ADR_KUNDE_BASIS.ID_ADRESSE) || ')'"
				,"NAME_BASIS_GEGEN"
				, new MyE2_String("Kunde")
				, bibE2.get_CurrSession())
				, true);
		
		this.add_SQLField(new SQLField("ADR_KUNDE_BASIS.ID_ADRESSE"
				,"LA_ID_ADRESSE_LIEFER"
				, new MyE2_String("ID Kunde")
				, bibE2.get_CurrSession())
				, true);


//		this.add_SQLField(new SQLField("CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN = 1 then ADR_GEGEN_VPOS.NAME_GEGEN_LADEN ELSE ADR_GEGEN_VPOS.NAME_GEGEN_ABLADEN END"
//				,"NAME_GEGEN_VPOS", new MyE2_String("Kunde"), bibE2.get_CurrSession()), true);
//		this.add_SQLField(new SQLField("CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN = 1 then ADR_GEGEN_VPOS.NAME_BASIS_GEGEN_LADEN ELSE ADR_GEGEN_VPOS.NAME_BASIS_GEGEN_ABLADEN END"
//				,"NAME_BASIS_GEGEN_VPOS", new MyE2_String("Kunde"), bibE2.get_CurrSession()), true);
//
//		this.add_SQLField(new SQLField("CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN = 1 then ADR_GEGEN_VPOS.ID_ADRESSE_LADEN ELSE ADR_GEGEN_VPOS.ID_ADRESSE_ABLADEN END"
//				,"ID_ADRESSE_GEGEN_VPOS", new MyE2_String("ID Kunde"), bibE2.get_CurrSession()), true);
//
		
		
		// Netto.Menge mit VZ
		this.add_SQLField(new SQLField(" JT_BG_AUSWERT.MENGE_NETTO * JT_BG_AUSWERT.MENGENVORZEICHEN "
				,"MENGE_NETTO", new MyE2_String("Menge Netto"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField(" JT_BG_AUSWERT.MENGE * JT_BG_AUSWERT.MENGENVORZEICHEN "
				,"MENGE_BRUTTO", new MyE2_String("Menge Brutto"), bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField(" JT_BG_AUSWERT.MENGE_ABZUG  "
				,"ABZUG_MENGE", new MyE2_String("Abzug Menge"), bibE2.get_CurrSession()), true);

		
		this.add_SQLField(new SQLField("(CASE WHEN JT_BG_AUSWERT.MENGENVORZEICHEN = 1 THEN JT_BG_AUSWERT.MENGE_NETTO ELSE NULL END )"
				,"MENGE_WE", new MyE2_String("Menge WE netto"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField("(CASE WHEN JT_BG_AUSWERT.MENGENVORZEICHEN = -1 THEN JT_BG_AUSWERT.MENGE_NETTO ELSE NULL END )"
				,"MENGE_WA", new MyE2_String("Menge WA netto"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField("(CASE WHEN JT_BG_AUSWERT.MENGENVORZEICHEN = 1 THEN JT_BG_AUSWERT.MENGE ELSE NULL END )"
				,"MENGE_WE_BRUTTO", new MyE2_String("Menge WE brutto"), bibE2.get_CurrSession()), true);
				
		this.add_SQLField(new SQLField("(CASE WHEN JT_BG_AUSWERT.MENGENVORZEICHEN = -1 THEN JT_BG_AUSWERT.MENGE ELSE NULL END )"
				,"MENGE_WA_BRUTTO", new MyE2_String("Menge WA brutto"), bibE2.get_CurrSession()), true);

				
		this.add_SQLField(new SQLField("nvl(JT_BG_ATOM.BEMERKUNG_INTERN,'') || NVL(JT_BG_VEKTOR.BEMERKUNG_SACHBEARBEITER,'')"
				,"BEMERKUNG", new MyE2_String("Bemerkung"), bibE2.get_CurrSession()), true);
		

		String sWhere =    
				" JT_BG_AUSWERT.ID_BG_DEL_INFO IS NULL "
				+ " AND JT_BG_AUSWERT.ID_BG_STORNO_INFO IS NULL "
				+ " AND JT_BG_AUSWERT.ID_ARTIKEL IS NOT NULL "
				+ " AND JT_BG_AUSWERT.DATUM_AUSFUEHRUNG IS NOT NULL "
				+ " AND NVL(ADR_LAGER.SONDERLAGER,'STRECKE') = 'STRECKE' "+
				
// Einschränkung auf Eigene Läger	
		 		" AND JT_BG_AUSWERT.ID_ADRESSE IN " +
		 		"            ( " +
		 		"               SELECT V.ID_ADRESSE FROM V_FIRMENADRESSEN_FLACH V " +
		 		"                WHERE V.ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() + " AND NVL( V.SONDERLAGER,'STRECKE') = 'STRECKE' AND V.ID_MANDANT =  " + bibALL.get_ID_MANDANT() +
		 		" 	         )											"
		 		;
		
		this.add_BEDINGUNG_STATIC(sWhere);
		
		
		this.add_ORDER_SEGMENT(	" JT_BG_AUSWERT.DATUM_AUSFUEHRUNG DESC " +
				" , NVL(ADR_LAGER.ORT,'') || ' (' || NVL(ADR_LAGER.PLZ,'') || '), ' || NVL(ADR_LAGER.NAME1,'') ||' ' || NVL(ADR_LAGER.NAME2,'') " +
				" , JT_ARTIKEL.ANR1" +
				" , JT_BG_AUSWERT.MENGENVORZEICHEN DESC");


		this.initFields();
	}
	
}
