 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
   
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.basics4project.ENUM_AdressInfoMessageType;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS.AKTIONSANLASS;
import panter.gmbh.basics4project.DB_ENUMS.BESUCHSERGEBNIS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
  
public class AI_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public AI_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
         
        this.registerComponent(new RB_KF(ADRESSE_INFO.aktiv),    new RB_cb());
        this.registerComponent(new RB_KF(ADRESSE_INFO.datumeintrag),    		new RB_date_selektor(100,true));
        this.registerComponent(new RB_KF(ADRESSE_INFO.datumereignis),    		new RB_date_selektor(100,true));
        this.registerComponent(new RB_KF(ADRESSE_INFO.folgedatum),    			new RB_date_selektor(100,true));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_adresse),        		new RB_lab());
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_adresse_info),    		new RB_lab());
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_aktionsanlass),        new RB_selField()._width(200)
        																					._populate(new RecList21(_TAB.aktionsanlass)._fillWithAll(), 
        																										AKTIONSANLASS.kurzbezeichnung, AKTIONSANLASS.id_aktionsanlass, true));
        RecList21 rlBesErg = new RecList21(_TAB.besuchsergebnis)._fillWithAll();
        
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis1),    	new RB_selField()._width(200)
        																				._populate(rlBesErg, BESUCHSERGEBNIS.kurzbezeichnung, BESUCHSERGEBNIS.id_besuchsergebnis, true));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis2),    	new RB_selField()._width(200)
        																				._populate(rlBesErg, BESUCHSERGEBNIS.kurzbezeichnung, BESUCHSERGEBNIS.id_besuchsergebnis, true));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis3),    	new RB_selField()._width(200)
        																				._populate(rlBesErg, BESUCHSERGEBNIS.kurzbezeichnung, BESUCHSERGEBNIS.id_besuchsergebnis, true));
        
		USER_SELECTOR_GENERATOR_NT  selUsers = new USER_SELECTOR_GENERATOR_NT(true,ENUM_USER_TYP.AKTIV, ENUM_USER_TYP.BUERO, ENUM_USER_TYP.ENTWICKLER, ENUM_USER_TYP.SUPERVISOR, ENUM_USER_TYP.GESCHAEFTSFUEHRER);

        this.registerComponent(new RB_KF(ADRESSE_INFO.id_user),    				new RB_selField()._width(200)._populate(selUsers.get_selUsers(true))._populateShadow(selUsers.get_notSelectedUsers()));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_user_ersatz),    		new RB_selField()._width(200)._populate(selUsers.get_selUsers(true))._populateShadow(selUsers.get_notSelectedUsers()));
        this.registerComponent(new RB_KF(ADRESSE_INFO.id_user_sachbearbeiter),  new RB_selField()._width(200)._populate(selUsers.get_selUsers(true))._populateShadow(selUsers.get_notSelectedUsers()));
        this.registerComponent(new RB_KF(ADRESSE_INFO.ist_message),    			new RB_cb()._width(20));
        this.registerComponent(new RB_KF(ADRESSE_INFO.kuerzel),    				new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(ADRESSE_INFO.message_sofort),    		new RB_cb()._width(20));
        this.registerComponent(new RB_KF(ADRESSE_INFO.message_typ),    			new RB_selField()._width(200)._populate(ENUM_AdressInfoMessageType.ADRESS_INFO_TYP_ALLGEMEIN.get_dd_Array(true)));
        this.registerComponent(new RB_KF(ADRESSE_INFO.text),   					new RB_TextArea()._width(500)._rows(8));
        this.registerComponent(new RB_KF(ADRESSE_INFO.wiederholungjaehrlich),   new RB_cb());
        this.registerComponent(new RB_KF(ADRESSE_INFO.wiederholungmonatlich),   new RB_cb());
        
        
//       //beispiel fuer einen setter-validator 
//       this.registerSetterValidators(ADRESSE_INFO.id_xxx,new RB_Mask_Set_And_Valid() {
//			@Override
//			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
//					ExecINFO oExecInfo) throws myException {
//				return null;
//			}
//		});
 
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
            
            AI__TYP typ = (AI__TYP)m_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);

            
            Rec21 adresse = new Rec21(_TAB.adresse)._fill_id(this.m_tpHashMap.getMotherKeyValue());
            
            
            if (this.getParams()==null || this.getParams().getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG)==null) {
            	throw new myException(this,"init-process is not finished yet");
            }
            
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.aktiv.fk()).set_rb_Default("Y");
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.kuerzel.fk()).set_rb_Default(bibALL.get_KUERZEL());
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.kuerzel.fk()).set_Enabled(false);
            
            if (S.isFull(adresse.getUfs(ADRESSE.id_user)) && typ == AI__TYP.INFO) {
            	preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.id_user.fk()).set_rb_Default(adresse.getUfs(ADRESSE.id_user));
            }
            
            
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.datumeintrag.fk()).set_rb_Default(bibALL.get_cDateNOW());
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.datumereignis.fk()).set_rb_Default(bibALL.get_cDateNOW());
            
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.text.fk()).set_MustField(true);
            
            preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.ist_message.fk()).set_Enabled(false);
            if (typ==AI__TYP.INFO) {
            	preSettingsContainer.rb_set_forcedValueAtSave(ADRESSE_INFO.ist_message, "N");
            	preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.ist_message.fk()).set_rb_Default("N");
            	
            } else {
            	preSettingsContainer.rb_set_forcedValueAtSave(ADRESSE_INFO.ist_message, "Y");
            	preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.ist_message.fk()).set_rb_Default("Y");
                preSettingsContainer.rb_get_hmSurfaceSettings().get(ADRESSE_INFO.message_typ.fk()).set_MustField(true);

            }
            
            
        }
        return null;
     }
	
}
 
 
