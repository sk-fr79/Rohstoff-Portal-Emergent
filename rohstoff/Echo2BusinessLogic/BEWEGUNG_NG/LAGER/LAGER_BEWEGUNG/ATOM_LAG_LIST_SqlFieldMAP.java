package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * Die Fieldmap der Lagerbewegungen basiert auf den Lagerstationen, da die Stationen die eingentlichen und kleinsten Einheiten
 * der atomaren Bewegungen sind. Sie spiegeln die Halbfuhren wieder.
 *  
 * @author manfred
 * @date   17.07.2013
 * @throws myException
 */
public class ATOM_LAG_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public ATOM_LAG_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_BEWEGUNG_STATION", "", false);

		// INNER JOIN -> JT_BEWEGUNG_ATOM (Einseitig, bringt immer beides Seiten des Atoms) 
		this.add_JOIN_Table("JT_BEWEGUNG_ATOM", 
				"JT_BEWEGUNG_ATOM", 
				SQLFieldMAP.INNER, 
				":ID_ARTIKEL:LEISTUNGSDATUM:MENGE:ABZUG_MENGE:E_PREIS:E_PREIS_RESULT_NETTO_MGE:ARTBEZ1:ARTBEZ2:BEMERKUNG:BEMERKUNG_SACHBEARBEITER:DELETED:ID_BEWEGUNG:ID_BEWEGUNG_VEKTOR:",
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM=JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM", 
				"", 
				null);

		// INNER JOIN -> JT_ARTIKEL (Nur Atome mit Artikeln sind relevant)
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.INNER, 
				":ANR1:", 
				true, 
				"JT_BEWEGUNG_ATOM.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL", 
				"", 
				null);
//			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		// INNER JOIN -> JT_ARTIKEL (Nur Atome mit Artikeln sind relevant)
		this.add_JOIN_Table("JT_ARTIKEL_BEZ", 
				"JT_ARTIKEL_BEZ", 
				SQLFieldMAP.INNER, 
				":ANR2:", 
				true, 
				"JT_BEWEGUNG_ATOM.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ", 
				"", 
				null);
//			bibALL.get_HashMap("ARTBEZ_INFO", "NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'') || ' / ' ||NVL(JT_ARTIKEL_BEZ.ARTBEZ2,'')"));
		
		this.add_SQLField(new SQLField("NVL(JT_ARTIKEL.ANR1,'') || '-' ||NVL(JT_ARTIKEL_BEZ.ANR2,'') || ' - ' ||NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'')","ART_INFO", new MyE2_String("Artikelinfo"), bibE2.get_CurrSession()), true);
		
		
		// LEFT OUTER JOIN -> JT_ADRESSE (Lageradresse) 
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":"	, 
				true, 
				"JT_BEWEGUNG_STATION.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE", 
				"", 
			bibALL.get_HashMap("LAGER_INFO", " NVL2(JT_ADRESSE.ORT,JT_ADRESSE.ORT,'') ||  NVL2(JT_ADRESSE.PLZ,' (' || JT_ADRESSE.PLZ || '), ','') || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'')  || NVL2(ADR_LIEFER.LA_BESCHREIBUNG, ' - ' || ADR_LIEFER.LA_BESCHREIBUNG,'')"));

		// LEFT OUTER JOIN -> JT_ADRESSE (Lageradresse) 
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"JT_LIEFERADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_ADRESSE_LIEFER:ID_ADRESSE_FREMDWARE:"	, 
				true, 
				"JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER", 
				"", 
				null);

		
		// LEFT OUTER JOIN -> JT_BEWEGUNG 
		this.add_JOIN_Table("JT_BEWEGUNG", 
				"JT_BEWEGUNG", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_VPOS_TPA_FUHRE:NAME2:PLZ:ORT:", 
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG=JT_BEWEGUNG.ID_BEWEGUNG", 
				"",
				null);

		
		// Join mit der gegenüberliegenden Station um eigen- und Fremdwaren zu unterscheiden
		this.add_JOIN_Table("JT_BEWEGUNG_STATION",
				"S1",
				SQLFieldMAP.INNER,
				"",
				true,
				"JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM AND S1.MENGENVORZEICHEN = (JT_BEWEGUNG_STATION.MENGENVORZEICHEN * -1)",
				"",
				null);
		
		
		
		/**
		 * Einstufung der Fremdwaren/Eigenwaren/Abrechenbar/Nicht Abrechenbar
		 * -------------
		 * |  2  |  1  |
		 * |-----|-----|
		 * |EW=1 |Ab=1 |
		 * |FW=0 |NA=0 |
		 * -------------
		 * EW-Abrechenbar = 11 = 3
		 * EW-Nicht Abrechenbar = 10 = 2
		 * FW-Abrechenbar = 01 = 1  (eigentlich nicht möglich)
		 * FW-Nicht Abrechenbar = 00 = 0
		 */
