package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_SF_Zahlungsbedingung extends RB_SelectField {

	public KFIX_K_M_SF_Zahlungsbedingung(IF_Field oField, int iWidth) throws myException {
		super(oField, 
				new SEL()
				.ADDFIELD(ZAHLUNGSBEDINGUNGEN.kurzbezeichnung.fieldName(),"B")
				.ADDFIELD(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen.fieldName(),"A")
				.FROM(_TAB.zahlungsbedingungen).s(),
				false,
				false,
				new Extent(iWidth)
				);
		this.add_oActionAgent(new ownActionAgent(), false);
	}

	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_K_M_SF_Zahlungsbedingung oThis = KFIX_K_M_SF_Zahlungsbedingung.this;
			bibMSG.add_MESSAGE(new KFIX_K_M_Controller(
					oThis._find_componentMapCollector_i_belong_to())
					.do_mask_settings(oThis, 
							bibALL.convertID2UnformattedID(oThis.get_ActualWert()), true)
					);
		}

	}
}
