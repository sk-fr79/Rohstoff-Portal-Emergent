package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.io.File;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;



/*
 * spezielle file-version, die einen korrekten mail-anlage-namen mitfuehrt 
 * (statt der oft langen temp-file-names)
 */
public class FileWithSendName extends File 
{
	private String 			cNameFor_USER_EmailAttachment = null;
	private JasperFileDef	oJasperFileDef = null;
	private String 			cNameWithPath = null;
	
	
	//weiterer konstuktor fuer filewithSendName, damit kann das automatische loeschen verhindert werden, da das TempFile hier mitgespeichert wird
	private  myTempFile     oTempFile = null;
	
	public FileWithSendName(myTempFile  o_TempFile, String NameForEmailAttachment, JasperFileDef	jasperFileDef)  throws myException
	{
		super(o_TempFile.getFileName());
		
		this.oJasperFileDef = jasperFileDef;
		this.oTempFile = o_TempFile;

		if (this.oJasperFileDef==null)
			throw new myException(this,"JasperFileDef MUST be defined !!");
		
		this.cNameWithPath = oTempFile.getFileName();
		
		this.cNameFor_USER_EmailAttachment = NameForEmailAttachment;
		
		if (!this.exists())
			throw new myException("E2_FileWithSendName:"+this.cNameWithPath+" is not existing !!!");
		
		if (bibALL.isEmpty(this.cNameFor_USER_EmailAttachment))
			this.cNameFor_USER_EmailAttachment = this.getName();
		
		if (bibALL.isEmpty(this.cNameFor_USER_EmailAttachment))
			throw new myException("E2_FileWithSendName: NameForEmailAttachment MUST NOT BE EMPTY!!!");
		
	}
	
	public FileWithSendName(String pathname, String NameForEmailAttachment, JasperFileDef	jasperFileDef)  throws myException
	{
		super(pathname);
		
		this.oJasperFileDef = jasperFileDef;

		if (this.oJasperFileDef==null)
			throw new myException(this,"JasperFileDef MUST be defined !!");

		
		this.cNameWithPath = pathname;
		
		this.cNameFor_USER_EmailAttachment = NameForEmailAttachment;
		
		if (!this.exists())
			throw new myException("E2_FileWithSendName:"+pathname+" is not existing !!!");
		
		if (bibALL.isEmpty(this.cNameFor_USER_EmailAttachment))
			this.cNameFor_USER_EmailAttachment = this.getName();
		
		if (bibALL.isEmpty(this.cNameFor_USER_EmailAttachment))
			throw new myException("E2_FileWithSendName: NameForEmailAttachment MUST NOT BE EMPTY!!!");
		
	}

	
	public String get_cNameFor_USER_EmailAttachment() 
	{
		return cNameFor_USER_EmailAttachment;
	}

	
	public void Download_File() throws myException
	{
		new E2_Download().starteFileDownloadWithEX(this.cNameWithPath,this.cNameFor_USER_EmailAttachment,this.oJasperFileDef.MimeType);
	}


	public String get_cNameWithPath() 
	{
		return cNameWithPath;
	}
	
	
	public JasperFileDef get_oJasperFileDef()
	{
		return oJasperFileDef;
	}

	public void set_oJasperFileDef(JasperFileDef jasperFileDef)
	{
		oJasperFileDef = jasperFileDef;
	}

	public myTempFile get_oTempFile() {
		return oTempFile;
	}


	
	
}
