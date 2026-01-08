package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.io.File;
import java.util.UUID;

import com.lowagie.text.pdf.BaseFont;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__FINDER_MODULE_KEY;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.LoadImageNT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver_Delete_File_WhenAllowed;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory;

public class RECORD_ARCHIVMEDIEN_ext extends RECORD_ARCHIVMEDIEN
{

	public RECORD_ARCHIVMEDIEN_ext() throws myException
	{
		super();
	}

	public RECORD_ARCHIVMEDIEN_ext(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}

	public RECORD_ARCHIVMEDIEN_ext(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}

	public RECORD_ARCHIVMEDIEN_ext(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public RECORD_ARCHIVMEDIEN_ext(RECORD_ARCHIVMEDIEN recordOrig)
	{
		super(recordOrig);
	}

	public RECORD_ARCHIVMEDIEN_ext(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_ARCHIVMEDIEN_ext(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}


	public String get__cCompletePathAndFileName() throws myException
	{
		String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());

		String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(this.get_PFAD_cUF_NN(""));
		String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(this.get_DATEINAME_cUF_NN(""));
		String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;

		return cCompletePath;
	}

	public String get__cCompletePathAndFileName(String wertWennExeption) 
	{
		String cCompletePath;
		try {
			String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());

			String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(this.get_PFAD_cUF_NN(""));
			String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(this.get_DATEINAME_cUF_NN(""));
			cCompletePath = File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
		} catch (myException e) {
			e.printStackTrace();
			cCompletePath = S.NN(wertWennExeption);
		}

		return cCompletePath;
	}


	public boolean get_bFileIsPhysicalExisting() throws myException{

		return new File(this.get__cCompletePathAndFileName()).exists();
	}




	public void starte__downLoad() throws myException
	{
		String cEndung = this.get_UP_RECORD_MEDIENTYP_id_medientyp().get_DATEIENDUNG_cUF_NN("");

		String cMimeTyp = "application/bin";

		if (S.isFull(cEndung))
		{
			cMimeTyp = "application/"+cEndung;
		}


		boolean download_immer_erlauben = bib_Settigs_Mandant.IS__Value("DOWNLOAD_ORIGINAL_RG_ERLAUBEN_FUER_GF", "N", "N") && 
				bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES();


		if (	this.is_IST_ORIGINAL_YES() && 
				this.get_UP_RECORD_MEDIENTYP_id_medientyp()!=null &&
				this.get_UP_RECORD_MEDIENTYP_id_medientyp().get_DATEIENDUNG_cF_NN("").toUpperCase().endsWith("PDF") && 
				(!download_immer_erlauben )) {
			//dann eine kopie erzeugen und dort einen Text einstempeln, diese dann downloaden
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Download eines Originals wird gestempelt !!")));
			PDF_Overlay_Factory fact = new PDF_Overlay_Factory();
			int iFontSize = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTSIZE_lValue(new Long(60)).intValue();
			String cWasserzeichenText = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_TEXT_cUF_NN("KOPIE");
			String cWasserzeichenFont = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTNAME_cUF_NN(BaseFont.HELVETICA_BOLD);
			float f_WasserzeichenRotate = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_ROTATE_dValue(270d).floatValue();

			//jetzt validieren
			if (iFontSize >100) {iFontSize=100;}
			if (iFontSize <0) 	{iFontSize=60;}

			if (!fact.get_FontNames().contains(cWasserzeichenFont))  {
				cWasserzeichenFont=BaseFont.HELVETICA_BOLD;
			}
			if (f_WasserzeichenRotate >360 || f_WasserzeichenRotate<1) {f_WasserzeichenRotate=270f;}

			fact.setImprintFontSize(iFontSize);
			fact.setImprintFontName(cWasserzeichenFont);


			myTempFile t = fact.generatePDFwithOverlayText(this.get__cCompletePathAndFileName() ,cWasserzeichenText , 1, f_WasserzeichenRotate);
			t.set_bDeleteAtFinalize(true);
			t.starteDownLoad(this.get_DOWNLOADNAME_cUF_NN("downFile"),cMimeTyp);
		} else {
			E2_Download oDownload = new E2_Download();
			oDownload.starteFileDownload(this.get__cCompletePathAndFileName(),this.get_DOWNLOADNAME_cUF_NN("downFile"),cMimeTyp);
		}

	}


