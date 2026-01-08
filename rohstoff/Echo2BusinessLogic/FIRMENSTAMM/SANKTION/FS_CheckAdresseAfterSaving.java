package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceHandleSanktionsChecks;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskSaveController_IF.ENUM_SAVEMASKCONTROLLERS_POS;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBox;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.SANKTION.SANKTION_Ergebnisse;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class FS_CheckAdresseAfterSaving {

	public FS_CheckAdresseAfterSaving() {
		super();
	}

	
	public FS_CheckAdresseAfterSaving _init(E2_SaveMASK saver, ENUM_SAVEMASKCONTROLLERS_POS pos, MyE2_MessageVector mv	) throws myException {
		//holt sich die zuletzt gespeicherte id
		Long lastAdressId = saver.getLastSavedId();
		
		if (pos == ENUM_SAVEMASKCONTROLLERS_POS.AFTER_SAVE) {
		
			if (lastAdressId!=null) {
				
				if (ENUM_MANDANT_DECISION.SANKTIONS_CHECK_ADRESS_AT_SAVE.is_YES_FromSession()) {
					@SuppressWarnings("unchecked")
					VEK<Long>  vAllAdressIdsFromMandant = new VEK<Long>()._a(bibSES.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null))
															._a((VEK<Long>) ENUM_MANDANT_SESSION_STORE.VECTOR_ALL_DELIVERYADDRESSES_FROM_MANDANT.getValueFromSession())
															._a((VEK<Long>) ENUM_MANDANT_SESSION_STORE.VECTOR_ALL_EMPLOYES_FROM_MANDANT.getValueFromSession());
					
//					DEBUG._print(vAllAdressIdsFromMandant);
//					DEBUG._print("-------------------------------------");
//					DEBUG._print(lastAdressId.toString()+" : "+vAllAdressIdsFromMandant.indexOf(lastAdressId));
					
					if ( 	vAllAdressIdsFromMandant.contains(lastAdressId) && ENUM_MANDANT_DECISION.SANKTIONS_CHECK_ADRESS_EXCLUDE_ADRESS_MANDANT.is_YES_FromSession()) {
						
						return this;    // dann passiert nichts
						
					} else {
						PdServiceHandleSanktionsChecks  sanktionService = new PdServiceHandleSanktionsChecks()._send_meldung(false).initWithAdresses(new VEK<String>()._a(lastAdressId.toString()));
						
//						HashMap<String, SANKTION_Ergebnisse> testResult = sanktionService.get_check_result();

						
						if (sanktionService.get_check_result().size()>0) {   //2018-09-28: abfagen der moeglichkeit, dass kein ergebniss zurueckkommt (wegen Datenbankfehler z.B.) 
							SANKTION_Ergebnisse ergebnissDerPruefung = sanktionService.get_check_result().get(lastAdressId.toString());
							
							if (ergebnissDerPruefung!=null && ergebnissDerPruefung.containsSanktion() && (!sanktionService.has_freigabe(lastAdressId.toString()))) {
							  new SanktionsInfoPopupAfterSaveAdress(lastAdressId, sanktionService);
							}
						}
					}
				}
			}
		}
		
		return this;
	}
	
	
	
	
	private class SanktionsInfoPopupAfterSaveAdress extends E2_MessageBox {

		
		private Rec21_adresse m_recAdresse = null;
		private PdServiceHandleSanktionsChecks m_sanktionService;

		/**
		 * @param idAdresse
		 * @throws myException 
		 */
		public SanktionsInfoPopupAfterSaveAdress(Long idAdresse, PdServiceHandleSanktionsChecks  sanktionService) throws myException {
			super();
			this.m_sanktionService = sanktionService;
			if (idAdresse !=null) {
				this.m_recAdresse =  new Rec21_adresse()._fill_id(idAdresse);
				this.m_recAdresse = this.m_recAdresse._getMainAdresse();
				this._setTitleOfPopup(S.ms("Achtung ! Potentiell zu sanktionierende Adresse !"))._show(600, 400);
			}
		}

		
		
		
		@Override
		public void arrangeContent(E2_Grid g) throws myException{
			g._clear()._setSize(new Extent(100, Extent.PERCENT));
			
			RB_gld lo = new RB_gld()._left_mid()._ins(2);

			if (this.m_recAdresse!=null) {
				g._setSize(80,100,500);
				g._a(new RB_lab()._icon(E2_ResourceIcon.get_RI("warnschild-80.png")),lo._c()._span_r(2));
				g._a(new RB_lab()._tr("Treffer in der Sanktionsdatenbank! Adresse muss überprüft werden !")._b(), lo._c()._span(2));
				
				g._a(new RB_lab()._tr("Adresse: "), lo);
				g._a(new RB_lab()._t(this.m_recAdresse.get__FullNameAndAdress_flexibleWithId(" ")), lo._c()._span(2));
				
				g._a(this.m_sanktionService.render_ergebnis(), new RB_gld()._ins(2, 10, 2, 10)._span(3));
				
				g._a(new E2_Grid()._setSize(180,400)._a(this.getBtYes()._sizeWH(170,35)._insets(2, 10, 2, 2)._al_center()._tr("OK bestätigen !")._b()._standard_text_button(), lo)
													._a(new BtSendMessage()._sizeWH(170,35)._insets(2, 10, 2, 2)._al_center()._standard_text_button(),lo),new RB_gld()._left_bottom()._ins(2, 10, 2, 2)._span(3));
			}		
		
		}
		
		
		private class BtSendMessage extends E2_Button {

			public BtSendMessage() {
				super();
				
				this._image("meldung20.png")._t("Meldung verschicken");
				
				this._aaa(()->{ENUM_MESSAGE_PROVIDER.MESSAGE_ADRESSE_FREIGABE.generateMessages(		MODUL.NAME_MODUL_FIRMENSTAMM_LIST
																								,	m_recAdresse.get_key_value()
																								, 	S.ms("Adresse ").ut(m_recAdresse.get__FullNameAndAdress_flexibleWithId(" "))
																													.t(" muss bezüglich Sanktionen geprüft werden !").CTrans()
																								,	"Adressen-Stamm");});
				
				
			}
			
		}
	}
}
