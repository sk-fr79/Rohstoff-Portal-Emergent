package rohstoff.Echo2BusinessLogic.INVENTUR_FREMDLAGER;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class INF_SAVE_SETTINGS extends E2_UserSetting_HashMap {

	public static String KEY_LAGER_AKTIV	=			"KEY_LAGER_AKTIV";
	public static String KEY_LAGER_INAKTIV	=			"KEY_LAGER_INAKTIV";
	public static String KEY_EIGENWARE	=				"KEY_EIGENWARE";
	public static String KEY_FREMDWARE	=				"KEY_FREMDWARE";
	public static String KEY_HAUPTLAGER	=				"KEY_HAUPTLAGER";
	public static String KEY_INFOZEILE	=				"KEY_INFOZEILE";
	public static String KEY_ID_USER_SACHBEARBEITER	=	"KEY_ID_USER_SACHBEARBEITER";
	public static String KEY_ID_LISTE_SORTEN_EXCLUDE=	"KEY_ID_LISTE_SORTEN_EXCLUDE";
	public static String KEY_MAIL_TYPE_MATRIX=			"KEY_MAIL_TYPE_MATRIX";

	private Vector<String>  vKEYS = new Vector<String>();
	
	
	public INF_SAVE_SETTINGS() {
		super(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SAVE_LAGER_INVENTUR_MODUL.name());
		
		vKEYS.add(INF_SAVE_SETTINGS.KEY_LAGER_AKTIV);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_LAGER_INAKTIV);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_EIGENWARE);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_FREMDWARE);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_HAUPTLAGER);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_INFOZEILE);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_ID_USER_SACHBEARBEITER);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_ID_LISTE_SORTEN_EXCLUDE);
		vKEYS.add(INF_SAVE_SETTINGS.KEY_MAIL_TYPE_MATRIX);

		this.set_KeySet(vKEYS);
		
	}

	
	public void save_Values(INF_ANFORDERUNG_INVENTUR_BasicModuleContainer oContainer) throws myException {
		HashMap<String, String>  hmThingsToSave = new HashMap<String, String>();
		
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_LAGER_AKTIV, oContainer.get_oCB_Aktiv().isSelected()?"Y":"N");
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_LAGER_INAKTIV, oContainer.get_oCB_InAktiv().isSelected()?"Y":"N");
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_EIGENWARE, oContainer.get_oCB_EigenWare().isSelected()?"Y":"N");
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_FREMDWARE, oContainer.get_oCB_Fremdware().isSelected()?"Y":"N");
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_HAUPTLAGER, oContainer.get_oCB_HauptlagerAuchDrucken().isSelected()?"Y":"N");
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_INFOZEILE, oContainer.get_oCB_BlendeInfozeileEin().isSelected()?"Y":"N");
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_ID_USER_SACHBEARBEITER,S.NN(oContainer.get_oSelectUser().get_ActualWert()));
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_ID_LISTE_SORTEN_EXCLUDE,oContainer.get_SaveStringAusgeblendete_Sorten());
		hmThingsToSave.put(INF_SAVE_SETTINGS.KEY_MAIL_TYPE_MATRIX,oContainer.get_SaveStringMailTypenMatrix());
		
		this.STORE(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SAVE_LAGER_INVENTUR_MODUL.name(), hmThingsToSave);
	}
	

	@SuppressWarnings("unchecked")
	public void restore_values(INF_ANFORDERUNG_INVENTUR_BasicModuleContainer oContainer) throws myException {
		HashMap<String, String>  hmThingsToSave = (HashMap<String, String>)this.get_Settings(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SAVE_LAGER_INVENTUR_MODUL.name());
		
		if (hmThingsToSave != null && hmThingsToSave.size()>0) {
			
			oContainer.get_oCB_Aktiv().setSelected(S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_LAGER_AKTIV)).equals("Y"));
			oContainer.get_oCB_EigenWare().setSelected(S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_EIGENWARE)).equals("Y"));
			oContainer.get_oCB_Fremdware().setSelected(S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_FREMDWARE)).equals("Y"));
			oContainer.get_oCB_HauptlagerAuchDrucken().setSelected(S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_HAUPTLAGER)).equals("Y"));
			oContainer.get_oCB_BlendeInfozeileEin().setSelected(S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_INFOZEILE)).equals("Y"));
	
			String cID_USER = hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_ID_USER_SACHBEARBEITER);
			if (S.isFull(cID_USER)) {
				oContainer.get_oSelectUser().set_ActiveValue_OR_FirstValue(cID_USER);
			}
			
			String cID_LIST_SORTEN_EXCLUDE =  S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_ID_LISTE_SORTEN_EXCLUDE));
			oContainer.restore_SavedStringAusgeblendete_Sorten(cID_LIST_SORTEN_EXCLUDE);
			
			String cSaveStringMailTypenMatrix =  S.NN(hmThingsToSave.get(INF_SAVE_SETTINGS.KEY_MAIL_TYPE_MATRIX));
			oContainer.restore_MailTypenMatrix(cSaveStringMailTypenMatrix);
		}
	}
	
}
