package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BL_FUHREN_Transformation_LL extends BL_FUHREN_Transformation {


	
	private long posNrVektorPosInVektor = 1L;
	private long posNrVektorInBewegung = 1L;
	
	
	public BL_FUHREN_Transformation_LL(Vector<String> vLagerOrte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel, HashMap<String, String> hmLieferbedingungen) throws myException {
		super(vLagerOrte,objUmrechnung,hmArtikeBezZuordnung,vLeergutartikel,hmLieferbedingungen);
		
		m_typ_strecke_lager_mixed = 4;

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
		

		//
		// Vector für Haupfuhre
		//
		JtBewegung_Vektor vecFuhre = generiere_Vektor_Hauptfuhre(oRecFuhre, posNrVektorInBewegung++);
		vecFuhre.setVariante(ENUM_VEKTOR_TYP.LL.name());
		
		posNrVektorPosInVektor = vecFuhre.getJtBewegungVektorPoss().size();
		posNrVektorPosInVektor++;
		
		// den Vektor der Fuhre in Bewegung einfügen
		m_Bewegung.getJtBewegungsvektors().add(vecFuhre);
		
		// Fuhrenorte 
		JtBewegung_Vektor_Pos vecOrt = null;
		for (RECORD_VPOS_TPA_FUHRE_ORT ort : m_rlOrt.values()){
			
			boolean bIstLadestelle = ort.get_DEF_QUELLE_ZIEL_cUF().equals("EK");
		
			if (bIstLadestelle){
				vecOrt = generiereVektorPosUndAtomLL_ORT_WE(vecFuhre, oRecFuhre, ort, posNrVektorPosInVektor++);
				
			}  else {
				vecOrt = generiereVektorPosUndAtomLL_ORT_WA(vecFuhre, oRecFuhre, ort, posNrVektorPosInVektor++);
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
		
		JtBewegung_Vektor_Pos vecPosHauptfuhre = generiere_VPos_Hauptfuhre(oFuhre, vecFuhre, 1L, ENUM_SONDERLAGER.LL); 
		
		return vecFuhre;
	}

	
	
	
	private JtBewegung_Vektor_Pos generiereVektorPosUndAtomLL_ORT_WE (JtBewegung_Vektor oVec_Fuhre, RECORD_VPOS_TPA_FUHRE oFuhre, RECORD_VPOS_TPA_FUHRE_ORT oOrt, Long posnrVektorPos) throws myException{
		// neuen Bewegungsvektor für den Fuhrenort
		JtBewegung_Vektor_Pos vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Ladeseite(oVec_Fuhre, oFuhre, oOrt, posnrVektorPos,ENUM_SONDERLAGER.LL,false);
		
		return vecPosOrt;
	}
	
	
	
	private JtBewegung_Vektor_Pos generiereVektorPosUndAtomLL_ORT_WA (JtBewegung_Vektor oVec_Fuhre, RECORD_VPOS_TPA_FUHRE oFuhre, RECORD_VPOS_TPA_FUHRE_ORT oOrt, Long posnrVektorPos) throws myException{
		// neuen Bewegungsvektor für den Fuhrenort
		JtBewegung_Vektor_Pos vecPosOrt = generiereVektorPosUndAtome_FuhrenORT_Abladeseite(oVec_Fuhre, oFuhre, oOrt, posnrVektorPos,ENUM_SONDERLAGER.LL,false);
		
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
		
		HashMap<String, BigDecimal> hmSortenpreise = new HashMap<String, BigDecimal>();
		
		try {
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
			
			// alle Atome durchlaufen um lagerpreise beim Ausgangslager zu finden 
			for (JtBewegung_Vektor v: bewegung.getJtBewegungsvektors()){
				for(JtBewegung_Vektor_Pos p : v.getJtBewegungVektorPoss()){

					for (JtBewegung_Atom a: p.getJtBewegungsatoms()){
						String sIDArtikel = a.getIdArtikel().Value();
						if (sIDArtikel != null){
							if (a.getEPreis().bdValue == null || a.getEPreis().bdValue.equals(BigDecimal.ZERO) ){
								if (a.getIdArtikel().Value().equals(sIDArtikelHauptsorte)){
									a.setEPreis(bdPreisHauptsorte);
									a.setEPreisResultNettoMge(bdPreisHauptsorte);
								} else {
									// prüfen, ob die Sorte noch irgendwo vorhanden ist
									if (hmSortenpreise.containsKey(a.getIdArtikel().Value())){
										a.setEPreis(hmSortenpreise.get(a.getIdArtikel().Value()));
										a.setEPreisResultNettoMge(hmSortenpreise.get(a.getIdArtikel().Value()));
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
			
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