//		String sSqlFieldAbrechenbar = " CASE WHEN JT_BEWEGUNG_STATION.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() + " then "
//				+ "             CASE WHEN JT_BEWEGUNG_STATION.ID_ADRESSE_BESITZER = s1.ID_ADRESSE_BESITZER  " 
//				+ "                  THEN 2  " 
//				+ "                  ELSE 3  " 
//				+ "             END  " 
//				+ "     WHEN JT_BEWEGUNG_STATION.ID_ADRESSE_BESITZER !=  " + bibALL.get_ID_ADRESS_MANDANT() + " then   " 
//				+ "             CASE WHEN s1.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() + "   " 
//				+ "              	THEN 3  " 
//				+ "              	ELSE 0  "
//				+ "             END  "
//				+ "    ELSE -1  "
//				+ " END ";

		
		String sSqlFieldEWFW = 
				  " CASE WHEN JT_BEWEGUNG_STATION.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() + " "
				+ " 					OR S1.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() 
				+ " 				THEN 'Y' "
				+ "                 ELSE 'N' " 
				+ " END ";
		
		this.add_SQLField(
				new SQLField(sSqlFieldEWFW,
				"EW_FW", 
				new MyE2_String("EW_FW"), 
				bibE2.get_CurrSession()), 
				true);

		
		/**
		 * Abrechenbar anhand des Besitzuebergangs ermitteln
		 */
		String sSqlFieldAbrechenbar = 
				  " CASE WHEN (JT_BEWEGUNG_STATION.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() + " "
				+ " 					OR S1.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() + " ) AND JT_BEWEGUNG_STATION.ID_ADRESSE_BESITZER != S1.ID_ADRESSE_BESITZER "
				+ "				THEN 'Y' "
				+ "             ELSE 'N' " 
				+ " END ";
		
		this.add_SQLField(
				new SQLField(sSqlFieldAbrechenbar,
				"ABRECHENBAR_BESITZ", 
				new MyE2_String("ABRECHENBAR_BESITZ"), 
				bibE2.get_CurrSession()), 
				true);

		
		
		
		// INNER JOIN auf LIEFERADRESSE zum ermitteln und einschränken des Lagerorts des Mandanten
		String sJoinTable = "(" + 
			" SELECT LA.ID_ADRESSE_LIEFER   LA_ID_ADRESSE_LIEFER" +
			", LA.BESCHREIBUNG as LA_BESCHREIBUNG" +
			" FROM " + bibE2.cTO() + ".JT_LIEFERADRESSE LA" +
			" INNER JOIN " + bibE2.cTO() + ".JT_ADRESSE ADRL ON LA.ID_ADRESSE_LIEFER = ADRL.ID_ADRESSE" +
			" " +
			"      WHERE LA.ID_ADRESSE_BASIS = " +
			" 		     ( SELECT M.EIGENE_ADRESS_ID FROM  " + bibE2.cTO() + ".JD_MANDANT M WHERE M.ID_MANDANT = " + bibALL.get_ID_MANDANT() + " )" +
			"        AND ( ADRL.SONDERLAGER is null OR trim(ADRL.SONDERLAGER) = trim('STRECKE') )" +
			" UNION " +
			"    SELECT M.EIGENE_ADRESS_ID, null FROM  " + bibE2.cTO() + ".JD_MANDANT M" +
			"    WHERE M.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + 
			" ) " ;
		
		this.add_JOIN_Table(sJoinTable, 
				"ADR_LIEFER", 
				SQLFieldMAP.INNER, 
				":LA_ID_ADRESSE_LIEFER:LA_BESCHREIBUNG:", 
				true, 
				"JT_BEWEGUNG_STATION.ID_ADRESSE=LA_ID_ADRESSE_LIEFER", 
				"",
				null);

		
		String sJoinTableGegenseite = "" +
				"( SELECT    ADR2.ID_ADRESSE as ID_ADRESSE_GEGEN" +
				"                , A2.ID_BEWEGUNG_ATOM as ID_BEWEGUNG_ATOM_GEGEN" +
				"                , ADR2.ID_MANDANT as ID_MANDANT_GEGEN" +
				"                , NVL(S2.NAME1, ADR2.NAME1) || NVL2(S2.NAME2,' ' || S2.NAME2,'') || ', ' ||  NVL(S2.ORT,'') || ' (' || TO_CHAR(ADR2.ID_ADRESSE) || ')' as NAME_GEGEN" +
				"                , S2.MENGENVORZEICHEN as MENGENVZ_GEGEN " +
				"				 , nvl( LA.ID_ADRESSE_BASIS, ADR2.ID_ADRESSE) as ID_ADRESSE_BASIS_GEGEN" +
				"				 , nvl2(ADR_LA.ID_ADRESSE,NVL(ADR_LA.NAME1, '') || ' ' || nvl(ADR_LA.NAME2,'') || ', ' || nvl(ADR_LA.ORT,'') ,'') ||  nvl2(ADR_LA.ID_ADRESSE,' (' || TO_CHAR(ADR_LA.ID_ADRESSE) || ')','') as NAME_BASIS_GEGEN " +
				" FROM  " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A2 " +
				" INNER JOIN  " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S2 ON A2.ID_BEWEGUNG_ATOM = S2.ID_BEWEGUNG_ATOM " +
				" INNER JOIN  " + bibE2.cTO() + ".JT_ADRESSE ADR2 ON S2.ID_ADRESSE = ADR2.ID_ADRESSE" +
				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_LIEFERADRESSE LA on LA.ID_ADRESSE_LIEFER = ADR2.ID_ADRESSE " +
				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_ADRESSE ADR_LA on LA.ID_ADRESSE_BASIS = ADR_LA.ID_ADRESSE " +
				" WHERE A2.ID_MANDANT       = " +bibALL.get_ID_MANDANT() + " )  " ;
		this.add_JOIN_Table(sJoinTableGegenseite, 
				"ADR_GEGEN", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME_GEGEN:ID_BEWEGUNG_ATOM_GEGEN:ID_MANDANT_GEGEN:ID_ADRESSE_GEGEN:ID_ADRESSE_BASIS_GEGEN:NAME_BASIS_GEGEN:", 
				true, 
				" ID_BEWEGUNG_ATOM_GEGEN = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM " +
				" AND ID_MANDANT_GEGEN = JT_BEWEGUNG_ATOM.ID_MANDANT " +
				" AND MENGENVZ_GEGEN = -1 * JT_BEWEGUNG_STATION.MENGENVORZEICHEN ", 
				"",
				null);

		this.add_SQLField(new SQLField("ADR_GEGEN.NAME_GEGEN","NAME_GEGEN", new MyE2_String("Kunde"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("ADR_GEGEN.ID_ADRESSE_GEGEN","LA_ID_ADRESSE_LIEFER", new MyE2_String("ID Kunde"), bibE2.get_CurrSession()), true);

		
		
		String sJoinTableGegenseiteVecPos = "( SELECT" +
				" P.ID_MANDANT as ID_MANDANT_GEGEN_VPOS " + 
				" ,A1.ID_BEWEGUNG as ID_BEWEGUNG_GEGEN_VPOS " +
				" ,A1.ID_BEWEGUNG_VEKTOR as ID_BEWEGUNG_VEKTOR_GEGEN_VPOS " +
				" ,P.ID_BEWEGUNG_VEKTOR_POS as ID_BEWEGUNG_VEKTOR_POS_GEGEN_VPOS  " +
				
				" ,A1.ID_BEWEGUNG_ATOM as ID_BEWEGUNG_ATOM_LADEN  " +
				" ,S1.ID_ADRESSE_BESITZER AS ID_ADRESSE_BESITZER_LADEN  " +
				" ,S1.ID_ADRESSE_BASIS as ID_ADRESSE_BASIS_LADEN   " +
				" ,s1.ID_ADRESSE as ID_ADRESSE_LADEN   " +
				" ,NVL(S1.NAME1, ADR1.NAME1) || NVL2(S1.NAME2,' ' || S1.NAME2,'') || ', ' ||  NVL(S1.ORT,'') || ' (' || TO_CHAR(ADR1.ID_ADRESSE) || ')' as NAME_GEGEN_LADEN   " +
				" ,NVL( ADR1_B.NAME1,ADR1.NAME1) || NVL2(ADR1_B.NAME2,' ' || S1.NAME2,'') || ', ' ||  NVL(ADR1_B.ORT,'') || ' (' || TO_CHAR(ADR1_B.ID_ADRESSE) || ')' as NAME_BASIS_GEGEN_LADEN    " +
				
				" ,case when a2.id_bewegung_atom IS NOT NULL then A2.ID_BEWEGUNG_ATOM else A1.ID_BEWEGUNG_ATOM END as ID_BEWEGUNG_ATOM_ABLADEN  " +
				" ,case when a2.id_bewegung_atom IS NOT NULL then S2.ID_ADRESSE_BESITZER else S1_2.ID_ADRESSE_BESITZER end AS ID_ADRESSE_BESITZER_ABLADEN  " +
				" ,case when a2.id_bewegung_atom IS NOT NULL then S2.ID_ADRESSE_BASIS  else S1_2.ID_ADRESSE_BASIS end as ID_ADRESSE_BASIS_ABLADEN   " +
				" ,case when a2.id_bewegung_atom IS NOT NULL then s2.ID_ADRESSE  else s1_2.ID_ADRESSE  end as ID_ADRESSE_ABLADEN  " +
				" ,case when a2.id_bewegung_atom IS NOT NULL then NVL(S2.NAME1, ADR2.NAME1) || NVL2(S2.NAME2,' ' || S2.NAME2,'') || ', ' ||  NVL(S2.ORT,'') || ' (' || TO_CHAR(ADR2.ID_ADRESSE) || ')'   " +
				" else NVL(S1_2.NAME1, ADR1_2.NAME1) || NVL2(S1_2.NAME2,' ' || S1_2.NAME2,'') || ', ' ||  NVL(S1_2.ORT,'') || ' (' || TO_CHAR(ADR1_2.ID_ADRESSE) || ')'  end as NAME_GEGEN_ABLADEN  " +

				" ,case when a2.id_bewegung_atom IS NOT NULL then NVL(ADR2_B.NAME1, S2.NAME1) || NVL2(ADR2_B.NAME2,'' || S2.NAME2,'') || ', ' ||  NVL(ADR2_B.ORT,'') || ' (' || TO_CHAR(ADR2_B.ID_ADRESSE) || ')'   " +
				" else NVL(ADR1_B2.NAME1, S1_2.NAME1) || NVL2(ADR1_B2.NAME2,' ' || ADR1_B2.NAME2,'') || ', ' ||  NVL(ADR1_B2.ORT,'') || ' (' || TO_CHAR(ADR1_B2.ID_ADRESSE) || ')'  end as NAME_BASIS_GEGEN_ABLADEN  " +
				
				" FROM " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR_POS P   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A1 on P.ID_BEWEGUNG_VEKTOR_POS = A1.ID_BEWEGUNG_VEKTOR_POS and A1.POSNR = 1  " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1 on A1.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM and S1.MENGENVORZEICHEN = -1   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1_2 on A1.ID_BEWEGUNG_ATOM = S1_2.ID_BEWEGUNG_ATOM and S1_2.MENGENVORZEICHEN = +1   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_ADRESSE ADR1 ON S1.ID_ADRESSE = ADR1.ID_ADRESSE   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_ADRESSE ADR1_B ON S1.ID_ADRESSE_BASIS = ADR1_B.ID_ADRESSE " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_ADRESSE ADR1_B2 ON S1_2.ID_ADRESSE_BASIS = ADR1_B2.ID_ADRESSE" +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_ADRESSE ADR1_2 ON S1_2.ID_ADRESSE = ADR1_2.ID_ADRESSE   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM a2 on P.ID_BEWEGUNG_VEKTOR_POS = A2.ID_BEWEGUNG_VEKTOR_POS and A2.POSNR = 2   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S2 on A2.ID_BEWEGUNG_ATOM = S2.ID_BEWEGUNG_ATOM and S2.MENGENVORZEICHEN = +1   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_ADRESSE ADR2 ON S2.ID_ADRESSE = ADR2.ID_ADRESSE   " +
				" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_ADRESSE ADR2_B ON S2.ID_ADRESSE_BASIS = ADR2_B.ID_ADRESSE " +
				" WHERE P.ID_MANDANT       = " +bibALL.get_ID_MANDANT() + " )  " ;
				
				
		this.add_JOIN_Table(sJoinTableGegenseiteVecPos, 
				"ADR_GEGEN_VPOS", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_BEWEGUNG_GEGEN_VPOS:ID_BEWEGUNG_VEKTOR_GEGEN_VPOS:ID_BEWEGUNG_VEKTOR_POS_GEGEN_VPOS"
				+ ":ID_BEWEGUNG_ATOM_LADEN:ID_ADRESSE_BESITZER_LADEN:ID_ADRESSE_BASIS_LADEN:ID_ADRESSE_LADEN:NAME_GEGEN_LADEN:NAME_BASIS_GEGEN_LADEN"
				+ ":ID_BEWEGUNG_ATOM_ABLADEN:ID_ADRESSE_BESITZER_ABLADEN:ID_ADRESSE_BASIS_ABLADEN:ID_ADRESSE_ABLADEN:NAME_GEGEN_ABLADEN:NAME_BASIS_GEGEN_ABLADEN", 
				true, 
				" ID_BEWEGUNG_VEKTOR_POS_GEGEN_VPOS = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_VEKTOR_POS " +
				" AND ID_MANDANT_GEGEN_VPOS = JT_BEWEGUNG_ATOM.ID_MANDANT " 
				, 
				"",
				null);

		this.add_SQLField(new SQLField("CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN = 1 then ADR_GEGEN_VPOS.NAME_GEGEN_LADEN ELSE ADR_GEGEN_VPOS.NAME_GEGEN_ABLADEN END","NAME_GEGEN_VPOS", new MyE2_String("Kunde"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN = 1 then ADR_GEGEN_VPOS.NAME_BASIS_GEGEN_LADEN ELSE ADR_GEGEN_VPOS.NAME_BASIS_GEGEN_ABLADEN END","NAME_BASIS_GEGEN_VPOS", new MyE2_String("Kunde"), bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN = 1 then ADR_GEGEN_VPOS.ID_ADRESSE_LADEN ELSE ADR_GEGEN_VPOS.ID_ADRESSE_ABLADEN END","ID_ADRESSE_GEGEN_VPOS", new MyE2_String("ID Kunde"), bibE2.get_CurrSession()), true);

		
		
		// Netto.Menge mit VZ
		this.add_SQLField(new SQLField("(JT_BEWEGUNG_STATION.MENGENVORZEICHEN * (JT_BEWEGUNG_ATOM.MENGE - NVL(JT_BEWEGUNG_ATOM.ABZUG_MENGE,0)))","MENGE_NETTO", new MyE2_String("Menge Netto"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("(JT_BEWEGUNG_STATION.MENGENVORZEICHEN * JT_BEWEGUNG_ATOM.MENGE )","MENGE_BRUTTO", new MyE2_String("Menge Brutto"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField("(CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN > 0 THEN (JT_BEWEGUNG_ATOM.MENGE - NVL(JT_BEWEGUNG_ATOM.ABZUG_MENGE,0)) else null END )","MENGE_WE", new MyE2_String("Menge WE netto"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("(CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN < 0 THEN (JT_BEWEGUNG_ATOM.MENGE - NVL(JT_BEWEGUNG_ATOM.ABZUG_MENGE,0)) else null END )","MENGE_WA", new MyE2_String("Menge WA netto"), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField("(CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN > 0 THEN JT_BEWEGUNG_ATOM.MENGE  else null END )","MENGE_WE_BRUTTO", new MyE2_String("Menge WE brutto"), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("(CASE WHEN JT_BEWEGUNG_STATION.MENGENVORZEICHEN < 0 THEN JT_BEWEGUNG_ATOM.MENGE  else null END )","MENGE_WA_BRUTTO", new MyE2_String("Menge WA brutto"), bibE2.get_CurrSession()), true);

		
		this.add_SQLField(new SQLField("nvl(JT_BEWEGUNG_ATOM.BEMERKUNG,'') || NVL(JT_BEWEGUNG_ATOM.BEMERKUNG_SACHBEARBEITER,'')","BEMERKUNG", new MyE2_String("Bemerkung"), bibE2.get_CurrSession()), true);
		

		String sWhere =    " NVL(JT_BEWEGUNG_ATOM.DELETED,'N')= 'N' ";
		this.add_BEDINGUNG_STATIC(sWhere);
		
		
		this.add_ORDER_SEGMENT(	" JT_BEWEGUNG_ATOM.LEISTUNGSDATUM DESC " +
				" , NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') " +
				" , JT_ARTIKEL.ANR1" +
				" , JT_BEWEGUNG_STATION.MENGENVORZEICHEN DESC");


		this.initFields();
	}
	
}
