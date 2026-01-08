package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ExportSql.EXP_ButtonToCreateSqLExport;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button_old;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;


public class TR__LIST_BedienPanel extends MyE2_Column 
{
	
	private TR__LIST_Selector  oTR__LIST_Selector = null;
	
	public TR__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer, TR__LIST_BasicModuleContainer oListModuleContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oTR__LIST_Selector = new TR__LIST_Selector(oNaviList, oListModuleContainer.get_MODUL_IDENTIFIER());
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oTR__LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new TR__LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TR__LIST_BT_COPY(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TR__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TR__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TR__LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAME_ENUM.MODUL.MODUL_TAXRULES_LIST.get_callKey()), new Insets(20,2,2,2));
		
		oRowForComponents.add(new REP__POPUP_Button_old(oListModuleContainer.get_MODUL_IDENTIFIER(), oNaviList));

		//oRowForComponents.add(new E2_ButtonToCreate_SQL_ExportStatements(oNaviList, new E2_ButtonAUTH_ONLY_ADMIN()), new Insets(10,2,2,2));
		oRowForComponents.add(new EXP_ButtonToCreateSqLExport(oNaviList, 			new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER()), new Insets(10,2,2,2));

		
		
		oRowForComponents.add(new TR__LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public TR__LIST_Selector get_oTR__LIST_Selector() 
	{
		return oTR__LIST_Selector;
	}
	
	
	
//	private class ActionTest extends XX_ActionAgent {
//
//
//		private E2_NavigationList navi;
//
//		E2_ComponentMapMarker marker = null;
//		
//		public ActionTest(E2_NavigationList m) {
//			super();
//			this.navi = m;
//		}
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			
//			for (E2_ComponentMAP m: navi.get_vComponentMAPS()) {
//				Rec21 hd = new Rec21(_TAB.handelsdef)._fill_id(m.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
//				
////				boolean aktiv = 	hd.is_yes_db_val(HANDELSDEF.aktiv);
////				boolean selected = 	navi.get_vSelectedIDs_Unformated().contains(m.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
//				if (marker==null) {
//					marker = new E2_ComponentMapMarker(m); 
//					marker.formatComponentMap();
//				} else {
//					marker.resetAttributesInMap();
//					marker = null;
//				}
//				
//			}
//			
//		}
//		
//	}
	
	
}
