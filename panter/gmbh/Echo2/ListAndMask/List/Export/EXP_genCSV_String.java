package panter.gmbh.Echo2.ListAndMask.List.Export;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.exceptions.myException;

public class EXP_genCSV_String {
	
	private String csv = null;

	public EXP_genCSV_String(MyRECORD rec, IF_Field field) throws myException {
		super();

		this.csv = "";
		
		try {
			if (rec != null) {
				MyResultValue   rv = rec.get(field.fieldName());
				if (rv!=null) {
					MyMetaFieldDEF md = field.generate_MetaFieldDef();
					
					if (rv.get_oNativeDataObject()!=null) {
						if (md.get_bIsDateType()) {
							csv = rv.get_cDateTimeValueFormated();
						} else if (md.get_bIsNumberTypeWithOutDecimals()) {
							csv = ""+rv.getLongValue().longValue();
						} else if (md.get_bIsNumberTypeWithDecimals()) {
							csv = rec.get_FormatedValue(field.fieldName(), "");
						} else {
							csv = rec.get_FormatedValue(field.fieldName(), "");
							//anfuehrungszeichen innerhalb eines textes verdoppeln 
							this.csv=bibALL.ReplaceTeilString(this.csv, "\"", "\"\"");
							if (S.isFull(csv)) {
								csv = "\""+csv+"\"";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.csv= "\"ERROR\"";
		} 
		
		//sonderzeichen entfernen
		this.csv=bibALL.ReplaceTeilString(this.csv, "\n", "");
		this.csv=bibALL.ReplaceTeilString(this.csv, "\t", "");
		
	}
	
	public String csv() {
		return this.csv;
	}
	
	
	
	
}
