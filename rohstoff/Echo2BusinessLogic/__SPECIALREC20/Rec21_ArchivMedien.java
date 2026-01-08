package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.io.File;

import com.lowagie.text.pdf.BaseFont;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__FINDER_MODULE_KEY;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.MEDIENTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.LoadImageNT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory;

public class Rec21_ArchivMedien extends Rec21 {

	public Rec21_ArchivMedien() throws myException {
		super(_TAB.archivmedien);
	}
	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_ArchivMedien(Rec21 rec) throws myException {
		super(rec);
		
		if (!rec.get_TABLENAME().equals(_TAB.archivmedien.n())) {
			throw new myException("Rec21_ArchivMedien: false table");
		}
	}

	
	public ImageReference  get_image() throws myException {
		ImageReference image = null;

		File oFileToShow = Archiver.getFile(this);

		if (this.get_up_Rec21(MEDIENTYP.id_medientyp).is_yes_db_val(MEDIENTYP.ist_pixelimage)) {

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

		File oFileToShow = Archiver.getFile(this);

		if (this.get_up_Rec21(MEDIENTYP.id_medientyp).is_yes_db_val(MEDIENTYP.ist_pixelimage)) {

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


	public XX_ActionValidator generate_ButtonValidator4Download() throws myException {

		//modulkenner fuer die validierer des download-buttons
		String modulKey4Validator = new __FINDER_MODULE_KEY(this.get_tab().baseTableName()).get_Key().db_val();
		String buttonKey4Validator = VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DOWNLOAD_ATTACHMENT.db_val();

		return new E2_ButtonAUTHValidator(modulKey4Validator, buttonKey4Validator);
	}

	
	public String getCompletePathAndFileName() throws myException	{
		String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());

		String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(this.getUfs(ARCHIVMEDIEN.pfad,""));
		String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(this.getUfs(ARCHIVMEDIEN.dateiname,""));
		String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;

		return cCompletePath;
	}

	public String getCompletePathAndFileName(String whenException)	{
		try {
			return this.getCompletePathAndFileName();
		} catch (myException e) {
			e.printStackTrace();
			return whenException;
		}
	}
	
	
	
	public void starteDownLoad() throws myException 	{
		
		Rec20 recMedienTyp = this.get_up_Rec20(MEDIENTYP.id_medientyp); 
		
		String cEndung = recMedienTyp.getUfs(MEDIENTYP.dateiendung,"");

		String cMimeTyp = "application/bin";

		if (S.isFull(cEndung)) 	{
			cMimeTyp = "application/"+cEndung;
		}


		boolean download_immer_erlauben = bib_Settigs_Mandant.IS__Value("DOWNLOAD_ORIGINAL_RG_ERLAUBEN_FUER_GF", "N", "N") && 
				bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES();


		if (	this.is_yes_db_val(ARCHIVMEDIEN.ist_original) && 
				recMedienTyp !=null &&
				recMedienTyp.getUfs(MEDIENTYP.dateiendung,"").toUpperCase().endsWith("PDF") && 
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


			myTempFile t = fact.generatePDFwithOverlayText(this.getCompletePathAndFileName() ,cWasserzeichenText , 1, f_WasserzeichenRotate);
			t.set_bDeleteAtFinalize(true);
			t.starteDownLoad(this.getUfs(ARCHIVMEDIEN.downloadname,"downFile"),cMimeTyp);
		} else {
			E2_Download oDownload = new E2_Download();
			oDownload.starteFileDownload(this.getCompletePathAndFileName(),this.getUfs(ARCHIVMEDIEN.downloadname,"downFile"),cMimeTyp);
		}

	}

	
	
	
	public String getName4User() {
		try {
			String name = this.getUfs(ARCHIVMEDIEN.downloadname);
			if (S.isEmpty(name)) {
				name = this.getUfs(ARCHIVMEDIEN.dateiname);
			}
			if (S.isEmpty(name)) {
				name = "File: "+this.getId();
			}
			return name;
		} catch (myException e) {
			return "<error reading name>";
		}
	}
	
	
}
