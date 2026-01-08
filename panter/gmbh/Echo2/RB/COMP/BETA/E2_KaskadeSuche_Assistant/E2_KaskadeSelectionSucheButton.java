package panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public abstract class E2_KaskadeSelectionSucheButton extends E2_Button {

	private VEK<String> vSelectionCriteria 	= new VEK<String>();
	private VEK<String> vTitel 				= new VEK<String> ();
	private E2_BasicModuleContainer oPopUp 	= null;
	
	public E2_KaskadeSelectionSucheButton() throws myException{
		super();
		this._image("suchkaskade_mini.png");
		this._aaa(()->launch_search());
	}

	private void launch_search() throws myException{
		if(vSelectionCriteria.size()>0) {
			 this.oPopUp = new ownBasicModuleContainer();
			 this.oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(640), new Extent(480), S.ms("Suche"));
		}else {
			bibMSG.MV()._addAlarm("Kein Suche Criteria");
		}
	}
	
	public E2_KaskadeSelectionSucheButton _addCriteria(String oSelection, String oTitel) {
		vSelectionCriteria	._a(oSelection);
		vTitel				._a(oTitel);
		return this;
	}

	public E2_BasicModuleContainer getPopUp() throws myException{
		return this.oPopUp;
	}
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer{
		public ownBasicModuleContainer() throws myException {
			super();

			E2_KaskadeSelectionSuche oSelektion = new E2_KaskadeSelectionSuche();
			
			for(int i=0;i<vSelectionCriteria.size();i++){
				oSelektion._addCriteria(vSelectionCriteria.get(i), vTitel.get(i), 200, i);
			}
			oSelektion.set_ActionAgentFuerLastSelection(new oAgentFuerLastSelection());
			oSelektion.START("", false);
			
			this.add(new E2_Grid()._a(oSelektion));
		}
	}
	
	private class oAgentFuerLastSelection extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_Button oButton = (E2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			String output_value = oButton.EXT().get_C_MERKMAL();
			
			define_actions_4_lastSelection(output_value);
		}
		
	}
	
	public abstract void define_actions_4_lastSelection(String outputValue) throws myException;
}
