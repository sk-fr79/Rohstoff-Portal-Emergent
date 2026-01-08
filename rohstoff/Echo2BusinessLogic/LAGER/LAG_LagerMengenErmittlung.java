package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class LAG_LagerMengenErmittlung {

	
	private String m_VFuhren = null;
	private String m_IdMandant = null;
	private String m_TableOwner = null;
	
	
	

	private String [][] cLagerDaten = null;
	
	
	public LAG_LagerMengenErmittlung() {
		
		m_IdMandant = bibALL.get_ID_MANDANT();
		m_VFuhren =  bibALL.get_TABLEOWNER() + ".V" + m_IdMandant + "_REALGEWICHTE";
		m_TableOwner = bibALL.get_TABLEOWNER();
	}

 
	

	/**
	 * Ermittelt für alle Läger und alle Sorten in einem Statement alle Restmengen
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager, idSorte) gelesen werden 
	 * @param IDLager
	 * @param IDSorte
	 * @throws myException
	 */
	public void ReadVertragsLagerDaten(String sIDLager, String sIDHauptsorte, String sIDSorte ) throws myException{

				
		String sSqlWhere = "";
		
		if (sIDLager != null && sIDLager.trim().length() > 0) {
			sSqlWhere += " AND L.ID_ADRESSE_LAGER = " + sIDLager;
		}
		if (sIDSorte != null && sIDSorte.trim().length() > 0) {
			sSqlWhere += " AND K.ID_ARTIKEL = " + sIDSorte ;
			
		} else if (sIDHauptsorte != null && sIDHauptsorte.trim().length() > 0){
			sSqlWhere += " AND  K.ID_ARTIKEL in (SELECT A.ID_ARTIKEL FROM " +   m_TableOwner + ".JT_ARTIKEL A where SUBSTR(A.ANR1,0,2) = '" + sIDHauptsorte + "' ) "   ;
		}
		
		
		
		String sSqlEK = 
			"SELECT 'EK', L.ID_ADRESSE_LAGER, K.ID_ARTIKEL, SUM(L.LAGERMENGE), " + 
			" SUM (L.LAGERMENGE) - " + 
		    " NVL(" +
		    "	 (SELECT SUM( V1.ANTEIL_LADEMENGE_LIEF ) as SUM_ABLADEMENGE " +
		    "	 FROM  " + m_VFuhren + " V1 " + 
		    "	 LEFT OUTER JOIN " + m_TableOwner + ".JT_VPOS_KON K1  ON   ( K1.ID_VPOS_KON = V1.ID_VPOS_KON_EK    ) " +
		    "	 INNER JOIN " + m_TableOwner + ".JT_VPOS_KON_TRAKT TRAKT1  ON ( K1.ID_VPOS_KON = TRAKT1.ID_VPOS_KON ) " +
		    "	 INNER JOIN  " + m_TableOwner + ".JT_VPOS_KON_LAGER L1  ON ( L1.ID_VPOS_KON = K1.ID_VPOS_KON )  " +
		    "	 WHERE  nvl(V1.DELETED,'N') = 'N' " +
		    "	 AND nvl(V1.IST_STORNIERT,'N') = 'N' " +
			"	 AND NVL(TRAKT1.ABGESCHLOSSEN,'N') = 'N' " +
		    "	 AND nvl(V1.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " +
		    "	 AND (V1.ID_ADRESSE_LAGER_ZIEL = L1.ID_ADRESSE_LAGER) " +
		    "	 AND V1.ID_ADRESSE_LAGER_ZIEL = L.ID_ADRESSE_LAGER " +
		    "	 AND V1.ID_ARTIKEL = K.ID_ARTIKEL " +
		    "	 GROUP BY  V1.ID_ADRESSE_LAGER_ZIEL, V1.ID_ARTIKEL ) " +
		    " ,0) as RESTMENGE_AUF_KONTRAKT, " +
		    " 0 " + 
//		    " SUM(L.LAGERMENGE) - " +
//		    " NVL(" +
//		    "	(SELECT SUM(  " +
//		    "  		CASE nvl(V2.ANTEIL_LADEMENGE_LIEF,-1)  WHEN -1 THEN nvl(V2.ANTEIL_PLANMENGE_LIEF,0) ELSE V2.ANTEIL_LADEMENGE_LIEF  END " +
//		    "	)  " +
//		    " 	FROM  " + m_VFuhren + " V2 " +
//		    " 	LEFT OUTER JOIN " + m_TableOwner + ".JT_VPOS_KON K2  ON   ( K2.ID_VPOS_KON = V2.ID_VPOS_KON_EK    )  " + 
//		    " 	INNER JOIN  " + m_TableOwner + ".JT_VPOS_KON_LAGER L2  ON ( L2.ID_VPOS_KON = K2.ID_VPOS_KON )  " +
//		    " 	WHERE  nvl(V2.DELETED,'N') = 'N' " +
//		    " 	AND nvl(V2.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " +
//		    " 	AND nvl(V2.IST_STORNIERT,'N') = 'N' " +
//		    " 	AND (V2.ID_ADRESSE_LAGER_ZIEL = L2.ID_ADRESSE_LAGER) " +
//		    " 	AND V2.ID_ADRESSE_LAGER_ZIEL = L.ID_ADRESSE_LAGER " +
//		    " 	AND V2.ID_ARTIKEL = K.ID_ARTIKEL " +
//		    " 	GROUP BY  V2.ID_ADRESSE_LAGER_ZIEL, V2.ID_ARTIKEL ) " +
//		    ",0) as RESTMENGE_AUF_KONTRAKT_P " +
		    " FROM  " +
		     m_TableOwner + ".JT_VPOS_KON K " +
		    " INNER JOIN " +
		    m_TableOwner + ".JT_VKOPF_KON KOPF  ON ( K.ID_VKOPF_KON = KOPF.ID_VKOPF_KON )  " +
		    " INNER JOIN " +
		    m_TableOwner + ".JT_VPOS_KON_TRAKT TRAKT  ON ( K.ID_VPOS_KON = TRAKT.ID_VPOS_KON )  " +
		    " INNER JOIN " +
		     m_TableOwner + ".JT_VPOS_KON_LAGER L  ON  (  K.ID_VPOS_KON = L.ID_VPOS_KON )  " +
			" WHERE KOPF.VORGANG_TYP = 'EK_KONTRAKT' " +
			" AND  NVL(TRAKT.ABGESCHLOSSEN,'N') = 'N' " +
			sSqlWhere + 
			" GROUP BY  L.ID_ADRESSE_LAGER,  K.ID_ARTIKEL ";

//UNION

//-- (VK)-Kontrakt-Lagermengen und die Restmengen, die nicht von Fuhren verbraucht sind.
String sSqlVK = 
			" SELECT 'VK', L.ID_ADRESSE_LAGER, K.ID_ARTIKEL, sum(L.LAGERMENGE), " + 
		    " SUM(L.LAGERMENGE) - " +
		    " NVL(" +
		    "	(SELECT SUM(  " +
		    " 		V1.ANTEIL_ABLADEMENGE_ABN " +
		    "	) as SUM_LADEMENGE " +
		    " 	FROM  " + m_VFuhren + " V1 " +
		    " 	LEFT OUTER JOIN " + m_TableOwner + ".JT_VPOS_KON K1   ON   ( K1.ID_VPOS_KON = V1.ID_VPOS_KON_VK ) " + 
		    "	INNER JOIN " + m_TableOwner + ".JT_VPOS_KON_TRAKT TRAKT1  ON ( K1.ID_VPOS_KON = TRAKT1.ID_VPOS_KON ) " +
			" 	INNER JOIN  " + m_TableOwner + ".JT_VPOS_KON_LAGER L1  ON ( L1.ID_VPOS_KON   = K1.ID_VPOS_KON )  " +
			" 	WHERE  nvl(V1.DELETED,'N') = 'N' " +
			" 	AND nvl(V1.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " +
			" 	AND nvl(V1.IST_STORNIERT,'N') = 'N' " +
			"	AND NVL(TRAKT1.ABGESCHLOSSEN,'N') = 'N' " +
			" 	AND (V1.ID_ADRESSE_LAGER_START =L1.ID_ADRESSE_LAGER) " +
			" 	AND V1.ID_ADRESSE_LAGER_START = L.ID_ADRESSE_LAGER " +
			" 	AND V1.ID_ARTIKEL = K.ID_ARTIKEL " +
			" 	GROUP BY  V1.ID_ADRESSE_LAGER_START, V1.ID_ARTIKEL ) " +
			" ,0) as RESTMENGE_AUF_KONTRAKT, " +
			
			" 0 " + 
//			" SUM(L.LAGERMENGE) - " +
//			" NVL(" +
//			"	(SELECT SUM(  " +
//			"		 CASE nvl(V2.ANTEIL_ABLADEMENGE_ABN,-1) WHEN -1 THEN nvl(V2.ANTEIL_PLANMENGE_ABN ,0)  ELSE V2.ANTEIL_ABLADEMENGE_ABN  END " +
//			"	)  " +
//			" 	FROM  " + m_VFuhren + " V2 " +
//			" 	LEFT OUTER JOIN " + m_TableOwner + ".JT_VPOS_KON K1  ON   ( K1.ID_VPOS_KON = V2.ID_VPOS_KON_VK    ) " + 
//			" 	INNER JOIN  " + m_TableOwner + ".JT_VPOS_KON_LAGER L1  ON ( L1.ID_VPOS_KON = K1.ID_VPOS_KON )  " +
//			" 	WHERE  nvl(V2.DELETED,'N') = 'N' " +
//			" 	AND nvl(V2.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " +
//			" 	AND nvl(V2.IST_STORNIERT,'N') = 'N' " +
//			" 	AND (V2.ID_ADRESSE_LAGER_START = L1.ID_ADRESSE_LAGER) " +
//			" 	AND V2.ID_ADRESSE_LAGER_START = L.ID_ADRESSE_LAGER " +
//			" 	AND V2.ID_ARTIKEL = K.ID_ARTIKEL " +
//			" 	GROUP BY  V2.ID_ADRESSE_LAGER_START, V2.ID_ARTIKEL ) " +
//			",0) as RESTMENGE_AUF_KONTRAKT_P " +
			" FROM  " +
			m_TableOwner + ".JT_VPOS_KON K " +
			" INNER JOIN  " +
			" " + m_TableOwner + ".JT_VKOPF_KON KOPF ON ( K.ID_VKOPF_KON = KOPF.ID_VKOPF_KON ) " +
			" INNER JOIN " + 
			m_TableOwner + ".JT_VPOS_KON_LAGER L  ON  ( K.ID_VPOS_KON = L.ID_VPOS_KON )  " +
		    " INNER JOIN " +
		    m_TableOwner + ".JT_VPOS_KON_TRAKT TRAKT  ON ( K.ID_VPOS_KON = TRAKT.ID_VPOS_KON )  " +
			" WHERE  " +
			" KOPF.VORGANG_TYP = 'VK_KONTRAKT'   " +
			" AND  NVL(TRAKT.ABGESCHLOSSEN,'N') = 'N' " +
			sSqlWhere +			
			" GROUP BY  L.ID_ADRESSE_LAGER, K.ID_ARTIKEL ";				

		String sSql = sSqlEK + " UNION " + sSqlVK;
		
		cLagerDaten = new String[0][0];
		cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
	
	}


	/**
	 * Die Ergebnismenge wird gelöscht.
	 */
	public void ClearData(){
		cLagerDaten = new String[0][0];
	}
	
	
	/**
	 * Nachdem die Daten durch die Methode ReadVertragsLagerDaten() vorbereitet wurden, kann 
	 * durch getData(..) die Information für eine Lager/Sorten-Kombination ermittelt werden. 
	 * @param idLager
	 * @param idSorte
	 * @return
	 */
	public LAG_LagerMengenDaten getData(String idLager, String idSorte) {
		
		LAG_LagerMengenDaten data = new LAG_LagerMengenDaten();
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cLagerDaten == null || cLagerDaten.length == 0){
			return data;
		}
//		
		
		BigDecimal 	dLagermenge = 	 null;
		BigDecimal 	dRestmenge = 	 null;
		BigDecimal 	dRestmengePlan = null;
		
		// sonst den Vektor füllen
		for (int i = 0; i < cLagerDaten.length; i++){
			
			String 		Typ	=				cLagerDaten[i][0];
			String 		Lager = 			cLagerDaten[i][1];
			String		Sorte =				cLagerDaten[i][2];
			String 		Lagermenge = 		cLagerDaten[i][3];
			String 		Restmenge = 		cLagerDaten[i][4];
			String 		RestmengePlan = 	cLagerDaten[i][5];

			if (Lager.equals(idLager) && Sorte.equals(idSorte)){
				dLagermenge = 		Lagermenge != null ? new BigDecimal(Lagermenge) : BigDecimal.ZERO ;
				dRestmenge = 		Restmenge != null ? new BigDecimal(Restmenge) : BigDecimal.ZERO ;
				dRestmengePlan = 	RestmengePlan != null ? new BigDecimal(RestmengePlan) : BigDecimal.ZERO ;
				
				if (Typ.equals("EK")){
					data.setEKLagermenge( dLagermenge);
					data.setEKRestmenge(dRestmenge);
					data.setEKRestmengeMitPlanung(dRestmengePlan);
				} else {
					data.setVKLagermenge( dLagermenge);
					data.setVKRestmenge(dRestmenge);
					data.setVKRestmengeMitPlanung(dRestmengePlan);
				}
			}
		
		}
			
		// Gesamtlagerdaten 
		data.setGesamtRestmenge(data.getEKRestmenge().subtract(data.getVKRestmenge()));
		data.setGesamtRestmengeMitPlanung( data.getEKRestmengeMitPlanung().subtract(data.getVKRestmengeMitPlanung()));
		
		return data;
		
	}
	

}
