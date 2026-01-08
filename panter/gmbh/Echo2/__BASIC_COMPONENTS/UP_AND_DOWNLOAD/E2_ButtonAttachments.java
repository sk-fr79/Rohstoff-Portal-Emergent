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
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;




public abstract class E2_ButtonAttachments extends E2_Button {

	// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
	private Vector<XX_ActionAgentWhenCloseWindow> vCloseActions = new Vector<XX_ActionAgentWhenCloseWindow>();

	public abstract Long   						getIdTableForAttachment() throws myException;
	public abstract _TAB   						getTableForAttachment() throws myException;
	public abstract E2_MODULNAME_ENUM.MODUL   	getModule() throws myException;
	

	/**
	 * to override, when needed
	 * @return
	 * @throws myException
	 */
	public UP_DOWN_AddOn_COL_ComponentFactory   getAddOnComponentFactory() throws myException {
		return  null;
	}
	
	/**
	 * 
	 * to override, when needed
	 * @return
	 * @throws myException
	 */
	public String   							getMedienKenner() throws myException {
		return  null;
	}

	

	/**
	 * 
	 * @param tablenameBasic
	 * @param id_table_uf
	 * @param modulkenner_calling
	 */
	public E2_ButtonAttachments() {
		super();
		
		this._image(E2_ResourceIcon.get_RI("attach.png"), E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent());
		
	}


	
	
	private class ownActionAgent extends XX_ActionAgent	{
		public ownActionAgent()	{
			super();
		}


		public void executeAgentCode(ExecINFO oExecInfo)  throws myException{

			Long  id = getIdTableForAttachment();
			
			_TAB  table = getTableForAttachment();
			MODUL module =  getModule();

			if (id==null) {
				bibMSG.MV()._addAlarm("Bitte genau eine Zeile in der Liste auswählen, um Anlagen zu bearbeiten !");
			} else if (table == null || module==null) {
				bibMSG.MV()._addAlarm("Um Zusatzdateien zu bearbeiten MUSS GENAU EINE Satz-ID, die Tabelle sowie das betreffende Modul für die Anlagen übergeben werden !!");
			} else 	{
				
				try {
					
					String s_id = 		id.toString();
					String tabName = 	table.baseTableName();
					
					
					AM_BasicContainer oPopUp = new AM_BasicContainer(		tabName,
																			s_id,
																			module.get_callKey(),
																			null,
																			true,
																			getMedienKenner(),
																			getAddOnComponentFactory());
					
					// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
					if (E2_ButtonAttachments.this.get_vActionAgentWhenCloseWindow().size()>0) {
						for (XX_ActionAgentWhenCloseWindow action: E2_ButtonAttachments.this.get_vActionAgentWhenCloseWindow()) {
							oPopUp.add_CloseActions(action);
						}
					}
					
					oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien ..."));
					
				}  catch (myException ex) {
					
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
