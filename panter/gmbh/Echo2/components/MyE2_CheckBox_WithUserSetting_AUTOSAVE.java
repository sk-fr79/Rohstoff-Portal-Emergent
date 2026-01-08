package panter.gmbh.Echo2.components;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_CheckBox_WithUserSetting_AUTOSAVE extends MyE2_CheckBox_WithUserSetting 
{

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(boolean bIsSelected,boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(bIsSelected, bSetDisabledFromBasic, HASH_STRING_4_SAVE_STATUS,
				MODULEKENNER);
		
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(Object cText,
			boolean bIsSelected, boolean bSetDisabledFromBasic,
			String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) {
		super(cText, bIsSelected, bSetDisabledFromBasic, HASH_STRING_4_SAVE_STATUS,
				MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(Object cText,
			MutableStyle oStyle, boolean bIsSelected,
			boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS,
			String MODULEKENNER) {
		super(cText, oStyle, bIsSelected, bSetDisabledFromBasic,
				HASH_STRING_4_SAVE_STATUS, MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(Object cText,
			MutableStyle oStyle, String HASH_STRING_4_SAVE_STATUS,
			String MODULEKENNER) {
		super(cText, oStyle, HASH_STRING_4_SAVE_STATUS, MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(Object cText,
			MyE2_String cToolTipText, boolean bIsSelected,
			boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS,
			String MODULEKENNER) {
		super(cText, cToolTipText, bIsSelected, bSetDisabledFromBasic,
				HASH_STRING_4_SAVE_STATUS, MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(Object cText,
			MyE2_String cToolTipText, String HASH_STRING_4_SAVE_STATUS,
			String MODULEKENNER) {
		super(cText, cToolTipText, HASH_STRING_4_SAVE_STATUS, MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(Object cText,
			String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) {
		super(cText, HASH_STRING_4_SAVE_STATUS, MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	public MyE2_CheckBox_WithUserSetting_AUTOSAVE(
			String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) {
		super(HASH_STRING_4_SAVE_STATUS, MODULEKENNER);
		this.add_oActionAgent(new ownActionSaveSettings());
	}

	private class ownActionSaveSettings extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_CheckBox_WithUserSetting_AUTOSAVE.this.save_SelectedStatus();
		}
		
	}
	
	
	
}
