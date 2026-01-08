package rohstoff.Echo2BusinessLogic.EAK.GRUPPE;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;

public class EAKG_LIST_ModuleContainer extends E2_BasicModuleContainer
{

	private MyE2_Row 						oBedienPanel = new MyE2_Row(MyE2_Row.STYLE_3D_BORDER());
	private EAKG_LIST_COMPONENT_MAP 		oMap = null;
	private E2_BASIC_EditListButtonPanel 	oBasicEditPanel = null;
	
	private E2_NavigationList 				oNaviList = null;
	
	private MyE2_Label  					labelBranchenCode = new MyE2_Label("");
	private MyE2_Label  					labelBranchenText = new MyE2_Label("");
	
		
	
	public EAKG_LIST_ModuleContainer(EAK_BasicModuleContainer oTopContainer) throws myException
	{
		super();
		
		//this.set_MODUL_IDENTIFIER(E2_MODULNAMES.NAME_MODUL_EAK+"_GRUPPE");
		//2015-10-08: neue benennung
		this.set_MODUL_IDENTIFIER(E2_MODULNAME_ENUM.MODUL.MODUL_EAK_GRUPPE.get_callKey());
		
		this.set_bVisible_Row_For_Messages(false);

		this.labelBranchenText.setFont(new E2_FontPlain(-2));
		this.labelBranchenCode.setFont(new E2_FontPlain(-2));
		
		GridLayoutData oGL = new GridLayoutData();
		oGL.setBackground(new E2_ColorLLLight());
		oGL.setInsets(E2_INSETS.I_5_0_5_0);

		
		MyE2_Grid gridHelp = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());
		gridHelp.setColumnWidth(0,new Extent(100));
		gridHelp.setColumnWidth(1,new Extent(50));
		gridHelp.setColumnWidth(2,new Extent(700));
		gridHelp.add(new MyE2_Label(new MyE2_String("Branche:"),MyE2_Label.STYLE_SMALL_PLAIN()));
		gridHelp.add(this.labelBranchenCode,oGL);
		gridHelp.add(this.labelBranchenText,oGL);
		
		this.oNaviList = new E2_NavigationList();
		
		this.oMap = new EAKG_LIST_COMPONENT_MAP(oTopContainer,this.oNaviList);
		
		
		oNaviList.INIT_WITH_ComponentMAP(oMap,null, null);
		
		this.oBedienPanel.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), E2_INSETS.I_0_2_5_2);
		
		this.oBasicEditPanel = new E2_BASIC_EditListButtonPanel(
				oNaviList, true,true,true,null,null,this.get_MODUL_IDENTIFIER(),"", null, null, null);
		this.oBedienPanel.add(this.oBasicEditPanel,E2_INSETS.I_0_2_5_2);
		this.oBedienPanel.add(new EAKG_ListSearch(oMap,oNaviList));
		
		this.add(gridHelp,E2_INSETS.I_5_5_5_5);
												
		this.add(this.oBedienPanel,E2_INSETS.I_5_5_5_5);
		this.add(oNaviList,E2_INSETS.I_5_5_5_5);
		
		oNaviList._REBUILD_COMPLETE_LIST("");
		
		this.oBasicEditPanel.set_ALL_ButtonsDisabled();
	}
	
	
	
	
	public void set_Branche(String cID_Branche_Unformatiert) throws myException
	{
		SQLFieldMAP oSQLFieldMap = this.oMap.get_oSQLFieldMAP();
		if (!bibALL.isEmpty(cID_Branche_Unformatiert) && bibALL.isInteger(cID_Branche_Unformatiert))
		{
			((SQLFieldForRestrictTableRange)oSQLFieldMap.get_("ID_EAK_BRANCHE")).set_cRestrictionValue_IN_DB_FORMAT(cID_Branche_Unformatiert);
			this.oBasicEditPanel.set_BUTTON_STATUS_VIEW();
		}
		else
		{
			((SQLFieldForRestrictTableRange)oSQLFieldMap.get_("ID_EAK_BRANCHE")).set_cRestrictionValue_IN_DB_FORMAT("NULL");
			this.oBasicEditPanel.set_ALL_ButtonsDisabled();
		}
		this.oNaviList._REBUILD_COMPLETE_LIST("");
		
	}




	public MyE2_Label get_labelBranchenText()
	{
		return labelBranchenText;
	}




	public MyE2_Label get_labelBranchenNummer()
	{
		return labelBranchenCode;
	}
	
	
	
}
