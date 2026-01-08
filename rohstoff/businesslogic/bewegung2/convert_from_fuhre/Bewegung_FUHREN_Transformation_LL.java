package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

public class Bewegung_FUHREN_Transformation_LL extends Bewegung_Fuhren_Transformation {


//	// Preisfindung
//	BigDecimal bdPreisHauptsorte = BigDecimal.ZERO;
//	String sIDArtikelHauptsorte = "";
//	HashMap<String, BigDecimal> hmSortenpreise = new HashMap<String, BigDecimal>();
	
	
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
	public Bewegung_FUHREN_Transformation_LL( conv_helper helper)
												throws myException {
		super(helper);
		m_transportTyp = EnTransportTyp.LAGER_LAGER;
	}

	
	
	
	public Vector<jt_bg_vektor> transformiereFuhre(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException {
		Vector<jt_bg_vektor> _vVektor = new Vector<>();

		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
		
		// generiere den Bewegungssatz und übernehme die Daten
		m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
		m_BG_Vektor.setEN_TRANSPORT_TYP(m_transportTyp);
		m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
		
		generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre);		

		// Adresse der Zwischenstation setzen
		m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
		// ladungsbesitzer
		m_BG_Vektor.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
		
		
		this.initLagerpreise(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, "-1"));
		
		_vVektor.addElement(m_BG_Vektor);
		
		//
		// Fuhrenorte
		//
		int posnr = 2;
		jt_bg_atom atomOrt = null;
		
		// Fuhrenorte konvertieren
		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab())){
				
				Rec21_VPOS_TPA_FUHRE_ORT_ext ort = new Rec21_VPOS_TPA_FUHRE_ORT_ext(o);	
				// generiere den Bewegungssatz und übernehme die Daten
				jt_bg_vektor vOrt= generiereVectorAusFuhre_Basisdaten(oRecFuhre,ort);
				vOrt.setEN_TRANSPORT_TYP(m_transportTyp);
				
				generiereAtome_FuhrenORT(vOrt, oRecFuhre, ort);
				
				// zusätzliche Daten setzen
				vOrt.setPLANMENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_planmenge));
				
				vOrt.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
				vOrt.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
				
				vOrt.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
				vOrt.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
						
				
				
				vOrt.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
				// ladungsbesitzer
				vOrt.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				_vVektor.addElement(vOrt);
								
			}
		}

		setzeLagerPreise(_vVektor);
		
		return _vVektor;
	}
	

