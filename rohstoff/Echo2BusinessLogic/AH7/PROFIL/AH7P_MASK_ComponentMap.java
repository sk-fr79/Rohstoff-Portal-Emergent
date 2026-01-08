package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.AH7_ENUM_SONDERPROFILE;
import rohstoff.Echo2BusinessLogic.AH7.AH7_MLabelStatus;
import rohstoff.Echo2BusinessLogic.AH7.AH7_MLabelStatusFieldKey;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_TEILNEHMER;

public class AH7P_MASK_ComponentMap extends RB_ComponentMap {
	
	private VEK<IF_Field> fieldsToCheck = new VEK<IF_Field>()
												._a(AH7_PROFIL.verbr_veranlasser_1)
												._a(AH7_PROFIL.import_empfaenger_1)
												._a(AH7_PROFIL.abfallerzeuger_1)
												._a(AH7_PROFIL.verwertungsanlage_1)
											
												._a(AH7_PROFIL.verbr_veranlasser_2)
												._a(AH7_PROFIL.import_empfaenger_2)
												._a(AH7_PROFIL.abfallerzeuger_2)
												._a(AH7_PROFIL.verwertungsanlage_2)
												
												._a(AH7_PROFIL.verbr_veranlasser_3)
												._a(AH7_PROFIL.import_empfaenger_3)
												._a(AH7_PROFIL.abfallerzeuger_3)
												._a(AH7_PROFIL.verwertungsanlage_3)
												;

	
	
    public AH7P_MASK_ComponentMap() throws myException {
        super();
        
        String[][] ddarr = AH7__ENUM_TEILNEHMER.get_ddArray(true);
        for (int i=0;i<ddarr.length;i++) {
        	if (ddarr[i][1].equals(AH7__ENUM_TEILNEHMER.ADRESSE_MANDANT.db_val())) {
        		ddarr[i][0]=bibALL.get_RECORD_MANDANT().get___KETTE(MANDANT.name1,MANDANT.name2);
        	}
        }
        
        this.registerComponent(AH7_PROFIL.id_ah7_profil,    	new RB_lab());
        this.registerComponent(AH7_PROFIL.bezeichnung,  	  	new RB_TextField()._width(600));
        this.registerComponent(AH7_PROFIL.status_relation,  	new AH7P_MComp_StatusRelation()._width(600));
        
        String cText="Sonderfälle für alle Relationen, die nicht eingeordnet werden können";
        this.registerComponent(AH7_PROFIL.profile4allothers, 	new AH7P_MComp_SonderProfil()._width(600)._ttt(S.ms(cText)));
        
        this.registerComponent(AH7_PROFIL.start_eigenes_lager, 	new AH7P_MComp_EigenLager(true)._width(170));
        this.registerComponent(AH7_PROFIL.start_inland,    		new AH7P_MComp_Inland()._width(100));
        this.registerComponent(AH7_PROFIL.ah7_quelle_sicher, 	new AH7P_MComp_Sicher(true)._width(100));
        this.registerComponent(AH7_PROFIL.startlager_fremdbesitz, new RB_cb()._al_center()._width(10));
        

        this.registerComponent(AH7_PROFIL.ziel_eigenes_lager,  	new AH7P_MComp_EigenLager(false)._width(170));
        this.registerComponent(AH7_PROFIL.ziel_inland,    		new AH7P_MComp_Inland()._width(100));
        this.registerComponent(AH7_PROFIL.ah7_ziel_sicher,    	new AH7P_MComp_Sicher(false)._width(100));
        this.registerComponent(AH7_PROFIL.ziellager_fremdbesitz,new RB_cb()._al_center()._width(10));

        this.registerComponent(AH7_PROFIL.verbr_veranlasser_1, 	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.import_empfaenger_1, 	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.abfallerzeuger_1,    	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.verwertungsanlage_1, 	new RB_selField()._width(290)._populate(ddarr));

        this.registerComponent(AH7_PROFIL.verbr_veranlasser_2, 	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.import_empfaenger_2, 	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.abfallerzeuger_2,    	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.verwertungsanlage_2, 	new RB_selField()._width(290)._populate(ddarr));

        this.registerComponent(AH7_PROFIL.verbr_veranlasser_3, 	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.import_empfaenger_3, 	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.abfallerzeuger_3,    	new RB_selField()._width(290)._populate(ddarr));
        this.registerComponent(AH7_PROFIL.verwertungsanlage_3, 	new RB_selField()._width(290)._populate(ddarr));

        this.registerComponent(new AH7_MLabelStatusFieldKey(_TAB.ah7_profil), new AH7_MLabelStatus());

        this.registerSetterValidators(AH7_PROFIL.id_ah7_profil.fk(), 		new ownValidatorBlockMustBeComplete());
        this.registerSetterValidators(AH7_PROFIL.profile4allothers.fk(), 	new ownValidatorSettingWegenSonderfall());
        this.registerSetterValidators(AH7_PROFIL.status_relation.fk(), 	new ownValidatorOnlyGFCanSetActive());
        this.registerSetterValidators(AH7_PROFIL.status_relation.fk(), 	new ownValidatorCheckFilledFields());
        this.registerSetterValidators(AH7_PROFIL.status_relation.fk(), 	new ownValidatorAmpelschaltung());
        
        OwnValidatorCheckFremdbesitzKorrelation ownValidatorCheckFremdbesitzKorrelation = new OwnValidatorCheckFremdbesitzKorrelation();
        this.registerSetterValidators(AH7_PROFIL.startlager_fremdbesitz.fk(), 	ownValidatorCheckFremdbesitzKorrelation);
        this.registerSetterValidators(AH7_PROFIL.ziellager_fremdbesitz.fk(), 	ownValidatorCheckFremdbesitzKorrelation);
        this.registerSetterValidators(AH7_PROFIL.profile4allothers.fk(), 		ownValidatorCheckFremdbesitzKorrelation);

        for (IF_Field f: fieldsToCheck) {
        	this.registerSetterValidators(f.fk(), 	ownValidatorCheckFremdbesitzKorrelation);
        }       
        
        
        this._setWidth(600,AH7_PROFIL.status_relation);

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
		preSettingsContainer.rb_set_defaultMaskValue(AH7_PROFIL.profile4allothers, AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val());
		return null;
	}
	
	
	private class ownValidatorBlockMustBeComplete extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
				RB_MaskController c = new RB_MaskController(rbMASK);
				
