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

public class BL_FUHREN_Transformation_MIXED_old extends BL_FUHREN_Transformation {


	private long posNrVektorInBewegung = 1;
	private long posNrVektorPosInVektor = 1;
	private long posNrVektorPos = 1;
	
	
	public BL_FUHREN_Transformation_MIXED_old(Vector<String> vLagerOrte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel, HashMap<String, String> hmLieferbedingungen) throws myException {
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
		
		// Atom für die berechnung der Sortenwechsel bei Fuhrenorten auf der Lagerseite
		JtBewegung_Atom oAtomReferenzSW = null;
		

		//
		// Vector für Haupfuhre
		//
		JtBewegung_Vektor vecFuhre = generiere_Vektor_Hauptfuhre(oRecFuhre, posNrVektorInBewegung++);
		vecFuhre.setVariante(ENUM_VEKTOR_TYP.MI.name());

		posNrVektorPos = vecFuhre.getJtBewegungVektorPoss().size();
		posNrVektorPos++;
		
		// den Vektor der Fuhre in Bewegung einfügen
		m_Bewegung.getJtBewegungsvektors().add(vecFuhre);
		
		// Fuhrenorte 
		JtBewegung_Vektor vecOrt = null;
		for (RECORD_VPOS_TPA_FUHRE_ORT ort : m_rlOrt.values()){
			
			boolean bIstLadestelle = ort.get_DEF_QUELLE_ZIEL_cUF().equals("EK");
		
			if (bIstLadestelle){
				generiereVektorPosUndAtomMixed_ORT_WE(vecFuhre, oRecFuhre, ort, posNrVektorPos++);
//				m_Bewegung.getJtBewegungsvektors().add(vecOrt);
				
			}  else {
				generiereVektorPosUndAtomMixed_ORT_WA(vecFuhre, oRecFuhre, ort, posNrVektorPos++);
//				m_Bewegung.getJtBewegungsvektors().add(vecOrt);
			}
		}

		
		lesePreiseFuerLL(m_Bewegung);
		
		return m_Bewegung;
		
	}
	

	
	
	
	
	
	protected JtBewegung_Vektor generiere_Vektor_Hauptfuhre (RECORD_VPOS_TPA_FUHRE oFuhre, long posnrVektor) throws myException{
		
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

	
	
	
	private JtBewegung_Vektor_Pos generiereVektorPosUndAtomMixed_ORT_WE (JtBewegung_Vektor oVec_Fuhre, RECORD_VPOS_TPA_FUHRE oFuhre, RECORD_VPOS_TPA_FUHRE_ORT oOrt, Long posnrVektorPos) throws myException{
		// neuen Bewegungsvektor für den Fuhrenort
		
		JtBewegung_Vektor_Pos vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Ladeseite(oVec_Fuhre, oFuhre, oOrt, posnrVektorPos, ENUM_SONDERLAGER.ZWE,false);
		
		// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
		boolean bStartIsLager =  m_vLagerOrte.contains(oOrt.get_ID_ADRESSE_LAGER_cUF_NN("*")) ;
		boolean bZielIsLager =  m_vLagerOrte.contains(oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("*")) ;

		
		if (bStartIsLager && bZielIsLager){
			// LAGER-LAGER
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);

		} else if (!bStartIsLager && !bZielIsLager){
			// EVTL. Strecke, abhängig von den Gewichten
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
		} else {
			// EVTL. Strecke, abhängig von den Gewichten
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_MIXED_longValue());
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_MIXED_longValue());
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
		}

		
		return vecPosOrt;
	}
	
	
	
	private JtBewegung_Vektor_Pos generiereVektorPosUndAtomMixed_ORT_WA (JtBewegung_Vektor oVec_Fuhre, RECORD_VPOS_TPA_FUHRE oFuhre, RECORD_VPOS_TPA_FUHRE_ORT oOrt, Long posnrVektorPos) throws myException{
		// neuen Bewegungsvektor für den Fuhrenort
		
		JtBewegung_Vektor_Pos vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Abladeseite(oVec_Fuhre, oFuhre, oOrt, posnrVektorPos, ENUM_SONDERLAGER.ZWA,false);
		
		// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
		boolean bStartIsLager =  m_vLagerOrte.contains(oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("*")) ;
		boolean bZielIsLager =  m_vLagerOrte.contains(oOrt.get_ID_ADRESSE_LAGER_cUF_NN("*")) ;

		
		if (bStartIsLager && bZielIsLager){
			// LAGER-LAGER
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);

		} else if (!bStartIsLager && !bZielIsLager){
			// EVTL. Strecke, abhängig von den Gewichten
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
		} else {
			// EVTL. Strecke, abhängig von den Gewichten
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_MIXED_longValue());
			vecPosOrt.getJtBewegungsatoms().firstElement().getBewegungsstationZiel().setIDAdresseBasis(m_IDAdresseMandant);
			
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresse(bibSES.get_ID_ADRESSE_LAGER_MIXED_longValue());
			vecPosOrt.getJtBewegungsatoms().lastElement().getBewegungsstationStart().setIDAdresseBasis(m_IDAdresseMandant);
		}

		
		return vecPosOrt;
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
