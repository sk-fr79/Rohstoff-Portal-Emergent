package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SONDERLAGER_ENUM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_TextField_resizable;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_besitzer_label;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;


public class LL_CM_Station extends RB_ComponentMap {
	
	private ENUM_STATION_TYP  stationstyp = null;
	private boolean      is_physical = true;
	
    public LL_CM_Station(ENUM_STATION_TYP p_stationstyp, boolean b_is_physical) throws myException {
    	super();
        
    	this.rb_INIT_4_DB(_TAB.bewegung_station);
    	this.is_physical = b_is_physical;
    	
    	this.stationstyp = p_stationstyp;

    	if (this.is_physical) {
    		this.registerComponent(BEWEGUNG_STATION.id_adresse,		new LL_CO_search_own_station(this.stationstyp, FZ__CONST.FIELD_WIDTH.ADRESSSUCHE.get_pixel()));
    	} else {
    		this.registerComponent(BEWEGUNG_STATION.id_adresse,		new RB_TextField(35));
    	}
    	
    	this.registerComponent(BEWEGUNG_STATION.id_adresse_besitzer, 	new RB_besitzer_label(false));

    	this.registerComponent(BEWEGUNG_STATION.id_adresse_basis,		new RB_TextField_resizable(150));
		this.registerComponent(BEWEGUNG_STATION.bestellnummer,    	new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.hausnummer,    		new RB_TextField_resizable(35));
		this.registerComponent(BEWEGUNG_STATION.id_wiegekarte,    	new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.kontrollmenge,    	new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.laendercode,    		new RB_TextField_resizable(35));
		this.registerComponent(BEWEGUNG_STATION.name1,    			new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.name2,    			new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.name3,    			new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.ort,    				new RB_TextField_resizable(285));
		this.registerComponent(BEWEGUNG_STATION.ortzusatz,    		new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.plz,    				new RB_TextField_resizable(70));
		this.registerComponent(BEWEGUNG_STATION.strasse,    			new RB_TextField_resizable(360));
		this.registerComponent(BEWEGUNG_STATION.oeffnungszeiten,		new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.fax, 					new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.telefon, 				new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.wiegekartenkenner, 	new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.id_bewegung_station, 	new RB_TextField_resizable(400));

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
    	
    	SurfaceSettingsContainer.rb_get_forcedDB(BEWEGUNG_STATION.mengenvorzeichen)	.rb_set_formated_value_forced_at_save((this.stationstyp==ENUM_STATION_TYP.START_STATION?"-1":"1"));
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse_besitzer)		.set_rb_Default(bibSES.RECORD_MANDANT().ufs(MANDANT.eigene_adress_id));   	
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse_besitzer)		.set_Enabled(false);

    	if (!this.is_physical) {
    		SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse)		.set_rb_Default(FZ_SONDERLAGER_ENUM.LG_ZWISCHENLAGER.get_zwischenlager_id());
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse_basis)	.set_rb_Default(FZ_SONDERLAGER_ENUM.LG_ZWISCHENLAGER.get_zwischenlager_id());    	}
        return new MyE2_MessageVector();
    }
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
    
     
}
 
