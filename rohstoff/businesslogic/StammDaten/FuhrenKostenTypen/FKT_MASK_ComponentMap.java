 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.IF_PopUpCascading;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_PopupWithEnumValues;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_KOSTEN_TYP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_MASK_ComponentMap extends RB_ComponentMap {
    public FKT_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(new RB_KF(FUHREN_KOSTEN_TYP.betrifft_zoll),    		new RB_cb());
        this.registerComponent(new RB_KF(FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ),   new RB_lab());
        this.registerComponent(new RB_KF(FUHREN_KOSTEN_TYP.kurztext_uebersicht),    new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(FUHREN_KOSTEN_TYP.neutral),    			new RB_cb());
        this.registerComponent(new RB_KF(FUHREN_KOSTEN_TYP.speditionsrechnung),    	new RB_cb());
        this.registerComponent(new RB_KF(FUHREN_KOSTEN_TYP.text4benutzer),    		new RB_TextField()._width(400));
        
        //suchbutton fuer die standard kostentypen
        this.registerComponent(new RB_KF(FKT_CONST.FKT_BUTTONS.POPUP_TYPEN.dbVal()),new ownPopupButton());
        
    }
    
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
        return null;
    }
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
        return null;
    }
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		return null;
	}
	
	private class ownPopupButton extends E2_Button {

		public ownPopupButton() {
			super();
			this.__setImages(E2_ResourceIcon.get_RI("suche.png"),E2_ResourceIcon.get_RI("suche__.png"));
			this._aaa(()-> {new ownPopupSelect().popupCascading(S.ms("Bitte wählen"), 400, 100);});
		}
		
		private class ownPopupSelect extends E2_PopupWithEnumValues implements IF_PopUpCascading {
			public ownPopupSelect() throws myException {
				super(EN_KOSTENTYP_VORSCHLAEGE.values(), 400);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public XX_ActionAgent generateAgent(IF_enumForDb val) throws myException {
				return new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownPopupButton oThis = ownPopupButton.this;
						
						RB_MaskController c = new RB_MaskController(oThis);
						c.set_maskVal(FUHREN_KOSTEN_TYP.kurztext_uebersicht, val.dbVal(), bibMSG.MV());
						c.set_maskVal(FUHREN_KOSTEN_TYP.text4benutzer, val.userText(), bibMSG.MV());
					}
				};
			}

			
		}
		
	}
	
}
 
