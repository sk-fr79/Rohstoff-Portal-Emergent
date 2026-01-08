/**
 * rohstoff.Echo2BusinessLogic.CONTAINERTYP
 * @author manfred
 * @date 07.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT.IF_definable_all_or_relevant;

/**
 * @author manfred
 * @date 07.12.2017
 *
 */
public abstract class CONTYP_Selector_YN extends E2_ListSelektorMultiselektionStatusFeld_STD  implements IF_definable_all_or_relevant{
	
	
	
	
	/**
	 * Must call:  ADD_STATUS_TO_Selector(boolean bPreselected, String cBedingungsBlockFuer_OR_Statement, MyString cBeschriftung, MyString cToolTips));
	 * @author manfred
	 * @date 07.12.2017
	 *
	 */
	protected abstract void addStatusToSelector();
	
	
	/**
	 * @author manfred
	 * @date 07.12.2017
	 *
	 */
	public CONTYP_Selector_YN(String sBeschriftung) {
		super(bibARR.ia(30,30),true,S.ms(sBeschriftung) ,new Extent(80));

		// abstrakte Methode...
		addStatusToSelector();
		
		this.set_cConditionWhenAllIsSelected("");
		this.set_cConditionWhenNothingIsSelected("1=2");
		
		for ( MyE2_CheckBox box :  get_vCheckBoxTypen()  ){
			// default Eventhandler anfügen
			box.add_oActionAgent(new ownActionAgent(),true);
			// Font anpassen
			box.setFont(new E2_FontPlain(-2));
		}

		this.set_oNeutralisator(new XX_ListSelektor_Neutralisator() {
			@Override
			public void set_to_Neutral() throws myException {
				
				for ( MyE2_CheckBox box :  CONTYP_Selector_YN.this.get_vCheckBoxTypen()  ){
					
					box.setSelected(true);
				}
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
		}
	}

	
//	@Override
//	public Component get_oComponentForSelection() {
//		
//		E2_Grid  g = new E2_Grid()._setSize(120,150);
//		
//		for (int i=0;i<this.get_vCheckBoxTypen().size();i++)  {
//			g._a(this.get_vCheckBoxTypen().get(i), new RB_gld()._ins(E2_INSETS.I(2,1,2,1)));
//		}
//		
//		return g;
//
//	}


	@Override
	public void select_all_data() throws myException {
		this.set_neutral_if_possible();
	}


	@Override
	public void select_relevant_data() throws myException {
		this.set_neutral_if_possible();
	}
	
	
	
}
