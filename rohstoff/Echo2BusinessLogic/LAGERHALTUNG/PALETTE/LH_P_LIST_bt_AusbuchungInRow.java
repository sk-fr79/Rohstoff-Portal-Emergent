package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author sebastien
 * @date 07.01.2019
 *
 */
public class LH_P_LIST_bt_AusbuchungInRow extends E2_Button{
	/**
	 * @author sebastien
	 * @date 07.01.2019
	 *
	 */

	private RB_TransportHashMap 		m_trp_hm 		= null;


	public LH_P_LIST_bt_AusbuchungInRow(RB_TransportHashMap p_trp_hm) throws myException{
		super();

		this.m_trp_hm 		= p_trp_hm;

		this._image("lagerabgang.png","leer.png");
		this._ttt(S.ms("Palette ausbuchen"));
		this._aaa(()->generate_buchung_popup());
		this._addGlobalValidator(new LH_P_LIST_Validator_ausbuchung(this.m_trp_hm));

	}
	

	private void generate_buchung_popup() throws myException{
		String id_palette = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		LH_P_LIST_Ausbuchung_Container popup = new LH_P_LIST_Ausbuchung_Container(this.m_trp_hm, new VEK<String>()._a(id_palette));
		
		popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(380), new Extent(300), S.ms("Palette Id. " + id_palette + " ausbuchen"));
	}

	public RB_TransportHashMap getTransportHashMap(){
		return this.m_trp_hm;
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		LH_P_LIST_bt_AusbuchungInRow copy=null;
		try {
			copy = new LH_P_LIST_bt_AusbuchungInRow(this.getTransportHashMap());
			copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return copy;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
}
