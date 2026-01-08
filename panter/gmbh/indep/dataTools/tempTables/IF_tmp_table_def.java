package panter.gmbh.indep.dataTools.tempTables;

public interface IF_tmp_table_def {
	public String get_fieldname();
	public String fn();
	public String tnfn();
	public String fn(String alias);
	public String get_field_def();
}
