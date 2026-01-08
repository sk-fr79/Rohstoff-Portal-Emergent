package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class FUO_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).removeAll();
		MyE2_Label oLabelDel = new MyE2_Label(new MyE2_String("ID:"),MyE2_Label.STYLE_NORMAL_BOLD());
		((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).add(oLabelDel);

	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).removeAll();

		if (S.NN(oUsedResultMAP.get_UnFormatedValue("DELETED")).equals("Y"))
		{
			((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).removeAll();
			MyE2_Label oLabelDel = new MyE2_Label(new MyE2_String("Gelöscht"),MyE2_Label.STYLE_ERROR_BIGBIG());
			((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).add(oLabelDel);
			
		}
		else
		{
			((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).removeAll();
			MyE2_Label oLabelDel = new MyE2_Label(new MyE2_String("ID:"),MyE2_Label.STYLE_NORMAL_BOLD());
			((MyE2_Grid)oMAP.get__Comp(FUO__CONST.HASHKEY_MASK_SHOW_DELETED)).add(oLabelDel);
		}
	}

}
