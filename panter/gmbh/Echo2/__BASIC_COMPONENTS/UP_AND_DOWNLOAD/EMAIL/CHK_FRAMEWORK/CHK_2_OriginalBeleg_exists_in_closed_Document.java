package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_2;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;

public class CHK_2_OriginalBeleg_exists_in_closed_Document extends CHK_0_ABSTRACT {

	private RECLIST_ARCHIVMEDIEN_ext 	rlAM = null;
	private CHK_IF_BELEG  				Beleg = null;
	
	public CHK_2_OriginalBeleg_exists_in_closed_Document(CHK_IF_BELEG  beleg) throws myException {
		super();
		this.Beleg = beleg;
		this.rlAM = new RECLIST_ARCHIVMEDIEN_ext(this.Beleg.get_TABLEBASENAME(), this.Beleg.get_ID(), MEDIENKENNER.ORIGBELEG, null, true);
		
	}

	@Override
	public IF_STATUS check_status(MyE2_MessageVector mv_sammler) throws myException {
		IF_STATUS status = null;
		
		if (rlAM.get_vKeyValues().size()==0) {
			status = STATUS_CHK_2.HAS_NO_ORIGINAL_ATTACHMENT;
			return status;
		} else if (rlAM.get_vKeyValues().size()==1) {
			if (this.Beleg.is_Closed()) {
				status = STATUS_CHK_2.HAS_ONE_ORIGINAL_ATTACHMENT;
				return status;
			} else {
				status = STATUS_CHK_2.HAS_ONE_ORIGINAL_ATTACHMENT_BUT_NOT_CLOSED_ERR;
				mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.t("Zur Vorgangs-ID:"),
						S.ut( this.Beleg.get_ID()),
						S.t(" exisitiert eine Original-Archiv-Datei. Der Beleg ist noch OFFEN !"))));

				return status;
			}
		} else {
			status = STATUS_CHK_2.HAS_MULTI_ORIGINAL_ATTACHMENT_ERR;
			
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.t("Zur Vorgangs-ID:"),
					S.ut( this.Beleg.get_ID()),
					S.t(" exisitiert mehrere Original-Archiv-Dateien. Dies ist ein Fehler !"))));
			return status;
		}
	}

	@Override
	public MyE2_String get_Description() {
		return new MyE2_String("Prüft, ob der Vorgang ein Mail-Original besitzt");
	}

	public RECLIST_ARCHIVMEDIEN_ext get_recAM() {
		return rlAM;
	}

}
