package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public class EMS_RECNEW_EMAIL_SEND_SCHABLONE extends RECORDNEW_EMAIL_SEND_SCHABLONE {

	public EMS_RECNEW_EMAIL_SEND_SCHABLONE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException {
		super(hmMetadefs);
		this.set_DBToolBox_FAB(new ownToolBox_FAB());
		this.get_hm_Field_Value_pairs_from_outside().put(_DB.EMAIL_SEND_SCHABLONE$ID_MANDANT,new EMS__FindActualIdMandant().get_ID_MANDANT_UF());
	}

	
	private class ownToolBox_FAB extends MyDBToolBox_FAB {
		@Override
		public MyDBToolBox generate_INDIVIDUELL_DBToolBox(MyConnection conn) throws myException {
			return MyDBToolBox_FAB.generate_DBToolBox_WithOut_ID_MANDANT_AUTOMATIC(conn);
		}
	}
	
}
