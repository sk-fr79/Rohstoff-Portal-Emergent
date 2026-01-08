 
package rohstoff.businesslogic.bewegung2.mask;
   
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentMapContainsMaskPosition;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.detail.B2_maskBt_RecordDetail;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.list.B2_TransportHashMapAddons;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_AdressOwnerLabel;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchAdresseV2Lager;
  
public class B2_MaskComponentMap_STATION extends RB_ComponentMap  implements IF_RbComponentMapContainsMaskPosition{
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
	
	private EnPositionStation     enPositionStation = null;

	private EnTabKeyInMask  			  enumTab = null;

	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public B2_MaskComponentMap_STATION(RB_TransportHashMap  p_tpHashMap, EnPositionStation   positionStation, EnTabKeyInMask p_enumTab) throws myException {
		super();
    
	    this.enumTab = p_enumTab;

	    int standardSpalte = 390;

       	this.m_tpHashMap = p_tpHashMap;        
        this.enPositionStation = positionStation;

       	RecList21 land = (RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_LAND);
         
       	this.registerComponent(BG_STATION.id_bg_station,   		new B2_maskBt_RecordDetail());
       	this.registerComponent(BG_STATION.id_bg_vektor,   		new RB_lab());
       	this.registerComponent(BG_STATION.id_land,   			new RB_selField()._populate(land,LAND.laendername,_TAB.land.key(),true)._width(200));
       	this.registerComponent(BG_STATION.id_adresse,   		new B2_SearchAdresseV2Lager(enPositionStation));
       	this.registerComponent(BG_STATION.id_adresse_basis,   	new RB_TextField()._width(200));
       	this.registerComponent(BG_STATION.id_adresse_besitz_ldg,new B2_AdressOwnerLabel(enPositionStation));
       	this.registerComponent(BG_STATION.id_wiegekarte,   		new RB_TextField()._width(200));
       	this.registerComponent(BG_STATION.id_bg_storno_info,   	new RB_lab());
       	this.registerComponent(BG_STATION.id_bg_del_info,   	new RB_lab());
       	this.registerComponent(BG_STATION.wiegekartenkenner,   	new RB_TextField()._width(100));
       	this.registerComponent(BG_STATION.kontrollmenge,   		new RB_TextField()._width(100));
       	this.registerComponent(BG_STATION.name1,   				new RB_TextField()._width(380));
       	this.registerComponent(BG_STATION.name2,   				new RB_TextField()._width(380));
       	this.registerComponent(BG_STATION.name3,   				new RB_TextField()._width(380));
       	this.registerComponent(BG_STATION.strasse,   			new RB_TextField()._width(380));
       	this.registerComponent(BG_STATION.hausnummer,   		new RB_TextField()._width(200));
       	this.registerComponent(BG_STATION.plz,   				new RB_TextField()._width(200));
       	this.registerComponent(BG_STATION.ort,   				new RB_TextField()._width(380));
       	this.registerComponent(BG_STATION.ortzusatz,   			new RB_TextField()._width(380));
       	this.registerComponent(BG_STATION.oeffnungszeiten,   	new RB_TextArea()._width(standardSpalte-5)._rows(1));
       	this.registerComponent(BG_STATION.telefon,   			new RB_TextField()._width(standardSpalte-5));
        this.registerComponent(BG_STATION.fax,   				new RB_TextField()._width(standardSpalte-5));
        this.registerComponent(BG_STATION.timestamp_in_mask,   	new RB_TextField()._width(100));
        this.registerComponent(BG_STATION.pos_in_mask,  	 	new RB_TextField()._width(100));
        this.registerComponent(BG_STATION.umsatzsteuerlkz,  	new RB_TextField()._width(30)._fsa(-0));
        this.registerComponent(BG_STATION.umsatzsteuerid, 	 	new RB_TextField()._width(standardSpalte-39)._fsa(-0));
        
        //this.registerComponent(B2_EnumMaskComponentKeys.GRID_4_STATION.getKey(),(E2_Grid_V2)new E2_Grid_V2()._w100()._height(new Extent(20)));

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
    
		if (status.isStatusNew()) {
			String defaultVal = null;
			switch (enPositionStation) {
			case LEFT:
				defaultVal = EnTabKeyInMask.S1.dbVal();
				break;
			case MID:
				defaultVal = EnTabKeyInMask.S2.dbVal();
				break;
			case RIGHT:
				defaultVal = EnTabKeyInMask.S3.dbVal();
				break;
			default:
				break;
			}
			preSettingsContainer.rb_get(BG_STATION.pos_in_mask).set_rb_Default(defaultVal);
		}

        return null;
     }
	
	
	@Override
	public String getPositionCode() {
		return this.enumTab.dbVal();
	}
 
}
 
 
