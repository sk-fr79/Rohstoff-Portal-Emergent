package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class MAIL_INBOX_LIST_BedienPanel extends MyE2_Column 
{
	
	private MAIL_INBOX_LIST_Selector  oMAIL_INBOX_LIST_Selector = null;
	
	public MAIL_INBOX_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer, String cMODULE_KENNER) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oMAIL_INBOX_LIST_Selector = new MAIL_INBOX_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oMAIL_INBOX_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
//		oRowForComponents.add(new MAIL_INBOX_LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new MAIL_INBOX_LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new MAIL_INBOX_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new MAIL_INBOX_LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		
		
		oRowForComponents.add(new MAIL_INBOX_LIST_BT_ImportMails(oNaviList), E2_INSETS.I_20_2_2_2);
		
		oRowForComponents.add(new MAIL_INBOX_LIST_BT_CheckAllUnconnectedEmails(oNaviList), E2_INSETS.I_20_2_2_2);
		
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,cMODULE_KENNER), new Insets(20,2,2,2));
		
		
		
		oRowForComponents.add(new MAIL_INBOX_LIST_DATASEARCH(oNaviList, cMODULE_KENNER), new Insets(20,2,2,2));
	}

	public MAIL_INBOX_LIST_Selector get_oMAIL_INBOX_LIST_Selector() 
	{
		return oMAIL_INBOX_LIST_Selector;
	}
}
