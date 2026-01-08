package panter.gmbh.indep.archive;

import java.io.File;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class ArchiveReportFiles 
{
    
    /*
     * kompletter pfad fuer rueckgabe 
     */
    private String 			cCompleteArchivePathWithSeparators =  null;
    
    /*
     * der name ab dem Archiv-pfad wird in die datenbank geschrieben
     */
    private String 			cArchivePathWithSeparators =  null;
    
    
  
	private String     		cFileName = null;


	/*
     * die klasse legt folgenden pfad an: <globaler Archivpfad>/MANDANT000001/REPORTS/<cSub_DIR_IN_Archive>/<201012>
     * 
     * und erstellt einen dateinamen, in den dann druckdateien kopiert werden koennen
     */
	public ArchiveReportFiles(String cSub_DIR_IN_Archive, String cNameDerDateiOhneEndung, String cDateiEndung) throws myException
	{
		super();
		
        String cString_Mandant = "MANDANT_"+bibALL.fillString(bibALL.get_ID_MANDANT(),10,'0',true);
        
        cSub_DIR_IN_Archive = Archiver.truncate_FileSeparatorsFromPath(cSub_DIR_IN_Archive);
        String cYearMonth = bibDB.EinzelAbfrage(DB_META.get_MonthDayStamp(DB_META.DB_ORA));

        String cPathName = File.separator+bibALL.get_WEBROOTPATH()+File.separator+bibALL.get_ARCHIVPATH()+File.separator+cString_Mandant;
        cPathName = this.makePath(cPathName);
        cPathName = this.makePath(cPathName+File.separator+"REPORTS");
        cPathName = this.makePath(cPathName+File.separator+this.truncate_FileSeparatorsFromPath(cSub_DIR_IN_Archive));
        cPathName = this.makePath(cPathName+File.separator+cYearMonth);
        
        this.cCompleteArchivePathWithSeparators = cPathName+File.separator;

        
        //hier existiert das gewuenschte Unterverzeichnis
        
        //jetzt den datenbank-pfad extrahieren
        int iPos = (File.separator+bibALL.get_WEBROOTPATH()+File.separator+bibALL.get_ARCHIVPATH()+File.separator).length();
        this.cArchivePathWithSeparators= this.cCompleteArchivePathWithSeparators.substring(iPos);
        
        
        
        //jetzt in einer schleife das fileObject erzeugen
        String cNextSeqNumber = bibDB.EinzelAbfrage("SELECT SEQ_REPORTARCHIV.NEXTVAL FROM DUAL");
        if (!bibALL.isLong(cNextSeqNumber))
        {
        	throw new myException(this,"Query <SELECT SEQ_REPORTARCHIV.NEXTVAL FROM DUAL> is not valid !!!");
        }
        
       this.cFileName = cNameDerDateiOhneEndung+"____arch_"+cNextSeqNumber+"."+cDateiEndung;
        
        
        
	}

	
	private String makePath(String cPathName) throws myException
	{
        File oFile = new File(cPathName);

        if (oFile.exists())
        {
            if (!oFile.isDirectory())
                throw new myException("ArchiveReportFiles:"+cPathName+" exists, but NO DIRECTORY !!!");
        }
        else
        {
           if (!oFile.mkdirs())
               throw new myException("ArchiveReportFiles:"+cPathName+" not existing, but CANNOT CREATE !!!");
        }
		
       	return cPathName;
       
	}
	
    
    private String truncate_FileSeparatorsFromPath(String cPath)
    {
        String cRueck = cPath.trim();
        
        if (cRueck.startsWith(File.separator))
            cRueck = cRueck.substring(1);
        
        if (cRueck.endsWith(File.separator))
            cRueck = cRueck.substring(0,cRueck.length()-1);
        
        return cRueck;
    }
    
 
    public String get_cCompleteArchivePathWithSeparators() 
    {
    	return cCompleteArchivePathWithSeparators;
    }
    
	public String get_cFileName() 
	{
		return cFileName;
	}

	/**
	 * 
	 * @return s Pfadanteil ab dem Basis-Archiv-Pfad (fuer datenbank-felder) (vorne ohne, hinten mit separator)
	 */
	public String get_cArchivePathWithEndSeparators() 
	{
			return cArchivePathWithSeparators;
	}

	
}
