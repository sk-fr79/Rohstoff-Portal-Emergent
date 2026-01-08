/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.O;
import panter.gmbh.indep.enumtools.IF_enumSammlerMitSchubladen;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_ErmittlePassendeHandelsDef;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_FehlerBericht;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_FehlerBerichte;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_FehlerPruefung_SystemischFehler;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Station;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Stationen;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufung;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufungen;

/**
 * @author martin
 * 
 * abstractere implementierung der XX_ActionAgent-klasse _HD_ActionAgent_FindTaxDef__ABSTRACT
 *
 */
public class PdServiceFindHandelsdefSettings {
	
	/**
	 * interface, nachdem ein station-creator erzeugt werden muss
	 * @author martin
	 *
	 */
	public static interface StationsCreator {
		public abstract HD_Stationen 	createStationen() throws myException;
		public abstract Long    		getRecordId() throws myException;  				//methode muss fuer die statistik eine RecordID liefern (fuhre, ort, oder BG_Vektor ...)
	}
	
	public static interface ActionHandlerWhenFound {
		/**
		 * 
		 * @param einstufung
		 * @param IDRecord (kann jede art von warenbewegung sein (fuhre, fuhrenort, bewegungsvektor)
		 * @throws myException
		 */
		public void applyAction(HD_WarenBewegungEinstufungen einstufungen, HD_Station stationEK, HD_Station stationVK, EN_STATUSBERICHT fehlerDef,  Long IDRecord) throws myException;
	}
	
	
	public enum EN_STATUSBERICHT implements IF_enumSammlerMitSchubladen<EN_STATUSBERICHT, Long> {
		UNVOLLSTAENDIG("Fuhre hat unvollständige Angaben", false)
		,SONSTIGE_FEHLER("Sonstige Fehler bei Adressprüfung", false)
		,OK("Einordung war erfolgreich", true)
		,UNDEFINIERT("Undefinierter Fehler", false)
		,ERLAUBTE_MEHRFACHZUWEISUNG("Erlaubte Mehrfachzuweisung (für Auswahl)", false)
		,UNERLAUBTE_MEHRFACHZUWEISUNG("Verbotene Mehrfachzuweisung", false)
		,KEINE_HANDELSDEF_GEFUNDEN("Keine Handelsdefinition gefunden", false)
		;

		private String langText = null;
		private boolean erfolgreich = false;
		
		private EN_STATUSBERICHT(String langText, boolean p_erfolgreich) {
			this.langText = langText;
			this.erfolgreich = p_erfolgreich;
		}

		@Override
		public EN_STATUSBERICHT[] getValues() {
			return EN_STATUSBERICHT.values();
		}

		public String getLangText() {
			return langText;
		}

		public boolean isErfolgreich() {
			return erfolgreich;
		}
		
	}
	
	

	
	/**
	 * was wird gemacht, wenn eine definition gefunden wurde
	 * @throws myException
	 */
	private StationsCreator  				stationsCreator = null; 
	private ActionHandlerWhenFound 			actionHandlerWhenFound = null;
	private HD_Stationen  					hdStationen = null;
	private HD_WarenBewegungEinstufungen	warenbewegungEinstufungen = null;


