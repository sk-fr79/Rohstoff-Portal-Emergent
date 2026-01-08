package panter.gmbh.Echo2.BasicInterfaces;


// DEMO fuer die konstanten-enums: XX ersetzen durch den Paket-praefix
public enum XX_ENUM_TEXTE implements IF_konstanten {
	
	
	TEST1("Bitte geben Sie einen Namen ein:",true)
	,FRAGE_MAHNSTUFE("Gewünschte Mahnstufe?",true)
	
	;

	
	private String s_base_text = null;
	boolean translate = true;
	
	
	

	/**
	 * @param name
	 * @param ordinal
	 */
	private XX_ENUM_TEXTE(String text, boolean  p_translate) {
		this.s_base_text=text;
		this.translate = p_translate;
				
	}

	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String base_text() {
		return this.s_base_text;
	}

	@Override
	public boolean isTrans() {
		return translate;
	}
	
	
	
}
