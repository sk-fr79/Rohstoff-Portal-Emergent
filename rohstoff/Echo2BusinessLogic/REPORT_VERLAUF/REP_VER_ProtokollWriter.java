package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import java.util.GregorianCalendar;
import java.util.HashMap;

import com.google.common.base.Enums;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class REP_VER_ProtokollWriter {

	public REP_VER_ProtokollWriter _writeInLogTable(E2_JasperHASH oJH, String jasperBaseName)throws myException{
		
		VEK<Rec21> vRecords = new VEK<Rec21>();
		HashMap<String, String> values_4_report_log_param = new HashMap<String, String>();
		MyE2_MessageVector omv = new MyE2_MessageVector();
		
		String weg = "";
		if(oJH.get_IS_TYP_PREVIEW()) {
			weg = ENUM_REPORT_WEG.PREVIEW.name();
		}else if(oJH.get_IS_TYP_PRINT()) {
			weg = ENUM_REPORT_WEG.PRINT.name();
		}else if(oJH.get_IS_TYP_MAIL()) {
			weg = ENUM_REPORT_WEG.MAIL.name();
		}else {
			weg = ENUM_REPORT_WEG.UND.name();
		}

		String report_base_name = oJH.get_cReportBaseName();
		if (S.isFull(jasperBaseName) ) {
			report_base_name = jasperBaseName;
		}
		
		try {
			Rec21 rec_reportLog = new Rec21(_TAB.report_log)
					._nv(REPORT_LOG.report_jasperfile , 		report_base_name, 								omv)
					._nv(REPORT_LOG.report_datei_name, 			oJH.get_completeDownloadAndSendeName(true),		omv)
					._nv(REPORT_LOG.report_titel, 				oJH.get_HM_ARCHIV_DATEINAME(),					omv)
					._nv(REPORT_LOG.report_druck_von, 			bibALL.get_ID_USER(), 							omv)
					._nv(REPORT_LOG.report_weg, 				weg, 											omv)
					._nv(REPORT_LOG.report_uuid,				oJH.get_reportUUID(), 							omv)
					._setNewVal(REPORT_LOG.report_druck_am, 	new GregorianCalendar().getTime(), 				omv)
					;

			for(String hash_k: oJH.keySet()) {
				if(! Enums.getIfPresent(ENUM_REPORT_CONSTANT.class, hash_k).isPresent()) {
					if(oJH.get(hash_k) instanceof String) {
						String val = ((String)oJH.get(hash_k));
						values_4_report_log_param.put(hash_k, val );
					}
				}
			}

			vRecords._a(rec_reportLog);

			for(String hash_k: values_4_report_log_param.keySet()) {

				DEBUG.System_println( ""+ hash_k +":" +values_4_report_log_param.get(hash_k));

				Rec21 rec_reportLog_param = new Rec21(_TAB.report_log_param)
						._nv(REPORT_LOG_PARAM.parameter_name, 	hash_k, 									omv)
						._nv(REPORT_LOG_PARAM.parameter_wert, 	""+values_4_report_log_param.get(hash_k), 	omv)
						;

				rec_reportLog_param._setNewValueInDatabaseTerminus(REPORT_LOG_PARAM.id_report_log, _TAB.report_log.seq_currval());

				vRecords._a(rec_reportLog_param);
			}

			for(Rec21 rec_2_save: vRecords) {
				rec_2_save._SAVE(false, omv);
			}
			if(omv.get_bIsOK()) {
				bibDB.Commit();
			}else {
				DEBUG.System_print(omv);
			}
		}catch(Exception e) {
			DEBUG.System_println(e.getMessage());
		}
		return this;
	}
}
