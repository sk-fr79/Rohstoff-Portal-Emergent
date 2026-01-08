
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldLand;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_FULLDAUGHTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_LIST_BasicModuleContainer;



public class BOR_MASK_ComponentMap extends RB_ComponentMap {

	public BOR_MASK_ComponentMap() throws myException {
		super();

		this.registerComponent(new RB_KF(BORDERCROSSING.id_bordercrossing), new RB_Label(BORDERCROSSING.id_bordercrossing));
		this.registerComponent(new RB_KF(BORDERCROSSING.id_land_quelle), new RB_HighLevel_SelectFieldLand(BORDERCROSSING.id_land_quelle,true,new Extent(200)));
		this.registerComponent(new RB_KF(BORDERCROSSING.id_land_ziel), new RB_HighLevel_SelectFieldLand(BORDERCROSSING.id_land_ziel,true,new Extent(200)));
		this.registerComponent(new RB_KF(BORDERCROSSING.title), new RB_TextField_old(BORDERCROSSING.title, 400));
		this.registerComponent(new RB_KF(BORDERCROSSING.message), new RB_TextArea_old(BORDERCROSSING.message, 400, 5));
		this.registerComponent(new RB_KF(BORDERCROSSING.erinnerung_bei_anlage), new RB_CheckBox(BORDERCROSSING.message));
		this.registerComponent(new RB_KF(BORDERCROSSING.intervall_tage), new RB_TextField_old(BORDERCROSSING.intervall_tage, 100));
		this.registerComponent(new RB_KF(BORDERCROSSING.offset_before_start), new RB_TextField_old(BORDERCROSSING.offset_before_start, 100));
		
		// Benutzerzuordnung
		this.registerComponent(BOR_CONST.MASK_KEYS.USER_CROSSTABLE.key(), new BOR_MASK_UserLeftRight());
		
		// Artikel-Zuordnung
		this.registerComponent(BOR_CONST.MASK_KEYS.BORDERCROSSING_ARTIKEL.key(),new BOR_ART_FULLDAUGHTER(this.get_oModulContainerMASK_This_BelongsTo(),this));
		
		
		//validierer mindestens ein land
		this.getMaskSetAndValidContainer().registerComponent(new RB_KF(BORDERCROSSING.id_bordercrossing), new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
				
				MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
				
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					//jetzt sicherstellen, dass immer mindestens ein land vorhanden ist
					IF_RB_Component_Savable  comp_land_start = (IF_RB_Component_Savable)RB__TOOLS.find_comp(rbMASK, new RB_KF(BORDERCROSSING.id_land_quelle));
					IF_RB_Component_Savable  comp_land_ziel = (IF_RB_Component_Savable)RB__TOOLS.find_comp(rbMASK, new RB_KF(BORDERCROSSING.id_land_ziel));
					
					if (S.isEmpty(comp_land_start.rb_readValue_4_dataobject()) && S.isEmpty(comp_land_ziel.rb_readValue_4_dataobject())) {
						mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens ein Land angeben !")));
					}
				}
				
				return mv_rueck;
			}
		});
		

		//validierer mindestens ein benutzer
		this.getMaskSetAndValidContainer().registerComponent(new RB_KF(BORDERCROSSING.id_bordercrossing), new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
				
				MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
				
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					BOR_MASK_UserLeftRight ulr = (BOR_MASK_UserLeftRight)rbMASK.getComp(BOR_CONST.MASK_KEYS.USER_CROSSTABLE.key());
					
					if (ulr.get_v_cb_right().size()==0) {
						mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens Benutzer zuteilen !")));
					}
				}
				return mv_rueck;
			}
		});

	
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
		if (status==MASK_STATUS.NEW) {
			preSettingsContainer.rb_set_defaultMaskValue(BORDERCROSSING.intervall_tage,"1");
			preSettingsContainer.rb_set_defaultMaskValue(BORDERCROSSING.erinnerung_bei_anlage,"Y");
			preSettingsContainer.rb_set_defaultMaskValue(BORDERCROSSING.offset_before_start,"0");
		}
		return null;
	}
	
	
	
}
