/**
 * 
 */
package panter.gmbh.basics4project.SANKTION;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class SANKTION_ResultScreen extends E2_BasicModuleContainer {

	/**
	 * @throws myException 
	 * 
	 */
	public SANKTION_ResultScreen(boolean is_4_save,E2_Grid grd) throws myException {
		super();

		E2_Grid gHelp = new E2_Grid()._setSize(640);

		E2_Grid bt_grd = new E2_Grid()
				._a(new btCloseAndPrint(),	new RB_gld()._ins(2, 10, 5, 2))
				._a(new btClose(),			new RB_gld()._ins(2, 10, 5, 2));

		gHelp
		._a(new RB_lab()._tr("Ergebnis der Adress-Prüfung auf Sanktionseinträge:")._b(), new RB_gld()._ins(0,15,0,15))
		._a(grd,new RB_gld()._ins(2))._a(bt_grd, new RB_gld()._ins(2, 10, 2, 2));

		this.add(gHelp, E2_INSETS.I(5,5,5,5));

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(650), new Extent(500),S.ms("Ergebnis..."));
	}


	private class btCloseAndPrint extends E2_Button {
		public btCloseAndPrint() {
			super();
			this._tr("Komplette Sanktionsliste drucken und schliessen")._standard_text_button();
			this._ttt(S.ms("Schliess das Fenster und druck ein Report mit die Suche Ergebnisse."));
			this._aaa(new SANKTION_JasperHash_ActionAgent());
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					SANKTION_ResultScreen.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
	}
	
	private class btClose extends E2_Button {
		public btClose() {
			super();
			this._tr("Schliessen ohne Druck")._standard_text_button();
			this._ttt(S.ms("Schliess das Fenster und druck ein Report mit die Suche Ergebnisse."));
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					SANKTION_ResultScreen.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
	}
}
