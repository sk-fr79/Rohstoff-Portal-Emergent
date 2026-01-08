 
package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_MASK_ComponentMap extends RB_ComponentMap {
    public ADR_FREIGABE_MASK_ComponentMap() throws myException {
        super();
        
        RB_HighLevel_SelectFieldUser user_comp = new RB_HighLevel_SelectFieldUser(SANKTION_PRUEFUNG.freigabe_user, true, true,new Extent(150), ENUM_USER_TYP.AKTIV);
		
        this.registerComponent(SANKTION_PRUEFUNG.id_adresse,    								new ADR_FREIGABE_COMP_Label_adresse_detail());
        this.registerComponent(SANKTION_PRUEFUNG.freigabe,   		 							new ADR_FREIGABE_COMP_bt_freigabe());
        this.registerComponent(SANKTION_PRUEFUNG.freigabe_bemerkung,  							new RB_TextArea(400,4)._fo_s_plus(1));
        this.registerComponent(SANKTION_PRUEFUNG.freigabe_datum,    							new RB_TextField(110)._fsa(1)._bord_ddd());
        this.registerComponent(SANKTION_PRUEFUNG.freigabe_user,    								user_comp);
        this.registerComponent(SANKTION_PRUEFUNG.hashwert_adresse, 								new RB_lab()._fsa(1));
        this.registerComponent(SANKTION_PRUEFUNG.hashwert_sanktion, 							new RB_lab()._fsa(1));
        this.registerComponent(ADR_FREIGABE_CONST.MASK_KEY.SANKTION_DETAIL.fk(), 				new ADR_FREIGABE_MASK_Sanktion_detail());
        this.registerComponent(ADR_FREIGABE_CONST.MASK_KEY.SANKTION_ADRESSE_BEARBEITEN.fk(), 	new ADR_FREIGABE_COMP_bt_jumpAdressModul());
        this.registerComponent(SANKTION_PRUEFUNG.aktiv, new RB_cb());
        this.registerComponent(SANKTION_PRUEFUNG.geprueft_am,    								new RB_TextField(110)._fsa(1)._bord_ddd());

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
		preSettingsContainer.rb_get(SANKTION_PRUEFUNG.freigabe_bemerkung)	.set_MustField(true);
		preSettingsContainer.rb_get(SANKTION_PRUEFUNG.freigabe_user)		.set_Enabled(false);
		preSettingsContainer.rb_get(SANKTION_PRUEFUNG.freigabe_datum)		.set_Enabled(false);
		preSettingsContainer.rb_get(SANKTION_PRUEFUNG.geprueft_am)			.set_Enabled(false);
		preSettingsContainer.rb_get(SANKTION_PRUEFUNG.aktiv)				.set_Enabled(false);
		return null;
	}
}
 
