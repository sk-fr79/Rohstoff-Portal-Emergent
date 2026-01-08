package panter.gmbh.indep.Replacer;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.exceptions.myException;

public class RP_GeneratorReplacelistFromRecord {
	
	private Vector<MyRECORD>   								v_records_to_generate = new Vector<MyRECORD>();
	
	private LinkedHashMap<MyRECORD, Vector<MyResultValue>>  hm_resultValues = new LinkedHashMap<>();
	private LinkedHashMap<String, String>   				hm_replacement_terms = new LinkedHashMap<String, String>();
	private LinkedHashMap<MyRECORD, String>   				hm_tableNameAddons = new LinkedHashMap<MyRECORD, String>();

	public RP_GeneratorReplacelistFromRecord() throws myException {
		super();
	}

	public RP_GeneratorReplacelistFromRecord _a(MyRECORD p_record_to_generate) throws myException {
		if (p_record_to_generate!=null) {
			this.v_records_to_generate.add(p_record_to_generate);
		}
		this.hm_tableNameAddons.put(p_record_to_generate, "");
		
		this.gen();
		return this;
	}
	
	public RP_GeneratorReplacelistFromRecord _a(MyRECORD p_record_to_generate, String tab_addon) throws myException {
		if (p_record_to_generate!=null) {
			this.v_records_to_generate.add(p_record_to_generate);
		}
		this.hm_tableNameAddons.put(p_record_to_generate, tab_addon);
		
		this.gen();
		return this;
	}

	
	private void gen() throws myException {
		this.hm_resultValues.clear();
		this.hm_replacement_terms.clear();
		this.fill_and_sort_result_values();
		this.fill_hm_replacement_terms();
	}
	
	
	private void fill_and_sort_result_values() throws myException {
		
		for (MyRECORD rec: this.v_records_to_generate) {
			Vector<MyResultValue> v_resultValue = new Vector<>(rec.values());
			
			String table_name = S.NN(rec.get_TABLENAME());

			v_resultValue.sort(new Comparator<MyResultValue>() {
				@Override
				public int compare(MyResultValue o1, MyResultValue o2) {
					return ((table_name+o1.get_cFieldLABEL()).compareTo((table_name+o2.get_cFieldLABEL())));
				}
			});
			
			this.hm_resultValues.put(rec, v_resultValue);
		}
	}
	

	
	private void fill_hm_replacement_terms() throws myException {
		
		for (MyRECORD rec: this.v_records_to_generate) {
			Vector<MyResultValue>  v_results = this.hm_resultValues.get(rec);
			String table_name = S.NN(rec.get_TABLENAME());
			if (S.isFull(table_name)) {
				table_name=table_name.substring(3);
			}
			
			if (S.isFull(this.hm_tableNameAddons.get(rec))) {
				table_name=table_name+"."+this.hm_tableNameAddons.get(rec);
			}
			
			if (S.isFull(table_name)) {
				table_name=table_name+".";
			}
			
			
			for (MyResultValue rv: v_results) {
				String field_name = rv.get_MetaFieldDef().get_FieldName();
				
				if 			(rv.get_MetaFieldDef().is_boolean_single_char()) {
					this.hm_replacement_terms.put("#DBFIELD:"+table_name+field_name+"#", 		rec.ufs(field_name, "N"));
				} else if 	(rv.get_MetaFieldDef().get_bIsDateType()) {
					this.hm_replacement_terms.put("#DBFIELD:UF:"+table_name+field_name+"#", 	rec.ufs(field_name, ""));
					this.hm_replacement_terms.put("#DBFIELD:F:"+table_name+field_name+"#", 		rec.fs(field_name, ""));
					this.hm_replacement_terms.put("#DBFIELD:LONG:"+table_name+field_name+"#", 	S.NN(rec.get(field_name).get_cDateTimeValueFormated()));
				} else if   (rv.get_MetaFieldDef().get_bIsNumberTypeWithOutDecimals()) {
					this.hm_replacement_terms.put("#DBFIELD:UF:"+table_name+field_name+"#", 	rec.ufs(field_name, ""));
					this.hm_replacement_terms.put("#DBFIELD:F:"+table_name+field_name+"#", 		rec.fs(field_name, ""));
				} else if   (rv.get_MetaFieldDef().get_bIsNumberTypeWithDecimals()) {
					this.hm_replacement_terms.put("#DBFIELD:UF:"+table_name+field_name+"#", 	rec.ufs(field_name, ""));
					this.hm_replacement_terms.put("#DBFIELD:F:"+table_name+field_name+"#", 		rec.fs(field_name, ""));
				} else {
					this.hm_replacement_terms.put("#DBFIELD:"+table_name+field_name+"#", 		rec.ufs(field_name, ""));
				}
			}
		}
		
		
		//jetzt sicherstellen, dass kein benutzerpasswort hinterlegt wird
		LinkedHashMap<String, String> hm_temp = new LinkedHashMap<>();
		for (Map.Entry<String, String>  entry: this.hm_replacement_terms.entrySet() ) {
			if (!(entry.getKey().toUpperCase().contains("PASSWORT") || entry.getKey().toUpperCase().contains("PASSWORD") )) {
				hm_temp.put(entry.getKey(), entry.getValue());
			}
		}


		this.hm_replacement_terms.clear();
		this.hm_replacement_terms.putAll(hm_temp);
		
	}
	
	
	
	
	public LinkedHashMap<String, String> get_hm_replacement_terms() {
		return hm_replacement_terms;
	}
	
	
	public void write_to_console() {

			DEBUG.System_println("");
			DEBUG.System_println("");
			DEBUG.System_println("Datenbank-Felder "+S.NN(this.v_records_to_generate.get(0).get_TABLE_NAME(),"<table>")+" und weitere  -----------------------------------------");
			DEBUG.System_print(hm_replacement_terms);
			DEBUG.System_println("");
			DEBUG.System_println("");
		
	}
	
	
}