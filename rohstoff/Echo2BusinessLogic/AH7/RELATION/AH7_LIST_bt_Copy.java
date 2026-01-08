 
package rohstoff.Echo2BusinessLogic.AH7.RELATION;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V3;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class AH7_LIST_bt_Copy extends AH7_LIST_bt_New {
    
    public AH7_LIST_bt_Copy(E2_NavigationList p_naviList) {
        super(p_naviList);
        this._image(E2_ResourceIcon.get_RI("copy.png") , true);
        this._setInjectValuesForPresetNewMask(new ownInjector());
        this._ttt(S.ms("Kopieren einer AH7-Steuerungregel"));
        this.add_GlobalValidator(ENUM_VALIDATION.AH7_STEUERDATEI_VIEW.getValidatorWithoutSupervisorPersilschein());
    }

    private class ownInjector implements RB_bt_New_V3.InjectValuesForPresetNewMask {

		@Override
		public void injectValues(RB_ComponentMap map, MyE2_MessageVector mv) throws myException {
			E2_NavigationList naviList =  AH7_LIST_bt_Copy.this.get_NaviList();
			
			Vector<String> idsSelected = naviList.get_vSelectedIDs_Unformated();
			
			if (idsSelected.size()!=1) {
				mv._addAlarm(S.ms("Sie müssen zum Kopieren genau eine Zeile in der Liste als Quelle der Kopei auswählen !"));
			} else {
				RB_MaskController con = new RB_MaskController(map);
				Rec21 r = new Rec21(_TAB.ah7_steuerdatei)._fill_id(idsSelected.get(0));

				((AH7_Mask_SearchStation)con.get_comp(AH7_STEUERDATEI.id_adresse_geo_start, mv)).rb_set_db_value_manual(r.getFs(AH7_STEUERDATEI.id_adresse_geo_start),true,false);
				((AH7_Mask_SearchStation)con.get_comp(AH7_STEUERDATEI.id_adresse_geo_ziel, mv)).rb_set_db_value_manual(r.getFs(AH7_STEUERDATEI.id_adresse_geo_ziel),true,false);
				
				con.set_maskVal(AH7_STEUERDATEI.id_ah7_profil, 			r.getFs(AH7_STEUERDATEI.id_ah7_profil), mv);
				
				con.set_maskVal(AH7_STEUERDATEI.id_1_abfallerzeuger, 	r.getFs(AH7_STEUERDATEI.id_1_abfallerzeuger), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_1_import_empfaenger, r.getFs(AH7_STEUERDATEI.id_1_import_empfaenger), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_1_verbr_veranlasser, r.getFs(AH7_STEUERDATEI.id_1_verbr_veranlasser), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_1_verwertungsanlage, r.getFs(AH7_STEUERDATEI.id_1_verwertungsanlage), mv);
				
				con.set_maskVal(AH7_STEUERDATEI.drucke_blatt2, 	r.getFs(AH7_STEUERDATEI.drucke_blatt2), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_2_abfallerzeuger, 	r.getFs(AH7_STEUERDATEI.id_2_abfallerzeuger), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_2_import_empfaenger, r.getFs(AH7_STEUERDATEI.id_2_import_empfaenger), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_2_verbr_veranlasser, r.getFs(AH7_STEUERDATEI.id_2_verbr_veranlasser), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_2_verwertungsanlage, r.getFs(AH7_STEUERDATEI.id_2_verwertungsanlage), mv);

				con.set_maskVal(AH7_STEUERDATEI.drucke_blatt3, 	r.getFs(AH7_STEUERDATEI.drucke_blatt3), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_3_abfallerzeuger, 	r.getFs(AH7_STEUERDATEI.id_3_abfallerzeuger), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_3_import_empfaenger, r.getFs(AH7_STEUERDATEI.id_3_import_empfaenger), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_3_verbr_veranlasser, r.getFs(AH7_STEUERDATEI.id_3_verbr_veranlasser), mv);
				con.set_maskVal(AH7_STEUERDATEI.id_3_verwertungsanlage, r.getFs(AH7_STEUERDATEI.id_3_verwertungsanlage), mv);
				
				con.set_maskVal(AH7_STEUERDATEI.status_relation, 		r.getFs(AH7_STEUERDATEI.status_relation), mv);
				con.set_maskVal(AH7_STEUERDATEI.locked, 				"N", mv);

			}
			
		}

//		@Override
//		public HashMap<RB_KM, Rec20> getInjectPresetValues(MyE2_MessageVector mv) throws myException {
//			E2_NavigationList naviList =  AH7_LIST_bt_Copy.this.get_NaviList();
//			
//			Vector<String> idsSelected = naviList.get_vSelectedIDs_Unformated();
//			
//			HashMap<RB_KM, Rec20> hm = new HashMap<RB_KM, Rec20>();
//			
//			if (idsSelected.size()!=1) {
//				mv._addAlarm(S.ms("Sie müssen zum Kopieren genau eine Zeile in der Liste als Quelle der Kopei auswählen !"));
//			} else {
//				hm.put(new AH7_KEY(), new Rec21(_TAB.ah7_steuerdatei)._fill_id(idsSelected.get(0)));
//			}
//			
//			return hm;
//		}
//    	
    }
    
    
	
	
} 
