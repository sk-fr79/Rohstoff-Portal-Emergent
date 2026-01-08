/**
 * panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES
 * @author manfred
 * @date 18.04.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.HashMap;
import java.util.Vector;

//import javafx.scene.control.Separator;
import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Bestätigen der Nachricht in der Liste
 * 
 * @author manfred
 * @date 18.04.2016
 *
 */
public class MESSAGE_LIST_BT_Bestaetigen extends MyE2_Button implements DS_IF_components4decision{

	E2_NavigationList m_navList = null;
	
	
	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();

	

	/**
	 * @author manfred
	 * @date 18.04.2016
	 * 
	 */
	public MESSAGE_LIST_BT_Bestaetigen(E2_NavigationList oNavigationList) {
		super(E2_ResourceIcon.get_RI("ok.png") , E2_ResourceIcon.get_RI("ok__.png"));
		this.m_navList = oNavigationList;
		this.setToolTipText(new MyString("Bestätigen der ausgewählten Nachrichten.").CTrans());
		
		// decision-Agent
		this.add_oActionAgent(new cYesNoDecisionAction(this));
		
		// action-Agent
		this.add_oActionAgent(new cActionAgentBestaetigen());
	}
	
	
	
	private class cActionAgentBestaetigen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_LIST_BT_Bestaetigen oThis = MESSAGE_LIST_BT_Bestaetigen.this;
			
			Vector<String> vSelectedIDs = oThis.m_navList.get_vSelectedIDs_Unformated();
			String sID = "= -1";
			
			if (vSelectedIDs.size()<= 0)	{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss mindestens eine Meldung ausgewählt sein.")));
				return;
			} else if (vSelectedIDs.size() == 1){
				sID = " = " + vSelectedIDs.get(0);
			}else {
				sID = " in ("+ bibALL.Concatenate(vSelectedIDs, ",", "-1") + ")";
			}

			
			RECLIST_NACHRICHT rl = new RECLIST_NACHRICHT ("SELECT * FROM JT_NACHRICHT WHERE ID_NACHRICHT " + sID ) ;
			Vector<String> vSql = new Vector<>();
			
			for (RECORD_NACHRICHT rec : rl){
				rec.set_NEW_VALUE_BESTAETIGT("Y");
				vSql.addElement(rec.get_SQL_UPDATE_STD());
			}
			
			MyE2_MessageVector mvRet = bibDB.ExecMultiSQLVector(vSql, true);
			if (mvRet.size() == 0){
				bibMSG.add_MESSAGE(new MyE2_Info_Message(bibALL.ReplaceTeilString(new MyString("Es wurden #wert# Nachrichten bestätigt.").CTrans(), "#wert#", Integer.toString(rl.size())) ));
			} else {
				bibMSG.add_MESSAGE(mvRet);
			}
			
			m_navList.RefreshList();
		}
		
	}
	
	
	


	
	private class cYesNoDecisionAction extends DS_ActionAgent {

		public cYesNoDecisionAction(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
			return new ownPopUp();
		}
		
		private class ownPopUp extends DS_PopupContainer4Decision {
			
			public ownPopUp() {
				super(MESSAGE_LIST_BT_Bestaetigen.this);
				
				E2_Grid4MaskSimple gs=new E2_Grid4MaskSimple();
				gs.add_ext(new MyE2_Label(new MyString("Bestätigen der ausgewählten Nachrichten?"), new E2_FontBold(2)), 2, 1, E2_INSETS.I_0_10_0_10, Alignment.ALIGN_LEFT);
				gs.add(new E2_Grid4MaskSimple().add_(this.get_bt_OK()).add_(this.get_bt_NO()).c()) ;
				this.add(gs);
				
			}

			@Override
			public int get_width_in_pixel() {	return 400;}

			@Override
			public int get_height_in_pixel() {	return 200; }

			@Override
			public MyE2_String get_titleText4PopUp() { return new MyE2_String("Sollen alle ausgewählten Nachrichten bestätigt werden?"); }
			
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}
		
	}

	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision#get_storage_vector_4_all_agents()
	 */
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return storage_vector_4_all_agents;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision#get_storage_vector_4_standard_agents()
	 */
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		// TODO Auto-generated method stub
		return storage_vector_4_standard_agents;
		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision#get_storage_vector_4_decision_agents()
	 */
	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		// TODO Auto-generated method stub
		return storage_vector_4_decision_agents;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision#get_hm_descision_containers()
	 */
	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		// TODO Auto-generated method stub
		return hm_descision_containers;
	}
	
}
