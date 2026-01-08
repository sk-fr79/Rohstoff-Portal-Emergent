package panter.gmbh.BasicInterfaces.ServiceBean;

import java.util.GregorianCalendar;
import java.util.HashMap;

import com.google.common.base.Enums;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceWriteInReportVerlauf;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.REPORT_VERLAUF.ENUM_REPORT_CONSTANT;
import rohstoff.Echo2BusinessLogic.REPORT_VERLAUF.ENUM_REPORT_WEG;

public class PdServiceWriteInReportVerlaufBean implements PdServiceWriteInReportVerlauf {


	@Override
	public void save(E2_JasperHASH ojHash, MyE2_MessageVector omv) throws myException {

		if(! (ojHash== null)) {

			VEK<Rec21> vRecords = new VEK<Rec21>();

			HashMap<String, String> values_4_report_log_param = new HashMap<String, String>();

			String weg = "";
			if(ojHash.get_IS_TYP_PREVIEW()) {
				weg = ENUM_REPORT_WEG.PREVIEW.name();
			}else if(ojHash.get_IS_TYP_PRINT()) {
				weg = ENUM_REPORT_WEG.PRINT.name();
			}else if(ojHash.get_IS_TYP_MAIL()) {
				weg = ENUM_REPORT_WEG.MAIL.name();
			}else {
				weg = ENUM_REPORT_WEG.UND.name();
			}

			Rec21 rec_reportLog = new Rec21(_TAB.report_log)
					._nv(REPORT_LOG.report_jasperfile , 		ojHash.get_cReportBaseName(), 					omv)
					._nv(REPORT_LOG.report_datei_name, 			ojHash.get_completeDownloadAndSendeName(true),	omv)
					._nv(REPORT_LOG.report_titel, 				ojHash.get_HM_ARCHIV_DATEINAME(),	omv)
					._nv(REPORT_LOG.report_druck_von, 			bibALL.get_ID_USER(), 							omv)
					._nv(REPORT_LOG.report_weg, 				weg, 											omv)
					._setNewVal(REPORT_LOG.report_druck_am, 	new GregorianCalendar().getTime(), 				omv)
					;

			for(String hash_k: ojHash.keySet()) {
				if(! Enums.getIfPresent(ENUM_REPORT_CONSTANT.class, hash_k).isPresent()) {
					if(ojHash.get(hash_k) instanceof String) {
						String val = ((String)ojHash.get(hash_k));
						values_4_report_log_param.put(hash_k, val );
					}
				}
			}

			vRecords._a(rec_reportLog);

			for(String hash_k: values_4_report_log_param.keySet()) {

				DEBUG.System_println( ""+ hash_k +":" +values_4_report_log_param.get(hash_k));

				Rec21 rec_reportLog_param = new Rec21(_TAB.report_log_param)
						._nv(REPORT_LOG_PARAM.parameter_name, 	hash_k, omv)
						._nv(REPORT_LOG_PARAM.parameter_wert, 	""+values_4_report_log_param.get(hash_k), omv)
						;

				rec_reportLog_param._setNewValueInDatabaseTerminus(REPORT_LOG_PARAM.id_report_log, _TAB.report_log.seq_currval());

				vRecords._a(rec_reportLog_param);
			}

			for(Rec21 rec_2_save: vRecords) {
				rec_2_save._SAVE(false, omv);
			}
			if(omv.get_bIsOK()) {
				bibDB.Commit();
//				bibMSG.MV()._addInfo("");
			}else {
				omv._addAlarm("**** ERROR !");
			}

		}

	}


}
