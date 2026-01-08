package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_DecisionHat_EU_VERTRAG_warenbewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_DecisionHat_EU_VERTRAG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;

public class ____DA_DecisionHat_EU_VERTRAG extends DA_DecisionHat_EU_VERTRAG {

	private ___Sammler_ID_VPOS_TPA_FUHRE  id_sammler = null;
	
	public ____DA_DecisionHat_EU_VERTRAG(DS_IF_components4decision p_actionComponent, ___Sammler_ID_VPOS_TPA_FUHRE p_sammler) {
		super(p_actionComponent);
		this.id_sammler = p_sammler;
		this.set_collector_relations(new collectorRelations());
	}

	private class collectorRelations extends DA_DecisionHat_EU_VERTRAG.collector_Relations {
		@Override
		public ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung> get_datasets() throws myException {
			
			ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung> al_rueck = new ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung>();
			
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
							al_rueck.add(new DA_DecisionHat_EU_VERTRAG_warenbewegung(rf));
						}
					}
				}
			}
			return al_rueck;
		}
		
	}
	
}
