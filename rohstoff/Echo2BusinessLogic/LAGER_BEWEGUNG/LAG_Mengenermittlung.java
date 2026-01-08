package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;

/**
 * Klasse zur Mengenermittlung von nicht verbuchten WE-Positionen
 * die unterhalb eines bestimmten Preises liegen
 * @author manfred
 *
 */
public class LAG_Mengenermittlung extends Observable{

	// Vector mit den IDs der LAGER_KONTO-Einträge, die in der Selektion vorkommen
	private Vector<String>  vAuswahlListe = null;
	private BigDecimal      m_AktuelleMenge = null;
	private BigDecimal 		m_AktuellerPreis = null;
	
	private String			m_sIDEinheitReferenz = null;
	private String 			m_sEinheit = null;
	
	
	/**
	 * Standard-Konstruktor
	 * initialisiert alle Werte
	 */
	public LAG_Mengenermittlung() {
		vAuswahlListe = new Vector<String>();
		m_AktuelleMenge = BigDecimal.ZERO;
		m_AktuellerPreis = BigDecimal.ZERO;
	}



	/**
	 * Ermittelt die Liste der aktuellen Läger IDs und summiert die Mengen zur Abfrage
	 * Ergebnis muss abgefragt werden mit 
	 * 		- getErmittelteMenge()
	 * 		- getErmittelterPreis()
	 * 		- getMengeneinheit()
	 * 
	 * Author: manfred
	 * 08.07.2009
	 *
	 * @param IDAdresseLager
	 * @param IDSorte
	 * @param maxBetrag - der Maximalbetrag (kleiner gleich) 
	 */
	public void ermittleMengeZuMaximalpreis(String IDAdresseLager, String IDHauptsorte, String IDSorte, BigDecimal maxBetrag ) {
		
		// löschen der aktuellen Daten
		this.clearAuswahlListe();
		this.m_AktuelleMenge = BigDecimal.ZERO;
		this.m_AktuellerPreis = BigDecimal.ZERO;
		
		BigDecimal bdRet = new BigDecimal(0);
		
		
		// wenn einer der Parameter leer oder null ist, dann zurück
		/*bibALL.isEmpty(IDAdresseLager) || */
		if ( (bibALL.isEmpty(IDSorte) && bibALL.isEmpty(IDHauptsorte)) || maxBetrag == null){
			return;
		}
		
		String sWhereSorte = " ";
		if (!bibALL.isEmpty(IDSorte)){
			sWhereSorte += " AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = " + IDSorte + " ";
		}
		if (!bibALL.isEmpty(IDHauptsorte)){
			sWhereSorte += " AND SUBSTR(JT_ARTIKEL.ANR1,0,2) = '" +IDHauptsorte.trim() + "' ";
		}
		
		
		String sWhereLager = "";
		if (!bibALL.isEmpty(IDAdresseLager)){
			sWhereLager += " AND JT_LAGER_KONTO.ID_ADRESSE_LAGER = " + IDAdresseLager;
		}
		
		String sSql = 
				" SELECT JT_LAGER_KONTO.ID_LAGER_KONTO,   " +
				" JT_LAGER_KONTO.MENGE -  nvl( (Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				" from  "+bibE2.cTO()+".JT_LAGER_BEWEGUNG where JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO),0) as OFFEN" +
				", JT_ARTIKEL.ID_EINHEIT " +
				",  E1.EINHEITKURZ " +
				" FROM  "+bibE2.cTO()+".JT_LAGER_KONTO " +
				" INNER JOIN  "+bibE2.cTO()+".JT_ARTIKEL " +
						" ON JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "+
				" INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on JT_ARTIKEL.ID_EINHEIT = E1.ID_EINHEIT " +
				" LEFT OUTER JOIN " +
				"	(" +
				"	SELECT I.ID_ADRESSE_LAGER adr, I.ID_ARTIKEL_SORTE art, NVL(max(I.BUCHUNGSDATUM), to_date('2000-01-01','yyyy-MM-dd') ) as INVENTURDATUM " +
				" 	FROM " + bibE2.cTO() + ".JT_LAGER_INVENTUR I  GROUP BY I.ID_ADRESSE_LAGER, I.ID_ARTIKEL_SORTE " +
				"	) INVENTUR ON ( adr = JT_LAGER_KONTO.ID_ADRESSE_LAGER AND art = JT_LAGER_KONTO.ID_ARTIKEL_SORTE )" +
				" " +
				" WHERE  (JT_LAGER_KONTO.STORNO is null OR JT_LAGER_KONTO.STORNO = 'N')"+
				" AND JT_LAGER_KONTO.BUCHUNGSTYP = 'WE'"+
				"	AND (JT_LAGER_KONTO.IST_KOMPLETT  IS NULL or JT_LAGER_KONTO.IST_KOMPLETT = 'N')"+
				"	and (JT_LAGER_KONTO.MENGE -  nvl( ( Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) "+
				"	from  "+bibE2.cTO()+".JT_LAGER_BEWEGUNG where JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO),0) ) > 0"+
				" AND JT_LAGER_KONTO.PREIS <= " + maxBetrag.toPlainString() + 
				" AND ( NVL(INVENTURDATUM,to_date('2000-01-01','yyyy-MM-dd')) < JT_LAGER_KONTO.BUCHUNGSDATUM ) " + 
				sWhereLager + 
				sWhereSorte;
		
				//" AND JT_LAGER_KONTO.ID_ADRESSE_LAGER = " + IDAdresseLager + 
				//" AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = " + IDSorte;
		try {
			
			String [][] cLagerDaten = new String[0][0];
			cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0){
				return ;
			}
			
		
			
			// sonst den Vektor füllen
			for (int i = 0; i < cLagerDaten.length; i++){
				String 		IDLagerKonto = 			cLagerDaten[i][0];
				String		sMengeOffen = 			cLagerDaten[i][1];
				String 		sIDEinheit	= 			cLagerDaten[i][2];
				String 		sEinheit	= 			cLagerDaten[i][3];
				
				
				if (m_sIDEinheitReferenz == null){
					m_sIDEinheitReferenz = sIDEinheit;
					m_sEinheit = sEinheit;
				}
				
				if (!sIDEinheit.equals(m_sIDEinheitReferenz)){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Ermittlung der Kennzahlen ist nur möglich, " +
									"wenn alle Buchungen die gleiche Mengeneinheit (z.B. t, Kg ) haben")));
					// die Observer benachrichtigen
					clearAuswahlListe();
					return;
				}
				
				
				if (IDLagerKonto != null){
					// die Lagerid zur Liste hinzufügen
					this.addLagerKontoId(IDLagerKonto);
					// die offene Menge summieren
					BigDecimal bdMenge = new BigDecimal(sMengeOffen);
					bdMenge = bdMenge.setScale(3, BigDecimal.ROUND_HALF_UP);
					bdRet = bdRet.add(bdMenge);
				}
			}
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der möglichen Lagerbestände.")));
		}
		
		// übernehmen der aktuellen Menge
		m_AktuelleMenge = bdRet;
		m_AktuellerPreis = maxBetrag;
		
		// die Observer benachrichtigen
		setChanged();
		notifyObservers();
		
	}
	
	
	
	
	/**
	 * Ermittelt eine Menge, die den gegebenen Durchschnittspreis ergeben. 
	 * Es werden die Lagereinträge in einer Liste gesammelt, die den Kriterien entsprechen.
	 * Das Ergebnis muss abgefragt werden mit 
	 * 		- getErmittelteMenge()
	 * 		- getErmittelterPreis()
	 * 		- getMengeneinheit()
	 * 
	 * Author: manfred
	 * 13.11.2009
	 *
	 * @param IDAdresseLager
	 * @param IDSorte
	 * @param avgBetrag - der Durchschnittsbetrag (kleiner gleich) 
	 */
	public void ermittleMengeZuDurchschnittspreis(String IDAdresseLager, String IDHauptsorte, String IDSorte, BigDecimal avgBetrag ){
		
		// löschen der aktuellen Daten
		this.clearAuswahlListe();
		this.m_AktuelleMenge = BigDecimal.ZERO;
		this.m_AktuellerPreis = BigDecimal.ZERO;
		
		BigDecimal bdAvgPreis = BigDecimal.ZERO;
		BigDecimal bdAvgPreisOld = BigDecimal.ZERO;
		
		
		BigDecimal bdMengeRet = new BigDecimal(0);
		
		
		// wenn einer der Parameter leer oder null ist, dann zurück
		/*bibALL.isEmpty(IDAdresseLager) || */
		if ( (bibALL.isEmpty(IDSorte) && bibALL.isEmpty(IDHauptsorte)) || avgBetrag == null){
			return;
		}
		
		String sWhereSorte = " ";
		if (!bibALL.isEmpty(IDSorte)){
			sWhereSorte += " AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = " + IDSorte + " ";
		}
		if (!bibALL.isEmpty(IDHauptsorte)){
			sWhereSorte += " AND SUBSTR(JT_ARTIKEL.ANR1,0,2) = '" +IDHauptsorte.trim() + "' ";
		}
		
		
		String sWhereLager = "";
		if (!bibALL.isEmpty(IDAdresseLager)){
			sWhereLager += " AND JT_LAGER_KONTO.ID_ADRESSE_LAGER = " + IDAdresseLager;
		}
		
		String sSql = 
				" SELECT JT_LAGER_KONTO.ID_LAGER_KONTO,   " +
				"JT_LAGER_KONTO.MENGE -  nvl( (Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				" from  "+bibE2.cTO()+".JT_LAGER_BEWEGUNG where JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO),0) as OFFEN" +
				", NVL(JT_LAGER_KONTO.PREIS,0.0) " + 
				", JT_ARTIKEL.ID_EINHEIT " +
				",  E1.EINHEITKURZ " +
				" FROM  "+bibE2.cTO()+".JT_LAGER_KONTO INNER JOIN  "+bibE2.cTO()+".JT_ARTIKEL " +
						" ON JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "+
				" INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on JT_ARTIKEL.ID_EINHEIT = E1.ID_EINHEIT " + 
				" LEFT OUTER JOIN " +
				"	(" +
				"	SELECT I.ID_ADRESSE_LAGER adr, I.ID_ARTIKEL_SORTE art, NVL(max(I.BUCHUNGSDATUM), to_date('2000-01-01','yyyy-MM-dd') ) as INVENTURDATUM " +
				" 	FROM " + bibE2.cTO() + ".JT_LAGER_INVENTUR I  GROUP BY I.ID_ADRESSE_LAGER, I.ID_ARTIKEL_SORTE " +
				"	) INVENTUR ON ( adr = JT_LAGER_KONTO.ID_ADRESSE_LAGER AND art = JT_LAGER_KONTO.ID_ARTIKEL_SORTE )" +
				" " +
				" WHERE  NVL(JT_LAGER_KONTO.STORNO,'N') = 'N' "+
				" 	AND JT_LAGER_KONTO.BUCHUNGSTYP = 'WE' "+
				"	AND NVL(JT_LAGER_KONTO.IST_KOMPLETT,'N') = 'N' "+
				"	AND (JT_LAGER_KONTO.MENGE -  NVL( ( Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) "+
				"		from  "+bibE2.cTO()+".JT_LAGER_BEWEGUNG where JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO),0) ) > 0"+
				"   AND PREIS IS NOT NULL " +
				" AND ( NVL(INVENTURDATUM,to_date('2000-01-01','yyyy-MM-dd')) < JT_LAGER_KONTO.BUCHUNGSDATUM ) " + 
				sWhereLager + 
				sWhereSorte + 
				" ORDER BY JT_LAGER_KONTO.PREIS, JT_LAGER_KONTO.MENGE, JT_LAGER_KONTO.BUCHUNGSDATUM" ;

		try {
			
			String [][] cLagerDaten = new String[0][0];
			cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0){
				return ;
			}
			
			BigDecimal bdMengeTemp = BigDecimal.ZERO;
			BigDecimal bdPreisTemp = BigDecimal.ZERO;
			
			
			// sonst den Vektor füllen
			for (int i = 0; i < cLagerDaten.length; i++){
				String 		IDLagerKonto = 			cLagerDaten[i][0];
				String		sMengeOffen = 			cLagerDaten[i][1];
				String 		sPreis	= 				cLagerDaten[i][2];
				String 		sIDEinheit = 			cLagerDaten[i][3];
				String 		sEinheit	= 			cLagerDaten[i][4];
				
				BigDecimal bdPreis = new BigDecimal(sPreis);
				
				
				if (m_sIDEinheitReferenz == null){
					m_sIDEinheitReferenz = sIDEinheit;
					m_sEinheit= sEinheit;
				}
				
				if (!sIDEinheit.equals(m_sIDEinheitReferenz)){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Ermittlung der Kennzahlen ist nur möglich, " +
									"wenn alle Buchungen die gleiche Mengeneinheit (z.B. t, Kg ) haben")));
					// die Observer benachrichtigen
					clearAuswahlListe();
					return;
				}

				
				
				if (IDLagerKonto != null){
					
					// die offene Menge summieren
					BigDecimal bdMenge = new BigDecimal(sMengeOffen);
					bdMenge = bdMenge.setScale(3, BigDecimal.ROUND_HALF_UP);
					
					// Gesamtmenge
					bdMengeTemp = bdMengeTemp.add(bdMenge);
					
					// Gesamtpreis 
					bdPreisTemp = bdPreisTemp.add(bdMenge.multiply(bdPreis));
					
					// aktueller Durchschnittspreis
					bdAvgPreis = bdPreisTemp.divide(bdMengeTemp,3,BigDecimal.ROUND_HALF_UP);
					
					if (bdAvgPreis.compareTo(avgBetrag) <= 0){
						// die Lagerid zur Liste hinzufügen
						this.addLagerKontoId(IDLagerKonto);
						bdMengeRet = bdMengeRet.add(bdMenge);
						bdAvgPreisOld = bdAvgPreis;
					} else {
						// Mengenanteil des nächsthöheren Haufens berechnen:
						// P1: aktueller Durchschnittspreis
						// M1: aktuelle Durchschnittsmenge
						//
						//  M(von_haufen) = ( (P1*M1) - (Psoll*M1) ) / (Psoll - P_haufen) 
						//
						// wenn der aktuelle Durchschnittspreis 0 ist, dann gibt es keine Menge!
						if (bdAvgPreisOld.compareTo(BigDecimal.ZERO) != 0 ){
							BigDecimal bdM_haufen = BigDecimal.ZERO;
							bdM_haufen = bdAvgPreisOld.multiply(bdMengeRet).subtract(avgBetrag.multiply(bdMengeRet));
							bdM_haufen = bdM_haufen.divide(avgBetrag.subtract(bdPreis), 1, BigDecimal.ROUND_HALF_UP);
							
							BigDecimal bdP1 = bdAvgPreisOld.multiply(bdMengeRet);
							BigDecimal bdP2 = bdPreis.multiply(bdM_haufen);
							BigDecimal bdP3 = bdP1.add(bdP2).divide(bdMengeRet.add(bdM_haufen),2,BigDecimal.ROUND_HALF_UP);
							
							bdAvgPreisOld = bdP3; 
							bdMengeRet = bdMengeRet.add(bdM_haufen);
							
							this.addLagerKontoId(IDLagerKonto);
						}
						// exit for-Schleife
						break;
					}
				}
			}
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der möglichen Lagerbestände.")));
		}
		
		// übernehmen der aktuellen Menge
		m_AktuelleMenge = bdMengeRet;
		m_AktuellerPreis = bdAvgPreisOld;
		
		// die Observer benachrichtigen
		setChanged();
		notifyObservers();
	}

	/**
	 * Ermittelt den Durchschnittspreis zu einer gegebenen Menge eines Lagers und einer Sorte
	 * Wenn die Menge "null" oder negativ ist, dann wird der Durchschnittspreis des gesamten schon
	 * bewerteten und noch nicht verbuchten Lagers ermittelt.
	 * 
	 * Ergebnis muss abgefragt werden mit 
	 * 		- getErmittelteMenge()
	 * 		- getErmittelterPreis()
	 * 		- getMengeneinheit()
	 * 
	 * Author: manfred
	 * 13.11.2009
	 *
	 * @param IDAdresseLager
	 * @param IDHauptsorte
	 * @param IDSorte
	 * @param Menge 
	 */
	public void ermittleDurchschnittspreisVonMenge(String IDAdresseLager, String IDHauptsorte, String IDSorte, BigDecimal Menge ){
		this.ermittleDurchschnittspreisVonMenge(IDAdresseLager, IDHauptsorte, IDSorte, Menge, null, false );
	}
	
	
	/**
	 * Ermittelt den Durchschnittspreis zu einer gegebenen Menge eines Lagers und einer Sorte
	 * Wenn die Menge "null" oder negativ ist, dann wird der Durchschnittspreis des gesamten schon
	 * bewerteten und noch nicht verbuchten Lagers ermittelt.
	 * 
	 * Ergebnis muss abgefragt werden mit 
	 * 		- getErmittelteMenge()
	 * 		- getErmittelterPreis()
	 * 		- getMengeneinheit()
	 * 
	 * Author: manfred
	 * 13.11.2009
	 *
	 * @param IDAdresseLager
	 * @param IDHauptsorte
	 * @param IDSorte
	 * @param Menge 
	 */
	public void ermittleDurchschnittspreisVonMenge(String IDAdresseLager, String IDHauptsorte, String IDSorte, BigDecimal Menge, boolean bKeineNullPreise ){
		this.ermittleDurchschnittspreisVonMenge(IDAdresseLager, IDHauptsorte, IDSorte, Menge, null, bKeineNullPreise );
	}
	
		
	
	
	
	/**
	 * Ermittelt den Durchschnittspreis zu einer gegebenen Menge eines Lagers und einer Sorte
	 * Wenn die Menge "null" oder negativ ist, dann wird der Durchschnittspreis des gesamten schon
	 * bewerteten und noch nicht verbuchten Lagers ermittelt.
	 * 
	 * Ergebnis muss abgefragt werden mit 
	 * 		- getErmittelteMenge()
	 * 		- getErmittelterPreis()
	 * 		- getMengeneinheit()
	 * 
	 * Author: manfred
	 * 13.11.2009
	 *
	 * @param IDAdresseLager
	 * @param IDHauptsorte
	 * @param IDSorte
	 * @param Menge 
	 * @param IDFuhre  - wenn man für eine bestimmte Fuhre den Preis ermitteln möchte, darf die eigene Fuhre nicht berücksichtigt werden
	 * @param bKeineNullPreise - wenn true, werden Einträge mit Preisen mit dem Wert 0 Euro nicht berücksichtigt. 
	 * 
	 */
	public void ermittleDurchschnittspreisVonMenge(String IDAdresseLager, String IDHauptsorte, String IDSorte, BigDecimal Menge ,String IDFuhre, boolean bKeineNullPreise){
		
		// löschen der aktuellen Daten
		this.clearAuswahlListe();
		this.m_AktuelleMenge = BigDecimal.ZERO;
		this.m_AktuellerPreis = null;

		// es wird der Gesamtdurchschnitt berechnet, wenn keine Menge angegeben ist.
		boolean bGesamtdurchschnitt = ( Menge == null || Menge.compareTo(BigDecimal.ZERO) < 0 );
		
		BigDecimal bdMengeRet = new BigDecimal(0);
		BigDecimal bdPreisRet = new BigDecimal(0);
		
		// wenn einer der Parameter leer oder null ist, dann zurück
		/*bibALL.isEmpty(IDAdresseLager) || */
		if ( (bibALL.isEmpty(IDSorte) && bibALL.isEmpty(IDHauptsorte)) ){
			return;
		}
		
		String sWhereSorte = " ";
		if (!bibALL.isEmpty(IDSorte)){
			sWhereSorte += " AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = ? " ;
		}
		if (!bibALL.isEmpty(IDHauptsorte)){
			sWhereSorte += " AND SUBSTR(JT_ARTIKEL.ANR1,0,2) = ? " ; 
		}
		
		
		String sWhereLager = "";
		if (!bibALL.isEmpty(IDAdresseLager)){
			sWhereLager += " AND JT_LAGER_KONTO.ID_ADRESSE_LAGER = ? " ;
		}

		if (!bibALL.isEmpty(IDFuhre)){
			sWhereLager += " AND NVL(JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE,-1) != ? "; 
		}
		
		if (bKeineNullPreise){
			sWhereLager += " AND NVL(JT_LAGER_KONTO.PREIS,0.0) != 0.0 " ;
		}
		
		
		String sSql = 
				" SELECT JT_LAGER_KONTO.ID_LAGER_KONTO,   " +
				"JT_LAGER_KONTO.MENGE -  nvl( (Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				" from  "+bibE2.cTO()+".JT_LAGER_BEWEGUNG where JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO),0) as OFFEN" +
				", NVL(JT_LAGER_KONTO.PREIS,0.0) " + 
				", JT_ARTIKEL.ID_EINHEIT " +
				",  E1.EINHEITKURZ " +
				" FROM  "+bibE2.cTO()+".JT_LAGER_KONTO INNER JOIN  "+bibE2.cTO()+".JT_ARTIKEL " +
						" ON JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "+
						" INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on JT_ARTIKEL.ID_EINHEIT = E1.ID_EINHEIT " + 	
				" LEFT OUTER JOIN " +
						"	(" +
						"	SELECT I.ID_ADRESSE_LAGER adr, I.ID_ARTIKEL_SORTE art, NVL(max(I.BUCHUNGSDATUM), to_date('2000-01-01','yyyy-MM-dd') ) as INVENTURDATUM " +
						" 	FROM " + bibE2.cTO() + ".JT_LAGER_INVENTUR I  GROUP BY I.ID_ADRESSE_LAGER, I.ID_ARTIKEL_SORTE " +
						"	) INVENTUR ON ( adr = JT_LAGER_KONTO.ID_ADRESSE_LAGER AND art = JT_LAGER_KONTO.ID_ARTIKEL_SORTE )" +
						" " +
				" WHERE  NVL(JT_LAGER_KONTO.STORNO,'N') = 'N' "+
				" 	AND JT_LAGER_KONTO.BUCHUNGSTYP = 'WE' "+
				"	AND NVL(JT_LAGER_KONTO.IST_KOMPLETT,'N') = 'N' "+
				"	AND (JT_LAGER_KONTO.MENGE -  NVL( ( Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) "+
				"		from  "+bibE2.cTO()+".JT_LAGER_BEWEGUNG where JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO),0) ) > 0"+
				"   AND PREIS IS NOT NULL " +
				" AND ( NVL(INVENTURDATUM,to_date('2000-01-01','yyyy-MM-dd')) < JT_LAGER_KONTO.BUCHUNGSDATUM ) " + 
				sWhereLager + 
				sWhereSorte + 
				" ORDER BY JT_LAGER_KONTO.PREIS,JT_LAGER_KONTO.MENGE, JT_LAGER_KONTO.BUCHUNGSDATUM" ;

		try {
			
			SqlStringExtended sqlExt = new SqlStringExtended(sSql);
			
			if (!bibALL.isEmpty(IDSorte)){
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(IDSorte)));
			}
			
			if (!bibALL.isEmpty(IDHauptsorte)){
				sqlExt.getValuesList().add(new Param_String("",IDHauptsorte.trim()));
			}
			
			if (!bibALL.isEmpty(IDAdresseLager)){
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(IDAdresseLager)));
			}

			if (!bibALL.isEmpty(IDFuhre)){
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(IDFuhre)));
			}
			
			
			
			String [][] cLagerDaten = new String[0][0];
			cLagerDaten =  bibDB.EinzelAbfrageInArray(sqlExt,(String)null);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0){
				return ;
			}
			
			BigDecimal bdMengeTemp = BigDecimal.ZERO;
			
			// sonst den Vektor füllen
			for (int i = 0; i < cLagerDaten.length; i++){
				String 		IDLagerKonto = 			cLagerDaten[i][0];
				String		sMengeOffen = 			cLagerDaten[i][1];
				String 		sPreis	= 				cLagerDaten[i][2];
				String 		sIDEinheit = 			cLagerDaten[i][3];
				String 		sEinheit	= 			cLagerDaten[i][4];
				
				// die offene Menge summieren
				BigDecimal bdPreis = new BigDecimal(sPreis);
				BigDecimal bdMenge = new BigDecimal(sMengeOffen);

				bdMenge = bdMenge.setScale(3, BigDecimal.ROUND_HALF_UP);
				
				
				if (m_sIDEinheitReferenz == null){
					m_sIDEinheitReferenz = sIDEinheit;
					m_sEinheit = sEinheit;
				}

				
				if (!sIDEinheit.equals(m_sIDEinheitReferenz)){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Ermittlung der Kennzahlen ist nur möglich, " +
									"wenn alle Buchungen die gleiche Mengeneinheit (z.B. t, Kg ) haben")));
					// die Observer benachrichtigen
					clearAuswahlListe();
					return;
				}

				
				if (bGesamtdurchschnitt){  // alle Eintragungen verarbeiten
					
					bdMengeRet = bdMengeRet.add(bdMenge);
					bdPreisRet = bdPreisRet.add(bdMenge.multiply(bdPreis));
					
					// es wird keine Lagerid-Liste geschrieben!
				}
				else
				{ // nur solange verarbeiten, bis die Menge erreicht wurde.
					
					// Gesamtmenge prüfen, ob kleiner der gewünschten Menge
					bdMengeTemp = bdMengeTemp.add(bdMenge);
					
					if (bdMengeTemp.compareTo(Menge) <= 0){
						// die Lagerid zur Liste hinzufügen
						this.addLagerKontoId(IDLagerKonto);
						
						bdMengeRet = bdMengeRet.add(bdMenge);
						bdPreisRet = bdPreisRet.add(bdMenge.multiply(bdPreis));
						
					} else {
						// Restmenge:
						BigDecimal bdRestmenge = Menge.subtract(bdMengeRet);
						bdMengeRet = bdMengeRet.add(bdRestmenge);
						bdPreisRet = bdPreisRet.add(bdRestmenge.multiply(bdPreis));
						
						// die Lagerid zur Liste hinzufügen
						this.addLagerKontoId(IDLagerKonto);
						
						// exit for-Schleife
						break;
					}
				}
			}
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der möglichen Lagerbestände.")));
		}
		
		// berechnen des Durchschnittspreises für die Rückgabe:
		if (!bdMengeRet.equals(BigDecimal.ZERO)){
			m_AktuellerPreis = bdPreisRet.divide(bdMengeRet, 2, BigDecimal.ROUND_HALF_UP);
		} else {
			m_AktuellerPreis = null;
		}
		
		// übernehmen der aktuellen Menge
		m_AktuelleMenge = bdMengeRet;
		
		// die Observer benachrichtigen
		setChanged();
		notifyObservers();
	}

	
	
	
	
	/**
	 * Fügt eine ID zur Liste dazu
	 * Author: manfred
	 * 08.07.2009
	 *
	 * @param idLagerKonto
	 */
	private void addLagerKontoId(String idLagerKonto){
		this.vAuswahlListe.add(idLagerKonto);
	}
	
	
	/**
	 * Löschen aller Einträge in der Liste der Lagerkonten,
	 * Author: manfred
	 * 08.07.2009
	 *
	 */
	public void clearAuswahlListe(){
		this.vAuswahlListe.removeAllElements();
		this.m_AktuelleMenge = BigDecimal.ZERO;
		m_sEinheit = "";
		m_sIDEinheitReferenz = null;
		
		
		// die Observer-Objekte benachrichtigen
		setChanged();
		notifyObservers();
	}


	/**
	 * @return the vAuswahlListe
	 */
	public Vector<String> getAuswahlListe() {
		return vAuswahlListe;
	}



	/**
	 * @return the m_AktuelleMenge
	 */
	public BigDecimal get_ErmittelteMenge() {
		return m_AktuelleMenge;
	}
	
	public BigDecimal get_ErmittelterPreis(){
		return m_AktuellerPreis;
	}
	
	/**
	 * Gibt die ermittelte Mengeneinheit der aktuell berechnenten Sorten zurück!
	 * @return
	 */
	public String get_Mengeneinheit(){
		return m_sEinheit;
	}
	
	

	
}
