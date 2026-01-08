package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_SelectField_Zeitraum;


public class WF_ListSelektor_3_Zeitselektor extends XX_ListSelektor implements IF_definable_all_or_relevant {
	
	protected 	String[][] 	  						arrZeitraum = null;
	private 	WF_HEAD_LIST_SelectField_Zeitraum  	selZeitraum = null;
	private     RB_cb                      			cb_add_empty_dates = new RB_cb(new MyE2_String("Aufgaben ohne Fälligkeit mit anzeigen"))
																					._fsa(-2)
																					._ttt(new MyE2_String("Fügt Laufzettel zur Auswahl hinzu, die nicht abgeschlossen sind, aber kein Fälligkeitsdatum haben"))
																					;
	private     RB_cb                       		cb_add_empty_laufzettel = new RB_cb(new MyE2_String("Laufzettel ohne Aufgaben mit anzeigen"))
																					._fsa(-2)
																					._ttt(new MyE2_String("Fügt Laufzettel zur Auswahl hinzu, die überhaupt keine Aufgaben haben"))
																					;
	private     RB_cb                       		cb_add_deleted= new RB_cb(new MyE2_String("Laufzettel mit gelöschten Aufg. mit anzeigen"))
																					._fsa(-2)
																					._ttt(new MyE2_String("Fügt Laufzettel zur Auswahl hinzu, die gelöschte Aufgaben haben"));
	
	private     RB_cb                       		cb_add_closed = new RB_cb(new MyE2_String("Laufzettel mit geschloss. Aufg. mit anzeigen"))
																					._fsa(-2)
																					._ttt(new MyE2_String("Fügt Laufzettel zur Auswahl hinzu, die gelöschte Aufgaben haben"));
	
	
	public WF_ListSelektor_3_Zeitselektor() throws myException {
		super();
		
		arrZeitraum = new String[][]{
				{new MyE2_String("--").CTrans(),""},
				{new MyE2_String("1 Tag").CTrans(),"1"},
				{new MyE2_String("2 Tage").CTrans(),"2"},
				{new MyE2_String("3 Tage").CTrans(),"3"},
				{new MyE2_String("4 Tage").CTrans(),"4"},
				{new MyE2_String("5 Tage").CTrans(),"5"},
				{new MyE2_String("6 Tage").CTrans(),"6"},
				{new MyE2_String("7 Tage").CTrans(),"7"},
				{new MyE2_String("2 Wochen").CTrans(),"14"},
				{new MyE2_String("3 Wochen").CTrans(),"21"},
				{new MyE2_String("4 Wochen").CTrans(),"28"},
				{new MyE2_String("5 Wochen").CTrans(),"35"},
				{new MyE2_String("10 Wochen").CTrans(),"70"},
				{new MyE2_String("20 Wochen").CTrans(),"140"},
				{new MyE2_String("30 Wochen").CTrans(),"210"},
				{new MyE2_String("1 Jahr").CTrans(),"365"},
				{new MyE2_String("unbegrenzt").CTrans(),"9999999"},
				};
		this.selZeitraum = new WF_HEAD_LIST_SelectField_Zeitraum(arrZeitraum, null, false);
		this.selZeitraum.set_ActiveValue_OR_FirstValue("14");   //vorgabe: 2 wochen
		
		this.cb_add_empty_dates.setSelected(true);
		this.cb_add_empty_laufzettel.setSelected(true);
		
		this.selZeitraum.setWidth(new Extent(250));
		this.selZeitraum.setToolTipText(new MyE2_String("Beschränkt die Auswahl auf Laufzettel mit Aufgaben, deren Fälligkeitsdatum <= HEUTE + gewählter Zeitraum in der Zukunft ist!"
														+ " Laufzettel ohne Aufgaben werden nicht angezeigt,"
														+ " Laufzettel, deren Aufgaben alle geschlossen sind, werden nicht angezeigt").CTrans());

		this.selZeitraum.add_oActionAgent(new ownSelectionDisableCbWhenNeutral());
		
		this.set_oNeutralisator(new XX_ListSelektor_Neutralisator() {
			@Override
			public void set_to_Neutral() throws myException {
				WF_ListSelektor_3_Zeitselektor.this.selZeitraum.set_ActiveValue("");
				WF_ListSelektor_3_Zeitselektor.this.cb_add_empty_dates.setSelected(false);
				WF_ListSelektor_3_Zeitselektor.this.cb_add_empty_laufzettel.setSelected(false);
				WF_ListSelektor_3_Zeitselektor.this.cb_add_deleted.setSelected(false);
				WF_ListSelektor_3_Zeitselektor.this.cb_add_closed.setSelected(false);
			}
		});

	}

