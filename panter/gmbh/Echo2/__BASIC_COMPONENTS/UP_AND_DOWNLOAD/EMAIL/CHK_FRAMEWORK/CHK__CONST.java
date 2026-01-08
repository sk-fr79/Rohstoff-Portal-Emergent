package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;

public class CHK__CONST {

	public interface IF_STATUS {
		public boolean isOK() ;
		public boolean isError();
		public MyE2_String get_ErrorMessage();
	}
	
	
	public enum STATUS_CHK_1 implements IF_STATUS{
		 DOCUMENT_IS_CLOSED(								true,new MyE2_String("Vorgang wurde bereits gedruckt"))
		,DOCUMENT_IS_OPEN(									true,new MyE2_String("Vorgang ist noch nicht gedruckt"))
		;
			
			private boolean 		IsOK = false;
			private MyE2_String 	ErrorMessage = null;
			private STATUS_CHK_1(boolean isOK, MyE2_String errorMessage) {
				this.IsOK = isOK;
				this.ErrorMessage = errorMessage;
			}
			public boolean isOK() {
				return IsOK;
			}
			public boolean isError() {
				return (!IsOK);
			}

			public MyE2_String get_ErrorMessage() {
				return ErrorMessage;
			}
		}

	
	public enum STATUS_CHK_2 implements IF_STATUS{
		 HAS_NO_ORIGINAL_ATTACHMENT(						true,new MyE2_String("Vorgang besitzt keine Original-Anlage"))
		,HAS_ONE_ORIGINAL_ATTACHMENT(						true,new MyE2_String("Vorgang besitzt exakt eine Original-Anlage"))
		,HAS_MULTI_ORIGINAL_ATTACHMENT_ERR(					false,new MyE2_String("Vorgang besitzt mehrere Original-Anlagen (FEHLER!!!)"))
		,HAS_ONE_ORIGINAL_ATTACHMENT_BUT_NOT_CLOSED_ERR(	false,new MyE2_String("Vorgang besitzt eine Original-Anlagen, ist aber noch nicht geschlossen (FEHLER!!!)"))
		
		;
			
			private boolean 		IsOK = false;
			private MyE2_String 	ErrorMessage = null;
			private STATUS_CHK_2(boolean isOK, MyE2_String errorMessage) {
				this.IsOK = isOK;
				this.ErrorMessage = errorMessage;
			}
			public boolean isOK() {
				return IsOK;
			}
			public boolean isError() {
				return (!IsOK);
			}

			public MyE2_String get_ErrorMessage() {
				return ErrorMessage;
			}
			
			
		}

	
	public enum STATUS_CHK_3 implements IF_STATUS{
		 ORIGINAL_IS_ATTATCHED_TO_NONE(						true,new MyE2_String("Es existiert keine eMail-Sendung mit Original-Dokument"))
		,ORIGINAL_IS_ATTATCHED_TO_SINGLE_MAIL(				true,new MyE2_String("Es existiert EINE eMail-Sendung mit Original-Dokument"))
		,ORIGINAL_IS_ATTATCHED_TO_MULTIPLE_MAIL_ERR(		false,new MyE2_String("Das Original hängt an mehreren eMail-Sendungen (FEHLER!!!)"))
		;
			
			private boolean 		IsOK = false;
			private MyE2_String 	ErrorMessage = null;
			private STATUS_CHK_3(boolean isOK, MyE2_String errorMessage) {
				this.IsOK = isOK;
				this.ErrorMessage = errorMessage;
			}
			public boolean isOK() {
				return IsOK;
			}
			public boolean isError() {
				return (!IsOK);
			}

			public MyE2_String get_ErrorMessage() {
				return ErrorMessage;
			}
			
			
		}
	
	
	
	
	
	public enum STATUS_CHK_4 implements IF_STATUS{
		 MAIL_CONTAINS_OTHER_ORIGINALS_ERR(					false,new MyE2_String("Das Original hängt an einer eMail-Sendungen zusammen mit weiteren Originalen (FEHLER!!!)"))
		,MAIL_CONTAINS_ATTACHMENTS_FROM_DIFF_RECORDS_ERR(	false,new MyE2_String("Die (Original)-eMail-Sendung besitzt Anlagen aus verschiedenen Basis-Records (FEHLER!!!)"))
		,MAIL_HAS_MORE_THAN_ONE_TARGETS_ERR(				false,new MyE2_String("Die (Original)-eMail-Sendung ist zu mehr als einem Ziel definiert  (FEHLER!!!)"))
		,MAIL_HAS_EMPTY_TARGET_LIST_ERR(					false,new MyE2_String("Die eMail-Sendung hat keine Zieladressen  (FEHLER!!!)"))
		,MAIL_HAS_FALSE_TARGET_ERR(							false,new MyE2_String("Die eMail-Sendung besitzt leere oder fehlerhafte Zieladressen  (FEHLER!!!)"))
		,MAIL_IS_CORRECT_UNSENT(							true,new MyE2_String("Die (Original)-eMail-Sendung ist zum Senden vorbereitet"))
		,MAIL_IS_CORRECT_SENT(								true,new MyE2_String("Die (Original)-eMail-Sendung ist bereits verschickt worden!"))
		,UNDEFINED_ERR(										false,new MyE2_String("undefinierter Status (FEHLER!!!)"))
		
		;
			
			private boolean 		IsOK = false;
			private MyE2_String 	ErrorMessage = null;
			private STATUS_CHK_4(boolean isOK, MyE2_String errorMessage) {
				this.IsOK = isOK;
				this.ErrorMessage = errorMessage;
			}
			public boolean isOK() {
				return IsOK;
			}
			public boolean isError() {
				return (!IsOK);
			}

			public MyE2_String get_ErrorMessage() {
				return ErrorMessage;
			}
			
			
		}


}
