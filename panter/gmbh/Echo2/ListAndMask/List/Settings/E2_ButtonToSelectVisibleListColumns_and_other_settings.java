package panter.gmbh.Echo2.ListAndMask.List.Settings;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_WindowSplitPane;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * button zum anzeigen eines auswahlfensters fuer listen,
 * damit wird ein popup angezeigt, das die auswahl der anzuzeigenden spalten
 * definiert 
 *
 */
public class E2_ButtonToSelectVisibleListColumns_and_other_settings extends MyE2_Button
{
	private E2_NavigationList 	oNavigationList = 		null;

	/*
	 *  vector fuer die zustaende des anzeigeflags gespeichert werden
	 */
	private E2_BasicModuleContainer oBasicContainer = null;
	
	//variante fuer listen, wo keine selektion moeglich ist
	public boolean   				AllowSettingsMarkierung = true; 
	
	public E2_ButtonToSelectVisibleListColumns_and_other_settings(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("selectrows.png"),true);
		
		this.oNavigationList = onavigationList;
		this.add_oActionAgent(new ButtonActionAgent_Start_Grid_Settings());
		this.setToolTipText((new MyE2_String("Auswählen, welche Spalten in der Liste angezeigt werden, sowie weitere Listeneinstellungen ...")).CTrans());
	}

	
	public void create_New_Container()	{
		this.oBasicContainer = new ownContainer();
	}
	
	
	/**
	 * ActionAgent zum anzeigen des selektionsfensters
	 * @author martin
	 *
	 */
	private class ButtonActionAgent_Start_Grid_Settings extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ButtonToSelectVisibleListColumns_and_other_settings oThis = E2_ButtonToSelectVisibleListColumns_and_other_settings.this;
			
			//oThis.oBasicContainer = new E2_BasicModuleContainer();
			oThis.create_New_Container();
			
			MyE2_Grid  oGridOutline = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oThis.oBasicContainer.add(oGridOutline);

			int iHeight = MyE2_WindowSplitPane.CalculateWindowHight(21,10);
			
			MyE2_TabbedPane  oTabbedPaneGridSettings = new MyE2_TabbedPane(iHeight);
			
			//dann das grid fuer die einstellung der sichtbaren spalten
			LIST_Setting_Plugin_SelectVisibleColumns oGridSetVisibleColumns = 
					new LIST_Setting_Plugin_SelectVisibleColumns(oThis.oNavigationList, oThis.oBasicContainer);

			oTabbedPaneGridSettings.add_Tabb(new MyString("Sichtbare Spalten"), oGridSetVisibleColumns);
			
			//2016-07-04: listen sortierbar und spaltenbreite beieinflussbar
			//if (bibALL.get_bDebugMode()) {
				
				if (S.isFull(oThis.oNavigationList.get_AUTOMATIC_GENERATED_KENNUNG()) && oThis.AllowSettingsMarkierung)
				{
					//dann das plugin fuer die markierungs- und multiselektionen
					LIST_Setting_Plugin_Set_Colum_SortAndWidth  oGridSetColumnSortAndWidth = 
							new LIST_Setting_Plugin_Set_Colum_SortAndWidth(oThis.oNavigationList, oThis.oBasicContainer);
					oTabbedPaneGridSettings.add_Tabb(new MyString("Sortierung und Breite"), oGridSetColumnSortAndWidth);
				}
			//}
			
			
			
			//nur wenn gewuenscht und auch speicherbar
			if (S.isFull(oThis.oNavigationList.get_AUTOMATIC_GENERATED_KENNUNG()) && oThis.AllowSettingsMarkierung)
			{
				//dann das plugin fuer die markierungs- und multiselektionen
				LIST_Setting_Plugin_Set_MultiSelect_and_marking  oGridSetMultiSelectAndMark = 
					new LIST_Setting_Plugin_Set_MultiSelect_and_marking(oThis.oNavigationList, oThis.oBasicContainer);
				oTabbedPaneGridSettings.add_Tabb(new MyString("Mehrfachselektion/Markierung"), oGridSetMultiSelectAndMark);
			}

			
			
			
			
//			//2014-09-01: fuer infos zum jeweiligen modul
			try {
				if (S.isFull(oThis.oNavigationList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE())) {
					
					oTabbedPaneGridSettings.add_Tabb(new MyString("Listen/Modulinfo"),
							new LIST_Setting_Plugin_Show_Infos_From_List(oThis.oNavigationList, oThis.oBasicContainer));
					
				}
			} catch (myException ex) {
				ex.printStackTrace();
			}
//			//2014-09-01

			
			
			oThis.oBasicContainer.add(oTabbedPaneGridSettings, E2_INSETS.I_2_2_2_2);
			
			oThis.oBasicContainer.add_CloseActions(new ActionAfterClose(oThis.oBasicContainer));
			
			oThis.oBasicContainer.CREATE_AND_SHOW_POPUPWINDOW(
					new Extent(700),
					new Extent(iHeight+140),
					new MyE2_String("Einstellungen der Liste ..."));
			
		}
		
	}
	

	private class ActionAfterClose extends XX_ActionAgentWhenCloseWindow
	{
		public ActionAfterClose(E2_BasicModuleContainer container)
		{
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ButtonToSelectVisibleListColumns_and_other_settings.this.oNavigationList.set_CheckBox_To_AllIdsInVector(new Vector<String>());   //alle aus
			E2_ButtonToSelectVisibleListColumns_and_other_settings.this.oNavigationList._REBUILD_ACTUAL_SITE(null);
			
			//2011-06-01: bei speichern der neuen anordnung wird icht nur die liste neu gebaut, sondern auch vorher neu gefuelle
			//            damit kann in einzelnen berechnungsspalten geprueft werden, ob die spalte eingeblendet ist, und wenn nein,
			//            kann sie leer gelassen werden
			E2_ButtonToSelectVisibleListColumns_and_other_settings.this.oNavigationList.BUILD_ComponentMAP_Vector_from_ActualSegment();
			//  --ende 
			
			E2_ButtonToSelectVisibleListColumns_and_other_settings.this.oNavigationList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen

			
		}
	}


	
	private class ownContainer extends E2_BasicModuleContainer {
	}
	
}
