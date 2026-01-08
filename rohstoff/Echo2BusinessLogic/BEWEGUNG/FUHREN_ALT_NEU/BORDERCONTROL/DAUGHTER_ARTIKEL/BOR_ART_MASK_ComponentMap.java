 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelField_USER;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
 
 
public class BOR_ART_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    public BOR_ART_MASK_ComponentMap(PARAMHASH  p_params) throws myException {
       super();
       this.params = p_params;        
         
//        this.registerComponent(new RB_KF(BORDERCROSSING_ARTIKEL.id_artikel),    new RB_TextField()._width(100));
       this.registerComponent(new RB_KF(BORDERCROSSING_ARTIKEL.id_bordercrossing),    		new RB_TextField()._width(100));
       this.registerComponent(new RB_KF(BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel),  new RB_TextField()._width(100));
       this.registerComponent(new RB_KF(BORDERCROSSING_ARTIKEL.id_artikel),    				new BOR_ART_SelField_Artikel(BORDERCROSSING_ARTIKEL.id_artikel, 400));
       this.registerComponent(new RB_KF(BORDERCROSSING_ARTIKEL.menge),    					new RB_TextField()._width(100));
       
       
       
//       this.registerSetterValidators(BORDERCROSSING_ARTIKEL.id_artikel.fk(), 
//    		   new RB_Mask_Set_And_Valid() {
//								@Override
//								public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
//										ExecINFO oExecInfo) throws myException {
//									if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION){
//										
//										Long idArtikel = rbMASK.get_LActualDBValue(BORDERCROSSING_ARTIKEL.id_artikel.fn(), null, null);
////										idArtikel = rbMASK.getRbDataObjectActual().get_MyRECORD().get_longValue(BORDERCROSSING_ARTIKEL.id_artikel.fn());
//										
//										Rec21 r = new Rec21(_TAB.artikel)._fill_id(idArtikel);
//										String sa = (String) r.getRawResultValue(ARTIKEL.anr1.fn());
//										
//										return new MyE2_MessageVector()._addAlarm("Geklickt " + idArtikel.toString() );
//										
//									} else if (ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION){
//										Long idArtikel = rbMASK.get_LActualDBValue(BORDERCROSSING_ARTIKEL.id_artikel.fn(), null, null);
//										if (idArtikel == null){
//											idArtikel = -1L;
//										}
//										return new MyE2_MessageVector()._addWarn("Artikel geladen: " + idArtikel.toString());
//									}
//									return null;
//								}
//							});
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
		// vordefinierte Werte
		String idBorderCrossing = this.params.get(BOR_ART_PARAMS.BOR_ART_ID_BORDERCROSSING).toString();
		preSettingsContainer.rb_set_forcedValueAtSave(BORDERCROSSING_ARTIKEL.id_bordercrossing, idBorderCrossing);
		
		// mandatory fields
		preSettingsContainer.rb_get(BORDERCROSSING_ARTIKEL.id_artikel).set_MustField(true);
		
		
		return null;
	}
	
}
 
