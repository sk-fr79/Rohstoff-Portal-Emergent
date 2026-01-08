package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.BETA.RB_searchAVVCode;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_AVV;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_AVV_Search extends RB_searchAVVCode{

	
	private KFIX_P_M_masklist_AVV_code_list parent_list;

	private MyLong l_id_vpos_kon_avv = null;
	
	public KFIX_P_M_AVV_Search(KFIX_P_M_masklist_AVV_code_list p_parent_list) throws myException {
		super();

		this.parent_list = p_parent_list;

		this._clear()._setSize(300,100)._a(this.getTfForAnzeige(), new RB_gld()._left_mid()._ins(0, 2, 5, 2))
		._a(this.getTfDatenFeldWithID(), new RB_gld()._left_mid()._ins(0, 2, 5, 2));
	
		this.getvActionsAfterFound().add(new action_after_search());
		
	}

	public MyLong get_id_vpos_kon_avv() {
		return l_id_vpos_kon_avv;
	}

	public void set_id_vpos_kon_avv(MyLong l_id_vpos_kon_avv) {
		this.l_id_vpos_kon_avv = l_id_vpos_kon_avv;
	}

	/**
	 * add of a new search field in the list
	 * @author sebastien
	 *
	 */
	private class action_after_search extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_P_M_AVV_Search oThis = KFIX_P_M_AVV_Search.this;
			
			MyE2_MessageVector mv =  new MyE2_MessageVector();
			
			Rec20 record_vpos_avv =  new Rec20(_TAB.vpos_kon_avv);		
			
			if(oThis.l_id_vpos_kon_avv!= null && oThis.l_id_vpos_kon_avv.get_bOK()){
				record_vpos_avv._fill_id(l_id_vpos_kon_avv.get_lValue());
			}
			
			String id_vpos_kon = oThis.parent_list.get_id_vpos_kon().get_cF_LongString();
			
			record_vpos_avv._nv(VPOS_KON_AVV.id_vpos_kon, id_vpos_kon 													,mv);
			record_vpos_avv._nv(VPOS_KON_AVV.id_eak_code, oThis.getTfDatenFeldWithID().get__actual_maskstring_in_view()	,mv);
			
			if(mv.get_bIsOK()){
				record_vpos_avv._SAVE(true, mv);
				oThis.parent_list.add_new_line(oThis);
				oThis.parent_list.rb_set_db_value_manual(id_vpos_kon);
			}else{
				bibMSG.add_MESSAGE(mv.get_AlarmMessages());
			}
		}
	}
	
	
}
