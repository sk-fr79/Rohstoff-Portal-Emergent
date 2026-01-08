package panter.gmbh.Echo2.components;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_CheckBoxAnAus;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_CheckBox_WithUserSetting extends MyE2_CheckBox 
{
	private String cHASH_STRING_4_SAVE_STATUS = null;
	private String cMODULEKENNER = null;

	public MyE2_CheckBox_WithUserSetting(String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super();
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(boolean bIsSelected, boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(bIsSelected, bSetDisabledFromBasic);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(Object cText, boolean bIsSelected,	boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(cText, bIsSelected, bSetDisabledFromBasic);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(Object cText, MutableStyle oStyle,	boolean bIsSelected, boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(cText, oStyle, bIsSelected, bSetDisabledFromBasic);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(Object cText, MutableStyle oStyle, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(cText, oStyle);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(Object cText,MyE2_String cToolTipText, boolean bIsSelected,boolean bSetDisabledFromBasic, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(cText, cToolTipText, bIsSelected, bSetDisabledFromBasic);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(Object cText, MyE2_String cToolTipText, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER) 
	{
		super(cText, cToolTipText);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}

	public MyE2_CheckBox_WithUserSetting(Object cText, String HASH_STRING_4_SAVE_STATUS, String MODULEKENNER)  
	{
		super(cText);
		this.cHASH_STRING_4_SAVE_STATUS = HASH_STRING_4_SAVE_STATUS;
		this.cMODULEKENNER = MODULEKENNER;
		this.setSelected(this.get_StoredStatus());
	}
	

	public void save_SelectedStatus() throws myException 
	{
		String cActualWert = MyE2_CheckBox_WithUserSetting.this.isSelected()?"Y":"N";
		new E2_UserSetting_CheckBoxAnAus(MyE2_CheckBox_WithUserSetting.this.cHASH_STRING_4_SAVE_STATUS).STORE(
				MyE2_CheckBox_WithUserSetting.this.cMODULEKENNER, cActualWert);

	}

	
	
	public boolean get_StoredStatus()
	{
		String cRueck = "N";
		
		try 
		{
			cRueck = (String)new E2_UserSetting_CheckBoxAnAus(MyE2_CheckBox_WithUserSetting.this.cHASH_STRING_4_SAVE_STATUS).get_Settings(MyE2_CheckBox_WithUserSetting.this.cMODULEKENNER);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return S.NN(cRueck).equals("Y");
	}
	
	
	

	
	
	
}
