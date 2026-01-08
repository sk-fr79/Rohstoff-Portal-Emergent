package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_EXT;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_Selection_User_DropDown;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_USER_EXT;


public class WF_ListSelektor_3_super_user_selektor extends XX_ListSelektor_EXT {

	private WF_HEAD_LIST_Selection_User_DropDown  	sel_field = null;
	private MyE2_Button      						bt_me = 	new MyE2_Button(E2_ResourceIcon.get_RI("user-mini.png"));  //button stellt die selektoren auf den eingeloggten user ein

	
	public WF_ListSelektor_3_super_user_selektor() throws myException {
		super();
		
		this.sel_field = new WF_HEAD_LIST_Selection_User_DropDown(false, 170);
		this.sel_field.set_ActiveValue_OR_FirstValue(bibALL.get_RECORD_USER().ufs(USER.id_user));
		
		this.bt_me.add_oActionAgent(new ownActionSelectMe());
		RECORD_USER_EXT user_e = new RECORD_USER_EXT(bibALL.get_RECORD_USER());
		this.bt_me._ttt(new MyE2_String("Den Benutzer <",true,user_e.get__vorname_name1_name2(),false,"> ein- und ausschalten",true));

		this.sel_field.setToolTipText(new MyE2_String("Angezeigt werden alle Laufzettel, bei denen der gewählte Benutzer entweder Besitzer,Supervisor oder Abschliessender ist oder die eine Aufgabe mit den gleichen Merkmalen enthalten").CTrans());
		
	}

	@Override
	public String get_WhereBlock() throws myException {
		if (S.isEmpty(this.sel_field.get_ActualWert())) {
			return "";
		}
		
		Vector<String> v_where_blocks = new Vector<>();
		
		for (LAUFZETTEL_USER_ROLE rol: LAUFZETTEL_USER_ROLE.values()) {
			v_where_blocks.add(rol.get_query_id_laufzettel_with_user(this.sel_field.get_ActualWert()).s());;
		}
		
		StringBuffer  c_rueck = new StringBuffer();
		for (String c_where: v_where_blocks) {
			c_rueck.append(c_where+" UNION ");
		}
		
		String sql = LAUFZETTEL.id_laufzettel.tnfn()+" IN ("+c_rueck.toString().substring(0,c_rueck.toString().length()-7)+")";
		
		return sql;
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return new E2_Grid()
					._a(new RB_lab()._tr("Laufzettel mit Beteiligung von ")._fsa(-2)._i(), new RB_gld()._ins(2)._span(2))
					._a(this.sel_field)
					._a(this.bt_me)
					._setSize(170,20);
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.sel_field.add_oActionAgent(oAgent);
		this.bt_me.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {
	}

	
	private class ownActionSelectMe extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (S.isEmpty(WF_ListSelektor_3_super_user_selektor.this.sel_field.get_ActualWert())) {
				WF_ListSelektor_3_super_user_selektor.this.sel_field.set_ActiveValue_OR_FirstValue(bibALL.get_ID_USER());
			} else {
				WF_ListSelektor_3_super_user_selektor.this.sel_field.set_ActiveValue_OR_FirstValue("");
			}
		}
		
	}

	
	public String get_ActualWert() throws myException {
		return S.NN(this.sel_field.get_ActualWert());
	}
	
	
	public MyE2_SelectField  get_selField() {
		return this.sel_field;
	}

	
	public MyE2_Button get_bt_me() {
	
		return bt_me;
	}
	
}
