package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BG_FUHREN_Transformation_LL extends BG_FUHREN_Transformation {


	// Preisfindung
	BigDecimal bdPreisHauptsorte = BigDecimal.ZERO;
	String sIDArtikelHauptsorte = "";
	HashMap<String, BigDecimal> hmSortenpreise = new HashMap<String, BigDecimal>();
	
	
	/**
	 * 
	 * @author manfred
	 * @date 09.02.2018
	 *
	 * @param vLagerOrte
	 * @param objUmrechnung
	 * @param hmArtikeBezZuordnung
	 * @param vLeergutartikel
	 * @param hmLieferbedingungen
	 * @param hmLaendercode
	 * @throws myException
	 */
	public BG_FUHREN_Transformation_LL(	Vector<String> vLagerOrte, 
										GROESSEN_Umrechnung objUmrechnung, 
										HashMap<String, String> hmArtikeBezZuordnung, 
										Vector<String> vLeergutartikel, 
										HashMap<String, String> hmLieferbedingungen ,
										HashMap<String, Long> hmLaendercode) throws myException {
		super(vLagerOrte,objUmrechnung,hmArtikeBezZuordnung,  vLeergutartikel,hmLieferbedingungen, hmLaendercode);
		m_vektor_typ = EN_VEKTOR_TYP.LL;
	}

	
	
	
	public jt_bg_vektor transformiereFuhre(Rec20_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException {

		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
		
		// generiere den Bewegungssatz und übernehme die Daten
		m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
		m_BG_Vektor.setEN_VEKTOR_TYP(m_vektor_typ);
		m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
		
		// Hauptfuhre, Pos1
		jt_bg_atom atom_fuhre = generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre, 1);

		this.initLagerpreise(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, "-1"));
		
		
		
		//
		// Fuhrenorte
		//
		long posnr = 2;
		jt_bg_atom atomOrt = null;
		
		// Fuhrenorte konvertieren
		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab())){
				
				Rec20_VPOS_TPA_FUHRE_ORT_ext ort = new Rec20_VPOS_TPA_FUHRE_ORT_ext(o);	
				atomOrt = generiereAtom_FuhrenORT(m_BG_Vektor, oRecFuhre, ort, posnr++);
			}
		}

		lesePreiseFuerLL();
		
		
		return m_BG_Vektor;
		
	}
	

	
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

	
}
