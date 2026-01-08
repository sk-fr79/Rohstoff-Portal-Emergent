 
package rohstoff.businesslogic.bewegung2.mask;
   
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentMapContainsMaskPosition;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_TextFieldReadOnly;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelectWaehrung;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.EnPruefungTyp;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.detail.B2_maskBt_RecordDetail;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.list.B2_TransportHashMapAddons;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_DateField;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_InfoBlockFremdwaehrungMask;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_MaskComponentAvvCode;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_MaskComponentPruefungStamp;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchAngebotsPosV2;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchArtbezV2;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchKontraktPosV2;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SelectTaxInGroups;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValidation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
  
public class B2_MaskComponentMap_ATOM extends RB_ComponentMap  implements IF_RbComponentMapContainsMaskPosition{
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
	
	private EnTabKeyInMask  			  enumTab = null;

	private EnPositionStation     enPositionStation = null;
	
	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public B2_MaskComponentMap_ATOM(RB_TransportHashMap  p_tpHashMap, EnPositionStation   positionStation, EnTabKeyInMask p_enumTab) throws myException {
       super();
    
       int standardSpalte = 390;
       
       this.enumTab = p_enumTab;

       
       this.m_tpHashMap = p_tpHashMap;        
       this.enPositionStation = positionStation;
        
       RecList21 liefBed = (RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_LIEFERBEDINGUNGEN);
       RecList21 zahlBed = (RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_ZAHLUNGSBEDINGUNGEN);
//       RecList21 tax =     (RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_TAX);
       RecList21 zolltartif = (RecList21)m_tpHashMap.getFromExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_ZOLLTARIFNUMMER);
         
       this.registerComponent(BG_ATOM.id_bg_atom,    			new B2_maskBt_RecordDetail());
       this.registerComponent(BG_ATOM.id_bg_vektor,    			new RB_lab());
       this.registerComponent(BG_ATOM.id_bg_station_quelle,		new RB_lab());
       this.registerComponent(BG_ATOM.id_bg_station_ziel,		new RB_lab());
       
       this.registerComponent(BG_ATOM.id_bg_rech_block,    		new RB_lab());

       this.registerComponent(BG_ATOM.lieferbedingungen,    	new RB_TextField()._width(275));
       this.registerComponent(BG_ATOM.id_lieferbedingungen,    (RB_selField)new RB_selField()
    		   													._populate(liefBed,LIEFERBEDINGUNGEN.kurzbezeichnung,_TAB.lieferbedingungen.key(),true)
       															._width(105)
       															._aaa(()->{
       																new B2_McForValueSettingsOnMaskAction(this)._clearLieferbedAfterSelected(enPositionStation,null);
       																new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
       															}))
       															;
       this.registerComponent(BG_ATOM.zahlungsbedingungen,    	new RB_TextField()._width(275));
       this.registerComponent(BG_ATOM.id_zahlungsbedingungen,   (RB_selField)new RB_selField()
    		   													._populate(zahlBed,ZAHLUNGSBEDINGUNGEN.kurzbezeichnung,_TAB.zahlungsbedingungen.key(),true)
    		   													._width(105)
       															._aaa(()->{
       																new B2_McForValueSettingsOnMaskAction(this)._clearZahlungsbedAfterSelected(enPositionStation, null);
       																new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
       															}))
      															;

       this.registerComponent(BG_ATOM.id_zolltarifnummer,  		 new RB_selField()
					._populate(zolltartif,(r)->{
							String s = "";
							try {
								s = s +(r.getFs(ZOLLTARIFNUMMER.nummer, "")+" - "+(r.getFs(ZOLLTARIFNUMMER.text1, "")));
							} catch (myException e) {
								s = "<Err>";
								e.printStackTrace();
							}
							return s;
						},_TAB.zolltarifnummer.key(),true)
					._width(380))
					;

       
       this.registerComponent(BG_ATOM.id_artikel,   			new RB_lab());
       
       this.registerComponent(BG_ATOM.id_artikel_bez,   		new B2_SearchArtbezV2(enPositionStation));
       
       this.registerComponent(BG_ATOM.id_vpos_kon,   			new B2_SearchKontraktPosV2(enPositionStation));
       
