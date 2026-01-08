package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

public class Bewegung_FUHREN_Transformation_MIXED extends Bewegung_Fuhren_Transformation {

	// Preisfindung
	BigDecimal bdPreisHauptsorte = BigDecimal.ZERO;
	String sIDArtikelHauptsorte = "";
	HashMap<String, BigDecimal> hmSortenpreise = new HashMap<String, BigDecimal>();

	
	public Bewegung_FUHREN_Transformation_MIXED(conv_helper helper)
												throws myException {
		super(helper);
		// Transport-Typ abhängig vom Vorgang
		
	}

	
	
	
	/**
	 * 
	 */
	@Override
	public Vector<jt_bg_vektor> transformiereFuhre(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException {
		Vector<jt_bg_vektor> _vVektor = new Vector<>();

		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);

		// prüfen, ob die Hautpfuhre WE, WA, S oder LL ist..
		boolean bStartIsLager 	=  m_helper.m_vEigeneLieferadressen.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "*") ) ;
		boolean bZielIsLager 	=  m_helper.m_vEigeneLieferadressen.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel, "-")) ;

		
		if (bStartIsLager && bZielIsLager){
			// LL-Fuhre
			m_transportTyp = EnTransportTyp.LAGER_LAGER;
			
			// generiere den Bewegungssatz und übernehme die Daten
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			m_BG_Vektor.setEN_TRANSPORT_TYP(m_transportTyp);
			
			m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
			
			generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre);		

			// Adresse der Zwischenstation setzen
			m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
			m_BG_Vektor.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
			
			_vVektor.addElement(m_BG_Vektor);
			
			this.initLagerpreise(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, "-1"));
			setzeLagerPreise(_vVektor);
			
			
		} else if (!bStartIsLager && !bZielIsLager){
			// STRECKE
			m_transportTyp = EnTransportTyp.STRECKE;
			// generiere den Bewegungssatz und übernehme die Daten
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			m_BG_Vektor.setEN_TRANSPORT_TYP(m_transportTyp);

			// zusätzliche Daten setzen
			m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
			m_BG_Vektor.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
			m_BG_Vektor.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
			m_BG_Vektor.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
			m_BG_Vektor.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
			
			// Atome generieren
			generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre);
			
			// Adresse der Zwischenstation setzen
			m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
			m_BG_Vektor.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));

			// preise im Atom aus RG-pos lesen
			setRGValuesInAtomFromFuhre(m_BG_Vektor, oRecFuhre, EnTabKeyInMask.A1);
			setRGValuesInAtomFromFuhre(m_BG_Vektor, oRecFuhre, EnTabKeyInMask.A2);

			_vVektor.addElement(m_BG_Vektor);
			
		} else if (bZielIsLager){
			m_transportTyp = EnTransportTyp.WE;
			
			// generiere den Bewegungssatz und übernehme die Daten
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			m_BG_Vektor.setEN_TRANSPORT_TYP(m_transportTyp);
			
			// zusätzliche Daten setzen
			m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
			m_BG_Vektor.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
			m_BG_Vektor.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
			m_BG_Vektor.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
			m_BG_Vektor.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
			
			// Atome generieren
			generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre);
			
			// Preise von Atom 2 (kunde) nach atom2 (lager) übertragen
			m_BG_Vektor.getATOM_1().setE_PREIS_BASISWAEHRUNG(m_BG_Vektor.getATOM_2().getE_PREIS_BASISWAEHRUNG().bdValue);
			m_BG_Vektor.getATOM_1().setE_PREIS_RES_NETTO_MGE_BASIS(m_BG_Vektor.getATOM_2().getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);

			 // Adresse der Zwischenstation setzen
			m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE_longValue());
			m_BG_Vektor.getStation2().setID_ADRESSE_BESITZER_LDG(m_BG_Vektor.getStation3().getID_ADRESSE_BESITZER_LDG().Value());

			
			// preise im Atom aus RG-pos lesen
			setRGValuesInAtomFromFuhre(m_BG_Vektor, oRecFuhre, EnTabKeyInMask.A1);
			
			// Preise von Atom 1 (kunde) nach atom2 (lager) übertragen
			m_BG_Vektor.getATOM_2().setE_PREIS_BASISWAEHRUNG(m_BG_Vektor.getATOM_1().getE_PREIS_BASISWAEHRUNG().bdValue);
			m_BG_Vektor.getATOM_2().setE_PREIS_FREMDWAEHRUNG(m_BG_Vektor.getATOM_1().getE_PREIS_FREMDWAEHRUNG().bdValue);
			
			m_BG_Vektor.getATOM_2().setE_PREIS_RES_NETTO_MGE_BASIS(m_BG_Vektor.getATOM_1().getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);
			m_BG_Vektor.getATOM_2().setE_PREIS_RES_NETTO_MGE_FREMD(m_BG_Vektor.getATOM_1().getE_PREIS_RES_NETTO_MGE_FREMD().bdValue);

			m_BG_Vektor.getATOM_2().setG_PREIS_BASISWAEHRUNG(m_BG_Vektor.getATOM_1().getG_PREIS_BASISWAEHRUNG().bdValue);
			m_BG_Vektor.getATOM_2().setG_PREIS_FREMDWAEHRUNG(m_BG_Vektor.getATOM_1().getG_PREIS_FREMDWAEHRUNG().bdValue);
			
			m_BG_Vektor.getATOM_2().setG_PREIS_ABZUG_BASIS(m_BG_Vektor.getATOM_1().getG_PREIS_ABZUG_BASIS().bdValue);
			m_BG_Vektor.getATOM_2().setG_PREIS_ABZUG_FREMD(m_BG_Vektor.getATOM_1().getG_PREIS_ABZUG_FREMD().bdValue);
			
			_vVektor.addElement(m_BG_Vektor);
			
		} else if (!bZielIsLager) {
			// WA
			m_transportTyp = EnTransportTyp.WA;
			
			// generiere den Bewegungssatz und übernehme die Daten
			m_BG_Vektor = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
			m_BG_Vektor.setEN_TRANSPORT_TYP(m_transportTyp);
			
			// zusätzliche Daten setzen
			m_BG_Vektor.setPLANMENGE(oRecFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief));
			m_BG_Vektor.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
			m_BG_Vektor.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
			m_BG_Vektor.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
			m_BG_Vektor.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
			
			// Atome generieren
			generiere_atom_Hauptfuhre(m_BG_Vektor, oRecFuhre);
			
			// Preise von Atom 2 (kunde) nach atom2 (lager) übertragen
			m_BG_Vektor.getATOM_1().setE_PREIS_BASISWAEHRUNG(m_BG_Vektor.getATOM_2().getE_PREIS_BASISWAEHRUNG().bdValue);
			m_BG_Vektor.getATOM_1().setE_PREIS_RES_NETTO_MGE_BASIS(m_BG_Vektor.getATOM_2().getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);

			 // Adresse der Zwischenstation setzen
			m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA_longValue());
			m_BG_Vektor.getStation2().setID_ADRESSE_BESITZER_LDG(m_BG_Vektor.getStation1().getID_ADRESSE_BESITZER_LDG().Value());

			_vVektor.addElement(m_BG_Vektor);
			
			// preise im Atom aus RG-pos lesen
			setRGValuesInAtomFromFuhre(m_BG_Vektor, oRecFuhre, EnTabKeyInMask.A2);
			
			// Preise von Atom 2 (kunde) nach atom2 (lager) übertragen
			m_BG_Vektor.getATOM_1().setE_PREIS_BASISWAEHRUNG(m_BG_Vektor.getATOM_2().getE_PREIS_BASISWAEHRUNG().bdValue);
			m_BG_Vektor.getATOM_1().setE_PREIS_FREMDWAEHRUNG(m_BG_Vektor.getATOM_2().getE_PREIS_FREMDWAEHRUNG().bdValue);
			
			m_BG_Vektor.getATOM_1().setE_PREIS_RES_NETTO_MGE_BASIS(m_BG_Vektor.getATOM_2().getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);
			m_BG_Vektor.getATOM_1().setE_PREIS_RES_NETTO_MGE_FREMD(m_BG_Vektor.getATOM_2().getE_PREIS_RES_NETTO_MGE_FREMD().bdValue);

			m_BG_Vektor.getATOM_1().setG_PREIS_BASISWAEHRUNG(m_BG_Vektor.getATOM_2().getG_PREIS_BASISWAEHRUNG().bdValue);
			m_BG_Vektor.getATOM_1().setG_PREIS_FREMDWAEHRUNG(m_BG_Vektor.getATOM_2().getG_PREIS_FREMDWAEHRUNG().bdValue);
			
			m_BG_Vektor.getATOM_1().setG_PREIS_ABZUG_BASIS(m_BG_Vektor.getATOM_2().getG_PREIS_ABZUG_BASIS().bdValue);
			m_BG_Vektor.getATOM_1().setG_PREIS_ABZUG_FREMD(m_BG_Vektor.getATOM_2().getG_PREIS_ABZUG_FREMD().bdValue);
			

			
			
		} else {
			// FEHLER
		}

		

		//
		//  Vektor-Positionen
		//
		
		jt_bg_atom atomOrt = null;

		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab()) ) {
				Rec21_VPOS_TPA_FUHRE_ORT_ext ort = new Rec21_VPOS_TPA_FUHRE_ORT_ext(o);	
				boolean bLadeseite = ort.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equalsIgnoreCase("EK");
				
				// Start und Ziel definieren
				if (bLadeseite){
					bStartIsLager = m_helper.m_vEigeneLieferadressen.contains(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "*") );
					bZielIsLager  =  m_helper.m_vEigeneLieferadressen.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel, "*") ) ;
				} else {
					bStartIsLager =  m_helper.m_vEigeneLieferadressen.contains(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "*")) ;
					bZielIsLager  = m_helper.m_vEigeneLieferadressen.contains(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "*"));
				}
				
				jt_bg_vektor vOrt ;
				
				
				// Fuhrenort generieren
				if (bStartIsLager && bZielIsLager){
					//LL
					m_transportTyp = EnTransportTyp.LAGER_LAGER;
					
					// generiere den Bewegungssatz und übernehme die Daten
					vOrt= generiereVectorAusFuhre_Basisdaten(oRecFuhre,ort);
					vOrt.setEN_TRANSPORT_TYP(m_transportTyp);
					
					generiereAtome_FuhrenORT(vOrt, oRecFuhre, ort);
					
					// zusätzliche Daten setzen
					vOrt.setPLANMENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_planmenge));
					
					vOrt.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
					vOrt.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
					
					vOrt.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
					vOrt.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
							
					vOrt.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER_longValue());
					vOrt.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
					
					this.initLagerpreise(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, "-1"));
					
					_vVektor.addElement(vOrt);
					
