package panter.gmbh.indep;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;



// klasse baut ein Temporäres File auf und gibt das zuruck
public class myTempFile
{
	public static String CHARSET_US_ASCII=		"US-ASCII"; 
	public static String CHARSET_ISO_8859_1=	"ISO-8859-1";
	public static String CHARSET_UTF_8=			"UTF-8";
	
	
    private String FileName = ""; // dateiname auf filesystem
    private String FileURL = ""; // dateiname als URL für einbettung ins web
    private String NamensKern = "";
    private String Endung = "";
    private File oDatei = null;
    private boolean bOK = false;
    private String cFehler = "";
    private BufferedWriter obfWriter = null;

    private boolean bDeleteAtFinalize = false;
    
    

	public myTempFile(String pNamensKern, String pEndung, boolean bDeleteOnExit)
    {
        this.NamensKern = pNamensKern;
        this.Endung = pEndung;

        //DEBUG.System_println("@@##@@----  Erzeuge:  myTempFile  "+this,"");
        
        // XML-definierter ausgabepfad festlegen 
        String webRootPath = ((String) bibALL.getSessionValue("WEBROOTPATH")).trim(); // pysikalischer Name des web-app-ordners (in diesem ordner steht WEB_INF)
        String outPath = ((String) bibALL.getSessionValue("OUTPUTPATH")).trim(); // name des zielordners im web-ordner

        if (outPath.equals("") || webRootPath.equals(""))
        {
            this.bOK = false;
            cFehler += "Fehlende Info in Konfiguration WEBROOTPATH/OUTPUTPATH ";
        }
        else
        {
            // prüfen, ob outpath rechts oder links einen / hat, wenn ja weg
            if (outPath.substring(outPath.length() - 1).equals("/"))
            {
                outPath = outPath.substring(0, outPath.length() - 1);
            }

            if (outPath.substring(0, 1).equals("/"))
            {
                outPath = outPath.substring(1);
            }

            // prüfen, ob webRootPath rechts oder links einen / hat, wenn ja weg
            if (webRootPath.substring(webRootPath.length() - 1).equals("/"))
            {
                webRootPath = webRootPath.substring(0, webRootPath.length() - 1);
            }

            if (webRootPath.substring(0, 1).equals("/"))
            {
                webRootPath = webRootPath.substring(1);
            }

            try
            {
            	
            	//System.out.println(this.NamensKern+ this.Endung);
            	
                // erzeugt eine temporäre datei, die nach der session gelöscht wird
                oDatei = File.createTempFile(this.NamensKern, this.Endung, new File("/" + webRootPath + "/" + outPath + "/"));

                if (bDeleteOnExit)
                {
                    oDatei.deleteOnExit();
                }

                this.FileName = oDatei.getPath(); // name auf file-struktur
                this.FileURL = ((String) bibALL.getSessionValue("WEBNAME")).trim() + "/" + outPath + "/" + oDatei.getName();

                this.bOK = true;
                
                
                
        		//DEBUG-INFO
        		//System.out.println("                 -------baue Tempfile -- "+this.getFileName());
        		//DEBUG-INFO
        		

                
                
                
            }
            catch (IOException e)
            {
            	e.printStackTrace();
                this.bOK = false;
                cFehler += "Fehler beim Erstellen der Datei !";
            }
        }
    }


	/**
	 * 2015-06-15: neue variante mit direkter uebergabe des flags deleteonfinalize
	 * @param pNamensKern
	 * @param pEndung
	 * @param bDeleteOnExit
	 * @param bDeleteOnFinalize
	 */
	public myTempFile(String pNamensKern, String pEndung, boolean bDeleteOnExit, boolean bDeleteOnFinalize) {
		this(pNamensKern, pEndung, bDeleteOnExit);
		this.set_bDeleteAtFinalize(bDeleteOnFinalize);
    }

	
	
	
	
