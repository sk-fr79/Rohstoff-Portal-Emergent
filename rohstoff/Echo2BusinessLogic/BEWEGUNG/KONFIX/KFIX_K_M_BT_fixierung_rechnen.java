package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_BT_fixierung_rechnen extends E2_Button {

	private KFIX_K_M_ComponentMap compMap = null;
	
	public KFIX_K_M_BT_fixierung_rechnen(KFIX_K_M_ComponentMap p_compMap, boolean is_4_fixierungs_restmenge) {
		super();
		
		this._image(E2_ResourceIcon.get_RI("calc.png"));
		
		this.add_GlobalValidator(new ownValidator());
		
		this.compMap = p_compMap;
		
		if(is_4_fixierungs_restmenge){
			this._aaa(new aa_rechnen_fixierung_menge());
		}else{
			this._aaa(new aa_rechnen_gelieferte_menge());
		}
	}
	
	private class ownValidator extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			MyBigDecimal bdGesamtMengeFixierung_lief  	= new MyBigDecimal(compMap._find_component_in_neighborhood(VKOPF_KON.fix_menge_gesamt).get__actual_maskstring_in_view());

			if (bdGesamtMengeFixierung_lief == null || bdGesamtMengeFixierung_lief.get_longValue()<=0) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Berechnung ist nur möglich, wenn eine Fixierungsmenge vorhanden ist !")));
			}
			return mv;
		}
		
	}
	
	
	private class aa_rechnen_fixierung_menge extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_K_M_ComponentMap oThis = KFIX_K_M_BT_fixierung_rechnen.this.compMap;
		
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			MyBigDecimal bdGesamtMengeFixierung_lief  	= new MyBigDecimal(compMap._find_component_in_neighborhood(VKOPF_KON.fix_menge_gesamt).get__actual_maskstring_in_view());
			MyBigDecimal bdFixiert 						= new MyBigDecimal(compMap._find_component_in_neighborhood(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT.key()).get__actual_maskstring_in_view());
			MyBigDecimal bdFixiert_restMge				= new MyBigDecimal(compMap._find_component_in_neighborhood(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key()).get__actual_maskstring_in_view());
			
			bdFixiert_restMge = bdGesamtMengeFixierung_lief.subtract_from_me(bdFixiert.get_bdWert());
			if (bibNUM.IS_LESS_0(bdFixiert_restMge.get_bdWert())) {
				mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fixierungsmenge ist überschritten !")));
			}	
			oThis._find_component_in_neighborhood(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key()).rb_set_db_value_manual(bdFixiert_restMge.get_FormatedRoundedNumber(3));
		}
		
	}
	
	private class aa_rechnen_gelieferte_menge extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_K_M_ComponentMap oThis = KFIX_K_M_BT_fixierung_rechnen.this.compMap;
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			MyBigDecimal bdGesamtMengeFixierung_fix = 	new MyBigDecimal(compMap._find_component_in_neighborhood(VKOPF_KON.fix_menge_gesamt).get__actual_maskstring_in_view());
			MyBigDecimal bdGeliefert 		= 			new MyBigDecimal(compMap._find_component_in_neighborhood(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT.key()).get__actual_maskstring_in_view());
			MyBigDecimal bdGeliefertRestMenge = 		new MyBigDecimal(compMap._find_component_in_neighborhood(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()).get__actual_maskstring_in_view());
	
			bdGeliefertRestMenge = bdGesamtMengeFixierung_fix.subtract_from_me(bdGeliefert);
			if (bibNUM.IS_LESS_0(bdGeliefertRestMenge.get_bdWert())) {
				mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fixierungsmenge ist überschritten !")));
			}			
			
			oThis._find_component_in_neighborhood(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()).rb_set_db_value_manual(bdGeliefertRestMenge.get_FormatedRoundedNumber(3));
			
			if(bdGeliefertRestMenge.get_longValue()<0){
				((RB_TextAnzeige)oThis.getRbComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()))._col_back_alarm();
			}else{
				((RB_TextAnzeige)oThis.getRbComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()))._col_back(Color.GREEN);
			}
		
		}
		
	}
	
}
