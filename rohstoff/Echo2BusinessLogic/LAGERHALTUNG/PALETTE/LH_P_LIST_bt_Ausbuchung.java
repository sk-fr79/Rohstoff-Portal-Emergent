package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class LH_P_LIST_bt_Ausbuchung extends E2_Button {

	private RB_TransportHashMap m_trpHashMap;

//	private boolean access_by_boxPaletteMask = false;

	public LH_P_LIST_bt_Ausbuchung(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		this._image("lagerabgang.png","leer.png");
		this._ttt(S.ms("Selektierte Paletten ausbuchen"));

		this.m_trpHashMap = p_tpHashMap;

//		if(this.m_trpHashMap.getModulContainerList().get_MODUL_IDENTIFIER().equals(LH_P_CONST.TRANSLATOR.LIST.get_modul().get_callKey())) {
//			access_by_boxPaletteMask = false;
//		}else if(this.m_trpHashMap.getModulContainerList().get_MODUL_IDENTIFIER().equals(LH_P_CONST.TRANSLATOR.LAGER_PALETTE_LIST_INTERNAL.get_modul().get_callKey())){
//			access_by_boxPaletteMask = true;
//		}else{
//
//			throw new myException("FEHLER: 5b256146-39f9-43b9-8660-f6a99c6ddb0b : unknwon system problem");
//		}

		this.add_GlobalValidator(new LH_P_LIST_Validator_ausbuchung(this.m_trpHashMap));
				/*new XX_ActionValidator_NG() {
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				LH_P_LIST_bt_Ausbuchung oThis = LH_P_LIST_bt_Ausbuchung.this;

				VEK<String> vSelectedIds = new VEK<String>()._a(oThis.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated());

				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (oThis.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated().size()==0) {
					mv._addAlarm(new MyE2_String("Sie muessen mindestens eine Datenzeile auswaehlen !"));
				}

				for(String id: vSelectedIds ) {
					Rec21 recPalette = new Rec21(_TAB.lager_palette)._fill_id(id);
					boolean is_hand_ausgebucht = recPalette.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand);

					String idVposAusbuchung = recPalette.getFs(LAGER_PALETTE.ausbuchung_hand);

					if(S.isFull(idVposAusbuchung) || is_hand_ausgebucht) {
						mv._addAlarm("Die Palette ID. " + id + " ist schon ausgebucht !");
					}
				}
				return mv;
			}
		}*/
	
		this._aaa( ()->make_ausbuchung() );
	}

	private VEK<String> get_paletten_id() throws myException{

		//		VEK<String> selectedIds = new VEK<String>();

		VEK<String> vIdPaletten = new VEK<String>()._a(this.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated());

		//		if(! access_by_boxPaletteMask) {
		//			vIdPaletten._a(selectedIds);	
		//		}else {
		//			for(String selected_id: selectedIds) {
		//				vIdPaletten._a(new Rec21(_TAB.lager_palette_box)._fill_id(selected_id).getUfs(LAGER_PALETTE_BOX.id_lager_palette));
		//			}
		//		}
		return vIdPaletten;

	}


	private void make_ausbuchung() throws myException {

		LH_P_LIST_Ausbuchung_Container popup = new LH_P_LIST_Ausbuchung_Container(this.m_trpHashMap, get_paletten_id());

		popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(380), new Extent(300), S.ms("Paletten ausbuchung"));

	}



	private RB_TransportHashMap getTransportHashMap() {
		return this.m_trpHashMap;

	}

}