	/**
	 * 
	 * @param pNamensKern
	 * @param pEndung
	 * @param bDeleteOnExit  
	 * @param deleteOnFinalize
	 * @param origFileIsCopied
	 */
	public myTempFile(String pNamensKern, String pEndung, boolean bDeleteOnExit,boolean bDeleteOnFinalize, File origFileIsCopied) throws myException{
		this(pNamensKern, pEndung, bDeleteOnExit,bDeleteOnFinalize);
		
		if (origFileIsCopied==null || !origFileIsCopied.exists()){
			throw new myException(this,"empty File is not allowed !");
		}
		
		//proforma ein leezeichen an die temp-datei schreiben
		if (!this.WriteLine("--")) {
			throw new myException(this,"Error creating tempfile"+this.getFileName());
		}
		this.close();  //buffered writer schliessen
		
		try {
			//jetzt die originaldatei ueber die neu erzeugte temp-datei schreiben
			FileUtils.copyFile(origFileIsCopied,new File(this.getFileName()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new myException(this,"Error copying "+origFileIsCopied.getPath());
		}
	}

	
	
	
	
	
	
    // wenn auf die datei geschrieben werden soll (ascii)
    // dann  muss mit get_BufferedWriter ein Writer-objekt generiert werden
    public BufferedWriter get_BufferedWriter()
    {
        if (this.obfWriter == null)
        {
            try
            {
                this.obfWriter = new BufferedWriter(new FileWriter(oDatei));
            }
            catch (Exception e)
            {
                bibALL.WriteLogEceptionInfo(e);
            }
        }

        return (this.obfWriter);
    }

    
    
    
    
    
    
    // schreibt eine zeile in eine ausgabedatei
    public boolean WriteLine(String cZeileOhne_CR)
    {
        boolean bRueck = false;

        if (this.obfWriter == null)
        { // dann bauen
            this.get_BufferedWriter();
        }

        // wenn immer noch null
        if (this.obfWriter != null)
        {
            try
            {
                this.obfWriter.write(cZeileOhne_CR);
                this.obfWriter.newLine();
                bRueck = true;
            }
            catch (Exception e)
            {
                bibALL.WriteLogEceptionInfo(e);
                bRueck = false;
            }
        }

        return bRueck;
    }

    // schreibt eine zeile in eine ausgabedatei
    public boolean WriteTextBlock(String Text)
    {
        boolean bRueck = false;

        if (this.obfWriter == null)
        { // dann bauen
            this.get_BufferedWriter();
        }

        // wenn immer noch null
        if (this.obfWriter != null)
        {
            try
            {
                this.obfWriter.write(Text);
                bRueck = true;
            }
            catch (Exception e)
            {
                bibALL.WriteLogEceptionInfo(e);
                bRueck = false;
            }
        }

        return bRueck;
    }

    
    
    // 2014-04-29: schreibt eine zeile in eine ausgabedatei in einem definierten encoding 
    public boolean WriteTextBlock(String Text, String Encoding)
    {
        boolean bRueck = false;
       	FileOutputStream   oFOut = 		null;
    	OutputStreamWriter oFW = 		null;
    	BufferedWriter     oBufWrit = 	null;

        try
        {
	       	oFOut = 		new FileOutputStream(oDatei);
	    	oFW = 		new OutputStreamWriter(oFOut,Encoding);
	    	oBufWrit = 	new BufferedWriter(oFW);
	    	oBufWrit.write(Text);
            bRueck = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            bRueck = false;
        }

        try {
        	if (oBufWrit!=null) {
        		oBufWrit.close();
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
            bRueck = false;
        }
        
        try {
        	if (oFW!=null) {
        		oFW.close();
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
            bRueck = false;
        }
        
        try {
        	if (oFOut!=null) {
        		oFOut.close();
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
            bRueck = false;
        }
        
        return bRueck;
    }

    
    
    
    public File getFile()
    {
        return this.oDatei;
    }

    public String getFileName()
    {
        return this.FileName;
    }

    public String getFileURL()
    {
        return this.FileURL;
    }

    public boolean istOK()
    {
        if (this.oDatei == null)
        {
            this.bOK = false;
        }

        return this.bOK;
    }

    public String getFehlerInfo()
    {
        return this.cFehler;
    }

    
    public void close()
    {
        try
        {
        	//DEBUG.System_println("@@##@@----  Betrete myTempFile.close()","");
        	
            if (this.obfWriter != null)
            {
                this.obfWriter.close();
            }
        }
        catch (Exception e)
        {
        }
        
        //DEBUG.System_println("@@##@@----  Verlasse myTempFile.close() ","");
    }

    protected void finalize() throws Throwable
    {
    	
    	//DEBUG.System_println("@@##@@----  Betrete myTempFile.finalize()","");
    	
    	this.close();
    	
    	
    	
    	try 
    	{
	    	
    		if (this.bDeleteAtFinalize)
	    	{
    			//DEBUG.System_println("@@##@@----  myTempFile...bDeleteAtFinalize == true","");
				
				if (this.getFile()!=null && this.getFile().exists())
				{
					//DEBUG.System_println("@@##@@----   myTempFile...delete file " + this.getFile().getAbsolutePath(),"");
					this.getFile().delete();
				}
	    	}
    	} 
    	catch (Exception e) 
    	{
    		bibMSG.add_MESSAGE(new MyE2_Alarm_Message("myTempFile:finalize()::"+e.getLocalizedMessage()));
    		e.printStackTrace();
    	}
    	finally{
    		
    		//DEBUG.System_println("@@##@@----  myTempFile.finally","");
    		super.finalize();
    	}
    }

    
    
    public void set_bDeleteAtFinalize(boolean bDeleteAtFinalize) 
    {
		this.bDeleteAtFinalize = bDeleteAtFinalize;
	}

    
    /**
     * physikalisches loeschen der datei
     * @param throwExWhenError
     * @throws myException
     */
    public void delete_File(boolean throwExWhenError) throws myException {
		if (this.getFile()!=null && this.getFile().exists()) {
			try {
				this.getFile().delete();
			} catch (Exception e) {
				e.printStackTrace();
				if (throwExWhenError) {
					throw new myException(this,e.getLocalizedMessage());
				}
			}
		}
    }
    
    
    
    public void starteDownLoad(String cDownloadFileName,String cMimeType)
    {
    	new E2_Download().starteFileDownload(this.getFileName(),cDownloadFileName,cMimeType);
    }

	public String get_NamensKern() {
		return NamensKern;
	}
    
	
	public String get_Endung() {
		return this.Endung;
	}
	
	/**
	 * 
	 * @param v_tempFileToAdd (Vector<myTempFile>)
	 * @throws myException
	 */
	public void append_pdf(Vector<?>  v_tempFileToAdd) throws myException {
		
		Vector<String>  vFilenamesPDF = new Vector<String>();
		
		vFilenamesPDF.add(this.getFileName());
		for (Object tf: v_tempFileToAdd) {
			if (tf instanceof myTempFile) {
				vFilenamesPDF.add(((myTempFile)tf).getFileName());
			} else if (tf instanceof String) {
				vFilenamesPDF.add((String)tf);
			} else {
				throw new myException(this, "Only the types <String> and myTempfile can be in the vector !!");
			}
		}
		
		//jetzt pruefen, ob alle existieren
		for (String filename: vFilenamesPDF) {
			if (! new File(filename).exists()) {
				throw new myException(this,"File: "+filename+" is not existing !");
			}
		}
		
		pdfConcat  oConcater = new pdfConcat(vFilenamesPDF);
		myTempFile oTempfileNew = oConcater.baueZielDatei("attachment_files");
		oTempfileNew.set_bDeleteAtFinalize(true);

		try {
			//jetzt die neue datei auf die alte kopieren (kuckucksei-prinzip)
			FileUtils.copyFile(new File(oTempfileNew.getFileName()),new File(this.getFileName()));
			
			DEBUG.System_println(" ");
			DEBUG.System_println("======================");
			DEBUG.System_println("Quelle: "+oTempfileNew.getFileName());
			DEBUG.System_println("Ziel: "+this.getFileName());
			DEBUG.System_println("======================");
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new myException(this,"Error building output-file");
		}

	}
	
}
