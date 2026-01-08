package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class WF_ListSelektor_3_besitztAufgaben_JaNein extends E2_ListSelektorMultiselektionStatusFeld_STD  implements IF_definable_all_or_relevant {
	
	private WF_ListSelektor_zeitraum  zeitraumselektor = null;
	
	public WF_ListSelektor_3_besitztAufgaben_JaNein(WF_ListSelektor_zeitraum  p_zeitraumselektor) throws myException {
		super(bibARR.ia(140,140),false,null,new Extent(80));

		this.zeitraumselektor = p_zeitraumselektor;
		
		String c_sql1 = "((SELECT COUNT(*) FROM "+_TAB.laufzettel_eintrag.fullTableName()+" ET WHERE ET.ID_LAUFZETTEL="+LAUFZETTEL.id_laufzettel.tnfn()+")=0)";
		String c_sql2 = "((SELECT COUNT(*) FROM "+_TAB.laufzettel_eintrag.fullTableName()+" ET WHERE ET.ID_LAUFZETTEL="+LAUFZETTEL.id_laufzettel.tnfn()+")>0)";
		
		this.ADD_STATUS_TO_Selector(true,	c_sql2,			new MyE2_String("Hat Aufgaben"), 		new MyE2_String("Zeige Laufzettel mit Aufgaben"));
		this.ADD_STATUS_TO_Selector(true,	c_sql1,			new MyE2_String("Hat KEINE Aufgaben"), 	new MyE2_String("Zeige Laufzettel ohne Aufgaben"));		
		
		this.set_cConditionWhenAllIsSelected("");
		this.set_cConditionWhenNothingIsSelected("1=2");
		this.get_vCheckBoxTypen().get(0).add_oActionAgent(new ownActionAgent(),true);
		
		this.get_vCheckBoxTypen().get(0).setFont(new E2_FontPlain(-2));
		this.get_vCheckBoxTypen().get(1).setFont(new E2_FontPlain(-2));

		this.set_oNeutralisator(new XX_ListSelektor_Neutralisator() {
			@Override
			public void set_to_Neutral() throws myException {
				WF_ListSelektor_3_besitztAufgaben_JaNein.this.get_vCheckBoxTypen().get(0).setSelected(true);
				WF_ListSelektor_3_besitztAufgaben_JaNein.this.get_vCheckBoxTypen().get(1).setSelected(true);
			}
		});

	}
	

	@Override
	public String get_WhereBlock() throws myException {
		String c_where = super.get_WhereBlock();
		return c_where;
	}

	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WF_ListSelektor_3_besitztAufgaben_JaNein oThis = WF_ListSelektor_3_besitztAufgaben_JaNein.this;
			
			if (oThis.get_vCheckBoxTypen().get(0).isSelected() && oThis.zeitraumselektor!=null) {
				//dann muss der selektor, der nach aufgaben-zeitraeumen selektiert, neutral sein, da der nur nach aufgaben selektiert, die es hier nicht gibt
				oThis.zeitraumselektor.get_selZeitraum().setSelectedIndex(0);
			}
			
		}
	}

	
	@Override
	public Component get_oComponentForSelection() {
		
		E2_Grid  g = new E2_Grid()._setSize(120,150);
		
		for (int i=0;i<this.get_vCheckBoxTypen().size();i++)  {
			g._a(this.get_vCheckBoxTypen().get(i), new RB_gld()._ins(E2_INSETS.I(2,1,2,1)));
		}
		
		return g;

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