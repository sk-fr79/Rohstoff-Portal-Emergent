package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * listselektor, der jedes vorkommen jedes benutzers definiert und waehlbar macht
 * @author martin
 *
 */
public class WF_ListSelektor_3_multi_user_selektor extends XX_ListSelektor  implements IF_definable_all_or_relevant  {

	private LinkedHashMap<MyE2_String, ownSelectField> 			hm_DropDowns = new LinkedHashMap<>();
	
	private WF_ListSelektor_3_super_user_selektor   			super_selektor = null;
	
	private RB_cb   											cb_select_union_or_intersect = new RB_cb()
																										._t(new MyE2_String("Mehrfachauswahl erweitert die Anzahl der gewählten Laufzettel"))
																										._check()
																										._ttt(new MyE2_String("Legt fest, ob die Selektion "
																																+ "der Benutzerdefinition additiv oder einschränkend wirkt"))
																										._aaa(new toggleString())
																										;

	//schluesselvektoren, um die zeilen-zuordnung titel - komponente besser hinzubekommen
	private Vector<MyE2_String>   v_keys = new Vector<>();
	
	
	public WF_ListSelektor_3_multi_user_selektor(WF_ListSelektor_3_super_user_selektor   p_super_selektor) throws myException {
		super();
		
		this.super_selektor = p_super_selektor;
		
		
		this.set_oNeutralisator(new ownNeutron());
		String[][] array_users = new WF_touched_users_dd_array(true).get_users_that_are_touching() ;
//		String[][] array_users_all = new WF_touched_users_dd_array(false).get_users_that_are_touching() ;
		
		
		hm_DropDowns.put(LAUFZETTEL_USER_ROLE.OWNER_LAUFZETTEL.get_beschriftung(), 		new ownSelectField(LAUFZETTEL_USER_ROLE.OWNER_LAUFZETTEL,array_users));
		hm_DropDowns.put(LAUFZETTEL_USER_ROLE.SUPERVISOR_LAUFZETTEL.get_beschriftung(), new ownSelectField(LAUFZETTEL_USER_ROLE.SUPERVISOR_LAUFZETTEL,array_users));
		hm_DropDowns.put(LAUFZETTEL_USER_ROLE.CLOSER_LAUFZETTEL.get_beschriftung(), 	new ownSelectField(LAUFZETTEL_USER_ROLE.CLOSER_LAUFZETTEL,array_users));
		hm_DropDowns.put(LAUFZETTEL_USER_ROLE.OWNER_TASK.get_beschriftung(), 			new ownSelectField(LAUFZETTEL_USER_ROLE.OWNER_TASK,array_users));
		hm_DropDowns.put(LAUFZETTEL_USER_ROLE.REFEREE_TASK.get_beschriftung(), 			new ownSelectField(LAUFZETTEL_USER_ROLE.REFEREE_TASK,array_users));
		hm_DropDowns.put(LAUFZETTEL_USER_ROLE.CLOSER_TASK.get_beschriftung(),	 		new ownSelectField(LAUFZETTEL_USER_ROLE.CLOSER_TASK,array_users));
		
		v_keys.add(LAUFZETTEL_USER_ROLE.OWNER_LAUFZETTEL.get_beschriftung());
		v_keys.add(LAUFZETTEL_USER_ROLE.SUPERVISOR_LAUFZETTEL.get_beschriftung());
		v_keys.add(LAUFZETTEL_USER_ROLE.CLOSER_LAUFZETTEL.get_beschriftung());
		
		v_keys.add(LAUFZETTEL_USER_ROLE.OWNER_TASK.get_beschriftung());
		v_keys.add(LAUFZETTEL_USER_ROLE.REFEREE_TASK.get_beschriftung());
		v_keys.add(LAUFZETTEL_USER_ROLE.CLOSER_TASK.get_beschriftung());

	}

