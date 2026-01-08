package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.Service.PdServiceLagerhaltungAusbuchung;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class LH_P_LIST_Ausbuchung_Container extends E2_BasicModuleContainer {

	private LH_P_MASK_Comp_fuhreSearch 	fuhre_id_sucheFeld 	= null;
	private RB_date_selektor 			ausbuchung_am 		= null;
	private RB_cb						cb_ausbuchung_hand 	= null;
	private RB_TextArea 				ta_hand_ausbuchung_bem = null;
	
	private VEK<String> vPalettenIds = new VEK<String>();
	private RB_TransportHashMap m_trpHashMap = null;

	public LH_P_LIST_Ausbuchung_Container(RB_TransportHashMap p_trpHashMap, VEK<String> p_vPalettenIds) throws myException {
		super();

		this.fuhre_id_sucheFeld 	= new LH_P_MASK_Comp_fuhreSearch(160, false);
		this.ausbuchung_am 			= new RB_date_selektor(160,true,true);
		this.cb_ausbuchung_hand		= new RB_cb();
		this.ta_hand_ausbuchung_bem = new RB_TextArea(160,2);
		this.m_trpHashMap 			= p_trpHashMap;

		E2_Button ausbuchen_bt = new E2_Button()._t("Palette ausbuchen")._standard_text_button()._sizeWH(120, 20);
		E2_Button abbrechen_bt = new E2_Button()._t("Abbrechen")._standard_text_button()._sizeWH(120, 20);

		ausbuchen_bt._align(E2_ALIGN.CENTER_MID).focus_on();
		abbrechen_bt._align(E2_ALIGN.CENTER_MID).focus_on();

		this.ausbuchung_am.rb_set_db_value_manual(bibALL.get_cDateNOW());
		this.cb_ausbuchung_hand._aaa(()->check_ausbuchung_status());
		this.fuhre_id_sucheFeld.mark_MustField();

		ausbuchen_bt._aaa(()->palette_ausbuchen(p_vPalettenIds))._addGlobalValidator(new ownIsReady2Ausbuchen_validatorNg());
		abbrechen_bt._aaa(()->popup_schliessen());

		E2_Grid grd = new E2_Grid()._s(2)._w100()
				._a("Ausbuchungsfuhre ID: ",  	new RB_gld()._ins(2)._left_top())._a(fuhre_id_sucheFeld,  	new RB_gld()._ins(2)._left_top())
				._a("Ausbuchung Hand:", 		new RB_gld()._ins(2)._left_top())._a(cb_ausbuchung_hand,  	new RB_gld()._ins(2)._left_top())
				._a("Bemerkung: ", 				new RB_gld()._ins(2)._left_top())._a(ta_hand_ausbuchung_bem,new RB_gld()._ins(2)._left_top())
				._a("Ausbuchungsdatum:",  		new RB_gld()._ins(2,2,2,10)._left_top())._a(ausbuchung_am, 	new RB_gld()._ins(2,2,2,10)._left_top())
				._a(ausbuchen_bt, new RB_gld()._ins(20,2,2,2)._center_bottom())._a(abbrechen_bt, 			new RB_gld()._ins(2,2,20,2)._center_bottom());

		this.add(grd);
	}


	/**
	 * @author sebastien
	 * @date 22.10.2019
	 *
	 * @return
	 * @throws myException 
	 */
	private void check_ausbuchung_status() throws myException {
		this.fuhre_id_sucheFeld.set_bEnabled_For_Edit(! this.cb_ausbuchung_hand.isSelected());
		if(this.cb_ausbuchung_hand.isSelected()) {
			this.fuhre_id_sucheFeld.mark_Neutral();
		}else {
			this.fuhre_id_sucheFeld.mark_MustField();
		}
	}


	public void palette_ausbuchen(VEK<String> p_vPalettenIds) throws myException {

		this.ausbuchung_am.mark_Neutral();
		this.fuhre_id_sucheFeld.mark_Neutral();
		this.vPalettenIds._clear()._a(p_vPalettenIds);
		boolean is_hand_ausbuchung = this.cb_ausbuchung_hand.isSelected();
		
		PdServiceLagerhaltungAusbuchung ausbuchung_service = new PdServiceLagerhaltungAusbuchung();
		
		MyE2_MessageVector mv = new MyE2_MessageVector();

		String ausbuchungsDatum 	= this.ausbuchung_am.get__actual_maskstring_in_view();
		String ausbuchungsFuhreId 	= this.fuhre_id_sucheFeld.get__actual_maskstring_in_view();
		String bemerkung 			= this.ta_hand_ausbuchung_bem.get__actual_maskstring_in_view();
		
		if(is_hand_ausbuchung) {
			ausbuchung_service._ausbuchung_von_hand(p_vPalettenIds, ausbuchungsDatum, bemerkung, mv);
		}else {
			ausbuchung_service._ausbuchung_mit_fuhre(this.vPalettenIds,ausbuchungsFuhreId, ausbuchungsDatum,  mv);
		}

		if(mv.get_bIsOK()) {
			this.m_trpHashMap.getNavigationList()._REBUILD_COMPLETE_LIST("");

			this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}else {
			bibMSG.add_MESSAGE(mv);
		}

	}

	public void popup_schliessen() throws myException {
		this.m_trpHashMap.getNavigationList()._REBUILD_COMPLETE_LIST("");
		this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
	}

	private class ownIsReady2Ausbuchen_validatorNg extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			ausbuchung_am.mark_Neutral();
			fuhre_id_sucheFeld.mark_Neutral();
			fuhre_id_sucheFeld.set_bEnabled_For_Edit(true);
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if(S.isEmpty(fuhre_id_sucheFeld.get__actual_maskstring_in_view())) {
				if(! cb_ausbuchung_hand.isSelected()) {
					mv._addAlarm("Bitte suchen sie eine Fuhre !");
					fuhre_id_sucheFeld.mark_FalseInput();
				}
			}
			if(S.isEmpty(ausbuchung_am.get__actual_maskstring_in_view())) {
				mv._addAlarm("Kein Ausbuchungsdatum !");
			
				ausbuchung_am.mark_FalseInput();
			}

			return mv;
		}

	}
}
