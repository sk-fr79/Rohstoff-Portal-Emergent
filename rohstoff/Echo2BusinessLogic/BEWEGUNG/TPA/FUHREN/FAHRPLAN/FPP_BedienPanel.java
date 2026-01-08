package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_DELETE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_EDIT_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_NEW_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_VIEW_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_DataSearch;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__ALL.CopyTyp;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_BUTTON_New_fast_and_furious;

public class FPP_BedienPanel extends MyE2_Column
{

	private FU_LIST_DataSearch  oDataSearch = null;
	
	
	public FPP_BedienPanel(FU_LIST_ModulContainer modulContainerLIST) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		E2_NavigationList   			oNaviList = 		modulContainerLIST.get_oNavList();
		FU_MASK_ModulContainer			oMaskFuhre = 		modulContainerLIST.get_oMaskFuhre();

		this.add(new E2_ComponentGroupHorizontal(0,	
													new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),
													new FU_LIST_BT_FUHRE_VIEW_MASK(oNaviList,oMaskFuhre),
													new FU_LIST_BT_FUHRE_NEW_MASK(oNaviList,oMaskFuhre,null,null),
													new FUS_BUTTON_New_fast_and_furious(oNaviList,true),
													new FU_LIST_BT_FUHRE_EDIT_MASK(oNaviList,oMaskFuhre),
													new FU_LIST_BT_FUHRE_DELETE(oNaviList),
													new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER),  //2015-05-28: up-downliad-button
													new FP_LIST_BT_SCHNELL_Erfassung_Fahrt(),
													new REP__POPUP_Button(modulContainerLIST.get_MODUL_IDENTIFIER(),oNaviList),
													this.oDataSearch= new FU_LIST_DataSearch(modulContainerLIST,modulContainerLIST.get_MODUL_IDENTIFIER()),
													new MyE2_Label(new MyE2_String("Multiplikator: ")),
													new FP_DROPDOWN_COPYS(oNaviList, CopyTyp.FAHRPLANPOOL_KOPIE),
													new FP_BUTTON_UMBUCHEN(oNaviList),
													new FPP_BUTTON_FAHRTENPLANER(oNaviList),
													E2_INSETS.I_0_0_5_0),
													new Insets(0,0,0,10));

		
	}


	public FU_LIST_DataSearch get_oSearchObject()
	{
		return oDataSearch;
	}

}