	/**
	 * 2015-10-08: 	erzeugen eines tempfiles aus der vorhandenen datei, falls original und pdf, 
	 * 				dann gestempelt ausser der benutzer ist berechtigt
	 * @return
	 * @throws myException
	 */
	public myTempFile generate_tempFileCopy(boolean bDeleteAtFinalize) throws myException {

		myTempFile  tempf = null;

		boolean download_immer_erlauben = bib_Settigs_Mandant.IS__Value("DOWNLOAD_ORIGINAL_RG_ERLAUBEN_FUER_GF", "N", "N") && 
				bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES();


		if (	this.is_IST_ORIGINAL_YES() && 
				this.get_UP_RECORD_MEDIENTYP_id_medientyp()!=null &&
				this.get_UP_RECORD_MEDIENTYP_id_medientyp().get_DATEIENDUNG_cF_NN("").toUpperCase().endsWith("PDF") && 
				(!download_immer_erlauben )) {
			//dann eine kopie erzeugen und dort einen Text einstempeln, diese dann downloaden
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Download eines Originals wird gestempelt !!")));
			PDF_Overlay_Factory fact = new PDF_Overlay_Factory();
			//fact.setImprintColor(0, 0, 0);
			int iFontSize = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTSIZE_lValue(new Long(60)).intValue();
			String cWasserzeichenText = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_TEXT_cUF_NN("KOPIE");
			String cWasserzeichenFont = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTNAME_cUF_NN(BaseFont.HELVETICA_BOLD);
			float f_WasserzeichenRotate = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_ROTATE_dValue(270d).floatValue();

			//jetzt validieren
			if (iFontSize >100) {iFontSize=100;}
			if (iFontSize <0) 	{iFontSize=60;}

			if (!fact.get_FontNames().contains(cWasserzeichenFont))  {
				cWasserzeichenFont=BaseFont.HELVETICA_BOLD;
			}
			if (f_WasserzeichenRotate >360 || f_WasserzeichenRotate<1) {f_WasserzeichenRotate=270f;}

			fact.setImprintFontSize(iFontSize);
			fact.setImprintFontName(cWasserzeichenFont);

			//eine kopie des orignals
			tempf = fact.generatePDFwithOverlayText(this.get__cCompletePathAndFileName() ,cWasserzeichenText , 1, f_WasserzeichenRotate);
			tempf.set_bDeleteAtFinalize(bDeleteAtFinalize);

		} else {
			//hier eine kopie des originals
			tempf = new myTempFile(UUID.randomUUID().toString(), ".pdf", true, bDeleteAtFinalize, new File(this.get__cCompletePathAndFileName()));
		}

		return tempf;

	}




	/**
	 * Bindet das aktuelle Archivmedium an einen zusätzlichen Datensatz in dem der Eintrag kopiert wird 
	 * und die neue Tabelle und die ID in der Tabelle angepasst wird. 
	 * 
	 * @author manfred
	 * @date   22.03.2013
	 * @param sTableName
	 * @param sID
	 * @param bCommit
	 * @return
	 * @throws myException
	 */
	public boolean connectToAdditionalEntry(String sTableName, String sID, boolean bCommit) throws myException{
		return connectToAdditionalEntry(sTableName, sID, null, bCommit);
		//		
		//		String sSql = connectToAdditionalEntry(sTableName, sID);
		//		boolean bRet = bibDB.ExecSQL(sSql, bCommit);
		//		
		//		return bRet;

	}



	public boolean connectToAdditionalEntry(String sTableName, String sID, Archiver_CONST.MEDIENKENNER medienkenner, boolean bCommit ) throws myException{

		String sSql = connectToAdditionalEntry(sTableName, sID, medienkenner);
		boolean bRet = bibDB.ExecSQL(sSql, bCommit);

		return bRet;
	}


	/**
	 * Bindet das aktuelle Archivmedium an einen zusätzlichen Datensatz in dem der Eintrag kopiert wird 
	 * und die neue Tabelle und die ID in der Tabelle angepasst wird. 
	 * 
	 * Erzeugt nur das SQL-Statement und gibt es zurück
	 * 
	 * @author manfred
	 * @date   22.03.2013
	 * @param sTableName
	 * @param sID
	 * @return
	 * @throws myException
	 */
	public String connectToAdditionalEntry(String sTableName, String sID) throws myException{

		String sTableNameNormalized = new Archiver_Normalized_Tablename(sTableName).get_TableBaseName();
	
		return connectToAdditionalEntry(sTableNameNormalized, sID, null);
	}



