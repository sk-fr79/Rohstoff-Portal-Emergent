package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;
import java.util.Vector;

import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_DoubleText_4_PW;
import panter.gmbh.Echo2.RB.COMP.RB_DoubleText_4_PW_complexity;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class IP_MASK_ComponentMap extends RB_ComponentMap {
	
	private ownPasswordField passwordField = null;
	private MyE2_CheckBox switchPassword = null; 
	
    public IP_MASK_ComponentMap() throws myException {
        super();
        boolean passwordMasked = true;
        
        switchPassword = new  MyE2_CheckBox("Passwort maskieren");
        switchPassword.setSelected(passwordMasked);
        switchPassword.add_oActionAgent(new OwnActionSwithPasswordField());
        
        this.registerComponent(new RB_KF(REPORT.id_report),    		new RB_Label(REPORT.id_report));
        
        this.registerComponent(new RB_KF(REPORT.buttontext),    		new RB_TextField_old(REPORT.buttontext,400));
        this.registerComponent(new RB_KF(REPORT.titel),    			new RB_TextField_old(REPORT.titel,400));

        this.registerComponent(new RB_KF(REPORT.password),    		passwordField = new ownPasswordField(
        		  																				REPORT.password,
        		  																				passwordMasked));
        
        this.registerComponent(new RB_KF(REPORT.beschreibung),    	new RB_TextArea_old(REPORT.beschreibung,600,8));
        
        this.registerComponent(new RB_KF(REPORT.button_auth_kenner),  new RB_TextField_old(REPORT.button_auth_kenner,400));
        
        this.registerComponent(new RB_KF(REPORT.geschaeftsfuehrer),   new RB_CheckBox(REPORT.geschaeftsfuehrer, 
        														new MyE2_String("Beschränken auf Geschäftsführer"),
        														new MyE2_String("Wenn angehakt, dann muss der Benutzer Geschäftsführer sein um den Report starten zu können")
        		));
        
        this.registerComponent(new RB_KF(IP_CONST.IP_NAMES.CHECKBOX_MASKING_PASSWORD.db_val()), switchPassword);
        
        this.registerComponent(new RB_KF(REPORT.supervisor),    		new RB_CheckBox(REPORT.supervisor,
												        		new MyE2_String("Beschränken auf Administratoren"),
																new MyE2_String("Wenn angehakt, dann muss der Benutzer Administrator (Supervisor) sein um den Report starten zu können")
        		));
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
    public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer SurfaceSettingsContainer,MASK_STATUS status) throws myException {
        return new MyE2_MessageVector();
    }
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
    private class OwnActionSwithPasswordField extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(switchPassword.isSelected()){
				passwordField.setMaskedField(true);
			}else {
				passwordField.setMaskedField(false);
			}
			
		}
    	
    }
    
    
    
    private class ownPasswordField extends RB_DoubleText_4_PW {
    		
    	public ownPasswordField(  	IF_Field 		field, 
    								boolean 		b_isMasked) throws myException {
    			
    		super(field, 5, RB_DoubleText_4_PW_complexity.SecurityLevel.LOW, 200, b_isMasked);
    		this.set_input_field_length(150);
    	}

  
    	@Override
    	public void build_component_4_mask(TextField tf1, TextField tf2) 	throws myException {
    		this.removeAll();
    		this.setSize(4);
    		this.add(tf1, E2_INSETS.I(0,0,5,0));
    		this.add(new MyE2_Label(new MyE2_String(" ...bitte wiederholen: ")), E2_INSETS.I(10,0,0,0));
    		this.add(tf2, E2_INSETS.I(5,0,5,0));
    		this.add(IP_MASK_ComponentMap.this.switchPassword, E2_INSETS.I(10,0,0,0));
    	}

    }
    
    
}
 
