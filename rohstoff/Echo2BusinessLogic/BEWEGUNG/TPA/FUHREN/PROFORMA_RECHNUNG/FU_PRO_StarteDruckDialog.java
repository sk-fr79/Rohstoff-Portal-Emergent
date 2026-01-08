package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_CheckKreditVersicherung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_DecisionHat_EU_VERTRAG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_Decision_BorderCrossing;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_Decision_DatumsDifferenz;

public class FU_PRO_StarteDruckDialog extends MyE2_Button  implements DS_IF_components4decision {
	
	private E2_NavigationList  oNaviList = null;

	public FU_PRO_StarteDruckDialog(E2_NavigationList NaviList) throws myException {
		super(E2_ResourceIcon.get_RI("printer_proforma.png"),true);
		this.oNaviList = NaviList;
		this.setToolTipText(new MyE2_String("Erzeugt für jede vorhandene Abladestation eine Proforma-Rechnung").CTrans());
		
		this.add_GlobalValidator(new ownValidatior());
		
//		//2014-11-14: proforma-rechnung auch ueber den kreditlimit-validierer fuehren
//		this.add_GlobalValidator(new VALIDATOR_KreditStatus() {
//			
//			@Override
//			public Vector<String> get_Actual_ID_VPOS_TPA_FUHRE_To_Print() throws myException {
//				return FU_PRO_StarteDruckDialog.this.oNaviList.get_vSelectedIDs_Unformated();
//			}
//		});

		//2016-01-29: pruefung der datumsbereiche
		this.add_oActionAgent(new ____DA_Decision_DatumsDifferenz(this,new sammler_ID_VPOS_TPA_FUHRE()));
		
		//2016-02-23: und die neue EU-Vertrag-Pruefung
		this.add_oActionAgent(new ____DA_DecisionHat_EU_VERTRAG(this,new sammler_ID_VPOS_TPA_FUHRE()));

		
		//2016-03-07: decision fuer kreditpruefung
		this.add_oActionAgent(new own_Decision_CheckKredit(this));
		
		//2016-04-06: weitere desicion: bordercrossing
		this.add_oActionAgent(new ____DA_Decision_BorderCrossing(this,new sammler_ID_VPOS_TPA_FUHRE()));

		
		this.add_oActionAgent(new FU_PRO_ActionAgent_StartPrintMail(this.oNaviList));
		
	}
	 
	
	
	private class own_Decision_CheckKredit extends DA_Decision_CheckKreditVersicherung {
		public own_Decision_CheckKredit(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}
		
		@Override
		public Vector<String> get_v_ids_fuhren_2_check_kredit() throws myException {
			Vector<String> vRueck = new Vector<String>();
			vRueck.addAll(FU_PRO_StarteDruckDialog.this.oNaviList.get_vSelectedIDs_Unformated());
			return vRueck;
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}

	}

	
	private class ownValidatior extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
//			return new FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS(FU_PRO_StarteDruckDialog.this.oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()).get_oMV();
			return new FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS_NG(FU_PRO_StarteDruckDialog.this.oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()).get_oMV();

		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}
	
	
	
	//2016-01-29: descision-pruefungen
	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return this.storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return this.storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return this.storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return this.hm_descision_containers;
	}

	
	//sammelklasse fuer das sammeln von id_vpos_tpa_fuhre-werten (fuer drucke)
	private class sammler_ID_VPOS_TPA_FUHRE extends ___Sammler_ID_VPOS_TPA_FUHRE {

		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException {
			Vector<String>  v_fuhren = new Vector<>();
			v_fuhren.addAll(FU_PRO_StarteDruckDialog.this.oNaviList.get_vSelectedIDs_Unformated());
			return v_fuhren;
		}

		@Override
		public void rebuild_Navilist_if_Needed() throws myException {
		}
	}


	
}
