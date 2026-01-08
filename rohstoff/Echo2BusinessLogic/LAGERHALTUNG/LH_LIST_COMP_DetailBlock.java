package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.UUID;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author sebastien
 * @date 14.11.2018
 *
 */
public class LH_LIST_COMP_DetailBlock extends MyE2_DB_PlaceHolder_NT {

	private RB_TransportHashMap   m_tpHashMap = null;

	/**
	 * @author sebastien
	 * @throws myException 
	 * @date 14.11.2018
	 *
	 */

	private int nb_of_fuhre_angezeigt 	= 0;

	public LH_LIST_COMP_DetailBlock(RB_TransportHashMap tmpHashMap) throws myException {
		super();
		this.nb_of_fuhre_angezeigt = Integer.parseInt(ENUM_MANDANT_CONFIG.LAGERHALTUNG_NB_OF_ANGEZEIGT_FUHRE.getCheckedValue());
		this.m_tpHashMap = tmpHashMap;

		this._bo_no();

		this.EXT().set_oCompTitleInList(new RB_lab()._t("Detail"));

	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}


	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,SQLResultMAP oResultMAP) throws myException {
		this._clear();

		RB_gld gld_t_lm = new RB_gld()._ins(1)._left_mid()._col_back_d();
		RB_gld gld_t_cm = new RB_gld()._ins(1)._left_mid()._col_back_d();
		RB_gld gld_t_rm = new RB_gld()._ins(1)._right_mid()._col_back_d();

		RB_gld gld_lt = new RB_gld()._ins(1)._left_top();
		RB_gld gld_ct = new RB_gld()._ins(1)._center_top();
		RB_gld gld_rt = new RB_gld()._ins(1)._right_top();

		VEK<String> vKundeselectorWert =new VEK<String>();
		VEK<String> vFuhreSelectorWert =new VEK<String>();
		VEK<String> vSorteSelectorWert =new VEK<String>();

		if(this.m_tpHashMap.getListSelectionComponentVector() != null) {

			E2_SelectionComponentsVector vSelector = this.m_tpHashMap.getListSelectionComponentVector();

			for(int i=0;i<vSelector.size();i++) {
				if(vSelector.get(i) instanceof LH_LIST_Selector_kunde) {
					vKundeselectorWert._a( ((LH_LIST_Selector_kunde)vSelector.get(i)).get_SelectedValues());
				}
				if(vSelector.get(i) instanceof LH_LIST_Selector_fuhre) {
					vFuhreSelectorWert._a( ((LH_LIST_Selector_fuhre)vSelector.get(i)).get_SelectedValues());
				}
				if(vSelector.get(i) instanceof LH_LIST_Selector_sorte) {
					vSorteSelectorWert._a( ((LH_LIST_Selector_sorte)vSelector.get(i)).get_SelectedValues());
				}
			}
		}

//		SEL s = new SEL(LAGER_PALETTE.id_lager_box).ADDFIELD(LAGER_PALETTE.id_vpos_tpa_fuhre_ein).ADDFIELD(LAGER_PALETTE.id_artikel_bez)
//				.ADDFIELD("SUM(JT_LAGER_PALETTE.NETTOMENGE)")
//				.ADDFIELD("SUM(JT_LAGER_PALETTE.BRUTTOMENGE)")
//				.ADDFIELD("count(JT_LAGER_PALETTE.ID_LAGER_PALETTE)")
//				.FROM(_TAB.lager_palette_box)
//				.INNERJOIN(_TAB.lager_palette, LAGER_PALETTE_BOX.id_lager_palette, LAGER_PALETTE.id_lager_palette)
//				.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
//				.WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_box, ""+oResultMAP.getLongDBValue(LAGER_BOX.id_lager_box)))
//				;
		
		SEL s_v2 = new SEL(LAGER_PALETTE.id_lager_box).ADDFIELD(LAGER_PALETTE.id_vpos_tpa_fuhre_ein).ADDFIELD(LAGER_PALETTE.id_artikel_bez)
				.ADDFIELD("JT_LAGER_PALETTE.NETTOMENGE")
				.ADDFIELD("JT_LAGER_PALETTE.BRUTTOMENGE")
				.ADDFIELD("JT_LAGER_PALETTE.ID_LAGER_PALETTE")
				.ADDFIELD("NVL("+VPOS_TPA_FUHRE.buchungsnr_fuhre.tnfn()+",NVL("+LAGER_PALETTE.buchungsnr_hand.tnfn()+",''))","BUCHUNGSNR")
				.FROM(_TAB.lager_palette_box)
				.INNERJOIN(_TAB.lager_palette, LAGER_PALETTE_BOX.id_lager_palette, LAGER_PALETTE.id_lager_palette)
				.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
				.WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_box, ""+oResultMAP.getLongDBValue(LAGER_BOX.id_lager_box)))
				.AND(new VglNull(LAGER_PALETTE_BOX.ausbuchung_am)).AND(new vgl_YN(LAGER_PALETTE.ausbuchung_hand, false));
		
		if(vKundeselectorWert.size()>0) {
//			s.AND(VPOS_TPA_FUHRE.id_adresse_start, COMP.IN.ausdruck(), "("+ bibALL.Concatenate(vKundeselectorWert, ",", "")+")");
			s_v2.AND(VPOS_TPA_FUHRE.id_adresse_start, COMP.IN.ausdruck(), "("+ bibALL.Concatenate(vKundeselectorWert, ",", "")+")");
		}

		if(vFuhreSelectorWert.size()>0) {
//			s.AND(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, COMP.IN.ausdruck(), "("+ bibALL.Concatenate(vFuhreSelectorWert, ",", "")+")");
			s_v2.AND(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, COMP.IN.ausdruck(), "("+ bibALL.Concatenate(vFuhreSelectorWert, ",", "")+")");
		}

		if(vSorteSelectorWert.size()>0) {
//			s.AND(VPOS_TPA_FUHRE.id_artikel_bez_ek, COMP.IN.ausdruck(), "("+ bibALL.Concatenate(vSorteSelectorWert, ",", "")+")");
			s_v2.AND(LAGER_PALETTE.id_artikel_bez, COMP.IN.ausdruck(), "("+ bibALL.Concatenate(vSorteSelectorWert, ",", "")+")");
		}


