package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__Indirect;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class WF_HEAD__NaviList extends E2_NavigationList {

	private MyE2_SelectField    sel_hauptuser_from_selektor = null;
	
	public WF_HEAD__NaviList() throws myException {
		super();
		
		this.add_actionAfterListCompleted(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for (E2_ComponentMAP map: WF_HEAD__NaviList.this.get_vComponentMAPS()) {
					WF_HEAD__NaviList.this.mark_user_Components(map);
				}
			}
		});

	}

	
	
	
	public void mark_user_Components(E2_ComponentMAP oMAP) throws myException {
		
		HashMap<String, MyE2IF__Component> hm = oMAP.get_hmRealComponents();
		
		Component  o_user_besitzer = (Component)hm.get(LAUFZETTEL.id_user_besitzer.fn());
		Component  o_user_supervisor = (Component)hm.get(LAUFZETTEL.id_user_supervisor.fn());
		Component  o_user_abgeschlossen_von = (Component)hm.get(LAUFZETTEL.id_user_abgeschlossen_von.fn());

		String id_user_besitzer = 			S.NN(oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL.id_user_besitzer.fn()));
		String id_user_supervisor = 		S.NN(oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL.id_user_supervisor.fn()));
		String id_user_abgeschlossen_von =	S.NN(oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL.id_user_abgeschlossen_von.fn()));
		
		String id_user =this.sel_hauptuser_from_selektor!=null?this.sel_hauptuser_from_selektor.get_ActualWert():"";
		if (S.isEmpty(id_user)) {
			id_user = bibALL.get_ID_USER();
		}
		
		RECORD_USER rec_super_sel_user = 	new RECORD_USER(id_user);
		Color   	col_highlight = 		WF_HEAD_CONST.HIGHLIGHT_COLOR_4_USER_FIELDS;
		
		if (rec_super_sel_user != null) {
			
			boolean b_id_user_besitzer_is_supersel_user = 			rec_super_sel_user.get_ID_USER_cUF().equals(id_user_besitzer);
			boolean b_id_user_supervisor_is_supersel_user = 		rec_super_sel_user.get_ID_USER_cUF().equals(id_user_supervisor);
			boolean b_id_user_abgeschlossen_von_is_supersel_user = 	rec_super_sel_user.get_ID_USER_cUF().equals(id_user_abgeschlossen_von);
			
			
			if (o_user_besitzer!=null && b_id_user_besitzer_is_supersel_user) {
				Component c_rendered = o_user_besitzer;
				if (o_user_besitzer instanceof MyE2IF__Indirect) {
					c_rendered = ((MyE2IF__Indirect)o_user_besitzer).get_RenderedComponent();
				} 
				LayoutDataFactory.change_gridLayoutData( c_rendered, col_highlight);
			}
			
			if (o_user_supervisor!=null && b_id_user_supervisor_is_supersel_user) {
				Component c_rendered = o_user_supervisor;
				if (o_user_supervisor instanceof MyE2IF__Indirect) {
					c_rendered = ((MyE2IF__Indirect)o_user_supervisor).get_RenderedComponent();
				} 
				LayoutDataFactory.change_gridLayoutData( c_rendered, col_highlight);
			}
			
			if (o_user_abgeschlossen_von!=null && b_id_user_abgeschlossen_von_is_supersel_user) {
				Component c_rendered = o_user_abgeschlossen_von;
				if (o_user_abgeschlossen_von instanceof MyE2IF__Indirect) {
					c_rendered = ((MyE2IF__Indirect)o_user_abgeschlossen_von).get_RenderedComponent();
				} 
				LayoutDataFactory.change_gridLayoutData( c_rendered, col_highlight);
			}
		}
	}


	
	public void set_sel_hauptuser_from_selektor(MyE2_SelectField p_sel_hauptuser_from_selektor) {
		this.sel_hauptuser_from_selektor = p_sel_hauptuser_from_selektor;
	}

	
}
