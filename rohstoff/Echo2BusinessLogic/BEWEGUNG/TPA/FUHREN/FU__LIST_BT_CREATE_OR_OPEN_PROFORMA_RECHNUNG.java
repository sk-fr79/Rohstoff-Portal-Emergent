package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_CheckKreditVersicherung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG.FU_PRO_ActionAgent_StartPrintMail;

public class FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG extends MyE2_ButtonInLIST implements DS_IF_components4decision{


	public FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG() throws myException {
		super(new MyE2_String("Proforma"),MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Center(new E2_FontItalic(-2)));
		this.setToolTipText(new MyE2_String("Erzeugen oder Aufrufen einer Proformarechnung").CTrans());
		
	
		//2016-03-07: kreditstatus-Check
		this.add_oActionAgent(new own_Decision_CheckKredit(this));
		
		this.add_oActionAgent(new FU_PRO_ActionAgent_StartPrintMail(null));
		this.setLineWrap(true);
		
//		//2014-11-14: proforma-rechnung auch ueber den kreditlimit-validierer fuehren
//		this.add_GlobalValidator(new VALIDATOR_KreditStatus() {
//			
//			@Override
//			public Vector<String> get_Actual_ID_VPOS_TPA_FUHRE_To_Print() throws myException {
//				E2_ComponentMAP  oMap = FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG.this.EXT().get_oComponentMAP();
//				Vector<String> vRueck = new Vector<String>();
//				vRueck.add(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
//				return vRueck;
////				return FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG.this.naviList.get_vSelectedIDs_Unformated();
//			}
//		});
		
	}
	
	
	
	private class own_Decision_CheckKredit extends DA_Decision_CheckKreditVersicherung {
		public own_Decision_CheckKredit(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}
		
		@Override
		public Vector<String> get_v_ids_fuhren_2_check_kredit() throws myException {
			E2_ComponentMAP  oMap = FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG.this.EXT().get_oComponentMAP();
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			return vRueck;
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}

	}
	

	
	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG oRueck = null;
		
		try {
			oRueck = new FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG();
			oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		} catch (myException e) {
			throw new myExceptionCopy("Error Copy FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG");
		}
		
		return oRueck;
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

		
	
}
