package panter.gmbh.indep.archive;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import panter.gmbh.indep.pdf.PDF_GeneratePdf_With_Images;
import panter.gmbh.indep.pdf.pdfConcat;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


public class ArchiverConcatenated_PDF_and_PIXELFiles {

		
	public ArchiverConcatenated_PDF_and_PIXELFiles() {
		super();
	}
	
	/**
	 * 
	 * @param vArchivmedienRG
	 * @param suppressAddingOfOriginalFile
	 * @param mv_collector
	 * @param baseFileNameOfDownloadFile
	 * @return
	 * @throws myException
	 */
	public myTempFile  generate_ConcatenatedFile(	ArrayList<RECORD_ARCHIVMEDIEN> 	vArchivmedienRG,
													MyE2_MessageVector 				mv_collector,
													String              			baseFileNameOfDownloadFile) throws myException {
		
		
		Vector<String> v_filenamesOfPdfFiles = new Vector<String>();
		Vector<myTempFile>  v_tempfiles_pdfs_created_from_bitmap = new Vector<myTempFile>();
		
		for (RECORD_ARCHIVMEDIEN rao: vArchivmedienRG) {
			RECORD_ARCHIVMEDIEN_ext  rae = new RECORD_ARCHIVMEDIEN_ext(rao);
			
			if (rae.is_IST_ORIGINAL_YES() && rae.is_PDF()) {
				//dann stempeln
				v_filenamesOfPdfFiles.add(rae.generate_tempFileCopy(true).getFileName());
				continue;
			} else if (	rae.is_IST_ORIGINAL_YES()) {
				//kein PDF mit original wird uebersprungen
				continue;
			}
			
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
		
		myTempFile  tempFile = 	null;

		if (v_filenamesOfPdfFiles.size()>0) {
			tempFile = new pdfConcat(v_filenamesOfPdfFiles).baueZielDatei(baseFileNameOfDownloadFile);
			tempFile.set_bDeleteAtFinalize(true);
		}

		return tempFile;
		
	}
	
}
