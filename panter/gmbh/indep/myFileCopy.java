
package panter.gmbh.indep;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import panter.gmbh.indep.exceptions.myException;


/**
 * kopieren von einer datei auf eine andere
 */
public class myFileCopy
{
    
    private String cNameSourceFile = "";
    private String cNameTargetFile = "";

    /**
     * @param nameSourceFile
     * @param nameTargetFile
     */
    public myFileCopy(String nameSourceFile, String nameTargetFile) throws myException
    {
        super();
        if (nameSourceFile == null || nameSourceFile.trim().equals("") || nameTargetFile == null || nameTargetFile.trim().equals("") )
            throw new myException("myFileCopy:please give two correct, existing filenames ...");
        
        
        cNameSourceFile = nameSourceFile;
        cNameTargetFile = nameTargetFile;
        FileInputStream in 		= null;
        FileOutputStream out 	= null;

        
        try 
        {
            in = new FileInputStream(cNameSourceFile);
            out = new FileOutputStream(cNameTargetFile);
            byte[] buf = new byte[4096];
            int len;
            while ((len = in.read(buf)) > 0) 
            {
                out.write(buf, 0, len);
            }
            
            try
            {
                out.close();
            }
            catch (Exception ex) {}
            try
            {
                in.close();
            }
            catch (Exception ex) {}
                
        } 
        catch (IOException e) 
        {
            try
            {
                if (out !=null )out.close();
            }
            catch (Exception ex) {}
            try
            {
               if (in != null) in.close();
            }
            catch (Exception ex) {}

            
            throw new myException("myFileCopy:cannot copy files ...");
        }        
        
        
    }
    
    
    
    
    
    
}
