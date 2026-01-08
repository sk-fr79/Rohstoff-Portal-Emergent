package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_GROUP;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_GROUP_2_SETTING;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_SCANNER_GROUP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SCANNER_GROUP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SCANNER_GROUP_2_SETTING;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES_ENUM;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_BUTTON_SelectScannerName extends MyE2_Button {

	private Rebuildable object_to_rebuild = null;
	
	public SCAN_BUTTON_SelectScannerName(Rebuildable buttonRenderer) {
		super(E2_ResourceIcon.get_RI("scanner_choice.png"));
		this.object_to_rebuild=buttonRenderer;
		this.add_oActionAgent(new ownActionAgentSelectScannerNames());
	}

	public SCAN_BUTTON_SelectScannerName(Rebuildable buttonRenderer, boolean bMini) {
		super(E2_ResourceIcon.get_RI(bMini?"scanner_choice_mini.png":"scanner_choice.png"));
		this.object_to_rebuild=buttonRenderer;
		this.add_oActionAgent(new ownActionAgentSelectScannerNames());
	}

	
	public SCAN_BUTTON_SelectScannerName(Rebuildable buttonRenderer, MyE2_String s_button_text) {
		this(buttonRenderer);
		this.setIcon(null);
		this.set_Text(s_button_text);
	}
	
	
	
	private class ownActionAgentSelectScannerNames extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new ownPopupSelectScanner();
		}
	}

	
	private class ownPopupSelectScanner extends E2_BasicModuleContainer {

		public ownPopupSelectScanner() throws myException {
			super();
			
//			//der inner join sorgt dafuer, dass nur nicht-leere scanner-gruppen beruecksichtigt werden 
//			SELECT selScannerGroups = new SELECT( "DISTINCT "+_DB.SCANNER_GROUP+".*").from(_DB.SCANNER_GROUP).join(_DB.SCANNER_GROUP_2_SETTING)
//					.on(_DB.Z_SCANNER_GROUP$ID_SCANNER_GROUP,_DB.Z_SCANNER_GROUP_2_SETTING$ID_SCANNER_GROUP)
//					.orderBy(_DB.Z_SCANNER_GROUP$SCANNER_GROUP);
			
			SEL selScanGroups = new SEL("DISTINCT "+_TAB.scanner_group.fullTableName()+".*")
						.FROM(_TAB.scanner_group).LEFTOUTER(_TAB.scanner_group_2_setting, SCANNER_GROUP.id_scanner_group, SCANNER_GROUP_2_SETTING.id_scanner_group)
						.ORDERUP(SCANNER_GROUP.scanner_group);
			
			
//			DEBUG.System_println("AM_BasicContainer_Button_SelectScannerName:"+ selScannerGroups.toString());
			
			RECLIST_SCANNER_GROUP rlScannerGroup = new RECLIST_SCANNER_GROUP(selScanGroups.s());
			
			if (rlScannerGroup.get_vKeyValues().size()>0) {
				MyE2_Grid  innerGrid = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
				for (RECORD_SCANNER_GROUP recGroup: rlScannerGroup) {
					innerGrid.add(new ownButtonSelectScannerGroup(recGroup), E2_INSETS.I(2,3,2,3));
				}
				
				this.add(innerGrid, E2_INSETS.I(3,3,3,3));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Bitte wählen Sie "));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine nichtleeren Scannergruppen gefunden")));
			}
			
		}
		
		private class ownButtonSelectScannerGroup extends MyE2_Button {

			private RECLIST_SCANNER_SETTINGS_SPECIAL vRecordScanSetting = new RECLIST_SCANNER_SETTINGS_SPECIAL();
			private RECORD_SCANNER_GROUP 			RecGroup = null; 
			
			public ownButtonSelectScannerGroup(RECORD_SCANNER_GROUP recGroup) throws myException {
				super(recGroup.get_SCANNER_GROUP_cUF());
				this.RecGroup = recGroup;
				StringBuffer cToolTips = new StringBuffer(); 
				for (RECORD_SCANNER_GROUP_2_SETTING recSet: recGroup.get_DOWN_RECORD_LIST_SCANNER_GROUP_2_SETTING_id_scanner_group()) {
					vRecordScanSetting.ADD(new RECORD_SCANNER_SETTINGS_SPECIAL(recSet.get_UP_RECORD_SCANNER_SETTINGS_id_scanner_settings()),true);
					cToolTips.append(recSet.get_UP_RECORD_SCANNER_SETTINGS_id_scanner_settings().get_SCANNER_NAME_cUF()+"("+
										recSet.get_UP_RECORD_SCANNER_SETTINGS_id_scanner_settings().get_BESCHREIBUNG_cUF_NN("-")+")\n");
				}
				
				this.setToolTipText(cToolTips.toString());
				this.add_oActionAgent(new ownActionAgentSetScanner());
			}

			
			private class ownActionAgentSetScanner extends XX_ActionAgent {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibALL.setSessionValue(bibSES_ENUM.SCANNERLISTE.name(), ownButtonSelectScannerGroup.this.vRecordScanSetting);
					if (SCAN_BUTTON_SelectScannerName.this.object_to_rebuild!=null) {
						SCAN_BUTTON_SelectScannerName.this.object_to_rebuild.rebuild();
					}
					ownPopupSelectScanner.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(	S.t("Ihrer Sitzung wurde die Scannergruppe: "),
																				S.ut(ownButtonSelectScannerGroup.this.RecGroup.get_SCANNER_GROUP_cUF()),
																				S.t(" zugeordnet!"))));
				}
				
			}
			
		}

	}

	
	public interface Rebuildable {
		public void rebuild() throws myException;
	}
	
	
	
}