	@Override
	public String get_WhereBlock() throws myException {
		String cWert = this.selZeitraum.get_ActualWert();
		
		if (S.isEmpty(cWert)) {
			return "";                    //selektor verhaelt sich neutral
		} else {
			MyLong l = new MyLong(cWert);
			if (l.get_bOK()) {
				SEL  sel_1 = new SEL().ADDFIELD(LAUFZETTEL_EINTRAG.id_laufzettel).FROM(_TAB.laufzettel_eintrag)
																				  .AND(new TermSimple("1=1"));
				if (! this.cb_add_closed.isSelected()) {
					sel_1.AND(new TermSimple(LAUFZETTEL_EINTRAG.abgeschlossen_am.tnfn()+" IS NULL"));
				}
				if (! this.cb_add_deleted.isSelected()) {
					sel_1.AND(new vgl_YN(LAUFZETTEL_EINTRAG.geloescht, false));
				}											  
				if (l.get_lValue(0l)<=400) {
				   sel_1.AND(new TermSimple(" NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE + " + l.get_cUF_LongString() + ") <= (SYSDATE + " + l.get_cUF_LongString() + ")"));
				}
				
				SEL  sel_2 = new SEL().ADDFIELD(LAUFZETTEL_EINTRAG.id_laufzettel).FROM(_TAB.laufzettel_eintrag)
																				  .WHERE(new TermSimple(LAUFZETTEL_EINTRAG.faellig_am.tnfn()+" IS NULL"));
				if (! this.cb_add_closed.isSelected()) {
					sel_2.AND(new TermSimple(LAUFZETTEL_EINTRAG.abgeschlossen_am.tnfn()+" IS NULL"));
				}
				if (! this.cb_add_deleted.isSelected()) {
					sel_2.AND(new vgl_YN(LAUFZETTEL_EINTRAG.geloescht, false));
				}											  

//				SEL  sel_3 = new SEL().ADDFIELD(LAUFZETTEL.id_laufzettel).FROM(_TAB.laufzettel)
//																		  .WHERE(new TermSimple("((SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL=JT_LAUFZETTEL.ID_LAUFZETTEL)=0)"))
//																		  .AND(new TermSimple(LAUFZETTEL.abgeschlossen_am.tnfn()+" IS NULL"))
//																		  .AND(new vgl_YN(LAUFZETTEL.geloescht, false));
				
				SEL  sel_3 = new SEL().ADDFIELD(LAUFZETTEL.id_laufzettel).FROM(_TAB.laufzettel)
											  .WHERE(new TermSimple("((SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL=JT_LAUFZETTEL.ID_LAUFZETTEL)=0)"))
											  ;

				
				Vector<String> v_union = new Vector<>();
				v_union.add(sel_1.s());
				if (this.cb_add_empty_dates.isSelected()) {
					v_union.add(sel_2.s());
				}
				if (this.cb_add_empty_laufzettel.isSelected()) {
					v_union.add(sel_3.s());
				}
				return " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +bibALL.Concatenate(v_union, " UNION ", "1")+")";
			} else {
				throw new myException("Unexpected Selector-Error !!");
			}
		}

		
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		E2_Grid g = new E2_Grid()._setSize(200)
								._a(new RB_lab()._tr("Offene Aufgaben mit Fälligkeit ab jetzt in ...")._fsa(-2)._i(), new RB_gld()._ins(E2_INSETS.I(0,0,0,3)))
								._a(this.selZeitraum)
								._a(this.cb_add_empty_dates)
								._a(this.cb_add_empty_laufzettel)
								._a(this.cb_add_closed)
								._a(this.cb_add_deleted)
								;
		
		return g;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.selZeitraum.add_oActionAgent(oAgent);
		this.cb_add_empty_dates.add_oActionAgent(oAgent);
		this.cb_add_empty_laufzettel.add_oActionAgent(oAgent);
		this.cb_add_closed.add_oActionAgent(oAgent);
		this.cb_add_deleted.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {

		// TODO Auto-generated method stub

	}

	
	private class ownSelectionDisableCbWhenNeutral extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WF_ListSelektor_3_Zeitselektor oThis = WF_ListSelektor_3_Zeitselektor.this;
			
			if (S.isEmpty(oThis.selZeitraum.get_ActualWert())) {
				oThis.cb_add_empty_dates.setSelected(false);
				oThis.cb_add_empty_laufzettel.setSelected(false);
				oThis.cb_add_closed.setSelected(false);
				oThis.cb_add_deleted.setSelected(false);
				
				oThis.cb_add_empty_dates.set_bEnabled_For_Edit(false);
				oThis.cb_add_empty_laufzettel.set_bEnabled_For_Edit(false);
				oThis.cb_add_closed.set_bEnabled_For_Edit(false);
				oThis.cb_add_deleted.set_bEnabled_For_Edit(false);
			} else {
				oThis.cb_add_empty_dates.set_bEnabled_For_Edit(true);
				oThis.cb_add_empty_laufzettel.set_bEnabled_For_Edit(true);
				oThis.cb_add_closed.set_bEnabled_For_Edit(true);
				oThis.cb_add_deleted.set_bEnabled_For_Edit(true);

				//immer wenn eingeschaltet wird, die standard-auswahl anzeigen
				oThis.cb_add_empty_dates.setSelected(true);
				oThis.cb_add_empty_laufzettel.setSelected(true);
			}
		}
	}
	
	
	
	@Override
	public void select_all_data() throws myException {
		this.set_neutral_if_possible();
	}


	@Override
	public void select_relevant_data() throws myException {
		this.selZeitraum.set_ActiveValue("14");
		this.cb_add_empty_dates.setSelected(true);
		this.cb_add_empty_laufzettel.setSelected(true);
		this.cb_add_closed.setSelected(false);
		this.cb_add_deleted.setSelected(false);
	}

	
}
