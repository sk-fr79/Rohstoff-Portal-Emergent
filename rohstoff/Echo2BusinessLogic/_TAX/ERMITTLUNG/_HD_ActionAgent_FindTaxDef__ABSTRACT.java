package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public abstract class _HD_ActionAgent_FindTaxDef__ABSTRACT extends XX_ActionAgent
{

	 
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		
		//zuerst die vollstaendigkeit der angaben pruefen
		HD_FehlerBerichte  	vVollstaendigFehler = 								new HD_FehlerBerichte();
		
		//Vector feur die sammlung der fehlergrid, die angezeigt werden, um dem benutzer den sachverhalt klarzumachen
		Vector<MyE2_Grid>  					vFehlerKeineOderMultipleHandelsdefinition_Grids = 	new Vector<MyE2_Grid>();

		//zuerst alle, auch die doppelten stationen sammeln, um die Fehlerliste zu bekommen
		HD_Stationen  vAlle_Beteiligten_Stationen = 	this.create_Stationen(oExecInfo);

		//zuerst systemische felderpruefungen
		vVollstaendigFehler.addAll(new HD_FehlerPruefung_SystemischFehler().get_vFehlerSystemisch());
		
		//die stationen geben einen fehlerstatus zurueck (leer wenn alles ok ist)
		vVollstaendigFehler.addAll(vAlle_Beteiligten_Stationen.get_vFehlerVect());
		
		//es muesste jetzt immer genau eine ek -und eine vk-station geben
		if (vAlle_Beteiligten_Stationen.getHD_StationenEK(true).size()==0) {
			vVollstaendigFehler.add(new HD_FehlerBericht(true, new MyE2_String("Fuhrendefinition, Ladeseite"),  new MyE2_String("Stationen "),  new MyE2_String("nicht vorhanden")));
		}
		if (vAlle_Beteiligten_Stationen.getHD_StationenVK(true).size()==0) {
			vVollstaendigFehler.add(new HD_FehlerBericht(false, new MyE2_String("Fuhrendefinition, Abladeseite"),  new MyE2_String("Stationen "),  new MyE2_String("nicht vorhanden")));
		}
		
		
		//wenn keine unvollstaendigen ortspaare gefunden wurden, dann versuche handelsdefs zu finden
		if (vVollstaendigFehler.size()>0) {
			new POPUP_FuhrenstationenUnvollstaendig(vVollstaendigFehler, new MyE2_String("Fehlerinformation"));
		} else {

			//2014-01-29: jetzt die Stationsfehler aufgrund der Pruefung der Handelserlaubnis, der korrekten definition der Firmen usw. pruefen
			if (vAlle_Beteiligten_Stationen.get_oMV_SammlerVonFehlern().get_bIsOK()) { 
			
				HD_Stationen  			vStationenSingleEK 		= 	vAlle_Beteiligten_Stationen.getHD_StationenEK(true);
				HD_Stationen  			vStationenSingleVK 		= 	vAlle_Beteiligten_Stationen.getHD_StationenVK(true);
	
				//2014-01-02: bescharaenkung auf 1 zu 1 - relationen, keine mehrfach-abarbeitung
				if (vStationenSingleEK.size()>1 || vStationenSingleVK.size()>1) {
					
					new POPUP_FuhrenstationenNurEinsZuEinsErlaubt(vVollstaendigFehler, new MyE2_String("Fehlerinformation"));
					
				} else {
				
					HD_ErmittlePassendeHandelsDef  	oHD_Ermittler = new HD_ErmittlePassendeHandelsDef(vStationenSingleEK.get(0),vStationenSingleVK.get(0));
					HD_WarenBewegungEinstufungen 		vHandelsdefsGefunden = 	oHD_Ermittler.getWarenBewegungEinstufungen();
					
					if (vHandelsdefsGefunden.size()==0) 	{
						
						vFehlerKeineOderMultipleHandelsdefinition_Grids.add(vHandelsdefsGefunden.get_GridWithHandelsRelation(
											new MyE2_String(
												"Es wurde KEIN passender Eintrag für folgende Sachverhalte in der Tabelle <USt./Steuertext> gefunden!")));
						
					} else if (vHandelsdefsGefunden.size()==1) {
						
						this.setze_handelsdefinition_mit_vorgesehener_aktion(vHandelsdefsGefunden);
						
					} else if (vHandelsdefsGefunden.size()>1) {
						
						//2014-01-02: mehrfache moeglichkeit: erlaubte dubletten oder nicht (dann fehlermeldung)
						if (vHandelsdefsGefunden.get_bVectorHasAllowedMultidefinitions()) {
							
							new POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl(vHandelsdefsGefunden);
							
						} else {
						
							vFehlerKeineOderMultipleHandelsdefinition_Grids.add(vHandelsdefsGefunden.get_GridWithHandelsRelation(
												new MyE2_String(
													"Es wurden MEHRE Einträge für folgende " +
													"Sachverhalte in der Tabelle <USt./Steuertext> gefunden, " +
													"die unterschiedliche Einstufungen erzeugen !")));
						}
					}
					
					if (vFehlerKeineOderMultipleHandelsdefinition_Grids.size()>0)
					{
						new POPUP_Handelsdef_nicht_vorhanden_oder_mehrdeutig(vFehlerKeineOderMultipleHandelsdefinition_Grids,new MyE2_String("Fehler ..."));
					}
					
				} 
			
			}
			
			//alle meldungen veroeffentlichen
			bibMSG.add_MESSAGE(vAlle_Beteiligten_Stationen.get_oMV_SammlerVonFehlern());
			
		}
	}

	
	
	
	private void setze_handelsdefinition_mit_vorgesehener_aktion(HD_WarenBewegungEinstufungen vHandelsdefsGefunden) throws myException {
		//sicherheithalber
		if (vHandelsdefsGefunden.size()!=1) {
			throw new myException(this,"setze_handelsdefinition_mit_vorgesehener_aktion: only vector with 1 elements allowed!");
		}
		
		if (vHandelsdefsGefunden.isUpdatingBothSidesAllowedForAllMembers())	{
			
			this._action_if_found_uniqueDefinition(vHandelsdefsGefunden.get(0));
			
			//meldung(en) generieren
			vHandelsdefsGefunden.generate_Messages_4_User();						
			
		} else {

			new HD_PopupZeigeFuhreneinstufung(vHandelsdefsGefunden, 
					this.get_Message_Eindeutige_Fuhreneinstufung_Kann_nicht_uebernommen_werden(),
					true);
		
		}

	}
	
	
	public abstract MyE2_String   get_Message_Eindeutige_Fuhreneinstufung_Kann_nicht_uebernommen_werden();
	
	/** 
	 * muss einen ungefilterten Vector mit stationen uebergeben
	 * @return
	 * @throws myException
	 */
	public abstract HD_Stationen create_Stationen(ExecINFO oExecInfo) throws myException;
	
	/**
	 * was wird gemacht, wenn eine definition gefunden wurde
	 * @throws myException
	 */
	public abstract void  _action_if_found_uniqueDefinition(HD_WarenBewegungEinstufung oHD_Fuhreneinstufung) throws myException;
	
	
	private class  POPUP_FuhrenstationenUnvollstaendig extends E2_BasicModuleContainer
	{
		
		public POPUP_FuhrenstationenUnvollstaendig(HD_FehlerBerichte vVollstaendigkeitsFehler  , MyE2_String cFehlerInfos) throws myException
		{
			super();
			this.add(vVollstaendigkeitsFehler.get_GridWithMeldungen());
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(500), cFehlerInfos);
		}
	}
	
	
	
	private class  POPUP_FuhrenstationenNurEinsZuEinsErlaubt extends E2_BasicModuleContainer
	{
		
		public POPUP_FuhrenstationenNurEinsZuEinsErlaubt(HD_FehlerBerichte vVollstaendigkeitsFehler  , MyE2_String cFehlerInfos) throws myException
		{
			super();
			this.add(vVollstaendigkeitsFehler.get_GridWithMeldungen());
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(500), cFehlerInfos);
		}
	}

	
	private class  POPUP_Handelsdef_nicht_vorhanden_oder_mehrdeutig extends E2_BasicModuleContainer
	{
		private Vector<MyE2_Grid>  vGridsWithInfos = null;
		
		public POPUP_Handelsdef_nicht_vorhanden_oder_mehrdeutig(Vector<MyE2_Grid>  GridsWithInfos, MyE2_String cFehlerInfos) throws myException
		{
			super();
			
			this.vGridsWithInfos=GridsWithInfos;
			
			MyE2_Grid oGridBase = this.vGridsWithInfos.get(0);
			
			if (this.vGridsWithInfos.size()>1)
			{
				for (int i=1; i<this.vGridsWithInfos.size();i++)
				{
					oGridBase.append_Grid(this.vGridsWithInfos.get(i), new Separator(), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
				}
			}
			this.add(oGridBase, E2_INSETS.I_2_2_2_2);
			

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(400), cFehlerInfos);
		}
	}
	

	
	
	/**
	 * auswahl-popup-window fuer verschiedene, erlaubt ergebnisse zur auswahl fuer den anwender
	 */
	private class  POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl extends E2_BasicModuleContainer
	{
		
		public POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl(HD_WarenBewegungEinstufungen vMultipleMoeglichkeiten) throws myException
		{
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
			public ownButton(HD_WarenBewegungEinstufung  oEinstufung) throws myException
			{
				super(E2_ResourceIcon.get_RI("ok_big.png"));
				this.add_oActionAgent(new ownActionAgent(oEinstufung));
				this.setToolTipText(new MyE2_String("Die USt./Steuertext-Definition in dieser Zeile auswählen").CTrans());
			}
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			private HD_WarenBewegungEinstufungen  vFuhrenEinstufung = null;
			public ownActionAgent(HD_WarenBewegungEinstufung  oEinstufung) throws myException
			{
				super();
				this.vFuhrenEinstufung = new HD_WarenBewegungEinstufungen();
				this.vFuhrenEinstufung.add(oEinstufung);
			}
			
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				POPUP_ZweigeGefundeneHandelsdefs_zur_auswahl.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				_HD_ActionAgent_FindTaxDef__ABSTRACT.this.setze_handelsdefinition_mit_vorgesehener_aktion(this.vFuhrenEinstufung);
				
			}
			
		}
		
		
	}

	
}
