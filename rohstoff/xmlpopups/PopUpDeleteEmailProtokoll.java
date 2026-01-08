package rohstoff.xmlpopups;

import java.io.File;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.xmlDefTools.XX_ModuleContainerListEditPopup;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class PopUpDeleteEmailProtokoll extends XX_ModuleContainerListEditPopup 
{
	
	private MyE2_String 	cError = null;
	private MyE2_Column   	oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	private Vector<String>  vIds = null;
	
	
	public void build_Content() throws myException 
	{
		this.vIds = this.get_oNavigationListFromListModule().get_vSelectedIDs_Unformated();
		
		if (vIds.size()==0)
		{
			this.cError = new MyE2_String("Bitte markieren Sie die gewünschten Protokoll-Einträge zum Löschen !");
		}
		else
		{
			this.get_oContainer().add(this.oColBasic);
			this.oColBasic.add(new MyE2_Label(new MyE2_String("Sind Sie sicher ! Protokolle und Anlagen löschen ?"),MyE2_Label.STYLE_ERROR_BIG()),new Insets(10,10,10,10));
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
					PopUpDeleteEmailProtokoll.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
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
					PopUpDeleteEmailProtokoll oThis = PopUpDeleteEmailProtokoll.this;
					
					int iCount=0;
					
					for (int i=0;i<oThis.vIds.size();i++)
					{
						MyDataRecordHashMap oHM = new MyDataRecordHashMap("JT_EMAIL_PROTOKOLL",(String)oThis.vIds.get(i));
						String cID_EMAIL_PROTOKOLL = oHM.get_UnFormatedValue("ID_EMAIL_PROTOKOLL");
						String cDATEINAME = oHM.get_UnFormatedValue("ANHANG_ARCHIV_NAME");
						
						if (bibALL.isEmpty(cDATEINAME))
						{
							if (bibDB.ExecSQL("DELETE FROM "+bibE2.cTO()+".JT_EMAIL_PROTOKOLL WHERE ID_EMAIL_PROTOKOLL="+cID_EMAIL_PROTOKOLL, true))
							{
								iCount++;
							}
						}
						else
						{
							if ( new File(cDATEINAME).delete())
							{
								if (bibDB.ExecSQL("DELETE FROM "+bibE2.cTO()+".JT_EMAIL_PROTOKOLL WHERE ID_EMAIL_PROTOKOLL="+cID_EMAIL_PROTOKOLL, true))
								{
									iCount++;
								}
							}
						}
					}
					oThis.get_oNavigationListFromListModule()._REBUILD_COMPLETE_LIST("");
					oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Gelöschte Einträge: "+iCount)));
				}
			});
		}
	}
	
	public MyString get_Check_CanBeShown() 
	{
		return this.cError;
	}

	
	
	

	
	
}
