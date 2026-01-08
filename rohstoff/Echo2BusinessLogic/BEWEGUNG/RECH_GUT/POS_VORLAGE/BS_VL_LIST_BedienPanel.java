package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public class BS_VL_LIST_BedienPanel extends MyE2_Row
{
	private BS_VL_LIST_BasicModuleContainer 			oModulContainerLIST = 	null;
	
	public BS_VL_LIST_BedienPanel(BS_VL_LIST_BasicModuleContainer modulContainerLIST) throws myException
	{
		super();
		this.oModulContainerLIST = modulContainerLIST;
		E2_NavigationList   			oNavList = this.oModulContainerLIST.get_oNaviList();
		
		BS_VL_MASK_ModulContainer		oMaskPosition = this.oModulContainerLIST.get_oModulContainerMASK();
		
		/*
		 * buttons zur steuerung
		 */
		//MyE2_PopUpMenue oPopUp =new MyE2_PopUpMenue(null,null, false);

		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNavList),E2_INSETS.I_2_2_2_2);
		
		
		this.add(new E2_ComponentGroupHorizontal(1,new MyE2_Label(new MyE2_String("MASKE: ")),
															new BS_VL_LIST_BT_VIEW_MASK(oNavList,oMaskPosition),
															new BS_VL_LIST_BT_EDIT_MASK(oNavList,oMaskPosition),
															new BS_VL_LIST_BT_NEW_MASK(oNavList,oMaskPosition),
															new BS_VL_LIST_BT_DELETE(oNavList),
															E2_INSETS.I_2_2_2_2),E2_INSETS.I_2_2_2_2);
		

		//this.add(oPopUp);
		this.add(new BS_VL_LIST_DATASEARCH(oNavList,this.oModulContainerLIST.get_MODUL_IDENTIFIER()));
		
	}
	
	
	
}
