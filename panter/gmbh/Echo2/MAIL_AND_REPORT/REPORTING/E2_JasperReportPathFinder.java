package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.File;
import java.util.Vector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.BT_BuildJaspers;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_jasperCompileChain;

public class E2_JasperReportPathFinder 
{
	public String  c_ReportPath = null;
	public String  c_AddonPath = null;
	public String  c_ImagePath = null;
	public String  c_ReportBaseName = null;

	//2014-05-21
	public String  c_ReportName4SearchInPipelineDB = null;
	
	
	public E2_JasperReportPathFinder(String NameOfReport) throws myException
	{
		super();
		
		//jetzt den pfad pruefen und die reportdatei uebernehmen
		if (S.isEmpty(NameOfReport))
		{
			throw new myException(this,"Empty reportname is not allowed !");
		}

		if (NameOfReport.toUpperCase().endsWith(".JASPER"))
		{
			NameOfReport=NameOfReport.substring(0,NameOfReport.length()-7);
		}
		
		
		this.c_ReportName4SearchInPipelineDB = NameOfReport;
		this.c_ReportBaseName = NameOfReport;

		
		
		//2014-05-21: hier jetzt schauen, ob der reportbasename mit einer String von #0 bis #99 endet. wenn ja, wird der reportbasename veraendert auf den
		//            namen ohne diesen nummernzusatz
		for (int i=1;i<=99;i++) {
			if (this.c_ReportBaseName.endsWith("#"+i)) {
				if (i<10){
					this.c_ReportBaseName = this.c_ReportBaseName.substring(0,this.c_ReportBaseName.length()-2);
				} else {
					this.c_ReportBaseName = this.c_ReportBaseName.substring(0,this.c_ReportBaseName.length()-3);
				}
				break;
			}
		}
		
		
		/*
		 * jetzt der reihe nach pruefen:
		 * LISTEN - Pfad
		 * Mandanten-Pfad
		 * alle-Pfad
		 */
		
		if (new File(bibALL.get_REPORTPATH_LISTEN_MANDANT()+this.c_ReportBaseName+".jasper").exists() || new File(bibALL.get_REPORTPATH_LISTEN_MANDANT()+this.c_ReportBaseName+".jrxml").exists())
		{
			this.c_ReportPath = bibALL.get_REPORTPATH_LISTEN_MANDANT();
			this.c_AddonPath = bibALL.get_REPORTPATH_ADDONS_MANDANT();
			this.c_ImagePath = bibALL.get_REPORTPATH_IMAGES_MANDANT();
		}
		else if (new File(bibALL.get_REPORTPATH_MANDANT()+this.c_ReportBaseName+".jasper").exists() || new File(bibALL.get_REPORTPATH_MANDANT()+this.c_ReportBaseName+".jrxml").exists())
		{
			this.c_ReportPath = bibALL.get_REPORTPATH_MANDANT();
			this.c_AddonPath = bibALL.get_REPORTPATH_ADDONS_MANDANT();
			this.c_ImagePath = bibALL.get_REPORTPATH_IMAGES_MANDANT();
		}
		else if (new File(bibALL.get_REPORTPATH_LISTEN_ALLE()+this.c_ReportBaseName+".jasper").exists() || new File(bibALL.get_REPORTPATH_LISTEN_ALLE()+this.c_ReportBaseName+".jrxml").exists())
		{
			this.c_ReportPath = bibALL.get_REPORTPATH_LISTEN_ALLE();
			this.c_AddonPath = bibALL.get_REPORTPATH_ADDONS_ALLE();
			this.c_ImagePath = bibALL.get_REPORTPATH_IMAGES_ALLE();
		}
		else if (new File(bibALL.get_REPORTPATH_ALLE()+this.c_ReportBaseName+".jasper").exists() || new File(bibALL.get_REPORTPATH_ALLE()+this.c_ReportBaseName+".jrxml").exists())
		{
			this.c_ReportPath = bibALL.get_REPORTPATH_ALLE();
			this.c_AddonPath = bibALL.get_REPORTPATH_ADDONS_ALLE();
			this.c_ImagePath = bibALL.get_REPORTPATH_IMAGES_ALLE();
		}
		else
		{
			throw new myException(this,"Report-Base-Name: "+this.c_ReportBaseName+" can not be found !!");
		}
		
		
		
		//2018-05-04: pruefen, ob in der datenbank noch eintraege sind fuer tochterreports
		new RecList21_jasperCompileChain().fillWithReportName(this.c_ReportBaseName+".jrxml").checkAndCompileIfNeeded(this.c_ReportPath);
		//ende der erweiterung der Kompilierung

		
		
		
		//danach der klassische check auf reports im gleichnamigen ordner
		//2011-03-31: immer noch auf compile pruefen
		this.check_compile(this.c_ReportPath, this.c_ReportBaseName);
		

		
		
	}
	
