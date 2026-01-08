package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button4Valid;
import panter.gmbh.basics4project.VALIDATOR_KEY;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD_ANGEBOT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;


/**
 * ##TODO@Sebastien
 * @author martin
 *
 */
public class Bt_korrigiereAngebotPosition extends E2_Button4Valid {

	private String  id_vpos_std = null;

	private XX_List_EXPANDER_4_ComponentMAP parent = null;
	
	/**
	 * 
	 * @param label-text
	 * @param p_id_vpos_std
	 * @param bsa_K_LIST_EXPANDER_4_ComponentMAP 
	 * @param bsa_K_LIST_EXPANDER_4_ComponentMAP 
	 */
	public Bt_korrigiereAngebotPosition(String label, String  p_id_vpos_std, XX_List_EXPANDER_4_ComponentMAP oListToRefreshInstance ) {
		super(VALIDATOR_KEY.DATUMSAENDERUNG_ANGEBOTE);
		this.id_vpos_std = p_id_vpos_std;
		
		this.parent=oListToRefreshInstance;
		
		this._bord(null)._fsa(-2)._i()._t(label)._lw(false)._gld(new RB_gld()._ins(2, 2, 3, 1));

		this.add_oActionAgent(new ownActionAgentEditDaten()	);
	}
	
	
	private class ownActionAgentEditDaten extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Bt_korrigiereAngebotPosition oThis = Bt_korrigiereAngebotPosition.this;
			
			MyLong l_id = new MyLong(oThis.id_vpos_std);
			
			if (l_id.get_bOK()) {
				RECORD_VPOS_STD_ANGEBOT rec_vpos = new RECORD_VPOS_STD_ANGEBOT(new vgl(VPOS_STD_ANGEBOT.id_vpos_std, oThis.id_vpos_std).s());
			
				Bt_korrigiereAngebote_Datum_Popup popup = new Bt_korrigiereAngebote_Datum_Popup(rec_vpos, parent);
				
				popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(350), new MyE2_String("Änderung der Gültigkeit der gewählten Position des Angebots"));
				
			} else {
				throw new  myException("...");
			}
		
		}
		
	}
	
	
	
}
