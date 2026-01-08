package panterdt.common;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import panterdt.exceptions.pdException;


// klasse baut ein File auf und gibt schreibzugriffe darauf

public class pdFilerForOpenAndRead extends File
{
    
    private			BufferedReader  obfReader = null;
    private			String  		cPathAndName = "";

    
    
    public pdFilerForOpenAndRead(String cpathandname) throws pdException
    {
        super(cpathandname);
        this.__MyFiler(cpathandname);
    }


    
    private void __MyFiler(String cpathandname) throws pdException
    {
        this.cPathAndName 		= cpathandname;
        
        if (this.cPathAndName==null || this.cPathAndName.trim().equals("")) 
            throw new pdException("MyFiler:__MyFiler:Filename null or empty is not allowed");
        
        this.cPathAndName = this.cPathAndName.trim();

        /*
         * jetzt pruefen, ob der name da ist usw.
         */
        if (! this.exists())
        {
           throw new pdException("MyFilerForOpenAndRead:__MyFiler:Filename not existing !!");
        }
    }
    
    
    // wenn auf die datei geschrieben werden soll (ascii)
    // dann  muss mit get_BufferedWriter ein Writer-objekt generiert werden
    public BufferedReader get_BufferedReader() throws pdException
    {
        if (this.obfReader == null)
        {
            try
            {
                this.obfReader = new BufferedReader(new FileReader(this));
            }
            catch (Exception e)
            {
                throw new pdException("MyFiler:BufferedReader:Error Creating BufferedReader!"+e.getLocalizedMessage());
            }
        }

        return (this.obfReader);
    }

    
    /*
     * am ende wird null zurueckgegeben
     */
    public String ReadLine() throws pdException
    {
        String cRueck = null;
        if (this.obfReader == null)
            this.get_BufferedReader();

        try
        {
            cRueck = this.obfReader.readLine();
        }
        catch(Exception ex)
        {
            throw new pdException("MyFiler:Readline:Error reading line from "+this.cPathAndName+"  ---> "+ex.getLocalizedMessage());
        }
        
        return cRueck;
    }
    
    


    public void close()
    {
        try
        {
            if (this.obfReader != null)
                this.obfReader.close();

        }
        catch (Exception e)
        {
        }
        
    }

    protected void finalize()
    {
        this.close();
        this.obfReader = null;
    }
}
