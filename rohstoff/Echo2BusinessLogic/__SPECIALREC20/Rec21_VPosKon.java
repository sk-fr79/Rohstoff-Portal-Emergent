package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;

import panter.gmbh.basics4project.ENUM_UNICODE;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;

public class Rec21_VPosKon extends Rec21 {
	
	private Rec21  recVposKonTrakt = null;
	
	public Rec21_VPosKon(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	public Rec21_VPosKon() throws myException {
		super(_TAB.vpos_kon);
	}

	public String get_vkopf_id() throws myException{
		return this.get_fs_dbVal(VPOS_KON.id_vkopf_kon,"");
	}

	public String get_vpos_kon_id() throws myException{
		return this.get_fs_dbVal(VPOS_KON.id_vpos_kon,"");
	}

	public String get_fixiert_menge() throws myException{

		Rec21 recKopf = this.get_up_Rec21(VPOS_KON.id_vkopf_kon,VKOPF_KON.id_vkopf_kon, false);

		RecList21 position_rec_list = recKopf.get_down_reclist21(
				VPOS_KON.id_vkopf_kon, "NVL("+VPOS_KON.deleted+", 'N')='N'", 
				VPOS_KON.id_vpos_kon.fieldName()
				);

		MyBigDecimal fixiert_sum = new MyBigDecimal("0");
		for(Rec20 position_rec : position_rec_list){
			fixiert_sum.add_to_me(position_rec.get_myBigDecimal_dbVal(VPOS_KON.anzahl));
		}

		return fixiert_sum.get_FormatedRoundedNumber(3);
	}

	public Rec21 get_rec_vpos_kon_trakt() throws myException{
		if (this.recVposKonTrakt==null) {
			this.recVposKonTrakt=this.get_down_reclist21(VPOS_KON_TRAKT.id_vpos_kon, "","").get(0);
		}
		return this.recVposKonTrakt;
	}


	public int get_how_many_fuhre_are_associated_with_position(boolean isEk) throws myException{
		int nb_of_fuhre = 0;
		if(isEk){
			
			
			
			//fuhre
			RecList21 fuhre_ek_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_kon_ek, 
					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
					, "");
			nb_of_fuhre += fuhre_ek_rec_list.size();

			//fuhre ort
			RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
					"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
					"");

			nb_of_fuhre += fuhre_ort_rec_list.size();

		}else{
			//fuhre
			RecList21 fuhre_vk_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_kon_vk, 
					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
					, "");
			nb_of_fuhre += fuhre_vk_rec_list.size();

