/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased
 * @author martin
 * @date 14.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;

import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;

/**
 * @author martin
 * @date 14.12.2018
 * definiert den typ des zusatzmoduls, der aktiviert wird:
 * Eine meldung oder eine simple Information
 */
public enum AI__TYP {
	
	INFO
	,MELDUNG
	
	;
	
	public E2_ButtonAUTHValidator getEditValidator() {
		if (this==AI__TYP.INFO) {
			return ENUM_VALIDATION.ADRESSE_INFO_EDIT.getValidator();
		} else {
			return ENUM_VALIDATION.ADRESSE_MELDUNG_EDIT.getValidator();
		}
	}
	

	
	public E2_ButtonAUTHValidator getViewValidator() {
		if (this==AI__TYP.INFO) {
			return ENUM_VALIDATION.ADRESSE_INFO_VIEW.getValidator();
		} else {
			return ENUM_VALIDATION.ADRESSE_MELDUNG_VIEW.getValidator();
		}
	}

	public E2_ButtonAUTHValidator getNewValidator() {

		if (this==AI__TYP.INFO) {
			return ENUM_VALIDATION.ADRESSE_INFO_NEW.getValidator();
		} else {
			return ENUM_VALIDATION.ADRESSE_MELDUNG_NEW.getValidator();
		}
	}

	public E2_ButtonAUTHValidator getDeleteValidator() {
		
		if (this==AI__TYP.INFO) {
			return ENUM_VALIDATION.ADRESSE_INFO_DELETE.getValidator();
		} else {
			return ENUM_VALIDATION.ADRESSE_MELDUNG_DELETE.getValidator();
		}

	}

	public E2_ButtonAUTHValidator getAttachValidator() {
		if (this==AI__TYP.INFO) {
			return ENUM_VALIDATION.ADRESSE_INFO_ATTACHMENT.getValidator();
		} else {
			return ENUM_VALIDATION.ADRESSE_MELDUNG_ATTACHMENT.getValidator();
		}
	}

	
	
}
