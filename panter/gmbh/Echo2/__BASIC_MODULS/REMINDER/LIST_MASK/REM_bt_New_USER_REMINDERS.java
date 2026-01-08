 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_Factory;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_CONST.REM_BUTTONS;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;



public class REM_bt_New_USER_REMINDERS extends MyE2_Grid implements REM__IF_getTableAndID, IF_BasicModuleContainer_ADD_ON_Component {
	private String 						cMODULKENNER = null; 
	
	private MyE2_Button btnListReminder;
	private REM_bt_New  btnNewReminder;
	
	
	// Navilist aus der aufrufenden Liste
    private _TAB  				m_table = null;
    private String				m_tableID = null;
    
    /**
     * Konstruktor für den Allgemeinen Reminder (am User hängend) 
     * @author manfred
     * @date 27.04.2016
     *
     * @throws myException
     */
    public REM_bt_New_USER_REMINDERS()  throws myException {
    	super(2,0);
   		btnListReminder 	= new MyE2_Button(E2_ResourceIcon.get_RI("wecker_liste.png"));
   		btnListReminder.setToolTipText("Zeigt die Liste der Erinnerungen and, die dem Benutzer direkt zugeordnet sind, ohne Verbindung zu einem Modul");

   		btnNewReminder		= new REM_bt_New(this);
   		btnNewReminder.setToolTipText("Legt eine persönliche Erinnerung an");
   		
       	this.m_table 			= _TAB.user;
        this.m_tableID			= bibALL.get_ID_USER();
         
        
//        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(REM_BUTTONS.NEW.db_val()));
        this.add(btnNewReminder,E2_INSETS.I_0_0_0_0);
        this.add(btnListReminder,E2_INSETS.I_0_0_0_0);
        
        btnListReminder.add_oActionAgent(new ownActionStartReminderModuleTable());
    }


    
    
    
    private class ownActionStartReminderModuleTable extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_String sDialogHeading = new MyE2_String("Erinnerungsmeldungen des Benutzers " + bibALL.get_USERNAME());
			REM_LIST_BasicModuleContainer oContainer = new REM_LIST_BasicModuleContainer(m_table, null, m_tableID);
			oContainer.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(1500), new Extent(800), sDialogHeading);
		}
    }
    
       

	@Override
	public _TAB get_table() {
		return this.m_table;
	}

	
	@Override
	public String get_id() {
		return this.m_tableID;
	}
	
	
	@Override
	public MODUL get_modul() throws myException {
		return null;
	}
	
	
    
    
	
	
	
	/**
	 * privater Button zum direkten anlegen eines Reminders (generisch)
	 * @author manfred
	 * @date 27.04.2016
	 *
	 */
	private class REM_bt_New extends RB_bt_New {
	    
		private REM__IF_getTableAndID list_container = null;

	    
	    public REM_bt_New(REM__IF_getTableAndID p_list_container) {
	        super(E2_ResourceIcon.get_RI("wecker_person.png"),true);
	        
	        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(REM_BUTTONS.NEW.db_val()));
			this.list_container = p_list_container;
	    }
	    
	    
	    @Override
	    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
	        return new REM_MASK_MaskModulContainer(this.list_container);
	    }
	    
	    @Override
	    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
	        return new REM_MASK_DataObjectCollector();
	    }
	    @Override
	    public void define_Actions_4_saveButton(RB_bt_New btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
	        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
	        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	        bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionSendReminderOnCreation(maskPopup));
	    }
	    
	    @Override
	    public void define_Actions_4_CloseButton(RB_bt_New btNewInList,    RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)    throws myException {
	        bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	    }
	    
	    
	    /**
	     * privater eventhandler im privaten Button zum Ausführen des sofort-Versendens der Nachricht
	     * @author manfred
	     * @date 27.04.2016
	     *
	     */
	    private class ownActionSendReminderOnCreation extends XX_ActionAgent {
	        private RB_ModuleContainerMASK maskPopup = null;
	        
	        public ownActionSendReminderOnCreation(RB_ModuleContainerMASK p_maskPopup) {
	            super();
	            this.maskPopup = p_maskPopup;
	        }
	        @Override
	        public void executeAgentCode(ExecINFO oExecInfo) throws myException {
	        	// Nachrichten-Sofortverschickung ausführen
	            String id_new = this.maskPopup.rb_ComponentMapCollector(new REM__TableKey()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.reminder_def.n());
	            REMINDER_Factory oRemFactory = new REMINDER_Factory();
	            oRemFactory.doCheckAndSendReminderOnCreation(id_new);
	        }
	    }
	    
	    
	}



	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component#get_bIsShown()
	 */
	@Override
	public boolean get_bIsShown() throws myException {
		return true;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component#set_cMODULKENNER(java.lang.String)
	 */
	@Override
	public void set_cMODULKENNER(String cModulKenner) {
		this.cMODULKENNER = cModulKenner;
		
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component#get_cMODULKENNER()
	 */
	@Override
	public String get_cMODULKENNER() {
		// TODO Auto-generated method stub
		return this.cMODULKENNER;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component#add_cZusatzMODULKENNER(java.lang.String)
	 */
	@Override
	public void add_cZusatzMODULKENNER(String cModulKenner) {
		// TODO Auto-generated method stub
		
	}
	 
	
	
	
	
}
 
