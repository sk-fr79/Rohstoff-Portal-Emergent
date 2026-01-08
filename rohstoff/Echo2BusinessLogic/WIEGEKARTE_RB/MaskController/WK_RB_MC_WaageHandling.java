/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController
 * @author manfred
 * @date 16.06.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.ServerPush.E2_ApplicationInstanceWithServerPushUpdateTask;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_ImageCapture_Handler;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_SETTINGS;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase.ENUM_WaageResultStatus;
import rohstoff.Echo2BusinessLogic.WAAGE.HANDLER.WaageHandlerBase;
import rohstoff.Echo2BusinessLogic.WAAGE.HANDLER.WaageHandlerFactory;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_WaegungPos;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_ZustandWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMapCollector;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Waegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Row_Waagedisplay;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Container;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WaageUser;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Waagedisplay;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_WeWa;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Fremdcontainer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Gebinde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_WaageSettings;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_container;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.WK_RB_Waegung_Buchungssatz;




/**
 * @author manfred
 * @date 16.06.2020
 *
 */
public class WK_RB_MC_WaageHandling extends RB_MaskController   {

	public WK_RB_MC_WaageHandling(IF_RB_Component p_component) throws myException {
		super(p_component);
		init_ReadWaage();
	}

	public WK_RB_MC_WaageHandling(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
		init_ReadWaage();
	}

	public WK_RB_MC_WaageHandling(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
		init_ReadWaage();
	}

	public WK_RB_MC_WaageHandling(RB_DataobjectsCollector p_dataObjectsCollector) throws myException {
		super(p_dataObjectsCollector);
		init_ReadWaage();
	}

	

	//
	//  locals
	//
	private static 	E2_ApplicationInstanceWithServerPushUpdateTask 	m_ServerPushTask = null;
	private 		ArrayList<WK_RB_Row_Waagedisplay> 				m_listWaageDisplays = null;

	private boolean 							m_bMindestlastErforderlich 			= bib_Settigs_Mandant.get_Waage_IstSPERRUNGBeiMindestlastUnterschreitung();
	private boolean 							m_bWarnungDoppelteMindestlastBeiWE 	= bib_Settigs_Mandant.get_Waage_IstWARNUNGBeiMindestlastUnterschreitungWE();
	private WK_RB_MASK_ComponentMap_Wiegekarte 	compMap	= null;
	
	private RecDOWiegekarte 	 rec 						= null;

	
	
	/**
	 * initialisiert die Waage-Verbindung und den Async-Prozess
	 * @author manfred
	 * @date 18.06.2020
	 *
	 * @throws myException
	 */
	public WK_RB_MC_WaageHandling init_ReadWaage() throws myException {
				
		m_bMindestlastErforderlich = bib_Settigs_Mandant.get_Waage_IstSPERRUNGBeiMindestlastUnterschreitung();
		compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);

    	
		
		try {
			WK_RB_Waagedisplay waageDisplay = (WK_RB_Waagedisplay) this.get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAAGELISTE.key());
			m_listWaageDisplays = waageDisplay.get_ListWaageDisplays(); 	

			// Eventhandler an die Buttons der Waagen anhängen
			for (WK_RB_Row_Waagedisplay row:   m_listWaageDisplays) {
				row.setActionAgent(null);
				row.setActionAgent(new actionSaveWaegung(row));
			}

			
		} catch (myException e) {
			// leere Liste
			m_listWaageDisplays = new ArrayList<WK_RB_Row_Waagedisplay>();
		}
		
		
		// falls aus irgend einem Grund der Timer noch läuft, erst mal anhalten
		stopListening();
		clearWaageDisplay();
		_disableWaageDisplays();

		
		// Serverpush-Task initialisieren
		m_ServerPushTask = new E2_ApplicationInstanceWithServerPushUpdateTask(3000);
		
		m_ServerPushTask.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					stopListening();
					getTempWeights_For_All_Scales();
					m_ServerPushTask.start();
			}
			
		});
		
		return this;
	}
	
	

	/**
	 * ActionAgent: Wägung durchführen von LiveWaage 
	 * @author manfred
	 *
	 */
	private class actionSaveWaegung extends XX_ActionAgent{

		private WK_RB_Row_Waagedisplay _rowWaageDisplay = null;
		
		
		public actionSaveWaegung(WK_RB_Row_Waagedisplay oRow) {
			_rowWaageDisplay = oRow;
		}
		

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			_getWaegungFromWaageAndSave(_rowWaageDisplay.getWaageSettings());
			
		}
		
	}


	
	
	/**
	 * Fordert eine Wägung der Waage aus waageSetting an und gibt sie zurück
	 * @author manfred
	 * @date 15.07.2020
	 *
	 * @param waageSetting
	 * @return
	 * @throws myException
	 */
	public RecDOWaegung _getWaegungFromWaageAndSave(Rec_WaageSettings waageSetting) throws myException  {

		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		// temp-Waegungen stoppen
		stopListening();
		clearWaageDisplay();
		_disableWaageDisplays();
		
		// Echtwägung durchführen
		RecDOWaegung rec_waegung = null;
		try {
			
			// Waageuser holen, da er beim Speichern zurückgesetzt wird 
			String idUserWaegung = getWaageUser(); 
			
			
			// speichern der Wiegekarte um den aktuellen Zustand festzuschreiben...
			WK_RB_MASK_ComponentMapCollector compMapColl = (WK_RB_MASK_ComponentMapCollector) get_ComponentMapCollector();
			compMapColl._DoCompleteSaveCycle();

			// Wägung durchführen...
			rec_waegung = createWaegungBuchung( waageSetting, idUserWaegung,mv);
			if (mv.isOK()) {
				rec._set_Waegung(rec_waegung);
				// jetzt die Wägung Speichern (speichert die Wiegekarte mit)
				compMapColl._DoCompleteSaveCycle();
			} 
			
			if (bibMSG.get_bIsOK()) {
				
				compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) compMapColl.get(RecDOWiegekarte.key);
				rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
				//Bilder aufnehmen
				captureImages(rec, waageSetting);

				
				// Hofschein automatisch drucken
				WK_RB_MC_PrintHandling wkPrintHandling = new WK_RB_MC_PrintHandling(compMap,mv);
				wkPrintHandling.printHofscheinAUTOMATIK();
				
			}
			
			
			
			// alle evtl. Messages an das System weiter geben.
			bibMSG.add_MESSAGE(mv);
			
			
		} catch (myException e) {
			
			bibMSG.add_MESSAGE(e);
			e.printStackTrace();
		} finally {
			clearWaageUser();
			stopListening();
		}
		
		return rec_waegung;
	}
	
	
	
	
	
	/**
	 * Erzeugt eine neue Wägung (automatisch 1. oder 2. Wägung) 
	 * @author manfred
	 * @date 14.07.2020
	 *
	 * @param recSetting
	 * @param idUserWaegung
	 * @return
	 * @throws myException
	 */
	public WK_RB_MC_WaageHandling _createWaegungHandAndSave( ENUM_WaegungPos waegung_pos) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		WK_RB_Comp_Waegung compWaeg = null;
		if (waegung_pos.equals(ENUM_WaegungPos.WAEGUNG_1)) {
			compWaeg = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key());
		} else {
			compWaeg = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key());
		}
		
		
		// temp-Waegungen stoppen, falls die irgendwie noch laufen...
		stopListening();
		clearWaageDisplay();
		_disableWaageDisplays();

		// hier explizit nochmal prüfen, ob man speichern kann...
		new WK_RB_MC_ValidateOnSave(compWaeg, mv)._validateBeforeSave();
		if(mv.hasAlarms()) {
			bibMSG.add_MESSAGE(mv);
			return this;
		}
		
		
