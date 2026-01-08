package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_ComponentMAP extends RB_ComponentMap {

	
	public ADK_M_ComponentMAP() throws myException {
		super();
		
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.id_adressklasse_def), new RB_Label(new RB_SQLField(ADRESSKLASSE_DEF.id_adressklasse_def)));
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.kurzbezeichnung), new RB_TextField_old(new RB_SQLField(ADRESSKLASSE_DEF.kurzbezeichnung),400));
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.bezeichnung), new RB_TextField_old(new RB_SQLField(ADRESSKLASSE_DEF.bezeichnung),400));
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.beschreibung), new RB_TextArea_old(new RB_SQLField(ADRESSKLASSE_DEF.beschreibung),400,10));
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.ist_kunde), new RB_CheckBox(new RB_SQLField(ADRESSKLASSE_DEF.ist_kunde)).set_W(20));
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.ist_lieferant), new RB_CheckBox(new RB_SQLField(ADRESSKLASSE_DEF.ist_lieferant)).set_W(20));
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.ist_standard), new RB_CheckBox(new RB_SQLField(ADRESSKLASSE_DEF.ist_standard)).set_W(20));
		
		RB_TextField_old tfr =  new RB_TextField_old(new RB_SQLField(ADRESSKLASSE_DEF.color_red),50,3);
		RB_TextField_old tfg =  new RB_TextField_old(new RB_SQLField(ADRESSKLASSE_DEF.color_green),50,3);
		RB_TextField_old tfb =  new RB_TextField_old(new RB_SQLField(ADRESSKLASSE_DEF.color_blue),50,3);
		RB_TextField_old tfSort =  new RB_TextField_old(new RB_SQLField(ADRESSKLASSE_DEF.colorsort),50,3);
		tfr.rb_VALIDATORS_4_INPUT().add(new compValidatorColorValue());
		tfg.rb_VALIDATORS_4_INPUT().add(new compValidatorColorValue());
		tfb.rb_VALIDATORS_4_INPUT().add(new compValidatorColorValue());
		
		
		
		tfr.setToolTipText(new MyE2_String("Farbwert, ROT-Anteil (0-255 erlaubt)").CTrans());
		tfg.setToolTipText(new MyE2_String("Farbwert, GRÜN-Anteil (0-255 erlaubt)").CTrans());
		tfb.setToolTipText(new MyE2_String("Farbwert, BLAU-Anteil (0-255 erlaubt)").CTrans());
		tfSort.setToolTipText(new MyE2_String("Bestimmt die Sortierung im Anzeigebalken der Firmenstamm-Liste").CTrans());
		
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.color_red), tfr);
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.color_green), tfg);
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.color_blue), tfb);
		this.registerComponent(new RB_KF(ADRESSKLASSE_DEF.colorsort), tfSort);
		
		this.registerComponent(new RB_KF(ADK_CONST.COMP_NAMES.COLORSELEKTOR.name()), new ADK_M_compColorSelector(tfr, tfg, tfb, tfSort));
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
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer SurfaceSettingsContainer,	MASK_STATUS status) throws myException {
		SurfaceSettingsContainer.rb_get(new RB_KF(ADRESSKLASSE_DEF.color_red)).set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.CENTER);
		SurfaceSettingsContainer.rb_get(new RB_KF(ADRESSKLASSE_DEF.color_green)).set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.CENTER);
		SurfaceSettingsContainer.rb_get(new RB_KF(ADRESSKLASSE_DEF.color_blue)).set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.CENTER);
		SurfaceSettingsContainer.rb_get(new RB_KF(ADRESSKLASSE_DEF.colorsort)).set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.CENTER);
		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

	
	
	private class compValidatorColorValue extends RB_Validator_Component {
		@Override
		public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component)	throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_TextField_old tf = (RB_TextField_old)rb_Component;
			String text4user = "???";
			if (tf.rb_KF().equals(new RB_KF(ADRESSKLASSE_DEF.color_red))) {
			  	text4user = "ROT-Anteil";
			} else if (tf.rb_KF().equals(new RB_KF(ADRESSKLASSE_DEF.color_green))) {
			  	text4user = "GRÜN-Anteil";
			} else if (tf.rb_KF().equals(new RB_KF(ADRESSKLASSE_DEF.color_blue))) {
				text4user = "BLAU-Anteil";
			}
			String value = tf.getText();
			
			if (S.isFull(value)) {
				MyInteger i_test = new MyInteger(value);
				if (i_test.get_bOK()) {
					if (i_test.get_iValue()<0 || i_test.get_iValue()>255) {
						mv.add_MESSAGE(new MyE2_Alarm_Message("Der Wert im Feld "+text4user+" muss eine Zahl von 0 bis 255 sein !"));
					}
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message("Der Wert im Feld "+text4user+" muss eine Zahl von 0 bis 255 sein !"));
				}
			}
			return mv;
		}
		
	}
	
	
}