//		String cplteQuery = s.s() + " AND "+ LAGER_PALETTE_BOX.ausbuchung_am.tnfn()+" IS NULL GROUP BY " +LAGER_PALETTE.id_lager_box.tnfn()+", "+LAGER_PALETTE.id_vpos_tpa_fuhre_ein.tnfn()+", "+ LAGER_PALETTE.id_artikel_bez.tnfn() 
//		+" ORDER BY "+ LAGER_PALETTE.id_vpos_tpa_fuhre_ein.tnfn();

		String cplteQuery_v2 = /*new SEL()
				.ADDFIELD("id_lager_box")
				.ADDFIELD("id_vpos_tpa_fuhre_ein")
				.ADDFIELD("id_artikel_bez")
				.ADDFIELD("buchungsnr")
				.ADDFIELD("SUM(NETTOMENGE)")
				.ADDFIELD("SUM(BRUTTOMENGE)")
				.ADDFIELD("count(ID_LAGER_PALETTE)")
				.s();*/
				"SELECT id_lager_box, id_vpos_tpa_fuhre_ein, id_artikel_bez, buchungsnr, SUM(NETTOMENGE), SUM(BRUTTOMENGE), count(ID_LAGER_PALETTE)"
				+" FROM ("+s_v2.s()+")"
				+" GROUP BY " +LAGER_PALETTE.id_lager_box.fn()+", " + " BUCHUNGSNR, " +LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fn()+", "+ LAGER_PALETTE.id_artikel_bez.fn() 
				+" ORDER BY "+ LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fn() + ", BUCHUNGSNR";
		
		String [][] result = bibDB.EinzelAbfrageInArray(cplteQuery_v2, "");

		LinkedHashMap<String, Paletten_Uebersicht> hm_anzahl_palette = new LinkedHashMap<>();

		if(! (result  == null)) {
			for(String[] res_line : result) {
				//				String boxNr 			= res_line[0];
				String id_fuhre 		= res_line[1];
				String id_artikel 		= res_line[2];
				String buchungsnr		= res_line[3];
				String menge_netto 		= res_line[4];
				String menge_brutto 	= res_line[5];
				String anzahl 			= res_line[6];

				String uuid = UUID.randomUUID().toString();

				Paletten_Uebersicht pal = new Paletten_Uebersicht()._set_artikel(id_artikel)._set_fuhre(id_fuhre)._set_buchungsnr(buchungsnr);

				pal.anzahl_paletten = new BigDecimal(anzahl).intValue();

				if(S.isFull(menge_netto)) {
					pal.netto_menge = new BigDecimal(menge_netto).longValue();
				}
				if(S.isFull(menge_brutto)) {
					pal.brutto_menge = new BigDecimal(menge_brutto).longValue();
				}

				hm_anzahl_palette.put(uuid, pal);
			}
		}

		if(hm_anzahl_palette.size()>0) {



			E2_Grid grdElement = new E2_Grid()._setSize(25,25,70,75,400,400,70,70,70)._bo_ddd();
			grdElement
			._a("", 									gld_t_cm)
			._a("", 									gld_t_cm)
			._a(new RB_lab("Fuhre ID")		._fsa(-1), 	gld_t_lm)
			._a(new RB_lab("Buch. nr.")		._fsa(-1), 	gld_t_lm)
			._a(new RB_lab("Kunde")			._fsa(-1), 	gld_t_lm)
			._a(new RB_lab("Material")		._fsa(-1), 	gld_t_lm)
			._a(new RB_lab("Menge bru.")	._fsa(-1), 	gld_t_rm)
			._a(new RB_lab("Menge net.")	._fsa(-1), 	gld_t_rm)
			._a(new RB_lab("Anz. Pal.")	._fsa(-1), 		gld_t_rm)
			;

			
			for(String key: hm_anzahl_palette.keySet()) {
				Paletten_Uebersicht fo = hm_anzahl_palette.get(key);

				String fuhre_id = fo.record_fuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre);
				String buchungsnr = fo.get_buchungsnummer();
				
				boolean show_palette_in_detail = false;

				SEL s_einzelnpalette = new SEL()
						.FROM(_TAB.lager_palette)
						.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
						.WHERE(new vgl(LAGER_PALETTE.id_lager_box, ""+oResultMAP.getLongDBValue(LAGER_BOX.id_lager_box)))
						.AND(new vgl(LAGER_PALETTE.id_vpos_tpa_fuhre_ein, fuhre_id))
						
						.AND(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
						.ORDER(LAGER_PALETTE.id_lager_palette.tnfn() + " ASC");
						;
						
				if(S.isFull(buchungsnr)) {
					s_einzelnpalette.AND("NVL("+VPOS_TPA_FUHRE.buchungsnr_fuhre.tnfn()+",'')", COMP.EQ.ausdruck(), "'"+buchungsnr+"'")
					.OR("NVL("+LAGER_PALETTE.buchungsnr_hand.tnfn()+",'')",COMP.EQ.ausdruck(), "'"+buchungsnr+"'" );
				}
						
				String[][] paletten_ids = bibDB.EinzelAbfrageInArray(s_einzelnpalette.s(),"");
				VEK<String> vPalettenIds = new VEK<String>();
				for(String[] pal_id:paletten_ids) {
					vPalettenIds._a(pal_id[0]);
				}
				this.m_tpHashMap._setToExtender(LH_CONST.LH_EXTENDER.LH_FUHRE_ID, fuhre_id);
				this.m_tpHashMap._setToExtender(LH_CONST.LH_EXTENDER.LH_EINZELPALETTE_LISTE, vPalettenIds);

				if(this.m_tpHashMap.getFromExtender(LH_CONST.LH_EXTENDER.LH_SHOW_EINZELPALETTE).equals("Y")){
					show_palette_in_detail=true;
				}

				LH_LIST_COMP_detailblock_bt_List2Mask liste_palette_sprung = new LH_LIST_COMP_detailblock_bt_List2Mask(true, this.m_tpHashMap, fuhre_id, vPalettenIds);
				liste_palette_sprung.EXT().set_oComponentMAP(this.EXT().get_oComponentMAP());
				liste_palette_sprung._image(E2_ResourceIcon.get_RI("edit_mini.png"));

				LH_LIST_COMP_detailblock_bt_List2Mask liste_palette_anzeige = new LH_LIST_COMP_detailblock_bt_List2Mask(false, this.m_tpHashMap, fuhre_id, vPalettenIds);
				liste_palette_anzeige.EXT().set_oComponentMAP(this.EXT().get_oComponentMAP());
				liste_palette_anzeige._image(E2_ResourceIcon.get_RI("view_mini.png"));
				
				E2_Grid oFuhreAnzeigeGrid = new E2_Grid()._setSize(50,20)
				._a(new RB_lab(fuhre_id)._fsa(-1), new RB_gld()._left_mid());
				if(S.isFull(fuhre_id)) {
					oFuhreAnzeigeGrid._a(new LH_LIST_bt_SprungZuFuhre(bibALL.convertID2UnformattedID(fuhre_id)), new RB_gld()._center_mid());
				}
				
				RB_lab oLblBuchungsnummer = new RB_lab()._t(fo.get_buchungsnummer());
				
				grdElement
				._a(liste_palette_sprung,																gld_ct)
				._a(liste_palette_anzeige, 																gld_ct)			
				._a(oFuhreAnzeigeGrid,																	gld_lt)
				._a(oLblBuchungsnummer._fsa(-1), 														gld_lt)
				._a(fo.get_kunde(),																		gld_lt)
				._a(new RB_lab(fo.get_sorte())					._fsa(-1), 								gld_lt)
				._a(new RB_lab(bibALL.convertID2FormattedID(""+fo.brutto_menge		)+" "+fo.get_einheit())._fsa(-1), 				gld_rt)
				._a(new RB_lab(bibALL.convertID2FormattedID(""+fo.netto_menge		)+" "+fo.get_einheit())._fsa(-1), 				gld_rt)
				._a(new RB_lab(bibALL.convertID2FormattedID(""+fo.anzahl_paletten	))._fsa(-1), 			gld_rt);

				if(show_palette_in_detail) {

					RecList21 paletten_in_detail = new RecList21(_TAB.lager_palette)._fill(s_einzelnpalette.s());
					if(paletten_in_detail.size()>1) {
						for(Rec21 rec_palette: paletten_in_detail) {
							grdElement
							._a("", new RB_gld()._span(6))
							._a(new RB_lab()._t(bibALL.convertID2FormattedID(rec_palette.getUfs(LAGER_PALETTE.bruttomenge, "0"))+" "+fo.get_einheit())._i()._fsa(-2), new RB_gld()._ins(1)._right_top()._colLL())
							._a(new RB_lab()._t(bibALL.convertID2FormattedID(rec_palette.getUfs(LAGER_PALETTE.nettomenge, "0"))	+" "+fo.get_einheit())._i()._fsa(-2), new RB_gld()._ins(1)._right_top()._colLL())
							._a("", gld_rt)
							;
						}
					}

				}
			}

			MyE2_ContainerEx cont = new MyE2_ContainerEx(grdElement);

			int interactive_height = (this.nb_of_fuhre_angezeigt*20)+1;

			cont.setHeight(new Extent(interactive_height));
			cont.setWidth(new Extent(1205 + 20));
			
			this.setSize(1);
//			this.add(grdTitle, 		E2_INSETS.I(0,0,0,0));
			this.add(cont, 			E2_INSETS.I(0,0,0,0));
			this.add(new RB_lab(), 	E2_INSETS.I(0,10,0,5));
		}
	}


	public LH_LIST_COMP_DetailBlock get_Copy(Object objHelp)  throws myExceptionCopy {
		try {
			return new LH_LIST_COMP_DetailBlock(this.m_tpHashMap);
		}catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}

	private class Paletten_Uebersicht{

		private int anzahl_paletten = 0;
		
		private String buchungsnr = "";
		
		private long 		netto_menge =  0;
		private long 		brutto_menge = 0;

		private Rec21 		record_artikel ;
		private Rec21 		record_fuhre;

		public Paletten_Uebersicht() throws myException {
			this.record_artikel = new Rec21(_TAB.artikel_bez);
			this.record_fuhre = new Rec21(_TAB.vpos_tpa_fuhre);
		}

	

		public Paletten_Uebersicht _set_buchungsnr(String buchungsnr) {
			this.buchungsnr = buchungsnr;
			return this;
		}

		private Paletten_Uebersicht _set_artikel(String ufs_id_artikel) throws myException {
			if(S.isFull(ufs_id_artikel) && !ufs_id_artikel.equals("-")) {
				this.record_artikel._fill_id(ufs_id_artikel); 
			}
			return this;
		}

		private Paletten_Uebersicht _set_fuhre(String ufs_id_vpos_tpa_fuhre_ein) throws myException{
			if(S.isFull(ufs_id_vpos_tpa_fuhre_ein) && !ufs_id_vpos_tpa_fuhre_ein.equals("-")) {
				this.record_fuhre._fill_id(ufs_id_vpos_tpa_fuhre_ein);
			}
			return this;
		}

		public String get_buchungsnummer() throws myException{
			return this.buchungsnr;
		}


		
		public E2_Grid get_kunde() throws myException{
			E2_Grid rueckGrid = new E2_Grid()._s(2);
			if(! record_fuhre.is_newRecordSet() ) {
				Rec21_adresse rec_adresse = new Rec21_adresse(this.record_fuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_start, ADRESSE.id_adresse,false));
				if(rec_adresse.__is_liefer_adresse()) {
					rueckGrid
					._a(new RB_lab()._t(rec_adresse.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2))._lwn()._fsa(-1), new RB_gld()._ins(0,0,5,0))
					._a(new RB_lab()._t(" (Lager von: " + rec_adresse._getMainAdresse().__get_name1_ort()+")")._i()._fsa(-2)._lwn())
					; 
				}else {
					rueckGrid._a(new RB_lab()._t(rec_adresse.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2))._lwn()._fsa(-1));
				}
			}
			return rueckGrid;
		}

		public String get_sorte() throws myException {
			String rueck = "";
			if(! record_artikel.is_newRecordSet()) {
				rueck =  this.record_artikel.get_up_Rec21(ARTIKEL_BEZ.id_artikel,ARTIKEL.id_artikel, false).get_ufs_dbVal(ARTIKEL.anr1)+"-";
				rueck += this.record_artikel.get_ufs_kette(" ", ARTIKEL_BEZ.anr2, ARTIKEL_BEZ.artbez1);
				return rueck;
			}
			return "<->";
		}

		public String get_einheit() throws myException{
			String rueck = "";
			if(! record_artikel.is_newRecordSet()) {
				String einheit = this.record_artikel
						.get_up_Rec21(ARTIKEL_BEZ.id_artikel,ARTIKEL.id_artikel, false)
						.get_up_Rec21(ARTIKEL.id_einheit, EINHEIT.id_einheit, false)
						.get_ufs_dbVal(EINHEIT.einheitkurz,"");
				rueck= einheit;
			}
			return rueck;
		}

	}

}
