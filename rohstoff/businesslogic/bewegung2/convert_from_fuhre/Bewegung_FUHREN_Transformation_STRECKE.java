package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

public class Bewegung_FUHREN_Transformation_STRECKE extends Bewegung_Fuhren_Transformation {


	public Bewegung_FUHREN_Transformation_STRECKE( conv_helper helper) throws myException {
		super(helper);
		m_transportTyp = EnTransportTyp.STRECKE;
	}

	

	public  Vector<jt_bg_vektor> transformiereFuhre(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException {
	
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
		m_BG_Vektor.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
		// ladungsbesitzer
		m_BG_Vektor.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));

		_vVektor.addElement(m_BG_Vektor);
		
		// preise im Atom aus RG-pos lesen
		setRGValuesInAtomFromFuhre(m_BG_Vektor, oRecFuhre, EnTabKeyInMask.A1);
		setRGValuesInAtomFromFuhre(m_BG_Vektor, oRecFuhre, EnTabKeyInMask.A2);
		
		
		//
		// Fuhrenorte
		//
		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab()) ) {
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
						
				
				
				vOrt.getStation2().setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue());
				// ladungsbesitzer
				vOrt.getStation2().setID_ADRESSE_BESITZER_LDG(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
				
				_vVektor.addElement(vOrt);
			}
		}

		return _vVektor;
		
	}
	
	

	
	

}
