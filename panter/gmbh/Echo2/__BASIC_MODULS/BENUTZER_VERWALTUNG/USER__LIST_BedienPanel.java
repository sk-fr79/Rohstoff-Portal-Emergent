package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class USER__LIST_BedienPanel extends MyE2_Column 
{
	
	private USER__LIST_Selector  oUSER__LIST_Selector = null;
	
	public USER__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oUSER__LIST_Selector = new USER__LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);

		boolean bSuperUser = (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES());
		
		
		if (bSuperUser) {
			this.add(oUSER__LIST_Selector, oInsets);
			this.add(oRowForComponents, oInsets);
			oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
			oRowForComponents.add(new USER__LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
			oRowForComponents.add(new USER__LIST_BT_COPY(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
			oRowForComponents.add(new USER__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
			oRowForComponents.add(new USER__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
			oRowForComponents.add(new USER__LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
			oRowForComponents.add(new USER__PopUpUserSettings_BUTTON(oNaviList), E2_INSETS.I_2_2_2_2);
			
			//2011-03-02: button-globale rechte setzen
			oRowForComponents.add(new USER__LIST_BT_DEFINIERE_RECHTE_AN_GLOBAL_BUTTONS(oNaviList), E2_INSETS.I_2_2_2_2);
			
			E2_ButtonUpDown_NavigationList_to_Archiv oButtonUpload = new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAME_ENUM.MODUL.NAME_MODUL_USERVERWALTUNG_LISTE.get_callKey());
			oButtonUpload.add_GlobalValidator_removeOthers(new E2_ButtonAUTH_ONLY_ADMIN());
			
			
			oRowForComponents.add(oButtonUpload, new Insets(20,2,2,2));
			oRowForComponents.add(new USER__LIST_DATASEARCH(oNaviList,E2_MODULNAME_ENUM.MODUL.NAME_MODUL_USERVERWALTUNG_LISTE.get_callKey()), new Insets(20,2,2,2));
		} else {
			this.add(oRowForComponents, oInsets);
			//dies wird angezeigt, wenn ein normaler benutzer das aufruft
			oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
			oRowForComponents.add(new USER__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
			oRowForComponents.add(new USER__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		}
 		
	}

	public USER__LIST_Selector get_oUSER__LIST_Selector() 
	{
		return oUSER__LIST_Selector;
	}
}
