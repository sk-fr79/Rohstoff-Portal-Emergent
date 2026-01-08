package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.ArrayList;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables_differentTypes;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.UserSettings.IF_Saveable_differentTypes;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;

public class AW2_StatusSaver extends E2_UserSetting_ListOfSaveables_differentTypes {

	private RECORD_REPORT_REITER 	recReportReiter = null;
	private String   				modul_kenner_addon = "ALL";	
	
	/**
	 *
	 *   @param p_id_report_reiter (is added to modulkenner, wenn null then ALL
	 * @throws myException 
	 */
	public AW2_StatusSaver(RECORD_REPORT_REITER p_recReportReiter, ArrayList<IF_Saveable_differentTypes> p_v_Components) throws myException {
		super();
		this.recReportReiter = p_recReportReiter;
		if (this.recReportReiter!=null) {
			this.modul_kenner_addon = this.recReportReiter.get_ID_REPORT_REITER_cUF();
		}
		this._init(p_v_Components, ENUM_USER_SAVEKEY.SAVEING_USERSETTINGS_IN_AW2_AUSERTUNGEN,this.modul_kenner_addon);
	}


	@Override
	public String get_SessionHash() {
		return  ENUM_USER_SAVEKEY.SESSION_HASH_GLOBAL_SETTINGS_DIVERSE.toString();
	}

}
