package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;

public class SF_sel_compares extends MyE2_SelectField {

	private HashMap<String, COMP>  		hm_correlation = new HashMap<String, COMP>();


	public SF_sel_compares() throws myException {
		super();
		this.setWidth(new Extent(SF_Filter_CONST.BREITE_COMPARESELEKTOR));
		Vector<String>  v_compare_stmt = new Vector<String>();
		
		for (COMP c: COMP.values()) {
			v_compare_stmt.add(c.ausdruck());
			this.hm_correlation.put(c.ausdruck(), c);
		}
		this.set_ListenInhalt(SF_enum_typ.get_dropdown_arrays(SF_enum_typ.BASIC_TYPE_PSEUDO_BOOLEAN, true), false);
		this.add_oActionAgent(new ownAction());
	}

	public COMP get_actualCompare() throws myException {
		return this.hm_correlation.get(this.get_ActualWert());
	}
	
	/**
	 * wenn ein checkbox-feld, dann ist nur = moeglich
	 * @param if_field
	 * @throws myException
	 */
	public void set_IF_field(IF_Field_ext if_field) throws myException {
		if (if_field==null) {
			this.set_ListenInhalt(SF_enum_typ.get_dropdown_arrays(SF_enum_typ.BASIC_TYPE_PSEUDO_BOOLEAN, true), false);
			this.setSelectedIndex(0);
			return;
		}
		MyMetaFieldDEF md = if_field.field.generate_MetaFieldDef();
		
		if (md.is_boolean_single_char()) {
			this.set_ListenInhalt(SF_enum_typ.get_dropdown_arrays(SF_enum_typ.BASIC_TYPE_PSEUDO_BOOLEAN, true), false);
			this.setSelectedIndex(1);
		} else if (md.get_bIsTextType()) {
			this.set_ListenInhalt(SF_enum_typ.get_dropdown_arrays(SF_enum_typ.BASIC_TYPE_TEXT,true), false);
			this.setSelectedIndex(0);
		} else if (md.get_bIsDateType()) {
			this.set_ListenInhalt(SF_enum_typ.get_dropdown_arrays(SF_enum_typ.BASIC_TYPE_DATUM,true), false);
			this.setSelectedIndex(0);
		} else if (md.get_bIsNumberTypeWithDecimals()||md.get_bIsNumberTypeWithOutDecimals()) {
			this.set_ListenInhalt(SF_enum_typ.get_dropdown_arrays(SF_enum_typ.BASIC_TYPE_NUMMER,true), false);
			this.setSelectedIndex(0);
		} else {
			throw new myException(this,"fieldtyp not useable ...");
		}
		
	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		}
	}

}
