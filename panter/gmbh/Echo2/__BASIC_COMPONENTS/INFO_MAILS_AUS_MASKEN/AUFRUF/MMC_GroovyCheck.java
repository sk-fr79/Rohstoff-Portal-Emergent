package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibGroovy;
import panter.gmbh.indep.exceptions.myException;

public class MMC_GroovyCheck {
	
	private String   				cGroovyCode= null;
	private boolean  				bErlaubnisErteilt = true;
	private MyE2_String  			cErrorMessage = null;
	

	public MMC_GroovyCheck(String c_GroovyCode) throws myException {
		super();
		this.cGroovyCode = 	c_GroovyCode;
		
		HashMap<String, String>  hmPruefung = new HashMap<String, String>();
		hmPruefung.put(MMC__CONST.KEY_RETURNVALUE, "");
		hmPruefung.put(MMC__CONST.KEY_ERRORCODE, "");

		
		if (S.isFull(this.cGroovyCode)) {
			
			
			// jetzt die auswertung starten, um die antworten aus dem groovy-umfeld zu bekommen, danach stehen in den hashfeldern die ergebnisse fuer
			// returnvalue und errorcode
			bibGroovy.interpretGroovySingleStringReturn(c_GroovyCode, hmPruefung);
			
			if (S.NN(hmPruefung.get(MMC__CONST.KEY_RETURNVALUE)).equals("Y")) {
				this.bErlaubnisErteilt = true;
			} else {
				this.bErlaubnisErteilt = false;
			}
			if (S.isFull(hmPruefung.get(MMC__CONST.KEY_ERRORCODE))) {
				this.cErrorMessage = new MyE2_String(hmPruefung.get(MMC__CONST.KEY_ERRORCODE));
			} else {
				this.cErrorMessage = new MyE2_String("Prüfscript blockiert Ausführung");
			}
		}
	}


	public boolean get_bErlaubnisErteilt() {
		return bErlaubnisErteilt;
	}
	

	public MyE2_String get_cFehlermeldung() {
		return this.cErrorMessage;
	}
	
	
	
}
