package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;

/**
 * Klasse zur Mengenermittlung von nicht verbuchten WE-Positionen
 * die unterhalb eines bestimmten Preises liegen
 * @author manfred
 *
 */
public class ATOM_LAG_Mengenermittlung_old extends Observable{

	// Vector mit den IDs der LAGER_KONTO-Einträge, die in der Selektion vorkommen
	private Vector<String>  vAuswahlListe = null;
	private BigDecimal      m_AktuelleMenge = null;
	private BigDecimal 		m_AktuellerPreis = null;
	
	private String			m_sIDEinheitReferenz = null;
	private String 			m_sEinheit = null;
	
	
	private String m_sqlTablenameAtomSetzkasten			= " JT_BEWEGUNG_SETZKASTEN ";
	private String m_sqlPrimaryKeyColAtomSetzkasten 	= " ID_BEWEGUNG_SETZKASTEN ";
	private String m_sqlColumPreis 						= " A.E_PREIS_RESULT_NETTO_MGE ";

	
	/**
	 * Standard-Konstruktor
	 * initialisiert alle Werte
	 */
	public ATOM_LAG_Mengenermittlung_old(boolean bKostenberuecksichtigung) {
		
		m_sqlTablenameAtomSetzkasten		= bKostenberuecksichtigung ? "JT_BEWEGUNG_SETZKASTEN_K" : "JT_BEWEGUNG_SETZKASTEN";
		m_sqlPrimaryKeyColAtomSetzkasten 	= bKostenberuecksichtigung ? "ID_BEWEGUNG_SETZKASTEN_K" : "ID_BEWEGUNG_SETZKASTEN";
		if (bKostenberuecksichtigung){
			m_sqlColumPreis = " ROUND(((A.E_PREIS_RESULT_NETTO_MGE * (A.MENGE_NETTO/NVL(ART.MENGENDIVISOR,1)) ) + NVL(V.KOSTEN_TRANSPORT_WE,0) + NVL(V.KOSTEN_PRODUKT_WE,0) ) / NVL(A.MENGE_NETTO / NVL(ART.MENGENDIVISOR,1),1),2)  ";
		} else {
			m_sqlColumPreis = " A.E_PREIS_RESULT_NETTO_MGE ";
		}
		
		
		vAuswahlListe = new Vector<String>();
		m_AktuelleMenge = BigDecimal.ZERO;
		m_AktuellerPreis = BigDecimal.ZERO;
	}


