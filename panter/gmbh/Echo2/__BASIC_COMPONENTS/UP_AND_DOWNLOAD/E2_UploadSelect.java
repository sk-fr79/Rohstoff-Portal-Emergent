/*
 * Created on 30.09.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadSelect;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionInFileHandling;


/**
 * Upload-selektor fuer allgemeine hochlade-moeglichkeiten
 */
public class E2_UploadSelect extends UploadSelect
{
    private String cUploadPfad 					= "";
    private String cUploadFileNameOhneEndung 	= "";   // name des upload-files
    private String cStoringFileNameOhneEndung 	= "";   // name, unter dem gespeichert wurde
    private boolean bOverWrite 				= false;
    private String cEndung 						= "";

    private String cStoragePathAndFileNameWithEnding		= "";   // nach erfolgreichem hochladen steht hier der zielname
    
    // hier werden (falls nötig) erlaubte endungen aufgelistet
    private Vector<String> vErlaubtEndungen = new Vector<String>();

    
    public E2_UploadSelect(String cuploadPath, boolean boverWrite) throws myException
    {
        super();
        
        this.setBackground(new E2_ColorDark());
        this.bOverWrite = boverWrite;
        this.set_UploadPath(cuploadPath);
        this.setSendButtonDisplayed(true);
        
        this.setWidth(new Extent(100,Extent.PERCENT));
        this.setHeight(new Extent(50));
        
        this.setEnabledSendButtonText("Datei hochladen...");
       
    }

    
    
    /**
     * @param cuploadPath
     * appends, if neccessary, a ending File.separator
     */
    public void set_UploadPath(String cuploadPath) throws myException
    {
        if (bibALL.isEmpty(cuploadPath) || bibALL.isEmpty(cuploadPath.trim()))
        	throw new myException("myUploadSelect:constructor:empty upload-Path not allowed !");

    	// jetzt prüfen, ob der pfad mit / endet
        this.cUploadPfad = "";

        this.cUploadPfad = cuploadPath.trim().trim();
        if (!this.cUploadPfad.endsWith(File.separator))
             this.cUploadPfad += File.separator;
            
        
    }
    
    
    

    /**
     * speichert die hochgeladene datei an den gewuenschten ort
     * und aendert den namen (oder ergaenzt, wenn bAddNewName=true, vorne)
     * @param e
     * @param cNewFileNameOhneEndung
     * @param bAddNewName
     * @return
     */
    public void doSaveFile(UploadEvent e, String cNewFileNameOhneEndung, boolean bAddNewName) throws myException
    {
    	this.cStoragePathAndFileNameWithEnding = "";
    	
        File 		oFile_for_help = 	null;
        String 		cDateiName 		= 	e.getFileName().trim();

        int 		iPosEndung 	= 	cDateiName.lastIndexOf(".");
       
        if (iPosEndung >= 0)
        {
            this.cUploadFileNameOhneEndung = cDateiName.substring(0, iPosEndung);
            this.cEndung = cDateiName.substring(iPosEndung + 1).toLowerCase();
        }
        else
        {
            this.cUploadFileNameOhneEndung = cDateiName;
            this.cEndung = "";
        }
        
        /*
         * vorgabe fuer den speichernamen, Umlaute werden ersetzt
         */
        
        
        
        this.cStoringFileNameOhneEndung = this.cUploadFileNameOhneEndung;
        
        if (!bibALL.null2leer(cNewFileNameOhneEndung).equals(""))
        {
            if (bAddNewName)
                this.cStoringFileNameOhneEndung = cNewFileNameOhneEndung+this.cStoringFileNameOhneEndung;
            else
                this.cStoringFileNameOhneEndung = cNewFileNameOhneEndung;
        }
        
        
        // zuerst checken, ob es eine erlaubte endung ist
        boolean 	bEndungOK 	= 	true;
        if ((this.vErlaubtEndungen != null) && (this.vErlaubtEndungen.size() > 0))
        {
        	bEndungOK=false;
            for (int i = 0; i < this.vErlaubtEndungen.size(); i++)
            {
            	String cHelp = ((String) this.vErlaubtEndungen.get(i)).toLowerCase();

                if (cHelp.equals(cEndung))
                {
                    bEndungOK = true;
                    break;
                }
            }

            if (!bEndungOK)
            	throw new myExceptionInFileHandling(myExceptionInFileHandling.ERROR_FILETYPE_NOT_ALLOWED,cEndung);
        }
 
        
        //2016-01-14: dateinamen saeubern von sonderzeichen
        this.cUploadFileNameOhneEndung = new FileNameCleaner(this.cUploadFileNameOhneEndung).get_filename_clean();
        this.cStoringFileNameOhneEndung = new FileNameCleaner(this.cStoringFileNameOhneEndung).get_filename_clean();
        //fertig
        
        
        
          // jetzt den uploadpfad checken und ggf. anlegen
        oFile_for_help = new File(this.cUploadPfad);

        if (!oFile_for_help.exists())
        {
            if (!oFile_for_help.mkdir())
            	throw new myException("E2_UploadSelect: doSaveFile: Upload-Path is not available !");
           
        }
        else
        {
            if (!oFile_for_help.isDirectory())
            	throw new myException("E2_UploadSelect: doSaveFile: Upload-Path exists as a file !");
            
        }

        // jetzt prüfen, ob eine solche datei bereits existiert
        String cZielFileName = this.cUploadPfad + this.cStoringFileNameOhneEndung;

        if (!this.cEndung.equals(""))
            cZielFileName += ("." + this.cEndung);

        
        oFile_for_help = new File(cZielFileName);
        if (oFile_for_help.exists() && !this.bOverWrite)
        	throw new myExceptionInFileHandling(myExceptionInFileHandling.ERROR_FILE_EXISTS,cZielFileName);

        InputStream oInput = e.getInputStream();
        FileOutputStream oOut = null;
        byte[] oBuf = new byte[4096];
        int len;

        boolean bError = false;
        try
        {
            oOut = new FileOutputStream(cZielFileName);

            while ((len = oInput.read(oBuf)) > 0)
            {
                oOut.write(oBuf, 0, len);
            }

            oOut.close();
        }
        catch (Exception ex)
        {
            bError=true;
        }
        finally
        {
            if (oOut != null)
            {
                try
                {
                    oOut.close();
                }
                catch (Exception exx)
                {
                }
                finally
                {
                    oOut = null;
                }
            }
        }

        oFile_for_help = null;

        if (bError)
        	throw new myExceptionInFileHandling(myExceptionInFileHandling.ERROR_FILECANNOT_BE_WRITTEN,cZielFileName);
        else
        	this.cStoragePathAndFileNameWithEnding = cZielFileName;        
        
    }


    
    public String get_cUploadFileNameWithEndung_NoPath()
    {
        String cRueck = this.cUploadFileNameOhneEndung;
        if (!this.cEndung.equals(""))
            cRueck += "."+this.cEndung;
        
        return cRueck;
    }


