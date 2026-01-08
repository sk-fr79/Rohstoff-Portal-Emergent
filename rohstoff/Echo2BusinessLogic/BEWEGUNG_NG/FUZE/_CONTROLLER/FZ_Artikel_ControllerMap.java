package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;

public abstract class FZ_Artikel_ControllerMap extends RB_MaskControllerMap {

	private  boolean isEk;

	public FZ_Artikel_ControllerMap(RB_ComponentMapCollector p_componentMapCollector, FZ__CONST.SEARCH_EK_OR_VK ek_or_vk) throws myException {
		super(p_componentMapCollector);
		isEk = (ek_or_vk == SEARCH_EK_OR_VK.EK)?true:false;

	}

	public abstract KEY_ATOM get_quelle_atom();

	public abstract KEY_ATOM get_ziel_atom();

	public abstract KEY_VEKT get_vektor();

	public abstract RB_KF get_angebot_kontrakt_field() throws myException;

	public abstract RB_KF get_einheit_field() throws myException;
	
	public void fill_artikel(String id_artBez, MyE2_MessageVector mv) throws myException{

		KEY_ATOM atom_quelle 	= this.get_quelle_atom();
		
		KEY_ATOM atom_ziel 		= this.get_ziel_atom();

		KEY_ATOM  at 			= 	isEk ? get_quelle_atom() : get_ziel_atom();

//		KEY_VEKT vekt 			=  this.get_vektor();
		
		RB_KF ang_kon_RB_KF 	= get_angebot_kontrakt_field();
		
		if(ang_kon_RB_KF != null){

			if(S.isFull(this.get_liveVal(at, ang_kon_RB_KF))){
				this.set_maskVal(atom_quelle, ang_kon_RB_KF, "", mv);

				this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.id_vpos_kon, "", mv);

				this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.id_vpos_std, "", mv);

				this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.e_preis, "", mv);

				this.set_maskVal(atom_ziel, ang_kon_RB_KF, "", mv);

				this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.id_vpos_kon, "", mv);

				this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.id_vpos_std, "", mv);

				this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.e_preis, "", mv);
			}
		}
		
		MyLong l_id_artBez = new MyLong(id_artBez);

		if(l_id_artBez!=null && l_id_artBez.get_bOK()){
			Rec20 rec20_Artikel_bez = new Rec20(_TAB.artikel_bez)._fill_id(l_id_artBez.get_lValue());
			Rec20 rec20_Artikel = new Rec20(_TAB.artikel)._fill_id(		rec20_Artikel_bez.get_myLong_dbVal(ARTIKEL_BEZ.id_artikel).get_lValue());
			
			this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.id_artikel_bez,	rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),	mv);
			this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.id_artikel,		rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel), 		mv);
			this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.artbez1, 		rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)	, 		mv);
			this.set_maskVal(atom_quelle, BEWEGUNG_ATOM.artbez2, 		rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)	, 		mv);
			this.set_maskVal(atom_quelle, get_einheit_field(), 			rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),	mv);

			this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.id_artikel_bez,	rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),	mv);
			this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.id_artikel,		rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel), 		mv);
			this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.artbez1, 			rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)	, 		mv);
			this.set_maskVal(atom_ziel, BEWEGUNG_ATOM.artbez2, 			rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)	, 		mv);
			this.set_maskVal(atom_ziel, get_einheit_field(), 			rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),	mv);	

			this.set_maskVal(get_vektor(),BEWEGUNG_VEKTOR.id_eak_code,	rec20_Artikel.get_ufs_dbVal(ARTIKEL.id_eak_code),				mv);
			
			this.set_maskVal(atom_quelle, FZ__CONST.f_keys.ANR12.k(), 	rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv);
			this.set_maskVal(atom_ziel, FZ__CONST.f_keys.ANR12.k(), rec20_Artikel_bez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv);

		}else{
			this.set_maskVal(atom_quelle, 		BEWEGUNG_ATOM.id_artikel,	 "", 		mv);
			this.set_maskVal(atom_quelle, 		BEWEGUNG_ATOM.artbez1, 		 "", 		mv);
			this.set_maskVal(atom_quelle, 		BEWEGUNG_ATOM.artbez2, 		 "", 		mv);

			this.set_maskVal(atom_ziel, 		BEWEGUNG_ATOM.id_artikel_bez,"",		mv);
			this.set_maskVal(atom_ziel, 		BEWEGUNG_ATOM.id_artikel,	 "", 		mv);
			this.set_maskVal(atom_ziel, 		BEWEGUNG_ATOM.artbez1, 		 "", 		mv);
			this.set_maskVal(atom_ziel, 		BEWEGUNG_ATOM.artbez2, 		 "", 		mv);

			this.set_maskVal(atom_quelle, 		get_einheit_field(),"...",				mv);
			
			this.set_maskVal(get_vektor(),		BEWEGUNG_VEKTOR.id_eak_code,"",			mv);
			
			this.set_maskVal(atom_quelle, 		FZ__CONST.f_keys.ANR12.k(), "", 		mv);
			this.set_maskVal(atom_ziel, 		FZ__CONST.f_keys.ANR12.k(), "", 		mv);
			
			this.get_comp(atom_quelle, 			BEWEGUNG_ATOM.e_preis, 					mv).set_bEnabled_For_Edit(true);
			this.get_comp(atom_ziel,   			BEWEGUNG_ATOM.e_preis, 					mv).set_bEnabled_For_Edit(true);
		}
	}


	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		return null;
	}

}
