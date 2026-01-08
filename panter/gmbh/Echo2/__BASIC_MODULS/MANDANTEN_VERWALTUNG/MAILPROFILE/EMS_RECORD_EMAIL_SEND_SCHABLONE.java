package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_SCHABLONE;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.exceptions.myException;

public class EMS_RECORD_EMAIL_SEND_SCHABLONE extends RECORD_EMAIL_SEND_SCHABLONE {

	public EMS_RECORD_EMAIL_SEND_SCHABLONE(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super();
		
		this.set_DBToolBox_FAB(new ownToolBox_FAB());
		
		this.PrepareAndBuild("*",bibE2.cTO()+"."+this.get_TABLENAME(),this.get_PRIMARY_KEY_NAME()+"="+c_ID_or_WHEREBLOCK_OR_SQL);
	}
	
	private class ownToolBox_FAB extends MyDBToolBox_FAB {
		@Override
		public MyDBToolBox generate_INDIVIDUELL_DBToolBox(MyConnection conn) throws myException {
			MyDBToolBox o_db = MyDBToolBox_FAB.generate_DBToolBox_WithOut_ID_MANDANT_AUTOMATIC(conn);
			o_db.set_bErsetzungTableView(false);
			return o_db;
		}
	}

	
}
