package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class E2_Button_ConnectToOtherObjects extends MyE2_Button
{
	 
	RECORD_ARCHIVMEDIEN_ext 	m_oRec  			= null;
	private String 				m_cModulKenner 		= null;
	private Vector<String> 		m_vIDArchivmedien 	= null;
	

	
	public E2_Button_ConnectToOtherObjects(String cMODULKENNER, String idArchivmedien)
	{
		this(cMODULKENNER,bibALL.get_Vector(idArchivmedien));
	}
	
	
	public E2_Button_ConnectToOtherObjects(String cMODULKENNER,E2_NavigationList onavigationList )
	{
		this(cMODULKENNER, onavigationList.get_vSelectedIDs_Unformated());
	}

	
	public E2_Button_ConnectToOtherObjects(String cMODULKENNER, Vector<String> vidArchivmedien)
	{
		super(E2_ResourceIcon.get_RI("attach_mini.png"), E2_ResourceIcon.get_RI("leer.png"));
		m_vIDArchivmedien = vidArchivmedien;

		if (m_cModulKenner!=null) this.add_GlobalValidator(new E2_ButtonAUTHValidator(m_cModulKenner,"DIALOG_ZUSATZDATEIEN_VERKNUEPFEN"));
		this.setToolTipText(new MyE2_String("Diesen Anhang mit weiteren Objekten verbinden.").CTrans() );
		
		this.add_oActionAgent(new ownActionAgent());
		
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		
		public ownActionAgent()
		{
			super();
		}



		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			E2_Button_ConnectToOtherObjects oThis = E2_Button_ConnectToOtherObjects.this;
			
			
			try
			{
				
				PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS oPopUp = new PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS(
																		oThis.m_vIDArchivmedien,
																		oThis.m_cModulKenner
																		);
			
				oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Verknüpfen von Archivdateien... "));
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Window for additional Connection "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}

	}
	
	
}