	/**
	 * Bindet das aktuelle Archivmedium an einen zusätzlichen Datensatz in dem der Eintrag kopiert wird 
	 * und die neue Tabelle und die ID in der Tabelle angepasst wird. 
	 * 
	 * Erzeugt nur das SQL-Statement und gibt es zurück
	 * 
	 * @author manfred
	 * @date   22.03.2013
	 * @param sTableName
	 * @param sID
	 * @return
	 * @throws myException
	 */
	public String connectToAdditionalEntry(String sTableName, String sID, Archiver_CONST.MEDIENKENNER MedienkennerNew) throws myException{

		String sTableNameNormalized = new Archiver_Normalized_Tablename(sTableName).get_TableBaseName();
		
		MySqlStatementBuilder oStmtBuilder = this.get_StatementBuilderFilledWithActualValues(true);

		// die neuen Werte für die neue Vebindung
		oStmtBuilder.addSQL_Paar(RECORD_ARCHIVMEDIEN_ext.FIELD__ID_TABLE, sID,false);
		oStmtBuilder.addSQL_Paar(RECORD_ARCHIVMEDIEN_ext.FIELD__TABLENAME, sTableNameNormalized,true);
		if (MedienkennerNew != null){
			oStmtBuilder.addSQL_Paar(RECORD_ARCHIVMEDIEN_ext.FIELD__MEDIENKENNER, MedienkennerNew.toString(),true);
		}


		// den Sequenzer für die neue ID
		oStmtBuilder.addSQL_Paar(RECORD_ARCHIVMEDIEN_ext.FIELD__ID_ARCHIVMEDIEN, "SEQ_"+ RECORD_ARCHIVMEDIEN_ext.TABLENAME.substring(3) + ".NEXTVAL" , false);

		String sSql = oStmtBuilder.get_CompleteInsertString(RECORD_ARCHIVMEDIEN_ext.TABLENAME);

		return sSql;
	}









	/**
	 * falls der dateiname mit einer numerischen endung wie pdf_1 ... endet, dann die "_N" - anhaenge
	 * wieder rausnehmen
	 * @return
	 * @throws myException 
	 */
	public String get_DATEINAME_Orig() throws myException {
		String cNameSaved = this.get_DATEINAME_cUF_NN("");
		String cNameNew = cNameSaved;
		if (S.isFull(cNameSaved)) {
			if (cNameSaved.lastIndexOf("_")>0) {
				String nummer = cNameSaved.substring(cNameSaved.lastIndexOf("_")+1);

				MyLong lTest = new MyLong(nummer);

				if (lTest.get_bOK()) {
					cNameNew = cNameSaved.substring(0,cNameSaved.lastIndexOf("_"));
				}
			}
		}
		DEBUG.System_println("Originalwert: "+cNameNew);

		return cNameNew;

	}



	/**
	 * sucht das letzte "." im filename und schneidet davon links ab
	 * @return  z.B. von "scan1.pdf" "scan1"
	 * @throws myException 
	 */
	public String get__Base_Filename_Orig(String nameWhenEmpty) throws myException {
		String cNameSaved = this.get_DATEINAME_cUF_NN(nameWhenEmpty);
		String cNameNew = cNameSaved;

		int iPos = cNameNew.lastIndexOf(".");

		if (iPos>0) {
			cNameNew = cNameNew.substring(0,iPos);
		}

		return cNameNew;
	}





	public boolean is_PDF() throws myException {
		return this.get_DOWNLOADNAME_cUF_NN("-----").toUpperCase().endsWith(".PDF");
	}



	/**
	 * 
	 * @return buttonAuthValidator for download this file
	 * @throws myException 
	 */
	public XX_ActionValidator generate_ButtonValidator4Download() throws myException {

		//modulkenner fuer die validierer des download-buttons
		String modulKey4Validator = new __FINDER_MODULE_KEY(this.get_TABLENAME_cUF()).get_Key().db_val();
		String buttonKey4Validator = VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DOWNLOAD_ATTACHMENT.db_val();

		return new E2_ButtonAUTHValidator(modulKey4Validator, buttonKey4Validator);
	}




