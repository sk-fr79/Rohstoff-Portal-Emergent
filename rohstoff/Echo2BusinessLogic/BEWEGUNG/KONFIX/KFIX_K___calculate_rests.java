package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K___calculate_rests {

	
	private RB_ComponentMap  rb_c_map = null;
	
	
	
	/**
	 * @throws myException 
	 * 
	 */
	public KFIX_K___calculate_rests(RB_ComponentMap  p_rb_c_map, boolean use_at_mask) throws myException {
		super();
		this.rb_c_map = p_rb_c_map;
		
		ownMaskController mc = new ownMaskController(this.rb_c_map);
		
		MyBigDecimal bdGesamtMengeFixierung_lief = 	mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), VKOPF_KON.fix_menge_gesamt);
		MyBigDecimal bdGesamtMengeFixierung_fix = 	mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), VKOPF_KON.fix_menge_gesamt);
		
		MyBigDecimal bdGeliefert = 					mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT.key());
		MyBigDecimal bdGeliefertRestMenge = 		mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key());

		MyBigDecimal bdFixiert = 					mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT.key());
		MyBigDecimal bdFixiertRestMenge = 			mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key());
		
		
		if (bdGesamtMengeFixierung_lief == null || bdGesamtMengeFixierung_lief.get_longValue()<=0) {
			if (use_at_mask) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Berechnung ist nur möglich, wenn eine Fixierungsmenge vorhanden ist !")));
			}
		} else {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			bdFixiertRestMenge = bdGesamtMengeFixierung_lief.subtract_from_me(bdFixiert.get_bdWert());
			if (bibNUM.IS_LESS_0(bdFixiertRestMenge.get_bdWert())) {
				mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fixierungsmenge ist überschritten !")));
			}	
			mc.set_maskVal(new RB_KM(_TAB.vkopf_kon), KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key(), bdFixiertRestMenge.get_FormatedRoundedNumber(3), bibMSG.MV());
			
			
			bdGeliefertRestMenge = bdGesamtMengeFixierung_fix.subtract_from_me(bdGeliefert);
			if (bibNUM.IS_LESS_0(bdGeliefertRestMenge.get_bdWert())) {
				mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fixierungsmenge ist überschritten !")));
			}			
			
			mc.set_maskVal(new RB_KM(_TAB.vkopf_kon), KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key(), bdGeliefertRestMenge.get_FormatedRoundedNumber(3), bibMSG.MV());
			
			if(bdGeliefertRestMenge.get_longValue()<0){
				((RB_TextAnzeige)p_rb_c_map.getRbComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()))._col_back_alarm();
			}else{
				((RB_TextAnzeige)p_rb_c_map.getRbComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()))._col_back(Color.GREEN);
			}
			
			bibMSG.add_MESSAGE(mv);
		}

		
	}


	private class ownMaskController extends RB_MaskControllerMap {

		/**
		 * @param componentMapCollector
		 * @throws myException
		 */
		public ownMaskController(RB_ComponentMap componentMap) throws myException {
			super(componentMap.rb_get_belongs_to());
		}

		/**
		 * @param p_component
		 * @throws myException
		 */
		public ownMaskController(IF_RB_Component p_component) throws myException {
			super(p_component);
		}

		@Override
		public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)		throws myException {
			return null;
		}
		
	}

	
	
}
