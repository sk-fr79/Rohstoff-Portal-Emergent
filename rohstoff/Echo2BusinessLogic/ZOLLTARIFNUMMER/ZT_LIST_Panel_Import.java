/**
 * rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER
 * @author manfred
 * @date 12.09.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer.IMPORT_Zolltarifnummer_Temp_BasicModuleContainer;

/**
 * @author manfred
 * @date 12.09.2016
 *
 */
public class ZT_LIST_Panel_Import extends E2_ListSelectorContainer{

	E2_NavigationList  _naviList = null;
	
	ZT_LIST_BasicModuleContainer	_parentContainer 	= null;
	IMPORT_Zolltarifnummer_Handler 	_importHandler 		= null;
	MyE2_Button						_btnCleanImport 	= null;
	
	MyE2_Button						_btnRefresh 		= null;
	
	MyE2_Button 					_btnImportWarennummern = null;
	
	boolean 			_hatImportDaten 	= false;	
	MyE2_Grid	 		_gridInner 			= null;
	
	
	MyE2_Label			_lblDesc			= null;
	MyE2_Label			_lblDesc2			= null;
	
	MyE2_Label			_lblNew				= null;
	MyE2_Label			_lblDeleted			= null;
	MyE2_Label			_lblChanged			= null;

	MyE2_TextField		_tfNew				= null;
	MyE2_TextField		_tfDeleted			= null;
	MyE2_TextField		_tfChanged			= null;
	
	MyE2_Button			_btInsert			= null;
	MyE2_Button			_btDelete			= null;
	MyE2_Button			_btUpdate			= null;
		
	
	/**
	 * @author manfred
	 * @date 12.09.2016
	 *
	 * @throws myException
	 */
	public ZT_LIST_Panel_Import(ZT_LIST_BasicModuleContainer oModulContainer,  E2_NavigationList oNaviList) throws myException {
		super(new MyE2_String("Import von Zolltarifnummern"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		_parentContainer = oModulContainer;
		_importHandler = new IMPORT_Zolltarifnummer_Handler();
		
		_naviList = oNaviList;
		
		refreshPanel();
	}
	
	
	private void initPanel(){

		_hatImportDaten = _importHandler.hasEntries();
		
		this.remove_All_FromInnerComponent();
		_gridInner = new MyE2_Grid(3, 1);
		
		if (_hatImportDaten){
			
			_btnCleanImport	= new MyE2_Button(E2_ResourceIcon.get_RI("delete_mini.png"));
			_btnCleanImport.setText("Löschen der temporären Import-Daten");
			_btnCleanImport.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					_importHandler.cleanImportEntries();
					refreshPanel();
					_parentContainer.initNaviList(_hatImportDaten);
				}
			});
			
			_btnRefresh	= new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
			_btnRefresh.setText("Aktualisieren");
			_btnRefresh.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					refreshPanel();
				}
			});
			
			
			_lblDesc		= new MyE2_Label(new MyString("Abgleichen der importierten Zolltarifnummern "));
			_lblDesc2		= new MyE2_Label(new MyString("Solange sich Daten in der temporären Import-Tabelle befinden kann kein neuer Import aus einer Text-Datei durchgeführt werden. "));
			
			_lblNew 		= new MyE2_Label(new MyString("Neue Einträge:"));
			_lblDeleted 	= new MyE2_Label(new MyString("Einträge zu löschen:"));
			_lblChanged 		= new MyE2_Label(new MyString("Einträge mit Änderungen:"));
			
			_tfNew			= new MyE2_TextField("-", 50, 50);
			_tfDeleted		= new MyE2_TextField("-", 50, 50);
			_tfChanged		= new MyE2_TextField("-", 50, 50);
			
			try {
				_tfNew.set_bEnabled_For_Edit(false);
				_tfDeleted.set_bEnabled_For_Edit(false);
				_tfChanged.set_bEnabled_For_Edit(false);
			} catch (myException e) {}
			
			
			_btInsert		= new MyE2_Button(new MyString("Alle neuen Einträge einfügen"));
			
			_btInsert.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					_importHandler.insertNewEntries();
					refreshList();
					
				}


			});
			
			_btDelete		= new MyE2_Button(new MyString("Alle Einträge Inaktiv setzen"));
			_btDelete.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					_importHandler.setEntriesInactive();
					refreshList();
				}
			});
			
			_btUpdate		= new MyE2_Button(new MyString("Alle im Text veränderten Einträge korrigieren"));
			_btUpdate.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					_importHandler.updateChangedEntries();
					refreshList();
				}
			});
			
			
			_gridInner.add(_lblDesc,2 , E2_INSETS.I_0_10_0_10);
			_gridInner.add(_btnRefresh,1,E2_INSETS.I_10_10_10_10);
			
			_gridInner.add(_lblDesc2,3 , E2_INSETS.I_0_10_0_10);
			
			_gridInner.add(_lblNew, E2_INSETS.I_0_0_5_5);
			_gridInner.add(_tfNew, E2_INSETS.I_0_0_5_5);
			_gridInner.add(_btInsert,E2_INSETS.I_0_0_5_5);
			
			_gridInner.add(_lblDeleted, E2_INSETS.I_0_0_5_5);
			_gridInner.add(_tfDeleted, E2_INSETS.I_0_0_5_5);
			_gridInner.add(_btDelete,E2_INSETS.I_0_0_5_5);
			
			_gridInner.add(_lblChanged, E2_INSETS.I_0_0_5_5);
			_gridInner.add(_tfChanged, E2_INSETS.I_0_0_5_5);
			_gridInner.add(_btUpdate,E2_INSETS.I_0_0_5_5);
			
			_gridInner.add(_btnCleanImport, E2_INSETS.I_0_10_0_0);
			
			
		} else {
			
			_btnImportWarennummern = new MyE2_Button(new MyE2_String("Zolltarifnummern-Import"));
			_btnImportWarennummern.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					IMPORT_Zolltarifnummer_Temp_BasicModuleContainer oImportDlg = new IMPORT_Zolltarifnummer_Temp_BasicModuleContainer();
					oImportDlg.add_CloseActions(new XX_ActionAgentWhenCloseWindow(oImportDlg) {
						
						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							refreshPanel();
							_parentContainer.initNaviList(_hatImportDaten);
						}
					});
					oImportDlg.show();
				}
			});
			
			
			_gridInner.add(_btnImportWarennummern, E2_INSETS.I_0_0_5_5);

		}
		
		
		this.add(_gridInner);
		
	}

	
	
	
	
	/**
	 * ermitteln der Import-Daten
	 * @author manfred
	 * @date 13.09.2016
	 *
	 */
	private void initData(){
		if (_hatImportDaten){
			int nNew = 		_importHandler.getCountNewEntries();
			int nDel = 		_importHandler.getCountEntriesToDelete();
			int nChanged = 	_importHandler.getCountChangedEntries();
			
			_tfNew.setText(Integer.toString(nNew));
			_tfDeleted.setText(Integer.toString(nDel));
			_tfChanged.setText(Integer.toString(nChanged));
		}

	}
	
	
	
	
	
	/**
	 * baut das Panel für den Datenimport neu auf
	 * @author manfred
	 * @date 14.09.2016
	 *
	 */
	public void refreshPanel(){
		this.initPanel();
		this.initData();
	}
	
	/**
	 * @author manfred
	 * @date 09.07.2021
	 *
	 */
	private void refreshList() {
		if (_naviList != null ) {
			try {
				_naviList.RefreshList();
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	
		refreshPanel();
	}
	
}
