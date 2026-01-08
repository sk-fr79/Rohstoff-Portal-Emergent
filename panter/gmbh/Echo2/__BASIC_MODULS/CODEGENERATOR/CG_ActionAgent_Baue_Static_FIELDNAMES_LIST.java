package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.exceptions.myException;


public class CG_ActionAgent_Baue_Static_FIELDNAMES_LIST extends XX_ActionAgent
{

	private String[][] 		cTables = null;
	private MyE2_Column  	oColOutput = null;
	
	private String path = 	null;

	
	public CG_ActionAgent_Baue_Static_FIELDNAMES_LIST(String[][] tables, MyE2_Column ColOutput)
	{
		super();
		this.cTables = tables;
		this.oColOutput = ColOutput; 
	}



	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		
		//ausgabepfade anlegen
		File outFolderREC = new File(bibALL.get_CompleteOutPutPath(true)+"DB_RECORDS");
		if (!outFolderREC.exists()) {
			try {
				FileUtils.forceMkdir(outFolderREC);
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new myException("Please clear /../OUTPUT/");
			}
		}
		this.path = bibALL.get_CompleteOutPutPath(true)+"DB_RECORDS"+File.separator;

		
		/*
		 * erst einen Vector bauen, der alle ID_<TABLE_NAMES> enthaelt.  Wenn in einer tabelle ein solcher 
		 * vorkommt, dann kann ein MAP-Object zur Vater-Tabelle aufgebaut werden
		 */
		Vector<String> vSammleAlleTabellen = new Vector<String>();
		for (int i=0;i<this.cTables.length;i++)
		{
			vSammleAlleTabellen.add(this.cTables[i][0]);
		}
		
		this.oColOutput.removeAll();
		
		
		StringBuffer cStaticClass= new StringBuffer("package panter.gmbh.basics4project.DB_RECORDS;\n\n");
		
		
		String 	cOutName_StaticClassFile = 	this.path+"_DB.java";
		File 	oFileOut_RECORD = 			new File(cOutName_StaticClassFile);
		
		
		cStaticClass.append("\n");
		cStaticClass.append("public class _DB \n");
		cStaticClass.append("{\n");
		

		
		
		
		for (int i=0;i<this.cTables.length;i++)
		{
			if (this.cTables[i][0].toUpperCase().startsWith("JT_")||this.cTables[i][0].toUpperCase().startsWith("JD_"))
			{
			
				String cTabLang = this.cTables[i][0];
				String cTabKurz = this.cTables[i][0].substring(3);
				cStaticClass.append("public static final String "+cTabKurz+"=\""+this.cTables[i][0]+"\";\n");
				//2015-03-10: sequencer in den namespace mitaufnehmen
				cStaticClass.append("public static final String "+cTabKurz+"$$SEQ=\"SEQ_"+cTabKurz+"\";\n");
				cStaticClass.append("public static final String "+cTabKurz+"$$SEQ_NEXT=\"SEQ_"+cTabKurz+".NEXTVAL\";\n");
				cStaticClass.append("public static final String "+cTabKurz+"$$SEQ_CURR=\"SEQ_"+cTabKurz+".CURRVAL\";\n");
				//2015-03-10: ende
				
				Vector<String>  vFields = DB_META.get_ORA_FIELDS(this.cTables[i][0]);
				
				for (String cField: vFields)
				{
					cStaticClass.append("public static final String "+cTabKurz+"$"+cField+"=\""+cField+"\";\n");
				}
				
				//2014-01-21: weiterer Static-Block, der ein Feld in der Schreibweise: TABELLE.FELD uebergibt (fuer SQL-Statements)
				for (String cField: vFields)
				{
					cStaticClass.append("public static final String "+"Z_"+cTabKurz+"$"+cField+"=\""+cTabLang+"."+cField+"\";\n");
				}
				
				
			}
		}
		
		cStaticClass.append("\n");
		cStaticClass.append("}\n");

		
		try
		{
		
			FileUtils.writeStringToFile(oFileOut_RECORD, cStaticClass.toString(), "UTF-8");
		}
		catch (IOException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error writing file: "+cOutName_StaticClassFile));
		}
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message("_DB.java is generated !"));
	}



}
