package panterdt;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.indep.dataTools.MyConnection;
import panterdt.common.pdFilerForNewFile;
import panterdt.dataBase.pdDBToolBox;
import panterdt.dataBase.pdProtokollInfos;
import panterdt.dataBase.pdTableBuilder;
import panterdt.dataBase.pdTableExporter;
import panterdt.exceptions.pdException;

/*
 * hier wird der export abgearbeitet
 */
public class Exporter
{
	private  MyConnection 	oConn 	= null;
	private pdDBToolBox		oDB 	= null;
	private String			cExportPath = "";
	private pdTableBuilder	oTableBuilder = null;
    
    private pdProtokollInfos oProt = null;
    
    private boolean 		bOnlyStructure = false;
    

    /**
     * @param conn
     * @param cexportpath
     * @param bMigrate
     * @param OnlyStructure
     * @throws pdException
     */
    public Exporter(MyConnection conn,String cexportpath, boolean bMigrate, boolean OnlyStructure) throws pdException 
    {
        super();
        oConn 	= conn;
        oDB 	= new pdDBToolBox(this.oConn);
        this.cExportPath = cexportpath;
        
        this.bOnlyStructure = OnlyStructure;
        
        if (this.cExportPath == null) 				throw new pdException("Exporter:null path not allowed !!");
        if (this.cExportPath.trim().equals("")) 	throw new pdException("Exporter:empty path not allowed !!");
        
        /*
         * darf nicht mit einem pathseparator enden
         */
        if (this.cExportPath.endsWith(File.separator)) this.cExportPath = this.cExportPath.substring(0,this.cExportPath.length()-1);
        
        File oFileTest = new File(this.cExportPath);
        if (!oFileTest.exists()) throw new pdException("Exporter:path "+this.cExportPath+" not existing !!");

        try
        {
            oTableBuilder = new pdTableBuilder(this.oConn,bMigrate);
            oTableBuilder.buildTableDef();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
            throw new pdException("Exporter:error creating tablebuilder:"+ex.getLocalizedMessage());
        }
    }
    
    
    /*
     * doExport gibt ein export-protokoll zurueck
     */
    public String doExport() throws pdException
    {
        /*
         * die files fuer die verschiedenen zwecke bauen
         */
        String cProtokoll = "";

        try
        {
        	Vector vHelp = null;
        	
        	this.oProt = new pdProtokollInfos();
        	
            cProtokoll += "Starte Export ...\n";
            
            vHelp=this.oTableBuilder.getTableDef();
            this.doWrite(vHelp,this.cExportPath+File.separator+"EXPORT_00001_tables.sql");
            cProtokoll += this.cExportPath+File.separator+" EXPORT_00001_tables.sql"+"  geschrieben ...\n";
            this.oProt.setNumberOfTables(""+vHelp.size());
            
            vHelp = this.oTableBuilder.getTableIndex();
            this.doWrite(vHelp,this.cExportPath+File.separator+"EXPORT_00003_index.sql");
            cProtokoll += this.cExportPath+File.separator+" EXPORT_00003_index.sql"+"  geschrieben ...\n";
            this.oProt.setNumberOfIndex(""+vHelp.size());
            
            vHelp = this.oTableBuilder.getPrimaryKey();
            this.doWrite(vHelp,this.cExportPath+File.separator+"EXPORT_00002_primarykeys.sql");
            cProtokoll += this.cExportPath+File.separator+" EXPORT_00002_primarykeys.sql"+"  geschrieben ...\n";
            this.oProt.setNumberOfPrimaryKeys(""+vHelp.size());

            vHelp = this.oTableBuilder.getForeignKey();
            this.doWrite(vHelp,this.cExportPath+File.separator+"EXPORT_99999_foreignkeys.sql");
            cProtokoll += this.cExportPath+File.separator+" EXPORT_99999_foreignkeys.sql"+"  geschrieben ...\n\n\n";
            this.oProt.setNumberOfForeignKeys(""+vHelp.size());

            
            vHelp = this.oTableBuilder.get_vViews();
            this.doWrite(vHelp,this.cExportPath+File.separator+"EXPORT_00004_views.sql");
            cProtokoll += this.cExportPath+File.separator+" EXPORT_00004_views.sql"+"  geschrieben ...\n\n\n";
            this.oProt.setNumberOfViews(""+vHelp.size());

            vHelp = this.oTableBuilder.get_vSequences();
            this.doWrite(vHelp,this.cExportPath+File.separator+"EXPORT_00005_sequences.sql");
            cProtokoll += this.cExportPath+File.separator+" EXPORT_00005_sequences.sql"+"  geschrieben ...\n\n\n";
            this.oProt.setNumberOfSequences(""+vHelp.size());

            // cProtokoll += "Tabellen zum Exportieren ...\n";
            
            pdTableExporter 	oTableEx 	= null;
            pdFilerForNewFile 	oFileToEx 	= null;
            Vector<String>		vTables 	= this.oTableBuilder.getTablesOfUser(); 
            Vector<Vector>		vToExport 	= new Vector<Vector>();
            Vector<Vector>		vDoneExport = new Vector<Vector>();
            int 				iHelp 		= 10000;
            
            
            if (!this.bOnlyStructure)
            {
	            /*
	             * jede tabelle wird in eine eigene datei exportiert,
	             * die sortnummern beginnen bei 10000, das garantiert eine fuenfstellige nummer
	             */
	            cProtokoll += "Table (rowcount)"+"\t\t\t   --->    Written(number rows)"+"\n";
	            cProtokoll += "========================================================================================="+"\n";
	            for (int i=0,iZahl=vTables.size();i<iZahl;i++)
	            {
	                oTableEx = new pdTableExporter(this.oDB,true);
	                oTableEx.addExportTabelle((String)vTables.get(i));
	                oFileToEx = new pdFilerForNewFile(this.cExportPath+File.separator+"EXPORT_"+(iHelp+i)+"_"+(String)vTables.get(i)+".sql",true);
	                oTableEx.doExportToFile(oFileToEx);
	                oFileToEx.close();
	                vToExport.add(oTableEx.get_vZuExportierenTabUndZahl());
	                vDoneExport.add(oTableEx.get_vWurdeExportiertTabUndZahl());
	                for (int k=0,kZahl=oTableEx.get_vZuExportierenTabUndZahl().size();k<kZahl;k++)
	                {
	                    cProtokoll += (String)oTableEx.get_vZuExportierenTabUndZahl().get(k)+"\t\t\t   --->    "+(String)oTableEx.get_vWurdeExportiertTabUndZahl().get(k)+"\n";
	                    System.out.println((String)oTableEx.get_vZuExportierenTabUndZahl().get(k)+"\t\t\t   --->    "+(String)oTableEx.get_vWurdeExportiertTabUndZahl().get(k));
	                    
	                    this.oProt.add_ExportInfo(	(String)oTableEx.get_vTabellen().get(k),
	                    							(String)oTableEx.get_vZuExportierenZahl().get(k),
	                    							(String)oTableEx.get_vWurdeExportiertZahl().get(k));
	                }
//                    if (i>3)
//                    	break;

	            }
            }
            
            FileUtils.writeStringToFile(new File(this.cExportPath+File.separator+"export.log"),cProtokoll,"ISO-8859-15");
            
        }
        catch (Exception ex)
        {
            /*
             * vor dem rausspringen alle fileobjekte closen
             */
            throw new pdException("Exporter:doExport:"+ex.getLocalizedMessage());
        }
        return cProtokoll;
        
    }
    
    private void doWrite(Vector vInhalt,String cFileNameAndPath) throws pdException
    {

        pdFilerForNewFile 		oFile 		= null; 
        BufferedWriter 	obfWriter 	= null;
        
        try
        {
            oFile     = new pdFilerForNewFile(cFileNameAndPath,true);
            obfWriter = new BufferedWriter(new FileWriter(oFile));

	        for (int i=0;i<vInhalt.size();i++)
	        {
	            obfWriter.write(((String)vInhalt.get(i)));
	            obfWriter.newLine();
	        }
	        
	        obfWriter.flush();
	        obfWriter.close();
        }
        catch (Exception ex)
        {
            /*
             * vor dem rausspringen alle fileobjekte closen
             */
            if (obfWriter != null)
            {
                try
                {
                    obfWriter.close();
                }
                catch (Exception exx) {}
            }
            
            throw new pdException("Exporter:doExport:"+ex.getLocalizedMessage());
        }
    }


	public pdProtokollInfos get_Protokoll()
	{
		return oProt;
	}
    
}
