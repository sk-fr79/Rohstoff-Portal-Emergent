package panter.gmbh.Echo2.components;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicContainer;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_InfoButton_NG extends E2_Button {

	private E2_Grid   title_grid = new E2_Grid();
	private E2_Grid   info_grid = new E2_Grid();
	
	
	/**
	 * 
	 */
	public E2_InfoButton_NG() {
		super();
		this.__setImages(E2_ResourceIcon.get_RI("inforound.png"), E2_ResourceIcon.get_RI("inforound__.png"))._s_Image()._aaa(new ownActionAgent())
			._width(18)
			;
	}

	
	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			E2_InfoButton_NG oThis = E2_InfoButton_NG.this;
			
			E2_BasicContainer container = oThis.generate_popup_container();
			
			oThis.title_grid._clear();
			oThis.info_grid._clear();
			oThis.fill_popup_container_and_show(container, oThis.title_grid, oThis.info_grid);
			
		}
	}
		

	/**
	 * erzeugt den popup-container
	 * @return
	 * @throws myException
	 */
	public abstract E2_BasicContainer  generate_popup_container() throws myException;
	
	//anordnen der grids und informationen
	public abstract E2_InfoButton_NG fill_popup_container_and_show(E2_BasicContainer own_container, E2_Grid title_grid,  E2_Grid info_grid ) throws myException;

	
	public E2_Grid get_title_grid() {
		return title_grid;
	}

	public E2_Grid get_info_grid() {
		return info_grid;
	}


	
}
