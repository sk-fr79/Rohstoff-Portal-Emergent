package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_delete;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_LOG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.DB_META_TRIGGER_HANDLER;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class AT_LIST_bt_Delete extends RB_bt_delete {
   
    private E2_NavigationList  naviList = null;
    
    public AT_LIST_bt_Delete(E2_NavigationList  p_naviList) throws myException {
        super();
        this.naviList=p_naviList;
        this._image(E2_ResourceIcon.get_RI("delete_mini.png"));
        this.setToolTipText(new MyE2_String("Die ausgewählten Trigger löschen").CTrans());
        this._v(new E2_ButtonAUTHValidator_AUTO(AT_CONST.TRI_BUTTONS.DELETE.db_val()));
    }

	@Override
	public Vector<String> get_ids_to_delete() throws myException {
		return this.naviList.get_vSelectedIDs_Unformated();
	}


	@Override
	public Vector<XX_ActionAgent> get_action_after_delete() throws myException {
		XX_ActionAgent agentRefresh = new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				AT_LIST_bt_Delete.this.naviList._REBUILD_ACTUAL_SITE(null);
			}
		};
		
		return new VEK<XX_ActionAgent>()._a(agentRefresh);
	}

	@Override
	public String get_message_text_mindestens_eine_irgendwas_markieren() {
		return "Bitte markieren Sie mindestens EINEN Trigger zun löschen !";
	}

	@Override
	public String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
		return "Achtung! Es wird ein Trigger gelöscht!";
	}

	@Override
	public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
		return "Achtung! Es werden #WERT# Trigger gelöscht!";
	}

	@Override
	public Vector<String> get_delete_sql_statements(String id_to_delete) throws myException {
		VEK<String> v_ret = new VEK<>();
		Rec20 rec = new Rec20(_TAB.trigger_action_def)._fill_id(id_to_delete);
		
		//zum loeschen ist der triggerinhalt egal, d.h. welchen der handler man nimmt 
		DB_META_TRIGGER_HANDLER trh = new AT_TriggerHandlerAllgemein(rec)._init();

		if (trh.get_actual_status()!=DB_META_TRIGGER_HANDLER.STATUS_TRIGGER.MISSING) {
			v_ret._a(trh.get_delStatement());
		}
		
		RecList20 rl_log = rec.get_down_reclist20(TRIGGER_ACTION_LOG.id_trigger_action_def, null, null);
		for (Rec20 rlog: rl_log) {
			v_ret._a(rlog.get_sql_2_delete());
		}
		
		v_ret._a(rec.get_sql_2_delete());
		
		return v_ret;
	}
    
    
     
}
 
