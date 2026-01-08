package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.File;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.exceptions.myException;

public class DownLoader 
{
	private RECORD_ARCHIVMEDIEN  recArchivmedienDown = null;

	public DownLoader(RECORD_ARCHIVMEDIEN recArchivmedien) 
	{
		super();
		this.recArchivmedienDown = recArchivmedien;
		
		try
		{
			String cMimeTyp = "";
			
			if (this.recArchivmedienDown.get_UP_RECORD_MEDIENTYP_id_medientyp()!=null)
			{
				cMimeTyp = "application/"+this.recArchivmedienDown.get_UP_RECORD_MEDIENTYP_id_medientyp().get_DATEIENDUNG_cUF_NN("bin");
			}
			
			String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
			String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(this.recArchivmedienDown.get_PFAD_cUF());
			String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(this.recArchivmedienDown.get_DATEINAME_cUF());
			String cDownloadName	=  	Archiver.truncate_FileSeparatorsFromPath(this.recArchivmedienDown.get_DOWNLOADNAME_cUF_NN(this.recArchivmedienDown.get_DATEINAME_cUF()));
			String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
		
			E2_Download oDownload = new E2_Download();
			oDownload.starteFileDownload(cCompletePath,cDownloadName,cMimeTyp);
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("BS_POPUP_DownloadDruckbelege:  Error in Downloading !!"+ex.ErrorMessage),false));
		}
	}
	

}
