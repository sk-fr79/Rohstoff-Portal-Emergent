package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;

public enum SF_enum_typ {
	
	BASIC_TYPE_TEXT(DB_META.BASIC_TYPE_TEXT,"UPPER(#FIELD#)","UPPER(#VALUE#)")
	,BASIC_TYPE_DATUM(DB_META.BASIC_TYPE_DATUM,"TO_CHAR(#FIELD#,'YYYY-MM-DD')","#VALUE#")
	,BASIC_TYPE_NUMMER(DB_META.BASIC_TYPE_NUMMER,"#FIELD#","#VALUE#")
	,BASIC_TYPE_PSEUDO_BOOLEAN(DB_META.BASIC_TYPE_TEXT,"NVL(UPPER(#FIELD#),'N')","UPPER(#VALUE#)")
	;
	
	private String db_meta_key = null;
	private String field_ausdruck_mask = null;
	private String value_ausdruck_mask = null;
	
	private SF_enum_typ(String p_db_meta_key, String p_field_ausdruck_mask, String p_value_ausdruck_mask) {
		this.db_meta_key = p_db_meta_key;
		this.field_ausdruck_mask = p_field_ausdruck_mask;
		this.value_ausdruck_mask = p_value_ausdruck_mask;
	}

	public String get_db_meta_key() {
		return this.db_meta_key;
	}

	public String get_field_ausdruck_mask() {
		return this.field_ausdruck_mask;
	}

	public String get_value_ausdruck_mask() {
		return this.value_ausdruck_mask;
	}

	
	public static String[][] get_dropdown_arrays(SF_enum_typ typ, boolean b_empty_in_front) {
		String[][] arr = null;
		switch (typ) {
		case BASIC_TYPE_TEXT: 
			arr = new String[11][2];
			arr[0][0]="-";
			arr[0][1]="";
			arr[1][0]=COMP.EQ.get_dd_text();
			arr[1][1]=COMP.EQ.ausdruck();
			arr[2][0]=COMP.NOT_EQ.get_dd_text();
			arr[2][1]=COMP.NOT_EQ.ausdruck();
			arr[3][0]=COMP.GE.get_dd_text();
			arr[3][1]=COMP.GE.ausdruck();
			arr[4][0]=COMP.GT.get_dd_text();
			arr[4][1]=COMP.GT.ausdruck();
			arr[5][0]=COMP.LE.get_dd_text();
			arr[5][1]=COMP.LE.ausdruck();
			arr[6][0]=COMP.LT.get_dd_text();
			arr[6][1]=COMP.LT.ausdruck();
			arr[7][0]=COMP.IN.get_dd_text();
			arr[7][1]=COMP.IN.ausdruck();
			arr[8][0]=COMP.NOT_IN.get_dd_text();
			arr[8][1]=COMP.NOT_IN.ausdruck();
			arr[9][0]=COMP.ISNULL.get_dd_text();
			arr[9][1]=COMP.ISNULL.ausdruck();
			arr[10][0]=COMP.ISNOTNULL.get_dd_text();
			arr[10][1]=COMP.ISNOTNULL.ausdruck();
			
			
			
			break;
		
		case BASIC_TYPE_DATUM: 
			arr = new String[9][2];
			arr[0][0]="-";
			arr[0][1]="";
			arr[1][0]=COMP.EQ.get_dd_text();
			arr[1][1]=COMP.EQ.ausdruck();
			arr[2][0]=COMP.NOT_EQ.get_dd_text();
			arr[2][1]=COMP.NOT_EQ.ausdruck();
			arr[3][0]=COMP.GE.get_dd_text();
			arr[3][1]=COMP.GE.ausdruck();
			arr[4][0]=COMP.GT.get_dd_text();
			arr[4][1]=COMP.GT.ausdruck();
			arr[5][0]=COMP.LE.get_dd_text();
			arr[5][1]=COMP.LE.ausdruck();
			arr[6][0]=COMP.LT.get_dd_text();
			arr[6][1]=COMP.LT.ausdruck();
			arr[7][0]=COMP.ISNULL.get_dd_text();
			arr[7][1]=COMP.ISNULL.ausdruck();
			arr[8][0]=COMP.ISNOTNULL.get_dd_text();
			arr[8][1]=COMP.ISNOTNULL.ausdruck();
			
			break;

		case BASIC_TYPE_NUMMER: 
			arr = new String[11][2];
			arr[0][0]="-";
			arr[0][1]="";
			
			arr[1][0]=COMP.EQ.get_dd_text();
			arr[1][1]=COMP.EQ.ausdruck();
			
			arr[2][0]=COMP.NOT_EQ.get_dd_text();
			arr[2][1]=COMP.NOT_EQ.ausdruck();
			
			arr[3][0]=COMP.GE.get_dd_text();
			arr[3][1]=COMP.GE.ausdruck();
			
			arr[4][0]=COMP.GT.get_dd_text();
			arr[4][1]=COMP.GT.ausdruck();
			
			arr[5][0]=COMP.LE.get_dd_text();
			arr[5][1]=COMP.LE.ausdruck();
			
			arr[6][0]=COMP.LT.get_dd_text();
			arr[6][1]=COMP.LT.ausdruck();
			
			arr[7][0]=COMP.IN.get_dd_text();
			arr[7][1]=COMP.IN.ausdruck();
			
			arr[8][0]=COMP.NOT_IN.get_dd_text();
			arr[8][1]=COMP.NOT_IN.ausdruck();
			
			arr[9][0]=COMP.ISNULL.get_dd_text();
			arr[9][1]=COMP.ISNULL.ausdruck();
			
			arr[10][0]=COMP.ISNOTNULL.get_dd_text();
			arr[10][1]=COMP.ISNOTNULL.ausdruck();
			
			
			break;
	
		case BASIC_TYPE_PSEUDO_BOOLEAN: 
			arr = new String[4][2];
			arr[0][0]="-";
			arr[0][1]="";
			arr[1][0]=COMP.EQ.get_dd_text();
			arr[1][1]=COMP.EQ.ausdruck();
			arr[2][0]=COMP.ISNULL.get_dd_text();
			arr[2][1]=COMP.ISNULL.ausdruck();
			arr[3][0]=COMP.ISNOTNULL.get_dd_text();
			arr[3][1]=COMP.ISNOTNULL.ausdruck();
			
			break;
		}
			
		
		if (!b_empty_in_front) {
			String[][] arr2 = new String[arr.length-1][2];
			for (int i=1;i<arr.length;i++){
				arr2[i-1][0]=arr[i][0];
				arr2[i-1][1]=arr[i][1];
			}
			return arr2;
		}
		
		return arr;
	}
	
