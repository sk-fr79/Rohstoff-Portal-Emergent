/*
 * Created on 24.09.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import nextapp.echo2.app.filetransfer.ResourceDownloadProvider;



/**
 *  Ableitung der vorhandenen filetransfer-lib-klasse, damit auch dateien ausserhalb
 *        des klassenpfades runtergeladen werden können
 *
 *
 */
public class E2_DownloadProviderForFiles extends ResourceDownloadProvider
{
    private static final int BUFFER_SIZE = 4096;
    private String cFileName;


    /**
     * wenn cDownloadFileName leer ist, dann wird der resourcename genommen
     */
    public E2_DownloadProviderForFiles(String filename,String DownloadName, String ContentType)
    		throws FileNotFoundException
    {
	    super(DownloadName, ContentType);
	    this.cFileName 		= filename;
	    
	    File oFileTest = new File(filename);
	
	    if (!oFileTest.exists())
	    {
	        throw new FileNotFoundException("Die gewünschte Datei ist nicht vorhanden " + filename);
	    }
    }
    
    
    
    public void writeFile(OutputStream out) throws IOException
    {
        FileInputStream in = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;

        try
        {
        	
            in = new FileInputStream(this.cFileName);

            if (in == null)
            {
                throw new IOException("Specified resource does not exist (1): " + this.cFileName + ".");
            }

            do
            {
                bytesRead = in.read(buffer);

                if (bytesRead > 0)
                {
                    out.write(buffer, 0, bytesRead);
                }
            }
            while (bytesRead > 0);
        }
        catch (FileNotFoundException ex)
        {
            throw new IOException("Specified resource does not exist (2): " + this.cFileName + ".");
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException ex)
                {
                }
            }
        }
        
    }
    

}
