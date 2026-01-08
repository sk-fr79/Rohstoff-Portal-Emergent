 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_MASK_ComponentMap extends RB_ComponentMap {
    public CONTYP_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(new RB_KF(CONTAINERTYP.id_containertyp), new RB_TextField()._width(50));
        this.registerComponent(new RB_KF(CONTAINERTYP.kuerzel), 		new RB_TextField(400,20));
        this.registerComponent(new RB_KF(CONTAINERTYP.kurzbezeichnung), new RB_TextField(400,50));
        this.registerComponent(new RB_KF(CONTAINERTYP.beschreibung),    new RB_TextArea(400,2,400) );
        this.registerComponent(new RB_KF(CONTAINERTYP.containerinhalt), new RB_TextField()._width(50));
        this.registerComponent(new RB_KF(CONTAINERTYP.ablauf),    		new RB_CheckBox(CONTAINERTYP.ablauf,S.ms("Ablauf"),S.ms("Container mit Ablauf")));
        this.registerComponent(new RB_KF(CONTAINERTYP.abroll),    		new RB_CheckBox(CONTAINERTYP.abroll,S.ms("Abroll"),S.ms("Abrollcontainer")));
        this.registerComponent(new RB_KF(CONTAINERTYP.absetz),    		new RB_CheckBox(CONTAINERTYP.absetz,S.ms("Absetz"),S.ms("Absetzcontainer")));
        this.registerComponent(new RB_KF(CONTAINERTYP.deckel),    		new RB_CheckBox(CONTAINERTYP.deckel,S.ms("Deckel"),S.ms("Container mit Deckel")));
        this.registerComponent(new RB_KF(CONTAINERTYP.dicht),    		new RB_CheckBox(CONTAINERTYP.dicht,S.ms("Dicht"),S.ms("Container ist Dicht")));
        this.registerComponent(new RB_KF(CONTAINERTYP.plane),    		new RB_CheckBox(CONTAINERTYP.plane,S.ms("Plane"),S.ms("Container mit Plane")));
        this.registerComponent(new RB_KF(CONTAINERTYP.symmetrisch),    	new RB_CheckBox(CONTAINERTYP.symmetrisch,S.ms("Symmetrisch"),S.ms("Symmetrisch")));
        this.registerComponent(new RB_KF(CONTAINERTYP.aktiv),    		new RB_CheckBox(CONTAINERTYP.aktiv,S.ms("Aktiv"),S.ms("Aktiv")));
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
}
 
