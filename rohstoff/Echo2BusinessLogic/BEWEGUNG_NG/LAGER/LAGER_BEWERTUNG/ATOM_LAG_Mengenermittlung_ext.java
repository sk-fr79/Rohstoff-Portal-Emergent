package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;

/**
 * Klasse zur Mengenermittlung von nicht verbuchten WE-Positionen
 * die unterhalb eines bestimmten Preises liegen
 * @author manfred
 *
 */
public class ATOM_LAG_Mengenermittlung_ext extends Observable{

	// Vector mit den IDs der LAGER_KONTO-Einträge, die in der Selektion vorkommen
	private Vector<String>  vAuswahlListe = null;
	private BigDecimal      m_AktuelleMenge = null;
	private BigDecimal 		m_AktuellerPreis = null;
	private BigDecimal     m_AktuellerPreisOhneKosten = null;
	
	private String			m_sIDEinheitReferenz = null;
	private String 			m_sEinheit = null;
	
	private String m_sqlTablenameAtomSetzkasten			= " JT_BEWEGUNG_SETZKASTEN ";
	private String m_sqlPrimaryKeyColAtomSetzkasten 	= " ID_BEWEGUNG_SETZKASTEN ";
	private String m_sqlColumPreis 						= " A.E_PREIS_RESULT_NETTO_MGE ";

	private ATOM_LAG_BEW_StatusErmittlung_Ext  m_oStatus = null;
	
	
	/**
	 * Standard-Konstruktor
	 * initialisiert alle Werte
	 */
	public ATOM_LAG_Mengenermittlung_ext(boolean bKostenberuecksichtigung) {
		
		// das Objekt für die Datenerhebung
		m_oStatus = new ATOM_LAG_BEW_StatusErmittlung_Ext(bKostenberuecksichtigung);
		
		vAuswahlListe = new Vector<String>();
		m_AktuelleMenge = BigDecimal.ZERO;
		m_AktuellerPreis = BigDecimal.ZERO;
		m_AktuellerPreisOhneKosten = BigDecimal.ZERO;
	}

	
	
	/**
	 * Initialisiert die Mengenermittlung
	 * @param IDAdresseLager
	 * @param IDHauptsorte
	 * @param IDSorte
	 * @param Buchungsdatum
	 *
	 * Autor:	 manfred
	 * Erstellt: 07.05.2014
	 *
	 */
	public void initMengenermittlung(String IDAdresseLager, String IDHauptsorte, String IDSorte,String Buchungsdatum){
		m_oStatus.ErmittleLagerstatusFuerMengenermittlung(IDAdresseLager, IDHauptsorte, IDSorte, Buchungsdatum);
	}


	/**
	 * Initialisiert die Mengenermittlung mit Mehrfachauswahl von Lager, Hauptsorte und Sorte
	 * @param IDAdresseLager
	 * @param vIDAdresseLager
	 * @param IDHauptsorte
	 * @param vIDHauptsorte
	 * @param IDSorte
	 * @param vIDSorte
	 * @param Buchungsdatum
	 */
	public void initMengenermittlung(
			String IDAdresseLager, 
			Vector<String> vIDAdresseLager, 
			String IDHauptsorte, 
			Vector<String> vIDHauptsorte, 
			String IDSorte,
			Vector<String> vIDSorte,
			String Buchungsdatum){
		m_oStatus.ErmittleLagerstatusFuerMengenermittlung(IDAdresseLager, vIDAdresseLager, IDHauptsorte, vIDHauptsorte, IDSorte, vIDSorte, Buchungsdatum);
	}
	
	
	
