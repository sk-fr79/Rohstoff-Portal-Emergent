package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class SF_sel_Field extends MyE2_SelectField {

	private HashMap<String, IF_Field_ext>  	hm_correlation = new HashMap<String, IF_Field_ext>();
	private SF_orLine 						or_line = null;
	
	public SF_sel_Field(Vector<IF_Field_ext> fields, SF_orLine p_or_line) throws myException {
		super();
		this.setWidth(new Extent(SF_Filter_CONST.BREITE_FIELDSELEKTOR));

		this.or_line = p_or_line;
		Vector<String>  vfields = new Vector<String>();
		
		for (IF_Field_ext f: fields) {
			vfields.add(f.tnfn());
			this.hm_correlation.put(f.tnfn(), f);
		}
		
		Collections.sort(vfields);
		
		String[][] a_fields = new String[vfields.size()+1][2];
		a_fields[0][0]="-";
		a_fields[0][1]="";
		int i=1;
		for (String s: vfields) {
			a_fields[i][0]=s;
			a_fields[i][1]=s;
			i++;
		}
		this.set_ListenInhalt(a_fields, false);
		
		this.add_oActionAgent(new ownAction());
	}

	public IF_Field_ext get_actualField() throws myException {
		return this.hm_correlation.get(this.get_ActualWert());
	}
	
	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (S.isEmpty(SF_sel_Field.this.get_ActualWert())) {
				SF_sel_Field.this.or_line.set_IF_field(null);
			} else {
				SF_sel_Field.this.or_line.set_IF_field(SF_sel_Field.this.hm_correlation.get(SF_sel_Field.this.get_ActualWert()));
			}
		}
	}
	
	
	public void do_actionPassivSimulated() throws myException {
		new ownAction().ExecuteAgentCode(new ExecINFO(new MyActionEvent(new ActionEvent(this, "action")), true));
	}
}
