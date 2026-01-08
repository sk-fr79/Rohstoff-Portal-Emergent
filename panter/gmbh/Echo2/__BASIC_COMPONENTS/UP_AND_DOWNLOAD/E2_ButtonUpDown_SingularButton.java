package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonUpDown_SingularButton extends MyE2_Button
{
	
	
	// falls man einen Medienkenner direkt angeben will
	private String              m_cMedienkenner = null;

	
	
	//2015-01-30: plugin um die elemente in der liste, die die zugeordnete id_email_send - zugehoerigkeit zu erzeugen
	private UP_DOWN_AddOn_COL_ComponentFactory  id_EMAIL_COMPONENT_FACTORY = null;

	
	
	// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
	private Vector<XX_ActionAgentWhenCloseWindow> vCloseActions = new Vector<XX_ActionAgentWhenCloseWindow>();


	private String c_tablenameBasic = null;
	private String c_id_table_uf = null;
	private String c_modulkenner = null;
	

	/**
	 * 
	 * @param tablenameBasic
	 * @param id_table_uf
	 * @param modulkenner_calling
	 */
	public E2_ButtonUpDown_SingularButton(String tablenameBasic, String id_table_uf, String modulkenner_calling) {
		super(E2_ResourceIcon.get_RI("attach.png"), E2_ResourceIcon.get_RI("leer.png"));

		this.c_tablenameBasic = tablenameBasic;
		this.c_id_table_uf = id_table_uf;
		this.c_modulkenner = modulkenner_calling;
		this.add_oActionAgent(new ownActionAgent());
	}

	/**
	 * 
	 * @param tablenameBasic
	 * @param id_table_uf
	 * @param modulkenner_calling
	 * @param bMini (true=mini-Icon)
	 */
	public E2_ButtonUpDown_SingularButton(String tablenameBasic, String id_table_uf, String modulkenner_calling, boolean bMini) {
		super(E2_ResourceIcon.get_RI(bMini?"attach_mini.png":"attach.png"), E2_ResourceIcon.get_RI("leer.png"));

		this.c_tablenameBasic = tablenameBasic;
		this.c_id_table_uf = id_table_uf;
		this.c_modulkenner = modulkenner_calling;
		this.add_oActionAgent(new ownActionAgent());
	}



	public UP_DOWN_AddOn_COL_ComponentFactory get_id_EMAIL_COMPONENT_FACTORY() {
		return id_EMAIL_COMPONENT_FACTORY;
	}



//
//	public void set_id_EMAIL_COMPONENT_FACTORY(UP_DOWN_AddOn_COL_ComponentFactory idEMAIL_COMONENT_FACTORY) {
//		this.id_EMAIL_COMPONENT_FACTORY = idEMAIL_COMONENT_FACTORY;
//	}
//


	
	
	
	private class ownActionAgent extends XX_ActionAgent	{
		public ownActionAgent()	{
			super();
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Vector<String> vIDs_Selected_Unformated = new Vector<String>();
			vIDs_Selected_Unformated.add(E2_ButtonUpDown_SingularButton.this.c_id_table_uf);
			
			
			if (vIDs_Selected_Unformated.size() != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(new MyE2_String("Um Zusatzdateien zu bearbeiten MUSS GENAU EINE Satz-ID übergeben werden !!").CTrans()));
			}
			else
			{
				try
				{
					String cID = 			vIDs_Selected_Unformated.get(0);
					String cTableName = 	E2_ButtonUpDown_SingularButton.this.c_tablenameBasic;
					
					
					AM_BasicContainer oPopUp = new AM_BasicContainer(		cTableName,
																			cID,
																			E2_ButtonUpDown_SingularButton.this.c_modulkenner,
																			null,
																			true,
																			E2_ButtonUpDown_SingularButton.this.m_cMedienkenner,
																			E2_ButtonUpDown_SingularButton.this.id_EMAIL_COMPONENT_FACTORY);
					
					// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
					if (E2_ButtonUpDown_SingularButton.this.get_vActionAgentWhenCloseWindow().size()>0) {
						for (XX_ActionAgentWhenCloseWindow action: E2_ButtonUpDown_SingularButton.this.get_vActionAgentWhenCloseWindow()) {
							oPopUp.add_CloseActions(action);
						}
					}
					
					/*
					 * 2015-01-30: falls in der liste die komponente der Spalte ID_EMAIL_SEND eine aktion bekommen soll, dann wird hier die
					 * factory-klasse, die die jeweilige komponente erzeugt, uebergeben
					 */
					if (E2_ButtonUpDown_SingularButton.this.get_id_EMAIL_COMPONENT_FACTORY()!=null) {
						oPopUp.set_id_EMAIL_COMONENT_FACTORY(E2_ButtonUpDown_SingularButton.this.get_id_EMAIL_COMPONENT_FACTORY());
					}
					
					oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien ..."));
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






	// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
	public Vector<XX_ActionAgentWhenCloseWindow> get_vActionAgentWhenCloseWindow() 	{
		return vCloseActions;
	}


	
}
