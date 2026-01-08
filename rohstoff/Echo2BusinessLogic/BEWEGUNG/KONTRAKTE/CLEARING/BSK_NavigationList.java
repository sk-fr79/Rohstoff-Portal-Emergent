package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class BSK_NavigationList extends E2_NavigationList
{

	public BSK_NavigationList() throws myException
	{
		super();
	}

	/**
	 * 
	 * @param oModuleContainer
	 * @param cEK_VK
	 * @param cMODULE_IDENTIFIER
	 * @param cID_VPOS_KON_Gegen  wird != null, wenn die liste als tochterausklappliste benutzt wird
	 * @throws myException
	 */
	public void INIT(BSKC_ModulContainerLIST oModuleContainer, String cEK_VK, String cMODULE_IDENTIFIER, String cID_VPOS_KON_Gegen) throws myException
	{
		MutableStyle  oStyleNaviList = new Style_Table_Normal( new Border(1, new E2_ColorBase(-50), Border.STYLE_SOLID),E2_INSETS.I_0_0_0_0);
		
		if (S.isFull(cID_VPOS_KON_Gegen))    //dann ist das eine ausklappliste
		{
			this.set_bShowNavilistWithHeader(false);     //keine ueberschrift
			this.get_vectorSegmentation().set_bOnlyOneSegment(true);          //alles am stueck
			oStyleNaviList = new Style_Table_Normal( new Border(1, new E2_ColorBase(-50), Border.STYLE_SOLID),E2_INSETS.I_0_0_0_0, new E2_ColorLLight());
		}
		INIT_WITH_ComponentMAP(new BSKC_LIST_ComponentMAP(this, oModuleContainer,cEK_VK, cID_VPOS_KON_Gegen),oStyleNaviList, cMODULE_IDENTIFIER);
	}

}
