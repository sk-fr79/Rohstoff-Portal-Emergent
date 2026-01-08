package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;
import java.util.Vector;

/**
 * Klasse bildet Eintrag ab,
 *  der zum Sammeln von Fibu-Daten für die Zeile in DataRowStatus dient.
 *  
 * Jeder eintrag STATKD_DataRowStatus bekommt zwei Vectoren mit STATKD_DataRowFuhreEntry-Einträgen
 * - für die gefahrenen Fuhren
 * - für die geplanten Fuhren
 * 
 * @author manfred
 *
 */
public class STATKD_DataRowFibu_Buchungsblock {
	
	private Vector<STATKD_DataRowFibu_RechPos>  m_vRechPos_Mit_Steuer 	= null;
	private Vector<STATKD_DataRowFibu_RechPos>  m_vRechPos_Ohne_Steuer 	= null;
	
	private Vector<STATKD_DataRowFibu_RechPos>  m_vRechPos_Zahlung  	= null;
	private BigDecimal							m_bdZahlung = new BigDecimal(0);
	
	private boolean 							m_bIstForderung = true;
	
	
	/**
	 * Initialisiert einen Buchungsblock je nach Blocktyp Forderung/Verbindlichkeit
	 * @author manfred
	 * @date   10.04.2013
	 * @param bIstForderung
	 */
	public STATKD_DataRowFibu_Buchungsblock(boolean bIstForderung){
		m_vRechPos_Mit_Steuer 	= new Vector<STATKD_DataRowFibu_RechPos>();
		m_vRechPos_Ohne_Steuer 	= new Vector<STATKD_DataRowFibu_RechPos>();
		m_vRechPos_Zahlung  	= new Vector<STATKD_DataRowFibu_RechPos>();
		m_bIstForderung = bIstForderung;
		
	}

	/**
	 * Fügt eine Rechnungsposition ein und unterscheidet die einzelnen Positionstypen: 
	 * Mit Steuer
	 * Ohne Steuer
	 * - Zahlungsverkehr (Steuer == null)
	 * 
	 * die zeitliche Reihenfolge muss schon beim einfügen beachtet werden. Die Einträge werden bei der Bestimmung des
	 * restlichen Nettowertes wie eingetragen bearbeitet
	 * 
	 * @author manfred
	 * @date   10.04.2013
	 * @param oRechpos
	 */
	public void addDataRowFibu_RechPos ( STATKD_DataRowFibu_RechPos oRechpos){
		if (oRechpos.get_Steuersatz() == null){
			m_vRechPos_Zahlung.add(oRechpos);
			// gleich die Zahlungen Summieren
			m_bdZahlung = m_bdZahlung.add(oRechpos.get_PosPreisBrutto());
		} else if ( oRechpos.get_Steuersatz().compareTo(BigDecimal.ZERO) > 0 ){
			m_vRechPos_Mit_Steuer.add(oRechpos);
		} else {
			m_vRechPos_Ohne_Steuer.add(oRechpos);
		}
	}
	
	
	
