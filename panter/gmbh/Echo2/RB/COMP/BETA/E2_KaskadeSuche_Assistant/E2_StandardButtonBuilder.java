/**
 * panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant
 * @author sebastien
 * @date 20.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols.SelectBlockGrid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author sebastien
 * @date 20.05.2019
 *
 */


public class E2_StandardButtonBuilder implements IF_KaskadeButtonBuilder
{

	@Override
	public VEK<E2_Button> build_Buttons_In_Grid(E2_KaskadeSelectionSuche oMultiSel, String[] ergebnisZeile, E2_Grid ownSelectGrid) throws myException {
		String cNextValue = ergebnisZeile[0];
		String cButtonText = ergebnisZeile.length>1?ergebnisZeile[1]:cNextValue;
		String cButtonHelpText = ergebnisZeile.length>2?ergebnisZeile[2]:cButtonText;

		E2_Button oButton = new E2_Button()._t(cButtonText)	._bc(new E2_ColorBase());
		oButton._standard_text_button();
		oButton._aaa(new ActionToSelectNextColumn(oMultiSel))._ttt(cButtonHelpText+" ("+cNextValue+")");
		oButton.EXT().set_C_MERKMAL(cNextValue);
		oButton.EXT().set_O_PLACE_FOR_EVERYTHING(ownSelectGrid);

		ownSelectGrid._a(oButton, new RB_gld()._ins(1));
		
		return new VEK<E2_Button>()._a(oButton);
	}
	
	public class ActionToSelectNextColumn extends XX_ActionAgent
	{
		private E2_KaskadeSelectionSuche  oMultiSelColumns = null;
		
		
		public ActionToSelectNextColumn(E2_KaskadeSelectionSuche multiSelColumns)
		{
			super();
			this.oMultiSelColumns = multiSelColumns;
		}


		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			//zuerst den ausstiegspunkt auf null setzen
			this.oMultiSelColumns.set_cWertFuerAusstieg(null);
			
			E2_Button oButton = (E2_Button)execInfo.get_MyActionEvent().getSource();
			
			E2_Kaskade_SelectBlockGrid oSelCol = (E2_Kaskade_SelectBlockGrid)oButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			String cValueForNextCol = oButton.EXT().get_C_MERKMAL();
			
			if (oSelCol.isLastColumn()){
				this.oMultiSelColumns.set_cWertFuerAusstieg(cValueForNextCol);
				
				//dann den actionAgent starten
				if (this.oMultiSelColumns.getLastSelectionAgent() != null){
					this.oMultiSelColumns.getLastSelectionAgent().executeAgentCode(execInfo);
				}
			}else{
				oSelCol.getNextColumn().initialize_Column(cValueForNextCol);
			}
		}
		
	}
}