	/**
	 * 
	 * @param p_stationsCreator
	 * @param p_actionHandlerWhenFound
	 * @param protokollSammler
	 * @param includeInactive
	 * @param passivLauf heisst: es wird kein auswahlpopup angezeigt, wenn es mehrere moeglich handelsdefinitionen gibt
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public PdServiceFindHandelsdefSettings _findSettingsFuhre(	StationsCreator 						p_stationsCreator, 
																ActionHandlerWhenFound 					p_actionHandlerWhenFound, 
																HashMap<EN_STATUSBERICHT, VEK<Long>> 	protokollSammler,
																boolean  								includeInactive,
																boolean    								passivLauf,
																MyE2_MessageVector  					mv) throws myException {
		
		this.stationsCreator = 			p_stationsCreator;
		this.actionHandlerWhenFound = 	p_actionHandlerWhenFound;
		
		if (this.stationsCreator==null) {
			throw new myException(this,"A stationsCreator - instance MUST be defined");
		}
		
		warenbewegungEinstufungen = new HD_WarenBewegungEinstufungen();
		
		//zuerst die vollstaendigkeit der angaben pruefen
		HD_FehlerBerichte  	vVollstaendigFehler = new HD_FehlerBerichte();

		//zuerst alle, auch die doppelten stationen sammeln, um die Fehlerliste zu bekommen
		this.hdStationen = 	stationsCreator.createStationen();
		
		//DEBUG._print("anzahl gefundene stationen ..."+hdStationen.size());

		
		//zuerst systemische felderpruefungen
		vVollstaendigFehler.addAll(new HD_FehlerPruefung_SystemischFehler().get_vFehlerSystemisch());
		
		//die stationen geben einen fehlerstatus zurueck (leer wenn alles ok ist)
		vVollstaendigFehler.addAll(hdStationen.get_vFehlerVect());
		
		//es muesste jetzt immer genau eine ek -und eine vk-station geben
		if (hdStationen.getHD_StationenEK(true).size()==0) {
			vVollstaendigFehler.add(new HD_FehlerBericht(true, new MyE2_String("Fuhrendefinition, Ladeseite"),  new MyE2_String("Stationen "),  new MyE2_String("nicht vorhanden")));
		}
		if (hdStationen.getHD_StationenVK(true).size()==0) {
			vVollstaendigFehler.add(new HD_FehlerBericht(false, new MyE2_String("Fuhrendefinition, Abladeseite"),  new MyE2_String("Stationen "),  new MyE2_String("nicht vorhanden")));
		}
	
		
		//wenn keine unvollstaendigen ortspaare gefunden wurden, dann versuche handelsdefs zu finden
		if (vVollstaendigFehler.size()>0) {
			EN_STATUSBERICHT.UNVOLLSTAENDIG.add(	protokollSammler,	EN_STATUSBERICHT.UNVOLLSTAENDIG, stationsCreator.getRecordId());
			
			//DEBUG._print(vVollstaendigFehler.get_FehlerAsMessageVector().get_MessagesAsText());
			
			this.actionHandling(					warenbewegungEinstufungen, null,null,	EN_STATUSBERICHT.UNVOLLSTAENDIG, stationsCreator.getRecordId());
		} else {

			//2014-01-29: jetzt die Stationsfehler aufgrund der Pruefung der Handelserlaubnis, der korrekten definition der Firmen usw. pruefen
			if (hdStationen.get_oMV_SammlerVonFehlern().get_bIsOK()) { 
			
				HD_Stationen  			vStationenSingleEK 		= 	hdStationen.getHD_StationenEK(true);
				HD_Stationen  			vStationenSingleVK 		= 	hdStationen.getHD_StationenVK(true);
	
				//2014-01-02: bescharaenkung auf 1 zu 1 - relationen, keine mehrfach-abarbeitung
				if (vStationenSingleEK.size()>1 || vStationenSingleEK.size()>1) {
					//sollte nicht vorkommen
					EN_STATUSBERICHT.UNDEFINIERT.add(	protokollSammler,			EN_STATUSBERICHT.UNDEFINIERT, stationsCreator.getRecordId());
					this.actionHandling(				warenbewegungEinstufungen, 	null, null ,EN_STATUSBERICHT.UNDEFINIERT, stationsCreator.getRecordId());
				} else {
				
					warenbewegungEinstufungen.addAll(
							new HD_ErmittlePassendeHandelsDef(vStationenSingleEK.get(0),vStationenSingleVK.get(0),includeInactive).getWarenBewegungEinstufungen()
							);
					
					if (warenbewegungEinstufungen.size()==0) 	{
						EN_STATUSBERICHT.KEINE_HANDELSDEF_GEFUNDEN.add(	protokollSammler, 			EN_STATUSBERICHT.KEINE_HANDELSDEF_GEFUNDEN, stationsCreator.getRecordId());
						this.actionHandling(	warenbewegungEinstufungen, vStationenSingleEK.get(0), vStationenSingleVK.get(0),	EN_STATUSBERICHT.KEINE_HANDELSDEF_GEFUNDEN, stationsCreator.getRecordId());
					} else if (warenbewegungEinstufungen.size()==1) {
						EN_STATUSBERICHT.OK.add(protokollSammler, 			EN_STATUSBERICHT.OK, stationsCreator.getRecordId());
						this.actionHandling(	warenbewegungEinstufungen, vStationenSingleEK.get(0), vStationenSingleVK.get(0), 	EN_STATUSBERICHT.OK, stationsCreator.getRecordId());
					} else if (warenbewegungEinstufungen.size()>1) {
						if (warenbewegungEinstufungen.get_bVectorHasAllowedMultidefinitions()) {
							EN_STATUSBERICHT.OK.add(protokollSammler,			EN_STATUSBERICHT.ERLAUBTE_MEHRFACHZUWEISUNG, stationsCreator.getRecordId());
							this.actionHandling(warenbewegungEinstufungen, vStationenSingleEK.get(0), vStationenSingleVK.get(0), 	EN_STATUSBERICHT.ERLAUBTE_MEHRFACHZUWEISUNG, stationsCreator.getRecordId());
							if (!passivLauf) {
								new POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl(warenbewegungEinstufungen);
							}
						} else {
							EN_STATUSBERICHT.OK.add(protokollSammler,			EN_STATUSBERICHT.UNERLAUBTE_MEHRFACHZUWEISUNG, stationsCreator.getRecordId());
							this.actionHandling(warenbewegungEinstufungen, vStationenSingleEK.get(0), vStationenSingleVK.get(0), 	EN_STATUSBERICHT.UNERLAUBTE_MEHRFACHZUWEISUNG, stationsCreator.getRecordId());
						}
					}
				} 
			} else {
				EN_STATUSBERICHT.OK.add(protokollSammler,		EN_STATUSBERICHT.SONSTIGE_FEHLER, stationsCreator.getRecordId());
				this.actionHandling(warenbewegungEinstufungen, null,null, 	EN_STATUSBERICHT.SONSTIGE_FEHLER, stationsCreator.getRecordId());
				mv._add(hdStationen.get_oMV_SammlerVonFehlern());
			}
		}

		return this;
	}
	
	/*
	 * ausfuehren der aktionen, falls etwas definiert wurde
	 */
	private void actionHandling(HD_WarenBewegungEinstufungen einstufungen, HD_Station stationEK, HD_Station stationVK, EN_STATUSBERICHT fehlerDef,  Long IDRecord) throws myException {
		if (this.actionHandlerWhenFound!=null) {
			this.actionHandlerWhenFound.applyAction(einstufungen, stationEK, stationVK, fehlerDef, IDRecord);
		}
	}
	
	
	/**
	 * auswahl-popup-window fuer verschiedene, erlaubt ergebnisse zur auswahl fuer den anwender
	 */
	private class  POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl extends E2_BasicModuleContainer {
		
