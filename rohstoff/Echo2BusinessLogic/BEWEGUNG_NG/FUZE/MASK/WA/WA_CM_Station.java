package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SONDERLAGER_ENUM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_TextField_resizable;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_besitzer_label;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;

public class WA_CM_Station extends RB_ComponentMap {

	private boolean 	is_physical;

	private ENUM_STATION_TYP stations_typ;

	public WA_CM_Station(ENUM_STATION_TYP p_stationstyp, boolean b_is_physical) throws myException {
		super();

		this.rb_INIT_4_DB(_TAB.bewegung_station);
		this.is_physical = b_is_physical;

		this.stations_typ = p_stationstyp;

		if(stations_typ==ENUM_STATION_TYP.START_STATION){
			this.registerComponent(new RB_KF(BEWEGUNG_STATION.id_adresse),		new WA_CO_SearchLager(stations_typ, FZ__CONST.FIELD_WIDTH.ADRESSSUCHE.get_pixel()));
		}else{
			this.registerComponent(new RB_KF(BEWEGUNG_STATION.id_adresse),		new WA_CO_SearchStation(stations_typ, FZ__CONST.FIELD_WIDTH.ADRESSSUCHE.get_pixel()));
		}
//		this.rb_register(new RB_KF(BEWEGUNG_STATION.id_adresse_besitzer),   new WA_CO_SearchBesitzer(stations_typ));

		this.registerComponent(BEWEGUNG_STATION.id_adresse_besitzer,  new RB_besitzer_label(false));

		this.registerComponent(new RB_KF(BEWEGUNG_STATION.id_adresse_basis),		new RB_TextField_resizable(150));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.bestellnummer),    		new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.hausnummer),    		new RB_TextField_resizable(35));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.id_wiegekarte),    		new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.kontrollmenge),    		new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.laendercode),    		new RB_TextField_resizable(35));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.name1),    				new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.name2),    				new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.name3),    				new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.ort),    				new RB_TextField_resizable(285));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.ortzusatz),    			new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.plz),    				new RB_TextField_resizable(70));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.strasse),    			new RB_TextField_resizable(360));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.oeffnungszeiten),		new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.fax), 					new RB_TextField_resizable(400));
		this.registerComponent(new RB_KF(BEWEGUNG_STATION.telefon), 				new RB_TextField_resizable(400));

		this.registerComponent(BEWEGUNG_STATION.wiegekartenkenner, 				new RB_TextField_resizable(400));
		this.registerComponent(BEWEGUNG_STATION.id_bewegung_station, 				new RB_TextField_resizable(400));
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

		SurfaceSettingsContainer.rb_get_forcedDB(BEWEGUNG_STATION.mengenvorzeichen).rb_set_formated_value_forced_at_save((this.stations_typ==ENUM_STATION_TYP.START_STATION?"-1":"1"));
		SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse_basis).set_Enabled(false);

		if(stations_typ == ENUM_STATION_TYP.START_STATION){
			SurfaceSettingsContainer.rb_get_forcedDB(BEWEGUNG_STATION.id_adresse_besitzer).rb_set_formated_value_forced_at_save(bibALL.get_ID_ADRESS_MANDANT());
		}
		
		if(!is_physical){
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse)		.set_rb_Default(FZ_SONDERLAGER_ENUM.WA_ZWISCHENLAGER.get_zwischenlager_id());
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.id_adresse_basis)	.set_rb_Default(FZ_SONDERLAGER_ENUM.WA_ZWISCHENLAGER.get_zwischenlager_id());

			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.name1)				.set_rb_Default("");
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.name2)				.set_rb_Default("");
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.name3)				.set_rb_Default("");

			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.hausnummer)		.set_rb_Default("");
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.strasse)			.set_rb_Default("");
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.ort)				.set_rb_Default("");
			SurfaceSettingsContainer.rb_get(BEWEGUNG_STATION.laendercode)		.set_rb_Default("");
		}

		
		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {}

}
