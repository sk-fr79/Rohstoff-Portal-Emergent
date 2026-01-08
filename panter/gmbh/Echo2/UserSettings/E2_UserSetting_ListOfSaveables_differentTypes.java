package panter.gmbh.Echo2.UserSettings;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_UserSetting_ListOfSaveables_differentTypes extends XXX_UserSetting  {
	private ArrayList<IF_Saveable_differentTypes>   					vComponents = 			new ArrayList<IF_Saveable_differentTypes>();
	private ENUM_USER_SAVEKEY  											cMODULE_IDENTIFIER = 	null;
	private String   												  	addon_identifier = 		null;
	private ownActionAgent          									oAction4SaveStatus = 	new ownActionAgent();
	private boolean   													b_init = 				false;

	public E2_UserSetting_ListOfSaveables_differentTypes() {
		super();
	}

	public void _init(		ArrayList<IF_Saveable_differentTypes> p_v_Components, 
							ENUM_USER_SAVEKEY  MODULE_IDENTIFIER,
							String p_addonIdendifier) {
		this.vComponents.clear();
		this.vComponents.addAll(p_v_Components);
		this.cMODULE_IDENTIFIER = MODULE_IDENTIFIER;
		this.addon_identifier = S.NN(p_addonIdendifier);
		this.b_init = true;
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
		for (int i=0;i<this.vComponents.size();i++)
		{
			cRueck += (S.NN(this.vComponents.get(i).get_component_key())+"@"+this.vComponents.get(i).get_value_to_save());
			cRueck += "|";
		}
		return cRueck;
	}

	
	public void RESTORE() throws myException  {
		if (!this.b_init) {
			throw new myException(this,"Usersetting-object not yet initialized !!");
		}

		
		String cWerte = S.NN((String)this.get_Settings(this.cMODULE_IDENTIFIER.toString()+"@"+this.addon_identifier));
		
		Vector<String> vWerte = bibALL.TrenneZeile(cWerte, "|");
		
		for (int i=0;i<this.vComponents.size();i++) {
			boolean bFoundComponentInStoreString = false;
			for (String c_wert: vWerte) {
//				DEBUG.System_println("-----------------<"+c_wert+">");
				if (c_wert.startsWith(this.vComponents.get(i).get_component_key()+"@")) {
					bFoundComponentInStoreString = true;
					this.vComponents.get(i).restore_value(c_wert.substring(
								(this.vComponents.get(i).get_component_key()+"@").length()));
					break;
				}
			}
			if (!bFoundComponentInStoreString) {
				this.vComponents.get(i).set_component_to_status_not_saved();
			}
		}
	}
	
	
	
	public void SAVE() throws myException 	{
		if (!this.b_init) {
			throw new myException(this,"Usersetting-object not yet initialized !!");
		}

		this.STORE(this.cMODULE_IDENTIFIER.toString()+"@"+this.addon_identifier, this.get_Status_of_saveables());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent  {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
			E2_UserSetting_ListOfSaveables_differentTypes.this.SAVE();
		}
	}

	public ownActionAgent get_oAction4SaveStatus()	{
		return oAction4SaveStatus;
	}

}