       if (enPositionStation==EnPositionStation.LEFT) {
	       this.registerComponent(BG_ATOM.id_vpos_std,   			new B2_SearchAngebotsPosV2(ENUM_VORGANGSART.ABNAHMEANGEBOT));
       } else {
	       this.registerComponent(BG_ATOM.id_vpos_std,   			new B2_SearchAngebotsPosV2(ENUM_VORGANGSART.ANGEBOT));
       }
       
   
       this.registerComponent(BG_ATOM.id_tax,   				(B2_SelectTaxInGroups)new B2_SelectTaxInGroups(enPositionStation)._aaa(()-> {
    	   																new B2_McForValueSettingsOnMaskAction(this)._setSteuerSatzAndSteuerTextAfterSteuerIsSet(enPositionStation);
       																}));
       this.registerComponent(BG_ATOM.id_bg_pruefprot_menge,    new B2_MaskComponentPruefungStamp(enPositionStation.getKeyAtom(),BG_ATOM.id_bg_pruefprot_menge, EnPruefungTyp.BG_ATOM_MENGENKONTROLLE)
														._addChangeListener((o)-> {
															MyE2_MessageVector mv = bibMSG.getNewMV();
															mv._add(new B2_McForValidation(B2_MaskComponentMap_ATOM.this, mv).validAbschlussMenge(positionStation));
															if (mv.hasAlarms()) {
																o.getCheckBox().setSelected(false);
															}
															return mv;
														})._addChangeListener((o)-> {
															if (bibMSG.get_bIsOK()) {
																new B2_McForMaskShapeSettings(B2_MaskComponentMap_ATOM.this, bibMSG.MV())._setAllMaskShapeSettings();
															}
															return bibMSG.getNewMV();
														})
															);
       this.registerComponent(BG_ATOM.id_bg_pruefprot_preis,    new B2_MaskComponentPruefungStamp(enPositionStation.getKeyAtom(),BG_ATOM.id_bg_pruefprot_preis, EnPruefungTyp.BG_ATOM_PREISKONTROLLE)
														._addChangeListener((o)-> {
																MyE2_MessageVector mv = bibMSG.getNewMV();
																new B2_McForValueSettingsOnMaskAction(B2_MaskComponentMap_ATOM.this,mv)._loadPreisAndWaehrungskursFromKontraktOrAngebot(positionStation,mv);
																new B2_McForValueSettingsOnMaskAction(B2_MaskComponentMap_ATOM.this, mv)._berechneFremdPreis(positionStation,mv);
																mv._add(new B2_McForValidation(B2_MaskComponentMap_ATOM.this,mv).validAbschlussPreis(positionStation));
																if (mv.hasAlarms()) {
																	o.getCheckBox().setSelected(false);
																}
																return mv;
														})._addChangeListener((o)-> {
															if (bibMSG.get_bIsOK()) {
																new B2_McForMaskShapeSettings(B2_MaskComponentMap_ATOM.this,bibMSG.MV())._setAllMaskShapeSettings();
															}
															return bibMSG.getNewMV();
														})
														);
       this.registerComponent(BG_ATOM.id_bg_pruefprot_abschluss,new B2_MaskComponentPruefungStamp(enPositionStation.getKeyAtom(),BG_ATOM.id_bg_pruefprot_abschluss, EnPruefungTyp.BG_ATOM_ABSCHLUSS)
																._addChangeListener((o)-> {
																	MyE2_MessageVector mv = bibMSG.getNewMV();
																	mv._add(new B2_McForValidation(B2_MaskComponentMap_ATOM.this,mv).validAbschlussLadung(positionStation));
																	if (mv.hasAlarms()) {
																		o.getCheckBox().setSelected(false);
																	}
																	return mv;
																})._addChangeListener((o)-> {
																	if (bibMSG.get_bIsOK()) {
																		new B2_McForMaskShapeSettings(B2_MaskComponentMap_ATOM.this,bibMSG.MV())._setAllMaskShapeSettings();
																	}
																	return bibMSG.getNewMV();
																})
															);

       this.registerComponent(BG_ATOM.id_bg_pruefport_gelangensbest,new B2_MaskComponentPruefungStamp(enPositionStation.getKeyAtom(),BG_ATOM.id_bg_pruefport_gelangensbest, EnPruefungTyp.BG_ATOM_GELANGENSBESTAETIGUNG)
														._addChangeListener((o)-> {
															new B2_McForMaskShapeSettings(B2_MaskComponentMap_ATOM.this,bibMSG.MV())._setAllMaskShapeSettings();
															return bibMSG.getNewMV();
														})
													);

       
       
