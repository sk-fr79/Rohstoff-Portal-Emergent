/**
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_CodingResultGrid.ComponentGenerator;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class GEO_CodingResultScreen extends E2_BasicModuleContainer {

	/**
	 * @throws myException 
	 * 
	 */
	public GEO_CodingResultScreen(ComponentGenerator generator, GEO_ErrorMap... maps) throws myException {
		super();
		
		E2_Grid gHelp = new E2_Grid()._setSize(400);
		
		GEO_CodingResultGrid g = new GEO_CodingResultGrid(generator  , maps) ;

		gHelp._a(g,new RB_gld()._ins(2))._a(new E2_Grid()._a(new btClose()), new RB_gld()._ins(2, 10, 2, 2));
		
		
		this.add(gHelp, E2_INSETS.I(5,5,5,5));
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(500),S.ms("Ergebniss ..."));
	}

	
	private class btClose extends E2_Button {
		public btClose() {
			super();
			this._tr("Schliessen")._standard_text_button();
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					GEO_CodingResultScreen.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
	}
	
	
}
