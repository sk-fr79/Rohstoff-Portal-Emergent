package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class bt_test_enum_persistence extends E2_Button {

	/**
	 * 
	 */
	public bt_test_enum_persistence() {
		super();
		this._t("Test Enum-Persitence");
		
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				if (!ENUM_VEKTORPOS_TYP.LG_MAIN.delete_recs_not_fitting(mv)) {
					bibMSG.add_MESSAGE(mv);
				} else {
					if (!ENUM_VEKTORPOS_TYP.LG_MAIN.create_recs_missing(mv)) {
						bibMSG.add_MESSAGE(mv);
					}else {
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Alles paletti!"));
					}
				}
				
			}
		});
	}

	
	
	
	
}
