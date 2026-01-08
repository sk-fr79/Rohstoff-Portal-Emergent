package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO.ATOM_SALDO_LIST_popup_export;


public class ATOM_LAG_BEW_LIST_BedienPanel extends MyE2_Column 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5059473383088673422L;

	public ATOM_LAG_BEW_LIST_BedienPanel(E2_NavigationList oNaviList,ATOM_LAG_BEW_LIST_Selector oSelect, E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oSelect, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
//		oRowForComponents.add(new LAG_BEW_LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
//		oRowForComponents.add(new LAG_BEW_LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
//		oRowForComponents.add(new LAG_BEW_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
//		oRowForComponents.add(new LAG_BEW_LIST_BT_RECALCULATE(oNaviList), E2_INSETS.I_2_2_2_2);
		
		//oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_LAGER_BEWEGUNG_LISTE), new Insets(20,2,2,2));
		//oRowForComponents.add(new ATOM_SALDO_LIST_BT_EXPORT2EXCEL(oNaviList) ,E2_INSETS.I_20_2_2_2);
		
		oRowForComponents.add(new ATOM_SALDO_LIST_popup_export(oNaviList) ,E2_INSETS.I_20_2_2_2);
		
		
		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE,oNaviList), E2_INSETS.I_0_0_10_0);
		oRowForComponents.add(new ATOM_LAG_BEW_LIST_DATASEARCH(oNaviList,E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE), new Insets(20,2,2,2));
	}

}
