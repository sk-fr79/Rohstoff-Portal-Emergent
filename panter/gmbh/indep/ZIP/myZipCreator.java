package panter.gmbh.indep.ZIP;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName_Vector;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class myZipCreator 
{
	
	private FileWithSendName_Vector   	vFilesWithSendName = null;
	private String    					cNameOfNewZipFile =null;
	private myTempFile  				oTempZipFile = null;
	
	public myZipCreator(FileWithSendName_Vector v_filesWithSendName) throws FileNotFoundException, myException
	{
		super();
		this.vFilesWithSendName = v_filesWithSendName;
		
		this._createZip("archiv");
	}


	
	public myZipCreator(FileWithSendName_Vector v_filesWithSendName, String cArchivname) throws FileNotFoundException, myException
	{
		super();
		this.vFilesWithSendName = v_filesWithSendName;
		
		String cName = cArchivname;
		
		if (cName.toUpperCase().endsWith(".ZIP"))
		{
			cName = cName.substring(0,cName.length()-4);
		}
		
		this._createZip(cName);
	}

	
	
	private void _createZip(String cNameWithoutEnding) throws FileNotFoundException, myException
	{
		myZipper  	oZip = null;
		this.oTempZipFile = null;

		try
		{
			this.oTempZipFile = new myTempFile(cNameWithoutEnding,".zip",true);
			
			oZip = new myZipper(new FileOutputStream(this.oTempZipFile.getFileName()));
	
			for (FileWithSendName ofile: this.vFilesWithSendName)
			{
				oZip.addFileToZip(oZip.get_NotDoubleFileName(ofile.get_cNameFor_USER_EmailAttachment()), ofile);
			}
			
			this.cNameOfNewZipFile = this.oTempZipFile.getFileName();
			oZip.close();
			this.oTempZipFile.close();
			
		}
		catch (Exception ex)
		{
			throw new myException(this,ex.getLocalizedMessage());
		}
		finally
		{
			if (oZip != null)
			{
				try
				{
					oZip.close();
					oZip = null;
				}
				catch (Exception e) {}
			}
			if (this.oTempZipFile != null)
			{
				try
				{
					this.oTempZipFile.close();
//					outFile = null;
				}
				catch (Exception e) {}
			}

		}

	}
	
	
	public String get_NameOfNewZipFile()
	{
		return cNameOfNewZipFile;
	}
	
	public myTempFile  get_oTempZipFile()
	{
		return this.oTempZipFile;
	}
	
	
}
