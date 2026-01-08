package rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.AM_BasicContainer;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BT_UpDownload_PrintProtokollAttachtments extends MyE2_ButtonInLIST {

	private static GridLayoutData layoutInList = new GridLayoutData();
	{
		layoutInList.setInsets(E2_INSETS.I(0,0,0,0));
		layoutInList.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
	}
	
	private String 	table_name  = null;
	
	/**
	 * 
	 * @param tablename_protokoll
	 * @throws myException
	 */
	public BT_UpDownload_PrintProtokollAttachtments(String tablename_protokoll) 	throws myException {
		super(E2_ResourceIcon.get_RI("attach_mini.png"), true);
		this.table_name = tablename_protokoll;
		this.setToolTipText(new MyE2_String("Zusatzdateien und Anlagen zum Druckprotokoll").CTrans());
		this.EXT().set_oCompTitleInList(new MyE2_Label(E2_ResourceIcon.get_RI("attach_mini.png")));
		this.EXT().set_oLayout_ListElement(BT_UpDownload_PrintProtokollAttachtments.layoutInList);
		
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try {
			return new BT_UpDownload_PrintProtokollAttachtments(this.table_name);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	
	
	
	private class ownActionAgent extends XX_ActionAgent {
		public ownActionAgent() {
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo){
			
			BT_UpDownload_PrintProtokollAttachtments oThis = BT_UpDownload_PrintProtokollAttachtments.this;
			
			try	{
				String cID = 			oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				String cTableName = 	oThis.table_name;
				AM_BasicContainer oPopUp = new AM_BasicContainer(		cTableName,
																		cID,
																		null,
																		true);
			
				oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien zu Druckprotokoll-Einträgen ..."));
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Download-Window: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}

	}

	
}
