package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


/**
 * Anzeige der letzten Kontostände (SALDO) 
 * @author manfred
 *
 */
public class ATOM_SALDO_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{


	
	
	public ATOM_SALDO_LIST_SqlFieldMAP() throws myException
	{
		super("JT_BEWEGUNG_STATION", "", false);
		
		this.add_JOIN_Table("JT_BEWEGUNG_ATOM", 
				"JT_BEWEGUNG_ATOM", 
				SQLFieldMAP.INNER, 
				":ID_ARTIKEL:", 
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM", 
				"", 
				null);
		
		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				"", true, "JT_BEWEGUNG_ATOM.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL", "", 
			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		this.add_JOIN_Table("JT_EINHEIT", 
				"JT_EINHEIT", 
				SQLFieldMAP.LEFT_OUTER, 
				":EINHEITKURZ:", 
				true, 
				"JT_ARTIKEL.ID_EINHEIT = JT_EINHEIT.ID_EINHEIT", 
				"",
				null);
			

		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				""
				, true, 
				"JT_BEWEGUNG_STATION.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE", 
				"", 
			   bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.ORT,'') || nvl2(JT_ADRESSE.PLZ,' (' || JT_ADRESSE.PLZ || '), ' ,'') || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') "));
		
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"JT_LIEFERADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":BEZEICHNUNG:", 
				true, 
				" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER ",
				"", null
				);
		
		
		
//		String sSaldo_inline = 
//			  " ( SELECT  NVL(sum  ((A1.MENGE - NVL(A1.ABZUG_MENGE,0)) * S1.MENGENVORZEICHEN),0) AS SALDO_NACH_INVENTUR " 
//			+ " FROM  " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1"
//			+ " 	INNER JOIN   " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A1 "
//			+ "         ON A1.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM AND A1.ID_MANDANT = S1.ID_MANDANT "
//			+ "         WHERE A1.LEISTUNGSDATUM >= nvl(" 
//			+ "         (  SELECT MAX( TO_DATE( TO_CHAR(LI.BUCHUNGSDATUM,'YYYY-MM-DD') || ' ' || LI.BUCHUNGSZEIT, 'YYYY-MM-DD HH24:MI') ) as LI_DATUM  "
//			+ "                                         FROM JT_LAGER_INVENTUR LI  "
//			+ "                                         where LI.ID_ADRESSE_LAGER = S1.ID_ADRESSE and LI.ID_ARTIKEL_SORTE = A1.ID_ARTIKEL ),to_date('1900-01-01','yyyy-mm-dd')) "
//			+ " AND   A1.ID_ARTIKEL = JT_BEWEGUNG_ATOM.ID_ARTIKEL  "
//			+ " AND   S1.ID_ADRESSE = JT_BEWEGUNG_STATION.ID_ADRESSE  "
//			+ " AND   NVL(A1.STORNIERT,'N') = 'N'  "
//			+ " AND   NVL(A1.DELETED,'N') = 'N'  "
//			+ " GROUP BY S1.ID_ADRESSE, A1.ID_ARTIKEL  "
//			+ "  ) "
//				;
//		this.add_SQLField(new SQLField(sSaldo_inline,"MENGE_SALDO", new MyE2_String("SALDO"), bibE2.get_CurrSession()), true);

		
		//
		// Einschraenkung auf die letzte Station, damit man pro Artikel und Lager nur einen Eintrag in der Liste bekommt
		//
		String sWhere = 
		   " JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION =  ( " +
		   " SELECT MAX(S1.ID_BEWEGUNG_STATION)  " +
		   "         FROM  " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1 " +
		   "         INNER JOIN  " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A1 ON S1.ID_BEWEGUNG_ATOM = A1.ID_BEWEGUNG_ATOM " +
		   "         WHERE S1.ID_ADRESSE = JT_BEWEGUNG_STATION.ID_ADRESSE  " +
		   "           AND A1.ID_ARTIKEL = JT_BEWEGUNG_ATOM.ID_ARTIKEL " +
		   "           AND S1.ID_ADRESSE IN ( " +
		   "                 SELECT LA.ID_ADRESSE_LIEFER " +
		   "                  FROM   " + bibE2.cTO() + ".JT_LIEFERADRESSE LA " +
		   "                  INNER JOIN   " + bibE2.cTO() + ".JT_ADRESSE ADRL ON LA.ID_ADRESSE_LIEFER = ADRL.ID_ADRESSE   AND LA.ID_MANDANT = ADRL.ID_MANDANT " +
		   "                  WHERE LA.ID_ADRESSE_BASIS =  " +
		   "                          (   " +
		   "                          SELECT M.EIGENE_ADRESS_ID FROM  " + bibE2.cTO() + ".JD_MANDANT M " +
		   "                          WHERE M.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + 
		   "                          )    " +
		   "				   AND (ADRL.SONDERLAGER is null OR ADRL.SONDERLAGER = 'STRECKE') " +
		   "                   UNION    " +
		   "                   SELECT M.EIGENE_ADRESS_ID  " +
		   "                   FROM  " + bibE2.cTO() + ".JD_MANDANT M   " +
		   "                   WHERE M.ID_MANDANT =   " + bibALL.get_ID_MANDANT() + 
		   "         ) " +
		   " ) "  ;
			
			this.add_BEDINGUNG_STATIC(sWhere);

			// initiale Sortierung
			this.add_ORDER_SEGMENT(" NVL(JT_ADRESSE.ORT,'') || nvl2(JT_ADRESSE.PLZ,' (' || JT_ADRESSE.PLZ || '), ' ,'') || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'')" +
					",JT_ARTIKEL.ANR1");
		
		this.initFields();
	}
	
	
}
