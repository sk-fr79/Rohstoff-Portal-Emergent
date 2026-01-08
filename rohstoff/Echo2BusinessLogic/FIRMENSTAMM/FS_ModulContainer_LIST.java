package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_bt_New_Generic;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO.ADI__CallingButton;
import rohstoff.utils.ECHO2.GROUP_COLLECTOR.POPUP_GROUP_COLLECTORS;

public class FS_ModulContainer_LIST extends Project_BasicModuleContainer
{
	
	private E2_NavigationList 		oNavigationList = null;
	private FS_LIST_ComponentMAP	oLISTComponentMAP = null;
	
	private FS_ModulContainer_MASK	oMaskContainer = null;
	
	
	public FS_ModulContainer_LIST() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST);
		this.oNavigationList = new E2_NavigationList();
		

		this.oMaskContainer = new FS_ModulContainer_MASK();

		//2016-04-06: navilist als parameter für das anlagen/emails spalte komponent
		this.oLISTComponentMAP = new FS_LIST_ComponentMAP(this.oNavigationList);
		
		MyE2_Column oColWithPanels = new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
		E2_ComponentGroupHorizontal oBedienPanel2 = new E2_ComponentGroupHorizontal(null);
		
		FS_ListSelector  oListSelektor = new FS_ListSelector(this.oNavigationList, this.oLISTComponentMAP, this.get_MODUL_IDENTIFIER());
		
		oColWithPanels.add(oListSelektor);
		oColWithPanels.add(oBedienPanel2);

		oBedienPanel2.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList), new Insets(3,2,15,2));

		oBedienPanel2.add(new FS_BT_LIST_VIEW_ADRESS(this.oNavigationList,this.oMaskContainer));
		oBedienPanel2.add(new FS_BT_LIST_NEW_ADRESS(this.oNavigationList,this.oMaskContainer));
		oBedienPanel2.add(new FS_BT_LIST_EDIT_ADRESS(this.oNavigationList,this.oMaskContainer));
		oBedienPanel2.add(new FS_BT_LIST_DELETE_ADRESS(this.oNavigationList));
		oBedienPanel2.add(new FS_BT_LIST_Export(this.oNavigationList));
		
		oBedienPanel2.add(new ADI__CallingButton(this.oNavigationList));
		
		oBedienPanel2.add(new E2_ButtonUpDown_NavigationList_to_Archiv(this.oNavigationList,this.get_MODUL_IDENTIFIER()));
		oBedienPanel2.add(new REM_bt_New_Generic(this.oNavigationList, MODUL.NAME_MODUL_FIRMENSTAMM_LIST));
		
		
		oBedienPanel2.add(new FS_BT_MASS_MAILER(this.oNavigationList));
		
//		oBedienPanel2.add(new FS_BT_LIST_SHOW_ADRESS_UEBERSICHT(this.oNavigationList));
		
		//2015-09-08: umstellung auf neue info-klassen
		oBedienPanel2.add(new FS_BT_LIST_SHOW_ADRESS_UEBERSICHT_NT(this.oNavigationList));
		
		oBedienPanel2.add(new FS_BT_POPUP_DIVERSE_FUNKTIONEN(this.oNavigationList, oListSelektor));
		
		//2014-11-17: neue Druckfunktionen
		oBedienPanel2.add(new FS_BT_POPUP_BoundPrints(this.oNavigationList));
		
		oBedienPanel2.add(new REP__POPUP_Button(this.get_MODUL_IDENTIFIER(),this.oNavigationList), new Insets(3,2,15,2));

		oBedienPanel2.add(new POPUP_GROUP_COLLECTORS(this.get_MODUL_IDENTIFIER(),this.oNavigationList), new Insets(3,2,15,2));

		oBedienPanel2.add(new FS_LIST_DATASEARCH(this.oNavigationList, this.get_MODUL_IDENTIFIER()));
		
		/*
		 * die liste leer initialisieren
		 */
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oLISTComponentMAP, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());
//		this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(
//				new E2_Usersetting_SiteLength(this.oNavigationList.get_AUTOMATIC_GENERATED_KENNUNG()).get_StoredListLengt(10));
		
		
		this.add(oColWithPanels);
		this.add(this.oNavigationList);
		
		//2011-02-24: aktive sind vorbelegt
		oListSelektor.get_oSelVector().doActionPassiv();
		//this.oNavigationList._REBUILD_COMPLETE_LIST("");
		
	}

	
}