			//fuhre ort
			RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
					"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
					"");

			nb_of_fuhre += fuhre_ort_rec_list.size();
		}


		return nb_of_fuhre;
	}

	
	
	
	public BigDecimal getMengeNochOffen() {
		BigDecimal bdRet  = null;
		
		try {
			bdRet = 	 O.NN((BigDecimal)this.getRawResultValue(VPOS_KON.anzahl.fn()),BigDecimal.ZERO);
			MyBigDecimal bdFuhrenMenge = get_gesamt_fuhre_menge();
			
			if (bdFuhrenMenge.isOK()) {
				bdRet = bdRet.subtract(bdFuhrenMenge.get_bdWert());
			}
		} catch (myException e) {
			e.printStackTrace();
			bdRet = null;
		}
		
		return bdRet;
	}
	
	
	
	public MyBigDecimal get_gesamt_fuhre_menge() throws myException{
		return this.get_gesamt_fuhre_menge(this.getVorgangTyp()==ENUM_VORGANGSART.EK_KONTRAKT);
	}
	

	public MyBigDecimal get_gesamt_fuhre_menge(boolean isEk) throws myException{

		MyBigDecimal summe = new MyBigDecimal(0l);

		if(isEk){
			RecList21 fuhre_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_kon_ek, 
					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
					, "");

			for(Rec21 rec_fuhre:fuhre_rec_list){
				summe.add_to_me(rec_fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, new MyBigDecimal(0l)));

				RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
						"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
						"");
				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
					summe.add_to_me(fuhre_ort_rec.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_lademenge, new MyBigDecimal(0l)));
				}
			}
		}else{
			RecList21 fuhre_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_kon_vk, 
					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
					, "");


			for(Rec21 rec_fuhre:fuhre_rec_list){
				summe.add_to_me(rec_fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, new MyBigDecimal(0l)));

				RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_kon, 
						"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'VK'", 
						"");
				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
					summe.add_to_me(fuhre_ort_rec.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge, new MyBigDecimal(0l)));
				}		
			}	
		}

		return summe;
	}
	
	
	public MyBigDecimal get_gesamt_fuhre_planmenge() throws myException{
		return this.get_gesamt_fuhre_planmenge(this.getVorgangTyp()==ENUM_VORGANGSART.EK_KONTRAKT);
	}
	
	
	public MyBigDecimal get_gesamt_fuhre_planmenge(boolean isEk) throws myException{

		MyBigDecimal summe = new MyBigDecimal(0l);

		if(isEk){
			RecList21 fuhre_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_kon_ek, 
					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
					, "");

			for(Rec21 rec_fuhre:fuhre_rec_list){
				
				summe.add_to_me(new Rec21_VposTpaFuhre(rec_fuhre).get_myBigDecimal_plan_menge(isEk, 0));
				
				RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
						"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
						"");
				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
					summe.add_to_me(new Rec21_VposTpaFuhre(fuhre_ort_rec).get_myBigDecimal_plan_menge(isEk, 0));
				}
			}
		}else{
			RecList21 fuhre_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_kon_vk, 
					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
					, "");


			for(Rec21 rec_fuhre:fuhre_rec_list){
				
				summe.add_to_me(new Rec21_VposTpaFuhre(rec_fuhre).get_myBigDecimal_plan_menge(isEk, 0));
				
				RecList21 fuhre_ort_rec_list = this.get_down_reclist21( VPOS_TPA_FUHRE_ORT.id_vpos_kon, 
						"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'VK'", 
						"");
				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
					summe.add_to_me(new Rec21_VposTpaFuhre(fuhre_ort_rec).get_myBigDecimal_plan_menge(isEk, 0));
				}		
			}	
		}

		return summe;
	}
	
	public String get_gueltig_von() throws myException{
		Rec21 rec_kont_trakt = this.get_down_reclist21(VPOS_KON_TRAKT.id_vpos_kon, "", "").get(0);
		
		return rec_kont_trakt.get_myDate_dbVal(VPOS_KON_TRAKT.gueltig_von).get_cDateStandardFormat();
	}
	
	public String get_gueltig_bis() throws myException{
		Rec21 rec_kont_trakt = this.get_down_reclist21(VPOS_KON_TRAKT.id_vpos_kon, "", "").get(0);
		
		return rec_kont_trakt.get_myDate_dbVal(VPOS_KON_TRAKT.gueltig_bis).get_cDateStandardFormat();
	}
	

	public String get_sorte() {
		String c_ret= "";
		
		try {
			Rec21 rec_artikel_bez = this.get_up_Rec21(ARTIKEL_BEZ.id_artikel_bez);
			Rec21 rec_artikel    = rec_artikel_bez.get_up_Rec21(ARTIKEL.id_artikel);
			
			c_ret = rec_artikel.get_ufs_dbVal(ARTIKEL.anr1)+"-"+rec_artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.anr2)+" "+rec_artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.artbez1);
		} catch (myException e) {
			e.printStackTrace();
		}
		return c_ret;
	}
	
	/**
	 * @return die Fremd Waehrung (z.B. EUR)
	 * @throws myException
	 */
	public String get_waehrung_fremd() throws myException{
		return this.get_up_Rec21(VPOS_KON.id_waehrung_fremd,WAEHRUNG.id_waehrung, false).get_ufs_dbVal(WAEHRUNG.kurzbezeichnung,"");
	}
	
	
	
	
	/**
	 * @throws myException 
	 * returns "10.04.2017 - 31.12.2017"
	 */
	public String getGueltigkeit() throws myException {
		Rec20 vposKon = this.get_rec_vpos_kon_trakt();
		String c_ret = vposKon.get_fs_dbVal(VPOS_KON_TRAKT.gueltig_von,"-")+" - "+vposKon.get_fs_dbVal(VPOS_KON_TRAKT.gueltig_bis,"-");
		return c_ret;
	}
	
	/**
	 * @throws myException 
	 * returns "0104-02"
	 */
	public String getAnr12() throws myException {
		String c_ret = this.get_fs_dbVal(VPOS_KON.anr1,"-")+" - "+this.get_fs_dbVal(VPOS_KON.anr2);
		return c_ret;
	}
	
	
	/**
	 * @throws myException 
	 * returns "2.003,23 EUR/t"
	 */
	public String getPreisMitEinheiten_FW() throws myException {
		String cWaehrung = 	this.get_up_Rec20(VPOS_KON.id_waehrung_fremd, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.kurzbezeichnung,  "?");
		if (cWaehrung.toUpperCase().equals("EUR")) {
			cWaehrung=ENUM_UNICODE.EUR.getCode();
		}

		String c_ret = this.get_myBigDecimal_dbVal(VPOS_KON.einzelpreis_fw, new MyBigDecimal(0)).get_FormatedRoundedNumber(2);
		c_ret=c_ret+" "+cWaehrung;
		c_ret=c_ret+"/"+this.get_fs_dbVal(VPOS_KON.einheit_preis_kurz,"");

		return c_ret;
	}
	
	
	/**
	 * @throws myException 
	 * returns "2.003,23 EUR/t"
	 */
	public String getGesPreisMitEinheiten_FW() throws myException {
		String cWaehrung = 	this.get_up_Rec20(VPOS_KON.id_waehrung_fremd, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.kurzbezeichnung,  "?");

		if (cWaehrung.toUpperCase().equals("EUR")) {
			cWaehrung=ENUM_UNICODE.EUR.getCode();
		}
		
		String c_ret = this.get_myBigDecimal_dbVal(VPOS_KON.gesamtpreis_fw, new MyBigDecimal(0)).get_FormatedRoundedNumber(2);
		c_ret=c_ret+" "+cWaehrung;
		c_ret=c_ret+"/"+this.get_fs_dbVal(VPOS_KON.einheit_preis_kurz,"");

		return c_ret;
	}

	
	/**
	 * @throws myException 
	 * returns "2.455,32 kg"
	 */
	public String getMengeMitEinh(int round) throws myException {
		String c_ret = this.get_myBigDecimal_dbVal(VPOS_KON.anzahl, new MyBigDecimal(0)).get_FormatedRoundedNumber(round);
		c_ret=c_ret+" "+this.get_fs_dbVal(VPOS_KON.einheitkurz, "");

		return c_ret;
	}
	

	
	/**
	 * 
	 * @param date
	 * @return s true, when date is in daterange of contract
	 * @throws myException
	 */
	public boolean isDateInKontaktRange(MyDate  testDate) throws myException {
		MyDate gueltig_von = this.get_rec_vpos_kon_trakt().get_myDate_dbVal(VPOS_KON_TRAKT.gueltig_von);
		MyDate gueltig_bis = this.get_rec_vpos_kon_trakt().get_myDate_dbVal(VPOS_KON_TRAKT.gueltig_bis);
		
		if (gueltig_von.isOK() && gueltig_bis.isOK() &&  testDate.isOK() ) {
			return (gueltig_von.isLessEqualThan(testDate) && gueltig_bis.isGreaterEqualThan(testDate));
		} else {
			throw new myException(this, "Only myDate-Objects which are ok allowed");
		}
	}
	
	
	public ENUM_VORGANGSART getVorgangTyp() {
		try {
			return ENUM_VORGANGSART.findENUM_VORGANGSART(this.get_up_Rec21(VKOPF_KON.id_vkopf_kon).getUfs(VKOPF_KON.vorgang_typ));
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public Rec21 getVposKonTrakt() throws myException {
		return this.get_down_reclist21(VPOS_KON_TRAKT.id_vpos_kon).get(0);
	}
	
	
	public String getGueltigkeitsZeitraum() throws myException {
		String ret = "";
		
		if (S.isFull(this.getVposKonTrakt().getFs(VPOS_KON_TRAKT.gueltig_von))) {
			ret = this.getVposKonTrakt().getFs(VPOS_KON_TRAKT.gueltig_von);
			
			if (S.isFull(this.getVposKonTrakt().getFs(VPOS_KON_TRAKT.gueltig_bis))) {
				ret = ret +"-"+this.getVposKonTrakt().getFs(VPOS_KON_TRAKT.gueltig_bis);
			}
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 08.01.2019
	 *
	 * @return s string with complete buchungsnummer or null
	 */
	public String getBuchungsNummer() {
		String ret = null;
		
		try {
			Rec21 recKopf = this.get_up_Rec21(VKOPF_KON.id_vkopf_kon);
			if (recKopf != null) {
				ret = recKopf.getUfs(VKOPF_KON.buchungsnummer, "<"+recKopf.getFs(VKOPF_KON.id_vkopf_kon)+">")+"-"+this.getFs(VPOS_KON.positionsnummer,"-");
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	public Rec21_adresse getMainAdressOfKontrakt() {
		Rec21_adresse rec = null;
		
		try {
			if (this.is_ExistingRecord()) {
				Rec21 r = this.get_up_Rec21(VKOPF_KON.id_vkopf_kon).get_up_Rec21(ADRESSE.id_adresse);
				if (r!=null && r.is_ExistingRecord()) {
					rec = new Rec21_adresse(r)._getMainAdresse();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rec;
	}
	
	
	public Rec21_VKopfKon getVkopfKon() throws myException {
		Rec21_VKopfKon recKopf = null;
		
		if (this.is_ExistingRecord()) {
			recKopf = (Rec21_VKopfKon) new Rec21_VKopfKon()._fill_id(this.getLongDbValue(VPOS_KON.id_vkopf_kon));
		}
		
		
		return recKopf;
	}
	
	
	/**
	 * 2020-03-06: martin
	 */
	public Rec21_VPosKon _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
}