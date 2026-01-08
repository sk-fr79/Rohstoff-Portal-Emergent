package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.ENUM_UNICODE;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;

public class Rec21_VPosStd extends Rec21 {
	
	private Rec21  recVPosStdAngebot = null;
	
	public Rec21_VPosStd(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	public Rec21_VPosStd() throws myException {
		super(_TAB.vpos_std);
	}

	public String get_vkopf_id() throws myException{
		return this.get_fs_dbVal(VPOS_STD.id_vkopf_std,"");
	}

	public String get_vpos_std_id() throws myException{
		return this.get_fs_dbVal(VPOS_STD.id_vpos_std,"");
	}



	public Rec21 get_rec_vpos_std_angebot() throws myException{
		if (this.recVPosStdAngebot==null) {
			this.recVPosStdAngebot=this.get_down_reclist21(VPOS_STD_ANGEBOT.id_vpos_std, "","").get(0);
		}
		return this.recVPosStdAngebot;
	}


	public int get_how_many_fuhre_are_associated_with_position(boolean isEk) throws myException{
		int nb_of_fuhre = 0;

		IF_Field fieldEkVk = 	isEk?VPOS_TPA_FUHRE.id_vpos_std_ek:VPOS_TPA_FUHRE.id_vpos_std_vk;
		String   ekVk = 		isEk?"EK":"VK";
		
			
		//fuhre
		RecList21 fuhre_ek_rec_list = this.get_down_reclist21(fieldEkVk, 
				new And(new vgl_YN(VPOS_TPA_FUHRE.ist_storniert, false)).and(new vgl_YN(VPOS_TPA_FUHRE.deleted, false)).s()	, "");
		nb_of_fuhre += fuhre_ek_rec_list.size();

		//fuhre ort
		RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
				new And(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false)).and(new vgl(VPOS_TPA_FUHRE_ORT.def_quelle_ziel, ekVk)).s()	, "");
		nb_of_fuhre += fuhre_ort_rec_list.size();

		return nb_of_fuhre;
	}

	public MyBigDecimal get_gesamt_fuhre_menge() throws myException{
		return this.get_gesamt_fuhre_menge(this.getVorgangTyp()==ENUM_VORGANGSART.ABNAHMEANGEBOT);
	}
	


	public MyBigDecimal get_gesamt_fuhre_menge(boolean isEk) throws myException{

		MyBigDecimal summe = new MyBigDecimal(0l);
		
		IF_Field fieldEkVk = 			isEk?VPOS_TPA_FUHRE.id_vpos_std_ek:VPOS_TPA_FUHRE.id_vpos_std_vk;
		IF_Field mengeFieldFuhre = 		isEk?VPOS_TPA_FUHRE.anteil_lademenge_lief:VPOS_TPA_FUHRE.anteil_ablademenge_abn;
		IF_Field mengeFieldFuhreOrt = 	isEk?VPOS_TPA_FUHRE_ORT.anteil_lademenge:VPOS_TPA_FUHRE_ORT.anteil_ablademenge;
		String   ekVk = 				isEk?"EK":"VK";
		

		RecList21 fuhre_rec_list = this.get_down_reclist21(fieldEkVk, 
				new And(new vgl_YN(VPOS_TPA_FUHRE.ist_storniert, false)).and(new vgl_YN(VPOS_TPA_FUHRE.deleted, false)).s()	, "");

		for(Rec21 rec_fuhre:fuhre_rec_list){
			summe.add_to_me(rec_fuhre.get_myBigDecimal_dbVal(mengeFieldFuhre, new MyBigDecimal(0l)));

			RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
					new And(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false)).and(new vgl(VPOS_TPA_FUHRE_ORT.def_quelle_ziel, ekVk)).s()	, "");
			for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
				summe.add_to_me(fuhre_ort_rec.get_myBigDecimal_dbVal(mengeFieldFuhreOrt, new MyBigDecimal(0l)));
			}
		}

		return summe;
	}
	
	
