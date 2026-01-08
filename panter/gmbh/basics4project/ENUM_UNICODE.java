package panter.gmbh.basics4project;

public enum ENUM_UNICODE {
	EUR("\u20AC")
	;
	
	private String code_seq = null;
	
	private ENUM_UNICODE(String p_code) {
		this.code_seq = p_code;
	}

	public String getCode() {
		return code_seq;
	}
	
	
	
}
