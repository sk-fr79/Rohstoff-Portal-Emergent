 
package rohstoff.businesslogic.bewegung2.mask;
   
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentMapContainsMaskPosition;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_labInGridNoDatabase;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.TRANSPORTMITTEL;
import panter.gmbh.basics4project.DB_ENUMS.VERARBEITUNG;
import panter.gmbh.basics4project.DB_ENUMS.VERPACKUNGSART;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.json.reader.JsonMaskFieldDescriptionReader;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.EN_VEKTOR_STATUS;
import rohstoff.businesslogic.bewegung2.detail.B2_maskBt_RecordDetail;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTpVerantwortung;
import rohstoff.businesslogic.bewegung2.global.EnumMaskSonderLabel;
import rohstoff.businesslogic.bewegung2.global.TK;
import rohstoff.businesslogic.bewegung2.list.B2_TransportHashMapAddons;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_ButtonPreisUndSteuerZauber;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_DateField;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_MaskCompHandelsDef;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchAdresseV2Fremdware;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchAdresseV2Spedition;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SelectFieldTransportArt;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValidation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
  
public class B2_MaskComponentMap_VEKTOR extends RB_ComponentMap implements IF_RbComponentMapContainsMaskPosition{
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   								m_tpHashMap = 	null;
 
	private EnTabKeyInMask  											enumTab = null;
	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public B2_MaskComponentMap_VEKTOR(RB_TransportHashMap  p_tpHashMap, EnTabKeyInMask p_enumTab) throws myException {
       super();
    
       this.enumTab = p_enumTab;
       
       int standardSpalte = 290;
       
       this.m_tpHashMap = p_tpHashMap;    
       
    	RecList21 transportmittel = 	(RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_TRANSPORTMITTEL);
     	RecList21 verpackungsart = 		(RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_VERPACKUNGSART);
     	RecList21 verarbeitung = 		(RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_VERARBEITUNG);
     	
     	RecList21 laender = (RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_LAND);
         
       this.registerComponent(BG_VEKTOR.id_bg_vektor,    			new B2_maskBt_RecordDetail());
       this.registerComponent(BG_VEKTOR.id_bg_storno_info,    		new RB_lab());
       this.registerComponent(BG_VEKTOR.id_bg_del_info,    			new RB_lab());
       this.registerComponent(BG_VEKTOR.posnr,    					new RB_TextField()._width(100));
       this.registerComponent(BG_VEKTOR.timestamp_in_mask,    		new RB_TextField()._width(100));
       this.registerComponent(BG_VEKTOR.lieferinfo_tpa,    			new RB_TextArea()._width(400)._rows(5));
       this.registerComponent(BG_VEKTOR.en_transport_typ,    		new B2_SelectFieldTransportArt()._width(standardSpalte));
       this.registerComponent(BG_VEKTOR.transportverantwortung,   	new RB_selField()._populate(EnTpVerantwortung.MANDANT,true)._width(standardSpalte));
			
       this.registerComponent(BG_VEKTOR.id_adresse_fremdware,   	new B2_SearchAdresseV2Fremdware());
       this.registerComponent(BG_VEKTOR.id_adresse_logi_start,    	new RB_TextField()._width(100));
       this.registerComponent(BG_VEKTOR.id_adresse_logi_ziel,    	new RB_TextField()._width(100));
       this.registerComponent(BG_VEKTOR.id_handelsdef,    			new B2_MaskCompHandelsDef());
       this.registerComponent(BG_VEKTOR.kosten_transport,    		new RB_TextField()._width(100));

	 	this.registerComponent(BG_VEKTOR.buchungsnummer,   			new RB_TextField()._width(100));
	 	this.registerComponent(BG_VEKTOR.datum_planung_von,   		new B2_DateField()._ttt(S.ms("Beginn Planungszeitraum der Fuhre"))._addZusatzAction(()->{
	 																					new B2_McForValueSettingsOnMaskAction(this)._setDateFieldsOnPlanungVonSetting();
	 																				}));
	 	this.registerComponent(BG_VEKTOR.datum_planung_bis,   		new B2_DateField());
	 	this.registerComponent(BG_VEKTOR.ek_vk_zuord_zwang,   		new RB_cb());
	 	this.registerComponent(BG_VEKTOR.ek_vk_sorte_lock,   		new RB_cb());

	 	this.registerComponent(BG_VEKTOR.ah7_menge,   				new RB_TextField()._width(100));
	 	this.registerComponent(BG_VEKTOR.ah7_volumen,   			new RB_TextField()._width(100));
	 	this.registerComponent(BG_VEKTOR.ah7_ausstellung_datum,   	new RB_date_selektor(100,true));

	 	this.registerComponent(BG_VEKTOR.id_adresse_spedition,   	new B2_SearchAdresseV2Spedition());
	 	this.registerComponent(BG_VEKTOR.transportmittel,   		new RB_TextField()._width(standardSpalte-5));
	 	this.registerComponent(BG_VEKTOR.id_transportmittel,		(RB_selField)new RB_selField()
						 											._populate(transportmittel,TRANSPORTMITTEL.beschreibung,_TAB.transportmittel.key(),true)._width(standardSpalte)
						 											._aaa(()->{
						 												new B2_McForValueSettingsOnMaskAction(this)._clearTransportMittelFollowFieldsIfTransportmittelIsEmpty();
						 												new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
				 													}));
	 	this.registerComponent(BG_VEKTOR.id_uma_kontrakt,   		new RB_lab());
	 	this.registerComponent(BG_VEKTOR.ordnungsnummer_cmr,   		new RB_TextField()._width(400));
	 	this.registerComponent(BG_VEKTOR.planmenge,   				new RB_TextField()._width(100));
	 	this.registerComponent(BG_VEKTOR.print_anhang7,   			new RB_cb());
	 	this.registerComponent(BG_VEKTOR.transportkennzeichen,   	new RB_TextField()._width(standardSpalte-5));
	 	this.registerComponent(BG_VEKTOR.anhaengerkennzeichen,   	new RB_TextField()._width(standardSpalte-5));
        this.registerComponent(BG_VEKTOR.bemerkung,   				new RB_TextArea()._width(300)._rows(5) );
        this.registerComponent(BG_VEKTOR.bemerkung_fuer_kunde,   	new RB_TextArea()._width(300)._rows(5));
        this.registerComponent(BG_VEKTOR.bemerkung_sachbearbeiter,   new RB_TextArea()._width(300)._rows(5));
        this.registerComponent(BG_VEKTOR.en_vektor_quelle,   		new RB_TextField()._width(100));
        this.registerComponent(BG_VEKTOR.en_vektor_status,   		new RB_TextField()._width(100));
        this.registerComponent(BG_VEKTOR.id_bg_pruefprot_abschluss,   		new RB_lab());
        this.registerComponent(BG_VEKTOR.id_verpackungsart,   		new RB_selField()._populate(verpackungsart,VERPACKUNGSART.verpackungsart,_TAB.verpackungsart.key(),true));
        this.registerComponent(BG_VEKTOR.id_verarbeitung,   		new RB_selField()._populate(verarbeitung,VERARBEITUNG.verarbeitung,_TAB.verarbeitung.key(),true));
        this.registerComponent(BG_VEKTOR.id_land_transit1,   		new RB_selField()._populate(laender, LAND.laendername, LAND.id_land, true)._width(standardSpalte)._fo_s(-2));
        this.registerComponent(BG_VEKTOR.id_land_transit2,   		new RB_selField()._populate(laender, LAND.laendername, LAND.id_land, true)._width(standardSpalte)._fo_s(-2));
        this.registerComponent(BG_VEKTOR.id_land_transit3,   		new RB_selField()._populate(laender, LAND.laendername, LAND.id_land, true)._width(standardSpalte)._fo_s(-2));
        
        this.registerComponent(new B2_ButtonPreisUndSteuerZauber());

        //jedes feld in jeder map bekommt ein potentielles label, das formal mit z.M.V@id_transportmittel beschriftet ist,
        // und was via JSON-eintrag mit demm korrekten wert gefuellt werden kann
        appendLabelInGridForAllFields();
        
        
        
        
        this.registerSetterValidators(BG_VEKTOR.id_bg_vektor,new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = bibMSG.newMV();

				if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION ) {
					new B2_McForMaskShapeSettings(B2_MaskComponentMap_VEKTOR.this)._setAllMaskShapeSettings();
				} 
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					mv._add(new B2_McForValidation(B2_MaskComponentMap_VEKTOR.this).validateAll());
				} 
				
