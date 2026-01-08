package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;

public class WF_ListSelektor_3_StatusGeloescht_Ja_Nein extends E2_ListSelektorMultiselektionStatusFeld_STD  implements IF_definable_all_or_relevant {
	
	public WF_ListSelektor_3_StatusGeloescht_Ja_Nein() throws myException {
		super(bibARR.ia(140,140),false,null,new Extent(80));

		this.ADD_STATUS_TO_Selector(true,	new vgl_YN(LAUFZETTEL.geloescht,false).s(),			new MyE2_String("Ungelöscht"), 		new MyE2_String("Zeige ungelöschte Laufzettel"));		
		this.ADD_STATUS_TO_Selector(false,	new vgl_YN(LAUFZETTEL.geloescht,true).s(),			new MyE2_String("Gelöscht"), 		new MyE2_String("Zeige gelöschte Laufzettel"));
		
		this.set_cConditionWhenAllIsSelected("");
		this.set_cConditionWhenNothingIsSelected("1=2");

		this.get_vCheckBoxTypen().get(0).setFont(new E2_FontPlain(-2));
		this.get_vCheckBoxTypen().get(1).setFont(new E2_FontPlain(-2));

		this.set_oNeutralisator(new XX_ListSelektor_Neutralisator() {
			@Override
			public void set_to_Neutral() throws myException {
				WF_ListSelektor_3_StatusGeloescht_Ja_Nein.this.get_vCheckBoxTypen().get(0).setSelected(true);
				WF_ListSelektor_3_StatusGeloescht_Ja_Nein.this.get_vCheckBoxTypen().get(1).setSelected(true);
			}
		});

	}
	

	@Override
	public String get_WhereBlock() throws myException {
		String c_where = super.get_WhereBlock();
		
		//DEBUG.System_println("Statusauswahl: "+c_where);
		
		return c_where;
	}

	
	@Override
	public Component get_oComponentForSelection() {
		
		E2_Grid  g = new E2_Grid()._setSize(140,100);
		
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
		this.get_vCheckBoxTypen().get(0).setSelected(true);   //ungeloescht
		this.get_vCheckBoxTypen().get(1).setSelected(false);   //ungeloescht
	}

	
}