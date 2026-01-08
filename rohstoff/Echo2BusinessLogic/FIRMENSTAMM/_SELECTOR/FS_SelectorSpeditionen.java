package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import java.util.ArrayList;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_TPA;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BRANCHE;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.exceptions.myException;

public class FS_SelectorSpeditionen extends XX_ListSelektor {

	//werte fuer den selektor
	private String 	id_branche_relevant = null;                 //hier muss der anwender die branche "speditionen" hinterlegen
	private String 	c_date_start = null;						//datum zeitraum beginn im format "DD.MM.YYYY"
	private String 	c_date_ziel  = null;						//datum zeitraum ende im format "DD.MM.YYYY"
	private boolean show_used_adresses = false;
	
	//felder zur darstellung
	private MyE2_Grid    			grid4bedienPanel = 		new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Button  			buttonStartSelector = 	new MyE2_Button(new MyE2_String("Speditionsauswahl"));
	private MyE2_Button             bt_clear = 				new MyE2_Button(E2_ResourceIcon.get_RI("delete2.png"));
	
	//komponenten im popup, der die selektion ausloest
	private MyE2_Button  		  	bt_OK =  				new MyE2_Button(new MyE2_String("OK"),MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN()) ;
	private MyE2_Button  			bt_Cancel = 			new MyE2_Button(new MyE2_String("Abbruch"));
	private ownSelectSpedition  	selectBranche = 		new ownSelectSpedition();
	private ownCheckbox_InOrOut  	cb_showIncluded = 		new ownCheckbox_InOrOut("Gefahren");
	private ownCheckbox_InOrOut  	cb_showExcluded = 		new ownCheckbox_InOrOut("Nicht gefahren");
	
	
	private ownSelectorDateVonBis   zeitraumAuswahl = 		new ownSelectorDateVonBis();

