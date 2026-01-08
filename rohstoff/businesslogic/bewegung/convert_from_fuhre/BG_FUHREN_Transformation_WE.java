package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BG_FUHREN_Transformation_WE extends BG_FUHREN_Transformation {


	public BG_FUHREN_Transformation_WE(	Vector<String> vLagerOrte, 
										GROESSEN_Umrechnung objUmrechnung, 
										HashMap<String, String> hmArtikeBezZuordnung, 
										Vector<String> vLeergutartikel, 
										HashMap<String, String> hmLieferbedingungen ,
										HashMap<String, Long> hmLaendercode) throws myException {
		super(vLagerOrte,objUmrechnung,hmArtikeBezZuordnung,  vLeergutartikel,hmLieferbedingungen, hmLaendercode);
		m_vektor_typ = EN_VEKTOR_TYP.WE;
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
		
		// preise im Atom aus RG-pos lesen
		int nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start,"-") ) ? -1: 1 ;
		setRGValuesInAtomFromFuhre(atom_fuhre, oRecFuhre, nLagervorzeichen);
		
		
		//
		// Fuhrenorte
		//
		long posnr = 2;
		jt_bg_atom atomOrt = null;
		
		// Fuhrenorte konvertieren
		for (Rec21 o : m_rlOrt.values()){
			if (o.get_tab().equals(VPOS_TPA_FUHRE_ORT._tab()) ) {
				Rec20_VPOS_TPA_FUHRE_ORT_ext ort = new Rec20_VPOS_TPA_FUHRE_ORT_ext(o);	
				
				atomOrt = generiereAtom_FuhrenORT(m_BG_Vektor, oRecFuhre, ort, posnr++);
				
				//Preisdaten aus der Rechnungsposition lesen
				setRGValuesInAtomFromFuhreOrt(atomOrt, ort);
				
			}
		}
		
		// wenn es zusatzorte gibt, auch die Preise noch vervollständigen
		if (m_rlOrt.size() > 0){
			checkAndCompletePriceEntries();
		}
		
		return m_BG_Vektor;
		
	}
	

	

	




	
}
