/*
 * Created on 01.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.indep.ZIP;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import panter.gmbh.indep.exceptions.myException;


/*
 *
 */
public class myZipper extends ZipOutputStream {

	
	/*
	 * sammelt alle dateinamen in diesem Zipper
	 */
	private Vector<String> vUsedFiles = new Vector<String>();
	
	private FileOutputStream oFileOutStream = null;

	public myZipper(FileOutputStream ofileOutStream) 
	{
		super(ofileOutStream);
		this.oFileOutStream = ofileOutStream;
	}
	
	
	public void addFileToZip( String		  cZIP_Eintrag,
							  String		  cFileName)
					   throws myException
	{
		FileInputStream zipIn = null;

		this.vUsedFiles.add(cFileName);
		
		try
		{
			byte[] buff = new byte[4096];
			int    len = 0;

			zipIn = new FileInputStream(cFileName);
			this.putNextEntry(new ZipEntry(cZIP_Eintrag));

			while ((len = zipIn.read(buff)) > 0)
			{
				this.write(buff, 0, len);
			}

			zipIn.close();
			zipIn = null;
			this.flush();
		}
		catch (Exception ex)
		{
			/*
			 * sicherstellen, dass zipIn wieder geschlossen wird
			 */
			try
			{
				if (zipIn != null)
				{
					zipIn.close();
					zipIn = null;
				}
			}
			catch (Exception exx) {}

			throw new myException("Fehler beim anhängen der Datei " + cFileName + " an das Rückgabe-Zip");
		}
	}

	
	public void addFileToZip( String	cZIP_Eintrag,
							  File		oFile)
	   throws myException
	{
		FileInputStream zipIn = null;
		
		this.vUsedFiles.add(cZIP_Eintrag);
		
		try
		{
			byte[] buff = new byte[4096];
			int    len = 0;
			
			zipIn = new FileInputStream(oFile);
			this.putNextEntry(new ZipEntry(cZIP_Eintrag));
			
			while ((len = zipIn.read(buff)) > 0)
			{
			this.write(buff, 0, len);
			}
			
			zipIn.close();
			zipIn = null;
			this.flush();
		}
		catch (Exception ex)
		{
			/*
			* sicherstellen, dass zipIn wieder geschlossen wird
			*/
			try
			{
				if (zipIn != null)
				{
					zipIn.close();
					zipIn = null;
				}
			}
			catch (Exception exx) {}
		
			throw new myException("Fehler beim anhängen der Datei " + oFile.getName() + " an das Rückgabe-Zip");
		}
	}

	
	
	protected void finalize()
	{
		if (this.oFileOutStream != null)
		{
			try
			{
				this.oFileOutStream.close();
				this.oFileOutStream = null;
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	
	
	
	
	public String get_NotDoubleFileName(String cName)
	{
		String cRueck = cName;
		if (this.vUsedFiles.contains(cRueck))
		{
			// dann neuen namen erzeugen durch anhaengen eines _ an den namen
			for (int s=0;s<20;s++)
			{
				cRueck=cRueck+"_";
				if (!this.vUsedFiles.contains(cRueck))
				{
					break;
				}
			}
		}
		return cRueck;
	}
	


}
