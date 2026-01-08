package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import panter.gmbh.indep.pdf.PDF_GeneratePdf_With_Images;
import panter.gmbh.indep.pdf.pdfConcat;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class RGOM___GenerateSingleConcatenatedPdf {

	private RECORD_VKOPF_RG 				RecRG = 	null;
	private myTempFile                      tempFile = 	null;

	/**
	 * generates concatenated pdf from all pdf witch are attached for email
	 * @param recRG
	 * @param suppressAddingOfOriginalFile
	 * @param mv_collector
	 * @throws myException
	 */
	public RGOM___GenerateSingleConcatenatedPdf(	RECORD_VKOPF_RG 	recRG, 
													boolean 			suppressAddingOfOriginalFile,
													MyE2_MessageVector 	mv_collector) throws myException {
		super();
		this.RecRG = recRG;
		
		//hier haengen alle archivmedien dran
		ArrayList<RECORD_ARCHIVMEDIEN> vArchivmedienRG = new RGOM___Attach_Missing_and_Collect_Relevant_Attachements_4_MAIL(this.RecRG, mv_collector).get_v_collected_ArchivMedien();
		
		Vector<String> v_filenamesOfPdfFiles = new Vector<String>();
		Vector<myTempFile>  v_tempfiles_pdfs_created_from_bitmap = new Vector<myTempFile>();
		
		for (RECORD_ARCHIVMEDIEN ra: vArchivmedienRG) {
			if (ra.is_IST_ORIGINAL_YES() && suppressAddingOfOriginalFile) {
				continue;
			}
			
			RECORD_ARCHIVMEDIEN_ext rae = new RECORD_ARCHIVMEDIEN_ext(ra);
			if (rae.is_PDF()) {
				v_filenamesOfPdfFiles.add(rae.get__cCompletePathAndFileName());
			} else {
				//falls es bitmaps sind, diese in pdf wandeln und auch verarbeiten
				//pro bild ein pdf
				try {
					if (rae.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES()) {
						myTempFile tfBild = new PDF_GeneratePdf_With_Images(bibVECTOR.get_Vector(rae.get__cCompletePathAndFileName()), rae.get__Base_Filename_Orig(""), false, 500).generate_pdf_with_images();
						v_tempfiles_pdfs_created_from_bitmap.add(tfBild);
						tfBild.set_bDeleteAtFinalize(true);
						v_filenamesOfPdfFiles.add(tfBild.getFileName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (v_filenamesOfPdfFiles.size()>0) {
			String cAttachmentFileName = "ATTACH_RG_BN_"+recRG.get_BUCHUNGSNUMMER_cUF_NN("_ID_"+recRG.get_ID_VKOPF_RG_cUF())+"_ADRESSID_"+recRG.get_ID_ADRESSE_cUF_NN("");
			this.tempFile = new pdfConcat(v_filenamesOfPdfFiles).baueZielDatei(cAttachmentFileName);
			this.tempFile.set_bDeleteAtFinalize(true);
		}
	}

	

	/**
	 * 
	 * @return concatenated pdf or null when nothing was found
	 */
	public myTempFile get_TempFile() {
		return tempFile;
	}
	

	
	
	
	
}
