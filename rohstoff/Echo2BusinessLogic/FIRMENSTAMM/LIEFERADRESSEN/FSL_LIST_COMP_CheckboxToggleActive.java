package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FSL_LIST_COMP_CheckboxToggleActive extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction {

	public FSL_LIST_COMP_CheckboxToggleActive(SQLField osqlField) 	throws myException {
		super(osqlField);
		this.add_oActionAgent(this.get_ToggleAction());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"AKTIVIEREN_LIEFER_ADRESSE"));
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			return new FSL_LIST_COMP_CheckboxToggleActive(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getMessage());
		}
	}

	@Override
	public XX_ActionAgent get_ToggleAction() throws myException {
		
		return new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String cID_ADRESSE = FSL_LIST_COMP_CheckboxToggleActive.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				RECORD_ADRESSE recAdr = new RECORD_ADRESSE(cID_ADRESSE);
				boolean bStatusalt = recAdr.is_AKTIV_YES();
				MyE2_MessageVector  mv=recAdr.set_NEW_VALUE_AKTIV(bStatusalt?"N":"Y");
				if (mv.get_bIsOK()) {
					mv.add_MESSAGE(recAdr.UPDATE(true));
					if (mv.get_bIsOK()) {
						mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(S.t("Adresse: "),S.ut(cID_ADRESSE),S.t(" wurde "),S.t(bStatusalt?"inaktiv":"aktiv"),S.t(" geschaltet!"))));
					}
				}
				bibMSG.add_MESSAGE(mv);
				
				FSL_LIST_COMP_CheckboxToggleActive.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE("");
			}
		};
		

	}

}
