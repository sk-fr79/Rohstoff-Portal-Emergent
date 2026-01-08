package panter.gmbh.Echo2.AgentsAndValidators;

import java.io.File;
import java.io.FileOutputStream;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.ZIP.myZipper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import panterdt.Exporter;
import panterdt.dataBase.pdProtokollInfos;
import echopointng.Separator;


/*
 * allgemeine benutzbare action-agent, der den kompletten
 * datenbestand inclusive create-scripte in den aus der
 * Umgebung festgelegten Ordner Export rausschreibt. 
 * Es wird ein uvz angelegt, das den namen datum/uhrzeit traegt 
 */
public class ActionAgent_ExportDBStructureAndData extends XX_ActionAgent
{

	private String 			cExportPath = "";
	private boolean 		bMakeZipAndDownload = false;
	private boolean 		bDeleteAfterAction = false;
//	private MyE2_Label 		oLabelInfoExportResult = null;
//	private MyE2_Column 	oColumnInfoExportResult = null;
	
	/*
	 * zwei protokollierer :	1. ein grid
	 * 							2. ein StringBuffer
	 */
	private MyE2_Grid 		oGridInfoExportResult = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
	private StringBuffer    oStringBufferInfo = new StringBuffer();

	
	private boolean     	bAddTimeStampTempPath = false;
	private String      	cSubDirForExport = "";

	private Exporter 		oExport = null;

	private MyConnection    oConnectionToDB_toExport = null;
	
