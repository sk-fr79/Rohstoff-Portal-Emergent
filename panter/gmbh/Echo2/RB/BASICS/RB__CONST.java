package panter.gmbh.Echo2.RB.BASICS;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;

public class RB__CONST {
	
	public enum MASK_STATUS {
		EDIT("EDIT",		false),
		NEW("NEW",			true),
		NEW_COPY("NEW_COPY",true),
		VIEW("VIEW",		false);
		
		private String 	VALUE=null;
		private boolean statusNew = false;
		
		MASK_STATUS(String cVALUE,boolean statusIsNew) {
			this.VALUE = cVALUE;
			this.statusNew = statusIsNew;
		}
		

		public String get_VALUE() {
			return VALUE;
		}

		public boolean isStatusNew() {
			return statusNew;
		}
		
		public boolean isStatusEdit() {
			return this.VALUE.equals(MASK_STATUS.EDIT.VALUE);
		}
		
		public boolean isStatusView() {
			return this.VALUE.equals(MASK_STATUS.VIEW.VALUE);
		}
		
		public boolean isStatusCanBeSaved() {
			return this.VALUE.equals(MASK_STATUS.EDIT.VALUE)||this.VALUE.equals(MASK_STATUS.NEW.VALUE)||this.VALUE.equals(MASK_STATUS.NEW_COPY.VALUE);
		}
		
		
		
		public String toString() {
			return this.VALUE;
		}
		
		public boolean get_bMustClearMask() {
			return this.VALUE.equals(MASK_STATUS.NEW.VALUE);
		}
		
		public String get_oldStatus_E2_ComponentMAP() throws myException {
			if 			(this.VALUE.equals(MASK_STATUS.EDIT.VALUE)) {
				return E2_ComponentMAP.STATUS_EDIT;
			} else if 	(this.VALUE.equals(MASK_STATUS.NEW.VALUE)){
				return E2_ComponentMAP.STATUS_NEW_EMPTY;
			} else if 	(this.VALUE.equals(MASK_STATUS.NEW_COPY.VALUE)){
				return E2_ComponentMAP.STATUS_NEW_COPY;
			} else if 	(this.VALUE.equals(MASK_STATUS.VIEW.VALUE)){
				return E2_ComponentMAP.STATUS_VIEW;
			} else {
				throw new myException("RB__CONST:MASK_STATUS:get_oldStatus_E2_ComponentMAP: No Status");
			}
		}
		
		
		
		public E2_ResourceIcon get_icon() {
			E2_ResourceIcon ri = E2_ResourceIcon.get_RI("empty.png");
			switch (this) {
				case EDIT: 		ri = E2_ResourceIcon.get_RI("edit.png"); break;
				case NEW: 		ri = E2_ResourceIcon.get_RI("new.png"); break;
				case NEW_COPY: 	ri = E2_ResourceIcon.get_RI("copy.png"); break;
				case VIEW: 		ri = E2_ResourceIcon.get_RI("view.png"); break;
			}
			return ri;
		}
		
		
		public MyE2_String get_statusBeschreibung() {
			MyE2_String  c = null;
			
			switch (this) { 
				case EDIT: 		c=new MyE2_String("Status der Maske: Bearbeiten"); break;
				case VIEW:		c=new MyE2_String("Status der Maske: Anzeige"); break;
				case NEW: 		c=new MyE2_String("Status der Maske: Neuerfassung"); break;
				case NEW_COPY: 	c=new MyE2_String("Status der Maske: Kopie"); break;
			}
			
			return c;
		}
		
		
	}
	
	
	
	
	
	public enum ALIGN_HORIZONTAL {
		LEFT(Alignment.LEFT),
		CENTER(Alignment.CENTER),
		RIGHT(Alignment.RIGHT);
		
		private int EchoConst = Alignment.LEFT;
		
		private ALIGN_HORIZONTAL(int echoConst) {
			EchoConst = echoConst;
		}
		public int get_EchoConst() {
			return EchoConst;
		}
		
	}
	
	public enum ALIGN_VERTICAL {
		TOP(Alignment.TOP),
		MID(Alignment.CENTER),
		BOTTOM(Alignment.BOTTOM),;
		
		
		private int EchoConst = Alignment.LEFT;
		
		private ALIGN_VERTICAL(int echoConst) {
			EchoConst = echoConst;
		}
		public int get_EchoConst() {
			return EchoConst;
		}
	}
	
	
	

	/**
	 * varianten der nutzung von set_and_valid-aufrufen
	 * @author martin
	 *
	 */
	public enum VALID_TYPE {
		USE_IN_MASK_KLICK_ACTION,
		USE_IN_MASK_LOAD_ACTION,
		USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION,
		USE_IN_MASK_VALID_ACTION,
		USE_IN_MASK_UNBOUND_KLICK_ACTION
	}
	
	
	
	
	
}
