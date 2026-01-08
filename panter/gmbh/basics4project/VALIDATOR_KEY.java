package panter.gmbh.basics4project;

import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;


/**
 * definiert einen button-key, dieser kann einem modul zugeordnet sein oder ohne Modulzuordnung definiert werden
 * @author martin
 *
 */
public enum VALIDATOR_KEY {
	
	EDIT_INFOS(MODUL.NAME_MODUL_FIRMENSTAMM_LIST)
	,DATUMSAENDERUNG_ANGEBOTE(MODUL.MODUL_KENNER_PROGRAMM_WIDE)
	;
	
	private MODUL modul = null;
	
	/**
	 * 
	 * @param p_modul (das modul, zu dem der button gehoert)
	 */
	private VALIDATOR_KEY(MODUL  p_modul) {
		this.modul = p_modul;
	}
	
	private VALIDATOR_KEY() {
		this.modul = null;
	}
	
	
	
	public XX_ActionValidator get_authValidator() {
		if (this.modul==null) {
			return new E2_ButtonAUTHValidator(this.name(),this.modul.get_callKey());
		} else {
			return new E2_ButtonAUTHValidator_AUTO(this.name());
		}
	}
	
}
