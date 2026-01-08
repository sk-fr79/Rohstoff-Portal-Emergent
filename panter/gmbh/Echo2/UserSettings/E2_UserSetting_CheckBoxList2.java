package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_CheckBoxList2 extends XXX_UserSetting 
{
	private String 					SESSION_HASH_SAVE_MULTI_CHECKBOXES = 	null;
	private Vector<MyE2_CheckBox>   vCB = 			new Vector<MyE2_CheckBox>();
	
	private String                  cMODULE_IDENTIFIER = null;
	
	private ownActionAgent          oAction4SaveStatus = new ownActionAgent();
	

	public E2_UserSetting_CheckBoxList2(Vector<MyE2_CheckBox> v_CB, String  MODULE_IDENTIFIER, String cSESSION_HASH) 
	{
		super();
		this.vCB.removeAllElements();
		this.vCB.addAll(v_CB);
		this.cMODULE_IDENTIFIER = MODULE_IDENTIFIER;
		this.SESSION_HASH_SAVE_MULTI_CHECKBOXES = cSESSION_HASH;
	}

	@Override
	public String get_SessionHash() 
	{
		return  this.SESSION_HASH_SAVE_MULTI_CHECKBOXES;
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{
		return (String)oSetting;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
	{
		return cDatabaseSetting;
	}


	private String get_Status_der_CBs() throws myException
	{
		
		String cRueck = "";
		for (int i=0;i<this.vCB.size();i++)
		{
			cRueck += (this.vCB.get(i).isSelected()?"Y":"N");
			cRueck += "|";
		}
		return cRueck;
	}

	
	public void restore_Status_cb() throws myException
	{
		String cWerte = S.NN((String)this.get_Settings(this.cMODULE_IDENTIFIER));
		
		Vector<String> vWerte = bibALL.TrenneZeile(cWerte, "|");
		
		for (int i=0;i<this.vCB.size();i++)
		{
			if (vWerte.size()>i)
			{
				this.vCB.get(i).setSelected(vWerte.get(i).equals("Y"));
			}
		}
	}
	
	
	
	public void saveSelectedStatus() throws myException
	{
		this.STORE(this.cMODULE_IDENTIFIER, this.get_Status_der_CBs());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_UserSetting_CheckBoxList2.this.saveSelectedStatus();
		}
	}

	public ownActionAgent get_oAction4SaveStatus() 
	{
		return oAction4SaveStatus;
	}

}
