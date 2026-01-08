package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_PRIO;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;


public class WF_ListSelektor_3_Prioritaet extends XX_ListSelektor  implements IF_definable_all_or_relevant {

	private Vector<ownCB> v_cb = new Vector<>();
	
	public WF_ListSelektor_3_Prioritaet() throws myException {
		super();
		
		RECLIST_LAUFZETTEL_PRIO rl_prio = new RECLIST_LAUFZETTEL_PRIO(new SEL(_TAB.laufzettel_prio).FROM(_TAB.laufzettel_prio).ORDERUP(LAUFZETTEL_PRIO.prio_sort).s());
		
		for (RECORD_LAUFZETTEL_PRIO rp: rl_prio.GET_SORTED_VECTOR(bibVECTOR.get_Vector(LAUFZETTEL_PRIO.prio_sort.fn()), true)) {
			this.v_cb.add(new ownCB(rp));
		}
		
		this.set_oNeutralisator(new ownNeutralisator());
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		E2_Grid grid = new E2_Grid();
		grid._a(new RB_lab()._tr("Priorität der Aufgaben")._fsa(-2)._i(), new RB_gld()._ins(0,0,0,3)._span(5));

		for (ownCB cb: this.v_cb) {
			grid._a(cb,new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		}
		grid._setSize(100,100,100,100,100);
		
		return grid;
	}

	
	@Override
	public String get_WhereBlock() throws myException {
		
		Vector<SEL> v_sammle_aktive = new Vector<>();
		
		for (ownCB cb: this.v_cb) {
			if (cb.isSelected()) {
				v_sammle_aktive.add(cb.get_union_sub_query());              // addiert etwas wie:
																		    // SELECT JT_LAUFZETTEL_EINTRAG.id_laufzettel
				                                                            // FROM JT_laufzettel_eintrag LEFT OUTER JOIN JT_LAUFZETTEL_PRIO ON (JT_LAUFZETTEL_EINTRAG.id_laufzettel_prio=JT_LAUFZETTEL_PRIO.id_laufzettel_prio)
				                                                            // WHERE JT_LAUFZETTEL_PRIO.id_laufzettel_prio=1000
			}
		}
		
	    if (v_sammle_aktive.size()==this.v_cb.size()) {
	    	return "";             //keine auswirkung, alles ist selektiert
	    } else if (v_sammle_aktive.size()==0) {
	    	return "1=2"; 		   //kein ergebnis
	    } else {
			String c_rueck = LAUFZETTEL.id_laufzettel.tnfn()+" IN (";
			
	    	for (SEL s: v_sammle_aktive) {
	    		c_rueck = c_rueck+s.s()+" UNION ";
	    	}
	    	
	    	return c_rueck.substring(0,c_rueck.length()-7)+")";   // das letzte union weg
	    }
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		for (ownCB o: this.v_cb) {
			o.add_oActionAgent(oAgent);
		}
	}

	@Override
	public void doInternalCheck() {
	}

	
	private class ownCB extends RB_cb {
		
		private RECORD_LAUFZETTEL_PRIO  rec_prio = null;

		public ownCB(RECORD_LAUFZETTEL_PRIO p_rec_prio) throws myException {
			super();
			this.rec_prio = p_rec_prio;
			this._t(this.rec_prio.fs(LAUFZETTEL_PRIO.prio,"-"))
				._fsa(-2)              //kleine schrift
				._check();             //selektiert
		}
		
		public SEL get_union_sub_query() throws myException {
			return new SEL(LAUFZETTEL_EINTRAG.id_laufzettel).FROM(_TAB.laufzettel_eintrag)
					.LEFTOUTER(_TAB.laufzettel_prio, LAUFZETTEL_EINTRAG.id_laufzettel_prio, LAUFZETTEL_PRIO.id_laufzettel_prio)
					.WHERE(new vgl(LAUFZETTEL_PRIO.id_laufzettel_prio, this.rec_prio.ufs(LAUFZETTEL_PRIO.id_laufzettel_prio)));

		}
	}

	
	private class ownNeutralisator extends XX_ListSelektor_Neutralisator {
		@Override
		public void set_to_Neutral() throws myException {
			for (ownCB cb: WF_ListSelektor_3_Prioritaet.this.v_cb) {
				cb._check();
			}
			
		}
	}
	
	
	
	@Override
	public void select_all_data() throws myException {
		this.set_neutral_if_possible();
	}


	@Override
	public void select_relevant_data() throws myException {
		this.set_neutral_if_possible();
	}

}
