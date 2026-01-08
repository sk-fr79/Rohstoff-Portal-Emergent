package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import panter.gmbh.indep.exceptions.myException;

public interface IF_definable_all_or_relevant {
	public void select_all_data() throws myException;
	public void select_relevant_data() throws myException;
}
