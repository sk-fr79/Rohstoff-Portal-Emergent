package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON_DRUCK;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_VKOPF_KON extends Rec20 {

	public Rec20_VKOPF_KON(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	public Rec20_VKOPF_KON(_TAB oTab) throws myException{
		super(oTab);
	}

	public Rec20_VKOPF_KON _fill_id(String oId) throws myException{
		super._fill_id(oId);
//		MyLong lid = new MyLong(oId);
//		if (lid.get_bOK()) {
//			this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.get_tab().fullTableName()+" WHERE "+this.get_tab().keyFieldName()+"="+lid.get_cUF_LongString());
//		} else {
//			throw new myException(this,"Error ID "+oId+" is no number !");
//		}
		return this;
	}
	public String getIdVkopf() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.id_vkopf_kon,"");
	}

	public String get_fix_datum_von() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.fix_von, "--.--.----");
	}


	public String get_fix_datum_bis() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.fix_bis, "--.--.----");
	}


	public boolean is_fixierungsKontrakte() throws myException{
		String fixValue = this.get_fs_dbVal(VKOPF_KON.ist_fixierung,"N");
		return fixValue.equals("Y") ? true : false;
	}

	public MyBigDecimal get_fixierung_gesamt_menge() throws myException{
		return this.get_myBigDecimal_dbVal(VKOPF_KON.fix_menge_gesamt,new MyBigDecimal("0"));
	}

	public MyBigDecimal get_aktuelle_fixiert_menge() throws myException{
		RecList20 position_rec_list = this.get_down_reclist20(
				VPOS_KON.id_vkopf_kon, 
				"NVL("+VPOS_KON.deleted+", 'N')='N'", 
				VPOS_KON.id_vpos_kon.fieldName());


		MyBigDecimal fixiert_sum = new MyBigDecimal("0");

		for(Rec20 position_rec : position_rec_list){
			MyBigDecimal positionMge = position_rec.get_myBigDecimal_dbVal(VPOS_KON.anzahl, new MyBigDecimal("0"));//_dbVal(), , new BigDecimal("0"));//.get_myBigDecimal_dbVal();
			if(positionMge.isOK()){
				fixiert_sum.add_to_me(positionMge);
			}
		}

		return fixiert_sum;
	}

	public String get_anr1() throws myException{
		return this.get_up_Rec20(VKOPF_KON.fix_id_artikel, ARTIKEL.id_artikel, false).get_fs_dbVal(ARTIKEL.anr1, "-");
	}

	public String get_anr2() throws myException{
		return this.get_up_Rec20(VKOPF_KON.fix_id_artbez, ARTIKEL_BEZ.id_artikel_bez, false).get_fs_dbVal(ARTIKEL_BEZ.anr2, "-");
	}

	public String get_artbez1() throws myException{
		return this.get_up_Rec20(VKOPF_KON.fix_id_artbez, ARTIKEL_BEZ.id_artikel_bez, false).get_fs_dbVal(ARTIKEL_BEZ.artbez1, "-");
	}

	public String get_artbez2() throws myException{
		return this.get_up_Rec20(VKOPF_KON.fix_id_artbez, ARTIKEL_BEZ.id_artikel_bez, false).get_fs_dbVal(ARTIKEL_BEZ.artbez2, "-");
	}

	public String get_preis_einheit() throws myException{
		String id_einheit_preis = this.get_up_Rec20(VKOPF_KON.fix_id_artikel, ARTIKEL.id_artikel, false).get_ufs_dbVal(ARTIKEL.id_einheit_preis, "-");
		return new Rec20(_TAB.einheit)._fill_id(id_einheit_preis).get_fs_dbVal(EINHEIT.einheitkurz,"");
	}

	public String get_einheit() throws myException{
		String id_einheit ="";
		id_einheit = this.get_up_Rec20(VKOPF_KON.fix_id_artikel, ARTIKEL.id_artikel, false).get_ufs_dbVal(ARTIKEL.id_einheit, "-");
		return new Rec20(_TAB.einheit)._fill_id(id_einheit).get_fs_dbVal(EINHEIT.einheitkurz,"");
	}

	public String get_adresse_for_info() throws myException{
		Rec20 recaddr  = this.get_up_Rec20(VKOPF_KON.id_adresse,ADRESSE.id_adresse, false);
		return 
				recaddr.get_ufs_dbVal(ADRESSE.name1,"") + " " + 
				recaddr.get_ufs_dbVal(ADRESSE.plz, "") + " " + 
				recaddr.get_ufs_dbVal(ADRESSE.ort, "");
	}

	
	public String get_adresse_name() throws myException{
		return this.get_up_Rec20(VKOPF_KON.id_adresse,ADRESSE.id_adresse, false).get_ufs_kette(" ", ADRESSE.name1,ADRESSE.name2);
	}

	
	public String get_id_adresse() throws myException{
		return this.get_ufs_dbVal(VKOPF_KON.id_adresse);
	}

	public int getMaxPositionNummer() throws NumberFormatException, myException {
		String maxPos = bibDB.EinzelAbfrage(new SEL("NVL(max("+VPOS_KON.positionsnummer+"),0)")
				.FROM(_TAB.vpos_kon)
				.WHERE(new vgl(VPOS_KON.id_vkopf_kon, this.getIdVkopf()))
				.s());
		if(S.isFull(maxPos)){
			return Integer.parseInt(maxPos)+1;
		}
		return 1;
	}

	public RecList20 get_position_reclist() throws myException{
		return this.get_down_reclist20(VPOS_KON.id_vkopf_kon, "", VPOS_KON.positionsnummer.fieldName());
	}

	public RecList20 get_position_reclist(boolean onlyUndeleted) throws myException{
		return this.get_down_reclist20(VPOS_KON.id_vkopf_kon, onlyUndeleted?new vgl_YN(VPOS_KON.deleted, false).s():"", VPOS_KON.positionsnummer.fieldName());
	}

	
	public MyBigDecimal get_gesamt_fuhre_menge(boolean isEk) throws myException{
		RecList20 position_rec_list = this.get_down_reclist20(
				VPOS_KON.id_vkopf_kon, 
				"NVL("+VPOS_KON.deleted+", 'N')='N'", 
				VPOS_KON.id_vpos_kon.fieldName());

		MyBigDecimal summe = new MyBigDecimal(0l);

		for(Rec20 position_rec: position_rec_list){
			if(isEk){
				RecList20 fuhre_rec_list = position_rec.get_down_reclist20(VPOS_TPA_FUHRE.id_vpos_kon_ek, 
						"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
						, "");


				for(Rec20 rec_fuhre:fuhre_rec_list){
					summe.add_to_me(rec_fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, new MyBigDecimal(0l)));

					RecList20 fuhre_ort_rec_list = position_rec.get_down_reclist20(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
							"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
							"");
					for(Rec20 fuhre_ort_rec: fuhre_ort_rec_list){
						summe.add_to_me(fuhre_ort_rec.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_lademenge, new MyBigDecimal(0l)));
					}

				}
			}else{
				RecList20 fuhre_rec_list = position_rec.get_down_reclist20(VPOS_TPA_FUHRE.id_vpos_kon_vk, 
						"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'"
						, "");


				for(Rec20 rec_fuhre:fuhre_rec_list){
					summe.add_to_me(rec_fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, new MyBigDecimal(0l)));

					RecList20 fuhre_ort_rec_list = position_rec.get_down_reclist20(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
							"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'VK'", 
							"");
					for(Rec20 fuhre_ort_rec: fuhre_ort_rec_list){
						summe.add_to_me(fuhre_ort_rec.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge, new MyBigDecimal(0l)));
					}		
				}	
			}
		}
		return summe;
	}

	public MyBigDecimal getMengeDivisor() throws myException{
		return this.get_up_Rec20(VKOPF_KON.fix_id_artikel, ARTIKEL.id_artikel, false).get_myBigDecimal_dbVal(ARTIKEL.mengendivisor, new MyBigDecimal(0L));
	}

	public boolean hasPosition() throws myException{
		int nb_of_position = this.get_position_reclist().size();
		if(nb_of_position>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean hasPositionUndeleted() throws myException{
		int nb_of_position = this.get_position_reclist(true).size();
		if(nb_of_position>0){
			return true;
		}else{
			return false;
		}
	}

	
	public boolean kopie_bemerkung_auf_position() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.kopie_bemerkung_auf_pos,"N").equals("Y") ? true : false;
	}

	public String get_fixierungs_bemerkung() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.bemerkung_fix1,"");
	}

	public int getDruckZaehler() throws myException{
		return this.get_down_reclist20(VKOPF_KON_DRUCK.id_vkopf_kon, "", VKOPF_KON_DRUCK.id_vkopf_kon_druck.fieldName()).size();
	}

	public boolean isAbgeschlossen() throws myException{
		return 	this.get_fs_dbVal(VKOPF_KON.abgeschlossen,"N").equals("Y")?true:false;
	}

	public boolean is_25_tonne_position() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.typ_25_to,"N").equals("Y")?true:false;
	}

	public boolean is_ladung_position() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.typ_ladung,"N").equals("Y")?true:false;
	}

	public String getBuchungsnummer()throws myException{
		return this.get_fs_dbVal(VKOPF_KON.buchungsnummer,"-");
	}

	public String get_boerse_diff_abs() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.boerse_diff_abs, "");
	}

	public String get_boerse_diff_proz() throws myException{
		return this.get_fs_dbVal(VKOPF_KON.boerse_diff_proz, "");
	}
	
	public String get_fremd_waehrung() throws myException{
		return this.get_up_Rec20(VKOPF_KON.id_waehrung_fremd, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.kurzbezeichnung);
	}
	
	
	public String getBuchungsnummerOrId()throws myException{
		return this.get_fs_dbVal(VKOPF_KON.buchungsnummer,"ID:"+this.get_fs_dbVal(VKOPF_KON.id_vkopf_kon));
	}


	public String get_sorte() {
		String c_ret= "";
		
		try {
			Rec20 rec_artikel_bez = this.get_up_Rec20(VKOPF_KON.fix_id_artbez,ARTIKEL_BEZ.id_artikel_bez,true);
			Rec20 rec_artikel    = rec_artikel_bez.get_up_Rec20(ARTIKEL.id_artikel);
			
			c_ret = rec_artikel.get_ufs_dbVal(ARTIKEL.anr1)+"-"+rec_artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.anr2)+" "+rec_artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.artbez1);
		} catch (myException e) {
			e.printStackTrace();
		}
		return c_ret;
	}

}