	/**
	 * erzeugt das Grundlegende Sql-Statement zur Ermittlung der offenen Mengen
	 * @param IDAdresseLager
	 * @param IDHauptsorte
	 * @param IDSorte
	 * @return
	 */
	private String getSQLStatementFor(String IDAdresseLager, String IDHauptsorte, String IDSorte){
		String sWhereSorte = " ";
		if (!bibALL.isEmpty(IDSorte)){
			sWhereSorte += " AND A.ID_ARTIKEL = " + IDSorte + " ";
		}
		if (!bibALL.isEmpty(IDHauptsorte)){
			sWhereSorte += " AND SUBSTR(ART.ANR1,0,2) = '" +IDHauptsorte.trim() + "' ";
		}

		String sWhereLager = "";
		if (!bibALL.isEmpty(IDAdresseLager)){
			sWhereLager += " AND S.ID_ADRESSE = " + IDAdresseLager;
		}
		
		String sSql = 
				" SELECT "+
				"    A.ID_BEWEGUNG_ATOM  "+
				" ,  A.MENGE_NETTO - nvl((SELECT sum(menge) from " + bibE2.cTO() + "." + m_sqlTablenameAtomSetzkasten + " K WHERE K.ID_ATOM_EINGANG = A.ID_BEWEGUNG_ATOM),0) as MENGE_OFFEN " +
				" ,  EINH.ID_EINHEIT " +
				" ,  EINH.EINHEITKURZ " +
				" , " + m_sqlColumPreis + 
				" , A.LEISTUNGSDATUM " + 
				" FROM  "+bibE2.cTO()+".JT_BEWEGUNG_ATOM  A " +
				" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V  			ON A.ID_BEWEGUNG_VEKTOR = V.ID_BEWEGUNG_VEKTOR " +
				" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART        			ON A.ID_ARTIKEL = ART.ID_ARTIKEL  " +
				" INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT EINH              	ON ART.ID_EINHEIT = EINH.ID_EINHEIT  " +
				" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S        	ON S.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM    AND S.MENGENVORZEICHEN = 1  " +
				" WHERE  " +
				" 	S.ID_ADRESSE IN ( SELECT ID_ADRESSE FROM " + bibE2.cTO() + ".V_FIRMENADRESSEN_FLACH WHERE ID_ADRESSE_BASIS =  " + bibALL.get_ID_ADRESS_MANDANT() + " and V_FIRMENADRESSEN_FLACH.SONDERLAGER is null) " +
				" 	AND NVL(A.DELETED,'N') 		= 'N' " +
				"	AND NVL(A.STORNIERT,'N') 	= 'N' " +
				"	AND NVL(A.MENGE_NETTO,0) != NVL((SELECT SUM(MENGE) FROM " + bibE2.cTO() + "." + m_sqlTablenameAtomSetzkasten + " K WHERE K.ID_ATOM_EINGANG = A.ID_BEWEGUNG_ATOM),0)  " +
				sWhereLager + 
				sWhereSorte ;
		
		return sSql;
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
		
		
		/**
		 * Sql-Statement aufbauen
		 */
		String sSql = getSQLStatementFor(IDAdresseLager, IDHauptsorte, IDSorte);
		sSql += " AND " + m_sqlColumPreis + "  <= " + maxBetrag.toPlainString() ;
		sSql += " ORDER BY 5,2,6";
		
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
					this.addAtomId(IDLagerKonto);
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

		String sSql = getSQLStatementFor(IDAdresseLager, IDHauptsorte, IDSorte);
		sSql += " AND " + m_sqlColumPreis + " is not null ";
		sSql += " ORDER BY 5,2,6";
		
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
				String 		sIDEinheit = 			cLagerDaten[i][2];
				String 		sEinheit	= 			cLagerDaten[i][3];
				String 		sPreis	= 				cLagerDaten[i][4];
				
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
						this.addAtomId(IDLagerKonto);
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
							
							this.addAtomId(IDLagerKonto);
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
		this.ermittleDurchschnittspreisVonMenge(IDAdresseLager, IDHauptsorte, IDSorte, Menge, false );
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
	 * @param bKeineNullPreise - wenn true, werden Einträge mit Preisen mit dem Wert 0 Euro nicht berücksichtigt. 
	 * 
	 */
	public void ermittleDurchschnittspreisVonMenge(String IDAdresseLager, String IDHauptsorte, String IDSorte, BigDecimal Menge , boolean bKeineNullPreise){
		
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
		
		String sSql = getSQLStatementFor(IDAdresseLager, IDHauptsorte, IDSorte);
		
		
		if (bKeineNullPreise){
			sSql += " AND NVL(" + m_sqlColumPreis + ",0) != 0.0 ";
		}
		
		sSql += " AND " + m_sqlColumPreis + " is not null ";
		sSql += " ORDER BY 5,2,6";
		

		try {
			
			String [][] cLagerDaten = new String[0][0];
			cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0){
				return ;
			}
			
			BigDecimal bdMengeTemp = BigDecimal.ZERO;
			
			// sonst den Vektor füllen
			for (int i = 0; i < cLagerDaten.length; i++){
				String 		IDLagerKonto = 			cLagerDaten[i][0];
				String		sMengeOffen = 			cLagerDaten[i][1];
				String 		sIDEinheit = 			cLagerDaten[i][2];
				String 		sEinheit	= 			cLagerDaten[i][3];
				String 		sPreis	= 				cLagerDaten[i][4];
				
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
						this.addAtomId(IDLagerKonto);
						
						bdMengeRet = bdMengeRet.add(bdMenge);
						bdPreisRet = bdPreisRet.add(bdMenge.multiply(bdPreis));
						
					} else {
						// Restmenge:
						BigDecimal bdRestmenge = Menge.subtract(bdMengeRet);
						bdMengeRet = bdMengeRet.add(bdRestmenge);
						bdPreisRet = bdPreisRet.add(bdRestmenge.multiply(bdPreis));
						
						// die Lagerid zur Liste hinzufügen
						this.addAtomId(IDLagerKonto);
						
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
	 * @param idBewegungAtom
	 */
	private void addAtomId(String idBewegungAtom){
		this.vAuswahlListe.add(idBewegungAtom);
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
