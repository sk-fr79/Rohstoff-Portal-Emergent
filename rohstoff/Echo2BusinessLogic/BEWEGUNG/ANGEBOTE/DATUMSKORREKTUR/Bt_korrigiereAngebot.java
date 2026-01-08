package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button4Valid;
import panter.gmbh.basics4project.VALIDATOR_KEY;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;


/**
 * ##TODO@Sebastien
 * @author martin
 *
 */
public class Bt_korrigiereAngebot extends E2_Button4Valid {

	private String  id_vkopf_std = null;

	private XX_List_EXPANDER_4_ComponentMAP parent = null;
	/**
	 * 
	 * @param label-text
	 * @param p_id_vkopf_std
	 */
	public Bt_korrigiereAngebot(String label, String  p_id_vkopf_std, XX_List_EXPANDER_4_ComponentMAP oListToRefresh) {
		super(VALIDATOR_KEY.DATUMSAENDERUNG_ANGEBOTE);
		this.id_vkopf_std = p_id_vkopf_std;
		
		this._bord(null)._fsa(-2)._i()._t(label)._lw(false)._gld(new RB_gld()._ins(2, 2, 3, 1)._right_top());

		this.parent = oListToRefresh;
		
		this.add_oActionAgent(new ownActionAgentEditDaten()	);
	}
	
	
	private class ownActionAgentEditDaten extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Bt_korrigiereAngebot oThis = Bt_korrigiereAngebot.this;
			
			MyLong l_id = new MyLong(oThis.id_vkopf_std);
			
			if (l_id.get_bOK()) {
				RECORD_VKOPF_STD rec_kopf = new RECORD_VKOPF_STD(l_id.get_lValue());
				
				Bt_korrigiereAngebote_Datum_Popup popup = new Bt_korrigiereAngebote_Datum_Popup(rec_kopf, parent);
				
				popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(580), new Extent(300), new MyE2_String("Änderung der Gültigkeit ALLER Positionen des Angebots"));
				
			} else {
				throw new  myException("...");
			}
		
		}
		
	}	
}
