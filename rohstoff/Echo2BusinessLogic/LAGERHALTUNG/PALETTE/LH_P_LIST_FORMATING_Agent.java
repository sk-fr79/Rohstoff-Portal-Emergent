
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_user;

public class LH_P_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;

	public LH_P_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
		this.m_tpHashMap = p_tpHashMap;
	}


	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
//		fill_artikel(oMAP, oUsedResultMAP);
		fill_boxnummer(oMAP, oUsedResultMAP);
		fill_username(oMAP, oUsedResultMAP);
		fill_einheit(oMAP, oUsedResultMAP);
		fill_chargennr(oMAP, oUsedResultMAP);
		
		fill_buchungsnr(oMAP, oUsedResultMAP);
		//		activate_ausbuchung_bt(oMAP, oUsedResultMAP);
	}


	/**
	 * @author sebastien
	 * @date 17.10.2019
	 *
	 * @param oMAP
	 * @param oUsedResultMAP
	 * @throws myException 
	 */
	private void fill_buchungsnr(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		String lBuchungsnr = oUsedResultMAP.get_UnFormatedValue(LAGER_PALETTE.buchungsnr_hand.fn(), "");
		String buchungsnr = "";

		Object o = oMAP.get__Comp(LAGER_PALETTE.buchungsnr_hand.fn());
				
		if(S.isEmpty(lBuchungsnr)) {
			buchungsnr = oUsedResultMAP.get_UnFormatedValue(VPOS_TPA_FUHRE.buchungsnr_fuhre.fn(), "-");
		}else {
			buchungsnr = "" + lBuchungsnr;
		}
		
		if(o !=null) {
			((MyE2_DB_Label_INGRID)o).set_Text(buchungsnr);
		}
	}



	/**
	 * @author sebastien
	 * @date 10.05.2019
	 *
	 * @param oMAP
	 * @param oUsedResultMAP
	 * @throws myException 
	 */
	private void fill_chargennr(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		long chargennr = oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.chargennummer.fn(), true);
		Object o = oMAP.get__Comp(LAGER_PALETTE.chargennummer.fn());
		if(chargennr > 0) {
			((MyE2_DB_Label_INGRID)o).set_Text(bibALL.convertID2UnformattedID("" + chargennr));
		}
	}


	/**
	 * @author sebastien
	 * @date 10.12.2018
	 *
	 * @param oMAP
	 * @param oUsedResultMAP
	 * @throws myException 
	 */
	private void fill_boxnummer(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		long id_box = oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.id_lager_box.fn(), true);
		
		MyE2_DB_Label_INGRID oCmp = ((MyE2_DB_Label_INGRID)oMAP.get__Comp(LAGER_PALETTE.id_lager_box.fn()));

		if(id_box > 0) {
			Rec21 record_lager_box = new Rec21(_TAB.lager_box)._fill_id(id_box);
			String oText = record_lager_box.getUfs(LAGER_BOX.boxnummer, "-");
			if(record_lager_box.is_yes_db_val(LAGER_BOX.is_default_box)) {
				oText += " *";
			}
			oCmp.set_Text(oText);
		}
	}


//	private void fill_artikel(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException{
//		long id_artikel = oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.id_artikel_bez.fn(), true);
//
//		Object o = oMAP.get__Comp(LAGER_PALETTE.id_artikel_bez.fn());
//
//		if(id_artikel > 0) {
//			Rec21 record_artbez = new Rec21(_TAB.artikel_bez)._fill_id(id_artikel);
//
//			String anr1 = record_artbez.get_up_Rec21(ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel, false).getUfs(ARTIKEL.anr1);
//
//			((MyE2_DB_Label_INGRID)o).set_Text(""+anr1+"-"+record_artbez.getUfs(ARTIKEL_BEZ.anr2)+"  "+record_artbez.getUfs(ARTIKEL_BEZ.artbez1));
//		}
//	}

	private void fill_einheit(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException{
		long id_artikel = oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.id_artikel_bez.fn(), true);
		if(id_artikel > 0) {
			Rec21_artikel record_art = new Rec21_artikel_bez()._fill_id(id_artikel)._getRec21Artikel();

			String einheit = record_art.get_einheit_k();

			Object o1 = oMAP.get__Comp(LAGER_PALETTE.bruttomenge.fn());
			((MyE2_DB_Label_INGRID)o1).set_Text(oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.bruttomenge.fn(), true)+ " " + einheit);

			Object o2 = oMAP.get__Comp(LAGER_PALETTE.taramenge.fn());
			((MyE2_DB_Label_INGRID)o2).set_Text(oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.taramenge.fn(), true)+ " " + einheit);

			Object o3 = oMAP.get__Comp(LAGER_PALETTE.nettomenge.fn());
			((MyE2_DB_Label_INGRID)o3).set_Text(oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.nettomenge.fn(), true)+ " " + einheit);
		}
	}

	private void fill_username(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException{
		Object o1 = oMAP.get__Comp(LAGER_PALETTE.gepresst_von.fn());
		Object o2 = oMAP.get__Comp(LAGER_PALETTE.palettiert_von.fn());
		Object o3 = oMAP.get__Comp(LAGER_PALETTE.endkontrolle_von.fn());

		long id_gepresst_von 	= oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.gepresst_von.fn(), true);
		long id_palettiert_von 	= oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.palettiert_von.fn(), true);
		long id_endkontrolle_von= oUsedResultMAP.get_LActualDBValue(LAGER_PALETTE.endkontrolle_von.fn(), true);
		Rec21_user recUser = new Rec21_user();

		if(id_gepresst_von>0) {
			recUser._fill_id(id_gepresst_von);
			((MyE2_DB_Label_INGRID)o1).set_Text(recUser.get_ufs_kette(" ", USER.name1, USER.vorname));
		}
		if(id_palettiert_von>0) {
			recUser._fill_id(id_palettiert_von);
			((MyE2_DB_Label_INGRID)o2).set_Text(recUser.get_ufs_kette(" ", USER.name1, USER.vorname));
		}
		if(id_endkontrolle_von>0) {
			recUser._fill_id(id_endkontrolle_von);
			((MyE2_DB_Label_INGRID)o3).set_Text(recUser.get_ufs_kette(" ", USER.name1, USER.vorname));
		}
	}


	public RB_TransportHashMap getTransportHashMap() {
		return m_tpHashMap;
	}

}


