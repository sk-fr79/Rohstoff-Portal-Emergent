package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.POI_TOOLS.MyHSSFWorkBookForNavigationList_flat;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonWriteListToExcel extends MyE2_Button
{
	private E2_NavigationList 	oNavigationList = null;
	private MyString 			cDownloadName = null;
	
	public E2_ButtonWriteListToExcel(E2_NavigationList onavigationlist, MyString DownloadName)
	{
		super(E2_ResourceIcon.get_RI("excelexport.png"),true);
		this.oNavigationList = onavigationlist;
		this.cDownloadName = DownloadName;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText((new MyE2_String("Exportieren der Liste in eine Excel-Datei ...")).CTrans());
	}

	/**
	 * 22013-11-07: mit auth-code-uebergabe
	 * @param onavigationlist
	 * @param DownloadName
	 * @param AUTHCODE
	 */
	public E2_ButtonWriteListToExcel(E2_NavigationList onavigationlist, MyString DownloadName, String AUTHCODE)
	{
		super(E2_ResourceIcon.get_RI("excelexport.png"),true);
		this.oNavigationList = onavigationlist;
		this.cDownloadName = DownloadName;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText((new MyE2_String("Exportieren der Liste in eine Excel-Datei ...")).CTrans());
		
		if (S.isFull(AUTHCODE)) {
			this.add_GlobalAUTHValidator_AUTO(AUTHCODE);
		}
		
	}

	
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ButtonWriteListToExcel oThis = E2_ButtonWriteListToExcel.this;
			try
			{
//				new MyHSSFWorkBookForNavigationList(
//						new myTempFile("export","xls",true),oThis.cDownloadName.CTrans(),oThis.oNavigationList);
				
				new MyHSSFWorkBookForNavigationList_flat(
						new myTempFile("export","xls",true),oThis.cDownloadName.CTrans(),oThis.oNavigationList);
				
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Export erfolgreich !").CTrans()));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
	
	
}
