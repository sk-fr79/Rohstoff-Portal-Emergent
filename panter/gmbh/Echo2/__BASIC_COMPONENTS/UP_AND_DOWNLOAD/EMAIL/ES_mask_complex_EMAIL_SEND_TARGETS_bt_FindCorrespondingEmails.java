package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.AM_BasicContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class ES_mask_complex_EMAIL_SEND_TARGETS_bt_FindCorrespondingEmails extends MyE2_Button {

	
	private ES_mask_complex_EMAIL_SEND_TARGETS  simpleDaughterSendTargets = null;
	
	
	public ES_mask_complex_EMAIL_SEND_TARGETS_bt_FindCorrespondingEmails(ES_mask_complex_EMAIL_SEND_TARGETS  p_simpleDaughterSendTargets) {
		super(E2_ResourceIcon.get_RI("suche_mini.png"),true);
		this.simpleDaughterSendTargets = p_simpleDaughterSendTargets;
		
		this.add_oActionAgent(new ownActionFindMails());
		
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new ES_mask_complex_EMAIL_SEND_TARGETS_bt_FindCorrespondingEmails(this.simpleDaughterSendTargets);
	}
	
	private class ownActionFindMails extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			TS_Treasure_Chest ts_UploadContainer = TS_Treasure_CONST.find_TreasureChest(TS_Treasure_CONST.TS_DEFINITION.UPLOADFILES_TREASURE_CHEST);
			
			if (ts_UploadContainer!=null) {
				AM_BasicContainer container = (AM_BasicContainer)ts_UploadContainer.get_TREASURE_CHEST();
					
				String cID_TABLE = container.get_id_TABLE();
				String cTABLE_BASE_NAME = container.get_table_NAME();
					
				new containerPopup4EmailSelektion(cID_TABLE, cTABLE_BASE_NAME);
					
			}
		}
		
	}
	
	
	
	private class containerPopup4EmailSelektion extends E2_BasicModuleContainer   {

		public containerPopup4EmailSelektion(String cID_TABLE, String cTABLE_BASE_NAME) throws myException {
			super();
			
//			Archiver_Normalized_Tablename normalizerTable = new Archiver_Normalized_Tablename(cTABLE_BASE_NAME);
			
			Vector<ES_RECORD_ADRESSE> v_recAdr = ES_CONST.get_adressen(cTABLE_BASE_NAME, cID_TABLE);
			
//			if (normalizerTable.get_TableName().equals("JT_ADRESSE")) {
//				recAdr = new RECORD_ADRESSE(cID_TABLE);
//			} else {
//				myCONST_ENUM.VORGANGSART va = myCONST_ENUM.find_Vorgang(normalizerTable.get_TableName());
//				
//				if (va != null) {
//					recAdr = va.get_RECORD_ADRESSE(cID_TABLE);
//				}
//			}
			
			ES_mask_complex_EMAIL_SEND_TARGETS  sd_sendTargets = ES_mask_complex_EMAIL_SEND_TARGETS_bt_FindCorrespondingEmails.this.simpleDaughterSendTargets;
			ES_RB_MASK_ComponentMap mask = (ES_RB_MASK_ComponentMap)sd_sendTargets.rb_ComponentMap_this_belongsTo();
			
			DEBUG.System_println(mask.getClass().getCanonicalName());
			
			
			if (v_recAdr != null && v_recAdr.size()>0) {
				int[] iBreite = {200,200,200};
				MyE2_Grid gridAussen = new MyE2_Grid(iBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());

				for (ES_RECORD_ADRESSE recAdr: v_recAdr) {
					MyE2_Grid gridFirma = 		new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
					MyE2_Grid gridMitarbeiter = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
					MyE2_Grid gridLiefer = 		new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
					MyE2_String titel = recAdr.get__titel();
					MyE2_String infoAdresse = recAdr.get__beschriftung();
	
					if (titel != null) {
						gridAussen.add(new MyE2_Label(titel, MyE2_Label.STYLE_TITEL_BIG_PLAIN()), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorDDark(), 3, 1));
					}
					if (infoAdresse!=null) {
						gridAussen.add(new MyE2_Label(infoAdresse, MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorDDark(), 3, 1));
					}
					gridAussen.add(new MyE2_Label(new MyE2_String("Hauptadresse"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorDDark(), 1, 1));
					gridAussen.add(new MyE2_Label(new MyE2_String("Mitarbeiter"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorDDark(), 1, 1));
					gridAussen.add(new MyE2_Label(new MyE2_String("Lieferadresse"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorDDark(), 1, 1));
					
					gridAussen.add(gridFirma, 		MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorBase(), 1, 1));
					gridAussen.add(gridMitarbeiter, MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorBase(), 1, 1));
					gridAussen.add(gridLiefer, 		MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorBase(), 1, 1));
					
					for (RECORD_EMAIL recMail: recAdr.get_DOWN_RECORD_LIST_EMAIL_id_adresse()) {
						gridFirma.add(new ownButton(recMail), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), 		new E2_ColorBase(), 1, 1));
					}
					
					for (RECORD_MITARBEITER recMA: recAdr.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis()) {
						for (RECORD_EMAIL recMail: recMA.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_DOWN_RECORD_LIST_EMAIL_id_adresse()) {
							if (recMA.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().is_AKTIV_YES()) {
								gridMitarbeiter.add(new ownButton(recMail), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), 		new E2_ColorBase(), 1, 1));
							}
						}
					}
					
					for (RECORD_LIEFERADRESSE recLA: recAdr.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis()) {
						for (RECORD_EMAIL recMail: recLA.get_UP_RECORD_ADRESSE_id_adresse_liefer().get_DOWN_RECORD_LIST_EMAIL_id_adresse()) {
							if (recLA.get_UP_RECORD_ADRESSE_id_adresse_liefer().is_AKTIV_YES()) {
								gridLiefer.add(new ownButton(recMail), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2), 		new E2_ColorBase(), 1, 1));
							}
						}
					}
					
					if (v_recAdr.get(v_recAdr.size()-1)!=recAdr) {
						gridAussen.add_Separator(E2_INSETS.I(0,2,0,2));
					}
				}
				
				this.add(gridAussen, E2_INSETS.I(2,2,2,2));
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("Bitte wählen Sie eine Mailadresse ..."));
				
			} else {
				
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Keine Adresse für eMail-Liste gefunden !")));
				
				
			}
			
			
			
			
		}
		
		private class ownButton extends MyE2_Button {
			private RECORD_EMAIL RecMail = null; 
			public ownButton(RECORD_EMAIL recMail) throws myException {
				super(recMail.get_EMAIL_cUF_NN("--"),MyE2_Button.StyleImageButtonBorderLess());
				this.RecMail = recMail;
				this.add_oActionAgent(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						E2_ComponentMAP oMapOfListRow = ES_mask_complex_EMAIL_SEND_TARGETS_bt_FindCorrespondingEmails.this.EXT().get_oComponentMAP();
						((MyE2_TextField)oMapOfListRow.get__Comp(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS)).setText(ownButton.this.RecMail.get_EMAIL_cUF_NN(""));
						containerPopup4EmailSelektion.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				});
			}
			
		}
		
	}
	
}