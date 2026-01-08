package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.ExpressionPrintVisitor;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.PrintVisitResult;
import panter.gmbh.indep.filter.RuleFactory;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.FKR_FB__CONST;

/**
 * Formats the list, such as that entries that do not have a valid Datev
 * account number (= "0000") will be printed with red background.
 * @author nils
 *
 */
public class FIBU_EXPORT_LIST_FormattingAgent extends
		XX_ComponentMAP_SubqueryAGENT {
	
	private static String GEGENKONTO = "GEGENKONTO";

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)
			throws myException {
	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)
			throws myException {

		String gegenkonto = oUsedResultMAP.get_UnFormatedValue(GEGENKONTO);

		MyE2_DB_Label_INROW lab = (MyE2_DB_Label_INROW) oMAP.get__Comp(GEGENKONTO);
		if (gegenkonto.equals("0000")) {
			lab.setBackground(new Color(255, 0, 0));
		}
	}

}