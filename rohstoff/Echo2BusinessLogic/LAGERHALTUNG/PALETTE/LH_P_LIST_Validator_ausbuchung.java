/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 22.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author sebastien
 * @date 22.10.2019
 *
 */
public class LH_P_LIST_Validator_ausbuchung extends XX_ActionValidator_NG {

	private RB_TransportHashMap m_trp_hashMap;
	
	public LH_P_LIST_Validator_ausbuchung(RB_TransportHashMap otrpHashMap) {
		super();
		this.m_trp_hashMap = otrpHashMap;
	}

	
	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		VEK<String> vPalettenIds = new VEK<String>();
		
		if(oComponentWhichIsValidated instanceof LH_P_LIST_bt_AusbuchungInRow) {
			E2_Button srcButton = (E2_Button) oComponentWhichIsValidated;
			vPalettenIds._a(srcButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		}else {
			vPalettenIds._a(this.m_trp_hashMap.getNavigationList().get_vSelectedIDs_Unformated());
		}


		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if (vPalettenIds.size()==0) {
			mv._addAlarm(S.ms("Sie muessen mindestens eine Datenzeile auswaehlen !"));
		}else {
			for(String id: vPalettenIds ) {
				Rec21 recPalette = new Rec21(_TAB.lager_palette)._fill_id(id);

				boolean is_hand_ausgebucht = recPalette.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand);
				String id_vpos_tpa_fuhre = new Rec21(_TAB.lager_palette)._fill_id(id).getFs(LAGER_PALETTE.id_vpos_tpa_fuhre_aus,"");

				if(S.isFull(id_vpos_tpa_fuhre) || is_hand_ausgebucht){
					mv._addAlarm("Die Palette ID. " + id + " ist schon ausgebucht !");
				}
			}

		}


		return mv;
	}

}