	private ownBasicContainer4Popup ownPopup	= 			null;
	
	
	public FS_SelectorSpeditionen() throws myException {
		super();
		//buttons in der leiste
		this.buttonStartSelector.add_oActionAgent(new ownActionStarteSelektorPopup());
		this.bt_clear.add_oActionAgent(new actionClearSelektionsWerte());
		this.bt_clear.setToolTipText(new MyE2_String("Die Selektion der Speditionsauswahl löschen").CTrans());
		
		//buttons im popup
		this.bt_OK.add_oActionAgent(new actionCloseWindowAndSetValues());
		this.bt_Cancel.add_oActionAgent(new actionCloseWindowAndClearValues());
		this.bt_OK.setToolTipText(new MyE2_String("Die Selektion starten").CTrans());
		this.bt_Cancel.setToolTipText(new MyE2_String("Die Selektion der Speditionsauswahl löschen und das Fenster schließen").CTrans());
		
		//radiobutton zwischen der an-aus-funktion
		ActionAgent_RadioFunction_CheckBoxList  radioAction = new ActionAgent_RadioFunction_CheckBoxList(false);
		radioAction.add_CheckBox(this.cb_showIncluded);
		radioAction.add_CheckBox(this.cb_showExcluded);
		
		this.set_oNeutralisator(new ownNeutralisator());
		
		this._baueSelektorKomponente();
	}

	
	private void _baueSelektorKomponente() throws myException {
		int[] i_eins = {170};
		int[] i_zwei = {150,20}; 
		this.grid4bedienPanel.removeAll();
		if (S.isFull(this.c_date_start) && S.isFull(this.c_date_ziel) && S.isFull(this.id_branche_relevant) ) {
			//selektor ist aktiv
			this.grid4bedienPanel.set_Spalten(i_zwei);
			this.grid4bedienPanel.add(this.buttonStartSelector);
			this.grid4bedienPanel.add(this.bt_clear);	
			this.buttonStartSelector.setFont(new E2_FontBold());
			
			RECORD_BRANCHE rb = new RECORD_BRANCHE(this.id_branche_relevant);
			String gefahrenOderNicht 	= new MyE2_String(this.show_used_adresses?"Anzeige der Adressen, die in Transportaufträgen vorkommen":"Anzeige der Adressen, die in Transportaufträgen NICHT vorkommen").CTrans();
			String zeitraum   			= this.c_date_start+" - "+this.c_date_ziel;
			
			String toolTip = new MyE2_String("Speditionsbranche: ").CTrans()+": "+rb.get_BEZEICHNUNG_cUF_NN("-")+"\n"+gefahrenOderNicht+"\n"+zeitraum;
			this.buttonStartSelector.setToolTipText(toolTip);
			
		} else {
			//selektor ist inaktiv
			this.grid4bedienPanel.set_Spalten(i_eins);
			this.grid4bedienPanel.add(this.buttonStartSelector);
			this.buttonStartSelector.setFont(new E2_FontPlain());
			
			this.buttonStartSelector.setToolTipText(new MyE2_String("Selektiere Speditionen auf Vorkommen in den Transportaufträgen").CTrans());
		}
		
		
		
		
	}
	
	
	@Override
	public String get_WhereBlock() throws myException {
		String date_start = new MyDate(this.c_date_start).get_cDateStandardFormat();
		String date_ziel = new MyDate(this.c_date_ziel).get_cDateStandardFormat();
		String id_branche = new MyLong(this.id_branche_relevant).get_cUF_LongString();
		
		TableTerm kopf_tpa = new TableTerm(_TAB.vkopf_tpa.fullTableName(), "TKO");
		TableTerm pos_tpa = new TableTerm(_TAB.vpos_tpa.fullTableName(), "TPO");
		TableTerm fuhre = new TableTerm(_TAB.vpos_tpa_fuhre.fullTableName(), "TFU");
		TableTerm adresse_ = new TableTerm(_TAB.adresse.fullTableName(), "TAD");
		TableTerm firmeninfo = new TableTerm(_TAB.firmeninfo.fullTableName(), "TFI");
		
		if (S.isFull(date_start) && S.isFull(date_ziel) && S.isFull(id_branche) ) {

			SEL  sel = new SEL(kopf_tpa.ft(VKOPF_TPA.id_adresse))
					.FROM(fuhre)
					.INNERJOIN(pos_tpa, fuhre.ft(VPOS_TPA_FUHRE.id_vpos_tpa), pos_tpa.ft(VPOS_TPA.id_vpos_tpa))
					.INNERJOIN(kopf_tpa, kopf_tpa.ft(VKOPF_TPA.id_vkopf_tpa), pos_tpa.ft(VPOS_TPA.id_vkopf_tpa))
					.INNERJOIN(adresse_, kopf_tpa.ft(VKOPF_TPA.id_adresse), adresse_.ft(ADRESSE.id_adresse))
					.INNERJOIN(firmeninfo, firmeninfo.ft(FIRMENINFO.id_adresse), adresse_.ft(ADRESSE.id_adresse))
					.WHERE(new vgl(fuhre.alias(),VPOS_TPA_FUHRE.datum_aufladen,COMP.GE,date_start))
					.AND(new vgl(fuhre.alias(),VPOS_TPA_FUHRE.datum_aufladen,COMP.LE,date_ziel));
		
			String c = "JT_ADRESSE.ID_ADRESSE IN ("+sel.s()+")";
			if (!this.show_used_adresses) {
				c = " NOT ("+c+")";
			}
			c = c + " AND JT_FIRMENINFO.ID_BRANCHE="+id_branche;
			
			return c;
		}
		
		
		return "";
	}

	
	private void clear_dateFieldAndValues() throws myException {
		this.c_date_start = null;
		this.c_date_ziel = null;
		this.zeitraumAuswahl.get_oTextFieldVon().setText("");
		this.zeitraumAuswahl.get_oTextFieldBis().setText("");
		this.id_branche_relevant = null;
		
		new ownSaver().RESTORE();
	}
	
	
	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.grid4bedienPanel;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.grid4bedienPanel;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.bt_OK.add_oActionAgent(oAgent);
		this.bt_clear.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {
	}

	
	//zwei felder, deren status gespeichert wird
	private class ownSelectSpedition extends FS__SelectFieldBranche implements IF_Saveable {
		
		public ownSelectSpedition() throws myException {
			super();
		}

		@Override
		public String get_value_to_save() throws myException {
			return this.get_ActualWert();
		}

