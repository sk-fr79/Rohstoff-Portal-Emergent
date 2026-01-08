package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_btDeleteVektor;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_btDeleteVektor4Develop;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_btEdit;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_btNEW;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_btView;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR.FZ_LIST__Selector;


public class FZ_LIST_BedienPanel extends MyE2_Column 
{
	
	private FZ_LIST__Selector  oFZ_LIST_Selector = null;
	private E2_NavigationList  navilist = null;
	
	
	public FZ_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		this.navilist = oNaviList;
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oFZ_LIST_Selector = new FZ_LIST__Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oFZ_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2));
		
		oRowForComponents.add(new FZ_LIST_btNEW(oNaviList),  new Insets(2));
		
		oRowForComponents.add(new FZ_LIST_btEdit(oNaviList),  new Insets(2));

		oRowForComponents.add(new FZ_LIST_btView(oNaviList),  new Insets(2));
		
		if (bibALL.get_bDebugMode()) {
			oRowForComponents.add(new FZ_LIST_btDeleteVektor4Develop(oNaviList),  new Insets(20,2,2,2));
		}
		
		oRowForComponents.add(new FZ_LIST_btDeleteVektor(oNaviList),  new Insets(20,2,2,2));
		
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAME_ENUM.MODUL.FUZE_ATOM_BASED_LIST.get_callKey()), new Insets(20,2,2,2));
		
		oRowForComponents.add(new FZ_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public FZ_LIST__Selector get_oFZ_LIST_Selector() 
	{
		return oFZ_LIST_Selector;
	}
	
}
