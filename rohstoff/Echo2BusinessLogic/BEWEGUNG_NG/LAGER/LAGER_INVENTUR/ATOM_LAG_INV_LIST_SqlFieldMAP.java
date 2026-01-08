package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_INVENTUR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_INV_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public ATOM_LAG_INV_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_LAGER_INVENTUR", "", false);
		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:", true, "JT_LAGER_INVENTUR.ID_ARTIKEL_SORTE=JT_ARTIKEL.ID_ARTIKEL", "", 
			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, "JT_LAGER_INVENTUR.ID_ADRESSE_LAGER=JT_ADRESSE.ID_ADRESSE", "", 
			//bibALL.get_HashMap("ADDRESS_INFO", "NVL(JT_ADRESSE.PLZ,'') ||' '|| NVL(JT_ADRESSE.ORT,'') ||', ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'')"));
			bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') "));

		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"JT_LIEFERADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":BEZEICHNUNG:", 
				true, 
				" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER ",
				"", null
				);

		
		// Lagerbestands-ermittlung SQL-Statement aus LAG_LagerSaldoErmittlung entnommen
		String sSqlLagerbestand = "( " +
				"    NVL (                                                                                            " +
				"        (																							  " +
				"		 SELECT                                                                                       " +
				"            SUM(                                                                            		  " +
				"            CASE                                                                          			  " +
				"            	WHEN S1.MENGENVORZEICHEN = -1                                                         " +
				"            	THEN (A1.MENGE ) * S1.MENGENVORZEICHEN                                                " +
				"            	ELSE (A1.MENGE - NVL(A1.ABZUG_MENGE,0)) * S1.MENGENVORZEICHEN                         " +
				"             END ) AS SALDO                                                    					  " +
				"        FROM                                                                                         " +
				"            JT_BEWEGUNG_STATION S1                                                                   " +
				"        INNER JOIN JT_BEWEGUNG_ATOM A1 ON 															  " +
				"    		A1.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM AND A1.ID_MANDANT = S1.ID_MANDANT 			  " +
				"        WHERE                                                                                        " +
				"            S1.ID_ADRESSE           = JT_LAGER_INVENTUR.ID_ADRESSE_LAGER                             " +
				"            AND A1.ID_ARTIKEL       = JT_LAGER_INVENTUR.ID_ARTIKEL_SORTE                             " +
				"            AND NVL(A1.STORNIERT,'N') = 'N'                                                           " +
				"            AND NVL(A1.DELETED,'N') = 'N'                                                            " +
//				"	    	 AND to_char(A1.LEISTUNGSDATUM,'yyyy-MM-dd') || ' 23:59'  <=  to_char(JT_LAGER_INVENTUR.BUCHUNGSDATUM,'yyyy-MM-dd') || ' 23:59' "
				"	    	 AND A1.LEISTUNGSDATUM  <=  JT_LAGER_INVENTUR.BUCHUNGSDATUM "
				+ "      ) ,0                                                                                           " +
				"    ) ) " ;
		
		this.add_SQLField(new SQLField(sSqlLagerbestand, "LAGERBESTAND", new MyE2_String("Lagerbestand"), bibE2.get_CurrSession()), true);
		
		
		
		
		
		
		this.get_("BUCHUNGSZEIT").set_cDefaultValueFormated("23:59");
		this.get_("MENGE").set_cDefaultValueFormated("0");
		this.get_("PREIS").set_cDefaultValueFormated("0");

		
		
		
		
		// ORDER BY
		this.add_ORDER_SEGMENT("BUCHUNGSDATUM DESC, BUCHUNGSZEIT DESC");
		
		
		
		this.initFields();
	}
	
}
