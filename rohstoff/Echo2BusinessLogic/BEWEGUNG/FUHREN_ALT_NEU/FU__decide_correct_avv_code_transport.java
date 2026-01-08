package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class FU__decide_correct_avv_code_transport {

	public enum AVVCODE_STATUSBERICHT {
		AVV_EX_MANDANT("AVV-Code ex-Mandant aus Artikelstamm")
		,AVV_MANDANT_STD_ANLIEFERUNG("AVV-Code Standardanlieferung aus Artikelstamm")
		,AVV_KUNDENSPEZ_EK_SORTE("AVV aus kundenspezifischen Artikelbezeichnung (EK)")
		,AVV_KUNDENSPEZ_VK_SORTE("AVV aus kundenspezifischen Artikelbezeichnung (VK)")
		,AVV_LEER_WG_PROD_DIENST_EOW("AVV leer, weil Sorte ist Produkt, Dienstleistung oder EOW")
		,AVV_NICHT_ERMITTELBAR("AVV kann nicht ermittelt werden")
		;
		
		private String f_text4user = null;
		private AVVCODE_STATUSBERICHT(String cText4User) {
			this.f_text4user = cText4User;			
		}
		public String getF_text4user() {
			return f_text4user;
		}
	}

	
	
	public abstract Long  read_actual_id_adress_start_geo() throws myException;
	public abstract Long  read_actual_id_adress_ziel_geo() throws myException;

	public abstract Long  read_actual_id_artikelbez_start() throws myException;
	public abstract Long  read_actual_id_artikelbez_ziel() throws myException;
	
	public abstract void  write_found_avvcode_to_mask(Long id_avv_code) throws myException;
	public abstract void  clear_avvcode_in_mask() throws myException;
	
	private AVVCODE_STATUSBERICHT status_der_ermittlung = AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR;
	
	
	
	/**
	 * 
	 */
	public FU__decide_correct_avv_code_transport() {
		super();
	}

	
	
	public FU__decide_correct_avv_code_transport set_found_code() throws myException {
		this.write_found_avvcode_to_mask(this.find_avv_code());
		return this;
	}
	
	
	public Long find_avv_code() throws myException{
		
		//zuerst die moeglichen daten besorgen

		Long  id_adress_start = 	this.read_actual_id_adress_start_geo();
		Long  id_adress_ziel = 		this.read_actual_id_adress_ziel_geo();

		Long  id_artikelbez_start = this.read_actual_id_artikelbez_start();
		Long  id_artikelbez_ziel =  this.read_actual_id_artikelbez_ziel();
		
		
		MyLong[] avv_0_bis_2 = new MyLong[3];
		for (int i=0;i<3; i++) {avv_0_bis_2[i]=null;}
		
		AVVCODE_STATUSBERICHT[] avv_status_0_2 = {AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR,AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR,AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR,};
		
		RECORD_ADRESSE_extend rec_adresse_start = 		null;
		RECORD_ADRESSE_extend rec_adresse_start_basis = null;
		
		RECORD_ADRESSE_extend rec_adresse_ziel = 		null;
		RECORD_ADRESSE_extend rec_adresse_ziel_basis = 	null;
	
		Rec20 rec_artbez_start = 	null;
		Rec20 rec_artikel_start =	null;
		Rec20 rec_artbez_ziel = 	null;
		Rec20 rec_artikel_ziel =	null;
	
		RecList20  rl_artbez_ek = null;
		RecList20  rl_artbez_vk = null;
		
		
		try {
			if (MyLong.isNotNull(id_adress_start)) {
				rec_adresse_start = new RECORD_ADRESSE_extend(id_adress_start);
				rec_adresse_start_basis = rec_adresse_start.base_Adress();
			}
			if (MyLong.isNotNull(id_adress_ziel)) {
				rec_adresse_ziel = new RECORD_ADRESSE_extend(id_adress_ziel);
				rec_adresse_ziel_basis = rec_adresse_ziel.base_Adress();
			}
			if (MyLong.isNotNull(id_artikelbez_start)) {
				rec_artbez_start = 	new Rec20(_TAB.artikel_bez)._fill_id(id_artikelbez_start);
				rec_artikel_start =	rec_artbez_start.get_up_Rec20(ARTIKEL.id_artikel);
			}
			if (MyLong.isNotNull(id_artikelbez_ziel)) {
				rec_artbez_ziel = 	new Rec20(_TAB.artikel_bez)._fill_id(id_artikelbez_ziel);
				rec_artikel_ziel =	rec_artbez_ziel.get_up_Rec20(ARTIKEL.id_artikel);
			}
			
			if (rec_adresse_start_basis!=null && id_artikelbez_start != null) {
				rl_artbez_ek = new Rec20(_TAB.adresse)._fill(rec_adresse_start_basis).get_down_reclist20(ARTIKELBEZ_LIEF.id_adresse,
												new And(ARTIKELBEZ_LIEF.id_artikel_bez,id_artikelbez_start.toString())
												.and(ARTIKELBEZ_LIEF.artbez_typ,"'EK'")
												.s()
												, null);
			}
			
			if (rec_adresse_ziel_basis!=null && id_artikelbez_ziel != null) {
				// ------------------------ 2 (vk-artikel)
				rl_artbez_vk = new Rec20(_TAB.adresse)._fill(rec_adresse_ziel_basis).get_down_reclist20(ARTIKELBEZ_LIEF.id_adresse,
											new And(ARTIKELBEZ_LIEF.id_artikel_bez,id_artikelbez_ziel.toString())
											.and(ARTIKELBEZ_LIEF.artbez_typ,"'VK'")
											.s()
											, null);
			}

			
			/*
			 * suchen von 5 varianten avv-code:
			 * 0. Avv-Code aufgrund Eintragung Ladestation und Sorte (wenn Ladestation fremd, dann Artikelstamm  ID_EAK_CODE, wenn Ladestation mandant ID_EAK_CODE_EX_MANDANT), bei strecke bleibt dieser code leer
			 * 1. Avv-Code aufgrund EK-Sorteneintrag
			 * 2. AVV-Code aufgrund VK-Sorteneintrag
			 * 
			 * dann von 2-0 nach unten suchen, der erste vorhandene avv-code wird genommen
			 */
			
			// ------------------------ 0 (angaben bei fuhren ohne kundenspezifische artikel),  startseite ist bekannt
			if (rec_adresse_start!=null && rec_artbez_start!=null && rec_adresse_start_basis.is_adress_of_mandant()) {	

				if ( ! (rec_artikel_start.is_yes_db_val(ARTIKEL.dienstleistung) ||  rec_artikel_start.is_yes_db_val(ARTIKEL.ist_produkt) || rec_artikel_start.is_yes_db_val(ARTIKEL.end_of_waste))) {
					//dieser avv nur, wenn es schrottsorten sind und nur in NICHT-Strecken-Relationen
					avv_0_bis_2[0] = rec_artbez_start.get_up_Rec20(ARTIKEL.id_artikel).get_myLong_dbVal(ARTIKEL.id_eak_code_ex_mandant);
					if (avv_0_bis_2[0]!=null && avv_0_bis_2[0].get_bOK()) {
						avv_status_0_2[0]=AVVCODE_STATUSBERICHT.AVV_EX_MANDANT;
					} else {
						avv_status_0_2[0]=AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR;
					}
				} else {
					avv_status_0_2[0]=AVVCODE_STATUSBERICHT.AVV_LEER_WG_PROD_DIENST_EOW;
				}
			} else if (rec_adresse_ziel!=null && rec_artbez_ziel!=null && rec_adresse_ziel_basis.is_adress_of_mandant()) {
			
				// ------------------------ 0 (angaben bei fuhren ohne kundenspezifische artikel)
				if ( ! (rec_artikel_ziel.is_yes_db_val(ARTIKEL.dienstleistung) ||  rec_artikel_ziel.is_yes_db_val(ARTIKEL.ist_produkt) || rec_artikel_ziel.is_yes_db_val(ARTIKEL.end_of_waste))) {
					avv_0_bis_2[0] = rec_artbez_ziel.get_up_Rec20(ARTIKEL.id_artikel).get_myLong_dbVal(ARTIKEL.id_eak_code);
					avv_status_0_2[0]=AVVCODE_STATUSBERICHT.AVV_MANDANT_STD_ANLIEFERUNG;
				} else {
					avv_status_0_2[0]=AVVCODE_STATUSBERICHT.AVV_LEER_WG_PROD_DIENST_EOW;
				}
			} 
			
			if (rl_artbez_ek != null) {
				//falls mehrere gefunden werden, den ersten avv-code auslesen
				for (Rec20 r: rl_artbez_ek) {
					if (S.isFull(r.get_ufs_dbVal(ARTIKELBEZ_LIEF.id_eak_code))) {
						avv_0_bis_2[1]=		r.get_myLong_dbVal(ARTIKELBEZ_LIEF.id_eak_code);
						avv_status_0_2[1] = AVVCODE_STATUSBERICHT.AVV_KUNDENSPEZ_EK_SORTE;
						break;
					}
				}
				if (avv_status_0_2[1]==null) {
					avv_status_0_2[1] = AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR;
				}
			}
			
			
			if (rl_artbez_vk != null) {
				//falls mehrere gefunden werden, den ersten avv-code auslesen
				for (Rec20 r: rl_artbez_vk) {
					if (S.isFull(r.get_ufs_dbVal(ARTIKELBEZ_LIEF.id_eak_code))) {
						avv_0_bis_2[2]=		r.get_myLong_dbVal(ARTIKELBEZ_LIEF.id_eak_code);
						avv_status_0_2[2] = AVVCODE_STATUSBERICHT.AVV_KUNDENSPEZ_VK_SORTE;
						break;
					}
				}
				if (avv_status_0_2[2]==null) {
					avv_status_0_2[2] = AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR;
				}
			}

			if (avv_0_bis_2[2]!=null && avv_0_bis_2[2].get_bOK()) {
				this.status_der_ermittlung=avv_status_0_2[2];
				return avv_0_bis_2[2].get_oLong();
			} else if (avv_0_bis_2[1]!=null && avv_0_bis_2[1].get_bOK()) {
				this.status_der_ermittlung=avv_status_0_2[1];
				return avv_0_bis_2[1].get_oLong();
			} else if (avv_0_bis_2[0]!=null && avv_0_bis_2[0].get_bOK()) {
				this.status_der_ermittlung=avv_status_0_2[0];
				return avv_0_bis_2[0].get_oLong();
			} else {
				this.status_der_ermittlung=AVVCODE_STATUSBERICHT.AVV_NICHT_ERMITTELBAR;
				this.clear_avvcode_in_mask();
				return null;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new myException(this, "Undefined SYSTEN-Error 2344512388975!");
		}

	}

	
	public AVVCODE_STATUSBERICHT get_status_der_ermittlung() {
		return status_der_ermittlung;
	}
	
	public MyE2_String getInfoText() {
		return new MyE2_String(status_der_ermittlung.getF_text4user());
	}
	
	
}
