package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public abstract class BL_FUHREN_Transformation {

	int m_typ_strecke_lager_mixed = -1;
	
	RECORD_VPOS_TPA_FUHRE 		m_oFuhre = null;
	RECLIST_VPOS_TPA_FUHRE_ORT  m_rlOrt = null;
	
	int fuhre_lager_links = 0;
	int fuhre_lager_rechts = 0;
	int fuhre_kunde_links = 0;
	int fuhre_kunde_rechts = 0;
	
	int lager_links = 0;
	int lager_rechts = 0;
	int kunde_links = 0;
	int kunde_rechts = 0;
	
	int sum_links = 0;
	int sum_rechts = 0;
	

	Vector<String> m_vLagerOrte = null;
	
	// der Hauptsatz
	JtBewegung    m_Bewegung = null;

	// Objekt zur Größenumrechnung
	GROESSEN_Umrechnung m_oUmrechnung = null;
	
	HashMap<String, String> m_hmArtikeBezZuordnung = null;
	Vector<String> 			m_vLeergutartikel = null;
	
	// eigene Mandanten-Adresse
	Long m_IDAdresseMandant = null;
	Long m_IDAdresseBesitzerStart = null;
	Long m_IDAdresseBesitzerZiel = null;
	
	HashMap<String, String> m_hmLieferbedingungen = null;

	
	/**
	 * Default-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   08.08.2012
	 */
	public BL_FUHREN_Transformation(Vector<String>Lagerorte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel , HashMap<String, String> hmLieferbedingungen) throws myException {
		super();
		m_vLagerOrte = Lagerorte;
		m_oUmrechnung = objUmrechnung;
		m_hmArtikeBezZuordnung = hmArtikeBezZuordnung;
		m_vLeergutartikel = vLeergutartikel;
		
		// die eigene AdressID ermitteln
		m_IDAdresseMandant = Long.parseLong( bibALL.get_ID_ADRESS_MANDANT() );
		
		// die Lieferbedingungen
		m_hmLieferbedingungen = hmLieferbedingungen;
	}

	
	
	
	/**
	 * ermittelt die Artikelid aus der Artikelbez-ID unter zuhilfenahme des internen Caches
	 * @author manfred
	 * @date   21.08.2012
	 * @param sIDArtikelBez
	 * @return
	 */
	protected Long getArtikelIDFromArtikelBezID(String sIDArtikelBez){
		String sIDArtikel = null;
		if (sIDArtikelBez == null || sIDArtikelBez.equals("-1")) return null;
		
		
		if (m_hmArtikeBezZuordnung.containsKey(sIDArtikelBez)){
			sIDArtikel = m_hmArtikeBezZuordnung.get(sIDArtikelBez);
		} else {
			RECORD_ARTIKEL_BEZ oArtBez = null;
			try {
				oArtBez = new RECORD_ARTIKEL_BEZ(sIDArtikelBez);
				sIDArtikel = oArtBez.get_ID_ARTIKEL_cUF();
				m_hmArtikeBezZuordnung.put(sIDArtikelBez, sIDArtikel);
			} catch (myException e) {
				e.printStackTrace();
				
			}
		}
		if (sIDArtikel != null){
			return new Long(sIDArtikel);
		} else 
			return null;
	}
	
	
	
	
	public abstract JtBewegung transformiereFuhre(RECORD_VPOS_TPA_FUHRE oRecFuhre) throws myException;	
	
	
	
	/**
	 * Aufbereiten der Fuhre und deren Orte um die verschiedenen Fälle zu betrachten
	 * @author manfred
	 * @date   06.08.2012
	 * @throws myException
	 */
	protected void	initFuhre(RECORD_VPOS_TPA_FUHRE oRecFuhre) throws myException{
		// initialisieren
		m_rlOrt = null;
		
		fuhre_lager_links = 0;
		fuhre_lager_rechts = 0;
		fuhre_kunde_links = 0;
		fuhre_kunde_rechts = 0;
		
		lager_links = 0;
		lager_rechts = 0;
		kunde_links = 0;
		kunde_rechts = 0;
		
	
		m_Bewegung = null;
		
		
		//
		// Füllen der Daten
		//
		m_oFuhre = oRecFuhre;
		
		
		// lesen der Fuhrenorte
		try {
			m_rlOrt = m_oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N') = 'N' ", " DEF_QUELLE_ZIEL, ID_ARTIKEL  " ,true);
		} catch (myException e) {
			m_rlOrt = null;
		}
		
		// Hauptfuhre untersuchten
		if (m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-")) ){
			lager_links++;
			fuhre_lager_links++;
		} else {
			kunde_links++;
			fuhre_kunde_links++;
		}
		
		if (m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-")) ){
			lager_rechts++;
			fuhre_lager_rechts++;
		} else {
			kunde_rechts++;
			fuhre_kunde_rechts++;
		}
		
		
		// Fuhrenorte untersuchen
		if (m_rlOrt != null){
			for (RECORD_VPOS_TPA_FUHRE_ORT o: m_rlOrt.values()){
				
				if (m_vLagerOrte.contains(o.get_ID_ADRESSE_LAGER_cUF_NN("-")) ){
					if (o.get_DEF_QUELLE_ZIEL_cUF().equals("EK")){
						lager_links++;
					} else {
						lager_rechts++;
					}
				} else {
					if (o.get_DEF_QUELLE_ZIEL_cUF().equals("EK")){
						kunde_links++;
					} else {
						kunde_rechts++;
					}
				}
			}
		}
		
		// summen...
		sum_links = lager_links + kunde_links;
		sum_rechts = lager_rechts + kunde_rechts;
		
	}
	
	
	
		
	/**
	 * füllt die Basisdaten, unabhängig ob Strecke, WE-WA-Fuhre oder ähnlichem
	 * @author manfred
	 * @date   01.08.2012
	 * @param oFuhre
	 * @return
	 * @throws myException
	 */
	protected JtBewegung_Vektor generiereVectorAusFuhre_Basisdaten(RECORD_VPOS_TPA_FUHRE oFuhre) throws myException{
		JtBewegung_Vektor oVec = new JtBewegung_Vektor();

		oVec.setJtBewegung(m_Bewegung);
		oVec.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		oVec.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));

		oVec.setAbgeschlossenAm(null);
		oVec.setAbgeschlossenVon(null);
		
		oVec.setErzeugtVon(oFuhre.get_ERZEUGT_VON_cUF());
		oVec.setErzeugtAm(bibDate.String2Date(oFuhre.get_ERZEUGT_AM_cUF() ) ); 
		
		oVec.setADatumBis(bibDate.String2Date(oFuhre.get_DATUM_ABHOLUNG_ENDE_cUF()));
		oVec.setADatumVon(bibDate.String2Date(oFuhre.get_DATUM_ABHOLUNG_cUF()));
		oVec.setAnhaengerkennzeichen(oFuhre.get_ANHAENGERKENNZEICHEN_cUF());
		oVec.setAvvAusstellungDatum(bibDate.String2Date(oFuhre.get_AVV_AUSSTELLUNG_DATUM_cUF()));
		oVec.setBemerkung(oFuhre.get_BEMERKUNG_cUF());
		oVec.setBemerkungSachbearbeiter(oFuhre.get_BEMERKUNG_SACHBEARBEITER_cUF());
		
		oVec.setDelDate(bibDate.String2Date(oFuhre.get_DEL_DATE_cUF()));
		oVec.setDeleted(oFuhre.get_DELETED_cUF());
		oVec.setDelGrund(oFuhre.get_DEL_GRUND_cUF());
		oVec.setDelKuerzel(oFuhre.get_DEL_KUERZEL_cUF());
		
		oVec.setEkVkZuordZwang(oFuhre.get_EK_VK_ZUORD_ZWANG_cUF());
		oVec.setEuBlattMenge(oFuhre.get_EU_BLATT_MENGE_bdValue(null));
		
		oVec.setEuBlattVolumen(oFuhre.get_EU_BLATT_MENGE_bdValue(null));
		oVec.setGeaendertVon(oFuhre.get_GEAENDERT_VON_cUF());
		oVec.setIdAdresseFremdware(oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_lValue(null));
		oVec.setIdAdresseSpedition(oFuhre.get_ID_ADRESSE_SPEDITION_lValue(null));
		oVec.setIdAdresseStartLogistik(oFuhre.get_ID_ADRESSE_SPEDITION_lValue(null));
		
		oVec.setIdEakCode(oFuhre.get_ID_EAK_CODE_lValue(null));
		
		oVec.setIdUmaKontrakt(oFuhre.get_ID_UMA_KONTRAKT_lValue(null));
		
		oVec.setLaendercodeTransit1(oFuhre.get_LAENDERCODE_TRANSIT1_cUF());
		oVec.setLaendercodeTransit2(oFuhre.get_LAENDERCODE_TRANSIT2_cUF());
		oVec.setLaendercodeTransit3(oFuhre.get_LAENDERCODE_TRANSIT3_cUF());
		oVec.setLDatumBis(bibDate.String2Date(oFuhre.get_DATUM_ANLIEFERUNG_ENDE_cUF()));
		oVec.setLDatumVon(bibDate.String2Date(oFuhre.get_DATUM_ANLIEFERUNG_cUF()));
		
		oVec.setLetzteAenderung(bibDate.String2Date(oFuhre.get_LETZTE_AENDERUNG_cUF()));
		oVec.setPrintEuAmtsblatt(oFuhre.get_PRINT_EU_AMTSBLATT_cUF());
		
		
		/**
		 * STATUS
		 * "Kann folgende Einträge haben: 
		 * GEPLANT
		 * STORNIERT (protokollieren: STORNIERT_VON,_AM) 
		 * AKTIV  (noetig, damit irgend ein Schein o.ä. erzeugt werden kann) 
		 * ABGESCHLOSSEN  (protokollieren: ABGESCHLOSSEN_VON,_AM)" 
		 */
		String sStatus = "GEPLANT";
		
		if (oFuhre.get_IST_STORNIERT_cUF_NN("-").equalsIgnoreCase("Y")){
			sStatus = "STORNIERT";
			oVec.setStorniertAm(bibDate.String2Date(oFuhre.get_LETZTE_AENDERUNG_cUF()));
			oVec.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
			oVec.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
		} else if (oFuhre.get_ABGESCHLOSSEN_cUF_NN("-").equalsIgnoreCase("Y")){
			//TODO: das kann nicht stimmen, aber was ist das Kriterium
			sStatus = "ABGESCHLOSSEN";
		} else if (oFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(null) != null || oFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null) != null ){
			sStatus = "AKTIV";
		} 
		oVec.setStatus(sStatus);
		
		oVec.setTransportkennzeichen(oFuhre.get_TRANSPORTKENNZEICHEN_cUF());
		oVec.setTransportmittel(oFuhre.get_TRANSPORTMITTEL_cUF());

