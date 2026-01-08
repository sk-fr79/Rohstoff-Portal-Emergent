package panter.gmbh.indep.ZIP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import panter.gmbh.indep.exceptions.myException;


public class MyUnzipper 
{
	private String 	cFileName = null;
	private String 	cCompletePath = null;
	private boolean bZipOK = false;
	private int 	iNumberUnzipped = 0;
	
	
	public MyUnzipper(String completePath,String cFilename) throws myException 
	{
		super();
		this.cCompletePath 	= completePath;
		if (!this.cCompletePath.endsWith(File.separator))
			this.cCompletePath+=File.separator;
		
		this.cFileName 		= cFilename;
		
		File oFile = new File(this.cCompletePath+this.cFileName);
		if (!oFile.exists())
			throw new myException(this,":"+this.cCompletePath+this.cFileName+": does not exist !!");
		
		try
		{
			ZipFile oZf = new ZipFile(this.cCompletePath+this.cFileName);

			Enumeration  entries = oZf.entries();

			
			while(entries.hasMoreElements()) 
			{
				ZipEntry entry = (ZipEntry)entries.nextElement();
				this.iNumberUnzipped++;
				
				if(entry.isDirectory()) 
				{
					throw new myException(this,":Zip-Archives with directory-structure are not allowed !!");
				}

				copyInputStream(oZf.getInputStream(entry),
						new BufferedOutputStream(new FileOutputStream(completePath+entry.getName())));
			}

			oZf.close();
			this.bZipOK = true;
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			throw new myException(this,": IOException:"+ex.getLocalizedMessage());
		}
		
	}
	
	
	  private void copyInputStream(InputStream in, OutputStream out) throws IOException
	  {
	    byte[] buffer = new byte[1024];
	    int len;

	    while((len = in.read(buffer)) >= 0)
	      out.write(buffer, 0, len);

	    in.close();
	    out.close();
	  }


	public boolean get_bZipOK() 
	{
		return bZipOK;
	}


	public String get_cCompletePath() 
	{
		return cCompletePath;
	}

	public String get_cFilename() 
	{
		return this.cFileName;
	}

	public int get_iNumberUnzipped() 
	{
		return iNumberUnzipped;
	}

	
	
}