	public String get_cCompleteReportPathAndFileName()
	{
		return this.c_ReportPath+this.c_ReportBaseName+".jasper";
	}

	
	/**
	 * 
	 * @author martin
	 * @date 04.02.2020
	 *
	 * @return s only path to found report
	 */
	public String getFoundPath() {
		return this.c_ReportPath;
	}
	
	
	
	private void check_compile(String cReportPath, String cReportBaseName) throws myException
	{
		
		//2011-12-08: sonderfall, wenn ein lieferschein geprueft wird, dann die anhang 7-dateien checken
		if (cReportBaseName.toUpperCase().equals("LIEFERSCHEIN"))
		{
			this.check_compile(cReportPath, "nur_eu_blatt");
		}
		

		if (cReportBaseName.toUpperCase().equals("NUR_EU_BLATT") )
		{
			this.check_compile(cReportPath, "eu_amtsblatt_a");
			this.check_compile(cReportPath, "eu_amtsblatt_b");
		}
		

		
		boolean bCompile = this.bCheckCompile(cReportPath+cReportBaseName+".jrxml", cReportPath+cReportBaseName+".jasper");

		
		//2011-12-08: 
		String cSubPath = cReportBaseName;
		if (cSubPath.startsWith("eu_amtsblatt"))
		{
			cSubPath="eu_amtsblatt";
		}
		
		//2011-06-22: auch die subreports im gleichnamigen ordner pruefen
		bCompile = bCompile || this.bCheckCompile_wegen_subreports(cReportPath+cSubPath+File.separator);
		

		
		
		if (bCompile)
		{
			try 
			{
				JasperCompileManager.compileReportToFile(cReportPath+cReportBaseName+".jrxml",cReportPath+cReportBaseName+".jasper");
				
				Vector<String>  vOK = new Vector<String>();
				Vector<String>  vFehler = new Vector<String>();
				BT_BuildJaspers.CompileAllJaspers(cReportPath+cSubPath+File.separator,vOK,vFehler);
				
				if (vFehler.size()>0)
				{
					throw new myException("Error compiling subreports:"+cReportPath+cSubPath);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Report: ",true,cReportBaseName,false," wurde kompiliert !",true)));
				}
				
			} 
			catch (JRException e) 
			{
				e.printStackTrace();
				throw new myException(e.getLocalizedMessage());
			}
		}
		
	}
	
	
	private boolean bCheckCompile(String cJRXML_Pfad_und_Namen, String cJasper_Pfad_und_Namen)
	{
		boolean bCompile = false;
		
		File fJasper = new File(cJasper_Pfad_und_Namen);
		File fJrxml = new File(cJRXML_Pfad_und_Namen);
		
		if (fJasper.exists() && (!fJrxml.exists()))
		{
			bCompile = false;
		}
		
		if (fJasper.exists() && fJrxml.exists())
		{
			if (fJrxml.lastModified()>fJasper.lastModified())
			{
				bCompile = true;
			}
			else
			{
				bCompile = false;
			}
		}
		
		if ((!fJasper.exists()) && fJrxml.exists())
		{
			bCompile = true;
		}
		
		return bCompile;

	}
	
	
	
	private boolean bCheckCompile_wegen_subreports(String cPathNameWithSeparators)
	{
		boolean bCheckCompile=false;

		try {
			File ofileDir = new File(cPathNameWithSeparators);
			
			//es kann immer sein, dass der uebergebene ordner nicht existiert (z.B. wenn ein report keine subreports hat)
			if (ofileDir.exists())
			{
				if (ofileDir.isDirectory())
				{
					String[] cJasperFiles = ofileDir.list();
					if (cJasperFiles != null && cJasperFiles.length>0)
					{
						for (int i=0;i<cJasperFiles.length;i++)
						{
							String cName = cJasperFiles[i];
							if (cName.toUpperCase().endsWith(".JRXML"))   //es werden nur die jrxml-eintraege in diesem ordner geprueft
							{
								cName = cName.substring(0,cName.length()-6);
								bCheckCompile = this.bCheckCompile(cPathNameWithSeparators+cName+".jrxml", cPathNameWithSeparators+cName+".jasper");
								
								if (bCheckCompile)   //einer reicht
								{
									return bCheckCompile;
								}
								
							}
						}
					}				
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Kompilieren der Report-Dateien !!!"));
		}

		return bCheckCompile;
	}

	
	
}