//		// speichern der Wiegekarte um den aktuellen Zustand festzuschreiben...
		WK_RB_MASK_ComponentMapCollector compMapColl = (WK_RB_MASK_ComponentMapCollector) get_ComponentMapCollector();
	
		
		// Feststellen welche Wägung durchgeführt werden soll:
		RecDOWaegung recWaegungHand = _createWaegungHand(compWaeg, mv);
		
		
		// Alarme weiter geben
		if(mv.hasAlarms()) {
			bibMSG.add_MESSAGE(mv);
			return this;
		}
		

		if (mv.isOK()) {
			rec._set_Waegung(recWaegungHand);
			
			// hier explizit nochmal prüfen, ob man speichern kann...
			new WK_RB_MC_ValidateOnSave(compWaeg, mv)._validateBeforeSave();
			
			if (mv.isOK()) {
				compMapColl._DoCompleteSaveCycle();
				
				// Bilder aufnehmen 
				if (bibMSG.get_bIsOK()) {
					compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) compMapColl.get(RecDOWiegekarte.key);
					rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
					captureImages(rec, null);
				}
				
				
				// Hofschein automatisch drucken
				WK_RB_MC_PrintHandling wkPrintHandling = new WK_RB_MC_PrintHandling(compWaeg,mv);
				wkPrintHandling.printHofscheinAUTOMATIK();
				
				
			} else {
				// waegung wieder löschen
				switch (waegung_pos) {
					case WAEGUNG_1:
						rec._set_Waegung1(null);
						break;
					case WAEGUNG_2:
						rec._set_Waegung2(null);
						break;
					default:
						break;
				} 
				bibMSG.add_MESSAGE(mv);
			}
		} else {
			// alle evtl. Messages an das System weiter geben.
			bibMSG.add_MESSAGE(mv);
			return this;
		}
		
		return this;
	}
	
	
	
	/**
	 * Prüft ob man die Wägung erzeugen kann und erzeugt einen, wenn ok
	 * @author manfred
	 * @date 13.08.2020
	 *
	 * @param compWaeg
	 * @param mv
	 * @return
	 * @throws myException
	 */
	private RecDOWaegung _createWaegungHand(WK_RB_Comp_Waegung compWaeg, MyE2_MessageVector mv) throws myException {
		
		// Vor dem erzeugen des Records prüfen, ob man Speichern kann...(hier ist die Wägung noch nicht an die Wiegekarte gebunden)
		new WK_RB_MC_ValidateOnSave(compWaeg, mv)._validateBeforeSave();
		
		
		// Wägung generieren und speichern...		
		RecDOWaegung recWaegung = null;
		
		// neuer Buchungssatz
		WK_RB_Waegung_Buchungssatz oSatz = null;

		
		ENUM_ZustandWiegekarte zustand = rec._get_ZustandWiegekarte(); 
		String sIDWiegekarte = null;
		if (zustand.equals(ENUM_ZustandWiegekarte.NEU)) {
			sIDWiegekarte = "SEQ_WIEGEKARTE.CURRVAL";
		} else {
			sIDWiegekarte = rec.getActualID().toString();
		}
		
		
		// Waageuser holen, da er beim Speichern zurückgesetzt wird 
		String idUserWaegung = getWaageUser(); 		
		if (S.isEmpty(idUserWaegung)) {
			mv._addAlarm("Es ist kein Waagebenutzer ausgewählt.");
		}
		
		String sWaegungPos  = Integer.toString(compWaeg._getWaegungPos());
		String sGewicht 	= compWaeg.get_txt_Gewicht().getText();
		String sInfoHand 	= compWaeg.get_txt_InfoHand().getText();

		// prüfung, ob beschreibungHand leer ist
		if (S.isEmpty(sInfoHand)) {
			mv._addAlarm("Info Handwägung muss gesetzt sein.");
			compWaeg.get_txt_InfoHand().mark_FalseInput();
		} else {
			compWaeg.get_txt_InfoHand().mark_CorrectInput();
			compWaeg.get_txt_InfoHand().set_bEnabled_For_Edit(compWaeg.get_txt_InfoHand().isEnabled());
		}

		// prüfung, ob Gewicht eine Zahl ist,
		if (!bibNUM.isAllNumber(sGewicht)) {
			mv._addAlarm("Gewicht ist keine gültige Zahl.");
			compWaeg.get_txt_Gewicht().mark_FalseInput();
		} else {
			compWaeg.get_txt_Gewicht().mark_CorrectInput();
			compWaeg.get_txt_Gewicht().set_bEnabled_For_Edit(compWaeg.get_txt_Gewicht().isEnabled());
		}
		
		
		
		if (mv.isOK()) {
	
					
			// ******************************************
			// Wägungsdaten füllen..
			//
			oSatz = new WK_RB_Waegung_Buchungssatz()
					.setID_WIEGEKARTE(sIDWiegekarte)
					.setWAEGUNG_POS(sWaegungPos)
					.setGEWICHT(bibALL.convertTextToBigDecimal(sGewicht))
					.setDATUM("SYSDATE")
					.setZEIT("TO_CHAR (SYSDATE, 'HH24:MI')")
					.setHANDEINGABE("Y")
					.setHANDEINGABE_BEM(sInfoHand)
					.setID_USER_WAEGUNG(idUserWaegung)
					.setW_EINHEIT("kg");
					;
				
			
			if (oSatz != null ) {
				switch (sWaegungPos) {
				case "1":
					recWaegung = (RecDOWaegung1) new RecDOWaegung1(MASK_STATUS.NEW)._fill_from(oSatz);
					break;
				case "2":
					recWaegung = (RecDOWaegung2) new RecDOWaegung2(MASK_STATUS.NEW)._fill_from(oSatz);
					break;
				default:
					throw new myException(new MyE2_String("Es wurde keine gültige Wägungsnummer angegeben!").CTrans());
				}
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim anlegen des Wägungssatzes."));
			}
		}
		
		return recWaegung;
		
	}
	
	
	
	

	/**
	 * Liest die Tara-Werte und trägt sie in die Wägung ein
	 * @author manfred
	 * @date 10.08.2020
	 *
	 * @param compWaeg
	 * @throws myException
	 */
	public  WK_RB_MC_WaageHandling _readTara( ENUM_WaegungPos waegung_pos, MyE2_MessageVector mv) throws myException {
		
		WK_RB_Comp_Waegung compWaeg = null;
		if (waegung_pos.equals(ENUM_WaegungPos.WAEGUNG_1)) {
			compWaeg = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key());
		} else {
			compWaeg = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key());
		}
		
		// Feststellen welche Wägung durchgeführt werden soll:
		WK_RB_MASK_ComponentMap_Wiegekarte 	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte  						rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		WK_RB_Search_Container searchContainer 	= (WK_RB_Search_Container) this.get_comp(RecDOWiegekarte.key, WIEGEKARTE.id_container_eigen);
		RB_TextField           txtContNr 		= (RB_TextField) this.get_comp(RecDOWiegekarte.key, WIEGEKARTE.container_nr);
		RB_TextField           txtContTara	 	= (RB_TextField) this.get_comp(RecDOWiegekarte.key, WIEGEKARTE.container_tara);
		WK_RB_cb_Fremdcontainer cbFremdCont		= (WK_RB_cb_Fremdcontainer) this.get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.CB_FREMDCONTAINER.key());
		
		String sDesc = "";
		String sTara = "";
		
		BigDecimal bdTara = null;
		
		if (cbFremdCont.isSelected()) {
			sTara = txtContTara.getText();
			// prüfung, ob Gewicht eine Zahl ist,
			if (!bibNUM.isAllNumber(sTara)) {
				mv._addAlarm("Tara ist keine gültige Zahl.");
			} else {
				bdTara = bibALL.convertTextToBigDecimal(sTara);
			}
			sDesc = "Container: " +  txtContNr.getText();
			
			// Gewicht eintragen
			if (mv.isOK()) {
				_setzeTaraGewicht(compWaeg, bdTara, sDesc);
			}
			
		} else {
			String id = searchContainer._get_IDContainer();
			if (!S.isEmpty(id)) {
				Rec_container recCon = new Rec_container()._fill_id(id);
				// zuerst die Korrekturwerte lesen..
				bdTara =  recCon.get_raw_resultValue_bigDecimal(CONTAINER.tara_korrektur);
				if (bdTara == null) {
					// wenn null, dann den Tara lesen...
					bdTara = recCon.get_raw_resultValue_bigDecimal(CONTAINER.tara);
				}
				
				
				sDesc = "Container: " + recCon.get_ufs_dbVal(CONTAINER.container_nr);
				//+  " / UVV:" + recCon.get_ufs_dbVal(CONTAINER.uvv_datum, " (-keine UVV-)");

				// Gewicht eintragen
				if (mv.isOK()) {
					_setzeTaraGewicht(compWaeg, bdTara, sDesc);
				}
				
			} else {
//				mv.add(new MyE2_Alarm_Message("Kein Container für Tara-Ermittlung eigetragen."));

				// prüfen, ob Einträge in der Tara-Liste vorhanden sind...
				if (rec != null && rec.is_ExistingRecord()) {
					RecList_WK_Gebinde rlGebinde = new RecList_WK_Gebinde(rec.getActualID().toString());
					if (rlGebinde.size() > 0) {
						// es gibt ein Tara-Gewicht...
						cMessageBoxAskIfZeroWeightOK msgBox = 					
								new cMessageBoxAskIfZeroWeightOK(new MyE2_String("Tara-Übernahme"), 
										new MyE2_String("JA"), 
										new MyE2_String("NEIN"), 
										true,
										true, 
										S.VT("Tara wird aus Gebindeliste übernommen."), 
										new ownActionAgentSave(mv,compWaeg));
						msgBox.set_ActionAgent4No(new ownActionAgentCancel(mv, compWaeg));
						
						
					} else {
						// keine Einträge
						mv.add(new MyE2_Alarm_Message("Es gibt weder Container-Tara noch Gebinde."));
					}
				} else {
					// keine Einträge
					mv.add(new MyE2_Alarm_Message("Es gibt weder Container-Tara noch Gebinde."));
				}
			}
		}
		
		
		return this;
		
	}
	
	
	
	
	private void _setzeTaraGewicht(WK_RB_Comp_Waegung compWaeg, BigDecimal bdTara, String sDesc) {
		String sBDTara = (bdTara != null) ? bibALL.convertBigDecimalToString(bdTara, 3) : "";
		compWaeg.get_txt_Gewicht().setText(sBDTara);
		compWaeg.get_txt_InfoHand().setText(sDesc);
	}
	
	
	
	
	

	/**
	 * Liest die Tara-Werte und trägt sie in die Wägung ein
	 * @author manfred
	 * @date 10.08.2020
	 *
	 * @param compWaeg
	 * @throws myException
	 */
	public  WK_RB_MC_WaageHandling _CopyWaegungTara( ENUM_WaegungPos waegung_pos, MyE2_MessageVector mv) throws myException {
		
		WK_RB_Comp_Waegung compWaeg = null;
		if (waegung_pos.equals(ENUM_WaegungPos.WAEGUNG_1)) {
			compWaeg = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key());
		} else {
			compWaeg = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key());
		}
		
		// Feststellen welche Wägung durchgeführt werden soll:
		WK_RB_MASK_ComponentMap_Wiegekarte 	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte  						rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		// Waegung der Original-Wiegekarte lesen
		Long idWiegekarteOri = rec.getLongDbValue(WIEGEKARTE.id_wiegekarte_storno);
		
		String sql = 
				   " SELECT * FROM " + bibE2.cTO() + "." + _TAB.waegung.fullTableName() 
				+  " WHERE " + WAEGUNG.id_wiegekarte.fieldName() + " = ? "
				+  " and "   + WAEGUNG.waegung_pos.fieldName() + " = ? " 
				;
		
		
		Rec22 _waegung = null;
		Rec22 _wiegekarteORI = null;

		
		try {
			// stornierte WK holen
//			Long lIDWKold = rec.getLongDbValue(WIEGEKARTE.id_wiegekarte_storno);
			if (idWiegekarteOri != null) {
				// die Wiegekarte lesen
				_wiegekarteORI = new Rec22(_TAB.wiegekarte)._fill_id(idWiegekarteOri);
			
				// die Wägungen lesen
				SqlStringExtended sqlex = new SqlStringExtended(sql)
						._addParameter(new Param_Long(idWiegekarteOri))
						._addParameter(new Param_Long(waegung_pos.getIntVal()));
				
				RecList22 rlWaegung = new RecList22(_TAB.waegung)._fill(sqlex);
				
				if (rlWaegung!= null && rlWaegung.size() > 0) {
					_waegung = rlWaegung.get(0);
				}
				
			}
		} catch (myException e1) {
			e1.printStackTrace();
		}
		
		RecDOWaegung waeg = compWaeg._getRecWaegung();
		
		
		String sDesc = "";
		String sTara = "";
		
		BigDecimal bdTara = null;
		
		if (_waegung!=null) {
			sTara = _waegung.get_ufs_dbVal(WAEGUNG.gewicht);
			// prüfung, ob Gewicht eine Zahl ist,
			if (!bibNUM.isAllNumber(sTara)) {
				mv._addAlarm("Tara ist keine gültige Zahl.");
			} else {
				bdTara = bibALL.convertTextToBigDecimal(sTara);
			}
			sDesc = "Werte aus WK: ";
			sDesc += _wiegekarteORI.get_ufs_dbVal(WIEGEKARTE.lfd_nr,"-");
//			sDesc += " / " + _waegung.get_ufs_dbVal(WAEGUNG.id_waegung);
						
			// Gewicht eintragen
			if (mv.isOK()) {
				_setzeTaraGewicht(compWaeg, bdTara, sDesc);
			}
			
		} else {
			mv.add(new MyE2_Alarm_Message("Es gibt keine Wiegedaten."));
		}
		
		return this;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private class ownActionAgentCancel extends XX_ActionAgent
	{
		MyE2_MessageVector _mv ;
		WK_RB_Comp_Waegung compWaeg;
		
		public ownActionAgentCancel(MyE2_MessageVector mv, WK_RB_Comp_Waegung comp) {
			_mv = mv;
			compWaeg = comp;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			compWaeg._clearHandwaegung();
		}
	}
	
	
	
	private class ownActionAgentSave extends XX_ActionAgent
	{
		MyE2_MessageVector _mv ;
		WK_RB_Comp_Waegung compWaeg;
		
		public ownActionAgentSave(MyE2_MessageVector mv, WK_RB_Comp_Waegung comp) {
			_mv = mv;
			compWaeg = comp;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			_setzeTaraGewicht(compWaeg, BigDecimal.ZERO, "Tara-Gewicht durch Gebinde.");
			_mv._addInfo("Es wurde ein 0 Tara gesetzt. Tara Gewicht kommt aus Gebindeliste.");
		}
	}
	
	
	private class cMessageBoxAskIfZeroWeightOK extends E2_MessageBoxYesNo
	{

		public cMessageBoxAskIfZeroWeightOK(MyE2_String TextTitelZeile,
				MyE2_String TextYes, MyE2_String TextNo, boolean bShowYes,
				boolean bShowNo, Vector<MyString> 	Infos,
				XX_ActionAgent ActionAgentStart) throws myException {
			super(TextTitelZeile, TextYes, TextNo, bShowYes, bShowNo, Infos,
					ActionAgentStart);
		}
	}

	
	
	
	
	
	/**
	 * Gibt den Waageuser zurück
	 * @author manfred
	 * @date 14.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public String getWaageUser() throws myException {
		WK_RB_SelField_WaageUser sel = (WK_RB_SelField_WaageUser) this.get_comp (RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_WAAGE_USER.key());
		return( sel.get_selected_dbVal() );
	}
	
	
	/**
	 * Setzt den Waageuser zurück
	 * @author manfred
	 * @date 17.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public void clearWaageUser() throws myException {
		WK_RB_SelField_WaageUser sel = (WK_RB_SelField_WaageUser) this.get_comp (RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_WAAGE_USER.key());
		sel._setActiveOrFirstDBVal("") ;
	}
	
	
	
	/**
	 * Startet die Zeitgesteuerten Temporären Wägungen aller gelisteten Waagen
	 * @author manfred
	 * @date 18.06.2020
	 *
	 * @return
	 */
	public WK_RB_MC_WaageHandling runTempWaegungen() {
				
		WK_RB_SelField_WaageUser waageUser = null;
		try {
			waageUser = (WK_RB_SelField_WaageUser) this.get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_WAAGE_USER.key());
		} catch (myException e1) {
			e1.printStackTrace();
		}
		
		String id = waageUser.get_selected_dbVal();
		if (m_ServerPushTask != null) {
			stopListening();
			
			if (S.isEmpty(id)) {
				clearWaageDisplay();
				_disableWaageDisplays();
			} else {
				if (m_listWaageDisplays.size()> 0
						&& !rec._get_ZustandWiegekarte().equals(ENUM_ZustandWiegekarte.GEDRUCKT)
						&& !rec._get_ZustandWiegekarte().equals(ENUM_ZustandWiegekarte.WAEGUNG2)) {
					
					// falls es eine aktuelle Handwägung ist, keine Waage-Daten lesen
					ENUM_ZustandWiegekarte 	m_enumZustandWiegekarte 	= ENUM_ZustandWiegekarte.GEDRUCKT;
			    	m_enumZustandWiegekarte 							= rec._get_ZustandWiegekarte();
			    	
			    	boolean bHandwaegung = false;
			    	WK_RB_Comp_Waegung comp = null;
			    	
			    	// Zweitwägung:
			    	try {
			    		if (m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.WAEGUNG1)) {
			    			comp = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key());
			    		} else {
			    			comp = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key());
			    		}
			    		bHandwaegung |= (comp._isHandwaegung() && comp.is_component_enabled());
			    	} catch (myException e) {	}
			    	
			    	// wenn keine Handwaegung, dann die Daten lesen...
			    	if (!bHandwaegung) {
			    		getTempWeights_For_All_Scales();
			    		m_ServerPushTask.start();
			    	}
					
				}
			}
		}
		return this;
		

	}
	
	

	/**
	 * beendet die Leseautomatik
	 * @author manfred
	 * @date 18.06.2020
	 *
	 * @return
	 */
	public WK_RB_MC_WaageHandling stopListening() {
		if (m_ServerPushTask != null) {
			m_ServerPushTask.stop();
		}
		return this;
	}
	
	public WK_RB_MC_WaageHandling destroyPushTask() {
		if (m_ServerPushTask != null) {
			m_ServerPushTask.stop();
			m_ServerPushTask = null;
		}
		return this;
	}
	
	
	
	/**
	 * löscht das Display der Waagen
	 * @author manfred
	 * @date 18.06.2020
	 *
	 * @return
	 */
	public WK_RB_MC_WaageHandling clearWaageDisplay() {
		_clearWaageDisplays();
		return this;
	}
	
	
	
	
	/**
	 * Löscht die Anzeigen aller Waagedisplays die angezeigt werden
	 */
	private void _clearWaageDisplays(){
		for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
			WK_RB_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
			oDisplay.clear();
		}	
	}
	
	/**
	 * disable alle Waagedisplay-Zeilen
	 * @author manfred
	 * @date 08.07.2020
	 *
	 * @throws myException
	 */
	private void _disableWaageDisplays() {
		for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
			WK_RB_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
			try {
				oDisplay.setRowEnabled(false);
			} catch (myException e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * liest die Waage aus, die angegeben ist
	 * 
	 * @param Command
	 * @return
	 * @throws myException
	 */
	public WK_RB_Waegung_Buchungssatz readWaage(Rec_WaageSettings recSettings, WaageHandlerBase.ENUM_Commands Command)	throws myException {
		WK_RB_Waegung_Buchungssatz oBuchung = null;
		
		WaageHandlerBase hWaage = null;
		WaageSatzBase oSatzBase = null;

		String sWaageErgebnis = null;

		if (recSettings != null) {
			hWaage = WaageHandlerFactory.getWaageHandler(recSettings);
		}

		
		if (hWaage != null) {
			hWaage.setCommand(Command);
			try {
				sWaageErgebnis = hWaage.leseWaage();
			} catch (Exception e) {
				sWaageErgebnis = null;
				throw new myException(e.getMessage());
			}
		}

		// jetzt den Buchungssatz aus dem Waage-Satzaufbau erstellen
		if (sWaageErgebnis != null) {
			oSatzBase = hWaage.getWaageSatz();
			
			boolean bOK = oSatzBase.getStatus().equals(ENUM_WaageResultStatus.OK);

			oBuchung = new WK_RB_Waegung_Buchungssatz(oSatzBase);
			if (bOK) {
				oBuchung.setWAAGE_DS_ORI(sWaageErgebnis);
				oBuchung.setID_WAAGE_SETTINGS(recSettings.get_ufs_dbVal(WAAGE_SETTINGS.id_waage_settings));
			} 
		}
		
		oSatzBase = null;
		hWaage = null;
		
		return oBuchung;
	}

	

	/**
	 * Methode prüft, ob der gewählte Wiegekarten-Typ die Mindestlast-Bedingungen erfordert die in der Waage angegeben sind
	 * Aktuell sind alle Wiegekarten-Typen an die Mindestlast gebunden, ausser der Dokumentarischen Verwiegung
	 * @author manfred
	 * @date   17.06.2020
	 *
	 * @return
	 */
	public boolean getWiegekartenTypErfodertMindestlast(){
		WK_RB_SelField_WiegekartenTyp typ = (WK_RB_SelField_WiegekartenTyp) this.getComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte);
		if (typ._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH)){
			return false;
		} else {
			return true;
		}
	}
	
	
	
	public boolean istLieferant() {
		WK_RB_WeWa istLieferant = (WK_RB_WeWa) this.getComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant);
		return istLieferant.isLieferant();
	}
	
	
	/**
	 * Lesen der Temporären Wägungen zur ständigen Anzeige
	 * @throws myException 
	 */
	private void getTempWeights_For_All_Scales()  {
		ENUM_ZustandWiegekarte 	m_enumZustandWiegekarte 	= ENUM_ZustandWiegekarte.GEDRUCKT;
    	m_enumZustandWiegekarte 							= rec._get_ZustandWiegekarte();

    	
    	MyE2_String  sText = new MyE2_String("Wägung %s durchführen"); 	
    	String sCount = "";
    	if (m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.WAEGUNG1)) {
    		sCount = "2";
    	} else if (m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.NEU) ||
    			m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.STAMMDATEN)) {
    		sCount = "1";
    	} else {
    		sCount = "";
    	}
    	String sTextForButtons = String.format(sText.toString(), sCount);
    	
    	
    	// die Wägungen müssen nur durchgeführt werden, wenn noch gewogen werden
		// muss...
		WK_RB_Waegung_Buchungssatz oSatz = null;
		for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
			WK_RB_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
			try {
				
				// wenn das Waagedisplay kein Gewicht mehr anfodern soll, dann einfach löschen...
				if (!oDisplay.isRequestingWeight()){
					oDisplay.clear();
					continue;
				}
				
				oSatz = readWaage(oDisplay.getWaageSettings(),	WaageHandlerBase.ENUM_Commands.CMD_READTEMP);
				
				if (oSatz != null) {
					if (oSatz.get_WaageResultStatus().equals(ENUM_WaageResultStatus.OK)){
						oDisplay.setGewicht(oSatz.getW_BRUTTO_GEWICHT());
						oDisplay.setButtonText(sTextForButtons);

						//
						// prüfen, ob die Wägung unter der mindeslast der Waage liegt. Wenn ja, keine Wägung erlauben
						//
						oDisplay.setRowEnabled(true);
						if (m_bMindestlastErforderlich && getWiegekartenTypErfodertMindestlast()) {

							Rec_WaageSettings oSettings = oDisplay.getWaageSettings();
							BigDecimal bdMindestlast = oSettings.getRawResultValueAsBigDecimal(WAAGE_SETTINGS.mindestlast, BigDecimal.ZERO);
							
							if (bdMindestlast.compareTo(BigDecimal.ZERO)<0){
								oDisplay.setFehler(new MyE2_String(	"Mindestlast der Waage ist nicht angegeben! Keine Wägung möglich!"));
								oDisplay.setRowEnabled(false);
								continue;
							} else if (oSatz.getGEWICHT().compareTo(bdMindestlast) < 0 ){
									oDisplay.setFehler(new MyE2_String(	"Mindestlast der Waage (" + bibALL.convertBigDecimalToString(bdMindestlast,0) + ") ist unterschritten! Keine Wägung möglich!"));
									oDisplay.setRowEnabled(false);
									continue;
								
							} else if(m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.WAEGUNG1)){
								// Falls es die Zweitwägung ist, noch prüfen, ob das Nettogewicht < Mindestlast wird
//								WK_RB_Waegung_Buchungssatz oBuchung1 = m_oWKHandler.get_BuchungWaegung1();
								RecDOWaegung1 recWaeg1 = rec._get_Waegung1();
								
								if (recWaeg1 != null){
									BigDecimal bdGewicht1 = recWaeg1.get_raw_resultValue_bigDecimal(WAEGUNG.gewicht);
									
									if ( oSatz.getGEWICHT().subtract(bdGewicht1).abs().compareTo(bdMindestlast) < 0 ){
										oDisplay.setFehler(new MyE2_String(	"Nettogewicht wird kleiner als die Mindestlast! Keine Wägung möglich!"));
										oDisplay.setRowEnabled(false);
										continue;
									} 
								}
							} 
						}
						
	
						//
						// prüfen, ob bei WE die doppelte Mindestlast erreicht ist, sonst Meldung wenn gewünscht
						//
						if (m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.NEU) || m_enumZustandWiegekarte.equals(ENUM_ZustandWiegekarte.STAMMDATEN)){
							if (istLieferant()){
								// Alle Vorrausetzungen gegeben. Jetzt kann man prüfen, ob die Warnung gegeben werden muss.
								if (m_bWarnungDoppelteMindestlastBeiWE && getWiegekartenTypErfodertMindestlast()) {
									Rec_WaageSettings oSettings = oDisplay.getWaageSettings();
									BigDecimal bdMindestlast = oSettings.get_raw_resultValue_bigDecimal(WAAGE_SETTINGS.mindestlast, BigDecimal.ZERO); // get_MINDESTLAST_bdValue(BigDecimal.ZERO);
									BigDecimal bdMindestlastDoppelt = bdMindestlast.multiply(new BigDecimal(2));
									if (bdMindestlast.compareTo(BigDecimal.ZERO) > 0){
										if (oSatz.getGEWICHT().compareTo(bdMindestlastDoppelt) < 0 ){
											oDisplay.setFehler(new MyE2_String(	"ACHTUNG: Bei Differenzverwiegung muss das Eingangsgewicht mindestens die doppelte Mindestlast (" + bibALL.convertBigDecimalToString(bdMindestlast,0) + ") betragen: " ));
										} 
									}
								}
							} 
						}
						
						
						
						// prüfen, ob es die 2.Wägung ist, wenn ja prüfen, ob
						// Brutto/Tara mit WE/WA passen
						boolean bBruttoTaraOk = true;
						if (m_enumZustandWiegekarte == ENUM_ZustandWiegekarte.WAEGUNG1) {
							BigDecimal bdGewicht1 = rec._get_Waegung1().get_raw_resultValue_bigDecimal(WAEGUNG.gewicht);
							BigDecimal bdGewicht2 = oSatz.getGEWICHT();
	
							if (bdGewicht2 != null) {
								
								String sLiefer = rec.getUfs(WIEGEKARTE.ist_lieferant); 
								
								if (sLiefer == null || sLiefer.equals("N")) {
									bBruttoTaraOk = bdGewicht1.compareTo(bdGewicht2) < 0;
								} else {
									bBruttoTaraOk = bdGewicht1.compareTo(bdGewicht2) > 0;
								}
							}
						}
						if (!bBruttoTaraOk) {
							oDisplay.setFehler(new MyE2_String(	"Achtung: Brutto/Tara passt nicht zur Wiegung!"));
						}
					} else {
						oDisplay.setFehler(new MyE2_String(oSatz.get_WaageFehlerBeschreibung()));
					}
				}
			} catch (myException e) {
				oDisplay.setFehler(new MyE2_String(e.ErrorMessage));
			} catch (Exception ex){
				// allgemeiner Fehler
				oDisplay.setFehler(new MyE2_String(ex.getMessage()));
			}
		}
	}
	

	
	
	/**
	 * führt eine Wägung durch und gibt einen initialisierten Buchungssatz zurück
	 * 
	 * UNTERSCHIED zu READTEMP: Die Wägung wird in den Speicher der Waage geschrieben, weil das Kommando ein 
	 * anderes ist.
	 * 
	 * @param RECORD_WAAGE_SETTINGS  recSettings
	 * @throws myException
	 */
	private WK_RB_Waegung_Buchungssatz readWeightFromScale(Rec_WaageSettings recSettings) throws myException {
		// Wägung initiieren!
		WK_RB_Waegung_Buchungssatz oSatz = null;
		
		try {
			oSatz = readWaage(recSettings, WaageHandlerBase.ENUM_Commands.CMD_READ);
		} catch (myException e) {
			throw new myException(e.getMessage());
		} catch (Exception ex) {
			// allgemeiner Fehler abfangen
			throw new myException(ex.getLocalizedMessage());
		}
		return oSatz;
	}
	

	
	
	/**
	 * Erzeugt eine neue Wägung (automatisch 1. oder 2. Wägung) 
	 * @author manfred
	 * @date 14.07.2020
	 *
	 * @param recSetting
	 * @param idUserWaegung
	 * @return
	 * @throws myException
	 */
	private RecDOWaegung createWaegungBuchung( Rec_WaageSettings recSetting, String idUserWaegung, MyE2_MessageVector mv) throws myException {
		
		// Feststellen welche Wägung durchgeführt werden soll:
		WK_RB_MASK_ComponentMap_Wiegekarte 	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte  						rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		// prüfung, ob alle Vorraussetzungen gegeben sind für eine Wäegung
//		checkDataContentsForWaegung(mv);
		WK_RB_MC_ValidateOnSave valOnSave = new WK_RB_MC_ValidateOnSave(compMap, mv);
		valOnSave._validateBeforeSave();
		
		if (mv.hasAlarms()) {
			return null;
		}
		
		
		
		
		int iWaegungPos = 0;
		ENUM_ZustandWiegekarte zustand =rec._get_ZustandWiegekarte(); 
		if (  zustand.equals(ENUM_ZustandWiegekarte.NEU) || zustand.equals(ENUM_ZustandWiegekarte.STAMMDATEN)  ) {
			iWaegungPos = 1;
		} else if (zustand.equals(ENUM_ZustandWiegekarte.WAEGUNG1)) {
			iWaegungPos = 2;
		} else {
			iWaegungPos = 0;
		}

		String sWaegungPos = new Integer(iWaegungPos).toString();

		
		// neuer Buchunssatz
		WK_RB_Waegung_Buchungssatz oSatz = null;


		switch (iWaegungPos) {
		case 1:
			if (rec._get_IDWaegung1() != null) {
				throw new myException(new MyE2_String("Wägung 1 wurde schon durchgeführt!").CTrans());
			}
			break;
		case 2:

			if (rec._get_IDWaegung2() != null) {
				throw new myException(new MyE2_String("Wägung 2 wurde schon durchgeführt!").CTrans());
			}

			// Prüfung ob schon eine Adresse eingegeben wurde: Zur schnelleren Wägung reicht es, die Adresse vor der 2. Wägung eingegeben zu haben
			if (bibALL.isEmpty(rec.get_ufs_dbVal(WIEGEKARTE.id_artikel_bez))  || 
				       ( 
						bibALL.isEmpty(rec.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant) ) &&
						bibALL.isEmpty(rec.get_ufs_dbVal(WIEGEKARTE.adresse_lieferant))
				       )
				){
				throw new myException(new MyE2_String("Vor der 2. Wägung muss die Adresse und die Sorte ausgewählt sein.").CTrans());
			}
			
			
			break;
		default:
			throw new myException(new MyE2_String("Es wurde keine gültige Wägungsnummer erkannt!").CTrans());
		}

		String sIDWiegekarte = null;
		if (zustand.equals(ENUM_ZustandWiegekarte.NEU)) {
			sIDWiegekarte = "SEQ_WIEGEKARTE.CURRVAL";
		} else {
			sIDWiegekarte = rec.getActualID().toString();
		}

		// ******************************************
		// eigentliche Waegung:
		// holen des aktuellen Waagewertes
		//
		
		oSatz = this.readWeightFromScale(recSetting);

		//
		// prüfen, ob die Wägung unter der mindeslast der Waage liegt. Wenn ja, keine Wägung erlauben
		// ausser der Wiegekartentyp setzt die Mindestlast ausser Funktion.
		//
		if (getWiegekartenTypErfodertMindestlast()  && m_bMindestlastErforderlich) {
			
			BigDecimal bdMindestlast = recSetting.get_raw_resultValue_bigDecimal(WAAGE_SETTINGS.mindestlast, BigDecimal.ONE.negate());
			
			//
			// Prüfung ob das Gewicht der Mindestlast entspricht 
			//
			if (bdMindestlast.compareTo(BigDecimal.ZERO) < 0){
				throw new myException(new MyE2_String("Mindestlast der Waage ist nicht angegeben! Keine Wägung möglich!").CTrans());
			} else {
				if (oSatz.getGEWICHT().compareTo(bdMindestlast) < 0 ){
					throw new myException(new MyE2_String("Mindestlast der Waage ist unterschritten! Keine Wägung möglich!").CTrans());
				}
			}
			
			//
			// Prüfung, ob bei 2.Waegung das Nettogewicht über der Mindestlast liegt
			// 
			if(iWaegungPos == 2){
				RecDOWaegung1 waeg1 = rec._get_Waegung1();
				if (waeg1 != null){
					BigDecimal bdGewicht1 = waeg1.get_raw_resultValue_bigDecimal(WAEGUNG.gewicht);
					if ( oSatz.getGEWICHT().subtract(bdGewicht1).abs().compareTo(bdMindestlast) < 0 ){
						throw new myException(new MyE2_String("Nettogewicht wird kleiner als die Mindestlast! Keine Wägung möglich!").CTrans());
					}
				}
			}
		}

		
		
		RecDOWaegung recWaegung = null;
		
		if (oSatz != null && oSatz.get_WaageResultStatus().equals(ENUM_WaageResultStatus.OK)) {

			oSatz.setWAEGUNG_POS(sWaegungPos);
			oSatz.setID_WIEGEKARTE(sIDWiegekarte);
			oSatz.setID_WAEGUNG("SEQ_WAEGUNG.NEXTVAL");
			oSatz.setID_USER_WAEGUNG(idUserWaegung);
			
			switch (iWaegungPos) {
			case 1:
				RecDOWaegung1 r1 =  (RecDOWaegung1) new RecDOWaegung1(MASK_STATUS.NEW)._fill_from(oSatz);
				recWaegung = r1;
				break;
			case 2:
				RecDOWaegung2 r2 = (RecDOWaegung2) new RecDOWaegung2(MASK_STATUS.NEW)._fill_from(oSatz);
				recWaegung = r2;
				break;
			default:
				throw new myException(new MyE2_String("Es wurde keine gültige Wägungsnummer angegeben!").CTrans());
			}
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oSatz.get_WaageFehlerBeschreibung()));
		}
		
		return recWaegung;
	}
	
	
	
	
	
	
	/**
	 * Aufnehmen der Camera-Bilder der Capture-Group, welche dem Modul "CAPTURE_WAAGE_AUTOMATIK" zugeordnet sind.
	 * Es wird noch abgefragt, welche Waage zuletzt genutzt wurde.
	 * 
	 * Adaption von WK_WiegekartenHandler
	 *  
	 * @author manfred
	 * @date 01.10.2020
	 *
	 * @return
	 * @throws myException
	 */
	public boolean captureImages(RecDOWiegekarte recWK,  Rec_WaageSettings recSetting){
		boolean bRet = false;
		
		// Falls der Schalter nicht gesetzt ist, wird abgebrochen
		boolean bCapture = false;
		try {
			bCapture = ENUM_MANDANT_DECISION.WAAGE_AUTO_CAPTURE.is_YES() ;
		} catch (myException e1) {
			bCapture = false;
		}

		String id_waage_settings = "";
		
		
		if (bCapture){
			try {
				// aktuelle Waage-Settings ermitteln, wenn null, ist es eine Handverwiegung
				if (recSetting == null ){
					id_waage_settings = "HAND";
				} else {
					id_waage_settings = recSetting.getUfs(WAAGE_SETTINGS.id_waage_settings);
				}
				
				String id_wiegekarte = Long.toString(recWK.getId());
				
				// Capture-Gruppe ermitteln 
				IMG_ImageCapture_Handler oIMGCapture = new IMG_ImageCapture_Handler("CAPTURE_WAAGE_AUTOMATIK",id_waage_settings);
				try {
					oIMGCapture.captureSnapshots(id_wiegekarte);
					bRet = true;
				} catch (myException e) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Beim Erfassen der Bilder ist ein Fehler aufgetreten!")));
					bRet = false;
				}
			} catch (myException e) {
				bRet = false;
			}
		}
		
		return bRet;
	}
	

	
}
