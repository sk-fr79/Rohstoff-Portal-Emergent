package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_DatumsDifferenz;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;

public class ____DA_Decision_DatumsDifferenz extends DA_Decision_DatumsDifferenz {

	private ___Sammler_ID_VPOS_TPA_FUHRE o_Sammler = null;
	
	public ____DA_Decision_DatumsDifferenz(	DS_IF_components4decision p_actionComponent,
															___Sammler_ID_VPOS_TPA_FUHRE p_sammler) {
		super(p_actionComponent);
		this.o_Sammler = p_sammler;
		this.set_date_collector(new own_date_collector());
	}

	private class own_date_collector extends collector_Date_values {
		
		@Override
		public ArrayList<ArrayList<MyDate>> get_dateSammlungen() throws myException {
			___Sammler_ID_VPOS_TPA_FUHRE id_sammler = ____DA_Decision_DatumsDifferenz.this.o_Sammler;
			
			ArrayList<ArrayList<MyDate>>  date_vector = new ArrayList<>();
			
			Vector<String>  v_id_vpos_tpa_fuhre = id_sammler.get_vID_VPOS_TPA_FUHRE();

			String view_name = "V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN";
			
			for (String id: v_id_vpos_tpa_fuhre) {
				if (S.isFull(id)) {
					SEL sel = new SEL(view_name+".*")
								.ADDFIELD("to_char("+view_name+".ID_VPOS_TPA_FUHRE,'0000000000')||to_char(NVL("+view_name+".ID_VPOS_TPA_FUHRE_ORT,0),'0000000000')", 
											"ID_ID").FROM(view_name).WHERE(new FieldTerm(view_name, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre), 
													new TermSimple(id));
					
					RECLIST_VPOS_TPA_FUHRE rlf = new RECLIST_VPOS_TPA_FUHRE(sel.s(),bibALL.get_oConnectionNormal(),0,"ID_ID");
					
					if (rlf.size()>0) {
						for (RECORD_VPOS_TPA_FUHRE rf: rlf) {
							ArrayList<MyDate> v_dates = new ArrayList<>();
	
							this.add_only_if_ok(rf.fs(VPOS_TPA_FUHRE.datum_abholung),v_dates);
							this.add_only_if_ok(rf.fs(VPOS_TPA_FUHRE.datum_abholung_ende),v_dates);
							this.add_only_if_ok(rf.fs(VPOS_TPA_FUHRE.datum_anlieferung),v_dates);
							this.add_only_if_ok(rf.fs(VPOS_TPA_FUHRE.datum_anlieferung_ende),v_dates);
							this.add_only_if_ok(rf.fs(VPOS_TPA_FUHRE.datum_aufladen),v_dates);
							this.add_only_if_ok(rf.fs(VPOS_TPA_FUHRE.datum_abladen),v_dates);
							
							date_vector.add(v_dates);
						}
					}
				}
			}
			
			//DEBUG.System_println("Anzahl Fuhren in der Prüfung: "+date_vector.size());
			
			return date_vector;
		}
		
		private void add_only_if_ok(String formated_date,ArrayList<MyDate> v_dates) {
			if (S.isFull(formated_date)) {
				
				//DEBUG.System_println(formated_date);
				
				MyDate dateTest = new MyDate(formated_date);
				if (dateTest.get_bOK()) {
					v_dates.add(dateTest);
				}
			}
		}
		
	}
}