//		EIGENWARE/FREMDWARE/LEERGUT
		String sWarenklasse = "EIGENWARE";
		if (oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF() != null || oFuhre.get_OHNE_ABRECHNUNG_cUF_NN("-").equalsIgnoreCase("Y")){
			sWarenklasse = "FREMDWARE";
		} 
		oVec.setWarenklasse(sWarenklasse);
		
		oVec.setZeitstempel(oFuhre.get_ZEITSTEMPEL_cUF());
		
		return oVec;
	}
	

	
	


	/**
	 * Erzeugen eines Bewegungsatoms aus einer HauptFuhre für einen Wareneingang
	 * LAGER -> KD
	 * LAGER -> MIX  / MIX -> KUNDE
	 * 
	 * Eine Mengenkorrektur / Sortenkorrektur findet aktuell implizit im MIXed-Lager statt
	 * 
	 * @author manfred
	 * @date   01.08.2012
	 * @param oFuhre
	 * @return
	 * @throws myException 
	 */
	protected JtBewegung_Vektor_Pos generiere_VPos_Hauptfuhre (RECORD_VPOS_TPA_FUHRE oFuhre, JtBewegung_Vektor oVec, long posnrInVektor, ENUM_SONDERLAGER enumSonderlager) throws myException{
		
		JtBewegung_Vektor_Pos vecPos = new JtBewegung_Vektor_Pos(oVec);
		oVec.getJtBewegungVektorPoss().add(vecPos);

		// neue Vectorposition
		vecPos.setPosnr(posnrInVektor);

		//
		// 1. Atom: Buchung ins Mixed (oder LKW-Lager)
		//
		JtBewegung_Atom oAtom = new JtBewegung_Atom();

		oAtom.setJtBewegung(m_Bewegung);
		oAtom.setJtBewegungsvektor(oVec);
		oAtom.setJtBewegungsvektorPos(vecPos);
		
		oAtom.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		oAtom.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
		oAtom.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		
		oAtom.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		
		oAtom.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		oAtom.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(null));

		oAtom.setPosnr(1L);
		
		oAtom.setAbgerechnet(null);
		oAtom.setAbrechenbar(null);
		
		// userinfo
		oAtom.setErzeugtAm(bibDate.String2Date(oFuhre.get_ERZEUGT_AM_cUF()));
		oAtom.setErzeugtVon(oFuhre.get_ERZEUGT_VON_cUF());
		oAtom.setGeaendertVon(oFuhre.get_GEAENDERT_VON_cUF());
		oAtom.setLetzteAenderung(bibDate.String2Date(oFuhre.get_LETZTE_AENDERUNG_cUF()));

		
		// Startstation
		JtBewegung_Station oStart = new JtBewegung_Station(oAtom, -1);
		oStart.setIDAdresse(oFuhre.get_ID_ADRESSE_LAGER_START_lValue(null));
		oStart.setIDAdresseBasis(oFuhre.get_ID_ADRESSE_START_lValue(null));
		oStart.setFax(oFuhre.get_FAX_LIEFERANT_cUF());
		oStart.setHausnummer(oFuhre.get_L_HAUSNUMMER_cUF());
		oStart.setLaendercode(oFuhre.get_L_LAENDERCODE_cUF());
		oStart.setName1(oFuhre.get_L_NAME1_cUF());
		oStart.setName2(oFuhre.get_L_NAME2_cUF());
		oStart.setName3(oFuhre.get_L_NAME3_cUF());
		oStart.setOeffnungszeiten(oFuhre.get_OEFFNUNGSZEITEN_LIEF_cUF());
		oStart.setOrt(oFuhre.get_L_ORT_cUF());
		oStart.setOrtzusatz(oFuhre.get_L_ORTZUSATZ_cUF());
		oStart.setPlz(oFuhre.get_L_PLZ_cUF());
		oStart.setStrasse(oFuhre.get_L_STRASSE_cUF());
		oStart.setTelefon(oFuhre.get_TEL_LIEFERANT_cUF());
		oStart.setBestellnummer(oFuhre.get_BESTELLNUMMER_EK_cUF());
		oStart.setWiegekartenKenner(oFuhre.get_WIEGEKARTENKENNER_LADEN_cUF());
		oStart.setIDWiegekarte(oFuhre.get_ID_WIEGEKARTE_LIEF_lValue(null));
		oStart.setKontrollmenge(oFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(null));
		
		
		// Zielstation
		JtBewegung_Station oZiel = new JtBewegung_Station(oAtom, +1);
		oZiel.setIDAdresse(bibSES.get_ID_AdresseLAGER_longValue(enumSonderlager));
		
		oZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()) );
		
		// Datum
		oAtom.setLeistungsdatum(bibDate.String2Date(oFuhre.get_DATUM_AUFLADEN_cUF()));
		
		// Sorte
		// info über neue Sorte lesen:
		Long idArtikel = getArtikelIDFromArtikelBezID(oFuhre.get_ID_ARTIKEL_BEZ_EK_cUF_NN("-1"));

		
		// Besitzverhältnisse
		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_bdValue(null) != null){
			// bei Fremdauftrag immer der Auftraggeber
			m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_lValue(null);
			m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
		} else {
			String sIDArtikel = idArtikel != null ? idArtikel.toString() : "-1";
			if (oFuhre.get_OHNE_ABRECHNUNG_cUF_NN("N").equalsIgnoreCase("Y") ){
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					m_IDAdresseBesitzerStart = m_IDAdresseMandant;
					m_IDAdresseBesitzerZiel = m_IDAdresseMandant;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_START_lValue(null);
					m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
				}
			} else {
				// Eigenware
				m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_START_lValue(null);
				m_IDAdresseBesitzerZiel =  m_IDAdresseMandant; //oFuhre.get_ID_ADRESSE_ZIEL_lValue(null);
			}
		}
		
		// abrechenbar immer, wenn besitzerwechsel stattfindet
		oAtom.setAbrechenbar(m_IDAdresseBesitzerStart.equals(m_IDAdresseBesitzerZiel) ? "N" : "Y");
		oStart.setIDAdresseBesitzer(m_IDAdresseBesitzerStart);
		oZiel.setIDAdresseBesitzer(m_IDAdresseBesitzerZiel);
		
		oAtom.setIdArtikel(idArtikel);
		oAtom.setIdArtikelBez(oFuhre.get_ID_ARTIKEL_BEZ_EK_lValue(null));
		oAtom.setArtbez1(oFuhre.get_ARTBEZ1_EK_cUF()); 
		oAtom.setArtbez2(oFuhre.get_ARTBEZ2_EK_cUF());
		
		// Menge abhaengig vom Schalter
		oAtom.setPlanmenge(oFuhre.get_ANTEIL_PLANMENGE_LIEF_bdValue(null));

		BigDecimal bdMenge = null;
		// falls der Ausgangsort ein Lagerot ist, dann immer die Lademenge nehmen, ansonsten das was der Schalter definiert für die Buchhalterische Menge
		if (m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-"))){
			// Warenausgang, immer Brutto-Menge vom Lager weg
			bdMenge = oFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(null); 
		} else {
			// falls der Ausgangsort ein Kunde ist, dann abhängig vom Schalter
			if (oFuhre.get_LADEMENGE_GUTSCHRIFT_cUF_NN("-").equalsIgnoreCase("Y")){
				bdMenge = oFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(null);
			} else {
				bdMenge = oFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(null);
			}
		}
		
		oAtom.setMenge(bdMenge);

		// bisher nur die Abzüge die auf der Lademenge eingetragen waren
		BigDecimal bdAbzugMenge = oFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO);
		oAtom.setAbzugMenge(bdAbzugMenge);
		
		if (bdMenge != null){ 
			oAtom.setMengeNetto(bdMenge.subtract(bdAbzugMenge));
		}
		
		
		oAtom.setPruefungMenge(oFuhre.get_PRUEFUNG_LADEMENGE_cUF());
		oAtom.setPruefungMengeAm(bibDate.String2Date(oFuhre.get_PRUEFUNG_LADEMENGE_AM_cUF()));
		oAtom.setPruefungMengeVon(oFuhre.get_PRUEFUNG_LADEMENGE_VON_cUF());

		// Preisinformationen
		oAtom.setEPreis(oFuhre.get_EINZELPREIS_EK_bdValue(null));
		oAtom.setEPreisResultNettoMge(oFuhre.get_EPREIS_RESULT_NETTO_MGE_EK_bdValue(null));
		
		// Steuerinfo
		oAtom.setEuSteuerVermerk(oFuhre.get_EU_STEUER_VERMERK_EK_cUF());
		oAtom.setSteuersatz(oFuhre.get_STEUERSATZ_EK_bdValue(null));

		
		//Daten aus der Rechnungsposition zusätzlich noch setzen, falls die Ladeseite kein Lager ist
		int nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-")) ? -1: 1 ;
		setRGValuesInAtomFromFuhre(oAtom, oFuhre, nLagervorzeichen);
		
		
		// Buchungsnummer
		oAtom.setBuchungsnummer(oFuhre.get_BUCHUNGSNR_FUHRE_cUF());
		
		// Bestellnr
		String sBestellnr = oFuhre.get_BESTELLNUMMER_EK_cUF();
		if (sBestellnr == null) {
			// manchmal steht die Bestellnummer auch auf der falschen Seite
			sBestellnr = oFuhre.get_BESTELLNUMMER_VK_cUF();
		}

		// Bemerkungsfelder
		oAtom.setBemerkung(oFuhre.get_BEMERKUNG_cUF());
		oAtom.setBemerkungSachbearbeiter(oFuhre.get_BEMERKUNG_SACHBEARBEITER_cUF());

		//Löschinformationen
		oAtom.setDelDate(bibDate.String2Date(oFuhre.get_DEL_DATE_cUF())) ;
		oAtom.setDeleted(oFuhre.get_DELETED_cUF());
		oAtom.setDelGrund(oFuhre.get_DEL_GRUND_cUF());
		oAtom.setDelKuerzel(oFuhre.get_DEL_KUERZEL_cUF());
		
		// Angebotsinfo
		oAtom.setIdVposStd(oFuhre.get_ID_VPOS_STD_EK_lValue(null));
		
		
		// Kontraktinfos
		oAtom.setIdVposKon(oFuhre.get_ID_VPOS_KON_EK_lValue(null));
		oAtom.setKontraktzwang(oFuhre.get_KEIN_KONTRAKT_NOETIG_cUF());
		oAtom.setKontraktzwangAusAm(null);
		oAtom.setKontraktzwangAusGrund(null);
		oAtom.setKontraktzwangAusVon(null);
		
		// Storno
		oAtom.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
		oAtom.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null);
		oAtom.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
		oAtom.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
		
		
		// sonstiges
		oAtom.setLieferinfoTpa(oFuhre.get_LIEFERINFO_TPA_cUF());
		oAtom.setNationalerAbfallCode(oFuhre.get_NATIONALER_ABFALL_CODE_cUF());
		oAtom.setNotifikationNr(oFuhre.get_NOTIFIKATION_NR_cUF());
		oAtom.setZeitstempel(oFuhre.get_ZEITSTEMPEL_cUF());
		oAtom.setOrdnungsnummerCmr(oFuhre.get_ORDNUNGSNUMMER_CMR_cUF());
		oAtom.setPostennummer(oFuhre.get_POSTENNUMMER_EK_cUF());
		oAtom.setPreisermittlung(null);
		oAtom.setSetzkastenKomplett(null);
		
		// das 1. Atom in den Vektor eintragen
		vecPos.getJtBewegungsatoms().add(oAtom);

		
		

		//
		// das 2. Atom ist die Buchung auf der Abladeseite
		// 
		JtBewegung_Atom oAtom1 = new JtBewegung_Atom();

		oAtom1.setJtBewegung(m_Bewegung);
		oAtom1.setJtBewegungsvektor(oVec);
		oAtom1.setJtBewegungsvektorPos(vecPos);
		
		oAtom1.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		oAtom1.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
		oAtom1.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		
		oAtom1.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		
		oAtom1.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		oAtom1.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(null));

		
		oAtom1.setPosnr(2L);
		
		oAtom1.setAbgerechnet(null);
		oAtom1.setAbrechenbar(null);
		
		// userinfo
		oAtom1.setErzeugtAm(bibDate.String2Date(oFuhre.get_ERZEUGT_AM_cUF()));
		oAtom1.setErzeugtVon(oFuhre.get_ERZEUGT_VON_cUF());
		oAtom1.setGeaendertVon(oFuhre.get_GEAENDERT_VON_cUF());
		oAtom1.setLetzteAenderung(bibDate.String2Date(oFuhre.get_LETZTE_AENDERUNG_cUF()));

		
		// Startstation kommt vom 1. Atom und ist in der Regel das MK-Lager
		JtBewegung_Station oStart1 = new JtBewegung_Station(oAtom1, -1);
		oStart1.setIDAdresse(oZiel.getIDAdresse().lValue);
		oStart1.setIDAdresseBasis(oZiel.getIDAdresseBasis().lValue);
		
		// Zielstation
		JtBewegung_Station oZiel1 = new JtBewegung_Station(oAtom1, +1);
		oZiel1.setIDAdresse( oFuhre.get_ID_ADRESSE_LAGER_ZIEL_lValue(null) );
		oZiel1.setIDAdresseBasis( oFuhre.get_ID_ADRESSE_ZIEL_lValue(null) );
		oZiel1.setFax(oFuhre.get_FAX_ABNEHMER_cUF());
		oZiel1.setHausnummer(oFuhre.get_A_HAUSNUMMER_cUF());
		oZiel1.setLaendercode(oFuhre.get_A_LAENDERCODE_cUF());
		oZiel1.setName1(oFuhre.get_A_NAME1_cUF());
		oZiel1.setName2(oFuhre.get_A_NAME2_cUF());
		oZiel1.setName3(oFuhre.get_A_NAME3_cUF());
		oZiel1.setOeffnungszeiten(oFuhre.get_OEFFNUNGSZEITEN_ABN_cUF());
		oZiel1.setOrt(oFuhre.get_A_ORT_cUF());
		oZiel1.setOrtzusatz(oFuhre.get_A_ORTZUSATZ_cUF());
		oZiel1.setPlz(oFuhre.get_A_PLZ_cUF());
		oZiel1.setStrasse(oFuhre.get_A_STRASSE_cUF());
		oZiel1.setTelefon(oFuhre.get_TEL_ABNEHMER_cUF());

		oZiel1.setBestellnummer(oFuhre.get_BESTELLNUMMER_VK_cUF());
		oZiel1.setKontrollmenge(oFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null));
		oZiel1.setWiegekartenKenner(oFuhre.get_WIEGEKARTENKENNER_ABLADEN_cUF());
		oZiel1.setIDWiegekarte(oFuhre.get_ID_WIEGEKARTE_ABN_lValue(null));
		
		
		// Datum
		oAtom1.setLeistungsdatum(bibDate.String2Date(oFuhre.get_DATUM_ABLADEN_cUF()));
		
		// Sorte
		idArtikel = getArtikelIDFromArtikelBezID(oFuhre.get_ID_ARTIKEL_BEZ_VK_cUF_NN("-1"));

		
		// Besitzverhältnisse
		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_bdValue(null) != null){
			// bei Fremdauftrag immer der Auftraggeber
			m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_lValue(null);
			m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
		} else {
			String sIDArtikel = idArtikel != null ? idArtikel.toString() : "-1";
			if (oFuhre.get_OHNE_ABRECHNUNG_cUF_NN("N").equalsIgnoreCase("Y") ){
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					m_IDAdresseBesitzerStart = m_IDAdresseMandant;
					m_IDAdresseBesitzerZiel = m_IDAdresseMandant;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_ZIEL_lValue(null);
					m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
				}
			} else {
				// Eigenware
				m_IDAdresseBesitzerZiel = oFuhre.get_ID_ADRESSE_ZIEL_lValue(null);
				m_IDAdresseBesitzerStart = oZiel.getIDAdresseBesitzer().lValue;
			}
		}
		
		// abrechenbar immer, wenn besitzerwechsel stattfindet
		oAtom1.setAbrechenbar(m_IDAdresseBesitzerStart.equals(m_IDAdresseBesitzerZiel) ? "N" : "Y");
		oStart1.setIDAdresseBesitzer(m_IDAdresseBesitzerStart);
		oZiel1.setIDAdresseBesitzer(m_IDAdresseBesitzerZiel);
		
		oAtom1.setIdArtikel(idArtikel);
		oAtom1.setIdArtikelBez(oFuhre.get_ID_ARTIKEL_BEZ_VK_lValue(null));
		oAtom1.setArtbez1(oFuhre.get_ARTBEZ1_VK_cUF()); 
		oAtom1.setArtbez2(oFuhre.get_ARTBEZ2_VK_cUF());
		
		// MENGEN
		//
		oAtom1.setPlanmenge(oFuhre.get_ANTEIL_PLANMENGE_ABN_bdValue(null));
		
		// WA Menge
		bdMenge = null;
		if (m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-"))){
			//  wenn das Ziel ein Lager ist
			bdMenge = oFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null);
		} else {
			// wenn das Ziellager KEIN Lager ist, dann abhägig vom Schalter
			if (oFuhre.get_ABLADEMENGE_RECHNUNG_cUF_NN("-").equalsIgnoreCase("Y")){
				bdMenge = oFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null);
			} else {
				bdMenge = oFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(null);
			}
		}
		
		oAtom1.setMenge(bdMenge);

		// Mengenabzug
		bdAbzugMenge = oFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO);
		oAtom1.setAbzugMenge(bdAbzugMenge);

		// Menge Netto
		if (bdMenge != null){
			oAtom1.setMengeNetto(bdMenge.subtract(bdAbzugMenge));
		}

		
		oAtom1.setPruefungMenge(oFuhre.get_PRUEFUNG_ABLADEMENGE_cUF());
		oAtom1.setPruefungMengeAm(bibDate.String2Date(oFuhre.get_PRUEFUNG_ABLADEMENGE_AM_cUF()));
		oAtom1.setPruefungMengeVon(oFuhre.get_PRUEFUNG_ABLADEMENGE_VON_cUF());

		// Preisinformationen
		oAtom1.setEPreis(oFuhre.get_EINZELPREIS_VK_bdValue(null));
		oAtom1.setEPreisResultNettoMge(oFuhre.get_EPREIS_RESULT_NETTO_MGE_VK_bdValue(null));
		
		//
		// WA: müssen noch die Preis-Werte des Einbuchungs-Atoms geschrieben werden
		// Von Lagerseite auf neue Menge umrechnen 
