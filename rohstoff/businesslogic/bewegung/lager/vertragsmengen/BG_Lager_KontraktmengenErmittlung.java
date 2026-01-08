package rohstoff.businesslogic.bewegung.lager.vertragsmengen;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BG_Lager_KontraktmengenErmittlung {

	private HashMap<String, BG_Lager_KontraktmengenDaten> m_Lagerdaten = null;
	
	
	private String m_IdMandant = null;
	private String m_TableOwner = null;
	private boolean m_bCompleteList = false;	

	
	
	public BG_Lager_KontraktmengenErmittlung() {
		m_IdMandant = bibALL.get_ID_MANDANT();
		m_TableOwner = bibALL.get_TABLEOWNER();
	}

 
	
	/**
	 * Ermittelt für alle Läger und alle Sorten in einem Statement alle Restmengen
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager, idSorte) gelesen werden 
	 * @throws myException
	 */
	public void ReadVertragsLagerDaten() throws myException{
		ReadVertragsLagerDaten(null,null,null);
		
	}

	/**
	 * Ermittelt alle Restmengen für die gegebenen Parameter
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager, idSorte) gelesen werden 
	 * @param IDLager
	 * @param IDSorte
	 * @throws myException
	 */
	public void ReadVertragsLagerDaten(String sIDLager, String sIDHauptsorte, String sIDSorte ) throws myException{

		this.ClearData();

		String sSqlWhere = "";
		
		if (sIDLager != null && sIDLager.trim().length() > 0) {
			sSqlWhere += " AND L.ID_ADRESSE_LAGER = " + sIDLager;
		}
		
		if (sIDSorte != null && sIDSorte.trim().length() > 0) {
			sSqlWhere += " AND K.ID_ARTIKEL = " + sIDSorte ;
			
		} else if (sIDHauptsorte != null && sIDHauptsorte.trim().length() > 0){
			sSqlWhere += " AND  K.ID_ARTIKEL in (SELECT A.ID_ARTIKEL FROM " +   m_TableOwner + ".JT_ARTIKEL A where SUBSTR(A.ANR1,0,2) = '" + sIDHauptsorte + "' ) "   ;
		}
		
		//merken, ob die Liste für die gesamten Lager/Sorten-Kombinationen erstellt wurde
		m_bCompleteList = bibALL.isEmpty(sSqlWhere);
		
		
		String sSqlEK = 
			"SELECT 'EK', L.ID_ADRESSE_LAGER, K.ID_ARTIKEL, SUM(L.LAGERMENGE), " + 
			" SUM (L.LAGERMENGE) - " + 
		    "    NVL( " +
		    "	 (SELECT SUM( LAD1.MENGE_NETTO  ) AS SUM_ABLADEMENGE " +
		    "	 FROM                 " + m_TableOwner + ".JT_BG_LADUNG LAD1 " + 
		    "	  INNER JOIN          " + m_TableOwner + ".JT_BG_ATOM A1                   ON LAD1.ID_BG_ATOM = A1.ID_BG_ATOM  " +
		    "	  INNER JOIN          " + m_TableOwner + ".JT_BG_STATION S1                ON LAD1.ID_BG_STATION = S1.ID_BG_STATION  " +
		    "     INNER JOIN          " + m_TableOwner + ".JT_BG_LADUNG L2                 ON A1.ID_BG_ATOM = L2.ID_BG_ATOM and L2.MENGENVORZEICHEN = LAD1.MENGENVORZEICHEN*-1" +
		    "     INNER JOIN          " + m_TableOwner + ".JT_BG_STATION S2                ON L2.ID_BG_STATION = S2.ID_BG_STATION " +
		    "     LEFT OUTER JOIN     " + m_TableOwner + ".JT_VPOS_KON K1     		 	   ON K1.ID_VPOS_KON = LAD1.ID_VPOS_KON" +
		    "     INNER JOIN          " + m_TableOwner + ".JT_VPOS_KON_TRAKT TRAKT1        ON K1.ID_VPOS_KON = TRAKT1.ID_VPOS_KON " +
		    "     INNER JOIN          " + m_TableOwner + ".JT_VPOS_KON_LAGER L1            ON L1.ID_VPOS_KON = K1.ID_VPOS_KON " +
		    "  WHERE   LAD1.ID_BG_DEL_INFO IS NULL" +
		    "     AND LAD1.ID_BG_STORNO_INFO IS NULL " +
		    "     AND LAD1.MENGENVORZEICHEN = -1 " + 
		    "     AND NVL(TRAKT1.ABGESCHLOSSEN,'N') = 'N' " +
		    "     AND S2.ID_ADRESSE = L1.ID_ADRESSE_LAGER " +
		    "     AND S2.ID_ADRESSE = L.ID_ADRESSE_LAGER  " +
		    "     AND LAD1.ID_ARTIKEL = K1.ID_ARTIKEL " +
		    "     AND LAD1.ID_ARTIKEL = K.ID_ARTIKEL " +
		    "     GROUP BY 					" +
		    "       S2.ID_ADRESSE,			" +
		    "       LAD1.ID_ARTIKEL       	" +
		    "   )                           " +
		    " ,0) as RESTMENGE_AUF_KONTRAKT, " +
		    " 0 " + 
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
			" SUM (L.LAGERMENGE) - " + 
		    "    NVL( " +
		    "	 (SELECT SUM( LAD1.MENGE_NETTO  ) AS SUM_ABLADEMENGE " +
		    "	 FROM                 " + m_TableOwner + ".JT_BG_LADUNG LAD1 " + 
		    "	  INNER JOIN          " + m_TableOwner + ".JT_BG_ATOM A1                   ON LAD1.ID_BG_ATOM = A1.ID_BG_ATOM  " +
		    "	  INNER JOIN          " + m_TableOwner + ".JT_BG_STATION S1                ON LAD1.ID_BG_STATION = S1.ID_BG_STATION  " +
		    "     INNER JOIN          " + m_TableOwner + ".JT_BG_LADUNG L2                 ON A1.ID_BG_ATOM = L2.ID_BG_ATOM and L2.MENGENVORZEICHEN = LAD1.MENGENVORZEICHEN*-1" +
		    "     INNER JOIN          " + m_TableOwner + ".JT_BG_STATION S2                ON L2.ID_BG_STATION = S2.ID_BG_STATION " +
		    "     LEFT OUTER JOIN     " + m_TableOwner + ".JT_VPOS_KON K1     		 	   ON K1.ID_VPOS_KON = LAD1.ID_VPOS_KON" +
		    "     INNER JOIN          " + m_TableOwner + ".JT_VPOS_KON_TRAKT TRAKT1        ON K1.ID_VPOS_KON = TRAKT1.ID_VPOS_KON " +
		    "     INNER JOIN          " + m_TableOwner + ".JT_VPOS_KON_LAGER L1            ON L1.ID_VPOS_KON = K1.ID_VPOS_KON " +
		    "  WHERE   LAD1.ID_BG_DEL_INFO IS NULL" +
		    "     AND LAD1.ID_BG_STORNO_INFO IS NULL " +
		    "     AND LAD1.MENGENVORZEICHEN = +1 " + 
		    "     AND NVL(TRAKT1.ABGESCHLOSSEN,'N') = 'N' " +
		    "     AND S2.ID_ADRESSE = L1.ID_ADRESSE_LAGER " +
		    "     AND S2.ID_ADRESSE = L.ID_ADRESSE_LAGER  " +
		    "     AND LAD1.ID_ARTIKEL = K1.ID_ARTIKEL " +
		    "     AND LAD1.ID_ARTIKEL = K.ID_ARTIKEL " +
		    "     GROUP BY 					" +
		    "       S2.ID_ADRESSE,			" +
		    "       LAD1.ID_ARTIKEL       	" +
		    "   )                           " +
		    " ,0) as RESTMENGE_AUF_KONTRAKT, " +
			
			" 0 " + 

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

		String [][] cLagerDaten = new String[0][0];
		cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		
		// Hashmap füllen mit den gefundenen Kontrakt-Lagerdaten
		for (int i = 0; i < cLagerDaten.length; i++) {

			String 		Typ	=				cLagerDaten[i][0];
			String 		Lager = 			cLagerDaten[i][1];
			String		Sorte =				cLagerDaten[i][2];
			String 		Lagermenge = 		cLagerDaten[i][3];
			String 		Restmenge = 		cLagerDaten[i][4];
			String 		RestmengePlan = 	cLagerDaten[i][5];
		
			
			BigDecimal dLagermenge = Lagermenge != null ? new BigDecimal(Lagermenge) : BigDecimal.ZERO;
			BigDecimal dRestmenge = Restmenge != null ? new BigDecimal(	Restmenge) : BigDecimal.ZERO;
			BigDecimal dRestmengePlan = RestmengePlan != null ? new BigDecimal(	RestmengePlan) : BigDecimal.ZERO;

			String sKey = Lager + "#" + Sorte;
			BG_Lager_KontraktmengenDaten oDaten  = null;

			// Datenobjekt holen bzw. erzeugen
			if (m_Lagerdaten.containsKey(sKey)){
				oDaten = m_Lagerdaten.get(sKey);
			} else {
				oDaten = new BG_Lager_KontraktmengenDaten(Lager,Sorte);
				m_Lagerdaten.put(sKey, oDaten);
			}

			
			// Daten aktualisieren
			if (Typ.equals("EK")){
				oDaten.setEKLagermenge( dLagermenge);
				oDaten.setEKRestmenge(dRestmenge);
				oDaten.setEKRestmengeMitPlanung(dRestmengePlan);
			} else {
				oDaten.setVKLagermenge( dLagermenge);
				oDaten.setVKRestmenge(dRestmenge);
				oDaten.setVKRestmengeMitPlanung(dRestmengePlan);
			}
			
			// Gesamtlagerdaten 
			oDaten.setGesamtRestmenge(oDaten.getEKRestmenge().subtract(oDaten.getVKRestmenge()));
			oDaten.setGesamtRestmengeMitPlanung( oDaten.getEKRestmengeMitPlanung().subtract(oDaten.getVKRestmengeMitPlanung()));
			
		}
		
		
	}

	
	

	/**
	 * Die Ergebnismenge wird gelöscht.
	 */
	public void ClearData(){
		if (m_Lagerdaten != null){
			m_Lagerdaten.clear();
		}
		m_Lagerdaten = new HashMap<String, BG_Lager_KontraktmengenDaten>();
	}
	
	/**
	 * Gibt zurück, ob die Liste schon aktualisiert wurde
	 * @author manfred
	 * @date   25.06.2013
	 * @return
	 */
	public boolean IsInitialized(){
		return m_Lagerdaten != null && m_Lagerdaten.size() > 0;
	}
	
	/**
	 * Nachdem die Daten durch die Methode ReadVertragsLagerDaten() vorbereitet wurden, kann 
	 * durch getData(..) die Information für eine Lager/Sorten-Kombination ermittelt werden. 
	 * @param idLager
	 * @param idArtikel
	 * @return
	 */
	public BG_Lager_KontraktmengenDaten getData(String idLager, String idArtikel) {

		String sKey = idLager + "#" + idArtikel;
		if (!m_Lagerdaten.containsKey(sKey)){
			return null;
		} else {
			return m_Lagerdaten.get(sKey);
		}
	}
	

}
