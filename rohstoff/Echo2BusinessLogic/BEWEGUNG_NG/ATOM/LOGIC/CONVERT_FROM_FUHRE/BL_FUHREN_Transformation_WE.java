package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public class BL_FUHREN_Transformation_WE extends BL_FUHREN_Transformation {


	private long posNrVektorInBewegung = 1L;
	private long posNrAtomInVektor = 1L;
	private long posNrVektorPosInVektor = 1L;
	
	
	public BL_FUHREN_Transformation_WE(Vector<String> vLagerOrte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel, HashMap<String, String> hmLieferbedingungen) throws myException {
		super(vLagerOrte,objUmrechnung,hmArtikeBezZuordnung,  vLeergutartikel,hmLieferbedingungen);
		
		m_typ_strecke_lager_mixed = 1;

	}

	
	
	
	/**
	 * 
	 */
	public JtBewegung transformiereFuhre(RECORD_VPOS_TPA_FUHRE oRecFuhre) throws myException {
		// Fuhrendaten initialisieren	
		this.initFuhre(oRecFuhre);
		
		
		
		// generiere den Bewegungssatz und übernehme die Daten
		m_Bewegung = new JtBewegung(oRecFuhre);

		// Atom für die berechnung der Sortenwechsel bei Fuhrenorten auf der Lagerseite
		JtBewegung_Atom oAtomReferenzSW = null;
		
		
		//
		// Vector für WE / Haupfuhre
		//
		JtBewegung_Vektor vecFuhre = generiereVectorAusFuhre_Basisdaten(oRecFuhre);
		vecFuhre.setPosnr(posNrVektorInBewegung++);
		vecFuhre.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		vecFuhre.setVariante(ENUM_VEKTOR_TYP.WE.name());
		
		vecFuhre.setJtBewegung(m_Bewegung);
		
		// den Vektor der Fuhre in Bewegung einfügen
		m_Bewegung.getJtBewegungsvektors().add(vecFuhre);
		
		
		posNrVektorPosInVektor = 1L;

		
		
		JtBewegung_Vektor_Pos vecPosHautpfuhre = generiere_VPos_Hauptfuhre(oRecFuhre, vecFuhre, posNrVektorPosInVektor++,ENUM_SONDERLAGER.ZWE);
		
		//
		// Fuhrenorte
		//
		JtBewegung_Vektor_Pos vecOrt = null;
		
		// Fuhrenorte konvertieren
		for (RECORD_VPOS_TPA_FUHRE_ORT ort : m_rlOrt.values()){

			if (ort.get_DEF_QUELLE_ZIEL_cUF().equals("EK")){
				vecOrt = generiereVektorPosUndAtome_FuhrenORT_Ladeseite(vecFuhre, m_oFuhre, ort, posNrVektorPosInVektor++, ENUM_SONDERLAGER.ZWE,false);
			} else {
				vecOrt = generiereVektorPosUndAtome_FuhrenORT_Abladeseite(vecFuhre, m_oFuhre, ort, posNrVektorPosInVektor++, ENUM_SONDERLAGER.ZWE,false);
			}
				
			//m_Bewegung.getJtBewegungsvektors().add(vecOrt);
		}

		
		return m_Bewegung;
		
	}
	

	

	




	
}
