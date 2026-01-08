 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
   
import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_GridLabelForMask;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelFieldFromTab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelFieldLaender;
import panter.gmbh.basics4project.DB_ENUMS.FIBU_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
  
public class TX_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public TX_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
         
        this.registerComponent(TAX.aktiv,    			new RB_cb()._t(S.ms("Aktiv")));
        this.registerComponent(TAX.dropdown_text,    	new RB_TextField()._width(500));
        this.registerComponent(TAX.historisch,    		new RB_cb()._t(S.ms("Historisch")));
        this.registerComponent(TAX.id_fibu_konto_gs,    new RB_SelFieldFromTab(_TAB.fibu_konto, new VEK<IF_Field>()._a(FIBU_KONTO.beschreibung), new VEK<IF_Field>()._a(FIBU_KONTO.beschreibung))._width(300));
        this.registerComponent(TAX.id_fibu_konto_re,    new RB_SelFieldFromTab(_TAB.fibu_konto, new VEK<IF_Field>()._a(FIBU_KONTO.beschreibung), new VEK<IF_Field>()._a(FIBU_KONTO.beschreibung))._width(300));
        this.registerComponent(TAX.id_land,    			new RB_SelFieldLaender()._width(300));
        this.registerComponent(TAX.id_tax,    			new RB_lab());
        this.registerComponent(TAX.id_tax_group,    	new RB_SelFieldFromTab(_TAB.tax_group, new VEK<IF_Field>()._a(TAX_GROUP.kurztext), new VEK<IF_Field>()._a(TAX_GROUP.kurztext))._width(300));
        this.registerComponent(TAX.info_text_user,    	new RB_TextArea()._width(500)._rows(5));
        this.registerComponent(TAX.leervermerk,    		new RB_cb());
        this.registerComponent(TAX.sort,    			new RB_TextField()._width(100));
        this.registerComponent(TAX.steuersatz,    		new RB_TextField()._width(100));
        this.registerComponent(TAX.steuersatz_neu,    	new RB_TextField()._width(100));
        this.registerComponent(TAX.steuervermerk,    	new RB_TextArea()._width(500)._rows(5));
        this.registerComponent(TAX.taxtyp,    			new RB_selField()._populate(TAX_CONST.TAXTYP_DD_ARRAY)._width(300));
        this.registerComponent(TAX.wechseldatum,    	new RB_date_selektor(100,true));
        
        this.registerComponent(new TX_AEND_MASK_DaughterListForMotherMaskStandalone(p_tpHashMap, true));
        
        //beschriftungselemente registieren
        this.registerComponent(TAX.aktiv.fk().getCpAdd("@TX@"), 			new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.AKTIV));
        this.registerComponent(TAX.dropdown_text.fk().getCpAdd("@TX@"),    	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.DROPDOWN_TEXT));
        this.registerComponent(TAX.historisch.fk().getCpAdd("@TX@"),    	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.HISTORISCH));
        this.registerComponent(TAX.id_fibu_konto_gs.fk().getCpAdd("@TX@") , new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.ID_FIBU_KONTO_GS));
        this.registerComponent(TAX.id_fibu_konto_re.fk().getCpAdd("@TX@"),  new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.ID_FIBU_KONTO_RE));
        this.registerComponent(TAX.id_land.fk().getCpAdd("@TX@"),    		new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.ID_LAND));
        this.registerComponent(TAX.id_tax.fk().getCpAdd("@TX@"),    		new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.ID_TAX));
        this.registerComponent(TAX.id_tax_group.fk().getCpAdd("@TX@"),  	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.ID_TAX_GROUP));
        this.registerComponent(TAX.info_text_user.fk().getCpAdd("@TX@") ,  	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.INFO_TEXT_USER));
        this.registerComponent(TAX.leervermerk.fk().getCpAdd("@TX@"),    	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.LEERVERMERK));
        this.registerComponent(TAX.sort.fk().getCpAdd("@TX@"),    			new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.SORT));
        this.registerComponent(TAX.steuersatz.fk().getCpAdd("@TX@"),    	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.STEUERSATZ));
        this.registerComponent(TAX.steuersatz_neu.fk().getCpAdd("@TX@"),   	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.STEUERSATZ_NEU));
        this.registerComponent(TAX.steuervermerk.fk().getCpAdd("@TX@"),    	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.STEUERVERMERK));
        this.registerComponent(TAX.taxtyp.fk().getCpAdd("@TX@"),    		new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.TAXTYP));
        this.registerComponent(TAX.wechseldatum.fk().getCpAdd("@TX@"),   	new RB_GridLabelForMask(TX_READABLE_FIELD_NAME.WECHSELDATUM));

        //jetzt fuer jedes feld einen label-in-grid einbauen
        
//       //beispiel fuer einen setter-validator 
//       this.registerSetterValidators(TAX.id_xxx,new RB_Mask_Set_And_Valid() {
//			@Override
//			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
//					ExecINFO oExecInfo) throws myException {
//				return null;
//			}
//		});
 
        
        this.registerSetterValidators(TAX.id_tax, (mask, typ)-> {
        	MyE2_MessageVector mv = bibMSG.getNewMV();
        	if (typ==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
	        	RB_MaskController mc = new RB_MaskController(TX_MASK_ComponentMap.this);
	        	Date wechseldatum = (Date)mc.getValueFromScreen(_TAB.tax.rb_km(), TAX.wechseldatum);
	        	BigDecimal steuerWechsel = (BigDecimal)mc.getValueFromScreen(_TAB.tax.rb_km(), TAX.steuersatz_neu);
	        	
	        	if ( (wechseldatum==null && steuerWechsel!=null) ||  wechseldatum!=null && steuerWechsel==null) {
	        		mv._addAlarm(S.ms("Wechseldatum und neuer Steuersatz sind nur gemeinsam sinnvoll!"));
	        	}
        	}
        	return mv;
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
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
    
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        }
        return null;
     }
	
}
 
 
