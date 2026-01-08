/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceNormalizeStringBean;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class FSL_Break4PopupControllerAtMaskSave extends E2_Break4PopupController {

	private boolean   allowSaveWithoutRegister = 	false;    //zustandsvariable, wird bei jedem controlleraufruf neu gesetzt
	
	public FSL_Break4PopupControllerAtMaskSave(E2_BasicModuleContainer_MASK omaskContainer) {
		super();
		this._registerBreak(new ownBreak4Popup(omaskContainer));
	}
	@Override
	public E2_Break4PopupController _initAtExecution(ActionEvent p_eventWhichWasBroken) throws myException {
		this.allowSaveWithoutRegister = 	false;
		return super._initAtExecution(p_eventWhichWasBroken);
	}

	
	
	private class ownBreak4Popup extends E2_Break4Popup {

		private E2_BasicModuleContainer_MASK  	maskContainer=				null;
		
		public ownBreak4Popup(E2_BasicModuleContainer_MASK  p_maskContainer) {
			super();
			this.maskContainer = p_maskContainer;
			this.setPopupWidth(400);
			this.setPopupHeight(300);
			
			this.getOwnSaveButton()._aaa(()->{allowSaveWithoutRegister=true;});
			
			this.setTitle(S.ms("Hinweis: "));
			
		}

		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			return new ownBasicModulContainer();
		}

		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			
			String id_adresse_basis = ((FSL_ModulContainer_MASK)this.maskContainer).get_BASE_ADRESS_ID();   
			E2_ComponentMAP mapAdresse = 		this.maskContainer.get_vCombinedComponentMAPs().get(0);
			E2_ComponentMAP mapLieferAdresse = 	this.maskContainer.get_vCombinedComponentMAPs().get(1);
			
			Rec21 recAdr = new Rec21(_TAB.adresse)._fill_id(bibALL.get_RECORD_MANDANT().ufs(MANDANT.eigene_adress_id));
			
			String ortMandant = new PdServiceNormalizeStringBean().normalForm(recAdr.getUfs(ADRESSE.ort));
			String ortAdresse = new PdServiceNormalizeStringBean().normalForm( mapAdresse.get_cActualDBValueFormated(ADRESSE.ort.fn()));
			
			boolean b_register = mapLieferAdresse.get_bActualDBValue(LIEFERADRESSE.registerfuehrung.fn());
			
			if (id_adresse_basis.equals(bibALL.get_ID_ADRESS_MANDANT())) {
				if (!b_register && (ortAdresse.startsWith(ortMandant) || ortMandant.startsWith(ortAdresse))) {
					return true && (!allowSaveWithoutRegister);
				}
			}
			return false;
		}
		
		
		private class ownBasicModulContainer extends E2_BasicModuleContainer {

			public ownBasicModulContainer() {
				super();
				
				allowSaveWithoutRegister = 	false;
				E2_Grid  g = new E2_Grid()
									._setSize(200,200)._a(new RB_lab()._tr("Bitte prüfen ...")._b(), new RB_gld()._span(2)._ins(2, 5, 2, 5))
									._a(new RB_lab()._tr("Die Adresse ist offenbar am gleichen Ort"), new RB_gld()._span(2)._ins(2, 5, 2, 2))
									._a(new RB_lab()._tr("wie der Mandant ansässig !"), new RB_gld()._span(2)._ins(2, 2, 2, 10))
									._a(new RB_lab()._tr("Daher muss evtl. die Registerführung ausgewählt werden !"), new RB_gld()._span(2)._ins(2, 10, 2, 10))
									._a(ownBreak4Popup.this.getOwnSaveButton()._standard_text_button()._t("Trotzdem Speichern"), new RB_gld()._span(1)._ins(2, 10, 2, 10))
									._a(ownBreak4Popup.this.getOwnCloseButton()._standard_text_button()._t("Abbruch, zurück zur Maske"), new RB_gld()._span(1)._ins(2, 10, 2, 10))
									;
				
				this.add(g, E2_INSETS.I(2));
			}
			
		}
		
	}



}
