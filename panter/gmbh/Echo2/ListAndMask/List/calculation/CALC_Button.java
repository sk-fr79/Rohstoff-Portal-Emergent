package panter.gmbh.Echo2.ListAndMask.List.calculation;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2IF__BelongsToNavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public abstract class CALC_Button extends MyE2_Button implements E2IF__BelongsToNavigationList{

	/*
	 * button bekommt die navilist beim zudefinieren der componentmap zu navilist uebergeben 
	 */
	private E2_NavigationList  				o_NAVILIST = null;
	

	public abstract CALC_Container_IF 		_GENERATE_Container() throws myException;
	

	/**
	 * 
	 * @param oNaviList
	 */
	public CALC_Button(E2_ResourceIcon oICON) {
		super(oICON==null?E2_ResourceIcon.get_RI("calc.png"):oICON, true);
		this.add_oActionAgent(new actionAgentShowWindow());
	}

	
	
	private class actionAgentShowWindow extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CALC_Button oThis = CALC_Button.this;
			
			CALC_Container_IF o_CALC_Container = oThis._GENERATE_Container();
			
			
			if (oThis.o_NAVILIST==null) {
				throw new myException(this,"Error: NO NaviList is defined !");
			}

			if (o_CALC_Container==null) {
				throw new myException(this,"Error: NO CalcContainer is defined !");
			}
			
			o_CALC_Container._SET_NAVILIST_THIS_BELONGS_TO(oThis.o_NAVILIST);
			o_CALC_Container._REGISTER_NAVILIST_TO_CALC_RULES(oThis.o_NAVILIST);
			o_CALC_Container._SET_CALLING_CALC_BUTTON(oThis);
			o_CALC_Container._FILL_INTERNAL_Container();
			o_CALC_Container._SHOW_Container();
			
		}
		
	}




	@Override
	public void _SET_NAVILIST_THIS_BELONGS_TO(E2_NavigationList oNAVI_LIST) throws myException {
		this.o_NAVILIST=oNAVI_LIST;
	}


	@Override
	public E2_NavigationList _GET_NAVILIST_THIS_BELONGS_TO() throws myException {
		return this.o_NAVILIST;
	}


	
	
}
