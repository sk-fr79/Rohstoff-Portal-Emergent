package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class __FUHREN_MASKEN_OEFFNER 
{

	public __FUHREN_MASKEN_OEFFNER(	FU_MASK_ModulContainer 	oFuMaskContainer, 
									E2_NavigationList  		oNaviList, 
									String 					cID_Fuhre,
									String                  cTextAlsMaskenTitel,
									String                  cTextFuerMessage,
									boolean                 bAdd_SaveAndMoreButton) throws myException 
	{
		super();
		
		E2_ComponentGroupHorizontal oCompGroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_10_2);
		maskButtonSaveMask    oButtonSave = new maskButtonSaveMask(
															oFuMaskContainer, 
															new E2_SaveMaskStandard(oFuMaskContainer, oNaviList), 
															oNaviList, null);
		MaskButtonCancelMaskSTANDARD  oButtonCancel = new MaskButtonCancelMaskSTANDARD(oFuMaskContainer) ;
		MyE2_Button oButton_Print_und_Mail = new FU_MASK_BT_PRINT_MAIL_BELEG(oFuMaskContainer,false);

		FU__MASK_Button_SaveFromMask_And_FollowAction  oButtonSaveAndMore = new FU__MASK_Button_SaveFromMask_And_FollowAction(oFuMaskContainer, oNaviList);
		
		oCompGroup.add(oButtonSave);
		oCompGroup.add(oButtonCancel);
		oCompGroup.add(oButton_Print_und_Mail);
		
		if (bAdd_SaveAndMoreButton)
		{
			oCompGroup.add(oButtonSaveAndMore);
		}
		
		
		E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oFuMaskContainer.get_vCombinedComponentMAPs();
		
		oFuMaskContainer.get_oRowForButtons().removeAll();
		oFuMaskContainer.get_oRowForButtons().add(oCompGroup);
		
		String cID = cID_Fuhre;

		MyE2_String cFensterTitelText = 	new MyE2_String(S.isFull(cTextAlsMaskenTitel)?cTextAlsMaskenTitel:"Maskenansicht zum Bearbeiten:  ID:",true,cID,false);
		MyE2_String cMessageText = 		    new MyE2_String(S.isFull(cTextAlsMaskenTitel)?cTextAlsMaskenTitel:"Fuhrenkopie bearbeiten ...  ID:",true,cID,false);
		
		
		if (bibMSG.get_bIsOK())
		{
			vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID);
			oFuMaskContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), cFensterTitelText);
			oFuMaskContainer.get_oWindowPane().set_oTitle(cMessageText);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(cMessageText),true);
		}
	}
}
