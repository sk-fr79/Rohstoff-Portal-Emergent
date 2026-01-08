package rohstoff.Echo2BusinessLogic._4_ALL;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.pdf.pdfConcat;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;


/**
 * 2014-11-05: uebernimmt eine VectorSingle mit ID_VKOPF_RG unformated und 
 * holt sich die passenden Archiv-Dateien aus dem JT_ARCHIV und gibt diese verkettet zurueck
 * @author martin
 *
 */
public class Generate_Concatenated_InvoiceArchiveLists {

	private VectorSingle 		v_ID_VKOPF_RG = 							new VectorSingle();
	private boolean      		b_AllowEmptyArchives = 						false;	
	private Vector<String>		v_GesammeltePfadeFoundMedium = 				new Vector<String>();
	private Vector<String>		v_ID_VKOPF_RG_HasNo_Archiv_Dataset = 			new Vector<String>();
	private Vector<String>		v_ID_VKOPF_RG_FileFromDatasetNotFound = 	new Vector<String>();
	private myTempFile		   	o_TempFile = null;
	
	private String fileNamePrefix = "RECHNUNGS_SAMMLER";
	
	public void setFileNamePrefix(String prefix) {
		fileNamePrefix = prefix;
	}
	
	
	public Generate_Concatenated_InvoiceArchiveLists(Collection<String> v_ID_VKOPF_RG, boolean bAllowEmptyArchives) {
		super();
		this.v_ID_VKOPF_RG.addAll(v_ID_VKOPF_RG);
		this.b_AllowEmptyArchives = bAllowEmptyArchives;
	}

	public Generate_Concatenated_InvoiceArchiveLists() {
		super();
	}
	
	public MyE2_MessageVector DO_Concatenate() throws myException {
		
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();
		
		
		
//		for (String cID_VKOPF_RG: v_ID_VKOPF_RG) {
//			
//			RECORD_VKOPF_RG_ext  recVKOPF = new RECORD_VKOPF_RG_ext(cID_VKOPF_RG);
//			
//			RECORD_ARCHIVMEDIEN oRecArchivMedien = recVKOPF.get_RECORD_ARCHIVMEDIEN_NEWEST_NOT_ORIGINAL_SINGULAR();
//			
//			if (oRecArchivMedien==null ) {
//				this.v_ID_VKOPF_RG_HasNo_Archiv_Dataset.add(cID_VKOPF_RG);
//			} else {
//			
//				RECORD_ARCHIVMEDIEN_ext  oRecArchivMedExt = new RECORD_ARCHIVMEDIEN_ext(oRecArchivMedien);
//				
//				String cPFAD = oRecArchivMedExt.get__cCompletePathAndFileName();
//				
//				if (new File(cPFAD).exists()) {
//					this.v_GesammeltePfadeFoundMedium.add(cPFAD);
//				} else {
//					this.v_ID_VKOPF_RG_FileFromDatasetNotFound.add(cID_VKOPF_RG);
//				}
//			}
//		}

		//neu ab 2015-06-12: es koennen mehrere archivdateien am druckprotokoll haengen
		for (String cID_VKOPF_RG: v_ID_VKOPF_RG) {
			
			RECORD_VKOPF_RG_ext  recVKOPF = new RECORD_VKOPF_RG_ext(cID_VKOPF_RG);
			
			Vector<RECORD_ARCHIVMEDIEN> v_rec_archiv_medien = recVKOPF.get_RECORD_ARCHIVMEDIEN_NEWEST_NOT_ORIGINAL(false);
			
			if (v_rec_archiv_medien==null || v_rec_archiv_medien.size()==0 ) {
				this.v_ID_VKOPF_RG_HasNo_Archiv_Dataset.add(cID_VKOPF_RG);
			} else {
				
				for (RECORD_ARCHIVMEDIEN ra: v_rec_archiv_medien) {
				
					RECORD_ARCHIVMEDIEN_ext  oRecArchivMedExt = new RECORD_ARCHIVMEDIEN_ext(ra);
					
					String cPFAD = oRecArchivMedExt.get__cCompletePathAndFileName();
					
					if (new File(cPFAD).exists()) {
						this.v_GesammeltePfadeFoundMedium.add(cPFAD);
					} else {
						if (!this.v_ID_VKOPF_RG_FileFromDatasetNotFound.contains(cID_VKOPF_RG)) {   //bei mehrfach-dateieintraegen am  druckprotokoll nur eine erwaehnung
							this.v_ID_VKOPF_RG_FileFromDatasetNotFound.add(cID_VKOPF_RG);
						}
					}
				}
			}
		}

		
		
		if (this.v_ID_VKOPF_RG_HasNo_Archiv_Dataset.size()>0) {
			if (this.b_AllowEmptyArchives) {
				oMV_Rueck.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Zu folgenden Rechnungen existiert KEIN Archiveintrag: ",true,
							bibALL.Concatenate(this.v_ID_VKOPF_RG_HasNo_Archiv_Dataset, "/", "-"),false)));
			} else {
				oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zu folgenden Rechnungen existiert KEIN Archiveintrag: ",true,
							bibALL.Concatenate(this.v_ID_VKOPF_RG_HasNo_Archiv_Dataset, "/", "-"),false)));
			}
		}
		
		
		if (this.v_ID_VKOPF_RG_FileFromDatasetNotFound.size()>0) {
			if (this.b_AllowEmptyArchives) {
				oMV_Rueck.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Zu folgenden Rechnungen existiert ein Archiveintrag , aber keine Datei: ",true,
							bibALL.Concatenate(this.v_ID_VKOPF_RG_FileFromDatasetNotFound, "/", "-"),false)));
			} else {
				oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zu folgenden Rechnungen existiert ein Archiveintrag , aber keine Datei: ",true,
							bibALL.Concatenate(this.v_ID_VKOPF_RG_FileFromDatasetNotFound, "/", "-"),false)));
			}

		}
		
		
		if (this.v_GesammeltePfadeFoundMedium.size()>0) {
			pdfConcat oPDF_Concat = new pdfConcat(this.v_GesammeltePfadeFoundMedium);
			
			this.o_TempFile = oPDF_Concat.baueZielDatei(fileNamePrefix);
			this.o_TempFile.set_bDeleteAtFinalize(true);
		}
		
		return oMV_Rueck;
		
	}

	public VectorSingle get_vID_VKOPF_RG() {
		return this.v_ID_VKOPF_RG;
	}

	public Vector<String> get_vGesammeltePfade() {
		return v_GesammeltePfadeFoundMedium;
	}

	/**
	 * 
	 * @return myTempFile or null when nothing was found 
	 */
	public myTempFile get_oTempFile() {
		return o_TempFile;
	}

	public void set_bAllowFindNotAll(boolean b_AllowFindNotAll) {
		this.b_AllowEmptyArchives = b_AllowFindNotAll;
	}
	
	
	
	
	
}
