package rohstoff.Echo2BusinessLogic.BAM_IMPORT;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_Button_Popup_Image_Viewer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		// Button für die Anhänge
		MyE2_Row_EveryTimeEnabled oRowAnhaenge  = (MyE2_Row_EveryTimeEnabled) oMAP.get__Comp_From_RealComponents(BAM_IMPORT_Const.ROW_SHOW_ANHANG_LISTE);
		if (oRowAnhaenge != null){
			oRowAnhaenge.addAfterRemoveAll(new E2_ButtonUpDown_NavigationList_to_Archiv( 
																	oMAP, 
																	oMAP.get_oNavigationList_This_Belongs_to().get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE(),
																	Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG.get_DB_WERT()) , 
																	E2_INSETS.I_2_0_2_0);
			
			String sTable = oMAP.get_oSQLFieldMAP().get_cMAIN_TABLE();
					
			IMG_Button_Popup_Image_Viewer oButtonDia = new IMG_Button_Popup_Image_Viewer(
																oUsedResultMAP.get_oSQLFieldMAP().get_cMAIN_TABLE(), 
																oUsedResultMAP.get_cUNFormatedROW_ID()
																);
			oButtonDia.setToolTipText(new MyString("Anzeige der Bildanhänge in einem Popup-Fenster.").CTrans());
			oRowAnhaenge.add(oButtonDia , E2_INSETS.I_2_0_2_0);
		}
		
		
		// Button für die Fuhren
		MyE2_Row_EveryTimeEnabled rowJumperFuhre = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp_From_RealComponents(BAM_IMPORT_Const.ROW_SHOW_FUHRE);
		rowJumperFuhre.removeAll();

		String idFuhre = oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		if (!bibALL.isEmpty(idFuhre)){
			// button fürs Öffnen der fuhre einbauen
			BTN_BAM_IMPORT__JUMP_TO_FUHRE oNavigator = new BTN_BAM_IMPORT__JUMP_TO_FUHRE(bibALL.get_Vector(idFuhre), null);
			rowJumperFuhre.add(oNavigator,E2_INSETS.I_3_0_0_0);
		}
		
		
		MyE2_Row_EveryTimeEnabled rowJumperWK = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp_From_RealComponents(BAM_IMPORT_Const.ROW_SHOW_WIEGEKARTE);
		rowJumperWK.removeAll();
		
		String idWiegekarte = oUsedResultMAP.get_UnFormatedValue("ID_WIEGEKARTE");
		if (!bibALL.isEmpty(idWiegekarte)){
			// button fürs Öffnen der fuhre einbauen
			BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE oNavigatorWK = new BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE(bibALL.get_Vector(idWiegekarte), null);
			rowJumperWK.add(oNavigatorWK,E2_INSETS.I_3_0_0_0);
		}
		
	}
	
	
	

	


}
