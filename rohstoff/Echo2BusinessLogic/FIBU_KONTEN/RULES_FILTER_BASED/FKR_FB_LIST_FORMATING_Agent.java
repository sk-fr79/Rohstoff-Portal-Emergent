package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.ExpressionPrintVisitor;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.PrintVisitResult;
import panter.gmbh.indep.filter.RuleFactory;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN.FIBU_KONTEN_REGELN__CONST;

public class FKR_FB_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT {

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)
			throws myException {
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)
			throws myException {

		String idFilter = oUsedResultMAP.get_UnFormatedValue("ID_FILTER");
		// This is now where a filter can be toString()'ed
		String filterAsString = "";

		try {
			INodeElement rule = RuleFactory.getRule(Integer.parseInt(idFilter));
			if (rule != null) {
				PrintVisitResult result = (PrintVisitResult) rule
						.accept(new ExpressionPrintVisitor());
				filterAsString = result.getText();
			}
		} catch (Exception e) {
			filterAsString = "INVALID FILTER: "+e.getMessage();
			e.printStackTrace();
		}

		MyE2_Grid qGrid = (MyE2_Grid) oMAP.get__Comp(FKR_FB__CONST.COMPONENT_NAME_STRINGED_CONDITION);
		MyE2_Label lab = new MyE2_Label(filterAsString);
		lab.setLineWrap(true);
		qGrid.removeAll();
		qGrid.add(lab);

	}

}