//					setzeLagerPreise(_vVektor);
					
				} else if (!bStartIsLager && !bZielIsLager){
					// STRECKE
					m_transportTyp = EnTransportTyp.STRECKE;
					
					// Abhängig ob lade oder abladeseite...
					if (ort.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equalsIgnoreCase("EK")){
//						atomOrt.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.LEFT);
					} else {
//						atomOrt.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.RIGHT);
					}
					// generiere den Bewegungssatz und übernehme die Daten
					vOrt= generiereVectorAusFuhre_Basisdaten(oRecFuhre,ort);
					vOrt.setEN_TRANSPORT_TYP(m_transportTyp);
					
					generiereAtome_FuhrenORT(vOrt, oRecFuhre, ort);
					
					// zusätzliche Daten setzen
					vOrt.setPLANMENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_planmenge));
					
					vOrt.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
					vOrt.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
					
					vOrt.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
					vOrt.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
							
					vOrt.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
					vOrt.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
					
					_vVektor.addElement(vOrt);
					
				} else if (bStartIsLager){
					m_transportTyp = EnTransportTyp.WA;
					
					// generiere den Bewegungssatz und übernehme die Daten
					vOrt= generiereVectorAusFuhre_Basisdaten(oRecFuhre,ort);
					vOrt.setEN_TRANSPORT_TYP(m_transportTyp);
					
					generiereAtome_FuhrenORT(vOrt, oRecFuhre, ort);
					
					// Preise von Atom 2 (kunde) nach atom2 (lager) übertragen
					vOrt.getATOM_1().setE_PREIS_BASISWAEHRUNG(vOrt.getATOM_2().getE_PREIS_BASISWAEHRUNG().bdValue);
					vOrt.getATOM_1().setE_PREIS_RES_NETTO_MGE_BASIS(vOrt.getATOM_2().getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);
					
					
					// zusätzliche Daten setzen
					vOrt.setPLANMENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_planmenge));
					
					vOrt.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
					vOrt.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
					
					vOrt.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
					vOrt.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
							
					
					
					vOrt.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA_longValue());
					// ladungsbesitzer
					vOrt.getStation2().setID_ADRESSE_BESITZER_LDG(m_BG_Vektor.getStation1().getID_ADRESSE_BASIS().Value());

					_vVektor.addElement(vOrt);				

				} else if (bZielIsLager) {
					m_transportTyp = EnTransportTyp.WE;
					
					// generiere den Bewegungssatz und übernehme die Daten
					vOrt= generiereVectorAusFuhre_Basisdaten(oRecFuhre,ort);
					vOrt.setEN_TRANSPORT_TYP(m_transportTyp);
					
					generiereAtome_FuhrenORT(vOrt, oRecFuhre, ort);
					
					// Preise von Atom 1 (kunde) nach atom2 (lager) übertragen
					vOrt.getATOM_2().setE_PREIS_BASISWAEHRUNG(vOrt.getATOM_1().getE_PREIS_BASISWAEHRUNG().bdValue);
					vOrt.getATOM_2().setE_PREIS_RES_NETTO_MGE_BASIS(vOrt.getATOM_1().getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);
					
					
					// zusätzliche Daten setzen
					vOrt.setPLANMENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_planmenge));
					
					vOrt.setDATUM_PLANUNG_VON(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
					vOrt.setDATUM_PLANUNG_BIS(oRecFuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
					
					vOrt.setTRANSPORTMITTEL(oRecFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
					vOrt.setID_ADRESSE_SPEDITION(oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
							
					
					
					vOrt.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE_longValue());
					// ladungsbesitzer
					vOrt.getStation2().setID_ADRESSE_BESITZER_LDG(m_BG_Vektor.getStation3().getID_ADRESSE_BASIS().Value());

					
					
					_vVektor.addElement(vOrt);
					
					
				} else {
					//FEHLER
				}
			}
		}
		
		// LL-Preisanteile setzen
		setzeLagerPreise(_vVektor);
		
		checkAndCompletePriceEntries( _vVektor );		
		
		return _vVektor;
		
	}
	
	
	
	
	

	/**
	 * Abhängig vom Typ wird der Preis ergänzt...
	 */
	@Override
	protected void checkAndCompletePriceEntries(Vector<jt_bg_vektor> vVektor) {
//			HashMap<Long, BigDecimal> hmKundenPreise = new HashMap<>();
//			// erst die Kundenpreise ermitteln
//			
//			for (jt_bg_vektor vektor: vVektor){
//				jt_bg_atom    	atom1 = vektor.getATOM_1();
//				jt_bg_atom 		atom2 = vektor.getATOM_2();
//				jt_bg_station 	sStart = vektor.getStation1();
//				jt_bg_station 	sZiel = vektor.getStation3();
//				
//				String adrStart = sStart.getID_ADRESSE().ValuePlain();
//				String adrZiel 	= sZiel.getID_ADRESSE().ValuePlain();
//				
//				if (!m_helper.m_vEigeneLieferadressen.contains(adrStart)){
//						hmKundenPreise.put(atom1.getID_ARTIKEL().lValue , atom1.getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);
//				}
//			}
//
//			for (jt_bg_vektor vektor: vVektor){
//				jt_bg_atom    	atom1 = vektor.getATOM_1();
//				jt_bg_atom 		atom2 = vektor.getATOM_2();
//				jt_bg_station 	sStart = vektor.getStation1();
//				jt_bg_station 	sZiel = vektor.getStation3();
//				
//				String adrStart = sStart.getID_ADRESSE().ValuePlain();
//				String adrZiel 	= sZiel.getID_ADRESSE().ValuePlain();
//				
//				// wenn es eine Lageradresse ist, schauen, ob es die gleiche Sorte auf der Lieferseite gibt, 
//				if (m_helper.m_vEigeneLieferadressen.contains(adrZiel)){
//					if( atom2.getE_PREIS_RES_NETTO_MGE_BASIS().bdValue == null ||
//						atom2.getE_PREIS_RES_NETTO_MGE_BASIS().bdValue.equals(BigDecimal.ZERO) ) 
//					{
//						if (hmKundenPreise.containsKey(atom2.getID_ARTIKEL().lValue)) {
//							atom2.setE_PREIS_RES_NETTO_MGE_BASIS(hmKundenPreise.get(atom2.getID_ARTIKEL().lValue));
//							atom2.setE_PREIS_BASISWAEHRUNG(hmKundenPreise.get(atom2.getID_ARTIKEL().lValue));
//						}
//						
//					}
//				}
//			}
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
