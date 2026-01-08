package rohstoff.Echo2BusinessLogic.SPIELWIESE.mask;

import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;


/**
 * 
 * @author nils
 *
 */
public class SPIELWIESE_MASK  {

		MyE2_Grid  	grid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());; 

		public SPIELWIESE_MASK(String id) throws myException
		{
			/*super();
			this.INIT_4_DB(_DB.FIBU_KONTENREGEL_NEU);
			//this.get_MetaFieldDef(_DB.EMAIL_SEND$SENDER_ADRESS).set_bIsNullableBasic(false);


			//this.register_Component(new RB_BT_SaveMask(this));

			this.register_Component(new RB_TextField(this, _DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU));
			this.register_Component(new RB_TextArea(this, 	_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR, 200, 5));

			/*this.register_Component(new RB_TextField(this, 	_DB.EMAIL_SEND$ID_EMAIL_SEND));
			this.register_Component(new RB_TextField(this, 	_DB.EMAIL_SEND$BETREFF,true,200));
			this.register_Component(new RB_TextArea(this, 	_DB.EMAIL_SEND$TEXT,200,5));
			this.register_Component(new RB_TextField(this, 	_DB.EMAIL_SEND$SENDER_ADRESS,true,200));
			
			this.register_Component(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS,
									new RGOM_SimpleDaughter_EMAIL_SEND_TARGETS(this, _DB.EMAIL_SEND$ID_EMAIL_SEND));

			this.rgom_ListeAnlagen = new RGOM_LISTE_ANLAGEN();
			this.register_Component(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_LIST_ARCHIVMEDIEN,
					this.rgom_ListeAnlagen);
			
			
			SPIELWIESE_MASK_HashMap mask = new SPIELWIESE_MASK_HashMap();
			
			this.rb_ModuleContainerMASK = new RB_ModuleContainerMASK(mask);
			this.rb_ModuleContainerMASK.register_RB_MASK(this);
			this.rb_ModuleContainerMASK.get_oRowForButtons().add(new RB_BT_SaveMask(this.rb_ModuleContainerMASK,E2_ResourceIcon.get_RI("save.png")));
*/			
//			this.rb_ModuleContainerMASK.add(oGrid, E2_INSETS.I(2,2,2,2));
//			this.rb_ModuleContainerMASK.INIT(RB__MASK_KENNER.RB_MODUL_KENNER.POPUP_EMAIL_SEND, oGrid, true);
			
			//E2_MaskFiller  oFiller = new E2_MaskFiller(this,null,null);
			
		/*	oFiller.add_Line(grid, "#ID-Email", 1, _DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU, 1);
			oFiller.add_Line(grid, "#Absender", 1, _DB.FIBU_KONTENREGEL_NEU$KOMMENTAR, 1);
			oFiller.add_Line(grid, "#Irgendwas", 1, _DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU, 1);

			*/
			//mask.register_Interactiv_settings_validation(_DB.EMAIL_SEND, _DB.EMAIL_SEND$ID_EMAIL_SEND, new ownValidator_CheckMailAdresses());
			
			
			
			//mask.set_Records_2_All_Masks(cID_EMAIL_SEND_UF, RB__CONST.MASK_STATUS.EDIT);
			//bibMSG.add_MESSAGE(rb_ModuleContainerMASK.get_RB_MASK_HM().maskContent_COMPLETE_FILL_CYCLE());
			
			//rb_ModuleContainerMASK.add_CloseActions(new ownCloseAction(this.rb_ModuleContainerMASK));

		}
		
		public MyE2_Grid getGrid() {
			return grid;
		}

		/*
		@Override
		public MyE2_MessageVector maskSettings_do_Before_Load()
				throws myException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MyE2_MessageVector maskSettings_do_After_Load()
				throws myException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MyE2_MessageVector maskSettings_Define_Own_SurfaceSettings(
				HashMap<String, RB_SURFACE_SETTINGS> hmBasic,
				MASK_STATUS statusToSet) throws myException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MyRECORD_IF_RECORDS generate_RECORD(String cID_RECORD)
				throws myException {
			return new RECORD_FIBU_KONTENREGEL_NEU(cID_RECORD);
		}
		
*/
		/*
		public void createAndShow() throws myException {
			this.rb_ModuleContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(500), new MyE2_String("Email-Verbund"));
		}
		
	
		private class ownCloseAction extends XX_ActionAgentWhenCloseWindow  {

			public ownCloseAction(E2_BasicModuleContainer container) {
				super(container);
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				// RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.this.oMAP_From_Archivmedien.get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE("");
			}
			
		}
		

		@Override
		public MyRECORD_IF_RECORDS generate_RECORD(String cID_RECORD) throws myException {
			return new RECORD_EMAIL_SEND(cID_RECORD);
		}

		@Override
		public MyE2_MessageVector maskSettings_do_Before_Load() throws myException
		{
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector maskSettings_do_After_Load() throws myException
		{
			this.rgom_ListeAnlagen.fill_Grid((RECORD_EMAIL_SEND)this.get_Record4Mask());
			
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector maskSettings_Define_Own_SurfaceSettings(HashMap<String, RB_SURFACE_SETTINGS> hmBasic, MASK_STATUS statusToSet) throws myException {
			hmBasic.get(_DB.EMAIL_SEND$BETREFF).set_MustField(true);
			hmBasic.get(_DB.EMAIL_SEND$SENDER_ADRESS).set_MustField(true);
			hmBasic.get(_DB.EMAIL_SEND$TEXT).set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.LEFT);
			
			
			hmBasic.get(_DB.EMAIL_SEND$BETREFF).set_Description(new MyE2_String("eMail-Betreff"), new MyE2_String("Hauptmaske"));
			hmBasic.get(_DB.EMAIL_SEND$SENDER_ADRESS).set_Description(new MyE2_String("eMail-Absender"), new MyE2_String("Hauptmaske"));
			hmBasic.get(_DB.EMAIL_SEND$TEXT).set_Description(new MyE2_String("eMail-Text"), new MyE2_String("Hauptmaske"));
			
			return null;
		}


	
		//validierer, der die email-adressen prueft
		private class ownValidator_CheckMailAdresses extends RB_MASK_Set_And_Valid {

			@Override
			public MyE2_MessageVector make_InteractiveSettings(	MASK_STATUS 							status, 
																RB_MASK_HM_BASE 						hmMASK, 
																int 									ActionType, 
																ExecINFO 								oExecInfo, 
																HashMap<String, MyRECORD_IF_FILLABLE> 	hmRecords) throws myException {
				
				MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
				
				Vector<MyE2IF__DB_Component>  vMailadressenAlle = this.sammleMailadressen(hmMASK,true);
				Vector<MyE2IF__DB_Component>  vMailadressenFalsch = this.sammleMailadressen(hmMASK,false);

				RB_MASK__BASE mask = hmMASK.get(_DB.EMAIL_SEND);
				 
				
				//alle farbmarkierungen resetten
				for (MyE2IF__DB_Component  tf: vMailadressenAlle) {
					((MyE2_DB_TextField)tf).setBackground(new E2_ColorEditBackground());
				}
				((RGOM_SimpleDaughter_EMAIL_SEND_TARGETS)mask.get(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS))
				.set_bEnabled_For_Edit_Add_and_Del_Buttons(false);

				
				
				
				if (ActionType==RB_MASK_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
					
					if (vMailadressenFalsch.size()>0) {
						for (MyE2IF__DB_Component eMail:vMailadressenFalsch) {
							oMVRueck.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Mailadresse: ",true,eMail.get_cActualMaskValue(),false," scheint nicht korrekt! ",true)));
							((MyE2_DB_TextField)eMail).setBackground(new E2_ColorHelpBackground());
						}
					}
					
					//jetzt pruefen, ob die eMail eine originaldatei betrifft und ob bereits verschickt
					if (mask.get_actual_MASK_STATUS().isStatusEdit()) {
						
						//die standard-maskeneinstellungen holen
						mask.maskSettings_Generate_Standard_SurfaceSettings(RB__CONST.MASK_STATUS.EDIT);
						mask.maskSettings_Define_Own_SurfaceSettings(mask.get_HM_Surface_Settings(), RB__CONST.MASK_STATUS.EDIT);
						
						
						
						RECORD_EMAIL_SEND recSend = (RECORD_EMAIL_SEND)mask.get_Record4Mask();
						
						RECLIST_ARCHIVMEDIEN rlMed= recSend.get_DOWN_RECORD_LIST_ARCHIVMEDIEN_id_email_send();
						boolean bHasOrig = false;
						for (RECORD_ARCHIVMEDIEN ra: rlMed) {
							bHasOrig = bHasOrig||ra.is_IST_ORIGINAL_YES();
						}
						
						if (bHasOrig) {
							((RGOM_SimpleDaughter_EMAIL_SEND_TARGETS)mask.get(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS))
									.set_bEnabled_For_Edit_Add_and_Del_Buttons(false);
						}
						
//						//jetzt durch die zeilen der versandadressen laufen und verschickte disabled setzen
//						((RGOM_SimpleDaughter_EMAIL_SEND_TARGETS)mask.get(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS))
//							.set_bEnabled_4_Edit_TargetMailField(false);
//						
						//wenn ein target-eintrag als verschickt markiert ist, dann maske disabled
						if (((RGOM_SimpleDaughter_EMAIL_SEND_TARGETS)mask.get(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS)).get_bEvenOneTargetWasSend()) {
							((RGOM_SimpleDaughter_EMAIL_SEND_TARGETS)mask.get(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS)).set_bDaughterIsPassive(true);
							
							for (String hash: mask.get_HM_Surface_Settings().keySet()) {
								if (!hash.equals(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_LIST_ARCHIVMEDIEN)) {
									mask.get_HM_Surface_Settings().get(hash).set_Enabled(false);
								}
							}
							mask.maskSettings_Execute_SurfaceSettings();
							
						}
						
						
					}
					
					
					
					
					
				} else if (ActionType==RB_MASK_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
					if (vMailadressenFalsch.size()>0) {
						for (MyE2IF__DB_Component eMail:vMailadressenFalsch) {
							oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mailadresse: ",true,eMail.get_cActualMaskValue(),false," scheint nicht korrekt! ",true)));
							((MyE2_DB_TextField)eMail).setBackground(new E2_ColorHelpBackground());
						}
					}
				}
				
				
				
				
				
				return oMVRueck;
			}
			
			
			private Vector<MyE2IF__DB_Component> sammleMailadressen(RB_MASK_HM_BASE hmMASK, boolean bAll) throws myException {
				
				Vector<MyE2IF__DB_Component>  vMailadressenFalsch = new Vector<MyE2IF__DB_Component>();
				Vector<MyE2IF__DB_Component>  vMailadressenAll = new Vector<MyE2IF__DB_Component>();
				RB_TextField tf_Email= (RB_TextField)hmMASK.get(_DB.EMAIL_SEND).get__Comp(_DB.EMAIL_SEND$SENDER_ADRESS);
				
				vMailadressenAll.add(tf_Email);
				
				if (S.isFull(tf_Email.get_cActualMaskValue())) {
					if (!(new MailAdressChecker(tf_Email.get_cActualMaskValue()).isOK())) {
						vMailadressenFalsch.add(tf_Email);
					}
				}

				RB_SimpleDaughter  sd_Verteiler = (RB_SimpleDaughter)hmMASK.get(_DB.EMAIL_SEND).get__Comp(RGOM_UP_DOWN_EMAIL_COL_ComponentFactory.HASHKEY_MASK_DAUGHTER_TARGETS);
				
				Vector<E2_ComponentMAP> vE2_ComponentMAPS = sd_Verteiler.get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker();
				
				for (E2_ComponentMAP oMAP: vE2_ComponentMAPS) {
					vMailadressenAll.add(oMAP.get__DBComp(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS));
					String cMailAdress = oMAP.get_cActualDBValueFormated(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS);
					if (S.isFull(cMailAdress)) {
						if (!(new MailAdressChecker(cMailAdress).isOK())) {
							vMailadressenFalsch.add(oMAP.get__DBComp(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS));
						}
					}
				}
				
				return bAll?vMailadressenAll:vMailadressenFalsch;
				
			}
			
			
		}
		*/
	
}

