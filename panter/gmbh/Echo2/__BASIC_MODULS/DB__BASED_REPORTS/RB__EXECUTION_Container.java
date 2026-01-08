/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS
 * @author martin
 * @date 30.10.2018
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import java.util.HashMap;

import echopointng.Separator;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 30.10.2018
 *
 */
public class RB__EXECUTION_Container extends E2_BasicModuleContainer {

	private Rec21 		record = null;
	private RecList21 	rlParam = null;
	
	private E2_Grid     gridContent = new E2_Grid(); 
	
	private E2_Button   bt_start = 		(E2_Button) new E2_Button()._tr("Abfrage starten")._standard_text_button()
																._aaa(()->{bibMSG.MV()._add(validateInput());})
																._aaa(()->{fillControllLabels();})
																._aaa(()->{bibMSG.MV()._add(createIfNeededAndFillTempTable());})
																._aaa(new ActionAgentStartReport())
																; 
	private E2_Button   bt_test = 		(E2_Button) new E2_Button()._tr("Parameter testen")._standard_text_button()
															._aaa(()->{bibMSG.MV()._add(validateInput());})
															._aaa(()->{fillControllLabels();})
															;
	private E2_Button   bt_abbruch = 	(E2_Button) new E2_Button()._tr("Beenden")._standard_text_button()._aaa(()->{this.CLOSE_AND_DESTROY_POPUPWINDOW(true);});
	
	private VEK<IF_RB_Component>  	vInputsComps = new VEK<>();
	private VEK<RB_lab>  			vPrueflabels = new VEK<>();   //im falle des testvorgangs wird hier ein validierter wert angezeigt, wie er im sql-statement landet
	private RQ_MASK_BtMakeQueryActive m_btForActivation;
	private boolean m_test;

	
	
