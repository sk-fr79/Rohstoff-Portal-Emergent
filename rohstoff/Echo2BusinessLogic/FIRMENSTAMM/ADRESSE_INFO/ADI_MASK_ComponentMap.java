 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ADI_MASK_ComponentMap extends RB_ComponentMap {
	
	private RECORD_ADRESSE rec_adresse = null;

    public ADI_MASK_ComponentMap(RECORD_ADRESSE  p_rec_adresse) throws myException {
        super();
		this.rec_adresse = p_rec_adresse;

        this.registerComponent(new RB_KF(ADRESSE_INFO.kuerzel),    			new RB_TextField(200));
        this.registerComponent(new RB_KF(ADRESSE_INFO.datumeintrag),    		new RB_TextField(100));

        this.registerComponent(new RB_KF(ADRESSE_INFO.datumereignis),    		new RB_date_selektor(100, true));
        this.registerComponent(new RB_KF(ADRESSE_INFO.folgedatum),    		new RB_date_selektor(100, true));
        
        this.registerComponent(new RB_KF(ADRESSE_INFO.wiederholungjaehrlich), new RB_CheckBox(ADRESSE_INFO.wiederholungjaehrlich));
        this.registerComponent(new RB_KF(ADRESSE_INFO.wiederholungmonatlich), new RB_CheckBox(ADRESSE_INFO.wiederholungmonatlich));

        this.registerComponent(new RB_KF(ADRESSE_INFO.id_aktionsanlass),		new ADI_MASK_SelectField_Anlass(ADRESSE_INFO.id_aktionsanlass));
        
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_user),   			new ADI_MASK_SelectField_Betreuer(ADRESSE_INFO.id_user));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_user_ersatz),    	new ADI_MASK_SelectField_Betreuer(ADRESSE_INFO.id_user_ersatz));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_user_sachbearbeiter),new ADI_MASK_SelectField_Betreuer(ADRESSE_INFO.id_user_sachbearbeiter));
        
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis1),   new ADI_MASK_SelectField_BesucherErgebnis(ADRESSE_INFO.id_besuchsergebnis1));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis2),   new ADI_MASK_SelectField_BesucherErgebnis(ADRESSE_INFO.id_besuchsergebnis2));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis3),   new ADI_MASK_SelectField_BesucherErgebnis(ADRESSE_INFO.id_besuchsergebnis3));

        this.registerComponent(new RB_KF(ADRESSE_INFO.text),    				new RB_TextArea(600,10));

//        this.rb_register(new RB_KF(ADRESSE_INFO.id_adresse_info),    new RB_TextField());
//        this.rb_register(new RB_KF(ADRESSE_INFO.ist_message),    new RB_CheckBox(ADRESSE_INFO.ist_message));
//        this.rb_register(new RB_KF(ADRESSE_INFO.message_sofort),    new RB_CheckBox(ADRESSE_INFO.message_sofort));
//        this.rb_register(new RB_KF(ADRESSE_INFO.message_typ),    new RB_TextField(400));
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
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {
       	//hier muss die adress-id zwingend in den statementbuilder rein
    	preSettingsContainer.rb_get(ADRESSE_INFO.kuerzel.fk()).set_Enabled(false);
    	preSettingsContainer.rb_get(ADRESSE_INFO.datumeintrag.fk()).set_Enabled(false);
    	
    	if (status.isStatusNew()) {
    		preSettingsContainer.rb_set_forcedValueAtSave(ADRESSE_INFO.id_adresse, 	this.rec_adresse.ufs(ADRESSE_INFO.id_adresse));
    		preSettingsContainer.rb_set_defaultMaskValue(ADRESSE_INFO.kuerzel, 		bibALL.get_KUERZEL());
    		preSettingsContainer.rb_set_defaultMaskValue(ADRESSE_INFO.datumeintrag, bibALL.get_cDateNOW());	
    	}
    	
    	preSettingsContainer.rb_get(ADRESSE_INFO.text).set_MustField(true);

    	preSettingsContainer.rb_set_forcedValueAtSave(ADRESSE_INFO.message_typ, "ALLGEMEIN");
    	
		return null;
	}
}
 
