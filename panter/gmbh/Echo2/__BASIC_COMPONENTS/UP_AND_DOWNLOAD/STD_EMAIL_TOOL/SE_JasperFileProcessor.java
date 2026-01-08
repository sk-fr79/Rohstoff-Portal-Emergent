package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class SE_JasperFileProcessor {
	
	private SE_JasperHASH_4_ArchivPDF 	jasperHash = null;

	public SE_JasperFileProcessor(SE_JasperHASH_4_ArchivPDF p_jasperHash) {
		super();
		this.jasperHash = p_jasperHash;
	}
	
//	public MyE2_MessageVector do_processing_preview() throws myException {
//		MyE2_MessageVector  mv = new MyE2_MessageVector();
//
//		
//		//zuerst die tempdatei erzeugen
//		if (this.jasperHash==null) {
//			throw new myException(this,"Error: jasperHash is null !!");
//		}
//		
//		this.jasperHash.set_bVorschauDruck(true);
//		this.jasperHash.set_TYPE_MAIL();
//		this.jasperHash.Build_tempFile(true);
//		
//		if (this.jasperHash.get_jasperTempFile_and_pipePos_VECT_standard().size()>0) {
//			this.tempfile = this.jasperHash.get_jasperTempFile_and_pipePos_VECT_standard().get_vJasperTempFile().get_myTempFileAllConcated(this.jasperHash.get_table().baseTableName()+bibALL.get_cDateNOWInverse("-"));
//			this.tempfile.starteDownLoad(this.jasperHash.get_table().baseTableName()+bibALL.get_cDateNOWInverse("-")+".pdf", JasperFileDef.MIMETYP_PDF);
//			this.tempfile.set_bDeleteAtFinalize(true);
//		}
//				
//		return mv;
//	}
	
	/**
	 * 
	 * @param mv
	 * @param preview
	 * @return
	 * @throws myException
	 */
	public myTempFile do_processing_print_or_preview(MyE2_MessageVector  mv, boolean preview) throws myException {

		//zuerst die tempdatei erzeugen
		if (this.jasperHash==null) {
			throw new myException(this,"Error: jasperHash is null !!");
		}
		
		this.jasperHash.set_bVorschauDruck(preview);
		if(preview) {
			this.jasperHash.set_TYPE_PREVIEW();
		}else {
			this.jasperHash.set_TYPE_PRINT();
		}
		this.jasperHash.Build_tempFile(true);
		
		myTempFile file_ret = null;
		
		if (this.jasperHash.get_jasperTempFile_and_pipePos_VECT_standard().size()>0) {
			file_ret = this.jasperHash.get_jasperTempFile_and_pipePos_VECT_standard().get_vJasperTempFile().get_myTempFileAllConcated(this.jasperHash.get_table().baseTableName()+bibALL.get_cDateNOWInverse("-"));
		}
		return file_ret;
	}

	public void do_processing_mail(MyE2_MessageVector  mv) throws myException {
		//zuerst die tempdatei erzeugen
		if (this.jasperHash==null) {
			throw new myException(this,"Error: jasperHash is null !!");
		}
		
		this.jasperHash.set_bVorschauDruck(false);
		this.jasperHash.set_TYPE_MAIL();
		this.jasperHash.Build_tempFile(false);
		
		//hier muss nichts weiter passieren, da im Build_tempFile das archiv-file angehaengt wird
	}

}
