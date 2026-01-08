package panter.gmbh.indep.dataTools.tempTables;

public enum TT_LOG_BEWEGUNG_VEKTOR implements IF_tmp_table_def {

	ID_BEWEGUNG_VEKTOR("NUMBER(10)")
	,ID_ATOM_START("NUMBER(10)")
	,ID_ATOM_ZIEL("NUMBER(10)")
	,RECHPOS("NUMBER(10)")
	,GUTPOS("NUMBER(10)")
	,DATUM("DATE")
	;

	private String f_defblock = null;
	private String f_fieldname = null;
	
	private TT_LOG_BEWEGUNG_VEKTOR(String p_defblock) {
		this.f_defblock = p_defblock;
		this.f_fieldname = this.name().toUpperCase();
	}
	
	
	@Override
	public String get_fieldname() {
		return this.f_fieldname;
	}

	
	@Override
	public String get_field_def() {
		return this.f_defblock;
	}


	@Override
	public String fn() {
		return this.f_fieldname;
	}


	@Override
	public String tnfn() {
		return "TT_LOG_BEWEGUNG_VEKTOR."+this.f_fieldname;
	}


	@Override
	public String fn(String alias) {
		return  alias+"."+this.f_fieldname;
	}

	
	
}
