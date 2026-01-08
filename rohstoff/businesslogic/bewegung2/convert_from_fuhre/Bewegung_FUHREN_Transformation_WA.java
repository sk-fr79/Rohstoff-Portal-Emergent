package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

public class Bewegung_FUHREN_Transformation_WA extends Bewegung_Fuhren_Transformation {

	
	public Bewegung_FUHREN_Transformation_WA(	 conv_helper helper)	throws myException {
		super(helper);
		m_transportTyp = EnTransportTyp.WA;
	}

	
		
	public Vector<jt_bg_vektor> transformiereFuhre(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException {
	
		Vector<jt_bg_vektor> _vVektor = new Vector<>();
		
		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
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
		m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA_longValue());
		// ladungsbesitzer
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
		
		
		
		//
		// Fuhrenorte
		//
		
		// Fuhrenorte konvertieren
		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab()) ) {
				Rec21_VPOS_TPA_FUHRE_ORT_ext ort = new Rec21_VPOS_TPA_FUHRE_ORT_ext(o);	
				
				// generiere den Bewegungssatz und übernehme die Daten
				jt_bg_vektor vOrt= generiereVectorAusFuhre_Basisdaten(oRecFuhre,ort);
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

			}
		}

		// wenn es zusatzorte gibt, auch die Preise noch vervollständigen
//		if (m_rlOrt.size() > 0){
			checkAndCompletePriceEntries( _vVektor );
//		}

		return _vVektor;
	}


	/**
	 *  Werte die in Verkaufspreise angegeben sind in den Lagerausgang übertragen
	 */
	@Override
	protected void checkAndCompletePriceEntries(Vector<jt_bg_vektor> vVektor) {
			
		    if (1==1) return;
		    
			HashMap<Long, BigDecimal> hmKundenPreise = new HashMap<>();
			// erst die Kundenpreise ermitteln
			
			for (jt_bg_vektor vektor: vVektor){
				jt_bg_atom    	atom1 = vektor.getATOM_1();
				jt_bg_atom 		atom2 = vektor.getATOM_2();
				jt_bg_station 	sStart = vektor.getStation1();
				jt_bg_station 	sZiel = vektor.getStation3();
				
				String adrStart = sStart.getID_ADRESSE().ValuePlain();
				String adrZiel 	= sZiel.getID_ADRESSE().ValuePlain();
				
				if (!m_helper.m_vEigeneLieferadressen.contains(adrStart)){
						hmKundenPreise.put(atom1.getID_ARTIKEL().lValue , atom1.getE_PREIS_RES_NETTO_MGE_BASIS().bdValue);
				}
			}

			for (jt_bg_vektor vektor: vVektor){
				jt_bg_atom    	atom1 = vektor.getATOM_1();
				jt_bg_atom 		atom2 = vektor.getATOM_2();
				jt_bg_station 	sStart = vektor.getStation1();
				jt_bg_station 	sZiel = vektor.getStation3();
				
				String adrStart = sStart.getID_ADRESSE().ValuePlain();
				String adrZiel 	= sZiel.getID_ADRESSE().ValuePlain();
				
				// wenn es eine Lageradresse ist, schauen, ob es die gleiche Sorte auf der Lieferseite gibt, 
				if (m_helper.m_vEigeneLieferadressen.contains(adrZiel)){
					if( atom2.getE_PREIS_RES_NETTO_MGE_BASIS().bdValue == null ||
						atom2.getE_PREIS_RES_NETTO_MGE_BASIS().bdValue.equals(BigDecimal.ZERO) ) 
					{
						if (hmKundenPreise.containsKey(atom2.getID_ARTIKEL().lValue)) {
							atom2.setE_PREIS_RES_NETTO_MGE_BASIS(hmKundenPreise.get(atom2.getID_ARTIKEL().lValue));
							atom2.setE_PREIS_BASISWAEHRUNG(hmKundenPreise.get(atom2.getID_ARTIKEL().lValue));
						}
						
					}
				}
			}
	}
	
	
}
