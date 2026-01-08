package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES.RANGE_KEY;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.SCAN_BUTTON_SelectScannerName.Rebuildable;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopDownMenue;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SCANNER_SETTINGS;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_POPUP_Button_Generic extends MyE2_PopDownMenue implements Rebuildable {

	private SCAN_DESCRIPTION_IF scanDescriptor = null;
	
	public SCAN_POPUP_Button_Generic(SCAN_DESCRIPTION_IF p_scanDescriptor) throws myException {
		super(E2_ResourceIcon.get_RI("scanner_popup.png"),E2_ResourceIcon.get_RI("scanner_popup__.png"));

		this.scanDescriptor = p_scanDescriptor;
		this.setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.setPopUpOutsets(E2_INSETS.I(0,-60,0,0));
		
		this.rebuild();
	}


	@Override
	public void rebuild() throws myException {
		
		this.removeAllButtons();
		
		//zuerst den button zur scannergruppen-auswahl anzeigen
		this.addButton(new SCAN_BUTTON_SelectScannerName(this,new MyE2_String("Scannergruppe wählen/wechseln")), true);
		
		//jetzt nachsehen, welche scanner-record angezeigt werden
		MyE2_MessageVector mv = new MyE2_MessageVector();
		RECLIST_SCANNER_SETTINGS_SPECIAL 			rlScannerAllInSelectedRange = 	bibSES.get_RECLIST_SCANNER_4_USER(mv);
		//scanner-settings fuer einen speziell definierten Bereich
		Vector<RECORD_SCANNER_SETTINGS_SPECIAL> 	vPassendeScannerSettings = 		new Vector<RECORD_SCANNER_SETTINGS_SPECIAL>();
		//scanner-settings fuer allgemeine Nutzung
		Vector<RECORD_SCANNER_SETTINGS_SPECIAL> 	vAllModuleScannerSettings = 	new Vector<RECORD_SCANNER_SETTINGS_SPECIAL>();

		String range_key_specified = 	null;
		String range_text_specified = 	null;

		if (this.scanDescriptor.get_RANGE_KEY()!=null) {
			//ein leerer rangekey wird wie der key ATTACHMENT_ALL_OTHERS behandelt
			range_key_specified = this.scanDescriptor.get_RANGE_KEY().db_val();
			range_text_specified = this.scanDescriptor.get_RANGE_KEY().user_text();
			//jetzt die passenden sammeln
			for (RECORD_SCANNER_SETTINGS set: rlScannerAllInSelectedRange) {
				if (set.get_MODULE_KENNER_cUF_NN(RANGE_KEY.ATTACHMENT_ALL_OTHERS.db_val()).equals(range_key_specified)) {
					vPassendeScannerSettings.add(new RECORD_SCANNER_SETTINGS_SPECIAL(set));
				} 
			}
			
			vPassendeScannerSettings.sort(new Comparator<RECORD_SCANNER_SETTINGS>() {
				@Override
				public int compare(RECORD_SCANNER_SETTINGS o1,	RECORD_SCANNER_SETTINGS o2) {
					try {
						return o1.get_SCANNER_NAME_cUF_NN("").compareTo(o2.get_SCANNER_NAME_cUF_NN(""));
					} catch (myException e) {
						return 0;
					}
				}
			});
			
			this.addButton(new PseudoButton(new MyE2_String(S.t("Scanner Bereich: "),S.t(range_text_specified)),new E2_FontBoldItalic(-2),new E2_ColorGray(120)),false);
			if (vPassendeScannerSettings.size()>0) {
				for (RECORD_SCANNER_SETTINGS_SPECIAL recScanProfile: vPassendeScannerSettings) {
					this.addButton(new SCAN_BUTTON(recScanProfile, this.scanDescriptor), true);
				}
			} else {
				addButton(new PseudoButton(new MyE2_String(S.t("<kein Scanner definiert>")),new E2_FontPlain(),Color.BLACK),false);
			}
			
		}
		
		
		//IMMER die leeren oder als <Fuer Alle> definierten profile anzeigen (nur falls der specified nicht bereits der all-other war)
		//jetzt die undefinierten (leer oder fuer alle module) sammeln
		if ( !(S.NN(range_key_specified,"-").equals(RANGE_KEY.ATTACHMENT_ALL_OTHERS.db_val()))) {
			for (RECORD_SCANNER_SETTINGS set: rlScannerAllInSelectedRange) {
				if (set.get_MODULE_KENNER_cUF_NN(RANGE_KEY.ATTACHMENT_ALL_OTHERS.db_val()).equals(RANGE_KEY.ATTACHMENT_ALL_OTHERS.db_val())) {
					vAllModuleScannerSettings.add(new RECORD_SCANNER_SETTINGS_SPECIAL(set));
				} 
			}
			
			vAllModuleScannerSettings.sort(new Comparator<RECORD_SCANNER_SETTINGS>() {
				@Override
				public int compare(RECORD_SCANNER_SETTINGS o1,	RECORD_SCANNER_SETTINGS o2) {
					try {
						return o1.get_SCANNER_NAME_cUF_NN("").compareTo(o2.get_SCANNER_NAME_cUF_NN(""));
					} catch (myException e) {
						return 0;
					}
				}
			});

			
			
			this.addButton(new PseudoButton(new MyE2_String(S.t("Scanner Bereich: "),S.t(RANGE_KEY.ATTACHMENT_ALL_OTHERS.user_text())),new E2_FontBoldItalic(-2),new E2_ColorGray(120)),false);
			if (vAllModuleScannerSettings.size()>0) {
				for (RECORD_SCANNER_SETTINGS_SPECIAL recScanProfile: vAllModuleScannerSettings) {
					this.addButton(new SCAN_BUTTON(recScanProfile, this.scanDescriptor), true);
				}
			} else {
				addButton(new PseudoButton(new MyE2_String(S.ut("<kein Scanner definiert>")),new E2_FontPlain(),Color.BLACK),false);
			}
		}

	}
	
	
	private class PseudoButton extends MyE2_Button {

		public PseudoButton(MyString cText, Font font, Color foreColor) {
			super(cText);
			this.setEnabled(false);
			this.setFont(font);
			this.setBackground(new E2_ColorLLight());
			this.setForeground(foreColor);
			this.setBorder(new Border(0, new E2_ColorLLight(), Border.STYLE_SOLID));
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				}
			});
		}
		
	}

}