				VEK<String>  v_block2 = new VEK<>();
				VEK<String>  v_block3 = new VEK<>();
				
				v_block2._a(	c.get_liveVal(AH7_PROFIL.verbr_veranlasser_2),
								c.get_liveVal(AH7_PROFIL.import_empfaenger_2),
								c.get_liveVal(AH7_PROFIL.abfallerzeuger_2),
								c.get_liveVal(AH7_PROFIL.verwertungsanlage_2));
				
				v_block3._a(	c.get_liveVal(AH7_PROFIL.verbr_veranlasser_3),
								c.get_liveVal(AH7_PROFIL.import_empfaenger_3),
								c.get_liveVal(AH7_PROFIL.abfallerzeuger_3),
								c.get_liveVal(AH7_PROFIL.verwertungsanlage_3));
		
				int iCount2 = 0;
				for (String s: v_block2) {if (S.isEmpty(s) ) {iCount2++;};}
				int iCount3 = 0;
				for (String s: v_block3) {if (S.isEmpty(s) ) {iCount3++;};}
			
				if (iCount2!=0 && iCount2!=4) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Der Block <AH7 für Abladestelle> muss komplett gefüllt oder leer sein !")));
				}
				
				if (iCount3!=0 && iCount3!=4) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Der Block <AH7 für Ladestelle> muss komplett gefüllt oder leer sein !")));
				}
			}
			return mv;
		}
	}
	
	
	
	private class ownValidatorOnlyGFCanSetActive extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION || ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
				if (ENUM_MANDANT_DECISION.AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF.is_YES()) {
					if (!bibALL.is_geschaeftsfuehrer()) {
						
						RB_MaskController  c = new RB_MaskController(rbMASK);
						
						String cOLD = S.NN(c.getStringDbVal(AH7_PROFIL.status_relation));
						String cNEW = S.NN(c.getStringMaskVal(AH7_PROFIL.status_relation));
						
						if (cNEW.equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val())) {
							mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nur ein Geschäftsführer darf Statusrelationen auf AKTIV setzen / Aktive Relationen speichern!")));
							
							//falls klick, wieder resetten
							if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
								c.set_maskVal(AH7_PROFIL.status_relation, cOLD, mv);
							}
							
						}
					}
				}
			}
			
			return mv;
		}
		
	}
	
	
	/**
	 * stellt die ampel auf den richtigen zustand
	 * @author martin
	 *
	 */
	private class ownValidatorAmpelschaltung extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION || ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				RB_MaskController  c = new RB_MaskController(rbMASK);
				AH7_MLabelStatus ampel = (AH7_MLabelStatus)c.get_comp(_TAB.ah7_profil.rb_km(), new AH7_MLabelStatusFieldKey(_TAB.ah7_profil), mv);

				//standard ist immer rot
				ampel._setStatus(AH7__ENUM_STATUSRELATION.UNDEF);

				String status = c.get_maskVal(AH7_PROFIL.status_relation);
				if (S.isFull(status)) {
					AH7__ENUM_STATUSRELATION stat = AH7__ENUM_STATUSRELATION.getStatus(status);
					if (stat!=null) {
						ampel._setStatus(stat);
					}
				}
			}
			return mv;
		}
	}

	
	
	private class ownValidatorSettingWegenSonderfall extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			//AH7P_MASK_ComponentMap oThis = AH7P_MASK_ComponentMap.this;
			MyE2_MessageVector mv = new MyE2_MessageVector();
			AH7P_MASK_ComponentMap  map = AH7P_MASK_ComponentMap.this;
			RB_MaskController  c = new RB_MaskController(map);

			boolean bStatusEdit = map.rb_get_belongs_to().rb_Actual_DataobjectCollector().rb_hm_DataObjects().get(new AH7P_KEY()).isStatusCanBeSaved();
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				
				Rec20 rec = map.rb_get_belongs_to().rb_Actual_DataobjectCollector().get(_TAB.ah7_profil.rb_km()).rec20();
				if (rec.is_newRecordSet()) {
					c.set_maskVal(AH7_PROFIL.profile4allothers, AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val(), mv);   //damit im fall der kopien das auch funktioniert
					map.setFieldCompareBlockMandatory(true);
					map.setSurfaceSettingsActive();
				} else {
					if (rec.get_ufs_dbVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val())) {
						map.setFieldCompareBlockMandatory(bStatusEdit);
						map.setSurfaceSettingsActive();
						mv.add_MESSAGE(map.setFieldCompareBlockEnabled(bStatusEdit));
					} else {
						map.setFieldCompareBlockMandatory(false);
						map.setSurfaceSettingsActive();
						mv.add_MESSAGE(map.setFieldCompareBlockEnabled(false));
						mv.add_MESSAGE(map.setFieldCompareBlockNull());
					}
				}
			}
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
				
				if (c.get_liveVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val())) {
					map.setFieldCompareBlockMandatory(bStatusEdit);
					map.setSurfaceSettingsActive();
					mv.add_MESSAGE(map.setFieldCompareBlockEnabled(bStatusEdit));
				} else {
					map.setFieldCompareBlockMandatory(false);
					map.setSurfaceSettingsActive();
					mv.add_MESSAGE(map.setFieldCompareBlockEnabled(false));
					mv.add_MESSAGE(map.setFieldCompareBlockNull());
				}
			}
			
			return mv;
		}
	}
	
	
	
	private class ownValidatorCheckFilledFields extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
						
				RB_MaskController  c = new RB_MaskController(rbMASK);
				
				String selectedStatus = S.NN(c.getStringMaskVal(AH7_PROFIL.status_relation));
				
				if (selectedStatus.equals(AH7__ENUM_STATUSRELATION.INACTIVE.db_val()) || selectedStatus.equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val() )) {
					// dann muss mindstens ein block gefuellt sein
					int iAnzahl = c.getCountNotEmptyInActualMask(AH7_PROFIL.abfallerzeuger_1
																 ,AH7_PROFIL.import_empfaenger_1
																 ,AH7_PROFIL.verbr_veranlasser_1
																 ,AH7_PROFIL.verwertungsanlage_1
																 );
					if (iAnzahl!=4) {
						mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Bitte zuerst den AH7-Block: Kontrollblatt ausfüllen !")));
						c.set_maskVal(AH7_PROFIL.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv);
					}
				}
			}
			
			return mv;
		}
		
	}


	
	private class OwnValidatorCheckFremdbesitzKorrelation  extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION || ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {

		        RB_MaskController c = new RB_MaskController(rbMASK);
		        
		        //wie oft kommt der eintrag "fremdbesitzer" in den adresszuteilungen vor 
		        int iCountUseAdresseStartFremdbesitz = 0;
		        int iCountUseAdresseZielFremdbesitz = 0;
		        for (IF_Field f: fieldsToCheck) {
		        	if (c.get_liveVal(f).equals(AH7__ENUM_TEILNEHMER.ADRESSE_START_GEO_DRITTBESITZ.db_val())) {
		        		iCountUseAdresseStartFremdbesitz++;
		        	}
		        	if (c.get_liveVal(f).equals(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_GEO_DRITTBESITZ.db_val())) {
		        		iCountUseAdresseZielFremdbesitz++;
		        	}
		        }

		        //wenn es ein vorkommen gibt, dann muss der schalter gesetzt sein
		        if (iCountUseAdresseStartFremdbesitz>0 && !c.getYNMaskVal(AH7_PROFIL.startlager_fremdbesitz)) {
		        	mv._addAlarm(S.ms("Sie haben im Profil die Zuteilung ").ut(AH7__ENUM_TEILNEHMER.ADRESSE_START_GEO_DRITTBESITZ.user_text())
		        				.t(" verwendet ! Dies ist nur möglich mit dem Schalter: Quelle hat Fremdbesitzer!"));
		        }
		        
		        if (iCountUseAdresseZielFremdbesitz>0 && !c.getYNMaskVal(AH7_PROFIL.ziellager_fremdbesitz)) {
		        	mv._addAlarm(S.ms("Sie haben im Profil die Zuteilung ").ut(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_GEO_DRITTBESITZ.user_text())
		        			.t(" verwendet ! Dies ist nur möglich mit dem Schalter: Ziel hat Fremdbesitzer!"));
		        }
			}
			
			return mv;
		}
		
	}
	
	
	
	private void setFieldCompareBlockMandatory(boolean must) throws myException {
		this.getPreSettingsContainer().rb_get(AH7_PROFIL.start_eigenes_lager).set_MustField(must);
		this.getPreSettingsContainer().rb_get(AH7_PROFIL.start_inland).set_MustField(must);
		this.getPreSettingsContainer().rb_get(AH7_PROFIL.ah7_quelle_sicher).set_MustField(must);
		
		this.getPreSettingsContainer().rb_get(AH7_PROFIL.ziel_eigenes_lager).set_MustField(must);
		this.getPreSettingsContainer().rb_get(AH7_PROFIL.ziel_inland).set_MustField(must);
		this.getPreSettingsContainer().rb_get(AH7_PROFIL.ah7_ziel_sicher).set_MustField(must);
	}
	
	private MyE2_MessageVector setFieldCompareBlockEnabled(boolean enabled) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		RB_MaskController  c = new RB_MaskController(this);
		c.get_comp(AH7_PROFIL.start_eigenes_lager, mv).set_bEnabled_For_Edit(enabled);
		c.get_comp(AH7_PROFIL.start_inland, mv).set_bEnabled_For_Edit(enabled);
		c.get_comp(AH7_PROFIL.ah7_quelle_sicher, mv).set_bEnabled_For_Edit(enabled);
		c.get_comp(AH7_PROFIL.ziel_eigenes_lager, mv).set_bEnabled_For_Edit(enabled);
		c.get_comp(AH7_PROFIL.ziel_inland, mv).set_bEnabled_For_Edit(enabled);
		c.get_comp(AH7_PROFIL.ah7_ziel_sicher, mv).set_bEnabled_For_Edit(enabled);
		
		//wenn hier disable, dann wurde ein basisprofil gewaehlt, dann duerfen keine fremdbesitzer-lager vorhanden sein
		if (!enabled) {
			c.get_comp(AH7_PROFIL.startlager_fremdbesitz, mv).set_bEnabled_For_Edit(false);
			c.get_comp(AH7_PROFIL.ziellager_fremdbesitz, mv).set_bEnabled_For_Edit(false);
			((CheckBox)c.get_comp(AH7_PROFIL.startlager_fremdbesitz, mv)).setSelected(false);
			((CheckBox)c.get_comp(AH7_PROFIL.ziellager_fremdbesitz, mv)).setSelected(false);
		}
		
		
		return mv;
	}
	
	
	private MyE2_MessageVector setFieldCompareBlockNull() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		RB_MaskController  c = new RB_MaskController(this);
		c.set_maskVal(AH7_PROFIL.start_eigenes_lager,"",mv);
		c.set_maskVal(AH7_PROFIL.start_inland,"",mv);
		c.set_maskVal(AH7_PROFIL.ah7_quelle_sicher,"",mv);
		c.set_maskVal(AH7_PROFIL.ziel_eigenes_lager,"",mv);
		c.set_maskVal(AH7_PROFIL.ziel_inland,"",mv);
		c.set_maskVal(AH7_PROFIL.ah7_ziel_sicher,"",mv);
		
		return mv;
	}

}
 
