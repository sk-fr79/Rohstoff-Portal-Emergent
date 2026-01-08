package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyQueryBUILDER;
import panter.gmbh.indep.exceptions.myException;

public class BST__QueryBuilderComplete_TPA_POS extends MyQueryBUILDER
{

	public BST__QueryBuilderComplete_TPA_POS(String cID_VPOS_TPA_Unformated) throws myException
	{
		super("JT_VPOS_TPA", "ID_VPOS_TPA", null,bibALL.get_VectorAusArray(BST__CONST.DATENBANK_EXCLUDES_IN_POS), bibE2.get_CurrSession());
		this.add_Table("JT_VKOPF_TPA","ID_VKOPF_TPA",null, "A_",bibALL.get_VectorAusArray(BST__CONST.DATENBANK_EXCLUDES_IN_KOPF),"JT_VKOPF_TPA.ID_VKOPF_TPA=JT_VPOS_TPA.ID_VKOPF_TPA");
		this.add_Table("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",null,"B_",bibALL.get_VectorAusArray(BST__CONST.DATENBANK_EXCLUDES_IN_POSTPA),"JT_VPOS_TPA_FUHRE.ID_VPOS_TPA=JT_VPOS_TPA.ID_VPOS_TPA");
		this.add_Bedingung("JT_VPOS_TPA.ID_VPOS_TPA="+cID_VPOS_TPA_Unformated);
	}

}