//		BigDecimal bdMengeKundeNetto = oAtom1.getMengeNetto().bdValue != null ? oAtom1.getMengeNetto().bdValue : BigDecimal.ZERO;
//		BigDecimal bdPreisKundeNetto = oAtom1.getEPreisResultNettoMge().bdValue != null ? oAtom1.getEPreisResultNettoMge().bdValue : BigDecimal.ZERO ;
//		
//		BigDecimal bdGesamtpreis  =  bdMengeKundeNetto.multiply( bdPreisKundeNetto);
//		BigDecimal bdEPreisLagerNetto = null;
//		bdMenge = oAtom.getMenge().bdValue;
//		if (bdMenge != null && bdMenge.compareTo(BigDecimal.ZERO) != 0){
//			bdEPreisLagerNetto = bdGesamtpreis.divide(oAtom.getMenge().bdValue, 2, BigDecimal.ROUND_HALF_UP);
//		}

		//Daten aus der Rechnungsposition zusätzlich noch setzen, falls das Ziel kein Lager ist
		//if (!m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-"))){
		nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-")) ? 1: -1 ;
		setRGValuesInAtomFromFuhre(oAtom1, oFuhre, nLagervorzeichen);
		
		// Preise setzen im Start-Atom
//		oAtom.setEPreis(bdEPreisLagerNetto);
//		oAtom.setEPreisResultNettoMge(bdEPreisLagerNetto);
		
		// Steuerinfo
		oAtom1.setEuSteuerVermerk(oFuhre.get_EU_STEUER_VERMERK_EK_cUF());
		oAtom1.setSteuersatz(oFuhre.get_STEUERSATZ_EK_bdValue(null));

		// Buchungsnummer
		oAtom1.setBuchungsnummer(oFuhre.get_BUCHUNGSNR_FUHRE_cUF());
		
		// Bestellnr
		sBestellnr = oFuhre.get_BESTELLNUMMER_VK_cUF();
		if (sBestellnr == null) {
			// manchmal steht die Bestellnummer auch auf der falschen Seite
			sBestellnr = oFuhre.get_BESTELLNUMMER_EK_cUF();
		}

		// Bemerkungsfelder
		oAtom1.setBemerkung(oFuhre.get_BEMERKUNG_cUF());
		oAtom1.setBemerkungSachbearbeiter(oFuhre.get_BEMERKUNG_SACHBEARBEITER_cUF());

		//Löschinformationen
		oAtom1.setDelDate(bibDate.String2Date(oFuhre.get_DEL_DATE_cUF())) ;
		oAtom1.setDeleted(oFuhre.get_DELETED_cUF());
		oAtom1.setDelGrund(oFuhre.get_DEL_GRUND_cUF());
		oAtom1.setDelKuerzel(oFuhre.get_DEL_KUERZEL_cUF());
		
		// Angebotsinfo
		oAtom1.setIdVposStd(oFuhre.get_ID_VPOS_STD_VK_lValue(null));
		
		// Kontraktinfos
		oAtom1.setIdVposKon(oFuhre.get_ID_VPOS_KON_VK_lValue(null));
		oAtom1.setKontraktzwang(oFuhre.get_KEIN_KONTRAKT_NOETIG_cUF());
		oAtom1.setKontraktzwangAusAm(null);
		oAtom1.setKontraktzwangAusGrund(null);
		oAtom1.setKontraktzwangAusVon(null);
		
		// Storno
		oAtom1.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
		oAtom1.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null);
		oAtom1.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
		oAtom1.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
		
		// sonstiges
		oAtom1.setLieferinfoTpa(oFuhre.get_LIEFERINFO_TPA_cUF());
		oAtom1.setNationalerAbfallCode(oFuhre.get_NATIONALER_ABFALL_CODE_cUF());
		oAtom1.setNotifikationNr(oFuhre.get_NOTIFIKATION_NR_cUF());
		oAtom1.setZeitstempel(oFuhre.get_ZEITSTEMPEL_cUF());
		oAtom1.setOrdnungsnummerCmr(oFuhre.get_ORDNUNGSNUMMER_CMR_cUF());
		oAtom1.setPostennummer(oFuhre.get_POSTENNUMMER_EK_cUF());
		oAtom1.setPreisermittlung(null);
		oAtom1.setSetzkastenKomplett(null);
		
		
		
		// Preisinformationen in beiden Atomen angleichen. Falls ein Preis null oder 0 ist wird der Preis des anderen Atoms genommen.
		if (oAtom.getEPreis().bdValue == null || oAtom.getEPreis().bdValue.equals(BigDecimal.ZERO)){
			oAtom.setEPreis(oAtom1.getEPreis().bdValue);
			oAtom.setEPreisResultNettoMge(oAtom1.getEPreisResultNettoMge().bdValue);
		} else if (oAtom1.getEPreis().bdValue == null || oAtom1.getEPreis().bdValue.equals(BigDecimal.ZERO)){
			oAtom1.setEPreis(oAtom.getEPreis().bdValue);
			oAtom1.setEPreisResultNettoMge(oAtom.getEPreisResultNettoMge().bdValue);
		}
		
		
		// das 1. Atom in den Vektor eintragen
		vecPos.getJtBewegungsatoms().add(oAtom1);

		
		return vecPos;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	/**
	 * Setzt relevante Rechnungspositions-Werte im Atom bezogen auf die Fuhre
	 * @author manfred
	 * @date   12.09.2013
	 * @param atom
	 * @param oFuhre
	 * @param Lagervorzeichen
	 * @throws myException
	 */
	protected void setRGValuesInAtomFromFuhre(JtBewegung_Atom atom, RECORD_VPOS_TPA_FUHRE oFuhre, int Lagervorzeichen) throws myException {
		
		// Alternative Lieferbedingung aus der Fuhre setzen
		String sLieferbedingung = "";
		if(Lagervorzeichen>0){
			sLieferbedingung = oFuhre.get_LIEFERBED_ALTERNATIV_EK_cUF();
		} else {
			sLieferbedingung = oFuhre.get_LIEFERBED_ALTERNATIV_VK_cUF();
		}
		
		// zuerst die Lieferbedingung aus der Fuhre im Atom eintragen 
		setIDLieferbedingungInAtom(atom, sLieferbedingung);
		
		RECORD_VPOS_RG oVposRg = null;
		try {
			oVposRg = new RECORD_VPOS_RG(  	" ID_VPOS_RG_STORNO_VORGAENGER is null " +
															" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
														   	" AND NVL(DELETED,'N') = 'N' " +
														   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = " + oFuhre.get_ID_VPOS_TPA_FUHRE_cUF() +
														   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL " + 
														   	" AND LAGER_VORZEICHEN = " + Integer.toString(Lagervorzeichen) );
			

			// Werte setzen wenn eine RG-Position gefunden wurde
			setRGValuesInAtom( atom, oVposRg );
			 
		} catch (myException e) {
//			throw new myException("setRGValuesInAtomFromFuhre::Rechnungsposition konnte nicht ermittelt werden", e);
		}
		
		
	}
	
	
	
	
	
	/**
	 * Setzt relevante Rechnungspositions-Werte im Atom bezogen auf den Fuhrenort
	 * @author manfred
	 * @date   12.09.2013
	 * @param atom
	 * @param oFuhreOrt
	 * @throws myException
	 */
	protected void setRGValuesInAtomFromFuhreOrt (JtBewegung_Atom atom, RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt) throws myException{
		
		// Alternative Lieferbedingung aus dem Fuhrenort setzen
		String sLieferbedingung =oFuhreOrt.get_LIEFERBED_ALTERNATIV_cUF(); 
		
		// zuerst die Lieferbedingungen im Atom aus der Fuhre setzen 
		setIDLieferbedingungInAtom(atom, sLieferbedingung);
		

		RECORD_VPOS_RG oVposRg = null;
		try {
			oVposRg = new RECORD_VPOS_RG(  	" ID_VPOS_RG_STORNO_VORGAENGER is null " +
															" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
														   	" AND NVL(DELETED,'N') = 'N' " +
														   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = " + oFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF() +
														   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = " + oFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
			
			
			// Werte setzen wenn eine RG-Position gefunden wurde
			setRGValuesInAtom( atom, oVposRg );
			
		} catch (myException e) {
//			throw new myException("setRGValuesInAtomFromFuhreOrt::Rechnungsposition konnte nicht ermittelt werden", e);
		}
		
		
		
	}
	
	
	/**
	 * Setzt die Werte aus der Rechnungsposition im Atom
	 * @author manfred
	 * @date   12.09.2013
	 * @param atom
	 * @param oVposRg
	 * @throws myException
	 */
	private void setRGValuesInAtom(JtBewegung_Atom atom, RECORD_VPOS_RG oVposRg ) throws myException{
			
			if (oVposRg != null){
				
				// falls die Rechnungsposition vorhanden ist, dann die Menge aus der Rechnungsposition nehmen
				atom.setEPreis(oVposRg.get_EINZELPREIS_bdValue(null));
				atom.setEPreisResultNettoMge(oVposRg.get_EPREIS_RESULT_NETTO_MGE_bdValue(null));
				atom.setEPreisResultBruttoMenge(oVposRg.get_EINZELPREIS_RESULT_bdValue(null));
				
//				atom.setMenge(oVposRg.get_ANZAHL_bdValue(null));
				
//				atom.setGesamtpreis(oVposRg.get_GESAMTPREIS_bdValue(null));
				
				atom.setGPreisAbzMenge(oVposRg.get_GPREIS_ABZ_MGE_bdValue(null));
				atom.setGPreisAbzAufNettomenge(oVposRg.get_GPREIS_ABZ_AUF_NETTOMGE_bdValue(null));
				atom.setGPreisAbzVorauszahlung(oVposRg.get_GPREIS_ABZ_VORAUSZAHLUNG_bdValue(null));
				
				
				atom.setIdZahlungsbedingungen(oVposRg.get_ID_ZAHLUNGSBEDINGUNGEN_lValue(null));
				
				// die Lieferbedingung zuerst aus der Fuhre (also aus dem Atom) lesen, wenn nicht vorhanden, dann aus der Rechnungsposition
				String sLieferbedingung = atom.getLieferbedingungen().sValue;
				if (bibALL.isEmpty(sLieferbedingung) ){
					// falls noch keine Lieferbedingung im Atom eingetragen war, die Lieferbedingung aus der Rechnungsposition eintragen
					sLieferbedingung =  oVposRg.get_LIEFERBEDINGUNGEN_cUF(); 
					setIDLieferbedingungInAtom(atom, sLieferbedingung);
				}
			}
	}
	
	
	
	
	
	/**
	 * Setzt die Lieferbedingung in die ID um
	 * @param atom
	 * @param sLieferbedingung
	 */
	private void setIDLieferbedingungInAtom(JtBewegung_Atom atom, String sLieferbedingung){
		// jetzt die ID aus der Lieferbedingung ermitteln
		if (sLieferbedingung != null){
			atom.setLieferbedingungen(sLieferbedingung);

			sLieferbedingung = sLieferbedingung.trim().toUpperCase();
			
			if (sLieferbedingung != null){
				if (m_hmLieferbedingungen.containsKey(sLieferbedingung)){
					atom.setIdLieferbedingungen(Long.parseLong(m_hmLieferbedingungen.get(sLieferbedingung)));
				} else if (m_hmLieferbedingungen.containsKey(sLieferbedingung.substring(0, 3) )){
					// falls die Lieferbedingungen nicht gefunden werden, dann die ersten drei Buchstaben vergleichen
					atom.setIdLieferbedingungen(Long.parseLong(m_hmLieferbedingungen.get(sLieferbedingung.substring(0, 3) )));
				}
			}
		}
	}
	


	
	/**
	 * erzeugt einen Fuhrenort auf der Ladeseite. 
	 * Geht immer ins Mixed-Lager
	 * 
	 * @author manfred
	 * @date   06.08.2012
	 * @param oFuhre
	 * @return
	 * @throws myException 
	 */
	protected JtBewegung_Vektor_Pos generiereVektorPosUndAtome_FuhrenORT_Ladeseite (JtBewegung_Vektor oVec_ori, RECORD_VPOS_TPA_FUHRE oFuhre, RECORD_VPOS_TPA_FUHRE_ORT oOrt, long posnrVektorPos, ENUM_SONDERLAGER enumSonderlager, boolean generateDummyEntry) throws myException{
		
		JtBewegung_Vektor_Pos vecPos = null;
		vecPos = new JtBewegung_Vektor_Pos(oVec_ori);
		
		oVec_ori.getJtBewegungVektorPoss().add(vecPos);
		
		vecPos.setPosnr(posnrVektorPos);
		
		//
		// Bewegungsatom
		//
		JtBewegung_Atom oAtom = new JtBewegung_Atom();
		oAtom.setPosnr(new Long(1));

		oAtom.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		oAtom.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		oAtom.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		oAtom.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
		
		oAtom.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		oAtom.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(null));
		
		// userinfo
		oAtom.setErzeugtAm(bibDate.String2Date(oOrt.get_ERZEUGT_AM_cUF()));
		oAtom.setErzeugtVon(oOrt.get_ERZEUGT_VON_cUF());
		oAtom.setGeaendertVon(oOrt.get_GEAENDERT_VON_cUF());
		oAtom.setLetzteAenderung(bibDate.String2Date(oOrt.get_LETZTE_AENDERUNG_cUF()));
		
		oAtom.setAbgerechnet(null);
		oAtom.setAbrechenbar(null);

		
		// Start
		JtBewegung_Station oStart = new JtBewegung_Station(oAtom, -1);
		
		oStart.setIDAdresse(oOrt.get_ID_ADRESSE_LAGER_lValue(null));
		oStart.setIDAdresseBasis(oOrt.get_ID_ADRESSE_lValue(null));
		oStart.setFax(oOrt.get_FAX_cUF());
		oStart.setHausnummer(oOrt.get_HAUSNUMMER_cUF());
		oStart.setLaendercode(oOrt.get_LAENDERCODE_cUF());
		oStart.setName1(oOrt.get_NAME1_cUF());
		oStart.setName2(oOrt.get_NAME2_cUF());
		oStart.setName3(oOrt.get_NAME3_cUF());
		oStart.setOeffnungszeiten(oOrt.get_OEFFNUNGSZEITEN_cUF());
		oStart.setOrt(oOrt.get_ORT_cUF());
		oStart.setOrtzusatz(oOrt.get_ORTZUSATZ_cUF());
		oStart.setPlz(oOrt.get_PLZ_cUF());
		oStart.setStrasse(oOrt.get_STRASSE_cUF());
		oStart.setTelefon(oOrt.get_TEL_cUF());
		oStart.setBestellnummer(oOrt.get_BESTELLNUMMER_cUF());
		oStart.setKontrollmenge(oOrt.get_ANTEIL_ABLADEMENGE_bdValue(null));
		oStart.setWiegekartenKenner(oOrt.get_WIEGEKARTENKENNER_cUF());
		oStart.setIDWiegekarte(oOrt.get_ID_WIEGEKARTE_lValue(null));
		

		
		// Ziel
		JtBewegung_Station oZiel = new JtBewegung_Station(oAtom, +1);
		oZiel.setIDAdresse(bibSES.get_ID_AdresseLAGER_longValue(enumSonderlager) );;
		oZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
		
		
		// Datum
		oAtom.setLeistungsdatum(bibDate.String2Date(oOrt.get_DATUM_LADE_ABLADE_cUF()));
		
		// Besitzverhältnisse
		// EIGENWARE/FREMDWARE/LEERGUT
		String sIDArtikel = oOrt.get_ID_ARTIKEL_lValue((long)-1).toString();
		
		if (oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_bdValue(null) != null){
			// bei Fremdauftrag immer der Auftraggeber
			m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_lValue(null);
			m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
		} else {
			if (oOrt.get_OHNE_ABRECHNUNG_cUF_NN("N").equalsIgnoreCase("Y") ){
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					m_IDAdresseBesitzerStart = m_IDAdresseMandant;
					m_IDAdresseBesitzerZiel = m_IDAdresseMandant;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					m_IDAdresseBesitzerStart = oOrt.get_ID_ADRESSE_lValue(null);
					m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
				}
			} else {
				// Eigenware
				m_IDAdresseBesitzerStart = oOrt.get_ID_ADRESSE_lValue(null);
				m_IDAdresseBesitzerZiel = oFuhre.get_ID_ADRESSE_ZIEL_lValue(null);
			}
		}

		oAtom.setAbrechenbar(m_IDAdresseBesitzerStart.equals(m_IDAdresseBesitzerZiel) ? "N" : "Y");
		oStart.setIDAdresseBesitzer(m_IDAdresseBesitzerStart);
		oZiel.setIDAdresseBesitzer(m_IDAdresseBesitzerZiel);
		
		
		// Sorte
		oAtom.setIdArtikel(oOrt.get_ID_ARTIKEL_lValue(null));
		oAtom.setIdArtikelBez(oOrt.get_ID_ARTIKEL_BEZ_lValue(null));
		oAtom.setArtbez1(oOrt.get_ARTBEZ1_cUF()); 
		oAtom.setArtbez2(oOrt.get_ARTBEZ2_cUF());
		
		// Menge abhaengig vom Schalter
		oAtom.setPlanmenge(oOrt.get_ANTEIL_PLANMENGE_bdValue(null));
		
		BigDecimal bdMenge = null;
		if (oOrt.get_ABLADEMENGE_RECHNUNG_cUF_NN("-").equalsIgnoreCase("Y")){
			bdMenge = oOrt.get_ANTEIL_ABLADEMENGE_bdValue(null);
		} else {
			bdMenge = oOrt.get_ANTEIL_LADEMENGE_bdValue(null);
		}
		oAtom.setMenge(bdMenge);
		
		oAtom.setAbzugMenge(oOrt.get_ABZUG_MENGE_bdValue(null));
		
		oAtom.setPruefungMenge(oOrt.get_PRUEFUNG_MENGE_cUF());
		oAtom.setPruefungMengeAm(bibDate.String2Date(oOrt.get_PRUEFUNG_MENGE_AM_cUF()));
		oAtom.setPruefungMengeVon(oOrt.get_PRUEFUNG_MENGE_VON_cUF());

		// Preisinformationen
		oAtom.setEPreis(oOrt.get_EINZELPREIS_bdValue(null));
		oAtom.setEPreisResultNettoMge(oOrt.get_EPREIS_RESULT_NETTO_MGE_bdValue(null));

		//Daten aus der Rechnungsposition
		setRGValuesInAtomFromFuhreOrt(oAtom, oOrt);
	
		// Steuerinfo
		oAtom.setEuSteuerVermerk(oOrt.get_EU_STEUER_VERMERK_cUF());
		oAtom.setSteuersatz(oOrt.get_STEUERSATZ_bdValue(null));

		// Buchungsnummer
		oAtom.setBuchungsnummer(oOrt.get_BUCHUNGSNUMMER_ZUSATZ_cUF());
		
		// Bemerkungsfelder
		oAtom.setBemerkung(oOrt.get_BEMERKUNG_cUF());
		oAtom.setBemerkungSachbearbeiter(null);

		//Löschinformationen
		oAtom.setDeleted(oVec_ori.getDeleted().sValue);
		oAtom.setDelDate(bibDate.String2Date(oOrt.get_DEL_DATE_cUF())) ;
		oAtom.setDelGrund(oOrt.get_DEL_GRUND_cUF());
		oAtom.setDelKuerzel(oOrt.get_DEL_KUERZEL_cUF());
		
		// Angebotsinfo
		oAtom.setIdVposStd(oOrt.get_ID_VPOS_STD_lValue(null));
		
		
		// Kontraktinfos
		oAtom.setIdVposKon(oOrt.get_ID_VPOS_KON_lValue(null));
		oAtom.setKontraktzwang(oOrt.get_KEIN_KONTRAKT_NOETIG_cUF());
		oAtom.setKontraktzwangAusAm(null);
		oAtom.setKontraktzwangAusGrund(null);
		oAtom.setKontraktzwangAusVon(null);
		
		// Storno
		oAtom.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
		oAtom.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null );
		oAtom.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
		oAtom.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
		

		// sonstiges
		oAtom.setLieferinfoTpa(null);
		oAtom.setNationalerAbfallCode(oOrt.get_NATIONALER_ABFALL_CODE_cUF());
		oAtom.setNotifikationNr(oOrt.get_NOTIFIKATION_NR_cUF());
		oAtom.setZeitstempel(oOrt.get_ZEITSTEMPEL_cUF());
		oAtom.setOrdnungsnummerCmr(null);
		oAtom.setPostennummer(oOrt.get_POSTENNUMMER_cUF());
		oAtom.setPreisermittlung(null);
		oAtom.setSetzkastenKomplett(null);

		vecPos.getJtBewegungsatoms().add(oAtom);

		if (generateDummyEntry){
			JtBewegung_Atom oAtomFuell = new JtBewegung_Atom();
			oAtomFuell.setPosnr(new Long(2));
			
			oAtomFuell.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
			oAtomFuell.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
			oAtomFuell.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
			oAtomFuell.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
			
			oAtomFuell.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
			oAtomFuell.setIdVposTpaFuhre(oAtom.getIdVposTpaFuhre().lValue);
			
			
			// userinfo
			oAtomFuell.setErzeugtAm(bibDate.String2Date(oOrt.get_ERZEUGT_AM_cUF()));
			oAtomFuell.setErzeugtVon(oOrt.get_ERZEUGT_VON_cUF());
			oAtomFuell.setGeaendertVon(oOrt.get_GEAENDERT_VON_cUF());
			oAtomFuell.setLetzteAenderung(bibDate.String2Date(oOrt.get_LETZTE_AENDERUNG_cUF()));
			
			oAtomFuell.setAbgerechnet(null);
			oAtomFuell.setAbrechenbar(null);
			
			
			// Start
			
			JtBewegung_Station oStartFuell = new JtBewegung_Station(oAtomFuell, -1);
			oStartFuell.setIDAdresse( bibSES.get_ID_AdresseLAGER_longValue(enumSonderlager) );
			oStartFuell.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
			
			
			// Ziel
			JtBewegung_Station oZielFuell = new JtBewegung_Station(oAtomFuell, +1);
			oZielFuell.setIDAdresse(oFuhre.get_ID_ADRESSE_LAGER_ZIEL_lValue(null));
			oZielFuell.setIDAdresseBasis(oFuhre.get_ID_ADRESSE_ZIEL_lValue(null));
			oZielFuell.setFax(oFuhre.get_FAX_ABNEHMER_cF());
			oZielFuell.setHausnummer(oFuhre.get_A_HAUSNUMMER_cUF());
			oZielFuell.setLaendercode(oFuhre.get_A_LAENDERCODE_cUF());
			oZielFuell.setName1(oFuhre.get_A_NAME1_cUF());
			oZielFuell.setName2(oFuhre.get_A_NAME2_cUF());
			oZielFuell.setName3(oFuhre.get_A_NAME3_cUF());
			oZielFuell.setOeffnungszeiten(oFuhre.get_OEFFNUNGSZEITEN_ABN_cUF());
			oZielFuell.setOrt(oFuhre.get_A_ORT_cUF());
			oZielFuell.setOrtzusatz(oFuhre.get_A_ORTZUSATZ_cUF());
			oZielFuell.setPlz(oFuhre.get_A_PLZ_cUF());
			oZielFuell.setStrasse(oFuhre.get_A_STRASSE_cUF());
			oZielFuell.setTelefon(oFuhre.get_TEL_ABNEHMER_cUF());
			oZielFuell.setBestellnummer(oFuhre.get_BESTELLNUMMER_VK_cUF());
			oZielFuell.setKontrollmenge(BigDecimal.ZERO);
			oZielFuell.setWiegekartenKenner(null);
			oZielFuell.setIDWiegekarte(null);
			
			
			
			// Datum
			oAtomFuell.setLeistungsdatum(bibDate.String2Date(oOrt.get_DATUM_LADE_ABLADE_cUF()));
			
			
			// Besitzverhältnisse
			// EIGENWARE/FREMDWARE/LEERGUT
			oZielFuell.setIDAdresseBesitzer(oZiel.getIDAdresseBesitzer().lValue);
			oStartFuell.setIDAdresseBesitzer(oZiel.getIDAdresseBesitzer().lValue);
			
			
			oAtomFuell.setAbrechenbar("N");
			
			// Sorte
			oAtomFuell.setIdArtikel(oOrt.get_ID_ARTIKEL_lValue(null));
			oAtomFuell.setIdArtikelBez(oOrt.get_ID_ARTIKEL_BEZ_lValue(null));
			oAtomFuell.setArtbez1(oOrt.get_ARTBEZ1_cUF()); 
			oAtomFuell.setArtbez2(oOrt.get_ARTBEZ2_cUF());
			
			// Menge abhaengig vom Schalter
			BigDecimal bdMengeFuell = BigDecimal.ZERO;
			oAtomFuell.setPlanmenge(bdMengeFuell);
			oAtomFuell.setMenge(bdMengeFuell);
			oAtomFuell.setMengeNetto(bdMengeFuell);
			oAtomFuell.setAbzugMenge(BigDecimal.ZERO);
			
			// Preisinformationen
			oAtomFuell.setEPreis(oOrt.get_EINZELPREIS_bdValue(null));
			oAtomFuell.setEPreisResultNettoMge(oOrt.get_EPREIS_RESULT_NETTO_MGE_bdValue(null));
			
			//Löschinformationen
			oAtomFuell.setDeleted(oVec_ori.getDeleted().sValue);
			oAtomFuell.setDelDate(bibDate.String2Date(oOrt.get_DEL_DATE_cUF())) ;
			oAtomFuell.setDelGrund(oOrt.get_DEL_GRUND_cUF());
			oAtomFuell.setDelKuerzel(oOrt.get_DEL_KUERZEL_cUF());
			
			// Storno
			oAtomFuell.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
			oAtomFuell.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null );
			oAtomFuell.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
			oAtomFuell.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
			
			
			// sonstiges
			oAtomFuell.setZeitstempel(oOrt.get_ZEITSTEMPEL_cUF());
			
			vecPos.getJtBewegungsatoms().add(oAtomFuell);
		}

		
		return vecPos;
	}

	
	
	/**
	 * generiert einen Fuhrenort auf der Abladeseite.
	 * Kommt immer aus dem Mixed-Lager
	 * 
	 * 
	 * @author manfred
	 * @date 17.01.2017
	 *
	 * @param oVec_ori
	 * @param oFuhre
	 * @param oOrt
	 * @param posnrVektor
	 * @return
	 * @throws myException
	 */
	protected JtBewegung_Vektor_Pos generiereVektorPosUndAtome_FuhrenORT_Abladeseite (JtBewegung_Vektor oVec_ori,  RECORD_VPOS_TPA_FUHRE oFuhre, RECORD_VPOS_TPA_FUHRE_ORT oOrt, long posnrVektorPos, ENUM_SONDERLAGER enumSonderlager, boolean generateDummyEntry) throws myException{
		
		JtBewegung_Vektor_Pos vecPos = null;
		vecPos = new JtBewegung_Vektor_Pos(oVec_ori);
		oVec_ori.getJtBewegungVektorPoss().add(vecPos);
		
		vecPos.setPosnr(posnrVektorPos);
		Long posnr = 1L;
		
		if (generateDummyEntry){
			
			JtBewegung_Atom oAtomFuell = new JtBewegung_Atom();
			oAtomFuell.setPosnr(posnr++);
			
			oAtomFuell.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
			oAtomFuell.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
			oAtomFuell.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
			oAtomFuell.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
			oAtomFuell.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
			
			oAtomFuell.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(null));

			
			// userinfo
			oAtomFuell.setErzeugtAm(bibDate.String2Date(oOrt.get_ERZEUGT_AM_cUF()));
			oAtomFuell.setErzeugtVon(oOrt.get_ERZEUGT_VON_cUF());
			oAtomFuell.setGeaendertVon(oOrt.get_GEAENDERT_VON_cUF());
			oAtomFuell.setLetzteAenderung(bibDate.String2Date(oOrt.get_LETZTE_AENDERUNG_cUF()));
			
			oAtomFuell.setAbgerechnet(null);
			oAtomFuell.setAbrechenbar(null);
			
			
			// Start
			JtBewegung_Station oStartFuell = new JtBewegung_Station(oAtomFuell, -1);
			oStartFuell.setIDAdresse(oFuhre.get_ID_ADRESSE_LAGER_START_lValue(null));
			oStartFuell.setIDAdresseBasis(oFuhre.get_ID_ADRESSE_START_lValue(null));
			oStartFuell.setFax(oFuhre.get_FAX_LIEFERANT_cUF());
			oStartFuell.setHausnummer(oFuhre.get_L_HAUSNUMMER_cUF());
			oStartFuell.setLaendercode(oFuhre.get_L_LAENDERCODE_cUF());
			oStartFuell.setName1(oFuhre.get_L_NAME1_cUF());
			oStartFuell.setName2(oFuhre.get_L_NAME2_cUF());
			oStartFuell.setName3(oFuhre.get_L_NAME3_cUF());
			oStartFuell.setOeffnungszeiten(oFuhre.get_OEFFNUNGSZEITEN_LIEF_cUF());
			oStartFuell.setOrt(oFuhre.get_L_ORT_cUF());
			oStartFuell.setOrtzusatz(oFuhre.get_L_ORTZUSATZ_cUF());
			oStartFuell.setPlz(oFuhre.get_L_PLZ_cUF());
			oStartFuell.setStrasse(oFuhre.get_L_STRASSE_cUF());
			oStartFuell.setTelefon(oFuhre.get_TEL_LIEFERANT_cUF());
			oStartFuell.setBestellnummer(oFuhre.get_BESTELLNUMMER_EK_cUF());
			oStartFuell.setKontrollmenge(BigDecimal.ZERO);
			oStartFuell.setWiegekartenKenner(null);
			oStartFuell.setIDWiegekarte(null);
			oStartFuell.setIDAdresseBesitzer(m_IDAdresseMandant);
			
			
			
			// Ziel
			JtBewegung_Station oZielFuell = new JtBewegung_Station(oAtomFuell, +1);
			oZielFuell.setIDAdresse(bibSES.get_ID_AdresseLAGER_longValue(enumSonderlager));
			
			oZielFuell.setIDAdresseBasis(m_IDAdresseMandant);
			oZielFuell.setIDAdresseBesitzer(m_IDAdresseMandant);
			
			
			// Datum
			oAtomFuell.setLeistungsdatum(bibDate.String2Date(oOrt.get_DATUM_LADE_ABLADE_cUF()));
			
			
			// Besitzverhältnisse
			// EIGENWARE/FREMDWARE/LEERGUT wird erst im 2. Atom gesetzt
			
			
			
			oAtomFuell.setAbrechenbar("N");
			
			// Sorte
			oAtomFuell.setIdArtikel(oOrt.get_ID_ARTIKEL_lValue(null));
			oAtomFuell.setIdArtikelBez(oOrt.get_ID_ARTIKEL_BEZ_lValue(null));
			oAtomFuell.setArtbez1(oOrt.get_ARTBEZ1_cUF()); 
			oAtomFuell.setArtbez2(oOrt.get_ARTBEZ2_cUF());
			
			// Menge abhaengig vom Schalter
			BigDecimal bdMengeFuell = BigDecimal.ZERO;
			oAtomFuell.setPlanmenge(bdMengeFuell);
			oAtomFuell.setMenge(bdMengeFuell);
			oAtomFuell.setAbzugMenge(BigDecimal.ZERO);
			
			// Preisinformationen
			oAtomFuell.setEPreis(oOrt.get_EINZELPREIS_bdValue(null));
			oAtomFuell.setEPreisResultNettoMge(oOrt.get_EPREIS_RESULT_NETTO_MGE_bdValue(null));
			
			//Löschinformationen
			oAtomFuell.setDeleted(oVec_ori.getDeleted().sValue);
			oAtomFuell.setDelDate(bibDate.String2Date(oOrt.get_DEL_DATE_cUF())) ;
			oAtomFuell.setDelGrund(oOrt.get_DEL_GRUND_cUF());
			oAtomFuell.setDelKuerzel(oOrt.get_DEL_KUERZEL_cUF());
			
			// Storno
			oAtomFuell.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
			oAtomFuell.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null );
			oAtomFuell.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
			oAtomFuell.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
			
			
			// sonstiges
			oAtomFuell.setZeitstempel(oOrt.get_ZEITSTEMPEL_cUF());
			
			vecPos.getJtBewegungsatoms().add(oAtomFuell);
			
			
		}
		
		
		
		
		
		//
		// Bewegungsatom
		//
		JtBewegung_Atom oAtom = new JtBewegung_Atom();

		// Referenzen und IDS
		oAtom.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		oAtom.setJtBewegung(m_Bewegung);
		
		oAtom.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
		oAtom.setJtBewegungsvektor(oVec_ori);
		
		oAtom.setJtBewegungsvektorPos(vecPos);
		oAtom.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");

		oAtom.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		oAtom.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		
		oAtom.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(null));

		
		// userinfo
		oAtom.setErzeugtAm(bibDate.String2Date(oOrt.get_ERZEUGT_AM_cUF()));
		oAtom.setErzeugtVon(oOrt.get_ERZEUGT_VON_cUF());
		oAtom.setGeaendertVon(oOrt.get_GEAENDERT_VON_cUF());
		oAtom.setLetzteAenderung(bibDate.String2Date(oOrt.get_LETZTE_AENDERUNG_cUF()));

		oAtom.setPosnr(posnr ++);
		
		oAtom.setAbgerechnet(null);
		oAtom.setAbrechenbar(null);
		
		// Startstation
		JtBewegung_Station oStart = new JtBewegung_Station(oAtom, -1);
		oStart.setIDAdresse(bibSES.get_ID_AdresseLAGER_longValue(enumSonderlager) );
		oStart.setIDAdresseBasis( m_IDAdresseMandant);
		oStart.setIDAdresseBesitzer(m_IDAdresseMandant);
	
	
		// Zielstation
		JtBewegung_Station oZiel = new JtBewegung_Station(oAtom, +1);
		oZiel.setIDAdresse(oOrt.get_ID_ADRESSE_LAGER_lValue(null));
		oZiel.setIDAdresseBasis(oOrt.get_ID_ADRESSE_lValue(null));
		oZiel.setBestellnummer(oOrt.get_BESTELLNUMMER_cUF());
		oZiel.setFax(oOrt.get_FAX_cUF());
		oZiel.setHausnummer(oOrt.get_HAUSNUMMER_cUF());
		oZiel.setLaendercode(oOrt.get_LAENDERCODE_cUF());
		oZiel.setName1(oOrt.get_NAME1_cUF());
		oZiel.setName2(oOrt.get_NAME2_cUF());
		oZiel.setName3(oOrt.get_NAME3_cUF());
		oZiel.setOeffnungszeiten(oOrt.get_OEFFNUNGSZEITEN_cUF());
		oZiel.setOrt(oOrt.get_ORT_cUF());
		oZiel.setOrtzusatz(oOrt.get_ORTZUSATZ_cUF());
		oZiel.setPlz(oOrt.get_PLZ_cUF());
		oZiel.setStrasse(oOrt.get_STRASSE_cUF());
		oZiel.setTelefon(oOrt.get_TEL_cUF());
		oZiel.setWiegekartenKenner(oOrt.get_WIEGEKARTENKENNER_cUF());
		oZiel.setIDWiegekarte(oOrt.get_ID_WIEGEKARTE_lValue(null));
		oZiel.setKontrollmenge(oOrt.get_ANTEIL_LADEMENGE_bdValue(null));

		
		// Datum
		oAtom.setLeistungsdatum(bibDate.String2Date(oOrt.get_DATUM_LADE_ABLADE_cUF()));
	
		
		// Besitzverhältnisse
		// EIGENWARE/FREMDWARE/LEERGUT
		
		if (oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_bdValue(null) != null){
			// bei Fremdauftrag immer der Auftraggeber
			m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_FREMDAUFTRAG_lValue(null);
			m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
		} else {
			if (oOrt.get_OHNE_ABRECHNUNG_cUF_NN("N").equalsIgnoreCase("Y") ){
				String sIDArtikel = oOrt.get_ID_ARTIKEL_lValue((long)-1).toString();
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					m_IDAdresseBesitzerStart = m_IDAdresseMandant;
					m_IDAdresseBesitzerZiel = m_IDAdresseMandant;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					m_IDAdresseBesitzerStart = oOrt.get_ID_ADRESSE_lValue(null);
					m_IDAdresseBesitzerZiel = m_IDAdresseBesitzerStart;
				}
			} else {
				// Eigenware / nur abrechenbar, wenn der Ort ein Kunde ist....
				m_IDAdresseBesitzerZiel = oOrt.get_ID_ADRESSE_lValue(null);

				if( !m_vLagerOrte.contains(oOrt.get_ID_ADRESSE_LAGER_cUF_NN("*"))){
					m_IDAdresseBesitzerStart = oFuhre.get_ID_ADRESSE_START_lValue(null);
				} else {
					m_IDAdresseBesitzerStart = m_IDAdresseMandant;
				}
			}
		}
		
		oAtom.setAbrechenbar(m_IDAdresseBesitzerStart.equals(m_IDAdresseBesitzerZiel) ? "N" : "Y");

		oStart.setIDAdresseBesitzer(m_IDAdresseBesitzerStart);
		oZiel.setIDAdresseBesitzer(m_IDAdresseBesitzerZiel);
		
		// setzen des Besitzers des Pseudo-Atoms
