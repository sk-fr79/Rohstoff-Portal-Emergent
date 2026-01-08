package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BL_FUHREN_Transformation_STRECKE extends BL_FUHREN_Transformation {


	private long posNrVektorInBewegung = 1L;
	private long posNrVektorPosInVektor = 1L;
	private long posNrAtomInVektorPos = 1L;
	
	
	
	public BL_FUHREN_Transformation_STRECKE(Vector<String> vLagerOrte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel, HashMap<String, String> hmLieferbedingungen) throws myException {
		super(vLagerOrte,objUmrechnung,hmArtikeBezZuordnung,vLeergutartikel,hmLieferbedingungen);
		m_typ_strecke_lager_mixed = 0;
	}


	public JtBewegung transformiereFuhre(RECORD_VPOS_TPA_FUHRE oRecFuhre) throws myException {
		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
		
		posNrVektorInBewegung = 1;

		// generiere den Bewegungssatz und übernehme die Daten
		m_Bewegung = new JtBewegung(oRecFuhre);
		

		//
		// Atom für Hauptfuhre
		//
		posNrAtomInVektorPos = (long)1;

		//
		// Vectoren für Haupfuhre
		//
		JtBewegung_Vektor vecFuhre = generiere_Vektor_Hauptfuhre(oRecFuhre, posNrVektorInBewegung++);
		vecFuhre.setVariante(ENUM_VEKTOR_TYP.ST.name());
		
		posNrVektorPosInVektor = vecFuhre.getJtBewegungVektorPoss().size();
		posNrVektorPosInVektor++;
		
		// den Vektor der Fuhre in Bewegung einfügen
		m_Bewegung.getJtBewegungsvektors().add(vecFuhre);
		
		JtBewegung_Vektor_Pos vecPosOrt = null;
		
		
		// Atome der Hauptfuhre ermitteln
		JtBewegung_Atom oAtomFuhreWE = vecFuhre.getJtBewegungVektorPoss().firstElement().getJtBewegungsatoms().firstElement();
		JtBewegung_Atom oAtomFuhreWA = vecFuhre.getJtBewegungVektorPoss().firstElement().getJtBewegungsatoms().lastElement();
		
		
		
		// n-Vektoren für den WE der Orte
		JtBewegung_Atom oAtom = null;
		for (RECORD_VPOS_TPA_FUHRE_ORT ort : m_rlOrt.values()){
			oAtom = null;
			if (ort.get_DEF_QUELLE_ZIEL_cUF_NN("-").equals("EK")){
				// WE
				vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Ladeseite(vecFuhre, m_oFuhre, ort, posNrVektorPosInVektor++, ENUM_SONDERLAGER.ZWE,true);
				
				// 1. Atom: vom Kunde ins Streckenlager
				oAtom =  vecPosOrt.getJtBewegungsatoms().firstElement();

				// falls die Fuhre Abrechenbar ist, ist der Besitzer der Ware in der Strecke der Mandant
				if (oAtomFuhreWE.getAbrechenbar().sValue.equalsIgnoreCase("Y")){
					oAtom.getBewegungsstationZiel().setIDAdresseBesitzer(m_IDAdresseMandant);
				}
				oAtom.setAbrechenbar(oAtom.getBewegungsstationZiel().getIDAdresseBesitzer().lValue.equals(oAtom.getBewegungsstationStart().getIDAdresseBesitzer().lValue) ? "N" : "Y");
				
				
				// Füllatom mit Menge und Preis befüllen
				JtBewegung_Atom oAtom1 = vecPosOrt.getJtBewegungsatoms().lastElement();
				oAtom1.getBewegungsstationZiel().setIDAdresse( bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
				
				oAtom1.setMenge(oAtom.getMenge().bdValue);
				oAtom1.setMengeNetto(oAtom.getMengeNetto().bdValue);
				oAtom1.setAbzugMenge(oAtom.getAbzugMenge().bdValue);
				
				oAtom1.setEPreis(oAtom.getEPreis().bdValue);
				oAtom1.setEPreisResultNettoMge(oAtom.getEPreisResultNettoMge().bdValue);
				oAtom1.setEPreisResultBruttoMenge(oAtom.getEPreisResultBruttoMenge().bdValue);
				
				
			} else {
				
				// WA
				vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Abladeseite(vecFuhre, m_oFuhre, ort, posNrVektorPosInVektor++, ENUM_SONDERLAGER.ZWA,true);

				//1. Atom: Füllatom
				JtBewegung_Atom oAtom1 = vecPosOrt.getJtBewegungsatoms().firstElement();
				oAtom1.getBewegungsstationStart().setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_STRECKE() ));
				oAtom1.getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
				
				//2. Atom: 
				oAtom =  vecPosOrt.getJtBewegungsatoms().lastElement();

				
				// Füllatom mit Menge und Preis befüllen
				oAtom1.setMenge(oAtom.getMenge().bdValue);
				oAtom1.setMengeNetto(oAtom.getMengeNetto().bdValue);
				oAtom1.setAbzugMenge(oAtom.getAbzugMenge().bdValue);
				oAtom1.setEPreis(oAtom.getEPreis().bdValue);
				oAtom1.setEPreisResultNettoMge(oAtom.getEPreisResultNettoMge().bdValue);
				oAtom1.setEPreisResultBruttoMenge(oAtom.getEPreisResultBruttoMenge().bdValue);
				
				
				// falls die Fuhre Abrechenbar ist, ist der Besitzer der Ware in der Strecke der Mandant
				// TODO: prüfen, ob wa oder we
				if (oAtomFuhreWA.getAbrechenbar().sValue.equalsIgnoreCase("Y")){
					oAtom.getBewegungsstationStart().setIDAdresseBesitzer(m_IDAdresseMandant);
				}
				
				oAtom.setAbrechenbar(oAtom.getBewegungsstationZiel().getIDAdresseBesitzer().lValue.equals(oAtom.getBewegungsstationStart().getIDAdresseBesitzer().lValue) ? "N" : "Y");

			}
		}
			
		return m_Bewegung;

		
	}
	

	

	
	
	protected JtBewegung_Vektor generiere_Vektor_Hauptfuhre (RECORD_VPOS_TPA_FUHRE oFuhre, long posnrVektor) throws myException{
		
		JtBewegung_Vektor vecFuhre = generiereVectorAusFuhre_Basisdaten(oFuhre); 
		vecFuhre.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		vecFuhre.setJtBewegung(m_Bewegung);
		vecFuhre.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		vecFuhre.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		vecFuhre.setPosnr(posnrVektor);
		
		// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
		boolean bStartIsLager =  m_vLagerOrte.contains(oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("*")) ;
		boolean bZielIsLager =  m_vLagerOrte.contains(oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("*")) ;
		
		// 3. Vektorpos mit jeweils 2 Atomen für den WE in die Strecke
		JtBewegung_Vektor_Pos vecPosHauptfuhre1 = generiere_VPos_Hauptfuhre(oFuhre, vecFuhre, 1L,ENUM_SONDERLAGER.ZWE);
		JtBewegung_Vektor_Pos vecPosHauptfuhre2 = generiere_VPos_Hauptfuhre(oFuhre, vecFuhre, 1L,ENUM_SONDERLAGER.ZWA);
		
		// in der ersten Vectorpos brauchen wir das 1. Atom und duplizieren dies zum 2. Atom und passen die Stationen an
		JtBewegung_Atom copy1 = vecPosHauptfuhre1.getJtBewegungsatoms().firstElement().get_copy();
		copy1.setPosnr(2L);
		JtBewegung_Station s1_1 = new JtBewegung_Station(copy1, -1);   // copy1.getJtBewegungstations().firstElement();
		s1_1.setIDAdresseBasis(m_IDAdresseMandant);
		s1_1.setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE_longValue());
		s1_1.setIDAdresseBesitzer(m_IDAdresseMandant);
		
		JtBewegung_Station s1_2 = new JtBewegung_Station(copy1, 1); //copy1.getJtBewegungstations().lastElement();
		s1_2.setIDAdresseBasis(m_IDAdresseMandant);
		s1_2.setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
		s1_2.setIDAdresseBesitzer(m_IDAdresseMandant);
		
		vecPosHauptfuhre1.getJtBewegungsatoms().remove(1);
		vecPosHauptfuhre1.getJtBewegungsatoms().add(1, copy1);
		
		
		
		
		// in der zweiten Vectorpos brauchen wir das 2. Atom und duplizieren dies zum 1. Atom und passen die Stationen an
		JtBewegung_Atom copy2 = vecPosHauptfuhre2.getJtBewegungsatoms().lastElement().get_copy();
		copy2.setPosnr(1L);
		
		JtBewegung_Station s2_1 =   new JtBewegung_Station(copy2, -1);
		s2_1.setIDAdresseBasis(m_IDAdresseMandant);
		s2_1.setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
		s2_1.setIDAdresseBesitzer(m_IDAdresseMandant);
		
		JtBewegung_Station s2_2 = new JtBewegung_Station(copy2, 1); 
		s2_2.setIDAdresseBasis(m_IDAdresseMandant);
		s2_2.setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA_longValue());
		s2_2.setIDAdresseBesitzer(m_IDAdresseMandant);
		
		vecPosHauptfuhre2.getJtBewegungsatoms().remove(0);
		vecPosHauptfuhre2.getJtBewegungsatoms().add(0, copy2);
		
		// atome nummerieren
		Long posnr = 1L;
		vecPosHauptfuhre1.setPosnr(1L);
		vecPosHauptfuhre1.getJtBewegungsatoms().get(0).setPosnr(1L);
		vecPosHauptfuhre1.getJtBewegungsatoms().get(1).setPosnr(2L);
		vecPosHauptfuhre2.setPosnr(2L);
		vecPosHauptfuhre2.getJtBewegungsatoms().get(0).setPosnr(1L);
		vecPosHauptfuhre2.getJtBewegungsatoms().get(1).setPosnr(2L);
		
		
		
		// Besitzverhältnisse ordnen
		JtBewegung_Atom aAb ; 
		Long idBesitzerStart ;
		Long idBesitzerZiel  ;
		
		// 1. Atom
		aAb =vecPosHauptfuhre1.getJtBewegungsatoms().get(0);
		idBesitzerStart = aAb.getBewegungsstationStart().getIDAdresseBesitzer().lValue;
		idBesitzerZiel  = aAb.getBewegungsstationZiel().getIDAdresseBesitzer().lValue;
		aAb.setAbrechenbar((idBesitzerStart.equals(idBesitzerZiel) ? "N":"Y") );
		
		// 2. Atom
		aAb =vecPosHauptfuhre1.getJtBewegungsatoms().get(1);
		idBesitzerStart = aAb.getBewegungsstationStart().getIDAdresseBesitzer().lValue;
		idBesitzerZiel  = aAb.getBewegungsstationZiel().getIDAdresseBesitzer().lValue;
		aAb.setAbrechenbar((idBesitzerStart.equals(idBesitzerZiel) ? "N":"Y") );
		
		// 3. Atom
		aAb =vecPosHauptfuhre2.getJtBewegungsatoms().get(0);
		idBesitzerStart = aAb.getBewegungsstationStart().getIDAdresseBesitzer().lValue;
		idBesitzerZiel  = aAb.getBewegungsstationZiel().getIDAdresseBesitzer().lValue;
		aAb.setAbrechenbar((idBesitzerStart.equals(idBesitzerZiel) ? "N":"Y") );
		
		// 4. Atom
		aAb =vecPosHauptfuhre2.getJtBewegungsatoms().get(1);
		idBesitzerStart = aAb.getBewegungsstationStart().getIDAdresseBesitzer().lValue;
		idBesitzerZiel  = aAb.getBewegungsstationZiel().getIDAdresseBesitzer().lValue;
		aAb.setAbrechenbar((idBesitzerStart.equals(idBesitzerZiel) ? "N":"Y") );
				
		return vecFuhre;
		
	}


		
	/**
	 * Erzeugen der Bewegungsatome für die Strecke
	 * VecPOS 1:
	 * 			Atom 1: KD - ZWE  
	 *          Atom 2: ZWE - STRECKE
	 * 
	 * VecPOS 2:
	 * 			Atom 3: STRECKE - ZWA  
	 *          Atom 4: ZWA - KUNDE
	 * 
	 * 
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
		
		oAtom.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(0L));
		
		oAtom.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
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
		
		oAtom1.setIdVposTpaFuhre(oFuhre.get_ID_VPOS_TPA_FUHRE_lValue(0L));
		
		oAtom1.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
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
		
		// das 1. Atom in den Vektor eintragen
		vecPos.getJtBewegungsatoms().add(oAtom1);

		
		return vecPos;
		
	}
	
	
	

	
}
