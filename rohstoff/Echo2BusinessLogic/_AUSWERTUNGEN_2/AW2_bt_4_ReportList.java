package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_ButtonPopupPasswordQuery;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.exceptions.myException;

public class AW2_bt_4_ReportList extends E2_ButtonPopupPasswordQuery {


	private RECORD_REPORT  rec_rep = null;
	
	public AW2_bt_4_ReportList(String cText, RECORD_REPORT  p_rec_rep) throws myException {
		super(cText);
		this.rec_rep = p_rec_rep;
	}

	@Override
	public String get_password_for_this_button() throws myException {
		return new RECORD_REPORT(this.rec_rep.get_ID_REPORT_cUF()).get_PASSWORD_cUF_NN("");
	}

	@Override
	public MyE2_Button generate_password_confirm_button() throws myException {
		return new MyE2_Button("Starte den Report");
	}

	@Override
	public E2_BasicModuleContainer generate_password_confirm_popup_modulcontainer()	throws myException {
		return new ownBasicContainer();
	}


	//eigene klasse fuer speichern der groesse/position
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}
	
}