	public static SF_enum_typ  indentify_typ(IF_Field_ext if_field) throws myException {
		MyMetaFieldDEF md = if_field.field.generate_MetaFieldDef();
		
		if (md.is_boolean_single_char()) {
			return SF_enum_typ.BASIC_TYPE_PSEUDO_BOOLEAN;
		} else if (md.get_bIsTextType()) {
			return SF_enum_typ.BASIC_TYPE_TEXT;
		} else if (md.get_bIsDateType()) {
			return SF_enum_typ.BASIC_TYPE_DATUM;
		} else if (md.get_bIsNumberTypeWithDecimals()||md.get_bIsNumberTypeWithOutDecimals()) {
			return SF_enum_typ.BASIC_TYPE_NUMMER;
		} else {
			throw new myException("SF_enum_typ: fieldtyp indentify_typ not possible ...");
		}
	}

	
	public static String field_term(IF_Field_ext if_field) throws myException {
		SF_enum_typ typ = SF_enum_typ.indentify_typ(if_field);
		
		String maske = typ.get_field_ausdruck_mask();
		
		return bibALL.ReplaceTeilString(maske, "#FIELD#", if_field.tnfn());
	}
	
	public static String value_term(IF_Field_ext if_field, String value) throws myException {
		SF_enum_typ typ = SF_enum_typ.indentify_typ(if_field);
		
		String maske = typ.get_value_ausdruck_mask();
		
		return bibALL.ReplaceTeilString(maske, "#VALUE#", value);
	}
}
