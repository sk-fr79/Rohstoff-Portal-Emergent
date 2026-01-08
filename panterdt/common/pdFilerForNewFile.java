package panterdt.common;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import panterdt.exceptions.pdException;


// klasse baut ein File auf und gibt schreibzugriffe darauf

public class pdFilerForNewFile extends File
{
    
    private 		BufferedWriter 	obfWriter = null;
    private			String  		cPathAndName = "";

    


    /**
     * @param cpathandname
     * @param bDeleteExistingFile
     * @throws pdException
     */
    public pdFilerForNewFile(String cpathandname,boolean bDeleteExistingFile) throws pdException
    {
        super(cpathandname);
        this.__MyFiler(cpathandname,bDeleteExistingFile);
    }

    
    private void __MyFiler(String cpathandname,boolean bDeleteExistingFile) throws pdException
    {
        this.cPathAndName 		= cpathandname;
        
        if (this.cPathAndName==null || this.cPathAndName.trim().equals("")) 
            throw new pdException("MyFiler:__MyFiler:Filename null or empty is not allowed");
        
        this.cPathAndName = this.cPathAndName.trim();

        /*
         * jetzt pruefen, ob der name da ist usw.
         */
        if (this.exists())
        {
            if (bDeleteExistingFile)
            {
                try
                {
                    this.delete();
                }
                catch (Exception ex)
                {
                    throw new pdException("MyFiler:__MyFiler:Filename exists and can not be deleted!!");
                }
            }
            else
            {
                throw new pdException("MyFiler:__MyFiler:Filename exists !!");
            }
        }
    }
    
    // wenn auf die datei geschrieben werden soll (ascii)
    // dann  muss mit get_BufferedWriter ein Writer-objekt generiert werden
    public BufferedWriter get_BufferedWriter() throws pdException
    {
        if (this.obfWriter == null)
        {
            try
            {
                this.obfWriter = new BufferedWriter(new FileWriter(this));
            }
            catch (Exception e)
            {
                throw new pdException("MyFiler:BufferedWriter:Error Creating BufferedWriter!"+e.getLocalizedMessage());
            }
        }

        return (this.obfWriter);
    }
    
    
    // schreibt eine zeile in eine ausgabedatei
    public void WriteLine(String cZeileOhne_CR) throws pdException
    {
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
                this.obfWriter.flush();
            }
            catch (Exception e)
            {
                throw new pdException("MyFiler:WriteLine:Error Writing Line! "+e.getLocalizedMessage());
            }
        }

    }


    
    public void close()
    {
        try
        {
            if (this.obfWriter != null)
                this.obfWriter.close();

        }
        catch (Exception e)
        {
        }
        
    }

    protected void finalize()
    {
        this.close();
        this.obfWriter = null;
    }
}
