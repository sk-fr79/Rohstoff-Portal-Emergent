package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.BasicInterfaces.Service.PdServiceReadCurrencysForAdress;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4Confirm;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF extends BS_SELECTFIELD_WAEHRUNG_FREMD {

	public BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF(SQLFieldMAP oFM, boolean bSmall) throws myException 	{
		super(oFM,bSmall);
		
		this.setBreak4PopupController(new E2_Break4PopupController()
										._registerBreak(new Break4ConfirmWaehrung()));
	}
	
	private E2_ComponentMAP getMaskMap() {
		return this.EXT().get_oComponentMAP();
	}
	

	
	private Long getIdAdressActual() {
		
		Long ret = null;
		
		try {
			String idAdressActual = this.getMaskMap().get_cActualDBValueFormated("ID_ADRESSE");
			
			if (S.isFull(idAdressActual)) {
				MyLong lID = new MyLong(idAdressActual);
				
				if (lID.isOK()) {
					ret = lID.get_oLong();
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	
	private class Break4ConfirmWaehrung extends Break4Confirm {

		public Break4ConfirmWaehrung() {
			super();
			this._setWindowTitle(S.ms("Bestätigungsabfrage :"));
			this._setTitle(S.ms("Die Währung ist NICHT der aktuellen Adresse zugeordnet !"));
			this._setTextForYes(S.ms("Weiter: Währung OK"));
			this._setTextForNo(S.ms("Abbrechen"));
			
			this._setHeight(250);
			
			this.getOwnCloseButton()._aaa(()->{BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF.this.set_ActiveValue(BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF.this.getLastSetValue());});

		}
		
		
		
		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			boolean ret = false;

			//super.check4break prueft den popup-zaehler
			if (super.check4break(mv)) {     
				
				if (S.isEmpty(BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF.this.get_ActualWert())) {
					return false;          //einschalten leere währung ohne aktion
				}
				
				MyLong _idWaehrung = new MyLong(BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF.this.get_ActualWert());
				Long    idAdresse  = BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF.this.getIdAdressActual();
				
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
	//					DEBUG._print("W1: "+r.getLongDbValue(WAEHRUNG.id_waehrung)+" ->  W-Mask: "+_idWaehrung.get_oLong());
						
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
