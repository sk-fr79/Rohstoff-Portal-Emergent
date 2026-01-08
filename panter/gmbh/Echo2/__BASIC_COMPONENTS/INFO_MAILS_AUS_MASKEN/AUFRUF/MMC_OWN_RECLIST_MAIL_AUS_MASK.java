package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAIL_AUS_MASK;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MMC_OWN_RECLIST_MAIL_AUS_MASK extends RECLIST_MAIL_AUS_MASK {

	public MMC_OWN_RECLIST_MAIL_AUS_MASK(String MASK_KENNER) throws myException {
		super("SELECT * FROM "+bibE2.cTO()+"."+_DB.MAIL_AUS_MASK+
				" WHERE UPPER("+_DB.MAIL_AUS_MASK$MODULKENNER+")="+bibALL.MakeSql(MASK_KENNER.toUpperCase().trim())+
				" ORDER BY "+_DB.MAIL_AUS_MASK$BUTTONBESCHRIFTUNG);
	}

}
