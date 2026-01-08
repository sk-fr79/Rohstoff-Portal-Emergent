package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BorderCrossingReminder {

	public BorderCrossingReminder() {
		super();
	}
	
	
	public MyE2_MessageVector  check_and_generate_necessary_reminders_from_wiegekarte(String id_wiegekarte) throws myException { 
	
		RECORD_WIEGEKARTE  rec_wiegekarte = new RECORD_WIEGEKARTE(id_wiegekarte);
		
		if (rec_wiegekarte.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre()!=null) {
			return this.check_and_generate_necessary_reminders(rec_wiegekarte.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre());
		} else {
			return this.check_and_generate_necessary_reminders(rec_wiegekarte);
		}
	}
	
	
	
	public MyE2_MessageVector  check_and_generate_necessary_reminders(RECORD_WIEGEKARTE  rec_wiegekarte) throws myException {
		MyE2_MessageVector  mv_rueck = new MyE2_MessageVector();
		
		ArrayList<BorderCrossingInfo>  al_borderCrossingInfos = new ArrayList<>();

		if (rec_wiegekarte == null) {
			throw new myException(this," Parameter rec_wiegekarte MUST NOT BE NULL");
		}
		
		al_borderCrossingInfos.addAll(new BorderCrossingQuery_FROM_Wiegekarte()
												.get_al_BorderCrossingInfo(new VEK<String>()._a(rec_wiegekarte.ufs(WIEGEKARTE.id_wiegekarte))));
		
		
		
		Vector<BorderCrossingInfo> v_borderCrossingToWarn = new BorderCrossingChecker(al_borderCrossingInfos).get_v_critical_transactions();
		
		//im v_borderCrossingToWarn sollten nur noch bewegungen uebrig sein, die eine warnung ausloesen muessen
		
		for (BorderCrossingInfo bi: v_borderCrossingToWarn) {
			mv_rueck.add_MESSAGE(bi.f_record_bordercross.write_reminder_def(bi));
		}
		
		return mv_rueck;
	}
	
	
	public MyE2_MessageVector  check_and_generate_necessary_reminders(RECORD_VPOS_TPA_FUHRE  rec_fuhre) throws myException {
		MyE2_MessageVector  mv_rueck = new MyE2_MessageVector();
		
		ArrayList<BorderCrossingInfo>  al_borderCrossingInfos = new ArrayList<>();

		if (rec_fuhre == null) {
			throw new myException(this," Parameter rec_fuhre MUST NOT BE NULL");
		}
		
		al_borderCrossingInfos.addAll(new BorderCrossingQuery_FROM_Fuhren()
												.get_al_BorderCrossingInfo(new VEK<String>()._a(rec_fuhre.ufs(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre))));
		
		
		
		Vector<BorderCrossingInfo> v_borderCrossingToWarn = new BorderCrossingChecker(al_borderCrossingInfos).get_v_critical_transactions();
		
		//im v_borderCrossingToWarn sollten nur noch bewegungen uebrig sein, die eine warnung ausloesen muessen
		
		for (BorderCrossingInfo bi: v_borderCrossingToWarn) {
			mv_rueck.add_MESSAGE(bi.f_record_bordercross.write_reminder_def(bi));
		}
		
		return mv_rueck;
	}
	
	
	
}
