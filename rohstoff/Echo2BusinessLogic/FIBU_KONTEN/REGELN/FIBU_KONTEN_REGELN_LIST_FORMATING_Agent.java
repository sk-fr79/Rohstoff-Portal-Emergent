package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

import java.sql.SQLException;

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

public class FIBU_KONTEN_REGELN_LIST_FORMATING_Agent extends
		XX_ComponentMAP_SubqueryAGENT {

	/*
	 * subquery-agent, füllt die infofelder wieviele mitarbeiter/Infos usw eine
	 * adresse hat
	 */
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)
			throws myException {

	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)
			throws myException {
		// TODO: Hier können die Filterregeln zusammengefasst werden (als
		// String)

		String idFilter = oUsedResultMAP.get_UnFormatedValue("ID_FILTER");
		INodeElement rule = null;
		String ruleResult = "";
		try {
			rule = RuleFactory.getRule(Integer.parseInt(idFilter));
		} catch (NumberFormatException e) {
			ruleResult = "Could not create rule: "+e.getMessage();
			e.printStackTrace();
		} catch (SQLException e) {
			ruleResult = "Could not create rule: "+e.getMessage();
			e.printStackTrace();
		}

		if (rule != null) {
			PrintVisitResult result = (PrintVisitResult) rule
					.accept(new ExpressionPrintVisitor());

			System.out.println("Formatting Filter: " + idFilter + " to "
					+ result.getText());
			ruleResult = result.getText();


		} 
		
		MyE2_Grid qGrid = (MyE2_Grid) oMAP
				.get__Comp(FIBU_KONTEN_REGELN__CONST.LIST_COMPONENT_QUELLEN_LAND_TABLE);
		MyE2_Label lab = new MyE2_Label(ruleResult);
		lab.setLineWrap(true);
		qGrid.add(lab);

	}
}