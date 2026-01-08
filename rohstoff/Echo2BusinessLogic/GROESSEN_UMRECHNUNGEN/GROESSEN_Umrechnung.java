package rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EINHEIT_FAKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT_FAKTOR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class GROESSEN_Umrechnung {

	
	HashMap<String, cUmrechnungsfaktor> hmUmrechnungsfaktoren = null;
	HashMap<String, cArtikelumrechnung> hmArtikel = null;
	
	/**
	 * Standard-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   16.07.2012
	 */
	public GROESSEN_Umrechnung() throws myException {
		// lesen der JT_EINHEITEN_FAKTOR
		initUmrechnungsfaktoren();
		
		// lesen der JT_ARTIKEL und ermitteln der Faktoren
		initArtikelUmrechnung();
		
	}


	
	/**
	 * erreichnet aus dem Einzelpreis den Gesamtpreis unter berücksichtigung der Umrechnungsfaktoren
	 * @author manfred
	 * @date   16.07.2012
	 * @param idArtikel
	 * @param dbMenge
	 * @param bdEinzelpreis
	 * @return
	 * @throws myException 
	 */
	public BigDecimal BerechneGesamtpreis( String idArtikel, BigDecimal bdMenge, BigDecimal bdEinzelpreis ) throws myException{
		BigDecimal bdRet = null;
		
		cArtikelumrechnung oArtikel = hmArtikel.get(idArtikel);
		
		GewichteterWert preis = new GewichteterWert(bdEinzelpreis,oArtikel.get_FaktorPreis());
		GewichteterWert menge = new GewichteterWert(bdMenge,oArtikel.get_FaktorMenge());
		
		BerechnungPreis rech = new BerechnungPreis();
		bdRet = rech.berechneGesamtpreis(preis, menge);

		return bdRet;
	}
	
	
	/**
	 * Rechnet den Einzelpreis in Euro/Gewichtseinheit (Euro/t -> Euro/kg) von ArtikelAlt in einen neuen Einzelpreis von ArtikelNeu um
	 * -> neuer Wert nur, wenn andere Preiseinheiten vorliegen. 
	 * -> Wenn die Preis-Faktoren gleich sind, ist der Preis auch gleich
	 * @author manfred
	 * @date   16.07.2012
	 * @param idArtikelAlt
	 * @param idArtikelNeu
	 * @param Preis
	 * @return
	 * @throws myException 
	 */
	public BigDecimal BerechneEinzelpreisNeuerArtikel( String idArtikelAlt, BigDecimal EinzelpreisAlt, String idArtikelNeu) throws myException{
		BigDecimal bdRet = null;
		
		cArtikelumrechnung oArtikelAlt = hmArtikel.get(idArtikelAlt);
		cArtikelumrechnung oArtikelNeu = hmArtikel.get(idArtikelNeu);
		
		if (oArtikelAlt.get_FaktorPreis().equals(oArtikelNeu.get_FaktorPreis())){
			// wenn die beiden Preisfaktoren gleich sind, dann sind die Einzelpreise gleich
			return EinzelpreisAlt;
		}
		
		
		GewichteterWert PreisAlt = new GewichteterWert(EinzelpreisAlt,oArtikelAlt.get_FaktorPreis());
		BigDecimal      bdFaktorNeu = oArtikelNeu.get_FaktorPreis();
		
		BerechnungPreis rech = new BerechnungPreis();
		GewichteterWert PreisNeu = rech.berechnePreisMitGewichtung(PreisAlt, bdFaktorNeu);
		return PreisNeu.getWert();
	}

	
	/**
	 * Rechnet den Einzelpreis in Euro/Gewichtseinheit (Euro/t -> Euro/kg) von ArtikelAlt in einen neuen Einzelpreis von ArtikelNeu um
	 * -> neuer Wert nur, wenn andere Preiseinheiten vorliegen. 
	 * -> Wenn die Preis-Faktoren gleich sind, ist der Preis auch gleich
	 * @author manfred
	 * @date   16.07.2012
	 * @param idArtikelAlt
	 * @param idArtikelNeu
	 * @param Preis
	 * @return
	 * @throws myException 
	 */
	public BigDecimal BerechneMengeNeuerArtikel( String idArtikelAlt, BigDecimal MengeAlt, String idArtikelNeu) throws myException{
		BigDecimal bdRet = null;
		
		cArtikelumrechnung oArtikelAlt = hmArtikel.get(idArtikelAlt);
		cArtikelumrechnung oArtikelNeu = hmArtikel.get(idArtikelNeu);
		
		if (oArtikelAlt.get_FaktorMenge().equals(oArtikelNeu.get_FaktorMenge())){
			// wenn die beiden Preisfaktoren gleich sind, dann sind die Einzelpreise gleich
			return MengeAlt;
		}
		
		
		GewichteterWert gwMengeAlt = new GewichteterWert(MengeAlt,oArtikelAlt.get_FaktorMenge());
		
		BerechnungGewicht rech = new BerechnungGewicht();
		GewichteterWert GewichtNeu = rech.convertGewicht(gwMengeAlt, oArtikelNeu.get_FaktorMenge());
		return GewichtNeu.getWert();
	}

	
	/**
	 * Rechnet einen Einzelpreis mit einem bestimmten Preisfaktor um auf einen neuen Artikel-Einzelpreis mit einem neuen Preisfaktor
	 * @author manfred
	 * @date   16.07.2012
	 * @param EinzelpreisAlt
	 * @param FaktorAlt
	 * @param idArtikelNeu
	 * @return
	 * @throws myException 
	 */
	public BigDecimal BerechneEinzelpreisNeuerArtikel( BigDecimal EinzelpreisAlt, BigDecimal FaktorAlt, String idArtikelNeu) throws myException{
		BigDecimal bdRet = null;
		
		
		cArtikelumrechnung oArtikelNeu = hmArtikel.get(idArtikelNeu);
		
		if (FaktorAlt.equals(oArtikelNeu.get_FaktorPreis())){
			// wenn die beiden Preisfaktoren gleich sind, dann sind die Einzelpreise gleich
			return EinzelpreisAlt;
		}
		
		
		GewichteterWert PreisAlt = new GewichteterWert(EinzelpreisAlt,FaktorAlt);
		BigDecimal      bdFaktorNeu = oArtikelNeu.get_FaktorPreis();
		
		BerechnungPreis rech = new BerechnungPreis();
		GewichteterWert PreisNeu = rech.berechnePreisMitGewichtung(PreisAlt, bdFaktorNeu);
		return PreisNeu.getWert();
	}

	
	
	
	
	
	

	/**
	 * lesen und puffern aller Umrechnungsfaktoren
	 * @author manfred
	 * @date   16.07.2012
	 * @throws myException
	 */
	private void initUmrechnungsfaktoren() throws myException {
		if (hmUmrechnungsfaktoren == null){

			// alle Einheitsfaktoren ermitteln und in Hashtable cachen
			RECLIST_EINHEIT_FAKTOR rl_EINHEIT_FAKTOR = new RECLIST_EINHEIT_FAKTOR("","");
			hmUmrechnungsfaktoren = new HashMap<String, GROESSEN_Umrechnung.cUmrechnungsfaktor>();
			for (RECORD_EINHEIT_FAKTOR rec: rl_EINHEIT_FAKTOR.values()){
				cUmrechnungsfaktor o = new cUmrechnungsfaktor(rec.get_ID_EINHEIT_cUF(), rec.get_FAKTOR_bdValue(BigDecimal.ONE));
				hmUmrechnungsfaktoren.put(rec.get_ID_EINHEIT_cUF(), o);
			}
			
		}
	}





	/**
	 * lesen und puffern aller Artikel und deren Umrechnungsfaktoren
	 * @author manfred
	 * @date   16.07.2012
	 */
	private void initArtikelUmrechnung() {

		if (hmArtikel == null){
			
			hmArtikel = new HashMap<String, GROESSEN_Umrechnung.cArtikelumrechnung>();
			
			String[][] arArtikel = bibDB.EinzelAbfrageInArray("select A.ID_ARTIKEL, A.ID_EINHEIT, A.ID_EINHEIT_PREIS from  "+bibE2.cTO()+".JT_ARTIKEL A ");
			int len = arArtikel.length;
			
			String sIDArtikel 		= null;
			String sIDEinheit 		= null;
			String sIDEinheitPreis 	= null;
			
			cUmrechnungsfaktor oFaktorMenge = null;
			cUmrechnungsfaktor oFaktorPreis = null;
			
			for (int i=0;i < len;i++)
			{
				sIDArtikel 		= arArtikel[i][0];
				sIDEinheit 		= arArtikel[i][1];
				sIDEinheitPreis 	= arArtikel[i][2];
				
				oFaktorMenge = hmUmrechnungsfaktoren.get(sIDEinheit);
				oFaktorPreis = hmUmrechnungsfaktoren.get(sIDEinheitPreis);
				
				try {
					cArtikelumrechnung o = new cArtikelumrechnung(
							sIDArtikel, 
							sIDEinheit, 
							oFaktorMenge.getFaktor(),
							sIDEinheitPreis, 
							oFaktorPreis.getFaktor());

					hmArtikel.put(sIDArtikel, o);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}




	

	/**
	 * Klasse hält die Umrechnungsfaktoren für einen Artikel
	 * @author manfred
	 * @date   16.07.2012
	 */
	private class cArtikelumrechnung {
		String 				m_IDArtikel = null;

		String				m_IDEinheitMenge = null;
		BigDecimal			m_FaktorMenge = null;
		
		String 				m_IDEinheitPreis = null;
		BigDecimal			m_FaktorPreis = null;
		
		public cArtikelumrechnung(String sIDArtikel, String idEinheitMenge, BigDecimal FaktorMenge,
													 String idEinheitPreis, BigDecimal FaktorPreis){
			m_IDArtikel = sIDArtikel;
			m_IDEinheitMenge = idEinheitMenge;
			m_IDEinheitPreis = idEinheitPreis;
			m_FaktorMenge = FaktorMenge;
			m_FaktorPreis = FaktorPreis;
			
		}

		/**
		 * @return the m_IDArtikel
		 */
		public String get_IDArtikel() {
			return m_IDArtikel;
		}

		/**
		 * @param m_IDArtikel the m_IDArtikel to set
		 */
		public void set_IDArtikel(String m_IDArtikel) {
			this.m_IDArtikel = m_IDArtikel;
		}

		/**
		 * @return the m_IDEinheitMenge
		 */
		public String get_IDEinheitMenge() {
			return m_IDEinheitMenge;
		}

		/**
		 * @param m_IDEinheitMenge the m_IDEinheitMenge to set
		 */
		public void set_IDEinheitMenge(String m_IDEinheitMenge) {
			this.m_IDEinheitMenge = m_IDEinheitMenge;
		}

		/**
		 * @return the m_FaktorMenge
		 */
		public BigDecimal get_FaktorMenge() {
			return m_FaktorMenge;
		}

		/**
		 * @param m_FaktorMenge the m_FaktorMenge to set
		 */
		public void set_FaktorMenge(BigDecimal m_FaktorMenge) {
			this.m_FaktorMenge = m_FaktorMenge;
		}

		/**
		 * @return the m_IDEinheitPreis
		 */
		public String get_IDEinheitPreis() {
			return m_IDEinheitPreis;
		}

		/**
		 * @param m_IDEinheitPreis the m_IDEinheitPreis to set
		 */
		public void set_IDEinheitPreis(String m_IDEinheitPreis) {
			this.m_IDEinheitPreis = m_IDEinheitPreis;
		}

		/**
		 * @return the m_FaktorPreis
		 */
		public BigDecimal get_FaktorPreis() {
			return m_FaktorPreis;
		}

		/**
		 * @param m_FaktorPreis the m_FaktorPreis to set
		 */
		public void set_FaktorPreis(BigDecimal m_FaktorPreis) {
			this.m_FaktorPreis = m_FaktorPreis;
		}

		
		
		
		
	}
	
	
	
	
	
	/**
	 * Private Klasse zum halten der Umrechnungsfaktoren
	 * @author manfred
	 * @date   16.07.2012
	 */
	private class cUmrechnungsfaktor{
		String 			sIDEinheit = null;
		BigDecimal 		bdFaktor = null;
		
		cUmrechnungsfaktor(String IDEinheit, BigDecimal Faktor){
			sIDEinheit = IDEinheit;
			bdFaktor = Faktor;
		}

		/**
		 * @return the sIDEinheit
		 */
		public String getIDEinheit() {
			return sIDEinheit;
		}

		/**
		 * @param sIDEinheit the sIDEinheit to set
		 */
		public void setIDEinheit(String sIDEinheit) {
			this.sIDEinheit = sIDEinheit;
		}

		/**
		 * @return the bdFaktor
		 */
		public BigDecimal getFaktor() {
			return bdFaktor;
		}

		/**
		 * @param bdFaktor the bdFaktor to set
		 */
		public void setFaktor(BigDecimal bdFaktor) {
			this.bdFaktor = bdFaktor;
		}
		
		
		
	}

	
}
