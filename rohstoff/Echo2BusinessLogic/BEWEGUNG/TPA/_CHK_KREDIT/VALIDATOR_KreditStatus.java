package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA._CHK_KREDIT;

import java.util.UUID;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.event.WindowPaneEvent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ComponentDisabler;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.Messaging.E2_WindowPane_4_PopupMessages;
import panter.gmbh.Echo2.Messaging.IF_Message_ForceIntoPopup;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPaneExtender;
import panter.gmbh.Echo2.decisions.FingerPrint_Record;
import panter.gmbh.Echo2.decisions.MyE2_AlarmMessage_4_Confirm_ABSTRACT;
import panter.gmbh.Echo2.decisions.bibDECISIONS;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_StatusErmittlung_Kreditversicherung;

public abstract class VALIDATOR_KreditStatus extends XX_ActionValidator {

	public static String BUTTON_KENNER_ERLAUBE_UEBERSCHREITEN_KREDITLIMIT = "ERLAUBE_UEBERSCHREITEN_KREDITLIMIT";
	
	private Vector<String>  									vID_VPOS_TPA_FUHRE = 	new Vector<String>();
	private Vector<STATKD_StatusErmittlung_Kreditversicherung>  vStatusVersicherungen = new Vector<STATKD_StatusErmittlung_Kreditversicherung>();
	
	public abstract Vector<String>  get_Actual_ID_VPOS_TPA_FUHRE_To_Print() throws myException;

	
	private ownWindow4Messages oWindow = new ownWindow4Messages();
	

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();
		
		
		//2014-12-08: unterdrückung der Kreditabfrage ist moeglich
		if (bib_Settigs_Mandant.get_SUPPRESS_KREDITSTATE_CHECK_BEFORE_PRINT_PAPERS()) {
			return oMV_Rueck;
		}
		
