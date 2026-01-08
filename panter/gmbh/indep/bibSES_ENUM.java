package panter.gmbh.indep;

public enum bibSES_ENUM {

//	public enum PARAMETES_IN_CREATE_SESSION {
		 ID_MANDANT()
		,USER_KUERZEL()
		,DEBUG_FLAGS()          										//2015-05-13, martin
		,ALLOWEDBUTTONS(true,"Berechtigungen")       					//2015-05-21, martin 
		,SCANNERLISTE(true,"Scanner")         							//2015-05-21, martin
		,MARK_COLOR_ADRESSCLASS(true,"Farbmarker in Adressliste")  		//2015-08-28, martin
		,SAVE_LIST_EIGENE_LAGER_4_SELECT_IN_SEARCHFIELD(true,"Speichern der immer gleichen eigenene Lagerliste des Mandanten")					
		,
		
		;
		
		private String  userText = null; 
		private boolean is_resetted = false;
		
		private bibSES_ENUM() {
		}
		private bibSES_ENUM(boolean resetted, String user_text) {
			this.is_resetted = resetted;
			this.userText = user_text;
		}
		public boolean is_Resetted() {
			return is_resetted;
		}
		
		public String user_text() {
			return S.NN(this.userText,this.name());
		}
		
		
		
		
//	}
	

	
	
	
	
	
}
