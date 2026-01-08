
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.PROFIL_GRENZUBERTRITT;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BGL_PROFIL_MASK_ComponentMap extends RB_ComponentMap {
	public BGL_PROFIL_MASK_ComponentMap() throws myException {
		super();

		SEL zahlungsbedingung_query = new SEL(ZAHLUNGSBEDINGUNGEN.bezeichnung).ADDFIELD(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)
				.FROM(_TAB.zahlungsbedingungen).WHERE(new vgl(ZAHLUNGSBEDINGUNGEN.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP("1");	

		SEL  rech_gut_tax_query = new SEL(TAX.dropdown_text).ADDFIELD(TAX.id_tax).FROM(_TAB.tax)
				.WHERE(new vgl_YN(TAX.aktiv, true)).AND(new vgl(TAX.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP("1");	

		RB_SelectField zahlBed_selField = new RB_SelectField(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, zahlungsbedingung_query.s(), false, false ,new Extent(402) );

		RB_SelectField rechTax_selField = new RB_SelectField(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, rech_gut_tax_query.s(), false, false ,new Extent(402) );

		RB_SelectField gutTax_selField = new RB_SelectField(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, rech_gut_tax_query.s(), false, false ,new Extent(402) );

		this.registerComponent(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt,  new RB_TextField()._width(100));
		this.registerComponent(PROFIL_GRENZUBERTRITT.name_profil,    			new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.std,    					new RB_CheckBox(PROFIL_GRENZUBERTRITT.std));
		this.registerComponent(PROFIL_GRENZUBERTRITT.id_zahlungsbedingung,    	zahlBed_selField);
		this.registerComponent(PROFIL_GRENZUBERTRITT.id_tax_gutschrift,    		rechTax_selField);
		this.registerComponent(PROFIL_GRENZUBERTRITT.id_tax_rechnung,    		gutTax_selField);
		this.registerComponent(PROFIL_GRENZUBERTRITT.name_ansprechpartner,    	new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.tel_ansprechpartner,    	new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.fax_ansprechpartner,    	new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.name_bearbeiter,    		new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.tel_bearbeiter,    		new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.fax_bearbeiter,    		new RB_TextField()._width(400));
		this.registerComponent(PROFIL_GRENZUBERTRITT.auslandsvertretung_text,   new RB_TextArea()._width(395)._rows(3));
		this.registerComponent(PROFIL_GRENZUBERTRITT.formulartext_anfang,    	new RB_TextArea()._width(395)._rows(3));
		this.registerComponent(PROFIL_GRENZUBERTRITT.formulartext_ende,    		new RB_TextArea()._width(395)._rows(3));

		this.registerSetterValidators(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt.fk(), new own_mask_validator());
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
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.name_profil).set_MustField(true);
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.id_zahlungsbedingung).set_MustField(true);
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.id_tax_gutschrift).set_MustField(true);
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.id_tax_rechnung).set_MustField(true);
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.id_zahlungsbedingung).set_Enabled(true);
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.id_tax_gutschrift).set_Enabled(true);
		preSettingsContainer.rb_get(PROFIL_GRENZUBERTRITT.id_tax_rechnung).set_Enabled(true);
		return null;
	}

	private class own_mask_validator extends RB_Mask_Set_And_Valid{

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {

			MyE2_MessageVector mv = new MyE2_MessageVector();

			if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
				MASK_STATUS st = rbMASK.rb_get_belongs_to().rb_Actual_DataobjectCollector().get(rbMASK.getOwnMaskKey()).rb_MASK_STATUS();
			
				if(st == MASK_STATUS.NEW) {
					String namen_abfrage = new SEL(PROFIL_GRENZUBERTRITT.name_profil).FROM(_TAB.profil_grenzubertritt)
							.WHERE(new vgl(PROFIL_GRENZUBERTRITT.id_mandant, bibALL.get_ID_MANDANT()))
							.s();

					String[][] db_list_name = bibDB.EinzelAbfrageInArray(namen_abfrage);

					String name_2_test = rbMASK._find_component_in_neighborhood(PROFIL_GRENZUBERTRITT.name_profil).get__actual_maskstring_in_view();
					VEK<String> name_list = new VEK<String>();

					for(String[] r : db_list_name) {
						name_list._a(r[0]);
					}

					if(name_list.contains(name_2_test)) {
						return mv._addAlarm("Der Profil "+ name_2_test+ " existiert schon. Bitte wählen sie ein andere Name.");
					}
				}
			}
			return mv;
		}

	}
}