		if (  oComponentWhichIsValidated == null || 
			  (!(oComponentWhichIsValidated instanceof E2_IF_Handles_ActionAgents)) || 
			  (!(oComponentWhichIsValidated instanceof MyE2IF__Component))) {
			
			oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String("Die Prüfung auf Überschreitung der Kreditausfallversicherung konnte nicht durchgeführt werden: Fehlercode:",true, "No Component found!",false)));
			
		} else {

			E2_IF_Handles_ActionAgents 	oActionHandle = (E2_IF_Handles_ActionAgents) oComponentWhichIsValidated;
			MyE2IF__Component 			oComponent =	(MyE2IF__Component) oComponentWhichIsValidated;
			
			//zuerst nachsehen, ob eine pruefung bereits bestaetigt wurde
			if (!bibDECISIONS.COMPARE_SESSION_FingerPrints_WithActualFingerPrint(oComponent.EXT().get_UUID(),this.get_UUID(), new ownFingerPrint())) {
				
				bibDECISIONS.SAVE_VALID_FingerPrint_TO_SESSION_STEP1(oComponent.EXT().get_UUID(),this.get_UUID(), new ownFingerPrint());
				
				//hier erfolgt dann die validierung
				this.vID_VPOS_TPA_FUHRE.removeAllElements();
				this.vID_VPOS_TPA_FUHRE.addAll(this.get_Actual_ID_VPOS_TPA_FUHRE_To_Print());

				this.vStatusVersicherungen.removeAllElements();
				this.vStatusVersicherungen.addAll(this.fill_Vector_MustBeWarned(this.vID_VPOS_TPA_FUHRE));
				
				if (this.vStatusVersicherungen.size()>0) {
					//heisst: es wurde mindestens eine Firma mit ueberschrittenem Kreditlimit gefunden
					oMV_Rueck.add(new ownMessage4ControlKreditVersicherung(oActionHandle, oComponent.EXT().get_UUID(), this.get_UUID()));
				}
			}
		}
		
		return oMV_Rueck;
	}
 
	
	/**
	 * @param v__ID_VPOS_TPA_FUHRE
	 * @return Vector mit den unsicheren STATKD_StatusErmittlung_Kreditversicherung-objekten
	 * @throws myException
	 */
	private Vector<STATKD_StatusErmittlung_Kreditversicherung> fill_Vector_MustBeWarned(Vector<String>  v__ID_VPOS_TPA_FUHRE) throws myException {

		Vector<STATKD_StatusErmittlung_Kreditversicherung> vRueck = new Vector<STATKD_StatusErmittlung_Kreditversicherung>();
		
		if (v__ID_VPOS_TPA_FUHRE.size()>0) {
			for(String sIDFuhre: v__ID_VPOS_TPA_FUHRE){
				STATKD_StatusErmittlung_Kreditversicherung oStatus = new STATKD_StatusErmittlung_Kreditversicherung();
				boolean bRet = oStatus.pruefeFuhre(sIDFuhre);
				if (!bRet) {
					vRueck.add(oStatus);
				}
			}
		} 
		return vRueck;
	}
	
	
	
	
	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
		return null;
	}

	
	private class ownMessage4ControlKreditVersicherung extends MyE2_AlarmMessage_4_Confirm_ABSTRACT {

		//private MyE2_Button  			oButtonStart   = 	new MyE2_Button(new MyE2_String("Trotzdem drucken"),MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		private ownButton               oButtonStart   = 	new ownButton(new MyE2_String("Trotzdem drucken"),MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		private MyE2_Button  			oButtonAbbruch = 	new MyE2_Button(new MyE2_String("Abbruch"),MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		private E2_ComponentDisabler  	oDisabler =			new E2_ComponentDisabler(null);
		
		
		public ownMessage4ControlKreditVersicherung(E2_IF_Handles_ActionAgents 	oStartButton, UUID uuidComponentWhichStartedThis, UUID uuidActionAgent_OR_Validator) throws myException {
			super(	new MyE2_String("Achtung! Es bestehen überschrittene Kreditlimits. Soll der Druck trotzdem ausgeführt werden ? Wenn JA werden Meldungen verschickt!"), 
					oStartButton, uuidActionAgent_OR_Validator);
			
			VALIDATOR_KreditStatus.this.oWindow.set_Size(new MyE2_WindowPaneExtender(new Extent(800), new Extent(400)));
			
			//erster actionAgent sorgt fuer die validierung der berechtigung
			
			this.oButtonStart.add_oInternalActionAgent(new ownActionAgent4Validation());
			this.oButtonStart.add_oInternalActionAgent(new ownActionAgentCloseWindow());
			this.oButtonStart.add_oInternalActionAgent(this.get_ActionAgent_4_SettingStamp_STEP2());
			//STEP2 (oben) sorgt dafuer
			//       dass das erneute ausfuehren funktioniert
			this.oButtonStart.add_oInternalActionAgent(this.get_ActionAgent_4_Executing_CallingComponent());
			
			//die meldungen verschicken
			this.oButtonStart.add_oInternalActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					for (STATKD_StatusErmittlung_Kreditversicherung oStatus : VALIDATOR_KreditStatus.this.vStatusVersicherungen){
						// Meldung mit der Warnung verschicken, dass die Fuhre bestätigt wurde.
						oStatus.warnung_FuhreBestaetigt();
					}

				}
			});
			
			//hier nach dem ausfuehren den FingerPrintContainer fuer diesen button wieder loeschen (damit beim naechten mal wieder gefragt wird)
			this.oButtonStart.add_oInternalActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibDECISIONS.CLEAN_Component_Fingerprint_in_SESSION(((MyE2IF__Component)ownMessage4ControlKreditVersicherung.this.get_oComponentCalling()).EXT().get_UUID());
				}
			});
			

			this.oButtonAbbruch.add_oActionAgent(new ownActionAgentCloseWindow());
		}


		
		//2014-11-24: Validator zum buttonstart wird nicht ausgefuehrt (da add_oInternalActionAgent() ), deshalb muss dieser validierer simuliert werden
		//            via actionAgent. Dieser setzt in die bibMSG eine alarmmessage und die wird dann beim Druecken des Druckbuttons via doEvent() 
		//            in der folgenden realen event-schleife beruecksichtigt
		private class ownActionAgent4Validation extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new E2_ButtonAUTHValidator_AUTO(
						VALIDATOR_KreditStatus.BUTTON_KENNER_ERLAUBE_UEBERSCHREITEN_KREDITLIMIT).isValid(
								(Component)ownMessage4ControlKreditVersicherung.this.get_oComponentCalling()));
			}
			
		}
		


		@Override
		public Component get_ComponentWithMessageAndButtons(MyE2_Message oMessage) {
			MyE2_Grid  oGridRueck = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			//1. message
			MyE2_Grid  oGrid1 = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
			MyE2_Label  oLabel = new MyE2_Label(oMessage.get_cMessage(),new E2_FontBoldItalic(0),true);
			GridLayoutData oGL = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(4,2,2,2), new E2_ColorAlarm(), 1, 1);
			oLabel.setLayoutData(oGL);
			oGrid1.add(oLabel);

			
			MyE2_Grid oGrid2 = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGrid2.add(this.oButtonStart,E2_INSETS.I(1,1,1,1));
			oGrid2.add(this.oButtonAbbruch,E2_INSETS.I(3,1,1,1));
			
			this.oButtonAbbruch.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibDECISIONS.CLEAN_Component_Fingerprint_in_SESSION(ownMessage4ControlKreditVersicherung.this.get_uuid_ComponentWhichStartedThis());
					ownMessage4ControlKreditVersicherung.this.oDisabler.setEnabled();
				}
			});

			oGridRueck.add(oGrid1, E2_INSETS.I(2,2,2,2));
			oGridRueck.add(oGrid2, E2_INSETS.I(2,2,2,2));
			
			return oGridRueck;
		}




		@Override
		public XX_ActionAgent get_ActionAgent_4_SettingStamp_STEP2() throws myException {
			return new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibDECISIONS.SAVE_VALID_FingerPrint_TO_SESSION_STEP2(	ownMessage4ControlKreditVersicherung.this.get_uuid_ComponentWhichStartedThis(), 
																			ownMessage4ControlKreditVersicherung.this.get_uuid_ActionAgent_OR_Validator(), 
																			new ownFingerPrint());
				}
			};
			
		}

		@Override
		public XX_ActionAgent get_ActionAgent_4_Executing_CallingComponent() throws myException {
			return new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					if (ownMessage4ControlKreditVersicherung.this.get_oComponentCalling() instanceof AbstractButton) {
						((AbstractButton)ownMessage4ControlKreditVersicherung.this.get_oComponentCalling()).doAction();
					}
				}
			};
		}


		@Override
		public Color get_Color_4_MessageBackground(MyE2_Message oMessage) {
			return new E2_ColorBase();
		}






		@Override
		public Insets get_Insets_4_MessageInMessageGrid(MyE2_Message oMessage) {
			return E2_INSETS.I(2,2,2,2);
		}



		@Override
		public Vector<E2_IF_Handles_ActionAgents> get_vActiveComponents(MyE2_Message oMessage) {
			Vector<E2_IF_Handles_ActionAgents> vRueck = new Vector<E2_IF_Handles_ActionAgents>();
			
			vRueck.add(this.oButtonStart);
			vRueck.add(this.oButtonAbbruch);
			
			return vRueck;
		}



		@Override
		public void disable_ActiveComponents(MyE2_Message oMessage) throws myException {
			this.oButtonStart.set_bEnabled_For_Edit(false);
			this.oButtonAbbruch.set_bEnabled_For_Edit(false);
		}



		@Override
		public void enable_ActiveComponents(MyE2_Message oMessage) throws myException {
			this.oButtonStart.set_bEnabled_For_Edit(true);
			this.oButtonAbbruch.set_bEnabled_For_Edit(true);
		}



		@Override
		public void create_WindowPane4ShowingMessages(IF_Message_ForceIntoPopup msg) {
		}



		@Override
		public E2_WindowPane_4_PopupMessages get_WindowPane4ShowingMessages(IF_Message_ForceIntoPopup msg) {
			return VALIDATOR_KreditStatus.this.oWindow;
		}



		@Override
		public void windowPaneClosing(WindowPaneEvent arg0) {
			try {
				if ( (arg0 != null) && (arg0.getSource() instanceof E2_WindowPane_4_PopupMessages)) {
					if (((E2_WindowPane_4_PopupMessages)arg0.getSource()).get_bCloseEventIsFromWindowButtonRightCorner()) {
						bibDECISIONS.CLEAN_Component_Fingerprint_in_SESSION(this.get_uuid_ComponentWhichStartedThis());				}
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		}


	}
	
	
	private class ownWindow4Messages extends E2_WindowPane_4_PopupMessages {
		
		public ownWindow4Messages() {
			super();
		}
	}

	
	private class ownActionAgentCloseWindow extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			VALIDATOR_KreditStatus.this.oWindow.set_bCloseEventIsFromWindowButtonRightCorner(false);
			VALIDATOR_KreditStatus.this.oWindow.userClose();
		}
	}
	
	
	
	private class ownButton extends MyE2_Button {

		public ownButton(MyString cText, E2_MutableStyle oStyle) {
			super(cText, oStyle);
			this.setHeight(new Extent(100,Extent.PERCENT));
		}
	}
	
	
	private class ownFingerPrint extends FingerPrint_Record {

		public ownFingerPrint() throws myException {
			super();
			
			Vector<String>  vID_VPOS_TPA_FUHRE = new Vector<String>();
			vID_VPOS_TPA_FUHRE.addAll(VALIDATOR_KreditStatus.this.get_Actual_ID_VPOS_TPA_FUHRE_To_Print());
			
			VectorSingle vFieldsToExclude = new VectorSingle();
			vFieldsToExclude.add(_DB.VPOS_TPA_FUHRE$ZEITSTEMPEL);
			vFieldsToExclude.add(_DB.VPOS_TPA_FUHRE$LETZTE_AENDERUNG);
			
			this.set_vFieldsToExclude(vFieldsToExclude);
			
			for (String cID_VPOS_TPA_FUHRE: vID_VPOS_TPA_FUHRE) {
				
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
				this.add_FingerPrint(new FingerPrint_Record(recFuhre, _DB.VPOS_TPA_FUHRE, cID_VPOS_TPA_FUHRE, vFieldsToExclude));
				
				for (RECORD_VPOS_TPA_FUHRE_ORT  recORT: recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().values()) {
					this.add_FingerPrint(new FingerPrint_Record(recORT, _DB.VPOS_TPA_FUHRE_ORT, recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), vFieldsToExclude));
				}
			}
		}
		
	}
	
}
