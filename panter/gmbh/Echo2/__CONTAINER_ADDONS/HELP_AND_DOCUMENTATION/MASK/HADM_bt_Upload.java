package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_SingularButton;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Searcher;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class HADM_bt_Upload extends E2_ButtonUpDown_SingularButton {

	public HADM_bt_Upload(String id_table_uf) {
		super(_TAB.hilfetext.baseTableName(), id_table_uf, E2_MODULNAME_ENUM.MODUL.POPUP_WINDOW_HELPTEXT_LIST.get_callKey(), true);
		
		this.add_GlobalValidator(new HADM_bt_validator(id_table_uf));
		
		this.get_vActionAgentWhenCloseWindow().add(new ownActionOnClose());
		
		this.setToolTipText(new MyE2_String("Dokumente zu diesem Eintrag hochladen").CTrans());

	}
	
	
	private class ownActionOnClose extends XX_ActionAgentWhenCloseWindow {
		
		public ownActionOnClose() {
			super(null);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new HAD_Searcher()._rebuild();
		}
		
	}

}
