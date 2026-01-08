package rohstoff.Echo2BusinessLogic.LAGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


/**
 * Anzeige der letzten Kontostände (SALDO) 
 * @author manfred
 *
 */
public class LAG_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6634758154806405979L;

	public LAG_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_LAGER_KONTO", "", false);
		
		
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
//			bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.PLZ,'') ||' '|| NVL(JT_ADRESSE.ORT,'') ||', ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'')"));
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
//		String sSqlLagerbestand = "( " +
//				"SELECT                                                                                               " +
//				"    NVL                                                                                              " +
//				"    (                                                                                                " +
//				"        (                                                                                            " +
//				"        SELECT                                                                                       " +
//				"            sum(K1.menge)                                                                            " +
//				"        FROM                                                                                         " +
//				"            JT_LAGER_KONTO K1                                                                        " +
//				"        WHERE                                                                                        " +
//				"            K1.ID_ADRESSE_LAGER     = K.ID_ADRESSE_LAGER                                             " +
//				"            AND K1.ID_ARTIKEL_SORTE = K.ID_ARTIKEL_SORTE                                             " +
//				"            AND NVL(K1.STORNO,'N')  = 'N'                                                            " +
//				"            AND K1.BUCHUNGSDATUM    >                                                                " +
//				"            (                                                                                        " +
//				"            SELECT                                                                                   " +
//				"                nvl(max( to_date( to_char(LI.Buchungsdatum,'yyyy-mm-dd') || ' ' ||                   " +
//				"		 LI.Buchungszeit, 'yyyy-mm-dd HH24:mi') ),to_date('1900-01-01','yyyy-mm-dd'))         		" +
//				"            FROM                                                                                     " +
//				"                JT_LAGER_INVENTUR LI                                                      " +
//				"            WHERE                                                                                    " +
//				"                LI.ID_ADRESSE_LAGER     = K1.ID_ADRESSE_LAGER                                        " +
//				"                AND LI.ID_ARTIKEL_SORTE = K1.ID_ARTIKEL_SORTE                                        " +
//				"            )                                                                                        " +
//				"        )                                                                                            " +
//				"        ,0                                                                                           " +
//				"    ) as MENGE_NACH_INVENTUR                                                                         " +
//				"FROM                                                                                                 " +
//				"    JT_LAGER_KONTO K                                                                                 " +
//				"INNER JOIN                                                                                           " +
//				"    JT_ARTIKEL ART                                                                                   " +
//				"    ON                                                                                               " +
//				"    K.ID_ARTIKEL_SORTE = ART.ID_ARTIKEL                                                              " +
//				"    AND K.ID_MANDANT   = ART.ID_MANDANT                                                              " +
//				"WHERE                                                                                                " +
//				"    NVL(K.STORNO,'N') = 'N'                                                                          " +
//				"    and K.ID_ADRESSE_LAGER = JT_LAGER_KONTO.ID_ADRESSE_LAGER										  " +
//				"	 and K.ID_ARTIKEL_SORTE = JT_LAGER_KONTO.ID_ARTIKEL_SORTE										  " +
//				"GROUP BY                                                                                             " +
//				"    K.ID_ADRESSE_LAGER,                                                                              " +
//				"    K.ID_ARTIKEL_SORTE                                                                               " +
//				" )"  ;

		String sSqlLagerbestand = "( " +
		"        SELECT                                                                                       " +
		"           nvl( sum(K1.menge)  ,0)                                                                   " +
		"        FROM                                                                                         " +
		"            JT_LAGER_KONTO K1                                                                        " +
		"        WHERE                                                                                        " +
		"            K1.ID_ADRESSE_LAGER     = JT_LAGER_KONTO.ID_ADRESSE_LAGER                                   " +
		"            AND K1.ID_ARTIKEL_SORTE = JT_LAGER_KONTO.ID_ARTIKEL_SORTE                                   " +
		"            AND NVL(K1.STORNO,'N')  = 'N'                                                            " +
		"            AND K1.BUCHUNGSDATUM    >                                                                " +
		"            (                                                                                        " +
		"            SELECT                                                                                   " +
		"                nvl(max( to_date( to_char(LI.Buchungsdatum,'yyyy-mm-dd') || ' ' ||                   " +
		"		        LI.Buchungszeit, 'yyyy-mm-dd HH24:mi') ),to_date('1900-01-01','yyyy-mm-dd'))         		" +
		"            FROM                                                                                     " +
		"                JT_LAGER_INVENTUR LI                                                      " +
		"            WHERE                                                                                    " +
		"                LI.ID_ADRESSE_LAGER     = K1.ID_ADRESSE_LAGER                                        " +
		"                AND LI.ID_ARTIKEL_SORTE = K1.ID_ARTIKEL_SORTE                                        " +
		"            )                                                                                        " +
		"        )                                                                                           " +
		" "  ;
		
		//
		//  Lagerbestand ohne nullstellenberücksichtigung
		//
		sSqlLagerbestand = "( " +
		"        SELECT                                                                                       " +
		"           nvl( sum(K1.menge)  ,0)                                                                   " +
		"        FROM                                                                                         " +
		"            JT_LAGER_KONTO K1                                                                        " +
		"        WHERE                                                                                        " +
		"            K1.ID_ADRESSE_LAGER     = JT_LAGER_KONTO.ID_ADRESSE_LAGER                                   " +
		"            AND K1.ID_ARTIKEL_SORTE = JT_LAGER_KONTO.ID_ARTIKEL_SORTE                                   " +
		"            AND NVL(K1.STORNO,'N')  = 'N'                                                            " +
		"        )                                                                                           " +
		" "  ;
				
		
		this.add_SQLField(new SQLField(sSqlLagerbestand, "LAGERBESTAND", new MyE2_String("Lagerbestand"), bibE2.get_CurrSession()), true);
				
		
		String sWhere = " NVL(JT_LAGER_KONTO.STORNO,'N') = 'N' AND" +
	   " JT_LAGER_KONTO.ID_LAGER_KONTO = (" +
       " select max(k2.ID_LAGER_KONTO) "+
       " from " + bibE2.cTO() + ".JT_LAGER_KONTO k2 "+
       " where JT_LAGER_KONTO.ID_MANDANT = k2.ID_MANDANT "+
       " and JT_LAGER_KONTO.ID_ADRESSE_LAGER = k2.ID_ADRESSE_LAGER "+
       " and JT_LAGER_KONTO.ID_ARTIKEL_SORTE = k2.ID_ARTIKEL_SORTE " +
       " and NVL(JT_LAGER_KONTO.STORNO,'N') = NVL(k2.STORNO,'N')" +
       " )" ;
		
		this.add_BEDINGUNG_STATIC(sWhere);
		//this.add_ORDER_SEGMENT(" JT_LAGER_KONTO.ID_ADRESSE_LAGER, NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'') " );
		this.add_ORDER_SEGMENT("NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'')");

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
