package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB_SurfaceSettings;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD__GridWithData;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Searcher;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD___CONST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class HADM_ComponentMap extends RB_ComponentMap {

	public HADM_ComponentMap() throws myException {
		super();

		this.registerComponent(new RB_KF(HILFETEXT.id_hilfetext),			new RB_Label(new RB_SQLField(HILFETEXT.id_hilfetext)));
		this.registerComponent(new RB_KF(HILFETEXT.titel),				new RB_TextField_old(new RB_SQLField(HILFETEXT.titel), 500));
		this.registerComponent(new RB_KF(HILFETEXT.hilfetext),			new RB_TextArea_old(new RB_SQLField(HILFETEXT.hilfetext),500,20));
		this.registerComponent(new RB_KF(HILFETEXT.info_developer),		new RB_TextArea_old(new RB_SQLField(HILFETEXT.info_developer),500,20));
		this.registerComponent(new RB_KF(HILFETEXT.status),				new RB_SelectField(HILFETEXT.status,HAD___CONST.TICKET_STATUS.NEW.get_dd_Array(true),false,new Extent(122)));
		this.registerComponent(new RB_KF(HILFETEXT.typ),					new RB_SelectField(HILFETEXT.typ,			HAD___CONST.TICKET_TYP.BUGFIX.get_dd_Array(true),false,new Extent(122)));
		this.registerComponent(new RB_KF(HILFETEXT.modulkenner),			new RB_SelectField(HILFETEXT.modulkenner,	E2_MODULNAME_ENUM.get_dd_moduls(null, true),false,new Extent(250)));
		this.registerComponent(new RB_KF(HILFETEXT.id_user_bearbeiter),	new RB_HighLevel_SelectFieldUser(HILFETEXT.id_user_bearbeiter,	true, new Extent(122), ENUM_USER_TYP.ENTWICKLER));
		this.registerComponent(new RB_KF(HILFETEXT.id_user_ursprung),		new RB_HighLevel_SelectFieldUser(
																			HILFETEXT.id_user_ursprung,	true, new Extent(122), 
																			ENUM_USER_TYP.ENTWICKLER,
																			ENUM_USER_TYP.SUPERVISOR,
																			ENUM_USER_TYP.BUERO,
																			ENUM_USER_TYP.GESCHAEFTSFUEHRER));

		SEL sel = new SEL(VERSION.version,VERSION.id_version).FROM(_TAB.version).ORDERUP(VERSION.version);
		
		this.registerComponent(new RB_KF(HILFETEXT.ticketnummer),				new RB_TextField_old(new RB_SQLField(HILFETEXT.ticketnummer), 80));
		this.registerComponent(new RB_KF(HILFETEXT.abschlussdatum),			new RB_TextField_old(new RB_SQLField(HILFETEXT.abschlussdatum), 80));
		
		
		this.registerComponent(new RB_KF(HILFETEXT.id_version), new RB_SelectField(HILFETEXT.id_version,sel.s(),false,false,new Extent(250)));
		
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

		RECORD_USER  recUser = new RECORD_USER(bibALL.get_RECORD_USER().get_ID_USER_lValue(-1l).longValue());
		
		if (status.equals(MASK_STATUS.NEW)) {
			HAD__GridWithData  had_grid = new HAD_Searcher().get_had_grid_with_data();
			if (had_grid != null) {
				SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.modulkenner)).set_rb_Default(S.NN(had_grid.get_modulKenner()));
				SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.status)).set_rb_Default(HAD___CONST.TICKET_STATUS.NEW.db_val());
				SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.id_user_ursprung)).set_rb_Default(bibALL.get_RECORD_USER().get_ID_USER_cF());
				if (recUser.is_DEVELOPER_YES()) {
					SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.id_user_bearbeiter)).set_rb_Default(bibALL.get_RECORD_USER().get_ID_USER_cF());
				}
				
			}
		}
		SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.status)).set_MustField(true);
		SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.typ)).set_MustField(true);

		//benutzer duerfen masken nur aendern, wenn privilegiert oder selbstgemacht
		if (status.equals(MASK_STATUS.EDIT)) {
			boolean bAllowedToEdit = false;
			
			if (recUser.is_DEVELOPER_YES() || recUser.is_GESCHAEFTSFUEHRER_YES()) {
				bAllowedToEdit = true;
			} else  if (this.getRbDataObjectActual().get_RecORD()!=null) {
				RECORD_HILFETEXT ht = (RECORD_HILFETEXT)this.getRbDataObjectActual().get_RecORD();
				
				if (ht.get_ID_USER_URSPRUNG_cUF_NN("-1").equals(recUser.get_ID_USER_cUF())) {
					bAllowedToEdit = true;
				}
			}
			
			if (!bAllowedToEdit) {
				for (RB_SurfaceSettings  s: SurfaceSettingsContainer.rb_get_hmSurfaceSettings().values()) {
					s.set_Enabled(false);
				}
				
			}
		}
		
		//IMMER: benutzer duerfen das erstellerfeld nur aendern, wenn privilegiert
		if (!(recUser.is_DEVELOPER_YES() || recUser.is_GESCHAEFTSFUEHRER_YES())) {
			SurfaceSettingsContainer.rb_get(new RB_KF(HILFETEXT.id_user_ursprung)).set_Enabled(false);
		}
		
		
		
		
		return new MyE2_MessageVector();
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

}
