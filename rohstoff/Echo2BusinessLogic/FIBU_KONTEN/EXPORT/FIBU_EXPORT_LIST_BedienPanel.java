package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_EXPORT_LIST_BedienPanel extends MyE2_Column 
{
	private FIBU_EXPORT_LIST_Selector  oFIBU_EXPORT_LIST_Selector = null;
	
	public FIBU_EXPORT_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oFIBU_EXPORT_LIST_Selector = new FIBU_EXPORT_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oFIBU_EXPORT_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));

		// Add the delete button
		FIBU_EXPORT_LIST_ButtonDelete btDelete = new FIBU_EXPORT_LIST_ButtonDelete(oNaviList);
		btDelete.setSelektor(get_oFIBU_EXPORT_LIST_Selector());
		oRowForComponents.add(btDelete, E2_INSETS.I_2_2_2_2);
		
		
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_LIST_FIBU_KONTEN_REGELN), new Insets(20,2,2,2));
		oRowForComponents.add(new EXP_popup_menue_exporter(oNaviList), new Insets(10,2,2,2));

		// Suche ist deaktiviert
		//oRowForComponents.add(new FIBU_EXPORT_LIST_Datasearch(oNaviList), new Insets(20,2,2,2));
	}

	public FIBU_EXPORT_LIST_Selector get_oFIBU_EXPORT_LIST_Selector() 
	{
		return oFIBU_EXPORT_LIST_Selector;
	}
}
