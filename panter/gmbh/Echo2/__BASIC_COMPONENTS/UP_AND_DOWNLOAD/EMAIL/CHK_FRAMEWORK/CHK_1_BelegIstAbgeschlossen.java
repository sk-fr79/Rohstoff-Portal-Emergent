package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_1;
import panter.gmbh.indep.exceptions.myException;

public class CHK_1_BelegIstAbgeschlossen extends CHK_0_ABSTRACT {

	
	private CHK_IF_BELEG Beleg = null;
	
	public CHK_1_BelegIstAbgeschlossen(CHK_IF_BELEG beleg) throws myException {
		super();
		this.Beleg = beleg;
		
	}

	@Override
	public IF_STATUS check_status(MyE2_MessageVector mv_sammler) throws myException {
		IF_STATUS status = null;
		
		if (this.Beleg.is_Closed()) {
			status = STATUS_CHK_1.DOCUMENT_IS_CLOSED;
			return status;
		} else {
			status = STATUS_CHK_1.DOCUMENT_IS_OPEN;
			return status;
		}

	}

	@Override
	public MyE2_String get_Description() {
		return new MyE2_String("Prüft, ob der Vorgang bereits abgeschlossen ist");
	}

}