//	public MyBigDecimal get_gesamt_fuhre_planmenge() throws myException{
//		return this.get_gesamt_fuhre_planmenge(this.getVorgangTyp()==ENUM_VORGANGSART.EK_STDTRAKT);
//	}
//	
//	
//	public MyBigDecimal get_gesamt_fuhre_planmenge(boolean isEk) throws myException{
//
//		MyBigDecimal summe = new MyBigDecimal(0l);
//
//		if(isEk){
//			RecList21 fuhre_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_std_ek, 
//					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
//					, "");
//
//			for(Rec21 rec_fuhre:fuhre_rec_list){
//				
//				summe.add_to_me(new Rec21_VposTpaFuhre(rec_fuhre).get_myBigDecimal_plan_menge(isEk, 0));
//				
//				RecList21 fuhre_ort_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
//						"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
//						"");
//				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
//					summe.add_to_me(new Rec21_VposTpaFuhre(fuhre_ort_rec).get_myBigDecimal_plan_menge(isEk, 0));
//				}
//			}
//		}else{
//			RecList21 fuhre_rec_list = this.get_down_reclist21(VPOS_TPA_FUHRE.id_vpos_std_vk, 
//					"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
//					, "");
//
//
//			for(Rec21 rec_fuhre:fuhre_rec_list){
//				
//				summe.add_to_me(new Rec21_VposTpaFuhre(rec_fuhre).get_myBigDecimal_plan_menge(isEk, 0));
//				
//				RecList21 fuhre_ort_rec_list = this.get_down_reclist21( VPOS_TPA_FUHRE_ORT.id_vpos_std, 
//						"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'VK'", 
//						"");
//				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
//					summe.add_to_me(new Rec21_VposTpaFuhre(fuhre_ort_rec).get_myBigDecimal_plan_menge(isEk, 0));
//				}		
//			}	
//		}
//
//		return summe;
//	}
	
	public String get_gueltig_von() throws myException{
		Rec21 rec_stdt_trakt = this.get_down_reclist21(VPOS_STD_ANGEBOT.id_vpos_std, "", "").get(0);
		
		return rec_stdt_trakt.get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_von).get_cDateStandardFormat();
	}
	
	public String get_gueltig_bis() throws myException{
		Rec21 rec_stdt_trakt = this.get_down_reclist21(VPOS_STD_ANGEBOT.id_vpos_std, "", "").get(0);
		
		return rec_stdt_trakt.get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_bis).get_cDateStandardFormat();
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
		return this.get_up_Rec21(VPOS_STD.id_waehrung_fremd,WAEHRUNG.id_waehrung, false).get_ufs_dbVal(WAEHRUNG.kurzbezeichnung,"");
	}
	
	
	
	
	/**
	 * @throws myException 
	 * returns "10.04.2017 - 31.12.2017"
	 */
	public String getGueltigkeit() throws myException {
		Rec20 vposStd = this.get_rec_vpos_std_angebot();
		String c_ret = vposStd.get_fs_dbVal(VPOS_STD_ANGEBOT.gueltig_von,"-")+" - "+vposStd.get_fs_dbVal(VPOS_STD_ANGEBOT.gueltig_bis,"-");
		return c_ret;
	}
	
	/**
	 * @throws myException 
	 * returns "0104-02"
	 */
	public String getAnr12() throws myException {
		String c_ret = this.get_fs_dbVal(VPOS_STD.anr1,"-")+" - "+this.get_fs_dbVal(VPOS_STD.anr2);
		return c_ret;
	}
	
	
	/**
	 * @throws myException 
	 * returns "2.003,23 EUR/t"
	 */
	public String getPreisMitEinheiten_FW() throws myException {
		String cWaehrung = 	this.get_up_Rec21(VPOS_STD.id_waehrung_fremd, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.kurzbezeichnung,  "?");
		if (cWaehrung.toUpperCase().equals("EUR")) {
			cWaehrung=ENUM_UNICODE.EUR.getCode();
		}

		String c_ret = this.get_myBigDecimal_dbVal(VPOS_STD.einzelpreis_fw, new MyBigDecimal(0)).get_FormatedRoundedNumber(2);
		c_ret=c_ret+" "+cWaehrung;
		c_ret=c_ret+"/"+this.get_fs_dbVal(VPOS_STD.einheit_preis_kurz,"");

		return c_ret;
	}
	
	
	/**
	 * @throws myException 
	 * returns "2.003,23 EUR/t"
	 */
	public String getGesPreisMitEinheiten_FW() throws myException {
		String cWaehrung = 	this.get_up_Rec20(VPOS_STD.id_waehrung_fremd, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.kurzbezeichnung,  "?");

		if (cWaehrung.toUpperCase().equals("EUR")) {
			cWaehrung=ENUM_UNICODE.EUR.getCode();
		}
		
		String c_ret = this.get_myBigDecimal_dbVal(VPOS_STD.gesamtpreis_fw, new MyBigDecimal(0)).get_FormatedRoundedNumber(2);
		c_ret=c_ret+" "+cWaehrung;
		c_ret=c_ret+"/"+this.get_fs_dbVal(VPOS_STD.einheit_preis_kurz,"");

		return c_ret;
	}

	
	/**
	 * @throws myException 
	 * returns "2.455,32 kg"
	 */
	public String getMengeMitEinh(int round) throws myException {
		String c_ret = this.get_myBigDecimal_dbVal(VPOS_STD.anzahl, new MyBigDecimal(0)).get_FormatedRoundedNumber(round);
		c_ret=c_ret+" "+this.get_fs_dbVal(VPOS_STD.einheitkurz, "");

		return c_ret;
	}
	

	
	/**
	 * 
	 * @param date
	 * @return s true, when date is in daterange of contract
	 * @throws myException
	 */
	public boolean isDateInKontaktRange(MyDate  testDate) throws myException {
		MyDate gueltig_von = this.get_rec_vpos_std_angebot().get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_von);
		MyDate gueltig_bis = this.get_rec_vpos_std_angebot().get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_bis);
		
		if (gueltig_von.isOK() && gueltig_bis.isOK() &&  testDate.isOK() ) {
			return (gueltig_von.isLessEqualThan(testDate) && gueltig_bis.isGreaterEqualThan(testDate));
		} else {
			throw new myException(this, "Only myDate-Objects which are ok allowed");
		}
	}
	
	
	public ENUM_VORGANGSART getVorgangTyp() {
		try {
			return ENUM_VORGANGSART.findENUM_VORGANGSART(this.get_up_Rec21(VKOPF_STD.id_vkopf_std).getUfs(VKOPF_STD.vorgang_typ));
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public Rec21 getVposStdAngebot() throws myException {
		return this.get_down_reclist21(VPOS_STD_ANGEBOT.id_vpos_std).get(0);
	}
	
	
	public String getGueltigkeitsZeitraum() throws myException {
		String ret = "";
		
		if (S.isFull(this.getVposStdAngebot().getFs(VPOS_STD_ANGEBOT.gueltig_von))) {
			ret = this.getVposStdAngebot().getFs(VPOS_STD_ANGEBOT.gueltig_von);
			
			if (S.isFull(this.getVposStdAngebot().getFs(VPOS_STD_ANGEBOT.gueltig_bis))) {
				ret = ret +"-"+this.getVposStdAngebot().getFs(VPOS_STD_ANGEBOT.gueltig_bis);
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
			Rec21 recKopf = this.get_up_Rec21(VKOPF_STD.id_vkopf_std);
			if (recKopf != null) {
				ret = recKopf.getUfs(VKOPF_STD.buchungsnummer, "<"+recKopf.getFs(VKOPF_STD.id_vkopf_std)+">")+"-"+this.getFs(VPOS_STD.positionsnummer,"-");
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
				Rec21 r = this.get_up_Rec21(VKOPF_STD.id_vkopf_std).get_up_Rec21(ADRESSE.id_adresse);
				if (r!=null && r.is_ExistingRecord()) {
					rec = new Rec21_adresse(r)._getMainAdresse();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rec;
	}
	
	public Rec21_VKopfStd getVkopfStd() throws myException {
		Rec21_VKopfStd recKopf = null;
		
		if (this.is_ExistingRecord()) {
			recKopf = (Rec21_VKopfStd) new Rec21_VKopfStd()._fill_id(this.getLongDbValue(VPOS_STD.id_vkopf_std));
		}
		
		
		return recKopf;
	}
	
	/**
	 * 2020-03-06: martin
	 */
	public Rec21_VPosStd _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
}