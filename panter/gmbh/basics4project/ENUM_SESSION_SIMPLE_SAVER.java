package panter.gmbh.basics4project;

import panter.gmbh.indep.bibSES;

public enum ENUM_SESSION_SIMPLE_SAVER {

	WIEGEKARTEN_DRUCK
	
	;
	
	private static String keySavePart = "5a8707ee-ba47-11e8-96f8-529269fb1459";
	
	
	
	public String getSaveKey() {
		return ENUM_SESSION_SIMPLE_SAVER.keySavePart+"@"+this.name();
	}
	

	/**
	 * 
	 * @return s stored object or null
	 */
	public Object getValue() {
		return bibSES.getSessionValue(this.getSaveKey());
	}
	
	
	public void setValue(Object o) {
		bibSES.setSessionValue(this.getSaveKey(), o);
	}
	
	
}
