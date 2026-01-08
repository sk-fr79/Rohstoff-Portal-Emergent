package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_FaxNummerSearcher_Ext;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_ListWithSelector_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_MailAdressSearcher;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_agent_processing_print_mail_preview;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.FAXNUMMER_DEF;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_ENUMS.FIBU_FORMULAR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_FORMULAR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.FIBU_CONST.FORMULARSTYP;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2.FIBU_LIST_BT_Sachbearbeiter_Validator;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class FIBU_LIST_BT_PrintMail_Kontokorrentabrede extends MyE2_Button implements FIBU_LIST_BT_IF_Druck_Mail_Common{

	private E2_NavigationList  naviList = null;

	private ArrayList<String> emailList = new ArrayList<>();
	private ArrayList<String> faxNumberList = new ArrayList<>() ;

	private SE_ListWithSelector_Component emailListComp;
	private SE_ListWithSelector_Component FaxListComp;

	private MyE2_TextField_DatePOPUP_OWN falligDatumFeld;
	
	private MyE2_SelectField formularSelectBox;
	
	private MyE2_TextField tagenToleranzFeld;
		
	private ownJasperHash oJh;
	
	private MyE2_MessageVector omv;
	
	private Vector<String> selectedBuchungBlock;

	private Vector<String> selectedFibuIds;
	
	private static final String KONTAKT_ = "KONTAKT_";

	private static final FORMULARSTYP FORMULAR_TYP =  FORMULARSTYP.KONTOKORRENTABREDE;
	
	private RECORD_FIBU_FORMULAR formularName;
	
	private SEL querySelectField;
	
	private MyE2_CheckBox chkBoxRechnungAnhaegen =  null;

	public FIBU_LIST_BT_PrintMail_Kontokorrentabrede(E2_NavigationList  p_naviList) throws myException {
		super(new MyE2_String("Druck/Mail Kontokorrentabrede"));
		this.naviList = p_naviList;
		this.selectedBuchungBlock = new Vector<>();
		this.selectedFibuIds = new Vector<>();
		this.omv = new MyE2_MessageVector();
		
		this.tagenToleranzFeld = new MyE2_TextField("3", 150, 3);
		
		querySelectField = new SEL(FIBU_FORMULAR.dropdowntext, FIBU_FORMULAR.id_fibu_formular)
				.FROM(_TAB.fibu_formular)
				.WHERE(new vgl(FIBU_FORMULAR.formulartyp,FORMULAR_TYP.getTyp()))
				.AND(new vgl(FIBU_FORMULAR.id_mandant, bibALL.get_ID_MANDANT()));
		
		this.add_GlobalValidator(new formularValidator());
		this.add_GlobalValidator(new KontokorrentabredeValidator());
		this.add_GlobalValidator(new FIBU_LIST_BT_Sachbearbeiter_Validator());
		
		this.add_oActionAgent(new XX_ActionAgent() {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new ownBasicPopupContainer();
			}
		});

	}

	
	private void preloadEmailList() throws myException{
		emailList.clear();

		Vector<String> selectedIds = FIBU_LIST_BT_PrintMail_Kontokorrentabrede.this.naviList.get_vSelectedIDs_Unformated();
		if(selectedIds.size()>0){	
			RECORD_FIBU rec = new RECORD_FIBU(selectedIds.get(0));
			String adresseId = rec.get_ID_ADRESSE_cUF();

			RECORD_ADRESSE recAddr = new RECORD_ADRESSE(adresseId);
			SE_MailAdressSearcher mailSearcher = new SE_MailAdressSearcher(recAddr, MAILDEF.EMAIL_FIBU, true, true, false);
			if(mailSearcher.get_v_MailAdressesFound().size()==0){
				omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es gibt keine Email-Adresse mit der Mail-Verwendung FIBU."))); //kein empfänger emails
			} else emailList.addAll(mailSearcher.get_v_MailAdressesFound());
		}
	}

	private void preloadFaxNumbers() throws myException{
		faxNumberList.clear();
		Vector<String> selectedIds = FIBU_LIST_BT_PrintMail_Kontokorrentabrede.this.naviList.get_vSelectedIDs_Unformated();
		if(selectedIds.size()>0){
			RECORD_FIBU rec = new RECORD_FIBU(selectedIds.get(0));
			String adresseId = rec.get_ID_ADRESSE_cUF();

			SE_FaxNummerSearcher_Ext faxNumberResearch = new SE_FaxNummerSearcher_Ext(adresseId, FAXNUMMER_DEF.FIBU, true, true);
			if(faxNumberResearch.get_vFax_number_found().size()==0){
				omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es ist keine Faxnummer beim Kunden eingetragen."))); //kein empfänger faxnummer
			}else if(faxNumberResearch.get_vFax_number_found().size()>4){
				for(int i = 0;i<5 ;i++){
					faxNumberList.add(faxNumberResearch.get_vFax_number_found().get(i));
				}
			}else faxNumberList.addAll(faxNumberResearch.get_vFax_number_found());


		}
	}

	public E2_NavigationList getNaviList() {
		return naviList;
	}

	public void setNaviList(E2_NavigationList naviList) {
		this.naviList = naviList;
	}

	private class ownBasicPopupContainer extends E2_BasicModuleContainer {

		

		public ownBasicPopupContainer() throws myException {
			super();

			this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());

			FIBU_LIST_BT_PrintMail_Button  bt_print = new FIBU_LIST_BT_PrintMail_Button("Druck", selectedBuchungBlock, formularName);
			FIBU_LIST_BT_PrintMail_Button  bt_preview = new FIBU_LIST_BT_PrintMail_Button("Vorschau", selectedBuchungBlock, formularName);
			FIBU_LIST_BT_PrintMail_Button  bt_mail = new FIBU_LIST_BT_PrintMail_Button("eMail", selectedBuchungBlock, formularName);

			emailListComp = new SE_ListWithSelector_Component(bt_print, bt_mail, bt_preview, processtype.MAIL);
			emailListComp.setMultipleSelectionAllowed(true);
			emailListComp.setOnly_five_contact(false);
			emailListComp.setFixedSize(290, 75);
			
			FaxListComp = new SE_ListWithSelector_Component( null, bt_mail, null, processtype.PRINT);
			FaxListComp.setMultipleSelectionAllowed(false);
			FaxListComp.setFixedSize(290, 75);
			
			falligDatumFeld = new MyE2_TextField_DatePOPUP_OWN("",150);
			
			chkBoxRechnungAnhaegen = new MyE2_CheckBox("Rechnungsarchiv anhängen");
			
			formularSelectBox = new MyE2_SelectField(querySelectField.s(),false, false, false, false);
			formularSelectBox.add_oActionAgent(new XX_ActionAgent() {	
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bt_preview.refresh_fibu_formular(formularSelectBox.get_ActualWert());
					bt_print.refresh_fibu_formular(formularSelectBox.get_ActualWert());
					bt_mail.refresh_fibu_formular(formularSelectBox.get_ActualWert());
				}
			});
			
			
			bt_print.add_GlobalValidator(new dataValidator());
			bt_print.add_oActionAgent(new ownActionAgent(processtype.PRINT));
			bt_print.add_oActionAgent(new ownArchivAgent(naviList));
			bt_print.add_oActionAgent(new ownCloseAgent(this, processtype.PRINT));
			bt_print.refresh_fibu_formular(formularSelectBox.get_ActualWert());
			
			bt_mail.add_GlobalValidator(new dataValidator());
			bt_mail.add_oActionAgent(new ownActionAgent(processtype.MAIL));
			bt_mail.add_oActionAgent(new ownArchivAgent(naviList));
			bt_mail.add_oActionAgent(new ownCloseAgent(this, processtype.MAIL));
			bt_mail.refresh_fibu_formular(formularSelectBox.get_ActualWert());
			
			bt_preview.add_GlobalValidator(new dataValidator());
			bt_preview.add_oActionAgent(new ownActionAgent(processtype.PREVIEW));
			bt_preview.refresh_fibu_formular(formularSelectBox.get_ActualWert());
			
			bt_mail.set_bEnabled_For_Edit(false);
			
			
			int[] i_breite = {300,300};
			E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_DDARK_BORDER())
					.def_(E2_INSETS.I(2,5,2,2))
					.add_(new MyE2_Label("Formular / Sprache: ",new E2_FontBold(2)))
					.add_(formularSelectBox)
					.def_(2,1)
					.def_(E2_INSETS.I(2,10,2,4))
					.add_(new MyE2_Label(new MyE2_String("Bitte wählen ..."),new E2_FontBold(2)))	
					.def_(1,1)
					.def_(E2_INSETS.I(2,5,2,2))
					.def_(new E2_ColorDark())
					.add_(new MyE2_Label(new MyE2_String("Fax Nummer"),new E2_FontBoldItalic()))
					.add_(new MyE2_Label(new MyE2_String("Email "),new E2_FontBoldItalic()))
					.def_(new E2_ColorBase())
					.add_(FaxListComp.setKontakts_with_scrollBar(faxNumberList))
					.add_(emailListComp.setKontakts_with_scrollBar(emailList))
					.def_(E2_INSETS.I(2,5,2,2))
					.add_(new MyE2_Label(new MyE2_String("Widerspruchsfrist (Tage):"),new E2_FontBold(2)))
					.add_(tagenToleranzFeld)
					.add_(new MyE2_Label(new MyE2_String("Fällig am:"),new E2_FontBold(2)))
					.add_(falligDatumFeld)
					.setSize_(i_breite)
					;

			int[] i_button_breite = {200,200,200, 200};
			E2_Grid4MaskSimple buttonGrid = new E2_Grid4MaskSimple()
			.def_(E2_INSETS.I(4,4,4,4))
			.add_(bt_print)
			.add_(bt_mail)
			.add_(bt_preview)
			.setSize_(i_button_breite);


			this.add(gm, E2_INSETS.I(5,5,5,0));
			this.add(chkBoxRechnungAnhaegen,E2_INSETS.I(6,20,15,0));
			this.add(buttonGrid, E2_INSETS.I(5,2,5,5));
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(640), new Extent(480), new MyE2_String("Drucken/Mailen/Vorschau - Kontokorrentabrede"));

			bibMSG.add_MESSAGE(omv);
			
			FIBU_LIST_BT_Sachbearbeiter_Validator.sachBearbeiterWarnung();
		}
	}

	private class KontokorrentabredeValidator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv_rueck = new MyE2_MessageVector();

			E2_NavigationList parentNaviList = FIBU_LIST_BT_PrintMail_Kontokorrentabrede.this.getNaviList();

			Vector<String> selectIdsVector = parentNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only();

			selectedFibuIds.clear();
			selectedBuchungBlock.clear();
			omv.clear();
			
			
			if(selectIdsVector.size() == 0){
				mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte ein Eintrag auswählen.")));
			}		
			
			else {

				Collections.sort(selectIdsVector);
				RECORD_FIBU rec = new RECORD_FIBU_ext(selectIdsVector.get(0));
				String buchungNr = rec.get_BUCHUNGSBLOCK_NR_cUF();

				RECLIST_FIBU recBuhungNr = new RECLIST_FIBU(RECORD_FIBU.FIELD__BUCHUNGSBLOCK_NR + "="+buchungNr, "");
				Vector <String> buchungBlockList = new Vector<String>(recBuhungNr.get_ID_FIBU_hmString_UnFormated("").values());

				for(String selectedId : selectIdsVector){
					if(! buchungBlockList.contains(selectedId)){
						mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie nur Einträge eines Buchungsblocks aus!")));
					}

				}
				
				int flag[] = {0,0,0};
				
				for(RECORD_FIBU record: recBuhungNr){
					
					if 			(	(record.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG) 	&& record.d(FIBU.endbetrag_fremd_waehrung).doubleValue()>=0)   ||
									(record.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT) 	&& record.d(FIBU.endbetrag_fremd_waehrung).doubleValue()<0)	) {
						 flag[0]++;
					}else if 	(	(record.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT)  	&& record.d(FIBU.endbetrag_fremd_waehrung).doubleValue()>=0) ||
									(record.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG)  	&& record.d(FIBU.endbetrag_fremd_waehrung).doubleValue()<0)) {
						 flag[1]++;
					}else{
						flag[2]++;
					}
				}
				
				if(flag[0]==0 || flag[1]==0){
					mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie einen Buchungsblock mit mindestens einer Forderung und einer Verbindlichkeit!")));
				}
				
				for(String selectedId :buchungBlockList){
					RECORD_FIBU rec1 = new RECORD_FIBU(selectedId);
					if(rec1.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG) || rec1.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT)){
						selectedFibuIds.add(selectedId);
					}
				}
				
				Collections.sort(selectedFibuIds);
				
				selectedBuchungBlock.add(buchungNr);
			}

			
			preloadEmailList();
			preloadFaxNumbers();
			
			return mv_rueck;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}

	}

	private class formularValidator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			SEL formularCount = new SEL("COUNT ("+FIBU_FORMULAR.id_fibu_formular+")").FROM(_TAB.fibu_formular)
					.WHERE(new vgl(FIBU_FORMULAR.id_mandant,bibALL.get_ID_MANDANT()))
					.WHERE(new vgl(FIBU_FORMULAR.formulartyp,FORMULAR_TYP.getTyp()))
					.AND(new vgl(FIBU_FORMULAR.id_mandant, bibALL.get_ID_MANDANT()));
			
			MyE2_MessageVector mv =  new MyE2_MessageVector();
			
			int nbOfFormular = Integer.parseInt(bibDB.EinzelAbfrage(formularCount.s()));
			
			if(nbOfFormular==0){
				mv.add(new MyE2_Alarm_Message("Es ist kein Formular vom Typ:"+FORMULAR_TYP.getTyp() + " hinterlegt !"));
			}
			
			return mv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {return null;}
		
	}
	
	private class ownActionAgent extends SE_agent_processing_print_mail_preview {

		public ownActionAgent(processtype p_typ) {
			super(p_typ);
		}

		@Override
		public boolean is_multi_id_allowed() {
			return true;
		}

		@Override
		public Vector<String> get_ids_to_print() throws myException {
			Vector<String> selectedFibuId = new Vector<String>();
			selectedFibuId.add(selectedFibuIds.get(0));
			return selectedFibuId;
		}

		@Override
		public SE_JasperHASH_4_ArchivPDF generate_jasperHash(String id) throws myException {
			ArrayList<String> emails = emailListComp.getSelectedKontakt();
			String faxNummer = FaxListComp.getFaxNummer();

			if(S.isFull(faxNummer)){
				faxNummer = faxNummer.substring(0,faxNummer.indexOf("  ("));
			}
			
			formularName =  new RECORD_FIBU_FORMULAR(formularSelectBox.get_ActualWert());
			
			if(emails.size()>0){
				Collections.sort(emails);
			}

			oJh = new ownJasperHash(id);

			oJh.put("FAXNUMMER", faxNummer);			
			
			if(emails.size()>0){
				for(int i = 0; i<emails.size(); i++){
					oJh.put(KONTAKT_ + (i + 1), emails.get(i));	
				}
			}
			
			oJh.put("FAXNUMMER", faxNummer);

			oJh.put("BUCHUNGSBLOCK_NR", selectedBuchungBlock.get(0));
			
			oJh.put("TAGEN_TOLERANZ", tagenToleranzFeld.getText());
			
			String falligDatum = bibALL.BaueDatumSQL(falligDatumFeld.get_oFormatedDateFromTextField(), "DD.MM.YYYY");
			oJh.put("FALLIG_DATUM", falligDatum);

			return oJh;
		}
	}



	private class ownJasperHash extends SE_JasperHASH_4_ArchivPDF {

		public ownJasperHash(String id_fibu) throws myException {
			super(formularName.get_FORMULARNAME_cUF(), _TAB.fibu, id_fibu);
			
			this.get_vExecuters().add(0,new FIBU_LIST_BT_Rechnung_Anhanger_executer(FIBU_LIST_BT_PrintMail_Kontokorrentabrede.this));
		}

		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return true;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return FORMULAR_TYP.getTyp();
		}

		@Override
		public ArrayList<String> get_v_email_targets() throws myException {
			ArrayList<String> v_email;

			v_email = FIBU_LIST_BT_PrintMail_Kontokorrentabrede.this.emailListComp.getSelectedKontakt();

			return v_email;
		}

		@Override
		public MAILPROFILE get_used_send_profile() throws myException {
			return MAILPROFILE.FIBUKONTOKORRENT;
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

		@Override
		public void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale)	throws myException {

		}

		@Override
		public PROGRAMMKENNER generate_programmkenner_for_archiv() throws myException {
			return null;
		}

		@Override
		public MEDIENKENNER generate_medienkenner_for_archiv() throws myException {
			return MEDIENKENNER.AUTOARCHIV;
		}

	}
	
	public class ownArchivAgent extends XX_ActionAgent{

		E2_NavigationList navilist;
		
		public ownArchivAgent(E2_NavigationList c_NavigationList) {
			this.navilist = c_NavigationList;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String> selectIdsVector = this.navilist.get_vSelectedIDs_Unformated_Select_the_one_and_only();
			
			
			Collections.sort(selectIdsVector);
			String baseId = selectIdsVector.get(0);
			
			RECORD_FIBU fRec = new RECORD_FIBU(baseId);
			
			RECLIST_FIBU recBuhungNr = new RECLIST_FIBU(RECORD_FIBU.FIELD__BUCHUNGSBLOCK_NR + "="+fRec.get_BUCHUNGSBLOCK_NR_cUF(), RECORD_FIBU.FIELD__ID_FIBU);
			Vector <String> buchungBlockFibuIdList = recBuhungNr.get_vKeyValues();
			Vector <String> updateSqlStatement = new Vector<>();
			for(int i=1; i<buchungBlockFibuIdList.size();i++){
				String id_fibu = buchungBlockFibuIdList.get(i);
				
				RECORD_ARCHIVMEDIEN_ext amRecExt = new RECORD_ARCHIVMEDIEN_ext(oJh.get_rec_Archivmedien());
				updateSqlStatement.add(amRecExt.connectToAdditionalEntry(FIBU.baseTabName(), id_fibu));	
			}
			
			bibDB.ExecMultiSQLVector(updateSqlStatement, true);
		}
		
	}
	
	private class ownCloseAgent extends XX_ActionAgent{
		private E2_BasicModuleContainer parent;
		private processtype typ;
		public ownCloseAgent(E2_BasicModuleContainer c_parentContainer, processtype typ) {
			this.parent = c_parentContainer;
			this.typ = typ;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(this.typ==processtype.MAIL){
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Eine Email ist vorbereitet, bitte prüfen Sie den Inhalt und führen die Sendung aus."));
			}
			
			E2_ComponentMAP compMap = naviList.get_ComponentMAP(selectedFibuIds.get(0));
			if (compMap!=null)
			{
				compMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
			}
			naviList.refresh_pageinfo_in_navigator(naviList.get_iActualPage());
			
			RECLIST_FIBU recBuhungNr = new RECLIST_FIBU(RECORD_FIBU.FIELD__BUCHUNGSBLOCK_NR + "="+selectedBuchungBlock.get(0), RECORD_FIBU.FIELD__ID_FIBU);
			
			naviList.ADD_NEW_ID_TO_ALL_VECTORS(recBuhungNr.get_vKeyValues());
			naviList.set_SelectIDs(recBuhungNr.get_vKeyValues());
			
			parent.CLOSE_AND_DESTROY_POPUPWINDOW(false);

			
		}
		
	}

	private class dataValidator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			if(S.isEmpty(tagenToleranzFeld.getText())){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte eine Widerspruchsfrist erfassen !")));
			}
			
			if(emailListComp.getSelectedKontakt().size()>5){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Maximal 5 eMail-Adressen möglich!")));
			}
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}

	@Override
	public boolean mussRechnungAnhaegen() {
		return chkBoxRechnungAnhaegen.isSelected();
	}


	@Override
	public Vector<String> getSelectedFibuList() {
		return selectedFibuIds;
	}


	@Override
	public Vector<String> getSeletedBuchungNummer() {
		return selectedBuchungBlock;
	}


	@Override
	public MyE2_Button get_maskContainer() {
		return this;
	}
}