		@Override
		public void restore_value(String value) throws myException {
			this.set_ActiveValue_OR_FirstValue(value);
		}

		@Override
		public void set_component_to_status_not_saved() throws myException {
			this.setSelectedIndex(0);
		}

		@Override
		public Component get_Comp() throws myException {
			return this;
		}

		@Override
		public void add_action(XX_ActionAgent agent) throws myException {
			super.add_oActionAgent(agent);
		}
		
	}
	
	
	private class ownCheckbox_InOrOut extends MyE2_CheckBox implements IF_Saveable {

		public ownCheckbox_InOrOut(String text) {
			super(new MyE2_String(text));
			//this.setToolTipText(new MyE2_String("Definiert, ob alle Speditionsadressen angezeigt werden, die im definierten Zeitraum aktiv waren oder die NICHT aktiv waren !").CTrans());
		}

		@Override
		public String get_value_to_save() throws myException {
			return this.isSelected()?"Y":"N";
		}

		@Override
		public void restore_value(String value) throws myException {
			this.setSelected(S.NN(value).equals("Y"));			
		}

		@Override
		public void set_component_to_status_not_saved() throws myException {
			this.setSelected(true);			
		}

		@Override
		public Component get_Comp() throws myException {
			return this;
		}

		@Override
		public void add_action(XX_ActionAgent agent) throws myException {
			super.add_oActionAgent(agent);		
		}
		
	}
	
	
	
	
	private class ownSelectorDateVonBis extends 	MyE2_TextField_Date_von_bis_POPUP_OWN {

		public ownSelectorDateVonBis() throws myException {
			super(S.NN(FS_SelectorSpeditionen.this.c_date_start),S.NN(FS_SelectorSpeditionen.this.c_date_start));
			this.set_bAutoCloseOnBisCalendar(true);
		}

		@Override
		public void Ordne_Komponenten_An(	MyE2_TextField oTextFieldVon,
											MyE2_TextField oTextFieldBis, MyE2_Button oButtonCalendar,
											MyE2_Button oButtonEraserVon, MyE2_Button oButtonEraserBis)
													throws myException {
			 this.removeAll();
			 this.setSize(5);
			 this.add(oTextFieldVon,E2_INSETS.I(0,0,2,0));
			 this.add(oButtonEraserVon,E2_INSETS.I(0,0,2,0));
			 this.add(oButtonCalendar,E2_INSETS.I(0,0,2,0));
			 this.add(oTextFieldBis,E2_INSETS.I(0,0,2,0));
			 this.add(oButtonEraserBis,E2_INSETS.I(0,0,2,0));
		}

	}
	
	
	
	private class ownBasicContainer4Popup extends E2_BasicModuleContainer {

		public ownBasicContainer4Popup() throws myException {
			super();
			this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE_NO_CLOSE());
			
			FS_SelectorSpeditionen oThis = FS_SelectorSpeditionen.this;
			
			MyE2_Grid  grid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			grid.add(new MyE2_Label(new MyE2_String("Bitte geben Sie die Speditionsbranche an"),true), E2_INSETS.I(2,1,2,10));
			grid.add(oThis.selectBranche, E2_INSETS.I(2,1,2,10));
			
			grid.add(new MyE2_Label(new MyE2_String("Betrachtungszeitraum"),true), E2_INSETS.I(2,1,2,10));
			grid.add(oThis.zeitraumAuswahl, E2_INSETS.I(2,1,2,10));
			
			grid.add(new MyE2_Label(new MyE2_String("Spedition ist im Betrachtungszeitraum ..."),true), E2_INSETS.I(2,1,2,4));
			grid.add(oThis.cb_showIncluded, E2_INSETS.I(2,1,2,4));

