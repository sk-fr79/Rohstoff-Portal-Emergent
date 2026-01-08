package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		BAM_IMPORT_SearchFieldFuhre oSearch = (BAM_IMPORT_SearchFieldFuhre) oMAP.get__Comp(BAM_IMPORT_Const.SEARCHFIELD_FUHRE);
		oSearch.clean();
		
		String sIDFuhre = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		String sIDFuhrenOrt =oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT");
		
		oSearch.set_Fuhre(sIDFuhre, sIDFuhrenOrt);
		

		
		
		String sIDWiegekarte =oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_WIEGEKARTE");
		
		boolean bEnable = false;
		MyE2_DB_TextField tfIDWiegekarte = (MyE2_DB_TextField)oMAP.get__Comp("ID_WIEGEKARTE");
		MyE2_Button  btnConnectWK = (MyE2_Button) oMAP.get__Comp(BAM_IMPORT_Const.BUTTON_CONNECT_WIEGEKARTE);
		MyE2_Button  btnDisonnectWK = (MyE2_Button) oMAP.get__Comp(BAM_IMPORT_Const.BUTTON_DISCONNECT_WIEGEKARTE);
		if (bibALL.isEmpty(sIDWiegekarte)){
			bEnable = true;
		} else {
			bEnable = false;
		}
		tfIDWiegekarte.EXT().set_bDisabledFromInteractive(!bEnable);
		btnConnectWK.EXT().set_bDisabledFromInteractive(!bEnable);
		btnDisonnectWK.EXT().set_bDisabledFromInteractive(bEnable);
	}

}
