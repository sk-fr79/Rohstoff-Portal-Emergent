package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.io.File;
import java.util.Vector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class BT_BuildJaspers extends MyE2_Button
{
	private MyE2_TextArea 		oText4Output = null;
	private Vector<String>   	vPathToCompile = new Vector<String>();
//	private MyString 			cButtonText = null;
	
	
	/**
	 * @param cPathWithSeparators
	 * @param ocolForOutput
	 * @param omessageAgent
	 */
	public BT_BuildJaspers(MyString cbuttonText,String cPathWithSeparators, MyE2_TextArea Text4Output,  String cMODUL_KENNER , String cBUTTONKENNER )
	{
		super(cbuttonText);
//		this.cButtonText = cbuttonText;
		this.vPathToCompile.removeAllElements();
		this.vPathToCompile.add(cPathWithSeparators);
		this.oText4Output = Text4Output;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODUL_KENNER,cBUTTONKENNER));
	}

	
	/**
	 * @param cPathWithSeparators
	 * @param ocolForOutput
	 * @param omessageAgent
	 */
	public BT_BuildJaspers(MyString cbuttonText,Vector<String> vPathWithSeparators, MyE2_TextArea Text4Output,  String cMODUL_KENNER , String cBUTTONKENNER )
	{
		super(cbuttonText);
//		this.cButtonText = cbuttonText;
		this.vPathToCompile.removeAllElements();
		this.vPathToCompile.addAll(vPathWithSeparators);
		this.oText4Output = Text4Output;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODUL_KENNER,cBUTTONKENNER));
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
//				int iCountALL_OK 	= 0;
//				int iCountALL_ERROR = 0;

				Vector<String> vErrors = new Vector<String>();
				Vector<String> vCompiled = new Vector<String>();
				for (int i=0;i<BT_BuildJaspers.this.vPathToCompile.size();i++)
				{
					BT_BuildJaspers.CompileAllJaspers(BT_BuildJaspers.this.vPathToCompile.get(i),vCompiled,vErrors);
					MyE2_String cErgebnis = new MyE2_String("Ergebnis der Aktion: ");
					cErgebnis.addUnTranslated(BT_BuildJaspers.this.vPathToCompile.get(i));

					cErgebnis.addUnTranslated(""+"\n");
					cErgebnis.addUnTranslated(""+"\n");
					cErgebnis.addUnTranslated("--------------------------------- OK --------------------------------------------------"+"\n");
					for (int k=0;k<vCompiled.size();k++)
					{
						cErgebnis.addUnTranslated(vCompiled.get(k)+"\n");
					}
					cErgebnis.addUnTranslated("--------------------------------------------------------------------------------------"+"\n");
					cErgebnis.addUnTranslated(""+"\n");

					cErgebnis.addUnTranslated(""+"\n");
					cErgebnis.addUnTranslated(""+"\n");
					cErgebnis.addUnTranslated("--------------------------------- FEHLER -----------------------------------------------"+"\n");
					for (int k=0;k<vErrors.size();k++)
					{
						cErgebnis.addUnTranslated(vErrors.get(k)+"\n");
					}
					cErgebnis.addUnTranslated("--------------------------------------------------------------------------------------"+"\n");
					cErgebnis.addUnTranslated(""+"\n");
					
					BT_BuildJaspers.this.oText4Output.setText(BT_BuildJaspers.this.oText4Output.getText()+"\n"+cErgebnis.CTrans());
				}
				
				if (vErrors.size()>0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind Fehler beim kompilieren aufgetreten !"));
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
			
			
		}	
	}
	
	
	/*
	 * kompiliert alle jrxml-dateien in einem pfad
	 * iRueck[0] = ok, iRueck[1] = fehler
	 */
	public static void CompileAllJaspers(String cPathNameWithSeparators,Vector<String> vOK, Vector<String> vErrors) throws myException
	{
		//int[] iRueck = new int[2];
		
//		iRueck[0]=0;
//		iRueck[1]=0;
		
		File ofileDir = new File(cPathNameWithSeparators);
		
		//es kann immer sein, dass der uebergebene ordner nicht existiert (z.B. wenn ein report keine subreports hat)
		if (!ofileDir.exists())
		{
			return;
		}
		
		
		if (!ofileDir.isDirectory())
		{
			vErrors.add(new MyE2_String("Der übergebene Name ist kein Ordner: ").CTrans()+cPathNameWithSeparators);
			return;
		}
		
		String[] cJasperFiles = ofileDir.list();
		if (cJasperFiles == null || cJasperFiles.length==0)
		{
			//vErrors.add(new MyE2_String("Der übergebene Name enthält keine Dateien: ").CTrans()+cPathNameWithSeparators);
			return;
		}
		
		Vector<String> vJaspers = new Vector<String>();
		
		for (int i=0;i<cJasperFiles.length;i++)
		{
			File fileTest = new File(cPathNameWithSeparators+cJasperFiles[i]);
			
			if (fileTest.isDirectory() && (!cJasperFiles[i].startsWith(".")))       //nur die nicht versteckten ordner in die recursion aufnehmen
			{
				//recursiver aufruf
				BT_BuildJaspers.CompileAllJaspers(cPathNameWithSeparators+cJasperFiles[i]+File.separator, vOK, vErrors);
			}
			else
			{
				if (cJasperFiles[i].endsWith(".jrxml"))
					vJaspers.add(cJasperFiles[i]);
			}
		}
		
		if (vJaspers.size()==0)
		{
//			vErrors.add(new MyE2_String("Der übergebene Name enthält keine jrxml-Dateien: ").CTrans()+cPathNameWithSeparators);
			return;
		}
		
		for (int i=0;i<vJaspers.size();i++)
		{
			String cName = (String)vJaspers.get(i);
			cName = cName.substring(0,cName.length()-5);
			String cQuellName = cPathNameWithSeparators+cName+"jrxml";
			String cZielName = cPathNameWithSeparators+cName+"jasper";
			try
			{
				JasperCompileManager.compileReportToFile(cQuellName,cZielName);
//				System.out.println(cQuellName);
				vOK.add(cQuellName);

			}
			catch (JRException ex)
			{
				ex.printStackTrace();
				vErrors.add(cQuellName+"      ------> "+ex.getLocalizedMessage());
			}
		}
		
		return;
		
	}

	
	
}
