package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class LH_P_LIST_boxAenderung_Container extends E2_BasicModuleContainer {

	private MyE2_SelectField   			boxSelectField 	= null;

	private VEK<String> vPalettenIds = new VEK<String>();

	private RB_TransportHashMap m_trpHashMap = null;

	public LH_P_LIST_boxAenderung_Container(RB_TransportHashMap p_trpHashMap, VEK<String> p_vPalettenIds) throws myException {
		super();

		this.m_trpHashMap 		= p_trpHashMap;
		this.vPalettenIds 		= p_vPalettenIds;
		E2_Button ausbuchen_bt 	= new E2_Button()._t("Box ändern")._standard_text_button()._sizeWH(120, 20);
		E2_Button abbrechen_bt	= new E2_Button()._t("Abbrechen")._standard_text_button()._sizeWH(120, 20);

		ausbuchen_bt._align(E2_ALIGN.CENTER_MID).focus_on();

		ausbuchen_bt._aaa(()->box_aendern());
		abbrechen_bt._aaa(()->popup_schliessen());

		E2_Grid oQuelleBoxGrid = new E2_Grid()._s(1)._bo_dd()._w100();

		String lagerBoxAbfrage = new SEL()
				.ADDFIELD(LAGER_BOX.boxnummer.tnfn())
				.ADDFIELD(LAGER_BOX.id_lager_box)
				.FROM(_TAB.lager_box)
				.WHERE(new vgl(LAGER_BOX.id_mandant, bibALL.get_ID_MANDANT()))
				.ORDERUP(LAGER_BOX.is_default_box.tnfn()+","+LAGER_BOX.id_lager_box.tnfn())
				.s();

		String[][] BoxNrArray = bibDB.EinzelAbfrageInArray(lagerBoxAbfrage);
		BoxNrArray =  bibARR.add_emtpy_db_value_inFront(BoxNrArray);

		this.boxSelectField = new MyE2_SelectField(BoxNrArray, "", false);

		VEK<String> boxNrn = new VEK<>();
		for(String id_palette:vPalettenIds) {
			boxNrn._addIfNotIN(new Rec21(_TAB.lager_palette)._fill_id(id_palette).get_up_Rec21(LAGER_PALETTE.id_lager_box,  LAGER_BOX.id_lager_box, false).getUfs(LAGER_BOX.boxnummer));
		}

		for(String boxNr : boxNrn) {
			oQuelleBoxGrid._a(new RB_lab()._t(boxNr), new RB_gld()._ins(2)._center_mid());
		}

		E2_Grid grd = new E2_Grid()._s(3)._w100()
				._a(new RB_lab()._t("Quelle Box ")._bi(),  	new RB_gld()._ins(2)._left_mid()._col_back_dd())
				._a("",  									new RB_gld()._ins(2)._left_top()._col_back_dd())
				._a(new RB_lab()._t("Ziel Box")._bi(),  	new RB_gld()._ins(2)._left_mid()._col_back_dd())

				._a(oQuelleBoxGrid, 					new RB_gld()._ins(2)._left_mid())
				._a(new RB_lab()._icon("right.png"),	new RB_gld()._ins(2)._center_mid())
				._a(boxSelectField,						new RB_gld()._ins(2)._left_mid())
				;

		this.add(grd);
		this.add(new E2_Grid()._s(2)._w100()._a(ausbuchen_bt, new RB_gld()._center_mid())._a(abbrechen_bt, new RB_gld()._center_mid()) , E2_INSETS.I(1,15,1,1));
	}


	public void box_aendern() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		boxAenderungProzess(this.vPalettenIds, mv);

		if(mv.get_bIsOK()) {
		
			this.m_trpHashMap.getNavigationList()._REBUILD_COMPLETE_LIST("");
			
			this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}else {
			bibMSG.add_MESSAGE(mv);
		}
	}




	private void boxAenderungProzess(VEK<String> vPalettenIds2,  MyE2_MessageVector mv) throws myException {
		String datum = bibALL.get_cDateNOW();

		String mask_box_id 	= this.boxSelectField.get_ActualWert();

		VEK<Rec21> vRecPalette 		= new VEK<>();
		VEK<Rec21> vRecPaletteBox 	= new VEK<Rec21>();

		for(String id_palette: vPalettenIds) {
			vRecPalette._a(new Rec21(_TAB.lager_palette)._fill_id(id_palette));

			SEL query = new SEL().FROM(_TAB.lager_palette_box).WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, id_palette)).AND(new VglNull(LAGER_PALETTE_BOX.ausbuchung_am));
			vRecPaletteBox._a(new Rec21(_TAB.lager_palette_box)._fill_sql(query.s()));
		}

		for(int i=0 ; i<vRecPalette.size() ; i++) {

			vRecPalette.get(i)._nv(LAGER_PALETTE.id_lager_box, mask_box_id,mv)._SAVE(true, mv);
		}

		for(int i=0 ; i<vRecPalette.size() ; i++) {
			vRecPaletteBox.get(i)._nv(LAGER_PALETTE_BOX.ausbuchung_am, datum, mv)._SAVE(true, mv);

			new Rec21(_TAB.lager_palette_box)
			._nv(LAGER_PALETTE_BOX.einbuchung_am, datum, mv)
			._nv(LAGER_PALETTE_BOX.id_lager_box, mask_box_id, mv)
			._nv(LAGER_PALETTE_BOX.id_lager_palette, vRecPalette.get(i).get_key_value(), mv)._SAVE(true, mv);

		}

		mv._addWarn(S.ms("Palette wurde umgebucht"));

	}

	public void popup_schliessen() throws myException {
		this.m_trpHashMap.getNavigationList()._REBUILD_COMPLETE_LIST("");
		this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
	}
}
