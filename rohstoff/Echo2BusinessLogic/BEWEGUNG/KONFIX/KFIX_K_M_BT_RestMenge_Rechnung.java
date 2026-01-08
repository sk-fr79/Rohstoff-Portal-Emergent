package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_BT_RestMenge_Rechnung extends E2_Button {

	/**
	 * 
	 */
	public KFIX_K_M_BT_RestMenge_Rechnung() {
		super();
		
		this._tr("Neuberechnung Restmenge")._backDDark()._lw(true)._i(E2_INSETS.I(3))._style(E2_Button.StyleTextButtonCentered());
		this.setWidth(new Extent(100));
		this.setHeight(new Extent(40));
		
		this._aaa(new ownActionAgent());
		
	}

	
	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			KFIX_K_M_BT_RestMenge_Rechnung oThis = KFIX_K_M_BT_RestMenge_Rechnung.this;
			new KFIX_K___calculate_rests(oThis._find_componentMap_i_belong_to(),true);
			
			
//			
//			ownMaskController mc = new ownMaskController(oThis);
//			
//			MyBigDecimal bdGesamtMengeFixierung_lief = 	mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), VKOPF_KON.fix_menge_gesamt);
//			MyBigDecimal bdGesamtMengeFixierung_fix = 	mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), VKOPF_KON.fix_menge_gesamt);
//			
//			MyBigDecimal bdGeliefert = 					mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_GELIEFERT.key());
//			MyBigDecimal bdGeliefertRestMenge = 		mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_GELIEFERT_RESTMENGE.key());
//
//			MyBigDecimal bdFixiert = 					mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_FIXIERT.key());
//			MyBigDecimal bdFixiertRestMenge = 			mc.get_MyBigDecimal_liveVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_FIXIERT_RESTMENGE.key());
//			
//			DEBUG.System_println(bdGesamtMengeFixierung_lief.get_UnFormatedRoundedNumber(3));
//			DEBUG.System_println(bdGesamtMengeFixierung_fix.get_UnFormatedRoundedNumber(3));
//			DEBUG.System_println(bdGeliefert.get_UnFormatedRoundedNumber(3));
//			DEBUG.System_println(bdGeliefertRestMenge.get_UnFormatedRoundedNumber(3));
//			DEBUG.System_println(bdFixiert.get_UnFormatedRoundedNumber(3));
//			DEBUG.System_println(bdFixiert.get_UnFormatedRoundedNumber(3));
//			
//			TextField comp1 = (TextField)mc.get_comp(new RB_KM(_TAB.vkopf_kon), VKOPF_KON.fix_menge_gesamt, bibMSG.MV());
//			TextField comp2 = (TextField)mc.get_comp(new RB_KM(_TAB.vkopf_kon), VKOPF_KON.fix_menge_gesamt, bibMSG.MV());
//			
//			TextField comp3 = (TextField)mc.get_comp(new RB_KM(_TAB.vkopf_kon),BSK_K_CONST.ADDITIONNAL_COMP.TA_GELIEFERT.key(), bibMSG.MV());
//			TextField comp4 = (TextField)mc.get_comp(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_GELIEFERT_RESTMENGE.key(), bibMSG.MV());
//			TextField comp5 = (TextField)mc.get_comp(new RB_KM(_TAB.vkopf_kon),  BSK_K_CONST.ADDITIONNAL_COMP.TA_FIXIERT.key(), bibMSG.MV());
//			TextField comp6 = (TextField)mc.get_comp(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_FIXIERT_RESTMENGE.key(), bibMSG.MV());
//			
//			DEBUG.System_println(comp1.getText());
//			DEBUG.System_println(comp2.getText());
//			DEBUG.System_println(comp3.getText());
//			DEBUG.System_println(comp4.getText());
//			DEBUG.System_println(comp5.getText());
//			DEBUG.System_println(comp6.getText());
//			
//			
//			if (bdGesamtMengeFixierung_lief == null || bdGesamtMengeFixierung_lief.get_longValue()<=0) {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Berechnung ist nur möglich, wenn Gesamtmenge Fixierung vorhanden ist !")));
//			} else {
//				
//				if (bdFixiert == null) {
//					bdFixiert = new MyBigDecimal(0L);
//					mc.set_maskVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_GELIEFERT.key(), new MyBigDecimal(0L).get_FormatedRoundedNumber(3) , bibMSG.MV());
//				}
//				bdFixiertRestMenge = bdGesamtMengeFixierung_lief.subtract_from_me(bdFixiert.get_bdWert());
//				if (bibNUM.IS_LESS_0(bdFixiertRestMenge.get_bdWert())) {
//					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fixierungsmenge ist überschritten !")));
//				}	
//				mc.set_maskVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_FIXIERT_RESTMENGE.key(), bdFixiertRestMenge.get_FormatedRoundedNumber(3), bibMSG.MV());
//				
//				
//				if (bdGeliefert == null) {
//					bdGeliefert = new MyBigDecimal(0L);
//					mc.set_maskVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_FIXIERT.key() ,new MyBigDecimal(0L).get_FormatedRoundedNumber(3) , bibMSG.MV());
//				}
//				bdGeliefertRestMenge = bdGesamtMengeFixierung_fix.subtract_from_me(bdGeliefert);
//				if (bibNUM.IS_LESS_0(bdGeliefertRestMenge.get_bdWert())) {
//					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fixierungsmenge ist überschritten !")));
//				}			
//				
//				mc.set_maskVal(new RB_KM(_TAB.vkopf_kon), BSK_K_CONST.ADDITIONNAL_COMP.TA_GELIEFERT_RESTMENGE.key(), bdGeliefertRestMenge.get_FormatedRoundedNumber(3), bibMSG.MV());
//				
//			}
			
			
			
			
		}
		
	}
	
	
//	private class ownMaskController extends RB_MaskControllerMap {
//
//		/**
//		 * @param componentMapCollector
//		 * @throws myException
//		 */
//		public ownMaskController(RB_ComponentMapCollector componentMapCollector) throws myException {
//			super(componentMapCollector);
//		}
//
//		/**
//		 * @param p_component
//		 * @throws myException
//		 */
//		public ownMaskController(IF_RB_Component p_component) throws myException {
//			super(p_component);
//		}
//
//		@Override
//		public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)		throws myException {
//			return null;
//		}
//		
//	}
	
	@Override
	public void mark_Disabled() throws myException {
		this.set_bEnabled_For_Edit(false);
	}
}
