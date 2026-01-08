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
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_STD;


/**
 * validierer, prueft
 * @author martin
 *
 */
public class FZ_SaV_CheckDatumKontraktGegenLeistungsDatum extends RB_Mask_Set_And_Valid {
	
	private IF_MasterKey  				maskKey = null;
	private RB_ComponentMap  			comp_map = null; 
	
	
	/**
	 * @param p_maskKey
	 */
	public FZ_SaV_CheckDatumKontraktGegenLeistungsDatum(IF_MasterKey p_maskKey, RB_ComponentMap p_comp_map) {
		super();
		this.maskKey = p_maskKey;
		this.comp_map = p_comp_map;
	}


	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		//alle atome durchsuchen, die in der maske einen kontrakt oder ein angebot enthalten, wenn ja, die jeweilige gueltigkeit gegen die gueltigkeit 
		//des leistungsdatums checken
		
		if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
		
			Vector<IF_KeyChain> vAllKeys = this.maskKey.get_all_child_keys();
			
			RB_MaskController  con = new RB_MaskController(this.comp_map);
			
			
			//alle atom-keys suchen
			for (IF_KeyChain key: vAllKeys) {
				if (key instanceof KEY_ATOM) {
					KEY_ATOM at = (KEY_ATOM) key;
					
					MyDate dat = con.get_MyDate_liveVal(at, BEWEGUNG_ATOM.leistungsdatum.fk());
					
					if (dat.isOK()) {
						
						//dann nach kontrakt oder angebot sehen
						MyLong l_vpos_kon = con.get_MyLong_liveVal(at, BEWEGUNG_ATOM.id_vpos_kon.fk());
						MyLong l_vpos_std = con.get_MyLong_liveVal(at, BEWEGUNG_ATOM.id_vpos_std.fk());
						
						if (l_vpos_kon.isOK()) {
							Rec20_VPOS_KON rec = (Rec20_VPOS_KON)new Rec20_VPOS_KON()._fill_id(l_vpos_kon.get_lValue());
							if (!rec.isDateInKontaktRange(dat)) {
								mv.add(new MyE2_Alarm_Message(S.ms("Fehler in der Planunug: Das Datum der Warenbewegung ").ut(dat.get_cDateStandardFormat()).t(" passt nicht in den Gültikeitszeitraum des Kontrakts!")));
							}
						}
						
						if (l_vpos_std.isOK()) {
							Rec20_VPOS_STD rec = (Rec20_VPOS_STD)new Rec20_VPOS_STD()._fill_id(l_vpos_std.get_lValue());
							if (!rec.isDateInKontaktRange(dat)) {
								mv.add(new MyE2_Alarm_Message(S.ms("Fehler in der Planunug: Das Datum der Warenbewegung ").ut(dat.get_cDateStandardFormat()).t(" passt nicht in den Gültikeitszeitraum des Angebots!")));
							}
						}
					}
					
				}
			}
		}
		
		return mv;
	}


	
	
}