	@Override
	public String get_WhereBlock() throws myException {
		//zuerst sammeln der "benutzten Selektoren"
		
		Vector<String> v_intersect_querys= new Vector<>();
		
		for (ownSelectField selField: this.hm_DropDowns.values()) {
			if (S.isFull(selField.get_ActualWert())) {
				v_intersect_querys.add(selField.get_user_role().get_query_id_laufzettel_with_user(selField.get_ActualWert()).s());
			}
		}

		String c_where = "";
		
		String mengenoperator = this.cb_select_union_or_intersect.isSelected()?" UNION ":" INTERSECT ";
		
		if (v_intersect_querys.size()==0) {
			c_where = "";
		} else if (v_intersect_querys.size()==1) {
			c_where = "("+LAUFZETTEL.id_laufzettel.tnfn()+" IN ("+v_intersect_querys.get(0)+")"+")";
		} else {
			c_where =  "("+LAUFZETTEL.id_laufzettel.tnfn()+" IN ("+bibALL.Concatenate(v_intersect_querys, mengenoperator, "0")+"))";
		}
		
		return c_where;
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		RB_gld glT = new RB_gld()._ins(E2_INSETS.I(1,1,5,2))._col(new E2_ColorDark());
		RB_gld gl = new RB_gld()._ins(E2_INSETS.I(1,1,1,1));
		E2_Grid g = new E2_Grid();

		
		g	._a(new RB_lab()._tr("Benutzer genauer definieren")._fsa(-2)._i(), new RB_gld()._ins(2)._span(6))
			._a(this.cb_select_union_or_intersect, 						new RB_gld()._span(6)._ins(2,2,2,4))
			._a(new RB_lab(this.v_keys.get(0))._i()._fsa(-2),  	glT)
			._a(new RB_lab(this.v_keys.get(1))._i()._fsa(-2),  	glT)
			._a(new RB_lab(this.v_keys.get(2))._i()._fsa(-2),  	glT)
			._a(new RB_lab(this.v_keys.get(3))._i()._fsa(-2),  	glT)
			._a(new RB_lab(this.v_keys.get(4))._i()._fsa(-2),  	glT)
			._a(new RB_lab(this.v_keys.get(5))._i()._fsa(-2),  	glT)
			._a(this.hm_DropDowns.get(this.v_keys.get(0)),  		gl)
			._a(this.hm_DropDowns.get(this.v_keys.get(1)),  		gl)
			._a(this.hm_DropDowns.get(this.v_keys.get(2)),  		gl)
			._a(this.hm_DropDowns.get(this.v_keys.get(3)),  		gl)
		 	._a(this.hm_DropDowns.get(this.v_keys.get(4)),  		gl)
		 	._a(this.hm_DropDowns.get(this.v_keys.get(5)),  		gl)
		 	._setSize(120,120,120,120,120,120);
		 	
		return g;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		WF_ListSelektor_3_multi_user_selektor oThis = WF_ListSelektor_3_multi_user_selektor.this;
		
		for (ownSelectField selField: oThis.hm_DropDowns.values()) {
			selField.add_oActionAgent(oAgent);
		}
		
		this.cb_select_union_or_intersect._aaa(oAgent);
	}

	@Override
	public void doInternalCheck() {
	}

	
	
	private class ownNeutron extends XX_ListSelektor_Neutralisator {
		@Override
		public void set_to_Neutral() throws myException {
			WF_ListSelektor_3_multi_user_selektor oThis = WF_ListSelektor_3_multi_user_selektor.this;
			
			for (ownSelectField selField: oThis.hm_DropDowns.values()) {
				selField.set_ActiveValue_OR_FirstValue("");
			}
		}
	}
	
	
	/*
	 * erweitertes selectfield mit "home"-button, der den eingeloggten benutzer auswaehlt
	 */
	private class ownSelectField extends E2_Grid {
		private LAUFZETTEL_USER_ROLE  	role = 		null;
		private MyE2_SelectField  		selfield = 	new MyE2_SelectField();
		private MyE2_Button      		bt_me = 	new MyE2_Button(E2_ResourceIcon.get_RI("user-mini.png"));  //button stellt die selektoren auf den eingeloggten user ein
		
		public ownSelectField(LAUFZETTEL_USER_ROLE p_role, String[][] arrayUserPresent) throws myException {
			super();
			this._a(selfield)._a(bt_me)._setSize(120,16);
			this.role = p_role;
			this.selfield.set_ListenInhalt(arrayUserPresent, false);
			this.bt_me.add_oActionAgent(new ownActionSelectMe());
			this.bt_me._ttt(new MyE2_String("Den gewählten Hauptbenutzer ein- und ausschalten"));
			this.selfield.setFont(new E2_FontPlain(-2));
			this.selfield.setWidth(new Extent(120));
		}
		
		public LAUFZETTEL_USER_ROLE get_user_role() {
			return this.role;
		}
		
		public void set_ActiveValue_OR_FirstValue(String id_uf) throws myException {
			this.selfield.set_ActiveValue_OR_FirstValue(id_uf);
		}
		
		public void add_oActionAgent(XX_ActionAgent agent) {
			this.selfield.add_oActionAgent(agent);
			this.bt_me.add_oActionAgent(agent);
		}
		
		public String get_ActualWert() throws myException {
			return this.selfield.get_ActualWert();
		}
		
		private class ownActionSelectMe extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				//der getoggelte user ist der user aus dem superselektor, wenn der leer ist, dann der eingeloggte user
				String id_targetuser = bibALL.get_ID_USER();
				if (S.isFull(WF_ListSelektor_3_multi_user_selektor.this.super_selektor.get_ActualWert())) {
					id_targetuser = WF_ListSelektor_3_multi_user_selektor.this.super_selektor.get_ActualWert();
				}
				
				if (S.isEmpty(ownSelectField.this.selfield.get_ActualWert())) {
					ownSelectField.this.selfield.set_ActiveValue_OR_FirstValue(id_targetuser);
				} else {
					ownSelectField.this.selfield.set_ActiveValue_OR_FirstValue("");
				}
			}
		}
	}
	

	private class toggleString extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (WF_ListSelektor_3_multi_user_selektor.this.cb_select_union_or_intersect.isSelected()) {
				WF_ListSelektor_3_multi_user_selektor.this.cb_select_union_or_intersect._t(new MyE2_String("Mehrfachauswahl erweitert die Anzahl der gewählten Laufzettel"));
			} else {
				WF_ListSelektor_3_multi_user_selektor.this.cb_select_union_or_intersect._t(new MyE2_String("Mehrfachauswahl reduziert die Anzahl der gewählten Laufzettel"));
			}
		}
	}


	
	public RB_cb get_cb_select_union_or_intersect() {
		return cb_select_union_or_intersect;
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
