package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SELECTOR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.myTempFileAutoDel;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SEL__PRUEFBUTTON extends MyE2_ButtonInLIST
{
	
	private E2_BasicModuleContainer oCallingModuleContainer = null;
	private myTempFileAutoDel 		oTempfile = null;
	
	
	public SEL__PRUEFBUTTON(E2_BasicModuleContainer CallingContainer)
	{
		//super(E2_ResourceIcon.get_RI("sql_button.png"), true);
		super(new MyE2_String("SQL"));
		
		this.oCallingModuleContainer = CallingContainer;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Abfrage mit dem Where-Statement simulieren ...").CTrans());
	}
	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			return new SEL__PRUEFBUTTON(this.oCallingModuleContainer);
		}
		catch (Exception ex)
		{
			throw new myExceptionCopy("Error Copying SQL-Test-Button");
		}
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			SEL__PRUEFBUTTON btTest = (SEL__PRUEFBUTTON)oExecInfo.get_MyActionEvent().getSource();
			
			//System.out.println(btTest.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			
			String cWhereBlock = "";
			
			if (btTest.EXT().get_oComponentMAP() != null && btTest.EXT().get_oComponentMAP().get_STATUS_LAST_FILL().equals(E2_ComponentMAP.STATUS_VIEW))
			{
				//dann ist der button in einer zeile der navilist
				RECORD_SELECTOR  recSel = new RECORD_SELECTOR(btTest.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				cWhereBlock = recSel.get_WHEREBLOCK_cF_NN("1=1");
			}
			else
			{
				//dann in der maske
				cWhereBlock = btTest.EXT().get_oComponentMAP().get_cActualDBValueFormated(_DB.SELECTOR$WHEREBLOCK, "1=1");
			}
			
			
			SEL__PRUEFBUTTON oThis = SEL__PRUEFBUTTON.this;
			
			String cSQL_Query = null;
			
			if (oThis.oCallingModuleContainer.get_oNaviListFirstAdded() != null)
			{
				SQLFieldMAP  oSQLMap = null;
				
				if (oThis.oCallingModuleContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF() != null)
				{
					oSQLMap = oThis.oCallingModuleContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP();
					
					if (oSQLMap != null)
					{
						cSQL_Query = bibReplacer.ReplaceSysvariablesInStrings(oSQLMap.get_CompleteSQLQueryFor_ID_VECTOR(cWhereBlock, false));
						
						try
						{
							oThis.oTempfile = new myTempFileAutoDel("_temp_sql_info", ".txt", true);
							oTempfile.WriteTextBlock(cSQL_Query);
							oTempfile.close();
							
							
							oTempfile.starteDownLoad("query.sql", "Application/text");
						}
						catch (Exception ex)
						{
							throw new myException(ex.getLocalizedMessage());
						}
						
					}
				}
				
			}
			
			
			
		}
		
	}
}