       this.registerComponent(BG_ATOM.id_bg_storno_info,   		new RB_lab());
       this.registerComponent(BG_ATOM.id_bg_del_info,   		new RB_lab());
       this.registerComponent(BG_ATOM.id_waehrung,	 			new RB_SelectWaehrung()._width(105));
       this.registerComponent(BG_ATOM.waehrungskurs, 			new RB_TextField()._width(60));
       this.registerComponent(BG_ATOM.manuell_preis,    		new RB_cb())._ttt("Preis wird manuell erfaßt, keine Übernahme der Kontrakt- oder Angebotspreise");
       this.registerComponent(BG_ATOM.e_preis_basiswaehrung, 	new RB_TextField()._width(100))._ttt(S.ms("Einzelpreis in "+bibALL.get_WAEHRUNG_BASISWAEHRUNG_KURZ()));
       this.registerComponent(BG_ATOM.e_preis_fremdwaehrung, 	new RB_TextFieldReadOnly()._width(100))._ttt(S.ms("Einzelpreis in Fremdwährung"));
       this.registerComponent(BG_ATOM.e_preis_res_netto_mge_basis, 	new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.e_preis_res_netto_mge_fremd, 	new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.g_preis_basiswaehrung,   	new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.g_preis_fremdwaehrung,  	new RB_TextFieldReadOnly()._width(100));
       this.registerComponent(BG_ATOM.g_preis_abzug_basis,   	new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.g_preis_abzug_fremd,  	new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.datum_ausfuehrung,    	new B2_DateField()._addZusatzAction(()->{
    	   																				new B2_McForValueSettingsOnMaskAction(this)._setOtherLeistungsdatum(enPositionStation);}));
       this.registerComponent(BG_ATOM.menge, 				   	new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.menge_abzug,    			new RB_TextFieldReadOnly()._width(100));
       this.registerComponent(BG_ATOM.menge_verteilung,    		new RB_TextFieldReadOnly()._width(100));
       this.registerComponent(BG_ATOM.menge_netto,    			new RB_TextFieldReadOnly()._width(100));
       this.registerComponent(BG_ATOM.menge_abrech,    			new RB_TextFieldReadOnly()._width(100));
       this.registerComponent(BG_ATOM.anr1,   					new RB_TextField()._width(150));
       this.registerComponent(BG_ATOM.anr2,   					new RB_TextField()._width(150));
       this.registerComponent(BG_ATOM.artbez1,   				new RB_TextField()._width(380));
       this.registerComponent(BG_ATOM.artbez2,   				new RB_TextArea()._width(380)._rows(5));
       this.registerComponent(BG_ATOM.kontraktzwang,    		new RB_cb());
       this.registerComponent(BG_ATOM.postennummer,				new RB_TextField()._width(standardSpalte-5));
       this.registerComponent(BG_ATOM.eu_steuer_vermerk,   		new RB_TextArea()._width(380)._rows(2)._fsa(-2));
       this.registerComponent(BG_ATOM.bemerkung_extern,   		new RB_TextArea()._width(380)._rows(5));
       this.registerComponent(BG_ATOM.bemerkung_intern,   		new RB_TextArea()._width(380)._rows(5));
       this.registerComponent(BG_ATOM.timestamp_in_mask,   		new RB_TextFieldReadOnly()._width(100));
       this.registerComponent(BG_ATOM.kosten_produkt,   		new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.steuersatz,   			new RB_TextFieldReadOnly()._width(30)._fsa(-2));
       this.registerComponent(BG_ATOM.notifikation_nr,  		new RB_TextField()._width(standardSpalte-5));
       this.registerComponent(BG_ATOM.nationaler_abfall_code,	new RB_TextField()._width(400));
       this.registerComponent(BG_ATOM.bestellnummer,   			new RB_TextField()._width(standardSpalte-5));
       this.registerComponent(BG_ATOM.pos_in_mask,  	 		new RB_TextField()._width(100));
       this.registerComponent(BG_ATOM.id_eak_code,  		  	new B2_MaskComponentAvvCode(enPositionStation));
       this.registerComponent(BG_ATOM.intrastat_meld,    		new RB_cb()._t(S.ms("Intrastat melden"))._fsa(-2));
       this.registerComponent(BG_ATOM.transit_meld,    			new RB_cb()._t(S.ms("Transit melden"))._fsa(-2));
       this.registerComponent(BG_ATOM.eu_vertrag_check,    		new RB_cb()._t(S.ms("Prüfe EU-Vertrag"))._fsa(-2));

       this.registerComponent(new B2_InfoBlockFremdwaehrungMask(positionStation));
       
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
			String defaultVal = (enPositionStation==EnPositionStation.LEFT?EnTabKeyInMask.A1.dbVal():EnTabKeyInMask.A2.dbVal());
			preSettingsContainer.rb_get(BG_ATOM.pos_in_mask).set_rb_Default(defaultVal);
		}
		
		preSettingsContainer.rb_get(BG_ATOM.e_preis_fremdwaehrung).set_Enabled(false);
		
        return null;
     }
	
	
	@Override
	public String getPositionCode() {
		return this.enumTab.dbVal();
	}
 
	
}
 
 
