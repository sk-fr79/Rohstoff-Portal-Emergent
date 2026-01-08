package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.BasicInterfaces.Service.PdServiceReadCurrencysForAdress;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4Confirm;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class KFIX__SelectField_Waehrung extends RB_selField {
	
	public KFIX__SelectField_Waehrung(IF_Field field, int iWidth) throws myException{
		super();
		
		this._populate(new RecList21(_TAB.waehrung)._fillWithAll(new VEK<IF_Field>()._a(WAEHRUNG.kurzbezeichnung)), WAEHRUNG.kurzbezeichnung, WAEHRUNG.id_waehrung, true)
			._setMemoryEffectActive()._width(120);
		
		this.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4ConfirmWaehrung()));
		
		
	}
	
	
	private Long findIdAdress() throws myException {
		return new RB_MaskController(this).getLongLiveVal(VKOPF_KON.id_adresse);
	}
	
	
	private class Break4ConfirmWaehrung extends Break4Confirm {

		public Break4ConfirmWaehrung() {
			super();
			this._setWindowTitle(S.ms("Bestätigungsabfrage :"));
			this._setTitle(S.ms("Die Währung ist NICHT der aktuellen Adresse zugeordnet !"));
			this._setTextForYes(S.ms("Weiter: Währung OK"));
			this._setTextForNo(S.ms("Abbrechen"));
			
			this._setHeight(250);
			
			this.getOwnCloseButton()._aaa(()->{KFIX__SelectField_Waehrung.this._setActiveDBVal(KFIX__SelectField_Waehrung.this.getLastSetValue());});
		}
		
		
		
		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			boolean ret = false;

			//super.check4break prueft den popup-zaehler
			if (super.check4break(mv)) {     
				
				if (S.isEmpty(KFIX__SelectField_Waehrung.this.rb_readValue_4_dataobject())) {
					return false;          //einschalten leere währung ohne aktion
				}
				
				MyLong _idWaehrung = new MyLong(KFIX__SelectField_Waehrung.this.rb_readValue_4_dataobject());
				Long    idAdresse  = findIdAdress();
				
				this._clearInfoBlock();
				this._setWindowTitle(S.ms("Bestätigungsabfrage :"));
				this._setTitle(S.ms("Die Währung ist NICHT der aktuellen Adresse zugeordnet !"));
				this._setTextForYes(S.ms("Weiter: Währung OK"));
				this._setTextForNo(S.ms("Abbrechen"));
				this.getOwnSaveButton().set_bEnabled_For_Edit(true);
	
				if (_idWaehrung.isOK() && idAdresse !=null) {
					//jetzt feststellen , ob die Adresse und die waehrung passen
					VEK<Rec21> vWaehrungenAdresse = new PdServiceReadCurrencysForAdress().getVEKWaehrungen(idAdresse);
					
					boolean passt = false;
					for (Rec21 r: vWaehrungenAdresse) {
						if (r.getLongDbValue(WAEHRUNG.id_waehrung).longValue()==_idWaehrung.get_oLong().longValue()) {
							passt=true;
							break;
						}
					}
					if (!passt) {
						Rec21 recWaehrung = new Rec21(_TAB.waehrung)._fill_id(_idWaehrung.get_lValue());
						
						this._setTitle(S.ms("Die Währung <").ut(recWaehrung.getUfs(WAEHRUNG.kurzbezeichnung)).t("> ist NICHT der aktuellen Adresse zugeordnet !"));
						this._setTextForYes(S.ms("<").ut(recWaehrung.getUfs(WAEHRUNG.kurzbezeichnung)).t("> übernehmen"));

						this._addInfoTextLine(S.ms("Erlaubte Währungen: "));
						String infoText = ""; 
						for (Rec21 w: vWaehrungenAdresse ) {
							infoText += (w.getUfs(WAEHRUNG.kurzbezeichnung) +" / ");
						}
						if (infoText.endsWith(" / ")) {
							infoText = infoText.substring(0,infoText.lastIndexOf(" / "));
						}
						this._addInfoTextLine(S.msUt(infoText));
					}
					
					ret = !passt;
					
					
				} else {
					if (idAdresse==null) {
						this._setWindowTitle(S.ms("Fehler :"));
						this._setTitle(S.ms("Bitte zuerst die Adresse definieren "));
						this.getOwnSaveButton().set_bEnabled_For_Edit(false);
					} else {
						this._setWindowTitle(S.ms("Fehler :"));
						this._setTitle(S.ms("Die Währung konnte nicht ermittelt werden !"));
						this.getOwnSaveButton().set_bEnabled_For_Edit(false);
					}
					ret = true;    //popup zwingen
				}
			}
			return ret;
		}

		
		
		private class OwnContainer extends E2_BasicModuleContainer {
		}



		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			return this.fillContainer(new OwnContainer());
		}

		

		
	}
}
