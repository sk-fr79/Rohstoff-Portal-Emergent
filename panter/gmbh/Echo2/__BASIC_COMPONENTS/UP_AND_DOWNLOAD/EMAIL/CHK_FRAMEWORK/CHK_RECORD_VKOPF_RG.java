package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;

public class CHK_RECORD_VKOPF_RG extends RECORD_VKOPF_RG implements CHK_IF_BELEG {

	public CHK_RECORD_VKOPF_RG(RECORD_VKOPF_RG recordOrig) {
		super(recordOrig);
	} 

	@Override
	public boolean is_Closed() throws myException {
		return this.is_ABGESCHLOSSEN_YES();
	}

	@Override
	public String get_TABLEBASENAME() {
		return _DB.VKOPF_RG.substring(3);
	}

	@Override
	public String get_ID() throws myException {
		return this.get_ID_VKOPF_RG_cUF_NN("");
	}

	@Override
	public void RollBackToStatus_Orig(MyE2_MessageVector mv_protokoll) throws myException	{
		//status vor druck des originals wiederherstellen
		VectorSingle vSQL = new VectorSingle();
		this.set_NEW_VALUE_ABGESCHLOSSEN("N");
		this.set_NEW_VALUE_DRUCKZAEHLER("0");
		vSQL.add(this.get_SQL_UPDATE_STD());
		RECLIST_ARCHIVMEDIEN_ext rlArch = new RECLIST_ARCHIVMEDIEN_ext(this.get_TABLEBASENAME(), this.get_ID_VKOPF_RG_cUF(), Archiver_CONST.MEDIENKENNER.ORIGBELEG, null, true);
		
		for (RECORD_ARCHIVMEDIEN recArch: rlArch) {
			RECLIST_EMAIL_SEND_ATTACH rlAttch =  recArch.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_archivmedien();
			for (RECORD_EMAIL_SEND_ATTACH recAttch: rlAttch) {
				
				RECORD_EMAIL_SEND recSend= recAttch.get_UP_RECORD_EMAIL_SEND_id_email_send();
				//dieser wird geloescht mit allem dran
				for (RECORD_EMAIL_SEND_TARGETS recTarget: recSend.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send()) {
					if (recTarget.is_SEND_OK_YES()) {
						//dann darf nicht geloescht werden
						mv_protokoll.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ein Vorgang mit bereits versendeten eMails SOLLTE NICHT zurückgesetzt werden !")));
					}
					vSQL.add(recTarget.get_DELETE_STATEMENT());
				}
				
				for (RECORD_EMAIL_SEND_ATTACH recAttach: recSend.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()) {
					vSQL.add(recAttach.get_DELETE_STATEMENT());
				}
				

				vSQL.add(recSend.get_DELETE_STATEMENT());
				
			}
			vSQL.add(recArch.get_DELETE_STATEMENT());
		}

		StringBuffer stb = new StringBuffer();
		for (String st: vSQL) {
			stb.append(st+";\n");
		}
		
		myTempFile  oTempFile = new myTempFile("exp_sql", ".txt", true);
		oTempFile.WriteTextBlock(stb.toString(), myTempFile.CHARSET_ISO_8859_1);
		oTempFile.close();
		
		E2_Download  oDownload = new E2_Download();
		oDownload.starteFileDownload(oTempFile.getFileName(),"sql-statement.txt", "application/text");
		
	}

	@Override
	public RECORD_EMAIL_SEND get_RECORD_EMAIL_SEND_withOriginal() 	throws myException {
		RECORD_EMAIL_SEND recSendWithOriginal = null;
		
		RECLIST_ARCHIVMEDIEN_ext rlArch = new RECLIST_ARCHIVMEDIEN_ext(this.get_TABLEBASENAME(), this.get_ID_VKOPF_RG_cUF(), Archiver_CONST.MEDIENKENNER.ORIGBELEG, null, true);
		
		for (RECORD_ARCHIVMEDIEN recArch: rlArch) {
			recSendWithOriginal = null;
			RECLIST_EMAIL_SEND_ATTACH rlAttch =  recArch.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_archivmedien();
			for (RECORD_EMAIL_SEND_ATTACH recAttch: rlAttch) {
				recSendWithOriginal = recAttch.get_UP_RECORD_EMAIL_SEND_id_email_send();
			}
		}
		
		return recSendWithOriginal;
	}

	@Override
	public MyE2_String get_BelegInfo() throws myException {
		MyE2_String cRueck = new MyE2_String("");
		
		if (this.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
			cRueck.addString(new MyE2_String("Rechnungsbeleg "));
		} else {
			cRueck.addString(new MyE2_String("Gutschriftsbeleg "));
		}
		cRueck.addString(new MyE2_String(S.t(" ID "),S.ut(this.get_ID_VKOPF_RG_cUF())));
		cRueck.addString(new MyE2_String(" an "));
		cRueck.addString(new MyE2_String(S.ut(this.get_NAME1_cUF_NN("<name>")+" "),S.ut(this.get_ORT_cUF_NN("<ort>"))));
		return cRueck;
	}

}
