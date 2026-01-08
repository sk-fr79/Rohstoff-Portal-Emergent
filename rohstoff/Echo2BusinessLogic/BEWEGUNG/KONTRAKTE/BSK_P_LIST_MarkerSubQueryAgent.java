package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSK_P_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	private MyDBToolBox oDB = bibALL.get_myDBToolBox();
	
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}

	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		MyE2_Label oLabLocked =   (MyE2_Label)oMAP.get__Comp(BSK__CONST.HASH_KEY_ANZEIGE_LOCKED_POS);
		
		// jetzt den locked-button verarzten
		if (oUsedResultMAP.get_FormatedValue("K_ABGESCHLOSSEN").equals("Y"))
			oLabLocked.setIcon(BSK__CONST.LABEL_POSITION_LOCKED);
		else
			oLabLocked.setIcon(BSK__CONST.LABEL_POSITION_UNLOCKED);
		
		
		
		
		
		boolean bDeleted = oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");
		
		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}
		
	}

}