	/**
	 * Ermittelt die Liste der aktuellen Läger IDs und summiert die Mengen zur Abfrage
	 * Ergebnis muss abgefragt werden mit 
	 * 		- getErmittelteMenge()
	 * 		- getErmittelterPreis()
	 * 		- getMengeneinheit()
	 * 
	 * @param maxBetrag - der Maximalbetrag (kleiner gleich) 
	 */
	public void ermittleMengeZuMaximalpreis( BigDecimal maxBetrag ) {
		
		// löschen der aktuellen Daten
		this.clearAuswahlListe();
		this.m_AktuelleMenge 				= BigDecimal.ZERO;
		this.m_AktuellerPreis 				= BigDecimal.ZERO;
		this.m_AktuellerPreisOhneKosten 	= BigDecimal.ZERO;
		
		BigDecimal bdRet = new BigDecimal(0);
		
		Vector<ATOM_LAG_BEW_DataRowStatus> oVec = m_oStatus.getResultList();
		
		if (oVec.size() == 0) return;
		
		
		// sonst den Vektor füllen
		for (int i = 0; i < oVec.size(); i++){
			ATOM_LAG_BEW_DataRowStatus row = oVec.get(i);
			
			String 		IDAtom 			= row.getIdBewegungAtom();
			BigDecimal	bdMengeOffen 	= row.getMenge_Gesamt();
			String 		sIDEinheit		= row.getIdEinheit();
			String 		sEinheit		= row.getEinheitKurz();
			BigDecimal  bdPreis			= row.getAvg_Restwert_gesamt();
			
			
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
			
			
			if (bdPreis != null && bdPreis.compareTo(maxBetrag) <= 0 ){
				// die Lagerid zur Liste hinzufügen
				this.addAtomId(IDAtom);
				// die offene Menge summieren
				BigDecimal bdMenge = bdMengeOffen;
				bdMenge = bdMenge.setScale(3, BigDecimal.ROUND_HALF_UP);
				bdRet = bdRet.add(bdMenge);
			}
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
	 * @param avgBetrag - der Durchschnittsbetrag (kleiner gleich) 
	 */
	public void ermittleMengeZuDurchschnittspreis(BigDecimal avgBetrag ){
		
		// löschen der aktuellen Daten
		this.clearAuswahlListe();
		this.m_AktuelleMenge = BigDecimal.ZERO;
		this.m_AktuellerPreis = BigDecimal.ZERO;
		this.m_AktuellerPreisOhneKosten = BigDecimal.ZERO;
		
		BigDecimal bdAvgPreis = BigDecimal.ZERO;
		BigDecimal bdAvgPreisOld = BigDecimal.ZERO;
		BigDecimal bdMengeRet = new BigDecimal(0);
		
		
		Vector<ATOM_LAG_BEW_DataRowStatus> oVec = m_oStatus.getResultList();
		if (oVec.size() == 0) return;

		BigDecimal bdMengeTemp = BigDecimal.ZERO;
		BigDecimal bdPreisTemp = BigDecimal.ZERO;
			
		// sonst den Vektor füllen
		for (int i = 0; i < oVec.size(); i++){
			ATOM_LAG_BEW_DataRowStatus row = oVec.get(i);
			
			String 		IDAtom 			= row.getIdBewegungAtom();
			BigDecimal	bdMengeOffen 	= row.getMenge_Gesamt();
			String 		sIDEinheit		= row.getIdEinheit();
			String 		sEinheit		= row.getEinheitKurz();
			BigDecimal  bdPreis			= row.getAvg_Restwert_gesamt();
			
			if (bdPreis == null){
				continue;
			}
			
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

			
			
			if (bdMengeOffen != null && bdMengeOffen.compareTo(BigDecimal.ZERO)!= 0){
				
				// die offene Menge summieren
				BigDecimal bdMenge = bdMengeOffen;
				bdMenge = bdMenge.setScale(3, BigDecimal.ROUND_HALF_UP);
				
				// Gesamtmenge
				bdMengeTemp = bdMengeTemp.add(bdMenge);
				
				// Gesamtpreis 
				bdPreisTemp = bdPreisTemp.add(bdMenge.multiply(bdPreis));
				
				// aktueller Durchschnittspreis
				bdAvgPreis = bdPreisTemp.divide(bdMengeTemp,3,BigDecimal.ROUND_HALF_UP);
				
				if (bdAvgPreis.compareTo(avgBetrag) <= 0){
					// die Lagerid zur Liste hinzufügen
					this.addAtomId(IDAtom);
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
						if (bdM_haufen.compareTo(BigDecimal.ZERO) != 0){
							this.addAtomId(IDAtom);
						}
					}
					// exit for-Schleife
					break;
				}
			}
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
	public void ermittleDurchschnittspreisVonMenge( BigDecimal Menge ){
		this.ermittleDurchschnittspreisVonMenge( Menge, false );
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
	public void ermittleDurchschnittspreisVonMenge( BigDecimal Menge , boolean bKeineNullPreise){
		
		// löschen der aktuellen Daten
		this.clearAuswahlListe();
		this.m_AktuelleMenge = BigDecimal.ZERO;
		this.m_AktuellerPreis = null;
		this.m_AktuellerPreisOhneKosten = null;
		
		// es wird der Gesamtdurchschnitt berechnet, wenn keine Menge angegeben ist.
		boolean bGesamtdurchschnitt = ( Menge == null || Menge.compareTo(BigDecimal.ZERO) < 0 );
		
		BigDecimal bdMengeRet = new BigDecimal(0);
		BigDecimal bdPreisRet = new BigDecimal(0);
		BigDecimal bdPreisRetOhne = new BigDecimal(0);
		
		
		Vector<ATOM_LAG_BEW_DataRowStatus> oVec = m_oStatus.getResultList();
		if (oVec.size() == 0) return;


			
		BigDecimal bdMengeTemp = BigDecimal.ZERO;
		
		// sonst den Vektor füllen
		for (int i = 0; i < oVec.size(); i++){
			
			ATOM_LAG_BEW_DataRowStatus row = oVec.get(i);
			
			String 		IDAtom 			= row.getIdBewegungAtom();
			BigDecimal	bdMengeOffen 	= row.getMenge_Gesamt();
			String 		sIDEinheit		= row.getIdEinheit();
			String 		sEinheit		= row.getEinheitKurz();
			BigDecimal  bdPreis			= row.getAvg_Restwert_gesamt();
			BigDecimal  bdPreisOhne		= row.getAvg_Restwert_gesamt_Ohne_Kosten();
			
			
			// die offene Menge summieren
			BigDecimal bdMenge = bdMengeOffen;
			bdMenge = bdMenge.setScale(3, BigDecimal.ROUND_HALF_UP);

			// einträge ohne Preise können nicht berücksichtigt werden
			if (bdPreis == null){
				continue;
			}

			// Preise mit dem Wert 0 Euro können ausgeblendet werden
			if (bKeineNullPreise){
				if(bdPreis.compareTo(BigDecimal.ZERO) == 0){
					continue;
				}
			}
			
			
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
				bdPreisRetOhne = bdPreisRetOhne.add(bdMenge.multiply(bdPreisOhne));
			}
			else
			{ // nur solange verarbeiten, bis die Mengen erreicht wurde
				
				// Gesamtmenge prüfen, ob kleiner der gewünschten Menge
				bdMengeTemp = bdMengeTemp.add(bdMenge);
				
				if (bdMengeTemp.compareTo(Menge) <= 0){
					// die Lagerid zur Liste hinzufügen
					this.addAtomId(IDAtom);
					
					bdMengeRet = bdMengeRet.add(bdMenge);
					bdPreisRet = bdPreisRet.add(bdMenge.multiply(bdPreis));
					bdPreisRetOhne = bdPreisRetOhne.add(bdMenge.multiply(bdPreisOhne));
					
					
					
				} else {
					// Restmenge:
					BigDecimal bdRestmenge = Menge.subtract(bdMengeRet);
					bdMengeRet = bdMengeRet.add(bdRestmenge);
					bdPreisRet = bdPreisRet.add(bdRestmenge.multiply(bdPreis));
					bdPreisRetOhne = bdPreisRetOhne.add(bdRestmenge.multiply(bdPreisOhne));
					
					// die Lagerid zur Liste hinzufügen
					this.addAtomId(IDAtom);
					
					// exit for-Schleife
					break;
				}
			}
		}
		
		
		// berechnen des Durchschnittspreises für die Rückgabe:
		if (bdMengeRet.compareTo(BigDecimal.ZERO)!=0){
			m_AktuellerPreis = bdPreisRet.divide(bdMengeRet, 2, BigDecimal.ROUND_HALF_UP);
			m_AktuellerPreisOhneKosten = bdPreisRetOhne.divide(bdMengeRet,2,BigDecimal.ROUND_HALF_UP);
		} else {
			m_AktuellerPreis = null;
			m_AktuellerPreisOhneKosten = null;
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
	
	public BigDecimal get_ermittelterPreisOhneKosten(){
		return m_AktuellerPreisOhneKosten;
	}
	
	
	/**
	 * Gibt die ermittelte Mengeneinheit der aktuell berechnenten Sorten zurück!
	 * @return
	 */
	public String get_Mengeneinheit(){
		return m_sEinheit;
	}
	
	

	
}
