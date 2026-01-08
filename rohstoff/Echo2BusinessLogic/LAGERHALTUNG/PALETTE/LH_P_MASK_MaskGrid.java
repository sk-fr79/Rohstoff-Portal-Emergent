
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST;

public class LH_P_MASK_MaskGrid extends E2_Grid {

	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;

	//wird benutzt, falls mehr als ein E2_Grid verwendung findet
	private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);

	/*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */

	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
	private VEK<MyString>  tabText = 			new VEK<MyString>();

	public LH_P_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		int iWidthComplete = LH_P_CONST.LH_P_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
				LH_P_CONST.LH_P_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
		this._setSize(iWidthComplete)._bo_no();

		this._setSize(800)._bo_no();

		this.m_tpHashMap = p_tpHashMap;
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);

		LH_P_MASK_ComponentMap  map1 = null;

		map1 = (LH_P_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());

		E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( LH_P_CONST.LH_P_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
				LH_P_CONST.LH_P_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        

		int iZahl = 1;

		tabText._a(S.ms("Tab "+(iZahl++)));

		RB_gld  gld_2222_lt = new RB_gld()._ins(2,2,2,2)._left_top();

		g1
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_palette)) ,			gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.id_lager_palette), 												gld_2222_lt)
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_box)) ,				gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.id_lager_box), 													gld_2222_lt)
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.ist_palette)) ,				gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.ist_palette), 													gld_2222_lt)

		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.chargennummer)) ,				gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.chargennummer), 													gld_2222_lt)
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.einbuchung_hand)),			new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(LAGER_PALETTE.einbuchung_hand),												new RB_gld()._ins(2)._left_top())
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_adresse_einbuch_hand)),	new RB_gld()._ins(2,3,2,1)._left_top())
		._a(map1.getComp(LAGER_PALETTE.id_adresse_einbuch_hand),										new RB_gld()._ins(2)._left_top())
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_vpos_tpa_fuhre_ein)) ,		new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(LAGER_PALETTE.id_vpos_tpa_fuhre_ein), 											new RB_gld()._ins(2)._left_top())
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.buchungsnr_hand)),			new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(LAGER_PALETTE.buchungsnr_hand),												new RB_gld()._ins(2)._left_top())
		
		
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_artikel_bez)) ,			gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.id_artikel_bez), 												gld_2222_lt)
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.material_zusatzinfo)),		gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.material_zusatzinfo), 											gld_2222_lt)
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.ausbuchung_hand)),			new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(LAGER_PALETTE.ausbuchung_hand),												new RB_gld()._ins(2)._left_top())
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_adresse_ausbuch_hand)),	new RB_gld()._ins(2,3,2,1)._left_top())
		._a(map1.getComp(LAGER_PALETTE.id_adresse_ausbuch_hand),										new RB_gld()._ins(2)._left_top())
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.hand_ausbuchung_bemerkung)),	new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(LAGER_PALETTE.hand_ausbuchung_bemerkung),										new RB_gld()._ins(2)._left_top())
		
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_vpos_tpa_fuhre_aus)) ,		gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.id_vpos_tpa_fuhre_aus), 											gld_2222_lt)

		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.datum_verarbeitet)) ,			gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.datum_verarbeitet), 												gld_2222_lt)
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.gepresst_von)) ,				gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.gepresst_von), 													gld_2222_lt)

		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.palettiert_von)) ,			gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.palettiert_von), 												gld_2222_lt)
		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.endkontrolle_von)) ,			gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.endkontrolle_von), 												gld_2222_lt)

		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.bruttomenge)) ,				gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.bruttomenge), 													gld_2222_lt)

		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.taramenge)) ,					gld_2222_lt)
		._a(map1.getComp(LAGER_PALETTE.taramenge), 														gld_2222_lt)

		._a(new RB_lab(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.nettomenge)) ,				gld_2222_lt)
		._a(new E2_Grid()._s(2)
				._a(map1.getComp(LAGER_PALETTE.nettomenge), 											gld_2222_lt)
				._a(map1.getComp(new RB_KF(LH_CONST.MASK_KEY.LH_MASK_MENGE_HELP.k())), 					gld_2222_lt) 
				,new RB_gld()._left_mid())

		;

		tabText._a(S.ms("Tab "+(iZahl++)));

		this.renderMask();

		this.resize(LH_P_CONST.LH_P_NUM_CONST.MASKPOPUP_WIDTH.getValue(), LH_P_CONST.LH_P_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
	}


	private void renderMask() throws myException {

		if (this.fieldContainers.size()==1) {
			this._a(this.fieldContainers.get(0));
		} else {
			for (int i=0; i<this.fieldContainers.size(); i++) {
				MyString s_tab = this.tabText.size()>i?S.NN(this.tabText.get(i), S.ms("..")):S.ms("Tab Nr: ").ut(" "+(i+1));
				this.ta.add_Tabb(s_tab, this.fieldContainers.get(i));
			}
			this._a(this.ta);
		}
	}


	/*
	 * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
	 */
	public void resize(int width, int height) {
		this.ta.setWidth(new Extent(width-60));
		this.ta.setHeight(new Extent(height-170));
	}

}


