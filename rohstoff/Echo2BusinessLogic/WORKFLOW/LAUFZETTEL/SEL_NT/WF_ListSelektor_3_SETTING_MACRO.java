package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_USER_EXT;

/**
 * pseudoselektor, wird zur einstellung von standard-selektionen benutzt
 * @author martin
 *
 */
public class WF_ListSelektor_3_SETTING_MACRO extends XX_ListSelektor {

	public enum SELTYP {
		ALL,
		RELEVANT
	}
	
	private SELTYP 							seltyp = null;
	private E2_SelectionComponentsVector   	selVector =  null;

	private MyE2_Button 					bt_set = null;
	
	
	public WF_ListSelektor_3_SETTING_MACRO(SELTYP p_seltyp, E2_SelectionComponentsVector p_selVector, MyE2_SelectField selFieldUser) throws myException {
		super();
		this.seltyp = 		p_seltyp;
		this.selVector = 	p_selVector;
		this.bt_set = 		this.seltyp==SELTYP.ALL ? new MyE2_Button(new MyE2_String("Alle Laufzettel selektieren")): new MyE2_Button(new MyE2_String("Alle RELEVANTEN Laufzettel selektieren"));
		this.bt_set.add_oActionAgent(new ownAction());
		this.bt_set._lw()._fsa(-2)._ttt(new MyE2_String(this.seltyp==SELTYP.ALL?"Stellt die Selektoren so ein, daﬂ ALLE Laufzettel der Benutzerauswahl erscheinen":"Stellt die Selektoren so ein, daﬂ RELEVANTE Laufzettel der Benutzerauswahl erscheinen"));
		
		this.set_personalisation(selFieldUser.get_ActualWert());
	}

	@Override
	public String get_WhereBlock() throws myException {
		return "";
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.bt_set;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.bt_set;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.bt_set.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {
	}

	
	public MyE2_Button get_bt_set() {
		return bt_set;
	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WF_ListSelektor_3_SETTING_MACRO oThis = WF_ListSelektor_3_SETTING_MACRO.this;
			
			for (XX_ListSelektor ls: oThis.selVector) {
				if (ls instanceof IF_definable_all_or_relevant) {
					if (oThis.seltyp==SELTYP.ALL) {
						((IF_definable_all_or_relevant)ls).select_all_data();
					} else {
						((IF_definable_all_or_relevant)ls).select_relevant_data();
					}
				}
			}
		}
	}
	
	public void set_personalisation(String id_user_uf) {
		if (S.isFull(id_user_uf)) {
			try {
				RECORD_USER_EXT rec_user = new RECORD_USER_EXT(new RECORD_USER(id_user_uf));
				if (this.seltyp==SELTYP.ALL) {
					this.bt_set._t(new MyE2_String("ALLE Laufzettel von ",true,rec_user.get__vorname_name1_name2(),false," selektieren",true));
				} else {
					this.bt_set._t(new MyE2_String("RELEVANTE Laufzettel von ",true,rec_user.get__vorname_name1_name2(),false," selektieren",true));
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		} else {
			if (this.seltyp==SELTYP.ALL) {
				this.bt_set._t(new MyE2_String("ALLE Laufzettel selektieren",true));
			} else {
				this.bt_set._t(new MyE2_String("RELEVANTE Laufzettel selektieren",true));
			}
		}
	}
	
}
