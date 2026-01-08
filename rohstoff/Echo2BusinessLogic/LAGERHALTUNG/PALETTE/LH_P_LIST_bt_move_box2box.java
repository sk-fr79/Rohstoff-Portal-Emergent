/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 21.05.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class LH_P_LIST_bt_move_box2box extends E2_Button {
	private RB_TransportHashMap  m_trpHashMap = null;

//	private boolean access_by_boxPaletteMask = false;

	public LH_P_LIST_bt_move_box2box(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		this.m_trpHashMap = p_tpHashMap;

		//		this._image("box_2_box.png", "leer.png");
		this.__setImages(E2_ResourceIcon.get_RI("box_2_box.png"), E2_ResourceIcon.get_RI("leer.png"));

		this._ttt(S.ms("Selektiert Paletten - Box aenderung"));

		/*if(this.m_trpHashMap.getModulContainerList().get_MODUL_IDENTIFIER().equals(LH_P_CONST.TRANSLATOR.LIST.get_modul().get_callKey())) {
			access_by_boxPaletteMask = false;
		}else if(this.m_trpHashMap.getModulContainerList().get_MODUL_IDENTIFIER().equals(LH_P_CONST.TRANSLATOR.LAGER_PALETTE_LIST_INTERNAL.get_modul().get_callKey())){
			access_by_boxPaletteMask = true;
		}else{
			throw new myException("FEHLER: bc838e6d-78a1-4b8b-8fa7-995d3c41ae64 : unknwon system problem");
		}*/

		this.add_GlobalValidator(new LH_P_LIST_Validator_ausbuchung(this.m_trpHashMap));
		this._aaa( ()->call_popup() );
	}


	public RB_TransportHashMap getTransportHashMap() {
		return m_trpHashMap;
	}

	private VEK<String> get_paletten_id() throws myException{

		VEK<String> vIdPaletten = new VEK<String>()._a(this.m_trpHashMap.getNavigationList().get_vSelectedIDs_Unformated());

		return vIdPaletten;

	}

	private void  call_popup() throws myException {

		LH_P_LIST_boxAenderung_Container popup = new LH_P_LIST_boxAenderung_Container(this.m_trpHashMap, get_paletten_id());

		popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(380), new Extent(200), S.ms("Box ändern"));
	}



}
