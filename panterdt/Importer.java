package panterdt;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import panter.gmbh.indep.dataTools.MyConnection;
import panterdt.common.pdFileHelper;
import panterdt.common.pdFilerForNewFile;
import panterdt.common.pdFilerForOpenAndRead;
import panterdt.common.pdUnicodeTransformer;
import panterdt.dataBase.pdDBToolBox;
import panterdt.exceptions.pdException;

/**
 * @author martin
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Importer
{

    private MyConnection 	oConn;
    private pdDBToolBox 	oDB;
    private String 			cImportPath;
    private Vector<String>	vImportFiles 	= new Vector<String>();
    
    private StringBuffer    cStringBufferProtokoll = new StringBuffer();   
    
    
    /**
     * @param conn   - MyConnection
     * @param pathForAction - pfade
     * @param filenames - array mit dateinamen, die nacheinander importiert werden,
     * 		dabei kann ein Filename auch platzhalter beinhalten
     */
    public Importer(MyConnection conn, String PathForAction, String[] filenames) throws pdException 
    {
        super();
        
        String pathForAction = PathForAction;
        
        oConn 	= conn;
        oDB 	= new pdDBToolBox(this.oConn);
        if (pathForAction == null) 				throw new pdException("Null-Path not allowed !!");
        if (pathForAction.trim().equals("")) 	throw new pdException("empty path not allowed !!");

        /*
         * pruefen, ob die uebergebenen pfade funktionieren
         */
        if (! pathForAction.endsWith(File.separator)) pathForAction += File.separator;
        this.cImportPath = pathForAction;
        File oTest = new File(this.cImportPath);   // fileobject only for path
        if (oTest.isDirectory())
        {
            for (int i=0;i<filenames.length;i++)
            {
                pdFileHelper.Filter oFilter = new pdFileHelper.Filter(filenames[i]);
                
                String[] cListForImport =  oTest.list(oFilter);
                if (cListForImport.length==0)
                {
                    throw new pdException("Files not found !!!  -> "+this.cImportPath+filenames[i]);
                }
                else
                {
                    /*
                     * die liste mit dateinamen an einen vector uebergeben
                     */
                    for (int k=0;k<cListForImport.length;this.vImportFiles.add(cListForImport[k++]));
                }
            }
        }
        else
        {
            throw new pdException("NO DIRECTORY ... "+this.cImportPath);
        }
        
        /*
         * hier werden dann die dateien sortiert
         */
        Collections.sort( this.vImportFiles);
        
    }


    
    public void doImport() throws pdException
    {
        /*
         * ein ausgabefile fuer das schreiben der nicht ausgefuerten sql-statements
         */
        pdFilerForNewFile		oFileOutErrors = new pdFilerForNewFile(this.cImportPath+"Import.log",true);
        
        pdFilerForOpenAndRead 	oFileToImp = null;
        String  				cImportLine = null;
        
        oFileOutErrors.WriteLine("Start import ...");
        oFileOutErrors.WriteLine("");
        
        for (int i=0;i<this.vImportFiles.size();i++)
        {
            oFileToImp = new pdFilerForOpenAndRead(this.cImportPath+(String)this.vImportFiles.get(i));
            oFileOutErrors.WriteLine("importing ........   "+oFileToImp.getAbsolutePath());
            int iImportedLines = 0;
            
            this.cStringBufferProtokoll.append("IMPORT "+this.vImportFiles.get(i));
             
            int iFehler = 0;
            
            do
            {
                cImportLine = oFileToImp.ReadLine();
 	            if (cImportLine != null)
	            {
 	                if (! cImportLine.startsWith("//"))     // kommentarzeilen auslassen
 	                {
	                    cImportLine = pdUnicodeTransformer.get_AsciiFromUnicode(cImportLine);
	                    try
	                    {
	                        this.oDB.ExecSqlohneZusatz(cImportLine,false);
	                        iImportedLines ++;
	                        
	                        if (iImportedLines % 5000 == 0)
	                        {
	                        	this.oDB.Commit();
	                        	System.out.println("importing ........   "+oFileToImp.getAbsolutePath()+"  ..."+iImportedLines);
	                        }
	                    }
	                    catch (pdException ex)
	                    {
	                        oFileOutErrors.WriteLine(cImportLine);
	                        oFileOutErrors.WriteLine("// \t\t  --> Code: "+ex.getLocalizedMessage());
	                        System.out.println(cImportLine);
	                        System.out.println("// \t\t  --> Code: "+ex.getLocalizedMessage());
	                        iFehler++;
	                    }
	                }
                }
                
            } while (cImportLine != null);
            
            if (iFehler==0)
            {
            	this.cStringBufferProtokoll.append("  "+iImportedLines+" Datensätze korrekt importiert ... *** OK ***\n");
            }
            else
            {
            	this.cStringBufferProtokoll.append(" "+iImportedLines+" Datensätze korrekt importiert  ---- " +iFehler+" Datensätze NICHT importiert !!!! ERROR !!!!\n");
            }
            
            try
            {
            	this.oDB.Commit();        //abschluss-Commit
            }
            catch (pdException ex)
            {
                oFileOutErrors.WriteLine(cImportLine);
                oFileOutErrors.WriteLine("// \t\t  --> Code: "+ex.getLocalizedMessage());
                System.out.println(cImportLine);
                System.out.println("// \t\t  --> Code: "+ex.getLocalizedMessage());
            }
            
            
            oFileOutErrors.WriteLine("Finished importing ........"+oFileToImp.getAbsolutePath()+" ---->  "+iImportedLines+" Records imported");
            oFileOutErrors.WriteLine("");
            System.out.println("Finished importing ........"+oFileToImp.getAbsolutePath()+" ---->  "+iImportedLines+" Records imported");
            System.out.println("");
        }
        
        
        oFileOutErrors.WriteLine("finished import ...");

        oFileOutErrors.close();
        oFileToImp.close();
        
    }
    
    
    
    
    /**
     * @return Returns the vImportFiles.
     */
    public Vector get_vImportFiles()
    {
        return this.vImportFiles;
    }



	public StringBuffer get_StringBufferProtokoll()
	{
		return cStringBufferProtokoll;
	}
}
