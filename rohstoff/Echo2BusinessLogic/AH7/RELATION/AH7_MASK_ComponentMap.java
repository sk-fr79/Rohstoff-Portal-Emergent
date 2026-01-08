package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.AH7_MLabelStatus;
import rohstoff.Echo2BusinessLogic.AH7.AH7_MLabelStatusFieldKey;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceCheckAH7ProfileIsFittingToAH7Steuerrelation;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceCompareAH7ProfileWithAdressesInMask;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceShowProfileMask;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Profil;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;


public class AH7_MASK_ComponentMap extends RB_ComponentMap {
    public AH7_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_ah7_profil),    				new AH7_Mask_selFieldProfil()._width(600));
        
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_adresse_geo_start),   	new AH7_Mask_SearchStation());
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_adresse_jur_start),    	new AH7_Mask_SearchMainAdress());
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_adresse_geo_ziel),    	new AH7_Mask_SearchStation());
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_adresse_jur_ziel),    	new AH7_Mask_SearchMainAdress());

        this.registerComponent(new RB_KF(AH7_STEUERDATEI.drucke_blatt2),   		 	new RB_cb()._width(20));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.drucke_blatt3),    		new RB_cb()._width(20));
        
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_1_abfallerzeuger),    	new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_1_import_empfaenger),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_1_verbr_veranlasser),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_1_verwertungsanlage),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_2_abfallerzeuger),    	new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_2_import_empfaenger),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_2_verbr_veranlasser),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_2_verwertungsanlage),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_3_abfallerzeuger),    	new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_3_import_empfaenger),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_3_verbr_veranlasser),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_3_verwertungsanlage),   new AH7_Mask_selField_adress()._width(600));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.id_ah7_steuerdatei),    	new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.locked),    				new RB_cb()._width(20));
        this.registerComponent(new RB_KF(AH7_STEUERDATEI.status_relation),    		new AH7_Mask_selFieldStatus()._width(100));
        //ampel anzeigen
        this.registerComponent(new AH7_MLabelStatusFieldKey(_TAB.ah7_steuerdatei), 	new AH7_MLabelStatus());

        //maskenbutton zur anzeige der aktiven profilmaske
        this.registerComponent(new RB_KF(AH7__CONST.AH7_B_BUTTONS.MASK_BUTTON_ZEIGE_PROFIL.db_val()), 	new BtShowProfileMask());
        
        this.registerSetterValidators(AH7_STEUERDATEI.id_adresse_geo_start.fk(), 	new setterFillAllAdressDropDowns());
        this.registerSetterValidators(AH7_STEUERDATEI.id_adresse_geo_ziel.fk(), 	new setterFillAllAdressDropDowns());
        
        this.registerSetterValidators(AH7_STEUERDATEI.drucke_blatt2.fk(), 			new setterActiveInactiveAH7_Block());
        this.registerSetterValidators(AH7_STEUERDATEI.drucke_blatt3.fk(), 			new setterActiveInactiveAH7_Block());
        this.registerSetterValidators(AH7_STEUERDATEI.locked.fk(), 					new setterAfterMaskLock());
        
        this.registerSetterValidators(AH7_STEUERDATEI.id_ah7_steuerdatei.fk(), 		new setterCheckCompleteBlocks());
        
        this.registerSetterValidators(AH7_STEUERDATEI.status_relation.fk(), 		new ownValidatorOnlyGFCanSetActive());
        this.registerSetterValidators(AH7_STEUERDATEI.status_relation.fk(), 		new ownValidatorCheckFilledFields());
        this.registerSetterValidators(AH7_STEUERDATEI.status_relation.fk(), 		new ownValidatorAmpelschaltung());
        
        this.registerSetterValidators(AH7_STEUERDATEI.id_adresse_geo_start.fk(), 	new ownValidatorCheckAllIdsAllowed());
                
        this.registerSetterValidators(AH7_STEUERDATEI.id_ah7_profil.fk(), 			new ownValidatorAmpelschaltung());
        this.registerSetterValidators(AH7_STEUERDATEI.id_ah7_steuerdatei.fk(), 		new ownValidatorCheckRelationAgainstMask());
        

        this._setWidth(600,AH7_STEUERDATEI.status_relation);
        
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
		return null;
	}
	
	
	/*
	 * dafuer sorgen, dass beim öffnen der maske automatisch der schalter LOCKED gesetzt ist
	 */
	private class setterAfterMaskLock  extends RB_Mask_Set_And_Valid {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector 	mv = 	new MyE2_MessageVector();

			if (ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				// new RB_MaskController(rbMASK).set_maskVal(AH7_STEUERDATEI.locked, "Y", mv);
			}
			
			return mv;
		} 
		
	}
	
	
	private class setterFillAllAdressDropDowns extends RB_Mask_Set_And_Valid {
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
			
			MyE2_MessageVector 	mv = 	new MyE2_MessageVector();

			if (ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				new AH7__ActualizeDropDownsInMask(rbMASK, mv,true);
			}
			
			return mv;
		}
	}

	
	private class setterActiveInactiveAH7_Block extends RB_Mask_Set_And_Valid {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,		ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION || ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				RB_MaskController cont = new RB_MaskController(rbMASK);
				
				boolean bAnh7AbladeNoetig = cont.isYes_LiveVal(AH7_STEUERDATEI.drucke_blatt2);

				if (!bAnh7AbladeNoetig) {
					cont.set_maskVal(AH7_STEUERDATEI.id_2_abfallerzeuger,"",mv);
					cont.set_maskVal(AH7_STEUERDATEI.id_2_import_empfaenger,"",mv);
					cont.set_maskVal(AH7_STEUERDATEI.id_2_verbr_veranlasser,"",mv);
					cont.set_maskVal(AH7_STEUERDATEI.id_2_verwertungsanlage,"",mv);
				}
				cont.get_comp(AH7_STEUERDATEI.id_2_abfallerzeuger, mv).set_bEnabled_For_Edit(bAnh7AbladeNoetig);
				cont.get_comp(AH7_STEUERDATEI.id_2_import_empfaenger, mv).set_bEnabled_For_Edit(bAnh7AbladeNoetig);
				cont.get_comp(AH7_STEUERDATEI.id_2_verbr_veranlasser, mv).set_bEnabled_For_Edit(bAnh7AbladeNoetig);
				cont.get_comp(AH7_STEUERDATEI.id_2_verwertungsanlage, mv).set_bEnabled_For_Edit(bAnh7AbladeNoetig);
				
				
				boolean bAnh7LadeNoetig = cont.isYes_LiveVal(AH7_STEUERDATEI.drucke_blatt3);
				if (!bAnh7LadeNoetig) {
					cont.set_maskVal(AH7_STEUERDATEI.id_3_abfallerzeuger,"",mv);
					cont.set_maskVal(AH7_STEUERDATEI.id_3_import_empfaenger,"",mv);
					cont.set_maskVal(AH7_STEUERDATEI.id_3_verbr_veranlasser,"",mv);
					cont.set_maskVal(AH7_STEUERDATEI.id_3_verwertungsanlage,"",mv);
				}
				cont.get_comp(AH7_STEUERDATEI.id_3_abfallerzeuger, mv).set_bEnabled_For_Edit(bAnh7LadeNoetig);
				cont.get_comp(AH7_STEUERDATEI.id_3_import_empfaenger, mv).set_bEnabled_For_Edit(bAnh7LadeNoetig);
				cont.get_comp(AH7_STEUERDATEI.id_3_verbr_veranlasser, mv).set_bEnabled_For_Edit(bAnh7LadeNoetig);
				cont.get_comp(AH7_STEUERDATEI.id_3_verwertungsanlage, mv).set_bEnabled_For_Edit(bAnh7LadeNoetig);
			
			}
			return mv;
			
		}
	}
	
	
	private class setterCheckCompleteBlocks extends RB_Mask_Set_And_Valid {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,		ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			IF_Field[] fieldBlock1 = {
					AH7_STEUERDATEI.id_1_verbr_veranlasser
					,AH7_STEUERDATEI.id_1_import_empfaenger
					,AH7_STEUERDATEI.id_1_abfallerzeuger
					,AH7_STEUERDATEI.id_1_verwertungsanlage
			};

			IF_Field[] fieldBlock2 = {
					AH7_STEUERDATEI.id_2_verbr_veranlasser
					,AH7_STEUERDATEI.id_2_import_empfaenger
					,AH7_STEUERDATEI.id_2_abfallerzeuger
					,AH7_STEUERDATEI.id_2_verwertungsanlage
			};

			IF_Field[] fieldBlock3 = {
					AH7_STEUERDATEI.id_3_verbr_veranlasser
					,AH7_STEUERDATEI.id_3_import_empfaenger
					,AH7_STEUERDATEI.id_3_abfallerzeuger
					,AH7_STEUERDATEI.id_3_verwertungsanlage
			};

			
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION ) {
				RB_MaskController cont = new RB_MaskController(rbMASK);
				
				int count1 = this.countOfNotEmpty(fieldBlock1, cont);
				int count2 = this.countOfNotEmpty(fieldBlock2, cont);
				int count3 = this.countOfNotEmpty(fieldBlock3, cont);
				
				boolean b1_Empty = 	(count1==0);
				boolean b1_Ok = 	(count1==4);
				
				boolean b2_Ok = (count2==0 || count2==4);
				boolean b3_Ok = (count3==0 || count3==4);

				if (b1_Empty) {
//					cont.set_maskVal(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv);
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte Block 1 komplett ausfüllen !")));
				}
				
				if (!b1_Empty && !b1_Ok) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte Block 1 komplett ausfüllen !")));
				}
				if (!b2_Ok) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte Block 2 (Abladestelle) komplett ausfüllen oder leer lassen!")));
				} else {
					if (count2==4) {
						cont.set_maskVal(AH7_STEUERDATEI.drucke_blatt2, "Y", mv);
					} else if (count2==0) {
						cont.set_maskVal(AH7_STEUERDATEI.drucke_blatt2, "N", mv);
					}
				}
				if (!b3_Ok) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte Block 3 (Ladestelle) komplett ausfüllen oder leer lassen!")));
				} else {
					if (count3==4) {
						cont.set_maskVal(AH7_STEUERDATEI.drucke_blatt3, "Y", mv);
					} else if (count3==0) {
						cont.set_maskVal(AH7_STEUERDATEI.drucke_blatt3, "N", mv);
					}
				}
				
			}
			return mv;
			
		}
		
		private int countOfNotEmpty(IF_Field[] block1Field, RB_MaskController cont) throws myException {
			int i=0;
			
			for (IF_Field f: block1Field) {
				if (S.isFull(cont.getStringMaskVal(f))) {
					i++;
				}
			}
			
			return i;
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
				AH7_MLabelStatus ampel = (AH7_MLabelStatus)c.get_comp(_TAB.ah7_steuerdatei.rb_km(), new AH7_MLabelStatusFieldKey(_TAB.ah7_steuerdatei), mv);

				//standard ist immer rot
				ampel._setStatus(AH7__ENUM_STATUSRELATION.UNDEF);

				String status = c.get_maskVal(AH7_STEUERDATEI.status_relation);
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


	
	
	
	private class ownValidatorOnlyGFCanSetActive extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION || ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
				if (ENUM_MANDANT_DECISION.AH7_STEUERTABELLE_AKTIVIERUNG_NUR_GF.is_YES()) {
					if (!bibALL.is_geschaeftsfuehrer()) {
						
						RB_MaskController  c = new RB_MaskController(rbMASK);
						
						String cOLD = S.NN(c.getStringDbVal(AH7_STEUERDATEI.status_relation));
						String cNEW = S.NN(c.getStringMaskVal(AH7_STEUERDATEI.status_relation));
						
						if (cNEW.equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val())) {
							mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nur ein Geschäftsführer darf Statusrelationen auf AKTIV setzen / AKTIVE Relationen speichern!!")));
							
							//falls klick, wieder resetten
							if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
								c.set_maskVal(AH7_STEUERDATEI.status_relation, cOLD, mv);
							}
							
						}
					}
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
				
				String selectedStatus = S.NN(c.getStringMaskVal(AH7_STEUERDATEI.status_relation));
				
				if (selectedStatus.equals(AH7__ENUM_STATUSRELATION.INACTIVE.db_val()) || selectedStatus.equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val() )) {
					// dann muss mindstens ein block gefuellt sein
					int iAnzahl = c.getCountNotEmptyInActualMask(AH7_STEUERDATEI.id_1_abfallerzeuger
																 ,AH7_STEUERDATEI.id_1_import_empfaenger
																 ,AH7_STEUERDATEI.id_1_verbr_veranlasser
																 ,AH7_STEUERDATEI.id_1_verwertungsanlage
																 );
					if (iAnzahl!=4) {
						mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Bitte zuerst den AH7-Block: Kontrollblatt ausfüllen !")));
						c.set_maskVal(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv);
					}
				}
			}
			
			return mv;
		}
		
	}

	
	//pruefung, ob alle ids, die geladen wurden, auch (noch) moeglich sind. es koennte z.b. sein, dass alle bei einem lager die lager-besitzer-id wieder entfernt wurde
	private class ownValidatorCheckAllIdsAllowed  extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION || ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION ) {
			
				boolean warning = (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION);
				
				RB_MaskController con = new RB_MaskController(rbMASK);
				
				if (rbMASK.getRbDataObjectActual().isStatusNew()) {
					return mv;
				}
				Rec20 rAH7 = rbMASK.getRbDataObjectActual().rec20();
				
				//zuerst die id_adressen_geo beschaffen
				MyLong l_start_geo = con.get_MyLong_liveVal(AH7_STEUERDATEI.id_adresse_geo_start);
				MyLong l_ziel_geo = con.get_MyLong_liveVal(AH7_STEUERDATEI.id_adresse_geo_ziel);
	
				if (O.isOneNull(l_start_geo,l_ziel_geo) || l_start_geo.isNotOK() || l_ziel_geo.isNotOK()) {
					mv._addAlarm("Fehler in der Adress-Struktur des Vorgangs: Start- und Ziel nicht gefunden !");
				} else {
					AH7__HmPossibleAdresses hmPossible = new AH7__HmPossibleAdresses(l_start_geo.get_oLong(), l_ziel_geo.get_oLong());
					VEK<Long> vPossible = hmPossible.getVectorAllIds();
					
					VEK<IF_Field>    vAdressFields = new VEK<IF_Field>();
					vAdressFields._a(AH7_STEUERDATEI.id_1_abfallerzeuger);
					vAdressFields._a(AH7_STEUERDATEI.id_1_import_empfaenger);
					vAdressFields._a(AH7_STEUERDATEI.id_1_verbr_veranlasser);
					vAdressFields._a(AH7_STEUERDATEI.id_1_verwertungsanlage);
					vAdressFields._a(AH7_STEUERDATEI.id_2_abfallerzeuger);
					vAdressFields._a(AH7_STEUERDATEI.id_2_import_empfaenger);
					vAdressFields._a(AH7_STEUERDATEI.id_2_verbr_veranlasser);
					vAdressFields._a(AH7_STEUERDATEI.id_2_verwertungsanlage);
					vAdressFields._a(AH7_STEUERDATEI.id_3_abfallerzeuger);
					vAdressFields._a(AH7_STEUERDATEI.id_3_import_empfaenger);
					vAdressFields._a(AH7_STEUERDATEI.id_3_verbr_veranlasser);
					vAdressFields._a(AH7_STEUERDATEI.id_3_verwertungsanlage);
	
					for (IF_Field f: vAdressFields) {
						if (con.get_MyLong_liveVal(f)!=null) {
							if (!vPossible.contains(con.get_MyLong_liveVal(f).get_oLong())) {
								Rec21_adresse rec_fehler = new Rec21_adresse()._fill_id(rAH7.get_raw_resultValue_Long(f));
								
								if (warning) {
									mv._addWarn(S.ms("Folgende Adresse darf in dieser Relation nicht verwendet werden :").ut(rec_fehler.get__FullNameAndAdress_flexible(", ")));
								} else {
									mv._addAlarm(S.ms("Folgende Adresse darf in dieser Relation nicht verwendet werden :").ut(rec_fehler.get__FullNameAndAdress_flexible(", ")));
								}
							}
						}
					}
				}
			}
			
			return mv;
		}
	}
	
	
	
	private class BtShowProfileMask extends E2_Button {

		/**
		 * @throws myException 
		 * 
		 */
		public BtShowProfileMask() throws myException {
			super();
			this.__setImages(E2_ResourceIcon.get_RI("suche.png"), E2_ResourceIcon.get_RI("suche__.png"));
			this._style(E2_Button.StyleImageButton());
			this._ttt(S.ms("Zeige das Profil an !"));
			this._aaa(()-> {
				RB_MaskController  con = new RB_MaskController(this);
				String id_ah7_profile = con.get_liveVal(AH7_STEUERDATEI.id_ah7_profil);
				if (S.isFull(id_ah7_profile)) {
					new _PdServiceShowProfileMask().show(id_ah7_profile);
				} else {
					bibMSG.MV()._addAlarm("Das zugehörige AH7-Profil konnte nicht gefunden werden !");
				}
			});
		}
		
	}
	
	
	
	
	private class ownValidatorCheckRelationAgainstMask extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				
				RB_MaskController c = new RB_MaskController(AH7_MASK_ComponentMap.this);
				Long idAH7Profil = 			c.getLongLiveVal(AH7_STEUERDATEI.id_ah7_profil);
				
				if (idAH7Profil!=null) {
					
					Long idAdresseLagerStart = 	c.getLongLiveVal(AH7_STEUERDATEI.id_adresse_geo_start);
					Long idAdresseLagerZiel = 	c.getLongLiveVal(AH7_STEUERDATEI.id_adresse_geo_ziel);
					if (O.isOneNull(idAdresseLagerStart,idAdresseLagerZiel) ) {
						mv._addAlarm(S.ms("Fehler bei der Konsistenzpüfung, einer der Werte Start-/Zieladresse oder AH7-Profil kann nicht gelesen werden!"));
					} else {			
						boolean isProfilAndMaskMatching = new _PdServiceCheckAH7ProfileIsFittingToAH7Steuerrelation().isFitting(
																					new Rec21_AH7_Profil()._fill_id(idAH7Profil), idAdresseLagerStart, idAdresseLagerZiel) ;
						
						boolean isMaskAdressesFittingToProfile = new _PdServiceCompareAH7ProfileWithAdressesInMask().isMatching(
																					idAdresseLagerStart
																					,idAdresseLagerZiel
																					,idAH7Profil
																					,mv
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_1_verbr_veranlasser)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_1_import_empfaenger)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_1_abfallerzeuger)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_1_verwertungsanlage)
		
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_2_verbr_veranlasser)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_2_import_empfaenger)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_2_abfallerzeuger)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_2_verwertungsanlage)
																					
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_3_verbr_veranlasser)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_3_import_empfaenger)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_3_abfallerzeuger)
																					,c.getLongLiveVal(AH7_STEUERDATEI.id_3_verwertungsanlage)
																					);
					
						if (!isProfilAndMaskMatching ) {
							mv._addAlarm(S.ms("Die Maskenprüfung hat ergeben, daß die Relationen der Adressen (Quelle->Ziel) und das gewählte Profil "
									+ " nicht übereinstimmen. Falls Ihre Überprüfung ergibt, daß die alles korrekt ist, dann setzen Sie das Feld <zugeordnetes Profil>"
									+ " auf leer. Danach können Sie die Maske speichern !"));
						}
						if (!isMaskAdressesFittingToProfile) {
							mv._addAlarm(S.ms("Die Maskenprüfung hat ergeben, daß die Adressen in den Blöcken 1-3 und das Profil nicht übereinstimmen."
									+ " Evtl. haben Sie eine oder mehrere Adressen manuell verändert.  Falls Ihre Überprüfung ergibt, daß die alles korrekt ist, dann setzen Sie das Feld <zugeordnetes Profil>" 
									+ " auf leer. Danach können Sie die Maske speichern !"));
						}

					
					}
				}
			}
			return mv;
		}
		
	}

}
 
