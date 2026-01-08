package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BG_FUHREN_Transformation_MIXED extends BG_FUHREN_Transformation {


	
	public BG_FUHREN_Transformation_MIXED(Vector<String> vLagerOrte, 
										GROESSEN_Umrechnung objUmrechnung, 
										HashMap<String, String> hmArtikeBezZuordnung, 
										Vector<String> vLeergutartikel, 
										HashMap<String, String> hmLieferbedingungen ,
										HashMap<String, Long> hmLaendercode) throws myException {
		super(vLagerOrte, objUmrechnung, hmArtikeBezZuordnung, vLeergutartikel, hmLieferbedingungen, hmLaendercode);
		m_vektor_typ = EN_VEKTOR_TYP.MI;
	}

	
	
	
	/**
	 * 
	 */
	@Override
	public jt_bg_vektor transformiereFuhre(Rec20_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException {
		
		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
		
		// generiere den Bewegungssatz und übernehme die Daten
		m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);		
		long posnr = 1;
		
		// prüfen, ob die Hautpfuhre WE, WA, S oder LL ist..
		// jetzt die Zwischen-Orte der Atome korrigieren, abhängig von den Start- und Ziel- Adressen
		boolean bStartIsLager 	=  m_vLagerOrte.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "*") ) ;
		boolean bZielIsLager 	=  m_vLagerOrte.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel, "-")) ;

		
		if (bStartIsLager && bZielIsLager){
			// LL-Fuhre
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
//			m_BG_Vektor.setEN_VEKTOR_TYP(m_vektor_typ);
			m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
			
			// Hauptfuhre, Pos1
			jt_bg_atom atom_fuhre = generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre, posnr++);
			this.initLagerpreise(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre,"-1"));
			this.lesePreiseFuerLL();
			
			
		} else if (!bStartIsLager && !bZielIsLager){
			// STRECKE
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			m_BG_Vektor.setEN_VEKTOR_TYP(m_vektor_typ);
			m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_abn));
			
			// Hauptfuhre KD1-> ST , Pos1
			jt_bg_atom atom_fuhre1 = generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre, posnr++);
			jt_bg_atom atom_fuhre2 = generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre, posnr++);

			
			// Streckensatz einbauen
			fuhre_to_strecke(oRecFuhre, m_BG_Vektor, atom_fuhre1,atom_fuhre2);
			
			// Rechnungspreise der Positionen setzen
			setRGValuesInAtomFromFuhre(atom_fuhre1, oRecFuhre, 1);
			setRGValuesInAtomFromFuhre(atom_fuhre2, oRecFuhre, -1);

			
		} else {
			// WA/WE-Fuhre
			boolean bIsWE = (bZielIsLager == true);

			
			// generiere den Bewegungssatz und übernehme die Daten
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			m_BG_Vektor.setEN_VEKTOR_TYP(m_vektor_typ);
			m_BG_Vektor.setPLANMENGE(bIsWE ? oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief) : oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_abn) );
			
			// Hauptfuhre, Pos1
			jt_bg_atom atom_fuhre = generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre, posnr++);

			// Preise im Atom aus RG-pos lesen
			int nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "-") ) ? -1: 1 ;
			setRGValuesInAtomFromFuhre(atom_fuhre, oRecFuhre, nLagervorzeichen);
			
		}

		

		//
		//  Vektor-Positionen
		//
		
		jt_bg_atom atomOrt = null;
		
		
		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab()) ) {
				Rec20_VPOS_TPA_FUHRE_ORT_ext ort = new Rec20_VPOS_TPA_FUHRE_ORT_ext(o);	

				// Start und Ziel definieren
				if (ort.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equalsIgnoreCase("EK")){
					bStartIsLager = m_vLagerOrte.contains(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "*") );
					bZielIsLager =  m_vLagerOrte.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel, "*") ) ;
				} else {
					bStartIsLager =  m_vLagerOrte.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "*")) ;
					bZielIsLager = m_vLagerOrte.contains(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "*"));
				}
				
				
				// Fuhrenort generieren
				if (bStartIsLager && bZielIsLager){
					atomOrt = generiereAtom_FuhrenORT(m_BG_Vektor, oRecFuhre, ort, posnr++);
					lesePreiseFuerLL();
					
				} else if (!bStartIsLager && !bZielIsLager){
					atomOrt = generiereAtom_FuhrenORT(m_BG_Vektor, oRecFuhre, ort, posnr++);
					// Abhängig ob lade oder abladeseite...
					if (ort.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equalsIgnoreCase("EK")){
						atomOrt.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.LEFT);
					} else {
						atomOrt.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.RIGHT);
					}
					
					setRGValuesInAtomFromFuhreOrt(atomOrt, ort);
					fuhrenort_to_strecke(oRecFuhre, ort, m_BG_Vektor, atomOrt);
					
				} else {
					atomOrt = generiereAtom_FuhrenORT(m_BG_Vektor, oRecFuhre, ort, posnr++);
					
					//Preisdaten aus der Rechnungsposition lesen
					setRGValuesInAtomFromFuhreOrt(atomOrt, ort);
				}
			}
		}
			
		checkAndCompletePriceEntries();		
		
		return m_BG_Vektor;
		
	}
	
	
	
	
	
	// Preisfindung
	BigDecimal bdPreisHauptsorte = BigDecimal.ZERO;
	String sIDArtikelHauptsorte = "";
	HashMap<String, BigDecimal> hmSortenpreise = new HashMap<String, BigDecimal>();

	/**
	 * Liest die Lagerpreise einmal für die Fuhre
	 * @author manfred
	 * @date 09.02.2018
	 *
	 * @param atom
	 * @throws myException
	 */
	private void initLagerpreise(String sIDFuhre) throws myException{
		
		// für die Lager/Lager Preise sind nur die WA-Preise relevant, da diese auch so wieder eingebucht werden müssen 
		RECLIST_LAGER_KONTO listKto = new RECLIST_LAGER_KONTO("NVL(STORNO,'N') = 'N' AND MENGE < 0 AND ID_VPOS_TPA_FUHRE = " + sIDFuhre , "");
	    if (listKto.size() == 1 ){
	    	bdPreisHauptsorte = listKto.get(0).get_PREIS_bdValue(BigDecimal.ZERO);
	    	sIDArtikelHauptsorte = listKto.get(0).get_ID_ARTIKEL_SORTE_cUF_NN("");
	    } else {
	    	//den Preis der Hauptfuhre
			for (RECORD_LAGER_KONTO rec : listKto.values()){
				BigDecimal bdPreisTemp = rec.get_PREIS_bdValue(BigDecimal.ZERO);
				String     sIDSorte = rec.get_ID_ARTIKEL_SORTE_cUF_NN("");
				if (rec.get_ID_VPOS_TPA_FUHRE_ORT_cF() == null){
					bdPreisHauptsorte = bdPreisTemp;
					sIDArtikelHauptsorte = sIDSorte;
				}  
			
				hmSortenpreise.put(sIDSorte, bdPreisTemp);
				
			}
	    }
	}

	
	/**
	 * Bei der Konvertierung muss der LL-Preis noch ermittelt werden (Hier aus der Lagerliste,
	 * da dort schon die Preisermittlung durchgeführt wurde)
	 * @author manfred
	 * @date   26.11.2012
	 * @param bewegung
	 */
	private void lesePreiseFuerLL(){
		
		// alle Atome durchlaufen um lagerpreise beim Ausgangslager zu finden 
		for (jt_bg_atom a: m_BG_Vektor.get_jt_bg_atoms()){
			
			// ladungen ermitteln und ladung-Start / Ziel festlegen
			jt_bg_ladung lad_start = null;
			jt_bg_ladung lad_ziel = null;
			jt_bg_ladung lad_preis = null;
			
			for (jt_bg_ladung ladung: a.get_jt_bg_ladungs()){
				if (ladung.getMENGENVORZEICHEN().lValue.equals(-1L)){
					lad_start = ladung;
				} else {
					lad_ziel = ladung;
				}
			}
			
			if(lad_start == null || lad_ziel == null){
				return;
			}
			
			// im Hauptatom ist der Relevante Artikel der auf der Ladeseite
			String sIDArtikel = lad_start.getID_ARTIKEL().Value();
			lad_preis = lad_start;

			//  beim Fuhrenort kommt es drauf an, welcher das Gewicht hat. (der andere hat 0)
			if (!bibALL.isEmpty(a.getID_VPOS_TPA_FUHRE_ORT().Value()) ){
				if (lad_start.getMENGE().bdValue.equals(BigDecimal.ZERO)){
					sIDArtikel = lad_ziel.getID_ARTIKEL().Value();
					lad_preis = lad_ziel;
				}
			}
			
			
			if (sIDArtikel != null){
	
				if (a.getE_PREIS().bdValue == null || a.getE_PREIS().bdValue.equals(BigDecimal.ZERO) ){
					
					if (lad_preis.getID_ARTIKEL().Value().equals(sIDArtikelHauptsorte)){
						a.setE_PREIS(bdPreisHauptsorte);
						lad_start.setE_PREIS_RESULT_NETTO_MGE(bdPreisHauptsorte);
						lad_ziel.setE_PREIS_RESULT_NETTO_MGE(bdPreisHauptsorte);
					} else {
						// prüfen, ob die Sorte noch irgendwo vorhanden ist
						if (hmSortenpreise.containsKey(lad_preis.getID_ARTIKEL().Value())){
							BigDecimal bdPreis =hmSortenpreise.get(lad_preis.getID_ARTIKEL().Value()); 
							a.setE_PREIS(bdPreis);
							lad_start.setE_PREIS_RESULT_NETTO_MGE(bdPreis);
							lad_ziel.setE_PREIS_RESULT_NETTO_MGE(bdPreis);
						} else {
							// sonst doch den Preis der Hauptsorte nehmen
							a.setE_PREIS(bdPreisHauptsorte);
							lad_start.setE_PREIS_RESULT_NETTO_MGE(bdPreisHauptsorte);
							lad_ziel.setE_PREIS_RESULT_NETTO_MGE(bdPreisHauptsorte);
						}
					}
				}
			}
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
			sID_Fuhre = m_oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, "?");
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
