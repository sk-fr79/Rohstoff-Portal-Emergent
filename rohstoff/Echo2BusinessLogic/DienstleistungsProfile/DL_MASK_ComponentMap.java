 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
   
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Adresse;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Artbez;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Artikel;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelFieldV2ArtikelBez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.IF_Simple_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.DienstleistungsProfile.DL_CONST.TP_MAP_ADDONS;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
  
public class DL_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public DL_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;
       
       RB_SelFieldV2ArtikelBez	artBez = 	null;
       RB_SelFieldV2ArtikelBez 	artBezDl = null;
       
     
       if (m_tpHashMap.getFromExtender(TP_MAP_ADDONS.FIELD_SELECT_ARTIKEL)!=null) {
    	   artBez = (RB_SelFieldV2ArtikelBez)m_tpHashMap.getFromExtender(TP_MAP_ADDONS.FIELD_SELECT_ARTIKEL);
       } else {
    	   	artBez = 	new RB_SelFieldV2ArtikelBez();
    	   	artBez._initMenueMaps(null);
    	   	artBez._init();
    	   	artBez._render();
   			m_tpHashMap._setToExtender(TP_MAP_ADDONS.FIELD_SELECT_ARTIKEL,artBez);
       }
       

       if (m_tpHashMap.getFromExtender(TP_MAP_ADDONS.FIELD_SELECT_DIENSTLEISTUNGEN)!=null) {
    	   artBezDl = (RB_SelFieldV2ArtikelBez)m_tpHashMap.getFromExtender(TP_MAP_ADDONS.FIELD_SELECT_DIENSTLEISTUNGEN);
       } else {
//	       //dienstleistungsids raussuchen ....
			SEL sel = new SEL(ARTIKEL_BEZ.id_artikel_bez).FROM(_TAB.artikel_bez)
														.INNERJOIN(_TAB.artikel, ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel).WHERE(new vgl_YN(ARTIKEL.dienstleistung, true));
			VEK<Object[]> vals = bibDB.getResultLines(new SqlStringExtended(sel.s()),true);
			VEK<Long> ids = new VEK<>();
			
			for (Object[] o: vals) {
				ids._a( ((BigDecimal)o[0]).longValueExact());
			}
			
			//Debug.println("anzahl artikel  ", ""+ids.size()+" code:  c089d5ee-d607-11e9-bb65-2a2ae2dbcce4 ");
			
			artBezDl = 	new RB_SelFieldV2ArtikelBez();
			artBezDl._initMenueMaps(ids)._init()._render();

			m_tpHashMap._setToExtender(TP_MAP_ADDONS.FIELD_SELECT_DIENSTLEISTUNGEN,artBezDl);
       }

       this.registerComponent(new RB_KF(DLP_PROFIL.aktiv),    					new RB_cb()._width(100));
	   this.registerComponent(new RB_KF(DLP_PROFIL.anteil_menge),    			new RB_TextField()._width(100));
	   this.registerComponent(new RB_KF(DLP_PROFIL.beschreibung),    			new RB_TextField()._width(300));
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_adresse_start),    		new SearchAdress());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_adresse_ziel),    		new SearchAdress());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_adresse_fremdware),    	new SearchAdressBesitzerFremd());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_adresse_rechnung),    	new SearchAdressBesitzerFremd());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_adresse_buchungslager),  	new OwnSearchBuchungsLager());
	   
	   
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_artikel),  	  			new OwnSearchSorte());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_artikel_bez_quelle), 		new OwnSearchArtikelBezQuelleZiel());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_artikel_bez_ziel), 		new OwnSearchArtikelBezQuelleZiel());
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_artikel_bez_dl),  	  	new OwnSearchDienstleistungsArtikelBez());
	   
	   
	   this.registerComponent(new RB_KF(DLP_PROFIL.id_dlp_profil),   			new RB_lab());
	   this.registerComponent(new RB_KF(DLP_PROFIL.typ_mengen_calc),    		new RB_selField()._populate(DL_ENUM_TYP_MENGEN_CALC.PRO_LADUNG,false)._width(300));
	   this.registerComponent(new RB_KF(DLP_PROFIL.anwenden_auf_typ),    		new RB_selField()._populate(DL_ENUM_ANWENDEN_AUF.ALLE,false)._width(300));
	   this.registerComponent(new RB_KF(DLP_PROFIL.umrech_mge_quelle_ziel),    	new RB_TextField()._width(100));


	   this.registerComponent(new RB_KF(DLP_PROFIL.gilt_ab_datum),    			new RB_date_selektor2(100,true));
	   
        

		//validieren, dass ein anteil zwischen 0 und 1 liegt
		this.registerSetterValidators(DLP_PROFIL.anteil_menge, new IF_Simple_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector executeSetterValidator(RB_ComponentMap rbMASK, VALID_TYPE ActionType) throws myException {
				RB_MaskController mc = new RB_MaskController(rbMASK);
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					BigDecimal bdAnteil = mc.get_MyBigDecimal_liveVal(DLP_PROFIL.anteil_menge).get_bdWert();
					if (bdAnteil!=null && (bdAnteil.compareTo(BigDecimal.ONE)>0 || bdAnteil.compareTo(BigDecimal.ZERO)<0 )) {
						mv._addAlarm(S.ms("Ein Anteil muss zwischen 0 und 1 liegen!"));
					}
				}
				return mv;
			}
		});

		
		//bei typ "pro ladung" anteil und faktor= 1 und sperren
		this.registerSetterValidators(DLP_PROFIL.typ_mengen_calc, new IF_Simple_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector executeSetterValidator(RB_ComponentMap rbMASK, VALID_TYPE ActionType) throws myException {
				RB_MaskController mc = new RB_MaskController(rbMASK);
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				if ((ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION && mc.getMaskStatus().isStatusCanBeSaved())||(ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION)) {
					//standard setzen
					mc._setEnabledForEdit(DL_CONST.getLeadingMaskKey(), DLP_PROFIL.anteil_menge, true);
					mc._setEnabledForEdit(DL_CONST.getLeadingMaskKey(), DLP_PROFIL.umrech_mge_quelle_ziel, true);

					String typ  = S.NN(mc.get_liveVal(DLP_PROFIL.typ_mengen_calc));
					if (typ.equals(DL_ENUM_TYP_MENGEN_CALC.PRO_LADUNG.name())) {
						
						if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
							mc.set_maskVal(DLP_PROFIL.anteil_menge, "1",mv);
							mc.set_maskVal(DLP_PROFIL.umrech_mge_quelle_ziel, "1",mv);
						}
						
						mc._setEnabledForEdit(DL_CONST.getLeadingMaskKey(), DLP_PROFIL.anteil_menge, false);
						mc._setEnabledForEdit(DL_CONST.getLeadingMaskKey(), DLP_PROFIL.umrech_mge_quelle_ziel, false);
						
					}
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

		preSettingsContainer.rb_set_defaultMaskValue(DLP_PROFIL.aktiv, "Y");
		preSettingsContainer.rb_set_defaultMaskValue(DLP_PROFIL.anteil_menge, "1,00");
		preSettingsContainer.rb_set_defaultMaskValue(DLP_PROFIL.umrech_mge_quelle_ziel, "1000,00");
		preSettingsContainer.rb_set_defaultMaskValue(DLP_PROFIL.typ_mengen_calc, DL_ENUM_TYP_MENGEN_CALC.PRO_MENGE.db_val());
		preSettingsContainer.rb_set_defaultMaskValue(DLP_PROFIL.gilt_ab_datum,   new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
		
        return null;
     }
	
	
	private class SearchAdress extends RB_HL_Search_V2_Adresse {

		public SearchAdress() throws myException {
			super();
			this._setFindOwnAdresseWhenEmpytSearchField();
			this._registerAfterFieldEraseEvents(()-> {
				try {
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_adresse_fremdware,null,null);
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_adresse_rechnung,null,null);
				} catch (myException e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(e.get_ErrorMessage());
				}
				
			});
			
			
			this._registerAfterValueChangeEvents(()-> {
				try {
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_adresse_fremdware,null,null);
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_adresse_rechnung,null,null);
				} catch (myException e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(e.get_ErrorMessage());
				}
			});

			
			
		}

		
		
		
	}
	
	
	
	private class SearchAdressBesitzerFremd extends RB_HL_Search_V2_Adresse {

		public SearchAdressBesitzerFremd() throws myException {
			super();
			
			this._setSearchIconAutomatikSearch();
			
			this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Wird der Button mit leerem Suchfeld gedrückt, dann versucht das System die Fremdadresse aus Lade- und Ablade-Station zu finden !"));
			
			this.getButtonStartSearch()._addValidator(()-> {
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				Long idAdresseStart = getLongLiveVal(DLP_PROFIL.id_adresse_start);
				Long idAdresseZiel  = getLongLiveVal(DLP_PROFIL.id_adresse_ziel);

				if (O.isOneNull(idAdresseStart,idAdresseZiel)) {
					bibMSG.MV()._addAlarm(S.ms("Bitte zuerst die Start- und ZielAdresse wählen !"));
				}
				
				return mv;
			});
			
			this._registerBeforeStartSearchEvent(()-> {
				
				
				//falls eine der beiden adressen fremd ist, diese vorschlagen fuer die fremdadresse
				try {
					Long idAdresseStart = getLongLiveVal(DLP_PROFIL.id_adresse_start);
					Long idAdresseZiel  = getLongLiveVal(DLP_PROFIL.id_adresse_ziel);
					
					if (idAdresseStart!=null && idAdresseZiel!=null && S.isEmpty(getTextFieldSearchInput().getText())) {
						Rec21_adresse start = new Rec21_adresse()._fill_id(idAdresseStart)._getMainAdresse();
						Rec21_adresse ziel = new Rec21_adresse()._fill_id(idAdresseZiel)._getMainAdresse();

						Long idAdresseMandant = Long.parseLong(bibALL.get_ID_ADRESS_MANDANT());

						_setAllowEmptySearchfield(false);

						if (start.getId()==idAdresseMandant && ziel.getId()!=idAdresseMandant) {
							getAndStatementCollectorOneTimeCondition().add(new vgl(ADRESSE.id_adresse, ziel.getIdLong().toString()));
							_setAllowEmptySearchfield(true);
						} else if (start.getId()!=idAdresseMandant && ziel.getId()==idAdresseMandant) {
							getAndStatementCollectorOneTimeCondition().add(new vgl(ADRESSE.id_adresse, start.getIdLong().toString()));
							_setAllowEmptySearchfield(true);
						} 
 							
						
						
					}
				} catch (myException e) {
					e.printStackTrace();
				}
			});
		}
	}
	
	

	private class OwnSearchBuchungsLager extends RB_HL_Search_V2_Adresse {
		public OwnSearchBuchungsLager() throws myException {
			super();
			this._setFindOnlyMandantenAdresses();
		}
	}
	
	

	private class OwnSearchSorte extends RB_HL_Search_V2_Artikel {

		public OwnSearchSorte() throws myException {
			super();
			
			this._setSearchIconAutomatikSearch();
			
			this._registerAfterFieldEraseEvents(()-> {
				try {
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_artikel_bez_quelle,null,null);
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_artikel_bez_ziel,null,null);
				} catch (myException e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(e.get_ErrorMessage());
				}
				
			});
			
			
			this._registerAfterValueChangeEvents(()-> {
				try {
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_artikel_bez_quelle,null,null);
					_setClearWhenNotEmpty(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.id_artikel_bez_ziel,null,null);
				} catch (myException e) {
					e.printStackTrace();
					bibMSG.add_MESSAGE(e.get_ErrorMessage());
				}
			});

		}
		
		public OwnSearchSorte _setOwnShape() {
			RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
			//this.getAddOnComponents()._put("EDIT_ADRESSE", editAdresse);
			this._clear()
				._a(getTextFieldSearchInput(), gl)
				._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
				._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
				._a("", gl._c()._ins(0, 2, 2, 0))
				._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
				._setSize(100,20,20,20,400);

			return this;
		}

	}
	
	
	
	private class OwnSearchArtikelBezQuelleZiel extends RB_HL_Search_V2_Artbez {

		public OwnSearchArtikelBezQuelleZiel() throws myException {
			super();
			
			this._setSearchIconAutomatikSearch();
			this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Bei leerem Suchfeld werden die Artikelbezeichner der Hauptsorte angezeigt !"));

			this.getButtonStartSearch()._addValidator(()-> {
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				if (getLongLiveVal(DLP_PROFIL.id_artikel)==null) {
					mv._addAlarm(S.ms("Bitte zuerst eine Sorte auswählen !"));
				}
				
				return mv;
			});
			

			this._registerBeforeStartSearchEvent(()-> {
				try {
					_setAllowEmptySearchfield(false);
					if (S.isEmpty(getTextFieldSearchInput().getText())) {
						Long idArtikel = getLongLiveVal(DLP_PROFIL.id_artikel);
						if (idArtikel!=null) {
							getAndStatementCollectorOneTimeCondition().add(new vgl(ARTIKEL_BEZ.id_artikel, idArtikel.toString()));
							_setAllowEmptySearchfield(true);
						}
					}
				} catch (myException e) {
					e.printStackTrace();
					bibMSG.MV()._addAlarm(S.ms("Bitte zuerst eine Sorte auswählen !"));
				}
			});
			
		}
		
		public OwnSearchArtikelBezQuelleZiel _setOwnShape() {
			RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
			//this.getAddOnComponents()._put("EDIT_ADRESSE", editAdresse);
			this._clear()
				._a(getTextFieldSearchInput(), gl)
				._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
				._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
				._a("", gl._c()._ins(0, 2, 2, 0))
				._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
				._setSize(100,20,20,20,400);

			return this;
		}

	}
	

	
	private class OwnSearchDienstleistungsArtikelBez extends RB_HL_Search_V2_Artbez {

		public OwnSearchDienstleistungsArtikelBez() throws myException {
			super();
			
			_setAllowEmptySearchfield(true);
			_setSearchIconAutomatikSearch();
			
			this._registerBeforeStartSearchEvent(()-> {
				if (S.isEmpty(getTextFieldSearchInput().getText())) {
					try {
						getAndStatementCollectorOneTimeCondition().and(new vgl_YN(ARTIKEL.dienstleistung,true));
					} catch (myException e) {
						getAndStatementCollectorOneTimeCondition().and(new TermSimple("1=2"));
						e.printStackTrace();
					}
				}
			});
			
		}
		public OwnSearchDienstleistungsArtikelBez _setOwnShape() {
			RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
			//this.getAddOnComponents()._put("EDIT_ADRESSE", editAdresse);
			this._clear()
				._a(getTextFieldSearchInput(), gl)
				._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
				._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
				._a("", gl._c()._ins(0, 2, 2, 0))
				._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
				._setSize(100,20,20,20,400);

			return this;
		}
	}
	
	
	
	
	
	
	
}
 
 
