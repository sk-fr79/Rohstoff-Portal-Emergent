package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;



public class BL_HAND_Transformation {
	
	Vector<String> m_vLagerOrte = null;
	
	GROESSEN_Umrechnung m_oUmrechnung = null;
	
	HashMap<String, RECORD_ARTIKEL_BEZ> m_hmArtikelBez_zu_Artikel = null;
	
	/**
	 * Default-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   08.08.2012
	 */
	public BL_HAND_Transformation(Vector<String>Lagerorte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, RECORD_ARTIKEL_BEZ> hmArtikeBezZuordnung) throws myException {
		super();
		m_vLagerOrte = Lagerorte;
		m_oUmrechnung = objUmrechnung;
		m_hmArtikelBez_zu_Artikel = hmArtikeBezZuordnung;
	}



	

	/**
	 * Generieren eines Vektors mit Atom für eine einfache Korrekturbuchung
	 * @author manfred
	 * @date   24.09.2012
	 * @param oRec
	 * @param posnrAtomInVector
	 * @return
	 * @throws myException
	 */
	protected JtBewegung generiere_Bewegung_Korrketurbuchung (RECORD_LAGER_KONTO oRec) throws myException{

		JtBewegung			oBew = new JtBewegung();
		JtBewegung_Vektor 	oVec = new JtBewegung_Vektor();
		
		// Bewegung 
		oBew.setIdMandant(oRec.get_ID_MANDANT_lValue(null));
		oBew.setErzeugtAm(bibDate.String2Date(oRec.get_ERZEUGT_AM_cUF()));
		oBew.setErzeugtVon(oRec.get_ERZEUGT_VON_cUF());
		oBew.setGeaendertVon(oRec.get_GEAENDERT_VON_cUF());
		oBew.setLetzteAenderung(bibDate.String2Date(oRec.get_LETZTE_AENDERUNG_cUF()));
		oBew.setIstLagerbuchungAlt("Y");
		oBew.setIdBewegung("SEQ_BEWEGUNG.NEXTVAL");
		
		oBew.getJtBewegungsvektors().add(oVec);

		
		
		String idBuchung = oRec.get_ID_LAGER_KONTO_cUF();
		
		oVec.setJtBewegung(oBew);
		oVec.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		oVec.setIdMandant(oRec.get_ID_MANDANT_lValue(null));
		
		// userinfo
		oVec.setErzeugtAm(bibDate.String2Date(oRec.get_ERZEUGT_AM_cUF()));
		oVec.setErzeugtVon(oRec.get_ERZEUGT_VON_cUF());
		oVec.setGeaendertVon(oRec.get_GEAENDERT_VON_cUF());
		oVec.setLetzteAenderung(bibDate.String2Date(oRec.get_LETZTE_AENDERUNG_cUF()));
		
		
		oVec.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		

		oVec.setLetzteAenderung(bibDate.String2Date(oRec.get_LETZTE_AENDERUNG_cUF()));
		
		
		
		if (oRec.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y")){
			oVec.setStorniertAm(bibDate.String2Date(oRec.get_LETZTE_AENDERUNG_cUF()));
			oVec.setStorniertGrund(null);
			oVec.setStorniertVon(oRec.get_GEAENDERT_VON_cUF());
			oVec.setStatus("STORNIERT");
		}
		

		oVec.setVariante(ENUM_VEKTOR_TYP.KORR.name());
		oVec.setPosnr(1L);

		
		// die 1. Bewegungsvektorposition erzeugen und anhängen 
		JtBewegung_Vektor_Pos oVecPos = new JtBewegung_Vektor_Pos(oVec);
		oVec.getJtBewegungVektorPoss().add(oVecPos);
		oVecPos.setPosnr(1L);
		
		JtBewegung_Atom oAtom = new JtBewegung_Atom();
		
		
		// id_lager_konto
		oAtom.setIdLagerKonto(oRec.get_ID_LAGER_KONTO_lValue(null));
		oAtom.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		
		oAtom.setJtBewegung(oBew);
		oAtom.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		oAtom.setJtBewegungsvektor(oVec);
		oAtom.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");

		oAtom.setJtBewegungsvektorPos(oVecPos);
		oAtom.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		
		oAtom.setIdMandant(oRec.get_ID_MANDANT_lValue(null));
		oAtom.setPosnr(new Long(1));
		
		// userinfo
		oAtom.setErzeugtAm(bibDate.String2Date(oRec.get_ERZEUGT_AM_cUF()));
		oAtom.setErzeugtVon(oRec.get_ERZEUGT_VON_cUF());
		oAtom.setGeaendertVon(oRec.get_GEAENDERT_VON_cUF());
		oAtom.setLetzteAenderung(bibDate.String2Date(oRec.get_LETZTE_AENDERUNG_cUF()));
		
		oAtom.setAbgerechnet(null);
		oAtom.setAbrechenbar(null);
		
		// Datum
		oAtom.setLeistungsdatum(bibDate.String2Date(oRec.get_BUCHUNGSDATUM_cUF()) );
		
		// Sorte
		oAtom.setIdArtikel(oRec.get_ID_ARTIKEL_SORTE_lValue(null));
		oAtom.setIdArtikelBez(getArtikelBezID_For_ArtikelID(oRec.get_ID_ARTIKEL_SORTE_cUF()));
		oAtom.setArtbez1(getARTBEZ1(oRec.get_ID_ARTIKEL_SORTE_cUF()));
		
		// Mengen
		oAtom.setPlanmenge(oRec.get_MENGE_bdValue(BigDecimal.ZERO).abs());
		oAtom.setMenge(oRec.get_MENGE_bdValue(BigDecimal.ZERO).abs());
		
		oAtom.setPruefungMenge("Y");
		oAtom.setPruefungMengeAm(oAtom.getLeistungsdatum().dValue);
		oAtom.setPruefungMengeVon(oRec.get_ERZEUGT_VON_cUF());
		
		// Preis
		oAtom.setEPreis(oRec.get_PREIS_bdValue(null));
		oAtom.setEPreisResultNettoMge(oRec.get_PREIS_bdValue(null));
		oAtom.setAbzugMenge(BigDecimal.ZERO);

		
		
		// Start-Station
		JtBewegung_Station oStationStart = new JtBewegung_Station(oAtom,-1);

		
		// Station ZIEL
		JtBewegung_Station oStationZiel = new JtBewegung_Station(oAtom,+1);
		
		
		// Zu- oder Abbuchung abhängig vom Buchungstyp
		// PROBLEM: Storno werden als negierten Wert im gleichen Buchungstyp durchgeführt
		boolean bZubuchung = false;
		if (oRec.get_BUCHUNGSTYP_cUF_NN("-").equalsIgnoreCase("WE")){
			// ist eine Zubuchung, wenn die Menge auch positiv ist, sonst ist es die Stornobuchung eines WEs
			bZubuchung = true && oRec.get_MENGE_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) >= 0;
		} else {
			// ist eine Abbuchung, wenn die Menge negativ ist, sonst ist es eine Stornobuchung eines WAs
			bZubuchung = false || oRec.get_MENGE_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) > 0;;
		}		
		
		if (bZubuchung){
			// WE
			oStationStart.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND()));
			oStationStart.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
			
			
			oStationStart.setKontrollmenge(oRec.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			oStationZiel.setIDAdresse(oRec.get_ID_ADRESSE_LAGER_lValue(null));
			oStationZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
			
			oStationZiel.setKontrollmenge(oRec.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			
			oStationStart.setIDAdresseBesitzer(getIdAdresseBesitzer(oRec.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
			oStationZiel.setIDAdresseBesitzer(getIdAdresseBesitzer(oRec.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
			
		} else {
			// WA
			oStationStart.setIDAdresse(oRec.get_ID_ADRESSE_LAGER_lValue(null));
			oStationStart.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()) );
			
			oStationStart.setKontrollmenge(oRec.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			oStationZiel.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND()));
			oStationZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
			
			oStationZiel.setKontrollmenge(oRec.get_MENGE_bdValue(BigDecimal.ZERO).abs());

			oStationStart.setIDAdresseBesitzer(getIdAdresseBesitzer(oRec.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
			oStationZiel.setIDAdresseBesitzer(getIdAdresseBesitzer(oRec.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
		}
		
		// Bemerkungsfelder
		oAtom.setBemerkung(oRec.get_BEMERKUNG_cUF());
		
		// Storno
		oAtom.setStorniert(oRec.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y") ? "Y" : null );
		oAtom.setStorniertAm(oRec.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null);
		oAtom.setStorniertGrund(null);
		oAtom.setStorniertVon(oRec.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y") ? oRec.get_GEAENDERT_VON_cUF():null );
		
		// Atom in Vetor einbauen
		oVecPos.getJtBewegungsatoms().add(oAtom);

		return oBew;
	}


	/**
	 * Generieren von Vektor für die Umbuchung 
	 * @author manfred
	 * @date   24.09.2012
	 * @param lager1
	 * @param lager2
	 * @param posnrVektor
	 * @return
	 * @throws myException
	 */
	protected JtBewegung generiere_Bewegung_Umbuchung( RECORD_LAGER_KONTO lager1, RECORD_LAGER_KONTO lager2) throws myException{

		// Eintrittsbedingung
		if (lager1 == null && lager2 == null) return null;

		// die Bewegung 
		JtBewegung  oBew = new JtBewegung();
		
		
		// der Vektor
		JtBewegung_Vektor oVec = new JtBewegung_Vektor();
		
		boolean bStornoWE = false;
		boolean bStornoWA = false;
		
		// zuweisen von WE / WA Eintraegen
		RECORD_LAGER_KONTO oKtoWE = null;
		RECORD_LAGER_KONTO oKtoWA = null;
				
		// Bewegung-Satz
		oBew.setIdMandant(lager1.get_ID_MANDANT_lValue(null));
		// userinfo
		oBew.setErzeugtAm(bibDate.String2Date(lager1.get_ERZEUGT_AM_cUF()));
		oBew.setErzeugtVon(lager1.get_ERZEUGT_VON_cUF());
		oBew.setGeaendertVon(lager1.get_GEAENDERT_VON_cUF());
		oBew.setLetzteAenderung(bibDate.String2Date(lager1.get_LETZTE_AENDERUNG_cUF()));
		oBew.setIdBewegung("SEQ_BEWEGUNG.NEXTVAL");
		
		oBew.setIstLagerbuchungAlt("Y");
		oBew.getJtBewegungsvektors().add(oVec);

		
		
		if (lager1 != null ) {
			
			oVec.setIdMandant( lager1.get_ID_MANDANT_lValue(null) );
			
			
			// userinfo
			oVec.setErzeugtAm(bibDate.String2Date(lager1.get_ERZEUGT_AM_cUF()));
			oVec.setErzeugtVon(lager1.get_ERZEUGT_VON_cUF());
			oVec.setGeaendertVon(lager1.get_GEAENDERT_VON_cUF());
			oVec.setLetzteAenderung(bibDate.String2Date(lager1.get_LETZTE_AENDERUNG_cUF()));
			
			if ( lager1.get_BUCHUNGSTYP_cUF_NN("-").equalsIgnoreCase("WE")){
				oKtoWE = lager1;
				if (lager1.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y")){
					bStornoWE = true;
				}
			} else {
				oKtoWA = lager1;
				if (lager1.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y")){
					bStornoWA = true;
				}
			}
		}
		
		if (lager2 != null){
			oVec.setIdMandant (lager2.get_ID_MANDANT_lValue(null));
			if ( lager2.get_BUCHUNGSTYP_cUF_NN("-").equalsIgnoreCase("WA")){
				oKtoWA = lager2;
				if (lager2.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y")){
					bStornoWA = true;
				}
			} else {
				oKtoWE = lager2;
				if (lager2.get_STORNO_cUF_NN("-").equalsIgnoreCase("Y")){
					bStornoWE = true;
				}
			}
		}			

		
		
		oVec.setJtBewegung(oBew);
		oVec.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		oVec.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		oVec.setPosnr(1L);
		
		oVec.setVariante(ENUM_VEKTOR_TYP.UMB.name());
		
		JtBewegung_Vektor_Pos oVecPos = new JtBewegung_Vektor_Pos(oVec);
		oVec.getJtBewegungVektorPoss().add(oVecPos);
		oVecPos.setPosnr(1L);
		
		
		// 1. Atom Umbuchung-Ausbuchung 
		
		Long atomposnr = (long)1;
		
		if (oKtoWA != null){
			JtBewegung_Atom a1 = new JtBewegung_Atom();
			
			a1.setJtBewegung(oBew);
			a1.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");

			
			a1.setJtBewegungsvektor(oVec);
			a1.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
			
			a1.setJtBewegungsvektorPos(oVecPos);
			a1.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
			
			a1.setIdMandant(oKtoWA.get_ID_MANDANT_lValue(null));
			
			a1.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
			
			a1.setPosnr(atomposnr++);
			
			// ID-Lagerkonto
			a1.setIdLagerKonto(oKtoWA.get_ID_LAGER_KONTO_lValue(null));

			// userinfo
			a1.setErzeugtAm(bibDate.String2Date(oKtoWA.get_ERZEUGT_AM_cUF()));
			a1.setErzeugtVon(oKtoWA.get_ERZEUGT_VON_cUF());
			a1.setGeaendertVon(oKtoWA.get_GEAENDERT_VON_cUF());
			a1.setLetzteAenderung(bibDate.String2Date(oKtoWA.get_LETZTE_AENDERUNG_cUF()));
			

			// Datum
			a1.setLeistungsdatum(bibDate.String2Date(oKtoWA.get_BUCHUNGSDATUM_cUF()) );

			
			// Sorte
			a1.setIdArtikel(oKtoWA.get_ID_ARTIKEL_SORTE_lValue(null));
			a1.setIdArtikelBez(getArtikelBezID_For_ArtikelID(oKtoWA.get_ID_ARTIKEL_SORTE_cUF()));
			a1.setArtbez1(getARTBEZ1(oKtoWA.get_ID_ARTIKEL_SORTE_cUF()));
			a1.setArtbez2(null);
			
			// Menge
			a1.setMenge(oKtoWA.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			a1.setPlanmenge(oKtoWA.get_MENGE_bdValue(null).abs());
			a1.setAbzugMenge(BigDecimal.ZERO);
			
			a1.setPruefungMenge("Y");
			a1.setPruefungMengeAm(a1.getLeistungsdatum().dValue);
			a1.setPruefungMengeVon(oKtoWA.get_ERZEUGT_VON_cUF());
			
			// Preis
			a1.setEPreis(oKtoWA.get_PREIS_bdValue(null));
			a1.setEPreisResultNettoMge(oKtoWA.get_PREIS_bdValue(null));
			
			// Start-Station
			JtBewegung_Station oStationStart = new JtBewegung_Station(a1,-1);
			
			// Station ZIEL
			JtBewegung_Station oStationZiel = new JtBewegung_Station(a1,+1);

			oStationStart.setKontrollmenge(oKtoWA.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			oStationZiel.setKontrollmenge(oKtoWA.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			
			// beim Storno (positive Mengen im Warenausgang) werden die einzelnen Positionen umgekehrt gebucht
			if (oKtoWA.get_MENGE_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) <= 0){
				// WA
				oStationStart.setIDAdresse(oKtoWA.get_ID_ADRESSE_LAGER_lValue(null));
				oStationStart.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationZiel.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND()));
				oStationZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationStart.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWA.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
				oStationZiel.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWA.get_ID_ADRESSE_LAGER_cUF_NN("-1")));

				
				
			} else {
				// S T O R N O - ist beim WA eine Einbuchung
				oStationStart.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND() ));
				oStationStart.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationZiel.setIDAdresse(oKtoWA.get_ID_ADRESSE_LAGER_lValue(null)) ;
				oStationZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationStart.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWA.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
				oStationZiel.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWA.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
			}

			
			// Storno
			if (bStornoWA){
				a1.setStorniert( "Y" );
				a1.setStorniertAm(bibDate.String2Date("2012-01-01"));
				a1.setStorniertGrund(null);
				a1.setStorniertVon(oKtoWA.get_GEAENDERT_VON_cUF());
			}

			// Bemerkungsfelder
			a1.setBemerkung(oKtoWA.get_BEMERKUNG_cUF());
			
			// das 1. Atom in den Vektor eintragen
			oVecPos.getJtBewegungsatoms().add(a1);
		}
		
		
		if (oKtoWE != null){
			JtBewegung_Atom a2 = new JtBewegung_Atom();
			
			a2.setJtBewegung(oBew);
			a2.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");

			a2.setJtBewegungsvektor(oVec);
			a2.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
			
			a2.setJtBewegungsvektorPos(oVecPos);
			a2.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
			
			a2.setIdMandant(oKtoWE.get_ID_MANDANT_lValue(null));
			
			a2.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
			
			a2.setPosnr(atomposnr++);

			// ID-Lagerkonto
			a2.setIdLagerKonto(oKtoWE.get_ID_LAGER_KONTO_lValue(null));

			
			// userinfo
			a2.setErzeugtAm(bibDate.String2Date(oKtoWE.get_ERZEUGT_AM_cUF()));
			a2.setErzeugtVon(oKtoWE.get_ERZEUGT_VON_cUF());
			a2.setGeaendertVon(oKtoWE.get_GEAENDERT_VON_cUF());
			a2.setLetzteAenderung(bibDate.String2Date(oKtoWE.get_LETZTE_AENDERUNG_cUF()));

			// Datum
			a2.setLeistungsdatum(bibDate.String2Date(oKtoWE.get_BUCHUNGSDATUM_cUF()) );

			
			// Sorte
			a2.setIdArtikel(oKtoWE.get_ID_ARTIKEL_SORTE_lValue(null));
			a2.setIdArtikelBez(getArtikelBezID_For_ArtikelID(oKtoWE.get_ID_ARTIKEL_SORTE_cUF()));
			a2.setArtbez1(getARTBEZ1(oKtoWE.get_ID_ARTIKEL_SORTE_cUF()));
			a2.setArtbez2(null);
			
			// Menge
			a2.setMenge(oKtoWE.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			a2.setPlanmenge(oKtoWE.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			a2.setAbzugMenge(BigDecimal.ZERO);

			a2.setPruefungMenge("Y");
			a2.setPruefungMengeAm(a2.getLeistungsdatum().dValue);
			a2.setPruefungMengeVon(oKtoWE.get_ERZEUGT_VON_cUF());

			// Preis
			a2.setEPreis(oKtoWE.get_PREIS_bdValue(null));
			a2.setEPreisResultNettoMge(oKtoWE.get_PREIS_bdValue(null));
			
			
			
			// Start-Station
			JtBewegung_Station oStationStart = new JtBewegung_Station(a2,-1);
			
			// Station ZIEL
			JtBewegung_Station oStationZiel = new JtBewegung_Station(a2,+1);
			

			oStationStart.setKontrollmenge(oKtoWE.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			oStationZiel.setKontrollmenge(oKtoWE.get_MENGE_bdValue(BigDecimal.ZERO).abs());
			
			if (oKtoWE.get_MENGE_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>=0){
				// WE
				oStationStart.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND() ));
				oStationStart.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));;
				
				oStationZiel.setIDAdresse(oKtoWE.get_ID_ADRESSE_LAGER_lValue(null));
				oStationZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationStart.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWE.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
				oStationZiel.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWE.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
			} else {
				// S T O R N O
				// WE
				oStationStart.setIDAdresse(oKtoWE.get_ID_ADRESSE_LAGER_lValue(null));
				oStationStart.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationZiel.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND() ));
				oStationZiel.setIDAdresseBasis(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				oStationStart.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWE.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
				oStationZiel.setIDAdresseBesitzer(getIdAdresseBesitzer(oKtoWE.get_ID_ADRESSE_LAGER_cUF_NN("-1")));
			}
			
			if (bStornoWE){
				a2.setStorniert( "Y" );
				a2.setStorniertAm(bibDate.String2Date("2012-01-01"));
				a2.setStorniertGrund(null);
				a2.setStorniertVon(oKtoWE.get_GEAENDERT_VON_cUF());
			}
			
			// Bemerkungsfelder
			a2.setBemerkung(oKtoWE.get_BEMERKUNG_cUF());
			
			// das 2. Atom in den Vektor eintragen
			oVecPos.getJtBewegungsatoms().add(a2);
		}
		
		
		//
		// die Bewegung zurückgeben
		//
		return oBew;
		
	}

	
	
	
	/**
	 * Ermittelt die ID-Adresse des Besitzer eines Lagers
	 * @param sIDAdresseLager
	 * @return
	 */
	protected Long getIdAdresseBesitzer(String sIDAdresseLager ){

		String sSQL = " SELECT DISTINCT NVL(V.ID_ADRESSE_FREMDWARE,V.ID_ADRESSE_BASIS) FROM " + bibE2.cTO() +".V_FIRMENADRESSEN_FLACH V "
					+ " WHERE V.ID_MANDANT = " + bibALL.get_ID_MANDANT()
					+ " AND V.ID_ADRESSE_LIEFER = " + sIDAdresseLager ;
				
		Long lIDRet = null;
		
		String sIDRet = bibDB.EinzelAbfrage(sSQL);
		if ( !bibALL.isEmpty(sIDRet) ){
			try {
				lIDRet = Long.parseLong(sIDRet);
			} catch (Exception e) {
				lIDRet = null;
			}
		}
		return lIDRet;
	}
	
	
	
	/**
	 * ermittelt die Artikelid aus der Artikelbez-ID unter zuhilfenahme des internen Caches
	 * @author manfred
	 * @date   21.08.2012
	 * @param sIDArtikelBez
	 * @return
	 * @throws myException 
	 */
	protected Long getArtikelBezID_For_ArtikelID(String sIDArtikel) {
		Long lIDArtikelBez = null;
		RECORD_ARTIKEL_BEZ oRecArtbez = null;
		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
		
		
		if (m_hmArtikelBez_zu_Artikel.containsKey(sIDArtikel)){
			oRecArtbez = m_hmArtikelBez_zu_Artikel.get(sIDArtikel);
			try {
				lIDArtikelBez = oRecArtbez.get_ID_ARTIKEL_BEZ_lValue(null);
			} catch (myException e) {
				e.printStackTrace();
			}
		} else {
			RECLIST_ARTIKEL_BEZ oArtBez = null;
			try {
				oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
				RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
				lIDArtikelBez = o.get_ID_ARTIKEL_BEZ_lValue(null);
				m_hmArtikelBez_zu_Artikel.put(sIDArtikel, o);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		return lIDArtikelBez;
	}

	
	/**
	 * Ermittelt die ARTBEZ1 zu einem Artikel
	 * @author manfred
	 * @date   27.09.2012
	 * @param sIDArtikel
	 * @return
	 */
	protected String getARTBEZ1(String sIDArtikel){
		String sARTBEZ1 = null;
		Long lIDArtikelBez = null;
		RECORD_ARTIKEL_BEZ oRecArtbez = null;
		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
		
		
		if (m_hmArtikelBez_zu_Artikel.containsKey(sIDArtikel)){
			oRecArtbez = m_hmArtikelBez_zu_Artikel.get(sIDArtikel);
			try {
				sARTBEZ1 = oRecArtbez.get_ARTBEZ1_cUF();
			} catch (myException e) {
				e.printStackTrace();
			}
		} else {
			RECLIST_ARTIKEL_BEZ oArtBez = null;
			try {
				oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
				RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
				sARTBEZ1 = oRecArtbez.get_ARTBEZ1_cUF();
				m_hmArtikelBez_zu_Artikel.put(sIDArtikel, o);
			} catch (myException e) {
				e.printStackTrace();
			}
		}

		return sARTBEZ1;
	}
	
	

	
	
	public Vector<String> getSQLTransformation(){
		return new Vector<String>();
	}
	
	
}
