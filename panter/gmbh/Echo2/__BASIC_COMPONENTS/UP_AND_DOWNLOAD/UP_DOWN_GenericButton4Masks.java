package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.exceptions.myException;


/**
 * up-und download -button, kann auf jedem E2_BasicModuleContainer_MASK  eingesetzt werden
 * @author martin
 *
 */
public class UP_DOWN_GenericButton4Masks extends MyE2_Button {
	
	private E2_ComponentMAP  	map = null;
	private String         		moduleKey = null;

	
	/**
	 * 
	 * @param p_map
	 * @param p_moduleKey
	 * @param toolTips (can be null)
	 * @param validator (can be null)
	 */
	public UP_DOWN_GenericButton4Masks(E2_ComponentMAP  p_map, String p_moduleKey, MyString toolTips, XX_ActionValidator validator) {
		super(E2_ResourceIcon.get_RI("attach_mini.png"), E2_ResourceIcon.get_RI("leer.png"));
		
		this.map = 			p_map;
		this.moduleKey = 	p_moduleKey;
		
		this.add_oActionAgent(new ownActionAgent());
		
		if (toolTips!=null) {
			this.setToolTipText(toolTips.CTrans());
		}
		
		if (validator !=null) {
			this.add_GlobalValidator(validator);
		}
		
	}
	

	
	
	private class ownActionAgent extends XX_ActionAgent {

		public void executeAgentCode(ExecINFO oExecInfo){
			
			UP_DOWN_GenericButton4Masks oThis = UP_DOWN_GenericButton4Masks.this;
			
			try	{
				if (oThis.map.get_oInternalSQLResultMAP()!=null) {
						
					String baseTable = new Archiver_Normalized_Tablename(
							map.get_oInternalSQLResultMAP().get_oSQLFieldMAP().get_cMAIN_TABLE()).get_TableBaseName();
					String id_table = map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

					AM_BasicContainer oPopUp = new AM_BasicContainer(	baseTable,
																		id_table,
																		oThis.moduleKey,
																		true);

					oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien ..."));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei Neuerfassung ist ein Upload ins Archiv nicht möglich!")));
				}
				
			} catch (myException ex) {
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Download-Window: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
}
