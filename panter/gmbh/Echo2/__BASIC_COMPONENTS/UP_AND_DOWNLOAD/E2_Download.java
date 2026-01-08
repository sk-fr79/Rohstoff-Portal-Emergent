

package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JasperPrint;
import nextapp.echo2.app.filetransfer.Download;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;


/*
 * 
 * @author martin
 *
 * abgeleitete download-klasse mit download-methode
 * 
 */

public class E2_Download  extends Download {

	
	public E2_Download()
	{
		super();
		
	}
	
	/*
	 * variante datei-download mit separatem ziel-dateiname
	 */
	public void starteFileDownload(String cFileNameWithPath, String cDownloadFileName, String cContentType)
	 {
		 try
		 {
			 E2_DownloadProviderForFiles provider = new E2_DownloadProviderForFiles(cFileNameWithPath,cDownloadFileName, cContentType);
			 provider.setFileNameProvided(true);
			
			 this.setProvider(provider);
			 bibE2.get_ActiveInstance().enqueueCommand(this);
			 this.setActive(true);
		 }
		 catch (FileNotFoundException ex)
		 {
			 bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Download-ERROR! Dokument "+cFileNameWithPath+" not found !!",false)));
		 }
	 }


	/*
	 * variante datei-download mit separatem ziel-dateiname (Varinate schmeisst exception)
	 */
	public void starteFileDownloadWithEX(String cFileNameWithPath, String cDownloadFileName, String cContentType) throws myException
	 {
		 try
		 {
			 E2_DownloadProviderForFiles provider = new E2_DownloadProviderForFiles(cFileNameWithPath,cDownloadFileName, cContentType);
			 provider.setFileNameProvided(true);
			
			 this.setProvider(provider);
			 bibE2.get_ActiveInstance().enqueueCommand(this);
			 this.setActive(true);
		 }
		 catch (FileNotFoundException ex)
		 {
			 throw new myException("E2_Download:starteFileDownloadWithEX: Error: "+ex.getLocalizedMessage());
		 }
	 }

	
	
	
	
	
	/*
	 * variante datei-download ohne separatem ziuel-dateiname
	 */
	public void starteFileDownload(String cFileNameWithPath, String cContentType)
	 {
		 try
		 {
			 E2_DownloadProviderForFiles provider = new E2_DownloadProviderForFiles(cFileNameWithPath,cFileNameWithPath,cContentType);
			 provider.setFileNameProvided(true);
			
			 this.setProvider(provider);
			 bibE2.get_ActiveInstance().enqueueCommand(this);
			 this.setActive(true);
		 }
		 catch (FileNotFoundException ex)
		 {
			 ex.printStackTrace();
			 bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Download-Fehler! Das Dokument "+cFileNameWithPath+" ist nicht vorhanden!"));
		 }
	 }
	
	
	
	

	/*
	 * variante download eines jasper-streams
	 */
	public void starteJasperStreamDownload(JasperPrint oJasperPrint, String cContentType, String cTargetMimeType, String cDownloadFileName)
	 {
		 E2_DownloadProviderForJasperStreams provider = new E2_DownloadProviderForJasperStreams(oJasperPrint, cContentType, cTargetMimeType,cDownloadFileName);
		 provider.setFileNameProvided(true);
			
		 this.setProvider(provider);
		 bibE2.get_ActiveInstance().enqueueCommand(this);
		 this.setActive(true);
	 }
	

}
