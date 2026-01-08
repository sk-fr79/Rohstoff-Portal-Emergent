package panter.gmbh.indep.ZIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class ZIP_NG_Creator 
{
	
	private Vector<ZIP_NG_NamePair> v_Names  = new Vector<ZIP_NG_NamePair>();
	
	private String    				c_NewFileName4Download =null;
	private myTempFile  			o_TempZipFile = null;
	
	/**
	 * 
	 * @param vNames ist ein Vector mit namenspaaren (name im Archiv und name der bestehenden datei, jeweils mit Endung)
	 * @param cNewFileName4Download ist der gewuenschte Downloadname OHRN Endung (es wird .zip angehaengt)
	 * @throws FileNotFoundException
	 * @throws myException
	 */
	public ZIP_NG_Creator(Vector<ZIP_NG_NamePair> vNames, String cNewFileName4Download) throws  myException
	{
		super();
		this.v_Names = vNames;
		this.c_NewFileName4Download = cNewFileName4Download;
		if (this.c_NewFileName4Download.toUpperCase().endsWith(".ZIP")) {
			this.c_NewFileName4Download=this.c_NewFileName4Download.substring(0,this.c_NewFileName4Download.length()-4);
		}
		
		this.o_TempZipFile = new myTempFile(this.c_NewFileName4Download, ".zip", true);
		this.o_TempZipFile.set_bDeleteAtFinalize(true);
		
		try {
			this._createZip("archiv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		}
	}


	

	
	private void _createZip(String cNameWithoutEnding) throws FileNotFoundException, myException
	{
		myZipper  	oZip = null;
		
		try {
			oZip = new myZipper(new FileOutputStream(this.o_TempZipFile.getFileName()));
		
	
			for (ZIP_NG_NamePair oPair: this.v_Names)
			{
				oZip.addFileToZip(oZip.get_NotDoubleFileName(oPair.get_cNameInZip()), new File(oPair.get_cNameRealInFileSystem()));
			}
				
			oZip.close();
			this.o_TempZipFile.close();
			
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
				catch (Exception e) {
					throw new myException("Fehler beim Erstellen der ZIP-Datei", e);
				}
			}
			if (this.o_TempZipFile != null)
			{
				try
				{
					this.o_TempZipFile.close();
				}
				catch (Exception e) {
					throw new myException("Fehler beim Erstellen der ZIP-Datei", e);
				}
			}

		}
	}
	
	
	public String get_NameOfNewZipFile()
	{
		return this.o_TempZipFile.getFileName();
	}
	
	public myTempFile  get_oTempZipFile()
	{
		return this.o_TempZipFile;
	}
	
	
	public void startDownload() throws myException {
		this.o_TempZipFile.starteDownLoad(this.c_NewFileName4Download+".zip", "Application/zip");
	}
	
	
}
