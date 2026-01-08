package rohstoff.xmlpopups;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.xmlDefTools.XX_ModuleContainerListEditPopup;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class PopUpDownloadEmailProtokoll extends XX_ModuleContainerListEditPopup 
{
	
	private MyE2_String 	cError = null;
	private MyE2_Column   	oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	private Vector<String>  vIds = null;
	
	
	public void build_Content() throws myException 
	{
		this.vIds = this.get_oNavigationListFromListModule().get_vSelectedIDs_Unformated();
		
		if (vIds.size()!=1)
		{
			this.cError = new MyE2_String("Bitte wählen Sie genau EIN eMail-Protokoll aus !!");
		}
		else
		{
			this.get_oContainer().add(this.oColBasic);
			this.oColBasic.add(new MyE2_Label(new MyE2_String("Download starten ?"),MyE2_Label.STYLE_ERROR_BIG()),new Insets(10,10,10,10));
			this.oColBasic.add(new E2_ComponentGroupHorizontal(0,new buttonOK(),new buttonAbbruch(), E2_INSETS.I_0_0_10_0),new Insets(10,30,10,10));
		}
	}

	
	private class buttonAbbruch extends MyE2_Button
	{
		public buttonAbbruch()
		{
			super(new MyE2_String("NEIN - Abbruch"));
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					PopUpDownloadEmailProtokoll.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}
	
	
	private class buttonOK extends MyE2_Button
	{
		public buttonOK()
		{
			super(new MyE2_String("JA"));
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					PopUpDownloadEmailProtokoll oThis = PopUpDownloadEmailProtokoll.this;
					

					MyDataRecordHashMap oHM = new MyDataRecordHashMap("JT_EMAIL_PROTOKOLL",(String)oThis.vIds.get(0));
					String cDATEINAME = oHM.get_UnFormatedValue("ANHANG_ARCHIV_NAME");
					String cMimeType = 	bibALL.null2leer(oHM.get_UnFormatedValue("MIME_TYPE"));
					
					if (bibALL.isEmpty(cMimeType))
					{
						cMimeType = "unknown";
					}
					if (!bibALL.isEmpty(cDATEINAME))
					{
						E2_Download oDownload = new E2_Download();
						oDownload.starteFileDownload(cDATEINAME, cMimeType);
						oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);

					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Diese Mail hatte keine Anlage !!"));
					}
				}
			});
		}
	}
	
	public MyString get_Check_CanBeShown() 
	{
		return this.cError;
	}

	
	
}
