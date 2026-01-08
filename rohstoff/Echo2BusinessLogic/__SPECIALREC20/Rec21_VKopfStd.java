package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD_DRUCK;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class Rec21_VKopfStd extends Rec21 {

	public Rec21_VKopfStd(Rec21 baseRec) throws myException {
		super(baseRec);
		
		if (baseRec.get_tab()!=_TAB.vkopf_std) {
			throw new myException("Error: <456ftt4564-9289-11e9-bc42-5345af7764f64> only _TAB.vkopf_std allwoed !");
		}

		
	}

	public Rec21_VKopfStd() throws myException{
		super(_TAB.vkopf_std);
	}

	public Rec21_VKopfStd _fill_id(String oId) throws myException{
		super._fill_id(oId);
		return this;
	}
	public String getIdVkopf() throws myException{
		return this.get_fs_dbVal(VKOPF_STD.id_vkopf_std,"");
	}


	
	public ENUM_VORGANGSART getVorgangTyp() {
		try {
			return ENUM_VORGANGSART.findENUM_VORGANGSART(this.getUfs(VKOPF_STD.vorgang_typ));
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}


	public String get_adresse_for_info() throws myException{
		Rec21 recaddr  = this.get_up_Rec21(VKOPF_STD.id_adresse,ADRESSE.id_adresse, false);
		return 
				recaddr.get_ufs_dbVal(ADRESSE.name1,"") + " " + 
				recaddr.get_ufs_dbVal(ADRESSE.plz, "") + " " + 
				recaddr.get_ufs_dbVal(ADRESSE.ort, "");
	}

	
	public String get_adresse_name() throws myException{
		return this.get_up_Rec21(VKOPF_STD.id_adresse,ADRESSE.id_adresse, false).get_ufs_kette(" ", ADRESSE.name1,ADRESSE.name2);
	}

	
	public String get_id_adresse() throws myException{
		return this.get_ufs_dbVal(VKOPF_STD.id_adresse);
	}

	public int getMaxPositionNummer() throws NumberFormatException, myException {
		String maxPos = bibDB.EinzelAbfrage(new SEL("NVL(max("+VPOS_STD.positionsnummer+"),0)")
				.FROM(_TAB.vpos_std)
				.WHERE(new vgl(VPOS_STD.id_vkopf_std, this.getIdVkopf()))
				.s());
		if(S.isFull(maxPos)){
			return Integer.parseInt(maxPos)+1;
		}
		return 1;
	}

	public RecList20 get_position_reclist() throws myException{
		return this.get_down_reclist20(VPOS_STD.id_vkopf_std, "", VPOS_STD.positionsnummer.fieldName());
	}

	public RecList20 get_position_reclist(boolean onlyUndeleted) throws myException{
		return this.get_down_reclist20(VPOS_STD.id_vkopf_std, onlyUndeleted?new vgl_YN(VPOS_STD.deleted, false).s():"", VPOS_STD.positionsnummer.fieldName());
	}

	
	
	public MyBigDecimal get_gesamt_fuhre_menge(boolean isEk) throws myException{
		RecList21 position_rec_list = this.get_down_reclist21(VPOS_STD.id_vkopf_std,"NVL("+VPOS_STD.deleted+", 'N')='N'",VPOS_STD.id_vpos_std.fieldName());

		MyBigDecimal summe = new MyBigDecimal(0l);

		IF_Field downField = 		isEk? VPOS_TPA_FUHRE.id_vpos_std_ek: VPOS_TPA_FUHRE.id_vpos_std_vk;
		String   defQuelleZiel = 	isEk? "EK": "VK";
		
		for(Rec21 position_rec: position_rec_list){
		
			RecList21 fuhre_rec_list = position_rec.get_down_reclist21(downField, new And(new vgl_YN(VPOS_TPA_FUHRE.ist_storniert,false))
																						.and(new vgl_YN(VPOS_TPA_FUHRE.deleted,false)).s(), "");


			for(Rec21 rec_fuhre:fuhre_rec_list){
				summe.add_to_me(rec_fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, new MyBigDecimal(0l)));
	
				RecList21 fuhre_ort_rec_list = position_rec.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, 
						new And(new vgl_YN(VPOS_TPA_FUHRE.deleted,false)).and(new vgl(VPOS_TPA_FUHRE_ORT.def_quelle_ziel, defQuelleZiel)).s(), "");
				for(Rec21 fuhre_ort_rec: fuhre_ort_rec_list){
					summe.add_to_me(fuhre_ort_rec.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_lademenge, new MyBigDecimal(0l)));
				}
			}

		}
		return summe;
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


	public int getDruckZaehler() throws myException{
		return this.get_down_reclist20(VKOPF_STD_DRUCK.id_vkopf_std, "", VKOPF_STD_DRUCK.id_vkopf_std_druck.fieldName()).size();
	}

	public boolean isAbgeschlossen() throws myException{
		return 	this.get_fs_dbVal(VKOPF_STD.abgeschlossen,"N").equals("Y")?true:false;
	}


	public String getBuchungsnummer()throws myException{
		return this.get_fs_dbVal(VKOPF_STD.buchungsnummer,"-");
	}

	
	public String get_fremd_waehrung() throws myException{
		return this.get_up_Rec21(VKOPF_STD.id_waehrung_fremd, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.kurzbezeichnung);
	}
	
	
	public String getBuchungsnummerOrId()throws myException{
		return this.get_fs_dbVal(VKOPF_STD.buchungsnummer,"ID:"+this.get_fs_dbVal(VKOPF_STD.id_vkopf_std));
	}

	
	
}