	private boolean     	bMakeMigration = false;
	private boolean     	bExportOnlyStructure = false;

	
	/**
	 * 
	 * @param cexportPath
	 * @param bcreateZipDownload
	 * @param bdeleteAfterAction
	 * @param AddTimeStampTempPath
	 * @param gridInfoExportResult (kann null sein)
	 * @param stringBuffer4Protokoll (kann null sein)
	 * @throws myException
	 */
	public ActionAgent_ExportDBStructureAndData(	String 			cexportPath, 
													boolean 		bcreateZipDownload, 
													boolean 		bdeleteAfterAction,
													boolean     	AddTimeStampTempPath) throws myException
	{
		super();
		
		this.cExportPath=cexportPath;
		this.bMakeZipAndDownload = bcreateZipDownload;
		this.bDeleteAfterAction = bdeleteAfterAction;
		this.bAddTimeStampTempPath = AddTimeStampTempPath;
		
		this.oGridInfoExportResult.removeAll();

		if (this.cExportPath.endsWith(File.separator))
			this.cExportPath=this.cExportPath.substring(0,this.cExportPath.length()-1);
		
		File oFile = new File(this.cExportPath);
		
		if (!oFile.exists())
			throw new myException("ActionAgent_ExportDBStructureAndData: Exportpath is not existing: "+cexportPath);
		
		if (!oFile.isDirectory())
			throw new myException("ActionAgent_ExportDBStructureAndData: Exportpath is not a directory: "+cexportPath);
		
	}





	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		if (this.oConnectionToDB_toExport==null)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Die Connection ist nicht aktiv !!"));
			return;
		}
		
		MyConnection oConn = this.oConnectionToDB_toExport;
		
		try
		{
			
			
			if (this.bAddTimeStampTempPath)
			{
				String cHelp = bibALL.get_cDateTimeNOW();
				cHelp = bibALL.ReplaceTeilString(cHelp,":","");
				cHelp = bibALL.ReplaceTeilString(cHelp," ","");
				cHelp = bibALL.ReplaceTeilString(cHelp,".","");
				cHelp = bibALL.ReplaceTeilString(cHelp,"-","");
				this.cSubDirForExport=cHelp;
				
				String cPath = this.cExportPath+File.separator+this.cSubDirForExport;
				File fHelp = new File(cPath);
				if (!fHelp.exists())
					fHelp.mkdir();
			}
			
			
			String 		cCompletePath = "";

			if (this.cSubDirForExport.trim().equals(""))
				cCompletePath=this.cExportPath;
			else
				cCompletePath=this.cExportPath+File.separator+this.cSubDirForExport;

			oExport = new Exporter(oConn,cCompletePath,this.bMakeMigration,this.bExportOnlyStructure);
			
			oExport.doExport();
			
//			/*
//			 * jetzt das protokoll einlesen, damit es, falls gewuenscht, ausgegeben werden kann
//			 */
//
//			File oFileLog = new File(cCompletePath+File.separator+"export.log");
//			if (oFileLog.exists())
//			{
//				List cLog = FileUtils.readLines(oFileLog,"UTF-8");
//	
//				if (this.oLabelInfoExportResult !=null)
//				{
//					this.oLabelInfoExportResult.setText("");
//					String cOuttext = "";
//					for (int i=0;i<cLog.size();i++)
//					{
//						String cHelp =  (String)cLog.get(i);
//						if (!cHelp.equals(""))
//							cOuttext=cOuttext +"\n"+ cHelp;
//					}
//					
//					this.oLabelInfoExportResult.setText((cOuttext));
//				}
//				else if (this.oColumnInfoExportResult != null)
//				{
//					this.oColumnInfoExportResult.removeAll();
//					for (int i=0;i<cLog.size();i++)
//					{
//						String cHelp =  (String)cLog.get(i);
//						if (!cHelp.equals(""))
//							this.oColumnInfoExportResult.add(new MyE2_Label(new MyE2_String(cHelp,false)));
//					}
//				}
//			}
			
			/*
			 * wenn das uebergebene element ein grid war, dann wird eine detailiertere Info ausgegeben
			 */
			if (this.oGridInfoExportResult != null)
			{
				pdProtokollInfos oProt = this.oExport.get_Protokoll();
				this.oGridInfoExportResult.removeAll();
				this.oGridInfoExportResult.setSize(4);
				
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl geschriebene Table-Creates")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(oProt.getNumberOfTables()),2,E2_INSETS.I_2_2_20_2);
				this.oStringBufferInfo.append("Anzahl geschriebene Table-Creates: .......  "+oProt.getNumberOfTables()+"\n");
				

				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl geschriebene Primärschlüssel-Creates")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(oProt.getNumberOfPrimaryKeys()),2,E2_INSETS.I_2_2_20_2);
				this.oStringBufferInfo.append("Anzahl geschriebene Primärschlüssel-Creates: .......  "+oProt.getNumberOfPrimaryKeys()+"\n");
				
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl geschriebene Index-Creates")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(oProt.getNumberOfIndex()),2,E2_INSETS.I_2_2_20_2);
				this.oStringBufferInfo.append("Anzahl geschriebene Index-Creates: .......  "+oProt.getNumberOfIndex()+"\n");
								
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl geschriebene Views-Creates")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(oProt.getNumberOfViews()),2,E2_INSETS.I_2_2_20_2);
				this.oStringBufferInfo.append("Anzahl geschriebene Views-Creates: .......  "+oProt.getNumberOfViews()+"\n");
				
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl geschriebene Sequencer-Creates")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(oProt.getNumberOfSequences()),2,E2_INSETS.I_2_2_20_2);
				this.oStringBufferInfo.append("Anzahl geschriebene Sequencer-Creates: .......  "+oProt.getNumberOfSequences()+"\n");

				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl geschriebene Fremdschlüssel-Creates")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(oProt.getNumberOfForeignKeys()),2,E2_INSETS.I_2_2_20_2);
				this.oStringBufferInfo.append("Anzahl geschriebene Fremdschlüssel-Creates: .......  "+oProt.getNumberOfForeignKeys()+"\n");
				
				this.oStringBufferInfo.append("----------------------------------------------------------------\n");
				
				this.oGridInfoExportResult.add(new Separator(),4,E2_INSETS.I_2_2_2_2);

				MutableStyle oStyle = MyE2_Label.STYLE_NORMAL_BOLD_ITALIC();
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Tabelle"),oStyle),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Anzahl Datensätze"),oStyle),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("Exportierte Datensätze"),oStyle),E2_INSETS.I_2_2_20_2);
				this.oGridInfoExportResult.add(new MyE2_Label(new MyE2_String("OK?"),oStyle),E2_INSETS.I_2_2_20_2);
				
				for (int i=0;i<oProt.get_NumberOfExportInfos();i++)
				{
					this.oGridInfoExportResult.add(new MyE2_Label(oProt.get_Tablename(i)),E2_INSETS.I_2_2_20_2);
					this.oGridInfoExportResult.add(new MyE2_Label(oProt.get_CountToExport(i)),E2_INSETS.I_2_2_20_2);
					this.oGridInfoExportResult.add(new MyE2_Label(oProt.get_CountExported(i)),E2_INSETS.I_2_2_20_2);
					this.oGridInfoExportResult.add(oProt.get_CountToExport(i).equals(oProt.get_CountExported(i))?
															new MyE2_Label("**OK**"):new MyE2_Label("**ERR**"),E2_INSETS.I_2_2_20_2);
					
					this.oStringBufferInfo.append("Tabelle: " +oProt.get_Tablename(i)+ 	" vorhandene Datensätze:  "+oProt.get_CountToExport(i)+
																						" geschrieben Datensätze:  "+oProt.get_CountExported(i)+
																						(oProt.get_CountToExport(i).equals(oProt.get_CountExported(i))?
																								"**OK**\n":"**ERR**\n"));
				}
			}
			
			
			/*
			 * jetzt eine zipdatei erzeugen
			 */
			/*
			 * jetzt in die zip-datei haengen
			 */
			myTempFile oTempFile = new myTempFile("DATA_EXPORT", ".ZIP", true);
			myZipper zipOut    = new myZipper(new FileOutputStream(oTempFile.getFile()));

			String[] cFileList = new File(cCompletePath).list();
			
			
			if (this.bMakeZipAndDownload)
			{
				for (int i=0;i<cFileList.length;i++)
				{
					String cGesamtPfad = cCompletePath+File.separator+cFileList[i];
					zipOut.addFileToZip(cFileList[i],cGesamtPfad);
				}
					
				zipOut.close();
				oTempFile.close();
				
				E2_Download oDown = new E2_Download();
				oDown.starteFileDownload(oTempFile.getFileName(),"DATADUMP.ZIP","application/zip");
			}
			
			if (this.bDeleteAfterAction)
			{
				for (int i=0;i<cFileList.length;i++)
				{
					String cGesamtPfad = cCompletePath+File.separator+cFileList[i];
					if (!new File(cGesamtPfad).delete())
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("ActionAgent_ExportDBStructureAndData:doAction: Error with deleting exported files !",false)));
						break;
					}
				}
				
				// jetzt den ordner loeschen (nur wenn der datums-zusatzpfad definiert wurde)
				if (!this.cExportPath.equals(cCompletePath))
					new File(cCompletePath).delete();
			}
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("ActionAgent_ExportDBStructureAndData:Constructor: Error: "+ex.getMessage(),false)));
		}
		finally
		{
			if (oConn !=null)
				oConn = null;
		}
	
	}

	public Exporter get_oExport()
	{
		return oExport;
	}

	public MyConnection get_oConnectionToDB_toExport() 
	{
		return oConnectionToDB_toExport;
	}

	public void set_oConnectionToDB_toExport(MyConnection connectionToDB_toExport) 
	{
		oConnectionToDB_toExport = connectionToDB_toExport;
	}

	public void set_bExportOnlyStructure(boolean exportOnlyStructure) 
	{
		bExportOnlyStructure = exportOnlyStructure;
	}

	public void set_bMakeMigration(boolean makeMigration) 
	{
		bMakeMigration = makeMigration;
	}

	public MyE2_Grid get_oGridInfoExportResult()
	{
		return oGridInfoExportResult;
	}

	public StringBuffer get_cStringBufferInfo()
	{
		return oStringBufferInfo;
	}

}