		public POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl(HD_WarenBewegungEinstufungen vMultipleMoeglichkeiten) throws myException	{
			super();
			boolean bStart = true;
			
			MyE2_Grid oGridHelp = null;
			for (HD_WarenBewegungEinstufung  oStufung: vMultipleMoeglichkeiten) {
				if (bStart) {
					oGridHelp = oStufung.get_GridMitFuhrenEinstufung(new MyE2_String("Unterschiedliche Bewertung möglich ..."),true,new ownButton(oStufung));
				} else {
					oGridHelp.append_Grid(oStufung.get_GridMitFuhrenEinstufung(null, false, new ownButton(oStufung)), null, null);
				}
				
				bStart=false;                          //ab dem zweiten keine fuhreninfo mehr anzeigen
			}
			
			this.add(oGridHelp);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(500), new MyE2_String("Bitte wählen Sie den gewünschten Einstufungsvorschlag aus..."));
		}
		
		
		private class ownButton extends MyE2_Button {
			public ownButton(HD_WarenBewegungEinstufung  oEinstufung) throws myException {
				super(E2_ResourceIcon.get_RI("ok_big.png"));
				this.add_oActionAgent(new ownActionAgent(oEinstufung));
				this.setToolTipText(new MyE2_String("Die USt./Steuertext-Definition in dieser Zeile auswählen").CTrans());
			}
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			private HD_WarenBewegungEinstufungen 	einstufungen = new HD_WarenBewegungEinstufungen();
			public ownActionAgent(HD_WarenBewegungEinstufung  p_einstufung) throws myException {
				super();
				this.einstufungen.add(p_einstufung);
			}
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				if (actionHandlerWhenFound!=null) {
					PdServiceFindHandelsdefSettings.this.actionHandling(einstufungen, null, null, EN_STATUSBERICHT.OK, stationsCreator.getRecordId());
				}
			}
		}
		
		
	}


	/**
	 * 
	 * @return found vector of handelsdefs, when not initialiszed then null, when initialized and nothing was found then empty vector
	 */
	public HD_WarenBewegungEinstufungen getWarenbewegungEinstufungen() {
		return warenbewegungEinstufungen;
	}

	/**
	 * 
	 * @return found HD_WarenBewegungEinstufung, when not found or multiples found, then null
	 * @throws myException 
	 */
	public HD_WarenBewegungEinstufung   getWarenbewegungEinstufungSingle() throws myException {
		return O.getSingleMember(O.NN(warenbewegungEinstufungen, new HD_WarenBewegungEinstufungen()));
	}
	

	
	
}
