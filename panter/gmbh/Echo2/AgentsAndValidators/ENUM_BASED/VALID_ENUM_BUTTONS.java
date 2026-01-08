package panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;


public class VALID_ENUM_BUTTONS {
	

	
	/**
	 * sammlung fuer die Upload-Fenster (Attachment-Verwaltung)
	 */
	public static enum ATTACHMENT_BUTTONS implements IF_enum_4_db {
		DELETE_ATTACHMENT(null), 
		UPLOAD_ATTACHMENT(null), 
		DOWNLOAD_ATTACHMENT(null), 	
		GENERATE_EMAIL(null), 
		START_SCAN(null), 
		MULTI_DOWNLOAD(null), 
		EDIT_IN_LIST(null), 
		CHANGE_PROGKENNER(null),	
		DELETE_MAIL(null),   			
		VIEW_MAILMASK(null), 	
		EDIT_MAILMASK(null), 	
		SENDMAIL(null),
		CONNECT_TO_OTHER_MODULE(null),
		__ALL__("@@@ALL@@@");   //pseudoschluessel
		
		private String KEY = null;
		private ATTACHMENT_BUTTONS(String key) {
			this.KEY=key;
		}

		public String     db_val() {
			return S.isEmpty(this.KEY)?this.name():this.KEY;
		}
		
		public IF_enum_4_db get_ButtonKey() {
			return this;
		}
		
		@Override
		public String user_text_lang() {
			return this.name();
		}
		@Override
		public String user_text() {
			return this.name();
		}

		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(ATTACHMENT_BUTTONS.values(), emptyPairInFront);
		}


		
	}

	
	


	
	
	
}
