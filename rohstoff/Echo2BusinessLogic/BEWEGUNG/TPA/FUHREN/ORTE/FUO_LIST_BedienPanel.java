package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class FUO_LIST_BedienPanel extends MyE2_Column 
{
	
	private FUO_LIST_BT_NEW 	oFUO_LIST_BT_NEW = null;
	private FUO_LIST_BT_VIEW 	oFUO_LIST_BT_VIEW = null;
	private FUO_LIST_BT_EDIT 	oFUO_LIST_BT_EDIT = null;
	private FUO_LIST_BT_DELETE 	oFUO_LIST_BT_DELETE = null;
	
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;
	
	public FUO_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		Insets oInsets = new Insets(0,0,0,5);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(this.oFUO_LIST_BT_NEW = new FUO_LIST_BT_NEW(oNaviList,oMaskContainer, oFUO_DaughterComponent), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(this.oFUO_LIST_BT_VIEW = new FUO_LIST_BT_VIEW(oNaviList,oMaskContainer, oFUO_DaughterComponent), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(this.oFUO_LIST_BT_EDIT = new FUO_LIST_BT_EDIT(oNaviList,oMaskContainer, oFUO_DaughterComponent), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(this.oFUO_LIST_BT_DELETE = new FUO_LIST_BT_DELETE(oNaviList, oFUO_DaughterComponent), E2_INSETS.I_2_2_2_2);
		
		//2015-04-29: upload-button fuer fuhrenorte
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(	oNaviList,
																			  (FUO_DaugherComponent.is_EK()?
																			  E2_MODULNAMES.NAME_MODUL_FUO_LISTE_EK
																			  :
																			  E2_MODULNAMES.NAME_MODUL_FUO_LISTE_VK)
																			,
																			E2_MODULNAMES.NAME_MODUL_FUHRENMASKE), E2_INSETS.I_2_2_2_2);
	}


	public FUO_LIST_BT_NEW get_oFUO_LIST_BT_NEW()
	{
		return oFUO_LIST_BT_NEW;
	}

	
	public FUO_LIST_BT_VIEW get_oFUO_LIST_BT_VIEW()
	{
		return oFUO_LIST_BT_VIEW;
	}

	
	public FUO_LIST_BT_EDIT get_oFUO_LIST_BT_EDIT()
	{
		return oFUO_LIST_BT_EDIT;
	}

	
	public FUO_LIST_BT_DELETE get_oFUO_LIST_BT_DELETE()
	{
		return oFUO_LIST_BT_DELETE;
	}
	
	
}
