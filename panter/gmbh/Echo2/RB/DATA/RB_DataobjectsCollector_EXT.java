package panter.gmbh.Echo2.RB.DATA;

import java.util.Vector;

import panter.gmbh.indep.dataTools.MySqlStatementBuilder;

/**
 * extender, der in allen RB_DataobjectsCollector - implementierungen vorhanden sein muss
 * @author martin
 *
 */
public class RB_DataobjectsCollector_EXT {
	
	private Vector<String>  v_statements_in_front = new Vector<>();
	private Vector<String>  v_statements_at_end = new Vector<>();
	
	
	public Vector<String> get_v_statements_in_front() {
		return v_statements_in_front;
	}
	
	
	public Vector<String> get_v_statements_at_end() {
		return v_statements_at_end;
	}
	
}