//	
//	/**
//	 * Liest die Lagerpreise einmal für die Fuhre
//	 * @author manfred
//	 * @date 09.02.2018
//	 *
//	 * @param atom
//	 * @throws myException
//	 */
//	private void initLagerpreise(String sIDFuhre) throws myException{
//		
//		Reclist21_LAGER_KONTO_ext list  = null;
//		
//		list = new Reclist21_LAGER_KONTO_ext(sIDFuhre,true,false); 
//		
//		// für die Lager/Lager Preise sind nur die WA-Preise relevant, da diese auch so wieder eingebucht werden müssen 
//	    if (list.size() == 1 ){
//	    	bdPreisHauptsorte = list.get(0).get_raw_resultValue_bigDecimal(LAGER_KONTO.preis, BigDecimal.ZERO);
//	    	sIDArtikelHauptsorte = list.get(0).get_ufs_dbVal(LAGER_KONTO.id_artikel_sorte, "");
//	    } else {
//	    	//den Preis der Hauptfuhre
//			for (Rec21 rec : list.values()){
//				
//				BigDecimal bdPreisTemp = rec.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis,BigDecimal.ZERO);
//				String sIDSorte = rec.get_ufs_dbVal(LAGER_KONTO.id_artikel_sorte, "");
//				
//				if (bdPreisTemp.equals(BigDecimal.ZERO)) {
//					bdPreisTemp = getLagerPreisAvg(rec.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager),
//												   rec.get_raw_resultValue_Long(LAGER_KONTO.id_artikel_sorte),
//												   rec.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));
//					
//				}
//				
//								
//				if (rec.get_ufs_dbVal(LAGER_KONTO.id_vpos_tpa_fuhre_ort) == null){
//					bdPreisHauptsorte = bdPreisTemp;
//					sIDArtikelHauptsorte = sIDSorte;
//				}  
//			
//				hmSortenpreise.put(sIDSorte, bdPreisTemp);
//				
//			}
//	    }
//	}
//
//	
//	private BigDecimal getLagerPreisAvg(Long idLager, Long idSorte, Date dateMaxISO) {
//		
//		String sql = "SELECT AVG(PREIS) FROM ( " +
//						" SELECT K.PREIS FROM  " +
//						" JT_LAGER_KONTO K " +
//						" WHERE NVL(K.STORNO ,'N') = 'N' " +
//						" AND K.BUCHUNGSTYP='WE' " +
//						" AND NVL(K.PREIS,0) > 0 " +
//						" AND K.ID_ADRESSE_LAGER = ? " +
//						" AND K.ID_ARTIKEL_SORTE = ? " +
//						" AND K.BUCHUNGSDATUM <= ? " +
//						" ORDER BY K.BUCHUNGSDATUM DESC " +
//				" ) WHERE ROWNUM < 10";
//				
//		SqlStringExtended sqlExt = new SqlStringExtended(sql);
//		sqlExt.getValuesList().add(new Param_Long(idLager));
//		sqlExt.getValuesList().add(new Param_Long(idSorte));
//		sqlExt.getValuesList().add(new Param_Date(dateMaxISO));
//		
//		String [][] cDaten = new String[0][0];
//		cDaten =  bibDB.EinzelAbfrageInArray(sqlExt,(String)null);
//		
//		BigDecimal bdRet = BigDecimal.ZERO;
//		if (cDaten.length> 0) {
//			bdRet = new BigDecimal(cDaten[0][0]);
//		}
//		return bdRet;
//		
//	}
//	
//	
//	
//	
//	/**
//	 * Bei der Konvertierung muss der LL-Preis noch ermittelt werden (Hier aus der Lagerliste,
//	 * da dort schon die Preisermittlung durchgeführt wurde)
//	 * @author manfred
//	 * @date   26.11.2012
//	 * @param bewegung
//	 */
//	private void setzeLagerPreise(Vector<jt_bg_vektor> vVektor){
//		
//		// alle Vektoren durchlaufen um Preise beim Ausgangslager zu finden 
//		for (jt_bg_vektor v: vVektor ){
//			
//			jt_bg_atom atom_start = v.getATOM_1();
//			jt_bg_atom atom_ziel = v.getATOM_2();
//
//			// relevantes Atom merken
//			jt_bg_atom atom_preis = atom_start;
//			
//			if(atom_start == null || atom_ziel == null){
//				// weiter zum nächsten Vektor
//				break;
//			}
//			
//			
//			// der Relevante Artikel der auf der Ladeseite
//			String sIDArtikel = atom_start.getID_ARTIKEL().ValuePlain();
//			
//			//  beim Fuhrenort kommt es drauf an, welcher das Gewicht hat. (der andere hat 0)
//			if (v.get_jt_bg_vektor_konvert().getID_VPOS_TPA_FUHRE_ORT() != null) {
//				if(v.getATOM_1().getMENGE().bdValue.equals(BigDecimal.ZERO)){
//					sIDArtikel = atom_ziel.getID_ARTIKEL().ValuePlain();
//					atom_preis = atom_ziel;
//				}
//			}
//						
//			
//			if (sIDArtikel != null){
//				if (atom_preis.getE_PREIS_BASISWAEHRUNG().bdValue == null || atom_preis.getE_PREIS_BASISWAEHRUNG().bdValue.equals(BigDecimal.ZERO) ){
//					
//					if (atom_preis.getID_ARTIKEL().Value().equals(sIDArtikelHauptsorte)){
////						atom_preis.setE_PREIS_BASISWAEHRUNG(bdPreisHauptsorte);
////						atom_preis.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreisHauptsorte);
//						atom_start.setE_PREIS_BASISWAEHRUNG(bdPreisHauptsorte);
//						atom_start.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreisHauptsorte);
//						atom_ziel.setE_PREIS_BASISWAEHRUNG(bdPreisHauptsorte);
//						atom_ziel.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreisHauptsorte);
//						
//					} else {
//						BigDecimal bdPreis = BigDecimal.ZERO;
//						// prüfen, ob die Sorte noch irgendwo vorhanden ist
//						if (hmSortenpreise.containsKey(sIDArtikel)){
//							bdPreis =hmSortenpreise.get(sIDArtikel);
//						} else {
//							// sonst doch den Preis der Hauptsorte nehmen
//							bdPreis = bdPreisHauptsorte;
//						}
//						atom_start.setE_PREIS_BASISWAEHRUNG(bdPreis);
//						atom_start.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreis);
//						atom_ziel.setE_PREIS_BASISWAEHRUNG(bdPreis);
//						atom_ziel.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreis);
//					}
//				}
//			}
//		}
//	}

	

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung2.convert_from_fuhre.Bewegung_Fuhren_Transformation#checkAndCompletePriceEntries(java.util.Vector)
	 */
	@Override
	protected void checkAndCompletePriceEntries(Vector<jt_bg_vektor> vVektor) {
			
	}
	
	
}