	public ImageReference  get_image() throws myException {
		ImageReference image = null;

		File oFileToShow = Archiver.get_File(this);

		if (this.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES()) {

			if (oFileToShow.exists()) {
				LoadImageNT  oLoad = new LoadImageNT(oFileToShow.getAbsolutePath());
				image = oLoad.get_ImageRef();
			} else {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anlage wurde nicht gefunden ...").CTrans()+oFileToShow.getAbsolutePath()));
			}

		} else {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anlage ist kein Bild ...").CTrans()+oFileToShow.getAbsolutePath()));
		}

		return image;
	}



	/**
	 * Wenn das bild breiter ist als maxWidth, dann wird seitenverhaeltnis-richtig scaliert
	 * @param maxWidth
	 * @return
	 * @throws myException
	 */
	public ImageReference  get_image(int iWidthMaximum) throws myException {
		ImageReference image = null;

		File oFileToShow = Archiver.get_File(this);

		if (this.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES()) {

			if (oFileToShow.exists()) {
				image = new LoadImageNT(oFileToShow.getAbsolutePath()).get_ImageRef(iWidthMaximum);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anlage wurde nicht gefunden ...").CTrans()+oFileToShow.getAbsolutePath()));
			}

		} else {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anlage ist kein Bild ...").CTrans()+oFileToShow.getAbsolutePath()));
		}

		return image;
	}


	/**
	 * Loescht den Datensatz und (wenn nicht anderweitig verkettet) die dateien
	 * @return messages
	 * @throws myException
	 */
	public MyE2_MessageVector DELETE_DatasetAndFilesWhenPossible() throws myException {

		MyE2_MessageVector  mv = this.DELETE();

		if (mv.get_bIsOK()) {

			new Archiver_Delete_File_WhenAllowed(	this.get_PFAD_cUF_NN("/TMP/SDSFSDAFSAFADFASDFTVHDHBFZUDN BH/"),
					this.get_DATEINAME_cUF_NN("!!!!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@"),
					true,
					mv);
		}
		return mv;
	}


	/**
	 * 
	 * @return true, wenn die datei eine pixeldatei ist
	 * @throws myException
	 */
	public boolean is_pixel_image() throws myException {
		boolean rueck = false;

		if (this.get_UP_RECORD_MEDIENTYP_id_medientyp()!=null && 
				this.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES()	) {
			rueck = true;
		}
		return rueck;
	}



	public boolean is_existing_in_filesystem() {
		try {
			return new File(this.get__cCompletePathAndFileName()).exists();
		} catch (myException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int[] getEmailStatus() throws myException{
		int [] mailInfo = {0,0};

		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append("SELECT " );
		sqlQuery.append("count("+ EMAIL_SEND_TARGETS.fullTabName() + "." +EMAIL_SEND_TARGETS.id_email_send+") ");
		sqlQuery.append(",count(case when "+ EMAIL_SEND_TARGETS.fullTabName() + "." + EMAIL_SEND_TARGETS.send_ok+"= 'Y' then 1 end) ");
		sqlQuery.append("from " + ARCHIVMEDIEN.fullTabName() + " ");
		sqlQuery.append("inner join " + EMAIL_SEND_ATTACH.fullTabName()+" ");
		sqlQuery.append(
				"on " + ARCHIVMEDIEN.fullTabName() + "." + ARCHIVMEDIEN.id_archivmedien + "=" 
						+ EMAIL_SEND_ATTACH.fullTabName() + "." + EMAIL_SEND_ATTACH.id_archivmedien);
		sqlQuery.append(" inner join " + EMAIL_SEND_TARGETS.fullTabName() + " ");
		sqlQuery.append("on " + EMAIL_SEND_TARGETS.fullTabName()+"."+EMAIL_SEND_TARGETS.id_email_send + "="
						+EMAIL_SEND_ATTACH.fullTabName()+ "." + EMAIL_SEND_ATTACH.id_email_send);
		sqlQuery.append(" where " + ARCHIVMEDIEN.fullTabName()+"."+ARCHIVMEDIEN.tablename + "='" + this.get_TABLENAME_cUF());
		sqlQuery.append("' and " + ARCHIVMEDIEN.fullTabName()+"."+ARCHIVMEDIEN.id_table + "=" + this.get_ID_TABLE_cUF());
		
		sqlQuery.toString();
		
 		String[][] queryResponse = bibDB.EinzelAbfrageInArray(sqlQuery.toString());
		mailInfo[0] = Integer.valueOf(queryResponse[0][0]);
		mailInfo[1] = Integer.valueOf(queryResponse[0][1]);

		return mailInfo;
	}

	public void is_email_existing(){

	}

	public void is_email_send(){

	}

}
