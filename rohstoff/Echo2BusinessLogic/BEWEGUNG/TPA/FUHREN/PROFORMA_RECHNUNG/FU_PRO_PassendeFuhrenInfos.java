package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_PROFORMA_RECHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FU_PRO_PassendeFuhrenInfos extends HashMap<String, Integer> {
	public static String KEY_ID_VPOS_TPA_FUHRE = 				"ID_VPOS_TPA_FUHRE"; 
	public static String KEY_ID_VPOS_TPA_FUHRE_ORT = 			"ID_VPOS_TPA_FUHRE_ORT";
	public static String KEY_ID_ADRESSE_ZIEL_FUHRE = 			"KEY_ID_ADRESSE_ZIEL_FUHRE";					//Abnehmer-Adresse
	public static String KEY_ID_ADRESSE_LAGER_ZIEL_FUHRE = 	"KEY_ID_ADRESSE_LAGER_ZIEL_FUHRE";					//Abnehmer-Adresse
	
	//weitere felder fuer die neuen felder des fuhrensonderfalls
	private RECORD_PROFORMA_RECHNUNG    recProforma = 	null;											//Zusatzinfos zur proforma-rechnung
	


	private RECORD_VPOS_TPA_FUHRE 		     recFuhre = 	null;
	private RECORD_VPOS_TPA_FUHRE_ORT       recFuhreOrt = 	null;
	
	
	public FU_PRO_PassendeFuhrenInfos(
						int iID_VPOS_TPA_FUHRE, 
						int iID_VPOS_TPA_FUHRE_ORT, 
						int iID_ADRESSE_ZIELFUHRE, 
						int iID_ADRESSE_LAGER_ZIELFUHRE,
						RECORD_PROFORMA_RECHNUNG  RecProforma) throws myException {
		this.put(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE, 				iID_VPOS_TPA_FUHRE);
		this.put(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE_ORT, 			iID_VPOS_TPA_FUHRE_ORT);
		this.put(FU_PRO_PassendeFuhrenInfos.KEY_ID_ADRESSE_ZIEL_FUHRE, 			iID_ADRESSE_ZIELFUHRE);
		this.put(FU_PRO_PassendeFuhrenInfos.KEY_ID_ADRESSE_LAGER_ZIEL_FUHRE, 	iID_ADRESSE_LAGER_ZIELFUHRE);
		this.recProforma = RecProforma;
		
		if (this.recProforma == null) {
			throw new myException("Error: Null value in RECORD_PROFORMA_RECHNUNG not allowed ! ");
		}
		
		this.recFuhre = new RECORD_VPOS_TPA_FUHRE(this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE));
		this.recFuhreOrt   = null;
		if ( this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE_ORT)>0) {
			this.recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE_ORT));
		}

		
	}
	
	
	public String get_cID_ADRESSE_ZielFuhre_UF() {
		return ""+this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_ADRESSE_ZIEL_FUHRE);
	}
	public String get_cID_ADRESSE_LAGER_ZielFuhre_UF() {
		return ""+this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_ADRESSE_LAGER_ZIEL_FUHRE);
	}

	
	public String get_cID_VPOS_TPA_FUHRE_UF() {
		return ""+this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE);
	}
	
	/**
	 * liefert -1, wenn es kein fuhrenort ist
	 * @return
	 */
	public String get_cID_VPOS_TPA_FUHRE_ORT_UF() {
		return ""+this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE_ORT);
	}
	public int get_iID_ADRESSE() {
		return this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_ADRESSE_ZIEL_FUHRE);
	}
	public int get_iID_VPOS_TPA_FUHRE() {
		return this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE);
	}
	public int get_iID_VPOS_TPA_FUHRE_ORT() {
		return this.get(FU_PRO_PassendeFuhrenInfos.KEY_ID_VPOS_TPA_FUHRE_ORT);
	}
	
	public MyE2_String get_Text4Info() throws myException {
		MyE2_String cRueck = null;
		
		
		if (this.recFuhreOrt == null) {
			
			RECORD_ARTIKEL_BEZ 	recArtBez = this.recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
			RECORD_ARTIKEL  	recArtikel = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel();     
			
			String cInfos1 = this.recFuhre.get_ANTEIL_ABLADEMENGE_ABN_cF_NN("0")+" "+recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cF_NN("-")+" "+
			                 recArtikel.get_ANR1_cUF_NN("<anr1>")+" "+recArtBez.get_ANR2_cUF_NN("<anr2>")+" "+recArtBez.get_ARTBEZ1_cUF_NN(recArtikel.get_ARTBEZ1_cUF_NN(""));
			
			cRueck = new MyE2_String("Haupt-Fuhre (",true,this.recFuhre.get_ID_VPOS_TPA_FUHRE_cF_NN("-"),false,"):  ",false,cInfos1,false," ------> ",false, this.recFuhre.get___KETTE(bibVECTOR.get_Vector(
					_DB.VPOS_TPA_FUHRE$A_NAME1,
					_DB.VPOS_TPA_FUHRE$A_ORT)),false);
		} else {
			
			RECORD_ARTIKEL_BEZ 	recArtBez = this.recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
			RECORD_ARTIKEL  	recArtikel = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel();     
			
			String cInfos1 = this.recFuhre.get_ANTEIL_ABLADEMENGE_ABN_cF_NN("0")+" "+recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cF_NN("-")+" "+
			                 recArtikel.get_ANR1_cUF_NN("<anr1>")+" "+recArtBez.get_ANR2_cUF_NN("<anr2>")+" "+recArtBez.get_ARTBEZ1_cUF_NN(recArtikel.get_ARTBEZ1_cUF_NN(""));

			
			cRueck = new MyE2_String("Zusatzort zu Fuhre (",true,this.recFuhre.get_ID_VPOS_TPA_FUHRE_cF_NN("-"),false,"):  ",false,cInfos1,false, this.recFuhreOrt.get___KETTE(bibVECTOR.get_Vector(
					_DB.VPOS_TPA_FUHRE_ORT$NAME1,
					_DB.VPOS_TPA_FUHRE_ORT$ORT)),false);
		}
		return cRueck;
	}

	
	public RECORD_PROFORMA_RECHNUNG get_RecProforma() {
		return recProforma;
	}

	public RECORD_VPOS_TPA_FUHRE get_oRecFuhre() {
		return recFuhre;
	}


	public RECORD_VPOS_TPA_FUHRE_ORT get_oRecFuhreOrt() {
		return recFuhreOrt;
	}


	public String get_RelevantPrice() throws myException {
		if (this.recFuhreOrt != null) {
			return this.recFuhreOrt.get_EINZELPREIS_cF_NN("0");
		} else {
			return this.recFuhre.get_EINZELPREIS_VK_cF_NN("0");
		}
	}
	
	public BigDecimal get_RelevantPriceDB() throws myException {
		if (this.recFuhreOrt != null) {
			return this.recFuhreOrt.get_EINZELPREIS_bdValue(BigDecimal.ZERO);
		} else {
			return this.recFuhre.get_EINZELPREIS_VK_bdValue(BigDecimal.ZERO);
		}
	}


	
	public boolean get_bSonderFall() throws myException {
		return this.get_cID_ADRESSE_ZielFuhre_UF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
	}
	
	
	
	public String get_NameOfReport() throws myException {
		if (this.get_bSonderFall()) {
			return FU_PRO_CONST.REPORTFILE_EXTENDED;
		} else {
			return FU_PRO_CONST.REPORTFILE_STANDARD;
		}
	}
	
}