			grid.add(new MyE2_Label(""), E2_INSETS.I(2,1,2,10));
			grid.add(oThis.cb_showExcluded, E2_INSETS.I(2,1,2,10));

			
			grid.add(FS_SelectorSpeditionen.this.bt_OK, E2_INSETS.I(2,1,2,4));
			grid.add(FS_SelectorSpeditionen.this.bt_Cancel, E2_INSETS.I(2,1,2,4));

			
			this.add(grid, E2_INSETS.I(2,2,2,2));
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(250), new MyE2_String("Speditionen selektieren ..."));
		}
	}
	
	
	//loescht die werte 
	private class actionClearSelektionsWerte extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_SelectorSpeditionen.this.clear_dateFieldAndValues();
			FS_SelectorSpeditionen.this._baueSelektorKomponente();
		}
	}
	
	//schliesst das popup und uebernimmt die werte fuer die spaetere selektion
	private class actionCloseWindowAndSetValues extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_SelectorSpeditionen oThis = FS_SelectorSpeditionen.this;
			oThis.c_date_start = oThis.zeitraumAuswahl.get_oFormatedDateFromTextFieldVon();
			oThis.c_date_ziel = oThis.zeitraumAuswahl.get_oFormatedDateFromTextFieldBis();
			oThis.id_branche_relevant = oThis.selectBranche.get_ActualWert();
			
			if (S.isEmpty(oThis.c_date_start) || S.isEmpty(oThis.c_date_ziel)) {
				oThis.zeitraumAuswahl.get_oTextFieldVon().setText("");
				oThis.zeitraumAuswahl.get_oTextFieldBis().setText("");
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie ein Anfangsdatum und Enddatum des Auswertebereiches an ...")));
			}
			if (S.isEmpty(oThis.id_branche_relevant)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie eine Branche (Logistik) an ...")));
			}
			
			if (bibMSG.get_bIsOK()) {
				oThis.show_used_adresses = oThis.cb_showIncluded.isSelected();
				new ownSaver().SAVE();
				oThis._baueSelektorKomponente();
				oThis.ownPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
	}
	
	//schliesst das popup und uebernimmt die werte fuer die spaetere selektion
	private class actionCloseWindowAndClearValues extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_SelectorSpeditionen oThis = FS_SelectorSpeditionen.this;
			oThis.clear_dateFieldAndValues();
			oThis._baueSelektorKomponente();
			oThis.ownPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	
	
	
	private class ownActionStarteSelektorPopup extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_SelectorSpeditionen oThis = FS_SelectorSpeditionen.this;
			
			if (S.isEmpty(FS_SelectorSpeditionen.this.id_branche_relevant)) {
				//dann ist das der erste start, werte laden aus session
				new ownSaver().RESTORE();
			}

			//falls noch nichts gespeichert ist, dann excluded anschalten
			if ((!oThis.cb_showExcluded.isSelected()) && (!oThis.cb_showIncluded.isSelected())) {
				oThis.cb_showExcluded.setSelected(true);
			}
			
			
			if (S.isFull(oThis.c_date_start)) {
				oThis.zeitraumAuswahl.get_oTextFieldVon().setText(oThis.c_date_start);
			}
			if (S.isFull(oThis.c_date_ziel)) {
				oThis.zeitraumAuswahl.get_oTextFieldBis().setText(oThis.c_date_ziel);
			}
			if (S.isFull(oThis.id_branche_relevant)) {
				oThis.selectBranche.set_ActiveValue_OR_FirstValue(oThis.id_branche_relevant);
			}
			oThis.ownPopup=new ownBasicContainer4Popup();
		}
	}
	
	
	//speicherklasse, merkt sich die einstellung der checkbox incude/exclude und die branche
	private class ownSaver extends E2_UserSetting_ListOfSaveables {
		public ownSaver() {
			super();
			ArrayList<IF_Saveable>  savelist = new ArrayList<IF_Saveable>();
			savelist.add(FS_SelectorSpeditionen.this.cb_showIncluded);
			savelist.add(FS_SelectorSpeditionen.this.cb_showExcluded);
			savelist.add(FS_SelectorSpeditionen.this.selectBranche);
			this._init(savelist, ENUM_USER_SAVEKEY.MODULEKENNER__SAVE_SPEDITIONSSELECTION_ADRESSMODULE);
		}
	}
	
	
	
	public class ownNeutralisator extends XX_ListSelektor_Neutralisator {
		@Override
		public void set_to_Neutral() throws myException {
			FS_SelectorSpeditionen.this.clear_dateFieldAndValues();
		}
		
	}
	
}
