package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BL_FUHREN_Transformation_MIXED extends BL_FUHREN_Transformation {


	private long posNrVektorInBewegung = 1;
	private long posNrVektorPosInVektor = 1;
	
	
	public BL_FUHREN_Transformation_MIXED(Vector<String> vLagerOrte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel, HashMap<String, String> hmLieferbedingungen) throws myException {
		super(vLagerOrte,objUmrechnung,hmArtikeBezZuordnung,vLeergutartikel,hmLieferbedingungen);
		
		m_typ_strecke_lager_mixed = 3;

	}

	
	/**
	 * 
	 */
	@Override
	public JtBewegung transformiereFuhre(RECORD_VPOS_TPA_FUHRE oRecFuhre) throws myException {
		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
		
		posNrVektorInBewegung = 1;
		

		// generiere den Bewegungssatz und übernehme die Daten
		m_Bewegung = new JtBewegung(oRecFuhre);

		
		// prüfen, ob die Hautpfuhre WE, WA, S oder LL ist..
		// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
		boolean bStartIsLager =  m_vLagerOrte.contains(oRecFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("*")) ;
		boolean bZielIsLager =  m_vLagerOrte.contains(oRecFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("*")) ;

		
		//
		// Vector für Haupfuhre in Form einer Streckenfuhre LADE--ZWE ZWE--ST ST--ZWA ZWA--ABLADE
		//
		JtBewegung_Vektor vecFuhre ;
		
		
		if (bStartIsLager && bZielIsLager){
			
			vecFuhre = generiereVectorAusFuhre_Basisdaten(oRecFuhre); 
			vecFuhre.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
			vecFuhre.setJtBewegung(m_Bewegung);
			vecFuhre.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
			vecFuhre.setIdMandant(oRecFuhre.get_ID_MANDANT_lValue(null));
			vecFuhre.setVariante(ENUM_VEKTOR_TYP.MI.name());
			vecFuhre.setPosnr(posNrVektorInBewegung);
			
			JtBewegung_Vektor_Pos vecPosHauptfuhre = generiere_VPos_Hauptfuhre(oRecFuhre, vecFuhre, 1L, ENUM_SONDERLAGER.LL); 
			
			
		} else if (!bStartIsLager && !bZielIsLager){
			// STRECKE
			// nix machen, ist schon strecke
			vecFuhre = generiere_Vektor_Hauptfuhre(oRecFuhre, posNrVektorInBewegung++);
			vecFuhre.setVariante(ENUM_VEKTOR_TYP.ST.name());
						
		} else {
	
			boolean bIsWE = bZielIsLager == true;
			ENUM_SONDERLAGER enumZwischenLager = bIsWE ?  ENUM_SONDERLAGER.ZWE :ENUM_SONDERLAGER.ZWA ;

			// Fuhre anlegen
			vecFuhre = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			vecFuhre.setPosnr(posNrVektorInBewegung++);
			vecFuhre.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
			vecFuhre.setJtBewegung(m_Bewegung);
		
			
			posNrVektorPosInVektor = 1L;

			
			if (bZielIsLager){
				// WE
				JtBewegung_Vektor_Pos vecPosHautpfuhre = generiere_VPos_Hauptfuhre(oRecFuhre, vecFuhre, posNrVektorPosInVektor++,enumZwischenLager);
				vecFuhre.setVariante(ENUM_VEKTOR_TYP.WE.name());
			} else {
				//WA
				JtBewegung_Vektor_Pos vecPosHautpfuhre = generiere_VPos_Hauptfuhre(oRecFuhre, vecFuhre, posNrVektorPosInVektor++,enumZwischenLager);
				vecFuhre.setVariante(ENUM_VEKTOR_TYP.WA.name());
			}
			
						
			
		}

		
		
		posNrVektorPosInVektor = vecFuhre.getJtBewegungVektorPoss().size();
		posNrVektorPosInVektor++;
		
		// den Vektor der Fuhre in Bewegung einfügen
		m_Bewegung.getJtBewegungsvektors().add(vecFuhre);
		

		// n-Vektoren für den WE der Orte
		JtBewegung_Vektor_Pos vecPosOrt = null;
		JtBewegung_Atom oAtom = null;
		for (RECORD_VPOS_TPA_FUHRE_ORT ort : m_rlOrt.values()){
			oAtom = null;
			if (ort.get_DEF_QUELLE_ZIEL_cUF_NN("-").equals("EK")){
				
				// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
				bStartIsLager =  m_vLagerOrte.contains(ort.get_ID_ADRESSE_LAGER_cUF_NN("*")) ;
				bZielIsLager =  m_vLagerOrte.contains(oRecFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("*")) ;
				
				ENUM_SONDERLAGER lager_typ = bStartIsLager ? ENUM_SONDERLAGER.ZWA : ENUM_SONDERLAGER.ZWE;
				
				
				// ladeseite
				vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Ladeseite(vecFuhre, m_oFuhre, ort, posNrVektorPosInVektor, lager_typ,true);
				
				
				if (bStartIsLager && bZielIsLager){
					//LL
					// 1. Atom: vom Kunde ins Streckenlager
					oAtom =  vecPosOrt.getJtBewegungsatoms().firstElement();
		
					// 2. Atom entfernen
					vecPosOrt.getJtBewegungsatoms().remove(1);

					// 1. Atom: ZWE in LL umwandeln
					vecPosOrt.getJtBewegungsatoms().firstElement().getJtBewegungstations().lastElement().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
					vecPosOrt.getJtBewegungsatoms().firstElement().getJtBewegungstations().lastElement().setIDAdresseBasis(m_IDAdresseMandant);
					vecPosOrt.setPosnr(posNrVektorPosInVektor++);

					
					
				} else if (!bStartIsLager && !bZielIsLager){
					// Strecke
					// 1. Atom: vom Kunde ins Streckenlager
					oAtom =  vecPosOrt.getJtBewegungsatoms().firstElement();
					vecPosOrt.setPosnr(posNrVektorPosInVektor++);
					
					// Füllatom mit Menge und Preis befüllen
					JtBewegung_Atom oAtom1 = vecPosOrt.getJtBewegungsatoms().lastElement();
					oAtom1.getBewegungsstationZiel().setIDAdresse( bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
					
					oAtom1.setMenge(oAtom.getMenge().bdValue);
					oAtom1.setMengeNetto(oAtom.getMengeNetto().bdValue);
					oAtom1.setAbzugMenge(oAtom.getAbzugMenge().bdValue);
					
					oAtom1.setEPreis(oAtom.getEPreis().bdValue);
					oAtom1.setEPreisResultNettoMge(oAtom.getEPreisResultNettoMge().bdValue);
					oAtom1.setEPreisResultBruttoMenge(oAtom.getEPreisResultBruttoMenge().bdValue);

					oAtom.setAbrechenbar(oAtom.getBewegungsstationZiel().getIDAdresseBesitzer().lValue.equals(oAtom.getBewegungsstationStart().getIDAdresseBesitzer().lValue) ? "N" : "Y");

					
					
				} else {
					// MIXED
					// Strecke
					// 1. Atom: vom Kunde ins Streckenlager
					oAtom =  vecPosOrt.getJtBewegungsatoms().firstElement();
					vecPosOrt.setPosnr(posNrVektorPosInVektor++);
					vecPosOrt.getJtBewegungsatoms().remove(1);
					
					
				}
				
				
			} else {  // ABLADESEITE
				
				// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
				bStartIsLager =  m_vLagerOrte.contains(oRecFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("*")) ;
				bZielIsLager =  m_vLagerOrte.contains(ort.get_ID_ADRESSE_LAGER_cUF_NN("*")) ;

				ENUM_SONDERLAGER lager_typ = bZielIsLager ?  ENUM_SONDERLAGER.ZWE : ENUM_SONDERLAGER.ZWA ;
				
				// WA
				vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Abladeseite(vecFuhre, m_oFuhre, ort, posNrVektorPosInVektor, lager_typ,true);


				// ??---ZWA ZWA--ABLADE
				
				if (bStartIsLager && bZielIsLager){
					//LL
					// 1. Atom: vom Kunde ins Streckenlager
					oAtom =  vecPosOrt.getJtBewegungsatoms().lastElement();
		
					// 2. Atom entfernen
					vecPosOrt.getJtBewegungsatoms().remove(0);

					// 1. Atom: ZWE in LL umwandeln
					vecPosOrt.getJtBewegungsatoms().firstElement().getJtBewegungstations().firstElement().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
					vecPosOrt.getJtBewegungsatoms().firstElement().getJtBewegungstations().firstElement().setIDAdresseBasis(m_IDAdresseMandant);
					vecPosOrt.setPosnr(posNrVektorPosInVektor++);
					
					
				} else if (!bStartIsLager && !bZielIsLager){
					// Strecke
					
					
					//1. Atom: Füllatom
					JtBewegung_Atom oAtom1 = vecPosOrt.getJtBewegungsatoms().firstElement();
					oAtom1.getBewegungsstationStart().setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_STRECKE() ));
					oAtom1.getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
					
					//2. Atom: 
					oAtom =  vecPosOrt.getJtBewegungsatoms().lastElement();
					vecPosOrt.setPosnr(posNrVektorPosInVektor++);

					// Füllatom mit Menge und Preis befüllen
					oAtom1.setMenge(oAtom.getMenge().bdValue);
					oAtom1.setMengeNetto(oAtom.getMengeNetto().bdValue);
					oAtom1.setAbzugMenge(oAtom.getAbzugMenge().bdValue);
					
					oAtom1.setEPreis(oAtom.getEPreis().bdValue);
					oAtom1.setEPreisResultNettoMge(oAtom.getEPreisResultNettoMge().bdValue);
					oAtom1.setEPreisResultBruttoMenge(oAtom.getEPreisResultBruttoMenge().bdValue);
					
					oAtom.setAbrechenbar(oAtom.getBewegungsstationZiel().getIDAdresseBesitzer().lValue.equals(oAtom.getBewegungsstationStart().getIDAdresseBesitzer().lValue) ? "N" : "Y");

				

				} else {
					vecPosOrt.getJtBewegungsatoms().remove(0);					

					// MIXED
					oAtom =  vecPosOrt.getJtBewegungsatoms().firstElement();
					oAtom.setPosnr(1L);
					vecPosOrt.setPosnr(posNrVektorPosInVektor++);

	
				}
				
			}
		}


		
		lesePreiseFuerLL(m_Bewegung);
		
		return m_Bewegung;
		
	}
	
	
	
	
	
	

	
	
	/**
	 * generiert einen Vektor der Hauptfuhre als "Streckenfuhre". Muss dann noch umgebaut werden...
	 * @author manfred
	 * @date 16.11.2017
	 *
	 * @param oFuhre
	 * @param posnrVektor
	 * @return
	 * @throws myException
	 */
	protected JtBewegung_Vektor generiere_Vektor_Hauptfuhre (RECORD_VPOS_TPA_FUHRE oFuhre, long posnrVektor) throws myException{
		
		JtBewegung_Vektor vecFuhre = generiereVectorAusFuhre_Basisdaten(oFuhre); 
		vecFuhre.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		vecFuhre.setJtBewegung(m_Bewegung);
		vecFuhre.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		vecFuhre.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		vecFuhre.setPosnr(posnrVektor);
		vecFuhre.setVariante(ENUM_VEKTOR_TYP.ST.name());
		
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
		
		// nummerieren
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
	
	
	
	
	


	
	
	
	
	protected JtBewegung_Vektor generiere_Vektor_Hauptfuhre_oldmix (RECORD_VPOS_TPA_FUHRE oFuhre, long posnrVektor) throws myException{
		
		JtBewegung_Vektor vecFuhre = generiereVectorAusFuhre_Basisdaten(oFuhre); 
		vecFuhre.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		vecFuhre.setJtBewegung(m_Bewegung);
		vecFuhre.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		vecFuhre.setIdMandant(oFuhre.get_ID_MANDANT_lValue(null));
		vecFuhre.setPosnr(posnrVektor);
		
		
		JtBewegung_Vektor_Pos vecPosHauptfuhre = generiere_VPos_Hauptfuhre(oFuhre, vecFuhre, 1L, ENUM_SONDERLAGER.MI); 
		
		// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
		boolean bStartIsLager =  m_vLagerOrte.contains(oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("*")) ;
		boolean bZielIsLager =  m_vLagerOrte.contains(oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("*")) ;

		
		if (bStartIsLager && bZielIsLager){
			// LAGER-LAGER
			vecPosHauptfuhre.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			vecPosHauptfuhre.getJtBewegungsatoms().firstElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosHauptfuhre.getJtBewegungsatoms().lastElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			vecPosHauptfuhre.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);

		} else if (!bStartIsLager && !bZielIsLager){
			// EVTL. Strecke, abhängig von den Gewichten
			vecPosHauptfuhre.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			vecPosHauptfuhre.getJtBewegungsatoms().firstElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosHauptfuhre.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			vecPosHauptfuhre.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
		} else {
			// EVTL. Strecke, abhängig von den Gewichten
			vecPosHauptfuhre.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_MIXED_longValue());
			vecPosHauptfuhre.getJtBewegungsatoms().firstElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosHauptfuhre.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_MIXED_longValue());
			vecPosHauptfuhre.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
			
		}
		
		
		return vecFuhre;
		
		
		
		
	}

	
	
	

	
	/**
	 * Bei der Konvertierung muss der LL-Preis noch ermittelt werden (Hier aus der Lagerliste,
	 * da dort schon die Preisermittlung durchgeführt wurde)
	 * @author manfred
	 * @date   26.11.2012
	 * @param bewegung
	 */
	private void lesePreiseFuerLL(JtBewegung bewegung){
		String sIDFuhre = bewegung.getIdVposTpaFuhre().Value();
		
		BigDecimal bdPreisHauptsorte = BigDecimal.ZERO;
		String sIDArtikelHauptsorte = "";
		
		HashMap<String, BigDecimal> hmSortenpreiseWA = new HashMap<String, BigDecimal>();
		HashMap<String, BigDecimal> hmSortenpreiseWE = new HashMap<String, BigDecimal>();
		
		
		try {
			// für die Lager/Lager Preise sind nur die WA-Preise relevant, da diese auch so wieder eingebucht werden müssen 
			RECLIST_LAGER_KONTO listKto = new RECLIST_LAGER_KONTO("NVL(STORNO,'N') = 'N' AND  ID_VPOS_TPA_FUHRE = " + sIDFuhre , "");
		    if (listKto.size() == 1 ){
		    	bdPreisHauptsorte = listKto.get(0).get_PREIS_bdValue(BigDecimal.ZERO);
		    	sIDArtikelHauptsorte = listKto.get(0).get_ID_ARTIKEL_SORTE_cUF_NN("");
		    } else {
		    	//den Preis der Hauptfuhre
				for (RECORD_LAGER_KONTO rec : listKto.values()){
					BigDecimal bdMenge = rec.get_MENGE_bdValue(BigDecimal.ZERO);
					if (bdMenge.compareTo(BigDecimal.ZERO)<0){
						BigDecimal bdPreisTemp = rec.get_PREIS_bdValue(BigDecimal.ZERO);
						String     sIDSorte = rec.get_ID_ARTIKEL_SORTE_cUF_NN("");
						if (rec.get_ID_VPOS_TPA_FUHRE_ORT_cF() == null){
							bdPreisHauptsorte = bdPreisTemp;
							sIDArtikelHauptsorte = sIDSorte;
						}  
						hmSortenpreiseWA.put(sIDSorte, bdPreisTemp);
					} else {
						BigDecimal bdPreisTemp = rec.get_PREIS_bdValue(BigDecimal.ZERO);
						String     sIDSorte = rec.get_ID_ARTIKEL_SORTE_cUF_NN("");
//						if (rec.get_ID_VPOS_TPA_FUHRE_ORT_cF() == null){
//							bdPreisHauptsorte = bdPreisTemp;
//							sIDArtikelHauptsorte = sIDSorte;
//						}  
						hmSortenpreiseWE.put(sIDSorte, bdPreisTemp);
					}
				}
		    }
			
			// alle Atome durchlaufen
			for (JtBewegung_Vektor v: bewegung.getJtBewegungsvektors()){
			
				for (JtBewegung_Vektor_Pos p: v.getJtBewegungVektorPoss()){
				
					for (JtBewegung_Atom a: p.getJtBewegungsatoms()){
						
						String sIDArtikel = a.getIdArtikel().Value();
						if (sIDArtikel != null){
							
							if (a.getEPreis().bdValue == null || a.getEPreis().bdValue.equals(BigDecimal.ZERO) ){
								
								if (m_vLagerOrte.contains( a.getBewegungsstationStart().getIDAdresse().Value()) ) {
									if (a.getIdArtikel().Value().equals(sIDArtikelHauptsorte)){
										a.setEPreis(bdPreisHauptsorte);
										a.setEPreisResultNettoMge(bdPreisHauptsorte);
									} else {
										// prüfen, ob die Sorte noch irgendwo vorhanden ist
										if (hmSortenpreiseWA.containsKey(a.getIdArtikel().Value())){
											a.setEPreis(hmSortenpreiseWA.get(a.getIdArtikel().Value()));
											a.setEPreisResultNettoMge(hmSortenpreiseWA.get(a.getIdArtikel().Value()));
										} else {
											// sonst doch den Preis der Hauptsorte nehmen
											a.setEPreis(bdPreisHauptsorte);
											a.setEPreisResultNettoMge(bdPreisHauptsorte);
										}
									}
									
								} else if (m_vLagerOrte.contains(a.getBewegungsstationZiel().getIDAdresse().Value())){
									
									if (a.getIdArtikel().Value().equals(sIDArtikelHauptsorte)){
										a.setEPreis(bdPreisHauptsorte);
										a.setEPreisResultNettoMge(bdPreisHauptsorte);
									} else {
										// prüfen, ob die Sorte noch irgendwo vorhanden ist
										if (hmSortenpreiseWE.containsKey(a.getIdArtikel().Value())){
											a.setEPreis(hmSortenpreiseWE.get(a.getIdArtikel().Value()));
											a.setEPreisResultNettoMge(hmSortenpreiseWE.get(a.getIdArtikel().Value()));
										} else {
											// sonst doch den Preis der Hauptsorte nehmen
											a.setEPreis(bdPreisHauptsorte);
											a.setEPreisResultNettoMge(bdPreisHauptsorte);
										}
									}
								}
							}
						}
					}
				
				}
				
			}
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	/**
	 * Methode zum prüfen der Fälle bei Mixed
	 * @author manfred
	 * @date   22.08.2012
	 * @return
	 */
	public int Fallunterscheidung(){
		
		String sDebug = "";
		String sID_Fuhre = "";
		int iRet = 0;
		
		try {
			sID_Fuhre = m_oFuhre.get_ID_VPOS_TPA_FUHRE_cF_NN("?");
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		sDebug = "Fuhre " + sID_Fuhre + ": " +
//		" Links / Rechts :   " + Integer.toString(lager_links+kunde_links) +" / " + Integer.toString(lager_rechts+kunde_rechts)   + 
//		"   HF Lager Links =" + Integer.toString(fuhre_lager_links) + " HF Kunde Links=" + Integer.toString(fuhre_kunde_links) + " Lager Links=" + Integer.toString(lager_links) + " Kunde Links=" + Integer.toString(kunde_links) + 
//		"   HF Lager Rechts =" + Integer.toString(fuhre_lager_rechts) +" HF Kunde Rechts =" + Integer.toString(fuhre_kunde_rechts) + " Lager Rechts= " + Integer.toString(lager_rechts) + " Kunde Rechts=" + Integer.toString(kunde_rechts) 
//		;

		
		/************************************************************
		 * FALL 1:1 
		 */
		if (	sum_links == 1 	&&	sum_rechts== 1 )
		{
			iRet = 1000;

			
			
			
		}
		else
		/************************************************************
		 * FALL 1:N  
		 */
		if (	sum_links == 1	&&	sum_rechts > 1 )
		{
			iRet = 2000;
			
		}
		else
		/************************************************************
		 * FALL N:1  
		 */
		if (	sum_links > 1 && sum_rechts == 1 )
		{
			iRet = 3000;
		}
		else
		/************************************************************
		 * FALL N:M  
		 * 
		 */
		if (	sum_links > 1 && sum_rechts > 1 )
		{
			iRet = 4000;
		}
		
		
		/**
		 * irgendeinanderer Fall
		 */
		else {
			iRet = -1;
			sDebug = "Fuhre " + sID_Fuhre + ": " +
					" Links / Rechts :   " + Integer.toString(lager_links+kunde_links) +" / " + Integer.toString(lager_rechts+kunde_rechts)   + 
					"   Links : FL=" + Integer.toString(fuhre_lager_links) + " FK=" + Integer.toString(fuhre_kunde_links) + " L=" + Integer.toString(lager_links) + " K=" + Integer.toString(kunde_links) + 
					"   Rechts: FL=" + Integer.toString(fuhre_lager_rechts) +" FK=" + Integer.toString(fuhre_kunde_rechts) + " L=" + Integer.toString(lager_rechts) + " K=" + Integer.toString(kunde_rechts) 
					;
			
			
			DEBUG.System_println(sDebug, DEBUG.DEBUG_FLAG_DIVERS1);
			
		}
		return iRet;
	}
	
		
	
	
	


}
