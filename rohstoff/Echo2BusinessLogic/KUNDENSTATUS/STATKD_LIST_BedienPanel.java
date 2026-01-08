package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class STATKD_LIST_BedienPanel extends MyE2_Column 
{
	
	private STATKD_LIST_Selector  oSTATKD_LIST_Selector = null;
	
	public STATKD_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oSTATKD_LIST_Selector = new STATKD_LIST_Selector(oNaviList, E2_MODULNAMES.NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oSTATKD_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new STATKD_LIST_BT_NEW(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new STATKD_BT_LIST_EXCEL(oNaviList), E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE,oNaviList), E2_INSETS.I_0_0_10_0);
		
		oRowForComponents.add(new STATKD_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public STATKD_LIST_Selector get_oSTATKD_LIST_Selector() 
	{
		return oSTATKD_LIST_Selector;
	}
}
