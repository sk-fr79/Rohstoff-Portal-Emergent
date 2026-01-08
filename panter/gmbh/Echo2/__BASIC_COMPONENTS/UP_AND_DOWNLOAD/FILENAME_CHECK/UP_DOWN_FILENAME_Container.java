package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import java.io.IOException;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FILENAME_Container extends E2_BasicModuleContainer{

	private UP_DOWN_FILENAME_ListView fileNameCheckerMainGrid = null;

	public UP_DOWN_FILENAME_Container() throws myException {
		super();
		try {

			E2_Grid grd = new E2_Grid()._w100()._bo_no()._s(1);

//			E2_Button dateiname_gathering_bt = new E2_Button()._t("#--# Dateiname in datenbank analysieren.");
//			dateiname_gathering_bt._standard_text_button()._aaa( new ownAnalyseAufrufen());
			
			this.fileNameCheckerMainGrid = new UP_DOWN_FILENAME_ListView();
			
			grd
//			._a(dateiname_gathering_bt)
			._a(this.fileNameCheckerMainGrid);
				
			this.add(grd);
			fill_list();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fill_list() throws myException, IOException{
		this.fileNameCheckerMainGrid.aufruf();
	}

//	private class ownAnalyseAufrufen extends XX_ActionAgent{
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			try {
//				UP_DOWN_FILENAME_Container.this.fill_list();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
}
