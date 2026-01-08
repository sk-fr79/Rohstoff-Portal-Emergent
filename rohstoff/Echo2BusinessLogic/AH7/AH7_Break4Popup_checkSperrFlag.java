/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_KEY;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_MASK_ComponentMapCollector;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_MASK_MaskModulContainer;

/**
 * @author martin AH7_Break4Popup_checkSperrFlag
 *
 */
public class AH7_Break4Popup_checkSperrFlag extends E2_Break4PopupController {

	private AH7_MASK_MaskModulContainer ah7_MASK_MaskModulContainer;

	public AH7_Break4Popup_checkSperrFlag(AH7_MASK_MaskModulContainer ah7_MASK_MaskModulContainer) {
		super();
		this.ah7_MASK_MaskModulContainer = ah7_MASK_MaskModulContainer;
		this._registerBreak(new Break4PopupSetzeSperrFlag());
	}

	private class Break4PopupSetzeSperrFlag extends E2_Break4Popup {

		public Break4PopupSetzeSperrFlag() {
			super();
			this._setLeftPos(300)._setTopPos(300)._setWidth(600)._setHeight(200);
			this.setTitle(S.ms("Sicherheitsabfrage :"));
		}

		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			return new ownPopup();
		}

		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			AH7_MASK_ComponentMapCollector 		ah7_maskComponentMapCollector = (AH7_MASK_ComponentMapCollector)ah7_MASK_MaskModulContainer.rb_FirstAndOnlyComponentMapCollector();
			RB_DataobjectsCollector_V2  		dataObjectsCollector = (RB_DataobjectsCollector_V2)ah7_maskComponentMapCollector.rb_Actual_DataobjectCollector();
			if (AH7_Break4Popup_checkSperrFlag.this.getHmCounter().get(this)!=null && AH7_Break4Popup_checkSperrFlag.this.getHmCounter().get(this).longValue()==0) {
				if (dataObjectsCollector.get(new AH7_KEY()).isStatusCanBeSaved()) {
					if (ah7_maskComponentMapCollector.getNumberOfChangedFields(false)>0) {
						RB_MaskController con = new RB_MaskController(ah7_maskComponentMapCollector);
						if (!con.getYNMaskVal(AH7_STEUERDATEI.locked) && S.NN(con.get_liveVal(AH7_STEUERDATEI.status_relation)).equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val())) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
		private class ownPopup extends E2_BasicModuleContainer {
			public ownPopup() {
				super();
				
				getOwnSaveButton()._tr("Trotzdem speichern")._standard_text_button();
				getOwnCloseButton()._tr("Abbruch - Zurück zur Maske")._standard_text_button();
				
				E2_Grid g = new E2_Grid()._setSize(280,280);
				g._a(new RB_lab()._tr("Sie haben die Maske verändert! Wollen Sie den Datensatz ohne Sperrschalter speichern ?"), new RB_gld()._span(2)._ins(4, 5, 5, 2));
				g._a(new RB_lab()._tr("(Damit schützen Sie den Satz bei Neubewertungs-Läufen)"), new RB_gld()._span(2)._ins(4, 2, 5, 20));
				g._a(getOwnSaveButton(),new RB_gld()._center_mid()._ins(5));
				g._a(getOwnCloseButton(),new RB_gld()._center_mid()._ins(5));
				
				this.add(g, E2_INSETS.I(5));
			}
		}
	}
}
