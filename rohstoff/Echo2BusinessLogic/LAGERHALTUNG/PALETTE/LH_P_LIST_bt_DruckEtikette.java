package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_agent_processing_print_mail_preview;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_LagerPalette;

public class LH_P_LIST_bt_DruckEtikette extends E2_Button {

	private RB_TransportHashMap m_trpHashMap = null;

	public LH_P_LIST_bt_DruckEtikette(RB_TransportHashMap p_trpHashMap) throws myException{
		super();

		this.m_trpHashMap = p_trpHashMap;

		this._aaa(new ownActionAgent());

		this._image(E2_ResourceIcon.get_RI("barcode.png")).setToolTipText("Selektiert Etiketten drucken");

		this._addGlobalValidator(new ownValidator());
	}


	public RB_TransportHashMap getTransportHashMap() {
		return m_trpHashMap;
	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			LH_P_LIST_bt_DruckEtikette copy = new LH_P_LIST_bt_DruckEtikette(this.getTransportHashMap());
			copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
			return copy;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}


	private class ownActionAgent extends SE_agent_processing_print_mail_preview{

		public ownActionAgent() {
			super(processtype.PRINT);
			
			this._addAfterCreateAllPdfsListener((ids,tempfiles)-> {
				try {
					VEK<Rec21_LagerPalette> paletten = new VEK<Rec21_LagerPalette>();
					DEBUG.System_print(ids);
					for (String id: ids) {
						Rec21_LagerPalette palette = new Rec21_LagerPalette()._fill_id(id);
						palette._setNewVal(LAGER_PALETTE.datum_verarbeitet, new Date(), bibMSG.MV());
						paletten._a(palette);
					}
					if (bibDB.saveRecords(paletten,true, bibMSG.MV())) {
						bibMSG.MV()._addInfo(S.ms("Die Palette(n) wurden als verarbeitet mit heutigem Datum gesetzt!"));
					}
					
					m_trpHashMap.getNavigationList()._REBUILD_ACTUAL_SITE(null);;
					
				} catch (Exception e) {
					e.printStackTrace();
					bibMSG.MV()._addAlarm(e.getLocalizedMessage());
				}
			});

		}


		@Override
		public boolean is_multi_id_allowed() {
			return true;
		}

		@Override
		public Vector<String> get_ids_to_print() throws myException {
			Vector<String> vSelectedIds = getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated();
			Vector<String> vConfirmedIds = new Vector<String>();
			for(String selectedId : vSelectedIds) {
				Rec21 recPalette = new Rec21(_TAB.lager_palette)._fill_id(selectedId);
				boolean ausgebucht = 
						S.isFull(recPalette.get_ufs_dbVal(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, "")) ||
						recPalette.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand);
				if(!ausgebucht) {
					vConfirmedIds.add(selectedId);
				}
				
			}
			return vConfirmedIds;
		}

		@Override
		public SE_JasperHASH_4_ArchivPDF generate_jasperHash(String id_palette) throws myException {
			return new ownJasperHash(id_palette);
		}	
	}

	private class ownJasperHash extends SE_JasperHASH_4_ArchivPDF {


		public ownJasperHash(String id_palette) throws myException {
			super("palette_etikett", _TAB.lager_palette);

			this.put("ID_LAGER_PALETTE", id_palette);

			this.put("PREFIX_BARCODE", ENUM_MANDANT_CONFIG.PALETTE_BARCODE_PREFIX.getValue(bibALL.get_ID_MANDANT()));
		}


		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return false;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return "palette_etikett";
		}


		@Override
		public ArrayList<String> get_v_email_targets() throws myException {
			return null;
		}

		@Override
		public MAILPROFILE get_used_send_profile() throws myException {
			return null;
		}

		@Override
		public void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale)
				throws myException {}

		@Override
		public PROGRAMMKENNER generate_programmkenner_for_archiv() throws myException {
			return null;
		}

		@Override
		public MEDIENKENNER generate_medienkenner_for_archiv() throws myException {
			return null;
		}

		@Override
		protected MailBlock create_MailBlock() throws myException {
			return null;
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException {
			return false;
		}

		@Override
		public void doActionAfterCreatedFile() throws myException {
		}
	}

	private class ownValidator extends XX_ActionValidator_NG{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			boolean ausgebucht = false;

			VEK<String> vSelectedIds = new VEK<String>()._a(m_trpHashMap.getNavigationList().get_vSelectedIDs_Unformated());

			if(vSelectedIds.size()==0) {
				mv._addAlarm("Sie müssen mindestens eine Palette auswahlen.");
			}else {
				for(String selectedId: vSelectedIds) {
					Rec21 recPalette = new Rec21(_TAB.lager_palette)._fill_id(selectedId);
					ausgebucht = 
							S.isFull(recPalette.get_ufs_dbVal(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, "")) ||
							recPalette.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand);

					if(ausgebucht == true) {
						mv._addWarn("Palette ID."+bibALL.convertID2FormattedID(selectedId) +": Die Etikette kann nicht gedruckt werden, weil die Palette ist schon ausgebucht !");
					}
				}
			}

			return mv;
		}

	}
}


