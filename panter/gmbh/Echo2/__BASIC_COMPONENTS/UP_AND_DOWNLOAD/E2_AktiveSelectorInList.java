package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_AktiveSelectorInList extends MyE2_DB_SelectField
{

	private SQLField 		oSqlField = null; 
	private String[][] 		aDefArray = null;
	
	
	public E2_AktiveSelectorInList(SQLField osqlField, String[][] defArray) throws myException
	{
		super(osqlField, defArray, false);
		this.oSqlField = osqlField;
		this.aDefArray = defArray;
		this.add_oActionAgent(new ownActionAgent());
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			return new E2_AktiveSelectorInList(this.oSqlField, this.aDefArray);
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy("Error copy: "+e.getOriginalMessage());
		}
		
	}
	
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
	}
 
	//eigene disable-methode fuer die via XX_ComponentMAP_SubqueryAGENT
	public void set_bDisabled() throws myException {
		super.set_bEnabled_For_Edit(false);
	}

	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP  oMAP = E2_AktiveSelectorInList.this.EXT().get_oComponentMAP();
			String cID_ARCHIVMEDIEN = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			String cNewValueProgrammkenner = E2_AktiveSelectorInList.this.get_ActualWert();
			RECORD_ARCHIVMEDIEN recArchivMedien = new RECORD_ARCHIVMEDIEN(cID_ARCHIVMEDIEN);
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			oMV.add_MESSAGE(recArchivMedien.set_NEW_VALUE_PROGRAMM_KENNER(cNewValueProgrammkenner));
			oMV.add_MESSAGE(recArchivMedien.UPDATE(true));
			if (oMV.get_bIsOK()) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Wert wurde gespeichert ...")));
			} else {
				bibMSG.add_MESSAGE(oMV);
			}
		}
	}
	
	
}
