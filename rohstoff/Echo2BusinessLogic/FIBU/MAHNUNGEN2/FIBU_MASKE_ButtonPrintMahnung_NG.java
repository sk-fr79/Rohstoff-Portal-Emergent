package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_agent_processing_print_mail_preview;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class FIBU_MASKE_ButtonPrintMahnung_NG extends MyE2_Button 
{
	private FIBU_Container4Mahnung_NG oMaskContainer = null;

	private static final String KONTAKT_ = "KONTAKT_";

	private processtype typ;

	private ownJasperHash oJh;

	private VectorSingle  selectedFibuIds = new VectorSingle();

	private String smallestFibuId =null;

	private boolean fileFlag = false;

	public FIBU_MASKE_ButtonPrintMahnung_NG(FIBU_Container4Mahnung_NG MaskContainer, processtype e_typ) throws myException 
	{
		super("");
		this.typ = e_typ;

		this.oMaskContainer = MaskContainer;

		switch(typ){
		case MAIL:
			this.setText("Speichern + Mail");
			this.setToolTipText(new MyE2_String("Mahnung per Mail Schicken, speichern und Maske schliessen").CTrans());
			this.add_oActionAgent(new ownActionAgent(typ));
			this.add_GlobalValidator(new ownValidator());
			this.add_oActionAgent(new ownCloseAction(this.oMaskContainer, this.typ));
			break;
		case PRINT:
			this.setText("Speichern + Drucken");
			this.setToolTipText(new MyE2_String("Mahnung drucken, speichern und Maske schliessen").CTrans());
			this.add_oActionAgent(new ownActionAgent(typ));
			this.add_GlobalValidator(new ownValidator());
			this.add_oActionAgent(new ownCloseAction(this.oMaskContainer, this.typ));
			break;
		default:
			break;
		}

		this.add_oActionAgent(new ownArchivAgent());


		selectedFibuIds.addAll(oMaskContainer.get_oNaviList().get_vSelectedIDs_Unformated_Select_the_one_and_only());
		Collections.sort(selectedFibuIds);
		if(selectedFibuIds.size()>0){
			smallestFibuId = selectedFibuIds.get(0);
		}

	}

	private class ownValidator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector omv = new MyE2_MessageVector();
			
			FIBU_Container4Mahnung_NG parentComponent = FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer;

			omv.addAll(parentComponent.checkFields());
			return omv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}

	}
	private class ownJasperHash extends SE_JasperHASH_4_ArchivPDF{

		private String cID_ADRESSE = null;

		public ownJasperHash(String p_id_mahnung) throws myException {
			super("FIBU_MAHNUNG", _TAB.fibu, p_id_mahnung);

			RECORD_MAHNUNG recMahnung = FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.get_recMahnung();

			this.cID_ADRESSE = recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().get(0).get_UP_RECORD_FIBU_id_fibu().get_ID_ADRESSE_cUF();

			this.put("id_mahnung",recMahnung.get_ID_MAHNUNG_cUF());

			this.set_bVorschauDruck(false);

			this.get_vExecuters().add(0,new FIBU_EXECUTER_ADD_RECHNUNG_ORIG(FIBU_MASKE_ButtonPrintMahnung_NG.this));

			this.set_cDownloadAndSendeNameStaticPart("MAHNUNG"+"_"+recMahnung.get_MAHNSTUFE_cUF_NN("-")+"ADRESSE_"+this.cID_ADRESSE);
		}

		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return true;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return "MAHNUNG";
		}

		@Override
		public ArrayList<String> get_v_email_targets() throws myException {
			return FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.getSelectedEmails();
		}

		@Override
		public MAILPROFILE get_used_send_profile() throws myException {
			return MAILPROFILE.FIBUMAHNUNGEN;
		}

		@Override
		public void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale)
				throws myException {/* */}

		@Override
		public PROGRAMMKENNER generate_programmkenner_for_archiv() throws myException {
			return null;
		}

		@Override
		public MEDIENKENNER generate_medienkenner_for_archiv() throws myException {
			return MEDIENKENNER.AUTOARCHIV;
		}

		@Override
		protected MailBlock create_MailBlock() throws myException {
			return new ownMailBlock();
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException {
			return false;
		}

		@Override
		public void doActionAfterCreatedFile() throws myException {
		}

	}

	private  class ownActionAgent extends SE_agent_processing_print_mail_preview
	{

		public ownActionAgent(processtype p_typ) {
			super(p_typ);
		}

		@Override
		public boolean is_multi_id_allowed() {
			return false;
		}

		@Override
		public Vector<String> get_ids_to_print() throws myException {
			Vector<String> id = new Vector<>();

			if(FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.isbNeu()){
				smallestFibuId =selectedFibuIds.get(0);
				id.add(smallestFibuId);
			}else{
				RECLIST_FIBU_MAHNUNG rec = new RECLIST_FIBU_MAHNUNG(
						RECORD_FIBU_MAHNUNG.FIELD__ID_MAHNUNG + "=" +
								FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.getMahnungID(),
								RECORD_FIBU_MAHNUNG.FIELD__ID_FIBU
						);
				smallestFibuId = rec.get(0).get_ID_FIBU_cUF();
				id.add( smallestFibuId);
			}

			return id;
		}

		@Override
		public SE_JasperHASH_4_ArchivPDF generate_jasperHash(String p_id_mahnung) throws myException {

			String idMahnung = p_id_mahnung;

			MyE2_MessageVector mv = FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.save_mask();

			if(mv.get_bIsOK()){
				ArrayList<String> emails = FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.getSelectedEmails(); 
				String fax = FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.getSelectedFaxNummer();
				
				oJh = new ownJasperHash(idMahnung);
				
				oJh.put("FAXNUMMER", fax);
				
				for(int i = 0; i<emails.size(); i++){
					oJh.put(KONTAKT_ + (i + 1), emails.get(i));	
				}

				oJh.put("id_mahnung", FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.getMahnungID());
			}	
			return oJh;

		}		

	}

	public class ownArchivAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			Vector <String> updateSqlStatement = new Vector<>();

			RECLIST_FIBU_MAHNUNG fmRecList = new RECLIST_FIBU_MAHNUNG(RECORD_FIBU_MAHNUNG.FIELD__ID_MAHNUNG+"="+oMaskContainer.getMahnungID(), RECORD_FIBU_MAHNUNG.FIELD__ID_FIBU);
			if(fmRecList.size()>1){
				for(int i = 1; i<fmRecList.size();i++){
					RECORD_ARCHIVMEDIEN_ext amRecExt = new RECORD_ARCHIVMEDIEN_ext(oJh.get_rec_Archivmedien());
					updateSqlStatement.add(amRecExt.connectToAdditionalEntry(FIBU.baseTabName(), fmRecList.get(i).get_ID_FIBU_cUF()));	
				}
				bibDB.ExecMultiSQLVector(updateSqlStatement, true);

			}
		}

	}

	private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyE2_String cStringForComponent = null;

		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent, Boolean bAllowEdit) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, bAllowEdit);
			this.cStringForComponent = StringForComponent; 
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.cStringForComponent,MyE2_Label.STYLE_SMALL_ITALIC());
		}

		@Override
		public String get_cAdressInfo() throws myException
		{
			return "<keine Adressinfo>";
		}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return this.cStringForComponent;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return null;
		}

	}

	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() throws myException
		{
			super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,OWN_MailBlock,new MyE2_String("Vorhandene eMail"),new Boolean(false));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",OWN_MailBlock,new MyE2_String("Freie eMail"),new Boolean(true));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"),new Boolean(false));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-eMail"),new Boolean(false));
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll() throws myException
		{
			return new MyE2_String("MAHNUNG");
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return "MAHNUNG";
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String("MAHNUNG");
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String("Mail aus Mahnungsmodul");
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return FIBU_MASKE_ButtonPrintMahnung_NG.this.oMaskContainer.get_oNaviList().get_oContainer_NaviList_BelongsTo().get_MODUL_IDENTIFIER();
		}
	}

	public FIBU_Container4Mahnung_NG get_maskContainer() {

		return oMaskContainer;
	}

	public boolean isFileGenerated() {
		return fileFlag;
	}

	private class ownCloseAction extends XX_ActionAgent{

		private FIBU_Container4Mahnung_NG parent;
		private processtype typ;

		public ownCloseAction(FIBU_Container4Mahnung_NG c_parentContainer, processtype typ) {
			this.parent = c_parentContainer;
			this.typ = typ;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			if(this.typ==processtype.MAIL){
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Eine Email ist vorbereitet, bitte prüfen Sie den Inhalt und führen die Sendung aus."));
			}

			parent.get_oNaviList().refresh_pageinfo_in_navigator(parent.get_oNaviList().get_iActualPage());
			parent.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}

	}
}