//		oStartFuell.setIDAdresseBesitzer(m_IDAdresseBesitzerStart);
//		oZielFuell.setIDAdresseBesitzer(m_IDAdresseBesitzerStart);
		
		
		// Sorte
		oAtom.setIdArtikel(oOrt.get_ID_ARTIKEL_lValue(null));
		oAtom.setIdArtikelBez(oOrt.get_ID_ARTIKEL_BEZ_lValue(null));
		oAtom.setArtbez1(oOrt.get_ARTBEZ1_cUF()); 
		oAtom.setArtbez2(oOrt.get_ARTBEZ2_cUF());
		
		// Menge abhaengig vom Schalter
		oAtom.setPlanmenge(oOrt.get_ANTEIL_PLANMENGE_bdValue(null));
		BigDecimal bdMenge = null;
		if (oOrt.get_LADEMENGE_GUTSCHRIFT_cUF_NN("-").equalsIgnoreCase("Y")){
			bdMenge = oOrt.get_ANTEIL_LADEMENGE_bdValue(null);
		} else {
			bdMenge = oOrt.get_ANTEIL_ABLADEMENGE_bdValue(null);
		}
		oAtom.setMenge(bdMenge);
		
		oAtom.setAbzugMenge(oOrt.get_ABZUG_MENGE_bdValue(null));

		// Mengenprüfung
		oAtom.setPruefungMenge(oOrt.get_PRUEFUNG_MENGE_cUF());
		oAtom.setPruefungMengeAm(bibDate.String2Date(oOrt.get_PRUEFUNG_MENGE_AM_cUF()));
		oAtom.setPruefungMengeVon(oOrt.get_PRUEFUNG_MENGE_VON_cUF());

		// Preisinformationen
		oAtom.setEPreis(oOrt.get_EINZELPREIS_bdValue(null));
		oAtom.setEPreisResultNettoMge(oOrt.get_EPREIS_RESULT_NETTO_MGE_bdValue(null));
		
		//Daten aus der Rechnungsposition
		setRGValuesInAtomFromFuhreOrt(oAtom, oOrt);
		
		// Steuerinfo
		oAtom.setEuSteuerVermerk(oOrt.get_EU_STEUER_VERMERK_cUF());
		oAtom.setSteuersatz(oOrt.get_STEUERSATZ_bdValue(null));

		// Buchungsnummer
		oAtom.setBuchungsnummer(oOrt.get_BUCHUNGSNUMMER_ZUSATZ_cUF());
		

		// Bemerkungsfelder
		oAtom.setBemerkung(oOrt.get_BEMERKUNG_cUF());
		oAtom.setBemerkungSachbearbeiter(null);

		//Löschinformationen
		oAtom.setDeleted(oVec_ori.getDeleted().sValue);
		oAtom.setDelDate(bibDate.String2Date(oOrt.get_DEL_DATE_cUF())) ;
		oAtom.setDelGrund(oOrt.get_DEL_GRUND_cUF());
		oAtom.setDelKuerzel(oOrt.get_DEL_KUERZEL_cUF());
		
		// Angebotsinfo
		oAtom.setIdVposStd(oOrt.get_ID_VPOS_STD_lValue(null));
		
		// Kontraktinfos
		oAtom.setIdVposKon(oOrt.get_ID_VPOS_KON_lValue(null));
		oAtom.setKontraktzwang(oOrt.get_KEIN_KONTRAKT_NOETIG_cUF());
		oAtom.setKontraktzwangAusAm(null);
		oAtom.setKontraktzwangAusGrund(null);
		oAtom.setKontraktzwangAusVon(null);
		
		// Storno
		oAtom.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
		oAtom.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null);
		oAtom.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
		oAtom.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
		
		// sonstiges
		oAtom.setLieferinfoTpa(null);
		oAtom.setNationalerAbfallCode(oOrt.get_NATIONALER_ABFALL_CODE_cUF());
		oAtom.setNotifikationNr(oOrt.get_NOTIFIKATION_NR_cUF());
		oAtom.setZeitstempel(oOrt.get_ZEITSTEMPEL_cUF());
		oAtom.setOrdnungsnummerCmr(null);
		oAtom.setPostennummer(oOrt.get_POSTENNUMMER_cUF());
		oAtom.setPreisermittlung(null);
		oAtom.setSetzkastenKomplett(null);

		vecPos.getJtBewegungsatoms().add(oAtom);
		
		return vecPos;
	}

	
	
	
	
	public Vector<String> getSQLTransformation(){
		return new Vector<String>();
	}
	
	
}
