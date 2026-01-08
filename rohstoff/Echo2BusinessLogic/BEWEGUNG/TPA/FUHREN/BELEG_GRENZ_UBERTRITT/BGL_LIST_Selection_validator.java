package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BGL_LIST_Selection_validator extends XX_ActionValidator_NG {

	private E2_NavigationList navi_list;
	private VEK<String> vIds_valid = new VEK<String>();
	
	public BGL_LIST_Selection_validator(E2_NavigationList oNaviList) {
		super();
		this.navi_list = oNaviList; 
	}
	
	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		VEK<String> selected_fuhre_id = new VEK<String>()._a(this.navi_list.get_vSelectedIDs_Unformated());
		this.vIds_valid.clear();
		
		Long id_mandant_land = bibALL.get_RECORD_MANDANT().get_UP_RECORD_LAND_id_land().get_longValue(LAND.id_land.fieldName());
		
		if(selected_fuhre_id.size()>0) {
			if(selected_fuhre_id.size()>1) {
				mv._addAlarm("Bitte nur 1 Fuhre auswählen !");
			}else {
				for(String fuhre_id : selected_fuhre_id) {
					Rec20 rec_fuhre = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(fuhre_id);
					if(	rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_start).equals( bibALL.get_ID_ADRESS_MANDANT() ) &&
							rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_ziel).equals(bibALL.get_ID_ADRESS_MANDANT() )	) {

						long id_land_lager_start = 	rec_fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_start, ADRESSE.id_adresse, false).get_myLong_dbVal(ADRESSE.id_land).get_lValue();
						long id_lang_lager_ziel = 	rec_fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_ziel, ADRESSE.id_adresse, false).get_myLong_dbVal(ADRESSE.id_land).get_lValue();

						boolean is_intrastat = false;

						if(id_land_lager_start == id_mandant_land && id_lang_lager_ziel == id_mandant_land) {
							mv._addAlarm("Fuhre ID "+ rec_fuhre.get_key_value()+" nicht relevant : Start und Ziel Lager befinden sich in "+bibALL.get_RECORD_MANDANT().get_UP_RECORD_LAND_id_land().get_BESCHREIBUNG_cUF());

						} else if(id_land_lager_start != id_mandant_land && id_lang_lager_ziel == id_mandant_land) {

							is_intrastat = new Rec20(_TAB.land)._fill_id(id_land_lager_start).get_ufs_dbVal(LAND.intrastat_jn,"N").equals("Y")?true:false;

							if(is_intrastat) {
								this.vIds_valid._a(selected_fuhre_id);
							}else {
								mv._addAlarm("Fuhre ID "+ rec_fuhre.get_key_value()+" nicht relevant: Ausgangsland ist nicht in der EU");
							}

						} else if(id_land_lager_start == id_mandant_land && id_lang_lager_ziel != id_mandant_land) {

							is_intrastat = new Rec20(_TAB.land)._fill_id(id_lang_lager_ziel).get_ufs_dbVal(LAND.intrastat_jn,"N").equals("Y")?true:false;

							if(is_intrastat) {
								this.vIds_valid._a(selected_fuhre_id);
							}else {
								mv._addAlarm("Fuhre ID "+ rec_fuhre.get_key_value()+" nicht relevant: Zileland ist nicht in der EU");
							}

						} else {
							mv._addAlarm("Fuhre ID "+ rec_fuhre.get_key_value()+" nicht relevant: Die Fuhre hat keinen Grenzübertritt");
						}

					} else {
						mv._addAlarm("Fuhre ID "+ rec_fuhre.get_key_value()+" ist keine Lager-Lager Fuhre");
					}
				}
			}
		}else {
			mv._addAlarm("Bitte eine Fuhre auswählen !");
		}

		return mv;
	}

}