	/**
	 * konstruktor fuer die Ausfuehrung
	 * @author martin
	 * @date 08.11.2018
	 *
	 * @param p_record
	 * @throws myException
	 */
	public RB__EXECUTION_Container(Rec21 p_record) throws myException {
		this(p_record, false, null);
		this.set_cADDON_TO_CLASSNAME(p_record.getUfs(REPORTING_QUERY.realname_temptable));

	}

	
	
	
	/**
	 * aufbau eines abfrage-containers, der die Parameter einfordert und den report startet
	 * @author martin
	 * @date 30.10.2018
	 *
	 * @param p_record
	 * @param callAsTest (wenn true, dann wird eine Testfunktion auf korrekte parameter eingeblendet)
	 * @param btForActivation  (button von erfassungsmaske, wird aktive, wenn testquery geklappt hat)
	 * @throws myException 
	 */
	public RB__EXECUTION_Container(Rec21 p_record, boolean callAsTest, RQ_MASK_BtMakeQueryActive btForActivation) throws myException {
		super();
		this.record = 		p_record;
		this.m_test = callAsTest;
		this.m_btForActivation = btForActivation;
		
		if (this.m_test) {
			this.bt_start.setText(S.ms("Abfrage testen").CTrans());
		} else {
			this.bt_start.setText(S.ms("Abfrage starten").CTrans());
		}
		
		this.rlParam = 		p_record.get_down_reclist21(REPORTING_QUERY_PARAM.id_reporting_query);
		
		this.add(gridContent, E2_INSETS.I(5,5,5,5));
		
		RB_TextArea ta = new RB_TextArea(600, 5)._t(this.record.getUfs(REPORTING_QUERY.langtextinfo));
		ta.mark_Neutral();
		ta.setEnabled(false);
		ta.setBackground(new E2_ColorLight());
		ta.setForeground(Color.BLACK);

		this.gridContent._s(3)
						._a(new RB_lab(S.msUt(this.record.getUfs(REPORTING_QUERY.titel_4_user)))._fo_s_plus(4), new RB_gld()._span(2)._ins(2,10,2,10))._a()
						._a(ta, new RB_gld()._span(2)._ins(2,10,2,10))._a()
						._a(new Separator(), new RB_gld()._span(2)._ins(2,2,2,2))._a()
						;

		
		for (Rec21 r: this.rlParam) {
			
			if (S.isEmpty(r.getUfs(REPORTING_QUERY_PARAM.typ)) ) {
				throw new myException(this,"Empty parameter-typ is no allowed !");
			}
			
			IF_RB_Component comp = RQ__PARAM_TYP.YES_NO.getEnum(r.getUfs(REPORTING_QUERY_PARAM.typ)).getComponent(r);
			vInputsComps.add(comp);
			vPrueflabels.add(new RB_lab());
			comp.EXT().set_O_PLACE_FOR_EVERYTHING(r);         //jede kompoenten merkt sich den record
			comp.rb_set_db_value_manual(r.getUfs(REPORTING_QUERY_PARAM.paramdefault));
		}

		
		for (int i=0; i<vInputsComps.size();i++) {
			IF_RB_Component c = vInputsComps.get(i);
			RB_lab 			l = vPrueflabels.get(i);
			Rec21 r = (Rec21)c.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			gridContent._a(new RB_lab()._t(r.getUfs(REPORTING_QUERY_PARAM.paramname_4_user)), new RB_gld()._ins(2));
			gridContent._a((Component)c, new RB_gld()._ins(2));
			gridContent._a(l, new RB_gld()._ins(2));
		}
		
		if (callAsTest) {
			gridContent._a(		new E2_Grid()._s(3)
											._a(this.bt_start, new RB_gld()._ins(0, 10, 10, 2))
											._a(this.bt_test, new RB_gld()._ins(0, 10, 10, 2))
											._a(this.bt_abbruch,new RB_gld()._ins(10, 10, 10, 2))
											
								, new RB_gld()._span(3)._ins(2,2,2,2));
		} else {
			gridContent._a(		new E2_Grid()._s(2)
						._a(this.bt_start, new RB_gld()._ins(0, 10, 10, 2))
						._a(this.bt_abbruch,new RB_gld()._ins(10, 10, 10, 2))
						, new RB_gld()._span(3)._ins(2,2,2,2));			
		}
	}
	
	
	
	
	/**
	 * validierung der eingabefelder
	 * @author martin
	 * @date 31.10.2018
	 *
	 * @return
	 * @throws myException
	 */
	private MyE2_MessageVector validateInput() throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		for (int i=0; i<vInputsComps.size();i++) {
			IF_RB_Component c = vInputsComps.get(i);
			Rec21           r_param = (Rec21)c.EXT().get_O_PLACE_FOR_EVERYTHING();
			RQ__PARAM_TYP   p = RQ__PARAM_TYP.YES_NO.getEnum(r_param.getUfs(REPORTING_QUERY_PARAM.typ));
			
			String inputVal = c.get__actual_maskstring_in_view();
			
			if (!p.isValid(inputVal)) {
				mv._addAlarm(S.ms("Fehler bei der Eingabe im Feld: <").ut(r_param.getUfs(REPORTING_QUERY_PARAM.paramname_4_user)).ut("> "+p.getValidMsgParam(inputVal).CTrans()));
			}
		}
		return mv;
	}
	
	
	
	private void fillControllLabels() throws myException {
		for (int i=0; i<vInputsComps.size();i++) {
			IF_RB_Component c = 		vInputsComps.get(i);
			RB_lab 			l = 		vPrueflabels.get(i);
			Rec21           r_param = 	(Rec21)c.EXT().get_O_PLACE_FOR_EVERYTHING();
			RQ__PARAM_TYP   p = 		RQ__PARAM_TYP.YES_NO.getEnum(r_param.getUfs(REPORTING_QUERY_PARAM.typ));
			
			String 			inputVal = 	c.get__actual_maskstring_in_view();
			
			if (p.isValid(inputVal)) {
				l._t(p.getSqlPart(inputVal));
			}
		}
	}

	

	//an dieser stelle muss bereits validiert sein !!
	public MyE2_MessageVector createIfNeededAndFillTempTable() throws myException {
		//zuerst die parameter/value-Map fuellen
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		HashMap<String, String> hmParamVals = new HashMap<>();
		
		for (int i=0; i<vInputsComps.size();i++) {
			IF_RB_Component c = 		vInputsComps.get(i);
			Rec21           r_param = 	(Rec21)c.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			hmParamVals.put(r_param.getUfs(REPORTING_QUERY_PARAM.paramkey), c.get__actual_maskstring_in_view());
			
		}

		String cSql = new RQ__serviceBuildQuery().getSqlSelectBlock(record, hmParamVals);
		if (this.m_test && this.m_btForActivation!=null) {
			String[][] result = bibDB.EinzelAbfrageInArray(cSql,"");
			if (result!=null) {
				mv._addInfo(S.ms("OK: Anzahl Datensätze in der Ergebniss-Menge :"+result.length));
				this.m_btForActivation.set_bEnabled_For_Edit(true);
			} else {
				mv._addAlarm(S.ms("Fehler bei der Abfrage: ").ut(cSql));

			}
		} else {
			mv._add(new RQ__serviceBuildQuery().fillOrCreateAndFillHelpTable(record, cSql));
			if (mv.isOK()) {
				mv._addInfo(S.ms("OK: Anzahl Datensätze in der Ergebnis-Menge :"+new RQ__serviceBuildQuery().getNumberOfRows(this.record)));
			}
		}
		return mv;
	}
	
	
	private class ActionAgentStartReport extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (!m_test) {
				RB__ResultContainer result = new RB__ResultContainer(RB__EXECUTION_Container.this.record);
				result.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Ausführung von: ").ut(RB__EXECUTION_Container.this.record.getUfs(REPORTING_QUERY.titel_4_user)));
			}
		}
		
	}
	
	// falls keine eingabeparameter vorhanden sind, kann das direkt gestartet werden
	public boolean isToBeShown() {
		return this.rlParam.size()>0;
	}
	
	
}
