package panter.gmbh.Echo2.UserSettings;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_ListOfSaveables_NG extends XXX_UserSetting  {
	private ArrayList<IF_Saveable>   									v_savebles =	new ArrayList<IF_Saveable>();
	private String  												  	cMODULE_IDENTIFIER = 	null;
	
	private ENUM_USER_SAVEKEY  											SESSION_HASH_KEY = 	null;
	
	private ownActionAgent          									oAction4SaveStatus = 	new ownActionAgent();
	private boolean   													b_init = 				false;

	public E2_UserSetting_ListOfSaveables_NG(ENUM_USER_SAVEKEY session_key) {
		super();
		this.SESSION_HASH_KEY = session_key;
	}

	public void _init(ArrayList<IF_Saveable> p_v_savebles, String MODULE_IDENTIFIER) {
		this.v_savebles.clear();
		this.v_savebles.addAll(p_v_savebles);
		this.cMODULE_IDENTIFIER = MODULE_IDENTIFIER;
		this.b_init = true;
	}
	
	@Override
	public String get_SessionHash() {
		return  this.SESSION_HASH_KEY.name();
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException {
		if (!this.b_init) {
			throw new myException(this,"Usersetting-object not yet initialized !!");
		}
		return (String)oSetting;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException {
		if (!this.b_init) {
			throw new myException(this,"Usersetting-object not yet initialized !!");
		}
		return cDatabaseSetting;
	}


	private String get_Status_of_saveables() throws myException 	{
		
		String cRueck = "|";
		for (int i=0;i<this.v_savebles.size();i++)
		{
			cRueck += (this.v_savebles.get(i).get_value_to_save());
			cRueck += "|";
		}
		return cRueck;
	}

	
	public void RESTORE() throws myException  {
		if (!this.b_init) {
			throw new myException(this,"Usersetting-object not yet initialized !!");
		}
		String cWerte = S.NN((String)this.get_Settings(this.cMODULE_IDENTIFIER));
		
		Vector<String> vWerte = bibALL.TrenneZeile(cWerte, "|");
		
		for (int i=0;i<this.v_savebles.size();i++) {
			if (vWerte.size()>i) {
				this.v_savebles.get(i).restore_value(vWerte.get(i));
			} else {
				this.v_savebles.get(i).set_component_to_status_not_saved();
			}
		}
	}
	
	
	
	public void SAVE() throws myException 	{
		if (!this.b_init) {
			throw new myException(this,"Usersetting-object not yet initialized !!");
		}

		this.STORE(this.cMODULE_IDENTIFIER, this.get_Status_of_saveables());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent  {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
			E2_UserSetting_ListOfSaveables_NG.this.SAVE();
		}
	}

	public ownActionAgent get_oAction4SaveStatus()	{
		return oAction4SaveStatus;
	}

}
