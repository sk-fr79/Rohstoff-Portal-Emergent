package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_delete;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BGL_PROFIL_MASK_bt_delete extends RB_bt_delete {

	private BGL_LIST_popup_BelegGrenzUbertritt popUp;

	public BGL_PROFIL_MASK_bt_delete(BGL_LIST_popup_BelegGrenzUbertritt p_parent) throws myException {
		super();
		this.popUp = p_parent;
		this._ttt("Gewähltes Profil löschen");
	}

	@Override
	public Vector<String> get_ids_to_delete() throws myException {
		Vector<String> vId = new Vector<String> ();
		vId.add(popUp.get_selected_profile());
		return vId;
	}

	@Override
	public Vector<String> get_delete_sql_statements(String id_to_delete) throws myException {
		VEK<String> v_ret = new VEK<>();
		Rec21 rec = new Rec21(_TAB.profil_grenzubertritt)._fill_id(id_to_delete);
		v_ret.add(rec.get_sql_2_delete());
		return v_ret;
	}

	@Override
	public Vector<XX_ActionAgent> get_action_after_delete() throws myException {
		Vector<XX_ActionAgent> vAction = new Vector<XX_ActionAgent>();
		vAction.add(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				popUp.reload_profile_list();				
			}
		});
		return vAction;
	}

	@Override
	public String get_message_text_mindestens_eine_irgendwas_markieren() {
		return "Bitte markieren Sie mindestens EINEN Profil zu löschen !";
	}

	@Override
	public String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
		return "Achtung! Es wird ein Profil gelöscht!";
	}

	@Override
	public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
		return "Achtung! Es werden 1 Profil gelöscht!";
	}

}
