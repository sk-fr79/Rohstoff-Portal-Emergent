package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._SET_AN_VALID;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_STD;


/**
 * validierer, prueft
 * @author martin
 *
 */
public class FZ_SaV_CheckLeistungsDatumGegenPlanungszeitraum extends RB_Mask_Set_And_Valid {
	
	private IF_MasterKey  				maskKey = null;
	private RB_ComponentMap  			comp_map = null; 
	
	
	/**
	 * @param p_maskKey
	 */
	public FZ_SaV_CheckLeistungsDatumGegenPlanungszeitraum(IF_MasterKey p_maskKey, RB_ComponentMap p_comp_map) {
		super();
		this.maskKey = p_maskKey;
		this.comp_map = p_comp_map;
	}


	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
			RB_MaskController  con = new RB_MaskController(this.comp_map);

			KEY_VEKT kv = this.maskKey.k_vektor(); 
			
			MyDate ladedatum_von = con.get_MyDate_liveVal(kv, BEWEGUNG_VEKTOR.l_datum_von.fk());
			MyDate ladedatum_bis = con.get_MyDate_liveVal(kv, BEWEGUNG_VEKTOR.l_datum_bis.fk());

			MyDate abladedatum_von = con.get_MyDate_liveVal(kv, BEWEGUNG_VEKTOR.a_datum_von.fk());
			MyDate abladedatum_bis = con.get_MyDate_liveVal(kv, BEWEGUNG_VEKTOR.a_datum_bis.fk());

			//zuerst die linke seite
			MyDate leistungsdatum_start = con.get_MyDate_liveVal(this.maskKey.getKeyAtomStart(), BEWEGUNG_ATOM.leistungsdatum.fk());
			
			//zuerst die rechte seite
			MyDate leistungsdatum_ziel = con.get_MyDate_liveVal(this.maskKey.getKeyAtomZiel(), BEWEGUNG_ATOM.leistungsdatum.fk());
			
			if (ladedatum_von.isOK() && ladedatum_bis.isOK() && leistungsdatum_start.isOK()) {
				if (! (ladedatum_von.isLessEqualThan(leistungsdatum_start) && ladedatum_bis.isGreaterEqualThan(leistungsdatum_start))) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Auf der Startseite passt das Leistungsdatum nicht in den Planungszeitraum")));
				}
			}
			
			if (abladedatum_von.isOK() && abladedatum_bis.isOK() && leistungsdatum_ziel.isOK()) {
				if (! (abladedatum_von.isLessEqualThan(leistungsdatum_ziel) && abladedatum_bis.isGreaterEqualThan(leistungsdatum_ziel))) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Auf der Zielseite passt das Leistungsdatum nicht in den Planungszeitraum")));
				}
			}

		}
		
		return mv;
	}


	
	
}