				return mv;
			}
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
		MyE2_MessageVector mv = bibMSG.newMV();

		if (status==MASK_STATUS.NEW) {
			preSettingsContainer.rb_get(BG_VEKTOR.en_vektor_quelle.fk()).set_rb_Default(EN_VEKTOR_QUELLE.NATIV.dbVal());
			preSettingsContainer.rb_get_hmSurfaceSettings().get(BG_VEKTOR.en_vektor_status.fk()).set_rb_Default(EN_VEKTOR_STATUS.GEPLANT.dbVal());
			
			preSettingsContainer.rb_get_hmSurfaceSettings().get(BG_VEKTOR.transportverantwortung.fk()).set_rb_Default(EnTpVerantwortung.MANDANT.dbVal());
		}
		
		preSettingsContainer.rb_get_hmSurfaceSettings().get(BG_VEKTOR.datum_planung_von.fk()).set_MustField(true);
		preSettingsContainer.rb_get_hmSurfaceSettings().get(BG_VEKTOR.transportverantwortung.fk()).set_MustField(true);

		
        return mv;
     }
	
	
    
    
    /**
     * baut zuerst fuer jedes feld eine vordefiniertes RB_labInGridNoDatabase auf, was spaeter aus der json-datei fuer die maskenfelder ueberschrieben werden kann
     * @author martin
     * @date 29.05.2020
     *
     * @throws myException
     */
    private void appendLabelInGridForAllFields() {
    	
    	try {
    		
    		//zuerst fuer jeden eintrag (alle beteiligen Tabellen und alle fields in diese tabellen einen dummy erstellen
			for (EnTabKeyInMask enumTab: EnTabKeyInMask.values()) {
				for (IF_Field f: enumTab.getTable().get_fields()) {
					this.registerComponent(new RB_labInGridNoDatabase(TK.gen(enumTab, f)));
				}
			}
			for (EnumMaskSonderLabel sonderlabel: EnumMaskSonderLabel.values()) {
				this.registerComponent(new RB_labInGridNoDatabase(TK.gen(sonderlabel)));
			}
			
			
			//jetzt mit den json ueberschreiben
			//File jsonFile = new File(File.separator+bibALL.get_WEBROOTPATH()+File.separator+ENUM_REGISTRY.SUBDIR_TO_JSON.getStringValue()+File.separator+"bgFuhreMaskTextDefinition.json");
			try {
				JsonMaskFieldDescriptionReader helper = new JsonMaskFieldDescriptionReader();
			    HMAP<RB_KM, HMAP<TK, RB_labInGridNoDatabase>>  maskLabelDefs = helper.getMapWithLabels();
			    for (RB_KM keyM: maskLabelDefs.keySet()) {
			    	HMAP<TK, RB_labInGridNoDatabase> innerMap = maskLabelDefs.get(keyM);
			    	for (RB_KF keyF: innerMap.keySet()) {
			    		this.registerReplaceComponent(keyF, innerMap.get(keyF));
			    	}
			    }
			} catch (Exception e) {
				e.printStackTrace();
				bibMSG.MV()._addWarn("Error reading setting-File!"+e.getLocalizedMessage());
			}

			
		} catch (myException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }


	@Override
	public String getPositionCode() {
		return this.enumTab.dbVal();
	}
    
    
    
	
}
 
 