    public String get_cStoringFileNameWithEndung_NoPath()
    {
        String cRueck = this.cStoringFileNameOhneEndung;
        if (!this.cEndung.equals(""))
            cRueck += "."+this.cEndung;
        
        return cRueck;
    }

    
    public String get_cUploadPfad()
    {
        return cUploadPfad;
    }

    public boolean get_bOverWrite()
    {
        return bOverWrite;
    }

    public void set_bOverWrite(boolean bOverwrite)
    {
        this.bOverWrite = bOverwrite;
    }
    
    public Vector<String> get_vErlaubtEndungen()
    {
        return vErlaubtEndungen;
    }

    public void set_vErlaubtEndungen(Vector<String> vector)
    {
        vErlaubtEndungen = vector;
    }

    public String get_cEndung()
    {
        return this.cEndung;
    }



	public String get_cStoragePathAndFileNameWithEnding()
	{
		return cStoragePathAndFileNameWithEnding;
	}


	
	/**
	 * @param oBufferForAddingInfoText (hier wird ein infotext angehaengt mit den dateitypen)
	 * @return
	 */
	public static Vector<String> get_vErlaubteEndungen(StringBuffer oBufferForAddingInfoText)
	{
		/*
		 * jetzt checken, welche endungen erlaubt sind !!!
		 */
		Vector<String> 		vEndungen	= new Vector<String>();
		String[][] 			cDefinitionen = bibDB.EinzelAbfrageInArray("SELECT DATEIENDUNG,BESCHREIBUNG FROM " + bibALL.get_TABLEOWNER() + ".JT_MEDIENTYP order by DATEIENDUNG", "@@@");
		
		if ((cDefinitionen == null) || (cDefinitionen.length == 0))
		{
			// dann error-vorgaben schreiben
			cDefinitionen = new String[1][2];
			cDefinitionen[0][0]="ERR";
			cDefinitionen[0][1]="Fehler->Keine Dateiendungen";
		}

		for (int i = 0; i < cDefinitionen.length; i++)
		{
			vEndungen.add(cDefinitionen [i] [0]);
			oBufferForAddingInfoText.append(("*." + cDefinitionen [i] [0]));
			if (i < (cDefinitionen.length - 1))
				oBufferForAddingInfoText.append(" / ");

		}
		return vEndungen;
	}
	
	
	
}