	/**
	 * nähert bzw. ermittelt den aktuellen NETTO-Betrag des Buchungsblocks
	 * 
	 * Es werden alle Zahlungsvorgänge gegen die vorhandenen Brutto-Beträge gegengerechnet, bis kein Zahlungsvorgang mehr vorhanden ist.
	 * Danach werden alle Beträge als Netto-Beträge aufsummiert.
	 *  
	 * Somit hat man als Näherung die Netto-Summe des Buchungsblocks
	 * 
	 * @author manfred
	 * @date   10.04.2013
	 * @return
	 */
	public BigDecimal getBuchungsblockBetrag(){
		if (m_bIstForderung){
			return getBuchungsblockBetragForderung();
		} else {
			return getBuchungsblockBetragVerbindlichkeit();
		}
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date   10.04.2013
	 * @return
	 */
	private BigDecimal getBuchungsblockBetragForderung(){
		BigDecimal bdReturn = new BigDecimal(0);
		
		BigDecimal bdRestZahlung = m_bdZahlung;
		
		// die Steuer beaufschlagten Werte gegenrechnen
		for (STATKD_DataRowFibu_RechPos oRechPos: m_vRechPos_Mit_Steuer){
			if ( bdRestZahlung.compareTo(BigDecimal.ZERO) == 0){
				
				// es gibt keine Restzahlung mehr, den Nettowert addieren
				bdReturn = bdReturn.add(oRechPos.get_PosPreisNetto());
				
			} else if ( bdRestZahlung.add( oRechPos.get_PosPreisBrutto() ).compareTo(BigDecimal.ZERO) <= 0 )	{
				
				// der Brutto-Betrag passt in die Zahlung komplett rein
				bdRestZahlung = bdRestZahlung.add(oRechPos.get_PosPreisBrutto());

			} else if ( bdRestZahlung.add(oRechPos.get_PosPreisBrutto() ).compareTo(BigDecimal.ZERO ) > 0 ){
			
				// wenn es noch eine Restzahlung gibt, dann berechnen...
				if (bdRestZahlung.compareTo(BigDecimal.ZERO) != 0){
		
					// die Zahlung vom Brutto-Preis abziehen und vom Rest dann den Nettowert zurückrechnen
					BigDecimal bdRestBrutto = bdRestZahlung.add(oRechPos.get_PosPreisBrutto());
					BigDecimal bdRestNetto = bdRestBrutto.divide( 
							(BigDecimal.ONE.add( oRechPos.get_Steuersatz().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP) ) )
							,2
							,BigDecimal.ROUND_HALF_UP) ;
					
					bdReturn = bdReturn.add(bdRestNetto);
					bdRestZahlung = BigDecimal.ZERO;
					
				} 
			}
		}

		
		
		// dann die ohne Steuer beaufschlagten Werte gegenrechnen
		for (STATKD_DataRowFibu_RechPos oRechPos: m_vRechPos_Ohne_Steuer){
			if ( bdRestZahlung.compareTo(BigDecimal.ZERO) == 0){
				// es gibt keine Restzahlung mehr...
				bdReturn = bdReturn.add(oRechPos.get_PosPreisNetto());
			} else if ( bdRestZahlung.add( oRechPos.get_PosPreisNetto() ).compareTo(BigDecimal.ZERO) <= 0 )	{
				// der Brutto-Betrag passt in die Zahlung komplett rein
				bdRestZahlung = bdRestZahlung.add(oRechPos.get_PosPreisNetto());

			} else if ( bdRestZahlung.add(oRechPos.get_PosPreisNetto() ).compareTo(BigDecimal.ZERO ) > 0 ){
			
				// wenn es noch eine Restzahlung gibt, dann berechnen...
				if (bdRestZahlung.compareTo(BigDecimal.ZERO) != 0){
					// die Zahlung vom Brutto-Preis abziehen und vom Rest dann den Nettowert zurückrechnen
					BigDecimal bdRestNetto = bdRestZahlung.add(oRechPos.get_PosPreisNetto() );
					
					bdReturn = bdReturn.add(bdRestNetto);
					bdRestZahlung = BigDecimal.ZERO;
				} 
			}
		}
		
		// zum Schluss noch den möglichen Rest der Zahlung auf den Rückgabewert addieren
		bdReturn = bdReturn.add(bdRestZahlung);
		
		// der aktuelle, ohne Steuer beaufschlagte Rest im Buchungsblock
		return bdReturn;
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date   10.04.2013
	 * @return
	 */
	private BigDecimal getBuchungsblockBetragVerbindlichkeit(){
		BigDecimal bdReturn = new BigDecimal(0);
		
		BigDecimal bdRestZahlung = m_bdZahlung;
		
		// die Steuer beaufschlagten Werte gegenrechnen
		for (STATKD_DataRowFibu_RechPos oRechPos: m_vRechPos_Mit_Steuer){
			if ( bdRestZahlung.compareTo(BigDecimal.ZERO) == 0){
				
				// es gibt keine Restzahlung mehr, den Nettowert addieren
				bdReturn = bdReturn.add(oRechPos.get_PosPreisNetto());
				
			} else if ( bdRestZahlung.add( oRechPos.get_PosPreisBrutto() ).compareTo(BigDecimal.ZERO) <= 0 )	{
				
				// der Brutto-Betrag passt in die Zahlung komplett rein
				bdRestZahlung = bdRestZahlung.add(oRechPos.get_PosPreisBrutto());

			} else if ( bdRestZahlung.add(oRechPos.get_PosPreisBrutto() ).compareTo(BigDecimal.ZERO ) > 0 ){
			
				// wenn es noch eine Restzahlung gibt, dann berechnen...
				if (bdRestZahlung.compareTo(BigDecimal.ZERO) != 0){
		
					// die Zahlung vom Brutto-Preis abziehen und vom Rest dann den Nettowert zurückrechnen
					BigDecimal bdRestBrutto = bdRestZahlung.add(oRechPos.get_PosPreisBrutto());
					BigDecimal bdRestNetto = bdRestBrutto.divide( 
							(BigDecimal.ONE.add( oRechPos.get_Steuersatz().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP) ) )
							,2
							,BigDecimal.ROUND_HALF_UP) ;
					
					bdReturn = bdReturn.add(bdRestNetto);
					bdRestZahlung = BigDecimal.ZERO;
					
				} 
			}
		}

		
		
		// dann die ohne Steuer beaufschlagten Werte gegenrechnen
		for (STATKD_DataRowFibu_RechPos oRechPos: m_vRechPos_Ohne_Steuer){
			if ( bdRestZahlung.compareTo(BigDecimal.ZERO) == 0){
				// es gibt keine Restzahlung mehr...
				bdReturn = bdReturn.add(oRechPos.get_PosPreisNetto());
			} else if ( bdRestZahlung.add( oRechPos.get_PosPreisNetto() ).compareTo(BigDecimal.ZERO) <= 0 )	{
				// der Brutto-Betrag passt in die Zahlung komplett rein
				bdRestZahlung = bdRestZahlung.add(oRechPos.get_PosPreisNetto());

			} else if ( bdRestZahlung.add(oRechPos.get_PosPreisNetto() ).compareTo(BigDecimal.ZERO ) > 0 ){
			
				// wenn es noch eine Restzahlung gibt, dann berechnen...
				if (bdRestZahlung.compareTo(BigDecimal.ZERO) != 0){
					// die Zahlung vom Brutto-Preis abziehen und vom Rest dann den Nettowert zurückrechnen
					BigDecimal bdRestNetto = bdRestZahlung.add(oRechPos.get_PosPreisNetto() );
					
					bdReturn = bdReturn.add(bdRestNetto);
					bdRestZahlung = BigDecimal.ZERO;
				} 
			}
		}
		
		// zum Schluss noch den möglichen Rest der Zahlung auf den Rückgabewert addieren
		bdReturn = bdReturn.add(bdRestZahlung);
		
		// der aktuelle, ohne Steuer beaufschlagte Rest im Buchungsblock
		return bdReturn;
	}

	
	
}
